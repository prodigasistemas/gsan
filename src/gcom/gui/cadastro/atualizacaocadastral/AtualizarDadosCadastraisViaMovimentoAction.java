package gcom.gui.cadastro.atualizacaocadastral;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarDadosCadastraisViaMovimentoAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws Exception {

    	ActionForward retorno = actionMapping.findForward("telaSucesso");
    	
    	FiltrarAlteracaoAtualizacaoCadastralActionForm form = (FiltrarAlteracaoAtualizacaoCadastralActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
    	
		if (!form.getIdRegistrosAutorizados().equals("")) {
			String registrosAutorizados = form.getIdRegistrosAutorizados();
			
			String[] listaIdRegistrosSim = registrosAutorizados.split(",");
			for (int i = 0; i < listaIdRegistrosSim.length; i++) {
				Integer idRegistro = new Integer(listaIdRegistrosSim[i]);
				try {
					fachada.atualizarIndicadorAutorizacaoTabelaAtualizacaoCadastral(idRegistro, ConstantesSistema.SIM);
				} catch (Exception e) {
					throw new Exception("erro.sistema", e);
				}
			}
		}
			
		if (!form.getIdRegistrosNaoAutorizados().equals("")) {
			String registrosNaoAutorizados = form.getIdRegistrosNaoAutorizados();
			
			String[] listaIdRegistrosNao = registrosNaoAutorizados.split(",");
			for (int i = 0; i < listaIdRegistrosNao.length; i++) {
				Integer idRegistroNao = new Integer(listaIdRegistrosNao[i]);
				try {
					fachada.atualizarIndicadorAutorizacaoTabelaAtualizacaoCadastral(idRegistroNao, ConstantesSistema.NAO);
				} catch (Exception e) {
					throw new Exception("erro.sistema", e);
				}
			}
		}
    	
    	montarPaginaSucesso(httpServletRequest, "Registro atualizado com sucesso.",
				"Realizar outra atualização nos registros",
				"filtrarAlteracaoAtualizacaoCadastralAction.do");
    	
    	
    	return retorno; 
	}
}
