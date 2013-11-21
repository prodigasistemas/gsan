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
package gcom.relatorio.cadastro.imovel;

import gcom.batch.Relatorio;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * classe responsável por criar o relatório de bairro manter de água
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class EmissaoBoletimCadastro extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public EmissaoBoletimCadastro(Usuario usuario) {
		super(usuario, ConstantesRelatorios.BOLETIM_CADASTRO);
	}
	
	@Deprecated
	public EmissaoBoletimCadastro() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */
	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		// id da genrencia regional
		String gerenciaRegionalPesquisa = (String) getParametro("gerenciaRegional");
		// id da unidade negocio
		String idUnidadeNegoio = (String) getParametro("unidadeNegocio");

		// numero da quadra origem
		String qudraOrigem = (String) getParametro("quadraOrigem");
		// numero quadra destino
		String quadraDestino = (String) getParametro("quadraDestino");
		// lote origem
		String loteOrigem = (String) getParametro("loteOrigem");
		// lote destino
		String loteDestino = (String) getParametro("loteDestino");
		// cep
		String cep = (String) getParametro("cep");
		// id localidade origem
		String localidadeOrigem = (String) getParametro("localidadeOrigem");
		// id localidade destino
		String localidadeDestino = (String) getParametro("localidadeDestino");
		// setor comercial origem CD
		String setorComercialOrigemCD = (String) getParametro("setorComercialOrigemCD");
		// setor comercial destino CD
		String setorComercialDestinoCD = (String) getParametro("setorComercialDestinoCD");
		// cliente ID
		String clienteID = (String) getParametro("clienteID");
		// municipio ID
		String municipioID = (String) getParametro("municipioID");
		// bairro ID
		String bairroID = (String) getParametro("bairroID");
		// logradouro ID
		String logradouroID = (String) getParametro("logradouroID");
		// cliente relacao tipo ID
		String clienteRelacaoTipoID = (String) getParametro("clienteRelacaoTipoID");
		// cliente tipo ID
		String clienteTipoID = (String) getParametro("clienteTipoID");
		// imovel condominio ID
		String imovelCondominioID = (String) getParametro("imovelCondominioID");
		// imovel Principal ID
		String imovelPrincipalID = (String) getParametro("imovelPrincipalID");
		// nome Conta ID
		// String nomeContaID = (String) getParametro("nomeContaID");
		// situacao Agua
		String situacaoAgua = (String) getParametro("situacaoAgua");
		// situacao Ligacao Esgoto
		String situacaoLigacaoEsgoto = (String) getParametro("situacaoLigacaoEsgoto");
		// consumo Minimo Inicial
		String consumoMinimoInicial = (String) getParametro("consumoMinimoInicial");
		// consumo Minimo Final
		String consumoMinimoFinal = (String) getParametro("consumoMinimoFinal");
		// consumo Minimo Fixado Esgoto Inicial
		String consumoMinimoFixadoEsgotoInicial = (String) getParametro("consumoMinimoFixadoEsgotoInicial");
		// consumo Minimo Fixado Esgoto Final
		String consumoMinimoFixadoEsgotoFinal = (String) getParametro("consumoMinimoFixadoEsgotoFinal");
		// intervalo Percentual Esgoto Inicial
		String intervaloPercentualEsgotoInicial = (String) getParametro("intervaloPercentualEsgotoInicial");
		// intervalor Percentual Esgoto Final
		String intervaloPercentualEsgotoFinal = (String) getParametro("intervaloPercentualEsgotoFinal");
		// indicador Medicao
		String indicadorMedicaoPesquisa = (String) getParametro("indicadorMedicao");
		// tipo Medicao ID
		String tipoMedicaoID = (String) getParametro("tipoMedicaoID");
		// intervalo Media Minima Imovel Inicial
		String intervaloMediaMinimaImovelInicial = (String) getParametro("intervaloMediaMinimaImovelInicial");
		// intervalo Media Minima Imovel Final
		String intervaloMediaMinimaImoveFinal = (String) getParametro("intervaloMediaMinimaImoveFinal");
		// intervalo Media Minima Hidrometro Inicial
		String intervaloMediaMinimaHidrometroInicial = (String) getParametro("intervaloMediaMinimaHidrometroInicial");
		// intervalo Media Minima Hidrometro Final
		String intervaloMediaMinimaHidrometroFinal = (String) getParametro("intervaloMediaMinimaHidrometroFinal");
		// perfil Imovel ID
		String perfilImovelID = (String) getParametro("perfilImovelID");
		// categoria Imovel ID
		String categoriaImovelID = (String) getParametro("categoriaImovelID");
		// sub categoria ID
		String subCategoriaID = (String) getParametro("subCategoriaID");
		// quantidade Economias Inicial
		String quantidadeEconomiasInicial = (String) getParametro("quantidadeEconomiasInicial");
		// quantidade Economias Final
		String quantidadeEconomiasFinal = (String) getParametro("quantidadeEconomiasFinal");
		// numero Pontos Inicial
		String numeroPontosInicial = (String) getParametro("numeroPontosInicial");
		// numero Pontos Final
		String numeroPontosFinal = (String) getParametro("numeroPontosFinal");
		// numero Moradores Inicial
		String numeroMoradoresInicial = (String) getParametro("numeroMoradoresInicial");
		// numero Moradoras Final
		String numeroMoradoresFinal = (String) getParametro("numeroMoradoresFinal");
		// area Construida Inicial
		String areaConstruidaInicial = (String) getParametro("areaConstruidaInicial");
		// area Construida Final
		String areaConstruidaFinal = (String) getParametro("areaConstruidaFinal");
		// area Construida Faixa
		String areaConstruidaFaixa = (String) getParametro("areaConstruidaFaixa");
		// poco Tipo ID
		String pocoTipoID = (String) getParametro("pocoTipoID");
		// tipo Situacao Faturamento ID
		String tipoSituacaoFaturamentoID = (String) getParametro("tipoSituacaoFaturamentoID");
		// tipo Situacao Especial Cobranca ID
		String tipoSituacaoEspecialCobrancaID = (String) getParametro("tipoSituacaoEspecialCobrancaID");
		// situacao Cobranca ID
		String situacaoCobrancaID = (String) getParametro("situacaoCobrancaID");
		// dia Vencimento Alternativo
		String diaVencimentoAlternativo = (String) getParametro("diaVencimentoAlternativo");
		// ocorrencia Cadastro
		String ocorrenciaCadastro = (String) getParametro("ocorrenciaCadastro");
		// tarifa Consumo
		String tarifaConsumo = (String) getParametro("tarifaConsumo");
		// anormalidade Elo
		String anormalidadeElo = (String) getParametro("anormalidadeElo");
