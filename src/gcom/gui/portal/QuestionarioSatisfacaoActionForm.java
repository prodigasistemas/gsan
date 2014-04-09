package gcom.gui.portal;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Formulario do Questionario de Satisfacao do Cliente
 * 
 * @author Paulo Diniz
 * @date 20/06/2011
 */
public class QuestionarioSatisfacaoActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String indicadorBemAtendido;

	private String comentarioBemAtendido;

	private String indicadorSolicitacaoResolvida;

	private String comentarioSolicitacaoResolvida;

	private String indicadorContatoEquipeCampo;

	private String comentarioContatoEquipeCampo;

	private String nota;

	private String comentarioGeral;

	private String idRegistroAtendimentoEncerrado;

	public String getIndicadorBemAtendido() {
		return indicadorBemAtendido;
	}

	public void setIndicadorBemAtendido(String indicadorBemAtendido) {
		this.indicadorBemAtendido = indicadorBemAtendido;
	}

	public String getComentarioBemAtendido() {
		return comentarioBemAtendido;
	}

	public void setComentarioBemAtendido(String comentarioBemAtendido) {
		this.comentarioBemAtendido = comentarioBemAtendido;
	}

	public String getIndicadorSolicitacaoResolvida() {
		return indicadorSolicitacaoResolvida;
	}

	public void setIndicadorSolicitacaoResolvida(
			String indicadorSolicitacaoResolvida) {
		this.indicadorSolicitacaoResolvida = indicadorSolicitacaoResolvida;
	}

	public String getComentarioSolicitacaoResolvida() {
		return comentarioSolicitacaoResolvida;
	}

	public void setComentarioSolicitacaoResolvida(
			String comentarioSolicitacaoResolvida) {
		this.comentarioSolicitacaoResolvida = comentarioSolicitacaoResolvida;
	}

	public String getIndicadorContatoEquipeCampo() {
		return indicadorContatoEquipeCampo;
	}

	public void setIndicadorContatoEquipeCampo(
			String indicadorContatoEquipeCampo) {
		this.indicadorContatoEquipeCampo = indicadorContatoEquipeCampo;
	}

	public String getComentarioContatoEquipeCampo() {
		return comentarioContatoEquipeCampo;
	}

	public void setComentarioContatoEquipeCampo(
			String comentarioContatoEquipeCampo) {
		this.comentarioContatoEquipeCampo = comentarioContatoEquipeCampo;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getComentarioGeral() {
		return comentarioGeral;
	}

	public void setComentarioGeral(String comentarioGeral) {
		this.comentarioGeral = comentarioGeral;
	}

	public String getIdRegistroAtendimentoEncerrado() {
		return idRegistroAtendimentoEncerrado;
	}

	public void setIdRegistroAtendimentoEncerrado(
			String idRegistroAtendimentoEncerrado) {
		this.idRegistroAtendimentoEncerrado = idRegistroAtendimentoEncerrado;
	}

}
