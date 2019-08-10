package sv.edu.udb.www.beans;

import java.io.Serializable;

public class Autor implements Serializable {

	private static final long serialVersionUID = -2164580503891534675L;
	private String codigoAutor;
	private String nombreAutor;
	private String nacionalidad;

	public Autor() {
		this.codigoAutor = "";
		this.nombreAutor = "";
		this.nacionalidad = "";
	}

	public Autor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}

	public Autor(String codigoAutor, String nombreAutor, String nacionalidad) {
		this.codigoAutor = codigoAutor;
		this.nombreAutor = nombreAutor;
		this.nacionalidad = nacionalidad;
	}

	public String getCodigoAutor() {
		return codigoAutor;
	}

	public void setCodigoAutor(String codigoAutor) {
		this.codigoAutor = codigoAutor;
	}

	public String getNombreAutor() {
		return nombreAutor;
	}

	public void setNombreAutor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
}
