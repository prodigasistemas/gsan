package gcom.gui.relatorio.faturamento.conta;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.faturamento.conta.FiltrarMapaControleContaRelatorioActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.conta.RelatorioMapaControleConta;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Geração do relatório [UC] Gerar Relatório 
 * 
 * @author Flavio Cordeiro
 */

public class GerarRelatorioMapaControleContaAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarMapaControleContaRelatorioActionForm filtrarMapaControleContaRelatorioActionForm = (FiltrarMapaControleContaRelatorioActionForm) actionForm;
		 
		Collection colecaoMapaControleConta  = (Collection)sessao.getAttribute("colecaoMapaControleConta");
		
			
		// Parte que vai mandar o relatório para a tela

		// cria uma instância da classe do relatório
		RelatorioMapaControleConta relatorioMapaControleConta = new RelatorioMapaControleConta(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		relatorioMapaControleConta.addParametro("colecaoMapaControleConta",colecaoMapaControleConta);
		
		relatorioMapaControleConta.addParametro("mesAno",filtrarMapaControleContaRelatorioActionForm.getMesAno());
		relatorioMapaControleConta.addParametro("idGrupoFaturamento",filtrarMapaControleContaRelatorioActionForm.getIdGrupoFaturamento());
		
		//Parte do codigo q gera a data de vencimento
		//dia do vencimento do grupo/mes ano de referencia do grupo + 1
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID, 
				filtrarMapaControleContaRelatorioActionForm.getIdGrupoFaturamento()));
		
		Collection colecaoFaturamento = Fachada.getInstancia().pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo)colecaoFaturamento.iterator().next();
		
		String vencimento = Util.adicionarZerosEsquedaNumero(2,faturamentoGrupo.getDiaVencimento()+"") + "/" 
			+ Util.somaMesMesAnoComBarra(filtrarMapaControleContaRelatorioActionForm.getMesAno(), 1);
		
		relatorioMapaControleConta.addParametro("vencimento",vencimento);
		

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioMapaControleConta.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioMapaControleConta,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");

		}

		return retorno;
	}

}
