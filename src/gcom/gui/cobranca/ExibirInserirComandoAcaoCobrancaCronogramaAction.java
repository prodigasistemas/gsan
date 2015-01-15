package gcom.gui.cobranca;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.CobrancaAcaoCronograma;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.CobrancaGrupoCronogramaMes;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeCronograma;
import gcom.cobranca.FiltroCobrancaAcaoCronograma;
import gcom.cobranca.FiltroCobrancaAtividade;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupoCronogramaMes;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0243] Inserir Comando de A��o de Conbran�a - Tipo de Comando Cronograma
 * @author Rafael Santos
 * @since 24/01/2006
 */
public class ExibirInserirComandoAcaoCobrancaCronogramaAction  extends GcomAction{
	
	
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
                .findForward("exibirInserirComandoAcaoCobrancaCronograma");

        //Mudar isso quando implementar a parte de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Fachada fachada = Fachada.getInstancia();
        
        String limparForm = httpServletRequest.getParameter("limparForm");
        
		if (limparForm != null && !limparForm.trim().equalsIgnoreCase("")) {

			if (sessao.getAttribute("colecaoCobrancaGrupo") != null) {
				sessao.removeAttribute("colecaoCobrancaGrupo");
			}
			if (sessao.getAttribute("colecaoCobrancaAcao") != null) {
				sessao.removeAttribute("colecaoCobrancaAcao");
			}
			if (sessao.getAttribute("colecaoCobrancaAtividade") != null) {
				sessao.removeAttribute("colecaoCobrancaAtividade");
			}
		
		}
        
        String validarCobrancaGrupo = (String) httpServletRequest.getParameter("validarCobrancaGrupo");
        String validarCobrancaAcao = (String) httpServletRequest.getParameter("validarCobrancaAcao");
        String validarCobrancaAtividade = (String) httpServletRequest.getParameter("validarCobrancaAtividade");        
        
        FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
        InserirComandoAcaoCobrancaCronogramaActionForm inserirComandoAcaoCobrancaCronogramaActionForm = (InserirComandoAcaoCobrancaCronogramaActionForm) actionForm;
        
        Collection colecaoCobrancaGrupo = null;
        if(sessao.getAttribute("colecaoCobrancaGrupo") == null){
        	Collection colecaoCobrancaGrupoAptos = new ArrayList();
        		 
        	filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);
        	
	        // CARREGAR AS COBRAN�AS GRUPO  - INICIO
        	colecaoCobrancaGrupo = (Collection) fachada.pesquisar(filtroCobrancaGrupo,CobrancaGrupo.class.getName());
	        
