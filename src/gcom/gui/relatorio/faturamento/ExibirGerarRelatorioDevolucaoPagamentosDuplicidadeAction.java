package gcom.gui.relatorio.faturamento;

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
 * [UC1129] Gerar Relatório Devolução dos Pagamentos em Duplicidade
 * 
 * @author Hugo Leonardo
 *
 * @date 10/03/2011
 */

public class ExibirGerarRelatorioDevolucaoPagamentosDuplicidadeAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
			.findForward("exibirGerarRelatorioDevolucaoPagamentosDuplicidadeAction");
		
		GerarRelatorioDevolucaoPagamentosDuplicidadeActionForm form = 
			(GerarRelatorioDevolucaoPagamentosDuplicidadeActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Primeira vez que carrega a pagina
		if ( httpServletRequest.getParameter("menu") != null && 
				httpServletRequest.getParameter("menu").equals("sim")) {
			
			//form.setSituacaoUsuario("0");
		}
		
		this.pesquisarGerencia(httpServletRequest,form);
		this.pesquisarUnidadeNegocio(httpServletRequest,form);
		this.pesquisarPerfilImovel(httpServletRequest,form);
		this.pesquisarCategoriaImovel(httpServletRequest,form);

		// Seta os request´s encontrados
		if ( httpServletRequest.getParameter("validar") != null && 
				httpServletRequest.getParameter("validar").equals("localidade")) {
			
			this.validarLocalidade(httpServletRequest, form, sessao);
		}
		
		// Pega os codigos que o usuario digitou para a pesquisa direta da Localidade
		if (form.getIdLocalidade() != null && !form.getIdLocalidade().trim().equals("")) {
			
			this.pesquisarLocalidade( httpServletRequest, form);
		}
		
		return retorno;
	}
	
	private void pesquisarGerencia(HttpServletRequest httpServletRequest,
			GerarRelatorioDevolucaoPagamentosDuplicidadeActionForm form){
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		
		filtroGerenciaRegional.setConsultaSemLimites(true);
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples( FiltroGerenciaRegional.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);		

		Collection colecaoGerenciaRegional = 
			this.getFachada().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());

		if ( Util.isVazioOrNulo(colecaoGerenciaRegional)) {
			
			throw new ActionServletException("atencao.naocadastrado", null, "Gerência Regional");
		} else {
			
			httpServletRequest.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
		}
	}
	
	private void pesquisarUnidadeNegocio(HttpServletRequest httpServletRequest,
			GerarRelatorioDevolucaoPagamentosDuplicidadeActionForm form){
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		
		if(form.getIdGerencia() != null && !form.getIdGerencia().equals("-1")){
			
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.GERENCIA, 
					new Integer(form.getIdGerencia())));
		}
		
		filtroUnidadeNegocio.setConsultaSemLimites(true);
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);

		filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection colecaoUnidadeNegocio = 
			this.getFachada().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());

		if ( Util.isVazioOrNulo(colecaoUnidadeNegocio)) {
			
			throw new ActionServletException("atencao.naocadastrado", null, "Unidade Negócio");
		} else {
			
			httpServletRequest.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
		}
	}

	private void validarLocalidade(HttpServletRequest httpServletRequest,
			GerarRelatorioDevolucaoPagamentosDuplicidadeActionForm form, HttpSession sessao){
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, form.getIdLocalidade()));

		Collection<Localidade> localidadePesquisada = this.getFachada().pesquisar(
				filtroLocalidade, Localidade.class.getName());

		// Se nenhum usuário for encontrado a mensagem é enviada para a página
		if ( !Util.isVazioOrNulo(localidadePesquisada)) {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(localidadePesquisada);
		
			if(form.getIdGerencia() != null && !form.getIdGerencia().equals("-1") 
					&& localidade.getGerenciaRegional().getId().compareTo(new Integer(form.getIdGerencia())) != 0){
				
				FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
				
				filtroGerenciaRegional.adicionarParametro(new ParametroSimples( FiltroGerenciaRegional.ID, 
						form.getIdGerencia()));		
	
				Collection colecaoGerencia = 
					this.getFachada().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
				
				GerenciaRegional gerenciaRegional = (GerenciaRegional)Util.retonarObjetoDeColecao(colecaoGerencia);
				
				form.setIdLocalidade("");
				form.setNomeLocalidade("");
				httpServletRequest.setAttribute("limpar", "localidade");
				
				throw new ActionServletException("atencao.localidade_nao_percente_gerencia_regional", null, gerenciaRegional.getNome());
			}
			
			if(form.getIdUnidadeNegocio() != null && !form.getIdUnidadeNegocio().equals("-1") 
					&& localidade.getUnidadeNegocio().getId().compareTo(new Integer(form.getIdUnidadeNegocio())) != 0){
				
				FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
				
				filtroUnidadeNegocio.adicionarParametro(
						new ParametroSimples(FiltroUnidadeNegocio.ID, form.getIdUnidadeNegocio()));		
	
				Collection colecaoUnidadeNegocio = 
					this.getFachada().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
				
				UnidadeNegocio unidadeNegocio = (UnidadeNegocio)Util.retonarObjetoDeColecao(colecaoUnidadeNegocio);
				
				form.setIdLocalidade("");
				form.setNomeLocalidade("");
				httpServletRequest.setAttribute("limpar", "localidade");
				
				throw new ActionServletException("atencao.localidade_nao_percente_unidade_negocio", null, unidadeNegocio.getNome());
			}
		}
	}
	
	private void pesquisarPerfilImovel(HttpServletRequest httpServletRequest,
			GerarRelatorioDevolucaoPagamentosDuplicidadeActionForm form){
		
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		
		filtroImovelPerfil.setConsultaSemLimites(true);
		filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);

		filtroImovelPerfil.adicionarParametro(
				new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection colecaoImovelPerfil = 
			this.getFachada().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());

		if ( Util.isVazioOrNulo(colecaoImovelPerfil)) {
			
			throw new ActionServletException("atencao.naocadastrado", null, "Imóvel Perfil");
		} else {
			
			httpServletRequest.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
		}
	}
	
	private void pesquisarCategoriaImovel(HttpServletRequest httpServletRequest,
			GerarRelatorioDevolucaoPagamentosDuplicidadeActionForm form){
		
		FiltroCategoria filtroCategoria = new FiltroCategoria();
		
		filtroCategoria.setConsultaSemLimites(true);
		filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);

		filtroCategoria.adicionarParametro(
				new ParametroSimples( FiltroCategoria.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection colecaoCategoria = 
			this.getFachada().pesquisar(filtroCategoria, Categoria.class.getName());

		if ( Util.isVazioOrNulo(colecaoCategoria)) {
			
			throw new ActionServletException("atencao.naocadastrado", null, "Categoria");
		} else {
			
			httpServletRequest.setAttribute("colecaoCategoria", colecaoCategoria);
		}
	}
	
	private void pesquisarLocalidade(HttpServletRequest httpServletRequest, 
			GerarRelatorioDevolucaoPagamentosDuplicidadeActionForm form) {

		// Pesquisa a usuário na base
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, form.getIdLocalidade()));
		
		if(form.getIdUnidadeNegocio() != null && !form.getIdUnidadeNegocio().equals("-1")){
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.UNIDADE_NEGOCIO_ID, 
					new Integer(form.getIdUnidadeNegocio())));
		}
		
		if(form.getIdGerencia() != null && !form.getIdGerencia().equals("-1")){
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_GERENCIA, 
					new Integer(form.getIdGerencia())));
		}

		Collection<Localidade> localidadePesquisada = this.getFachada().pesquisar(
				filtroLocalidade, Localidade.class.getName());

		// Se nenhum usuário for encontrado a mensagem é enviada para a página
		if ( !Util.isVazioOrNulo(localidadePesquisada)) {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(localidadePesquisada);
			
			form.setIdLocalidade("" + localidade.getId());
			form.setNomeLocalidade( localidade.getDescricao());

		} else {
			form.setIdLocalidade("");
			form.setNomeLocalidade("LOCALIDADE INEXISTENTE");
			httpServletRequest.setAttribute("localidadeInexistente",true);
			httpServletRequest.setAttribute("nomeCampo", "idLocalidade");
		}
	}
}
