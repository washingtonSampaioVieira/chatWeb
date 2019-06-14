package chat.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_mensagem")
public class Mensagem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_mensagem")
	private Long codMensagem;

	private String mensagem;

	public Long getCodMensagem() {
		return codMensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setCodMensagem(Long codMensagem) {
		this.codMensagem = codMensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String toString() {
		return "Mensagem [codMensagem=" + codMensagem + ", mensagem=" + mensagem + "]";
	}

}
