package gcom.gui.cadastro;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Arthur Carvalho
 * @date 11/08/2008
 */
public class InserirFonteAbastecimentoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	String descricao;
	
	String descricaoAbreviada;
	
	Short indicadorCalcularVolumeFixo;
	
	Short indicadorPermitePoco;
	
	Short indicadorUso;
	
	public Short gerIndicadorUso(){
		return indicadorUso;
	}
	
	public void setIndicadorUso(Short indicadorUso){
		this.indicadorUso = indicadorUso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}


	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public Short getIndicadorCalcularVolumeFixo() {
		return indicadorCalcularVolumeFixo;
	}

	public void setIndicadorCalcularVolumeFixo(Short indicadorCalcularVolumeFixo) {
		this.indicadorCalcularVolumeFixo = indicadorCalcularVolumeFixo;
	}

	public Short getIndicadorPermitePoco() {
		return indicadorPermitePoco;
	}

	public void setIndicadorPermitePoco(Short indicadorPermitePoco) {
		this.indicadorPermitePoco = indicadorPermitePoco;
	}
	
}
