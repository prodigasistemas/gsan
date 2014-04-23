package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class GerarArquivosImoveisNaoEnviadosAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
	
			ActionForward retorno = actionMapping
					.findForward("telaSucesso");
			
			ConsultarArquivoTextoLeituraActionForm consultarArquivoTextoLeituraActionForm = (ConsultarArquivoTextoLeituraActionForm) actionForm;
			
			String[] ids = consultarArquivoTextoLeituraActionForm.getIdsRegistros();
			
			Fachada fachada = Fachada.getInstancia(); 
			
			fachada.gerarArquivoImoveisNaoEnviados( ids );
			
			montarPaginaSucesso(httpServletRequest,
					"Arquivo texto dos imóveis não enviados gerados com sucesso.",
					"Realizar outra Manutenção de Arquivo Texto para Leitura",
					"consultarArquivoTextoLeituraAction.do");
			
	
			return retorno;
	}
}
