package gcom.gui.cobranca.spcserasa;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC 0653] Pesquisar Comando Negativação
 * 
 * @author Kássia Albuquerque
 * @date 07/11/2007
 */


public class ConsultarParametrosComandoNegativacaoActionForm extends ValidatorActionForm {
	
	
	private static final long serialVersionUID = 1L;
	
	// DADOS GERAIS
	
	private String negativador;
	private String qtdInclusoes;
	private String valorTotalDebito;
	private String qtdItensIncluidos;
	private String tituloComando;
	private String descricaoSolicitacao;
	private String indicadorSimularNegativacao;
	private String dataExecucao;
	private String idUsuarioResponsavel;
	private String usuarioResponsavel;
	private String qtdMaxInclusoes;
	private String descricaoTipo;
	private String numOrdemSelecao;
	private String indicadorCoincidente;

	
	// DADOS DO DEBITO
	
	private String referenciaInicial;
    private String referenciaFinal; 
    private String vencimentoInicial;
    private String vencimentoFinal;
    private String valoMinimoDebito;
    private String valoMaximoDebito;
    private String qtdMinimaContas;
    private String qtdMaximaContas;
    private String indicadorContasRevisao;
    private String indicadorGuiaPagamento;
    private String indicadorParcelamentoAtraso;
    private String numDiasAtrasoParcelamento;
    private String indicadorRecCartaAtraso;
    private String numDiasAtrasoAposRecCarta;
	
	// DADOS DO IMOVEL
	
    private String idCliente;
    private String nomeCliente;
    private String tipoRelClie;
    private String indicadorEspecialCobranca;
    private String indicadorSituacaoCobranca;
    private String subcategoria;
    private String perfilImovel;
    private String tipoCliente;
    
	// DADOS DA LOCALIZAÇÃO
    
    private String grupoCobranca;
    private String gerenciaRegional;
    private String unidadeNegocio;
    private String eloPolo;
    private String locInicial;
    private String locFinal;
    private String setComInicial;
    private String setComFinal;
    
	public void reset(){
		this.negativador = null;
		this.qtdInclusoes = null;
		this.valorTotalDebito = null;
		this.qtdItensIncluidos = null;
		this.tituloComando = null;
		this.descricaoSolicitacao = null;
		this.indicadorSimularNegativacao = null;
		this.dataExecucao = null;
		this.idUsuarioResponsavel = null;
		this.usuarioResponsavel = null;
		this.qtdMaxInclusoes = null;
		this.descricaoTipo = null;
		this.numOrdemSelecao = null;
		this.indicadorCoincidente = null;
		
		// DADOS DO DEBITO		
		this.referenciaInicial = null;
		this.referenciaFinal = null; 
		this.vencimentoInicial = null;
		this.vencimentoFinal = null;
		this.valoMinimoDebito = null;
		this.valoMaximoDebito = null;
		this.qtdMinimaContas = null;
		this.qtdMaximaContas = null;
		this.indicadorContasRevisao = null;
		this.indicadorGuiaPagamento = null;
		this.indicadorParcelamentoAtraso = null;
		this.numDiasAtrasoParcelamento = null;
		this.indicadorRecCartaAtraso = null;
		this.numDiasAtrasoAposRecCarta = null;
		
		// DADOS DO IMOVEL		
		this.idCliente = null;
		this.nomeCliente = null;
		this.tipoRelClie = null;
		this.indicadorEspecialCobranca = null;
		this.indicadorSituacaoCobranca = null;
		this.subcategoria = null;
		this.perfilImovel = null;
		this.tipoCliente = null;
	    
		// DADOS DA LOCALIZAÇÃO	    
		this.grupoCobranca = null;
		this.gerenciaRegional = null;
		this.unidadeNegocio = null;
		this.eloPolo = null;
		this.locInicial = null;
		this.locFinal = null;
		this.setComInicial = null;
	}
	
	/**
	 * @return Retorna o campo dataExecucao.
	 */
	public String getDataExecucao() {
		return dataExecucao;
	}

