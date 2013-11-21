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
package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.FiltroGuiaPagamentoHistorico;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoHistorico;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroDocumentoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.conta.FiltroContaHistorico;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoACobrarHistorico;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoACobrar;
import gcom.faturamento.debito.FiltroDebitoACobrarHistorico;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

/**
 * Action que inicializa a página de inserir pagamento manual do processo de
 * inserir pagamentos
 * 
 * @author Pedro Alexandre
 * @date 16/02/2006
 */
public class ExibirInserirPagamentosTipoInclusaoManualAction extends GcomAction {

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * <Breve descrição sobre o subfluxo>
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * <Breve descrição sobre o fluxo secundário>
	 * 
	 * <Identificador e nome do fluxo secundário>
	 * 
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno para a página de inserir pagamento
		// manual
		ActionForward retorno = actionMapping
				.findForward("inserirPagamentosTipoInclusaoManual");

		// Cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Cria uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// recupera o form
		DynaValidatorActionForm pagamentoActionForm = (DynaValidatorActionForm) actionForm;
		
		/*
		 * Colocado por Raphael Rossiter em 24/09/2007 (Analista: Aryed Lins)
		 * OBJ: Inserir mais de um pagamento por vez
		 */
		String informarPagamento = httpServletRequest.getParameter("informarPagamento");
		String removerPagamento = httpServletRequest.getParameter("removerPagamento");
		String clicouAdicionar = httpServletRequest.getParameter("clicouAdicionar");

