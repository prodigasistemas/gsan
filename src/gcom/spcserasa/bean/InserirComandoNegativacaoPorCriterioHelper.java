package gcom.spcserasa.bean;

import gcom.cobranca.NegativacaoComando;
import gcom.cobranca.NegativacaoCriterio;
import gcom.cobranca.NegativacaoCriterioCpfTipo;

import java.util.Collection;


/**
 * Classe responsável por ajudar o caso de uso 
 * [UC065] Inserir Comando de Negativação - Por Critério
 *
 * @author Ana Maria
 * @date 13/12/2007
 */
public class InserirComandoNegativacaoPorCriterioHelper {

	private NegativacaoComando negativacaoComando;
	private NegativacaoCriterio negativacaoCriterio;
	private Collection<NegativacaoCriterioCpfTipo> colecaoNegativacaoCriterioCpfTipo;

	private String[] idsCobrancaSituacaoTipo;
	private String[] idsCobrancaSituacao;
	private String[] idsLigacaoAguaSituacao;
	private String[] idsLigacaoEsgotoSituacao;
	private String[] idsSubcategoria;
	private String[] idsPerfilImovel;
	private String[] idsTipoCliente;
	private String[] idsCobrancaGrupo;
	private String[] idsGerenciaRegional;
	private String[] idsUnidadeNegocio;
	private String[] idsEloPolo;
	private String indicadorBaixaRenda;
	private String[] idsMotivoRetorno;
	

	public String getIndicadorBaixaRenda() {
		return indicadorBaixaRenda;
	}


	public void setIndicadorBaixaRenda(String indicadorBaixaRenda) {
		this.indicadorBaixaRenda = indicadorBaixaRenda;
	}


	/**
	 * @return Retorna o campo colecaoNegativacaoCriterioCpfTipo.
	 */
	public Collection<NegativacaoCriterioCpfTipo> getColecaoNegativacaoCriterioCpfTipo() {
		return colecaoNegativacaoCriterioCpfTipo;
	}


	/**
	 * @param colecaoNegativacaoCriterioCpfTipo O colecaoNegativacaoCriterioCpfTipo a ser setado.
	 */
	public void setColecaoNegativacaoCriterioCpfTipo(
			Collection<NegativacaoCriterioCpfTipo> colecaoNegativacaoCriterioCpfTipo) {
		this.colecaoNegativacaoCriterioCpfTipo = colecaoNegativacaoCriterioCpfTipo;
	}


	/**
	 * @return Retorna o campo idsCobrancaGrupo.
	 */
	public String[] getIdsCobrancaGrupo() {
		return idsCobrancaGrupo;
	}


	/**
	 * @param idsCobrancaGrupo O idsCobrancaGrupo a ser setado.
	 */
	public void setIdsCobrancaGrupo(String[] idsCobrancaGrupo) {
		this.idsCobrancaGrupo = idsCobrancaGrupo;
	}


	/**
	 * @return Retorna o campo idsEloPolo.
	 */
	public String[] getIdsEloPolo() {
		return idsEloPolo;
	}


	/**
	 * @param idsEloPolo O idsEloPolo a ser setado.
	 */
	public void setIdsEloPolo(String[] idsEloPolo) {
		this.idsEloPolo = idsEloPolo;
	}


	/**
	 * @return Retorna o campo idsGerenciaRegional.
	 */
	public String[] getIdsGerenciaRegional() {
		return idsGerenciaRegional;
	}


	/**
	 * @param idsGerenciaRegional O idsGerenciaRegional a ser setado.
	 */
	public void setIdsGerenciaRegional(String[] idsGerenciaRegional) {
		this.idsGerenciaRegional = idsGerenciaRegional;
	}


	/**
	 * @return Retorna o campo idsPerfilImovel.
	 */
	public String[] getIdsPerfilImovel() {
		return idsPerfilImovel;
	}


	/**
	 * @param idsPerfilImovel O idsPerfilImovel a ser setado.
	 */
	public void setIdsPerfilImovel(String[] idsPerfilImovel) {
		this.idsPerfilImovel = idsPerfilImovel;
	}


