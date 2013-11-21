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
import gcom.faturamento.bean.EmitirHistogramaAguaDetalheHelper;
import gcom.faturamento.bean.EmitirHistogramaAguaHelper;
import gcom.faturamento.bean.FiltrarEmitirHistogramaAguaHelper;
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
 * classe responsável por criar o relatório de histograma de ligação de agua
 * 
 * @author Rafael Pinto / Rafael Correa
 * @created 07/06/2007
 */
public class RelatorioHistogramaAguaLigacao extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioHistogramaAguaLigacao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_EMITIR_HISTOGRAMA_AGUA_LIGACAO);
	}

	@Deprecated
	public RelatorioHistogramaAguaLigacao() {
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

		FiltrarEmitirHistogramaAguaHelper filtrarEmitirHistogramaAguaHelper = 
			(FiltrarEmitirHistogramaAguaHelper) getParametro("filtrarEmitirHistogramaAguaHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioHistogramaAguaLigacaoBean relatorioHistogramaAguaLigacaoBean = null;

		Collection<EmitirHistogramaAguaHelper> colecao = 
			fachada.pesquisarEmitirHistogramaAgua(filtrarEmitirHistogramaAguaHelper);

		// se a coleção de parâmetros da analise não for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {

				EmitirHistogramaAguaHelper emitirHistogramaAguaHelper = 
					(EmitirHistogramaAguaHelper) colecaoIterator.next();
				
				String opcaoTotalizacao = emitirHistogramaAguaHelper.getOpcaoTotalizacao();
				String descricao = emitirHistogramaAguaHelper.getDescricaoTitulo();
				
				Collection colecaoDetalhe = emitirHistogramaAguaHelper.getColecaoEmitirHistogramaAguaDetalhe();
				
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
						
						EmitirHistogramaAguaDetalheHelper detalhe = 
							(EmitirHistogramaAguaDetalheHelper) colecaoDetalheIterator.next();
						
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
						
						relatorioHistogramaAguaLigacaoBean = 
							new RelatorioHistogramaAguaLigacaoBean(
								// Opção de Totalização
								opcaoTotalizacao,
								// Descrição
								descricao,
								// Categoria
								categoria,
								// Subcategoria
								subcategoria,
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
						relatorioBeans.add(relatorioHistogramaAguaLigacaoBean);
					
					}
				
				}
				
				numeroLigacoes = formato.format(emitirHistogramaAguaHelper.getTotalQuantidadeLigacoes());
				percentualParcialLigacao = (""+emitirHistogramaAguaHelper.getTotalPercentualParcialLigacao()).replace(".",",");
				
				percentualAcumuladoLigacao = "";
				if(!descricao.contains("TOTAL")){
					percentualAcumuladoLigacao = (""+emitirHistogramaAguaHelper.getTotalPercentualAcumuladoLigacao()).replace(".",",");
				}
				
				economias = formato.format(emitirHistogramaAguaHelper.getTotalQuantidadeEconomias());
				volumeEstimado = "";
				if(emitirHistogramaAguaHelper.getTotalQuantidadeVolumeEstimado() != 0){
					volumeEstimado = formato.format(emitirHistogramaAguaHelper.getTotalQuantidadeVolumeEstimado());
				}
				
				volumeMedido = "";
				if(emitirHistogramaAguaHelper.getTotalQuantidadeVolumeMedido() != 0){
					volumeMedido = formato.format(emitirHistogramaAguaHelper.getTotalQuantidadeVolumeMedido());
				}
				
				volumeTotal = formato.format(emitirHistogramaAguaHelper.getTotalQuantidadeVolumeTotal());
				
				percentualParcialConsumo = 
					(""+emitirHistogramaAguaHelper.getTotalPercentualParcialConsumo()).replace(".",",");
				
				percentualAcumuladoConsumo = "";
				if(!descricao.contains("TOTAL")){
					percentualAcumuladoConsumo = 
						(""+emitirHistogramaAguaHelper.getTotalPercentualAcumuladoConsumo()).replace(".",",");
				}
				
				mediaConsumo = ""+emitirHistogramaAguaHelper.getTotalMediaConsumo();
				
				valorFaturamento = Util.formatarMoedaReal(emitirHistogramaAguaHelper.getTotalValorFaturado());
				
				percentualParcialFaturamento = 
					(""+emitirHistogramaAguaHelper.getTotalPercentualParcialFaturamento()).replace(".",",");
				
				percentualAcumuladoFaturamento = "";
				if(!descricao.contains("TOTAL")){
					percentualAcumuladoFaturamento = 
						(""+emitirHistogramaAguaHelper.getTotalPercentualAcumuladoFaturamento()).replace(".",",");
				}				
				
				relatorioHistogramaAguaLigacaoBean = 
					new RelatorioHistogramaAguaLigacaoBean(
						// Opção de Totalização
						opcaoTotalizacao,
						// Descrição
						descricao,
						// Categoria
						"TOTAL",
						// Subcategoria
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
				relatorioBeans.add(relatorioHistogramaAguaLigacaoBean);
				
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("anoMes", Util.formatarAnoMesParaMesAno(filtrarEmitirHistogramaAguaHelper.getMesAnoFaturamento()));
		parametros.put("tipoFormatoRelatorio", "R0600");
		
		

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		if ( filtrarEmitirHistogramaAguaHelper.getIndicadorTarifaCategoria() == 1 ){
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_EMITIR_HISTOGRAMA_AGUA_LIGACAO,
				parametros, ds, tipoFormatoRelatorio);
		} else {
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_EMITIR_HISTOGRAMA_AGUA_LIGACAO_SUBCATEGORIA,
					parametros, ds, tipoFormatoRelatorio);
		}

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.HISTOGRAMA_AGUA_LIGACAO,
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
		AgendadorTarefas.agendarTarefa("RelatorioHistogramaAguaLigacao", this);

	}

}