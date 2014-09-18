package gcom.arrecadacao.pagamento;

import gcom.interceptor.ObjetoGcom;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class PagamentoSituacao extends ObjetoGcom {
	
	private static final long serialVersionUID = 1L;

	//Constantes *********************************
	public final static Integer PAGAMENTO_CLASSIFICADO = new Integer(0);
	
	public final static Integer PAGAMENTO_EM_DUPLICIDADE = new Integer(1);

	//criado por pedro alexandre em 25/05/2006 para o UC[0276] 
	public final static Integer DOCUMENTO_INEXISTENTE = new Integer(2);
	
	public final static Integer FATURA_INEXISTENTE = new Integer(2);

	public final static Integer VALOR_EM_EXCESSO = new Integer(3);

	public final static Integer VALOR_A_BAIXAR = new Integer(4);
	
	public final static Integer VALOR_NAO_CONFERE = new Integer(5);
	
	public final static Integer MOVIMENTO_ABERTO = new Integer(7);
	
	public final static Integer DUPLICIDADE_EXCESSO_DEVOLVIDO = new Integer(9);
    
	public final static Integer DOCUMENTO_A_CONTABILIZAR = new Integer(10);
    
	/**
	 * Detalhar contabilização de documentos inexistentes
	 * 
	 * @author Wellington Rocha
	 * @author Felipe Santos
	 * @date 01/08/2012*/
	
    public final static Integer DOCUMENTO_INEXISTENTE_DEBITO_PRESCRITO = new Integer(11);
    
    public final static Integer DOCUMENTO_INEXISTENTE_CONTA_PARCELADA = new Integer(12);
    
    public final static Integer DOCUMENTO_INEXISTENTE_CONTA_CANCELADA = new Integer(13);
    
    public final static Integer DOCUMENTO_INEXISTENTE_ERRO_PROCESSAMENTO = new Integer(14);
	//********************************************
	
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private String descricaoAbreviada;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** full constructor */
    public PagamentoSituacao(String descricao, String descricaoAbreviada, Date ultimaAlteracao, Short indicadorUso) {
    	this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorUso = indicadorUso;
    }

    /** default constructor */
    public PagamentoSituacao() {
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

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void  setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
}
