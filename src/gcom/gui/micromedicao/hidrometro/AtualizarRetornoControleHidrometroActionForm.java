package gcom.gui.micromedicao.hidrometro;

import org.apache.struts.validator.ValidatorActionForm;


/**
 * [UC1032] ATUALIZAR RETORNO CONTROLE HIDROMETRO
 * 
 * @author Wallace Thierre
 * @date 03/08/2010
 */


public class AtualizarRetornoControleHidrometroActionForm extends ValidatorActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;	
	private String descricao;	
	private String indicadorGeracao; 	
	private String indicadorUso;	
	private String dataCorrente;
	
	
	public String getId() {
		return id;
	}
	

	public void setId(String id) {
		this.id = id;
	}

	
	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getIndicadorGeracao() {
		return indicadorGeracao;
	}

	
	public void setIndicadorGeracao(String indicadorGeracao) {
		this.indicadorGeracao = indicadorGeracao;
	}

	
	public String getIndicadorUso() {
		return indicadorUso;
	}

	
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	
	public String getDataCorrente(){
		return dataCorrente;
	}
	
	public void setDataCorrente(String data){
		this.dataCorrente = data;
	}	
	
}
