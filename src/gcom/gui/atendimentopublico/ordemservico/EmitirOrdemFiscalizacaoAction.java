package gcom.gui.atendimentopublico.ordemservico;

import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.ordemservico.RelatorioOrdemFiscalizacaoOnline;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0983] Emitir Ordem de Fiscalização
 * 
 * Este Caso Uso permite realizar a emissão de Documentos de Ordem de Fiscalização
 * de forma individual para um determinado imóvel.
 * 
 * @author Hugo Amorim
 * @data 08/02/2010
 *
 */
public class EmitirOrdemFiscalizacaoAction extends ExibidorProcessamentoTarefaRelatorio {
	/**
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		EmitirOrdemFiscalizacaoForm form =
			(EmitirOrdemFiscalizacaoForm) actionForm;
		
		
		RelatorioOrdemFiscalizacaoOnline relatorio
			= new RelatorioOrdemFiscalizacaoOnline(usuario);
		
		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		
		
		relatorio.addParametro("form", form);
		relatorio.addParametro("tipoFormatoRelatorio", new Integer(tipoRelatorio));
	
		
		retorno = processarExibicaoRelatorio(relatorio,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);
		
		
		
		return retorno;
	}
}
