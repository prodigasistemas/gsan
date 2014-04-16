package gcom.gui.cobranca;


import gcom.cadastro.empresa.Empresa;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;




/**
 * 
 * @author Arthur Carvalho
 * @date 14/08/2009
 */
public class ExibirAtualizarCobrancaGrupoAction extends GcomAction {

	/**
	 * Método responsavel por responder a requisicao
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
				.findForward("cobrancaGrupoAtualizar");

		AtualizarCobrancaGrupoActionForm form = (AtualizarCobrancaGrupoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();		

		HttpSession sessao = httpServletRequest.getSession(false);

		String id = null;
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null){
			id = httpServletRequest.getParameter("idRegistroAtualizacao");

		} else if (sessao.getAttribute("cobrancaGrupo") != null) {
			id = ((CobrancaGrupo) sessao.getAttribute("cobrancaGrupo")).getId().toString();
			
		} else {
			id = ((CobrancaGrupo) sessao.getAttribute("atualizarCobrancaGrupo")).getId().toString();
		}
		

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		if (id == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				id = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}

		CobrancaGrupo cobrancaGrupo = new CobrancaGrupo();
						
		if (id != null && !id.trim().equals("") && Integer.parseInt(id) > 0) {

			FiltroCobrancaGrupo filtroCobrancaGrupo= new FiltroCobrancaGrupo();
			filtroCobrancaGrupo.adicionarParametro(
				new ParametroSimples(FiltroCobrancaGrupo.ID, id));
			
			Collection colecaoCobrancaGrupo = fachada.pesquisar(
					filtroCobrancaGrupo, CobrancaGrupo.class.getName());
			
			if (colecaoCobrancaGrupo != null && !colecaoCobrancaGrupo.isEmpty()) {
				cobrancaGrupo = (CobrancaGrupo) Util.retonarObjetoDeColecao(colecaoCobrancaGrupo);
			}



			Collection<Empresa> collectionEmpresa = fachada.pesquisarEmpresasContratoServico();
			httpServletRequest.setAttribute("collectionEmpresa", collectionEmpresa);
			List colecaoHelper = null;
			Integer idEmpresa = null;

			if (httpServletRequest.getParameter("carregarContrato") != null && httpServletRequest.getParameter("carregarContrato").equals("NAO")) {
				// caso tenha sido selecionada nenhuma empresa, remover coleção de contratos
				sessao.removeAttribute("collectionContrato");
			} else if (httpServletRequest.getParameter("carregarContrato") != null && httpServletRequest.getParameter("carregarContrato").equals("SIM")){
				// caso tenha sido selecionada alguma empresa, carregar contratos
				idEmpresa = new Integer(httpServletRequest.getParameter("empresa"));
			} else if (cobrancaGrupo.getContratoEmpresaServico() != null) {
				idEmpresa = cobrancaGrupo.getContratoEmpresaServico().getEmpresa().getId();
				form.setEmpresa(cobrancaGrupo.getContratoEmpresaServico().getEmpresa().getId().toString());
				form.setIdNumeroContrato(cobrancaGrupo.getContratoEmpresaServico().getId().toString());
				form.setId(cobrancaGrupo.getId().toString());
				form.setDescricao(cobrancaGrupo.getDescricao());
				form.setDescricaoAbreviada(cobrancaGrupo.getDescricaoAbreviada());
				form.setAnoMesReferencia( Util.formatarAnoMesParaMesAno( cobrancaGrupo.getAnoMesReferencia() ));
				form.setIndicadorUso(cobrancaGrupo.getIndicadorUso());
				form.setEmailResponsavel(cobrancaGrupo.getEmailResponsavel());
				form.setIndicadorExecucaoAutomatica(cobrancaGrupo.getIndicadorExecucaoAutomatica());
			}
			
			if (idEmpresa != null) {
				colecaoHelper = new ArrayList();
				colecaoHelper = fachada.obterDadosItensContratoServico(idEmpresa);

				if(colecaoHelper != null && colecaoHelper.size() > 0){
					sessao.setAttribute("collectionContrato", colecaoHelper);
				}
			} else if (httpServletRequest.getParameter("carregarContrato") == null
					|| httpServletRequest.getParameter("carregarContrato").equals("")){
				form.setId(cobrancaGrupo.getId().toString());
				form.setDescricao(cobrancaGrupo.getDescricao());
				form.setDescricaoAbreviada(cobrancaGrupo.getDescricaoAbreviada());
				form.setAnoMesReferencia( Util.formatarAnoMesParaMesAno( cobrancaGrupo.getAnoMesReferencia() ));
				form.setIndicadorUso(cobrancaGrupo.getIndicadorUso());
				form.setEmailResponsavel(cobrancaGrupo.getEmailResponsavel());
				form.setIndicadorExecucaoAutomatica(cobrancaGrupo.getIndicadorExecucaoAutomatica());
				if (cobrancaGrupo.getContratoEmpresaServico() == null) {
					sessao.removeAttribute("collectionContrato");
					form.setEmpresa("");
					form.setIdNumeroContrato("");
				}
			}
			
			if (httpServletRequest.getParameter("carregarContrato") == null
					|| httpServletRequest.getParameter("carregarContrato").equals("")) {
				form.setEmailResponsavel(cobrancaGrupo.getEmailResponsavel());
				form.setIndicadorExecucaoAutomatica(cobrancaGrupo.getIndicadorExecucaoAutomatica());
			}
			

			sessao.setAttribute("atualizarCobrancaGrupo", cobrancaGrupo);
			if (sessao.getAttribute("colecaoCobrancaGrupo") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarCobrancaGrupoAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarCobrancaGrupoAction.do");
			}

		}
		
		return retorno;
	}
}
