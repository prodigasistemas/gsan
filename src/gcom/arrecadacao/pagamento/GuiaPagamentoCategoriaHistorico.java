package gcom.arrecadacao.pagamento;

import gcom.cadastro.imovel.Categoria;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GuiaPagamentoCategoriaHistorico implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private GuiaPagamentoCategoriaHistoricoPK comp_id;

    /** nullable persistent field */
    private Integer quantidadeEconomia;

    /** nullable persistent field */
    private BigDecimal valorCategoria;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private GuiaPagamentoHistorico guiaPagamentoHistorico;

    /** nullable persistent field */
    private Categoria categoria;


    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
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
	 * @return Retorna o campo comp_id.
	 */
	public GuiaPagamentoCategoriaHistoricoPK getComp_id() {
		return comp_id;
	}


	/**
	 * @param comp_id O comp_id a ser setado.
	 */
	public void setComp_id(GuiaPagamentoCategoriaHistoricoPK comp_id) {
		this.comp_id = comp_id;
	}


	/**
	 * @return Retorna o campo guiaPagamentoHistorico.
	 */
	public GuiaPagamentoHistorico getGuiaPagamentoHistorico() {
		return guiaPagamentoHistorico;
	}


	/**
	 * @param guiaPagamentoHistorico O guiaPagamentoHistorico a ser setado.
	 */
	public void setGuiaPagamentoHistorico(
			GuiaPagamentoHistorico guiaPagamentoHistorico) {
		this.guiaPagamentoHistorico = guiaPagamentoHistorico;
	}


	/**
	 * @return Retorna o campo quantidadeEconomia.
	 */
	public Integer getQuantidadeEconomia() {
		return quantidadeEconomia;
	}


	/**
	 * @param quantidadeEconomia O quantidadeEconomia a ser setado.
	 */
	public void setQuantidadeEconomia(Integer quantidadeEconomia) {
		this.quantidadeEconomia = quantidadeEconomia;
	}


	/**
	 * @return Retorna o campo ultimaAltercao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}


	/**
	 * @param ultimaAltercao O ultimaAltercao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}


	/**
	 * @return Retorna o campo valorCategoria.
	 */
	public BigDecimal getValorCategoria() {
		return valorCategoria;
	}


	/**
	 * @param valorCategoria O valorCategoria a ser setado.
	 */
	public void setValorCategoria(BigDecimal valorCategoria) {
		this.valorCategoria = valorCategoria;
	}

}
