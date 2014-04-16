package gcom.gui.batch;

import gcom.batch.FiltroProcessoTipo;
import gcom.batch.ProcessoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0780] Pesquisar Processo
 * 
 * @date 11/07/2008
 * @author Arthur Carvalho
 */
public class ExibirPesquisarProcessoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirPesquisarProcesso");

		Fachada fachada = Fachada.getInstancia();
		
		PesquisarProcessoActionForm pesquisarProcessoActionForm = (PesquisarProcessoActionForm) actionForm;
		
		if (httpServletRequest.getParameter("limparForm") != null && !httpServletRequest.getParameter("limparForm").equals("")) {
			pesquisarProcessoActionForm.setDescricao("");
			pesquisarProcessoActionForm.setDescricaoAbreviada("");
			pesquisarProcessoActionForm.setIdProcessoTipo("");
			
		}
		
//		[FS0001] Verificar Existencia de dados - Pesquisa do tipo de processo
		FiltroProcessoTipo filtroProcessoTipo = new FiltroProcessoTipo();
		
	//	filtroProcesso.adicionarParametro(new ParametroSimples(FiltroProcesso.PROCESSO_TIPO, ""));
		
		Collection colecaoProcessoTipo = (Collection)
			fachada.pesquisar(filtroProcessoTipo, ProcessoTipo.class.getName());
		
		if (colecaoProcessoTipo == null || colecaoProcessoTipo.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "Tipo de Processo");
		}
		
		httpServletRequest.setAttribute("colecaoProcessoTipo", colecaoProcessoTipo);
		
		pesquisarProcessoActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());

		return retorno;

	}

}
