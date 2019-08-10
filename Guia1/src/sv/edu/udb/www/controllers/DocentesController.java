package sv.edu.udb.www.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sv.edu.udb.www.beans.Docente;
import sv.edu.udb.www.model.DocenteModel;
import sv.edu.udb.www.utils.Validaciones;

/**
 * Servlet implementation class DocentesController
 */
@WebServlet(name = "/DocentesController", urlPatterns = {"/docentes.do"})
public class DocentesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ArrayList<String> listaErrores = new ArrayList<String>();
	DocenteModel modelo = new DocenteModel();
    
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			if (request.getParameter("op") == null) {
				listar(request, response);
				return;
			}
			
			String operacion = request.getParameter("op");

			switch (operacion) {
				case "listar":
					listar(request, response);
					break;
				case "nuevo":
					request.getRequestDispatcher("/docentes/nuevoDocente.jsp").forward(request, response);
					break;
				case "insertar":
					insertar(request, response);
					break;
				case "obtener":
					obtener(request, response);
					break;
				case "modificar":
					modificar(request, response);
					break;
				case "eliminar":
					eliminar(request, response);
					break;
			}
		}catch(Exception ex) {
			System.out.println(ex.getStackTrace());
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}


	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

	private void listar(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("listaDocentes", modelo.listarDocentes());
			request.getRequestDispatcher("/docentes/listarDocentes.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(DocentesController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void insertar(HttpServletRequest request, HttpServletResponse response) {
		try {
			listaErrores.clear();
			Docente miDocente = new Docente();
			miDocente.setCodigo_docente(request.getParameter("codigo"));
			miDocente.setNombre_docente(request.getParameter("nombre"));
			
			if (Validaciones.isEmpty(miDocente.getCodigo_docente())) {
				listaErrores.add("El codigo del docente es obligatorio");
			}
			
			if (Validaciones.isEmpty(miDocente.getNombre_docente())) {
				listaErrores.add("El nombre del docente es obligatorio");
			}
			
			
			if (listaErrores.size() > 0) {
				request.setAttribute("listaErrores", listaErrores);
				request.setAttribute("docente", miDocente);
				request.getRequestDispatcher("docentes.do?op=nuevo").forward(request, response);
			} else {
				if (modelo.insertarDocente(miDocente) > 0) {
					request.getSession().setAttribute("exito", "docente registrado exitosamente");
					response.sendRedirect(request.getContextPath() + "/docentes.do?op=listar");
				} else {
					request.getSession().setAttribute("fracaso","El docente no ha sido ingresado ya hay un autor con este codigo");
					response.sendRedirect(request.getContextPath() + "/docentes.do?op=listar");
				}
			}
		} catch (IOException | SQLException | ServletException ex) {
			Logger.getLogger(DocentesController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void obtener(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codigo = request.getParameter("id");
			Docente miDocente = modelo.obtenerDocente(codigo);
			if (miDocente != null) {
				request.setAttribute("docente", miDocente);
				request.getRequestDispatcher("/docentes/editarDocente.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/error404.jsp");
			}
		} catch (SQLException | IOException | ServletException ex) {
			Logger.getLogger(DocentesController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void modificar(HttpServletRequest request, HttpServletResponse response) {
		try {
			listaErrores.clear();
			Docente miDocente = new Docente();
			miDocente.setCodigo_docente(request.getParameter("codigo"));
			miDocente.setNombre_docente(request.getParameter("nombre"));

			if (Validaciones.isEmpty(miDocente.getCodigo_docente())) {
				listaErrores.add("El codigo del docente es obligatorio");
			}
			
			if (Validaciones.isEmpty(miDocente.getNombre_docente())) {
				listaErrores.add("El nombre del docente es obligatorio");
			}
			
			
			if (listaErrores.size() > 0) {
				request.setAttribute("listaErrores", listaErrores);
				request.setAttribute("docente", miDocente);
				request.getRequestDispatcher("docentes.do?op=obtener").forward(request, response);
			} else {
				if (modelo.modificarDocente(miDocente) > 0) {
					request.getSession().setAttribute("exito", "docente modificado exitosamente");
					response.sendRedirect(request.getContextPath() + "/docentes.do?op=listar");
				} else {
					request.getSession().setAttribute("fracaso",
							"El docente no ha sido modificado ya hay un autor con este codigo");
					response.sendRedirect(request.getContextPath() + "/docentes.do?op=listar");
				}
			}
		} catch (IOException | SQLException | ServletException ex) {
			Logger.getLogger(DocentesController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codigo = request.getParameter("id");
			if (modelo.eliminarDocente(codigo) > 0) {
				request.setAttribute("exito", "Docente eliminado exitosamente");

			} else {
				request.setAttribute("fracaso", "No se puede eliminar este docente");
			}
			request.getRequestDispatcher("/docentes.do?op=listar").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(DocentesController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
