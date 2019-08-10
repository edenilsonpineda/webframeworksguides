package sv.edu.udb.www.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import sv.edu.udb.www.model.*;
import sv.edu.udb.www.beans.*;
import sv.edu.udb.www.utils.Validaciones;

/**
 * Servlet implementation class PrestamosController
 */
@WebServlet(name = "/PrestamosController", urlPatterns = {"/prestamos.do"})
public class PrestamosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ArrayList<String> listaErrores = new ArrayList<>();
	PrestamoModel modelo = new PrestamoModel();
	LibrosModel libro = new LibrosModel();
	DocenteModel docente = new DocenteModel();
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	response.setContentType("text/html;charset=UTF-8");
    	
    	try (PrintWriter out = response.getWriter()){
    		if(request.getParameter("op") == null) {
    			listar(request, response);
    			return;
    		}
    		
    		String operacion = request.getParameter("op");
    		
    		switch(operacion) {
    		case "listar":
    			listar(request, response);
    			break;
    			
    		case "nuevo":
    			nuevo(request, response);
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
    			
    		case "detalles":
    			detalles(request, response);
    			break;
    		
    		default:
    			request.getRequestDispatcher("/error404.jsp").forward(request, response);
    			break;
    		}//cierre del swicth
    	}
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	
	@Override
	public String getServletInfo() {
		return "Short description";
	}
	
	
	private void listar(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("listaPrestamo", modelo.listaPrestamo());
			request.getRequestDispatcher("/prestamos/listaPrestamos.jsp").forward(request, response);
		}catch(SQLException ex) {
			Logger.getLogger(PrestamosController.class.getName()).log(Level.SEVERE, null, ex);
		}catch(ServletException ex) {
			Logger.getLogger(PrestamosController.class.getName()).log(Level.SEVERE, null, ex);
		}catch(IOException ex) {
			Logger.getLogger(PrestamosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
	private void insertar(HttpServletRequest request, HttpServletResponse response) {
		 try{
			 listaErrores.clear();
			 Prestamo miPrestamo = new Prestamo();
			 miPrestamo.setCodigo_libro(request.getParameter("codigo_libro"));
			 miPrestamo.setCodigo_docente(request.getParameter("codigo_docente"));
			 miPrestamo.setFecha_prestamo(request.getParameter("fecha_prestamo"));
			 miPrestamo.setFecha_devolucion(request.getParameter("fecha_devolucion"));

		 
			 if(Validaciones.isEmpty(miPrestamo.getFecha_prestamo())){
				 listaErrores.add("La fecha de prestamos debe agregarse");
			 }
			 
			 if(Validaciones.isEmpty(miPrestamo.getFecha_devolucion())){
				 listaErrores.add("La fecha de devolucion debe agregarse");
			 }


			 if(listaErrores.size() >0){
				 request.setAttribute("listaErrores", listaErrores);
				 request.setAttribute("prestamo", miPrestamo);
				 request.getRequestDispatcher("prestamos.do?op=nuevo").forward(request, response);
			 }else{
				 if(modelo.insertarPrestamo(miPrestamo)>0){
					 request.getSession().setAttribute("exito", "prestamo registrado exitosamente");
					 response.sendRedirect(request.getContextPath() +"/prestamos.do?op=listar");
				 }else{
					 request.getSession().setAttribute("fracaso", "El prestamo no ha sido ingresado ya hay un prestamo con este codigo");
					 response.sendRedirect(request.getContextPath() +"/prestamos.do?op=listar");
				 }
			 }
		 } catch (ServletException ex) {
			 Logger.getLogger(PrestamosController.class.getName()).log(Level.SEVERE, null, ex);
		 } catch (IOException ex) {
			 Logger.getLogger(PrestamosController.class.getName()).log(Level.SEVERE, null, ex);
		 } catch (SQLException ex) {
			 Logger.getLogger(PrestamosController.class.getName()).log(Level.SEVERE, null, ex);
		 }

	}
	
	
	private void obtener(HttpServletRequest request, HttpServletResponse response) {
		 try {
			 String codigo = request.getParameter("id");
			 Prestamo miPrestamo = modelo.obtenerPrestamo(codigo);
			 if(miPrestamo != null){
				 request.setAttribute("prestamo", miPrestamo);
				 request.setAttribute( "listaLibros", libro.listaLibros() );
				 request.setAttribute("listaDocentes", docente.listarDocentes());
				 request.getRequestDispatcher("/prestamos/editarPrestamo.jsp").forward(request, response);
			 }else{
				 response.sendRedirect(request.getContextPath() + "/error404.jsp");
			 }
		 } catch (SQLException | ServletException | IOException ex) {
			 Logger.getLogger(PrestamosController.class.getName()).log(Level.SEVERE, null, ex);
		 }
	}
	
	private void modificar(HttpServletRequest request, HttpServletResponse response) {
		 try{
		 listaErrores.clear();
		 Prestamo miPrestamo = new Prestamo();
		 miPrestamo.setCodigo_prestamo(Integer.parseInt(request.getParameter("codigo_prestamo")));
		 miPrestamo.setCodigo_libro(request.getParameter("codigo_libro"));
		 miPrestamo.setCodigo_docente(request.getParameter("codigo_docente"));
		 miPrestamo.setFecha_prestamo(request.getParameter("fecha_prestamo"));
		 miPrestamo.setFecha_devolucion(request.getParameter("fecha_devolucion"));
		 
		 if(Validaciones.isEmpty(miPrestamo.getFecha_devolucion())){
			 listaErrores.add("La fecha de devolucion debe agregarse");
		 }

		 if(listaErrores.size() >0){
			 request.setAttribute("prestamo", miPrestamo);
			 request.setAttribute("listaErrores", listaErrores);
			 request.getRequestDispatcher("prestamos.do?op=obtener").forward(request, response);
		 }else{
			 if(modelo.modificarPrestamo(miPrestamo)>0){
				 request.getSession().setAttribute("exito", "prestamo modificado exitosamente");
				 response.sendRedirect(request.getContextPath() +"/prestamos.do?op=listar");
			 }else{
				 request.getSession().setAttribute("fracaso", "El prestamo no ha sido modificado ya hay un prestamo con este codigo");
				 response.sendRedirect(request.getContextPath() +"/prestamos.do?op=listar");
			 }
		 }
	} catch (ServletException ex) {
		 Logger.getLogger(PrestamosController.class.getName()).log(Level.SEVERE, null, ex);
	} catch (IOException ex) {
		 Logger.getLogger(PrestamosController.class.getName()).log(Level.SEVERE, null, ex);
	} catch (SQLException ex) {
		 Logger.getLogger(PrestamosController.class.getName()).log(Level.SEVERE, null, ex);
	}

 }
	
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		 try {
			 String codigo = request.getParameter("id");
			 if(modelo.eliminarPrestamo(codigo) > 0){
				 request.setAttribute("exito","prestamo eliminado exitosamente");
			 }else{
				 request.setAttribute("fracaso", "No se puede eliminar este prestamo");
			 }
			 
			 request.getRequestDispatcher("/prestamos.do?op=listar").forward(request, response);
		 } catch (SQLException | ServletException | IOException ex) {
			 Logger.getLogger(PrestamosController.class.getName()).log(Level.SEVERE, null, ex);
		 }
	}
	
	private void nuevo(HttpServletRequest request, HttpServletResponse response) {
		 try {
			 request.setAttribute( "listaLibros", libro.listaLibros());
			 request.setAttribute("listaDocentes", docente.listarDocentes());
			 
			 request.getRequestDispatcher("/prestamos/nuevoPrestamo.jsp").forward(request, response);
		 } catch (SQLException | ServletException | IOException ex) {
			 Logger.getLogger(PrestamosController.class.getName()).log(Level.SEVERE, null, ex);
		 }
	}
	
	
	private void detalles(HttpServletRequest request, HttpServletResponse response) {
		 try {
			 PrintWriter out = response.getWriter();
			 String codigo = request.getParameter("id");
			 Prestamo prestamo = modelo.detallePrestamo(codigo);
			 JSONObject json = new JSONObject();
			 json.put("codigo_prestamo", prestamo.getCodigo_prestamo());
			 json.put("codigo_libro", prestamo.getCodigo_libro());
			 json.put("codigo_docente", prestamo.getCodigo_docente());
			 json.put("fecha_prestamo", prestamo.getFecha_prestamo());
			 json.put("fecha_devolucion", prestamo.getFecha_devolucion());
			 out.print(json);
		 } catch (SQLException ex) {
			 Logger.getLogger(PrestamosController.class.getName()).log(Level.SEVERE, null, ex);
		 } catch (IOException ex) {
			 Logger.getLogger(PrestamosController.class.getName()).log(Level.SEVERE, null, ex);
		 }
	}

}
