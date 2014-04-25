package gcom.gui.relatorio.financeiro;

import gcom.batch.Processo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.financeiro.RelatorioContasBaixadasContabilmente;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Geração do relatório [UC0726] Gerar Relatório das Contas Baixadas Contabilmente
 * 
 * @author Vivianne Sousa
 * @data 08/04/2008
 */

public class GerarRelatorioContasBaixadasContabilmenteAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

//		ActionForward retorno = null;
        ActionForward retorno = actionMapping.findForward("telaSucesso");
        GerarRelatorioContasBaixadasContabilmenteActionForm form = (GerarRelatorioContasBaixadasContabilmenteActionForm) actionForm;

        //Este map levará todos os parâmetros para a inicialização do relatório
        Map parametros = new HashMap();
        
               
        String referenciaInicial = form.getReferenciaInicial();
        String referenciaFinal = form.getReferenciaFinal();
        Integer referenciaInicialInt = 0;
        Integer referenciaFinalInt = 0;
        String tipoFormato = form.getTipoFormato();
        
        if (referenciaInicial != null && !referenciaInicial.equals("") &&
                !Util.validarMesAno(referenciaInicial)) {
            throw new ActionServletException("atencao.adicionar_debito_ano_mes_referencia_invalido",null,"Inicial");
        }
        if (referenciaFinal != null && !referenciaFinal.equals("") &&
                !Util.validarMesAno(referenciaFinal)) {
            throw new ActionServletException("atencao.adicionar_debito_ano_mes_referencia_invalido",null,"Final");
        }
        
        //[FS0001] - Verificar tipo de relatório
        if (form.getTipo() == null || form.getTipo().equalsIgnoreCase("")) {
          throw new ActionServletException("atencao.required", null, "Tipo de Relatório");
        }
        
        //[FS0002] - Verificar a periodicidade
        if (form.getPeriodicidade() == null || form.getPeriodicidade().equalsIgnoreCase("")) {
          throw new ActionServletException("atencao.required", null, "Periodicidade");
        }
        
        Short tipo = new Short (form.getTipo());
        Short periodicidade = new Short (form.getPeriodicidade());
        //[FS0003] - Verificar atributos inicial e final
        if ((periodicidade.equals(ConstantesSistema.ACUMULADO) || (periodicidade.equals(ConstantesSistema.MENSAL))) &&
            (referenciaFinal == null || referenciaFinal.equalsIgnoreCase(""))) {
          throw new ActionServletException("atencao.required", null, "Referência das Faturas Final");
        }else{
            referenciaFinalInt = new Integer(Util
                    .formatarMesAnoParaAnoMesSemBarra(referenciaFinal));
        }
        
        if (periodicidade.equals(ConstantesSistema.MENSAL)) {
            
            if((referenciaInicial != null && !referenciaInicial.equalsIgnoreCase("")) &&
               (referenciaFinal == null || referenciaFinal.equalsIgnoreCase(""))){
                
                throw new ActionServletException("atencao.required", null, "Referência das Faturas Final");
            }
            
            if((referenciaFinal != null && !referenciaFinal.equalsIgnoreCase("")) &&
                (referenciaInicial == null || referenciaInicial.equalsIgnoreCase(""))){
                 
                 throw new ActionServletException("atencao.required", null, "Referência das Faturas Inicial");
            }
            
            if (referenciaInicial != null && !referenciaInicial.equalsIgnoreCase("")&&
                referenciaFinal != null && !referenciaFinal.equalsIgnoreCase("")) {

                referenciaInicialInt = new Integer(Util
                        .formatarMesAnoParaAnoMesSemBarra(referenciaInicial));

                referenciaFinalInt = new Integer(Util
                        .formatarMesAnoParaAnoMesSemBarra(referenciaFinal));
                
                if (Util.compararAnoMesReferencia(referenciaInicialInt, referenciaFinalInt, ">")) {
                    throw new ActionServletException(
                    "atencao.referencia_fatura_final_menor_referencia_fatura_inicial");
                }

            }
           
        }
        
        if (tipoFormato == null || tipoFormato.equals("")){
        	throw new ActionServletException(
            "atencao.relatorio_tipo_nao_informado");
        }
        
        parametros.put("tipo",tipo);
        parametros.put("periodicidade",periodicidade);
        parametros.put("referenciaInicial",referenciaInicialInt);
        parametros.put("referenciaFinal",referenciaFinalInt);
        parametros.put("tipoFormatoRelatorio", tipoFormato);
        

        if (tipoFormato.equals("TXT")){
        
        Fachada.getInstancia().inserirProcessoIniciadoParametrosLivres(parametros, 
        		Processo.GERAR_TXT_CONTAS_BAIXADAS_CONTABILMENTE ,
        		this.getUsuarioLogado(httpServletRequest));
        
        
        } else 
        	if (tipoFormato.equals("PDF")){
        	
        		RelatorioContasBaixadasContabilmente relatorioContasBaixadasContabilmente = new RelatorioContasBaixadasContabilmente((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
    			
        		relatorioContasBaixadasContabilmente.addParametro("tipo", tipo);
        		relatorioContasBaixadasContabilmente.addParametro("periodicidade", periodicidade);
        		relatorioContasBaixadasContabilmente.addParametro("referenciaInicial", referenciaInicialInt);
        		relatorioContasBaixadasContabilmente.addParametro("referenciaFinal", referenciaFinalInt);
        		relatorioContasBaixadasContabilmente.addParametro("tipoFormatoRelatorio", TarefaRelatorio.TIPO_PDF);
    			String tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
    			
    			try {
    				retorno = processarExibicaoRelatorio(relatorioContasBaixadasContabilmente,
    						tipoRelatorio, httpServletRequest, httpServletResponse,
    						actionMapping);

    			} catch (RelatorioVazioException ex) {
    				// manda o erro para a página no request atual
    				reportarErros(httpServletRequest, "atencao.relatorio.vazio");

    				// seta o mapeamento de retorno para a tela de atenção de popup
    				retorno = actionMapping.findForward("telaAtencaoPopup");
    			}
        	
        }
        
        montarPaginaSucesso(httpServletRequest, "Relatório foi para batch.",
                "",
                "");
        
		return retorno;
	}
	
}
