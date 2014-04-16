package gcom.gui.micromedicao.hidrometro;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.util.ConstantesSistema;

import java.util.Date;

import org.apache.struts.validator.ValidatorActionForm;


/**
 * [UC0524]	INSERIR SISTEMA ESGOTO
 * 
 * @author Kássia Albuquerque
 * @date 08/03/2007
 */


public class InserirHidrometroMarcaActionForm extends ValidatorActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String descricaoMarcaHidrometro;
	
	private String descricaoAbreviada;
	
	private String validadeRevisao;
	
	private String marcaHidrometro;
	


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
		hidrometroMarca.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		
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

}
