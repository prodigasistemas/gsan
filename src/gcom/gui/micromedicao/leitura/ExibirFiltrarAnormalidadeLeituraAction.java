package gcom.gui.micromedicao.leitura;

import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeLeitura;
import gcom.micromedicao.leitura.LeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.LeituraAnormalidadeLeitura;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de pesquisa de cliente
 * 
 * @author Thiago Tenório
 * @created 25 de Abril de 2005
 */
public class ExibirFiltrarAnormalidadeLeituraAction extends GcomAction {
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

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		FiltrarAnormalidadeLeituraActionForm filtrarAnormalidadeLeituraActionForm = (FiltrarAnormalidadeLeituraActionForm) actionForm;
		//	Código para checar ou não o ATUALIZAR
		
		/*if (httpServletRequest.getParameter("menu") != null) {
			filtrarAnormalidadeLeituraActionForm.setDescricao("");
			filtrarAnormalidadeLeituraActionForm
					.setIndicadorRelativoHidrometro("");
			filtrarAnormalidadeLeituraActionForm
					.setIndicadorImovelSemHidrometro("");
			filtrarAnormalidadeLeituraActionForm.setUsoRestritoSistema("");
			filtrarAnormalidadeLeituraActionForm.setPerdaTarifaSocial("");
			filtrarAnormalidadeLeituraActionForm.setOsAutomatico("");
			filtrarAnormalidadeLeituraActionForm.setTipoServico("");
			filtrarAnormalidadeLeituraActionForm
					.setConsumoLeituraNaoInformado("");
			filtrarAnormalidadeLeituraActionForm.setConsumoLeituraInformado("");
			filtrarAnormalidadeLeituraActionForm.setLeituraLeituraInformado("");
			filtrarAnormalidadeLeituraActionForm
					.setLeituraLeituraNaoturaInformado("");

		}
*/
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarAnormalidadeLeituraActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}		

		if (filtrarAnormalidadeLeituraActionForm.getTipoServico() == null
				|| filtrarAnormalidadeLeituraActionForm.getTipoServico().equals("")) {
			Collection colecaoPesquisa = null;

			FiltroTipoServico filtroTipoServico = new FiltroTipoServico();

			filtroTipoServico.setCampoOrderBy(FiltroTipoServico.DESCRICAO);

			filtroTipoServico.adicionarParametro(new ParametroSimples(
					FiltroTipoServico.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna banco
			colecaoPesquisa = fachada.pesquisar(filtroTipoServico, ServicoTipo.class
					.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Nenhum registro na tabela localidade_porte foi encontrado
				throw new ActionServletException(
						"atencao.pesquisa.nenhum_registro_tabela", null,
						"Tipo de Servico");
			} else {
				sessao.setAttribute("colecaoTipoServico", colecaoPesquisa);
			}

		} 

		FiltroLeituraAnormalidadeConsumo filtroLeituraAnormalidadeConsumo = new FiltroLeituraAnormalidadeConsumo();
		filtroLeituraAnormalidadeConsumo
				.setCampoOrderBy(FiltroLeituraAnormalidadeConsumo.ID);

		Collection colecaoLeituraAnormalidadeConsumo = fachada.pesquisar(
				filtroLeituraAnormalidadeConsumo,
				LeituraAnormalidadeConsumo.class.getName());
		sessao.setAttribute("colecaoLeituraAnormalidadeConsumo",
				colecaoLeituraAnormalidadeConsumo);
		
		//coleção anormalidade leitura

		FiltroLeituraAnormalidadeLeitura filtroLeituraAnormalidadeLeitura = new FiltroLeituraAnormalidadeLeitura();
		filtroLeituraAnormalidadeLeitura
				.setCampoOrderBy(FiltroLeituraAnormalidadeLeitura.ID);

		Collection colecaoLeituraAnormalidadeLeitura = fachada.pesquisar(
				filtroLeituraAnormalidadeLeitura,
				LeituraAnormalidadeLeitura.class.getName());
		sessao.setAttribute("colecaoLeituraAnormalidadeLeitura",
				colecaoLeituraAnormalidadeLeitura);

		//	Se voltou da tela de Atualizar Sistema de Esgoto
		if (sessao.getAttribute("voltar") != null && sessao.getAttribute("voltar").equals("filtrar")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			if(sessao.getAttribute("tipoPesquisa") != null){
				filtrarAnormalidadeLeituraActionForm.setTipoPesquisa(sessao.getAttribute("tipoPesquisa").toString());
			}
		}
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("filtrarAnormalidadeLeitura");

		return retorno;

	}
}
