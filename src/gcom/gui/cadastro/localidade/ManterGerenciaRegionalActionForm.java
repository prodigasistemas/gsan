package gcom.gui.cadastro.localidade;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ManterGerenciaRegionalActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String indicadorAtualizar;
	
	private String gerenciaID;
		
    private String indicadorUso;

    private String localidadeID;
    
    private String gerenciaRegionalNomeAbre;

    private String gerenciaRegionalNome;
    
    private String tipoPesquisa;

    private String[] localidadeSelectID;
    
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


}
