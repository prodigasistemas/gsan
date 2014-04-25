package gcom.gui.gerencial.cobranca;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
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
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * @author Sávio Luiz
 * @date 22/05/2007
 */
public class ExibirInformarDadosGeracaoResumoAcaoConsultaAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirInformarDadosGeracaoResumoAcaoConsulta");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Fachada fachada = Fachada.getInstancia();
        
        InformarDadosGeracaoResumoAcaoConsultaActionForm informarDadosGeracaoResumoAcaoConsultaActionForm = 
        (InformarDadosGeracaoResumoAcaoConsultaActionForm) actionForm;
        
        String limparForm = httpServletRequest.getParameter("limparForm");
        
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
        
        //UNIDADE DE NEGÓCIO        
    	if (sessao.getAttribute("collUnidadeNegocio") == null){
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroUnidadeNegocio.setCampoOrderBy("nome");
			Collection collUnidadeNegocio = Fachada.getInstancia().pesquisar(filtroUnidadeNegocio , UnidadeNegocio.class.getName());
			sessao.setAttribute("collUnidadeNegocio", collUnidadeNegocio);
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
        
        //EMPRESA
        if (sessao.getAttribute("colecaoEmpresa") == null){
        	
        	FiltroEmpresa filtroEmpresa = new FiltroEmpresa(FiltroEsferaPoder.DESCRICAO);
        	filtroEmpresa.setConsultaSemLimites(true);
        	
        	filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEsferaPoder.INDICADOR_USO,
        	ConstantesSistema.INDICADOR_USO_ATIVO));
        	
        	Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa, 
        	Empresa.class.getName());
        	
        	if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()){
        		throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "EMPRESA");
        	}
        	
        	sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
        }
        
        
        String pesquisarEloPolo = httpServletRequest.getParameter("pesquisarEloPolo");
        String pesquisarLocalidade = httpServletRequest.getParameter("pesquisarLocalidade");
        String pesquisarSetorComercial = httpServletRequest.getParameter("pesquisarSetorComercial");
        String pesquisarQuadra = httpServletRequest.getParameter("pesquisarQuadra");
        
        Collection colecaoPesquisa = null;
        
        if (pesquisarEloPolo != null && !pesquisarEloPolo.equals("") && 
        	informarDadosGeracaoResumoAcaoConsultaActionForm.getEloPolo() != null &&
        	!informarDadosGeracaoResumoAcaoConsultaActionForm.getEloPolo().equals("")){
        	
        	FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
        	filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");
        	
            filtroLocalidade.adicionarParametro(new ParametroSimples(
            FiltroLocalidade.ID, informarDadosGeracaoResumoAcaoConsultaActionForm.getEloPolo()));

            filtroLocalidade.adicionarParametro(new ParametroSimples(
            FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

            colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
            Localidade.class.getName());
            
            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()){
            	
            	informarDadosGeracaoResumoAcaoConsultaActionForm.setEloPolo("");
            	informarDadosGeracaoResumoAcaoConsultaActionForm.setDescricaoEloPolo("Elo Pólo inexistente");
            	httpServletRequest.setAttribute("nomeCampo", "eloPolo");
            	httpServletRequest.setAttribute("corEloPolo", "exception");
            	
            }
            else{
            	Localidade eloPlo = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
            	
            	if (eloPlo.getLocalidade() == null){
            		
            		informarDadosGeracaoResumoAcaoConsultaActionForm.setEloPolo("");
                	informarDadosGeracaoResumoAcaoConsultaActionForm.setDescricaoEloPolo("Código informado não é um Elo Pólo");
                	httpServletRequest.setAttribute("nomeCampo", "eloPolo");
                	httpServletRequest.setAttribute("corEloPolo", "exception");
                	
            	}
            	else if (!eloPlo.getLocalidade().getId().equals(
            			 new Integer(informarDadosGeracaoResumoAcaoConsultaActionForm.getEloPolo()))){
            		
            		informarDadosGeracaoResumoAcaoConsultaActionForm.setEloPolo("");
                	informarDadosGeracaoResumoAcaoConsultaActionForm.setDescricaoEloPolo("Código informado não é um Elo Pólo");
                	httpServletRequest.setAttribute("nomeCampo", "eloPolo");
                	httpServletRequest.setAttribute("corEloPolo", "exception");
                	
            	}
            	else{
            		
            		informarDadosGeracaoResumoAcaoConsultaActionForm.setEloPolo(String.valueOf(eloPlo.getId()));
                	informarDadosGeracaoResumoAcaoConsultaActionForm.setDescricaoEloPolo(eloPlo.getDescricao());
                	httpServletRequest.setAttribute("nomeCampo", "perfilImovel");
            	}
            }
        }
        
        
        if (pesquisarLocalidade != null && !pesquisarLocalidade.equals("") && 
            informarDadosGeracaoResumoAcaoConsultaActionForm.getLocalidade() != null &&
            !informarDadosGeracaoResumoAcaoConsultaActionForm.getLocalidade().equals("")){
            	
            	FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
            	
                filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.ID, informarDadosGeracaoResumoAcaoConsultaActionForm.getLocalidade()));

                filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

                colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
                Localidade.class.getName());
                
                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()){
                	
                	informarDadosGeracaoResumoAcaoConsultaActionForm.setLocalidade("");
                	informarDadosGeracaoResumoAcaoConsultaActionForm.setDescricaoLocalidade("Localidade inexistente");
                	httpServletRequest.setAttribute("nomeCampo", "localidade");
                	httpServletRequest.setAttribute("corLocalidade", "exception");
                	
                }
                else{
                	Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
                	
                	informarDadosGeracaoResumoAcaoConsultaActionForm.setLocalidade(String.valueOf(localidade.getId()));
                    informarDadosGeracaoResumoAcaoConsultaActionForm.setDescricaoLocalidade(localidade.getDescricao());
                    httpServletRequest.setAttribute("nomeCampo", "setorComercial");
                }
        }
        
        
        if (pesquisarSetorComercial != null && !pesquisarSetorComercial.equals("") && 
            informarDadosGeracaoResumoAcaoConsultaActionForm.getSetorComercial() != null &&
            !informarDadosGeracaoResumoAcaoConsultaActionForm.getSetorComercial().equals("")){
                	
                	FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
                	
                	filtroSetorComercial.adicionarParametro(new ParametroSimples(
                	FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, 
                	informarDadosGeracaoResumoAcaoConsultaActionForm.getSetorComercial()));

                	filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.ID_LOCALIDADE, 
                    informarDadosGeracaoResumoAcaoConsultaActionForm.getLocalidade()));
                	
                	filtroSetorComercial.adicionarParametro(new ParametroSimples(
                	FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

                    colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
                    SetorComercial.class.getName());
                    
                    if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()){
                    	
                    	informarDadosGeracaoResumoAcaoConsultaActionForm.setSetorComercial("");
                    	informarDadosGeracaoResumoAcaoConsultaActionForm.setDescricaoSetorComercial("Setor Comercial inexistente");
                    	httpServletRequest.setAttribute("nomeCampo", "setorComercial");
                    	httpServletRequest.setAttribute("corSetorComercial", "exception");
                    	
                    }
                    else{
                    	SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
                    	
                    	informarDadosGeracaoResumoAcaoConsultaActionForm.setIdSetorComercial(String.valueOf(setorComercial.getId()));
                    	informarDadosGeracaoResumoAcaoConsultaActionForm.setSetorComercial(String.valueOf(setorComercial.getCodigo()));
                        informarDadosGeracaoResumoAcaoConsultaActionForm.setDescricaoSetorComercial(setorComercial.getDescricao());
                        httpServletRequest.setAttribute("nomeCampo", "perfilImovel");
                    }
            }

        
	        if (pesquisarQuadra != null && !pesquisarQuadra.equals("") && 
	                informarDadosGeracaoResumoAcaoConsultaActionForm.getQuadra() != null &&
	                !informarDadosGeracaoResumoAcaoConsultaActionForm.getQuadra().equals("")){
	                    	
	        	FiltroQuadra filtroQuadra = new FiltroQuadra();
	            //filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");
	                    	
	            filtroQuadra.adicionarParametro(new ParametroSimples(
	    	    FiltroQuadra.ID_SETORCOMERCIAL, 
	    	    informarDadosGeracaoResumoAcaoConsultaActionForm.getIdSetorComercial()));
	            
	            filtroQuadra.adicionarParametro(new ParametroSimples(
	            FiltroQuadra.NUMERO_QUADRA, 
	            informarDadosGeracaoResumoAcaoConsultaActionForm.getQuadra()));
	
	            filtroQuadra.adicionarParametro(new ParametroSimples(
	            FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
	
	            colecaoPesquisa = fachada.pesquisar(filtroQuadra,
	            Quadra.class.getName());
	                        
	            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()){
	                        	
	            	informarDadosGeracaoResumoAcaoConsultaActionForm.setQuadra("");
	            	httpServletRequest.setAttribute("nomeCampo", "quadra");
	            	httpServletRequest.setAttribute("msgQuadra", "QUADRA INEXISTENTE");
	                        	
	            }
	            else{
	            	Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
	            		
	            	informarDadosGeracaoResumoAcaoConsultaActionForm.setQuadra(String.valueOf(quadra.getNumeroQuadra()));
	            	httpServletRequest.setAttribute("nomeCampo", "perfilImovel");
	            }
	        }
	        
	        
        
	        if (limparForm != null && !limparForm.equals("")){
	        	
	        	informarDadosGeracaoResumoAcaoConsultaActionForm.setMesAnoFaturamento("");
	        	informarDadosGeracaoResumoAcaoConsultaActionForm.setGrupoCobranca(null);
	        	informarDadosGeracaoResumoAcaoConsultaActionForm.setGerencialRegional(null);
	        	informarDadosGeracaoResumoAcaoConsultaActionForm.setUnidadeNegocio(null);
	        	informarDadosGeracaoResumoAcaoConsultaActionForm.setEloPolo("");
	        	informarDadosGeracaoResumoAcaoConsultaActionForm.setDescricaoEloPolo("");
	        	informarDadosGeracaoResumoAcaoConsultaActionForm.setLocalidade("");
	        	informarDadosGeracaoResumoAcaoConsultaActionForm.setDescricaoLocalidade("");
	        	informarDadosGeracaoResumoAcaoConsultaActionForm.setSetorComercial("");
	        	informarDadosGeracaoResumoAcaoConsultaActionForm.setDescricaoSetorComercial("");
	        	informarDadosGeracaoResumoAcaoConsultaActionForm.setQuadra("");
	        	informarDadosGeracaoResumoAcaoConsultaActionForm.setPerfilImovel(null);
	        	informarDadosGeracaoResumoAcaoConsultaActionForm.setSituacaoLigacaoAgua(null);
	        	informarDadosGeracaoResumoAcaoConsultaActionForm.setSituacaoLigacaoEsgoto(null);
	        	informarDadosGeracaoResumoAcaoConsultaActionForm.setCategoria(null);
	        	informarDadosGeracaoResumoAcaoConsultaActionForm.setEsferaPoder(null);
	        	informarDadosGeracaoResumoAcaoConsultaActionForm.setEmpresa(null);
	        	
	        	informarDadosGeracaoResumoAcaoConsultaActionForm.setTipoImpressao("");
	        	
	        }
        
	        //Pesquisando Sistema Parâmetros
            if (sessao.getAttribute("sistemaParametro") == null) {
                SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
                sessao.setAttribute("sistemaParametro", sistemaParametro);
            }
        
	        return retorno;
    }

}
