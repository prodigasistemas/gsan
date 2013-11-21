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
 * Rômulo Aurélio de Melo Souza Filho
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

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroPocoTipo;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.PocoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroProtecao;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
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
 * <<Descrição da Classe>>
 * 
 * @author lms
 * @date 17/07/2006
 */
public class ExibirAtualizarInstalacaoHidrometroAction extends GcomAction {

	/**
	 * Este caso de uso permite efetuar a atualização dos atributos da
	 * instalação do hidrômetro
	 * 
	 * [UC0368] Atualizar Instalação de Hidrômetro
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

		ActionForward retorno = actionMapping
				.findForward("atualizarInstalacaoHidrometro");

		AtualizarInstalacaoHidrometroActionForm form = (AtualizarInstalacaoHidrometroActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

/*		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

		String tipoConsulta = httpServletRequest.getParameter("tipoConsulta");*/

		Boolean veioEncerrarOS = null;

		if (httpServletRequest.getAttribute("veioEncerrarOS") != null) {

			veioEncerrarOS = Boolean.TRUE;

		} else {

			veioEncerrarOS = Boolean.FALSE;

		}

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		if (form.getReset().equals("true")) {

			form.reset();

		} else if (form.getReset().equals("hidrometro")) {

			form.resetHidrometro();
		}

		// Permissao Especial Efetuar Ligacao de Agua sem RA

		boolean atualizarInstalcaoHidrometroSemRA = Fachada.getInstancia()
				.verificarPermissaoEspecial(PermissaoEspecial.ATUALIZAR_INSTALACAO_DO_HIDROMETRO,usuarioLogado);

		httpServletRequest.setAttribute("atualizarInstalcaoHidrometroSemRA",atualizarInstalcaoHidrometroSemRA);

		form.setPermissaoAtualizarInstalacaoHidrometrosemRA("false");

		// Constrói filtro para pesquisa dos locais de instalação
		FiltroHidrometroLocalInstalacao filtroHidrometroLocalInstalacao = new FiltroHidrometroLocalInstalacao();

		filtroHidrometroLocalInstalacao.setCampoOrderBy(FiltroHidrometroLocalInstalacao.DESCRICAO);

		filtroHidrometroLocalInstalacao.adicionarParametro(new ParametroSimples(
			FiltroHidrometroLocalInstalacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

		sessao.setAttribute("colecaoLocalInstalacao", fachada.pesquisar(
				filtroHidrometroLocalInstalacao, HidrometroLocalInstalacao.class.getName()));

		// Constrói o filtro para pesquisa das proteções
		FiltroHidrometroProtecao filtroHidrometroProtecao = new FiltroHidrometroProtecao();

		filtroHidrometroProtecao.setCampoOrderBy(FiltroHidrometroProtecao.DESCRICAO);
		filtroHidrometroProtecao.adicionarParametro(new ParametroSimples(
				FiltroHidrometroProtecao.INDICADOR_USO,	ConstantesSistema.INDICADOR_USO_ATIVO));

		sessao.setAttribute("colecaoProtecao", fachada.pesquisar(
				filtroHidrometroProtecao, HidrometroProtecao.class.getName()));

		Integer idOrdemServico = Util.converterStringParaInteger(httpServletRequest.getParameter("idOrdemServico"));
		form.setUsuarioNaoEncontrado("false");
		
		FiltroPocoTipo filtroPocoTipo = new FiltroPocoTipo();
		filtroPocoTipo.setCampoOrderBy(FiltroPocoTipo.DESCRICAO);
		filtroPocoTipo.adicionarParametro(
				new ParametroSimples(
						FiltroPocoTipo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroPocoTipo.adicionarParametro(
				new ParametroSimples(
						FiltroPocoTipo.INDICADOR_HIDROMETRO_TIPO_POCO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoTipoPoco =
			Fachada.getInstancia().pesquisar(filtroPocoTipo,
					PocoTipo.class.getName());
		
		 httpServletRequest.setAttribute("colecaoTipoPoco", colecaoTipoPoco);

		String idImovel = null;

		if (httpServletRequest.getParameter("matriculaImovel") != null
				&& !httpServletRequest.getParameter("matriculaImovel").equalsIgnoreCase("")) {

			idImovel = (String) httpServletRequest.getParameter("matriculaImovel");
		} else {

			idImovel = form.getIdImovel();
		}

		if (idImovel != null && !idImovel.trim().equals("")) {
			// Pesquisa o imovel na base
			String inscricaoImovelEncontrado = fachada
					.pesquisarInscricaoImovel(new Integer(idImovel));

			if (inscricaoImovelEncontrado != null
					&& !inscricaoImovelEncontrado.equalsIgnoreCase("")) {

				form.setMatriculaImovel(idImovel);

				form.setInscricaoImovel(inscricaoImovelEncontrado);

				FiltroImovel filtroImovel = new FiltroImovel();

				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, idImovel));

				filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_SITUACAO);

				filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_ESGOTO_SITUACAO);

				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico.hidrometro");
				
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico.hidrometroProtecao");

				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");

				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua.hidrometroInstalacaoHistorico");

				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua.hidrometroInstalacaoHistorico.hidrometro");

				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua.hidrometroInstalacaoHistorico.hidrometroProtecao");

				Collection colecaoImovel = fachada.pesquisar(filtroImovel,
						Imovel.class.getName());

				Imovel imovel = (Imovel) colecaoImovel.iterator().next();

				// [FS0002] Verificar Situação do Imovel.

				if (ConstantesSistema.INDICADOR_IMOVEL_EXCLUIDO == imovel
						.getIndicadorExclusao()) {

					throw new ActionServletException(
							"atencao.atualizar_imovel_situacao_invalida", null,
							imovel.getId().toString());
				}

				form.setSituacaoLigacaoAgua(imovel.getLigacaoAguaSituacao().getDescricao());

				form.setSituacaoLigacaoEsgoto(imovel.getLigacaoEsgotoSituacao().getDescricao());

				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.IMOVEL_ID, idImovel));

				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE);

				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE_TIPO);

