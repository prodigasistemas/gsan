package gcom.cadastro.imovel;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class PavimentoRua extends ObjetoTransacao{
	
	private static final long serialVersionUID = 1L;
	
	
	public static final Integer NAO_INFORMADO  = new Integer(0);
	public static final Integer TERRA  = new Integer(1);
	public static final Integer ASFALTO  = new Integer(2);
	public static final Integer CONCRETO  = new Integer(3);
	public static final Integer PARALELEPIPEDO  = new Integer(4);
	public static final Integer RACHAO  = new Integer(5);
	public static final Integer BLOQUETE  = new Integer(6);
	public static final Integer ESCADARIA  = new Integer(7);	
	public static final Integer OUTROS  = new Integer(8);
	public static final Integer PEDRA  = new Integer(9);
	public static final Integer SEM_PAVIMENTO  = new Integer(10);

	
    private Integer id;
    private String descricao;
    private String descricaoAbreviada;
    private Short indicadorUso;
    private Date ultimaAlteracao;
    private String descricaoComId;

    public PavimentoRua(String descricao, String descricaoAbreviada,
            Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public PavimentoRua() {
    }
    
    public PavimentoRua(String descricao, String descricaoAbreviada) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
    }

    public PavimentoRua(Integer id) {
		this.id = id;
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
    
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}
	
	public Filtro retornaFiltro() {
		Filtro filtro = new FiltroPavimentoRua();
		filtro.adicionarParametro(new ParametroSimples(FiltroPavimentoRua.ID,
				this.getId()));		
		return filtro;
	}
	
	public String getDescricaoComId() {
		
		if(this.getId().compareTo(10) == -1){
			descricaoComId = "0" + getId()+ " - " + getDescricao();
		}else{
			descricaoComId = getId()+ " - " + getDescricao();
		}
		
		return descricaoComId;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}
}
