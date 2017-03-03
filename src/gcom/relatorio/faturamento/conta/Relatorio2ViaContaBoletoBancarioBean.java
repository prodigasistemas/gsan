package gcom.relatorio.faturamento.conta;

import gcom.faturamento.bean.EmitirContaHelper;
import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * [UC0482] Emitir 2ª Via de Conta 
 * subrelatorio quando for gerado boleto bancario
 * 
 * @author Vivianne Sousa
 * @date 29/08/2008
 */
public class Relatorio2ViaContaBoletoBancarioBean implements RelatorioBean {
	
	private String indicadorPrimeiraPaginaBoleto;
	
	private JRBeanCollectionDataSource arrayJRDetailBoleto;
	private ArrayList arrayRelatorio2ViaContaDetailBean;
	
	private String matriculaImovelFormatadaBoleto;
	private String nomeClienteBoleto;
	private String enderecoImovelBoleto;
	private String faturaBoleto;
	private String inscricaoBoleto;
	private String valorContaStringBoleto;
	private String primeiraParteBoleto;
	private String segundaParteBoleto;
	private String terceiraParteBoleto;
	private String nomeGerenciaRegionalBoleto;
	private String mesAnoFormatadoBoleto;
	private String numeroIndiceTurbidezBoleto;
	private String numeroCloroResidualBoleto;	
	private String representacaoNumericaCodBarraFormatadaBoleto;
	private String representacaoNumericaCodBarraSemDigitoBoleto;
	private String dataValidadeBoleto;
	private String grupoBoleto;	
	private String firmaBoleto;
	private String idContaBoleto;
	private String totalPaginasRelatorioBoleto;
	private String contaSemCodigoBarrasBoleto;
	
	private String nossoNumero;
	private String cedente;
	private String dataAtual;
	
	//só aparece na CAERN
	private String rotaEntrega;
	private String debitoCreditoSituacaoAtualContaBoleto;
	private String contaPagaBoleto;
	private String numeroNitrato;
	private String numeroColiformesTotais;
	private String numeroPH;
	
	public Relatorio2ViaContaBoletoBancarioBean(
			EmitirContaHelper emitirContaHelper,
			int indicadorPrimeiraPaginaBoleto,
			int totalPaginasRelatorio,
			String contaPaga,
			Collection colecaoDetail,
			String debitoCreditoSituacaoAtualConta,
			String cedente) {
	    	
		this.indicadorPrimeiraPaginaBoleto = "" + indicadorPrimeiraPaginaBoleto;
		this.matriculaImovelFormatadaBoleto = emitirContaHelper.getIdImovel() + "";
		this.nomeClienteBoleto = emitirContaHelper.getNomeCliente() ;
		this.enderecoImovelBoleto = emitirContaHelper.getEnderecoImovel() ;
		this.faturaBoleto = emitirContaHelper.getFatura() ;
		this.inscricaoBoleto = emitirContaHelper.getInscricaoImovel() ;
		this.valorContaStringBoleto = emitirContaHelper.getValorContaString() ;
		this.primeiraParteBoleto = emitirContaHelper.getPrimeiraParte() ;
    	this.segundaParteBoleto = emitirContaHelper.getSegundaParte() ;
    	this.terceiraParteBoleto = emitirContaHelper.getTerceiraParte();
    	this.nomeGerenciaRegionalBoleto = emitirContaHelper.getNomeGerenciaRegional();
    	this.mesAnoFormatadoBoleto = emitirContaHelper.getMesAnoFormatado();
    	this.numeroIndiceTurbidezBoleto = emitirContaHelper.getNumeroIndiceTurbidez();
    	this.numeroCloroResidualBoleto = emitirContaHelper.getNumeroCloroResidual();	
    	this.representacaoNumericaCodBarraFormatadaBoleto = 
    		emitirContaHelper.getRepresentacaoNumericaCodBarraFormatada();
    	this.representacaoNumericaCodBarraSemDigitoBoleto = 
    		emitirContaHelper.getRepresentacaoNumericaCodBarraSemDigito();
    	this.dataValidadeBoleto = emitirContaHelper.getDataValidade();
    	this.grupoBoleto = emitirContaHelper.getIdFaturamentoGrupo().toString();	
    	this.firmaBoleto = emitirContaHelper.getIdEmpresa().toString();
    	this.totalPaginasRelatorioBoleto = "" + totalPaginasRelatorio;
    	this.idContaBoleto = emitirContaHelper.getIdConta().toString();
    	this.contaSemCodigoBarrasBoleto = emitirContaHelper.getContaSemCodigoBarras();
    	this.debitoCreditoSituacaoAtualContaBoleto = debitoCreditoSituacaoAtualConta;
    	this.contaPagaBoleto = contaPaga;
    	
    	this.nossoNumero = emitirContaHelper.getNossoNumero();
    	this.cedente = cedente;
    	this.dataAtual = Util.formatarData(new Date());
    	
    	this.numeroNitrato = emitirContaHelper.getNumeroNitrato();
    	this.numeroColiformesTotais = emitirContaHelper.getValorMedioColiformesTotais();
    	this.numeroPH = emitirContaHelper.getValorMedioPh();
    	
		this.arrayRelatorio2ViaContaDetailBean = new ArrayList();
		this.arrayRelatorio2ViaContaDetailBean.addAll(colecaoDetail);
		this.arrayJRDetailBoleto = new JRBeanCollectionDataSource(
				this.arrayRelatorio2ViaContaDetailBean);
		  
	}
	
