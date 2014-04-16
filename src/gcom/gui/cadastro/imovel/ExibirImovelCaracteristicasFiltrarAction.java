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
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirImovelCaracteristicasFiltrarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarCaracteristicasImovel");
		
		//obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		ImovelOutrosCriteriosActionForm imovelOutrosCriteriosActionForm = (ImovelOutrosCriteriosActionForm) actionForm;

		 Fachada fachada = Fachada.getInstancia();
		 
		 Collection<ImovelPerfil> collectionImovelPerfil = null;
		 Collection<Categoria> collectionImovelCategoria= null;
		 Collection<Subcategoria> collectionImovelSubcategoria = null;
		 Collection<AreaConstruidaFaixa> collectionAreaConstuidaFaixa = null;
		 Collection<PocoTipo> collectionTipoPoco = null;
		 
		 String parametroGerarRelatorio = (String)sessao.getAttribute("parametroGerarRelatorio");
		 
		 if(imovelOutrosCriteriosActionForm.getCategoriaImovel() != null &&
				 !imovelOutrosCriteriosActionForm.getCategoriaImovel().trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)
				 && !imovelOutrosCriteriosActionForm.getCategoriaImovel().trim().equals("")){
			 
			 FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
			 filtroSubcategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.CATEGORIA_ID, imovelOutrosCriteriosActionForm.getCategoriaImovel()));
			 filtroSubcategoria.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);
			 collectionImovelSubcategoria = fachada.pesquisar(filtroSubcategoria, Subcategoria.class.getName() );
			 
			 sessao.setAttribute("collectionImovelSubcategoria", collectionImovelSubcategoria);	 
		 } else if(imovelOutrosCriteriosActionForm.getSubcategoria() != null
				 && !imovelOutrosCriteriosActionForm.getSubcategoria().trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)
				 && !imovelOutrosCriteriosActionForm.getSubcategoria().trim().equalsIgnoreCase("")){
			 
			 FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
			 filtroSubcategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID, imovelOutrosCriteriosActionForm.getSubcategoria()));
			 filtroSubcategoria.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);
			 collectionImovelSubcategoria = fachada.pesquisar(filtroSubcategoria, Subcategoria.class.getName() );
			 
			 Subcategoria subcategoria = collectionImovelSubcategoria.iterator().next();
			 FiltroCategoria filtroCategoria = new FiltroCategoria();
			 
			 if(parametroGerarRelatorio.trim().equalsIgnoreCase("RelatorioTarifaSocial")){
				 filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO,
						 Categoria.RESIDENCIAL));
			 }
			 filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
			 
			 if(subcategoria.getCategoria() != null){
			 	 filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO, subcategoria.getCategoria().getId()));
			 	 imovelOutrosCriteriosActionForm.setCategoriaImovel("" + subcategoria.getCategoria().getId());
			 }
			 collectionImovelCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName() );
			 
			 filtroSubcategoria.limparListaParametros();
			 filtroSubcategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.CATEGORIA_ID, imovelOutrosCriteriosActionForm.getCategoriaImovel()));
			 filtroSubcategoria.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);
			 collectionImovelSubcategoria = fachada.pesquisar(filtroSubcategoria, Subcategoria.class.getName() );
			 
			 sessao.setAttribute("collectionImovelSubcategoria", collectionImovelSubcategoria);
			 
			 sessao.setAttribute("collectionImovelCategoria", collectionImovelCategoria);
			 
		 }else{
			 FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();	
			 filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
			 
			 if(parametroGerarRelatorio.trim().equalsIgnoreCase("RelatorioTarifaSocial")){
				 filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, ImovelPerfil.TARIFA_SOCIAL));
			 }
			 
			 collectionImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName() );
			 if(collectionImovelPerfil == null || collectionImovelPerfil.isEmpty()){
					throw new ActionServletException("atencao.naocadastrado", null,
					"Perfil do Imóvel");			
			 }				 
			 
			 
			 FiltroCategoria filtroCategoria = new FiltroCategoria();
			 filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
			 
			 if(parametroGerarRelatorio.trim().equalsIgnoreCase("RelatorioTarifaSocial")){
				 filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO,
						 Categoria.RESIDENCIAL));
			 }
			 collectionImovelCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName() );
			 if(collectionImovelCategoria == null || collectionImovelCategoria.isEmpty()){
					throw new ActionServletException("atencao.naocadastrado", null,
					"Categoria");			
			 }				 
			 
			 FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
			 filtroSubcategoria.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);
			 
			 if(parametroGerarRelatorio.trim().equalsIgnoreCase("RelatorioTarifaSocial")){
				 filtroSubcategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.CATEGORIA_ID,
						 Categoria.RESIDENCIAL));
			 }
			 collectionImovelSubcategoria = fachada.pesquisar(filtroSubcategoria, Subcategoria.class.getName() );		 
			 if(collectionImovelSubcategoria == null || collectionImovelSubcategoria.isEmpty()){
					throw new ActionServletException("atencao.naocadastrado", null,
					"Subcategoria");			
			 }				 
			 
			 FiltroAreaConstruidaFaixa filtroAreaConstruidaFaixa = new FiltroAreaConstruidaFaixa();
			 filtroAreaConstruidaFaixa.setCampoOrderBy(FiltroAreaConstruidaFaixa.CODIGO);
			 collectionAreaConstuidaFaixa = fachada.pesquisar(filtroAreaConstruidaFaixa, AreaConstruidaFaixa.class.getName() );
			 if(collectionAreaConstuidaFaixa == null || collectionAreaConstuidaFaixa.isEmpty()){
					throw new ActionServletException("atencao.naocadastrado", null,
					"Intervalo de Área Construída");			
			 }			 
			 
			 FiltroPocoTipo filtroPocoTipo = new FiltroPocoTipo();
			 collectionTipoPoco = fachada.pesquisar(filtroPocoTipo, PocoTipo.class.getName() );
			 if(collectionTipoPoco == null || collectionTipoPoco.isEmpty()){
					throw new ActionServletException("atencao.naocadastrado", null,
					"Tipo de Poço");			
			 }				 
			 
			 sessao.setAttribute("collectionImovelPerfil", collectionImovelPerfil);
			 sessao.setAttribute("collectionImovelCategoria", collectionImovelCategoria);
			 sessao.setAttribute("collectionImovelSubcategoria", collectionImovelSubcategoria);
			 sessao.setAttribute("collectionAreaConstuidaFaixa", collectionAreaConstuidaFaixa);
			 sessao.setAttribute("collectionTipoPoco", collectionTipoPoco);
		 }
		 		 
		return retorno;
	}

}
