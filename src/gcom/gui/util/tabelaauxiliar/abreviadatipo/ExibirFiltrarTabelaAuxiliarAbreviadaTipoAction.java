package gcom.gui.util.tabelaauxiliar.abreviadatipo;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.SistemaAbastecimento;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * <Esse componente serve para SetorAbastecimento e ZonaAbastecimento, sendo o tipo SistemaAbastecimento>
 * 
 * @author Administrador
 */
public class ExibirFiltrarTabelaAuxiliarAbreviadaTipoAction extends GcomAction {
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
				.findForward("filtrarTabelaAuxiliarAbreviadaTipo");

		//Cria a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		String tela = httpServletRequest.getParameter("tela");
		sessao.setAttribute("tela",tela);

		Fachada fachada = Fachada.getInstancia();

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;
		
		pesquisarActionForm.set("atualizar","1");
		
		if (pesquisarActionForm.get("tipoPesquisa") == null
				|| pesquisarActionForm.get("tipoPesquisa").equals("")) {

			pesquisarActionForm.set("tipoPesquisa",
					ConstantesSistema.TIPO_PESQUISA_INICIAL+"");

		}
		
		SistemaParametro sistemaParametro = (SistemaParametro) fachada
				.pesquisarParametrosDoSistema();
		
		sessao.setAttribute("nomeSistema", sistemaParametro.getNomeEmpresa());

		String descricao = "Descrição";
		String descricaoAbreviada = "Descrição Abreviada";

		int tamMaxCampoDescricao = 40;
		int tamMaxCampoDescricaoAbreviada = 3;

        String tituloTipo = null;
        Collection tipos = null;

		DadosTelaTabelaAuxiliarAbreviadaTipo dados = DadosTelaTabelaAuxiliarAbreviadaTipo
				.obterDadosTelaTabelaAuxiliar(tela);

		if (dados.getNomeParametroFuncionalidade().equals("setorAbastecimento")) {
			
			Collection colecaoObject = new ArrayList();	
			
			FiltroSistemaAbastecimento filtro = new FiltroSistemaAbastecimento();
			filtro.adicionarParametro(
				new ParametroSimples(
					FiltroSistemaAbastecimento.INDICADOR_USO, 
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoObject = 
				this.getFachada().pesquisar(filtro,SistemaAbastecimento.class.getName());
			
			sessao.setAttribute("colecaoObject", colecaoObject);
		}

		if (dados.getNomeParametroFuncionalidade().equals("zonaAbastecimento")) {
			
			Collection colecaoObject = new ArrayList();	
			
			FiltroSistemaAbastecimento filtro = new FiltroSistemaAbastecimento();
			filtro.adicionarParametro(
				new ParametroSimples(
					FiltroSistemaAbastecimento.INDICADOR_USO, 
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			
			colecaoObject = 
				this.getFachada().pesquisar(filtro,SistemaAbastecimento.class.getName());
			
			sessao.setAttribute("colecaoObject", colecaoObject);
		}
		

		sessao.setAttribute("dados", dados);
		sessao.setAttribute("titulo", dados.getTitulo());
		sessao.setAttribute("tabela", dados.getTabela());
		sessao.setAttribute("funcionalidadeTabelaAuxiliarAbreviadaTipoInserir",
				dados.getFuncionalidadeTabelaAuxInserir());
		sessao.setAttribute("nomeParametroFuncionalidade", dados
				.getNomeParametroFuncionalidade());
		sessao.setAttribute("descricao",descricao);
		sessao.setAttribute("descricaoAbreviada",descricaoAbreviada);
		sessao.setAttribute("tipoPesquisa","1");
		sessao.setAttribute("tamMaxCampoDescricao", tamMaxCampoDescricao);
		sessao.setAttribute("tamMaxCampoDescricaoAbreviada",tamMaxCampoDescricaoAbreviada);
        sessao.setAttribute("tituloTipo", tituloTipo);
        sessao.setAttribute("tipos", tipos);
        
		//funcionalidadeTabelaAuxiliarAbreviadaManter
		sessao.setAttribute("funcionalidadeTabelaAuxiliarAbreviadaTipoFiltrar", dados
				.getFuncionalidadeTabelaAuxFiltrar());
		sessao.setAttribute("pacoteNomeObjeto", dados.getTabelaAuxiliar().getClass().getName());
		
		
		//Adiciona o objeto no request
		httpServletRequest.setAttribute("tamMaxCampoDescricao", new Integer(
				tamMaxCampoDescricao));
		httpServletRequest.setAttribute("tamMaxCampoDescricaoAbreviada",
				new Integer(tamMaxCampoDescricaoAbreviada));
		httpServletRequest.setAttribute("descricao", descricao);
		httpServletRequest.setAttribute("descricaoAbreviada",
				descricaoAbreviada);

		return retorno;
	}

}
