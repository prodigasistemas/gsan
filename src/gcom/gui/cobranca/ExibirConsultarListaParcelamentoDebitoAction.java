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
package gcom.gui.cobranca;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cobranca.parcelamento.FiltroParcelamento;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirConsultarListaParcelamentoDebitoAction extends
		GcomAction {
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

		// Seta a ação de retorno
		ActionForward retorno = actionMapping
				.findForward("consultarListaParcelamentoDebito");
		
		// Mudar isso quando tiver esquema de segurança
		//HttpSession sessao = httpServletRequest.getSession(false);

		ParcelamentoDebitoActionForm parcelamentoDebitoActionForm = (ParcelamentoDebitoActionForm) actionForm;
		
		// Obtém a facahda
		Fachada fachada = Fachada.getInstancia();

		// Pega os codigos que o usuario digitou para a pesquisa direta de imovel
		String codigoImovel = httpServletRequest.getParameter("codigoImovel");
		
		if (codigoImovel != null && !codigoImovel.trim().equals("")) {
			
			// Pesquisa o imovel na base
			FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.IMOVEL_ID, codigoImovel));
			
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, ClienteRelacaoTipo.USUARIO));
			
			filtroClienteImovel.adicionarParametro(new ParametroNulo( FiltroClienteImovel.DATA_FIM_RELACAO));

			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial.municipio.unidadeFederacao");
			
			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro");
			
			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
			
			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
		
			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovel.imovelPerfil");
				
			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoAguaSituacao");

			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoEsgotoSituacao");
			
			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("cliente");

			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");

			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");

			Collection<ClienteImovel> imovelPesquisado = fachada.pesquisar(
					filtroClienteImovel, ClienteImovel.class.getName());

			// Se nenhum imovel for encontrado a mensagem é enviada para a página
			if (imovelPesquisado != null && imovelPesquisado.isEmpty()) {
				httpServletRequest.setAttribute("enderecoFormatado","".toUpperCase());
				parcelamentoDebitoActionForm.setNomeCliente("");
				parcelamentoDebitoActionForm.setCpfCnpj("");
				parcelamentoDebitoActionForm.setSituacaoAgua("");
				parcelamentoDebitoActionForm.setSituacaoEsgoto("");
				httpServletRequest.setAttribute("corImovel","exception");
				parcelamentoDebitoActionForm.setInscricao(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
            }
			// obtem o imovel pesquisado
			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
				
				ClienteImovel dadosImovel = (ClienteImovel) ((List) imovelPesquisado).get(0);
				
				//O endereço foi encontrado
				if (dadosImovel.getImovel().getId() != null) 
				{
					parcelamentoDebitoActionForm.setCodigoImovel(""
							+ dadosImovel.getImovel().getId());
				}
				if (dadosImovel.getImovel().getInscricaoFormatada() != null) 
				{
					parcelamentoDebitoActionForm.setInscricao(""
							+ dadosImovel.getImovel().getInscricaoFormatada());
				}
				if (dadosImovel.getImovel().getLigacaoAguaSituacao() != null) 
				{
					parcelamentoDebitoActionForm.setSituacaoAgua(""
							+ dadosImovel.getImovel().getLigacaoAguaSituacao().getDescricao());
				}
				if (dadosImovel.getImovel().getLigacaoEsgotoSituacao() != null) 
				{
					parcelamentoDebitoActionForm.setSituacaoEsgoto(""
							+ dadosImovel.getImovel().getLigacaoEsgotoSituacao().getDescricao());
				}
				if (dadosImovel.getCliente().getNome() != null) 
				{
					parcelamentoDebitoActionForm.setNomeCliente(""
							+ dadosImovel.getCliente().getNome());
				}
				if (dadosImovel.getImovel().getImovelPerfil() != null) 
				{
					parcelamentoDebitoActionForm.setImovelPerfil(""
							+ dadosImovel.getImovel().getImovelPerfil().getDescricao());
				}
				if (dadosImovel.getCliente().getClienteTipo().getIndicadorPessoaFisicaJuridica() == 1 ){
					if (dadosImovel.getCliente().getCpfFormatado() != null) 
					{
						parcelamentoDebitoActionForm.setCpfCnpj(""
								+ dadosImovel.getCliente().getCpfFormatado());
					}
				}
				else
				{
					if (dadosImovel.getCliente().getCnpjFormatado() != null) 
					{
						parcelamentoDebitoActionForm.setCpfCnpj(""
								+ dadosImovel.getCliente().getCnpjFormatado());
					}
				}
				if (dadosImovel.getImovel().getNumeroParcelamento() != null) 
				{
					parcelamentoDebitoActionForm.setParcelamento(""
							+ dadosImovel.getImovel().getNumeroParcelamento());
				}
				if (dadosImovel.getImovel().getNumeroReparcelamento() != null) 
				{
					parcelamentoDebitoActionForm.setReparcelamento(""
							+ dadosImovel.getImovel().getNumeroReparcelamento());
				}
				if (dadosImovel.getImovel().getNumeroReparcelamentoConsecutivos() != null) 
				{
					parcelamentoDebitoActionForm.setReparcelamentoConsecutivo(""
							+ dadosImovel.getImovel().getNumeroReparcelamentoConsecutivos());
				}
				// Manda a colecao pelo request
				httpServletRequest.setAttribute("imovelPesquisado",
						imovelPesquisado);
				String enderecoFormatado = "";
				try {
					enderecoFormatado = fachada.pesquisarEnderecoFormatado(new Integer(codigoImovel));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ControladorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				httpServletRequest.setAttribute("enderecoFormatado",enderecoFormatado);
				FiltroParcelamento filtroParcelamento = new FiltroParcelamento();

				filtroParcelamento.adicionarParametro(new ParametroSimples(
							FiltroParcelamento.IMOVEL_ID, codigoImovel));

				filtroParcelamento
						.adicionarCaminhoParaCarregamentoEntidade("parcelamentoSituacao");

				Collection<Parcelamento> colecaoParcelamento = fachada.pesquisar(filtroParcelamento, Parcelamento.class.getName() );
				
				if (colecaoParcelamento != null && !colecaoParcelamento.isEmpty()) 
				{
					httpServletRequest.setAttribute("colecaoParcelamento", colecaoParcelamento);
				}
				else
				{
					if (colecaoParcelamento == null || colecaoParcelamento.isEmpty()){
						throw new ActionServletException("atencao.parcelamento.inexistente");
		        	}
				}
			}
		}
		//httpServletRequest.setAttribute("ParcelamentoDebitoActionForm",parcelamentoDebitoActionForm);
		return retorno;
	}
}
