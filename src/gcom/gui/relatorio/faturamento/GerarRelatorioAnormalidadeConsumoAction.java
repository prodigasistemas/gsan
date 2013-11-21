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
package gcom.gui.relatorio.faturamento;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.faturamento.GerarRelatorioAnormalidadeConsumoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.RelatorioAnormalidadeConsumo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição do relatório de contas em revisão
 * 
 * @author Rafael Corrêa
 * @created 20/09/2007
 */
public class GerarRelatorioAnormalidadeConsumoAction extends
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

		GerarRelatorioAnormalidadeConsumoActionForm gerarRelatorioAnormalidadeConsumoActionForm = (GerarRelatorioAnormalidadeConsumoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Valida os parâmetro passados como consulta
		boolean peloMenosUmParametroInformado = false;

		// Grupo
		Integer idGrupo = null;

		if (gerarRelatorioAnormalidadeConsumoActionForm.getGrupo() != null
				&& !gerarRelatorioAnormalidadeConsumoActionForm.getGrupo()
						.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			idGrupo = new Integer(gerarRelatorioAnormalidadeConsumoActionForm
					.getGrupo());
		}
		
		// Rota
		Short cdRota = null;

		if (gerarRelatorioAnormalidadeConsumoActionForm.getRota() != null
				&& !gerarRelatorioAnormalidadeConsumoActionForm.getRota().trim()
						.equals("")) {
			peloMenosUmParametroInformado = true;
			cdRota = new Short(gerarRelatorioAnormalidadeConsumoActionForm
					.getRota());
		}

		// Gerência Regional
		Integer idGerenciaRegional = null;

		if (gerarRelatorioAnormalidadeConsumoActionForm.getRegional() != null
				&& !gerarRelatorioAnormalidadeConsumoActionForm.getRegional()
						.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			idGerenciaRegional = new Integer(
					gerarRelatorioAnormalidadeConsumoActionForm.getRegional());
		}

		// Unidade de Negócio
		Integer idUnidadeNegocio = null;

		if (gerarRelatorioAnormalidadeConsumoActionForm.getUnidadeNegocio() != null
				&& !gerarRelatorioAnormalidadeConsumoActionForm
						.getUnidadeNegocio().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			idUnidadeNegocio = new Integer(
					gerarRelatorioAnormalidadeConsumoActionForm
							.getUnidadeNegocio());
		}

		// Localidade Inicial
		Localidade localidadeInicial = null;
		SetorComercial setorComercialInicial = null;

		String idLocalidadeInicial = gerarRelatorioAnormalidadeConsumoActionForm
				.getIdLocalidadeInicial();
		String codigoSetorComercialInicial = gerarRelatorioAnormalidadeConsumoActionForm
				.getCodigoSetorComercialInicial();

		if (idLocalidadeInicial != null && !idLocalidadeInicial.equals("")) {
			peloMenosUmParametroInformado = true;

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeInicial));

			Collection colecaoLocalidades = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidades != null && !colecaoLocalidades.isEmpty()) {
				localidadeInicial = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidades);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Localidade Inicial");
			}
			
			// Setor Comercial Inicial
			if (codigoSetorComercialInicial != null && !codigoSetorComercialInicial.trim().equals("")) {
				
				FiltroSetorComercial filtro = new FiltroSetorComercial();
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialInicial));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidadeInicial.getId()));
				Collection pesquisa = (Collection) fachada.pesquisar(filtro, SetorComercial.class.getName());
				
				if (pesquisa != null && !pesquisa.isEmpty()) {
					setorComercialInicial = (SetorComercial) Util.retonarObjetoDeColecao(pesquisa);
				} else {
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Setor Comercial");
				}
			}
			
		}

		// Localidade Final
		Localidade localidadeFinal = null;
		SetorComercial setorComercialFinal = null;

		String idLocalidadeFinal = gerarRelatorioAnormalidadeConsumoActionForm
				.getIdLocalidadeFinal();
		String codigoSetorComercialFinal = gerarRelatorioAnormalidadeConsumoActionForm
				.getCodigoSetorComercialFinal();

		if (idLocalidadeFinal != null && !idLocalidadeFinal.equals("")) {
			peloMenosUmParametroInformado = true;

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeFinal));

			Collection colecaoLocalidades = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidades != null && !colecaoLocalidades.isEmpty()) {
				localidadeFinal = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidades);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Localidade Final");
			}
			
			// Setor Comercial Final
			if (codigoSetorComercialFinal != null && !codigoSetorComercialFinal.trim().equals("")) {
				
				FiltroSetorComercial filtro = new FiltroSetorComercial();
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialFinal));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidadeFinal.getId()));
				Collection pesquisa = (Collection) fachada.pesquisar(filtro, SetorComercial.class.getName());
				
				if (pesquisa != null && !pesquisa.isEmpty()) {
					setorComercialFinal = (SetorComercial) Util.retonarObjetoDeColecao(pesquisa);
				} else {
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Setor Comercial");
				}
			}
			
			pesquisarQuadra(gerarRelatorioAnormalidadeConsumoActionForm,fachada,httpServletRequest);
			validacaoFinal(gerarRelatorioAnormalidadeConsumoActionForm);	
		}
		
		Collection<Integer> colecaoIdsEmpresa = null;
		if(gerarRelatorioAnormalidadeConsumoActionForm.getColecaoIdsEmpresa() != null){
			colecaoIdsEmpresa = new ArrayList<Integer>();
			
			for (String id : gerarRelatorioAnormalidadeConsumoActionForm.getColecaoIdsEmpresa()) {
				if (!id.equals("-1")) {
					colecaoIdsEmpresa.add(new Integer(id));
				}
			}
			
			if (colecaoIdsEmpresa.size() > 0) {
				peloMenosUmParametroInformado = true;
			}	
		}

		//CRC4561 - comentado por Vivianne Sousa - 07/06/2010 - analista:Adriana Ribeiro
		// Anormalidade de Consumo
