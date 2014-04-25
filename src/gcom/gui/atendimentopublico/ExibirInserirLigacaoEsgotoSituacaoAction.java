package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * @author Vinícius de Melo Medeiros
 * @created 14/05/2008
 */
public class ExibirInserirLigacaoEsgotoSituacaoAction extends GcomAction {
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
		ActionForward retorno = actionMapping.findForward("inserirLigacaoEsgotoSituacao");

		InserirLigacaoEsgotoSituacaoActionForm inserirLigacaoEsgotoSituacaoActionForm = (InserirLigacaoEsgotoSituacaoActionForm) actionForm;

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirLigacaoEsgotoSituacaoActionForm.setDescricao("");
			inserirLigacaoEsgotoSituacaoActionForm.setDescricaoAbreviado("");
			inserirLigacaoEsgotoSituacaoActionForm.setVolumeMinimoFaturamento("");
			inserirLigacaoEsgotoSituacaoActionForm.setIndicadorExistenciaLigacao("");
			inserirLigacaoEsgotoSituacaoActionForm.setIndicadorExistenciaRede("");
			inserirLigacaoEsgotoSituacaoActionForm.setIndicadorFaturamentoSituacao("");
			
			
			if (inserirLigacaoEsgotoSituacaoActionForm.getDescricao() == null
					|| inserirLigacaoEsgotoSituacaoActionForm.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;

				FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();

				filtroLigacaoEsgotoSituacao.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);

				colecaoPesquisa = fachada.pesquisar(filtroLigacaoEsgotoSituacao,
						LigacaoEsgotoSituacao.class.getName());

				// Verifica se há registros na tabela de Situação de Ligação de Água
				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"Situação de Ligação de Esgoto");
				
				} else {
				
					sessao.setAttribute("colecaoLigacaoEsgotoSituacao", colecaoPesquisa);
				
				}

				// Coleção de Situacao de Ligacao de Esgoto
				FiltroLigacaoEsgotoSituacao filtroLigEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
				filtroLigEsgotoSituacao.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.ID);

				Collection colecaoLigEsgotoSituacao = fachada.pesquisar(filtroLigEsgotoSituacao,
						LigacaoEsgotoSituacao.class.getName());
				sessao.setAttribute("colecaoLigEsgotoSituacao", colecaoLigEsgotoSituacao);

			}

		}
		
		return retorno;
	
	}
}
