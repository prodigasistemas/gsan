package gcom.gui.arrecadacao.aviso;

import gcom.util.ConstantesSistema;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Form utilizado no filtro de aviso bancário
 * 
 * @author Vivianne Sousa
 * @created 08/03/2006
 */
public class FiltrarAvisoBancarioActionForm extends ValidatorActionForm  {
	private static final long serialVersionUID = 1L;
	private String arrecadadorCodAgente;
	private String arrecadadorNomeCliente;	
	private String dataLancamentoInicio;
	private String dataLancamentoFim;
	private String tipoAviso;
	private String idAvisoBancario;
	
	//conta bancaria
	private String idContaBancaria;
	private String idBanco;
	private String codAgencia;
	private String numeroConta;

	//movimento
	private String idMovimento;
	private String codigoBanco;
	private String codigoRemessa;
	private String identificacaoServico;
	private String numeroSequencial;
	
	private String periodoArrecadacaoInicio;
	private String periodoArrecadacaoFim;
	private String dataPrevisaoCreditoDebitoInicio;
	private String dataPrevisaoCreditoDebitoFim;
	private String intervaloValorPrevistoInicio;
	private String intervaloValorPrevistoFim;
	private String dataRealizacaoCreditoDebitoInicio;
	private String dataRealizacaoCreditoDebitoFim;
	private String intervaloValorRealizadoInicio;
	private String intervaloValorRealizadoFim;
	
	//Avisos Abertos / Fechados
	private String aviso;
	
	
	
