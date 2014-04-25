package gcom.gui.faturamento;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0846]FILTRAR TIPO DA SITUACAO DE FATURAMENTO
 * 
 * @author Arthur Carvalho
 * @date 20/08/2008
 */

public class FiltrarFaturamentoSituacaoTipoActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String id;
	private String descricao;
	private String indicadorParalisacaoFaturamento;
	private String indicadorParalisacaoLeitura;
	private String indicadorValidoAgua;
	private String indicadorValidoEsgoto;
	private String indicadorInformarConsumo;
	private String indicadorInformarVolume;
	private String indicadorUso;
	private String leituraAnormalidadeLeituraComLeitura;
	private String leituraAnormalidadeLeituraSemLeitura;
	private String leituraAnormalidadeConsumoComLeitura;
	private String leituraAnormalidadeConsumoSemLeitura;
	private String indicadorAtualizar;
	private String tipoPesquisa;
	
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
	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}
	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}
	public String getIndicadorInformarConsumo() {
		return indicadorInformarConsumo;
	}
	public void setIndicadorInformarConsumo(String indicadorInformarConsumo) {
		this.indicadorInformarConsumo = indicadorInformarConsumo;
	}
	public String getIndicadorInformarVolume() {
		return indicadorInformarVolume;
	}
	public void setIndicadorInformarVolume(String indicadorInformarVolume) {
		this.indicadorInformarVolume = indicadorInformarVolume;
	}
	public String getIndicadorParalisacaoFaturamento() {
		return indicadorParalisacaoFaturamento;
	}
	public void setIndicadorParalisacaoFaturamento(
			String indicadorParalisacaoFaturamento) {
		this.indicadorParalisacaoFaturamento = indicadorParalisacaoFaturamento;
	}
	public String getIndicadorParalisacaoLeitura() {
		return indicadorParalisacaoLeitura;
	}
	public void setIndicadorParalisacaoLeitura(String indicadorParalisacaoLeitura) {
		this.indicadorParalisacaoLeitura = indicadorParalisacaoLeitura;
	}
	public String getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public String getIndicadorValidoAgua() {
		return indicadorValidoAgua;
	}
	public void setIndicadorValidoAgua(String indicadorValidoAgua) {
		this.indicadorValidoAgua = indicadorValidoAgua;
	}
	public String getIndicadorValidoEsgoto() {
		return indicadorValidoEsgoto;
	}
	public void setIndicadorValidoEsgoto(String indicadorValidoEsgoto) {
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
	public String getTipoPesquisa() {
		return tipoPesquisa;
	}
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
	
}	
