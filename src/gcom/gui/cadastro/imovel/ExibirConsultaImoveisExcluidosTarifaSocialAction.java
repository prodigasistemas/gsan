package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.bean.ImovelRelatorioHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultaImoveisExcluidosTarifaSocialAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        ActionForward retorno = actionMapping.findForward("consultaImoveisExcluidosTarifaSocial");

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        Collection imoveis = (Collection)httpServletRequest.getAttribute("collectionImoveis");
        //pega o cliente usuario e seta no objeto p apresentação na tela de manter
        if(imoveis != null && !imoveis.isEmpty()){
        	Iterator iterator = imoveis.iterator();
        	ImovelRelatorioHelper imovelRelatorioHelper = null;
        	while(iterator.hasNext()){
        		imovelRelatorioHelper = (ImovelRelatorioHelper)iterator.next(); 
        		Collection clientes = (Collection) imovelRelatorioHelper.getClientes();
        		Iterator iteratorCliente = clientes.iterator();
        		ImovelRelatorioHelper imovelRelatorioHelperCliente = null;
        		while(iteratorCliente.hasNext()){
        			imovelRelatorioHelperCliente = (ImovelRelatorioHelper)iteratorCliente.next();
        			if(imovelRelatorioHelperCliente.getClienteRelacaoTipo().trim().equalsIgnoreCase(ConstantesSistema.CLIENTE_RELACAO_TIPO_USUARIO_EXTENSO)){
        				imovelRelatorioHelper.setClienteNome(imovelRelatorioHelperCliente.getClienteNome());
        				imovelRelatorioHelper.setClienteCpf(imovelRelatorioHelperCliente.getClienteCpf());
        				imovelRelatorioHelper.setClienteRelacaoTipo(imovelRelatorioHelperCliente.getClienteRelacaoTipo());
        				imovelRelatorioHelper.setClienteEmissaoOrgaoRg(imovelRelatorioHelperCliente.getClienteEmissaoOrgaoRg());
        				imovelRelatorioHelper.setClienteRg(imovelRelatorioHelperCliente.getClienteRg());
        				imovelRelatorioHelper.setClienteDataEmissaoOrgaoRg(imovelRelatorioHelperCliente.getClienteDataEmissaoOrgaoRg());
        				imovelRelatorioHelper.setClienteUf(imovelRelatorioHelperCliente.getClienteUf());
        				break;
        			}
        		}
        	}
        	//seta a colecao na sessao p uso do mater e do detalhar consulta
        	sessao.setAttribute("colecaoImoveisExcluidosTarifaSocial", imoveis);
            
        } else {
            throw new ActionServletException(
                    "atencao.pesquisa.nenhumresultado", null, "Imóvel");
        }

        return retorno;
    }

}