	public String getArrecadadorCodAgente() {
		return arrecadadorCodAgente;
	}
	public void setArrecadadorCodAgente(String arrecadadorCodAgente) {
		this.arrecadadorCodAgente = arrecadadorCodAgente;
	}
	public String getArrecadadorNomeCliente() {
		return arrecadadorNomeCliente;
	}
	public void setArrecadadorNomeCliente(String arrecadadorNomeCliente) {
		this.arrecadadorNomeCliente = arrecadadorNomeCliente;
	}
	public String getCodAgencia() {
		return codAgencia;
	}
	public void setCodAgencia(String codAgencia) {
		this.codAgencia = codAgencia;
	}
	public String getCodigoBanco() {
		return codigoBanco;
	}
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}
	public String getCodigoRemessa() {
		return codigoRemessa;
	}
	public void setCodigoRemessa(String codigoRemessa) {
		this.codigoRemessa = codigoRemessa;
	}
	public String getDataLancamentoFim() {
		return dataLancamentoFim;
	}
	public void setDataLancamentoFim(String dataLancamentoFim) {
		this.dataLancamentoFim = dataLancamentoFim;
	}
	public String getDataLancamentoInicio() {
		return dataLancamentoInicio;
	}
	public void setDataLancamentoInicio(String dataLancamentoInicio) {
		this.dataLancamentoInicio = dataLancamentoInicio;
	}
	public String getDataPrevisaoCreditoDebitoFim() {
		return dataPrevisaoCreditoDebitoFim;
	}
	public void setDataPrevisaoCreditoDebitoFim(String dataPrevisaoCreditoDebitoFim) {
		this.dataPrevisaoCreditoDebitoFim = dataPrevisaoCreditoDebitoFim;
	}
	public String getDataPrevisaoCreditoDebitoInicio() {
		return dataPrevisaoCreditoDebitoInicio;
	}
	public void setDataPrevisaoCreditoDebitoInicio(
			String dataPrevisaoCreditoDebitoInicio) {
		this.dataPrevisaoCreditoDebitoInicio = dataPrevisaoCreditoDebitoInicio;
	}
	public String getDataRealizacaoCreditoDebitoFim() {
		return dataRealizacaoCreditoDebitoFim;
	}
	public void setDataRealizacaoCreditoDebitoFim(
			String dataRealizacaoCreditoDebitoFim) {
		this.dataRealizacaoCreditoDebitoFim = dataRealizacaoCreditoDebitoFim;
	}
	public String getDataRealizacaoCreditoDebitoInicio() {
		return dataRealizacaoCreditoDebitoInicio;
	}
	public void setDataRealizacaoCreditoDebitoInicio(
			String dataRealizacaoCreditoDebitoInicio) {
		this.dataRealizacaoCreditoDebitoInicio = dataRealizacaoCreditoDebitoInicio;
	}
	public String getIdBanco() {
		return idBanco;
	}
	public void setIdBanco(String idBanco) {
		this.idBanco = idBanco;
	}
	public String getIdentificacaoServico() {
		return identificacaoServico;
	}
	public void setIdentificacaoServico(String identificacaoServico) {
		this.identificacaoServico = identificacaoServico;
	}
	public String getIntervaloValorPrevistoFim() {
		return intervaloValorPrevistoFim;
	}
	public void setIntervaloValorPrevistoFim(String intervaloValorPrevistoFim) {
		this.intervaloValorPrevistoFim = intervaloValorPrevistoFim;
	}
	public String getIntervaloValorPrevistoInicio() {
		return intervaloValorPrevistoInicio;
	}
	public void setIntervaloValorPrevistoInicio(String intervaloValorPrevistoInicio) {
		this.intervaloValorPrevistoInicio = intervaloValorPrevistoInicio;
	}
	public String getIntervaloValorRealizadoFim() {
		return intervaloValorRealizadoFim;
	}
	public void setIntervaloValorRealizadoFim(String intervaloValorRealizadoFim) {
		this.intervaloValorRealizadoFim = intervaloValorRealizadoFim;
	}
	public String getIntervaloValorRealizadoInicio() {
		return intervaloValorRealizadoInicio;
	}
	public void setIntervaloValorRealizadoInicio(
			String intervaloValorRealizadoInicio) {
		this.intervaloValorRealizadoInicio = intervaloValorRealizadoInicio;
	}
	public String getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	public String getNumeroSequencial() {
		return numeroSequencial;
	}
	public void setNumeroSequencial(String numeroSequencial) {
		this.numeroSequencial = numeroSequencial;
	}
	public String getPeriodoArrecadacaoFim() {
		return periodoArrecadacaoFim;
	}
	public void setPeriodoArrecadacaoFim(String periodoArrecadacaoFim) {
		this.periodoArrecadacaoFim = periodoArrecadacaoFim;
	}
	public String getPeriodoArrecadacaoInicio() {
		return periodoArrecadacaoInicio;
	}
	public void setPeriodoArrecadacaoInicio(String periodoArrecadacaoInicio) {
		this.periodoArrecadacaoInicio = periodoArrecadacaoInicio;
	}
	public String getTipoAviso() {
		return tipoAviso;
	}
	public void setTipoAviso(String tipoAviso) {
		this.tipoAviso = tipoAviso;
	}
	public String getAviso() {
		return aviso;
	}
	public void setAviso(String aviso) {
		this.aviso = aviso;
	}
	public String getIdMovimento() {
		return idMovimento;
	}
	public void setIdMovimento(String idMovimento) {
		this.idMovimento = idMovimento;
	}
	public String getIdContaBancaria() {
		return idContaBancaria;
	}
	public void setIdContaBancaria(String idContaBancaria) {
		this.idContaBancaria = idContaBancaria;
	}
	/*
	 *
	 * Acréscimo desse campo para ser usado na pesquisa, na ação manter aviso bancário
	*/
	public String getIdAvisoBancario() {
		return idAvisoBancario;
	}
	public void setIdAvisoBancario(String idAvisoBancario) {
		this.idAvisoBancario = idAvisoBancario;
	}
	

	public void limparForm (){
		
    	setArrecadadorCodAgente("");
    	setArrecadadorNomeCliente("");
    	setDataLancamentoInicio("");
    	setDataLancamentoFim("");
    	setTipoAviso("3");
    	setCodAgencia("");
    	setIdBanco("");
    	setNumeroConta("");
    	setCodigoBanco("");
    	setCodigoRemessa("");
    	setIdentificacaoServico("");
    	setNumeroSequencial("");
    	setPeriodoArrecadacaoInicio("");
    	setPeriodoArrecadacaoFim("");
    	setDataPrevisaoCreditoDebitoInicio("");
    	setDataPrevisaoCreditoDebitoFim("");
    	setIntervaloValorPrevistoInicio("");
    	setIntervaloValorPrevistoFim("");
    	setDataRealizacaoCreditoDebitoInicio("");
    	setDataRealizacaoCreditoDebitoFim("");
    	setIntervaloValorRealizadoInicio("");
    	setIntervaloValorRealizadoFim("");
    	setAviso("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
    	setIdAvisoBancario("");

	}
	
}

