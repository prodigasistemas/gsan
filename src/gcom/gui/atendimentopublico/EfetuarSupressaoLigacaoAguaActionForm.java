package gcom.gui.atendimentopublico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author COMPESA
 * @date 14/07/2006
 */
public class EfetuarSupressaoLigacaoAguaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idOrdemServico;

	private String nomeOrdemServico;

	private String matriculaImovel;

	private String inscricaoImovel;

	private String clienteUsuario;

	private String cpfCnpjCliente;

	private String indicadorTipoSupressao;

	private String situacaoLigacaoAgua;

	private String situacaoLigacaoEsgoto;

	private String numeroSeloSupressao;

	private String motivoSupressao;

	private String numeroLeituraSupressao;

	private String tipoSupressao;

	private String dataSupressao;

	private String veioEncerrarOS;

	// Dados da Geração do Débito
	private String idTipoDebito;

	private String descricaoTipoDebito;

	private String valorDebito;

	private String motivoNaoCobranca;

	private String percentualCobranca;

	private String quantidadeParcelas;

	private String valorParcelas;
	
	/*
	 * Colocado por Raphael Rossiter em 20/04/2007
	 * [FS0013 - Alteração de Valor]
	 */
    private String alteracaoValor;

	public String getDescricaoTipoDebito() {
		return descricaoTipoDebito;
	}

	public void setDescricaoTipoDebito(String descricaoTipoDebito) {
		this.descricaoTipoDebito = descricaoTipoDebito;
	}

	public String getIdTipoDebito() {
		return idTipoDebito;
	}

	public void setIdTipoDebito(String idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}

	public String getMotivoNaoCobranca() {
		return motivoNaoCobranca;
	}

	public void setMotivoNaoCobranca(String motivoNaoCobranca) {
		this.motivoNaoCobranca = motivoNaoCobranca;
	}

	public String getPercentualCobranca() {
		return percentualCobranca;
	}

	public void setPercentualCobranca(String percentualCobranca) {
		this.percentualCobranca = percentualCobranca;
	}

	public String getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(String quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public String getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}

	public String getValorParcelas() {
		return valorParcelas;
	}

	public void setValorParcelas(String valorParcelas) {
		this.valorParcelas = valorParcelas;
	}

	public String getVeioEncerrarOS() {
		return veioEncerrarOS;
	}

	public void setVeioEncerrarOS(String veioEncerrarOS) {
		this.veioEncerrarOS = veioEncerrarOS;
	}

	/**
	 * @return Retorna o campo clienteUsuario.
	 */
	public String getClienteUsuario() {
		return clienteUsuario;
	}

	/**
	 * @param clienteUsuario
	 *            O clienteUsuario a ser setado.
	 */
	public void setClienteUsuario(String clienteUsuario) {
		this.clienteUsuario = clienteUsuario;
	}

	/**
	 * @return Retorna o campo cpfCnpjCliente.
	 */
	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}

	/**
	 * @param cpfCnpjCliente
	 *            O cpfCnpjCliente a ser setado.
	 */
	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}

	/**
	 * @return Retorna o campo idOrdemServico.
	 */
	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	/**
	 * @param idOrdemServico
	 *            O idOrdemServico a ser setado.
	 */
	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	/**
	 * @return Retorna o campo inscricaoImovel.
	 */
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	/**
	 * @param inscricaoImovel
	 *            O inscricaoImovel a ser setado.
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	/**
	 * @return Retorna o campo matriculaImovel.
	 */
	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	/**
	 * @param matriculaImovel
	 *            O matriculaImovel a ser setado.
	 */
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	/**
	 * @return Retorna o campo nomeOrdemServico.
	 */
	public String getNomeOrdemServico() {
		return nomeOrdemServico;
	}

	/**
	 * @param nomeOrdemServico
	 *            O nomeOrdemServico a ser setado.
	 */
	public void setNomeOrdemServico(String nomeOrdemServico) {
		this.nomeOrdemServico = nomeOrdemServico;
	}

	/**
	 * @return Retorna o campo numeroLeituraSupressao.
	 */
	public String getNumeroLeituraSupressao() {
		return numeroLeituraSupressao;
	}

	/**
	 * @param numeroLeituraSupressao
	 *            O numeroLeituraSupressao a ser setado.
	 */
	public void setNumeroLeituraSupressao(String numeroLeituraSupressao) {
		this.numeroLeituraSupressao = numeroLeituraSupressao;
	}

	/**
	 * @return Retorna o campo numeroSeloSupressao.
	 */
	public String getNumeroSeloSupressao() {
		return numeroSeloSupressao;
	}

	/**
	 * @param numeroSeloSupressao
	 *            O numeroSeloSupressao a ser setado.
	 */
	public void setNumeroSeloSupressao(String numeroSeloSupressao) {
		this.numeroSeloSupressao = numeroSeloSupressao;
	}

	/**
	 * @return Retorna o campo situacaoLigacaoAgua.
	 */
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	/**
	 * @param situacaoLigacaoAgua
	 *            O situacaoLigacaoAgua a ser setado.
	 */
	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	/**
	 * @return Retorna o campo situacaoLigacaoEsgoto.
	 */
	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	/**
	 * @param situacaoLigacaoEsgoto
	 *            O situacaoLigacaoEsgoto a ser setado.
	 */
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	/**
	 * @return Retorna o campo tipoSupressao.
	 */
	public String getTipoSupressao() {
		return tipoSupressao;
	}

	/**
	 * @param tipoSupressao
	 *            O tipoSupressao a ser setado.
	 */
	public void setTipoSupressao(String tipoSupressao) {
		this.tipoSupressao = tipoSupressao;
	}

	/**
	 * @return Retorna o campo dataSupressao.
	 */
	public String getDataSupressao() {
		return dataSupressao;
	}

	/**
	 * @param dataSupressao
	 *            O dataSupressao a ser setado.
	 */
	public void setDataSupressao(String dataSupressao) {
		this.dataSupressao = dataSupressao;
	}

	/**
	 * @return Retorna o campo motivoSupressao.
	 */
	public String getMotivoSupressao() {
		return motivoSupressao;
	}

	/**
	 * @param motivoSupressao
	 *            O motivoSupressao a ser setado.
	 */
	public void setMotivoSupressao(String motivoSupressao) {
		this.motivoSupressao = motivoSupressao;
	}

	/**
	 * @return Retorna o campo indicadorTipoSupressao.
	 */
	public String getIndicadorTipoSupressao() {
		return indicadorTipoSupressao;
	}

	/**
	 * @param indicadorTipoSupressao
	 *            O indicadorTipoSupressao a ser setado.
	 */
	public void setIndicadorTipoSupressao(String indicadorTipoSupressao) {
		this.indicadorTipoSupressao = indicadorTipoSupressao;
	}

	public String getAlteracaoValor() {
		return alteracaoValor;
	}

	public void setAlteracaoValor(String alteracaoValor) {
		this.alteracaoValor = alteracaoValor;
	}
	
	

}
