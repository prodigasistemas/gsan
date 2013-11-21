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
package gcom.gui.atendimentopublico.hidrometro;

import gcom.atendimentopublico.ordemservico.FiltroOrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de inserir bairro
 * 
 * @author Thiago Tenório
 * @created 28 de Junho de 2004
 */
public class ExibirAtualizarEfetuarRetiradaHidrometroAction extends GcomAction {
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

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("efetuarRetiradaHidrometro");
		EfetuarRetiradaHidrometroActionForm retiradaActionForm = (EfetuarRetiradaHidrometroActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		// Variavel responsavél pelo preenchimento do imovel no formulário
		String idOrdemServico = retiradaActionForm.getIdOrdemServico();

		OrdemServico ordemServico = null;

		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

			FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
			filtroOrdemServico.adicionarParametro(new ParametroSimples(
					FiltroOrdemServico.ID, idOrdemServico));

			filtroOrdemServico
					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.ligacaoAguaSituacao");
			filtroOrdemServico
					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.ligacaoEsgotoSituacao");
			filtroOrdemServico
					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.localidade");
			filtroOrdemServico
					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.setorComercial");
			filtroOrdemServico
					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.quadra");
			filtroOrdemServico
					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.cliente");

			Collection colecaoOrdemServico = fachada.pesquisar(
					filtroOrdemServico, OrdemServico.class.getName());
			if (colecaoOrdemServico != null && !colecaoOrdemServico.isEmpty()) {

				ordemServico = (OrdemServico) colecaoOrdemServico.iterator()
						.next();

				// Inicio de codigo * Dados do Imovél
				if (ordemServico.getRegistroAtendimento().getImovel() != null) {
					sessao.setAttribute("imovel", ordemServico
							.getRegistroAtendimento().getImovel());
					String matriculaImovel = ordemServico
							.getRegistroAtendimento().getImovel().getId()
							.toString();
					retiradaActionForm.setMatriculaImovel("" + matriculaImovel);

					// Inscrição do Imovél

					String inscricaoImovel = ordemServico
							.getRegistroAtendimento().getImovel()
							.getInscricaoFormatada();

					retiradaActionForm.setMatriculaImovel(matriculaImovel);
					retiradaActionForm.setInscricaoImovel(inscricaoImovel);

				} else {
					retiradaActionForm.setMatriculaImovel("");
					retiradaActionForm.setInscricaoImovel("");
				}
				// Cliente Usuário

				/*if (ordemServico.getRegistroAtendimento().getCliente() != null) {
					String clienteUsuario = ordemServico
							.getRegistroAtendimento().getCliente().getNome();
					retiradaActionForm.setClienteUsuario(clienteUsuario);
				} else {
					retiradaActionForm.setClienteUsuario("");
				}*/

				// CPF & CNPJ
				/*if (ordemServico.getRegistroAtendimento().getCliente() != null
						&& ordemServico.getRegistroAtendimento().getCliente()
								.getCpfFormatado() != "") {

					String cpfCnpj = Util.formatarCPFApresentacao(ordemServico
							.getRegistroAtendimento().getCliente()
							.getCpfFormatado());
					retiradaActionForm.setCpfCnpjCliente(cpfCnpj);
					retiradaActionForm.setCpfCnpjCliente(cpfCnpj);
				} else {
					if (ordemServico.getRegistroAtendimento().getCliente() != null
							&& ordemServico.getRegistroAtendimento()
									.getCliente().getCnpjFormatado() != "") {
						String cpfCnpj = Util
								.formatarCPFApresentacao(ordemServico
										.getRegistroAtendimento().getCliente()
										.getCnpjFormatado());
						retiradaActionForm.setCpfCnpjCliente(cpfCnpj);
					} else {
						retiradaActionForm.setCpfCnpjCliente("");
					}
				}*/

				if (ordemServico.getRegistroAtendimento().getImovel() != null
						&& ordemServico.getRegistroAtendimento().getImovel()
								.getLigacaoAguaSituacao() != null) {
					// Situação da Ligação de Agua

					String situacaoLigacaoAgua = ordemServico
							.getRegistroAtendimento().getImovel()
							.getLigacaoAguaSituacao().getDescricao();
					retiradaActionForm
							.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

				} else {
					retiradaActionForm.setSituacaoLigacaoAgua("");
				}
				// Situação da Ligação de Esgoto

				if (ordemServico.getRegistroAtendimento().getImovel() != null
						&& ordemServico.getRegistroAtendimento().getImovel()
								.getLigacaoEsgotoSituacao() != null) {
					String situacaoLigacaoEsgoto = ordemServico
							.getRegistroAtendimento().getImovel()
							.getLigacaoEsgotoSituacao().getDescricao();
					retiradaActionForm
							.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);
					//
				} else {
					retiradaActionForm.setSituacaoLigacaoEsgoto("");
				}

			} else {
				httpServletRequest.setAttribute("nomeCampo", "idOrdemServico");
				retiradaActionForm.setIdOrdemServico("");
				retiradaActionForm
						.setNomeOrdemServico("ORDEM DE SERVIÇO INEXISTENTE");
			}
			// -------Fim da Parte que trata do código quando o usuário tecla
			// enter

			if (ordemServico.getRegistroAtendimento().getImovel() != null) {

				String idImovel = ordemServico.getRegistroAtendimento()
						.getImovel().getId().toString();

				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, idImovel));

				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico.hidrometro");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico.tipoMedicao");

				Collection colecaoImovel = fachada.pesquisar(filtroImovel,
						Imovel.class.getName());
				if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

					Imovel imovel = (Imovel) colecaoImovel.iterator().next();

					// Inicio de codigo * Dados do Imovél
					if (imovel.getHidrometroInstalacaoHistorico() != null) {
						String hidrometro = imovel
								.getHidrometroInstalacaoHistorico()
								.getHidrometro().getNumero();
						retiradaActionForm.setHidrometro(hidrometro);

						// Tipo Medição

						if (imovel.getHidrometroInstalacaoHistorico()
								.getMedicaoTipo() != null) {
							String medicaoTipo = imovel
									.getHidrometroInstalacaoHistorico()
									.getMedicaoTipo().getDescricao();
							retiradaActionForm.setMedicaoTipo(medicaoTipo);
						} else {
							retiradaActionForm.setMedicaoTipo("");
						}
						if (imovel.getHidrometroInstalacaoHistorico()
								.getDataRetirada() != null) {
							Date dataRetirada = imovel
									.getHidrometroInstalacaoHistorico()
									.getDataRetirada();
							retiradaActionForm.setDataRetirada(Util
									.formatarData(dataRetirada));
						} else {
							retiradaActionForm.setDataRetirada("");
						}

						if (imovel.getHidrometroInstalacaoHistorico()
								.getNumeroLeituraRetirada() == 0) {
							Integer numeroLeituraRetirada = imovel
									.getHidrometroInstalacaoHistorico()
									.getNumeroLeituraRetirada();
							retiradaActionForm
									.setNumeroLeitura(numeroLeituraRetirada
											.toString());
						} else {
							retiradaActionForm.setNumeroLeitura("");
						}

					}
				}

			}
		}

		return retorno;
	}

}
