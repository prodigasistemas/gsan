package gcom.gui.atendimentopublico.ligacaoesgoto;

import java.util.Date;

import org.apache.struts.action.ActionForm;


public class AtualizarVolumeMinimoLigacaoEsgotoActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	// OS
	private String idOrdemServico;
	private String nomeOrdemServico;
	//Imóvel
    private String matriculaImovel;
    private String inscricaoImovel;
    private String clienteUsuario;
    private String cpfCnpjCliente;
    private String situacaoLigacaoAgua;
    private String situacaoLigacaoEsgoto;
    private String categoriaImovel;
    private String qtdeEconomia;
    private String consumoTarifaId;
    //Dados da Ligação
    private String veioEncerrarOS;
    private String consumoMinimoFixado;
    private Date dataConcorrencia;
    private String valorObtido;
    
	public Date getDataConcorrencia() {
		return dataConcorrencia;
	}

	public void setDataConcorrencia(Date dataConcorrencia) {
		this.dataConcorrencia = dataConcorrencia;
	}

	public String getValorObtido() {
		return valorObtido;
	}

	public void setValorObtido(String valorObtido) {
		this.valorObtido = valorObtido;
	}

	public String getConsumoTarifaId() {
		return consumoTarifaId;
	}

	public void setConsumoTarifaId(String consumoTarifaId) {
		this.consumoTarifaId = consumoTarifaId;
	}

	public String getCategoriaImovel() {
		return categoriaImovel;
	}

	public void setCategoriaImovel(String categoriaImovel) {
		this.categoriaImovel = categoriaImovel;
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



	public String getQtdeEconomia() {
		return qtdeEconomia;
	}

	public void setQtdeEconomia(String qtdeEconomia) {
		this.qtdeEconomia = qtdeEconomia;
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

	public String getVeioEncerrarOS() {
		return veioEncerrarOS;
	}

	public void setVeioEncerrarOS(String veioEncerrarOS) {
		this.veioEncerrarOS = veioEncerrarOS;
	}

	public String getConsumoMinimoFixado() {
		return consumoMinimoFixado;
	}

	public void setConsumoMinimoFixado(String consumoMinimoFixado) {
		this.consumoMinimoFixado = consumoMinimoFixado;
	}
    
       
	
	

}
