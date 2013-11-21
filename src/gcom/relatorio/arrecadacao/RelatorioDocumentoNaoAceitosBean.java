package gcom.relatorio.arrecadacao;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

/**
 * [UC1215] Gerar Relatório de Documentos Não Aceitos
 * 
 * Classe responsável por emitir o relatório de Documentos não aceitos
 * 
 * @author Mariana Victor
 *
 * @date 21/03/2011
 */
public class RelatorioDocumentoNaoAceitosBean implements RelatorioBean{

	private String tipoDebito;
	private String dataLancamento;
	private String sequencial;
	private String nsa;
	private String formaArrecadacao;
	private String dataPagamento;
	private BigDecimal valor;
	private String arrecadador;
	
	public String getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public String getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public String getFormaArrecadacao() {
		return formaArrecadacao;
	}
	public void setFormaArrecadacao(String formaArrecadacao) {
		this.formaArrecadacao = formaArrecadacao;
	}
	public String getNsa() {
		return nsa;
	}
	public void setNsa(String nsa) {
		this.nsa = nsa;
	}
	public String getSequencial() {
		return sequencial;
	}
	public void setSequencial(String sequencial) {
		this.sequencial = sequencial;
	}
	public String getTipoDebito() {
		return tipoDebito;
	}
	public void setTipoDebito(String tipoDebito) {
		this.tipoDebito = tipoDebito;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getArrecadador() {
		return arrecadador;
	}
	public void setArrecadador(String arrecadador) {
		this.arrecadador = arrecadador;
	}
	
	
}
