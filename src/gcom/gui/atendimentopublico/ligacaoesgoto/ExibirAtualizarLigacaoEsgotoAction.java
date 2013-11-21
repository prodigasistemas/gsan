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
package gcom.gui.atendimentopublico.ligacaoesgoto;

import gcom.atendimentopublico.FiltroLigacaoOrigem;
import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.ligacaoesgoto.FiltroDiametroLigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoMaterialEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoPerfilEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pré-exibição da página de atualizar ligação de esgoto
 * 
 * @author Leonardo Regis
 * @created 18 de Julho de 2006
 */
public class ExibirAtualizarLigacaoEsgotoAction extends GcomAction {

	/**
	 * Exibição de atualização de ligação de esgoto.
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		// Session
		HttpSession sessao = httpServletRequest.getSession(false);
		// Seta Retorno (Forward = Exibição da Tela de Atualização)
		ActionForward retorno = actionMapping
				.findForward("atualizarLigacaoEsgoto");
		// Form
		AtualizarLigacaoEsgotoActionForm ligacaoEsgotoActionForm = (AtualizarLigacaoEsgotoActionForm) actionForm;
		// Fachada
		Fachada fachada = Fachada.getInstancia();

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		Boolean veioEncerrarOS = null;

		if (httpServletRequest.getAttribute("veioEncerrarOS") != null) {
			veioEncerrarOS = Boolean.TRUE;
		} else {
			veioEncerrarOS = Boolean.FALSE;
		}

		// Seta coleção de diâmetro de ligação
		getDiametroLigacaoCollection(sessao, fachada);
		// Seta coleção de material de ligação
		getLigacaoMaterialCollection(sessao, fachada);
		// Seta coleção de perfil de ligação
		getPerfilLigacaoCollection(sessao, fachada);
		// Seta colecao de Ligacao origem
		getLigacaoOrigemCollection(sessao, fachada);
		// Testa se é redirecionamento
		if (!isRedirectioned(ligacaoEsgotoActionForm)) {

			// Permissao Especial Efetuar Ligacao de Esgoto sem RA

			boolean atualizarLigacaoEsgotoSemRA = Fachada
					.getInstancia()
					.verificarPermissaoEspecial(
							PermissaoEspecial.ATUALIZAR_LIGACAO_DE_ESGOTO_SEM_RA,
							usuarioLogado);

			ligacaoEsgotoActionForm
			.setPermissaoAtualizarLigacaoEsgotosemRA("false");

			if (!veioEncerrarOS) {
				
				if (atualizarLigacaoEsgotoSemRA) {
					ligacaoEsgotoActionForm
							.setPermissaoAtualizarLigacaoEsgotosemRA("true");
				}
				
				httpServletRequest.setAttribute("atualizarLigacaoEsgotoSemRA",
						atualizarLigacaoEsgotoSemRA);			
			}
			
			
			
			
			

			String idImovel = null;

			if (httpServletRequest.getParameter("matriculaImovel") != null
					&& !httpServletRequest.getParameter("matriculaImovel")
							.equals("")) {

				idImovel = (String) httpServletRequest
						.getParameter("matriculaImovel");
				ligacaoEsgotoActionForm.setIdImovel(idImovel);
			} else {

				idImovel = ligacaoEsgotoActionForm.getIdImovel();
			}

			if (idImovel != null && !idImovel.trim().equals("")) {
				// Pesquisa o imovel na base
				String inscricaoImovelEncontrado = fachada
						.pesquisarInscricaoImovel(new Integer(idImovel));
				if (inscricaoImovelEncontrado != null
						&& !inscricaoImovelEncontrado.equalsIgnoreCase("")) {

					ligacaoEsgotoActionForm.setIdImovel(idImovel);

					ligacaoEsgotoActionForm.setMatriculaImovel(idImovel);

					ligacaoEsgotoActionForm
							.setInscricaoImovel(inscricaoImovelEncontrado);

					Imovel imovel = (Imovel) fachada.pesquisarDadosImovel(new Integer(idImovel));

					// Verificar Ligação de Esgoto do Imovel.
					if (imovel.getLigacaoEsgoto() == null) {
						throw new ActionServletException(
								"atencao.atualizar_ligacao_esgoto_inexistente",
								null, imovel.getId().toString());
					}

					// [FS0002] Verificar Situação do Imovel.
					if (imovel.getIndicadorExclusao().intValue() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {
						throw new ActionServletException(
								"atencao.atualizar_situacao_imovel_indicador_exclusao_esgoto",
								null, imovel.getId().toString());
					}

					// [FS003] Validar Situação de Esgoto do imóvel.
					if (imovel.getLigacaoEsgotoSituacao().getId().intValue() == LigacaoEsgotoSituacao.POTENCIAL
							.intValue()
							|| imovel.getLigacaoEsgotoSituacao().getId()
									.intValue() == LigacaoEsgotoSituacao.FACTIVEL
									.intValue()) {
						throw new ActionServletException(
								"atencao.atualizar_situacao_ligacao_esgoto_invalida",
								null, imovel.getLigacaoEsgotoSituacao()
										.getDescricao());
					}

					// Matricula Imóvel
					ligacaoEsgotoActionForm.setMatriculaImovel(imovel.getId().toString());

					// Situação da Ligação de Agua
					String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao().getDescricao();
					ligacaoEsgotoActionForm.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

					// Situação da Ligação de Esgoto
					String situacaoLigacaoEsgoto = imovel.getLigacaoEsgotoSituacao().getDescricao();
					ligacaoEsgotoActionForm.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

					getCliente(ligacaoEsgotoActionForm, fachada);

					if (ligacaoEsgotoActionForm.getPerfilLigacao() != null
							&& !ligacaoEsgotoActionForm.getPerfilLigacao()
									.equals("-1")) {

						FiltroLigacaoEsgotoPerfil filtroLigacaoPercentualEsgoto = new FiltroLigacaoEsgotoPerfil();

						filtroLigacaoPercentualEsgoto
								.adicionarParametro(new ParametroSimples(
										FiltroLigacaoEsgotoPerfil.ID,
										ligacaoEsgotoActionForm
												.getPerfilLigacao()));

						Collection colecaoPercentualEsgoto = fachada.pesquisar(
								filtroLigacaoPercentualEsgoto,
								LigacaoEsgotoPerfil.class.getName());

						if (colecaoPercentualEsgoto != null
								&& !colecaoPercentualEsgoto.isEmpty()) {

							LigacaoEsgotoPerfil percentualEsgotoPerfil = (LigacaoEsgotoPerfil) colecaoPercentualEsgoto
									.iterator().next();

							String percentualFormatado = percentualEsgotoPerfil
									.getPercentualEsgotoConsumidaColetada()
									.toString().replace(".", ",");

							ligacaoEsgotoActionForm
									.setPercentualEsgoto(percentualFormatado);
						}
					}
					
					//-----------------------------------------------------------
					// Alterado por Rômulo Aurélio [CRC-812] Data: 23/12/2008
					// Permissao Especial ALTERAR PERCENTUAL COLETA ESGOTO
					boolean alterarPercentualColetaEsgoto = Fachada
							.getInstancia()
							.verificarPermissaoEspecial(
									PermissaoEspecial.ALTERAR_PERCENTUAL_COLETA_ESGOTO,
									usuarioLogado);

					if (alterarPercentualColetaEsgoto) {
						ligacaoEsgotoActionForm.setPercentualColeta("100,00");
						httpServletRequest.setAttribute(
								"alterarPercentualColetaEsgoto",
								alterarPercentualColetaEsgoto);
					} else {
						ligacaoEsgotoActionForm.setPercentualColeta("100,00");
					}
					// -----------------------------------------------------------

					FiltroLigacaoEsgoto filtroLigacaoEsgoto = new FiltroLigacaoEsgoto();
					filtroLigacaoEsgoto.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgoto.ID, imovel.getId()));
					filtroLigacaoEsgoto.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoPerfil");
					filtroLigacaoEsgoto.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoDiametro");
					filtroLigacaoEsgoto.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoMaterial");
					filtroLigacaoEsgoto.adicionarCaminhoParaCarregamentoEntidade("ligacaoOrigem");
					filtroLigacaoEsgoto.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoPerfil");

					setLigacaoEsgotoForm(ligacaoEsgotoActionForm, imovel, fachada, httpServletRequest,usuarioLogado);

				} else {

					httpServletRequest.setAttribute("corImovel", "exception");

					ligacaoEsgotoActionForm
							.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
				}

			}

			String idOrdemServico = null;

			if (httpServletRequest.getParameter("idOrdemServico") != null
					&& !httpServletRequest.getParameter("idOrdemServico")
							.equals("")) {

				idOrdemServico = (String) httpServletRequest
						.getParameter("idOrdemServico");
				ligacaoEsgotoActionForm.setIdImovel(idImovel);
			} else {

				idOrdemServico = ligacaoEsgotoActionForm.getIdOrdemServico();
			}

			// Testa matrícula
			if (idOrdemServico != null && !idOrdemServico.equals("")) {

				// Recupera Imóvel
				OrdemServico ordemServico = fachada
						.recuperaOSPorId(new Integer(ligacaoEsgotoActionForm
								.getIdOrdemServico()));

				if (ordemServico != null) {

					// Faz todas as validações da ligação de esgoto do imóvel.
					fachada.validarLigacaoEsgotoImovelExibir(ordemServico,
							veioEncerrarOS);

					ligacaoEsgotoActionForm.setVeioEncerrarOS(""
							+ veioEncerrarOS);

					Imovel imovel = ordemServico.getRegistroAtendimento()
							.getImovel();

					ligacaoEsgotoActionForm.setIdOrdemServico(""
							+ ordemServico.getId());
					ligacaoEsgotoActionForm.setNomeOrdemServico(ordemServico
							.getServicoTipo().getDescricao());

					// Inscrição do Imóvel
					String inscricaoImovel = imovel.getInscricaoFormatada();
					ligacaoEsgotoActionForm.setMatriculaImovel(""
							+ imovel.getId());
					ligacaoEsgotoActionForm.setInscricaoImovel(inscricaoImovel);

					// Situação da Ligação de Agua
					String situacaoLigacaoAgua = imovel
							.getLigacaoAguaSituacao().getDescricao();
					ligacaoEsgotoActionForm
							.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

					// Situação da Ligação de Esgoto
					String situacaoLigacaoEsgoto = imovel
							.getLigacaoEsgotoSituacao().getDescricao();
					ligacaoEsgotoActionForm
							.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

					// Seta no form informações do cliente
					getCliente(ligacaoEsgotoActionForm, fachada);

					/*-------------------- Ligação de Esgoto -------------------------------*/
					// Seta no form informações da ligação de esgoto
					setLigacaoEsgotoForm(ligacaoEsgotoActionForm, imovel,
							fachada, httpServletRequest, usuarioLogado);
					/*-------------------- Fim Dados da Ligação ----------------------------*/
					sessao.setAttribute("osEncontrada", "true");
				} else {
					sessao.removeAttribute("osEncontrada");
					ligacaoEsgotoActionForm
							.setNomeOrdemServico("Ordem de Serviço inexistente");
					ligacaoEsgotoActionForm.setIdOrdemServico("");
				}
			} else {
				httpServletRequest.setAttribute("nomeCampo", "idOrdemServico");
				ligacaoEsgotoActionForm.setDataConcorrencia(new Date());
			}
		} else {
			// Tela redirecionada (Motivo: Alteração do Perfil de Ligação)
			// Setando Redirect para estado inicial
			ligacaoEsgotoActionForm.setRedirect("false");
			// Fazendo reload do Percentual de Esgoto
			setPercentualEsgoto(ligacaoEsgotoActionForm, fachada);
			
			//-----------------------------------------------------------
			// Alterado por Rômulo Aurélio [CRC-812] Data: 23/12/2008
			// Permissao Especial ALTERAR PERCENTUAL COLETA ESGOTO
			boolean alterarPercentualColetaEsgoto = Fachada
					.getInstancia()
					.verificarPermissaoEspecial(
							PermissaoEspecial.ALTERAR_PERCENTUAL_COLETA_ESGOTO,
							usuarioLogado);

			if (alterarPercentualColetaEsgoto) {
				ligacaoEsgotoActionForm.setPercentualColeta("100,00");
				httpServletRequest.setAttribute(
						"alterarPercentualColetaEsgoto",
						alterarPercentualColetaEsgoto);
			} else {
				ligacaoEsgotoActionForm.setPercentualColeta("100,00");
			}
			// -----------------------------------------------------------

		}

		return retorno;
	}

	/**
	 * Recupera Cliente do Imóvel
	 * 
	 * @author Leonardo Regis
	 * @date 21/07/2006
	 * 
	 * @param ligacaoEsgotoActionForm
	 * @param fachada
	 */
	private void getCliente(
			AtualizarLigacaoEsgotoActionForm ligacaoEsgotoActionForm,
			Fachada fachada) {
		// Filtra ClienteImovel
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.IMOVEL_ID, ligacaoEsgotoActionForm
						.getMatriculaImovel()));
		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
				ClienteRelacaoTipo.USUARIO));
		filtroClienteImovel.adicionarParametro(new ParametroNulo(
				FiltroClienteImovel.DATA_FIM_RELACAO));
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
		// Recupera Cliente
		Collection colecaoClienteImovel = fachada.pesquisar(
				filtroClienteImovel, ClienteImovel.class.getName());
		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {
			ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovel
					.iterator().next();
			Cliente cliente = clienteImovel.getCliente();
			// Seta informações do cliente
			ligacaoEsgotoActionForm.setClienteUsuario(cliente.getNome());
			// CPF & CNPJ
			String cpfCnpj = "";
			if (cliente.getCpfFormatado() != null
					&& !cliente.getCpfFormatado().equals("")) {
				cpfCnpj = cliente.getCpfFormatado();
			} else {
				cpfCnpj = cliente.getCnpjFormatado();
			}
			ligacaoEsgotoActionForm.setCpfCnpjCliente(cpfCnpj);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Cliente");
		}
	}

	/**
	 * Seta as informações da ligação de esgoto no form bean
	 * 
	 * @author Leonardo Regis
	 * @date 21/07/2006
	 * 
	 * @param ligacaoEsgotoActionForm
	 * @param imovel
	 */
	private void setLigacaoEsgotoForm(
			AtualizarLigacaoEsgotoActionForm ligacaoEsgotoActionForm,
			Imovel imovel, Fachada fachada, HttpServletRequest httpServletRequest, Usuario usuarioLogado) {
		LigacaoEsgoto ligacaoEsgoto = imovel.getLigacaoEsgoto();

		if (ligacaoEsgoto != null) {

			// Data de Ligacao
			String dataLigacao = Util.formatarData(ligacaoEsgoto
					.getDataLigacao());
			ligacaoEsgotoActionForm.setDataLigacao(dataLigacao);

			if (ligacaoEsgoto.getLigacaoEsgotoDiametro().getId() != null) {
				ligacaoEsgotoActionForm.setDiametroLigacao(ligacaoEsgoto
						.getLigacaoEsgotoDiametro().getId().toString());
			}
			if (ligacaoEsgoto.getLigacaoEsgotoMaterial().getId() != null) {
				ligacaoEsgotoActionForm.setMaterialLigacao(ligacaoEsgoto
						.getLigacaoEsgotoMaterial().getId().toString());
			}
			if (ligacaoEsgoto.getLigacaoEsgotoPerfil().getId() != null) {
				ligacaoEsgotoActionForm.setPerfilLigacao(ligacaoEsgoto
						.getLigacaoEsgotoPerfil().getId().toString());
			}
			
//			 -----------------------------------------------------------
			// Alterado por Rômulo Aurélio [CRC-812] Data: 23/12/2008
			// Permissao Especial ALTERAR PERCENTUAL COLETA ESGOTO
			boolean alterarPercentualColetaEsgoto = Fachada
					.getInstancia()
					.verificarPermissaoEspecial(
							PermissaoEspecial.ALTERAR_PERCENTUAL_COLETA_ESGOTO,
							usuarioLogado);

			if (alterarPercentualColetaEsgoto) {
				httpServletRequest.setAttribute(
						"alterarPercentualColetaEsgoto",
						alterarPercentualColetaEsgoto);
			} 
			// -----------------------------------------------------------

			ligacaoEsgotoActionForm.setPercentualColeta(ligacaoEsgoto.getPercentualAguaConsumidaColetada().toString().replace(".", ","));
			ligacaoEsgotoActionForm.setIndicadorCaixaGordura(ligacaoEsgoto.getIndicadorCaixaGordura().toString());
			ligacaoEsgotoActionForm.setIndicadorLigacao(ligacaoEsgoto.getIndicadorLigacaoEsgoto().toString());

			if (ligacaoEsgoto.getLigacaoOrigem() != null) {

				ligacaoEsgotoActionForm.setIdLigacaoOrigem(ligacaoEsgoto
						.getLigacaoOrigem().getId().toString());

			}

			setPercentualEsgoto(ligacaoEsgotoActionForm, fachada);
		}
	}

	/**
	 * Seta Valor de Percentual de Esgoto de Acordo com o Perfil de Ligação.
	 * 
	 * @author Leonardo Regis
	 * @date 22/07/2006
	 * 
	 * @param ligacaoEsgotoActionForm
	 * @param fachada
	 */
	private void setPercentualEsgoto(
			AtualizarLigacaoEsgotoActionForm ligacaoEsgotoActionForm,
			Fachada fachada) {
		FiltroLigacaoPerfilEsgoto filtroLigacaoEsgotoPerfil = new FiltroLigacaoPerfilEsgoto();
		filtroLigacaoEsgotoPerfil.adicionarParametro(new ParametroSimples(
				FiltroLigacaoPerfilEsgoto.ID, ligacaoEsgotoActionForm
						.getPerfilLigacao()));
		// filtroLigacaoEsgotoPerfil
		// .adicionarCaminhoParaCarregamentoEntidade("percentualEsgotoConsumidaColetada");
		// Recupera Perfil da Ligação de Esgoto
		Collection colecaoLigacaoEsgotoPerfil = fachada.pesquisar(
				filtroLigacaoEsgotoPerfil, LigacaoEsgotoPerfil.class.getName());
		ligacaoEsgotoActionForm.setPercentualEsgoto(null);
		if (colecaoLigacaoEsgotoPerfil != null
				&& !colecaoLigacaoEsgotoPerfil.isEmpty()) {
			LigacaoEsgotoPerfil ligacaoEsgotoPerfil = (LigacaoEsgotoPerfil) colecaoLigacaoEsgotoPerfil
					.iterator().next();
			if (ligacaoEsgotoPerfil.getPercentualEsgotoConsumidaColetada() != null) {
				ligacaoEsgotoActionForm.setPercentualEsgoto(ligacaoEsgotoPerfil
						.getPercentualEsgotoConsumidaColetada().toString()
						.replace(".", ","));
			}
		}
	}

	/**
	 * Carrega coleção de diâmetro da ligação.
	 * 
	 * @author Leonardo Regis
	 * @date 21/07/2006
	 * 
	 * @param sessao
	 * @param fachada
	 */
	private void getDiametroLigacaoCollection(HttpSession sessao,
			Fachada fachada) {
		// Filtra Diâmetro da Ligação
		FiltroDiametroLigacaoEsgoto filtroDiametroLigacaoEsgoto = new FiltroDiametroLigacaoEsgoto();
		filtroDiametroLigacaoEsgoto.adicionarParametro(new ParametroSimples(
				FiltroDiametroLigacaoEsgoto.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroDiametroLigacaoEsgoto
				.setCampoOrderBy(FiltroDiametroLigacaoEsgoto.DESCRICAO);

		Collection colecaoDiametroLigacaoEsgoto = fachada.pesquisar(
				filtroDiametroLigacaoEsgoto, LigacaoEsgotoDiametro.class
						.getName());
		if (colecaoDiametroLigacaoEsgoto != null
				&& !colecaoDiametroLigacaoEsgoto.isEmpty()) {
			sessao.setAttribute("colecaoDiametroLigacaoEsgoto",
					colecaoDiametroLigacaoEsgoto);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Diametro da Ligação");
		}
	}

	/**
	 * Carrega coleção de material da ligação.
	 * 
	 * @author Leonardo Regis
	 * @date 21/07/2006
	 * 
	 * @param sessao
	 * @param fachada
	 */
	private void getLigacaoMaterialCollection(HttpSession sessao,
			Fachada fachada) {
		// Filtra Material da Ligação
		FiltroLigacaoMaterialEsgoto filtroMaterialLigacaoEsgoto = new FiltroLigacaoMaterialEsgoto();
		filtroMaterialLigacaoEsgoto.adicionarParametro(new ParametroSimples(
				FiltroLigacaoMaterialEsgoto.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroMaterialLigacaoEsgoto
				.setCampoOrderBy(FiltroLigacaoMaterialEsgoto.DESCRICAO);

		Collection colecaoMaterialLigacaoEsgoto = fachada.pesquisar(
				filtroMaterialLigacaoEsgoto, LigacaoEsgotoMaterial.class
						.getName());

		if (colecaoMaterialLigacaoEsgoto != null
				&& !colecaoMaterialLigacaoEsgoto.isEmpty()) {
			sessao.setAttribute("colecaoMaterialLigacaoEsgoto",
					colecaoMaterialLigacaoEsgoto);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Material da Ligação");
		}
	}

	/**
	 * Carrega coleção de perfil da ligação.
	 * 
	 * @author Leonardo Regis
	 * @date 21/07/2006
	 * 
	 * @param sessao
	 * @param fachada
	 */
	private void getPerfilLigacaoCollection(HttpSession sessao, Fachada fachada) {
		// Filtra Perfil da Ligação
		FiltroLigacaoPerfilEsgoto filtroPerfilLigacaoEsgoto = new FiltroLigacaoPerfilEsgoto();
		filtroPerfilLigacaoEsgoto.adicionarParametro(new ParametroSimples(
				FiltroLigacaoPerfilEsgoto.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroPerfilLigacaoEsgoto
				.setCampoOrderBy(FiltroLigacaoPerfilEsgoto.DESCRICAO);

		Collection colecaoPerfilLigacaoEsgoto = fachada.pesquisar(
				filtroPerfilLigacaoEsgoto, LigacaoEsgotoPerfil.class.getName());
		if (colecaoPerfilLigacaoEsgoto != null
				&& !colecaoPerfilLigacaoEsgoto.isEmpty()) {
			sessao.setAttribute("colecaoPerfilLigacaoEsgoto",
					colecaoPerfilLigacaoEsgoto);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Perfil de Ligação");
		}
	}

	/**
	 * Carrega coleção de ligação de origem.
	 * 
	 * @author Rômulo Aurélio
	 * @date 11/09/2008
	 * 
	 * @param sessao
	 * @param fachada
	 */
	private void getLigacaoOrigemCollection(HttpSession sessao, Fachada fachada) {
		// Filtra Perfil da Ligação
		FiltroLigacaoOrigem filtroLigacaoOrigem = new FiltroLigacaoOrigem();
		filtroLigacaoOrigem.adicionarParametro(new ParametroSimples(
				FiltroLigacaoOrigem.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroLigacaoOrigem.setCampoOrderBy(FiltroLigacaoOrigem.DESCRICAO);

		Collection colecaoLigacaoOrigem = fachada.pesquisar(
				filtroLigacaoOrigem, LigacaoOrigem.class.getName());
		if (colecaoLigacaoOrigem != null && !colecaoLigacaoOrigem.isEmpty()) {
			sessao.setAttribute("colecaoLigacaoOrigem", colecaoLigacaoOrigem);
		} else {
			sessao.setAttribute("colecaoLigacaoOrigem", new ArrayList());
		}
	}

	/**
	 * Valida Redirecionamento (Motivo: Alteração do Perfil da Ligação)
	 * 
	 * @author Leonardo Regis
	 * @date 22/07/2006
	 * 
	 * @param form
	 * @return boolean
	 */
	private boolean isRedirectioned(AtualizarLigacaoEsgotoActionForm form) {
		boolean toReturn = false;
		if (form.getRedirect().equalsIgnoreCase("true")) {
			toReturn = true;
		}
		return toReturn;
	}

}