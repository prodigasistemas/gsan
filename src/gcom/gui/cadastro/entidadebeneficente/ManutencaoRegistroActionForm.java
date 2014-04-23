package gcom.gui.cadastro.entidadebeneficente;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ManutencaoRegistroActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String idRegistroAtualizacao;

    private String[] idRegistrosRemocao;
    
    private String[] idRegistrosAutorizar;

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
        //idRegistroAtualizacao = null;
    }

    /**
     * Retorna o valor de idRegistroAtualizacao
     * 
     * @return O valor de idRegistroAtualizacao
     */
    public String getIdRegistroAtualizacao() {
        return idRegistroAtualizacao;
    }

    /**
     * Seta o valor de idRegistroAtualizacao
     * 
     * @param idRegistroAtualizacao
     *            O novo valor de idRegistroAtualizacao
     */
    public void setIdRegistroAtualizacao(String idRegistroAtualizacao) {
        this.idRegistroAtualizacao = idRegistroAtualizacao;
    }

    /**
     * Retorna o valor de idRegistrosRemocao
     * 
     * @return O valor de idRegistrosRemocao
     */
    public String[] getIdRegistrosRemocao() {
        return idRegistrosRemocao;
    }

    /**
     * Seta o valor de idRegistrosRemocao
     * 
     * @param idRegistrosRemocao
     *            O novo valor de idRegistrosRemocao
     */
    public void setIdRegistrosRemocao(String[] idRegistrosRemocao) {
        this.idRegistrosRemocao = idRegistrosRemocao;
    }
    
    /**
     * Retorna o valor de idRegistrosRemocao
     * 
     * @return O valor de idRegistrosRemocao
     */
    public String[] getIdRegistrosAutorizar() {
        return idRegistrosAutorizar;
    }

    /**
     * Seta o valor de idRegistrosRemocao
     * 
     * @param idRegistrosRemocao
     *            O novo valor de idRegistrosRemocao
     */
    public void setIdRegistrosAutorizar(String[] idRegistrosAutorizar) {
        this.idRegistrosAutorizar = idRegistrosAutorizar;
    }

}
