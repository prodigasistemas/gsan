package gcom.cadastro.imovel.bean;

public class FiltrarImovelOutrosCriteriosHelper {

	private String idClienteTipo;
	
	private String tipoRelatorio;
	
	private String NomeCliente;

	private String id;

	private String descricao;

	private String indicadorUso;

	private String intervaloMediaMinimaHidrometroInicio;

	private String intervaloMediaMinimaHidrometroFinal;

	private String intervaloMediaMinimaImovelInicio;//

	private String intervaloMediaMinimaImovelFinal;//

	private String indicadorMedicao;//

	private String intervaloPercentualEsgotoInicial;

	private String intervaloPercentualEsgotoFinal;

	private String inscricaoTipo;//

	private String consumoMinimo;

	private String nomeMunicipio;

	private String idNomeConta;

	private String idImovelPrincipal;

	private String idImovelCondominio;

	private String tipoRelacao;// Falta modificar no hbm pra fazer reverso, so
								// com rodrigo...

	private String idCliente;

	private String loteDestino;

	private String quadraDestinoNM;

	private String idBairro;

	private String nomeBairro;

	private String loteOrigem;

	private String idLocalidade;

	private String nomeLocalidadeOrigem;

	private String nomeSetorComercialOrigem;

	private String quadraOrigemNM;

	private String quadraMensagemOrigem;

	private String nomeLocalidadeDestino;

	private String setorComercialDestinoCD;

	private String setorComercialOrigemCD;

	private String setorComercialOrigemID;

	private String quadraOrigemID;

	private String localidadeDestinoID;

	private String localidadeOrigemID;

	private String nomeSetorComercialDestino;

	private String setorComercialDestinoID;

	private String quadraMensagemDestino;

	private String idGerenciaRegional;

	private String quadraDestinoID;

	private String idMunicipio;

	private String consumoMinimoInicial;//

	private String consumoMinimoFinal;//

	private String situacaoAgua;

	private String situacaoLigacaoEsgoto;

	private String consumoMinimoFixadoEsgotoInicial;

	private String consumoMinimoFixadoEsgotoFinal;

	private String tipoMedicao;

	private String perfilImovel;

	private String categoriaImovel;

	private String subcategoria;

	private String quantidadeEconomiasInicial;//

	private String quantidadeEconomiasFinal;//

	private String numeroPontosInicial;//

	private String numeroPontosFinal;//

	private String numeroMoradoresInicial;//

	private String numeroMoradoresFinal;//

	private String areaConstruidaInicial;//

	private String areaConstruidaFinal;//

	private String areaConstruidaFaixa;//

	private String tipoPoco;

	private String tipoSituacaoEspecialFaturamento;//

	private String tipoSituacaoEspecialCobranca;//

	private String situacaoCobranca;//

	private String diaVencimentoAlternativo;//

	private String anormalidadeElo;//

	private String ocorrenciaCadastro;//

	private String tarifaConsumo;//

	private String CEP;

	private String idLogradouro;

	private String tarifaSocialCartaoTipoId;// Esperar pra fazer o hql

	private String tarifaSocialRendaTipoId;//

	private String tarifaSocialExclusaoMotivoId;// Perto \R
												// cliente_relacao_tipoem
												// vermelho

	private String nomeLogradouro;
	
	private String cdRotaInicial;

	private String sequencialRotaInicial;

	private String cdRotaFinal;

	private String sequencialRotaFinal;
	
	private String indicadorCodigoBarra;
	
	private String cpfCnpj;
	

	public String getIndicadorCodigoBarra() {
		return indicadorCodigoBarra;
	}

	public void setIndicadorCodigoBarra(String indicadorCodigoBarra) {
		this.indicadorCodigoBarra = indicadorCodigoBarra;
	}

	public String getIdLogradouro() {
		return idLogradouro;
	}

	public String getAnormalidadeElo() {
		return anormalidadeElo;
	}

