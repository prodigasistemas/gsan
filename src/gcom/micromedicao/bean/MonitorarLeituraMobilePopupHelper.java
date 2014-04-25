package gcom.micromedicao.bean;

import gcom.micromedicao.consumo.ConsumoHistorico;

import java.io.Serializable;
import java.util.Collection;

/**
 *[UC0932] Monitorar Leituras Transmitidas
 *
 * Classe de apoio para o caso de uso acima
 * 
 * @author bruno
 *
 */

public class MonitorarLeituraMobilePopupHelper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String inscricao;
	private String idImovel;
	private String sequencialRota;
	private String leituraAnterior;
	private String leituraAtual;
	private String idAnormalidade;
	private String dtLeitura;
	private String dtRecebimento;
	private String icEmissaoConta;
	private String motivoNaoEmissao;	
	private String dadosCliente;
	private Collection<ConsumoHistorico> colConsumos;
	
	public String getDtLeitura() {
		return dtLeitura;
	}
	public void setDtLeitura(String dtLeitura) {
		this.dtLeitura = dtLeitura;
	}
	public String getDtRecebimento() {
		return dtRecebimento;
	}
	public void setDtRecebimento(String dtRecebimento) {
		this.dtRecebimento = dtRecebimento;
	}
	public String getIcEmissaoConta() {
		return icEmissaoConta;
	}
	public void setIcEmissaoConta(String icEmissaoConta) {
		this.icEmissaoConta = icEmissaoConta;
	}
	public String getIdAnormalidade() {
		return idAnormalidade;
	}
	public void setIdAnormalidade(String idAnormalidade) {
		this.idAnormalidade = idAnormalidade;
	}
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
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
	public String getMotivoNaoEmissao() {
		return motivoNaoEmissao;
	}
	public void setMotivoNaoEmissao(String motivoNaoEmissao) {
		this.motivoNaoEmissao = motivoNaoEmissao;
	}
	public String getSequencialRota() {
		return sequencialRota;
	}
	public void setSequencialRota(String sequencialRota) {
		this.sequencialRota = sequencialRota;
	}
	public String getDadosCliente() {
		return dadosCliente;
	}
	public void setDadosCliente(String dadosCliente) {
		this.dadosCliente = dadosCliente;
	}
	public Collection<ConsumoHistorico> getColConsumos() {
		return colConsumos;
	}
	public void setColConsumos(Collection<ConsumoHistorico> colConsumos) {
		this.colConsumos = colConsumos;
	}					

	
	
}
