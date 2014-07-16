package gcom.gui.relatorio.cadastro.atualizacaocadastral;

import gcom.cadastro.FiltroSituacaoAtualizacaoCadastral;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirGerarRelatorioImoveisSituacaoPeriodoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioImoveisSituacaoPeriodo");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		RelatorioImoveisSituacaoPeriodoActionForm form = (RelatorioImoveisSituacaoPeriodoActionForm) actionForm;
		
		FiltroSituacaoAtualizacaoCadastral filtro = new FiltroSituacaoAtualizacaoCadastral();
		filtro.setCampoOrderBy(FiltroSituacaoAtualizacaoCadastral.DESCRICAO);
		filtro.adicionarParametro(new ParametroSimples(FiltroSituacaoAtualizacaoCadastral.INDICADORUSO, ConstantesSistema.SIM));
		Collection colecaoSituacoes = Fachada.getInstancia().pesquisar(filtro, SituacaoAtualizacaoCadastral.class.getName());
		
		sessao.setAttribute("colecaoSituacoes", colecaoSituacoes);
		
		return retorno;
	}
}
