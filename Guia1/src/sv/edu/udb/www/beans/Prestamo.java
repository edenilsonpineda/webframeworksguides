package sv.edu.udb.www.beans;

public class Prestamo {
	private int codigo_prestamo;
	private String codigo_libro;
	private String codigo_docente;
	private String fecha_prestamo;
	private String fecha_devolucion;
	private Docente docente;
	private Libro libro;
	
	public Prestamo() {
		this.codigo_prestamo = 0;
		this.codigo_libro = "";
		this.codigo_docente = "";
		this.fecha_prestamo = "";
		this.fecha_devolucion = "";
		this.docente = null;
		this.libro = null;
	}
	
	public Prestamo(int codigo_prestamo) {
		this.codigo_prestamo = codigo_prestamo;
	}
	
	
	public Prestamo(int codigo_prestamo, String codigo_libro, String codigo_docente, String fecha_prestamo,
			String fecha_devolucion, Docente docente, Libro libro) {
		this.codigo_prestamo = codigo_prestamo;
		this.codigo_libro = codigo_libro;
		this.codigo_docente = codigo_docente;
		this.fecha_prestamo = fecha_prestamo;
		this.fecha_devolucion = fecha_devolucion;
		this.docente = docente;
		this.libro = libro;
	}

	public int getCodigo_prestamo() {
		return codigo_prestamo;
	}

	public void setCodigo_prestamo(int codigo_prestamo) {
		this.codigo_prestamo = codigo_prestamo;
	}

	public String getCodigo_libro() {
		return codigo_libro;
	}

	public void setCodigo_libro(String codigo_libro) {
		this.codigo_libro = codigo_libro;
	}

	public String getCodigo_docente() {
		return codigo_docente;
	}

	public void setCodigo_docente(String codigo_docente) {
		this.codigo_docente = codigo_docente;
	}

	public String getFecha_prestamo() {
		return fecha_prestamo;
	}

	public void setFecha_prestamo(String fecha_prestamo) {
		this.fecha_prestamo = fecha_prestamo;
	}

	public String getFecha_devolucion() {
		return fecha_devolucion;
	}

	public void setFecha_devolucion(String fecha_devolucion) {
		this.fecha_devolucion = fecha_devolucion;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	
	
		
}
