package sv.edu.udb.www.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import sv.edu.udb.www.beans.Autor;
import sv.edu.udb.www.model.AutoresModel;

public class AutorImpl implements AutorService {
	private AutoresModel autorModel = new AutoresModel();
	private int result = 0;
	
	@Override
	public List<Autor> listarAutores() {
		
		List<Autor> lista = new ArrayList<>();
		
		try {
			lista = autorModel.listarAutores();
		} catch (SQLException e) {
			Logger.getLogger(AutoresModel.class.getName()).log(Level.SEVERE, null, e);
		}
		return lista;
	}

	@Override
	public int insertarAutor(Autor autor) {
		try {
			result = this.autorModel.insertarAutor(autor);
		} catch (SQLException e) {
			Logger.getLogger(AutoresModel.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return result;
	}

	@Override
	public int deleteAutor(String codigo) {
		try {
			result = this.autorModel.eliminarAutor(codigo);
		} catch (Exception e) {
			Logger.getLogger(AutoresModel.class.getName()).log(Level.SEVERE, null, e);
		}
		return result;
	}

	@Override
	public int modificarAutor(Autor autor) {
		try {
			result = this.autorModel.modificarAutor(autor);
		} catch (Exception e) {
			Logger.getLogger(AutoresModel.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return result;
	}

	@Override
	public Autor obtenerAutor(String codigo) {
		Autor autor = new Autor();
		try {
			autor = this.autorModel.obtenerAutor(codigo);
		} catch (Exception e) {
			Logger.getLogger(AutoresModel.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return autor;
	}

	@Override
	public int totalAutores() {
		try {
			result = this.autorModel.totalAutores();		
		} catch (Exception e) {
			Logger.getLogger(AutoresModel.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return result;
	}
	
}
