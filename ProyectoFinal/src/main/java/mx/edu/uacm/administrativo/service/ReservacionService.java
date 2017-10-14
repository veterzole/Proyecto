package mx.edu.uacm.administrativo.service;

import java.util.List;

import mx.edu.uacm.administrativo.domain.Calendario;
import mx.edu.uacm.administrativo.domain.Reservacion;
import mx.edu.uacm.administrativo.domain.ReservacionEquipo;
import mx.edu.uacm.administrativo.domain.Sitio;

public interface ReservacionService {
	
	String agregarSitio(Sitio sitio);
	List <Sitio> buscarTodosSitio();
	Sitio buscarSitio(String id);
	String agregarReservacion(Reservacion reservacion,Calendario calendario,List<ReservacionEquipo> equipos);
}