//		Collection<Integer> colecaoIdsAnormalidadeConsumo = null;
//
//		if (gerarRelatorioAnormalidadeConsumoActionForm.getColecaoIdsConsumoAnormalidade() != null) {
//			colecaoIdsAnormalidadeConsumo = new ArrayList<Integer>();
//			
//			for (String id : gerarRelatorioAnormalidadeConsumoActionForm.getColecaoIdsConsumoAnormalidade()) {
//				if (!id.equals("-1")) {
//					colecaoIdsAnormalidadeConsumo.add(new Integer(id));
//				}
//			}
//			
//			if (colecaoIdsAnormalidadeConsumo.size() > 0) {
//				peloMenosUmParametroInformado = true;
//			}
//		}
		
		// Anormalidade de Leitura Informada
		Collection<Integer> colecaoIdsAnormalidadeLeituraInformada = null;

		if (gerarRelatorioAnormalidadeConsumoActionForm.getColecaoIdsLeituraAnormalidadeInformada() != null) {
			colecaoIdsAnormalidadeLeituraInformada = new ArrayList<Integer>();
			
			for (String id : gerarRelatorioAnormalidadeConsumoActionForm.getColecaoIdsLeituraAnormalidadeInformada()) {
				if (!id.equals("-1")) {
					colecaoIdsAnormalidadeLeituraInformada.add(new Integer(id));
				}
			}
			
			if (colecaoIdsAnormalidadeLeituraInformada.size() > 0) {
				peloMenosUmParametroInformado = true;
			}
		}
		
		//CRC4561 - comentado por Vivianne Sousa - 07/06/2010 - analista:Adriana Ribeiro
		// Anormalidade de Leitura Faturada
