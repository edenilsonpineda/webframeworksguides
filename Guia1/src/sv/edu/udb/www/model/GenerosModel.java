package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Genero;

public class GenerosModel extends Conexion {
	
	public List<Genero> listarGeneros() throws SQLException{
		try {
			List<Genero> lista = new ArrayList<Genero>();
			String sql = "CALL sp_listarGeneros()";
			this.conectar();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while(rs.next()) {
				Genero genero = new Genero();
				genero.setIdGenero(rs.getInt("id_genero"));
				genero.setNombreGenero(rs.getString("nombre_genero"));
				genero.setDescripcion(rs.getString("descripcion"));
				lista.add(genero);
			}
			this.desconectar();
			return lista;
		}catch(SQLException ex) {
			Logger.getLogger(GenerosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}
	
	public int insertarGeneros(Genero genero) throws SQLException{
		try {
			int filasAfectadas = 0;
			String sql = "CALL sp_insertarGenero(?,?)";
			this.conectar();
			
			cs = conexion.prepareCall(sql);
			cs.setString(1,genero.getNombreGenero());
			cs.setString(2, genero.getDescripcion());
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		}catch(SQLException ex) {
			Logger.getLogger(GenerosModel.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
	}
	
	
	public Genero obtenerGenero(String id) throws SQLException{
		try {
			String sql = "CALL sp_obtenerGenero(?)";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, id);
			rs = cs.executeQuery();
			if(rs.next()) {
				Genero genero = new Genero();
				genero.setIdGenero(rs.getInt("id_genero"));
				genero.setNombreGenero(rs.getString("nombre_genero"));
				genero.setDescripcion(rs.getString("descripcion"));
				this.desconectar();
				return genero;
			}
			this.desconectar();
			return null;
		}catch(SQLException ex) {
			Logger.getLogger(GenerosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}
	
	
	public int modificarGenero(Genero genero) throws SQLException{
		try {
			int filasAfectadas = 0;
			String sql = "CALL sp_modificarGenero(?,?,?)";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, genero.getNombreGenero());
			cs.setString(2, genero.getDescripcion());
			cs.setInt(3, genero.getIdGenero());
			
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		}catch(SQLException ex) {
			Logger.getLogger(GenerosModel.class.getName()).log(Level.SEVERE, null, ex);;
			this.desconectar();
			return 0;
		}
	}
	
	
	public int eliminarGenero(String id) throws SQLException{
		try {
			int filasAfectadas = 0;
			String sql = "CALL sp_eliminarGenero(?)";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, id);
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
			
		}catch(SQLException ex) {
			Logger.getLogger(GenerosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}
	
	public int totalGeneros() throws SQLException{
		try {
			int total = 0;
			String sql = "SELECT COUNT(id_genero) as total FROM generos";
			this.conectar();
			st = conexion.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				total = rs.getInt("total");
			}
			this.desconectar();
			return total;
		}catch(SQLException ex) {
			Logger.getLogger(GenerosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}
}
