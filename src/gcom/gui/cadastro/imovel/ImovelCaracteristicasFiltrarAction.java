package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.AreaConstruidaFaixa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroAreaConstruidaFaixa;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroPocoTipo;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.PocoTipo;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ImovelCaracteristicasFiltrarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarCaracteristicasImovel");


		 Fachada fachada = Fachada.getInstancia();
		 
		 FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();		 
		 Collection<ImovelPerfil> collectionImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName() );
		 
		 FiltroCategoria filtroCategoria = new FiltroCategoria();
		 Collection<Categoria> collectionImovelCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName() );
		 
		 
		 FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
		 Collection<Subcategoria> collectionImovelSubcategoria = fachada.pesquisar(filtroSubcategoria, Subcategoria.class.getName() );		 
		 
		 FiltroAreaConstruidaFaixa filtroAreaConstruidaFaixa = new FiltroAreaConstruidaFaixa();		 
		 Collection<AreaConstruidaFaixa> collectionAreaConstuidaFaixa = fachada.pesquisar(filtroAreaConstruidaFaixa, AreaConstruidaFaixa.class.getName() );
		 
		 FiltroPocoTipo filtroPocoTipo = new FiltroPocoTipo();
		 Collection<PocoTipo> collectionTipoPoco = fachada.pesquisar(filtroPocoTipo, PocoTipo.class.getName() );				
		 
		 httpServletRequest.setAttribute("collectionImovelPerfil", collectionImovelPerfil);
		 httpServletRequest.setAttribute("collectionImovelCategoria", collectionImovelCategoria);
		 httpServletRequest.setAttribute("collectionImovelSubcategoria", collectionImovelSubcategoria);
		 httpServletRequest.setAttribute("collectionAreaConstuidaFaixa", collectionAreaConstuidaFaixa);
		 httpServletRequest.setAttribute("collectionTipoPoco", collectionTipoPoco);
		 		 
		return retorno;
	}

}
