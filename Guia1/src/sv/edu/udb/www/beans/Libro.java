package sv.edu.udb.www.beans;

public class Libro {
	private String nombreLibro;
	private int existencias;
	private double precio;
	private Autor autor;
	private Editorial editorial;
	private Genero genero;
	private String descripcion;
	private String codigoAutor;
	private String codigoEditorial;
	private int idGenero;

	public Libro(String nombreLibro, int existencias, double precio, Autor autor, Editorial editorial, Genero genero,
			String descripcion, String codigoAutor, String codigoEditorial, int idGenero, String codigoLibro) {
		this.nombreLibro = "";
		this.existencias = 0;
		this.precio = 0;
		this.autor = null;
		this.editorial = null;
		this.genero = genero;
		this.descripcion = "";
		this.codigoAutor = "";
		this.codigoEditorial = "";
		this.idGenero = 0;
		this.codigoLibro = "";
	}

	public String getCodigoLibro() {
		return codigoLibro;
	}

	public void setCodigoLibro(String codigoLibro) {
		this.codigoLibro = codigoLibro;
	}

	public String getNombreLibro() {
		return nombreLibro;
	}

	public void setNombreLibro(String nombreLibro) {
		this.nombreLibro = nombreLibro;
	}

	public int getExistencias() {
		return existencias;
	}

	public void setExistencias(int existencias) {
		this.existencias = existencias;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigoAutor() {
		return codigoAutor;
	}

	public void setCodigoAutor(String codigoAutor) {
		this.codigoAutor = codigoAutor;
	}

	public String getCodigoEditorial() {
		return codigoEditorial;
	}

	public void setCodigoEditorial(String codigoEditorial) {
		this.codigoEditorial = codigoEditorial;
	}

	public int getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}

	private String codigoLibro;

}
