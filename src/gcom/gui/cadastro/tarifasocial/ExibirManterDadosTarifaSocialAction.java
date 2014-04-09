package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author rodrigo
 */
public class ExibirManterDadosTarifaSocialAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        ActionForward retorno = actionMapping.findForward("manterImovel");

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);
        
        sessao.removeAttribute("tarifasocialDadoEconomeia");
        sessao.removeAttribute("cliente");        

        FiltroClienteImovel filtroClienteImovel = (FiltroClienteImovel) httpServletRequest
                .getAttribute("filtroImovelPreenchido");

        Collection imoveis = null;

        Fachada fachada = Fachada.getInstancia();

        sessao.removeAttribute("imoveisFiltrados");

        //filtroClienteImovel
        
        imoveis = fachada.pesquisarClienteImovel(filtroClienteImovel);
        
        if (!imoveis.isEmpty()) {
        	/*
            Iterator iteratorImoveis = imoveis.iterator();
            Collection imoveisSemDuplicar = new ArrayList();
            Collection imoveisLista = new ArrayList();
            Imovel imovel = null;
            while (iteratorImoveis.hasNext()) {
                ClienteImovelSimplificado clienteImovelSimplificado = (ClienteImovelSimplificado) iteratorImoveis
                        .next();
                if (clienteImovelSimplificado.getImovel() != null) {
                    if (!imoveisSemDuplicar.contains(clienteImovelSimplificado)) {
                        imovel = clienteImovelSimplificado.getImovel();
                        imoveisSemDuplicar.add(clienteImovelSimplificado);
                        imoveisLista.add(imovel);
                    }
                }
            }
            */
            sessao.setAttribute("imoveisFiltrados", imoveis);
        } else {
            throw new ActionServletException(
                    "atencao.pesquisa.nenhumresultado", null, "Imóvel");
        }

        return retorno;
    }
}
