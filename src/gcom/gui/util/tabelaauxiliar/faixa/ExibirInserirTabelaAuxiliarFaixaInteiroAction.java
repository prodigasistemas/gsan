package gcom.gui.util.tabelaauxiliar.faixa;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rômulo Aurélio
 *
 */
public class ExibirInserirTabelaAuxiliarFaixaInteiroAction extends GcomAction {
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

		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping
				.findForward("inserirTabelaAuxiliarFaixaInteiro");

		// Pega o parametro passado no request
		// String tela = (String) httpServletRequest.getParameter("tela");

		// Declaração de objetos e tipos primitivos


		int tamMaxCampoMenorFaixa = 4;
		int tamMaxCampoMaiorFaixa = 4;
		int tamMaxCampoFaixaCompleta = 4;


		// Cria a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		String tela = httpServletRequest.getParameter("tela");
		// tempo da sessão
		// sessao.setMaxInactiveInterval(1000);
		// Adiciona os objetos na sessão

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		SistemaParametro sistemaParametro = (SistemaParametro) fachada
				.pesquisarParametrosDoSistema();

		sessao.setAttribute("nomeSistema", sistemaParametro.getNomeEmpresa());

		DadosTelaTabelaAuxiliarFaixaInteiro dados = (DadosTelaTabelaAuxiliarFaixaInteiro) DadosTelaTabelaAuxiliarFaixaInteiro
				.obterDadosTelaTabelaAuxiliar(tela);


		sessao.setAttribute("dados", dados);
		sessao.setAttribute("titulo", dados.getTitulo());
		sessao.setAttribute("tabela", dados.getTabelaAuxiliarAbstrata());
		sessao.setAttribute("funcionalidadeTabelaAuxiliarFaixaInteiroInserir",
				dados.getFuncionalidadeTabelaFaixaInteiroInserir());
		sessao.setAttribute("nomeParametroFuncionalidade", dados
				.getNomeParametroFuncionalidade());

		// Adiciona o objeto no request
		httpServletRequest.setAttribute("tamMaxCampoMenorFaixa", new Integer(
				tamMaxCampoMenorFaixa));
		httpServletRequest.setAttribute("tamMaxCampoMaiorFaixa", new Integer(
				tamMaxCampoMaiorFaixa));
		httpServletRequest.setAttribute("tamMaxCampoFaixaCompleta",
				new Integer(tamMaxCampoFaixaCompleta));
		
		//seta o parametro tela a parte de acesso a funcionalidade ou operação
		httpServletRequest.setAttribute("tela",tela);
		return retorno;
	}

}