	public void setAnormalidadeElo(String anormalidadeElo) {
		this.anormalidadeElo = anormalidadeElo;
	}

	public String getAreaConstruidaFaixa() {
		return areaConstruidaFaixa;
	}

	public void setAreaConstruidaFaixa(String areaConstruidaFaixa) {
		this.areaConstruidaFaixa = areaConstruidaFaixa;
	}

	public String getCategoriaImovel() {
		return categoriaImovel;
	}

	public void setCategoriaImovel(String categoriaImovel) {
		this.categoriaImovel = categoriaImovel;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cep) {
		CEP = cep;
	}

	public String getDiaVencimentoAlternativo() {
		return diaVencimentoAlternativo;
	}

	public void setDiaVencimentoAlternativo(String diaVencimentoAlternativo) {
		this.diaVencimentoAlternativo = diaVencimentoAlternativo;
	}

	public String getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(String idBairro) {
		this.idBairro = idBairro;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getIdImovelCondominio() {
		return idImovelCondominio;
	}

	public void setIdImovelCondominio(String idImovelCondominio) {
		this.idImovelCondominio = idImovelCondominio;
	}

	public String getIdImovelPrincipal() {
		return idImovelPrincipal;
	}

	public void setIdImovelPrincipal(String idImovelPrincipal) {
		this.idImovelPrincipal = idImovelPrincipal;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getIdNomeConta() {
		return idNomeConta;
	}

	public void setIdNomeConta(String idNomeConta) {
		this.idNomeConta = idNomeConta;
	}

	public String getInscricaoTipo() {
		return inscricaoTipo;
	}

	public void setInscricaoTipo(String inscricaoTipo) {
		this.inscricaoTipo = inscricaoTipo;
	}

	public String getLocalidadeDestinoID() {
		return localidadeDestinoID;
	}

	public void setLocalidadeDestinoID(String localidadeDestinoID) {
		this.localidadeDestinoID = localidadeDestinoID;
	}

	public String getLocalidadeOrigemID() {
		return localidadeOrigemID;
	}

	public void setLocalidadeOrigemID(String localidadeOrigemID) {
		this.localidadeOrigemID = localidadeOrigemID;
	}

	public String getLoteDestino() {
		return loteDestino;
	}

	public void setLoteDestino(String loteDestino) {
		this.loteDestino = loteDestino;
	}

	public String getLoteOrigem() {
		return loteOrigem;
	}

	public void setLoteOrigem(String loteOrigem) {
		this.loteOrigem = loteOrigem;
	}

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	public String getNomeLocalidadeDestino() {
		return nomeLocalidadeDestino;
	}

	public void setNomeLocalidadeDestino(String nomeLocalidadeDestino) {
		this.nomeLocalidadeDestino = nomeLocalidadeDestino;
	}

	public String getNomeLocalidadeOrigem() {
		return nomeLocalidadeOrigem;
	}

	public void setNomeLocalidadeOrigem(String nomeLocalidadeOrigem) {
		this.nomeLocalidadeOrigem = nomeLocalidadeOrigem;
	}

	public String getNomeLogradouro() {
		return nomeLogradouro;
	}

	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public String getNomeSetorComercialDestino() {
		return nomeSetorComercialDestino;
	}

	public void setNomeSetorComercialDestino(String nomeSetorComercialDestino) {
		this.nomeSetorComercialDestino = nomeSetorComercialDestino;
	}

	public String getNomeSetorComercialOrigem() {
		return nomeSetorComercialOrigem;
	}

	public void setNomeSetorComercialOrigem(String nomeSetorComercialOrigem) {
		this.nomeSetorComercialOrigem = nomeSetorComercialOrigem;
	}

	public String getOcorrenciaCadastro() {
		return ocorrenciaCadastro;
	}

	public void setOcorrenciaCadastro(String ocorrenciaCadastro) {
		this.ocorrenciaCadastro = ocorrenciaCadastro;
	}

	public String getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public String getQuadraDestinoID() {
		return quadraDestinoID;
	}

	public void setQuadraDestinoID(String quadraDestinoID) {
		this.quadraDestinoID = quadraDestinoID;
	}

	public String getQuadraDestinoNM() {
		return quadraDestinoNM;
	}

	public void setQuadraDestinoNM(String quadraDestinoNM) {
		this.quadraDestinoNM = quadraDestinoNM;
	}

	public String getQuadraMensagemDestino() {
		return quadraMensagemDestino;
	}

	public void setQuadraMensagemDestino(String quadraMensagemDestino) {
		this.quadraMensagemDestino = quadraMensagemDestino;
	}

	public String getQuadraMensagemOrigem() {
		return quadraMensagemOrigem;
	}

	public void setQuadraMensagemOrigem(String quadraMensagemOrigem) {
		this.quadraMensagemOrigem = quadraMensagemOrigem;
	}

	public String getQuadraOrigemID() {
		return quadraOrigemID;
	}

	public void setQuadraOrigemID(String quadraOrigemID) {
		this.quadraOrigemID = quadraOrigemID;
	}

	public String getQuadraOrigemNM() {
		return quadraOrigemNM;
	}

	public void setQuadraOrigemNM(String quadraOrigemNM) {
		this.quadraOrigemNM = quadraOrigemNM;
	}

	public String getSetorComercialDestinoCD() {
		return setorComercialDestinoCD;
	}

	public void setSetorComercialDestinoCD(String setorComercialDestinoCD) {
		this.setorComercialDestinoCD = setorComercialDestinoCD;
	}

	public String getSetorComercialDestinoID() {
		return setorComercialDestinoID;
	}

	public void setSetorComercialDestinoID(String setorComercialDestinoID) {
		this.setorComercialDestinoID = setorComercialDestinoID;
	}

	public String getSetorComercialOrigemCD() {
		return setorComercialOrigemCD;
	}

	public void setSetorComercialOrigemCD(String setorComercialOrigemCD) {
		this.setorComercialOrigemCD = setorComercialOrigemCD;
	}

	public String getSetorComercialOrigemID() {
		return setorComercialOrigemID;
	}

	public void setSetorComercialOrigemID(String setorComercialOrigemID) {
		this.setorComercialOrigemID = setorComercialOrigemID;
	}

	public String getSituacaoAgua() {
		return situacaoAgua;
	}

	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	public String getSituacaoCobranca() {
		return situacaoCobranca;
	}

	public void setSituacaoCobranca(String situacaoCobranca) {
		this.situacaoCobranca = situacaoCobranca;
	}

	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	public String getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}

	public String getTarifaConsumo() {
		return tarifaConsumo;
	}

	public void setTarifaConsumo(String tarifaConsumo) {
		this.tarifaConsumo = tarifaConsumo;
	}

	public String getTarifaSocialCartaoTipoId() {
		return tarifaSocialCartaoTipoId;
	}

	public void setTarifaSocialCartaoTipoId(String tarifaSocialCartaoTipoId) {
		this.tarifaSocialCartaoTipoId = tarifaSocialCartaoTipoId;
	}

	public String getTarifaSocialExclusaoMotivoId() {
		return tarifaSocialExclusaoMotivoId;
	}

	public void setTarifaSocialExclusaoMotivoId(
			String tarifaSocialExclusaoMotivoId) {
		this.tarifaSocialExclusaoMotivoId = tarifaSocialExclusaoMotivoId;
	}

	public String getTarifaSocialRendaTipoId() {
		return tarifaSocialRendaTipoId;
	}

	public void setTarifaSocialRendaTipoId(String tarifaSocialRendaTipoId) {
		this.tarifaSocialRendaTipoId = tarifaSocialRendaTipoId;
	}

	public String getTipoMedicao() {
		return tipoMedicao;
	}

	public void setTipoMedicao(String tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}

	public String getTipoPoco() {
		return tipoPoco;
	}

	public void setTipoPoco(String tipoPoco) {
		this.tipoPoco = tipoPoco;
	}

	public String getTipoRelacao() {
		return tipoRelacao;
	}

	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}

