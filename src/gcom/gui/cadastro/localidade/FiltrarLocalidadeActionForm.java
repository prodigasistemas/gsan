package gcom.gui.cadastro.localidade;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class FiltrarLocalidadeActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String indicadorAtualizar;
	
	private String gerenciaID;
	
	private String idUnidadeNegocio;

    private String indicadorUso;

    private String localidadeID;

    private String localidadeNome;
    
    private String tipoPesquisa;

    private String[] localidadeSelectID;
    
    private String ordernarPor;
    
    public String[] getLocalidadeSelectID() {
		return localidadeSelectID;
	}

	public void setLocalidadeSelectID(String[] localidadeSelectID) {
		this.localidadeSelectID = localidadeSelectID;
	}

	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
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

	public ActionErrors validate(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
        /** @todo: finish this method, this is just the skeleton. */
        return null;
    }

    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    }

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	/**
	 * @return Retorna o campo idUnidadeNegocio.
	 */
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	/**
	 * @param idUnidadeNegocio O idUnidadeNegocio a ser setado.
	 */
	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo gerenciaID.
	 */
	public String getGerenciaID() {
		return gerenciaID;
	}

	/**
	 * @param gerenciaID O gerenciaID a ser setado.
	 */
	public void setGerenciaID(String gerenciaID) {
		this.gerenciaID = gerenciaID;
	}

	public String getOrdernarPor() {
		return ordernarPor;
	}

	public void setOrdernarPor(String ordernarPor) {
		this.ordernarPor = ordernarPor;
	}
	

}
