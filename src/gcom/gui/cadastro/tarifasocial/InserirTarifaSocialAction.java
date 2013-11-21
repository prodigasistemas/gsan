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
import gcom.cadastro.cliente.ClienteImovel;
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

public class InserirTarifaSocialAction extends GcomAction {
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Instancia da Fachada
        Fachada fachada = Fachada.getInstancia();

        //Pega uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);
        
        RegistroAtendimento registroAtendimento = (RegistroAtendimento) sessao.getAttribute("ra");
        
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
        AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
        atendimentoMotivoEncerramento.setId(15);
        
        registroAtendimento.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
        registroAtendimento.setDataEncerramento(new Date());
        
        RegistroAtendimentoUnidade registroAtendimentoUnidade = new RegistroAtendimentoUnidade();
        registroAtendimentoUnidade.setRegistroAtendimento(registroAtendimento);
        registroAtendimentoUnidade.setUnidadeOrganizacional(usuarioLogado.getUnidadeOrganizacional());
        registroAtendimentoUnidade.setUsuario(usuarioLogado);
        AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
        atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
        registroAtendimentoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
        registroAtendimentoUnidade.setUltimaAlteracao(new Date());

        //Para apenas uma economia
        
        Collection colecaoTarifaSocialDadoEconomia = null;
        
        if (sessao
                .getAttribute("colecaoDadosTarifaSocial") != null) {
        	colecaoTarifaSocialDadoEconomia = (Collection) sessao
                .getAttribute("colecaoDadosTarifaSocial");
        } else {
        	colecaoTarifaSocialDadoEconomia = (Collection) sessao
            .getAttribute("colecaoTarifaSocialDadoEconomia");
        }
        
        ClienteImovel clienteImovel = (ClienteImovel) sessao
                .getAttribute("clienteImovel");

        //Para mais de uma economia
        Collection colecaoClienteImovelEconomia = (Collection) sessao
                .getAttribute("colecaoClienteImovelEconomia");

        //Imóvel que está sendo trabalhado
        Imovel imovelSessao = (Imovel) sessao.getAttribute("imovelTarifa");
        
        Integer idTarifaSocialDadoEconomiaExcluida = null; 
        	
        if (sessao.getAttribute("idTarifaSocialDadoEconomia") != null) {
        	idTarifaSocialDadoEconomiaExcluida = new Integer((String) sessao.getAttribute("idTarifaSocialDadoEconomia"));
        }
        
        Collection colecaoTarifaSocialRecadastrar = (Collection) sessao
			.getAttribute("colecaoTarifaSocialDadoEconomia");
        
        Imovel imovelAtualizar = (Imovel) sessao.getAttribute("imovelAtualizado");
        
        Collection colecaoImovelEconomiaAtualizar = (Collection) sessao.getAttribute("colecaoImovelEconomiaAtualizados");
        
        fachada.inserirTarifaSocial(imovelSessao, clienteImovel,
				registroAtendimento, registroAtendimentoUnidade, usuarioLogado,
				idTarifaSocialDadoEconomiaExcluida,
				colecaoTarifaSocialDadoEconomia, colecaoClienteImovelEconomia,
				colecaoTarifaSocialRecadastrar, imovelAtualizar,
				colecaoImovelEconomiaAtualizar);
        
        montarPaginaSucesso(httpServletRequest, "Imóvel de matrícula " + imovelSessao.getId()
                + " incluído na tarifa social com sucesso.", "Inserir outra tarifa social",
                "exibirInserirTarifaSocialAction.do?menu=sim");
        
        
        
        return retorno;
    }
}