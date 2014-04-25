package gcom.gui.atendimentopublico.registroatendimento;

import org.apache.struts.validator.ValidatorForm;

/**
 * Esta classe tem por finalidade gerar o formulário que receberá os parâmetros
 * para realização da atualização de um R.A
 * 
 * @author Sávio Luiz
 * @date 10/08/2006
 */
public class AtualizarRegistroAtendimentoActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
	private String idOrdemServico;

	// ABA Nº 01
	private String numeroRA;
	
	private String numeroAtendimentoManual;

	private String tipo;

	private String dataAtendimento;

	private String hora;

	private String tempoEsperaInicial;

	private String tempoEsperaFinal;

	private String unidade;

	private String descricaoUnidade;

	private String meioSolicitacao;

	private String tipoSolicitacao;

	private String especificacao;

	private String dataPrevista;
	
	private String valorSugerido;

	private String observacao;

	// ABA Nº 02
	private String imovelObrigatorio;

	private String pavimentoRuaObrigatorio;

	private String pavimentoCalcadaObrigatorio;

	private String idImovel;

	private String inscricaoImovel;

	private String pontoReferencia;

	private String idMunicipio;

	private String descricaoMunicipio;

	private String cdBairro;

	private String descricaoBairro;

	private String idBairro;

	private String idBairroArea;

	private String idLocalidade;

	private String descricaoLocalidade;

	private String cdSetorComercial;

	private String descricaoSetorComercial;

	private String idSetorComercial;

	private String nnQuadra;

	private String idQuadra;

	private String idDivisaoEsgoto;

	private String idUnidadeAtual;

	private String descricaoUnidadeAtual;

	private String parecerUnidadeDestino;

	private String idLocalOcorrencia;

	private String idPavimentoRua;

	private String idPavimentoCalcada;

	private String descricaoLocalOcorrencia;

	private String indValidacaoMatriculaImovel;

	private String enderecoOcorrencia;

	private String indRuaLocalOcorrencia;

	private String indCalcadaLocalOcorrencia;

	private String indFaltaAgua;

	private String indMatricula;
	
	private String idSolicitantePrincipal;
	
	private String nnCoordenadaNorte;
	
	private String nnCoordenadaLeste;
	
	private String idConta;
	
	private String descConta;
	
	private String idContaPesquisada;
	
	private String descricaoMunicipioOcorrencia;
	
	private String nnDiametro;

	public String getNnDiametro() {
		return nnDiametro;
	}

	public void setNnDiametro(String nnDiametro) {
		this.nnDiametro = nnDiametro;
	}

	public String getNumeroAtendimentoManual() {
		return numeroAtendimentoManual;
	}

	public void setNumeroAtendimentoManual(String numeroAtendimentoManual) {
		this.numeroAtendimentoManual = numeroAtendimentoManual;
	}

	public String getIndFaltaAgua() {
		return indFaltaAgua;
	}

	public void setIndFaltaAgua(String indFaltaAgua) {
		this.indFaltaAgua = indFaltaAgua;
	}

	public String getIndMatricula() {
		return indMatricula;
	}

	public void setIndMatricula(String indMatricula) {
		this.indMatricula = indMatricula;
	}

	public String getIdQuadra() {
		return idQuadra;
	}

	public void setIdQuadra(String idQuadra) {
		this.idQuadra = idQuadra;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public String getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(String idBairro) {
		this.idBairro = idBairro;
	}

	public String getCdBairro() {
		return cdBairro;
	}

	public void setCdBairro(String cdBairro) {
		this.cdBairro = cdBairro;
	}

	public String getCdSetorComercial() {
		return cdSetorComercial;
	}

	public void setCdSetorComercial(String cdSetorComercial) {
		this.cdSetorComercial = cdSetorComercial;
	}

	public String getDescricaoBairro() {
		return descricaoBairro;
	}

	public void setDescricaoBairro(String descricaoBairro) {
		this.descricaoBairro = descricaoBairro;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getDescricaoLocalOcorrencia() {
		return descricaoLocalOcorrencia;
	}

	public void setDescricaoLocalOcorrencia(String descricaoLocalOcorrencia) {
		this.descricaoLocalOcorrencia = descricaoLocalOcorrencia;
	}

	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}

	public void setDescricaoMunicipio(String descricaoMunicipio) {
		this.descricaoMunicipio = descricaoMunicipio;
	}

	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}

	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}

	public String getIdBairroArea() {
		return idBairroArea;
	}

	public void setIdBairroArea(String idBairroArea) {
		this.idBairroArea = idBairroArea;
	}

	public String getIdDivisaoEsgoto() {
		return idDivisaoEsgoto;
	}

	public void setIdDivisaoEsgoto(String idDivisaoEsgoto) {
		this.idDivisaoEsgoto = idDivisaoEsgoto;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdLocalOcorrencia() {
		return idLocalOcorrencia;
	}

	public void setIdLocalOcorrencia(String idLocalOcorrencia) {
		this.idLocalOcorrencia = idLocalOcorrencia;
	}

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getIdPavimentoCalcada() {
		return idPavimentoCalcada;
	}

	public void setIdPavimentoCalcada(String idPavimentoCalcada) {
		this.idPavimentoCalcada = idPavimentoCalcada;
	}

	public String getIdPavimentoRua() {
		return idPavimentoRua;
	}

	public void setIdPavimentoRua(String idPavimentoRua) {
		this.idPavimentoRua = idPavimentoRua;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getNnQuadra() {
		return nnQuadra;
	}

	public void setNnQuadra(String nnQuadra) {
		this.nnQuadra = nnQuadra;
	}

	public String getParecerUnidadeDestino() {
		return parecerUnidadeDestino;
	}

	public void setParecerUnidadeDestino(String parecerUnidadeDestino) {
		this.parecerUnidadeDestino = parecerUnidadeDestino;
	}

	public String getPontoReferencia() {
		return pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(String dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public String getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public String getDescricaoUnidade() {
		return descricaoUnidade;
	}

	public void setDescricaoUnidade(String descricaoUnidade) {
		this.descricaoUnidade = descricaoUnidade;
	}

	public String getEspecificacao() {
		return especificacao;
	}

	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getMeioSolicitacao() {
		return meioSolicitacao;
	}

	public void setMeioSolicitacao(String meioSolicitacao) {
		this.meioSolicitacao = meioSolicitacao;
	}

	public String getTempoEsperaFinal() {
		return tempoEsperaFinal;
	}

	public void setTempoEsperaFinal(String tempoEsperaFinal) {
		this.tempoEsperaFinal = tempoEsperaFinal;
	}

	public String getTempoEsperaInicial() {
		return tempoEsperaInicial;
	}

	public void setTempoEsperaInicial(String tempoEsperaInicial) {
		this.tempoEsperaInicial = tempoEsperaInicial;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipoSolicitacao() {
		return tipoSolicitacao;
	}

	public void setTipoSolicitacao(String tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getNumeroRA() {
		return numeroRA;
	}

	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}

	public String getIndValidacaoMatriculaImovel() {
		return indValidacaoMatriculaImovel;
	}

	public void setIndValidacaoMatriculaImovel(
			String indValidacaoMatriculaImovel) {
		this.indValidacaoMatriculaImovel = indValidacaoMatriculaImovel;
	}

	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public String getEnderecoOcorrencia() {
		return enderecoOcorrencia;
	}

	public void setEnderecoOcorrencia(String enderecoOcorrencia) {
		this.enderecoOcorrencia = enderecoOcorrencia;
	}

	public String getImovelObrigatorio() {
		return imovelObrigatorio;
	}

	public void setImovelObrigatorio(String imovelObrigatorio) {
		this.imovelObrigatorio = imovelObrigatorio;
	}

	public String getPavimentoCalcadaObrigatorio() {
		return pavimentoCalcadaObrigatorio;
	}

	public void setPavimentoCalcadaObrigatorio(
			String pavimentoCalcadaObrigatorio) {
		this.pavimentoCalcadaObrigatorio = pavimentoCalcadaObrigatorio;
	}

	public String getPavimentoRuaObrigatorio() {
		return pavimentoRuaObrigatorio;
	}

	public void setPavimentoRuaObrigatorio(String pavimentoRuaObrigatorio) {
		this.pavimentoRuaObrigatorio = pavimentoRuaObrigatorio;
	}

	public String getDescricaoUnidadeAtual() {
		return descricaoUnidadeAtual;
	}

	public void setDescricaoUnidadeAtual(String descricaoUnidadeAtual) {
		this.descricaoUnidadeAtual = descricaoUnidadeAtual;
	}

	public String getIdUnidadeAtual() {
		return idUnidadeAtual;
	}

	public void setIdUnidadeAtual(String idUnidadeAtual) {
		this.idUnidadeAtual = idUnidadeAtual;
	}

	public String getIndCalcadaLocalOcorrencia() {
		return indCalcadaLocalOcorrencia;
	}

	public void setIndCalcadaLocalOcorrencia(String indCalcadaLocalOcorrencia) {
		this.indCalcadaLocalOcorrencia = indCalcadaLocalOcorrencia;
	}

	public String getIndRuaLocalOcorrencia() {
		return indRuaLocalOcorrencia;
	}

	public void setIndRuaLocalOcorrencia(String indRuaLocalOcorrencia) {
		this.indRuaLocalOcorrencia = indRuaLocalOcorrencia;
	}

	public String getIdSolicitantePrincipal() {
		return idSolicitantePrincipal;
	}

	public void setIdSolicitantePrincipal(String idSolicitantePrincipal) {
		this.idSolicitantePrincipal = idSolicitantePrincipal;
	}

	public String getValorSugerido() {
		return valorSugerido;
	}

	public void setValorSugerido(String valorSugerido) {
		this.valorSugerido = valorSugerido;
	}
	public String getNnCoordenadaLeste() {
		return nnCoordenadaLeste;
	}
	public void setNnCoordenadaLeste(String nnCoordenadaLeste) {
		this.nnCoordenadaLeste = nnCoordenadaLeste;
	}

	/**
	 * @return Retorna o campo nnCoordenadaNorte.
	 */
	public String getNnCoordenadaNorte() {
		return nnCoordenadaNorte;
	}

	/**
	 * @param nnCoordenadaNorte O nnCoordenadaNorte a ser setado.
	 */
	public void setNnCoordenadaNorte(String nnCoordenadaNorte) {
		this.nnCoordenadaNorte = nnCoordenadaNorte;
	}

	public String getDescConta() {
		return descConta;
	}

	public void setDescConta(String descConta) {
		this.descConta = descConta;
	}

	public String getIdConta() {
		return idConta;
	}

	public void setIdConta(String idConta) {
		this.idConta = idConta;
	}

	public String getIdContaPesquisada() {
		return idContaPesquisada;
	}

	public void setIdContaPesquisada(String idContaPesquisada) {
		this.idContaPesquisada = idContaPesquisada;
	}

	public String getDescricaoMunicipioOcorrencia() {
		return descricaoMunicipioOcorrencia;
	}

	public void setDescricaoMunicipioOcorrencia(String descricaoMunicipioOcorrencia) {
		this.descricaoMunicipioOcorrencia = descricaoMunicipioOcorrencia;
	}
}
