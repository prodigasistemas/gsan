package gcom.gui.relatorio.faturamento.conta;

import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.faturamento.FiltroMotivoCancelamento;
import gcom.faturamento.conta.ContaMotivoCancelamento;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.faturamento.conta.FiltroMotivoRetificacaoConta;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.relatorio.faturamento.RelatorioContasCanceladasRetificadasActionForm;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
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
 * @author Flávio Cordeiro
 */
public class ExibirFiltrarRelatorioContasCanceladasRetificadasAction extends GcomAction {

	private Collection colecaoPesquisa = null;
	private String localidadeID = null;
	private String responsavelID = null;

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirFiltrarRelatorioContasCanceladasRetificadas");

        Fachada fachada = Fachada.getInstancia();
        
        RelatorioContasCanceladasRetificadasActionForm form = 
        	(RelatorioContasCanceladasRetificadasActionForm) actionForm;
        
        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);
        String tipoContaVar = (String) httpServletRequest.getParameter("tipoContaVar");
        String menu = (String) httpServletRequest.getParameter("menu");
        
        sessao.setAttribute("mostrarLogin",true);
        
        //----------- Pesqusia Localidade
        if((form != null && form.getLocalidadeFiltro() != null && !form.getLocalidadeFiltro().equals(""))
        		&&(form.getNomeLocalidade() == null || form.getNomeLocalidade().equals(""))){
        	
        	pesquisarLocalidade(form, fachada, httpServletRequest);
    	}
        
     
        //----------- Pesquisa Motivo
        Collection colecaoMotivo = new ArrayList(); 
        if(sessao.getAttribute("colecaoCategoria") == null){
        	form.setTipoConta("1");
        }
        
        
    	//verifica se foi clicado num botão de seleção de tipo de relatório de contas canceladas ou retificadas
    	if(tipoContaVar != null && tipoContaVar.equals("sim")){
    		
    		//verifica se o tipo de relatorio eh diferente de nulo
    		if(form.getRelatorioTipo() != null){
    			//se o relatorio for sintetico passa o parametro visibilidade como hidden para nao exibir 
    			//os tipos de ordenacao
    			if(form.getRelatorioTipo().equals("2")){
    			  form.setOrdenacaoTipo(null);
    			  form.setPeriodoInicial("");
    			  form.setPeriodoFinal("");
    			  sessao.setAttribute("visibilidade","hidden");  
    			  sessao.setAttribute("visibilidade2","hidden");  
    			  
    			}else if(form.getRelatorioTipo().equals("1")){
    		      
    			   //seta o relatorio como analítico
    			  form.setRelatorioTipo("1");
    		      
    			  //verifica se o tipo de ordenação selecionado eh por data, se for mostra as datas
    		      if(form.getOrdenacaoTipo().equals("2")){
    		    	sessao.setAttribute("visibilidade","visible");  
    		    	sessao.setAttribute("visibilidade2","visible");  
    		      }else {
    		    	  form.setOrdenacaoTipo("1"); 
    		    	  sessao.setAttribute("visibilidade","visible");  
    		    	  sessao.setAttribute("visibilidade2","hidden");  
    		      }
    			}
    		}
    	}else if(form.getRelatorioTipo() != null){
    			//se o relatorio for sintetico passa o parametro visibilidade como hidden para nao exibir 
    			//os tipos de ordenacao
    			if(form.getRelatorioTipo().equals("2")){
    			  form.setOrdenacaoTipo(null);
    			  form.setPeriodoInicial("");
    			  form.setPeriodoFinal("");
    			  sessao.setAttribute("visibilidade","hidden");   			  
    			}else if(form.getRelatorioTipo().equals("1")){
    		      
    			   //seta o relatorio como analítico
    			  form.setRelatorioTipo("1");
    		      
    			  //verifica se o tipo de ordenação selecionado eh por data, se for mostra as datas
    		      if(form.getOrdenacaoTipo().equals("2")){
    		    	sessao.setAttribute("visibilidade","visible");  
    		    	sessao.setAttribute("visibilidade2","visible"); 
    		      }else {
    		    	  form.setOrdenacaoTipo("1"); 
    		    	  sessao.setAttribute("visibilidade","hidden");  
    		      }
    			}
    	}else {
    		form.setRelatorioTipo("2");    		
    	}
    	
    	if(menu != null && menu.equals("sim")){
    	  sessao.setAttribute("visibilidade","hidden");
    	  sessao.setAttribute("visibilidade2","hidden"); 
    	}
        
        //se motico canceladas
        if(form.getTipoConta() == null || form.getTipoConta().equals("") || form.getTipoConta().equals("1")){
        	FiltroMotivoCancelamento filtroMotivoCancelamento = new FiltroMotivoCancelamento();
        	filtroMotivoCancelamento.setCampoOrderBy(FiltroMotivoCancelamento.DESCRICAO);
        	colecaoMotivo = fachada.pesquisar(filtroMotivoCancelamento, ContaMotivoCancelamento.class.getName());
        	sessao.setAttribute("colecaoMotivoCancelamento", colecaoMotivo);
        	sessao.removeAttribute("colecaoMotivoRetificacao");
        
        }else if(form.getTipoConta().equals("2")) {
        //se motivo retificadas
        	FiltroMotivoRetificacaoConta filtroMotivoRetificacaoConta = new FiltroMotivoRetificacaoConta();
        	filtroMotivoRetificacaoConta.setCampoOrderBy(FiltroMotivoRetificacaoConta.DESCRICAO_MOTIVO_RETIFICACAO_CONTA);
        	colecaoMotivo = fachada.pesquisar(filtroMotivoRetificacaoConta, ContaMotivoRetificacao.class.getName());
        	sessao.setAttribute("colecaoMotivoRetificacao", colecaoMotivo);
        	sessao.removeAttribute("colecaoMotivoCancelamento");
        	
        }else{
        	sessao.removeAttribute("colecaoMotivoCancelamento");
        	sessao.removeAttribute("colecaoMotivoRetificacao");
        }
        
        
        //Pesquisa Categoria
        if(sessao.getAttribute("colecaoCategoria") == null){
        	Collection colecaoCategoria = new ArrayList();
        	FiltroCategoria filtroCategoria = new FiltroCategoria();
        	filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
        	colecaoCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName());
        	sessao.setAttribute("colecaoCategoria", colecaoCategoria);
        }
        
        //Pesquisa Perfil do Imovel
        if(sessao.getAttribute("colecaoPerfil") == null){
        	Collection colecaoPerfil = new ArrayList();
        	FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
        	filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
        	colecaoPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
        	sessao.setAttribute("colecaoPerfil", colecaoPerfil);
        }
        
        //Pesquisar Esfera Poder
        if(sessao.getAttribute("colecaoEsferaPoder") == null){
        	Collection colecaoEsferaPoder = new ArrayList();
        	FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
        	filtroEsferaPoder.setCampoOrderBy(FiltroEsferaPoder.DESCRICAO);
        	colecaoEsferaPoder = fachada.pesquisar(filtroEsferaPoder, EsferaPoder.class.getName());
        	sessao.setAttribute("colecaoEsferaPoder", colecaoEsferaPoder);
        }
        Collection colecaoUN = (Collection)sessao.getAttribute("colecaoUnidadeNegocio");
        if ( colecaoUN == null || colecaoUN.isEmpty()  ) {
        
        	FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();	
            filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME_ABREVIADO,FiltroUnidadeNegocio.NOME);
            
        	filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
                    FiltroUnidadeNegocio.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            //Retorna Localidade_Classe
            Collection colecaoUnidadeNegocio = 
            	this.getFachada().pesquisar(filtroUnidadeNegocio,
                    UnidadeNegocio.class.getName());

            if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
                //Nenhum registro na tabela gerencia_regional foi
                // encontrada
                throw new ActionServletException(
                        "atencao.pesquisa.nenhum_registro_tabela", null,
                        "Unidade de Negócio");
            } else {
            	
            	UnidadeNegocio unidadeNegocio = null;
    			Iterator iterator = colecaoUnidadeNegocio.iterator();
    		
    			while (iterator.hasNext()) {
    				unidadeNegocio = (UnidadeNegocio) iterator.next();
    				
    				String descUnidadeNegocio = unidadeNegocio.getNomeAbreviado() + "-" + unidadeNegocio.getNome();
    				unidadeNegocio.setNome(descUnidadeNegocio);
    				
    			}
            	
                sessao.setAttribute("colecaoUnidadeNegocio",
                		colecaoUnidadeNegocio);
            }
        }
        
        //Pesquisar Responsavel
        if((form != null && form.getResponsavelFiltro() != null && !form.getResponsavelFiltro().equals(""))
        		&&(form.getNomeResponsavel() == null || form.getNomeResponsavel().equals(""))){
        	
        	pesquisarResponsavel(form, fachada, httpServletRequest);
    	}
        
      //this.pesquisarUnidadeNegocio(httpServletRequest,form);
		this.pesquisarGrupoFaturamento(httpServletRequest);
		
		//CRC1550 - adicionado por Vivianne Sousa - 16/06/2010 - analista:Ana Cristina
		this.pesquisarGerenciaRegional(httpServletRequest);
        
        return retorno;

    }
    
    private void pesquisarLocalidade(RelatorioContasCanceladasRetificadasActionForm form, 
    		Fachada fachada,
			HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		localidadeID = (String) form.getLocalidadeFiltro();
		String nomeLocalidade = form.getNomeLocalidade();

		if (localidadeID != null && !localidadeID.equals("")
				&& (nomeLocalidade == null || nomeLocalidade.equals(""))) {

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
				// formulário
				form.setLocalidadeFiltro("");
				form.setNomeLocalidade("localidade inexistente");
				httpServletRequest.setAttribute(
						"codigoLocalidadeNaoEncontrada", "exception");
				httpServletRequest
						.setAttribute("nomeCampo", "localidadeFiltro");
				// httpServletRequest.setAttribute("corLocalidadeOrigem","exception");
			} else {
				Localidade objetoLocalidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				form.setLocalidadeFiltro(String
						.valueOf(objetoLocalidade.getId()));
				form.setNomeLocalidade(objetoLocalidade
						.getDescricao());
			}
		}
	}
    
    private void pesquisarResponsavel(RelatorioContasCanceladasRetificadasActionForm form, 
    		Fachada fachada,
			HttpServletRequest httpServletRequest) {

		FiltroUsuario filtroUsuario = new FiltroUsuario();

		responsavelID = (String) form.getResponsavelFiltro();
		String nomeResponsavel = form.getNomeResponsavel();

		if (responsavelID != null && 
			!responsavelID.equals("") && 
			(nomeResponsavel == null || nomeResponsavel.equals(""))) {

			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.LOGIN, responsavelID));

			// Retorna o usuário
			colecaoPesquisa = 
				this.getFachada().pesquisar(filtroUsuario,
					Usuario.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {

				form.setResponsavelFiltro("");
				form.setNomeResponsavel("Responsável inexistente");
				
				httpServletRequest.setAttribute("codigoResponsavelNaoEncontrada", "exception");
				httpServletRequest.setAttribute("nomeCampo", "responsavelFiltro");
			} else {
				
				Usuario objetoUsuario = (Usuario) Util.retonarObjetoDeColecao(colecaoPesquisa);
				
				form.setResponsavelFiltro(objetoUsuario.getLogin());
				form.setNomeResponsavel(objetoUsuario.getNomeUsuario());
			}
		}
	}
    
    private void pesquisarGrupoFaturamento(HttpServletRequest httpServletRequest) {

		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();

		filtroFaturamentoGrupo.setConsultaSemLimites(true);

		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
				FiltroFaturamentoGrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO ));
		filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO);

		Collection colecaoFaturamentoGrupo = this.getFachada().pesquisar(
				filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

		if (colecaoFaturamentoGrupo == null
				|| colecaoFaturamentoGrupo.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Grupo Faturamento");
		} else {
			httpServletRequest.setAttribute("colecaoGrupo",
					colecaoFaturamentoGrupo);
		}
	}
    
    private void pesquisarGerenciaRegional(HttpServletRequest httpServletRequest) {

    	FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
    	filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
    			FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO ));
    	filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
    	Collection<GerenciaRegional> colecaoGerenciaRegional = this.getFachada().pesquisar(
    			filtroGerenciaRegional, GerenciaRegional.class.getName());

    	if (colecaoGerenciaRegional == null || colecaoGerenciaRegional.isEmpty()) {
    		
    		throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Gerência Regional");
    	}
    	
    	httpServletRequest.setAttribute("colecaoGerenciaRegional",colecaoGerenciaRegional);
    	
//    	sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);

	}
    
}