	public String getTipoSituacaoEspecialCobranca() {
		return tipoSituacaoEspecialCobranca;
	}

	public void setTipoSituacaoEspecialCobranca(
			String tipoSituacaoEspecialCobranca) {
		this.tipoSituacaoEspecialCobranca = tipoSituacaoEspecialCobranca;
	}

	public String getTipoSituacaoEspecialFaturamento() {
		return tipoSituacaoEspecialFaturamento;
	}

	public void setTipoSituacaoEspecialFaturamento(
			String tipoSituacaoEspecialFaturamento) {
		this.tipoSituacaoEspecialFaturamento = tipoSituacaoEspecialFaturamento;
	}

	public void setIdLogradouro(String idLogradouro) {
		this.idLogradouro = idLogradouro;
	}

	public String getConsumoMinimoFinal() {
		return consumoMinimoFinal;
	}

	public void setConsumoMinimoFinal(String consumoMinimoFinal) {
		this.consumoMinimoFinal = consumoMinimoFinal;
	}

	public String getConsumoMinimoInicial() {
		return consumoMinimoInicial;
	}

	public void setConsumoMinimoInicial(String consumoMinimoInicial) {
		this.consumoMinimoInicial = consumoMinimoInicial;
	}

	public String getConsumoMinimo() {
		return consumoMinimo;
	}

