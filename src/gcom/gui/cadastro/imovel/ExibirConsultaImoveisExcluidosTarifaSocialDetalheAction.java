package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.bean.ImovelRelatorioHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultaImoveisExcluidosTarifaSocialDetalheAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        ActionForward retorno = actionMapping.findForward("consultaImoveisExcluidosTarifaSocialDetalhe");

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);
        
        String idImovel = (String) httpServletRequest
        		.getParameter("idRegistroAtualizacao");
        
        Collection imoveis = (Collection)sessao.getAttribute("colecaoImoveisExcluidosTarifaSocial");
        //pega o cliente usuario e seta no objeto p apresentação na tela de manter
        Collection clientesProprietarios = new ArrayList();
        if(imoveis != null && !imoveis.isEmpty()){
        	Iterator iterator = imoveis.iterator();
        	ImovelRelatorioHelper imovelRelatorioHelper = null;
        	ImovelRelatorioHelper tarifaEconomia = null;
        	while(iterator.hasNext()){
        		imovelRelatorioHelper = (ImovelRelatorioHelper)iterator.next();
        		if(imovelRelatorioHelper.getTarifasEconomias() != null
        				&& !imovelRelatorioHelper.getTarifasEconomias().isEmpty()){
        			tarifaEconomia = (ImovelRelatorioHelper)imovelRelatorioHelper.getTarifasEconomias().iterator().next();
        		}
        		Collection clientes = (Collection) imovelRelatorioHelper.getClientes();
        		Iterator iteratorCliente = clientes.iterator();
        		ImovelRelatorioHelper imovelRelatorioHelperCliente = null;
        		if(imovelRelatorioHelper.getMatriculaImovel().toString().trim().equalsIgnoreCase(idImovel)){
	        		while(iteratorCliente.hasNext()){
	        			imovelRelatorioHelperCliente = (ImovelRelatorioHelper)iteratorCliente.next();
	        			if(imovelRelatorioHelperCliente.getClienteRelacaoTipo().trim().equalsIgnoreCase(ConstantesSistema.CLIENTE_RELACAO_TIPO_PROPRIETARIO_EXTENSO)){
	        				clientesProprietarios.add(imovelRelatorioHelperCliente);
	        			}
	        		}
//	        		seta a colecao na sessao p uso do mater e do detalhar consulta
	            	httpServletRequest.setAttribute("colecaoClienteProprietario", clientesProprietarios);
	            	httpServletRequest.setAttribute("imovel", imovelRelatorioHelper);
	            	httpServletRequest.setAttribute("tarifaEconomia", tarifaEconomia);
        		}
        	}
        } else {
            throw new ActionServletException(
                    "atencao.pesquisa.nenhumresultado", null, "Imóvel");
        }
        return retorno;
    }

}
