package gcom.gui.cadastro.localidade;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class LocalidadeActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String id;

    private String Descricao;

    private String numeroImovel;

    private String complementoEndereco;

    private String fone;

    private String ramalFone;

    private String fax;

    private String email;

    private String consumoGrandeUsuario;

    private String indicadorUso;

    /**
     * Retorna o valor de complementoEndereco
     * 
     * @return O valor de complementoEndereco
     */
    public String getComplementoEndereco() {
        return complementoEndereco;
    }

    /**
     * Seta o valor de complementoEndereco
     * 
     * @param complementoEndereco
     *            O novo valor de complementoEndereco
     */
    public void setComplementoEndereco(String complementoEndereco) {
        this.complementoEndereco = complementoEndereco;
    }

    /**
     * Retorna o valor de consumoGrandeusuario
     * 
     * @return O valor de consumoGrandeusuario
     */
    public String getConsumoGrandeUsuario() {
        return consumoGrandeUsuario;
    }

    /**
     * Seta o valor de consumoGrandeusuario
     * 
     * @param consumoGrandeusuario
     *            O novo valor de consumoGrandeusuario
     */
    public void setConsumoGrandeUsuario(String consumoGrandeUsuario) {
        this.consumoGrandeUsuario = consumoGrandeUsuario;
    }

    /**
     * Retorna o valor de descricao
     * 
     * @return O valor de descricao
     */
    public String getDescricao() {
        return Descricao;
    }

    /**
     * Seta o valor de descricao
     * 
     * @param Descricao
     *            O novo valor de descricao
     */
    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    /**
     * Retorna o valor de email
     * 
     * @return O valor de email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Seta o valor de email
     * 
     * @param email
     *            O novo valor de email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna o valor de fax
     * 
     * @return O valor de fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * Seta o valor de fax
     * 
     * @param fax
     *            O novo valor de fax
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * Retorna o valor de fone
     * 
     * @return O valor de fone
     */
    public String getFone() {
        return fone;
    }

    /**
     * Seta o valor de fone
     * 
     * @param fone
     *            O novo valor de fone
     */
    public void setFone(String fone) {
        this.fone = fone;
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
     * Retorna o valor de numeroImovel
     * 
     * @return O valor de numeroImovel
     */
    public String getNumeroImovel() {
        return numeroImovel;
    }

    /**
     * Seta o valor de numeroImovel
     * 
     * @param numeroImovel
     *            O novo valor de numeroImovel
     */
    public void setNumeroImovel(String numeroImovel) {
        this.numeroImovel = numeroImovel;
    }

    /**
     * Retorna o valor de ramalFone
     * 
     * @return O valor de ramalFone
     */
    public String getRamalFone() {
        return ramalFone;
    }

    /**
     * Seta o valor de ramalFone
     * 
     * @param ramalFone
     *            O novo valor de ramalFone
     */
    public void setRamalFone(String ramalFone) {
        this.ramalFone = ramalFone;
    }

}
