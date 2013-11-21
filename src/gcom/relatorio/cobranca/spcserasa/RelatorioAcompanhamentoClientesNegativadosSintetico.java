
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
package gcom.relatorio.cobranca.spcserasa;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.batch.Relatorio;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorMovimentoReg;
import gcom.cobranca.bean.DadosConsultaNegativacaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.cobranca.spcserasa.RelatorioAcompanhamentoClientesNegativadosHelper;
import gcom.relatorio.ConstantesExecucaoRelatorios;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.FiltroNegativador;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** 
 * @author Ivan Sergio, Mariana Victor
 * @created 04/02/2011, 10/02/2011
 */

public class RelatorioAcompanhamentoClientesNegativadosSintetico extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the RelatorioAcompanhamentoClientesNegativados object
	 */
	public RelatorioAcompanhamentoClientesNegativadosSintetico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_CLIENTES_NEGATIVADOS_SINTETICO);
	}

	@Deprecated
	public RelatorioAcompanhamentoClientesNegativadosSintetico() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param Negativador Parametros
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
		DadosConsultaNegativacaoHelper parametrosHelper = (DadosConsultaNegativacaoHelper) getParametro("parametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório

		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioAcompanhamentoClientesNegativadosSinteticoBean relatorioBean = null;
		
		// Nova consulta para trazer objeto completo
		Collection colecaoNovos = fachada.pesquisarRelatorioAcompanhamentoClientesNegativador(parametrosHelper);

		if (colecaoNovos != null && !colecaoNovos.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			
			String idLocalidadeAnterior = "";
			String periodoEnvioNegativacaoAnterior = "";
			String situacaoAnterior = "";
			String localidadeAnterior = "";
			
			NegativadorMovimentoReg negativadorMovimentoReg = null;
			String idNegativador = "";
			String negativador = "";
			String nomeCliente = "";
			String periodoEnvioNegativacao = "";
			String localidade = "";
			String idLocalidade = "";
			String situacao = "";
			String matricula = "";
			String cpfCnpj = "";
			BigDecimal valorNegativado = BigDecimal.ZERO;
			BigDecimal valorPago = BigDecimal.ZERO;
			Collection colecaoDadosParcelamentoBean = new ArrayList();		
			boolean primeiraVez = true;
			
			String idGerenciaRegional = "";
			String gerenciaRegional = "";
			String idUnidadeNegocio = "";
			String unidadeNegocio = "";
			Integer quantidadeLocalidade = 0;
			Integer quantidadePagos = 0;
			String idGerenciaRegionalAnterior = "";
			String idUnidadeNegocioAnterior = "";
			String gerenciaRegionalAnterior = "";
			String unidadeNegocioAnterior = "";
			
			RelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean  parametrosSelecionadosBean = retornaParametrosSelecionaodos(parametrosHelper);
			
			Iterator it  = colecaoNovos.iterator();
			while(it.hasNext()){
				
				RelatorioAcompanhamentoClientesNegativadosHelper helper = (RelatorioAcompanhamentoClientesNegativadosHelper)it.next();
				negativadorMovimentoReg = helper.getNegativadorMovimentoReg();
				Short indicadorExcluidoNgim = helper.getIndicadorExcluidoNgim();
				
				boolean limpaValoresParc = false;
				// data de processamento
				periodoEnvioNegativacao = "";
				if (negativadorMovimentoReg != null	&& negativadorMovimentoReg.getNegativadorMovimento() != null) {
					periodoEnvioNegativacao = Util.formatarData(negativadorMovimentoReg.getNegativadorMovimento().getDataProcessamentoEnvio());
				}
				
				//localidade				
				localidade = "";
				idLocalidade = "";
				idGerenciaRegional = "";
				gerenciaRegional = "";
				idUnidadeNegocio = "";
				unidadeNegocio = "";
				if (negativadorMovimentoReg.getLocalidade() != null) {		
					idLocalidade = negativadorMovimentoReg.getLocalidade().getId().toString();
					localidade = negativadorMovimentoReg.getLocalidade().getDescricao();
					
					// Gerencia Regional
					if (negativadorMovimentoReg.getLocalidade().getGerenciaRegional() != null) {
						idGerenciaRegional = negativadorMovimentoReg.getLocalidade().getGerenciaRegional().getId().toString();
						gerenciaRegional = negativadorMovimentoReg.getLocalidade().getGerenciaRegional().getNome();
					}
					
					// Unidade de Negocio
					if (negativadorMovimentoReg.getLocalidade().getUnidadeNegocio() != null) {
						idUnidadeNegocio = negativadorMovimentoReg.getLocalidade().getUnidadeNegocio().getId().toString();
						unidadeNegocio = negativadorMovimentoReg.getLocalidade().getUnidadeNegocio().getNome();

					}
					
				}
				
				//se a data de processamento anterior for diferente da atual ou
				//se a localidade anterior for diferente da atual
				if(!periodoEnvioNegativacaoAnterior.equals(periodoEnvioNegativacao)||
						!idGerenciaRegional.equals(idGerenciaRegional) ||
						!idUnidadeNegocio.equals(idUnidadeNegocio)){
					limpaValoresParc = true;
				}
				
				if(!primeiraVez){
					//Inicializa o construtor constituído dos campos
					// necessários para a impressão do relatorio
					
					if(!limpaValoresParc){
						//dados da mesma localidade, os totais por localidade recebem zero
						relatorioBean = new RelatorioAcompanhamentoClientesNegativadosSinteticoBean(
								nomeCliente, 
							    matricula,
							    cpfCnpj,
							    valorNegativado,
							    valorPago,
							    situacao,
							    periodoEnvioNegativacaoAnterior,
							    localidadeAnterior, 
							    idLocalidadeAnterior,
							    idNegativador,
							    negativador,
							    colecaoDadosParcelamentoBean,
							    parametrosSelecionadosBean,
								quantidadeLocalidade,
								quantidadePagos);
						
						relatorioBean.setIdGerenciaRegional(idGerenciaRegionalAnterior);
						relatorioBean.setGerenciaRegional(gerenciaRegionalAnterior);
						relatorioBean.setIdUnidadeNegocio(idUnidadeNegocioAnterior);
						relatorioBean.setUnidadeNegocio(unidadeNegocioAnterior);
					}else{
						//mudou de localidade, totais por localidade recebem os valores calculados 
						relatorioBean = new RelatorioAcompanhamentoClientesNegativadosSinteticoBean(
								nomeCliente, 
							    matricula,
							    cpfCnpj,
							    valorNegativado,
							    valorPago,
							    situacao,
							    periodoEnvioNegativacaoAnterior,
							    localidadeAnterior, 
							    idLocalidadeAnterior,
							    idNegativador,
							    negativador,
							    colecaoDadosParcelamentoBean,
							    parametrosSelecionadosBean,
								quantidadeLocalidade,
								quantidadePagos);
						
						relatorioBean.setIdGerenciaRegional(idGerenciaRegionalAnterior);
						relatorioBean.setGerenciaRegional(gerenciaRegionalAnterior);
						relatorioBean.setIdUnidadeNegocio(idUnidadeNegocioAnterior);
						relatorioBean.setUnidadeNegocio(unidadeNegocioAnterior);
					}
					
					relatorioBeans.add(relatorioBean);
				}
				periodoEnvioNegativacaoAnterior = periodoEnvioNegativacao;
				idLocalidadeAnterior = idLocalidade;
				localidadeAnterior = localidade;
				idUnidadeNegocioAnterior = idUnidadeNegocio;
				idGerenciaRegionalAnterior = idGerenciaRegional;
				unidadeNegocioAnterior = unidadeNegocio;
				gerenciaRegionalAnterior = gerenciaRegional;
				primeiraVez = false;

				
				// Faz as validações dos campos necessáriose e formata a String
				// para a forma como irá aparecer no relatório
				
				valorNegativado = fachada.pesquisarRelatorioAcompanhamentoClientesNegativadorValorDebitosUnidade(
						parametrosHelper, negativadorMovimentoReg, new Integer(idGerenciaRegionalAnterior), 
						new Integer(idUnidadeNegocioAnterior), indicadorExcluidoNgim);
				if (valorNegativado == null) {
					valorNegativado = BigDecimal.ZERO;
				}
				
				valorPago = fachada.pesquisarRelatorioAcompanhamentoClientesNegativadorValorPagoUnidade(
						parametrosHelper, negativadorMovimentoReg, new Integer(idGerenciaRegionalAnterior), 
						new Integer(idUnidadeNegocioAnterior), indicadorExcluidoNgim);
				if (valorPago == null) {
					valorPago = BigDecimal.ZERO;
				}
				
				quantidadeLocalidade = fachada.pesquisarRelatorioAcompanhamentoClientesNegativadorCountClientes(
						parametrosHelper, negativadorMovimentoReg, new Integer(idGerenciaRegionalAnterior), 
						new Integer(idUnidadeNegocioAnterior), indicadorExcluidoNgim);
				
				quantidadePagos = fachada.pesquisarRelatorioAcompanhamentoClientesNegativadorCountValorPago(
						parametrosHelper, negativadorMovimentoReg, new Integer(idGerenciaRegionalAnterior), 
						new Integer(idUnidadeNegocioAnterior), indicadorExcluidoNgim);
				if (quantidadePagos == null) {
					quantidadePagos = 0;
				}
				
				// indicadorExisteDadosParcelamento = ConstantesSistema.NAO; 
			
				idNegativador = "";
				negativador = "";
				if (negativadorMovimentoReg.getNegativadorMovimento() != null && negativadorMovimentoReg.getNegativadorMovimento().getNegativador() != null) {
					Negativador negativ = negativadorMovimentoReg.getNegativadorMovimento().getNegativador();
					idNegativador = negativ.getId().toString();		
					
					if(negativ.getCliente()!=null){
						negativador = negativ.getCliente().getNome();
					}
				}
				
				// cliente nome
				nomeCliente = "";
				if (negativadorMovimentoReg.getCliente() != null) {
					nomeCliente = negativadorMovimentoReg.getCliente().getNome();
				}
				
				//situação
				situacao = "";
				if(negativadorMovimentoReg.getIndicadorAceito()== null){
					situacao = "NEGATIVAÇÕES SEM RETORNO DO NEGATIVADOR";
				}else if(negativadorMovimentoReg.getIndicadorAceito().intValue()== 2){
					situacao = "NEGATIVAÇÕES REJEITADAS";
				}else if(negativadorMovimentoReg.getIndicadorAceito().intValue()== 1){	
					if(indicadorExcluidoNgim != null && indicadorExcluidoNgim.intValue() == 1){
						situacao = "NEGATIVAÇÕES EXCLUÍDAS";
					}else{
						if(negativadorMovimentoReg.getCobrancaSituacao() != null){
							situacao = negativadorMovimentoReg.getCobrancaSituacao().getDescricao();	
						}else{
							situacao = "NEGATIVAÇÕES ACEITAS E SEM SITUAÇÃO DE COBRANÇA";
						}
					}
				}
				
				//se a situacao anterior for diferente da atual
				if(!situacaoAnterior.equals(situacao)){
					limpaValoresParc = true;
				}
				situacaoAnterior = situacao;
				
				// matricula do Imovel
				matricula = "";
				if (negativadorMovimentoReg.getImovel() != null) {
					matricula = negativadorMovimentoReg.getImovel().getId().toString();
				}
				
				//cpf ou cnpj
				cpfCnpj = "";				
				if (negativadorMovimentoReg.getNumeroCnpj() != null ) {
					cpfCnpj = negativadorMovimentoReg.getNumeroCnpj();
				}else if(negativadorMovimentoReg.getNumeroCpf() != null){
					cpfCnpj = negativadorMovimentoReg.getNumeroCpf();
				}
				
								
			}
			
			//insere a ultima linha do relatorio
			relatorioBean = new RelatorioAcompanhamentoClientesNegativadosSinteticoBean(
					nomeCliente, 
				    matricula,
				    cpfCnpj,
				    valorNegativado,
				    valorPago,
				    situacao,
				    periodoEnvioNegativacaoAnterior,
				    localidadeAnterior, 
				    idLocalidadeAnterior,
				    idNegativador,
				    negativador,
				    colecaoDadosParcelamentoBean,
				    parametrosSelecionadosBean,
					quantidadeLocalidade,
					quantidadePagos);
			
			relatorioBean.setIdGerenciaRegional(idGerenciaRegionalAnterior);
			relatorioBean.setGerenciaRegional(gerenciaRegionalAnterior);
			relatorioBean.setIdUnidadeNegocio(idUnidadeNegocioAnterior);
			relatorioBean.setUnidadeNegocio(unidadeNegocioAnterior);

			relatorioBeans.add(relatorioBean);
				
				
		}
		
		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		
		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_CLIENTES_NEGATIVADOS_SINTETICO, parametros, ds,
				tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			//*************************************************************************
			//*************************************************************************
			// ADICIONAR O NOVO RELATORIO
			//*************************************************************************
			//*************************************************************************
			persistirRelatorioConcluido(retorno, Relatorio.GERAR_RELATORIO_ACOMPANHAMENTO_CLIENTES_NEGATIVADOS_SINTETICO,
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
		int retorno = ConstantesExecucaoRelatorios.QUANTIDADE_NAO_INFORMADA;

        DadosConsultaNegativacaoHelper parametrosHelper = (DadosConsultaNegativacaoHelper) getParametro("parametros");
        
		retorno = Fachada.getInstancia().pesquisarRelatorioAcompanhamentoClientesNegativadorCount(parametrosHelper);

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAcompanhamentoClientesNegativadosSintetico", this);
	}

	
	public RelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean retornaParametrosSelecionaodos(DadosConsultaNegativacaoHelper parametrosHelper){
		Fachada fachada = Fachada.getInstancia();
		RelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean retorno =
			new RelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean();
		
		//*************************************************************
		// RM3755
		// Autor: Ivan Sergio
		// Data: 10/01/2011
		//*************************************************************
		if (parametrosHelper.getColecaoNegativador() != null && !parametrosHelper.getColecaoNegativador().isEmpty()) {
			FiltroNegativador filtroNegativador = new FiltroNegativador();
			filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");		
			filtroNegativador.adicionarParametro(new ParametroSimplesIn(FiltroNegativador.ID, parametrosHelper.getColecaoNegativador()));

			Collection collNegativador = fachada.pesquisar(filtroNegativador, Negativador.class.getName());
			Iterator itt = collNegativador.iterator();
			boolean primeiro = true;
			retorno.setNegativador("");
			while(itt.hasNext()){
				Negativador negativador = (Negativador)itt.next();
				if(negativador.getCliente()!=null){					
					retorno.setNegativador(retorno.getNegativador() + negativador.getCliente().getNome());
					if (collNegativador.size() > 1 && primeiro) {
						retorno.setNegativador(retorno.getNegativador() + " / ");
						primeiro = false;
					}
//					parametros.put("negativador", negativador.getCliente().getNome());
				}
			}
			
		} else {
			retorno.setNegativador("");
		}
		//*****************************************************
			
		if (parametrosHelper.getPeriodoEnvioNegativacaoInicio() != null) {
			retorno.setPeriodoEnvioNegativacao(parametrosHelper.getPeriodoEnvioNegativacaoInicio() + " à " + parametrosHelper.getPeriodoEnvioNegativacaoFim());
		} else {
			retorno.setPeriodoEnvioNegativacao("");
		}
		
		if (parametrosHelper.getTituloComando() != null) {
			retorno.setTituloComando(parametrosHelper.getTituloComando().toString());
		} else {
			retorno.setTituloComando("");
		}
		
		if (parametrosHelper.getIdEloPolo() != null) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();		
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_ELO,parametrosHelper.getIdEloPolo()));

			Collection collLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			Iterator itt = collLocalidade.iterator();
			while(itt.hasNext()){
				Localidade localidade = (Localidade)itt.next();
				if(localidade!=null){		
					retorno.setEloPolo(localidade.getDescricao());
				}
				break;
			}
		} else {
			retorno.setEloPolo("");
		}
		
		if (parametrosHelper.getIdLocalidade() != null) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();		
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,parametrosHelper.getIdLocalidade()));

			Collection collLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			Iterator itt = collLocalidade.iterator();
			while(itt.hasNext()){
				Localidade localidade = (Localidade)itt.next();
				if(localidade!=null){	
					retorno.setLocalidade(localidade.getDescricao());
				}
				break;
			}

		} else {
			retorno.setLocalidade("");
		}
		
		if (parametrosHelper.getIdSetorComercial() != null) {
			retorno.setCodigoSetorComercial(parametrosHelper.getIdSetorComercial().toString());
		} else {
			retorno.setCodigoSetorComercial("");
		}
		
		if (parametrosHelper.getIdQuadra() != null) {
			FiltroQuadra filtroQuadra = new FiltroQuadra();		
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID,parametrosHelper.getIdQuadra()));

			Collection collQuadra = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
			Iterator itt = collQuadra.iterator();
			while(itt.hasNext()){
				Quadra quadra = (Quadra)itt.next();
				if(quadra!=null){	
					retorno.setNumeroQuadra("" + quadra.getNumeroQuadra());
				}
				break;
			}
		} else {
			retorno.setNumeroQuadra("");
		}
		
		if (parametrosHelper.getColecaoCobrancaGrupo() != null) {
			String gruposCobranca = "";
			
			Iterator itt=parametrosHelper.getColecaoCobrancaGrupo().iterator();
			boolean primeiro = true;
			while(itt.hasNext()){
				CobrancaGrupo cobrancaGrupo = (CobrancaGrupo)itt.next();
				if(cobrancaGrupo!=null){
					if (primeiro) {
						gruposCobranca = gruposCobranca + cobrancaGrupo.getDescricao();
						primeiro = false;
					} else {
						gruposCobranca = gruposCobranca + ", " + cobrancaGrupo.getDescricao();
					}
				}		
			}
			retorno.setGrupoCobranca(gruposCobranca);

		} else {
			retorno.setGrupoCobranca("");
		}
		
		if (parametrosHelper.getColecaoGerenciaRegional() != null) {
			String gerenciasRegionais = "";
			
			Iterator itt=parametrosHelper.getColecaoGerenciaRegional().iterator();
			boolean primeiro = true;
			while(itt.hasNext()){
				GerenciaRegional gerenciaRegional = (GerenciaRegional)itt.next();
				if(gerenciaRegional!=null){
					if (primeiro) {
						gerenciasRegionais = gerenciasRegionais + gerenciaRegional.getNome();
						primeiro = false;
					} else {
						gerenciasRegionais = gerenciasRegionais + ", " +  gerenciaRegional.getNome();
					}
				}		
			}
			retorno.setGerenciaRegional(gerenciasRegionais);

		} else {
			retorno.setGerenciaRegional("");
		}
		
		
		if (parametrosHelper.getColecaoUnidadeNegocio() != null) {
			String unidadesNegocio = "";
			
			Iterator itt=parametrosHelper.getColecaoUnidadeNegocio().iterator();
			boolean primeiro = true;
			while(itt.hasNext()){
				UnidadeNegocio unidadeNegocio = (UnidadeNegocio)itt.next();
				if(unidadeNegocio!=null){
					if (primeiro) {
						unidadesNegocio = unidadesNegocio + unidadeNegocio.getNome();
						primeiro = false;
					} else {
						unidadesNegocio = unidadesNegocio + ", " +  unidadeNegocio.getNome();
					}
				}		
			}
			retorno.setUnidadeNegocio(unidadesNegocio);

		} else {
			retorno.setUnidadeNegocio("");
		}
		
		
		if (parametrosHelper.getColecaoImovelPerfil() != null) {
			String imoveisPerfil = "";
			
			Iterator itt=parametrosHelper.getColecaoImovelPerfil().iterator();
			boolean primeiro = true;
			while(itt.hasNext()){
				ImovelPerfil imovelPerfil = (ImovelPerfil)itt.next();
				if(imovelPerfil!=null){
					if (primeiro) {
						imoveisPerfil = imoveisPerfil + imovelPerfil.getDescricao();
						primeiro = false;
					} else {
						imoveisPerfil = imoveisPerfil + ", " +  imovelPerfil.getDescricao();
					}
				}		
			}
			retorno.setImovelPerfil(imoveisPerfil);

		} else {
			retorno.setImovelPerfil("");
		}
		
		if (parametrosHelper.getColecaoCategoria() != null) {
			String categorias = "";
			
			Iterator itt=parametrosHelper.getColecaoCategoria().iterator();
			boolean primeiro = true;
			while(itt.hasNext()){
				Categoria categoria = (Categoria)itt.next();
				if(categoria!=null){
					if (primeiro) {
						categorias = categorias + categoria.getDescricao();
						primeiro = false;
					} else {
						categorias = categorias + ", " +  categoria.getDescricao();
					}
				}		
			}
			retorno.setCategoria(categorias);

		} else {
			retorno.setCategoria("");
		}
		
		
		if (parametrosHelper.getColecaoClienteTipo() != null) {
			String tiposCliente = "";
			
			Iterator itt=parametrosHelper.getColecaoClienteTipo().iterator();
			boolean primeiro = true;
			while(itt.hasNext()){
				ClienteTipo clienteTipo = (ClienteTipo)itt.next();
				if(clienteTipo!=null){
					if (primeiro) {
						tiposCliente = tiposCliente + clienteTipo.getDescricao();
						primeiro = false;
					} else {
						tiposCliente = tiposCliente + ", " +  clienteTipo.getDescricao();
					}
				}		
			}
			retorno.setTipoCliente(tiposCliente);

		} else {
			retorno.setTipoCliente("");
		}
		
		if (parametrosHelper.getColecaoEsferaPoder() != null) {
			String esferasPoder = "";
			
			Iterator itt=parametrosHelper.getColecaoEsferaPoder().iterator();
			boolean primeiro = true;
			while(itt.hasNext()){
				EsferaPoder esferaPoder = (EsferaPoder)itt.next();
				if(esferaPoder!=null){
					if (primeiro) {
						esferasPoder = esferasPoder + esferaPoder.getDescricao();
						primeiro = false;
					} else {
						esferasPoder = esferasPoder + ", " +  esferaPoder.getDescricao();
					}
				}		
			}
			retorno.setEsferaPoder(esferasPoder);

		} else {
			retorno.setEsferaPoder("");
		}
		
		//*************************************************************
		// RM3755
		// Autor: Ivan Sergio
		// Data: 10/01/2011
		//*************************************************************
		if (parametrosHelper.getColecaoLigacaoAguaSituacao() != null) {
			String dados = "";
			
			Iterator itt=parametrosHelper.getColecaoLigacaoAguaSituacao().iterator();
			boolean primeiro = true;
			while(itt.hasNext()){
				LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao)itt.next();
				if(ligacaoAguaSituacao!=null){
					if (primeiro) {
						dados = dados + ligacaoAguaSituacao.getDescricao();
						primeiro = false;
					} else {
						dados = dados + ", " + ligacaoAguaSituacao.getDescricao();
					}
				}		
			}
			retorno.setLigacaoAguaSituacao(dados);
		} else {
			retorno.setLigacaoAguaSituacao("");
		}
		
		if (parametrosHelper.getColecaoLigacaoEsgotoSituacao() != null) {
			String dados = "";
			
			Iterator itt = parametrosHelper.getColecaoLigacaoEsgotoSituacao().iterator();
			boolean primeiro = true;
			while(itt.hasNext()){
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao)itt.next();
				if(ligacaoEsgotoSituacao != null){
					if (primeiro) {
						dados = dados + ligacaoEsgotoSituacao.getDescricao();
						primeiro = false;
					} else {
						dados = dados + ", " + ligacaoEsgotoSituacao.getDescricao();
					}
				}		
			}
			retorno.setLigacaoEsgotoSituacao(dados);
		} else {
			retorno.setLigacaoEsgotoSituacao("");
		}
		
		return retorno;
	}
	
}
