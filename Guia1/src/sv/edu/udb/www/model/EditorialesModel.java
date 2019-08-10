package sv.edu.udb.www.model;

import java.sql.SQLException; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
import sv.edu.udb.www.beans.Editorial;

public class EditorialesModel extends Conexion {
	
	public List<Editorial> listarEditoriales() throws SQLException{
		try {
			List<Editorial> lista = new ArrayList<Editorial>();
			String sql = "SELECT * FROM editoriales ORDER BY nombre_editorial";
			this.conectar();
			st = conexion.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				Editorial editorial = new Editorial();
				editorial.setCodigoEditorial(rs.getString("codigo_editorial"));
				editorial.setNombreEditorial(rs.getString("nombre_editorial"));
				editorial.setContacto(rs.getString("contacto"));
				editorial.setTelefono(rs.getString("telefono"));
				lista.add(editorial);
			}
			this.desconectar();
			return lista;
		}catch(SQLException ex) {
			Logger.getLogger(EditorialesModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}
	
	
	
	public int insertarEditorial(Editorial editorial) throws SQLException{
		try {
			int filasAfectadas = 0;
			String sql = "INSERT INTO editoriales VALUES(?,?,?,?)";
			this.conectar();
			st = conexion.prepareStatement(sql);
			st.setString(1, editorial.getCodigoEditorial());
			st.setString(2, editorial.getNombreEditorial());
			st.setString(3, editorial.getContacto());
			st.setString(4, editorial.getTelefono());
			filasAfectadas = st.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		}catch(SQLException ex) {
			Logger.getLogger(EditorialesModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}
	
	
	public Editorial obtenerEditorial(String codigo) throws SQLException{
		try {
			String sql = "SELECT * FROM editoriales WHERE codigo_editorial=?";
			this.conectar();
			st = conexion.prepareStatement(sql);
			st.setString(1, codigo);
			rs = st.executeQuery();
			if(rs.next()) {
				Editorial editorial = new Editorial();
				editorial.setCodigoEditorial(rs.getString("codigo_editorial"));
				editorial.setNombreEditorial(rs.getString("nombre_editorial"));
				editorial.setContacto(rs.getString("contacto"));
				editorial.setTelefono(rs.getString("telefono"));
				
				this.desconectar();
				return editorial;
			}
			this.desconectar();
			return null;			
		}catch(SQLException ex) {
			Logger.getLogger(EditorialesModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}
	
	
	public int modificarEditorial(Editorial editorial) throws SQLException{
		try {
			int filasAfectadas = 0;
			String sql = "UPDATE editoriales SET nombre_editorial = ?, contacto = ?, telefono = ? WHERE codigo_editorial = ?";
			this.conectar();
			st = conexion.prepareStatement(sql);
			st.setString(1, editorial.getNombreEditorial());
			st.setString(2, editorial.getContacto());
			st.setString(3, editorial.getTelefono());
			st.setString(4, editorial.getCodigoEditorial());
			filasAfectadas = st.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		}catch(SQLException ex) {
			Logger.getLogger(EditorialesModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}
	
	
	public int eliminarEditorial(String codigo) throws SQLException{
		try {
			int filasAfectadas = 0;
			String sql = "DELETE FROM editoriales WHERE codigo_editorial = ?";
			this.conectar();
			st = conexion.prepareStatement(sql);
			st.setString(1, codigo);
			filasAfectadas = st.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		}catch(SQLException ex) {
			Logger.getLogger(EditorialesModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
			
		}
	}
	
	
	public int totalEditoriales() throws SQLException{
		try {
			int total = 0;
			String sql = "SELECT COUNT(codigo_editorial) as totaledit FROM editoriales";
			this.conectar();
			st = conexion.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				total = rs.getInt("totaledit");
			}
			this.desconectar();
			return total;
		}catch(SQLException ex) {
			Logger.getLogger(AutoresModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}
	
	
	
}


