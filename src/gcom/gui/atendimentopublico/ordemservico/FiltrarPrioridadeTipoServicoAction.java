package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipoPrioridade;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroSistemaEsgoto;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;

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
public class FiltrarPrioridadeTipoServicoAction extends GcomAction {

	/**
	 * Este caso de uso permite Filtrar uma Prioridade do Tipo de Serviço
	 * 
	 * [UC00540] Filtrar Prioridade do Tipo de Serviço
	 * 
	 * 
	 *
	 * @author Thiago Tenório
	 * @date 18/01/2007
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
				.findForward("exibirManterPrioridadeTipoServico");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarPrioridadeTipoServicoActionForm filtrarPrioridadeTipoServicoActionForm = (FiltrarPrioridadeTipoServicoActionForm) actionForm;

		FiltroServicoTipoPrioridade filtroServicoTipoPrioridade = new FiltroServicoTipoPrioridade();

		// Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String codigo = filtrarPrioridadeTipoServicoActionForm.getCodigo();

		String descricao = filtrarPrioridadeTipoServicoActionForm
				.getDescricao();

		String abreviatura = filtrarPrioridadeTipoServicoActionForm
				.getAbreviatura();

		String qtdHorasInicio = filtrarPrioridadeTipoServicoActionForm
				.getQtdHorasInicio();

		String qtdHorasFim = filtrarPrioridadeTipoServicoActionForm
				.getQtdHorasFim();
		
		String tipoPesquisa = filtrarPrioridadeTipoServicoActionForm
				.getTipoPesquisa();				
		 

		// Verifica se o campo Código da Prioridade foi Informado

		if (codigo != null && !codigo.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroServicoTipoPrioridade.adicionarParametro(new ComparacaoTexto(
					FiltroServicoTipoPrioridade.ID, codigo));

		}

		// Descrição do Sistema de Esgoto
		if (descricao != null && !descricao.equalsIgnoreCase("")) {
			
			peloMenosUmParametroInformado = true;
			
			if (tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				
				filtroServicoTipoPrioridade.adicionarParametro(new ComparacaoTextoCompleto(FiltroSistemaEsgoto.DESCRICAO, 
						descricao));
			} else {
				
				filtroServicoTipoPrioridade.adicionarParametro(new ComparacaoTexto(FiltroSistemaEsgoto.DESCRICAO,
						descricao));
			}
		}
		
		// Verifica se o campo descrição abreviatura foi informado

		if (abreviatura != null && !abreviatura.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroServicoTipoPrioridade.adicionarParametro(new ComparacaoTexto(
					FiltroServicoTipoPrioridade.DESCRICAO_ABREVIADA,
					abreviatura));

		}

		// Verifica se o campo Horas para execução do servico foi informado

		if (qtdHorasInicio != null
				&& !qtdHorasInicio.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroServicoTipoPrioridade.adicionarParametro(new ComparacaoTexto(
					FiltroServicoTipoPrioridade.PRAZO_EXECUCAO_INICIO,
					qtdHorasInicio));

		}

		// Verifica se o campo Horas para Finalização do servico foi informado

		if (qtdHorasFim != null && !qtdHorasFim.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroServicoTipoPrioridade
					.adicionarParametro(new ComparacaoTexto(
							FiltroServicoTipoPrioridade.PRAZO_EXECUCAO_FIM,
							qtdHorasFim));

		}

		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		if (filtrarPrioridadeTipoServicoActionForm.getIndicadorAtualizar() != null	
				&& filtrarPrioridadeTipoServicoActionForm.getIndicadorAtualizar().equalsIgnoreCase("1")) {
				
			httpServletRequest.setAttribute("atualizar",filtrarPrioridadeTipoServicoActionForm.getIndicadorAtualizar());
	
		}

//		filtroServicoTipoPrioridade
//				.adicionarCaminhoParaCarregamentoEntidade("servicoTipoPrioridade");

		sessao.setAttribute("filtroPrioridadeTipoServico",
				filtroServicoTipoPrioridade);

		return retorno;
	}

}
