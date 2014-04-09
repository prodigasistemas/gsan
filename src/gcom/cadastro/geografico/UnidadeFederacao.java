package gcom.cadastro.geografico;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao
public class UnidadeFederacao extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private String sigla;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
    public UnidadeFederacao(String descricao, String sigla, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.sigla = sigla;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public UnidadeFederacao() {
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

    public String getSigla() {
        return this.sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

	@Override
	public Filtro retornaFiltro() {
		FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao();
    	filtroUnidadeFederacao.adicionarParametro(new ParametroSimples(FiltroUnidadeFederacao.ID,this.getId()));		
       	return filtroUnidadeFederacao;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getSigla();
	}

	@Override
	public boolean equals(Object obj) {
		boolean retorno = false;
		if (obj instanceof UnidadeFederacao) {
			UnidadeFederacao element = (UnidadeFederacao) obj;
			retorno = element.getId()==this.getId();
		}
		
		return retorno;
	}

	@Override
	public int hashCode() {
		return this.getId();
	}
}
