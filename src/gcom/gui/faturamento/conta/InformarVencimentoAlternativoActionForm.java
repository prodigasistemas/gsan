package gcom.gui.faturamento.conta;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InformarVencimentoAlternativoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idImovel;

	private String numeroImovel;

	private String inscricaoImovel;

	private String mesAnoConta;

	private String novoDiaVencimento;

	private String nomeClienteUsuario;

	private String percentualEsgoto;

	private String situacaoAguaConta;

	private String situacaoAguaImovel;

	private String situacaoEsgotoConta;

	private String situacaoEsgotoImovel;

	private String diaVencimentoGrupo;

	private String diaVencimentoAtual;

	private String dataAlteracaoVencimento;
	
	private String diaInicial; 

	private String indicadorVencimentoMesSeguinte;
	
	public String getIndicadorVencimentoMesSeguinte() {
		return indicadorVencimentoMesSeguinte;
	}

	public void setIndicadorVencimentoMesSeguinte(
			String indicadorVencimentoMesSeguinte) {
		this.indicadorVencimentoMesSeguinte = indicadorVencimentoMesSeguinte;
	}

	public String getDiaInicial() {
		return diaInicial;
	}

	public void setDiaInicial(String diaInicial) {
		this.diaInicial = diaInicial;
	}

	public String getDataAlteracaoVencimento() {
		return dataAlteracaoVencimento;
	}

	public void setDataAlteracaoVencimento(String dataAlteracaoVencimento) {
		this.dataAlteracaoVencimento = dataAlteracaoVencimento;
	}

	public String getDiaVencimentoAtual() {
		return diaVencimentoAtual;
	}

	public void setDiaVencimentoAtual(String diaVencimentoAtual) {
		this.diaVencimentoAtual = diaVencimentoAtual;
	}

	public String getDiaVencimentoGrupo() {
		return diaVencimentoGrupo;
	}

	public void setDiaVencimentoGrupo(String diaVencimentoGrupo) {
		this.diaVencimentoGrupo = diaVencimentoGrupo;
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

	public String getMesAnoConta() {
		return mesAnoConta;
	}

	public void setMesAnoConta(String mesAnoConta) {
		this.mesAnoConta = mesAnoConta;
	}

	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}

	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}

	public String getNovoDiaVencimento() {
		return novoDiaVencimento;
	}

	public void setNovoDiaVencimento(String novoDiaVencimento) {
		this.novoDiaVencimento = novoDiaVencimento;
	}

	public String getPercentualEsgoto() {
		return percentualEsgoto;
	}

	public void setPercentualEsgoto(String percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}

	public String getSituacaoAguaConta() {
		return situacaoAguaConta;
	}

	public void setSituacaoAguaConta(String situacaoAguaConta) {
		this.situacaoAguaConta = situacaoAguaConta;
	}

	public String getSituacaoAguaImovel() {
		return situacaoAguaImovel;
	}

	public void setSituacaoAguaImovel(String situacaoAguaImovel) {
		this.situacaoAguaImovel = situacaoAguaImovel;
	}

	public String getSituacaoEsgotoConta() {
		return situacaoEsgotoConta;
	}

	public void setSituacaoEsgotoConta(String situacaoEsgotoConta) {
		this.situacaoEsgotoConta = situacaoEsgotoConta;
	}

	public String getSituacaoEsgotoImovel() {
		return situacaoEsgotoImovel;
	}

	public void setSituacaoEsgotoImovel(String situacaoEsgotoImovel) {
		this.situacaoEsgotoImovel = situacaoEsgotoImovel;
	}

	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		this.idImovel = "";

		this.inscricaoImovel = "";

		this.mesAnoConta = "";

		this.novoDiaVencimento = "";

		this.nomeClienteUsuario = "";

		this.percentualEsgoto = "";

		this.situacaoAguaConta = "";

		this.situacaoAguaImovel = "";

		this.situacaoEsgotoConta = "";

		this.situacaoEsgotoImovel = "";

		this.diaVencimentoGrupo = "";

		this.diaVencimentoAtual = "";

		this.dataAlteracaoVencimento = "";

		this.numeroImovel = "";
		
		this.diaInicial = "";
	}

	public String getNumeroImovel() {
		return numeroImovel;
	}

	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}
}
