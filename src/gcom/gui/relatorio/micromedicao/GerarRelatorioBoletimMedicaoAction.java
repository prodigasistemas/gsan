package gcom.gui.relatorio.micromedicao;

import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.micromedicao.FiltrarRelatorioBoletimMedicaoHelper;
import gcom.relatorio.micromedicao.RelatorioBoletimMedicao;
import gcom.relatorio.micromedicao.RelatorioBoletimMedicaoArquivoTxt;
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
 * [UC1054] - Gerar Relatório Boletim de Medição
 * 
 * @author Hugo Leonardo
 *
 * @date 04/08/2010
 */

public class GerarRelatorioBoletimMedicaoAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		   
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		   
		// Form
		GerarRelatorioBoletimMedicaoForm form = (GerarRelatorioBoletimMedicaoForm) actionForm;
		
		FiltrarRelatorioBoletimMedicaoHelper helper = new FiltrarRelatorioBoletimMedicaoHelper();
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		boolean peloMenosUmParametroInformado = false;
		
		// Ano Mes
		if ( form.getMesAnoReferencia() != null && 
				!form.getMesAnoReferencia().equals("")) {
			
			String anoMes = Util.formatarMesAnoParaAnoMesSemBarra(form.getMesAnoReferencia());
			helper.setMesAnoReferencia(anoMes);
			peloMenosUmParametroInformado = true;
		}
		
		// Empresa
		if(form.getEmpresa() != null && !form.getEmpresa().equals("-1")){
			
			helper.setEmpresa(form.getEmpresa());
			peloMenosUmParametroInformado = true;
		}
		
		// Número Contrato
		if(form.getNumeroContrato() != null && 
				!form.getNumeroContrato().equals("-1")){
			
			helper.setNumeroContrato(form.getNumeroContrato());
			peloMenosUmParametroInformado = true;
		}
		
		// gerência regional
		if(form.getGerenciaRegional() != null && 
				!form.getGerenciaRegional().equals("-1")){
			
			helper.setGerenciaRegional(form.getGerenciaRegional());
			peloMenosUmParametroInformado = true;
		}
		
		// localidade inicial
		if(form.getLocalidadeInicial() != null && 
				!form.getLocalidadeInicial().equals("")){
			
			helper.setLocalidadeInicial(form.getLocalidadeInicial());
			peloMenosUmParametroInformado = true;
		}

		// localidade final
		if(form.getLocalidadeFinal() != null && 
				!form.getLocalidadeFinal().equals("")){
			
			helper.setLocalidadeFinal(form.getLocalidadeFinal());
			peloMenosUmParametroInformado = true;
		}
		
		// forma de geração
		if(form.getFormaGeracao() != null && 
				!form.getFormaGeracao().equals("")){
			
			helper.setFormaGeracao(form.getFormaGeracao());
			peloMenosUmParametroInformado = true;
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		TarefaRelatorio relatorio = null;
		
		try {
			// Gerar Arquivo TXT
			if (form.getFormaGeracao().equals("2")
					|| form.getFormaGeracao().equals("3")) {
				
				relatorio = new RelatorioBoletimMedicaoArquivoTxt((Usuario)
						(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

				String tipoRelatorioTxt = TarefaRelatorio.TIPO_HTML + "";

				relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorioTxt));
				
				relatorio.addParametro("filtrarRelatorioBoletimMedicaoHelper", helper);

				relatorio.addParametro("mesAno", form.getMesAnoReferencia());
				
				retorno = processarExibicaoRelatorio(relatorio, tipoRelatorioTxt, httpServletRequest, 
							httpServletResponse, actionMapping);
				
			}
			
			
			// Gerar Relatório
			if (form.getFormaGeracao().equals("1")
				|| form.getFormaGeracao().equals("3")) {
				
				relatorio = new RelatorioBoletimMedicao((Usuario)
						(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
				if (tipoRelatorio  == null) {
					tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
				}
				
				relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
				
				relatorio.addParametro("filtrarRelatorioBoletimMedicaoHelper", helper);
				
				relatorio.addParametro("mesAno", form.getMesAnoReferencia());
				
				retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
							httpServletResponse, actionMapping);
				
			}
			
	
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
