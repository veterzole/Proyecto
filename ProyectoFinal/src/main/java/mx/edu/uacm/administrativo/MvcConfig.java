//CONFIGURACION DE LAS VISTAS
package mx.edu.uacm.administrativo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/sitio").setViewName("Sitio");
		registry.addViewController("/reservacion").setViewName("reservacion");
		
	}
}
