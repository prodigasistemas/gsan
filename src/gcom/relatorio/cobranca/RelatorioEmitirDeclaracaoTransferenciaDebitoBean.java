package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;


/**
 * classe responsável por criar o Relatório de Emissão de Declaracao de Transferencia de Débito 
 * 
 * @author Daniel Alves
 * @created 2 de Março de 2010.
 */
public class RelatorioEmitirDeclaracaoTransferenciaDebitoBean implements RelatorioBean{
	
	private	String clienteUsuarioDestino;		
	private	String clienteUsuarioOrigem;
	private	String valorNovaConta;
	private	String indicadorTipoEmissao;
	private	String diaMesAno;	
	private String municipio;
	
	public String getClienteUsuarioDestino() {
		return clienteUsuarioDestino;
	}
	public void setClienteUsuarioDestino(String clienteUsuarioDestino) {
		this.clienteUsuarioDestino = clienteUsuarioDestino;
	}
	public String getClienteUsuarioOrigem() {
		return clienteUsuarioOrigem;
	}
	public void setClienteUsuarioOrigem(String clienteUsuarioOrigem) {
		this.clienteUsuarioOrigem = clienteUsuarioOrigem;
	}
	public String getValorNovaConta() {
		return valorNovaConta;
	}
	public void setValorNovaConta(String valorNovaConta) {
		this.valorNovaConta = valorNovaConta;
	}
	public String getIndicadorTipoEmissao() {
		return indicadorTipoEmissao;
	}
	public void setIndicadorTipoEmissao(String indicadorTipoEmissao) {
		this.indicadorTipoEmissao = indicadorTipoEmissao;
	}
	public String getDiaMesAno() {
		return diaMesAno;
	}
	public void setDiaMesAno(String diaMesAno) {
		this.diaMesAno = diaMesAno;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
		
}
