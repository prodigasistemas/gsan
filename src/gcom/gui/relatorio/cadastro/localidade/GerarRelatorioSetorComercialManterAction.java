package gcom.gui.relatorio.cadastro.localidade;

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.cadastro.localidade.PesquisarFiltrarSetorComercialActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.localidade.RelatorioManterSetorComercial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corrêa
 * @version 1.0
 */

public class GerarRelatorioSetorComercialManterAction extends ExibidorProcessamentoTarefaRelatorio {

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

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarFiltrarSetorComercialActionForm pesquisarAtualizarSetorComercialActionForm = (PesquisarFiltrarSetorComercialActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		FiltroSetorComercial filtroSetorComercial = (FiltroSetorComercial) sessao
				.getAttribute("filtroSetorComercial");

		// Inicio da parte que vai mandar os parametros para o relatório

		SetorComercial setorComercialParametros = new SetorComercial();

		String idMunicipio = (String) pesquisarAtualizarSetorComercialActionForm
				.getMunicipioID();

		Municipio municipio = null;

		if (idMunicipio != null && !idMunicipio.equals("")) {
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, idMunicipio));
			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection municipios = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (municipios != null && !municipios.isEmpty()) {
				// O municipio foi encontrado
				Iterator municipioIterator = municipios.iterator();

				municipio = (Municipio) municipioIterator.next();

			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Município");
			}

		} else {
			municipio = new Municipio();
		}

		String idLocalidade = (String) pesquisarAtualizarSetorComercialActionForm
				.getLocalidadeID();

		Localidade localidade = null;

		if (idLocalidade != null && !idLocalidade.equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidade));
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection localidades = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (localidades != null && !localidades.isEmpty()) {
				// A localidade foi encontrada
				Iterator localidadeIterator = localidades.iterator();

				localidade = (Localidade) localidadeIterator.next();

			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Localidade");
			}

		} else {
			localidade = new Localidade();
		}

		int codigoSetorComercial = 0;

		String codigoSetorComercialPesquisar = (String) pesquisarAtualizarSetorComercialActionForm
				.getSetorComercialCD();

		if (codigoSetorComercialPesquisar != null
				&& !codigoSetorComercialPesquisar.equals("")) {
			codigoSetorComercial = Integer
					.parseInt(codigoSetorComercialPesquisar);
		}

		Short indicadorDeUso = null;

		if (pesquisarAtualizarSetorComercialActionForm.getIndicadorUso() != null
				&& !pesquisarAtualizarSetorComercialActionForm
						.getIndicadorUso().equals("")) {

			indicadorDeUso = new Short(""
					+ pesquisarAtualizarSetorComercialActionForm
							.getIndicadorUso());
		}
		
		String nomeSetorComercial = "";
		
		if (pesquisarAtualizarSetorComercialActionForm
				.getSetorComercialNome() != null) {
			nomeSetorComercial = pesquisarAtualizarSetorComercialActionForm
			.getSetorComercialNome();
		}

		// seta os parametros que serão mostrados no relatório
		setorComercialParametros.setLocalidade(localidade);
		setorComercialParametros.setMunicipio(municipio);
		setorComercialParametros.setCodigo(codigoSetorComercial);
		setorComercialParametros.setDescricao(nomeSetorComercial);
		setorComercialParametros.setIndicadorUso(indicadorDeUso);

		// Fim da parte que vai mandar os parametros para o relatório

			// cria uma instância da classe do relatório
			RelatorioManterSetorComercial relatorioManterSetorComercial = new RelatorioManterSetorComercial(
					(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

			relatorioManterSetorComercial.addParametro("filtroSetorComercial",
					filtroSetorComercial);
			relatorioManterSetorComercial.addParametro(
					"setorComercialParametros", setorComercialParametros);

			// chama o metódo de gerar relatório passando o código da analise
			// como parâmetro
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorioManterSetorComercial.addParametro("tipoFormatoRelatorio", Integer
					.parseInt(tipoRelatorio));
			try {
				retorno = processarExibicaoRelatorio(relatorioManterSetorComercial,
						tipoRelatorio, httpServletRequest, httpServletResponse,
						actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
