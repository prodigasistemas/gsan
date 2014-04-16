package gcom.gui.gerencial.faturamento;

import gcom.fachada.Fachada;
import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ConsultarResumoAnaliseFaturamentoAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping
                .findForward("consultarResumoAnaliseFaturamento");

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();
        
        ResumoAnaliseFaturamentoActionForm resumoAnaliseFaturamentoActionForm = (ResumoAnaliseFaturamentoActionForm) actionForm;

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
        String faturamento = "S";
        sessao.setAttribute("faturamento", faturamento);
        BigDecimal valorTotalConta = new BigDecimal("0.00");
        BigDecimal valorConsumoAgua = new BigDecimal("0.00");
        BigDecimal valorConsumoEsgoto = new BigDecimal("0.00");
        BigDecimal valorDebitos = new BigDecimal("0.00");
        BigDecimal valorCreditos = new BigDecimal("0.00");
        BigDecimal valorImpostos = new BigDecimal("0.00");
        Integer qtdeContas = 0;
        Integer qtdeEconomias = 0;
        Integer volumeConsumidoAgua = 0;
        Integer volumeColetadoEsgoto = 0;
        
        InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper = 
        	(InformarDadosGeracaoRelatorioConsultaHelper)sessao.getAttribute("informarDadosGeracaoRelatorioConsultaHelper");
        List retornoConsulta = fachada.consultarResumoAnaliseFaturamento(informarDadosGeracaoRelatorioConsultaHelper);
        if( retornoConsulta != null && !retornoConsulta.equals("")
				&& retornoConsulta.size() != 0 ){
			for (int i = 0; i < retornoConsulta.size(); i++) {
				Object obj = retornoConsulta.get(i);
				if (obj instanceof Object[]) {
					Object[] retornoObject = (Object[]) obj;
					
					if((Integer)retornoObject[0] != null)
					{
						qtdeContas = qtdeContas + (Integer)retornoObject[0];
						resumoAnaliseFaturamentoActionForm.setQuantidadeContas(qtdeContas);
					}
					else
					{
						resumoAnaliseFaturamentoActionForm.setQuantidadeContas(null);
					}
					if((Integer)retornoObject[1] != null)
					{
						qtdeEconomias = qtdeEconomias + (Integer)retornoObject[1];
						resumoAnaliseFaturamentoActionForm.setQuantidadeEconomia(qtdeEconomias);
					}
					else
					{
						resumoAnaliseFaturamentoActionForm.setQuantidadeEconomia(null);
					}
					if((Integer)retornoObject[2] != null)
					{
						volumeConsumidoAgua = volumeConsumidoAgua + (Integer)retornoObject[2];
						resumoAnaliseFaturamentoActionForm.setConsumoAgua(volumeConsumidoAgua);
					}
					else
					{
						resumoAnaliseFaturamentoActionForm.setConsumoAgua(null);
					}
					if((BigDecimal)retornoObject[3] != null)
					{
						valorConsumoAgua = valorConsumoAgua.add((BigDecimal)retornoObject[3]);
						resumoAnaliseFaturamentoActionForm.setValorAgua(valorConsumoAgua);
						valorTotalConta = valorTotalConta.add((BigDecimal)retornoObject[3]);
					}
					else
					{
						resumoAnaliseFaturamentoActionForm.setValorAgua(null);
					}
					if((Integer)retornoObject[4] != null)
					{
						volumeColetadoEsgoto = volumeColetadoEsgoto + (Integer)retornoObject[4];
						resumoAnaliseFaturamentoActionForm.setConsumoEsgoto(volumeColetadoEsgoto);
					}
					else
					{
						resumoAnaliseFaturamentoActionForm.setConsumoEsgoto(null);
					}
					if((BigDecimal)retornoObject[5] != null)
					{
						valorConsumoEsgoto = valorConsumoEsgoto.add((BigDecimal)retornoObject[5]);
						resumoAnaliseFaturamentoActionForm.setValorEsgoto(valorConsumoEsgoto);
						valorTotalConta = valorTotalConta.add((BigDecimal)retornoObject[5]);
					}
					else
					{
						resumoAnaliseFaturamentoActionForm.setValorEsgoto(null);
					}
					if((BigDecimal)retornoObject[7] != null)
					{
						valorDebitos = valorDebitos.add((BigDecimal)retornoObject[7]);
						resumoAnaliseFaturamentoActionForm.setValorDebitos(valorDebitos);
						valorTotalConta = valorTotalConta.add((BigDecimal)retornoObject[7]);
					}
					else
					{
						resumoAnaliseFaturamentoActionForm.setValorDebitos(null);
					}
					if((BigDecimal)retornoObject[6] != null)
					{
						valorCreditos = valorCreditos.add((BigDecimal)retornoObject[6]);
						resumoAnaliseFaturamentoActionForm.setValorCreditos(valorCreditos);
						valorTotalConta = valorTotalConta.subtract((BigDecimal)retornoObject[6]);
					}
					else
					{
						resumoAnaliseFaturamentoActionForm.setValorCreditos(null);
					}
					if((BigDecimal)retornoObject[8] != null)
					{
						valorImpostos = valorImpostos.add((BigDecimal)retornoObject[8]);
						resumoAnaliseFaturamentoActionForm.setValorImpostos(valorImpostos);
						valorTotalConta = valorTotalConta.subtract((BigDecimal)retornoObject[8]);
					}
					else
					{
						resumoAnaliseFaturamentoActionForm.setValorImpostos(null);
					}
					valorTotalConta = valorTotalConta.setScale(2, BigDecimal.ROUND_HALF_UP);
					if(valorTotalConta.compareTo(new BigDecimal ("0.00")) == 0)
					{
						resumoAnaliseFaturamentoActionForm.setValorTotal(null);
					}
					else
					{
						resumoAnaliseFaturamentoActionForm.setValorTotal(valorTotalConta);
					}
					if((Integer)retornoObject[1] == null && (Integer)retornoObject[2] == null && (BigDecimal)retornoObject[3] == null
							 && (Integer)retornoObject[4] == null && (BigDecimal)retornoObject[5] == null && (BigDecimal)retornoObject[6] == null
							 && (BigDecimal)retornoObject[7] == null)
					{
						throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");		
					}
				}
			}
        }
        sessao.setAttribute("informarDadosGeracaoRelatorioConsultaHelper", informarDadosGeracaoRelatorioConsultaHelper);
        sessao.setAttribute("colecaoAnaliseFaturamento", retornoConsulta);
        
		/**
		 * Cria coleção de agrupamntos(uma coleção de object[3], agrupamento, id, descricao)
		 */
		Collection colecaoAgrupamento = fachada.criarColecaoAgrupamentoResumos(informarDadosGeracaoRelatorioConsultaHelper);
		
		sessao.setAttribute("colecaoAgrupamento", colecaoAgrupamento);
		sessao.setAttribute("mesAnoReferencia", Util.formatarAnoMesParaMesAno(informarDadosGeracaoRelatorioConsultaHelper.getAnoMesReferencia()));
		
        //devolve o mapeamento de retorno
        return retorno;
    }
}
