package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 10/08/2006
 */
public class InserirPrioridadeTipoServicoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String descricao;

	private String abreviatura;

	private String qtdHorasInicio;

	private String qtdHorasFim;

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

}
