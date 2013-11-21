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
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.PocoTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action para atualização da instalação do hidrômetro.
 * 
 * @author lms
 * @date 24/07/2006
 */
public class AtualizarInstalacaoHidrometroAction extends GcomAction {

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

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		AtualizarInstalacaoHidrometroActionForm form = (AtualizarInstalacaoHidrometroActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		Imovel imovel = null;

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		/*
		 * [UC0107] Registrar Transação
		 * 
		 */
//		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
//				Operacao.OPERACAO_INSTALACAO_HIDROMETRO_ATUALIZAR,
//				new UsuarioAcaoUsuarioHelper(usuario,
//						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
//
//		Operacao operacao = new Operacao();
//		operacao.setId(Operacao.OPERACAO_INSTALACAO_HIDROMETRO_ATUALIZAR);
//
//		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
//		operacaoEfetuada.setOperacao(operacao);
		// [UC0107] -Fim- Registrar Transação

		if (form.getIdOrdemServico() != null && !form.getIdOrdemServico().equals("")) {

			Integer idOrdemServico = Util.converterStringParaInteger(form.getIdOrdemServico());

			OrdemServico ordemServico = fachada.recuperaOSPorId(idOrdemServico);

			// Valida a ordem de serviço
			fachada.validarExibirAtualizarInstalacaoHidrometro(ordemServico,false);

			imovel = ordemServico.getRegistroAtendimento().getImovel();

		} else {
			String idImovel = form.getIdImovel();

			String inscricaoImovelEncontrado = fachada
					.pesquisarInscricaoImovel(new Integer(idImovel));

			form.setMatriculaImovel(idImovel);

			form.setInscricaoImovel(inscricaoImovelEncontrado);

			FiltroImovel filtroImovel = new FiltroImovel();

			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, idImovel));

			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_SITUACAO);

			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_ESGOTO_SITUACAO);

			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico.hidrometro");

			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");

			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua.hidrometroInstalacaoHistorico");

			Collection colecaoImovel = fachada.pesquisar(filtroImovel,
					Imovel.class.getName());

			imovel = (Imovel) colecaoImovel.iterator().next();

		}

		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = null;

		Integer medicaoTipo = Integer.parseInt(form.getMedicaoTipo());

		if (MedicaoTipo.LIGACAO_AGUA.equals(medicaoTipo)) {
			hidrometroInstalacaoHistorico = imovel.getLigacaoAgua()
					.getHidrometroInstalacaoHistorico();
		} else if (MedicaoTipo.POCO.equals(medicaoTipo)) {
			hidrometroInstalacaoHistorico = imovel
					.getHidrometroInstalacaoHistorico();
			
			PocoTipo pocoTipo = new PocoTipo();
			pocoTipo.setId(new Integer(form.getTipoPoco()));
			
			imovel.setPocoTipo(pocoTipo);
		}

		// Valida a existência do hidrômetro
		fachada.validarExistenciaHidrometro(imovel, medicaoTipo);

		// Atualiza a entidade com os valores do formulário
		form.setFormValues(hidrometroInstalacaoHistorico);

		// Informa que o usuário que fez a instalação é o usuário logado
		hidrometroInstalacaoHistorico.setUsuarioInstalacao((Usuario) sessao
				.getAttribute("usuarioLogado"));

		// Atualiza a base de dados com as alterações da instalação hidrômetro
		fachada.atualizarInstalacaoHidrometro(imovel, Integer.parseInt(form
				.getMedicaoTipo()),usuario);

		// Setando Operação
//		hidrometroInstalacaoHistorico.setOperacaoEfetuada(operacaoEfetuada);
//		hidrometroInstalacaoHistorico.adicionarUsuario(usuario,
//				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
//		registradorOperacao.registrarOperacao(hidrometroInstalacaoHistorico);

		// Exibe a página de sucesso

		montarPaginaSucesso(httpServletRequest,
				"Atualização do Histórico da Instalação de Hidrômetro do imóvel "
						+ imovel.getId() + " efetuada com sucesso.",
				"Efetuar nova Atualização do Histórico da Instalação de Hidrômetro",
				"exibirAtualizarInstalacaoHidrometroAction.do?menu=sim");

		// Exibe a página de sucesso
		/*
		 * montarPaginaSucesso(httpServletRequest, "Atualização do Histórico da
		 * Instalação de Hidrômetro do imóvel " + imovel.getId() + " efetuada
		 * com sucesso.", "Efetuar Atualização do Histórico da Instalação de
		 * Hidrômetro", "exibirEfetuarLigacaoEsgotoAction.do?menu=sim",
		 * "exibirAtualizarInstalacaoHidrometroAction.do");
		 */

		return retorno;
	}

}