	public JRBeanCollectionDataSource getArrayJRDetailBoleto() {
		return arrayJRDetailBoleto;
	}

	public void setArrayJRDetailBoleto(
			JRBeanCollectionDataSource arrayJRDetailBoleto) {
		this.arrayJRDetailBoleto = arrayJRDetailBoleto;
	}

	public ArrayList getArrayRelatorio2ViaContaDetailBean() {
		return arrayRelatorio2ViaContaDetailBean;
	}

	public void setArrayRelatorio2ViaContaDetailBean(
			ArrayList arrayRelatorio2ViaContaDetailBean) {
		this.arrayRelatorio2ViaContaDetailBean = arrayRelatorio2ViaContaDetailBean;
	}

	public String getContaPagaBoleto() {
		return contaPagaBoleto;
	}

	public void setContaPagaBoleto(String contaPagaBoleto) {
		this.contaPagaBoleto = contaPagaBoleto;
	}

	public String getContaSemCodigoBarrasBoleto() {
		return contaSemCodigoBarrasBoleto;
	}

	public void setContaSemCodigoBarrasBoleto(String contaSemCodigoBarrasBoleto) {
		this.contaSemCodigoBarrasBoleto = contaSemCodigoBarrasBoleto;
	}

	public String getDataValidadeBoleto() {
		return dataValidadeBoleto;
	}

	public void setDataValidadeBoleto(String dataValidadeBoleto) {
		this.dataValidadeBoleto = dataValidadeBoleto;
	}

	public String getDebitoCreditoSituacaoAtualContaBoleto() {
		return debitoCreditoSituacaoAtualContaBoleto;
	}

	public void setDebitoCreditoSituacaoAtualContaBoleto(
			String debitoCreditoSituacaoAtualContaBoleto) {
		this.debitoCreditoSituacaoAtualContaBoleto = debitoCreditoSituacaoAtualContaBoleto;
	}

	public String getEnderecoImovelBoleto() {
		return enderecoImovelBoleto;
	}

	public void setEnderecoImovelBoleto(String enderecoImovelBoleto) {
		this.enderecoImovelBoleto = enderecoImovelBoleto;
	}

	public String getFaturaBoleto() {
		return faturaBoleto;
	}

	public void setFaturaBoleto(String faturaBoleto) {
		this.faturaBoleto = faturaBoleto;
	}

	public String getFirmaBoleto() {
		return firmaBoleto;
	}

	public void setFirmaBoleto(String firmaBoleto) {
		this.firmaBoleto = firmaBoleto;
	}

	public String getGrupoBoleto() {
		return grupoBoleto;
	}

	public void setGrupoBoleto(String grupoBoleto) {
		this.grupoBoleto = grupoBoleto;
	}

	public String getIdContaBoleto() {
		return idContaBoleto;
	}

	public void setIdContaBoleto(String idContaBoleto) {
		this.idContaBoleto = idContaBoleto;
	}

	public String getIndicadorPrimeiraPaginaBoleto() {
		return indicadorPrimeiraPaginaBoleto;
	}

	public void setIndicadorPrimeiraPaginaBoleto(
			String indicadorPrimeiraPaginaBoleto) {
		this.indicadorPrimeiraPaginaBoleto = indicadorPrimeiraPaginaBoleto;
	}

	public String getInscricaoBoleto() {
		return inscricaoBoleto;
	}

	public void setInscricaoBoleto(String inscricaoBoleto) {
		this.inscricaoBoleto = inscricaoBoleto;
	}

	public String getMatriculaImovelFormatadaBoleto() {
		return matriculaImovelFormatadaBoleto;
	}

	public void setMatriculaImovelFormatadaBoleto(
			String matriculaImovelFormatadaBoleto) {
		this.matriculaImovelFormatadaBoleto = matriculaImovelFormatadaBoleto;
	}

