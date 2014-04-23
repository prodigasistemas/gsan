package gcom.financeiro;

import gcom.cadastro.imovel.Categoria;
import gcom.financeiro.lancamento.LancamentoItem;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoTipo;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DevedoresDuvidososContabilParametro implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private String descricaoHistoricoDebito;

    /** persistent field */
    private String descricaoHistoricoCredito;

    /** persistent field */
    private LancamentoItem lancamentoItem;

    /** persistent field */
    private LancamentoTipo lancamentoTipo;

    /** persistent field */
    private Categoria categoria;

    /** persistent field */
    private gcom.financeiro.ContaContabil contaContabilCredito;

    /** persistent field */
    private gcom.financeiro.ContaContabil contaContabilDebito;

    /** persistent field */
    private LancamentoItemContabil lancamentoItemContabil;

    /** full constructor */
    public DevedoresDuvidososContabilParametro(Integer id, Date ultimaAlteracao, String descricaoHistoricoDebito, String descricaoHistoricoCredito, LancamentoItem lancamentoItem, LancamentoTipo lancamentoTipo, Categoria categoria, gcom.financeiro.ContaContabil contaContabilCredito, gcom.financeiro.ContaContabil contaContabilDebito, LancamentoItemContabil lancamentoItemContabil) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.descricaoHistoricoDebito = descricaoHistoricoDebito;
        this.descricaoHistoricoCredito = descricaoHistoricoCredito;
        this.lancamentoItem = lancamentoItem;
        this.lancamentoTipo = lancamentoTipo;
        this.categoria = categoria;
        this.contaContabilCredito = contaContabilCredito;
        this.contaContabilDebito = contaContabilDebito;
        this.lancamentoItemContabil = lancamentoItemContabil;
    }

    /** default constructor */
    public DevedoresDuvidososContabilParametro() {
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

    public String getDescricaoHistoricoDebito() {
        return this.descricaoHistoricoDebito;
    }

    public void setDescricaoHistoricoDebito(String descricaoHistoricoDebito) {
        this.descricaoHistoricoDebito = descricaoHistoricoDebito;
    }

    public String getDescricaoHistoricoCredito() {
        return this.descricaoHistoricoCredito;
    }

    public void setDescricaoHistoricoCredito(String descricaoHistoricoCredito) {
        this.descricaoHistoricoCredito = descricaoHistoricoCredito;
    }

    public LancamentoItem getLancamentoItem() {
        return this.lancamentoItem;
    }

    public void setLancamentoItem(LancamentoItem lancamentoItem) {
        this.lancamentoItem = lancamentoItem;
    }

    public LancamentoTipo getLancamentoTipo() {
        return this.lancamentoTipo;
    }

    public void setLancamentoTipo(LancamentoTipo lancamentoTipo) {
        this.lancamentoTipo = lancamentoTipo;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public gcom.financeiro.ContaContabil getContaContabilCredito() {
        return this.contaContabilCredito;
    }

    public void setContaContabilCredito(gcom.financeiro.ContaContabil contaContabilCredito) {
        this.contaContabilCredito = contaContabilCredito;
    }

    public gcom.financeiro.ContaContabil getContaContabilDebito() {
        return this.contaContabilDebito;
    }

    public void setContaContabilDebito(gcom.financeiro.ContaContabil contaContabilDebito) {
        this.contaContabilDebito = contaContabilDebito;
    }

    public LancamentoItemContabil getLancamentoItemContabil() {
        return this.lancamentoItemContabil;
    }

    public void setLancamentoItemContabil(LancamentoItemContabil lancamentoItemContabil) {
        this.lancamentoItemContabil = lancamentoItemContabil;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
