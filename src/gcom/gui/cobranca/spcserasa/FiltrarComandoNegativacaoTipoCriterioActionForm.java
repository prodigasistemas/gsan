package gcom.gui.cobranca.spcserasa;

import org.apache.struts.action.ActionForm;

/**
 * Classe que representa o form da pagina de exibição do filtro 
 * para pesquisa de comandos de negativação por critério
 * 
 * @author: Thiago Vieira
 * @date: 10/1/2007
 */

public class FiltrarComandoNegativacaoTipoCriterioActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String idNegativador;
	private String titulo;
	private String tipoPesquisaTitulo;
	private String comandoSimulado;
	private String nomeCliente;
	private String codigoCliente;
	private String idTipoRelacao;
	private String idGrupoCobranca;
	private String idGerenciaRegional;
	private String idUnidadeNegocio;
	private String idEloPolo;
	private String codigoLocalidadeInicial;
	private String descricaoLocalidadeInicial;
	private String codigoSetorComercialInicial;
	private String descricaoSetorComercialInicial;
	private String codigoLocalidadeFinal;
	private String descricaoLocalidadeFinal;
	private String codigoSetorComercialFinal;
	private String descricaoSetorComercialFinal;
	private String geracaoComandoDataInicial;
	private String geracaoComandoDataFinal;
	private String execucaoComandoDataInicial;
	private String execucaoComandoDataFinal;
	private String referenciaDebitoDataInicial;
	private String referenciaDebitoDataFinal;
	private String vencimentoDebitoDataInicial;
	private String vencimentoDebitoDataFinal;
	private String valorDebitoInicial;
	private String valorDebitoFinal;
	private String numeroContasInicial;
	private String numeroContasFinal;
	private String cartaParcelamentoAtraso;
	private String situacaoComando;
	
	private String localidadeInicialIncompativel;
	private String okCliente;
    private String indicadorContaNomeCliente;
	
