package gcom.relatorio.cobranca;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

/**
 * [UC1152] Emissão Boletim Medição Cobrança
 * 
 * Classe responsável por emitir o relatório de Boletim de Medição de Cobrança
 * 
 * @author Mariana Victor
 *
 * @date 21/03/2011
 */
public class RelatorioBoletimMedicaoCobrancaBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String unidadeNegocio;
	
	private String gerenciaRegional;
	
	private String localidade;
	
	private String itemCodigo;
	
	private String itemDescricao;
	
	private BigDecimal quantidade;
	
	private BigDecimal valorUnitario;
	
	private BigDecimal valorItem;
	
	private String totalizacao;
	
	private String dataGeracao;
	
	private String nomeColuna01;
	
	private String nomeColuna02;
	
	private String nomeColuna03;
	
	private String coluna01;
	

	public RelatorioBoletimMedicaoCobrancaBean() {
		super();
	}

	public RelatorioBoletimMedicaoCobrancaBean(String gerenciaRegional, String localidade,
			String itemCodigo, String itemDescricao, BigDecimal quantidade, BigDecimal valorUnitario,
			BigDecimal valorItem, String totalizacao, String dataGeracao, String unidadeNegocio,
			String nomeColuna01, String nomeColuna02, String nomeColuna03, String coluna01) {
		super();
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.itemCodigo = itemCodigo;
		this.itemDescricao = itemDescricao;
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
		this.valorItem = valorItem;
		this.totalizacao = totalizacao;
		this.dataGeracao = dataGeracao;
		this.unidadeNegocio = unidadeNegocio;
		this.nomeColuna01 = nomeColuna01;
		this.nomeColuna02 = nomeColuna02;
		this.nomeColuna03 = nomeColuna03;
		this.coluna01 = coluna01;
		
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

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorItem() {
		return valorItem;
	}

	public void setValorItem(BigDecimal valorItem) {
		this.valorItem = valorItem;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public String getTotalizacao() {
		return totalizacao;
	}

	public void setTotalizacao(String totalizacao) {
		this.totalizacao = totalizacao;
	}

	public String getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(String dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getNomeColuna01() {
		return nomeColuna01;
	}

	public void setNomeColuna01(String nomeColuna01) {
		this.nomeColuna01 = nomeColuna01;
	}

	public String getNomeColuna02() {
		return nomeColuna02;
	}

	public void setNomeColuna02(String nomeColuna02) {
		this.nomeColuna02 = nomeColuna02;
	}

	public String getNomeColuna03() {
		return nomeColuna03;
	}

	public void setNomeColuna03(String nomeColuna03) {
		this.nomeColuna03 = nomeColuna03;
	}

	public String getColuna01() {
		return coluna01;
	}

	public void setColuna01(String coluna01) {
		this.coluna01 = coluna01;
	}

}
