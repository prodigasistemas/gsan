package gcom.arrecadacao.pagamento;

import gcom.cadastro.imovel.Categoria;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GuiaPagamentoCategoria extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];

		retorno[0] = "comp_id";
		
		return retorno;
	}
	
	public Filtro retornaFiltro() {
		FiltroGuiaPagamentoCategoria filtroGuiaPagamentoCategoria = new FiltroGuiaPagamentoCategoria();

		filtroGuiaPagamentoCategoria.adicionarParametro(new ParametroSimples(
				FiltroGuiaPagamentoCategoria.ID, this.getComp_id()));
		filtroGuiaPagamentoCategoria.adicionarCaminhoParaCarregamentoEntidade("guiaPagamento");
		filtroGuiaPagamentoCategoria
				.adicionarCaminhoParaCarregamentoEntidade("categoria");

		return filtroGuiaPagamentoCategoria;
	}

    /** identifier field */
    private gcom.arrecadacao.pagamento.GuiaPagamentoCategoriaPK comp_id;

    /** nullable persistent field */
    private Integer quantidadeEconomia;

    /** nullable persistent field */
    private BigDecimal valorCategoria;

    /** nullable persistent field */
    private gcom.arrecadacao.pagamento.GuiaPagamento guiaPagamento;

    /** nullable persistent field */
    private Categoria categoria;
    
    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
    public GuiaPagamentoCategoria(gcom.arrecadacao.pagamento.GuiaPagamentoCategoriaPK comp_id, Integer quantidadeEconomia, BigDecimal valorCategoria, gcom.arrecadacao.pagamento.GuiaPagamento guiaPagamento, Categoria categoria, Date ultimaAlteracao) {
        this.comp_id = comp_id;
        this.quantidadeEconomia = quantidadeEconomia;
        this.valorCategoria = valorCategoria;
        this.guiaPagamento = guiaPagamento;
        this.categoria = categoria;
        this.ultimaAlteracao = ultimaAlteracao;
}

    /** default constructor */
    public GuiaPagamentoCategoria() {
    }

    /** minimal constructor */
    public GuiaPagamentoCategoria(gcom.arrecadacao.pagamento.GuiaPagamentoCategoriaPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.arrecadacao.pagamento.GuiaPagamentoCategoriaPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.arrecadacao.pagamento.GuiaPagamentoCategoriaPK comp_id) {
        this.comp_id = comp_id;
    }

    public Integer getQuantidadeEconomia() {
        return this.quantidadeEconomia;
    }

    public void setQuantidadeEconomia(Integer quantidadeEconomia) {
        this.quantidadeEconomia = quantidadeEconomia;
    }

    public BigDecimal getValorCategoria() {
        return this.valorCategoria;
    }

    public void setValorCategoria(BigDecimal valorCategoria) {
        this.valorCategoria = valorCategoria;
    }

    public gcom.arrecadacao.pagamento.GuiaPagamento getGuiaPagamento() {
        return this.guiaPagamento;
    }

    public void setGuiaPagamento(gcom.arrecadacao.pagamento.GuiaPagamento guiaPagamento) {
        this.guiaPagamento = guiaPagamento;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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
        if ( !(other instanceof GuiaPagamentoCategoria) ) return false;
        GuiaPagamentoCategoria castOther = (GuiaPagamentoCategoria) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

}
