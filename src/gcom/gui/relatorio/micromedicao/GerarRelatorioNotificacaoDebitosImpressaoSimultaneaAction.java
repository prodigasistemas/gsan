package gcom.gui.relatorio.micromedicao;

import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cadastro.micromedicao.RelatorioNotificacaoDebitosImpressaoSimultanea;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1022] Relatório de Notificação de Débitos para Impressão Simultânea 
 * 
 * @author Daniel Alves
 * @date 17/05/2010
 */
public class GerarRelatorioNotificacaoDebitosImpressaoSimultaneaAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;

		// Form
		FiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaActionForm form = 
			(FiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaActionForm) actionForm;

		RelatorioNotificacaoDebitosImpressaoSimultaneaHelper helper = new
			RelatorioNotificacaoDebitosImpressaoSimultaneaHelper();

		//Trecho que converte o form para um helper
		
		//linha que vai modificar o formato da data, de mm/aaaa para aaaamm.
		helper.setAnoMesReferencia(
				String.valueOf(Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesReferencia()))
		);
		
		helper.setEmpresa(form.getEmpresa());
		helper.setGrupo(form.getGrupo());
		helper.setLocalidade(form.getLocalidade());
		helper.setCodigoSetorComercial(form.getCodigoSetorComercial());
		helper.setRota(form.getRota());

		//Modifica o cabeçalho do relatorio tem
		//localidade, setor e rota		
		helper.setCabecalhoTipo(0);

		//caso o Localidade for informada
	    //adicionar Localidade
		if(form.getLocalidade() != null &&
       		 !form.getLocalidade().equalsIgnoreCase("")){			

			helper.setCabecalhoTipo(1);       	 
			
			//caso o Setor for informada
   	    	//adicionar Localidade e Setor
       	    if(form.getCodigoSetorComercial() != null &&
           		 !form.getCodigoSetorComercial().equalsIgnoreCase("")){

       	    	helper.setCabecalhoTipo(2);        

       	    	//caso a Rota for informada
       	    	//adicionar Localidade, Setor e Rota
           	    if(form.getRota() != null &&
	            		 !form.getRota().equalsIgnoreCase("")){
           	    	helper.setCabecalhoTipo(3);
	            }
            }
        }


		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioNotificacaoDebitosImpressaoSimultanea relatorio =
			new RelatorioNotificacaoDebitosImpressaoSimultanea(this.getUsuarioLogado(httpServletRequest));

		relatorio.addParametro("relatorioNotificacaoDebitosImpressaoSimultaneaHelper", helper);
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
	    }

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));

		retorno = 
			processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
				httpServletResponse, actionMapping);

		return retorno;
	}

}
