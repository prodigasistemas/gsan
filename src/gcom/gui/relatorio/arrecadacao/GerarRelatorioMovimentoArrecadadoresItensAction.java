package gcom.gui.relatorio.arrecadacao;

import gcom.gui.arrecadacao.PesquisarItensMovimentoArrecadadorActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.arrecadacao.RelatorioManterMovimentoArrecadadoresItens;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;

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
 * @author Vinicius Medeiros
 * @version 1.0
 */

public class GerarRelatorioMovimentoArrecadadoresItensAction extends
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
		
		PesquisarItensMovimentoArrecadadorActionForm pesquisarItensMovimentoArrecadadorActionForm = (PesquisarItensMovimentoArrecadadorActionForm) actionForm;

        HttpSession sessao = getSessao(httpServletRequest);
        
		Collection colecaoArrecadadorMovimentoItemHelper = 
			pesquisarItensMovimentoArrecadadorActionForm.getColecaoArrecadadorMovimentoItem();
		
		String indicadorAceitacao =pesquisarItensMovimentoArrecadadorActionForm.getIndicadorAceitacao();
		String descricaoOcorrencia = pesquisarItensMovimentoArrecadadorActionForm.getDescricaoOcorrencia();
		String indicadorDiferencaValorMovimentoValorPagamento = 
			pesquisarItensMovimentoArrecadadorActionForm.getIndicadorDiferencaValorMovimentoValorPagamento();
		String idImovel = pesquisarItensMovimentoArrecadadorActionForm.getMatriculaImovel();
		String vlMovimento = pesquisarItensMovimentoArrecadadorActionForm.getValorMovimento();
		String vlPagamento = pesquisarItensMovimentoArrecadadorActionForm.getValorPagamento();
		String valorDadosMovimento = (String) sessao.getAttribute("valorDadosMovimento");
		String valorDadosPagamento = (String) sessao.getAttribute("valorDadosPagamento");
		String nomeArrecadador = (String) sessao.getAttribute("nomeArrecadador");
		
		String descricaoArrecadacaoForma = null;
		if(pesquisarItensMovimentoArrecadadorActionForm.getDescricaoFormaArrecadacao() == null || 
			pesquisarItensMovimentoArrecadadorActionForm.getDescricaoFormaArrecadacao().equals("-1")){	
			descricaoArrecadacaoForma = "";
		} else {
			descricaoArrecadacaoForma = pesquisarItensMovimentoArrecadadorActionForm.getDescricaoFormaArrecadacao();
		}
		
		
		// cria uma instância da classe do relatório
		RelatorioManterMovimentoArrecadadoresItens relatorioManterMovimentoArrecadadoresItens = 
			new RelatorioManterMovimentoArrecadadoresItens(
				this.getUsuarioLogado(httpServletRequest));
		
		relatorioManterMovimentoArrecadadoresItens.addParametro("colecaoArrecadadorMovimentoItemHelper",colecaoArrecadadorMovimentoItemHelper);
		relatorioManterMovimentoArrecadadoresItens.addParametro("indicadorAceitacao",indicadorAceitacao);
		relatorioManterMovimentoArrecadadoresItens.addParametro("descricaoOcorrencia",descricaoOcorrencia);
		relatorioManterMovimentoArrecadadoresItens.addParametro("descricaoArrecadacaoForma",descricaoArrecadacaoForma);
		relatorioManterMovimentoArrecadadoresItens.addParametro("indicadorDiferencaValorMovimentoValorPagamento",indicadorDiferencaValorMovimentoValorPagamento);
		relatorioManterMovimentoArrecadadoresItens.addParametro("idImovel",idImovel);
		relatorioManterMovimentoArrecadadoresItens.addParametro("vlMovimento",vlMovimento);
		relatorioManterMovimentoArrecadadoresItens.addParametro("vlPagamento",vlPagamento);
		relatorioManterMovimentoArrecadadoresItens.addParametro("valorDadosMovimento",valorDadosMovimento);
		relatorioManterMovimentoArrecadadoresItens.addParametro("valorDadosPagamento",valorDadosPagamento);
		relatorioManterMovimentoArrecadadoresItens.addParametro("nomeArrecadador",nomeArrecadador);
		
		sessao.removeAttribute("valorDadosMovimento");
		sessao.removeAttribute("valorDadosPagamento");

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterMovimentoArrecadadoresItens.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterMovimentoArrecadadoresItens,
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
