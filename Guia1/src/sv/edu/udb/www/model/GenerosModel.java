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
			List<Genero> lista = new ArrayList<>();
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
	
	
	
}
