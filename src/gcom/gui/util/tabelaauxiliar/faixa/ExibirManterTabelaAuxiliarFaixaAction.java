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
package gcom.gui.util.tabelaauxiliar.faixa;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.tabelaauxiliar.faixa.FiltroTabelaAuxiliarFaixa;
import gcom.util.tabelaauxiliar.faixa.TabelaAuxiliarFaixa;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirManterTabelaAuxiliarFaixaAction extends GcomAction {
    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("manterTabelaAuxiliarFaixa");

        //Obtém a instancia da fachada
        Fachada fachada = Fachada.getInstancia();

        //Cria a coleção de tabelas auxiliares
        Collection tabelasAuxiliaresFaixas = null;

        //Obtém a instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //Obtém o nome da tela passado no get do request
        String tela = httpServletRequest.getParameter("tela");

        //Declaração de objetos e tipos primitivos
        String titulo = null;
//        TabelaAuxiliarFaixa tabelaAuxiliarFaixa = null;
        String pacoteNomeObjeto = null;
        String funcionalidadeTabelaAuxiliarFaixaManter = null;
        int tamMaxCampoFaixaInicial = 3;
        int tamMaxCampoFaixaFinal = 3;

        //Verifica se o exibir manter foi chamado da tela de filtro
        if (httpServletRequest.getAttribute("filtroTabelaAuxiliarFaixa") != null) {
            tela = (String) sessao.getAttribute("tela");
        }

        //********BLOCO DE CÓDIGO PARA DEFINIÇÃO DOS CADASTROS PERTENCENTES A
        // INSERIR TABELA FAIXA******//
        //        Para serem incluidos novos cadastros com código, faixa inicial e
        // faixa final basta apenas cria um novo
        //        if (condicional) semelhante ao exemplo abaixo, informando apenas os
        // dados relativos
        //        ao objeto desejado.

        //---AREA CONSTRUIDA
        //Identifica a string do objeto passado no get do request
        /*
         * if (tela.equals("areaConstruida")) { //Título a ser exido nas páginas
         * titulo = "Área Construída"; //Cria o objeto AreaConstruida
         * areaConstruida = new AreaConstruida(); //Associa o objeto tabela
         * auxiliar ao tipo criado tabelaAuxiliarFaixa = areaConstruida; //Obtém
         * o path do pacote mais o tipo do objeto pacoteNomeObjeto =
         * tabelaAuxiliarFaixa.getClass().getName(); //Define o link a ser
         * exibido na página de sucesso funcionalidadeTabelaAuxiliarFaixaManter =
         * Funcionalidade.TABELA_AUXILIAR_FAIXA_MANTER +
         * Funcionalidade.TELA_AREA_CONSTRUIDA; //Obtém o tamanho da propriedade
         * da classe de acordo com length do mapeamento tamMaxCampoFaixaInicial =
         * HibernateUtil.getColumnSize(AreaConstruida.class,"faixaInicial");
         * tamMaxCampoFaixaFinal =
         * HibernateUtil.getColumnSize(AreaConstruida.class,"faixaFinal"); }
         */
        //********FIM DO BLOCO DE CÓDIGO*******//
        //Parte da verificação do filtro
        FiltroTabelaAuxiliarFaixa filtroTabelaAuxiliarFaixa = null;

        //Verifica se o filtro foi informado pela página de filtragem da tabela
        // auxiliar faixa
        if (httpServletRequest.getAttribute("filtroTabelaAuxiliarFaixa") != null) {
            filtroTabelaAuxiliarFaixa = (FiltroTabelaAuxiliarFaixa) httpServletRequest
                    .getAttribute("filtroTabelaAuxiliarFaixa");
        } else {
            //Caso o exibirManterTabelaFaixa não tenha passado por algum
            // esquema de filtro,
            //a quantidade de registros é verificada para avaliar a necessidade
            // de filtragem
            filtroTabelaAuxiliarFaixa = new FiltroTabelaAuxiliarFaixa();

            if (fachada.registroMaximo(TabelaAuxiliarFaixa.class) > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_MANUTENCAO) {
                //Se o limite de registros foi atingido, a página de filtragem
                // é chamada
                retorno = actionMapping
                        .findForward("filtrarTabelaAuxiliarFaixa");
                sessao.setAttribute("tela", tela);
            }
        }

        //A pesquisa de tabelas auxiliares só será feita se o forward estiver
        // direcionado
        //para a página de manterTabelaAuxiliar
        if (retorno.getName().equalsIgnoreCase("manterTabelaAuxiliarFaixa")) {
            //Seta a ordenação desejada do filtro
            filtroTabelaAuxiliarFaixa
                    .setCampoOrderBy(FiltroTabelaAuxiliarFaixa.ID);
            //Pesquisa de tabelas auxiliares
            tabelasAuxiliaresFaixas = fachada.pesquisarTabelaAuxiliar(
                    filtroTabelaAuxiliarFaixa, pacoteNomeObjeto);

            if (tabelasAuxiliaresFaixas == null
                    || tabelasAuxiliaresFaixas.isEmpty()) {
                //Nenhum atividade cadastrado
                throw new ActionServletException("atencao.naocadastrado", null,
                        titulo);
            }

            //Verifica o numero de objetos retornados
            if (tabelasAuxiliaresFaixas.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_MANUTENCAO) {
                throw new ActionServletException(
                        "atencao.pesquisa.muitosregistros");
            }

            //A coleção fica na sessão devido ao esquema de paginação
            sessao.setAttribute("tabelasAuxiliaresFaixas",
                    tabelasAuxiliaresFaixas);
            //Envia o path do pacote na sessão
            sessao.setAttribute("pacoteNomeObjeto", pacoteNomeObjeto);
        }

        //Envia os objetos na sessão
        sessao.setAttribute("titulo", titulo);
        sessao.setAttribute("funcionalidadeTabelaAuxiliarFaixaManter",
                funcionalidadeTabelaAuxiliarFaixaManter);
        sessao.setAttribute("tamMaxCampoFaixaInicial", new Integer(
                tamMaxCampoFaixaInicial));
        sessao.setAttribute("tamMaxCampoFaixaFinal", new Integer(
                tamMaxCampoFaixaFinal));

        //Devolve o mapeamento de retorno
        return retorno;
    }
}
