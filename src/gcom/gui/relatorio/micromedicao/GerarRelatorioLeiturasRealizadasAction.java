package gcom.gui.relatorio.micromedicao;

import java.util.Collection;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.RelatorioAnaliticoFaturamento;
import gcom.relatorio.micromedicao.RelatorioLeiturasRealizadas;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioLeiturasRealizadasAction extends ExibidorProcessamentoTarefaRelatorio {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		//Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("telaSucesso");
        
        RelatorioLeiturasRealizadasActionForm form = (RelatorioLeiturasRealizadasActionForm) actionForm;
        
      //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);
        Fachada fachada = Fachada.getInstancia();
        
        Usuario usuarioLogado = (Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado");
        
       // Fachada fachada = Fachada.getInstancia();
        //Variaveis
        String idFaturamentoGrupo = (String) form
                .getIdGrupoFaturamento();
        
        sessao.removeAttribute("indicadorAtualizar");
        
        String mesAno = (String)form.getMesAno();
        
        boolean parametroInformado = false;
        String anoMes = "";
        
        if (idFaturamentoGrupo != null
                && !idFaturamentoGrupo.trim().equalsIgnoreCase("")
                && !idFaturamentoGrupo.trim().equalsIgnoreCase("-1")
                && mesAno != null && !mesAno.trim().equals("")) {
        	String mes = mesAno.substring(0, 2);
        	String ano = mesAno.substring(3, 7);
        	anoMes = ano+mes;
            parametroInformado = true;
        }
        
        String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
        
        if(parametroInformado){
        	//chamar metodo de filtragem
        	try {
        		 Collection colecaoLeiturasRealizadas = fachada.pesquisarDadosRelatorioLeiturasRealizadas(
            			new Integer(anoMes), new Integer(idFaturamentoGrupo));
        		 
        		 if(colecaoLeiturasRealizadas.isEmpty()){
        			 throw new ActionServletException("atencao.relatorio.vazio");
        		 }
        		 
        		 //*******************************
        		 
        		 RelatorioLeiturasRealizadas relatorioLeiturasRealizadas = new RelatorioLeiturasRealizadas(
 						usuarioLogado);

        		 relatorioLeiturasRealizadas.addParametro(
 						"colecaoLeiturasRealizadas", colecaoLeiturasRealizadas);

        		 relatorioLeiturasRealizadas.addParametro("mesAno", Util.formatarAnoMesParaMesAno(anoMes));
        		 relatorioLeiturasRealizadas.addParametro("idGrupoFaturamento",
 						idFaturamentoGrupo + "");

 				// Parte do codigo q gera a data de vencimento
 				// dia do vencimento do grupo/mes ano de referencia do grupo + 1
 				FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
 				filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
 						FiltroFaturamentoGrupo.ID, idFaturamentoGrupo));

 				Collection colecaoFaturamento = Fachada.getInstancia()
 						.pesquisar(filtroFaturamentoGrupo,
 								FaturamentoGrupo.class.getName());
 				FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) colecaoFaturamento
 						.iterator().next();

 				if (tipoRelatorio == null) {
 					tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
 				}

 				relatorioLeiturasRealizadas.addParametro("tipoFormatoRelatorio",
 						Integer.parseInt(tipoRelatorio));

        		 
        		 //*******************************
        		
        		retorno = processarExibicaoRelatorio(relatorioLeiturasRealizadas,
						tipoRelatorio, httpServletRequest, httpServletResponse,
						actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}
        	
        }else{
        	throw new ActionServletException(
				"atencao.filtro.nenhum_parametro_informado");
        }
        
//  	 montando página de sucesso
		montarPaginaSucesso(httpServletRequest,
				"Gerar Relatório de Leituras Realizadas", "Voltar",
				"/exibirRelatorioLeiturasRealizadas.do");
		
		return retorno;
	}

}
