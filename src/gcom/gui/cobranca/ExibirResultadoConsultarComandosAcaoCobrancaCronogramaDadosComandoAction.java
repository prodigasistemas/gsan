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

import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.CobrancaAtividade;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite consultar comandos de ação de cobrança 
 * [UC0325] Consultar Comandos de Ação de Conbrança
 * @author Rafael Santos
 * @since 11/05/2006
 */
public class ExibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoAction  extends GcomAction{
	
	
	/**
	 * 
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
                .findForward("exibirComandosAcaoCobrancaCronogramaDadosComando");
        
        String idCobrancaAcaoAtividadeCronograma =  httpServletRequest.getParameter("idCobrancaAcaoAtividadeCronograma");
        
        Fachada fachada = Fachada.getInstancia();
        
        CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma =  fachada.obterCobrancaAcaoAtividadeCronograma(idCobrancaAcaoAtividadeCronograma);
        
        HttpSession sessao = httpServletRequest.getSession(false);        
        
        ExibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm = (ExibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm)actionForm;
/*        //limpar formulario
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setGrupoCobranca("");
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setReferenciaCobranca("");
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setAcaoCobranca("");
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setAtividadeCobranca("");
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setDataComando("");
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setHoraComando("");
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setValorDocumentos("");
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setQuantidadeDocumentos("");
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setQuantidadeItensDocumentos("");
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setSituacaoComando("");
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setSituacaoCronograma("");
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setDataPrevistaCronograma("");*/
        
        
        if(cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma() != null &&
        		cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaGrupoCronogramaMes() != null
        		&& cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaGrupoCronogramaMes().getCobrancaGrupo() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setGrupoCobranca(cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaGrupoCronogramaMes().getCobrancaGrupo().getDescricao());
        }
        
        if(cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma() != null 
        		&& cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaGrupoCronogramaMes() != null ){
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setReferenciaCobranca(new Integer(cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaGrupoCronogramaMes().getAnoMesReferencia()).toString());
        }
        if(cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma() != null 
        		&& cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaAcao() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setAcaoCobranca(cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaAcao().getDescricaoCobrancaAcao());
        }
        if(cobrancaAcaoAtividadeCronograma.getCobrancaAtividade() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setAtividadeCobranca(cobrancaAcaoAtividadeCronograma.getCobrancaAtividade().getDescricaoCobrancaAtividade());
        }
        exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setDataPrevistaCronograma(Util.formatarData(cobrancaAcaoAtividadeCronograma.getDataPrevista()));
        if(cobrancaAcaoAtividadeCronograma.getComando() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setDataComando(Util.formatarData(cobrancaAcaoAtividadeCronograma.getComando()));
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setHoraComando(Util.formatarHoraSemData(cobrancaAcaoAtividadeCronograma.getComando()));
        }
        if(cobrancaAcaoAtividadeCronograma.getRealizacao() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setDataRealizacao(Util.formatarData(cobrancaAcaoAtividadeCronograma.getRealizacao()));
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setHoraRealizacao(Util.formatarHoraSemData(cobrancaAcaoAtividadeCronograma.getRealizacao()));
        }
        if(cobrancaAcaoAtividadeCronograma.getValorDocumentos() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setValorDocumentos(cobrancaAcaoAtividadeCronograma.getValorDocumentos().toString());
        }
        if(cobrancaAcaoAtividadeCronograma.getQuantidadeDocumentos() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setQuantidadeDocumentos(cobrancaAcaoAtividadeCronograma.getQuantidadeDocumentos().toString());
        }
        if(cobrancaAcaoAtividadeCronograma.getQuantidadeItensCobrados() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setQuantidadeItensDocumentos(cobrancaAcaoAtividadeCronograma.getQuantidadeItensCobrados().toString());
        }
        
        if(cobrancaAcaoAtividadeCronograma.getComando() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setSituacaoComando("Comandado");
        	
        }else{
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setSituacaoComando("Não Comandado");
        }
              
        if(cobrancaAcaoAtividadeCronograma.getRealizacao() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setSituacaoCronograma("Realizado");
        	if(cobrancaAcaoAtividadeCronograma.getCobrancaAtividade().getId().equals(CobrancaAtividade.EMITIR)){
          	  httpServletRequest.setAttribute("emitir", "sim");	
          	}
        }else{
        	exibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm.setSituacaoCronograma("Não Realizado");
        }
        
        if(fachada.verificarPermissaoEmissaoDocumentoCobranca((Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO))){
        	 httpServletRequest.setAttribute("permissaoEmitir", "sim");	
        }
        
        httpServletRequest.setAttribute("idCobrancaAcaoAtividadeCronograma", idCobrancaAcaoAtividadeCronograma);
        sessao.setAttribute("cobrancaAcaoAtividadeCronograma", cobrancaAcaoAtividadeCronograma);                
		        
        return retorno;
    }

}
