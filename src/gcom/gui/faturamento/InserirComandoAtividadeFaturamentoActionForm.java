package gcom.gui.faturamento;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InserirComandoAtividadeFaturamentoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String atividadeFaturamentoID;

    private String grupoFaturamentoID;

    private String referenciaFaturamento;

    private String vencimentoGrupo;

    private String[] idRotaSelecao;

    public String getAtividadeFaturamentoID() {
        return atividadeFaturamentoID;
    }

    public void setAtividadeFaturamentoID(String atividadeFaturamentoID) {
        this.atividadeFaturamentoID = atividadeFaturamentoID;
    }

    public String getGrupoFaturamentoID() {
        return grupoFaturamentoID;
    }

    public void setGrupoFaturamentoID(String grupoFaturamentoID) {
        this.grupoFaturamentoID = grupoFaturamentoID;
    }

    public String getReferenciaFaturamento() {
        return referenciaFaturamento;
    }

    public void setReferenciaFaturamento(String referenciaFaturamento) {
        this.referenciaFaturamento = referenciaFaturamento;
    }

    public String getVencimentoGrupo() {
        return vencimentoGrupo;
    }

    public void setVencimentoGrupo(String vencimentoGrupo) {
        this.vencimentoGrupo = vencimentoGrupo;
    }

    public String[] getIdRotaSelecao() {
        return idRotaSelecao;
    }

    public void setIdRotaSelecao(String[] idRotaSelecao) {
        this.idRotaSelecao = idRotaSelecao;
    }

    public ActionErrors validate(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
        /** @todo: finish this method, this is just the skeleton. */
        return null;
    }

    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    }
}
