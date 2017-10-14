package mx.edu.uacm.administrativo.test.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import mx.edu.uacm.administrativo.Application;
import mx.edu.uacm.administrativo.domain.repository.CalendarioRepository;
import mx.edu.uacm.administrativo.domain.repository.ReservacionEquipoRepository;
import mx.edu.uacm.administrativo.domain.repository.ReservacionRepository;
import mx.edu.uacm.administrativo.domain.repository.SitioRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@AutoConfigureMockMvc
public class ReservacionControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ReservacionRepository reservacionRepositiory;
	
	@Autowired
	SitioRepository sitioRepositiory;
	
	@Autowired
	CalendarioRepository calendarioRepotory;
	
	@Autowired
	ReservacionEquipoRepository rEquipoRepotory;
	

	  @Test
	    public void probarHome() throws Exception{
	        mockMvc.perform(get("/")).andExpect(view().name("home"));
	        
	    }
	  
	  @Test
	    public void probarSitioVista() throws Exception{
	        mockMvc.perform(get("/sitio")).andExpect(view().name("Sitio"));
	        
	    }
	  @Test
	    public void probarReservacionSitio() throws Exception{
	        mockMvc.perform(get("/reservacion")).andExpect(view().name("reservacion"));
	        
	    }
	  
	  @Test
	    public void probarRecibirSitio() throws Exception{
	    	mockMvc.perform(post("/recibirSitio").
	    	param("nombreSitio","A201").
	    	param("capacidad","50").
	    	param("descripcion","").
	    	param("tipo","EDIFICIO")).
			andExpect(redirectedUrl("/sitio"));
	    }
	  
	  @Test
	    public void probarRecibirReservacion() throws Exception{
	    	mockMvc.perform(post("/recibirReservacion").
	    	param("nombre","Job").
	    	param("correo","veter.zole12@gmail.com").
	    	param("area","Colegio de Ciencia y Tecnologia").
	    	param("nPersonas","100").
	    	param("evento","CLASE").
	    	//Para calendario
	    	param("horaInicio","07:00").
	    	param("horaFin","07:30").
	    	param("fecha","2017-11-12").
	    	//Para Equipo
	    	param("equipo","TV").
	    	param("equipo","DVD").
	    	//aula
	    	param("aula","A202")).
			andExpect(redirectedUrl("/reservacion"));
	    	
	    }
}
