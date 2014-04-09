package gcom.gui.arrecadacao;

import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.debitoautomatico.DebitoAutomaticoMovimento;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pre- processamento para gerar movimento de débito automático para o banco
 * 
 * @author Sávio Luiz
 * @date 17/04/2006
 */
public class ExibirGerarMovimentoDebitoAutomaticoBancoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("gerarMovimentoDebitoAutomatico");

		GerarMovimentoDebitoAutomaticoBancoActionForm gerarMovimentoDebitoAutomaticoBancoActionForm = (GerarMovimentoDebitoAutomaticoBancoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		if (httpServletRequest.getParameter("criaColecaoBanco") != null
				&& !httpServletRequest.getParameter("criaColecaoBanco").equals(
						"")) {

			boolean mesAnoValido = Util
					.validarMesAno(gerarMovimentoDebitoAutomaticoBancoActionForm
							.getMesAnoFaturamento());
			if (!mesAnoValido) {
				throw new ActionServletException("atencao.ano_mes.invalido");
			}
			int ano = Integer
					.parseInt(gerarMovimentoDebitoAutomaticoBancoActionForm
							.getMesAnoFaturamento().substring(3, 7));
			if (ano < 2005) {
				throw new ActionServletException("atencao.movimento.maior.2005");
			}
			
			
			Integer anoMesReferencia = Util.formatarMesAnoComBarraParaAnoMes(
			gerarMovimentoDebitoAutomaticoBancoActionForm.getMesAnoFaturamento());
			
			
			/*
			 * Colocado por Raphael Rossiter em 03/07/2007 (Analista: Rosana Carvalgo)
			 * Objetivo: Selecionar um conjunto de grupos de faturamento
			 */
			
			//GRUPOS SELECIONADOS
			String dadosFaturamentoGrupo = httpServletRequest.getParameter("criaColecaoBanco");
			
			//GERANDO COLEÇÃO DE GRUPOS
			Collection colecaoIdsFaturamentoGrupo = this.obterGruposSelecionados(dadosFaturamentoGrupo, anoMesReferencia);
			
			//OBTENDO OS DÁBITOS AUTOMÁTICOS
			Map<Banco, Collection<DebitoAutomaticoMovimento>> debitosAutomaticoBancosMap = fachada
			.pesquisaDebitoAutomaticoMovimento(colecaoIdsFaturamentoGrupo, anoMesReferencia);
			

			if (debitosAutomaticoBancosMap != null
					&& !debitosAutomaticoBancosMap.isEmpty()) {
				sessao.setAttribute("debitosAutomaticoBancosMap",
						debitosAutomaticoBancosMap);
			} else {
				throw new ActionServletException(
						"atencao.nao.movimento.debito.automatico");
			}
			
			
		} else {
			sessao.removeAttribute("debitosAutomaticoBancosMap");
		}
		if (httpServletRequest.getParameter("limpaColecao") != null
				&& !httpServletRequest.getParameter("limpaColecao").equals("")) {
			sessao.removeAttribute("debitosAutomaticoBancosMap");
		}

		if (httpServletRequest.getParameter("menu") != null
				&& !httpServletRequest.getParameter("menu").equals("")) {
			gerarMovimentoDebitoAutomaticoBancoActionForm.reset(actionMapping,
					httpServletRequest);
			FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo(
					FiltroFaturamentoGrupo.DESCRICAO);

			filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoGrupo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoFaturamentoGrupo = fachada.pesquisar(
					filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

			sessao.setAttribute("colecaoFaturamentoGrupo",
					colecaoFaturamentoGrupo);
		}

		return retorno;
	}
	
	
	private Collection obterGruposSelecionados(String faturamentoGrupoStringBuffer, Integer anoMesReferencia){
		
		Collection retorno = null;
		
		if (faturamentoGrupoStringBuffer != null && !faturamentoGrupoStringBuffer.equalsIgnoreCase("")){
			
			retorno = new ArrayList();
			
			String[] arrayFaturamentoGrupo = faturamentoGrupoStringBuffer.split(":");
			String[] arrayGrupoEReferencia = null;
			
			Integer idFaturamentoGrupo = null;
			Integer anoMesFaturamentoGrupo = null;
			
			for (int x=0; x < arrayFaturamentoGrupo.length; x++){
				
				arrayGrupoEReferencia = arrayFaturamentoGrupo[x].split(";");
				
				idFaturamentoGrupo = new Integer(arrayGrupoEReferencia[0]); 
				anoMesFaturamentoGrupo = new Integer(arrayGrupoEReferencia[1]);
			
				if (anoMesReferencia > anoMesFaturamentoGrupo) {
					throw new ActionServletException(
							"atencao.faturamento.posterior.faturamento.grupo");
				}
				
				retorno.add(idFaturamentoGrupo);
			}
		}
		
		return retorno;
	}
}
