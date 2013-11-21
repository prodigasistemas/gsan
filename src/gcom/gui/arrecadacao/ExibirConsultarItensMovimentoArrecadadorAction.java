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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.ArrecadadorMovimento;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.bean.ArrecadadorMovimentoItemHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Esta classe tem por finalidade exibir para o usuário os itens do movimento do arrecadador
 *
 * @author Raphael Rossiter
 * @date 20/03/2006
 */
public class ExibirConsultarItensMovimentoArrecadadorAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

	        ActionForward retorno = actionMapping.findForward("exibirConsultarItensMovimentoArrecadador");
	        
	        HttpSession sessao = getSessao(httpServletRequest);
	        
	        PesquisarItensMovimentoArrecadadorActionForm pesquisarItensMovimentoArrecadadorActionForm = 
	        	(PesquisarItensMovimentoArrecadadorActionForm) actionForm;
	        
	        String idArrecadadorMovimento = pesquisarItensMovimentoArrecadadorActionForm.getIdArrecadadorMovimento();
	        
	        Fachada fachada = Fachada.getInstancia();
	        
	        ArrecadadorMovimento arrecadadorMovimento = new ArrecadadorMovimento();
	        arrecadadorMovimento.setId(new Integer(idArrecadadorMovimento));
	        
	        Integer idImovel = null ;
			String retornoImovel = "";
	        if (pesquisarItensMovimentoArrecadadorActionForm.getMatriculaImovel() != null 
					&& !pesquisarItensMovimentoArrecadadorActionForm.getMatriculaImovel() .equals("")) {
	        	idImovel = new Integer(pesquisarItensMovimentoArrecadadorActionForm.getMatriculaImovel());
	        	
				retornoImovel = fachada.pesquisarInscricaoImovel(idImovel);
				if(retornoImovel == null || retornoImovel.equalsIgnoreCase(""))
				{
					throw new ActionServletException(
						"atencao.imovel.inexistente");
				}
			}else{
				sessao.removeAttribute("valorDadosMovimento");
				sessao.removeAttribute("valorDadosPagamento");
			}

	        String nomeArrecadador = "";
	        
	        Collection<Object[]> nomeArrecadadorNomeAgencia = null;
			
			if(idArrecadadorMovimento != null){
				nomeArrecadadorNomeAgencia = fachada.consultarNomeArrecadadorNomeAgencia( idArrecadadorMovimento );
				
				Iterator iteDados= nomeArrecadadorNomeAgencia.iterator();
				
				while (iteDados.hasNext()) {
					
					String dados = (String) iteDados.next();
				
					if (dados != null) {
						
						if (dados != null) {
							nomeArrecadador = (String) dados;
						} 
					}
				}
				
			}	
	        
			sessao.setAttribute("nomeArrecadador", nomeArrecadador);
	        
			Short indicadorAceitacao = null;
			if (pesquisarItensMovimentoArrecadadorActionForm.getIndicadorAceitacao()!= null &&
					!pesquisarItensMovimentoArrecadadorActionForm.getIndicadorAceitacao().equals("") &&
					!pesquisarItensMovimentoArrecadadorActionForm.getIndicadorAceitacao().equals("3") &&
					!pesquisarItensMovimentoArrecadadorActionForm.getIndicadorAceitacao().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				
				indicadorAceitacao = new Short(pesquisarItensMovimentoArrecadadorActionForm.getIndicadorAceitacao());
			}
			
			String descricaoOcorrencia = null;
			if (pesquisarItensMovimentoArrecadadorActionForm.getDescricaoOcorrencia()!= null &&
					!pesquisarItensMovimentoArrecadadorActionForm.getDescricaoOcorrencia().equals("") &&
					!pesquisarItensMovimentoArrecadadorActionForm.getDescricaoOcorrencia().equals("3") &&
					!pesquisarItensMovimentoArrecadadorActionForm.getDescricaoOcorrencia().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				
				descricaoOcorrencia = pesquisarItensMovimentoArrecadadorActionForm.getDescricaoOcorrencia();
			}
			
			// ------------ Forma de Arrecadacao --------------- Kássia Albuquerque
			
			String codigoArrecadacaoForma = null;
			if (pesquisarItensMovimentoArrecadadorActionForm.getFormaArrecadacao()!= null && 
					!pesquisarItensMovimentoArrecadadorActionForm.getFormaArrecadacao().equals("")&&
					!pesquisarItensMovimentoArrecadadorActionForm.getFormaArrecadacao().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
				
				FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();
				
				filtroArrecadacaoForma.adicionarParametro(new ParametroSimples(FiltroArrecadacaoForma.CODIGO,
						pesquisarItensMovimentoArrecadadorActionForm.getFormaArrecadacao()));
				
				Collection<ArrecadacaoForma> colecaoArrecadacaoForma = fachada.pesquisar(filtroArrecadacaoForma,ArrecadacaoForma.class.getName());
				
				if (colecaoArrecadacaoForma!= null && !colecaoArrecadacaoForma.isEmpty()){
					
					ArrecadacaoForma arrecadacaoForma = (ArrecadacaoForma)colecaoArrecadacaoForma.iterator().next();
					codigoArrecadacaoForma = arrecadacaoForma.getCodigoArrecadacaoForma();
					String descricaoArrecadacao = arrecadacaoForma.getDescricao();
					
					pesquisarItensMovimentoArrecadadorActionForm.setDescricaoFormaArrecadacao(descricaoArrecadacao);
				}
			}
			
			Short indicadorDiferencaValorMovimentoValorPagamento = Short.parseShort( pesquisarItensMovimentoArrecadadorActionForm.getIndicadorDiferencaValorMovimentoValorPagamento() );
	        
	        Collection colecaoArrecadadorMovimentoItemHelper =fachada.
        	consultarItensMovimentoArrecadador(arrecadadorMovimento,idImovel,indicadorAceitacao,descricaoOcorrencia
	        			,codigoArrecadacaoForma, indicadorDiferencaValorMovimentoValorPagamento );
	        
	        
	        // ------------- Agrupando os valores Totais do Movimento e do Pagamento ------- Kássia Albuquerque
	        
	        ArrecadadorMovimentoItemHelper helper = null;
	        BigDecimal valorDadosMovimento = new BigDecimal("0.00");
	        BigDecimal valorDadosPagamento = new BigDecimal("0.00");
			
	
			if (colecaoArrecadadorMovimentoItemHelper != null && !colecaoArrecadadorMovimentoItemHelper.isEmpty()) {
				
				Iterator colecaoArrecadadorMovimentoItemHelperIterator = colecaoArrecadadorMovimentoItemHelper.iterator();
				// percorre a colecao de debito a cobrar somando o valor para obter um valor total
				while (colecaoArrecadadorMovimentoItemHelperIterator.hasNext()) {
	
					helper = (ArrecadadorMovimentoItemHelper) colecaoArrecadadorMovimentoItemHelperIterator.next();
					
					if (helper.getVlMovimento()!= null && !helper.getVlMovimento().equals("")){
						
						valorDadosMovimento = valorDadosMovimento.add(Util.formatarMoedaRealparaBigDecimal(helper.getVlMovimento()));
					}
					
					if (helper.getVlPagamento()!= null && !helper.getVlPagamento().equals("")){
						
						valorDadosPagamento = valorDadosPagamento.add(Util.formatarMoedaRealparaBigDecimal(helper.getVlPagamento()));
					}
					
					
				}
				
				sessao.setAttribute("valorDadosMovimento", Util .formatarMoedaReal(valorDadosMovimento));
				sessao.setAttribute("valorDadosPagamento", Util .formatarMoedaReal(valorDadosPagamento));
			}
	        
			httpServletRequest.setAttribute("colecaoArrecadadorMovimentoItemHelper", colecaoArrecadadorMovimentoItemHelper);

			pesquisarItensMovimentoArrecadadorActionForm.setColecaoArrecadadorMovimentoItem(colecaoArrecadadorMovimentoItemHelper);
		      
	       
	       return retorno;
	    }

}
