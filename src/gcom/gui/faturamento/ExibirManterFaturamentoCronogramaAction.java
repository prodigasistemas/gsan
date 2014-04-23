package gcom.gui.faturamento;

import gcom.faturamento.FaturamentoGrupoCronogramaMensal;
import gcom.faturamento.FiltroFaturamentoGrupoCronogramaMensal;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirManterFaturamentoCronogramaAction extends GcomAction {

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
                .findForward("manterFaturamentoCronograma");

       // Fachada fachada = Fachada.getInstancia();

        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
        
        FiltroFaturamentoGrupoCronogramaMensal filtroFaturamentoGrupoCronogramaMensal = (FiltroFaturamentoGrupoCronogramaMensal) sessao
                .getAttribute("filtroFaturamentoGrupoCronogramaMensal");

//        FiltroFaturamentoGru
//        Collection faturamentoGrupos = fachada.pesquisar(
//               filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
//        //Coleçao q vai guardar os faturamentos grupos cronogramas mensais
//        // filtrados
      //  Collection faturamentoGrupoCronogramaMensaisFiltrados = new HashSet();

//        FiltroFaturamentoGrupoCronogramaMensal filtroFaturamentoGrupoCronogramaMensal = new FiltroFaturamentoGrupoCronogramaMensal();

//        if (!faturamentoGrupos.isEmpty()) {
//        	Iterator iteratorFaturamentoGrupo = faturamentoGrupos.iterator();
        	
        	
//            FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) faturamentoGrupos
//                    .iterator().next();
//
//            filtroFaturamentoGrupoCronogramaMensal
//                    .adicionarParametro(new ParametroSimples(
//                            FiltroFaturamentoGrupoCronogramaMensal.ID_FATURAMENTO_GRUPO,
//                            filtroFaturamentoGrupoCronogramaMensalSessao.getId()));
//            filtroFaturamentoGrupoCronogramaMensal
//                    .adicionarParametro(new MaiorQue(
//                            FiltroFaturamentoGrupoCronogramaMensal.ANO_MES_REFERENCIA,
//                            faturamentoGrupo.getAnoMesReferencia()));
//            
//            filtroFaturamentoGrupoCronogramaMensal.setCampoOrderBy(FiltroFaturamentoGrupoCronogramaMensal.REFERENCIA);
//            if(httpServletRequest.getAttribute("anoMes") != null && !httpServletRequest.getAttribute("anoMes").toString().trim().equalsIgnoreCase("")){
//            	filtroFaturamentoGrupoCronogramaMensal.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupoCronogramaMensal.ANO_MES_REFERENCIA, 
//            			httpServletRequest.getAttribute("anoMes").toString()));
//            }

   	
//        	 novo componente de paginação
        	filtroFaturamentoGrupoCronogramaMensal.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
        	
        	filtroFaturamentoGrupoCronogramaMensal.setCampoOrderBy(FiltroFaturamentoGrupoCronogramaMensal.FATURAMENTO_GRUPO,
        			FiltroFaturamentoGrupoCronogramaMensal.REFERENCIA);
            Map resultado = controlarPaginacao(httpServletRequest, retorno,
            		filtroFaturamentoGrupoCronogramaMensal, FaturamentoGrupoCronogramaMensal.class.getName());
            Collection faturamentoGrupoCronogramaMensais = (Collection) resultado.get("colecaoRetorno");
    		retorno = (ActionForward) resultado.get("destinoActionForward");
    		

    		// [FS0004] Nenhum registro encontrado				
    		if (faturamentoGrupoCronogramaMensais == null || faturamentoGrupoCronogramaMensais.isEmpty()) {
    			// Nenhuma Localidade cadastrado
    			throw new ActionServletException("atencao.nao_cadastrado.faturamento_atividade_cronograma");
    		}
            
//---Fim
//	            Collection faturamentoGrupoCronogramaMensais = fachada.pesquisar(
//	                    filtroFaturamentoGrupoCronogramaMensal,
//	                    FaturamentoGrupoCronogramaMensal.class.getName());

	            
	            //verifica se a algum Faturamento Grupo Cronograma Mensal a ser
	            // retornado pelo filtro

	            if(identificadorAtualizar == null){
	            	identificadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
	            }
	            
//	            if (!faturamentoGrupoCronogramaMensais.isEmpty()) {
//	
//	            	//ordenação através do sort---Feita pelo mesAnoReferencia
//	                Collections.sort((List) faturamentoGrupoCronogramaMensais, new Comparator() {
//	                    public int compare(Object a, Object b) {
//	                            String faturamentoGrupoCronogramaMensail1 = ((FaturamentoGrupoCronogramaMensal) a).getAnoMesReferencia().toString();
//	                            String faturamentoGrupoCronogramaMensail2 = ((FaturamentoGrupoCronogramaMensal) b).getAnoMesReferencia().toString();
//	
//	                            return faturamentoGrupoCronogramaMensail1.compareTo(faturamentoGrupoCronogramaMensail2);
//	                    }
//	                });
	            	
//	            	Iterator iteratorFaturamentoGrupoCronogramaMensal = faturamentoGrupoCronogramaMensais
//	                        .iterator();
//	                FiltroFaturamentoAtividadeCronograma filtroFaturamentoAtividadeCronograma = new FiltroFaturamentoAtividadeCronograma();
//	                FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal = null;
//	                Collection faturamentoAtividadeCronogramas = null;
	
//	                while (iteratorFaturamentoGrupoCronogramaMensal.hasNext()) {
//	                    //Procura Faturamento Atividade Cronogramas Associados a o
//	                    // Grupo Cronograma Mensal
//	                    faturamentoAtividadeCronogramas = null;
//	                    filtroFaturamentoAtividadeCronograma
//	                            .limparListaParametros();
//	                    faturamentoGrupoCronogramaMensal = (FaturamentoGrupoCronogramaMensal) iteratorFaturamentoGrupoCronogramaMensal
//	                            .next();
//	                    filtroFaturamentoAtividadeCronograma
//	                            .adicionarParametro(new ParametroSimples(
//	                                    FiltroFaturamentoAtividadeCronograma.FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_ID,
//	                                    faturamentoGrupoCronogramaMensal.getId()));
//	                    faturamentoAtividadeCronogramas = fachada.pesquisar(
//	                            filtroFaturamentoAtividadeCronograma,
//	                            FaturamentoAtividadeCronograma.class.getName());
//	                    if (!faturamentoAtividadeCronogramas.isEmpty()) {
//	                        //Parte para testar se a alguma atividade em aberto
//	                        Iterator iteratorFaturamentoAtividadeCronograma = faturamentoAtividadeCronogramas
//	                                .iterator();
//	                        FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = null;
//	
//	                        while (iteratorFaturamentoAtividadeCronograma.hasNext()) {
//	                            faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) iteratorFaturamentoAtividadeCronograma
//	                                    .next();
//	                            if (faturamentoAtividadeCronograma
//	                                    .getDataRealizacao() == null) {
//	                                faturamentoGrupoCronogramaMensaisFiltrados
//	                                        .add(faturamentoGrupoCronogramaMensal);
//	                            }
//	                        }
//	                    }
//	                }
//	            }
//        }
        
        if (faturamentoGrupoCronogramaMensais.isEmpty()) {
            throw new ActionServletException("atencao.nao_cadastrado.faturamento_atividade_cronograma");
        } else {
        	if(faturamentoGrupoCronogramaMensais.size() == 1 && identificadorAtualizar != null && !identificadorAtualizar.trim().equals("")){
//        		 caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("exibirAtualizar");
				FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal = (FaturamentoGrupoCronogramaMensal)faturamentoGrupoCronogramaMensais.iterator().next();
				sessao.setAttribute("idRegistroAtualizacao", new Integer (faturamentoGrupoCronogramaMensal.getId()).toString());
				sessao.setAttribute("voltar", "filtrar");
        	}else{
        		sessao.setAttribute("faturamentoGruposCronogramaMensais",
        				faturamentoGrupoCronogramaMensais);
        		FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal = (FaturamentoGrupoCronogramaMensal)faturamentoGrupoCronogramaMensais.iterator().next();
        		sessao.setAttribute("idRegistroAtualizacao", new Integer (faturamentoGrupoCronogramaMensal.getId()).toString());
        		sessao.setAttribute("voltar", "manter");
        	}
        }

        return retorno;

    }
}
