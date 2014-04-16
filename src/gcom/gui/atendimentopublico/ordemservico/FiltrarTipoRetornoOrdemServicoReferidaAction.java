package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroOSReferidaRetornoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Thiago Tenório
 * @date 05/08/2006
 */
public class FiltrarTipoRetornoOrdemServicoReferidaAction extends GcomAction {

	/**
	 * Este caso de uso permite Pesquisar um Tipo de Servicço
	 * 
	 * [UC0437] Pesquisar Tipo de Serviço de Referência
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 31/07/2006
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
				.findForward("exibirManterTipoRetornoOrdemServicoReferida");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarTipoRetornoOrdemServicoReferidaActionForm filtrarTipoRetornoOrdemServicoReferidaActionForm = (FiltrarTipoRetornoOrdemServicoReferidaActionForm) actionForm;

		FiltroOSReferidaRetornoTipo filtroOSReferidaRetornoTipo = new FiltroOSReferidaRetornoTipo();

		// Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String codigoTipoRetorno = filtrarTipoRetornoOrdemServicoReferidaActionForm
				.getCodigoTipoRetorno();

		String descricao = filtrarTipoRetornoOrdemServicoReferidaActionForm
				.getDescricao();
		
		String abreviatura = filtrarTipoRetornoOrdemServicoReferidaActionForm
		.getAbreviatura();
		
		String servicoTipoReferencia = filtrarTipoRetornoOrdemServicoReferidaActionForm
				.getServicoTipoReferencia();
		
		String deferimento = filtrarTipoRetornoOrdemServicoReferidaActionForm
		.getDeferimento();
		
		String trocaServico = filtrarTipoRetornoOrdemServicoReferidaActionForm
		.getTrocaServico();
		
		String situacao = filtrarTipoRetornoOrdemServicoReferidaActionForm
		.getSituacao();
		
		String atendimentoMotivoEncerramento = filtrarTipoRetornoOrdemServicoReferidaActionForm
		.getAtendimentoMotivoEncerramento();
		
		String indicadorUso = filtrarTipoRetornoOrdemServicoReferidaActionForm
		.getIndicadorUso();

		
		// Verifica se o campo Código do Tipo de Retorno

		if (codigoTipoRetorno != null && !codigoTipoRetorno.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroOSReferidaRetornoTipo.adicionarParametro(new ComparacaoTexto(
					FiltroOSReferidaRetornoTipo.ID, codigoTipoRetorno));

		}

		// Verifica se o campo Descrição foi informado

		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroOSReferidaRetornoTipo.adicionarParametro(new ComparacaoTexto(
					FiltroOSReferidaRetornoTipo.DESCRICAO, descricao));

		}

		// Verifica se o campo descrição abreviatura foi informado

		if (abreviatura != null
				&& !abreviatura.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroOSReferidaRetornoTipo.adicionarParametro(new ComparacaoTexto(
					FiltroOSReferidaRetornoTipo.DESCRICAO_ABREVIADA,
					abreviatura));

		}

		// Verifica se o campo Referência do Tipo de Serviço foi informado

		if (servicoTipoReferencia != null
				&& !servicoTipoReferencia.trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroOSReferidaRetornoTipo.adicionarParametro(new ParametroSimples(
					FiltroOSReferidaRetornoTipo.ID_SERVICO_TIPO_REFERENCIA,
					servicoTipoReferencia));

		}


		// Verifica se o campo Indicador de Falta D'Água foi informado

		if (deferimento != null
				&& !deferimento.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroOSReferidaRetornoTipo
					.adicionarParametro(new ParametroSimples(
							FiltroOSReferidaRetornoTipo.INDICADOR_DEFERIMENTO,
							deferimento));

		}
		
		// Verifica se o campo Indicador de Troca de Serviço foi informado

		if (trocaServico != null
				&& !trocaServico.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroOSReferidaRetornoTipo
					.adicionarParametro(new ParametroSimples(
							FiltroOSReferidaRetornoTipo.INDICADOR_TROCA_SERVICO,
							trocaServico));

		}
		
		// Verifica se o campo Código da Situação foi informado

		if (situacao != null
				&& !situacao.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroOSReferidaRetornoTipo
					.adicionarParametro(new ParametroSimples(
							FiltroOSReferidaRetornoTipo.ID,
							situacao));

		}
		
		// Verifica se o campo Motivo de Encerramento do Atendimento foi informado

		if (atendimentoMotivoEncerramento != null
				&& !atendimentoMotivoEncerramento.trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroOSReferidaRetornoTipo.adicionarParametro(new ParametroSimples(
					FiltroOSReferidaRetornoTipo.ATENDIMENTO_MOTIVO_ENCERRAMENTO_ID,
					atendimentoMotivoEncerramento));

		}
		
		// Verifica se o campo Indicador de Uso foi informado

		if (indicadorUso != null
				&& !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroOSReferidaRetornoTipo
					.adicionarParametro(new ParametroSimples(
							FiltroOSReferidaRetornoTipo.INDICADOR_USO,
							indicadorUso));

		}

		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		// Verifica se o checkbox Atualizar está marcado e em caso afirmativo
		// manda pela sessão uma variável para o
		// ExibirManterEquipeAction e nele verificar se irá para o
		// atualizar ou para o manter, caso o checkbox esteja desmarcado remove
		// da sessão
		String indicadorAtualizar = httpServletRequest
				.getParameter("atualizar");

		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			sessao.setAttribute("atualizar", indicadorAtualizar);
		} else {
			sessao.removeAttribute("atualizar");
		}

		filtroOSReferidaRetornoTipo.adicionarCaminhoParaCarregamentoEntidade("servicoTipoReferencia");

		sessao.setAttribute("filtroTipoRetornoOrdemServicoReferida", filtroOSReferidaRetornoTipo);
		


		return retorno;	}

}
