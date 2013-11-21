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
package gcom.gui.arrecadacao.aviso;


import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.aviso.bean.AvisoBancarioHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Esta classe tem por finalidade gerar o formulário que irá apresentar a análise do aviso bancário e os
 * pagamentos/devoluções associados
 *
 * @author Raphael Rossiter, Vivianne Sousa
 * @date 23/03/2006, 15/12/2006
 */
public class ExibirApresentarAnaliseAvisoBancarioAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirApresentarAnaliseAvisoBancario");
        
        ApresentarAnaliseAvisoBancarioActionForm apresentarAnaliseAvisoBancarioActionForm = 
        (ApresentarAnaliseAvisoBancarioActionForm) actionForm;
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        String idAvisoBancario = httpServletRequest.getParameter("idAvisoBancario");
        
//        String situacaoAbertoFechado = "";
//        
//        if(httpServletRequest.getParameter("situacaoAbertoFechado")!= null
//        		&& !httpServletRequest.getParameter("situacaoAbertoFechado").equals("")){
//        	situacaoAbertoFechado = httpServletRequest.getParameter("situacaoAbertoFechado");
//        	sessao.setAttribute("situacaoAbertoFechado",situacaoAbertoFechado);
//        }else{
//        	situacaoAbertoFechado = (String)sessao.getAttribute("situacaoAbertoFechado");
//        }
        	
        
        String botao = httpServletRequest.getParameter("botao");
        
        if(botao != null && !botao.equalsIgnoreCase("") && botao.equalsIgnoreCase("sim"))        {
        	sessao.removeAttribute("habilitarBotao");
        }
        
        if (idAvisoBancario == null || idAvisoBancario.equalsIgnoreCase("")){
			if (httpServletRequest.getAttribute("idAvisoBancario") == null){
				idAvisoBancario = (String) sessao.getAttribute("idAvisoBancario");
			}else{
				idAvisoBancario = (String) httpServletRequest.getAttribute("idAvisoBancario").toString();
			}
			
		} else {
			sessao.setAttribute("i", true);
		}
		
        apresentarAnaliseAvisoBancarioActionForm.setIdAvisoBancario(idAvisoBancario);
		sessao.setAttribute("idAvisoBancario", idAvisoBancario);
		
		sessao.removeAttribute("filtrar_manter");
        
        Fachada fachada = Fachada.getInstancia();
        
        AvisoBancario avisoBancario = new AvisoBancario();
        avisoBancario.setId(new Integer(idAvisoBancario));
        
        
        AvisoBancarioHelper avisoBancarioHelper = fachada.apresentarAnaliseAvisoBancario(avisoBancario);
        
        apresentarAnaliseAvisoBancarioActionForm.setCodigoNomeArrecadador(avisoBancarioHelper.getCodigoNomeArrecadador());
        apresentarAnaliseAvisoBancarioActionForm.setCodigoDescricaoArrecadacaoForma(avisoBancarioHelper.getCodigoDescricaoArrecadacaoForma());
        
        if (avisoBancarioHelper.getAvisoBancario().getDataLancamento() != null){
        	apresentarAnaliseAvisoBancarioActionForm.setDataLancamento(Util.formatarData(
            avisoBancarioHelper.getAvisoBancario().getDataLancamento()));
        }
        
        if (avisoBancarioHelper.getAvisoBancario().getNumeroSequencial() != null){
        	apresentarAnaliseAvisoBancarioActionForm.setSequencial(avisoBancarioHelper
        	.getAvisoBancario().getNumeroSequencial().toString());
        }
        
        if (avisoBancarioHelper.getAvisoBancario().getNumeroDocumento() != 0){
        	apresentarAnaliseAvisoBancarioActionForm.setNumeroDocumento(String.valueOf(avisoBancarioHelper
        	.getAvisoBancario().getNumeroDocumento()));
        }
        
        apresentarAnaliseAvisoBancarioActionForm.setSituacao(avisoBancarioHelper.getSituacao());
        
        if (avisoBancarioHelper.getAvisoBancario().getDataPrevista() != null){
        	apresentarAnaliseAvisoBancarioActionForm.setDataPrevistaCredito(Util.formatarData(
            avisoBancarioHelper.getAvisoBancario().getDataPrevista()));
        }
        
        if (avisoBancarioHelper.getAvisoBancario().getDataRealizada() != null){
        	apresentarAnaliseAvisoBancarioActionForm.setDataRealCredito(Util.formatarData(
                    avisoBancarioHelper.getAvisoBancario().getDataRealizada()));
        }

        if (avisoBancarioHelper.getAvisoBancario().getValorRealizado() != null){
        	apresentarAnaliseAvisoBancarioActionForm.setValorRealCredito(Util.formatarMoedaReal(
                	avisoBancarioHelper.getAvisoBancario().getValorRealizado()));
        }
        
        if (avisoBancarioHelper.getAvisoBancario().getValorArrecadacaoCalculado() != null){
        	apresentarAnaliseAvisoBancarioActionForm.setValorArrecadacaoCalculado(Util.formatarMoedaReal(
        	avisoBancarioHelper.getAvisoBancario().getValorArrecadacaoCalculado()));
        }
        
        if (avisoBancarioHelper.getAvisoBancario().getValorArrecadacaoInformado() != null){
        	apresentarAnaliseAvisoBancarioActionForm.setValorArrecadacaoInformado(Util.formatarMoedaReal(
        	avisoBancarioHelper.getAvisoBancario().getValorArrecadacaoInformado()));
        }
        
        if (avisoBancarioHelper.getAvisoBancario().getValorDevolucaoCalculado() != null){
        	apresentarAnaliseAvisoBancarioActionForm.setValorDevolucaoCalculado(Util.formatarMoedaReal(
        	avisoBancarioHelper.getAvisoBancario().getValorDevolucaoCalculado()));
        }
        
        if (avisoBancarioHelper.getAvisoBancario().getValorDevolucaoInformado() != null){
        	apresentarAnaliseAvisoBancarioActionForm.setValorDevolucaoInformado(Util.formatarMoedaReal(
        	avisoBancarioHelper.getAvisoBancario().getValorDevolucaoInformado()));
        }
        
        if (avisoBancarioHelper.getAvisoBancario().getValorContabilizado() != null){
        	apresentarAnaliseAvisoBancarioActionForm.setValorContabilizado(Util.formatarMoedaReal(
        	avisoBancarioHelper.getAvisoBancario().getValorContabilizado()));
        }
        
        if (avisoBancarioHelper.getAvisoBancario().getAnoMesReferenciaArrecadacao() != 0){
        	apresentarAnaliseAvisoBancarioActionForm.setAnoMesArrecadacao(Util.formatarAnoMesParaMesAno(
        	avisoBancarioHelper.getAvisoBancario().getAnoMesReferenciaArrecadacao()));
        }
        
        apresentarAnaliseAvisoBancarioActionForm.setTipoAviso(avisoBancarioHelper.getTipoAviso());
        
        if (avisoBancarioHelper.getValorSomatorioDeducoes()!= null){
        	
        	apresentarAnaliseAvisoBancarioActionForm.setValorSomatorioDeducoes(
        			Util.formatarMoedaReal(avisoBancarioHelper.getValorSomatorioDeducoes()));
        }
        
