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

import gcom.arrecadacao.pagamento.FiltroPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaMotivoCancelamento;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.util.ArrayList;
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


public class CancelarContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirCancelarConta");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        //Instância do formulário que está sendo utilizado
        CancelarContaActionForm cancelarContaActionForm = (CancelarContaActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();
        
        //Contas selecionadas pelo usuário
        String identificadoresConta = cancelarContaActionForm.getContaSelected();
        
        String[] arrayIdentificadores = identificadoresConta.split(",");
        
        //Controlamos o cancelamento de contas pagas
        Object[] arrayValidaContasCanceladasPagas = validarContasCanceladasPagasRA( 
                arrayIdentificadores,
                httpServletRequest,
                actionMapping );
        
        if ( ( Boolean ) arrayValidaContasCanceladasPagas[2] ){
            return ( ActionForward ) arrayValidaContasCanceladasPagas[0];
        }
        
        Boolean confirmadoCancelamentoContasPagas = ( Boolean )arrayValidaContasCanceladasPagas[1];
        
        if ( confirmadoCancelamentoContasPagas != null && !confirmadoCancelamentoContasPagas ){
            return retorno;
        }
        
        if ( confirmadoCancelamentoContasPagas == null ){
        	confirmadoCancelamentoContasPagas = new Boolean( Boolean.FALSE );
        }
        
		int flag = 0;
		
		for (int i = 0; i < arrayIdentificadores.length; i++) {
			// Carregando a conta que está na base
			String dadosConta = arrayIdentificadores[i];
			String[] idUltimaAlteracao = dadosConta.split("-");
			
			Calendar data = new GregorianCalendar();
			data.setTimeInMillis(new Long(idUltimaAlteracao[1].trim())
					.longValue());
			data.set(Calendar.MILLISECOND, 0);
			
			Object dataUltimaAlteracaoConta = fachada.pesquisarDataUltimaAlteracaoConta(new Integer(idUltimaAlteracao[0]));
			
			Calendar calendarDataUltimaAlteracaoNaBase = Calendar.getInstance();
			calendarDataUltimaAlteracaoNaBase.setTime((Date)dataUltimaAlteracaoConta);
			calendarDataUltimaAlteracaoNaBase.set(Calendar.MILLISECOND, 0);
			
			if (calendarDataUltimaAlteracaoNaBase.compareTo(data) > 0 ) {
				httpServletRequest.setAttribute("reloadPage", "OK");
				flag = 1;
				sessao.setAttribute("erroConcorrencia","erroConcorrencia");
			}
		}

        
        //MotivoCancelamentoConta selecinado pelo usuário
        ContaMotivoCancelamento contaMotivoCancelamento = new ContaMotivoCancelamento();
        contaMotivoCancelamento.setId(new Integer(cancelarContaActionForm.getMotivoCancelamentoContaID()));
        
        
        if (sessao.getAttribute("colecaoContaImovel") != null && (identificadoresConta != null &&
        	!identificadoresConta.equalsIgnoreCase("")) && flag == 0){
        
        	Collection<Conta> colecaoContaImovel = (Collection) sessao.getAttribute("colecaoContaImovel");

        	Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        	
        	//Cancelando uma ou várias contas
        	fachada.cancelarConta(colecaoContaImovel, identificadoresConta, contaMotivoCancelamento,
        	usuarioLogado, confirmadoCancelamentoContasPagas );
        	
        	//Realizar um reload na tela de manter conta
        	httpServletRequest.setAttribute("reloadPage", "OK");
        	
        }
        sessao.setAttribute("cancelar", "1");
                
        return retorno;
    }
    
    /**
     * 
     * [UC0146ManterConta] - Manter Conta
     * 
     * Metodo verifica se existe contas já pagas nas contas que serão canceladas.
     * Em caso afirmativo, apresenta todos os meses de referencia das contas
     * para informar ao usuário.
     *
     * @author bruno
     * @date 06/05/2009
     *
     * @param String[]: Vetor com todos os id's das contas
     * @param request: Onde se serão informados os parametros para geração da página
     * @param actionMapping: Necessário para geração da página
     * 
     * @return Object[3]
     * 
     *      Object[0]: ActionFoward com a tela a ser mostrada
     *      Object[1]: Se o usuário confimou ou não a inserção do novo ra
     *      Object[2]: Se será redirecionado ao usuário perguntando se as
     *                 contas terão os cnta_id removidos dos pagamentos.
     */
    private Object[] validarContasCanceladasPagasRA( 
            String[] idsContas, 
            HttpServletRequest request,
            ActionMapping actionMapping ){
        
        Object[] retorno = new Object[3];
        
        // Verificamos se ja foi confimado...
        retorno[1] = 
            ( request.getParameter("confirmado") != null ? 
                    request.getParameter("confirmado").equals("ok") : 
                        null );
        
        retorno[2] = new Boolean( Boolean.FALSE );
        
        if ( retorno[1] == null ){
            Fachada fachada = Fachada.getInstancia();
            
            Collection<String> colContasPagas = new ArrayList();

            for ( int i=0; i<idsContas.length; i++ ){
                String dadosConta = idsContas[i];
                String id = dadosConta.split("-")[0];
                
                
                FiltroPagamento filtro = 
                    new FiltroPagamento();
                
                filtro.adicionarParametro( new ParametroSimples( 
                        FiltroPagamento.CONTA_ID, 
                        Integer.valueOf(id) ) );
                
                Collection<Pagamento> colPagamento = 
                    fachada.pesquisar( 
                            filtro, 
                            Pagamento.class.getName() ); 
                
                Pagamento pagamento = ( Pagamento )Util.retonarObjetoDeColecao( colPagamento );
                
                if ( pagamento != null ){
                    if ( colContasPagas.size() == 0 ){
                        colContasPagas.add( Util.formatarAnoMesParaMesAno( pagamento.getAnoMesReferenciaPagamento() ) );
                    } else {
                        colContasPagas.add( ", " + Util.formatarAnoMesParaMesAno( pagamento.getAnoMesReferenciaPagamento() ) );    
                    }
                    
                }
            }
            
            if ( colContasPagas.size() > 0 ){            
                String[] arrayIdsContas = new String[ colContasPagas.size() ];            
                System.arraycopy( colContasPagas.toArray(), 0, arrayIdsContas, 0, arrayIdsContas.length );            
                
                request.setAttribute("caminhoActionConclusao",
                        "/gsan/cancelarContaAction.do");
                request.setAttribute("cancelamento", "TRUE");
                request.setAttribute("nomeBotao1", "Sim");
                request.setAttribute("nomeBotao2", "Não");
    
                retorno[0] = montarPaginaConfirmacao(
                        "atencao.contas_cancelamento_pagas",
                        request, 
                        actionMapping, 
                        arrayIdsContas );
                
                retorno[2] = new Boolean( Boolean.TRUE );
            }
            return retorno;
        }       
        
        retorno[0] = actionMapping.findForward("telaSucesso");
        return retorno;
    }
}
