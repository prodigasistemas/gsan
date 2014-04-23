package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.bean.GerarNumeracaoRAManualHelper;
import gcom.fachada.Fachada;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioNumeracaoRAManual;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Esta classe tem por finalidade receber os dados para geração da relação numérica
 * 
 * @author Raphael Rossiter
 * @date 06/11/2006
 */
public class GerarNumeracaoManualRegistroAtendimentoAction extends ExibidorProcessamentoTarefaRelatorio {
	
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
		ActionForward retorno = null;

		GerarNumeracaoManualRegistroAtendimentoActionForm form = 
			(GerarNumeracaoManualRegistroAtendimentoActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		GerarNumeracaoRAManualHelper gerarNumeracaoRAManualHelper = fachada.GerarNumeracaoRAManual(Util.converterStringParaInteger(form.getQuantidade()), 
				Util.converterStringParaInteger(form.getIdUnidadeOrganizacional()));

		RelatorioNumeracaoRAManual relatorioNumeracaoRAManual = new RelatorioNumeracaoRAManual(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioNumeracaoRAManual.addParametro(
				"gerarNumeracaoRAManualHelper", gerarNumeracaoRAManualHelper);
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioNumeracaoRAManual.addParametro(
				"tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(
				relatorioNumeracaoRAManual, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}
	
}
