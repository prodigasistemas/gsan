package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroOrdemServicoPavimento;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gcom.atendimentopublico.ordemservico.bean.OSPavimentoHelper;
import gcom.atendimentopublico.ordemservico.bean.OSPavimentoRetornoHelper;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.FiltroPavimentoRua;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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
 * Action de exibir manter o Ordem Processo de Repavimentação
 * @author Yara Taciane de Souza
 * @created 03/06/2008
 * 
 * * @alterado por Arthur Carvalho 
 * * @data:28-04-2010
 */

public class ExibirManterOrdemProcessoRepavimentacaoAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("manterOrdemProcessoRepavimentacao");
		HttpSession sessao = httpServletRequest.getSession(false);		
		Fachada fachada = Fachada.getInstancia();  
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		boolean temPermissao = verificaUnidadeUsuario(usuario, fachada);		
		
		FiltrarOrdemProcessoRepavimentacaoActionForm form = (FiltrarOrdemProcessoRepavimentacaoActionForm) actionForm;
		
		//Recebe os Parametros do filtro atraves do Helper e seta os valores no FORM
		OSPavimentoHelper osPavimentoHelper = null;
		if (sessao.getAttribute("osPavimentoHelper") != null) {
			
			osPavimentoHelper =  (OSPavimentoHelper) sessao.getAttribute("osPavimentoHelper");
			
			adicionaOsParametrosNoForm( osPavimentoHelper , form , fachada );
						
		}
		
		if(osPavimentoHelper.getSituacaoRetorno() == 4){
			form.setEscolhaRelatorio("1");
		}else if ( form.getEscolhaRelatorio() == null || form.getEscolhaRelatorio().equals("") ) {
			form.setEscolhaRelatorio("2");
		}
		
		if(form.getManterPaginaAux() == null ){
			
   		 	form.setManterPaginaAux("0");
			httpServletRequest.setAttribute("manterPaginaAux", form.getManterPaginaAux() );
		}
		
		 Collection collOrdemServicoPavimento = null;		    
	     if (httpServletRequest.getParameter("retornaDoPopup") != null) {
	        	collOrdemServicoPavimento = fachada.pesquisarOrdemProcessoRepavimentacao(osPavimentoHelper,
	        			(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));
		 }else{
				if (sessao.getAttribute("collOrdemServicoPavimentoHelper") != null) {
					collOrdemServicoPavimento =  (Collection) sessao.getAttribute("collOrdemServicoPavimentoHelper");
				}				
		 }
	     
	     //Conta a quantidade de registros selecionados.
	     Integer ordemServicoPavimentoCount = fachada.pesquisarOrdemProcessoRepavimentacaoCount(osPavimentoHelper);
	     
	     if ( ordemServicoPavimentoCount == null || ordemServicoPavimentoCount.equals("") || 
	    		 ordemServicoPavimentoCount.equals(" ") || ordemServicoPavimentoCount.intValue() <= 0 ) {
	    	 throw new ActionServletException("atencao.informarOutroFiltro", "exibirFiltrarOrdemProcessoRepavimentacaoAction.do?limpar=S", null, new String[]{} );
	     }
	     
	     form.setOrdensServicoSelecionadas(ordemServicoPavimentoCount.toString());
	     
	     retorno = this.controlarPaginacao(httpServletRequest, retorno, ordemServicoPavimentoCount );
	     
	     
	     //pesquisa na colecao para atualizacao na tela
	     Integer pagina = null;
	     Integer paginaAtualizada = null ;
	     if ( httpServletRequest.getAttribute("numeroPaginasPesquisa") != null && 
	    		 (!httpServletRequest.getAttribute("numeroPaginasPesquisa").toString().equals("0") || 
	    				 form.getManterPaginaAux() != null ) ) {
	    	
	    	 //Primeira vez que entra na pagina
	    	 if ( sessao.getAttribute("numeroPagina") != null ) {
	    		 //Controla pagina para que nao retorne para pagina incial.
	    		 String paginaAtual = (String) sessao.getAttribute("numeroPagina"); 
	    		 form.setManterPaginaAux(  paginaAtual );
	    		 pagina = (Integer) Util.converterStringParaInteger( form.getManterPaginaAux() );
		    	
	    		 collOrdemServicoPavimento = fachada.pesquisarOrdemProcessoRepavimentacao( osPavimentoHelper, pagina );
		    	 
	    		 Integer numeroDaPagina = pagina.intValue() +1;
		    	 httpServletRequest.setAttribute("page.offset", numeroDaPagina.toString() );
		    	 httpServletRequest.setAttribute("numeroPaginasPesquisa", pagina.toString() );
		    	 
		    	 retorno = this.controlarPaginacao(httpServletRequest, retorno, ordemServicoPavimentoCount );
	    	 
	    	 } else {
				 //Esse fluxo serve para ter o controle da paginação quando a pagina for atualizada atraves do popup.
	    		 paginaAtualizada = (Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa") + 1;
	    		 form.setManterPaginaAux(  paginaAtualizada.toString() );
	    		 
	    		 collOrdemServicoPavimento = fachada.pesquisarOrdemProcessoRepavimentacao(osPavimentoHelper,
		    	 			(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));
	    		 
	    		 //Fluxo responsavel caso as OS sejam pesquisada com a situação PENDENTE
	    		 if ( osPavimentoHelper.getSituacaoRetorno().toString().equals("1") &&
	    				 httpServletRequest.getParameter("retornaDoPopup") != null ) {
	    			 
	    			 collOrdemServicoPavimento = fachada.pesquisarOrdemProcessoRepavimentacao(osPavimentoHelper,
	    					 (Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));
	    			 
	    			 sessao.setAttribute("totalRegistros", ordemServicoPavimentoCount);

	    		 } else {
	    			 
	    			 collOrdemServicoPavimento = fachada.pesquisarOrdemProcessoRepavimentacao(osPavimentoHelper,
			    	 			(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));
	    		 }
	    		 
	    		 
	    	 }
	    	 
	    	 
	     } else {
	    	 //Pega o numero da pagina atraves do aux criado para guardar o numero da pagina.
	    	 //segunda vez em diante que abre a pagina
	    	 if (form.getManterPaginaAux() != null ) {
		    
	    		 pagina = (Integer) Util.converterStringParaInteger(form.getManterPaginaAux());
		    	 
	    		 collOrdemServicoPavimento = fachada.pesquisarOrdemProcessoRepavimentacao(osPavimentoHelper, pagina);
		    	 
		    	 Integer pag = pagina.intValue() +1;
		    	 httpServletRequest.setAttribute("page.offset", pag.toString() );
		    	 
		    	 retorno = this.controlarPaginacao(httpServletRequest, retorno, ordemServicoPavimentoCount );
	    	
	    	 } else {
	    		
	    		 collOrdemServicoPavimento = fachada.pesquisarOrdemProcessoRepavimentacao(osPavimentoHelper,
		    	 			(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));
	    	 }
	     }
	     String[] idsRegistrosChecados = form.getIdRegistro(); 
	     
         //Verifica se o usuario clicou no botao de Concordancia das demandas.
         if ( httpServletRequest.getParameter("acao") != null && 
         	  httpServletRequest.getParameter("acao").equals("concordanciaDemandas")){
        	
        	 //verifica a permissao do usuario.
        	 if (temPermissao) {
        		 
        		 Iterator iteratorCollOrdemServicoPavimento = collOrdemServicoPavimento.iterator();
	        	 OSPavimentoRetornoHelper osPavimentoRetornoHelper  = new OSPavimentoRetornoHelper();
	        	 while( iteratorCollOrdemServicoPavimento.hasNext() ) {
	        		 
	        		 osPavimentoRetornoHelper = (OSPavimentoRetornoHelper) iteratorCollOrdemServicoPavimento.next();
	        		 
	        		 for ( int i=0 ; i < idsRegistrosChecados.length ; i++ ) { 
	        			 //verifica se o dado exibido é igual 
	        			 if(osPavimentoRetornoHelper.getOrdemServico().getId().toString().equals(idsRegistrosChecados[i])
	        					 && osPavimentoRetornoHelper.getOrdemServicoPavimento().getMotivoRejeicao() == null){
	        				 
	        				 osPavimentoHelper.setPavimentoRuaRetorno( osPavimentoHelper.getPavimentoRua() );
	        				 osPavimentoHelper.setPavimentoCalcadaRetorno( osPavimentoHelper.getPavimentoCalcada() );
	        				 
	        				 //Pesquisar a Ordem Servico para atualizacao na base
	        				 FiltroOrdemServicoPavimento filtroOrdemServicoPavimento = new FiltroOrdemServicoPavimento();
	        				 filtroOrdemServicoPavimento.adicionarParametro( new ParametroSimples(
	        						 FiltroOrdemServicoPavimento.ORDEM_SERVICO_ID, idsRegistrosChecados[i]));
	        				 
	        				 Collection colecaoOrdemServicoPavimento = fachada.pesquisar(filtroOrdemServicoPavimento,
	        						 OrdemServicoPavimento.class.getName());
	        				 
	        				 OrdemServicoPavimento ordemServicoPavimento = (OrdemServicoPavimento) 
	        				 		Util.retonarObjetoDeColecao(colecaoOrdemServicoPavimento);
	        				 //ordemServicoPavimento.setAreaPavimentoCalcadaRetorno(osPavimentoHelper.getAreaPavimentoCalcada());
	        				 FiltroPavimentoRua filtroPavimentoRua = new FiltroPavimentoRua();
	        				 filtroPavimentoRua.adicionarParametro( new ParametroSimples( FiltroPavimentoRua.ID,
	        						 osPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getId() ) );
	        				 
	        				 Collection colecaoPavimentoRuaRetorno = fachada.pesquisar(filtroPavimentoRua, 
	        						 PavimentoRua.class.getName());
	        				 
	        				 PavimentoRua pavimentoRuaRetorno = (PavimentoRua) 
	        				 	Util.retonarObjetoDeColecao(colecaoPavimentoRuaRetorno);
	        				 
	        				 //Caso tenha sido registrado o retorno para um serviço ele não mais poderá ser alterado;
	        				 if (ordemServicoPavimento.getPavimentoRuaRetorno() == null || 
	        						 ordemServicoPavimento.getPavimentoRua().equals("")) {
		        				 
	        					 ordemServicoPavimento.setPavimentoRuaRetorno(pavimentoRuaRetorno);
		        				 ordemServicoPavimento.setAreaPavimentoRuaRetorno(
		        						 osPavimentoRetornoHelper.getOrdemServicoPavimento()
		        						 	.getAreaPavimentoRua());
		        				 ordemServicoPavimento.setDataExecucao(new Date());
		        				 ordemServicoPavimento.setIndicadorAceite(ConstantesSistema.SIM);
		        				 ordemServicoPavimento.setDataAceite(ordemServicoPavimento.getDataExecucao());
		        				 
		        				 if ( ordemServicoPavimento.getDescricaoMotivoAceite() != null &&
		        						 !ordemServicoPavimento.getDescricaoMotivoAceite().equals("") ) {
		        					 
		        					 String descricaoJaCadastrada = ordemServicoPavimento.getDescricaoMotivoAceite();
		        					 ordemServicoPavimento.setDescricaoMotivoAceite( descricaoJaCadastrada + " ACEITE AUTOMATICO");
		        					 
		        				 } else {
		        					 
		        					 ordemServicoPavimento.setDescricaoMotivoAceite("ACEITE AUTOMATICO");
		        				 }
		        				 //Usuario admin.
		        				 ordemServicoPavimento.setUsuarioAceite(Usuario.USUARIO_BATCH);
		        				 //ordemServicoPavimento.set
		        				 
		        				 fachada.atualizar(ordemServicoPavimento);
		        				 
		        				 
		        			     //pesquisa na colecao para atualizacao na tela
		        				 if ( pagina != null ) {
			        			     
		        					 collOrdemServicoPavimento = fachada.pesquisarOrdemProcessoRepavimentacao(osPavimentoHelper,
		        							 pagina);
		        					 
		        					 if ( osPavimentoHelper.getSituacaoRetorno().toString() != "1" && 
		        							 osPavimentoHelper.getSituacaoRetorno().toString() != "2" ) {
			        					 
		        						 Integer atualizaPaginacao = fachada.pesquisarOrdemProcessoRepavimentacaoCount(osPavimentoHelper);
			        				     
			        				     if ( atualizaPaginacao == null || atualizaPaginacao.equals("") || 
			        				    		 atualizaPaginacao.equals(" ") || atualizaPaginacao.intValue() <= 0 ) {
			        				    	 throw new ActionServletException("atencao.informarOutroFiltro", "exibirFiltrarOrdemProcessoRepavimentacaoAction.do?limpar=S", null, new String[]{} );
			        				     } else {
			        				    	 sessao.setAttribute("totalRegistros", atualizaPaginacao);
				        				     retorno = this.controlarPaginacao(httpServletRequest, retorno, atualizaPaginacao );
			        				     }
			        				     
		        					 }
		        				     
		        				 } else {
			        			     
		        					 collOrdemServicoPavimento = fachada.pesquisarOrdemProcessoRepavimentacao(osPavimentoHelper,
			        						 (Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));
		        					 
		        					 if ( !osPavimentoHelper.getSituacaoRetorno().toString().equals("1")  && 
		        							 !osPavimentoHelper.getSituacaoRetorno().toString().equals("2")  ) {
			        					
		        						 Integer atualizaPaginacao = fachada.pesquisarOrdemProcessoRepavimentacaoCount(osPavimentoHelper);
			        				     
			        				     if ( atualizaPaginacao == null || atualizaPaginacao.equals("") || 
			        				    		 atualizaPaginacao.equals(" ") || atualizaPaginacao.intValue() <= 0 ) {
			        				    	 throw new ActionServletException("atencao.informarOutroFiltro", "exibirFiltrarOrdemProcessoRepavimentacaoAction.do?limpar=S", null, new String[]{} );

			        				     } 
		        					 }
		        					 //situacao pendente
		        				     if ( osPavimentoHelper.getSituacaoRetorno().toString().equals("1") ) {
		        				    	 
		        				    	 Integer atualizaPaginacao = fachada.pesquisarOrdemProcessoRepavimentacaoCount(osPavimentoHelper);
			        				     
			        				     if ( atualizaPaginacao == null || atualizaPaginacao.equals("") || 
			        				    		 atualizaPaginacao.equals(" ") || atualizaPaginacao.intValue() <= 0 ) {
			        				    	 throw new ActionServletException("atencao.informarOutroFiltro", "exibirFiltrarOrdemProcessoRepavimentacaoAction.do?limpar=S", null, new String[]{} );

			        				     } 
				        				
			        				     if ( idsRegistrosChecados.length == 10 ) {
			        				    	 
			        				    	 collOrdemServicoPavimento = fachada.pesquisarOrdemProcessoRepavimentacao(osPavimentoHelper,
					        						 (Integer) 0 );
			        				    	 sessao.setAttribute("totalRegistros", atualizaPaginacao);
			        				    	 sessao.setAttribute("page.offset", (Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa") + 1 );
				        				     retorno = this.controlarPaginacao(httpServletRequest, retorno, atualizaPaginacao );
				        				 
			        				     } else if (collOrdemServicoPavimento.size() == 0) { 
		        				     	
			        				    	 collOrdemServicoPavimento = fachada.pesquisarOrdemProcessoRepavimentacao(osPavimentoHelper,
					        						 (Integer) 0 );
			        				    	 sessao.setAttribute("totalRegistros", atualizaPaginacao);
			        				    	 sessao.setAttribute("page.offset", 1);
				        				     retorno = this.controlarPaginacao(httpServletRequest, retorno, atualizaPaginacao );
				        				     
		        				     	 }else {
				        					
				        					 sessao.setAttribute("totalRegistros", atualizaPaginacao);
				        				 }
		        				     }
		        				 }

	        				 } else {
	        					 throw new ActionServletException("atencao.registro_nao_pode_ser_atualizado", 
	        							 "exibirManterOrdemProcessoRepavimentacaoAction.do", null , new String[] {});
	        				 }
		        		 }
	        		 }
	        	 }
        	 } else {
        		 throw new ActionServletException("atencao.nao_possui_permissao_para_atualizar");
        	 }
         }
         
		// httpServletRequest.setAttribute("collOrdemServicoPavimento", collOrdemServicoPavimento);
		 sessao.setAttribute("collOrdemServicoPavimento", collOrdemServicoPavimento);
		 sessao.removeAttribute("numeroPagina");
		 sessao.removeAttribute("desabilitaBotaoAlterar");
		 sessao.removeAttribute("colecaoMotivoRejeicao");
			
         return retorno;
        
    }
    
	
	/**
	 * Verifica se usuario em Permissao para atualizar o 
	 * retorno das ordens de Serviço atraves do botão confirmar demandas.
	 * 
	 * @author Arthur Carvalho
	 * 
	 * @date 26/04/2010
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
     * @author Arthur Carvalho
     * 
     * @date 04/05/2010 
     * 
     * @param osPavimentoHelper
     * @param form
     * @param fachada
     */
    private void adicionaOsParametrosNoForm( OSPavimentoHelper osPavimentoHelper, 
    		FiltrarOrdemProcessoRepavimentacaoActionForm form , Fachada fachada) {
    	
    	if(osPavimentoHelper.getIdUnidadeResponsavel() != null){
			
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
						FiltroUnidadeOrganizacional.ID,osPavimentoHelper.getIdUnidadeResponsavel()));			
			
			Collection colecaoUnidadesResponsaveis = fachada.pesquisar(
						filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadesResponsaveis);		
			
			if(unidadeOrganizacional != null){
				form.setDescricaoUnidadeResponsavel(unidadeOrganizacional.getDescricao());
			}				
			
		}
		
		if(osPavimentoHelper.getSituacaoRetorno() != null){				
			String situacao = "";				
			if(osPavimentoHelper.getSituacaoRetorno().toString().equals("1")){
				situacao = "PENDENTES";
			}else if(osPavimentoHelper.getSituacaoRetorno().toString().equals("2")){
				situacao = "CONCLUÍDAS";
			}else if(osPavimentoHelper.getSituacaoRetorno().toString().equals("4")){
				situacao = "REJEITADAS";
			// 3
			}else{
				situacao = "TODAS";
			}
		  form.setSituacaoRetornoDescricao(situacao);					
		}
		
		if(osPavimentoHelper.getPeriodoEncerramentoOsInicial() != null && osPavimentoHelper.getPeriodoEncerramentoOsInicial()!= null){
			form.setPeriodoEncerramentoOsInicial(osPavimentoHelper.getPeriodoEncerramentoOsInicial());
			form.setPeriodoEncerramentoOsFinal(osPavimentoHelper.getPeriodoEncerramentoOsFinal());
		}else{
			form.setPeriodoEncerramentoOsInicial(null);
			form.setPeriodoEncerramentoOsFinal(null);
		}
		
		if(osPavimentoHelper.getIdMunicipio() != null){
			
			FiltroMunicipio filtro = new FiltroMunicipio();
			filtro.adicionarParametro(
				new ParametroSimples(
					FiltroMunicipio.ID,
					osPavimentoHelper.getIdMunicipio()));			
			
			Collection colecaoMunicipio = 
				this.getFachada().pesquisar(
					filtro, 
					Municipio.class.getName());
			
			Municipio municipio = (Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);		
			
			if(municipio != null){
				form.setIdMunicipio(municipio.getId().toString());
				form.setMunicipioDescricao(municipio.getNome());
			}
		}
		
		if(osPavimentoHelper.getIdBairro() != null){
			
			FiltroBairro filtro = new FiltroBairro();
			filtro.adicionarParametro(
				new ParametroSimples(
					FiltroBairro.ID,
					osPavimentoHelper.getIdBairro()));			
			
			Collection colecao = 
				this.getFachada().pesquisar(
					filtro, 
					Bairro.class.getName());
			
			Bairro bairro = (Bairro) Util.retonarObjetoDeColecao(colecao);		
			
			if(bairro != null){
				form.setIdBairro(bairro.getId().toString());
				form.setBairroDescricao(bairro.getNome());
			}
			
		}

		if(osPavimentoHelper.getIdLogradouro() != null){
			FiltroLogradouro filtro = new FiltroLogradouro();
			filtro.adicionarParametro(
				new ParametroSimples(
					FiltroLogradouro.ID,
					osPavimentoHelper.getIdLogradouro()));			
			
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouro.LOGRADOUROTIPO);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouro.LOGRADOUROTITULO);
			
			Collection colecao = 
				this.getFachada().pesquisar(
					filtro, 
					Logradouro.class.getName());
			
			Logradouro logradouro = (Logradouro) Util.retonarObjetoDeColecao(colecao);		
			
			if(logradouro != null){
				form.setIdLogradouro(logradouro.getId().toString());
				form.setLogradouroDescricao(logradouro.getDescricaoFormatada());
			}
			

		}

		
    }
  

}
