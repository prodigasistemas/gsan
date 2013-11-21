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
package gcom.gui.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarLigacaoAguaSituacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		AtualizarLigacaoAguaSituacaoActionForm atualizarLigacaoAguaSituacaoActionForm = (AtualizarLigacaoAguaSituacaoActionForm) actionForm;

		LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) sessao.getAttribute("atualizarLigacaoAguaSituacao");
		
		ligacaoAguaSituacao.setDescricao(atualizarLigacaoAguaSituacaoActionForm.getDescricao());
		ligacaoAguaSituacao.setDescricaoAbreviado(atualizarLigacaoAguaSituacaoActionForm.getDescricaoAbreviada());
		ligacaoAguaSituacao.setIndicadorFaturamentoSituacao(new Short (atualizarLigacaoAguaSituacaoActionForm.getIndicadorFaturamentoSituacao()));
		ligacaoAguaSituacao.setIndicadorExistenciaLigacao(new Short (atualizarLigacaoAguaSituacaoActionForm.getIndicadorExistenciaLigacao()));
		ligacaoAguaSituacao.setIndicadorExistenciaRede(new Short (atualizarLigacaoAguaSituacaoActionForm.getIndicadorExistenciaRede()));
		ligacaoAguaSituacao.setConsumoMinimoFaturamento(new Integer (atualizarLigacaoAguaSituacaoActionForm.getConsumoMinimoFaturamento()));
		ligacaoAguaSituacao.setIndicadorUso(new Short (atualizarLigacaoAguaSituacaoActionForm.getIndicadorUso()));
		ligacaoAguaSituacao.setIndicadorAbastecimento(new Short (atualizarLigacaoAguaSituacaoActionForm.getIndicadorAbastecimento()));
		
				
        String descricaoLigacao = atualizarLigacaoAguaSituacaoActionForm
        .getDescricao();
        String descricaoAbreviadaLigacao = atualizarLigacaoAguaSituacaoActionForm
        .getDescricaoAbreviada();    
        String indicadorFaturamento = atualizarLigacaoAguaSituacaoActionForm.getIndicadorFaturamentoSituacao();
        String indicadorExistenciaLigacao = atualizarLigacaoAguaSituacaoActionForm.getIndicadorExistenciaLigacao();
        String indicadorExistenciaRede = atualizarLigacaoAguaSituacaoActionForm.getIndicadorExistenciaRede();
        String consumoMinimo = atualizarLigacaoAguaSituacaoActionForm.getConsumoMinimoFaturamento();
        String indicadordeUso = atualizarLigacaoAguaSituacaoActionForm.getIndicadorUso();
        String indicadordeAbastecimento = atualizarLigacaoAguaSituacaoActionForm.getIndicadorAbastecimento();
		
        ligacaoAguaSituacao.setDescricao(descricaoLigacao);
        ligacaoAguaSituacao.setDescricaoAbreviado(descricaoAbreviadaLigacao);
        ligacaoAguaSituacao.setIndicadorFaturamentoSituacao( new Short(indicadorFaturamento));
        ligacaoAguaSituacao.setIndicadorExistenciaLigacao( new Short(indicadorExistenciaLigacao));
        ligacaoAguaSituacao.setIndicadorExistenciaRede( new Short(indicadorExistenciaRede));
        ligacaoAguaSituacao.setConsumoMinimoFaturamento( new Integer(consumoMinimo));
        ligacaoAguaSituacao.setUltimaAlteracao( new Date() );	
        ligacaoAguaSituacao.setIndicadorUso( new Short(indicadordeUso));
        ligacaoAguaSituacao.setIndicadorAbastecimento( new Short(indicadordeAbastecimento));
		
		fachada.atualizar(ligacaoAguaSituacao);

		montarPaginaSucesso(httpServletRequest, "Situação de Ligação de Água "
				+ atualizarLigacaoAguaSituacaoActionForm.getDescricao() + " atualizada com sucesso.",
				"Realizar outra Manutenção de Atividade ",
				"exibirFiltrarLigacaoAguaSituacaoAction.do?menu=sim");        
        
		return retorno;
	}
}