        	//[FS0002] - Verificar sele��o de pelo menos um grupo de cobran�a
	        if(colecaoCobrancaGrupo != null && !colecaoCobrancaGrupo.isEmpty()){
	            //carregar grupo de cobran�a
	        	
	        	Iterator iteratorColecaoCobrancaGrupo = colecaoCobrancaGrupo.iterator();
	        	while (iteratorColecaoCobrancaGrupo.hasNext()) {
					CobrancaGrupo cobrancaGrupo = (CobrancaGrupo) iteratorColecaoCobrancaGrupo.next();
					
		        	FiltroCobrancaGrupoCronogramaMes filtroCobrancaGrupoCronogramaMensal = new FiltroCobrancaGrupoCronogramaMes();
		        	filtroCobrancaGrupoCronogramaMensal.adicionarCaminhoParaCarregamentoEntidade("cobrancaGrupo");
		            filtroCobrancaGrupoCronogramaMensal.adicionarParametro(
		            		new ParametroSimples(FiltroCobrancaGrupoCronogramaMes.ID_COBRANCA_GRUPO, cobrancaGrupo.getId()));
		            filtroCobrancaGrupoCronogramaMensal.adicionarParametro(
		            		new ParametroSimples(FiltroCobrancaGrupoCronogramaMes.ANO_MES_REFERENCIA, cobrancaGrupo.getAnoMesReferencia()));
		        	
		            Collection colecaoCobrancaGrupoCronogramaMensal = (Collection) fachada.pesquisar(filtroCobrancaGrupoCronogramaMensal,CobrancaGrupoCronogramaMes.class.getName());
					
		            if(colecaoCobrancaGrupoCronogramaMensal != null && !colecaoCobrancaGrupoCronogramaMensal.isEmpty()){
		            	colecaoCobrancaGrupoAptos.add(cobrancaGrupo);
		            }
				}
		        if(colecaoCobrancaGrupoAptos == null || colecaoCobrancaGrupoAptos.isEmpty()){
					throw new ActionServletException("atencao.nao.grupo_cobranca.cronograma.mes.corrente");
		        }
	        	sessao.setAttribute("colecaoCobrancaGrupo",colecaoCobrancaGrupoAptos);
			} else {
				throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela",
						null, "Tabela Cobran�a Grupo");
			}        
	        // FIM COBRAN�A GRUPO
        }
        
        //INICIO VALIDAR COBRANCA GRUPO SELECIONADO e CARREGAR AS A��ES DE COBRAN�A
        if(validarCobrancaGrupo != null && !validarCobrancaGrupo.equals("")){
        	String idGrupoCobranca = inserirComandoAcaoCobrancaCronogramaActionForm.getCobrancaGrupo();
        	if(idGrupoCobranca != null && !idGrupoCobranca.equals("")){
	            //pesquisando a cobrancao grupo
	            filtroCobrancaGrupo = new FiltroCobrancaGrupo();
	            filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(FiltroCobrancaGrupo.ID,idGrupoCobranca));
	            colecaoCobrancaGrupo = (Collection) fachada.pesquisar(filtroCobrancaGrupo,CobrancaGrupo.class.getName());
        		CobrancaGrupo cobrancaGrupo = (CobrancaGrupo)colecaoCobrancaGrupo.iterator().next();
        		   
	            //pesquisando o grupo cronograma mensal
	        	FiltroCobrancaGrupoCronogramaMes filtroCobrancaGrupoCronogramaMensal = new FiltroCobrancaGrupoCronogramaMes();
	            
	            filtroCobrancaGrupoCronogramaMensal.adicionarParametro(
 	            		new ParametroSimples(FiltroCobrancaGrupoCronogramaMes.ID_COBRANCA_GRUPO, cobrancaGrupo.getId()));
	            filtroCobrancaGrupoCronogramaMensal.adicionarCaminhoParaCarregamentoEntidade("cobrancaGrupo");
	            filtroCobrancaGrupoCronogramaMensal.setCampoOrderBy(FiltroCobrancaGrupoCronogramaMes.ID);
	            
	        	Collection colecaoCobrancaGrupoCronogramaMensal = (Collection) fachada.pesquisar(filtroCobrancaGrupoCronogramaMensal,CobrancaGrupoCronogramaMes.class.getName());
	        	
	        	
	        	//[FS002] - Veririficar exist�ncia do cronograma para o grupo
	        	if(colecaoCobrancaGrupoCronogramaMensal == null || colecaoCobrancaGrupoCronogramaMensal.isEmpty()){
	    			throw new ActionServletException("atencao.cronograma_cobranca.inexistente",
	    				null, idGrupoCobranca + " no M�s Corrente " + cobrancaGrupo.getAnoMesReferencia());
	    		}
	        	
	        	Collections.reverse((List)colecaoCobrancaGrupoCronogramaMensal);
	            
	            //cobrancao de grupo selecionado
	            sessao.setAttribute("cobrancaGrupo",cobrancaGrupo);
	            CobrancaGrupoCronogramaMes cobrancaGrupoCronogramaMensal = null;
	            if (inserirComandoAcaoCobrancaCronogramaActionForm.getReferenciaCobranca() != null
	            		&& !inserirComandoAcaoCobrancaCronogramaActionForm.getReferenciaCobranca().toString().equals("")){
	     
	            	cobrancaGrupoCronogramaMensal = new CobrancaGrupoCronogramaMes();
	            	cobrancaGrupoCronogramaMensal.setId(new Integer(
        					inserirComandoAcaoCobrancaCronogramaActionForm.getReferenciaCobranca()));
	            }else{
	            	
	            	cobrancaGrupoCronogramaMensal = (CobrancaGrupoCronogramaMes) colecaoCobrancaGrupoCronogramaMensal.iterator().next();
	            }
	            
	            sessao.setAttribute("colecaoCobrancaGrupoCronogramaMensal",colecaoCobrancaGrupoCronogramaMensal);
	            
	            //CARREGAR A��O COBRAN�A
	        	FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
	        	
	        	filtroCobrancaAcao.setCampoOrderBy(FiltroCobrancaAcao.DESCRICAO);
	            Collection colecaoCobrancaAcao = (Collection) fachada.pesquisar(filtroCobrancaAcao,CobrancaAcao.class.getName());
	            
	            //[SB0004] - Selecionar A��o de Cobran�a
	            if(colecaoCobrancaAcao != null && !colecaoCobrancaAcao.isEmpty()){
	                Collection colecaoCobranAcaoAptas = new ArrayList();
	            	Iterator iColecaoCobrancaAcao = colecaoCobrancaAcao.iterator();

	            	Collection colecaoCobrancaAcaoAtividadeCronograma = null;
            		CobrancaAcaoCronograma cobrancaAcaoCronograma = null;
            		Collection colecaoCobrancaAcaoCronograma = null;
            		FiltroCobrancaAcaoAtividadeCronograma filtroCobrancaAcaoAtividadeCronograma = null;
            		
	            	//adicionar as cobran�as de a��o aptas para serem selecionadas
	            	while(iColecaoCobrancaAcao.hasNext()){
	            		boolean primeiraCondicao = false;
	            		boolean segundaCondicao = false;
	            		boolean terceiraCondicao = false;
	            		CobrancaAcao cobrancaAcao = (CobrancaAcao) iColecaoCobrancaAcao.next();
	
	            		//pesquisa a cobranca acao cronograma
	            		FiltroCobrancaAcaoCronograma filtroCobrancaAcaoCronograma = new FiltroCobrancaAcaoCronograma();
	            		filtroCobrancaAcaoCronograma.adicionarParametro(new ParametroSimples(FiltroCobrancaAcaoCronograma.ID_COBRANCA_ACAO,cobrancaAcao.getId()));
	            		filtroCobrancaAcaoCronograma.adicionarParametro(new ParametroSimples(FiltroCobrancaAcaoCronograma.ID_COBRANCA_GRUPO_CRONOGRAMA_MES,cobrancaGrupoCronogramaMensal.getId()));
	            		colecaoCobrancaAcaoCronograma = fachada.pesquisar(filtroCobrancaAcaoCronograma,CobrancaAcaoCronograma.class.getName());
	            		
	            		if(colecaoCobrancaAcaoCronograma != null && !colecaoCobrancaAcaoCronograma.isEmpty()){
	            			cobrancaAcaoCronograma = (CobrancaAcaoCronograma) colecaoCobrancaAcaoCronograma.iterator().next(); 
	            			terceiraCondicao = true;
		            		//pesquisar crobranca acao atividade cronograma
	            			filtroCobrancaAcaoAtividadeCronograma = new FiltroCobrancaAcaoAtividadeCronograma();
		            		filtroCobrancaAcaoAtividadeCronograma.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_ATIVIDADE);
		            		filtroCobrancaAcaoAtividadeCronograma.adicionarParametro(new ParametroSimples(FiltroCobrancaAcaoAtividadeCronograma.ID_COBRANCA_ACAO_CRONOGRAMA,cobrancaAcaoCronograma.getId()));
		            		colecaoCobrancaAcaoAtividadeCronograma = fachada.pesquisar(filtroCobrancaAcaoAtividadeCronograma,CobrancaAcaoAtividadeCronograma.class.getName()); 
	            		}	
	            		//primeira condi��o
	            		if(cobrancaAcao.getIndicadorRepeticao().shortValue() == (short)1){
	            			primeiraCondicao = true;
	            		}else{
	            			if(colecaoCobrancaAcaoAtividadeCronograma != null && !colecaoCobrancaAcaoAtividadeCronograma.isEmpty()){
	            				Iterator iteratorColecaoCobrancaAcaoAtividadeCronograma = colecaoCobrancaAcaoAtividadeCronograma.iterator();
	            				boolean achou = true;
	            				while (iteratorColecaoCobrancaAcaoAtividadeCronograma.hasNext() & achou) {
									CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = (CobrancaAcaoAtividadeCronograma) iteratorColecaoCobrancaAcaoAtividadeCronograma.next();
									if(cobrancaAcaoAtividadeCronograma.getRealizacao() == null & cobrancaAcaoAtividadeCronograma.getCobrancaAtividade().getIndicadorComando().shortValue() == (short)1){
										primeiraCondicao = true;			
										achou = false;
									}
								}
	            			}
	            		}
	            		
	            		boolean achou = true;
	            		
	            		//segunda condi��o
	            		if(cobrancaAcao.getCobrancaAcaoPredecessora() == null){
	            			segundaCondicao = true;
	            		}else{

		            		//pesquisa a cobranca acao cronograma
		            		filtroCobrancaAcaoCronograma = new FiltroCobrancaAcaoCronograma();
		            		filtroCobrancaAcaoCronograma.adicionarParametro(new ParametroSimples(FiltroCobrancaAcaoCronograma.ID_COBRANCA_ACAO,cobrancaAcao.getCobrancaAcaoPredecessora().getId()));
		            		filtroCobrancaAcaoCronograma.adicionarParametro(new ParametroSimples(FiltroCobrancaAcaoCronograma.ID_COBRANCA_GRUPO_CRONOGRAMA_MES,cobrancaGrupoCronogramaMensal.getId()));
		            		colecaoCobrancaAcaoCronograma = fachada.pesquisar(filtroCobrancaAcaoCronograma,CobrancaAcaoCronograma.class.getName()); 
		            		if(colecaoCobrancaAcaoCronograma != null && !colecaoCobrancaAcaoCronograma.isEmpty()){
			            		cobrancaAcaoCronograma = (CobrancaAcaoCronograma) colecaoCobrancaAcaoCronograma.iterator().next(); 
			            		
			            		//pesquisar crobranca acao atividade cronograma
			            		filtroCobrancaAcaoAtividadeCronograma = new FiltroCobrancaAcaoAtividadeCronograma();
			            		filtroCobrancaAcaoAtividadeCronograma.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_ATIVIDADE);
			            		filtroCobrancaAcaoAtividadeCronograma.adicionarParametro(new ParametroSimples(FiltroCobrancaAcaoAtividadeCronograma.ID_COBRANCA_ACAO_CRONOGRAMA,cobrancaAcaoCronograma.getId()));
			            		colecaoCobrancaAcaoAtividadeCronograma = fachada.pesquisar(filtroCobrancaAcaoAtividadeCronograma,CobrancaAcaoAtividadeCronograma.class.getName()); 
		            		}
	            			
	            			if(colecaoCobrancaAcaoAtividadeCronograma != null && !colecaoCobrancaAcaoAtividadeCronograma.isEmpty()){
	            				Iterator iteratorColecaoCobrancaAcaoAtividadeCronograma = colecaoCobrancaAcaoAtividadeCronograma.iterator();
	            				
	            				while (iteratorColecaoCobrancaAcaoAtividadeCronograma.hasNext() & achou) {
									CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = (CobrancaAcaoAtividadeCronograma) iteratorColecaoCobrancaAcaoAtividadeCronograma.next();
									if(cobrancaAcaoAtividadeCronograma.getCobrancaAtividade().getIndicadorComando().shortValue() == (short)1){
										if((cobrancaAcaoAtividadeCronograma.getRealizacao() != null) || (cobrancaAcaoAtividadeCronograma.getRealizacao() != null && (cobrancaAcaoAtividadeCronograma.getRealizacao().compareTo(cobrancaAcaoAtividadeCronograma.getComando()) <1 ))){
											segundaCondicao = false;			
											achou = false;
										}
									}
								}
	            			}
	            			if(!achou){
		            			segundaCondicao = true;
		            		}		            		
	            			
	            		}
	            		
	            		if(primeiraCondicao & segundaCondicao & terceiraCondicao){
	            			colecaoCobranAcaoAptas.add(cobrancaAcao);
	            		}
	            		
	            	}
	            	
	            	if(colecaoCobranAcaoAptas != null && !colecaoCobranAcaoAptas.isEmpty()){
	            		//carregar Acao de cobran�a aptos
	            		sessao.setAttribute("colecaoCobrancaAcao",colecaoCobranAcaoAptas);
	            	}else{
	        			throw new ActionServletException("atencao.nenhuma.acao_cobranca",
	            				null,cobrancaGrupo.getId() + " no m�s corrente " + cobrancaGrupo.getAnoMesReferencia() + " j� foram realizadas");
	            	}
	            	
	    		} else {
	    			throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela",
	    					null, "Tabela Cobran�a A��o");
	    		}
        	}else{
        		
        		if (sessao.getAttribute("colecaoCobrancaGrupoCronogramaMensal") != null) {
    				sessao.removeAttribute("colecaoCobrancaGrupoCronogramaMensal");
    			}
        		
    			if (sessao.getAttribute("colecaoCobrancaAcao") != null) {
    				sessao.removeAttribute("colecaoCobrancaAcao");
    			}
    			if (sessao.getAttribute("colecaoCobrancaAtividade") != null) {
    				sessao.removeAttribute("colecaoCobrancaAtividade");
    			}

        	}
        }
        //FIM VALIDAR COBRAN�A GRUPO SELECIONADO
        
        //INICIO DE VALIDAR COBRAN�A A��O SELECIONADA
        if(validarCobrancaAcao != null && !validarCobrancaAcao.equals("")){
        	
        	String idAcaoCobranca = inserirComandoAcaoCobrancaCronogramaActionForm.getCobrancaAcao();
        	
        	if(idAcaoCobranca != null && !idAcaoCobranca.equals("")){
        		if(sessao.getAttribute("cobrancaGrupo") != null  && sessao.getAttribute("colecaoCobrancaGrupoCronogramaMensal") != null){
	        		CobrancaGrupo cobrancaGrupo = (CobrancaGrupo)sessao.getAttribute("cobrancaGrupo");
	        		Integer idCobrancaGrupoCronogramaMensalSelecionado = new Integer(inserirComandoAcaoCobrancaCronogramaActionForm.getReferenciaCobranca());
	            	
	        		//validando a cobran�a a��o selecionada
	        		//essa valida��o � necess�ria pq a a��o pode ser carrega sem ter validado pelo [SF004] 
	        		FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
	        		filtroCobrancaAcao.adicionarParametro(
	                		new ParametroSimples("id", idAcaoCobranca));
	        		
	        		Collection colecaoCobrancaAcao = (Collection) fachada.pesquisar(filtroCobrancaAcao,CobrancaAcao.class.getName());
	        		
	        		CobrancaAcao cobrancaAcao = (CobrancaAcao) colecaoCobrancaAcao.iterator().next();
	        		sessao.setAttribute("cobrancaAcao",cobrancaAcao);

            		//pesquisa a cobranca acao cronograma
        			FiltroCobrancaAcaoCronograma filtroCobrancaAcaoCronograma = new FiltroCobrancaAcaoCronograma();
            		filtroCobrancaAcaoCronograma.adicionarParametro(new ParametroSimples(FiltroCobrancaAcaoCronograma.ID_COBRANCA_ACAO,idAcaoCobranca));
            		filtroCobrancaAcaoCronograma.adicionarParametro(new ParametroSimples(FiltroCobrancaAcaoCronograma.ID_COBRANCA_GRUPO_CRONOGRAMA_MES,idCobrancaGrupoCronogramaMensalSelecionado));
            		Collection colecaoCobrancaAcaoCronograma = fachada.pesquisar(filtroCobrancaAcaoCronograma,CobrancaAcaoCronograma.class.getName()); 
            		
	                CobrancaAcaoCronograma cobrancaAcaoCronograma = (CobrancaAcaoCronograma) colecaoCobrancaAcaoCronograma.iterator().next();
	                sessao.setAttribute("cobrancaAcaoCronogama",cobrancaAcaoCronograma);
	        	
		          	//CARREGAR COBRAN�A ATIVIDADE
		        	FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
		        	filtroCobrancaAtividade.setCampoOrderBy(FiltroCobrancaAtividade.DESCRICAO);
		        	filtroCobrancaAtividade.adicionarParametro(
		            		new ParametroSimples("indicadorComando", "1"));//valor 1
		        	
		        	
		        	Collection colecaoCobrancaAtividade = (Collection) fachada.pesquisar(filtroCobrancaAtividade, CobrancaAtividade.class.getName());
		        	
		        	//carregar as cobran�as atividades aptas
		            if(colecaoCobrancaAtividade != null && !colecaoCobrancaAtividade.isEmpty()){
		                
		            	Collection colecaoCobrancaAtividadeAptas = new ArrayList();
		            	
		            	Iterator iColecaoCobrancaAtividade = colecaoCobrancaAtividade.iterator();
		            	
		            	while(iColecaoCobrancaAtividade.hasNext()){
		            		boolean primeiraCondicao = false;
		            		boolean segundaCondicao = false;
		            		boolean terceiraCondicao = false;
		            		
		            		CobrancaAtividade cobrancaAtividade = (CobrancaAtividade) iColecaoCobrancaAtividade.next();
		            		
		            		//terceira condi��o
			        		FiltroCobrancaAcaoAtividadeCronograma filtroCobrancaAcaoAtividadeCronograma = new FiltroCobrancaAcaoAtividadeCronograma();
			        		filtroCobrancaAcaoAtividadeCronograma.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoCronograma");
			        		filtroCobrancaAcaoAtividadeCronograma.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_ATIVIDADE);
			        		filtroCobrancaAcaoAtividadeCronograma.adicionarParametro(
				            		new ParametroSimples(FiltroCobrancaAcaoAtividadeCronograma.ID_COBRANCA_ATIVIDADE,cobrancaAtividade.getId() ));
				        	        		
			        		filtroCobrancaAcaoAtividadeCronograma.adicionarParametro(
				            		new ParametroSimples(FiltroCobrancaAcaoAtividadeCronograma.ID_COBRANCA_ACAO_CRONOGRAMA,cobrancaAcaoCronograma.getId() ));
			        		
			        		Collection colecaoCobrancaAcaoAtividadeCronograma = (Collection) fachada.pesquisar(filtroCobrancaAcaoAtividadeCronograma,CobrancaAcaoAtividadeCronograma.class.getName());
			        		if(colecaoCobrancaAcaoAtividadeCronograma != null && !colecaoCobrancaAcaoAtividadeCronograma.isEmpty()){
			        			terceiraCondicao = true;
			        		}
		            		
		            		//primeira condi��o
		            		//pesquisar crobranca acao atividade cronograma
		            	///	FiltroCobrancaAcaoAtividadeCronograma filtroCobrancaAcaoAtividadeCronograma = new FiltroCobrancaAcaoAtividadeCronograma();
		            		//filtroCobrancaAcaoAtividadeCronograma.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_ATIVIDADE);
		            		//filtroCobrancaAcaoAtividadeCronograma.adicionarParametro(new ParametroSimples(FiltroCobrancaAcaoAtividadeCronograma.ID_COBRANCA_ACAO_CRONOGRAMA,cobrancaAcaoCronograma.getId()));;
		            		//filtroCobrancaAcaoAtividadeCronograma.adicionarParametro(new ParametroSimples(FiltroCobrancaAcaoAtividadeCronograma.ID_COBRANCA_ATIVIDADE,cobrancaAtividade.getId()));;
		            		
		            		//Collection colecaoCobrancaAcaoAtividadeCronograma = fachada.pesquisar(filtroCobrancaAcaoAtividadeCronograma,CobrancaAcaoAtividadeCronograma.class.getName()); 
		            		if(colecaoCobrancaAcaoAtividadeCronograma != null && !colecaoCobrancaAcaoAtividadeCronograma.isEmpty()){
		            			
		            			boolean achou = true;
		            			Iterator iteratorCobrancaAcaoAtividadeCronograma = colecaoCobrancaAcaoAtividadeCronograma.iterator();
		            			while (iteratorCobrancaAcaoAtividadeCronograma.hasNext() && achou) {
								
		            				CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = (CobrancaAcaoAtividadeCronograma) iteratorCobrancaAcaoAtividadeCronograma.next();
		            				
		            				if(cobrancaAcaoAtividadeCronograma.getRealizacao() == null){
		            					primeiraCondicao = true;
		            					achou = false;
		            				}
								}
		            		}

		            		//segunda condi��o
		            		//pesquisar crobranca acao atividade cronograma
		            		if(cobrancaAtividade.getCobrancaAtividadePredecessora() == null){
		            			segundaCondicao = true;
		            		}else{
		            			//pesquisa pela predecesora
			            		filtroCobrancaAcaoAtividadeCronograma = new FiltroCobrancaAcaoAtividadeCronograma();
			            		filtroCobrancaAcaoAtividadeCronograma.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_ATIVIDADE);
			            		filtroCobrancaAcaoAtividadeCronograma.adicionarParametro(new ParametroSimples(FiltroCobrancaAcaoAtividadeCronograma.ID_COBRANCA_ACAO_CRONOGRAMA,cobrancaAcaoCronograma.getId()));
			            		filtroCobrancaAcaoAtividadeCronograma.adicionarParametro(new ParametroSimples(FiltroCobrancaAcaoAtividadeCronograma.ID_COBRANCA_ATIVIDADE,cobrancaAtividade.getCobrancaAtividadePredecessora().getId()));
			            		
			            		colecaoCobrancaAcaoAtividadeCronograma = fachada.pesquisar(filtroCobrancaAcaoAtividadeCronograma,CobrancaAcaoAtividadeCronograma.class.getName()); 
			            		boolean achou = true;
			            		if(colecaoCobrancaAcaoAtividadeCronograma != null && !colecaoCobrancaAcaoAtividadeCronograma.isEmpty()){
			            			
			            			Iterator iteratorCobrancaAcaoAtividadeCronograma = colecaoCobrancaAcaoAtividadeCronograma.iterator();
			            			while (iteratorCobrancaAcaoAtividadeCronograma.hasNext() && achou) {
			            				
			            				CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = (CobrancaAcaoAtividadeCronograma) iteratorCobrancaAcaoAtividadeCronograma.next();
			            				
			            				if(cobrancaAcaoAtividadeCronograma.getRealizacao() != null){
			            					segundaCondicao = false;
			            					achou = false;
			            				}
			            					
			            			}
			            		}
			            		if(!achou){
			            			segundaCondicao = true;
			            		}
			            	}
		            		
		            		if(primeiraCondicao & segundaCondicao & terceiraCondicao){
		            			colecaoCobrancaAtividadeAptas.add(cobrancaAtividade);
		            		}
		            		
		            		
		            	}
		            	
		            	//[SF005] - Verificar sele��o de pelo menos uma atividade de cobran�a
		            	if(colecaoCobrancaAtividade != null && colecaoCobrancaAtividade.isEmpty()){
		        			throw new ActionServletException("atencao.nenhuma.atividade_cobranca",
		            				null,idAcaoCobranca + " para o Grupo " + cobrancaGrupo.getId().toString()+ ", no m�s corrente " + cobrancaGrupo.getAnoMesReferencia() + ", j� foram realizadas");
		            	}else{
		                	//carregar atividade de cobran�a
		                	sessao.setAttribute("colecaoCobrancaAtividade",colecaoCobrancaAtividadeAptas);
		            	}
		    		} else {
		    			throw new ActionServletException("atencao.pesquisa_inexistente",
		    				null, "Tabela Cobran�a Atividade");
		    		}         	
	        	}
        	}else{
        		if (sessao.getAttribute("colecaoCobrancaAtividade") != null) {
        			sessao.removeAttribute("colecaoCobrancaAtividade");
        		}
           }
        }
        
        //validar a cobran�a atividade  selecionada
        if(validarCobrancaAtividade != null && !validarCobrancaAtividade.equals("")){
        	
        	String idAtividade = inserirComandoAcaoCobrancaCronogramaActionForm.getCobrancaAtividade();
        	
        	if(idAtividade != null && !idAtividade.equals("")){
        	
	        	if( sessao.getAttribute("colecaoCobrancaGrupoCronogramaMensal") != null 
	        			&& sessao.getAttribute("cobrancaAcaoCronogama") != null){
	            	CobrancaAcaoCronograma cobrancaAcaoCronogama = (CobrancaAcaoCronograma) sessao.getAttribute("cobrancaAcaoCronogama");
	
	        		FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
	        		filtroCobrancaAtividade.adicionarParametro(
		            		new ParametroSimples("id",idAtividade ));
		        	        		
	        		Collection colecaoCobrancaAtividade = (Collection) fachada.pesquisar(filtroCobrancaAtividade,CobrancaAtividade.class.getName());
	        		
	        		CobrancaAtividade cobrancaAtividade = (CobrancaAtividade) colecaoCobrancaAtividade.iterator().next();
	        		
	        		FiltroCobrancaAcaoAtividadeCronograma filtroCobrancaAcaoAtividadeCronograma = new FiltroCobrancaAcaoAtividadeCronograma();
	        		filtroCobrancaAcaoAtividadeCronograma.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoCronograma");
	        		filtroCobrancaAcaoAtividadeCronograma.adicionarParametro(
		            		new ParametroSimples(FiltroCobrancaAcaoAtividadeCronograma.ID_COBRANCA_ATIVIDADE,idAtividade ));
		        	        		
	        		filtroCobrancaAcaoAtividadeCronograma.adicionarParametro(
		            		new ParametroSimples(FiltroCobrancaAcaoAtividadeCronograma.ID_COBRANCA_ACAO_CRONOGRAMA,cobrancaAcaoCronogama.getId() ));
	        		
	        		Collection colecaoCobrancaAcaoAtividadeCronograma = (Collection) fachada.pesquisar(filtroCobrancaAcaoAtividadeCronograma,CobrancaAcaoAtividadeCronograma.class.getName());
	        		
	        		//[FS006] - Verificar exist�ncia da atividade no cronograma do grupo do m�s corrente
//	                if(!(colecaoCobrancaAcaoAtividadeCronograma != null && !colecaoCobrancaAcaoAtividadeCronograma.isEmpty())){
	        			//throw new ActionServletException("atencao.nenhuma.atividade_grupo_acao",
//	        				null, cobrancaGrupo.getId().toString() + ", do M�s Corrente " + cobrancaGrupo.getAnoMesReferencia() + ", n�o existe a Atividade " + cobrancaAtividade.getDescricaoCobrancaAtividade() + " para a A��o " + cobrancaAcao.getDescricaoCobrancaAcao());
	//        		}        		
	        	
	        		CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = (CobrancaAcaoAtividadeCronograma)colecaoCobrancaAcaoAtividadeCronograma.iterator().next();
	        		sessao.setAttribute("cobrancaAtividade",cobrancaAtividade);
	        		sessao.setAttribute("cobrancaAcaoAtividadeCronograma",cobrancaAcaoAtividadeCronograma);
	        	
	        	}
        	}
        }

        return retorno;
    }

}
