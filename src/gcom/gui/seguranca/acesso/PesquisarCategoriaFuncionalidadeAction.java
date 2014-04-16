package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidadeCategoria;
import gcom.seguranca.acesso.FuncionalidadeCategoria;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Fernando Fontelles
 * 
 * @created 21/08/2009
 */
public class PesquisarCategoriaFuncionalidadeAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("listaCategoriaFuncionalidadeResultado");
		
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		

		PesquisarCategoriaFuncionalidadeActionForm pesquisarCategoriaFuncionalidadeActionForm 
			= (PesquisarCategoriaFuncionalidadeActionForm) actionForm;

		// Recupera os parâmetros do form
		
		String descricao = pesquisarCategoriaFuncionalidadeActionForm.getDescricao();
		String categoriaSuperior = pesquisarCategoriaFuncionalidadeActionForm.getIdCategoriaSuperior();
		String tipoPesquisa = pesquisarCategoriaFuncionalidadeActionForm.getTipoPesquisa();
		String modulo = pesquisarCategoriaFuncionalidadeActionForm.getIdModulo();
		String indicadorUso = pesquisarCategoriaFuncionalidadeActionForm.getIndicadorUso();
		
		// filtro para a pesquisa da unidade organizacional
		
		FiltroFuncionalidadeCategoria filtroFuncionalidadeCategoria = new FiltroFuncionalidadeCategoria();

		boolean peloMenosUmParametroInformado = false;

		// Insere os parâmetros informados no filtro
		if (descricao != null && !descricao.equalsIgnoreCase("")) {
			
			peloMenosUmParametroInformado = true;
			
			if (tipoPesquisa != null && 
				tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				
				filtroFuncionalidadeCategoria.adicionarParametro(
					new ComparacaoTextoCompleto(FiltroFuncionalidadeCategoria.DESCRICAO_MENU_ITEM, descricao));
			} else {
				filtroFuncionalidadeCategoria.adicionarParametro(
					new ComparacaoTexto(FiltroFuncionalidadeCategoria.DESCRICAO_MENU_ITEM, descricao));
			}
		}	

		if (categoriaSuperior != null && !categoriaSuperior.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroFuncionalidadeCategoria.adicionarParametro(
					new ParametroSimples(FiltroFuncionalidadeCategoria.MENU_ITEM_SUPERIOR, categoriaSuperior));
		}

		/*
		if (modulo != null && !modulo.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroFuncionalidadeCategoria.adicionarParametro(
					new ParametroSimples(FiltroFuncionalidadeCategoria.MODULO_ID, modulo));
		}
		*/
		
		// Verifica se o campo modulo foi informado

		if (modulo != null
				&& !modulo.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			filtroFuncionalidadeCategoria.adicionarParametro(new ParametroSimples(
					FiltroFuncionalidadeCategoria.MODULO_ID, modulo));

		}
		
		if (indicadorUso !=null && !indicadorUso.equalsIgnoreCase( "" )){
			filtroFuncionalidadeCategoria.adicionarParametro( new ParametroSimples
					(FiltroFuncionalidadeCategoria.INDICADOR_USO, indicadorUso ) );
        	peloMenosUmParametroInformado = true;
        }
		
		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// adiciona as dependências para serem mostradas na página
		
		filtroFuncionalidadeCategoria.adicionarCaminhoParaCarregamentoEntidade("modulo");
		filtroFuncionalidadeCategoria.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeCategoriaSuperior");
		
		 //Collection colecaoCategoria = null;
		
		//Faz a busca das Categorias das Funcionalidades
        Collection colecaoCategoria = Fachada.getInstancia()
        	.pesquisar(filtroFuncionalidadeCategoria, FuncionalidadeCategoria.class.getName());
		
		// Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroFuncionalidadeCategoria, FuncionalidadeCategoria.class.getName());
		
		colecaoCategoria = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		// Coloca a coleção na sessão			
		sessao.setAttribute("colecaoCategoria",colecaoCategoria);
		
		if(resultado.isEmpty()){
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "categoria funcionalidade");
		}
		
		return retorno;
	}

}
