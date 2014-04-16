package gcom.gui.util.tabelaauxiliar.indicador;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * @author Rômulo Aurélio
 *
 */
public class ExibirFiltrarTabelaAuxiliarIndicadorAction extends GcomAction{
	
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
				.findForward("filtrarTabelaAuxiliarIndicador");

		//Cria a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		String tela=null;
		
		if(httpServletRequest.getParameter("tela")!=null){
		tela = httpServletRequest.getParameter("tela");
		sessao.setAttribute("tela",tela);
		}else{
			if(sessao.getAttribute("tela")!=null){
				tela =(String) sessao.getAttribute("tela"); 	
			}
			
		}
		
		
		//      Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;
		
		pesquisarActionForm.set("atualizar","1");
		
		SistemaParametro sistemaParametro = (SistemaParametro) fachada
				.pesquisarParametrosDoSistema();

		sessao.setAttribute("nomeSistema", sistemaParametro.getNomeEmpresa());

		String descricao = "Descrição";
		int tamMaxCampoDescricao = 40;
		String indicadorNegocio = null;
		
		

		DadosTelaTabelaAuxiliarIndicador dados = (DadosTelaTabelaAuxiliarIndicador) DadosTelaTabelaAuxiliarIndicador
				.obterDadosTelaTabelaAuxiliar(tela);

		sessao.setAttribute("dados", dados);
		sessao.setAttribute("titulo", dados.getTitulo());
		
		if (tela.equalsIgnoreCase("quadraPerfil")) {
			descricao = "Descricao";
			indicadorNegocio = "Indicador Baixa Renda";
			
		}
		
		
		sessao.setAttribute("tabela", dados.getTabelaAuxiliarAbstrata());
		sessao.setAttribute("pacoteNomeObjeto", dados.getTabelaAuxiliarAbstrata().getClass().getName());
		sessao.setAttribute("funcionalidadeTabelaAuxiliarIndicadorInserir",
				dados.getFuncionalidadeTabelaAuxInserir());
		sessao.setAttribute("funcionalidadeTabelaAuxiliarIndicadorFiltrar",
				dados.getFuncionalidadeTabelaIndicadorFiltrar());
		sessao.setAttribute("nomeParametroFuncionalidade", dados
				.getNomeParametroFuncionalidade());
		sessao.setAttribute("descricao",descricao);
		sessao.setAttribute("indicadorNegocio",indicadorNegocio);
		sessao.setAttribute("indicadorBaixaRenda","sim");
		sessao.setAttribute("tamMaxCampoDescricao", tamMaxCampoDescricao);
		
		httpServletRequest.setAttribute("tela",tela);

		return retorno;
	}
 




}
