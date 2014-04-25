package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;
/**
 * Bean responsável de enviar os campos(fields) que serão exibidos no detail do relatório
 * Pagamento para Entidades Beneficentes Analitico.
 * 
 * @author Daniel Alves
 * @created 25 de Janeiro de 2010
 */
public class RelatorioPagamentoEntidadesBeneficentesSinteticoBean implements RelatorioBean{
	
	private String entidadeBeneficente;
	
	private String idEntidadeBeneficente;
				
	private String idTotalizacao;
	
	private String tipoTotalizacao;	
	
	private String descricao;
	
	private Integer quantidade;
		
	private BigDecimal valor;	
	
	public RelatorioPagamentoEntidadesBeneficentesSinteticoBean(){}
	

	public RelatorioPagamentoEntidadesBeneficentesSinteticoBean(
			String idEntidadeBeneficente, String entidadeBeneficente,
			String idTotalizacao, String tipoTotalizacao, String descricao,
			Integer quantidade, BigDecimal valor) {

		this.idEntidadeBeneficente = idEntidadeBeneficente;
		this.entidadeBeneficente = entidadeBeneficente;
		this.idTotalizacao = idTotalizacao;
		this.tipoTotalizacao = tipoTotalizacao;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.valor = valor;
	}

	public String getEntidadeBeneficente() {
		return entidadeBeneficente;
	}

	public void setEntidadeBeneficente(String entidadeBeneficente) {
		this.entidadeBeneficente = entidadeBeneficente;
	}

	public String getIdEntidadeBeneficente() {
		return idEntidadeBeneficente;
	}

	public void setIdEntidadeBeneficente(String idEntidadeBeneficente) {
		this.idEntidadeBeneficente = idEntidadeBeneficente;
	}

	public String getIdTotalizacao() {
		return idTotalizacao;
	}

	public void setIdTotalizacao(String idTotalizacao) {
		this.idTotalizacao = idTotalizacao;
	}

	public String getTipoTotalizacao() {
		return tipoTotalizacao;
	}

	public void setTipoTotalizacao(String tipoTotalizacao) {
		this.tipoTotalizacao = tipoTotalizacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
