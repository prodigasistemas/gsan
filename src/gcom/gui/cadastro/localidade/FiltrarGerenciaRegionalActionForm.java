package gcom.gui.cadastro.localidade;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class FiltrarGerenciaRegionalActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String indicadorAtualizar;
	
	private String gerenciaRegionalID;
		
    private String indicadorUso;

    private String localidadeID;
    
	private String idUnidadeNegocio;
    
    private String gerenciaRegionalNomeAbre;

    private String gerenciaRegionalNome;
    
    private String tipoPesquisa;

    private String[] localidadeSelectID;
    
	private String atualizar;
	
	private String cnpjGerenciaRegional;
	
    
    public String getAtualizar() {
		return atualizar;
	}

	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
	}

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

	public String getGerenciaRegionalNome() {
		return gerenciaRegionalNome;
	}

	public void setGerenciaRegionalNome(String gerenciaRegionalNome) {
		this.gerenciaRegionalNome = gerenciaRegionalNome;
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
	 * @return Retorna o campo gerenciaRegionalID.
	 */
	public String getGerenciaRegionalID() {
		return gerenciaRegionalID;
	}

	/**
	 * @param gerenciaID O gerenciaID a ser setado.
	 */
	public void setGerenciaRegionalID(String gerenciaRegionalID) {
		this.gerenciaRegionalID = gerenciaRegionalID;
	}

	/**
	 * @return Retorna o campo gerenciaRegionalNomeAbre.
	 */
	public String getGerenciaRegionalNomeAbre() {
		return gerenciaRegionalNomeAbre;
	}

	/**
	 * @param gerenciaRegionalNomeAbre O gerenciaRegionalNomeAbre a ser setado.
	 */
	public void setGerenciaRegionalNomeAbre(String gerenciaRegionalNomeAbre) {
		this.gerenciaRegionalNomeAbre = gerenciaRegionalNomeAbre;
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
	 * @param cnpjGerenciaRegional O cnpjGerenciaRegional a ser setado.
	 */
	public String getCnpjGerenciaRegional() {
		return cnpjGerenciaRegional;
	}

	/**
	 * @param cnpjGerenciaRegional O cnpjGerenciaRegional a ser setado.
	 */
	public void setCnpjGerenciaRegional(String cnpjGerenciaRegional) {
		this.cnpjGerenciaRegional = cnpjGerenciaRegional;
	}


	
}
