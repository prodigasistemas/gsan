/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento This
 * file is part of GSAN, an integrated service management system for Sanitation GSAN is free
 * software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 2 of the License. GSAN is
 * distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details. You should have received a copy of the GNU General Public
 * License along with this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place - Suite 330, Boston, MA 02111-1307, USA
 */

/*
 * GSAN - Sistema Integrado de Gestão de Serviços de Saneamento Copyright (C) <2007> Adriano Britto
 * Siqueira Alexandre Santos Cabral Ana Carolina Alves Breda Ana Maria Andrade Cavalcante Aryed Lins
 * de Araújo Bruno Leonardo Rodrigues Barros Carlos Elmano Rodrigues Ferreira Cláudio de Andrade
 * Lira Denys Guimarães Guenes Tavares Eduardo Breckenfeld da Rosa Borges Fabíola Gomes de Araújo
 * Flávio Leonardo Cavalcanti Cordeiro Francisco do Nascimento Júnior Homero Sampaio Cavalcanti Ivan
 * Sérgio da Silva Júnior José Edmar de Siqueira José Thiago Tenório Lopes Kássia Regina Silvestre
 * de Albuquerque Leonardo Luiz Vieira da Silva Magno Jean Gouveia Silveira Márcio Roberto Batista
 * da Silva Maria de Fátima Sampaio Leite Micaela Maria Coelho de Araújo Nelson Mendonça de Carvalho
 * Newton Morais e Silva Pedro Alexandre Santos da Silva Filho Rafael Corrêa Lima e Silva Rafael
 * Francisco Pinto Rafael Koury Monteiro Rafael Palermo de Araújo Raphael Veras Rossiter Roberto
 * Sobreira Barbalho Rodrigo Avellar Silveira Rosana Carvalho Barbosa Sávio Luiz de Andrade
 * Cavalcante Tai Mu Shih Thiago Augusto Souza do Nascimento Tiago Moreno Rodrigues Vivianne Barbosa
 * Sousa Este programa é software livre; você pode redistribuí-lo e/ou modificá-lo sob os termos de
 * Licença Pública Geral GNU, conforme publicada pela Free Software Foundation; versão 2 da Licença.
 * Este programa é distribuído na expectativa de ser útil, mas SEM QUALQUER GARANTIA; sem mesmo a
 * garantia implícita de COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM PARTICULAR.
 * Consulte a Licença Pública Geral GNU para obter mais detalhes. Você deve ter recebido uma cópia
 * da Licença Pública Geral GNU junto com este programa; se não, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 */
package gcom.gui.micromedicao;

import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRateioTipo;
import gcom.micromedicao.RateioTipo;
import gcom.micromedicao.hidrometro.FiltroHidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Atualizar Tipo de Rateio
 * 
 * @author Rafael Santos, Magno Gouveia
 * @since 12/01/2006, 20/08/2011
 */
public class EstabelecerVinculoPopupAction extends GcomAction {

    private Fachada fachada = Fachada.getInstancia();

    @Override
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucessoPopup");

        HttpSession sessao = httpServletRequest.getSession(false);

        EstabelecerVinculoPopupActionForm form = (EstabelecerVinculoPopupActionForm) actionForm;

        // rateio agua
        String tipoRateioLigacaoAgua = form.getRateioTipoAgua();
        // rateio poco
        String tipoRateioLigacaoPoco = form.getRateioTipoPoco();

        Collection imoveisASerVinculados = null;
        int contador = 0;
        Imovel imovel = null;
        if (sessao.getAttribute("imovelCondominio") != null) {
            imovel = (Imovel) sessao.getAttribute("imovelCondominio");

            HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoAgua = null;
            HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoPoco = null;

            // rateio agua
            if (tipoRateioLigacaoAgua != null && !tipoRateioLigacaoAgua.equals("")) {

                FiltroRateioTipo filtroRateioTipo = new FiltroRateioTipo();

                filtroRateioTipo.adicionarParametro(new ParametroSimples(FiltroRateioTipo.ID,
                                                                         tipoRateioLigacaoAgua));
                // Rateio Tipo Agua
                RateioTipo rateioTipo = (RateioTipo) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroRateioTipo, RateioTipo.class.getName()));

                FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();
                filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroSimples(FiltroHidrometroInstalacaoHistorico.LIGACAO_AGUA_ID,
                                                                                            imovel.getId()));
                filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroNulo(FiltroHidrometroInstalacaoHistorico.DATA_RETIRADA));

                Collection colecaoHidrometroInstalacaoHistorico = fachada.pesquisar(filtroHidrometroInstalacaoHistorico, HidrometroInstalacaoHistorico.class.getName());

                if (colecaoHidrometroInstalacaoHistorico != null && !colecaoHidrometroInstalacaoHistorico.isEmpty()) {
                    hidrometroInstalacaoHistoricoAgua = (HidrometroInstalacaoHistorico) Util.retonarObjetoDeColecao(colecaoHidrometroInstalacaoHistorico);
                    hidrometroInstalacaoHistoricoAgua.setRateioTipo(rateioTipo);
                }
            }

            imovel.setIndicadorImovelCondominio(Imovel.IMOVEL_CONDOMINIO);

            // rateio poço
            if (tipoRateioLigacaoPoco != null && !tipoRateioLigacaoPoco.equals("")) {

                FiltroRateioTipo filtroRateioTipo = new FiltroRateioTipo();

                filtroRateioTipo.adicionarParametro(new ParametroSimples(FiltroRateioTipo.ID,
                                                                         tipoRateioLigacaoPoco));

                // Rateio Tipo Poco
                RateioTipo rateioTipo = (RateioTipo) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroRateioTipo, RateioTipo.class.getName()));

                FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();
                filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroSimples(FiltroHidrometroInstalacaoHistorico.IMOVEL_ID,
                                                                                            imovel.getId()));
                filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroNulo(FiltroHidrometroInstalacaoHistorico.DATA_RETIRADA));

                Collection colecaoHidrometroInstalacaoHistorico = fachada.pesquisar(filtroHidrometroInstalacaoHistorico, HidrometroInstalacaoHistorico.class.getName());

                if (colecaoHidrometroInstalacaoHistorico != null && !colecaoHidrometroInstalacaoHistorico.isEmpty()) {
                    hidrometroInstalacaoHistoricoPoco = (HidrometroInstalacaoHistorico) Util.retonarObjetoDeColecao(colecaoHidrometroInstalacaoHistorico);
                    hidrometroInstalacaoHistoricoPoco.setRateioTipo(rateioTipo);
                }

            }

            if (sessao.getAttribute("colecaoImoveisASerVinculados") != null) {
                imoveisASerVinculados = (Collection) sessao.getAttribute("colecaoImoveisASerVinculados");

                if (imoveisASerVinculados != null && !imoveisASerVinculados.isEmpty()) {
                    Iterator iter = imoveisASerVinculados.iterator();

                    while (iter.hasNext()) {

                        Imovel imovelASerVinculado = (Imovel) iter.next();
                        if (imovelASerVinculado.getImovelCondominio() == null) {
                            contador++;
                        }

                        imovelASerVinculado.setImovelCondominio(imovel);

                        /*
                         * Caso o tipo de rateio seja alterado de RATEIO POR AREA COMUM para RATEIO
                         * POR IMÓVEL Alterar o indicadorImovelAreaComum para NAO (2)
                         */
                        if (!(hidrometroInstalacaoHistoricoAgua.getRateioTipo() != null
                                && hidrometroInstalacaoHistoricoAgua.getRateioTipo()
                                                                    .getId()
                                                                    .equals(RateioTipo.RATEIO_AREA_COMUM) && imovelASerVinculado.getIndicadorImovelAreaComum()
                                                                                                                                .equals(ConstantesSistema.SIM))) {
                            imovelASerVinculado.setIndicadorImovelAreaComum(ConstantesSistema.NAO);
                        }
                    }

                } else {
                    throw new ActionServletException("atencao.imovel.adicionar_vinculos");
                }
            } else {
                throw new ActionServletException("atencao.imovel.adicionar_vinculos");
            }

            Collection imoveisASerDesvinculados = null;
            if (sessao.getAttribute("colecaoImoveisASerDesvinculados") != null) {
                imoveisASerDesvinculados = (Collection) sessao.getAttribute("colecaoImoveisASerDesvinculados");

                if (imoveisASerDesvinculados != null && !imoveisASerDesvinculados.isEmpty()) {
                    Iterator iter = imoveisASerDesvinculados.iterator();

                    while (iter.hasNext()) {
                        Imovel imovelASerDesvinculado = (Imovel) iter.next();
                        imovelASerDesvinculado.setImovelCondominio(null);
                        imovelASerDesvinculado.setIndicadorImovelAreaComum(ConstantesSistema.NAO);
                    }
                }
            }

            /**
             * alterado por pedro alexandre dia 20/11/2006 Recupera o usuário logado para passar no
             * metódo de estabelecer vínculo para verificar se o usuário tem abrangência para
             * estabelecer o vínculo informado.
             */
            Usuario usuarioLogado = (Usuario) sessao.getAttribute(Usuario.USUARIO_LOGADO);

            // Atualizar Tipo Rateio
            fachada.estabelecerVinculo(imovel, imoveisASerVinculados, imoveisASerDesvinculados, hidrometroInstalacaoHistoricoAgua, hidrometroInstalacaoHistoricoPoco, usuarioLogado);
        }

        httpServletRequest.setAttribute("fechar", "true");

        // liberar da sessao
        if (sessao.getAttribute("imovelCondominio") != null) {
            sessao.removeAttribute("imovelCondominio");
        }
        if (sessao.getAttribute("colecaoRateioTipo") != null) {
            sessao.removeAttribute("colecaoRateioTipo");
        }
        if (sessao.getAttribute("colecaoImoveisASerVinculados") != null) {
            sessao.removeAttribute("colecaoImoveisASerVinculados");
        }

        if (contador != 0) {
            // Monta a página de sucesso
            if (retorno.getName().equalsIgnoreCase("telaSucessoPopup")) {
                montarPaginaSucesso(httpServletRequest, contador + " imóveis vinculados ao imóvel condomínio "
                        + imovel.getId() + " com sucesso.", "", "");
            }

        } else {
            if (retorno.getName().equalsIgnoreCase("telaSucessoPopup")) {
                montarPaginaSucesso(httpServletRequest, "Imóvel(is) já vinculado(s) ao imóvel condomínio " + imovel.getId()
                        + ".", "", "");
            }
        }

        return retorno;
    }
}