package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Docente;

public class DocenteModel extends Conexion {
	public List<Docente> listarDocentes() throws SQLException {
		try {
			List<Docente> lista = new ArrayList<Docente>();
			String sql = "SELECT * FROM docentes";
			this.conectar();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while (rs.next()) {
				Docente docente = new Docente();
				docente.setCodigo_docente(rs.getString(1));
				docente.setNombre_docente(rs.getString(2));
				lista.add(docente);
			}
			this.desconectar();
			return lista;
		} catch (SQLException ex) {
			Logger.getLogger(DocenteModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public int insertarDocente(Docente docente) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "INSERT INTO docentes VALUES(?,?)";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, docente.getCodigo_docente());
			cs.setString(2, docente.getNombre_docente());
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(DocenteModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

	public int eliminarDocente(String codigo) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "DELETE FROM docentes WHERE codigo_docente = ?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, codigo);
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(DocenteModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}

	}

	public int modificarDocente(Docente docente) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "UPDATE docentes SET nombre_docente = ? WHERE codigo_docente = ?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, docente.getNombre_docente());
			cs.setString(2, docente.getCodigo_docente());
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(DocenteModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

	public Docente obtenerDocente(String codigo) throws SQLException {
		try {
			String sql = "SELECT * FROM docentes WHERE codigo_docente = ?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, codigo);
			rs = cs.executeQuery();
			if (rs.next()) {
				Docente docente = new Docente();
				docente.setCodigo_docente(rs.getString("codigo_docente"));
				docente.setNombre_docente(rs.getString("nombre_docente"));
				this.desconectar();
				return docente;
			}
			this.desconectar();
			return null;
		} catch (SQLException ex) {
			Logger.getLogger(DocenteModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public int totalAutores() throws SQLException {
		try {
			int totaldo = 0;
			String sql = "SELECT COUNT(codigo_docente) as totaldo FROM docentes";
			this.conectar();
			st = conexion.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				totaldo = rs.getInt("totaldo");
			}
			return totaldo;
		} catch (SQLException ex) {
			Logger.getLogger(DocenteModel.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		} finally {
			this.desconectar();
		}
	}
}
