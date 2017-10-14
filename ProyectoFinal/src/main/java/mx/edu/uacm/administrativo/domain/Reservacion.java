package mx.edu.uacm.administrativo.domain;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name="reservacion")
public class Reservacion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idReservacion;
	@NotNull
	private String nombre;
	@NotNull
	private String correo;
	@NotNull
	private String area;
	@NotNull
	private int nPersonas;
	@NotNull
	private String evento;
	
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="reservacion",cascade={CascadeType.ALL})
	private List<Calendario> calendarios;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="reservacion",cascade={CascadeType.ALL})
	private List<ReservacionEquipo> reservacionEquipos;
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="reservacion",cascade={CascadeType.ALL})
	private ReservacionSitio reservacionSitio;
	
	public long getIdReservacion() {
		return idReservacion;
	}
	public void setIdReservacion(long idReservacion) {
		this.idReservacion = idReservacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	public int getnPersonas() {
		return nPersonas;
	}
	public void setnPersonas(int nPersonas) {
		this.nPersonas = nPersonas;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public List<Calendario> getCalendarios() {
		return calendarios;
	}
	public void setCalendarios(List<Calendario> calendarios) {
		this.calendarios = calendarios;
	}
	
	public List<ReservacionEquipo> getReservacionEquipos() {
		return reservacionEquipos;
	}
	public void setReservacionEquipos(List<ReservacionEquipo> reservacionEquipos) {
		this.reservacionEquipos = reservacionEquipos;
	}

	
}
