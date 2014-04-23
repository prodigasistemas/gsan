package gcom.faturamento;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class FaturamentoSituacaoMotivo extends ObjetoTransacao implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    public final static Integer ISENCAO_TARIFA_ESGOTO_DECRETO_18251_94 = new Integer(11);
    
    public final static Integer INSPECAO_ESGOTO = 13;
    
    public final static Integer IMOVEL_MEDIDO_COM_HIDROMETRO_RETIRADO = 14;
    
    public final static Integer IMOVEL_COM_CONSUMO_MENOR_IGUAL_10M3_VARIOS_MESES = 15;

    /** full constructor */
    public FaturamentoSituacaoMotivo(String descricao, Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public FaturamentoSituacaoMotivo() {
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
    	FiltroFaturamentoSituacaoMotivo filtro = new FiltroFaturamentoSituacaoMotivo();
		
		filtro.adicionarParametro(new ParametroSimples(FiltroFaturamentoSituacaoMotivo.ID,this.getId()));
		
		return filtro;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String []atributos = {"Id"};
		return atributos;	

	}
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}

	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []atributos = {"descricao"};
		return atributos;	

	}

	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []atributos = {"Descricao"};
		return atributos;
	}	

}
