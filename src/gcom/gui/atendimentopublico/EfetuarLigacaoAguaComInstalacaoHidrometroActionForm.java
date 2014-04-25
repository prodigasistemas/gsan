package gcom.gui.atendimentopublico;

import org.apache.struts.action.ActionForm;

public class EfetuarLigacaoAguaComInstalacaoHidrometroActionForm extends ActionForm{
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
    
    //Dados da Ligação
    private String diametroLigacao;
    private String dataLigacao;
    private String materialLigacao;
    private String perfilLigacao;
    private String ramalLocalInstalacao;
    private String descHidrometro;
    private String aceitaLacre;
    /*private String posicaoLigacao;
    private String abastecimentoAlternativo;
    private String situacaoAbastecimento;*/
    private String numeroLacre;    
    
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
	private String numeroLacreHidrometro;
    
    //Dados da Geração do Débito
    private String idTipoDebito;
    private String descricaoTipoDebito;
    
    private String valorDebito;
    private String motivoNaoCobranca;
    private String percentualCobranca;
    private String quantidadeParcelas;
    private String valorParcelas;
    
    
    /*
	 * Colocado por Rômulo Aurelio em 02/08/2008
	 * 
	 */
    private String idImovel;
    
    private String permissaoEfetuarLigacaoAguaComInstalacaoHidrometrosemRA;
    
    private String idLigacaoOrigem;
    
    
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
	 * @return Retorna o campo dataLigacao.
	 */
	public String getDataLigacao() {
		return dataLigacao;
	}
	/**
	 * @param dataLigacao O dataLigacao a ser setado.
	 */
	public void setDataLigacao(String dataLigacao) {
		this.dataLigacao = dataLigacao;
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
	 * @return Retorna o campo descHidrometro.
	 */
	public String getDescHidrometro() {
		return descHidrometro;
	}
	/**
	 * @param descHidrometro O descHidrometro a ser setado.
	 */
	public void setDescHidrometro(String descHidrometro) {
		this.descHidrometro = descHidrometro;
	}
	/**
	 * @return Retorna o campo diametroLigacao.
	 */
	public String getDiametroLigacao() {
		return diametroLigacao;
	}
	/**
	 * @param diametroLigacao O diametroLigacao a ser setado.
	 */
	public void setDiametroLigacao(String diametroLigacao) {
		this.diametroLigacao = diametroLigacao;
	}
	/**
	 * @return Retorna o campo materialLigacao.
	 */
	public String getMaterialLigacao() {
		return materialLigacao;
	}
	/**
	 * @param materialLigacao O materialLigacao a ser setado.
	 */
	public void setMaterialLigacao(String materialLigacao) {
		this.materialLigacao = materialLigacao;
	}
	/**
	 * @return Retorna o campo perfilLigacao.
	 */
	public String getPerfilLigacao() {
		return perfilLigacao;
	}
	/**
	 * @param perfilLigacao O perfilLigacao a ser setado.
	 */
	public void setPerfilLigacao(String perfilLigacao) {
		this.perfilLigacao = perfilLigacao;
	}
	public String getVeioEncerrarOS() {
		return veioEncerrarOS;
	}
	public void setVeioEncerrarOS(String veioEncerrarOS) {
		this.veioEncerrarOS = veioEncerrarOS;
	}
	public String getRamalLocalInstalacao() {
		return ramalLocalInstalacao;
	}
	public void setRamalLocalInstalacao(String ramalLocalInstalacao) {
		this.ramalLocalInstalacao = ramalLocalInstalacao;
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
	 * @return Retorna o campo aceitaLacre.
	 */
	public String getAceitaLacre() {
		return aceitaLacre;
	}
	/**
	 * @param aceitaLacre O aceitaLacre a ser setado.
	 */
	public void setAceitaLacre(String aceitaLacre) {
		this.aceitaLacre = aceitaLacre;
	}
	/**
	 * @return Retorna o campo numeroLacre.
	 */
	public String getNumeroLacre() {
		return numeroLacre;
	}
	/**
	 * @param numeroLacre O numeroLacre a ser setado.
	 */
	public void setNumeroLacre(String numeroLacre) {
		this.numeroLacre = numeroLacre;
	}
	
	public String getAlteracaoValor() {
		return alteracaoValor;
	}
	public void setAlteracaoValor(String alteracaoValor) {
		this.alteracaoValor = alteracaoValor;
	}
	/**
	 * @return Retorna o campo abastecimentoAlternativo.
	 */
	/*public String getAbastecimentoAlternativo() {
		return abastecimentoAlternativo;
	}*/
	/**
	 * @param abastecimentoAlternativo O abastecimentoAlternativo a ser setado.
	 */
	/*public void setAbastecimentoAlternativo(String abastecimentoAlternativo) {
		this.abastecimentoAlternativo = abastecimentoAlternativo;
	}*/
	/**
	 * @return Retorna o campo numeroLacreHidrometro.
	 */
	public String getNumeroLacreHidrometro() {
		return numeroLacreHidrometro;
	}
	/**
	 * @param numeroLacreHidrometro O numeroLacreHidrometro a ser setado.
	 */
	public void setNumeroLacreHidrometro(String numeroLacreHidrometro) {
		this.numeroLacreHidrometro = numeroLacreHidrometro;
	}
	/**
	 * @return Retorna o campo posicaoLigacao.
	 */
	/*public String getPosicaoLigacao() {
		return posicaoLigacao;
	}*/
	/**
	 * @param posicaoLigacao O posicaoLigacao a ser setado.
	 */
	/*public void setPosicaoLigacao(String posicaoLigacao) {
		this.posicaoLigacao = posicaoLigacao;
	}*/
	/**
	 * @return Retorna o campo situacaoAbastecimento.
	 */
	/*public String getSituacaoAbastecimento() {
		return situacaoAbastecimento;
	}*/
	/**
	 * @param situacaoAbastecimento O situacaoAbastecimento a ser setado.
	 */
	/*public void setSituacaoAbastecimento(String situacaoAbastecimento) {
		this.situacaoAbastecimento = situacaoAbastecimento;
	}*/
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
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	public String getIdLigacaoOrigem() {
		return idLigacaoOrigem;
	}
	public void setIdLigacaoOrigem(String idLigacaoOrigem) {
		this.idLigacaoOrigem = idLigacaoOrigem;
	}
	
	public String getPermissaoEfetuarLigacaoAguaComInstalacaoHidrometrosemRA() {
		return permissaoEfetuarLigacaoAguaComInstalacaoHidrometrosemRA;
	}
	public void setPermissaoEfetuarLigacaoAguaComInstalacaoHidrometrosemRA(
			String permissaoEfetuarLigacaoAguaComInstalacaoHidrometrosemRA) {
		this.permissaoEfetuarLigacaoAguaComInstalacaoHidrometrosemRA = permissaoEfetuarLigacaoAguaComInstalacaoHidrometrosemRA;
	}
	
	

}
