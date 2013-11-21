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

import gcom.batch.FiltroProcessoIniciado;
import gcom.batch.Processo;
import gcom.batch.ProcessoIniciado;
import gcom.batch.ProcessoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.ArquivoTextoRoteiroEmpresa;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * [UC0936] Informar Leitura por Rota
 * </p>
 * 
 * @author Thiago Nascimento
 */
public class InformarLeituraRotaAction extends GcomAction {

    @Override
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        HttpSession sessao = httpServletRequest.getSession(false);

        ActionForward retorno = actionMapping.findForward("InformarLeituraRotaAction");

        InformarLeituraRotaActionForm form = (InformarLeituraRotaActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();

        FiltroLeituraAnormalidade filtro = new FiltroLeituraAnormalidade(FiltroLeituraAnormalidade.ID);
        filtro.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.INDICADOR_USO,
                                                       ConstantesSistema.SIM));
        filtro.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.INDICADOR_USO_SISTEMA,
                                                       ConstantesSistema.NAO));

        StringBuffer faixas = new StringBuffer();

        int indice = form.getIndice().intValue();

        char delimitador = '/';
        char delimitador2 = ';';

        try {

            String temPreenchido = httpServletRequest.getParameter("temPreenchido");

            if (!temPreenchido.trim().equals("0")) {

                FiltroRota filtroRota = new FiltroRota();

                filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.LOCALIDADE_ID,
                                                                   form.getIdLocalidade()));
                filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_CODIGO,
                                                                   form.getCodigoSetorComercial()));
                filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.CODIGO_ROTA,
                                                                   form.getRota()));

                Collection colecao = fachada.pesquisar(filtroRota, Rota.class.getName());

                Rota rota = null;

                if (!Util.isVazioOrNulo(colecao)) {
                    rota = (Rota) Util.retonarObjetoDeColecao(colecao);
                }

                Collection<Integer> processoSituacao = new ArrayList<Integer>();
                processoSituacao.add(ProcessoSituacao.EM_ESPERA);
                processoSituacao.add(ProcessoSituacao.EM_PROCESSAMENTO);

                FiltroProcessoIniciado filtroProcessoIniciado = new FiltroProcessoIniciado();
                filtroProcessoIniciado.adicionarParametro(new ParametroSimples(FiltroProcessoIniciado.ID_PROCESSO,
                                                                               Processo.FATURAR_GRUPO_FATURAMENTO));
                filtroProcessoIniciado.adicionarParametro(new ParametroSimples(FiltroProcessoIniciado.CODIGO_GRUPO,
                                                                               rota.getFaturamentoGrupo().getId()));
                filtroProcessoIniciado.adicionarParametro(new ParametroSimplesIn(FiltroProcessoIniciado.PROCESSO_SITUACAO_ID,
                                                                                 processoSituacao));

                Collection colecaoProcessos = fachada.pesquisar(filtroProcessoIniciado, ProcessoIniciado.class.getName());

                if (!Util.isVazioOrNulo(colecaoProcessos)) {
                    throw new ActionServletException("atencao.processo_faturamento_em_execucao");
                }

                Vector<DadosMovimentacao> dados = new Vector<DadosMovimentacao>();
                int lengthLeituras = form.getLeituras().length;

                String[] leituras = form.getLeituras();
                String[] anormalidades = form.getAnormalidades();
                String[] datas = form.getDatas();

                /*
                 * Auxiliar criada para remover 1 dos arrays de leituras,
                 * anormalidades e datas quando existir um dado com msg de
                 * supressao ou hidrometro retirado.
                 */
                int auxiliar = 0;

                for (int i = (indice - 1) * 12; i < (indice - 1) * 12 + lengthLeituras; i++) {
                    DadosMovimentacao dado = form.getDados().get(i);
                    int aux = i % 12;

                    if (dado.getMsgImovelSuprimidoOuHidrometroRetirado() == null
                            || dado.getMsgImovelSuprimidoOuHidrometroRetirado().equals("")) {

                        if (auxiliar > 0) {
                            aux = aux - auxiliar;
                        }

                        if (leituras[aux] != null && !leituras[aux].equals("")) {
                            dado.setLeituraHidrometro(new Integer(leituras[aux]));
                            if (dado.getLeituraHidrometro().intValue() >= dado.getFaixaLeituraEsperadaInferior().intValue()
                                    && dado.getLeituraHidrometro().intValue() <= dado.getFaixaLeituraEsperadaSuperior().intValue()) {
                                dado.setIndicadorConfirmacaoLeitura(new Byte((byte) 1));
                            } else {
                                dado.setIndicadorConfirmacaoLeitura(new Byte((byte) 0));
                            }

                        } else {
                            dado.setLeituraHidrometro(null);
                            dado.setIndicadorConfirmacaoLeitura(new Byte((byte) 0));
                        }

                        if (anormalidades[aux] != null && !anormalidades[aux].equals("")) {
                            dado.setCodigoAnormalidade(new Integer(anormalidades[aux]));
                        } else {
                            dado.setCodigoAnormalidade(new Integer(0));
                        }

                        if (datas[aux] != null && !datas[aux].equals("")) {
                            dado.setDataLeituraCampo(Util.converteStringParaDate(datas[aux]));
                            if (dado.getDataLeituraCampo() == null) {
                                throw new ActionServletException("atencao.date",
                                                                 null,
                                                                 "Data");
                            } else if (dado.getDataLeituraCampo().after(new Date())) {
                                throw new ActionServletException("atencao.data_menor_que_atual",
                                                                 null,
                                                                 "Leitura");
                            }
                        }
                    } else {
                        /*
                         * aumenta um indice no tamanho de dados da tela, pois
                         * nao conta com os dados que ligacao de agua suprimida
                         * ou hidrometro retirado.
                         */
                        lengthLeituras = lengthLeituras + 1;
                        auxiliar = auxiliar + 1;
                        dado.setLeituraHidrometro(null);
                        dado.setIndicadorConfirmacaoLeitura(new Byte((byte) 0));
                        dado.setCodigoAnormalidade(new Integer(0));
                        dado.setDataLeituraCampo(new Date());
                    }

                    dados.add(dado);
                }

                fachada.atualizarLeituraAnormalidadeSemCelular(dados);
            }

            String action = httpServletRequest.getParameter("action").toString();

            boolean concluir = false;

            if (action.equals("voltar")) {

                // Voltar
                indice--;
            } else if (action.equals("avancar")) {

                // Avançar
                indice++;
            } else if (action.equals("concluir")) {
                // Concluir
                Iterator<DadosMovimentacao> it = form.getDados().iterator();
                concluir = true;
                boolean naoAcabou = false;
                while (it.hasNext()) {
                    DadosMovimentacao dado = it.next();

                    if (dado.getDataLeituraCampo() == null
                            || (dado.getLeituraHidrometro() == null && dado.getCodigoAnormalidade() == null)) {

                        if (dado.getMsgImovelSuprimidoOuHidrometroRetirado() == null
                                || dado.getMsgImovelSuprimidoOuHidrometroRetirado().equals("")) {

                            naoAcabou = true;
                            break;
                        }
                    }

                }

                if (!naoAcabou) {

                    FiltroRota filtroRota = new FiltroRota();
                    filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.LOCALIDADE_ID,
                                                                       form.getIdLocalidade()));
                    filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_CODIGO,
                                                                       form.getCodigoSetorComercial()));
                    filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.CODIGO_ROTA,
                                                                       form.getRota()));

                    Collection colecao = fachada.pesquisar(filtroRota, Rota.class.getName());

                    Rota rota = null;

                    if (colecao != null && !colecao.isEmpty()) {
                        rota = (Rota) colecao.iterator().next();
                    }

                    Collection<Integer> processoSituacao = new ArrayList<Integer>();
                    processoSituacao.add(ProcessoSituacao.EM_ESPERA);
                    processoSituacao.add(ProcessoSituacao.EM_PROCESSAMENTO);

                    FiltroProcessoIniciado filtroProcessoIniciado = new FiltroProcessoIniciado();
                    filtroProcessoIniciado.adicionarParametro(new ParametroSimples(FiltroProcessoIniciado.ID_PROCESSO,
                                                                                   Processo.FATURAR_GRUPO_FATURAMENTO));
                    filtroProcessoIniciado.adicionarParametro(new ParametroSimples(FiltroProcessoIniciado.CODIGO_GRUPO,
                                                                                   rota.getFaturamentoGrupo().getId()));
                    filtroProcessoIniciado.adicionarParametro(new ParametroSimplesIn(FiltroProcessoIniciado.PROCESSO_SITUACAO_ID,
                                                                                     processoSituacao));

                    Collection colecaoProcessos = fachada.pesquisar(filtroProcessoIniciado, ProcessoIniciado.class.getName());

                    if (!Util.isVazioOrNulo(colecaoProcessos)) {
                        throw new ActionServletException("atencao.processo_faturamento_em_execucao");
                    }

                    // FiltroRota filtroRota = new FiltroRota();
                    // filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.FATURAMENTO_GRUPO);
                    //										
                    // filtroRota.adicionarParametro(new
                    // ParametroSimples(FiltroRota.LOCALIDADE_ID,
                    // form.getIdLocalidade()));
                    // filtroRota.adicionarParametro(new
                    // ParametroSimples(FiltroRota.SETOR_COMERCIAL_CODIGO,
                    // form.getCodigoSetorComercial()));
                    // filtroRota.adicionarParametro(new
                    // ParametroSimples(FiltroRota.CODIGO_ROTA,
                    // form.getRota()));
                    //					
                    // Collection colecao = f.pesquisar(filtroRota,
                    // Rota.class.getName());
                    //					
                    // if(colecao !=null && !colecao.isEmpty()){
                    // Rota rota = (Rota)colecao.iterator().next();
                    // f.efetuarRateioDeConsumoPorRota(rota,
                    // rota.getFaturamentoGrupo().getAnoMesReferencia());
                    // }
                    Date dataRealizacao = null;
                    if (form.getDados().get(0) != null && form.getDados().get(0).getDataLeituraCampo() != null
                            && !form.getDados().get(0).getDataLeituraCampo().equals("")) {
                        dataRealizacao = form.getDados().get(0).getDataLeituraCampo();
                    } else {
                        dataRealizacao = new Date();
                    }

                    fachada.atualizarFaturamentoAtividadeCronogramaRegistrarConsistirEfetuarLeitura(form.getDados().get(0).getGrupoFaturamento(), dataRealizacao);

                    Iterator<DadosMovimentacao> itera = form.getDados().iterator();
                    ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa = null;
                    while (itera.hasNext()) {
                        DadosMovimentacao dado = itera.next();

                        if (dado.getArquivoTextoRoteiroEmpresa() != null) {
                            arquivoTextoRoteiroEmpresa = dado.getArquivoTextoRoteiroEmpresa();
                            break;
                        }
                    }

                    if (arquivoTextoRoteiroEmpresa != null
                            && arquivoTextoRoteiroEmpresa.getSituacaoTransmissaoLeitura() != null
                            && arquivoTextoRoteiroEmpresa.getSituacaoTransmissaoLeitura().getId().compareTo(SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO) != 0) {

                        arquivoTextoRoteiroEmpresa.setSituacaoTransmissaoLeitura(new SituacaoTransmissaoLeitura(SituacaoTransmissaoLeitura.FINALIZADO_POR_DIGITACAO));
                        arquivoTextoRoteiroEmpresa.setUltimaAlteracao(new Date());

                        fachada.atualizar(arquivoTextoRoteiroEmpresa);
                    }

                    retorno = actionMapping.findForward("telaSucesso");
                    montarPaginaSucesso(httpServletRequest, "Leituras e Anormalidades da Rota inseridas com sucesso", "Leituras e Anormalidades por Rota", "exibirInformarLeituraRotaAction.do?menu=sim");
                } else {
                    // Mensagem de erro
                    throw new ActionServletException("atencao.leitura_rota_nao_concluida",
                                                     null,
                                                     form.getRota());
                }
            }

            if (!concluir) {
                form.setIndice(new Integer(indice));
                Collection<DadosMovimentacao> dados12 = new ArrayList<DadosMovimentacao>();

                for (int i = (indice - 1) * 12; i < (indice - 1) * 12 + 12 && i < form.getDados().size(); i++) {
                    DadosMovimentacao dado = form.getDados().get(i);
                    dados12.add(dado);
                    faixas.append(dado.getFaixaLeituraEsperadaInferior());
                    faixas.append(delimitador2);
                    faixas.append(dado.getFaixaLeituraEsperadaSuperior());
                    if (i + 1 < (indice - 1) * 12 + 12 && i + 1 < form.getDados().size()) {
                        faixas.append(delimitador);
                    }
                }

                // Novos 12 imoveis
                sessao.setAttribute("colecaoLeituras", dados12);

                httpServletRequest.setAttribute("qnt", "" + dados12.size());

                // Anormalidades do banco
                Collection colecaoLeituraAnormalidade = Fachada.getInstancia().pesquisar(filtro, LeituraAnormalidade.class.getName());

                Iterator iterator = colecaoLeituraAnormalidade.iterator();
                StringBuffer anor = new StringBuffer();

                while (iterator.hasNext()) {
                    LeituraAnormalidade l = (LeituraAnormalidade) iterator.next();
                    anor.append(l.getId().toString());
                    anor.append(delimitador2);
                    anor.append(l.getIndicadorLeitura().toString());

                    if (iterator.hasNext()) {
                        anor.append(delimitador);
                    }

                }
                httpServletRequest.setAttribute("anormalidadesBanco", anor.toString());
                httpServletRequest.setAttribute("faixa", faixas.toString());

            }

        } catch (NumberFormatException n) {
            throw new ActionServletException("atencao.integer",
                                             n,
                                             "Leitura ou Anormalidade");

        } catch (IllegalArgumentException i) {
            throw new ActionServletException("atencao.date",
                                             i,
                                             "Data");
        }

        return retorno;
    }

    //	
    // private void verificaGrupoFaturado(
    // String idLocalidade,
    // String cdSetorComercial,
    // String cdRota,
    // InformarLeituraRotaActionForm form,
    // HttpServletRequest httpServletRequest, Fachada fachada) {
    //	
    // SistemaParametro sistemaParametro =
    // fachada.pesquisarParametrosDoSistema();
    //		
    // FiltroRota filtroRota = new FiltroRota();
    // filtroRota.adicionarParametro(new
    // ParametroSimples(FiltroRota.CODIGO_ROTA, cdRota));
    // filtroRota.adicionarParametro(new
    // ParametroSimples(FiltroRota.LOCALIDADE_ID, idLocalidade));
    // filtroRota.adicionarParametro(new
    // ParametroSimples(FiltroRota.SETOR_COMERCIAL_CODIGO,cdSetorComercial));
    //		
    // filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.SETOR_COMERCIAL);
    // filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.LOCALIDADE);
    // filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.FATURAMENTO_GRUPO);
    // filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.EMPRESA);
    //		
    // Collection pesquisa = fachada.pesquisar(filtroRota,
    // Rota.class.getName());
    //		
    // if (pesquisa != null && !pesquisa.isEmpty()) {
    // Rota rota = (Rota) Util.retonarObjetoDeColecao(pesquisa);
    //			
    // FaturamentoGrupo grupoFaturamento = rota.getFaturamentoGrupo();
    // Integer anoMesFaturamento = sistemaParametro.getAnoMesFaturamento();
    //			
    // if(Util.compararAnoMesReferencia(grupoFaturamento.getAnoMesReferencia(),
    // anoMesFaturamento, ">") ){
    // throw new ActionServletException("atencao.grupo.ja.faturado");
    // }
    // }
    // }
    //	

}
