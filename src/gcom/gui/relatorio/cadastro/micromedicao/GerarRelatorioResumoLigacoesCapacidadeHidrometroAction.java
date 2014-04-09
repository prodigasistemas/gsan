package gcom.gui.relatorio.cadastro.micromedicao;


import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.micromedicao.RelatorioResumoLigacoesCapacidadeHidrometro;
import gcom.relatorio.cadastro.micromedicao.RelatorioResumoLigacoesCapacidadeHidrometroHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0997] Gerar Resumo de Ligações por Capacidade de Hidrômetro
 * 
 * @author Hugo Leonardo
 *
 * @date 29/03/2010
 */

public class GerarRelatorioResumoLigacoesCapacidadeHidrometroAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		   
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		   
		// Form
		GerarRelatorioResumoLigacoesCapacidadeHidrometroActionForm form = 
			(GerarRelatorioResumoLigacoesCapacidadeHidrometroActionForm) actionForm;
		
		RelatorioResumoLigacoesCapacidadeHidrometroHelper helper = 
			new RelatorioResumoLigacoesCapacidadeHidrometroHelper();
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		// mesAno Faturamento 
		SistemaParametro sistemaParametro = this.getSistemaParametro();
		
		Integer anoMesFaturamento =  sistemaParametro.getAnoMesFaturamento();
		
		// Subtrai 1 mês do AnoMesFaturamentoReferencia
		Integer anoMesFaturamentoAnterior = Util.subtrairMesDoAnoMes(anoMesFaturamento, 1);
		String mesAno = Util.formatarAnoMesParaMesAno(anoMesFaturamentoAnterior.toString());
		
		int mes = Util.obterMes(anoMesFaturamentoAnterior);
		int ano = Util.obterAno(anoMesFaturamentoAnterior);
		
		// obtém a Data do último dia do anoMes Faturamento Anterior
		Integer dia = new Integer(Util.obterUltimoDiaMes(mes, ano));
		
		helper.setMesAnoReferencia(Util.criarData(dia, mes, ano));
		
		helper.setAnoMesReferenciaAnterior(anoMesFaturamentoAnterior);
		
		// opcao Totalizacao
		if(form.getOpcaoTotalizacao() != null && !form.getOpcaoTotalizacao().equals("-1")){
			helper.setOpcaoTotalizacao(form.getOpcaoTotalizacao());
		}
		
		// Gerencia Regional
		if(form.getRegional() != null && !form.getRegional().equals("-1")){
			helper.setIdGerenciaRegional( new Integer(form.getRegional()));
		}
		
		// Unidade de Negócio
		if(form.getUnidadeNegocio() != null && !form.getUnidadeNegocio().equals("-1")){
			helper.setIdUnidadeNegocio( new Integer(form.getUnidadeNegocio()));
		}
		
		//Localidade
		if(form.getIdLocalidade() != null && !form.getIdLocalidade().equals("")){
			helper.setIdLocalidade(new Integer(form.getIdLocalidade()));
		}
		
		TarefaRelatorio relatorio = null;
	
		relatorio = new RelatorioResumoLigacoesCapacidadeHidrometro((Usuario)
						(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			 
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		relatorio.addParametro("relatorioResumoLigacoesCapacidadeHidrometroHelper", helper);
		
		relatorio.addParametro("mesAno", mesAno);
		
		try {	
			
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
						httpServletResponse, actionMapping);
	
		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");
	
			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");
	
		} catch (RelatorioVazioException ex1) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
		}
			
			return retorno;
		}
	
}
