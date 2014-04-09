package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 21/09/2006
 */
public class ExibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String titulo;

	private String cabecalho;

	private String mes;

	private String ano;

	/**
	 * @return Retorna o campo ano.
	 */
	public String getAno() {
		return ano;
	}

	/**
	 * @param ano O ano a ser setado.
	 */
	public void setAno(String ano) {
		this.ano = ano;
	}

	/**
	 * @return Retorna o campo mes.
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * @param mes O mes a ser setado.
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * @return Retorna o campo titulo.
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo
	 *            O titulo a ser setado.
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return Retorna o campo cabecalho.
	 */
	public String getCabecalho() {
		return cabecalho;
	}

	/**
	 * @param cabecalho
	 *            O cabecalho a ser setado.
	 */
	public void setCabecalho(String cabecalho) {
		this.cabecalho = cabecalho;
	}

}
