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
package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoAtividade;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirRotasNaoHabilitadasAction extends GcomAction {

    /**
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

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirRotasNaoHabilitadas");

        //Carrega a instancia da fachada
        Fachada fachada = Fachada.getInstancia();

        //Carrega o objeto sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        Collection colecaoFaturamentoGrupo;

        // Grupo de faturamento (Carregar coleção)
        // [FS0001] - Verificar existência de dados
        if (sessao.getAttribute("colecaoGrupoFaturamento") == null) {

            FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo(
                    FiltroFaturamentoGrupo.DESCRICAO);

            colecaoFaturamentoGrupo = fachada.pesquisar(filtroFaturamentoGrupo,
                    FaturamentoGrupo.class.getName());

            if (colecaoFaturamentoGrupo == null
                    || colecaoFaturamentoGrupo.isEmpty()) {
                throw new ActionServletException(
                        "atencao.pesquisa.nenhum_registro_tabela", null,
                        "FATURAMENTO_GRUPO");
            } else {
                sessao.setAttribute("colecaoGrupoFaturamento",
                        colecaoFaturamentoGrupo);
            }
        } else {
            colecaoFaturamentoGrupo = (Collection) sessao
                    .getAttribute("colecaoGrupoFaturamento");
        }

        // Grupo selecionado
        String grupoFaturamentoJSP = httpServletRequest.getParameter("grupo");
        // Atividade selecionado
        String atividadeFaturamentoJSP = httpServletRequest
                .getParameter("atividade");

        FaturamentoGrupo faturamentoGrupo = obterFaturamentoGrupoSelecionado(
                grupoFaturamentoJSP, colecaoFaturamentoGrupo);

        // Lista as rotas "não habilitadas" do grupo

        // [FS0006] - Verificar existência de rotas para o grupo
        Collection colecaoRotasGrupo = fachada
                .verificarExistenciaRotaGrupo(faturamentoGrupo);

        //Obter objeto FaturamentoAtividade a partir do ID -------------------
        FiltroFaturamentoAtividade filtroFaturamentoAtividade = new FiltroFaturamentoAtividade();

        filtroFaturamentoAtividade.adicionarParametro(new ParametroSimples(
                FiltroFaturamentoAtividade.ID, atividadeFaturamentoJSP));

        filtroFaturamentoAtividade.adicionarParametro(new ParametroSimples(
                FiltroFaturamentoAtividade.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));

        Collection colecaoFaturamentoAtividade = fachada.pesquisar(
                filtroFaturamentoAtividade, FaturamentoAtividade.class
                        .getName());

        FaturamentoAtividade faturamentoAtividade = (FaturamentoAtividade) Util
                .retonarObjetoDeColecao(colecaoFaturamentoAtividade);
        //--------------------------------------------------------------------

        //[SB0002] - Verificar Situação da Atividade para a Rota
        Collection colecaoRotasSituacao = fachada
                .verificarSituacaoAtividadeRota(colecaoRotasGrupo,
                        faturamentoAtividade, faturamentoGrupo, false);

        //[FS0007] - Verificar seleção de pelo menos uma rota habilitada
        if (colecaoRotasSituacao == null || colecaoRotasSituacao.isEmpty()) {
            throw new ActionServletException(
                    "atencao.pesquisa.nenhuma.rota_nao_habilitada_grupo");
        }

        // Passa a colecao de rotas não habilitadas pela sessão (Motivo:
        // paginação)
        httpServletRequest.setAttribute("colecaoRotasNaoHabilitadas", colecaoRotasSituacao);

        return retorno;
    }

    /**
     * Retorna o objeto FaturamentoGrupo selecionado
     * 
     * @param id
     * @param colecao
     * @return
     */
    private FaturamentoGrupo obterFaturamentoGrupoSelecionado(String id,
            Collection colecao) {
        FaturamentoGrupo retorno = null;
        Iterator colecaoIterator = colecao.iterator();

        while (colecaoIterator.hasNext()) {
            retorno = (FaturamentoGrupo) colecaoIterator.next();

            if (retorno.getId().equals(new Integer(id))) {
                break;
            }
        }

        return retorno;
    }

}
