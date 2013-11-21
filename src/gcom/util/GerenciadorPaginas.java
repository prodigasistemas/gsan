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
package gcom.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Tem por objetivo o gerenciamento das funcionalidades qualificadas como
 * processos, bem como o controle de links, hints e quantidade de páginas
 * envolvidas num dado processo
 * 
 * @author Cesar
 */
public class GerenciadorPaginas {
    private LinkedList paginas;

    private String actionInicial;

    private String actionFinal;

    /**
     * Construtor da classe GuiGerenciadorPrecessos
     */
    public GerenciadorPaginas() {
        paginas = new LinkedList();
    }

    /**
     * Adds a feature to the Page attribute of the GuiGerenciadorPrecessos
     * object
     * 
     * @param descricao
     *            The feature to be added to the Page attribute
     * @param uriInicial
     *            Descrição do parâmetro
     * @param uriFinal
     *            Descrição do parâmetro
     * @param imagemAtiva
     *            The feature to be added to the Page attribute
     * @param imagemInativa
     *            The feature to be added to the Page attribute
     * @return Descrição do retorno
     */
    public Pagina adicionaPagina(String descricao, String uriInicial,
            String uriFinal, String imagemAtiva, String imagemInativa) {
        paginas.add(new Pagina(descricao, uriInicial, uriFinal, imagemAtiva,
                imagemInativa));

        Pagina pagina = (Pagina) paginas.getLast();

        pagina.setIndex(paginas.size());
        return pagina;
    }

    /**
     * Retorna o valor de paginaCorrente
     * 
     * @return O valor de paginaCorrente
     */
    public Pagina getPaginaCorrente() {
        Pagina retorno = null;

        ListIterator listIterator = paginas.listIterator();

        while (listIterator.hasNext()) {
            Pagina pagina = (Pagina) listIterator.next();

            if (pagina.isPaginaCorrente()) {
                retorno = pagina;
            }
        }
        return retorno;
    }

    /**
     * Retorna o valor de paginaSeguinte
     * 
     * @return O valor de paginaSeguinte
     */
    public Pagina getPaginaSeguinte() {
        Pagina paginaCorrente = null;
        Pagina paginaSeguinte = null;

        paginaCorrente = getPaginaCorrente();

        if (paginaCorrente == null) {
            paginaSeguinte = setPaginaCorrentePeloIndice(0);
        } else {
            paginaSeguinte = getPaginaPeloIndice(paginaCorrente.getIndex() + 1);
        }

        return paginaSeguinte;
    }

    /**
     * Retorna o valor de paginaAnterior
     * 
     * @return O valor de paginaAnterior
     */
    public Pagina getPaginaAnterior() {
        //boolean existePaginaCorrente = false;
        Pagina paginaCorrente = null;
        Pagina paginaAnterior = null;

        paginaCorrente = getPaginaCorrente();

        if (paginaCorrente == null) {
            paginaAnterior = setPaginaCorrentePeloIndice(0);
        } else {
            paginaAnterior = getPaginaPeloIndice(paginaCorrente.getIndex() - 1);
        }

        return paginaAnterior;
    }

    /**
     * Construtor da classe setPaginaPeloIndice
     * 
     * @param indice
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public Pagina setPaginaCorrentePeloIndice(int indice) {
        Pagina paginaCorrenteNova = null;
        Pagina paginaCorrenteAtual = null;

        paginaCorrenteAtual = getPaginaCorrente();

        if (paginaCorrenteAtual != null) {
            if (indice > paginaCorrenteAtual.getIndex()) {
                paginaCorrenteNova = getPaginaSeguinte();
                paginaCorrenteAtual.setPaginaCorrente(false);
                paginaCorrenteAtual.setPaginaPreenchida(true);
            } else if (indice < paginaCorrenteAtual.getIndex()) {
                for (int i = indice; i < paginas.size(); i++) {
                    paginaCorrenteAtual = ((Pagina) paginas.get(i));
                    paginaCorrenteAtual.setPaginaCorrente(false);
                    paginaCorrenteAtual.setPaginaPreenchida(false);
                }
                paginaCorrenteNova = ((Pagina) paginas.get(indice - 1));
            } else if (indice == paginaCorrenteAtual.getIndex()) {
                paginaCorrenteNova = paginaCorrenteAtual;
            }
        } else {
            paginaCorrenteNova = ((Pagina) paginas.get(indice - 1));
        }
        paginaCorrenteNova.setPaginaCorrente(true);
        paginaCorrenteNova.setPaginaPreenchida(false);

        return paginaCorrenteNova;
    }

    /**
     * Retorna o valor de paginaPeloIndice
     * 
     * @param indice
     *            Descrição do parâmetro
     * @return O valor de paginaPeloIndice
     */
    public Pagina getPaginaPeloIndice(int indice) {
        return (Pagina) this.paginas.get(indice - 1);
    }

    /**
     * Seta o valor de paginaInativa
     * 
     * @param indice
     *            O novo valor de paginaInativa
     */
    public void setPaginaInativa(int indice) {
        Pagina pagina = ((Pagina) paginas.get(indice - 1));

        pagina.setPaginaInativa(true);
    }

    /**
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     */
    public Collection getPaginas() {
        return paginas;
    }

    /**
     * < <Descrição do método>>
     * 
     * @param request
     *            Descrição do parâmetro
     * @param pagina
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public String generateHint(HttpServletRequest request, Pagina pagina) {
        String hint = "";
        //int indexAtributoPagina = 0;
        AtributoPagina atributoPagina = null;
        String descricaoAtributoPagina = null;
        String descricaoAtributoRequest = null;
        Map mapRequest = request.getParameterMap();

        Iterator iterator = pagina.getAtributosPagina().iterator();

        while (iterator.hasNext()) {
            atributoPagina = (AtributoPagina) iterator.next();
            descricaoAtributoPagina = atributoPagina.getDescricao();
            if (mapRequest.get(atributoPagina.getId()) != null) {
                descricaoAtributoRequest = ((String[]) mapRequest
                        .get(atributoPagina.getId()))[0];
                atributoPagina.setTamanho(new Integer(descricaoAtributoRequest
                        .length()));
                hint += "<b>" + descricaoAtributoPagina + ":</b> "
                        + descricaoAtributoRequest + "<br>";
            }
        }
        hint = hint.substring(0, hint.length() - 4);
        return hint;
    }

    /**
     * < <Descrição do método>>
     * 
     * @param indicePagina
     *            Descrição do parâmetro
     */
    public void clearHint(int indicePagina) {
        this.getPaginaPeloIndice(indicePagina);
    }

    /**
     * Retorna o valor de size
     * 
     * @return O valor de size
     */
    public Integer getSize() {
        return new Integer(this.paginas.size());
    }

    /**
     * Retorna o valor de actionFinal
     * 
     * @return O valor de actionFinal
     */
    public String getActionFinal() {
        return actionFinal;
    }

    /**
     * Retorna o valor de actionInicial
     * 
     * @return O valor de actionInicial
     */
    public String getActionInicial() {
        return actionInicial;
    }

    /**
     * Seta o valor de actionFinal
     * 
     * @param actionFinal
     *            O novo valor de actionFinal
     */
    public void setActionFinal(String actionFinal) {
        this.actionFinal = actionFinal;
    }

    /**
     * Seta o valor de actionInicial
     * 
     * @param actionInicial
     *            O novo valor de actionInicial
     */
    public void setActionInicial(String actionInicial) {
        this.actionInicial = actionInicial;
    }

}
