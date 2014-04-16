package gcom.gui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Responsável por executar a ação de alteraçao
 * 
 * @author thiago toscano
 * @date 21/12/2005
 */
public abstract class ControladorAlteracaoGcomAction extends ControladorGcomAction {

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
	public final ActionForward exibir(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ControladorAlteracaoGcomActionForm form = (ControladorAlteracaoGcomActionForm) actionForm; 

		String[] chavesPrimarias = form.getChavePrimaria().split(ControladorGcomAction.PARAMETRO_SEPARADO_CHAVE_PRIMARIA);
		
		Object obj = this.consultarObjetoSistema(chavesPrimarias, request, form);
		
		this.montarFormulario(obj, form);
		
		ActionForward forward = this.exibirAuxiliar(actionMapping, actionForm, request, response);

		this.carregarColecao(form);

		form.setAcao(ControladorGcomAction.PARAMETRO_ACAO_PROCESSAR);

		if (forward != null) {
			return forward;
		}
		return actionMapping.findForward(ControladorGcomAction.FORWARD_EXIBIR);
	}

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
	public final ActionForward processar(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception{

		ControladorAlteracaoGcomActionForm form = (ControladorAlteracaoGcomActionForm) actionForm; 

		Object obj = gerarObject(form,request);

		this.atualizarObjeto(obj, request, form);
		
		ActionForward forward = this.processarAuxiliar(actionMapping, actionForm, request, response);
		
		if (forward != null) {
			return forward;
		}
		return actionMapping.findForward(ControladorGcomAction.FORWARD_PROCESSAR);
	}

	/**
	 * Método que auxiliar ao método exibir 
	 *
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exibirAuxiliar(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}

	/**
	 * Método que auxiliar ao método processar 
	 *
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward processarAuxiliar(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}

	/**
	 * Método que consulta o Objeto no sistema com o array de chaves necessaria
	 * 
	 * @param chavesPrimarias
	 * @return
	 * @throws Exception
	 */	
	public abstract Object consultarObjetoSistema(String[] chavesPrimarias, HttpServletRequest request, ControladorAlteracaoGcomActionForm actionForm) throws Exception;

	/**
	 * Método que atualiza o objeto no sistema
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public abstract void atualizarObjeto(Object obj, HttpServletRequest request, ControladorAlteracaoGcomActionForm actionForm) throws Exception;

	/**
	 * Método responsável por preencher o formulario de apresentação a partir do objeto selecionado  
	 * 
	 * @param obj
	 * @param actionForm
	 */
	public abstract void montarFormulario(Object obj, ControladorAlteracaoGcomActionForm actionForm) ;
	
	/**
	 * Método que gera o objeto para a manipulacao no sistema 
	 *  
	 * @param actionForm
	 * @return
	 */
	public abstract Object gerarObject(ControladorAlteracaoGcomActionForm actionForm, HttpServletRequest request);
	
	/**
	 * Método que carrega a colecao para a apresentação dos dados.
	 * 
	 * @param actionForm
	 */
	public abstract void carregarColecao(ControladorAlteracaoGcomActionForm actionForm);
}
