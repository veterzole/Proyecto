package mx.edu.uacm.administrativo.email;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmailController {
	
	//private final Logger log = Logger.getLogger(this.getClass());
	
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;
	
	
	@Autowired
	public void setJavaMailSender(JavaMailSender javaMailSender){
		this.javaMailSender= javaMailSender;	
	}
	
	@RequestMapping(value="/enviarCorreo", method=RequestMethod.POST)
	public String enviar() throws MailException{
		final SimpleMailMessage email = constructEmailMessage();
		javaMailSender.send(email);
		return "home";
	}
	
	
	private final SimpleMailMessage constructEmailMessage(){
		final String recipientAddres = "juanjun24@gmail.com";
		final String subject = "Reservacion espacio";
		final String message = "Reservacion Exitosa";

		final SimpleMailMessage email = new SimpleMailMessage();
		
		email.setTo(recipientAddres);
		email.setSubject(subject);
		email.setText(message + "\r\n\n" );
		
		email.setFrom(env.getProperty("support.email"));
		
		return email;
		
	}

}