	public String getMesAnoFormatadoBoleto() {
		return mesAnoFormatadoBoleto;
	}

	public void setMesAnoFormatadoBoleto(String mesAnoFormatadoBoleto) {
		this.mesAnoFormatadoBoleto = mesAnoFormatadoBoleto;
	}

	public String getNomeClienteBoleto() {
		return nomeClienteBoleto;
	}

	public void setNomeClienteBoleto(String nomeClienteBoleto) {
		this.nomeClienteBoleto = nomeClienteBoleto;
	}

	public String getNomeGerenciaRegionalBoleto() {
		return nomeGerenciaRegionalBoleto;
	}

	public void setNomeGerenciaRegionalBoleto(String nomeGerenciaRegionalBoleto) {
		this.nomeGerenciaRegionalBoleto = nomeGerenciaRegionalBoleto;
	}

	public String getNumeroCloroResidualBoleto() {
		return numeroCloroResidualBoleto;
	}

	public void setNumeroCloroResidualBoleto(String numeroCloroResidualBoleto) {
		this.numeroCloroResidualBoleto = numeroCloroResidualBoleto;
	}

	public String getNumeroIndiceTurbidezBoleto() {
		return numeroIndiceTurbidezBoleto;
	}

	public void setNumeroIndiceTurbidezBoleto(String numeroIndiceTurbidezBoleto) {
		this.numeroIndiceTurbidezBoleto = numeroIndiceTurbidezBoleto;
	}

	public String getPrimeiraParteBoleto() {
		return primeiraParteBoleto;
	}

	public void setPrimeiraParteBoleto(String primeiraParteBoleto) {
		this.primeiraParteBoleto = primeiraParteBoleto;
	}

	public String getRepresentacaoNumericaCodBarraFormatadaBoleto() {
		return representacaoNumericaCodBarraFormatadaBoleto;
	}

	public void setRepresentacaoNumericaCodBarraFormatadaBoleto(
			String representacaoNumericaCodBarraFormatadaBoleto) {
		this.representacaoNumericaCodBarraFormatadaBoleto = representacaoNumericaCodBarraFormatadaBoleto;
	}

	public String getRepresentacaoNumericaCodBarraSemDigitoBoleto() {
		return representacaoNumericaCodBarraSemDigitoBoleto;
	}

	public void setRepresentacaoNumericaCodBarraSemDigitoBoleto(
			String representacaoNumericaCodBarraSemDigitoBoleto) {
		this.representacaoNumericaCodBarraSemDigitoBoleto = representacaoNumericaCodBarraSemDigitoBoleto;
	}

	public String getRotaEntrega() {
		return rotaEntrega;
	}

	public void setRotaEntrega(String rotaEntrega) {
		this.rotaEntrega = rotaEntrega;
	}

	public String getSegundaParteBoleto() {
		return segundaParteBoleto;
	}

	public void setSegundaParteBoleto(String segundaParteBoleto) {
		this.segundaParteBoleto = segundaParteBoleto;
	}

	public String getTerceiraParteBoleto() {
		return terceiraParteBoleto;
	}

	public void setTerceiraParteBoleto(String terceiraParteBoleto) {
		this.terceiraParteBoleto = terceiraParteBoleto;
	}

	public String getTotalPaginasRelatorioBoleto() {
		return totalPaginasRelatorioBoleto;
	}

	public void setTotalPaginasRelatorioBoleto(String totalPaginasRelatorioBoleto) {
		this.totalPaginasRelatorioBoleto = totalPaginasRelatorioBoleto;
	}

	public String getValorContaStringBoleto() {
		return valorContaStringBoleto;
	}

	public void setValorContaStringBoleto(String valorContaStringBoleto) {
		this.valorContaStringBoleto = valorContaStringBoleto;
	}

	public String getCedente() {
		return cedente;
	}

	public void setCedente(String cedente) {
		this.cedente = cedente;
	}

	public String getNossoNumero() {
		return nossoNumero;
	}

	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}

	public String getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(String dataAtual) {
		this.dataAtual = dataAtual;
	}

	public String getNumeroColiformesTotais() {
		return numeroColiformesTotais;
	}

	public void setNumeroColiformesTotais(String numeroColiformesTotais) {
		this.numeroColiformesTotais = numeroColiformesTotais;
	}

	public String getNumeroNitrato() {
		return numeroNitrato;
	}

	public void setNumeroNitrato(String numeroNitrato) {
		this.numeroNitrato = numeroNitrato;
	}

	public String getNumeroPH() {
		return numeroPH;
	}

	public void setNumeroPH(String numeroPH) {
		this.numeroPH = numeroPH;
	}

	
	
	}
