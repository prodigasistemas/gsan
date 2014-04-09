package gcom.gui.atendimentopublico;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AtualizarLigacaoAguaActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;

	//Ordem de Serviço
	private String idOrdemServico;
	private String nomeOrdemServico;
	private String servicoTipo;

	//Imóvel
    private String matriculaImovel;
    private String inscricaoImovel;
    private String clienteUsuario;
    private String cpfCnpjCliente;
    private String situacaoLigacaoAgua;
    private String situacaoLigacaoEsgoto;
    
    //Dados da Ligação
    private String dataLigacao;
    private String diametroLigacao;
    private String materialLigacao;
    private String perfilLigacao;
    private String ramalLocalInstalacao;
    
    private String motivoCorte;
    private String tipoCorte;    
    private String numSeloCorte;
    private String leituraCorte;

    private String motivoSupressao;
    private String tipoSupressao;
    private String numSeloSupressao;
    private String leituraSupressao;
    
    private String aceitaLacre;
    private String numeroLacre;
    
    //Dados Validação
    private String habilitaCorte		= "true";
    private String habilitaSupressao 	= "true";
    
    private String veioEncerrarOS;
    private Date dataConcorrencia;
    
    /*private String ligacaoPosicao;
    private String ligacaoAguaAbastecimentoAlternativo;
    private String ligacaoAguaSituacaoAbastecimento;*/
    
    /*
	 * Colocado por Rômulo Au?elio em 02/08/2008
	 * [Permissao Especial efetuar ligacao de agua sem ra]
	 */
    
    private String idImovel;
    
    private String idLigacaoOrigem;
    
    private String permissaoAlterarOSsemRA;
    
    
    
    public Date getDataConcorrencia() {
		return dataConcorrencia;
	}

	public void setDataConcorrencia(Date dataConcorrencia) {
		this.dataConcorrencia = dataConcorrencia;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
    
    	idOrdemServico = null;
    	nomeOrdemServico = null;
    	servicoTipo = null;

    	//Imóvel
        matriculaImovel = null;
        inscricaoImovel = null;
        clienteUsuario = null;
        cpfCnpjCliente = null;
        situacaoLigacaoAgua = null;
        situacaoLigacaoEsgoto = null;
        
        //Dados da Ligação
        dataLigacao = null;
        diametroLigacao = null;
        materialLigacao = null;
        perfilLigacao = null;
        
        motivoCorte = null;
        tipoCorte = null;
        numSeloCorte = null;
        leituraCorte = null;

        motivoSupressao = null;
        tipoSupressao = null;
        numSeloSupressao = null;
        leituraSupressao = null;
    }
    
	
	
	public String getDataLigacao() {
		return dataLigacao;
	}

	public void setDataLigacao(String dataLigacao) {
		this.dataLigacao = dataLigacao;
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
  
	public String getDiametroLigacao() {
		return diametroLigacao;
	}

	public void setDiametroLigacao(String diametroLigacao) {
		this.diametroLigacao = diametroLigacao;
	}

	public String getMaterialLigacao() {
		return materialLigacao;
	}
	public void setMaterialLigacao(String materialLigacao) {
		this.materialLigacao = materialLigacao;
	}

	public String getPerfilLigacao() {
		return perfilLigacao;
	}
	public void setPerfilLigacao(String perfilLigacao) {
		this.perfilLigacao = perfilLigacao;
	}
    
	public String getNumSeloCorte() {
		return numSeloCorte;
	}
	public void setNumSeloCorte(String numSeloCorte) {
		this.numSeloCorte = numSeloCorte;
	}
	public String getNumSeloSupressao() {
		return numSeloSupressao;
	}
	public void setNumSeloSupressao(String numSeloSupressao) {
		this.numSeloSupressao = numSeloSupressao;
	}
	public String getMotivoCorte() {
		return motivoCorte;
	}
	public void setMotivoCorte(String motivoCorte) {
		this.motivoCorte = motivoCorte;
	}
	public String getMotivoSupressao() {
		return motivoSupressao;
	}
	public void setMotivoSupressao(String motivoSupressao) {
		this.motivoSupressao = motivoSupressao;
	}
	public String getTipoCorte() {
		return tipoCorte;
	}
	public void setTipoCorte(String tipoCorte) {
		this.tipoCorte = tipoCorte;
	}
	public String getTipoSupressao() {
		return tipoSupressao;
	}
	public void setTipoSupressao(String tipoSupressao) {
		this.tipoSupressao = tipoSupressao;
	}

	public String getHabilitaCorte() {
		return habilitaCorte;
	}
	public void setHabilitaCorte(String habilitaCorte) {
		this.habilitaCorte = habilitaCorte;
	}
	public String getHabilitaSupressao() {
		return habilitaSupressao;
	}
	public void setHabilitaSupressao(String habilitaSupressao) {
		this.habilitaSupressao = habilitaSupressao;
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
	public String getServicoTipo() {
		return servicoTipo;
	}
	public void setServicoTipo(String servicoTipo) {
		this.servicoTipo = servicoTipo;
	}
	public String getLeituraCorte() {
		return leituraCorte;
	}
	public void setLeituraCorte(String leituraCorte) {
		this.leituraCorte = leituraCorte;
	}
	public String getLeituraSupressao() {
		return leituraSupressao;
	}
	public void setLeituraSupressao(String leituraSupressao) {
		this.leituraSupressao = leituraSupressao;
	}

	public String getRamalLocalInstalacao() {
		return ramalLocalInstalacao;
	}

	public void setRamalLocalInstalacao(String ramalLocalInstalacao) {
		this.ramalLocalInstalacao = ramalLocalInstalacao;
	}

	public String getAceitaLacre() {
		return aceitaLacre;
	}

	public void setAceitaLacre(String aceitaLacre) {
		this.aceitaLacre = aceitaLacre;
	}

	public String getNumeroLacre() {
		return numeroLacre;
	}

	public void setNumeroLacre(String numeroLacre) {
		this.numeroLacre = numeroLacre;
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

	public String getPermissaoAlterarOSsemRA() {
		return permissaoAlterarOSsemRA;
	}

	public void setPermissaoAlterarOSsemRA(String permissaoAlterarOSsemRA) {
		this.permissaoAlterarOSsemRA = permissaoAlterarOSsemRA;
	}

}
