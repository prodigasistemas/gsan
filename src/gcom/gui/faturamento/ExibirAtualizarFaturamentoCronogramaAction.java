package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FaturamentoGrupoCronogramaMensal;
import gcom.faturamento.FiltroFaturamentoAtividade;
import gcom.faturamento.FiltroFaturamentoAtividadeCronograma;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupoCronogramaMensal;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarFaturamentoCronogramaAction extends GcomAction {
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

        ActionForward retorno = actionMapping
                .findForward("atualizarFaturamentoCronograma");

        FaturamentoActionForm faturamentoActionForm = (FaturamentoActionForm) actionForm;
        
        Fachada fachada = Fachada.getInstancia();

        HttpSession sessao = httpServletRequest.getSession(false);

        String idFaturamentoGrupoCronogramaMensal = null;
        if(httpServletRequest.getParameter("idRegistroAtualizacao") != null){
        	
        	idFaturamentoGrupoCronogramaMensal = httpServletRequest.getParameter("idRegistroAtualizacao");
        }else{
        	
        	idFaturamentoGrupoCronogramaMensal = (String)sessao.getAttribute("idRegistroAtualizacao");
        }
        
        //joga faturamentogrupocronogramamensal na sessao para comparacao de concorrencia
        FiltroFaturamentoGrupoCronogramaMensal filtroFaturamentoGrupoCronogramaMensalSessao = new FiltroFaturamentoGrupoCronogramaMensal();
        filtroFaturamentoGrupoCronogramaMensalSessao.adicionarParametro(
        		new ParametroSimples(FiltroFaturamentoGrupoCronogramaMensal.ID,idFaturamentoGrupoCronogramaMensal));
        
        Collection colecaoFaturamentoGrupoCronogramaMensal = fachada.pesquisar(
        			filtroFaturamentoGrupoCronogramaMensalSessao, FaturamentoGrupoCronogramaMensal.class.getName());
        
        if(!colecaoFaturamentoGrupoCronogramaMensal.isEmpty()){
        	FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensalSessao = 
        		(FaturamentoGrupoCronogramaMensal)colecaoFaturamentoGrupoCronogramaMensal.iterator().next();
        	
        	sessao.setAttribute("faturamentoGrupoCronogramaMensalSessao", faturamentoGrupoCronogramaMensalSessao);
        }

        sessao.setAttribute("idGrupoFaturamento", idFaturamentoGrupoCronogramaMensal);

        FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
        filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
                FiltroFaturamentoGrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
        
        Collection faturamentoGrupos = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
        if (!faturamentoGrupos.isEmpty()) {
            
        	sessao.setAttribute("faturamentoGrupos", faturamentoGrupos);
        } else {
            
        	throw new ActionServletException("erro.naocadastrado", null,
                    "grupo de faturamento");
        }

        FiltroFaturamentoGrupoCronogramaMensal filtroFaturamentoGrupoCronogramaMensal = new FiltroFaturamentoGrupoCronogramaMensal();
        FiltroFaturamentoAtividadeCronograma filtroFaturamentoAtividadeCronograma = new FiltroFaturamentoAtividadeCronograma();
        Collection faturamentoGrupoCronogramaMensais = null;
        Collection faturamentoAtividadeCronogramas = new ArrayList();

        if (idFaturamentoGrupoCronogramaMensal != null
                && !idFaturamentoGrupoCronogramaMensal.trim().equalsIgnoreCase("")) {
            filtroFaturamentoGrupoCronogramaMensal.adicionarParametro(
            		new ParametroSimples(FiltroFaturamentoGrupoCronogramaMensal.ID, 
            					idFaturamentoGrupoCronogramaMensal));
            //Pesquisa Faturamento Grupo Cronograma Mensal
            faturamentoGrupoCronogramaMensais = fachada.pesquisar(filtroFaturamentoGrupoCronogramaMensal,
                    FaturamentoGrupoCronogramaMensal.class.getName());
            if (!faturamentoGrupoCronogramaMensais.isEmpty()) {
                //Pesquisa Faturamento Atividade Cronograma do Faturamento
                // Grupo Cronograma Mensal
                filtroFaturamentoAtividadeCronograma
                        .adicionarParametro(new ParametroSimples(
                                FiltroFaturamentoAtividadeCronograma.FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_ID,
                                idFaturamentoGrupoCronogramaMensal));
                filtroFaturamentoAtividadeCronograma.adicionarCaminhoParaCarregamentoEntidade("faturamentoAtividade");
                faturamentoAtividadeCronogramas = fachada.pesquisar(filtroFaturamentoAtividadeCronograma,
                        FaturamentoAtividadeCronograma.class.getName());
                
                if (!faturamentoAtividadeCronogramas.isEmpty()) {
                    
                	FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal = 
                    	(FaturamentoGrupoCronogramaMensal) faturamentoGrupoCronogramaMensais.iterator().next();
                    
                    //Seta os valores no form
                    faturamentoActionForm.setIdFaturamentoGrupoCronogramaMensal(idFaturamentoGrupoCronogramaMensal);
                    faturamentoActionForm.setIdGrupoFaturamento(
                    		faturamentoGrupoCronogramaMensal.getFaturamentoGrupo().getId().toString());
                    faturamentoActionForm.setMesAno(faturamentoGrupoCronogramaMensal.getMesAno());
                    
                    sessao.setAttribute("mesAno", faturamentoGrupoCronogramaMensal.getMesAno());
                    
                }
            }

        }
        //Pega as atividade q ainda nao estao associadas aum faturamento
        // cronograma atividade
        FiltroFaturamentoAtividade filtroFaturamentoAtividade = new FiltroFaturamentoAtividade();
        filtroFaturamentoAtividade.adicionarParametro(new ParametroSimples(
                FiltroFaturamentoAtividade.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroFaturamentoAtividade.setCampoOrderBy(FiltroFaturamentoAtividade.ORDEM_REALIZACAO);
        
        Iterator iteratorFaturamentoAtividadeCronogramas = faturamentoAtividadeCronogramas.iterator();
        
        FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = null;
        
        while (iteratorFaturamentoAtividadeCronogramas.hasNext()) {
            faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) 
            iteratorFaturamentoAtividadeCronogramas.next();
            
            filtroFaturamentoAtividade.adicionarParametro(
            		new ParametroSimplesDiferenteDe(FiltroFaturamentoAtividade.ID, 
            				faturamentoAtividadeCronograma.getFaturamentoAtividade().getId()));
        }
        //pesquisa as atividades q ainda nao fazem parte do cronograma
        Collection faturamentoAtividades = fachada.pesquisar(filtroFaturamentoAtividade, 
        		FaturamentoAtividade.class.getName());
        
        //intera a colecao montando objetos do tipo FaturamentoAtividadeCronograma
        //e os coloca em uma coleção nova
        Iterator iteratorFaturamentoAtividades = faturamentoAtividades.iterator();
        Collection colecaoFaturamentoAtividadeCronogramaNovo = new ArrayList();
        FaturamentoAtividadeCronograma faturamentoAtividadeCronogramaNovo = null;
        while(iteratorFaturamentoAtividades.hasNext()){
        	faturamentoAtividadeCronogramaNovo = new FaturamentoAtividadeCronograma();
        	
        	faturamentoAtividadeCronogramaNovo.setFaturamentoAtividade(
        			(FaturamentoAtividade)iteratorFaturamentoAtividades.next());
        	
        	colecaoFaturamentoAtividadeCronogramaNovo.add(faturamentoAtividadeCronogramaNovo);
        }
        //junção das coleções para ordenação
        colecaoFaturamentoAtividadeCronogramaNovo.addAll(faturamentoAtividadeCronogramas);
        //ordenação através do sort
        Collections.sort((List) colecaoFaturamentoAtividadeCronogramaNovo, new Comparator() {
            public int compare(Object a, Object b) {
                    Integer faturamentoAtividadeCronograma1 = new Integer(((FaturamentoAtividadeCronograma) a).getFaturamentoAtividade().getOrdemRealizacao());
                    Integer faturamentoAtividadeCronograma2 = new Integer(((FaturamentoAtividadeCronograma) b).getFaturamentoAtividade().getOrdemRealizacao());

                    return faturamentoAtividadeCronograma1.compareTo(faturamentoAtividadeCronograma2);
            }
        });
        
        Collection colecaoAtividadeCronogramaAtualizarHelp =  new ArrayList();
        
        FiltroFaturamentoGrupoCronogramaMensal filtroCronogramaMensal = new FiltroFaturamentoGrupoCronogramaMensal();

		filtroCronogramaMensal.adicionarParametro(new ParametroSimples(
				FiltroFaturamentoGrupoCronogramaMensal.FATURAMENTO_GRUPO,
				faturamentoActionForm.getIdGrupoFaturamento()));

		String anoMes = Util
				.formatarMesAnoParaAnoMesSemBarra(faturamentoActionForm
						.getMesAno());

		Integer anoMesMenosUm = Util.subtraiAteSeisMesesAnoMesReferencia(
				new Integer(anoMes), 1);

		filtroCronogramaMensal.adicionarParametro(new ParametroSimples(
				FiltroFaturamentoGrupoCronogramaMensal.REFERENCIA,
				anoMesMenosUm));

		Collection colecaoCronogramaMensal = fachada.pesquisar(
				filtroCronogramaMensal, FaturamentoGrupoCronogramaMensal.class
						.getName());

		FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal = (FaturamentoGrupoCronogramaMensal) Util
				.retonarObjetoDeColecao(colecaoCronogramaMensal);

		Integer idFatGrupoCronogramaMensal = null;
		if( faturamentoGrupoCronogramaMensal != null ){
		idFatGrupoCronogramaMensal = faturamentoGrupoCronogramaMensal
				.getId();
		}

		for (Iterator iter = colecaoFaturamentoAtividadeCronogramaNovo
				.iterator(); iter.hasNext();) {

			FaturamentoAtividadeAtualizarHelp faturamentoAtividadeAtualizarHelp = new FaturamentoAtividadeAtualizarHelp();

			FaturamentoAtividadeCronograma atividadeCronograma = (FaturamentoAtividadeCronograma) iter
					.next();

			if( atividadeCronograma != null && atividadeCronograma.getId() == null ){
				continue;
			}
			
			FaturamentoAtividadeCronograma fatAtividadeCronograma = null;
				
			if( idFatGrupoCronogramaMensal != null ){	
				
				FiltroFaturamentoAtividadeCronograma filtroAtividadeCronograma = new FiltroFaturamentoAtividadeCronograma();

				filtroAtividadeCronograma.adicionarParametro(new ParametroSimples(
								FiltroFaturamentoAtividadeCronograma.FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_ID,
									idFatGrupoCronogramaMensal));

				filtroAtividadeCronograma.adicionarParametro(new ParametroSimples(
								FiltroFaturamentoAtividadeCronograma.FATURAMENTO_ATIVIDADE_ID,
									atividadeCronograma.getFaturamentoAtividade().getId()));

				Collection colecaoAtividadeCronograma = fachada.pesquisar(
						filtroAtividadeCronograma, FaturamentoAtividadeCronograma.class.getName());

				fatAtividadeCronograma = (FaturamentoAtividadeCronograma) Util
						.retonarObjetoDeColecao(colecaoAtividadeCronograma);
			}


			Date dataRealizacaoMesAnterior = null;

			if( fatAtividadeCronograma != null && fatAtividadeCronograma.getDataRealizacao() != null){
				dataRealizacaoMesAnterior = fatAtividadeCronograma.getDataRealizacao();
			}
			if( dataRealizacaoMesAnterior == null && fatAtividadeCronograma != null && 
					fatAtividadeCronograma.getDataPrevista() != null ){
				dataRealizacaoMesAnterior = fatAtividadeCronograma.getDataPrevista();	
			}
					
			Date dataRealizacaoMesAtual = null;
			Integer qtdDias = null;
			
			if( atividadeCronograma != null && atividadeCronograma.getDataRealizacao() != null ){
				
				dataRealizacaoMesAtual = atividadeCronograma.getDataRealizacao();
			}else if( dataRealizacaoMesAtual == null && atividadeCronograma != null 
					&& atividadeCronograma.getDataPrevista() != null ){
				
				dataRealizacaoMesAtual = atividadeCronograma.getDataPrevista();	
			}else if( dataRealizacaoMesAtual == null ){
				
				dataRealizacaoMesAtual = new Date();
			}
			
			if(fatAtividadeCronograma == null || dataRealizacaoMesAnterior == null ){
				
				dataRealizacaoMesAnterior = dataRealizacaoMesAtual;
			}


			if (atividadeCronograma != null && fatAtividadeCronograma !=null) {

				qtdDias = Util.obterQuantidadeDiasEntreDuasDatas(dataRealizacaoMesAnterior , dataRealizacaoMesAtual);

				faturamentoAtividadeAtualizarHelp.setFaturamentoAtividadeCronograma(atividadeCronograma);
				faturamentoAtividadeAtualizarHelp.setQuantidadeDias(qtdDias.toString());
				faturamentoAtividadeAtualizarHelp.setId(fatAtividadeCronograma.getId());
				faturamentoAtividadeAtualizarHelp.setDataRealizacaoMesAnterior(Util.formatarData(dataRealizacaoMesAnterior));
				faturamentoAtividadeAtualizarHelp.setIdFaturamentoAtividade(atividadeCronograma.getFaturamentoAtividade().getId().toString());
				colecaoAtividadeCronogramaAtualizarHelp.add(faturamentoAtividadeAtualizarHelp);

			} else {
				
				qtdDias = Util.obterQuantidadeDiasEntreDuasDatas( dataRealizacaoMesAnterior , dataRealizacaoMesAtual);
				
				faturamentoAtividadeAtualizarHelp.setFaturamentoAtividadeCronograma(atividadeCronograma);
	            faturamentoAtividadeAtualizarHelp.setQuantidadeDias(qtdDias.toString());
	            faturamentoAtividadeAtualizarHelp.setDataRealizacaoMesAnterior(Util.formatarData(dataRealizacaoMesAnterior));
	            faturamentoAtividadeAtualizarHelp.setIdFaturamentoAtividade(atividadeCronograma.getFaturamentoAtividade().getId().toString());
	            
	            if ( atividadeCronograma != null ) { 
	            	faturamentoAtividadeAtualizarHelp.setId(atividadeCronograma.getId());
	            }
	            
				colecaoAtividadeCronogramaAtualizarHelp.add(faturamentoAtividadeAtualizarHelp);	
			}
		}
		
        sessao.setAttribute("colecaoFaturamentoAtividadeCronograma", colecaoAtividadeCronogramaAtualizarHelp);
        
        /**
		 * Autor: Hugo Leonardo
		 * Data: 22/06/2010
		 * CRC: 4694
		 * Analista: Eduardo Borges
		 * comentardo o código abaixo
		 */
        
        // Desabilita o check caso o AnoMes informado seja superior ao AnoMes do FaturamentoGrupo. 
		if(faturamentoActionForm.getMesAno() != null &&
				!faturamentoActionForm.getMesAno().toString().equals("")){
			
			FiltroFaturamentoGrupo filtroFatGrupo= new FiltroFaturamentoGrupo();
			filtroFatGrupo.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoGrupo.ID, faturamentoActionForm.getIdGrupoFaturamento()));
			
			Collection colecaoFaturamentoGrupo = fachada.pesquisar(filtroFatGrupo,
					FaturamentoGrupo.class.getName());
			
			FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) 
				Util.retonarObjetoDeColecao(colecaoFaturamentoGrupo);
			
			if(Util.compararAnoMesReferencia(anoMes, faturamentoGrupo.getAnoMesReferencia().toString(), ">")){
				sessao.setAttribute("desabilitaCheck", true);
			}else{
				sessao.removeAttribute("desabilitaCheck");
			}
		}
        
        /*
        //  Author:Hugo Amorim Data:28/04/2010 CRC:4040
		//
		//	Em Gsan, Faturamento, cronograma, manter cronograma, 
		// coluna "comandar" todos os checkbox´s venham por default desmarcados. 
		// (Isso só deve ocorre quando a empresa for cosanpa)
		//	
        if(sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase("COSANPA")){
        	sessao.setAttribute("EmpresaCosanpa", true);
		}else{
			sessao.removeAttribute("EmpresaCosanpa");
		}
        */
        
        return retorno;

    }

}
