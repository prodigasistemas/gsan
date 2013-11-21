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
package gcom.gui.micromedicao;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.bean.ImovelMicromedicao;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroMotivoInterferenciaTipo;
import gcom.faturamento.FiltroMovimentoContaPrefaturada;
import gcom.faturamento.MotivoInterferenciaTipo;
import gcom.faturamento.MovimentoContaPrefaturada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroReleituraMobile;
import gcom.micromedicao.ReleituraMobile;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.micromedicao.bean.CalculoConsumoHelper;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraTipo;
import gcom.micromedicao.medicao.FiltroMedicaoHistoricoSql;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de inserir bairro
 * 
 * @author Sávio Luiz, Ivan Sérgio
 * @created 28 de Junho de 2004
 * @date 06/03/2008
 * @alteracao: Verificar poco_id para indicar que o imovel possui poco em vez de hidi_id
 */
public class ExibirDadosAnaliseExcecoesLeituraResumidoAction extends GcomAction {
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
		ActionForward retorno = actionMapping
				.findForward("dadosAnaliseMedicaoConsumoResumo");

		LeituraConsumoActionForm leituraConsumoActionForm = (LeituraConsumoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = this.getUsuarioLogado(httpServletRequest);
		
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo)sessao.getAttribute("faturamentoGrupo");
		String mesAnoPesquisa = (String) sessao.getAttribute("mesAnoPesquisa");
		
