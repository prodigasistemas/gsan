package gcom.gui.relatorio.faturamento.conta;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.faturamento.conta.FiltrarMapaControleContaRelatorioActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.conta.RelatorioResumoContaLocalidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
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

public class GerarRelatorioResumoContaLocalidadeAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarMapaControleContaRelatorioActionForm filtrarMapaControleContaRelatorioActionForm = (FiltrarMapaControleContaRelatorioActionForm) actionForm;
		 
		Collection colecaoResumoContaLocalidadeConta  = (Collection)sessao.getAttribute("colecaoResumoContaLocalidadeConta");
		
		String idFirma = filtrarMapaControleContaRelatorioActionForm.getFirma();
		
		Empresa empresa = null;
		
		if (idFirma != null && !idFirma.trim().equals("") && !idFirma.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, idFirma));
			
			Collection colecaoEmpresas = Fachada.getInstancia().pesquisar(filtroEmpresa, Empresa.class.getName());
			
			empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresas);
		
		}
		
		// Parte que vai mandar o relatório para a tela

		// cria uma instância da classe do relatório
		RelatorioResumoContaLocalidade relatorioResumoContaLocalidade = new RelatorioResumoContaLocalidade(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		relatorioResumoContaLocalidade.addParametro("colecaoResumoContaLocalidadeConta",colecaoResumoContaLocalidadeConta);
		
		relatorioResumoContaLocalidade.addParametro("mesAno",filtrarMapaControleContaRelatorioActionForm.getMesAno());
		relatorioResumoContaLocalidade.addParametro("idGrupoFaturamento",filtrarMapaControleContaRelatorioActionForm.getIdGrupoFaturamento());
		relatorioResumoContaLocalidade.addParametro("firma", empresa);
		
		//Parte do codigo q gera a data de vencimento
		//dia do vencimento do grupo/mes ano de referencia do grupo + 1
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID, 
				filtrarMapaControleContaRelatorioActionForm.getIdGrupoFaturamento()));
		
		Collection colecaoFaturamento = Fachada.getInstancia().pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo)colecaoFaturamento.iterator().next();
		
		String vencimento = Util.adicionarZerosEsquedaNumero(2,faturamentoGrupo.getDiaVencimento()+"") + "/" 
			+ Util.somaMesMesAnoComBarra(filtrarMapaControleContaRelatorioActionForm.getMesAno(), 1);
		
		relatorioResumoContaLocalidade.addParametro("vencimento",vencimento);
		

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioResumoContaLocalidade.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioResumoContaLocalidade,
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
