package mx.edu.uacm.administrativo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import mx.edu.uacm.administrativo.domain.Calendario;
import mx.edu.uacm.administrativo.domain.Reservacion;
import mx.edu.uacm.administrativo.domain.ReservacionEquipo;
import mx.edu.uacm.administrativo.domain.Sitio;
import mx.edu.uacm.administrativo.domain.repository.CalendarioRepository;
import mx.edu.uacm.administrativo.domain.repository.ReservacionRepository;
import mx.edu.uacm.administrativo.domain.repository.SitioRepository;
import mx.edu.uacm.administrativo.service.ReservacionService;

@Service
public class ReservacionServiceImp implements ReservacionService {
	
	/*validacion de Sitio*/
	private static final String TIPO_EDIFICIO = "EDIFICIO";
	private static final String TIPO_OTROS = "OTROS";
	private static final int MAX_DESCRIPCION_SITIO=200;
	
	/*validacion de Reservacion*/
	private static final int MAX_NOMBRE=70;
	private static final int MIN_NOMBRE=10;
	private static final int MAX_AREA=70;
	private static final int MAX_ASISTENTES=1000;//PUEDE CAMBIAR
	private static final int MIN_ASISTENTES=10;
	private static final int MIN_AREA=10;
	private static final String EVENTOS[]={"CLASES","TITULACIONES","ACTIVIDADES ACADEMICAS",
			"ORGANOS DE GOBIERNO","DIFUCION CULTURAL","C. SERVICIOS ESTUDIANTILES","ADMINISTRACION",
			"SUTUACM","EXTERNOS"};

	@Autowired
	ReservacionRepository reservacionRepository;
	
	@Autowired
	CalendarioRepository calendarioRepository;

	@Autowired
	SitioRepository sitioRepository;
	
	@Autowired
	MessageSource messages;
	Locale locale = LocaleContextHolder.getLocale();


	public String agregarSitio(Sitio sitio){
		if(sitio.getCapacidad()<50){
			return messages.getMessage("message.sitio.capacidad", null, locale);
		}
		//validacion de descripcion
		else if(sitio.getDescripcion().length()>MAX_DESCRIPCION_SITIO){
			return messages.getMessage("message.sitio.max.descripcion", null, locale);
		}else if(!sitio.getDescripcion().matches("^[A-Za-z0-9\\s]+$")){///agregar estrpecion regular
			return messages.getMessage("message.sitio.letras.descripcion", null, locale);
		}
		else if(sitio.getTipo().equals(TIPO_EDIFICIO)){
			if(!sitio.getNombre().matches("^[A-Z][1-3]1[0-6]$||^[A-Z]00[1-9]")){
				return  messages.getMessage("message.sitio.aula.nombre", null, locale); 
			}else{
				sitioRepository.save(sitio);
				return "";
			}
		}
		else if(sitio.getTipo().equals(TIPO_OTROS)){
			if(!sitio.getNombre().matches("^[A-Za-z\\s]+$")){//verificar Exprecion regular
				return  messages.getMessage("message.sitio.otro.nombre", null, locale); 
			}
			else{
				sitioRepository.save(sitio);
				return "";
			}
		}
		else {
			return messages.getMessage("message.sitio.tipo", null, locale); 
		}
		
	}

	
	public String agregarReservacion(Reservacion reservacion,Calendario calendario,List<ReservacionEquipo> equipos){
		if(reservacion.getNombre().length()<MIN_NOMBRE){
			return  messages.getMessage("message.reservacion.min.nombre", null, locale); 
		}else if(reservacion.getNombre().length()>MAX_NOMBRE){
			return  messages.getMessage("message.reservacion.max.nombre", null, locale); 	
		}else if(!reservacion.getNombre().matches("^[A-Za-z\\s]+$")){
			return  messages.getMessage("message.reservacion.letras.nombre", null, locale); 	
		}else if(reservacion.getnPersonas()<MIN_ASISTENTES){
			return messages.getMessage("message.reservacion.min.asistentes", null, locale); 
		}else if(reservacion.getnPersonas()>MAX_ASISTENTES){
			return messages.getMessage("message.reservacion.max.asistentes", null, locale);
		}else if(reservacion.getArea().length()<MIN_AREA){
			return messages.getMessage("message.reservacion.min.area", null, locale);
		}else if(reservacion.getArea().length()>MAX_AREA){
			return messages.getMessage("message.reservacion.max.area", null, locale);
		}else if(!reservacion.getArea().matches("^[A-Za-z\\s]+$")){
			return messages.getMessage("message.reservacion.letras.area", null, locale);
		}else if(!reservacion.getCorreo().matches("^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$")){
			return messages.getMessage("message.reservacion.email", null, locale);
		}else {
			boolean bandera = false;
			for(int i = 0;i<EVENTOS.length;i++){
				if(reservacion.getEvento().equals(EVENTOS[i])){
					bandera=true;	
				}
			}
			if(bandera){
				List<Calendario> calendarios = new ArrayList<Calendario>();
				calendarios.add(calendario);
				reservacion.setCalendarios(calendarios);
				reservacion.setReservacionEquipos(equipos);
				calendario.setReservacion(reservacion);
				
				for(int i = 0;i<equipos.size();i++){
					equipos.get(i).setReservacion(reservacion);
				}
				reservacionRepository.save(reservacion);
				return "";
			}else{
				return messages.getMessage("message.reservacion.evento", null, locale);
			}
		}
	}
	
	public List<Sitio> buscarTodosSitio() {
		List<Sitio> lista = (List<Sitio>) sitioRepository.findAll();
		return lista;
	}

	public Sitio buscarSitio(String id) {
		sitioRepository.findOne(id);
		return null;
	}
	
	
}