	public void setConsumoMinimo(String consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}

	public String getIntervaloPercentualEsgotoFinal() {
		return intervaloPercentualEsgotoFinal;
	}

	public void setIntervaloPercentualEsgotoFinal(
			String intervaloPercentualEsgotoFinal) {
		this.intervaloPercentualEsgotoFinal = intervaloPercentualEsgotoFinal;
	}

	public String getIntervaloPercentualEsgotoInicial() {
		return intervaloPercentualEsgotoInicial;
	}

	public void setIntervaloPercentualEsgotoInicial(
			String intervaloPercentualEsgotoInicial) {
		this.intervaloPercentualEsgotoInicial = intervaloPercentualEsgotoInicial;
	}

	public String getConsumoMinimoFixadoEsgotoFinal() {
		return consumoMinimoFixadoEsgotoFinal;
	}

	public void setConsumoMinimoFixadoEsgotoFinal(
			String consumoMinimoFixadoEsgotoFinal) {
		this.consumoMinimoFixadoEsgotoFinal = consumoMinimoFixadoEsgotoFinal;
	}

	public String getConsumoMinimoFixadoEsgotoInicial() {
		return consumoMinimoFixadoEsgotoInicial;
	}

	public void setConsumoMinimoFixadoEsgotoInicial(
			String consumoMinimoFixadoEsgotoInicial) {
		this.consumoMinimoFixadoEsgotoInicial = consumoMinimoFixadoEsgotoInicial;
	}

	public String getIndicadorMedicao() {
		return indicadorMedicao;
	}

	public void setIndicadorMedicao(String indicadorMedicao) {
		this.indicadorMedicao = indicadorMedicao;
	}

	public String getIntervaloMediaMinimaImovelFinal() {
		return intervaloMediaMinimaImovelFinal;
	}

	public void setIntervaloMediaMinimaImovelFinal(
			String intervaloMediaMinimaImovelFinal) {
		this.intervaloMediaMinimaImovelFinal = intervaloMediaMinimaImovelFinal;
	}

	public String getIntervaloMediaMinimaImovelInicio() {
		return intervaloMediaMinimaImovelInicio;
	}

	public void setIntervaloMediaMinimaImovelInicio(
			String intervaloMediaMinimaImovelInicio) {
		this.intervaloMediaMinimaImovelInicio = intervaloMediaMinimaImovelInicio;
	}

