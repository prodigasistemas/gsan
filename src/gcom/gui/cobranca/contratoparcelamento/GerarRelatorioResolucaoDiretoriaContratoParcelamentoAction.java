package gcom.gui.cobranca.contratoparcelamento;

import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamentoRD;
import gcom.fachada.Fachada;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.contratoparcelamento.RelatorioManterContratoParcelamentoRD;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioResolucaoDiretoriaContratoParcelamentoAction extends
ExibidorProcessamentoTarefaRelatorio{

	
	/**
	 * Este caso de uso permite a inclusão de uma resolução de diretoria
	 * 
	 * [UC1134] Manter Resolução de Diretoria (RD) para Contratos de Parcelamento por cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	//Obtém a instância da fachada
	Fachada fachada = Fachada.getInstancia();

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		FiltroContratoParcelamentoRD filtro = (FiltroContratoParcelamentoRD) sessao.getAttribute("filtroContratoParcelamento");
		
		TarefaRelatorio relatorio = null;
		
		relatorio = new RelatorioManterContratoParcelamentoRD(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));
		relatorio.addParametro("filtroContratoParcelamento", filtro);
		relatorio.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);
		
		
		return retorno;
	}
}