//		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String indicadorCpfCnpj = (String) getParametro("indicadorCpfCnpj");		
		String cpfCnpj = (String) getParametro("cpfCnpj");

		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();

//		RelatorioManterResolucaoDiretoriaBean relatorioBean = null;


		retorno = fachada.emitirBoletimCadastro(imovelCondominioID,
				imovelPrincipalID, situacaoAgua, consumoMinimoInicial,
				consumoMinimoFinal, situacaoLigacaoEsgoto,
				consumoMinimoFixadoEsgotoInicial,
				consumoMinimoFixadoEsgotoFinal,
				intervaloPercentualEsgotoInicial,
				intervaloPercentualEsgotoFinal,
				intervaloMediaMinimaImovelInicial,
				intervaloMediaMinimaImoveFinal,
				intervaloMediaMinimaHidrometroInicial,
				intervaloMediaMinimaHidrometroFinal, perfilImovelID,
				pocoTipoID, tipoSituacaoFaturamentoID,
				situacaoCobrancaID, tipoSituacaoEspecialCobrancaID,
				anormalidadeElo, areaConstruidaInicial,
				areaConstruidaFinal, ocorrenciaCadastro, tarifaConsumo,
				gerenciaRegionalPesquisa, localidadeOrigem,
				localidadeDestino, setorComercialOrigemCD,
				setorComercialDestinoCD, qudraOrigem, quadraDestino,
				loteOrigem, loteDestino, cep, logradouroID, bairroID,
				municipioID, tipoMedicaoID, indicadorMedicaoPesquisa,
				subCategoriaID, categoriaImovelID,
				quantidadeEconomiasInicial, quantidadeEconomiasFinal,
				diaVencimentoAlternativo, clienteID, clienteTipoID,
				clienteRelacaoTipoID, numeroPontosInicial,
				numeroPontosFinal, numeroMoradoresInicial,
				numeroMoradoresFinal, areaConstruidaFaixa,idUnidadeNegoio,
				indicadorCpfCnpj, cpfCnpj);

		// se a coleção de parâmetros da analise não for vazia
