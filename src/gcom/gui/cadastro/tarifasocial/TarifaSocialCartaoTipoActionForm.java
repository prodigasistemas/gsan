package gcom.gui.cadastro.tarifasocial;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class TarifaSocialCartaoTipoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String id;

    private String descricao;

    private String descricaoAbreviada;

    private String numeroMaximoMeses;

    private String validade;

    private String indicadorUso;

    /**
     * Retorna o valor de descricao
     * 
     * @return O valor de descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Seta o valor de descricao
     * 
     * @param descricao
     *            O novo valor de descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna o valor de descricaoAbreviada
     * 
     * @return O valor de descricaoAbreviada
     */
    public String getDescricaoAbreviada() {
        return descricaoAbreviada;
    }

    /**
     * Seta o valor de descricaoAbreviada
     * 
     * @param descricaoAbreviada
     *            O novo valor de descricaoAbreviada
     */
    public void setDescricaoAbreviada(String descricaoAbreviada) {
        this.descricaoAbreviada = descricaoAbreviada;
    }

    /**
     * Retorna o valor de numeroMaximoMeses
     * 
     * @return O valor de numeroMaximoMeses
     */
    public String getNumeroMaximoMeses() {
        return numeroMaximoMeses;
    }

    /**
     * Seta o valor de numeroMaximoMeses
     * 
     * @param numeroMaximoMeses
     *            O novo valor de numeroMaximoMeses
     */
    public void setNumeroMaximoMeses(String numeroMaximoMeses) {
        this.numeroMaximoMeses = numeroMaximoMeses;
    }

    /**
     * Retorna o valor de validade
     * 
     * @return O valor de validade
     */
    public String getValidade() {
        return validade;
    }

    /**
     * Seta o valor de validade
     * 
     * @param validade
     *            O novo valor de validade
     */
    public void setValidade(String validade) {
        this.validade = validade;
    }

    /**
     * Retorna o valor de indicadorUso
     * 
     * @return O valor de indicadorUso
     */
    public String getIndicadorUso() {
        return indicadorUso;
    }

    /**
     * Seta o valor de indicadorUso
     * 
     * @param indicadorUso
     *            O novo valor de indicadorUso
     */
    public void setIndicadorUso(String indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    /**
     * Retorna o valor de id
     * 
     * @return O valor de id
     */
    public String getId() {
        return id;
    }

    /**
     * Seta o valor de id
     * 
     * @param id
     *            O novo valor de id
     */
    public void setId(String id) {
        this.id = id;
    }

}
