package gcom.gui.relatorio.faturamento;

import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.conta.FiltroContaMotivoRevisao;
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
 * Action que faz a exibição da tela para o usuário setar os parâmetros
 * necessário para a geração do relatório
 * 
 * [UC0635] Gerar Relatórios de Contas em Revisão
 * 
 * @author Rafael Corrêa
 * @since 20/09/2007
 */
public class ExibirGerarRelatorioContasEmRevisaoAction extends GcomAction {

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
 
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirGerarRelatorioContasEmRevisao");

		GerarRelatorioContasEmRevisaoActionForm gerarRelatorioContasEmRevisaoActionForm = (GerarRelatorioContasEmRevisaoActionForm) actionForm;
 
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String parametroIdGerenciaRegional = httpServletRequest.getParameter("idGerenciaRegional");
		if (parametroIdGerenciaRegional != null) {
			if (parametroIdGerenciaRegional.equals("-1")) {
				gerarRelatorioContasEmRevisaoActionForm.setIdGerenciaRegional(null);
			} else {
				gerarRelatorioContasEmRevisaoActionForm.setIdGerenciaRegional(parametroIdGerenciaRegional);
			}
		} 
		
		// Carrega as coleções que serão exibidas na tela
		// Gerência Regional
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
		
		sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		if ( gerarRelatorioContasEmRevisaoActionForm.getIdGerenciaRegional() != null && 
			!gerarRelatorioContasEmRevisaoActionForm.getIdGerenciaRegional().trim().equals("")) {
			
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.GERENCIA, gerarRelatorioContasEmRevisaoActionForm.getIdGerenciaRegional()));
		}
		Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
		
		sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
		
		// Motivo de Revisão
		FiltroContaMotivoRevisao filtroContaMotivoRevisao = new FiltroContaMotivoRevisao();
		
		Collection colecaoContaMotivoRevisao = fachada.pesquisar(filtroContaMotivoRevisao, ContaMotivoRevisao.class.getName());
		
		sessao.setAttribute("colecaoContaMotivoRevisao", colecaoContaMotivoRevisao);
		
		// Perfil do Imóvel
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		
		Collection colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
		
		sessao.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
		
		// Categoria
		FiltroCategoria filtroCategoria = new FiltroCategoria();
		
		Collection colecaoCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName());
		
		sessao.setAttribute("colecaoCategoria", colecaoCategoria);
		
		// Esfera Poder
		FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
		
		Collection colecaoEsferaPoder = fachada.pesquisar(filtroEsferaPoder, EsferaPoder.class.getName());
		
		sessao.setAttribute("colecaoEsferaPoder", colecaoEsferaPoder);
		
		// Valida a parte de quando o usuário pesquisa pelo enter
		// Elo
