package gcom.batch;

import gcom.seguranca.acesso.Funcionalidade;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ProcessoFuncionalidade implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private short sequencialExecucao;

    /** persistent field */
    private gcom.batch.UnidadeProcessamento unidadeProcessamento;

    /** persistent field */
    private Funcionalidade funcionalidade;

    /** persistent field */
    private gcom.batch.Processo processo;

    /** persistent field */
    private Set funcionalidadesIniciadas;
    

    private short indicadorUso;
    
    /** persistent field */
    private String orientacao;
    
    

    public short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	/** full constructor */
    public ProcessoFuncionalidade(Date ultimaAlteracao, short sequencialExecucao, gcom.batch.UnidadeProcessamento unidadeProcessamento, Funcionalidade funcionalidade, gcom.batch.Processo processo, Set funcionalidadesIniciadas) {
        this.ultimaAlteracao = ultimaAlteracao;
        this.sequencialExecucao = sequencialExecucao;
        this.unidadeProcessamento = unidadeProcessamento;
        this.funcionalidade = funcionalidade;
        this.processo = processo;
        this.funcionalidadesIniciadas = funcionalidadesIniciadas;
    }

    /** default constructor */
    public ProcessoFuncionalidade() {
    }

    /** minimal constructor */
    public ProcessoFuncionalidade(short sequencialExecucao, gcom.batch.UnidadeProcessamento unidadeProcessamento, Funcionalidade funcionalidade, gcom.batch.Processo processo, Set funcionalidadesIniciadas) {
        this.sequencialExecucao = sequencialExecucao;
        this.unidadeProcessamento = unidadeProcessamento;
        this.funcionalidade = funcionalidade;
        this.processo = processo;
        this.funcionalidadesIniciadas = funcionalidadesIniciadas;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public short getSequencialExecucao() {
        return this.sequencialExecucao;
    }

    public void setSequencialExecucao(short sequencialExecucao) {
        this.sequencialExecucao = sequencialExecucao;
    }

    public gcom.batch.UnidadeProcessamento getUnidadeProcessamento() {
        return this.unidadeProcessamento;
    }

    public void setUnidadeProcessamento(gcom.batch.UnidadeProcessamento unidadeProcessamento) {
        this.unidadeProcessamento = unidadeProcessamento;
    }

    public Funcionalidade getFuncionalidade() {
        return this.funcionalidade;
    }

    public void setFuncionalidade(Funcionalidade funcionalidade) {
        this.funcionalidade = funcionalidade;
    }

    public gcom.batch.Processo getProcesso() {
        return this.processo;
    }

    public void setProcesso(gcom.batch.Processo processo) {
        this.processo = processo;
    }

    public Set getFuncionalidadesIniciadas() {
        return this.funcionalidadesIniciadas;
    }

    public void setFuncionalidadesIniciadas(Set funcionalidadesIniciadas) {
        this.funcionalidadesIniciadas = funcionalidadesIniciadas;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public String getOrientacao() {
		return orientacao;
	}

	public void setOrientacao(String orientacao) {
		this.orientacao = orientacao;
	}
    
    

}
