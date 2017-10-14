package mx.edu.uacm.administrativo.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mx.edu.uacm.administrativo.domain.Calendario;
import mx.edu.uacm.administrativo.domain.Reservacion;
import mx.edu.uacm.administrativo.domain.ReservacionEquipo;
import mx.edu.uacm.administrativo.domain.ReservacionSitio;
import mx.edu.uacm.administrativo.domain.Sitio;
import mx.edu.uacm.administrativo.service.ReservacionService;

@Controller
@RequestMapping("/")
public class ReservacionController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private JavaMailSender javaMailSender;
	
	@Autowired
	public void setJavaMailSender(JavaMailSender javaMailSender){
		this.javaMailSender= javaMailSender;	
	}
	@Autowired 
	ReservacionService reservacionService;// crear metodo agregar reservacion 
	
	@Autowired
	private Environment env;
	

	@RequestMapping(value="/recibirReservacion", method= RequestMethod.POST)
    public String recibeReservacion(Map<String, Object> model,String nombre,String area,String nPersonas,
    		String correo,String aula,String evento,
    		String horaInicio,String horaFin,String fecha,@RequestParam("equipo") List<String> equipo) throws ParseException{
		Reservacion reservacion = new Reservacion();
		reservacion.setNombre(nombre);
		reservacion.setArea(area);
		reservacion.setCorreo(correo);
		reservacion.setEvento(evento);
		reservacion.setnPersonas(Integer.parseInt(nPersonas));
		
		Sitio sitio = new Sitio();
		sitio.setNombre(aula);
		
		Calendario calendario = new Calendario();
		java.sql.Time fecFormatoTime = null;
		SimpleDateFormat sdf1 = new java.text.SimpleDateFormat("hh:mm", new Locale("es", "ES"));
		fecFormatoTime = new java.sql.Time(sdf1.parse(horaInicio).getTime());
		
		java.sql.Time fecFormatoTime1 = null;
	    SimpleDateFormat sdf = new java.text.SimpleDateFormat("hh:mm", new Locale("es", "ES"));
		fecFormatoTime1 = new java.sql.Time(sdf.parse(horaFin).getTime());
		
		Date  actual1 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
		java.sql.Date actual = new java.sql.Date(actual1.getTime());
		calendario.setFecha(actual);
		calendario.setHoraInicio(fecFormatoTime);
		calendario.setHoraFin(fecFormatoTime1);
		//calendario.setSitio(sitio);
		List<ReservacionEquipo> equipos = new ArrayList<ReservacionEquipo>();
		ReservacionSitio rsitio = new ReservacionSitio();
		rsitio.setAula(aula);
		
		
	
		for(int i = 0;i<equipo.size();i++){
			ReservacionEquipo equipO = new ReservacionEquipo();
			equipO.setEquipo(equipo.get(i));
			equipos.add(equipO);
			equipO.setReservacion(reservacion);
		}
		String cadena = reservacionService.agregarReservacion(reservacion,calendario,equipos);
		log.debug(cadena);
        final SimpleMailMessage email = constructEmailMessage(reservacion.getCorreo());
        javaMailSender.send(email);
        return "redirect:/reservacion";
	}
	
	@RequestMapping(value="/recibirSitio", method= RequestMethod.POST)
	public String recibeSitio(Map<String, Object> model, Sitio sitio){
		reservacionService.agregarSitio(sitio);
		return "redirect:/sitio";
	}
	
	
	
	private final SimpleMailMessage constructEmailMessage(String correo){
		final String recipientAddres = correo;
		final String subject = "Reservacion espacio";
		final String message = "Hola" 
				+"\n Su reservacion fue un exito";

		final SimpleMailMessage email = new SimpleMailMessage();
		
		email.setTo(recipientAddres);
		email.setSubject(subject);
		email.setText(message + "\r\n\n" );
		
		email.setFrom(env.getProperty("support.email"));
		
		return email;
		
	}



	

}
