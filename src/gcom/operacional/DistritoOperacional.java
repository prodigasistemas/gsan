package gcom.operacional;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class DistritoOperacional extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private String descricaoAbreviada;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    private ZonaAbastecimento zonaAbastecimento;
    
    private SetorAbastecimento setorAbastecimento;

    /** persistent field */
    //private gcom.operacional.SistemaAbastecimento sistemaAbastecimento;

    /** full constructor */
    public DistritoOperacional(String descricao, String descricaoAbreviada,
            Short indicadorUso, Date ultimaAlteracao,
            gcom.operacional.SistemaAbastecimento sistemaAbastecimento) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
       // this.sistemaAbastecimento = sistemaAbastecimento;
    }
    
    

    public SetorAbastecimento getSetorAbastecimento() {
		return setorAbastecimento;
	}



	public void setSetorAbastecimento(SetorAbastecimento setorAbastecimento) {
		this.setorAbastecimento = setorAbastecimento;
	}



	public ZonaAbastecimento getZonaAbastecimento() {
		return zonaAbastecimento;
	}



	public void setZonaAbastecimento(ZonaAbastecimento zonaAbastecimento) {
		this.zonaAbastecimento = zonaAbastecimento;
	}



	/** default constructor */
    public DistritoOperacional() {
    }

    /** minimal constructor */
    public DistritoOperacional(
            gcom.operacional.SistemaAbastecimento sistemaAbastecimento) {
       // this.sistemaAbastecimento = sistemaAbastecimento;
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
        return new ToStringBuilder(this).append("id", getId()).toString();
    }
	
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();
		
		filtroDistritoOperacional.adicionarCaminhoParaCarregamentoEntidade(FiltroDistritoOperacional.ZONAABASTECIMENTO);
		filtroDistritoOperacional.adicionarCaminhoParaCarregamentoEntidade(FiltroDistritoOperacional.SETORABASTECIMENTO);
		filtroDistritoOperacional.adicionarParametro(
				new ParametroSimples(FiltroDistritoOperacional.ID, this.getId()));
		return filtroDistritoOperacional; 
	}    
    
}
