package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo;
import gcom.cadastro.cliente.FiltroClienteImovelFimRelacaoMotivo;
import gcom.cadastro.tarifasocial.bean.TarifaSocialHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rafael Corrêa
 * @since 16/01/2007
 */
public class ExibirRemoverClienteImovelTarifaSocialAction extends GcomAction {

	/**
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("remover");

		Fachada fachada = Fachada.getInstancia();

		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarDadosTarifaSocialClienteActionForm atualizarDadosTarifaSocialClienteActionForm = (AtualizarDadosTarifaSocialClienteActionForm) actionForm;

		FiltroClienteImovelFimRelacaoMotivo filtroClienteImovelFimRelacaoMotivo = new FiltroClienteImovelFimRelacaoMotivo();
		filtroClienteImovelFimRelacaoMotivo
				.adicionarParametro(new ParametroSimples(
						FiltroClienteImovelFimRelacaoMotivo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroClienteImovelFimRelacaoMotivo.setCampoOrderBy("descricao");

		Collection colecaoFimRelacaoMotivo = fachada.pesquisar(
				filtroClienteImovelFimRelacaoMotivo,
				ClienteImovelFimRelacaoMotivo.class.getName());

		sessao.setAttribute("colecaoFimRelacaoMotivo", colecaoFimRelacaoMotivo);

		Date dataAtual = new Date();

		atualizarDadosTarifaSocialClienteActionForm.setDataFimRelacao(Util
				.formatarData(dataAtual));
		atualizarDadosTarifaSocialClienteActionForm
				.setClienteImovelFimRelacaoMotivo(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO);

		String[] posicaoRemover = atualizarDadosTarifaSocialClienteActionForm
				.getPosicaoParaRemover();

		Collection colecaoClienteImovelRemover = new ArrayList();
		Collection colecaoClienteImovelEconomiaRemover = new ArrayList();

		Collection colecaoClienteImovel = null;
		Collection colecaoClienteImovelEconomia = null;

		// Uma Economia
		if (sessao.getAttribute("colecaoClienteImovel") != null) {

			colecaoClienteImovel = (Collection) sessao
					.getAttribute("colecaoClienteImovel");

			Iterator colecaoClienteImovelIterator = colecaoClienteImovel
					.iterator();

			int i = 0;

			Collection colecaoRemovidos = new ArrayList();

			boolean existePeloMenosUmNaBase = false;

			while (i < posicaoRemover.length) {
				String posicao = posicaoRemover[i];

				if (posicao != null && !posicao.equals("")) {

					int a = 0;

					while (colecaoClienteImovelIterator.hasNext()) {
						ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovelIterator
								.next();

						if (posicao.equals("" + a)) {

							// Caso o cliente imóvel já exista na base
							if (clienteImovel.getId() != null) {
								existePeloMenosUmNaBase = true;
								retorno = actionMapping.findForward("remover");

								colecaoClienteImovelRemover.add(clienteImovel);
							}
							// Caso ele tenha inserido o cliente nessa sessão e
							// ele
							// ainda não esteja na base
							else {

								if (!existePeloMenosUmNaBase) {
									retorno = actionMapping
											.findForward("exibirDadosCliente");
								}

								TarifaSocialHelper tarifaSocialHelperAtualizar = (TarifaSocialHelper) sessao
										.getAttribute("tarifaSocialHelperAtualizar");

								Collection colecaoClientesInseridos = tarifaSocialHelperAtualizar
										.getColecaoClientesInseridos();

								colecaoClientesInseridos.remove(clienteImovel);
								colecaoRemovidos.add(clienteImovel);

								tarifaSocialHelperAtualizar
										.setColecaoClientesInseridos(colecaoClientesInseridos);
								sessao.setAttribute(
										"tarifaSocialHelperAtualizar",
										tarifaSocialHelperAtualizar);
								sessao.setAttribute("colecaoClienteImovel",
										colecaoClienteImovel);

							}
						}

						a++;
					}

					colecaoClienteImovelIterator = colecaoClienteImovel
							.iterator();
				}

				i++;
			}

			if (colecaoRemovidos != null && !colecaoRemovidos.isEmpty()) {
				colecaoClienteImovel.removeAll(colecaoRemovidos);
				sessao.setAttribute("colecaoClienteImovel",
						colecaoClienteImovel);
			}
			
			sessao.setAttribute("colecaoClienteImovelRemover",
					colecaoClienteImovelRemover);

		} else {
			colecaoClienteImovelEconomia = (Collection) sessao
					.getAttribute("colecaoClienteImovelEconomia");

			Iterator colecaoClienteImovelEconomiaIterator = colecaoClienteImovelEconomia
					.iterator();

			int i = 0;

			Collection colecaoRemovidos = new ArrayList();

			boolean existePeloMenosUmNaBase = false;

			while (i < posicaoRemover.length) {
				String posicao = posicaoRemover[i];

				if (posicao != null && !posicao.equals("")) {

					int a = 0;

					while (colecaoClienteImovelEconomiaIterator.hasNext()) {
						ClienteImovelEconomia clienteImovelEconomia = (ClienteImovelEconomia) colecaoClienteImovelEconomiaIterator
								.next();

						if (posicao.equals("" + a)) {

							// Caso o cliente imóvel já exista na base
							if (clienteImovelEconomia.getId() != null) {
								existePeloMenosUmNaBase = true;
								retorno = actionMapping.findForward("remover");

								colecaoClienteImovelEconomiaRemover.add(clienteImovelEconomia);
							}
							
							// Caso ele tenha inserido o cliente nessa sessão e
							// ele
							// ainda não esteja na base
							else {

								if (!existePeloMenosUmNaBase) {
									retorno = actionMapping
											.findForward("exibirDadosCliente");
								}

								TarifaSocialHelper tarifaSocialHelperAtualizar = (TarifaSocialHelper) sessao
										.getAttribute("tarifaSocialHelperAtualizar");

								Collection colecaoClientesEconomiaInseridos = tarifaSocialHelperAtualizar
										.getColecaoClientesEconomiaInseridos();

								colecaoClientesEconomiaInseridos.remove(clienteImovelEconomia);
								colecaoRemovidos.add(clienteImovelEconomia);

								tarifaSocialHelperAtualizar
										.setColecaoClientesEconomiaInseridos(colecaoClientesEconomiaInseridos);
								sessao.setAttribute(
										"tarifaSocialHelperAtualizar",
										tarifaSocialHelperAtualizar);
								sessao.setAttribute("colecaoClienteImovelEconomia",
										colecaoClienteImovelEconomia);

							}
						}

						a++;
					}

					colecaoClienteImovelEconomiaIterator = colecaoClienteImovelEconomia
							.iterator();
				}

				i++;
			}

			if (colecaoRemovidos != null && !colecaoRemovidos.isEmpty()) {
				colecaoClienteImovelEconomia.removeAll(colecaoRemovidos);
				sessao.setAttribute("colecaoClienteImovelEconomia",
						colecaoClienteImovelEconomia);
			}
			
			sessao.setAttribute("colecaoClienteImovelEconomiaRemover",
					colecaoClienteImovelEconomiaRemover);
		}

		return retorno;

	}

}
