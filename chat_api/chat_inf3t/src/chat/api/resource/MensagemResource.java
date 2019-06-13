package chat.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import chat.api.model.Mensagem;
import chat.api.repository.MensagemRepository;

@RequestMapping("/chat")
@RestController
public class MensagemResource {
	@Autowired
	private MensagemRepository mensagemRepository;
	
	@GetMapping
	public List<Mensagem> getMensagens(){
		return mensagemRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Mensagem> setMensagem(@RequestBody Mensagem m, HttpServletResponse response) {
		Mensagem mensagem = mensagemRepository.save(m);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(mensagem.getCodMensagem())
				.toUri();
		response.addHeader("Location", uri.toASCIIString());

		return ResponseEntity.ok(mensagem);
	}
	
	
}
