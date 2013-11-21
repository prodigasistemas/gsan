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
package gcom.gui.micromedicao.leitura;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.funcionario.Funcionario;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.leitura.AtualizarLeituristaActionForm;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarLeituristaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarLeituristaActionForm form = (AtualizarLeituristaActionForm) actionForm;

		Leiturista leiturista = (Leiturista) sessao.getAttribute("leiturista");
		
		// Validar se IMEI possui 15 caracteres
		if (form.getNumeroImei() != null && form.getNumeroImei().toString().length() != 15) {
			throw new ActionServletException("atencao.imei.invalido");
		}
		 
		// Validar se IMEI já está cadastrado
		if (form.getNumeroImei() != null && !form.getNumeroImei().equals(leiturista.getNumeroImei())) {
			FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.USUARIO);
			filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.IMEI, form.getNumeroImei()));
			
			Collection pesquisa = fachada.pesquisar(filtroLeiturista, Leiturista.class.getName());
			
			if (pesquisa != null && pesquisa.size() > 0) {
				Leiturista l = (Leiturista) Util.retonarObjetoDeColecao(pesquisa);
				
				String nomeLeiturista = l.getId().toString();
				
				if(l.getCliente() != null){
					nomeLeiturista = l.getCliente().getNome();
				}else if(l.getFuncionario() != null){
					nomeLeiturista = l.getFuncionario().getNome();
				}else if(l.getUsuario() != null){
					nomeLeiturista = l.getUsuario().getNomeUsuario();
				}
				
				throw new ActionServletException("atencao.imei.ja.cadastrado", null, nomeLeiturista);
			}
		}
		
		if(form.getIdCliente()!=null && !form.getIdCliente().trim().equals("")){
			// Cliente		
			Cliente cliente = new Cliente();
			cliente.setId(new Integer(form.getIdCliente()));
			leiturista.setCliente(cliente);
			leiturista.setFuncionario(null);
		}else if(form.getIdFuncionario()!=null && !form.getIdFuncionario().trim().equals("")){
			// Funcionario
			Funcionario funcionario = new Funcionario();
			funcionario.setId(new Integer(form
					.getIdFuncionario()));
			leiturista.setFuncionario(funcionario);
			leiturista.setCliente(null);
		}else{
			//Erro Informar um cliente ou funcionario
			throw new ActionServletException("atencao.cliente_ou_funcionario");
			
		}
				
				
		leiturista.setCodigoDDD(form.getDdd());
		leiturista.setNumeroFone(form.getTelefone());
		leiturista.setNumeroImei(form.getNumeroImei());

		//Indicador de uso 
		leiturista.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		

		// Empresa		
		Empresa empresa = null;
		
		if (form
				.getEmpresaID() != null && !form
				.getEmpresaID().equals("") && !form
				.getEmpresaID().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			empresa = new Empresa();
			
			empresa.setId(new Integer(form
					.getEmpresaID()));
		}else{
			throw new ActionServletException("atencao.empresa_leituristica_nao_informado"); 
		}
		
		//Setando
		leiturista.setEmpresa(empresa);
		
		// Usuario
		if ( form.getLoginUsuario() != null && !form.getLoginUsuario().equals( "" ) ){
			// Filtra Usuario
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ComparacaoTexto(FiltroUsuario.LOGIN, form.getLoginUsuario() ) );		
			
			// Recupera Usuário
			Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = colecaoUsuario.iterator().next();
				
				leiturista.setUsuario( usuario );
			}
		}else{
			leiturista.setUsuario( null );
		}
		
		leiturista.setIndicadorUso(new Short(form.getIndicadorUso()));

		fachada.atualizarLeiturista(leiturista);

		montarPaginaSucesso(httpServletRequest, "Leiturista de código "
				+ leiturista.getId().toString() + " atualizada com sucesso.",
				"Realizar outra Manutenção de Leiturista ",
				"exibirFiltrarLeituristaAction.do?menu=sim");
		return retorno;
	}
}