		if(leituraConsumoActionForm.getIdAnormalidade() != null && 
			!leituraConsumoActionForm.getIdAnormalidade().trim().equals("") && 
			httpServletRequest.getParameter("pesquisarAnormalidade") != null){
			
			String codigoAnormalidadeLeituraDigitadoEnter = leituraConsumoActionForm.getIdAnormalidade();
			// Verifica se o usuário alterou a Anormalidade de Leitura
				FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
				filtroLeituraAnormalidade
						.adicionarParametro(new ParametroSimples(
								FiltroLeituraAnormalidade.ID,
								codigoAnormalidadeLeituraDigitadoEnter));

				Collection anormalidadeLeituraEncontrada = fachada
						.pesquisar(filtroLeituraAnormalidade,
								LeituraAnormalidade.class.getName());

				if (anormalidadeLeituraEncontrada != null
						&& !anormalidadeLeituraEncontrada.isEmpty()) {

					leituraConsumoActionForm
							.setIdAnormalidade(""
									+ ((LeituraAnormalidade) ((List) anormalidadeLeituraEncontrada)
											.get(0)).getId());
					leituraConsumoActionForm
							.setDescricaoAnormalidade(((LeituraAnormalidade) ((List) anormalidadeLeituraEncontrada)
									.get(0)).getDescricao());
					
					leituraConsumoActionForm.
							setIndicadorLeitura(""
									+ ((LeituraAnormalidade) ((List) anormalidadeLeituraEncontrada)
											.get(0)).getIndicadorLeitura());

				} else {
					leituraConsumoActionForm
							.setIdAnormalidade("");
					httpServletRequest.setAttribute(
							"idAnormalidadeNaoEncontrada", "true");
					leituraConsumoActionForm
							.setDescricaoAnormalidade("Anormalidade de leitura inexistente");
					httpServletRequest.setAttribute("nomeCampo",
							"idAnormalidade");
					leituraConsumoActionForm.setIndicadorLeitura("");
				}	
		} else {
			leituraConsumoActionForm.setDataLeituraAnteriorFaturamento("");
			leituraConsumoActionForm.setDataLeituraAtualFaturamento("");
			leituraConsumoActionForm.setDataLeituraAtualInformada("");
			leituraConsumoActionForm.setConsumo("");
			leituraConsumoActionForm.setLeituraAnterior("");
			leituraConsumoActionForm.setLeituraAnteriorFaturamento("");
			leituraConsumoActionForm.setLeituraAtualFaturada("");
			leituraConsumoActionForm.setLeituraAtualInformada("");
			leituraConsumoActionForm.setIdAnormalidade("");
			leituraConsumoActionForm.setDescricaoAnormalidade("");
			leituraConsumoActionForm.setIndicadorLeitura("");
			leituraConsumoActionForm.setConsumoMedioImovel("");
			leituraConsumoActionForm.setDiasConsumo("");
			leituraConsumoActionForm.setVarConsumo("");
			leituraConsumoActionForm.setLeituraSituacaoAtual("");
			leituraConsumoActionForm.setIdFuncionario("");
			leituraConsumoActionForm.setConsumoInformado("");
			leituraConsumoActionForm.setConsumoTipo("");
			leituraConsumoActionForm.setConsumoAnormalidadeAbreviada("");
			leituraConsumoActionForm.setMedido("");
			leituraConsumoActionForm.setMotivoInterferenciaTipo(-1);
			leituraConsumoActionForm.setObservacao("");
			
			Integer idAnormalidadeConsumo = 0;
			
			String codigoImovel = httpServletRequest
					.getParameter("idRegistroAtualizacao");
			
			if(httpServletRequest.getParameter("consultaImovelLista") != null){
				Collection colecaoTeste = (Collection)sessao.getAttribute("colecaoIdsImovelTotal");
				Iterator iteratorTeste = colecaoTeste.iterator();
				int contem = 0;
				while(iteratorTeste.hasNext()){
					ImovelMicromedicao imovelMicromedicaoTeste = (ImovelMicromedicao)iteratorTeste.next();
					Integer codigoImovelTeste = imovelMicromedicaoTeste.getImovel().getId();
					if(codigoImovel.equals(codigoImovelTeste.toString())){
						contem = 1;
						break;
					}
				}
				if(contem != 1){
					throw new ActionServletException(
							"atencao.pesquisa.nenhumresultado");
				}
			}
			
			String idMedicaoTipo = httpServletRequest
					.getParameter("medicaoTipo");
	
			//================ parte de controle de paginas (Avançar e Voltar) ===
			FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql = new FiltroMedicaoHistoricoSql();
			
			if (sessao.getAttribute("filtroMedicaoHistoricoSql") != null) {
				filtroMedicaoHistoricoSql = (FiltroMedicaoHistoricoSql) sessao
						.getAttribute("filtroMedicaoHistoricoSql");
			}
			
			Integer totalRegistros = null;
			if (sessao.getAttribute("totalRegistros") != null
					&& !sessao.getAttribute("totalRegistros").equals("")) {
				totalRegistros = (Integer) sessao
						.getAttribute("totalRegistros");
			} else {
				totalRegistros = fachada.filtrarExcecoesLeiturasConsumosCount(
						faturamentoGrupo, filtroMedicaoHistoricoSql,
						(String) sessao.getAttribute("mesAnoPesquisa"),
						(String)sessao.getAttribute("valorAguaEsgotoInicial"), 
						(String)sessao.getAttribute("valorAguaEsgotoFinal"));
				sessao.setAttribute("totalRegistros", totalRegistros);
			}
			
			if (totalRegistros == 0) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
	
			int numeroPaginasPesquisa = 0;
			//numeroPaginasPesquisa é inicialmente passado pela sessao pelo ExibirManterAnaliseExcecoesConsumosAction
			//depois é sobreposto nesse action
			if (sessao.getAttribute("numeroPaginasPesquisa") != null
					&& !sessao.getAttribute("numeroPaginasPesquisa").equals("")) {
				numeroPaginasPesquisa = (Integer) sessao
						.getAttribute("numeroPaginasPesquisa");
			}
	
			//index é a variavel q guarda a posição do objeto imovel dentro da colecao de imoveis
			int index = 0;
			if (sessao.getAttribute("index") != null) {
				index = (Integer) sessao.getAttribute("index");
			}

			Collection colecaoIdsImovel = null;
			if(sessao.getAttribute("colecaoIdsImovelTotal") != null){
				 colecaoIdsImovel = (Collection)sessao.getAttribute("colecaoIdsImovelTotal");
			}else{
				
				colecaoIdsImovel =  fachada.filtrarExcecoesLeiturasConsumos(faturamentoGrupo,
						filtroMedicaoHistoricoSql, totalRegistros, true, (String)sessao.getAttribute("mesAnoPesquisa"),
								(String)sessao.getAttribute("valorAguaEsgotoInicial"), (String)sessao.getAttribute("valorAguaEsgotoFinal"));
				sessao.setAttribute("colecaoIdsImovelTotal", colecaoIdsImovel);
			}
	
			Imovel imovelAtual = ((ImovelMicromedicao) ((List) colecaoIdsImovel)
					.get(index)).getImovel();
			
			// Adiciona os objeto selecionados
			String observacao = (String)httpServletRequest.getParameter("observacao");
			adicionarObjetosSelecionadosColecoes(httpServletRequest, sessao, imovelAtual, observacao);
			
			// Limpa a sessao para não marcar o checkbox caso o novo imóvel não esteja na coleção
			sessao.setAttribute("analisado", "");
			sessao.setAttribute("gerarAviso", "");
			sessao.setAttribute("gerarOS", "");
			sessao.setAttribute("gerarRelatorio", "");
			sessao.setAttribute("observacao", "");

			// Atualiza os dados de medição histórico colocando como analisado
			if (sessao.getAttribute("habilitaCampos") == null) {
				
				
				String analisado = httpServletRequest
						.getParameter("analisado");

				// Verifica se o usuário marcou a opção de analisado
				if (analisado != null && !analisado.trim().equals("")) {

					MedicaoHistorico medicaoHistorico = ((ImovelMicromedicao) ((List) colecaoIdsImovel)
							.get(index)).getMedicaoHistorico();

					if (medicaoHistorico.getIndicadorAnalisado().equals(
							MedicaoHistorico.INDICADOR_ANALISADO_NAO)) {

						fachada
								.atualizarIndicadorAnalisadoMedicaoHistorico(
										medicaoHistorico.getId(),
										usuarioLogado);
						
						medicaoHistorico.setUsuarioAlteracao(usuarioLogado);
						medicaoHistorico.setIndicadorAnalisado(MedicaoHistorico.INDICADOR_ANALISADO_SIM);

					}
				}
			}
			
			// verifica se é a primeira vez.Se for então pesquisa o index do id do
			// imovel na coleção para não precisar ficar rodando a coleção toda vez
			// que o usuário quiser o imovel anterior ou o proximo imovel
			if (codigoImovel != null && !codigoImovel.equals("")) {
				int i = 0;
				Iterator iterator = colecaoIdsImovel.iterator();
				while (iterator.hasNext()) {
					ImovelMicromedicao imovelMicromedicao = (ImovelMicromedicao) iterator
							.next();
					if (!imovelMicromedicao.getImovel().getId().equals(
							new Integer(codigoImovel))) {
						i = i + 1;
					} else {
						break;
					}
				}
				index = i;
				sessao.setAttribute("index", index);
				// caso não seja a primeira vez então, dependendo do parametro que
				// foi passado, recupera o id do imóvel para ser pesquisado
			} else {
				
				if (httpServletRequest.getParameter("imovelAnterior") != null) {
					index = index - 1;
					leituraConsumoActionForm.setAnalisado("");
					leituraConsumoActionForm.setGerarAviso("");
					leituraConsumoActionForm.setGerarOS("");
					leituraConsumoActionForm.setGerarRelatorio("");
					leituraConsumoActionForm.setMotivoInterferenciaTipo(-1);
				} else if (httpServletRequest.getParameter("proximoImovel") != null) {
					index = index + 1;
					leituraConsumoActionForm.setAnalisado("");
					leituraConsumoActionForm.setGerarAviso("");
					leituraConsumoActionForm.setGerarOS("");
					leituraConsumoActionForm.setGerarRelatorio("");
					leituraConsumoActionForm.setMotivoInterferenciaTipo(-1);
				}
				
			// caso
			if (index == colecaoIdsImovel.size() || index == -1) {
					if (colecaoIdsImovel != null && !colecaoIdsImovel.isEmpty()) {
	
						// recupera o id do imovel
						codigoImovel = ((ImovelMicromedicao) ((List) colecaoIdsImovel)
								.get(index)).getImovel().getId().toString();
						if(((ImovelMicromedicao) ((List) colecaoIdsImovel)
								.get(index)).getMedicaoHistorico().getMedicaoTipo() != null){
							idMedicaoTipo = ((ImovelMicromedicao) ((List) colecaoIdsImovel)
									.get(index)).getMedicaoHistorico().getMedicaoTipo().getId().toString();
						}
						sessao.setAttribute("index", index);
					}
				} else {
	
					// recupera o id do imovel
					codigoImovel = ((ImovelMicromedicao) ((List) colecaoIdsImovel)
							.get(index)).getImovel().getId().toString();
					
					if(((ImovelMicromedicao) ((List) colecaoIdsImovel)
							.get(index)).getMedicaoHistorico().getMedicaoTipo() != null){
						if(((ImovelMicromedicao) ((List) colecaoIdsImovel)
								.get(index)).getMedicaoHistorico().getMedicaoTipo().getId() != null){
							idMedicaoTipo = ((ImovelMicromedicao) ((List) colecaoIdsImovel)
									.get(index)).getMedicaoHistorico().getMedicaoTipo().getId().toString();	
						}
					}
					sessao.setAttribute("index", index);
				}
			}
			
			imovelAtual = ((ImovelMicromedicao) ((List) colecaoIdsImovel).get(index)).getImovel();

			//	Pesquisa perfil do imovel para liberar acesso a alterar leitura anterior e atual
			
			ImovelPerfil imovelPerfil = fachada.recuperaPerfilImovel(
					new Integer(codigoImovel));
			
			
			/**
			 * CRC_5084
			 * Autor: Hugo Leonardo
			 * Analista: Adriana Ribeiro.
			 * Data: 26/10/2010
			 * 
			 * */
			FiltroMovimentoContaPrefaturada filtroMovimentoContaPrefaturada = new FiltroMovimentoContaPrefaturada();
			filtroMovimentoContaPrefaturada.adicionarParametro(new ParametroSimples(
					FiltroMovimentoContaPrefaturada.MATRICULA, codigoImovel));
			filtroMovimentoContaPrefaturada.adicionarParametro(new ParametroSimples(
					FiltroMovimentoContaPrefaturada.ID_MEDICAO_TIPO, idMedicaoTipo));
			filtroMovimentoContaPrefaturada.adicionarParametro(new ParametroSimples(
					FiltroMovimentoContaPrefaturada.ANO_MES_REFERENCIA_PRE_FATURAMENTO, Util.formatarMesAnoComBarraParaAnoMes(mesAnoPesquisa)));
			
//			filtroMovimentoContaPrefaturada.adicionarCaminhoParaCarregamentoEntidade(FiltroMovimentoContaPrefaturada.CONTA);
//			filtroMovimentoContaPrefaturada.adicionarParametro(new ParametroNaoNulo(FiltroMovimentoContaPrefaturada.CONTA));
//			filtroMovimentoContaPrefaturada.adicionarParametro(new ParametroSimples(
//					FiltroMovimentoContaPrefaturada.ID_CONTA_DEBITO_CREDITO_SITUACAO_ATUAL, DebitoCreditoSituacao.PRE_FATURADA));
			
			
			Collection movimentoContaPrefaturadaEncontrada = fachada.pesquisar(
					filtroMovimentoContaPrefaturada, MovimentoContaPrefaturada.class.getName());
			
			Imovel imovelFiltro = null;
			
			if (codigoImovel != null && !codigoImovel.equals("") ) {
				//obtem dados de sistema parametros
				
				// Rota e Seq de Rota
				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.rota");
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("rotaAlternativa");
				
				filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, new Integer(codigoImovel)));
				Collection imoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
				
