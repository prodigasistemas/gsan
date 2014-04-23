package gcom.gui.cadastro.imovel;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.FiltroImovelSituacaoAguaLigacao;
import gcom.cadastro.imovel.FiltroImovelSituacaoEsgotoLigacao;
import gcom.cadastro.imovel.FiltroImovelSituacaoTipo;
import gcom.cadastro.imovel.ImovelSituacaoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0037] Filtrar Situação do Imóvel Tem o objetivo de filtrar as situacoes de
 * imovel existentes
 * 
 * @author Rômulo Aurélio
 * @date 31/03/2006
 * 
 * @param actionMapping
 * @param actionForm
 * @param httpServletRequest
 * @param httpServletResponse
 * @return
 */
public class ExibirFiltrarImovelSituacaoAction extends GcomAction {

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
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("situacaoImovelFiltrar");

		Fachada fachada = Fachada.getInstancia();

		// Pesquisa os dados do filtro, joga na sessao e seta os dados da colecao no request
		
		FiltroImovelSituacaoTipo filtroImovelSituacaoTipo = new FiltroImovelSituacaoTipo();

		Collection<ImovelSituacaoTipo> colecaoImovelSituacaoTipo = fachada
				.pesquisar(filtroImovelSituacaoTipo, ImovelSituacaoTipo.class
						.getName());

		if (colecaoImovelSituacaoTipo.isEmpty()) {
			throw new ActionServletException("Atencao.Sem_dados_para_selecao");
		}
		httpServletRequest.setAttribute("colecaoImovelSituacaoTipo",
				colecaoImovelSituacaoTipo);

		FiltroImovelSituacaoAguaLigacao filtroImovelSituacaoAguaLigacao = new FiltroImovelSituacaoAguaLigacao();

		Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = fachada
				.pesquisar(filtroImovelSituacaoAguaLigacao,
						LigacaoAguaSituacao.class.getName());

		if (colecaoLigacaoAguaSituacao.isEmpty()) {
			throw new ActionServletException("Atencao.Sem_dados_para_selecao");
		}

		httpServletRequest.setAttribute("colecaoLigacaoAguaSituacao",
				colecaoLigacaoAguaSituacao);

		FiltroImovelSituacaoEsgotoLigacao filtroImovelSituacaoEsgotoLigacao = new FiltroImovelSituacaoEsgotoLigacao();

		Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao = fachada
				.pesquisar(filtroImovelSituacaoEsgotoLigacao,
						LigacaoEsgotoSituacao.class.getName());

		if (colecaoLigacaoEsgotoSituacao.isEmpty()) {
			throw new ActionServletException("Atencao.Sem_dados_para_selecao");
		}

		httpServletRequest.setAttribute("colecaoLigacaoEsgotoSituacao",
				colecaoLigacaoEsgotoSituacao);

		return retorno;

	}
}
