package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0450] Filtrar Registro de Atendimento
 * 
 * Classe facilitadora para o retorno do filtro a ser usado no manter.
 * 
 * @author Rafael Pinto
 * @date 18/08/2006
 */
public class RelatorioOrdemServicoBean implements RelatorioBean {

	private String idOrdemServico;
	private String dataGeracao;
	private String dataEmissao;
	private String inscricaoImovel;
	private String idImovel;
	private String categoriaQtdeEconomias;
	private String unidadeGeracao;
	private String situacaoAguaEsgoto;
	private String esgotoFixo;
	private String pavimentoRua;
	private String pavimentoCalcada;
	private String meio;
	private String endereco;
	private String nomeAtendente;
	private String idAtendente;
	private String pontoReferencia;
	private String telefone;
	private String servicoSolicitado;
	private String valorSolicitado;
	private String localOcorrencia;
	private String previsao;
	private String observacaoRA;
	private String observacaoOS;
	private String solicitante;
	private String unidade;
	private String idRA;
	private String tipoSolicitanteUsuario;
	private String tipoSolicitanteEmpresa;
	private String especificacao;
	private String tempoMedioExecucao;
	private String origem;
	private String destino;
	private String enderecoFone;
	private String localidadeRota;
	private String pavimentoRuaCalcada;
	private String servicoTipoReferencia;
	private String nomeProjeto;
	
	
	private String anoMesHistoricoConsumo1;
	private String dtLeituraAtualInformada1;
	private String leituraAtualInformada1;
	private String consumoFaturado1;
	private String descAbrevAnormalidadeConsumo1;
	private String descAbrevAnormalidadeLeitura1;
	
	private String anoMesHistoricoConsumo2;
	private String dtLeituraAtualInformada2;
	private String leituraAtualInformada2;
	private String consumoFaturado2;
	private String descAbrevAnormalidadeConsumo2;
	private String descAbrevAnormalidadeLeitura2;
	
	private String anoMesHistoricoConsumo3;
	private String dtLeituraAtualInformada3;
	private String leituraAtualInformada3;
	private String consumoFaturado3;
	private String descAbrevAnormalidadeConsumo3;
	private String descAbrevAnormalidadeLeitura3;
	
	private String anoMesHistoricoConsumo4;
	private String dtLeituraAtualInformada4;
	private String leituraAtualInformada4;
	private String consumoFaturado4;
	private String descAbrevAnormalidadeConsumo4;
	private String descAbrevAnormalidadeLeitura4;
	
	private String anoMesHistoricoConsumo5;
	private String dtLeituraAtualInformada5;
	private String leituraAtualInformada5;
	private String consumoFaturado5;
	private String descAbrevAnormalidadeConsumo5;
	private String descAbrevAnormalidadeLeitura5;
	
	private String anoMesHistoricoConsumo6;
	private String dtLeituraAtualInformada6;
	private String leituraAtualInformada6;
	private String consumoFaturado6;
	private String descAbrevAnormalidadeConsumo6;
	private String descAbrevAnormalidadeLeitura6;
	
	private String hidrometroNumero;
	private String hidrometroFixo;
	private String hidrometroMarca;
	private String hidrometroCapacidade;
	private String hidrometroDiametro;
	private String hidrometroLocal;
	private String hidrometroLeitura;
	private String hidrometroNumeroDigitos;
	
	private String dataEncerramento;
	private String parecerEncerramento;
	private String cpfCnpjCliente;
	
	private String imovelPerfil;
	
	public RelatorioOrdemServicoBean(
			String idOrdemServico, 
			String dataGeracao,
			String dataEmissao,
			String inscricaoImovel, 
			String idImovel, 
			String categoriaQtdeEconomias, 
			String unidadeGeracao,
			String situacaoAguaEsgoto, 
			String esgotoFixo, 
			String pavimentoRua, 
			String pavimentoCalcada,
			String meio, 
			String endereco, 
			String nomeAtendente, 
			String idAtendente,
			String pontoReferencia, 
			String telefone, 
			String servicoSolicitado,
			String localOcorrencia, 
			String previsao, 
			String observacaoRA, 
			String observacaoOS,
			String solicitante, 
			String unidade, 
			String idRA, 
			String tipoSolicitanteUsuario,
			String tipoSolicitanteEmpresa, 
			String especificacao) {
		
		this.idOrdemServico = idOrdemServico;
		this.dataGeracao = dataGeracao;
		this.dataEmissao = dataEmissao;
		this.inscricaoImovel = inscricaoImovel;
		this.idImovel = idImovel;
		this.categoriaQtdeEconomias = categoriaQtdeEconomias;
		this.unidadeGeracao = unidadeGeracao;
		this.situacaoAguaEsgoto = situacaoAguaEsgoto;
		this.esgotoFixo = esgotoFixo;
		this.pavimentoRua = pavimentoRua;
		this.pavimentoCalcada = pavimentoCalcada;
		this.meio = meio;
		this.endereco = endereco;
		this.nomeAtendente = nomeAtendente;
		this.idAtendente = idAtendente;
		this.pontoReferencia = pontoReferencia;
		this.telefone = telefone;
		this.servicoSolicitado = servicoSolicitado;
		this.localOcorrencia = localOcorrencia;
		this.previsao = previsao;
		this.observacaoRA = observacaoRA;
		this.observacaoOS = observacaoOS;
		this.solicitante = solicitante;
		this.unidade = unidade;
		this.idRA = idRA;
		this.tipoSolicitanteUsuario = tipoSolicitanteUsuario;
		this.tipoSolicitanteEmpresa = tipoSolicitanteEmpresa;
		this.especificacao = especificacao;
		
		
	}
	
