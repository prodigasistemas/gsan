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
package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.FiltroHidrometroMotivoMovimentacao;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroMotivoMovimentacao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirConfirmarMovimentarHidrometroAction extends GcomAction {
    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Obtém o action form
        ConfirmarMovimentarHidrometroActionForm confirmarMovimentarHidrometroActionForm = (ConfirmarMovimentarHidrometroActionForm) actionForm;

        //Define ação de retorno
        ActionForward retorno = actionMapping
                .findForward("confirmarMovimentarHidrometro");

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //Obtém a facahda
        Fachada fachada = Fachada.getInstancia();

        Collection colecaoHidrometroSelecionado = (Collection) sessao
                .getAttribute("colecaoHidrometroSelecionado");
        
        httpServletRequest.setAttribute("qtdeHidrometrosMovimentados",colecaoHidrometroSelecionado.size());

        //Obtém a descrição do local de armazenagem
        Hidrometro hidrometro = (Hidrometro) Util
                .retonarObjetoDeColecao(colecaoHidrometroSelecionado);
        String codigoDescricaoLocalArmazenagemAtual = hidrometro
                .getHidrometroLocalArmazenagem().getId().toString();

        codigoDescricaoLocalArmazenagemAtual = codigoDescricaoLocalArmazenagemAtual
                + " - "
                + hidrometro.getHidrometroLocalArmazenagem().getDescricao();

        //Obtém o objetoCosulta vindo na sessão
        String objetoConsulta = (String) httpServletRequest
                .getParameter("objetoConsulta");
        
        httpServletRequest.setAttribute("nomeCampo", "idLocalArmazenagemDestino");
        
        //Verifica se o objeto é diferente de nulo
        if (objetoConsulta != null
                && !objetoConsulta.trim().equalsIgnoreCase("")
                && (Integer.parseInt(objetoConsulta)) == 1) {

            //Filtro para obter o local de armazenagem ativo de id informado
            FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem = new FiltroHidrometroLocalArmazenagem();

            filtroHidrometroLocalArmazenagem
                    .adicionarParametro(new ParametroSimples(
                            FiltroHidrometroLocalArmazenagem.ID, new Integer(
                                    confirmarMovimentarHidrometroActionForm
                                            .getIdLocalArmazenagemDestino()),
                            ParametroSimples.CONECTOR_AND));
            filtroHidrometroLocalArmazenagem
                    .adicionarParametro(new ParametroSimples(
                            FiltroHidrometroLocalArmazenagem.INDICADOR_USO,
                            ConstantesSistema.INDICADOR_USO_ATIVO));

            //Pesquisa de acordo com os parâmetros informados no filtro
            Collection colecaoHidrometroLocalArmazenagem = fachada.pesquisar(
                    filtroHidrometroLocalArmazenagem,
                    HidrometroLocalArmazenagem.class.getName());

            //Verifica se a pesquisa retornou algum objeto para a coleção
            if (colecaoHidrometroLocalArmazenagem != null
                    && !colecaoHidrometroLocalArmazenagem.isEmpty()) {

                //Obtém o objeto da coleção pesquisada
                HidrometroLocalArmazenagem hidrometroLocalArmazenagem = (HidrometroLocalArmazenagem) Util
                        .retonarObjetoDeColecao(colecaoHidrometroLocalArmazenagem);

                //Exibe o código e a descrição pesquisa na página
                httpServletRequest.setAttribute("corLocalArmazenagem", "valor");
                confirmarMovimentarHidrometroActionForm
                        .setIdLocalArmazenagemDestino(hidrometroLocalArmazenagem
                                .getId().toString());
                confirmarMovimentarHidrometroActionForm
                        .setLocalArmazenagemDescricaoDestino(hidrometroLocalArmazenagem
                                .getDescricao());
                httpServletRequest.setAttribute("nomeCampo", "dataMovimentacao");

            } else {

                //Exibe mensagem de código inexiste e limpa o campo de código
                httpServletRequest.setAttribute("corLocalArmazenagem",
                        "exception");
                confirmarMovimentarHidrometroActionForm
                        .setIdLocalArmazenagemDestino("");
                confirmarMovimentarHidrometroActionForm
                        .setLocalArmazenagemDescricaoDestino("LOCAL DE ARMAZENAGEM INEXISTENTE");
            }

        }
        //Criação e definição do filto de hidrômetro motivo da movimentação
        FiltroHidrometroMotivoMovimentacao filtroHidrometroMotivoMovimentacao = new FiltroHidrometroMotivoMovimentacao();
        
        filtroHidrometroMotivoMovimentacao
                .adicionarParametro(new ParametroSimples(
                        FiltroHidrometroMotivoMovimentacao.INDICADOR_USO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroHidrometroMotivoMovimentacao
                .setCampoOrderBy(FiltroHidrometroMotivoMovimentacao.DESCRICAO);

        //Obtém os motivos da movimentação
        Collection colecaoHidrometroMotivoMovimentacao = fachada.pesquisar(
                filtroHidrometroMotivoMovimentacao,
                HidrometroMotivoMovimentacao.class.getName());

        //Envia objeto no request
        httpServletRequest.setAttribute("colecaoHidrometroMotivoMovimentacao",
                colecaoHidrometroMotivoMovimentacao);
        //Envia objeto pela sessão
        sessao.setAttribute("codigoDescricaoLocalArmazenagemAtual",
                codigoDescricaoLocalArmazenagemAtual);
        
        //Data Corrente
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dataCorrente = new GregorianCalendar();
        
        httpServletRequest.setAttribute("dataMovimentacao", formatoData
        		.format(dataCorrente.getTime()));
        httpServletRequest.setAttribute("dataAtual", formatoData
        		.format(dataCorrente.getTime()));

        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        Calendar horaCorrente = new GregorianCalendar();
        
        httpServletRequest.setAttribute("horaMovimentacao", formatoHora
        		.format(horaCorrente.getTime()));
        return retorno;
    }
}