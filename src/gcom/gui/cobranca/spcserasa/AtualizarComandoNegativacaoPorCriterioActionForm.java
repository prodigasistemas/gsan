package gcom.gui.cobranca.spcserasa;

import java.util.Date;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Esta classe tem por finalidade gerar o formulário que receberá os parâmetros
 * para realização da inserção de um comando negativação
 * 
 * @author Ana Maria
 * @date 06/11/2007
 */
public class AtualizarComandoNegativacaoPorCriterioActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	//private String clienteObrigatorio;
	
	private String idNegativacaoComando;
	private Date ultimaAlteracaoNegComando;
	private String idNegativacaoCriterio;
	private String negativadorId;
	private String nomeNegativador;
	
	//Dados Gerais
	private String titulo;
	private String solicitacao;
	private String simular;
	private String executarSimulacao;
	private String idComandoSimulado;
	private String descricaoComandoSimulado;	
	private String dataPrevista;
	private String usuario;
	private String nomeUsuario;
	private String qtdMaximaInclusao;
	private String titularidadeNegativacao;
	private String indicadorOrgaoPublico;
	
	//Dados Débito
	private String referenciaInicial;
	private String referenciaFinal;
	private String dataVencimentoInicial;
	private String dataVencimentoFinal;
	private String valorDebitoInicial;
	private String valorDebitoFinal;
	private String numeroContasInicial;
	private String numeroContasFinal;
	private String contasRevisao;
	private String guiasPagamento;
	private String parcelaAtraso;
	private String diasAtrasoParcelamento;
	private String cartaParcelamentoAtraso;
	private String diasAtrasoRecebimentoCarta;
	private String indicadorContaNomeCliente;
	
	//Dados Imovél
	private String idCliente;
	private String descricaoCliente;
	private String tipoRelacao;	
	private String imovSitEspecialCobranca;
	private String imovSitCobranca;

	private String[] cobrancaSituacaoTipo;
	private String[] cobrancaSituacao;
	private String[] ligacaoAguaSituacao;
	private String[] ligacaoEsgotoSituacao;
	private String[] subCategoria;
	private String[] perfilImovel;
	private String[] tipoCliente;
	private String indicadorBaixaRenda;
	
	//Dados Localização
	private String[] cobrancaGrupo;
	private String[] gerenciaRegional;
	private String[] unidadeNegocio;
	private String[] eloPolo;
	private String idLocalidadeInicial;
	private String localidadeDescricaoInicial;
	private String codigoSetorComercialInicial;
	private String setorComercialDescricaoInicial;
	private String idLocalidadeFinal;
	private String localidadeDescricaoFinal;
	private String codigoSetorComercialFinal;
	private String setorComercialDescricaoFinal;

	//Exclusão
	private String[] motivoRetorno;
	private String quantidadeDias;
	
	/**
	 * @return Retorna o campo negativadorId.
	 */
	public String getNegativadorId() {
		return negativadorId;
	}

	/**
	 * @param negativadorId O negativadorId a ser setado.
	 */
	public void setNegativadorId(String negativadorId) {
		this.negativadorId = negativadorId;
	}

	/**
	 * @return Retorna o campo nomeNegativador.
	 */
	public String getNomeNegativador() {
		return nomeNegativador;
	}

	/**
	 * @param nomeNegativador O nomeNegativador a ser setado.
	 */
	public void setNomeNegativador(String nomeNegativador) {
		this.nomeNegativador = nomeNegativador;
	}

	/**
	 * @return Retorna o campo usuario.
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario O usuario a ser setado.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	 * @return Retorna o campo dataVencimentoFinal.
	 */
	public String getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}

	/**
	 * @param dataVencimentoFinal O dataVencimentoFinal a ser setado.
	 */
	public void setDataVencimentoFinal(String dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}

	/**
	 * @return Retorna o campo dataVencimentoInicial.
	 */
	public String getDataVencimentoInicial() {
		return dataVencimentoInicial;
	}

	/**
	 * @param dataVencimentoInicial O dataVencimentoInicial a ser setado.
	 */
	public void setDataVencimentoInicial(String dataVencimentoInicial) {
		this.dataVencimentoInicial = dataVencimentoInicial;
	}

	/**
	 * @return Retorna o campo referenciaFinal.
	 */
	public String getReferenciaFinal() {
		return referenciaFinal;
	}

	/**
	 * @param referenciaFinal O referenciaFinal a ser setado.
	 */
	public void setReferenciaFinal(String referenciaFinal) {
		this.referenciaFinal = referenciaFinal;
	}

	/**
	 * @return Retorna o campo referenciaInicial.
	 */
	public String getReferenciaInicial() {
		return referenciaInicial;
	}

	/**
	 * @param referenciaInicial O referenciaInicial a ser setado.
	 */
	public void setReferenciaInicial(String referenciaInicial) {
		this.referenciaInicial = referenciaInicial;
	}

	/**
	 * @return Retorna o campo tipoRelacao.
	 */
	public String getTipoRelacao() {
		return tipoRelacao;
	}

	/**
	 * @param tipoRelacao O tipoRelacao a ser setado.
	 */
	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}

	/**
	 * @return Retorna o campo dataPrevista.
	 */
	public String getDataPrevista() {
		return dataPrevista;
	}

	/**
	 * @param dataPrevista O dataPrevista a ser setado.
	 */
	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	/**
	 * @return Retorna o campo qtdMaximaInclusao.
	 */
	public String getQtdMaximaInclusao() {
		return qtdMaximaInclusao;
	}

	/**
	 * @param qtdMaximaInclusao O qtdMaximaInclusao a ser setado.
	 */
	public void setQtdMaximaInclusao(String qtdMaximaInclusao) {
		this.qtdMaximaInclusao = qtdMaximaInclusao;
	}

	/**
	 * @return Retorna o campo simular.
	 */
	public String getSimular() {
		return simular;
	}

	/**
	 * @param simular O simular a ser setado.
	 */
	public void setSimular(String simular) {
		this.simular = simular;
	}

	/**
	 * @return Retorna o campo solicitacao.
	 */
	public String getSolicitacao() {
		return solicitacao;
	}

	/**
	 * @param solicitacao O solicitacao a ser setado.
	 */
	public void setSolicitacao(String solicitacao) {
		this.solicitacao = solicitacao;
	}

	/**
	 * @return Retorna o campo titularidadeNegativacao.
	 */
	public String getTitularidadeNegativacao() {
		return titularidadeNegativacao;
	}

	/**
	 * @param titularidadeNegativacao O titularidadeNegativacao a ser setado.
	 */
	public void setTitularidadeNegativacao(String titularidadeNegativacao) {
		this.titularidadeNegativacao = titularidadeNegativacao;
	}

	/**
	 * @return Retorna o campo titulo.
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo O titulo a ser setado.
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return Retorna o campo cartaParcelamentoAtraso.
	 */
	public String getCartaParcelamentoAtraso() {
		return cartaParcelamentoAtraso;
	}

	/**
	 * @param cartaParcelamentoAtraso O cartaParcelamentoAtraso a ser setado.
	 */
	public void setCartaParcelamentoAtraso(String cartaParcelamentoAtraso) {
		this.cartaParcelamentoAtraso = cartaParcelamentoAtraso;
	}

	/**
	 * @return Retorna o campo contasRevisao.
	 */
	public String getContasRevisao() {
		return contasRevisao;
	}

	/**
	 * @param contasRevisao O contasRevisao a ser setado.
	 */
	public void setContasRevisao(String contasRevisao) {
		this.contasRevisao = contasRevisao;
	}

	/**
	 * @return Retorna o campo diasAtrasoParcelamento.
	 */
	public String getDiasAtrasoParcelamento() {
		return diasAtrasoParcelamento;
	}

	/**
	 * @param diasAtrasoParcelamento O diasAtrasoParcelamento a ser setado.
	 */
	public void setDiasAtrasoParcelamento(String diasAtrasoParcelamento) {
		this.diasAtrasoParcelamento = diasAtrasoParcelamento;
	}

	/**
	 * @return Retorna o campo diasAtrasoRecebimentoCarta.
	 */
	public String getDiasAtrasoRecebimentoCarta() {
		return diasAtrasoRecebimentoCarta;
	}

	/**
	 * @param diasAtrasoRecebimentoCarta O diasAtrasoRecebimentoCarta a ser setado.
	 */
	public void setDiasAtrasoRecebimentoCarta(String diasAtrasoRecebimentoCarta) {
		this.diasAtrasoRecebimentoCarta = diasAtrasoRecebimentoCarta;
	}

	/**
	 * @return Retorna o campo guiasPagamento.
	 */
	public String getGuiasPagamento() {
		return guiasPagamento;
	}

	/**
	 * @param guiasPagamento O guiasPagamento a ser setado.
	 */
	public void setGuiasPagamento(String guiasPagamento) {
		this.guiasPagamento = guiasPagamento;
	}

	/**
	 * @return Retorna o campo numeroContasFinal.
	 */
	public String getNumeroContasFinal() {
		return numeroContasFinal;
	}

	/**
	 * @param numeroContasFinal O numeroContasFinal a ser setado.
	 */
	public void setNumeroContasFinal(String numeroContasFinal) {
		this.numeroContasFinal = numeroContasFinal;
	}

	/**
	 * @return Retorna o campo numeroContasInicial.
	 */
	public String getNumeroContasInicial() {
		return numeroContasInicial;
	}

	/**
	 * @param numeroContasInicial O numeroContasInicial a ser setado.
	 */
	public void setNumeroContasInicial(String numeroContasInicial) {
		this.numeroContasInicial = numeroContasInicial;
	}

	/**
	 * @return Retorna o campo parcelaAtraso.
	 */
	public String getParcelaAtraso() {
		return parcelaAtraso;
	}

	/**
	 * @param parcelaAtraso O parcelaAtraso a ser setado.
	 */
	public void setParcelaAtraso(String parcelaAtraso) {
		this.parcelaAtraso = parcelaAtraso;
	}

	/**
	 * @return Retorna o campo valorDebitoFinal.
	 */
	public String getValorDebitoFinal() {
		return valorDebitoFinal;
	}

	/**
	 * @param valorDebitoFinal O valorDebitoFinal a ser setado.
	 */
	public void setValorDebitoFinal(String valorDebitoFinal) {
		this.valorDebitoFinal = valorDebitoFinal;
	}

	/**
	 * @return Retorna o campo valorDebitoInicial.
	 */
	public String getValorDebitoInicial() {
		return valorDebitoInicial;
	}

	/**
	 * @param valorDebitoInicial O valorDebitoInicial a ser setado.
	 */
	public void setValorDebitoInicial(String valorDebitoInicial) {
		this.valorDebitoInicial = valorDebitoInicial;
	}

	/**
	 * @return Retorna o campo descricaoCliente.
	 */
	public String getDescricaoCliente() {
		return descricaoCliente;
	}

	/**
	 * @param descricaoCliente O descricaoCliente a ser setado.
	 */
	public void setDescricaoCliente(String descricaoCliente) {
		this.descricaoCliente = descricaoCliente;
	}

	/**
	 * @return Retorna o campo idCliente.
	 */
	public String getIdCliente() {
		return idCliente;
	}

	/**
	 * @param idCliente O idCliente a ser setado.
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * @return Retorna o campo imovSitCobranca.
	 */
	public String getImovSitCobranca() {
		return imovSitCobranca;
	}

	/**
	 * @param imovSitCobranca O imovSitCobranca a ser setado.
	 */
	public void setImovSitCobranca(String imovSitCobranca) {
		this.imovSitCobranca = imovSitCobranca;
	}

	/**
	 * @return Retorna o campo imovSitEspecialCobranca.
	 */
	public String getImovSitEspecialCobranca() {
		return imovSitEspecialCobranca;
	}

	/**
	 * @param imovSitEspecialCobranca O imovSitEspecialCobranca a ser setado.
	 */
	public void setImovSitEspecialCobranca(String imovSitEspecialCobranca) {
		this.imovSitEspecialCobranca = imovSitEspecialCobranca;
	}

	/**
	 * @return Retorna o campo perfilImovel.
	 */
	public String[] getPerfilImovel() {
		return perfilImovel;
	}

	/**
	 * @param perfilImovel O perfilImovel a ser setado.
	 */
	public void setPerfilImovel(String[] perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	/**
	 * @return Retorna o campo subCategoria.
	 */
	public String[] getSubCategoria() {
		return subCategoria;
	}

	/**
	 * @param subCategoria O subCategoria a ser setado.
	 */
	public void setSubCategoria(String[] subCategoria) {
		this.subCategoria = subCategoria;
	}

	/**
	 * @return Retorna o campo tipoCliente.
	 */
	public String[] getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * @param tipoCliente O tipoCliente a ser setado.
	 */
	public void setTipoCliente(String[] tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	/**
	 * @return Retorna o campo cobrancaGrupo.
	 */
	public String[] getCobrancaGrupo() {
		return cobrancaGrupo;
	}

	/**
	 * @param cobrancaGrupo O cobrancaGrupo a ser setado.
	 */
	public void setCobrancaGrupo(String[] cobrancaGrupo) {
		this.cobrancaGrupo = cobrancaGrupo;
	}

	/**
	 * @return Retorna o campo eloPolo.
	 */
	public String[] getEloPolo() {
		return eloPolo;
	}

	/**
	 * @param eloPolo O eloPolo a ser setado.
	 */
	public void setEloPolo(String[] eloPolo) {
		this.eloPolo = eloPolo;
	}

	/**
	 * @return Retorna o campo gerenciaRegional.
	 */
	public String[] getGerenciaRegional() {
		return gerenciaRegional;
	}

	/**
	 * @param gerenciaRegional O gerenciaRegional a ser setado.
	 */
	public void setGerenciaRegional(String[] gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	/**
	 * @return Retorna o campo idLocalidadeInicial.
	 */
	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	/**
	 * @param idLocalidadeInicial O idLocalidadeInicial a ser setado.
	 */
	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}
	
	/**
	 * @return Retorna o campo localidadeDescricaoInicial.
	 */
	public String getLocalidadeDescricaoInicial() {
		return localidadeDescricaoInicial;
	}

	/**
	 * @param localidadeDescricaoInicial O localidadeDescricaoInicial a ser setado.
	 */
	public void setLocalidadeDescricaoInicial(String localidadeDescricaoInicial) {
		this.localidadeDescricaoInicial = localidadeDescricaoInicial;
	}

	/**
	 * @return Retorna o campo setorComercialDescricaoInicial.
	 */
	public String getSetorComercialDescricaoInicial() {
		return setorComercialDescricaoInicial;
	}

	/**
	 * @param setorComercialDescricaoInicial O setorComercialDescricaoInicial a ser setado.
	 */
	public void setSetorComercialDescricaoInicial(
			String setorComercialDescricaoInicial) {
		this.setorComercialDescricaoInicial = setorComercialDescricaoInicial;
	}

	/**
	 * @return Retorna o campo unidadeNegocio.
	 */
	public String[] getUnidadeNegocio() {
		return unidadeNegocio;
	}

	/**
	 * @param unidadeNegocio O unidadeNegocio a ser setado.
	 */
	public void setUnidadeNegocio(String[] unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	/**
	 * @return Retorna o campo idLocalidadeFinal.
	 */
	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}

	/**
	 * @param idLocalidadeFinal O idLocalidadeFinal a ser setado.
	 */
	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}

	/**
	 * @return Retorna o campo codigoSetorComercialFinal.
	 */
	public String getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	/**
	 * @param codigoSetorComercialFinal O codigoSetorComercialFinal a ser setado.
	 */
	public void setCodigoSetorComercialFinal(String codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	/**
	 * @return Retorna o campo codigoSetorComercialInicial.
	 */
	public String getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	/**
	 * @param codigoSetorComercialInicial O codigoSetorComercialInicial a ser setado.
	 */
	public void setCodigoSetorComercialInicial(String codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}

	/**
	 * @return Retorna o campo localidadeDescricaoFinal.
	 */
	public String getLocalidadeDescricaoFinal() {
		return localidadeDescricaoFinal;
	}

	/**
	 * @param localidadeDescricaoFinal O localidadeDescricaoFinal a ser setado.
	 */
	public void setLocalidadeDescricaoFinal(String localidadeDescricaoFinal) {
		this.localidadeDescricaoFinal = localidadeDescricaoFinal;
	}

	/**
	 * @return Retorna o campo setorComercialDescricaoFinal.
	 */
	public String getSetorComercialDescricaoFinal() {
		return setorComercialDescricaoFinal;
	}

	/**
	 * @param setorComercialDescricaoFinal O setorComercialDescricaoFinal a ser setado.
	 */
	public void setSetorComercialDescricaoFinal(String setorComercialDescricaoFinal) {
		this.setorComercialDescricaoFinal = setorComercialDescricaoFinal;
	}
	
	public void reset(){
		this.idNegativacaoComando = null;
		this.ultimaAlteracaoNegComando = null;
		this.idNegativacaoCriterio = null;
		this.negativadorId = null;
		this.nomeNegativador = null;
		
		this.titulo = null;
		this.solicitacao = null;
		this.simular = null;
		this.dataPrevista = null;
		this.qtdMaximaInclusao = null;
		this.titularidadeNegativacao = null;
		
		this.valorDebitoInicial = null;
		this.valorDebitoFinal = null;
		this.numeroContasInicial = null;
		this.numeroContasFinal = null;
		this.contasRevisao = null;
		this.guiasPagamento = null;
		this.parcelaAtraso = null;
		this.diasAtrasoParcelamento = null;
		this.cartaParcelamentoAtraso = null;
		this.diasAtrasoRecebimentoCarta = null;
		
		this.idCliente = null;
		this.descricaoCliente = null;
		this.imovSitEspecialCobranca = null;
		this.imovSitCobranca = null;
		this.subCategoria = null;
		this.perfilImovel = null;
		this.tipoCliente = null;
		
		this.cobrancaGrupo = null;
		this.gerenciaRegional = null;
		this.unidadeNegocio = null;
		this.eloPolo = null;
		this.idLocalidadeInicial = null;
		this.localidadeDescricaoInicial = null;
		this.codigoSetorComercialInicial = null;
		this.setorComercialDescricaoInicial = null;
		this.idLocalidadeFinal = null;
		this.localidadeDescricaoFinal = null;
		this.codigoSetorComercialFinal = null;
		this.setorComercialDescricaoFinal = null;
	}

	/**
	 * @return Retorna o campo idNegativacaoComando.
	 */
	public String getIdNegativacaoComando() {
		return idNegativacaoComando;
	}

	/**
	 * @param idNegativacaoComando O idNegativacaoComando a ser setado.
	 */
	public void setIdNegativacaoComando(String idNegativacaoComando) {
		this.idNegativacaoComando = idNegativacaoComando;
	}

	/**
	 * @return Retorna o campo idNegativacaoCriterio.
	 */
	public String getIdNegativacaoCriterio() {
		return idNegativacaoCriterio;
	}

	/**
	 * @param idNegativacaoCriterio O idNegativacaoCriterio a ser setado.
	 */
	public void setIdNegativacaoCriterio(String idNegativacaoCriterio) {
		this.idNegativacaoCriterio = idNegativacaoCriterio;
	}

	/**
	 * @return Retorna o campo ultimaAlteracaoNegComando.
	 */
	public Date getUltimaAlteracaoNegComando() {
		return ultimaAlteracaoNegComando;
	}

	/**
	 * @param ultimaAlteracaoNegComando O ultimaAlteracaoNegComando a ser setado.
	 */
	public void setUltimaAlteracaoNegComando(Date ultimaAlteracaoNegComando) {
		this.ultimaAlteracaoNegComando = ultimaAlteracaoNegComando;
	}

	/**
	 * @return Retorna o campo descricaoComandoSimulado.
	 */
	public String getDescricaoComandoSimulado() {
		return descricaoComandoSimulado;
	}

	/**
	 * @param descricaoComandoSimulado O descricaoComandoSimulado a ser setado.
	 */
	public void setDescricaoComandoSimulado(String descricaoComandoSimulado) {
		this.descricaoComandoSimulado = descricaoComandoSimulado;
	}

	/**
	 * @return Retorna o campo executarSimulacao.
	 */
	public String getExecutarSimulacao() {
		return executarSimulacao;
	}

	/**
	 * @param executarSimulacao O executarSimulacao a ser setado.
	 */
	public void setExecutarSimulacao(String executarSimulacao) {
		this.executarSimulacao = executarSimulacao;
	}

	/**
	 * @return Retorna o campo idComandoSimulado.
	 */
	public String getIdComandoSimulado() {
		return idComandoSimulado;
	}

	/**
	 * @param idComandoSimulado O idComandoSimulado a ser setado.
	 */
	public void setIdComandoSimulado(String idComandoSimulado) {
		this.idComandoSimulado = idComandoSimulado;
	}

	/**
	 * @return Retorna o campo ligacaoAguaSituacao.
	 */
	public String[] getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	/**
	 * @param ligacaoAguaSituacao O ligacaoAguaSituacao a ser setado.
	 */
	public void setLigacaoAguaSituacao(String[] ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	/**
	 * @return Retorna o campo ligacaoEsgotoSituacao.
	 */
	public String[] getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	/**
	 * @param ligacaoEsgotoSituacao O ligacaoEsgotoSituacao a ser setado.
	 */
	public void setLigacaoEsgotoSituacao(String[] ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public String getIndicadorBaixaRenda() {
		return indicadorBaixaRenda;
	}

	public void setIndicadorBaixaRenda(String indicadorBaixaRenda) {
		this.indicadorBaixaRenda = indicadorBaixaRenda;
	}

	public String[] getMotivoRetorno() {
		return motivoRetorno;
	}

	public void setMotivoRetorno(String[] motivoRetorno) {
		this.motivoRetorno = motivoRetorno;
	}

	public String getQuantidadeDias() {
		return quantidadeDias;
	}

	public void setQuantidadeDias(String quantidadeDias) {
		this.quantidadeDias = quantidadeDias;
	}

	public String getIndicadorContaNomeCliente() {
		return indicadorContaNomeCliente;
	}

	public void setIndicadorContaNomeCliente(String indicadorContaNomeCliente) {
		this.indicadorContaNomeCliente = indicadorContaNomeCliente;
	}

	public String getIndicadorOrgaoPublico() {
		return indicadorOrgaoPublico;
	}

	public void setIndicadorOrgaoPublico(String indicadorOrgaoPublico) {
		this.indicadorOrgaoPublico = indicadorOrgaoPublico;
	}

	public String[] getCobrancaSituacao() {
		return cobrancaSituacao;
	}

	public void setCobrancaSituacao(String[] cobrancaSituacao) {
		this.cobrancaSituacao = cobrancaSituacao;
	}

	public String[] getCobrancaSituacaoTipo() {
		return cobrancaSituacaoTipo;
	}

	public void setCobrancaSituacaoTipo(String[] cobrancaSituacaoTipo) {
		this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
	}

	
}
