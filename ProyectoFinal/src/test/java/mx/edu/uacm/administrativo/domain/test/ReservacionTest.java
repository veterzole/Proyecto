package mx.edu.uacm.administrativo.domain.test;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mx.edu.uacm.administrativo.Application;
import mx.edu.uacm.administrativo.domain.Calendario;
import mx.edu.uacm.administrativo.domain.Reservacion;
import mx.edu.uacm.administrativo.domain.ReservacionEquipo;

import mx.edu.uacm.administrativo.domain.repository.CalendarioRepository;
import mx.edu.uacm.administrativo.domain.repository.ReservacionEquipoRepository;
import mx.edu.uacm.administrativo.domain.repository.ReservacionRepository;
import mx.edu.uacm.administrativo.domain.repository.SitioRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class ReservacionTest {
	
	@Autowired
	ReservacionRepository reservacionRepositiory;
	@Autowired
	SitioRepository sitioRepositiory;
	
	@Autowired
	CalendarioRepository calendarioRepotory;
	
	@Autowired
	ReservacionEquipoRepository rEquipoRepotory;
	

	
	@Test
	public void probarReservacion() throws Exception{
		/*Reservacion reservacion = new Reservacion();
		ReservacionSitio reservacionSitio = new ReservacionSitio();
		List<ReservacionEquipo> reservacionEquipos = new ArrayList<ReservacionEquipo>();
		List<Calendario> calendarios = new ArrayList<Calendario>();
		
		ReservacionEquipo equipo1 = new ReservacionEquipo("TV");
		
		ReservacionEquipo equipo2 = new ReservacionEquipo("DVD");

		
		reservacionEquipos.add(equipo1);
		reservacionEquipos.add(equipo2);
		Calendario  calendario = new Calendario();
		String date="1999-11-12";
		
		String horString = "10:28:31";
		java.sql.Time fecFormatoTime = null;
		try {
			SimpleDateFormat sdf = new java.text.SimpleDateFormat("hh:mm:ss", new Locale("es", "ES"));
		    fecFormatoTime = new java.sql.Time(sdf.parse(horString).getTime());
		} catch (Exception ex) {				     
		
		}
		Date  actual1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		java.sql.Date actual = new java.sql.Date(actual1.getTime());
		calendario.setFecha(actual);
		calendario.setHoraInicio(fecFormatoTime);
		calendario.setHoraFin(fecFormatoTime);
		calendarios.add(calendario);
		
		reservacionSitio.setAula("A201");

		//llenando  datos
		reservacion.setArea("Colegio Ciencia y Tecnologia");
		reservacion.setCorreo("job@hotmail.com");
		reservacion.setNombre("job");
		reservacion.setEvento("Titulacion");
		reservacion.setnPersonas(180);
		reservacion.setReservacionSitio(reservacionSitio);
		reservacion.setCalendarios(calendarios);
		reservacion.setReservacionEquipos(reservacionEquipos);
		
		equipo1.setReservacion(reservacion);
		equipo2.setReservacion(reservacion);
		reservacionSitio.setReservacion(reservacion);
		calendario.setReservacion(reservacion);

		reservacionRepositiory.save(reservacion);*/
	}
	

}
