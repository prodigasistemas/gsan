
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
package gcom.relatorio.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.bean.OSPavimentoHelper;
import gcom.atendimentopublico.ordemservico.bean.OSPavimentoRetornoHelper;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Yara Taciane, Hugo Leonardo
 * @created 28/02/2008, 13/12/2010
 * @version 1.0
 */

public class RelatorioRelacaoServicosAcompanhamentoRepavimentacao extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the RelatorioManterNegativadorExclusaoMotivo object
	 */
	public RelatorioRelacaoServicosAcompanhamentoRepavimentacao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RELACAO_SERVICO_ACOMPANHAMENTO_REPAVIMENTACAO_NOVO);
	}

	@Deprecated
	public RelatorioRelacaoServicosAcompanhamentoRepavimentacao() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param NegativadorExclusaoMotivo Parametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		// Recebe os parâmetros que serão utilizados no relatório
		//FiltroNegativadorExclusaoMotivo  filtroNegativadorExclusaoMotivo  = (FiltroNegativadorExclusaoMotivo) getParametro("filtroNegativadorExclusaoMotivo");
		OSPavimentoHelper oSPavimentoHelperParametros = (OSPavimentoHelper) getParametro("parametros");
		String escolhaRelatorio = (String) getParametro("escolhaRelatorio");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String indicadorOsObservacaoRetorno = (String) getParametro("indicadorOsObservacaoRetorno");
		
		if(indicadorOsObservacaoRetorno != null && 
			new Short(indicadorOsObservacaoRetorno).shortValue() == ConstantesSistema.SIM.shortValue()){
			
			oSPavimentoHelperParametros.setIndicadorOsObservacaoRetorno(ConstantesSistema.SIM);
		}else{
			oSPavimentoHelperParametros.setIndicadorOsObservacaoRetorno(ConstantesSistema.NAO);
		}
		
		
		// Flag's
		Boolean achouPrimeiraOcorrenciaPendente = false;
		Boolean indicadorPrimeiraOcorrenciaPendente = false;
		
		Boolean indicadorUltimaOcorrenciaRejeitada = false;
		
		Boolean achouPrimeiraOcorrenciaConcluida = false;
		Boolean indicadorPrimeiraOcorrenciaConcluida = false;
		
		Boolean achouPrimeiraOcorrenciaRejeitada = false;
		Boolean indicadorPrimeiraOcorrenciaRejeitada = false;
			
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório

		ArrayList relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean relatorioBean = null;
		
		Collection<OSPavimentoRetornoHelper> collOrdemServicoPavimentoHelper = fachada.
				pesquisarRelatorioOrdemProcessoRepavimentacao(oSPavimentoHelperParametros, escolhaRelatorio);
	

		if (collOrdemServicoPavimentoHelper != null && !collOrdemServicoPavimentoHelper.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			for (OSPavimentoRetornoHelper oSPavimentoRetornoHelper : collOrdemServicoPavimentoHelper) {
				
				// Faz as validações dos campos necessáriose e formata a String
				// para a forma como irá aparecer no relatório
				
				// Situação Retorno
				String situacaoRetorno = "";
				
				// numeroOS
				String numeroOS = "";				
				if (oSPavimentoRetornoHelper.getOrdemServico() != null) {
					numeroOS = oSPavimentoRetornoHelper.getOrdemServico().getId().toString();
				}
				
				// matricula do imóvel
				String matricula = "";				
				if (oSPavimentoRetornoHelper.getOrdemServico()!= null && oSPavimentoRetornoHelper.getOrdemServico().getImovel() != null) {
					matricula = oSPavimentoRetornoHelper.getOrdemServico().getImovel().getId().toString();
				}
				
				// endereco
				String endereco = "";				
				if (oSPavimentoRetornoHelper.getEndereco() != null) {
					endereco = oSPavimentoRetornoHelper.getEndereco();
				}
									
				 // data encerramento da OS
				String dataEncerramento = "";				
				if (oSPavimentoRetornoHelper.getOrdemServico() != null && oSPavimentoRetornoHelper.getOrdemServico().getDataEncerramento()!= null) {
					dataEncerramento = Util.formatarData(oSPavimentoRetornoHelper.getOrdemServico().getDataEncerramento());
				}
				
				
				// tipo pvto rua
				String tipoPvtoRua = "";				
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua()!= null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getDescricaoAbreviada()!= null) {
					tipoPvtoRua = oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getId() + "-" +
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getDescricaoAbreviada();
				}
				
				// Metr. (m²) indicada.
				BigDecimal metragem = null;				
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null && oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRua()!= null) {
					metragem =oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRua();
				}
				
				//...............................................................................................
				// data da conclusão
				String dataConclusao = "";				
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento()!= null && oSPavimentoRetornoHelper.getOrdemServicoPavimento().getDataExecucao()!= null) {
					dataConclusao = Util.formatarData(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getDataExecucao());
				}
				//...............................................................................................
								
				//tipo pvto rua retorno.
				String tipoPvtoRuaRetorno = "";
				
				// flag usada para agrupar as ordens de serviços entre: Pendentes e Concluídas	
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno()!= null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getDescricaoAbreviada()!= null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getMotivoRejeicao() == null ) {
					
					tipoPvtoRuaRetorno = oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getId() + "-" +
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getDescricaoAbreviada();
				
					situacaoRetorno = "2"; // CONCLUIDAS
					
					if(indicadorPrimeiraOcorrenciaConcluida == false && achouPrimeiraOcorrenciaConcluida == false){
						indicadorPrimeiraOcorrenciaConcluida = true;
						achouPrimeiraOcorrenciaConcluida = true;
						
					}else{
						indicadorPrimeiraOcorrenciaConcluida = false;
					}
				}else if(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getMotivoRejeicao() == null){
					
					situacaoRetorno = "1"; // PENDENTES
					
					if(indicadorPrimeiraOcorrenciaPendente == false && achouPrimeiraOcorrenciaPendente == false){
						indicadorPrimeiraOcorrenciaPendente = true;
						achouPrimeiraOcorrenciaPendente = true;
						
					}else{
						indicadorPrimeiraOcorrenciaPendente = false;
					}
				}
				
				// Metr. (m²) indicada retorno.
				BigDecimal metragemRetorno = null;				
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null && oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRuaRetorno()!= null) {
					metragemRetorno = oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRuaRetorno();
				}
				
				
				// Motivo Rejeição
				String motivoRejeicao = "";
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento().getMotivoRejeicao() != null){
					
					situacaoRetorno = "4"; // REJEITADAS
					motivoRejeicao = oSPavimentoRetornoHelper.getOrdemServicoPavimento().getMotivoRejeicao().getDescricao();
					
					if(indicadorPrimeiraOcorrenciaRejeitada == false && achouPrimeiraOcorrenciaRejeitada == false){
						indicadorPrimeiraOcorrenciaRejeitada = true;
						achouPrimeiraOcorrenciaRejeitada = true;
						
					}else{
						indicadorPrimeiraOcorrenciaRejeitada = false;
					}
				}else{
					
					if(indicadorPrimeiraOcorrenciaRejeitada == true && indicadorUltimaOcorrenciaRejeitada == false ){
						indicadorUltimaOcorrenciaRejeitada = true;
					
					}else{
						indicadorUltimaOcorrenciaRejeitada = false;
					}
				}
				
				String observacao = "";
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento().getObservacao() != null){
					observacao = oSPavimentoRetornoHelper.getOrdemServicoPavimento().getObservacao();
				}
				
				// data da rejeição
				String dataRejeicao = "";				
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento()!= null && oSPavimentoRetornoHelper.getOrdemServicoPavimento().getDataRejeicao()!= null) {
					dataRejeicao = Util.formatarData(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getDataRejeicao());
				}
				
				//Verifica o tipo de relatorio a ser gerado é COMPLETO
				if (escolhaRelatorio != null && escolhaRelatorio.equals("1")){
					
					//Inicializa o construtor constituído dos campos
					// necessários para a impressão do relatorio
					relatorioBean = 
						new RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean(
							numeroOS,
							matricula,
							endereco,
							dataEncerramento,
							tipoPvtoRua,
							metragem,
							dataConclusao,
							tipoPvtoRuaRetorno,
							metragemRetorno,
							oSPavimentoRetornoHelper.getCollOSTipoPavimentoHelper_Rua(),
							oSPavimentoRetornoHelper.getCollOSTipoPavimentoHelper_RuaRet()
					);
					
					relatorioBean.setDataRejeicao(dataRejeicao);
					relatorioBean.setSituacaoRetorno(situacaoRetorno);
					relatorioBean.setIndicadorPrimeiraOcorrenciaConcluida(indicadorPrimeiraOcorrenciaConcluida);
					relatorioBean.setIndicadorPrimeiraOcorrenciaPendente(indicadorPrimeiraOcorrenciaPendente);
					relatorioBean.setIndicadorPrimeiraOcorrenciaRejeitada(indicadorPrimeiraOcorrenciaRejeitada);
					relatorioBean.setIndicadorUltimaOcorrenciaRejeitada(indicadorUltimaOcorrenciaRejeitada);
					relatorioBean.setMotivoRejeicao(motivoRejeicao);
					relatorioBean.setObservacao(observacao);
					
					
					//adiciona o bean a coleção
					relatorioBeans.add(relatorioBean);
					
				//Verifica se o Tipo do relatorio a ser gerado é DIVERGENTE
				} else if (escolhaRelatorio != null && escolhaRelatorio.equals("2") ) {
					Integer validacaoAceite = calculaPercentualMetragemEValidaRetorno( metragem, metragemRetorno ) ;
					//Validar se oSPavimentoRetorno é divergente, pois esse relatorio é divergente! 
					if ( ( tipoPvtoRuaRetorno != null && !tipoPvtoRuaRetorno.equals("") && !tipoPvtoRuaRetorno.equals(tipoPvtoRua) ) ||
							 ( validacaoAceite != null && validacaoAceite.toString().equals("2") ) ) {
						
						//Inicializa o construtor constituído dos campos
						// necessários para a impressão do relatorio
						relatorioBean = 
							new RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean(
								numeroOS,
								matricula,
								endereco,
								dataEncerramento,
								tipoPvtoRua,
								metragem,
								dataConclusao,
								tipoPvtoRuaRetorno,
								metragemRetorno,
								oSPavimentoRetornoHelper.getCollOSTipoPavimentoHelper_Rua(),
								oSPavimentoRetornoHelper.getCollOSTipoPavimentoHelper_RuaRet()
					   );
						
						relatorioBean.setDataRejeicao(dataRejeicao);
						relatorioBean.setSituacaoRetorno(situacaoRetorno);
						relatorioBean.setIndicadorPrimeiraOcorrenciaConcluida(indicadorPrimeiraOcorrenciaConcluida);
						relatorioBean.setIndicadorPrimeiraOcorrenciaPendente(indicadorPrimeiraOcorrenciaPendente);
						relatorioBean.setIndicadorPrimeiraOcorrenciaRejeitada(indicadorPrimeiraOcorrenciaRejeitada);
						relatorioBean.setIndicadorUltimaOcorrenciaRejeitada(indicadorUltimaOcorrenciaRejeitada);
						relatorioBean.setMotivoRejeicao(motivoRejeicao);
						relatorioBean.setObservacao(observacao);
						
						//adiciona o bean a coleção
						relatorioBeans.add(relatorioBean);
					}
				// Caso o Tipo do relatório seja CONVERGENTE
				} else  {
					Integer validacaoAceite = calculaPercentualMetragemEValidaRetorno( metragem, metragemRetorno ) ;
					//Validar se oSPavimentoRetorno é divergente, pois esse relatorio é divergente! 
					if ( ( tipoPvtoRuaRetorno != null && !tipoPvtoRuaRetorno.equals("") && tipoPvtoRuaRetorno.equals(tipoPvtoRua) ) ||
							( validacaoAceite != null && validacaoAceite.toString().equals("1"))) {
						
						//Inicializa o construtor constituído dos campos
						// necessários para a impressão do relatorio
						relatorioBean = new RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean(
								numeroOS,
								matricula,
								endereco,
								dataEncerramento,
								tipoPvtoRua,
								metragem,
								dataConclusao,
								tipoPvtoRuaRetorno,
								metragemRetorno,
								oSPavimentoRetornoHelper.getCollOSTipoPavimentoHelper_Rua(),
								oSPavimentoRetornoHelper.getCollOSTipoPavimentoHelper_RuaRet()
						);
					
						relatorioBean.setDataRejeicao(dataRejeicao);
						relatorioBean.setSituacaoRetorno(situacaoRetorno);
						relatorioBean.setIndicadorPrimeiraOcorrenciaConcluida(indicadorPrimeiraOcorrenciaConcluida);
						relatorioBean.setIndicadorPrimeiraOcorrenciaPendente(indicadorPrimeiraOcorrenciaPendente);
						relatorioBean.setIndicadorPrimeiraOcorrenciaRejeitada(indicadorPrimeiraOcorrenciaRejeitada);
						relatorioBean.setIndicadorUltimaOcorrenciaRejeitada(indicadorUltimaOcorrenciaRejeitada);
						relatorioBean.setMotivoRejeicao(motivoRejeicao);
						relatorioBean.setObservacao(observacao);
						
						//adiciona o bean a coleção
						relatorioBeans.add(relatorioBean);
					}
				}
			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		UnidadeOrganizacional unidadeOrganizacional = null;
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
	    filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID,oSPavimentoHelperParametros.getIdUnidadeResponsavel()));		    
	    filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeSuperior");	
	    
		Collection colecaoUnidadeOrganizacional = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
		Iterator it = colecaoUnidadeOrganizacional.iterator();
		while(it.hasNext()){
			unidadeOrganizacional = (UnidadeOrganizacional)it.next();
		}		
		if(unidadeOrganizacional != null){
			parametros.put("unidadeResponsavel", unidadeOrganizacional.getDescricao());				

		}
		
		if(unidadeOrganizacional != null && unidadeOrganizacional.getUnidadeSuperior() != null){
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacionalSup = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacionalSup.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID,unidadeOrganizacional.getUnidadeSuperior().getId()));		 
			filtroUnidadeOrganizacionalSup.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
			UnidadeOrganizacional unidadeOrganizacionalSup = null;
			Collection colecaoUnidadeOrganizacionalSup = fachada.pesquisar(filtroUnidadeOrganizacionalSup, UnidadeOrganizacional.class.getName());
			Iterator itt = colecaoUnidadeOrganizacionalSup.iterator();
			while(itt.hasNext()){
				unidadeOrganizacionalSup = (UnidadeOrganizacional)itt.next();
			}
			
			if(unidadeOrganizacionalSup!= null && unidadeOrganizacionalSup.getGerenciaRegional()!= null){
				parametros.put("gerenciaRegional", unidadeOrganizacionalSup.getGerenciaRegional().getId() + "-" + unidadeOrganizacionalSup.getGerenciaRegional().getNomeAbreviado());	
				
			}
		}
		
		String situacao = "";
		if(oSPavimentoHelperParametros.getSituacaoRetorno() == 1){
			situacao = "PENDENTE";
		}else if(oSPavimentoHelperParametros.getSituacaoRetorno() == 2){
			situacao = "CONCLUÍDA";
		
		}else if(oSPavimentoHelperParametros.getSituacaoRetorno() == 4){
			situacao = "REJEITADAS";
		// 3
		}else{
			situacao = "TODAS";
		}		
		parametros.put("situacao", situacao);
		
		parametros.put("escolhaRelatorio", escolhaRelatorio);
		
		String periodoEncerramentoOs = "";
		if(oSPavimentoHelperParametros.getPeriodoEncerramentoOsInicial()!= null && oSPavimentoHelperParametros.getPeriodoEncerramentoOsFinal()!= null){
			periodoEncerramentoOs = oSPavimentoHelperParametros.getPeriodoEncerramentoOsInicial() + "-" +
			oSPavimentoHelperParametros.getPeriodoEncerramentoOsFinal();
			parametros.put("periodoEncerramentoOs", periodoEncerramentoOs) ;
		}
		
		String periodoRetornoServico = "";
		if(oSPavimentoHelperParametros.getPeriodoRetornoServicoInicial()!=null && oSPavimentoHelperParametros.getPeriodoRetornoServicoFinal()!= null ){
			periodoRetornoServico = oSPavimentoHelperParametros.getPeriodoRetornoServicoInicial() + "-" +
			oSPavimentoHelperParametros.getPeriodoRetornoServicoFinal();
			parametros.put("periodoRetornoServico", periodoRetornoServico);
		}
		
		String periodoRejeicaoServico = "";
		if(oSPavimentoHelperParametros.getPeriodoRejeicaoInicial()!=null && oSPavimentoHelperParametros.getPeriodoRejeicaoFinal()!= null ){
			periodoRejeicaoServico = oSPavimentoHelperParametros.getPeriodoRejeicaoInicial() + "-" +
			oSPavimentoHelperParametros.getPeriodoRejeicaoFinal();
			parametros.put("periodoRejeicaoServico", periodoRejeicaoServico);
		}
		
		// teste
		ArrayList colecaoBeans = (ArrayList) relatorioBeans;
		
		//colecaoBeans.ge
		Iterator itBean = colecaoBeans.iterator();
		
		int indice = 0;
		int ultimoIndice = colecaoBeans.size();
		
		while(itBean.hasNext()){
			relatorioBean = (RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean)itBean.next();
			
			if(relatorioBean.getIndicadorUltimaOcorrenciaRejeitada() == true){
				
				relatorioBean.setIndicadorUltimaOcorrenciaRejeitada(false);
				
				if(indice > 0){
					relatorioBean = (RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean)colecaoBeans.get(indice-1);
					
					relatorioBean.setIndicadorUltimaOcorrenciaRejeitada(true);
				}
				
				
				break;
			}
			
			indice = indice + 1;
		}
		
		if(indice > 0 && indice == ultimoIndice){
			relatorioBean = (RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean)colecaoBeans.get(indice-1);
			
			relatorioBean.setIndicadorUltimaOcorrenciaRejeitada(true);
		}
		
		// classifica a lista por situação de retorno
		Collections.sort((List) colecaoBeans,
				new Comparator() {
					public int compare(Object a, Object b) {
						String codigo1 = ((RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean) a)
								.getSituacaoRetorno();
						String codigo2 = ((RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean) b)
							.getSituacaoRetorno();
						if (codigo1 == null || codigo1.equals("")) {
							return -1;
						} else {
							return codigo1.compareTo(codigo2);
						}
					}
				});
		
		

		// classifica a lista por situação de retorno
		Collections.sort((List) colecaoBeans,
				new Comparator() {
					public int compare(Object a, Object b) {
						String codigo1 = ((RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean) a)
								.getSituacaoRetorno();
						String codigo2 = ((RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean) b)
							.getSituacaoRetorno();
						if (codigo1 == null || codigo1.equals("")) {
							return -1;
						} else {
							return codigo1.compareTo(codigo2);
						}
					}
				});
		
		while(itBean.hasNext()){
			relatorioBean = (RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean)itBean.next();
			
			if(relatorioBean.getIndicadorUltimaOcorrenciaRejeitada() == true){
				
				relatorioBean.setIndicadorUltimaOcorrenciaRejeitada(false);
				
				if(indice > 0){
					relatorioBean = (RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean)colecaoBeans.get(indice-1);
					
					relatorioBean.setIndicadorUltimaOcorrenciaRejeitada(true);
				}
				
				
				break;
			}
			
			indice = indice + 1;
		}
		
		if(indice > 0 && indice == ultimoIndice){
			relatorioBean = (RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean)colecaoBeans.get(indice-1);
			
			relatorioBean.setIndicadorUltimaOcorrenciaRejeitada(true);
		}

		// cria uma instância do dataSource do relatório
		if ( relatorioBeans.size() > 0) {
			RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);
			
			retorno = this.gerarRelatorio(
					ConstantesRelatorios.RELATORIO_RELACAO_SERVICO_ACOMPANHAMENTO_REPAVIMENTACAO_NOVO, parametros, ds,
					tipoFormatoRelatorio);
		} else {

			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			
		}
		

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_RELACAO_SERVICO_ACOMPANHAMENTO_REPAVIMENTACAO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 1;
		
		OSPavimentoHelper osPavimentoHelper = (OSPavimentoHelper) getParametro("parametros");
		retorno = Fachada.getInstancia().pesquisarOrdemProcessoRepavimentacaoCount(osPavimentoHelper);

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioRelacaoServicosAcompanhamentoRepavimentacao", this);
	}

	
	
	/**
	 *  [SB0003] - Imprimir relação das ordens
	 *  1.331
	 * Metodo responsavel por verificar se a metragem informada no retorno esta compreendida
	 * entre o percentual de variação permitido.
	 * 
	 * @author Arthur Carvalho
	 * @date 26/07/2010
	 * @param metragem
	 * @param metragemRetono
	 * @return
	 */
	public Integer calculaPercentualMetragemEValidaRetorno(BigDecimal metragem, BigDecimal metragemRetono ) {
		Integer indicadorAceiteComCalculoPercentualConvergencia = null;
		BigDecimal percentualConvergenciaRepavimentacao = new BigDecimal(0);
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		percentualConvergenciaRepavimentacao = sistemaParametro.getPercentualConvergenciaRepavimentacao();
		
		//1.3.3.    Total Ordens Aceitas 
		if ( percentualConvergenciaRepavimentacao != null ) { 
			 
			if ( metragem.add(metragem.multiply(percentualConvergenciaRepavimentacao).divide(
							new BigDecimal(100))).compareTo(metragemRetono) >= 0 &&
								metragem.subtract(metragem.multiply(
									percentualConvergenciaRepavimentacao).divide(
											new BigDecimal(100))).compareTo(metragemRetono) <= 0 ) {
				
				indicadorAceiteComCalculoPercentualConvergencia = 1;
			} else {
				indicadorAceiteComCalculoPercentualConvergencia = 2;
			}
		} 

		return indicadorAceiteComCalculoPercentualConvergencia;
	}
}
