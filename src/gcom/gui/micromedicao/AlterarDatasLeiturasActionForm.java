package gcom.gui.micromedicao;

import org.apache.struts.action.ActionForm;

/**
 * 
 * Action Form da tela de filtro do alterar datas leituras 
 *
 * @author bruno
 * @date 26/02/2009
 */
public class AlterarDatasLeiturasActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
    
    private String idGrupo;
    private String[] arrDtAnterior;
    private String[] arrDtAtual;
    
    public String[] getArrDtAtual() {
        return arrDtAtual;
    }
    
    public void setArrDtAtual(String[] arrDtFinal) {
        this.arrDtAtual = arrDtFinal;
    }
    
    public String[] getArrDtAnterior() {
        return arrDtAnterior;
    }
    
    public void setArrDtAnterior(String[] arrDtInicial) {
        this.arrDtAnterior = arrDtInicial;
    }
    
    public String getIdGrupo() {
        return idGrupo;
    }
    
    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }
}