//		Collection<Integer> colecaoIdsAnormalidadeLeitura = null;
//
//		if (gerarRelatorioAnormalidadeConsumoActionForm.getColecaoIdsLeituraAnormalidade() != null) {
//			colecaoIdsAnormalidadeLeitura = new ArrayList<Integer>();
//			
//			for (String id : gerarRelatorioAnormalidadeConsumoActionForm.getColecaoIdsLeituraAnormalidade()) {
//				if (!id.equals("-1")) {
//					colecaoIdsAnormalidadeLeitura.add(new Integer(id));
//				}
//			}
//			
//			if (colecaoIdsAnormalidadeLeitura.size() > 0) {
//				peloMenosUmParametroInformado = true;
//			}
//		}

		// Perfil do Imóvel
		Integer idImovelPerfil = null;

		if (gerarRelatorioAnormalidadeConsumoActionForm.getIdImovelPerfil() != null
				&& !gerarRelatorioAnormalidadeConsumoActionForm
						.getIdImovelPerfil().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;

			idImovelPerfil = new Integer(
					gerarRelatorioAnormalidadeConsumoActionForm
							.getIdImovelPerfil());
		}
		
		//Categoria
		Integer idCategoria = null;
		if (gerarRelatorioAnormalidadeConsumoActionForm.getIdCategoria() != null
				&& !gerarRelatorioAnormalidadeConsumoActionForm
						.getIdCategoria().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;

			idCategoria = new Integer(
					gerarRelatorioAnormalidadeConsumoActionForm
							.getIdCategoria());
		}
		

		// Anormalidade de Leitura
