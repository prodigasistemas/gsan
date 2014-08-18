package gcom.micromedicao.leitura;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoGcom;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

@ControleAlteracao()
public class LeituraSituacao extends ObjetoGcom {

	private static final long serialVersionUID = 1L;
	
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

    private Integer id;
    private String descricao;
    private Short indicadorUso;
    private Date ultimaAlteracao;

    public final static Integer REALIZADA = new Integer(1);
    public final static Integer CONFIRMADA = new Integer(3);
    public final static Integer NAO_REALIZADA = new Integer(2);
    public final static Integer LEITURA_ALTERADA = new Integer(4);
    public final static Integer REAL = new Integer(-1);

    public LeituraSituacao() {
    }
    
    public LeituraSituacao(Integer id) {
    	this.id = id;
    }

    public LeituraSituacao(String descricao, Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
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
        return new ToStringBuilder(this).append("id", getId()).toString();
    }
}