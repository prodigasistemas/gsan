package gcom.gui.cobranca.parcelamento;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0594] Gerar Relação de Parcelamento
 * 
 * @author Ana Maria
 *
 * @date 30/05/2007
 */
public class GerarRelatorioRelacaoParcelamentoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idVisaoRelatorio;
	private String idGerenciaRegional;
	private String idUnidadeNegocio;
	private String idEloPolo;
	private String descricaoElo;
	private String idLocalidade;
	private String descricaoLocalidade;
	private String idSetorComercial;
	private String descricaoSetorComercial;
	private String idQuadra;
	private String idSituacaoParcelamento;
	private String dataParcelamentoInicial;
	private String dataParcelamentoFinal;
	private String[] idsMotivoDesfazimento;
	private String valorDebitoInicial;
	private String valorDebitoFinal;
	private String idUsuarioResponsavel;
	private String descricaoUsuarioResponsavel;
	private String dataConfirmacaoInicial;
	private String dataConfirmacaoFinal;
	private String idUsuarioConfirmacao;
	private String descricaoUsuarioConfirmacao;
	private String indicadorConfirmacaoOperadora;
	private String dataConfirmacaoOperadoraInicial;
	private String dataConfirmacaoOperadoraFinal;
	private String idUnidadeOrganizacional;
	private String descricaoUnidadeOrganizacional;
	
	private String[] perfilImovel;
    private String[] municipiosAssociados;
	
	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest ){

    }
    
	/**
	 * @return Retorna o campo dataParcelamentoFinal.
	 */
	public String getDataParcelamentoFinal() {
		return dataParcelamentoFinal;
	}
	/**
	 * @param dataParcelamentoFinal O dataParcelamentoFinal a ser setado.
	 */
	public void setDataParcelamentoFinal(String dataParcelamentoFinal) {
		this.dataParcelamentoFinal = dataParcelamentoFinal;
	}
	/**
	 * @return Retorna o campo dataParcelamentoInicial.
	 */
	public String getDataParcelamentoInicial() {
		return dataParcelamentoInicial;
	}
	/**
	 * @param dataParcelamentoInicial O dataParcelamentoInicial a ser setado.
	 */
	public void setDataParcelamentoInicial(String dataParcelamentoInicial) {
		this.dataParcelamentoInicial = dataParcelamentoInicial;
	}
	/**
	 * @return Retorna o campo descricaoElo.
	 */
	public String getDescricaoElo() {
		return descricaoElo;
	}
	/**
	 * @param descricaoElo O descricaoElo a ser setado.
	 */
	public void setDescricaoElo(String descricaoElo) {
		this.descricaoElo = descricaoElo;
	}
	/**
	 * @return Retorna o campo descricaoLocalidade.
	 */
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	/**
	 * @param descricaoLocalidade O descricaoLocalidade a ser setado.
	 */
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	/**
	 * @return Retorna o campo descricaoSetorComercial.
	 */
	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}
	/**
	 * @param descricaoSetorComercial O descricaoSetorComercial a ser setado.
	 */
	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}
	/**
	 * @return Retorna o campo descricaoUsuarioResponsavel.
	 */
	public String getDescricaoUsuarioResponsavel() {
		return descricaoUsuarioResponsavel;
	}
	/**
	 * @param descricaoUsuarioResponsavel O descricaoUsuarioResponsavel a ser setado.
	 */
	public void setDescricaoUsuarioResponsavel(String descricaoUsuarioResponsavel) {
		this.descricaoUsuarioResponsavel = descricaoUsuarioResponsavel;
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
	 * @return Retorna o campo idLocalidade.
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}
	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	/**
	 * @return Retorna o campo idQuadra.
	 */
	public String getIdQuadra() {
		return idQuadra;
	}
	/**
	 * @param idQuadra O idQuadra a ser setado.
	 */
	public void setIdQuadra(String idQuadra) {
		this.idQuadra = idQuadra;
	}
	/**
	 * @return Retorna o campo idSetorComercial.
	 */
	public String getIdSetorComercial() {
		return idSetorComercial;
	}
	/**
	 * @param idSetorComercial O idSetorComercial a ser setado.
	 */
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	/**
	 * @return Retorna o campo idSituacaoParcelamento.
	 */
	public String getIdSituacaoParcelamento() {
		return idSituacaoParcelamento;
	}
	/**
	 * @param idSituacaoParcelamento O idSituacaoParcelamento a ser setado.
	 */
	public void setIdSituacaoParcelamento(String idSituacaoParcelamento) {
		this.idSituacaoParcelamento = idSituacaoParcelamento;
	}
	/**
	 * @return Retorna o campo idsMotivoDesfazimento.
	 */
	public String[] getIdsMotivoDesfazimento() {
		return idsMotivoDesfazimento;
	}
	/**
	 * @param idsMotivoDesfazimento O idsMotivoDesfazimento a ser setado.
	 */
	public void setIdsMotivoDesfazimento(String[] idsMotivoDesfazimento) {
		this.idsMotivoDesfazimento = idsMotivoDesfazimento;
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
	/**
	 * @return Retorna o campo idVisaoRelatorio.
	 */
	public String getIdVisaoRelatorio() {
		return idVisaoRelatorio;
	}
	/**
	 * @param idVisaoRelatorio O idVisaoRelatorio a ser setado.
	 */
	public void setIdVisaoRelatorio(String idVisaoRelatorio) {
		this.idVisaoRelatorio = idVisaoRelatorio;
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

	public String getDataConfirmacaoInicial() {
		return dataConfirmacaoInicial;
	}

	public void setDataConfirmacaoInicial(String dataConfirmacaoInicial) {
		this.dataConfirmacaoInicial = dataConfirmacaoInicial;
	}

	public String getDataConfirmacaoFinal() {
		return dataConfirmacaoFinal;
	}

	public void setDataConfirmacaoFinal(String dataConfirmacaoFinal) {
		this.dataConfirmacaoFinal = dataConfirmacaoFinal;
	}

	public String getIdUsuarioConfirmacao() {
		return idUsuarioConfirmacao;
	}

	public void setIdUsuarioConfirmacao(String idUsuarioConfirmacao) {
		this.idUsuarioConfirmacao = idUsuarioConfirmacao;
	}

	public String getDescricaoUsuarioConfirmacao() {
		return descricaoUsuarioConfirmacao;
	}

	public void setDescricaoUsuarioConfirmacao(String descricaoUsuarioConfirmacao) {
		this.descricaoUsuarioConfirmacao = descricaoUsuarioConfirmacao;
	}

	public String getIndicadorConfirmacaoOperadora() {
		return indicadorConfirmacaoOperadora;
	}

	public void setIndicadorConfirmacaoOperadora(
			String indicadorConfirmacaoOperadora) {
		this.indicadorConfirmacaoOperadora = indicadorConfirmacaoOperadora;
	}

	public String getDataConfirmacaoOperadoraInicial() {
		return dataConfirmacaoOperadoraInicial;
	}

	public void setDataConfirmacaoOperadoraInicial(
			String dataConfirmacaoOperadoraInicial) {
		this.dataConfirmacaoOperadoraInicial = dataConfirmacaoOperadoraInicial;
	}

	public String getDataConfirmacaoOperadoraFinal() {
		return dataConfirmacaoOperadoraFinal;
	}

	public void setDataConfirmacaoOperadoraFinal(
			String dataConfirmacaoOperadoraFinal) {
		this.dataConfirmacaoOperadoraFinal = dataConfirmacaoOperadoraFinal;
	}

	public String getDescricaoUnidadeOrganizacional() {
		return descricaoUnidadeOrganizacional;
	}

	public void setDescricaoUnidadeOrganizacional(
			String descricaoUnidadeOrganizacional) {
		this.descricaoUnidadeOrganizacional = descricaoUnidadeOrganizacional;
	}

	public String getIdUnidadeOrganizacional() {
		return idUnidadeOrganizacional;
	}

	public void setIdUnidadeOrganizacional(String idUnidadeOrganizacional) {
		this.idUnidadeOrganizacional = idUnidadeOrganizacional;
	}

	public String[] getMunicipiosAssociados() {
		return municipiosAssociados;
	}

	public void setMunicipiosAssociados(String[] municipiosAssociados) {
		this.municipiosAssociados = municipiosAssociados;
	}
	
    public String[] getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(String[] perfilImovel) {
		this.perfilImovel = perfilImovel;
	}
}
