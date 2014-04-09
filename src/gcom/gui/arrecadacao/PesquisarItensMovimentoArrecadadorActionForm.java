package gcom.gui.arrecadacao;

import java.util.Collection;

import org.apache.struts.action.ActionForm;

/**
 * @author Vivianne Sousa
 * @date 05/12/2006
 */
public class PesquisarItensMovimentoArrecadadorActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	
	private String matriculaImovel;
	
	private String inscricaoImovel;
	
	private String descricaoOcorrencia;
	
	private String indicadorAceitacao;
	
	private String idArrecadadorMovimento;
	
	private String descricaoFormaArrecadacao;
	
	private String formaArrecadacao;
	
	private String indicadorDiferencaValorMovimentoValorPagamento;
	
	private Collection colecaoArrecadadorMovimentoItem;
	
	private String valorMovimento;
	
	private String valorPagamento;
	
	private String nomeBanco;
	
	private String nomeAgencia;

	public String getValorMovimento() {
		return valorMovimento;
	}

	public void setValorMovimento(String valorMovimento) {
		this.valorMovimento = valorMovimento;
	}

	public String getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(String valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public String getIndicadorDiferencaValorMovimentoValorPagamento() {
		return indicadorDiferencaValorMovimentoValorPagamento;
	}

	public void setIndicadorDiferencaValorMovimentoValorPagamento(
			String indicadorDiferencaValorMovimentoValorPagamento) {
		this.indicadorDiferencaValorMovimentoValorPagamento = indicadorDiferencaValorMovimentoValorPagamento;
	}

	public String getFormaArrecadacao() {
		return formaArrecadacao;
	}

	public void setFormaArrecadacao(String formaArrecadacao) {
		this.formaArrecadacao = formaArrecadacao;
	}

	public String getIdArrecadadorMovimento() {
		return idArrecadadorMovimento;
	}

	public void setIdArrecadadorMovimento(String idArrecadadorMovimento) {
		this.idArrecadadorMovimento = idArrecadadorMovimento;
	}

	public String getDescricaoOcorrencia() {
		return descricaoOcorrencia;
	}

	public void setDescricaoOcorrencia(String descricaoOcorrencia) {
		this.descricaoOcorrencia = descricaoOcorrencia;
	}

	public String getIndicadorAceitacao() {
		return indicadorAceitacao;
	}

	public void setIndicadorAceitacao(String indicadorAceitacao) {
		this.indicadorAceitacao = indicadorAceitacao;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public Collection getColecaoArrecadadorMovimentoItem() {
		return colecaoArrecadadorMovimentoItem;
	}

	public void setColecaoArrecadadorMovimentoItem(
			Collection colecaoArrecadadorMovimentoItem) {
		this.colecaoArrecadadorMovimentoItem = colecaoArrecadadorMovimentoItem;
	}

	public String getDescricaoFormaArrecadacao() {
		return descricaoFormaArrecadacao;
	}

	public void setDescricaoFormaArrecadacao(String descricaoFormaArrecadacao) {
		this.descricaoFormaArrecadacao = descricaoFormaArrecadacao;
	}

	public String getNomeAgencia() {
		return nomeAgencia;
	}

	public void setNomeAgencia(String nomeAgencia) {
		this.nomeAgencia = nomeAgencia;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}
	

	
}
