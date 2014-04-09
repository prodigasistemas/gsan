package gcom.gui.atendimentopublico;

import org.apache.struts.action.ActionForm;

public class EfetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String idOrdemServico;
	private String nomeOrdemServico;
	
	//Imóvel
    private String matriculaImovel;
    private String inscricaoImovel;
    private String clienteUsuario;
    private String cpfCnpjCliente;
    private String situacaoLigacaoAgua;
    private String situacaoLigacaoEsgoto;
    
    //Dados do Restabelecimento
    private String dataRestabelecimento; 
    
    private String veioEncerrarOS;
    
    // Dados do Hidrômetro
    private String idLocalArmazenagem;
    private String numeroHidrometro;
    private String dataInstalacao;
    private String localInstalacao;
    private String protecao;
    private String leituraInstalacao;
    private String numeroSelo;
	private String situacaoCavalete;
    
    //Dados da Geração do Débito
    private String idTipoDebito;
    private String descricaoTipoDebito;
    
    private String valorDebito;
    private String motivoNaoCobranca;
    private String percentualCobranca;
    private String quantidadeParcelas;
    private String valorParcelas;
    
    /*
	 * Colocado por Raphael Rossiter em 18/04/2007
	 * [FS0013 - Alteração de Valor]
	 */
    private String alteracaoValor;
    
    
	/**
	 * @return Retorna o campo clienteUsuario.
	 */
	public String getClienteUsuario() {
		return clienteUsuario;
	}
	/**
	 * @param clienteUsuario O clienteUsuario a ser setado.
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
	 * @param cpfCnpjCliente O cpfCnpjCliente a ser setado.
	 */
	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}
	/**
	 * @return Retorna o campo dataInstalacao.
	 */
	public String getDataInstalacao() {
		return dataInstalacao;
	}
	/**
	 * @param dataInstalacao O dataInstalacao a ser setado.
	 */
	public void setDataInstalacao(String dataInstalacao) {
		this.dataInstalacao = dataInstalacao;
	}
	/**
	 * @return Retorna o campo dataRestabelecimento.
	 */
	public String getDataRestabelecimento() {
		return dataRestabelecimento;
	}
	/**
	 * @param dataRestabelecimento O dataRestabelecimento a ser setado.
	 */
	public void setDataRestabelecimento(String dataRestabelecimento) {
		this.dataRestabelecimento = dataRestabelecimento;
	}
	/**
	 * @return Retorna o campo descricaoTipoDebito.
	 */
	public String getDescricaoTipoDebito() {
		return descricaoTipoDebito;
	}
	/**
	 * @param descricaoTipoDebito O descricaoTipoDebito a ser setado.
	 */
	public void setDescricaoTipoDebito(String descricaoTipoDebito) {
		this.descricaoTipoDebito = descricaoTipoDebito;
	}
	/**
	 * @return Retorna o campo idOrdemServico.
	 */
	public String getIdOrdemServico() {
		return idOrdemServico;
	}
	/**
	 * @param idOrdemServico O idOrdemServico a ser setado.
	 */
	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	/**
	 * @return Retorna o campo idTipoDebito.
	 */
	public String getIdTipoDebito() {
		return idTipoDebito;
	}
	/**
	 * @param idTipoDebito O idTipoDebito a ser setado.
	 */
	public void setIdTipoDebito(String idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}
	/**
	 * @return Retorna o campo inscricaoImovel.
	 */
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	/**
	 * @param inscricaoImovel O inscricaoImovel a ser setado.
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	/**
	 * @return Retorna o campo leituraInstalacao.
	 */
	public String getLeituraInstalacao() {
		return leituraInstalacao;
	}
	/**
	 * @param leituraInstalacao O leituraInstalacao a ser setado.
	 */
	public void setLeituraInstalacao(String leituraInstalacao) {
		this.leituraInstalacao = leituraInstalacao;
	}
	/**
	 * @return Retorna o campo localInstalacao.
	 */
	public String getLocalInstalacao() {
		return localInstalacao;
	}
	/**
	 * @param localInstalacao O localInstalacao a ser setado.
	 */
	public void setLocalInstalacao(String localInstalacao) {
		this.localInstalacao = localInstalacao;
	}
	/**
	 * @return Retorna o campo matriculaImovel.
	 */
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	/**
	 * @param matriculaImovel O matriculaImovel a ser setado.
	 */
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	/**
	 * @return Retorna o campo motivoNaoCobranca.
	 */
	public String getMotivoNaoCobranca() {
		return motivoNaoCobranca;
	}
	/**
	 * @param motivoNaoCobranca O motivoNaoCobranca a ser setado.
	 */
	public void setMotivoNaoCobranca(String motivoNaoCobranca) {
		this.motivoNaoCobranca = motivoNaoCobranca;
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
	 * @return Retorna o campo numeroHidrometro.
	 */
	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}
	/**
	 * @param numeroHidrometro O numeroHidrometro a ser setado.
	 */
	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}
	/**
	 * @return Retorna o campo numeroSelo.
	 */
	public String getNumeroSelo() {
		return numeroSelo;
	}
	/**
	 * @param numeroSelo O numeroSelo a ser setado.
	 */
	public void setNumeroSelo(String numeroSelo) {
		this.numeroSelo = numeroSelo;
	}
	/**
	 * @return Retorna o campo percentualCobranca.
	 */
	public String getPercentualCobranca() {
		return percentualCobranca;
	}
	/**
	 * @param percentualCobranca O percentualCobranca a ser setado.
	 */
	public void setPercentualCobranca(String percentualCobranca) {
		this.percentualCobranca = percentualCobranca;
	}
	/**
	 * @return Retorna o campo protecao.
	 */
	public String getProtecao() {
		return protecao;
	}
	/**
	 * @param protecao O protecao a ser setado.
	 */
	public void setProtecao(String protecao) {
		this.protecao = protecao;
	}
	/**
	 * @return Retorna o campo quantidadeParcelas.
	 */
	public String getQuantidadeParcelas() {
		return quantidadeParcelas;
	}
	/**
	 * @param quantidadeParcelas O quantidadeParcelas a ser setado.
	 */
	public void setQuantidadeParcelas(String quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}
	/**
	 * @return Retorna o campo situacaoCavalete.
	 */
	public String getSituacaoCavalete() {
		return situacaoCavalete;
	}
	/**
	 * @param situacaoCavalete O situacaoCavalete a ser setado.
	 */
	public void setSituacaoCavalete(String situacaoCavalete) {
		this.situacaoCavalete = situacaoCavalete;
	}
	/**
	 * @return Retorna o campo situacaoLigacaoAgua.
	 */
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	/**
	 * @param situacaoLigacaoAgua O situacaoLigacaoAgua a ser setado.
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
	 * @param situacaoLigacaoEsgoto O situacaoLigacaoEsgoto a ser setado.
	 */
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}
	/**
	 * @return Retorna o campo valorDebito.
	 */
	public String getValorDebito() {
		return valorDebito;
	}
	/**
	 * @param valorDebito O valorDebito a ser setado.
	 */
	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}
	/**
	 * @return Retorna o campo valorParcelas.
	 */
	public String getValorParcelas() {
		return valorParcelas;
	}
	/**
	 * @param valorParcelas O valorParcelas a ser setado.
	 */
	public void setValorParcelas(String valorParcelas) {
		this.valorParcelas = valorParcelas;
	}
	/**
	 * @return Retorna o campo veioEncerrarOS.
	 */
	public String getVeioEncerrarOS() {
		return veioEncerrarOS;
	}
	/**
	 * @param veioEncerrarOS O veioEncerrarOS a ser setado.
	 */
	public void setVeioEncerrarOS(String veioEncerrarOS) {
		this.veioEncerrarOS = veioEncerrarOS;
	}
	public String getAlteracaoValor() {
		return alteracaoValor;
	}
	public void setAlteracaoValor(String alteracaoValor) {
		this.alteracaoValor = alteracaoValor;
	}
	/**
	 * @return Retorna o campo idLocalArmazenagem.
	 */
	public String getIdLocalArmazenagem() {
		return idLocalArmazenagem;
	}
	/**
	 * @param idLocalArmazenagem O idLocalArmazenagem a ser setado.
	 */
	public void setIdLocalArmazenagem(String idLocalArmazenagem) {
		this.idLocalArmazenagem = idLocalArmazenagem;
	}
    
    
}
