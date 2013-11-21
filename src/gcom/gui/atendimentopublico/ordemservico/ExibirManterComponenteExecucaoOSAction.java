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

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.EquipeComponentes;
import gcom.atendimentopublico.ordemservico.FiltroEquipe;
import gcom.atendimentopublico.ordemservico.FiltroEquipeComponentes;
import gcom.atendimentopublico.ordemservico.OsExecucaoEquipe;
import gcom.atendimentopublico.ordemservico.OsExecucaoEquipeComponentes;
import gcom.atendimentopublico.ordemservico.bean.ManterDadosAtividadesOrdemServicoHelper;
import gcom.atendimentopublico.ordemservico.bean.OSAtividadePeriodoExecucaoHelper;
import gcom.atendimentopublico.ordemservico.bean.OSExecucaoEquipeHelper;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os
 * parâmetros para realização da atualização dos dados das atividades de uma OS
 * (Componente)
 * 
 * @author Raphael Rossiter
 * @date 23/10/2006
 */
public class ExibirManterComponenteExecucaoOSAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("manterComponenteExecucaoOS");

		ManterComponenteExecucaoOSActionForm form = (ManterComponenteExecucaoOSActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		//Carregamento inicial
		String numeroOS = httpServletRequest.getParameter("numeroOS");
		
		if (numeroOS != null && !numeroOS.equalsIgnoreCase("")){
			
			String idAtividade = httpServletRequest.getParameter("idAtividade");
			String descricaoAtividade = httpServletRequest.getParameter("descricaoAtividade");
			String idEquipe = httpServletRequest.getParameter("idEquipe");
			String dataExecucaoEquipeComponente = httpServletRequest.getParameter("dataExecucaoEquipeComponente");
			
			dataExecucaoEquipeComponente = dataExecucaoEquipeComponente.replace("_","/");
			
			String horaInicioExecucaoEquipeComponente = httpServletRequest.getParameter("horaInicioExecucaoEquipeComponente");
			String horaFimExecucaoEquipeComponente = httpServletRequest.getParameter("horaFimExecucaoEquipeComponente");
			
			//Equipe
			FiltroEquipe filtroEquipe = new FiltroEquipe();
			
			filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.ID, 
			idEquipe));
			
			Collection colecaoEquipe = 
			fachada.pesquisar(filtroEquipe, Equipe.class.getName());
			
			Equipe equipe = (Equipe) Util.retonarObjetoDeColecao(colecaoEquipe);
			
			form.setNumeroOSForm(numeroOS);
			form.setIdAtividade(idAtividade);
			form.setDescricaoAtividade(descricaoAtividade);
			form.setNomeEquipe(equipe.getNome());
			
			form.setDataExecucao(Util.converteStringParaDate(dataExecucaoEquipeComponente));
			form.setHoraInicio(horaInicioExecucaoEquipeComponente);
			form.setHoraFim(horaFimExecucaoEquipeComponente);
			form.setIdEquipe(Util.converterStringParaInteger(idEquipe));
			
			//Componentes já cadastrados
			FiltroEquipeComponentes filtroEquipeComponentes = new FiltroEquipeComponentes();
			
			filtroEquipeComponentes.adicionarCaminhoParaCarregamentoEntidade("equipe");
			filtroEquipeComponentes.adicionarCaminhoParaCarregamentoEntidade("funcionario");
			
			filtroEquipeComponentes.adicionarParametro(new ParametroSimples(FiltroEquipeComponentes.ID_EQUIPE, 
			idEquipe));
			
			Collection colecaoEquipeComponentes = 
			fachada.pesquisar(filtroEquipeComponentes, EquipeComponentes.class.getName());
			
			if (colecaoEquipeComponentes == null || colecaoEquipeComponentes.isEmpty()){
				
				/*throw new ActionServletException(
				"atencao.equipe_componentes_inexistente");*/
			}
			else{
				
				//Adicionar...
				Iterator iteratorColecaoEquipeComponentes = colecaoEquipeComponentes.iterator();
				EquipeComponentes equipeComponentes = new EquipeComponentes();
				
				Collection colecaoSessao = (Collection) 
				sessao.getAttribute("colecaoManterDadosAtividadesOrdemServicoHelper");
				
				while (iteratorColecaoEquipeComponentes.hasNext()){
					
					equipeComponentes = (EquipeComponentes) iteratorColecaoEquipeComponentes.next();
					
					if (equipeComponentes.getFuncionario() != null){
					
						this.informarOsExecucaoEquipeComponentes(Util.converterStringParaInteger(form.getIdAtividade()),
						form.getDataExecucao(), form.getHoraInicio(), form.getHoraFim(),
						equipeComponentes.getEquipe().getId(), colecaoSessao, 
						equipeComponentes.getFuncionario().getId(), equipeComponentes.getIndicadorResponsavel(),
						null, fachada, true);
					}
					else{
						
						this.informarOsExecucaoEquipeComponentes(Util.converterStringParaInteger(form.getIdAtividade()),
						form.getDataExecucao(), form.getHoraInicio(), form.getHoraFim(),
						equipeComponentes.getEquipe().getId(), colecaoSessao, 
						null, equipeComponentes.getIndicadorResponsavel(),
						equipeComponentes.getComponentes(), fachada, true);
					}
				}
			}
			
			
			//Inicializando o formulário
			form.setIdFuncionario("");
			form.setDescricaoFuncionario("");
			form.setNomeComponente("");
			form.setResponsavel(String.valueOf(EquipeComponentes.INDICADOR_RESPONSAVEL_NAO));
			
		}
		
		
		//Pesquisar Funcionario ENTER
		if ((form.getIdFuncionario() != null && !form.getIdFuncionario().equals("")) &&
			(form.getDescricaoFuncionario() == null || form.getDescricaoFuncionario().equals(""))){

			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
		
			filtroFuncionario.adicionarParametro(new ParametroSimples(FiltroFuncionario.ID,
			form.getIdFuncionario()));
			
			Collection colecaoFuncionario = fachada.pesquisar(filtroFuncionario,
			Funcionario.class.getName());
	
			if (colecaoFuncionario == null || colecaoFuncionario.isEmpty()) {
	
				form.setIdFuncionario("");
				form.setDescricaoFuncionario("FUNCIONÁRIO INEXISTENTE");
	
				httpServletRequest.setAttribute("corFuncionario", "exception");
				httpServletRequest.setAttribute("nomeCampo", "idFuncionario");
	
			} else {
				
				 Funcionario funcionario = (Funcionario) Util
				.retonarObjetoDeColecao(colecaoFuncionario);
	
				 form.setIdFuncionario(funcionario.getId().toString());
				 form.setDescricaoFuncionario(funcionario.getNome());
				
				httpServletRequest.setAttribute("nomeCampo", "botaoAdicionar");
			
			}
		}
		
		
		//Pesquisar Funcionário POPUP
		String pesquisarFuncionario = httpServletRequest.getParameter("pesquisarFuncionario");
		
		if (pesquisarFuncionario != null && !pesquisarFuncionario.equalsIgnoreCase("")){
			retorno = actionMapping.findForward("pesquisarFuncionario");
		}
		
		
		//Recebendo dados Funcionário POPUP
		if (httpServletRequest.getParameter("idCampoEnviarDados") != null){
			form.setIdFuncionario(httpServletRequest.getParameter("idCampoEnviarDados"));
			form.setDescricaoFuncionario(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
		
			httpServletRequest.setAttribute("nomeCampo", "botaoAdicionar");
		}
		
		
		//Adicionar
		String adicionarComponente = httpServletRequest.getParameter("adicionarComponente");
		
		if (adicionarComponente != null && !adicionarComponente.equalsIgnoreCase("")){
			
			//Verificando as informações colhidas
			//===================================================================================
			Integer idFuncionario = null;
			String nomeComponente = null;
			if (form.getIdFuncionario() != null && !form.getIdFuncionario().equalsIgnoreCase("")){
				idFuncionario = Util.converterStringParaInteger(form.getIdFuncionario());
			}
			else{
				nomeComponente = form.getNomeComponente();
			}
			
			//===================================================================================
			
			
			//Informando OS_EXECUCAO_EQUIPE_COMPONENTES
				
			Collection colecaoManterDadosAtividadesOrdemServicoHelper = (Collection)
			sessao.getAttribute("colecaoManterDadosAtividadesOrdemServicoHelper");
				
			this.informarOsExecucaoEquipeComponentes(Util.converterStringParaInteger(form.getIdAtividade()),
			form.getDataExecucao(), form.getHoraInicio(), form.getHoraFim(),
			form.getIdEquipe(), colecaoManterDadosAtividadesOrdemServicoHelper, 
			idFuncionario, Short.parseShort(form.getResponsavel()),
			nomeComponente, fachada, false);
			
			
			//Inicializando o formulário
			form.setIdFuncionario("");
			form.setDescricaoFuncionario("");
			form.setNomeComponente("");
			form.setResponsavel(String.valueOf(EquipeComponentes.INDICADOR_RESPONSAVEL_NAO));
			
			httpServletRequest.setAttribute("nomeCampo", "idFuncionario");
		}
		
		
		//Remover
		String removerComponente = httpServletRequest.getParameter("removerComponente");
		if (removerComponente != null && !removerComponente.equalsIgnoreCase("")){
			
			Collection colecaoSessao = (Collection) sessao.getAttribute("colecaoManterDadosAtividadesOrdemServicoHelper");
			
			this.removerOsExecucaoEquipeComponentes(Util.converterStringParaInteger(form.getIdAtividade()),
			form.getDataExecucao(), form.getHoraInicio(), form.getHoraFim(), form.getIdEquipe(), colecaoSessao, 
			fachada, Long.parseLong(removerComponente));
			
			//Inicializando o formulário
			form.setIdFuncionario("");
			form.setDescricaoFuncionario("");
			form.setNomeComponente("");
			form.setResponsavel(String.valueOf(EquipeComponentes.INDICADOR_RESPONSAVEL_NAO));
			
			httpServletRequest.setAttribute("nomeCampo", "idFuncionario");
		}
		
		
		//Objetos utilizados apenas para facilitar a quebra na exibição
		httpServletRequest.setAttribute("numeroOS", form.getNumeroOSForm());
		httpServletRequest.setAttribute("idAtividade", form.getIdAtividade());
		httpServletRequest.setAttribute("dataExecucaoEquipeComponente", form.getDataExecucao());
		httpServletRequest.setAttribute("horaInicioExecucaoEquipeComponente", form.getHoraInicio());
		httpServletRequest.setAttribute("horaFimExecucaoEquipeComponente", form.getHoraFim());
		httpServletRequest.setAttribute("idEquipe", form.getIdEquipe());
		
		
		return retorno;
	}
	
	
	
	private void informarOsExecucaoEquipeComponentes(Integer idAtividade, Date dataExecucao,
			String horaInicio, String horaFim, Integer idEquipe, Collection colecaoSessao, 
			Integer idFuncionario, short responsavel, String nomeComponente, Fachada fachada,
			boolean carregamentoInicial){
		
		Iterator iteratorColecaoSessao = colecaoSessao.iterator();
		ManterDadosAtividadesOrdemServicoHelper manterDadosAtividadesOrdemServicoHelper = null;
		OSAtividadePeriodoExecucaoHelper osAtividadePeriodoExecucaoHelper = null;
		OSExecucaoEquipeHelper osExecucaoEquipeHelper = null;
		OsExecucaoEquipeComponentes osExecucaoEquipeComponentes = null;
		
		boolean responsavelInformado = false;
		boolean equipeLocalizada = false;
		
		//Atividade
		while (iteratorColecaoSessao.hasNext()){
			
			manterDadosAtividadesOrdemServicoHelper = (ManterDadosAtividadesOrdemServicoHelper)
			iteratorColecaoSessao.next();
			
			if (manterDadosAtividadesOrdemServicoHelper.getOrdemServicoAtividade().getAtividade().getId()
				.equals(idAtividade)){
				
				//Período
				Collection colecaoOSAtividadePeriodoExecucaoHelper = 
				manterDadosAtividadesOrdemServicoHelper.getColecaoOSAtividadePeriodoExecucaoHelper();
				
				Iterator iteratorColecaoOSAtividadePeriodoExecucaoHelper = 
				colecaoOSAtividadePeriodoExecucaoHelper.iterator();
				
				while (iteratorColecaoOSAtividadePeriodoExecucaoHelper.hasNext()){
					
					osAtividadePeriodoExecucaoHelper = (OSAtividadePeriodoExecucaoHelper)
					iteratorColecaoOSAtividadePeriodoExecucaoHelper.next();
					
					
					if (osAtividadePeriodoExecucaoHelper.getDataExecucaoParaQuebra().equals(dataExecucao) &&
						osAtividadePeriodoExecucaoHelper.getHoraMinutoInicioParaQuebra().equalsIgnoreCase(horaInicio) &&
						osAtividadePeriodoExecucaoHelper.getHoraMinutoFimParaQuebra().equalsIgnoreCase(horaFim)){
						
						
						//Equipe
						Collection colecaoOSExecucaoEquipeHelper = osAtividadePeriodoExecucaoHelper
						.getColecaoOSExecucaoEquipeHelper();
						
						Iterator iteratorColecaoOSExecucaoEquipeHelper = colecaoOSExecucaoEquipeHelper.iterator();
					
						while (iteratorColecaoOSExecucaoEquipeHelper.hasNext()){
							
							osExecucaoEquipeHelper = (OSExecucaoEquipeHelper) iteratorColecaoOSExecucaoEquipeHelper.next();
						
							if (osExecucaoEquipeHelper.getOsExecucaoEquipe()
								.getEquipe().getId().equals(idEquipe)){
								
								equipeLocalizada = true;
								
								//Componente
								Collection colecaoOSExecucaoEquipeComponentes = osExecucaoEquipeHelper
								.getColecaoOsExecucaoEquipeComponentes();
								
								if (colecaoOSExecucaoEquipeComponentes != null &&
									!colecaoOSExecucaoEquipeComponentes.isEmpty()){
									
									
									if (!carregamentoInicial){
										
										Iterator iteratorColecaoOSExecucaoEquipeComponentes = 
										colecaoOSExecucaoEquipeComponentes.iterator();
												
										while (iteratorColecaoOSExecucaoEquipeComponentes.hasNext()){
													
												osExecucaoEquipeComponentes = (OsExecucaoEquipeComponentes)
												iteratorColecaoOSExecucaoEquipeComponentes.next();
													
												if (idFuncionario != null){
													
													if (osExecucaoEquipeComponentes.getFuncionario() != null &&
														osExecucaoEquipeComponentes.getFuncionario().getId().equals(idFuncionario)){
															
														throw new ActionServletException(
														"atencao.equipe_componente_ja_informado");
													}
														
												}
												else{
														
													if (osExecucaoEquipeComponentes.getNomeComponente() != null &&
														osExecucaoEquipeComponentes.getNomeComponente()
														.equalsIgnoreCase(nomeComponente)){
																
														throw new ActionServletException(
														"atencao.equipe_componente_ja_informado");
													}
												}
													
												if (osExecucaoEquipeComponentes.getIndicadorResponsavel() == 
													ConstantesSistema.INDICADOR_USO_ATIVO.shortValue()){
														
													responsavelInformado = true;
												}
										}
											
										if (responsavelInformado && 
											responsavel == ConstantesSistema.INDICADOR_USO_ATIVO.shortValue()){
												
												throw new ActionServletException(
												"atencao.responsavel_ja_informado");
										}
										
										
										//Gerando objeto
										osExecucaoEquipeComponentes = this.gerarOsExecucaoEquipeComponentes(osExecucaoEquipeHelper
										.getOsExecucaoEquipe(), idFuncionario, nomeComponente, responsavel, fachada);
										
										osExecucaoEquipeHelper.getColecaoOsExecucaoEquipeComponentes().add(osExecucaoEquipeComponentes);
									}
								}
								else{
									
									Collection colecaoComponentes = new ArrayList();
									
									//Gerando objeto
									osExecucaoEquipeComponentes = this.gerarOsExecucaoEquipeComponentes(osExecucaoEquipeHelper
									.getOsExecucaoEquipe(), idFuncionario, nomeComponente, responsavel, fachada);
								
									colecaoComponentes.add(osExecucaoEquipeComponentes);
									
									osExecucaoEquipeHelper.setColecaoOsExecucaoEquipeComponentes(colecaoComponentes);
								}
							}
							
							if (equipeLocalizada){
								break;
							}
						}
					}
					
					if (equipeLocalizada){
						break;
					}
				}
			}
			
			if (equipeLocalizada){
				break;
			}
		}
	}
	
	
	private OsExecucaoEquipeComponentes gerarOsExecucaoEquipeComponentes(OsExecucaoEquipe osExecucaoEquipe, 
			Integer idFuncionario, String nomeComponente, short responsavel, Fachada fachada){
		
		OsExecucaoEquipeComponentes osExecucaoEquipeComponentes = 
		new OsExecucaoEquipeComponentes();
		
		osExecucaoEquipeComponentes.setOsExecucaoEquipe(osExecucaoEquipe);
		
		osExecucaoEquipeComponentes.setIndicadorResponsavel(responsavel);
		
		if (idFuncionario != null){
		
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			
			filtroFuncionario.adicionarParametro(new ParametroSimples(FiltroFuncionario.ID,
			idFuncionario));
			
			Collection colecaoFuncionario = fachada.pesquisar(filtroFuncionario,
			Funcionario.class.getName());

			if (colecaoFuncionario == null || colecaoFuncionario.isEmpty()) {

				throw new ActionServletException(
				"atencao.label_inexistente", null, "Funcionário");

			} else {
				
				 Funcionario funcionario = (Funcionario) Util
				.retonarObjetoDeColecao(colecaoFuncionario);
				 
				 osExecucaoEquipeComponentes.setFuncionario(funcionario);

			}
		}
		else{
			
			osExecucaoEquipeComponentes.setNomeComponente(nomeComponente);
		}
		
		osExecucaoEquipeComponentes.setUltimaAlteracao(new Date());
		
		return osExecucaoEquipeComponentes;
	}
	
	
	private void removerOsExecucaoEquipeComponentes(Integer idAtividade, Date dataExecucao,
			String horaInicio, String horaFim, Integer idEquipe, Collection colecaoSessao, 
			Fachada fachada, long identificadorComponente){
		
		Iterator iteratorColecaoSessao = colecaoSessao.iterator();
		ManterDadosAtividadesOrdemServicoHelper manterDadosAtividadesOrdemServicoHelper = null;
		OSAtividadePeriodoExecucaoHelper osAtividadePeriodoExecucaoHelper = null;
		OSExecucaoEquipeHelper osExecucaoEquipeHelper = null;
		OsExecucaoEquipeComponentes osExecucaoEquipeComponentes = null;
		
		boolean equipeLocalizada = false;
		
		//Atividade
		while (iteratorColecaoSessao.hasNext()){
			
			manterDadosAtividadesOrdemServicoHelper = (ManterDadosAtividadesOrdemServicoHelper)
			iteratorColecaoSessao.next();
			
			if (manterDadosAtividadesOrdemServicoHelper.getOrdemServicoAtividade().getAtividade().getId()
				.equals(idAtividade)){
				
				//Período
				Collection colecaoOSAtividadePeriodoExecucaoHelper = 
				manterDadosAtividadesOrdemServicoHelper.getColecaoOSAtividadePeriodoExecucaoHelper();
				
				Iterator iteratorColecaoOSAtividadePeriodoExecucaoHelper = 
				colecaoOSAtividadePeriodoExecucaoHelper.iterator();
				
				while (iteratorColecaoOSAtividadePeriodoExecucaoHelper.hasNext()){
					
					osAtividadePeriodoExecucaoHelper = (OSAtividadePeriodoExecucaoHelper)
					iteratorColecaoOSAtividadePeriodoExecucaoHelper.next();
					
					
					if (osAtividadePeriodoExecucaoHelper.getDataExecucaoParaQuebra().equals(dataExecucao) &&
						osAtividadePeriodoExecucaoHelper.getHoraMinutoInicioParaQuebra().equalsIgnoreCase(horaInicio) &&
						osAtividadePeriodoExecucaoHelper.getHoraMinutoFimParaQuebra().equalsIgnoreCase(horaFim)){
						
						
						//Equipe
						Collection colecaoOSExecucaoEquipeHelper = osAtividadePeriodoExecucaoHelper
						.getColecaoOSExecucaoEquipeHelper();
						
						Iterator iteratorColecaoOSExecucaoEquipeHelper = colecaoOSExecucaoEquipeHelper.iterator();
					
						while (iteratorColecaoOSExecucaoEquipeHelper.hasNext()){
							
							osExecucaoEquipeHelper = (OSExecucaoEquipeHelper) iteratorColecaoOSExecucaoEquipeHelper.next();
						
							if (osExecucaoEquipeHelper.getOsExecucaoEquipe()
								.getEquipe().getId().equals(idEquipe)){
								
								equipeLocalizada = true;
								
								//Componente
								Collection colecaoOSExecucaoEquipeComponentes = osExecucaoEquipeHelper
								.getColecaoOsExecucaoEquipeComponentes();
								
								if (colecaoOSExecucaoEquipeComponentes != null &&
									!colecaoOSExecucaoEquipeComponentes.isEmpty()){
									
									Iterator iteratorColecaoOSExecucaoEquipeComponentes = 
									colecaoOSExecucaoEquipeComponentes.iterator();
												
									while (iteratorColecaoOSExecucaoEquipeComponentes.hasNext()){
													
										osExecucaoEquipeComponentes = (OsExecucaoEquipeComponentes)
										iteratorColecaoOSExecucaoEquipeComponentes.next();
													
										if (GcomAction.obterTimestampIdObjeto(osExecucaoEquipeComponentes)
											== identificadorComponente){
												
											colecaoOSExecucaoEquipeComponentes.remove(osExecucaoEquipeComponentes);
											break;
										}
									}
								}
							}
							
							if (equipeLocalizada){
								break;
							}
						}
					}
					
					if (equipeLocalizada){
						break;
					}
				}
			}
			
			if (equipeLocalizada){
				break;
			}
		}
	}

}
