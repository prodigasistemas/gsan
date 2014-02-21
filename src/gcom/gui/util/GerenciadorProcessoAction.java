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
