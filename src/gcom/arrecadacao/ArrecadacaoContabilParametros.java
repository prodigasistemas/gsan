package gcom.arrecadacao;

import gcom.cadastro.imovel.Categoria;
import gcom.financeiro.ContaContabil;
import gcom.financeiro.lancamento.LancamentoItem;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoTipo;

import java.util.Date;

/**
 * 
 * Descrição da classe 
 *
 * @author Thiago Toscano
 * @date 16/05/2006
 */
public class ArrecadacaoContabilParametros {

    private Integer id;

    private String descricaoHistoricoCredito;

    private String descricaoHistoricoDebito;

    private Date ultimaAlteracao;

    private RecebimentoTipo recebimentoTipo;

    private LancamentoTipo lancamentoTipo;

    private LancamentoItem lancamentoItem;

    private LancamentoItemContabil lancamentoItemContabil;

    private Categoria categoria;

    private ContaContabil contaContabilDebito;

    private ContaContabil contaContabilCredito;

	public ArrecadacaoContabilParametros() {
		  
	}

	public ArrecadacaoContabilParametros(Integer id, String descricaoHistoricoCredito, String descricaoHistoricoDebito, Date ultimaAlteracao, RecebimentoTipo recebimentoTipo, LancamentoTipo lancamentoTipo, LancamentoItem lancamentoItem, LancamentoItemContabil lancamentoItemContabil, Categoria categoria, ContaContabil contaContabilDebito, ContaContabil contaContabilCredito) {

		
		this.id = id;
		this.descricaoHistoricoCredito = descricaoHistoricoCredito;
		this.descricaoHistoricoDebito = descricaoHistoricoDebito;
		this.ultimaAlteracao = ultimaAlteracao;
		this.recebimentoTipo = recebimentoTipo;
		this.lancamentoTipo = lancamentoTipo;
		this.lancamentoItem = lancamentoItem;
		this.lancamentoItemContabil = lancamentoItemContabil;
		this.categoria = categoria;
		this.contaContabilDebito = contaContabilDebito;
		this.contaContabilCredito = contaContabilCredito;
	}

	/**
	 * @return Retorna o campo categoria.
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria O categoria a ser setado.
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return Retorna o campo contaContabilCredito.
	 */
	public ContaContabil getContaContabilCredito() {
		return contaContabilCredito;
	}

	/**
	 * @param contaContabilCredito O contaContabilCredito a ser setado.
	 */
	public void setContaContabilCredito(ContaContabil contaContabilCredito) {
		this.contaContabilCredito = contaContabilCredito;
	}

	/**
	 * @return Retorna o campo contaContabilDebito.
	 */
	public ContaContabil getContaContabilDebito() {
		return contaContabilDebito;
	}

	/**
	 * @param contaContabilDebito O contaContabilDebito a ser setado.
	 */
	public void setContaContabilDebito(ContaContabil contaContabilDebito) {
		this.contaContabilDebito = contaContabilDebito;
	}

	/**
	 * @return Retorna o campo descricaoHistoricoCredito.
	 */
	public String getDescricaoHistoricoCredito() {
		return descricaoHistoricoCredito;
	}

	/**
	 * @param descricaoHistoricoCredito O descricaoHistoricoCredito a ser setado.
	 */
	public void setDescricaoHistoricoCredito(String descricaoHistoricoCredito) {
		this.descricaoHistoricoCredito = descricaoHistoricoCredito;
	}

	/**
	 * @return Retorna o campo descricaoHistoricoDebito.
	 */
	public String getDescricaoHistoricoDebito() {
		return descricaoHistoricoDebito;
	}

	/**
	 * @param descricaoHistoricoDebito O descricaoHistoricoDebito a ser setado.
	 */
	public void setDescricaoHistoricoDebito(String descricaoHistoricoDebito) {
		this.descricaoHistoricoDebito = descricaoHistoricoDebito;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo lancamentoItem.
	 */
	public LancamentoItem getLancamentoItem() {
		return lancamentoItem;
	}

	/**
	 * @param lancamentoItem O lancamentoItem a ser setado.
	 */
	public void setLancamentoItem(LancamentoItem lancamentoItem) {
		this.lancamentoItem = lancamentoItem;
	}

	/**
	 * @return Retorna o campo lancamentoItemContabil.
	 */
	public LancamentoItemContabil getLancamentoItemContabil() {
		return lancamentoItemContabil;
	}

	/**
	 * @param lancamentoItemContabil O lancamentoItemContabil a ser setado.
	 */
	public void setLancamentoItemContabil(
			LancamentoItemContabil lancamentoItemContabil) {
		this.lancamentoItemContabil = lancamentoItemContabil;
	}

	/**
	 * @return Retorna o campo lancamentoTipo.
	 */
	public LancamentoTipo getLancamentoTipo() {
		return lancamentoTipo;
	}

	/**
	 * @param lancamentoTipo O lancamentoTipo a ser setado.
	 */
	public void setLancamentoTipo(LancamentoTipo lancamentoTipo) {
		this.lancamentoTipo = lancamentoTipo;
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

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
