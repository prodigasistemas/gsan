package gcom.gui.cobranca;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CancelarDocumentosCobrancaAction extends GcomAction {
	
	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuarioLogado");
		ActionForward retorno = mapping.findForward("telaSucesso");
		Fachada fachada = Fachada.getInstancia();
		
		String msg = "";
		String caminho = "";
		
		CancelarDocumentosCobrancaActionForm form = (CancelarDocumentosCobrancaActionForm) actionForm;
		if (form.getIdCobrancaAcaoAtividadeCronograma() != null && !form.getIdCobrancaAcaoAtividadeCronograma().equals("")) {
			Integer idCobrancaAcaoAtividadeCronograma = new Integer(form.getIdCobrancaAcaoAtividadeCronograma());
			fachada.cancelarDocumentosCobrancaDoCronogramaOuEventual(usuario, idCobrancaAcaoAtividadeCronograma, null);
			
			msg = "Documentos de Cobrança do Cronograma " 
				+ idCobrancaAcaoAtividadeCronograma + " cancelados com sucesso.";
			caminho = "exibirComandosAcaoCobrancaCronogramaDadosComandoAction.do?idCobrancaAcaoAtividadeCronograma=" + idCobrancaAcaoAtividadeCronograma;
		}
		if (form.getIdCobrancaAcaoAtividadeComando() != null && !form.getIdCobrancaAcaoAtividadeComando().equals("")) {
			Integer idCobrancaAcaoAtividadeComando = new Integer(form.getIdCobrancaAcaoAtividadeComando());
			fachada.cancelarDocumentosCobrancaDoCronogramaOuEventual(usuario, null, idCobrancaAcaoAtividadeComando);
			
			msg = "Documentos de Cobrança do Comando " 
				+ idCobrancaAcaoAtividadeComando + " cancelados com sucesso."; 
			caminho = "exibirComandosAcaoCobrancaEventualDadosComandoAction.do?idCobrancaAcaoAtividadeEventual=" + idCobrancaAcaoAtividadeComando;
		}
		
		montarPaginaSucesso(request, msg, "Voltar", caminho);
		return retorno;
	}

}
