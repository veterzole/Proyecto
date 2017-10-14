package mx.edu.uacm.administrativo.service.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import mx.edu.uacm.administrativo.Application;
import mx.edu.uacm.administrativo.domain.Calendario;
import mx.edu.uacm.administrativo.domain.Reservacion;
import mx.edu.uacm.administrativo.domain.ReservacionEquipo;
import mx.edu.uacm.administrativo.domain.Sitio;
import mx.edu.uacm.administrativo.domain.repository.CalendarioRepository;
import mx.edu.uacm.administrativo.domain.repository.ReservacionEquipoRepository;
import mx.edu.uacm.administrativo.domain.repository.ReservacionRepository;
import mx.edu.uacm.administrativo.domain.repository.SitioRepository;
import mx.edu.uacm.administrativo.service.ReservacionService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class ReservacionServiceTest {
	
	@Autowired
	ReservacionService service;

	@Autowired
	MessageSource messages;
	
	@Autowired
	ReservacionRepository reservacionRepositiory;
	
	@Autowired
	SitioRepository sitioRepositiory;
	
	@Autowired
	CalendarioRepository calendarioRepotory;
	

	@Autowired
	ReservacionEquipoRepository rEquipoRepotory;
	
	
	
	//*****************************pruebas de sitio******************************
	
	@Test
	public void pruebaTipoCapacidad(){
		Sitio sitio = new Sitio();
		sitio.setTipo("OTROS");
		sitio.setCapacidad(23);
		String cadena = service.agregarSitio(sitio);
		String cadena1  = messages.getMessage("message.sitio.capacidad", new Object[]{}, LocaleContextHolder.getLocale());
		Assert.assertEquals(cadena,cadena1);
	}
	/*
	@Test
	public void pruebaTipoCapacidad2(){
		Sitio sitio = new Sitio();
		sitio.setTipo("EDIFICIO");
		sitio.setCapacidad(45660);
		String cadena =service.agregarSitio(sitio);
		String cadena1  = messages.getMessage("message.sitio.capacidad", new Object[]{}, LocaleContextHolder.getLocale());
		Assert.assertEquals(cadena,cadena1);
	}
	
	@Test
	public void pruebaDescripcionTamano(){
		Sitio sitio = new Sitio();
		sitio.setTipo("EDIFICIO");
		sitio.setCapacidad(50);
		sitio.setDescripcion("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"
				+ "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"
				+ "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"
				+ "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"
				+ "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"
				+ "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"
				+ "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"
				+ "22222222222222222222222222222222222222222222222222222222222222222"
				+ "22222222222222222222222222222222222222222222222222222222222222222");
		String cadena =service.agregarSitio(sitio);
		String cadena1  = messages.getMessage("message.sitio.max.descripcion", new Object[]{}, LocaleContextHolder.getLocale());
		Assert.assertEquals(cadena,cadena1);
	}
	
	@Test
	public void pruebaDescripcionSoloLetrasYNumeros(){
		Sitio sitio = new Sitio();
		sitio.setTipo("EDIFICIO");
		sitio.setCapacidad(50);
		sitio.setDescripcion("@@@@@@@@@@@@@");
		String cadena =service.agregarSitio(sitio);
		String cadena1  = messages.getMessage("message.sitio.letras.descripcion", new Object[]{}, LocaleContextHolder.getLocale());
		Assert.assertEquals(cadena,cadena1);
	}
	
	@Test
	public void pruebaNombreOTro(){
		Sitio sitio = new Sitio();
		sitio.setTipo("OTROS");
		sitio.setCapacidad(50);
		sitio.setDescripcion("Entre el estacinamiento y los cubos de los profes");
		sitio.setNombre("1234@@@");
		String cadena =service.agregarSitio(sitio);
		String cadena1  = messages.getMessage("message.sitio.otro.nombre", new Object[]{}, LocaleContextHolder.getLocale());
		Assert.assertEquals(cadena,cadena1);
	}
	@Test
	public void pruebaNombreEdificio(){
		Sitio sitio = new Sitio();
		sitio.setTipo("EDIFICIO");
		sitio.setCapacidad(50);
		sitio.setDescripcion("Audio visual");
		sitio.setNombre("A123123");
		String cadena =service.agregarSitio(sitio);
		String cadena1  = messages.getMessage("message.sitio.aula.nombre", new Object[]{}, LocaleContextHolder.getLocale());
		Assert.assertEquals(cadena,cadena1);
	}
	
	@Test
	public void pruebaCorrecta(){
		Sitio sitio = new Sitio();
		sitio.setTipo("EDIFICIO");
		sitio.setCapacidad(180);
		sitio.setDescripcion("Audio visual");
		sitio.setNombre("A116");
		String cadena =service.agregarSitio(sitio);
		Assert.assertEquals(cadena,"");
	}
	
	@Test
	public void pruebaCorrecta2(){
		Sitio sitio = new Sitio();
		sitio.setTipo("OTROS");
		sitio.setCapacidad(100);
		sitio.setDescripcion("Audio visual");
		sitio.setNombre("AGORA");
		String cadena =service.agregarSitio(sitio);
		Assert.assertEquals(cadena,"");
	}
	//***************************Pruebas de Reservacion*************************************
	@Test
	public void pruebaMinNombre(){
		Reservacion reservacion = new Reservacion();
		ReservacionSitio reservacionSitio = new ReservacionSitio();
		List<ReservacionEquipo> reservacionEquipos = new ArrayList<ReservacionEquipo>();
		Calendario calendarios = new Calendario();
		reservacion.setNombre("Jun");
		reservacion.setnPersonas(45);
		reservacion.setArea("Colegio ciencia y teconologia"); 
		reservacion.setCorreo("juan@gmail.com");
		reservacion.setEvento("ADMINISTRACION");
		String cadena = service.agregarReservacion(reservacion,calendarios,reservacionEquipos,reservacionSitio);
		String cadena1 = messages.getMessage("message.reservacion.min.nombre", new Object[]{}, LocaleContextHolder.getLocale());
		Assert.assertEquals(cadena, cadena1);
	}
	@Test
	public void pruebaMaxNombre(){
		Reservacion reservacion= new Reservacion();
		ReservacionSitio reservacionSitio = new ReservacionSitio();
		List<ReservacionEquipo> reservacionEquipos = new ArrayList<ReservacionEquipo>();
		Calendario calendarios = new Calendario();
		reservacion.setNombre("Juannnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"
				+ "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"
				+ "Danielllllllllllllllllllllllllllllllllllllllllllllllllllllllllll"
				+ "Hidalgoooooooooooooooooooooooooooooooooooooooooooooooooooooooooo"
				+ "Cruzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz"
				+ "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz"
				+ "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
		reservacion.setnPersonas(45);
		reservacion.setArea("Colegio ciencia y teconologia");
		reservacion.setCorreo("juan@gmail.com");
		reservacion.setEvento("ADMINISTRACION");
		String cadena = service.agregarReservacion(reservacion,calendarios,reservacionEquipos,reservacionSitio);
		String cadena1 = messages.getMessage("message.reservacion.max.nombre",new Object[]{}, LocaleContextHolder.getLocale());
		Assert.assertEquals(cadena, cadena1);
	}
	@Test
	public void pruebaSoloLetrasNombre(){
		Reservacion reservacion= new Reservacion();
		ReservacionSitio reservacionSitio = new ReservacionSitio();
		List<ReservacionEquipo> reservacionEquipos = new ArrayList<ReservacionEquipo>();
		Calendario calendarios = new Calendario();
		reservacion.setNombre("Juan D@niel H1dalgo Cru2");
		reservacion.setnPersonas(45);
		reservacion.setArea("Colegio ciencia y teconologia");
		reservacion.setCorreo("juan@gmail.com");
		reservacion.setEvento("ADMINISTRACION");
		String cadena = service.agregarReservacion(reservacion,calendarios,reservacionEquipos,reservacionSitio);
		String cadena1 = messages.getMessage("message.reservacion.letras.nombre", new Object[]{}, LocaleContextHolder.getLocale());
		Assert.assertEquals(cadena, cadena1);
	}
	@Test
	public void pruebaNumMinAsistentes(){
		Reservacion reservacion= new Reservacion();
		ReservacionSitio reservacionSitio = new ReservacionSitio();
		List<ReservacionEquipo> reservacionEquipos = new ArrayList<ReservacionEquipo>();
		Calendario calendarios = new Calendario();
		reservacion.setNombre("Juan Daniel Hidalgo Cruz");
		reservacion.setnPersonas(9);
		reservacion.setArea("Colegio ciencia y teconologia");
		reservacion.setCorreo("juan@gmail.com");
		reservacion.setEvento("ADMINISTRACION");
		String cadena = service.agregarReservacion(reservacion,calendarios,reservacionEquipos,reservacionSitio);
		String cadena1 = messages.getMessage("message.reservacion.min.asistentes", new Object[]{}, LocaleContextHolder.getLocale());
		Assert.assertEquals(cadena, cadena1);
	}
	@Test
	public void pruebaNumAsistentesMax(){
		Reservacion reservacion= new Reservacion();
		ReservacionSitio reservacionSitio = new ReservacionSitio();
		List<ReservacionEquipo> reservacionEquipos = new ArrayList<ReservacionEquipo>();
		Calendario calendarios = new Calendario();
		reservacion.setNombre("Juan Daniel Hidalgo Cruz");
		reservacion.setnPersonas(1001);
		reservacion.setArea("Colegio ciencia y teconologia");
		reservacion.setCorreo("juan@gmail.com");
		reservacion.setEvento("ADMINISTRACION");
		String cadena = service.agregarReservacion(reservacion,calendarios,reservacionEquipos,reservacionSitio);
		String cadena1 = messages.getMessage("message.reservacion.max.asistentes", new Object[]{}, LocaleContextHolder.getLocale());
		Assert.assertEquals(cadena, cadena1);
	}
	@Test
	public void pruebaAreaMin(){
		Reservacion reservacion= new Reservacion();
		ReservacionSitio reservacionSitio = new ReservacionSitio();
		List<ReservacionEquipo> reservacionEquipos = new ArrayList<ReservacionEquipo>();
		Calendario calendarios = new Calendario();
		reservacion.setNombre("Juan Daniel Hidalgo Cruz");
		reservacion.setnPersonas(201);
		reservacion.setArea("Colegio");
		reservacion.setCorreo("juan@gmail.com");
		reservacion.setEvento("ADMINISTRACION");
		String cadena = service.agregarReservacion(reservacion,calendarios,reservacionEquipos,reservacionSitio);
		String cadena1 = messages.getMessage("message.reservacion.min.area", new Object[]{}, LocaleContextHolder.getLocale());
		Assert.assertEquals(cadena, cadena1);
	}
	@Test 
	public void pruebaAreaMax(){
		Reservacion reservacion= new Reservacion();
		ReservacionSitio reservacionSitio = new ReservacionSitio();
		List<ReservacionEquipo> reservacionEquipos = new ArrayList<ReservacionEquipo>();
		Calendario calendarios = new Calendario();
		reservacion.setNombre("Juan Daniel Hidalgo Cruz");
		reservacion.setnPersonas(201);
		reservacion.setArea("Colegioooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo"
				+ "de Cienciassssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
				+ "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy"
				+ "Humanidadessssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
				+ "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
				+ "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
		reservacion.setCorreo("juan@gmail.com");
		reservacion.setEvento("ADMINISTRACION");
		String cadena = service.agregarReservacion(reservacion,calendarios,reservacionEquipos,reservacionSitio);
		String cadena1 = messages.getMessage("message.reservacion.max.area", new Object[]{}, LocaleContextHolder.getLocale());
		Assert.assertEquals(cadena, cadena1);
	}
	@Test
	public void pruebaAreaSoloLetras(){
		Reservacion reservacion= new Reservacion();
		ReservacionSitio reservacionSitio = new ReservacionSitio();
		List<ReservacionEquipo> reservacionEquipos = new ArrayList<ReservacionEquipo>();
		Calendario calendarios = new Calendario();
		reservacion.setNombre("Juan Daniel Hidalgo Cruz");
		reservacion.setnPersonas(201);
		reservacion.setArea("Co1egio cienci@ y teconologia");
		reservacion.setCorreo("juan@gmail.com");
		reservacion.setEvento("ADMINISTRACION");
		String cadena = service.agregarReservacion(reservacion,calendarios,reservacionEquipos,reservacionSitio);
		String cadena1 = messages.getMessage("message.reservacion.letras.area", new Object[]{}, LocaleContextHolder.getLocale());
		Assert.assertEquals(cadena, cadena1);
	}
	
	@Test
	public void pruebaEmail() throws ParseException{
		
		Reservacion reservacion= new Reservacion();
		ReservacionSitio reservacionSitio = new ReservacionSitio();
		List<ReservacionEquipo> reservacionEquipos = new ArrayList<ReservacionEquipo>();
		Calendario calendarios = new Calendario();

		reservacion.setNombre("Juan Daniel Hidalgo Cruz");
		reservacion.setnPersonas(201);
		reservacion.setArea("Colegio de Ciencia y Teconologia");
		reservacion.setCorreo("juangmail.com");
		reservacion.setEvento("ADMINISTRACION");
		String cadena = service.agregarReservacion(reservacion,calendarios,reservacionEquipos,reservacionSitio);
		String cadena1 = messages.getMessage("message.reservacion.email", null, LocaleContextHolder.getLocale());
		Assert.assertEquals(cadena, cadena1);
	} 
	@Test
	public void pruebaEventoPredefinido1() throws ParseException{
		Reservacion reservacion = new Reservacion();
		ReservacionSitio reservacionSitio = new ReservacionSitio();
		reservacionSitio.setAula("A201");
		List<ReservacionEquipo> reservacionEquipos = new ArrayList<ReservacionEquipo>();
		ReservacionEquipo equipo1 = new ReservacionEquipo("TV");
		
		ReservacionEquipo equipo2 = new ReservacionEquipo("DVD");

		
		reservacionEquipos.add(equipo1);
		reservacionEquipos.add(equipo2);
		
		Calendario  calendarios = new Calendario();
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
		calendarios.setFecha(actual);
		calendarios.setHoraInicio(fecFormatoTime);
		calendarios.setHoraFin(fecFormatoTime);
		
		reservacion.setNombre("Juan Daniel Hidalgo Cruz");
		reservacion.setnPersonas(201);
		reservacion.setArea("Colegio de Ciencia y Teconologia");
		reservacion.setCorreo("juan@gmail.com");
		reservacion.setEvento("CLASES");
		String cadena = service.agregarReservacion(reservacion,calendarios,reservacionEquipos,reservacionSitio);
		Assert.assertEquals(cadena, "");
	}
	@Test
	public void pruebaEventoPredefinido2() throws ParseException{
		Reservacion reservacion = new Reservacion();
		ReservacionSitio reservacionSitio = new ReservacionSitio();
		reservacionSitio.setAula("A201");
		List<ReservacionEquipo> reservacionEquipos = new ArrayList<ReservacionEquipo>();
		ReservacionEquipo equipo1 = new ReservacionEquipo("TV");
		
		ReservacionEquipo equipo2 = new ReservacionEquipo("DVD");

		
		reservacionEquipos.add(equipo1);
		reservacionEquipos.add(equipo2);
		
		Calendario  calendarios = new Calendario();
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
		calendarios.setFecha(actual);
		calendarios.setHoraInicio(fecFormatoTime);
		calendarios.setHoraFin(fecFormatoTime);
		
		reservacion.setNombre("Juan Daniel Hidalgo Cruz");
		reservacion.setnPersonas(201);
		reservacion.setArea("Colegio de Ciencia y Teconologia");
		reservacion.setCorreo("juan@gmail.com");
		reservacion.setEvento("TITULACIONES");
		String cadena = service.agregarReservacion(reservacion,calendarios,reservacionEquipos,reservacionSitio);
		Assert.assertEquals(cadena, "");
	}
	@Test
	public void pruebaEventoPredefinido3() throws ParseException{
		Reservacion reservacion = new Reservacion();
		ReservacionSitio reservacionSitio = new ReservacionSitio();
		reservacionSitio.setAula("A201");
		List<ReservacionEquipo> reservacionEquipos = new ArrayList<ReservacionEquipo>();
		ReservacionEquipo equipo1 = new ReservacionEquipo("TV");
		
		ReservacionEquipo equipo2 = new ReservacionEquipo("DVD");

		
		reservacionEquipos.add(equipo1);
		reservacionEquipos.add(equipo2);
		
		Calendario  calendarios = new Calendario();
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
		calendarios.setFecha(actual);
		calendarios.setHoraInicio(fecFormatoTime);
		calendarios.setHoraFin(fecFormatoTime);
		
		reservacion.setNombre("Juan Daniel Hidalgo Cruz");
		reservacion.setnPersonas(201);
		reservacion.setArea("Colegio de Ciencia y Teconologia");
		reservacion.setCorreo("juan@gmail.com");
		reservacion.setEvento("ACTIVIDADES ACADEMICAS");
		String cadena = service.agregarReservacion(reservacion,calendarios,reservacionEquipos,reservacionSitio);
		Assert.assertEquals(cadena, "");
	}
	@Test
	public void pruebaEventoPredefinido4() throws ParseException{
		Reservacion reservacion = new Reservacion();
		ReservacionSitio reservacionSitio = new ReservacionSitio();
		reservacionSitio.setAula("A201");
		List<ReservacionEquipo> reservacionEquipos = new ArrayList<ReservacionEquipo>();
		ReservacionEquipo equipo1 = new ReservacionEquipo("TV");
		
		ReservacionEquipo equipo2 = new ReservacionEquipo("DVD");

		
		reservacionEquipos.add(equipo1);
		reservacionEquipos.add(equipo2);
		
		Calendario  calendarios = new Calendario();
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
		calendarios.setFecha(actual);
		calendarios.setHoraInicio(fecFormatoTime);
		calendarios.setHoraFin(fecFormatoTime);
		calendarios.setFecha(actual);
		calendarios.setHoraInicio(fecFormatoTime);
		calendarios.setHoraFin(fecFormatoTime);
		reservacion.setNombre("Juan Daniel Hidalgo Cruz");
		reservacion.setnPersonas(201);
		reservacion.setArea("Colegio de Ciencia y Teconologia");
		reservacion.setCorreo("juan@gmail.com");
		reservacion.setEvento("ORGANOS DE GOBIERNO");
		String cadena = service.agregarReservacion(reservacion,calendarios,reservacionEquipos,reservacionSitio);
		Assert.assertEquals(cadena, "");
	}
	@Test
	public void pruebaEventoPredefinido5() throws ParseException{
		Reservacion reservacion = new Reservacion();
		ReservacionSitio reservacionSitio = new ReservacionSitio();
		reservacionSitio.setAula("A201");
		List<ReservacionEquipo> reservacionEquipos = new ArrayList<ReservacionEquipo>();
		ReservacionEquipo equipo1 = new ReservacionEquipo("TV");
		
		ReservacionEquipo equipo2 = new ReservacionEquipo("DVD");

		
		reservacionEquipos.add(equipo1);
		reservacionEquipos.add(equipo2);
		
		Calendario  calendarios = new Calendario();
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
		calendarios.setFecha(actual);
		calendarios.setHoraInicio(fecFormatoTime);
		calendarios.setHoraFin(fecFormatoTime);
		
		
		calendarios.setFecha(actual);
		calendarios.setHoraInicio(fecFormatoTime);
		calendarios.setHoraFin(fecFormatoTime);
		reservacion.setNombre("Juan Daniel Hidalgo Cruz");
		reservacion.setnPersonas(201);
		reservacion.setArea("Colegio de Ciencia y Teconologia");
		reservacion.setCorreo("juan@gmail.com");
		reservacion.setEvento("DIFUCION CULTURAL");
		String cadena = service.agregarReservacion(reservacion,calendarios,reservacionEquipos,reservacionSitio);
		Assert.assertEquals(cadena, "");
	}
	@Test
	public void pruebaEventoPredefinido6() throws ParseException{
		Reservacion reservacion = new Reservacion();
		ReservacionSitio reservacionSitio = new ReservacionSitio();
		reservacionSitio.setAula("A201");
		List<ReservacionEquipo> reservacionEquipos = new ArrayList<ReservacionEquipo>();
		ReservacionEquipo equipo1 = new ReservacionEquipo("TV");
		
		ReservacionEquipo equipo2 = new ReservacionEquipo("DVD");

		
		reservacionEquipos.add(equipo1);
		reservacionEquipos.add(equipo2);
		
		Calendario  calendarios = new Calendario();
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
		calendarios.setFecha(actual);
		calendarios.setHoraInicio(fecFormatoTime);
		calendarios.setHoraFin(fecFormatoTime);
		
		calendarios.setFecha(actual);
		calendarios.setHoraInicio(fecFormatoTime);
		calendarios.setHoraFin(fecFormatoTime);
		reservacion.setNombre("Juan Daniel Hidalgo Cruz");
		reservacion.setnPersonas(201);
		reservacion.setArea("Colegio de Ciencia y Teconologia");
		reservacion.setCorreo("juan@gmail.com");
		reservacion.setEvento("C. SERVICIOS ESTUDIANTILES");
		String cadena = service.agregarReservacion(reservacion,calendarios,reservacionEquipos,reservacionSitio);
		Assert.assertEquals(cadena, "");
	}
	@Test
	public void pruebaEventoPredefinido7() throws ParseException{
		Reservacion reservacion = new Reservacion();
		ReservacionSitio reservacionSitio = new ReservacionSitio();
		reservacionSitio.setAula("A201");
		List<ReservacionEquipo> reservacionEquipos = new ArrayList<ReservacionEquipo>();
		ReservacionEquipo equipo1 = new ReservacionEquipo("TV");
		
		ReservacionEquipo equipo2 = new ReservacionEquipo("DVD");

		
		reservacionEquipos.add(equipo1);
		reservacionEquipos.add(equipo2);
		
		Calendario  calendarios = new Calendario();
		String date="2017-11-12";
		
		String horString = "10:30:00";
		java.sql.Time fecFormatoTime = null;
		try {
			SimpleDateFormat sdf = new java.text.SimpleDateFormat("hh:mm:ss", new Locale("es", "ES"));
		    fecFormatoTime = new java.sql.Time(sdf.parse(horString).getTime());
		} catch (Exception ex) {				     
		
		}
		Date  actual1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		java.sql.Date actual = new java.sql.Date(actual1.getTime());
		calendarios.setFecha(actual);
		calendarios.setHoraInicio(fecFormatoTime);
		calendarios.setHoraFin(fecFormatoTime);
		
		
		calendarios.setFecha(actual);
		calendarios.setHoraInicio(fecFormatoTime);
		calendarios.setHoraFin(fecFormatoTime);
		reservacion.setNombre("job hernandez");
		reservacion.setnPersonas(201);
		reservacion.setArea("Colegio de Ciencia y Teconologia");
		reservacion.setCorreo("an@gmail.com");
		reservacion.setEvento("ADMINISTRACION");
		String cadena = service.agregarReservacion(reservacion,calendarios,reservacionEquipos,reservacionSitio);
		Assert.assertEquals(cadena, "");
	}
	@Test
	public void pruebaEventoPredefinido8() throws ParseException{
		Reservacion reservacion = new Reservacion();
		ReservacionSitio reservacionSitio = new ReservacionSitio();
		reservacionSitio.setAula("A201");
		List<ReservacionEquipo> reservacionEquipos = new ArrayList<ReservacionEquipo>();
		ReservacionEquipo equipo1 = new ReservacionEquipo("TV");
		
		ReservacionEquipo equipo2 = new ReservacionEquipo("DVD");

		
		reservacionEquipos.add(equipo1);
		reservacionEquipos.add(equipo2);
		
		Calendario  calendarios = new Calendario();
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
		calendarios.setFecha(actual);
		calendarios.setHoraInicio(fecFormatoTime);
		calendarios.setHoraFin(fecFormatoTime);
		
		Date  actual3 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		java.sql.Date actual2 = new java.sql.Date(actual3.getTime());
		calendarios.setFecha(actual2);
		calendarios.setHoraInicio(fecFormatoTime);
		calendarios.setHoraFin(fecFormatoTime);
		reservacion.setNombre("Juan Daniel Hidalgo Cruz");
		reservacion.setnPersonas(201);
		reservacion.setArea("Colegio de Ciencia y Teconologia");
		reservacion.setCorreo("juan@gmail.com");
		reservacion.setEvento("SUTUACM");
		String cadena = service.agregarReservacion(reservacion,calendarios,reservacionEquipos,reservacionSitio);
		Assert.assertEquals(cadena, "");
	}
	@Test
	public void pruebaEventoPredefinido9() throws Exception {
		Reservacion reservacion = new Reservacion();
		ReservacionSitio reservacionSitio = new ReservacionSitio();
		reservacionSitio.setAula("A201");
		List<ReservacionEquipo> reservacionEquipos = new ArrayList<ReservacionEquipo>();
		ReservacionEquipo equipo1 = new ReservacionEquipo("TV");
		
		ReservacionEquipo equipo2 = new ReservacionEquipo("DVD");

		
		reservacionEquipos.add(equipo1);
		reservacionEquipos.add(equipo2);
		
		Calendario  calendarios = new Calendario();
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
		calendarios.setFecha(actual);
		calendarios.setHoraInicio(fecFormatoTime);
		calendarios.setHoraFin(fecFormatoTime);
		
		reservacion.setNombre("Juan Daniel Hidalgo Cruz");
		reservacion.setnPersonas(201);
		reservacion.setArea("Colegio de Ciencia y Teconologia");
		reservacion.setCorreo("juan@gmail.com");
		reservacion.setEvento("EXTERNOS");
		String cadena = service.agregarReservacion(reservacion,calendarios,reservacionEquipos,reservacionSitio);
		Assert.assertEquals(cadena, "");
	}
	
	
	*/

}
