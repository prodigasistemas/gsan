package gcom.gui.cobranca;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarArquivoTextoPagamentosContasCobrancaEmpresaAction extends
		GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

        
        GerarArquivoTextoPagamentosContasCobrancaEmpresaActionForm form = (GerarArquivoTextoPagamentosContasCobrancaEmpresaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		

		Integer idEmpresa = new Integer(form.getIdEmpresa());
		Integer referenciaInicial = Util.formatarMesAnoComBarraParaAnoMes(form.getReferenciaInicial());
		Integer referenciaFinal =  Util.formatarMesAnoComBarraParaAnoMes(form.getReferenciaFinal());
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		
		fachada.inserirProcessoIniciadoPagamentosContasCobranca(idEmpresa,referenciaInicial,referenciaFinal,usuarioLogado);
		
		//montando página de sucesso
		montarPaginaSucesso(httpServletRequest,
			"Arquivo Texto Pagamentos Contas Cobrança por Empresa Enviado para Processamento", 
			"Voltar",
			"exibirGerarArquivoTextoPagamentosContasCobrancaEmpresaAction.do");

	
		return retorno;
	}
}
