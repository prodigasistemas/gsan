package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
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
 * [UC0771]FILTRAR GRUPO DE FATURAMENTO
 * 
 * @author Vinícius Medeiros
 * @date 23/04/2008
 */

public class FiltrarFaturamentoGrupoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterFaturamentoGrupo");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarFaturamentoGrupoActionForm filtrarFaturamentoGrupoActionForm = (FiltrarFaturamentoGrupoActionForm) actionForm;

		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarFaturamentoGrupoActionForm.getId();
		String descricao = filtrarFaturamentoGrupoActionForm.getDescricao();
		String descricaoAbreviada = filtrarFaturamentoGrupoActionForm
				.getDescricaoAbreviada();
		String anoMesReferencia = filtrarFaturamentoGrupoActionForm
				.getAnoMesReferencia();
		String diaVencimento = filtrarFaturamentoGrupoActionForm
				.getDiaVencimento();
		String indicadorVencimentoMesFatura = filtrarFaturamentoGrupoActionForm
				.getIndicadorVencimentoMesFatura();
		String indicadorUso = filtrarFaturamentoGrupoActionForm
				.getIndicadorUso();
		String tipoPesquisa = filtrarFaturamentoGrupoActionForm
				.getTipoPesquisa();

		// ID
		if (id != null && !id.trim().equals("")) {
			//boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			//if (achou) {
				peloMenosUmParametroInformado = true;
				filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
						FiltroFaturamentoGrupo.ID, id));
			//}
		}
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroFaturamentoGrupo
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroFaturamentoGrupo.DESCRICAO, descricao));
			} else {

				filtroFaturamentoGrupo.adicionarParametro(new ComparacaoTexto(
						FiltroFaturamentoGrupo.DESCRICAO, descricao));
			}
		}

		// Descricao Abreviada
		if (descricaoAbreviada != null
				&& !descricaoAbreviada.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			filtroFaturamentoGrupo
					.adicionarParametro(new ComparacaoTextoCompleto(
							FiltroFaturamentoGrupo.DESCRICAO_ABREVIADA,
							descricaoAbreviada));
		} else {

			filtroFaturamentoGrupo.adicionarParametro(new ComparacaoTexto(
					FiltroFaturamentoGrupo.DESCRICAO_ABREVIADA,
					descricaoAbreviada));
		}
		
		// Ano Mes Referencia
	       if(anoMesReferencia != null && !anoMesReferencia.trim().equals("")){
	        	String mes = anoMesReferencia.substring(0, 2);
	        	String ano = anoMesReferencia.substring(3, 7);
	        	String anoMes = ano+mes;
	        	peloMenosUmParametroInformado = true;
	        	filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
	        			FiltroFaturamentoGrupo.ANO_MES_REFERENCIA, anoMes));
	        	
	        }
		
	       // Dia Vencimento
			if (diaVencimento != null && !diaVencimento.trim().equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
				filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
						FiltroFaturamentoGrupo.DIA_VENCIMENTO,
						diaVencimento));
			}
		
		// Indicador Vencimento Mes Fatura
		if (indicadorVencimentoMesFatura != null
				&& !indicadorVencimentoMesFatura.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoGrupo.INDICADOR_VENCIMENTO_MES_FATURA,
					indicadorVencimentoMesFatura));

		}

		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoGrupo.INDICADOR_USO, indicadorUso));
		}
		
		Collection<FaturamentoGrupo> colecaoFaturamentoGrupo = fachada
				.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class
						.getName());

		// Verificar a existencia de um Grupo de Faturamento
		if (colecaoFaturamentoGrupo.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Grupo de Faturamento");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros

		if (colecaoFaturamentoGrupo == null
				|| colecaoFaturamentoGrupo.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Grupo de Faturamento");
		} else {
			httpServletRequest.setAttribute("colecaoFaturamentoGrupo",
					colecaoFaturamentoGrupo);
			FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();
			faturamentoGrupo = (FaturamentoGrupo) Util
					.retonarObjetoDeColecao(colecaoFaturamentoGrupo);
			String idRegistroAtualizacao = faturamentoGrupo.getId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroFaturamentoGrupo", filtroFaturamentoGrupo);

		httpServletRequest.setAttribute("filtroFaturamentoGrupo",
				filtroFaturamentoGrupo);

		return retorno;

	}
}
