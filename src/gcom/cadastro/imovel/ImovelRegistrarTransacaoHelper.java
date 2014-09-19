package gcom.cadastro.imovel;

import java.util.Date;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

@ControleAlteracao()
public class ImovelRegistrarTransacaoHelper extends ObjetoTransacao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int TABELA_ID = 52;
	
	@ControleAlteracao(idTabelaColuna=35000)
	private Imovel imovelCondominio;
	
	public ImovelRegistrarTransacaoHelper(){
		
	}

	public ImovelRegistrarTransacaoHelper(Imovel imovelCondominio) {
		this.imovelCondominio = imovelCondominio;
	}

	public Imovel getImovelCondominio() {
		return imovelCondominio;
	}

	public void setImovelCondominio(Imovel imovelCondominio) {
		this.imovelCondominio = imovelCondominio;
	}

	@Override
	public Date getUltimaAlteracao() {
		
		return null;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		
		
	}

	@Override
	public Filtro retornaFiltro() {
		
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		
		return null;
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String[] labels = {"imovelCondominio.id"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String[] labels = {"Imovel"};
		return labels;		
	}

}
