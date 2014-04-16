package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

import java.util.ArrayList;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * [UC0532] Gerar Relatório de Faturamento das Ligações com Medição Individualizada
 * 
 * @author Vivianne Sousa
 * @date 09/01/2007
 */
public class RelatorioFaturamentoLigacoesMedicaoIndividualizadaBean implements RelatorioBean {
	
	private String idLocalidade;
	private String nomeLocalidade; 
	private String matriculaImovel;
	private String inscricao;
	private String nomeConsumidor;
	private String qtdeEconomias;
	private String leituraAnterior;
	private String dataLeituraAnterior;
	private String leituraAtual;
	private String dataLeituraAtual;
	private String media;
	private String consumoImoveisVinculados;
	private String consumoFaturado;
	private String rateio;
	private String consumoEsgoto;
	private String anormalidade;
	private String anormalidadeConsumo;
	private String tipoConsumo;
	private String indicadorPoco;

	private String indicadorQuebraImovelCondominio;
	private String totalConsumidoresRateioMacromedidor;
	private String numeroEconomiasRateio;
	
	
	 public RelatorioFaturamentoLigacoesMedicaoIndividualizadaBean(
			 String idLocalidade,
			 String nomeLocalidade, 
			 String matriculaImovel,
			 String inscricao,
			 String nomeConsumidor,
			 String qtdeEconomias,
			 String leituraAnterior,
			 String dataLeituraAnterior,
			 String leituraAtual,
			 String dataLeituraAtual,
			 String media,
			 String consumoImoveisVinculados,
			 String consumoFaturado,
			 String rateio,
			 String consumoEsgoto,
			 String anormalidade,
			 String anormalidadeConsumo,
			 String tipoConsumo,
			 String indicadorPoco,
			 String indicadorQuebraImovelCondominio,
			 String totalConsumidoresRateioMacromedidor,
			 String numeroEconomiasRateio){
		 
		 
		 this.idLocalidade = idLocalidade;
		 this.nomeLocalidade = nomeLocalidade; 
		 this.matriculaImovel = matriculaImovel;
		 this.inscricao = inscricao;
		 this.nomeConsumidor = nomeConsumidor;
		 this.qtdeEconomias = qtdeEconomias;
		 this.leituraAnterior = leituraAnterior;
		 this.dataLeituraAnterior = dataLeituraAnterior;
		 this.leituraAtual = leituraAtual;
		 this.dataLeituraAtual = dataLeituraAtual;
		 this.media = media;
		 this.consumoImoveisVinculados = consumoImoveisVinculados;
		 this.consumoFaturado = consumoFaturado;
		 this.rateio = rateio;
		 this.consumoEsgoto = consumoEsgoto;
		 this.anormalidade = anormalidade;
		 this.anormalidadeConsumo = anormalidadeConsumo;
		 this.tipoConsumo = tipoConsumo;
		 this.indicadorPoco = indicadorPoco; 
		 this.indicadorQuebraImovelCondominio = indicadorQuebraImovelCondominio;
		 this.totalConsumidoresRateioMacromedidor = totalConsumidoresRateioMacromedidor;
		 this.numeroEconomiasRateio = numeroEconomiasRateio;
		 
	 }
		 
		
	
	private String indicadorPrimeiraPagina;
	
	private JRBeanCollectionDataSource arrayJRDetail;

	private ArrayList arrayRelatorio2ViaContaDetailBean;


	public String getAnormalidade() {
		return anormalidade;
	}

	public void setAnormalidade(String anormalidade) {
		this.anormalidade = anormalidade;
	}

	public String getAnormalidadeConsumo() {
		return anormalidadeConsumo;
	}

	public void setAnormalidadeConsumo(String anormalidadeConsumo) {
		this.anormalidadeConsumo = anormalidadeConsumo;
	}

	public JRBeanCollectionDataSource getArrayJRDetail() {
		return arrayJRDetail;
	}

