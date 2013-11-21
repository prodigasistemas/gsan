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
package gcom.gui.micromedicao;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRateioTipo;
import gcom.micromedicao.RateioTipo;
import gcom.micromedicao.hidrometro.FiltroHidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Atualizar Tipo de Rateio
 * 
 * @author Rafael Santos
 * @since 11/01/2006
 */
public class ExibirAtualizarTipoRateioPopupAction extends GcomAction {

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    @Override
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("atualizarTipoRateioPopup");

        Fachada fachada = Fachada.getInstancia();

        AtualizarTipoRateioPopupActionForm form = (AtualizarTipoRateioPopupActionForm) actionForm;

        if (httpServletRequest.getParameter("limpar") != null
                && httpServletRequest.getParameter("limpar").equalsIgnoreCase("ok")) {
            form.setRateioTipoAgua(null);
            form.setRateioTipoPoco(null);
            form.setBotao("");
            form.setMatriculaImovelAreaComum("");
            form.setInscricaoImovelAreaComum("");
            form.setMatriculaImovelAreaComumAtual("");
        }

        if (httpServletRequest.getParameter("pesquisarInscricao") != null
                && ((String) httpServletRequest.getParameter("pesquisarInscricao")).equals("sim")
                && form.getMatriculaImovelAreaComum() != null && Integer.valueOf(form.getMatriculaImovelAreaComum()) > 0) {

            this.pesquisarInscricaoImovel(new Integer(form.getMatriculaImovelAreaComum()), form, httpServletRequest);
            form.setBotao("Visualizar");
        } else {

            form.setMatriculaImovelAreaComum("");
            form.setInscricaoImovelAreaComum("");

            FiltroRateioTipo filtroRateioTipo = new FiltroRateioTipo();
            filtroRateioTipo.setCampoOrderBy(FiltroRateioTipo.DESCRICAO);
            filtroRateioTipo.adicionarParametro(new ParametroSimples(FiltroRateioTipo.INDICADOR_USO,
                                                                     ConstantesSistema.INDICADOR_USO_ATIVO));

            Collection<RateioTipo> colecaoRateioTipo = fachada.pesquisar(filtroRateioTipo, RateioTipo.class.getName());

            HttpSession sessao = httpServletRequest.getSession(false);

            sessao.setAttribute("colecaoRateioTipo", colecaoRateioTipo);

            String matriculaImovel = "";
            if (httpServletRequest.getParameter("MatriculaImovel") != null) {
                sessao.setAttribute("matriculaImovel", httpServletRequest.getParameter("MatriculaImovel"));
                matriculaImovel = (String) httpServletRequest.getParameter("MatriculaImovel");
            } else {
                matriculaImovel = (String) sessao.getAttribute("matriculaImovel");
            }

            FiltroImovel filtroImovel = new FiltroImovel();
            filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
                                                                 matriculaImovel));

            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua.hidrometroInstalacaoHistorico.rateioTipo");
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico.rateioTipo");
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.rota");

            Collection<Imovel> imovelPesquisado = fachada.pesquisar(filtroImovel, Imovel.class.getName());

            Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(imovelPesquisado);

            // ligacao agua
            FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();
            filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroSimples(FiltroHidrometroInstalacaoHistorico.LIGACAO_AGUA_ID,
                                                                                        matriculaImovel));
            filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroNulo(FiltroHidrometroInstalacaoHistorico.DATA_RETIRADA));

            Collection colecaoHidrometroInstalacaoHistoricoAgua = fachada.pesquisar(filtroHidrometroInstalacaoHistorico, HidrometroInstalacaoHistorico.class.getName());

            if (colecaoHidrometroInstalacaoHistoricoAgua != null && !colecaoHidrometroInstalacaoHistoricoAgua.isEmpty()) {

                RateioTipo rateioTipo = ((HidrometroInstalacaoHistorico) Util.retonarObjetoDeColecao(colecaoHidrometroInstalacaoHistoricoAgua)).getRateioTipo();
                if (rateioTipo != null) {
                    form.setRateioTipoAgua(rateioTipo.getId().toString());
                } else {
                    form.setRateioTipoAgua("");
                }

                form.setBotao("Visualizar");
            }

            // poço
            FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistoricoPoco = new FiltroHidrometroInstalacaoHistorico();
            filtroHidrometroInstalacaoHistoricoPoco.adicionarParametro(new ParametroSimples(FiltroHidrometroInstalacaoHistorico.IMOVEL_ID,
                                                                                            matriculaImovel));

            Collection colecaoHidrometroInstalacaoHistoricoPoco = fachada.pesquisar(filtroHidrometroInstalacaoHistoricoPoco, HidrometroInstalacaoHistorico.class.getName());

            if (colecaoHidrometroInstalacaoHistoricoPoco != null && !colecaoHidrometroInstalacaoHistoricoPoco.isEmpty()) {

                RateioTipo rateioTipo = ((HidrometroInstalacaoHistorico) colecaoHidrometroInstalacaoHistoricoPoco.iterator()
                                                                                                                 .next()).getRateioTipo();
                if (rateioTipo != null) {
                    form.setRateioTipoPoco(rateioTipo.getId().toString());
                } else {
                    form.setRateioTipoPoco("");
                }
                form.setBotao("Visualizar");
            }

            // [FS0009] - Verificar se o imóvel é um condomínio
            if (imovel.getIndicadorImovelCondominio() == null
                    || imovel.getIndicadorImovelCondominio().equals(ConstantesSistema.NAO)) {
                throw new ActionServletException("atencao.imovel.nao_condominio");
            }

            /*
             * Magno Gouveia [SB0001] Atualizar tipo de rateio
             */
            form.setMatriculaImovelAreaComumAtual("");

            /*
             * Caso o tipo da rateio do imóvel condomínio da ligação de água corresponda a 'RATEIO
             * POR ÁREA COMUM' o sistema pesquisa na tabela cadastro.imovel a matrícula vinculada ao
             * imóvel condomínio que tenha o indicador(imov_icimovelareacomum = 1) e exibe a ma-
             * trícula ao lado da descrição do tipo de rateio e habilita o botão 'ATUALIZAR IMOVEL
             * AREA COMUM', Caso Contrário mover nulo para a matrícula ao lado da descrição do tipo
             * de rateio e desabilitar o botão ‘ATUALIZAR IMOVEL AREA COMUM’
             */
            if (!Util.isVazioOrNulo(colecaoHidrometroInstalacaoHistoricoAgua)) {

                HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = (HidrometroInstalacaoHistorico) Util.retonarObjetoDeColecao(colecaoHidrometroInstalacaoHistoricoAgua);

                if (hidrometroInstalacaoHistorico.getRateioTipo() != null
                        && hidrometroInstalacaoHistorico.getRateioTipo().getId().equals(RateioTipo.RATEIO_AREA_COMUM)) {

                    Integer idImovelAreaComum = (Integer) fachada.pesquisarImovelAreaComum(imovel.getId());

                    if (idImovelAreaComum != null) {
                        form.setMatriculaImovelAreaComumAtual(idImovelAreaComum.toString());
                    }
                }
            }

            sessao.setAttribute("imovelVinculado", imovel);

        }

        if ((httpServletRequest.getParameter("tipoConsulta") != null && !httpServletRequest.getParameter("tipoConsulta")
                                                                                           .equals(""))
                && (httpServletRequest.getParameter("tipoConsulta").equals("imovel"))) {
            form.setMatriculaImovelAreaComum(httpServletRequest.getParameter("idCampoEnviarDados"));
            form.setInscricaoImovelAreaComum(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
        }

        return retorno;
    }

    private void pesquisarInscricaoImovel(Integer idImovel, AtualizarTipoRateioPopupActionForm form,
            HttpServletRequest httpServletRequest) {

        Fachada fachada = Fachada.getInstancia();

        String inscricaoImovel = fachada.pesquisarInscricaoImovel(idImovel);

        // Verificar existêncioa da matrícula do imóvel
        if (inscricaoImovel == null || inscricaoImovel.equals("")) {
            form.setMatriculaImovelAreaComum("");
            form.setInscricaoImovelAreaComum("MATRÍCULA INEXISTENTE");
            httpServletRequest.setAttribute("nomeCampo", "matriculaImovelAreaComum");
            httpServletRequest.setAttribute("idImovelNaoEncontrado", "exception");
        } else {
            form.setInscricaoImovelAreaComum(inscricaoImovel);
        }

    }
}