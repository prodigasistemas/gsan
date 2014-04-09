package gcom.gui.relatorio.financeiro;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0822] Gerar Relatório do Valor Referente a Volumes Consumidos e Não Faturados
 * 
 * @see gcom.gui.relatorio.financeiro.ExibirGerarRelatorioVolumesConsumidosNaoFaturadosAction
 * @see gcom.gui.relatorio.financeiro.GerarRelatorioVolumesConsumidosNaoFaturadosAction
 * 
 * @author Victor Cisneiros
 * @date 09/07/2008
 */
public class GerarRelatorioVolumesConsumidosNaoFaturadosActionForm extends ValidatorForm {

	private static final long serialVersionUID = 1L;
	
	public String mesAno;
	
	private String opcaoTotalizacao;

	private String gerenciaRegionalId;
	private String gerenciaRegionalPorUnidadeId;
	private String gerenciaRegionalPorLocalidadeId;
	private String unidadeNegocioId;
	private String unidadeNegocioPorLocalidadeId;
	private String codigoLocalidade;
	private String descricaoLocalidade;
	private String codigoMunicipio;
	private String descricaoMunicipio;
	
	
	private String opcaoRelatorio;
	
	public GerarRelatorioVolumesConsumidosNaoFaturadosActionForm() { }

	/**
	 * @return Retorna o campo codigoLocalidade.
	 */
	public String getCodigoLocalidade() {
		return codigoLocalidade;
	}

	/**
	 * @param codigoLocalidade O codigoLocalidade a ser setado.
	 */
	public void setCodigoLocalidade(String codigoLocalidade) {
		this.codigoLocalidade = codigoLocalidade;
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
	 * @return Retorna o campo gerenciaRegionalId.
	 */
	public String getGerenciaRegionalId() {
		return gerenciaRegionalId;
	}

	/**
	 * @param gerenciaRegionalId O gerenciaRegionalId a ser setado.
	 */
	public void setGerenciaRegionalId(String gerenciaRegionalId) {
		this.gerenciaRegionalId = gerenciaRegionalId;
	}

	/**
	 * @return Retorna o campo gerenciaRegionalporLocalidadeId.
	 */
	public String getGerenciaRegionalPorLocalidadeId() {
		return gerenciaRegionalPorLocalidadeId;
	}

	/**
	 * @param gerenciaRegionalporLocalidadeId O gerenciaRegionalporLocalidadeId a ser setado.
	 */
	public void setGerenciaRegionalPorLocalidadeId(
			String gerenciaRegionalporLocalidadeId) {
		this.gerenciaRegionalPorLocalidadeId = gerenciaRegionalporLocalidadeId;
	}

	/**
	 * @return Retorna o campo opcaoRelatorio.
	 */
	public String getOpcaoRelatorio() {
		return opcaoRelatorio;
	}

	/**
	 * @param opcaoRelatorio O opcaoRelatorio a ser setado.
	 */
	public void setOpcaoRelatorio(String opcaoRelatorio) {
		this.opcaoRelatorio = opcaoRelatorio;
	}

	/**
	 * @return Retorna o campo opcaoTotalizacao.
	 */
	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}

	/**
	 * @param opcaoTotalizacao O opcaoTotalizacao a ser setado.
	 */
	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	/**
	 * @return Retorna o campo unidadeNegocioId.
	 */
	public String getUnidadeNegocioId() {
		return unidadeNegocioId;
	}

	/**
	 * @param unidadeNegocioId O unidadeNegocioId a ser setado.
	 */
	public void setUnidadeNegocioId(String unidadeNegocioId) {
		this.unidadeNegocioId = unidadeNegocioId;
	}

	/**
	 * @return Retorna o campo mesAno.
	 */
	public String getMesAno() {
		return mesAno;
	}

	/**
	 * @param mesAno O mesAno a ser setado.
	 */
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	/**
	 * @return Retorna o campo gerenciaRegionalPorUnidadeId.
	 */
	public String getGerenciaRegionalPorUnidadeId() {
		return gerenciaRegionalPorUnidadeId;
	}

	/**
	 * @param gerenciaRegionalPorUnidadeId O gerenciaRegionalPorUnidadeId a ser setado.
	 */
	public void setGerenciaRegionalPorUnidadeId(String gerenciaRegionalPorUnidadeId) {
		this.gerenciaRegionalPorUnidadeId = gerenciaRegionalPorUnidadeId;
	}

	/**
	 * @return Retorna o campo unidadeNegocioPorLocalidadeId.
	 */
	public String getUnidadeNegocioPorLocalidadeId() {
		return unidadeNegocioPorLocalidadeId;
	}

	/**
	 * @param unidadeNegocioPorLocalidadeId O unidadeNegocioPorLocalidadeId a ser setado.
	 */
	public void setUnidadeNegocioPorLocalidadeId(
			String unidadeNegocioPorLocalidadeId) {
		this.unidadeNegocioPorLocalidadeId = unidadeNegocioPorLocalidadeId;
	}

	public String getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public void setCodigoMunicipio(String codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}

	public void setDescricaoMunicipio(String descricaoMunicipio) {
		this.descricaoMunicipio = descricaoMunicipio;
	}
}
