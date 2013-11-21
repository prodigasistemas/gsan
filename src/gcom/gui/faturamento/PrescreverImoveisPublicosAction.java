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

import gcom.batch.Processo;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.PrescreverDebitosImovelHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PrescreverImoveisPublicosAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        SistemaParametro sistemaParametro = 
			this.getFachada().pesquisarParametrosDoSistema();
        
        PrescreverImoveisPublicosActionForm form = (PrescreverImoveisPublicosActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();
        
    	Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
       
        PrescreverDebitosImovelHelper helper = new PrescreverDebitosImovelHelper();
        
        String dataInicial = form.getPeriodoInicial();
		String dataFinal = form.getPeriodoFinal();
		
		// Período 
		if(Util.verificarNaoVazio(dataInicial)){
			
			if(!Util.verificarNaoVazio(dataFinal)){
				
				throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial",
						null,"atendimento");
			}else{
				
				Date dtInicial = Util.converteStringParaDate(dataInicial);
				Date dtFinal = Util.converteStringParaDate(dataFinal);
				
				if(Util.compararData(dtInicial, dtFinal) == 1){
					
					throw new ActionServletException("atencao.gsan.data_final_menor_data_inicial",null, "Prescrição");
				}
				
				helper.setDataInicio(dataInicial);
				helper.setDataFim(dataFinal);
			}
		}
        
        helper.setUsuarioLogado(usuarioLogado);
        
        //Seta o anoMes de Faturamento.
        helper.setAnoMesReferencia(sistemaParametro.getAnoMesFaturamento().toString());
        
        // Id Cliente
        if(Util.verificarNaoVazio(form.getIdCliente())){
        	helper.setIdCliente(form.getIdCliente());
        }
        
        //Is Imovel
        if(Util.verificarNaoVazio(form.getIdImovel())){
        	helper.setIdImovel(form.getIdImovel());
        }
        
		// Esfera de poder
        String idsEsferaPoder = "";
        if(!Util.verificarNaoVazio(form.getIdImovel()) && !Util.verificarNaoVazio(form.getIdCliente()) 
        		&& !Util.isVazioOrNulo( form.getIdsEsferaPoder())){
        	
        	for (String id : form.getIdsEsferaPoder()) {
        		if (!id.equals("-1")) {
        			idsEsferaPoder += id + ",";
        		}
        	}
        	idsEsferaPoder = Util.removerUltimosCaracteres(idsEsferaPoder, 1);

        }else if(!Util.verificarNaoVazio(form.getIdImovel()) && !Util.verificarNaoVazio(form.getIdCliente())){
        	throw new ActionServletException("atencao.campo_texto.obrigatorio",null, "Esfera do Poder");
        }
        
        helper.setEsferaPoder(idsEsferaPoder);
		
		// Forma Prescricao
		if(Util.verificarNaoVazio(form.getFormaPrescricao())){
			
			helper.setFormaPrescricao(form.getFormaPrescricao());
			
			if(form.getFormaPrescricao().equals("0")){
		        	
				helper.setIdProcesso(Processo.PRESCREVER_DEBITOS_IMOVEIS_PUBLICOS_MANUAL);
		    }else{
		    	helper.setIdProcesso(Processo.PRESCREVER_DEBITOS_IMOVEIS_PUBLICOS_AUTOMATICO);
		    }
		}
        
		Integer tipo = fachada.prescreverDebitosImoveisPublicos(helper);

		
		// Monta a página de sucesso
		// Caso Batch Manual
		if(tipo != null){
			montarPaginaSucesso(httpServletRequest,
	    			"Processo Prescrever Débitos de Imóveis Públicos Manual iniciado com sucesso.", 
	    			"Inserir outra Prescrição de Débitos de Imóveis Públicos Manual",
	    			"/exibirPrescreverImoveisPublicosAction.do");
			
		}else{
			// Caso Batch Automático
			
			montarPaginaSucesso(httpServletRequest,
	    			"Configuração da Prescrição de Débitos de Imóveis Públicos Automática inserida com sucesso.", 
	    			"Inserir outra Prescrição de Débitos de Imóveis Públicos Automática",
	    			"/exibirPrescreverImoveisPublicosAction.do");
		}
		
		sessao.removeAttribute("colecaoEsferaPoder");
        
        return retorno;
	}

}
