package gcom.seguranca.acesso;

import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;


/**
 * Descrição da classe 
 *
 * @author Pedro Alexandre
 * @date 08/11/2006
 */
public class Abrangencia implements Serializable {
    
	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private Imovel imovel;
    private Localidade localidade;
    private Localidade eloPolo;
    private UnidadeNegocio unidadeNegocio;
    private GerenciaRegional gerenciaRegional;
    private Municipio municipio;
    private SetorComercial setorComercial;
    private Quadra quadra;
    
    public final static String ABRANGENCIA = "abrangencia";
    
    
    /**
     * Construtor de Abrangencia 
     * 
     * @param usuario
     * @param imovel
     */
    public Abrangencia(Usuario usuario, Imovel imovel) {
        this.usuario = usuario;
        this.imovel = imovel;
    }

    /**
     * Construtor de Abrangencia 
     * 
     * @param usuario
     * @param localidade
     */
    public Abrangencia(Usuario usuario, Localidade localidade) {
        this.usuario = usuario;
        this.localidade = localidade;
    }
    
    /**
     * Construtor de Abrangencia 
     * 
     * @param eloPolo
     * @param usuario
     */
    public Abrangencia(Localidade eloPolo, Usuario usuario) {
        this.usuario = usuario;
        this.eloPolo = eloPolo;
    }
    
    /**
     * Construtor de Abrangencia 
     * 
     * @param usuario
     * @param unidadeNegocio
     */
    public Abrangencia(Usuario usuario, UnidadeNegocio unidadeNegocio) {
        this.usuario = usuario;
        this.unidadeNegocio = unidadeNegocio;
    }
    
    /**
     * Construtor de Abrangencia 
     * 
     * @param usuario
     * @param gerenciaRegional
     */
    public Abrangencia(Usuario usuario, GerenciaRegional gerenciaRegional) {
        this.usuario = usuario;
        this.gerenciaRegional = gerenciaRegional;
    }

    /**
     * Construtor de Abrangencia 
     * 
     * @param usuario
     * @param municipio
     */
    public Abrangencia(Usuario usuario, Municipio municipio) {
        this.usuario = usuario;
        this.municipio = municipio;
    }

    /**
     * Construtor de Abrangencia 
     * 
     * @param usuario
     * @param setorComercial
     */
    public Abrangencia(Usuario usuario, SetorComercial setorComercial) {
        this.usuario = usuario;
        this.setorComercial = setorComercial;
    }
    
    /**
     * Construtor de Abrangencia 
     * 
     * @param usuario
     * @param quadra
     */
    public Abrangencia(Usuario usuario, Quadra quadra) {
        this.usuario = usuario;
        this.quadra = quadra;
    }

    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 13/11/2006
     *
     * @return
     */
    public Localidade getEloPolo() {
        return eloPolo;
    }


    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 13/11/2006
     *
     * @param eloPolo
     */
    protected void setEloPolo(Localidade eloPolo) {
        this.eloPolo = eloPolo;
    }


    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 13/11/2006
     *
     * @return
     */
    public GerenciaRegional getGerenciaRegional() {
        return gerenciaRegional;
    }


    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @param gerenciaRegional
     */
    protected void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
        this.gerenciaRegional = gerenciaRegional;
    }


    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @return
     */
    public Imovel getImovel() {
        return imovel;
    }


    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @param imovel
     */
    protected void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }


    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @return
     */
    public Localidade getLocalidade() {
        return localidade;
    }


    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @param localidade
     */
    protected void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }


    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @return
     */
    public UnidadeNegocio getUnidadeNegocio() {
        return unidadeNegocio;
    }


    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @param unidadeNegocio
     */
    protected void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
        this.unidadeNegocio = unidadeNegocio;
    }


    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @return
     */
    public Usuario getUsuario() {
        return usuario;
    }


    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @param usuario
     */
    protected void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 14/11/2006
     *
     * @return
     */
    public Municipio getMunicipio() {
        return municipio;
    }

    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 14/11/2006
     *
     * @param municipio
     */
    protected void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 14/11/2006
     *
     * @return
     */
    public Quadra getQuadra() {
        return quadra;
    }

    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 14/11/2006
     *
     * @param quadra
     */
    protected void setQuadra(Quadra quadra) {
        this.quadra = quadra;
    }

    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 14/11/2006
     *
     * @return
     */
    public SetorComercial getSetorComercial() {
        return setorComercial;
    }

    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 14/11/2006
     *
     * @param setorComercial
     */
    protected void setSetorComercial(SetorComercial setorComercial) {
        this.setorComercial = setorComercial;
    }
}    
  
