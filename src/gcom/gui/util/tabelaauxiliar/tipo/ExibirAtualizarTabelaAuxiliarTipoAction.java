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
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.tipo.FiltroTabelaAuxiliarTipo;
import gcom.util.tabelaauxiliar.tipo.TabelaAuxiliarTipo;

import java.util.Collection;
import java.util.Iterator;

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
public class ExibirAtualizarTabelaAuxiliarTipoAction extends GcomAction {
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
                .findForward("atualizarTabelaAuxiliarTipo");

        //Obtém a instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //Pega o actionform associado
        ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

        //Código da tabela auxiliar a ser atualizada
        String id = manutencaoRegistroActionForm.getIdRegistroAtualizacao();

        //Cria o filtro para atividade
        FiltroTabelaAuxiliarTipo filtroTabelaAuxiliarTipo = new FiltroTabelaAuxiliarTipo();

        //String com path do pacote mais o nome do objeto
        String pacoteNomeObjeto = (String) sessao
                .getAttribute("pacoteNomeObjeto");

        //Declaração de variáveis e objetos
        //String tituloTipo = null;
        //String titulo = null;
        //Object objetoTipo = null;
        String pacoteNomeObjetoTipo = null;
        //String funcionalidadeTabelaAuxiliarTipoManter = null;
        int tamMaxCampoDescricao = 40;

        //Recebe valor do objeto envia pela sessão
        TabelaAuxiliarTipo tabelaAuxiliarTipo = (TabelaAuxiliarTipo) sessao
                .getAttribute("tabelaAuxiliarTipo");

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
         * if (tabelaAuxiliarTipo instanceof Bacia) { //Cria o objeto pricipal
         * Bacia bacia = new Bacia(); //Cria o objeto tipo TipoPavimentoRua
         * tipoPavimentoRua = new TipoPavimentoRua(); //Pega o path do pacote
         * mais o tipo do objeto pacoteNomeObjetoTipo =
         * tipoPavimentoRua.getClass().getName(); //Título da página titulo =
         * "Bacia"; tituloTipo = "Tipo Pavimento Rua"; //Associa o objeto tabela
         * auxiliar ao tipo criado tabelaAuxiliarTipo = bacia; //Associa o
         * objeto tipo ao tipo criado objetoTipo = tipoPavimentoRua; //Define o
         * link a ser exibido na página de sucesso
         * funcionalidadeTabelaAuxiliarTipoManter =
         * Funcionalidade.TABELA_AUXILIAR_TIPO_MANTER +
         * Funcionalidade.TELA_BACIA; //Obtém o tamanho da propriedade da classe
         * de acordo com length do mapeamento tamMaxCampoDescricao =
         * HibernateUtil.getColumnSize(Bacia.class,"descricao"); }
         */
        //********FIM DO BLOCO DE CÓDIGO*******//
        //Pesquisa o objeto de acordo com o filtro passado
        Collection colecaoTipos = fachada.pesquisarTabelaAuxiliar(
                filtroTabelaAuxiliarTipo, pacoteNomeObjetoTipo);

        //Caso a coleção esteja vazia, indica erro inesperado
        if (colecaoTipos == null || colecaoTipos.isEmpty()) {
            throw new ActionServletException("erro.sistema");
        }
        //Manda a coleção de tipos da tabela auxiliar na sessão
        httpServletRequest.setAttribute("colecaoTipos", colecaoTipos);

        //Adiciona o parâmetro no filtro
        filtroTabelaAuxiliarTipo.adicionarParametro(new ParametroSimples(
                FiltroTabelaAuxiliarTipo.ID, id));

        //Pesquisa a tabela auxiliar no sistema
        Collection tabelasAuxiliaresTipos = fachada.pesquisarTabelaAuxiliar(
                filtroTabelaAuxiliarTipo, pacoteNomeObjeto);

        //Caso a coleção esteja vazia, indica erro inesperado
        if (tabelasAuxiliaresTipos == null || tabelasAuxiliaresTipos.isEmpty()) {
            throw new ActionServletException("erro.sistema");
        }

        Iterator iteratorTabelaAuxiliarTipo = tabelasAuxiliaresTipos.iterator();

        //A tabela auxiliar tipo que será atualizada
        tabelaAuxiliarTipo = (TabelaAuxiliarTipo) iteratorTabelaAuxiliarTipo
                .next();

        //Manda os objetos na sessão
        sessao.setAttribute("tabelaAuxiliarTipo", tabelaAuxiliarTipo);
        httpServletRequest.setAttribute("tipo", tabelaAuxiliarTipo.getIdTipo());
        httpServletRequest.setAttribute("tamMaxCampoDescricao", new Integer(
                tamMaxCampoDescricao));

        //Devolve o mapeamento de retorno
        return retorno;
    }

}
