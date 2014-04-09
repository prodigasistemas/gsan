package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FiltroFaturamentoSituacaoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0838]FILTRAR TIPO DE SITUACAO DE FATURAMENTO
 * 
 * @author Arthur Carvalho
 * @date 20/08/08
 */

public class FiltrarFaturamentoSituacaoTipoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterFaturamentoSituacaoTipo");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarFaturamentoSituacaoTipoActionForm filtrarFaturamentoSituacaoTipoActionForm = (FiltrarFaturamentoSituacaoTipoActionForm) actionForm;

		FiltroFaturamentoSituacaoTipo filtroFaturamentoSituacaoTipo = new FiltroFaturamentoSituacaoTipo();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarFaturamentoSituacaoTipoActionForm.getId();
		String descricao = filtrarFaturamentoSituacaoTipoActionForm.getDescricao();
		String indicadorParalisacaoFaturamento = filtrarFaturamentoSituacaoTipoActionForm.getIndicadorParalisacaoFaturamento();
		String indicadorParalisacaoLeitura = filtrarFaturamentoSituacaoTipoActionForm.getIndicadorParalisacaoLeitura();
		String indicadorValidoAgua = filtrarFaturamentoSituacaoTipoActionForm.getIndicadorValidoAgua();
		String indicadorValidoEsgoto = filtrarFaturamentoSituacaoTipoActionForm.getIndicadorValidoEsgoto();
		String indicadorInformarConsumo = filtrarFaturamentoSituacaoTipoActionForm.getIndicadorInformarConsumo();
		String indicadorInformarVolume = filtrarFaturamentoSituacaoTipoActionForm.getIndicadorInformarVolume();
		String leituraAnormalidadeLeituraComLeitura = filtrarFaturamentoSituacaoTipoActionForm.getLeituraAnormalidadeLeituraComLeitura();
		String leituraAnormalidadeLeituraSemLeitura = filtrarFaturamentoSituacaoTipoActionForm.getLeituraAnormalidadeLeituraSemLeitura();
		String leituraAnormalidadeConsumoComLeitura = filtrarFaturamentoSituacaoTipoActionForm.getLeituraAnormalidadeConsumoComLeitura();
		String leituraAnormalidadeConsumoSemLeitura = filtrarFaturamentoSituacaoTipoActionForm.getLeituraAnormalidadeConsumoSemLeitura();
		String indicadorUso = filtrarFaturamentoSituacaoTipoActionForm.getIndicadorUso();
		String tipoPesquisa = filtrarFaturamentoSituacaoTipoActionForm.getTipoPesquisa();

		// Indicador Atualizar
		String indicadorAtualizar = httpServletRequest
				.getParameter("indicadorAtualizar");

		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {
			sessao.removeAttribute("indicadorAtualizar");
		}

		// CODIGO
		if (id != null && !id.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			if (achou) {
				peloMenosUmParametroInformado = true;

				filtroFaturamentoSituacaoTipo
						.adicionarParametro(new ParametroSimples(
								FiltroFaturamentoSituacaoTipo.ID, id));
			}
		}

		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroFaturamentoSituacaoTipo
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroFaturamentoSituacaoTipo.DESCRICAO,
								descricao));
			} else {

				filtroFaturamentoSituacaoTipo
						.adicionarParametro(new ComparacaoTexto(
								FiltroFaturamentoSituacaoTipo.DESCRICAO,
								descricao));
			}
		}

		// Indicador Paralisacao Faturamento
		if (indicadorParalisacaoFaturamento != null
				&& !indicadorParalisacaoFaturamento.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroFaturamentoSituacaoTipo
					.adicionarParametro(new ParametroSimples(
							FiltroFaturamentoSituacaoTipo.INDICADOR_PARALISACAO_FATURAMENTO,
							indicadorParalisacaoFaturamento));
		}

		// Indicador Paralisacao Leitura
		if (indicadorParalisacaoLeitura != null
				&& !indicadorParalisacaoLeitura.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroFaturamentoSituacaoTipo
					.adicionarParametro(new ParametroSimples(
							FiltroFaturamentoSituacaoTipo.INDICADOR_PARALISACAO_LEITURA,
							indicadorParalisacaoLeitura));
		}

		// Indicador Valido Agua
		if (indicadorValidoAgua != null
				&& !indicadorValidoAgua.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroFaturamentoSituacaoTipo
					.adicionarParametro(new ParametroSimples(
							FiltroFaturamentoSituacaoTipo.INDICADOR_VALIDO_AGUA,
							indicadorValidoAgua));
		}

		// Indicador Valido Esgoto
		if (indicadorValidoEsgoto != null
				&& !indicadorValidoEsgoto.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroFaturamentoSituacaoTipo
					.adicionarParametro(new ParametroSimples(
							FiltroFaturamentoSituacaoTipo.INDICADOR_VALIDO_ESGOTO,
							indicadorValidoEsgoto));
		}

		// Indicador Informar Consumo
		if (indicadorInformarConsumo != null
				&& !indicadorInformarConsumo.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroFaturamentoSituacaoTipo
					.adicionarParametro(new ParametroSimples(
							FiltroFaturamentoSituacaoTipo.INDICADOR_INFORMAR_CONSUMO,
							indicadorInformarConsumo));
		}

		// Indicador Informar Volume
		if (indicadorInformarVolume != null
				&& !indicadorInformarVolume.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroFaturamentoSituacaoTipo
					.adicionarParametro(new ParametroSimples(
							FiltroFaturamentoSituacaoTipo.INDICADOR_INFORMAR_VOLUME,
							indicadorInformarVolume));
		}
		
		//Leitura Anormalidade Leitura Com Leitura
		if (leituraAnormalidadeLeituraComLeitura!= null && 
				!leituraAnormalidadeLeituraComLeitura.trim().equals("-1")) {
				
				peloMenosUmParametroInformado = true;
				
				filtroFaturamentoSituacaoTipo.adicionarParametro(
					new ParametroSimples(FiltroFaturamentoSituacaoTipo.LEITURA_ANORMALIDADE_COM_LEITURA, 
							leituraAnormalidadeLeituraComLeitura));
			}
			
        //Leitura Anormalidade Leitura Sem Leitura
		if (leituraAnormalidadeLeituraSemLeitura!= null && 
				!leituraAnormalidadeLeituraSemLeitura.trim().equals("-1")) {
					
				peloMenosUmParametroInformado = true;
					
			    filtroFaturamentoSituacaoTipo.adicionarParametro(
					new ParametroSimples(FiltroFaturamentoSituacaoTipo.LEITURA_ANORMALIDADE_SEM_LEITURA, 
							leituraAnormalidadeLeituraSemLeitura));
			}	

		 //Leitura Anormalidade Consumo com Leitura
		if (leituraAnormalidadeConsumoComLeitura!= null && 
				!leituraAnormalidadeConsumoComLeitura.trim().equals("-1")) {
					
				peloMenosUmParametroInformado = true;
					
				filtroFaturamentoSituacaoTipo.adicionarParametro(
					new ParametroSimples(FiltroFaturamentoSituacaoTipo.LEITURA_ANORMALIDADE_CONSUMO_COM_LEITURA, 
							leituraAnormalidadeConsumoComLeitura));
			}
		
		//Leitura Anormalidade Consumo sem Leitura
		if (leituraAnormalidadeConsumoSemLeitura!= null && 
				!leituraAnormalidadeConsumoSemLeitura.trim().equals("-1")) {
					
				peloMenosUmParametroInformado = true;
					
				filtroFaturamentoSituacaoTipo.adicionarParametro(
					new ParametroSimples(FiltroFaturamentoSituacaoTipo.LEITURA_ANORMALIDADE_CONSUMO_SEM_LEITURA, 
							leituraAnormalidadeConsumoSemLeitura));
			}
		
		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroFaturamentoSituacaoTipo
					.adicionarParametro(new ParametroSimples(
							FiltroFaturamentoSituacaoTipo.INDICADOR_USO,
							indicadorUso));
		}

		Collection<FaturamentoSituacaoTipo> colecaoFaturamentoSituacaoTipo = fachada
				.pesquisar(filtroFaturamentoSituacaoTipo,
						FaturamentoSituacaoTipo.class.getName());

		// Verificar a existencia de um Grupo de cadastro
		if (colecaoFaturamentoSituacaoTipo.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Tipo de Situação de Faturamento");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}

		// Pesquisa sem registros
		if (colecaoFaturamentoSituacaoTipo == null
				|| colecaoFaturamentoSituacaoTipo.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null,
					"Tipo de Situação de Faturamento");
		} 

		httpServletRequest.setAttribute("colecaoFaturamentoSituacaoTipo",
				colecaoFaturamentoSituacaoTipo);
		FaturamentoSituacaoTipo faturamentoSituacaoTipo = new FaturamentoSituacaoTipo();
		faturamentoSituacaoTipo = (FaturamentoSituacaoTipo) Util
				.retonarObjetoDeColecao(colecaoFaturamentoSituacaoTipo);
		String idRegistroAtualizar = faturamentoSituacaoTipo.getId()
				.toString();
		sessao.setAttribute("idRegistroAtualizar", idRegistroAtualizar);
		

		sessao.setAttribute("filtroFaturamentoSituacaoTipo",
				filtroFaturamentoSituacaoTipo);

		httpServletRequest.setAttribute("filtroFaturamentoSituacaoTipo",
				filtroFaturamentoSituacaoTipo);

		return retorno;
	}
}
