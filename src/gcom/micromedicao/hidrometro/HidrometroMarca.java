package gcom.micromedicao.hidrometro;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class HidrometroMarca extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String descricao;

    /** persistent field */
    private String descricaoAbreviada;

    /** persistent field */
    private short intervaloDiasRevisao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    /** nullable persistent field */
    private String codigoHidrometroMarca;
    
    public final static Integer MARCA_ID_A = 10;
    public final static String MARCA_A = "A";
    public final static Integer MARCA_ID_B = 11;
    public final static String MARCA_B = "B";
    public final static Integer MARCA_ID_C = 12;
    public final static String MARCA_C = "C";
    

    /** full constructor */
    public HidrometroMarca(String descricao, String descricaoAbreviada,
            short intervaloDiasRevisao, Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.intervaloDiasRevisao = intervaloDiasRevisao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public HidrometroMarca() {
    }

    /** minimal constructor */
    public HidrometroMarca(String descricao, String descricaoAbreviada,
            short intervaloDiasRevisao) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.intervaloDiasRevisao = intervaloDiasRevisao;
    }

    public HidrometroMarca(Integer id) {
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

    public short getIntervaloDiasRevisao() {
        return this.intervaloDiasRevisao;
    }

    public void setIntervaloDiasRevisao(short intervaloDiasRevisao) {
        this.intervaloDiasRevisao = intervaloDiasRevisao;
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

	public String getCodigoHidrometroMarca() {
		return codigoHidrometroMarca;
	}

	public void setCodigoHidrometroMarca(String codigoHidrometroMarca) {
		this.codigoHidrometroMarca = codigoHidrometroMarca;
	}
	
	public Filtro retornaFiltro() {
		FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca();
		filtroHidrometroMarca.adicionarParametro(new ParametroSimples(FiltroHidrometroMarca.ID,this.getId()));
		return filtroHidrometroMarca;
	}	
}
