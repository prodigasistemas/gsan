package gcom.gui.relatorio.arrecadacao.pagamento;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.fachada.Fachada;
import gcom.gui.cadastro.imovel.CategoriaActionForm;
import gcom.gui.faturamento.ManterGuiaPagamentoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.arrecadacao.pagamento.RelatorioEmitirGuiaPagamento;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioEmitirGuiaPagamentoAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		String[] ids = null;

		if (actionForm instanceof ManterGuiaPagamentoActionForm) {
			ManterGuiaPagamentoActionForm manterGuiaPagamentoActionForm = (ManterGuiaPagamentoActionForm) actionForm;
			ids = manterGuiaPagamentoActionForm.getIdRegistrosRemocao();
		} else if (httpServletRequest.getParameter("idGuiaPagamento") != null) {
			ids = new String[1];
			String idGuiaPagamento = (String) httpServletRequest.getParameter("idGuiaPagamento");
			ids[0] = idGuiaPagamento;

		} else if (actionForm instanceof CategoriaActionForm) {

			ids = (String[]) sessao.getAttribute("idGuiaPagamento");

		} else if (sessao.getAttribute("idGuiaPagamento") != null) {
			ids = (String[]) sessao.getAttribute("idGuiaPagamento");
		} else {
			String idParcelamento = (String) sessao.getAttribute("idParcelamento");

			FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
			filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.PARCELAMENTO_ID, new Integer(idParcelamento)));

			Collection collectionGuiaPagamento = fachada.pesquisar(filtroGuiaPagamento, GuiaPagamento.class.getName());
			GuiaPagamento guiaPagamento = (GuiaPagamento) Util.retonarObjetoDeColecao(collectionGuiaPagamento);
			String idGuiaPagamento = "" + guiaPagamento.getId();
			ids = new String[1];
			ids[0] = idGuiaPagamento;
		}

		RelatorioEmitirGuiaPagamento relatorioEmitirGuiaPagamento = new RelatorioEmitirGuiaPagamento((Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

		relatorioEmitirGuiaPagamento.addParametro("ids", ids);
		String tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";

		relatorioEmitirGuiaPagamento.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));

		try {
			retorno = processarExibicaoRelatorio(relatorioEmitirGuiaPagamento, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);

		} catch (RelatorioVazioException ex) {
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		return retorno;
	}
}
