package gcom.gui.batch.relatorio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import gcom.batch.FiltroProcessoIniciado;
import gcom.batch.ProcessoIniciado;
import gcom.batch.ProcessoSituacao;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import gcom.util.filtro.ConectorAnd;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.ParametroSimples;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Classe respsável por montar a apresentação dos relatórios armazenados em
 * batch
 * 
 * 
 * @author Genival Barbosa
 * @date 03/07/2009
 */
public class ExibirAutorizarRelatoriosBatchAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("autorizarRelatorio");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		
		/*
		 * -------Iniciar datas (mes atual, mes anterior)--------
		 */
		if (httpServletRequest.getParameter("dataInicialSelecionado")== null || httpServletRequest.getParameter("dataFinalSelecionado") == null 
				||httpServletRequest.getParameter("dataInicialSelecionado").equals("") || httpServletRequest.getParameter("dataFinalSelecionado").equals("")) {
			
			Date dataInicial = new Date(System.currentTimeMillis()); 
			
			Calendar calendarData = Calendar.getInstance();  
			calendarData.setTime(dataInicial);  
			calendarData.add(Calendar.MONTH,-1);  
			dataInicial = calendarData.getTime(); 
			   
			SimpleDateFormat formatarDateInicial = new SimpleDateFormat("dd/MM/yyyy");    
			httpServletRequest.setAttribute("dataInicial", formatarDateInicial.format(dataInicial));
			
			
			Date dataFinal = new Date(System.currentTimeMillis());    
			SimpleDateFormat formatarDateFinal = new SimpleDateFormat("dd/MM/yyyy");    
			httpServletRequest.setAttribute("dataFinal", formatarDateFinal.format(dataFinal));
		} else {
			httpServletRequest.setAttribute("dataInicial",httpServletRequest.getParameter("dataInicialSelecionado"));
			httpServletRequest.setAttribute("dataFinal",httpServletRequest.getParameter("dataFinalSelecionado"));
		}
		
		/*
		 * ------------------------------------------------------
		 */
		
		//limitar pesquisa por no maximo 30 dias
		Date dataInicialDate = converterDataHora(httpServletRequest.getAttribute("dataInicial").toString(),"00:00:00");
		Date dataFinalDate = converterDataHora(httpServletRequest.getAttribute("dataFinal").toString(),"23:59:59");
		
		Calendar calendDataInic = Calendar.getInstance();
		calendDataInic.setTime(dataInicialDate);
		
		Calendar calendDataFinal = Calendar.getInstance();
		calendDataFinal.setTime(dataFinalDate);
		
		long diferenca = calendDataFinal.getTimeInMillis() - calendDataInic.getTimeInMillis();
		
		int tempoDia = 1000 * 60 * 60 * 24;
		
		long diasDiferenca = diferenca/tempoDia;
		
		if(diasDiferenca<=31){
			if (diasDiferenca>=0){
		
				FiltroProcessoIniciado filtroProcessoIniciado = new FiltroProcessoIniciado();
				
				filtroProcessoIniciado.adicionarCaminhoParaCarregamentoEntidade(FiltroProcessoIniciado.PROCESSO);
		        filtroProcessoIniciado.adicionarCaminhoParaCarregamentoEntidade(FiltroProcessoIniciado.USUARIO);
		        filtroProcessoIniciado.adicionarCaminhoParaCarregamentoEntidade(FiltroProcessoIniciado.PROCESSO_SITUACAO);
		  
		        
				filtroProcessoIniciado.adicionarParametro(new ParametroSimples(FiltroProcessoIniciado.PROCESSO_SITUACAO_ID,ProcessoSituacao.AGUARDANDO_AUTORIZACAO,ConectorAnd.CONECTOR_AND));
				filtroProcessoIniciado.adicionarParametro(new Intervalo(FiltroProcessoIniciado.DATA_HORA_AGENDAMENTO,
						dataInicialDate,dataFinalDate ));
				
				Collection colecaoProcessosIniciados = null;
				
				Map resultado = controlarPaginacao(httpServletRequest, retorno,
						filtroProcessoIniciado, ProcessoIniciado.class.getName());
				colecaoProcessosIniciados = (Collection) resultado.get("colecaoRetorno");
				retorno = (ActionForward) resultado.get("destinoActionForward");
				
		
				sessao.setAttribute("collProcessoIniciado", colecaoProcessosIniciados);
			}else{
				throw new ActionServletException(
	                    "atencao.processo_iniciado.datafinal_menor_datainicial", null, "");
			}
		}else{
			throw new ActionServletException(
                    "atencao.processo_iniciado.limite_trinta_dias", null, ""); 
		}
		
        return retorno;	
	}
	
private Date converterDataHora(String data, String hora) {
		
		SimpleDateFormat formatoDataHora = new SimpleDateFormat("dd/MM/yyyy k:mm:ss");
		try {
			return formatoDataHora.parse(data + " " + hora);
		} catch (ParseException e) {
			throw new ActionServletException("erro.sistema");

		}

	}

}
