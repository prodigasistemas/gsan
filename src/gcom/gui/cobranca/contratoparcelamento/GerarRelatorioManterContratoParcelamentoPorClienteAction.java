package gcom.gui.cobranca.contratoparcelamento;

import gcom.cobranca.contratoparcelamento.ContratoParcelamentoCliente;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamentoCliente;
import gcom.fachada.Fachada;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.contratoparcelamento.RelatorioManterContratoParcelamentoPorCliente;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioManterContratoParcelamentoPorClienteAction extends
ExibidorProcessamentoTarefaRelatorio{

	
	/**
	 * 
	 * 
	 * [UC1137] 
	 * 
	 * 
	 * @author 
	 * @date 
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
		
		TarefaRelatorio relatorio = null;
		
		
		FiltroContratoParcelamentoCliente filtroContratoParcelamentoCliente = (FiltroContratoParcelamentoCliente) sessao.getAttribute("filtroContratoParcelamentoCliente");
		Collection<ContratoParcelamentoCliente> collContratoParcelamentoCliente = fachada.pesquisar(filtroContratoParcelamentoCliente, ContratoParcelamentoCliente.class.getName());
		
		relatorio = new RelatorioManterContratoParcelamentoPorCliente(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));
		
		relatorio.addParametro("collContratoParcelamentoCliente", collContratoParcelamentoCliente);
		relatorio.addParametro("filtroContratoParcelamentoCliente", filtroContratoParcelamentoCliente);
		relatorio.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		
		
		
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);
		
		
		return retorno;
	}
}
