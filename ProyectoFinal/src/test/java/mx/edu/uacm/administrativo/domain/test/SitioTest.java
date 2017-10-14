package mx.edu.uacm.administrativo.domain.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mx.edu.uacm.administrativo.Application;
import mx.edu.uacm.administrativo.domain.Sitio;
import mx.edu.uacm.administrativo.domain.repository.SitioRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class SitioTest {
	
	@Autowired
	SitioRepository sitioRepository;
	
	@Test
	public void pruebaSitio(){
		/*Sitio sitio = new Sitio();
		sitio.setNombre("A206");
		sitio.setCapacidad(180);
		sitio.setTipo("EDIFICIO");
		sitio.setDescripcion("Aula");
		sitioRepository.save(sitio);
		Sitio sitio2 = sitioRepository.findOne(sitio.getNombre());
		Assert.assertEquals(sitio.getNombre(), sitio2.getNombre());
		sitioRepository.deleteAll();*/
	}
}
