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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroUnidadeRepavimentadoraCustoPavimentoCalcada;
import gcom.atendimentopublico.ordemservico.FiltroUnidadeRepavimentadoraCustoPavimentoRua;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoCalcada;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoRua;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1106] Inserir Custo de Pavimento por Repavimentadora
 * 
 * @author Hugo Leonardo
 * @date 21/12/2010
 */

public class InserirCustoPavimentoPorRepavimentadoraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		ExibirInserirCustoPavimentoPorRepavimentadoraActionForm form = 
			(ExibirInserirCustoPavimentoPorRepavimentadoraActionForm) actionForm;
		
		List colecaoUnidadeRepavimentadoraCustoPavimentoRua = (List) sessao.getAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoRua");
		List colecaoUnidadeRepavimentadoraCustoPavimentoCalcada = (List) sessao.getAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoCalcada");
		
		UnidadeRepavimentadoraCustoPavimentoRua unidadeRepavimentadoraCustoPavimentoRua = null;
		UnidadeRepavimentadoraCustoPavimentoCalcada unidadeRepavimentadoraCustoPavimentoCalcada = null;
		
		if(!Util.verificarNaoVazio(form.getIdUnidadeRepavimentadora())){
			throw new ActionServletException("atencao.unidade_repavimentadora_nao_informada");
		}
		
		if( (colecaoUnidadeRepavimentadoraCustoPavimentoRua == null || (colecaoUnidadeRepavimentadoraCustoPavimentoRua.size() == 0))
				&& (colecaoUnidadeRepavimentadoraCustoPavimentoCalcada == null || (colecaoUnidadeRepavimentadoraCustoPavimentoCalcada.size() == 0)) ){
			
			throw new ActionServletException("atencao.informe.custo_pavimento_por_repavimentadora");
		}
		
		Iterator iterator = null;
		
		// Inserir Custo Pavimento Rua
		if(colecaoUnidadeRepavimentadoraCustoPavimentoRua != null && (colecaoUnidadeRepavimentadoraCustoPavimentoRua.size() > 0)){

			iterator = colecaoUnidadeRepavimentadoraCustoPavimentoRua.iterator();
			
			while (iterator.hasNext()) {
				unidadeRepavimentadoraCustoPavimentoRua = (UnidadeRepavimentadoraCustoPavimentoRua) iterator.next();
				
				// [SB0001] Verificar existência de custo de pavimento de rua vigente
				FiltroUnidadeRepavimentadoraCustoPavimentoRua filtroCustoPavimentoRua = new FiltroUnidadeRepavimentadoraCustoPavimentoRua();
    			
    			filtroCustoPavimentoRua.adicionarParametro(new ParametroSimples(
    					FiltroUnidadeRepavimentadoraCustoPavimentoRua.UNIDADE_REPAVIMENTADORA_ID, form.getIdUnidadeRepavimentadora()));

    			Collection<UnidadeRepavimentadoraCustoPavimentoRua> colecaoCustoPavimentoRua = Fachada.getInstancia().pesquisar(
    					filtroCustoPavimentoRua, UnidadeRepavimentadoraCustoPavimentoRua.class.getName());
    			
    			if(!Util.isVazioOrNulo(colecaoCustoPavimentoRua)){
    				
    				UnidadeRepavimentadoraCustoPavimentoRua unidRepPavRua = null;
    				
    				Iterator it = colecaoCustoPavimentoRua.iterator();
    				
    				while(it.hasNext()){
    					unidRepPavRua = (UnidadeRepavimentadoraCustoPavimentoRua) it.next();
    					
    					if(unidRepPavRua.getPavimentoRua().getId().equals(unidadeRepavimentadoraCustoPavimentoRua.getPavimentoRua().getId()) 
    							&& Util.compararData(unidRepPavRua.getDataVigenciaInicial(), unidadeRepavimentadoraCustoPavimentoRua.getDataVigenciaInicial()) == -1
    							&& unidRepPavRua.getDataVigenciaFinal() == null){
    						
    						unidRepPavRua.setDataVigenciaFinal(Util.formatarDataFinal( Util.subtrairNumeroDiasDeUmaData(unidadeRepavimentadoraCustoPavimentoRua.getDataVigenciaInicial(), 1)) );
    						unidRepPavRua.setUltimaAlteracao(new Date());
    						
    						fachada.atualizar(unidRepPavRua);
    					}
    				}
    			}
    			
    			// ------------ REGISTRAR TRANSAÇÃO ----------------
    			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
    					Operacao.OPERACAO_INSERIR_CUSTO_PAVIMENTO, unidadeRepavimentadoraCustoPavimentoRua.getId(),
    					unidadeRepavimentadoraCustoPavimentoRua.getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
    					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
    			// ------------ REGISTRAR TRANSAÇÃO ----------------
    			registradorOperacao.registrarOperacao(unidadeRepavimentadoraCustoPavimentoRua);
				
				fachada.inserir(unidadeRepavimentadoraCustoPavimentoRua);
			}
		}
		
		// Inserir Custo Pavimento Calcada
		if(colecaoUnidadeRepavimentadoraCustoPavimentoCalcada != null && (colecaoUnidadeRepavimentadoraCustoPavimentoCalcada.size() > 0)){

			iterator = colecaoUnidadeRepavimentadoraCustoPavimentoCalcada.iterator();
			
			while (iterator.hasNext()) {
				unidadeRepavimentadoraCustoPavimentoCalcada = (UnidadeRepavimentadoraCustoPavimentoCalcada) iterator.next();
				
				// [SB0002] Verificar existência de custo de pavimento de calçada vigente.
				FiltroUnidadeRepavimentadoraCustoPavimentoCalcada filtroCustoPavimentoCalcada = new FiltroUnidadeRepavimentadoraCustoPavimentoCalcada();
    			
				filtroCustoPavimentoCalcada.adicionarParametro(new ParametroSimples(
    					FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.UNIDADE_REPAVIMENTADORA_ID, form.getIdUnidadeRepavimentadora()));

    			Collection<UnidadeRepavimentadoraCustoPavimentoCalcada> colecaoCustoPavimentoCalcada = Fachada.getInstancia().pesquisar(
    					filtroCustoPavimentoCalcada, UnidadeRepavimentadoraCustoPavimentoCalcada.class.getName());
    			
    			if(!Util.isVazioOrNulo(colecaoCustoPavimentoCalcada)){
    				
    				UnidadeRepavimentadoraCustoPavimentoCalcada unidRepPavCalcada = null;
    				
    				Iterator it = colecaoCustoPavimentoCalcada.iterator();
    				
    				while(it.hasNext()){
    					unidRepPavCalcada = (UnidadeRepavimentadoraCustoPavimentoCalcada) it.next();
    					
    					if(unidRepPavCalcada.getPavimentoCalcada().getId().equals(unidadeRepavimentadoraCustoPavimentoCalcada.getPavimentoCalcada().getId()) 
    							&& Util.compararData(unidRepPavCalcada.getDataVigenciaInicial(), unidadeRepavimentadoraCustoPavimentoCalcada.getDataVigenciaInicial()) == -1
    							&& unidRepPavCalcada.getDataVigenciaFinal() == null){
    						
    						unidRepPavCalcada.setDataVigenciaFinal(Util.formatarDataFinal( Util.subtrairNumeroDiasDeUmaData(unidadeRepavimentadoraCustoPavimentoCalcada.getDataVigenciaInicial(), 1)) );
    						unidRepPavCalcada.setUltimaAlteracao(new Date());
    						
    						fachada.atualizar(unidRepPavCalcada);
    					}
    				}
    			}
				
				fachada.inserir(unidadeRepavimentadoraCustoPavimentoCalcada);
			}
		}
		
		// Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Custo do pavimento por repavimentadora inserido com sucesso ",
				"Inserir outro Custo do pavimento por repavimentadora",
				"exibirInserirCustoPavimentoPorRepavimentadoraAction.do?menu=sim");
		
		sessao.removeAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoRua");
		sessao.removeAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoCalcada");
		sessao.removeAttribute("colecaoUnidadeRepavimentadora");
		sessao.removeAttribute("colecaoPavimentoRua");
		sessao.removeAttribute("colecaoPavimentoCalcada");
		
		return retorno;
	}

}
