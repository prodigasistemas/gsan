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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela atualização da subcategoria
 * 
 * [UC0059] Atualizar Subcategoria
 * 
 * @author Fernanda Paiva
 * @date 4/01/2006
 */
public class AtualizarSubcategoriaAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		FiltrarSubcategoriaActionForm filtrarSubcategoriaActionForm = (FiltrarSubcategoriaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_SUBCATEGORIA_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_SUBCATEGORIA_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		Fachada fachada = Fachada.getInstancia();

		Subcategoria subcategoria = (Subcategoria) sessao
				.getAttribute("subcategoria");

		String codigoSubcategoria = filtrarSubcategoriaActionForm
				.getCodigoSubcategoria();

		String idCategoria = (String) filtrarSubcategoriaActionForm
				.getIdCategoria();

		Short indicadorDeUso = new Short(filtrarSubcategoriaActionForm
				.getIndicadorUso());
		
		String descricaoAbreviada = filtrarSubcategoriaActionForm.getDescricaoAbreviada();
        String  numeroFatorFiscalizacao =  filtrarSubcategoriaActionForm.getNumeroFatorFiscalizacao() ;
        String indicadorSazonalidade = filtrarSubcategoriaActionForm.getIndicadorSazonalidade();
        
        Short  indicadorTarifaConsumo = null;
        
        if(filtrarSubcategoriaActionForm.getIndicadorTarifaConsumo() != null && !filtrarSubcategoriaActionForm.getIndicadorTarifaConsumo().equalsIgnoreCase("")){

        	indicadorTarifaConsumo =  new Short(filtrarSubcategoriaActionForm.getIndicadorTarifaConsumo()) ;
        }
        
        String codigoTarifaSocial = null;
		
        if ( filtrarSubcategoriaActionForm.getCodigoTarifaSocial() != null && !filtrarSubcategoriaActionForm.getCodigoTarifaSocial().equalsIgnoreCase("")){
        
			codigoTarifaSocial = filtrarSubcategoriaActionForm.getCodigoTarifaSocial();
		}
        Integer codigoGrupoSubcategoria = null; 
        
        if(filtrarSubcategoriaActionForm.getCodigoGrupoSubcategoria() != null && !filtrarSubcategoriaActionForm.getCodigoGrupoSubcategoria().equalsIgnoreCase("")){
        
        	codigoGrupoSubcategoria = new Integer(filtrarSubcategoriaActionForm.getCodigoGrupoSubcategoria());
        }

		Categoria categoria = null;

		if (idCategoria != null && !idCategoria.equals("")) {
			FiltroCategoria filtroCategoria = new FiltroCategoria();

			filtroCategoria.adicionarParametro(new ParametroSimples(
					FiltroCategoria.CODIGO, idCategoria));
			filtroCategoria.adicionarParametro(new ParametroSimples(
					FiltroCategoria.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection categorias = fachada.pesquisar(filtroCategoria,
					Categoria.class.getName());

			if (categorias != null && !categorias.isEmpty()) {
				// A categoria foi encontrada
				Iterator categoriaIterator = categorias.iterator();

				categoria = (Categoria) categoriaIterator.next();

			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Categoria");
			}

		}
		// seta os campos para serem atualizados
		subcategoria.setId(new Integer(filtrarSubcategoriaActionForm
				.getIdSubcategoria()));
		subcategoria.setCategoria(categoria);
		subcategoria.setCodigo(Integer.parseInt(codigoSubcategoria));
		
		subcategoria.setDescricao(filtrarSubcategoriaActionForm
				.getDescricaoSubcategoria());
		subcategoria.setIndicadorUso(indicadorDeUso);
		subcategoria.setDescricaoAbreviada(descricaoAbreviada);
		subcategoria.setCodigoTarifaSocial(codigoTarifaSocial);
		subcategoria.setCodigoGrupoSubcategoria(codigoGrupoSubcategoria);
		subcategoria.setNumeroFatorFiscalizacao(new Short(numeroFatorFiscalizacao));
		subcategoria.setIndicadorTarifaConsumo(indicadorTarifaConsumo);
		subcategoria.setIndicadorSazonalidade(new Short(indicadorSazonalidade));
		
		
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		subcategoria.setOperacaoEfetuada(operacaoEfetuada);
		subcategoria.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(subcategoria);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		Subcategoria subcategoriaVelha = (Subcategoria) sessao
				.getAttribute("subcategoria");

		fachada.atualizarSubcategoria(subcategoria, subcategoriaVelha);

		montarPaginaSucesso(httpServletRequest, "Subcategoria de código "
				+ subcategoria.getCodigo() + " da categoria "
				+ subcategoria.getCategoria().getDescricao()
				+ " atualizada com sucesso.",
				"Realizar outra Manutenção de Subcategoria",
				"exibirManterSubcategoriaAction.do?menu=sim");

		return retorno;
	}

}
