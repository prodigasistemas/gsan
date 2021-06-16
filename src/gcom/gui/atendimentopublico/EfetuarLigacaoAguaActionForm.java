package gcom.gui.atendimentopublico;

import org.apache.struts.action.ActionForm;

public class EfetuarLigacaoAguaActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String idOrdemServico;
	
	//Imóvel
	private String nomeOrdemServico;
    private String matriculaImovel;
    private String inscricaoImovel;
    private String clienteUsuario;
    private String cpfCnpjCliente;
    private String situacaoLigacaoAgua;
    private String situacaoLigacaoEsgoto;
   
    //Dados da Ligação
    private String hidrometroInstalacaoHistorico;
    private String diametroLigacao;
    private String dataLigacao;
    private String materialLigacao;
    private String perfilLigacao;
    private String ramalLocalInstalacao;
    private String consumoMinimo;
    private String descHidrometro;
    private String aceitaLacre;
    private String numeroLacre;
    private String profundidadeRamal;
    private String distanciaInstalacaoRamal;
    private String idPavimentoRua;
    private String idPavimentoCalcada;
    private String veioEncerrarOS;
    
    //Dados da Geração do Débito
    private String idTipoDebito;
    private String descricaoTipoDebito;
    private String valorDebito;
    private String motivoNaoCobranca;
    private String percentualCobranca;
    private String quantidadeParcelas;
    private String valorParcelas;
    private String alteracaoValor;
    private String idImovel;
    private String idLigacaoOrigem;
    private String permissaoAlterarOSsemRA;
    
	public String getPermissaoAlterarOSsemRA() {
		return permissaoAlterarOSsemRA;
	}
	
	public void setPermissaoAlterarOSsemRA(String permissaoAlterarOSsemRA) {
		this.permissaoAlterarOSsemRA = permissaoAlterarOSsemRA;
	}
	public String getIdLigacaoOrigem() {
		return idLigacaoOrigem;
	}
	
	public void setIdLigacaoOrigem(String idLigacaoOrigem) {
		this.idLigacaoOrigem = idLigacaoOrigem;
	}
	
	public String getIdImovel() {
		return idImovel;
	}
	
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	
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

	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}

	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}

	public String getDataLigacao() {
		return dataLigacao;
	}

	public void setDataLigacao(String dataLigacao) {
		this.dataLigacao = dataLigacao;
	}

	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
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

	public String getNomeOrdemServico() {
		return nomeOrdemServico;
	}

	public void setNomeOrdemServico(String nomeOrdemServico) {
		this.nomeOrdemServico = nomeOrdemServico;
	}

	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	public String getHidrometroInstalacaoHistorico() {
		return hidrometroInstalacaoHistorico;
	}

	public void setHidrometroInstalacaoHistorico(String hidrometroInstalacaoHistorico) {
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
	}

	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	public String getClienteUsuario() {
		return clienteUsuario;
	}

	public void setClienteUsuario(String clienteUsuario) {
		this.clienteUsuario = clienteUsuario;
	}

	public String getConsumoMinimo() {
		return consumoMinimo;
	}

	public void setConsumoMinimo(String consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}

	public String getDescHidrometro() {
		return descHidrometro;
	}

	public void setDescHidrometro(String descHidrometro) {
		this.descHidrometro = descHidrometro;
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
	
	public String getAlteracaoValor() {
		return alteracaoValor;
	}
	
	public void setAlteracaoValor(String alteracaoValor) {
		this.alteracaoValor = alteracaoValor;
	}
	
	public String getProfundidadeRamal() {
		return profundidadeRamal;
	}
	
	public void setProfundidadeRamal(String profundidadeRamal) {
		this.profundidadeRamal = profundidadeRamal;
	}
	
	public String getDistanciaInstalacaoRamal() {
		return distanciaInstalacaoRamal;
	}
	
	public void setDistanciaInstalacaoRamal(String distanciaInstalacaoRamal) {
		this.distanciaInstalacaoRamal = distanciaInstalacaoRamal;
	}
	
	public String getIdPavimentoRua() {
		return idPavimentoRua;
	}
	
	public void setIdPavimentoRua(String idPavimentoRua) {
		this.idPavimentoRua = idPavimentoRua;
	}
	
	public String getIdPavimentoCalcada() {
		return idPavimentoCalcada;
	}
	
	public void setIdPavimentoCalcada(String idPavimentoCalcada) {
		this.idPavimentoCalcada = idPavimentoCalcada;
	}
}