	//CAERN
	public RelatorioOrdemServicoBean(String idOrdemServico, 
			String idRA,
			String dataGeracao, 
			String dataEmissao,
			String tempoMedioExecucao, 
			String meio,
			String origem, 
			String nomeAtendente, 
			String destino,
			String solicitante, 
			String inscricaoImovel, 
			String enderecoFone,
			String idImovel, 
			String localidadeRota, 
			String pontoReferencia,
			String situacaoAguaEsgoto, 
			String categoriaQtdeEconomias,
			String localOcorrencia, 
			String pavimentoRuaCalcada,
			String servicoSolicitado, 
			String valorSolicitado,
			String observacaoOS,
			String servicoTipoReferencia,
			String anoMesHistoricoConsumo1,
			String dtLeituraAtualInformada1,
			String leituraAtualInformada1,
			String consumoFaturado1,
			String descAbrevAnormalidadeConsumo1,
			String descAbrevAnormalidadeLeitura1,
			String anoMesHistoricoConsumo2,
			String dtLeituraAtualInformada2,
			String leituraAtualInformada2,
			String consumoFaturado2,
			String descAbrevAnormalidadeConsumo2,
			String descAbrevAnormalidadeLeitura2,
			String anoMesHistoricoConsumo3,
			String dtLeituraAtualInformada3,
			String leituraAtualInformada3,
			String consumoFaturado3,
			String descAbrevAnormalidadeConsumo3,
			String descAbrevAnormalidadeLeitura3,
			String anoMesHistoricoConsumo4,
			String dtLeituraAtualInformada4,
			String leituraAtualInformada4,
			String consumoFaturado4,
			String descAbrevAnormalidadeConsumo4,
			String descAbrevAnormalidadeLeitura4,
			String anoMesHistoricoConsumo5,
			String dtLeituraAtualInformada5,
			String leituraAtualInformada5,
			String consumoFaturado5,
			String descAbrevAnormalidadeConsumo5,
			String descAbrevAnormalidadeLeitura5,
			String anoMesHistoricoConsumo6,
			String dtLeituraAtualInformada6,
			String leituraAtualInformada6,
			String consumoFaturado6,
			String descAbrevAnormalidadeConsumo6,
			String descAbrevAnormalidadeLeitura6,
			String hidrometroNumero,
			String hidrometroFixo,
			String hidrometroMarca,
			String hidrometroCapacidade,
			String hidrometroDiametro,
			String hidrometroLocal,
			String hidrometroLeitura,
			String hidrometroNumeroDigitos,
			String dataEncerramento,
			String parecerEncerramento,
			String cpfCnpjCliente) {
		
		this.idOrdemServico = idOrdemServico;
		this.idRA = idRA;
		this.dataGeracao = dataGeracao;
		this.dataEmissao = dataEmissao;
		this.tempoMedioExecucao = tempoMedioExecucao;
		this.meio = meio;
		this.origem = origem;
		this.nomeAtendente = nomeAtendente;
		this.destino = destino;
		this.solicitante = solicitante;
		this.inscricaoImovel = inscricaoImovel;
		this.enderecoFone = enderecoFone;
		this.idImovel = idImovel;
		this.localidadeRota = localidadeRota;
		this.pontoReferencia = pontoReferencia;
		this.situacaoAguaEsgoto = situacaoAguaEsgoto;
		this.categoriaQtdeEconomias = categoriaQtdeEconomias;
		this.localOcorrencia = localOcorrencia;
		this.pavimentoRuaCalcada = pavimentoRuaCalcada;
		this.servicoSolicitado = servicoSolicitado;
		this.valorSolicitado = valorSolicitado;
		this.observacaoOS = observacaoOS;
		this.servicoTipoReferencia = servicoTipoReferencia;

		this.anoMesHistoricoConsumo1 = anoMesHistoricoConsumo1; 
		this.dtLeituraAtualInformada1 = dtLeituraAtualInformada1; 
		this.leituraAtualInformada1 = leituraAtualInformada1; 
		this.consumoFaturado1 = consumoFaturado1; 
		this.descAbrevAnormalidadeConsumo1 = descAbrevAnormalidadeConsumo1;
		this.descAbrevAnormalidadeLeitura1 = descAbrevAnormalidadeLeitura1;
		this.anoMesHistoricoConsumo2 = anoMesHistoricoConsumo2; 
		this.dtLeituraAtualInformada2 = dtLeituraAtualInformada2; 
		this.leituraAtualInformada2 = leituraAtualInformada2; 
		this.consumoFaturado2 = consumoFaturado2; 
		this.descAbrevAnormalidadeConsumo2 = descAbrevAnormalidadeConsumo2;
		this.descAbrevAnormalidadeLeitura2 = descAbrevAnormalidadeLeitura2;
		this.anoMesHistoricoConsumo3 = anoMesHistoricoConsumo3; 
		this.dtLeituraAtualInformada3 = dtLeituraAtualInformada3; 
		this.leituraAtualInformada3 = leituraAtualInformada3; 
		this.consumoFaturado3 = consumoFaturado3; 
		this.descAbrevAnormalidadeConsumo3 = descAbrevAnormalidadeConsumo3;
		this.descAbrevAnormalidadeLeitura3 = descAbrevAnormalidadeLeitura3;
		this.anoMesHistoricoConsumo4 = anoMesHistoricoConsumo4; 
		this.dtLeituraAtualInformada4 = dtLeituraAtualInformada4; 
		this.leituraAtualInformada4 = leituraAtualInformada4; 
		this.consumoFaturado4 = consumoFaturado4; 
		this.descAbrevAnormalidadeConsumo4 = descAbrevAnormalidadeConsumo4;
		this.descAbrevAnormalidadeLeitura4 = descAbrevAnormalidadeLeitura4;
		this.anoMesHistoricoConsumo5 = anoMesHistoricoConsumo5; 
		this.dtLeituraAtualInformada5 = dtLeituraAtualInformada5; 
		this.leituraAtualInformada5 = leituraAtualInformada5; 
		this.consumoFaturado5 = consumoFaturado5; 
		this.descAbrevAnormalidadeConsumo5 = descAbrevAnormalidadeConsumo5;
		this.descAbrevAnormalidadeLeitura5 = descAbrevAnormalidadeLeitura5;
		this.anoMesHistoricoConsumo6 = anoMesHistoricoConsumo6; 
		this.dtLeituraAtualInformada6 = dtLeituraAtualInformada6; 
		this.leituraAtualInformada6 = leituraAtualInformada6; 
		this.consumoFaturado6 = consumoFaturado6; 
		this.descAbrevAnormalidadeConsumo6 = descAbrevAnormalidadeConsumo6;
		this.descAbrevAnormalidadeLeitura6 = descAbrevAnormalidadeLeitura6;
		this.hidrometroNumero = hidrometroNumero;
		this.hidrometroFixo = hidrometroFixo;
		this.hidrometroMarca = hidrometroMarca;
		this.hidrometroCapacidade = hidrometroCapacidade;
		this.hidrometroDiametro = hidrometroDiametro;
		this.hidrometroLocal = hidrometroLocal;
		this.hidrometroLeitura = hidrometroLeitura;
		this.hidrometroNumeroDigitos = hidrometroNumeroDigitos;
		
		this.dataEncerramento = dataEncerramento;
		this.parecerEncerramento = parecerEncerramento;
		this.cpfCnpjCliente = cpfCnpjCliente;
		
	}
	
//	CAERN
	public RelatorioOrdemServicoBean(String idOrdemServico, 
			String idRA,
			String dataGeracao, 
			String dataEmissao,
			String tempoMedioExecucao, 
			String meio,
			String origem, 
			String nomeAtendente, 
			String destino,
			String solicitante, 
			String inscricaoImovel, 
			String enderecoFone,
			String idImovel, 
			String localidadeRota, 
			String pontoReferencia,
			String situacaoAguaEsgoto, 
			String categoriaQtdeEconomias,
			String localOcorrencia, 
			String pavimentoRuaCalcada,
			String servicoSolicitado, 
			String valorSolicitado,
			String observacaoOS,
			String observacaoRA,
			String servicoTipoReferencia,
			String anoMesHistoricoConsumo1,
			String dtLeituraAtualInformada1,
			String leituraAtualInformada1,
			String consumoFaturado1,
			String descAbrevAnormalidadeConsumo1,
			String anoMesHistoricoConsumo2,
			String dtLeituraAtualInformada2,
			String leituraAtualInformada2,
			String consumoFaturado2,
			String descAbrevAnormalidadeConsumo2,
			String anoMesHistoricoConsumo3,
			String dtLeituraAtualInformada3,
			String leituraAtualInformada3,
			String consumoFaturado3,
			String descAbrevAnormalidadeConsumo3,
			String anoMesHistoricoConsumo4,
			String dtLeituraAtualInformada4,
			String leituraAtualInformada4,
			String consumoFaturado4,
			String descAbrevAnormalidadeConsumo4,
			String anoMesHistoricoConsumo5,
			String dtLeituraAtualInformada5,
			String leituraAtualInformada5,
			String consumoFaturado5,
			String descAbrevAnormalidadeConsumo5,
			String anoMesHistoricoConsumo6,
			String dtLeituraAtualInformada6,
			String leituraAtualInformada6,
			String consumoFaturado6,
			String descAbrevAnormalidadeConsumo6,
			String hidrometroNumero,
			String hidrometroFixo,
			String hidrometroMarca,
			String hidrometroCapacidade,
			String hidrometroDiametro,
			String hidrometroLocal,
			String hidrometroLeitura,
			String hidrometroNumeroDigitos) {
		
		this.idOrdemServico = idOrdemServico;
		this.idRA = idRA;
		this.dataGeracao = dataGeracao;
		this.dataEmissao = dataEmissao;
		this.tempoMedioExecucao = tempoMedioExecucao;
		this.meio = meio;
		this.origem = origem;
		this.nomeAtendente = nomeAtendente;
		this.destino = destino;
		this.solicitante = solicitante;
		this.inscricaoImovel = inscricaoImovel;
		this.enderecoFone = enderecoFone;
		this.idImovel = idImovel;
		this.localidadeRota = localidadeRota;
		this.pontoReferencia = pontoReferencia;
		this.situacaoAguaEsgoto = situacaoAguaEsgoto;
		this.categoriaQtdeEconomias = categoriaQtdeEconomias;
		this.localOcorrencia = localOcorrencia;
		this.pavimentoRuaCalcada = pavimentoRuaCalcada;
		this.servicoSolicitado = servicoSolicitado;
		this.valorSolicitado = valorSolicitado;
		this.observacaoOS = observacaoOS;
		this.observacaoRA = observacaoRA;
		this.servicoTipoReferencia = servicoTipoReferencia;

		this.anoMesHistoricoConsumo1 = anoMesHistoricoConsumo1; 
		this.dtLeituraAtualInformada1 = dtLeituraAtualInformada1; 
		this.leituraAtualInformada1 = leituraAtualInformada1; 
		this.consumoFaturado1 = consumoFaturado1; 
		this.descAbrevAnormalidadeConsumo1 = descAbrevAnormalidadeConsumo1; 
		this.anoMesHistoricoConsumo2 = anoMesHistoricoConsumo2; 
		this.dtLeituraAtualInformada2 = dtLeituraAtualInformada2; 
		this.leituraAtualInformada2 = leituraAtualInformada2; 
		this.consumoFaturado2 = consumoFaturado2; 
		this.descAbrevAnormalidadeConsumo2 = descAbrevAnormalidadeConsumo2; 
		this.anoMesHistoricoConsumo3 = anoMesHistoricoConsumo3; 
		this.dtLeituraAtualInformada3 = dtLeituraAtualInformada3; 
		this.leituraAtualInformada3 = leituraAtualInformada3; 
		this.consumoFaturado3 = consumoFaturado3; 
		this.descAbrevAnormalidadeConsumo3 = descAbrevAnormalidadeConsumo3; 
		this.anoMesHistoricoConsumo4 = anoMesHistoricoConsumo4; 
		this.dtLeituraAtualInformada4 = dtLeituraAtualInformada4; 
		this.leituraAtualInformada4 = leituraAtualInformada4; 
		this.consumoFaturado4 = consumoFaturado4; 
		this.descAbrevAnormalidadeConsumo4 = descAbrevAnormalidadeConsumo4; 
		this.anoMesHistoricoConsumo5 = anoMesHistoricoConsumo5; 
		this.dtLeituraAtualInformada5 = dtLeituraAtualInformada5; 
		this.leituraAtualInformada5 = leituraAtualInformada5; 
		this.consumoFaturado5 = consumoFaturado5; 
		this.descAbrevAnormalidadeConsumo5 = descAbrevAnormalidadeConsumo5; 
		this.anoMesHistoricoConsumo6 = anoMesHistoricoConsumo6; 
		this.dtLeituraAtualInformada6 = dtLeituraAtualInformada6; 
		this.leituraAtualInformada6 = leituraAtualInformada6; 
		this.consumoFaturado6 = consumoFaturado6; 
		this.descAbrevAnormalidadeConsumo6 = descAbrevAnormalidadeConsumo6; 
		this.hidrometroNumero = hidrometroNumero;
		this.hidrometroFixo = hidrometroFixo;
		this.hidrometroMarca = hidrometroMarca;
		this.hidrometroCapacidade = hidrometroCapacidade;
		this.hidrometroDiametro = hidrometroDiametro;
		this.hidrometroLocal = hidrometroLocal;
		this.hidrometroLeitura = hidrometroLeitura;
		this.hidrometroNumeroDigitos = hidrometroNumeroDigitos;
		
	}

