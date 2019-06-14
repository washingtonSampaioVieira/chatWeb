package chat.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import chat.api.model.Usuario;

public interface UsuarioRepository 
	extends JpaRepository<Usuario, Long> {
	
	@Query("select u from Usuario u where u.nome = ?1 and u.senha = ?2")
	public Usuario login (String nome, String senha);
	
	
	
	
}