//		if (colecaoResolucoesDiretoria != null
//				&& !colecaoResolucoesDiretoria.isEmpty()) {
//
//			// coloca a coleção de parâmetros da analise no iterator
//			Iterator colecaoResolucoesDiretoriaIterator = colecaoResolucoesDiretoria
//					.iterator();
//
//			// laço para criar a coleção de parâmetros da analise
//			while (colecaoResolucoesDiretoriaIterator.hasNext()) {
//
//				ResolucaoDiretoria resolucaoDiretoria = (ResolucaoDiretoria) colecaoResolucoesDiretoriaIterator
//						.next();
//				
//				// Faz as validações dos campos necessáriose e formata a String
//				// para a forma como irá aparecer no relatório
//				
//				// Data Vigência Início
//				String dataVigenciaInicio = "";
//				
//				if (resolucaoDiretoria.getDataVigenciaInicio() != null) {
//					dataVigenciaInicio = Util.formatarData(resolucaoDiretoria
//							.getDataVigenciaInicio());
//				}
//				
//				// Data Vigência Fim
//				String dataVigenciaFim = "";
//				
//				if (resolucaoDiretoria.getDataVigenciaFim() != null) {
//					dataVigenciaFim = Util.formatarData(resolucaoDiretoria
//							.getDataVigenciaFim());
//				}
//
//				relatorioBean = new RelatorioManterResolucaoDiretoriaBean(
//						
//						// Número
//						resolucaoDiretoria.getNumeroResolucaoDiretoria(),
//						
//						// Assunto
//						resolucaoDiretoria.getDescricaoAssunto(),
//						
//						// Data da Vigência Início
//						dataVigenciaInicio,
//										
//						// Data da Vigência Fim
//						dataVigenciaFim);
//
//				// adiciona o bean a coleção
//				relatorioBeans.add(relatorioBean);
//			}
//		}
//		// __________________________________________________________________
//
//		// Parâmetros do relatório
//		Map parametros = new HashMap();
//
//		// adiciona os parâmetros do relatório
//		// adiciona o laudo da análise
//		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
//		
//		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
//		
//		parametros.put("numero", resolucaoDiretoriaParametros
//				.getNumeroResolucaoDiretoria());
//
//		parametros.put("assunto", resolucaoDiretoriaParametros
//				.getDescricaoAssunto());
//
//		if (resolucaoDiretoriaParametros.getDataVigenciaInicio() != null) {
//			parametros.put("dataInicio", Util
//					.formatarData(resolucaoDiretoriaParametros
//							.getDataVigenciaInicio()));
//		} else {
//			parametros.put("dataInicio", "");
//		}
//
//		if (resolucaoDiretoriaParametros.getDataVigenciaFim() != null) {
//			parametros.put("dataTermino", Util
//					.formatarData(resolucaoDiretoriaParametros
//							.getDataVigenciaFim()));
//		} else {
//			parametros.put("dataTermino", "");
//		}
//
//		// cria uma instância do dataSource do relatório
//		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
//
//		retorno = gerarRelatorio(
//				ConstantesRelatorios.RELATORIO_RESOLUCAO_DIRETORIA_MANTER,
//				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.BOLETIM_CADASTRO,
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
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("BoletimCadastro", this);
	}
}