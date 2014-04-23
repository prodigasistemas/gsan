package gcom.gui.cadastro.imovel;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.FiltroImovelSituacaoTipo;
import gcom.cadastro.imovel.ImovelSituacaoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 26/03/2006
 */
public class ExibirInserirImovelSituacaoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma nova situação do imóvel
	 * 
	 * [UC0224] Inserir Situação do Imovel
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 24/03/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("situacaoImovelInserir");

		Fachada fachada = Fachada.getInstancia();

		FiltroImovelSituacaoTipo filtroImovelSituacaoTipo = new FiltroImovelSituacaoTipo();

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao.
		// Seta a colecao no request

		Collection<ImovelSituacaoTipo> collectionImovelSituacaoTipo = fachada
				.pesquisar(filtroImovelSituacaoTipo, ImovelSituacaoTipo.class
						.getName());
		if (collectionImovelSituacaoTipo == null
				|| collectionImovelSituacaoTipo.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Imóvel Situação Tipo");
		}
		httpServletRequest.setAttribute("collectionImovelSituacaoTipo",
				collectionImovelSituacaoTipo);

		// Parte referente ao combo Situação da Ligação de Água

		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();

		filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
				FiltroLigacaoAguaSituacao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<LigacaoAguaSituacao> collectionLigacaoAguaSituacao = fachada
				.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class
						.getName());

		if (collectionLigacaoAguaSituacao == null
				|| collectionLigacaoAguaSituacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Ligação Água Situação");
		}
		httpServletRequest.setAttribute("collectionLigacaoAguaSituacao",
				collectionLigacaoAguaSituacao);

		// Parte referente ao combo Situação da Ligação de Esgoto

		FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();

		filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
				FiltroLigacaoEsgotoSituacao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<LigacaoEsgotoSituacao> collectionLigacaoEsgotoSituacao = fachada
				.pesquisar(filtroLigacaoEsgotoSituacao,
						LigacaoEsgotoSituacao.class.getName());

		if (collectionLigacaoEsgotoSituacao == null
				|| collectionLigacaoEsgotoSituacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Ligação Esgoto Situação");

		}

		httpServletRequest.setAttribute("collectionLigacaoEsgotoSituacao",
				collectionLigacaoEsgotoSituacao);

		return retorno;
	}

}
