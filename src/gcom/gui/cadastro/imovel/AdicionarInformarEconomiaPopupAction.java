package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.ImovelEconomia;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável para a adição da coleção de cliente imovel economia
 * 
 * @author Sávio Luiz
 * @created 19 de Maio de 2004
 */
public class AdicionarInformarEconomiaPopupAction extends GcomAction {
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

        //Cria a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //Obtém o action form
        EconomiaPopupActionForm economiaPopupActionForm = (EconomiaPopupActionForm) actionForm;

        ActionForward retorno = null;

        if (economiaPopupActionForm.getBotaoAdicionar() != null
                && !economiaPopupActionForm.getBotaoAdicionar().equals("")) {
            if (economiaPopupActionForm.getBotaoAdicionar().equals("atualizar")) {
                retorno = actionMapping.findForward("atualizarEconomiaPopup");
            }

            if (economiaPopupActionForm.getBotaoAdicionar().equals("inserir")) {

                //Prepara o retorno da Ação
                retorno = actionMapping.findForward("inserirEconomiaPopup");

                ImovelSubcategoria imovelSubCategoria = (ImovelSubcategoria) sessao
                        .getAttribute("imovelSubCategoria");

                Collection imovelEconomias = null;

                if (imovelSubCategoria.getImovelEconomias() == null
                        || imovelSubCategoria.getImovelEconomias().equals("")) {

                    imovelEconomias = new ArrayList();

                } else {
                    imovelEconomias = imovelSubCategoria.getImovelEconomias();
                }

                if (imovelEconomias.size() + 1 > imovelSubCategoria
                        .getQuantidadeEconomias()) {
                    throw new ActionServletException(
                            "atencao.quantidade_ultrapassada.imovel_economia",
                            null, new Integer(imovelSubCategoria
                                    .getImovelEconomias().size()).toString());
                }
            }
        }

        //Obtém a fachada
        Fachada fachada = Fachada.getInstancia();

        //Criação dos objetos
        String idCliente = null;
        Cliente cliente = null;
        Collection clientes = null;
        Collection colecaoClientesImoveisEconomia = null;

        ImovelEconomia imovelEconomia = (ImovelEconomia) sessao
                .getAttribute("imovelEconomia");

        //parametro que testa se dará um reload(true) ou nao(false)
        httpServletRequest.setAttribute("testeInserir", "false");

        //HashSet verifica se existe objeto igual na collection
        if (sessao.getAttribute("colecaoClientesImoveisEconomia") != null) {
            colecaoClientesImoveisEconomia = (Collection) sessao
                    .getAttribute("colecaoClientesImoveisEconomia");

        } else {
            colecaoClientesImoveisEconomia = new HashSet();
        }

        SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");

        Date dataInicioRelacao = null;

        try {
            dataInicioRelacao = dataFormato.parse(economiaPopupActionForm
                    .getDataInicioClienteImovelRelacao());

        } catch (ParseException ex) {
            dataInicioRelacao = null;
        }

        Date dataCorrente = null;
        Calendar a = Calendar.getInstance();

        a.set(Calendar.SECOND, 0);
        a.set(Calendar.MILLISECOND, 0);
        a.set(Calendar.HOUR, 0);
        a.set(Calendar.MINUTE, 0);
        dataCorrente = a.getTime();

        //caso a data de inicio da relação seja anterior que a data atual
        if (dataInicioRelacao.after(dataCorrente)) {
            throw new ActionServletException(
                    "atencao.data_inicio_relacao_cliente_imovel");
        }

