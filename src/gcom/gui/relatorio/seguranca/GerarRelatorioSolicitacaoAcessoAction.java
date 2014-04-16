package gcom.gui.relatorio.seguranca;

import gcom.gui.ActionServletException;
import gcom.gui.seguranca.acesso.usuario.FiltrarSolicitacaoAcessoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.seguranca.FiltrarRelatorioSolicitacaoAcessoHelper;
import gcom.relatorio.seguranca.RelatorioSolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1093] Gerar Relatório Solicitação de Acesso
 * 
 * @author Hugo Leonardo
 *
 * @date 22/11/2010
 */

public class GerarRelatorioSolicitacaoAcessoAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		   
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		FiltrarSolicitacaoAcessoActionForm form = 
			(FiltrarSolicitacaoAcessoActionForm) sessao.getAttribute("filtroForm");
		
		FiltrarRelatorioSolicitacaoAcessoHelper filtroHelper = this.setFiltroHelper(form);
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		 
		TarefaRelatorio relatorio = new RelatorioSolicitacaoAcesso((Usuario)
				(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		relatorio.addParametro("filtroSolicitacaoAcesso", filtroHelper);
		
		String periodo = "";
		if(filtroHelper.getDataInicial() != null && filtroHelper.getDataFinal() != null){
			
			periodo = filtroHelper.getDataInicial() + " a " + filtroHelper.getDataFinal();
		}
		relatorio.addParametro("periodo", periodo);
		
		try {	
			
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
						httpServletResponse, actionMapping);
	
		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");
	
			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");
	
		} catch (RelatorioVazioException ex1) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
		}
			
			return retorno;
		}
	
	/**
	 * Preenche objeto FiltrarRelatorioSolicitacaoAcessoHelper
	 * 
	 * @author Hugo Leonardo
	 * @date 23/11/2010
	 * 
	 * @param FiltrarSolicitacaoAcessoActionForm
	 * @return FiltrarRelatorioSolicitacaoAcessoHelper
	 */
	private FiltrarRelatorioSolicitacaoAcessoHelper setFiltroHelper(FiltrarSolicitacaoAcessoActionForm form) {
		
		FiltrarRelatorioSolicitacaoAcessoHelper filtroHelper = new FiltrarRelatorioSolicitacaoAcessoHelper();
		
		// funcionário solicitante
		if(Util.verificarNaoVazio(form.getIdFuncionarioSolicitante())){
			
			filtroHelper.setIdFuncionarioSolicitante(form.getIdFuncionarioSolicitante());
		}
		
		//Período Inicial e Período Final
		if (form.getDataInicial() != null && !form.getDataInicial().equals("")
				&& form.getDataFinal() != null && !form.getDataFinal().equals("")){

			filtroHelper.setDataInicial(form.getDataInicial());
			filtroHelper.setDataFinal(form.getDataFinal());
		}
		
		// funcionário responsável
		if(Util.verificarNaoVazio(form.getIdFuncionarioSuperior())){
			
			filtroHelper.setIdFuncionarioSolicitante(form.getIdFuncionarioSuperior());
		}
		
		// Empresa
		if(Util.verificarNaoVazio(form.getIdEmpresa())){
			
			filtroHelper.setIdEmpresa(form.getIdEmpresa());
		}
		
		// funcionário usuário
		if(Util.verificarNaoVazio(form.getIdFuncionario())){
	
			filtroHelper.setIdFuncionario(form.getIdFuncionario());
		}
		
		// Nome Usuário
		if(Util.verificarNaoVazio(form.getNomeUsuario())){
	
			filtroHelper.setNomeUsuario(form.getNomeUsuario());
		}
		
		// Unidade de Lotação
		if(Util.verificarNaoVazio(form.getIdLotacao())){
			
			filtroHelper.setIdLotacao(form.getIdLotacao());
		}

		// Situação
		if (form.getIdsSituacao() != null && !form.getIdsSituacao().equals("-1") ){
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getIdsSituacao();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}
			if(colecao.size() > 0){
				filtroHelper.setIdsSituacao(colecao);
			}
		}

		return filtroHelper;
	}

	
}
