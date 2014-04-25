package gcom.gui.micromedicao;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form utilizado no Inserir Rota e no Atualizar Rota
 * 
 * @author Vivianne Sousa
 * @created 21/03/2006
 */
public class AdicionarCriterioCobrancaRotaActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String cobrancaAcao;
	private String idCobrancaCriterio;
	private String descricaoCobrancaCriterio;
	
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
    	
    	cobrancaAcao = null;
    	idCobrancaCriterio = null;
    	descricaoCobrancaCriterio = null;
    }


	public String getCobrancaAcao() {
		return cobrancaAcao;
	}


	public void setCobrancaAcao(String cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}


	public String getDescricaoCobrancaCriterio() {
		return descricaoCobrancaCriterio;
	}

	public void setDescricaoCobrancaCriterio(String descricaoCobrancaCriterio) {
		this.descricaoCobrancaCriterio = descricaoCobrancaCriterio;
	}

	public String getIdCobrancaCriterio() {
		return idCobrancaCriterio;
	}

	public void setIdCobrancaCriterio(String idCobrancaCriterio) {
		this.idCobrancaCriterio = idCobrancaCriterio;
	}

	
	}
