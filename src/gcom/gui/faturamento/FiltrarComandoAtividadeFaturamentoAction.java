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
package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.faturamento.FaturamentoGrupoCronogramaMensal;
import gcom.faturamento.FiltroFaturamentoAtividadeCronograma;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarComandoAtividadeFaturamentoAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("filtrarComandoAtividadeFaturamento");

        //Carrega a instancia da fachada
        Fachada fachada = Fachada.getInstancia();

        //Carrega o objeto sessao
        HttpSession sessao = httpServletRequest.getSession(false);
        
        
        //Limpando todos os objetos colocados na sessão
        //--------------------------------------------------------------------
        sessao.removeAttribute("dataCorrente");
        sessao.removeAttribute("exibirCampoVencimentoGrupo");
        sessao.removeAttribute("faturamentoAtividadeCronograma");
        sessao.removeAttribute("colecaoFaturamentoAtividadeCronogramaRota");
        sessao.removeAttribute("colecaoRotasSelecionadas");
        sessao.removeAttribute("colecaoRotasSelecionadasUsuario");
        sessao
                .removeAttribute("colecaoFaturamentoAtividadeCronogramaRotaUniao");
        sessao.removeAttribute("PesquisarActionForm");
        sessao.removeAttribute("InserirComandoAtividadeFaturamentoActionForm");
        sessao.removeAttribute("statusWizard");
        //--------------------------------------------------------------------
        
        
        FiltroFaturamentoAtividadeCronograma filtroFaturamentoAtividadeCronograma = new FiltroFaturamentoAtividadeCronograma();
        filtroFaturamentoAtividadeCronograma.setConsultaSemLimites(true);
        
        //filtroFaturamentoAtividadeCronograma.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupoCronogramaMensal.faturamentoGrupo.anoMesReferencia");
        filtroFaturamentoAtividadeCronograma.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupoCronogramaMensal.faturamentoGrupo");
        
        // Retorna uma lista de atividades de faturamento comandadas e ainda não
        // realizadas
        /*Collection colecaoAtividadesAtualizacao = fachada
                .buscarAtividadeComandadaNaoRealizada(filtroFaturamentoAtividadeCronograma);*/
        
		// 1º Passo - Pegar o total de registros através de um count da consulta que aparecerá na tela
		Integer totalRegistros = fachada
				.buscarAtividadeComandadaNaoRealizadaCount();

		// 2º Passo - Chamar a função de Paginação passando o total de registros
		retorno = this.controlarPaginacao(httpServletRequest, retorno,
				totalRegistros);

		// 3º Passo - Obter a coleção da consulta que aparecerá na tela passando o numero de paginas
		// da pesquisa que está no request
		Collection colecaoAtividadesAtualizacao = fachada
			.buscarAtividadeComandadaNaoRealizada((Integer) httpServletRequest
					.getAttribute("numeroPaginasPesquisa"));

        
        Iterator colecaoAtividadesAtualizacaoIt = colecaoAtividadesAtualizacao.iterator();
        Collection colecaoFaturamentoAtividadeCronograma = new Vector();
        Object[] arrayConteudoAtividade = new Object[6];
        FaturamentoAtividadeCronograma faturamentoAtividadeCronograma;
        FaturamentoAtividade faturamentoAtividade;
        FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal;
        Date comando, dataPrevista;
        
        while (colecaoAtividadesAtualizacaoIt.hasNext()){
        	arrayConteudoAtividade = (Object[]) colecaoAtividadesAtualizacaoIt.next();
        	
        	faturamentoAtividade = (FaturamentoAtividade) arrayConteudoAtividade[1];
        	faturamentoGrupoCronogramaMensal = (FaturamentoGrupoCronogramaMensal) arrayConteudoAtividade[4];
        	comando = (Date) arrayConteudoAtividade[2];
        	dataPrevista = (Date) arrayConteudoAtividade[3];
        	
        	faturamentoAtividadeCronograma = new  FaturamentoAtividadeCronograma();
        	faturamentoAtividadeCronograma.setId(new Integer(String.valueOf(arrayConteudoAtividade[0])));
        	faturamentoAtividadeCronograma.setFaturamentoAtividade(faturamentoAtividade);
        	faturamentoAtividadeCronograma.setComando(comando);
        	faturamentoAtividadeCronograma.setDataPrevista(dataPrevista);
        	faturamentoAtividadeCronograma.setFaturamentoGrupoCronogramaMensal(faturamentoGrupoCronogramaMensal);
        	colecaoFaturamentoAtividadeCronograma.add(faturamentoAtividadeCronograma);
        }

        sessao.setAttribute("colecaoAtividadesAtualizacao",
        		colecaoFaturamentoAtividadeCronograma);
        
        
        return retorno;
    }

}
