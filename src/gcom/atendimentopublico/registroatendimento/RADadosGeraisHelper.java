package gcom.atendimentopublico.registroatendimento;

import java.util.Collection;


public class RADadosGeraisHelper {

	private Integer idRegistroAtendimento;
	private short indicadorAtendimentoOnLine; 
	private String numeroRAManual; 
	private String dataAtendimento;
	private String horaAtendimento; 
	private String tempoEsperaInicial;
	private String tempoEsperaFinal; 
	private Integer idUnidadeAtendimento; 
	private Integer idMeioSolicitacao;
	private Integer idSolicitacaoTipo;
	private Integer idSolicitacaoTipoEspecificacao; 
	private String dataPrevista;
	private String observacao; 
	private Integer idUsuarioLogado;
	private Integer idFuncionario;
	private Integer idRAJAGerado;
	private String protocoloAtendimento;
	private String observacaoOS;
	@SuppressWarnings("rawtypes")
	private Collection colecaoRegistroAtendimentoAnexo;
	
	public RADadosGeraisHelper() {
		
	}
	
 	public Integer getIdRegistroAtendimento() {
		return idRegistroAtendimento;
	}

	public void setIdRegistroAtendimento(Integer idRegistroAtendimento) {
		this.idRegistroAtendimento = idRegistroAtendimento;
	}

