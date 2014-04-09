package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.ImovelSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 24/03/2006
 */

public class InserirImovelSituacaoAction extends GcomAction {

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

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		InserirImovelSituacaoActionForm inserirImovelSituacaoActionForm = (InserirImovelSituacaoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		// Recebe os dados do ActionForm e envia para o metodo
		// inserirSituacaoImovel do controlador

		String idImovelSituacaoTipo = inserirImovelSituacaoActionForm
				.getImovelSituacaoTipo();
		String idLigacaoAguaSituacao = inserirImovelSituacaoActionForm
				.getLigacaoAguaSituacao();
		String idLigacaoEsgotoSituacao = inserirImovelSituacaoActionForm
				.getLigacaoEsgotoSituacao();

		ImovelSituacao imovelSituacao = new ImovelSituacao();

		imovelSituacao.setUltimaAlteracao(new Date());

		/*Integer idImovelSituacao = (Integer)*/ fachada.inserirSituacaoImovel(
				idImovelSituacaoTipo, idLigacaoAguaSituacao,
				idLigacaoEsgotoSituacao);

		// Monta a Pagina de sucesso

		montarPaginaSucesso(httpServletRequest,
				"Situação de imóvel inserida com sucesso.",
				"Inserir outra situação",
				"exibirInserirImovelSituacaoAction.do?menu=sim",
				"exibirFiltrarImovelSituacaoAction.do?menu=sim",
				"Consultar outra Situação de Imóvel");

		return retorno;

	}
}
