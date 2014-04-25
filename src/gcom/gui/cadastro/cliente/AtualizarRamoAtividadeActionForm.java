package gcom.gui.cadastro.cliente;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0596] Atualizar Ramo de Atividade
 * 
 * @author Fernando Fontelles Filho
 * @date 02/12/2009
 */

public class AtualizarRamoAtividadeActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String codigo;

	private String descricao;
	
	private Short indicadorUso;
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMesAno(Integer anoMes) {
		String anoMesStr = anoMes + "";
		String mesAno = "";

		mesAno = anoMesStr.substring(0, 4) + "/" + anoMesStr.substring(4, 6);

		return mesAno;
	}
	
}
