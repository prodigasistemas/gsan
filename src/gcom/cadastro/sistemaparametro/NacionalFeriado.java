package gcom.cadastro.sistemaparametro;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class NacionalFeriado extends ObjetoTransacao  {
	
	private static final long serialVersionUID = 1L;
	
	
    /** identifier field */
    private Integer id;
    
    /** persistent field */
    private Date data;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
    public NacionalFeriado(Date data, String descricao, Date ultimaAlteracao) {
        this.data = data;
        this.descricao = descricao;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public NacionalFeriado() {
    }

    /** minimal constructor */
    public NacionalFeriado(Date data) {
        this.data = data;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
    
    public Filtro retornaFiltro(){
    	FiltroNacionalFeriado filtroNacionalFeriado = new FiltroNacionalFeriado();

		filtroNacionalFeriado.adicionarParametro(new ParametroSimples(FiltroNacionalFeriado.ID,this.getId()));
		
		return filtroNacionalFeriado;
	}
    
    public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}
	

}