        if (economiaPopupActionForm.getIdCliente() != null
                && !economiaPopupActionForm.getIdCliente().trim()
                        .equalsIgnoreCase("")) {
            FiltroCliente filtroCliente = new FiltroCliente();

            idCliente = (String) economiaPopupActionForm.getIdCliente();
            //Adiciona parametro
            filtroCliente.adicionarParametro(new ParametroSimples(
                    FiltroCliente.ID, idCliente));
            //Realiza a pesquisa de cliente
            clientes = fachada
                    .pesquisar(filtroCliente, Cliente.class.getName());
            if (clientes != null && !clientes.isEmpty()) {
                //O cliente foi encontrado
                cliente = new Cliente();
                cliente = (Cliente) clientes.iterator().next();

                //inicializa o tipo do cliente imovel
                ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();

                //recupera id do tipo do cliente imovel
                clienteRelacaoTipo.setId(new Integer(economiaPopupActionForm
                        .getClienteRelacaoTipo()));
                //recupera a descricao do tipo do cliente imovel
                clienteRelacaoTipo.setDescricao(economiaPopupActionForm
                        .getTextoSelecionadoEconomia());

                //inicializa o cliente imovel
                ClienteImovelEconomia clienteImovelEconomia = new ClienteImovelEconomia(
                        dataInicioRelacao, null, new Date(), null, cliente,
                        clienteRelacaoTipo, null);

                if (!colecaoClientesImoveisEconomia
                        .contains(clienteImovelEconomia)) {

                    if (imovelEconomia != null && !imovelEconomia.equals("")) {
                        if (imovelEconomia.getClienteImovelEconomias()
                                .contains(clienteImovelEconomia)) {
                            throw new ActionServletException(
                                    "atencao.ja_cadastradado.cliente_imovel_usuario");
                        }
                    }
                    //verifica se o tipo do cliente é usuário
                    if (clienteImovelEconomia.getClienteRelacaoTipo().getId()
                            .shortValue() == ClienteRelacaoTipo.USUARIO
                            .shortValue()) {
                        if (economiaPopupActionForm.getIdClienteImovelUsuario() == null
                                || (economiaPopupActionForm
                                        .getIdClienteImovelUsuario().equals(""))) {

                            economiaPopupActionForm
                                    .setIdClienteImovelUsuario(cliente.getId()
                                            .toString());
                            //adiciona o cliente imovel na coleção de
                            // imovelClientesNovos
                            colecaoClientesImoveisEconomia
                                    .add(clienteImovelEconomia);
                        } else {
                            throw new ActionServletException(
                                    "atencao.ja_cadastradado.cliente_imovel_usuario");
                        }

                    } else {

                        if (imovelEconomia != null) {
                            Collection clientesImoveisEconoiasCadastradas = imovelEconomia
                                    .getClienteImovelEconomias();
                            //cria um iterator da coleção de clientes imoveis
                            // economia
                            Iterator clienteImovelEconomiaCadastradaIterator = clientesImoveisEconoiasCadastradas
                                    .iterator();

                            //verifica cada cliente imovel economia
                            while (clienteImovelEconomiaCadastradaIterator
                                    .hasNext()) {
                                ClienteImovelEconomia clienteImovelEconomiaCadastrada = (ClienteImovelEconomia) clienteImovelEconomiaCadastradaIterator
                                        .next();

                                //se o cliente imovel economia da coleção for
                                // igual ao cliente imovel economia que
                                //está querendo cadastrar.
                                if (clienteImovelEconomiaCadastrada
                                        .getCliente().getId().equals(
                                                clienteImovelEconomia
                                                        .getCliente().getId())) {
                                    //verifica se esse cliente imovel economia
                                    // cadastrado é propietário
                                    if (clienteImovelEconomiaCadastrada
                                            .getClienteRelacaoTipo().getId()
                                            .shortValue() == ClienteRelacaoTipo.PROPRIETARIO
                                            .shortValue()) {
                                        throw new ActionServletException(
                                                "atencao.ja_cadastrado_cliente_imovel_propietario");
                                    }
                                }
                            }
                        }
                        colecaoClientesImoveisEconomia
                                .add(clienteImovelEconomia);

                    }

                    SimpleDateFormat dataFormatoAtual = new SimpleDateFormat(
                            "dd/MM/yyyy");
                    //joga em dataInicial a parte da data
                    String dataAtual = dataFormatoAtual.format(new Date());

                    economiaPopupActionForm
                            .setDataInicioClienteImovelRelacao(dataAtual);

                    //manda para a sessão a coleção de imovelClienteNovos
                    sessao.setAttribute("colecaoClientesImoveisEconomia",
                            colecaoClientesImoveisEconomia);

                } else {
                    throw new ActionServletException(
                            "atencao.ja_cadastradado.cliente_imovel");
                }

            } else {
                throw new ActionServletException(
                        "atencao.pesquisa.nenhumresultado", null, "cliente");
            }
        }
        
        if (!colecaoClientesImoveisEconomia.isEmpty()){
			economiaPopupActionForm.setColecaoCliente("SIM");
		} else {
			economiaPopupActionForm.setColecaoCliente(null);
		}

        httpServletRequest.setAttribute("i","i");
        return retorno;
    }
}
