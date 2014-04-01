package gcom.cadastro.imovel;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@ControleAlteracao()
public class ImovelSubcategoria extends ObjetoTransacao implements IImovelSubcategoria {
	
	private static final long serialVersionUID = 1L;

	private gcom.cadastro.imovel.ImovelSubcategoriaPK comp_id;

	@ControleAlteracao(funcionalidade={Imovel.ATRIBUTOS_IMOVEL_INSERIR, Imovel.ATRIBUTOS_IMOVEL_ATUALIZAR,Imovel.ATRIBUTOS_IMOVEL_REMOVER,Imovel.OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
	private short quantidadeEconomias;

	private Short quantidadeUnidadesPrivativas;
	
	private Short quantidadeUnidadesColetivas;
	
	private Date ultimaAlteracao;

	private Set imovelEconomias;

	public ImovelSubcategoria() {
	}

	public ImovelSubcategoria(ImovelSubcategoriaPK comp_id) {
		this.comp_id = comp_id;
	}
	
	public ImovelSubcategoria(ImovelSubcategoriaPK comp_id, short quantidadeEconomias, Date ultimaAlteracao) {
		this.comp_id = comp_id;
		this.quantidadeEconomias = quantidadeEconomias;
		this.ultimaAlteracao = ultimaAlteracao;
	}


	public ImovelSubcategoria(ImovelSubcategoriaPK comp_id, short quantidadeEconomias) {
		this.comp_id = comp_id;
		this.quantidadeEconomias = quantidadeEconomias;
	}
	
	public ImovelSubcategoria(ImovelSubcategoriaPK comp_id, short quantidadeEconomias, 
			Short quantidadeUnidadesPrivativas, Short quantidadeUnidadesColetivas,
			Date ultimaAlteracao) {
		this.comp_id = comp_id;
		this.quantidadeEconomias = quantidadeEconomias;
		this.quantidadeUnidadesPrivativas = quantidadeUnidadesPrivativas;
		this.quantidadeUnidadesColetivas = quantidadeUnidadesColetivas;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.cadastro.imovel.ImovelSubcategoriaPK getComp_id() {
		return this.comp_id;
	}

	public void setComp_id(gcom.cadastro.imovel.ImovelSubcategoriaPK comp_id) {
		this.comp_id = comp_id;
	}

	public short getQuantidadeEconomias() {
		return this.quantidadeEconomias;
	}

	public void setQuantidadeEconomias(short quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String toString() {
		return new ToStringBuilder(this).append("comp_id", getComp_id()).toString();
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof ImovelSubcategoria)) {
			return false;
		}
		ImovelSubcategoria castOther = (ImovelSubcategoria) other;

		return new EqualsBuilder().append(this.getComp_id(), castOther.getComp_id()).isEquals();
	}

	public int hashCode() {
		return this.ultimaAlteracao.hashCode();
	}

	public Set getImovelEconomias() {
		return imovelEconomias;
	}

	public void setImovelEconomias(Set imovelEconomias) {
		this.imovelEconomias = imovelEconomias;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = {"comp_id"};
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		Filtro filtro = new FiltroImovelSubCategoria();
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelSubCategoria.SUBCATEGORIA_CATEGORIA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelSubCategoria.SUBCATEGORIA);
		filtro.adicionarParametro(new ParametroSimples(FiltroImovelSubCategoria.IMOVEL_ID, this.getComp_id().getImovel().getId()));
		filtro.adicionarParametro(new ParametroSimples(FiltroImovelSubCategoria.SUBCATEGORIA_ID, this.getComp_id().getSubcategoria().getId()));
		
		return filtro; 
	}
	
	public void initializeLazy(){
		if (this.getComp_id() != null){
			if(getComp_id().getSubcategoria() != null){
				getComp_id().getSubcategoria().initializeLazy();
			}
			getDescricaoParaRegistroTransacao();
		}		
	}
	
	public String getDescricaoParaRegistroTransacao(){
		if (this.getComp_id() != null && this.getComp_id().getSubcategoria() != null 
				&& this.getComp_id().getSubcategoria().getCategoria() != null){
			return this.getComp_id().getSubcategoria().getCategoria().getDescricao() + " / " 
			    + this.getComp_id().getSubcategoria().getDescricao();	
		} else {
			return "";
		}
		 	
	}

	public Short getQuantidadeUnidadesColetivas() {
		return quantidadeUnidadesColetivas;
	}

	public void setQuantidadeUnidadesColetivas(Short quantidadeUnidadesColetivas) {
		this.quantidadeUnidadesColetivas = quantidadeUnidadesColetivas;
	}

	public Short getQuantidadeUnidadesPrivativas() {
		return quantidadeUnidadesPrivativas;
	}

	public void setQuantidadeUnidadesPrivativas(Short quantidadeUnidadesPrivativas) {
		this.quantidadeUnidadesPrivativas = quantidadeUnidadesPrivativas;
	}

	public Imovel getImovel() {
		return (comp_id != null) ? comp_id.getImovel() : null;
	}

	public void setImovel(Imovel imovel) {
		if (this.comp_id == null){
			this.comp_id = new ImovelSubcategoriaPK();
		}
		this.comp_id.setImovel(imovel);
	}

	public Subcategoria getSubcategoria() {
		return (comp_id != null) ? comp_id.getSubcategoria() : null;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		if (this.comp_id == null){
			this.comp_id = new ImovelSubcategoriaPK();
		}
		this.comp_id.setSubcategoria(subcategoria);
	}
}