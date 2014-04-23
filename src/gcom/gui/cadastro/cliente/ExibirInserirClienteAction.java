package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * [UC0000] Inserir Cliente 
 *
 * @author Rodrigo, Roberta Costa
 * @date 28/06/2006
 */
public class ExibirInserirClienteAction extends GcomAction {

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

        // localiza o action no objeto actionmapping
        ActionForward retorno = actionMapping
                .findForward("inserirClienteNomeTipo");

        //obtém a instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //limpa a sessão
        sessao.removeAttribute("colecaoClienteFone");
        
        //Fachada
        Fachada fachada = Fachada.getInstancia();
        
        DynaValidatorForm clienteActionForm = (DynaValidatorForm) actionForm;
        
       
        
        //**********************************************************************
		// Autor: Ivan Sergio
		// Data: 23/07/2009
		// CRC2103
		// Guarda o endereco do Imovel para o caso em que o Inserir/Manter
		// cliente é invocado pelo Inserir/Manter Imovel como PopUp
		//**********************************************************************
		if (sessao.getAttribute("colecaoEnderecos") != null) {
			Collection colecaoEndereco = (Collection) sessao.getAttribute("colecaoEnderecos");
			Object obj = (Object) colecaoEndereco.iterator().next();
			if (obj instanceof Imovel) {
				sessao.setAttribute("colecaoEnderecosImovel", sessao.getAttribute("colecaoEnderecos"));
			}
		}
        
        sessao.removeAttribute("colecaoEnderecos");
        sessao.removeAttribute("foneTipos");
        sessao.removeAttribute("municipios");
        sessao.removeAttribute("colecaoResponsavelSuperiores");
        sessao.removeAttribute("InserirEnderecoActionForm");
        sessao.removeAttribute("InserirClienteActionForm");
        sessao.removeAttribute("tipoPesquisaRetorno");
        
        //Monta o Status do Wizard
        StatusWizard statusWizard = new StatusWizard(
                "inserirClienteWizardAction", "inserirClienteAction",
                "cancelarInserirClienteAction","exibirInserirClienteAction.do"
                );
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        1, "ClienteNomeTipoA.gif", "ClienteNomeTipoD.gif",
                        "exibirInserirClienteNomeTipoAction",
                        "inserirClienteNomeTipoAction"));
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        2, "ClientePessoaA.gif", "ClientePessoaD.gif",
                        "exibirInserirClientePessoaAction",
                        "inserirClientePessoaAction"));
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        3, "ClienteEnderecoA.gif", "ClienteEnderecoD.gif",
                        "exibirInserirClienteEnderecoAction",
                        "inserirClienteEnderecoAction"));
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        4, "ClienteTelefoneA.gif", "ClienteTelefoneD.gif",
                        "exibirInserirClienteTelefoneAction",
                        "inserirClienteTelefoneAction"));

        //manda o statusWizard para a sessao
        sessao.setAttribute("statusWizard", statusWizard);
        
        
        
        clienteActionForm.set("indicadorPessoaFisicaJuridica", ClienteTipo.INDICADOR_PESSOA_FISICA.toString());
        
        clienteActionForm.set("indicadorGeraFaturaAntecipada", ConstantesSistema.NAO.toString());
        
