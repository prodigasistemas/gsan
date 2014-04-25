package gcom.gui.seguranca.acesso;



import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidadeCategoria;
import gcom.seguranca.acesso.FiltroModulo;
import gcom.seguranca.acesso.FuncionalidadeCategoria;
import gcom.seguranca.acesso.Modulo;
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

/**
 * Action que define o pré-processamento da página de pesquisa Categoria da Funcionalidade
 * 
 * @author Fernando Fontelles
 * @created 21/08/2009
 */
public class ExibirPesquisarCategoriaFuncionalidadeAction extends GcomAction {
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
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		Fachada fachada = Fachada.getInstancia();
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirPesquisarCategoriaFuncionalidade");

		
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarCategoriaFuncionalidadeActionForm pesquisarCategoriaFuncionalidadeActionForm = 
				(PesquisarCategoriaFuncionalidadeActionForm) actionForm;

		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
		
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("2")) {

				// Faz a consulta de Categoria
				pesquisarCategoriaFuncionalidade(httpServletRequest, retorno,pesquisarCategoriaFuncionalidadeActionForm);

		}
		
		FiltroModulo filtroModulo = new FiltroModulo();

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		Collection<Modulo> colecaoModulo = fachada.pesquisar(filtroModulo,
				Modulo.class.getName());

		if (colecaoModulo == null || colecaoModulo.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Modulo");
		}
		
		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaCategoriaFuncionalidade") != null) {
			
			sessao.setAttribute("caminhoRetornoTelaPesquisaCategoriaFuncionalidade",
				httpServletRequest.getParameter("caminhoRetornoTelaPesquisaCategoriaFuncionalidade"));
			
		}
		
		if (httpServletRequest.getParameter("idCampoEnviarDados") != null && 
				!httpServletRequest.getParameter("idCampoEnviarDados").equals("")) {
			
			pesquisarCategoriaFuncionalidadeActionForm.setIdCategoriaSuperior(httpServletRequest.getParameter("idCampoEnviarDados"));
			pesquisarCategoriaFuncionalidadeActionForm.setDescricaoCategoriaSuperior(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			
		}
		
		if(httpServletRequest.getParameter("limparForm") != null){
			pesquisarCategoriaFuncionalidadeActionForm.setDescricao("");
			pesquisarCategoriaFuncionalidadeActionForm.setIdCategoriaSuperior("");
			pesquisarCategoriaFuncionalidadeActionForm.setDescricaoCategoriaSuperior("");
			pesquisarCategoriaFuncionalidadeActionForm.setIdModulo("");
			pesquisarCategoriaFuncionalidadeActionForm.setIndicadorUso("");
		}
		
		this.setaRequest(httpServletRequest,pesquisarCategoriaFuncionalidadeActionForm);
		
		if(pesquisarCategoriaFuncionalidadeActionForm.getTipoPesquisa() == null){
			pesquisarCategoriaFuncionalidadeActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		}
		
		httpServletRequest.setAttribute("colecaoModulo", colecaoModulo);
		
		return retorno;
	}
	
	private void pesquisarCategoriaFuncionalidade(
			HttpServletRequest httpServletRequest, ActionForward retorno,
			PesquisarCategoriaFuncionalidadeActionForm pesquisarCategoriaFuncionalidadeActionForm) {
		
		//Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();
		
		// Filtro para obter Categorida da Funcionalidade ativo de id informado
		FiltroFuncionalidadeCategoria filtroCategoriaFuncionalidade = new FiltroFuncionalidadeCategoria();

		String idCategoria = pesquisarCategoriaFuncionalidadeActionForm.getIdCategoriaSuperior();
		
		filtroCategoriaFuncionalidade.adicionarParametro(
			new ParametroSimples(FiltroFuncionalidadeCategoria.ID , idCategoria));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoCategoria = fachada.pesquisar(filtroCategoriaFuncionalidade,FuncionalidadeCategoria.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoCategoria != null && !colecaoCategoria.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			FuncionalidadeCategoria categoriaFuncionalidade = 
				(FuncionalidadeCategoria) Util.retonarObjetoDeColecao(colecaoCategoria);

			// Exibe o código e a descrição pesquisa na página
			httpServletRequest.setAttribute("idCategoriaEncontrada","true");
			
			pesquisarCategoriaFuncionalidadeActionForm.setIdCategoriaSuperior(categoriaFuncionalidade.getId().toString());
			pesquisarCategoriaFuncionalidadeActionForm.setDescricaoCategoriaSuperior(categoriaFuncionalidade.getNome());
			

		} else {

			pesquisarCategoriaFuncionalidadeActionForm.setDescricaoCategoriaSuperior("FUNCIONALIDADE CATEGORIA INEXISTENTE");
			pesquisarCategoriaFuncionalidadeActionForm.setIdCategoriaSuperior("");

		}
	}
	
	private void setaRequest(HttpServletRequest httpServletRequest,
			PesquisarCategoriaFuncionalidadeActionForm pesquisarCategoriaFuncionalidadeActionForm){

		// Categoria Superior
		if(pesquisarCategoriaFuncionalidadeActionForm.getIdCategoriaSuperior() != null && 
			!pesquisarCategoriaFuncionalidadeActionForm.getIdCategoriaSuperior().equals("") && 
			pesquisarCategoriaFuncionalidadeActionForm.getDescricaoCategoriaSuperior() != null && 
			!pesquisarCategoriaFuncionalidadeActionForm.getDescricaoCategoriaSuperior().equals("")){
					
			httpServletRequest.setAttribute("idCategoriaEncontrada","true");
		}
		
	}

	
	
}
