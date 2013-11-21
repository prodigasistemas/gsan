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
package gcom.gui.faturamento.conta;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.financeiro.FinanciamentoTipo;

import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ExibirAdicionarDebitoCobradoContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirAdicionarDebitoCobradoConta");
        
        HttpSession sessao = httpServletRequest.getSession(false);

        Fachada fachada = Fachada.getInstancia();
        
        //Instância do formulário que está sendo utilizado
        AdicionarDebitoCobradoContaActionForm adicionarDebitoCobradoContaActionForm = 
        (AdicionarDebitoCobradoContaActionForm) actionForm;
        
        /*
         * Costante que informa o ano limite para o campo anoMesReferencia da conta
         */
        httpServletRequest.setAttribute("anoLimite", ConstantesSistema.ANO_LIMITE);
        
        //Recebimento da matricula do imóvel selecionado
        String idImovel = (String) httpServletRequest.getParameter("imovel");
        
        if (idImovel == null || idImovel.equalsIgnoreCase("")){
        	throw new ActionServletException(
                    "atencao.adicionar_debito_imovel_obrigatorio");
        }
        else{
        	//O id do imóvel será utilizado no action de inserção dos débitos cobrados.
        	adicionarDebitoCobradoContaActionForm.setImovelID(idImovel);
        }
        
        //Carregar tipo do débito
        if (sessao.getAttribute("colecaoAdicionarDebitoTipo") == null){
        
        	FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo(FiltroDebitoTipo.DESCRICAO_ABREVIADA);
        	filtroDebitoTipo.setConsultaSemLimites(true);
        	
        	filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.FINANCIAMENTO_TIPO, FinanciamentoTipo.SERVICO_NORMAL));
        	
        	filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.INDICADOR_USO,
        			ConstantesSistema.INDICADOR_USO_ATIVO));
        
        	filtroDebitoTipo.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);
        	Collection colecaoAdicionarDebitoTipo = fachada.pesquisar(filtroDebitoTipo,
        		DebitoTipo.class.getName());
        
        	if (colecaoAdicionarDebitoTipo == null || colecaoAdicionarDebitoTipo.isEmpty()){
        	throw new ActionServletException(
                    "atencao.pesquisa.nenhum_registro_tabela", null,
                    "DEBITO_TIPO");
        	}
        
        	// Disponibiliza a coleção pela sessão
        	sessao.setAttribute("colecaoAdicionarDebitoTipo", colecaoAdicionarDebitoTipo);
        
        }
        
        if (adicionarDebitoCobradoContaActionForm.getDebitoTipoID() != null && !adicionarDebitoCobradoContaActionForm.getDebitoTipoID().trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
        	FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
        	filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.ID, adicionarDebitoCobradoContaActionForm.getDebitoTipoID()));
        	
        	Collection colecaoDebitoTipo = fachada.pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());
        	
        	DebitoTipo debitoTipo = (DebitoTipo) Util.retonarObjetoDeColecao(colecaoDebitoTipo);
        	
        	adicionarDebitoCobradoContaActionForm.setValorDebito(Util.formatarMoedaReal(debitoTipo.getValorSugerido()));
        } else {
        	adicionarDebitoCobradoContaActionForm.setValorDebito("");
        }
        
        boolean alterarValorSugeridoEmTipoDebito = Fachada
				.getInstancia()
				.verificarPermissaoEspecial(
						PermissaoEspecial.ALTERAR_VALOR_SUGERIDO_EM_TIPO_DEBITO,
						this.getUsuarioLogado(httpServletRequest));

		httpServletRequest.setAttribute("alterarValorSugeridoEmTipoDebito",
				alterarValorSugeridoEmTipoDebito);
        
		if (adicionarDebitoCobradoContaActionForm.getValorDebito() == null
				|| adicionarDebitoCobradoContaActionForm.getValorDebito()
						.equals("")) {

			httpServletRequest.setAttribute("alterarValorSugeridoEmTipoDebito",
					true);

		}
		
        return retorno;
    }

}
