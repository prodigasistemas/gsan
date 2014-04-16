package gcom.gui.micromedicao.hidrometro;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.FiltroHidrometroClasseMetrologica;
import gcom.micromedicao.hidrometro.FiltroHidrometroDiametro;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.FiltroHidrometroMarca;
import gcom.micromedicao.hidrometro.FiltroHidrometroRelojoaria;
import gcom.micromedicao.hidrometro.FiltroHidrometroSituacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroTipo;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroClasseMetrologica;
import gcom.micromedicao.hidrometro.HidrometroDiametro;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroRelojoaria;
import gcom.micromedicao.hidrometro.HidrometroSituacao;
import gcom.micromedicao.hidrometro.HidrometroTipo;
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
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 * @created 1 de Setembro de 2005
 */
public class ExibirFiltrarHidrometroAction extends GcomAction {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Obtém o action form
		HidrometroActionForm hidrometroActionForm = (HidrometroActionForm) actionForm;

		Collection colecaoHidrometroCapacidade = null;

		String tela = (String) httpServletRequest.getParameter("tela");

		// Seta a ação de retorno
		ActionForward retorno = actionMapping.findForward("filtrarHidrometro");

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a facahda
		Fachada fachada = Fachada.getInstancia();

		// Obtém o objetoCosulta vindo na sessão
		String objetoConsulta = (String) httpServletRequest
				.getParameter("objetoConsulta");

		
		