	/**
	 * @param dataExecucao O dataExecucao a ser setado.
	 */
	public void setDataExecucao(String dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	/**
	 * @return Retorna o campo descricaoSolicitacao.
	 */
	public String getDescricaoSolicitacao() {
		return descricaoSolicitacao;
	}

	/**
	 * @param descricaoSolicitacao O descricaoSolicitacao a ser setado.
	 */
	public void setDescricaoSolicitacao(String descricaoSolicitacao) {
		this.descricaoSolicitacao = descricaoSolicitacao;
	}

	/**
	 * @return Retorna o campo negativador.
	 */
	public String getNegativador() {
		return negativador;
	}

	/**
	 * @param negativador O negativador a ser setado.
	 */
	public void setNegativador(String negativador) {
		this.negativador = negativador;
	}

	/**
	 * @return Retorna o campo qtdInclusoes.
	 */
	public String getQtdInclusoes() {
		return qtdInclusoes;
	}

	/**
	 * @param qtdInclusoes O qtdInclusoes a ser setado.
	 */
	public void setQtdInclusoes(String qtdInclusoes) {
		this.qtdInclusoes = qtdInclusoes;
	}

	/**
	 * @return Retorna o campo qtdItensIncluidos.
	 */
	public String getQtdItensIncluidos() {
		return qtdItensIncluidos;
	}

	/**
	 * @param qtdItensIncluidos O qtdItensIncluidos a ser setado.
	 */
	public void setQtdItensIncluidos(String qtdItensIncluidos) {
		this.qtdItensIncluidos = qtdItensIncluidos;
	}

	/**
	 * @return Retorna o campo qtdMaxInclusoes.
	 */
	public String getQtdMaxInclusoes() {
		return qtdMaxInclusoes;
	}

	/**
	 * @param qtdMaxInclusoes O qtdMaxInclusoes a ser setado.
	 */
	public void setQtdMaxInclusoes(String qtdMaxInclusoes) {
		this.qtdMaxInclusoes = qtdMaxInclusoes;
	}

	/**
	 * @return Retorna o campo tituloComando.
	 */
	public String getTituloComando() {
		return tituloComando;
	}

	/**
	 * @param tituloComando O tituloComando a ser setado.
	 */
	public void setTituloComando(String tituloComando) {
		this.tituloComando = tituloComando;
	}

	/**
	 * @return Retorna o campo usuarioResponsavel.
	 */
	public String getUsuarioResponsavel() {
		return usuarioResponsavel;
	}

	/**
	 * @param usuarioResponsavel O usuarioResponsavel a ser setado.
	 */
	public void setUsuarioResponsavel(String usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}

	/**
	 * @return Retorna o campo valorTotalDebito.
	 */
	public String getValorTotalDebito() {
		return valorTotalDebito;
	}

	/**
	 * @param valorTotalDebito O valorTotalDebito a ser setado.
	 */
	public void setValorTotalDebito(String valorTotalDebito) {
		this.valorTotalDebito = valorTotalDebito;
	}

	/**
	 * @return Retorna o campo locFinal.
	 */
	public String getLocFinal() {
		return locFinal;
	}

	/**
	 * @param locFinal O locFinal a ser setado.
	 */
	public void setLocFinal(String locFinal) {
		this.locFinal = locFinal;
	}

	/**
	 * @return Retorna o campo locInicial.
	 */
	public String getLocInicial() {
		return locInicial;
	}

	/**
	 * @param locInicial O locInicial a ser setado.
	 */
	public void setLocInicial(String locInicial) {
		this.locInicial = locInicial;
	}

	/**
	 * @return Retorna o campo nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente O nomeCliente a ser setado.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * @return Retorna o campo numDiasAtrasoAposRecCarta.
	 */
	public String getNumDiasAtrasoAposRecCarta() {
		return numDiasAtrasoAposRecCarta;
	}

	/**
	 * @param numDiasAtrasoAposRecCarta O numDiasAtrasoAposRecCarta a ser setado.
	 */
	public void setNumDiasAtrasoAposRecCarta(String numDiasAtrasoAposRecCarta) {
		this.numDiasAtrasoAposRecCarta = numDiasAtrasoAposRecCarta;
	}

	/**
	 * @return Retorna o campo numDiasAtrasoParcelamento.
	 */
	public String getNumDiasAtrasoParcelamento() {
		return numDiasAtrasoParcelamento;
	}

	/**
	 * @param numDiasAtrasoParcelamento O numDiasAtrasoParcelamento a ser setado.
	 */
	public void setNumDiasAtrasoParcelamento(String numDiasAtrasoParcelamento) {
		this.numDiasAtrasoParcelamento = numDiasAtrasoParcelamento;
	}

	/**
	 * @return Retorna o campo qtdMaximaContas.
	 */
	public String getQtdMaximaContas() {
		return qtdMaximaContas;
	}

	/**
	 * @param qtdMaximaContas O qtdMaximaContas a ser setado.
	 */
	public void setQtdMaximaContas(String qtdMaximaContas) {
		this.qtdMaximaContas = qtdMaximaContas;
	}

	/**
	 * @return Retorna o campo qtdMinimaContas.
	 */
	public String getQtdMinimaContas() {
		return qtdMinimaContas;
	}

	/**
	 * @param qtdMinimaContas O qtdMinimaContas a ser setado.
	 */
	public void setQtdMinimaContas(String qtdMinimaContas) {
		this.qtdMinimaContas = qtdMinimaContas;
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
	 * @return Retorna o campo setComFinal.
	 */
	public String getSetComFinal() {
		return setComFinal;
	}

	/**
	 * @param setComFinal O setComFinal a ser setado.
	 */
	public void setSetComFinal(String setComFinal) {
		this.setComFinal = setComFinal;
	}

	/**
	 * @return Retorna o campo setComInicial.
	 */
	public String getSetComInicial() {
		return setComInicial;
	}

	/**
	 * @param setComInicial O setComInicial a ser setado.
	 */
	public void setSetComInicial(String setComInicial) {
		this.setComInicial = setComInicial;
	}

	/**
	 * @return Retorna o campo tipoRelClie.
	 */
	public String getTipoRelClie() {
		return tipoRelClie;
	}

	/**
	 * @param tipoRelClie O tipoRelClie a ser setado.
	 */
	public void setTipoRelClie(String tipoRelClie) {
		this.tipoRelClie = tipoRelClie;
	}

	/**
	 * @return Retorna o campo valoMaximoDebito.
	 */
	public String getValoMaximoDebito() {
		return valoMaximoDebito;
	}

	/**
	 * @param valoMaximoDebito O valoMaximoDebito a ser setado.
	 */
	public void setValoMaximoDebito(String valoMaximoDebito) {
		this.valoMaximoDebito = valoMaximoDebito;
	}

	/**
	 * @return Retorna o campo valoMinimoDebito.
	 */
	public String getValoMinimoDebito() {
		return valoMinimoDebito;
	}

	/**
	 * @param valoMinimoDebito O valoMinimoDebito a ser setado.
	 */
	public void setValoMinimoDebito(String valoMinimoDebito) {
		this.valoMinimoDebito = valoMinimoDebito;
	}

	/**
	 * @return Retorna o campo vencimentoFinal.
	 */
	public String getVencimentoFinal() {
		return vencimentoFinal;
	}

	/**
	 * @param vencimentoFinal O vencimentoFinal a ser setado.
	 */
	public void setVencimentoFinal(String vencimentoFinal) {
		this.vencimentoFinal = vencimentoFinal;
	}

	/**
	 * @return Retorna o campo vencimentoInicial.
	 */
	public String getVencimentoInicial() {
		return vencimentoInicial;
	}

	/**
	 * @param vencimentoInicial O vencimentoInicial a ser setado.
	 */
	public void setVencimentoInicial(String vencimentoInicial) {
		this.vencimentoInicial = vencimentoInicial;
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
	}/**
	 * @return Retorna o campo indicadorContasRevisao.
	 */
	public String getIndicadorContasRevisao() {
		return indicadorContasRevisao;
	}

	/**
	 * @param indicadorContasRevisao O indicadorContasRevisao a ser setado.
	 */
	public void setIndicadorContasRevisao(String indicadorContasRevisao) {
		this.indicadorContasRevisao = indicadorContasRevisao;
	}

	/**
	 * @return Retorna o campo indicadorEspecialCobranca.
	 */
	public String getIndicadorEspecialCobranca() {
		return indicadorEspecialCobranca;
	}

	/**
	 * @param indicadorEspecialCobranca O indicadorEspecialCobranca a ser setado.
	 */
	public void setIndicadorEspecialCobranca(String indicadorEspecialCobranca) {
		this.indicadorEspecialCobranca = indicadorEspecialCobranca;
	}

	/**
	 * @return Retorna o campo indicadorGuiaPagamento.
	 */
	public String getIndicadorGuiaPagamento() {
		return indicadorGuiaPagamento;
	}

	/**
	 * @param indicadorGuiaPagamento O indicadorGuiaPagamento a ser setado.
	 */
	public void setIndicadorGuiaPagamento(String indicadorGuiaPagamento) {
		this.indicadorGuiaPagamento = indicadorGuiaPagamento;
	}

	/**
	 * @return Retorna o campo indicadorParcelamentoAtraso.
	 */
	public String getIndicadorParcelamentoAtraso() {
		return indicadorParcelamentoAtraso;
	}

	/**
	 * @param indicadorParcelamentoAtraso O indicadorParcelamentoAtraso a ser setado.
	 */
	public void setIndicadorParcelamentoAtraso(String indicadorParcelamentoAtraso) {
		this.indicadorParcelamentoAtraso = indicadorParcelamentoAtraso;
	}

	/**
	 * @return Retorna o campo indicadorRecCartaAtraso.
	 */
	public String getIndicadorRecCartaAtraso() {
		return indicadorRecCartaAtraso;
	}

	/**
	 * @param indicadorRecCartaAtraso O indicadorRecCartaAtraso a ser setado.
	 */
	public void setIndicadorRecCartaAtraso(String indicadorRecCartaAtraso) {
		this.indicadorRecCartaAtraso = indicadorRecCartaAtraso;
	}

	/**
	 * @return Retorna o campo indicadorSimularNegativacao.
	 */
	public String getIndicadorSimularNegativacao() {
		return indicadorSimularNegativacao;
	}

	/**
	 * @param indicadorSimularNegativacao O indicadorSimularNegativacao a ser setado.
	 */
	public void setIndicadorSimularNegativacao(String indicadorSimularNegativacao) {
		this.indicadorSimularNegativacao = indicadorSimularNegativacao;
	}

	/**
	 * @return Retorna o campo indicadorSituacaoCobranca.
	 */
	public String getIndicadorSituacaoCobranca() {
		return indicadorSituacaoCobranca;
	}

	/**
	 * @param indicadorSituacaoCobranca O indicadorSituacaoCobranca a ser setado.
	 */
	public void setIndicadorSituacaoCobranca(String indicadorSituacaoCobranca) {
		this.indicadorSituacaoCobranca = indicadorSituacaoCobranca;
	}

	/**
	 * @return Retorna o campo eloPolo.
	 */
	public String getEloPolo() {
		return eloPolo;
	}

	/**
	 * @param eloPolo O eloPolo a ser setado.
	 */
	public void setEloPolo(String eloPolo) {
		this.eloPolo = eloPolo;
	}

	/**
	 * @return Retorna o campo gerenciaRegional.
	 */
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	/**
	 * @param gerenciaRegional O gerenciaRegional a ser setado.
	 */
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	/**
	 * @return Retorna o campo grupoCobranca.
	 */
	public String getGrupoCobranca() {
		return grupoCobranca;
	}

	/**
	 * @param grupoCobranca O grupoCobranca a ser setado.
	 */
	public void setGrupoCobranca(String grupoCobranca) {
		this.grupoCobranca = grupoCobranca;
	}

	/**
	 * @return Retorna o campo unidadeNegocio.
	 */
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	/**
	 * @param unidadeNegocio O unidadeNegocio a ser setado.
	 */
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	/**
	 * @return Retorna o campo perfilImovel.
	 */
	public String getPerfilImovel() {
		return perfilImovel;
	}

	/**
	 * @param perfilImovel O perfilImovel a ser setado.
	 */
	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	/**
	 * @return Retorna o campo subcategoria.
	 */
	public String getSubcategoria() {
		return subcategoria;
	}

	/**
	 * @param subcategoria O subcategoria a ser setado.
	 */
	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}

	/**
	 * @return Retorna o campo tipoCliente.
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * @param tipoCliente O tipoCliente a ser setado.
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	/**
	 * @return Retorna o campo descricaoTipo.
	 */
	public String getDescricaoTipo() {
		return descricaoTipo;
	}

	/**
	 * @param descricaoTipo O descricaoTipo a ser setado.
	 */
	public void setDescricaoTipo(String descricaoTipo) {
		this.descricaoTipo = descricaoTipo;
	}

	/**
	 * @return Retorna o campo indicadorCoincidente.
	 */
	public String getIndicadorCoincidente() {
		return indicadorCoincidente;
	}

	/**
	 * @param indicadorCoincidente O indicadorCoincidente a ser setado.
	 */
	public void setIndicadorCoincidente(String indicadorCoincidente) {
		this.indicadorCoincidente = indicadorCoincidente;
	}

	/**
	 * @return Retorna o campo numOrdemSelecao.
	 */
	public String getNumOrdemSelecao() {
		return numOrdemSelecao;
	}

	/**
	 * @param numOrdemSelecao O numOrdemSelecao a ser setado.
	 */
	public void setNumOrdemSelecao(String numOrdemSelecao) {
		this.numOrdemSelecao = numOrdemSelecao;
	}

	/**
	 * @return Retorna o campo idUsuarioResponsavel.
	 */
	public String getIdUsuarioResponsavel() {
		return idUsuarioResponsavel;
	}

	/**
	 * @param idUsuarioResponsavel O idUsuarioResponsavel a ser setado.
	 */
	public void setIdUsuarioResponsavel(String idUsuarioResponsavel) {
		this.idUsuarioResponsavel = idUsuarioResponsavel;
	}




}
