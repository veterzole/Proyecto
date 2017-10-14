package mx.edu.uacm.administrativo.domain;





import java.sql.Date;
import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="calendario")
public class Calendario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idCalendario;
	@NotNull
	private Time horaInicio;
	@NotNull
	private Time horaFin;
	@NotNull
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="id_reservacion")
	private Reservacion reservacion;
	

	public long getIdCalendario() {
		return idCalendario;
	}

	public void setIdCalendario(long idCalendario) {
		this.idCalendario = idCalendario;
	}

	public Time getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Time getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Time horaFin) {
		this.horaFin = horaFin;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Reservacion getReservacion() {
		return reservacion;
	}

	public void setReservacion(Reservacion reservacion) {
		this.reservacion = reservacion;
	}
	
	
}
