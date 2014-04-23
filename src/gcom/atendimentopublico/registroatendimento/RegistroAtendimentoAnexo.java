package gcom.atendimentopublico.registroatendimento;

import java.io.Serializable;
import java.util.Date;

public class RegistroAtendimentoAnexo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private RegistroAtendimento registroAtendimento;
	
	private String descricaoDocumento;
	
	private byte[] imagemDocumento;
	
	private Date ultimaAlteracao;
	
	private String nomeExtensaoDocumento;
	
	public RegistroAtendimentoAnexo(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RegistroAtendimento getRegistroAtendimento() {
		return registroAtendimento;
	}

	public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof RegistroAtendimentoAnexo)) {
			return false;
		}
		RegistroAtendimentoAnexo castOther = (RegistroAtendimentoAnexo) other;

		return (this.getId().equals(castOther.getId()));
	}

	public String getDescricaoDocumento() {
		return descricaoDocumento;
	}

	public void setDescricaoDocumento(String descricaoDocumento) {
		this.descricaoDocumento = descricaoDocumento;
	}

	public byte[] getImagemDocumento() {
		return imagemDocumento;
	}

	public void setImagemDocumento(byte[] imagemDocumento) {
		this.imagemDocumento = imagemDocumento;
	}

	public String getNomeExtensaoDocumento() {
		return nomeExtensaoDocumento;
	}

	public void setNomeExtensaoDocumento(String nomeExtensaoDocumento) {
		this.nomeExtensaoDocumento = nomeExtensaoDocumento;
	}
}