//		String idElo = gerarRelatorioContasEmRevisaoActionForm
//				.getIdElo();
//
//		if (idElo != null && !idElo.equals("")) {
//			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
//
//			filtroLocalidade.adicionarParametro(new ParametroSimples(
//					FiltroLocalidade.ID, idElo));
//			filtroLocalidade.adicionarParametro(new ParametroSimples(
//					FiltroLocalidade.ID_ELO, idElo));
//
//			Collection colecaoElos = fachada.pesquisar(filtroLocalidade,
//					Localidade.class.getName());
//
//			if (colecaoElos != null && !colecaoElos.isEmpty()) {
//				Localidade localidade = (Localidade) Util
//						.retonarObjetoDeColecao(colecaoElos);
//
//				gerarRelatorioContasEmRevisaoActionForm
//						.setIdElo(localidade.getId().toString());
//				gerarRelatorioContasEmRevisaoActionForm
//						.setNomeElo(localidade.getDescricao());
//				httpServletRequest.setAttribute("nomeCampo", "idLocalidadeInicial");
//
//			} else {
//				gerarRelatorioContasEmRevisaoActionForm.setIdElo("");
//				gerarRelatorioContasEmRevisaoActionForm
//						.setNomeElo("Elo Inexistente");
//
//				httpServletRequest.setAttribute("idEloNaoEncontrado",
//						"true");
//
//				httpServletRequest.setAttribute("nomeCampo", "idElo");
//			}
//		}

		// Localidade Inicial
		String idLocalidadeInicial = gerarRelatorioContasEmRevisaoActionForm
				.getIdLocalidadeInicial();

		if (idLocalidadeInicial != null && !idLocalidadeInicial.equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeInicial));

			Collection colecaoLocalidades = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidades != null && !colecaoLocalidades.isEmpty()) {
				Localidade localidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidades);

				gerarRelatorioContasEmRevisaoActionForm
						.setIdLocalidadeInicial(localidade.getId().toString());
				gerarRelatorioContasEmRevisaoActionForm
						.setNomeLocalidadeInicial(localidade.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "idLocalidadeFinal");

			} else {
				gerarRelatorioContasEmRevisaoActionForm.setIdLocalidadeInicial("");
				gerarRelatorioContasEmRevisaoActionForm
						.setNomeLocalidadeInicial("Localidade Inexistente");

				httpServletRequest.setAttribute("idLocalidadeInicialNaoEncontrada",
						"true");

				httpServletRequest.setAttribute("nomeCampo", "idLocalidadeInicial");
			}
		}
		
		// Localidade Final
		String idLocalidadeFinal = gerarRelatorioContasEmRevisaoActionForm
		.getIdLocalidadeFinal();
		
		if (idLocalidadeFinal != null && !idLocalidadeFinal.equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeFinal));

			Collection colecaoLocalidades = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidades != null && !colecaoLocalidades.isEmpty()) {
				Localidade localidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidades);

				gerarRelatorioContasEmRevisaoActionForm
						.setIdLocalidadeFinal(localidade.getId().toString());
				gerarRelatorioContasEmRevisaoActionForm
						.setNomeLocalidadeFinal(localidade.getDescricao());

			} else {
				gerarRelatorioContasEmRevisaoActionForm.setIdLocalidadeFinal("");
				gerarRelatorioContasEmRevisaoActionForm
						.setNomeLocalidadeFinal("Localidade Inexistente");

				httpServletRequest.setAttribute("idLocalidadeFinalNaoEncontrada",
						"true");

				httpServletRequest.setAttribute("nomeCampo", "idLocalidadeFinal");
			}
		}
		
		String codigoSetorComercialInicial = gerarRelatorioContasEmRevisaoActionForm.getCodigoSetorComercialInicial();
		if (codigoSetorComercialInicial != null && !codigoSetorComercialInicial.equals("") &&
			idLocalidadeInicial != null && !idLocalidadeInicial.equals("")) {
			
			FiltroSetorComercial filtro = new FiltroSetorComercial();
			filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialInicial));
			filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, idLocalidadeInicial));
			Collection pesquisa = (Collection) fachada.pesquisar(filtro, SetorComercial.class.getName());
			
			if (pesquisa != null && !pesquisa.isEmpty()) {
				SetorComercial inicial = (SetorComercial) Util.retonarObjetoDeColecao(pesquisa);
				gerarRelatorioContasEmRevisaoActionForm.setCodigoSetorComercialInicial(inicial.getCodigo() + "");
				gerarRelatorioContasEmRevisaoActionForm.setNomeSetorComercialInicial(inicial.getDescricao());
			} else {
				gerarRelatorioContasEmRevisaoActionForm.setCodigoSetorComercialInicial("");
				gerarRelatorioContasEmRevisaoActionForm.setNomeSetorComercialInicial("SETOR COMERCIAL INEXISTENTE");
				httpServletRequest.setAttribute("setorComercialInicialInexistente",true);
			}
		} else {
			gerarRelatorioContasEmRevisaoActionForm.setCodigoSetorComercialInicial("");
			gerarRelatorioContasEmRevisaoActionForm.setNomeSetorComercialInicial("");
		}
		
		String codigoSetorComercialFinal = gerarRelatorioContasEmRevisaoActionForm.getCodigoSetorComercialFinal();
		if (codigoSetorComercialFinal != null && !codigoSetorComercialFinal.equals("") &&
			idLocalidadeFinal != null && !idLocalidadeFinal.equals("")) {
			
			FiltroSetorComercial filtro = new FiltroSetorComercial();
			filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialFinal));
			filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, idLocalidadeFinal));
			Collection pesquisa = (Collection) fachada.pesquisar(filtro, SetorComercial.class.getName());
			
			if (pesquisa != null && !pesquisa.isEmpty()) {
				SetorComercial Final = (SetorComercial) Util.retonarObjetoDeColecao(pesquisa);
				gerarRelatorioContasEmRevisaoActionForm.setCodigoSetorComercialFinal(Final.getCodigo() + "");
				gerarRelatorioContasEmRevisaoActionForm.setNomeSetorComercialFinal(Final.getDescricao());
			} else {
				gerarRelatorioContasEmRevisaoActionForm.setCodigoSetorComercialFinal("");
				gerarRelatorioContasEmRevisaoActionForm.setNomeSetorComercialFinal("SETOR COMERCIAL INEXISTENTE");
				httpServletRequest.setAttribute("setorComercialFinalInexistente",true);
			}
		} else {
			gerarRelatorioContasEmRevisaoActionForm.setCodigoSetorComercialFinal("");
			gerarRelatorioContasEmRevisaoActionForm.setNomeSetorComercialFinal("");
		}

		return retorno;

	}

}
