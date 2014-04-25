package gcom.gui;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Responsável por executar a ação desejada
 * 
 * @author thiago toscano
 * @date 21/12/2005
 */
public abstract class ControladorGcomAction extends GcomAction {

	public static final String PARAMETRO_ACAO = "acao";

	public static final String PARAMETRO_ACAO_EXIBIR = "exibir";

	public static final String PARAMETRO_ACAO_PROCESSAR = "processar";
	
	public static final String FORWARD_EXIBIR = "exibir";
	
	public static final String FORWARD_EXIBIR_REMOVER = "exibirRemover";
	
	public static final String FORWARD_REMOVER = "remover";

	public static final String FORWARD_PROCESSAR = "processar";

	public static final String FORWARD_CONCLUIR = "concluir";

	public static final String FORWARD_TELA_SUCESSO = "telaSucesso";

	public static final String FORWARD_POPUP = "popup";
	
	public static final String PARAMETRO_COLL_OBJETO = "collObjeto";
	
	public static final String PARAMETRO_FORMULARIO = "formulario";
	
	public static final String PARAMETRO_SEPARADO_CHAVE_PRIMARIA = ";";

	/**
	 * Reemplementação do Método para validar a passagem do parametro acao
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return selecionarExibir(actionMapping, actionForm, request, response);
	}

	/**
	 * Redireciona pra tela de exibicao
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, ServletRequest request, ServletResponse response) throws Exception {
		return selecionarExibir(actionMapping, actionForm, request, response);
	}

	/**
	 * Redireciona pra tela de exibicao
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */	
	private ActionForward selecionarExibir(ActionMapping actionMapping, ActionForm actionForm, ServletRequest request, ServletResponse response) throws Exception {
		String acao = request.getParameter(PARAMETRO_ACAO);
		ActionForward retorno = null;
		if (acao == null || "".equals(acao)) {
			retorno = this.exibir(actionMapping, actionForm, (HttpServletRequest) request, (HttpServletResponse) response);
		} else {
			retorno = super.execute(actionMapping, actionForm, (HttpServletRequest) request, (HttpServletResponse) response);
		}
		return retorno;
	}

	/**
	 * Encaminha pra tela de sucesso
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */	
	public ActionForward telaSucesso(ActionMapping actionMapping, HttpServletRequest request, String mensagemPaginaSucesso, String labelPaginaSucesso, String caminhoFuncionalidade) throws Exception {

		request.setAttribute("caminhoFuncionalidade",caminhoFuncionalidade);
		request.setAttribute("labelPaginaSucesso",labelPaginaSucesso);
		request.setAttribute("mensagemPaginaSucesso",mensagemPaginaSucesso);

		return actionMapping.findForward(ControladorGcomAction.FORWARD_TELA_SUCESSO);
	}

	
	public ControladorGcomActionForm getControladorGcomActionForm(HttpServletRequest request, String nomeForm){
		return (ControladorGcomActionForm) getSessao(request).getAttribute(nomeForm);
	}

	/**
	 * Método que responde pela ação de exibição
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public abstract ActionForward exibir(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	/**
	 * Método que responde pela ação de processamento 
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public abstract ActionForward processar(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception;

	
	
	
	
//	/**
//	 * Método responsável por preencher o formulario de apresentação a partir do objeto selecionado  
//	 * 
//	 * @param obj
//	 * @param actionForm
//	 */
//	public abstract void montarFormulario(Object obj, ControladorGcomActionForm actionForm) ;
//
//	/**
//	 * Método responsável por montar o filtro que ser utilizado pela consulta 
//	 * 
//	 * @param actionForm
//	 * @return
//	 */
//	public abstract Filtro gerarFiltro(ControladorGcomActionForm actionForm);
//	
//	/**
//	 * Método que gera o objeto para a manipulacao no sistema 
//	 *  
//	 * @param actionForm
//	 * @return
//	 */
//	public abstract Object gerarObject(ControladorGcomActionForm actionForm);
//	
//	/**
//	 * Método que carrega a colecao para a apresentação dos dados.
//	 * 
//	 * @param actionForm
//	 */
//	public abstract void carregarColecao(ControladorGcomActionForm actionForm);
}