	public void setArrayJRDetail(JRBeanCollectionDataSource arrayJRDetail) {
		this.arrayJRDetail = arrayJRDetail;
	}

	public ArrayList getArrayRelatorio2ViaContaDetailBean() {
		return arrayRelatorio2ViaContaDetailBean;
	}

	public void setArrayRelatorio2ViaContaDetailBean(
			ArrayList arrayRelatorio2ViaContaDetailBean) {
		this.arrayRelatorio2ViaContaDetailBean = arrayRelatorio2ViaContaDetailBean;
	}

	public String getConsumoEsgoto() {
		return consumoEsgoto;
	}

	public void setConsumoEsgoto(String consumoEsgoto) {
		this.consumoEsgoto = consumoEsgoto;
	}

	public String getConsumoFaturado() {
		return consumoFaturado;
	}

	public void setConsumoFaturado(String consumoFaturado) {
		this.consumoFaturado = consumoFaturado;
	}

	public String getConsumoImoveisVinculados() {
		return consumoImoveisVinculados;
	}

	public void setConsumoImoveisVinculados(String consumoImoveisVinculados) {
		this.consumoImoveisVinculados = consumoImoveisVinculados;
	}

	public String getDataLeituraAnterior() {
		return dataLeituraAnterior;
	}

	public void setDataLeituraAnterior(String dataLeituraAnterior) {
		this.dataLeituraAnterior = dataLeituraAnterior;
	}

	public String getDataLeituraAtual() {
		return dataLeituraAtual;
	}

	public void setDataLeituraAtual(String dataLeituraAtual) {
		this.dataLeituraAtual = dataLeituraAtual;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIndicadorPoco() {
		return indicadorPoco;
	}

	public void setIndicadorPoco(String indicadorPoco) {
		this.indicadorPoco = indicadorPoco;
	}

	public String getIndicadorPrimeiraPagina() {
		return indicadorPrimeiraPagina;
	}

	public void setIndicadorPrimeiraPagina(String indicadorPrimeiraPagina) {
		this.indicadorPrimeiraPagina = indicadorPrimeiraPagina;
	}

	public String getIndicadorQuebraImovelCondominio() {
		return indicadorQuebraImovelCondominio;
	}

	public void setIndicadorQuebraImovelCondominio(
			String indicadorQuebraImovelCondominio) {
		this.indicadorQuebraImovelCondominio = indicadorQuebraImovelCondominio;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getLeituraAnterior() {
		return leituraAnterior;
	}

	public void setLeituraAnterior(String leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}

	public String getLeituraAtual() {
		return leituraAtual;
	}

	public void setLeituraAtual(String leituraAtual) {
		this.leituraAtual = leituraAtual;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getNomeConsumidor() {
		return nomeConsumidor;
	}

	public void setNomeConsumidor(String nomeConsumidor) {
		this.nomeConsumidor = nomeConsumidor;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getNumeroEconomiasRateio() {
		return numeroEconomiasRateio;
	}

	public void setNumeroEconomiasRateio(String numeroEconomiasRateio) {
		this.numeroEconomiasRateio = numeroEconomiasRateio;
	}

	public String getQtdeEconomias() {
		return qtdeEconomias;
	}

	public void setQtdeEconomias(String qtdeEconomias) {
		this.qtdeEconomias = qtdeEconomias;
	}

	public String getRateio() {
		return rateio;
	}

	public void setRateio(String rateio) {
		this.rateio = rateio;
	}

	public String getTipoConsumo() {
		return tipoConsumo;
	}

	public void setTipoConsumo(String tipoConsumo) {
		this.tipoConsumo = tipoConsumo;
	}

	public String getTotalConsumidoresRateioMacromedidor() {
		return totalConsumidoresRateioMacromedidor;
	}

	public void setTotalConsumidoresRateioMacromedidor(
			String totalConsumidoresRateioMacromedidor) {
		this.totalConsumidoresRateioMacromedidor = totalConsumidoresRateioMacromedidor;
	}
	
	


}
