package gcom.arrecadacao.bean;

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.MovimentoCartaoRejeita;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Sávio Luiz
 * @created 01 de Fevereiro de 2006 [UC0259] - Processar Pagamento com Código de
 *          Barras
 */
public class PagamentoHelperCodigoBarras implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public PagamentoHelperCodigoBarras() {
	}

	private String indicadorAceitacaoRegistro;

	private String descricaoOcorrencia;

	private Collection colecaoPagamentos;
	
	private Collection<Devolucao> colecaoDevolucao;
	
	private Collection<ProcessarPagamentoParcialContaHelper> colecaoProcessarPagamentoParcialContaHelper;
	
	//CARTÃO DE CRÉDITO
	private MovimentoCartaoRejeita movimentoCartaoRejeita;

	public Collection<ProcessarPagamentoParcialContaHelper> getColecaoProcessarPagamentoParcialContaHelper() {
		return colecaoProcessarPagamentoParcialContaHelper;
	}

	public void setColecaoProcessarPagamentoParcialContaHelper(
			Collection<ProcessarPagamentoParcialContaHelper> colecaoProcessarPagamentoParcialContaHelper) {
		this.colecaoProcessarPagamentoParcialContaHelper = colecaoProcessarPagamentoParcialContaHelper;
	}

	public Collection getColecaoPagamentos() {
		return colecaoPagamentos;
	}

	public void setColecaoPagamentos(Collection colecaoPagamentos) {
		this.colecaoPagamentos = colecaoPagamentos;
	}

	public String getDescricaoOcorrencia() {
		return descricaoOcorrencia;
	}

	public void setDescricaoOcorrencia(String descricaoOcorrencia) {
		this.descricaoOcorrencia = descricaoOcorrencia;
	}

	public String getIndicadorAceitacaoRegistro() {
		return indicadorAceitacaoRegistro;
	}

	public void setIndicadorAceitacaoRegistro(String indicadorAceitacaoRegistro) {
		this.indicadorAceitacaoRegistro = indicadorAceitacaoRegistro;
	}

	public Collection<Devolucao> getColecaoDevolucao() {
		return colecaoDevolucao;
	}

	public void setColecaoDevolucao(Collection<Devolucao> colecaoDevolucao) {
		this.colecaoDevolucao = colecaoDevolucao;
	}

	public MovimentoCartaoRejeita getMovimentoCartaoRejeita() {
		return movimentoCartaoRejeita;
	}

	public void setMovimentoCartaoRejeita(
			MovimentoCartaoRejeita movimentoCartaoRejeita) {
		this.movimentoCartaoRejeita = movimentoCartaoRejeita;
	}
}
