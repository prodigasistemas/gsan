package gcom.gui.atendimentopublico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Arthur Carvalho
 * @date 25/08/2008
 */
public class InserirLigacaoEsgotoEsgotamentoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	String descricao;
	
	String quantidadeMesesSituacaoEspecial;
	
	String faturamentoSituacaoTipo;
	
	String faturamentoSituacaoMotivo;
	
	Short indicadorUso;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public String getFaturamentoSituacaoMotivo() {
		return faturamentoSituacaoMotivo;
	}

	public void setFaturamentoSituacaoMotivo(String faturamentoSituacaoMotivo) {
		this.faturamentoSituacaoMotivo = faturamentoSituacaoMotivo;
	}

	public String getFaturamentoSituacaoTipo() {
		return faturamentoSituacaoTipo;
	}

	public void setFaturamentoSituacaoTipo(String faturamentoSituacaoTipo) {
		this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
	}

	public String getQuantidadeMesesSituacaoEspecial() {
		return quantidadeMesesSituacaoEspecial;
	}

	public void setQuantidadeMesesSituacaoEspecial(
			String quantidadeMesesSituacaoEspecial) {
		this.quantidadeMesesSituacaoEspecial = quantidadeMesesSituacaoEspecial;
	}
	
	
}
