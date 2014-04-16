package gcom.gui.faturamento.conta;

import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMensagem;
import gcom.faturamento.conta.FiltroContaMensagem;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltrarMensagemContaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		FiltrarMensagemContaActionForm filtrarMensagemContaActionForm = (FiltrarMensagemContaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		// Variavel para testar se o campo naum obrigatorio esta vazio

		String referenciaFaturamento = filtrarMensagemContaActionForm
				.getReferenciaFaturamento();
		String atualizar = httpServletRequest.getParameter("atualizar");
		String mensagemConta01 = filtrarMensagemContaActionForm
				.getMensagemConta01();
/*		String mensagemConta02 = filtrarMensagemContaActionForm
			.getMensagemConta02();
		String mensagemConta03 = filtrarMensagemContaActionForm
			.getMensagemConta03();
*/		
		String grupoFaturamento = filtrarMensagemContaActionForm
				.getGrupoFaturamentoHidden();
		String gerenciaRegional = filtrarMensagemContaActionForm
				.getGerenciaRegionalHidden();
		String localidade = filtrarMensagemContaActionForm.getLocalidade();
		String setorComercial = filtrarMensagemContaActionForm
				.getSetorComercial();
		String quadra = filtrarMensagemContaActionForm.getQuadra();

		if ((referenciaFaturamento == null || referenciaFaturamento
				.equalsIgnoreCase(""))
				&& (grupoFaturamento == null || grupoFaturamento
						.equalsIgnoreCase(""))
				&& (mensagemConta01 == null || mensagemConta01.equalsIgnoreCase(""))
				&& (gerenciaRegional == null || gerenciaRegional
						.equalsIgnoreCase(""))
				&& (localidade == null || localidade.equalsIgnoreCase(""))
				&& (setorComercial == null || setorComercial
						.equalsIgnoreCase(""))) {

			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		FiltroContaMensagem filtroContaMensagem = new FiltroContaMensagem();

		if (referenciaFaturamento != null
				&& !referenciaFaturamento.equalsIgnoreCase("")) {
			if (Util.validarAnoMes(referenciaFaturamento)) {
				throw new ActionServletException("atencao.ano_mes_invalido");
			} else {
				Integer mes = new Integer(referenciaFaturamento.substring(0, 2));
				Integer ano = new Integer(referenciaFaturamento.substring(3, 7));

				if (mes <= 0 || mes > 12) {
					throw new ActionServletException("atencao.ano_mes_invalido");
				}

				if (ano < 1900) {
					throw new ActionServletException("atencao.ano_mes_invalido");
				}

				if (referenciaFaturamento != null
						&& !referenciaFaturamento.equalsIgnoreCase("")) {
					Integer referenciaFaturametoTratado = new Integer(
							Util
									.formatarMesAnoParaAnoMesSemBarra(referenciaFaturamento));

					filtroContaMensagem
							.adicionarParametro(new ParametroSimples(
									FiltroContaMensagem.ANO_MES_REFERECIA_FATURAMENTO,
									referenciaFaturametoTratado));
				}
			}
		}

		if (grupoFaturamento != null && !grupoFaturamento.equalsIgnoreCase("")) {
			filtroContaMensagem
					.adicionarParametro(new ParametroSimples(
							FiltroContaMensagem.GRUPO_FATURAMENTO_ID,
							grupoFaturamento));

		}

		if (mensagemConta01 != null && !mensagemConta01.equalsIgnoreCase("")) {
			filtroContaMensagem.adicionarParametro(new ComparacaoTexto(
					FiltroContaMensagem.MENSAGEM_CONTA_01, mensagemConta01));
		}
		
/*		if (mensagemConta02 != null && !mensagemConta02.equalsIgnoreCase("")) {
			filtroContaMensagem.adicionarParametro(new ComparacaoTexto(
					FiltroContaMensagem.MENSAGEM_CONTA_02, mensagemConta02));
		}
		
		if (mensagemConta01 != null && !mensagemConta01.equalsIgnoreCase("")) {
			filtroContaMensagem.adicionarParametro(new ComparacaoTexto(
					FiltroContaMensagem.MENSAGEM_CONTA_03, mensagemConta03));
		}
*/		

		if (gerenciaRegional != null && !gerenciaRegional.equalsIgnoreCase("")) {
			filtroContaMensagem
					.adicionarParametro(new ParametroSimples(
							FiltroContaMensagem.GERENCIA_REGIONAL_ID,
							gerenciaRegional));

		}

		if (localidade != null && !localidade.equalsIgnoreCase("")) {
			filtroContaMensagem.adicionarParametro(new ParametroSimples(
					FiltroContaMensagem.LOCALIDADE_ID, localidade));

		}

		if (setorComercial != null && !setorComercial.equalsIgnoreCase("")) {
			filtroContaMensagem.adicionarParametro(new ParametroSimples(
					FiltroContaMensagem.SETOR_COMERCIAL_CD, setorComercial));

		}
		
		if (quadra != null && !quadra.equalsIgnoreCase("")) {
			filtroContaMensagem.adicionarParametro(new ParametroSimples(
					FiltroContaMensagem.NUMERO_QUADRA, quadra));
		}
		filtroContaMensagem
				.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
		filtroContaMensagem
				.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
		filtroContaMensagem
				.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroContaMensagem
				.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroContaMensagem
				.adicionarCaminhoParaCarregamentoEntidade("quadra");
		
		filtroContaMensagem.setCampoOrderBy(FiltroContaMensagem.ANO_MES_REFERECIA_FATURAMENTO);
		
		Collection<ContaMensagem> colecaoContaMensagem = (Collection<ContaMensagem>) fachada
				.pesquisar(filtroContaMensagem, ContaMensagem.class.getName());
		sessao.setAttribute("colecaoContaMensagem", colecaoContaMensagem);

		ActionForward retorno = null;

		if (colecaoContaMensagem != null && !colecaoContaMensagem.isEmpty()) {
			if (colecaoContaMensagem.size() == 1 && atualizar != null) {
				httpServletRequest.setAttribute("contaMensagemID",
						colecaoContaMensagem.iterator().next().getId());
				retorno = actionMapping
						.findForward("exibirAtualizarMensagemConta");
			} else {
				retorno = actionMapping
						.findForward("exibirManterMensagemConta");
			}
		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		
		if (sessao.getAttribute("filtroContaMensagem") != null){
			sessao.removeAttribute("filtroContaMensagem");
		}
		
		sessao.setAttribute("filtroContaMensagem", filtroContaMensagem);
		
		return retorno;
	}
	
	
	
}
