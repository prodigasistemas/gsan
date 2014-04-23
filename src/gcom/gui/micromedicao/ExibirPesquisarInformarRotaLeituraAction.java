package gcom.gui.micromedicao;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action para exibir a pesquisa por rota da página Informar Leitura por Rota
 * 
 * @author Thiago Nascimento
 * 
 */
public class ExibirPesquisarInformarRotaLeituraAction extends GcomAction {

    @Override
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("ExibirPesquisarRotaAction");

        PesquisarRotaActionForm form = (PesquisarRotaActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();

        HttpSession sessao = httpServletRequest.getSession(false);

        if (httpServletRequest.getParameter("limparForm") != null
                && httpServletRequest.getParameter("limparForm").equals("sim")) {
            form.setIdGrupoFaturamento("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
            form.setIdLocalidade("");
            form.setLocalidadeDescricao("");
            form.setCodigoSetorComercial("");
            form.setSetorComercialDescricao("");
            form.setCodigoRota("");
            form.setEmpresaLeituristica("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
            form.setIndicadorUso("3");
            form.setBloquearCampos("");

            sessao.removeAttribute("bloquearSetorComercial");
            sessao.removeAttribute("caminhoRetornoTelaPesquisa");
        }

        form.setIndicadorRotaAlternativa(ConstantesSistema.NAO_CONFIRMADA);

        String idEmpresaLeituristica = httpServletRequest.getParameter("idEmpresaLeituristicaRecebida");
        if (idEmpresaLeituristica != null) {
            sessao.setAttribute("idEmpresaLeituristicaRecebida", idEmpresaLeituristica);
            form.setEmpresaLeituristica(idEmpresaLeituristica);
        }

        if (httpServletRequest.getParameter("idLocalidade") != null
                && !httpServletRequest.getParameter("idLocalidade").equals("")) {
            form.setIdLocalidade("" + httpServletRequest.getParameter("idLocalidade"));
        }

        if (httpServletRequest.getParameter("codigoSetorComercial") != null
                && !httpServletRequest.getParameter("codigoSetorComercial").equals("")) {
            form.setCodigoSetorComercial("" + httpServletRequest.getParameter("codigoSetorComercial"));
        }

        if (httpServletRequest.getParameter("bloquearCampos") != null
                && !httpServletRequest.getParameter("bloquearCampos").equals("")) {
            form.setBloquearCampos("sim");
        }

        // Pesquisando grupo de faturamento
        FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
        filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO_ABREVIADA);

        Collection<FaturamentoGrupo> collectionFaturamentoGrupo = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

        sessao.setAttribute("collectionFaturamentoGrupo", collectionFaturamentoGrupo);
        // Fim de pesquisando grupo de faturamento

        // Pesquisando empresa leiturística
        FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
        filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);

        Collection<Empresa> collectionEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());

        sessao.setAttribute("collectionEmpresa", collectionEmpresa);
        // Fim de pesquisando empresa leiturística

        // -------Parte que trata do código quando o usuário tecla enter
        String idDigitadoEnterLocalidade = form.getIdLocalidade();
        if (idDigitadoEnterLocalidade != null && !idDigitadoEnterLocalidade.equalsIgnoreCase("")
                && Util.validarValorNaoNumerico(idDigitadoEnterLocalidade)) {
            // Localidade não numérico.
            httpServletRequest.setAttribute("nomeCampo", "idLocalidade");
            throw new ActionServletException("atencao.nao.numerico",
                                             null,
                                             "Localidade ");
        }

        verificaExistenciaCodLocalidade(idDigitadoEnterLocalidade, form, httpServletRequest, fachada);

        String idDigitadoEnterSetorComercial = form.getCodigoSetorComercial();
        if (idDigitadoEnterSetorComercial != null && !idDigitadoEnterSetorComercial.equalsIgnoreCase("")
                && Util.validarValorNaoNumerico(idDigitadoEnterSetorComercial)) {
            // Setor Comercial não numérico.
            httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercial");
            throw new ActionServletException("atencao.nao.numerico",
                                             null,
                                             "Setor Comercial ");
        }

        if (httpServletRequest.getParameter("tipoConsulta") != null
                && !httpServletRequest.getParameter("tipoConsulta").equals("")) {

            // Verifica se o tipo da consulta é de Localidade
            // se for os parametros de enviarDadosParametros serão mandados para
            // a pagina rota_pesuisar.jsp
            if (httpServletRequest.getParameter("tipoConsulta").equals("localidade")) {

                form.setIdLocalidade(httpServletRequest.getParameter("idCampoEnviarDados"));

                form.setLocalidadeDescricao(httpServletRequest.getParameter("descricaoCampoEnviarDados"));

                httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercial");

            } else if (httpServletRequest.getParameter("tipoConsulta").equals("setorComercial")) {

                form.setCodigoSetorComercial(httpServletRequest.getParameter("idCampoEnviarDados"));

                form.setSetorComercialDescricao(httpServletRequest.getParameter("descricaoCampoEnviarDados"));

                httpServletRequest.setAttribute("nomeCampo", "codigoRota");

            }

        }

