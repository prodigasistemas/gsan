package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialExclusaoMotivo;
import gcom.cadastro.tarifasocial.bean.TarifaSocialHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author rodrigo
 */
public class ExibirManterTarifaSocialDadosMultiplasEconomiasAction extends
		GcomAction {

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

		ActionForward retorno = actionMapping
				.findForward("manterTarifaSocialMultiplasEconomias");

		// Instancia da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		// Pega uma instancia do actionform
		ManterTarifaSocialActionForm manterTarifaSocialActionForm = (ManterTarifaSocialActionForm) actionForm;

		String idImovel = manterTarifaSocialActionForm.getIdImovel();

		ArrayList colecaoTarifaSocialHelper = null;

		if (sessao.getAttribute("atualizar") != null) {
			colecaoTarifaSocialHelper = (ArrayList) sessao
					.getAttribute("colecaoTarifaSocialHelper");
			sessao.removeAttribute("atualizar");
		} else {
			colecaoTarifaSocialHelper = (ArrayList) fachada
					.pesquisarDadosClienteEconomiaTarifaSocial(new Integer(
							idImovel));

			if (colecaoTarifaSocialHelper != null
					&& !colecaoTarifaSocialHelper.isEmpty()) {

				sessao.setAttribute("colecaoTarifaSocialHelper",
						colecaoTarifaSocialHelper);

			}else{
				
				throw new ActionServletException("atencao.imovel.economias_nao_cadastradas");
				
			}

			String idTarifaSocial = httpServletRequest
					.getParameter("idTarifaSocial");

			if (idTarifaSocial != null && !idTarifaSocial.equals("")) {
				Collection colecaoTarifaSocialExcluida = (Collection) sessao
						.getAttribute("colecaoTarifaSocialExcluida");

				boolean removeu = false;

				if (colecaoTarifaSocialExcluida != null
						&& !colecaoTarifaSocialExcluida.isEmpty()) {

					Iterator colecaoTarifaSocialExcluidaIterator = colecaoTarifaSocialExcluida
							.iterator();

					while (colecaoTarifaSocialExcluidaIterator.hasNext()) {

						TarifaSocialDadoEconomia tarifaSocialDadoEconomia = (TarifaSocialDadoEconomia) colecaoTarifaSocialExcluidaIterator
								.next();

						if (tarifaSocialDadoEconomia.getId().toString().equals(
								idTarifaSocial)) {
							colecaoTarifaSocialExcluida
									.remove(tarifaSocialDadoEconomia);
							removeu = true;
							break;
						}

					}

				}

				if (!removeu) {
					
					if (sessao.getAttribute("pesquisaImovel") != null) {
						throw new ActionServletException("atencao.nao_permitido.recadastramento.tarifa_social.sem.ra");
					}

					Collection colecaoTarifasSociaisRecadastradas = null;

					if (sessao
							.getAttribute("colecaoTarifasSociaisRecadastradas") != null) {
						colecaoTarifasSociaisRecadastradas = (Collection) sessao
								.getAttribute("colecaoTarifasSociaisRecadastradas");
					} else {
						colecaoTarifasSociaisRecadastradas = new ArrayList();
					}

					TarifaSocialDadoEconomia tarifaSocialDadoEconomia = fachada
							.pesquisarTarifaSocial(new Integer(idTarifaSocial));
					
					if (tarifaSocialDadoEconomia.getTarifaSocialExclusaoMotivo().getIndicadorPermiteRecadastramentoImovel().equals(TarifaSocialExclusaoMotivo.INDICADOR_PERMITE_RECADASTRAMENTO_NAO)) {
						throw new ActionServletException("atencao.motivo_exclusao_imovel_economia_nao_permite_recadastramento");
					}
					
					tarifaSocialDadoEconomia.setDataExclusao(null);
					tarifaSocialDadoEconomia
							.setTarifaSocialExclusaoMotivo(null);
					int qtdeRecadastramentos = 1;

					if (tarifaSocialDadoEconomia.getQuantidadeRecadastramento() != null) {
						qtdeRecadastramentos = tarifaSocialDadoEconomia
								.getQuantidadeRecadastramento().intValue() + 1;
					}

					tarifaSocialDadoEconomia
							.setQuantidadeRecadastramento(new Short(""
									+ qtdeRecadastramentos));

					int i = 0;

					Iterator colecaoTarifaSocialHelperIterator = colecaoTarifaSocialHelper
							.iterator();

					while (colecaoTarifaSocialHelperIterator.hasNext()) {

						TarifaSocialHelper tarifaSocialHelper = (TarifaSocialHelper) colecaoTarifaSocialHelperIterator
								.next();

						if (tarifaSocialHelper.getTarifaSocialDadoEconomia()
								.getId().equals(
										tarifaSocialDadoEconomia.getId())) {
							Integer imovelEconomia = null;
							
								if(tarifaSocialDadoEconomia.getImovelEconomia() != null){
									imovelEconomia = tarifaSocialDadoEconomia.getImovelEconomia().getId();
								}
							
							Cliente cliente = tarifaSocialHelper.getClienteImovelEconomia().getCliente();
							
							Collection colecaoImovel = fachada.verificarClienteCadastradoManterTarifaSocialMultiplasEconomias(cliente.getId(), imovelEconomia);
							
							if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

								Imovel imovel = (Imovel) colecaoImovel.iterator()
										.next();

								throw new ActionServletException(
										"atencao.usuario.ja_cadastrado_tarifasocial.sem.encerramento",
										null, imovel.getId().toString());

							}
								
							fachada.verificarClienteMotivoExclusaoRecadastramento(cliente.getId());
							
							Collection colecaoClientesUsuarioExistenteTarifaSocial = fachada
							.pesquisarClientesUsuarioExistenteTarifaSocial(cliente
									.getId());
							
							// Caso o usuário esteja na tarifa social em outro imóvel com motivo de revisão 
							if (colecaoClientesUsuarioExistenteTarifaSocial != null && !colecaoClientesUsuarioExistenteTarifaSocial.isEmpty()) {
								httpServletRequest.setAttribute(
										"abrirPopupExclusao", true);
								TarifaSocialDadoEconomia tarifaSocialDadoEconomiaImovelAnterior = (TarifaSocialDadoEconomia) Util
										.retonarObjetoDeColecao(colecaoClientesUsuarioExistenteTarifaSocial);
								sessao
										.setAttribute(
												"tarifaSocialDadoEconomiaImovelAnterior",
												tarifaSocialDadoEconomiaImovelAnterior);
								sessao.setAttribute("tarifaSocialRecadastrar", tarifaSocialDadoEconomia);
								sessao.setAttribute("clienteTarifaSocialImovelAnterior", cliente);
							} else {
								tarifaSocialHelper
									.setTarifaSocialDadoEconomia(tarifaSocialDadoEconomia);
								colecaoTarifaSocialHelper
									.set(i, tarifaSocialHelper);
								
								colecaoTarifasSociaisRecadastradas
									.add(tarifaSocialDadoEconomia);

								sessao.setAttribute("colecaoTarifasSociaisRecadastradas",
										colecaoTarifasSociaisRecadastradas);
							}
							
							break;

						}
						
						i++;

					}

				}

			}

		}

		return retorno;
	}

}
