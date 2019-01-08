package gcom.cobranca;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;

@ControleAlteracao()

public class CobrancaSituacaoMotivo extends ObjetoTransacao implements Serializable {
	private static final long serialVersionUID = 1L;

	public final static Integer EM_PROCESSO_JUDICIAL = new Integer(4);
	public final static Integer IMOVEIS_ENVIADOS_EMPRESA_TERCEIRIZADA = new Integer(12);
    public final static Integer IMOVEL_CADASTRADO_VIVA_AGUA = new Integer(14);

    private Integer id;
    private String descricao;
    private Short indicadorUso;
    private Date ultimaAlteracao;

    public CobrancaSituacaoMotivo(String descricao, Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public CobrancaSituacaoMotivo() {
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

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	@Override
	public Filtro retornaFiltro() {
		FiltroCobrancaSituacaoMotivo filtro = new FiltroCobrancaSituacaoMotivo();
		
		filtro.adicionarParametro(new ParametroSimples(FiltroCobrancaSituacaoMotivo.ID,this.getId()));
		
		return filtro;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String []atributos = {"Id"};
		return atributos;	

	}
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return this.getDescricao()+"";
	}

	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []atributos = {"descricao"};
		return atributos;	

	}

	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []atributos = {"Descrição"};
		return atributos;
	}	

}