	/**
	 * @return Retorna o campo idsSubcategoria.
	 */
	public String[] getIdsSubcategoria() {
		return idsSubcategoria;
	}


	/**
	 * @param idsSubcategoria O idsSubcategoria a ser setado.
	 */
	public void setIdsSubcategoria(String[] idsSubcategoria) {
		this.idsSubcategoria = idsSubcategoria;
	}


	/**
	 * @return Retorna o campo idsTipoCliente.
	 */
	public String[] getIdsTipoCliente() {
		return idsTipoCliente;
	}


	/**
	 * @param idsTipoCliente O idsTipoCliente a ser setado.
	 */
	public void setIdsTipoCliente(String[] idsTipoCliente) {
		this.idsTipoCliente = idsTipoCliente;
	}


	/**
	 * @return Retorna o campo idsUnidadeNegocio.
	 */
	public String[] getIdsUnidadeNegocio() {
		return idsUnidadeNegocio;
	}


	/**
	 * @param idsUnidadeNegocio O idsUnidadeNegocio a ser setado.
	 */
	public void setIdsUnidadeNegocio(String[] idsUnidadeNegocio) {
		this.idsUnidadeNegocio = idsUnidadeNegocio;
	}


	/**
	 * @return Retorna o campo negativacaoComando.
	 */
	public NegativacaoComando getNegativacaoComando() {
		return negativacaoComando;
	}


	/**
	 * @param negativacaoComando O negativacaoComando a ser setado.
	 */
	public void setNegativacaoComando(NegativacaoComando negativacaoComando) {
		this.negativacaoComando = negativacaoComando;
	}


	/**
	 * @return Retorna o campo negativacaoCriterio.
	 */
	public NegativacaoCriterio getNegativacaoCriterio() {
		return negativacaoCriterio;
	}


	/**
	 * @param negativacaoCriterio O negativacaoCriterio a ser setado.
	 */
	public void setNegativacaoCriterio(NegativacaoCriterio negativacaoCriterio) {
		this.negativacaoCriterio = negativacaoCriterio;
	}


	/**
	 * @return Retorna o campo idsLigacaoAguaSituacao.
	 */
	public String[] getIdsLigacaoAguaSituacao() {
		return idsLigacaoAguaSituacao;
	}


	/**
	 * @param idsLigacaoAguaSituacao O idsLigacaoAguaSituacao a ser setado.
	 */
	public void setIdsLigacaoAguaSituacao(String[] idsLigacaoAguaSituacao) {
		this.idsLigacaoAguaSituacao = idsLigacaoAguaSituacao;
	}


	/**
	 * @return Retorna o campo idsLigacaoEsgotoSituacao.
	 */
	public String[] getIdsLigacaoEsgotoSituacao() {
		return idsLigacaoEsgotoSituacao;
	}


	/**
	 * @param idsLigacaoEsgotoSituacao O idsLigacaoEsgotoSituacao a ser setado.
	 */
	public void setIdsLigacaoEsgotoSituacao(String[] idsLigacaoEsgotoSituacao) {
		this.idsLigacaoEsgotoSituacao = idsLigacaoEsgotoSituacao;
	}


	public String[] getIdsMotivoRetorno() {
		return idsMotivoRetorno;
	}


	public void setIdsMotivoRetorno(String[] idsMotivoRetorno) {
		this.idsMotivoRetorno = idsMotivoRetorno;
	}


	public String[] getIdsCobrancaSituacao() {
		return idsCobrancaSituacao;
	}


	public void setIdsCobrancaSituacao(String[] idsCobrancaSituacao) {
		this.idsCobrancaSituacao = idsCobrancaSituacao;
	}


	public String[] getIdsCobrancaSituacaoTipo() {
		return idsCobrancaSituacaoTipo;
	}


	public void setIdsCobrancaSituacaoTipo(String[] idsCobrancaSituacaoTipo) {
		this.idsCobrancaSituacaoTipo = idsCobrancaSituacaoTipo;
	}



}
