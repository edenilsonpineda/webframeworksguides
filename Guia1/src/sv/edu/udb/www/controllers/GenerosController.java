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
import sv.edu.udb.www.beans.Genero;
import sv.edu.udb.www.model.GenerosModel;
import sv.edu.udb.www.utils.Validaciones;

/**
 * Servlet implementation class GenerosController
 */
@WebServlet(name = "/GenerosController", urlPatterns = {"/generos.do"})
public class GenerosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ArrayList<String> listaErrores = new ArrayList<String>();
	GenerosModel modelo = new GenerosModel();
	
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
					request.getRequestDispatcher("/generos/nuevoGenero.jsp").forward(request, response);
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
			request.setAttribute("listaGeneros", modelo.listarGeneros());
			request.getRequestDispatcher("/generos/listarGeneros.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(GenerosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void insertar(HttpServletRequest request, HttpServletResponse response) {
		try {
			listaErrores.clear();
			
			Genero miGenero = new Genero();
			miGenero.setNombreGenero(request.getParameter("nombre"));
			miGenero.setDescripcion(request.getParameter("descripcion"));
			
			
			if (Validaciones.isEmpty(miGenero.getNombreGenero())) {
				listaErrores.add("El nombre del genero es obligatorio");
			}
			
			if (Validaciones.isEmpty(miGenero.getDescripcion())) {
				listaErrores.add("La descripcion es obligatoria");
			}
			
			if (listaErrores.size() > 0) {
				request.setAttribute("listaErrores", listaErrores);
				request.setAttribute("genero", miGenero);
				request.getRequestDispatcher("generos.do?op=nuevo").forward(request, response);
			} else {
				if (modelo.insertarGeneros(miGenero) > 0) {
					request.getSession().setAttribute("exito", "genero registrado exitosamente");
					response.sendRedirect(request.getContextPath() + "/generos.do?op=listar");
				} else {
					request.getSession().setAttribute("fracaso","El genero no ha sido ingresado ya hay un autor con este codigo");
					response.sendRedirect(request.getContextPath() + "/generos.do?op=listar");
				}
			}
		} catch (IOException | SQLException | ServletException ex) {
			Logger.getLogger(GenerosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void obtener(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codigo = request.getParameter("id");
			Genero miGenero = modelo.obtenerGenero(codigo);
			if (miGenero != null) {
				request.setAttribute("genero", miGenero);
				request.getRequestDispatcher("/generos/editarGenero.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/error404.jsp");
			}
		} catch (SQLException | IOException | ServletException ex) {
			Logger.getLogger(GenerosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void modificar(HttpServletRequest request, HttpServletResponse response) {
		try {
			listaErrores.clear();
			Genero miGenero = new Genero();
			miGenero.setIdGenero(Integer.parseInt(request.getParameter("codigo")));
			miGenero.setNombreGenero(request.getParameter("nombre"));
			miGenero.setDescripcion(request.getParameter("descripcion"));
			
			if(!Validaciones.esEnteroPositivo(request.getParameter("codigo"))){
				 listaErrores.add("El codigo debe ser un número entero positivo");
			 }
			
			if (Validaciones.isEmpty(miGenero.getNombreGenero())) {
				listaErrores.add("El nombre del genero es obligatorio");
			}
			
			if (Validaciones.isEmpty(miGenero.getDescripcion())) {
				listaErrores.add("La descripcion es obligatoria");
			}
			
			if (listaErrores.size() > 0) {
				request.setAttribute("listaErrores", listaErrores);
				request.setAttribute("genero", miGenero);
				request.getRequestDispatcher("generos.do?op=obtener").forward(request, response);
			} else {
				if (modelo.modificarGenero(miGenero) > 0) {
					request.getSession().setAttribute("exito", "genero modificado exitosamente");
					response.sendRedirect(request.getContextPath() + "/generos.do?op=listar");
				} else {
					request.getSession().setAttribute("fracaso","El genero no ha sido modificado ya hay un genero con este codigo");
					response.sendRedirect(request.getContextPath() + "/generos.do?op=listar");
				}
			}
		} catch (IOException | SQLException | ServletException ex) {
			Logger.getLogger(GenerosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codigo = request.getParameter("id");
			if (modelo.eliminarGenero(codigo) > 0) {
				request.setAttribute("exito", "Genero eliminado exitosamente");

			} else {
				request.setAttribute("fracaso", "No se puede eliminar este genero");
			}
			request.getRequestDispatcher("/generos.do?op=listar").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(GenerosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
