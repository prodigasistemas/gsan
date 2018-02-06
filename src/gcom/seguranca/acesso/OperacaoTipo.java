package gcom.seguranca.acesso;

import gcom.interceptor.ObjetoGcom;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class OperacaoTipo extends ObjetoGcom {
	
	private static final long serialVersionUID = 1L;
	
	public static final int PESQUISAR = 1;
 	public static final int REMOVER = 2;
	public static final int ATUALIZAR = 3;
	public static final int INSERIR = 4;

    private Integer id;
    private String descricao;
    private short indicadorAtualiza;
    private Date ultimaAlteracao;

    public OperacaoTipo(Integer id, String descricao, short indicadorAtualiza, Date ultimaAlteracao) {
        this.id = id;
        this.descricao = descricao;
        this.indicadorAtualiza = indicadorAtualiza;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public OperacaoTipo() {
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

	public short getIndicadorAtualiza() {
		return indicadorAtualiza;
	}

	public void setIndicadorAtualiza(short indicadorAtualiza) {
		this.indicadorAtualiza = indicadorAtualiza;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
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
