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

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AlterarVencimentoContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirManterConta");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        //Instância do formulário que está sendo utilizado
        AlterarVencimentoContaActionForm alterarVencimentoContaActionForm = (AlterarVencimentoContaActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();
        
    	SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
        
        //Contas selecionadas pelo usuário
        String identificadoresConta = alterarVencimentoContaActionForm.getContaSelected();
        
        String[] arrayIdentificadores = identificadoresConta.split(",");
        
        int flag = 0;
		Integer contaNaBase = null;
		
		for (int i = 0; i < arrayIdentificadores.length; i++) {
			// Carregando a conta que está na base
			String dadosConta = arrayIdentificadores[i];
			String[] idUltimaAlteracao = dadosConta.split("-");
			
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			
			Calendar data = new GregorianCalendar();
			data.setTimeInMillis(new Long(idUltimaAlteracao[1].trim())
					.longValue());
			
			String time = formatter.format(data.getTime());
			
			// alterado para fazer a pesquisa por Hql e nao com filtro como estava sendo feita antes - Fernanda Paiva - 23/08/2006
			contaNaBase = fachada.pesquisarExistenciaContaParaConcorrencia(idUltimaAlteracao[0],time);
			
			// Verificar atualização realizada antes por outro usuário
			if (contaNaBase == null || contaNaBase.equals("")) {
				httpServletRequest.setAttribute("reloadPage", "OK");
				flag = 1;
				sessao.setAttribute("erroConcorrencia","erroConcorrencia");
			}
		}
        
        //Data de vencimento
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        
        Date dataVencimentoConta = new Date();
 
        try {
			dataVencimentoConta = formatoData.parse(alterarVencimentoContaActionForm.getDataVencimento());
		} catch (ParseException e) {
			
			e.printStackTrace();
		}

        if (sessao.getAttribute("colecaoContaImovel") != null && (identificadoresConta != null &&
        	!identificadoresConta.equalsIgnoreCase("")) && flag == 0){
        	
        	Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        	
        	Collection<Conta> colecaoContaImovel = (Collection) sessao.getAttribute("colecaoContaImovel");
       		
        	// [FS0007] - Validar data de vencimento.       	
    		if (dataVencimentoConta != null
					&& Util.getAno(dataVencimentoConta) < Util.obterAno(sistemaParametro
							.getAnoMesFaturamento())) {
				throw new ActionServletException(
						"atencao.data_vencimento_conta_inferior_ano_faturamento");
			}	          	
          			
    		// -------------------------------------------------------------------------------------------
			// Alterado por :  Hugo Leonardo - data : 19/08/2010 
			// Analista :  Aryed Lins.
        	// [FS0007] - Validar data de vencimento.		
			// -------------------------------------------------------------------------------------------
    		
			//if(sistemaParametro.getIndicadorCalculaVencimento() == 1){
				//Caso data corrente seja posterior a data corrente mais a quantidade de dias parametro.
				Date dataCorrente = new Date();		
				
				Integer diasAdicionais = 0;
	        	
	        	if(sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior() != null){
	        		diasAdicionais = sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior().intValue();
	        	}
				
				Date dataCorrenteComDias = Util.adicionarNumeroDiasDeUmaData(dataCorrente, diasAdicionais.intValue());
				//E o usuário não tenha permissão especial.	
				boolean temPermissaoParaRetificarDataVencimentoAlemPrazoPadrao = fachada.verificarPermissaoEspecial(PermissaoEspecial.RETIFICAR_DATA_VENCIMENTO_ALEM_PRAZO_PADRAO,
						usuarioLogado);	
					
				//	1 se a dataVencimentoConta for maior que a dataCorrenteComDias.
				if(Util.compararData(dataVencimentoConta, dataCorrenteComDias) == 1 && (temPermissaoParaRetificarDataVencimentoAlemPrazoPadrao == false)){
					throw new ActionServletException("atencao.necessario_permissao_especial_para_data_vencimento_posterior_permitido");
				}
	        	
			//}	
        	//-------------------------------------------------------------------------------------------
        	
			//Alterando a data de vencimento de uma ou mais contas
        	fachada.alterarVencimentoConta(colecaoContaImovel, identificadoresConta, dataVencimentoConta, usuarioLogado);
        	
        	//Realizar um reload na tela de manter conta
        	httpServletRequest.setAttribute("reloadPage", "OK");
        	
        }
                
        return retorno;
    }

}

