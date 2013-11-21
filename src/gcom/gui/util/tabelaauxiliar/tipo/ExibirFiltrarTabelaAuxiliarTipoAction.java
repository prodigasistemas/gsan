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
import gcom.gui.GcomAction;
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
public class ExibirFiltrarTabelaAuxiliarTipoAction extends GcomAction {
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

        ActionForward retorno = actionMapping
                .findForward("filtrarTabelaAuxiliarTipo");

        //Obtém a sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        //Obtém a fachada
        Fachada fachada = Fachada.getInstancia();

        //Declaração de variáveis e objetos
        Collection tipos = null;
        FiltroTabelaAuxiliarTipo filtroTabelaAuxiliarTipo = new FiltroTabelaAuxiliarTipo();
        String pacoteNomeObjeto = null;
        String tituloTipo = null;
        String titulo = null;
        int tamMaxCampoDescricao = 40;

        //Recebe valor do objeto envia pela sessão
       // TabelaAuxiliarTipo tabelaAuxiliarTipo = (TabelaAuxiliarTipo) sessao
        //        .getAttribute("tabelaAuxiliarTipo");

        //********BLOCO DE CÓDIGO PARA DEFINIÇÃO DOS CADASTROS PERTENCENTES A
        // INSERIR TABELA AUXILIAR******//
        //        Para serem incluidos novos cadastros com código e descrição basta
        // apenas cria um novo
        //        if (condicional) semelhante ao exemplo abaixo, informando apenas os
        // dados relativos
        //        ao objeto desejado.

        //---BACIA
        //Identifica a string do objeto passado no get do request
        /*
         * if (tabelaAuxiliarTipo instanceof Bacia) { //Cria o objeto tipo
         * TipoPavimentoRua tipoPavimentoRua = new TipoPavimentoRua(); //Pega o
         * path do objeto mais o tipo pacoteNomeObjeto =
         * tipoPavimentoRua.getClass().getName(); //Título da tela titulo =
         * "Bacia"; tituloTipo = "Tipo Pavimento Rua"; //Obtém o tamanho da
         * propriedade da classe de acordo com length do mapeamento
         * tamMaxCampoDescricao =
         * HibernateUtil.getColumnSize(Bacia.class,"descricao"); }
         */
        //********FIM DO BLOCO DE CÓDIGO*******//
        //Pesquisa o objeto de acordo com o filtro passado
        tipos = fachada.pesquisarTabelaAuxiliar(filtroTabelaAuxiliarTipo,
                pacoteNomeObjeto);
        //Adiciona o objeto no request
        httpServletRequest.setAttribute("tamMaxCampoDescricao", new Integer(
                tamMaxCampoDescricao));
        //Envia os objetos na sessão
        sessao.setAttribute("titulo", titulo);
        sessao.setAttribute("tituloTipo", tituloTipo);
        sessao.setAttribute("tipos", tipos);

        return retorno;
    }

}
