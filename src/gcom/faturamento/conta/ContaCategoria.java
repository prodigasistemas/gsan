package gcom.faturamento.conta;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@ControleAlteracao()
public class ContaCategoria extends ObjetoTransacao implements IContaCategoria {
	private static final long serialVersionUID = 1L;

	private ContaCategoriaPK comp_id;

    @ControleAlteracao(funcionalidade=Conta.ATRIBUTOS_RETIFICAR_CONTA)
    private short quantidadeEconomia;

    private Date ultimaAlteracao;
    private BigDecimal valorAgua;
    private Integer consumoAgua;
    private BigDecimal valorEsgoto;
    private Integer consumoEsgoto;
    private BigDecimal valorTarifaMinimaAgua;
    private Integer consumoMinimoAgua;
    private BigDecimal valorTarifaMinimaEsgoto;
    private Integer consumoMinimoEsgoto;
    @SuppressWarnings("rawtypes")
	private Set contaCategoriaConsumoFaixas;
        
    @SuppressWarnings("rawtypes")
	public ContaCategoria(ContaCategoriaPK comp_id, short quantidadeEconomia, Date ultimaAlteracao, BigDecimal valorAgua, Integer consumoAgua, 
			BigDecimal valorEsgoto, Integer consumoEsgoto, BigDecimal valorTarifaMinimaAgua, Integer consumoMinimoAgua, BigDecimal valorTarifaMinimaEsgoto, 
			Integer consumoMinimoEsgoto, Set contaCategoriaConsumoFaixas) {
        this.comp_id = comp_id;
        this.quantidadeEconomia = quantidadeEconomia;
        this.ultimaAlteracao = ultimaAlteracao;
        this.valorAgua = valorAgua;
        this.consumoAgua = consumoAgua;
        this.valorEsgoto = valorEsgoto;
        this.consumoEsgoto = consumoEsgoto;
        this.valorTarifaMinimaAgua = valorTarifaMinimaAgua;
        this.consumoMinimoAgua = consumoMinimoAgua;
        this.valorTarifaMinimaEsgoto = valorTarifaMinimaEsgoto;
        this.consumoMinimoEsgoto = consumoMinimoEsgoto;
        this.contaCategoriaConsumoFaixas = contaCategoriaConsumoFaixas;
    }

    public ContaCategoria() {
    	this.comp_id = new ContaCategoriaPK();
    }

    public ContaCategoria(Conta conta, Categoria categoria) {
    	this.comp_id = new ContaCategoriaPK(conta, categoria);
    }
    
    public ContaCategoria(Conta conta, Categoria categoria, Subcategoria subcategoria) {
    	this.comp_id = new ContaCategoriaPK(conta.getId(), categoria, subcategoria);
    }

    @SuppressWarnings("rawtypes")
	public ContaCategoria(ContaCategoriaPK comp_id, short quantidadeEconomia, Set contaCategoriaConsumoFaixas) {
        this.comp_id = comp_id;
        this.quantidadeEconomia = quantidadeEconomia;
        this.contaCategoriaConsumoFaixas = contaCategoriaConsumoFaixas;
    }

    public ContaCategoriaPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(ContaCategoriaPK comp_id) {
        this.comp_id = comp_id;
    }

    public short getQuantidadeEconomia() {
        return this.quantidadeEconomia;
    }

    public void setQuantidadeEconomia(short quantidadeEconomia) {
        this.quantidadeEconomia = quantidadeEconomia;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public BigDecimal getValorAgua() {
        return this.valorAgua;
    }

    public void setValorAgua(BigDecimal valorAgua) {
        this.valorAgua = valorAgua;
    }

    public Integer getConsumoAgua() {
        return this.consumoAgua;
    }

    public void setConsumoAgua(Integer consumoAgua) {
        this.consumoAgua = consumoAgua;
    }

    public BigDecimal getValorEsgoto() {
        return this.valorEsgoto;
    }

    public void setValorEsgoto(BigDecimal valorEsgoto) {
        this.valorEsgoto = valorEsgoto;
    }

    public Integer getConsumoEsgoto() {
        return this.consumoEsgoto;
    }

    public void setConsumoEsgoto(Integer consumoEsgoto) {
        this.consumoEsgoto = consumoEsgoto;
    }

    public BigDecimal getValorTarifaMinimaAgua() {
        return this.valorTarifaMinimaAgua;
    }

    public void setValorTarifaMinimaAgua(BigDecimal valorTarifaMinimaAgua) {
        this.valorTarifaMinimaAgua = valorTarifaMinimaAgua;
    }

    public Integer getConsumoMinimoAgua() {
        return this.consumoMinimoAgua;
    }

    public void setConsumoMinimoAgua(Integer consumoMinimoAgua) {
        this.consumoMinimoAgua = consumoMinimoAgua;
    }

    public BigDecimal getValorTarifaMinimaEsgoto() {
        return this.valorTarifaMinimaEsgoto;
    }

    public void setValorTarifaMinimaEsgoto(BigDecimal valorTarifaMinimaEsgoto) {
        this.valorTarifaMinimaEsgoto = valorTarifaMinimaEsgoto;
    }

    public Integer getConsumoMinimoEsgoto() {
        return this.consumoMinimoEsgoto;
    }

    public void setConsumoMinimoEsgoto(Integer consumoMinimoEsgoto) {
        this.consumoMinimoEsgoto = consumoMinimoEsgoto;
    }

    public Set getContaCategoriaConsumoFaixas() {
        return this.contaCategoriaConsumoFaixas;
    }

    public void setContaCategoriaConsumoFaixas(Set contaCategoriaConsumoFaixas) {
        this.contaCategoriaConsumoFaixas = contaCategoriaConsumoFaixas;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof ContaCategoria) ) return false;
        ContaCategoria castOther = (ContaCategoria) other;
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
		String[] retorno = {"comp_id"};
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroContaCategoria filtro = new FiltroContaCategoria();
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroContaCategoria.CATEGORIA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroContaCategoria.SUBCATEGORIA);
		filtro.adicionarParametro(
				new ParametroSimples(FiltroContaCategoria.CATEGORIA_ID, this.getComp_id().getCategoria().getId()));
		filtro.adicionarParametro(
				new ParametroSimples(FiltroContaCategoria.SUBCATEGORIA_ID, this.getComp_id().getSubcategoria().getId()));
		filtro.adicionarParametro(
				new ParametroSimples(FiltroContaCategoria.CONTA_ID, this.getComp_id().getConta().getId()));
		
		return filtro; 
	}
	
	public void initializeLazy(){
		if (this.getComp_id() != null){
			this.comp_id.initializeLazy();	
		}		
	}
	
	public String getDescricao(){
		return this.getComp_id().getDescricao(); 	
	}
	
	public void setDescricao(String descricao) {
		if (this.getComp_id() == null) {
			this.comp_id = new ContaCategoriaPK();
		}
		
		if (this.comp_id.getCategoria() == null) {
			this.comp_id.setCategoria(new Categoria());
		}
		
		this.comp_id.getCategoria().setDescricao(descricao);
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return this.getDescricao();
	}

    public void setConta(IConta conta){
    	if (comp_id == null){
    		comp_id = new ContaCategoriaPK();
    	}
    	comp_id.setConta(conta);
    }
    public void setCategoria(Categoria categoria){
    	if (comp_id == null){
    		comp_id = new ContaCategoriaPK();
    	}
    	comp_id.setCategoria(categoria);
    }
    public void setSubcategoria(Subcategoria subCategoria){
    	if (comp_id == null){
    		comp_id = new ContaCategoriaPK();
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
