package gcom.gui.cobranca;

import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.CobrancaAcaoCronograma;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeCronograma;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Remove as categorias selecionadas na lista da funcionalidade Manter Categoria
 * 
 * @author Roberta Costa
 * @created 02 de Janeiro de 2006
 */
public class RemoverCobrancaAtividadeCronogramaAction extends GcomAction {
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

        CobrancaActionForm cobrancaActionForm = (CobrancaActionForm) actionForm;

        // Obtém os ids de remoção
        String[] ids = cobrancaActionForm.getIdRegistrosRemocao();

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Obtém a sessão
        //HttpSession sessao = httpServletRequest.getSession(false);

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //mensagem de erro quando o usuário tenta excluir sem ter selecionado nenhum registro
        if (ids == null || ids.length == 0) {
            throw new ActionServletException(
                    "atencao.registros.nao_selecionados");
        }
        
        FiltroCobrancaAcaoAtividadeCronograma filtroCobrancaAcaoAtividadeCronograma = new FiltroCobrancaAcaoAtividadeCronograma();
        filtroCobrancaAcaoAtividadeCronograma.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoCronograma");
        
        Collection colecaoAtividadeCronograma = null;
        for(int i = 0; i < (ids.length + 1); i++){
        	filtroCobrancaAcaoAtividadeCronograma.adicionarParametro(new ParametroSimples(
        			FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_ACAO, ids[i], ParametroSimples.CONECTOR_OR));        	
        }
        
        colecaoAtividadeCronograma = fachada.pesquisar(filtroCobrancaAcaoAtividadeCronograma,
        		CobrancaAcaoAtividadeCronograma.class.getName());
        
        Iterator iteratorAtividadeCronograma = colecaoAtividadeCronograma.iterator();
        CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = null;
        while(iteratorAtividadeCronograma.hasNext()){
        	cobrancaAcaoAtividadeCronograma = (CobrancaAcaoAtividadeCronograma) iteratorAtividadeCronograma.next();
        	
        	fachada.remover(cobrancaAcaoAtividadeCronograma);
        }
        
        fachada.remover(ids, CobrancaAcaoCronograma.class.getName(), null, null);

        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(httpServletRequest,
                    "Categoria(s) removida(s) com sucesso",
                    "Realizar outra manutenção de Categoria",
                    "exibirManterCategoriaAction.do");
        }

        return retorno;
    }
}
