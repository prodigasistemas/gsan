package gcom.gui.faturamento;

import org.apache.struts.action.ActionForm;

public class InserirGuiaPagamentoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String idImovel;
	private String codigoCliente;
	private String inscricaoImovel;
	private String nomeClienteUsuario;
	private String situacaoAgua;
	private String situacaoEsgoto;
	private String cpf;
	private String nomeCliente;
	private String profissao;
	private String ramoAtividade;
	private String registroAtendimento;
	private String nomeRegistroAtendimento;	
	private String ordemServico;
	private String descricaoOrdemServico;	
	private String idTipoDebito;
	private String descricaoTipoDebito;
	private String dataVencimento;
	private String valorDebito;
	private String localidade;
	private String habilitaTipoDebito = "true";
    private String qtdeDiasVencimento;
    private String numeroTotalPrestacao;
    private String observacao;
    private String indicadorEmitirObservacao;
    
    private String observacaoPagamentoParcial;

	public String getHabilitaTipoDebito() {
		return habilitaTipoDebito;
	}

	public void setHabilitaTipoDebito(String habilitaTipoDebito) {
		this.habilitaTipoDebito = habilitaTipoDebito;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}

	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}

	public String getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(String ordemServico) {
		this.ordemServico = ordemServico;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getRamoAtividade() {
		return ramoAtividade;
	}

	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}

	public String getRegistroAtendimento() {
		return registroAtendimento;
	}

	public void setRegistroAtendimento(String registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	public String getSituacaoAgua() {
		return situacaoAgua;
	}

	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}

	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}

	public String getIdTipoDebito() {
		return idTipoDebito;
	}

	public void setIdTipoDebito(String idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}

	public String getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}

	public String getDescricaoTipoDebito() {
		return descricaoTipoDebito;
	}

	public void setDescricaoTipoDebito(String descricaoTipoDebito) {
		this.descricaoTipoDebito = descricaoTipoDebito;
	}

	public String getNomeRegistroAtendimento() {
		return nomeRegistroAtendimento;
	}

	public void setNomeRegistroAtendimento(String nomeRegistroAtendimento) {
		this.nomeRegistroAtendimento = nomeRegistroAtendimento;
	}

	public String getDescricaoOrdemServico() {
		return descricaoOrdemServico;
	}

	public void setDescricaoOrdemServico(String descricaoOrdemServico) {
		this.descricaoOrdemServico = descricaoOrdemServico;
	}

    public String getNumeroTotalPrestacao() {
        return numeroTotalPrestacao;
    }

    public void setNumeroTotalPrestacao(String numeroTotalPrestacao) {
        this.numeroTotalPrestacao = numeroTotalPrestacao;
    }

    public String getQtdeDiasVencimento() {
        return qtdeDiasVencimento;
    }

    public void setQtdeDiasVencimento(String qtdeDiasVencimento) {
        this.qtdeDiasVencimento = qtdeDiasVencimento;
    }

	/**
	 * @return Retorna o campo indicadorEmitirObservacao.
	 */
	public String getIndicadorEmitirObservacao() {
		return indicadorEmitirObservacao;
	}

	/**
	 * @param indicadorEmitirObservacao O indicadorEmitirObservacao a ser setado.
	 */
	public void setIndicadorEmitirObservacao(String indicadorEmitirObservacao) {
		this.indicadorEmitirObservacao = indicadorEmitirObservacao;
	}

	/**
	 * @return Retorna o campo observacao.
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * @param observacao O observacao a ser setado.
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getObservacaoPagamentoParcial() {
		return observacaoPagamentoParcial;
	}

	public void setObservacaoPagamentoParcial(String observacaoPagamentoParcial) {
		this.observacaoPagamentoParcial = observacaoPagamentoParcial;
	}
}
