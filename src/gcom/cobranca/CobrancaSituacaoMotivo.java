package gcom.cobranca;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;

@ControleAlteracao()
/** @author Hibernate CodeGenerator */
public class CobrancaSituacaoMotivo extends ObjetoTransacao implements Serializable {
	private static final long serialVersionUID = 1L;
	//	Indicador usado para a empresa Terceirizada da CAEMA
    public final static Integer IMOVEIS_ENVIADOS_EMPRESA_TERCEIRIZADA = new Integer(12);
    
    public final static Integer IMOVEL_CADASTRADO_VIVA_AGUA = new Integer(14);

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
    public CobrancaSituacaoMotivo(String descricao, Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
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
