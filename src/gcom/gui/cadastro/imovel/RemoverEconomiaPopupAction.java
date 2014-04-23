package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.imovel.ImovelEconomia;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que remove a o objeto selecionado de cliente imovel economia que está
 * sem o imovel economia
 * 
 * @author Sávio Luiz
 * @created 20 de Maio de 2004
 */
public class RemoverEconomiaPopupAction extends GcomAction {
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

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        //Obtém o action form
        EconomiaPopupActionForm economiaPopupActionForm = (EconomiaPopupActionForm) actionForm;

        ActionForward retorno = null;

        //verifica se o botão do form é diferente de vazio
        if (economiaPopupActionForm.getBotaoAdicionar() != null
                && !economiaPopupActionForm.getBotaoAdicionar().equals("")) {
            //verifica se o botão tem o valor atualizar
            //se tiver volta para o popup atualizar
            if (economiaPopupActionForm.getBotaoAdicionar().equals("atualizar") ||
                economiaPopupActionForm.getBotaoAdicionar().equals("ATUALIZAR")) {
                retorno = actionMapping.findForward("atualizarEconomiaPopup");
            }
            //verifica se o botão tem o valor inserir
            //se tiver volta para o popup inserir
            if (economiaPopupActionForm.getBotaoAdicionar().equals("inserir") ||
                economiaPopupActionForm.getBotaoAdicionar().equals("INSERIR")) {
                retorno = actionMapping.findForward("inserirEconomiaPopup");
            }
        }

      //  Fachada fachada = Fachada.getInstancia();

        //Cria variaveis
        Collection colecaoClientesImoveisEconomia = (Collection) sessao
                .getAttribute("colecaoClientesImoveisEconomia");
                        
        Collection colecaoClientesImoveisEconomiaRemovidas = null;
        
        if (sessao.getAttribute("colecaoClientesImoveisEconomiaRemovidas")!= null){
        	colecaoClientesImoveisEconomiaRemovidas = (Collection) 
        		sessao.getAttribute("colecaoClientesImoveisEconomiaRemovidas");
        }else{
        	colecaoClientesImoveisEconomiaRemovidas = new ArrayList();
        }

        //Cria variaveis
        ImovelEconomia imovelEconomia = (ImovelEconomia) sessao
                .getAttribute("imovelEconomia");

        //atribui os valores q vem pelo request as variaveis
        String[] clientesImoveis = economiaPopupActionForm
                .getIdRegistrosRemocao();

        //instancia cliente
        ClienteImovelEconomia clienteImovelEconomia = null;

        Collection colecaoClientesImoveisEconomiaFimRelacao = new ArrayList();

        if (imovelEconomia != null && !imovelEconomia.equals("")) {
            Collection clienteImovelEconomias = (Collection) imovelEconomia
                    .getClienteImovelEconomias();
            Iterator clienteImovelEconomiaIterator = clienteImovelEconomias
                    .iterator();

            while (clienteImovelEconomiaIterator.hasNext()) {
                clienteImovelEconomia = (ClienteImovelEconomia) clienteImovelEconomiaIterator
                        .next();
                for (int i = 0; i < clientesImoveis.length; i++) {
                    if (clienteImovelEconomia.getUltimaAlteracao().getTime() == Long
                            .parseLong(clientesImoveis[i])) {

                        if (clienteImovelEconomia.getId() != null
                                && !clienteImovelEconomia.getId().equals("")) {
                            //caso seja um cliente imóvel economia da base
                            // então vai para o
                            //exibirManterFimRelacaoClienteImovel para colocar
                            // o motivo do fim da relação
                            retorno = actionMapping
                                    .findForward("exibirManterFimRelacaoClienteImovel");
                            colecaoClientesImoveisEconomiaFimRelacao
                                    .add(clienteImovelEconomia);

                            if (clienteImovelEconomia.getClienteRelacaoTipo()
                                    .getId().shortValue() == ClienteRelacaoTipo.USUARIO
                                    .shortValue()) {
                                economiaPopupActionForm
                                        .setIdClienteImovelUsuario(null);
                            }

                            colecaoClientesImoveisEconomiaRemovidas.add(clienteImovelEconomia);
                            
                            //clienteImovelEconomiaIterator.remove();
                            
                            sessao.setAttribute("colecaoClientesImoveisEconomiaFimRelacao",
                            		colecaoClientesImoveisEconomiaFimRelacao);
                            
                            sessao.setAttribute("colecaoClientesImoveisEconomiaRemovidas",colecaoClientesImoveisEconomiaRemovidas);
                            
                        } else {

                            clienteImovelEconomiaIterator.remove();

                            if (clienteImovelEconomia.getClienteRelacaoTipo()
                                    .getId().shortValue() == ClienteRelacaoTipo.USUARIO
                                    .shortValue()) {
                                economiaPopupActionForm
                                        .setIdClienteImovelUsuario(null);
                            }

                        }
                    }
                }
            }

            sessao.setAttribute("imovelEconomia", imovelEconomia);

        }

        if (colecaoClientesImoveisEconomia != null
                && !colecaoClientesImoveisEconomia.equals("")) {

            Iterator clienteImovelEconomiaIterator = colecaoClientesImoveisEconomia
                    .iterator();

            while (clienteImovelEconomiaIterator.hasNext()) {
                clienteImovelEconomia = (ClienteImovelEconomia) clienteImovelEconomiaIterator
                        .next();
                for (int i = 0; i < clientesImoveis.length; i++) {
                    if (clienteImovelEconomia.getUltimaAlteracao().getTime() == Long
                            .parseLong(clientesImoveis[i])) {
                        clienteImovelEconomiaIterator.remove();

                        if (clienteImovelEconomia.getClienteRelacaoTipo()
                                .getId().shortValue() == ClienteRelacaoTipo.USUARIO
                                .shortValue()) {
                            economiaPopupActionForm
                                    .setIdClienteImovelUsuario(null);
                        }
                    }
                }
            }

        }
        if (!colecaoClientesImoveisEconomia.isEmpty()){
			economiaPopupActionForm.setColecaoCliente("SIM");
		} else {
			economiaPopupActionForm.setColecaoCliente(null);
		}

        return retorno;
    }

}
