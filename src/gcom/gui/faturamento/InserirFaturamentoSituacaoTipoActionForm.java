package gcom.gui.faturamento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Arthur Carvalho
 * @date 11/08/2008
 */
public class InserirFaturamentoSituacaoTipoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	String descricao;
	
	Short indicadorParalisacaoFaturamento;
	
	Short indicadorParalisacaoLeitura;
	
	Short indicadorValidoAgua;
	
	Short indicadorValidoEsgoto;
	
	Short indicadorInformarConsumo;
    
    Short indicadorInformarVolume;
	
	Short indicadorUso;
	
	String leituraAnormalidadeLeituraComLeitura;
	
	String leituraAnormalidadeLeituraSemLeitura;
	
	String leituraAnormalidadeConsumoComLeitura;
	
	String leituraAnormalidadeConsumoSemLeitura;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getIndicadorInformarConsumo() {
		return indicadorInformarConsumo;
	}

	public void setIndicadorInformarConsumo(Short indicadorInformarConsumo) {
		this.indicadorInformarConsumo = indicadorInformarConsumo;
	}

	public Short getIndicadorInformarVolume() {
		return indicadorInformarVolume;
	}

	public void setIndicadorInformarVolume(Short indicadorInformarVolume) {
		this.indicadorInformarVolume = indicadorInformarVolume;
	}

	public Short getIndicadorParalisacaoFaturamento() {
		return indicadorParalisacaoFaturamento;
	}

	public void setIndicadorParalisacaoFaturamento(
			Short indicadorParalisacaoFaturamento) {
		this.indicadorParalisacaoFaturamento = indicadorParalisacaoFaturamento;
	}

	public Short getIndicadorParalisacaoLeitura() {
		return indicadorParalisacaoLeitura;
	}

	public void setIndicadorParalisacaoLeitura(Short indicadorParalisacaoLeitura) {
		this.indicadorParalisacaoLeitura = indicadorParalisacaoLeitura;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Short getIndicadorValidoAgua() {
		return indicadorValidoAgua;
	}

	public void setIndicadorValidoAgua(Short indicadorValidoAgua) {
		this.indicadorValidoAgua = indicadorValidoAgua;
	}

	public Short getIndicadorValidoEsgoto() {
		return indicadorValidoEsgoto;
	}

	public void setIndicadorValidoEsgoto(Short indicadorValidoEsgoto) {
		this.indicadorValidoEsgoto = indicadorValidoEsgoto;
	}

	public String getLeituraAnormalidadeConsumoComLeitura() {
		return leituraAnormalidadeConsumoComLeitura;
	}

	public void setLeituraAnormalidadeConsumoComLeitura(
			String leituraAnormalidadeConsumoComLeitura) {
		this.leituraAnormalidadeConsumoComLeitura = leituraAnormalidadeConsumoComLeitura;
	}

	public String getLeituraAnormalidadeConsumoSemLeitura() {
		return leituraAnormalidadeConsumoSemLeitura;
	}

	public void setLeituraAnormalidadeConsumoSemLeitura(
			String leituraAnormalidadeConsumoSemLeitura) {
		this.leituraAnormalidadeConsumoSemLeitura = leituraAnormalidadeConsumoSemLeitura;
	}

	public String getLeituraAnormalidadeLeituraComLeitura() {
		return leituraAnormalidadeLeituraComLeitura;
	}

	public void setLeituraAnormalidadeLeituraComLeitura(
			String leituraAnormalidadeLeituraComLeitura) {
		this.leituraAnormalidadeLeituraComLeitura = leituraAnormalidadeLeituraComLeitura;
	}

	public String getLeituraAnormalidadeLeituraSemLeitura() {
		return leituraAnormalidadeLeituraSemLeitura;
	}

	public void setLeituraAnormalidadeLeituraSemLeitura(
			String leituraAnormalidadeLeituraSemLeitura) {
		this.leituraAnormalidadeLeituraSemLeitura = leituraAnormalidadeLeituraSemLeitura;
	}
	
	
	
	
	
}
