package gcom.gui.micromedicao;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Estabelecer Vinculo
 * 
 * @author Rafael Santos
 * @since 11/01/2006
 */
public class ExibirEstabelecerVinculoPopupAction extends GcomAction {
    /**
     * @author Administrador
     * @date 21/03/2006
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("estabelecerVinculoPopup");

        Fachada fachada = Fachada.getInstancia();

        // Mudar isso quando implementar a parte de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        EstabelecerVinculoPopupActionForm form = (EstabelecerVinculoPopupActionForm) actionForm;

        String acao = httpServletRequest.getParameter("acao");
        String posicao = httpServletRequest.getParameter("posicao");

        String codigoRegistro = httpServletRequest.getParameter("idCampoEnviarDados");

        String descricaoCampoEnviarDados = httpServletRequest.getParameter("descricaoCampoEnviarDados");

        if (codigoRegistro != null && !codigoRegistro.equals("")) {
            // acao = "EXIBIR";

            String matriculaImovel = (String) sessao.getAttribute("matriculaImovel");

            FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();
            filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroSimples(FiltroHidrometroInstalacaoHistorico.LIGACAO_AGUA_ID,
                                                                                        matriculaImovel));
            filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroNulo(FiltroHidrometroInstalacaoHistorico.DATA_RETIRADA));

            Collection colecaoHidrometroInstalacaoHistorico = fachada.pesquisar(filtroHidrometroInstalacaoHistorico, HidrometroInstalacaoHistorico.class.getName());

            if (colecaoHidrometroInstalacaoHistorico != null && !colecaoHidrometroInstalacaoHistorico.isEmpty()) {

                RateioTipo rateioTipo = ((HidrometroInstalacaoHistorico) colecaoHidrometroInstalacaoHistorico.iterator()
                                                                                                             .next()).getRateioTipo();
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
            filtroHidrometroInstalacaoHistoricoPoco.adicionarParametro(new ParametroNulo(filtroHidrometroInstalacaoHistorico.DATA_RETIRADA));

            Collection colecaoHidrometroInstalacaoHistoricoPoco = fachada.pesquisar(filtroHidrometroInstalacaoHistoricoPoco, HidrometroInstalacaoHistorico.class.getName());

            if (colecaoHidrometroInstalacaoHistoricoPoco != null && !colecaoHidrometroInstalacaoHistoricoPoco.isEmpty()) {

                RateioTipo rateioTipo = ((HidrometroInstalacaoHistorico) Util.retonarObjetoDeColecao(colecaoHidrometroInstalacaoHistoricoPoco)).getRateioTipo();
                if (rateioTipo != null) {
                    form.setRateioTipoPoco(rateioTipo.getId().toString());
                } else {
                    form.setRateioTipoPoco("");
                }
                form.setBotao("Visualizar");
            }
        }

        String pesquisaImovel = httpServletRequest.getParameter("pesquisaImovel");

        if (pesquisaImovel != null && !pesquisaImovel.equals("")) {

            FiltroImovel filtroImovel = new FiltroImovel();
            filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
                                                                 form.getCodigoImovel()));

            filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOCALIDADE);
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL);

            Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());

            if (colecaoImovel != null && !colecaoImovel.isEmpty()) {
                Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);

                form.setMatriculaImovel(imovel.getInscricaoFormatada());
                form.setCodigoImovel(imovel.getId().toString());
                httpServletRequest.setAttribute("matriculaInexistente", null);

            } else {
                form.setMatriculaImovel("MATRÍCULA INEXISTENTE");
                form.setCodigoImovel("");
                httpServletRequest.setAttribute("matriculaInexistente", "exception");
            }

        }

        Collection<Imovel> colecaoImoveisASerVinculados = new ArrayList<Imovel>();

        if (acao != null && acao.trim().equalsIgnoreCase("EXIBIR")) {

            form.setRateioTipoAgua(null);
            form.setRateioTipoPoco(null);
            form.setBotao(null);
            form.setCodigoImovel(null);
            form.setImoveisVinculados(null);
            form.setImovel(null);
            form.setIndicadorImovelAreaComum(null);

            FiltroRateioTipo filtroRateioTipo = new FiltroRateioTipo();
            filtroRateioTipo.setCampoOrderBy(FiltroRateioTipo.DESCRICAO);
            filtroRateioTipo.adicionarParametro(new ParametroSimples(FiltroRateioTipo.INDICADOR_USO,
                                                                     ConstantesSistema.INDICADOR_USO_ATIVO));

            Collection<RateioTipo> colecaoRateioTipo = fachada.pesquisar(filtroRateioTipo, RateioTipo.class.getName());

            sessao.setAttribute("colecaoRateioTipo", colecaoRateioTipo);

            String matriculaImovel = null;

            if (httpServletRequest.getParameter("MatriculaImovel") != null) {

                matriculaImovel = httpServletRequest.getParameter("MatriculaImovel").trim();
            } else {
                matriculaImovel = (String) sessao.getAttribute("matriculaImovel");
            }

            sessao.setAttribute("matriculaImovel", matriculaImovel);

            FiltroImovel filtroImovel = new FiltroImovel();
            filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
                                                                 matriculaImovel));

            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua.hidrometroInstalacaoHistorico.rateioTipo");
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico.rateioTipo");
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelCondominio");
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_SITUACAO);
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_ESGOTO_SITUACAO);
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.rota");

            Collection<Imovel> imovelPesquisado = fachada.pesquisar(filtroImovel, Imovel.class.getName());

            // visualizar os dados do condominio
            if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
                Imovel imovel = imovelPesquisado.iterator().next();

                FiltroImovel filtroImovelVinculados = new FiltroImovel(FiltroImovel.INDICADOR_IMOVEL_AREA_COMUM);
                filtroImovelVinculados.adicionarParametro(new ParametroSimples(FiltroImovel.IMOVEL_CONDOMINIO_ID,
                                                                               imovel.getId()));
                filtroImovelVinculados.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.IMOVEL_CONDOMINIO);

                Collection<Imovel> imovelPesquisadoVinculados = fachada.pesquisar(filtroImovelVinculados, Imovel.class.getName());

                // ligacao agua
                FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();
                filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroSimples(FiltroHidrometroInstalacaoHistorico.LIGACAO_AGUA_ID,
                                                                                            matriculaImovel));
                filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroNulo(FiltroHidrometroInstalacaoHistorico.DATA_RETIRADA));

                Collection colecaoHidrometroInstalacaoHistorico = fachada.pesquisar(filtroHidrometroInstalacaoHistorico, HidrometroInstalacaoHistorico.class.getName());

                if (colecaoHidrometroInstalacaoHistorico != null && !colecaoHidrometroInstalacaoHistorico.isEmpty()) {

                    RateioTipo rateioTipo = ((HidrometroInstalacaoHistorico) Util.retonarObjetoDeColecao(colecaoHidrometroInstalacaoHistorico)).getRateioTipo();
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
                filtroHidrometroInstalacaoHistoricoPoco.adicionarParametro(new ParametroNulo(FiltroHidrometroInstalacaoHistorico.DATA_RETIRADA));

                Collection colecaoHidrometroInstalacaoHistoricoPoco = fachada.pesquisar(filtroHidrometroInstalacaoHistoricoPoco, HidrometroInstalacaoHistorico.class.getName());

                if (colecaoHidrometroInstalacaoHistoricoPoco != null && !colecaoHidrometroInstalacaoHistoricoPoco.isEmpty()) {
                    RateioTipo rateioTipo = ((HidrometroInstalacaoHistorico) Util.retonarObjetoDeColecao(colecaoHidrometroInstalacaoHistoricoPoco)).getRateioTipo();
                    if (rateioTipo != null) {
                        form.setRateioTipoPoco(rateioTipo.getId().toString());
                    } else {
                        form.setRateioTipoPoco("");
                    }
                    form.setBotao("Visualizar");
                }

                if (form.getRateioTipoAgua() != null
                        && form.getRateioTipoAgua().equals(RateioTipo.RATEIO_AREA_COMUM.toString())) {
                    for (Imovel imovelVinculado : imovelPesquisadoVinculados) {
                        if (imovelVinculado.getIndicadorImovelAreaComum().equals(ConstantesSistema.SIM)) {
                            form.setPossuiImovelAreaComum(true);
                            break;
                        }
                    }
                }

                sessao.setAttribute("colecaoImoveisASerVinculados", imovelPesquisadoVinculados);

                // [FS0003] Verificar exitência de vínculo com outro imóvel
                // condomínio
                if (imovel.getImovelCondominio() != null && (imovel.getImovelCondominio().getId() != null)) {
                    throw new ActionServletException("atencao.imovel.vinculado",
                                                     null,
                                                     imovel.getImovelCondominio().getId().toString());
                }
                // [FS0002] Verificar pré-requisitos para imóvel condomínio
                if (imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.LIGADO.intValue()
                        && imovel.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.LIGADO.intValue()) {
                    throw new ActionServletException("atencao.imovel.condominio.nao.agua.esgoto");
                }

                // trata se não tem ligação de agua
                if (imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.LIGADO.intValue()
                        & imovel.getLigacaoAgua() == null) {
                    throw new ActionServletException("atencao.imovel.sem.ligacao_agua");
                }

                // [FS0002] Verificar pré-requisitos para imóvel condomínio
                if (imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.LIGADO.intValue()
                        && imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() == null) {
                    throw new ActionServletException("atencao.imovel.condominio.nao.hidrometro.agua");
                }
                // [FS0002] Verificar pré-requisitos para imóvel condomínio
                if (imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.LIGADO.intValue()
                        && imovel.getLigacaoEsgotoSituacao().getId().intValue() == LigacaoEsgotoSituacao.LIGADO.intValue()
                        && imovel.getHidrometroInstalacaoHistorico() == null) {
                    throw new ActionServletException("atencao.imovel.condominio.nao.hidrometro.poco");
                }

                // [FS0002] Verificar pré-requisitos para imóvel condomínio
                String anoMesFaturamento = fachada.pesquisarParametrosDoSistema().getAnoMesFaturamento() + "";

                Calendar dataFaturamento = new GregorianCalendar();
                dataFaturamento.set(Calendar.YEAR, new Integer(anoMesFaturamento.substring(0, 4)).intValue());
                dataFaturamento.set(Calendar.MONTH, new Integer(anoMesFaturamento.substring(4, 6)).intValue() - 1);
                dataFaturamento.set(Calendar.DATE, 30);

                // data inicio vencimento debito
                Calendar dataInicioVencimentoDebito = new GregorianCalendar();
                dataInicioVencimentoDebito.set(Calendar.YEAR, new Integer("0001").intValue());
                dataInicioVencimentoDebito.set(Calendar.MONTH, 0);
                dataInicioVencimentoDebito.set(Calendar.DATE, new Integer("01").intValue());

                // data final de vencimento de debito
                Calendar dataFimVencimentoDebito = new GregorianCalendar();
                dataFimVencimentoDebito.set(Calendar.YEAR, new Integer(anoMesFaturamento.substring(0, 4)).intValue());
                dataFimVencimentoDebito.set(Calendar.MONTH, new Integer(anoMesFaturamento.substring(4, 6)).intValue());
                dataFimVencimentoDebito.set(Calendar.DAY_OF_MONTH, dataFaturamento.getMaximum(Calendar.DAY_OF_MONTH));

                // data final referencia debito
                dataFaturamento.add(Calendar.MONTH, -1);
                StringBuffer dataFinalReferenciaDebito = new StringBuffer().append(dataFaturamento.get(Calendar.YEAR));
                if (dataFaturamento.get(Calendar.MONTH) < 10) {
                    dataFinalReferenciaDebito.append("0" + dataFaturamento.get(Calendar.MONTH));
                } else {
                    dataFinalReferenciaDebito.append(dataFaturamento.get(Calendar.MONTH));
                }

                ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper = fachada.obterDebitoImovelOuCliente(1, imovel.getId()
                                                                                                                                .toString(), null, null, "000101", dataFinalReferenciaDebito.toString(), dataInicioVencimentoDebito.getTime(), dataFimVencimentoDebito.getTime(), 1, 1, 1, 1, 1, 1, 2, null);

                boolean existeDebito = false;
                if (obterDebitoImovelOuClienteHelper != null) {
                    // contas
                    if (obterDebitoImovelOuClienteHelper.getColecaoContasValores() != null
                            && !obterDebitoImovelOuClienteHelper.getColecaoContasValores().isEmpty()) {
                        existeDebito = true;

                        // credito a realizar
                    } else if (obterDebitoImovelOuClienteHelper.getColecaoCreditoARealizar() != null
                            && !obterDebitoImovelOuClienteHelper.getColecaoCreditoARealizar().isEmpty()) {
                        existeDebito = true;

                        // debito a cobrar
                    } else if (obterDebitoImovelOuClienteHelper.getColecaoDebitoACobrar() != null
                            && !obterDebitoImovelOuClienteHelper.getColecaoDebitoACobrar().isEmpty()) {
                        existeDebito = true;

                        // guias pagamento
                    } else if (obterDebitoImovelOuClienteHelper.getColecaoGuiasPagamentoValores() != null
                            && !obterDebitoImovelOuClienteHelper.getColecaoGuiasPagamentoValores().isEmpty()) {
                        existeDebito = true;
                    }

                }

                // Se existir debito para o imovel
                if (existeDebito) {
                    throw new ActionServletException("atencao.imovel.condominio.possui_debito");

                }
                sessao.setAttribute("imovelCondominio", imovel);
                form.setMatriculaImovel("");
            }
        } else if (acao != null && acao.trim().equalsIgnoreCase("ADICIONAR")) {
            // adicionar imovel

            String idImovel = form.getCodigoImovel().trim();
            Collection imoveisASerVinculados = null;

            if (sessao.getAttribute("colecaoImoveisASerVinculados") != null) {
                imoveisASerVinculados = (Collection) sessao.getAttribute("colecaoImoveisASerVinculados");

                if (imoveisASerVinculados != null && !imoveisASerVinculados.isEmpty()) {
                    Iterator iImoveisASerVinculados = imoveisASerVinculados.iterator();

                    while (iImoveisASerVinculados.hasNext()) {

                        Imovel imovelVinculado = (Imovel) iImoveisASerVinculados.next();

                        if (imovelVinculado.getId().intValue() == new Integer(idImovel).intValue()) {
                            throw new ActionServletException("atencao.imovel.a_ser_vinculado",
                                                             null,
                                                             imovelVinculado.getId().toString());
                        }
                    }
                }
            }

            if (form.getRateioTipoAgua() != null) {
                form.setRateioTipoAgua(form.getRateioTipoAgua());
                form.setBotao("Visualizar");
            }
            if (form.getRateioTipoPoco() != null) {
                form.setRateioTipoPoco(form.getRateioTipoPoco());
                form.setBotao("Visualizar");
            }

            FiltroImovel filtroImovel = new FiltroImovel();

            filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
                                                                 idImovel));
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.rota");
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");

            // Procura Imovel na base
            Collection colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());

            // [FS0001] Verificar exitência da matrícula do imóvel
            if (colecaoImoveis != null && colecaoImoveis.isEmpty()) {
                throw new ActionServletException("atencao.pesquisa_inexistente",
                                                 null,
                                                 "Matrícula");
            }

            Imovel imovelASerVinculado = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);

            Imovel imovelCondominio = null;

            if (sessao.getAttribute("imovelCondominio") != null) {
                imovelCondominio = (Imovel) sessao.getAttribute("imovelCondominio");
            }

            if (imovelCondominio != null && imovelASerVinculado.getId().intValue() == imovelCondominio.getId().intValue()) {
                throw new ActionServletException("atencao.imovel_condominio.nao.vincular.imovel");
            }

            // [FS0003] Verificar exitência de vínculo com outro imóvel
            // condomínio
            if (imovelASerVinculado.getImovelCondominio() != null
                    && (imovelASerVinculado.getImovelCondominio().getId() != null)
                    && (imovelASerVinculado.getImovelCondominio().getId().intValue() != imovelCondominio.getId().intValue())) {
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
            if (imovelCondominio != null) {
                if (imovelCondominio.getQuadra().getRota().getId().intValue() != imovelASerVinculado.getQuadra()
                                                                                                    .getRota()
                                                                                                    .getId()
                                                                                                    .intValue()) {
                    throw new ActionServletException("atencao.imovel.nao_rota");
                }
            }

            // [FS0010] Verificar pré-requisitos para imóvel vinculado
            if (imovelCondominio != null) {

                if (imovelCondominio.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.LIGADO.intValue()
                        && imovelASerVinculado.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.LIGADO.intValue()
                        && imovelASerVinculado.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.CORTADO.intValue()) {
                    throw new ActionServletException("atencao.imovel.agua.incompativel.condominio");
                }

                if (imovelCondominio.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.LIGADO.intValue()
                        && imovelASerVinculado.getLigacaoEsgotoSituacao().getId().intValue() == LigacaoEsgotoSituacao.LIGADO.intValue()
                        && imovelASerVinculado.getLigacaoEsgotoSituacao().getId().intValue() == LigacaoEsgotoSituacao.LIG_FORA_DE_USO.intValue()) {
                    throw new ActionServletException("atencao.imovel.esgoto.incompativel.condominio");
                }

                if (imovelCondominio.getLigacaoEsgotoSituacao().getId().intValue() == LigacaoEsgotoSituacao.LIGADO.intValue()
                        && imovelASerVinculado.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.LIGADO.intValue()
                        && imovelASerVinculado.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.LIG_FORA_DE_USO.intValue()) {
                    throw new ActionServletException("atencao.imovel.esgoto.incompativel.condominio");
                }
            }

            if (form.getIndicadorImovelAreaComum() == null || form.getIndicadorImovelAreaComum().equals("2")) {
                imovelASerVinculado.setIndicadorImovelAreaComum(ConstantesSistema.NAO);
            } else {
                imovelASerVinculado.setIndicadorImovelAreaComum(ConstantesSistema.SIM);
                form.setPossuiImovelAreaComum(true);
            }

            // adicionar na coleção de imoveis a serem vinculados
            if (sessao.getAttribute("colecaoImoveisASerVinculados") != null) {
                ((Collection) sessao.getAttribute("colecaoImoveisASerVinculados")).add(imovelASerVinculado);
            } else {
                colecaoImoveisASerVinculados = new ArrayList();
                colecaoImoveisASerVinculados.add(imovelASerVinculado);
                sessao.setAttribute("colecaoImoveisASerVinculados", colecaoImoveisASerVinculados);
            }

            form.setCodigoImovel("");
            form.setMatriculaImovel("");

        } else if (acao != null && acao.trim().equalsIgnoreCase("REMOVER")) {
            // remover o imovel vinculado

            Collection imoveisASerVinculados = null;
            Collection novaimoveisASerVinculados = new ArrayList();
            Collection imoveisASerDesvinculados = null;
            // Collection novaimoveisASerDesvinculados = new ArrayList();

            if (sessao.getAttribute("colecaoImoveisASerDesvinculados") != null) {
                imoveisASerDesvinculados = (Collection) sessao.getAttribute("colecaoImoveisASerDesvinculados");
            } else {
                imoveisASerDesvinculados = new ArrayList();
            }

            if (sessao.getAttribute("colecaoImoveisASerVinculados") != null) {
                imoveisASerVinculados = (Collection) sessao.getAttribute("colecaoImoveisASerVinculados");

                if (imoveisASerVinculados != null && !imoveisASerVinculados.isEmpty()) {
                    Iterator iImoveisASerVinculados = imoveisASerVinculados.iterator();

                    while (iImoveisASerVinculados.hasNext()) {

                        Imovel imovel = (Imovel) iImoveisASerVinculados.next();

                        if (!(imovel.getId().intValue() == new Integer(posicao).intValue())) {
                            novaimoveisASerVinculados.add(imovel);
                        } else {
                            if (imovel.getIndicadorImovelAreaComum() != null
                                    && imovel.getIndicadorImovelAreaComum().equals(ConstantesSistema.SIM)) {
                                form.setPossuiImovelAreaComum(false);
                            }
                            imoveisASerDesvinculados.add(imovel);
                        }

                    }
                }

                sessao.removeAttribute("colecaoImoveisASerVinculados");
                sessao.removeAttribute("novaimoveisASerVinculados");

                sessao.setAttribute("colecaoImoveisASerVinculados", novaimoveisASerVinculados);
                sessao.setAttribute("colecaoImoveisASerDesvinculados", imoveisASerDesvinculados);
            }
        }

        if (codigoRegistro != null && !codigoRegistro.equals("")) {
            form.setCodigoImovel(codigoRegistro.trim());
            form.setMatriculaImovel(descricaoCampoEnviarDados);

        }

        return retorno;
    }
}
