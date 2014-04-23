package gcom.arrecadacao.bean;

import java.util.Collection;


/**
 * Classe que irá auxiliar no formato do retorno da pesquisa dos itens de um
 * determinado movimento do arrecadador
 *
 * @author Raphael Rossiter
 * @date 20/03/2006
 */
public class ArrecadadorMovimentoItemHelper {
	
	private Integer id;
	private String codigoRegistro;
	private String identificacao;
	private String tipoPagamento;
	private String ocorrencia;
	private Short indicadorAceitacao;
	private String descricaoIndicadorAceitacao;
	private RegistroHelperCodigoB registroHelperCodigoB;
	private RegistroHelperCodigoC registroHelperCodigoC;
	private RegistroHelperCodigoE registroHelperCodigoE;
	private RegistroHelperCodigoF registroHelperCodigoF;
	private RegistroHelperCodigoG registroHelperCodigoG;
	
	private Collection colecaoArrecadadorMovimentoItemHelper;
	private String vlMovimento;
	private String vlPagamento;
	private String matriculaImovel;
	
	
	public RegistroHelperCodigoB getRegistroHelperCodigoB() {
		return registroHelperCodigoB;
	}


	public void setRegistroHelperCodigoB(RegistroHelperCodigoB registroHelperCodigoB) {
		this.registroHelperCodigoB = registroHelperCodigoB;
	}


	public RegistroHelperCodigoC getRegistroHelperCodigoC() {
		return registroHelperCodigoC;
	}


	public void setRegistroHelperCodigoC(RegistroHelperCodigoC registroHelperCodigoC) {
		this.registroHelperCodigoC = registroHelperCodigoC;
	}


	public RegistroHelperCodigoE getRegistroHelperCodigoE() {
		return registroHelperCodigoE;
	}


	public void setRegistroHelperCodigoE(RegistroHelperCodigoE registroHelperCodigoE) {
		this.registroHelperCodigoE = registroHelperCodigoE;
	}


	public RegistroHelperCodigoF getRegistroHelperCodigoF() {
		return registroHelperCodigoF;
	}


	public void setRegistroHelperCodigoF(RegistroHelperCodigoF registroHelperCodigoF) {
		this.registroHelperCodigoF = registroHelperCodigoF;
	}


	public RegistroHelperCodigoG getRegistroHelperCodigoG() {
		return registroHelperCodigoG;
	}


	public void setRegistroHelperCodigoG(RegistroHelperCodigoG registroHelperCodigoG) {
		this.registroHelperCodigoG = registroHelperCodigoG;
	}


	public ArrecadadorMovimentoItemHelper() {}
	
	
	public ArrecadadorMovimentoItemHelper(String codigoRegistro, String identificacao, String tipoPagamento, 
			String ocorrencia, Short indicadorAceitacao, String descricaoIndicadorAceitacao) {
		
		this.codigoRegistro = codigoRegistro;
		this.identificacao = identificacao;
		this.tipoPagamento = tipoPagamento;
		this.ocorrencia = ocorrencia;
		this.indicadorAceitacao = indicadorAceitacao;
		this.descricaoIndicadorAceitacao = descricaoIndicadorAceitacao;
	}
	
	public String getCodigoRegistro() {
		return codigoRegistro;
	}
	public void setCodigoRegistro(String codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}
	public String getDescricaoIndicadorAceitacao() {
		return descricaoIndicadorAceitacao;
	}
	public void setDescricaoIndicadorAceitacao(String descricaoIndicadorAceitacao) {
		this.descricaoIndicadorAceitacao = descricaoIndicadorAceitacao;
	}
	public String getIdentificacao() {
		return identificacao;
	}
	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}
	public Short getIndicadorAceitacao() {
		return indicadorAceitacao;
	}
	public void setIndicadorAceitacao(Short indicadorAceitacao) {
		this.indicadorAceitacao = indicadorAceitacao;
	}
	public String getOcorrencia() {
		return ocorrencia;
	}
	public void setOcorrencia(String ocorrencia) {
		this.ocorrencia = ocorrencia;
	}


	public String getTipoPagamento() {
		return tipoPagamento;
	}


	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getVlMovimento() {
		return vlMovimento;
	}


	public void setVlMovimento(String vlMovimento) {
		this.vlMovimento = vlMovimento;
	}


	public String getVlPagamento() {
		return vlPagamento;
	}


	public void setVlPagamento(String vlPagamento) {
		this.vlPagamento = vlPagamento;
	}


	public Collection getColecaoArrecadadorMovimentoItemHelper() {
		return colecaoArrecadadorMovimentoItemHelper;
	}


	public void setColecaoArrecadadorMovimentoItemHelper(
			Collection colecaoArrecadadorMovimentoItemHelper) {
		this.colecaoArrecadadorMovimentoItemHelper = colecaoArrecadadorMovimentoItemHelper;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

}
