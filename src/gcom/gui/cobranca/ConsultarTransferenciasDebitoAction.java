package gcom.gui.cobranca;

import gcom.cobranca.bean.ConsultarTransferenciasDebitoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ConsultarTransferenciasDebitoAction extends GcomAction {

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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirTransferenciasDebito");

		ConsultarTransferenciasDebitoActionForm consultarTransferenciasDebitoActionForm = (ConsultarTransferenciasDebitoActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		String idImovelOrigem = consultarTransferenciasDebitoActionForm.getIdImovelOrigem();
		String idImovelDestino = consultarTransferenciasDebitoActionForm.getIdImovelDestino();
		String dataInicio = consultarTransferenciasDebitoActionForm.getDataInicio();
		String dataFim = consultarTransferenciasDebitoActionForm.getDataFim();
		String idUsuario = consultarTransferenciasDebitoActionForm.getIdUsuario();
		String loginUsuario = consultarTransferenciasDebitoActionForm.getLoginUsuario();
		
		if (dataInicio != null && !dataInicio.trim().equals("")
				&& dataFim != null && !dataFim.trim().equals("")) {
			if ((Util.converteStringParaDate(dataInicio)).compareTo(Util
					.converteStringParaDate(dataFim)) > 0) {
				throw new ActionServletException(
						"atencao.data.intervalo.invalido");
			}
		}
		
		ConsultarTransferenciasDebitoHelper consultarTransferenciasDebitoHelper = new ConsultarTransferenciasDebitoHelper();

		boolean peloMenosUmParametroInformado = false;

		if (idImovelOrigem != null && !idImovelOrigem.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			
			String inscricao = fachada.pesquisarInscricaoImovel(new Integer(idImovelOrigem));
			
			if (inscricao == null || inscricao.trim().equals("")) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Imóvel Origem");
			}
			
			consultarTransferenciasDebitoHelper.setIdImovelOrigem(new Integer(idImovelOrigem));
			
		}
		
		if (idImovelDestino != null && !idImovelDestino.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			
			String inscricao = fachada.pesquisarInscricaoImovel(new Integer(idImovelDestino));
			
			if (inscricao == null || inscricao.trim().equals("")) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Imóvel Destino");
			}
			
			consultarTransferenciasDebitoHelper.setIdImovelDestino(new Integer(idImovelDestino));
			
		}

		// Data Inicial da Transferência
		if (dataInicio != null && !dataInicio.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			
			consultarTransferenciasDebitoHelper.setDataInicial(Util.converteStringParaDate(dataInicio));
		}

		// Data Final da Transferência
		if (dataFim != null && !dataFim.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			GregorianCalendar dataFinal = new GregorianCalendar();
			dataFinal.setTime(Util.converteStringParaDate(dataFim));
			dataFinal.set(GregorianCalendar.HOUR_OF_DAY, 23);
			dataFinal.set(GregorianCalendar.MINUTE, 59);
			dataFinal.set(GregorianCalendar.SECOND, 59);
			dataFinal.set(GregorianCalendar.MILLISECOND, 999);
			
			consultarTransferenciasDebitoHelper.setDataFinal(dataFinal.getTime());
			
		}
		
		// Usuário
		if ((loginUsuario != null && !loginUsuario.trim().equals("")) || (idUsuario != null && !idUsuario.trim().equals(""))) {
			peloMenosUmParametroInformado = true;
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			
			if ((loginUsuario != null && !loginUsuario.trim().equals(""))) {
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, loginUsuario));
			} else {
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, idUsuario));
			}
			
			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
				
				consultarTransferenciasDebitoHelper.setIdUsuario(usuario.getId());
			} else {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Usuário");
			}
			
		} else {
			consultarTransferenciasDebitoActionForm.setNomeUsuario("");
		}
		
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		// Pesquisa as coleções que serão mostradas para o usuário
		Collection colecaoContasTransferidas = fachada.consultarContasTransferidas(consultarTransferenciasDebitoHelper);
		sessao.setAttribute("colecaoContasTransferidas", colecaoContasTransferidas);
		
		Collection colecaoDebitosACobrarTransferidos = fachada.consultarDebitosACobrarTransferidos(consultarTransferenciasDebitoHelper);
		sessao.setAttribute("colecaoDebitosACobrarTransferidos", colecaoDebitosACobrarTransferidos);
		
		Collection colecaoGuiasPagamentoTransferidas = fachada.consultarGuiasDePagamentoTransferidas(consultarTransferenciasDebitoHelper);
		sessao.setAttribute("colecaoGuiasPagamentoTransferidas", colecaoGuiasPagamentoTransferidas);
		
		Collection colecaoCreditosARealizarTransferidos = fachada.consultarCreditosARealizarTransferidos(consultarTransferenciasDebitoHelper);
		sessao.setAttribute("colecaoCreditosARealizarTransferidos", colecaoCreditosARealizarTransferidos);
		
		sessao.setAttribute("consultarTransferenciasDebitoHelper", consultarTransferenciasDebitoHelper);
		
		if ((colecaoContasTransferidas == null || colecaoContasTransferidas.isEmpty()) &&
			(colecaoDebitosACobrarTransferidos == null || colecaoDebitosACobrarTransferidos.isEmpty()) &&
			(colecaoGuiasPagamentoTransferidas == null || colecaoGuiasPagamentoTransferidas.isEmpty()) &&
			(colecaoCreditosARealizarTransferidos == null || colecaoCreditosARealizarTransferidos.isEmpty())) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}
}
