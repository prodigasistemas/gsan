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
 * Rômulo Aurélio de Melo Souza Filho
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

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioImoveisComAcordo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0891]-Gerar Relatório de Imóveis com Acordo
 *
 * @author Rômulo Aurélio
 * @date 23/03/2009
 */
public class GerarRelatorioImoveisComAcordoAction extends
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

		GerarRelatorioImoveisComAcordoActionForm form = (GerarRelatorioImoveisComAcordoActionForm) actionForm;

		// Inicio da parte que vai mandar os parametros para o relatório

		String idUnidadeNegocio = form.getIdUnidadeNegocio();
		String idLocalidadeInicial = form.getIdLocalidadeInicial();
		String idLocalidadeFinal = form.getIdLocalidadeFinal();
		String idGerenciaRegional = form.getIdGerenciaRegional();
		String dataInicialAcordo = form.getPeriodoInicialAcordo();
		String dataFinalAcordo = form.getPeriodoFinalAcordo();
		String rotaInicial = form.getRotaInicial();
		String rotaFinal = form.getRotaFinal();
		String sequencialRotaInicial = form.getSequencialRotaInicial();
		String sequencialRotaFinal = form.getSequencialRotaFinal();
		String idSetorComercialInicial = form.getSetorComercialInicial();
		String idSetorComercialFinal = form.getSetorComercialFinal();
		
		

		RelatorioImoveisComAcordo relatorioImoveisComAcordo = new RelatorioImoveisComAcordo(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));

		if (idUnidadeNegocio != null
				&& !idUnidadeNegocio.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.ID, idUnidadeNegocio));

			Collection colecaoUnidadeNegocio = this.getFachada().pesquisar(filtroUnidadeNegocio,
					UnidadeNegocio.class.getName());

			UnidadeNegocio unidadeNegocio = null;
			
			if (colecaoUnidadeNegocio != null && !colecaoUnidadeNegocio.isEmpty()) {
				
				unidadeNegocio = (UnidadeNegocio) Util.retonarObjetoDeColecao(colecaoUnidadeNegocio);
				
				relatorioImoveisComAcordo.addParametro("unidadeNegocio", unidadeNegocio);
				
			}

		}
		
		if (idGerenciaRegional != null
				&& !idGerenciaRegional.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.ID, idGerenciaRegional));

			Collection colecaoGerenciaRegional = this.getFachada().pesquisar(filtroGerenciaRegional,
					GerenciaRegional.class.getName());

			GerenciaRegional gerenciaRegional = null;
			
			if (colecaoGerenciaRegional != null && !colecaoGerenciaRegional.isEmpty()) {
				
				gerenciaRegional = (GerenciaRegional) Util.retonarObjetoDeColecao(colecaoGerenciaRegional);
				
				relatorioImoveisComAcordo.addParametro(
						"gerenciaRegional", gerenciaRegional);
			}

		}

		Date dataInicial = null;
		Date dataFinal = null;
		
		if (dataInicialAcordo != null && !dataInicialAcordo.equalsIgnoreCase("")) {
			
			dataInicial = Util.converteStringParaDate(dataInicialAcordo);
			
			dataFinal = Util.converteStringParaDate(dataFinalAcordo);
			
			if(dataInicial.after(dataFinal)){
				throw new ActionServletException("atencao.data_final_situacao_cobranca_invalida");
			}

			relatorioImoveisComAcordo.addParametro(
					"dataInicial", dataInicial);
			relatorioImoveisComAcordo.addParametro(
					"dataFinal", dataFinal);
		}
		
		if (idLocalidadeInicial != null
				&& !idLocalidadeInicial.trim().equals("")) {
			
			FiltroLocalidade filtroLocalidadeInicial = new FiltroLocalidade();
			
			filtroLocalidadeInicial.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeInicial));

			Collection colecaoLocalidadeInicial = this.getFachada().pesquisar(filtroLocalidadeInicial,
					Localidade.class.getName());

			Localidade localidadeInicial = null;
			
			if (colecaoLocalidadeInicial != null && !colecaoLocalidadeInicial.isEmpty()) {
				
				localidadeInicial = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidadeInicial);
				
				relatorioImoveisComAcordo.addParametro(
						"localidadeInicial", localidadeInicial);
			}else{
				throw new ActionServletException("atencao.pesquisa.localidade_inicial_inexistente");
			}

		}
		
		if (idLocalidadeFinal != null
				&& !idLocalidadeFinal.trim().equals("")) {
			
			FiltroLocalidade filtroLocalidadeFinal = new FiltroLocalidade();
			
			filtroLocalidadeFinal.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeFinal));

			Collection colecaoLocalidadeFinal = this.getFachada().pesquisar(filtroLocalidadeFinal,
					Localidade.class.getName());

			Localidade localidadeFinal = null;
			
			if (colecaoLocalidadeFinal != null && !colecaoLocalidadeFinal.isEmpty()) {
				
				localidadeFinal = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidadeFinal);
				
				relatorioImoveisComAcordo.addParametro(
						"localidadeFinal", localidadeFinal);
			}else{
				throw new ActionServletException("atencao.pesquisa.localidade_final_inexistente");
			}

		}
		
		if (idSetorComercialInicial != null
				&& !idSetorComercialInicial.trim().equals("")) {
			
			FiltroSetorComercial filtroSetorComercialInicial = new FiltroSetorComercial();
			
			filtroSetorComercialInicial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL
					, idSetorComercialInicial));

			Collection colecaoSetorComercialInicial = this.getFachada().pesquisar(filtroSetorComercialInicial,
					SetorComercial.class.getName());

			SetorComercial setorComercialInicial = null;
			
			if (colecaoSetorComercialInicial != null && !colecaoSetorComercialInicial.isEmpty()) {
				
				setorComercialInicial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercialInicial);
				
				relatorioImoveisComAcordo.addParametro(
						"setorComercialInicial", setorComercialInicial);
			}else{
				throw new ActionServletException("atencao.pesquisa.setor_inicial_inexistente");
			}

		}
		
		if (idSetorComercialFinal != null
				&& !idSetorComercialFinal.trim().equals("")) {
			
			FiltroSetorComercial filtroSetorComercialFinal = new FiltroSetorComercial();
			
			filtroSetorComercialFinal.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, idSetorComercialFinal));

			Collection colecaoSetorComercialFinal = this.getFachada().pesquisar(filtroSetorComercialFinal,
					SetorComercial.class.getName());

			SetorComercial setorComercialFinal = null;
			
			if (colecaoSetorComercialFinal != null && !colecaoSetorComercialFinal.isEmpty()) {
				
				setorComercialFinal = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercialFinal);
				
				relatorioImoveisComAcordo.addParametro(
						"setorComercialFinal", setorComercialFinal);
			}else{
				throw new ActionServletException("atencao.pesquisa.setor_final_inexistente");
			}

		}
		
		relatorioImoveisComAcordo.addParametro("rotaInicial", rotaInicial);
		relatorioImoveisComAcordo.addParametro("rotaFinal", rotaFinal);
		
		relatorioImoveisComAcordo.addParametro("sequencialRotaInicial", sequencialRotaInicial);
		relatorioImoveisComAcordo.addParametro("sequencialRotaFinal", sequencialRotaFinal);
		
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioImoveisComAcordo.addParametro(
				"tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));

		retorno = processarExibicaoRelatorio(
				relatorioImoveisComAcordo, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);
		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
