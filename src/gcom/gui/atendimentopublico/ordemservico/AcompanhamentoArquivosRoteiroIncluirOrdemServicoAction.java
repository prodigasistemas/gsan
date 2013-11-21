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
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1199] – Acompanhamento Arquivos Roteiro Incluir Ordem Servico Action
 * 
 * @author Thúlio Araújo
 *
 * @date 15/08/2011
 */
public class AcompanhamentoArquivosRoteiroIncluirOrdemServicoAction extends GcomAction {

	Fachada fachada = Fachada.getInstancia();
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("incluirOrdemServicoAcompanhamentoArquivosRoteiro");
		
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
		
		// [SB0005] - Incluir Ordem de Serviço na Programação
		this.incluirOrdemServicoProgramacao(acompanharActionForm,usuario);
		
		httpServletRequest.setAttribute("fecharPopup", "true");
		
		return retorno;
	}
	
	private void incluirOrdemServicoProgramacao(AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm,
			Usuario usuario){

		Date dataRoteiro = Util.converteStringParaDate(acompanharActionForm.getDataRoteiro());
		
		// Colocado por Raphael Rossiter em 12/03/2007
		Integer idUnidadeLotacao = new Integer(acompanharActionForm.getUnidadeLotacao());
		
		OrdemServico os = 
		Fachada.getInstancia().recuperaOSPorId(new Integer(acompanharActionForm.getIdOrdemServico()));
		
		// Verifica Existencia da Ordem de Servico
		if (os == null || os.equals("")){
			throw new ActionServletException("atencao.ordem_servico.inexistente");
		}
		
		ProgramacaoRoteiro programacaoRoteiro = fachada.pesquisarProgramacaoRoteiro(usuario.getUnidadeOrganizacional().getId(),dataRoteiro);
		
		// [FS0015] - Verificar registro de atendimento e ordem de serviço
		this.pesquisarRegistroAtendimento(acompanharActionForm, usuario.getUnidadeOrganizacional().getId());
		
		String numeroRa = acompanharActionForm.getNumeroRA();
		if(numeroRa != null && !numeroRa.equals("")){
			
			if(os.getRegistroAtendimento() == null || (os.getRegistroAtendimento().getId().intValue() != new Integer(numeroRa).intValue())){
				String[] parametros = new String[2];
				parametros[0] = os.getId().toString();
				parametros[1] = numeroRa;
				throw new ActionServletException("atencao.registro_nao_pertence_ordem_servico",null,parametros);					
			}
		}
		
		if(os.getRegistroAtendimento() == null){
			String[] parametros = new String[2];
			parametros[0] = os.getId().toString();
			parametros[1] = numeroRa;
			throw new ActionServletException("atencao.registro_nao_pertence_ordem_servico",null,parametros);					
		}
		
		//[FS0003] - Verificar possibilidade da inclusão da ordem de serviço
		fachada.validarInclusaoOsNaProgramacao(os, idUnidadeLotacao);
		
		//Validar carga do trabalho
		if (fachada.validarCargaTrabalhoEquipe(Util.converterStringParaInteger(acompanharActionForm.getIdEquipe()),
				programacaoRoteiro.getId(),Util.converterStringParaInteger(acompanharActionForm.getIdOrdemServico()),
				usuario.getUnidadeOrganizacional().getId())){
			throw new ActionServletException("atencao.limite_carga_trabalho_excedido");
		}
		
		//[SB0004 - Reordena Sequencial de Programação - Inclusão de Ordem de Serviço]
		short maiorSequencial = new Short(acompanharActionForm.getSequencialProgramacao());
		short sequencial = new Short(acompanharActionForm.getSequencialProgramacaoPrevisto());
		short sequencialRetorno = 0;
		
		//[FS0004] - Verificar sequencial invalido
		if (sequencial > maiorSequencial){
			throw new ActionServletException("atencao.sequencial_programacao_maior_limite",null,""+maiorSequencial);
		}else if(maiorSequencial != sequencial){
			sequencialRetorno = fachada.reordenaSequencialOSProgramacao(dataRoteiro,sequencial,
					Util.converterStringParaInteger(acompanharActionForm.getIdEquipe()));
		}else{
			sequencialRetorno = sequencial;
		}
		
		// [SB0011] - Incluir Programação
		OrdemServicoProgramacao ordemServicoProgramacao = new OrdemServicoProgramacao();
		
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setId(new Integer(acompanharActionForm.getIdOrdemServico()));
		
		ordemServicoProgramacao.setProgramacaoRoteiro(programacaoRoteiro);
		ordemServicoProgramacao.setOrdemServico(ordemServico);
		
		Equipe equipe = new Equipe();
		equipe.setId(new Integer(acompanharActionForm.getIdEquipe()));
		
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
		
		fachada.inserirOrdemProgramacoAcompanhamentoServico(new Integer(acompanharActionForm.getIdEquipe()), dataRoteiro);
	}
	
	private void pesquisarRegistroAtendimento(AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm, Integer unidId) {
		
		ObterDadosRegistroAtendimentoHelper obter = 
			fachada.obterDadosRegistroAtendimento(new Integer(
					acompanharActionForm.getNumeroRA()));

		if (obter.getRegistroAtendimento() != null) {
			RegistroAtendimento registroAtendimento = obter.getRegistroAtendimento();
			
			if (!registroAtendimento.getUnidadeAtual().getId().equals(unidId)){
				throw new ActionServletException("atencao.registro.atendimento.nao_associado",
						Integer.toString(registroAtendimento.getId()), Integer.toString(unidId));
			}
			
			if (registroAtendimento.getCodigoSituacao() == 2){
				throw new ActionServletException("atencao.situacao.ra.pendente");				
			}
			
		} else {
			throw new ActionServletException("atencao.registro.atendimento.inexistente");
		}
	}
}