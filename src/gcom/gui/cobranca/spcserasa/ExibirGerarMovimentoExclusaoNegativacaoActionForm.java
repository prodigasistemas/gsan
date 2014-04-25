package gcom.gui.cobranca.spcserasa;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Esta classe tem por finalidade gerar o formulário que receberá os parâmetros
 * para realização da inserção de um Geração da Movimentacao da Negativacao
 * 
 * @author Thiago Toscano 
 * @date 18/12/2006
 */
public class ExibirGerarMovimentoExclusaoNegativacaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
//	private String clienteObrigatorio;
//	
//	// ABA Nº 01
//	private String idNegativador;
//	private String tipo;
//	
//	
//	// ABA Nº 02
//	private String nomeNegativador;
//	private String identificacaoCI;
//	private String usuario;
//	private String nomeUsuario;
//	
//	//Adicionar
//	private String idImovelDebitos;
//	private String matriculaImovelDebitos;
//	private String situacaoEsgotoDebitos;
//	private String situacaoAguaDebitos;
//	private String codigoImovel;
//	private String tipoRelacao;
//	private String referenciaInicial;
//	private String referenciaFinal;
//	private String dataVencimentoInicial;
//	private String dataVencimentoFinal;
//	private String ligacaoAgua;
//	private String ligacaoEsgoto;
//	private String maticula;
//	private String inscricao;
//	private String nomeCliente;
//	private String tipoRelacaoCliente;
//	private String cpf;
//	private String cnpj;
//	private String refInicial;
//	private String refFinal;
//	private String dtInicial;
//	private String dtFinal;
//	private String enderecoFormatado;
//	private String clienteSelecionado;
//	
//	
//	
//	
//	
//	private String numeroAtendimentoManual;
//
//	private String dataAtendimento;
//
//	private String hora;
//
//	private String tempoEsperaInicial;
//
//	private String tempoEsperaFinal;
//
//	private String unidade;
//
//	private String descricaoUnidade;
//
//	private String meioSolicitacao;
//
//	private String tipoSolicitacao;
//
//	private String especificacao;
//
//	private String dataPrevista;
//
//	private String observacao;
//
//	// ABA Nº 02
//	private String imovelObrigatorio;
//
//	private String pavimentoRuaObrigatorio;
//
//	private String pavimentoCalcadaObrigatorio;
//
//	private String idImovel;
//
//	//private String inscricaoImovel;
//
//	private String pontoReferencia;
//
//	private String idMunicipio;
//
//	private String descricaoMunicipio;
//
//	private String cdBairro;
//
//	private String descricaoBairro;
//
//	private String idBairro;
//
//	private String idBairroArea;
//
//	private String idLocalidade;
//
//	private String descricaoLocalidade;
//
//	private String cdSetorComercial;
//
//	private String descricaoSetorComercial;
//
//	private String idSetorComercial;
//
//	private String nnQuadra;
//
//	private String idQuadra;
//
//	private String idDivisaoEsgoto;
//
//	private String idUnidadeDestino;
//
//	private String descricaoUnidadeDestino;
//
//	private String parecerUnidadeDestino;
//
//	private String idLocalOcorrencia;
//
//	private String idPavimentoRua;
//
//	private String idPavimentoCalcada;
//
//	private String descricaoLocalOcorrencia;
//
//	// ABA Nº 03
//
//	private String idImovelAssociacaoCliente;
//	
//	private String idCliente;
//
//	private String idUnidadeSolicitante;
//
//	private String descricaoUnidadeSolicitante;
//
//	private String idFuncionarioResponsavel;
//
//	private String nomeFuncionarioResponsavel;
//
//	private String nomeSolicitante;
//
//	private String clienteEnderecoSelected;
//
//	private String pontoReferenciaSolicitante;
//
//	private String clienteFoneSelected;
//
//	private String indicadorClienteEspecificacao;
//
//	private String indRuaLocalOcorrencia;
//
//	private String indCalcadaLocalOcorrencia;
//
//	private String indFaltaAgua;
//
//	private String indMatricula;
//	
//	
//	
//	
//	public String getIdImovelAssociacaoCliente() {
//		return idImovelAssociacaoCliente;
//	}
//
//	public void setIdImovelAssociacaoCliente(String idImovelAssociacaoCliente) {
//		this.idImovelAssociacaoCliente = idImovelAssociacaoCliente;
//	}
//
//	public String getNumeroAtendimentoManual() {
//		return numeroAtendimentoManual;
//	}
//
//	public void setNumeroAtendimentoManual(String numeroAtendimentoManual) {
//		this.numeroAtendimentoManual = numeroAtendimentoManual;
//	}
//
//	public String getIndFaltaAgua() {
//		return indFaltaAgua;
//	}
//
//	public void setIndFaltaAgua(String indFaltaAgua) {
//		this.indFaltaAgua = indFaltaAgua;
//	}
//
//	public String getIndMatricula() {
//		return indMatricula;
//	}
//
//	public void setIndMatricula(String indMatricula) {
//		this.indMatricula = indMatricula;
//	}
//
//	public String getIndCalcadaLocalOcorrencia() {
//		return indCalcadaLocalOcorrencia;
//	}
//
//	public void setIndCalcadaLocalOcorrencia(String indCalcadaLocalOcorrencia) {
//		this.indCalcadaLocalOcorrencia = indCalcadaLocalOcorrencia;
//	}
//
//	public String getIndRuaLocalOcorrencia() {
//		return indRuaLocalOcorrencia;
//	}
//
//	public void setIndRuaLocalOcorrencia(String indRuaLocalOcorrencia) {
//		this.indRuaLocalOcorrencia = indRuaLocalOcorrencia;
//	}
//
//	public String getIndicadorClienteEspecificacao() {
//		return indicadorClienteEspecificacao;
//	}
//
//	public void setIndicadorClienteEspecificacao(
//			String indicadorClienteEspecificacao) {
//		this.indicadorClienteEspecificacao = indicadorClienteEspecificacao;
//	}
//
//	public String getClienteEnderecoSelected() {
//		return clienteEnderecoSelected;
//	}
//
//	public void setClienteEnderecoSelected(String clienteEnderecoSelected) {
//		this.clienteEnderecoSelected = clienteEnderecoSelected;
//	}
//
//	public String getClienteFoneSelected() {
//		return clienteFoneSelected;
//	}
//
//	public void setClienteFoneSelected(String clienteFoneSelected) {
//		this.clienteFoneSelected = clienteFoneSelected;
//	}
//
//	public String getDescricaoUnidadeSolicitante() {
//		return descricaoUnidadeSolicitante;
//	}
//
//	public void setDescricaoUnidadeSolicitante(
//			String descricaoUnidadeSolicitante) {
//		this.descricaoUnidadeSolicitante = descricaoUnidadeSolicitante;
//	}
//
//	public String getIdCliente() {
//		return idCliente;
//	}
//
//	public void setIdCliente(String idCliente) {
//		this.idCliente = idCliente;
//	}
//
//	public String getIdFuncionarioResponsavel() {
//		return idFuncionarioResponsavel;
//	}
//
//	public void setIdFuncionarioResponsavel(String idFuncionarioResponsavel) {
//		this.idFuncionarioResponsavel = idFuncionarioResponsavel;
//	}
//
//	public String getIdUnidadeSolicitante() {
//		return idUnidadeSolicitante;
//	}
//
//	public void setIdUnidadeSolicitante(String idUnidadeSolicitante) {
//		this.idUnidadeSolicitante = idUnidadeSolicitante;
//	}
//
//	public String getNomeCliente() {
//		return nomeCliente;
//	}
//
//	public void setNomeCliente(String nomeCliente) {
//		this.nomeCliente = nomeCliente;
//	}
//
//	public String getNomeFuncionarioResponsavel() {
//		return nomeFuncionarioResponsavel;
//	}
//
//	public void setNomeFuncionarioResponsavel(String nomeFuncionarioResponsavel) {
//		this.nomeFuncionarioResponsavel = nomeFuncionarioResponsavel;
//	}
//
//	public String getNomeSolicitante() {
//		return nomeSolicitante;
//	}
//
//	public void setNomeSolicitante(String nomeSolicitante) {
//		this.nomeSolicitante = nomeSolicitante;
//	}
//
//	public String getPontoReferenciaSolicitante() {
//		return pontoReferenciaSolicitante;
//	}
//
//	public void setPontoReferenciaSolicitante(String pontoReferenciaSolicitante) {
//		this.pontoReferenciaSolicitante = pontoReferenciaSolicitante;
//	}
//
//	public String getPavimentoCalcadaObrigatorio() {
//		return pavimentoCalcadaObrigatorio;
//	}
//
//	public void setPavimentoCalcadaObrigatorio(
//			String pavimentoCalcadaObrigatorio) {
//		this.pavimentoCalcadaObrigatorio = pavimentoCalcadaObrigatorio;
//	}
//
//	public String getPavimentoRuaObrigatorio() {
//		return pavimentoRuaObrigatorio;
//	}
//
//	public void setPavimentoRuaObrigatorio(String pavimentoRuaObrigatorio) {
//		this.pavimentoRuaObrigatorio = pavimentoRuaObrigatorio;
//	}
//
//	public String getImovelObrigatorio() {
//		return imovelObrigatorio;
//	}
//
//	public void setImovelObrigatorio(String imovelObrigatorio) {
//		this.imovelObrigatorio = imovelObrigatorio;
//	}
//
//	public String getIdQuadra() {
//		return idQuadra;
//	}
//
//	public void setIdQuadra(String idQuadra) {
//		this.idQuadra = idQuadra;
//	}
//
//	public String getIdSetorComercial() {
//		return idSetorComercial;
//	}
//
//	public void setIdSetorComercial(String idSetorComercial) {
//		this.idSetorComercial = idSetorComercial;
//	}
//
//	public String getIdBairro() {
//		return idBairro;
//	}
//
//	public void setIdBairro(String idBairro) {
//		this.idBairro = idBairro;
//	}
//
//	public String getCdBairro() {
//		return cdBairro;
//	}
//
//	public void setCdBairro(String cdBairro) {
//		this.cdBairro = cdBairro;
//	}
//
//	public String getCdSetorComercial() {
//		return cdSetorComercial;
//	}
//
//	public void setCdSetorComercial(String cdSetorComercial) {
//		this.cdSetorComercial = cdSetorComercial;
//	}
//
//	public String getDescricaoBairro() {
//		return descricaoBairro;
//	}
//
//	public void setDescricaoBairro(String descricaoBairro) {
//		this.descricaoBairro = descricaoBairro;
//	}
//
//	public String getDescricaoLocalidade() {
//		return descricaoLocalidade;
//	}
//
//	public void setDescricaoLocalidade(String descricaoLocalidade) {
//		this.descricaoLocalidade = descricaoLocalidade;
//	}
//
//	public String getDescricaoLocalOcorrencia() {
//		return descricaoLocalOcorrencia;
//	}
//
//	public void setDescricaoLocalOcorrencia(String descricaoLocalOcorrencia) {
//		this.descricaoLocalOcorrencia = descricaoLocalOcorrencia;
//	}
//
//	public String getDescricaoMunicipio() {
//		return descricaoMunicipio;
//	}
//
//	public void setDescricaoMunicipio(String descricaoMunicipio) {
//		this.descricaoMunicipio = descricaoMunicipio;
//	}
//
//	public String getDescricaoSetorComercial() {
//		return descricaoSetorComercial;
//	}
//
//	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
//		this.descricaoSetorComercial = descricaoSetorComercial;
//	}
//
//	public String getDescricaoUnidadeDestino() {
//		return descricaoUnidadeDestino;
//	}
//
//	public void setDescricaoUnidadeDestino(String descricaoUnidadeDestino) {
//		this.descricaoUnidadeDestino = descricaoUnidadeDestino;
//	}
//
//	public String getIdBairroArea() {
//		return idBairroArea;
//	}
//
//	public void setIdBairroArea(String idBairroArea) {
//		this.idBairroArea = idBairroArea;
//	}
//
//	public String getIdDivisaoEsgoto() {
//		return idDivisaoEsgoto;
//	}
//
//	public void setIdDivisaoEsgoto(String idDivisaoEsgoto) {
//		this.idDivisaoEsgoto = idDivisaoEsgoto;
//	}
//
//	public String getIdImovel() {
//		return idImovel;
//	}
//
//	public void setIdImovel(String idImovel) {
//		this.idImovel = idImovel;
//	}
//
//	public String getIdLocalidade() {
//		return idLocalidade;
//	}
//
//	public void setIdLocalidade(String idLocalidade) {
//		this.idLocalidade = idLocalidade;
//	}
//
//	public String getIdLocalOcorrencia() {
//		return idLocalOcorrencia;
//	}
//
//	public void setIdLocalOcorrencia(String idLocalOcorrencia) {
//		this.idLocalOcorrencia = idLocalOcorrencia;
//	}
//
//	public String getIdMunicipio() {
//		return idMunicipio;
//	}
//
//	public void setIdMunicipio(String idMunicipio) {
//		this.idMunicipio = idMunicipio;
//	}
//
//	public String getIdPavimentoCalcada() {
//		return idPavimentoCalcada;
//	}
//
//	public void setIdPavimentoCalcada(String idPavimentoCalcada) {
//		this.idPavimentoCalcada = idPavimentoCalcada;
//	}
//
//	public String getIdPavimentoRua() {
//		return idPavimentoRua;
//	}
//
//	public void setIdPavimentoRua(String idPavimentoRua) {
//		this.idPavimentoRua = idPavimentoRua;
//	}
//
//	public String getNnQuadra() {
//		return nnQuadra;
//	}
//
//	public void setNnQuadra(String nnQuadra) {
//		this.nnQuadra = nnQuadra;
//	}
//
//	public String getParecerUnidadeDestino() {
//		return parecerUnidadeDestino;
//	}
//
//	public void setParecerUnidadeDestino(String parecerUnidadeDestino) {
//		this.parecerUnidadeDestino = parecerUnidadeDestino;
//	}
//
//	public String getPontoReferencia() {
//		return pontoReferencia;
//	}
//
//	public void setPontoReferencia(String pontoReferencia) {
//		this.pontoReferencia = pontoReferencia;
//	}
//
//	public String getIdUnidadeDestino() {
//		return idUnidadeDestino;
//	}
//
//	public void setIdUnidadeDestino(String idUnidadeDestino) {
//		this.idUnidadeDestino = idUnidadeDestino;
//	}
//
//	public String getObservacao() {
//		return observacao;
//	}
//
//	public void setObservacao(String observacao) {
//		this.observacao = observacao;
//	}
//
//	public String getDataAtendimento() {
//		return dataAtendimento;
//	}
//
//	public void setDataAtendimento(String dataAtendimento) {
//		this.dataAtendimento = dataAtendimento;
//	}
//
//	public String getDataPrevista() {
//		return dataPrevista;
//	}
//
//	public void setDataPrevista(String dataPrevista) {
//		this.dataPrevista = dataPrevista;
//	}
//
//	public String getDescricaoUnidade() {
//		return descricaoUnidade;
//	}
//
//	public void setDescricaoUnidade(String descricaoUnidade) {
//		this.descricaoUnidade = descricaoUnidade;
//	}
//
//	public String getEspecificacao() {
//		return especificacao;
//	}
//
//	public void setEspecificacao(String especificacao) {
//		this.especificacao = especificacao;
//	}
//
//	public String getHora() {
//		return hora;
//	}
//
//	public void setHora(String hora) {
//		this.hora = hora;
//	}
//
//	public String getMeioSolicitacao() {
//		return meioSolicitacao;
//	}
//
//	public void setMeioSolicitacao(String meioSolicitacao) {
//		this.meioSolicitacao = meioSolicitacao;
//	}
//
//	public String getTempoEsperaFinal() {
//		return tempoEsperaFinal;
//	}
//
//	public void setTempoEsperaFinal(String tempoEsperaFinal) {
//		this.tempoEsperaFinal = tempoEsperaFinal;
//	}
//
//	public String getTempoEsperaInicial() {
//		return tempoEsperaInicial;
//	}
//
//	public void setTempoEsperaInicial(String tempoEsperaInicial) {
//		this.tempoEsperaInicial = tempoEsperaInicial;
//	}
//
//	public String getTipo() {
//		return tipo;
//	}
//
//	public void setTipo(String tipo) {
//		this.tipo = tipo;
//	}
//
//	public String getTipoSolicitacao() {
//		return tipoSolicitacao;
//	}
//
//	public void setTipoSolicitacao(String tipoSolicitacao) {
//		this.tipoSolicitacao = tipoSolicitacao;
//	}
//
//	public String getUnidade() {
//		return unidade;
//	}
//
//	public void setUnidade(String unidade) {
//		this.unidade = unidade;
//	}
//
//	public String getClienteObrigatorio() {
//		return clienteObrigatorio;
//	}
//
//	public void setClienteObrigatorio(String clienteObrigatorio) {
//		this.clienteObrigatorio = clienteObrigatorio;
//	}
//
//	/**
//	 * @return Retorna o campo idNegativador.
//	 */
//	public String getIdNegativador() {
//		return idNegativador;
//	}
//
//	/**
//	 * @param idNegativador O idNegativador a ser setado.
//	 */
//	public void setIdNegativador(String idNegativador) {
//		this.idNegativador = idNegativador;
//	}
//
//	/**
//	 * @return Retorna o campo nomeNegativador.
//	 */
//	public String getNomeNegativador() {
//		return nomeNegativador;
//	}
//
//	/**
//	 * @param nomeNegativador O nomeNegativador a ser setado.
//	 */
//	public void setNomeNegativador(String nomeNegativador) {
//		this.nomeNegativador = nomeNegativador;
//	}
//
//	/**
//	 * @return Retorna o campo identificacaoCI.
//	 */
//	public String getIdentificacaoCI() {
//		return identificacaoCI;
//	}
//
//	/**
//	 * @param identificacaoCI O identificacaoCI a ser setado.
//	 */
//	public void setIdentificacaoCI(String identificacaoCI) {
//		this.identificacaoCI = identificacaoCI;
//	}
//
//	/**
//	 * @return Retorna o campo usuario.
//	 */
//	public String getUsuario() {
//		return usuario;
//	}
//
//	/**
//	 * @param usuario O usuario a ser setado.
//	 */
//	public void setUsuario(String usuario) {
//		this.usuario = usuario;
//	}
//
//	/**
//	 * @return Retorna o campo nomeUsuario.
//	 */
//	public String getNomeUsuario() {
//		return nomeUsuario;
//	}
//
//	/**
//	 * @param nomeUsuario O nomeUsuario a ser setado.
//	 */
//	public void setNomeUsuario(String nomeUsuario) {
//		this.nomeUsuario = nomeUsuario;
//	}
//
//	/**
//	 * @return Retorna o campo codigoImovel.
//	 */
//	public String getCodigoImovel() {
//		return codigoImovel;
//	}
//
//	/**
//	 * @param codigoImovel O codigoImovel a ser setado.
//	 */
//	public void setCodigoImovel(String codigoImovel) {
//		this.codigoImovel = codigoImovel;
//	}
//
//	/**
//	 * @return Retorna o campo cnpj.
//	 */
//	public String getCnpj() {
//		return cnpj;
//	}
//
//	/**
//	 * @param cnpj O cnpj a ser setado.
//	 */
//	public void setCnpj(String cnpj) {
//		this.cnpj = cnpj;
//	}
//
//	/**
//	 * @return Retorna o campo cpf.
//	 */
//	public String getCpf() {
//		return cpf;
//	}
//
//	/**
//	 * @param cpf O cpf a ser setado.
//	 */
//	public void setCpf(String cpf) {
//		this.cpf = cpf;
//	}
//
//	/**
//	 * @return Retorna o campo dataVencimentoFinal.
//	 */
//	public String getDataVencimentoFinal() {
//		return dataVencimentoFinal;
//	}
//
//	/**
//	 * @param dataVencimentoFinal O dataVencimentoFinal a ser setado.
//	 */
//	public void setDataVencimentoFinal(String dataVencimentoFinal) {
//		this.dataVencimentoFinal = dataVencimentoFinal;
//	}
//
//	/**
//	 * @return Retorna o campo dataVencimentoInicial.
//	 */
//	public String getDataVencimentoInicial() {
//		return dataVencimentoInicial;
//	}
//
//	/**
//	 * @param dataVencimentoInicial O dataVencimentoInicial a ser setado.
//	 */
//	public void setDataVencimentoInicial(String dataVencimentoInicial) {
//		this.dataVencimentoInicial = dataVencimentoInicial;
//	}
//
//	/**
//	 * @return Retorna o campo dtFinal.
//	 */
//	public String getDtFinal() {
//		return dtFinal;
//	}
//
//	/**
//	 * @param dtFinal O dtFinal a ser setado.
//	 */
//	public void setDtFinal(String dtFinal) {
//		this.dtFinal = dtFinal;
//	}
//
//	/**
//	 * @return Retorna o campo dtInicial.
//	 */
//	public String getDtInicial() {
//		return dtInicial;
//	}
//
//	/**
//	 * @param dtInicial O dtInicial a ser setado.
//	 */
//	public void setDtInicial(String dtInicial) {
//		this.dtInicial = dtInicial;
//	}
//
//	/**
//	 * @return Retorna o campo inscricao.
//	 */
//	public String getInscricao() {
//		return inscricao;
//	}
//
//	/**
//	 * @param inscricao O inscricao a ser setado.
//	 */
//	public void setInscricao(String inscricao) {
//		this.inscricao = inscricao;
//	}
//
//	/**
//	 * @return Retorna o campo ligacaoAgua.
//	 */
//	public String getLigacaoAgua() {
//		return ligacaoAgua;
//	}
//
//	/**
//	 * @param ligacaoAgua O ligacaoAgua a ser setado.
//	 */
//	public void setLigacaoAgua(String ligacaoAgua) {
//		this.ligacaoAgua = ligacaoAgua;
//	}
//
//	/**
//	 * @return Retorna o campo ligacaoEsgoto.
//	 */
//	public String getLigacaoEsgoto() {
//		return ligacaoEsgoto;
//	}
//
//	/**
//	 * @param ligacaoEsgoto O ligacaoEsgoto a ser setado.
//	 */
//	public void setLigacaoEsgoto(String ligacaoEsgoto) {
//		this.ligacaoEsgoto = ligacaoEsgoto;
//	}
//
//	/**
//	 * @return Retorna o campo maticula.
//	 */
//	public String getMaticula() {
//		return maticula;
//	}
//
//	/**
//	 * @param maticula O maticula a ser setado.
//	 */
//	public void setMaticula(String maticula) {
//		this.maticula = maticula;
//	}
//
//	/**
//	 * @return Retorna o campo matriculaImovelDebitos.
//	 */
//	public String getMatriculaImovelDebitos() {
//		return matriculaImovelDebitos;
//	}
//
//	/**
//	 * @param matriculaImovelDebitos O matriculaImovelDebitos a ser setado.
//	 */
//	public void setMatriculaImovelDebitos(String matriculaImovelDebitos) {
//		this.matriculaImovelDebitos = matriculaImovelDebitos;
//	}
//
//	/**
//	 * @return Retorna o campo referenciaFinal.
//	 */
//	public String getReferenciaFinal() {
//		return referenciaFinal;
//	}
//
//	/**
//	 * @param referenciaFinal O referenciaFinal a ser setado.
//	 */
//	public void setReferenciaFinal(String referenciaFinal) {
//		this.referenciaFinal = referenciaFinal;
//	}
//
//	/**
//	 * @return Retorna o campo referenciaInicial.
//	 */
//	public String getReferenciaInicial() {
//		return referenciaInicial;
//	}
//
//	/**
//	 * @param referenciaInicial O referenciaInicial a ser setado.
//	 */
//	public void setReferenciaInicial(String referenciaInicial) {
//		this.referenciaInicial = referenciaInicial;
//	}
//
//	/**
//	 * @return Retorna o campo refFinal.
//	 */
//	public String getRefFinal() {
//		return refFinal;
//	}
//
//	/**
//	 * @param refFinal O refFinal a ser setado.
//	 */
//	public void setRefFinal(String refFinal) {
//		this.refFinal = refFinal;
//	}
//
//	/**
//	 * @return Retorna o campo refInicial.
//	 */
//	public String getRefInicial() {
//		return refInicial;
//	}
//
//	/**
//	 * @param refInicial O refInicial a ser setado.
//	 */
//	public void setRefInicial(String refInicial) {
//		this.refInicial = refInicial;
//	}
//
//	/**
//	 * @return Retorna o campo situacaoAguaDebitos.
//	 */
//	public String getSituacaoAguaDebitos() {
//		return situacaoAguaDebitos;
//	}
//
//	/**
//	 * @param situacaoAguaDebitos O situacaoAguaDebitos a ser setado.
//	 */
//	public void setSituacaoAguaDebitos(String situacaoAguaDebitos) {
//		this.situacaoAguaDebitos = situacaoAguaDebitos;
//	}
//
//	/**
//	 * @return Retorna o campo situacaoEsgotoDebitos.
//	 */
//	public String getSituacaoEsgotoDebitos() {
//		return situacaoEsgotoDebitos;
//	}
//
//	/**
//	 * @param situacaoEsgotoDebitos O situacaoEsgotoDebitos a ser setado.
//	 */
//	public void setSituacaoEsgotoDebitos(String situacaoEsgotoDebitos) {
//		this.situacaoEsgotoDebitos = situacaoEsgotoDebitos;
//	}
//
//	/**
//	 * @return Retorna o campo tipoRelacao.
//	 */
//	public String getTipoRelacao() {
//		return tipoRelacao;
//	}
//
//	/**
//	 * @param tipoRelacao O tipoRelacao a ser setado.
//	 */
//	public void setTipoRelacao(String tipoRelacao) {
//		this.tipoRelacao = tipoRelacao;
//	}
//
//	/**
//	 * @return Retorna o campo tipoRelacaoCliente.
//	 */
//	public String getTipoRelacaoCliente() {
//		return tipoRelacaoCliente;
//	}
//
//	/**
//	 * @param tipoRelacaoCliente O tipoRelacaoCliente a ser setado.
//	 */
//	public void setTipoRelacaoCliente(String tipoRelacaoCliente) {
//		this.tipoRelacaoCliente = tipoRelacaoCliente;
//	}
//
//	/**
//	 * @return Retorna o campo idImovelDebitos.
//	 */
//	public String getIdImovelDebitos() {
//		return idImovelDebitos;
//	}
//
//	/**
//	 * @param idImovelDebitos O idImovelDebitos a ser setado.
//	 */
//	public void setIdImovelDebitos(String idImovelDebitos) {
//		this.idImovelDebitos = idImovelDebitos;
//	}
//
//	/**
//	 * @return Retorna o campo enderecoFormatado.
//	 */
//	public String getEnderecoFormatado() {
//		return enderecoFormatado;
//	}
//
//	/**
//	 * @param enderecoFormatado O enderecoFormatado a ser setado.
//	 */
//	public void setEnderecoFormatado(String enderecoFormatado) {
//		this.enderecoFormatado = enderecoFormatado;
//	}
//
//	/**
//	 * @return Retorna o campo clienteSelecionado.
//	 */
//	public String getClienteSelecionado() {
//		return clienteSelecionado;
//	}
//
//	/**
//	 * @param clienteSelecionado O clienteSelecionado a ser setado.
//	 */
//	public void setClienteSelecionado(String clienteSelecionado) {
//		this.clienteSelecionado = clienteSelecionado;
//	}

}
