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

import gcom.atendimentopublico.ordemservico.ArquivoTextoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao;
import gcom.atendimentopublico.ordemservico.ProgramacaoRoteiro;
import gcom.atendimentopublico.ordemservico.bean.AcompanhamentoArquivosRoteiroHelper;
import gcom.atendimentopublico.ordemservico.bean.OSProgramacaoAcompanhamentoServicoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

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
 * [UC1199] – Acompanhamento Arquivos Roteiro Remanejar Ordem Servico Action
 * 
 * @author Thúlio Araújo
 *
 * @date 22/08/2011
 */
public class AcompanhamentoArquivosRoteiroRemanejarOrdemServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("remanejarEquipeOSAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Form
		AcompanharRoteiroProgramacaoOrdemServicoActionForm 
		acompanharActionForm = (AcompanharRoteiroProgramacaoOrdemServicoActionForm) actionForm;
		
		// Precisa pegar a unidade do usuario do login que esta na sessao
		Integer idUnidadeLotacao = usuario.getUnidadeOrganizacional().getId();
		Date dataRoteiro = Util.converteStringParaDate(acompanharActionForm.getDataProgramacao());
		acompanharActionForm.setDataRoteiro(Util.formatarData(dataRoteiro));
		acompanharActionForm.setUnidadeLotacao(""+idUnidadeLotacao);

		this.remanejaOrdemServico(acompanharActionForm,usuario,sessao);
		
		httpServletRequest.setAttribute("fecharPopup", "true");
		
		return retorno;
	}
	
	private void remanejaOrdemServico(AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm,
		Usuario usuario, HttpSession sessao){

		Fachada fachada = Fachada.getInstancia();
		
		Date dataRoteiro = Util.converteStringParaDate(acompanharActionForm.getDataRoteiro());
		
		Integer equipeRemanejada = new Integer(acompanharActionForm.getIdEquipePrincipal());
		Integer equipeAtual = new Integer(acompanharActionForm.getIdEquipeAtual());
		Integer idOrdemServico = new Integer(acompanharActionForm.getIdOrdemServico());

		Collection colecaoAcompanhamentoArquivosRoteiro = 
				(Collection) sessao.getAttribute("colecaoAcompanhamentoArquivosRoteiro");
		
		//[FS0010] - Verificar seleção do arquivo finalizado para equipe remanejada		
		ArquivoTextoAcompanhamentoServico arquivoTextoAcompanhamentoServico = 
				fachada.pesquisarArquivoTextoAcompanhamentoServicoEquipe(equipeRemanejada, dataRoteiro);
		
		if (arquivoTextoAcompanhamentoServico != null && !arquivoTextoAcompanhamentoServico.equals(null)) {
			if (arquivoTextoAcompanhamentoServico.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.TRANSMITIDO)){
				throw new ActionServletException("atencao.nao_possivel.incluir_situacao_finalizado");
			}
		}
		
		ProgramacaoRoteiro programacaoRoteiro = fachada.pesquisarProgramacaoRoteiro(usuario.getUnidadeOrganizacional().getId(),dataRoteiro);
		
		if (fachada.validarCargaTrabalhoEquipe(equipeRemanejada,programacaoRoteiro.getId(),idOrdemServico,usuario.getUnidadeOrganizacional().getId())){
			throw new ActionServletException("atencao.limite_carga_trabalho_excedido");
		}
		
		// Usa o mesmo metodo de alocar equipe
		fachada.alocaEquipeParaOs(idOrdemServico,dataRoteiro,equipeAtual, true, Util.converterStringParaInteger(acompanharActionForm.getIdAcompanhamentoArquivosRoteiro()));
		
		// [SB0011] - Incluir Programação
		OrdemServicoProgramacao ordemServicoProgramacao = new OrdemServicoProgramacao();
		
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setId(new Integer(acompanharActionForm.getIdOrdemServico()));
		
		ordemServicoProgramacao.setProgramacaoRoteiro(programacaoRoteiro);
		ordemServicoProgramacao.setOrdemServico(ordemServico);
		
		Equipe equipe = new Equipe();
		equipe.setId(new Integer(equipeRemanejada));
		
		OrdemServicoProgramacao ordemServicoProgramacaoRemanejada = Fachada.getInstancia().pesquisarOSProgramacaoAtivaComDataRoteiroIdEquipe(
			idOrdemServico, dataRoteiro, equipeAtual);
		
		//[SB0004 - Reordena Sequencial de Programação - Inclusão de Ordem de Serviço]
		short maiorSequencial = this.retornaUltimoSequencial(Integer.toString(equipe.getId()), colecaoAcompanhamentoArquivosRoteiro);
		short sequencial = ordemServicoProgramacaoRemanejada.getNnSequencialProgramacao();
		short sequencialRetorno = 0;		
		
		//[FS0004] - Verificar sequencial invalido
		if (sequencial > maiorSequencial+1){
			sequencial = (short)ConstantesSistema.NUMERO_NAO_INFORMADO;
		}else if(maiorSequencial+1 != sequencial){
			sequencialRetorno = fachada.reordenaSequencialOSProgramacao(dataRoteiro,sequencial,
					equipe.getId());
		}else{
			sequencialRetorno = sequencial;
		}
		
		ordemServicoProgramacao.setEquipe(equipe);
		ordemServicoProgramacao.setUsuarioProgramacao(usuario);
		ordemServicoProgramacao.setUsuarioFechamento(null);
		ordemServicoProgramacao.setOsProgramNaoEncerMotivo(null);
		ordemServicoProgramacao.setNnSequencialProgramacao(sequencialRetorno);
		ordemServicoProgramacao.setIndicadorAtivo(ConstantesSistema.SIM);
		ordemServicoProgramacao.setIndicadorEquipePrincipal(ConstantesSistema.SIM);	
		ordemServicoProgramacao.setUltimaAlteracao(new Date());
		ordemServicoProgramacao.setSituacaoFechamento(null);
		
		fachada.incluirOrdemServicoProgramacao(ordemServicoProgramacao, usuario);
		
		fachada.inserirOrdemProgramacoAcompanhamentoServico(new Integer(ordemServicoProgramacao.getEquipe().getId()), dataRoteiro);
	}
	
	private short retornaUltimoSequencial(String idEquipe, Collection colecaoAcompanhamentoArquivosRoteiro){
		short valorSequencial = 0;
		if (colecaoAcompanhamentoArquivosRoteiro != null && !colecaoAcompanhamentoArquivosRoteiro.isEmpty()){	
			Iterator itDadosAcompanhamentoArquivosRoteiroHelper = colecaoAcompanhamentoArquivosRoteiro.iterator();
			while (itDadosAcompanhamentoArquivosRoteiroHelper.hasNext()){
				AcompanhamentoArquivosRoteiroHelper acompanhamentoArquivosRoteiroHelper = (AcompanhamentoArquivosRoteiroHelper) itDadosAcompanhamentoArquivosRoteiroHelper.next();
				if (acompanhamentoArquivosRoteiroHelper.getIdEquipe().equals(idEquipe)){
					Collection osProgramacaoAcompServicoHelper = acompanhamentoArquivosRoteiroHelper.getOsProgramacaoAcompServicoHelper();
					
					Iterator iter = osProgramacaoAcompServicoHelper.iterator(); 
							
					while (iter.hasNext()) {
						OSProgramacaoAcompanhamentoServicoHelper helper = (OSProgramacaoAcompanhamentoServicoHelper) iter.next();
						
						if(valorSequencial < Short.parseShort(helper.getNnSequencialProgramacao())){
							valorSequencial = Short.parseShort(helper.getNnSequencialProgramacao());
						}
					}
				}
			}
		}
		return valorSequencial;
	}
}