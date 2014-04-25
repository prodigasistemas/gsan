package gcom.gui.relatorio.cobranca;

import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroImovelSituacaoTipo;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSituacaoTipo;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cobranca.FiltroResolucaoDiretoria;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.cobranca.parcelamento.FiltroParcelamentoPerfil;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;
import gcom.fachada.Fachada;
import gcom.gui.cobranca.parcelamento.FiltrarPerfilParcelamentoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cobranca.RelatorioManterPerfilParcelamento;
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

public class GerarRelatorioPerfilParcelamentoManterAction extends
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

		FiltrarPerfilParcelamentoActionForm filtrarPerfilParcelamentoActionForm = (FiltrarPerfilParcelamentoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroParcelamentoPerfil filtroParcelamentoPerfil = (FiltroParcelamentoPerfil) sessao.getAttribute("filtroParcelamentoPerfil");

		// Inicio da parte que vai mandar os parametros para o relatório

		ParcelamentoPerfil parcelamentoPerfilParametros = new ParcelamentoPerfil();

		// Resolução de Diretoria
		String idResolucaoDiretoria = (String) filtrarPerfilParcelamentoActionForm.getResolucaoDiretoria();

		ResolucaoDiretoria resolucaoDiretoria = new ResolucaoDiretoria();

		if (idResolucaoDiretoria != null
				&& !idResolucaoDiretoria.equals("")
				&& !idResolucaoDiretoria.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();

			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
					FiltroResolucaoDiretoria.CODIGO, idResolucaoDiretoria));

			Collection colecaoResolucoesDiretorias = fachada.pesquisar(
					filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());

			if (colecaoResolucoesDiretorias != null
					&& !colecaoResolucoesDiretorias.isEmpty()) {

				Iterator colecaoResolucoesDiretoriasIterator = colecaoResolucoesDiretorias
						.iterator();

				resolucaoDiretoria = (ResolucaoDiretoria) colecaoResolucoesDiretoriasIterator
						.next();

			}
		}
		
		// Tipo da Situação do Imóvel
		String idImovelSituacaoTipo = (String) filtrarPerfilParcelamentoActionForm.getImovelSituacaoTipo();

		ImovelSituacaoTipo imovelSituacaoTipo = new ImovelSituacaoTipo();

		if (idImovelSituacaoTipo != null
				&& !idImovelSituacaoTipo.equals("")
				&& !idImovelSituacaoTipo.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			FiltroImovelSituacaoTipo filtroImovelSituacaoTipo = new FiltroImovelSituacaoTipo();

			filtroImovelSituacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroImovelSituacaoTipo.ID, idImovelSituacaoTipo));

			Collection colecaoImoveisSituacoesTipos = fachada.pesquisar(
					filtroImovelSituacaoTipo, ImovelSituacaoTipo.class.getName());

			if (colecaoImoveisSituacoesTipos != null
					&& !colecaoImoveisSituacoesTipos.isEmpty()) {

				Iterator colecaoImoveisSituacoesTiposIterator = colecaoImoveisSituacoesTipos
						.iterator();

				imovelSituacaoTipo = (ImovelSituacaoTipo) colecaoImoveisSituacoesTiposIterator
						.next();

			}
		}
		
		// Perfil do Imóvel
		String idImovelPerfil = (String) filtrarPerfilParcelamentoActionForm.getImovelPerfil();

		ImovelPerfil imovelPerfil = new ImovelPerfil();

		if (idImovelPerfil != null
				&& !idImovelPerfil.equals("")
				&& !idImovelPerfil.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();

			filtroImovelPerfil.adicionarParametro(new ParametroSimples(
					FiltroImovelPerfil.ID, idImovelPerfil));

			Collection colecaoImoveisPerfis = fachada.pesquisar(
					filtroImovelPerfil, ImovelPerfil.class.getName());

			if (colecaoImoveisPerfis != null
					&& !colecaoImoveisPerfis.isEmpty()) {

				Iterator colecaoImoveisPerfisIterator = colecaoImoveisPerfis
						.iterator();

				imovelPerfil = (ImovelPerfil) colecaoImoveisPerfisIterator
						.next();

			}
		}
		
		// Subcategoria
		String idSubcategoria = (String) filtrarPerfilParcelamentoActionForm.getSubcategoria();

		Subcategoria subcategoria = new Subcategoria();

		if (idSubcategoria != null
				&& !idSubcategoria.equals("")
				&& !idSubcategoria.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();

			filtroSubCategoria.adicionarParametro(new ParametroSimples(
					FiltroSubCategoria.ID, idSubcategoria));

			Collection colecaoSubcategorias = fachada.pesquisar(
					filtroSubCategoria, Subcategoria.class.getName());

			if (colecaoSubcategorias != null
					&& !colecaoSubcategorias.isEmpty()) {

				Iterator colecaoSubcategoriasIterator = colecaoSubcategorias
						.iterator();

				subcategoria = (Subcategoria) colecaoSubcategoriasIterator
						.next();

			}
		}

		// seta os parametros que serão mostrados no relatório

		parcelamentoPerfilParametros.setResolucaoDiretoria(resolucaoDiretoria);
		parcelamentoPerfilParametros.setImovelSituacaoTipo(imovelSituacaoTipo);
		parcelamentoPerfilParametros.setImovelPerfil(imovelPerfil);
		parcelamentoPerfilParametros.setSubcategoria(subcategoria);

		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterPerfilParcelamento relatorioManterPerfilParcelamento = new RelatorioManterPerfilParcelamento(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterPerfilParcelamento.addParametro(
				"filtroParcelamentoPerfil",
				filtroParcelamentoPerfil);
		relatorioManterPerfilParcelamento.addParametro(
				"parcelamentoPerfilParametros",
				parcelamentoPerfilParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterPerfilParcelamento.addParametro(
				"tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(
					relatorioManterPerfilParcelamento, tipoRelatorio,
					httpServletRequest, httpServletResponse, actionMapping);

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
