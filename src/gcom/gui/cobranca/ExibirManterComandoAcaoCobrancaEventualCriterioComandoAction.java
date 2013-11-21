/**
 * 
 */
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
package gcom.gui.cobranca;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaAtividade;
import gcom.cobranca.FiltroCobrancaCriterio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0244] Manter Comando de Ação de Conbrança - Tipo de Comando Cronograma
 * @author Rafael Santos
 * @since 24/04/2006
 */
public class ExibirManterComandoAcaoCobrancaEventualCriterioComandoAction  extends GcomAction{
	
	/**
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirManterComandoAcaoCobrancaEventualCriterioComando");

        ManterComandoAcaoCobrancaDetalhesActionForm manterComandoAcaoCobrancaDetalhesActionForm = (ManterComandoAcaoCobrancaDetalhesActionForm) actionForm;        
        
        //Mudar isso quando implementar a parte de segurança
        HttpSession sessao = httpServletRequest.getSession(false);
        Fachada fachada = Fachada.getInstancia();
        
		if(httpServletRequest.getParameter("cobrancaGrupo")== null){
			manterComandoAcaoCobrancaDetalhesActionForm.setCobrancaGrupo("-1");	
		}
		if(httpServletRequest.getParameter("gerenciaRegional")== null){
			manterComandoAcaoCobrancaDetalhesActionForm.setGerenciaRegional("-1");
		}
		if(httpServletRequest.getParameter("unidadeNegocio") == null){
			manterComandoAcaoCobrancaDetalhesActionForm.setUnidadeNegocio("-1");
		}
		if(httpServletRequest.getParameter("clienteRelacaoTipo") == null){
			manterComandoAcaoCobrancaDetalhesActionForm.setClienteRelacaoTipo("-1");
		}
        
        
        
        String idCobrancaAtividade = httpServletRequest.getParameter("idCobrancaAtividade");
        String idCobrancaAcao = httpServletRequest.getParameter("idCobrancaAcao");
        
		String anoMesContaInicial = manterComandoAcaoCobrancaDetalhesActionForm.getPeriodoInicialConta(); 
		String anoMesContaFinal = manterComandoAcaoCobrancaDetalhesActionForm.getPeriodoFinalConta();
		
		String anoMesVencimentoInicial = manterComandoAcaoCobrancaDetalhesActionForm.getPeriodoVencimentoContaInicial();  
		String anoMesVencimentoFinal  = manterComandoAcaoCobrancaDetalhesActionForm.getPeriodoVencimentoContaFinal();

		
		CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando = null;
		if(sessao.getAttribute("cobrancaAcaoAtividadeComando") != null){
			cobrancaAcaoAtividadeComando = (CobrancaAcaoAtividadeComando) sessao.getAttribute("cobrancaAcaoAtividadeComando");
			httpServletRequest.setAttribute("idCobrancaAcaoAtividadeComando", cobrancaAcaoAtividadeComando.getId().toString());
		}
		
		String idComandoSelecionado = httpServletRequest.getParameter("idComandoSelecionado");
		if(idComandoSelecionado != null){
			if(cobrancaAcaoAtividadeComando.getCobrancaCriterio() != null){
				cobrancaAcaoAtividadeComando.getCobrancaCriterio().setId(new Integer(idComandoSelecionado));
				sessao.setAttribute("cobrancaAcaoAtividadeComando",cobrancaAcaoAtividadeComando);
			}else{
				CobrancaCriterio cobrancaCriterio = new CobrancaCriterio();
				cobrancaCriterio.setId(new Integer(idComandoSelecionado));
				cobrancaAcaoAtividadeComando.setCobrancaCriterio(cobrancaCriterio);
				sessao.setAttribute("cobrancaAcaoAtividadeComando",cobrancaAcaoAtividadeComando);

				
			}
		}
			
			
		//id criterio de cobrança
		//if(cobrancaAcaoAtividadeComando.getCobrancaCriterio() != null){
			//manterComandoAcaoCobrancaDetalhesActionForm.setIdCriterioCobranca(cobrancaAcaoAtividadeComando.getCobrancaCriterio().getId().toString());
		//}

		
		//id criterio de cobranca
		///if(manterComandoAcaoCobrancaDetalhesActionForm.getIdCriterioCobranca() != null 
			//	&& !manterComandoAcaoCobrancaDetalhesActionForm.getIdCriterioCobranca().equals("")){
			//httpServletRequest.setAttribute("idCriterioConbrancaSelecionado",manterComandoAcaoCobrancaDetalhesActionForm.getIdCriterioCobranca());	
		//}
		
		// [FS0012] - Verificar referência final menor que referência inicial
		fachada.validarAnoMesInicialFinalComandoAcaoCobranca(anoMesContaInicial,
				anoMesContaFinal);

		// [FS0014] - Verificar data final menos que data inicial
		fachada.verificarVencimentoContaComandoAcaoCobranca(
				anoMesVencimentoInicial, anoMesVencimentoFinal);
		
        
        //[SB0003] - Selecionar Cretério do Comando
        //pesquisar cobranca atividade
        if(idCobrancaAtividade != null && !idCobrancaAtividade.equals("")){
        	FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
        	filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.ID,idCobrancaAtividade));
        	
        	Collection colecaoCobrancaAtividade = fachada.pesquisar(filtroCobrancaAtividade,CobrancaAtividade.class.getName());

        	if(colecaoCobrancaAtividade != null && !colecaoCobrancaAtividade.isEmpty()){
        		
        		CobrancaAtividade cobrancaAtividade = (CobrancaAtividade)colecaoCobrancaAtividade.iterator().next();
        		if(cobrancaAtividade.getIndicadorExecucao().intValue() == 1){
        			httpServletRequest.setAttribute("habilitarExecutar","true");
        		}else{
        			httpServletRequest.setAttribute("habilitarExecutar","false");
        		}
        	}
        	
        }
        
        
        
        //pesquisar cobranca ação
        if(idCobrancaAcao != null && !idCobrancaAcao.equals("")){
        	Collection colecaoCriterioCobranca = null;
        	
        	FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
        	filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.ID,idCobrancaAcao));
        	
        	Collection colecaoCobrancaAcao = fachada.pesquisar(filtroCobrancaAcao,CobrancaAcao.class.getName());

        	if(colecaoCobrancaAcao != null && !colecaoCobrancaAcao.isEmpty()){
        		
        		CobrancaAcao cobrancaAcao = (CobrancaAcao)colecaoCobrancaAcao.iterator().next();
        		manterComandoAcaoCobrancaDetalhesActionForm.setDescricaoAcaoCobranca(cobrancaAcao.getDescricaoCobrancaAcao());
        	}
        
        	FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();
        	//filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(FiltroCobrancaCriterio.ID_COBRANCA_ACAO,idCobrancaAcao));
        	
        	colecaoCriterioCobranca = fachada.pesquisar(filtroCobrancaCriterio,CobrancaCriterio.class.getName());
        	
        	
        	if(colecaoCriterioCobranca == null || colecaoCriterioCobranca.isEmpty()){
				throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela",
						null, "Tabela Cobrança Critério");
        	}
        	
            //carregar criterios de cobranca
            sessao.setAttribute("colecaoCriterioCobranca",colecaoCriterioCobranca);        	
        	
        }
        

        sessao.setAttribute("manterComandoAcaoCobrancaDetalhesActionForm",
        		manterComandoAcaoCobrancaDetalhesActionForm);
        return retorno;
    }

}
