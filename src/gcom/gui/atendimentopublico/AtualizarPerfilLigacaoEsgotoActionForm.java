package gcom.gui.atendimentopublico;

import java.sql.Date;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * 
 * @author Arthur Carvalho
 * @date 17/10/2008
 */

public class AtualizarPerfilLigacaoEsgotoActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String id;

	private String descricao;

	private String percentualEsgotoConsumidaColetada;

	private Short indicadorUso;
	
	private Short indicadorPrincipal;

	private Date ultimaAlteracao;


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Short getIndicadorPrincipal() {
		return indicadorPrincipal;
	}

	public void setIndicadorPrincipal(Short indicadorPrincipal) {
		this.indicadorPrincipal = indicadorPrincipal;
	}

	public String getPercentualEsgotoConsumidaColetada() {
		return percentualEsgotoConsumidaColetada;
	}

	public void setPercentualEsgotoConsumidaColetada(
			String percentualEsgotoConsumidaColetada) {
		this.percentualEsgotoConsumidaColetada = percentualEsgotoConsumidaColetada;
	}

	
}
