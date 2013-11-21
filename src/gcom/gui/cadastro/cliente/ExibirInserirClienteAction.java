/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
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
