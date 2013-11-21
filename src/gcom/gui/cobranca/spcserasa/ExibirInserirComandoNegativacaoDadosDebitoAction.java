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
package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os parâmetros para realização
 * da inserção de um Comando de Negativação (Aba nº 02 - Dados do Débito) 
 *
 * @author Ana Maria	
 * @date 06/11/2007
 */
public class ExibirInserirComandoNegativacaoDadosDebitoAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("inserirComandoNegativacaoDadosDebito");
        
        Fachada fachada = Fachada.getInstancia();
        
        InserirComandoNegativacaoActionForm inserirComandoNegativacaoActionForm = (InserirComandoNegativacaoActionForm) actionForm;
        
  	    //Pesquisar Sistema Parametro 
  	    FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
  			
  	   Collection<SistemaParametro> collectionSistemaParametro = fachada
  					.pesquisar(filtroSistemaParametro,
  							SistemaParametro.class.getName());
  	    SistemaParametro sistemaParametro = (SistemaParametro) collectionSistemaParametro
  					.iterator().next();
  	  
  	    //Referência do Débito Final
  	    if(inserirComandoNegativacaoActionForm.getReferenciaFinal() == null ||
  	    		inserirComandoNegativacaoActionForm.getReferenciaFinal().equals("")){
	  	    String anoMesArrecadacao = Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesArrecadacao());
	  	    inserirComandoNegativacaoActionForm.setReferenciaFinal(anoMesArrecadacao);
  	    }
  	    //Data do Vencimento Final
  	    if(inserirComandoNegativacaoActionForm.getDataVencimentoFinal() == null || 
  	    		inserirComandoNegativacaoActionForm.getDataVencimentoFinal().equals("")){
			//Período de vencimento do débito	
			Integer numeroDiasVencimentoCobranca = new Integer(sistemaParametro.getNumeroDiasVencimentoCobranca());			
			Date dataVencimentoFinal = Util.subtrairNumeroDiasDeUmaData(new Date(), numeroDiasVencimentoCobranca);
			Date dataVencimentoInicial = Util.subtrairNumeroAnosDeUmaData(dataVencimentoFinal, -5);
			inserirComandoNegativacaoActionForm.setDataVencimentoInicial(Util.formatarData(dataVencimentoInicial));
			inserirComandoNegativacaoActionForm.setDataVencimentoFinal(Util.formatarData(dataVencimentoFinal));
			/*Date dataAtualMenosMes = Util.adcionarOuSubtrairMesesAData(new Date(), -1, 0);
	  	    int mesData = Util.getMes(dataAtualMenosMes);
	  	    int anoData = Util.getAno(dataAtualMenosMes);  	    
	  	    String dataVencimentoFinal = Util.obterUltimoDiaMes(mesData, anoData)+ "/"+ mesData + "/" + anoData;
	  	    inserirComandoNegativacaoActionForm.setDataVencimentoFinal(dataVencimentoFinal);*/
  	    }
  	    
        if(inserirComandoNegativacaoActionForm.getContasRevisao() == null){
        	//Considerar Conta em Revisão - exibir com opção "Não" selecionada    		
        	inserirComandoNegativacaoActionForm.setContasRevisao(ConstantesSistema.NAO_CONFIRMADA);   
        }
	
        if(inserirComandoNegativacaoActionForm.getGuiasPagamento() == null){
        	//Considerar Guias de Pagamento - exibir com opção "Não" selecionada    		
        	inserirComandoNegativacaoActionForm.setGuiasPagamento(ConstantesSistema.NAO_CONFIRMADA);   
        }

        if(inserirComandoNegativacaoActionForm.getParcelaAtraso() == null){
        	//Parcela em Atraso - exibir com opção "Não" selecionada    		
        	inserirComandoNegativacaoActionForm.setParcelaAtraso(ConstantesSistema.NAO_CONFIRMADA);   
        }
        
        if(inserirComandoNegativacaoActionForm.getCartaParcelamentoAtraso() == null){
        	//Recebeu Carta de Parcelamento em Atraso - exibir com opção "Não" selecionada    		
        	inserirComandoNegativacaoActionForm.setCartaParcelamentoAtraso(ConstantesSistema.NAO_CONFIRMADA);   
        }

        if(inserirComandoNegativacaoActionForm.getIndicadorContaNomeCliente() == null){
        	//Exigir ao Menos uma Conta em Nome do Cliente Negativado  - exibir com opção "Sim" selecionada    		
        	inserirComandoNegativacaoActionForm.setIndicadorContaNomeCliente(ConstantesSistema.CONFIRMADA);   
        }
	
        
		// Data Corrente
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dataCorrente = new GregorianCalendar();
		httpServletRequest.setAttribute("dataAtual", formatoData
				.format(dataCorrente.getTime()));
    		
    	return retorno;
    }

}