//		LeituraAnormalidade leituraAnormalidade = null;
//
//		String idAnormalidadeLeitura = gerarRelatorioAnormalidadeConsumoActionForm
//				.getIdLeituraAnormalidade();
//
//		if (idAnormalidadeLeitura != null && !idAnormalidadeLeitura.equals("")) {
//			peloMenosUmParametroInformado = true;
//
//			FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
//
//			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
//					FiltroLeituraAnormalidade.ID, idAnormalidadeLeitura));
//
//			Collection colecaoAnormalidadesLeitura = fachada.pesquisar(
//					filtroLeituraAnormalidade, LeituraAnormalidade.class
//							.getName());
//
//			if (colecaoAnormalidadesLeitura != null
//					&& !colecaoAnormalidadesLeitura.isEmpty()) {
//				leituraAnormalidade = (LeituraAnormalidade) Util
//						.retonarObjetoDeColecao(colecaoAnormalidadesLeitura);
//			} else {
//				throw new ActionServletException(
//						"atencao.pesquisa_inexistente", null,
//						"Anormalidade de Leitura");
//			}
//		}

		// Referência
		Integer referencia = null;

		if (gerarRelatorioAnormalidadeConsumoActionForm.getReferencia() != null
				&& !gerarRelatorioAnormalidadeConsumoActionForm.getReferencia()
						.equals("")) {
			peloMenosUmParametroInformado = true;

			referencia = Util
					.formatarMesAnoComBarraParaAnoMes(gerarRelatorioAnormalidadeConsumoActionForm
							.getReferencia());

			SistemaParametro sistemaParametro = fachada
					.pesquisarParametrosDoSistema();

			if (referencia > sistemaParametro.getAnoMesFaturamento()) {
				throw new ActionServletException(
						"atencao.ano_mes_referencia_anterior_que_ano_mes_faturamento_corrente",
						null, Util.somaMesMesAnoComBarra((Util.formatarAnoMesParaMesAno(sistemaParametro
								.getAnoMesFaturamento())),1));
			}

		}

		Integer mediaConsumoInicial = null;
		Integer mediaConsumoFinal = null;

		if (gerarRelatorioAnormalidadeConsumoActionForm
				.getIntervaloMediaConsumoInicial() != null
				&& !gerarRelatorioAnormalidadeConsumoActionForm
						.getIntervaloMediaConsumoInicial().trim().equals("")) {
			peloMenosUmParametroInformado = true;

			mediaConsumoInicial = new Integer(
					gerarRelatorioAnormalidadeConsumoActionForm
							.getIntervaloMediaConsumoInicial());

			mediaConsumoFinal = new Integer(
					gerarRelatorioAnormalidadeConsumoActionForm
							.getIntervaloMediaConsumoFinal());

			if (mediaConsumoInicial > mediaConsumoFinal) {
				throw new ActionServletException(
						"atencao.media.consumo.final.maior.media.consumo.inical");
			}

		}

		Integer numeroOcorrenciasConsecutivas = null;

		if (gerarRelatorioAnormalidadeConsumoActionForm
				.getNumOcorrenciasConsecutivas() != null
				&& !gerarRelatorioAnormalidadeConsumoActionForm
						.getNumOcorrenciasConsecutivas().trim().equals("")) {
			numeroOcorrenciasConsecutivas = new Integer(
					gerarRelatorioAnormalidadeConsumoActionForm
							.getNumOcorrenciasConsecutivas());

			if (numeroOcorrenciasConsecutivas > 12) {
				throw new ActionServletException(
						"atencao.quantidade_ocorrencia_maior");
			}
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// seta os parametros que serão mostrados no relatório

		// Fim da parte que vai mandar os parametros para o relatório
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		RelatorioAnormalidadeConsumo relatorioAnormalidadeConsumo = new RelatorioAnormalidadeConsumo(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));

		relatorioAnormalidadeConsumo.addParametro("idGrupo", idGrupo);
		
		relatorioAnormalidadeConsumo.addParametro("cdRota", cdRota);

		relatorioAnormalidadeConsumo.addParametro("idGerenciaRegional",
				idGerenciaRegional);

		relatorioAnormalidadeConsumo.addParametro("idUnidadeNegocio",
				idUnidadeNegocio);

		if (localidadeInicial != null) {
			relatorioAnormalidadeConsumo.addParametro("idLocalidadeInicial",
					localidadeInicial.getId());
		}

		if (localidadeFinal != null) {
			relatorioAnormalidadeConsumo.addParametro("idLocalidadeFinal",
					localidadeFinal.getId());
		}
		
		if (setorComercialInicial != null) {
			relatorioAnormalidadeConsumo.addParametro("idSetorComercialInicial", setorComercialInicial.getCodigo());
		}
		
		if (setorComercialFinal != null) {
			relatorioAnormalidadeConsumo.addParametro("idSetorComercialFinal", setorComercialFinal.getCodigo());
		}
		
		if (gerarRelatorioAnormalidadeConsumoActionForm.getQuadraInicialNM() != null &&
				!gerarRelatorioAnormalidadeConsumoActionForm.getQuadraInicialNM().equals("")){
			relatorioAnormalidadeConsumo.addParametro("numeroQuadraInicial",
					new Integer(gerarRelatorioAnormalidadeConsumoActionForm.getQuadraInicialNM()));
		}
		if (gerarRelatorioAnormalidadeConsumoActionForm.getQuadraFinalID() != null &&
				!gerarRelatorioAnormalidadeConsumoActionForm.getQuadraFinalNM().equals("")){
			relatorioAnormalidadeConsumo.addParametro("numeroQuadraFinal", 
					new Integer(gerarRelatorioAnormalidadeConsumoActionForm.getQuadraFinalNM()));
		}

		if (colecaoIdsEmpresa != null) {
			relatorioAnormalidadeConsumo.addParametro("colecaoIdsEmpresa", colecaoIdsEmpresa);
		}
		//CRC4561 - comentado por Vivianne Sousa - 07/06/2010 - analista:Adriana Ribeiro
