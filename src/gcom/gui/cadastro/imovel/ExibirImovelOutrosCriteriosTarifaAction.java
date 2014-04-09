package gcom.gui.cadastro.imovel;

//import gcom.cadastro.imovel.FiltroCategoria;
//import gcom.fachada.Fachada;
import gcom.cadastro.tarifasocial.FiltroRendaTipo;
import gcom.cadastro.tarifasocial.FiltroTarifaSocialCartaoTipo;
import gcom.cadastro.tarifasocial.FiltroTarifaSocialExclusaoMotivo;
import gcom.cadastro.tarifasocial.RendaTipo;
import gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo;
import gcom.cadastro.tarifasocial.TarifaSocialExclusaoMotivo;
import gcom.fachada.Fachada;
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
 * [UC0092] - Inserir Categoria
 * Action responsável pela pre-exibição da pagina de inserir Categoria
 * @author 	Roberta Costa
 * @created 22 de Dezembro de 2005
 */
public class ExibirImovelOutrosCriteriosTarifaAction extends GcomAction {
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
        ActionForward retorno = actionMapping.findForward("filtrarImovelDadosTarifaSocial");

        Fachada fachada = Fachada.getInstancia();
        
        FiltroTarifaSocialExclusaoMotivo filtroTarifaSocialExclusaoMotivo = new FiltroTarifaSocialExclusaoMotivo();
        filtroTarifaSocialExclusaoMotivo.adicionarParametro(new ParametroSimples(FiltroTarifaSocialExclusaoMotivo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
        Collection colecaoMotivoExclusao = fachada.pesquisar(filtroTarifaSocialExclusaoMotivo, TarifaSocialExclusaoMotivo.class.getName());
        
        httpServletRequest.setAttribute("colecaoMotivoExclusao", colecaoMotivoExclusao);
        
        FiltroTarifaSocialCartaoTipo filtroTarifaSocialCartaoTipo = new FiltroTarifaSocialCartaoTipo();
        filtroTarifaSocialCartaoTipo.adicionarParametro(new ParametroSimples(FiltroTarifaSocialCartaoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
        Collection colecaoCartaoTipo = fachada.pesquisar(filtroTarifaSocialCartaoTipo, TarifaSocialCartaoTipo.class.getName());
        
        httpServletRequest.setAttribute("colecaoCartaoTipo", colecaoCartaoTipo);
        
        FiltroRendaTipo filtroRendaTipo = new FiltroRendaTipo();
        filtroRendaTipo.adicionarParametro(new ParametroSimples(FiltroRendaTipo.INDICADOR_USO, RendaTipo.class.getName()));
        Collection colecaoRendaTipo = fachada.pesquisar(filtroRendaTipo, RendaTipo.class.getName());
        
        httpServletRequest.setAttribute("colecaoRendaTipo", colecaoRendaTipo);
        
        return retorno;
    }
}
