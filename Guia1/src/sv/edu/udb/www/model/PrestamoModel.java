package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import sv.edu.udb.www.beans.Docente;
import sv.edu.udb.www.beans.Libro;
import sv.edu.udb.www.beans.Prestamo;

public class PrestamoModel extends Conexion{
	public List<Prestamo> listaPrestamo() throws SQLException{
		try {
			List<Prestamo> lista = new ArrayList<Prestamo>();
			
			String sql = "SELECT * FROM prestamo AS P INNER JOIN libros AS L ON P.codigo_libro = L.codigo_libro INNER JOIN docentes AS D ON P.codigo_docente = D.codigo_docente";
			this.conectar();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while(rs.next()) {
				Prestamo prestamo = new Prestamo();
				prestamo.setCodigo_prestamo(Integer.parseInt(rs.getString("codigo_prestamo")));
				prestamo.setCodigo_libro(rs.getString("nombre_libro"));
				prestamo.setCodigo_docente(rs.getString("nombre_docente"));
				prestamo.setFecha_prestamo(rs.getString("FechaPrestamo"));
				prestamo.setFecha_devolucion(rs.getString("FechaDevolucion"));
				lista.add(prestamo);
			}
			this.desconectar();
			return lista;
		}catch(SQLException ex) {
			Logger.getLogger(PrestamoModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}
	
	
	public int insertarPrestamo(Prestamo prestamo) throws SQLException{
		try {
			int filasAfectadas = 0;
			String sql = "INSERT INTO prestamo (codigo_libro, codigo_docente, FechaPrestamo, FechaDevolucion) VALUES (?,?,?,?)";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, prestamo.getCodigo_libro());
			cs.setString(2, prestamo.getCodigo_docente());
			cs.setString(3, prestamo.getFecha_prestamo());
			cs.setString(4, prestamo.getFecha_devolucion());
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		}catch(SQLException ex){
			Logger.getLogger(PrestamoModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}
	
	
	public int eliminarPrestamo(String codigo) throws SQLException{
		try {
			int filasAfectadas = 0;
			String sql = "DELETE FROM prestamo WHERE codigo_prestamo = ?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, codigo);
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		}catch(SQLException ex) {
			Logger.getLogger(PrestamoModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}
	
	
	public Prestamo obtenerPrestamo(String codigo) throws SQLException{
		try {
			String sql = "SELECT * FROM prestamo AS P INNER JOIN libros AS L ON P.codigo_libro = L.codigo_libro INNER JOIN docentes AS D ON P.codigo_docente = D.codigo_docente WHERE codigo_prestamo = ?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, codigo);
			rs = cs.executeQuery();
			if(rs.next()) {
				Prestamo prestamo = new Prestamo();
				prestamo.setCodigo_prestamo(Integer.parseInt(rs.getString("codigo_prestamo")));
				prestamo.setCodigo_libro(rs.getString("nombre_libro"));
				prestamo.setCodigo_docente(rs.getString("nombre_docente"));
				prestamo.setFecha_prestamo(rs.getString("FechaPrestamo"));
				prestamo.setFecha_devolucion(rs.getString("FechaDevolucion"));
				
				this.desconectar();
				return prestamo;
			}
			this.desconectar();
			return null;
		}catch(SQLException ex) {
			Logger.getLogger(PrestamoModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}
	
	
	public int modificarPrestamo(Prestamo prestamo) throws SQLException{
		try {
			int filasAfectadas = 0;
			String sql = "UPDATE prestamo SET FechaDevolucion = ? WHERE codigo_prestamo = ?";
			this.conectar();
			
			cs = conexion.prepareCall(sql);
			cs.setString(1, prestamo.getFecha_devolucion());
			cs.setInt(2, prestamo.getCodigo_prestamo());
			
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		}catch(SQLException ex) {
			Logger.getLogger(PrestamoModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}
	
	
	public Prestamo detallePrestamo(String codigo) throws SQLException{
		try {
			String sql = "SELECT * FROM prestamo WHERE codigo_prestamo = ?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			if(rs.next()) {
				Prestamo prestamo = new Prestamo();
				prestamo.setCodigo_prestamo(Integer.parseInt(rs.getString("codigo_libro")));
				prestamo.setCodigo_libro(rs.getString("codigo_libro"));
				prestamo.setCodigo_docente(rs.getString("codigo_docente"));
				prestamo.setFecha_prestamo(rs.getString("FechaPrestamo"));
				prestamo.setFecha_devolucion(rs.getString("FechaDevolucion"));

				this.desconectar();
				return prestamo;
			}
			this.desconectar();
			return null;
		}catch(SQLException ex) {
			Logger.getLogger(DocenteModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}
	
	
	public int totalPrestamos() throws SQLException{
		try {
			int total = 0;
			String sql = "SELECT COUNT(codigo_prestamo) as total FROM prestamo";
			this.conectar();
			st = conexion.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				total = rs.getInt("total");
			}
			this.desconectar();
			return total;
		}catch(SQLException ex) {
			Logger.getLogger(PrestamoModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}
}
