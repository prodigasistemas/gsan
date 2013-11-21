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
import gcom.cadastro.imovel.FiltroSubCategoria;
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
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela inserção da subcategoria
 * 
 * [UC0058] Inserir Subcategoria
 * 
 * @author Fernanda Paiva
 * @date 28/12/2005
 */
public class InserirSubcategoriaAction extends GcomAction {
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

		//Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirSubcategoriaActionForm inserirSubcategoriaActionForm = (InserirSubcategoriaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		//------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_SUBCATEGORIA_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_SUBCATEGORIA_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		//------------ REGISTRAR TRANSAÇÃO ----------------

		//Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		//Obtém a sessão
		//HttpSession sessao = httpServletRequest.getSession(false);

		String categoria = inserirSubcategoriaActionForm.getCategoria();
		String descricaoSubcategoria = (String) inserirSubcategoriaActionForm
				.getDescricaoSubcategoria();
		Integer codigoSubcategoria = new Integer(inserirSubcategoriaActionForm
				.getCodigoSubcategoria());
		String descricaoAbreviada = inserirSubcategoriaActionForm
				.getDescricaoAbreviada();
		String codigoTarifaSocial = inserirSubcategoriaActionForm
				.getCodigoTarifaSocial();
		Integer codigoGrupoSubcategoria = null;

		if (inserirSubcategoriaActionForm.getCodigoGrupoSubcategoria() != null && !inserirSubcategoriaActionForm.getCodigoGrupoSubcategoria().equalsIgnoreCase("")) {

			codigoGrupoSubcategoria = new Integer(inserirSubcategoriaActionForm
					.getCodigoGrupoSubcategoria());
		}
		String numeroFatorFiscalizacao = inserirSubcategoriaActionForm
				.getNumeroFatorFiscalizacao();

		Short indicadorTarifaConsumo = null;

		if (inserirSubcategoriaActionForm.getIndicadorTarifaConsumo() != null  && !inserirSubcategoriaActionForm.getIndicadorTarifaConsumo().equalsIgnoreCase("")) {

			indicadorTarifaConsumo = new Integer(inserirSubcategoriaActionForm
					.getIndicadorTarifaConsumo()).shortValue();
		}

		String indicadorSazonalidade = inserirSubcategoriaActionForm
				.getIndicadorSazonalidade();

		Categoria categoriaSelecionada = null;
		Subcategoria subcategoriaSelecionada = null;

		//Verifica a descrição da categoria 
		if (categoria != null && !categoria.equals("")) {
			FiltroCategoria filtroCategoria = new FiltroCategoria();

			filtroCategoria.adicionarParametro(new ParametroSimples(
					FiltroCategoria.CODIGO, categoria));

			Collection<Categoria> categorias = fachada.pesquisar(
					filtroCategoria, Categoria.class.getName());

			if (categorias != null && categorias.isEmpty()) {
				throw new ActionServletException(
						"atencao.categoria_inexistente", null, "codigo");
			}

			categoriaSelecionada = categorias.iterator().next();
		}
		if (codigoSubcategoria != null) {
			FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();

			filtroSubcategoria.adicionarParametro(new ParametroSimples(
					FiltroSubCategoria.CODIGO, codigoSubcategoria));

			filtroSubcategoria.adicionarParametro(new ParametroSimples(
					FiltroSubCategoria.CATEGORIA_ID, categoria));

			filtroSubcategoria
					.adicionarCaminhoParaCarregamentoEntidade("categoria");

			Collection<Subcategoria> subcategorias = fachada.pesquisar(
					filtroSubcategoria, Subcategoria.class.getName());

			if (subcategorias != null && !subcategorias.isEmpty()) {
				subcategoriaSelecionada = subcategorias.iterator().next();
				throw new ActionServletException(
						"atencao.subcategoria_ja_existente",
						codigoSubcategoria.toString(), subcategoriaSelecionada
								.getCategoria().getDescricao());
			}
		}

		Short indicadorDeUso = ConstantesSistema.INDICADOR_USO_ATIVO;

		//cria o objeto subcategoria para ser inserido
		
		Subcategoria subcategoria = new Subcategoria(codigoSubcategoria,
				descricaoSubcategoria, indicadorDeUso, new Short(
						indicadorSazonalidade), descricaoAbreviada,
				codigoTarifaSocial, codigoGrupoSubcategoria,
				new Short(numeroFatorFiscalizacao), 
						indicadorTarifaConsumo, new Date(),
				categoriaSelecionada);

		//------------ REGISTRAR TRANSAÇÃO ----------------
		subcategoria.setOperacaoEfetuada(operacaoEfetuada);
		subcategoria.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(subcategoria);
		//------------ REGISTRAR TRANSAÇÃO ----------------

		fachada.inserir(subcategoria);

		montarPaginaSucesso(httpServletRequest, "Subcategoria de código "
				+ subcategoria.getCodigo() + " da categoria "
				+ subcategoria.getCategoria().getDescricao()
				+ " inserida com sucesso.", "Inserir outra Subcategoria",
				"exibirInserirSubcategoriaAction.do?menu=sim",
				"exibirAtualizarSubcategoriaAction.do?idRegistroAtualizacao="
						+ subcategoria.getId(),
				"Atualizar Subcategoria Inserida");

		return retorno;
	}
}
