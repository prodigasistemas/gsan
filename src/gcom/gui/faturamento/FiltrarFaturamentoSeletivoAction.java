package gcom.gui.faturamento;

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

public class FiltrarFaturamentoSeletivoAction extends GcomAction {


    @Override
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("filtrarFaturamentoSeletivo");

        FaturamentoSeletivoActionForm form = (FaturamentoSeletivoActionForm) actionForm;

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

        String idDigitadoEnterLocalidade = form.getIdLocalidade();
        if (idDigitadoEnterLocalidade != null && !idDigitadoEnterLocalidade.equalsIgnoreCase("") && Util.validarValorNaoNumerico(idDigitadoEnterLocalidade)) {
            httpServletRequest.setAttribute("nomeCampo", "idLocalidade");
            throw new ActionServletException("atencao.nao.numerico", null, "Localidade ");
        }

        verificaExistenciaCodLocalidade(idDigitadoEnterLocalidade, form, httpServletRequest, fachada);

        String idDigitadoEnterSetorComercial = form.getCodigoSetorComercial();
        if (idDigitadoEnterSetorComercial != null && !idDigitadoEnterSetorComercial.equalsIgnoreCase("")  && Util.validarValorNaoNumerico(idDigitadoEnterSetorComercial)) {
            httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercial");
            throw new ActionServletException("atencao.nao.numerico", null, "Setor Comercial ");
        }

        if (httpServletRequest.getParameter("tipoConsulta") != null && !httpServletRequest.getParameter("tipoConsulta").equals("")) {

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

    private void verificaExistenciaCodLocalidade(String idDigitadoEnterLocalidade, FaturamentoSeletivoActionForm form,
            HttpServletRequest httpServletRequest, Fachada fachada) {

        if (idDigitadoEnterLocalidade != null && !idDigitadoEnterLocalidade.trim().equals("")  && Integer.parseInt(idDigitadoEnterLocalidade) > 0) {

            Localidade localidadeEncontrada = fachada.pesquisarLocalidadeDigitada(new Integer(idDigitadoEnterLocalidade));

            if (localidadeEncontrada != null) {
                form.setIdLocalidade("" + (localidadeEncontrada.getId()));
                form.setLocalidadeDescricao(localidadeEncontrada.getDescricao());
                httpServletRequest.setAttribute("idLocalidadeNaoEncontrada", "true");
                httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercial");

            } else {
                form.setIdLocalidade("");
                httpServletRequest.setAttribute("idLocalidadeNaoEncontrada", "exception");
                form.setLocalidadeDescricao("LOCALIDADE INEXISTENTE");
            }
        }
    }

    @SuppressWarnings("unchecked")
	private void verificaExistenciaCodSetorComercial(String idDigitadoEnterLocalidade, String idDigitadoEnterSetorComercial,
    		FaturamentoSeletivoActionForm form, HttpServletRequest httpServletRequest, Fachada fachada) {

        if ((idDigitadoEnterSetorComercial != null && !idDigitadoEnterSetorComercial.toString().trim().equalsIgnoreCase(""))
                && (idDigitadoEnterLocalidade != null && !idDigitadoEnterLocalidade.toString().trim().equalsIgnoreCase(""))) {

            FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
            if (idDigitadoEnterLocalidade != null && !idDigitadoEnterLocalidade.toString().trim().equalsIgnoreCase("")) {

                filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, new Integer(idDigitadoEnterLocalidade)));
            }

            filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,new Integer(idDigitadoEnterSetorComercial)));

            Collection<SetorComercial> setorComerciais = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

            if (setorComerciais != null && !setorComerciais.isEmpty()) {
                SetorComercial setorComercialEncontrado = (SetorComercial) Util.retonarObjetoDeColecao(setorComerciais);
                form.setCodigoSetorComercial("" + (setorComercialEncontrado.getCodigo()));
                form.setSetorComercialDescricao(setorComercialEncontrado.getDescricao());
                httpServletRequest.setAttribute("idSetorComercialNaoEncontrada", "true");
                httpServletRequest.setAttribute("nomeCampo", "rota");

            } else {
                form.setCodigoSetorComercial("");
                httpServletRequest.setAttribute("idSetorComercialNaoEncontrada", "exception");
                form.setSetorComercialDescricao("SETOR COMERCIAL INEXISTENTE");
            }
        }
    }

}
