package gcom.gui.portal;

import java.util.Collection;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;

public class EmitirExtratoDebitosActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String idImovel;
	
	private String inscricaoImovel;
	
	private String nomeClienteUsuarioImovel;
	
	private String descricaoLigacaoAguaSituacaoImovel;
	
	private String descricaoLigacaoEsgotoSituacaoImovel;
	
	private String indicadorIncluirAcrescimosImpontualidade;
	
	private String indicadorTaxaCobranca;
	
	private String idsConta;
	private String idsDebito;
	private String idsCredito;
	private String idsGuia;
	private String idsParcelamento;

	private String cpfCnpj;
	
	private String idLigacaoAguaSituacaoImovel;
	private String idLigacaoEsgotoSituacaoImovel;
	private String indicadorRestabelecimento;
	private String valorDescontoPagamentoAVista;
	private String valorPagamentoAVista;
	
	private String totalDebitoSelecionado = "0,00";
	private String totalDebitoAtualizadoSelecionado = "0,00";
	
	private String teste;
	
	private String quantidadeContas;

	private String valorTotalContas;

	public String getTeste() {
		return teste;
	}

	public void setTeste(String teste) {
		this.teste = teste;
	}

	public String getTotalDebitoAtualizadoSelecionado() {
		return totalDebitoAtualizadoSelecionado;
	}

	public void setTotalDebitoAtualizadoSelecionado(
			String totalDebitoAtualizadoSelecionado) {
		this.totalDebitoAtualizadoSelecionado = totalDebitoAtualizadoSelecionado;
	}

	public String getTotalDebitoSelecionado() {
		return totalDebitoSelecionado;
	}

	public void setTotalDebitoSelecionado(String totalDebitoSelecionado) {
		this.totalDebitoSelecionado = totalDebitoSelecionado;
	}

	public String getDescricaoLigacaoAguaSituacaoImovel() {
		return descricaoLigacaoAguaSituacaoImovel;
	}

	public void setDescricaoLigacaoAguaSituacaoImovel(
			String descricaoLigacaoAguaSituacaoImovel) {
		this.descricaoLigacaoAguaSituacaoImovel = descricaoLigacaoAguaSituacaoImovel;
	}

	public String getDescricaoLigacaoEsgotoSituacaoImovel() {
		return descricaoLigacaoEsgotoSituacaoImovel;
	}

	public void setDescricaoLigacaoEsgotoSituacaoImovel(
			String descricaoLigacaoEsgotoSituacaoImovel) {
		this.descricaoLigacaoEsgotoSituacaoImovel = descricaoLigacaoEsgotoSituacaoImovel;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getNomeClienteUsuarioImovel() {
		return nomeClienteUsuarioImovel;
	}

	public void setNomeClienteUsuarioImovel(String nomeClienteUsuarioImovel) {
		this.nomeClienteUsuarioImovel = nomeClienteUsuarioImovel;
	}

	public String getIndicadorIncluirAcrescimosImpontualidade() {
		return indicadorIncluirAcrescimosImpontualidade;
	}

	public void setIndicadorIncluirAcrescimosImpontualidade(
			String indicadorIncluirAcrescimosImpontualidade) {
		this.indicadorIncluirAcrescimosImpontualidade = indicadorIncluirAcrescimosImpontualidade;
	}

	public String getIdsConta() {
		return idsConta;
	}

	public void setIdsConta(String idsConta) {
		this.idsConta = idsConta;
	}

	public String getIdsCredito() {
		return idsCredito;
	}

	public void setIdsCredito(String idsCredito) {
		this.idsCredito = idsCredito;
	}

	public String getIdsDebito() {
		return idsDebito;
	}

	public void setIdsDebito(String idsDebito) {
		this.idsDebito = idsDebito;
	}

	public String getIdsGuia() {
		return idsGuia;
	}

	public void setIdsGuia(String idsGuia) {
		this.idsGuia = idsGuia;
	}

	public String getIdsParcelamento() {
		return idsParcelamento;
	}

	public void setIdsParcelamento(String idsParcelamento) {
		this.idsParcelamento = idsParcelamento;
	}

	public String getIndicadorTaxaCobranca() {
		return indicadorTaxaCobranca;
	}

	public void setIndicadorTaxaCobranca(String indicadorTaxaCobranca) {
		this.indicadorTaxaCobranca = indicadorTaxaCobranca;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getIdLigacaoAguaSituacaoImovel() {
		return idLigacaoAguaSituacaoImovel;
	}

	public void setIdLigacaoAguaSituacaoImovel(String idLigacaoAguaSituacaoImovel) {
		this.idLigacaoAguaSituacaoImovel = idLigacaoAguaSituacaoImovel;
	}

	public String getIdLigacaoEsgotoSituacaoImovel() {
		return idLigacaoEsgotoSituacaoImovel;
	}

	public void setIdLigacaoEsgotoSituacaoImovel(
			String idLigacaoEsgotoSituacaoImovel) {
		this.idLigacaoEsgotoSituacaoImovel = idLigacaoEsgotoSituacaoImovel;
	}

	public String getIndicadorRestabelecimento() {
		return indicadorRestabelecimento;
	}

	public void setIndicadorRestabelecimento(String indicadorRestabelecimento) {
		this.indicadorRestabelecimento = indicadorRestabelecimento;
	}

	public String getValorDescontoPagamentoAVista() {
		return valorDescontoPagamentoAVista;
	}

	public void setValorDescontoPagamentoAVista(String valorDescontoPagamentoAVista) {
		this.valorDescontoPagamentoAVista = valorDescontoPagamentoAVista;
	}

	public String getValorPagamentoAVista() {
		return valorPagamentoAVista;
	}

	public void setValorPagamentoAVista(String valorPagamentoAVista) {
		this.valorPagamentoAVista = valorPagamentoAVista;
	}
	
	public String getQuantidadeContas() {
		return quantidadeContas;
	}

	public void setQuantidadeContas(String quantidadeContas) {
		this.quantidadeContas = quantidadeContas;
	}

	public String getValorTotalContas() {
		return valorTotalContas;
	}

	public void setValorTotalContas(String valorTotalContas) {
		this.valorTotalContas = valorTotalContas;
	}

}