//		if (colecaoIdsAnormalidadeConsumo != null) {
//		relatorioAnormalidadeConsumo.addParametro("colecaoIdsAnormalidadeConsumo", colecaoIdsAnormalidadeConsumo);
//		}

		if (colecaoIdsAnormalidadeLeituraInformada != null) {
			relatorioAnormalidadeConsumo.addParametro("colecaoIdsAnormalidadeLeituraInformada", colecaoIdsAnormalidadeLeituraInformada);
		}
		
		//CRC4561 - comentado por Vivianne Sousa - 07/06/2010 - analista:Adriana Ribeiro
//		if (colecaoIdsAnormalidadeLeitura != null) {
//			relatorioAnormalidadeConsumo.addParametro("colecaoIdsAnormalidadeLeitura", colecaoIdsAnormalidadeLeitura);
//		}

		relatorioAnormalidadeConsumo.addParametro("numeroOcorrencias",
				numeroOcorrenciasConsecutivas);

		relatorioAnormalidadeConsumo.addParametro("ocorrenciasIguais",
				gerarRelatorioAnormalidadeConsumoActionForm
						.getIndicadorOcorrenciasIguais());

		relatorioAnormalidadeConsumo.addParametro("idImovelPerfil",
				idImovelPerfil);
		relatorioAnormalidadeConsumo.addParametro("referencia", referencia);

		relatorioAnormalidadeConsumo.addParametro("mediaConsumoInicial",
				mediaConsumoInicial);
		relatorioAnormalidadeConsumo.addParametro("mediaConsumoFinal",
				mediaConsumoFinal);
		
		relatorioAnormalidadeConsumo.addParametro("tipoMedicao",
				new Integer(gerarRelatorioAnormalidadeConsumoActionForm
						.getTipoMedicao()));

		relatorioAnormalidadeConsumo.addParametro("idCategoria",idCategoria);
		
		relatorioAnormalidadeConsumo.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));

		retorno = processarExibicaoRelatorio(relatorioAnormalidadeConsumo,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}
	private void pesquisarQuadra(
			GerarRelatorioAnormalidadeConsumoActionForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		Collection colecaoPesquisa = null;
		String setorComercialCD = null;
		String setorComercialID = null;
		String quadraNM = null;
		FiltroQuadra filtroQuadra = new FiltroQuadra();

		if(form.getQuadraInicialNM() != null &&
				!form.getQuadraInicialNM().equals("")){
			
			setorComercialCD = (String) form.getCodigoSetorComercialInicial();
			setorComercialID = (String) form.getIdSetorComercialInicial();

			if (setorComercialCD != null
					&& !setorComercialCD.trim().equalsIgnoreCase("")
					&& setorComercialID != null
					&& !setorComercialID.trim().equalsIgnoreCase("")) {

				quadraNM = (String) form.getQuadraInicialNM();

				// Adiciona o id do setor comercial que está no formulário para compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				// Adiciona o número da quadra que esta no formulário para compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna quadra
				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do formulário
					form.setQuadraInicialNM("");
					form.setQuadraInicialID("");
					// Mensagem de tela
					form.setQuadraMensagemInicial("QUADRA INEXISTENTE.");
					httpServletRequest.setAttribute("corQuadraOrigem",	"exception");
					httpServletRequest.setAttribute("nomeCampo", "quadraInicialNM");
					
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Quadra Inicial");
					
				} else {
					Quadra objetoQuadra = (Quadra) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					form.setQuadraInicialNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
					form.setQuadraInicialID(String.valueOf(objetoQuadra.getId()));
//					form.setQuadraFinalNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
//					form.setQuadraFinalID(String.valueOf(objetoQuadra.getId()));
					httpServletRequest.setAttribute("corQuadraOrigem", "valor");
					
					if(form.getQuadraFinalNM() == null || form.getQuadraFinalNM().equals("")){
						form.setQuadraFinalNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
						form.setQuadraFinalID(String.valueOf(objetoQuadra.getId()));
						httpServletRequest.setAttribute("corQuadraDestino", "valor");
					}
					
				}
			} else {
				// Limpa o campo quadraOrigemNM do formulário
				form.setQuadraInicialNM("");
				form.setQuadraMensagemInicial("Informe o setor comercial inicial.");
				httpServletRequest.setAttribute("corQuadraOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercialInicial");
			}
		}
		
		if(form.getQuadraFinalNM() != null &&
				!form.getQuadraFinalNM().equals("")){
			//Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formulário.
			setorComercialCD = (String) form.getCodigoSetorComercialFinal();
			setorComercialID = (String) form.getIdSetorComercialFinal();

			// Os campos setorComercialOrigemCD e setorComercialID serão
			// obrigatórios
			if (setorComercialCD != null
					&& !setorComercialCD.trim().equalsIgnoreCase("")
					&& setorComercialID != null
					&& !setorComercialID.trim().equalsIgnoreCase("")) {

				quadraNM = (String) form.getQuadraFinalNM();

				// Adiciona o id do setor comercial que está no formulário para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				// Adiciona o número da quadra que esta no formulário para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna quadra
				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class
						.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do
					// formulário
					form.setQuadraFinalNM("");
					form.setQuadraFinalID("");
					// Mensagem de tela
					form.setQuadraMensagemFinal("QUADRA INEXISTENTE.");
					httpServletRequest.setAttribute("corQuadraDestino",	"exception");
					httpServletRequest.setAttribute("nomeCampo", "quadraFinalNM");
					
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Quadra Final");
					
				} else {
					Quadra objetoQuadra = (Quadra) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					form.setQuadraFinalNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
					form.setQuadraFinalID(String.valueOf(objetoQuadra.getId()));
					httpServletRequest.setAttribute("corQuadraDestino", "valor");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formulário
				form.setQuadraFinalNM("");
				// Mensagem de tela
				form.setQuadraMensagemFinal("Informe o setor comercial final.");
				httpServletRequest.setAttribute("corQuadraDestino", "exception");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialFinal");
			}
		}
	}
	private void validacaoFinal(GerarRelatorioAnormalidadeConsumoActionForm form) {

		// validar localidade inicial sendo maior que localidade final
		if (form.getIdLocalidadeInicial() != null
				&& form.getIdLocalidadeFinal() != null) {
			if (!form.getIdLocalidadeInicial().equals("")
					&& !form.getIdLocalidadeFinal().equals("")) {
				int origem = Integer.parseInt(form.getIdLocalidadeInicial());
				int destino = Integer.parseInt(form.getIdLocalidadeFinal());
				if (origem > destino) {
					throw new ActionServletException(
					"atencao.localidade.final.maior.localidade.inicial",null, "");
				}

			}
		}

		// validar setor comercial sendo maior que localidade final
		if (form.getCodigoSetorComercialInicial() != null
				&& form.getCodigoSetorComercialFinal() != null) {
			if (!form.getCodigoSetorComercialInicial().equals("")
					&& !form.getCodigoSetorComercialFinal().equals("")) {
				int origem = Integer.parseInt(form.getCodigoSetorComercialInicial());
				int destino = Integer.parseInt(form
						.getCodigoSetorComercialFinal());
				if (origem > destino) {
					throw new ActionServletException(
							"atencao.setor.comercial.final.maior.setor.comercial.inicial",
							null, "");
				}

			}
		}

		// validar quadra sendo maior que localidade final
		if (form.getQuadraInicialNM() != null
				&& form.getQuadraFinalNM() != null) {
			if (!form.getQuadraInicialNM().equals("")
					&& !form.getQuadraFinalNM().equals("")) {
				int origem = Integer.parseInt(form.getQuadraInicialNM());
				int destino = Integer.parseInt(form.getQuadraFinalNM());
				if (origem > destino)
					throw new ActionServletException(
							"atencao.quadra.final.maior.quadra.inical", null,
							"");
			}
		}
	
	}
}
