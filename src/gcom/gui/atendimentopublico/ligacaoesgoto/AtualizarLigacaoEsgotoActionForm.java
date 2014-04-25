package gcom.gui.atendimentopublico.ligacaoesgoto;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class AtualizarLigacaoEsgotoActionForm extends ActionForm{
	/**
	 * @since 16/05/2007
	 */
	private static final long serialVersionUID = 1L;
	//Ordem de Serviço
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
    private String percentualColeta;
    private String percentualEsgoto;
    private String indicadorCaixaGordura;
    private String indicadorLigacao;
    private String veioEncerrarOS;
    // Controle
    private String redirect = "false";
    private Date dataConcorrencia;
    
    /*
	 * Colocado por Rômulo Aurelio em 02/08/2008
	 * [Permissao Especial atualizar ligacao de esgoto sem ra]
	 */
    
    private String idImovel;
    
    private String idLigacaoOrigem;
    
    private String permissaoAtualizarLigacaoEsgotosemRA;
    
    
    
    
    
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
	/**
	 * @return Retorna o campo percentualColeta.
	 */
	public String getPercentualColeta() {
		return percentualColeta;
	}
	/**
	 * @param percentualColeta O percentualColeta a ser setado.
	 */
	public void setPercentualColeta(String percentualColeta) {
		this.percentualColeta = percentualColeta;
	}
	/**
	 * @return Retorna o campo percentualEsgoto.
	 */
	public String getPercentualEsgoto() {
		return percentualEsgoto;
	}
	/**
	 * @param percentualEsgoto O percentualEsgoto a ser setado.
	 */
	public void setPercentualEsgoto(String percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}
	/**
	 * @return Retorna o campo diametroLigacao.
	 */
	public String getDiametroLigacao() {
		return diametroLigacao;
	}
	
	/**
	 * Reseta dados do imóvel do action form 
	 *
	 * @author Leonardo Regis
	 * @date 21/07/2006
	 *
	 * @param ligacaoEsgotoActionForm
	 */
	public void resetImovel() {
		// Imóvel
		this.setMatriculaImovel("");
		this.setInscricaoImovel("");
		this.setClienteUsuario("");
		this.setCpfCnpjCliente("");
		this.setSituacaoLigacaoAgua("");
		this.setSituacaoLigacaoEsgoto("");
	}
	public String getRedirect() {
		return redirect;
	}
	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}
	public String getIdOrdemServico() {
		return idOrdemServico;
	}
	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	public String getNomeOrdemServico() {
		return nomeOrdemServico;
	}
	public void setNomeOrdemServico(String nomeOrdemServico) {
		this.nomeOrdemServico = nomeOrdemServico;
	}
	public String getVeioEncerrarOS() {
		return veioEncerrarOS;
	}
	public void setVeioEncerrarOS(String veioEncerrarOS) {
		this.veioEncerrarOS = veioEncerrarOS;
	}
	public Date getDataConcorrencia() {
		return dataConcorrencia;
	}
	public void setDataConcorrencia(Date dataConcorrencia) {
		this.dataConcorrencia = dataConcorrencia;
	}
	/**
	 * @return Retorna o campo indicadorCaixaGordura.
	 */
	public String getIndicadorCaixaGordura() {
		return indicadorCaixaGordura;
	}
	/**
	 * @param indicadorCaixaGordura O indicadorCaixaGordura a ser setado.
	 */
	public void setIndicadorCaixaGordura(String indicadorCaixaGordura) {
		this.indicadorCaixaGordura = indicadorCaixaGordura;
	}
	public String getPermissaoAtualizarLigacaoEsgotosemRA() {
		return permissaoAtualizarLigacaoEsgotosemRA;
	}
	public void setPermissaoAtualizarLigacaoEsgotosemRA(
			String permissaoAtualizarLigacaoEsgotosemRA) {
		this.permissaoAtualizarLigacaoEsgotosemRA = permissaoAtualizarLigacaoEsgotosemRA;
	}
	public String getIndicadorLigacao() {
		return indicadorLigacao;
	}
	public void setIndicadorLigacao(String indicadorLigacao) {
		this.indicadorLigacao = indicadorLigacao;
	}
}
