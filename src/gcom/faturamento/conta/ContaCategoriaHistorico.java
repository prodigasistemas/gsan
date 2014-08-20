package gcom.faturamento.conta;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Subcategoria;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class ContaCategoriaHistorico implements Serializable, IContaCategoria {
	private static final long serialVersionUID = 1L;

    private ContaCategoriaHistoricoPK comp_id;
    private short quantidadeEconomia;
    private BigDecimal valorAgua;
    private Integer consumoAgua;
    private BigDecimal valorEsgoto;
    private Integer consumoEsgoto;
    private BigDecimal valorTarifaMinimaAgua;
    private Integer consumoMinimoAgua;
    private BigDecimal valorTarifaMinimaEsgoto;
    private Integer consumoMinimoEsgoto;
    private Date ultimaAlteracao;

    @SuppressWarnings("rawtypes")
	private Set contaCategoriaConsumoFaixasHistorico;

    @SuppressWarnings("rawtypes")
    public ContaCategoriaHistorico(ContaCategoriaHistoricoPK comp_id, short quantidadeEconomia, BigDecimal valorAgua, Integer consumoAgua, BigDecimal valorEsgoto, Integer consumoEsgoto, BigDecimal valorTarifaMinimaAgua, Integer consumoMinimoAgua, BigDecimal valorTarifaMinimaEsgoto, Integer consumoMinimoEsgoto, Date ultimaAlteracao, Set contaCategoriaConsumoFaixasHistorico) {
        this.comp_id = comp_id;
        this.quantidadeEconomia = quantidadeEconomia;
        this.valorAgua = valorAgua;
        this.consumoAgua = consumoAgua;
        this.valorEsgoto = valorEsgoto;
        this.consumoEsgoto = consumoEsgoto;
        this.valorTarifaMinimaAgua = valorTarifaMinimaAgua;
        this.consumoMinimoAgua = consumoMinimoAgua;
        this.valorTarifaMinimaEsgoto = valorTarifaMinimaEsgoto;
        this.consumoMinimoEsgoto = consumoMinimoEsgoto;
        this.ultimaAlteracao = ultimaAlteracao;
        this.contaCategoriaConsumoFaixasHistorico = contaCategoriaConsumoFaixasHistorico;
    }

    public ContaCategoriaHistorico() {
    }

    @SuppressWarnings("rawtypes")
    public ContaCategoriaHistorico(ContaCategoriaHistoricoPK comp_id, short quantidadeEconomia, Date ultimaAlteracao, Set contaCategoriaConsumoFaixasHistorico) {
        this.comp_id = comp_id;
        this.quantidadeEconomia = quantidadeEconomia;
        this.ultimaAlteracao = ultimaAlteracao;
        this.contaCategoriaConsumoFaixasHistorico = contaCategoriaConsumoFaixasHistorico;
    }

    @SuppressWarnings("rawtypes")
    public Set getContaCategoriaConsumoFaixasHistorico() {
        return contaCategoriaConsumoFaixasHistorico;
    }

    @SuppressWarnings("rawtypes")
    public void setContaCategoriaConsumoFaixasHistorico(Set contaCategoriaConsumoFaixasHistorico) {
        this.contaCategoriaConsumoFaixasHistorico = contaCategoriaConsumoFaixasHistorico;
    }

    public ContaCategoriaHistoricoPK getComp_id() {
        return comp_id;
    }

    public void setComp_id(ContaCategoriaHistoricoPK comp_id) {
        this.comp_id = comp_id;
    }

    public Integer getConsumoAgua() {
        return consumoAgua;
    }

    public void setConsumoAgua(Integer consumoAgua) {
        this.consumoAgua = consumoAgua;
    }

    public Integer getConsumoEsgoto() {
        return consumoEsgoto;
    }

    public void setConsumoEsgoto(Integer consumoEsgoto) {
        this.consumoEsgoto = consumoEsgoto;
    }

    public Integer getConsumoMinimoAgua() {
        return consumoMinimoAgua;
    }

    public void setConsumoMinimoAgua(Integer consumoMinimoAgua) {
        this.consumoMinimoAgua = consumoMinimoAgua;
    }

    public Integer getConsumoMinimoEsgoto() {
        return consumoMinimoEsgoto;
    }

    public void setConsumoMinimoEsgoto(Integer consumoMinimoEsgoto) {
        this.consumoMinimoEsgoto = consumoMinimoEsgoto;
    }

    public short getQuantidadeEconomia() {
        return quantidadeEconomia;
    }

    public void setQuantidadeEconomia(short quantidadeEconomia) {
        this.quantidadeEconomia = quantidadeEconomia;
    }

    public Date getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public BigDecimal getValorAgua() {
        return valorAgua;
    }

    public void setValorAgua(BigDecimal valorAgua) {
        this.valorAgua = valorAgua;
    }

    public BigDecimal getValorEsgoto() {
        return valorEsgoto;
    }

    public void setValorEsgoto(BigDecimal valorEsgoto) {
        this.valorEsgoto = valorEsgoto;
    }

    public BigDecimal getValorTarifaMinimaAgua() {
        return valorTarifaMinimaAgua;
    }

    public void setValorTarifaMinimaAgua(BigDecimal valorTarifaMinimaAgua) {
        this.valorTarifaMinimaAgua = valorTarifaMinimaAgua;
    }

    public BigDecimal getValorTarifaMinimaEsgoto() {
        return valorTarifaMinimaEsgoto;
    }

    public void setValorTarifaMinimaEsgoto(BigDecimal valorTarifaMinimaEsgoto) {
        this.valorTarifaMinimaEsgoto = valorTarifaMinimaEsgoto;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof ContaCategoriaHistorico) ) return false;
        ContaCategoriaHistorico castOther = (ContaCategoriaHistorico) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

    @SuppressWarnings("rawtypes")
	public Set getContaCategoriaConsumoFaixas() {
		return null;
	}

    @SuppressWarnings("rawtypes")
	public void setContaCategoriaConsumoFaixas(Set contaCategoriaConsumoFaixas) {
	}

	public String getDescricao(){
		return this.getComp_id().getDescricao(); 	
	}
	
	public void setDescricao(String descricao) {
		if (this.getComp_id() == null) {
			this.comp_id = new ContaCategoriaHistoricoPK();
		}
		
		if (this.comp_id.getCategoria() == null) {
			this.comp_id.setCategoria(new Categoria());
		}
		
		this.comp_id.getCategoria().setDescricao(descricao);
	}
	
    public void setConta(IConta conta){
    	if (comp_id == null){
    		comp_id = new ContaCategoriaHistoricoPK();
    	}
    	
    	if (this.comp_id.getConta() == null) {
			this.comp_id.setConta(new Conta());
		}
    	comp_id.setConta(conta);
    }
    public void setCategoria(Categoria categoria){
    	if (comp_id == null){
    		comp_id = new ContaCategoriaHistoricoPK();
    	}
    	
    	if (this.comp_id.getCategoria() == null) {
			this.comp_id.setCategoria(new Categoria());
		}
    	comp_id.setCategoria(categoria);
    }
    public void setSubcategoria(Subcategoria subCategoria){
    	if (comp_id == null){
    		comp_id = new ContaCategoriaHistoricoPK();
    	}
    	
    	if (this.comp_id.getSubcategoria() == null) {
			this.comp_id.setSubcategoria(new Subcategoria());
		}
    	comp_id.setSubcategoria(subCategoria);
    }
    public IConta getConta(){
    	return comp_id != null ? comp_id.getConta() : null; 
    }
    public Categoria getCategoria(){
    	return comp_id != null ? comp_id.getCategoria() : null; 
    }
    public Subcategoria getSubcategoria(){
    	return comp_id != null ? comp_id.getSubcategoria() : null; 
    }
	
}
