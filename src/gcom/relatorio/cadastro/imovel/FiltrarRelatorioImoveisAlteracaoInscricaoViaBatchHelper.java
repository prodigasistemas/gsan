package gcom.relatorio.cadastro.imovel;

import java.io.Serializable;
import java.util.Date;


/**
 * [UC1121] Gerar Relatório de Imóveis com Alteração de Inscrição Via Batch
 * 
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * do relatorio de Imóveis com Alteração de Inscrição Via Batch
 * 
 * @author Hugo Leonardo
 * @date 19/01/2011
 */
public class FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer localidadeInicial;
	private Integer localidadeFinal;
	private Integer setorComercialInicial;
	private Integer setorComercialFinal;
	private Integer quadraInicial;
	private Integer quadraFinal;
	private Integer loteInicial;
	private Integer loteFinal;
	private Integer subLoteInicial;
	private Integer subLoteFinal;
	private Integer escolhaRelatorio;
	private Date dataInicio;
	private Date dataFim;

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Integer getEscolhaRelatorio() {
		return escolhaRelatorio;
	}

	public void setEscolhaRelatorio(Integer escolhaRelatorio) {
		this.escolhaRelatorio = escolhaRelatorio;
	}

	public Integer getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(Integer localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public Integer getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(Integer localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public Integer getLoteFinal() {
		return loteFinal;
	}

	public void setLoteFinal(Integer loteFinal) {
		this.loteFinal = loteFinal;
	}

	public Integer getLoteInicial() {
		return loteInicial;
	}

	public void setLoteInicial(Integer loteInicial) {
		this.loteInicial = loteInicial;
	}

	public Integer getQuadraFinal() {
		return quadraFinal;
	}

	public void setQuadraFinal(Integer quadraFinal) {
		this.quadraFinal = quadraFinal;
	}

	public Integer getQuadraInicial() {
		return quadraInicial;
	}

	public void setQuadraInicial(Integer quadraInicial) {
		this.quadraInicial = quadraInicial;
	}

	public Integer getSetorComercialFinal() {
		return setorComercialFinal;
	}

	public void setSetorComercialFinal(Integer setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}

	public Integer getSetorComercialInicial() {
		return setorComercialInicial;
	}

	public void setSetorComercialInicial(Integer setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}

	public Integer getSubLoteFinal() {
		return subLoteFinal;
	}

	public void setSubLoteFinal(Integer subLoteFinal) {
		this.subLoteFinal = subLoteFinal;
	}

	public Integer getSubLoteInicial() {
		return subLoteInicial;
	}

	public void setSubLoteInicial(Integer subLoteInicial) {
		this.subLoteInicial = subLoteInicial;
	}
	
}
