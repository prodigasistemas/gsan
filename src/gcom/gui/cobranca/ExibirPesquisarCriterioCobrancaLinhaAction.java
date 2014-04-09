package gcom.gui.cobranca;

import gcom.cobranca.CobrancaCriterioLinha;
import gcom.cobranca.FiltroCobrancaCriterioLinha;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Consultar Pagamento - Exibir
 * 
 * @author TIAGO MORENO - 31/01/2006
 */
public class ExibirPesquisarCriterioCobrancaLinhaAction extends GcomAction {

public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("pesquisarCriterioCobrancaLinha");

		// Instacia a fachada
		//Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		if (sessao.getAttribute("colecaoCobCritLinha") != null){
			sessao.removeAttribute("colecaoCobCritLinha");
		}
		
		if (sessao.getAttribute("descricaoCriterio") != null){
			sessao.removeAttribute("descricaoCriterio");
		}
		
		String criterioCobranca = httpServletRequest.getParameter("criterio");

		FiltroCobrancaCriterioLinha filtroCobrancaCriterioLinha = new FiltroCobrancaCriterioLinha();

		filtroCobrancaCriterioLinha.adicionarParametro(new ParametroSimples(
				FiltroCobrancaCriterioLinha.COBRANCA_CRITERIO_ID,
				criterioCobranca));
		filtroCobrancaCriterioLinha.setCampoOrderBy(
				FiltroCobrancaCriterioLinha.ID_IMOVEL_PERFIL,
					FiltroCobrancaCriterioLinha.ID_CATEGORIA);
		
		filtroCobrancaCriterioLinha.adicionarCaminhoParaCarregamentoEntidade("categoria");
		filtroCobrancaCriterioLinha.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
		filtroCobrancaCriterioLinha.adicionarCaminhoParaCarregamentoEntidade("cobrancaCriterio");

		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroCobrancaCriterioLinha, CobrancaCriterioLinha.class.getName());
		
		Collection<CobrancaCriterioLinha> colecaoCobCritLinha =(Collection) resultado
				.get("colecaoRetorno");
		
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		
		if (colecaoCobCritLinha != null && !colecaoCobCritLinha.isEmpty()) {
			sessao.setAttribute("colecaoCobCritLinha", colecaoCobCritLinha);
			PesquisarCriterioCobrancaLinhaActionForm pesquisarCriterioCobrancaLinhaActionForm = (PesquisarCriterioCobrancaLinhaActionForm) actionForm;
			pesquisarCriterioCobrancaLinhaActionForm.setCriterioDescricao(" " + colecaoCobCritLinha.iterator().next().getCobrancaCriterio().getDescricaoCobrancaCriterio());
			//sessao.setAttribute("descricaoCriterio", colecaoCobCritLinha.iterator().next().getCobrancaCriterio().getDescricaoCobrancaCriterio());
		} else {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Criterio Cobrança Linha");
		}

		return retorno;

	}}
