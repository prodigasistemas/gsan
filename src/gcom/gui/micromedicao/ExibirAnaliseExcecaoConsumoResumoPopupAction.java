package gcom.gui.micromedicao;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ControladorException;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action que define o pré-processamento da página de pesquisa de Localidade
 * 
 * @author Administrador
 * @created 27 de Maio de 2004
 */

public class ExibirAnaliseExcecaoConsumoResumoPopupAction extends GcomAction {
    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping
                .findForward("exibirAnaliseExcecaoConsumoResumoPopup");

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

       // LeituraConsumoActionForm leituraConsumoActionForm = (LeituraConsumoActionForm) actionForm;
        Fachada fachada = Fachada.getInstancia();

        String idImovel = httpServletRequest.getParameter("codigoImovel");
        
        FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
        filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
        filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO, "" + ClienteRelacaoTipo.USUARIO));
        
//        filtroClienteImovel.adicionarParametro( new ParametroSimples(FiltroClienteImovel.DATA_FIM_RELACAO, ""));
        
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
        
//        Collection colecaoCliente = fachada.pesquisarClienteImovel(filtroClienteImovel);
        
        Collection colecaoCliente = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
        
//        ClienteImovelSimplificado clienteImovel = (ClienteImovelSimplificado)colecaoCliente.iterator().next();
        
        ClienteImovel clienteImovel = new ClienteImovel();
        
        String nomeCliente = null;
        
        Iterator iteClieImovel = colecaoCliente.iterator();
        
        while ( iteClieImovel.hasNext() ){
        	
        	clienteImovel = (ClienteImovel)iteClieImovel.next();
        	
        	if ( clienteImovel.getDataFimRelacao() == null || clienteImovel.equals("") ){
        		
        		nomeCliente = clienteImovel.getCliente().getNome();
                sessao.setAttribute("nomeCliente", nomeCliente);
                       		
        	}
        
        }
        
//        ClienteImovel clienteImovel = (ClienteImovel)colecaoCliente.iterator().next();
        
        String imovelEndereco;
        
		try {
			imovelEndereco = fachada.pesquisarEnderecoFormatado(new Integer(idImovel));
	        sessao.setAttribute("imovelEndereco", imovelEndereco);
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		} catch (ControladorException e) {
			
			e.printStackTrace();
		}
        
        return retorno;
    }

}
