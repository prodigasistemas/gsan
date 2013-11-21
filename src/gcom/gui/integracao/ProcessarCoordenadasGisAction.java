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
 * Yara Taciane de Souza
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
package gcom.gui.integracao;

import gcom.gui.GcomAction;
import gcom.gui.SessaoHttpListener;
import gcom.integracao.GisRetornoMotivo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action utilizado
 * 
 * 
 * 
 * @author Genival Barbosa
 * @since 06/05/2009
 */
public class ProcessarCoordenadasGisAction extends GcomAction {
	
	/**
	 * Action que captura as requisições vindas da Integração do Gis com o Gsan. 
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		
		ActionForward actionForward = null;			
		
		//RETORNO_MOTIVO GSAN			
		Integer retorno = GisRetornoMotivo.OPERACAO_SUCESSO;	
			
		String loginUsuario = request.getParameter("usur_nmlogin");
		
		//Flag usada para verificar se a requisição gis veio da tela correta. 
		//inserção de um R.A (Aba nº 02 - Dados do local de ocorrência
		HttpSession sessaoUsuario = SessaoHttpListener.listaSessoesAtivasGis.get(loginUsuario);
		
		if(sessaoUsuario == null){
			retorno = GisRetornoMotivo.LOGIN_USUARIO_INEXISTENTE;		
		}else{
			//Boolean marcadorGis = (Boolean) sessao.getAttribute("gis");
		     
		    GisHelper gisHelper = new GisHelper();
		     
				
				//Login do usuário
					
				if(loginUsuario==null){
					retorno = GisRetornoMotivo.LOGIN_USUARIO_INEXISTENTE;
				}		
				
				//Coordenada norte da ocorrência
				String nnCoordenadaNorte = request.getParameter("rgat_nncoordenadanorte");	
				if("".equals(nnCoordenadaNorte) || nnCoordenadaNorte == null){
					retorno = GisRetornoMotivo.COORDENADA_NORTE_INVALIDA;
				}else{
					gisHelper.setNnCoordenadaNorte(nnCoordenadaNorte);
				}
				
				//Coordenada leste da ocorrência
				String nnCoordenadaLeste = request.getParameter("rgat_nncoordenadaleste");
				if("".equals(nnCoordenadaLeste) || nnCoordenadaLeste == null){
					retorno = GisRetornoMotivo.COORDENADA_LESTE_INVALIDA;
				}else{
					gisHelper.setNnCoordenadaLeste(nnCoordenadaLeste);
				}							
				
				//Colocando o helper na sessão.
				sessaoUsuario.setAttribute("gisHelper",gisHelper);	
		}
		
	
			try{
			
		    PrintWriter pw = response.getWriter();	  
		    pw.println(retorno);	
		    pw.flush();
	        
		     } catch (IOException e) {
		           e.printStackTrace();
		     }

		
		return actionForward;
	}
}