				Collection colecaoClienteImovel = fachada.pesquisar(
						filtroClienteImovel, ClienteImovel.class.getName());

				if (colecaoClienteImovel != null
						&& !colecaoClienteImovel.isEmpty()) {

					ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovel
							.iterator().next();

					form
							.setClienteUsuario(clienteImovel.getCliente()
									.getNome());

					if (clienteImovel.getCliente().getClienteTipo().getId()
							.intValue() == ClienteTipo.INDICADOR_PESSOA_FISICA
							.intValue()) {

						form.setCpfCnpjCliente(clienteImovel.getCliente()
								.getCpfFormatado());

					} else if (clienteImovel.getCliente().getClienteTipo()
							.getId().intValue() == ClienteTipo.INDICADOR_PESSOA_JURIDICA
							.intValue()) {
						form.setCpfCnpjCliente(clienteImovel.getCliente()
								.getCnpjFormatado());
					} else {
						form.setCpfCnpjCliente("");
					}

				}

				HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = null;

				// Verifica se o tipo de medição foi informado
				if (Util.validarStringNumerica(form.getMedicaoTipo())) {

					Integer medicaoTipo = Integer.parseInt(form
							.getMedicaoTipo());

					fachada.validarExistenciaHidrometro(imovel, medicaoTipo);

					if (MedicaoTipo.LIGACAO_AGUA.equals(medicaoTipo)) {

						hidrometroInstalacaoHistorico = imovel.getLigacaoAgua()
								.getHidrometroInstalacaoHistorico();

					} else if (MedicaoTipo.POCO.equals(medicaoTipo)) {

						hidrometroInstalacaoHistorico = imovel
								.getHidrometroInstalacaoHistorico();
					}

					// Seta os campos obrigatórios do form
					form.setDataInstalacao(Util
							.formatarData(hidrometroInstalacaoHistorico
									.getDataInstalacao()));

					form.setHidrometro(hidrometroInstalacaoHistorico
							.getHidrometro().getNumero());

					form.setLocalInstalacao(hidrometroInstalacaoHistorico
							.getHidrometroLocalInstalacao().getId().toString());

					
					form
							.setIndicadorExistenciaCavalete(hidrometroInstalacaoHistorico
									.getIndicadorExistenciaCavalete()
									.toString());

					form.setLeituraInstalacao(hidrometroInstalacaoHistorico
							.getNumeroLeituraInstalacao().toString());

					// Seta os campos opcionais do form
					form
							.setNumeroSelo(Util
									.converterObjetoParaString(hidrometroInstalacaoHistorico
											.getNumeroSelo()));
					form
							.setLeituraCorte(Util
									.converterObjetoParaString(hidrometroInstalacaoHistorico
											.getNumeroLeituraCorte()));
					form
							.setLeituraSupressao(Util
									.converterObjetoParaString(hidrometroInstalacaoHistorico
											.getNumeroLeituraSupressao()));
					form
							.setLeituraRetirada(Util
									.converterObjetoParaString(hidrometroInstalacaoHistorico
											.getNumeroLeituraRetirada()));
					form
							.setNumeroLacre(Util
									.converterObjetoParaString(hidrometroInstalacaoHistorico
											.getNumeroLacre()));
					
					form.setProtecao(hidrometroInstalacaoHistorico
							.getHidrometroProtecao().getId().toString());

					httpServletRequest.setAttribute(
							"hidrometroInstalacaoHistorico",
							hidrometroInstalacaoHistorico);

				}

			}
		}

		if (Util.validarNumeroMaiorQueZERO(idOrdemServico)) {

			OrdemServico ordemServico = fachada.recuperaOSPorId(idOrdemServico);

			if (ordemServico != null) {

				sessao.setAttribute("ordemServicoEncontrada", "true");

				// Realiza a pesquisa do imóvel
				fachada.validarExibirAtualizarInstalacaoHidrometro(
						ordemServico, veioEncerrarOS);

				form.setVeioEncerrarOS("" + veioEncerrarOS);

				Imovel imovel = ordemServico.getRegistroAtendimento()
						.getImovel();

				// Constrói o filtro para pesquisa do cliente do imóvel
				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("cliente");

				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.IMOVEL_ID, imovel.getId()));

				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
						ClienteRelacaoTipo.USUARIO));

				filtroClienteImovel.adicionarParametro(new ParametroNulo(
						FiltroClienteImovel.DATA_FIM_RELACAO));

				// Realiza a pesquisa do cliente usuário
				ClienteImovel clienteImovel = (ClienteImovel) fachada
						.pesquisar(filtroClienteImovel,
								ClienteImovel.class.getName()).iterator()
						.next();

				Cliente cliente = clienteImovel.getCliente();

				String cpfCnpjCliente = "";

				if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {

					cpfCnpjCliente = cliente.getCpfFormatado();

				} else {

					cpfCnpjCliente = cliente.getCnpjFormatado();
				}

				// Seta os campos obrigatórios do form
				form.setIdOrdemServico(idOrdemServico.toString());

				form.setNomeOrdemServico(ordemServico.getServicoTipo()
						.getDescricao());

				form.setMatriculaImovel(imovel.getId().toString());

				String inscricaoFormatada = fachada
						.pesquisarInscricaoImovel(imovel.getId());

				form.setInscricaoImovel(inscricaoFormatada);

				form.setSituacaoLigacaoAgua(imovel.getLigacaoAguaSituacao()
						.getDescricao());

				form.setSituacaoLigacaoEsgoto(imovel.getLigacaoEsgotoSituacao()
						.getDescricao());

				form.setClienteUsuario(cliente.getNome());

				if ("00.000.000/0000-00".equals(cpfCnpjCliente)) {

					form.setCpfCnpjCliente("");

				} else {

					form.setCpfCnpjCliente(cpfCnpjCliente);
				}

				HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = null;

				// Verifica se o tipo de medição foi informado
				if (Util.validarStringNumerica(form.getMedicaoTipo())) {

					Integer medicaoTipo = Integer.parseInt(form
							.getMedicaoTipo());

					fachada.validarExistenciaHidrometro(imovel, medicaoTipo);

					if (MedicaoTipo.LIGACAO_AGUA.equals(medicaoTipo)) {

						hidrometroInstalacaoHistorico = imovel.getLigacaoAgua()
								.getHidrometroInstalacaoHistorico();

					} else if (MedicaoTipo.POCO.equals(medicaoTipo)) {

						hidrometroInstalacaoHistorico = imovel
								.getHidrometroInstalacaoHistorico();
						
						Imovel imovelPoco = fachada.pesquisarImovel(imovel.getId());
						
						if (imovelPoco.getPocoTipo() != null){
						form.setTipoPoco(
								imovelPoco.getPocoTipo().getId().toString());
						}
						
					}

					// Seta os campos obrigatórios do form
					form.setDataInstalacao(Util
							.formatarData(hidrometroInstalacaoHistorico
									.getDataInstalacao()));

					form.setHidrometro(hidrometroInstalacaoHistorico
							.getHidrometro().getNumero());

					form.setLocalInstalacao(hidrometroInstalacaoHistorico
							.getHidrometroLocalInstalacao().getId().toString());

					form.setProtecao(hidrometroInstalacaoHistorico
							.getHidrometroProtecao().getId().toString());
					form
							.setIndicadorExistenciaCavalete(hidrometroInstalacaoHistorico
									.getIndicadorExistenciaCavalete()
									.toString());

					form.setLeituraInstalacao(hidrometroInstalacaoHistorico
							.getNumeroLeituraInstalacao().toString());

					// Seta os campos opcionais do form
					form
							.setNumeroSelo(Util
									.converterObjetoParaString(hidrometroInstalacaoHistorico
											.getNumeroSelo()));
					form
							.setLeituraCorte(Util
									.converterObjetoParaString(hidrometroInstalacaoHistorico
											.getNumeroLeituraCorte()));
					form
							.setLeituraSupressao(Util
									.converterObjetoParaString(hidrometroInstalacaoHistorico
											.getNumeroLeituraSupressao()));
					form
							.setLeituraRetirada(Util
									.converterObjetoParaString(hidrometroInstalacaoHistorico
											.getNumeroLeituraRetirada()));
					form
							.setNumeroLacre(Util
									.converterObjetoParaString(hidrometroInstalacaoHistorico
											.getNumeroLacre()));

					httpServletRequest.setAttribute(
							"hidrometroInstalacaoHistorico",
							hidrometroInstalacaoHistorico);

				}
			} else {
				sessao.removeAttribute("ordemServicoEncontrada");
				form.setIdOrdemServico("");
				form.setNomeOrdemServico("Ordem de Serviço Inexistente");
			}
		} else {
			form.setDataCorrente(new Date());
			httpServletRequest.setAttribute("nomeCampo", "idOrdemServico");
		}

		return retorno;
	}

}