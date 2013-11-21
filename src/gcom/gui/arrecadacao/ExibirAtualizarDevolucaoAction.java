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

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.FiltroDevolucao;
import gcom.arrecadacao.FiltroGuiaDevolucao;
import gcom.arrecadacao.GuiaDevolucao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0271]Action responsável pela pre-exibição da pagina de inserir
 * devolucoes
 * 
 * @author Fernanda Paiva
 * @created 09 de Março de 2006
 */
public class ExibirAtualizarDevolucaoAction extends GcomAction {
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarDevolucao");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String codigoDevolucao = httpServletRequest.getParameter("idRegistroAtualizacao");
		String codigoAtualizar = null;
		if (codigoDevolucao == null){
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null){
				codigoDevolucao = (String) sessao.getAttribute("codigoDevolucao");
			}else{
				codigoDevolucao = (String) httpServletRequest.getAttribute("idRegistroAtualizacao").toString();
				codigoAtualizar = (String) httpServletRequest.getAttribute("idRegistroAtualizacao").toString();
			}
			
		} else {
			sessao.setAttribute("i", true);
		}
			
		sessao.setAttribute("codigoDevolucao", codigoDevolucao);
		
		String tela = httpServletRequest.getParameter("tela");
		
		Fachada fachada = Fachada.getInstancia();
		
		ManterDevolucaoActionForm manterDevolucaoActionForm = (ManterDevolucaoActionForm) actionForm;

		// Carregar a data corrente do sistema
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dataCorrente = new GregorianCalendar();

		// Data Corrente
		httpServletRequest.setAttribute("dataAtual", formatoData
				.format(dataCorrente.getTime()));

		//	Código do Cliente
		String codigoDigitadoClienteEnter = (String) manterDevolucaoActionForm
				.getCodigoCliente();

		// Matrícula do Imóvel
		String codigoDigitadoImovelEnter = (String) manterDevolucaoActionForm
				.getCodigoImovel();

		// Matrícula do Localidade
		String codigoDigitadoLocalidadeEnter = (String) manterDevolucaoActionForm
				.getLocalidade();

		// Matrícula do Guia Devolucao
		String codigoDigitadoGuiaEnter = (String) manterDevolucaoActionForm
				.getGuiaDevolucao();

		// Matrícula do DebitoTipo
		String codigoDigitadoDebitoEnter = (String) manterDevolucaoActionForm
				.getTipoDebito();
		
		Devolucao dadosDevolucao = null;

		// ------Inicio da parte que verifica se vem da página de devolucao_manter.jsp		
		if (((codigoAtualizar != null || (httpServletRequest.getParameter("idRegistroAtualizacao") != null && !httpServletRequest.getParameter("idRegistroAtualizacao").equals(""))) && tela == null)) {
			
			FiltroDevolucao filtroDevolucao = new FiltroDevolucao() ;
			
			filtroDevolucao.adicionarParametro(new ParametroSimples(
					FiltroDevolucao.ID, codigoDevolucao));
			
			filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("avisoBancario.arrecadador");
			filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao");
			filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
			filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
			filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
	
			
			Collection devolucoes = fachada.pesquisar(filtroDevolucao,
					Devolucao.class.getName());
			
			if (devolucoes != null
					&& !devolucoes.isEmpty()){
				
				dadosDevolucao = (Devolucao) ((List) devolucoes).get(0);
				
				if (dadosDevolucao.getImovel() != null
						&& !dadosDevolucao.getImovel().equals("")) {
	
					manterDevolucaoActionForm
						.setCodigoImovel(formatarResultado(((Devolucao) ((List) devolucoes)
								.get(0)).getImovel().getId().toString()));
					manterDevolucaoActionForm
						.setCodigoImovelClone(formatarResultado(((Devolucao) ((List) devolucoes)
								.get(0)).getImovel().getId().toString()));
					manterDevolucaoActionForm.setInscricaoImovel(""
							+ ((Devolucao) ((List) devolucoes).get(0))
									.getImovel().getInscricaoFormatada());
				} 
				if (dadosDevolucao.getCliente() != null
						&& !dadosDevolucao.getCliente().equals("")) {
	
					manterDevolucaoActionForm
						.setCodigoCliente(formatarResultado(((Devolucao) ((List) devolucoes)
							.	get(0)).getCliente().getId().toString()));
					manterDevolucaoActionForm
						.setCodigoClienteClone(formatarResultado(((Devolucao) ((List) devolucoes)
								.get(0)).getCliente().getId().toString()));
					manterDevolucaoActionForm
						.setNomeCliente(formatarResultado(((Devolucao) ((List) devolucoes)
								.get(0)).getCliente().getNome().toString()));
					
				}
				if (dadosDevolucao.getGuiaDevolucao() != null
						&& !dadosDevolucao.getGuiaDevolucao().equals("")) {
					FiltroGuiaDevolucao filtroGuiaDevolucao = new FiltroGuiaDevolucao() ;
					
					filtroGuiaDevolucao.adicionarParametro(new ParametroSimples(
							FiltroGuiaDevolucao.ID, dadosDevolucao.getGuiaDevolucao().getId()));
					
					filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");
					
					Collection guiaDevolucoes = fachada.pesquisar(filtroGuiaDevolucao,
							GuiaDevolucao.class.getName());
					
					GuiaDevolucao dadosGuiaDevolucao = (GuiaDevolucao) ((List) guiaDevolucoes).get(0);
					
					manterDevolucaoActionForm
						.setGuiaDevolucao(formatarResultado(((Devolucao) ((List) devolucoes)
								.get(0)).getGuiaDevolucao().getId().toString()));

					if (dadosGuiaDevolucao.getId() != null
							&& !dadosGuiaDevolucao.getId().equals("")) {
						manterDevolucaoActionForm
							.setGuiaDevolucaoDescricao(formatarResultado(((GuiaDevolucao) ((List) guiaDevolucoes)
									.get(0)).getCreditoTipo().getDescricao()));
					}
				}
					
				if (dadosDevolucao.getDebitoTipo() != null
						&& !dadosDevolucao.getDebitoTipo().equals("")) {
	
					manterDevolucaoActionForm
					.setTipoDebito(formatarResultado(((Devolucao) ((List) devolucoes)
						.get(0)).getDebitoTipo().getId().toString()));
					manterDevolucaoActionForm
						.setTipoDebitoClone(formatarResultado(((Devolucao) ((List) devolucoes)
								.get(0)).getDebitoTipo().getId().toString()));
					manterDevolucaoActionForm
						.setDescricaoTipoDebito(formatarResultado(((Devolucao) ((List) devolucoes)
						.get(0)).getDebitoTipo().getDescricao()));
					
				}
				if (dadosDevolucao.getLocalidade() != null
						&& !dadosDevolucao.getLocalidade().equals("")) {
					manterDevolucaoActionForm
						.setLocalidade(formatarResultado(((Devolucao) ((List) devolucoes)
								.get(0)).getLocalidade().getId().toString()));
					manterDevolucaoActionForm
						.setLocalidadeClone(formatarResultado(((Devolucao) ((List) devolucoes)
								.get(0)).getLocalidade().getId().toString()));
                    manterDevolucaoActionForm
						.setLocalidadeDescricao(formatarResultado(((Devolucao) ((List) devolucoes)
								.get(0)).getLocalidade().getDescricao().toString()));

				}
			   manterDevolucaoActionForm
					.setCodigoAgenteArrecadador(formatarResultado(((Devolucao) ((List) devolucoes)
							.get(0)).getAvisoBancario().getArrecadador().getCodigoAgente().toString()));
			   manterDevolucaoActionForm
					.setDataLancamentoAviso(Util.formatarData(((Devolucao) ((List) devolucoes)
							.get(0)).getAvisoBancario().getDataLancamento()));
			   manterDevolucaoActionForm
					.setNumeroSequencialAviso(formatarResultado(((Devolucao) ((List) devolucoes)
							.get(0)).getAvisoBancario().getNumeroSequencial().toString()));
			   if (dadosDevolucao.getAnoMesReferenciaDevolucao() != null) {
					manterDevolucaoActionForm
						.setReferenciaDevolucao(Util.formatarAnoMesParaMesAno(((Devolucao) ((List) devolucoes)
								.get(0)).getAnoMesReferenciaDevolucao()));
				    manterDevolucaoActionForm
				   		.setReferenciaDevolucaoClone(Util.formatarAnoMesParaMesAno(((Devolucao) ((List) devolucoes)
				   				.get(0)).getAnoMesReferenciaDevolucao()));
		       }
			   manterDevolucaoActionForm
					.setValorDevolucao(Util.formatarMoedaReal(((Devolucao) ((List) devolucoes)
							.get(0)).getValorDevolucao()));
		      /*manterDevolucaoActionForm
					.setValorGuiaDevolucao(Util.formatarMoedaReal(((Devolucao) ((List) devolucoes)
							.get(0)).getValorDevolucao()));*/
			   if (((Devolucao) ((List) devolucoes)
						.get(0)).getGuiaDevolucao() != null) {
				   manterDevolucaoActionForm
						.setValorGuiaDevolucao(Util.formatarMoedaReal(((Devolucao) ((List) devolucoes)
								.get(0)).getGuiaDevolucao().getValorDevolucao()));
			   }
			   manterDevolucaoActionForm
					.setDataDevolucao(Util.formatarData(((Devolucao) ((List) devolucoes)
						.get(0)).getDataDevolucao()));
		      manterDevolucaoActionForm
					.setCodigoDevolucao(formatarResultado(((Devolucao) ((List) devolucoes)
							.get(0)).getId().toString()));
		      manterDevolucaoActionForm
					.setAvisoBancario(formatarResultado(((Devolucao) ((List) devolucoes)
							.get(0)).getAvisoBancario().getId().toString()));			
		    }
		}
		else
		{
			// Verifica se o código do imóvel foi digitado
			if (codigoDigitadoImovelEnter != null
					&& !codigoDigitadoImovelEnter.trim().equals("")
					&& Integer.parseInt(codigoDigitadoImovelEnter) > 0 
					&& codigoDigitadoGuiaEnter.trim().equals("")) {
				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("localidade");
				
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
				
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("quadra");
				
				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, codigoDigitadoImovelEnter));

				Collection imovelEncontrado = fachada.pesquisar(filtroImovel,
						Imovel.class.getName());

				if (imovelEncontrado != null && !imovelEncontrado.isEmpty()) {
					// O imovel foi encontrado
					manterDevolucaoActionForm.setCodigoImovel(""
							+ ((Imovel) ((List) imovelEncontrado).get(0)).getId());
					manterDevolucaoActionForm.setCodigoImovelClone(""
							+ ((Imovel) ((List) imovelEncontrado).get(0)).getId());				
					manterDevolucaoActionForm.setInscricaoImovel(""
							+ ((Imovel) ((List) imovelEncontrado).get(0))
									.getInscricaoFormatada());
					String localidadeAntes = codigoDigitadoLocalidadeEnter;
					codigoDigitadoLocalidadeEnter = ((Imovel) ((List) imovelEncontrado).get(0)).getLocalidade().getId().toString();
					
					if(localidadeAntes != null && !localidadeAntes.equals("") && !localidadeAntes.equals(codigoDigitadoLocalidadeEnter))
					{
						throw new ActionServletException(
								"atencao.pesquisa.localidade.imovel.diferente",""
								+ codigoDigitadoLocalidadeEnter, localidadeAntes);
					}
					manterDevolucaoActionForm.setLocalidade(""
							+ ((Imovel) ((List) imovelEncontrado).get(0)).getLocalidade().getId());
					manterDevolucaoActionForm.setLocalidadeClone(""
							+ ((Imovel) ((List) imovelEncontrado).get(0)).getLocalidade().getId());
					manterDevolucaoActionForm.setLocalidadeDescricao(""
							+ ((Imovel) ((List) imovelEncontrado).get(0)).getLocalidade().getDescricao());
				} else {
					httpServletRequest.setAttribute("corImovel","exception");
	               	manterDevolucaoActionForm
	               			.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
				}
			}
			// Verifica se o do cliente código foi digitado
			if (codigoDigitadoClienteEnter != null
					&& !codigoDigitadoClienteEnter.trim().equals("")
					&& Integer.parseInt(codigoDigitadoClienteEnter) > 0
					&& codigoDigitadoGuiaEnter.trim().equals("")) {

				FiltroCliente filtroCliente = new FiltroCliente();
				
				filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.ID, codigoDigitadoClienteEnter));
				filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection clienteEncontrado = fachada.pesquisar(filtroCliente,
						Cliente.class.getName());

				if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
					// O Cliente foi encontrado
					if (((Cliente) ((List) clienteEncontrado).get(0))
							.getIndicadorUso().equals(
									ConstantesSistema.INDICADOR_USO_DESATIVO)) {
						throw new ActionServletException("atencao.cliente.inativo",
								null, ""
										+ ((Cliente) ((List) clienteEncontrado)
												.get(0)).getId());
					}

					manterDevolucaoActionForm
							.setNomeCliente(((Cliente) ((List) clienteEncontrado)
									.get(0)).getNome());
					manterDevolucaoActionForm
							.setCodigoCliente(((Cliente) ((List) clienteEncontrado)
									.get(0)).getId().toString());
					manterDevolucaoActionForm
							.setCodigoClienteClone(((Cliente) ((List) clienteEncontrado)
							.get(0)).getId().toString());

				} else {
					httpServletRequest.setAttribute("corCliente","exception");
	               	manterDevolucaoActionForm
	               			.setNomeCliente(ConstantesSistema.CODIGO_CLIENTE_INEXISTENTE);

				}
			}

			// Verifica se o do debito tipo foi digitado
			if (codigoDigitadoDebitoEnter != null
					&& !codigoDigitadoDebitoEnter.trim().equals("")
					&& Integer.parseInt(codigoDigitadoDebitoEnter) > 0
					&& codigoDigitadoGuiaEnter.trim().equals("")) {

				FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();

				filtroDebitoTipo.adicionarParametro(new ParametroSimples(
						FiltroDebitoTipo.ID, codigoDigitadoDebitoEnter));
				filtroDebitoTipo.adicionarParametro(new ParametroSimples(
						FiltroDebitoTipo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection debitoEncontrado = fachada.pesquisar(filtroDebitoTipo,
						DebitoTipo.class.getName());

				if (debitoEncontrado != null && !debitoEncontrado.isEmpty()) {
					// O Cliente foi encontrado
					if (((DebitoTipo) ((List) debitoEncontrado).get(0))
							.getIndicadorUso().equals(
									ConstantesSistema.INDICADOR_USO_DESATIVO)) {
						throw new ActionServletException("atencao.debitoTipo.inativo",
								null, ""
										+ ((DebitoTipo) ((List) debitoEncontrado)
												.get(0)).getId());
					}

					manterDevolucaoActionForm
							.setTipoDebito(((DebitoTipo) ((List) debitoEncontrado)
									.get(0)).getId().toString());
					manterDevolucaoActionForm
							.setTipoDebitoClone(((DebitoTipo) ((List) debitoEncontrado)
									.get(0)).getId().toString());
					manterDevolucaoActionForm
							.setDescricaoTipoDebito(((DebitoTipo) ((List) debitoEncontrado)
									.get(0)).getDescricao());

				} else {
					httpServletRequest.setAttribute("corTipoDebito","exception");
	               	manterDevolucaoActionForm
	               			.setDescricaoTipoDebito(ConstantesSistema.CODIGO_TIPO_DEBITO_INEXISTENTE);
				}
			}

			//	Verifica se o código do imóvel foi digitado
			if (codigoDigitadoGuiaEnter != null
					&& !codigoDigitadoGuiaEnter.trim().equals("")
					&& Integer.parseInt(codigoDigitadoGuiaEnter) > 0) {
				
				FiltroGuiaDevolucao filtroGuiaDevolucao = new FiltroGuiaDevolucao();
				
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("imovel");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("cliente");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("imovel");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");
				
				filtroGuiaDevolucao.adicionarParametro(new ParametroSimples(
						FiltroGuiaDevolucao.ID, codigoDigitadoGuiaEnter));

				Collection guiaEncontrado = fachada.pesquisar(filtroGuiaDevolucao,
						GuiaDevolucao.class.getName());

				if (guiaEncontrado != null && !guiaEncontrado.isEmpty()) {
					// A Guia foi encontrada
					Iterator iterator = guiaEncontrado.iterator();

					GuiaDevolucao guiaDevolucao = (GuiaDevolucao) iterator.next();
		            
					if (guiaDevolucao.getImovel() != null)
					{
						manterDevolucaoActionForm.setCodigoImovel(""
								+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getImovel().getId());
						manterDevolucaoActionForm.setCodigoImovelClone(""
								+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getImovel().getId());

						manterDevolucaoActionForm.setInscricaoImovel(""
								+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getImovel().getInscricaoFormatada());
						manterDevolucaoActionForm.setLocalidade(""
								+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getImovel().getLocalidade().getId());
						manterDevolucaoActionForm.setLocalidadeClone(""
								+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getImovel().getLocalidade().getId());
						manterDevolucaoActionForm.setLocalidadeDescricao(""
								+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getImovel().getLocalidade().getDescricao());
					}
					else
					{
						manterDevolucaoActionForm.setCodigoImovel("");
						manterDevolucaoActionForm.setCodigoImovelClone("");
						manterDevolucaoActionForm.setInscricaoImovel("");
					}
					if (guiaDevolucao.getCliente() != null)
					{
						manterDevolucaoActionForm.setCodigoCliente(""
								+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getCliente().getId());
						manterDevolucaoActionForm.setCodigoClienteClone(""
								+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getCliente().getId());
						manterDevolucaoActionForm.setNomeCliente(""
								+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getCliente().getNome());
					}
					else
					{
						manterDevolucaoActionForm.setCodigoCliente("");
						manterDevolucaoActionForm.setCodigoClienteClone("");
						manterDevolucaoActionForm.setNomeCliente("");
					}
					
					manterDevolucaoActionForm.setValorDevolucao(""
							+ Util.formatarMoedaReal(((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getValorDevolucao()));
					
					manterDevolucaoActionForm.setValorGuiaDevolucao(""
							+ Util.formatarMoedaReal(((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getValorDevolucao()));
					if (guiaDevolucao.getCreditoTipo() != null)
					{
						manterDevolucaoActionForm.setGuiaDevolucaoDescricao(""
								+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getCreditoTipo().getDescricao());
					}
					if (guiaDevolucao.getDebitoTipo() != null)
					{
						manterDevolucaoActionForm.setTipoDebito(""
								+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getDebitoTipo().getId());
						manterDevolucaoActionForm.setTipoDebitoClone(""
								+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getDebitoTipo().getId());
						manterDevolucaoActionForm.setDescricaoTipoDebito(""
								+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getDebitoTipo().getDescricao());
					}
					else
					{
						manterDevolucaoActionForm.setTipoDebito("");
						manterDevolucaoActionForm.setTipoDebitoClone("");
						manterDevolucaoActionForm.setDescricaoTipoDebito("");
					}
					
					if (guiaDevolucao.getAnoMesReferenciaGuiaDevolucao() != null)
					{
						manterDevolucaoActionForm.setReferenciaDevolucao(""
								+ Util.formatarAnoMesParaMesAno(((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getAnoMesReferenciaGuiaDevolucao()));
						manterDevolucaoActionForm.setReferenciaDevolucaoClone(""
								+ Util.formatarAnoMesParaMesAno(((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getAnoMesReferenciaGuiaDevolucao()));
					}
					else
					{
						manterDevolucaoActionForm.setReferenciaDevolucao("");
						manterDevolucaoActionForm.setReferenciaDevolucaoClone("");
					}
					
				} else {
					httpServletRequest.setAttribute("corGuia","exception");
	               	manterDevolucaoActionForm
	               			.setGuiaDevolucaoDescricao(ConstantesSistema.CODIGO_GUIA_DEVOLUCAO_INEXISTENTE);
				}
			}
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			
			Collection localidades = new HashSet();
	        
			// Verifica se a localidade foi digitada
			if (codigoDigitadoLocalidadeEnter != null
					&& !codigoDigitadoLocalidadeEnter.trim().equals("")
					&& Integer.parseInt(codigoDigitadoLocalidadeEnter) > 0) {
		
				filtroLocalidade.limparListaParametros();
	            //coloca parametro no filtro
	            filtroLocalidade.adicionarParametro(new ParametroSimples(
	                    FiltroLocalidade.ID, new Integer(codigoDigitadoLocalidadeEnter)));
	            //pesquisa
	            localidades = fachada.pesquisar(filtroLocalidade, Localidade.class
	                    .getName());
	            if (localidades != null && !localidades.isEmpty()) {
	                //A localidade foi encontrada
	            	manterDevolucaoActionForm.setLocalidade(((Localidade) ((List) localidades)
							.get(0)).getId().toString());
	                
	            	manterDevolucaoActionForm.setLocalidadeClone(((Localidade) ((List) localidades)
							.get(0)).getId().toString());
	                
	            	manterDevolucaoActionForm.setLocalidadeDescricao(((Localidade) ((List) localidades)
							.get(0)).getDescricao());

	            } else {
	            	httpServletRequest.setAttribute("corLocalidade","exception");
	               	manterDevolucaoActionForm
	                		.setLocalidadeDescricao(ConstantesSistema.CODIGO_LOCALIDADE_INEXISTENTE);
	            }
			}
		}
		// caso ainda não tenha sido setado o nome campo(na primeira vez)
		if (httpServletRequest.getParameter("manter") != null){
			sessao.setAttribute("manter", "manter");
		}
		sessao.setAttribute("dadosDevolucao",dadosDevolucao);
		return retorno;
	}
	/**
	 * < <Descrição do método>>
	 * 
	 * @param parametro
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	private String formatarResultado(String parametro) {
		if (parametro != null && !parametro.trim().equals("")) {
			if (parametro.equals("null")) {
				return "";
			} else {
				return parametro.trim();
			}
		} else {
			return "";
		}
	}
}
