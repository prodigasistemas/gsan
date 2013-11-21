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
package gcom.gui.cadastro.tarifasocial;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ManterTarifaSocialAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Instancia da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		RegistroAtendimento registroAtendimento = null;
		RegistroAtendimentoUnidade registroAtendimentoUnidade = null;
		
		if (sessao.getAttribute("pesquisaImovel") == null) {

			registroAtendimento = (RegistroAtendimento) sessao
					.getAttribute("ra");

//			AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
//			atendimentoMotivoEncerramento.setId(15);
//
//			registroAtendimento
//					.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);

			registroAtendimentoUnidade = new RegistroAtendimentoUnidade();
			registroAtendimentoUnidade
					.setRegistroAtendimento(registroAtendimento);
			registroAtendimentoUnidade.setUnidadeOrganizacional(usuarioLogado
					.getUnidadeOrganizacional());
			registroAtendimentoUnidade.setUsuario(usuarioLogado);
			AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
			atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
			registroAtendimentoUnidade
					.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
			registroAtendimentoUnidade.setUltimaAlteracao(new Date());

		}

		// Imóvel que está sendo trabalhado
		Imovel imovelSessao = (Imovel) sessao.getAttribute("imovelTarifa");

		Collection colecaoTarifaSocialHelperAtualizar = (Collection) sessao
				.getAttribute("colecaoTarifaSocialHelperAtualizar");
		
		// Imóveis Anteriores do Usuários que foram excluídos da Tarifa Social 
		Collection colecaoImoveisExcluidosTarifaSocial = (Collection) sessao
				.getAttribute("colecaoImoveisExcluidosTarifaSocial");

		// Para apenas uma economia
		Collection colecaoTarifaSocialExcluida = (Collection) sessao
				.getAttribute("colecaoTarifaSocialExcluida");

		// Recadastramento
		Collection colecaoTarifasSociaisRecadastradas = (Collection) sessao
				.getAttribute("colecaoTarifasSociaisRecadastradas");
		
		AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = fachada.manterTarifaSocial(imovelSessao,
						colecaoTarifaSocialHelperAtualizar,
						colecaoImoveisExcluidosTarifaSocial,
						colecaoTarifaSocialExcluida,
						colecaoTarifasSociaisRecadastradas,usuarioLogado);

		if (sessao.getAttribute("pesquisaImovel") == null) {
            registroAtendimento.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
            registroAtendimento.setDataEncerramento(new Date());
			
			//Colocado por Raphael Rossiter em 10/03/2008
			ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper = 
			fachada.obterDadosRegistroAtendimento(registroAtendimento.getId());
			
			SolicitacaoTipoEspecificacao especificacao = registroAtendimentoHelper
			.getRegistroAtendimento().getSolicitacaoTipoEspecificacao();
			
			if (especificacao.getDebitoTipo() != null){
				
				fachada.encerrarRegistroAtendimento(registroAtendimento,registroAtendimentoUnidade, 
				usuarioLogado, especificacao.getDebitoTipo().getId(), especificacao.getValorDebito(), 1, "100", false,null,false);
			}
			else{
				fachada.encerrarRegistroAtendimento(registroAtendimento,registroAtendimentoUnidade, 
				usuarioLogado, null, null, null, null, false,null,false );
			}
			
		}

		montarPaginaSucesso(httpServletRequest, "Imóvel de matrícula "
				+ imovelSessao.getId()
				+ " mantido na tarifa social com sucesso.",
				"Manter outra tarifa social",
				"exibirManterTarifaSocialAction.do?menu=sim");

		return retorno;
	}
}