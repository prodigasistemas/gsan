package gcom.gui;

import gcom.util.filtro.Filtro;

import java.util.Collection;

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
public abstract class ControladorConsultaGcomAction extends ControladorGcomAction {

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

		ControladorConsultaGcomActionForm form = (ControladorConsultaGcomActionForm) actionForm; 

		this.carregarColecao(form);
		
		form.setAcao(ControladorGcomAction.PARAMETRO_ACAO_PROCESSAR);

		ActionForward forward = this.exibirAuxiliar(actionMapping, actionForm, request, response);

		if (forward != null) {
			return forward;
		} else {
			return actionMapping.findForward(ControladorGcomAction.FORWARD_EXIBIR);
		}
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

		ControladorConsultaGcomActionForm form = (ControladorConsultaGcomActionForm) actionForm; 

		Filtro filtro = (Filtro) this.gerarFiltro(form);

		Collection objects = this.pesquisarObjetoSistema(filtro);

		form.setCollObjeto(objects);
		
		ActionForward forward = this.processarAuxiliar(actionMapping, actionForm, request, response);
		
		if (forward != null) {
			return forward;
		} else {
			return actionMapping.findForward(ControladorGcomAction.FORWARD_PROCESSAR);
		}
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
	 * Método que gera o filtro a partir do form
	 * 
	 * @param actionForm
	 * @return
	 */
	public abstract Filtro gerarFiltro(ControladorConsultaGcomActionForm actionForm) ;

	/**
	 * Método que consulta o Objeto no sistema com o array de chaves necessaria
	 * 
	 * @param chavesPrimarias
	 * @return
	 * @throws Exception
	 */	
	public abstract Collection pesquisarObjetoSistema(Filtro filtro ) throws Exception;

	/**
	 * Método que carrega a colecao para a apresentação dos dados.
	 * 
	 * @param actionForm
	 */
	public abstract void carregarColecao(ControladorConsultaGcomActionForm actionForm);
}
