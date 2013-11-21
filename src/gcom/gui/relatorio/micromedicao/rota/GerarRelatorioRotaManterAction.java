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
package gcom.gui.relatorio.micromedicao.rota;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.micromedicao.RotaActionForm;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.micromedicao.rota.RelatorioManterRota;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
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

public class GerarRelatorioRotaManterAction extends ExibidorProcessamentoTarefaRelatorio {

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

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		RotaActionForm rotaActionForm = (RotaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		FiltroRota filtroRota = (FiltroRota) sessao
				.getAttribute("filtroRota");

		// Inicio da parte que vai mandar os parametros para o relatório

		Rota rotaParametros = new Rota();
		
		Localidade localidade = new Localidade();

		String idLocalidade = rotaActionForm.getIdLocalidade();

		if (idLocalidade != null && !idLocalidade.equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
			
			Collection colecaoLocalidades = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (colecaoLocalidades != null && !colecaoLocalidades.isEmpty()) {
				localidade = (Localidade) colecaoLocalidades.iterator().next();
			}			
		}

		String idSetorComercial = (String) rotaActionForm.getCodigoSetorComercial();

		SetorComercial setorComercial = new SetorComercial();

		if (idSetorComercial != null && !idSetorComercial.equals("")) {
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, idSetorComercial));

			Collection colecaoSetoresComerciais = fachada.pesquisar(
					filtroSetorComercial, SetorComercial.class.getName());

			if (colecaoSetoresComerciais != null && !colecaoSetoresComerciais.isEmpty()) {
				// Titulo do Logradouro Foi Encontrado
				Iterator colecaoSetoresComerciaisIterator = colecaoSetoresComerciais
						.iterator();

				setorComercial = (SetorComercial) colecaoSetoresComerciaisIterator
						.next();

			}
		}
		
		String idGrupoFaturamento = (String) rotaActionForm.getFaturamentoGrupo();

		FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();

		if (idGrupoFaturamento != null && !idGrupoFaturamento.equals("")) {
			FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();

			filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoGrupo.ID, idGrupoFaturamento));

			Collection colecaoFaturamentosGrupos = fachada.pesquisar(
					filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

			if (colecaoFaturamentosGrupos != null && !colecaoFaturamentosGrupos.isEmpty()) {
				// Titulo do Logradouro Foi Encontrado
				Iterator colecaoFaturamentosGruposIterator = colecaoFaturamentosGrupos
						.iterator();

				faturamentoGrupo = (FaturamentoGrupo) colecaoFaturamentosGruposIterator
						.next();

			}
		}
		
		Short codigo = null;
		
		String codigoPesquisar = rotaActionForm.getCodigoRota();
		
		if (codigoPesquisar != null && !codigoPesquisar.equals("")) {
			codigo = new Short(codigoPesquisar);
		}

		Short indicadorDeUso = null;

		if (rotaActionForm.getIndicadorUso() != null
				&& !rotaActionForm.getIndicadorUso()
						.equals("")) {

			indicadorDeUso = new Short(""
					+ rotaActionForm.getIndicadorUso());
		}

		// seta os parametros que serão mostrados no relatório
		
		setorComercial.setLocalidade(localidade);
		
		rotaParametros.setCodigo(codigo);
		rotaParametros.setSetorComercial(setorComercial);
		rotaParametros.setFaturamentoGrupo(faturamentoGrupo);
		rotaParametros.setIndicadorUso(indicadorDeUso);

		// Fim da parte que vai mandar os parametros para o relatório

			// cria uma instância da classe do relatório
			RelatorioManterRota relatorioManterRota = new RelatorioManterRota(
					(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			relatorioManterRota.addParametro("filtroRota", filtroRota);
			relatorioManterRota.addParametro("rotaParametros",
					rotaParametros);

			// chama o metódo de gerar relatório passando o código da analise
			// como parâmetro
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorioManterRota.addParametro("tipoFormatoRelatorio", Integer
					.parseInt(tipoRelatorio));
			try {
				retorno = processarExibicaoRelatorio(relatorioManterRota,
						tipoRelatorio, httpServletRequest, httpServletResponse,
						actionMapping);

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