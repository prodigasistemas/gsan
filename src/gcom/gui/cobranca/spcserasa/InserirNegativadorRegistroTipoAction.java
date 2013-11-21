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

import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorRegistroTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processamento para inserir o negativador registro tipo
 * 
 * 
 * @author Yara Taciane de Souza
 * @date 07/01/2008
 */
public class InserirNegativadorRegistroTipoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirNegativadorRegistroTipoActionForm inserirNegativadorRegistroTipoActionForm = (InserirNegativadorRegistroTipoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INSERIR_NEGATIVADOR_REGISTRO_TIPO,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_INSERIR_NEGATIVADOR_REGISTRO_TIPO);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		Fachada fachada = Fachada.getInstancia();
		Integer idNegativadorRegistroTipo = null;

		// cria o objeto negativador contrato para ser inserido
		NegativadorRegistroTipo negativadorRegistroTipo = new NegativadorRegistroTipo();
		
        //-------------------------------------------------------------------------
		if (inserirNegativadorRegistroTipoActionForm.getIdNegativador() != null
				&& !inserirNegativadorRegistroTipoActionForm
						.getIdNegativador().equals("-1")) {

			Negativador negativador = new Negativador();
			negativador.setId(new Integer(
					inserirNegativadorRegistroTipoActionForm
							.getIdNegativador()));
			negativadorRegistroTipo.setNegativador(negativador);

		} else {
			throw new ActionServletException("atencao.required", null,
					"Negativador");
		}

		// Verificar a existência de código do motivo
		// -------------------------------------------------------------------------------------

		if (inserirNegativadorRegistroTipoActionForm.getCodigoRegistro() != null
				&& !inserirNegativadorRegistroTipoActionForm
						.getCodigoRegistro().equals("")) {
			
					
			String codigoRegistroTipo = inserirNegativadorRegistroTipoActionForm.getCodigoRegistro();
			
			if(!codigoRegistroTipo.equalsIgnoreCase("H")&& !codigoRegistroTipo.equalsIgnoreCase("D") && !codigoRegistroTipo.equalsIgnoreCase("T") ){
				throw new ActionServletException("atencao.codigo_tipo_registro_invalido");
			}
				
			negativadorRegistroTipo.setCodigoRegistro(codigoRegistroTipo.toUpperCase());
			

		} else {
			throw new ActionServletException("atencao.required", null,"Código do Registro");
		}
		
		//------------------------------------------------------------------------------------
		if (inserirNegativadorRegistroTipoActionForm.getDescricaoRegistroTipo() != null
				&& !inserirNegativadorRegistroTipoActionForm
						.getDescricaoRegistroTipo().equals("")) {
			
			String descricaoRegistroTipo = inserirNegativadorRegistroTipoActionForm.getDescricaoRegistroTipo();
			negativadorRegistroTipo.setDescricaoRegistroTipo(descricaoRegistroTipo.toUpperCase());			
		
		}else{
			throw new ActionServletException("atencao.required", null,"Descrição do Tipo do Registro ");
		}

		
		
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		negativadorRegistroTipo.setOperacaoEfetuada(operacaoEfetuada);
		negativadorRegistroTipo.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(negativadorRegistroTipo);
		// ------------ REGISTRAR TRANSAÇÃO ----------------
	
		
		if (negativadorRegistroTipo != null) {

			negativadorRegistroTipo.setUltimaAlteracao(new Date());

			 idNegativadorRegistroTipo = (Integer)
			 fachada.inserir(negativadorRegistroTipo);
		} else {
			throw new ActionServletException(
					"atencao.informar.linha.criterio.cobranca");
		}

		// dúvida, os 2 ultimos parametros.
		montarPaginaSucesso(httpServletRequest,
				"Tipo do Registro do Negativador "
						+ idNegativadorRegistroTipo
						+ " inserido com sucesso.",
				"Inserir outro Tipo do Registro do Negativador",
				"exibirInserirNegativadorRegistroTipoAction.do?menu=sim","exibirAtualizarNegativadorRegistroTipoAction.do?idRegistroAtualizacao="
				+ idNegativadorRegistroTipo, "Atualizar Tipo de Registro do Negativador Inserido");

		return retorno;
	}

}