//        if (avisoBancarioHelper.getAvisoBancario().getContaBancaria() != null && 
//        	avisoBancarioHelper.getAvisoBancario().getContaBancaria().getAgencia() != null &&
//        	avisoBancarioHelper.getAvisoBancario().getContaBancaria().getAgencia().getBanco() != null){
//        	apresentarAnaliseAvisoBancarioActionForm.setContaBancaria(
//        	
//        	avisoBancarioHelper.getAvisoBancario().getContaBancaria().getAgencia().getBanco().getId().toString()
//        	+ " - " + avisoBancarioHelper.getAvisoBancario().getContaBancaria().getAgencia().getCodigoAgencia().toString());
//        }
        
        apresentarAnaliseAvisoBancarioActionForm.setBancoContaBancaria("" + avisoBancarioHelper.getIdBancoContaBancaria());
        apresentarAnaliseAvisoBancarioActionForm.setAgenciaContaBancaria("" + avisoBancarioHelper.getCodigoAgenciaContaBancaria());
        apresentarAnaliseAvisoBancarioActionForm.setNumeroContaBancaria(avisoBancarioHelper.getNumeroContaBancaria());
        
        if (avisoBancarioHelper.getValorSomatorioAcertosArrecadacao() != null){
        	apresentarAnaliseAvisoBancarioActionForm.setValorSomatorioAcertosArrecadacao(Util.formatarMoedaReal(avisoBancarioHelper.getValorSomatorioAcertosArrecadacao()));
        }
        
        if (avisoBancarioHelper.getValorSomatorioAcertosDevolucao() != null){
        	apresentarAnaliseAvisoBancarioActionForm.setValorSomatorioAcertosDevolucao(Util.formatarMoedaReal(avisoBancarioHelper.getValorSomatorioAcertosDevolucao()));	
        }
        
        //(valor da Arrecadação calculado - valor da Arrecadação informado + Somatório dos Acertos da Arrecadação)-
        //(valor da Devolução calculado - valor da Devolução informado + Somatório dos Acertos da Devolução)
        if (avisoBancarioHelper.getValorDiferencaArrecadacaoDevolucao() != null){
        	apresentarAnaliseAvisoBancarioActionForm.setValorDiferencaArrecadacaoDevolucao(Util.formatarMoedaReal(avisoBancarioHelper.getValorDiferencaArrecadacaoDevolucao()));	
        }
        
        //Request que irá habilitar ou desabilitar o botão "Consultar Movimento"
//        if (avisoBancarioHelper.getAvisoBancario().getArrecadadorMovimento() != null){
//        	httpServletRequest.setAttribute("idMovimentoArrecadador", avisoBancarioHelper.getAvisoBancario()
//        	.getArrecadadorMovimento().getId());
//            
//        }
        
        if (avisoBancarioHelper.getIdMovimentoArrecadador() != null){
        	httpServletRequest.setAttribute("idMovimentoArrecadador", avisoBancarioHelper.getIdMovimentoArrecadador());
            
        }
        
        
        // caso ainda não tenha sido setado o nome campo(na primeira vez)
		if (httpServletRequest.getParameter("manter") != null){
			sessao.setAttribute("manter", "manter");
		}
        sessao.setAttribute("avisoBancarioHelper", avisoBancarioHelper);
        
        
        return retorno;
    }

}

