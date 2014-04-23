package gcom.gui.cadastro.imovel;

import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Fernanda Karla
 * @created 29 de Dezembro de 2005
 */
public class InserirSubcategoriaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String categoria;

    private String descricaoSubcategoria;
    
    private String codigoSubcategoria;

    private String indicadorUso;
    
    private String descricaoAbreviada;
    
    private String codigoTarifaSocial;
    
    private String codigoGrupoSubcategoria;
    
    private String numeroFatorFiscalizacao;
    
    private String indicadorTarifaConsumo;

    private String indicadorSazonalidade;
    
	/**
	 * @return Returns the consumoAlto.
	 */
	public String getCategoria() {
		return categoria;
	}



	/**
	 * @param consumoAlto The consumoAlto to set.
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	/**
	 * @return Returns the consumoMinimo.
	 */
	public String getCodigoSubcategoria() {
		return codigoSubcategoria;
	}



	/**
	 * @param consumoMinimo The consumoMinimo to set.
	 */
	public void setCodigoSubcategoria(String codigoSubcategoria) {
		this.codigoSubcategoria = codigoSubcategoria;
	}


	/**
	 * @return Returns the consumoEstouro.
	 */
	public String getDescricaoSubcategoria() {
		return descricaoSubcategoria;
	}



	/**
	 * @param consumoEstouro The consumoEstouro to set.
	 */
	public void setDescricaoSubcategoria(String descricaoSubcategoria) {
		this.descricaoSubcategoria = descricaoSubcategoria;
	}


	/**
	 * @return Returns the indicadorUso.
	 */
	public String getIndicadorUso() {
		return indicadorUso;
	}



	/**
	 * @param indicadorUso The indicadorUso to set.
	 */
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

		
    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     */
    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    	
    	codigoSubcategoria = null;
        descricaoSubcategoria = null;
        indicadorUso = ConstantesSistema.INDICADOR_USO_ATIVO.toString();
        
    }

	public String getIndicadorSazonalidade() {
		return indicadorSazonalidade;
	}

	public void setIndicadorSazonalidade(String indicadorSazonalidade) {
		
		this.indicadorSazonalidade = indicadorSazonalidade;
	}



	public String getCodigoGrupoSubcategoria() {
		return codigoGrupoSubcategoria;
	}



	public void setCodigoGrupoSubcategoria(String codigoGrupoSubcategoria) {
		this.codigoGrupoSubcategoria = codigoGrupoSubcategoria;
	}



	public String getCodigoTarifaSocial() {
		return codigoTarifaSocial;
	}



	public void setCodigoTarifaSocial(String codigoTarifaSocial) {
		this.codigoTarifaSocial = codigoTarifaSocial;
	}



	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}



	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}



	public String getIndicadorTarifaConsumo() {
		return indicadorTarifaConsumo;
	}



	public void setIndicadorTarifaConsumo(String indicadorTarifaConsumo) {
		this.indicadorTarifaConsumo = indicadorTarifaConsumo;
	}



	public String getNumeroFatorFiscalizacao() {
		return numeroFatorFiscalizacao;
	}



	public void setNumeroFatorFiscalizacao(String numeroFatorFiscalizacao) {
		this.numeroFatorFiscalizacao = numeroFatorFiscalizacao;
	}
	
	
}
