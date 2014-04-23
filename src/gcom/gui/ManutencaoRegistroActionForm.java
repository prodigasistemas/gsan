package gcom.gui;

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
    
    private String idRegistroAtualizacao_segunda;

    private String[] idRegistrosRemocao_segunda;
    
    private String descricao;

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

	public String getIdRegistroAtualizacao_segunda() {
		return idRegistroAtualizacao_segunda;
	}

	public void setIdRegistroAtualizacao_segunda(
			String idRegistroAtualizacao_segunda) {
		this.idRegistroAtualizacao_segunda = idRegistroAtualizacao_segunda;
	}

	public String[] getIdRegistrosRemocao_segunda() {
		return idRegistrosRemocao_segunda;
	}

	public void setIdRegistrosRemocao_segunda(String[] idRegistrosRemocao_segunda) {
		this.idRegistrosRemocao_segunda = idRegistrosRemocao_segunda;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