	/**
	 * @return Retorna o campo servicoTipoReferencia.
	 */
	public String getServicoTipoReferencia() {
		return servicoTipoReferencia;
	}

	/**
	 * @param servicoTipoReferencia O servicoTipoReferencia a ser setado.
	 */
	public void setServicoTipoReferencia(String servicoTipoReferencia) {
		this.servicoTipoReferencia = servicoTipoReferencia;
	}

	/**
	 * @return Retorna o campo tipoSolicitanteEmpresa.
	 */
	public String getTipoSolicitanteEmpresa() {
		return tipoSolicitanteEmpresa;
	}

	public String getEspecificacao() {
		return especificacao;
	}

	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}

	/**
	 * @param tipoSolicitanteEmpresa O tipoSolicitanteEmpresa a ser setado.
	 */
	public void setTipoSolicitanteEmpresa(String tipoSolicitanteEmpresa) {
		this.tipoSolicitanteEmpresa = tipoSolicitanteEmpresa;
	}

	/**
	 * @return Retorna o campo tipoSolicitanteUsuario.
	 */
	public String getTipoSolicitanteUsuario() {
		return tipoSolicitanteUsuario;
	}

	/**
	 * @param tipoSolicitanteUsuario O tipoSolicitanteUsuario a ser setado.
	 */
	public void setTipoSolicitanteUsuario(String tipoSolicitanteUsuario) {
		this.tipoSolicitanteUsuario = tipoSolicitanteUsuario;
	}

	/**
	 * @return Retorna o campo categoriaQtdeEconomias.
	 */
	public String getCategoriaQtdeEconomias() {
		return categoriaQtdeEconomias;
	}

	/**
	 * @param categoriaQtdeEconomias O categoriaQtdeEconomias a ser setado.
	 */
	public void setCategoriaQtdeEconomias(String categoriaQtdeEconomias) {
		this.categoriaQtdeEconomias = categoriaQtdeEconomias;
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
	 * @return Retorna o campo descricaoServicoSolicitado.
	 */
	public String getServicoSolicitado() {
		return servicoSolicitado;
	}

	/**
	 * @param descricaoServicoSolicitado O descricaoServicoSolicitado a ser setado.
	 */
	public void setServicoSolicitado(String servicoSolicitado) {
		this.servicoSolicitado = servicoSolicitado;
	}

	/**
	 * @return Retorna o campo endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco O endereco a ser setado.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return Retorna o campo esgotoFixo.
	 */
	public String getEsgotoFixo() {
		return esgotoFixo;
	}

	/**
	 * @param esgotoFixo O esgotoFixo a ser setado.
	 */
	public void setEsgotoFixo(String esgotoFixo) {
		this.esgotoFixo = esgotoFixo;
	}

	/**
	 * @return Retorna o campo idAtendente.
	 */
	public String getIdAtendente() {
		return idAtendente;
	}

	/**
	 * @param idAtendente O idAtendente a ser setado.
	 */
	public void setIdAtendente(String idAtendente) {
		this.idAtendente = idAtendente;
	}

	/**
	 * @return Retorna o campo idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}

	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	/**
	 * @return Retorna o campo idOrdemServico.
	 */
	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	/**
	 * @param idOrdemServico O idOrdemServico a ser setado.
	 */
	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	/**
	 * @return Retorna o campo idRA.
	 */
	public String getIdRA() {
		return idRA;
	}

	/**
	 * @param idRA O idRA a ser setado.
	 */
	public void setIdRA(String idRA) {
		this.idRA = idRA;
	}

	/**
	 * @return Retorna o campo idSolicitante.
	 */
	public String getSolicitante() {
		return solicitante;
	}

	/**
	 * @param idSolicitante O idSolicitante a ser setado.
	 */
	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	/**
	 * @return Retorna o campo idUnidade.
	 */
	public String getUnidade() {
		return unidade;
	}

	/**
	 * @param idUnidade O idUnidade a ser setado.
	 */
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	/**
	 * @return Retorna o campo inscricaoImovel.
	 */
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	/**
	 * @param inscricaoImovel O inscricaoImovel a ser setado.
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	/**
	 * @return Retorna o campo localOcorrencia.
	 */
	public String getLocalOcorrencia() {
		return localOcorrencia;
	}

	/**
	 * @param localOcorrencia O localOcorrencia a ser setado.
	 */
	public void setLocalOcorrencia(String localOcorrencia) {
		this.localOcorrencia = localOcorrencia;
	}

	/**
	 * @return Retorna o campo meio.
	 */
	public String getMeio() {
		return meio;
	}

	/**
	 * @param meio O meio a ser setado.
	 */
	public void setMeio(String meio) {
		this.meio = meio;
	}

	/**
	 * @return Retorna o campo nomeAtendente.
	 */
	public String getNomeAtendente() {
		return nomeAtendente;
	}

	/**
	 * @param nomeAtendente O nomeAtendente a ser setado.
	 */
	public void setNomeAtendente(String nomeAtendente) {
		this.nomeAtendente = nomeAtendente;
	}

	/**
	 * @return Retorna o campo observacaoOS.
	 */
	public String getObservacaoOS() {
		return observacaoOS;
	}

	/**
	 * @param observacaoOS O observacaoOS a ser setado.
	 */
	public void setObservacaoOS(String observacaoOS) {
		this.observacaoOS = observacaoOS;
	}

	/**
	 * @return Retorna o campo observacaoRA.
	 */
	public String getObservacaoRA() {
		return observacaoRA;
	}

	/**
	 * @param observacaoRA O observacaoRA a ser setado.
	 */
	public void setObservacaoRA(String observacaoRA) {
		this.observacaoRA = observacaoRA;
	}

	/**
	 * @return Retorna o campo pavimentoCalcada.
	 */
	public String getPavimentoCalcada() {
		return pavimentoCalcada;
	}

	/**
	 * @param pavimentoCalcada O pavimentoCalcada a ser setado.
	 */
	public void setPavimentoCalcada(String pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}

	/**
	 * @return Retorna o campo pavimentoRua.
	 */
	public String getPavimentoRua() {
		return pavimentoRua;
	}

	/**
	 * @param pavimentoRua O pavimentoRua a ser setado.
	 */
	public void setPavimentoRua(String pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}

	/**
	 * @return Retorna o campo pontoReferencia.
	 */
	public String getPontoReferencia() {
		return pontoReferencia;
	}

	/**
	 * @param pontoReferencia O pontoReferencia a ser setado.
	 */
	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	/**
	 * @return Retorna o campo previsao.
	 */
	public String getPrevisao() {
		return previsao;
	}

	/**
	 * @param previsao O previsao a ser setado.
	 */
	public void setPrevisao(String previsao) {
		this.previsao = previsao;
	}

	/**
	 * @return Retorna o campo situacaoAguaEsgoto.
	 */
	public String getSituacaoAguaEsgoto() {
		return situacaoAguaEsgoto;
	}

	/**
	 * @param situacaoAguaEsgoto O situacaoAguaEsgoto a ser setado.
	 */
	public void setSituacaoAguaEsgoto(String situacaoAguaEsgoto) {
		this.situacaoAguaEsgoto = situacaoAguaEsgoto;
	}

	/**
	 * @return Retorna o campo telefone.
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone O telefone a ser setado.
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * @return Retorna o campo unidadeGeracao.
	 */
	public String getUnidadeGeracao() {
		return unidadeGeracao;
	}

	/**
	 * @param unidadeGeracao O unidadeGeracao a ser setado.
	 */
	public void setUnidadeGeracao(String unidadeGeracao) {
		this.unidadeGeracao = unidadeGeracao;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getEnderecoFone() {
		return enderecoFone;
	}

	public void setEnderecoFone(String enderecoFone) {
		this.enderecoFone = enderecoFone;
	}

	public String getLocalidadeRota() {
		return localidadeRota;
	}

	public void setLocalidadeRota(String localidadeRota) {
		this.localidadeRota = localidadeRota;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getPavimentoRuaCalcada() {
		return pavimentoRuaCalcada;
	}

	public void setPavimentoRuaCalcada(String pavimentoRuaCalcada) {
		this.pavimentoRuaCalcada = pavimentoRuaCalcada;
	}

	public String getTempoMedioExecucao() {
		return tempoMedioExecucao;
	}

	public void setTempoMedioExecucao(String tempoMedioExecucao) {
		this.tempoMedioExecucao = tempoMedioExecucao;
	}

	public String getAnoMesHistoricoConsumo1() {
		return anoMesHistoricoConsumo1;
	}

	public void setAnoMesHistoricoConsumo1(String anoMesHistoricoConsumo1) {
		this.anoMesHistoricoConsumo1 = anoMesHistoricoConsumo1;
	}

	public String getAnoMesHistoricoConsumo2() {
		return anoMesHistoricoConsumo2;
	}

	public void setAnoMesHistoricoConsumo2(String anoMesHistoricoConsumo2) {
		this.anoMesHistoricoConsumo2 = anoMesHistoricoConsumo2;
	}

	public String getAnoMesHistoricoConsumo3() {
		return anoMesHistoricoConsumo3;
	}

	public void setAnoMesHistoricoConsumo3(String anoMesHistoricoConsumo3) {
		this.anoMesHistoricoConsumo3 = anoMesHistoricoConsumo3;
	}

	public String getAnoMesHistoricoConsumo4() {
		return anoMesHistoricoConsumo4;
	}

	public void setAnoMesHistoricoConsumo4(String anoMesHistoricoConsumo4) {
		this.anoMesHistoricoConsumo4 = anoMesHistoricoConsumo4;
	}

	public String getAnoMesHistoricoConsumo5() {
		return anoMesHistoricoConsumo5;
	}

	public void setAnoMesHistoricoConsumo5(String anoMesHistoricoConsumo5) {
		this.anoMesHistoricoConsumo5 = anoMesHistoricoConsumo5;
	}

	public String getAnoMesHistoricoConsumo6() {
		return anoMesHistoricoConsumo6;
	}

	public void setAnoMesHistoricoConsumo6(String anoMesHistoricoConsumo6) {
		this.anoMesHistoricoConsumo6 = anoMesHistoricoConsumo6;
	}

	public String getConsumoFaturado1() {
		return consumoFaturado1;
	}

	public void setConsumoFaturado1(String consumoFaturado1) {
		this.consumoFaturado1 = consumoFaturado1;
	}

	public String getConsumoFaturado2() {
		return consumoFaturado2;
	}

	public void setConsumoFaturado2(String consumoFaturado2) {
		this.consumoFaturado2 = consumoFaturado2;
	}

	public String getConsumoFaturado3() {
		return consumoFaturado3;
	}

	public void setConsumoFaturado3(String consumoFaturado3) {
		this.consumoFaturado3 = consumoFaturado3;
	}

	public String getConsumoFaturado4() {
		return consumoFaturado4;
	}

	public void setConsumoFaturado4(String consumoFaturado4) {
		this.consumoFaturado4 = consumoFaturado4;
	}

	public String getConsumoFaturado5() {
		return consumoFaturado5;
	}

	public void setConsumoFaturado5(String consumoFaturado5) {
		this.consumoFaturado5 = consumoFaturado5;
	}

	public String getConsumoFaturado6() {
		return consumoFaturado6;
	}

	public void setConsumoFaturado6(String consumoFaturado6) {
		this.consumoFaturado6 = consumoFaturado6;
	}

	public String getDescAbrevAnormalidadeConsumo1() {
		return descAbrevAnormalidadeConsumo1;
	}

	public void setDescAbrevAnormalidadeConsumo1(
			String descAbrevAnormalidadeConsumo1) {
		this.descAbrevAnormalidadeConsumo1 = descAbrevAnormalidadeConsumo1;
	}

	public String getDescAbrevAnormalidadeConsumo2() {
		return descAbrevAnormalidadeConsumo2;
	}

	public void setDescAbrevAnormalidadeConsumo2(
			String descAbrevAnormalidadeConsumo2) {
		this.descAbrevAnormalidadeConsumo2 = descAbrevAnormalidadeConsumo2;
	}

	public String getDescAbrevAnormalidadeConsumo3() {
		return descAbrevAnormalidadeConsumo3;
	}

	public void setDescAbrevAnormalidadeConsumo3(
			String descAbrevAnormalidadeConsumo3) {
		this.descAbrevAnormalidadeConsumo3 = descAbrevAnormalidadeConsumo3;
	}

	public String getDescAbrevAnormalidadeConsumo4() {
		return descAbrevAnormalidadeConsumo4;
	}

	public void setDescAbrevAnormalidadeConsumo4(
			String descAbrevAnormalidadeConsumo4) {
		this.descAbrevAnormalidadeConsumo4 = descAbrevAnormalidadeConsumo4;
	}

	public String getDescAbrevAnormalidadeConsumo5() {
		return descAbrevAnormalidadeConsumo5;
	}

	public void setDescAbrevAnormalidadeConsumo5(
			String descAbrevAnormalidadeConsumo5) {
		this.descAbrevAnormalidadeConsumo5 = descAbrevAnormalidadeConsumo5;
	}

	public String getDescAbrevAnormalidadeConsumo6() {
		return descAbrevAnormalidadeConsumo6;
	}

	public void setDescAbrevAnormalidadeConsumo6(
			String descAbrevAnormalidadeConsumo6) {
		this.descAbrevAnormalidadeConsumo6 = descAbrevAnormalidadeConsumo6;
	}

	public String getDtLeituraAtualInformada1() {
		return dtLeituraAtualInformada1;
	}

	public void setDtLeituraAtualInformada1(String dtLeituraAtualInformada1) {
		this.dtLeituraAtualInformada1 = dtLeituraAtualInformada1;
	}

	public String getDtLeituraAtualInformada2() {
		return dtLeituraAtualInformada2;
	}

	public void setDtLeituraAtualInformada2(String dtLeituraAtualInformada2) {
		this.dtLeituraAtualInformada2 = dtLeituraAtualInformada2;
	}

	public String getDtLeituraAtualInformada3() {
		return dtLeituraAtualInformada3;
	}

	public void setDtLeituraAtualInformada3(String dtLeituraAtualInformada3) {
		this.dtLeituraAtualInformada3 = dtLeituraAtualInformada3;
	}

	public String getDtLeituraAtualInformada4() {
		return dtLeituraAtualInformada4;
	}

	public void setDtLeituraAtualInformada4(String dtLeituraAtualInformada4) {
		this.dtLeituraAtualInformada4 = dtLeituraAtualInformada4;
	}

	public String getDtLeituraAtualInformada5() {
		return dtLeituraAtualInformada5;
	}

	public void setDtLeituraAtualInformada5(String dtLeituraAtualInformada5) {
		this.dtLeituraAtualInformada5 = dtLeituraAtualInformada5;
	}

	public String getDtLeituraAtualInformada6() {
		return dtLeituraAtualInformada6;
	}

	public void setDtLeituraAtualInformada6(String dtLeituraAtualInformada6) {
		this.dtLeituraAtualInformada6 = dtLeituraAtualInformada6;
	}

	public String getLeituraAtualInformada1() {
		return leituraAtualInformada1;
	}

	public void setLeituraAtualInformada1(String leituraAtualInformada1) {
		this.leituraAtualInformada1 = leituraAtualInformada1;
	}

	public String getLeituraAtualInformada2() {
		return leituraAtualInformada2;
	}

	public void setLeituraAtualInformada2(String leituraAtualInformada2) {
		this.leituraAtualInformada2 = leituraAtualInformada2;
	}

	public String getLeituraAtualInformada3() {
		return leituraAtualInformada3;
	}

	public void setLeituraAtualInformada3(String leituraAtualInformada3) {
		this.leituraAtualInformada3 = leituraAtualInformada3;
	}

	public String getLeituraAtualInformada4() {
		return leituraAtualInformada4;
	}

	public void setLeituraAtualInformada4(String leituraAtualInformada4) {
		this.leituraAtualInformada4 = leituraAtualInformada4;
	}

	public String getLeituraAtualInformada5() {
		return leituraAtualInformada5;
	}

	public void setLeituraAtualInformada5(String leituraAtualInformada5) {
		this.leituraAtualInformada5 = leituraAtualInformada5;
	}

	public String getLeituraAtualInformada6() {
		return leituraAtualInformada6;
	}

	public void setLeituraAtualInformada6(String leituraAtualInformada6) {
		this.leituraAtualInformada6 = leituraAtualInformada6;
	}

	public String getHidrometroCapacidade() {
		return hidrometroCapacidade;
	}

	public void setHidrometroCapacidade(String hidrometroCapacidade) {
		this.hidrometroCapacidade = hidrometroCapacidade;
	}

	public String getHidrometroDiametro() {
		return hidrometroDiametro;
	}

	public void setHidrometroDiametro(String hidrometroDiametro) {
		this.hidrometroDiametro = hidrometroDiametro;
	}

	public String getHidrometroFixo() {
		return hidrometroFixo;
	}

	public void setHidrometroFixo(String hidrometroFixo) {
		this.hidrometroFixo = hidrometroFixo;
	}

	public String getHidrometroLeitura() {
		return hidrometroLeitura;
	}

	public void setHidrometroLeitura(String hidrometroLeitura) {
		this.hidrometroLeitura = hidrometroLeitura;
	}

	public String getHidrometroLocal() {
		return hidrometroLocal;
	}

	public void setHidrometroLocal(String hidrometroLocal) {
		this.hidrometroLocal = hidrometroLocal;
	}

	public String getHidrometroMarca() {
		return hidrometroMarca;
	}

	public void setHidrometroMarca(String hidrometroMarca) {
		this.hidrometroMarca = hidrometroMarca;
	}

	public String getHidrometroNumero() {
		return hidrometroNumero;
	}

	public void setHidrometroNumero(String hidrometroNumero) {
		this.hidrometroNumero = hidrometroNumero;
	}

	public String getHidrometroNumeroDigitos() {
		return hidrometroNumeroDigitos;
	}

	public void setHidrometroNumeroDigitos(String hidrometroNumeroDigitos) {
		this.hidrometroNumeroDigitos = hidrometroNumeroDigitos;
	}

	public String getValorSolicitado() {
		return valorSolicitado;
	}

	public void setValorSolicitado(String valorSolicitado) {
		this.valorSolicitado = valorSolicitado;
	}

	/**
	 * @return Retorna o campo dataEncerramento.
	 */
	public String getDataEncerramento() {
		return dataEncerramento;
	}

	/**
	 * @param dataEncerramento O dataEncerramento a ser setado.
	 */
	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	/**
	 * @return Retorna o campo parecerEncerramento.
	 */
	public String getParecerEncerramento() {
		return parecerEncerramento;
	}

	/**
	 * @param parecerEncerramento O parecerEncerramento a ser setado.
	 */
	public void setParecerEncerramento(String parecerEncerramento) {
		this.parecerEncerramento = parecerEncerramento;
	}

	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}

	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getDescAbrevAnormalidadeLeitura1() {
		return descAbrevAnormalidadeLeitura1;
	}

	public void setDescAbrevAnormalidadeLeitura1(
			String descAbrevAnormalidadeLeitura1) {
		this.descAbrevAnormalidadeLeitura1 = descAbrevAnormalidadeLeitura1;
	}

	public String getDescAbrevAnormalidadeLeitura2() {
		return descAbrevAnormalidadeLeitura2;
	}

	public void setDescAbrevAnormalidadeLeitura2(
			String descAbrevAnormalidadeLeitura2) {
		this.descAbrevAnormalidadeLeitura2 = descAbrevAnormalidadeLeitura2;
	}

	public String getDescAbrevAnormalidadeLeitura3() {
		return descAbrevAnormalidadeLeitura3;
	}

	public void setDescAbrevAnormalidadeLeitura3(
			String descAbrevAnormalidadeLeitura3) {
		this.descAbrevAnormalidadeLeitura3 = descAbrevAnormalidadeLeitura3;
	}

	public String getDescAbrevAnormalidadeLeitura4() {
		return descAbrevAnormalidadeLeitura4;
	}

	public void setDescAbrevAnormalidadeLeitura4(
			String descAbrevAnormalidadeLeitura4) {
		this.descAbrevAnormalidadeLeitura4 = descAbrevAnormalidadeLeitura4;
	}

	public String getDescAbrevAnormalidadeLeitura5() {
		return descAbrevAnormalidadeLeitura5;
	}

	public void setDescAbrevAnormalidadeLeitura5(
			String descAbrevAnormalidadeLeitura5) {
		this.descAbrevAnormalidadeLeitura5 = descAbrevAnormalidadeLeitura5;
	}

	public String getDescAbrevAnormalidadeLeitura6() {
		return descAbrevAnormalidadeLeitura6;
	}

	public void setDescAbrevAnormalidadeLeitura6(
			String descAbrevAnormalidadeLeitura6) {
		this.descAbrevAnormalidadeLeitura6 = descAbrevAnormalidadeLeitura6;
	}

	public String getNomeProjeto() {
		return nomeProjeto;
	}

	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

	public String getImovelPerfil() {
		return imovelPerfil;
	}

	public void setImovelPerfil(String imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}
		
}
