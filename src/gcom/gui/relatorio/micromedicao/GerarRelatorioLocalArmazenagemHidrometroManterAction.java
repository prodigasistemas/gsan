package gcom.gui.relatorio.micromedicao;

import gcom.gui.micromedicao.FiltrarLocalArmazenagemHidrometroActionForm;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.micromedicao.RelatorioManterLocalArmazenagemHidrometro;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
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

public class GerarRelatorioLocalArmazenagemHidrometroManterAction extends
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

		FiltrarLocalArmazenagemHidrometroActionForm filtrarLocalArmazenagemHidrometroActionForm = (FiltrarLocalArmazenagemHidrometroActionForm) actionForm;

		FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem= (FiltroHidrometroLocalArmazenagem) sessao
				.getAttribute("filtroHidrometroLocalArmazenagem");

		// Inicio da parte que vai mandar os parametros para o relatório

		HidrometroLocalArmazenagem hidrometroLocalArmazenagemParametros = new HidrometroLocalArmazenagem();

		String id = null;

		String idHidrometroLocalArmazenagemPesquisar = (String) filtrarLocalArmazenagemHidrometroActionForm.getId();

		if (idHidrometroLocalArmazenagemPesquisar != null && !idHidrometroLocalArmazenagemPesquisar.equals("")) {
		id = idHidrometroLocalArmazenagemPesquisar;
		}
		

		Short indicadorOficina= null;
		
		if(filtrarLocalArmazenagemHidrometroActionForm.getIndicadorOficina()!= null && !filtrarLocalArmazenagemHidrometroActionForm.getIndicadorOficina().equals("")){
			
			indicadorOficina = new Short ("" + filtrarLocalArmazenagemHidrometroActionForm.getIndicadorOficina());
		}
		
		Short indicadordeUso = null;
		
		if(filtrarLocalArmazenagemHidrometroActionForm.getIndicadorUso()!= null && !filtrarLocalArmazenagemHidrometroActionForm.getIndicadorUso().equals("")){
			
			indicadordeUso = new Short ("" + filtrarLocalArmazenagemHidrometroActionForm.getIndicadorUso());
		}
		
		
		// seta os parametros que serão mostrados no relatório

		hidrometroLocalArmazenagemParametros.setId(id == null ? null : new Integer(id));
		hidrometroLocalArmazenagemParametros.setDescricao(filtrarLocalArmazenagemHidrometroActionForm.getDescricao());
		hidrometroLocalArmazenagemParametros.setDescricaoAbreviada(filtrarLocalArmazenagemHidrometroActionForm.getDescricaoAbreviada());
		hidrometroLocalArmazenagemParametros.setIndicadorOficina(indicadorOficina);
		hidrometroLocalArmazenagemParametros.setIndicadorUso(indicadordeUso);
		
		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterLocalArmazenagemHidrometro relatorioManterLocalArmazenagemHidrometro = new RelatorioManterLocalArmazenagemHidrometro(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterLocalArmazenagemHidrometro.addParametro("filtroHidrometroLocalArmazenagem",
				filtroHidrometroLocalArmazenagem);
		relatorioManterLocalArmazenagemHidrometro.addParametro("hidrometroLocalArmazenagemParametros",
				hidrometroLocalArmazenagemParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterLocalArmazenagemHidrometro.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterLocalArmazenagemHidrometro,
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
