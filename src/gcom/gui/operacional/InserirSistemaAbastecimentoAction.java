package gcom.gui.operacional;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.operacional.FiltroFonteCaptacao;
import gcom.operacional.FiltroTipoCaptacao;
import gcom.operacional.FonteCaptacao;
import gcom.operacional.SistemaAbastecimento;
import gcom.operacional.TipoCaptacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirSistemaAbastecimentoAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

	        //Seta o retorno
	        ActionForward retorno = actionMapping.findForward("telaSucesso");
	
	        InserirSistemaAbastecimentoActionForm form = (InserirSistemaAbastecimentoActionForm) actionForm;
	
	        HttpSession sessao = httpServletRequest.getSession(false);
	        
	        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
	
	        //------------ REGISTRAR TRANSAÇÃO ----------------
	        RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_SISTEMA_ABASTECIMENTO_INSERIR,
					new UsuarioAcaoUsuarioHelper(usuario,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
	        
	        Operacao operacao = new Operacao();
	        operacao.setId(Operacao.OPERACAO_SISTEMA_ABASTECIMENTO_INSERIR);
	
	        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
	        operacaoEfetuada.setOperacao(operacao);
	        //------------ REGISTRAR TRANSAÇÃO ----------------
	
	        
	        String descricao = form.getDescricao();
	        String descricaoAbreviada = form.getDescricaoAbreviada();
	        String fonteCaptacaoId = form.getFonteCaptacao();
	        String tipoCaptacaoId = form.getTipoCaptacao();
	        
	        if(descricao == null || descricao.equals("")){
	        	
	        	//Descrição não informada
	        	throw new ActionServletException("atencao.descricao_sistema_abastecimento_nao_informado");
	        } else if(descricaoAbreviada == null || descricaoAbreviada.equals("")){
	        
	        	//Descrição Abreviada não informada
	        	throw new ActionServletException("atencao.descricao_abreviada_sistema_abastecimento_nao_informado");
	        } else{
	        	
	        	//Fonte de Captacao
	        	FonteCaptacao fonteCaptacao = new FonteCaptacao();
	        	
	        	if (Util.verificarNaoVazio(fonteCaptacaoId)) {
	
	            	Collection colecaoPesquisaFonteCaptacao = null;
	
	                FiltroFonteCaptacao filtroFonteCaptacao = new FiltroFonteCaptacao();
	
	                filtroFonteCaptacao.adicionarParametro(new ParametroSimples(FiltroFonteCaptacao.ID, 
	                		fonteCaptacaoId));
	
	                filtroFonteCaptacao.adicionarParametro(
	                	new ParametroSimples(
	                			FiltroFonteCaptacao.INDICADOR_USO,
	                		ConstantesSistema.INDICADOR_USO_ATIVO));
	
	                //Retorna Fonte de Captação
	                colecaoPesquisaFonteCaptacao = 
	                	this.getFachada().pesquisar(filtroFonteCaptacao,
	                        FonteCaptacao.class.getName());
	
	                if (colecaoPesquisaFonteCaptacao == null || colecaoPesquisaFonteCaptacao.isEmpty()) {
	                    //Fonte de Captacao inexistente
	                    throw new ActionServletException("atencao.pesquisa.fonte_captacao_inexistente");
	                } else {
	                    fonteCaptacao =(FonteCaptacao) Util.retonarObjetoDeColecao(colecaoPesquisaFonteCaptacao);
	                }
	        	}
	                
	               //Tipo de Captacao
	               //TipoCaptacao tipoCaptacao = new TipoCaptacao();
	            	
	            	if (Util.verificarNaoVazio(tipoCaptacaoId)) {
	
	                	Collection colecaoPesquisaTipoCaptacao = null;
	
	                    FiltroTipoCaptacao filtroTipoCaptacao = new FiltroTipoCaptacao();
	
	                    filtroTipoCaptacao.adicionarParametro(new ParametroSimples(FiltroTipoCaptacao.ID, 
	                    		tipoCaptacaoId));
	
	                    filtroTipoCaptacao.adicionarParametro(
	                    	new ParametroSimples(
	                    			FiltroTipoCaptacao.INDICADOR_USO,
	                    		ConstantesSistema.INDICADOR_USO_ATIVO));
	
	                    //Retorna Tipo de Captação
	                    colecaoPesquisaTipoCaptacao = 
	                    	this.getFachada().pesquisar(filtroTipoCaptacao,
	                            TipoCaptacao.class.getName());
	
	                    if (colecaoPesquisaTipoCaptacao == null || colecaoPesquisaTipoCaptacao.isEmpty()) {
	                        //Tipo de Captacao inexistente
	                        throw new ActionServletException("atencao.pesquisa.tipo_captacao_inexistente");
	                    } /*else {
	                        tipoCaptacao =(TipoCaptacao) Util.retonarObjetoDeColecao(colecaoPesquisaTipoCaptacao);
	                    }*/
	                
	                
	        	}
	        	//Criar o objeto sistemaAbastecimento que será inserido na base
	        	SistemaAbastecimento sistemaAbastecimento = new SistemaAbastecimento();
	        	
	        	sistemaAbastecimento.setDescricao(descricao);
	        	sistemaAbastecimento.setDescricaoAbreviada(descricaoAbreviada);
	        	sistemaAbastecimento.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
	        	sistemaAbastecimento.setUltimaAlteracao(new Date());
	        	sistemaAbastecimento.setFonteCaptacao(fonteCaptacao);
	        	
	        	//------------ REGISTRAR TRANSAÇÃO ----------------
	        	sistemaAbastecimento.setOperacaoEfetuada(operacaoEfetuada);
	        	sistemaAbastecimento.adicionarUsuario(usuario,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
	        	registradorOperacao.registrarOperacao(sistemaAbastecimento);
	        	//------------ REGISTRAR TRANSAÇÃO ----------------
	        	
	        	Integer codigoSistemaAbastecimentoInserido = 
	          		(Integer) this.getFachada().inserir(sistemaAbastecimento);
	        	
	        	montarPaginaSucesso(httpServletRequest,
	            		"Sistema de Abastecimento de código " + sistemaAbastecimento.getId() 
	                    	+ " - "  + sistemaAbastecimento.getDescricao().toUpperCase() 
	                    	+ " inserido com sucesso.",
	                    	"Inserir outro Sistema Abastecimento",
	                    	"exibirInserirSistemaAbastecimentoAction.do?menu=sim",
	                    	"exibirAtualizarSistemaAbastecimentoAction.do?menu=sim&sistemaAbastecimentoId=" + 
	                    	codigoSistemaAbastecimentoInserido, "Atualizar Sistema de Abastecimento Inserido");
	        	
	       }
	
	        //devolve o mapeamento de retorno
	        return retorno;
	    }

    }
