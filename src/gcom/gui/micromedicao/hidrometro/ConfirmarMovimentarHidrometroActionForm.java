package gcom.gui.micromedicao.hidrometro;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ConfirmarMovimentarHidrometroActionForm extends
        ValidatorActionForm {
	private static final long serialVersionUID = 1L;
    private String dataMovimentacao;

    private String localArmazenagemDescricaoAtual;

    private String localArmazenagemDescricaoDestino;

    private String horaMovimentacao;

    private String idLocalArmazenagemDestino;

    private String idMotivoMovimentacao;

    private String parecer;

    /**
     * Retorna o valor de dataMovimentacao
     * 
     * @return O valor de dataMovimentacao
     */
    public String getDataMovimentacao() {
        return dataMovimentacao;
    }

    /**
     * Seta o valor de dataMovimentacao
     * 
     * @param dataMovimentacao
     *            O novo valor de dataMovimentacao
     */
    public void setDataMovimentacao(String dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    /**
     * Retorna o valor de horaMovimentacao
     * 
     * @return O valor de horaMovimentacao
     */
    public String getHoraMovimentacao() {
        return horaMovimentacao;
    }

    /**
     * Seta o valor de horaMovimentacao
     * 
     * @param horaMovimentacao
     *            O novo valor de horaMovimentacao
     */
    public void setHoraMovimentacao(String horaMovimentacao) {
        this.horaMovimentacao = horaMovimentacao;
    }

    /**
     * Retorna o valor de idLocalArmazenagemDestino
     * 
     * @return O valor de idLocalArmazenagemDestino
     */
    public String getIdLocalArmazenagemDestino() {
        return idLocalArmazenagemDestino;
    }

    /**
     * Seta o valor de idLocalArmazenagemDestino
     * 
     * @param idLocalArmazenagemDestino
     *            O novo valor de idLocalArmazenagemDestino
     */
    public void setIdLocalArmazenagemDestino(String idLocalArmazenagemDestino) {
        this.idLocalArmazenagemDestino = idLocalArmazenagemDestino;
    }

    /**
     * Retorna o valor de idMotivoMovimentacao
     * 
     * @return O valor de idMotivoMovimentacao
     */
    public String getIdMotivoMovimentacao() {
        return idMotivoMovimentacao;
    }

    /**
     * Seta o valor de idMotivoMovimentacao
     * 
     * @param idMotivoMovimentacao
     *            O novo valor de idMotivoMovimentacao
     */
    public void setIdMotivoMovimentacao(String idMotivoMovimentacao) {
        this.idMotivoMovimentacao = idMotivoMovimentacao;
    }

    /**
     * Retorna o valor de parecer
     * 
     * @return O valor de parecer
     */
    public String getParecer() {
        return parecer;
    }

    /**
     * Seta o valor de parecer
     * 
     * @param parecer
     *            O novo valor de parecer
     */
    public void setParecer(String parecer) {
        this.parecer = parecer;
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
        dataMovimentacao = null;
        localArmazenagemDescricaoAtual = null;
        localArmazenagemDescricaoDestino = null;
        horaMovimentacao = null;
        idLocalArmazenagemDestino = null;
        idMotivoMovimentacao = null;
        parecer = null;

    }

    /**
     * Retorna o valor de localArmazenagemDescricaoDestino
     * 
     * @return O valor de localArmazenagemDescricaoDestino
     */
    public String getLocalArmazenagemDescricaoDestino() {
        return localArmazenagemDescricaoDestino;
    }

    /**
     * Retorna o valor de localArmazenagemDescricaoAtual
     * 
     * @return O valor de localArmazenagemDescricaoAtual
     */
    public String getLocalArmazenagemDescricaoAtual() {
        return localArmazenagemDescricaoAtual;
    }

    /**
     * Seta o valor de localArmazenagemDescricaoAtual
     * 
     * @param localArmazenagemDescricaoAtual
     *            O novo valor de localArmazenagemDescricaoAtual
     */
    public void setLocalArmazenagemDescricaoAtual(
            String localArmazenagemDescricaoAtual) {
        this.localArmazenagemDescricaoAtual = localArmazenagemDescricaoAtual;
    }

    /**
     * Seta o valor de localArmazenagemDescricaoDestino
     * 
     * @param localArmazenagemDescricaoDestino
     *            O novo valor de localArmazenagemDescricaoDestino
     */
    public void setLocalArmazenagemDescricaoDestino(
            String localArmazenagemDescricaoDestino) {
        this.localArmazenagemDescricaoDestino = localArmazenagemDescricaoDestino;
    }
}
