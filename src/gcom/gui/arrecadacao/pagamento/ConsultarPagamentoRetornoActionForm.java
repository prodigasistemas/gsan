package gcom.gui.arrecadacao.pagamento;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ConsultarPagamentoRetornoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String inscricao;
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
	private String tipoRemessa;
	private String identificacaoServico;
	private String numeroSequencial;
	private String dataGeracao;
	private String totalRegistros;
	private String valorTotalMovimento;
	private String dataProcessamento;
	private String horaProcessamento;
	
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
	public String getTipoAviso() {
		return tipoAviso;
	}
	public void setTipoAviso(String tipoAviso) {
		this.tipoAviso = tipoAviso;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
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
	/**
	 * @return Retorna o campo identificacaoServico.
	 */
	public String getIdentificacaoServico() {
		return identificacaoServico;
	}
	/**
	 * @param identificacaoServico O identificacaoServico a ser setado.
	 */
	public void setIdentificacaoServico(String identificacaoServico) {
		this.identificacaoServico = identificacaoServico;
	}
	/**
	 * @return Retorna o campo numeroSequencial.
	 */
	public String getNumeroSequencial() {
		return numeroSequencial;
	}
	/**
	 * @param numeroSequencial O numeroSequencial a ser setado.
	 */
	public void setNumeroSequencial(String numeroSequencial) {
		this.numeroSequencial = numeroSequencial;
	}
	/**
	 * @return Retorna o campo tipoRemessa.
	 */
	public String getTipoRemessa() {
		return tipoRemessa;
	}
	/**
	 * @param tipoRemessa O tipoRemessa a ser setado.
	 */
	public void setTipoRemessa(String tipoRemessa) {
		this.tipoRemessa = tipoRemessa;
	}
	/**
	 * @return Retorna o campo totalRegistros.
	 */
	public String getTotalRegistros() {
		return totalRegistros;
	}
	/**
	 * @param totalRegistros O totalRegistros a ser setado.
	 */
	public void setTotalRegistros(String totalRegistros) {
		this.totalRegistros = totalRegistros;
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
	
	
}
