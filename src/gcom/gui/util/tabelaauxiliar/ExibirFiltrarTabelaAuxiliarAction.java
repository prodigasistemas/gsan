package gcom.gui.util.tabelaauxiliar;

import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirFiltrarTabelaAuxiliarAction extends GcomAction {
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

		ActionForward retorno = actionMapping
				.findForward("filtrarTabelaAuxiliar");

		//Cria a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		String tela = httpServletRequest.getParameter("tela");
		sessao.setAttribute("tela",tela);
		//tempo da sessão
		//sessao.setMaxInactiveInterval(1000);

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;
		
		pesquisarActionForm.set("indicadorAtualizar","1");
		
		String descricao = "Descrição";
		String id = "Código";

		int tamMaxCampoDescricao = 20;
		
		DadosTelaTabelaAuxiliar dados = DadosTelaTabelaAuxiliar
		.obterDadosTelaTabelaAuxiliar(tela);

		String primeiraVez = httpServletRequest.getParameter("menu");
		
		//Verifica se a chamada veio do menu 
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("atualizar", "1");
			pesquisarActionForm.set("tipoPesquisa",ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		}

		sessao.setAttribute("pacoteNomeObjeto", dados.getTabelaAuxiliar().getClass().getName());
		sessao.setAttribute("titulo", dados.getTitulo());
		sessao.setAttribute("funcionalidadeTabelaAuxiliarInserir",
				dados.getFuncionalidadeTabelaAuxInserir());
		sessao.setAttribute("funcionalidadeTabelaAuxiliarFiltrar",dados.getFuncionalidadeTabelaAuxFiltrar());
		sessao.setAttribute("id",id);
		sessao.setAttribute("descricao",descricao);
		sessao.setAttribute("tamMaxCampoDescricao", tamMaxCampoDescricao);
		
		
		//Adiciona o objeto no request
		httpServletRequest.setAttribute("tamMaxCampoDescricao", new Integer(
				tamMaxCampoDescricao));
		httpServletRequest.setAttribute("descricao", descricao);
		httpServletRequest.setAttribute("tela",tela);
		
		sessao.removeAttribute("voltar");
		sessao.removeAttribute("tipoPesquisa");
		
		
        return retorno;
    }

}
