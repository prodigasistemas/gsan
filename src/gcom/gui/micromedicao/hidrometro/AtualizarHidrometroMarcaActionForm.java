package gcom.gui.micromedicao.hidrometro;
import gcom.micromedicao.hidrometro.HidrometroMarca;

import java.util.Date;

import org.apache.struts.validator.ValidatorActionForm;


/**
 * [UC0081] ATUALIZAR MARCA HIDROMETRO	
 * 
 * @author Bruno Barros
 * @date 08/03/2007
 */


public class AtualizarHidrometroMarcaActionForm extends ValidatorActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idHidrometroMarca;
	
	private String descricaoMarcaHidrometro;
	
	private String descricaoAbreviada;
	
	private String validadeRevisao;
	
	private String marcaHidrometro;
	
	private String indicadorUso;	


	public String getIndicadorUso() {
		return indicadorUso;
	}



	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}



	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}



	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public HidrometroMarca setFormValues(HidrometroMarca hidrometroMarca) {
		
		
		// Metodo usado para setar todos os valores do Form na base de dados

		hidrometroMarca.setDescricao(getDescricaoMarcaHidrometro());		
		hidrometroMarca.setDescricaoAbreviada(getDescricaoAbreviada());
		
		if ( getValidadeRevisao() != null && !getValidadeRevisao().equals( "" ) ){		
		  hidrometroMarca.setIntervaloDiasRevisao( Short.parseShort( getValidadeRevisao() ) );
		}
		
		hidrometroMarca.setCodigoHidrometroMarca( getMarcaHidrometro() );
		
		hidrometroMarca.setUltimaAlteracao(new Date());
		hidrometroMarca.setIndicadorUso( new Short( getIndicadorUso() ) );
		
		return hidrometroMarca;
	}



	public String getDescricaoMarcaHidrometro() {
		return descricaoMarcaHidrometro;
	}



	public void setDescricaoMarcaHidrometro(String descricaoMarcaHidrometro) {
		this.descricaoMarcaHidrometro = descricaoMarcaHidrometro;
	}



	public String getMarcaHidrometro() {
		return marcaHidrometro;
	}



	public void setMarcaHidrometro(String marcaHidrometro) {
		this.marcaHidrometro = marcaHidrometro;
	}



	public String getValidadeRevisao() {
		return validadeRevisao;
	}



	public void setValidadeRevisao(String validadeRevisao) {
		this.validadeRevisao = validadeRevisao;
	}


	public String getIdHidrometroMarca() {
		return idHidrometroMarca;
	}



	public void setIdHidrometroMarca(String idHidrometroMarca) {
		this.idHidrometroMarca = idHidrometroMarca;
	}

}
