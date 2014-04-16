package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoEsgotamento;
import gcom.faturamento.FaturamentoSituacaoMotivo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FiltroFaturamentoSituacaoMotivo;
import gcom.faturamento.FiltroFaturamentoSituacaoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Arthur Carvalho
 * @created 25 de agosto de 2008
 */
public class ExibirInserirLigacaoEsgotoEsgotamentoAction extends GcomAction {
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

		ActionForward retorno = actionMapping.findForward("inserirLigacaoEsgotoEsgotamento");

		//Tipo de Situação Especial de Faturamento
        FiltroFaturamentoSituacaoTipo filtroFaturamentoSituacaoTipo = new FiltroFaturamentoSituacaoTipo();
        
        filtroFaturamentoSituacaoTipo.setCampoOrderBy(FiltroLigacaoEsgotoEsgotamento.ID);
        
        filtroFaturamentoSituacaoTipo.adicionarParametro(new ParametroSimples(
        		FiltroFaturamentoSituacaoTipo.INDICADOR_USO,
        		ConstantesSistema.INDICADOR_USO_ATIVO));
       
        Collection colecaoPesquisa = null;
        
        //Retorna tipo de situacao especial de faturamento
       colecaoPesquisa = this.getFachada().pesquisar(filtroFaturamentoSituacaoTipo,
        		FaturamentoSituacaoTipo.class.getName());
        
        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
            //Nenhum registro na tabela leitura_anormalidade_consumo foi encontrado
            throw new ActionServletException(
                    "atencao.pesquisa.nenhum_registro_tabela", null,
                    "Tipo de Situação Especial de Faturamento");
        } else {
            httpServletRequest.setAttribute("colecaoTipoSituacaoEspecialFaturamento", colecaoPesquisa);
        }
        
        //Motivo de Situação Especial de Faturamento
        FiltroFaturamentoSituacaoMotivo filtroFaturamentoSituacaoMotivo = new FiltroFaturamentoSituacaoMotivo();
        
        filtroFaturamentoSituacaoMotivo.setCampoOrderBy(FiltroLigacaoEsgotoEsgotamento.ID);
        
        filtroFaturamentoSituacaoMotivo.adicionarParametro(new ParametroSimples(
        		FiltroFaturamentoSituacaoMotivo.INDICADOR_USO,
        		ConstantesSistema.INDICADOR_USO_ATIVO));
       
        //Retorna Motivo de situacao especial de faturamento
        colecaoPesquisa = this.getFachada().pesquisar(filtroFaturamentoSituacaoMotivo,
        		FaturamentoSituacaoMotivo.class.getName());
        
        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
            //Nenhum registro na tabela leitura_anormalidade_consumo foi encontrado
            throw new ActionServletException(
                    "atencao.pesquisa.nenhum_registro_tabela", null,
                    "Motivo da Situação Especial de Faturamento");
        } else {
            httpServletRequest.setAttribute("colecaoMotivoSituacaoEspecialFaturamento", colecaoPesquisa);
        }
        
        
		return retorno;
	}
}
