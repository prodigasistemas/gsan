package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author Thiago Tenório
 * @date 10/08/2006
 */
public class FiltrarPrioridadeTipoServicoActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;
	private String codigo;
	
	private String descricao;

	private String abreviatura;

	private String qtdHorasInicio;

	private String qtdHorasFim;
	
	private String indicadorAtualizar;
	
	private String tipoPesquisa;

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}

	/**
	 * @return Retorna o campo abreviatura.
	 */
	public String getAbreviatura() {
		return abreviatura;
	}

	/**
	 * @param abreviatura
	 *            O abreviatura a ser setado.
	 */
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return Retorna o campo qtdHorasFim.
	 */
	public String getQtdHorasFim() {
		return qtdHorasFim;
	}

	/**
	 * @param qtdHorasFim
	 *            O qtdHorasFim a ser setado.
	 */
	public void setQtdHorasFim(String qtdHorasFim) {
		this.qtdHorasFim = qtdHorasFim;
	}

	/**
	 * @return Retorna o campo qtdHorasInicio.
	 */
	public String getQtdHorasInicio() {
		return qtdHorasInicio;
	}

	/**
	 * @param qtdHorasInicio
	 *            O qtdHorasInicio a ser setado.
	 */
	public void setQtdHorasInicio(String qtdHorasInicio) {
		this.qtdHorasInicio = qtdHorasInicio;
	}

	/**
	 * @return Retorna o campo codigo.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo O codigo a ser setado.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
