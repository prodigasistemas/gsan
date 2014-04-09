package gcom.gui.operacional.abastecimento;


import gcom.operacional.DivisaoEsgoto;
import gcom.operacional.SistemaEsgoto;
import gcom.operacional.SistemaEsgotoTratamentoTipo;
import gcom.util.Util;

import org.apache.struts.validator.ValidatorForm;


/**
 * [UC0525] Manter Sistema Esgoto [SB0001]Atualizar Sistema Esgoto
 *
 * @author Kássia Albuquerque
 * @date 16/03/2007
 */

public class AtualizarSistemaEsgotoActionForm extends ValidatorForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String descricaoSistemaEsgoto;
	
	private String descricaoAbreviada;

	private String divisaoEsgoto;
	
	private String tipoTratamento;
	
	private String indicadorUso;
	
	private String ultimaAlteracao;
	
	
	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}



	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}



	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}



	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}



	public String getDescricaoSistemaEsgoto() {
		return descricaoSistemaEsgoto;
	}



	public void setDescricaoSistemaEsgoto(String descricaoSistemaEsgoto) {
		this.descricaoSistemaEsgoto = descricaoSistemaEsgoto;
	}



	public String getDivisaoEsgoto() {
		return divisaoEsgoto;
	}



	public void setDivisaoEsgoto(String divisaoEsgoto) {
		this.divisaoEsgoto = divisaoEsgoto;
	}



	public String getIndicadorUso() {
		return indicadorUso;
	}



	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}



	public String getTipoTratamento() {
		return tipoTratamento;
	}



	public void setTipoTratamento(String tipoTratamento) {
		this.tipoTratamento = tipoTratamento;
	}

	// Esse método carrega todos os valores da base de dados 
	// necesários para exibição da tela AtualizarSistemaEsgoto.

	public SistemaEsgoto setFormValues(SistemaEsgoto sistemaEsgoto) {
		
		sistemaEsgoto.setDescricao(getDescricaoSistemaEsgoto());
		sistemaEsgoto.setDescricaoAbreviada(getDescricaoAbreviada());
		
		DivisaoEsgoto divisaoEsgoto = new DivisaoEsgoto();
		divisaoEsgoto.setId(new Integer(getDivisaoEsgoto()));
		sistemaEsgoto.setDivisaoEsgoto(divisaoEsgoto);
		
		SistemaEsgotoTratamentoTipo sistemaEsgotoTratamentoTipo = new SistemaEsgotoTratamentoTipo();
		sistemaEsgotoTratamentoTipo.setId(new Integer(getTipoTratamento()));
		sistemaEsgoto.setSistemaEsgotoTratamentoTipo(sistemaEsgotoTratamentoTipo);
		
		sistemaEsgoto.setIndicadorUso(new Short(getIndicadorUso()));
		sistemaEsgoto.setUltimaAlteracao( Util.converteStringParaDateHora( getUltimaAlteracao() ) );
		
		return sistemaEsgoto;
}

}