//	public void reset(ActionMapping actionMapping,
//            HttpServletRequest httpServletRequest) {
//		
//		this.idNegativador = "";
////		this.collNegativador = new ArrayList();
//		this.titulo = "";
//		this.tipoPesquisaTitulo = "";
//		this.comandoSimulado = "";
//		this.nomeCliente = "";
//		this.codigoCliente = "";
//		this.idTipoRelacao = "";
//		this.idGrupoCobranca = "";
//		this.idGerenciaRegional = "";
//		this.idUnidadeNegocio = "";
//		this.idEloPolo = "";
//		this.codigoLocalidadeInicial = "";
//		this.descricaoLocalidadeInicial = "";
//		this.codigoSetorComercialInicial = "";
//		this.descricaoSetorComercialInicial = "";
//		this.codigoLocalidadeFinal = "";
//		this.descricaoLocalidadeFinal = "";
//		this.codigoSetorComercialFinal = "";
//		this.descricaoSetorComercialFinal = "";
//		this.geracaoComandoDataInicial = "";
//		this.geracaoComandoDataFinal = "";
//		this.execucaoComandoDataInicial = "";
//		this.execucaoComandoDataFinal = "";
//		this.referenciaDebitoDataInicial = "";
//		this.referenciaDebitoDataFinal = "";
//		this.vencimentoDebitoDataInicial = "";
//		this.vencimentoDebitoDataFinal = "";
//		this.valorDebitoInicial = "";
//		this.valorDebitoFinal = "";
//		this.numeroContasInicial = "";
//		this.numeroContasFinal = "";
//		this.cartaParcelamentoAtraso = "";
//		this.situacaoComando = "";
//    	
//		this.okCliente = "";
//		this.localidadeInicialIncompativel = "";
//
//    }

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
	 * @return Retorna o campo codigoCliente.
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente O codigoCliente a ser setado.
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return Retorna o campo codigoLocalidadeFinal.
	 */
	public String getCodigoLocalidadeFinal() {
		return codigoLocalidadeFinal;
	}

	/**
	 * @param codigoLocalidadeFinal O codigoLocalidadeFinal a ser setado.
	 */
	public void setCodigoLocalidadeFinal(String codigoLocalidadeFinal) {
		this.codigoLocalidadeFinal = codigoLocalidadeFinal;
	}

	/**
	 * @return Retorna o campo codigoLocalidadeInicial.
	 */
	public String getCodigoLocalidadeInicial() {
		return codigoLocalidadeInicial;
	}

	/**
	 * @param codigoLocalidadeInicial O codigoLocalidadeInicial a ser setado.
	 */
	public void setCodigoLocalidadeInicial(String codigoLocalidadeInicial) {
		this.codigoLocalidadeInicial = codigoLocalidadeInicial;
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
	 * @return Retorna o campo comandoSimulado.
	 */
	public String getComandoSimulado() {
		return comandoSimulado;
	}

	/**
	 * @param comandoSimulado O comandoSimulado a ser setado.
	 */
	public void setComandoSimulado(String comandoSimulado) {
		this.comandoSimulado = comandoSimulado;
	}

	/**
	 * @return Retorna o campo descricaoLocalidadeFinal.
	 */
	public String getDescricaoLocalidadeFinal() {
		return descricaoLocalidadeFinal;
	}

	/**
	 * @param descricaoLocalidadeFinal O descricaoLocalidadeFinal a ser setado.
	 */
	public void setDescricaoLocalidadeFinal(String descricaoLocalidadeFinal) {
		this.descricaoLocalidadeFinal = descricaoLocalidadeFinal;
	}

	/**
	 * @return Retorna o campo descricaoLocalidadeInicial.
	 */
	public String getDescricaoLocalidadeInicial() {
		return descricaoLocalidadeInicial;
	}

	/**
	 * @param descricaoLocalidadeInicial O descricaoLocalidadeInicial a ser setado.
	 */
	public void setDescricaoLocalidadeInicial(String descricaoLocalidadeInicial) {
		this.descricaoLocalidadeInicial = descricaoLocalidadeInicial;
	}

	/**
	 * @return Retorna o campo descricaoSetorComercialFinal.
	 */
	public String getDescricaoSetorComercialFinal() {
		return descricaoSetorComercialFinal;
	}

	/**
	 * @param descricaoSetorComercialFinal O descricaoSetorComercialFinal a ser setado.
	 */
	public void setDescricaoSetorComercialFinal(String descricaoSetorComercialFinal) {
		this.descricaoSetorComercialFinal = descricaoSetorComercialFinal;
	}

	/**
	 * @return Retorna o campo descricaoSetorComercialInicial.
	 */
	public String getDescricaoSetorComercialInicial() {
		return descricaoSetorComercialInicial;
	}

	/**
	 * @param descricaoSetorComercialInicial O descricaoSetorComercialInicial a ser setado.
	 */
	public void setDescricaoSetorComercialInicial(
			String descricaoSetorComercialInicial) {
		this.descricaoSetorComercialInicial = descricaoSetorComercialInicial;
	}

	/**
	 * @return Retorna o campo geracaoComandoDataFinal.
	 */
	public String getGeracaoComandoDataFinal() {
		return geracaoComandoDataFinal;
	}

	/**
	 * @param geracaoComandoDataFinal O geracaoComandoDataFinal a ser setado.
	 */
	public void setGeracaoComandoDataFinal(String geracaoComandoDataFinal) {
		this.geracaoComandoDataFinal = geracaoComandoDataFinal;
	}

	/**
	 * @return Retorna o campo geracaoComandoDataInicial.
	 */
	public String getGeracaoComandoDataInicial() {
		return geracaoComandoDataInicial;
	}

	/**
	 * @param geracaoComandoDataInicial O geracaoComandoDataInicial a ser setado.
	 */
	public void setGeracaoComandoDataInicial(String geracaoComandoDataInicial) {
		this.geracaoComandoDataInicial = geracaoComandoDataInicial;
	}

	
	/**
	 * @return Retorna o campo execucaoComandoDataFinal.
	 */
	public String getExecucaoComandoDataFinal() {
		return execucaoComandoDataFinal;
	}

	/**
	 * @param execucaoComandoDataFinal O execucaoComandoDataFinal a ser setado.
	 */
	public void setExecucaoComandoDataFinal(String execucaoComandoDataFinal) {
		this.execucaoComandoDataFinal = execucaoComandoDataFinal;
	}

	/**
	 * @return Retorna o campo execucaoComandoDataInicial.
	 */
	public String getExecucaoComandoDataInicial() {
		return execucaoComandoDataInicial;
	}

	/**
	 * @param execucaoComandoDataInicial O execucaoComandoDataInicial a ser setado.
	 */
	public void setExecucaoComandoDataInicial(String execucaoComandoDataInicial) {
		this.execucaoComandoDataInicial = execucaoComandoDataInicial;
	}

	/**
	 * @return Retorna o campo idEloPolo.
	 */
	public String getIdEloPolo() {
		return idEloPolo;
	}

	/**
	 * @param idEloPolo O idEloPolo a ser setado.
	 */
	public void setIdEloPolo(String idEloPolo) {
		this.idEloPolo = idEloPolo;
	}

	/**
	 * @return Retorna o campo idGerenciaRegional.
	 */
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	/**
	 * @param idGerenciaRegional O idGerenciaRegional a ser setado.
	 */
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	/**
	 * @return Retorna o campo idGrupoCobranca.
	 */
	public String getIdGrupoCobranca() {
		return idGrupoCobranca;
	}

	/**
	 * @param idGrupoCobranca O idGrupoCobranca a ser setado.
	 */
	public void setIdGrupoCobranca(String idGrupoCobranca) {
		this.idGrupoCobranca = idGrupoCobranca;
	}

	/**
	 * @return Retorna o campo idNegativador.
	 */
	public String getIdNegativador() {
		return idNegativador;
	}

	/**
	 * @param idNegativador O idNegativador a ser setado.
	 */
	public void setIdNegativador(String idNegativador) {
		this.idNegativador = idNegativador;
	}

	/**
	 * @return Retorna o campo idUnidadeNegocio.
	 */
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	/**
	 * @param idUnidadeNegocio O idUnidadeNegocio a ser setado.
	 */
	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
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
	 * @param numeroContasInical O numeroContasInicial a ser setado.
	 */
	public void setNumeroContasInicial(String numeroContasInicial) {
		this.numeroContasInicial = numeroContasInicial;
	}

	/**
	 * @return Retorna o campo referenciaDebitoDataFinal.
	 */
	public String getReferenciaDebitoDataFinal() {
		return referenciaDebitoDataFinal;
	}

	/**
	 * @param referenciaDebitoDataFinal O referenciaDebitoDataFinal a ser setado.
	 */
	public void setReferenciaDebitoDataFinal(String referenciaDebitoDataFinal) {
		this.referenciaDebitoDataFinal = referenciaDebitoDataFinal;
	}

	/**
	 * @return Retorna o campo referenciaDebitoDataInicial.
	 */
	public String getReferenciaDebitoDataInicial() {
		return referenciaDebitoDataInicial;
	}

	/**
	 * @param referenciaDebitoDataInicial O referenciaDebitoDataInicial a ser setado.
	 */
	public void setReferenciaDebitoDataInicial(String referenciaDebitoDataInicial) {
		this.referenciaDebitoDataInicial = referenciaDebitoDataInicial;
	}

	/**
	 * @return Retorna o campo situacaoComando.
	 */
	public String getSituacaoComando() {
		return situacaoComando;
	}

	/**
	 * @param situacaoComando O situacaoComando a ser setado.
	 */
	public void setSituacaoComando(String situacaoComando) {
		this.situacaoComando = situacaoComando;
	}

	/**
	 * @return Retorna o campo tipoPesquisaTitulo.
	 */
	public String getTipoPesquisaTitulo() {
		return tipoPesquisaTitulo;
	}

	/**
	 * @param tipoPesquisaTitulo O tipoPesquisaTitulo a ser setado.
	 */
	public void setTipoPesquisaTitulo(String tipoPesquisaTitulo) {
		this.tipoPesquisaTitulo = tipoPesquisaTitulo;
	}

	/**
	 * @return Retorna o campo tipoRelacao.
	 */
	public String getIdTipoRelacao() {
		return idTipoRelacao;
	}

	/**
	 * @param tipoRelacao O tipoRelacao a ser setado.
	 */
	public void setIdTipoRelacao(String idTipoRelacao) {
		this.idTipoRelacao = idTipoRelacao;
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
	 * @return Retorna o campo vencimentoDebitoDataFinal.
	 */
	public String getVencimentoDebitoDataFinal() {
		return vencimentoDebitoDataFinal;
	}

	/**
	 * @param vencimentoDebitoDataFinal O vencimentoDebitoDataFinal a ser setado.
	 */
	public void setVencimentoDebitoDataFinal(String vencimentoDebitoDataFinal) {
		this.vencimentoDebitoDataFinal = vencimentoDebitoDataFinal;
	}

	/**
	 * @return Retorna o campo vencimentoDebitoDataInicial.
	 */
	public String getVencimentoDebitoDataInicial() {
		return vencimentoDebitoDataInicial;
	}

	/**
	 * @param vencimentoDebitoDataInicial O vencimentoDebitoDataInicial a ser setado.
	 */
	public void setVencimentoDebitoDataInicial(String vencimentoDebitoDataInicial) {
		this.vencimentoDebitoDataInicial = vencimentoDebitoDataInicial;
	}

	/**
	 * @return Retorna o campo okCliente.
	 */
	public String getOkCliente() {
		return okCliente;
	}

	/**
	 * @param okCliente O okCliente a ser setado.
	 */
	public void setOkCliente(String okCliente) {
		this.okCliente = okCliente;
	}

	/**
	 * @return Retorna o campo localidadeInicialIncompativel.
	 */
	public String getLocalidadeInicialIncompativel() {
		return localidadeInicialIncompativel;
	}

	/**
	 * @param localidadeInicialIncompativel O localidadeInicialIncompativel a ser setado.
	 */
	public void setLocalidadeInicialIncompativel(
			String localidadeInicialIncompativel) {
		this.localidadeInicialIncompativel = localidadeInicialIncompativel;
	}

	public String getIndicadorContaNomeCliente() {
		return indicadorContaNomeCliente;
	}

	public void setIndicadorContaNomeCliente(String indicadorContaNomeCliente) {
		this.indicadorContaNomeCliente = indicadorContaNomeCliente;
	}

	
	
		
}