				if(!imoveis.isEmpty()){
					
					imovelFiltro = (Imovel) imoveis.iterator().next();
					
					if (imovelFiltro.getRotaAlternativa() != null){
						
						leituraConsumoActionForm.setRota(imovelFiltro.getRotaAlternativa().getCodigo() + "");
					}
					else{
						
						leituraConsumoActionForm.setRota(imovelFiltro.getQuadra().getRota().getCodigo() + "");
					}
					
					if (imovelFiltro.getNumeroSequencialRota() != null) {
						leituraConsumoActionForm.setSeqRota(imovelFiltro.getNumeroSequencialRota().toString());
					}
				}
			}
			
			boolean existeContaPreFaturada = false;
			Integer idDebitoCreditoSituacaoAtualDaConta = fachada.pesquisarDebitoCreditoSituacaoAtualDaConta(
					new Integer(codigoImovel), Util.formatarMesAnoComBarraParaAnoMes(mesAnoPesquisa));
			if(idDebitoCreditoSituacaoAtualDaConta != null){
				existeContaPreFaturada = true;
			}
			
//			boolean emissaoConta = false;
//			
//			if(!Util.isVazioOrNulo(movimentoContaPrefaturadaEncontrada)){
//				
//				MovimentoContaPrefaturada mcpf = (MovimentoContaPrefaturada) movimentoContaPrefaturadaEncontrada.iterator().next();
//				
//				if(mcpf.getIndicadorEmissaoConta().compareTo(ConstantesSistema.NAO) == 0){
//					if(imovelFiltro != null && ( imovelFiltro.getImovelCondominio() != null 
//							|| imovelFiltro.getIndicadorImovelCondominio().compareTo(ConstantesSistema.SIM) == 0 )){
//						
//						emissaoConta = true;
//					}
//				}
//
//			}else{
//				if(imovelFiltro != null && ( imovelFiltro.getImovelCondominio() != null 
//						|| imovelFiltro.getIndicadorImovelCondominio().compareTo(ConstantesSistema.SIM) == 0 )){
//					
//					emissaoConta = true;
//				}
//			}
//			
//			boolean desabilitaAtualizarImovel = false;
//			if(imovelAtual.getQuadra().getRota().getLeituraTipo().getId().intValue() == LeituraTipo.LEITURA_E_ENTRADA_SIMULTANEA.intValue()
//					&& imovelPerfil.getIndicadorGerarDadosLeitura().intValue() == ConstantesSistema.INDICADOR_USO_ATIVO.intValue()
//					&& !existeContaPreFaturada && emissaoConta){
//				
//				desabilitaAtualizarImovel = true;
//			}
			
			
			boolean desabilitaAtualizarImovel = true;
			
			if(imovelAtual.getQuadra().getRota().getLeituraTipo().getId().intValue() == LeituraTipo.LEITURA_E_ENTRADA_SIMULTANEA.intValue() && 
					imovelPerfil.getIndicadorGerarDadosLeitura().intValue() == ConstantesSistema.INDICADOR_USO_ATIVO.intValue()){

				//Caso o tipo de leitura da rota do imóvel seja igual a LEIT.E ENTR.SIMULTAN 
				//(LTTP_ID = 3 da tabela ROTA), então desabilita os campos de atualização da tela. 
				desabilitaAtualizarImovel = true;
			}
			
			if(existeContaPreFaturada){
				//Caso a conta esteja como pré-faturada (DCST_IDATUAL=9) habilitar os campos para alteração da conta
				desabilitaAtualizarImovel = false;
			
			}
			
			if(!Util.isVazioOrNulo(movimentoContaPrefaturadaEncontrada)){
				//Caso o imóvel não esteja em MOVIMENTO_CONTA_PREFATURADA para o ano/mês de referencia
				//ou MCPF_ICEMISSAOCONTA esteja como 2, habilitar campos para alteração
				
				MovimentoContaPrefaturada mcpf = (MovimentoContaPrefaturada) movimentoContaPrefaturadaEncontrada.iterator().next();
				if(mcpf.getIndicadorEmissaoConta().compareTo(ConstantesSistema.NAO) == 0){
					desabilitaAtualizarImovel = false;
				}
			}else{
				desabilitaAtualizarImovel = false;
			}
			
			
			if(imovelFiltro != null && ( imovelFiltro.getImovelCondominio() != null 
					|| imovelFiltro.getIndicadorImovelCondominio().compareTo(ConstantesSistema.SIM) == 0 )){
				//Caso o imóvel seja condomínio (IMOV_IDIMOVELCONDOMINIO seja diferente de NULL
				//ou IMOV_ICIMOVELCONDOMINIO possua valor 1), não permitir alterar
				desabilitaAtualizarImovel = true;
			}
			
			//Caso o imovel possua conta com situação diferente de pre-faturada para referencia informada
			//não sera possivel atualizar a medição(desabilita o botao Atualizar). 
			boolean imovelPossuiContaDiferentePreFaturada = fachada.pesquisarContaDoImovelDiferentePreFaturada(
					new Integer(codigoImovel), Util.formatarMesAnoComBarraParaAnoMes(mesAnoPesquisa));
			
			//imovelPossuiContaDiferentePreFaturada = true é por que o imovel possui conta diferente de prefaturada.
			if ( imovelPossuiContaDiferentePreFaturada ) {
				desabilitaAtualizarImovel = true;
			}
			
			
			httpServletRequest.setAttribute("desabilitaAtualizarImovel",desabilitaAtualizarImovel);
			
//			if(emissaoConta){
			
				verificarAbrangenciaUsuario(httpServletRequest, usuarioLogado, imovelAtual);