		// Verifica se o objeto é diferente de nulo
		if (objetoConsulta != null
				&& !objetoConsulta.trim().equalsIgnoreCase("")
				&& (Integer.parseInt(objetoConsulta)) == 1) {
			

			// Filtro para obter o local de armazenagem ativo de id informado
			FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem = new FiltroHidrometroLocalArmazenagem();

			filtroHidrometroLocalArmazenagem
					.adicionarParametro(new ParametroSimples(
							FiltroHidrometroLocalArmazenagem.ID, new Integer(
									hidrometroActionForm
											.getIdLocalArmazenagem()),
							ParametroSimples.CONECTOR_AND));
			filtroHidrometroLocalArmazenagem
					.adicionarParametro(new ParametroSimples(
							FiltroHidrometroLocalArmazenagem.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroHidrometroLocalArmazenagem
					.setCampoOrderBy(FiltroHidrometroLocalArmazenagem.DESCRICAO);

			// Pesquisa de acordo com os parâmetros informados no filtro
			Collection colecaoHidrometroLocalArmazenagem = fachada.pesquisar(
					filtroHidrometroLocalArmazenagem,
					HidrometroLocalArmazenagem.class.getName());

			// Verifica se a pesquisa retornou algum objeto para a coleção
			if (colecaoHidrometroLocalArmazenagem != null
					&& !colecaoHidrometroLocalArmazenagem.isEmpty()) {

				// Obtém o objeto da coleção pesquisada
				HidrometroLocalArmazenagem hidrometroLocalArmazenagem = (HidrometroLocalArmazenagem) Util
						.retonarObjetoDeColecao(colecaoHidrometroLocalArmazenagem);

				// Exibe o código e a descrição pesquisa na página
				httpServletRequest.setAttribute("corLocalArmazenagem", "valor");
				hidrometroActionForm
						.setIdLocalArmazenagem(hidrometroLocalArmazenagem
								.getId().toString());
				hidrometroActionForm
						.setLocalArmazenagemDescricao(hidrometroLocalArmazenagem
								.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "fixo");

			} else {

				// Exibe mensagem de código inexiste e limpa o campo de código
				httpServletRequest.setAttribute("corLocalArmazenagem",
						"exception");
				hidrometroActionForm.setIdLocalArmazenagem("");
				hidrometroActionForm
						.setLocalArmazenagemDescricao("LOCAL DE ARMAZENAGEM INEXISTENTE");

				httpServletRequest.setAttribute("nomeCampo",
						"idLocalArmazenagem");

			}
			// Verifica se os objetos estão na sessão
		}		// CRC-1671 / Analista:Rosana  
		// Verifica se a situacao do hidrometro = instalado 
		// e libera os campos de localidade, setor e quadra para o filtro
		else if (httpServletRequest.getParameter("situacao") != null) {

			if (hidrometroActionForm.getIdHidrometroSituacao() != null
					&& !hidrometroActionForm.getIdHidrometroSituacao().equals(
							"-1")) {

				if (hidrometroActionForm.getIdHidrometroSituacao().equals(
						HidrometroSituacao.INSTALADO.toString())) {
					
					if (hidrometroActionForm.getIdLocalidade() != null
							&& !hidrometroActionForm.getIdLocalidade().equals("")) {

						this.pesquisarLocalidade(hidrometroActionForm, httpServletRequest);

						if (hidrometroActionForm.getCodigoSetorComercial() != null
								&& !hidrometroActionForm.getCodigoSetorComercial().equals(
										"")) {

							this.pesquisarSetorComercial(hidrometroActionForm,
									httpServletRequest);

						}
						if (hidrometroActionForm.getNumeroQuadra() != null
								&& !hidrometroActionForm.getNumeroQuadra().equals("")) {
							this.pesquisarQuadra(hidrometroActionForm, httpServletRequest);

						}
					}

					sessao.setAttribute("instalado", true);
				}else{
					hidrometroActionForm.setIdLocalidade("");
					hidrometroActionForm.setNomeLocalidade("");
					hidrometroActionForm.setIdSetorComercial("");
					hidrometroActionForm.setCodigoSetorComercial("");
					hidrometroActionForm.setNomeSetorComercial("");
					hidrometroActionForm.setNumeroQuadra("");
					hidrometroActionForm.setIdQuadra("");
					sessao.removeAttribute("instalado");
				}

			}else{
				sessao.removeAttribute("instalado");
				hidrometroActionForm.setIdLocalidade("");
				hidrometroActionForm.setNomeLocalidade("");
				hidrometroActionForm.setIdSetorComercial("");
				hidrometroActionForm.setCodigoSetorComercial("");
				hidrometroActionForm.setNomeSetorComercial("");
				hidrometroActionForm.setNumeroQuadra("");
				hidrometroActionForm.setIdQuadra("");
			}

		}else{
			sessao.setAttribute("tela", tela);			
		}
		if (sessao.getAttribute("colecaoHidrometroClasseMetrologica") == null
				&& sessao.getAttribute("colecaoHidrometroMarca") == null
				&& sessao.getAttribute("colecaoHidrometroDiametro") == null
				&& sessao.getAttribute("colecaoHidrometroCapacidade") == null
				&& sessao.getAttribute("colecaoHidrometroTipo") == null
				&& sessao.getAttribute("colecaoHidrometroRelojoaria") == null) {

			hidrometroActionForm.setIndicadorMacromedidor("-1");
			
			// Filtro de hidrômetro classe metrológica para obter todas as
			// classes metrológicas ativas
			FiltroHidrometroClasseMetrologica filtroHidrometroClasseMetrologica = new FiltroHidrometroClasseMetrologica();

			filtroHidrometroClasseMetrologica
					.adicionarParametro(new ParametroSimples(
							FiltroHidrometroClasseMetrologica.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroHidrometroClasseMetrologica
					.setCampoOrderBy(FiltroHidrometroClasseMetrologica.DESCRICAO);

			// Pesquisa a coleção de classe metrológica
			Collection colecaoHidrometroClasseMetrologica = fachada.pesquisar(
					filtroHidrometroClasseMetrologica,
					HidrometroClasseMetrologica.class.getName());

			// Filtro de hidrômetro marca para obter todas as marcas de
			// hidrômetros ativas
			FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca();

			filtroHidrometroMarca.adicionarParametro(new ParametroSimples(
					FiltroHidrometroMarca.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroHidrometroMarca
					.setCampoOrderBy(FiltroHidrometroMarca.DESCRICAO);

			// Pesquisa a coleção de hidrômetro marca
			Collection colecaoHidrometroMarca = fachada.pesquisar(
					filtroHidrometroMarca, HidrometroMarca.class.getName());

			// Filtro de hidrômetro diâmetro para obter todos os diâmetros de
			// hidrômetros ativos
			FiltroHidrometroDiametro filtroHidrometroDiametro = new FiltroHidrometroDiametro();

			filtroHidrometroDiametro.adicionarParametro(new ParametroSimples(
					FiltroHidrometroDiametro.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroHidrometroDiametro
					.setCampoOrderBy(FiltroHidrometroDiametro.NUMERO_ORDEM);

			// Pesquisa a coleção de hidrômetro capacidade
			Collection colecaoHidrometroDiametro = fachada.pesquisar(
					filtroHidrometroDiametro, HidrometroDiametro.class
							.getName());

			// Filtro de hidrômetro capacidade para obter todos as capacidade de
			// hidrômetros ativas
			FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();

			filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
					FiltroHidrometroCapacidade.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroHidrometroCapacidade
					.setCampoOrderBy(FiltroHidrometroCapacidade.NUMERO_ORDEM);

			// Pesquisa a coleção de hidrômetro capacidade
			colecaoHidrometroCapacidade = fachada.pesquisar(
					filtroHidrometroCapacidade, HidrometroCapacidade.class
							.getName());

			// Filtro de hidrômetro tipo para obter todos os tipos de
			// hidrômetros ativos
			FiltroHidrometroTipo filtroHidrometroTipo = new FiltroHidrometroTipo();

			filtroHidrometroTipo.adicionarParametro(new ParametroSimples(
					FiltroHidrometroTipo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroHidrometroTipo
					.setCampoOrderBy(FiltroHidrometroTipo.DESCRICAO);

			// Pesquisa a coleção de hidrômetro tipo
			Collection colecaoHidrometroTipo = fachada.pesquisar(
					filtroHidrometroTipo, HidrometroTipo.class.getName());

			// Filtro de hidrômetro situação para obter todos os tipos de
			// hidrômetros ativos
			FiltroHidrometroSituacao filtroHidrometroSituacao = new FiltroHidrometroSituacao();

			filtroHidrometroSituacao.adicionarParametro(new ParametroSimples(
					FiltroHidrometroSituacao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroHidrometroSituacao
					.setCampoOrderBy(FiltroHidrometroTipo.DESCRICAO);

			// Pesquisa a coleção de hidrômetro tipo
			Collection colecaoHidrometroSituacao = fachada.pesquisar(
					filtroHidrometroSituacao, HidrometroSituacao.class
							.getName());
			
			// Filtro de hidrômetro relojoaria para obter todos os tipos de
			// hidrômetros relojoaria ativos
			FiltroHidrometroRelojoaria filtroHidrometroRelojoaria = new FiltroHidrometroRelojoaria();

			filtroHidrometroRelojoaria.adicionarParametro(new ParametroSimples(
					FiltroHidrometroRelojoaria.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroHidrometroRelojoaria
					.setCampoOrderBy(FiltroHidrometroRelojoaria.DESCRICAO);

			// Pesquisa a coleção de hidrômetro tipo
			Collection colecaoHidrometroRelojoaria = fachada.pesquisar(
					filtroHidrometroRelojoaria, HidrometroRelojoaria.class.getName());


//			hidrometroActionForm
//					.setIndicadorMacromedidor(Hidrometro.INDICADOR_COMERCIAL
//							.toString());

			// Envia as coleções na sessão
			sessao.setAttribute("colecaoHidrometroClasseMetrologica",
					colecaoHidrometroClasseMetrologica);
			sessao.setAttribute("colecaoHidrometroMarca",
					colecaoHidrometroMarca);
			sessao.setAttribute("colecaoHidrometroDiametro",
					colecaoHidrometroDiametro);
			sessao.setAttribute("colecaoHidrometroCapacidade",
					colecaoHidrometroCapacidade);
			sessao.setAttribute("colecaoHidrometroSituacao",
					colecaoHidrometroSituacao);
			sessao.setAttribute("colecaoHidrometroRelojoaria",
					colecaoHidrometroRelojoaria);

			sessao.setAttribute("colecaoHidrometroTipo", colecaoHidrometroTipo);

			
		}
		
		httpServletRequest.removeAttribute("i");
		
		String atualizar = httpServletRequest.getParameter("atualizar");
		String menu = httpServletRequest.getParameter("menu");
		
		if (atualizar == null && menu == null){
			boolean i = true;
			httpServletRequest.setAttribute("i",i);
		}
		httpServletRequest.setAttribute("nomeCampo","numeroHidrometro");
		return retorno;
	}

	private void pesquisarLocalidade(HidrometroActionForm hidrometroActionForm,
			HttpServletRequest httpServletRequest){
		
		Collection colecaoPesquisa;
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, hidrometroActionForm.getIdLocalidade()));

		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.INDICADORUSO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		// Retorna localidade
		colecaoPesquisa = this.getFachada().pesquisar(filtroLocalidade,
				Localidade.class.getName());

		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
			// Localidade nao encontrada
			// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
			// formulário
			hidrometroActionForm.setIdLocalidade("");
			hidrometroActionForm
					.setNomeLocalidade("LOCALIDADE INEXISTENTE");
			httpServletRequest.setAttribute("corLocalidade",
					"exception");
			httpServletRequest.setAttribute("nomeCampo",
					"idLocalidade");
			
		
		}else{
			Localidade objetoLocalidade = (Localidade) Util
					.retonarObjetoDeColecao(colecaoPesquisa);
			hidrometroActionForm.setIdLocalidade(String
					.valueOf(objetoLocalidade.getId()));
			hidrometroActionForm
					.setNomeLocalidade(objetoLocalidade.getDescricao());
			
			httpServletRequest.setAttribute("corLocalidade", "valor");
			httpServletRequest.setAttribute("nomeCampo",
					"codigoSetorComercial");
		}
		
	}

	private void pesquisarSetorComercial(HidrometroActionForm hidrometroActionForm,
			HttpServletRequest httpServletRequest){
		
		Collection colecaoPesquisa = null;

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

			// O campo localidadeOrigemID será obrigatório
			if (hidrometroActionForm.getIdLocalidade() != null
					&& !hidrometroActionForm.getIdLocalidade().trim().equalsIgnoreCase("")) {

				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, hidrometroActionForm.getIdLocalidade()));

				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						hidrometroActionForm.getCodigoSetorComercial()));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = this.getFachada().pesquisar(filtroSetorComercial,
						SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialOrigemCD,
					// nomeSetorComercialOrigem e setorComercialOrigemID do
					// formulário
					hidrometroActionForm.setIdSetorComercial("");
					hidrometroActionForm
							.setCodigoSetorComercial("");
					
					hidrometroActionForm
							.setNomeSetorComercial("Setor comercial inexistente.");
					httpServletRequest.setAttribute("corSetorComercial",
							"exception");
					httpServletRequest.setAttribute("nomeCampo",
							"codigoSetorComercial");

				} else {
					
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
				
					
					hidrometroActionForm.setIdSetorComercial(String
									.valueOf(objetoSetorComercial.getId()));
					
					hidrometroActionForm
							.setCodigoSetorComercial(String
									.valueOf(objetoSetorComercial.getCodigo()));
					
					hidrometroActionForm
							.setNomeSetorComercial(objetoSetorComercial
									.getDescricao());

					
					httpServletRequest.setAttribute("corSetorComercial",
							"valor");
					httpServletRequest.setAttribute("nomeCampo",
							"numeroQuadra");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formulário
				hidrometroActionForm
						.setCodigoSetorComercial("");
				hidrometroActionForm
						.setNomeSetorComercial("Informe a localidade");
				httpServletRequest.setAttribute("corSetorComercial",
						"exception");
			}
	}
	
	private void pesquisarQuadra(HidrometroActionForm hidrometroActionForm,
			HttpServletRequest httpServletRequest) {

		Collection colecaoPesquisa = null;

		FiltroQuadra filtroQuadra = new FiltroQuadra();

		if (hidrometroActionForm.getCodigoSetorComercial() != null
				&& !hidrometroActionForm.getCodigoSetorComercial().trim()
						.equalsIgnoreCase("")
				&& hidrometroActionForm.getIdSetorComercial() != null
				&& !hidrometroActionForm.getIdSetorComercial().trim()
						.equalsIgnoreCase("")) {

			// Adiciona o id do setor comercial que está no formulário para
			// compor a pesquisa.
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID_SETORCOMERCIAL, hidrometroActionForm
							.getIdSetorComercial()));

			// Adiciona o número da quadra que esta no formulário para
			// compor a pesquisa.
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.NUMERO_QUADRA, hidrometroActionForm
							.getNumeroQuadra()));

			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna quadra
			colecaoPesquisa = this.getFachada().pesquisar(filtroQuadra,
					Quadra.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Quadra nao encontrada
				// Limpa os campos quadraOrigemNM e quadraOrigemID do
				// formulário
				hidrometroActionForm.setNumeroQuadra("");
				hidrometroActionForm.setIdQuadra("");
				// Mensagem de tela
				hidrometroActionForm.setQuadraMensagem("QUADRA INEXISTENTE.");
				httpServletRequest.setAttribute("corQuadra", "exception");
				httpServletRequest.setAttribute("nomeCampo", "numeroQuadra");
			} else {
				Quadra objetoQuadra = (Quadra) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				hidrometroActionForm.setNumeroQuadra(String
						.valueOf(objetoQuadra.getNumeroQuadra()));
				hidrometroActionForm.setIdQuadra(String.valueOf(objetoQuadra
						.getId()));

				httpServletRequest.setAttribute("corQuadra", "valor");
			}
		} else {
			// Limpa o campo quadraOrigemNM do formulário
			hidrometroActionForm.setNumeroQuadra("");
			hidrometroActionForm
					.setQuadraMensagem("Informe o setor comercial.");
			httpServletRequest.setAttribute("corQuadra", "exception");
			httpServletRequest
					.setAttribute("nomeCampo", "codigoSetorComercial");
		}

	}
	
}
