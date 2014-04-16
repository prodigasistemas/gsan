package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pre- processamento para registrar leituras e anormalidades
 * 
 * @author Sávio Luiz
 */
public class ExibirRegistrarLeiturasAnormalidadesAction extends GcomAction {
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

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("registrarLeiturasAnormalidades");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Criação das coleções
		Collection faturamentosGrupos = null;

		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo(FiltroFaturamentoGrupo.DESCRICAO);

		faturamentosGrupos = fachada.pesquisar(filtroFaturamentoGrupo,
				FaturamentoGrupo.class.getName());

		if (faturamentosGrupos == null || faturamentosGrupos.isEmpty()) {
			// Nenhuma faturamento grupo cadastrada
			new ActionServletException("atencao.pesquisa.nenhumresultado",
					null, "faturamento grupo");
		}

		httpServletRequest.setAttribute("faturamentosGrupos",
				faturamentosGrupos);
		
		httpServletRequest.setAttribute("movimentoCelular",
				"2");

		return retorno;
	}
}
