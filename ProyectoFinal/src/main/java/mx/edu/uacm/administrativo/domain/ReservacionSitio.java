package mx.edu.uacm.administrativo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="reservacion_sitio")
public class ReservacionSitio {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idRservacionSitio;
	
	@OneToOne
	@JoinColumn(name="id_reservacion")
	private Reservacion reservacion;
	
	@NotNull
	private String aula;
	
	public Long getIdRservacionSitio() {
		return idRservacionSitio;
	}
	public void setIdRservacionSitio(Long idRservacionSitio) {
		this.idRservacionSitio = idRservacionSitio;
	}
	public Reservacion getReservacion() {
		return reservacion;
	}
	public void setReservacion(Reservacion reservacion) {
		this.reservacion = reservacion;
	}
	public String getAula() {
		return aula;
	}
	public void setAula(String aula) {
		this.aula = aula;
	}
	
	
}
