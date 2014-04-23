package gcom.gui.atendimentopublico.ligacaoagua;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;


/**
 * @author Vinícius de Melo Medeiros
 * @created 14/05/2008
 */
public class ExibirInserirLigacaoAguaSituacaoAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("inserirLigacaoAguaSituacao");

		InserirLigacaoAguaSituacaoActionForm inserirLigacaoAguaSituacaoActionForm = (InserirLigacaoAguaSituacaoActionForm) actionForm;

		inserirLigacaoAguaSituacaoActionForm.setIndicadorAbastecimento("" + ConstantesSistema.INDICADOR_USO_ATIVO);
		inserirLigacaoAguaSituacaoActionForm.setIndicadorExistenciaLigacao("" + ConstantesSistema.INDICADOR_USO_ATIVO);
		inserirLigacaoAguaSituacaoActionForm.setIndicadorExistenciaRede("" + ConstantesSistema.INDICADOR_USO_ATIVO);
		inserirLigacaoAguaSituacaoActionForm.setIndicadorAguaAtiva("" + ConstantesSistema.INDICADOR_USO_ATIVO);
		inserirLigacaoAguaSituacaoActionForm.setIndicadorAguaCadastrada("" + ConstantesSistema.INDICADOR_USO_ATIVO);
		inserirLigacaoAguaSituacaoActionForm.setIndicadorAguaDesligada("" + ConstantesSistema.INDICADOR_USO_ATIVO);
		inserirLigacaoAguaSituacaoActionForm.setIndicadorAnalizeAgua("" + ConstantesSistema.INDICADOR_USO_ATIVO);
		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirLigacaoAguaSituacaoActionForm.setDescricao("");
			inserirLigacaoAguaSituacaoActionForm.setDescricaoAbreviado("");
			inserirLigacaoAguaSituacaoActionForm.setConsumoMinimoFaturamento("");
			inserirLigacaoAguaSituacaoActionForm.setIndicadorExistenciaLigacao("");
			inserirLigacaoAguaSituacaoActionForm.setIndicadorExistenciaRede("");
			inserirLigacaoAguaSituacaoActionForm.setIndicadorFaturamentoSituacao("");
			inserirLigacaoAguaSituacaoActionForm.setIndicadorAbastecimento("");
			inserirLigacaoAguaSituacaoActionForm.setIndicadorAguaAtiva("");
			inserirLigacaoAguaSituacaoActionForm.setIndicadorAguaCadastrada("");
			inserirLigacaoAguaSituacaoActionForm.setIndicadorAguaDesligada("");
			inserirLigacaoAguaSituacaoActionForm.setIndicadorAnalizeAgua("");
			
			if (inserirLigacaoAguaSituacaoActionForm.getDescricao() == null
					|| inserirLigacaoAguaSituacaoActionForm.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;

				FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();

				filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);

				colecaoPesquisa = fachada.pesquisar(filtroLigacaoAguaSituacao,
						LigacaoAguaSituacao.class.getName());

				// Verifica se há registros na tabela
				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"Situação de Ligação de Água");
				} else {
					sessao.setAttribute("colecaoLigacaoAguaSituacao", colecaoPesquisa);
				}

				// Coleção de Situacao de Ligacao de Agua
				FiltroLigacaoAguaSituacao filtroLigAguaSituacao = new FiltroLigacaoAguaSituacao();
				filtroLigAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.ID);

				Collection colecaoLigAguaSituacao = fachada.pesquisar(filtroLigAguaSituacao,
						LigacaoEsgotoSituacao.class.getName());
				sessao.setAttribute("colecaoLigAguaSituacao", colecaoLigAguaSituacao);

			}

		}
		return retorno;
	}
}
