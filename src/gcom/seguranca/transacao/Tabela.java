package gcom.seguranca.transacao;

import gcom.util.tabelaauxiliar.abreviada.TabelaAuxiliarAbreviada;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Tabela extends TabelaAuxiliarAbreviada {
	private static final long serialVersionUID = 1L;
	
	public static final Integer IMOVEL_ATUALIZACAO_CADASTRAL = new Integer(661);
	public static final Integer CLIENTE_ATUALIZACAO_CADASTRAL = new Integer(662);
	public static final Integer CLIENTE_FONE_ATUALIZACAO_CADASTRAL = new Integer(663);
	public static final Integer IMOVEL_SUBCATEGORIA_ATUALIZACAO_CADASTRAL = new Integer(664);
	public static final Integer IMOVEL_RAMO_ATIVIDADE_ATUALIZACAO_CADASTRAL = new Integer(665);
	public static final Integer IMOVEL_QUANTIDADE_TIPO_OCUPANTE_ATUALIZACAO_CADASTRAL = new Integer(666);
    
    private String nomeTabela;

    private Set tabelaColunas;

    public Tabela() {
    }
    
    public Tabela(Integer id) {
    	this.id = id;
    }
    
    public Tabela(String descricaoTabela, String nomeTabela, Date ultimaAlteracao) {
        this.descricao = descricaoTabela;
        this.ultimaAlteracao = ultimaAlteracao;
        this.nomeTabela = nomeTabela;
    }

    public String getNomeTabela() {
		return nomeTabela;
	}

	public void setNomeTabela(String nomeTabela) {
		this.nomeTabela = nomeTabela;
	}

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Set getTabelaColunas() {
		return tabelaColunas;
	}

	public void setTabelaColunas(Set tabelaColunas) {
		this.tabelaColunas = tabelaColunas;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if (!(other instanceof Tabela))
			return false;
		Tabela castOther = (Tabela) other;
		return new EqualsBuilder().append(this.getId(),
				castOther.getId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}
	
	 public String[] retornaCamposChavePrimaria(){
			String[] retorno = new String[1];
			retorno[0] = "id";
			return retorno;
	}

}
