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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Este caso de uso permite reativar um RA
 * 
 * @author Ana Maria
 * @date 30/06/2006
 */

public class ReativarRegistroAtendimentoAction extends GcomAction {
	/**
	 * Este caso de uso permite retivar um registro de atendimento
	 * 
	 * [UC0426] Reativar Registro de Atendimento
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        ReativarRegistroAtendimentoActionForm form = (ReativarRegistroAtendimentoActionForm) actionForm;
        
        Fachada fachada = Fachada.getInstancia();
        
		HttpSession sessao = httpServletRequest.getSession(false);
        
        validarParecerUnidadeDestino(form);
        
        if(!form.getIdUnidadeDestino().equals("") && form.getIdUnidadeDestino() != null){
          fachada.verificaPossibilidadeEncaminhamentoUnidadeDestino(Util.converterStringParaInteger(form.getIdUnidadeDestino()));
        }
        
        /* Validação dos dados de reativação
         * [FS0003] - Verificar data do atendimento
         * [FS0004] - Verificar hora do atendimento        
         * [FS0005] - Verificar tempo de espera inicial para atendimento
         * [FS0006] - Verificar tempo de espera final para atendimento
         * [FS0007] - Verificar tempo de espera final para atendimento menor que o início
         * [FS0009] - Verificar autorização da unidade de atendimento par abertura de ra
		 * Mesma validação usada no [UC0366] Inserir Registro Atendimento
		 */
        fachada.validarInserirRegistroAtendimentoDadosGerais(form.getDataAtendimentoReativado(),
        		form.getHoraAtendimentoReativado(), 
        		form.getTempoEsperaInicial(),
        		form.getTempoEsperaFinal(), 
        		form.getIdUnidadeAtendimento(), 
        		null);
        
		if(form.getParecerUnidadeDestino() != null && 
			!form.getParecerUnidadeDestino().equals("") && 
			form.getParecerUnidadeDestino().length() > 200){
					
			String[] msg = new String[2];
			msg[0]="Parecer";
			msg[1]="200";
				
			throw new ActionServletException("atencao.execedeu_limit_observacao",null,msg);
		}
		
        if(form.getObservacao() != null && 
				!form.getObservacao().equals("") && 
				form.getObservacao().length() > 200){
				
			String[] msg = new String[2];
			msg[0]="Observação";
			msg[1]="200";
				
			throw new ActionServletException("atencao.execedeu_limit_observacao",null,msg);
		}
		

		
        
		//Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");	
		
		validarUnidadeAtendimento(form, fachada, usuario);
 
        RegistroAtendimento ra = new RegistroAtendimento();
        
        form.setFormValues(ra);
        
        Integer[] idRAGerado = 
        	fachada.reativarRegistroAtendimento(ra, 
        		Util.converterStringParaInteger(form.getIdUnidadeAtendimento()),
        		usuario.getId(),
        		Util.converterStringParaInteger(form.getIdClienteSolicitante()) ,
        		form.getIdRaSolicitante() ,
        		Util.converterStringParaInteger(form.getIdUnidadeDestino()) ,
        		form.getParecerUnidadeDestino(), 
        		Util.converterStringParaInteger(form.getTipoSolicitacaoId()));
        
        sessao.removeAttribute("colecaoMeioSolicitacao");
        sessao.removeAttribute("colecaoMotivoReativacao");
        
        if (fachada.gerarOrdemServicoAutomatica(ra.getSolicitacaoTipoEspecificacao().getId())){
        	
        	montarPaginaSucesso(httpServletRequest, "Registro de Atendimento de código "
				    + idRAGerado[0] + " e Ordem de Serviço de código " + idRAGerado[1] + " inseridos com sucesso.", "Voltar",
                    "exibirConsultarRegistroAtendimentoAction.do?numeroRA="
					+ form.getNumeroRA());
        }
        else{
        	
        	montarPaginaSucesso(httpServletRequest, "Registro de Atendimento de código "
                    + idRAGerado[0] + " inserido com sucesso.", "Voltar",
                    "exibirConsultarRegistroAtendimentoAction.do?numeroRA="
					+ form.getNumeroRA());
        }
        	
	
		return retorno;
	}

	/**
	 * Parecer para a Unidade Destino(caso a Unidade Destino esteja preenchida,
	 * obrigatório; caso contrário, opcional)
	 */		
	private void validarParecerUnidadeDestino(ReativarRegistroAtendimentoActionForm form) {
		if((form.getParecerUnidadeDestino().equals("") || form.getParecerUnidadeDestino().trim().equals("")) &&
        		(form.getIdUnidadeDestino() != null && !form.getIdUnidadeDestino().equals(""))){
			throw new ActionServletException("atencao.informe_parecer_unidade_destino_selecionada");	
        }
	}
	
	/**
	 * [FS0001]Valida possibilidade de reativação
	 *
	 * Verificar se o indicador de autorização para manutenção do RA não possui permissão
	 * (valor correspondente a 2-não) 
	 */	
	private void validarUnidadeAtendimento(ReativarRegistroAtendimentoActionForm form, Fachada fachada, Usuario usuario) {
		Short indicadorAutorizacao = fachada.obterIndicadorAutorizacaoManutencaoRA(Util.converterStringParaInteger(form.getIdUnidadeAtendimento()), usuario.getId());
		if(indicadorAutorizacao == null || indicadorAutorizacao.equals(ConstantesSistema.NAO)){
			throw new ActionServletException("atencao.unidade_nao_autorizada",
					null, form.getUnidadeAtendimento());		
		}
	}
	
}