package gcom.gui.cadastro.imovel;

import org.apache.struts.action.ActionForm;



/**
 * [UC0549] Consultar Dados de um Pagamento 
 * 
 * @author Kassia Albuquerque
 * @created 28/06/2007
 */


public class ConsultarDadosPagamentoActionForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String localidadeId;
	private String descricaoLocalidade;
	private String tipoDocumento;
	private String matriculaImovel;
	private String inscricaoImovel;
	private String clienteId;
	private String clienteNome;
	private String mesAno;
	private String debitoId;
	private String debitoDescricao;
	private String dataPagamento;
	private String valorPagamento;
	private String mesAnoRefPagamento;
	private String dataProcessamento;
	private String horaProcessamento;
	private String descricaoSituacaoAtual;
	private String descricaoSituacaoAnterior;
	private String valorExcedente;
	private String codigoArrecadador;
	private String nomeArrecadador;
	private String dataLancamento;
	private String codigoAgenteArrecadador;
	private String nomeAgenteArrecadador;
	private String descricaoCodigoRegistro;
	private String dataProcessamentoMovimento;
	private String horaProcessamentoMovimento;
	private String descricaoOcorrenciaMovimento;
	private String sequencialArquivoMovimento;
	private String codigoArrecadadorMovimento;
	private String nomeArrecadadorMovimento;
	private String servicoManutencao;
	private String formaArrecadacao;
	
	
	public String getFormaArrecadacao() {
		return formaArrecadacao;
	}
	public void setFormaArrecadacao(String formaArrecadacao) {
		this.formaArrecadacao = formaArrecadacao;
	}
	public String getCodigoArrecadadorMovimento() {
		return codigoArrecadadorMovimento;
	}
	public void setCodigoArrecadadorMovimento(String codigoArrecadadorMovimento) {
		this.codigoArrecadadorMovimento = codigoArrecadadorMovimento;
	}
	public String getDataProcessamentoMovimento() {
		return dataProcessamentoMovimento;
	}
	public void setDataProcessamentoMovimento(String dataProcessamentoMovimento) {
		this.dataProcessamentoMovimento = dataProcessamentoMovimento;
	}
	public String getDescricaoCodigoRegistro() {
		return descricaoCodigoRegistro;
	}
	public void setDescricaoCodigoRegistro(String descricaoCodigoRegistro) {
		this.descricaoCodigoRegistro = descricaoCodigoRegistro;
	}
	public String getDescricaoOcorrenciaMovimento() {
		return descricaoOcorrenciaMovimento;
	}
	public void setDescricaoOcorrenciaMovimento(String descricaoOcorrenciaMovimento) {
		this.descricaoOcorrenciaMovimento = descricaoOcorrenciaMovimento;
	}
	public String getHoraProcessamentoMovimento() {
		return horaProcessamentoMovimento;
	}
	public void setHoraProcessamentoMovimento(String horaProcessamentoMovimento) {
		this.horaProcessamentoMovimento = horaProcessamentoMovimento;
	}
	public String getNomeArrecadadorMovimento() {
		return nomeArrecadadorMovimento;
	}
	public void setNomeArrecadadorMovimento(String nomeArrecadadorMovimento) {
		this.nomeArrecadadorMovimento = nomeArrecadadorMovimento;
	}
	public String getSequencialArquivoMovimento() {
		return sequencialArquivoMovimento;
	}
	public void setSequencialArquivoMovimento(String sequencialArquivoMovimento) {
		this.sequencialArquivoMovimento = sequencialArquivoMovimento;
	}
	public String getServicoManutencao() {
		return servicoManutencao;
	}
	public void setServicoManutencao(String servicoManutencao) {
		this.servicoManutencao = servicoManutencao;
	}
	public String getClienteId() {
		return clienteId;
	}
	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}
	public String getClienteNome() {
		return clienteNome;
	}
	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}
	public String getCodigoAgenteArrecadador() {
		return codigoAgenteArrecadador;
	}
	public void setCodigoAgenteArrecadador(String codigoAgenteArrecadador) {
		this.codigoAgenteArrecadador = codigoAgenteArrecadador;
	}
	public String getCodigoArrecadador() {
		return codigoArrecadador;
	}
	public void setCodigoArrecadador(String codigoArrecadador) {
		this.codigoArrecadador = codigoArrecadador;
	}
	public String getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public String getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public String getDataProcessamento() {
		return dataProcessamento;
	}
	public void setDataProcessamento(String dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}
	public String getDebitoDescricao() {
		return debitoDescricao;
	}
	public void setDebitoDescricao(String debitoDescricao) {
		this.debitoDescricao = debitoDescricao;
	}
	public String getDebitoId() {
		return debitoId;
	}
	public void setDebitoId(String debitoId) {
		this.debitoId = debitoId;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public String getDescricaoSituacaoAnterior() {
		return descricaoSituacaoAnterior;
	}
	public void setDescricaoSituacaoAnterior(String descricaoSituacaoAnterior) {
		this.descricaoSituacaoAnterior = descricaoSituacaoAnterior;
	}
	public String getDescricaoSituacaoAtual() {
		return descricaoSituacaoAtual;
	}
	public void setDescricaoSituacaoAtual(String descricaoSituacaoAtual) {
		this.descricaoSituacaoAtual = descricaoSituacaoAtual;
	}
	public String getHoraProcessamento() {
		return horaProcessamento;
	}
	public void setHoraProcessamento(String horaProcessamento) {
		this.horaProcessamento = horaProcessamento;
	}
	
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getMesAno() {
		return mesAno;
	}
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	public String getMesAnoRefPagamento() {
		return mesAnoRefPagamento;
	}
	public void setMesAnoRefPagamento(String mesAnoRefPagamento) {
		this.mesAnoRefPagamento = mesAnoRefPagamento;
	}
	public String getNomeAgenteArrecadador() {
		return nomeAgenteArrecadador;
	}
	public void setNomeAgenteArrecadador(String nomeAgenteArrecadador) {
		this.nomeAgenteArrecadador = nomeAgenteArrecadador;
	}
	public String getNomeArrecadador() {
		return nomeArrecadador;
	}
	public void setNomeArrecadador(String nomeArrecadador) {
		this.nomeArrecadador = nomeArrecadador;
	}
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getValorExcedente() {
		return valorExcedente;
	}
	public void setValorExcedente(String valorExcedente) {
		this.valorExcedente = valorExcedente;
	}
	public String getValorPagamento() {
		return valorPagamento;
	}
	public void setValorPagamento(String valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
	public String getLocalidadeId() {
		return localidadeId;
	}
	public void setLocalidadeId(String localidadeId) {
		this.localidadeId = localidadeId;
	}
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	
	
    
}
