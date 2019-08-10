package sv.edu.udb.www.beans;

public class Docente {
	private String codigo_docente;
	private String nombre_docente;
	
	public Docente() {
		this.codigo_docente = "";
		this.nombre_docente = "";
	}
	
	public Docente(String nombreDocente) {
		this.nombre_docente = nombreDocente;
	}
	
	public Docente(String codigo_docente, String nombre_docente) {
		this.codigo_docente = codigo_docente;
		this.nombre_docente = nombre_docente;
	}

	public String getCodigo_docente() {
		return codigo_docente;
	}

	public void setCodigo_docente(String codigo_docente) {
		this.codigo_docente = codigo_docente;
	}

	public String getNombre_docente() {
		return nombre_docente;
	}

	public void setNombre_docente(String nombre_docente) {
		this.nombre_docente = nombre_docente;
	}
}
