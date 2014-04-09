package gcom.atendimentopublico.registroatendimento;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class SolicitacaoTipoGrupo extends ObjetoTransacao {
	
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private String descricaoAbreviada;

    /** persistent field */
    private short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private short indicadorEsgoto;
    
    public static final Integer ID_OPERACIONAL_AGUA_COM_DIAGNOSTICO = 6;

    /** full constructor */
    public SolicitacaoTipoGrupo(String descricao, String descricaoAbreviada, short indicadorUso, Date ultimaAlteracao, short indicadorEsgoto) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorEsgoto = indicadorEsgoto;
    }

    /** default constructor */
    public SolicitacaoTipoGrupo() {
    }

    /** minimal constructor */
    public SolicitacaoTipoGrupo(short indicadorUso, Date ultimaAlteracao, short indicadorEsgoto) {
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorEsgoto = indicadorEsgoto;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoAbreviada() {
        return this.descricaoAbreviada;
    }

    public void setDescricaoAbreviada(String descricaoAbreviada) {
        this.descricaoAbreviada = descricaoAbreviada;
    }

    public short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public short getIndicadorEsgoto() {
        return this.indicadorEsgoto;
    }

    public void setIndicadorEsgoto(short indicadorEsgoto) {
        this.indicadorEsgoto = indicadorEsgoto;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
    public Filtro retornaFiltro() {
		FiltroSolicitacaoTipoGrupo filtroSolicitacaoTipoGrupo = new FiltroSolicitacaoTipoGrupo();
		filtroSolicitacaoTipoGrupo.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoGrupo.ID,
				this.getId()));
		return filtroSolicitacaoTipoGrupo;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return this.getDescricao();
	}

}
