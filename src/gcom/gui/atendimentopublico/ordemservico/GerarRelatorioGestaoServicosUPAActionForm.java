package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0776] Filtrar Relatório de Gestão de Serviços UPA
 * 
 * @see gcom.gui.atendimentopublico.ordemservico.ExibirGerarRelatorioGestaoServicosUPAAction
 * @see gcom.gui.relatorio.atendimentopublico.GerarRelatorioGestaoServicosUPAAction
 * @see gcom.relatorio.atendimentopublico.RelatorioGestaoServicosUPA
 * 
 * @author Victor Cisneiros
 * @date 22/05/2008
 */
public class GerarRelatorioGestaoServicosUPAActionForm extends ValidatorForm {

	private static final long serialVersionUID = 1L;
	
	private String origemServico;
	private String situacaoOrdemServico;
	
	private String[] tipoServico;
	private String[] tipoServicoSelecionados;
	
	private String periodoGeracaoInicial;
	private String periodoGeracaoFinal;

	private String idUnidadeOrganizacional;
	private String descricaoUnidadeOrganizacional;
	
	private String idUnidadeSuperior;
	private String descricaoUnidadeSuperior;
	
	private String idEmpresa;
	private String descricaoEmpresa;
	
	/**
	 * @return Retorna o campo descricaoEmpresa.
	 */
	public String getDescricaoEmpresa() {
		return descricaoEmpresa;
	}
	/**
	 * @param descricaoEmpresa O descricaoEmpresa a ser setado.
	 */
	public void setDescricaoEmpresa(String descricaoEmpresa) {
		this.descricaoEmpresa = descricaoEmpresa;
	}
	/**
	 * @return Retorna o campo descricaoUnidadeOrganizacional.
	 */
	public String getDescricaoUnidadeOrganizacional() {
		return descricaoUnidadeOrganizacional;
	}
	/**
	 * @param descricaoUnidadeOrganizacional O descricaoUnidadeOrganizacional a ser setado.
	 */
	public void setDescricaoUnidadeOrganizacional(
			String descricaoUnidadeOrganizacional) {
		this.descricaoUnidadeOrganizacional = descricaoUnidadeOrganizacional;
	}
	/**
	 * @return Retorna o campo descricaoUnidadeSuperior.
	 */
	public String getDescricaoUnidadeSuperior() {
		return descricaoUnidadeSuperior;
	}
	/**
	 * @param descricaoUnidadeSuperior O descricaoUnidadeSuperior a ser setado.
	 */
	public void setDescricaoUnidadeSuperior(String descricaoUnidadeSuperior) {
		this.descricaoUnidadeSuperior = descricaoUnidadeSuperior;
	}
	/**
	 * @return Retorna o campo idEmpresa.
	 */
	public String getIdEmpresa() {
		return idEmpresa;
	}
	/**
	 * @param idEmpresa O idEmpresa a ser setado.
	 */
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	/**
	 * @return Retorna o campo idUnidadeOrganizacional.
	 */
	public String getIdUnidadeOrganizacional() {
		return idUnidadeOrganizacional;
	}
	/**
	 * @param idUnidadeOrganizacional O idUnidadeOrganizacional a ser setado.
	 */
	public void setIdUnidadeOrganizacional(String idUnidadeOrganizacional) {
		this.idUnidadeOrganizacional = idUnidadeOrganizacional;
	}
	/**
	 * @return Retorna o campo idUnidadeSuperior.
	 */
	public String getIdUnidadeSuperior() {
		return idUnidadeSuperior;
	}
	/**
	 * @param idUnidadeSuperior O idUnidadeSuperior a ser setado.
	 */
	public void setIdUnidadeSuperior(String idUnidadeSuperior) {
		this.idUnidadeSuperior = idUnidadeSuperior;
	}
	/**
	 * @return Retorna o campo origemServico.
	 */
	public String getOrigemServico() {
		return origemServico;
	}
	/**
	 * @param origemServico O origemServico a ser setado.
	 */
	public void setOrigemServico(String origemServico) {
		this.origemServico = origemServico;
	}
	/**
	 * @return Retorna o campo periodoGeracaoFinal.
	 */
	public String getPeriodoGeracaoFinal() {
		return periodoGeracaoFinal;
	}
	/**
	 * @param periodoGeracaoFinal O periodoGeracaoFinal a ser setado.
	 */
	public void setPeriodoGeracaoFinal(String periodoGeracaoFinal) {
		this.periodoGeracaoFinal = periodoGeracaoFinal;
	}
	/**
	 * @return Retorna o campo periodoGeracaoInicial.
	 */
	public String getPeriodoGeracaoInicial() {
		return periodoGeracaoInicial;
	}
	/**
	 * @param periodoGeracaoInicial O periodoGeracaoInicial a ser setado.
	 */
	public void setPeriodoGeracaoInicial(String periodoGeracaoInicial) {
		this.periodoGeracaoInicial = periodoGeracaoInicial;
	}
	/**
	 * @return Retorna o campo situacaoOrdemServico.
	 */
	public String getSituacaoOrdemServico() {
		return situacaoOrdemServico;
	}
	/**
	 * @param situacaoOrdemServico O situacaoOrdemServico a ser setado.
	 */
	public void setSituacaoOrdemServico(String situacaoOrdemServico) {
		this.situacaoOrdemServico = situacaoOrdemServico;
	}
	/**
	 * @return Retorna o campo tipoServico.
	 */
	public String[] getTipoServico() {
		return tipoServico;
	}
	/**
	 * @param tipoServico O tipoServico a ser setado.
	 */
	public void setTipoServico(String[] tipoServico) {
		this.tipoServico = tipoServico;
	}
	/**
	 * @return Retorna o campo tipoServicoSelecionados.
	 */
	public String[] getTipoServicoSelecionados() {
		return tipoServicoSelecionados;
	}
	/**
	 * @param tipoServicoSelecionados O tipoServicoSelecionados a ser setado.
	 */
	public void setTipoServicoSelecionados(String[] tipoServicoSelecionados) {
		this.tipoServicoSelecionados = tipoServicoSelecionados;
	}

	
}
