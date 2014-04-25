package gcom.gui.relatorio.atendimentopublico.ordemservico;

import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.ordemservico.FiltrarBoletimCustoPavimentoHelper;
import gcom.relatorio.atendimentopublico.ordemservico.RelatorioBoletimCustoPavimento;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1109] Filtrar Dados para Geração Boletim de Custo de Repavimentação
 * 
 * @author Hugo Leonardo
 *
 * @date 03/01/2011
 */

public class GerarRelatorioBoletimCustoRepavimentacaoPorTipoPavimentoAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		   
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		   
		// Form
		GerarRelatorioBoletimCustoRepavimentacaoPorTipoPavimentoForm form = (GerarRelatorioBoletimCustoRepavimentacaoPorTipoPavimentoForm) actionForm;
		
		FiltrarBoletimCustoPavimentoHelper helper = new FiltrarBoletimCustoPavimentoHelper();
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		boolean peloMenosUmParametroInformado = false;
		
		// Ano Mês Referência Geração
		if ( form.getMesAnoReferenciaGeracao() != null && 
				!form.getMesAnoReferenciaGeracao().equals("")) {
			
			String anoMesGeracao = Util.formatarMesAnoParaAnoMesSemBarra(form.getMesAnoReferenciaGeracao());
			helper.setMesAnoReferenciaGeracao(anoMesGeracao);
			peloMenosUmParametroInformado = true;
		}
		
		// Unidade Repavimentadora
		if(form.getIdUnidadeRepavimentadora() != null && !form.getIdUnidadeRepavimentadora().equals("-1")){
			
			helper.setIdUnidadeRepavimentadora(form.getIdUnidadeRepavimentadora());
			peloMenosUmParametroInformado = true;
		}
		
		// Tipo de Boletim
		if(form.getIndicadorTipoBoletim() != null && 
				!form.getIndicadorTipoBoletim().equals("")){
			
			helper.setIndicadorTipoBoletim(form.getIndicadorTipoBoletim());
			peloMenosUmParametroInformado = true;
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		 
		TarefaRelatorio relatorio = new RelatorioBoletimCustoPavimento((Usuario)
				(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		relatorio.addParametro("filtrarBoletimCustoPavimentoHelper", helper);
		
		relatorio.addParametro("mesAnoGeracao", form.getMesAnoReferenciaGeracao());
		
		relatorio.addParametro("usuario", usuario);
		
		String mesAno1Anterior = Util.somaMesMesAnoComBarra(form.getMesAnoReferenciaGeracao(), -1);
		String mesAno2Anterior = Util.somaMesMesAnoComBarra(form.getMesAnoReferenciaGeracao(), -2);
		String mesAno3Anterior = Util.somaMesMesAnoComBarra(form.getMesAnoReferenciaGeracao(), -3);
		
		String mesAnoAnteriores = ""+mesAno3Anterior + " - " + mesAno2Anterior + " - " + mesAno1Anterior;
		
		relatorio.addParametro("mesAnoAnteriores", mesAnoAnteriores);
		
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
