package gcom.micromedicao;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


@ControleAlteracao()
public class ItemServico extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	public static final int OPERACAO_INSERIR_ITEM_SERVICO = 1663;
	public static final int OPERACAO_MANTER_ITEM_SERVICO = 1668;
	public static final int OPERACAO_REMOVER_ITEM_SERVICO = 1669;
		
	
	private Integer id;
	
	@ControleAlteracao(funcionalidade={OPERACAO_INSERIR_ITEM_SERVICO,OPERACAO_MANTER_ITEM_SERVICO,OPERACAO_REMOVER_ITEM_SERVICO})
	private String descricao;
	
	@ControleAlteracao(funcionalidade={OPERACAO_INSERIR_ITEM_SERVICO,OPERACAO_MANTER_ITEM_SERVICO,OPERACAO_REMOVER_ITEM_SERVICO})
	private String descricaoAbreviada;
	
	@ControleAlteracao(funcionalidade={OPERACAO_INSERIR_ITEM_SERVICO,OPERACAO_MANTER_ITEM_SERVICO,OPERACAO_REMOVER_ITEM_SERVICO})
	private Short indicadorUso;
	
	@ControleAlteracao(funcionalidade={OPERACAO_INSERIR_ITEM_SERVICO,OPERACAO_MANTER_ITEM_SERVICO,OPERACAO_REMOVER_ITEM_SERVICO})
	private Integer codigoConstanteCalculo;
	
	@ControleAlteracao(funcionalidade={OPERACAO_INSERIR_ITEM_SERVICO,OPERACAO_MANTER_ITEM_SERVICO,OPERACAO_REMOVER_ITEM_SERVICO})
	private Date ultimaAlteracao;

	@ControleAlteracao(funcionalidade={OPERACAO_INSERIR_ITEM_SERVICO,OPERACAO_MANTER_ITEM_SERVICO,OPERACAO_REMOVER_ITEM_SERVICO})
	private Long codigoItem;
	
	public final static Integer CODIGO_CONSTANTE_1 = 1;
	public final static Integer CODIGO_CONSTANTE_2 = 2;
	public final static Integer CODIGO_CONSTANTE_3 = 3;
	public final static Integer CODIGO_CONSTANTE_4 = 4;
	public final static Integer CODIGO_CONSTANTE_5 = 5;
	public final static Integer CODIGO_CONSTANTE_6 = 6;
	public final static Integer CODIGO_CONSTANTE_7 = 7;
	public final static Integer CODIGO_CONSTANTE_8 = 8;
	public final static Integer CODIGO_CONSTANTE_9 = 9;
	public final static Integer CODIGO_CONSTANTE_10 = 10;
	public final static Integer CODIGO_CONSTANTE_11 = 11;
	public final static Integer CODIGO_CONSTANTE_12 = 12;
	public final static Integer CODIGO_CONSTANTE_13 = 13;
	public final static Integer CODIGO_CONSTANTE_14 = 14;
	public final static Integer CODIGO_CONSTANTE_15 = 15;
	public final static Integer CODIGO_CONSTANTE_16 = 16;
	public final static Integer CODIGO_CONSTANTE_17 = 17;
		
	    
	    public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getDescricaoAbreviada() {
			return descricaoAbreviada;
		}

		public void setDescricaoAbreviada(String descricaoAbreviada) {
			this.descricaoAbreviada = descricaoAbreviada;
		}

		public Integer getCodigoConstanteCalculo() {
			return codigoConstanteCalculo;
		}

		public void setCodigoConstanteCalculo(Integer codigoConstanteCalculo) {
			this.codigoConstanteCalculo = codigoConstanteCalculo;
		}

		public Date getUltimaAlteracao() {
			return ultimaAlteracao;
		}

		public void setUltimaAlteracao(Date ultimaAlteracao) {
			this.ultimaAlteracao = ultimaAlteracao;
		}

		public Short getIndicadorUso() {
	        return indicadorUso;
	    }

	    public void setIndicadorUso(Short indicadorUso) {
	        this.indicadorUso = indicadorUso;
	    }

	    
	    public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		
    public Long getCodigoItem() {
			return codigoItem;
		}

		public void setCodigoItem(Long codigoItem) {
			this.codigoItem = codigoItem;
		}

	public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }


	@Override
	public Filtro retornaFiltro() {
		FiltroItemServico filtroItemServico = new FiltroItemServico();
    	filtroItemServico.adicionarParametro(new ParametroSimples(FiltroItemServico.ID,this.getId()));		
       	return filtroItemServico;	
    }


	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
		
		
		@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarParametro(new ParametroSimples(FiltroItemServico.ID,this.getId()));
		
		return filtro;
	}
		
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
	}
		
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"descricao"};
		return labels;		
	}
		
	@Override
		public String[] retornarLabelsInformacoesOperacaoEfetuada() {
			String []labels = {"Descricao"};
			return labels;		
		}
}
