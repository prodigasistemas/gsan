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
package gcom.gui.util.tabelaauxiliar.tipo;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.tabelaauxiliar.TabelaAuxiliar;
import gcom.util.tabelaauxiliar.tipo.FiltroTabelaAuxiliarTipo;

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
public class ExibirManterTabelaAuxiliarTipoAction extends GcomAction {
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
                .findForward("manterTabelaAuxiliarTipo");

        //Obtém a instancia da fachada
        Fachada fachada = Fachada.getInstancia();

        //Cria a coleção de tabelas auxiliares
        Collection tabelasAuxiliaresTipos = null;

        //Obtém a instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //Obtém o nome da tela passado no get do request
        String tela = httpServletRequest.getParameter("tela");

        //Declaração de objetos e tipos primitivos
        String titulo = null;
        String tituloTipo = null;
        TabelaAuxiliar tabelaAuxiliarTipo = null;
        String pacoteNomeObjeto = null;
        String funcionalidadeTabelaAuxiliarTipoManter = null;
        Object objetoTipo = null;
        //Inicializa a variavel que será usada em uma função para pegar a
        // substring
        String pacoteNomeObjetoTipo = null;

        //Verifica se o exibir manter foi chamado da tela de filtro
        if (httpServletRequest.getAttribute("filtroTabelaAuxiliarTipo") != null) {
            tela = (String) sessao.getAttribute("tela");
        }

        //********BLOCO DE CÓDIGO PARA DEFINIÇÃO DOS CADASTROS PERTENCENTES A
        // INSERIR TABELA AUXILIAR TIPO******//
        //        Para serem incluidos novos cadastros com código, descrição e tipo
        // basta apenas cria um novo
        //        if (condicional) semelhante ao exemplo abaixo, informando apenas os
        // dados relativos
        //        ao objeto desejado.

        //---BACIA
        //Identifica a string do objeto passado no get do request
        /*
         * if (tela.equals("bacia")) { //Título a ser exido nas páginas titulo =
         * "Bacia"; tituloTipo = "Tipo Pavimento Rua"; //Cria o objeto principal
         * Bacia bacia = new Bacia(); //Cria o objeto tipo TipoPavimentoRua
         * tipoPavimentoRua = new TipoPavimentoRua(); //Associa o objeto tabela
         * auxiliar ao tipo criado tabelaAuxiliarTipo = bacia; //Associa o
         * objeto tipo ao tipo criado objetoTipo = tipoPavimentoRua; //Pega o
         * path do pacote mais o nome da classe pacoteNomeObjeto =
         * bacia.getClass().getName(); //Define o link a ser exibido na página
         * de sucesso do inserir funcionalidadeTabelaAuxiliarTipoManter =
         * Funcionalidade.TABELA_AUXILIAR_TIPO_MANTER +
         * Funcionalidade.TELA_BACIA; }
         */
        //********FIM DO BLOCO DE CÓDIGO*******//
        //Parte da verificação do filtro
        FiltroTabelaAuxiliarTipo filtroTabelaAuxiliarTipo = null;
        //Inicializa a variável tipo
        //Integer tipo = null;

        //Verifica se o filtro foi informado pela página de filtragem da tabela
        // auxiliar abreviada
        if (httpServletRequest.getAttribute("filtroTabelaAuxiliarTipo") != null) {
            filtroTabelaAuxiliarTipo = (FiltroTabelaAuxiliarTipo) httpServletRequest
                    .getAttribute("filtroTabelaAuxiliarTipo");
            //Pega pelo request a variavel tipo para saber se veio null ou não
        } else {
            //Caso o exibirManterTabelaAuxiliar não tenha passado por algum
            // esquema de filtro,
            //a quantidade de registros é verificada para avaliar a necessidade
            // de filtragem
            filtroTabelaAuxiliarTipo = new FiltroTabelaAuxiliarTipo();

            if (fachada.registroMaximo(TabelaAuxiliar.class) > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_MANUTENCAO) {
                //Se o limite de registros foi atingido, a página de filtragem
                // é chamada
                retorno = actionMapping
                        .findForward("filtrarTabelaAuxiliarTipo");
                sessao.setAttribute("tela", tela);
                //A variável recebe apenas o nome da classe do objeto
                pacoteNomeObjetoTipo = getNomeClasse(objetoTipo);
                //Envia o objeto na sessão
                sessao.setAttribute("tipo", pacoteNomeObjetoTipo);
            }
        }

        //A pesquisa de tabelas auxiliares só será feita se o forward estiver
        // direcionado
        //para a página de manterTabelaAuxiliar
        if (retorno.getName().equalsIgnoreCase("manterTabelaAuxiliarTipo")) {
            //Seta a ordenação desejada do filtro
            filtroTabelaAuxiliarTipo
                    .setCampoOrderBy(FiltroTabelaAuxiliarTipo.ID);

            //Pesquisa de tabelas auxiliares
            tabelasAuxiliaresTipos = fachada.pesquisarTabelaAuxiliar(
                    filtroTabelaAuxiliarTipo, pacoteNomeObjeto);

            if (tabelasAuxiliaresTipos == null
                    || tabelasAuxiliaresTipos.isEmpty()) {
                //Nenhum atividade cadastrado
                throw new ActionServletException("atencao.naocadastrado");
            }

            //Verifica o numero de objetos retornados
            if (tabelasAuxiliaresTipos.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_MANUTENCAO) {
                throw new ActionServletException("atencao.pesquisa.muitosregistros");
            }

            //A coleção fica na sessão devido ao esquema de paginação
            sessao.setAttribute("tabelasAuxiliaresTipos",
                    tabelasAuxiliaresTipos);
            //Envia o path do pacote na sessão
            sessao.setAttribute("pacoteNomeObjeto", pacoteNomeObjeto);

        }

        //Envia os objetos na sessão
        sessao.setAttribute("titulo", titulo);
        sessao.setAttribute("tipoTitulo", tituloTipo);
        sessao.setAttribute("funcionalidadeTabelaAuxiliarTipoManter",
                funcionalidadeTabelaAuxiliarTipoManter);
        sessao.setAttribute("tabelaAuxiliarTipo", tabelaAuxiliarTipo);

        //Devolve o mapeamento de retorno
        return retorno;
    }
}
