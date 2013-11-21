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

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Thiago Nascimento
 * 
 * Action que Busca os imoveis da rota para efetuar a leitura
 *
 */
public class BuscarImoveisPorRotaAction extends GcomAction {

    @Override
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        HttpSession sessao = httpServletRequest.getSession(false);

        ActionForward retorno = actionMapping.findForward("InformarLeituraRotaAction");

        Usuario usuarioLogado = this.getUsuarioLogado(httpServletRequest);

        InformarLeituraRotaActionForm form = (InformarLeituraRotaActionForm) actionForm;

        // Limpar Leituras antigas
        form.setAnormalidades(null);
        form.setLeituras(null);
        form.setDatas(null);

        FiltroLeituraAnormalidade filtro = new FiltroLeituraAnormalidade(FiltroLeituraAnormalidade.ID);
        filtro.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.INDICADOR_USO,
                                                       ConstantesSistema.SIM));
        filtro.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.INDICADOR_USO_SISTEMA,
                                                       ConstantesSistema.NAO));

        Fachada fachada = Fachada.getInstancia();

        StringBuffer faixas = new StringBuffer();

        if (form.getIdLocalidade() != null && !form.getIdLocalidade().trim().equals("")
                && form.getCodigoSetorComercial() != null && !form.getCodigoSetorComercial().trim().equals("")
                && form.getRota() != null && !form.getRota().equals("") && !Util.validarValorNaoNumerico(form.getRota())) {

            verificarAbrangenciaUsuario(httpServletRequest,
                                        usuarioLogado,
                                        Util.converterStringParaInteger(form.getIdLocalidade()));

            FiltroRota filtroRota = new FiltroRota();
            filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.EMPRESA);
            filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.FATURAMENTO_GRUPO);
            filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.SETOR_COMERCIAL);
            filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.LOCALIDADE);
            filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.LEITURA_TIPO);
            filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.LOCALIDADE_ID,
                                                               form.getIdLocalidade()));
            filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_CODIGO,
                                                               form.getCodigoSetorComercial()));
            filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.CODIGO_ROTA,
                                                               form.getRota()));

            Collection colecao = fachada.pesquisar(filtroRota, Rota.class.getName());
            if (colecao != null && !colecao.isEmpty()) {
                Rota rota = (Rota) colecao.iterator().next();

                String descricao = rota.getEmpresa().getDescricao() + " "
                        + rota.getFaturamentoGrupo().getDescricaoAbreviada() + " "
                        + rota.getSetorComercial().getLocalidade().getId() + "." + rota.getSetorComercial().getCodigo()
                        + "." + rota.getCodigo();
                form.setDescricaoRota(descricao);

                Collection<DadosMovimentacao> dados = fachada.buscarImoveisPorRota(rota,
                                                                                   rota.getFaturamentoGrupo()
                                                                                       .getAnoMesReferencia(),
                                                                                   form.getTipo().trim().equals("1"));

                if (dados != null && !dados.isEmpty()) {

                    Vector<DadosMovimentacao> v = new Vector<DadosMovimentacao>();
                    v.addAll(dados);
                    form.setDados(v);
                    form.setIndice(new Integer(1));
                    int size = dados.size();
                    if (size % 12 == 0) {
                        form.setTotal(new Integer(dados.size() / 12));
                    } else {
                        form.setTotal(new Integer((dados.size() / 12) + 1));
                    }
                    Collection<DadosMovimentacao> dados12 = new ArrayList<DadosMovimentacao>();
                    Iterator<DadosMovimentacao> it = dados.iterator();
                    char delimitador = '/';
                    char delimitador2 = ';';

                    for (int i = 0; i < 12 && it.hasNext(); i++) {
                        DadosMovimentacao dado = it.next();
                        dado.getInscricao();
                        faixas.append(dado.getFaixaLeituraEsperadaInferior());
                        faixas.append(delimitador2);
                        faixas.append(dado.getFaixaLeituraEsperadaSuperior());
                        if (i + 1 < 12 && it.hasNext()) {
                            faixas.append(delimitador);
                        }
                        dados12.add(dado);
                    }

                    sessao.setAttribute("colecaoLeituras", dados12);
                    httpServletRequest.setAttribute("qnt", "" + dados12.size());

                    Collection colecaoLeituraAnormalidade = Fachada.getInstancia()
                                                                   .pesquisar(filtro, LeituraAnormalidade.class.getName());

                    Iterator iterator = colecaoLeituraAnormalidade.iterator();
                    StringBuffer anormalidades = new StringBuffer();
                    while (iterator.hasNext()) {
                        LeituraAnormalidade l = (LeituraAnormalidade) iterator.next();
                        anormalidades.append(l.getId().toString());
                        anormalidades.append(delimitador2);
                        anormalidades.append(l.getIndicadorLeitura().toString());

                        if (iterator.hasNext()) {
                            anormalidades.append(delimitador);
                        }

                    }

                    httpServletRequest.setAttribute("anormalidadesBanco", anormalidades.toString());
                    httpServletRequest.setAttribute("faixa", faixas.toString());

                } else {
                    throw new ActionServletException("atencao.rota_sem_imovel_para_leitura",
                                                     form.getRota());
                }

            } else {
                // Rota n encontrada
                throw new ActionServletException("atencao.pesquisa.rota_inexistente");
            }

        } else {
            // Tratar se Não for Número
            httpServletRequest.setAttribute("nomeCampo", "idLocalidade");
            throw new ActionServletException("atencao.parametros.obrigatorios.nao.selecionados");
        }
        httpServletRequest.setAttribute("nomeCampo", "rota");

        return retorno;
    }

    private void verificarAbrangenciaUsuario(HttpServletRequest httpServletRequest, Usuario usuarioLogado,
            Integer idLocalidade) {

        Fachada fachada = Fachada.getInstancia();

        if (usuarioLogado.getUsuarioAbrangencia().getId().equals(UsuarioAbrangencia.LOCALIDADE)) {

            if (!usuarioLogado.getLocalidade().getId().equals(idLocalidade)) {
                throw new ActionServletException("atencao.acesso.negado.abrangencia");
            }

        } else if (usuarioLogado.getUsuarioAbrangencia().getId().equals(UsuarioAbrangencia.GERENCIA_REGIONAL)) {

            FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
            filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,
                                                                     idLocalidade));

            Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

            Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);

            if (!usuarioLogado.getGerenciaRegional().getId().equals(localidade.getGerenciaRegional().getId())) {
                throw new ActionServletException("atencao.acesso.negado.abrangencia");
            }

        } else if (usuarioLogado.getUsuarioAbrangencia().getId().equals(UsuarioAbrangencia.UNIDADE_NEGOCIO)) {

            FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
            filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,
                                                                     idLocalidade));

            Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

            Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);

            if (!usuarioLogado.getUnidadeNegocio().getId().equals(localidade.getUnidadeNegocio().getId())) {
                throw new ActionServletException("atencao.acesso.negado.abrangencia");
            }

        } else if (usuarioLogado.getUsuarioAbrangencia().getId().equals(UsuarioAbrangencia.ELO_POLO)) {

            FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
            filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,
                                                                     idLocalidade));

            Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

            Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);

            if (!usuarioLogado.getLocalidadeElo().getId().equals(localidade.getLocalidade().getId())) {
                throw new ActionServletException("atencao.acesso.negado.abrangencia");
            }

        }
    }
}