	public String getIntervaloMediaMinimaHidrometroFinal() {
		return intervaloMediaMinimaHidrometroFinal;
	}

	public void setIntervaloMediaMinimaHidrometroFinal(
			String intervaloMediaMinimaHidrometroFinal) {
		this.intervaloMediaMinimaHidrometroFinal = intervaloMediaMinimaHidrometroFinal;
	}

	public String getIntervaloMediaMinimaHidrometroInicio() {
		return intervaloMediaMinimaHidrometroInicio;
	}

	public void setIntervaloMediaMinimaHidrometroInicio(
			String intervaloMediaMinimaHidrometroInicio) {
		this.intervaloMediaMinimaHidrometroInicio = intervaloMediaMinimaHidrometroInicio;
	}

	public String getQuantidadeEconomiasFinal() {
		return quantidadeEconomiasFinal;
	}

	public void setQuantidadeEconomiasFinal(String quantidadeEconomiasFinal) {
		this.quantidadeEconomiasFinal = quantidadeEconomiasFinal;
	}

	public String getQuantidadeEconomiasInicial() {
		return quantidadeEconomiasInicial;
	}

	public void setQuantidadeEconomiasInicial(String quantidadeEconomiasInicial) {
		this.quantidadeEconomiasInicial = quantidadeEconomiasInicial;
	}

	public String getNumeroPontosInicial() {
		return numeroPontosInicial;
	}

	public void setNumeroPontosInicial(String numeroPontosInicial) {
		this.numeroPontosInicial = numeroPontosInicial;
	}

	public String getNumeroPontosFinal() {
		return numeroPontosFinal;
	}

	public void setNumeroPontosFinal(String numeroPontosFinal) {
		this.numeroPontosFinal = numeroPontosFinal;
	}

	public String getNumeroMoradoresFinal() {
		return numeroMoradoresFinal;
	}

	public void setNumeroMoradoresFinal(String numeroMoradoresFinal) {
		this.numeroMoradoresFinal = numeroMoradoresFinal;
	}

	public String getNumeroMoradoresInicial() {
		return numeroMoradoresInicial;
	}

	public void setNumeroMoradoresInicial(String numeroMoradoresInicial) {
		this.numeroMoradoresInicial = numeroMoradoresInicial;
	}

	public String getAreaConstruidaFinal() {
		return areaConstruidaFinal;
	}

	public void setAreaConstruidaFinal(String areaConstruidaFinal) {
		this.areaConstruidaFinal = areaConstruidaFinal;
	}

	public String getAreaConstruidaInicial() {
		return areaConstruidaInicial;
	}

	public void setAreaConstruidaInicial(String areaConstruidaInicial) {
		this.areaConstruidaInicial = areaConstruidaInicial;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getNomeCliente() {
		return NomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		NomeCliente = nomeCliente;
	}

	public String getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

	public String getIdClienteTipo() {
		return idClienteTipo;
	}

	public void setIdClienteTipo(String idClienteTipo) {
		this.idClienteTipo = idClienteTipo;
	}

	public String getCdRotaFinal() {
		return cdRotaFinal;
	}

	public void setCdRotaFinal(String cdRotaFinal) {
		this.cdRotaFinal = cdRotaFinal;
	}

	public String getCdRotaInicial() {
		return cdRotaInicial;
	}

	public void setCdRotaInicial(String cdRotaInicial) {
		this.cdRotaInicial = cdRotaInicial;
	}

	public String getSequencialRotaFinal() {
		return sequencialRotaFinal;
	}

	public void setSequencialRotaFinal(String sequencialRotaFinal) {
		this.sequencialRotaFinal = sequencialRotaFinal;
	}

	public String getSequencialRotaInicial() {
		return sequencialRotaInicial;
	}

	public void setSequencialRotaInicial(String sequencialRotaInicial) {
		this.sequencialRotaInicial = sequencialRotaInicial;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

}
