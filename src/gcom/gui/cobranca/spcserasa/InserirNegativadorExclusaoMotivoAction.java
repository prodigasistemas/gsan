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
import gcom.cobranca.Negativador;
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
 * Processamento para inserir o Motivo da Exclusão do Negativador
 * 
 * 
 * @author Yara Taciane de Souza
 * @date 18/12/2007
 */
public class InserirNegativadorExclusaoMotivoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirNegativadorExclusaoMotivoActionForm inserirNegativadorExclusaoMotivoActionForm = (InserirNegativadorExclusaoMotivoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INSERIR_NEGATIVADOR_EXCLUSAO_MOTIVO,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_INSERIR_NEGATIVADOR_EXCLUSAO_MOTIVO);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSAÇÃO ----------------
	
		Fachada fachada = Fachada.getInstancia();
		Integer idNegativadorExclusaoMotivo = null;

		// cria o objeto negativador contrato para ser inserido
		NegativadorExclusaoMotivo negativadorExclusaoMotivo = new NegativadorExclusaoMotivo();
		
        //-------------------------------------------------------------------------
		if (inserirNegativadorExclusaoMotivoActionForm.getIdNegativador() != null
				&& !inserirNegativadorExclusaoMotivoActionForm
						.getIdNegativador().equals("")) {

			Negativador negativador = new Negativador();
			negativador.setId(new Integer(
					inserirNegativadorExclusaoMotivoActionForm
							.getIdNegativador()));
			negativadorExclusaoMotivo.setNegativador(negativador);

		} else {
			throw new ActionServletException("atencao.required", null,
					"Negativador");
		}

		// Verificar a existência de código do motivo
		// -------------------------------------------------------------------------------------

		if (inserirNegativadorExclusaoMotivoActionForm.getCodigoMotivo() != null
				&& !inserirNegativadorExclusaoMotivoActionForm
						.getCodigoMotivo().equals("")) {
			
			String codigoExclusaoMotivo = inserirNegativadorExclusaoMotivoActionForm.getCodigoMotivo();
			

			FiltroNegativadorExclusaoMotivo filtroNegativadorExclusaoMotivo = new FiltroNegativadorExclusaoMotivo();

			filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(
					FiltroNegativadorExclusaoMotivo.CODIGO_EXCLUSAO_MOTIVO, codigoExclusaoMotivo));
			filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(
					FiltroNegativadorExclusaoMotivo.NEGATIVADOR_ID, negativadorExclusaoMotivo.getNegativador().getId()));
		
			
			Collection collNegativadorExclusaoMotivo= fachada.pesquisar(filtroNegativadorExclusaoMotivo,
					NegativadorExclusaoMotivo.class.getName());
			
			
			if(collNegativadorExclusaoMotivo != null && !collNegativadorExclusaoMotivo.isEmpty()){
			
				throw new ActionServletException("atencao.codigo_motivo_ja_existe_cadastro");
				
			}
			
			negativadorExclusaoMotivo.setCodigoExclusaoMotivo(Short.parseShort(codigoExclusaoMotivo));
			

		} else {
			throw new ActionServletException("atencao.required", null,"Código do Motivo");
		}
		
		//------------------------------------------------------------------------------------
		if (inserirNegativadorExclusaoMotivoActionForm.getDescricaoExclusaoMotivo() != null
				&& !inserirNegativadorExclusaoMotivoActionForm
						.getDescricaoExclusaoMotivo().equals("")) {
			
			String descricaoExlusaoMotivo = inserirNegativadorExclusaoMotivoActionForm.getDescricaoExclusaoMotivo();
			negativadorExclusaoMotivo.setDescricaoExclusaoMotivo(descricaoExlusaoMotivo);			
		
		}else{
			throw new ActionServletException("atencao.required", null,"Descrição do Motivo Exclusão");
		}

		//----------------------------------------------------------------------------------------

		if (inserirNegativadorExclusaoMotivoActionForm.getIdCobrancaDebitoSituacao() != null
				&& !inserirNegativadorExclusaoMotivoActionForm
						.getIdCobrancaDebitoSituacao().equals("")) {

			CobrancaDebitoSituacao cobrancaDebitoSituacao = new CobrancaDebitoSituacao();
			cobrancaDebitoSituacao.setId(new Integer(
					inserirNegativadorExclusaoMotivoActionForm.getIdCobrancaDebitoSituacao()));
			negativadorExclusaoMotivo.setCobrancaDebitoSituacao(cobrancaDebitoSituacao);

		} else {
			throw new ActionServletException("atencao.required", null,
					"Cobrança Débito Situação");
		}

		
		short indicadorUso = 1;		
		negativadorExclusaoMotivo.setIndicadorUso(indicadorUso);
		
		
		if (negativadorExclusaoMotivo != null) {

			negativadorExclusaoMotivo.setUltimaAlteracao(new Date());

			 idNegativadorExclusaoMotivo = (Integer)
			 fachada.inserir(negativadorExclusaoMotivo);
		} else {
			throw new ActionServletException(
					"atencao.informar.linha.criterio.cobranca");
		}

		
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		negativadorExclusaoMotivo.setOperacaoEfetuada(operacaoEfetuada);
		negativadorExclusaoMotivo.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(negativadorExclusaoMotivo);
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		

		montarPaginaSucesso(httpServletRequest,
				"Motivo da Exclusão do Negativador"
						+ idNegativadorExclusaoMotivo
						+ " inserido com sucesso.",
				"Inserir outro Motivo da Exclusão do Negativador",
				"exibirInserirNegativadorExclusaoMotivoAction.do?menu=sim","exibirAtualizarNegativadorExclusaoMotivoAction.do?idRegistroAtualizacao="
				+ idNegativadorExclusaoMotivo, "Atualizar Motivo da Exclusão do Negativador Inserido");
		
		

		return retorno;
	}

}