//        clienteActionForm.set("indicadorVencimentoMesSeguinte", ConstantesSistema.NAO.toString());
        
        if (httpServletRequest.getParameter("desfazer") != null) {
        	clienteActionForm.set("codigoCliente", null);
        	clienteActionForm.set("nome", null);
        	clienteActionForm.set("nomeAbreviado", null);
        	clienteActionForm.set("email", null);
        	clienteActionForm.set("tipoPessoa", null);
        	clienteActionForm.set("tipoPessoaAntes", null);
        	clienteActionForm.set("cpf", null);
        	clienteActionForm.set("rg", null);
        	clienteActionForm.set("dataEmissao", null);
        	clienteActionForm.set("idOrgaoExpedidor", null);
        	clienteActionForm.set("idUnidadeFederacao", null);
        	clienteActionForm.set("dataNascimento", null);
        	clienteActionForm.set("idProfissao", null);
        	clienteActionForm.set("idPessoaSexo", null);
        	clienteActionForm.set("nomeMae", null);
        	clienteActionForm.set("cnpj", null);
        	clienteActionForm.set("idRamoAtividade", null);
        	clienteActionForm.set("codigoClienteResponsavel", null);
        	clienteActionForm.set("nomeClienteResponsavel", null);
        	clienteActionForm.set("setorComercialOrigemCD", null);
        	clienteActionForm.set("enderecoCorrespondenciaSelecao", null);
        	clienteActionForm.set("ddd", null);
        	clienteActionForm.set("idTipoTelefone", null);
        	clienteActionForm.set("indicadorTelefonePadrao", null);
        	clienteActionForm.set("ramal", null);
        	clienteActionForm.set("contato", null);
        	clienteActionForm.set("telefone", null);
        	clienteActionForm.set("dddTelefone", null);
        	clienteActionForm.set("botaoClicado", null);
        	clienteActionForm.set("botaoAdicionar", null);
        	clienteActionForm.set("botaoRemover", null);
        	clienteActionForm.set("idMunicipio", null);
        	clienteActionForm.set("descricaoMunicipio", null);
        	clienteActionForm.set("idRegistroRemocao", null);
        	clienteActionForm.set("textoSelecionado", null);
        	clienteActionForm.set("idRegistrosRemocao", null);
        	clienteActionForm.set("indicadorUso", null);
        	clienteActionForm.set("indicadorAcrescimos", null);
        	//clienteActionForm.set("indicadorPessoaFisicaJuridica", null);
        	//clienteActionForm.set("indicadorGeraFaturaAntecipada", null);
        	clienteActionForm.set("diaVencimento", null);
        	clienteActionForm.set("indicadorVencimentoMesSeguinte", null);
        }
        
        if(httpServletRequest.getParameter("idCliente")!=null 
        		&& !httpServletRequest.getParameter("idCliente").toString().equals("") ){
        	
        	FiltroCliente filtroCliente = new FiltroCliente();
        	
        	filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,httpServletRequest.getParameter("idCliente")));
        	
        	filtroCliente.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.CLIENTE_TIPO);
        	
        	Collection clientes = fachada.pesquisar(filtroCliente,Cliente.class.getName());
        	
        	if(clientes!=null && !clientes.isEmpty()){
        		Cliente cliente = (Cliente) clientes.iterator().next();
        		clienteActionForm.set("nome", cliente.getNome());
                clienteActionForm.set("tipoPessoa", new Short(cliente.getClienteTipo().getId().toString()));
                clienteActionForm.set("indicadorPessoaFisicaJuridica",cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica().toString());
                httpServletRequest.setAttribute("nome",cliente.getNome());
                httpServletRequest.setAttribute("tipoPessoa",new Short(cliente.getClienteTipo().getId().toString()));
                httpServletRequest.setAttribute("indicadorPessoaFisicaJuridica",cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica());
            }
        	
        }
        
        //**********************************************************************
		// CRC2103
		// Autor: Ivan Sergio
		// Data: 17/07/2009
		// Recupera o action de retorno do inserir cliente exibido como um PopUp
		//**********************************************************************
		if (httpServletRequest.getParameter("PopUpInserirClienteRetorno") != null) {
			String actionRetorno = httpServletRequest.getParameter("PopUpInserirClienteRetorno");
			sessao.setAttribute("PopUpInserirClienteRetorno", actionRetorno);
		}
		
		if (httpServletRequest.getParameter("idClienteRelacaoTipo") != null) {
			sessao.setAttribute("idClienteRelacaoTipo",
					httpServletRequest.getParameter("idClienteRelacaoTipo"));
		}
		//**********************************************************************

        return retorno;
    }
}
