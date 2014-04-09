package gcom.gui.micromedicao;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRateioTipo;
import gcom.micromedicao.RateioTipo;
import gcom.micromedicao.hidrometro.FiltroHidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Atualizar Tipo de Rateio
 * <p>
 * [UC0098] Manter Vínculos de Imóveis para Rateio de Consumo
 * </p>
 * 
 * @author Rafael Santos, Magno Gouveia
 * @since 12/01/2006, 17/08/2011
 */
public class AtualizarTipoRateioPopupAction extends GcomAction {

    Fachada fachada = Fachada.getInstancia();

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

        ActionForward retorno = actionMapping.findForward("telaSucessoPopup");

        HttpSession sessao = httpServletRequest.getSession(false);

        AtualizarTipoRateioPopupActionForm form = (AtualizarTipoRateioPopupActionForm) actionForm;

        String tipoRateioLigacaoAgua = form.getRateioTipoAgua();

        String tipoRateioLigacaoPoco = form.getRateioTipoPoco();

        Imovel imovel = null;
        if (sessao.getAttribute("imovelVinculado") != null) {

            imovel = (Imovel) sessao.getAttribute("imovelVinculado");
            HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoAgua = null;
            HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoPoco = null;

            if (tipoRateioLigacaoAgua != null && !tipoRateioLigacaoAgua.equals("")) {

                // Rateio Tipo Agua
                FiltroRateioTipo filtroRateioTipo = new FiltroRateioTipo();
                filtroRateioTipo.adicionarParametro(new ParametroSimples(FiltroRateioTipo.ID,
                                                                         tipoRateioLigacaoAgua));
                RateioTipo rateioTipo = (RateioTipo) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroRateioTipo, RateioTipo.class.getName()));

                FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();
                filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroSimples(FiltroHidrometroInstalacaoHistorico.LIGACAO_AGUA_ID,
                                                                                            imovel.getId()));
                filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroNulo(FiltroHidrometroInstalacaoHistorico.DATA_RETIRADA));
                filtroHidrometroInstalacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua");

                Collection colecaoHidrometroInstalacaoHistorico = fachada.pesquisar(filtroHidrometroInstalacaoHistorico, HidrometroInstalacaoHistorico.class.getName());

                if (colecaoHidrometroInstalacaoHistorico != null && !colecaoHidrometroInstalacaoHistorico.isEmpty()) {
                    hidrometroInstalacaoHistoricoAgua = (HidrometroInstalacaoHistorico) Util.retonarObjetoDeColecao(colecaoHidrometroInstalacaoHistorico);
                    hidrometroInstalacaoHistoricoAgua.setRateioTipo(rateioTipo);
                }

            }

            if (tipoRateioLigacaoPoco != null && !tipoRateioLigacaoPoco.equals("")) {

                FiltroRateioTipo filtroRateioTipo = new FiltroRateioTipo();
                filtroRateioTipo.adicionarParametro(new ParametroSimples(FiltroRateioTipo.ID,
                                                                         tipoRateioLigacaoPoco));

                // Rateio Tipo Poco
                RateioTipo rateioTipo = (RateioTipo) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroRateioTipo, RateioTipo.class.getName()));

                FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();
                filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroSimples(FiltroHidrometroInstalacaoHistorico.IMOVEL_ID,
                                                                                            imovel.getId()));
                filtroHidrometroInstalacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua");
                filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroNulo(FiltroHidrometroInstalacaoHistorico.DATA_RETIRADA));

                Collection colecaoHidrometroInstalacaoHistorico = fachada.pesquisar(filtroHidrometroInstalacaoHistorico, HidrometroInstalacaoHistorico.class.getName());

                if (colecaoHidrometroInstalacaoHistorico != null && !colecaoHidrometroInstalacaoHistorico.isEmpty()) {
                    hidrometroInstalacaoHistoricoPoco = (HidrometroInstalacaoHistorico) Util.retonarObjetoDeColecao(colecaoHidrometroInstalacaoHistorico);
                    hidrometroInstalacaoHistoricoPoco.setRateioTipo(rateioTipo);
                }
            }

            if (httpServletRequest.getParameter("confirmado") != null) {

                if (httpServletRequest.getParameter("confirmado").equalsIgnoreCase("ok")) {

                    /*
                     * [SB0004] – Informar Imóvel para Área Comum Caso o imóvel pesquisado não
                     * exista ou seja diferente do imóvel a qual será vinculado Caso esteja com
                     * valor = nulo imov_id correspondente ao imóvel condomínio que está sendo
                     * atualizado
                     */
                    Integer matriculaImovelAreaComum = Integer.parseInt((String) sessao.getAttribute("matriculaImovelAreaComum"));
                    Integer imovelCondominioDoImovelAreaComum = this.fachada.pesquisarImovelCondominio(matriculaImovelAreaComum);

                    if (imovelCondominioDoImovelAreaComum == null
                            || !imovelCondominioDoImovelAreaComum.equals(imovel.getId())) {

                        Collection<Imovel> imoveisParaVincular = new ArrayList<Imovel>();
                        Imovel imovelParaVincular = this.fachada.pesquisarImovel(matriculaImovelAreaComum);
                        imovelParaVincular.setImovelCondominio(imovel);
                        imoveisParaVincular.add(imovelParaVincular);
                        fachada.estabelecerVinculo(imovel, imoveisParaVincular, null, hidrometroInstalacaoHistoricoAgua, hidrometroInstalacaoHistoricoPoco, (Usuario) sessao.getAttribute(Usuario.USUARIO_LOGADO));
                    }

                    atualizarTipoRateio(sessao, imovel, hidrometroInstalacaoHistoricoAgua, hidrometroInstalacaoHistoricoPoco, true, matriculaImovelAreaComum);
                }
            } else {
                /*
                 * [SB0001] Caso o usuário selecione o botão ATUALIZAR TIPO DE RATEIO Caso o tipo de
                 * rateio tenha sido atualizado para 'RATEIO POR ÁREA COMUM' o sistema valida a
                 * matrícula para área comum
                 */
                if (tipoRateioLigacaoAgua != null
                        && Integer.valueOf(tipoRateioLigacaoAgua).equals(RateioTipo.RATEIO_AREA_COMUM)) {

                    FiltroImovel filtroImovel = new FiltroImovel();
                    filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
                                                                         form.getMatriculaImovelAreaComum()));
                    filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
                    filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.rota");
                    filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_SITUACAO);

                    // Procura Imovel na base
                    Collection colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());

                    // [FS0001] Verificar exitência da matrícula do imóvel
                    if (colecaoImoveis != null && colecaoImoveis.isEmpty()) {
                        throw new ActionServletException("atencao.pesquisa_inexistente",
                                                         null,
                                                         "Matrícula");
                    }

                    Imovel imovelASerVinculado = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);

                    if (imovel != null && imovelASerVinculado.getId().intValue() == imovel.getId().intValue()) {
                        throw new ActionServletException("atencao.imovel_condominio.nao.vincular.imovel");
                    }

                    /*
                     * [FS0003] Verificar exitência de vínculo com outro imóvel condomínio
                     */
                    if (imovelASerVinculado.getImovelCondominio() != null
                            && (imovelASerVinculado.getImovelCondominio().getId() != null)
                            && (imovelASerVinculado.getImovelCondominio().getId().intValue() != imovel.getId().intValue())) {
                        throw new ActionServletException("atencao.imovel.vinculado",
                                                         null,
                                                         imovelASerVinculado.getImovelCondominio().getId().toString());
                    }

                    // [FS0004] Verificar se o imóvel já é um condomínio
                    if (imovelASerVinculado.getIndicadorImovelCondominio() != null
                            && imovelASerVinculado.getIndicadorImovelCondominio().shortValue() == Imovel.IMOVEL_CONDOMINIO.shortValue()) {
                        throw new ActionServletException("atencao.imovel.condominio");
                    }

                    // [FS0006] Verificar Rota
                    if (imovel != null) {
                        if (imovel.getQuadra().getRota().getId().intValue() != imovelASerVinculado.getQuadra()
                                                                                                  .getRota()
                                                                                                  .getId()
                                                                                                  .intValue()) {
                            throw new ActionServletException("atencao.imovel.nao_rota");
                        }
                    }

                    // [FS0010] Verificar pré-requisitos para imóvel vinculado
                    if (imovel != null) {

                        if (imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.LIGADO.intValue()
                                && imovelASerVinculado.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.LIGADO.intValue()
                                && imovelASerVinculado.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.CORTADO.intValue()) {
                            throw new ActionServletException("atencao.imovel.agua.incompativel.condominio");
                        }

                        if (imovel.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.LIGADO.intValue()
                                && imovelASerVinculado.getLigacaoEsgotoSituacao().getId().intValue() == LigacaoEsgotoSituacao.LIGADO.intValue()
                                && imovelASerVinculado.getLigacaoEsgotoSituacao().getId().intValue() == LigacaoEsgotoSituacao.LIG_FORA_DE_USO.intValue()) {
                            throw new ActionServletException("atencao.imovel.esgoto.incompativel.condominio");
                        }

                        if (imovel.getLigacaoEsgotoSituacao().getId().intValue() == LigacaoEsgotoSituacao.LIGADO.intValue()
                                && imovelASerVinculado.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.LIGADO.intValue()
                                && imovelASerVinculado.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.LIG_FORA_DE_USO.intValue()) {
                            throw new ActionServletException("atencao.imovel.esgoto.incompativel.condominio");
                        }
                    }

                    // FS0012
                    if (form.getMatriculaImovelAreaComum() == null || form.getMatriculaImovelAreaComum().length() == 0) {
                        form.setMatriculaImovelAreaComum(form.getMatriculaImovelAreaComumAtual());
                    }
                    this.validarMatriculaParaAreaComum(form.getMatriculaImovelAreaComum());

                    // FS0013 e FS0014
                    boolean imovelNaoVinculadoAoCondominio = this.validarVinculoMatriculaAreaComum(imovel.getId(), form.getMatriculaImovelAreaComum());

                    // FS0014
                    if (imovelNaoVinculadoAoCondominio) {
                        sessao.setAttribute("matriculaImovelAreaComum", form.getMatriculaImovelAreaComum());
                        httpServletRequest.setAttribute("caminhoActionConclusao", "/gsan/atualizarTipoRateioPopupAction.do");
                        httpServletRequest.setAttribute("nomeBotao1", "Sim");
                        httpServletRequest.setAttribute("nomeBotao2", "Não");

                        String[] params = new String[] { form.getMatriculaImovelAreaComum(), imovel.getId().toString() };

                        return montarPaginaConfirmacao("atencao.confirmar_vinculo_matricula_area_comum", httpServletRequest, actionMapping, params);
                    } else {
                        /*
                         * O imóvel informado já está vinculado ao imóvel condomínio, então só
                         * atualiza o indicadorImovelAreaComum
                         */
                        atualizarTipoRateio(sessao, imovel, hidrometroInstalacaoHistoricoAgua, hidrometroInstalacaoHistoricoPoco, true, Util.converterStringParaInteger(form.getMatriculaImovelAreaComum()));
                    }

                } else {
                    atualizarTipoRateio(sessao, imovel, hidrometroInstalacaoHistoricoAgua, hidrometroInstalacaoHistoricoPoco, false, Util.converterStringParaInteger(form.getMatriculaImovelAreaComum()));
                }

            }
        }

        httpServletRequest.setAttribute("fechar", "true");

        // liberar da sessao
        if (sessao.getAttribute("imovelVinculado") != null) {
            sessao.removeAttribute("imovelVinculado");
        }

        // Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucessoPopup")) {
            montarPaginaSucesso(httpServletRequest, "Tipo de Rateio do imóvel condomínio de matrícula " + imovel.getId()
                    + " atualizado com sucesso.", "", "");
        }

        return retorno;
    }

    /*
     * alterado por pedro alexandre dia 19/11/2006 Recupera o usuário logado para passar no metódo
     * de atualizar tipo de rateio para verificar se o usuário tem abrangência para atualizar o tipo
     * de rateio informado.
     */
    private void atualizarTipoRateio(HttpSession sessao, Imovel imovel,
            HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoAgua,
            HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoPoco, boolean isRateioPorAreaComum,
            Integer imovelAreaComum) {

        Usuario usuarioLogado = (Usuario) sessao.getAttribute(Usuario.USUARIO_LOGADO);

        // Atualizar Tipo Rateio
        fachada.atualizarTipoRateio(imovel, hidrometroInstalacaoHistoricoAgua, hidrometroInstalacaoHistoricoPoco, usuarioLogado, isRateioPorAreaComum, imovelAreaComum);
    }

    /**
     * [FS0012] - Validar Matricula para Área Comum
     */
    private void validarMatriculaParaAreaComum(String matriculaImovelAreaComum) {

        /*
         * Caso a matrícula do imóvel para área comum não tenha sido informada, exibir mensagem
         * 'Informe a matrícula do imóvel para área comum' e retornar para o passo correspondente no
         * fluxo principal
         */
        if (matriculaImovelAreaComum == null || matriculaImovelAreaComum.trim().length() == 0) {
            throw new ActionServletException("atencao.matricula_imovel_area_comum_nao_informada");
        }

        /*
         * Caso a matrícula do imóvel para área comum informada não exista na tabela IMOVEL, exibir
         * a mensagem 'Matrícula inexistente no cadastro' e retornar para o passo correspondente no
         * fluxo principal
         */
        Short indicadorExclusao = this.fachada.verificarExistenciaDoImovel(Integer.valueOf(matriculaImovelAreaComum));
        if (indicadorExclusao == null) {
            throw new ActionServletException("atencao.imovel_inexistente");
        } else if (indicadorExclusao.equals(Imovel.IMOVEL_EXCLUIDO)) {
            throw new ActionServletException("atencao.imovel_excluido");
        }
    }

    /**
     * [FS0013 – Validar Vinculo da Matrícula para Área Comum]
     */
    private boolean validarVinculoMatriculaAreaComum(Integer idImovelCondominio, String matriculaImovelAreaComum) {
        Integer imovelCondominioDoImovelAreaComumInformado = this.fachada.pesquisarImovelCondominio(Integer.valueOf(matriculaImovelAreaComum));

        if (imovelCondominioDoImovelAreaComumInformado != null
                && !imovelCondominioDoImovelAreaComumInformado.equals(Integer.valueOf(idImovelCondominio))) {
            throw new ActionServletException("atencao.imovel_vinculado_outro_condominio",
                                             String.valueOf(imovelCondominioDoImovelAreaComumInformado));
        }

        return (imovelCondominioDoImovelAreaComumInformado == null);
    }
}
