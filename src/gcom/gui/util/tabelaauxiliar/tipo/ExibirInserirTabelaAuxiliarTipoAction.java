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
import gcom.util.tabelaauxiliar.FiltroTabelaAuxiliar;
import gcom.util.tabelaauxiliar.tipo.TabelaAuxiliarTipo;

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
public class ExibirInserirTabelaAuxiliarTipoAction extends GcomAction {
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

        //Prepara o retorno da Ação
        ActionForward retorno = actionMapping
                .findForward("inserirTabelaAuxiliarTipo");

        Fachada fachada = Fachada.getInstancia();

        //Pega o parametro passado no request
        //String tela = (String) httpServletRequest.getParameter("tela");

        //Declaração de objetos e tipos primitivos
        String titulo = null;
        String tituloTipo = null;
        int tamMaxCampoDescricao = 40;
        String funcionalidadeTabelaAuxiliarTipoInserir = null;
        Collection tipos = null;
        TabelaAuxiliarTipo tabelaAuxiliarTipo = null;
        FiltroTabelaAuxiliar filtroTabelaAuxiliar = null;
        String pacoteNomeObjeto = null;

        //********BLOCO DE CÓDIGO PARA DEFINIÇÃO DOS CADASTROS PERTENCENTES A
        // INSERIR TABELA AUXILIAR TIPO******//
        //        Para serem incluidos novos cadastros com código, descrição e tipo
        // basta apenas cria um novo
        //        if (condicional) semelhante ao exemplo abaixo, informando apenas os
        // dados relativos
        //        ao objeto desejado.

        //Identifica a string do objeto passado no get do request
        /*
         * if (tela.equals("bacia")) { //Título a ser exido nas páginas titulo =
         * "Bacia"; tituloTipo = "Tipo Pavimento Rua"; //Cria o filtro referente
         * ao objeto tipo filtroTabelaAuxiliar = new FiltroTabelaAuxiliar();
         * //Cria o objeto principal Bacia bacia = new Bacia(); //Cria o objeto
         * tipo TipoPavimentoRua tipoPavimentoRua = new TipoPavimentoRua();
         * //Associa o objeto tabela auxiliar tipo ao tipo criado
         * tabelaAuxiliarTipo = bacia; //Pega o path do pacote mais o nome da
         * classe pacoteNomeObjeto = tipoPavimentoRua.getClass().getName();
         * //Define o link a ser exibido na página de sucesso do inserir
         * funcionalidadeTabelaAuxiliarTipoInserir =
         * Funcionalidade.TABELA_AUXILIAR_TIPO_INSERIR +
         * Funcionalidade.TELA_BACIA; //Obtém o tamanho da propriedade da classe
         * de acordo com length do mapeamento tamMaxCampoDescricao =
         * HibernateUtil.getColumnSize(Bacia.class,"descricao"); }
         */
        //********FIM DO BLOCO DE CÓDIGO*******//
        tipos = fachada.pesquisarTabelaAuxiliar(filtroTabelaAuxiliar,
                pacoteNomeObjeto);

        //Caso a coleção esteja vazia, indica erro inesperado
        if (tipos == null || tipos.isEmpty()) {
            throw new ActionServletException("atencao.naocadastrado");
        }

        //Cria a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //tempo da sessão
        sessao.setMaxInactiveInterval(1000);
        //Adiciona os objetos na sessão
        sessao.setAttribute("tabelaAuxiliarTipo", tabelaAuxiliarTipo);
        sessao.setAttribute("funcionalidadeTabelaAuxiliarTipoInserir",
                funcionalidadeTabelaAuxiliarTipoInserir);
        sessao.setAttribute("titulo", titulo);
        sessao.setAttribute("tituloTipo", tituloTipo);
        //Adiciona o objeto no request
        httpServletRequest.setAttribute("tamMaxCampoDescricao", new Integer(
                tamMaxCampoDescricao));
        httpServletRequest.setAttribute("tipos", tipos);

        return retorno;
    }

}
