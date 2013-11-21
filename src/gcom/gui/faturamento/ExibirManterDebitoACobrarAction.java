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
package gcom.gui.faturamento;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelCobrancaSituacao;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.FiltroDebitoACobrar;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Manter Débito a Cobrar ao Imovel [UC0184] Manter Débito a Cobrar
 * 
 * @author Rafael Santos
 * @since 29/12/2005
 * 
 */
public class ExibirManterDebitoACobrarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterDebitoACobrar");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		ManterDebitoACobrarActionForm manterDebitoACobrarActionForm = (ManterDebitoACobrarActionForm) actionForm;

		String limparForm = (String) httpServletRequest
				.getParameter("limparForm");

		String codigoImovel = null;
		
		if(httpServletRequest.getParameter("idRegistroInseridoManter")!= null 
				&& !httpServletRequest.getParameter("idRegistroInseridoManter").equals("") ){
			codigoImovel = "" + httpServletRequest.getParameter("idRegistroInseridoManter");
		}else{		
			codigoImovel =  manterDebitoACobrarActionForm.getCodigoImovel();
		}

		if (codigoImovel != null && !codigoImovel.trim().equals("")) {
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, codigoImovel));
		
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
			
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("quadra");
			
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
    
	/*		filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao.id");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao.id");
	*/		
			
			
			Collection<Imovel> imovelPesquisado = fachada.pesquisar(
					filtroImovel, Imovel.class.getName());

			// [FS0001 - Verificar existêncioa da matrícula do imóvel] Imovel
			// Inxistente
			if (imovelPesquisado != null && imovelPesquisado.isEmpty()) {
				manterDebitoACobrarActionForm.setInscricaoImovel("Matrícula Inexistente");
				manterDebitoACobrarActionForm.setNomeCliente("");
				manterDebitoACobrarActionForm.setSituacaoAgua("");
				manterDebitoACobrarActionForm.setSituacaoEsgoto("");
				httpServletRequest.setAttribute("corMatriculaImovel",
					null);
				httpServletRequest.setAttribute("nomeCampo",
				"codigoImovel");				
				if (sessao.getAttribute("colecaoDebitosACobrar") != null) {
					sessao.removeAttribute("colecaoDebitosACobrar");
				}
				
			}

			// [FS0001 - Verificar existêncioa da matrícula do imóvel] Imovel
			// Excluido
			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
				Imovel imovel = imovelPesquisado.iterator().next();
				if (imovel.getIndicadorExclusao() == Imovel.IMOVEL_EXCLUIDO) {
					throw new ActionServletException(
							"atencao.pesquisa.imovel.excluido");
				}
			}

			// [FS0003 - Verificar usuário com débito em cobrança
			// administrativa]
			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
				Imovel imovel = imovelPesquisado.iterator().next();
				FiltroImovelCobrancaSituacao filtroImovelCobrancaSituacao = new FiltroImovelCobrancaSituacao();

				filtroImovelCobrancaSituacao
						.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacao");
				filtroImovelCobrancaSituacao
						.adicionarParametro(new ParametroSimples(
								FiltroImovelCobrancaSituacao.IMOVEL_ID, imovel
										.getId()));

				Collection imovelCobrancaSituacaoEncontrada = fachada
						.pesquisar(filtroImovelCobrancaSituacao,
								ImovelCobrancaSituacao.class.getName());

				// Verifica se o imóvel tem débito em cobrança administrativa
				if (imovelCobrancaSituacaoEncontrada != null
						&& !imovelCobrancaSituacaoEncontrada.isEmpty()) {

					if (((ImovelCobrancaSituacao) ((List) imovelCobrancaSituacaoEncontrada)
							.get(0)).getCobrancaSituacao() != null) {

						if (((ImovelCobrancaSituacao) ((List) imovelCobrancaSituacaoEncontrada)
								.get(0)).getCobrancaSituacao().getId().equals(
								CobrancaSituacao.COBRANCA_ADMINISTRATIVA)
								&& ((ImovelCobrancaSituacao) ((List) imovelCobrancaSituacaoEncontrada)
										.get(0)).getDataRetiradaCobranca() == null) {

							throw new ActionServletException(
									"atencao.pesquisa.imovel.cobranca_administrativa");
						}
					}
				}
			}

			// Obtem o cliente imovel do imovel pesquisado
			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {

				Imovel imovel = imovelPesquisado.iterator().next();

				FiltroDebitoACobrar filtroDebitoACobrar = new FiltroDebitoACobrar();

				filtroDebitoACobrar
						.adicionarCaminhoParaCarregamentoEntidade("imovel");

				filtroDebitoACobrar.adicionarParametro(new ParametroSimples(
						FiltroDebitoACobrar.IMOVEL_ID, imovel.getId()));
				
				filtroDebitoACobrar.adicionarParametro(new ParametroSimples(
						FiltroDebitoACobrar.DEBITO_CREDITO_SITUACAO_ATUAL_ID, DebitoCreditoSituacao.NORMAL));
				
				/*filtroDebitoACobrar.adicionarParametro(new Menor(FiltroDebitoACobrar.NUMERO_PRESTACOES_COBRADAS,
						FiltroDebitoACobrar.NUMERO_PRESTACOES_DEBITO));
*/
				filtroDebitoACobrar
						.adicionarCaminhoParaCarregamentoEntidade(FiltroDebitoACobrar.FINANCIAMENTO_TIPO);
				filtroDebitoACobrar
						.adicionarCaminhoParaCarregamentoEntidade(FiltroDebitoACobrar.DEBITO_TIPO);
				filtroDebitoACobrar
						.adicionarCaminhoParaCarregamentoEntidade(FiltroDebitoACobrar.DEBITO_CREDITO_SITUACAO);

				Collection<DebitoACobrar> colecaoDebitoACobrar = fachada
						.pesquisar(filtroDebitoACobrar, DebitoACobrar.class
								.getName());

				// [FS0002] - Verificar existência de débito a cobrar
				if (colecaoDebitoACobrar != null
						&& colecaoDebitoACobrar.isEmpty()) {
					throw new ActionServletException(
							"atencao.debito_a_cobrar.inexistente", null,
							codigoImovel);
				}

				Collection<DebitoACobrar> colecaoDebitoACobrarFinal = new ArrayList();
				SistemaParametro sistemaParametro = this.getSistemaParametro();
				
				/*
				 * Adicionado por: Mariana Victor
				 * Data: 28/07/2011
				 * 
				 * 5. Caso o indicador de bloqueio de débitos a cobrar vinculados a contrato de parcelamento no manter debito a cobrar esteja ativo 
				 * */
				if (sistemaParametro.getIndicadorBloqueioDebitoACobrarContratoParcelManterDebito() != null
						&& sistemaParametro.getIndicadorBloqueioDebitoACobrarContratoParcelManterDebito()
							.compareTo(ConstantesSistema.SIM) == 0) {
					// retirar da lista de débitos a cobrar selecionados os débitos a cobrar vinculados 
					//  a algum contrato de parcelamento ativo
					Iterator iterator = colecaoDebitoACobrar.iterator();
					
					while(iterator.hasNext()) {
						DebitoACobrar debitoACobrar = (DebitoACobrar) iterator.next();
						
						if (!fachada.verificaDebitoACobrarVinculadoContratoParcelamentoCliente(debitoACobrar.getId())) {
							colecaoDebitoACobrarFinal.add(debitoACobrar);
						}
					}
				} else {
					colecaoDebitoACobrarFinal.addAll(colecaoDebitoACobrar);
				}
				
				// manterDebitoACobrarActionForm.setCodigoImovel()
				sessao.setAttribute("colecaoDebitosACobrar",
						colecaoDebitoACobrarFinal);

				sessao.setAttribute("imovelPesquisado", imovel);

				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("cliente");
				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.IMOVEL_ID, codigoImovel));
				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
						ClienteRelacaoTipo.USUARIO));

				Collection<ClienteImovel> clienteImovelPesquisado = fachada
						.pesquisar(filtroClienteImovel, ClienteImovel.class
								.getName());

				if (clienteImovelPesquisado != null
						&& !clienteImovelPesquisado.isEmpty()) {
					ClienteImovel clienteImovel = clienteImovelPesquisado
							.iterator().next();
					if (clienteImovel.getCliente() != null) {
						manterDebitoACobrarActionForm
								.setNomeCliente(clienteImovel.getCliente()
										.getNome());
					}
				}
				if (imovel.getLigacaoAguaSituacao() != null) {
					manterDebitoACobrarActionForm.setSituacaoAgua(imovel
							.getLigacaoAguaSituacao().getDescricao());
				}

				if (imovel.getLigacaoEsgotoSituacao() != null) {
					manterDebitoACobrarActionForm.setSituacaoEsgoto(imovel
							.getLigacaoEsgotoSituacao().getDescricao());
				}
				manterDebitoACobrarActionForm.setCodigoImovel(codigoImovel);

				manterDebitoACobrarActionForm.setInscricaoImovel(imovel
						.getInscricaoFormatada());
				
				httpServletRequest.setAttribute("corMatriculaImovel",
				"valor");
				httpServletRequest.setAttribute("nomeCampo",
				"idTipoDebito");				

			}
		}

		if (limparForm != null && !limparForm.trim().equalsIgnoreCase("")) {

			// manterDebitoACobrarActionForm.set
			manterDebitoACobrarActionForm.reset(actionMapping,
					httpServletRequest);

			if (sessao.getAttribute("imovelPesquisado") != null) {
				sessao.removeAttribute("imovelPesquisado");
			}

			if (sessao.getAttribute("colecaoDebitosACobrar") != null) {
				sessao.removeAttribute("colecaoDebitosACobrar");
			}

		}

		return retorno;
	}
}