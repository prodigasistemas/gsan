package gcom.atendimentopublico.registroatendimento;

import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Tramite extends ObjetoTransacao implements Serializable {

	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private String parecerTramite;

    /** persistent field */
    private Date dataTramite;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Usuario usuarioResponsavel;

    /** persistent field */
    private Usuario usuarioRegistro;

    /** persistent field */
    private gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimento;

    /** persistent field */
    private UnidadeOrganizacional unidadeOrganizacionalOrigem;
    
    /** persistent field */
    private UnidadeOrganizacional unidadeOrganizacionalDestino;
    

    /** full constructor */
    public Tramite(String parecerTramite, Date dataTramite, Date ultimaAlteracao, Usuario usuarioResponsavel, Usuario usuarioRegistro, gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimento, UnidadeOrganizacional unidadeOrganizacionalOrigem, UnidadeOrganizacional unidadeOrganizacionalDestino) {
        this.parecerTramite = parecerTramite;
        this.dataTramite = dataTramite;
        this.ultimaAlteracao = ultimaAlteracao;
        this.usuarioResponsavel = usuarioResponsavel;
        this.usuarioRegistro = usuarioRegistro;
        this.registroAtendimento = registroAtendimento;
        this.unidadeOrganizacionalOrigem = unidadeOrganizacionalOrigem;
        this.unidadeOrganizacionalDestino = unidadeOrganizacionalDestino;
    }

    /** default constructor */
    public Tramite() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParecerTramite() {
        return this.parecerTramite;
    }

    public void setParecerTramite(String parecerTramite) {
        this.parecerTramite = parecerTramite;
    }

    public Date getDataTramite() {
        return this.dataTramite;
    }

    public void setDataTramite(Date dataTramite) {
        this.dataTramite = dataTramite;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Usuario getUsuarioResponsavel() {
        return this.usuarioResponsavel;
    }

    public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }

    public Usuario getUsuarioRegistro() {
        return this.usuarioRegistro;
    }

    public void setUsuarioRegistro(Usuario usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public gcom.atendimentopublico.registroatendimento.RegistroAtendimento getRegistroAtendimento() {
        return this.registroAtendimento;
    }

    public void setRegistroAtendimento(gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimento) {
        this.registroAtendimento = registroAtendimento;
    }

    public UnidadeOrganizacional getUnidadeOrganizacionalOrigem() {
        return this.unidadeOrganizacionalOrigem;
    }

    public void setUnidadeOrganizacionalOrigem(UnidadeOrganizacional unidadeOrganizacionalOrigem) {
        this.unidadeOrganizacionalOrigem = unidadeOrganizacionalOrigem;
    }

    public UnidadeOrganizacional getUnidadeOrganizacionalDestino() {
        return this.unidadeOrganizacionalDestino;
    }

    public void setUnidadeOrganizacionalDestino(UnidadeOrganizacional unidadeOrganizacionalDestino) {
        this.unidadeOrganizacionalDestino = unidadeOrganizacionalDestino;
    }
    
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}    

	public Filtro retornaFiltro() {
		FiltroTramite filtroTramite = new FiltroTramite();

		filtroTramite.adicionarParametro(new ParametroSimples(FiltroTramite.ID, this.getId()));
		return filtroTramite;
	}     

}
