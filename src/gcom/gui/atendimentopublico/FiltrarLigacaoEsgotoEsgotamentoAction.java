package gcom.gui.atendimentopublico;



import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.fachada.Fachada;
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
 * [UC0838]FILTRAR LIGACAO DE ESGOTO ESGOTAMENTO
 * 
 * @author Arthur Carvalho
 * @date 25/08/08
 */

public class FiltrarLigacaoEsgotoEsgotamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterLigacaoEsgotoEsgotamento");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarLigacaoEsgotoEsgotamentoActionForm filtrarLigacaoEsgotoEsgotamentoActionForm = (FiltrarLigacaoEsgotoEsgotamentoActionForm) actionForm;

		FiltroLigacaoEsgotoEsgotamento filtroLigacaoEsgotoEsgotamento = new FiltroLigacaoEsgotoEsgotamento();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarLigacaoEsgotoEsgotamentoActionForm.getId();
		String quantidadeMesesSituacaoEspecial = filtrarLigacaoEsgotoEsgotamentoActionForm.getQuantidadeMesesSituacaoEspecial();
		String descricao = filtrarLigacaoEsgotoEsgotamentoActionForm.getDescricao();
		String indicadorUso = filtrarLigacaoEsgotoEsgotamentoActionForm.getIndicadorUso();
		String faturamentoSituacaoTipo =  filtrarLigacaoEsgotoEsgotamentoActionForm.getFaturamentoSituacaoTipo();
		String tipoPesquisa = filtrarLigacaoEsgotoEsgotamentoActionForm.getTipoPesquisa();
		String faturamentoSituacaoMotivo = filtrarLigacaoEsgotoEsgotamentoActionForm.getFaturamentoSituacaoMotivo();
		
		//Indicador Atualizar
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {			
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {			
			sessao.removeAttribute("indicadorAtualizar");
		}
		
		//CODIGO
		if (id != null && !id.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			if (achou) {
				peloMenosUmParametroInformado = true;
				
				filtroLigacaoEsgotoEsgotamento.adicionarParametro(
					new ParametroSimples(
						FiltroLigacaoEsgotoEsgotamento.ID, 
						id));
			}
		}
		
		//Motivo da situacal especial de faturamento
		if (faturamentoSituacaoMotivo != null && 
			!faturamentoSituacaoMotivo.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			
			filtroLigacaoEsgotoEsgotamento.adicionarParametro(
				new ParametroSimples(
					FiltroLigacaoEsgotoEsgotamento.FATURAMENTO_SITUACAO_MOTIVO_ID, 
					faturamentoSituacaoMotivo));
		}
		
		//Tipo de situacao especial de faturamento
		if (faturamentoSituacaoTipo != null && 
			!faturamentoSituacaoTipo.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			
			filtroLigacaoEsgotoEsgotamento.adicionarParametro(
				new ParametroSimples(
					FiltroLigacaoEsgotoEsgotamento.FATURAMENTO_SITUACAO_TIPO_ID, 
					faturamentoSituacaoTipo));
		}
	
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroLigacaoEsgotoEsgotamento
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroLigacaoEsgotoEsgotamento.DESCRICAO, descricao));
			} else {

				filtroLigacaoEsgotoEsgotamento.adicionarParametro(new ComparacaoTexto(
						FiltroLigacaoEsgotoEsgotamento.DESCRICAO, descricao));
			}
		}
		
		//Quantidade de meses para situacao especial de faturamento 
		if (quantidadeMesesSituacaoEspecial != null && !quantidadeMesesSituacaoEspecial.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroLigacaoEsgotoEsgotamento
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroLigacaoEsgotoEsgotamento.QUANTIDADE_MESES_SITUACAO_ESPECIAL, quantidadeMesesSituacaoEspecial));
			} else {

				filtroLigacaoEsgotoEsgotamento.adicionarParametro(new ComparacaoTexto(
						FiltroLigacaoEsgotoEsgotamento.QUANTIDADE_MESES_SITUACAO_ESPECIAL, quantidadeMesesSituacaoEspecial));
			}
		}
		
		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroLigacaoEsgotoEsgotamento.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgotoEsgotamento.INDICADOR_USO, indicadorUso));
		}
		
		filtroLigacaoEsgotoEsgotamento.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoTipo");
		filtroLigacaoEsgotoEsgotamento.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoMotivo"); 
		
		Collection <LigacaoEsgotoEsgotamento> colecaoLigacaoEsgotoEsgotamento = fachada
				.pesquisar(filtroLigacaoEsgotoEsgotamento, LigacaoEsgotoEsgotamento.class
						.getName());

		// Verificar a existencia de um Grupo de cadastro
		if (colecaoLigacaoEsgotoEsgotamento.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Ligação de Esgoto Esgotamento");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros

		if (colecaoLigacaoEsgotoEsgotamento == null
				|| colecaoLigacaoEsgotoEsgotamento.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Ligação de Esgoto Esgotamento");
		} else {
			httpServletRequest.setAttribute("colecaoLigacaoEsgotoEsgotamento",
					colecaoLigacaoEsgotoEsgotamento);
			LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento= new LigacaoEsgotoEsgotamento();
			ligacaoEsgotoEsgotamento = (LigacaoEsgotoEsgotamento) Util
					.retonarObjetoDeColecao(colecaoLigacaoEsgotoEsgotamento);
			String idRegistroAtualizar = ligacaoEsgotoEsgotamento.getId().toString();
			sessao.setAttribute("idRegistroAtualizar", idRegistroAtualizar);
		}

		sessao.setAttribute("filtroLigacaoEsgotoEsgotamento", filtroLigacaoEsgotoEsgotamento);

		httpServletRequest.setAttribute("filtroLigacaoEsgotoEsgotamento",
				filtroLigacaoEsgotoEsgotamento);

		
		return retorno;
	}
}
