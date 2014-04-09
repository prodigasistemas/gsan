package gcom.gui.cadastro.imovel;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
/**
 * Description of the Class
 * 
 * @author Fernanda Karla
 * @created 29 de Dezembro de 2005
 */
public class FiltrarSubcategoriaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String idCategoria;
	
	
	private String codigoSubcategoria;
	
	private String idSubcategoria;
	
    private String descricao;
    
    private String descricaoSubcategoria;

    private String indicadorUso;
    
    private String descricaoAbreviada;
    
    private String codigoTarifaSocial;
    
    private String codigoGrupoSubcategoria;
    
    private String numeroFatorFiscalizacao;
    
    private String indicadorTarifaConsumo;

    private String indicadorSazonalidade;

		
    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     */

    public String getCodigoSubcategoria() {
		return codigoSubcategoria;
	}


	public void setCodigoSubcategoria(String codigoSubcategoria) {
		this.codigoSubcategoria = codigoSubcategoria;
	}
    
    public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getIdCategoria() {
		return idCategoria;
	}


	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescricaoSubcategoria() {
		return descricaoSubcategoria;
	}

	public void setDescricaoSubcategoria(String descricaoSubcategoria) {
		this.descricaoSubcategoria = descricaoSubcategoria;
	}
	
	public String getIndicadorUso() {
		return indicadorUso;
	}


	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getIdSubcategoria() {
		return idSubcategoria;
	}


	public void setIdSubcategoria(String idSubcategoria) {
		this.idSubcategoria = idSubcategoria;
	}
	
	public ActionErrors validate(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
        /** @todo: finish this method, this is just the skeleton. */
        return null;
    }

    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
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


	public String getIndicadorSazonalidade() {
		return indicadorSazonalidade;
	}


	public void setIndicadorSazonalidade(String indicadorSazonalidade) {
		this.indicadorSazonalidade = indicadorSazonalidade;
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
