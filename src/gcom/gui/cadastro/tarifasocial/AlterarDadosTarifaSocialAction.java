package gcom.gui.cadastro.tarifasocial;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author rodrigo
 */
public class AlterarDadosTarifaSocialAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        ActionForward retorno = actionMapping.findForward("exibirAtualizarDadosTarifaSocial");
/*
        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        FiltroClienteImovel filtroClienteImovel = (FiltroClienteImovel) httpServletRequest
                .getAttribute("filtroImovelPreenchido");

        Collection imoveis = null;

        Fachada fachada = Fachada.getInstancia();

        sessao.removeAttribute("imoveisFiltrados");

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
            
            sessao.setAttribute("imoveisFiltrados", imoveis);
        } else {
            throw new ActionServletException(
                    "atencao.pesquisa.nenhumresultado", null, "Imóvel");
        }
*/
        return retorno;
    }
}
