package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLeituraSituacao;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidade;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraSituacao;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Roberta Costa
 * @created 28 de Dezembro de 2005
 */
public class ExibirFiltrarExcecoesLeiturasConsumosAnormalidadeAction extends GcomAction {
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
        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("filtrarExcecoesLeiturasConsumosAnormalidade");

        HttpSession sessao = httpServletRequest.getSession(false);

        Fachada fachada = Fachada.getInstancia();
        
        sessao.removeAttribute("analisado");
		sessao.removeAttribute("gerarAviso");
		sessao.removeAttribute("gerarOS");
		sessao.removeAttribute("gerarRelatorio");
		sessao.removeAttribute("colecaoImoveisGerarAviso");
		sessao.removeAttribute("colecaoImoveisGerarOS");
		sessao.removeAttribute("colecaoImoveisGerarRelatorio");
        
        //LeituraConsumoActionForm leituraConsumoActionForm = (LeituraConsumoActionForm) actionForm;
        
//      Leitura Anormalidade
		FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
		filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
				FiltroLeituraAnormalidade.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroLeituraAnormalidade.setCampoOrderBy(FiltroLeituraAnormalidade.DESCRICAO);
		Collection<LeituraAnormalidade> leituraAnormalidades = fachada
				.pesquisar(filtroLeituraAnormalidade, LeituraAnormalidade.class
						.getName());
		if (leituraAnormalidades.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado");
		} else {
			httpServletRequest.setAttribute("leituraAnormalidades",
					leituraAnormalidades);
		}

		// Anormalidade Consumo
		FiltroConsumoAnormalidade filtroConsumoAnormalidade = new FiltroConsumoAnormalidade();
		filtroConsumoAnormalidade.adicionarParametro(new ParametroSimples(
				FiltroConsumoAnormalidade.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroConsumoAnormalidade.setCampoOrderBy(FiltroConsumoAnormalidade.DESCRICAO);
		Collection<ConsumoAnormalidade> consumoAnormalidades = fachada
				.pesquisar(filtroConsumoAnormalidade, ConsumoAnormalidade.class
						.getName());
		if (consumoAnormalidades.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado");
		} else {
			httpServletRequest.setAttribute("consumoAnormalidades",
					consumoAnormalidades);
		}
		
		//Situação da leitura atual
		FiltroLeituraSituacao filtroLeituraSituacao = new FiltroLeituraSituacao();
		filtroLeituraSituacao.adicionarParametro(new ParametroSimples(FiltroLeituraSituacao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroLeituraSituacao.setCampoOrderBy(FiltroLeituraSituacao.DESCRICAO);
		
		Collection<LeituraSituacao> colecaoLeituraSituacaoAtual = 
			fachada.pesquisar(filtroLeituraSituacao, LeituraSituacao.class.getName());
		
		if (colecaoLeituraSituacaoAtual == null || colecaoLeituraSituacaoAtual.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Situação da leitura atual");
		} else {
			httpServletRequest.setAttribute("colecaoLeituraSituacaoAtual",colecaoLeituraSituacaoAtual);
		}

        return retorno;
    }
}
