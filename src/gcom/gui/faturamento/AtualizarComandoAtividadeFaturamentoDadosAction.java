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

import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarComandoAtividadeFaturamentoDadosAction extends GcomAction {

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
        ActionForward retorno = actionMapping.findForward("");

        //Carrega a instancia da fachada
       // Fachada fachada = Fachada.getInstancia();

        //Carrega o objeto sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        //Instância do formulário que está sendo utilizado
        InserirComandoAtividadeFaturamentoActionForm inserirComandoAtividadeFaturamentoActionForm = (InserirComandoAtividadeFaturamentoActionForm) actionForm;

        FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) sessao
                .getAttribute("faturamentoAtividadeCronograma");

        // Para auxiliar na formatação de uma data
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

        // Data corrente para comparação
        // =============================
        String dataCorrente = null;
        Date dataCorrenteGrupo = null;

        if (faturamentoAtividadeCronograma.getFaturamentoAtividade().getId()
                .equals(FaturamentoAtividade.FATURAR_GRUPO)
                && sessao.getAttribute("dataCorrente") != null) {

            dataCorrente = (String) sessao.getAttribute("dataCorrente");
            if (dataCorrente != null && !dataCorrente.equalsIgnoreCase("")) {

                try {
                    dataCorrenteGrupo = formatoData.parse(dataCorrente);
                } catch (ParseException ex) {
                    dataCorrenteGrupo = null;
                }
            }
        }
        
        
        //Data de vecimento do grupo para comparação
        // =========================================
        String dataVencimentoGrupoBase = null;
      //  Date dataVencimentoGrupoBaseObjeto = null;

        if (faturamentoAtividadeCronograma.getFaturamentoAtividade().getId()
                .equals(FaturamentoAtividade.FATURAR_GRUPO)
                && sessao.getAttribute("exibirCampoVencimentoGrupo") != null) {

        	dataVencimentoGrupoBase = (String) sessao.getAttribute("exibirCampoVencimentoGrupo");
            if (dataVencimentoGrupoBase != null && !dataVencimentoGrupoBase.equalsIgnoreCase("")) {

              /*  try {
                	dataVencimentoGrupoBaseObjeto = formatoData.parse(dataVencimentoGrupoBase);
                } catch (ParseException ex) {
                	dataVencimentoGrupoBaseObjeto = null;
                }*/
            }
        }

        
        // Data de vecimento do grupo informado pelo usuário (JSP)
        // ========================================================
        String dataVencimentoGrupoJSP = inserirComandoAtividadeFaturamentoActionForm
                .getVencimentoGrupo();

        Date dataVencimentoGrupo = null;

        if (faturamentoAtividadeCronograma.getFaturamentoAtividade().getId()
                .equals(FaturamentoAtividade.FATURAR_GRUPO)
                && sessao.getAttribute("dataCorrente") != null) {

            if (dataVencimentoGrupoJSP != null
                    && !dataVencimentoGrupoJSP.equalsIgnoreCase("")) {

                try {
                    dataVencimentoGrupo = formatoData
                            .parse(dataVencimentoGrupoJSP);

                    String mesDataVencimentoGrupo = dataVencimentoGrupoJSP
                            .substring(3, 5);
                    String anoDataVencimentoGrupo = dataVencimentoGrupoJSP
                            .substring(6, 10);

                    String mesDataVencimentoGrupoBase = dataVencimentoGrupoBase.substring(3, 5);
                    String anoDataVencimentoGrupoBase = dataVencimentoGrupoBase.substring(6, 10);

                    if (dataCorrenteGrupo.after(dataVencimentoGrupo)) {
                        throw new ActionServletException(
                                "atencao.faturamento_data_vencimento_grupo_menor",
                                null, dataCorrente);
                    } else if ((!mesDataVencimentoGrupo
                            .equalsIgnoreCase(mesDataVencimentoGrupoBase))
                            || (!anoDataVencimentoGrupo
                                    .equalsIgnoreCase(anoDataVencimentoGrupoBase))) {
                        throw new ActionServletException(
                                "atencao.faturamento_data_vencimento_mes_ano_diferente");
                    }
                } catch (ParseException ex) {
                	throw new ActionServletException(
                    "atencao.data.invalida");
                }
            }
        }

        return retorno;
    }
}
