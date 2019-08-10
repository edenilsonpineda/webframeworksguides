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
import sv.edu.udb.www.beans.Libro;
import sv.edu.udb.www.model.AutoresModel;
import sv.edu.udb.www.model.EditorialesModel;
import sv.edu.udb.www.model.GenerosModel;
import sv.edu.udb.www.model.LibrosModel;
import sv.edu.udb.www.utils.Validaciones;

/**
 * @author S. Mártir
 */
@WebServlet(name = "/LibrosController", urlPatterns = {"/libros.do"})
public class LibrosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ArrayList<String> listaErrores = new ArrayList<>();
    LibrosModel modelo = new LibrosModel();
    EditorialesModel editorial = new EditorialesModel();
    AutoresModel autor = new AutoresModel();
    GenerosModel genero = new GenerosModel();
	
    
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
			request.setAttribute("listaLibros", modelo.listaLibros());
			request.getRequestDispatcher("/libros/listaLibros.jsp").forward(request, response);
		}catch(SQLException ex) {
			Logger.getLogger(LibrosController.class.getName()).log(Level.SEVERE, null, ex);
		}catch(ServletException ex) {
			Logger.getLogger(LibrosController.class.getName()).log(Level.SEVERE, null, ex);
		}catch(IOException ex) {
			Logger.getLogger(LibrosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
	private void insertar(HttpServletRequest request, HttpServletResponse response) {
		 try{
			 listaErrores.clear();
			 Libro miLibro = new Libro();
			 miLibro.setCodigoLibro(request.getParameter("codigo"));
			 miLibro.setNombreLibro(request.getParameter("nombre"));
			 miLibro.setExistencias(Integer.parseInt(request.getParameter("existencias")));
			 miLibro.setPrecio(Double.parseDouble(request.getParameter("precio")));
			 miLibro.setCodigoAutor(request.getParameter("codigoAutor"));
			 miLibro.setCodigoEditorial(request.getParameter("codigoEditorial"));
			 miLibro.setIdGenero(Integer.parseInt(request.getParameter("idGenero")));
			 miLibro.setDescripcion(request.getParameter("descripcion"));

			 if(Validaciones.isEmpty(miLibro.getCodigoLibro())){
				 	listaErrores.add("El codigo del libro es obligatorio");
			 }else if(!Validaciones.esCodigoLibro(miLibro.getCodigoLibro())){
				 	listaErrores.add("El codigo del libro debe tener el formato correcto LIB000");
			 }
		 
			 if(Validaciones.isEmpty(miLibro.getNombreLibro())){
				 listaErrores.add("El nombre del libro es obligatorio");
			 }
			 
			 if(!Validaciones.esEnteroPositivo(request.getParameter("existencias"))){
				 listaErrores.add("existencias deben ser enteros positivos");
			 }
		 
			 if(!Validaciones.esDecimalPositivo(request.getParameter("precio"))){
				 listaErrores.add("El precio deber ser un numero mayor o igual a 0");
			 }


			 if(listaErrores.size() >0){
				 request.setAttribute("libro", miLibro);
				 request.setAttribute("listaErrores", listaErrores);
				 request.getRequestDispatcher("libros.do?op=nuevo").forward(request, response);
			 }else{
				 if(modelo.insertarLibro(miLibro)>0){
					 request.getSession().setAttribute("exito", "libro registrado exitosamente");
					 response.sendRedirect(request.getContextPath() +"/libros.do?op=listar");
				 }else{
					 request.getSession().setAttribute("fracaso", "El libro no ha sido ingresado"+ "ya hay un libro con este codigo");
					 response.sendRedirect(request.getContextPath() +"/libros.do?op=listar");
				 }
			 }
		 } catch (ServletException ex) {
			 Logger.getLogger(LibrosController.class.getName()).log(Level.SEVERE, null, ex);
		 } catch (IOException ex) {
			 Logger.getLogger(LibrosController.class.getName()).log(Level.SEVERE, null, ex);
		 } catch (SQLException ex) {
			 Logger.getLogger(LibrosController.class.getName()).log(Level.SEVERE, null, ex);
		 }

	}
	
	
	private void obtener(HttpServletRequest request, HttpServletResponse response) {
		 try {
			 String codigo = request.getParameter("id");
			 Libro miLibro = modelo.obtenerLibro(codigo);
			 if(miLibro != null){
				 request.setAttribute("libro", miLibro);
				 request.setAttribute( "listaEditoriales", editorial.listarEditoriales());
				 request.setAttribute("listaAutores", autor.listarAutores());
				 request.setAttribute("listaGeneros", genero.listarGeneros());
				 request.getRequestDispatcher("/libros/editarLibros.jsp").forward(request, response);
			 }else{
				 response.sendRedirect(request.getContextPath() + "/error404.jsp");
			 }
		 } catch (SQLException | ServletException | IOException ex) {
			 Logger.getLogger(LibrosController.class.getName()).log(Level.SEVERE, null, ex);
		 }
	}
	
	private void modificar(HttpServletRequest request, HttpServletResponse response) {
		 try{
			 listaErrores.clear();
			 Libro miLibro = new Libro();
			 miLibro.setCodigoLibro(request.getParameter("codigo"));
			 miLibro.setNombreLibro(request.getParameter("nombre"));
			 miLibro.setExistencias(Integer.parseInt(request.getParameter("existencias")));
			 miLibro.setPrecio(Double.parseDouble(request.getParameter("precio")));
			 miLibro.setCodigoAutor(request.getParameter("codigoAutor"));
			 miLibro.setCodigoEditorial(request.getParameter("codigoEditorial"));
			 miLibro.setIdGenero(Integer.parseInt(request.getParameter("idGenero")));
			 miLibro.setDescripcion(request.getParameter("descripcion"));

			 if(Validaciones.isEmpty(miLibro.getCodigoLibro())){
				 listaErrores.add("El codigo del libro es obligatorio");
			 }else if(!Validaciones.esCodigoLibro(miLibro.getCodigoLibro())){
				 listaErrores.add("El codigo del libro debe tener el formato correcto LIB000");
			 }
			 
			 if(Validaciones.isEmpty(miLibro.getNombreLibro())){
				 listaErrores.add("El nombre del libro es obligatorio");
			 }
			 
			 if(!Validaciones.esEnteroPositivo(request.getParameter("existencias"))){
				 listaErrores.add("existencias deben ser enteros positivos");
			 }
			 
			 if(!Validaciones.esDecimalPositivo(request.getParameter("precio"))){
				 listaErrores.add("El precio deber ser un numero mayor o igual a 0");
			 }


			 if(listaErrores.size() >0){
				 request.setAttribute("libro", miLibro);
				 request.setAttribute("listaErrores", listaErrores);
				 request.getRequestDispatcher("libros.do?op=obtener").forward(request, response);
			 }else{
				 if(modelo.modificarLibro(miLibro)>0){
					 request.getSession().setAttribute("exito", "libro modificado exitosamente");
					 response.sendRedirect(request.getContextPath() +"/libros.do?op=listar");
				 }else{
					 request.getSession().setAttribute("fracaso", "El libro no ha sido modificado"+ "ya hay un libro con este codigo");
					 response.sendRedirect(request.getContextPath() +"/libros.do?op=listar");
				 }
			 }
		 } catch (ServletException ex) {
			 Logger.getLogger(LibrosController.class.getName()).log(Level.SEVERE, null, ex);
		 } catch (IOException ex) {
			 Logger.getLogger(LibrosController.class.getName()).log(Level.SEVERE, null, ex);
		 } catch (SQLException ex) {
			 Logger.getLogger(LibrosController.class.getName()).log(Level.SEVERE, null, ex);
		 }

	}
	
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		 try {
			 String codigo = request.getParameter("id");
			 if(modelo.eliminarLibro(codigo) > 0){
				 request.setAttribute("exito","Libro eliminado exitosamente");
			 }else{
				 request.setAttribute("fracaso", "No se puede eliminar este libro");
			 }
			 
			 request.getRequestDispatcher("/libros.do?op=listar").forward(request, response);
		 } catch (SQLException | ServletException | IOException ex) {
			 Logger.getLogger(LibrosController.class.getName()).log(Level.SEVERE, null, ex);
		 }
	}
	
	private void nuevo(HttpServletRequest request, HttpServletResponse response) {
		 try {
			 request.setAttribute( "listaEditoriales", editorial.listarEditoriales());
			 request.setAttribute("listaAutores", autor.listarAutores());
			 request.setAttribute("listaGeneros", genero.listarGeneros());
			 request.getRequestDispatcher("/libros/nuevoLibro.jsp").forward(request, response);
		 } catch (SQLException | ServletException | IOException ex) {
			 Logger.getLogger(LibrosController.class.getName()).log(Level.SEVERE, null, ex);
		 }
	}
	
	
	private void detalles(HttpServletRequest request, HttpServletResponse response) {
		 try {
			 PrintWriter out = response.getWriter();
			 String codigo = request.getParameter("id");
			 Libro libro = modelo.detalleLibro(codigo);
			 JSONObject json = new JSONObject();
			 json.put("codigo", libro.getCodigoLibro());
			 json.put("nombre", libro.getNombreLibro());
			 json.put("existencias", libro.getExistencias());
			 json.put("precio", libro.getPrecio());
			 json.put("editorial", libro.getEditorial().getNombreEditorial());
			 json.put("autor",libro.getAutor().getNombreAutor());
			 json.put("genero", libro.getGenero().getNombreGenero());
			 json.put("descripcion", libro.getDescripcion());
			 out.print(json);
		 } catch (SQLException ex) {
			 Logger.getLogger(LibrosController.class.getName()).log(Level.SEVERE, null, ex);
		 } catch (IOException ex) {
			 Logger.getLogger(LibrosController.class.getName()).log(Level.SEVERE, null, ex);
		 }
	}
	
}
