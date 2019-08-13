package sv.edu.udb.www.services;

import java.util.List;
import sv.edu.udb.www.beans.Autor;

//Interfaz 
public interface AutorService {
	public List<Autor> listarAutores();
	
	public int insertarAutor(Autor autor);
	
	public int deleteAutor(String codigo);
	
	public int modificarAutor(Autor autor);
	
	public Autor obtenerAutor(String codigo);
	
	public int totalAutores();
}
