package gcom.gui.relatorio.gerencial.micromedicao;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.gerencial.cadastro.localidade.FiltroGLocalidade;
import gcom.gerencial.cadastro.localidade.GLocalidade;
import gcom.gui.ActionServletException;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.gerencial.micromedicao.FiltrarRelatorioResumoDistritoOperacionalHelper;
import gcom.relatorio.gerencial.micromedicao.RelatorioResumoDistritoOperacional;
import gcom.relatorio.gerencial.micromedicao.RelatorioResumoZonaAbastecimento;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioResumoDistritoOperacionalAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		GerarRelatorioResumoDistritoOperacionalActionForm form = (GerarRelatorioResumoDistritoOperacionalActionForm) actionForm;

		FiltrarRelatorioResumoDistritoOperacionalHelper filtro = new FiltrarRelatorioResumoDistritoOperacionalHelper();

		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		// Gerência Regional
		if (form.getGerenciaRegional() != null
				&& !form.getGerenciaRegional().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.ID, form.getGerenciaRegional()));

			Collection colecaoGerenciaRegional = this.getFachada().pesquisar(
					filtroGerenciaRegional, GerenciaRegional.class.getName());

			GerenciaRegional gerencia = (GerenciaRegional) Util
					.retonarObjetoDeColecao(colecaoGerenciaRegional);

			filtro.setGerenciaRegional(gerencia.getId().toString());
			filtro.setDescGerenciaRegional(gerencia.getNome());

		}

		// Unidade de Negocio
		if (form.getUnidadeNegocio() != null
				&& !form.getUnidadeNegocio().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();

			filtroUnidadeNegocio.setConsultaSemLimites(true);
			filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);

			if (form.getGerenciaRegional() != null
					&& !form.getGerenciaRegional().equals(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
						FiltroUnidadeNegocio.ID, form.getUnidadeNegocio()));
			}

			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoUnidadeNegocio = this.getFachada().pesquisar(
					filtroUnidadeNegocio, UnidadeNegocio.class.getName());

			UnidadeNegocio unidade = (UnidadeNegocio) Util
					.retonarObjetoDeColecao(colecaoUnidadeNegocio);

			filtro.setUnidadeNegocio(unidade.getId().toString());
			filtro.setDescUnidadeNegocio(unidade.getNome());

		}
		// Distrito Operacinal
		if (form.getDistritoOperacional() != null
				&& !form.getDistritoOperacional().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();

			filtroDistritoOperacional
					.adicionarParametro(new ParametroSimples(
							FiltroDistritoOperacional.ID, form
									.getDistritoOperacional()));

			Collection colecaoDistritoOperacional = this.getFachada()
					.pesquisar(filtroDistritoOperacional,
							DistritoOperacional.class.getName());

			DistritoOperacional distritoOperacional = (DistritoOperacional) Util
					.retonarObjetoDeColecao(colecaoDistritoOperacional);
			filtro.setDistritoOperacional(distritoOperacional.getId().toString());
			filtro.setDescDistritoOperacional(distritoOperacional.getDescricao());
		}

		// Localidade Inicial
		if (form.getLocalidadeInicial() != null
				&& !form.getLocalidadeInicial().equals("")) {

			FiltroGLocalidade filtroGLocalidade = new FiltroGLocalidade();

			filtroGLocalidade.adicionarParametro(new ParametroSimples(
					FiltroGLocalidade.ID, form.getLocalidadeInicial()));

			Collection colecaoLocalidade = this.getFachada()
					.pesquisarGerencial(filtroGLocalidade,
							GLocalidade.class.getName());

			if (colecaoLocalidade != null && colecaoLocalidade.isEmpty()) {
				throw new ActionServletException(
						"pesquisa.localidade.inexistente");
			} else {

				GLocalidade glocalidade = (GLocalidade) Util
						.retonarObjetoDeColecao(colecaoLocalidade);
				filtro.setLocalidadeInicial(glocalidade.getId());
				filtro
						.setNomeLocalidadeInicial(glocalidade
								.getNomelocalidade());
			}
		}

		// Setor Comercial Inicial
		if (form.getSetorComercialInicial() != null
				&& !form.getSetorComercialInicial().equals("")) {

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form
							.getSetorComercialInicial()));
			
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.LOCALIDADE, filtro.getLocalidadeInicial()));

			Collection colecaoSetor = this.getFachada().pesquisar(
					filtroSetorComercial, SetorComercial.class.getName());

			if (colecaoSetor != null && colecaoSetor.isEmpty()) {
				throw new ActionServletException(
						"atencao.setor_comercial.inexistente");
			} else {

				SetorComercial setorComercial = (SetorComercial) Util
						.retonarObjetoDeColecao(colecaoSetor);

				filtro.setSetorComercialInicial(setorComercial.getId());
				filtro.setCodigoSetorComercialInicial(setorComercial.getCodigo());
			
			}
		}
		// Localidade Final
		if (form.getLocalidadeFinal() != null
				&& !form.getLocalidadeFinal().equals("")) {

			FiltroGLocalidade filtroGLocalidade = new FiltroGLocalidade();

			filtroGLocalidade.adicionarParametro(new ParametroSimples(
					FiltroGLocalidade.ID, form.getLocalidadeFinal()));

			Collection colecaoLocalidade = this.getFachada()
					.pesquisarGerencial(filtroGLocalidade,
							GLocalidade.class.getName());

			if (colecaoLocalidade != null && colecaoLocalidade.isEmpty()) {
				throw new ActionServletException(
						"pesquisa.localidade.inexistente");
			} else {

				GLocalidade glocalidade = (GLocalidade) Util
						.retonarObjetoDeColecao(colecaoLocalidade);

				filtro.setLocalidadeFinal(glocalidade.getId());
				filtro.setNomeLocalidadeFinal(glocalidade.getNomelocalidade());
			}
		}

		// Setor Comercial Final
		if (form.getSetorComercialFinal() != null
				&& !form.getSetorComercialFinal().equals("")) {

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form
							.getSetorComercialFinal()));

			Collection colecaoSetor = this.getFachada().pesquisar(
					filtroSetorComercial, SetorComercial.class.getName());

			if (colecaoSetor != null && colecaoSetor.isEmpty()) {
				throw new ActionServletException(
						"atencao.setor_comercial.inexistente");
			} else {

				SetorComercial setorComercial = (SetorComercial) Util
						.retonarObjetoDeColecao(colecaoSetor);

				filtro.setSetorComercialFinal(setorComercial.getId());
				filtro.setCodigoSetorComercialFinal(setorComercial.getCodigo());
			}
		}
		// Referencia
		if (form.getMesAno() != null && !form.getMesAno().equals("")) {
			filtro.setMesAno(Util.formatarMesAnoComBarraParaAnoMes(
					form.getMesAno()).toString());
		}
		// Tipo
		if (form.getTipo() != null && !form.getTipo().equals("")) {
			filtro.setTipo(form.getTipo());

		}
		String tipoRelatorio = null;

		if (form.getTipo().equals("A")) {
			RelatorioResumoDistritoOperacional relatorioA = new RelatorioResumoDistritoOperacional(
					this.getUsuarioLogado(httpServletRequest));

			relatorioA.addParametro(
					"filtrarRelatorioResumoDistritoOperacionalHelper", filtro);
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorioA.addParametro("tipoFormatoRelatorio", Integer
					.parseInt(tipoRelatorio));
			retorno = processarExibicaoRelatorio(relatorioA, tipoRelatorio,
					httpServletRequest, httpServletResponse, actionMapping);

		} else if (form.getTipo().equals("D")) {
			RelatorioResumoZonaAbastecimento relatorioD = new RelatorioResumoZonaAbastecimento(
					this.getUsuarioLogado(httpServletRequest));
			relatorioD.addParametro(
					"filtrarRelatorioResumoDistritoOperacionalHelper", filtro);
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorioD.addParametro("tipoFormatoRelatorio", Integer
					.parseInt(tipoRelatorio));
			retorno = processarExibicaoRelatorio(relatorioD, tipoRelatorio,
					httpServletRequest, httpServletResponse, actionMapping);
		}
		return retorno;
	}

}