		// Cria o filtro de tipo de documento, e seta no filtro quais os tipo de
		// documentos necessários
		// para pesquisar os tipos de documento de conta, guia de pagamento e
		// débito a cobrar
		FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.ID, DocumentoTipo.CONTA,
				ParametroSimples.CONECTOR_OR));
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.ID, DocumentoTipo.GUIA_PAGAMENTO,
				ParametroSimples.CONECTOR_OR));
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.ID, DocumentoTipo.DEBITO_A_COBRAR));
		Collection<DocumentoTipo> colecaoDocumentoTipo = fachada.pesquisar(
				filtroDocumentoTipo, DocumentoTipo.class.getName());

		// [FS0002] - Verificar existência de dados
		// Caso a coleção de tipo de documento estiver nula ou vazia, levanta
		// uma
		// exceção para o usuário indicando que nenhum tipo de documento está
		// cadastrado
		// Caso contrário manda os tipos de documentos pesquisados pela sessão
		if (colecaoDocumentoTipo == null || colecaoDocumentoTipo.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Tipo de Documento");
		} else {
			sessao.setAttribute("colecaoDocumentoTipo", colecaoDocumentoTipo);
		}

		// [FS0007] - Verificar existência da localidade
		// Recupera o código da localidade digitado pelo usuário
		String codigoLocalidadeDigitadoEnter = (String) pagamentoActionForm
				.get("idLocalidade");

		// Caso o código da localidade informado não estiver vazio
		if (codigoLocalidadeDigitadoEnter != null
				&& !codigoLocalidadeDigitadoEnter.trim().equalsIgnoreCase("")) {

			// Recupera a localidade informada pelo usuário
			Localidade localidadeEncontrada = fachada
					.pesquisarLocalidadeDigitada(new Integer(
							codigoLocalidadeDigitadoEnter));
			// Localidade localidadeEncontrada =
			// this.pesquisarLocalidadeDigitada(new
			// Integer(codigoLocalidadeDigitadoEnter));

			// Caso a localidade informada pelo usuário esteja cadastrada no
			// sistema
			// Seta os dados da localidade no form
			// Caso contrário seta as informações da localidade para vazio
			// e indica ao usuário que a localidade não existe
			if (localidadeEncontrada != null) {
				pagamentoActionForm.set("idLocalidade", ""
						+ localidadeEncontrada.getId());
				pagamentoActionForm.set("descricaoLocalidade",
						localidadeEncontrada.getDescricao());
				httpServletRequest.setAttribute("idLocalidadeNaoEncontrada",
						"true");

			} else {
				pagamentoActionForm.set("idLocalidade", "");
				httpServletRequest.setAttribute("idLocalidadeNaoEncontrada",
						"exception");
				pagamentoActionForm.set("descricaoLocalidade",
						"Localidade inexistente");

			}
		}

		// [FS0008] - Verificar existência da matrícula do imóvel
		String codigoImovelDigitadoEnter = (String) pagamentoActionForm
				.get("idImovel");

		// Caso o código do imóvel informado não estiver vazio
		if (codigoImovelDigitadoEnter != null
				&& !codigoImovelDigitadoEnter.trim().equalsIgnoreCase("")) {

			// Recupera o imóvel informado pelo usuário
			Imovel imovelEncontrado = fachada
					.pesquisarImovelDigitado(new Integer(
							codigoImovelDigitadoEnter));
			// Imovel imovelEncontrado = this.pesquisarImovelDigitado(new
			// Integer(codigoImovelDigitadoEnter));

			// Caso o imóvel informado pelo usuário esteja cadastrado no sistema
			// Seta os dados o imóvel no form
			// Caso contrário seta as informações o imóvel para vazio
			// e indica ao usuário que o imóvel informado não existe
			if (imovelEncontrado != null) {
				pagamentoActionForm.set("idImovel", ""
						+ imovelEncontrado.getId());
				pagamentoActionForm.set("descricaoImovel", ""
						+ imovelEncontrado.getInscricaoFormatada());
				httpServletRequest
						.setAttribute("idImovelNaoEncontrado", "true");

				// Recupera a localidade do imóvel,caso o mesmo exista na base
				Localidade localidadeImovel = imovelEncontrado.getLocalidade();

				// Caso o usuário tenha informado a localidade
				if (codigoLocalidadeDigitadoEnter != null
						&& !codigoLocalidadeDigitadoEnter.trim()
								.equalsIgnoreCase("")) {
					// [FS0009] - Verificar localidade da matrícula do imóvel
					if (!fachada.verificarLocalidadeMatriculaImovel(
							codigoLocalidadeDigitadoEnter, imovelEncontrado)) {
						pagamentoActionForm.set("idImovel", "");
						httpServletRequest.setAttribute(
								"idImovelNaoEncontrado", "exception");
						pagamentoActionForm
								.set(
										"descricaoImovel",
										"A Localidade da Matrícula "
												+ localidadeImovel.getId()
												+ " é diferente da localidade informada "
												+ codigoLocalidadeDigitadoEnter);
					}

				} else {
					pagamentoActionForm.set("idLocalidade", ""
							+ localidadeImovel.getId());
					pagamentoActionForm.set("descricaoLocalidade",
							localidadeImovel.getDescricao());
					httpServletRequest.setAttribute(
							"idLocalidadeNaoEncontrada", "true");
				}
			} else {
				pagamentoActionForm.set("idImovel", "");
				httpServletRequest.setAttribute("idImovelNaoEncontrado",
						"exception");
				pagamentoActionForm.set("descricaoImovel",
						"Matrícula do Imóvel inexistente");
				
				//limpa todos os campos das pesquisas q dependem de Matricula do imovel
				pagamentoActionForm.set("referenciaConta", "");
				pagamentoActionForm.set("descricaoReferenciaConta", "");
				pagamentoActionForm.set("valorPagamento", "");
				
				pagamentoActionForm.set("idGuiaPagamento", "");
				pagamentoActionForm.set("descricaoGuiaPagamento","");
				pagamentoActionForm.set("valorGuiaPagamento", "");
				pagamentoActionForm.set("valorPagamento", "");
				
				pagamentoActionForm.set("idDebitoACobrar", "");
				pagamentoActionForm.set("descricaoDebitoACobrar", "");
				pagamentoActionForm.set("valorDebitoACobrar", "");
				pagamentoActionForm.set("valorPagamento", "");
				
			}
		}

		// [FS0011] - Verificar existência do código do cliente
		String codigoClienteDigitadoEnter = (String) pagamentoActionForm
				.get("idCliente");

		// Recupera a metrícula do imóvel e o código do cliente do form de
		// pagamento
		String codigoImovel = (String) pagamentoActionForm.get("idImovel");
		String codigoCliente = (String) pagamentoActionForm.get("idCliente");

		// Caso o usuário tenha informado o código do cliente
		if (codigoClienteDigitadoEnter != null
				&& !codigoClienteDigitadoEnter.trim().equalsIgnoreCase("")) {

			// Recupera o cliente ,caso o mesmo exista na base
			Cliente clienteEncontrado = fachada
					.pesquisarClienteDigitado(new Integer(
							codigoClienteDigitadoEnter));
			// Cliente clienteEncontrado = this.pesquisarClienteDigitado(new
			// Integer(codigoClienteDigitadoEnter));

			// Caso o cliente esteja cadastrado no sistema
			// Seta no form todos os dados do cliente
			// Caso contrário seta os dados do cliente para vazio e informa que
			// o cliente não existe
			if (clienteEncontrado != null) {
				pagamentoActionForm.set("idCliente", ""
						+ clienteEncontrado.getId());
				pagamentoActionForm.set("nomeCliente", clienteEncontrado
						.getNome());
				httpServletRequest.setAttribute("idClienteNaoEncontrado",
						"true");

			} else {
				pagamentoActionForm.set("idCliente", "");
				httpServletRequest.setAttribute("idClienteNaoEncontrado",
						"exception");
				pagamentoActionForm.set("nomeCliente",
						"Código de Cliente inexistente");
				
				//limpa todos os campos das pesquisas q dependem de Código de Cliente
				pagamentoActionForm.set("idGuiaPagamento", "");
				pagamentoActionForm.set("descricaoGuiaPagamento","");
				pagamentoActionForm.set("valorGuiaPagamento", "");
				pagamentoActionForm.set("valorPagamento", "");
				

			}
		}

		// [FS0012] - Verificar existência da conta
		String referenciaContaDigitadoEnter = (String) pagamentoActionForm
				.get("referenciaConta");

		// Caso o usuário tenha informado a referência da conta
		if (referenciaContaDigitadoEnter != null
				&& !referenciaContaDigitadoEnter.trim().equalsIgnoreCase("")) {

			// Caso o usuário não tenha informado a matrícula do imóvel
			// Levanta uma exceção para o usuário indicado que ele não informou
			// a matrícula do imóvel
			if (codigoImovel == null
					|| codigoImovel.trim().equalsIgnoreCase("")) {
				throw new ActionServletException("atencao.required", null,
						"Matrícula do Imóvel");
			}

			// Recupera a conta do imóvel com a referência informada
			Conta contaEncontrada = fachada.pesquisarContaDigitada(
					codigoImovel, referenciaContaDigitadoEnter);
			// Conta contaEncontrada = this.pesquisarContaDigitada(codigoImovel,
			// referenciaContaDigitadoEnter);

			// Caso a conta esteja cadastrada no sistema
			// Seta todas as informações da conta no form
			// Caso contrário seta as informações da conta para nulo
			// e indica ao usuário que não existe conta para o imóel
			// informadocom a referência indicada
			
			
			
			if (contaEncontrada != null && clicouAdicionar == null) {
				pagamentoActionForm.set("referenciaConta", ""
						+ referenciaContaDigitadoEnter);
				pagamentoActionForm.set("descricaoReferenciaConta", ""
						+ Util.formatarMoedaReal(contaEncontrada
								.getValorTotal()));
				pagamentoActionForm.set("valorPagamento", ""
						+ Util.formatarMoedaReal(contaEncontrada
								.getValorTotal()));
				httpServletRequest.setAttribute("referenciaContaNaoEncontrada",
						"true");

			} 
			else if ((informarPagamento == null || informarPagamento.equals("")) &&
					 (removerPagamento == null || removerPagamento.equals("")) && 
					 clicouAdicionar == null) {
				
				pagamentoActionForm.set("referenciaConta", ""
						+ referenciaContaDigitadoEnter);
				
				pagamentoActionForm.set("descricaoReferenciaConta", "");
				pagamentoActionForm.set("valorPagamento", "");
				httpServletRequest.setAttribute("referenciaContaNaoEncontrada",
						"true");
				
				/*httpServletRequest.setAttribute("referenciaContaNaoEncontrada",
						"exception");
				
				
				pagamentoActionForm.set("descricaoReferenciaConta",
						"Não há Conta com a referência "
								+ referenciaContaDigitadoEnter
								+ " para o imóvel " + codigoImovel);
				
				//pagamentoActionForm.set("descricaoReferenciaConta","");
				pagamentoActionForm.set("valorPagamento", "");
				
				throw new ActionServletException("atencao.referencia.naocadastrada",
						null, "" + referenciaContaDigitadoEnter, "" + codigoImovel);*/
				
			}
		}

		// [FS0022] - Verificar existência da guia de pagamento
		String codigoGuiaPagamentoDigitadoEnter = (String) pagamentoActionForm
				.get("idGuiaPagamento");

		// Caso o usuário tenha informado o código da guia de pagamento
		if (codigoGuiaPagamentoDigitadoEnter != null
				&& !codigoGuiaPagamentoDigitadoEnter.trim()
						.equalsIgnoreCase("")) {

			// Caso o usuário não tenha informado a matrícula do imóvel
			// Levanta uma exceção para o usuário indicado que ele não informou
			// a matrícula do imóvel
			if (codigoImovel == null
					|| codigoImovel.trim().equalsIgnoreCase("")) {
				if (codigoCliente == null
						|| codigoCliente.trim().equalsIgnoreCase("")) {
					throw new ActionServletException("atencao.required",
							null, "Matrícula do Imóvel ou Código do Cliente");
				}
			}

			// Pesquisa a guia de pagamento para o imóvel informado
			GuiaPagamento guiaPagamentoEncontrada = this
					.pesquisarGuiaPagamentoDigitada(codigoImovel,
							codigoCliente, codigoGuiaPagamentoDigitadoEnter, fachada);
			
			
			// GuiaPagamento guiaPagamentoEncontrada =
			// this.pesquisarGuiaPagamentoDigitada(codigoImovel, codigoCliente,
			// codigoGuiaPagamentoDigitadoEnter);

			// Caso a guia de pagamento esteja cadastrada no sistema
			// Seta os dados da guai de pagamento no form
			// Caso contrário seta os dados da guia para nulo e informa ao
			// usuário que não existe guia de pagamento cadastrada no sistema
			if (guiaPagamentoEncontrada != null && clicouAdicionar == null) {

				pagamentoActionForm.set("idGuiaPagamento", ""
						+ codigoGuiaPagamentoDigitadoEnter);
				pagamentoActionForm.set("descricaoGuiaPagamento", ""
						+ guiaPagamentoEncontrada.getDebitoTipo()
								.getDescricao());
				pagamentoActionForm.set("valorGuiaPagamento", ""
						+ Util.formatarMoedaReal(guiaPagamentoEncontrada
								.getValorDebito()));
				pagamentoActionForm.set("valorPagamento", ""
						+ Util.formatarMoedaReal(guiaPagamentoEncontrada
								.getValorDebito()));
				httpServletRequest.setAttribute("idGuiaPagamentoNaoEncontrado",
						"true");

				// Seta os dados da localidade
				pagamentoActionForm.set("idLocalidade", ""
						+ guiaPagamentoEncontrada.getLocalidade().getId());
				pagamentoActionForm.set("descricaoLocalidade",
						guiaPagamentoEncontrada.getLocalidade().getDescricao());
				httpServletRequest.setAttribute("idLocalidadeNaoEncontrada",
						"true");

			} else if (clicouAdicionar == null){
				pagamentoActionForm.set("idGuiaPagamento", "");
				pagamentoActionForm.set("descricaoGuiaPagamento",
						"Guia de Pagamento inexistente");
				pagamentoActionForm.set("valorGuiaPagamento", "");
				httpServletRequest.setAttribute("idGuiaPagamentoNaoEncontrado",
						"exception");
			}
		}

		// [FS0024] - Verificar existência do débito a cobrar
		String codigoDebitoACobrarDigitadoEnter = (String) pagamentoActionForm
				.get("idDebitoACobrar");

		// Caso o usuário tenha informado o código do débito a cobrar
		if (codigoDebitoACobrarDigitadoEnter != null
				&& !codigoDebitoACobrarDigitadoEnter.trim()
						.equalsIgnoreCase("")) {

			// Caso o usuário não tenha informado a matrícula do imóvel
			// Levanta uma exceção para o usuário indicado que ele não informou
			// a matrícula do imóvel
			if (codigoImovel == null
					|| codigoImovel.trim().equalsIgnoreCase("")) {
				throw new ActionServletException("atencao.required", null,
						"Matrícula do Imóvel");
				
			}

			// Pesquisa o débito a cobrar para o imóvel informado
			DebitoACobrar debitoACobrarEncontrado = this
					.pesquisarDebitoACobrarDigitado(codigoImovel,
							codigoDebitoACobrarDigitadoEnter, fachada);
			// DebitoACobrar debitoACobrarEncontrado =
			// this.pesquisarDebitoACobrarDigitado(codigoImovel,
			// codigoDebitoACobrarDigitadoEnter);

			// Caso o débito a cobrar esteja cadastrado no sistema
			// Seta os dados do débito a cobrar no form
			// Caso contrário seta os dados do débito para nulo e informa ao
			// usuário que não existe
			// débito a cobrar cadastrado no sistema
			if (debitoACobrarEncontrado != null && clicouAdicionar == null) {
				pagamentoActionForm.set("idDebitoACobrar", ""
						+ codigoDebitoACobrarDigitadoEnter);
				pagamentoActionForm.set("descricaoDebitoACobrar", ""
						+ debitoACobrarEncontrado.getDebitoTipo()
								.getDescricao());
				pagamentoActionForm.set("valorDebitoACobrar", ""
						+ Util.formatarMoedaReal(debitoACobrarEncontrado
								.getValorDebito()));
				pagamentoActionForm.set("valorPagamento", ""
						+ Util.formatarMoedaReal(debitoACobrarEncontrado
								.getValorDebito()));
				httpServletRequest.setAttribute("idDebitoACobrarNaoEncontrado",
						"true");

			} else if (clicouAdicionar == null){
				pagamentoActionForm.set("idDebitoACobrar", "");
				pagamentoActionForm.set("descricaoDebitoACobrar",
						"Débito a Cobrar inexistente");
				pagamentoActionForm.set("descricaoDebitoACobrar", "");
				httpServletRequest.setAttribute("idDebitoACobrarNaoEncontrado",
						"exception");

			}
		}

		// [FS0020] - Verificar existência do tipo de débito
		String codigoTipoDebitoDigitadoEnter = (String) pagamentoActionForm
				.get("idTipoDebito");

		// Caso o usuário tenha informado o código do tipo de débito
		if (codigoTipoDebitoDigitadoEnter != null
				&& !codigoTipoDebitoDigitadoEnter.trim().equalsIgnoreCase("")) {

			// Recupera o tipo de débito ,caso o mesmo exista na base
			DebitoTipo tipoDebitoEncontrado = fachada
					.pesquisarTipoDebitoDigitado(new Integer(
							codigoTipoDebitoDigitadoEnter));

			// Caso o tipo de débito esteja cadastrado no sistema
			// Seta no form todos os dados do tipo de débito
			// Caso contrário seta os dados do tipo de débito para vazio e
			// informa que o tipo de débito não existe
			if (tipoDebitoEncontrado != null) {
				pagamentoActionForm.set("idTipoDebito", ""
						+ tipoDebitoEncontrado.getId());
				pagamentoActionForm.set("descricaoTipoDebito",
						tipoDebitoEncontrado.getDescricao());
				httpServletRequest.setAttribute("idTipoDebitoNaoEncontrado",
						"true");

			} else {
				pagamentoActionForm.set("idTipoDebito", "");
				httpServletRequest.setAttribute("idTipoDebitoNaoEncontrado",
						"exception");
				pagamentoActionForm.set("descricaoTipoDebito",
						"Tipo de Débito inexistente");
			}
		}
		
		
		/*
		 * Colocado por Raphael Rossiter em 24/09/2007 (Analista: Aryed Lins)
		 * OBJ: Inserir mais de um pagamento por vez
		 */
		if (informarPagamento != null && !informarPagamento.equals("")){
			
			Collection<Pagamento> colecaoPagamento = (Collection) sessao.getAttribute("colecaoPagamento");
			
			if (colecaoPagamento != null && !colecaoPagamento.isEmpty()){
				
				//VALIDA E GERA O PAGAMENTO EM MEMORIA
				Pagamento pagamentoInformado = this.validarDadosPagamento(pagamentoActionForm, fachada, 
				httpServletRequest);
				
				//VERIFICA SE O PAGAMENTO JA FOI INFORMADO NESSA SESSAO
				this.verificarPagamentoJaInformado(colecaoPagamento, pagamentoInformado);
				
				colecaoPagamento.add(pagamentoInformado);
			}
			else{
				
				colecaoPagamento = new ArrayList();
				
				//VALIDA E GERA O PAGAMENTO EM MEMORIA
				colecaoPagamento.add(this.validarDadosPagamento(pagamentoActionForm, fachada, httpServletRequest));
				
				sessao.setAttribute("colecaoPagamento", colecaoPagamento);
			}
			
			//LIMAPA OS CAMPOS DO FORMULARIO PRA RECEBER OS DADOS DO PROXIMO PAGAMENTO
			this.limparDadosPagamento(pagamentoActionForm);
		}
		
		/*
		 * Colocado por Raphael Rossiter em 24/09/2007 (Analista: Aryed Lins)
		 * OBJ: Remover um pagamento informado
		 */
		if (removerPagamento != null && !removerPagamento.equals("")){
			
			Collection<Pagamento> colecaoPagamento = (Collection) sessao.getAttribute("colecaoPagamento");
			
			//REMOVE O PAGAMENTO DA MEMORIA
			this.removerPagamento(colecaoPagamento, removerPagamento, sessao);
			
			//LIMAPA OS CAMPOS DO FORMULARIO PRA RECEBER OS DADOS DO PROXIMO PAGAMENTO
			this.limparDadosPagamento(pagamentoActionForm);
		}

		
		return retorno;
	}

	// /**
	// * Este fluxo secundário tem como objetivo pesquisar a localidade digitada
	// pelo usuário
	// *
	// * [FS0007] - Verificar existência da localidade
	// *
	// * @author Pedro Alexandre
	// * @date 16/02/2006
	// *
	// * @param idLocalidadeDigitada
	// * @return
	// */
	// private Localidade pesquisarLocalidadeDigitada(Integer
	// idLocalidadeDigitada){
	//    	
	// //Varaiável que vai armazenar a localidade digitada pelo usuário
	// Localidade localidadeDigitada = null;
	//    	
	// //Cria uma instância da fachada
	// Fachada fachada = Fachada.getInstancia();
	//    	
	// //Pesquisa a localidade informada pelo usuário no sistema
	// FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
	// filtroLocalidade.adicionarParametro(new
	// ParametroSimples(FiltroLocalidade.ID, idLocalidadeDigitada));
	// Collection<Localidade> colecaoLocalidade =
	// fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
	//    	
	// //Caso exista a localidade no sistema
	// //Retorna para o usuário a localidade retornada pela pesquisa
	// //Caso contrário retorna um objeto nulo
	// if(colecaoLocalidade != null && !colecaoLocalidade.isEmpty()){
	// localidadeDigitada =(Localidade)
	// Util.retonarObjetoDeColecao(colecaoLocalidade);
	// }
	//    	
	// //Retorna a localdiade encontrada ou nulo se não existir
	// return localidadeDigitada;
	// }
	//    
	// /**
	// * Este fluxo secundário tem como objetivo pesquisar o imóvel digitado
	// pelo usuário
	// *
	// * [FS0008] - Verificar existência da matrícula do imóvel
	// *
	// * @author Pedro Alexandre
	// * @date 16/02/2006
	// *
	// * @param idImovelDigitado
	// * @return
	// */
	// private Imovel pesquisarImovelDigitado(Integer idImovelDigitado){
	//    	
	// //Cria a variável que vai armazenar o imóvel pesquisado
	// Imovel imovelDigitado = null;
	//   	
	// //Cria uma instância da fachada
	// Fachada fachada = Fachada.getInstancia();
	//    	
	// //Pesquisa o imóvel informado pelo usuário no sistema
	// FiltroImovel filtroImovel = new FiltroImovel();
	// filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
	// filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
	// idImovelDigitado));
	// Collection colecaoImovel = fachada.pesquisar(filtroImovel,
	// Imovel.class.getName());
	//    	
	// //Caso exista o imóvel no sistema
	// //Retorna para o usuário o imóvel retornado pela pesquisa
	// //Caso contrário retorna um objeto nulo
	// if(colecaoImovel != null && !colecaoImovel.isEmpty()){
	// imovelDigitado =(Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
	// }
	//   	
	// //Retorna o imóvel encontrado ou nulo se não existir
	// return imovelDigitado;
	// }
	//    
	// /**
	// * Este fluxo secundário tem como objetivo pesquisar o cliente digitado
	// pelo usuário
	// *
	// * [FS0011] - Verificar existência do código do cliente
	// *
	// * @author Pedro Alexandre
	// * @date 16/02/2006
	// *
	// * @param idClienteDigitado
	// * @return
	// */
	// private Cliente pesquisarClienteDigitado(Integer idClienteDigitado){
	//    	
	// //Cria a variável que vai armazenar o cliente pesquisado
	// Cliente clienteDigitado = null;
	//    	
	// //Cria uma instância da fachada
	// Fachada fachada = Fachada.getInstancia();
	//    	
	// //Pesquisa o cliente informado pelo usuário no sistema
	// FiltroCliente filtroCliente = new FiltroCliente();
	// filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,
	// idClienteDigitado));
	// Collection<Cliente> colecaoCliente = fachada.pesquisar(filtroCliente,
	// Cliente.class.getName());
	//    	
	// //Caso exista o cliente no sistema
	// //Retorna para o usuário o cliente retornado pela pesquisa
	// //Caso contrário retorna um objeto nulo
	// if(colecaoCliente != null && !colecaoCliente.isEmpty()){
	// clienteDigitado =(Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
	// }
	//    	
	// //Retorna o cliente encontrado ou nulo se não existir
	// return clienteDigitado;
	// }
	// 
	//
	// /**
	// * Verifica se a localidade informada é a mesma do imóvel informado
	// *
	// * [FS0009] - Verificar localidade da matrícula do imóvel
	// *
	// * @author Pedro Alexandre
	// * @date 16/02/2006
	// *
	// * @param idLocalidadeInformada
	// * @param imovelInformado
	// * @return
	// */
	// private boolean verificarLocalidadeMatriculaImovel(String
	// idLocalidadeInformada, Imovel imovelInformado){
	//
	// //Recupera a localidade do imóvel
	// Localidade localidadeImovel = imovelInformado.getLocalidade();
	//	 
	// //Caso a localidade informada pelo usuário seja a mesma do imóvel
	// //Retorna "true" indicandoque a localidade é a mesma
	// //Caso contrário retorna "false" indicando que a localidade é diferente
	// if(idLocalidadeInformada.equalsIgnoreCase(localidadeImovel.getId().toString())
	// ){
	// return true;
	// }else{
	// return false;
	// }
	// }
	// 
	// /**
	// * Pesquisa a conta do imóvel com a referência informada pelo usuário
	// *
	// * [FS0012] - Verificar existência da conta
	// *
	// * @author Pedro Alexandre
	// * @date 16/02/2006
	// *
	// * @param idImovel
	// * @param referenciaConta
	// * @return
	// */
	// private Conta pesquisarContaDigitada(String idImovel,String
	// referenciaConta){
	// 	
	// //Variável que vai armazenar a conta pesquisada
	// Conta contaDigitada = null;
	// 	
	// //Cria uma instância da fachada
	// Fachada fachada = Fachada.getInstancia();
	// 	
	// //Formata a referência da conta informada para o formato (AAAAMM) sem a
	// barra
	// String anoMesConta =
	// Util.formatarMesAnoParaAnoMesSemBarra(referenciaConta);
	//	
	// //Cria o filtro de conta e seta todos os parâmetros para pesquisar a
	// conta do imóvel
	// FiltroConta filtroConta = new FiltroConta();
	// filtroConta.adicionarParametro(new
	// ParametroSimples(FiltroConta.IMOVEL_ID,idImovel));
	// filtroConta.adicionarParametro(new
	// ParametroSimples(FiltroConta.REFERENCIA,anoMesConta));
	// filtroConta.adicionarParametro(new
	// ParametroSimples(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL,DebitoCreditoSituacao.NORMAL,
	// ParametroSimples.CONECTOR_OR,3));
	// filtroConta.adicionarParametro(new
	// ParametroSimples(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL,DebitoCreditoSituacao.RETIFICADA,
	// ParametroSimples.CONECTOR_OR));
	// filtroConta.adicionarParametro(new
	// ParametroSimples(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL,DebitoCreditoSituacao.INCLUIDA));
	// Collection colecaoContas = fachada.pesquisar(filtroConta,
	// Conta.class.getName());
	//
	// //Caso exista a conta para a referência informada cadastrada no sistema
	// //Retorna para o usuário a conta retornada pela pesquisa
	// //Caso contrário retorna um objeto nulo
	// if(colecaoContas != null && !colecaoContas.isEmpty()){
	// contaDigitada = (Conta)Util.retonarObjetoDeColecao(colecaoContas);
	// }
	// 	
	// //Retorna a conta encontrada ou nulo se não existir a conta
	// return contaDigitada;
	// }
	// 
	// /**
	// * Pesquisa o débito a cobrar do imóvel informado pelo usuário
	// *
	// * [FS0024] - Verificar existência do débito a cobrar
	// *
	// * @author Pedro Alexandre
	// * @date 16/02/2006
	// *
	// * @param idImovel
	// * @param idDebitoACobrar
	// * @return
	// */
	// private DebitoACobrar pesquisarDebitoACobrarDigitado(String
	// idImovel,String idDebitoACobrar){
	//
	// //Cria a variável que vai armazenar o débito a cobrar pesquisado
	// DebitoACobrar debitoACobrarDigitado = null;
	//	 	
	// //Cria uma instância da fachada
	// Fachada fachada = Fachada.getInstancia();
	//	 	
	// //Cria o filtro de débito a cobrar e seta todos os parâmetros para
	// pesquisar o débito a cobrar do imóvel
	// FiltroDebitoACobrar filtroDebitoACobrar = new FiltroDebitoACobrar();
	// filtroDebitoACobrar.adicionarParametro(new
	// ParametroSimples(FiltroDebitoACobrar.IMOVEL_ID,idImovel));
	// filtroDebitoACobrar.adicionarParametro(new
	// ParametroSimples(FiltroDebitoACobrar.ID,idDebitoACobrar));
	// filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
	// Collection colecaoDebitoACobrar = fachada.pesquisar(filtroDebitoACobrar,
	// DebitoACobrar.class.getName());
	//		
	// //Caso exista o débito a cobrar para o imóvel informado cadastrado no
	// sistema
	// //Retorna para o usuário o débito a cobrar retornado pela pesquisa
	// //Caso contrário retorna um objeto nulo
	// if(colecaoDebitoACobrar == null || colecaoDebitoACobrar.isEmpty()){
	// throw new ActionServletException("atencao.naocadastrado",null, "Débito a
	// Cobrar");
	// }else{
	// debitoACobrarDigitado =
	// (DebitoACobrar)Util.retonarObjetoDeColecao(colecaoDebitoACobrar);
	// }
	//	 	
	// //Retorna o débito a cobrar encontrado ou nulo se não existir o débito a
	// cobrar
	// return debitoACobrarDigitado;
	// }
	//
	//
	// /**
	// * <Breve descrição sobre o fluxo secundário>
	// *
	// * [FS0022] - Verificar existência da guia de pagamento
	// *
	// * @author Pedro Alexandre
	// * @date 16/02/2006
	// *
	// * @param idImovel
	// * @param idCliente
	// * @param idGuiaPagamento
	// * @return
	// */
	// private GuiaPagamento pesquisarGuiaPagamentoDigitada(String idImovel,
	// String idCliente, String idGuiaPagamento){
	//	
	// //Cria a variável que vai armazenar a guia de pagamento pequisada
	// GuiaPagamento guiaPagamentoDigitada = null;
	//	 	
	// //Cria uma instância da fachada
	// Fachada fachada = Fachada.getInstancia();
	//	 	
	// //Cria o filtro de guia de pagamento
	// FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
	//		
	// //Caso o usuário tenha informado o imóvel, seta o código do imóvel no
	// filtro
	// //Caso contrário, o usuário tenha informado o cliente seta o código do
	// cliente no filtro
	// //Caso o usuário não tenha informado nem o imóvel nem o cliente levanta
	// uma exceção
	// //para o usuário informando que tem de informar o cliente ou o imóvel
	// if(idImovel != null && !idImovel.trim().equalsIgnoreCase("")){
	// filtroGuiaPagamento.adicionarParametro(new
	// ParametroSimples(FiltroGuiaPagamento.IMOVEL_ID,idImovel));
	// }else if(idCliente != null || !idCliente.trim().equalsIgnoreCase("")){
	// filtroGuiaPagamento.adicionarParametro(new
	// ParametroSimples(FiltroGuiaPagamento.CLIENTE_ID,idCliente));
	// }else{
	// throw new ActionServletException("atencao.naoinformado", null, "Imóvel ou
	// Cliente");
	// }
	//
	// //Pesquisa a guia de pagamento de acordo com os parâmetros no filtro
	// filtroGuiaPagamento.adicionarParametro(new
	// ParametroSimples(FiltroGuiaPagamento.ID,idGuiaPagamento));
	// filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
	// Collection colecaoGuiaPagamento = fachada.pesquisar(filtroGuiaPagamento,
	// GuiaPagamento.class.getName());
	//		
	// //Caso exista a guia de pagamento para o imóvel ou o cliente informado
	// cadastrado no sistema
	// //Retorna para o usuário a guia de pagamento retornada pela pesquisa
	// //Caso contrário retorna um objeto nulo
	// if(colecaoGuiaPagamento == null || colecaoGuiaPagamento.isEmpty()){
	// throw new ActionServletException("atencao.naocadastrado",null, "Guia de
	// Pagamento");
	// }else{
	// guiaPagamentoDigitada =
	// (GuiaPagamento)Util.retonarObjetoDeColecao(colecaoGuiaPagamento);
	// }
	//	 	
	// //Retorna a guia de pagamento encontrada ou nulo se não existir aa guia
	// de pagamento
	// return guiaPagamentoDigitada;
	// }
	
	/**
	 *
	 * Este fluxo secundário tem como objetivo pesquisar o imóvel informado pelo usuário
	 *
	 * [FS0008] Verificar existência da matrícula do imóvel
	 *
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 *
	 * @param idImovel
	 * @return
	 */
	public Imovel verificarExistenciaMatriculaImovel(String idImovel){
		 
		//Cria a variável que vai armazenar o imóvel pesquisado
		Imovel imovelEncontrado = null;
	 	
		//Cria uma instância da fachada 
	 	Fachada fachada = Fachada.getInstancia();
	 	
	 	//Pesquisa o imóvel informado pelo usuário no sistema 
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,idImovel));
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
		Collection colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
		
		//Caso exista o imóvel no sistema 
		//Retorna para o usuário o imóvel retornado pela pesquisa
		//Caso contrário retorna um objeto nulo 
		if(colecaoImoveis == null || colecaoImoveis.isEmpty()){
			// throw new ActionServletException("atencao.naocadastrado",null, "Matrícula do imóvel");
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Matrícula");
		}else{
			imovelEncontrado = (Imovel)Util.retonarObjetoDeColecao(colecaoImoveis);
		}
	 	
		//Retorna o imóvel encontrado ou nulo se não existir 
	 	return imovelEncontrado;
	}
	
	/**
	 * Este fluxo secundário tem como objetivo pesquisar a localidade digitada pelo usuário
	 *
	 * [FS0007] Verificar existência da localidade 
	 *
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 *
	 * @param idLocalidade
	 * @return
	 */
	private Localidade verificarExistenciaLocalidade(Integer idLocalidade) {
		
		//Variável que vai armazenar a localidade digitada pelo usuário 
		Localidade localidadeEncontrada = null;
		
		//Cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		//Pesquisa a localidade informada pelo usuário no sistema 
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
		Collection<Localidade> colecaoLocalidade =  fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

		//Caso exista a localidade no sistema 
		//Retorna para o usuário a localidade retornada pela pesquisa
		//Caso contrário retorna um objeto nulo 
		if(colecaoLocalidade == null || colecaoLocalidade.isEmpty()){
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Localidade");
		}else{
			localidadeEncontrada =(Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
		}
		
		//Retorna a localdiade encontrada ou nulo se não existir 
		return localidadeEncontrada;
	}
	
	/**
	 * Verifica se a localidade informada é a mesma do imóvel informado
	 *
	 * [FS0009] Verificar localidade da matrícula do imóvel 
	 *
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 *
	 * @param idLocalidade
	 * @param imovel
	 * @return
	 */
	private boolean verificarLocalidadeMatriculaImovel(String idLocalidade, Imovel imovel){

		//Caso o imóvel tenha sido informado
		if(imovel != null){
		  //Recupera a localidade do imóvel 
		  Localidade localidadeImovel = imovel.getLocalidade();
		 
		  Integer codigoLocalidade = new Integer(idLocalidade);
		  
		  //Caso a localidade informada pelo usuário seja a mesma do imóvel
		  //Retorna "true" indicandoque a localidade é a mesma
		  //Caso contrário retorna "false" indicando que a localidade é diferente  
		  if(codigoLocalidade.intValue() == localidadeImovel.getId().intValue() ){
		    return true; 
		  }else{
		    return false;
		  }
		}else{
			return true;
		}
	}
	
	/**
	 * Verifica se existe conta para o imóvel e referência informados, caso não xista retorna uma conta nula  
	 *
	 * [FS0012] Verificar existência da conta
	 *
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 *
	 * @param referenciaConta
	 * @param idImovel
	 * @return
	 */
	private Conta verificarExistenciaConta(String referenciaConta, String idImovel){
		 
			//Variável que vai armazenar a conta pesquisada
	    	Conta contaEncontrada = null;
	     	
	    	//Cria uma instância da fachada 
	     	Fachada fachada = Fachada.getInstancia();
	     	
	     	//Formata a referência da conta informada para o formato (AAAAMM) sem a barra 
	     	String anoMesConta = Util.formatarMesAnoParaAnoMesSemBarra(referenciaConta);
	    	
	     	//Cria o filtro de conta e seta todos os parâmetros para pesquisar a conta do imóvel
	    	FiltroConta filtroConta = new FiltroConta();
	    	filtroConta.adicionarCaminhoParaCarregamentoEntidade("localidade");
	    	
	    	filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.IMOVEL_ID,idImovel));
	    	filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.REFERENCIA,anoMesConta));
	    	filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL_ID,DebitoCreditoSituacao.NORMAL, ParametroSimples.CONECTOR_OR,3));
	    	filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL_ID,DebitoCreditoSituacao.RETIFICADA, ParametroSimples.CONECTOR_OR));
	    	filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL_ID,DebitoCreditoSituacao.INCLUIDA));
	    	Collection colecaoContas = fachada.pesquisar(filtroConta, Conta.class.getName());
	    	
	    	//Caso exista a conta para a referência informada cadastrada no sistema 
	    	//Retorna para o usuário a conta retornada pela pesquisa
	    	//Caso contrário retorna um objeto nulo 
	    	if(colecaoContas != null && !colecaoContas.isEmpty()){
	    		contaEncontrada = (Conta) Util.retonarObjetoDeColecao(colecaoContas);
	    	}
	    	else{
	    		
	    		FiltroContaHistorico filtroContaHistorico = new FiltroContaHistorico();
		    	filtroContaHistorico.adicionarCaminhoParaCarregamentoEntidade("localidade");
		    	
	    		filtroContaHistorico.adicionarParametro(new ParametroSimples(FiltroContaHistorico.IMOVEL_ID,idImovel));
		    	filtroContaHistorico.adicionarParametro(new ParametroSimples(FiltroContaHistorico.ANO_MES_REFERENCIA,anoMesConta));
	    	
		    	colecaoContas = fachada.pesquisar(filtroContaHistorico, ContaHistorico.class.getName());
	    	
		    	if(colecaoContas != null && !colecaoContas.isEmpty()){
		    		
		    		ContaHistorico contaHistorico = (ContaHistorico) Util.retonarObjetoDeColecao(colecaoContas); 
		    		
		    		contaEncontrada = new Conta();
		    		contaEncontrada.setLocalidade(contaHistorico.getLocalidade());
		    		
		    	}
	    	}
	     	
	    	//Retorna a conta encontrada ou nulo se não existir a conta 
	     	return contaEncontrada;
	}
	
	/**
	 * Verifica se o usuário preencheu o imóvel ou o cliente, não pode existir os doi nem nenhum
	 *
	 * [FS0010] Verificar preenchimento do imóvel e do cliente 
	 *
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 *
	 * @param idImovel
	 * @param idCliente
	 */
	private void verificarPreeenchimentoImovelECliente(String idImovel,String idCliente){
		 
		//Caso o usuário não tenha informado o imóvel 
		 if(idImovel == null || idImovel.trim().equalsIgnoreCase("")){
			 //Caso o usuário não tenha informado o cliente, levanta uma exceção indicando que o 
			 //usuário não informou nem o imóvel nem o cliente 
			 if(idCliente == null || idCliente.trim().equalsIgnoreCase("")){
				 throw new ActionServletException("atencao.naoinformado", null, "Matrícula do Imóvel ou Código do Cliente");
			 }
		 }else{
			 //Caso o usuário tenha informado o imóvel e o cliente, levanta uma exceção para 
			 //indicando que só pode informar um dos dois
			 if(idCliente != null && !idCliente.trim().equalsIgnoreCase("")){
				 throw new ActionServletException("atencao.cliente_imovel_informado");
			 }
		 }
	}
	
	
	/**
	 * Este fluxo secundário tem como objetivo verificar a existência do cliente informado pelo usuário
	 *
	 * [FS0011] Verificar existência do código do cliente  
	 *
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 *
	 * @param idCliente
	 * @return
	 */
	private Cliente verificarExistenciaCodigoCliente(Integer idCliente){
		
		//Cria a variável que vai armazenar o cliente pesquisado
		Cliente clienteEncontrado = null;
		
		//Cria uma instância da fachada 
		Fachada fachada = Fachada.getInstancia();
		
		//Pesquisa o cliente informado pelo usuário no sistema 
		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));
		Collection<Cliente> colecaoCliente =  fachada.pesquisar(filtroCliente, Cliente.class.getName());
		
		//Caso exista o cliente no sistema 
		//Retorna para o usuário o cliente retornado pela pesquisa
		//Caso contrário retorna um objeto nulo 
		if(colecaoCliente != null && !colecaoCliente.isEmpty()){
			clienteEncontrado =(Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
		}else{
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Cliente");
		}
		
		//Retorna o cliente encontrado ou nulo se não existir 
		return clienteEncontrado;
	}
	
	
	/**
	 * Verifica se o usuário informou o código da guia de pagamento e o tipo de débito,
	 * só pode ser informado um dos dois 
	 *
	 * [FS0021] Verificar preenchimento da guia de pagamento e do tipo de débito 
	 *
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 *
	 * @param idGuiaPagamento
	 * @param idTipoDebito
	 */
	private void verificarPreeenchimentoGuiaPagamentoETipoDebito(String idGuiaPagamento,String idTipoDebito){
		 
		 //Caso o usuário não informou a guia de pagamento
		 if(idGuiaPagamento == null || idGuiaPagamento.trim().equalsIgnoreCase("")){
			 //Caso o usuário não informou o tipo de débito, levanta uma exceção para o usário
			 //indicando que o usuário precisa informar ou a guia ou o tipo de débito
			 if(idTipoDebito == null || idTipoDebito.trim().equalsIgnoreCase("")){
				 throw new ActionServletException("atencao.naoinformado", null, "Guia Pagamento ou Tipo de Débito");
			 }
		 }else{
			 //Caso o usuário informou a guia de pagamento e o tipo de débito, 
			 //levanta uma exceção para o usário indicando que o usuário informou a guia e o tipo de débito
			 if(idTipoDebito != null && !idTipoDebito.trim().equalsIgnoreCase("")){
				 throw new ActionServletException("atencao.guia_pagamento_tipo_debito_informado");
			 }
		 }
	}
	
	
	/**
	 * Verifica se existe guia de pagamento como código da guia informado para o cliente ou imóvel
	 *
	 * [FS0022] Verificar existência da guia de pagamento
	 *
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 *
	 * @param idImovel
	 * @param idCliente
	 * @param idGuiaPagamento
	 * @return
	 */
	private GuiaPagamento verificarExistenciaGuiaPagamento(String idImovel, String idCliente, String idGuiaPagamento){
	 	
			//Cria a variável que vai armazenar a guiade pagamento pesquisada
		 	GuiaPagamento guiaPagamentoDigitada = null;
		 	
		 	//Cria uma instância da fachada
		 	Fachada fachada = Fachada.getInstancia();
		 	
		 	//Cria o filtro da guia para pesquisar
			FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
			FiltroGuiaPagamentoHistorico filtroGuiaPagamentoHistorico = new FiltroGuiaPagamentoHistorico();
			
			//Caso o usuário tenha informado o imóvel, seta o código do imóvel no filtro da guia 
			if(idImovel != null && !idImovel.trim().equalsIgnoreCase("")){
			  filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.IMOVEL_ID,idImovel));
			  filtroGuiaPagamentoHistorico.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoHistorico.IMOVEL_ID,idImovel));
			  
			  //Caso o usuário tenha informado o cliente, seta o código do cliente no filtro da guia
			}else if(Util.verificarNaoVazio(idCliente)){
			  filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.CLIENTE_ID,idCliente));
			  filtroGuiaPagamentoHistorico.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoHistorico.CLIENTE_ID,idCliente));
			  
			  //Caso não tenha informado nem o imóvel e nem o cliente, levanta uma exceção para o usuário
			  //indicando que o imóvel ou o cliente tem de ser informados
			}else{
			  throw new ActionServletException("atencao.naoinformado", null, "Imóvel ou Cliente");
			}
			
			//Seta o códigoda guia no filtro e indica quais objetos para carregar no retorno da pesquisa
			filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.ID,idGuiaPagamento));
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("localidade");
			
			//Pesquisa a guia de pagamento no sistema
			Collection colecaoGuiaPagamento = fachada.pesquisar(filtroGuiaPagamento, GuiaPagamento.class.getName());
			
			//Caso não exista guia de pagamento cadastrada no sistema, levanta uma exceção para o
			//usuário indicando que a guia de apagamento não está cadastrada
			//Caso contrário recupera a guia de pagamento pesquisada
			if(colecaoGuiaPagamento != null && !colecaoGuiaPagamento.isEmpty()){
			  // throw new ActionServletException("atencao.naocadastrado",null, "Guia de Pagamento");
				guiaPagamentoDigitada = (GuiaPagamento)Util.retonarObjetoDeColecao(colecaoGuiaPagamento);
			}
			else{
				
				filtroGuiaPagamentoHistorico.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoHistorico.ID,idGuiaPagamento));
				filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
				filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("localidade");
				
				colecaoGuiaPagamento = fachada.pesquisar(filtroGuiaPagamentoHistorico, GuiaPagamentoHistorico.class.getName());
				
				if(colecaoGuiaPagamento == null || colecaoGuiaPagamento.isEmpty()){
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Guia de Pagamento");
				}
				else{
					
					GuiaPagamentoHistorico guiaPagamentoHistorico = (GuiaPagamentoHistorico) 
					Util.retonarObjetoDeColecao(colecaoGuiaPagamento);
					
					guiaPagamentoDigitada = new GuiaPagamento();
					guiaPagamentoDigitada.setDebitoTipo(guiaPagamentoHistorico.getDebitoTipo());
					guiaPagamentoDigitada.setLocalidade(guiaPagamentoHistorico.getLocalidade());
					guiaPagamentoDigitada.setImovel(guiaPagamentoHistorico.getImovel());
					guiaPagamentoDigitada.setCliente(guiaPagamentoHistorico.getCliente());
					guiaPagamentoDigitada.setValorDebito(guiaPagamentoHistorico.getValorDebito());
				}
			}
		 	
			//Retorna a guia de pagamento pesquisada ou um objeto vazio se a guia não existir 
		 	return guiaPagamentoDigitada;
		 }
	
	
	/**
	 * Verifica se a localidade informada é a mesma da guia de pagamento
	 *
	 * [FS0014] Verificar localidade da guia de pagamento
	 *
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 *
	 * @param guiaPagamento
	 * @param idLocalidade
	 */
	private void verificarLocalidadeGuiaPagamento(GuiaPagamento guiaPagamento,String idLocalidade){
		 
	 //Caso o usuário tenha informado a localidade	
	 if(idLocalidade != null && !idLocalidade.trim().equalsIgnoreCase("")){
		 //Caso a localidade da guia de pagamento seja diferente da localidade informada
		 if(!guiaPagamento.getLocalidade().getId().equals(new Integer(idLocalidade))){
			 
			//Cria a mensagem que vai ser exibida ao usuário
			//e levanta a exceção 
			String mensagem = "A Localidade da Guia de Pagamento "+guiaPagamento.getLocalidade().getId()+" é diferente da Localidade informada "+idLocalidade;
			throw new ActionServletException("atencao.localidade_guia_pagamento_diferente", null, mensagem); 
		 }
	 }
	}
	
	/**
	 * Este fluxo secundário tem como objetivo pesquisar o tipo de débito informado pelo usuário
	 *
	 * [FS0020] Verificar existência do tipo de débito 
	 *
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 *
	 * @param idTipoDebito
	 * @return
	 */
	private DebitoTipo verificarExistenciaTipoDebito(Integer idTipoDebito){
		
		//Variável que vai armazenar o tipo de dpébito digitado pelo usuário 
		DebitoTipo tipoDebitoEncontrado = null;
		
		//Cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		//Pesquisa o tipo de débito informado pelo usuário no sistema 
		FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
		filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.ID, idTipoDebito));
		Collection<DebitoTipo> colecaoDebitoTipo =  fachada.pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());
		
		//Caso exista o tipo de débito no sistema 
		//Retorna para o usuário o tipo de débito retornado pela pesquisa
		//Caso contrário retorna um objeto nulo 
		if(colecaoDebitoTipo == null || colecaoDebitoTipo.isEmpty()){
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Tipo de Débito");
		}else{
			tipoDebitoEncontrado =(DebitoTipo) Util.retonarObjetoDeColecao(colecaoDebitoTipo);
		}
		
		//Retorna o tipo de débito encontrado ou nulo se não existir 
		return tipoDebitoEncontrado;
	}
	
	/**
	 * Verifica a existência de guia de pagamento com o tipo de débito e o imóvel informados
	 *
	 * [FS0013] Verificar existência de guia de pagamento com tipo de débito informado
	 *
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 *
	 * @param idGuiaPagamento
	 * @param idImovel
	 */
	private GuiaPagamento verificarExistenciaGuiaPagamentoComTipoDebito(DebitoTipo tipoDebito,String idImovel, String idCliente){
		 
	  //Cria a variável que vai armazenar a guia de pagamento pesquisada	
	  GuiaPagamento guiaPagamento = null;
		
	  //Cria o filtro de guia de pagamento, e seta os parâmetros para pesquisar 
	  FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
	  filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.DEBITO_TIPO_ID, tipoDebito.getId()));
	  filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.DEBITO_CREDITO_SITUACAO_ATUAL_ID, DebitoCreditoSituacao.NORMAL));
	
	  FiltroGuiaPagamentoHistorico filtroGuiaPagamentoHistorico = new FiltroGuiaPagamentoHistorico();
	  filtroGuiaPagamentoHistorico.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoHistorico.DEBITO_TIPO_ID, tipoDebito.getId()));
	  filtroGuiaPagamentoHistorico.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoHistorico.DEBITO_CREDITO_SITUACAO_ATUAL_ID, DebitoCreditoSituacao.NORMAL));
	  
	  //Caso o usuário tenha informado a matrícula do imóvel,
	  //seta a metrículo do imóvel no filtro
	  //Caso contrário seta o códigodo cliente no filtro
	  if(idImovel != null && !idImovel.trim().equalsIgnoreCase("")){
		filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.IMOVEL_ID, idImovel));
		filtroGuiaPagamentoHistorico.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoHistorico.IMOVEL_ID, idImovel));
	  }else{
		filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.CLIENTE_ID, idCliente));
		filtroGuiaPagamentoHistorico.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoHistorico.CLIENTE_ID, idCliente));
	  }

	  //Pesquisa as guias de pagamento no sistema 
	  Collection colecaoGuiaPagamento = Fachada.getInstancia().pesquisar(filtroGuiaPagamento,GuiaPagamento.class.getName()); 
		
	  //Caso exista guia de pagamento cadastrada no sistema com os parâmetros informados no filtro
	  if(colecaoGuiaPagamento != null && !colecaoGuiaPagamento.isEmpty()){
		  
		//Caso exista mais que uma guia de pagamento cadastrada  
	    if(colecaoGuiaPagamento.size() > 1){
		  
	      //Cria a variável que vai armazenar a mensagem que vai ser exibida ao usuário 	
	      String mensagem = null;
	      
	      //Caso a pequisa foi para imóvel
	      //Cria a mensagem para imóvel
	      //Caso contrário cria a mensagem para cliente
		  if(idImovel != null && !idImovel.trim().equalsIgnoreCase("")){
		    mensagem = "Há mais de uma Guia de Pagamento com o tipo de débito "+ tipoDebito.getDescricao() +" para o Imóvel "+idImovel+". Efetue uma pesquisa para selecionar a Guia";	 
		  }else{
			mensagem = "Há mais de uma Guia de Pagamento com o tipo de débito "+ tipoDebito.getDescricao() +" para o Cliente "+idCliente+". Efetue uma pesquisa para selecionar a Guia";
		  }
		  //levanta a exceção para o usuário com a mensagem criada
		  throw new ActionServletException("atencao.descricao_concatenada", null, mensagem);
		  
		}else{
		  //Caso só exista apenas uma guia de pagamento cadastrada para o tipo de débito		
		  guiaPagamento = (GuiaPagamento) Util.retonarObjetoDeColecao(colecaoGuiaPagamento);
		}
	  }
	  else{
		  
		  colecaoGuiaPagamento = Fachada.getInstancia()
		  .pesquisar(filtroGuiaPagamentoHistorico, GuiaPagamentoHistorico.class.getName());
		  
		  if(colecaoGuiaPagamento != null && !colecaoGuiaPagamento.isEmpty()){
			  
			  //Caso exista mais que uma guia de pagamento cadastrada  
			  if(colecaoGuiaPagamento.size() > 1){
				  
			      //Cria a variável que vai armazenar a mensagem que vai ser exibida ao usuário 	
			      String mensagem = null;
			      
			      //Caso a pequisa foi para imóvel
			      //Cria a mensagem para imóvel
			      //Caso contrário cria a mensagem para cliente
				  if(idImovel != null && !idImovel.trim().equalsIgnoreCase("")){
				    mensagem = "Há mais de uma Guia de Pagamento com o tipo de débito "+ tipoDebito.getDescricao() +" para o Imóvel "+idImovel+". Efetue uma pesquisa para selecionar a Guia";	 
				  }else{
					mensagem = "Há mais de uma Guia de Pagamento com o tipo de débito "+ tipoDebito.getDescricao() +" para o Cliente "+idCliente+". Efetue uma pesquisa para selecionar a Guia";
				  }
				  //levanta a exceção para o usuário com a mensagem criada
				  throw new ActionServletException("atencao.descricao_concatenada", null, mensagem);
				  
			  }
			  else{
				  //Caso só exista apenas uma guia de pagamento cadastrada para o tipo de débito		
				  GuiaPagamentoHistorico guiaPagamentoHistorico = (GuiaPagamentoHistorico) 
				  Util.retonarObjetoDeColecao(colecaoGuiaPagamento);
				  
				  guiaPagamento = new GuiaPagamento();
				  guiaPagamento.setLocalidade(guiaPagamentoHistorico.getLocalidade());
			  }
		  }
	  }
		
	  //Retorna a guia de pagamento encontrada ou nulo se não existir a guia de pagamento 
	  return guiaPagamento;
	}
	
	
	/**
	 * Verifica se o usuário informou o código do débito a cobrar e o tipo de débito,
	 * só pode ser informado um dos dois 
	 *
	 * [FS0023] Verificar preenchimento do débito a cobrar e do tipo de débito 
	 *
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 *
	 * @param idDebitoACobrar
	 * @param idTipoDebito
	 */
	private void verificarPreeenchimentoDebitoACobrarETipoDebito(String idDebitoACobrar,String idTipoDebito){
		 
		//Caso o usuário não informou o débito a cobrar
		 if(idDebitoACobrar == null || idDebitoACobrar.trim().equalsIgnoreCase("")){
			 //Caso o usuário não informou o tipo de débito, levanta uma exceção para o usário
			 //indicando que o usuário precisa informar ou o débito a cobrar ou o tipo de débito
			 if(idTipoDebito == null || idTipoDebito.trim().equalsIgnoreCase("")){
				 throw new ActionServletException("atencao.naoinformado", null, "Débito a Cobrar ou Tipo de Débito");
			 }
		 }else{
			 //Caso o usuário informou o débito a cobrar e o tipo de débito, levanta uma exceção
			 //para o usário indicando que o usuário informou o débito a cobrar e o tipo de débito
			 if(idTipoDebito != null && !idTipoDebito.trim().equalsIgnoreCase("")){
				 throw new ActionServletException("atencao.debto_a_cobrar_tipo_debito_informado");
			 }
		 }
	}
	
	
	/**
	 *
	 * Verifica se o débito a cobrar do imóvel informado pelo usuário existe no sistema
	 *
	 * [FS0024] Verificar existência do débito a cobrar 
	 *
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 *
	 * @param idImovel
	 * @param idDebitoACobrar
	 * @return
	 */
	private DebitoACobrar verificarExistenciaDebitoACobrar(String idImovel, String idDebitoACobrar){
	 	
		//Cria a variável que vai armazenar o débito a cobrar pesquisado
		DebitoACobrar debitoACobrarDigitado = null;
	 	
		//Cria uma instância da fachada
	 	Fachada fachada = Fachada.getInstancia();
	 	
	 	//Cria o filtro de débito a cobrar e seta todos os parâmetros para pesquisar o débito a cobrar do imóvel
		FiltroDebitoACobrar filtroDebitoACobrar = new FiltroDebitoACobrar();
		filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.IMOVEL_ID,idImovel));
		filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.ID,idDebitoACobrar));
		filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("localidade");
		
		Collection colecaoDebitoACobrar = fachada.pesquisar(filtroDebitoACobrar, DebitoACobrar.class.getName());
		
		//Caso exista o débito a cobrar para o imóvel informado cadastrado no sistema 
		//Retorna para o usuário o débito a cobrar retornado pela pesquisa
		//Caso contrário retorna um objeto nulo 
		if(colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()){
		  
			debitoACobrarDigitado = (DebitoACobrar)Util.retonarObjetoDeColecao(colecaoDebitoACobrar);
		}
		else{
			
			FiltroDebitoACobrarHistorico filtroDebitoACobrarHistorico = new FiltroDebitoACobrarHistorico();
			
			filtroDebitoACobrarHistorico.adicionarParametro(new ParametroSimples(FiltroDebitoACobrarHistorico.IMOVEL_ID,idImovel));
			filtroDebitoACobrarHistorico.adicionarParametro(new ParametroSimples(FiltroDebitoACobrarHistorico.ID,idDebitoACobrar));
			filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("localidade");
			
			colecaoDebitoACobrar = fachada.pesquisar(filtroDebitoACobrarHistorico, DebitoACobrarHistorico.class.getName());
			
			if(colecaoDebitoACobrar == null || colecaoDebitoACobrar.isEmpty()){
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Débito a Cobrar");
			}
			else{
				
				DebitoACobrarHistorico debitoACobrarHistorico = (DebitoACobrarHistorico)Util.retonarObjetoDeColecao(colecaoDebitoACobrar);
			
				debitoACobrarDigitado = new DebitoACobrar();
				debitoACobrarDigitado.setDebitoTipo(debitoACobrarHistorico.getDebitoTipo());
				debitoACobrarDigitado.setLocalidade(debitoACobrarHistorico.getLocalidade());
			}
		}
	 	
		//Retorna o débito a cobrar encontrado ou nulo se não existir o débito a cobrar 
	 	return debitoACobrarDigitado;
	 }
	
	
	/**
	 * Verifica se a localidade informada é a mesma do débito a cobrar 
	 *
	 * [FS0017] Verificar localidade do débito a cobrar 
	 *
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 *
	 * @param debitoACobrar
	 * @param idLocalidade
	 */
	private void verificarLocalidadeDebitoACobrar(DebitoACobrar debitoACobrar,String idLocalidade){
	  //Caso o usuário tenha informado a localidade	
	  if(idLocalidade != null && !idLocalidade.trim().equalsIgnoreCase("")){
		 
	    //Caso a localidade do débito a cobrar seja diferente da localidade informada
		if(!debitoACobrar.getLocalidade().getId().equals(new Integer(idLocalidade))){
			 
		  //Cria a mensagem que vai ser exibida ao usuário
		  //e levanta a exceção 
		  String mensagem = "A Localidade do Débito a Cobrar "+debitoACobrar.getLocalidade().getId()+" é diferente da Localidade informada "+idLocalidade;
		  throw new ActionServletException("atencao.localidade_debito_a_cobrar_diferente", null, mensagem); 
		}
	  } 
	}
	
	
	/**
	 * Verifica a existência de débito a cobrar com o tipo de débito e o imóvel informados
	 *
	 * [FS0016] Verificar existência de débito a cobrar com tipo de débito informado 
	 *
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 *
	 * @param idTipoDebito
	 * @param idImovel
	 */
	private DebitoACobrar verificarExistenciaDebitoACobrarComTipoDebito(DebitoTipo tipoDebito,String idImovel, BigDecimal valorInformado){

	  //Cria a variável que vai armazenar o débito a cobrar pesquisado	
	  DebitoACobrar debitoACobrar = null;

	  //Cria o filtro de débito a cobrar, e seta os parâmetros para pesquisar 
	  FiltroDebitoACobrar filtroDebitoACobrar = new FiltroDebitoACobrar();
	  filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.DEBITO_TIPO_ID, tipoDebito.getId()));
	  filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.IMOVEL_ID, idImovel));
	  filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.DEBITO_CREDITO_SITUACAO_ATUAL_ID, DebitoCreditoSituacao.NORMAL));
	  Collection colecaoDebitoACobrar = Fachada.getInstancia().pesquisar(filtroDebitoACobrar,DebitoACobrar.class.getName());
		
	  DebitoACobrar debitoColecao = null;
	  //Caso exista débito a cobrar cadastrado com o tipo de débito informado
	  if(colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()){
		 debitoColecao = (DebitoACobrar)colecaoDebitoACobrar.iterator().next();
		//Caso exista mais que um débito a cobrar cadastrado para o tipo de débito 
		//Monta a mensagem para o usuário e levanta a exceção  
	    if(colecaoDebitoACobrar.size() > 1){
	      BigDecimal valorFaltaDebito = BigDecimal.ZERO;
	      
	      BigDecimal valorDebito = debitoColecao.getValorDebito();
	      short numeroPrestacaoDebito = debitoColecao.getNumeroPrestacaoDebito();
	      short numeroPrestacaoCobrada = debitoColecao.getNumeroPrestacaoCobradas();
	      
	      valorDebito = valorDebito.divide(new BigDecimal(numeroPrestacaoDebito+""));
	      
	      valorFaltaDebito = valorFaltaDebito.add(debitoColecao.getValorDebito());
	      valorFaltaDebito = valorFaltaDebito.subtract(valorDebito);
	      
	      valorFaltaDebito = valorFaltaDebito.multiply(new BigDecimal(numeroPrestacaoCobrada+""));
	      
	      
	      if(!valorInformado.equals(valorFaltaDebito)){
	    	  String mensagem = "Há mais de um Débito a Cobrar com o tipo de débito "+ tipoDebito.getDescricao() +" para o Imóvel "+idImovel + "  " + valorFaltaDebito;
	    	  throw new ActionServletException("atencao.descricao_concatenada", null, mensagem);
	      }
		}else{
		  //Caso só exista apenas um débito a cobrar cadastrado para o tipo de débito	
		  debitoACobrar =(DebitoACobrar) Util.retonarObjetoDeColecao(colecaoDebitoACobrar);
	    }
	  }
	  else{
		  
		  FiltroDebitoACobrarHistorico filtroDebitoACobrarHistorico = new FiltroDebitoACobrarHistorico();
		  filtroDebitoACobrarHistorico.adicionarParametro(new ParametroSimples(FiltroDebitoACobrarHistorico.DEBITO_TIPO_ID, tipoDebito.getId()));
		  filtroDebitoACobrarHistorico.adicionarParametro(new ParametroSimples(FiltroDebitoACobrarHistorico.IMOVEL_ID, idImovel));
		  filtroDebitoACobrarHistorico.adicionarParametro(new ParametroSimples(FiltroDebitoACobrarHistorico.DEBITO_CREDITO_SITUACAO_ATUAL_ID, DebitoCreditoSituacao.NORMAL));
		 
		  colecaoDebitoACobrar = Fachada.getInstancia().pesquisar(filtroDebitoACobrarHistorico,DebitoACobrarHistorico.class.getName());
		  
		  //Caso exista mais que um débito a cobrar cadastrado para o tipo de débito 
		  //Monta a mensagem para o usuário e levanta a exceção  
		  if(colecaoDebitoACobrar.size() > 1){
			  
			  DebitoACobrarHistorico debitoColecaoHistorico = (DebitoACobrarHistorico)colecaoDebitoACobrar.iterator().next();
			  
			  BigDecimal valorFaltaDebito = BigDecimal.ZERO;
			  
		      
		      BigDecimal valorDebito = debitoColecaoHistorico.getValorDebito();
		      short numeroPrestacaoDebito = debitoColecaoHistorico.getPrestacaoDebito();
		      short numeroPrestacaoCobrada = debitoColecaoHistorico.getPrestacaoCobradas();
		      
		      valorDebito = valorDebito.divide(new BigDecimal(numeroPrestacaoDebito+""));
		      
		      valorFaltaDebito = valorFaltaDebito.add(debitoColecaoHistorico.getValorDebito());
		      
		      valorFaltaDebito = valorFaltaDebito.subtract(valorDebito);
		      
		      valorFaltaDebito = valorFaltaDebito.multiply(new BigDecimal(numeroPrestacaoCobrada+""));

		      if(!valorInformado.equals(valorFaltaDebito)){
		    	  String mensagem = "Há mais de um Débito a Cobrar com o tipo de débito "+ tipoDebito.getDescricao() +" para o Imóvel "+idImovel;
				  throw new ActionServletException("atencao.descricao_concatenada", null, mensagem); 
		      }
		  }
		  else{
			  //Caso só exista apenas um débito a cobrar cadastrado para o tipo de débito	
			  debitoACobrar = new DebitoACobrar();
		  }
	  }
		
	  //Retorna o débito a cobrar encontrado ou nulo se não existir o débito a cobrar 
	  return debitoACobrar;
	}
	
	/**
	 * @author Raphael Rossiter
	 * @date 24/09/2007
	 */
	private Pagamento validarDadosPagamento(DynaValidatorActionForm pagamentoActionForm, 
			Fachada fachada, HttpServletRequest httpServletRequest){
		
		//Cria o objeto que vai armazenar os dados de pagamento
        Pagamento pagamento = new Pagamento();
        
        
        //[FS0001] - Validar data do pagamento
        //Recupera a data de pagamento e verifica se a data é uma data válida
        String dataPagamentoString = (String)pagamentoActionForm.get("dataPagamento");
        Date dataPagamento = null;
        SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dataPagamento = dataFormato.parse(dataPagamentoString);
        } catch (ParseException ex) {
        	throw new ActionServletException("atencao.data_pagamento_invalida");
        }
        
        //Recupera a forma de arrecadaação e pesquisa o objeto no sistema
        String idFormaArrecadacao = (String)pagamentoActionForm.get("idFormaArrecadacao");
        ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
        arrecadacaoForma.setId(new Integer(idFormaArrecadacao));
        
        //Recupera o aviso bancário e pequisa o objeto no sistema
        String idAvisoBancario = (String)pagamentoActionForm.get("idAvisoBancario");
        FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();
        filtroAvisoBancario.adicionarParametro(new ParametroSimples(FiltroAvisoBancario.ID, idAvisoBancario));
        AvisoBancario avisoBancario = (AvisoBancario)(fachada.pesquisar(filtroAvisoBancario,AvisoBancario.class.getName())).iterator().next();
        
        //Recupera o valor do pagamento
        BigDecimal valorPagamento = Util.formatarMoedaRealparaBigDecimal(
        (String)pagamentoActionForm.get("valorPagamento"));
        	
        //Recupera o código do imóvel
        String idImovel = (String)pagamentoActionForm.get("idImovel");
        	
        //Cria a variável que vai armazenar o imóvel informado
        Imovel imovel = null;
        	
        //Caso o usuário tenha informado o imóvel
        if(idImovel != null && !idImovel.trim().equalsIgnoreCase("")){
          //[FS0008] - Verificar existência da matrícula do imóvel
          imovel = this.verificarExistenciaMatriculaImovel(idImovel);
        }
        	
        //Recupera o tipo de documento
        String idTipoDocumento = (String)pagamentoActionForm.get("idTipoDocumento");
        	
        //Recupera o código da localidade
		String idLocalidade = (String)pagamentoActionForm.get("idLocalidade");
			
		//Cria a variável que vai armazenar a localidade informada 
		Localidade localidade = null;
			
		//Caso o tipo de documento não seja conta
		if(!idTipoDocumento.equals(DocumentoTipo.CONTA.toString())){
		  if(idLocalidade != null && !idLocalidade.equalsIgnoreCase("")){
			//[FS0007] - Verificar existência da localidade  
			localidade = this.verificarExistenciaLocalidade(new Integer(idLocalidade));
		  }
		}
			
		//[FS0009] - Verificar localidade da matrícula do imóvel 
		if(! this.verificarLocalidadeMatriculaImovel(idLocalidade, imovel)){
			throw new ActionServletException("atencao.localidade_imovel_diferente",imovel.getLocalidade().getId().toString(),idLocalidade);
      	}
			
		//Recupera o tipo de débito
		String idTipoDebito = (String)pagamentoActionForm.get("idTipoDebito");
			
		//Cria a variável que vai armazenar o tipo de débito 
		DebitoTipo debitoTipo = null;
        	
		//Formata a referência do pagamento para o formato AAAAMM
        Integer anoMesReferenciaPagamento = new Integer(Util.formatarMesAnoParaAnoMesSemBarra(
        dataPagamentoString.substring(3,10)));
        
        //Recupera os parâmetros do sistema 
    	SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

        //cria a variável que vai armazenar a referência da arrecadação
		int anoMesReferenciaArrecadacao;
			
		//Caso a referência do pagamento seja maior que a referência da arrecadação
		//dos parâmetros do sistema, atribui a referência do pagamento a referência da arrecadação
		//Caso contrário atribui a referência da arrecadação do parâmetro do sistema a referência de arrecadação
		if(anoMesReferenciaPagamento > sistemaParametro.getAnoMesArrecadacao()){
			anoMesReferenciaArrecadacao = anoMesReferenciaPagamento;
		}else{
			anoMesReferenciaArrecadacao = sistemaParametro.getAnoMesArrecadacao();
		}

		//Caso nenhum tipo de documento informado, levanta uma exceção para o usuário 
		//indicando que o tipo de documento não foi informado
        if(idTipoDocumento == null || idTipoDocumento.equalsIgnoreCase("")){
        	throw new ActionServletException("atencao.naoinformado",null, "Tipo de Documento");
        }else{
        		
        	//Cria o objeto que vai armazenar o tipo de documento
        	DocumentoTipo documentoTipo = new DocumentoTipo();
        		
        	//Caso o tipo de documento seja conta
        	if(idTipoDocumento.equals(DocumentoTipo.CONTA.toString())){
        
        		//Seta o tipo de documento para conta
        		documentoTipo.setId(new Integer(DocumentoTipo.CONTA));
        		documentoTipo.setDescricaoAbreviado(ConstantesSistema.TIPO_PAGAMENTO_CONTA);
        		documentoTipo.setDescricaoDocumentoTipo(ConstantesSistema.TIPO_PAGAMENTO_CONTA);
        			
        		//Recupera a referência da conta 
        		String referenciaConta = (String)pagamentoActionForm.get("referenciaConta");
        			
        		//[FS0012] - Verificar existência da conta
        		Conta conta = this.verificarExistenciaConta(referenciaConta, idImovel);
        		
        		//Formata a referência da conta para AAAAMM
        		Integer anoMesReferencia = new Integer(Util.formatarMesAnoParaAnoMesSemBarra(referenciaConta));
        			
        		//Cria o pagamento para conta
        		pagamento.setAnoMesReferenciaPagamento(anoMesReferencia);
        		pagamento.setAnoMesReferenciaArrecadacao(anoMesReferenciaArrecadacao);
        		pagamento.setValorPagamento(valorPagamento);
        		pagamento.setDataPagamento(dataPagamento);
        		pagamento.setPagamentoSituacaoAtual(null);
        		pagamento.setPagamentoSituacaoAnterior(null);
        		pagamento.setDebitoTipo(null);
        		
        		ContaGeral contaGeral = new ContaGeral();
    			if (conta != null) {
    				contaGeral.setId(conta.getId());
    				contaGeral.setConta(conta);
    			}
        		
        		pagamento.setContaGeral(contaGeral);
        		pagamento.setGuiaPagamento(null);
        		pagamento.setDebitoACobrarGeral(null);
        		pagamento.setDocumentoTipo(documentoTipo);
        		pagamento.setAvisoBancario(avisoBancario);
        		pagamento.setImovel(imovel);
        		pagamento.setArrecadadorMovimentoItem(null);
        		pagamento.setArrecadacaoForma(arrecadacaoForma);
        		pagamento.setUltimaAlteracao(new Date());
        		pagamento.setCliente(null);

        		DocumentoTipo documentoTipoAgregador = new DocumentoTipo();
    			documentoTipoAgregador.setId(DocumentoTipo.CONTA);
    			pagamento.setDocumentoTipoAgregador(documentoTipoAgregador);
    			
    			pagamento.setDataProcessamento(new Date());

        		//Caso a conta com a referência informada não esteja cadastrada no sistema
    			if(conta == null){
    			  
    				/*throw new ActionServletException("atencao.pesquisa.conta_referencia_imovel_inexistente",null, 
    				Util.formatarAnoMesParaMesAno(referenciaConta), idImovel.toString());*/
    				
    				/*
    				 * Colocado por Raphael Rossiter em 09/06/2008 - Analista: Aryed e Eduardo 
    				 * OBJ: Atribuir a localidade do imóvel no pagamento
    				 */
    				pagamento.setLocalidade(imovel.getLocalidade());
    				
    				httpServletRequest.setAttribute("msg", "Não há Conta com a referência " 
    				+ referenciaConta + " para o Imóvel " + idImovel);
    				
    				httpServletRequest.setAttribute("pagamento", String.valueOf(
    				GcomAction.obterTimestampIdObjeto(pagamento)));
    				  
      		    }
    			else{
    				pagamento.setLocalidade(conta.getLocalidade());
    			}
        			
        	//Caso o tipo de documento seja guia de pagamento
        	}else if(idTipoDocumento.equals(DocumentoTipo.GUIA_PAGAMENTO.toString())){
        			
        		//Seta o tipo de documento para guia de pagamento
        		documentoTipo.setId(new Integer(DocumentoTipo.GUIA_PAGAMENTO));
        		documentoTipo.setDescricaoAbreviado(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO);
        		documentoTipo.setDescricaoDocumentoTipo(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO);
        			
        		//Recupera o código do cliente
        		String idCliente = (String)pagamentoActionForm.get("idCliente");

        		//[FS0010] - Verificar preenchimento do imóvel e do cliente
             	this.verificarPreeenchimentoImovelECliente(idImovel, idCliente);
                	
                //Caso o usuário tenha informado o cliente
                //Recupera o cliente informado do sistema
                Cliente cliente = null;
                if(idCliente != null && !idCliente.trim().equalsIgnoreCase("")){
                	//[FS0011] - Verificar existência do código do cliente 
                  	cliente = this.verificarExistenciaCodigoCliente(new Integer(idCliente));
                }
                	
                //Recupera o código da guia de pagamento
        		String idGuiaPagamento = (String)pagamentoActionForm.get("idGuiaPagamento");
        			
        		//[FS0021] - Verificar preenchimento da guia de pagamento e do tipo de débito
        		this.verificarPreeenchimentoGuiaPagamentoETipoDebito(idGuiaPagamento, idTipoDebito);

        		//Caso o usuário informou a guia de pagamento
        		//Recupera a guia de pagamento informada do sistema 
        		GuiaPagamento guiaPagamento = null;
        		if(idGuiaPagamento != null && !idGuiaPagamento.trim().equals("")){
        		  //[FS0022] - Verificar existência da guia de pagamento
        		  guiaPagamento = this.verificarExistenciaGuiaPagamento(idImovel, idCliente, idGuiaPagamento);
        			
        		  //Caso existaa guia de pagamento, seta o tipo de débito da guia de pagamento 
        		  //para o tipo de débito do pagamento que vai ser inserido
        		  debitoTipo = guiaPagamento.getDebitoTipo();
        			  
        		  //[FS0014] - Verificar localidade da guia de pagamento
        		  this.verificarLocalidadeGuiaPagamento(guiaPagamento,idLocalidade);
        		}
        			
        			
        		//Caso o tipo de débito tenha sido informado
    			if(idTipoDebito != null && !idTipoDebito.trim().equals("")){
      			  //[FS0020] - Verificar existência do tipo de débito
      			  debitoTipo = this.verificarExistenciaTipoDebito(new Integer(idTipoDebito));
      			
      			  //[FS0013] - Verificar existência de guia de pagamento com o tipo de débito informado 
      			  if(idImovel != null && !idImovel.trim().equalsIgnoreCase("")){
      			    guiaPagamento =  this.verificarExistenciaGuiaPagamentoComTipoDebito(debitoTipo,idImovel, null);
      			    
      			  }

      			  if(idCliente != null && !idCliente.trim().equalsIgnoreCase("")){
       			    guiaPagamento =  this.verificarExistenciaGuiaPagamentoComTipoDebito(debitoTipo,null, idCliente);
       			    
        		  }
      			  
      			  //Caso não exista nenhuma guia de pagamento com o tipo de débito informado
      			  if(guiaPagamento == null){
          			  
          			if(idImovel != null && !idImovel.trim().equalsIgnoreCase("")){
          				  
          				/*throw new ActionServletException("atencao.descricao_concatenada", null, 
          				"Não há Guia de Pagamento com o tipo de débito " 
          				+ debitoTipo.getDescricao() + " para o Imóvel " + idImovel);*/
          				
          				httpServletRequest.setAttribute("msg", "Não há Guia de Pagamento com o tipo de débito " 
          						+ debitoTipo.getDescricao() + " para o Imóvel " + idImovel);
          			}
          			else{
          				
          				throw new ActionServletException("atencao.descricao_concatenada", null, 
          				"Não há Guia de Pagamento com o tipo de débito " 
          				+ debitoTipo.getDescricao() + " para o Cliente " + idCliente);
          				
          				/*httpServletRequest.setAttribute("msg", "Não há Guia de Pagamento com o tipo de débito " 
          						+ debitoTipo.getDescricao() + " para o Cliente " + idCliente);*/
          			}
          			  
      			  }
      			}
    			
      			  
    			//Cria o pagamento para a guia de pagamento
    			pagamento.setAnoMesReferenciaPagamento(null);
    			pagamento.setAnoMesReferenciaArrecadacao(anoMesReferenciaArrecadacao);
    			pagamento.setValorPagamento(valorPagamento);
    			pagamento.setDataPagamento(dataPagamento);
    			pagamento.setPagamentoSituacaoAtual(null);
    			pagamento.setPagamentoSituacaoAnterior(null);
    			pagamento.setDebitoTipo(debitoTipo);	
    			pagamento.setContaGeral(null);
    			pagamento.setGuiaPagamento(guiaPagamento);
    			pagamento.setDebitoACobrarGeral(null);
    			pagamento.setLocalidade(localidade);
    			pagamento.setDocumentoTipo(documentoTipo);
    			pagamento.setAvisoBancario(avisoBancario);
    			pagamento.setImovel(imovel);
    			pagamento.setArrecadadorMovimentoItem(null);
    			pagamento.setArrecadacaoForma(arrecadacaoForma);
    			pagamento.setUltimaAlteracao(new Date());
    			pagamento.setCliente(cliente);
    			
    			DocumentoTipo documentoTipoAgregador = new DocumentoTipo();
    			documentoTipoAgregador.setId(DocumentoTipo.GUIA_PAGAMENTO);
    			pagamento.setDocumentoTipoAgregador(documentoTipoAgregador);
    			
    			pagamento.setDataProcessamento(new Date());

    			httpServletRequest.setAttribute("pagamento", String.valueOf(
                  		GcomAction.obterTimestampIdObjeto(pagamento)));
        		
        	//Caso o tipo de documento seja débito a cobrar
        	}else if(idTipoDocumento.equals(DocumentoTipo.DEBITO_A_COBRAR.toString())){
        			
        		//Seta o tipo de documento do pagamento para débito a cobrar
        		documentoTipo.setId(new Integer(DocumentoTipo.DEBITO_A_COBRAR));
        		documentoTipo.setDescricaoAbreviado(ConstantesSistema.TIPO_PAGAMENTO_DEBITO_A_COBRAR);
        		documentoTipo.setDescricaoDocumentoTipo(ConstantesSistema.TIPO_PAGAMENTO_DEBITO_A_COBRAR);
        			
        		//Recupera o código do débito a cobrar
        		String idDebitoACobrar = (String)pagamentoActionForm.get("idDebitoACobrar");
        			
        		//[FS0024] - Verificar existência do débito a cobrar
        		this.verificarPreeenchimentoDebitoACobrarETipoDebito(idDebitoACobrar, idTipoDebito);
        			
        		//Caso o usuário informou o débito a cobrar
        		//Recupera o débito a cobrar informado do sistema 
        		DebitoACobrar debitoACobrar = null;
        		if(idDebitoACobrar != null && !idDebitoACobrar.trim().equals("")){
        		  //[FS0023] - Verificar preenchimento do débito a cobrar e do tipo de débito
        		  debitoACobrar = this.verificarExistenciaDebitoACobrar(idImovel, idDebitoACobrar);
        			
        		  //Caso exista o débito a cobrar, seta o tipo de débito do débito a cobrar
        		  //para o tipo de débito do pagamento que vai ser inserido
        		  debitoTipo = debitoACobrar.getDebitoTipo();
        			  
        		  //[FS0017] - Verificar localidade do débito a cobrar
        		  this.verificarLocalidadeDebitoACobrar(debitoACobrar,idLocalidade);
        		}
        			
        		//Caso o tipo de débito tenha sido informado
        		if(idTipoDebito != null && !idTipoDebito.trim().equals("")){
          		  //[FS0020] - Verificar existência do tipo de débito
          		  debitoTipo = this.verificarExistenciaTipoDebito(new Integer(idTipoDebito));
          		  
          		 BigDecimal valorPagamentoInformado = Util.formatarMoedaRealparaBigDecimal(
          		        (String)pagamentoActionForm.get("valorPagamento"));
          			
          		  //[FS0016] - Verificar existência de débito a cobrar com o tipo de débito
          		  debitoACobrar = this.verificarExistenciaDebitoACobrarComTipoDebito(debitoTipo,idImovel, valorPagamentoInformado);
          			  
          		  //Caso não exista nenhum débito a cobrar com o tipo de débito informado
          		  if(debitoACobrar == null){
          			  
          			 httpServletRequest.setAttribute("msg", "Não há Débito a Cobrar com o tipo de débito " 
          					  + debitoTipo.getDescricao() + " para o Imóvel " + idImovel);	  
          		  }
        		}
        			
        		
        		//Cria o pagamento para o débito a cobrar
        		pagamento.setAnoMesReferenciaPagamento(null);
        		pagamento.setAnoMesReferenciaArrecadacao(anoMesReferenciaArrecadacao);
        		pagamento.setValorPagamento(valorPagamento);
        		pagamento.setDataPagamento(dataPagamento);
        		pagamento.setPagamentoSituacaoAtual(null);
        		pagamento.setPagamentoSituacaoAnterior(null);
        		pagamento.setDebitoTipo(debitoTipo);	
        		pagamento.setContaGeral(null);
        		pagamento.setGuiaPagamento(null);

        		DebitoACobrarGeral debitoACobrarGeral = null;
				if(debitoACobrar != null){
	                debitoACobrarGeral = new DebitoACobrarGeral();
					debitoACobrarGeral.setId(debitoACobrar.getId());
					debitoACobrarGeral.setDebitoACobrar(debitoACobrar);
				}
        		
        		pagamento.setDebitoACobrarGeral(debitoACobrarGeral);
        		pagamento.setLocalidade(localidade);
        		pagamento.setDocumentoTipo(documentoTipo);
        		pagamento.setAvisoBancario(avisoBancario);
        		pagamento.setImovel(imovel);
        		pagamento.setArrecadadorMovimentoItem(null);
        		pagamento.setArrecadacaoForma(arrecadacaoForma);
        		pagamento.setUltimaAlteracao(new Date());
        		pagamento.setCliente(null);
        		
    			DocumentoTipo documentoTipoAgregador = new DocumentoTipo();
    			documentoTipoAgregador.setId(DocumentoTipo.DEBITO_A_COBRAR);
    			pagamento.setDocumentoTipoAgregador(documentoTipoAgregador);
    			
    			pagamento.setDataProcessamento(new Date());

        		httpServletRequest.setAttribute("pagamento", String.valueOf(
          				GcomAction.obterTimestampIdObjeto(pagamento)));
        		
        	}
        }
         
        return pagamento;
	}
	
	
	/**
	 * @author Raphael Rossiter
	 * @date 24/09/2007
	 */
	private void removerPagamento(Collection<Pagamento> colecaoPagamento, String identificadorPagamento,
			HttpSession sessao){
		
		if (colecaoPagamento != null && !colecaoPagamento.isEmpty()){
			
			long idPagamento = Long.parseLong(identificadorPagamento);
			
			Iterator itPagamento = colecaoPagamento.iterator();
			Pagamento pagamento = null;
			
			while(itPagamento.hasNext()){
				
				pagamento = (Pagamento) itPagamento.next();
				
				if (GcomAction.obterTimestampIdObjeto(pagamento) == idPagamento){
					itPagamento.remove();
					break;
				}
			}
			
			if (colecaoPagamento.isEmpty()){
				sessao.removeAttribute("colecaoPagamento");
			}
		}
	}
	
	/**
	 * @author Raphael Rossiter
	 * @date 24/09/2007
	 */
	private void limparDadosPagamento(DynaValidatorActionForm pagamentoActionForm){
		
		//pagamentoActionForm.set("idTipoDocumento", "");
		pagamentoActionForm.set("idLocalidade", "");
		pagamentoActionForm.set("descricaoLocalidade", "");
		//pagamentoActionForm.set("idImovel", "");
		//pagamentoActionForm.set("descricaoImovel", "");
		pagamentoActionForm.set("idCliente", "");
		pagamentoActionForm.set("nomeCliente", "");
		pagamentoActionForm.set("referenciaConta", "");
		pagamentoActionForm.set("descricaoReferenciaConta", "");
		pagamentoActionForm.set("idGuiaPagamento", "");
		pagamentoActionForm.set("descricaoGuiaPagamento", "");
		pagamentoActionForm.set("valorGuiaPagamento", "");
		
		pagamentoActionForm.set("idDebitoACobrar", "");
		pagamentoActionForm.set("descricaoDebitoACobrar", "");
		pagamentoActionForm.set("valorDebitoACobrar", "");
		pagamentoActionForm.set("idTipoDebito", "");
		pagamentoActionForm.set("descricaoTipoDebito", "");
		pagamentoActionForm.set("valorPagamento", "");
		
	}
	
	/**
	 * @author Raphael Rossiter
	 * @date 24/09/2007
	 */
	private void verificarPagamentoJaInformado(Collection colecaoPagamento, Pagamento pagamentoInformado){
		
		boolean informado = true;
		
		if (colecaoPagamento != null && !colecaoPagamento.isEmpty()){
			
			Iterator itPagamento = colecaoPagamento.iterator();
			Pagamento pagamentoColecao = null;
			
			while(itPagamento.hasNext()){
				
				informado = true;
				pagamentoColecao = (Pagamento) itPagamento.next();
				
				//TIPO DO DOCUMENTO
				if (!pagamentoColecao.getDocumentoTipo().getId().equals(
					pagamentoInformado.getDocumentoTipo().getId())){
					informado = false;
				}
				
				//IMOVEL
				if ((pagamentoColecao.getImovel() != null && pagamentoInformado.getImovel() == null) ||
					(pagamentoColecao.getImovel() == null && pagamentoInformado.getImovel() != null)){
					informado = false;
				}
				else if (pagamentoColecao.getImovel() != null &&
						 pagamentoInformado.getImovel() != null &&
						!pagamentoColecao.getImovel().getId().equals(
						 pagamentoInformado.getImovel().getId())){
					informado = false;
				}
				
				/*
				 * Comparação do CLIENTE apenas será realizada para os documentoS do tipo
				 * GUIA DE PAGAMENTO.
				 */
				if (pagamentoInformado.getDocumentoTipo().getId().equals(DocumentoTipo.GUIA_PAGAMENTO)){
					
					//CLIENTE
					if ((pagamentoColecao.getCliente() != null && pagamentoInformado.getCliente() == null) ||
						(pagamentoColecao.getCliente() == null && pagamentoInformado.getCliente() != null)){
						informado = false;
					}
					else if (pagamentoColecao.getCliente() != null &&
							 pagamentoInformado.getCliente() != null &&
							!pagamentoColecao.getCliente().getId().equals(
							 pagamentoInformado.getCliente().getId())){
						informado = false;
					}
				}
				
				//CONTA
				if ((pagamentoColecao.getContaGeral() != null && pagamentoInformado.getContaGeral() == null) ||
					(pagamentoColecao.getContaGeral() == null && pagamentoInformado.getContaGeral() != null)){
					informado = false;
				}
				else if (pagamentoColecao.getContaGeral() != null &&
						 pagamentoInformado.getContaGeral() != null &&
						 pagamentoColecao.getContaGeral().getConta() != null &&
						 pagamentoInformado.getContaGeral().getConta() != null &&
						 pagamentoColecao.getContaGeral().getConta().getReferencia() !=
						 pagamentoInformado.getContaGeral().getConta().getReferencia()){
					informado = false;
				}else if(pagamentoColecao.getContaGeral() != null &&
						 pagamentoInformado.getContaGeral() != null &&
						 pagamentoColecao.getContaGeral().getConta() != null &&
						 pagamentoInformado.getContaGeral().getConta() != null &&
						 pagamentoColecao.getContaGeral().getConta().getReferencia() ==
						 pagamentoInformado.getContaGeral().getConta().getReferencia() &&
						 !pagamentoInformado.getValorPagamento().equals(
						 pagamentoColecao.getValorPagamento())){
					informado = false;
				}
				
				//TIPO DO DEBITO
				if ((pagamentoColecao.getDebitoTipo() != null && pagamentoInformado.getDebitoTipo() == null) ||
					(pagamentoColecao.getDebitoTipo() == null && pagamentoInformado.getDebitoTipo() != null)){
					informado = false;
				}
				else if (pagamentoColecao.getDebitoTipo() != null &&
						pagamentoInformado.getDebitoTipo() != null &&
						!pagamentoColecao.getDebitoTipo().getId().equals(
						 pagamentoInformado.getDebitoTipo().getId())){
					informado = false;
				}
				
				if (informado){
					throw new ActionServletException("atencao.pagamento_ja_informado");
				}
			}
		}
	}

	
	/**
	 * Inseri uma coleção de pagamentos no sistema
	 * 
	 * [UC0265] Inserir Pagamentos
	 * 
	 * Pesquisa a guia de pagamento do imóvel informado pelo usuário
	 * 
	 * [FS0022] - Verificar existência da guia de pagamento
	 * 
	 * @author Raphael Rossiter
	 * @date 21/05/2008
	 * 
	 * @param idImovel
	 * @param idCliente
	 * @param idGuiaPagamento
	 * @return
	 * @throws ControladorException
	 */
	public GuiaPagamento pesquisarGuiaPagamentoDigitada(String idImovel, String idCliente, 
			String idGuiaPagamento, Fachada fachada){

		GuiaPagamento guiaPagamentoDigitada = null;

		FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
		FiltroGuiaPagamentoHistorico filtroGuiaPagamentoHistorico = new FiltroGuiaPagamentoHistorico();

		// Caso o usuário tenha informado o imóvel, seta o código do imóvel no
		// filtro
		// Caso contrário, o usuário tenha informado o cliente seta o código do
		// cliente no filtro
		// Caso o usuário não tenha informado nem o imóvel nem o cliente levanta
		// uma exceção
		// para o usuário informando que tem de informar o cliente ou o imóvel
		if (idImovel != null && !idImovel.trim().equalsIgnoreCase("")) {
			
			filtroGuiaPagamento.adicionarParametro(new ParametroSimples(
			FiltroGuiaPagamento.IMOVEL_ID, idImovel));
			
			filtroGuiaPagamentoHistorico.adicionarParametro(new ParametroSimples(
			FiltroGuiaPagamentoHistorico.IMOVEL_ID, idImovel));
		} 
		else if (Util.verificarNaoVazio(idCliente)) {
			
			filtroGuiaPagamento.adicionarParametro(new ParametroSimples(
			FiltroGuiaPagamento.CLIENTE_ID, idCliente));
			
			filtroGuiaPagamentoHistorico.adicionarParametro(new ParametroSimples(
			FiltroGuiaPagamentoHistorico.CLIENTE_ID, idCliente));
		} 
		else {
			throw new ActionServletException("atencao.naoinformado", null, "Imóvel ou Cliente");
		}

		filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.ID, idGuiaPagamento));

		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("cliente");

		Collection colecaoGuiaPagamento = fachada.pesquisar(filtroGuiaPagamento, GuiaPagamento.class.getName());

		// Caso exista a guia de pagamento para o imóvel ou o cliente informado
		// cadastrado no sistema
		// Retorna para o usuário a guia de pagamento retornada pela pesquisa
		// Caso contrário retorna um objeto nulo
		if (colecaoGuiaPagamento != null && !colecaoGuiaPagamento.isEmpty()) {
			
			guiaPagamentoDigitada = (GuiaPagamento) Util.retonarObjetoDeColecao(colecaoGuiaPagamento);
		} 
		else {
			
			filtroGuiaPagamentoHistorico.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoHistorico.ID, 
			idGuiaPagamento));

			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("cliente");

			colecaoGuiaPagamento = fachada.pesquisar(filtroGuiaPagamentoHistorico, GuiaPagamentoHistorico.class.getName());
			
			if (colecaoGuiaPagamento == null || colecaoGuiaPagamento.isEmpty()) {
				
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Guia de Pagamento");
			}
			else{
				
				GuiaPagamentoHistorico guiaPagamentoHistorico = (GuiaPagamentoHistorico) 
				Util.retonarObjetoDeColecao(colecaoGuiaPagamento);
				
				guiaPagamentoDigitada = new GuiaPagamento();
				guiaPagamentoDigitada.setDebitoTipo(guiaPagamentoHistorico.getDebitoTipo());
				guiaPagamentoDigitada.setLocalidade(guiaPagamentoHistorico.getLocalidade());
				guiaPagamentoDigitada.setImovel(guiaPagamentoHistorico.getImovel());
				guiaPagamentoDigitada.setCliente(guiaPagamentoHistorico.getCliente());
				guiaPagamentoDigitada.setValorDebito(guiaPagamentoHistorico.getValorDebito());
				
			}
		}

		// Retorna a guia de pagamento encontrada ou nulo se não existir aa guia
		// de pagamento
		return guiaPagamentoDigitada;
	}
	
	
	/**
	 * Inseri uma coleção de pagamentos no sistema
	 * 
	 * [UC0265] Inserir Pagamentos
	 * 
	 * Pesquisa o débito a cobrar do imóvel informado pelo usuário
	 * 
	 * [FS0024] - Verificar existência do débito a cobrar
	 * 
	 * @author Pedro Alexandre
	 * @date 21/05/2008
	 * 
	 * @param idImovel
	 * @param idDebitoACobrar
	 * @return
	 * @throws ControladorException
	 */
	public DebitoACobrar pesquisarDebitoACobrarDigitado(String idImovel,
			String idDebitoACobrar, Fachada fachada) {

		// Cria a variável que vai armazenar o débito a cobrar pesquisado
		DebitoACobrar debitoACobrarDigitado = null;

		// Cria o filtro de débito a cobrar e seta todos os parâmetros para
		// pesquisar o débito a cobrar do imóvel
		FiltroDebitoACobrar filtroDebitoACobrar = new FiltroDebitoACobrar();
		filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.IMOVEL_ID, idImovel));
		filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.ID, idDebitoACobrar));
		filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("imovel");
		
		Collection colecaoDebitoACobrar = fachada.pesquisar(filtroDebitoACobrar, DebitoACobrar.class.getName());

		// Caso exista o débito a cobrar para o imóvel informado cadastrado no
		// sistema
		// Retorna para o usuário o débito a cobrar retornado pela pesquisa
		// Caso contrário retorna um objeto nulo
		if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
			debitoACobrarDigitado = (DebitoACobrar) Util.retonarObjetoDeColecao(colecaoDebitoACobrar);
		} 
		else {
			
			FiltroDebitoACobrarHistorico filtroDebitoACobrarHistorico = new FiltroDebitoACobrarHistorico();
			filtroDebitoACobrarHistorico.adicionarParametro(new ParametroSimples(FiltroDebitoACobrarHistorico.IMOVEL_ID, idImovel));
			filtroDebitoACobrarHistorico.adicionarParametro(new ParametroSimples(FiltroDebitoACobrarHistorico.ID, idDebitoACobrar));
			filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel");
			
			colecaoDebitoACobrar = fachada.pesquisar(filtroDebitoACobrarHistorico, DebitoACobrarHistorico.class.getName());
			
			if (colecaoDebitoACobrar == null || colecaoDebitoACobrar.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Débito a Cobrar");
			}
			else{
				
				DebitoACobrarHistorico debitoACobrarHistorico = (DebitoACobrarHistorico) 
				Util.retonarObjetoDeColecao(colecaoDebitoACobrar);
				
				debitoACobrarDigitado = new DebitoACobrar();
				debitoACobrarDigitado.setDebitoTipo(debitoACobrarHistorico.getDebitoTipo());
				debitoACobrarDigitado.setLocalidade(debitoACobrarHistorico.getLocalidade());
				debitoACobrarDigitado.setImovel(debitoACobrarHistorico.getImovel());
				debitoACobrarDigitado.setValorDebito(debitoACobrarHistorico.getValorDebito());
			}
		}

		// Retorna o débito a cobrar encontrado ou nulo se não existir o débito
		// a cobrar
		return debitoACobrarDigitado;
	}
}
