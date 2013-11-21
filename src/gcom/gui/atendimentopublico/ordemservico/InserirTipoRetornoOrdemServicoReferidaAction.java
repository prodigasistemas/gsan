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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action para inserir o tipo retorno da os referida.
 * 
 * @author lms
 * @date 28/07/2006
 */
public class InserirTipoRetornoOrdemServicoReferidaAction extends GcomAction {

	/**
	 * Este caso de uso permite inserir tipo retorno da os referida
	 * 
	 * [UC0396] Inserir Tipo de Retorno da OS Referida
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
		InserirTipoRetornoOrdemServicoReferidaActionForm form = (InserirTipoRetornoOrdemServicoReferidaActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		// HttpSession sessao = httpServletRequest.getSession(false);

		// Atualiza a entidade com os valores do formulário
		OsReferidaRetornoTipo osReferidaRetornoTipo = form
				.getOsReferidaRetornoTipo();

		if (Util.validarNumeroMaiorQueZERO(form.getServicoTipoReferencia())) {
			// Constrói o filtro para pesquisa do serviço tipo referência
			FiltroServicoTipoReferencia filtroServicoTipoReferencia = new FiltroServicoTipoReferencia();
			filtroServicoTipoReferencia
					.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
							form.getServicoTipoReferencia()));
			// Realiza a pesquisa serviço tipo referência
			ServicoTipoReferencia servicoTipoReferencia = (ServicoTipoReferencia) fachada
					.pesquisar(filtroServicoTipoReferencia,
							ServicoTipoReferencia.class.getName()).iterator()
					.next();
			osReferidaRetornoTipo
					.setServicoTipoReferencia(servicoTipoReferencia);
		}

		if (Util.validarNumeroMaiorQueZERO(form
				.getAtendimentoMotivoEncerramento())) {
			// Constrói o filtro para pesquisa do atendimento motivo
			// encerramento
			FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
			filtroAtendimentoMotivoEncerramento
					.adicionarParametro(new ParametroSimples(
							FiltroAtendimentoMotivoEncerramento.ID, form
									.getAtendimentoMotivoEncerramento()));
			// Realiza a pesquisa serviço atendimento motivo encerramento
			AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = (AtendimentoMotivoEncerramento) fachada
					.pesquisar(filtroAtendimentoMotivoEncerramento,
							AtendimentoMotivoEncerramento.class.getName())
					.iterator().next();
			osReferidaRetornoTipo
					.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
		}
		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		// Registrando a operação
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO__TIPO_RETORNO_OS_REFERIDA_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO__TIPO_RETORNO_OS_REFERIDA_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		osReferidaRetornoTipo.setOperacaoEfetuada(operacaoEfetuada);
		osReferidaRetornoTipo.adicionarUsuario(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
    	registradorOperacao.registrarOperacao(osReferidaRetornoTipo);

		// Atualiza a base de dados com as alterações da instalação hidrômetro
		fachada.inserirOSReferidaRetornoTipo(osReferidaRetornoTipo);

		// Exibe a página de sucesso
		montarPaginaSucesso(httpServletRequest,
				"Tipo de Retorno da OS Referida "
						+ osReferidaRetornoTipo.getDescricao()
						+ " inserido com sucesso.", "Inserir outro Tipo de Retorno da OS Referida",
				"exibirInserirTipoRetornoOrdemServicoReferidaAction.do");

		return retorno;

	}

}