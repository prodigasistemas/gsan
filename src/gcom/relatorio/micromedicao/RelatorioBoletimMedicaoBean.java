package gcom.relatorio.micromedicao;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

/**
 * classe responsável por criar o relatório de Boletim de Medição
 * 
 * [UC1054] - Gerar Relatório Boletim de Medição
 * 
 * @author Hugo Leonardo
 *
 * @date 04/08/2010
 */
public class RelatorioBoletimMedicaoBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String gerenciaRegional;
	private String empresa;
	private String numeroContrato;
	private String localidade;
	private String itemCodigo;
	private String itemDescricao;
	private Integer quantidade;
	private BigDecimal valor;
	
	public RelatorioBoletimMedicaoBean(){
		
	}

	public RelatorioBoletimMedicaoBean(String gerenciaRegional, String empresa, 
			String numeroContrato, String localidade, 
			String itemCodigo, String itemDescricao, 
			Integer quantidade, BigDecimal valor) {
		
		this.gerenciaRegional = gerenciaRegional;
		this.empresa = empresa;
		this.numeroContrato = numeroContrato;
		this.localidade = localidade;
		this.itemCodigo = itemCodigo;
		this.itemDescricao = itemDescricao;
		this.quantidade = quantidade;
		this.valor = valor;
	}

	
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getItemCodigo() {
		return itemCodigo;
	}

	public void setItemCodigo(String itemCodigo) {
		this.itemCodigo = itemCodigo;
	}

	public String getItemDescricao() {
		return itemDescricao;
	}

	public void setItemDescricao(String itemDescricao) {
		this.itemDescricao = itemDescricao;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
}