        if (httpServletRequest.getParameter("idSetorComercial") != null
                && !httpServletRequest.getParameter("idSetorComercial").trim().equals("")) {
            String idSetorComercial = httpServletRequest.getParameter("idSetorComercial");
            FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
            filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade(FiltroSetorComercial.LOCALIDADE);
            filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID,
                                                                         idSetorComercial));

            Collection colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

            if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {

                SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);

                form.setCodigoSetorComercial("" + setorComercial.getCodigo());
                form.setSetorComercialDescricao(setorComercial.getDescricao());
                form.setIdLocalidade(setorComercial.getLocalidade().getId().toString());
                form.setLocalidadeDescricao(setorComercial.getLocalidade().getDescricao());
                form.setBloquearCampos("Sim");
            }

        }

        // envia uma flag que será verificado no quadra_resultado_pesquisa.jsp
        // para saber se irá usar o enviar dados ou o enviar dados parametros
        if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisa") != null) {
            sessao.setAttribute("caminhoRetornoTelaPesquisaQuadra", httpServletRequest.getParameter("caminhoRetornoTelaPesquisa"));
        }

        verificaExistenciaCodSetorComercial(idDigitadoEnterLocalidade, idDigitadoEnterSetorComercial, form, httpServletRequest, fachada);

        // -------Fim de parte que trata do código quando o usuário tecla enter
        if (httpServletRequest.getParameter("destinoRota") != null) {
            if (httpServletRequest.getParameter("destinoRota").equals("Inicial")) {
                sessao.setAttribute("destinoRota", "Inicial");
            } else {
                sessao.setAttribute("destinoRota", "Final");
            }

        }
        if (httpServletRequest.getParameter("idFaturamentoGrupo") != null) {
            if (!httpServletRequest.getParameter("idFaturamentoGrupo").equals("-1")) {
                form.setIdGrupoFaturamento(httpServletRequest.getParameter("idFaturamentoGrupo"));
            } else {
                sessao.removeAttribute("idFaturamentoGrupo");
            }
        }

        return retorno;
    }

    private void verificaExistenciaCodLocalidade(String idDigitadoEnterLocalidade, PesquisarRotaActionForm form,
            HttpServletRequest httpServletRequest, Fachada fachada) {

        // Verifica se o código da Localidade foi digitado
        if (idDigitadoEnterLocalidade != null && !idDigitadoEnterLocalidade.trim().equals("")
                && Integer.parseInt(idDigitadoEnterLocalidade) > 0) {

            // Recupera a localidade informada pelo usuário
            Localidade localidadeEncontrada = fachada.pesquisarLocalidadeDigitada(new Integer(idDigitadoEnterLocalidade));

            // Caso a localidade informada pelo usuário esteja cadastrada no
            // sistema
            // Seta os dados da localidade no form
            // Caso contrário seta as informações da localidade para vazio
            // e indica ao usuário que a localidade não existe

            if (localidadeEncontrada != null) {
                // a localidade foi encontrada
                form.setIdLocalidade("" + (localidadeEncontrada.getId()));
                form.setLocalidadeDescricao(localidadeEncontrada.getDescricao());
                httpServletRequest.setAttribute("idLocalidadeNaoEncontrada", "true");
                httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercial");

            } else {
                // a localidade não foi encontrada
                form.setIdLocalidade("");
                httpServletRequest.setAttribute("idLocalidadeNaoEncontrada", "exception");
                form.setLocalidadeDescricao("LOCALIDADE INEXISTENTE");

            }
        }

    }

    private void verificaExistenciaCodSetorComercial(String idDigitadoEnterLocalidade, String idDigitadoEnterSetorComercial,
            PesquisarRotaActionForm form, HttpServletRequest httpServletRequest, Fachada fachada) {

        // Verifica se o código do Setor Comercial foi digitado
        if ((idDigitadoEnterSetorComercial != null && !idDigitadoEnterSetorComercial.toString().trim().equalsIgnoreCase(""))
                && (idDigitadoEnterLocalidade != null && !idDigitadoEnterLocalidade.toString().trim().equalsIgnoreCase(""))) {

            FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
            if (idDigitadoEnterLocalidade != null && !idDigitadoEnterLocalidade.toString().trim().equalsIgnoreCase("")) {

                filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE,
                                                                             new Integer(idDigitadoEnterLocalidade)));
            }

            filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
                                                                         new Integer(idDigitadoEnterSetorComercial)));

            Collection<SetorComercial> setorComerciais = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

            if (setorComerciais != null && !setorComerciais.isEmpty()) {
                // o setor comercial foi encontrado
                SetorComercial setorComercialEncontrado = (SetorComercial) Util.retonarObjetoDeColecao(setorComerciais);
                form.setCodigoSetorComercial("" + (setorComercialEncontrado.getCodigo()));
                form.setSetorComercialDescricao(setorComercialEncontrado.getDescricao());
                httpServletRequest.setAttribute("idSetorComercialNaoEncontrada", "true");

            } else {
                // o setor comercial não foi encontrado
                form.setCodigoSetorComercial("");
                httpServletRequest.setAttribute("idSetorComercialNaoEncontrada", "exception");
                form.setSetorComercialDescricao("SETOR COMERCIAL INEXISTENTE");

            }

        }

    }
}
