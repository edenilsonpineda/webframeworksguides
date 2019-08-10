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

import sv.edu.udb.www.model.EditorialesModel;
import sv.edu.udb.www.beans.Editorial;
import sv.edu.udb.www.utils.Validaciones;

/**
 * Servlet implementation class EditorialesController
 */
@WebServlet(name = "/EditorialesController", urlPatterns = {"editoriales.do"})
public class EditorialesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	ArrayList<String> listaErrores = new ArrayList<String>();
	EditorialesModel modelo = new EditorialesModel();
	
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
					request.getRequestDispatcher("/editoriales/nuevoEditorial.jsp").forward(request, response);
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
			request.setAttribute("listaEditoriales", modelo.listarEditoriales());
			request.getRequestDispatcher("/editoriales/listarEditoriales.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(EditorialesController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void insertar(HttpServletRequest request, HttpServletResponse response) {
		try {
			listaErrores.clear();
			
			Editorial miEditorial = new Editorial();
			miEditorial.setCodigoEditorial(request.getParameter("codigo"));
			miEditorial.setNombreEditorial(request.getParameter("nombre"));
			miEditorial.setContacto(request.getParameter("contacto"));
			miEditorial.setTelefono(request.getParameter("telefono"));
			
			
			if (Validaciones.isEmpty(miEditorial.getCodigoEditorial())) {
				listaErrores.add("El codigo del editorial es obligatorio");
			}
			
			if (Validaciones.isEmpty(miEditorial.getNombreEditorial())) {
				listaErrores.add("El nombre del editorial es obligatorio");
			}
			
			if (Validaciones.isEmpty(miEditorial.getContacto())) {
				listaErrores.add("El contacto del editorial es obligatorio");
			}
			
			if (Validaciones.isEmpty(miEditorial.getTelefono())) {
				listaErrores.add("El teléfono del editorial es obligatorio");
			}
			
			if (listaErrores.size() > 0) {
				request.setAttribute("listaErrores", listaErrores);
				request.setAttribute("editorial", miEditorial);
				request.getRequestDispatcher("editoriales.do?op=nuevo").forward(request, response);
			} else {
				if (modelo.insertarEditorial(miEditorial) > 0) {
					request.getSession().setAttribute("exito", "editorial registrado exitosamente");
					response.sendRedirect(request.getContextPath() + "/editoriales.do?op=listar");
				} else {
					request.getSession().setAttribute("fracaso","El editorial no ha sido ingresado ya hay un editorial con este codigo");
					response.sendRedirect(request.getContextPath() + "/editoriales.do?op=listar");
				}
			}
		} catch (IOException | SQLException | ServletException ex) {
			Logger.getLogger(EditorialesController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void obtener(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codigo = request.getParameter("id");
			Editorial miEditorial = modelo.obtenerEditorial(codigo);
			if (miEditorial != null) {
				request.setAttribute("editorial", miEditorial);
				request.getRequestDispatcher("/editoriales/editarEditorial.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/error404.jsp");
			}
		} catch (SQLException | IOException | ServletException ex) {
			Logger.getLogger(EditorialesController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void modificar(HttpServletRequest request, HttpServletResponse response) {
		try {
			listaErrores.clear();
			Editorial miEditorial = new Editorial();
			miEditorial.setCodigoEditorial(request.getParameter("codigo"));
			miEditorial.setNombreEditorial(request.getParameter("nombre"));
			miEditorial.setContacto(request.getParameter("contacto"));
			miEditorial.setTelefono(request.getParameter("telefono"));
			
			if (Validaciones.isEmpty(miEditorial.getCodigoEditorial())) {
				listaErrores.add("El codigo del editorial es obligatorio");
			}
			
			if (Validaciones.isEmpty(miEditorial.getNombreEditorial())) {
				listaErrores.add("El nombre del editorial es obligatorio");
			}
			
			if (Validaciones.isEmpty(miEditorial.getContacto())) {
				listaErrores.add("El contacto del editorial es obligatorio");
			}
			
			if (Validaciones.isEmpty(miEditorial.getTelefono())) {
				listaErrores.add("El teléfono del editorial es obligatorio");
			}
			
			if (listaErrores.size() > 0) {
				request.setAttribute("listaErrores", listaErrores);
				request.setAttribute("editorial", miEditorial);
				request.getRequestDispatcher("editoriales.do?op=obtener").forward(request, response);
			} else {
				if (modelo.modificarEditorial(miEditorial) > 0) {
					request.getSession().setAttribute("exito", "editorial modificado exitosamente");
					response.sendRedirect(request.getContextPath() + "/editoriales.do?op=listar");
				} else {
					request.getSession().setAttribute("fracaso","El editorial no ha sido modificado ya hay un editorial con este codigo");
					response.sendRedirect(request.getContextPath() + "/editoriales.do?op=listar");
				}
			}
		} catch (IOException | SQLException | ServletException ex) {
			Logger.getLogger(EditorialesController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codigo = request.getParameter("id");
			if (modelo.eliminarEditorial(codigo) > 0) {
				request.setAttribute("exito", "Editorial eliminado exitosamente");

			} else {
				request.setAttribute("fracaso", "No se puede eliminar este editorial");
			}
			request.getRequestDispatcher("/editoriales.do?op=listar").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(EditorialesController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
