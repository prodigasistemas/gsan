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

                Collection<DadosMovimentacao> dados = fachada.buscarImoveisPorRota(null, rota, form.getTipo().trim().equals("1"));

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
