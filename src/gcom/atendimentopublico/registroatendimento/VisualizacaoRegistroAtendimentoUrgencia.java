package gcom.atendimentopublico.registroatendimento;

import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class VisualizacaoRegistroAtendimentoUrgencia extends ObjetoTransacao implements Serializable {

	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;
    
    /** persistent field */
    private gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimento;

    /** persistent field */
    private Usuario usuario;
    
    /** persistent field */
    private Integer indicadorVisualizacao;
    
    /** persistent field */
    private Integer indicadorTramite;

    /** persistent field */
    private Date ultimaAlteracao;
    
    private Short indicadorReiteracao;
    
    public Short getIndicadorReiteracao() {
		return indicadorReiteracao;
	}

	public void setIndicadorReiteracao(Short indicadorReiteracao) {
		this.indicadorReiteracao = indicadorReiteracao;
	}
    
    public VisualizacaoRegistroAtendimentoUrgencia(Integer id, RegistroAtendimento registroAtendimento, Usuario usuario, Integer indicadorVisualizacao, Integer indicadorTramite, Date ultimaAlteracao, Short indicadorReiteracao) {
		super();
		
		this.id = id;
		this.registroAtendimento = registroAtendimento;
		this.usuario = usuario;
		this.indicadorVisualizacao = indicadorVisualizacao;
		this.indicadorTramite = indicadorTramite;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorReiteracao = indicadorReiteracao;
	}

	public VisualizacaoRegistroAtendimentoUrgencia(
			Integer indicadorTramite, Integer indicadorVisualizacao,
			RegistroAtendimento registroAtendimento, Usuario usuario) {
	
		this.indicadorTramite = indicadorTramite;
		this.indicadorVisualizacao = indicadorVisualizacao;
		this.registroAtendimento = registroAtendimento;	
		this.usuario = usuario;
	}
    	

	/** default constructor */
    public VisualizacaoRegistroAtendimentoUrgencia() {
    }
    
            
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public gcom.atendimentopublico.registroatendimento.RegistroAtendimento getRegistroAtendimento() {
		return registroAtendimento;
	}

	public void setRegistroAtendimento(
			gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getIndicadorVisualizacao() {
		return indicadorVisualizacao;
	}

	public void setIndicadorVisualizacao(Integer indicadorVisualizacao) {
		this.indicadorVisualizacao = indicadorVisualizacao;
	}

	public Integer getIndicadorTramite() {
		return indicadorTramite;
	}

	public void setIndicadorTramite(Integer indicadorTramite) {
		this.indicadorTramite = indicadorTramite;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
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
		FiltroVisualizacaoRegistroAtendimentoUrgencia filtroVisualizacaoRegistroAtendimentoUrgencia = new FiltroVisualizacaoRegistroAtendimentoUrgencia();

		filtroVisualizacaoRegistroAtendimentoUrgencia.adicionarParametro(new ParametroSimples(FiltroVisualizacaoRegistroAtendimentoUrgencia.ID, this.getId()));
		return filtroVisualizacaoRegistroAtendimentoUrgencia;
	}     

}
