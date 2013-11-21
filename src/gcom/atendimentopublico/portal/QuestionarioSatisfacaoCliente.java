package gcom.atendimentopublico.portal;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;

import java.io.Serializable;
import java.util.Date;

public class QuestionarioSatisfacaoCliente  implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;
	
	private Date ultimaAlteracao;
	
	private RegistroAtendimento registroAtendimento;
	
	private Short indicadorBemAtendido;

	private String comentarioBemAtendido;
	
	private Short indicadorSolicitacaoResolvida;

	private String comentarioSolicitacaoResolvida;
	
	private Short indicadorContatoEquipeCampo;

	private String comentarioContatoEquipeCampo;
	
	private Short nota;

	private String comentarioGeral;
	
	public QuestionarioSatisfacaoCliente(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public RegistroAtendimento getRegistroAtendimento() {
		return registroAtendimento;
	}

	public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	public Short getIndicadorBemAtendido() {
		return indicadorBemAtendido;
	}

	public void setIndicadorBemAtendido(Short indicadorBemAtendido) {
		this.indicadorBemAtendido = indicadorBemAtendido;
	}

	public String getComentarioBemAtendido() {
		return comentarioBemAtendido;
	}

	public void setComentarioBemAtendido(String comentarioBemAtendido) {
		this.comentarioBemAtendido = comentarioBemAtendido;
	}

	public Short getIndicadorSolicitacaoResolvida() {
		return indicadorSolicitacaoResolvida;
	}

	public void setIndicadorSolicitacaoResolvida(Short indicadorSolicitacaoResolvida) {
		this.indicadorSolicitacaoResolvida = indicadorSolicitacaoResolvida;
	}

	public String getComentarioSolicitacaoResolvida() {
		return comentarioSolicitacaoResolvida;
	}

	public void setComentarioSolicitacaoResolvida(
			String comentarioSolicitacaoResolvida) {
		this.comentarioSolicitacaoResolvida = comentarioSolicitacaoResolvida;
	}

	public Short getIndicadorContatoEquipeCampo() {
		return indicadorContatoEquipeCampo;
	}

	public void setIndicadorContatoEquipeCampo(Short indicadorContatoEquipeCampo) {
		this.indicadorContatoEquipeCampo = indicadorContatoEquipeCampo;
	}

	public String getComentarioContatoEquipeCampo() {
		return comentarioContatoEquipeCampo;
	}

	public void setComentarioContatoEquipeCampo(String comentarioContatoEquipeCampo) {
		this.comentarioContatoEquipeCampo = comentarioContatoEquipeCampo;
	}

	public Short getNota() {
		return nota;
	}

	public void setNota(Short nota) {
		this.nota = nota;
	}

	public String getComentarioGeral() {
		return comentarioGeral;
	}

	public void setComentarioGeral(String comentarioGeral) {
		this.comentarioGeral = comentarioGeral;
	}
	
}
