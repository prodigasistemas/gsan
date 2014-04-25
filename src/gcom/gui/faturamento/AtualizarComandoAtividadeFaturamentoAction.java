package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoAtivCronRota;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarComandoAtividadeFaturamentoAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Carrega a instancia da fachada
        Fachada fachada = Fachada.getInstancia();

        //Carrega o objeto sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        // Faturamento Atividade Cronograma selecionado
        FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) sessao
                .getAttribute("faturamentoAtividadeCronograma");

        Collection colecaoFaturamentoAtividadeCronogramaRotaUniao = (Collection) sessao
                .getAttribute("colecaoFaturamentoAtividadeCronogramaRotaUniao");

        // [FS0005] Verificar exclusão de rotas
        if (colecaoFaturamentoAtividadeCronogramaRotaUniao == null
                || colecaoFaturamentoAtividadeCronogramaRotaUniao.isEmpty()) {
            throw new ActionServletException("atencao.faturamento_nenhuma_rota");
        }
        
        
       //Atualizando a data de vencimento da rota de acordo com o informado pelo usuário
	   //-------------------------------------------------------------------------------------------
       SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        
       Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
        
	   Iterator colecaoFaturamentoAtividadeCronogramaRotaUniaoIt = 
	   colecaoFaturamentoAtividadeCronogramaRotaUniao.iterator();
	   FaturamentoAtivCronRota faturamentoAtivCronRota;
	   String dataVencimentoRota = null;
	   Date dataVencimentoRotaJSPObjeto = null;
	        
	   while (colecaoFaturamentoAtividadeCronogramaRotaUniaoIt.hasNext()) {
		   
		   	faturamentoAtivCronRota = (FaturamentoAtivCronRota) colecaoFaturamentoAtividadeCronogramaRotaUniaoIt.next();

			if (requestMap.get("data"
				+ faturamentoAtivCronRota.getRota().getId().intValue()) != null) {

				dataVencimentoRota = (requestMap.get("data" + faturamentoAtivCronRota.getRota().getId().intValue()))[0];

				if (dataVencimentoRota == null
					|| dataVencimentoRota.equalsIgnoreCase("")) {
						throw new ActionServletException(
								"atencao.campo_texto.obrigatorio", null,
								"Data de vencimento da rota ");
				}

				try {
					dataVencimentoRotaJSPObjeto = formatoData.parse(dataVencimentoRota);
				} catch (ParseException ex) {
					dataVencimentoRotaJSPObjeto = null;
				}
				
				faturamentoAtivCronRota.setDataContaVencimento(dataVencimentoRotaJSPObjeto);
			}
	   	}
	    

        // Atualizar comando
        fachada.atualizarComandoAtividadeFaturamento(
                faturamentoAtividadeCronograma,
                colecaoFaturamentoAtividadeCronogramaRotaUniao);

        montarPaginaSucesso(httpServletRequest,
                "Comando de Atividade de Faturamento " + faturamentoAtividadeCronograma.getFaturamentoAtividade().getDescricao() + 
                " do " + faturamentoAtividadeCronograma.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo().getDescricaoAbreviada() + 
                " referência " + Util.formatarAnoMesParaMesAno(faturamentoAtividadeCronograma.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo().getAnoMesReferencia()) + 
                " atualizado com sucesso.",
                "Realizar outra Manutenção de Comando de Atividade de Faturamento",
                "filtrarComandoAtividadeFaturamentoAction.do");
        
        
        
        //Limpando todos os objetos colocados na sessão
        sessao.removeAttribute("dataCorrente");
        sessao.removeAttribute("exibirCampoVencimentoGrupo");
        sessao.removeAttribute("faturamentoAtividadeCronograma");
        sessao.removeAttribute("colecaoFaturamentoAtividadeCronogramaRota");
        sessao.removeAttribute("colecaoRotasSelecionadas");
        sessao.removeAttribute("colecaoRotasSelecionadasUsuario");
        sessao.removeAttribute("colecaoFaturamentoAtividadeCronogramaRotaUniao");
        sessao.removeAttribute("PesquisarActionForm");
        sessao.removeAttribute("InserirComandoAtividadeFaturamentoActionForm");
        sessao.removeAttribute("statusWizard");

        return retorno;
    }
}
