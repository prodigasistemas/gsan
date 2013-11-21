package gcom.gui.relatorio.faturamento;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.RelatorioContasEmRevisao;
import gcom.relatorio.faturamento.RelatorioContasEmRevisaoResumido;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição do relatório de contas em revisão
 * 
 * @author Rafael Corrêa
 * @created 20/09/2007
 */
public class GerarRelatorioContasEmRevisaoAction extends
		ExibidorProcessamentoTarefaRelatorio {

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

		// cria a variável de retorno
		ActionForward retorno = null;

		GerarRelatorioContasEmRevisaoActionForm gerarRelatorioContasEmRevisaoActionForm = (GerarRelatorioContasEmRevisaoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Valida os parâmetro passados como consulta
		boolean peloMenosUmParametroInformado = false;

		// Gerência Regional
		Integer idGerenciaRegional = null;

		if (gerarRelatorioContasEmRevisaoActionForm.getIdGerenciaRegional() != null
				&& !gerarRelatorioContasEmRevisaoActionForm
						.getIdGerenciaRegional().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			idGerenciaRegional = new Integer(
					gerarRelatorioContasEmRevisaoActionForm
							.getIdGerenciaRegional());
		}
		
		// Unidade Negocio
		Integer idUnidadeNegocio = null;

		if (gerarRelatorioContasEmRevisaoActionForm.getIdUnidadeNegocio() != null
				&& !gerarRelatorioContasEmRevisaoActionForm
						.getIdUnidadeNegocio().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			idUnidadeNegocio = new Integer(
					gerarRelatorioContasEmRevisaoActionForm
							.getIdUnidadeNegocio());
		}

		// Elo
//		Localidade elo = null;
//
//		String idElo = gerarRelatorioContasEmRevisaoActionForm.getIdElo();
//
//		if (idElo != null && !idElo.equals("")) {
//			peloMenosUmParametroInformado = true;
//			
//			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
//
//			filtroLocalidade.adicionarParametro(new ParametroSimples(
//					FiltroLocalidade.ID, idElo));
//			filtroLocalidade.adicionarParametro(new ParametroSimples(
//					FiltroLocalidade.ID_ELO, idElo));
//
//			Collection colecaoLocalidades = fachada.pesquisar(filtroLocalidade,
//					Localidade.class.getName());
//
//			if (colecaoLocalidades != null && !colecaoLocalidades.isEmpty()) {
//				elo = (Localidade) Util
//						.retonarObjetoDeColecao(colecaoLocalidades);
//			} else {
//				throw new ActionServletException(
//						"atencao.pesquisa_inexistente", null, "Elo");
//			}
//		}

		// Localidade Inicial
		Localidade localidadeInicial = null;
		SetorComercial setorComercialInicial = null;

		String idLocalidadeInicial = gerarRelatorioContasEmRevisaoActionForm
				.getIdLocalidadeInicial();
		String codigoSetorComercialInicial = gerarRelatorioContasEmRevisaoActionForm
				.getCodigoSetorComercialInicial();

		if (idLocalidadeInicial != null && !idLocalidadeInicial.equals("")) {
			peloMenosUmParametroInformado = true;
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeInicial));

			Collection colecaoLocalidades = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidades != null && !colecaoLocalidades.isEmpty()) {
				localidadeInicial = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidades);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Localidade Inicial");
			}
			
			// Setor Comercial Inicial
			if (codigoSetorComercialInicial != null && !codigoSetorComercialInicial.trim().equals("")) {
				
				FiltroSetorComercial filtro = new FiltroSetorComercial();
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialInicial));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidadeInicial.getId()));
				Collection pesquisa = (Collection) fachada.pesquisar(filtro, SetorComercial.class.getName());
				
				if (pesquisa != null && !pesquisa.isEmpty()) {
					setorComercialInicial = (SetorComercial) Util.retonarObjetoDeColecao(pesquisa);
				} else {
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Setor Comercial");
				}
			}
		}

		// Localidade Final
		Localidade localidadeFinal = null;
		SetorComercial setorComercialFinal = null;

		String idLocalidadeFinal = gerarRelatorioContasEmRevisaoActionForm
				.getIdLocalidadeFinal();
		String codigoSetorComercialFinal = gerarRelatorioContasEmRevisaoActionForm
				.getCodigoSetorComercialFinal();

		if (idLocalidadeFinal != null && !idLocalidadeFinal.equals("")) {
			peloMenosUmParametroInformado = true;
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeFinal));

			Collection colecaoLocalidades = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidades != null && !colecaoLocalidades.isEmpty()) {
				localidadeFinal = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidades);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Localidade Final");
			}
			
			// Setor Comercial Final
			if (codigoSetorComercialFinal != null && !codigoSetorComercialFinal.trim().equals("")) {
				
				FiltroSetorComercial filtro = new FiltroSetorComercial();
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialFinal));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidadeFinal.getId()));
				Collection pesquisa = (Collection) fachada.pesquisar(filtro, SetorComercial.class.getName());
				
				if (pesquisa != null && !pesquisa.isEmpty()) {
					setorComercialFinal = (SetorComercial) Util.retonarObjetoDeColecao(pesquisa);
				} else {
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Setor Comercial");
				}
			}
		}

		// Motivo de Revisão
		// Anormalidade de Consumo
		Collection<Integer> colecaoIdsMotivoRevisao = null;

		if (gerarRelatorioContasEmRevisaoActionForm.getColecaoIdsContaMotivoRevisao() != null) {
			colecaoIdsMotivoRevisao = new ArrayList<Integer>();
			
			for (String id : gerarRelatorioContasEmRevisaoActionForm.getColecaoIdsContaMotivoRevisao()) {
				if (!id.equals("-1")) {
					colecaoIdsMotivoRevisao.add(new Integer(id));
				}
			}
			
			if (colecaoIdsMotivoRevisao.size() > 0) {
				peloMenosUmParametroInformado = true;
			}
		}