//			}
			
			Collection colecaoFaturamentoSituacaoEspecial = fachada.pesquisarSituacaoEspecialFaturamentoVigente(imovelAtual.getId(), Util.formatarMesAnoComBarraParaAnoMes(mesAnoPesquisa));
			
			httpServletRequest.setAttribute("colecaoFaturamentoSituacaoEspecial", colecaoFaturamentoSituacaoEspecial);
			
			// Verifica se o objeto está na coleção para deixar o checkbox marcado
			Collection colecaoImoveisGerarAviso = (Collection) sessao.getAttribute("colecaoImoveisGerarAviso");
			Collection colecaoImoveisGerarOS = (Collection) sessao.getAttribute("colecaoImoveisGerarOS");
			Collection colecaoImoveisGerarRelatorio = (Collection) sessao.getAttribute("colecaoImoveisGerarRelatorio");
			HashMap<Integer, String> colecaoObservacaoOS = (HashMap<Integer, String>) sessao.getAttribute("colecaoObservacaoOS");
			
			if (colecaoImoveisGerarAviso != null && colecaoImoveisGerarAviso.contains(imovelAtual.getId())) {
				sessao.setAttribute("gerarAviso", "1");
			}
			
			if (colecaoImoveisGerarOS != null && colecaoImoveisGerarOS.contains(imovelAtual)) {
				sessao.setAttribute("gerarOS", "1");
				String observacao2 = colecaoObservacaoOS.get(imovelAtual.getId());
				sessao.setAttribute("observacao", observacao2);
				leituraConsumoActionForm.setObservacao(observacao2);
			}
			
			if (colecaoImoveisGerarRelatorio != null && colecaoImoveisGerarRelatorio.contains(imovelAtual)) {
				sessao.setAttribute("gerarRelatorio", "1");
			}
			
			sessao.setAttribute("indiceImovel", ""+(index+1));
			//====================================================================
			
			if (codigoImovel != null && !codigoImovel.equals("") ) {
				//obtem dados de sistema parametros
				
				/* 
				 * Comentado
				 * 
				 * CRC_5084
				 * Autor: Hugo Leonardo
				 * Analista: Adriana Ribeiro.
				 * Data: 26/10/2010
				 */
				// Rota e Seq de Rota
//				FiltroImovel filtroImovel = new FiltroImovel();
//				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.rota");
//				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("rotaAlternativa");
//				
//				filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, new Integer(codigoImovel)));
//				Collection imoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
//				
//				if(!imoveis.isEmpty()){
//					
//					Imovel imovel = (Imovel) imoveis.iterator().next();
//					
//					if (imovel.getRotaAlternativa() != null){
//						
//						leituraConsumoActionForm.setRota(imovel.getRotaAlternativa().getCodigo() + "");
//					}
//					else{
//						
//						leituraConsumoActionForm.setRota(imovel.getQuadra().getRota().getCodigo() + "");
//					}
//					
//					if (imovel.getNumeroSequencialRota() != null) {
//						leituraConsumoActionForm.setSeqRota(imovel.getNumeroSequencialRota().toString());
//					}
//				}
				
				
				boolean ligacaoAgua = false;
				if(idMedicaoTipo != null && idMedicaoTipo.trim().equals("" + MedicaoTipo.LIGACAO_AGUA)){
					ligacaoAgua = true;
				}
				
				sessao.setAttribute("ligacaoAgua", ligacaoAgua);
				sessao.setAttribute("tipoMedicao", idMedicaoTipo);
				
				// Cria as istancias dos objetos q receberam os dados q iram compor a tela
				ImovelMicromedicao imovelMicromedicaoDadosResumo = new ImovelMicromedicao();
				ImovelMicromedicao imovelMicromedicaoCarregaMedicaoResumo = new ImovelMicromedicao();
				
				imovelMicromedicaoDadosResumo = fachada.pesquiarImovelExcecoesApresentaDadosResumido(new Integer(codigoImovel), ligacaoAgua);
				imovelMicromedicaoCarregaMedicaoResumo = fachada.carregarDadosMedicaoResumido(new Integer(codigoImovel), ligacaoAgua, Util.formatarMesAnoParaAnoMesSemBarra((String)sessao.getAttribute("mesAnoPesquisa")));
				
				String mesAnoDigitado = Util.formatarMesAnoParaAnoMesSemBarra((String)sessao.getAttribute("mesAnoPesquisa"));
				
				if(imovelMicromedicaoDadosResumo.getImovel() != null &&
						imovelMicromedicaoDadosResumo.getImovel().getHidrometroInstalacaoHistorico() != null){
					leituraConsumoActionForm.setInstalacaoHidrometro(Util.formatarData(imovelMicromedicaoDadosResumo.getImovel().getHidrometroInstalacaoHistorico().getDataInstalacao()));
				}else{
					leituraConsumoActionForm.setInstalacaoHidrometro("");
				}
				
				imovelMicromedicaoDadosResumo.getImovel().setId(new Integer(codigoImovel));
				sessao.setAttribute("imovelMicromedicaoDadosResumo",imovelMicromedicaoDadosResumo);
				sessao.setAttribute("idImovel", codigoImovel);
				sessao.setAttribute("imovelMicromedicaoCarregaMedicaoResumo", imovelMicromedicaoCarregaMedicaoResumo);

				if(imovelMicromedicaoDadosResumo.getImovel() != null 
						&& imovelMicromedicaoDadosResumo.getImovel().getPocoTipo() != null 
						&& imovelMicromedicaoDadosResumo.getImovel().getPocoTipo().getId() != null
						&& imovelMicromedicaoDadosResumo.getImovel().getPocoTipo().getId() != 0){
					sessao.setAttribute("poco",true);
				}else{
					sessao.removeAttribute("poco");
				}
				
				Imovel imovel = new Imovel();
				imovel.setId(new Integer(codigoImovel));
				Categoria categoria = fachada.obterDescricoesCategoriaImovel(imovel);
				sessao.setAttribute("categoria", categoria);
				
				String inscricaoImovel = fachada.pesquisarInscricaoImovel(new Integer(codigoImovel));
				sessao.setAttribute("inscricaoFormatada", inscricaoImovel);
				
				Collection colecaoMedicaoHistorico = fachada.carregarDadosMedicaoResumo(new Integer(
						codigoImovel), ligacaoAgua);
	
				Collection imoveisMicromedicaoCarregamento = null;
				Collection colecaoImovelMicromedicao = new ArrayList();
				
				MedicaoHistorico medicaoHistoricoAnoMesAtual = new MedicaoHistorico();
				ImovelMicromedicao imovelMicromedicaoConsumo = new ImovelMicromedicao();

				// Pega a medição histórico da pesquisa para setar leitura do campo e leiturista
				MedicaoHistorico medicaoHistorico = ((ImovelMicromedicao) ((List) colecaoIdsImovel)
						.get(index)).getMedicaoHistorico();

				if (medicaoHistorico.getIndicadorAnalisado() != null){
					leituraConsumoActionForm.setAnalisado(medicaoHistorico.getIndicadorAnalisado().toString());
					sessao.setAttribute("analisado", medicaoHistorico.getIndicadorAnalisado().toString());
				} 
				
				if (medicaoHistorico.getUsuarioAlteracao() != null) {
					leituraConsumoActionForm.setLoginUsuario(medicaoHistorico.getUsuarioAlteracao().getLogin());
					leituraConsumoActionForm.setNomeUsuario(medicaoHistorico.getUsuarioAlteracao().getNomeUsuario());
				}else{
					leituraConsumoActionForm.setLoginUsuario("");
					leituraConsumoActionForm.setNomeUsuario("");
				}				
				
				if (medicaoHistorico.getLeituraCampo() != null) {
					leituraConsumoActionForm.setLeituraCampo(medicaoHistorico.getLeituraCampo().toString());
				}else{
					leituraConsumoActionForm.setLeituraCampo("");
				}
				
				if (medicaoHistorico.getLeiturista().getCliente() != null) {
					leituraConsumoActionForm.setNomeLeiturista(medicaoHistorico.getLeiturista().getCliente().getNome());
				} else if (medicaoHistorico.getLeiturista().getFuncionario() != null) {
					leituraConsumoActionForm.setNomeLeiturista(medicaoHistorico.getLeiturista().getFuncionario().getNome());
				}
				
				if (imovelMicromedicaoCarregaMedicaoResumo.getMedicaoHistorico().getMotivoInterferenciaTipo() != null){
					leituraConsumoActionForm.setMotivoInterferenciaTipo(
							imovelMicromedicaoCarregaMedicaoResumo.getMedicaoHistorico().getMotivoInterferenciaTipo().getId());
				} 
				
				// Carrega o combo de calculo de consumo
				Collection<CalculoConsumoHelper> colecaoCalculoConsumo = carregarComboCalculoConsumo(medicaoHistorico, imovel, Util.formatarMesAnoComBarraParaAnoMes(mesAnoPesquisa));
				sessao.setAttribute("colecaoCalculoConsumo", colecaoCalculoConsumo);
				leituraConsumoActionForm.setCalculoConsumo("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
	
				if (colecaoMedicaoHistorico != null && !colecaoMedicaoHistorico.isEmpty()) {
					Iterator iteratorMedicaoHistorico = colecaoMedicaoHistorico
							.iterator();
	
					while (iteratorMedicaoHistorico.hasNext()) {
						MedicaoHistorico medicaoHistoricoConsumo = (MedicaoHistorico) iteratorMedicaoHistorico
								.next();
						if (medicaoHistoricoConsumo.getAnoMesReferencia() != 0) {
							
							
							imoveisMicromedicaoCarregamento = fachada
									.carregarDadosConsumo(
											new Integer(codigoImovel),
											medicaoHistoricoConsumo
													.getAnoMesReferencia(), ligacaoAgua);
	
							if (imoveisMicromedicaoCarregamento != null) {
								ImovelMicromedicao imovelMicromedicao = (ImovelMicromedicao)imoveisMicromedicaoCarregamento.iterator().next();
								if(imovelMicromedicao.getMedicaoHistorico() != null 
										&& imovelMicromedicao.getMedicaoHistorico().getNumeroConsumoMes() != null){
									medicaoHistoricoConsumo.setNumeroConsumoMes(imovelMicromedicao.getMedicaoHistorico().getNumeroConsumoMes());
								}
								
								if(Util.formatarMesAnoComBarraParaAnoMes(mesAnoPesquisa) == medicaoHistoricoConsumo.getAnoMesReferencia()){
									
									if (imovelMicromedicao.getConsumoHistorico().getNumeroConsumoFaturadoMes() != null) {
										leituraConsumoActionForm.setConsumo(imovelMicromedicao.getConsumoHistorico().getNumeroConsumoFaturadoMes().toString());
									} else {
										leituraConsumoActionForm.setConsumo("");
									}
									
									if (imovelMicromedicao.getConsumoHistorico().getConsumoMedio() != null) {
										leituraConsumoActionForm.setConsumoMedioImovel(imovelMicromedicao.getConsumoHistorico().getConsumoMedio().toString());
									} else {
										leituraConsumoActionForm.setConsumoMedioImovel("");
									}
									
									if(imovelMicromedicao.getConsumoHistorico().getConsumoRateio() != null){
										leituraConsumoActionForm.setRateio(imovelMicromedicao.getConsumoHistorico().getConsumoRateio().toString());
									}
								}
								
								imovelMicromedicao.setMedicaoHistorico(medicaoHistoricoConsumo);
								
								colecaoImovelMicromedicao.add(imovelMicromedicao);
							}
							if(mesAnoDigitado.equals(medicaoHistoricoConsumo.getAnoMesReferencia()+"")){
								medicaoHistoricoAnoMesAtual = medicaoHistoricoConsumo;
								sessao.setAttribute("medicaoHistoricoAnoMesAtual", medicaoHistoricoAnoMesAtual);
								if (imoveisMicromedicaoCarregamento != null){
									imovelMicromedicaoConsumo = (ImovelMicromedicao)imoveisMicromedicaoCarregamento.iterator().next();

									if(imovelMicromedicaoConsumo.getQtdDias() != null){
										leituraConsumoActionForm.setDiasConsumo(imovelMicromedicaoConsumo.getQtdDias());
									}
									
									//fazer a parte de montagem do form aki e tirar os value no jsp
									if(imovelMicromedicaoCarregaMedicaoResumo.getMedicaoHistorico().getDataLeituraAnteriorFaturamento() != null){
										leituraConsumoActionForm.setDataLeituraAnteriorFaturamento(Util.formatarData(imovelMicromedicaoCarregaMedicaoResumo.getMedicaoHistorico().getDataLeituraAnteriorFaturamento()));
									}else{
										leituraConsumoActionForm.setDataLeituraAnteriorFaturamento("");
									}
									if(imovelMicromedicaoCarregaMedicaoResumo.getMedicaoHistorico().getDataLeituraAtualInformada() != null){
										leituraConsumoActionForm.setDataLeituraAtualInformada(Util.formatarData(imovelMicromedicaoCarregaMedicaoResumo.getMedicaoHistorico().getDataLeituraAtualInformada()));
									}else{
										leituraConsumoActionForm.setDataLeituraAtualInformada("");
									}
									if(imovelMicromedicaoCarregaMedicaoResumo.getMedicaoHistorico().getConsumoMedioHidrometro() != null){
										leituraConsumoActionForm.setConsumoMedioHidrometro(imovelMicromedicaoCarregaMedicaoResumo.getMedicaoHistorico().getConsumoMedioHidrometro()+"");
									}else{
										leituraConsumoActionForm.setConsumoMedioHidrometro("");
									}
									
									if(medicaoHistoricoAnoMesAtual.getDataLeituraAtualFaturamento() != null){
										leituraConsumoActionForm.setDataLeituraAtualFaturamento(Util.formatarData(medicaoHistoricoAnoMesAtual.getDataLeituraAtualFaturamento()));
									}else{
										leituraConsumoActionForm.setDataLeituraAtualFaturamento("");
									}
									
									leituraConsumoActionForm
											.setLeituraAnteriorFaturamento(imovelMicromedicaoCarregaMedicaoResumo
													.getMedicaoHistorico()
													.getLeituraAnteriorFaturamento()
													+ "");
									if(medicaoHistoricoAnoMesAtual.getLeituraAtualInformada() != null){
										leituraConsumoActionForm.setLeituraAtualInformada(medicaoHistoricoAnoMesAtual.getLeituraAtualInformada() + "");
									}else{
										leituraConsumoActionForm.setLeituraAtualInformada("");
									}
									
									leituraConsumoActionForm.setLeituraAtualFaturada(medicaoHistoricoAnoMesAtual.getLeituraAtualFaturamento() + "");

									if(medicaoHistoricoAnoMesAtual.getLeituraSituacaoAtual() != null){
										leituraConsumoActionForm.setLeituraSituacaoAtual(medicaoHistoricoAnoMesAtual.getLeituraSituacaoAtual().getDescricao());
									}
//									value="${medicaoHistoricoAnoMesAtual.funcionario.id}"
									if(medicaoHistoricoAnoMesAtual.getFuncionario() != null){
										leituraConsumoActionForm.setIdFuncionario(medicaoHistoricoAnoMesAtual.getFuncionario().getId().toString());
									}
									
									if(imovelMicromedicaoCarregaMedicaoResumo.getMedicaoHistorico().getNumeroConsumoInformado() != null){
										leituraConsumoActionForm.setConsumoInformado(imovelMicromedicaoCarregaMedicaoResumo.getMedicaoHistorico().getNumeroConsumoInformado().toString());
									} else {
										leituraConsumoActionForm.setConsumoInformado("");
										
									}
									
									if (medicaoHistoricoConsumo.getNumeroConsumoMes() != null) {
										leituraConsumoActionForm.setMedido(medicaoHistoricoConsumo.getNumeroConsumoMes()+"");
									} else {
										leituraConsumoActionForm.setMedido("");
									}
									
									if(imovelMicromedicaoCarregaMedicaoResumo.getMedicaoHistorico() != null
										&& imovelMicromedicaoCarregaMedicaoResumo.getMedicaoHistorico().getLeituraAnormalidadeFaturamento() != null
										&& imovelMicromedicaoCarregaMedicaoResumo.getMedicaoHistorico().getLeituraAnormalidadeFaturamento().getId() != null){
										FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
										filtroLeituraAnormalidade
												.adicionarParametro(new ParametroSimples(
														FiltroLeituraAnormalidade.ID,
														imovelMicromedicaoCarregaMedicaoResumo.getMedicaoHistorico().getLeituraAnormalidadeFaturamento().getId()));

										Collection anormalidadeLeituraEncontrada = fachada
												.pesquisar(filtroLeituraAnormalidade,
														LeituraAnormalidade.class.getName());

										if (anormalidadeLeituraEncontrada != null
												&& !anormalidadeLeituraEncontrada.isEmpty()) {

											LeituraAnormalidade leituraAnormalidade = (LeituraAnormalidade) Util.retonarObjetoDeColecao(anormalidadeLeituraEncontrada);
											imovelMicromedicaoCarregaMedicaoResumo.getMedicaoHistorico().setLeituraAnormalidadeFaturamento(leituraAnormalidade);
											
											leituraConsumoActionForm
													.setIdAnormalidade(""
															+ leituraAnormalidade.getId());
											leituraConsumoActionForm
													.setDescricaoAnormalidade(leituraAnormalidade.getDescricao());
											leituraConsumoActionForm.
													setIndicadorLeitura("" + leituraAnormalidade.getIndicadorLeitura());
										}
									}

									leituraConsumoActionForm.setConfirmacao(httpServletRequest.getParameter("confirmacao"));
									
									sessao.setAttribute("imovelMicromedicaoConsumo", imovelMicromedicaoConsumo);
									//% Var.Consumo
									if (imovelMicromedicaoConsumo.getConsumoHistorico().getNumeroConsumoFaturadoMes() != null
											&& imovelMicromedicaoConsumo.getConsumoHistorico().getNumeroConsumoFaturadoMes() != 0
											&& imovelMicromedicaoConsumo.getConsumoHistorico().getConsumoMedio() != null
											&& imovelMicromedicaoConsumo.getConsumoHistorico().getConsumoMedio() != 0) {
										int operacaoSubMult = (imovelMicromedicaoConsumo.getConsumoHistorico().getNumeroConsumoFaturadoMes() - imovelMicromedicaoConsumo.getConsumoHistorico().getConsumoMedio()) * 100;
										BigDecimal percentual = new BigDecimal(operacaoSubMult)
												.divide(new BigDecimal(imovelMicromedicaoConsumo.getConsumoHistorico().getConsumoMedio()), 2,
														BigDecimal.ROUND_HALF_UP);
										String valorPercentual = "" + percentual;
										leituraConsumoActionForm.setVarConsumo(valorPercentual.replace(".", ",") + "%");
//										sessao.setAttribute("varConsumo",valorPercentual.replace(".", ",") + "%");
	
									}
								}
								if(imovelMicromedicaoConsumo.getConsumoHistorico() != null
										&& imovelMicromedicaoConsumo.getConsumoHistorico().getConsumoAnormalidade() != null){
									leituraConsumoActionForm.setConsumoAnormalidadeAbreviada(imovelMicromedicaoConsumo.getConsumoHistorico().getConsumoAnormalidade().getDescricaoAbreviada());
									leituraConsumoActionForm.setAnormalidadeConsumo(imovelMicromedicaoConsumo.getConsumoHistorico().getConsumoAnormalidade().getDescricao());
								}else if(imovelMicromedicaoConsumo.getConsumoHistoricoEsgoto()!= null
										&& imovelMicromedicaoConsumo.getConsumoHistoricoEsgoto().getConsumoAnormalidade() != null){
									leituraConsumoActionForm.setAnormalidadeConsumo(imovelMicromedicaoConsumo.getConsumoHistoricoEsgoto().getConsumoAnormalidade().getDescricao());
								}
								iteratorMedicaoHistorico.remove();
							}
						}
					}
					
//					 Organizar a coleção de Conta
					if (colecaoImovelMicromedicao != null
							&& !colecaoImovelMicromedicao.isEmpty()) {
						
						Collections.sort((List) colecaoImovelMicromedicao,
								new Comparator() {
									public int compare(Object a, Object b) {
										
										int retorno = 0;
										Integer anoMesReferencia1 = ((ImovelMicromedicao) a).getMedicaoHistorico()
												.getAnoMesReferencia();
										Integer anoMesReferencia2 = ((ImovelMicromedicao) b).getMedicaoHistorico()
												.getAnoMesReferencia();

										if(anoMesReferencia1.compareTo(anoMesReferencia2) == 1){
											retorno = -1;
										}else if(anoMesReferencia1.compareTo(anoMesReferencia2) == -1){
											retorno = 1;
										}
										
										return retorno;

									}
							});
					}
					
					
	//				 --- fim variavel
									
					// coloca a colecao de medicao historico no request
					sessao.setAttribute("medicoesHistoricos", colecaoMedicaoHistorico);
					// coloca colecao de consumo historico no request
					sessao.setAttribute("imoveisMicromedicao", colecaoImovelMicromedicao);
				} 
				
				if ( httpServletRequest.getParameter("solicitarReleitura") != null ){
					fachada.solicitarReleitura( codigoImovel,  usuarioLogado );
					
					// Caso tudo tenha ido ok, informamos ao usuário
					httpServletRequest.setAttribute(
							"mensagemReleitura", "Releitura do imóvel foi solicitada com sucesso.");
					
				}
				
				if ( fachada.releituraJaRealizada( codigoImovel ) ){
					httpServletRequest.setAttribute( "releituraJaRealizada", Boolean.TRUE );
				}
				
				if (verificarRotaFinalizada(imovelFiltro.getQuadra().getRota().getId(), mesAnoPesquisa)){
					httpServletRequest.setAttribute( "rotaFinalizada", Boolean.TRUE );
					httpServletRequest.setAttribute( "solicitarReleitura", Boolean.TRUE );
				}
				
				if (verificarReleitura(Util.formatarMesAnoComBarraParaAnoMes(mesAnoPesquisa), imovelFiltro.getId())){
					httpServletRequest.setAttribute( "solicitarReleitura", Boolean.TRUE );
				}
				
				
			}
			
			
			
			sessao.setAttribute("leituraConsumoActionForm",
					leituraConsumoActionForm);
			if (index == 0 && numeroPaginasPesquisa == 0) {
				sessao.setAttribute("desabilitaBotaoAnterior", 1);
			}else{
				sessao.removeAttribute("desabilitaBotaoAnterior");
			}
			if (index >= (colecaoIdsImovel.size() - 1)) {
				sessao.setAttribute("desabilitaBotaoProximo", 1);
			}else{
				sessao.removeAttribute("desabilitaBotaoProximo");
			}
			
			/**
			 * TODO : COSANPA
			 * Alteração feita para permitir que os imóveis que estão em rotas do IS
			 * possam ser alterados na tela de análise de exceções.
			 */
			Integer anoMes = Util.formatarMesAnoComBarraParaAnoMes(mesAnoPesquisa);
			
			Conta conta = this.getFachada().obterContaImovel(imovelAtual.getId(), anoMes);
			
			MovimentoContaPrefaturada movimentoConta = this.getFachada().obterMovimentoImovel(imovelAtual.getId(), anoMes);
			
			MedicaoHistorico medicaoHistorico = this.getFachada().pesquisarMedicaoHistorico(imovelAtual.getId(), anoMes, new Integer(idMedicaoTipo));
			
			if (imovelAtual.getQuadra().getRota().getLeituraTipo().getId().intValue() != LeituraTipo.LEITURA_E_ENTRADA_SIMULTANEA.intValue()) {
				if (!isFaturamentoGrupoAberto(Util.formatarMesAnoComBarraParaAnoMes(mesAnoPesquisa), faturamentoGrupo)) {
					verificarAbrangenciaUsuario(httpServletRequest, usuarioLogado, imovelAtual);
					desabilitaAtualizarImovel = true;
				}
			}
			else if(imovelAtual.getQuadra().getRota().getLeituraTipo().getId().intValue() == LeituraTipo.LEITURA_E_ENTRADA_SIMULTANEA.intValue()
					&& imovelPerfil.getIndicadorGerarDadosLeitura().intValue() == ConstantesSistema.INDICADOR_USO_ATIVO.intValue()) {
				if (isFaturamentoGrupoAberto(Util.formatarMesAnoComBarraParaAnoMes(mesAnoPesquisa), faturamentoGrupo)
						&& medicaoHistorico != null && medicaoHistorico.getId() != null 
						&& conta != null && conta.getId() != null
						&& conta.getDebitoCreditoSituacaoAtual().getId().equals(DebitoCreditoSituacao.PRE_FATURADA)
						&& ((movimentoConta != null && movimentoConta.getId() != null
						&& movimentoConta.getIndicadorEmissaoConta().shortValue() == ConstantesSistema.NAO.shortValue()
						&& movimentoConta.getIndicadorGeracaoConta().shortValue() == ConstantesSistema.NAO.shortValue())
							|| movimentoConta == null)){
					desabilitaAtualizarImovel = false;
				} else {
					httpServletRequest.setAttribute("habilitaCampos", false);
					desabilitaAtualizarImovel = true;
				}
			}
			
			httpServletRequest.setAttribute("desabilitaAtualizarImovel",desabilitaAtualizarImovel);
			
		}
		
		/**
		 * Alteracao Feita por Tiago Moreno 19/02/2010
		 */
		
		FiltroMotivoInterferenciaTipo filtroMotivoInterferenciaTipo = new FiltroMotivoInterferenciaTipo();
		filtroMotivoInterferenciaTipo.adicionarParametro(new ParametroSimples(
				FiltroMotivoInterferenciaTipo.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection colecaoMotivoInterferenciaTipo = fachada.pesquisar(
				filtroMotivoInterferenciaTipo, MotivoInterferenciaTipo.class.getName());
		
		if (colecaoMotivoInterferenciaTipo!= null && !colecaoMotivoInterferenciaTipo.isEmpty()){
			sessao.setAttribute("colecaoMotivoInterferenciaTipo", colecaoMotivoInterferenciaTipo);
		}
		
		
		
		return retorno;
	}

	private boolean isAnormalidadeConsumoPermitidaHabilitarAlteracao(Integer idAnormalidadeConsumo) {
		
		if (idAnormalidadeConsumo != null &&
				(idAnormalidadeConsumo.equals(ConsumoAnormalidade.ESTOURO_CONSUMO_COBRANCA_MEDIA)
				|| idAnormalidadeConsumo.equals(ConsumoAnormalidade.ESTOURO_CONSUMO)
				|| idAnormalidadeConsumo.equals(ConsumoAnormalidade.ALTO_CONSUMO)
				|| idAnormalidadeConsumo.equals(ConsumoAnormalidade.BAIXO_CONSUMO))) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isFaturamentoGrupoAberto(Integer anoMesConsulta, FaturamentoGrupo grupoFaturamento) {
		if (anoMesConsulta >= grupoFaturamento.getAnoMesReferencia())
			return true;
		else
			return false;
	}
	
	private void verificarAbrangenciaUsuario(HttpServletRequest httpServletRequest, Usuario usuarioLogado, Imovel imovelAtual) {
		
		Fachada fachada = Fachada.getInstancia();
		
		if (usuarioLogado.getUsuarioAbrangencia().getId().equals(UsuarioAbrangencia.LOCALIDADE)) {
			
			if (!usuarioLogado.getLocalidade().getId().equals(imovelAtual.getLocalidade().getId())) {
				httpServletRequest.setAttribute("habilitaCampos", true);
			}
			
		} else if (usuarioLogado.getUsuarioAbrangencia().getId().equals(UsuarioAbrangencia.GERENCIA_REGIONAL)) {
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, imovelAtual.getLocalidade().getId()));
			
			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			
			if (!usuarioLogado.getGerenciaRegional().getId().equals(localidade.getGerenciaRegional().getId())) {
				httpServletRequest.setAttribute("habilitaCampos", true);
			}
			
		} else if (usuarioLogado.getUsuarioAbrangencia().getId().equals(UsuarioAbrangencia.UNIDADE_NEGOCIO)) {
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, imovelAtual.getLocalidade().getId()));
			
			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			
			if (!usuarioLogado.getUnidadeNegocio().getId().equals(localidade.getUnidadeNegocio().getId())) {
				httpServletRequest.setAttribute("habilitaCampos", true);
			}
			
		} else if (usuarioLogado.getUsuarioAbrangencia().getId().equals(UsuarioAbrangencia.ELO_POLO)) {
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, imovelAtual.getLocalidade().getId()));
			
			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			
			if (!usuarioLogado.getLocalidadeElo().getId().equals(localidade.getLocalidade().getId())) {
				httpServletRequest.setAttribute("habilitaCampos", true);
			}
			
		}
	}

	/**
	 * Adiciona o imóvel aos coleções selecionadas pelo usuário
	 *
	 * @author Rafael Corrêa
	 * @date 30/05/2008
	 *
	 * @param httpServletRequest
	 * @param sessao
	 * @param imovel
	 */
	@SuppressWarnings("unchecked")
	private void adicionarObjetosSelecionadosColecoes(HttpServletRequest httpServletRequest, HttpSession sessao, Imovel imovel, String observacao) {
		String gerarAviso = httpServletRequest.getParameter("gerarAviso");
		String gerarOS = httpServletRequest.getParameter("gerarOS");
		String gerarRelatorio = httpServletRequest.getParameter("gerarRelatorio");
		Collection colecaoImoveisGerarAviso = (Collection) sessao.getAttribute("colecaoImoveisGerarAviso");
		Collection colecaoImoveisGerarOS = (Collection) sessao.getAttribute("colecaoImoveisGerarOS");
		Collection colecaoImoveisGerarRelatorio = (Collection) sessao.getAttribute("colecaoImoveisGerarRelatorio");
		HashMap<Integer, String> colecaoObservacaoOS = (HashMap<Integer, String>) sessao.getAttribute("colecaoObservacaoOS");
		
		// Verifica se o usuário selecionou este imóvel para geração dos avisos
		if (gerarAviso != null && !gerarAviso.trim().equals("")) {
			
			if (colecaoImoveisGerarAviso == null) {
				colecaoImoveisGerarAviso = new ArrayList();
			}
			
			if (!colecaoImoveisGerarAviso.contains(imovel.getId())) {
				colecaoImoveisGerarAviso.add(imovel.getId());
			} else {
				sessao.setAttribute("gerarAviso", "1");
			}

			sessao.setAttribute("colecaoImoveisGerarAviso", colecaoImoveisGerarAviso);
		} 
		// Caso não esteja selecionado, verifica se ele estava na coleção e remove-o
		else {
			if (colecaoImoveisGerarAviso != null && !colecaoImoveisGerarAviso.isEmpty()) {
				if (colecaoImoveisGerarAviso.contains(imovel.getId())) {
					colecaoImoveisGerarAviso.remove(imovel.getId());
					sessao.setAttribute("colecaoImoveisGerarAviso", colecaoImoveisGerarAviso);
				}
			}
		}

		// Verifica se o usuário selecionou este imóvel para geração de OS
		if (gerarOS != null && !gerarOS.trim().equals("")) {
			
			if (colecaoImoveisGerarOS == null) {
				colecaoImoveisGerarOS = new ArrayList();
				colecaoObservacaoOS = new HashMap<Integer, String>();
			}
			
			if (!colecaoImoveisGerarOS.contains(imovel)) {
				colecaoImoveisGerarOS.add(imovel);
				colecaoObservacaoOS.put(imovel.getId(),observacao);
			}
			sessao.setAttribute("observacao", observacao);
			sessao.setAttribute("colecaoImoveisGerarOS", colecaoImoveisGerarOS);
			sessao.setAttribute("colecaoObservacaoOS", colecaoObservacaoOS);
		} 
		// Caso não esteja selecionado, verifica se ele estava na coleção e remove-o
		else {
			if (colecaoImoveisGerarOS != null && !colecaoImoveisGerarOS.isEmpty()) {
				if (colecaoImoveisGerarOS.contains(imovel)) {
					colecaoImoveisGerarOS.remove(imovel);
					colecaoObservacaoOS.remove(imovel.getId());
					sessao.setAttribute("colecaoImoveisGerarOS", colecaoImoveisGerarOS);
					sessao.setAttribute("colecaoObservacaoOS", colecaoObservacaoOS);
				}
			}
		}
		
		// Verifica se o usuário selecionou este imóvel para geração do relatório
		if (gerarRelatorio != null && !gerarRelatorio.trim().equals("")) {
			
			if (colecaoImoveisGerarRelatorio == null) {
				colecaoImoveisGerarRelatorio = new ArrayList();
			}
			
			if (!colecaoImoveisGerarRelatorio.contains(imovel.getId())) {
				colecaoImoveisGerarRelatorio.add(imovel.getId());
			} 

			sessao.setAttribute("colecaoImoveisGerarRelatorio", colecaoImoveisGerarRelatorio);
		} 
		// Caso não esteja selecionado, verifica se ele estava na coleção e remove-o
		else {
			if (colecaoImoveisGerarRelatorio != null && !colecaoImoveisGerarRelatorio.isEmpty()) {
				if (colecaoImoveisGerarRelatorio.contains(imovel.getId())) {
					colecaoImoveisGerarRelatorio.remove(imovel.getId());
					sessao.setAttribute("colecaoImoveisGerarRelatorio", colecaoImoveisGerarRelatorio);
				}
			}
		}
	}
	
	/**
	 * Carrega o com de cálculo de consumo colocando o e tipo de consumo e o seu valor
	 *
	 * @author Rafael Corrêa
	 * @date 29/05/2008
	 *
	 * @param medicaoHistorico
	 * @param imovel
	 * @return
	 */
	private Collection<CalculoConsumoHelper> carregarComboCalculoConsumo(MedicaoHistorico medicaoHistorico, Imovel imovel, Integer anoMesReferencia) {

		Fachada fachada = Fachada.getInstancia();
		
		Collection<CalculoConsumoHelper> retorno = new ArrayList<CalculoConsumoHelper>();
		
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.CONSUMO_TARIFA);
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, imovel.getId()));
		
		Collection colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
		
		imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
		
		// Consumo Médio
		CalculoConsumoHelper calculoConsumoMedio = new CalculoConsumoHelper();
		
		calculoConsumoMedio.setDescricao("MEDIA");
		calculoConsumoMedio.setConsumo(medicaoHistorico.getConsumoMedioHidrometro());
		
		retorno.add(calculoConsumoMedio);
		
		// Consumo Mínimo
		CalculoConsumoHelper calculoConsumoMinimo = new CalculoConsumoHelper();
		
		Integer consumoMinimo = fachada.obterConsumoMinimoLigacao(imovel, null);
		
		calculoConsumoMinimo.setDescricao("MINIMO");
		calculoConsumoMinimo.setConsumo(consumoMinimo);
		
		retorno.add(calculoConsumoMinimo);
		
		// Consumo Não Medido
		CalculoConsumoHelper calculoConsumoNaoMedido = new CalculoConsumoHelper();
		
		Integer consumoNaoMedido = fachada.obterConsumoNaoMedidoSemTarifa(imovel.getId(), anoMesReferencia);
		
		calculoConsumoNaoMedido.setDescricao("N MED.");
		calculoConsumoNaoMedido.setConsumo(consumoNaoMedido);
		
		retorno.add(calculoConsumoNaoMedido);
		
		return retorno;
	}
	
	private boolean verificarRotaFinalizada(Integer idRota, String mesAnoPesquisa) {
		
		Fachada fachada = Fachada.getInstancia();
	
		Integer anoMesFaturamento = Util.formatarMesAnoComBarraParaAnoMes(mesAnoPesquisa);
        
		Object[] dadosArquivoTextoRoteiroEmpresa = 
			fachada.pesquisarArquivoTextoRoteiroEmpresa( idRota, anoMesFaturamento);
		
		if (dadosArquivoTextoRoteiroEmpresa != null){
			Integer idSituacaoTransmissaoLeitura = (Integer)dadosArquivoTextoRoteiroEmpresa[1];
			if( !idSituacaoTransmissaoLeitura.equals( SituacaoTransmissaoLeitura.DISPONIVEL ) &&
				 !idSituacaoTransmissaoLeitura.equals( SituacaoTransmissaoLeitura.LIBERADO ) &&
				 !idSituacaoTransmissaoLeitura.equals( SituacaoTransmissaoLeitura.EM_CAMPO ) && 
				 !idSituacaoTransmissaoLeitura.equals( SituacaoTransmissaoLeitura.FINALIZADO_NAO_TRANSMITIDO ) ){
				return true;
			} 
		}
		return false;
	}
	
	private boolean verificarReleitura(Integer anoMesFaturamento, Integer idImovel) {
		
		Fachada fachada = Fachada.getInstancia();
			
		FiltroReleituraMobile filtroReleituraMobile = new FiltroReleituraMobile();
		filtroReleituraMobile.adicionarParametro(new ParametroSimples(
				FiltroReleituraMobile.ANO_MES_FATURAMENTO, anoMesFaturamento));
		filtroReleituraMobile.adicionarParametro(new ParametroSimples(
				FiltroReleituraMobile.ID_IMOVEL, idImovel));
		
        
		Collection<ReleituraMobile> colecaoReleituraMobile = 
			fachada.pesquisar( filtroReleituraMobile, ReleituraMobile.class.getName());
		
		if (colecaoReleituraMobile != null && !colecaoReleituraMobile.isEmpty()){
			return true;
		}
		return false;
	}
}
