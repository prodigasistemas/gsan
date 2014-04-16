package gcom.gui.arrecadacao.pagamento;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe 
 *
 * @author COMPESA
 * @date 10/05/2006
 */
public class ConsultarPagamentoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idImovel;
	private String inscricao;
	private String idAvisoBancario;
	private String idCliente;
	private String clienteRelacaoTipo;
	private String[] pagamentoSituacao;
	private String[] debitoTipo;
	private String dataPagamentoInicio;
	private String dataPagamentoFim;
	private String periodoArrecadacaoInicio;
	private String periodoArrecadacaoFim;
	private String periodoPagamentoInicio;
	private String periodoPagamentoFim;
	private String[] arrecadacaoForma;
	private String[] documentoTipo;
	private String codigoAgenteArrecadador;
	private String dataLancamentoAviso;
	private String numeroSequencialAviso;
	private String codigoBanco;
	private String codigoRemessa;
	private String identificacaoServico;
	private String numeroSequencial;
	private String[] debitoTipoSelecionados;
	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	private String nomeCliente;
	private String cpfCnpj;
	private String profissao;
	private String ramoAtividade;
	private String telefone;
	private String idArrecadador;
	private String descricaoArrecadador;
	private String dataLancamento;
	private String sequencialAviso;
	private String tipoAviso;
	private String localidadeInicial;
	private String descricaoLocalidadeInicial;
	private String localidadeFinal;
	private String descricaoLocalidadeFinal;
	private String dataGeracao;
	private String numeroRegistrosMovimento;
	private String valorTotalMovimento;
	private String dataProcessamento;
	private String horaProcessamento;
	private String opcaoPagamento;
    private String valorPagamentoInicial;
    private String valorPagamentoFinal;
   
	
	
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getDescricaoArrecadador() {
		return descricaoArrecadador;
	}

	public void setDescricaoArrecadador(String descricaoArrecadador) {
		this.descricaoArrecadador = descricaoArrecadador;
	}

	public String getIdArrecadador() {
		return idArrecadador;
	}

	public void setIdArrecadador(String idArrecadador) {
		this.idArrecadador = idArrecadador;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
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

	public String getSequencialAviso() {
		return sequencialAviso;
	}

	public void setSequencialAviso(String sequencialAviso) {
		this.sequencialAviso = sequencialAviso;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTipoAviso() {
		return tipoAviso;
	}

	public void setTipoAviso(String tipoAviso) {
		this.tipoAviso = tipoAviso;
	}

	public String[] getDebitoTipoSelecionados() {
		return debitoTipoSelecionados;
	}

	public void setDebitoTipoSelecionados(String[] debitoTipoSelecionados) {
		this.debitoTipoSelecionados = debitoTipoSelecionados;
	}

	public String getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public String getCodigoRemessa() {
		return codigoRemessa;
	}

	public void setCodigoRemessa(String codigoRemessa) {
		this.codigoRemessa = codigoRemessa;
	}

	public String getIdentificacaoServico() {
		return identificacaoServico;
	}

	public void setIdentificacaoServico(String identificacaoServico) {
		this.identificacaoServico = identificacaoServico;
	}

	public String getNumeroSequencial() {
		return numeroSequencial;
	}

	public void setNumeroSequencial(String numeroSequencial) {
		this.numeroSequencial = numeroSequencial;
	}

	public String[] getArrecadacaoForma() {
		return arrecadacaoForma;
	}

	public void setArrecadacaoForma(String[] arrecadacaoForma) {
		this.arrecadacaoForma = arrecadacaoForma;
	}

	public String[] getDocumentoTipo() {
		return documentoTipo;
	}

	public void setDocumentoTipo(String[] documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	public String getPeriodoArrecadacaoFim() {
		return periodoArrecadacaoFim;
	}

	public void setPeriodoArrecadacaoFim(String periodoArrecadacaoFim) {
		this.periodoArrecadacaoFim = periodoArrecadacaoFim;
	}

	public String getPeriodoArrecadacaoInicio() {
		return periodoArrecadacaoInicio;
	}

	public void setPeriodoArrecadacaoInicio(String periodoArrecadacaoInicio) {
		this.periodoArrecadacaoInicio = periodoArrecadacaoInicio;
	}

	public String getPeriodoPagamentoFim() {
		return periodoPagamentoFim;
	}

	public void setPeriodoPagamentoFim(String periodoPagamentoFim) {
		this.periodoPagamentoFim = periodoPagamentoFim;
	}

	public String getPeriodoPagamentoInicio() {
		return periodoPagamentoInicio;
	}

	public void setPeriodoPagamentoInicio(String periodoPagamentoInicio) {
		this.periodoPagamentoInicio = periodoPagamentoInicio;
	}

	public String getDataPagamentoFim() {
		return dataPagamentoFim;
	}

	public void setDataPagamentoFim(String dataPagamentoFim) {
		this.dataPagamentoFim = dataPagamentoFim;
	}

	public String getDataPagamentoInicio() {
		return dataPagamentoInicio;
	}

	public void setDataPagamentoInicio(String dataPagamentoInicio) {
		this.dataPagamentoInicio = dataPagamentoInicio;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public String[] getDebitoTipo() {
		return debitoTipo;
	}

	public void setDebitoTipo(String[] debitoTipo) {
		this.debitoTipo = debitoTipo;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getClienteRelacaoTipo() {
		return clienteRelacaoTipo;
	}

	public void setClienteRelacaoTipo(String clienteRelacaoTipo) {
		this.clienteRelacaoTipo = clienteRelacaoTipo;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String[] getPagamentoSituacao() {
		return pagamentoSituacao;
	}

	public void setPagamentoSituacao(String[] pagamentoSituacao) {
		this.pagamentoSituacao = pagamentoSituacao;
	}

	public String getCodigoAgenteArrecadador() {
		return codigoAgenteArrecadador;
	}

	public void setCodigoAgenteArrecadador(String codigoAgenteArrecadador) {
		this.codigoAgenteArrecadador = codigoAgenteArrecadador;
	}

	public String getDataLancamentoAviso() {
		return dataLancamentoAviso;
	}

	public void setDataLancamentoAviso(String dataLancamentoAviso) {
		this.dataLancamentoAviso = dataLancamentoAviso;
	}

	public String getNumeroSequencialAviso() {
		return numeroSequencialAviso;
	}

	public void setNumeroSequencialAviso(String numeroSequencialAviso) {
		this.numeroSequencialAviso = numeroSequencialAviso;
	}

	public String getIdAvisoBancario() {
		return idAvisoBancario;
	}

	public void setIdAvisoBancario(String idAvisoBancario) {
		this.idAvisoBancario = idAvisoBancario;
	}

	/**
	 * @return Retorna o campo localidadeFinal.
	 */
	public String getLocalidadeFinal() {
		return localidadeFinal;
	}

	/**
	 * @param localidadeFinal O localidadeFinal a ser setado.
	 */
	public void setLocalidadeFinal(String localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	/**
	 * @return Retorna o campo localidadeInicial.
	 */
	public String getLocalidadeInicial() {
		return localidadeInicial;
	}

	/**
	 * @param localidadeInicial O localidadeInicial a ser setado.
	 */
	public void setLocalidadeInicial(String localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	/**
	 * @return Retorna o campo descricaoLocalidadeFinal.
	 */
	public String getDescricaoLocalidadeFinal() {
		return descricaoLocalidadeFinal;
	}

	/**
	 * @param descricaoLocalidadeFinal O descricaoLocalidadeFinal a ser setado.
	 */
	public void setDescricaoLocalidadeFinal(String descricaoLocalidadeFinal) {
		this.descricaoLocalidadeFinal = descricaoLocalidadeFinal;
	}

	/**
	 * @return Retorna o campo descricaoLocalidadeInicial.
	 */
	public String getDescricaoLocalidadeInicial() {
		return descricaoLocalidadeInicial;
	}

	/**
	 * @param descricaoLocalidadeInicial O descricaoLocalidadeInicial a ser setado.
	 */
	public void setDescricaoLocalidadeInicial(String descricaoLocalidadeInicial) {
		this.descricaoLocalidadeInicial = descricaoLocalidadeInicial;
	}

	/**
	 * @return Retorna o campo dataGeracao.
	 */
	public String getDataGeracao() {
		return dataGeracao;
	}

	/**
	 * @param dataGeracao O dataGeracao a ser setado.
	 */
	public void setDataGeracao(String dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	/**
	 * @return Retorna o campo numeroRegistrosMovimento.
	 */
	public String getNumeroRegistrosMovimento() {
		return numeroRegistrosMovimento;
	}

	/**
	 * @param numeroRegistrosMovimento O numeroRegistrosMovimento a ser setado.
	 */
	public void setNumeroRegistrosMovimento(String numeroRegistrosMovimento) {
		this.numeroRegistrosMovimento = numeroRegistrosMovimento;
	}

	/**
	 * @return Retorna o campo valorTotalMovimento.
	 */
	public String getValorTotalMovimento() {
		return valorTotalMovimento;
	}

	/**
	 * @param valorTotalMovimento O valorTotalMovimento a ser setado.
	 */
	public void setValorTotalMovimento(String valorTotalMovimento) {
		this.valorTotalMovimento = valorTotalMovimento;
	}

	/**
	 * @return Retorna o campo dataProcessamento.
	 */
	public String getDataProcessamento() {
		return dataProcessamento;
	}

	/**
	 * @param dataProcessamento O dataProcessamento a ser setado.
	 */
	public void setDataProcessamento(String dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	/**
	 * @return Retorna o campo horaProcessamento.
	 */
	public String getHoraProcessamento() {
		return horaProcessamento;
	}

	/**
	 * @param horaProcessamento O horaProcessamento a ser setado.
	 */
	public void setHoraProcessamento(String horaProcessamento) {
		this.horaProcessamento = horaProcessamento;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/*
		this.idImovel = null;
		this.inscricao = null;
		this.idAvisoBancario = null;
		this.idCliente = null;
		this.clienteRelacaoTipo = null;
		this.dataPagamentoInicio = null;
		this.dataPagamentoFim = null;
		this.periodoArrecadacaoInicio = null;
		this.periodoArrecadacaoFim = null;
		this.periodoPagamentoInicio = null;
		this.periodoPagamentoFim = null;
		this.codigoAgenteArrecadador = null;
		this.dataLancamentoAviso = null;
		this.numeroSequencialAviso = null;
		this.codigoBanco = null;
		this.codigoRemessa = null;
		this.identificacaoServico = null;
		this.numeroSequencial = null;
		this.situacaoLigacaoAgua = null;
		this.situacaoLigacaoEsgoto = null;
		this.nomeCliente = null;
		this.cpfCnpj = null;
		this.profissao = null;
		this.ramoAtividade = null;
		this.telefone = null;
		this.idArrecadador = null;
		this.descricaoArrecadador = null;
		this.dataLancamento = null;
		this.sequencialAviso = null;
		this.tipoAviso = null;
		this.localidadeInicial = null;
		this.descricaoLocalidadeInicial = null;
		this.localidadeFinal = null;
		this.descricaoLocalidadeFinal = null;
		this.dataGeracao = null;
		this.numeroRegistrosMovimento = null;
		this.valorTotalMovimento = null;
		this.dataProcessamento = null;
		this.horaProcessamento = null;
		
		// Arrays
		this.pagamentoSituacao = new String[]{};
		this.debitoTipo = new String[]{};
		this.arrecadacaoForma = new String[]{};
		this.documentoTipo = new String[]{};
		this.debitoTipoSelecionados = new String[]{};*/
	}

	/**
	 * @return Retorna o campo opcaoPagamento.
	 */
	public String getOpcaoPagamento() {
		return opcaoPagamento;
	}

	/**
	 * @param opcaoPagamento O opcaoPagamento a ser setado.
	 */
	public void setOpcaoPagamento(String opcaoPagamento) {
		this.opcaoPagamento = opcaoPagamento;
	}

    
    public String getValorPagamentoFinal() {
        return valorPagamentoFinal;
    }

    
    public void setValorPagamentoFinal(String valorPagamentoFinal) {
        this.valorPagamentoFinal = valorPagamentoFinal;
    }

    
    public String getValorPagamentoInicial() {
        return valorPagamentoInicial;
    }

    
    public void setValorPagamentoInicial(String valorPagamentoInicial) {
        this.valorPagamentoInicial = valorPagamentoInicial;
    }
}
