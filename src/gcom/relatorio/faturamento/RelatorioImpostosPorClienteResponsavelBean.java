package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0919] gERAR
 * Esta classe representa o Bean para a geraçao de relatório dos impostos por cliente
 * responsável - de acordo com um ano, mês de referência
 * 
 * - o design do relatório padrão é o: relatorioRelacaoImpostosPorClienteResponsavel
 * 
 * @author José Guilherme Macedo Vieira
 * @date 22/06/2009
 */
public class RelatorioImpostosPorClienteResponsavelBean implements RelatorioBean {
	
	//O id e o nome do cliente no formato XXXXX - NOMEDOCLIENTE
	//onde XXXXX é o ID do CLIENTE
	private String clienteIdNome;
	
	private String cnpjCliente;

	//O id do imovel
	private String imovelID;
	
	private String valorFatura;
	
	private String idFatura;
	
	private Integer idImpostoTipo;
	
	private String valorImposto;

	private String descricaoImposto;
		
	private String percentualAliquota;
	
	public RelatorioImpostosPorClienteResponsavelBean() {
	
	}
	
	public String getDescricaoImposto() {
		return descricaoImposto;
	}

	public void setDescricaoImposto(String descricaoImposto) {
		this.descricaoImposto = descricaoImposto;
	}

	public Integer getIdImpostoTipo() {
		return idImpostoTipo;
	}

	public void setIdImpostoTipo(Integer idImpostoTipo) {
		this.idImpostoTipo = idImpostoTipo;
	}

	public String getPercentualAliquota() {
		return percentualAliquota;
	}

	public void setPercentualAliquota(String percentualAliquota) {
		this.percentualAliquota = percentualAliquota;
	}


	public String getValorImposto() {
		return valorImposto;
	}

	public void setValorImposto(String valorImposto) {
		this.valorImposto = valorImposto;
	}

	public String getValorFatura() {
		return valorFatura;
	}
	public void setValorFatura(String valorFatura) {
		this.valorFatura = valorFatura;
	}
	public String getClienteIdNome() {
		return clienteIdNome;
	}
	public void setClienteIdNome(String clienteIdNome) {
		this.clienteIdNome = clienteIdNome;
	}
	public String getImovelID() {
		return imovelID;
	}
	public void setImovelID(String imovelID) {
		this.imovelID = imovelID;
	}

	public String getIdFatura() {
		return idFatura;
	}

	public void setIdFatura(String idFatura) {
		this.idFatura = idFatura;
	}

	public String getCnpjCliente() {
		return cnpjCliente;
	}

	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}
}
