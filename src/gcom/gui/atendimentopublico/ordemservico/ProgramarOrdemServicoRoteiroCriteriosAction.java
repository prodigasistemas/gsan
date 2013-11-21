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
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao;
import gcom.atendimentopublico.ordemservico.ProgramacaoRoteiro;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.bean.OSFiltroHelper;
import gcom.atendimentopublico.ordemservico.bean.OSProgramacaoHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterCargaTrabalhoEquipeHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterOSDistribuidasPorEquipeHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ProgramarOrdemServicoRoteiroCriteriosAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("programarOrdemServico");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Form
		ElaborarOrdemServicoRoteiroCriteriosActionForm 
			elaborarActionForm = (ElaborarOrdemServicoRoteiroCriteriosActionForm) actionForm;
		
		// Data do Roteiro
		Date dataRoteiro = Util.converteStringParaDate(elaborarActionForm.getDataRoteiro());		

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		String tipoAcao = httpServletRequest.getParameter("tipoAcao");

		// Número de OsProgramação que estão programadas
		int osProgramadas = elaborarActionForm.getProgramadas();
		
		// Há ação que será executada será de remover ou desfazer
		if(tipoAcao != null && tipoAcao.equals("R")){

			String idOs = httpServletRequest.getParameter("idOs");
			
			// Defaz todas as OS programadas
			if(idOs == null){
				osProgramadas =
					this.removerTodasOrdemServicoProgramada(sessao,elaborarActionForm.getProgramadas());

			// Remove as OS selecionada na tela
			}else{ 
				osProgramadas = 
					this.removerOrdemServicoProgramada(sessao,new Integer(idOs),elaborarActionForm.getProgramadas());
				
			}
			
			elaborarActionForm.setProgramadas(osProgramadas);
			
			// [SB0010]-Prepara Barra de Carga de Trabalho 
			this.preparaBarraCargaTrabalho(sessao,dataRoteiro);

		// Há ação que será executada será de programar a OS selecionada
		}else if (tipoAcao != null && tipoAcao.equals("P")) {
		
			// Equipe Selecionada
			Integer[] idsEquipeSelecionada = (Integer[]) elaborarActionForm.getEquipesSelecionadas();

			// Ordem de Servico Selecionada
			Integer[] idsOsSelecionada = (Integer[]) elaborarActionForm.getOsSelecionada();
			
			if(idsEquipeSelecionada.length == 0 || 
				idsEquipeSelecionada[0].intValue() == ConstantesSistema.NUMERO_NAO_INFORMADO && 
				idsOsSelecionada.length == 0 || 
				idsOsSelecionada[0].intValue() == ConstantesSistema.NUMERO_NAO_INFORMADO){
					
				throw new ActionServletException("atencao.nao.informado.equipe.ordem_servico.programacao");
			}

			if(idsEquipeSelecionada.length == 0 || 
				idsEquipeSelecionada[0].intValue() == ConstantesSistema.NUMERO_NAO_INFORMADO){
				
				throw new ActionServletException("atencao.nao.informado.equipe.programacao");
			}
			
			
			if(idsOsSelecionada.length == 0 || 
				idsOsSelecionada[0].intValue() == ConstantesSistema.NUMERO_NAO_INFORMADO){
				
				throw new ActionServletException("atencao.nao.informado.ordem_servico.programacao");
			}
			
			if(idsOsSelecionada.length > 1 && idsEquipeSelecionada.length > 1){
				throw new ActionServletException("atencao.informado.ordem_servico.equipe.programacao");
			}

			
			//Selecionou mais de uma equipe,tem que informar que eh principal
			int idEquipePrincipal = 
				new Integer(httpServletRequest.getParameter("principal")).intValue();

			int i = 0;
			for (Integer idOs : idsOsSelecionada) {
				
				boolean programouOS = false;
				
				Set<Equipe> colecaoEquipesPorOS = new HashSet();
				
				for (Integer idEquipe : idsEquipeSelecionada) {
					
					Equipe equipe = pesquisarEquipePorId(sessao,idEquipe);
					
					OrdemServico ordemServico = 
						retornaOrdemServicoDaColecaoProgramada(sessao,idOs,equipe.getNome());
					
					colecaoEquipesPorOS.add(equipe);
					
					if(ordemServico == null){
						
						programouOS = true;
						
						OrdemServico os = this.retornaOrdemServicoSelecionadasPorId(sessao,idOs);

						OrdemServicoProgramacao ordemServicoProgramacao = new OrdemServicoProgramacao();
						ordemServicoProgramacao.setOrdemServico(os);
						ordemServicoProgramacao.setEquipe(equipe);
						ordemServicoProgramacao.setUsuarioProgramacao(usuario);
						
						if(idEquipePrincipal == equipe.getId().intValue()){
							ordemServicoProgramacao.setIndicadorEquipePrincipal(ConstantesSistema.SIM);
						}else{
							ordemServicoProgramacao.setIndicadorEquipePrincipal(ConstantesSistema.NAO);
						}
						
						
						// Pega a data final que foi colocada na tela 
						String dataProgramacaoSelecionada = 
							httpServletRequest.getParameter("dataFinalProgramacaoSelecionadas"+os.getId());
						
						ProgramacaoRoteiro programacaoRoteiro = new ProgramacaoRoteiro();
						
						programacaoRoteiro.setDataRoteiro(Util.converteStringParaDate(dataProgramacaoSelecionada));
						programacaoRoteiro.setUnidadeOrganizacional(usuario.getUnidadeOrganizacional());
						
						ordemServicoProgramacao.setProgramacaoRoteiro(programacaoRoteiro);
						
						this.montaOrdemServicoProgramacao(sessao,ordemServicoProgramacao,colecaoEquipesPorOS);
						
						osProgramadas = osProgramadas + 1;
						elaborarActionForm.setProgramadas(osProgramadas);
						
					}
				}
				
				if(programouOS){
					this.removerOrdemServicoSelecionadas(sessao,idOs);	
				}
				i++;
			}
			
			// [SB0010]-Prepara Barra de Carga de Trabalho 
			this.preparaBarraCargaTrabalho(sessao,dataRoteiro);

		// Exibir Alerta
		}else if (tipoAcao != null && tipoAcao.equals("A")) {
			
			String chaveEquipe = httpServletRequest.getParameter("chaveEquipe");
			Integer idOs = new Integer(httpServletRequest.getParameter("idOs"));
			
			HashMap mapOsProgramacaoHelper = (HashMap) sessao.getAttribute("mapOsProgramacaoHelper");
			
			Collection colecaoHelper = (ArrayList) mapOsProgramacaoHelper.get(chaveEquipe);

			Iterator iter = colecaoHelper.iterator();
			while (iter.hasNext()) {
				
				OSProgramacaoHelper helper = (OSProgramacaoHelper) iter.next();
				
				OrdemServicoProgramacao osProgramacao = helper.getOrdemServicoProgramacao();
				OrdemServico ordemServico = osProgramacao.getOrdemServico();
				
				if(osProgramacao.getOrdemServico().getId().intValue() == idOs.intValue()){
					
					elaborarActionForm.setIdOrdemServico(idOs.intValue());
					elaborarActionForm.setDescricaoOrdemServico(ordemServico.getServicoTipo().getDescricao());
					elaborarActionForm.setAlertaEquipeServico(helper.getAlertaEquipeDeServicoPerfilTipo());
					httpServletRequest.setAttribute("colecaoAlertaEquipeLogradouro",
						helper.getColecaoAlertaEquipeDeLogradouro());
					
					break;
				}
			}
			retorno = actionMapping.findForward("alertaOrdemServico");

		}		
		
		Collection colecaoOSFiltroHelper = (Collection) sessao.getAttribute("colecaoOSFiltroHelper");		
		elaborarActionForm.setSelecionadas(colecaoOSFiltroHelper.size());

		return retorno;
	}
	
	/**
	 * Pesquisa a Equipe na coleção que já tem na sessão a partir do id
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 * 
	 * @param sessao e id da Equipe 
	 * @return Equipe
	 */
	private Equipe pesquisarEquipePorId(HttpSession sessao,Integer idEquipe){

		Equipe equipe = null;
		
		Collection colecaoEquipes = (Collection) sessao.getAttribute("colecaoEquipes"); 

		Iterator iter = colecaoEquipes.iterator();
		while (iter.hasNext()) {
			Equipe eq = (Equipe) iter.next();
			
			if(eq.getId().intValue() == idEquipe.intValue()){
				equipe = eq;
				break;
			}
		}

		return equipe;
	}

	/**
	 * Para cada OS programada e retirada na lista de os possiveis de programação
	 * a partir do id da Ordem de Serviço programada
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 * 
	 * @param sessao e id da OS 
	 */
	private void removerOrdemServicoSelecionadas(HttpSession sessao,Integer idOS){
		
		Collection colecaoOSFiltroHelper = (Collection) sessao.getAttribute("colecaoOSFiltroHelper");
		Iterator iter = colecaoOSFiltroHelper.iterator();
		while (iter.hasNext()) {
			OSFiltroHelper helper = (OSFiltroHelper) iter.next();
			OrdemServico os = helper.getOrdemServico();
			
			if(os.getId().intValue() == idOS.intValue()){
				iter.remove();
			}
		}
		
		sessao.setAttribute("colecaoOSFiltroHelper", colecaoOSFiltroHelper);
	}
	
	/**
	 * Remove a Ordem de Servico Programada do HashMap de OSProgramação
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 * 
	 * @param sessao,id da Os e numero de OS programadas 
	 * @return numero de os programadas
	 */
	private int removerOrdemServicoProgramada(HttpSession sessao,Integer idOs,int programadas){
 
		HashMap mapOsProgramacaoHelper = (HashMap) sessao.getAttribute("mapOsProgramacaoHelper");

		Collection colecaoHelper = mapOsProgramacaoHelper.values();
		Iterator iteraChave = colecaoHelper.iterator();
		
		while (iteraChave.hasNext()) {
			
			Collection colecaoOSProgramacaoHelper = (Collection) iteraChave.next();

			Iterator iteraOsProgramacao = colecaoOSProgramacaoHelper.iterator();
			boolean removeu = false; 
			while (iteraOsProgramacao.hasNext()) {

				OSProgramacaoHelper osProgramacaoHelper = (OSProgramacaoHelper) iteraOsProgramacao.next();
				
				OrdemServicoProgramacao ordemServicoProgramacao = osProgramacaoHelper.getOrdemServicoProgramacao();
				
				if(ordemServicoProgramacao.getOrdemServico().getId().intValue() == idOs.intValue()){
					
					iteraOsProgramacao.remove();
					programadas--;
					
					removeu = true;
					
					String dataRoteiro = 
						Util.formatarData(ordemServicoProgramacao.getProgramacaoRoteiro().getDataRoteiro());
					
					this.adcionaOrdemServicoNaColecaoSelecionada(sessao,
						ordemServicoProgramacao.getOrdemServico(),dataRoteiro);
				
				}else if (removeu){
					
					short seq = ordemServicoProgramacao.getNnSequencialProgramacao();
					seq--;
					
					ordemServicoProgramacao.setNnSequencialProgramacao(seq);
				}
			}
			
			if(colecaoOSProgramacaoHelper == null || colecaoOSProgramacaoHelper.isEmpty()){
				iteraChave.remove();
			}
		}
		
		return programadas;
	}
	
	/**
	 * Remove todas as Ordens de Serviço Programada do HashMap de OSProgramacaoHelper,
	 * esse metodo eh usado no desfazer
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 * 
	 * @param sessao e numero de OS programadas 
	 * @return numero de os programadas
	 */
	private int removerTodasOrdemServicoProgramada(HttpSession sessao,int programadas){
 
		HashMap mapOsProgramacaoHelper = (HashMap) sessao.getAttribute("mapOsProgramacaoHelper");

		Collection colecaoHelper = mapOsProgramacaoHelper.values();
		Iterator iteraChave = colecaoHelper.iterator();
		
		while (iteraChave.hasNext()) {
			
			Collection colecaoOSProgramacaoHelper = (Collection) iteraChave.next();

			Iterator iteraOsProgramacao = colecaoOSProgramacaoHelper.iterator();
			
			while (iteraOsProgramacao.hasNext()) {

				OSProgramacaoHelper osProgramacaoHelper = (OSProgramacaoHelper) iteraOsProgramacao.next();
				
				OrdemServicoProgramacao ordemServicoProgramacao = osProgramacaoHelper.getOrdemServicoProgramacao();
				
				if(ordemServicoProgramacao.getId() == null || 
					ordemServicoProgramacao.getId().intValue() == ConstantesSistema.NUMERO_NAO_INFORMADO){
					
					iteraOsProgramacao.remove();
					programadas--;
					String dataRoteiro = 
						Util.formatarData(ordemServicoProgramacao.getProgramacaoRoteiro().getDataRoteiro());
					this.adcionaOrdemServicoNaColecaoSelecionada(sessao,
							ordemServicoProgramacao.getOrdemServico(),dataRoteiro);
				}
			}
			
			if(colecaoOSProgramacaoHelper == null || colecaoOSProgramacaoHelper.isEmpty()){
				iteraChave.remove();
			}
		}
		
		return programadas;
	}

	/**
	 * Adiciona a Ordem de Servico na lista de ordem de serviços para possivel programação,
	 * esse metodo eh usado no momento que eh feito a remoção da Os na HashMap de OSProgramacaoHelper
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 * 
	 * @param sessao,ordem de servico e data de roteiro
	 */
	private void adcionaOrdemServicoNaColecaoSelecionada(HttpSession sessao,
			OrdemServico ordemServico,String dataRoteiro){
		
		OSFiltroHelper osFiltroHelper = this.retornaOSFiltroHelper(ordemServico);
		
		OrdemServico os = retornaOrdemServicoSelecionadasPorId(sessao,ordemServico.getId());

		//Verifica se já tem essa ordem de servico
		if(os == null){
			Collection colecaoOSFiltroHelper = (Collection) sessao.getAttribute("colecaoOSFiltroHelper");
			osFiltroHelper.setDataPrevisaoAtual(dataRoteiro);
			colecaoOSFiltroHelper.add(osFiltroHelper);
		}
	}
	
	/**
	 * Monta o objeto OSFiltroHelper a partir da Ordem de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 * 
	 * @param ordem de serviço
	 * @return OSFiltroHelper
	 */	
	private OSFiltroHelper retornaOSFiltroHelper(OrdemServico ordemServico) {

		OSFiltroHelper helper = null;

		int qtdDiasCliente = ConstantesSistema.NUMERO_NAO_INFORMADO;
		int qtdDiasAgencia = ConstantesSistema.NUMERO_NAO_INFORMADO;
		
		if(ordemServico.getRegistroAtendimento() != null) {
			Date dataPrevistaAtual = ordemServico.getRegistroAtendimento().getDataPrevistaAtual();
			
			qtdDiasCliente = 
				Util.obterQuantidadeDiasEntreDuasDatas(dataPrevistaAtual,new Date());			
		
		}
			
		helper = new OSFiltroHelper();
		helper.setOrdemServico(ordemServico);

		if(ordemServico.getSituacao() == OrdemServico.SITUACAO_EXECUCAO_EM_ANDAMENTO.shortValue()){
			helper.setSituacao(""+ordemServico.getSituacao());	
		}
		
		if(qtdDiasCliente > 0){
			helper.setDiasAtrasoCliente(qtdDiasCliente);
		}

		if(qtdDiasAgencia > 0){
			helper.setDiasAtrasoAgencia(qtdDiasAgencia);
		}
		
		String endereco = Fachada.getInstancia().obterEnderecoAbreviadoOS(ordemServico.getId());		
		helper.setEndereco(endereco);
		
			
		return helper;
	}	
	
	/**
	 * Pesquisa a Ordem de Serivico na coleção de OSFiltroHelper que já tem na sessão a partir do id
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 * 
	 * @param sessao e id da OS
	 * @return OrdemServico
	 */
	private OrdemServico retornaOrdemServicoSelecionadasPorId(HttpSession sessao,Integer idOS){
		
		OrdemServico ordemServico = null;
		
		Collection colecaoOSFiltroHelper = (Collection) sessao.getAttribute("colecaoOSFiltroHelper");
		
		Iterator iter = colecaoOSFiltroHelper.iterator();
		while (iter.hasNext()) {
			OSFiltroHelper helper = (OSFiltroHelper) iter.next();
			OrdemServico os = helper.getOrdemServico();
			
			if(os.getId().intValue() == idOS.intValue()){
				ordemServico = os;
				break;
			}
		}
		
		return ordemServico;
	}

	/**
	 * Retorna a ordem de serviço do HashMap de OSProgramacaoHelper
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 * 
	 * @param sessao,id da OS e chave da equipe(nome da Equipe)
	 * @return OrdemServico
	 */	
	private OrdemServico retornaOrdemServicoDaColecaoProgramada(HttpSession sessao,Integer idOS,String chaveEquipe){
		
		OrdemServico ordemServico = null;
		
		HashMap mapOsProgramacaoHelper = (HashMap) sessao.getAttribute("mapOsProgramacaoHelper");

		if(mapOsProgramacaoHelper.containsKey(chaveEquipe)){
		
			Collection colecaoHelper = (ArrayList) mapOsProgramacaoHelper.get(chaveEquipe);
			Iterator iter = colecaoHelper.iterator();
			while (iter.hasNext()) {
				
				OSProgramacaoHelper helper = (OSProgramacaoHelper) iter.next();
				OrdemServico os = helper.getOrdemServicoProgramacao().getOrdemServico();
				if(os.getId().intValue() == idOS.intValue()){
					ordemServico = os;
					break;
				}
			}
		}
		return ordemServico;
	}

	
	/**
	 * Monta um HashMap(nomeEquipe,Colecao de OSProgramacaoHelper) a partir 
	 * da Ordem de Servico Programacao
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 * 
	 * @param sessao e ordemServicoProgramacao
	 */
	private void montaOrdemServicoProgramacao(HttpSession sessao,
			OrdemServicoProgramacao ordemServicoProgramacao,Set<Equipe> colecaoEquipesPorOS){
		
		HashMap mapOsProgramacaoHelper = (HashMap) sessao.getAttribute("mapOsProgramacaoHelper");
		HashMap mapEquipe = new HashMap();
		if(sessao.getAttribute("mapEquipe") != null){
			mapEquipe = (HashMap) sessao.getAttribute("mapEquipe");
		}
		

		OrdemServico ordemServico = ordemServicoProgramacao.getOrdemServico();
		Equipe equipe = ordemServicoProgramacao.getEquipe();
		String chave = equipe.getNome();
		
		OSProgramacaoHelper helper = new OSProgramacaoHelper();
			
		int qtdDiasCliente = ConstantesSistema.NUMERO_NAO_INFORMADO;
		int qtdDiasAgencia = ConstantesSistema.NUMERO_NAO_INFORMADO;
					
		
		if(ordemServico.getRegistroAtendimento() != null) {
			
			Date dataPrevistaAtual = ordemServico.getRegistroAtendimento().getDataPrevistaAtual();
				
			if(dataPrevistaAtual != null){
				qtdDiasCliente = 
					Util.obterQuantidadeDiasEntreDuasDatas(dataPrevistaAtual,new Date());
			}
			
			Date dataAgenciaReguladoraPrevisaoAtual = 
				Fachada.getInstancia().obterDataAgenciaReguladoraPrevisaoAtual(
					ordemServico.getRegistroAtendimento().getId());
			
			if(dataAgenciaReguladoraPrevisaoAtual != null){

				qtdDiasAgencia = 
					Util.obterQuantidadeDiasEntreDuasDatas(dataAgenciaReguladoraPrevisaoAtual,new Date());
			}

			int logradouro = ConstantesSistema.NUMERO_NAO_INFORMADO;
			if(ordemServico.getRegistroAtendimento().getLogradouroBairro() != null){
				logradouro = ordemServico.getRegistroAtendimento().getLogradouroBairro().getId().intValue();
			}
			
			Collection colecaoAlertaLogradouro = pesquisaEquipePeloLogradouro(sessao,logradouro,chave);
			if(colecaoAlertaLogradouro != null && !colecaoAlertaLogradouro.isEmpty()){
				helper.setTemAlerta(true);
				helper.setColecaoAlertaEquipeDeLogradouro(colecaoAlertaLogradouro);
			}

		}

		helper.setPodeRemover(true);
		
		ServicoPerfilTipo servicoPerfilTipo = ordemServico.getServicoTipo().getServicoPerfilTipo();
		ServicoPerfilTipo servicoPerfilTipoEquipe = equipe.getServicoPerfilTipo();
		
		if(servicoPerfilTipo != null && servicoPerfilTipoEquipe != null &&
			servicoPerfilTipo.getId().intValue() != servicoPerfilTipoEquipe.getId().intValue()){
			
			helper.setTemAlerta(true);
			helper.setAlertaEquipeDeServicoPerfilTipo(chave);
			
		}else if((servicoPerfilTipo == null && equipe.getServicoPerfilTipo() != null) ||
				 (servicoPerfilTipo != null && equipe.getServicoPerfilTipo() == null) ){

			helper.setTemAlerta(true);
			helper.setAlertaEquipeDeServicoPerfilTipo(chave);
		}
				
		if(ordemServico.getSituacao() == OrdemServico.SITUACAO_EXECUCAO_EM_ANDAMENTO.shortValue()){
			helper.setSituacao(""+ordemServico.getSituacao());	
		}
				
		if(qtdDiasCliente > 0){
			helper.setDiasAtrasoCliente(qtdDiasCliente);
		}

		if(qtdDiasAgencia > 0){
			helper.setDiasAtrasoAgencia(qtdDiasAgencia);
		}
		String endereco = Fachada.getInstancia().obterEnderecoAbreviadoOS(ordemServico.getId());		
		helper.setEndereco(endereco);
		helper.setColecaoEquipeAssociadaOS(colecaoEquipesPorOS);

		if(!mapOsProgramacaoHelper.containsKey(chave)){

			ordemServicoProgramacao.setNnSequencialProgramacao((short)1);
			
			helper.setOrdemServicoProgramacao(ordemServicoProgramacao);	
			
			Collection colecaoHelper = new ArrayList();	
			colecaoHelper.add(helper);

			mapOsProgramacaoHelper.put(chave,colecaoHelper);
				
		}else{
			Collection colecaoHelper = (ArrayList) mapOsProgramacaoHelper.get(chave);
			int valor = this.retornaUltimoSequencial(colecaoHelper)+1;
			
			ordemServicoProgramacao.setNnSequencialProgramacao((short)valor);
			helper.setOrdemServicoProgramacao(ordemServicoProgramacao);
			
			colecaoHelper.add(helper);
				
			mapOsProgramacaoHelper.put(chave,colecaoHelper);
				
		}
		
		mapEquipe.put(chave,equipe);
		sessao.setAttribute("mapEquipe",mapEquipe);
	}
	
	/**
	 * Retorna o ultimo sequencial das os´s programadas
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 * 
	 * @param colecao de OsProgramacaoHelper
	 * @return ultimoSequencial
	 */	
	private int retornaUltimoSequencial(Collection colecaoOsProgramacaoHelper){
		short valorSequencial = 0;
		Iterator iter = colecaoOsProgramacaoHelper.iterator();
		while (iter.hasNext()) {
			OSProgramacaoHelper helper = (OSProgramacaoHelper) iter.next();
			
			if(valorSequencial < helper.getOrdemServicoProgramacao().getNnSequencialProgramacao()){
				valorSequencial = helper.getOrdemServicoProgramacao().getNnSequencialProgramacao();
			}
		}
		
		return valorSequencial;
	}

	/**
	 * Retorna a colecao de chave da equipe que tenha o mesmo logradouro
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 * 
	 * @param sessao,id do Logradouro e chave da equipe
	 * @return colecao de chaves da equipe
	 */	
	private Collection pesquisaEquipePeloLogradouro(HttpSession sessao,int idLogradouro,String chave){
		Set retorno = new HashSet();
		
		HashMap mapOsProgramacaoHelper = (HashMap) sessao.getAttribute("mapOsProgramacaoHelper");
		
		if(mapOsProgramacaoHelper != null && !mapOsProgramacaoHelper.isEmpty()){
		
			Collection colecao = (Collection) mapOsProgramacaoHelper.get(chave);
			
			if(colecao != null && !colecao.isEmpty()){
				Iterator iter = colecao.iterator();
				while (iter.hasNext()) {
					OSProgramacaoHelper helper = (OSProgramacaoHelper) iter.next();
					
					OrdemServicoProgramacao ordemServicoProgramacao = helper.getOrdemServicoProgramacao();
					OrdemServico ordemServico = ordemServicoProgramacao.getOrdemServico();
					int idLogra = ConstantesSistema.NUMERO_NAO_INFORMADO;
	
					if(ordemServico.getRegistroAtendimento() != null){
						
						if(ordemServico.getRegistroAtendimento().getLogradouroBairro() != null){
							idLogra = ordemServico.getRegistroAtendimento().getLogradouroBairro().getId().intValue();	
						}
						
						String key = ordemServicoProgramacao.getEquipe().getNome();
						
						if(idLogra == idLogradouro && !key.equals(chave)){
							retorno.add(key);
						}
					}
				}
			}
		}
		
		return retorno;
	}
	
	/**
	 * [SB00010] - Prepara Barra da Carga de Trabalho 
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 * 
	 * @param sessao,data do roteiro
	 */	
	private void preparaBarraCargaTrabalho(HttpSession sessao,Date dataRoteiro){

		HashMap mapEquipeIdsOsProgramadas = new HashMap();
		HashMap mapEquipeOsDistribuidas = new HashMap();
		HashMap mapOsProgramacaoHelper = (HashMap) sessao.getAttribute("mapOsProgramacaoHelper");

		Collection colecaoHelper = mapOsProgramacaoHelper.values();
		Iterator itera = colecaoHelper.iterator();
		
		while (itera.hasNext()) {
			
			Collection colecaoOSProgramacaoHelper = (Collection) itera.next();

			Iterator iteraOsProgramacao = colecaoOSProgramacaoHelper.iterator();
			
			while (iteraOsProgramacao.hasNext()) {

				OSProgramacaoHelper osProgramacaoHelper = (OSProgramacaoHelper) iteraOsProgramacao.next();
				
				OrdemServicoProgramacao osProgramacao = osProgramacaoHelper.getOrdemServicoProgramacao();
				
				String chaveNome = osProgramacao.getEquipe().getNome();
				
				if(osProgramacao.getId() != null){

					if(!mapEquipeIdsOsProgramadas.containsKey(chaveNome)){

						Set colecaoIds = new HashSet();	
						colecaoIds.add(osProgramacao.getOrdemServico().getId());

						mapEquipeIdsOsProgramadas.put(chaveNome,colecaoIds);
					}else{
						Set colecaoIds = new HashSet();
						if(mapEquipeIdsOsProgramadas.get(chaveNome) != null){
							colecaoIds = (HashSet) mapEquipeIdsOsProgramadas.get(chaveNome);
							colecaoIds.add(osProgramacao.getOrdemServico().getId());							
						}
						
						mapEquipeIdsOsProgramadas.put(chaveNome,colecaoIds);
					}
				}else{
					
					if(!mapEquipeIdsOsProgramadas.containsKey(chaveNome)){
						mapEquipeIdsOsProgramadas.put(chaveNome,null);	
					}
					
					ObterOSDistribuidasPorEquipeHelper obterOSDistribuidasPorEquipeHelper = 
						new ObterOSDistribuidasPorEquipeHelper();
					
					obterOSDistribuidasPorEquipeHelper.setIdOS(osProgramacao.getOrdemServico().getId());
					obterOSDistribuidasPorEquipeHelper.setDataFinalProgramacao(
						osProgramacao.getProgramacaoRoteiro().getDataRoteiro());
					
					obterOSDistribuidasPorEquipeHelper.setColecaoEquipe(osProgramacaoHelper.getColecaoEquipeAssociadaOS());

					if(!mapEquipeOsDistribuidas.containsKey(chaveNome)){
						
						Set<ObterOSDistribuidasPorEquipeHelper> colecaoOsDistribuidas = new HashSet();	
						colecaoOsDistribuidas.add(obterOSDistribuidasPorEquipeHelper);

						mapEquipeOsDistribuidas.put(chaveNome,colecaoOsDistribuidas);
					}else{

						Set<ObterOSDistribuidasPorEquipeHelper> colecaoOsDistribuidas = 
							(HashSet) mapEquipeOsDistribuidas.get(chaveNome);	
						
						colecaoOsDistribuidas.add(obterOSDistribuidasPorEquipeHelper);
						
						mapEquipeOsDistribuidas.put(chaveNome,colecaoOsDistribuidas);
					}
				}
			}
		}
		
		itera = mapEquipeIdsOsProgramadas.keySet().iterator();

		HashMap mapEquipe = 
			(HashMap) sessao.getAttribute("mapEquipe");
		
		while (itera.hasNext()) {
			
			String key = (String) itera.next();
			
			Set colecaoIdsOSProgramadas = (HashSet) mapEquipeIdsOsProgramadas.get(key);
			
			Equipe equipe = (Equipe) mapEquipe.get(key);			
			
			Set<ObterOSDistribuidasPorEquipeHelper> colecaoOsDistribuidas = 
				(HashSet) mapEquipeOsDistribuidas.get(key);
			
			ObterCargaTrabalhoEquipeHelper obterCargaTrabalhoEquipeHelper = 
				Fachada.getInstancia().obterCargaTrabalhoEquipe(
						equipe.getId(),colecaoIdsOSProgramadas,colecaoOsDistribuidas,dataRoteiro);

			BigDecimal percentualPrevista = obterCargaTrabalhoEquipeHelper.getPercentualCargaTrabalhoPrevista();
			BigDecimal percentualRealizada = obterCargaTrabalhoEquipeHelper.getPercentualCargaRealizada();
			
			String chaveSessao = key.replace(" ","");
			String keyPercentualPrevista = "percentualTrabalhoPrevista"+chaveSessao;
			sessao.setAttribute(keyPercentualPrevista,percentualPrevista);

			String keyPercentualRealizada = "percentualTrabalhoRealizada"+chaveSessao;
			sessao.setAttribute(keyPercentualRealizada,percentualRealizada);

		}
	}
	
}