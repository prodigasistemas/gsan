package gcom.gui.cadastro.tarifasocial;

import java.util.Collection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class 
 *  asda
 * @author thiago toscvano 
 * @created 28 de Junho de 2004
 */ 
public class ExibirDadosTarifaSocialActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idTarifaSocialDadoEconomia = null;
	private String tipoCartao = null;
	private String rendaTipo = null;
	private String areaConstruida = null;
	
	private Collection collTipoCartao = null;
	private Collection collRendaTipo = null;
	private Collection collAreaConstruida = null;

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
    	this.idTarifaSocialDadoEconomia = "";
    	this.collTipoCartao = new Vector();
    	this.collRendaTipo = new Vector();
    	this.collAreaConstruida = new Vector();
    	
    	this.tipoCartao = "";
    	this.rendaTipo = "";
    	this.areaConstruida = "";
    	
    }

	public String getAreaConstruida() {
		return areaConstruida;
	}

	public void setAreaConstruida(String areaConstruida) {
		this.areaConstruida = areaConstruida;
	}

	public String getRendaTipo() {
		return rendaTipo;
	}

	public void setRendaTipo(String rendaTipo) {
		this.rendaTipo = rendaTipo;
	}

	public String getTipoCartao() {
		return tipoCartao;
	}

	public void setTipoCartao(String tipoCartao) {
		this.tipoCartao = tipoCartao;
	}

	public Collection getCollAreaConstruida() {
		return collAreaConstruida;
	}

	public void setCollAreaConstruida(Collection collAreaConstruida) {
		this.collAreaConstruida = collAreaConstruida;
	}

	public String getIdTarifaSocialDadoEconomia() {
		return idTarifaSocialDadoEconomia;
	}

	public Collection getCollRendaTipo() {
		return collRendaTipo;
	}

	public void setCollRendaTipo(Collection collRendaTipo) {
		this.collRendaTipo = collRendaTipo;
	}

	public void setIdTarifaSocialDadoEconomia(String idTarifaSocialDadoEconomia) {
		this.idTarifaSocialDadoEconomia = idTarifaSocialDadoEconomia;
	}

	public Collection getCollTipoCartao() {
		return collTipoCartao;
	}

	public void setCollTipoCartao(Collection collTipoCartao) {
		this.collTipoCartao = collTipoCartao;
	}
}
