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
package gcom.gui.cadastro;

import gcom.cadastro.ImovelInscricaoAlteradaHelper;
import gcom.cadastro.imovel.FiltroImovelInscricaoAlterada;
import gcom.cadastro.imovel.ImovelInscricaoAlterada;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AutorizarAlteracaoInscricaoImovelAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
			ImovelInscricaoAlteradaHelper helper = (ImovelInscricaoAlteradaHelper) 
				sessao.getAttribute("imovelInscricaoAlterada");
		
			
	        String ids = (String) httpServletRequest.getParameter("indicadorAutorizar");

	        String[] idsQuadrasArray = ids.split(",");

	         //mensagem de erro quando o usuário tenta autorizar sem ter selecionado
	         //nenhum registro
	        if (idsQuadrasArray == null || idsQuadrasArray.length == 0) {
	            throw new ActionServletException("atencao.registros.nao_selecionados");
	        }

	        Collection colecaoImovelInscricaoAlterada = new ArrayList();
	        
	        for(int x=0; x<idsQuadrasArray.length; x++){
	        
	        FiltroImovelInscricaoAlterada filtroImovelInscricaoAlterada = new FiltroImovelInscricaoAlterada();
	        
	        filtroImovelInscricaoAlterada.adicionarParametro(
	        		new ParametroSimples(
	        				FiltroImovelInscricaoAlterada.SETOR_COMERCIAL_ATUAL_CODIGO, helper.getCodigoSetorComercial()));
	        filtroImovelInscricaoAlterada.adicionarParametro(
	        		new ParametroSimples(
	        				FiltroImovelInscricaoAlterada.LOCALIDADE_ATUAL, helper.getIdLocalidade()));
	        filtroImovelInscricaoAlterada.adicionarParametro(
	        		new ParametroSimples(
	        				FiltroImovelInscricaoAlterada.QUADRA_ATUAL, idsQuadrasArray[x]));
	        filtroImovelInscricaoAlterada.adicionarParametro(
	        		new ParametroSimples(
	        				FiltroImovelInscricaoAlterada.INDICADOR_ALTERACAO_EXCLUIDA, ConstantesSistema.NAO));
	        filtroImovelInscricaoAlterada.adicionarParametro(
	        		new ParametroSimples(
	        				FiltroImovelInscricaoAlterada.INDICADOR_ATUALIZADO, ConstantesSistema.NAO));
	        filtroImovelInscricaoAlterada.adicionarParametro(
	        		new ParametroNulo(
	        				FiltroImovelInscricaoAlterada.INDICADOR_ERRO_ALTERACAO));
	        filtroImovelInscricaoAlterada.adicionarParametro(
	        		new ParametroSimples(
	        				FiltroImovelInscricaoAlterada.INDICADOR_AUTORIZADO, ConstantesSistema.NAO));
	
	        
	        colecaoImovelInscricaoAlterada = fachada.pesquisar(filtroImovelInscricaoAlterada, ImovelInscricaoAlterada.class.getName());
	        
	        Iterator iColecaoImovelInscricaoAlterada = colecaoImovelInscricaoAlterada.iterator();
	        
	        if (colecaoImovelInscricaoAlterada != null && !colecaoImovelInscricaoAlterada.isEmpty()){
	        	
	        	while (iColecaoImovelInscricaoAlterada.hasNext()){
	        		
	        	ImovelInscricaoAlterada imovelInscricaoAlterada = (ImovelInscricaoAlterada) iColecaoImovelInscricaoAlterada.next();
	        	
	        	imovelInscricaoAlterada.setIndicadorAutorizado(ConstantesSistema.SIM);
	        	imovelInscricaoAlterada.setUsuarioAutorizacao(usuario);
	        	
	        	//------------ REGISTRAR TRANSAÇÃO ----------------
//	    		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
//	    			Operacao.OPERACAO_AUTORIZAR_ALTERACAO_INSCRICAO_IMOVEL,
//	    			ImovelInscricaoAlterada.getId(), ImovelInscricaoAlterada.getId(),
//	    			new UsuarioAcaoUsuarioHelper(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
//	    			
//	    		registradorOperacao.registrarOperacao(ImovelInscricaoAlterada);
	    		
	    		//------------ REGISTRAR TRANSAÇÃO ----------------
	        		
	        	fachada.atualizar(imovelInscricaoAlterada);
	        	
	        	}
	        }
	        
	        }
	        
	        
			montarPaginaSucesso(httpServletRequest, "Alteração da inscrição do(s) imovel(s) autorizado(s) com sucesso.",
					"Realizar outra Autorização de Alteração de Inscrição de Imovel(s) com  ",
					"exibirFiltrarAlteracaoInscricaoImovelAction.do?menu=sim");
			
		
		
        
		// devolve o mapeamento de retorno
		return retorno;
	}

		
}
