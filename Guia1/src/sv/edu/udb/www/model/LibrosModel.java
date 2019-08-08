package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Autor;
import sv.edu.udb.www.beans.Editorial;
import sv.edu.udb.www.beans.Genero;
import sv.edu.udb.www.beans.Libro;

public class LibrosModel extends Conexion {
	public List<Libro> listaLibros() throws SQLException{
		try {
			List<Libro> lista = new ArrayList<Libro>();
			
			String sql = "CALL sp_listarLibros()";
			this.conectar();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while(rs.next()) {
				Libro libro = new Libro();
				libro.setCodigoAutor(rs.getString("codigo_libro"));
				libro.setNombreLibro(rs.getString("nombre_libro"));
				libro.setPrecio(rs.getDouble("precio"));
				libro.setExistencias(rs.getInt("existencias"));
				libro.setAutor(new Autor(rs.getString("nombre_autor")));
				libro.setEditorial(new Editorial(rs.getString("nombre_editorial")));
				libro.setGenero(new Genero(rs.getString("nombre_genero")));
				lista.add(libro);
			}
			this.desconectar();
			return lista;
		}catch(SQLException ex) {
			Logger.getLogger(LibrosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}
	
	
	public int insertarLibro(Libro libro) throws SQLException{
		try {
			int filasAfectadas = 0;
			String sql = "CALL sp_insertarLibro(?,?,?,?,?,?,?,?)";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, libro.getCodigoAutor());
			cs.setString(2, libro.getNombreLibro());
			cs.setInt(3, libro.getExistencias());
			cs.setDouble(4, libro.getPrecio());
			cs.setString(5, libro.getCodigoAutor());
			cs.setString(6, libro.getCodigoEditorial());
			cs.setInt(7, libro.getIdGenero());
			cs.setString(8, libro.getDescripcion());
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		}catch(SQLException ex){
			Logger.getLogger(LibrosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}
	
	
	public int eliminarLibro(String codigo) throws SQLException{
		try {
			int filasAfectadas = 0;
			String sql = "CALL sp_eliminarLibro(?)";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, codigo);
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		}catch(SQLException ex) {
			Logger.getLogger(LibrosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}
	
	
	public Libro obtenerLibro(String codigo) throws SQLException{
		try {
			String sql = "CALL sp_ontenerLibro(?)";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, codigo);
			if(rs.next()) {
				Libro libro = new Libro();
				libro.setCodigoLibro(rs.getString("codigo_libro"));
				libro.setNombreLibro(rs.getString("nombre_libro"));
				libro.setPrecio(rs.getDouble("precio"));
				libro.setExistencias(rs.getInt("existencias"));
				libro.setDescripcion(rs.getString("descripcion"));
				libro.setCodigoAutor(rs.getString("codigo_autor"));
				libro.setCodigoEditorial(rs.getString("codigo_editorial"));
				libro.setIdGenero(rs.getInt("id_genero"));
				this.desconectar();
				return libro;
			}
			this.desconectar();
			return null;
		}catch(SQLException ex) {
			Logger.getLogger(LibrosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}
	
	
	public int modificarLibro(Libro libro) throws SQLException{
		try {
			int filasAfectadas = 0;
			String sql = "CALL sp_modificarLibro(?,?,?,?,?,?,?,?)";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, libro.getCodigoLibro());
			cs.setString(2, libro.getNombreLibro());
			cs.setInt(3, libro.getExistencias());
			cs.setDouble(4, libro.getPrecio());
			cs.setString(5, libro.getCodigoAutor());
			cs.setString(5, libro.getCodigoEditorial());
			cs.setInt(7, libro.getIdGenero());
			cs.setString(8, libro.getDescripcion());
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		}catch(SQLException ex) {
			Logger.getLogger(LibrosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}
	
	
	public Libro detalleLibro(String codigo) throws SQLException{
		try {
			String sql = "CALL sp_detalleLibro(?)";
			this.conectar();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			if(rs.next()) {
				Libro libro = new Libro();
				libro.setCodigoLibro(rs.getString("codigo_libro"));
				libro.setNombreLibro(rs.getString("nombre_libro"));
				libro.setPrecio(rs.getDouble("precio"));
				libro.setExistencias(rs.getInt("existencias"));
				libro.setDescripcion(rs.getString("descripcion"));
				libro.setAutor(new Autor(rs.getString("nombre_autor")));
				libro.setEditorial(new Editorial(rs.getString("nombre_editorial")));
				libro.setGenero(new Genero(rs.getString("nombre_genero")));
				this.desconectar();
				return libro;
			}
			this.desconectar();
			return null;
		}catch(SQLException ex) {
			Logger.getLogger(LibrosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}
	
	
	public int totalLibros() throws SQLException{
		try {
			int total = 0;
			String sql = "SELECT COUNT(codigo_libro) as total FROM libros";
			this.conectar();
			st = conexion.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				total = rs.getInt("total");
			}
			this.desconectar();
			return total;
		}catch(SQLException ex) {
			Logger.getLogger(LibrosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}
}
