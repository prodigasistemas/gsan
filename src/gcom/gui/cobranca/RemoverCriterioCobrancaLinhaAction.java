package gcom.gui.cobranca;

import gcom.cobranca.CobrancaCriterioLinha;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * processamento para remover a linha do criterio da cobrança
 * 
 * @author Sávio Luiz
 * @date 03/05/2006
 */
public class RemoverCriterioCobrancaLinhaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = null;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		//CriterioCobrancaActionForm criterioCobrancaActionForm = (CriterioCobrancaActionForm) actionForm;


		String tipoRetorno = httpServletRequest.getParameter("tipoRetorno");
		if (tipoRetorno != null && !tipoRetorno.equals("")) {
			// se o tipo de retorno for igual a inserir
			// retorna para o jsp de inserir criterio cobranca
			if (tipoRetorno.equals("inserir")) {
				retorno = actionMapping.findForward("inserirCriterioCobranca");
			}
			// se o tipo de retorno for igual a atualizar
			// retorna para o jsp de atualizar criterio cobranca
			if (tipoRetorno.equals("atualizar")) {
				retorno = actionMapping
						.findForward("atualizarCriterioCobranca");
			}
		}

		Collection colecaoCobrancaCriterioLinha = (Collection) sessao
				.getAttribute("colecaoCobrancaCriterioLinha");

		Collection colecaoCobrancaCriterioLinhaRemovidas = null;
		if (sessao.getAttribute("colecaoCobrancaCriterioLinhaRemovidas") != null
				&& !sessao
						.getAttribute("colecaoCobrancaCriterioLinhaRemovidas")
						.equals("")) {
			colecaoCobrancaCriterioLinhaRemovidas = (Collection) sessao
					.getAttribute("colecaoCobrancaCriterioLinhaRemovidas");
		} else {
			colecaoCobrancaCriterioLinhaRemovidas = new ArrayList();
		}

		Iterator iteratorCobrancaCriterioLinha = colecaoCobrancaCriterioLinha
				.iterator();
		String codigoCriterioCobrancaLinha = httpServletRequest
				.getParameter("codigoCobrancaCriterioLinha");
		
		String[] arrayCodigo = codigoCriterioCobrancaLinha.split(",");
		String codigoImovelPerfil = arrayCodigo[0];
		String codigoCategoria = arrayCodigo[1];
		
		while (iteratorCobrancaCriterioLinha.hasNext()) {
			CobrancaCriterioLinha cobrancaCriterioLinha = (CobrancaCriterioLinha) iteratorCobrancaCriterioLinha
					.next();
			if (cobrancaCriterioLinha.getImovelPerfil().getId().equals(new Integer(codigoImovelPerfil))
					&& cobrancaCriterioLinha.getCategoria().getId().equals(new Integer(codigoCategoria))) {
				iteratorCobrancaCriterioLinha.remove();
				if (cobrancaCriterioLinha.getId() != null
						&& !cobrancaCriterioLinha.getId().equals("")) {
					colecaoCobrancaCriterioLinhaRemovidas
							.add(cobrancaCriterioLinha);
				}

			}
		}

		sessao.setAttribute("colecaoCobrancaCriterioLinha",
				colecaoCobrancaCriterioLinha);
		sessao.setAttribute("colecaoCobrancaCriterioLinhaRemovidas",
				colecaoCobrancaCriterioLinhaRemovidas);

		return retorno;
	}
}
