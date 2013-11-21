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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelCobrancaSituacao;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirInformarImovelSituacaoCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirInformarImovelSituacaoCobranca");

		Fachada fachada = Fachada.getInstancia();
				
		// Mudar isso quando tiver esquema de segurança
		// HttpSession sessao = httpServletRequest.getSession(false);
		
		InformarImovelSituacaoCobrancaActionForm informarImovelSituacaoCobrancaActionForm = (InformarImovelSituacaoCobrancaActionForm) actionForm;
		
		
		if (httpServletRequest.getParameter("objetoConsulta") != null ){
			Integer idImovel = new Integer(informarImovelSituacaoCobrancaActionForm.getIdImovel()); 
			if (idImovel != null){
				String matriculaImovel = fachada.pesquisarInscricaoImovel(idImovel);
				Imovel imovel = fachada.pesquisarImovelComSituacaoAguaEsgoto(idImovel);
				httpServletRequest.setAttribute("imovel", imovel);
				if (matriculaImovel != null && !matriculaImovel.equalsIgnoreCase("")){
					String enderecoImovel = fachada.pesquisarEndereco(idImovel);
					httpServletRequest.setAttribute("endereco", enderecoImovel);
					httpServletRequest.setAttribute("matriculaImovel", matriculaImovel);
					
					FiltroImovelCobrancaSituacao filtroImovelCobrancaSituacao = new FiltroImovelCobrancaSituacao();
					filtroImovelCobrancaSituacao.adicionarParametro(new ParametroSimples(
							FiltroImovelCobrancaSituacao.IMOVEL_ID, idImovel.toString()));
					filtroImovelCobrancaSituacao.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacao");
					filtroImovelCobrancaSituacao.adicionarCaminhoParaCarregamentoEntidade("cliente");
					filtroImovelCobrancaSituacao.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoAguaSituacao");
					filtroImovelCobrancaSituacao.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoEsgotoSituacao");
					Collection colecaoImovelCobrancaSituacao = fachada.pesquisar(
							filtroImovelCobrancaSituacao, ImovelCobrancaSituacao.class.getName());
					
					boolean escondeRetirar = true;
					boolean existeNaoRealizado = false;
					
					if (colecaoImovelCobrancaSituacao != null && !colecaoImovelCobrancaSituacao.isEmpty()){
						httpServletRequest.setAttribute("situacoes", colecaoImovelCobrancaSituacao);
						
						for (Iterator iter = colecaoImovelCobrancaSituacao.iterator(); iter.hasNext();) {
							ImovelCobrancaSituacao imovelCobrancaSituacao = (ImovelCobrancaSituacao) iter.next();
							if (imovelCobrancaSituacao.getDataRetiradaCobranca() == null) {
								existeNaoRealizado = true;
//								escondeInserir = true;
								Short indicadorRetirada = imovelCobrancaSituacao.getCobrancaSituacao().getIndicadorBloqueioRetirada();
								if (indicadorRetirada == null || indicadorRetirada.equals(new Short("2"))) {
									escondeRetirar = false;
								}
							}
						}
						
						if (existeNaoRealizado == false) { 
							escondeRetirar = true; 
						} 
					} else {
						escondeRetirar = true;
					}
					
//					if (escondeInserir) httpServletRequest.setAttribute("escondeInserir", "sim");
					if (escondeRetirar) httpServletRequest.setAttribute("escondeRetirar", "sim");
					
				} else {
					httpServletRequest.setAttribute("inexistente", 1);
					httpServletRequest.setAttribute("escondeInserir", "sim");
					httpServletRequest.setAttribute("escondeRetirar", "sim");
				}
			}
		}else{
			//o sistema desabilita a primeira vez q entra na tela
			httpServletRequest.setAttribute("escondeInserir", "sim");
			httpServletRequest.setAttribute("escondeRetirar", "sim");
		}

		
		return retorno;

	}

}
