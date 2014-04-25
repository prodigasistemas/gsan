package gcom.financeiro.lancamento;

import gcom.arrecadacao.RecebimentoTipo;
import gcom.cadastro.localidade.Localidade;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class LancamentoContabil implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMes;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.financeiro.lancamento.LancamentoOrigem lancamentoOrigem;

    /** persistent field */
    private gcom.financeiro.lancamento.LancamentoTipo lancamentoTipo;

    /** persistent field */
    private Localidade localidade;
    
    private RecebimentoTipo recebimentoTipo;
   
    
    /** full constructor */
    public LancamentoContabil(int anoMes, Date ultimaAlteracao, gcom.financeiro.lancamento.LancamentoOrigem lancamentoOrigem, gcom.financeiro.lancamento.LancamentoTipo lancamentoTipo, Localidade localidade,RecebimentoTipo recebimentoTipo) {
        this.anoMes = anoMes;
        this.ultimaAlteracao = ultimaAlteracao;
        this.lancamentoOrigem = lancamentoOrigem;
        this.lancamentoTipo = lancamentoTipo;
        this.localidade = localidade;
        this.recebimentoTipo= recebimentoTipo;
    }

    public LancamentoContabil(int anoMes, Date ultimaAlteracao, gcom.financeiro.lancamento.LancamentoOrigem lancamentoOrigem, gcom.financeiro.lancamento.LancamentoTipo lancamentoTipo, Localidade localidade) {
        this.anoMes = anoMes;
        this.ultimaAlteracao = ultimaAlteracao;
        this.lancamentoOrigem = lancamentoOrigem;
        this.lancamentoTipo = lancamentoTipo;
        this.localidade = localidade;
    }

    /** default constructor */
    public LancamentoContabil() {
    }

    /** minimal constructor */
    public LancamentoContabil(int anoMes, gcom.financeiro.lancamento.LancamentoOrigem lancamentoOrigem, gcom.financeiro.lancamento.LancamentoTipo lancamentoTipo, Localidade localidade) {
        this.anoMes = anoMes;
        this.lancamentoOrigem = lancamentoOrigem;
        this.lancamentoTipo = lancamentoTipo;
        this.localidade = localidade;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAnoMes() {
        return this.anoMes;
    }

    public void setAnoMes(int anoMes) {
        this.anoMes = anoMes;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.financeiro.lancamento.LancamentoOrigem getLancamentoOrigem() {
        return this.lancamentoOrigem;
    }

    public void setLancamentoOrigem(gcom.financeiro.lancamento.LancamentoOrigem lancamentoOrigem) {
        this.lancamentoOrigem = lancamentoOrigem;
    }

    public gcom.financeiro.lancamento.LancamentoTipo getLancamentoTipo() {
        return this.lancamentoTipo;
    }

    public void setLancamentoTipo(gcom.financeiro.lancamento.LancamentoTipo lancamentoTipo) {
        this.lancamentoTipo = lancamentoTipo;
    }

    public Localidade getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo recebimentoTipo.
	 */
	public RecebimentoTipo getRecebimentoTipo() {
		return recebimentoTipo;
	}

	/**
	 * @param recebimentoTipo O recebimentoTipo a ser setado.
	 */
	public void setRecebimentoTipo(RecebimentoTipo recebimentoTipo) {
		this.recebimentoTipo = recebimentoTipo;
	}

	
	
}
