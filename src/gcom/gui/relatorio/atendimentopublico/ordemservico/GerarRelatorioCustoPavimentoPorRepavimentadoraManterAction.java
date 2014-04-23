package gcom.gui.relatorio.atendimentopublico.ordemservico;

import gcom.cadastro.imovel.FiltroPavimentoCalcada;
import gcom.cadastro.imovel.FiltroPavimentoRua;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoCalcada;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoRua;
import gcom.fachada.Fachada;
import gcom.gui.atendimentopublico.ordemservico.FiltrarCustoPavimentoPorRepavimentadoraActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.ordemservico.RelatorioManterCustoPavimentoPorRepavimentadora;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
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
 * [UC1107] Manter Custo de Pavimento por Repavimentadora
 * 
 * @author Hugo Leonardo
 *
 * @date 12/01/2011
 */

public class GerarRelatorioCustoPavimentoPorRepavimentadoraManterAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		   
		httpServletRequest.setAttribute("telaSucessoRelatorio", true);
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Form
		FiltrarCustoPavimentoPorRepavimentadoraActionForm form = (FiltrarCustoPavimentoPorRepavimentadoraActionForm) actionForm;
		
		Collection<UnidadeRepavimentadoraCustoPavimentoRua> colecaoCustoPavimentoRua = (Collection<UnidadeRepavimentadoraCustoPavimentoRua>) sessao.getAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoRua");
		
		Collection<UnidadeRepavimentadoraCustoPavimentoCalcada> colecaoCustoPavimentoCalcada = (Collection<UnidadeRepavimentadoraCustoPavimentoCalcada>) sessao.getAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoCalcada");
		
		// cria uma instância da classe do relatório
		RelatorioManterCustoPavimentoPorRepavimentadora relatorioManterCustoPavimentoPorRepavimentadora = new RelatorioManterCustoPavimentoPorRepavimentadora(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		relatorioManterCustoPavimentoPorRepavimentadora.addParametro("colecaoCustoPavimentoRua", colecaoCustoPavimentoRua);
		relatorioManterCustoPavimentoPorRepavimentadora.addParametro("colecaoCustoPavimentoCalcada", colecaoCustoPavimentoCalcada);
		
		sessao.removeAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoRua");
		sessao.removeAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoCalcada");
		
		String unidadeRepavimentadora = " -- ";
		
		if(form.getIdUnidadeRepavimentadora() != null && !form.getIdUnidadeRepavimentadora().equals("-1")){
			
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, form.getIdUnidadeRepavimentadora()));
			
			Collection<UnidadeOrganizacional> colecaoUnidadeRepavimentadora = Fachada.getInstancia().pesquisar(
					filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			UnidadeOrganizacional unidade = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeRepavimentadora);
			unidadeRepavimentadora = unidade.getDescricao();
		}
		
		relatorioManterCustoPavimentoPorRepavimentadora.addParametro("unidadeRepavimentadora", unidadeRepavimentadora);
		
		String pavimentoRua = " -- ";
		if(form.getIdTipoPavimentoRua() != null && !form.getIdTipoPavimentoRua().equals("-1")){
			
			FiltroPavimentoRua filtroPavimentoRua = new FiltroPavimentoRua();
			filtroPavimentoRua.adicionarParametro(new ParametroSimples(
					FiltroPavimentoRua.ID, form.getIdTipoPavimentoRua()));
			
			Collection<PavimentoRua> colecaoPavimentoRua = Fachada.getInstancia().pesquisar(
					filtroPavimentoRua, PavimentoRua.class.getName());
			
			PavimentoRua pavimento = (PavimentoRua) Util.retonarObjetoDeColecao(colecaoPavimentoRua);
			pavimentoRua = pavimento.getDescricao();
		}
		
		relatorioManterCustoPavimentoPorRepavimentadora.addParametro("pavimentoRua", pavimentoRua);
		
		String vigenciaRua = " -- ";
		if(form.getDataVigenciaInicialPavimentoRua() != null && !form.getDataVigenciaInicialPavimentoRua().equals("") &&
				form.getDataVigenciaFinalPavimentoRua() != null && !form.getDataVigenciaFinalPavimentoRua().equals("")){
			
			vigenciaRua = form.getDataVigenciaInicialPavimentoRua() + " a " + form.getDataVigenciaFinalPavimentoRua();
		}
		
		relatorioManterCustoPavimentoPorRepavimentadora.addParametro("vigenciaRua", vigenciaRua);
		
		String situacaoVigenciaRua = " -- ";
		if(form.getSituacaoVigenciaRua() != null){
			
			if(form.getSituacaoVigenciaRua().equals("1")){
				
				situacaoVigenciaRua = "Vigente";
			}else if(form.getSituacaoVigenciaRua().equals("2")){
				
				situacaoVigenciaRua = "Não Vigente";
			}else{
				
				situacaoVigenciaRua = "Todos";
			}
		}
		
		relatorioManterCustoPavimentoPorRepavimentadora.addParametro("situacaoVigenciaRua", situacaoVigenciaRua);
		
		String pavimentoCalcada = " -- ";
		if(form.getIdTipoPavimentoCalcada() != null && !form.getIdTipoPavimentoCalcada().equals("-1")){
			
			FiltroPavimentoCalcada filtroPavimentoCalcada = new FiltroPavimentoCalcada();
			filtroPavimentoCalcada.adicionarParametro(new ParametroSimples(
					FiltroPavimentoCalcada.ID, form.getIdTipoPavimentoCalcada()));
			
			Collection<PavimentoCalcada> colecaoPavimentoCalcada = Fachada.getInstancia().pesquisar(
					filtroPavimentoCalcada, PavimentoCalcada.class.getName());
			
			PavimentoCalcada pavimento = (PavimentoCalcada) Util.retonarObjetoDeColecao(colecaoPavimentoCalcada);
			pavimentoCalcada = pavimento.getDescricao();
		}
		
		relatorioManterCustoPavimentoPorRepavimentadora.addParametro("pavimentoCalcada", pavimentoCalcada);
		
		String vigenciaCalcada = " -- ";
		if(form.getDataVigenciaInicialPavimentoCalcada() != null && !form.getDataVigenciaInicialPavimentoCalcada().equals("") &&
				form.getDataVigenciaFinalPavimentoCalcada() != null && !form.getDataVigenciaFinalPavimentoCalcada().equals("")){
			
			vigenciaCalcada = form.getDataVigenciaInicialPavimentoCalcada() + " a " + form.getDataVigenciaFinalPavimentoCalcada();
		}
		
		relatorioManterCustoPavimentoPorRepavimentadora.addParametro("vigenciaCalcada", vigenciaCalcada);
		
		String situacaoVigenciaCalcada = " -- ";
		if(form.getSituacaoVigenciaCalcada() != null){
			
			if(form.getSituacaoVigenciaCalcada().equals("1")){
				
				situacaoVigenciaCalcada = "Vigente";
			}else if(form.getSituacaoVigenciaRua().equals("2")){
				
				situacaoVigenciaCalcada = "Não Vigente";
			}else{
				
				situacaoVigenciaCalcada = "Todos";
			}
		}
		
		relatorioManterCustoPavimentoPorRepavimentadora.addParametro("situacaoVigenciaCalcada", situacaoVigenciaCalcada);
		
		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterCustoPavimentoPorRepavimentadora.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterCustoPavimentoPorRepavimentadora,
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
