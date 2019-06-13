package chat.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import chat.api.model.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

}
