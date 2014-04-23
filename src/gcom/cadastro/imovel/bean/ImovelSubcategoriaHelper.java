package gcom.cadastro.imovel.bean;

import gcom.cadastro.imovel.ImovelSubcategoria;

import java.io.Serializable;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Hibernate CodeGenerator
 * @created 1 de Junho de 2004
 */
public class ImovelSubcategoriaHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public ImovelSubcategoriaHelper(){}
	
	private String subcategoria;
	
	private String categoria;
	
	private short quantidadeEconomias;
	
	private Collection colecaoImovelEconomia;

	private JRBeanCollectionDataSource colecaoClienteImovelEconomiaHelper;

	public Collection getColecaoImovelEconomia() {
		return colecaoImovelEconomia;
	}

	private ImovelSubcategoria imovelSubcategoria;
	
	public void setColecaoImovelEconomia(Collection colecaoImovelEconomia) {
		this.colecaoImovelEconomia = colecaoImovelEconomia;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public short getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	public void setQuantidadeEconomias(short quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	public String getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}

	public JRBeanCollectionDataSource getColecaoClienteImovelEconomiaHelper() {
		return colecaoClienteImovelEconomiaHelper;
	}

	public void setColecaoClienteImovelEconomiaHelper(
			JRBeanCollectionDataSource colecaoImovelEconomiaHelper) {
		this.colecaoClienteImovelEconomiaHelper = colecaoImovelEconomiaHelper;
	}

	public ImovelSubcategoria getImovelSubcategoria() {
		return imovelSubcategoria;
	}

	public void setImovelSubcategoria(ImovelSubcategoria imovelSubcategoria) {
		this.imovelSubcategoria = imovelSubcategoria;
	}

	public String getCategoriaAPartirImovelSubcategoria() {
		if(this.imovelSubcategoria!=null && this.imovelSubcategoria.getComp_id()!=null){

			if(this.imovelSubcategoria.getComp_id().getSubcategoria()!=null
					&& this.imovelSubcategoria.getComp_id().getSubcategoria().getCategoria()!=null){
				
				return this.imovelSubcategoria.getComp_id().getSubcategoria().getCategoria().getDescricao();
			}
		}
		return "";
	}

	public String getSubcategoriaAPartirImovelSubcategoria() {
		if(this.imovelSubcategoria!=null && this.imovelSubcategoria.getComp_id()!=null){

			if(this.imovelSubcategoria.getComp_id().getSubcategoria()!=null){
				
				return this.imovelSubcategoria.getComp_id().getSubcategoria().getDescricao();
			}
		}
		return "";
	}

	public short getQuantidadeEconomiasAPartirImovelSubcategoria() {
		if(this.imovelSubcategoria!=null ){
			
			return this.imovelSubcategoria.getQuantidadeEconomias();
		}
		
		return 0;
	}

	
}
