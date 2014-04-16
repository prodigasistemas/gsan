package gcom.gui.faturamento.credito;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


/**
 * [UC0194] Crédito a Realizar
 * Permite inserir um crédito a realizar
 * @author Roberta Costa
 * @since  11/01/2006 
 */
public class InserirCreditoARealizarActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String matriculaImovel;
	private String inscricaoImovel;
	private String nomeCliente;
	private String situacaoAgua;
	private String situacaoEsgoto;
	private String registroAtendimento;
	private String nomeRegistroAtendimento;
	private String ordemServico;
	private String nomeOrdemServico;
	private String tipoCredito;
	
	private String origemCredito;
	private String numeroPrestacoes;
	private String valorCredito;
	private String referencia;
	
	/**
	 * @return Returns the inscricaoImovel.
	 */
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	/**
	 * @param inscricaoImovel The inscricaoImovel to set.
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	/**
	 * @return Returns the matriculaImovel.
	 */
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	/**
	 * @param matriculaImovel The matriculaImovel to set.
	 */
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	/**
	 * @return Returns the nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}
	/**
	 * @param nomeCliente The nomeCliente to set.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	/**
	 * @return Returns the numeroPrestacoes.
	 */
	public String getNumeroPrestacoes() {
		return numeroPrestacoes;
	}
	/**
	 * @param numeroPrestacoes The numeroPrestacoes to set.
	 */
	public void setNumeroPrestacoes(String numeroPrestacoes) {
		this.numeroPrestacoes = numeroPrestacoes;
	}
	/**
	 * @return Returns the ordemServico.
	 */
	public String getOrdemServico() {
		return ordemServico;
	}
	/**
	 * @param ordemServico The ordemServico to set.
	 */
	public void setOrdemServico(String ordemServico) {
		this.ordemServico = ordemServico;
	}
	/**
	 * @return Returns the origemCredito.
	 */
	public String getOrigemCredito() {
		return origemCredito;
	}
	/**
	 * @param origemCredito The origemCredito to set.
	 */
	public void setOrigemCredito(String origemCredito) {
		this.origemCredito = origemCredito;
	}
	/**
	 * @return Returns the registroAtendimento.
	 */
	public String getRegistroAtendimento() {
		return registroAtendimento;
	}
	/**
	 * @param registroAtendimento The registroAtendimento to set.
	 */
	public void setRegistroAtendimento(String registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}
	/**
	 * @return Returns the situacaoAgua.
	 */
	public String getSituacaoAgua() {
		return situacaoAgua;
	}
	/**
	 * @param situacaoAgua The situacaoAgua to set.
	 */
	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}
	/**
	 * @return Returns the situacaoEsgoto.
	 */
	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}
	/**
	 * @param situacaoEsgoto The situacaoEsgoto to set.
	 */
	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}
	/**
	 * @return Returns the tipoCredito.
	 */
	public String getTipoCredito() {
		return tipoCredito;
	}
	/**
	 * @param tipoCredito The tipoCredito to set.
	 */
	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}
	/**
	 * @return Returns the valorCredito.
	 */
	public String getValorCredito() {
		return valorCredito;
	}
	/**
	 * @param valorCredito The valorCredito to set.
	 */
	public void setValorCredito(String valorCredito) {
		this.valorCredito = valorCredito;
	}
	
	/**
	 * @return Retorna o campo referencia.
	 */
	public String getReferencia() {
		return referencia;
	}
	/**
	 * @param referencia O referencia a ser setado.
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
		/**@todo: finish this method, this is just the skeleton.*/
		return null;
	}
	
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 */
	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
		matriculaImovel = null;
		inscricaoImovel = null;
		nomeCliente = null;
		situacaoAgua = null;
		situacaoEsgoto= null;
	}
	/**
	 * @return Retorna o campo nomeOrdemServico.
	 */
	public String getNomeOrdemServico() {
		return nomeOrdemServico;
	}
	/**
	 * @param nomeOrdemServico O nomeOrdemServico a ser setado.
	 */
	public void setNomeOrdemServico(String nomeOrdemServico) {
		this.nomeOrdemServico = nomeOrdemServico;
	}
	/**
	 * @return Retorna o campo nomeRegistroAtendimento.
	 */
	public String getNomeRegistroAtendimento() {
		return nomeRegistroAtendimento;
	}
	/**
	 * @param nomeRegistroAtendimento O nomeRegistroAtendimento a ser setado.
	 */
	public void setNomeRegistroAtendimento(String nomeRegistroAtendimento) {
		this.nomeRegistroAtendimento = nomeRegistroAtendimento;
	}
}
