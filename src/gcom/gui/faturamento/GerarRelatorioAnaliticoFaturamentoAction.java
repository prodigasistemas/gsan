package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.relatorio.faturamento.RelatorioAnaliticoFaturamentoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.RelatorioAnaliticoFaturamento;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioAnaliticoFaturamentoAction extends ExibidorProcessamentoTarefaRelatorio {

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("telaSucesso");

        RelatorioAnaliticoFaturamentoActionForm form = (RelatorioAnaliticoFaturamentoActionForm) actionForm;
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
        		 Collection colecaoAnaliticoFaturamento = fachada.pesquisarDadosRelatorioAnaliticoFaturamento( 
            			new Integer(anoMes), new Integer(idFaturamentoGrupo), new Integer(form.getIndicadorLocalidadeInformatizada()),
            			(Collection)sessao.getAttribute("colecaoLocalidades"),
            			(Collection)sessao.getAttribute("colecaoSetor"),
            			(Collection)sessao.getAttribute("colecaoQuadras"),
            			tipoRelatorio, usuarioLogado);
        		 
        		 if(colecaoAnaliticoFaturamento.isEmpty()){
        			 throw new ActionServletException("atencao.relatorio.vazio");
        		 }
        		 
        		 //*******************************
        		 
        		 RelatorioAnaliticoFaturamento relatorioAnaliticoFaturamento = new RelatorioAnaliticoFaturamento(
 						usuarioLogado);

 				relatorioAnaliticoFaturamento.addParametro(
 						"colecaoAnaliticoFaturamento", colecaoAnaliticoFaturamento);

 				relatorioAnaliticoFaturamento.addParametro("mesAno", Util.formatarAnoMesParaMesAno(anoMes));
 				relatorioAnaliticoFaturamento.addParametro("idGrupoFaturamento",
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

 				String vencimento = Util.adicionarZerosEsquedaNumero(2,
 						faturamentoGrupo.getDiaVencimento() + "")
 						+ "/"
 						+ Util
 								.somaMesMesAnoComBarra(
 										Util
 												.formatarAnoMesParaMesAno(anoMes),
 										1);

 				relatorioAnaliticoFaturamento.addParametro("vencimento",
 						vencimento);

 				if (tipoRelatorio == null) {
 					tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
 				}

 				relatorioAnaliticoFaturamento.addParametro("tipoFormatoRelatorio",
 						Integer.parseInt(tipoRelatorio));

        		 
        		 //*******************************
        		
        		retorno = processarExibicaoRelatorio(relatorioAnaliticoFaturamento,
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
				"Gerar Relatório Mapa de Controle das Contas", "Voltar",
				"/exibirMapaControContalaRelatorioAction.do");
		
        return retorno;
    }

}
