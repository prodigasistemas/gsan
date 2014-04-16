package gcom.gui.atendimentopublico.hidrometro;

import org.apache.struts.action.ActionForm;

/**
 * Descrição da classe 
 *
 * @author Thiago Tenório
 * @date 19/06/2006
 */
public class EfetuarRetiradaHidrometroActionForm extends ActionForm{
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

    //Dados da Retirada de Hidrômetro
    private String hidrometro;
    private String medicaoTipo;
    private String dataRetirada;
    private String numeroLeitura;
    private String hidrometroLocalArmazenagem;
    
    private String veioEncerrarOS;
    
    //Dados da Geração do Débito
    private String idTipoDebito;
    private String descricaoTipoDebito;
    
    private String valorDebito;
    private String motivoNaoCobranca;
    private String percentualCobranca;
    private String quantidadeParcelas;
    private String valorParcelas;
    private String qtdeMaxParcelas;
    private String idHidrometroSituacao;
    
    /*
	 * Colocado por Raphael Rossiter em 20/04/2007
	 * [FS0013 - Alteração de Valor]
	 */
    private String alteracaoValor;
    
    /*
	 * Colocado por Hugo Fernando em 03/03/2010
	 * 
	 */
    private String hidrometroExtraviado;
    
    
    
    /**
	 * @return Returns the hidrometroExtraviado.
	 */
	public String getHidrometroExtraviado() {
		return hidrometroExtraviado;
	}

	/**
	 * @param hidrometroExtraviado The hidrometroExtraviado to set.
	 */
	public void setHidrometroExtraviado(String hidrometroExtraviado) {
		this.hidrometroExtraviado = hidrometroExtraviado;
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

	public String getVeioEncerrarOS() {
		return veioEncerrarOS;
	}

	public void setVeioEncerrarOS(String veioEncerrarOS) {
		this.veioEncerrarOS = veioEncerrarOS;
	}

	public String getDataRetirada() {
		return dataRetirada;
	}
    
	public void setDataRetirada(String dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public String getNumeroLeitura() {
		return numeroLeitura;
	}
	public void setNumeroLeitura(String numeroLeitura) {
		this.numeroLeitura = numeroLeitura;
	}
	public String getMedicaoTipo() {
		return medicaoTipo;
	}
	public void setMedicaoTipo(String tipoMedicao) {
		this.medicaoTipo = tipoMedicao;
	}
	/**
	 * @return Retorna o campo cpfCnpjCliente.
	 */
	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}
	public String getClienteUsuario() {
		return clienteUsuario;
	}
	public void setClienteUsuario(String clienteUsuario) {
		this.clienteUsuario = clienteUsuario;
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
	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}
	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}
	public String getHidrometro() {
		return hidrometro;
	}
	public void setHidrometro(String hidrometro) {
		this.hidrometro = hidrometro;
	}

	public String getHidrometroLocalArmazenagem() {
		return hidrometroLocalArmazenagem;
	}

	public void setHidrometroLocalArmazenagem(String hidrometroLocalArmazenagem) {
		this.hidrometroLocalArmazenagem = hidrometroLocalArmazenagem;
	}

	public String getIdHidrometroSituacao() {
		return idHidrometroSituacao;
	}

	public void setIdHidrometroSituacao(String idHidrometroSituacao) {
		this.idHidrometroSituacao = idHidrometroSituacao;
	}

	public String getQtdeMaxParcelas() {
		return qtdeMaxParcelas;
	}

	public void setQtdeMaxParcelas(String qtdeMaxParcelas) {
		this.qtdeMaxParcelas = qtdeMaxParcelas;
	}

	public String getAlteracaoValor() {
		return alteracaoValor;
	}

	public void setAlteracaoValor(String alteracaoValor) {
		this.alteracaoValor = alteracaoValor;
	}

	
}
	
