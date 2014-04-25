package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.CpfTipo;
import gcom.cadastro.FiltroCpfTipo;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.NegativacaoCriterio;
import gcom.cobranca.NegativacaoCriterioCpfTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os parâmetros para realização
 * da inserção de um Comando de Negativação (Aba nº 01 - Dados Gerais) 
 *
 * @author Ana Maria
 * @date 06/11/2007
 */
public class ExibirAtualizarComandoNegativacaoDadosGeraisAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("atualizarComandoNegativacaoDadosGerais");
        
    	Fachada fachada = Fachada.getInstancia();
    	
    	HttpSession sessao = httpServletRequest.getSession(false);

    	AtualizarComandoNegativacaoPorCriterioActionForm form = (AtualizarComandoNegativacaoPorCriterioActionForm) actionForm;
    	
    	if(form.getSimular().equals(ConstantesSistema.NAO_CONFIRMADA)){
        	httpServletRequest.setAttribute("habilitarExecutarSimulacao", "habilitarExecutarSimulacao");   		
    	}
		
    	if(httpServletRequest.getParameter("idComandoNegativacaoSimulado") != null){
 			Integer idComandoNegativacao = new Integer(httpServletRequest.getParameter("idComandoNegativacaoSimulado"));
 			
 			NegativacaoCriterio negativacaoCriterio = 
 				fachada.pesquisarComandoNegativacaoSimulado(idComandoNegativacao);
 			
 			//[FS0027] Verificar seleção de comando simulado
 			if (((Short)negativacaoCriterio.getNegativacaoComando().getIndicadorSimulacao()).equals(ConstantesSistema.NAO)){
     			throw new ActionServletException("atencao.comando_nao_simulacao");
 			}
 			if(negativacaoCriterio.getNegativacaoComando().getDataHoraRealizacao() == null){
 				throw new ActionServletException("atencao.simulacao_nao_realizada");
 			}
 			form.setIdComandoSimulado(idComandoNegativacao.toString());
 			form.setDescricaoComandoSimulado(negativacaoCriterio.getDescricaoTitulo());
 		}
    	
    	//[SB0003]- Determinar Data Prevista para Execução do Comando
    	if(httpServletRequest.getParameter("determinarData") != null){
			atualizaColecoesNaSessao(sessao, httpServletRequest);
	        if(form.getSimular().equals(ConstantesSistema.NAO_CONFIRMADA)){
	        	Date dataRealizacaoComando = fachada.pesquisarUltimaDataRealizacaoComando(new Integer(form.getNegativadorId()), 
    					new Integer(form.getSimular()));
	    	  if(dataRealizacaoComando == null){
	    		  dataRealizacaoComando = new Date();
	    	  }
	    	  //Pesquisar Sistema Parametro 
	    	  FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
	    			
	    	  Collection<SistemaParametro> collectionSistemaParametro = fachada
	    					.pesquisar(filtroSistemaParametro,
	    							SistemaParametro.class.getName());
	    	  SistemaParametro sistemaParametro = (SistemaParametro) collectionSistemaParametro
	    					.iterator().next();
	    	  Short periodoRealizacao = sistemaParametro.getCodigoPeriodicidadeNegativacao();
	    	  if(periodoRealizacao.equals(SistemaParametro.CODIGO_PERIODICIDADE_NEGATIVACAO_SEMANAL)){
	    		 dataRealizacaoComando = Util.adicionarNumeroDiasDeUmaData(dataRealizacaoComando, 7);  
	    	  }else if(periodoRealizacao.equals(SistemaParametro.CODIGO_PERIODICIDADE_NEGATIVACAO_QUINZENAL)){
	    		 dataRealizacaoComando = Util.adicionarNumeroDiasDeUmaData(dataRealizacaoComando, 15); 		
	    	  }else{
	    		 dataRealizacaoComando = Util.adicionarNumeroDiasDeUmaData(dataRealizacaoComando, 30); 
	    	  }
	    	  
			  Calendar calendar = Calendar.getInstance();
			  calendar.setTime(dataRealizacaoComando);
			  
			  Integer diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
			  Integer qtdSomarData = 7 - diaSemana;   	  
			  if(qtdSomarData != 0){
			    dataRealizacaoComando = Util.adicionarNumeroDiasDeUmaData(dataRealizacaoComando, qtdSomarData); 
			  }
			  
			  form.setDataPrevista(Util.formatarData(dataRealizacaoComando));
	    	}else{
	    		form.setDataPrevista(Util.formatarData(new Date()));
	    	}      
    	}
    	
        //Pesquisar Tipo CPF
        if(sessao.getAttribute("colecaoCPFTipo") == null){
        	FiltroCpfTipo filtroCpfTipo = new FiltroCpfTipo();
    		
    		Collection cpfTipos = fachada.pesquisar(filtroCpfTipo,CpfTipo.class.getName());
    		
    		sessao.setAttribute("colecaoCPFTipo", cpfTipos);
        }
        
        //Pesquisa Usuario 
        if(httpServletRequest.getParameter("pesquisarUsuario") != null){
        	atualizaColecoesNaSessao(sessao, httpServletRequest);
        	
	        String usuario = form.getUsuario();
	       
	        if(usuario != null && !usuario.equals("")){
	        	
	        	FiltroUsuario filtroUsuario = new FiltroUsuario();  
	            
	        	filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, usuario));
	            
	            Collection colecaoUsuario = fachada.pesquisar(
	                    filtroUsuario,Usuario.class.getName());
	            
	            if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
	            	httpServletRequest.setAttribute("corUsuario", "valor");
	            	
	            	form.setUsuario(""
							+ ((Usuario) ((List) colecaoUsuario).get(0)).getId());
	            	form.setNomeUsuario(""
							+ ((Usuario) ((List) colecaoUsuario).get(0)).getNomeUsuario());
				} else {
					httpServletRequest.setAttribute("corUsuario","exception");
					form.setUsuario(null);
					form.setNomeUsuario(ConstantesSistema.USUARIO_INEXISTENTE);
				}
	        }
        }
        
        //Adicionar Titularidade CPF/CNPJ da Negativação
        if(httpServletRequest.getParameter("adicionarTitularidade") != null){
        	atualizaColecoesNaSessao(sessao, httpServletRequest);
        	
        	if(form.getTitularidadeNegativacao()== null || 
        			form.getTitularidadeNegativacao().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
    			throw new ActionServletException("atencao.campo.informado", null, "Titularidade do CPF/CNPJ da Negativação");
        	}
        	Collection<NegativacaoCriterioCpfTipo> colecaoNegativacaoCriterioCpfTipo = null;
        	if(sessao.getAttribute("colecaoNegativacaoCriterioCpfTipo") == null){
        		colecaoNegativacaoCriterioCpfTipo = new ArrayList<NegativacaoCriterioCpfTipo>();
        	}else{
        		colecaoNegativacaoCriterioCpfTipo = (Collection)sessao.getAttribute("colecaoNegativacaoCriterioCpfTipo");
        	}
        	
        	NegativacaoCriterioCpfTipo negativacaoCriterioCpfTipo = new NegativacaoCriterioCpfTipo();
        	Integer idTitularidade = new Integer(form.getTitularidadeNegativacao());
        	//negativacaoCriterioCpfTipo.setId(idTitularidade);
        	FiltroCpfTipo filtroCpfTipo = new FiltroCpfTipo();
        	filtroCpfTipo.adicionarParametro(new ParametroSimples(FiltroCpfTipo.ID, idTitularidade));
    		
    		Collection titularidades = fachada.pesquisar(filtroCpfTipo,
    						CpfTipo.class.getName());
    		
    		if (titularidades != null && !titularidades.isEmpty()) {    		
    			CpfTipo cpfTipo = (CpfTipo) Util.retonarObjetoDeColecao(titularidades);
    			negativacaoCriterioCpfTipo.setCpfTipo(cpfTipo);	
    			negativacaoCriterioCpfTipo.setIndicadorCoincidente(new Short("2"));
    		}
    		
    		//[FS0013] Verificar Titularidade do CPF/CNPJ da Negativação já existe na lista
    		if(colecaoNegativacaoCriterioCpfTipo.contains(negativacaoCriterioCpfTipo)){
    			throw new ActionServletException("atencao.titularidade_negativacao_ja_existe_lista");
    		}
    		
    		form.setTitularidadeNegativacao(""+ConstantesSistema.NUMERO_NAO_INFORMADO);
    		colecaoNegativacaoCriterioCpfTipo.add(negativacaoCriterioCpfTipo);
    		sessao.setAttribute("colecaoNegativacaoCriterioCpfTipo", colecaoNegativacaoCriterioCpfTipo);
        }
        
		//Remover Titularidade CPF/CNPJ da Negativação
		if(httpServletRequest.getParameter("idTitularidadeRemover") != null){
			atualizaColecoesNaSessao(sessao, httpServletRequest);
			
			Integer idTitularidadeRemover = new Integer(httpServletRequest.getParameter("idTitularidadeRemover"));
			Collection colecaoNegativacaoCriterioCpfTipo = (Collection)sessao.getAttribute("colecaoNegativacaoCriterioCpfTipo");			
			if(colecaoNegativacaoCriterioCpfTipo != null && !colecaoNegativacaoCriterioCpfTipo.isEmpty()){
				NegativacaoCriterioCpfTipo negativacaoCriterioCpfTipo = new NegativacaoCriterioCpfTipo();
	        	FiltroCpfTipo filtroCpfTipo = new FiltroCpfTipo();
	        	filtroCpfTipo.adicionarParametro(new ParametroSimples(FiltroCpfTipo.ID, idTitularidadeRemover));
	    		
	    		Collection titularidades = fachada.pesquisar(filtroCpfTipo,CpfTipo.class.getName());
    			CpfTipo cpfTipo = (CpfTipo) Util.retonarObjetoDeColecao(titularidades);
				negativacaoCriterioCpfTipo.setCpfTipo(cpfTipo);
				colecaoNegativacaoCriterioCpfTipo.remove(negativacaoCriterioCpfTipo);		
				sessao.setAttribute("colecaoNegativacaoCriterioCpfTipo", colecaoNegativacaoCriterioCpfTipo);
			}
		}
        
    	return retorno;
    }

	private void atualizaColecoesNaSessao(HttpSession sessao,
			HttpServletRequest httpServletRequest){
    	     	
		//colecaoNegativacaoCriterioCpfTipo
		if (sessao.getAttribute("colecaoNegativacaoCriterioCpfTipo") != null
		&& !sessao.getAttribute("colecaoNegativacaoCriterioCpfTipo").equals("")) {
		
			Collection colecaoNegativacaoCriterioCpfTipo = (Collection) sessao
			.getAttribute("colecaoNegativacaoCriterioCpfTipo");
			// cria as variáveis para recuperar os parâmetros do request e jogar
			// no objeto  NegativacaoCriterioCpfTipo
			String ordem = null;
			String coincidente = null;
		
			Iterator iteratorNegativacaoCriterioCpfTipo = colecaoNegativacaoCriterioCpfTipo.iterator();
			
			while (iteratorNegativacaoCriterioCpfTipo.hasNext()) {
				NegativacaoCriterioCpfTipo negativacaoCriterioCpfTipo = 
					(NegativacaoCriterioCpfTipo) iteratorNegativacaoCriterioCpfTipo.next();
				
				Integer idTitularidade = negativacaoCriterioCpfTipo.getCpfTipo().getId();

				ordem = (String) httpServletRequest.getParameter("ordem" + idTitularidade);
				coincidente = (String) httpServletRequest.getParameter("coincidente" + idTitularidade);
				
				if (ordem != null && !ordem.equals("")) {
					negativacaoCriterioCpfTipo.setNumeroOrdemSelecao(new Short(ordem));
				}

				if(coincidente != null && !coincidente.equals("")){
					negativacaoCriterioCpfTipo.setIndicadorCoincidente(new Short("1"));	
				}else{
					negativacaoCriterioCpfTipo.setIndicadorCoincidente(new Short("2"));	
				}
			}
		}	        	
    }
}
