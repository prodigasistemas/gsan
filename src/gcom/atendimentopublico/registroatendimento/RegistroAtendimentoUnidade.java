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
public class RegistroAtendimentoUnidade extends ObjetoTransacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Usuario usuario;

    /** persistent field */
    private gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimento;

    /** persistent field */
    private UnidadeOrganizacional unidadeOrganizacional;

    /** persistent field */
    private gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo atendimentoRelacaoTipo;

    /** full constructor */
    public RegistroAtendimentoUnidade(Date ultimaAlteracao, Usuario usuario, gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimento, UnidadeOrganizacional unidadeOrganizacional, gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo atendimentoRelacaoTipo) {
        this.ultimaAlteracao = ultimaAlteracao;
        this.usuario = usuario;
        this.registroAtendimento = registroAtendimento;
        this.unidadeOrganizacional = unidadeOrganizacional;
        this.atendimentoRelacaoTipo = atendimentoRelacaoTipo;
    }

    /** default constructor */
    public RegistroAtendimentoUnidade() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public gcom.atendimentopublico.registroatendimento.RegistroAtendimento getRegistroAtendimento() {
        return this.registroAtendimento;
    }

    public void setRegistroAtendimento(gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimento) {
        this.registroAtendimento = registroAtendimento;
    }

    public UnidadeOrganizacional getUnidadeOrganizacional() {
        return this.unidadeOrganizacional;
    }

    public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
        this.unidadeOrganizacional = unidadeOrganizacional;
    }

    public gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo getAtendimentoRelacaoTipo() {
        return this.atendimentoRelacaoTipo;
    }

    public void setAtendimentoRelacaoTipo(gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo atendimentoRelacaoTipo) {
        this.atendimentoRelacaoTipo = atendimentoRelacaoTipo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Filtro retornaFiltro() {
		FiltroRegistroAtendimentoUnidade filtroRegistroAtendimentoUnidade = new FiltroRegistroAtendimentoUnidade();

		filtroRegistroAtendimentoUnidade.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");
		filtroRegistroAtendimentoUnidade.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
		filtroRegistroAtendimentoUnidade.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimentoUnidade.ID, this.getId()));

		return filtroRegistroAtendimentoUnidade;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}    
    
}
