package gcom.gui.relatorio.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.financeiro.FiltroFinanciamentoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.financeiro.lancamento.FiltroLancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.gui.faturamento.debito.FiltrarTipoDebitoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.RelatorioManterTipoDebito;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
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

public class GerarRelatorioTipoDebitoManterAction extends
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

		FiltrarTipoDebitoActionForm filtrarTipoDebitoActionForm = (FiltrarTipoDebitoActionForm) actionForm;


		FiltroDebitoTipo filtroDebitoTipo = (FiltroDebitoTipo) sessao
				.getAttribute("filtroDebitoTipo");

		// Inicio da parte que vai mandar os parametros para o relatório
		Fachada fachada = Fachada.getInstancia();

		DebitoTipo debitoTipoParametros = new DebitoTipo();

		String id = null;
		

		Short indicadorGeracaoDebitoAuto = null;
		
		if(filtrarTipoDebitoActionForm.getIndicadorGeracaoDebitoAutomatica()!= null
				&& !filtrarTipoDebitoActionForm.getIndicadorGeracaoDebitoAutomatica().equals("")){
			
			indicadorGeracaoDebitoAuto = new Short ("" + filtrarTipoDebitoActionForm.getIndicadorGeracaoDebitoAutomatica());
		}
		
		Short indicadorGeracaoDebitoConta = null;
		
		if(filtrarTipoDebitoActionForm.getIndicadorGeracaoDebitoConta()!= null
				&& !filtrarTipoDebitoActionForm.getIndicadorGeracaoDebitoConta().equals("")){
			
			indicadorGeracaoDebitoConta = new Short ("" + filtrarTipoDebitoActionForm.getIndicadorGeracaoDebitoConta());
		}
		
		if (filtrarTipoDebitoActionForm.getFinanciamentoTipo() != null && !filtrarTipoDebitoActionForm.getFinanciamentoTipo().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			FiltroFinanciamentoTipo filtroFinanciamentoTipo = new FiltroFinanciamentoTipo();
			filtroFinanciamentoTipo.adicionarParametro(new ParametroSimples(FiltroFinanciamentoTipo.ID, filtrarTipoDebitoActionForm.getFinanciamentoTipo()));
			
			Collection colecaoFinanciamentoTipo = fachada.pesquisar(filtroFinanciamentoTipo, FinanciamentoTipo.class.getName());
			
			if (colecaoFinanciamentoTipo != null && !colecaoFinanciamentoTipo.isEmpty()) {
				FinanciamentoTipo financiamentoTipo = (FinanciamentoTipo) Util.retonarObjetoDeColecao(colecaoFinanciamentoTipo);
				debitoTipoParametros.setFinanciamentoTipo(financiamentoTipo);
			}
			
		}
		if (filtrarTipoDebitoActionForm.getLancamentoItemContabil() != null && !filtrarTipoDebitoActionForm.getLancamentoItemContabil().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			FiltroLancamentoItemContabil filtroLancamentoItemContabil = new FiltroLancamentoItemContabil();
			filtroLancamentoItemContabil.adicionarParametro(new ParametroSimples(FiltroLancamentoItemContabil.ID, filtrarTipoDebitoActionForm.getLancamentoItemContabil()));
			
			Collection colecaoLancamentoItemContabil = fachada.pesquisar(filtroLancamentoItemContabil, LancamentoItemContabil.class.getName());
			
			if (colecaoLancamentoItemContabil != null && !colecaoLancamentoItemContabil.isEmpty()) {
				LancamentoItemContabil lancamentoItemContabil = (LancamentoItemContabil) Util.retonarObjetoDeColecao(colecaoLancamentoItemContabil);
				debitoTipoParametros.setLancamentoItemContabil(lancamentoItemContabil);
			}
			
		}
		Short indicadorUso = null;
		
		if(filtrarTipoDebitoActionForm.getIndicadorUso()!= null && !filtrarTipoDebitoActionForm.getIndicadorUso().equals("")){
			
			indicadorUso = new Short ("" + filtrarTipoDebitoActionForm.getIndicadorUso());
		}
	
		BigDecimal valorLimiteDebitoInicial = null;
		
		if(filtrarTipoDebitoActionForm.getValorLimiteDebitoInicial() != null && !filtrarTipoDebitoActionForm.getValorLimiteDebitoInicial().equals("")){
			valorLimiteDebitoInicial = new BigDecimal (filtrarTipoDebitoActionForm.getValorLimiteDebitoInicial());
		}
		
		BigDecimal valorLimiteDebitoFinal = null;
		
		if(filtrarTipoDebitoActionForm.getValorLimiteDebitoFinal() != null && !filtrarTipoDebitoActionForm.getValorLimiteDebitoFinal().equals("")){
			valorLimiteDebitoFinal = new BigDecimal (filtrarTipoDebitoActionForm.getValorLimiteDebitoFinal());
		}
		
		String valorSugerido = null;
		
		if(filtrarTipoDebitoActionForm.getValorSugerido()!= null && !filtrarTipoDebitoActionForm.getValorSugerido().equals("")){
			
			valorSugerido = new String (filtrarTipoDebitoActionForm.getValorSugerido());
		}
		
		
		// seta os parametros que serão mostrados no relatório

		debitoTipoParametros.setId(id == null ? null : new Integer(
				id));
		debitoTipoParametros.setDescricao(""
				+ filtrarTipoDebitoActionForm.getDescricao());
		debitoTipoParametros.setDescricaoAbreviada(""+ filtrarTipoDebitoActionForm.getDescricaoAbreviada());
		debitoTipoParametros.setIndicadorGeracaoAutomatica( indicadorGeracaoDebitoAuto );
		debitoTipoParametros.setIndicadorGeracaoConta( indicadorGeracaoDebitoConta );
		debitoTipoParametros.setIndicadorUso(indicadorUso);
		
		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterTipoDebito relatorioManterTipoDebito= new RelatorioManterTipoDebito(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterTipoDebito.addParametro("filtroDebitoTipo",
				filtroDebitoTipo);
		relatorioManterTipoDebito.addParametro("valorLimiteDebitoInicial", valorLimiteDebitoInicial);
		relatorioManterTipoDebito.addParametro("valorLimiteDebitoFinal", valorLimiteDebitoFinal);
		relatorioManterTipoDebito.addParametro("valorSugerido", valorSugerido);
		relatorioManterTipoDebito.addParametro("debitoTipoParametros",
				debitoTipoParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterTipoDebito.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterTipoDebito,
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
