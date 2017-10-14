package mx.edu.uacm.administrativo.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="reservacion_equipo")
public class ReservacionEquipo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idRservacionSitio;
	
	@ManyToOne
	@JoinColumn(name="id_reservacion")
	private  Reservacion reservacion;
	
	@NotNull
	private String equipo;
	
	public ReservacionEquipo(String equipo){
		this.equipo=equipo;
	}
	
	public ReservacionEquipo(){
		
	}
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
	public String getEquipo() {
		return equipo;
	}
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

		
}
