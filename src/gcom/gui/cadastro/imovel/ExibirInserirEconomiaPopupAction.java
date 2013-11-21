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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.cadastro.imovel.AreaConstruidaFaixa;
import gcom.cadastro.imovel.FiltroAreaConstruidaFaixa;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action para exibir a página de economia popup
 * 
 * @author Sávio Luiz
 * @created 19 de Maio de 2004
 */
public class ExibirInserirEconomiaPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping.findForward("inserirEconomiaPopup");

		// Cria a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		Collection colecaoClientesImoveisEconomia = null;

		// HashSet verifica se existe objeto igual na collection
		if (sessao.getAttribute("colecaoClientesImoveisEconomia") != null) {
			colecaoClientesImoveisEconomia = (Collection) sessao.getAttribute("colecaoClientesImoveisEconomia");
		} else {
			colecaoClientesImoveisEconomia = new ArrayList();
		}

		Collection colecaoImovelSubCategoriasCadastradas = null;

		// Coleção vinda do exibirInserirEconomiaAcion
		// nessa coleção estão todos os imoveis sub categorias que foi
		// pesquisado no economia_inserir_jsp
		if (sessao.getAttribute("colecaoImovelSubCategoriasCadastradas") != null) {
			colecaoImovelSubCategoriasCadastradas = (Collection) sessao.getAttribute("colecaoImovelSubCategoriasCadastradas");
		} else {
			colecaoImovelSubCategoriasCadastradas = new ArrayList();
		}

		// Obtém o action form
		EconomiaPopupActionForm economiaPopupActionForm = (EconomiaPopupActionForm) actionForm;

		// Obtém a fachada
		Fachada fachada = Fachada.getInstancia();

		// Verifica se veio algum parametro no economia_inserir.jsp
		// caso tenha vindo pega o parametro e procura na coleção um objeto
		// que tenha um hashCode igual ao do parametro
		if (httpServletRequest.getParameter("codigoImovelSubCategoria") != null && 
			!httpServletRequest.getParameter("codigoImovelSubCategoria").equals("")) {
			
			String codigoImovelSubCategoria = (String) httpServletRequest.getParameter("codigoImovelSubCategoria");

			Iterator imovelSubCategoriaIterator = colecaoImovelSubCategoriasCadastradas.iterator();
			
			while (imovelSubCategoriaIterator.hasNext()) {
				ImovelSubcategoria imovelSubCategoria = 
					(ImovelSubcategoria) imovelSubCategoriaIterator.next();
				
				if (imovelSubCategoria.getUltimaAlteracao().getTime() == Long.parseLong(codigoImovelSubCategoria)) {

					sessao.setAttribute("imovelSubCategoria",imovelSubCategoria);
					
					if (imovelSubCategoria.getImovelEconomias() != null && 
						!imovelSubCategoria.getImovelEconomias().equals("")) {
						
						if (imovelSubCategoria.getImovelEconomias().size() == imovelSubCategoria.getQuantidadeEconomias()) {
							throw new ActionServletException("atencao.ja_existe_dados_economia");
						} else {
							sessao.setAttribute("contIdentificadorTemp",
								new Integer(imovelSubCategoria.getImovelEconomias().size() + 1));
						}
					} else {
						sessao.setAttribute("contIdentificadorTemp",new Integer(1));
					}

					colecaoClientesImoveisEconomia = new ArrayList();

					break;
				}
			}

			economiaPopupActionForm.setComplementoEndereco("");
			economiaPopupActionForm.setNumeroPontosUtilizacao("");
			economiaPopupActionForm.setNumeroMorador("");
			economiaPopupActionForm.setNumeroIptu("");
			economiaPopupActionForm.setNumeroCelpe("");
			economiaPopupActionForm.setAreaConstruida("");
			economiaPopupActionForm.setIdCliente("");
			economiaPopupActionForm.setNomeCliente("");
			economiaPopupActionForm.setIdClienteImovelUsuario("");
		}

		// Criação das coleções
		Collection areasConstruidasFaixas = null;
		Collection clientesRelacoesTipos = null;
		Collection clientes;

		// parametro que testa se dará um reload(true) ou nao(false)
		httpServletRequest.setAttribute("testeInserir", "false");

		// Criação dos objetos
		String idCliente = null;

		if (economiaPopupActionForm.getIdCliente() == null || 
			economiaPopupActionForm.getIdCliente().toString().trim().equalsIgnoreCase("")) {

			// Filtro AreaConstruidaFaixa
			FiltroAreaConstruidaFaixa filtroAreaConstruidaFaixa = 
				new FiltroAreaConstruidaFaixa(FiltroAreaConstruidaFaixa.MENOR_FAIXA);

			filtroAreaConstruidaFaixa.adicionarParametro(
				new ParametroSimples(FiltroAreaConstruidaFaixa.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			areasConstruidasFaixas = 
				fachada.pesquisar(filtroAreaConstruidaFaixa, AreaConstruidaFaixa.class.getName());

			// Filtro cleintesRelacoesTipos
			FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = 
				new FiltroClienteRelacaoTipo(FiltroClienteRelacaoTipo.DESCRICAO);
			
			filtroClienteRelacaoTipo.adicionarParametro(
				new ParametroSimples(FiltroClienteRelacaoTipo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroClienteRelacaoTipo.adicionarParametro(
				new ParametroSimples(FiltroClienteRelacaoTipo.CLIENTE_RELACAO_TIPO_ID,
					ClienteRelacaoTipo.PROPRIETARIO,ParametroSimples.CONECTOR_OR));
			
			filtroClienteRelacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroClienteRelacaoTipo.CLIENTE_RELACAO_TIPO_ID,ClienteRelacaoTipo.USUARIO));
			
			clientesRelacoesTipos = 
				fachada.pesquisar(filtroClienteRelacaoTipo,ClienteRelacaoTipo.class.getName());

			// a coleção de clientesImoveisTipos é obrigatório
			if (clientesRelacoesTipos == null || clientesRelacoesTipos.equals("")) {

				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado", null,
						"cliente relação tipo");
			} else {

				if (economiaPopupActionForm.getTextoSelecionadoEconomia() == null || 
					economiaPopupActionForm.getTextoSelecionadoEconomia().equals("")) {
					
					economiaPopupActionForm.setTextoSelecionadoEconomia(((ClienteRelacaoTipo) 
						((List) clientesRelacoesTipos).get(0)).getDescricao());
				}
			}
			
			SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
			
			// joga em dataInicial a parte da data
			String dataInicial = dataFormato.format(new Date());

			economiaPopupActionForm.setDataInicioClienteImovelRelacao(dataInicial);

			// Envia os objetos no request
			sessao.setAttribute("areasConstruidasFaixas",areasConstruidasFaixas);
			sessao.setAttribute("clientesRelacoesTipos", clientesRelacoesTipos);

			// Realiza a pesquisa de Cliente se necessário (caso o usuário
			// informou um código do cliente e teclou <enter>)
		} else {

			idCliente = economiaPopupActionForm.getIdCliente();
			FiltroCliente filtroCliente = new FiltroCliente();

			// Adiciona parametro
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));

			// Realiza a pesquisa de cliente
			clientes = fachada.pesquisar(filtroCliente, Cliente.class.getName());
			
			if (clientes != null && !clientes.isEmpty()) {

				// O cliente foi encontrado
				economiaPopupActionForm.setIdCliente(((Cliente) ((List) clientes).get(0)).getId().toString());
				economiaPopupActionForm.setNomeCliente(((Cliente) ((List) clientes).get(0)).getNome());

				//cliente = new Cliente();
				/*cliente = (Cliente)*/ 
				clientes.iterator().next();

			} else {
				httpServletRequest.setAttribute("codigoClienteNaoEncontrado","true");
				economiaPopupActionForm.setNomeCliente("Código Inexistente");
			}
		}

		// Verifica se o tipoConsulta é diferente de nulo ou vazio esse tipo
		// consulta vem do
		// municipio_resultado_pesquisa.jsp ou do bairro_resultado_pesquisa.jsp.
		// É feita essa verificação pois pode ser que ainda não tenha
		// feito a pesquisa de municipio ou bairro.
		if (httpServletRequest.getParameter("tipoConsulta") != null &&
			!httpServletRequest.getParameter("tipoConsulta").equals("")) {
			
			// Verifica se o tipo da consulta de cliente é de municipio
			// se for os parametros de enviarDadosParametros serão mandados para
			// a pagina cliente_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta").equals("cliente")) {

				economiaPopupActionForm.setIdCliente(httpServletRequest.getParameter("idCampoEnviarDados"));
				economiaPopupActionForm.setNomeCliente(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			}
		}

		sessao.setAttribute("colecaoClientesImoveisEconomia",colecaoClientesImoveisEconomia);
		
		if (!colecaoClientesImoveisEconomia.isEmpty()){
			economiaPopupActionForm.setColecaoCliente("SIM");
		} else {
			economiaPopupActionForm.setColecaoCliente(null);
		}
		
		if (httpServletRequest.getAttribute("i") == null){
			economiaPopupActionForm.setClienteRelacaoTipo("-1");
		}

		return retorno;
	}

}
