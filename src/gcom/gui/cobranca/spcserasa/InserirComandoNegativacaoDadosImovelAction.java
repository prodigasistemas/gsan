package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade validar as informações da 3° aba do processo de inserção
 * de um Comando de Negativação
 *
 * @author Ana Maria
 * @date 06/11/2007
 */
public class InserirComandoNegativacaoDadosImovelAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("");

        Fachada fachada = Fachada.getInstancia();
        
        InserirComandoNegativacaoActionForm inserirComandoNegativacaoActionForm = (InserirComandoNegativacaoActionForm) actionForm;
        
        //Pesquisa Cliente
        String idCliente = inserirComandoNegativacaoActionForm.getIdCliente();
        if(idCliente != null && !idCliente.equals("")){
      	
      	FiltroCliente filtroCliente = new FiltroCliente();  
          
      	filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));
          
          Collection colecaoCliente = fachada.pesquisar(
          		filtroCliente,Cliente.class.getName());
          
          if (colecaoCliente != null && !colecaoCliente.isEmpty()) {
          	
          	inserirComandoNegativacaoActionForm.setIdCliente(""
						+ ((Cliente) ((List) colecaoCliente).get(0)).getId());
          	inserirComandoNegativacaoActionForm.setDescricaoCliente(""
						+ ((Cliente) ((List) colecaoCliente).get(0)).getNome());
			} else {
				throw new ActionServletException("atencao.cliente.inexistente");
			}
        }
        
        return retorno;
	}

}
