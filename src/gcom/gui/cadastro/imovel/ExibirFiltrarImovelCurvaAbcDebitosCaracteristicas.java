package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.CategoriaTipo;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroCategoriaTipo;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarImovelCurvaAbcDebitosCaracteristicas extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarImovelCurvaAbcDebitosCaracteristicas");
		
		//obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		ImovelCurvaAbcDebitosActionForm imovelCurvaAbcDebitosActionForm = (ImovelCurvaAbcDebitosActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		 
		Collection<ImovelPerfil> collectionImovelPerfil = null;
		Collection<CategoriaTipo> collectionCategoriaTipo = null;
		Collection<Categoria> collectionImovelCategoria= null;
		Collection<Subcategoria> collectionImovelSubcategoria = null;
		 
		Integer idTipoCategoria = 0;
		Integer idCategoria = 0;
		Integer categoria = null;
		 
		if (sessao.getAttribute("idTipoCategoria") != null) {
			idTipoCategoria = Util.converterStringParaInteger(sessao.getAttribute("idTipoCategoria").toString());
		}
		 
		if (sessao.getAttribute("idCategoria") != null) {
			idCategoria = Util.converterStringParaInteger(sessao.getAttribute("idCategoria").toString());
		}
		 
		if (imovelCurvaAbcDebitosActionForm.getCategoria() != null) {
			categoria = new Integer(imovelCurvaAbcDebitosActionForm.getCategoria()[0]);
		}
		 
		if (imovelCurvaAbcDebitosActionForm.getIdTipoCategoria() != null &&
            !imovelCurvaAbcDebitosActionForm.getIdTipoCategoria().trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO) &&
            !imovelCurvaAbcDebitosActionForm.getIdTipoCategoria().trim().equals("") &&
            Util.converterStringParaInteger(imovelCurvaAbcDebitosActionForm.getIdTipoCategoria()) != idTipoCategoria) {
		 
			FiltroCategoria filtroCategoria = new FiltroCategoria();
			filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.TIPO_CATEGORIA,
				 imovelCurvaAbcDebitosActionForm.getIdTipoCategoria()));
			filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
			
			collectionImovelCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName());
			 
			if (idCategoria != 0) {
				FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
				filtroSubcategoria.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);
				
				collectionImovelSubcategoria = fachada.pesquisar(filtroSubcategoria, Subcategoria.class.getName());
				
				sessao.setAttribute("collectionImovelSubcategoria", collectionImovelSubcategoria);
			}
			
			sessao.setAttribute("collectionImovelCategoria", collectionImovelCategoria);
			sessao.setAttribute("idTipoCategoria", imovelCurvaAbcDebitosActionForm.getIdTipoCategoria());
			
		}else if (categoria != null && categoria != idCategoria) {
			FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
			if (!categoria.toString().trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				filtroSubcategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.CATEGORIA_ID, categoria));
			}else {
				filtroSubcategoria.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);
			}
			filtroSubcategoria.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);
			collectionImovelSubcategoria = fachada.pesquisar(filtroSubcategoria, Subcategoria.class.getName());
			
			sessao.setAttribute("collectionImovelSubcategoria", collectionImovelSubcategoria);
			sessao.setAttribute("idCategoria", categoria);
			
		}else {
		
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();	
			filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
			
			collectionImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName() );
			if(collectionImovelPerfil == null || collectionImovelPerfil.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null, "Perfil do Imóvel");			
			}				 
			
			FiltroCategoriaTipo filtroCategoriaTipo = new FiltroCategoriaTipo();
			filtroCategoriaTipo.setCampoOrderBy(FiltroCategoriaTipo.DESCRICAO);
			
			collectionCategoriaTipo = fachada.pesquisar(filtroCategoriaTipo , CategoriaTipo.class.getName());
			if (collectionCategoriaTipo == null || collectionCategoriaTipo.isEmpty()) {
				throw new ActionServletException("atencao.naocadastrado", null, "Tipo da Categoria");
			}
			
			FiltroCategoria filtroCategoria = new FiltroCategoria();
			filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
			
			collectionImovelCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName() );
			if(collectionImovelCategoria == null || collectionImovelCategoria.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null, "Categoria");			
			}				 
			
			FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
			filtroSubcategoria.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);
			
			collectionImovelSubcategoria = fachada.pesquisar(filtroSubcategoria, Subcategoria.class.getName() );		 
			if(collectionImovelSubcategoria == null || collectionImovelSubcategoria.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null, "Subcategoria");			
			}				 
			
			sessao.setAttribute("collectionImovelPerfil", collectionImovelPerfil);
			sessao.setAttribute("collectionCategoriaTipo", collectionCategoriaTipo);
			sessao.setAttribute("collectionImovelCategoria", collectionImovelCategoria);
			sessao.setAttribute("collectionImovelSubcategoria", collectionImovelSubcategoria);
			
			sessao.removeAttribute("idTipoCategoria");
			sessao.removeAttribute("idCategoria");
		}
		
		// Usado para fazer o controle de navegacao por conta da Aba Local 
		sessao.setAttribute("abaAtual", "CARACTERISTICAS");
		
		if (imovelCurvaAbcDebitosActionForm.getClassificacao() != null) {
			httpServletRequest.setAttribute("classificacao", imovelCurvaAbcDebitosActionForm.getClassificacao());
		}
		
		return retorno;
	}
}
