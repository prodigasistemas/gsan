package gcom.arrecadacao.pagamento;

import gcom.interceptor.ObjetoGcom;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class PagamentoSituacao extends ObjetoGcom {
	
	private static final long serialVersionUID = 1L;

	public final static Integer PAGAMENTO_CLASSIFICADO = new Integer(0);
	public final static Integer PAGAMENTO_EM_DUPLICIDADE = new Integer(1);
	public final static Integer DOCUMENTO_INEXISTENTE = new Integer(2);
	public final static Integer FATURA_INEXISTENTE = new Integer(2);

	public final static Integer VALOR_EM_EXCESSO = new Integer(3);
	public final static Integer VALOR_A_BAIXAR = new Integer(4);
	public final static Integer VALOR_NAO_CONFERE = new Integer(5);
	public final static Integer MOVIMENTO_ABERTO = new Integer(7);
	public final static Integer DUPLICIDADE_EXCESSO_DEVOLVIDO = new Integer(9);
	public final static Integer DOCUMENTO_A_CONTABILIZAR = new Integer(10);
    public final static Integer DOCUMENTO_INEXISTENTE_DEBITO_PRESCRITO = new Integer(11);
    public final static Integer DOCUMENTO_INEXISTENTE_CONTA_PARCELADA = new Integer(12);
    public final static Integer DOCUMENTO_INEXISTENTE_CONTA_CANCELADA = new Integer(13);
    public final static Integer DOCUMENTO_INEXISTENTE_ERRO_PROCESSAMENTO = new Integer(14);
    public final static Integer PAGAMENTO_CLASSIFICADO_RECUPERACAO_CREDITO = new Integer(15);
	
    private Integer id;
    private String descricao;
    private String descricaoAbreviada;
    private Date ultimaAlteracao;
    private Short indicadorUso;

    public PagamentoSituacao() {
    }

    public PagamentoSituacao(Integer id) {
    	this.id = id;
    }
    
    public PagamentoSituacao(String descricao, String descricaoAbreviada, Date ultimaAlteracao, Short indicadorUso) {
    	this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorUso = indicadorUso;
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
