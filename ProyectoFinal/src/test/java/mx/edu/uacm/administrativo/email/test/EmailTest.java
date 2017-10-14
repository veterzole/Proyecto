package mx.edu.uacm.administrativo.email.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import mx.edu.uacm.administrativo.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@AutoConfigureMockMvc
public class EmailTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void provarEmail()throws Exception{
		//mockMvc.perform(post("/enviarCorreo")).andExpect(view().name("home"));
	}
}
