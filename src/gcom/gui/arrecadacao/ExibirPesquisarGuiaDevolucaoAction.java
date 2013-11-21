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
package gcom.gui.arrecadacao;

import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.FiltroClienteEndereco;
import gcom.cadastro.cliente.FiltroClienteFone;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroDocumentoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.FiltroDebitoCreditoSituacao;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pesquisar Guia Devolucao - Exibir
 * 
 *  Action para Exibir a página de consulta de guias de devolução
 * 
 * @author Fernanda Paiva - 02/03/2006
 */
public class ExibirPesquisarGuiaDevolucaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("pesquisarGuiaDevolucao");

		// Instacia a fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarGuiaDevolucaoActionForm pesquisarGuiaDevolucaoActionForm = (PesquisarGuiaDevolucaoActionForm) actionForm;
		
		// Recupera os parametros
		String tela = httpServletRequest.getParameter("tela");
		String tipo = httpServletRequest.getParameter("tipo");
		
		if ((tipo != null && !tipo.equals("")) || (tela != null && !tela.equals("")))  {
			pesquisarGuiaDevolucaoActionForm.setCodigoCliente("");
			pesquisarGuiaDevolucaoActionForm.setNomeCliente("");
			pesquisarGuiaDevolucaoActionForm.setCodigoImovel("");
			pesquisarGuiaDevolucaoActionForm.setInscricaoImovel("");
			pesquisarGuiaDevolucaoActionForm.reset(actionMapping,httpServletRequest);

		}

		if(httpServletRequest.getParameter("objetoConsulta") == null
				&& httpServletRequest.getParameter("tipoConsulta") == null
				&& httpServletRequest.getParameter("voltarPesquisa") == null
				&& sessao.getAttribute("flag") == null)
		{
			pesquisarGuiaDevolucaoActionForm.setCodigoCliente("");
			pesquisarGuiaDevolucaoActionForm.setNomeCliente("");
			pesquisarGuiaDevolucaoActionForm.setCodigoImovel("");
			pesquisarGuiaDevolucaoActionForm.setInscricaoImovel("");
			pesquisarGuiaDevolucaoActionForm.reset(actionMapping,httpServletRequest);

			sessao.removeAttribute("caminhoRetornoTelaPesquisaCliente");
			sessao.removeAttribute("caminhoRetornoTelaPesquisaImovel");
			if (httpServletRequest.getParameter("novaPesquisa") == null
					|| httpServletRequest.getParameter("novaPesquisa").equals(
							"")) {
				sessao.removeAttribute("caminhoRetorno");
			}
			sessao.setAttribute("flag","1");
		}
		// Recupera os dados do formulário 
		String codigoCliente = (String) pesquisarGuiaDevolucaoActionForm.getCodigoCliente();
		String codigoImovel = (String) pesquisarGuiaDevolucaoActionForm.getCodigoImovel();
		
		//Verifica se o tipoConsulta é diferente de nulo ou vazio esse tipo
		// consulta vem do
		// localidade_resultado_pesquisa.jsp ou do
		// cliente_resultado_pesquisa.jsp ou do imovel_resultado_pesquisa.jsp.
		// É feita essa verificação pois pode ser que ainda não tenha
		// feito a pesquisa de cliente ou imovel.
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {
			// Verifica se o tipo da consulta de guia devolucao é de imovel
			// se for os parametros de enviarDadosParametros serão mandados para
			// a pagina guia_devolucao_pesquisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta")
					.equals("imovel")) {

				pesquisarGuiaDevolucaoActionForm.setCodigoImovel(httpServletRequest
						.getParameter("idCampoEnviarDados"));

				pesquisarGuiaDevolucaoActionForm
						.setInscricaoImovel(httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));
				
				pesquisarGuiaDevolucaoActionForm.setCodigoCliente("");

				pesquisarGuiaDevolucaoActionForm
						.setNomeCliente("");

			}

			// Verifica se o tipo da consulta de arrecadador é de cliente
			// se for os parametros de enviarDadosParametros serão mandados para
			// a pagina arrecadador_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"cliente")) {

				pesquisarGuiaDevolucaoActionForm.setCodigoCliente(httpServletRequest
						.getParameter("idCampoEnviarDados"));
				pesquisarGuiaDevolucaoActionForm
						.setNomeCliente(httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));
				
				pesquisarGuiaDevolucaoActionForm.setCodigoImovel("");

				pesquisarGuiaDevolucaoActionForm
						.setInscricaoImovel("");

			}

		} else {
			// Verifica se o código do imóvel foi digitado
			if (codigoImovel != null
					&& !codigoImovel.trim().equals("")
					&& Integer.parseInt(codigoImovel) > 0) {
				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("quadra");
				
				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, codigoImovel));
	
				Collection imovelEncontrado = fachada.pesquisar(filtroImovel,
						Imovel.class.getName());
	
				if (imovelEncontrado != null && !imovelEncontrado.isEmpty()) {
					// O imovel foi encontrado
					pesquisarGuiaDevolucaoActionForm.setCodigoImovel(""
							+ ((Imovel) ((List) imovelEncontrado).get(0)).getId());
					pesquisarGuiaDevolucaoActionForm.setInscricaoImovel(""
							+ ((Imovel) ((List) imovelEncontrado).get(0)).getInscricaoFormatada());
				} else {
					pesquisarGuiaDevolucaoActionForm.setCodigoImovel("");
					httpServletRequest.setAttribute(
							"codigoImovelNaoEncontrado", "exception");
					pesquisarGuiaDevolucaoActionForm
							.setInscricaoImovel("MATRÍCULA INEXISTENTE");
					httpServletRequest.setAttribute("nomeCampo","codigoImovel");
				}
			}
			// Verifica se o código do cliente foi digitado
			if (codigoCliente != null
					&& !codigoCliente.trim().equals("")
					&& Integer.parseInt(codigoCliente) > 0) {
				
				FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
	
				filtroClienteEndereco.adicionarParametro(new ParametroSimples(
						FiltroClienteEndereco.CLIENTE_ID, codigoCliente));
	
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("enderecoTipo");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("cliente.profissao");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("cliente.ramoAtividade");
	
				Collection clienteEncontrado = fachada.pesquisar(filtroClienteEndereco,
						ClienteEndereco.class.getName());
	
				if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
				
					ClienteEndereco clienteEndereco = (ClienteEndereco) ((List) clienteEncontrado).get(0);
					// O endereço do cliente foi encontrado
					if (clienteEndereco.getCliente().getId() != null) 
					{
						pesquisarGuiaDevolucaoActionForm.setCodigoCliente(""
								+ clienteEndereco.getCliente().getId());
					}
					if (clienteEndereco.getCliente().getNome() != null) 
					{
						pesquisarGuiaDevolucaoActionForm.setNomeCliente(""
								+ clienteEndereco.getCliente().getNome());
					}
					if (clienteEndereco.getCliente().getClienteTipo().getIndicadorPessoaFisicaJuridica() == 1 ){
						if (clienteEndereco.getCliente().getCpfFormatado() != null) 
						{
							pesquisarGuiaDevolucaoActionForm.setCpfCnpj(""
									+ clienteEndereco.getCliente().getCpfFormatado());
						}
					}
					else
					{
						if (clienteEndereco.getCliente().getCnpjFormatado() != null) 
						{
							pesquisarGuiaDevolucaoActionForm.setCpfCnpj(""
									+ clienteEndereco.getCliente().getCnpjFormatado());
						}
					}
					if (clienteEndereco.getCliente().getProfissao() != null) 
					{
						pesquisarGuiaDevolucaoActionForm.setProfissao(""
								+ clienteEndereco.getCliente().getProfissao().getDescricao());
					}
					if (clienteEndereco.getCliente().getRamoAtividade() != null) 
					{
						pesquisarGuiaDevolucaoActionForm.setRamoAtividade(""
								+ clienteEndereco.getCliente().getRamoAtividade().getDescricao());
					}
					pesquisarGuiaDevolucaoActionForm.setEnderecoCliente(""
							+ ((ClienteEndereco) ((List) clienteEncontrado)
									.get(0)).getEnderecoFormatado());
					
					FiltroClienteFone filtroClienteFone = new FiltroClienteFone();
	
					filtroClienteFone.adicionarParametro(new ParametroSimples(
							FiltroClienteFone.CLIENTE_ID, codigoCliente));
	
					
					Collection colecaoClienteFone = fachada.pesquisar(
							filtroClienteFone, ClienteFone.class.getName());
	
					if (colecaoClienteFone != null
							&& !colecaoClienteFone.isEmpty()) {
						// O telefone foi encontrado
						pesquisarGuiaDevolucaoActionForm.setClienteFone(""
								+ ((ClienteFone) ((List) colecaoClienteFone)
										.get(0)).getTelefone());
					}
	
				} else {
					pesquisarGuiaDevolucaoActionForm.setCodigoCliente("");
					pesquisarGuiaDevolucaoActionForm
							.setNomeCliente(ConstantesSistema.CODIGO_CLIENTE_INEXISTENTE);
					httpServletRequest.setAttribute(
							"idClienteNaoEncontrado", "exception");
					httpServletRequest.setAttribute("nomeCampo","codigoCliente");
				}
			}
		}		
		// Coleção de Tipo de Crédito
		FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
		Collection<CreditoTipo> collectionTipoCredito = fachada.pesquisar(
				filtroCreditoTipo, CreditoTipo.class.getName());

		httpServletRequest.setAttribute("collectionTipoCredito",
				collectionTipoCredito);

		// Coleção de Tipo de Documento
		FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
		Collection<DocumentoTipo> collectionTipoDocumento = fachada.pesquisar(
				filtroDocumentoTipo, DocumentoTipo.class.getName());

		httpServletRequest.setAttribute("collectionTipoDocumento",
				collectionTipoDocumento);

		// Coleção de Debito Crédito Situacao
		FiltroDebitoCreditoSituacao filtroDebitoCreditoSituacao = new FiltroDebitoCreditoSituacao();
		Collection<DebitoCreditoSituacao> collectionSituacaoGuia = fachada.pesquisar(
				filtroDebitoCreditoSituacao, DebitoCreditoSituacao.class.getName());

		httpServletRequest.setAttribute("collectionSituacaoGuia",
				collectionSituacaoGuia);
		
		return retorno;

	}
}