	public short getIndicadorAtendimentoOnLine() {
		return indicadorAtendimentoOnLine;
	}
	private void setIndicadorAtendimentoOnLine(short indicadorAtendimentoOnLine) {
		this.indicadorAtendimentoOnLine = indicadorAtendimentoOnLine;
	}
	public RADadosGeraisHelper indicadorAtendimentoOnline(short indicadorAtendimentoOnLine){
		setIndicadorAtendimentoOnLine(indicadorAtendimentoOnLine);
		return this;
	}
	public String getNumeroRAManual() {
		return numeroRAManual;
	}
	private void setNumeroRAManual(String numeroRAManual) {
		this.numeroRAManual = numeroRAManual;
	}
	public RADadosGeraisHelper numeroRAManual(String numeroRAManual){
		setNumeroRAManual(numeroRAManual);
		return this;
	}
	public String getDataAtendimento() {
		return dataAtendimento;
	}
	private void setDataAtendimento(String dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}
	public RADadosGeraisHelper dataAtendimento(String dataAtendimento){
		setDataAtendimento(dataAtendimento);
		return this;
	}
	public String getHoraAtendimento() {
		return horaAtendimento;
	}
	private void setHoraAtendimento(String horaAtendimento) {
		this.horaAtendimento = horaAtendimento;
	}
	public RADadosGeraisHelper horaAtendimento(String horaAtendimento){
		setHoraAtendimento(horaAtendimento);
		return this;
	}
	public String getTempoEsperaInicial() {
		return tempoEsperaInicial;
	}
	private void setTempoEsperaInicial(String tempoEsperaInicial) {
		this.tempoEsperaInicial = tempoEsperaInicial;
	}
	public RADadosGeraisHelper tempoEsperaInicial(String tempoEsperaInicial){
		setTempoEsperaInicial(tempoEsperaInicial);
		return this;
	}
	public String getTempoEsperaFinal() {
		return tempoEsperaFinal;
	}
	private void setTempoEsperaFinal(String tempoEsperaFinal) {
		this.tempoEsperaFinal = tempoEsperaFinal;
	}
	public RADadosGeraisHelper tempoEsperaFinal(String tempoEsperaFinal){
		setTempoEsperaFinal(tempoEsperaFinal);
		return this;
	}
	public Integer getIdUnidadeAtendimento() {
		return idUnidadeAtendimento;
	}
	private void setIdUnidadeAtendimento(Integer idUnidadeAtendimento) {
		this.idUnidadeAtendimento = idUnidadeAtendimento;
	}
	public RADadosGeraisHelper idUnidadeAtendimento(Integer idUnidadeAtendimento){
		setIdUnidadeAtendimento(idUnidadeAtendimento);
		return this;
	}
	public Integer getIdMeioSolicitacao() {
		return idMeioSolicitacao;
	}
	private void setIdMeioSolicitacao(Integer idMeioSolicitacao) {
		this.idMeioSolicitacao = idMeioSolicitacao;
	}
	public RADadosGeraisHelper idMeioSolicitacao(Integer idMeioSolicitacao){
		setIdMeioSolicitacao(idMeioSolicitacao);
		return this;
	}
	public Integer getIdSolicitacaoTipo() {
		return idSolicitacaoTipo;
	}
	private void setIdSolicitacaoTipo(Integer idSolicitacaoTipo) {
		this.idSolicitacaoTipo = idSolicitacaoTipo;
	}
	public RADadosGeraisHelper idSolicitacaoTipo(Integer idSolicitacaoTipo){
		setIdSolicitacaoTipo(idSolicitacaoTipo);
		return this;
	}
	public Integer getIdSolicitacaoTipoEspecificacao() {
		return idSolicitacaoTipoEspecificacao;
	}
	private void setIdSolicitacaoTipoEspecificacao(Integer idSolicitacaoTipoEspecificacao) {
		this.idSolicitacaoTipoEspecificacao = idSolicitacaoTipoEspecificacao;
	}
	public RADadosGeraisHelper idSolicitacaoTipoEspecificacao(Integer idSolicitacaoTipoEspecificacao) {
		setIdSolicitacaoTipoEspecificacao(idSolicitacaoTipoEspecificacao);
		return this;
	}
	public String getDataPrevista() {
		return dataPrevista;
	}
	private void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	public RADadosGeraisHelper dataPrevista(String dataPrevista){
		setDataPrevista(dataPrevista);
		return this;
	}
	public String getObservacao() {
		return observacao;
	}
	private void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public RADadosGeraisHelper observacao(String observacao){
		setObservacao(observacao);
		return this;
	}
	public Integer getIdUsuarioLogado() {
		return idUsuarioLogado;
	}
	private void setIdUsuarioLogado(Integer idUsuarioLogado) {
		this.idUsuarioLogado = idUsuarioLogado;
	}
	public RADadosGeraisHelper idUsuarioLogado(Integer idUsuarioLogado){
		setIdUsuarioLogado(idUsuarioLogado);
		return this;
	}
	public Integer getIdFuncionario() {
		return idFuncionario;
	}
	private void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public RADadosGeraisHelper idFuncionario(Integer idFuncionario){
		setIdFuncionario(idFuncionario);
		return this;
	}
	public Integer getIdRAJAGerado() {
		return idRAJAGerado;
	}
	private void setIdRAJAGerado(Integer idRAJAGerado) {
		this.idRAJAGerado = idRAJAGerado;
	}
	public RADadosGeraisHelper idRAJAGerado(Integer idRAJAGerado){
		setIdRAJAGerado(idRAJAGerado);
		return this;
	}
	public String getProtocoloAtendimento() {
		return protocoloAtendimento;
	}
	private void setProtocoloAtendimento(String protocoloAtendimento) {
		this.protocoloAtendimento = protocoloAtendimento;
	}
	public RADadosGeraisHelper protocoloAtendimento(String protocoloAtendimento){
		setProtocoloAtendimento(protocoloAtendimento);
		return this;
	}
	public String getObservacaoOS() {
		return observacaoOS;
	}
	private void setObservacaoOS(String observacaoOS) {
		this.observacaoOS = observacaoOS;
	}
	public RADadosGeraisHelper observacaoOS(String observacaoOS){
		setObservacaoOS(observacaoOS);
		return this;
	}
	@SuppressWarnings("rawtypes")
	public Collection getColecaoRegistroAtendimentoAnexo() {
		return colecaoRegistroAtendimentoAnexo;
	}
	@SuppressWarnings("rawtypes")
	private void setColecaoRegistroAtendimentoAnexo(Collection colecaoRegistroAtendimentoAnexo) {
		this.colecaoRegistroAtendimentoAnexo = colecaoRegistroAtendimentoAnexo;
	}
	@SuppressWarnings("rawtypes")
	public RADadosGeraisHelper colecaoRegistroAtendimentoAnexo(Collection colecaoRegistroAtendimentoAnexo){
		setColecaoRegistroAtendimentoAnexo(colecaoRegistroAtendimentoAnexo);
		return this;
	}

	public RADadosGeraisHelper criar() throws Exception {
		StringBuffer mensagemErro = new StringBuffer();
		if(protocoloAtendimento == null){
			mensagemErro.append("Protocolo de Atendimento não foi preenchido.\n");
		}
		if(indicadorAtendimentoOnLine > 0){
			mensagemErro.append("Indicador de Atendimento não foi preenchido.\n");
		}
		if(dataAtendimento == null){
			mensagemErro.append("Data de Atendimento não foi preenchido.\n");
		}
		if(horaAtendimento == null){
			mensagemErro.append("Hora de Atendimento não foi preenchido.\n");
		}
		if(idUnidadeAtendimento == null){
			mensagemErro.append("Unidade de Atendimento não foi preenchido\n");
		}
		if(idMeioSolicitacao == null){
			mensagemErro.append("Meio de Solicitação não foi preenchido.\n");
		}
		if(idSolicitacaoTipo == null){
			mensagemErro.append("Tipo de Solicitação não foi preenchido.\n");
		}
		if(idSolicitacaoTipoEspecificacao == null){
			mensagemErro.append("Tipo de Especificação não foi preenchido.\n");
		}
		
		if(mensagemErro.length() > 0){
			throw new Exception(mensagemErro.toString());
		}
		
		return this;
	}
}
