package gcom.gui.cadastro;

import java.sql.Date;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * 
 * @author Arthur Carvalho
 * @date 08/05/2008
 */

public class AtualizarEmpresaActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String id;

	private String descricao;

	private String descricaoAbreviada;

	private Short indicadorUso;

	private Date ultimaAlteracao;

	private String email;

	private Short indicadorEmpresaPrincipal;

	private String indicadorEmpresaCobranca;

	private String dataInicioContratoCobranca;
	
	private String dataFimContratoCobranca;

	private String percentualPagamento;
	
	private Short indicadorLeitura;
	
	private String quantidadeMinimaContas;
    
	private String percentualDaFaixa;
	
	private String percentualDaFaixaInformado;

	public Short getIndicadorLeitura() {
		return indicadorLeitura;
	}

	public void setIndicadorLeitura(Short indicadorLeitura) {
		this.indicadorLeitura = indicadorLeitura;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Short getIndicadorEmpresaPrincipal() {
		return indicadorEmpresaPrincipal;
	}

	public void setIndicadorEmpresaPrincipal(Short indicadorEmpresaPrincipal) {
		this.indicadorEmpresaPrincipal = indicadorEmpresaPrincipal;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getDataInicioContratoCobranca() {
		return dataInicioContratoCobranca;
	}

	public void setDataInicioContratoCobranca(String dataInicioContratoCobranca) {
		this.dataInicioContratoCobranca = dataInicioContratoCobranca;
	}

	public String getIndicadorEmpresaCobranca() {
		return indicadorEmpresaCobranca;
	}

	public void setIndicadorEmpresaCobranca(String indicadorEmpresaCobranca) {
		this.indicadorEmpresaCobranca = indicadorEmpresaCobranca;
	}

	public String getPercentualPagamento() {
		return percentualPagamento;
	}

	public void setPercentualPagamento(String percentualPagamento) {
		this.percentualPagamento = percentualPagamento;
	}

	public String getDataFimContratoCobranca() {
		return dataFimContratoCobranca;
	}

	public void setDataFimContratoCobranca(String dataFimContratoCobranca) {
		this.dataFimContratoCobranca = dataFimContratoCobranca;
	}

	public String getPercentualDaFaixa() {
		return percentualDaFaixa;
	}

	public void setPercentualDaFaixa(String percentualDaFaixa) {
		this.percentualDaFaixa = percentualDaFaixa;
	}

	public String getQuantidadeMinimaContas() {
		return quantidadeMinimaContas;
	}

	public void setQuantidadeMinimaContas(String quantidadeMinimaContas) {
		this.quantidadeMinimaContas = quantidadeMinimaContas;
	}

	public String getPercentualDaFaixaInformado() {
		return percentualDaFaixaInformado;
	}

	public void setPercentualDaFaixaInformado(String percentualDaFaixaInformado) {
		this.percentualDaFaixaInformado = percentualDaFaixaInformado;
	}
	
}
