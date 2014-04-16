package gcom.arrecadacao;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class RecebimentoTipo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	
	//Constantes *******************************************************************************************
	public final static Integer RECEBIMENTOS_CLASSIFICADOS = new Integer(1);
	
	public final static Integer RECEBIMENTOS_NAO_CLASSIFICADOS = new Integer(2);

	public final static Integer TOTAL_RECEBIMENTOS = new Integer(3);

	public final static Integer DEVOLUCOES_CLASSIFICADAS = new Integer(4);

	public final static Integer DEVOLUCOES_NAO_CLASSIFICADAS = new Integer(5);
	
	public final static Integer TOTAL_DEVOLUCOES = new Integer(6);
	
	public final static Integer ARRECADACAO_LIQUIDA = new Integer(7);
	
	public final static Integer RECEBIMENTO_MESES_ANTERIORES_CLASSIFICADAS_NO_MES = new Integer(8);
	
	public final static Integer DEVOLUCOES_RECEBIMENTO_MESES_ANTERIORES_CLASSIFICADAS_NO_MES = new Integer(9);
	
	public final static Integer BAIXA_RECEBIMENTOS_NAO_CLASSIFICADOS = new Integer(10);
	
	public final static Integer RECEBIMENTOS_VALORES_CONTABILIZADOS_COMO_PERDAS = new Integer(11);
	
	public final static Integer RESUMO_RECEBIMENTOS_NAO_CLASSIFICADOS = new Integer(12);
	
	public final static Integer RESUMO_DEVOLUCOES_NAO_CLASSIFICADAS = new Integer(13);
		
	public final static Integer TOTAL_DESCONTOS = new Integer(14);
	
	public final static Integer RECEBIMENTO_DE_VALORES_DA_CAMPANHA_SOLIDARIEDADE_DA_CRIANCA = new Integer(15);
	
	public final static Integer RECEBIMENTO_MESES_ATE_31_12_2012_CLASSIFICADOS_NO_MES = new Integer (16);
	//********************************************************************************************************

	
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    private Short numeroSequencia;

    /**
	 * @return Retorna o campo numeroSequencia.
	 */
	public Short getNumeroSequencia() {
		return numeroSequencia;
	}

	/**
	 * @param numeroSequencia O numeroSequencia a ser setado.
	 */
	public void setNumeroSequencia(Short numeroSequencia) {
		this.numeroSequencia = numeroSequencia;
	}

	/** full constructor */
    public RecebimentoTipo (String descricao, Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public RecebimentoTipo () {
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

}
