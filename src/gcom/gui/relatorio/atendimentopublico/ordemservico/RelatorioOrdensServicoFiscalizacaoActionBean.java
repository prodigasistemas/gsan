package gcom.gui.relatorio.atendimentopublico.ordemservico;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

/**
 * 
 * @author Hugo Azevedo
 * @date 26/05/2011
 * 
 */
public class RelatorioOrdensServicoFiscalizacaoActionBean implements RelatorioBean {
	
	private String imovelMatricula;
	private String clienteNome;
	private BigDecimal valorEnviado;
	private String qtdContas;
	private BigDecimal valorPago;
	private String numeroOS;
	private String descricaoServico;
	private String dataGeracao;
	private String dataEncerramento;
	private String motivoEncerramento;
	private String idUnidadeNegocio;
	private String descUnidadeNegocio;
	private String idGerenciaRegional;
	private String descGerenciaRegional;
	
	
	public RelatorioOrdensServicoFiscalizacaoActionBean(){}
	
	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public String getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public String getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(String dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public String getDescricaoServico() {
		return descricaoServico;
	}

	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}

	public String getImovelMatricula() {
		return imovelMatricula;
	}

	public void setImovelMatricula(String imovelMatricula) {
		this.imovelMatricula = imovelMatricula;
	}

	public String getMotivoEncerramento() {
		return motivoEncerramento;
	}

	public void setMotivoEncerramento(String motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}

	public String getNumeroOS() {
		return numeroOS;
	}

	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}

	public String getQtdContas() {
		return qtdContas;
	}

	public void setQtdContas(String qtdContas) {
		this.qtdContas = qtdContas;
	}

	public BigDecimal getValorEnviado() {
		return valorEnviado;
	}

	public void setValorEnviado(BigDecimal valorEnviado) {
		this.valorEnviado = valorEnviado;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public String getDescGerenciaRegional() {
		return descGerenciaRegional;
	}

	public void setDescGerenciaRegional(String descGerenciaRegional) {
		this.descGerenciaRegional = descGerenciaRegional;
	}

	public String getDescUnidadeNegocio() {
		return descUnidadeNegocio;
	}

	public void setDescUnidadeNegocio(String descUnidadeNegocio) {
		this.descUnidadeNegocio = descUnidadeNegocio;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	
	
	
}