package gcom.cadastro;

import gcom.interceptor.ObjetoGcom;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class SituacaoAtualizacaoCadastral extends ObjetoGcom {
	
	private static final long serialVersionUID = 1L;
	
	public static final Integer DISPONIVEL = 0;
	public static final Integer BLOQUEADO = 1;
	public static final Integer EM_CAMPO = 2;
	public static final Integer TRANSMITIDO = 3;
	public static final Integer APROVADO = 4;
	public static final Integer EM_FISCALIZACAO = 5;
	public static final Integer ATUALIZADO = 6;
	public static final Integer PRE_APROVADO = 7;
	public static final Integer A_REVISAR = 8;
	public static final Integer EM_REVISAO = 9;

    private Integer id;

    private String descricao;

    private Short indicadorUso;

    private Date ultimaAlteracao;
    
    public SituacaoAtualizacaoCadastral(Integer id, String descricao, Short indicadorUso, Date ultimaAlteracao) {
        this.id = id;
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }
    
    public SituacaoAtualizacaoCadastral(Integer id) {
    	this.id = id;
    }

    public SituacaoAtualizacaoCadastral() {
    }

    public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
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
}
