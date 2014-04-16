package gcom.gui.atendimentopublico.ligacaoagua;



import java.util.Date;

import org.apache.struts.action.ActionForm;

public class EfetuarCorteLigacaoAguaActionForm extends ActionForm{
	
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
    private String dataCorte;
    private String motivoCorte;
    private String tipoCorte;
    private String numLeituraCorte;
    private String numSeloCorte;
    
    // Dados da Geração do Débito
    private String idTipoDebito;
    private String descricaoTipoDebito;
    private String valorDebito;
    private String motivoNaoCobranca;
    private String percentualCobranca;
    private String quantidadeParcelas;
    private String valorParcelas;
    private String qtdeMaxParcelas;
    
    /*
	 * Colocado por Raphael Rossiter em 18/04/2007
	 * [FS0013 - Alteração de Valor]
	 */
    private String alteracaoValor;
    
    // Controle
    private String veioEncerrarOS;
    private Date dataConcorrencia;

    public void reset() {
    	this.idOrdemServico = null;
    	this.nomeOrdemServico = null;
        this.matriculaImovel = null;
        this.inscricaoImovel = null;
        this.clienteUsuario = null;
        this.cpfCnpjCliente = null;
        this.situacaoLigacaoAgua = null;
        this.situacaoLigacaoEsgoto = null;
        this.dataCorte = null;
        this.motivoCorte = null;
        this.numLeituraCorte = null;
        this.numSeloCorte = null;
        this.idTipoDebito = null;
        this.descricaoTipoDebito = null;
        this.valorDebito = null;
        this.motivoNaoCobranca = null;
        this.percentualCobranca = null;
        this.quantidadeParcelas = null;
        this.valorParcelas = null;
        this.veioEncerrarOS = "false";
        this.dataConcorrencia = new Date();
    }
    
    
	public String getClienteUsuario() {
		return clienteUsuario;
	}

	public void setClienteUsuario(String clienteUsuario) {
		this.clienteUsuario = clienteUsuario;
	}

	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}

	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}

	public String getDataCorte() {
		return dataCorte;
	}

	public void setDataCorte(String dataCorte) {
		this.dataCorte = dataCorte;
	}

	public String getDescricaoTipoDebito() {
		return descricaoTipoDebito;
	}

	public void setDescricaoTipoDebito(String descricaoTipoDebito) {
		this.descricaoTipoDebito = descricaoTipoDebito;
	}

	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public String getIdTipoDebito() {
		return idTipoDebito;
	}

	public void setIdTipoDebito(String idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
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

	public String getMotivoCorte() {
		return motivoCorte;
	}

	public void setMotivoCorte(String motivoCorte) {
		this.motivoCorte = motivoCorte;
	}

	public String getMotivoNaoCobranca() {
		return motivoNaoCobranca;
	}

	public void setMotivoNaoCobranca(String motivoNaoCobranca) {
		this.motivoNaoCobranca = motivoNaoCobranca;
	}

	public String getNomeOrdemServico() {
		return nomeOrdemServico;
	}

	public void setNomeOrdemServico(String nomeOrdemServico) {
		this.nomeOrdemServico = nomeOrdemServico;
	}

	public String getNumLeituraCorte() {
		return numLeituraCorte;
	}

	public void setNumLeituraCorte(String numLeituraCorte) {
		this.numLeituraCorte = numLeituraCorte;
	}

	public String getNumSeloCorte() {
		return numSeloCorte;
	}

	public void setNumSeloCorte(String numSeloCorte) {
		this.numSeloCorte = numSeloCorte;
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

	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
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


	public String getQtdeMaxParcelas() {
		return qtdeMaxParcelas;
	}


	public void setQtdeMaxParcelas(String qtdeMaxParcelas) {
		this.qtdeMaxParcelas = qtdeMaxParcelas;
	}


	public String getTipoCorte() {
		return tipoCorte;
	}


	public void setTipoCorte(String tipoCorte) {
		this.tipoCorte = tipoCorte;
	}


	public Date getDataConcorrencia() {
		return dataConcorrencia;
	}


	public void setDataConcorrencia(Date dataConcorrencia) {
		this.dataConcorrencia = dataConcorrencia;
	}


	public String getAlteracaoValor() {
		return alteracaoValor;
	}


	public void setAlteracaoValor(String alteracaoValor) {
		this.alteracaoValor = alteracaoValor;
	}
	
	
    
}
