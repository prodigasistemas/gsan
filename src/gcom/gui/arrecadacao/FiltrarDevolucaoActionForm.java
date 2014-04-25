package gcom.gui.arrecadacao;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltrarDevolucaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idImovel;
	private String descricaoImovel;
	private String idCliente;
	private String nomeCliente;
	private String idLocalidadeInicial;
	private String descricaoLocalidadeInicial;
	private String idLocalidadeFinal;
	private String descricaoLocalidadeFinal;
	private String idAvisoBancario;
	private String codigoAgenteArrecadador;
	private String dataLancamentoAviso;
	private String numeroSequencialAviso;
	private String clienteRelacaoTipo;
	private String[] devolucaoSituacao;
	private String[] creditoTipo;
	private String dataDevolucaoInicio;
	private String dataDevolucaoFim;
	private String periodoArrecadacaoInicio;
	private String periodoArrecadacaoFim;
	private String[] documentoTipo;
	private String indicadorOpcaoDevolucao;
	

	public String getIndicadorOpcaoDevolucao() {
		return indicadorOpcaoDevolucao;
	}

	public void setIndicadorOpcaoDevolucao(String indicadorOpcaoDevolucao) {
		this.indicadorOpcaoDevolucao = indicadorOpcaoDevolucao;
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

	public String getIdImovel() {
		return idImovel;
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

	public String[] getCreditoTipo() {
		return creditoTipo;
	}

	public void setCreditoTipo(String[] creditoTipo) {
		this.creditoTipo = creditoTipo;
	}

	public String getDataDevolucaoFim() {
		return dataDevolucaoFim;
	}

	public void setDataDevolucaoFim(String dataDevolucaoFim) {
		this.dataDevolucaoFim = dataDevolucaoFim;
	}

	public String getDataDevolucaoInicio() {
		return dataDevolucaoInicio;
	}

	public void setDataDevolucaoInicio(String dataDevolucaoInicio) {
		this.dataDevolucaoInicio = dataDevolucaoInicio;
	}

	public String[] getDevolucaoSituacao() {
		return devolucaoSituacao;
	}

	public void setDevolucaoSituacao(String[] devolucaoSituacao) {
		this.devolucaoSituacao = devolucaoSituacao;
	}

	public String getIdAvisoBancario() {
		return idAvisoBancario;
	}

	public void setIdAvisoBancario(String idAvisoBancario) {
		this.idAvisoBancario = idAvisoBancario;
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

	public String getDescricaoImovel() {
		return descricaoImovel;
	}

	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getDescricaoLocalidadeFinal() {
		return descricaoLocalidadeFinal;
	}

	public void setDescricaoLocalidadeFinal(String descricaoLocalidadeFinal) {
		this.descricaoLocalidadeFinal = descricaoLocalidadeFinal;
	}

	public String getDescricaoLocalidadeInicial() {
		return descricaoLocalidadeInicial;
	}

	public void setDescricaoLocalidadeInicial(String descricaoLocalidadeInicial) {
		this.descricaoLocalidadeInicial = descricaoLocalidadeInicial;
	}

	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}

	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}

	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}
	

	
	
}

