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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.CobrancaDebitoSituacao;
import gcom.cobranca.FiltroNegativadorExclusaoMotivo;
import gcom.cobranca.NegativadorExclusaoMotivo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action form de manter  Negativador Exclusão Motivo
 * 
 * @author Yara Taciane 
 * @created 04/01/2008
 */
public class AtualizarNegativadorExclusaoMotivoAction extends GcomAction {
	/**
	 * @author Yara Taciane
	 * @date 04/01/2008
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
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AtualizarNegativadorExclusaoMotivoActionForm atualizarNegativadorExclusaoMotivoActionForm = (AtualizarNegativadorExclusaoMotivoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
			
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_ATUALIZAR_NEGATIVADOR_EXCLUSAO_MOTIVO,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_ATUALIZAR_NEGATIVADOR_EXCLUSAO_MOTIVO);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança

		NegativadorExclusaoMotivo negativadorExclusaoMotivo = (NegativadorExclusaoMotivo) sessao.getAttribute("negativadorExclusaoMotivo");

		// Pegando os dados do Formulário	
		String descricaoExclusaoMotivo = atualizarNegativadorExclusaoMotivoActionForm.getDescricaoExclusaoMotivo();
		String indicadorUso = atualizarNegativadorExclusaoMotivoActionForm.getIndicadorUso();
		String idCobrancaDebitoSituacao = atualizarNegativadorExclusaoMotivoActionForm.getIdCobrancaDebitoSituacao();
		
		Long time = Long.parseLong(atualizarNegativadorExclusaoMotivoActionForm.getTime()); 
		
		// Seta os campos para serem atualizados				
		
		if (descricaoExclusaoMotivo!= null	&& !descricaoExclusaoMotivo.equals("")) {

			negativadorExclusaoMotivo.setDescricaoExclusaoMotivo(descricaoExclusaoMotivo);

		} else {
			throw new ActionServletException("atencao.required", null,
					"Descrição Exclusão Motivo");
		}	
		
		if (idCobrancaDebitoSituacao!= null	&& !idCobrancaDebitoSituacao.equals("-1")) {

			CobrancaDebitoSituacao cobrancaDebitoSituacao = new CobrancaDebitoSituacao();
			cobrancaDebitoSituacao.setId(new Integer(idCobrancaDebitoSituacao));
			negativadorExclusaoMotivo.setCobrancaDebitoSituacao(cobrancaDebitoSituacao);

		} else {
			throw new ActionServletException("atencao.required", null,
					"Cobrança Débito Situação");
		}		
		
		if (indicadorUso!= null	&& !indicadorUso.equals("")) {		
		  negativadorExclusaoMotivo.setIndicadorUso(Short.parseShort(indicadorUso));		
		}else{
			throw new ActionServletException("atencao.required", null,
			"indicador de Uso");
		}
		
		//Check para atualização realizada por outro usuário 
		FiltroNegativadorExclusaoMotivo filtroNegativadorExclusaoMotivo = new FiltroNegativadorExclusaoMotivo(); 
		filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorExclusaoMotivo.ID, negativadorExclusaoMotivo.getId()));
		
		Collection collNegativadorExclusaoMotivo = Fachada.getInstancia().pesquisar(filtroNegativadorExclusaoMotivo, NegativadorExclusaoMotivo.class.getName());
		
		NegativadorExclusaoMotivo negativExclusaoMotivo = (NegativadorExclusaoMotivo)collNegativadorExclusaoMotivo.iterator().next();

		if (negativExclusaoMotivo.getUltimaAlteracao().getTime() != time){
			throw new ActionServletException("atencao.registro_remocao_nao_existente");
		}
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		negativadorExclusaoMotivo.setOperacaoEfetuada(operacaoEfetuada);
		negativadorExclusaoMotivo.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(negativadorExclusaoMotivo);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

			
		negativadorExclusaoMotivo.setUltimaAlteracao(new Date());
		
		// Atualiza o negativadorContrato 
		fachada.atualizar(negativadorExclusaoMotivo);
		
		montarPaginaSucesso(httpServletRequest, "negativador Exclusao Motivo de código "
				+ negativadorExclusaoMotivo.getId() + " atualizado com sucesso.",
				"Realizar outra Manutenção de Negativador Exclusao Motivo",
				"exibirFiltrarNegativadorExclusaoMotivoAction.do?desfazer=S");
	
		return retorno;
	}
    
	
    
   
}
 