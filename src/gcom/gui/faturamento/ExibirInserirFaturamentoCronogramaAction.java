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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
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
public class ExibirInserirFaturamentoCronogramaAction extends GcomAction {

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
                .findForward("inserirFaturamentoCronograma");

        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        FaturamentoActionForm faturamentoActionForm = (FaturamentoActionForm) actionForm;
        
        Fachada fachada = Fachada.getInstancia();
        
        //CARREGANDO OS GRUPOS
        FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
        Collection faturamentoGrupos = null;

        filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.INDICADOR_USO,
        ConstantesSistema.INDICADOR_USO_ATIVO));
        
        filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.ID);
        faturamentoGrupos = fachada.pesquisar(filtroFaturamentoGrupo,
        FaturamentoGrupo.class.getName());
        
        if ( Util.isVazioOrNulo(faturamentoGrupos)) {
            throw new ActionServletException("erro.naocadastrado", null,
            "grupo de faturamento");
        }
        
        sessao.setAttribute("faturamentoGrupos", faturamentoGrupos);

        
        //CARREGANDO AS ATIVIDADES
        FiltroFaturamentoAtividade filtroFaturamentoAtividade = new FiltroFaturamentoAtividade();
        Collection faturamentoAtividades = null;
        
        filtroFaturamentoAtividade.adicionarParametro(new ParametroSimples(
        FiltroFaturamentoAtividade.INDICADOR_USO,
        ConstantesSistema.INDICADOR_USO_ATIVO));
        
	    filtroFaturamentoAtividade.setCampoOrderBy(FiltroFaturamentoAtividade.ORDEM_REALIZACAO);
	    faturamentoAtividades = fachada.pesquisar( filtroFaturamentoAtividade , FaturamentoAtividade.class.getName());
	    
	    
	    //MONTANDO O OBJETO PARA EXIBIÇÃO
	    if(httpServletRequest.getParameter("menu")!=null && 
	       httpServletRequest.getParameter("menu").equals("sim")){
	    
	    	Collection faturamentoAtividadesHelp  = new ArrayList();
	    
	    	for (Iterator iter = faturamentoAtividades.iterator(); iter.hasNext();) {
			
		    	FaturamentoAtividade faturamentoAtividade = (FaturamentoAtividade) iter.next();
				
		    	FaturamentoCronogramaAtividadeHelp faturamentoCronogramaAtividadeHelp =
				new FaturamentoCronogramaAtividadeHelp();
				
		    	faturamentoCronogramaAtividadeHelp.setFaturamentoAtividade(faturamentoAtividade);
				faturamentoAtividadesHelp.add(faturamentoCronogramaAtividadeHelp);
				
				/**
				 * Autor: Hugo Leonardo
				 * Data: 22/06/2010
				 * CRC: 4694
				 * Analista: Eduardo Borges
				 * 
				 * O indicador de comando da atividade vai ser definido pelo (FTAT_ICCOMANDO).
				 * Caso (FTAT_ICCOMANDO = 1) será Ativo e Desmarcado;
				 * Caso (FTAT_ICCOMANDO = 2) será Inativo;
				 * Caso (FTAT_ICCOMANDO = 3) será Ativo e Marcado;
				 */
			
				if (faturamentoAtividade.getIndicadorPossibilidadeComandoAtividade() != null
						&& (faturamentoAtividade.getIndicadorPossibilidadeComandoAtividade().intValue() == ConstantesSistema.SIM.intValue()
								|| faturamentoAtividade.getIndicadorPossibilidadeComandoAtividade().intValue() == ConstantesSistema.TODOS.intValue())){
				
					faturamentoCronogramaAtividadeHelp.setComandar(faturamentoAtividade.getIndicadorPossibilidadeComandoAtividade().toString());
					/*
					if (faturamentoAtividade.getId().intValue() != FaturamentoAtividade.FATURAR_GRUPO.intValue()){
						faturamentoCronogramaAtividadeHelp.setComandar(faturamentoAtividade.getIndicadorPossibilidadeComandoAtividade().toString());
					}else{
						faturamentoCronogramaAtividadeHelp.setComandar("2");
					}
					*/
				}	 
				/*
				// 	Author:Hugo Amorim Data:28/04/2010 CRC:4040
				//
				//	Em Gsan, Faturamento, cronograma, manter cronograma, 
				// coluna "comandar" todos os checkbox´s venham por default desmarcados. 
				// (Isso só deve ocorre quando a empresa for cosanpa)
				//	
				if(sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase("COSANPA")){
					faturamentoCronogramaAtividadeHelp.setComandar("2");
				}	
				*/
			}
  
	        if ( faturamentoAtividades == null || faturamentoAtividades.isEmpty() ) {
	        	throw new ActionServletException("atencao.naocadastrado", null,"atividade");
	        } 
	        else {     	
	        	sessao.setAttribute("faturamentoAtividades", faturamentoAtividadesHelp);       	
	        }        
	    }
	    
        sessao.setAttribute("FaturamentoActionForm", faturamentoActionForm);
        
        String obterDataRealizacaoAnterior = httpServletRequest.getParameter("obterDataRealizacaoAnterior");
        
        this.obterDataRealizacaoAnteriorPorAtividade(obterDataRealizacaoAnterior, faturamentoActionForm,
        fachada, sessao);
        
        //DATA ATUAL
        httpServletRequest.setAttribute("dataAtual", Util.formatarData(new Date()));
        
		return retorno;
	}
    
    
    public void obterDataRealizacaoAnteriorPorAtividade(String obterDataRealizacaoAnterior,
    		FaturamentoActionForm faturamentoActionForm, Fachada fachada, HttpSession sessao){
    	
    	if (obterDataRealizacaoAnterior != null && 
    		!obterDataRealizacaoAnterior.equalsIgnoreCase("")) {

			if (faturamentoActionForm.getIdGrupoFaturamento() != null && 
				faturamentoActionForm.getIdGrupoFaturamento().toString().equals("-1")) {
				
				throw new ActionServletException(
				"atencao.preencha.campos.obrigatorio");
			}
			
			if (faturamentoActionForm.getMesAno() != null && 
				faturamentoActionForm.getMesAno().toString().equals("")) {
				
				throw new ActionServletException(
				"atencao.preencha.campos.obrigatorio");
			}
        	
			String anoMes = Util.formatarMesAnoParaAnoMesSemBarra(faturamentoActionForm
					.getMesAno());
			
			// Desabilita o check caso o AnoMes informado seja superior ao AnoMes do FaturamentoGrupo. 
			if(faturamentoActionForm.getMesAno() != null &&
					!faturamentoActionForm.getMesAno().toString().equals("")){
				
				FiltroFaturamentoGrupo filtroFaturamentoGrupo= new FiltroFaturamentoGrupo();
				filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
						FiltroFaturamentoGrupo.ID, faturamentoActionForm.getIdGrupoFaturamento()));
				
				Collection colecaoFaturamentoGrupo = fachada.pesquisar(filtroFaturamentoGrupo,
						FaturamentoGrupo.class.getName());
				
				FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) 
					Util.retonarObjetoDeColecao(colecaoFaturamentoGrupo);
				
				if(Util.compararAnoMesReferencia(anoMes, faturamentoGrupo.getAnoMesReferencia().toString(), ">")){
					sessao.setAttribute("desabilitaCheck", true);
				}else{
					sessao.removeAttribute("desabilitaCheck");
				}
			}
        	
        	//OBTENDO O CRONOGRAMA DO MES ANTERIOR
        	//--------------------------------------------------------------------------------------------------------
        	FiltroFaturamentoGrupoCronogramaMensal filtroCronogramaMensal = new FiltroFaturamentoGrupoCronogramaMensal();

			filtroCronogramaMensal.adicionarParametro(new ParametroSimples(
			FiltroFaturamentoGrupoCronogramaMensal.FATURAMENTO_GRUPO,
			faturamentoActionForm.getIdGrupoFaturamento()));

			Integer anoMesMenosUm = Util.subtraiAteSeisMesesAnoMesReferencia(
			new Integer(anoMes), 1);

			filtroCronogramaMensal.adicionarParametro(new ParametroSimples(
			FiltroFaturamentoGrupoCronogramaMensal.REFERENCIA, anoMesMenosUm));

			Collection colecaoCronogramaMensal = fachada.pesquisar(filtroCronogramaMensal,
			FaturamentoGrupoCronogramaMensal.class.getName());

			FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal = (FaturamentoGrupoCronogramaMensal) 
			Util.retonarObjetoDeColecao(colecaoCronogramaMensal);

			Integer idFaturamentoGrupoCronogramaMensal = null;
			
			if( faturamentoGrupoCronogramaMensal != null ){
			
				idFaturamentoGrupoCronogramaMensal = faturamentoGrupoCronogramaMensal.getId();
			}
			//--------------------------------------------------------------------------------------------------------

			
			//OBTENDO A DATA DE REALIZAÇÃO DA ATIVIDADE EM QUESTÃO PARA O MES ANTERIOR
			Collection faturamentoAtividadesHelpSessao = (Collection) sessao
			.getAttribute("faturamentoAtividades");
			
			for (Iterator iter = faturamentoAtividadesHelpSessao.iterator(); iter.hasNext();) {

				FaturamentoCronogramaAtividadeHelp helper = 
				(FaturamentoCronogramaAtividadeHelp) iter.next();
		
				FiltroFaturamentoAtividadeCronograma filtroAtividadeCronograma = 
				new FiltroFaturamentoAtividadeCronograma();
					
				FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = null;
					
				if( faturamentoGrupoCronogramaMensal != null ){	
					
					filtroAtividadeCronograma
					.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoAtividadeCronograma.FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_ID,
					idFaturamentoGrupoCronogramaMensal));

					filtroAtividadeCronograma
					.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoAtividadeCronograma.FATURAMENTO_ATIVIDADE_ID, 
					helper.getFaturamentoAtividade().getId()));

					Collection colecaoAtividadeCronograma = fachada.pesquisar(
					filtroAtividadeCronograma, FaturamentoAtividadeCronograma.class.getName());

					faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) Util
					.retonarObjetoDeColecao(colecaoAtividadeCronograma);

				}
					
				Date dataMesAnterior = null;

				if (faturamentoAtividadeCronograma != null && 
					faturamentoAtividadeCronograma.getDataRealizacao() != null) {

					dataMesAnterior = faturamentoAtividadeCronograma
					.getDataRealizacao();

				}
				else if ( faturamentoAtividadeCronograma != null && 
						faturamentoAtividadeCronograma.getDataPrevista() != null && dataMesAnterior == null ){
						
					dataMesAnterior = faturamentoAtividadeCronograma
					.getDataPrevista();

				}
				
				
				helper.setDataRealizacaoMesAnterior(Util.formatarData(dataMesAnterior));
			}
		}
    }

}
