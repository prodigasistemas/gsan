package gcom.gui.util.tabelaauxiliar.indicador;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rômulo Aurélio
 */
public class ExibirInserirTabelaAuxiliarIndicadorAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
		ActionForm actionForm, HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping.findForward("inserirTabelaAuxiliarIndicador");

		// Pega o parametro passado no request
		// String tela = (String) httpServletRequest.getParameter("tela");

		// Declaração de objetos e tipos primitivos
		int tamMaxCampoDescricao = 40;
		
		// Cria a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		String tela = httpServletRequest.getParameter("tela");

		SistemaParametro sistemaParametro = 
			(SistemaParametro) this.getFachada().pesquisarParametrosDoSistema();

		DadosTelaTabelaAuxiliarIndicador dados = 
			(DadosTelaTabelaAuxiliarIndicador) DadosTelaTabelaAuxiliarIndicador.
				obterDadosTelaTabelaAuxiliar(tela);
		
		sessao.setAttribute("nomeSistema", sistemaParametro.getNomeEmpresa());
		sessao.setAttribute("indicadorNegocio", "Indicador Baixa Renda");
		sessao.setAttribute("dados", dados);
		sessao.setAttribute("titulo", dados.getTitulo());
		sessao.setAttribute("tabela", dados.getTabelaAuxiliarAbstrata());
		sessao.setAttribute("funcionalidadeTabelaAuxiliarIndicadorInserir",dados.getFuncionalidadeTabelaIndicadorInserir());
		sessao.setAttribute("nomeParametroFuncionalidade", dados.getNomeParametroFuncionalidade());

		// Adiciona o objeto no request
		httpServletRequest.setAttribute("tamMaxCampoDescricao", new Integer(tamMaxCampoDescricao));
		httpServletRequest.setAttribute("tela",tela);

		return retorno;
	}
}
