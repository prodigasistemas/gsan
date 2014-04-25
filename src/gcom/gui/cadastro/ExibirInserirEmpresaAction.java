package gcom.gui.cadastro;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

/**
 * @author Arthur Carvalho
 * @created 14 de maio de 2008
 */
public class ExibirInserirEmpresaAction extends GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);

		ActionForward retorno = actionMapping.findForward("inserirEmpresa");

		InserirEmpresaActionForm inserirEmpresaActionForm = (InserirEmpresaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();	
		
		List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa = new ArrayList();
		
		if (sessao.getAttribute("colecaoEmpresaCobrancaFaixa") != null
				&& !sessao.getAttribute("colecaoEmpresaCobrancaFaixa").equals("")){
			colecaoEmpresaCobrancaFaixa = (List<EmpresaCobrancaFaixa>) sessao.getAttribute("colecaoEmpresaCobrancaFaixa");
		}

		if (httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").equals("sim")) {

			inserirEmpresaActionForm
					.setIndicadorEmpresaPrincipal(ConstantesSistema.INDICADOR_USO_DESATIVO);

			inserirEmpresaActionForm.setIndicadorEmpresaCobranca(""
					+ ConstantesSistema.INDICADOR_USO_DESATIVO);
			
			inserirEmpresaActionForm
					.setIndicadorLeitura(ConstantesSistema.SIM);
			
		}
		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirEmpresaActionForm.setDescricao("");

			if (inserirEmpresaActionForm.getDescricao() == null
					|| inserirEmpresaActionForm.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;

				FiltroEmpresa filtroEmpresa = new FiltroEmpresa();

				filtroEmpresa.setCampoOrderBy(FiltroEmpresa.ID);

				colecaoPesquisa = fachada.pesquisar(filtroEmpresa,
						Empresa.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(

					"atencao.pesquisa.nenhum_registro_tabela", null, "Empresa");

				} else {

					sessao.setAttribute("colecaoEmpresa", colecaoPesquisa);
				}
				// Coleção de Empresa
				filtroEmpresa = new FiltroEmpresa();

				filtroEmpresa.setCampoOrderBy(FiltroEmpresa.ID);

				Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
						Empresa.class.getName());

				sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
			}
		}

		// Adicionar EmpresaCobrancaFaixa
		if (httpServletRequest.getParameter("adicionarFaixa") != null
				&& httpServletRequest.getParameter("adicionarFaixa").equals("sim")
				&& inserirEmpresaActionForm.getQuantidadeMinimaContas() != null
				&& !inserirEmpresaActionForm.getQuantidadeMinimaContas().equals("")
				&& inserirEmpresaActionForm.getPercentualDaFaixa() != null
				&& !inserirEmpresaActionForm.getPercentualDaFaixa().equals("")) {

			Integer quantidadeMinimaContas = new Integer(inserirEmpresaActionForm.getQuantidadeMinimaContas());
			BigDecimal percentualFaixa = Util.formatarMoedaRealparaBigDecimal(inserirEmpresaActionForm.getPercentualDaFaixa());
			
			if (colecaoEmpresaCobrancaFaixa != null && !colecaoEmpresaCobrancaFaixa.isEmpty()) {
				Iterator iterator = colecaoEmpresaCobrancaFaixa.iterator();
				
				while(iterator.hasNext()) {
					EmpresaCobrancaFaixa empresaCobrancaFaixa = (EmpresaCobrancaFaixa) iterator.next();
					
					if (empresaCobrancaFaixa.getNumeroMinimoContasFaixa().compareTo(quantidadeMinimaContas) >= 0) {
						throw new ActionServletException(
								"atencao.quantidade.maior.que.quantidade.anterior", null, "Quantidade Mínima de Contas");
					}
				}
			}
			
			EmpresaCobrancaFaixa empresaCobrancaFaixa = new EmpresaCobrancaFaixa();
			empresaCobrancaFaixa.setNumeroMinimoContasFaixa(quantidadeMinimaContas);
			empresaCobrancaFaixa.setPercentualFaixa(percentualFaixa);
			
			colecaoEmpresaCobrancaFaixa.add(empresaCobrancaFaixa);
			sessao.setAttribute("colecaoEmpresaCobrancaFaixa", colecaoEmpresaCobrancaFaixa);

			inserirEmpresaActionForm.setPercentualDaFaixaInformado("sim");
			inserirEmpresaActionForm.setQuantidadeMinimaContas("");
			inserirEmpresaActionForm.setPercentualDaFaixa("");
		}
		
		// Remover EmpresaCobrancaFaixa
		if (httpServletRequest.getParameter("removerEmpresaCobrancaFaixa") != null
				&& !httpServletRequest.getParameter("removerEmpresaCobrancaFaixa").equals("")) {
			
			Integer indice = new Integer(httpServletRequest.getParameter("removerEmpresaCobrancaFaixa"));
        	
        	if (colecaoEmpresaCobrancaFaixa != null
        			&& !colecaoEmpresaCobrancaFaixa.isEmpty()
        			&& colecaoEmpresaCobrancaFaixa.size() >= indice) {
        		colecaoEmpresaCobrancaFaixa.remove(indice-1);
				sessao.setAttribute("colecaoEmpresaCobrancaFaixa", colecaoEmpresaCobrancaFaixa);
				if (colecaoEmpresaCobrancaFaixa != null
						&& !colecaoEmpresaCobrancaFaixa.isEmpty()) {
					inserirEmpresaActionForm.setPercentualDaFaixaInformado("sim");
				} else {
					inserirEmpresaActionForm.setPercentualDaFaixaInformado("");
				}
        	}
        	
		}

		// Limpar Formulário
		if (httpServletRequest.getParameter("limparFaixa") != null
				&& httpServletRequest.getParameter("limparFaixa").equals("sim")) {

			sessao.removeAttribute("colecaoEmpresaCobrancaFaixa");
			inserirEmpresaActionForm.setPercentualDaFaixaInformado("");
			
		}
		
		return retorno;
	}
}
