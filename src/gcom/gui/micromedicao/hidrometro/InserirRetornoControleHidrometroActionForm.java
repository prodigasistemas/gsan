package gcom.gui.micromedicao.hidrometro;

import java.util.Date;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1031] INSERIR RETORNO CONTROLE HIDROMETRO FORM
 * 
 * @author Wallace Thierre
 * @date 29/07/2010
 */

public class InserirRetornoControleHidrometroActionForm extends ValidatorActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String descricao;
	
	private Short indicadorGeracao;
	
	private Date datacorrente;
	
	private Short indicadorUso = 1;

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

	public Short getIndicadorGeracao() {
		return indicadorGeracao;
	}

	public void setIndicadorGeracao(Short indicadorGeracao) {
		this.indicadorGeracao = indicadorGeracao;
	}

	public Date getDatacorrente() {
		return datacorrente;
	}

	public void setDatacorrente(Date datacorrente) {
		this.datacorrente = datacorrente;
	}
	
		
}
