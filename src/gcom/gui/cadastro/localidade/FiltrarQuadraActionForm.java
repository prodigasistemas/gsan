package gcom.gui.cadastro.localidade;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class FiltrarQuadraActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String localidadeID;

    private String localidadeNome;

    private String[] quadraID;

    private String quadraMensagem;

    private String quadraNM;

    private String setorComercialCD;

    private String setorComercialID;

    private String setorComercialNome;
    
    private String idRota;
    
    private String codigoRota;

    private String indicadorUso;
    
    private String tipoPesquisa;

    public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getLocalidadeID() {
        return localidadeID;
    }

    public void setLocalidadeID(String localidadeID) {
        this.localidadeID = localidadeID;
    }

    public String getLocalidadeNome() {
        return localidadeNome;
    }

    public void setLocalidadeNome(String localidadeNome) {
        this.localidadeNome = localidadeNome;
    }

    public String[] getQuadraID() {
        return quadraID;
    }

    public void setQuadraID(String[] quadraID) {
        this.quadraID = quadraID;
    }

    public String getQuadraMensagem() {
        return quadraMensagem;
    }

    public void setQuadraMensagem(String quadraMensagem) {
        this.quadraMensagem = quadraMensagem;
    }

    public String getQuadraNM() {
        return quadraNM;
    }

    public void setQuadraNM(String quadraNM) {
        this.quadraNM = quadraNM;
    }

    public String getSetorComercialCD() {
        return setorComercialCD;
    }

    public void setSetorComercialCD(String setorComercialCD) {
        this.setorComercialCD = setorComercialCD;
    }

    public String getSetorComercialID() {
        return setorComercialID;
    }

    public void setSetorComercialID(String setorComercialID) {
        this.setorComercialID = setorComercialID;
    }

    public String getSetorComercialNome() {
        return setorComercialNome;
    }

    public void setSetorComercialNome(String setorComercialNome) {
        this.setorComercialNome = setorComercialNome;
    }

    public ActionErrors validate(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
        /** @todo: finish this method, this is just the skeleton. */
        return null;
    }

    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    }

    public String getIndicadorUso() {
        return indicadorUso;
    }

    public void setIndicadorUso(String indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

	/**
	 * @return Retorna o campo codigoRota.
	 */
	public String getCodigoRota() {
		return codigoRota;
	}

	/**
	 * @param codigoRota O codigoRota a ser setado.
	 */
	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}

	/**
	 * @return Retorna o campo idRota.
	 */
	public String getIdRota() {
		return idRota;
	}

	/**
	 * @param idRota O idRota a ser setado.
	 */
	public void setIdRota(String idRota) {
		this.idRota = idRota;
	}

}
