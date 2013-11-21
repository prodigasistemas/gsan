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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.AgenciaReguladoraMotReclamacao;
import gcom.atendimentopublico.registroatendimento.AgenciaReguladoraMotRetorno;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAgenciaReguladoraMotReclamacao;
import gcom.atendimentopublico.registroatendimento.FiltroAgenciaReguladoraMotRetorno;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0538] Filtrar RAs na Agencia Reguladora
 *
 * @author Kássia Albuquerque
 * @date 02/05/2007
 */

public class ExibirFiltrarRaDadosAgenciaReguladoraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			
			ActionForward retorno = actionMapping.findForward("filtrarRaDadosAgenciaReguladora");
	
			FiltrarRaDadosAgenciaReguladoraActionForm form = (FiltrarRaDadosAgenciaReguladoraActionForm) actionForm;
	
			Fachada fachada = Fachada.getInstancia();
	
			
			if (httpServletRequest.getParameter("menu") != null) {
				
				form.setIndicadorSituacaoAgencia(ConstantesSistema.SITUACAO_AGENCIA_TODOS.toString());
				form.setIndicadorSituacaoRA(ConstantesSistema.SITUACAO_RA_AGENCIA_TODOS.toString());
			}

			
			
			// CARREGANDO MOTIVO RECLAMAÇÃO AGENCIA DA TABELA AGENCIA_REGULADORA_MOTIVO_RECLAMAÇÃO
			
			FiltroAgenciaReguladoraMotReclamacao filtroAgenciaReguladoraMotReclamacao = 
						new FiltroAgenciaReguladoraMotReclamacao();
			
			filtroAgenciaReguladoraMotReclamacao.setCampoOrderBy(FiltroAgenciaReguladoraMotReclamacao.DESCRICAO);
			
			Collection colecaoMotivoReclamacao = fachada.pesquisar(
						filtroAgenciaReguladoraMotReclamacao, AgenciaReguladoraMotReclamacao.class.getName());
			
			if (colecaoMotivoReclamacao == null || colecaoMotivoReclamacao.isEmpty() ){
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao",null,"Motivo Reclamação da Agência");
			}
			
			httpServletRequest.setAttribute("colecaoMotivoReclamacao",colecaoMotivoReclamacao);
			
			
			
			// CARREGANDO MOTIVO ENCERRAMENTO ATENDIMENTO DA TABELA ATENDIMENTO_MOTIVO_ENCERRAMENTO
			
			FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = 
						new FiltroAtendimentoMotivoEncerramento();
			
			filtroAtendimentoMotivoEncerramento.setCampoOrderBy(FiltroAtendimentoMotivoEncerramento.DESCRICAO);
			
			Collection colecaoMotivoEncerramentoAtendimento = fachada.pesquisar(
					filtroAtendimentoMotivoEncerramento,AtendimentoMotivoEncerramento.class.getName());
			
			if (colecaoMotivoEncerramentoAtendimento == null || colecaoMotivoEncerramentoAtendimento.isEmpty() ){
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao",null,"Motivo Encerramento do Atendimento");
			}
			
			httpServletRequest.setAttribute("colecaoMotivoEncerramentoAtendimento",colecaoMotivoEncerramentoAtendimento);
			
			
			
			// CARREGANDO MOTIVO ENCERRAMENTO ATENDIMENTO DA TABELA AGENCIA_REGULADORA_MOTIVO_RETORNO
			
			FiltroAgenciaReguladoraMotRetorno filtroAgenciaReguladoraMotRetorno = 
						new FiltroAgenciaReguladoraMotRetorno();
			
			filtroAgenciaReguladoraMotRetorno.setCampoOrderBy(FiltroAgenciaReguladoraMotRetorno.DESCRICAO);
			
			Collection colecaoMotivoRetornoAgencia = fachada.pesquisar(
					filtroAgenciaReguladoraMotRetorno, AgenciaReguladoraMotRetorno.class.getName());
			
			if (colecaoMotivoRetornoAgencia == null || colecaoMotivoRetornoAgencia.isEmpty() ){
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao",null,"Motivo do Retorno para Agência");
			}
			
			httpServletRequest.setAttribute("colecaoMotivoRetornoAgencia",colecaoMotivoRetornoAgencia);
			
			
			return retorno;
		}
}
