package gcom.seguranca.acesso;

import java.util.Date;

import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.transacao.Tabela;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OperacaoTabela extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private OperacaoTabelaPK comp_id;

    /** nullable persistent field */
    private Tabela tabela;

    /** nullable persistent field */
    private Operacao operacao;

    /** nullable persistent field */
	private Date ultimaAlteracao;
	
    /** full constructor */
    public OperacaoTabela(OperacaoTabelaPK comp_id, Tabela tabela, Operacao operacao) {
        this.comp_id = comp_id;
        this.tabela = tabela;
        this.operacao = operacao;
    }

    /** default constructor */
    public OperacaoTabela() {
    }

    /** minimal constructor */
    public OperacaoTabela(OperacaoTabelaPK comp_id) {
        this.comp_id = comp_id;
    }

    public OperacaoTabelaPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(OperacaoTabelaPK comp_id) {
        this.comp_id = comp_id;
    }

    public Tabela getTabela() {
        return this.tabela;
    }

    public void setTabela(Tabela tabela) {
        this.tabela = tabela;
    }

    public Operacao getOperacao() {
        return this.operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

    public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
    
    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof OperacaoTabela) ) return false;
        OperacaoTabela castOther = (OperacaoTabela) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "comp_id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroOperacaoTabela filtroOperacaoTabela = new FiltroOperacaoTabela();
		
		filtroOperacaoTabela. adicionarCaminhoParaCarregamentoEntidade("operacao");
		filtroOperacaoTabela. adicionarCaminhoParaCarregamentoEntidade("tabela");
		new ParametroSimples(FiltroOperacaoTabela.OPERACAO_ID, this.getOperacao().getId());
		new ParametroSimples(FiltroOperacaoTabela.TABELA_ID, this.getTabela().getId());
		return filtroOperacaoTabela; 
	}
}
