package gcom.gui.cadastro.entidadebeneficente;


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




import gcom.cadastro.imovel.EntidadeBeneficente;
import gcom.cadastro.imovel.FiltroEntidadeBeneficente;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**

 * [UC0916] Manter Entidade Beneficente.

 *  

 * @author Hugo Fernando

 * @date 21/01/2010

 * @since 4.1.6.4

 *

 */

public class ExibirManterEntidadeBeneficenteAction extends GcomAction{
	
	
	
	@Override
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, 
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		 ActionForward retorno = actionMapping.findForward("manterEntidadeBeneficente");


		 HttpSession sessao = httpServletRequest.getSession(false);
      
      
		// Limpa o atributo se o usuário voltou para o manter
		if (sessao.getAttribute("colecaoEntidadeBeneficenteTela") != null) {
			sessao.removeAttribute("colecaoEntidadeBeneficenteTela");
		}
      
      
		// Recupera o filtro passado pelo FiltrarEntidadeBeneficenteAction para
		// ser efetuada pesquisa
		FiltroEntidadeBeneficente filtroEntidadeBeneficente = (FiltroEntidadeBeneficente) sessao
				.getAttribute("filtroEntidadeBeneficente");
      

		
		// Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroEntidadeBeneficente, EntidadeBeneficente.class.getName());
		
		Collection colecaoEntidadeBeneficente = (Collection) resultado.get("colecaoRetorno");
		//Collection colecaoEntidadeBeneficente2 = new Collection();
		
		for (Iterator entidadeBeneficente = colecaoEntidadeBeneficente.iterator(); 
		entidadeBeneficente.hasNext();) {
			
			EntidadeBeneficente entidadebeneficenteAtual = (EntidadeBeneficente) entidadeBeneficente.next();
			
			if( entidadebeneficenteAtual.getAnoMesContratoInicial() != null ){
			String anoInicioAdesao = entidadebeneficenteAtual.getAnoMesContratoInicial().toString().substring(0,4);
			String mesInicioAdesao = entidadebeneficenteAtual.getAnoMesContratoInicial().toString().substring(4);
			
			if( mesInicioAdesao.length() == 1 ){
				mesInicioAdesao = "0"+mesInicioAdesao;	
			}
			
			entidadebeneficenteAtual.setInicioMesAnoAdesao2(mesInicioAdesao+"/"+anoInicioAdesao);
			}
			
			if( entidadebeneficenteAtual.getAnoMesContratoFinal() != null ){
			String anoFimAdesao = entidadebeneficenteAtual.getAnoMesContratoFinal().toString().substring(0,4);
			String mesFimAdesao = entidadebeneficenteAtual.getAnoMesContratoFinal().toString().substring(4);
			
			if( mesFimAdesao.length() == 1 ){
				mesFimAdesao = "0"+mesFimAdesao;	
			}
			
			entidadebeneficenteAtual.setFimMesAnoAdesao2(mesFimAdesao+"/"+anoFimAdesao);
			}
			
		}
		
		
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		

		// Verifica se a coleção retornada pela pesquisa é nula, em caso
		// afirmativo comunica ao usuário que não existe nenhuma funcionalidade
		// cadastrada
		// para a pesquisa efetuada e em caso negativo e se
		// atender a algumas condições seta o retorno para o
		// ExibirAtualizarentidadeBeneficenteAction, se não atender manda a
		// coleção pelo request para ser recuperado e exibido pelo jsp.

		if (colecaoEntidadeBeneficente != null && !colecaoEntidadeBeneficente.isEmpty()) {

			// Verifica se a coleção contém apenas um objeto, se está retornando
			// da paginação (devido ao esquema de paginação de 10 em 10 faz uma
			// nova busca), evitando, assim, que caso haja 11 elementos no
			// retorno da pesquisa e o usuário selecione o link para ir para a
			// segunda página ele não vá para tela de atualizar.

			if (colecaoEntidadeBeneficente.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {

				// Verifica se o usuário marcou o checkbox de atualizar no jsp
				// funcionario_filtrar. Caso todas as condições sejam
				// verdadeiras seta o retorno para o
				// ExibirAtualizarEntidadeBeneficenteAction e em caso negativo
				// manda a coleção pelo request.

				if (httpServletRequest.getParameter("atualizar") != null) {
					
					retorno = actionMapping.findForward("exibirAtualizar");
					
					EntidadeBeneficente entidadeBeneficente = (EntidadeBeneficente) colecaoEntidadeBeneficente
							.iterator().next();
					
					
					if( entidadeBeneficente.getAnoMesContratoInicial() != null ){
						String anoInicioAdesao = entidadeBeneficente.getAnoMesContratoInicial().toString().substring(0,4);
						String mesInicioAdesao = entidadeBeneficente.getAnoMesContratoInicial().toString().substring(4);
						
						if( mesInicioAdesao.length() == 1 ){
							mesInicioAdesao = "0"+mesInicioAdesao;	
						}
						
						entidadeBeneficente.setInicioMesAnoAdesao2(mesInicioAdesao+"/"+anoInicioAdesao);
					}
						
					if( entidadeBeneficente.getAnoMesContratoFinal() != null ){
						String anoFimAdesao = entidadeBeneficente.getAnoMesContratoFinal().toString().substring(0,4);
						String mesFimAdesao = entidadeBeneficente.getAnoMesContratoFinal().toString().substring(4);
						
						if( mesFimAdesao.length() == 1 ){
							mesFimAdesao = "0"+mesFimAdesao;	
						}
						
						entidadeBeneficente.setFimMesAnoAdesao2(mesFimAdesao+"/"+anoFimAdesao);
					}
					
					sessao.setAttribute("objetoEntidadeBeneficente", entidadeBeneficente);

				} 
				else{
					httpServletRequest.setAttribute("colecaoEntidadeBeneficente",colecaoEntidadeBeneficente);
				}
			} 
			else {
				// salva a colecao no request para os dodos da EntidadeBeneficente serem recuperados  
				// no jsp "entidade_beneficente_manter.jsp" e mostrado para o usuario.
				httpServletRequest.setAttribute("colecaoEntidadeBeneficente",colecaoEntidadeBeneficente);
			}
		} 
		else {
			// Nenhuma EntidadeBeneficente cadastrada
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		
		
 
        // Devolve o mapeamento de retorno 
		return retorno;

	}

}
