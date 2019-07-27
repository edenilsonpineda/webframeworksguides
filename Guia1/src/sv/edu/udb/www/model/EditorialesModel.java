package sv.edu.udb.www.model;

import java.sql.SQLException; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
import sv.edu.udb.www.beans.Editorial;

public class EditorialesModel extends Conexion {
	
	public List<Editorial> listaEditoriales() throws SQLException{
		try {
			List<Editorial> lista = new ArrayList<>();
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
		}//cierre del catch
	}//cierre del public list
}//cierre de la clase principal
