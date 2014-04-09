package gcom.gui.gerencial;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesInterfaceGSAN;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os parâmetros para informar
 * os dados para geração de relatório/consulta 
 *
 * @author Raphael Rossiter
 * @date 23/02/2006
 */
public class ExibirInformarDadosGeracaoRelatorioConsultaAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirInformarDadosGeracaoRelatorioConsulta");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Fachada fachada = Fachada.getInstancia();
        
        /*
         * Informa os códigos das opções de totalização que irão direcionar o caso de uso para um JSP
         */
        httpServletRequest.setAttribute("jsp", "1,6,7,10,13,16,18");
        
        
        /**
         * na chamada do menu é passado um parametro com o tipo do resumo
         * para o informar saber que caso de uso será chamado apartir dele.   
         */
        String tipoResumo = httpServletRequest.getParameter("tipoResumo");
        
        String grupo = httpServletRequest.getParameter("grupo");
        
        if (tipoResumo != null && !tipoResumo.equals("")){
        	sessao.setAttribute("tipoResumo", tipoResumo);
        }
        
        if (grupo != null && !grupo.equals("")){
        	sessao.setAttribute("cobranca", grupo);
        }
        
        
        InformarDadosGeracaoRelatorioConsultaActionForm informarDadosGeracaoRelatorioConsultaActionForm = 
        (InformarDadosGeracaoRelatorioConsultaActionForm) actionForm;
        
        String analiseFaturamento = httpServletRequest.getParameter("analiseFaturamento");
        String limparForm = httpServletRequest.getParameter("limparForm");
        
        
        //Carregango as coleções que servirão como opção de escolha para o usuário
        
        //GRUPO DE FATURAMENTO
        if (sessao.getAttribute("colecaoFaturamentoGrupo") == null){
        	
        	FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo(FiltroFaturamentoGrupo.DESCRICAO_ABREVIADA);
        	filtroFaturamentoGrupo.setConsultaSemLimites(true);
        	
        	filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.INDICADOR_USO,
        	ConstantesSistema.INDICADOR_USO_ATIVO));
        	
        	Collection colecaoGrupoFaturamento = fachada.pesquisar(filtroFaturamentoGrupo, 
        	FaturamentoGrupo.class.getName());
        	
        	if (colecaoGrupoFaturamento == null || colecaoGrupoFaturamento.isEmpty()){
        		throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "FATURAMENTO_GRUPO");
        	}
        	
        	sessao.setAttribute("colecaoFaturamentoGrupo", colecaoGrupoFaturamento);
        }
        
        //GRUPO DE COBRANCA
        if (sessao.getAttribute("colecaoGrupoCobranca") == null){
        	
        	FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo(FiltroCobrancaGrupo.DESCRICAO_ABREVIADA);
        	filtroCobrancaGrupo.setConsultaSemLimites(true);
        	
        	filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(FiltroCobrancaGrupo.INDICADOR_USO,
        	ConstantesSistema.INDICADOR_USO_ATIVO));
        	
        	Collection colecaoGrupoCobranca = fachada.pesquisar(filtroCobrancaGrupo, 
        	CobrancaGrupo.class.getName());
        	
        	if (colecaoGrupoCobranca == null || colecaoGrupoCobranca.isEmpty()){
        		throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "COBRANCA_GRUPO");
        	}
        	
        	sessao.setAttribute("colecaoGrupoCobranca", colecaoGrupoCobranca);
        }        
        
        //GERÊNCIA REGIONAL
        if (sessao.getAttribute("colecaoGerenciaRegional") == null){
        	
        	FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional(FiltroGerenciaRegional.NOME);
        	filtroGerenciaRegional.setConsultaSemLimites(true);
        	
        	filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO,
        	ConstantesSistema.INDICADOR_USO_ATIVO));
        	
        	Collection colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional, 
        	GerenciaRegional.class.getName());
        	
        	if (colecaoGerenciaRegional == null || colecaoGerenciaRegional.isEmpty()){
        		throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "GERENCIA_REGIONAL");
        	}
        	
        	sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
        }
        
        //UNIDADE NEGOCIO
        if (sessao.getAttribute("colecaoUnidadeNegocio") == null){
        	
        	FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
    		filtroUnidadeNegocio.setConsultaSemLimites(true);
    		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);		
    		filtroUnidadeNegocio.adicionarParametro(
    				new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, 
    				ConstantesSistema.INDICADOR_USO_ATIVO));		

    		Collection<UnidadeNegocio> colecaoUnidadeNegocio = 
    			this.getFachada().pesquisar(filtroUnidadeNegocio,UnidadeNegocio.class.getName());

    		if ( Util.isVazioOrNulo(colecaoUnidadeNegocio)) {
    			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_NAO_CADASTRADO, 
    					ConstantesInterfaceGSAN.LABEL_GSAN_UNIDADE_NEGOCIO);
    		}
    		sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
        }
        
        //PERFIL IMÓVEL
        if (sessao.getAttribute("colecaoImovelPerfil") == null){
        	
        	FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil(FiltroImovelPerfil.DESCRICAO);
        	filtroImovelPerfil.setConsultaSemLimites(true);
        	
        	filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO,
        	ConstantesSistema.INDICADOR_USO_ATIVO));
        	
        	Collection colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil, 
        	ImovelPerfil.class.getName());
        	
        	if (colecaoImovelPerfil == null || colecaoImovelPerfil.isEmpty()){
        		throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "IMOVEL_PERFIL");
        	}
        	
        	sessao.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
        }
        
        //SITUAÇÃO LIGAÇÃO ÁGUA
        if (sessao.getAttribute("colecaoLigacaoAguaSituacao") == null){
        	
        	FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao(FiltroLigacaoAguaSituacao.DESCRICAO);
        	filtroLigacaoAguaSituacao.setConsultaSemLimites(true);
        	
        	filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.INDICADOR_USO,
        	ConstantesSistema.INDICADOR_USO_ATIVO));
        	
        	Collection colecaoLigacaoAguaSituacao = fachada.pesquisar(filtroLigacaoAguaSituacao, 
        	LigacaoAguaSituacao.class.getName());
        	
        	if (colecaoLigacaoAguaSituacao == null || colecaoLigacaoAguaSituacao.isEmpty()){
        		throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "LIGACAO_AGUA_SITUACAO");
        	}
        	
        	sessao.setAttribute("colecaoLigacaoAguaSituacao", colecaoLigacaoAguaSituacao);
        }
        
        //SITUAÇÃO LIGAÇÃO ESGOTO
        if (sessao.getAttribute("colecaoLigacaoEsgotoSituacao") == null){
        	
        	FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao(FiltroLigacaoEsgotoSituacao.DESCRICAO);
        	filtroLigacaoEsgotoSituacao.setConsultaSemLimites(true);
        	
        	filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.INDICADOR_USO,
        	ConstantesSistema.INDICADOR_USO_ATIVO));
        	
        	Collection colecaoLigacaoEsgotoSituacao = fachada.pesquisar(filtroLigacaoEsgotoSituacao, 
        	LigacaoEsgotoSituacao.class.getName());
        	
        	if (colecaoLigacaoEsgotoSituacao == null || colecaoLigacaoEsgotoSituacao.isEmpty()){
        		throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "LIGACAO_ESGOTO_SITUACAO");
        	}
        	
        	sessao.setAttribute("colecaoLigacaoEsgotoSituacao", colecaoLigacaoEsgotoSituacao);
        }
        
        //CATEGORIA
        if (sessao.getAttribute("colecaoCategoria") == null){
        	
        	FiltroCategoria filtroCategoria = new FiltroCategoria(FiltroCategoria.DESCRICAO);
        	filtroCategoria.setConsultaSemLimites(true);
        	
        	filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.INDICADOR_USO,
        	ConstantesSistema.INDICADOR_USO_ATIVO));
        	
        	Collection colecaoCategoria = fachada.pesquisar(filtroCategoria, 
        	Categoria.class.getName());
        	
        	if (colecaoCategoria == null || colecaoCategoria.isEmpty()){
        		throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "CATEGORIA");
        	}
        	
        	sessao.setAttribute("colecaoCategoria", colecaoCategoria);
        }
        
        //ESFERA PODER
        if (sessao.getAttribute("colecaoEsferaPoder") == null){
        	
        	FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder(FiltroEsferaPoder.DESCRICAO);
        	filtroEsferaPoder.setConsultaSemLimites(true);
        	
        	filtroEsferaPoder.adicionarParametro(new ParametroSimples(FiltroEsferaPoder.INDICADOR_USO,
        	ConstantesSistema.INDICADOR_USO_ATIVO));
        	
        	Collection colecaoEsferaPoder = fachada.pesquisar(filtroEsferaPoder, 
        	EsferaPoder.class.getName());
        	
        	if (colecaoEsferaPoder == null || colecaoEsferaPoder.isEmpty()){
        		throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "ESFERA_PODER");
        	}
        	
        	sessao.setAttribute("colecaoEsferaPoder", colecaoEsferaPoder);
        }
        
        String pesquisarEloPolo = httpServletRequest.getParameter("pesquisarEloPolo");
        String pesquisarLocalidade = httpServletRequest.getParameter("pesquisarLocalidade");
        String pesquisarSetorComercial = httpServletRequest.getParameter("pesquisarSetorComercial");
        String pesquisarQuadra = httpServletRequest.getParameter("pesquisarQuadra");
        String pesquisarMunicipio = httpServletRequest.getParameter("pesquisarMunicipio");
        
        Collection colecaoPesquisa = null;
        
        if (pesquisarEloPolo != null && !pesquisarEloPolo.equals("") && 
        	informarDadosGeracaoRelatorioConsultaActionForm.getEloPolo() != null &&
        	!informarDadosGeracaoRelatorioConsultaActionForm.getEloPolo().equals("")){
        	
        	FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
        	filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");
        	
            filtroLocalidade.adicionarParametro(new ParametroSimples(
            FiltroLocalidade.ID, informarDadosGeracaoRelatorioConsultaActionForm.getEloPolo()));

            filtroLocalidade.adicionarParametro(new ParametroSimples(
            FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

            colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
            Localidade.class.getName());
            
            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()){
            	
            	informarDadosGeracaoRelatorioConsultaActionForm.setEloPolo("");
            	informarDadosGeracaoRelatorioConsultaActionForm.setDescricaoEloPolo("Elo Pólo inexistente");
            	httpServletRequest.setAttribute("nomeCampo", "eloPolo");
            	httpServletRequest.setAttribute("corEloPolo", "exception");
            	
            }
            else{
            	Localidade eloPlo = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
            	
            	if (eloPlo.getLocalidade() == null){
            		
            		informarDadosGeracaoRelatorioConsultaActionForm.setEloPolo("");
                	informarDadosGeracaoRelatorioConsultaActionForm.setDescricaoEloPolo("Código informado não é um Elo Pólo");
                	httpServletRequest.setAttribute("nomeCampo", "eloPolo");
                	httpServletRequest.setAttribute("corEloPolo", "exception");
                	
            	}
            	else if (!eloPlo.getLocalidade().getId().equals(
            			 new Integer(informarDadosGeracaoRelatorioConsultaActionForm.getEloPolo()))){
            		
            		informarDadosGeracaoRelatorioConsultaActionForm.setEloPolo("");
                	informarDadosGeracaoRelatorioConsultaActionForm.setDescricaoEloPolo("Código informado não é um Elo Pólo");
                	httpServletRequest.setAttribute("nomeCampo", "eloPolo");
                	httpServletRequest.setAttribute("corEloPolo", "exception");
                	
            	}
            	else{
            		
            		informarDadosGeracaoRelatorioConsultaActionForm.setEloPolo(String.valueOf(eloPlo.getId()));
                	informarDadosGeracaoRelatorioConsultaActionForm.setDescricaoEloPolo(eloPlo.getDescricao());
                	httpServletRequest.setAttribute("nomeCampo", "perfilImovel");
            	}
            }
        }
        
        
        if (pesquisarLocalidade != null && !pesquisarLocalidade.equals("") && 
            informarDadosGeracaoRelatorioConsultaActionForm.getLocalidade() != null &&
            !informarDadosGeracaoRelatorioConsultaActionForm.getLocalidade().equals("")){
            	
            	FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
            	
                filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.ID, informarDadosGeracaoRelatorioConsultaActionForm.getLocalidade()));

                filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

                colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
                Localidade.class.getName());
                
                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()){
                	
                	informarDadosGeracaoRelatorioConsultaActionForm.setLocalidade("");
                	informarDadosGeracaoRelatorioConsultaActionForm.setDescricaoLocalidade("Localidade inexistente");
                	httpServletRequest.setAttribute("nomeCampo", "localidade");
                	httpServletRequest.setAttribute("corLocalidade", "exception");
                	
                }
                else{
                	Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
                	
                	informarDadosGeracaoRelatorioConsultaActionForm.setLocalidade(String.valueOf(localidade.getId()));
                    informarDadosGeracaoRelatorioConsultaActionForm.setDescricaoLocalidade(localidade.getDescricao());
                    httpServletRequest.setAttribute("nomeCampo", "setorComercial");
                }
        }
        
        
        if (pesquisarSetorComercial != null && !pesquisarSetorComercial.equals("") && 
            informarDadosGeracaoRelatorioConsultaActionForm.getSetorComercial() != null &&
            !informarDadosGeracaoRelatorioConsultaActionForm.getSetorComercial().equals("")){
                	
                	FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
                	
                	filtroSetorComercial.adicionarParametro(new ParametroSimples(
                	FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, 
                	informarDadosGeracaoRelatorioConsultaActionForm.getSetorComercial()));

                	filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.ID_LOCALIDADE, 
                    informarDadosGeracaoRelatorioConsultaActionForm.getLocalidade()));
                	
                	filtroSetorComercial.adicionarParametro(new ParametroSimples(
                	FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

                    colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
                    SetorComercial.class.getName());
                    
                    if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()){
                    	
                    	informarDadosGeracaoRelatorioConsultaActionForm.setSetorComercial("");
                    	informarDadosGeracaoRelatorioConsultaActionForm.setDescricaoSetorComercial("Setor Comercial inexistente");
                    	httpServletRequest.setAttribute("nomeCampo", "setorComercial");
                    	httpServletRequest.setAttribute("corSetorComercial", "exception");
                    	
                    }
                    else{
                    	SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
                    	
                    	informarDadosGeracaoRelatorioConsultaActionForm.setIdSetorComercial(String.valueOf(setorComercial.getId()));
                    	informarDadosGeracaoRelatorioConsultaActionForm.setSetorComercial(String.valueOf(setorComercial.getCodigo()));
                        informarDadosGeracaoRelatorioConsultaActionForm.setDescricaoSetorComercial(setorComercial.getDescricao());
                        httpServletRequest.setAttribute("nomeCampo", "perfilImovel");
                    }
            }

        
	        if (pesquisarQuadra != null && !pesquisarQuadra.equals("") && 
	                informarDadosGeracaoRelatorioConsultaActionForm.getQuadra() != null &&
	                !informarDadosGeracaoRelatorioConsultaActionForm.getQuadra().equals("")){
	                    	
	        	FiltroQuadra filtroQuadra = new FiltroQuadra();
	            filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");
	                    	
	            filtroQuadra.adicionarParametro(new ParametroSimples(
	    	    FiltroQuadra.ID_SETORCOMERCIAL, 
	    	    informarDadosGeracaoRelatorioConsultaActionForm.getIdSetorComercial()));
	            
	            filtroQuadra.adicionarParametro(new ParametroSimples(
	            FiltroQuadra.NUMERO_QUADRA, 
	            informarDadosGeracaoRelatorioConsultaActionForm.getQuadra()));
	
	            filtroQuadra.adicionarParametro(new ParametroSimples(
	            FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
	
	            colecaoPesquisa = fachada.pesquisar(filtroQuadra,
	            Quadra.class.getName());
	                        
	            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()){
	                        	
	            	informarDadosGeracaoRelatorioConsultaActionForm.setQuadra("");
	            	httpServletRequest.setAttribute("nomeCampo", "quadra");
	            	httpServletRequest.setAttribute("msgQuadra", "QUADRA INEXISTENTE");
	                        	
	            }
	            else{
	            	Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
	            		
	            	informarDadosGeracaoRelatorioConsultaActionForm.setQuadra(String.valueOf(quadra.getNumeroQuadra()));
	            	httpServletRequest.setAttribute("nomeCampo", "perfilImovel");
	            }
	        }
	        
	        //Pesquisar Município
	        if (pesquisarMunicipio != null && !pesquisarMunicipio.equals("") && 
	                informarDadosGeracaoRelatorioConsultaActionForm.getMunicipio() != null &&
	                !informarDadosGeracaoRelatorioConsultaActionForm.getMunicipio().equals("")){
	        	
	        	FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
            	
                filtroMunicipio.adicionarParametro(new ParametroSimples(
                FiltroMunicipio.ID, informarDadosGeracaoRelatorioConsultaActionForm.getMunicipio()));

                colecaoPesquisa = fachada.pesquisar(filtroMunicipio,
                Municipio.class.getName());
                
                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()){
                	
                	informarDadosGeracaoRelatorioConsultaActionForm.setMunicipio("");
                	informarDadosGeracaoRelatorioConsultaActionForm.setDescricaoMunicipio("Município inexistente");
                	httpServletRequest.setAttribute("nomeCampo", "municipio");
                	httpServletRequest.setAttribute("corMunicipio", "exception");
                	
                }
                else{
                	Municipio municipio = (Municipio) Util.retonarObjetoDeColecao(colecaoPesquisa);
                	
                	informarDadosGeracaoRelatorioConsultaActionForm.setMunicipio(String.valueOf(municipio.getId()));
                    informarDadosGeracaoRelatorioConsultaActionForm.setDescricaoMunicipio(municipio.getNome());
                    httpServletRequest.setAttribute("nomeCampo", "municipio");
                }
	        }
	        
	        if (analiseFaturamento != null && !analiseFaturamento.equals("")){
	        	sessao.setAttribute("analiseFaturamento", analiseFaturamento);
	        }
	        
	        //Pesquisar Rota
	        if (informarDadosGeracaoRelatorioConsultaActionForm.getIdRota() != null && !informarDadosGeracaoRelatorioConsultaActionForm.getIdRota().trim().equals("")) {
				FiltroRota filtroRota = new FiltroRota();
				filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, informarDadosGeracaoRelatorioConsultaActionForm.getIdRota()));
				filtroRota.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
				
				Collection colecaoRota = fachada.pesquisar(filtroRota, Rota.class.getName());
				
				if (colecaoRota != null && !colecaoRota.isEmpty()) {
					Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRota);
					informarDadosGeracaoRelatorioConsultaActionForm.setCodigoRota(rota.getCodigo().toString());
					
					informarDadosGeracaoRelatorioConsultaActionForm.setGrupoFaturamento(rota.getFaturamentoGrupo().getId() + "");
					
					sessao.setAttribute("faturamentoGrupo", rota.getFaturamentoGrupo().getId());
				}
				
			}
	        
	        if (limparForm != null && !limparForm.equals("")){
	        	
	        	informarDadosGeracaoRelatorioConsultaActionForm.setMesAnoFaturamento("");
	        	informarDadosGeracaoRelatorioConsultaActionForm.setOpcaoTotalizacao("");
	        	informarDadosGeracaoRelatorioConsultaActionForm.setGrupoFaturamento("");
	        	informarDadosGeracaoRelatorioConsultaActionForm.setGrupoCobranca("");
	        	informarDadosGeracaoRelatorioConsultaActionForm.setGerencialRegional("");
	        	informarDadosGeracaoRelatorioConsultaActionForm.setEloPolo("");
	        	informarDadosGeracaoRelatorioConsultaActionForm.setDescricaoEloPolo("");
	        	informarDadosGeracaoRelatorioConsultaActionForm.setLocalidade("");
	        	informarDadosGeracaoRelatorioConsultaActionForm.setDescricaoLocalidade("");
	        	informarDadosGeracaoRelatorioConsultaActionForm.setSetorComercial("");
	        	informarDadosGeracaoRelatorioConsultaActionForm.setDescricaoSetorComercial("");
	        	informarDadosGeracaoRelatorioConsultaActionForm.setQuadra("");
	        	informarDadosGeracaoRelatorioConsultaActionForm.setPerfilImovel(null);
	        	informarDadosGeracaoRelatorioConsultaActionForm.setSituacaoLigacaoAgua(null);
	        	informarDadosGeracaoRelatorioConsultaActionForm.setSituacaoLigacaoEsgoto(null);
	        	informarDadosGeracaoRelatorioConsultaActionForm.setCategoria(null);
	        	informarDadosGeracaoRelatorioConsultaActionForm.setEsferaPoder(null);
	        	informarDadosGeracaoRelatorioConsultaActionForm.setMunicipio("");
	        	informarDadosGeracaoRelatorioConsultaActionForm.setDescricaoMunicipio("");
	        	informarDadosGeracaoRelatorioConsultaActionForm.setIdRota("");
	        	informarDadosGeracaoRelatorioConsultaActionForm.setCodigoRota("");
	        }
	        return retorno;
    }

}
