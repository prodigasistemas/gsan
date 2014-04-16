package gcom.gui.relatorio.operacional;

import gcom.fachada.Fachada;
import gcom.gui.operacional.FiltrarBaciaActionForm;
import gcom.operacional.Bacia;
import gcom.operacional.FiltroBacia;
import gcom.operacional.FiltroSistemaEsgoto;
import gcom.operacional.SistemaEsgoto;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.operacional.RelatorioManterBacia;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;
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
 * @author Arthur Carvalho
 * @version 1.0
 */

public class GerarRelatorioBaciaManterAction extends
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

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarBaciaActionForm filtrarBaciaActionForm = (FiltrarBaciaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		FiltroBacia filtroBacia= (FiltroBacia) sessao
				.getAttribute("filtroBacia");

		// Inicio da parte que vai mandar os parametros para o relatório

		Bacia baciaParametros = new Bacia();

		String id = null;

		String idBaciaPesquisar = (String) filtrarBaciaActionForm.getId();

		if (idBaciaPesquisar != null && !idBaciaPesquisar.equals("")) {
		id = idBaciaPesquisar;
		}

		Short indicadorDeUso = null;
		
		if(filtrarBaciaActionForm.getIndicadorUso() != null && !filtrarBaciaActionForm.getIndicadorUso().equals("")){
			
			indicadorDeUso = new Short(""
					+ filtrarBaciaActionForm.getIndicadorUso());

		}

		// seta os parametros que serão mostrados no relatório

		if(filtrarBaciaActionForm.getId() == null || filtrarBaciaActionForm.getId().equals("")){
			baciaParametros.setId(null);
		} else {
			baciaParametros.setId(new Integer (id));
		}
		
		baciaParametros.setDescricao(""
				+ filtrarBaciaActionForm.getDescricao());
		baciaParametros.setIndicadorUso(indicadorDeUso);
		
		if (filtrarBaciaActionForm.getSistemaEsgoto() != null && !filtrarBaciaActionForm.getSistemaEsgoto().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			FiltroSistemaEsgoto filtroSistemaEsgoto = new FiltroSistemaEsgoto();
			filtroSistemaEsgoto.adicionarParametro(new ParametroSimples(FiltroSistemaEsgoto.ID, filtrarBaciaActionForm.getSistemaEsgoto()));
			
			Collection colecaoSistemaEsgoto = fachada.pesquisar(filtroSistemaEsgoto, SistemaEsgoto.class.getName());
			
			if (colecaoSistemaEsgoto != null && !colecaoSistemaEsgoto.isEmpty()) {
				SistemaEsgoto sistemaEsgoto = (SistemaEsgoto) Util.retonarObjetoDeColecao(colecaoSistemaEsgoto);
				baciaParametros.setSistemaEsgoto(sistemaEsgoto);
			}
			
		}
		
		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterBacia relatorioManterBacia= new RelatorioManterBacia(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterBacia.addParametro("filtroBacia",
				filtroBacia);
		relatorioManterBacia.addParametro("baciaParametros",
				baciaParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterBacia.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterBacia,
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
