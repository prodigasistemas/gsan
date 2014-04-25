package gcom.gui.cadastro.localidade;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PesquisarAtualizarLocalidadeActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String classeID;

    private String eloID;

    private String email;

    private String fax;

    private String gerenciaID;

    private String indicadorUso;

    private String localidadeID;

    private String localidadeNome;

    private String menorConsumo;

    private String porteID;

    private String ramal;

    private String telefone;

    private String[] localidadeSelectID;

    public String getClasseID() {
        return classeID;
    }

    public void setClasseID(String classeID) {
        this.classeID = classeID;
    }

    public String getEloID() {
        return eloID;
    }

    public void setEloID(String eloID) {
        this.eloID = eloID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getGerenciaID() {
        return gerenciaID;
    }

    public void setGerenciaID(String gerenciaID) {
        this.gerenciaID = gerenciaID;
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

    public String getMenorConsumo() {
        return menorConsumo;
    }

    public void setMenorConsumo(String menorConsumo) {
        this.menorConsumo = menorConsumo;
    }

    public String getPorteID() {
        return porteID;
    }

    public void setPorteID(String porteID) {
        this.porteID = porteID;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public ActionErrors validate(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
        /** @todo: finish this method, this is just the skeleton. */
        return null;
    }

    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    }

    public String[] getLocalidadeSelectID() {
        return localidadeSelectID;
    }

    public void setLocalidadeSelectID(String[] localidadeSelectID) {
        this.localidadeSelectID = localidadeSelectID;
    }
}
