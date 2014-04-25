package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action responsável por adicionar na coleção a relação entre o cliente imovel,
 * o cliente e a data de inicio da relação
 * 
 * @author Sávio Luiz
 * @created 16 de Maio de 2004
 */
public class AdicionarInserirImovelClienteAction extends GcomAction {

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
                .findForward("adicionarInserirImovelCliente");

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        DynaValidatorForm inserirImovelActionForm = (DynaValidatorForm) actionForm;

        Fachada fachada = Fachada.getInstancia();

        Collection imovelClientesNovos = null;

        if (sessao.getAttribute("imovelClientesNovos") != null) {
            imovelClientesNovos = (Collection) sessao
                    .getAttribute("imovelClientesNovos");
        } else {
            imovelClientesNovos = new ArrayList();
        }

        //instância um cliente

        Cliente cliente = new Cliente();

        //teste se o cliente ja foi pesquisado com enter

        if (inserirImovelActionForm.get("idCliente") != null) {

            //recupera o id do cliente
            String idCliente = (String) inserirImovelActionForm
                    .get("idCliente");
            //instância o filtro do cliente
            FiltroCliente filtroCliente = new FiltroCliente();

            //adiciona o parametro no filtro
            filtroCliente.adicionarParametro(new ParametroSimples(
                    FiltroCliente.ID, idCliente));

            //faz a pesquisa do cliente
            Collection clientesObjs = fachada.pesquisar(filtroCliente,
                    Cliente.class.getName());

            //recupera o cliente da coleção pesquisada
            if (!clientesObjs.isEmpty()) {
                cliente = (Cliente) clientesObjs.iterator().next();
            } else {
                throw new ActionServletException("atencao.naocadastrado", null,
                        "Cliente");
            }

        }

        //inicializa o tipo do cliente imovel
        ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();

        //recupera id do tipo do cliente imovel
        clienteRelacaoTipo.setId((Integer) inserirImovelActionForm
                .get("tipoClienteImovel"));
        //recupera a descricao do tipo do cliente imovel
        clienteRelacaoTipo.setDescricao((String) inserirImovelActionForm
                .get("textoSelecionado"));

        //inicializa o cliente imovel
        ClienteImovel clienteImovel = new ClienteImovel(new Date(), null, null,
                cliente, clienteRelacaoTipo);
        
        //Coloca a data de ultima alteração para identificar o objeto
        clienteImovel.setUltimaAlteracao(new Date());

        if (!imovelClientesNovos.contains(clienteImovel)) {
        	
            //verifica se o tipo do cliente é usuário ou é responsável
            if ((clienteImovel.getClienteRelacaoTipo().getId().intValue() == ConstantesSistema.CLIENTE_IMOVEL_TIPO_USUARIO.intValue())) {
            	//verifica se ja existe cliente do tipo usuário
            	if(inserirImovelActionForm.get("idClienteImovelUsuario") == null || inserirImovelActionForm.get("idClienteImovelUsuario").equals("")) {
            		sessao.setAttribute("idClienteImovelUsuario", cliente.getId().toString());
                    inserirImovelActionForm.set("idClienteImovelUsuario",
                            cliente.getId().toString());
                    //adiciona o cliente imovel na coleção de  imovelClientesNovos
                    imovelClientesNovos.add(clienteImovel);                
            	}else{	
            		throw new ActionServletException(
                            "atencao.ja_cadastradado.cliente_imovel_usuario");
                }
            } else if (clienteImovel.getClienteRelacaoTipo().getId().intValue() == ConstantesSistema.CLIENTE_IMOVEL_TIPO_RESPONSAVEL.intValue()) {
                if (inserirImovelActionForm
                        .get("idClienteImovelResponsavel") == null
                        || inserirImovelActionForm.get(
                                "idClienteImovelResponsavel").equals("")) {
                    inserirImovelActionForm.set("idClienteImovelResponsavel",
                            cliente.getId().toString());

                    sessao.setAttribute(
                            "idClienteImovelResponsavel", cliente.getId()
                            .toString());
                    //inserirImovelActionForm.set();
                    //adiciona o cliente imovel na coleção de
                    // imovelClientesNovos
                    imovelClientesNovos.add(clienteImovel);	
                }else{
            		throw new ActionServletException(
                            "atencao.ja_cadastradado.cliente_imovel_responsavel");
                }
            }else{//para cliente do tipo proprietáio pode colocarq qtos quiser
            	   //adiciona o cliente imovel na coleção de
                // imovelClientesNovos
                imovelClientesNovos.add(clienteImovel);            	
            }

            inserirImovelActionForm.set("idCliente", null);
            inserirImovelActionForm.set("nomeCliente", null);

            //manda para a sessão a coleção de imovelClienteNovos
            sessao.setAttribute("imovelClientesNovos", imovelClientesNovos);

        } else {//cliente e tipo ja informados
            throw new ActionServletException(
                    "atencao.ja_cadastradado.cliente_imovel");
        }

        return retorno;
    }

}
