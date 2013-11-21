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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao;
import gcom.atendimentopublico.ordemservico.ProgramacaoRoteiro;
import gcom.atendimentopublico.ordemservico.bean.OSProgramacaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ConcluirElaborarOrdemServicoRoteiroCriteriosAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = 
			actionMapping.findForward("telaSucesso");
		
		ElaborarOrdemServicoRoteiroCriteriosActionForm elaborarActionForm = 
			(ElaborarOrdemServicoRoteiroCriteriosActionForm) actionForm;

		String data = elaborarActionForm.getDataRoteiro();
		Date dataRoteiro = Util.converteStringParaDate(data);
		
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		HashMap mapOsProgramacaoHelper = (HashMap) sessao.getAttribute("mapOsProgramacaoHelper");
		
		Collection colecao = mapOsProgramacaoHelper.values();
		Collection colecaoNova = this.removeOrdemServicoProgramacaoJaExistente(colecao,dataRoteiro);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Fachada.getInstancia().elaborarRoteiro(colecaoNova, usuario);
		
		//Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest,
			"Elaboração do Roteiro de Programação de Ordens de Serviços efetuado com sucesso",
			"Efetuar outra Elaboração do Roteiro",
			"exibirElaborarOrdemServicoRoteiroCriteriosAction.do?menu=sim&dataRoteiro="+elaborarActionForm.getDataRoteiro());
		
		return retorno;
	}
	
	/**
	 * Remove as ordem de servico programacao que já existe no banco de dados
	 * so existe porque possui id
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 *
	 * @param colecao de OS ProgramacaoHelper
	 * @return Collection<OSProgramacaoHelper>
	 */
	
	private Collection<OSProgramacaoHelper> removeOrdemServicoProgramacaoJaExistente(Collection colecao,Date dataRoteiroElaboracao){
		Collection<OSProgramacaoHelper> retorno = new ArrayList();
		
		Iterator itera = colecao.iterator();
		
		while (itera.hasNext()) {
			
			Collection colecaoOSProgramacaoHelper = (Collection) itera.next();

			Iterator iteraOsProgramacao = colecaoOSProgramacaoHelper.iterator();
			
			short sequencial = 0;
			
			while (iteraOsProgramacao.hasNext()) {

				OSProgramacaoHelper osProgramacaoHelper = (OSProgramacaoHelper) iteraOsProgramacao.next();
				
				OrdemServicoProgramacao ordemServicoProgramacao = 
					osProgramacaoHelper.getOrdemServicoProgramacao();
				
				if(ordemServicoProgramacao.getId() == null || 
					ordemServicoProgramacao.getId().intValue() == ConstantesSistema.NUMERO_NAO_INFORMADO){
					
					Date dataRoteiro = ordemServicoProgramacao.getProgramacaoRoteiro().getDataRoteiro();
					
					//Caso tenha data diferentes é necessario incluir 2 programacões: 
					if(Util.compararData(dataRoteiroElaboracao,dataRoteiro) != 0){
						
						short sequencialAnterior = ordemServicoProgramacao.getNnSequencialProgramacao();
						
						sequencial++;
						
						// 1.Ordem de serviço programação com a data final da programação
						ordemServicoProgramacao.setNnSequencialProgramacao(sequencial);
						retorno.add(osProgramacaoHelper);
						
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(dataRoteiroElaboracao);

						//Vai adcionando programação até a data Final de Programacao
						//Ex: dataRoteiroElaboracao = 10/01 e dataRoteiro(DataFinalProgramacao) = 12/01
						//Ex: 10/01 até 12/01
						while(Util.compararData(calendar.getTime(),dataRoteiro) == -1){
							
							// 2.Ordem de serviço programação com a data do roteiro da elaboração
							ProgramacaoRoteiro programacaoRoteiro = 
								new ProgramacaoRoteiro(
									ordemServicoProgramacao.getProgramacaoRoteiro().getDataRoteiro(),
									ordemServicoProgramacao.getProgramacaoRoteiro().getUltimaAlteracao(),
									ordemServicoProgramacao.getProgramacaoRoteiro().getUnidadeOrganizacional() );
							
							programacaoRoteiro.setDataRoteiro(calendar.getTime());

							OSProgramacaoHelper helper = new OSProgramacaoHelper();

							OrdemServicoProgramacao ordemServicoProgramacaoNova = 
								new OrdemServicoProgramacao(
									ordemServicoProgramacao.getId(),
									sequencialAnterior,
									ordemServicoProgramacao.getIndicadorAtivo(),
									ordemServicoProgramacao.getIndicadorEquipePrincipal(),
									ordemServicoProgramacao.getUltimaAlteracao(),
									ordemServicoProgramacao.getEquipe(),
									ordemServicoProgramacao.getUsuarioProgramacao(),
									ordemServicoProgramacao.getUsuarioFechamento(),
									programacaoRoteiro,
									ordemServicoProgramacao.getOsProgramNaoEncerMotivo(),
									ordemServicoProgramacao.getOrdemServico() );

							helper.setOrdemServicoProgramacao(ordemServicoProgramacaoNova);
							retorno.add(helper);
							
							//Adciona um dia a mais na data
							calendar.add(Calendar.DAY_OF_MONTH,1);
						}

					}else{
						retorno.add(osProgramacaoHelper);
					}
					

				}
				
			}
			
		}
		return retorno;
	}
}