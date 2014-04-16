package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.bean.OSPavimentoRetornoHelper;
import gcom.atendimentopublico.ordemservico.bean.OrdemRepavimentacaoProcessoAceiteHelper;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de Exibir Manter Ordem de Repavimentacao em Processo de Aceite.
 * 
 * @author Hugo Leonardo
 * @created 14/05/2010
 * 
 */

public class ExibirManterOrdemRepavimentacaoProcessoAceiteAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("manterOrdemRepavimentacaoProcessoAceite");
		HttpSession sessao = httpServletRequest.getSession(false);		
		Fachada fachada = Fachada.getInstancia();  
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		boolean usuarioRepav = verificaUnidadeUsuario(usuario, fachada);		
		
		FiltrarOrdemRepavimentacaoProcessoAceiteActionForm form = (FiltrarOrdemRepavimentacaoProcessoAceiteActionForm) actionForm;
		
		//Recebe os Parametros do filtro atraves do Helper e seta os valores no FORM
		OrdemRepavimentacaoProcessoAceiteHelper osPavimentoAceiteHelper = null;
		if (sessao.getAttribute("osPavimentoAceiteHelper") != null) {
			
			osPavimentoAceiteHelper =  (OrdemRepavimentacaoProcessoAceiteHelper) sessao.getAttribute("osPavimentoAceiteHelper");
			
			adicionaOsParametrosNoForm( osPavimentoAceiteHelper , form , fachada );
						
		}
		
		if(form.getManterPaginaAux() == null ){
			
   		 	form.setManterPaginaAux("0");
			httpServletRequest.setAttribute("manterPaginaAux", form.getManterPaginaAux() );
		}
		
		 Collection collOrdemServicoPavimento = null;		    
	     if (httpServletRequest.getParameter("retornaDoPopup") != null) {
	        	collOrdemServicoPavimento = fachada.pesquisarOrdemRepavimentacaoProcessoAceite(osPavimentoAceiteHelper,
	        			(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));
		 }else{
				if (sessao.getAttribute("collOrdemServicoPavimentoHelper") != null) {
					collOrdemServicoPavimento =  (Collection) sessao.getAttribute("collOrdemServicoPavimentoHelper");
				}				
		 }
	     
	     //Conta a quantidade de registros selecionados.
	     Integer ordemServicoPavimentoCount = fachada.pesquisarOrdemRepavimentacaoProcessoAceiteCount(osPavimentoAceiteHelper);
	     form.setOrdensServicoSelecionadas(ordemServicoPavimentoCount.toString());
	     
	     if ( ordemServicoPavimentoCount == null || ordemServicoPavimentoCount.equals("") || 
	    		 ordemServicoPavimentoCount.equals(" ") || ordemServicoPavimentoCount.intValue() <= 0 ||
	    		 ordemServicoPavimentoCount.intValue() > 1000) {
	    	 throw new ActionServletException("atencao.informarOutroFiltro", "exibirFiltrarOrdemRepavimentacaoProcessoAceiteAction.do?limpar=S", null, new String[]{} );
	     }
	     
	     Integer pagina = null;
	     Integer paginaAtualizada = null ;
	     
	     retorno = this.controlarPaginacao(httpServletRequest, retorno, ordemServicoPavimentoCount );
	     
	     //pesquisa na colecao para atualizacao na tela
	     
	     if ( httpServletRequest.getAttribute("numeroPaginasPesquisa") != null && 
	    		 (!httpServletRequest.getAttribute("numeroPaginasPesquisa").toString().equals("0") || 
	    				 form.getManterPaginaAux() != null ) ) {
	    	
	    	 //Primeira vez que entra na pagina
	    	 if ( sessao.getAttribute("numeroPagina") != null ) {
	    		 //Controla pagina para que nao retorne para pagina incial.

	    		  retorno = this.controlarPaginacao(httpServletRequest, retorno, ordemServicoPavimentoCount );
	    		 
	    		 String paginaAtual = (String) sessao.getAttribute("numeroPagina"); 
	    		 form.setManterPaginaAux(  paginaAtual );
	    		 pagina = (Integer) Util.converterStringParaInteger( form.getManterPaginaAux() );
		    	
	    		 collOrdemServicoPavimento = fachada.pesquisarOrdemRepavimentacaoProcessoAceite( osPavimentoAceiteHelper, pagina );
		    	 
	    		 Integer numeroDaPagina = pagina.intValue() +1;
		    	 httpServletRequest.setAttribute("page.offset", numeroDaPagina.toString() );
		    	 httpServletRequest.setAttribute("numeroPaginasPesquisa", pagina.toString() );
		    	 
		    	 retorno = this.controlarPaginacao(httpServletRequest, retorno, ordemServicoPavimentoCount );
	    	 
	    	 } else {
				 //Esse fluxo serve para ter o controle da paginação quando a pagina for atualizada atraves do popup.
	    		 paginaAtualizada = (Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa") + 1;
	    		 form.setManterPaginaAux(  paginaAtualizada.toString() );
	    		 
	    		 collOrdemServicoPavimento = fachada.pesquisarOrdemRepavimentacaoProcessoAceite(osPavimentoAceiteHelper,
		    	 			(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));
	    		 
	    		 //Fluxo responsavel caso as OS sejam pesquisada com a situação SEM ACEITES
	    		 if ( osPavimentoAceiteHelper.getSituacaoAceite().toString().equals("1") &&
	    				 httpServletRequest.getParameter("retornaDoPopup") != null ) {
	    			 
	    			 collOrdemServicoPavimento = fachada.pesquisarOrdemRepavimentacaoProcessoAceite(osPavimentoAceiteHelper,
	    					 (Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));
	    			 
	    			 sessao.setAttribute("totalRegistros", ordemServicoPavimentoCount);
	    		 } else {
	    			 collOrdemServicoPavimento = fachada.pesquisarOrdemRepavimentacaoProcessoAceite(osPavimentoAceiteHelper,
			    	 			(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));
	    		 }
	    	 }
	 
	     } else {
	    	 //Pega o numero da pagina atraves do aux criado para guardar o numero da pagina.
	    	 //segunda vez em diante que abre a pagina
	    	 if (form.getManterPaginaAux() != null &&  !form.getManterPaginaAux().equals("")) {
		    
	    		 pagina = (Integer) Util.converterStringParaInteger(form.getManterPaginaAux());
		    	 
	    		 collOrdemServicoPavimento = fachada.pesquisarOrdemRepavimentacaoProcessoAceite(osPavimentoAceiteHelper, pagina);
		    	 
		    	 Integer pag = pagina.intValue() +1;
		    	 httpServletRequest.setAttribute("page.offset", pag.toString() );
		    	 
		    	 retorno = this.controlarPaginacao(httpServletRequest, retorno, ordemServicoPavimentoCount );
	    	 } else {
	    		
	    		 collOrdemServicoPavimento = fachada.pesquisarOrdemRepavimentacaoProcessoAceite(osPavimentoAceiteHelper,
		    	 			(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));
	    	 }
	     }
	     
	     String[] idsRegistrosChecados = form.getIdRegistro();

	     boolean exiteOrdemRepavSemAceite = verificaOrdemRepavSemAceite(usuario, fachada, osPavimentoAceiteHelper);
	     if (exiteOrdemRepavSemAceite) {
    		 sessao.setAttribute("exiteOrdemRepavSemAceite", "true");
    		 httpServletRequest.setAttribute("exiteOrdemRepavSemAceite", "true" );
	     }else{
	    	 sessao.removeAttribute("exiteOrdemRepavSemAceite");
    		 httpServletRequest.removeAttribute("exiteOrdemRepavSemAceite");
	     }
	     
	     boolean exiteOrdemRepavConvergentes = verificaOrdemRepavConvergentes(usuario, fachada, osPavimentoAceiteHelper);
	     if (exiteOrdemRepavConvergentes) {
    		 sessao.setAttribute("exiteOrdemRepavConvergentes", "true");
    		 httpServletRequest.setAttribute("exiteOrdemRepavConvergentes", "true" );
	     } else{
	    	 sessao.removeAttribute("exiteOrdemRepavConvergentes");
    		 httpServletRequest.removeAttribute("exiteOrdemRepavConvergentes");
	     }
	     
	     if(usuarioRepav){
	    	 sessao.setAttribute("usuarioTipoRepavimentadora", "true");
	    	 httpServletRequest.setAttribute("usuarioTipoRepavimentadora", "true" );
	     }else{
	    	 sessao.removeAttribute("usuarioTipoRepavimentadora");
	    	 httpServletRequest.removeAttribute("usuarioTipoRepavimentadora" );
	     }

	     //	pesquisa na colecao para atualizacao na tela
		 if ( pagina != null ) {
		     
			 collOrdemServicoPavimento = fachada.pesquisarOrdemRepavimentacaoProcessoAceite(osPavimentoAceiteHelper,
					 pagina);
			 
			 if ( osPavimentoAceiteHelper.getSituacaoAceite().toString().equals("1") || 
					 osPavimentoAceiteHelper.getSituacaoAceite().toString().equals("2") ) {
				 
				 Integer atualizaPaginacao = fachada.pesquisarOrdemRepavimentacaoProcessoAceiteCount(osPavimentoAceiteHelper);
			     
			     if ( atualizaPaginacao == null || atualizaPaginacao.equals("") || 
			    		 atualizaPaginacao.equals(" ") || atualizaPaginacao.intValue() <= 0 ) {
			    	 throw new ActionServletException("atencao.informarOutroFiltro", "exibirFiltrarOrdemRepavimentacaoProcessoAceiteAction.do?limpar=S", null, new String[]{} );
			     } else {
			    	 sessao.setAttribute("totalRegistros", atualizaPaginacao);
				     retorno = this.controlarPaginacao(httpServletRequest, retorno, atualizaPaginacao );
			     }
			     
			 }
		     
		 } else {
		     
			 collOrdemServicoPavimento = fachada.pesquisarOrdemRepavimentacaoProcessoAceite(osPavimentoAceiteHelper,
					 (Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));
			 
			 if ( osPavimentoAceiteHelper.getSituacaoAceite().toString().equals("1")  ||
					 osPavimentoAceiteHelper.getSituacaoAceite().toString().equals("2")  ) {
				
				 Integer atualizaPaginacao = fachada.pesquisarOrdemRepavimentacaoProcessoAceiteCount(osPavimentoAceiteHelper);
			     
			     if ( atualizaPaginacao == null || atualizaPaginacao.equals("") || 
			    		 atualizaPaginacao.equals(" ") || atualizaPaginacao.intValue() <= 0 ) {
			    	 throw new ActionServletException("atencao.informarOutroFiltro", "exibirFiltrarOrdemRepavimentacaoProcessoAceiteAction.do?limpar=S", null, new String[]{} );

			     } 
			 }

	    	 Integer atualizaPaginacao = fachada.pesquisarOrdemRepavimentacaoProcessoAceiteCount(osPavimentoAceiteHelper);
		     

		     if ( idsRegistrosChecados != null && idsRegistrosChecados.length == 10 ) {
		    	 
		    	 collOrdemServicoPavimento = fachada.pesquisarOrdemRepavimentacaoProcessoAceite(osPavimentoAceiteHelper,
						 (Integer) 0 );
		    	 sessao.setAttribute("totalRegistros", atualizaPaginacao);
		    	 sessao.setAttribute("page.offset", (Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa") + 1 );
			     retorno = this.controlarPaginacao(httpServletRequest, retorno, atualizaPaginacao );
			 
		     } else if (collOrdemServicoPavimento != null && collOrdemServicoPavimento.size() == 0) { 
	     	
		    	 collOrdemServicoPavimento = fachada.pesquisarOrdemRepavimentacaoProcessoAceite(osPavimentoAceiteHelper,
						 (Integer) 0 );
		    	 sessao.setAttribute("totalRegistros", atualizaPaginacao);
		    	 sessao.setAttribute("page.offset", 1);
			     retorno = this.controlarPaginacao(httpServletRequest, retorno, atualizaPaginacao );
			     
	     	 }else {
				
				 sessao.setAttribute("totalRegistros", atualizaPaginacao);
			 }
		 }
		 
		 httpServletRequest.removeAttribute("fecharPopup");
		 sessao.setAttribute("collOrdemServicoPavimentoAceite", collOrdemServicoPavimento);
		 sessao.removeAttribute("numeroPagina");
			
         return retorno;
    }
    
	/**
	 * UC_1020 - Exibir Ordens de Repavimentação em Processo de Aceite;
	 * 
	 * 5.1.	Caso exista ordem de repavimentação (sem aceite ou com aceite rejeitado) e não rejeitada 
	 * (existe ocorrência na tabela ORDEM_SERVICO_PAVIMENTO com (OSPV_ICACEITE com o valor nulo 
	 * ou com o valor 2 (dois)) e RPMR_ID com o valor nulo):
	 * 
	 * @author Hugo Leonardo
	 * 
	 * @date 19/05/2010, 10/12/2010
	 * @param usuario
	 * @param fachada
	 * @return boolean
	 */
    private boolean verificaOrdemRepavSemAceite(Usuario usuario, Fachada fachada, 
    		OrdemRepavimentacaoProcessoAceiteHelper osPavimentoAceiteHelper){
    	
    	boolean permissao = false;
    	
    	Collection collOrdemServicoPavimento = null;
    	
    	collOrdemServicoPavimento = fachada.pesquisarOrdemRepavimentacaoProcessoAceite( osPavimentoAceiteHelper, null );
    	
    	Iterator iteratorCollOrdemServicoPavimento = collOrdemServicoPavimento.iterator();
   	 	OSPavimentoRetornoHelper osPavimentoRetornoHelper  = new OSPavimentoRetornoHelper();
   	 	
   	 	while( iteratorCollOrdemServicoPavimento.hasNext() ) {
   		 
   	 		osPavimentoRetornoHelper = (OSPavimentoRetornoHelper) iteratorCollOrdemServicoPavimento.next();
   	 		
   	 		if(osPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite() == null 
   	 				&& osPavimentoRetornoHelper.getOrdemServicoPavimento().getMotivoRejeicao() == null){
   	 			
   	 			permissao = true;
   	 		}
   	 	}

    	return permissao;
    }
    
	/**
	 * UC_1020 - Exibir Ordens de Repavimentação em Processo de Aceite;
	 * 
	 * 5.1.2 Caso exista ordem de repavimentação com dados de pavimento 
	 * da contratante convergentes com os dados de pavimento retorno da contratada;
	 * 
	 * @author Hugo Leonardo
	 * 
	 * @date 19/05/2010
	 * @param usuario
	 * @param fachada
	 * @return boolean
	 */
    private boolean verificaOrdemRepavConvergentes(Usuario usuario, Fachada fachada, 
    		OrdemRepavimentacaoProcessoAceiteHelper osPavimentoAceiteHelper){
    	
    	boolean permissao = false;
    	
    	Collection collOrdemServicoPavimento = null;
    	
    	collOrdemServicoPavimento = fachada.pesquisarOrdemRepavimentacaoProcessoAceite( osPavimentoAceiteHelper, null );
    	Iterator iteratorCollOrdemServicoPavimento = collOrdemServicoPavimento.iterator();
   	 	OSPavimentoRetornoHelper osPavimentoRetornoHelper  = new OSPavimentoRetornoHelper();

   	 	while( iteratorCollOrdemServicoPavimento.hasNext() ) {
   		 
   	 		osPavimentoRetornoHelper = (OSPavimentoRetornoHelper) iteratorCollOrdemServicoPavimento.next();
   	 		
   	 		if(osPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRua().compareTo(
   	 			osPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRuaRetorno()) == 0 
   	 			&& osPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getId().equals(
   	 			   osPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getId())
   	 			&& osPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite() == null
   	 			&& osPavimentoRetornoHelper.getOrdemServicoPavimento().getMotivoRejeicao() == null){

   	 			permissao = true;
   	 			
   	 			break;
   	 		}
   	 	}

    	return permissao;
    }
    
	/**
	 * Verifica se usuario em Permissao para atualizar o 
	 * retorno das ordens de Serviço em Processo de Aceite atraves do botão Informar Aceite.
	 * 
	 * @author Hugo Leonardo
	 * 
	 * @date 17/05/2010
	 * @param usuario
	 * @param fachada
	 * @return boolean
	 */
    private boolean verificaUnidadeUsuario( Usuario usuario, Fachada fachada) {
    	
    	boolean temPermissao = false;
    	
    	Collection colecaoUnidadesResponsaveis = null;
    	FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
    	
		if ( usuario != null && usuario.getUnidadeOrganizacional() != null && 
				usuario.getUnidadeOrganizacional().getUnidadeTipo() != null && 
				usuario.getUnidadeOrganizacional().getUnidadeTipo().getId() != null &&
				(usuario.getUnidadeOrganizacional().getUnidadeTipo().getId().intValue() == 
					UnidadeTipo.UNIDADE_TIPO_REPAVIMENTADORA_ID.intValue()) ) { 
			
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, usuario.getUnidadeOrganizacional().getId()));
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID_UNIDADE_TIPO,UnidadeTipo.UNIDADE_TIPO_REPAVIMENTADORA_ID));
	
			colecaoUnidadesResponsaveis = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			if ( colecaoUnidadesResponsaveis != null && !colecaoUnidadesResponsaveis.isEmpty() ) {
				temPermissao = true;
			}
	    
		}
		return temPermissao;
    }
    
    /**
     * @author Hugo Leonardo
     * 
     * @date 17/05/2010 
     * 
     * @param OrdemRepavimentacaoProcessoAceiteHelper
     * @param form
     * @param fachada
     */
    private void adicionaOsParametrosNoForm( OrdemRepavimentacaoProcessoAceiteHelper ordemRepavimentacaoProcessoAceiteHelper, 
    		FiltrarOrdemRepavimentacaoProcessoAceiteActionForm form , Fachada fachada) {
    	
    	if(ordemRepavimentacaoProcessoAceiteHelper.getIdUnidadeResponsavel() != null){
			
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
						FiltroUnidadeOrganizacional.ID, ordemRepavimentacaoProcessoAceiteHelper.getIdUnidadeResponsavel()));			
			
			Collection colecaoUnidadesResponsaveis = fachada.pesquisar(
						filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadesResponsaveis);		
			
			if(unidadeOrganizacional != null){
				form.setDescricaoUnidadeResponsavel(unidadeOrganizacional.getDescricao());
			}				
			
		}
		
		if(ordemRepavimentacaoProcessoAceiteHelper.getSituacaoAceite() != null){				
			String situacao = "";				
			if(ordemRepavimentacaoProcessoAceiteHelper.getSituacaoAceite().toString().equals("1")){
				situacao = "SEM ACEITES";
			}else if(ordemRepavimentacaoProcessoAceiteHelper.getSituacaoAceite().toString().equals("2")){
				situacao = "ACEITAS";
			}else if(ordemRepavimentacaoProcessoAceiteHelper.getSituacaoAceite().toString().equals("3")){
				situacao = "NÃO ACEITAS";
			}else{
				situacao = "TODAS";
			}
		  form.setSituacaoAceiteDescricao(situacao);					
		}
		
		// Periodo de Aceite do Servico
		if(ordemRepavimentacaoProcessoAceiteHelper.getPeriodoAceiteServicoInicial() != null && ordemRepavimentacaoProcessoAceiteHelper.getPeriodoAceiteServicoFinal()!= null){
			form.setPeriodoAceiteServicoInicial(ordemRepavimentacaoProcessoAceiteHelper.getPeriodoAceiteServicoInicial());
			form.setPeriodoAceiteServicoFinal(ordemRepavimentacaoProcessoAceiteHelper.getPeriodoAceiteServicoFinal());
		}else{
			form.setPeriodoAceiteServicoInicial(null);
			form.setPeriodoAceiteServicoFinal(null);
		}
		
		// Periodo de Retorno do Servico
		if(ordemRepavimentacaoProcessoAceiteHelper.getPeriodoRetornoServicoInicial() != null && ordemRepavimentacaoProcessoAceiteHelper.getPeriodoRetornoServicoFinal()!= null){
			form.setPeriodoRetornoServicoInicial(ordemRepavimentacaoProcessoAceiteHelper.getPeriodoRetornoServicoInicial());
			form.setPeriodoRetornoServicoFinal(ordemRepavimentacaoProcessoAceiteHelper.getPeriodoRetornoServicoFinal());
		}else{
			form.setPeriodoRetornoServicoInicial(null);
			form.setPeriodoRetornoServicoFinal(null);
		}
		
		// Unidade de Encerramento
		if(ordemRepavimentacaoProcessoAceiteHelper.getIdUnidadeOrganizacional() !=null && !ordemRepavimentacaoProcessoAceiteHelper.getIdUnidadeOrganizacional().equals("")){
			form.setIdUnidadeOrganizacional(ordemRepavimentacaoProcessoAceiteHelper.getIdUnidadeOrganizacional().toString());
		}else{
			form.setIdUnidadeOrganizacional(null);
		}
		
    }
}
