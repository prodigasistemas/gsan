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
package gcom.gui.relatorio.cobranca;

import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroImovelSituacaoTipo;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSituacaoTipo;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cobranca.FiltroResolucaoDiretoria;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.cobranca.parcelamento.FiltroParcelamentoPerfil;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;
import gcom.fachada.Fachada;
import gcom.gui.cobranca.parcelamento.FiltrarPerfilParcelamentoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cobranca.RelatorioManterPerfilParcelamento;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;
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
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corrêa
 * @version 1.0
 */

public class GerarRelatorioPerfilParcelamentoManterAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
		ActionForward retorno = null;

		FiltrarPerfilParcelamentoActionForm filtrarPerfilParcelamentoActionForm = (FiltrarPerfilParcelamentoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroParcelamentoPerfil filtroParcelamentoPerfil = (FiltroParcelamentoPerfil) sessao.getAttribute("filtroParcelamentoPerfil");

		// Inicio da parte que vai mandar os parametros para o relatório

		ParcelamentoPerfil parcelamentoPerfilParametros = new ParcelamentoPerfil();

		// Resolução de Diretoria
		String idResolucaoDiretoria = (String) filtrarPerfilParcelamentoActionForm.getResolucaoDiretoria();

		ResolucaoDiretoria resolucaoDiretoria = new ResolucaoDiretoria();

		if (idResolucaoDiretoria != null
				&& !idResolucaoDiretoria.equals("")
				&& !idResolucaoDiretoria.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();

			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
					FiltroResolucaoDiretoria.CODIGO, idResolucaoDiretoria));

			Collection colecaoResolucoesDiretorias = fachada.pesquisar(
					filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());

			if (colecaoResolucoesDiretorias != null
					&& !colecaoResolucoesDiretorias.isEmpty()) {

				Iterator colecaoResolucoesDiretoriasIterator = colecaoResolucoesDiretorias
						.iterator();

				resolucaoDiretoria = (ResolucaoDiretoria) colecaoResolucoesDiretoriasIterator
						.next();

			}
		}
		
		// Tipo da Situação do Imóvel
		String idImovelSituacaoTipo = (String) filtrarPerfilParcelamentoActionForm.getImovelSituacaoTipo();

		ImovelSituacaoTipo imovelSituacaoTipo = new ImovelSituacaoTipo();

		if (idImovelSituacaoTipo != null
				&& !idImovelSituacaoTipo.equals("")
				&& !idImovelSituacaoTipo.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			FiltroImovelSituacaoTipo filtroImovelSituacaoTipo = new FiltroImovelSituacaoTipo();

			filtroImovelSituacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroImovelSituacaoTipo.ID, idImovelSituacaoTipo));

			Collection colecaoImoveisSituacoesTipos = fachada.pesquisar(
					filtroImovelSituacaoTipo, ImovelSituacaoTipo.class.getName());

			if (colecaoImoveisSituacoesTipos != null
					&& !colecaoImoveisSituacoesTipos.isEmpty()) {

				Iterator colecaoImoveisSituacoesTiposIterator = colecaoImoveisSituacoesTipos
						.iterator();

				imovelSituacaoTipo = (ImovelSituacaoTipo) colecaoImoveisSituacoesTiposIterator
						.next();

			}
		}
		
		// Perfil do Imóvel
		String idImovelPerfil = (String) filtrarPerfilParcelamentoActionForm.getImovelPerfil();

		ImovelPerfil imovelPerfil = new ImovelPerfil();

		if (idImovelPerfil != null
				&& !idImovelPerfil.equals("")
				&& !idImovelPerfil.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();

			filtroImovelPerfil.adicionarParametro(new ParametroSimples(
					FiltroImovelPerfil.ID, idImovelPerfil));

			Collection colecaoImoveisPerfis = fachada.pesquisar(
					filtroImovelPerfil, ImovelPerfil.class.getName());

			if (colecaoImoveisPerfis != null
					&& !colecaoImoveisPerfis.isEmpty()) {

				Iterator colecaoImoveisPerfisIterator = colecaoImoveisPerfis
						.iterator();

				imovelPerfil = (ImovelPerfil) colecaoImoveisPerfisIterator
						.next();

			}
		}
		
		// Subcategoria
		String idSubcategoria = (String) filtrarPerfilParcelamentoActionForm.getSubcategoria();

		Subcategoria subcategoria = new Subcategoria();

		if (idSubcategoria != null
				&& !idSubcategoria.equals("")
				&& !idSubcategoria.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();

			filtroSubCategoria.adicionarParametro(new ParametroSimples(
					FiltroSubCategoria.ID, idSubcategoria));

			Collection colecaoSubcategorias = fachada.pesquisar(
					filtroSubCategoria, Subcategoria.class.getName());

			if (colecaoSubcategorias != null
					&& !colecaoSubcategorias.isEmpty()) {

				Iterator colecaoSubcategoriasIterator = colecaoSubcategorias
						.iterator();

				subcategoria = (Subcategoria) colecaoSubcategoriasIterator
						.next();

			}
		}

		// seta os parametros que serão mostrados no relatório

		parcelamentoPerfilParametros.setResolucaoDiretoria(resolucaoDiretoria);
		parcelamentoPerfilParametros.setImovelSituacaoTipo(imovelSituacaoTipo);
		parcelamentoPerfilParametros.setImovelPerfil(imovelPerfil);
		parcelamentoPerfilParametros.setSubcategoria(subcategoria);

		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterPerfilParcelamento relatorioManterPerfilParcelamento = new RelatorioManterPerfilParcelamento(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterPerfilParcelamento.addParametro(
				"filtroParcelamentoPerfil",
				filtroParcelamentoPerfil);
		relatorioManterPerfilParcelamento.addParametro(
				"parcelamentoPerfilParametros",
				parcelamentoPerfilParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterPerfilParcelamento.addParametro(
				"tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(
					relatorioManterPerfilParcelamento, tipoRelatorio,
					httpServletRequest, httpServletResponse, actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}
}