package gcom.arrecadacao.pagamento;

import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.debito.DebitoTipo;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class GuiaPagamentoItem extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	private GuiaPagamentoItemPK comp_id;
	private BigDecimal valorDebito;
	private GuiaPagamentoGeral guiaPagamentoGeral;
	private DebitoTipo debitoTipo;
	private Date ultimaAlteracao;
	private ContaGeral contaGeral;
	
	public GuiaPagamentoItem(GuiaPagamentoItemPK comp_id, BigDecimal valorDebito, GuiaPagamentoGeral guiaPagamentoGeral, DebitoTipo debitoTipo, Date ultimaAlteracao) {
		this.comp_id = comp_id;
		this.valorDebito = valorDebito;
		this.guiaPagamentoGeral = guiaPagamentoGeral;
		this.debitoTipo = debitoTipo;
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public GuiaPagamentoItem(){}

	public GuiaPagamentoItemPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(GuiaPagamentoItemPK comp_id) {
		this.comp_id = comp_id;
	}

	public DebitoTipo getDebitoTipo() {
		return debitoTipo;
	}

	public void setDebitoTipo(DebitoTipo debitoTipo) {
		this.debitoTipo = debitoTipo;
	}

	public GuiaPagamentoGeral getGuiaPagamentoGeral() {
		return guiaPagamentoGeral;
	}

	public void setGuiaPagamentoGeral(
			GuiaPagamentoGeral guiaPagamentoGeral) {
		this.guiaPagamentoGeral = guiaPagamentoGeral;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	public ContaGeral getContaGeral() {
		return contaGeral;
	}

	public void setContaGeral(ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}
	
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

   

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof GuiaPagamentoItem) ) return false;
        GuiaPagamentoItem castOther = (GuiaPagamentoItem) other;
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
