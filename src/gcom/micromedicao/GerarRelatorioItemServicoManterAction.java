package gcom.micromedicao;


import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.micromedicao.RelatorioManterItemServico;
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
 * @author Rodrigo de Abreu Cabral
 * @version 1.0
 */

public class GerarRelatorioItemServicoManterAction extends
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

		FiltrarItemServicoActionForm form = (FiltrarItemServicoActionForm) actionForm;

		FiltroItemServico filtroItemServico = (FiltroItemServico) sessao
				.getAttribute("filtroItemServico");

		// Inicio da parte que vai mandar os parametros para o relatório

		ItemServico itemServicoParametros = new ItemServico();

		Short indicadordeUso = null;
		
		if(form.getIndicadorUso()!= null && !form.getIndicadorUso().equals("")){
			
			indicadordeUso = new Short ("" + form.getIndicadorUso());
			itemServicoParametros.setIndicadorUso(indicadordeUso);
		}
		
		
		// seta os parametros que serão mostrados no relatório
		
		if(form.getDescricao() != null && !form.getDescricao().equals("")){
			
			itemServicoParametros.setDescricao(form.getDescricao());
			
		}
		
		if(form.getDescricaoAbreviada() != null && !form.getDescricaoAbreviada().equals("")){
			
			itemServicoParametros.setDescricaoAbreviada(form.getDescricaoAbreviada());
		}
		
		
		Integer codigoConstanteCalculo = null;
		
		if(form.getCodigoConstanteCalculo()!= null && !form.getCodigoConstanteCalculo().equals("")){
			
			codigoConstanteCalculo = new Integer ("" + form.getCodigoConstanteCalculo());
			
			itemServicoParametros.setCodigoConstanteCalculo(codigoConstanteCalculo);
		}
		
		Long codigoItem = null;
		
		if(form.getCodigoItem()!= null && !form.getCodigoItem().equals("")){
			
			codigoItem = new Long ("" + form.getCodigoItem());
			
			itemServicoParametros.setCodigoItem(codigoItem);
		}
		
		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterItemServico relatorioManterItemServico = new RelatorioManterItemServico(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterItemServico.addParametro("filtroItemServico",
				filtroItemServico);
		relatorioManterItemServico.addParametro("itemServicoParametros",
				itemServicoParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterItemServico.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterItemServico,
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
