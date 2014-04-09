package gcom.gui.micromedicao;

import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * [UC0936] Informar Leitura por Rota
 * </p>
 * <p>
 * Responsável pela exibição dos dados
 * </p>
 * 
 * @author Thiago Nascimento, Magno Gouveia
 * @since , 01/09/2011
 */
public class ExibirInformarLeituraRotaAction extends GcomAction {

    @Override
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("InformarLeituraRotaAction");

        InformarLeituraRotaActionForm form = (InformarLeituraRotaActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();

        if (httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")) {
            form.setIdLocalidade("");
            form.setLocalidadeDescricao("");
            form.setCodigoSetorComercial("");
            form.setSetorComercialDescricao("");
            form.setRota("");
            form.setBloquearCampos("");
            form.setTipo("1");
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

            /*
             * Verifica se o tipo da consulta é de Localidade se for os
             * parametros de enviarDadosParametros serão mandados para a pagina
             * rota_pesuisar.jsp
             */
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

        verificaExistenciaCodSetorComercial(idDigitadoEnterLocalidade, idDigitadoEnterSetorComercial, form, httpServletRequest, fachada);

        return retorno;
    }

    private void verificaExistenciaCodLocalidade(String idDigitadoEnterLocalidade, InformarLeituraRotaActionForm form,
            HttpServletRequest httpServletRequest, Fachada fachada) {

        // Verifica se o código da Localidade foi digitado
        if (idDigitadoEnterLocalidade != null && !idDigitadoEnterLocalidade.trim().equals("")
                && Integer.parseInt(idDigitadoEnterLocalidade) > 0) {

            // Recupera a localidade informada pelo usuário
            Localidade localidadeEncontrada = fachada.pesquisarLocalidadeDigitada(new Integer(idDigitadoEnterLocalidade));

            /*
             * Caso a localidade informada pelo usuário esteja cadastrada no
             * sistema Seta os dados da localidade no form Caso contrário seta
             * as informações da localidade para vazio e indica ao usuário que a
             * localidade não existe
             */
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
            InformarLeituraRotaActionForm form, HttpServletRequest httpServletRequest, Fachada fachada) {

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
                httpServletRequest.setAttribute("nomeCampo", "rota");

            } else {
                // o setor comercial não foi encontrado
                form.setCodigoSetorComercial("");
                httpServletRequest.setAttribute("idSetorComercialNaoEncontrada", "exception");
                form.setSetorComercialDescricao("SETOR COMERCIAL INEXISTENTE");
            }
        }
    }
}
