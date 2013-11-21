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
package gcom.relatorio.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.EmitirHistogramaEsgotoDetalheHelper;
import gcom.faturamento.bean.EmitirHistogramaEsgotoHelper;
import gcom.faturamento.bean.FiltrarEmitirHistogramaEsgotoHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * classe responsável por criar o relatório de histograma de ligação de esgoto
 * 
 * @author Rafael Pinto
 * @created 05/11/2007
 */
public class RelatorioHistogramaEsgotoLigacao extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioHistogramaEsgotoLigacao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_EMITIR_HISTOGRAMA_ESGOTO_LIGACAO);
	}

	@Deprecated
	public RelatorioHistogramaEsgotoLigacao() {
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

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltrarEmitirHistogramaEsgotoHelper filtrarEmitirHistogramaEsgotoHelper = 
			(FiltrarEmitirHistogramaEsgotoHelper) getParametro("filtrarEmitirHistogramaEsgotoHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioHistogramaEsgotoLigacaoBean relatorioHistogramaEsgotoLigacaoBean = null;

		Collection<EmitirHistogramaEsgotoHelper> colecao = 
			fachada.pesquisarEmitirHistogramaEsgoto(filtrarEmitirHistogramaEsgotoHelper);

		// se a coleção de parâmetros da analise não for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {

				EmitirHistogramaEsgotoHelper emitirHistogramaEsgotoHelper = 
					(EmitirHistogramaEsgotoHelper) colecaoIterator.next();
				
				String opcaoTotalizacao = emitirHistogramaEsgotoHelper.getOpcaoTotalizacao();
				String descricao = emitirHistogramaEsgotoHelper.getDescricaoTitulo();
				
				Collection colecaoDetalhe = emitirHistogramaEsgotoHelper.getColecaoEmitirHistogramaEsgotoDetalhe();
				
				String numeroLigacoes = null;
				String percentualParcialLigacao = null;
				String percentualAcumuladoLigacao = null;
				String economias = null;
				String volumeEstimado = null;
				String volumeMedido = null;
				String volumeTotal = null;
				String percentualParcialConsumo = null;
				String percentualAcumuladoConsumo = null;
				String mediaConsumo = null;
				String valorFaturamento = null;
				String percentualParcialFaturamento = null;
				String percentualAcumuladoFaturamento = null;
				
				NumberFormat formato = NumberFormat.getInstance(new Locale("pt", "BR"));
				
				if (colecaoDetalhe != null && !colecaoDetalhe.isEmpty()) {
				
					Iterator colecaoDetalheIterator = colecaoDetalhe.iterator();
					
					while (colecaoDetalheIterator.hasNext()) {
						
						EmitirHistogramaEsgotoDetalheHelper detalhe = 
							(EmitirHistogramaEsgotoDetalheHelper) colecaoDetalheIterator.next();
						
						String categoria = detalhe.getDescricaoCategoria();
						String subcategoria = detalhe.getDescricaoSubcategoria();
						numeroLigacoes = formato.format(detalhe.getQuantidadeLigacoes());
						percentualParcialLigacao = (""+detalhe.getPercentualParcialLigacao()).replace(".",",");
						
						percentualAcumuladoLigacao = "";
						if(!descricao.contains("TOTAL")){
							percentualAcumuladoLigacao = (""+detalhe.getPercentualAcumuladoLigacao()).replace(".",",");
						}
						
						economias = formato.format(detalhe.getQuantidadeEconomias());
						volumeEstimado = "";
						if(detalhe.getQuantidadeVolumeEstimado() != 0){
							volumeEstimado = formato.format(detalhe.getQuantidadeVolumeEstimado());
						}
						
						volumeMedido = "";
						if(detalhe.getQuantidadeVolumeMedido() != 0){
							volumeMedido = formato.format(detalhe.getQuantidadeVolumeMedido());
						}
						
						volumeTotal = formato.format(detalhe.getQuantidadeVolumeTotal());
						
						percentualParcialConsumo = (""+detalhe.getPercentualParcialConsumo()).replace(".",",");
						
						percentualAcumuladoConsumo = "";
						if(!descricao.contains("TOTAL")){
							percentualAcumuladoConsumo = (""+detalhe.getPercentualAcumuladoConsumo()).replace(".",",");
						}
						
						mediaConsumo = ""+detalhe.getMediaConsumo();
						
						valorFaturamento = Util.formatarMoedaReal(detalhe.getValorFaturado());
						
						percentualParcialFaturamento = 
							(""+detalhe.getPercentualParcialFaturamento()).replace(".",",");
						
						percentualAcumuladoFaturamento = "";
						if(!descricao.contains("TOTAL")){
							percentualAcumuladoFaturamento = 
								(""+detalhe.getPercentualAcumuladoFaturamento()).replace(".",",");
						}
						
						relatorioHistogramaEsgotoLigacaoBean = 
							new RelatorioHistogramaEsgotoLigacaoBean(
								// Opção de Totalização
								opcaoTotalizacao,
								// Descrição
								descricao,
								// Categoria
								categoria,
								// Número de Ligações
								// Subcategoria
								subcategoria,
								numeroLigacoes,
								// Percentual Parcial da Ligação
								percentualParcialLigacao,
								// Percentual Acumulado da Ligação
								percentualAcumuladoLigacao,						
								// Economias
								economias,						
								// Volume Medido
								volumeMedido,						
								// Volume Estimado
								volumeEstimado,						
								// Volume Total
								volumeTotal,						
								// Percentual Parcial do Consumo
								percentualParcialConsumo,						
								// Percentual Acumulado do Consumo
								percentualAcumuladoConsumo,						
								// Média de Consumo
								mediaConsumo,						
								// Valor do Faturamento
								valorFaturamento,					
								// Percentual Parcial do Faturamento
								percentualParcialFaturamento,						
								// Percentual Acumulado do Faturamento
								percentualAcumuladoFaturamento );
		
						// adiciona o bean a coleção
						relatorioBeans.add(relatorioHistogramaEsgotoLigacaoBean);
					
					}
				
				}
				
				numeroLigacoes = formato.format(emitirHistogramaEsgotoHelper.getTotalQuantidadeLigacoes());
				percentualParcialLigacao = (""+emitirHistogramaEsgotoHelper.getTotalPercentualParcialLigacao()).replace(".",",");
				
				percentualAcumuladoLigacao = "";
				if(!descricao.contains("TOTAL")){
					percentualAcumuladoLigacao = (""+emitirHistogramaEsgotoHelper.getTotalPercentualAcumuladoLigacao()).replace(".",",");
				}
				
				economias = formato.format(emitirHistogramaEsgotoHelper.getTotalQuantidadeEconomias());
				volumeEstimado = "";
				if(emitirHistogramaEsgotoHelper.getTotalQuantidadeVolumeEstimado() != 0){
					volumeEstimado = formato.format(emitirHistogramaEsgotoHelper.getTotalQuantidadeVolumeEstimado());
				}
				
				volumeMedido = "";
				if(emitirHistogramaEsgotoHelper.getTotalQuantidadeVolumeMedido() != 0){
					volumeMedido = formato.format(emitirHistogramaEsgotoHelper.getTotalQuantidadeVolumeMedido());
				}
				
				volumeTotal = formato.format(emitirHistogramaEsgotoHelper.getTotalQuantidadeVolumeTotal());
				
				percentualParcialConsumo = 
					(""+emitirHistogramaEsgotoHelper.getTotalPercentualParcialConsumo()).replace(".",",");
				
				percentualAcumuladoConsumo = "";
				if(!descricao.contains("TOTAL")){
					percentualAcumuladoConsumo = 
						(""+emitirHistogramaEsgotoHelper.getTotalPercentualAcumuladoConsumo()).replace(".",",");
				}
				
				mediaConsumo = ""+emitirHistogramaEsgotoHelper.getTotalMediaConsumo();
				
				valorFaturamento = Util.formatarMoedaReal(emitirHistogramaEsgotoHelper.getTotalValorFaturado());
				
				percentualParcialFaturamento = 
					(""+emitirHistogramaEsgotoHelper.getTotalPercentualParcialFaturamento()).replace(".",",");
				
				percentualAcumuladoFaturamento = "";
				if(!descricao.contains("TOTAL")){
					percentualAcumuladoFaturamento = 
						(""+emitirHistogramaEsgotoHelper.getTotalPercentualAcumuladoFaturamento()).replace(".",",");
				}				
				
				relatorioHistogramaEsgotoLigacaoBean = 
					new RelatorioHistogramaEsgotoLigacaoBean(
						// Opção de Totalização
						opcaoTotalizacao,
						// Descrição
						descricao,
						// Categoria
						"TOTAL",
						"TOTAL",
						// Número de Ligações
						numeroLigacoes,
						// Percentual Parcial da Ligação
						percentualParcialLigacao,
						// Percentual Acumulado da Ligação
						percentualAcumuladoLigacao,						
						// Economias
						economias,						
						// Volume Medido
						volumeMedido,						
						// Volume Estimado
						volumeEstimado,						
						// Volume Total
						volumeTotal,						
						// Percentual Parcial do Consumo
						percentualParcialConsumo,						
						// Percentual Acumulado do Consumo
						percentualAcumuladoConsumo,						
						// Média de Consumo
						mediaConsumo,						
						// Valor do Faturamento
						valorFaturamento,					
						// Percentual Parcial do Faturamento
						percentualParcialFaturamento,						
						// Percentual Acumulado do Faturamento
						percentualAcumuladoFaturamento );

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioHistogramaEsgotoLigacaoBean);
				
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("anoMes", Util.formatarAnoMesParaMesAno(filtrarEmitirHistogramaEsgotoHelper.getMesAnoFaturamento()));
		parametros.put("tipoFormatoRelatorio", "R0602");

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		if ( filtrarEmitirHistogramaEsgotoHelper.getIndicadorTarifaCategoria() == 1 ){
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_EMITIR_HISTOGRAMA_ESGOTO_LIGACAO,
				parametros, ds, tipoFormatoRelatorio);
		} else {
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_EMITIR_HISTOGRAMA_ESGOTO_LIGACAO_SUBCATEGORA,
					parametros, ds, tipoFormatoRelatorio);
		}

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.HISTOGRAMA_ESGOTO_LIGACAO,
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
		AgendadorTarefas.agendarTarefa("RelatorioHistogramaEsgotoLigacao", this);

	}

}