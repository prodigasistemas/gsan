package gcom.gui.relatorio.cadastro.endereco;

import gcom.cadastro.endereco.FiltroLogradouroTipo;
import gcom.cadastro.endereco.FiltroLogradouroTitulo;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.LogradouroTitulo;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.cadastro.endereco.LogradorouRelatorioHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.endereco.RelatorioManterLogradouro;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

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

public class GerarRelatorioLogradouroManterAction extends ExibidorProcessamentoTarefaRelatorio {

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

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		LogradorouRelatorioHelper helperLogradouroRelatorio = (LogradorouRelatorioHelper) pesquisarActionForm.get("helperLogradouroRelatorio");
		
		Collection logradouros = fachada.pesquisarLogradouroCompletoRelatorio(
				helperLogradouroRelatorio.getIdMunicipio(),
				helperLogradouroRelatorio.getIdBairro(),
				helperLogradouroRelatorio.getNomeLogradouro(),
				helperLogradouroRelatorio.getNomePopularLogradouro(),
				helperLogradouroRelatorio.getIdTipoLogradouro(),
				helperLogradouroRelatorio.getIdTituloLogradouro(),
				helperLogradouroRelatorio.getCodigoCep(),
				helperLogradouroRelatorio.getIdLogradouro(),
				helperLogradouroRelatorio.getIndicadorUso(),
				helperLogradouroRelatorio.getTipoPesquisa(),
				helperLogradouroRelatorio.getTipoPesquisaPopular());
		
		// Organizar a coleção

//		Collections.sort((List) logradouros, new Comparator() {
//			public int compare(Object a, Object b) {
//				String municipio1 = ((Logradouro) a).getMunicipio().getNome();
//				String municipio2 = ((Logradouro) b).getMunicipio().getNome();
//
//				return municipio1.compareTo(municipio2);
//			}
//		});

		// Inicio da parte que vai mandar os parametros para o relatório

		Logradouro logradouroParametros = new Logradouro();

		String idMunicipio = (String) pesquisarActionForm.get("idMunicipioFiltro");

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

		String idLogradouro = null;

		String idLogradouroPesquisar = (String) pesquisarActionForm
				.get("idLogradouro");

		if (idLogradouroPesquisar != null && !idLogradouroPesquisar.equals("")) {
			idLogradouro = idLogradouroPesquisar;
		}

		Integer idTitulo = (Integer) pesquisarActionForm.get("idTitulo");

		LogradouroTitulo logradouroTitulo = null;

		if (idTitulo != null && !idTitulo.equals("")) {
			FiltroLogradouroTitulo filtroLogradouroTitulo = new FiltroLogradouroTitulo();

			filtroLogradouroTitulo.adicionarParametro(new ParametroSimples(
					FiltroLogradouroTitulo.ID, idTitulo));
			filtroLogradouroTitulo.adicionarParametro(new ParametroSimples(
					FiltroLogradouroTitulo.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection logradourosTitulos = fachada.pesquisar(
					filtroLogradouroTitulo, LogradouroTitulo.class.getName());

			if (logradourosTitulos != null && !logradourosTitulos.isEmpty()) {
				// Titulo do Logradouro Foi Encontrado
				Iterator logradouroTituloIterator = logradourosTitulos
						.iterator();

				logradouroTitulo = (LogradouroTitulo) logradouroTituloIterator
						.next();

			} else {
				logradouroTitulo = new LogradouroTitulo();
			}
		}

		Integer idTipo = (Integer) pesquisarActionForm.get("idTipo");

		LogradouroTipo logradouroTipo = null;

		if (idTipo != null && !idTipo.equals("")) {
			FiltroLogradouroTipo filtroLogradouroTipo = new FiltroLogradouroTipo();

			filtroLogradouroTipo.adicionarParametro(new ParametroSimples(
					FiltroLogradouroTipo.ID, idTitulo));
			filtroLogradouroTipo.adicionarParametro(new ParametroSimples(
					FiltroLogradouroTipo.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection logradourosTipos = fachada.pesquisar(
					filtroLogradouroTipo, LogradouroTipo.class.getName());

			if (logradourosTipos != null && !logradourosTipos.isEmpty()) {
				// Titulo do Logradouro Foi Encontrado
				Iterator logradouroTipoIterator = logradourosTipos.iterator();

				logradouroTipo = (LogradouroTipo) logradouroTipoIterator.next();

			} else {
				logradouroTipo = new LogradouroTipo();
			}

		}

		Short indicadorDeUso = null;

		if (pesquisarActionForm.get("indicadorUso") != null
				&& !pesquisarActionForm.get("indicadorUso").equals("")) {

			indicadorDeUso = new Short(""
					+ pesquisarActionForm.get("indicadorUso"));
		}

		// seta os parametros que serão mostrados no relatório

		logradouroParametros.setMunicipio(municipio);
		logradouroParametros.setId(idLogradouro == null ? null : new Integer(
				idLogradouro));
		logradouroParametros.setNome(""
				+ pesquisarActionForm.get("nomeLogradouro"));
		logradouroParametros.setLogradouroTitulo(logradouroTitulo);
		logradouroParametros.setLogradouroTipo(logradouroTipo);
		logradouroParametros.setIndicadorUso(indicadorDeUso);

		// Fim da parte que vai mandar os parametros para o relatório

			// cria uma instância da classe do relatório
			RelatorioManterLogradouro relatorioManterLogradouro = new RelatorioManterLogradouro(
					(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

			relatorioManterLogradouro.addParametro("logradouros", logradouros);
			relatorioManterLogradouro.addParametro("logradouroParametros",
					logradouroParametros);

			// chama o metódo de gerar relatório passando o código da analise
			// como parâmetro
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorioManterLogradouro.addParametro("tipoFormatoRelatorio", Integer
					.parseInt(tipoRelatorio));
			try {
				retorno = processarExibicaoRelatorio(relatorioManterLogradouro,
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
