package gcom.gui.relatorio.cadastro;

import java.util.Date;

import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.RelatorioAtualizacaoCadastralViaInternet;
import gcom.relatorio.cadastro.RelatorioResumoAtualizacaoCadastralViaInternet;
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
 * [UC1076] - Gerar Relatorio Atualizacao Cadastral Via Internet.
 * @author Daniel Alves
 * @date 24/09/2010 
 */

public class GerarRelatorioAtualizacaoCadastralViaInternetAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		   
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		   
		// Form
		GerarRelatorioAtualizacaoCadastralViaInternetActionForm form = 
			(GerarRelatorioAtualizacaoCadastralViaInternetActionForm) actionForm;
		
		if(form.getPeriodoReferenciaInicial() != null &&
				form.getPeriodoReferenciaInicial() != "" &&
				form.getPeriodoReferenciaFinal() != null &&
				form.getPeriodoReferenciaFinal() != ""){
			
		}else{			
			throw new ActionServletException("atencao.informe_os_campos_obrigatorios");
		}	
		
		
		if(Util.verificarNaoVazio(form.getPeriodoReferenciaInicial()) 
				&& Util.verificarNaoVazio(form.getPeriodoReferenciaFinal())){
			
			Date periodoInicial = Util.converteStringParaDate(form.getPeriodoReferenciaInicial());
			Date periodoFinal = Util.converteStringParaDate(form.getPeriodoReferenciaFinal());
			
			if(periodoFinal.compareTo(periodoInicial)<0){
				throw new ActionServletException("atencao.data_final_periodo.anterior.data_inicial_periodo");
			}
		}
		
		TarefaRelatorio relatorio = null;
		
		//escolhe o formato do relatorio (Analítico ou Resumo)
		if(form.getOpcaoRelatorio() != null &&
				form.getOpcaoRelatorio() != ""){
			
			if(form.getOpcaoRelatorio().equals("R")){
				relatorio = new RelatorioResumoAtualizacaoCadastralViaInternet((Usuario)
						(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			} else if (form.getOpcaoRelatorio().equals("A")){
				relatorio = new RelatorioAtualizacaoCadastralViaInternet((Usuario)
						(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			}
			
		}else{
			throw new ActionServletException("atencao.informe_os_campos_obrigatorios");
		}
		
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		relatorio.addParametro("filtroPeriodoInicial", form.getPeriodoReferenciaInicial());
		relatorio.addParametro("filtroPeriodoFinal", form.getPeriodoReferenciaFinal());
		relatorio.addParametro("filtroGerenciaRegional", form.getGerenciaRegional());
		relatorio.addParametro("filtroUnidadeNegocio", form.getUnidadeNegocio());
		relatorio.addParametro("filtroLocalidadeInicial", form.getLocalidadeInicial());
		relatorio.addParametro("filtroLocalidadeFinal", form.getLocalidadeFinal());
		
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
