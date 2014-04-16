package gcom.gui.micromedicao;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ConsistirLeiturasCalcularConsumosActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String idFaturamentoGrupo;

    /**
     * Seta o valor de idFaturamentoGrupo
     * 
     * @param idFaturamentoGrupo
     *            O novo valor de idFaturamentoGrupo
     */
    public void setIdFaturamentoGrupo(String idFaturamentoGrupo) {
        this.idFaturamentoGrupo = idFaturamentoGrupo;
    }

    /**
     * Retorna o valor de idFaturamentoGrupo
     * 
     * @return O valor de idFaturamentoGrupo
     */
    public String getIdFaturamentoGrupo() {
        return idFaturamentoGrupo;
    }

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionErrors validate(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {

        return null;
    }

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     */
    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    }

}
