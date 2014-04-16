package gcom.gui.micromedicao;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class LeituraConsumoActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
	private String tipoApresentacao;
	
	private String localidade;

	private String nomeLocalidade;

	private String setorComercialID;

	private String setorComercial;

	private String setorComercialNome;

	private String quadraInicial;

	private String quadraInicialNome;

	private String quadraInicialID;

	private String quadraInicialMensagem;

	private String quadraFinal;

	private String quadraFinalNome;

	private String quadraFinalID;

	private String quadraFinalMensagem;
	
	private String imovel;

	private String imovelCondominio;

	private String grupoFaturamento;

	private String empresaLeitura;

	private String indicadorImovelCondominio;

	private String perfilImovel;

	private String categoriaImovel;

	private String quantidadeEconomia;

	private String tipoMedicao;

	private String idTipoMedicao;

	private String tipoLigacao;

	private String tipoAnormalidade;

	private String anormalidadeLeituraInformada;

	private String anormalidadeLeituraFaturada;

	private String anormalidadeConsumo;
	
	private String consumoFaturamdoMinimo;

	private String consumoMedidoMinimo;

	private String consumoMedioMinimo;

	private String inscricaoTipo;

	private String mesAnoFaturamentoCorrente;

	private String inscricaoImovel;

	private String enderecoFormatado;

	private String ligacaoAguaSituacao;

	private String ligacaoEsgotoSituacao;

	private String clienteNome;

	private String clienteCpfCnpj;

	private String numeroHidrometro;

	private String capacidadeHidrometro;

	private String tipoHidrometro;

	private String marcaHidrometro;

	private String diametroHidrometro;

	private String instalacaoHidrometro;

	private String localInstalacaoHidrometro;

	private String anoFabricacao;

	private String protecaoHidrometro;

	private String indicadorCavalete;

	private String dtLeituraAnterior;

	private String leituraAnterior;

	private String dtLeituraInformada;

	private String leituraAtualInformada;

	private String situacaoLeituraAtual;

	private String codigoFuncionario;

	private String dtLeituraFaturada;

	private String leituraAtualFaturada;

	private String consumoRateio;

	private String consumoFaturado;
	
	private String consumoInformado;
	
	private String consumoMedido;

	private String percentualVariacao;

	private String consumoMedioHidrometro;

	private String diasConsumo;

	private String consumoTipo;

	private String idImovelSubstituirConsumo;

	private String habilitaLupa;

	private String idEmpresa;

	private String idGrupoFaturamento;

	private String localidadeFiltro;

	private String nomeLocalidadeFiltro;

	private String setorComercialFiltro;

	private String quadraInicialFiltro;

	private String quadraFinalFiltro;
	
	private String rotaFiltro;

	private String imovelFiltro;

	private String imovelCondominioFiltro;

	private String imovelMatriculaFiltro;

	private String imovelMatriculaCondominioFiltro;

	private String dataLigacaoAgua;

	private String dataCorteAgua;

	private String dataReligacaoAgua;

	private String dataSupressaoAgua;
	
	private String dataRestabelecimentoAgua;

	private String descricaoLigacaoAguaDiametro;

	private String descricaoLigacaoAguaMaterial;
	
	private String descricaoligacaoAguaPerfil;

	private String numeroConsumominimoAgua;

	private String idLigacaoEsgoto;
	
	private String consumoMesEsgoto;
	
	private String dataLigacaoEsgoto;

	private String descricaoLigacaoEsgotoDiametro;

	private String descricaoLigacaoEsgotoMaterial;

	private String descricaoligacaoEsgotoPerfil;

	private String numeroConsumominimoEsgoto;

	private String percentualEsgoto;

	private String percentualAguaConsumidaColetada;

	private String descricaoPocoTipo;

	private String idGrupoFaturamentoFiltro;

	private String idEmpresaFiltro;

	private String indicadorImovelCondominioFiltro;
	
	private String consumoMedioImovel;

	private String[] perfilImovelFiltro;

	private String categoriaImovelFiltro;

	private String quantidadeEconomiaFiltro;

	private String tipoMedicaoFiltro;

	private String idTipoMedicaoFiltro;

	private String tipoLigacaoFiltro;

	private String tipoAnormalidadeFiltro;

	private String[] anormalidadeLeituraInformadaFiltro;

	private String[] anormalidadeLeituraFaturadaFiltro;

	private String[] anormalidadeConsumoFiltro;

	private String consumoFaturadoInicialFiltro;
	
	private String consumoFaturadoFinalFiltro;

	private String consumoMedidoInicialFiltro;
	
	private String consumoMedidoFinalFiltro;

	private String consumoMedioInicialFiltro;
	
	private String consumoMedioFinalFiltro;
	
	private String valorAguaEsgotoInicialFiltro;
	
	private String valorAguaEsgotoFinalFiltro;
	
	private String idLigacaoAguaSituacao;
	
	private String idLigacaoAgua;
	
	private String idAnormalidade;
	
	private String descricaoAnormalidade;
	
	private String indicadorLeitura;
	
	private String dataLeituraAnteriorFaturamento;
	
	private String leituraAnteriorFaturamento;
	
	private String dataLeituraAtualInformada;
	
	private String consumo;
	
	private String confirmacao;
	
	private String dataLeituraAtualFaturamento;
	
	private String varConsumo;
	
	private String leituraSituacaoAtual;
	
	private String idFuncionario;
	
	private String consumoAnormalidadeAbreviada;
	
	private String indicadorDebitoAutomatico;
	
	private String rateio;
	
	private String leituraSituacaoAtualFiltro;
	
	private String medido;
	
	private String consumoMedidoHidrometro;
	
	private String rota;
	
	private String rotaMensagem;
	
	private String seqRota;
	
	 private String[] idRegistrosImovel;
	 
	private String mesAno;
	
	private String analisado;
	
	private String dataLeituraCampo;
	
	private String leituraCampo;
	
	private String nomeLeiturista;
	
	private String calculoConsumo;
	
	private String loginUsuario;
	
	private String nomeUsuario;
	
	private String gerarAviso;
	
	private String gerarOS;
	
	private String gerarRelatorio;
	
	private String indicadorAnalisado;
	
	private String idUsuarioAlteracao;
	
	private String loginUsuarioAlteracao;
	
	private String nomeUsuarioAlteracao;
	
	private Integer motivoInterferenciaTipo;
	
	
	private String leituraAnteriorFaturamentoHidden;
	private String carregaSomentePrimeiraVezHidden;
	private String auxHidden;
	
	private String observacao;
	
	
	
	/**
	 * @return Returns the auxHidden.
	 */
	public String getAuxHidden() {
		return auxHidden;
	}

	/**
	 * @param auxHidden The auxHidden to set.
	 */
	public void setAuxHidden(String auxHidden) {
		this.auxHidden = auxHidden;
	}

	/**
	 * @return Returns the carregaSomentePrimeiraVezHidden.
	 */
	public String getCarregaSomentePrimeiraVezHidden() {
		return carregaSomentePrimeiraVezHidden;
	}

	/**
	 * @param carregaSomentePrimeiraVezHidden The carregaSomentePrimeiraVezHidden to set.
	 */
	public void setCarregaSomentePrimeiraVezHidden(
			String carregaSomentePrimeiraVezHidden) {
		this.carregaSomentePrimeiraVezHidden = carregaSomentePrimeiraVezHidden;
	}

	/**
	 * @return Returns the leituraAnteriorFaturamentoHidden.
	 */
	public String getLeituraAnteriorFaturamentoHidden() {
		return leituraAnteriorFaturamentoHidden;
	}

	/**
	 * @param leituraAnteriorFaturamentoHidden The leituraAnteriorFaturamentoHidden to set.
	 */
	public void setLeituraAnteriorFaturamentoHidden(
			String leituraAnteriorFaturamentoHidden) {
		this.leituraAnteriorFaturamentoHidden = leituraAnteriorFaturamentoHidden;
	}

	public Integer getMotivoInterferenciaTipo() {
		return motivoInterferenciaTipo;
	}

	public void setMotivoInterferenciaTipo(Integer motivoInterferenciaTipo) {
		this.motivoInterferenciaTipo = motivoInterferenciaTipo;
	}

	/**
	 * @return Retorna o campo loginUsuarioAlteracao.
	 */
	public String getLoginUsuarioAlteracao() {
		return loginUsuarioAlteracao;
	}

	/**
	 * @param loginUsuarioAlteracao O loginUsuarioAlteracao a ser setado.
	 */
	public void setLoginUsuarioAlteracao(String loginUsuarioAlteracao) {
		this.loginUsuarioAlteracao = loginUsuarioAlteracao;
	}

	/**
	 * @return Retorna o campo indicadorAnalisado.
	 */
	public String getIndicadorAnalisado() {
		return indicadorAnalisado;
	}

	/**
	 * @param indicadorAnalisado O indicadorAnalisado a ser setado.
	 */
	public void setIndicadorAnalisado(String indicadorAnalisado) {
		this.indicadorAnalisado = indicadorAnalisado;
	}

	/**
	 * @return Retorna o campo nomeUsuarioAlteracao.
	 */
	public String getNomeUsuarioAlteracao() {
		return nomeUsuarioAlteracao;
	}

	/**
	 * @param nomeUsuarioAlteracao O nomeUsuarioAlteracao a ser setado.
	 */
	public void setNomeUsuarioAlteracao(String nomeUsuarioAlteracao) {
		this.nomeUsuarioAlteracao = nomeUsuarioAlteracao;
	}

	/**
	 * @return Retorna o campo analisado.
	 */
	public String getAnalisado() {
		return analisado;
	}

	/**
	 * @param analisado O analisado a ser setado.
	 */
	public void setAnalisado(String analisado) {
		this.analisado = analisado;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getConsumoMedidoHidrometro() {
		return consumoMedidoHidrometro;
	}

	public void setConsumoMedidoHidrometro(String consumoMedidoHidrometro) {
		this.consumoMedidoHidrometro = consumoMedidoHidrometro;
	}

	public String getMedido() {
		return medido;
	}

	public void setMedido(String medido) {
		this.medido = medido;
	}

	public String getRateio() {
		return rateio;
	}

	public void setRateio(String rateio) {
		this.rateio = rateio;
	}

	public String getIndicadorDebitoAutomatico() {
		return indicadorDebitoAutomatico;
	}

	public void setIndicadorDebitoAutomatico(String indicadorDebitoAutomatico) {
		this.indicadorDebitoAutomatico = indicadorDebitoAutomatico;
	}

	public String getConsumoAnormalidadeAbreviada() {
		return consumoAnormalidadeAbreviada;
	}

	public void setConsumoAnormalidadeAbreviada(String consumoAnormalidadeAbreviada) {
		this.consumoAnormalidadeAbreviada = consumoAnormalidadeAbreviada;
	}

	public String getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getLeituraSituacaoAtual() {
		return leituraSituacaoAtual;
	}

	public void setLeituraSituacaoAtual(String leituraSituacaoAtual) {
		this.leituraSituacaoAtual = leituraSituacaoAtual;
	}

	public String getVarConsumo() {
		return varConsumo;
	}

	public void setVarConsumo(String varConsumo) {
		this.varConsumo = varConsumo;
	}

	public String getDataLeituraAtualFaturamento() {
		return dataLeituraAtualFaturamento;
	}

	public void setDataLeituraAtualFaturamento(String dataLeituraAtualFaturamento) {
		this.dataLeituraAtualFaturamento = dataLeituraAtualFaturamento;
	}

	public String getConfirmacao() {
		return confirmacao;
	}

	public void setConfirmacao(String confirmacao) {
		this.confirmacao = confirmacao;
	}

	public String getConsumo() {
		return consumo;
	}

	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}

	public String getDataLeituraAnteriorFaturamento() {
		return dataLeituraAnteriorFaturamento;
	}

	public void setDataLeituraAnteriorFaturamento(
			String dataLeituraAnteriorFaturamento) {
		this.dataLeituraAnteriorFaturamento = dataLeituraAnteriorFaturamento;
	}

	public String getDataLeituraAtualInformada() {
		return dataLeituraAtualInformada;
	}

	public void setDataLeituraAtualInformada(String dataLeituraAtualInformada) {
		this.dataLeituraAtualInformada = dataLeituraAtualInformada;
	}

	public String getLeituraAnteriorFaturamento() {
		return leituraAnteriorFaturamento;
	}

	public void setLeituraAnteriorFaturamento(String leituraAnteriorFaturamento) {
		this.leituraAnteriorFaturamento = leituraAnteriorFaturamento;
	}

	public String getIdLigacaoAgua() {
		return idLigacaoAgua;
	}

	public void setIdLigacaoAgua(String idLigacaoAgua) {
		this.idLigacaoAgua = idLigacaoAgua;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	public void setIdGrupoFaturamento(String idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	public String getHabilitaLupa() {
		return habilitaLupa;
	}

	public void setHabilitaLupa(String habilitaLupa) {
		this.habilitaLupa = habilitaLupa;
	}

	public String getIdImovelSubstituirConsumo() {
		return idImovelSubstituirConsumo;
	}

	public void setIdImovelSubstituirConsumo(String idImovelSubstituirConsumo) {
		this.idImovelSubstituirConsumo = idImovelSubstituirConsumo;
	}

	public String getConsumoTipo() {
		return consumoTipo;
	}

	public void setConsumoTipo(String consumoTipo) {
		this.consumoTipo = consumoTipo;
	}

	public String getDiasConsumo() {
		return diasConsumo;
	}

	public void setDiasConsumo(String diasConsumo) {
		this.diasConsumo = diasConsumo;
	}

	public String getConsumoMedioHidrometro() {
		return consumoMedioHidrometro;
	}

	public void setConsumoMedioHidrometro(String consumoMedioHidrometro) {
		this.consumoMedioHidrometro = consumoMedioHidrometro;
	}

	public String getPercentualVariacao() {
		return percentualVariacao;
	}

	public void setPercentualVariacao(String percentualVariacao) {
		this.percentualVariacao = percentualVariacao;
	}

	public String getConsumoFaturado() {
		return consumoFaturado;
	}

	public void setConsumoFaturado(String consumoFaturado) {
		this.consumoFaturado = consumoFaturado;
	}

	public String getConsumoMedido() {
		return consumoMedido;
	}

	public void setConsumoMedido(String consumoMedido) {
		this.consumoMedido = consumoMedido;
	}

	public String getConsumoRateio() {
		return consumoRateio;
	}

	public void setConsumoRateio(String consumoRateio) {
		this.consumoRateio = consumoRateio;
	}

	public String getLeituraAtualFaturada() {
		return leituraAtualFaturada;
	}

	public void setLeituraAtualFaturada(String leituraAtualFaturada) {
		this.leituraAtualFaturada = leituraAtualFaturada;
	}

	public String getDtLeituraFaturada() {
		return dtLeituraFaturada;
	}

	public void setDtLeituraFaturada(String dtLeituraFaturada) {
		this.dtLeituraFaturada = dtLeituraFaturada;
	}

	public String getCodigoFuncionario() {
		return codigoFuncionario;
	}

	public void setCodigoFuncionario(String codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}

	public String getSituacaoLeituraAtual() {
		return situacaoLeituraAtual;
	}

	public void setSituacaoLeituraAtual(String situacaoLeituraAtual) {
		this.situacaoLeituraAtual = situacaoLeituraAtual;
	}

	public String getLeituraAtualInformada() {
		return leituraAtualInformada;
	}

	public void setLeituraAtualInformada(String leituraAtualInformada) {
		this.leituraAtualInformada = leituraAtualInformada;
	}

	public String getDtLeituraInformada() {
		return dtLeituraInformada;
	}

	public void setDtLeituraInformada(String dtLeituraInformada) {
		this.dtLeituraInformada = dtLeituraInformada;
	}

	public String getLeituraAnterior() {
		return leituraAnterior;
	}

	public void setLeituraAnterior(String leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}

	public String getDtLeituraAnterior() {
		return dtLeituraAnterior;
	}

	public void setDtLeituraAnterior(String dtLeituraAnterior) {
		this.dtLeituraAnterior = dtLeituraAnterior;
	}

	public String getIndicadorCavalete() {
		return indicadorCavalete;
	}

	public void setIndicadorCavalete(String indicadorCavalete) {
		this.indicadorCavalete = indicadorCavalete;
	}

	public String getProtecaoHidrometro() {
		return protecaoHidrometro;
	}

	public void setProtecaoHidrometro(String protecaoHidrometro) {
		this.protecaoHidrometro = protecaoHidrometro;
	}

	public String getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public String getCapacidadeHidrometro() {
		return capacidadeHidrometro;
	}

	public void setCapacidadeHidrometro(String capacidadeHidrometro) {
		this.capacidadeHidrometro = capacidadeHidrometro;
	}

	public String getDiametroHidrometro() {
		return diametroHidrometro;
	}

	public void setDiametroHidrometro(String diametroHidrometro) {
		this.diametroHidrometro = diametroHidrometro;
	}

	public String getMarcaHidrometro() {
		return marcaHidrometro;
	}

	public void setMarcaHidrometro(String marcaHidrometro) {
		this.marcaHidrometro = marcaHidrometro;
	}

	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}

	public String getTipoHidrometro() {
		return tipoHidrometro;
	}

	public void setTipoHidrometro(String tipoHidrometro) {
		this.tipoHidrometro = tipoHidrometro;
	}

	public String getClienteCpfCnpj() {
		return clienteCpfCnpj;
	}

	public void setClienteCpfCnpj(String clienteCpfCnpj) {
		this.clienteCpfCnpj = clienteCpfCnpj;
	}

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public String getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(String ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public String getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(String ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public String getEnderecoFormatado() {
		return enderecoFormatado;
	}

	public void setEnderecoFormatado(String enderecoFormatado) {
		this.enderecoFormatado = enderecoFormatado;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getInscricaoTipo() {
		return inscricaoTipo;
	}

	public void setInscricaoTipo(String inscricaoTipo) {
		this.inscricaoTipo = inscricaoTipo;
	}

	public String getAnormalidadeLeituraFaturada() {
		return anormalidadeLeituraFaturada;
	}

	public void setAnormalidadeLeituraFaturada(
			String anormalidadeLeituraFaturada) {
		this.anormalidadeLeituraFaturada = anormalidadeLeituraFaturada;
	}

	public String getAnormalidadeLeituraInformada() {
		return anormalidadeLeituraInformada;
	}

	public void setAnormalidadeLeituraInformada(
			String anormalidadeLeituraInformada) {
		this.anormalidadeLeituraInformada = anormalidadeLeituraInformada;
	}

	public String getCategoriaImovel() {
		return categoriaImovel;
	}

	public void setCategoriaImovel(String categoriaImovel) {
		this.categoriaImovel = categoriaImovel;
	}

	public String getConsumoFaturamdoMinimo() {
		return consumoFaturamdoMinimo;
	}

	public void setConsumoFaturamdoMinimo(String consumoFaturamdoMinimo) {
		this.consumoFaturamdoMinimo = consumoFaturamdoMinimo;
	}

	public String getConsumoMedidoMinimo() {
		return consumoMedidoMinimo;
	}

	public void setConsumoMedidoMinimo(String consumoMedidoMinimo) {
		this.consumoMedidoMinimo = consumoMedidoMinimo;
	}

	public String getConsumoMedioMinimo() {
		return consumoMedioMinimo;
	}

	public void setConsumoMedioMinimo(String consumoMedioMinimo) {
		this.consumoMedioMinimo = consumoMedioMinimo;
	}

	public String getEmpresaLeitura() {
		return empresaLeitura;
	}

	public void setEmpresaLeitura(String empresaLeitura) {
		this.empresaLeitura = empresaLeitura;
	}

	public String getGrupoFaturamento() {
		return grupoFaturamento;
	}

	public void setGrupoFaturamento(String grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}

	public String getImovel() {
		return imovel;
	}

	public void setImovel(String imovel) {
		this.imovel = imovel;
	}

	public String getImovelCondominio() {
		return imovelCondominio;
	}

	public String getIndicadorImovelCondominio() {
		return indicadorImovelCondominio;
	}

	public void setIndicadorImovelCondominio(String indicadorImovelCondominio) {
		this.indicadorImovelCondominio = indicadorImovelCondominio;
	}

	public String getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public String getQuantidadeEconomia() {
		return quantidadeEconomia;
	}

	public void setQuantidadeEconomia(String quantidadeEconomia) {
		this.quantidadeEconomia = quantidadeEconomia;
	}

	public String getTipoAnormalidade() {
		return tipoAnormalidade;
	}

	public void setTipoAnormalidade(String tipoAnormalidade) {
		this.tipoAnormalidade = tipoAnormalidade;
	}

	public String getTipoLigacao() {
		return tipoLigacao;
	}

	public void setTipoLigacao(String tipoLigacao) {
		this.tipoLigacao = tipoLigacao;
	}

	public String getTipoMedicao() {
		return tipoMedicao;
	}

	public void setTipoMedicao(String tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {

		return null;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 */
	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	public String getSetorComercialID() {
		return setorComercialID;
	}

	public void setSetorComercialID(String setorComercialID) {
		this.setorComercialID = setorComercialID;
	}

	public String getSetorComercialNome() {
		return setorComercialNome;
	}

	public void setSetorComercialNome(String setorComercialNome) {
		this.setorComercialNome = setorComercialNome;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getQuadraFinal() {
		return quadraFinal;
	}

	public void setQuadraFinal(String quadraFinal) {
		this.quadraFinal = quadraFinal;
	}

	public String getQuadraInicial() {
		return quadraInicial;
	}

	public void setQuadraInicial(String quadraInicial) {
		this.quadraInicial = quadraInicial;
	}

	public String getQuadraFinalNome() {
		return quadraFinalNome;
	}

	public void setQuadraFinalNome(String quadraFinalNome) {
		this.quadraFinalNome = quadraFinalNome;
	}

	public String getQuadraInicialNome() {
		return quadraInicialNome;
	}

	public void setQuadraInicialNome(String quadraInicialNome) {
		this.quadraInicialNome = quadraInicialNome;
	}

	public String getQuadraFinalMensagem() {
		return quadraFinalMensagem;
	}

	public void setQuadraFinalMensagem(String quadraFinalMensagem) {
		this.quadraFinalMensagem = quadraFinalMensagem;
	}

	public String getQuadraInicialMensagem() {
		return quadraInicialMensagem;
	}

	public void setQuadraInicialMensagem(String quadraInicialMensagem) {
		this.quadraInicialMensagem = quadraInicialMensagem;
	}

	public String getQuadraFinalID() {
		return quadraFinalID;
	}

	public void setQuadraFinalID(String quadraFinalID) {
		this.quadraFinalID = quadraFinalID;
	}

	public String getQuadraInicialID() {
		return quadraInicialID;
	}

	public void setQuadraInicialID(String quadraInicialID) {
		this.quadraInicialID = quadraInicialID;
	}

	public String getAnormalidadeConsumo() {
		return anormalidadeConsumo;
	}

	public void setAnormalidadeConsumo(String anormalidadeConsumo) {
		this.anormalidadeConsumo = anormalidadeConsumo;
	}

	public void setImovelCondominio(String imovelCondominio) {
		this.imovelCondominio = imovelCondominio;
	}

	public String getMesAnoFaturamentoCorrente() {
		return mesAnoFaturamentoCorrente;
	}

	public void setMesAnoFaturamentoCorrente(String mesAnoFaturamentoCorrente) {
		this.mesAnoFaturamentoCorrente = mesAnoFaturamentoCorrente;
	}

	public String getInstalacaoHidrometro() {
		return instalacaoHidrometro;
	}

	public void setInstalacaoHidrometro(String instalacaoHidrometro) {
		this.instalacaoHidrometro = instalacaoHidrometro;
	}

	public String getLocalInstalacaoHidrometro() {
		return localInstalacaoHidrometro;
	}

	public void setLocalInstalacaoHidrometro(String localInstalacaoHidrometro) {
		this.localInstalacaoHidrometro = localInstalacaoHidrometro;
	}

	public String getIdTipoMedicao() {
		return idTipoMedicao;
	}

	public void setIdTipoMedicao(String idTipoMedicao) {
		this.idTipoMedicao = idTipoMedicao;
	}

	public String[] getAnormalidadeConsumoFiltro() {
		return anormalidadeConsumoFiltro;
	}

	public void setAnormalidadeConsumoFiltro(String[] anormalidadeConsumoFiltro) {
		this.anormalidadeConsumoFiltro = anormalidadeConsumoFiltro;
	}

	public String[] getAnormalidadeLeituraFaturadaFiltro() {
		return anormalidadeLeituraFaturadaFiltro;
	}

	public void setAnormalidadeLeituraFaturadaFiltro(
			String[] anormalidadeLeituraFaturadaFiltro) {
		this.anormalidadeLeituraFaturadaFiltro = anormalidadeLeituraFaturadaFiltro;
	}

	public String[] getAnormalidadeLeituraInformadaFiltro() {
		return anormalidadeLeituraInformadaFiltro;
	}

	public void setAnormalidadeLeituraInformadaFiltro(
			String[] anormalidadeLeituraInformadaFiltro) {
		this.anormalidadeLeituraInformadaFiltro = anormalidadeLeituraInformadaFiltro;
	}

	public String getCategoriaImovelFiltro() {
		return categoriaImovelFiltro;
	}

	public void setCategoriaImovelFiltro(String categoriaImovelFiltro) {
		this.categoriaImovelFiltro = categoriaImovelFiltro;
	}

	/**
	 * @return Retorna o campo consumoFaturadoFinalFiltro.
	 */
	public String getConsumoFaturadoFinalFiltro() {
		return consumoFaturadoFinalFiltro;
	}

	/**
	 * @param consumoFaturadoFinalFiltro O consumoFaturadoFinalFiltro a ser setado.
	 */
	public void setConsumoFaturadoFinalFiltro(String consumoFaturadoFinalFiltro) {
		this.consumoFaturadoFinalFiltro = consumoFaturadoFinalFiltro;
	}

	/**
	 * @return Retorna o campo consumoFaturadoInicialFiltro.
	 */
	public String getConsumoFaturadoInicialFiltro() {
		return consumoFaturadoInicialFiltro;
	}

	/**
	 * @param consumoFaturadoInicialFiltro O consumoFaturadoInicialFiltro a ser setado.
	 */
	public void setConsumoFaturadoInicialFiltro(String consumoFaturadoInicialFiltro) {
		this.consumoFaturadoInicialFiltro = consumoFaturadoInicialFiltro;
	}

	/**
	 * @return Retorna o campo consumoMedidoFinalFiltro.
	 */
	public String getConsumoMedidoFinalFiltro() {
		return consumoMedidoFinalFiltro;
	}

	/**
	 * @param consumoMedidoFinalFiltro O consumoMedidoFinalFiltro a ser setado.
	 */
	public void setConsumoMedidoFinalFiltro(String consumoMedidoFinalFiltro) {
		this.consumoMedidoFinalFiltro = consumoMedidoFinalFiltro;
	}

	/**
	 * @return Retorna o campo consumoMedidoInicialFiltro.
	 */
	public String getConsumoMedidoInicialFiltro() {
		return consumoMedidoInicialFiltro;
	}

	/**
	 * @param consumoMedidoInicialFiltro O consumoMedidoInicialFiltro a ser setado.
	 */
	public void setConsumoMedidoInicialFiltro(String consumoMedidoInicialFiltro) {
		this.consumoMedidoInicialFiltro = consumoMedidoInicialFiltro;
	}

	/**
	 * @return Retorna o campo consumoMedioFinalFiltro.
	 */
	public String getConsumoMedioFinalFiltro() {
		return consumoMedioFinalFiltro;
	}

	/**
	 * @param consumoMedioFinalFiltro O consumoMedioFinalFiltro a ser setado.
	 */
	public void setConsumoMedioFinalFiltro(String consumoMedioFinalFiltro) {
		this.consumoMedioFinalFiltro = consumoMedioFinalFiltro;
	}

	/**
	 * @return Retorna o campo consumoMedioInicialFiltro.
	 */
	public String getConsumoMedioInicialFiltro() {
		return consumoMedioInicialFiltro;
	}

	/**
	 * @param consumoMedioInicialFiltro O consumoMedioInicialFiltro a ser setado.
	 */
	public void setConsumoMedioInicialFiltro(String consumoMedioInicialFiltro) {
		this.consumoMedioInicialFiltro = consumoMedioInicialFiltro;
	}

	public String getIdEmpresaFiltro() {
		return idEmpresaFiltro;
	}

	public void setIdEmpresaFiltro(String idEmpresaFiltro) {
		this.idEmpresaFiltro = idEmpresaFiltro;
	}

	public String getIdGrupoFaturamentoFiltro() {
		return idGrupoFaturamentoFiltro;
	}

	public void setIdGrupoFaturamentoFiltro(String idGrupoFaturamentoFiltro) {
		this.idGrupoFaturamentoFiltro = idGrupoFaturamentoFiltro;
	}

	public String getIdTipoMedicaoFiltro() {
		return idTipoMedicaoFiltro;
	}

	public void setIdTipoMedicaoFiltro(String idTipoMedicaoFiltro) {
		this.idTipoMedicaoFiltro = idTipoMedicaoFiltro;
	}

	public String getImovelCondominioFiltro() {
		return imovelCondominioFiltro;
	}

	public void setImovelCondominioFiltro(String imovelCondominioFiltro) {
		this.imovelCondominioFiltro = imovelCondominioFiltro;
	}

	public String getImovelFiltro() {
		return imovelFiltro;
	}

	public void setImovelFiltro(String imovelFiltro) {
		this.imovelFiltro = imovelFiltro;
	}

	public String getIndicadorImovelCondominioFiltro() {
		return indicadorImovelCondominioFiltro;
	}

	public void setIndicadorImovelCondominioFiltro(
			String indicadorImovelCondominioFiltro) {
		this.indicadorImovelCondominioFiltro = indicadorImovelCondominioFiltro;
	}

	public String getLocalidadeFiltro() {
		return localidadeFiltro;
	}

	public void setLocalidadeFiltro(String localidadeFiltro) {
		this.localidadeFiltro = localidadeFiltro;
	}

	public String getNomeLocalidadeFiltro() {
		return nomeLocalidadeFiltro;
	}

	public void setNomeLocalidadeFiltro(String nomeLocalidadeFiltro) {
		this.nomeLocalidadeFiltro = nomeLocalidadeFiltro;
	}

	public String[] getPerfilImovelFiltro() {
		return perfilImovelFiltro;
	}

	public void setPerfilImovelFiltro(String[] perfilImovelFiltro) {
		this.perfilImovelFiltro = perfilImovelFiltro;
	}

	public String getQuadraFinalFiltro() {
		return quadraFinalFiltro;
	}

	public void setQuadraFinalFiltro(String quadraFinalFiltro) {
		this.quadraFinalFiltro = quadraFinalFiltro;
	}

	public String getQuadraInicialFiltro() {
		return quadraInicialFiltro;
	}

	public void setQuadraInicialFiltro(String quadraInicialFiltro) {
		this.quadraInicialFiltro = quadraInicialFiltro;
	}

	public String getQuantidadeEconomiaFiltro() {
		return quantidadeEconomiaFiltro;
	}

	public void setQuantidadeEconomiaFiltro(String quantidadeEconomiaFiltro) {
		this.quantidadeEconomiaFiltro = quantidadeEconomiaFiltro;
	}

	public String getSetorComercialFiltro() {
		return setorComercialFiltro;
	}

	public void setSetorComercialFiltro(String setorComercialFiltro) {
		this.setorComercialFiltro = setorComercialFiltro;
	}

	public String getTipoAnormalidadeFiltro() {
		return tipoAnormalidadeFiltro;
	}

	public void setTipoAnormalidadeFiltro(String tipoAnormalidadeFiltro) {
		this.tipoAnormalidadeFiltro = tipoAnormalidadeFiltro;
	}

	public String getTipoLigacaoFiltro() {
		return tipoLigacaoFiltro;
	}

	public void setTipoLigacaoFiltro(String tipoLigacaoFiltro) {
		this.tipoLigacaoFiltro = tipoLigacaoFiltro;
	}

	public String getTipoMedicaoFiltro() {
		return tipoMedicaoFiltro;
	}

	public void setTipoMedicaoFiltro(String tipoMedicaoFiltro) {
		this.tipoMedicaoFiltro = tipoMedicaoFiltro;
	}

	public String getImovelMatriculaCondominioFiltro() {
		return imovelMatriculaCondominioFiltro;
	}

	public void setImovelMatriculaCondominioFiltro(
			String imovelMatriculaCondominioFiltro) {
		this.imovelMatriculaCondominioFiltro = imovelMatriculaCondominioFiltro;
	}

	public String getImovelMatriculaFiltro() {
		return imovelMatriculaFiltro;
	}

	public void setImovelMatriculaFiltro(String imovelMatriculaFiltro) {
		this.imovelMatriculaFiltro = imovelMatriculaFiltro;
	}

	public String getDataCorteAgua() {
		return dataCorteAgua;
	}

	public void setDataCorteAgua(String dataCorteAgua) {
		this.dataCorteAgua = dataCorteAgua;
	}

	public String getDataLigacaoAgua() {
		return dataLigacaoAgua;
	}

	public void setDataLigacaoAgua(String dataLigacaoAgua) {
		this.dataLigacaoAgua = dataLigacaoAgua;
	}

	public String getDataLigacaoEsgoto() {
		return dataLigacaoEsgoto;
	}

	public void setDataLigacaoEsgoto(String dataLigacaoEsgoto) {
		this.dataLigacaoEsgoto = dataLigacaoEsgoto;
	}

	public String getDataReligacaoAgua() {
		return dataReligacaoAgua;
	}

	public void setDataReligacaoAgua(String dataReligacaoAgua) {
		this.dataReligacaoAgua = dataReligacaoAgua;
	}

	public String getDataSupressaoAgua() {
		return dataSupressaoAgua;
	}

	public void setDataSupressaoAgua(String dataSupressaoAgua) {
		this.dataSupressaoAgua = dataSupressaoAgua;
	}

	public String getDescricaoLigacaoAguaDiametro() {
		return descricaoLigacaoAguaDiametro;
	}

	public void setDescricaoLigacaoAguaDiametro(
			String descricaoLigacaoAguaDiametro) {
		this.descricaoLigacaoAguaDiametro = descricaoLigacaoAguaDiametro;
	}

	public String getDescricaoLigacaoAguaMaterial() {
		return descricaoLigacaoAguaMaterial;
	}

	public void setDescricaoLigacaoAguaMaterial(
			String descricaoLigacaoAguaMaterial) {
		this.descricaoLigacaoAguaMaterial = descricaoLigacaoAguaMaterial;
	}

	public String getDescricaoLigacaoEsgotoDiametro() {
		return descricaoLigacaoEsgotoDiametro;
	}

	public void setDescricaoLigacaoEsgotoDiametro(
			String descricaoLigacaoEsgotoDiametro) {
		this.descricaoLigacaoEsgotoDiametro = descricaoLigacaoEsgotoDiametro;
	}

	public String getDescricaoLigacaoEsgotoMaterial() {
		return descricaoLigacaoEsgotoMaterial;
	}

	public void setDescricaoLigacaoEsgotoMaterial(
			String descricaoLigacaoEsgotoMaterial) {
		this.descricaoLigacaoEsgotoMaterial = descricaoLigacaoEsgotoMaterial;
	}

	public String getDescricaoligacaoEsgotoPerfil() {
		return descricaoligacaoEsgotoPerfil;
	}

	public void setDescricaoligacaoEsgotoPerfil(
			String descricaoligacaoEsgotoPerfil) {
		this.descricaoligacaoEsgotoPerfil = descricaoligacaoEsgotoPerfil;
	}

	public String getDescricaoPocoTipo() {
		return descricaoPocoTipo;
	}

	public void setDescricaoPocoTipo(String descricaoPocoTipo) {
		this.descricaoPocoTipo = descricaoPocoTipo;
	}

	public String getNumeroConsumominimoAgua() {
		return numeroConsumominimoAgua;
	}

	public void setNumeroConsumominimoAgua(String numeroConsumominimoAgua) {
		this.numeroConsumominimoAgua = numeroConsumominimoAgua;
	}

	public String getNumeroConsumominimoEsgoto() {
		return numeroConsumominimoEsgoto;
	}

	public void setNumeroConsumominimoEsgoto(String numeroConsumominimoEsgoto) {
		this.numeroConsumominimoEsgoto = numeroConsumominimoEsgoto;
	}

	public String getPercentualAguaConsumidaColetada() {
		return percentualAguaConsumidaColetada;
	}

	public void setPercentualAguaConsumidaColetada(
			String percentualAguaConsumidaColetada) {
		this.percentualAguaConsumidaColetada = percentualAguaConsumidaColetada;
	}

	public String getPercentualEsgoto() {
		return percentualEsgoto;
	}

	public void setPercentualEsgoto(String percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}

	public String getDataRestabelecimentoAgua() {
		return dataRestabelecimentoAgua;
	}

	public void setDataRestabelecimentoAgua(String dataRestabelecimentoAgua) {
		this.dataRestabelecimentoAgua = dataRestabelecimentoAgua;
	}

	public String getDescricaoligacaoAguaPerfil() {
		return descricaoligacaoAguaPerfil;
	}

	public void setDescricaoligacaoAguaPerfil(String descricaoligacaoAguaPerfil) {
		this.descricaoligacaoAguaPerfil = descricaoligacaoAguaPerfil;
	}

	public String getConsumoMedioImovel() {
		return consumoMedioImovel;
	}

	public void setConsumoMedioImovel(String consumoMedioImovel) {
		this.consumoMedioImovel = consumoMedioImovel;
	}

	public String getIdLigacaoEsgoto() {
		return idLigacaoEsgoto;
	}

	public void setIdLigacaoEsgoto(String idLigacaoEsgoto) {
		this.idLigacaoEsgoto = idLigacaoEsgoto;
	}

	public String getConsumoMesEsgoto() {
		return consumoMesEsgoto;
	}

	public void setConsumoMesEsgoto(String consumoMesEsgoto) {
		this.consumoMesEsgoto = consumoMesEsgoto;
	}

	public String getIdLigacaoAguaSituacao() {
		return idLigacaoAguaSituacao;
	}

	public void setIdLigacaoAguaSituacao(String idLigacaoAguaSituacao) {
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
	}

	public String getTipoApresentacao() {
		return tipoApresentacao;
	}

	public void setTipoApresentacao(String tipoApresentacao) {
		this.tipoApresentacao = tipoApresentacao;
	}

	public String getDescricaoAnormalidade() {
		return descricaoAnormalidade;
	}

	public void setDescricaoAnormalidade(String descricaoAnormalidade) {
		this.descricaoAnormalidade = descricaoAnormalidade;
	}

	public String getIdAnormalidade() {
		return idAnormalidade;
	}

	public void setIdAnormalidade(String idAnormalidade) {
		this.idAnormalidade = idAnormalidade;
	}

	public String getConsumoInformado() {
		return consumoInformado;
	}

	public void setConsumoInformado(String consumoInformado) {
		this.consumoInformado = consumoInformado;
	}

	public String getLeituraSituacaoAtualFiltro() {
		return leituraSituacaoAtualFiltro;
	}

	public void setLeituraSituacaoAtualFiltro(String leituraSituacaoAtualFiltro) {
		this.leituraSituacaoAtualFiltro = leituraSituacaoAtualFiltro;
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public String getSeqRota() {
		return seqRota;
	}

	public void setSeqRota(String seqRota) {
		this.seqRota = seqRota;
	}

	public String[] getIdRegistrosImovel() {
		return idRegistrosImovel;
	}

	public void setIdRegistrosImovel(String[] idRegistrosImovel) {
		this.idRegistrosImovel = idRegistrosImovel;
	}

	
	public String getRotaMensagem() {
	
		return rotaMensagem;
	}

	
	public void setRotaMensagem(String rotaMensagem) {
	
		this.rotaMensagem = rotaMensagem;
	}

	
	public String getRotaFiltro() {
	
		return rotaFiltro;
	}

	
	public void setRotaFiltro(String rotaFiltro) {
	
		this.rotaFiltro = rotaFiltro;
	}

	/**
	 * @return Retorna o campo leituraCampo.
	 */
	public String getLeituraCampo() {
		return leituraCampo;
	}

	/**
	 * @param leituraCampo O leituraCampo a ser setado.
	 */
	public void setLeituraCampo(String leituraCampo) {
		this.leituraCampo = leituraCampo;
	}

	/**
	 * @return Retorna o campo nomeLeiturista.
	 */
	public String getNomeLeiturista() {
		return nomeLeiturista;
	}

	/**
	 * @param nomeLeiturista O nomeLeiturista a ser setado.
	 */
	public void setNomeLeiturista(String nomeLeiturista) {
		this.nomeLeiturista = nomeLeiturista;
	}

	/**
	 * @return Retorna o campo calculoConsumo.
	 */
	public String getCalculoConsumo() {
		return calculoConsumo;
	}

	/**
	 * @param calculoConsumo O calculoConsumo a ser setado.
	 */
	public void setCalculoConsumo(String calculoConsumo) {
		this.calculoConsumo = calculoConsumo;
	}

	/**
	 * @return Retorna o campo gerarAviso.
	 */
	public String getGerarAviso() {
		return gerarAviso;
	}

	/**
	 * @param gerarAviso O gerarAviso a ser setado.
	 */
	public void setGerarAviso(String gerarAviso) {
		this.gerarAviso = gerarAviso;
	}

	/**
	 * @return Retorna o campo gerarOS.
	 */
	public String getGerarOS() {
		return gerarOS;
	}

	/**
	 * @param gerarOS O gerarOS a ser setado.
	 */
	public void setGerarOS(String gerarOS) {
		this.gerarOS = gerarOS;
	}

	/**
	 * @return Retorna o campo gerarRelatorio.
	 */
	public String getGerarRelatorio() {
		return gerarRelatorio;
	}

	/**
	 * @param gerarRelatorio O gerarRelatorio a ser setado.
	 */
	public void setGerarRelatorio(String gerarRelatorio) {
		this.gerarRelatorio = gerarRelatorio;
	}

	/**
	 * @return Retorna o campo dataLeituraCampo.
	 */
	public String getDataLeituraCampo() {
		return dataLeituraCampo;
	}

	/**
	 * @param dataLeituraCampo O dataLeituraCampo a ser setado.
	 */
	public void setDataLeituraCampo(String dataLeituraCampo) {
		this.dataLeituraCampo = dataLeituraCampo;
	}

	/**
	 * @return Retorna o campo loginUsuario.
	 */
	public String getLoginUsuario() {
		return loginUsuario;
	}

	/**
	 * @param loginUsuario O loginUsuario a ser setado.
	 */
	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	/**
	 * @return Retorna o campo nomeUsuario.
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	/**
	 * @param nomeUsuario O nomeUsuario a ser setado.
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	/**
	 * @return Retorna o campo idUsuarioAlteracao.
	 */
	public String getIdUsuarioAlteracao() {
		return idUsuarioAlteracao;
	}

	/**
	 * @param idUsuarioAlteracao O idUsuarioAlteracao a ser setado.
	 */
	public void setIdUsuarioAlteracao(String idUsuarioAlteracao) {
		this.idUsuarioAlteracao = idUsuarioAlteracao;
	}
	
	public String getValorAguaEsgotoFinalFiltro() {
		return valorAguaEsgotoFinalFiltro;
	}

	public void setValorAguaEsgotoFinalFiltro(String valorAguaEsgotoFinalFiltro) {
		this.valorAguaEsgotoFinalFiltro = valorAguaEsgotoFinalFiltro;
	}

	public String getValorAguaEsgotoInicialFiltro() {
		return valorAguaEsgotoInicialFiltro;
	}

	public void setValorAguaEsgotoInicialFiltro(String valorAguaEsgotoInicialFiltro) {
		this.valorAguaEsgotoInicialFiltro = valorAguaEsgotoInicialFiltro;
	}
	
	public String getIndicadorLeitura() {
		return indicadorLeitura;
	}

	public void setIndicadorLeitura(String indicadorLeitura) {
		this.indicadorLeitura = indicadorLeitura;
	}	
	

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
