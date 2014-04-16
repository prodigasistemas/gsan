package gcom.gui.util;

import gcom.util.GerenciadorPaginas;
import gcom.util.Pagina;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerenciadorProcessoAction extends Action {
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws IOException,
            ServletException {
        String acao = null;
        String numeroPagina = null;
        Pagina paginaDestino = null;
        Pagina paginaCorrente = null;
        ActionForward retorno = null;

        HttpSession sessao = httpServletRequest.getSession(false);

        GerenciadorPaginas gerenciadorPaginas = (GerenciadorPaginas) sessao
                .getAttribute("gerenciadorPaginas");

        paginaCorrente = gerenciadorPaginas.getPaginaCorrente();

        if (paginaCorrente == null) {
            paginaDestino = gerenciadorPaginas.setPaginaCorrentePeloIndice(1);
            retorno = actionMapping.findForward(paginaDestino.getUriInicial());
        } else {
            acao = httpServletRequest.getParameter("acao");
            numeroPagina = httpServletRequest.getParameter("numeroPagina");
            if (acao == null) {
                paginaDestino = gerenciadorPaginas
                        .setPaginaCorrentePeloIndice(Integer
                                .parseInt(numeroPagina));
                retorno = actionMapping.findForward(paginaDestino
                        .getUriInicial());
            } else {
                if (acao.equals("executar")
                        && paginaCorrente.getIndex() == gerenciadorPaginas
                                .getPaginas().size()) {
                    retorno = actionMapping.findForward(gerenciadorPaginas
                            .getActionFinal());
                } else if (acao.equals("voltar")
                        && numeroPagina.equals(Integer.toString(paginaCorrente
                                .getIndex() - 1))
                        || acao.equals("avancar")
                        && numeroPagina.equals(Integer.toString(paginaCorrente
                                .getIndex() + 1))) {
                    if (paginaCorrente.getAtributosPagina().size() > 0) {
                        paginaCorrente.setHint(gerenciadorPaginas.generateHint(
                                httpServletRequest, paginaCorrente));
                    }
                    paginaDestino = gerenciadorPaginas
                            .setPaginaCorrentePeloIndice(Integer
                                    .parseInt(numeroPagina));
                    retorno = actionMapping.findForward(paginaDestino
                            .getUriInicial());
                } else {
                    paginaDestino = gerenciadorPaginas
                            .setPaginaCorrentePeloIndice(paginaCorrente
                                    .getIndex());
                    retorno = actionMapping.findForward(paginaDestino
                            .getUriInicial());
                }
            }
        }

        return retorno;
    }
}