/*		Integer idMotivoRevisao = null;

		if (gerarRelatorioContasEmRevisaoActionForm.getIdMotivoRevisao() != null
				&& !gerarRelatorioContasEmRevisaoActionForm
						.getIdMotivoRevisao().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			
			idMotivoRevisao = new Integer(
					gerarRelatorioContasEmRevisaoActionForm
							.getIdMotivoRevisao());
		}*/

		// Perfil do Imóvel
		Integer idImovelPerfil = null;

		if (gerarRelatorioContasEmRevisaoActionForm.getIdImovelPerfil() != null
				&& !gerarRelatorioContasEmRevisaoActionForm.getIdImovelPerfil()
						.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			
			idImovelPerfil = new Integer(
					gerarRelatorioContasEmRevisaoActionForm.getIdImovelPerfil());
		}

		// Referência Inicial
		Integer referenciaInicial = null;

		if (gerarRelatorioContasEmRevisaoActionForm.getReferenciaInicial() != null
				&& !gerarRelatorioContasEmRevisaoActionForm
						.getReferenciaInicial().equals("")) {
			peloMenosUmParametroInformado = true;
			
			referenciaInicial = Util
					.formatarMesAnoComBarraParaAnoMes(gerarRelatorioContasEmRevisaoActionForm
							.getReferenciaInicial());
		}

		// Referência Final
		Integer referenciaFinal = null;

		if (gerarRelatorioContasEmRevisaoActionForm.getReferenciaFinal() != null
				&& !gerarRelatorioContasEmRevisaoActionForm
						.getReferenciaFinal().equals("")) {
			peloMenosUmParametroInformado = true;
			
			referenciaFinal = Util
					.formatarMesAnoComBarraParaAnoMes(gerarRelatorioContasEmRevisaoActionForm
							.getReferenciaFinal());
		}
		
		// Categoria
		Integer idCategoria = null;

		if (gerarRelatorioContasEmRevisaoActionForm.getIdCategoria() != null
				&& !gerarRelatorioContasEmRevisaoActionForm.getIdCategoria()
						.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			
			idCategoria = new Integer(
					gerarRelatorioContasEmRevisaoActionForm.getIdCategoria());
		}
		
		// Esfera Poder
		Integer idEsferaPoder = null;

		if (gerarRelatorioContasEmRevisaoActionForm.getIdEsferaPoder() != null
				&& !gerarRelatorioContasEmRevisaoActionForm.getIdEsferaPoder()
						.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			
			idEsferaPoder = new Integer(
					gerarRelatorioContasEmRevisaoActionForm.getIdEsferaPoder());
		}
		
		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// seta os parametros que serão mostrados no relatório

		// Fim da parte que vai mandar os parametros para o relatório
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		// Relatório Analítico
		RelatorioContasEmRevisao relatorioContasEmRevisao = new RelatorioContasEmRevisao(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));
		
		relatorioContasEmRevisao.addParametro("idGerenciaRegional",
				idGerenciaRegional);
		
		relatorioContasEmRevisao.addParametro("idUnidadeNegocio", idUnidadeNegocio);

		if (localidadeInicial != null) {
			relatorioContasEmRevisao.addParametro("idLocalidadeInicial",
					localidadeInicial.getId());
		}

		if (localidadeFinal != null) {
			relatorioContasEmRevisao.addParametro("idLocalidadeFinal",
					localidadeFinal.getId());
		}
		
		if (setorComercialInicial != null) {
			relatorioContasEmRevisao.addParametro("codigoSetorComercialInicial", setorComercialInicial.getCodigo());
		}
		
		if (setorComercialFinal != null) {
			relatorioContasEmRevisao.addParametro("codigoSetorComercialFinal", setorComercialFinal.getCodigo());
		}
		
		relatorioContasEmRevisao.addParametro("colecaoIdsMotivoRevisao",
				colecaoIdsMotivoRevisao);
		relatorioContasEmRevisao.addParametro("idImovelPerfil", idImovelPerfil);
		relatorioContasEmRevisao.addParametro("referenciaInicial",
				referenciaInicial);
		relatorioContasEmRevisao.addParametro("referenciaFinal",
				referenciaFinal);
		relatorioContasEmRevisao.addParametro("idCategoria", idCategoria);
		relatorioContasEmRevisao.addParametro("idEsferaPoder", idEsferaPoder);

		relatorioContasEmRevisao.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		
		// Relatório Resumido
		RelatorioContasEmRevisaoResumido relatorioContasEmRevisaoResumido = new RelatorioContasEmRevisaoResumido(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));
		relatorioContasEmRevisaoResumido.addParametro("idGerenciaRegional",
				idGerenciaRegional);
		
		relatorioContasEmRevisaoResumido.addParametro("idUnidadeNegocio",
				idUnidadeNegocio);

		if (localidadeInicial != null) {
			relatorioContasEmRevisaoResumido.addParametro("idLocalidadeInicial",
					localidadeInicial.getId());
		}

		if (localidadeFinal != null) {
			relatorioContasEmRevisaoResumido.addParametro("idLocalidadeFinal",
					localidadeFinal.getId());
		}
		
		if (setorComercialInicial != null) {
			relatorioContasEmRevisaoResumido.addParametro("codigoSetorComercialInicial", setorComercialInicial.getCodigo());
		}
		
		if (setorComercialFinal != null) {
			relatorioContasEmRevisaoResumido.addParametro("codigoSetorComercialFinal", setorComercialFinal.getCodigo());
		}
		
		relatorioContasEmRevisaoResumido.addParametro("colecaoIdsMotivoRevisao",
				colecaoIdsMotivoRevisao);
		relatorioContasEmRevisaoResumido.addParametro("idImovelPerfil", idImovelPerfil);
		relatorioContasEmRevisaoResumido.addParametro("referenciaInicial",
				referenciaInicial);
		relatorioContasEmRevisaoResumido.addParametro("referenciaFinal",
				referenciaFinal);
		
		relatorioContasEmRevisaoResumido.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		
		retorno = processarExibicaoRelatorio(relatorioContasEmRevisao,
		tipoRelatorio, httpServletRequest, httpServletResponse,
		actionMapping);
		
		processarExibicaoRelatorio(relatorioContasEmRevisaoResumido,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
