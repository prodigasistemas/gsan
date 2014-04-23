package gcom.seguranca.transacao;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class TabelaLinhaAlteracao implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer id1;

    /** nullable persistent field */
    private Integer id2;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    /** nullable persistent field */
    private Short indicadorPrincipal;

    /** persistent field */
    private gcom.seguranca.transacao.Tabela tabela;
    
    /** persistent field */
    private gcom.seguranca.acesso.OperacaoEfetuada operacaoEfetuada;
    
    /** persistent field */
    private AlteracaoTipo alteracaoTipo;

    /** persistent field */
    private Set tabelaLinhaColunaAlteracao;
    
    public static final Short INDICADOR_TABELA_LINHA_ALTERACAO_PRINCIPAL = 1;

	/** full constructor */
    public TabelaLinhaAlteracao(Integer id, Integer id1, Integer id2, Date ultimaAlteracao, 
    		gcom.seguranca.transacao.Tabela tabela, gcom.seguranca.acesso.OperacaoEfetuada operacaoEfetuada, Set tabelaLinhaColunaAlteracao, AlteracaoTipo alteracaoTipo) {
        this.id = id;
        this.id1 = id1;
        this.id2 = id2;
        this.ultimaAlteracao = ultimaAlteracao;
        this.tabela = tabela;
        this.operacaoEfetuada = operacaoEfetuada;
        this.tabelaLinhaColunaAlteracao = tabelaLinhaColunaAlteracao;
        this.alteracaoTipo = alteracaoTipo;
    }

    /** default constructor */
    public TabelaLinhaAlteracao() {
    }

    /**
	 * @return Retorna o campo alteracaoTipo.
	 */
	public AlteracaoTipo getAlteracaoTipo() {
		return alteracaoTipo;
	}

	/**
	 * @param alteracaoTipo O alteracaoTipo a ser setado.
	 */
	public void setAlteracaoTipo(AlteracaoTipo alteracaoTipo) {
		this.alteracaoTipo = alteracaoTipo;
	}

	/** minimal constructor */
    public TabelaLinhaAlteracao(Integer id, gcom.seguranca.transacao.Tabela tabela, gcom.seguranca.acesso.OperacaoEfetuada operacaoEfetuada, Set tabelaLinhaColunaAlteracao) {
        this.id = id;
        this.tabela = tabela;
        this.operacaoEfetuada = operacaoEfetuada;
        this.tabelaLinhaColunaAlteracao = tabelaLinhaColunaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * @return Returns the id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

    /**
	 * @return Retorna o campo id1.
	 */
	public Integer getId1() {
		return id1;
	}

	/**
	 * @param id1 O id1 a ser setado.
	 */
	public void setId1(Integer id1) {
		this.id1 = id1;
	}

	/**
	 * @return Returns the id2.
	 */
	public Integer getId2() {
		return id2;
	}

	/**
	 * @param id2 The id2 to set.
	 */
	public void setId2(Integer id2) {
		this.id2 = id2;
	}

	/**
	 * @return Returns the operacaoEfetuada.
	 */
	public gcom.seguranca.acesso.OperacaoEfetuada getOperacaoEfetuada() {
		return operacaoEfetuada;
	}

	/**
	 * @param operacaoEfetuada The operacaoEfetuada to set.
	 */
	public void setOperacaoEfetuada(
			gcom.seguranca.acesso.OperacaoEfetuada operacaoEfetuada) {
		this.operacaoEfetuada = operacaoEfetuada;
	}

	/**
	 * @return Returns the tabela.
	 */
	public gcom.seguranca.transacao.Tabela getTabela() {
		return tabela;
	}

	/**
	 * @param tabela The tabela to set.
	 */
	public void setTabela(gcom.seguranca.transacao.Tabela tabela) {
		this.tabela = tabela;
	}

	/**
	 * @return Returns the tabelaLinhaColunaAlteracao.
	 */
	public Set getTabelaLinhaColunaAlteracao() {
		return tabelaLinhaColunaAlteracao;
	}

	/**
	 * @param tabelaLinhaColunaAlteracao The tabelaLinhaColunaAlteracao to set.
	 */
	public void setTabelaLinhaColunaAlteracao(Set tabelaLinhaColunaAlteracao) {
		this.tabelaLinhaColunaAlteracao = tabelaLinhaColunaAlteracao;
	}

	/**
	 * @return Returns the ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao The ultimaAlteracao to set.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Short getIndicadorPrincipal() {
		return indicadorPrincipal;
	}

	public void setIndicadorPrincipal(Short indicadorPrincipal) {
		this.indicadorPrincipal = indicadorPrincipal;
	}
}
