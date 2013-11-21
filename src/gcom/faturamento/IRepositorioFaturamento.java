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
package gcom.faturamento;

import gcom.arrecadacao.debitoautomatico.DebitoAutomaticoMovimento;
import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ordemservico.ServicoCobrancaValor;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.imovel.bean.ImovelCobrarDoacaoHelper;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.ComandoEmpresaCobrancaContaHelper;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.faturamento.autoinfracao.AutosInfracao;
import gcom.faturamento.bean.ApagarDadosFaturamentoHelper;
import gcom.faturamento.bean.EmitirContaHelper;
import gcom.faturamento.bean.FiltrarEmitirHistogramaAguaEconomiaHelper;
import gcom.faturamento.bean.FiltrarEmitirHistogramaAguaHelper;
import gcom.faturamento.bean.FiltrarEmitirHistogramaEsgotoEconomiaHelper;
import gcom.faturamento.bean.FiltrarEmitirHistogramaEsgotoHelper;
import gcom.faturamento.bean.PrescreverDebitosImovelHelper;
import gcom.faturamento.bean.SituacaoEspecialFaturamentoHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.ConsumoTarifaCategoria;
import gcom.faturamento.consumotarifa.ConsumoTarifaFaixa;
import gcom.faturamento.consumotarifa.ConsumoTarifaVigencia;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaCategoria;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.ContaImpostosDeduzidos;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.conta.Fatura;
import gcom.faturamento.conta.FaturaItem;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.credito.CreditoRealizadoHistorico;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.DebitoTipoVigencia;
import gcom.financeiro.ResumoFaturamento;
import gcom.gui.cobranca.cobrancaporresultado.MovimentarOrdemServicoEmitirOSHelper;
import gcom.micromedicao.Rota;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.medicao.FiltroMedicaoHistoricoSql;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.relatorio.faturamento.FiltrarRelatorioDevolucaoPagamentosDuplicidadeHelper;
import gcom.relatorio.faturamento.FiltrarRelatorioJurosMultasDebitosCanceladosHelper;
import gcom.relatorio.faturamento.conta.RelatorioContasCanceladasRetificadasHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.FachadaException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * < <Descrição da Interface>>
 * 
 * @author Administrador
 */
public interface IRepositorioFaturamento {

	/**
	 * Método responsável por verificar se existe no banco um determinado ID na
	 * tabela de faturamento_grupo - caso exista o id passado como parâmetro na
	 * tabela, retorna true, caso contrário retorna false
	 * 
	 * @param Integer
	 *            id - id de um FaturamentoGrupo
	 * @return boolean - true para existir o id na tabela, false para não
	 *         existir
	 * @exception ErroRepositorioException
	 */
	public boolean verificarExistenciaIdGrupoFaturamento(Integer id)
			throws ErroRepositorioException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param anoMesReferencia
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Integer pesquisarExistenciaConta(Imovel imovel, int anoMesReferencia)
			throws ErroRepositorioException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param faturamentoGrupo
	 *            Descrição do parâmetro
	 * @param anoMesReferencia
	 *            Descrição do parâmetro
	 * @param faturamentoAtividade
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Object[] obterDataPrevistaRealizadaFaturamentoAtividadeCronograma(
			FaturamentoGrupo faturamentoGrupo, int anoMesReferencia,
			FaturamentoAtividade faturamentoAtividade)
			throws ErroRepositorioException;

	/**
	 * 
	 * @param idFaturamentoAtividadeCronograma
	 * @throws ErroRepositorioException
	 */
	public void removerTodasRotasPorCronogramaFaturamento(
			Integer idFaturamentoAtividadeCronograma)
			throws ErroRepositorioException;

	/**
	 * [UC0104] Manter Comando Atividade de Faturamento
	 * 
	 * @return uma lista de atividades de faturamento comandadas e ainda não
	 *         realizadas
	 * @throws ErroRepositorioException
	 */
	public Collection buscarAtividadeComandadaNaoRealizada(Integer numeroPagina)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite alterar ou excluir um comando de atividade de
	 * faturamento
	 * 
	 * [UC0104] Manter Comando Atividade de Faturamento
	 * 
	 * Retorna o count do resultado da pesquisa de Faturamento Atividade
	 * Cronograma não realizadas
	 * 
	 * buscarAtividadeComandadaNaoRealizadaCount
	 * 
	 * @author Roberta Costa
	 * @date 18/07/2006
	 * 
	 * @param filtroFaturamentoAtividadeCronograma
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer buscarAtividadeComandadaNaoRealizadaCount()
			throws ErroRepositorioException;

	/**
	 * [UC0104] Manter Comando Atividade de Faturamento
	 * 
	 * @return uma coleção de FATURAMENTO_ATIVIDADE_CRONOGRAMA
	 * @throws ErroRepositorioException
	 */
	public Collection buscarFaturamentoAtividadeCronograma(String ids)
			throws ErroRepositorioException;

	/**
	 * [UC0150] - Retificar Conta Author: Raphael Rossiter Data: 26/12/2005
	 * 
	 * @param conta
	 * @return uma coleção com os débitos cobrados de uma conta
	 * @throws ControladorException
	 */
	public Collection buscarDebitosCobradosConta(Conta conta)
			throws ErroRepositorioException;

	/**
	 * [UC0150] - Retificar Conta Author: Raphael Rossiter Data: 28/12/2005
	 * 
	 * @param conta
	 * @return uma coleção com os créditos realizados de uma conta
	 * @throws ControladorException
	 */
	public Collection buscarCreditosRealizadosConta(Conta conta)
			throws ErroRepositorioException;

	public Collection pesquisarConsumoTarifaVigenciaEntreDataLeituraAnterioreDataLeituraAtual(
			ConsumoTarifa consumoTarifa, Date dataLeituraAnterior,
			Date dataLeituraAtual) throws ErroRepositorioException;

	public Collection pesquisarConsumoTarifaVigenciaMenorDataLeituraAnterior(
			ConsumoTarifa consumoTarifa, Date dataLeituraAnterior)
			throws ErroRepositorioException;

	public Collection pesquisarConsumoTarifaCategoria(
			ConsumoTarifaVigencia consumoTarifaVigencia, Categoria categoria)
			throws ErroRepositorioException;

	public Collection pesquisarConsumoTarifaFaixa(
			ConsumoTarifaCategoria consumoTarifaCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0120] - Calcular Valores de Água e/ou Esgoto
	 * 
	 * Retorna a faixa de consumo de acordo com a tarifa da categoria e o
	 * consumo
	 * 
	 * @author Rafael Corrêa
	 * @date 13/07/2009
	 * 
	 * @param consumoTarifaCategoria
	 * @param consumo
	 * @throws ErroRepositorioException
	 */
	public ConsumoTarifaFaixa pesquisarConsumoTarifaFaixa(
			ConsumoTarifaCategoria consumoTarifaCategoria, Integer consumo)
			throws ErroRepositorioException;

	/**
	 * [UC0168] - Inserir Tarifa de Consumo Retorna a date de vigência em vigor
	 * de uma tarifa de consumo
	 * 
	 * @param consumoTarifa
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarConsumoTarifaVigenciaEmVigor(
			ConsumoTarifa consumoTarifa) throws ErroRepositorioException;

	/**
	 * [UC0168] - Inserir Tarifa de Consumo Retorna a date de vigência em vigor
	 * de uma tarifa de consumo Pesquisa a Data de Vigencia da Consumo Tarifa e
	 * da Consumo Tarifa Vigencia
	 * 
	 * @author Rafael Santos
	 * @since 11/07/2006
	 * @param consumoTarifa
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarConsumoTarifaVigenciaEmVigor(
			ConsumoTarifa consumoTarifa, int idConsumoTarifaVigencia)
			throws ErroRepositorioException;

	/**
	 * [UC0145] - Inserir Conta Author: Raphael Rossiter Data: 13/01/2006
	 * 
	 * Seleciona a partir da tabela CLIENTE_IMOVEL para IMOV_ID=Id do imóvel e
	 * CLIM_DTRELACAOFIM com o valor correspondente a nulo
	 * 
	 * @param IMOVEL
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteImovelDataRelacaoFimNull(Imovel imovel)
			throws ErroRepositorioException;

	public Integer pesquisarFaturamentoGrupoCronogramaMensal(
			Integer idFaturamentoGrupo, Integer anoMes)
			throws ErroRepositorioException;

	public Integer pesquisarFaturamentoAtividadeCronograma(
			Integer idFaturamentoGrupoCronogramaMensal,
			Integer idFaturamentoAtividade) throws ErroRepositorioException;

	public void atualizarFaturamentoAtividadeCronograma(
			Integer idFaturamentoAtividadeCronograma, Date dataRealizada)
			throws ErroRepositorioException;

	/**
	 * [UC0156] Informar Situacao Especial Faturamento
	 * 
	 * @author Rhawi Dantas
	 * @created 18/01/2006
	 * 
	 */
	public void inserirFaturamentoSituacaoHistorico(
			Collection collectionFaturamentoSituacaoHistorico)
			throws ErroRepositorioException;

	/**
	 * Consulta ResumoFaturamento para a geração do relatório '[UC0173] Gerar
	 * Relatório de Resumo Faturamento' de acordo com a opção de totalização.
	 * 
	 * @author Rodrigo Silveira, Diogo Peixoto
	 * @created 18/01/2006, 27/04/2011
	 * @param anoMesReferencia
	 * @param opcaoRelatorio
	 * @param estadoMunicipio
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoFaturamentoRelatorioPorEstado(
			int anoMesReferencia, String opcaoRelatorio, boolean estadoMunicipio)
			throws ErroRepositorioException;

	/**
	 * Consulta ResumoFaturamento para a geração do relatório '[UC0173] Gerar
	 * Relatório de Resumo Faturamento' de acordo com a opção de totalização.
	 * 
	 * @author Rodrigo Silveira
	 * @created 18/01/2006
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoFaturamentoRelatorioPorEstadoPorGerenciaRegional(
			int anoMesReferencia, String opcaoRelatorio)
			throws ErroRepositorioException;

	/**
	 * Consulta ResumoFaturamento para a geração do relatório '[UC0173] Gerar
	 * Relatório de Resumo Faturamento' de acordo com a opção de totalização.
	 * 
	 * @author Rodrigo Silveira
	 * @created 18/01/2006
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoFaturamentoRelatorioPorEstadoPorLocalidade(
			int anoMesReferencia, String opcaoRelatorio)
			throws ErroRepositorioException;

	/**
	 * Consulta ResumoFaturamento para a geração do relatório '[UC0173] Gerar
	 * Relatório de Resumo Faturamento' de acordo com a opção de totalização.
	 * 
	 * @author Diogo Peixoto
	 * @created 25/04/2011
	 * 
	 * @param anoMesReferencia
	 * @param opcaoRelatorio
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoFaturamentoRelatorioPorEstadoPorMunicipio(
			int anoMesReferencia, String opcaoRelatorio)
			throws ErroRepositorioException;

	/**
	 * Consulta ResumoFaturamento para a geração do relatório '[UC0173] Gerar
	 * Relatório de Resumo Faturamento' de acordo com a opção de totalização.
	 * 
	 * @author Rodrigo Silveira
	 * @created 18/01/2006
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoFaturamentoRelatorioPorGerenciaRegional(
			int anoMesReferencia, Integer gerenciaRegional,
			String opcaoRelatorio) throws ErroRepositorioException;

	/**
	 * Consulta ResumoFaturamento para a geração do relatório '[UC0173] Gerar
	 * Relatório de Resumo Faturamento' de acordo com a opção de totalização.
	 * 
	 * @author Rodrigo Silveira
	 * @created 18/01/2006
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoFaturamentoRelatorioPorGerenciaRegionalPorLocalidade(
			int anoMesReferencia, Integer gerenciaRegional,
			String opcaoRelatorio) throws ErroRepositorioException;

	/**
	 * Consulta ResumoFaturamento para a geração do relatório '[UC0173] Gerar
	 * Relatório de Resumo Faturamento' de acordo com a opção de totalização.
	 * 
	 * @author Rodrigo Silveira
	 * @created 18/01/2006
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoFaturamentoRelatorioPorLocalidade(
			int anoMesReferencia, Integer localidade, String opcaoRelatorio)
			throws ErroRepositorioException;

	/**
	 * Consulta ResumoFaturamento para a geração do relatório '[UC0173] Gerar
	 * Relatório de Resumo Faturamento' de acordo com a opção de totalização.
	 * 
	 * @author Diogo Peixoto
	 * @created 25/04/2011
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoFaturamentoRelatorioPorMunicipio(
			int anoMesReferencia, Integer municipio, String opcaoRelatorio)
			throws ErroRepositorioException;

	/**
	 * [UC0146] - Manter Conta Author: Raphael Rossiter Data: 21/01/2006
	 * 
	 * Obtém as contas de um imóvel que poderão ser mantidas
	 * 
	 * @param imovel
	 * @param situacaoNormal
	 * @param situacaoIncluida
	 * @param situacaoRetificada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterContasImovelManter(Imovel imovel,
			Integer situacaoNormal, Integer situacaoIncluida,
			Integer situacaoRetificada) throws ErroRepositorioException;

	/**
	 * [UC0302] - Gerar Debitos a Cobrar de Acréscimos por Impontualidade
	 * Author: Fernanda Paiva Data: 24/04/2006
	 * 
	 * Obtém as contas de um imóvel com ano/mes da data de vencimento menor ou
	 * igual ao ano/mes de referencia da arrecadacao corrente e com situacao
	 * atual correspondente a normal, retificada ou incluida.
	 * 
	 * @param imovel
	 * @param situacaoNormal
	 * @param situacaoIncluida
	 * @param situacaoRetificada
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterContasImovel(Integer imovel, Integer situacaoNormal,
			Integer situacaoIncluida, Integer situacaoRetificada,
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0302] - Gerar Debitos a Cobrar de Acréscimos por Impontualidade
	 * Author: Fernanda Paiva Data: 24/04/2006
	 * 
	 * Obtém as guias de pagamento de um imóvel com ano/mes da data de
	 * vencimento menor ou igual ao ano/mes de referencia da arrecadacao
	 * corrente e com situacao atual correspondente a normal, retificada ou
	 * incluida.
	 * 
	 * @param imovel
	 * @param situacaoNormal
	 * @param situacaoIncluida
	 * @param situacaoRetificada
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterGuiasPagamentoImovel(Integer imovel,
			Integer situacaoNormal, Integer situacaoIncluida,
			Integer situacaoRetificada, Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0147] - Cancelar Conta Author: Raphael Rossiter Data: 23/01/2006
	 * 
	 * @param conta
	 * @param debitoCreditoSituacaoAnterior
	 * @throws ErroRepositorioException
	 */
	public void cancelarContaReferenciaContabilMenorSistemaParametro(
			Conta conta, Integer debitoCreditoSituacaoAnterior)
			throws ErroRepositorioException;

	/**
	 * [UC0147] - Cancelar Conta Author: Raphael Rossiter Data: 23/01/2006
	 * 
	 * @param conta
	 * @throws ErroRepositorioException
	 */
	public void cancelarContaReferenciaContabilMaiorIgualSistemaParametro(
			Conta conta) throws ErroRepositorioException;

	/**
	 * [UC0148] - Colocar Conta Revisão Author: Raphael Rossiter Data:
	 * 23/01/2006
	 * 
	 * @param conta
	 * @throws ErroRepositorioException
	 */
	public void colocarContaRevisao(Conta conta)
			throws ErroRepositorioException;

	/**
	 * [UC0149] - Retirar Conta Revisão Author: Raphael Rossiter Data:
	 * 23/01/2006
	 * 
	 * @param conta
	 * @throws ErroRepositorioException
	 */
	public void retirarContaRevisao(Conta conta)
			throws ErroRepositorioException;

	/**
	 * [UC0151] - Alterar Vencimento Conta Author: Raphael Rossiter Data:
	 * 23/01/2006
	 * 
	 * @param conta
	 * @throws ErroRepositorioException
	 */
	public void alterarVencimentoConta(Conta conta)
			throws ErroRepositorioException;

	public Collection<Integer> pesquisarQuadras(Integer rotaId)
			throws ErroRepositorioException;

	/**
	 * [UC0113 - Faturar Grupo Faturamento] Author: Leonardo Vieira, Rafael
	 * Santos DAta: 17/02/2006 Remove o Resumo Faturamento Simulçao
	 * 
	 * @param idFaturamentoGrupo
	 *            Id do Faturamento Grupo
	 * @param anoMesReferencia
	 *            Ano Mes Referencia
	 * @exception ErroRepositorioException
	 *                Erro de Hibernate
	 */
	public void deletarResumoFaturamentoSimulacao(Integer idFaturamentoGrupo,
			Integer anoMesReferencia, Integer idRota)
			throws ErroRepositorioException;

	public Object pesquisarFaturamentoAtividadeCronogramaDataRealizacao(
			Integer faturamentoGrupoId, Integer faturamentoAtividadeId)
			throws ErroRepositorioException;

	public Collection pesquisarDebitosACobrar(Integer imovelId,
			Integer debitoCreditoSituacaoAtualId)
			throws ErroRepositorioException;

	public Collection pesquisarCreditoARealizar(Integer imovelId,
			Integer debitoCreditoSituacaoAtualId, int anoMesFaturamento)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Metódo responsável por inserir um
	 * objeto de ResumoFaturamento no sistema
	 * 
	 * @param resumoFaturamento
	 *            Objeto de resumo de faturamento que vai ser inserido
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public void inserirResumoFaturamentoAnoMesReferencia(
			ResumoFaturamento resumoFaturamento)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 01 Retorna o valor de água
	 * acumulado, de acordo com o ano/mês de referência, a localiade, a
	 * categoria e a situação da conta igual a normal
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no Hibernate
	 */
	public ResumoFaturamento acumularValorAguaSituacaoContaNormal(
			int anoMesReferencia, int localidade, int categoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 02 Retorna o valor de esgoto
	 * acumulado, de acordo com o ano/mês de referência, a localiade, a
	 * categoria e a situação da conta igual a normal
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no Hibernate
	 */
	public ResumoFaturamento acumularValorEsgotoSituacaoContaNormal(
			int anoMesReferencia, int localidade, int categoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 03 e 04 Retorna uma coleção
	 * de débitos a cobrar por ano e mês de referência, por gerência regional,
	 * localidade e categoria
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @param idLancamentoItemContabil
	 *            Código do itemde lançamento contábil
	 * @return retorna a coleção pesquisada de acordo com os parâmetros
	 *         informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoNormalFinanciamentoServico(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 05 Retorna o valor de guia
	 * de pagamento acumulado, de acordo com o ano/mês de referência, a situação
	 * da conta igual a normal e o tipo de financiamento igual a serviço
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection acumularValorGuiaPagamentoSituacaoNormalFinanciamentoServico(
			int anoMesReferencia, int localidade, int categoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 07 e 08 Retorna uma coleção
	 * de débitos a cobrar por ano e mês de referência, por gerência regional,
	 * localidade e categoria
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @param idLancamentoItemContabil
	 *            Código de lançamento de item contábil
	 * @return retorna a coleção pesquisada de acordo com os parâmetros
	 *         informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoCanceladoFinanciamentoServico(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 09 Retorna o valor de água
	 * acumulado, de acordo com o ano/mês de referência, a situação da conta
	 * igual a cancelada
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public BigDecimal acumularValorAguaSituacaoCancelada(int anoMesReferencia,
			int idLocalidade, int idCategoria) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 10 Retorna o valor de esgoto
	 * acumulado, de acordo com o ano/mês de referência, a situação da conta
	 * igual a cancelada
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public BigDecimal acumularValorEsgotoSituacaoCancelada(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 11 Retorna o valor de débito
	 * acumulado, de acordo com o ano/mês de referência, a situação da conta
	 * igual a cancelada e o tipo de financiamento igual a serviço
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public BigDecimal acumularValorDebitoTipoFinanciamentoServicoSituacaoCancelada(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 12 Retorna o valor de débito
	 * acumulado, de acordo com o ano/mês de referência, a situação da conta
	 * igual a cancelada e o tipo de financiamento igual a parcelamento de água
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public BigDecimal acumularValorDebitoTipoFinanciamentoParcelamentoAguaSituacaoCancelada(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 13 Retorna o valor de débito
	 * acumulado, de acordo com o ano/mês de referência, a situação da conta
	 * igual a cancelada e o tipo de financiamento igual a parcelamento de
	 * esgoto
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public BigDecimal acumularValorDebitoTipoFinanciamentoParcelamentoEsgotoSituacaoCancelada(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 14 Retorna o valor de débito
	 * acumulado, de acordo com o ano/mês de referência, a situação da conta
	 * igual a cancelada e o tipo de financiamento igual a parcelamento de
	 * serviço
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public BigDecimal acumularValorDebitoTipoFinanciamentoParcelamentoServicoSituacaoCancelada(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 15 Retorna o valor de débito
	 * acumulado, de acordo com o ano/mês de referência, a situação da conta
	 * igual a cancelada e o tipo de financiamento igual a juros de parcelamento
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public BigDecimal acumularValorDebitoTipoFinanciamentoJurosParcelamentoSituacaoCancelada(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 16 Retorna o valor de água
	 * acumulado, de acordo com o ano/mês de referência, a situação da conta
	 * igual a incluída
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public BigDecimal acumularValorAguaSituacaoIncluida(int anoMesReferencia,
			int idLocalidade, int idCategoria) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 17 Retorna o valor de esgoto
	 * acumulado, de acordo com o ano/mês de referência, a situação da conta
	 * igual a incluída
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public BigDecimal acumularValorEsgotoSituacaoIncluida(int anoMesReferencia,
			int idLocalidade, int idCategoria) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 20 Retorna o valor de
	 * categoria de débito acumulado, de acordo com o ano/mês de referência, a
	 * situação da conta igual a normal e o tipo de financiamento igual a
	 * serviço
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection acumularValorCategoriaDebitoTipoFinanciamentoServicoSituacaoNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 21 Retorna o valor de
	 * categoria de débito acumulado, de acordo com o ano/mês de referência, a
	 * situação da conta igual a normal e o tipo de financiamento igual a
	 * serviço, quando o número de prestações cobradas for maior que 11(onze)
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection acumularValorCategoriaDebitoTipoFinanciamentoServicoSituacaoNormalNumeroPrestacoesCobradasMaiorQue11(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 22 e 23 Retorna uma coleção
	 * de débito a cobrar , de acordo com o ano/mês de referência, a situação
	 * igual a normal e o grupo de parcelamento igual a documentos emitidos
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoNormalGrupoParcelamentoDocumentosEmitidos(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 24 e 25 Retorna uma coleção
	 * de débito a cobrar , de acordo com o ano/mês de referência, a situação
	 * igual a normal e o grupo de parcelamento igual a financiamentos a cobrar
	 * de curto prazo
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoNormalGrupoParcelamentoFinanciamentosACobrarCurtoPrazo(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 26 e 27 Retorna uma coleção
	 * de débito a cobrar de acordo com o ano/mês de referência, a situação
	 * igual a normal e o grupo de parcelamento igual a financiamentos a cobrar
	 * de longo prazo
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoNormalGrupoParcelamentoFinanciamentosACobrarLongoPrazo(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 28 e 29 Retorna uma coleção
	 * de débito a cobrar, de acordo com o ano/mês de referência, a situação
	 * igual a normal e o grupo de parcelamento igual a parcelamentos a cobrar
	 * de curto prazo
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoNormalGrupoParcelamentoParcelamentosACobrarCurtoPrazo(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 30 e 31 Retorna uma coleção
	 * de débito a cobrar, de acordo com o ano/mês de referência, a situação
	 * igual a normal e o grupo de parcelamento igual a parcelamentos a cobrar a
	 * longo prazo
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoNormalGrupoParcelamentoParcelamentosACobrarLongoPrazo(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 32 e 33 Retorna uma coleção
	 * de débitos a cobrar, de acordo com o ano/mês de referência, a situação
	 * igual a normal e o grupo de parcelamento igual a juros cobrados
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoNormalGrupoParcelamentoJurosCobrados(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 34 e 35 Retorna uma coleção
	 * de débito a cobrar, de acordo com o ano/mês de referência, a situação
	 * igual a cancelado por parcelamento e o grupo de parcelamento igual a
	 * juros cobrados
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoCanceladoPorParcelamentoGrupoParcelamentoJurosCobrados(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 36 Retorna o valor de débito
	 * acumulado, de acordo com o ano/mês de referência, a situação igual a
	 * normal e o tipo de financiamento igual a arrasto de água ou arrasto de
	 * esgoto ou arrasto de serviço
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection acumularValorDebitoSituacaoNormalTipoFinanciamentoArrastoAguaArrastoEsgotoArrastoServico(
			int anoMesReferencia, Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 37 e 38 Retorna uma coleção
	 * de débito a cobrar, de acordo com o ano/mês de referência, a situação
	 * igual a cancelado e o tipo de financiamento igual a parcelamento de água
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoCanceladoTipoFinanciamentoParcelamentoAgua(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 39 e 40 Retorna uma coleção
	 * de débito a cobrar, de acordo com o ano/mês de referência, a situação
	 * igual a cancelado e o tipo de financiamento igual a parcelamento de
	 * esgoto
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoCanceladoTipoFinanciamentoParcelamentoEsgoto(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 41 e 42 Retorna uma coleção
	 * de débito a cobrar acumulado, de acordo com o ano/mês de referência, a
	 * situação igual a cancelado e o tipo de financiamento igual a parcelamento
	 * de serviço
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoCanceladoTipoFinanciamentoParcelamentoServico(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 43 e 44 Retorna uma coleção
	 * de débito a cobrar, de acordo com o ano/mês de referência, a situação
	 * igual a cancelado e o tipo de financiamento igual a juros de parcelamento
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoCanceladoTipoFinanciamentoJurosParcelamento(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 45 Retorna o valor de
	 * categoria de débito cobrado acumulado, de acordo com o ano/mês de
	 * referência, a situação igual a normal e o tipo de financiamento igual a
	 * parcelamento de água
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public ResumoFaturamento acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoAguaSituacaoNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 46 Retorna o valor de
	 * categoria de débito cobrado acumulado, de acordo com o ano/mês de
	 * referência, a situação igual a normal e o tipo de financiamento igual a
	 * parcelamento de esgoto
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public ResumoFaturamento acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoEsgotoSituacaoNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 47 Retorna o valor de
	 * categoria de débito cobrado acumulado, de acordo com o ano/mês de
	 * referência, a situação igual a normal e o tipo de financiamento igual a
	 * parcelamento de serviços
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoServicosSituacaoNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 48 Retorna o valor de
	 * categoria de débito cobrado acumulado, de acordo com o ano/mês de
	 * referência, a situação igual a normal e o tipo de financiamento igual a
	 * juros de parcelamento
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public ResumoFaturamento acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoJurosParcelamentoSituacaoNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 49 Retorna o valor de
	 * categoria de débito cobrado acumulado, de acordo com o ano/mês de
	 * referência, a situação igual a normal e o tipo de financiamento igual a
	 * juros de parcelamento e a diferença de prestações maior que 11(onze)
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public ResumoFaturamento acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoJurosParcelamentoSituacaoNormalDiferencaPrestacoesMaiorQue11(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 50 Retorna o valor de
	 * categoria de débito cobrado acumulado, de acordo com o ano/mês de
	 * referência, a situação igual a normal e o tipo de financiamento igual a
	 * arrasto de água
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public ResumoFaturamento acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoArrastoAguaSituacaoNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 51 Retorna o valor de
	 * categoria de débito cobrado acumulado, de acordo com o ano/mês de
	 * referência, a situação igual a normal e o tipo de financiamento igual a
	 * arrasto de esgoto
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public ResumoFaturamento acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoArrastoEsgotoSituacaoNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 52 Retorna o valor de
	 * categoria de débito cobrado acumulado, de acordo com o ano/mês de
	 * referência, a situação igual a normal e o tipo de financiamento igual a
	 * arrasto de serviço
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoArrastoServicoSituacaoNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 53 Retorna o valor de
	 * categoria de credito realizado acumulado, de acordo com o ano/mês de
	 * referência, a situação igual a normal e a origem do crédito igual a
	 * contas pagas em duplicidade ou em excesso
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public ResumoFaturamento acumularValorCategoriaCreditoRealizadoCategoriaOrigemCreditoContasPagasEmDuplicidadeEmExcessoSituacaoContaNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 54 Retorna o valor de
	 * categoria de crédito realizado acumulado, de acordo com o ano/mês de
	 * referência, a situação de conta igual a normal e a origem do crédito
	 * igual a devolução de tarifa de água
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public ResumoFaturamento acumularValorCategoriaCreditoRealizadoCategoriaOrigemCreditoDevolucaoTarifaAguaSituacaoContaNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 55 Retorna o valor de
	 * categoria de crédito realizado acumulado, de acordo com o ano/mês de
	 * referência, a situação de conta igual a normal e a origem do crédito
	 * igual a devolução de tarifa de esgoto
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public ResumoFaturamento acumularValorCategoriaCreditoRealizadoCategoriaOrigemCreditoDevolucaoTarifaEsgotoSituacaoContaNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 56 Retorna o valor de
	 * categoria de crédito realizado acumulado, de acordo com o ano/mês de
	 * referência, a situação de conta igual a normal e a origem do crédito
	 * igual a serviços indiretos pagos indevidamente
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection acumularValorCategoriaCreditoRealizadoCategoriaOrigemCreditoServicosIndiretosPagosIndevidamenteSituacaoContaNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 57 Retorna o valor de
	 * categoria de crédito realizado acumulado, de acordo com o ano/mês de
	 * referência, a situação de conta igual a normal e a origem do crédito
	 * igual a devolução de juros de parcelamento
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public ResumoFaturamento acumularValorCategoriaCreditoRealizadoCategoriaOrigemCreditoDevolucaoJurosParcelamentoSituacaoContaNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 59 Retorna o valor de
	 * imposto de renda acumulado, de acordo com o ano/mês de referência, a
	 * situação de conta igual a normal e a categoria igual a pública
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection acumularValor_IR_SituacaoContaNormalCategoriaPublica(
			int anoMesReferencia, Integer idLocalidade, Integer idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 60 Retorna o valor de cofins
	 * acumulado, de acordo com o ano/mês de referência, a situação de conta
	 * igual a normal e a categoria igual a pública
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection acumularValor_COFINS_SituacaoContaNormalCategoriaPublica(
			int anoMesReferencia, Integer idLocalidade, Integer idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 61 Retorna o valor de csll
	 * acumulado, de acordo com o ano/mês de referência, a situação de conta
	 * igual a normal e a categoria igual a pública
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection acumularValor_CSLL_SituacaoContaNormalCategoriaPublica(
			int anoMesReferencia, Integer idLocalidade, Integer idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 62 Retorna o valor de pis e
	 * pasep acumulado, de acordo com o ano/mês de referência, a situação de
	 * conta igual a normal e a categoria igual a pública
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection acumularValor_PIS_PASEP_SituacaoContaNormalCategoriaPublica(
			int anoMesReferencia, Integer idLocalidade, Integer idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês [SB0001] - acumula o valor de
	 * débito cobrado para situação de conta igual a cancelada por retificação
	 * de acordo com o ano/mês de referência
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public BigDecimal pesquisarSomaValorAguaSituacaoCanceladaPorRetificacao(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês [SB0001] - acumula o valor de
	 * débito cobrado para situação de conta igual a retificada de acordo com o
	 * ano/mês de referência
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public BigDecimal pesquisarSomaValorAguaSituacaoRetificada(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês [SB0001] - acumula o valor de
	 * débito cobrado para situação de conta igual a cancelada por retificação
	 * de acordo com o ano/mês de referência
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @param tipoFinanciamento
	 *            Tipo de Financiamento
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public BigDecimal pesquisarSomaValorDebitoCobradoParcelamentoAguaSituacaoCanceladaPorRetificacao(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês [SB0001] - acumula o valor de
	 * débito cobrado para situação de conta igual a retificada de acordo com o
	 * ano/mês de referência
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @param tipoFinanciamento
	 *            Tipo de Financiamento
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public BigDecimal pesquisarSomaValorDebitoCobradoParcelamentoAguaSituacaoRetificada(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês [SB0001] - acumula o valor de guia
	 * de pagamento para situação de conta igual a cancelada de acordo com o
	 * ano/mês de referência
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @param tipoFinanciamento
	 *            Tipo de Financiamento
	 * @param itemContabil
	 *            Item Contábil
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public BigDecimal pesquisarSomaValorEsgotoSituacaoCanceladaPorRetificacao(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês [SB0001] - pesquisar a soma do
	 * valor de água para situação de conta igual a cancelada por retificação de
	 * acordo com o ano/mês de referência
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public BigDecimal pesquisarSomaValorEsgotoSituacaoRetificada(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês [SB0001] - pesquisar a soma do
	 * valor de esgoto para situação de conta igual a retificada de acordo com o
	 * ano/mês de referência
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public BigDecimal acumularValorGuiaPagamentoSituacaoCancelada(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer tipoFinanciamento, Integer itemContabil)
			throws ErroRepositorioException;

	// item 4
	/**
	 * [UC0155] - Encerrar Faturamento do Mês Item 04 - atualizar situação de
	 * imóvel com faturamento finalizado
	 * 
	 * @param anoMesFaturamento
	 *            Ano e mês de referência do faturamento
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public void atualizarImoveisSituacaoEspecialFaturamentoFinalizada(
			int anoMesFaturamento, Integer idSetorComercial)
			throws ErroRepositorioException;

	// item 5
	/**
	 * [UC0155] - Encerrar Faturamento do Mês Item 05 - pesquisar contas
	 * canceladas
	 * 
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Conta> pesquisarContasCanceladasPorMesAnoReferenciaContabil(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Item 05 - pesquisar debitos
	 * cobrados de contas canceladas
	 * 
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @param idConta
	 *            Código da conta
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<DebitoCobrado> pesquisarDebitosCobradosCanceladosPorMesAnoReferenciaContabil(
			int anoMesReferenciaContabil, Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Item 05 - pesquisar créditos
	 * realizados de contas canceladas
	 * 
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @param idConta
	 *            Código da conta
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<CreditoRealizado> pesquisarCreditosRealizadosCanceladosPorMesAnoReferenciaContabil(
			int anoMesReferenciaContabil, Integer idConta)
			throws ErroRepositorioException;

	// fim item 5

	// item 6
	/**
	 * [UC0155] - Encerrar Faturamento do Mês Item 06 - pesquisar débitos a
	 * cobrar cancelados
	 * 
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<DebitoACobrar> pesquisarDebitosACobrarCanceladosPorMesAnoReferenciaContabil(
			int anoMesReferenciaContabil, Integer idSetorComercial)
			throws ErroRepositorioException;

	// item 7
	/**
	 * [UC0155] - Encerrar Faturamento do Mês Item 07 - pesquisar créditos a
	 * realizar cancelados
	 * 
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<CreditoARealizar> pesquisarCreditosARealizarCanceladosPorMesAnoReferenciaContabil(
			int anoMesReferenciaContabil, Integer idSetorComercial)
			throws ErroRepositorioException;

	// item 8
	/**
	 * [UC0155] - Encerrar Faturamento do Mês Item 08 - atualizar ano mÊs de
	 * referência do faturamento de acordo com o ano/mês de referência
	 * 
	 * @param anoMesFaturamentoAtual
	 *            Ano e mês de referência do faturamento atual
	 * @param anoMesFaturamentoNovo
	 *            Ano e mês de referência do faturamento anterior
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public void atualizarAnoMesfaturamento(int anoMesFaturamentoAtual,
			int anoMesFaturamentoNovo) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Pesquisa uma coleção de resumos
	 * defaturamento por ano e mês de referência
	 * 
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<ResumoFaturamento> pesquisarResumoFaturamentoPorAnoMes(
			int anoMesFaturameto, Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * [UC0150] - Retificar Conta Author: Raphael Rossiter Data: 23/01/2006
	 * 
	 * @param conta
	 * @throws ErroRepositorioException
	 */
	public void retificarContaAtualizarSituacao(Conta conta,
			Integer situacaoAnterior) throws ErroRepositorioException;

	/**
	 * [UC0150] - Retificar Conta Author: Raphael Rossiter Data: 23/01/2006
	 * 
	 * @param conta
	 * @throws ErroRepositorioException
	 */
	public void retificarContaAtualizarValores(Conta conta)
			throws ErroRepositorioException;

	/**
	 * [UC0150] - Retificar Conta Author: Raphael Rossiter Data: 23/01/2006
	 * 
	 * @param conta
	 * @throws ErroRepositorioException
	 */
	public void removerContaCategoria(Conta conta)
			throws ErroRepositorioException;

	/**
	 * [UC0150] - Retificar Conta Author: Raphael Rossiter Data: 23/01/2006
	 * 
	 * @param conta
	 * @throws ErroRepositorioException
	 */
	public void removerDebitoCobrado(Conta conta)
			throws ErroRepositorioException;

	/**
	 * [UC0150] - Retificar Conta Author: Raphael Rossiter Data: 23/01/2006
	 * 
	 * @param conta
	 * @throws ErroRepositorioException
	 */
	public void removerCreditoRealizado(Conta conta)
			throws ErroRepositorioException;

	/**
	 * [UC0113] - Faturar Grupo Faturamento Author: Rafael Santos Data:
	 * 13/01/2006 Consultar os Debitos a Cobrar CAtegoria do Debito a Cobrar
	 * 
	 * @param debitoACobrarCategoriaID
	 *            Id do Debito a Cobrar Categoria
	 * @return Coleção de Debitos a Cobrar Categoria
	 * @exception ErroRepositorioException
	 */
	public Collection pesquisarDebitosACobrarCategoria(Integer debitoACobrarID)
			throws ErroRepositorioException;

	/**
	 * [UC0113] - Faturar Grupo Faturamento Author: Rafael Santos Data:
	 * 14/01/2006 Consultar os Creditos Realizados Categoria do Credito A
	 * Realizar
	 * 
	 * @param creditoARealizarID
	 *            Id do Creditoa A Realizar
	 * @return Coleção de Creditoa a Realizar Categoria
	 * @exception ErroRepositorioException
	 */
	public Collection pesquisarCreditoRealizarCategoria(
			Integer creditoARealizarID) throws ErroRepositorioException;

	/**
	 * [UC0113] - Faturar Grupo de Faturamento
	 * 
	 * Retorna o código do débito automático.
	 * 
	 * @author Rafael Santos, Pedro Alexandre
	 * @date 16/02/2006,18/09/2006
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer obterDebitoAutomatico(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Pesquisa os dados do imóvel que será faturado
	 * 
	 * [UC0113] - Faturar Grupo Faturamento
	 * 
	 * @author Rafael Santos, Raphael Rossiter, Raphael Rossiter
	 * @date 16/02/2006, 27/03/2009, 16/08/2011
	 * 
	 * @param idRota
	 * @param numeroPaginas
	 * @param quantidadeRegistros
	 * @param preFaturar
	 * @param resumo
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImovelFaturarGrupo(Integer idImovel,
			int numeroIndice, int quantidadeRegistros, boolean preFaturar,
			boolean resumo) throws ErroRepositorioException;

	/**
	 * [UC0113] - Gerar Faturamento Grupo Author: Rafael Santos Data: 16/02/2006
	 * 
	 * Dados do Cliente Imovel
	 * 
	 * @param imovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Cliente pesquisarClienteImovelGrupoFaturamento(Integer idImovel,
			Short relacaoTipo) throws ErroRepositorioException;

	/**
	 * [UC0242] - Registrar Movimento de Arrecadadores Author: Sávio Luiz Data:
	 * 01/02/2006
	 * 
	 * retorna o objeto debito automatico movimento
	 * 
	 * @param imovel
	 * @param situacaoNormal
	 * @param situacaoIncluida
	 * @param situacaoRetificada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public DebitoAutomaticoMovimento obterDebitoAutomaticoMovimento(
			Integer idImovel, Integer anoMesReferencia)
			throws ErroRepositorioException;

	/**
	 * [UC0242] - Registrar Movimento de Arrecadadores Author: Sávio Luiz Data:
	 * 01/02/2006
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param anoMesReferencia
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Integer pesquisarExistenciaContaComSituacaoAtual(Imovel imovel,
			int anoMesReferencia) throws ErroRepositorioException;

	/**
	 * [UC0259] - Processar Pagamento com Código de Barras [SF0003] - Processar
	 * Pagamento de Documento de Cobrança Author: Sávio Luiz Data: 01/02/2006
	 * 
	 * retorna o objeto debito automatico movimento
	 * 
	 * @param imovel
	 * @param situacaoNormal
	 * @param situacaoIncluida
	 * @param situacaoRetificada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarSituacaoAtualDebitoACobrar(Integer idDebitoACobrar)
			throws ErroRepositorioException;

	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * [SF0003] - Processar Pagamento de Documento de Cobrança
	 * 
	 * @author Sávio Luiz
	 * @created 16/02/2006
	 * 
	 * @param matriculaImovel
	 *            Matrícula do Imovel
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection pesquisarFaturaItem(Integer idCliente,
			Integer anoMesReferencia, Integer numeroSequencial,
			BigDecimal valordebito) throws ErroRepositorioException;

	/**
	 * Atualiza o ano mes de faturamento de faturamento situação historico
	 * 
	 * [UC0156] Informar Situacao Especial Faturamento
	 * 
	 * @author Sávio Luiz
	 * @date 17/03/2006
	 * 
	 * @param situacaoEspecialFaturamentoHelper
	 * @throws ErroRepositorioException
	 */
	public void atualizarAnoMesFaturamentoSituacaoHistorico(
			SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper,
			Collection<Integer> colecaoIdsImoveisRetirar,
			Integer idFaturamentoSituacaoComando)
			throws ErroRepositorioException;

	/**
	 * O sistema seleciona as atividades que foram previamente comandadas e
	 * ainda não realizadas (a partir da tabela FATURAMENTO_ATIVIDADE_CRONOGRAMA
	 * com FTCM_ID = FTCM_ID da tabela FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL e
	 * FTAC_TMCOMANDO preenchido e FTAC_TMREALIZACAO não preenchido ou com um
	 * valor anterior à FTAC_TMCOMANDO)
	 * 
	 * @author Raphael Rossiter
	 * @date 29/03/2006
	 * 
	 * @param FaturamentoGrupoCronogramaMensal
	 * @return Collection<FaturamentoAtividadeCronograma>
	 * @throws ErroRepositorioException
	 */
	public Collection<FaturamentoAtividadeCronograma> pesquisarFaturamentoAtividadeCronogramaComandadaNaoRealizada(
			Integer numeroPagina) throws ErroRepositorioException;

	/**
	 * Retorna o count do resultado da pesquisa de Faturamento Atividade
	 * Cronograma
	 * 
	 * pesquisarFaturamentoAtividadeCronogramaCount
	 * 
	 * @author Roberta Costa
	 * @date 05/05/2006
	 * 
	 * @param FaturamentoGrupoCronogramaMensal
	 *            faturamentoGrupoCronogramaMensal
	 * @param Integer
	 *            numeroPagina
	 * @return Integer retorno
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarFaturamentoAtividadeCronogramaComandadaNaoRealizadaCount()
			throws ErroRepositorioException;

	/**
	 * 
	 * Método que retorna todos os imóveis que tenham cliente responsável e
	 * indicacao de conta a ser entregue em outro endereço e que estejam nas
	 * quadras pertencentes às rotas passadas
	 * 
	 * UC0209 Gerar Taxa de Entrega de Conta em Outro Endereço.
	 * 
	 * @author Thiago Toscano
	 * @date 04/04/2006
	 * 
	 * @param rotas
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterImoveisPorRotasComContaEntregaEmOutroEndereco(
			Integer idRota) throws ErroRepositorioException;

	/**
	 * Metodo que retorno o debito Tipo do id passado
	 * 
	 * Utilizado pelo [UC029] Gerar Taxa de Entrega de Conta em Outro Endereço.
	 * 
	 * @author thiago
	 * @date 05/04/2006
	 * 
	 * @param id
	 * @return
	 * @throws ErroRepositorioException
	 */
	public DebitoTipo getDebitoTipo(Integer id) throws ErroRepositorioException;

	/**
	 * 
	 * Metodo que retorno o valor da tarifa normal a ser cobrando no caso de uso
	 * [UC029]
	 * 
	 * Utilizado pelo [UC029] Gerar Taxa de Entrega de Conta em Outro Endereço.
	 * 
	 * @author thiago
	 * @date 05/04/2006
	 * 
	 * @param anoMes
	 * @return
	 */
	public BigDecimal obterValorTarifa(Integer consumoTarifaId)
			throws ErroRepositorioException;

	/**
	 * 
	 * Metodo que retorno o valor da tarifa social a ser cobrando no caso de uso
	 * [UC029]
	 * 
	 * Utilizado pelo [UC029] Gerar Taxa de Entrega de Conta em Outro Endereço.
	 * 
	 * @author thiago
	 * @date 05/04/2006
	 * 
	 * @param anoMes
	 * @return
	 */
	// public BigDecimal obterValorTarifaSocial(Integer anoMes)
	// throws ErroRepositorioException;

	/**
	 * Metodo que retorno o debito Tipo do id passado
	 * 
	 * Utilizado pelo [UC029] Gerar Taxa de Entrega de Conta em Outro Endereço.
	 * 
	 * @author thiago
	 * @date 05/04/2006
	 * 
	 * @param id
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] getDebitoTipoHql(Integer id)
			throws ErroRepositorioException;

	/**
	 * Metodo que retorna o id debito a cobrar, o id do imóvel, o id do debito
	 * tipo e o ano/mês de referência do débito
	 * 
	 * Utilizado pelo [UC029] Gerar Taxa de Entrega de Conta em Outro Endereço.
	 * 
	 * @author Rafael Corrêa e Leonardo Vieira
	 * @date 24/08/2006
	 * 
	 * @param idImovel
	 *            , idDebitoTipo, anoMesReferenciaDebito
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDebitoACobrar(Integer idImovel,
			Integer idDebitoTipo, Integer anoMesReferenciaDebito)
			throws ErroRepositorioException;

	/**
	 * Metodo que deleta os débitos a cobrar categoria de um respectivo débito a
	 * cobrar
	 * 
	 * Utilizado pelo [UC029] Gerar Taxa de Entrega de Conta em Outro Endereço.
	 * 
	 * @author Rafael Corrêa e Leonardo Vieira
	 * @date 24/08/2006
	 * 
	 * @param idDebitoACobrar
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarDebitoACobrarCategoria(Integer idDebitoACobrar)
			throws ErroRepositorioException;

	/**
	 * Metodo que insere ou atualiza os débitos a cobrar
	 * 
	 * Utilizado pelo [UC029] Gerar Taxa de Entrega de Conta em Outro Endereço.
	 * 
	 * @author Rafael Corrêa, Leonardo Vieira, Pedro Alexandre
	 * @date 24/08/2006
	 * 
	 * @param colecaoDebitosACobrar
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<DebitoACobrar> insereOuAtualizaDebitoACobrar(
			Collection colecaoDebitosACobrar) throws ErroRepositorioException;

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * [UC0209] Gerar Taxa de Entrega de Conta em Outro Endereço
	 * 
	 * @author Pedro Alexandre
	 * @date 29/08/2006
	 * 
	 * @param colecaoDebitosACobrarCategoria
	 * @throws ErroRepositorioException
	 */
	public void inserirDebitoACobrarCategoria(
			Collection colecaoDebitosACobrarCategoria)
			throws ErroRepositorioException;

	/**
	 * 
	 * Metodo que retorna os imóveis das quadras pertencentes às rotas
	 * 
	 * Utilizado pelo [UC0302] Gerar Débitos a Cobrar de Acréscimos por
	 * Impontualidade
	 * 
	 * @author fernanda paiva, Raphael Rossiter
	 * @date 20/04/2006, 26/08/2009
	 * 
	 * @param idRota
	 * @return Collection
	 */
	public Collection pesquisarImoveisDasQuadrasPorRota(Integer idRota)
			throws ErroRepositorioException;

	/**
	 * [UC0302] - Gerar Debitos a Cobrar de Acréscimos por Impontualidade
	 * Author: Fernanda Paiva Data: 25/04/2006
	 * 
	 * Obtém os pagamentos da conta que contem a menor data de pagamento
	 * 
	 * @param conta
	 * @return
	 */
	public Date obterPagamentoContasMenorData(Integer conta, Integer idImovel,
			Integer anoMesReferenciaConta) throws ErroRepositorioException;

	/**
	 * [UC0302] Gerar Débito a Cobrar de Acrescimos por Impontualidade [SB0001]
	 * Gerar Débito a Cobrar
	 * 
	 * @author Fernanda Paiva
	 * @created 25/04/2006
	 * 
	 * @param valor
	 *            da multa/juros/atualização monetaria
	 * @param ano
	 *            /mes referencia
	 * @param tipo
	 *            debito
	 * 
	 * @return
	 */
	public Object gerarDebitoACobrar(DebitoACobrar debitoACobrar)
			throws ErroRepositorioException;

	/**
	 * [UC0302] - Gerar Debitos a Cobrar de Acréscimos por Impontualidade
	 * Author: Fernanda Paiva Data: 26/04/2006
	 * 
	 * Obtém os pagamentos da conta que contem a menor data de pagamento
	 * 
	 * @param conta
	 * @return
	 */
	public Object[] obterDebitoTipo(Integer debitoTipo)
			throws ErroRepositorioException;

	/**
	 * [UC0302] - Gerar Debitos a Cobrar de Acréscimos por Impontualidade
	 * Author: Fernanda Paiva Data: 27/04/2006
	 * 
	 * Atualiza o indicador de cobranca de multa na tabela de Conta
	 * 
	 * @param conta
	 * @return
	 */

	public void atualizarIndicadorMultaDeConta(
			Collection<Integer> colecaoIdsContas)
			throws ErroRepositorioException;

	/**
	 * [UC0302] - Gerar Debitos a Cobrar de Acréscimos por Impontualidade
	 * Author: Fernanda Paiva Data: 27/04/2006
	 * 
	 * Atualiza o indicador de cobranca de multa na tabela de Guia Pagamento
	 * 
	 * @param guia
	 * @return
	 */

	public void atualizarIndicadorMultaDeGuiaPagamento(
			Collection<Integer> colecaoIdsGuiasPagamento)
			throws ErroRepositorioException;

	/**
	 * Seleciona os relacionamentos entre o cliente e os imóveis de acordo com o
	 * código do cliente responsável
	 * 
	 * [UC0320] Gerar Fatura de Cliente Responsável
	 * 
	 * @author Pedro Alexandre
	 * @date 25/04/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarClienteImovelPorClienteResponsavel(
			Integer idCliente) throws ErroRepositorioException;

	/**
	 * Pesquisa todos os clientes responsáveis na tabela de ClienteImovel para
	 * tipo de relação igual a responsável e data de fim de relação iguala a
	 * nula
	 * 
	 * [UC0320] Gerar Fatura de Cliente Responsável
	 * 
	 * @author Pedro Alexandre
	 * @date 25/04/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarClientesResponsaveis(
			SistemaParametro sistemaParametro, Integer numeroIndice,
			Integer quantidadeRegistros) throws ErroRepositorioException;

	/**
	 * Pesquisa todos os clientes responsáveis na tabela de ClienteImovel para
	 * tipo de relação igual a responsável e data de fim de relação igual a nula
	 * e com indicador faturas agrupadas igual a 1
	 * 
	 * [UC0320] Gerar Fatura de Cliente Responsável
	 * 
	 * @author Rafael Corrêa
	 * @date 04/02/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarClientesResponsaveisFaturamentoAntecipado(
			SistemaParametro sistemaParametro, Integer numeroIndice,
			Integer quantidadeRegistros) throws ErroRepositorioException;

	/**
	 * Pesquisa a coleção de contas para os imóveis do cliente responsável para
	 * o ano/mês de referÊncia igual ao ano/mês de referÊncia corrente e a
	 * situação da conta igual a Normal ou Retificada ou Incluída
	 * 
	 * [UC0320] Gerar Fatura de Cliente Responsável
	 * 
	 * @author Pedro Alexandre
	 * @date 25/04/2006
	 * 
	 * @param idsConcatenadosImoveis
	 * @param anoMesReferenciaConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Conta> pesquisarContaImovelResponsabilidadeCliente(
			Collection idsConcatenadosImoveis, Integer anoMesReferenciaConta)
			throws ErroRepositorioException;

	/**
	 * Retorna um Object contendo um array de object com três posições contendo
	 * na primeira posição a soma do valor total das contas na segunda posição a
	 * maior data de vencimento das contas e na terceira posiçãoa maior data de
	 * validade das contas esses dados são necessários para gerar a fatura
	 * 
	 * [UC0320] Gerar Fatura de Cliente Responsável
	 * 
	 * @author Pedro Alexandre
	 * @date 25/04/2006
	 * 
	 * @param idsConcatenadosImoveis
	 * @param anoMesReferenciaConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarResumoContasClienteResponsavel(
			Collection idsConcatenadosImoveis, Integer anoMesReferenciaConta)
			throws ErroRepositorioException;

	/**
	 * Pesquisa os items da fatura informada com o código da fatura igua ao
	 * código da fatura dos items para emitir a fatura do cliente responsável
	 * 
	 * [UC0321] Emitir Fatura de Cliente Responsável
	 * 
	 * @author Pedro Alexandre
	 * @date 28/04/2006
	 * 
	 * @param idFatura
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<FaturaItem> pesquisarItemsFatura(Integer idFatura)
			throws ErroRepositorioException;

	/**
	 * [UC0329] - Restabelecer Situação Anterior da Conta Author: Fernanda Paiva
	 * Date: 05/05/2006
	 * 
	 * Atualiza a situacao anterior da conta de situacao atual cancelada
	 * 
	 * @param idConta
	 * @return
	 */

	public void restabelecerSituacaoAnteriorContaCancelada(String idConta)
			throws ErroRepositorioException;

	/**
	 * [UC0329] - Restabelecer Situação Anterior da Conta Author: Fernanda Paiva
	 * Date: 05/05/2006
	 * 
	 * Atualiza a situacao anterior da conta de situacao atual cancelada
	 * 
	 * @param idConta
	 * @return
	 */

	public void alterarSituacaoConta(String idConta, Integer situacao)
			throws ErroRepositorioException;

	/**
	 * [UC0329] - Alterar as Situação Anterior e atual da Conta Author: Fernanda
	 * Paiva Date: 05/05/2006
	 * 
	 * Atualiza a situacao anterior da conta de situacao atual da conta
	 * 
	 * @param idConta
	 *            situacaoAnterior situacaoAtual
	 * @return
	 */

	public void alterarSituacaoAnteriorAtualConta(String idConta,
			Integer situacaoAnterior, Integer situacaoAtual)
			throws ErroRepositorioException;

	/**
	 * 
	 * Permite FAturar um conjunto de rotas de um grupo de faturamento
	 * 
	 * [UC0113] - Faturar Grupo de Faturaumetno
	 * 
	 * Determinar VAlores para Faturamento de Água e/ou Esgoto
	 * 
	 * [SF0002] - Determinar VAlores para Faturamento de Água e/ou Esgoto
	 * 
	 * @author Rafael Santos
	 * @date 26/04/2006
	 * 
	 * @param faturamentoGrupoId
	 * @param faturamentoAtividadeId
	 * @param anoMesReferencia
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarFaturamentoAtividadeCronogramaDataRealizacao(
			Integer faturamentoGrupoId, Integer faturamentoAtividadeId,
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * Atualizar Debito a Cobrar Campo numero de prestações cobradas
	 * 
	 * [UC00113] - Faturar Grupo de Faturamento
	 * 
	 * @author Rafael Santos
	 * @date 03/05/2006
	 * 
	 * @param idDebitoAcobrar
	 * @param prestacaoCobrada
	 * @throws ErroRepositorioException
	 */
	public void atualizarDebitoAcobrar(DebitoACobrar debitoACobrar)
			throws ErroRepositorioException;

	/**
	 * Atualizar Debito a Cobrar Campo numero de prestações cobradas
	 * 
	 * [UC00113] - Faturar Grupo de Faturamento
	 * 
	 * @author Pedro Alexandre
	 * @date 15/09/2006
	 * 
	 * @param colecaoDebitosACobrar
	 * @throws ErroRepositorioException
	 */
	public void atualizarDebitoACobrar(List colecaoDebitosACobrar)
			throws ErroRepositorioException;

	/**
	 * Atualizar Credito a Realizar Campo numero de prestações cobradas
	 * 
	 * [UC00113] - Faturar Grupo de Faturamento
	 * 
	 * @author Rafael Santos
	 * @date 03/05/2006
	 * 
	 * @param idDebitoAcobrar
	 * @param prestacaoCobrada
	 * @throws ErroRepositorioException
	 */
	public void atualizarCreditoARealizar(CreditoARealizar creditoARealizar)
			throws ErroRepositorioException;

	/**
	 * Obtem a Ligacao Esgoto do Imovel
	 * 
	 * @author Rafael Santos
	 * @date 05/05/2006
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public LigacaoEsgoto obterLigacaoEsgotoImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Recupera as contas com estouro de consumo ou com baixo consumo [UC0348] -
	 * Emitir Contas
	 * 
	 * @author Sávio Luiz
	 * @date 15/05/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasEmitir(Collection<Integer> idTipoConta,
			Integer idEmpresa, Integer numeroPaginas, Integer anoMesReferencia,
			Integer idFaturamentoGrupo,
			Integer anoMesReferenciaFaturamentoAntecipado,
			Integer imovelContaEnvio) throws ErroRepositorioException;

	/**
	 * Recupera as contas com debito automático [UC0348] - Emitir Contas
	 * 
	 * @author Sávio Luiz
	 * @date 15/05/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasDebitoAutomatico(Collection idsContas)
			throws ErroRepositorioException;

	/**
	 * Recupera as contas com entrega para o cliente responsável [UC0348] -
	 * Emitir Contas
	 * 
	 * @author Sávio Luiz
	 * @date 15/05/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasClienteResponsavel(
			Collection<Integer> idTipoConta, Integer numeroPaginas,
			Integer anoMesReferencia, Integer idFaturamentoGrupo,
			Short indicadorEmissaoExtratoFaturamento,
			Integer anoMesReferenciaFaturamentoAntecipado,
			Integer imovelContaEnvio) throws ErroRepositorioException;

	/**
	 * Recupera as contas normais [UC0348] - Emitir Contas
	 * 
	 * @author Sávio Luiz
	 * @date 15/05/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasNormais(Collection idsContas)
			throws ErroRepositorioException;

	/**
	 * Recupera o nome do cliente usuário pela conta [UC0348] - Emitir Contas
	 * 
	 * @author Sávio Luiz
	 * @date 15/05/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String pesquisarNomeClienteUsuarioConta(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Recupera o id do cliente responsável pela conta [UC0348] - Emitir Contas
	 * 
	 * @author Sávio Luiz
	 * @date 15/05/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdClienteResponsavelConta(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Método que retorna uma colecao de categorias
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0007] Obter Quantidade de Economias da Conta
	 * 
	 * @author Sávio Luiz
	 * @date 19/05/2006
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Short obterQuantidadeEconomiasConta(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Método que retorna uma colecao de conta categoria
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0011] Obter Quantidade de Economias da Conta
	 * 
	 * @author Sávio Luiz
	 * @date 19/05/2006
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<ContaCategoria> pesquisarContaCategoria(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Método que retorna uma colecao de conta categoria
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0011] Obter Quantidade de Economias da Conta
	 * 
	 * @author Sávio Luiz
	 * @date 19/05/2006
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContaCategoriaFaixas(Integer idConta,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * Método que retorna uma arrey de object com a soma do valor dos debitos
	 * cobrados de parcelamento,o numero da prestacao e o numero total de
	 * prestações
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0013] Gerar Linhas dos Débitos Cobrados
	 * 
	 * @author Sávio Luiz
	 * @date 19/05/2006
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarParmsDebitoAutomatico(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Método que retorna uma arrey de object do debito ordenado pelo tipo de
	 * debito
	 * 
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0013] Gerar Linhas dos Débitos Cobrados
	 * 
	 * @author Sávio Luiz
	 * @date 19/05/2006
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List pesquisarParmsDebitoCobradoPorTipo(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Método que retorna uma arrey de object do crédito realizado ordenado pelo
	 * tipo de crédito
	 * 
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0013] Gerar Linhas dos Débitos Cobrados
	 * 
	 * @author Sávio Luiz
	 * @date 19/05/2006
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List pesquisarParmsCreditoRealizadoPorTipo(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Método que retorna uma arrey de object do conta mensagem ordenado pelo
	 * tipo de crédito
	 * 
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0013] Gerar Linhas dos Débitos Cobrados
	 * 
	 * @author Sávio Luiz
	 * @date 19/05/2006
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarParmsContaMensagem(
			EmitirContaHelper emitirContaHelper, Integer idFaturamentoGrupo,
			Integer idGerenciaRegional, Integer idLocalidade,
			Integer idSetorComercial) throws ErroRepositorioException;

	/**
	 * Método que retorna uma array de object de qualidade de agua
	 * 
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * @author Sávio Luiz
	 * @date 25/05/2006
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarParmsQualidadeAgua(
			EmitirContaHelper emitirContaHelper)
			throws ErroRepositorioException;

	/**
	 * Método que retorna uma array de object do conta impostos deduzidos
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0015] Gerar Linhas dos Impostos Deduzidos
	 * 
	 * @author Sávio Luiz
	 * @date 19/05/2006
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarParmsContaImpostosDeduzidos(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Pesquisa todas as contas para testar o batch
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 02/06/2006
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIdsTodasConta() throws ErroRepositorioException;

	public Collection gerarRelacaoAcompanhamentoFaturamento(
			String idImovelCondominio, String idImovelPrincipal,
			String idNomeConta, String idSituacaoLigacaoAgua,
			String consumoMinimoInicialAgua, String consumoMinimoFinalAgua,
			String idSituacaoLigacaoEsgoto, String consumoMinimoInicialEsgoto,
			String consumoMinimoFinalEsgoto,
			String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,

			String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal,

			String idImovelPerfil, String idPocoTipo,
			String idFaturamentoSituacaoTipo, String idCobrancaSituacaoTipo,
			String idSituacaoEspecialCobranca, String idEloAnormalidade,
			String areaConstruidaInicial, String areaConstruidaFinal,
			String idCadastroOcorrencia, String idConsumoTarifa,
			String idGerenciaRegional, String idLocalidadeInicial,
			String idLocalidadeFinal, String setorComercialInicial,
			String setorComercialFinal, String quadraInicial,
			String quadraFinal, String loteOrigem, String loteDestno,
			String cep, String logradouro, String bairro, String municipio,
			String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo,
			String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial,
			String numeroMoradoresFinal, String idAreaConstruidaFaixa

	) throws ErroRepositorioException;

	/**
	 * [UC0336] - Gerar Relatorio de Acompanhamento do Faturamento
	 * 
	 * @param anoMesReferenciaContabil
	 *            idImovel
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Conta> pesquisarContasDoImovelPorMesAnoReferencia(
			int anoMesReferencia, String idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0336] - Gerar Relatorio de Acompanhamento do Faturamento
	 * 
	 * @param anoMesReferenciaContabil
	 *            idImovel
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<ConsumoHistorico> pesquisarConsumoMedioLigacaoAgua(
			String idImovel, int anoMesReferencia)
			throws ErroRepositorioException;

	/**
	 * [UC0336] - Gerar Relatorio de Acompanhamento do Faturamento
	 * 
	 * @param anoMesReferenciaContabil
	 *            idImovel
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<ConsumoHistorico> pesquisarConsumoMedioLigacaoEsgoto(
			String idImovel, int anoMesReferencia)
			throws ErroRepositorioException;

	/**
	 * [UC0336] - Gerar Relatorio de Acompanhamento do Faturamento
	 * 
	 * @param anoMesReferenciaContabil
	 *            idImovel
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<ConsumoHistorico> pesquisarConsumoMesLigacaoAgua(
			String idImovel, int anoMesReferencia)
			throws ErroRepositorioException;

	/**
	 * [UC0336] - Gerar Relatorio de Acompanhamento do Faturamento
	 * 
	 * @param anoMesReferenciaContabil
	 *            idImovel
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<ConsumoHistorico> pesquisarConsumoMesLigacaoEsgoto(
			String idImovel, int anoMesReferencia)
			throws ErroRepositorioException;

	/**
	 * [UC0336] - Gerar Relatorio de Acompanhamento do Faturamento
	 * 
	 * @param anoMesReferenciaContabil
	 *            idImovel
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<MedicaoHistorico> pesquisarLeituraFaturadaLigacaoAgua(
			String idImovel, int anoMesReferencia)
			throws ErroRepositorioException;

	/**
	 * [UC0336] - Gerar Relatorio de Acompanhamento do Faturamento
	 * 
	 * @param anoMesReferenciaContabil
	 *            idImovel
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<MedicaoHistorico> pesquisarLeituraFaturadaLigacaoEsgoto(
			String idImovel, int anoMesReferencia)
			throws ErroRepositorioException;

	/**
	 * [UC0336] - Gerar Relatorio de Acompanhamento do Faturamento
	 * 
	 * @param anoMesReferenciaContabil
	 *            idImovel
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public String pesquisarAnormalidadeLeitura(Integer idAnormalidadeLeitura)
			throws ErroRepositorioException;

	/**
	 * [UC0336] - Gerar Relatorio de Acompanhamento do Faturamento
	 * 
	 * @param anoMesReferenciaContabil
	 *            idImovel
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<HidrometroInstalacaoHistorico> pesquisarDataHidrometroLigacaoAgua(
			String idImovel) throws ErroRepositorioException;

	/**
	 * [UC0336] - Gerar Relatorio de Acompanhamento do Faturamento
	 * 
	 * @param anoMesReferenciaContabil
	 *            idImovel
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<HidrometroInstalacaoHistorico> pesquisarDataHidrometroLigacaoEsgoto(
			String idImovel) throws ErroRepositorioException;

	/**
	 * [UC0336] - Gerar Relatorio de Acompanhamento do Faturamento
	 * 
	 * @param anoMesReferenciaContabil
	 *            idImovel
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<ConsumoHistorico> pesquisarConsumoFaturadoMes(
			String idImovel, int anoMesReferencia)
			throws ErroRepositorioException;

	public Collection<FaturamentoAtividadeCronograma> pesquisarRelacaoAtividadesGrupo(
			Integer faturamentoGrupoId, Integer anoMesReferencia)
			throws ErroRepositorioException;

	/**
	 * [UC0168] - Inserir Tarifa de Consumo Retorna a date de vigência em vigor
	 * de uma tarifa de consumo Pesquisa a Data de Vigencia da Consumo Tarifa e
	 * da Consumo Tarifa Vigencia
	 * 
	 * @author Rafael Santos
	 * @since 11/07/2006
	 * @param consumoTarifa
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarMenorDataConsumoTarifaVigenciaEmVigor(
			ConsumoTarifa consumoTarifa, int idConsumoTarifaVigencia)
			throws ErroRepositorioException;

	/**
	 * [UC0168] - Inserir Tarifa de Consumo Retorna a date de vigência em vigor
	 * de uma tarifa de consumo Pesquisa a Data de Vigencia da Consumo Tarifa e
	 * da Consumo Tarifa Vigencia
	 * 
	 * @author Rafael Santos
	 * @since 11/07/2006
	 * @param consumoTarifa
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarMaiorDataConsumoTarifaVigenciaEmVigor(
			ConsumoTarifa consumoTarifa, int idConsumoTarifaVigencia)
			throws ErroRepositorioException;

	/**
	 * [UC0168] - Inserir Tarifa de Consumo Retorna a date de vigência em vigor
	 * de uma tarifa de consumo Pesuisar a Maior Menor data de todas as
	 * vigências do Consumo Tarifa
	 * 
	 * @author Rafael Santos
	 * @since 12/07/2006
	 * @param consumoTarifa
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarMaiorDataConsumoTarifaVigencia(
			ConsumoTarifa consumoTarifa) throws ErroRepositorioException;

	/**
	 * Este caso de uso calcula a tarifa miníma de água para um imóvel
	 * 
	 * [UC0451] Obter Tarifa Miníma de Água para um Imóvel
	 * 
	 * Para cada categoria e maior data de vigência retorna o valor da tarifa
	 * minima
	 * 
	 * pesquisarTarifaMinimaCategoriaVigencia
	 * 
	 * @author Roberta Costa
	 * @date 09/08/2006
	 * 
	 * @param dataCorrente
	 * @param imovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarTarifaMinimaCategoriaVigencia(Categoria categoria,
			ConsumoTarifaVigencia consumoTarifaVigencia, Integer idSubCategoria)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso inicia um processo para o mecanismo batch
	 * 
	 * [UC0111] - Iniciar Processo
	 * 
	 * Este subfluxo tem o papel de iniciar um processo de faturamento
	 * comandado, neste método é feita uma busca para obter as atividades
	 * comandadas e não realizadas
	 * 
	 * [SB0001] - Iniciar Processo de Faturamento Comandado
	 * 
	 * @author Rodrigo Silveira
	 * @date 14/08/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Collection<FaturamentoAtividadeCronograma> pesquisarFaturamentoAtividadeCronogramaComandadasNaoRealizadas(
			int numeroPagina) throws ErroRepositorioException;

	/**
	 * Este caso de uso inicia um processo para o mecanismo batch
	 * 
	 * [UC0111] - Iniciar Processo
	 * 
	 * Este subfluxo tem o papel de iniciar um processo de faturamento
	 * comandado, neste método é feita uma busca para obter as atividades
	 * comandadas e não realizadas
	 * 
	 * [SB0001] - Iniciar Processo de Faturamento Comandado
	 * 
	 * @author Rodrigo Silveira
	 * @date 14/08/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public int pesquisarFaturamentoAtividadeCronogramaComandadasNaoRealizadasCount()
			throws ErroRepositorioException;

	/**
	 * Pesquisa a existencia de uma conta pelo id da conta e pela data da ultima
	 * alteracao
	 * 
	 * @param id
	 *            Descrição do parâmetro
	 * @param ultimaAlteracao
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Integer pesquisarExistenciaContaParaConcorrencia(String idConta,
			String ultimaAlteracao) throws ErroRepositorioException;

	/**
	 * Pesquisa a existencia de um debito tipo pelo id
	 * 
	 * @param id
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Integer verificarExistenciaDebitoTipo(Integer idDebitoTipo)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso consultar os dados da conta
	 * 
	 * @param idConta
	 *            Id da Conta
	 * 
	 * @author Fernanda Paiva
	 * @date 04/09/2006
	 * 
	 * @return uma colecao de conta
	 * @throws ErroRepositorioException
	 */
	public Collection consultarConta(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC00113] - Faturar Grupo de Faturamento
	 * 
	 * Recupera o percentual de esgoto para o imóvel informado.
	 * 
	 * @author Pedro Alexandre
	 * @date 18/09/2006
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterPercentualLigacaoEsgotoImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0352] Emitir Contas
	 * 
	 * Este caso de uso permite a emissão de uma ou mais contas.
	 * 
	 * @author Pedro Alexandre
	 * @date 19/09/2006
	 * 
	 * @param anoMesReferencia
	 * @param faturamentoGrupo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasImpressao(Integer anoMesReferencia,
			FaturamentoGrupo faturamentoGrupo) throws ErroRepositorioException;

	/**
	 * [UC0352] Emitir Contas
	 * 
	 * Este caso de uso permite a emissão de uma ou mais contas.
	 * 
	 * @author Pedro Alexandre
	 * @date 24/10/2006
	 * 
	 * @param anoMesReferencia
	 * @param faturamentoGrupo
	 * @param numeroPaginas
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasImpressao(Integer anoMesReferencia,
			FaturamentoGrupo faturamentoGrupo, Integer numeroPaginas)
			throws ErroRepositorioException;

	/**
	 * Pesquisa a soma dos valores das multas cobradas para a conta.
	 * 
	 * @author Pedro Alexandre
	 * @date 19/09/2006
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorMultasCobradas(int idConta)
			throws ErroRepositorioException;

	/**
	 * Recupera os dados da conta p emitir a 2ª via [UC0482]Emitir 2ª Via de
	 * Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 15/09/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarConta(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC0209] Gerar Taxa de Entrega de Conta em Outro Endereço
	 * 
	 * Atualiza os dados do débito a cobrar e a data de última atualização do
	 * débito a cobrar geral.
	 * 
	 * @author Pedro Alexandre
	 * @date 20/09/2006
	 * 
	 * @param colecaoDebitosACobrar
	 * @throws ErroRepositorioException
	 */
	public void atualizaDebitoACobrar(Collection colecaoDebitosACobrar)
			throws ErroRepositorioException;

	/**
	 * [UC0351 - Calcular Impostos Deduzidos da Conta] Author: Rafael Santos
	 * Data: 21/09/2006
	 * 
	 * @param idImovel
	 *            Id do Imóvel
	 * @return cliente responsavel pelo imovel
	 */
	public Integer pesquisarClienteResponsavelImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0351 - Calcular Impostos Deduzidos da Conta] Author: Fernanda Paiva
	 * Data: 22/09/2006
	 * 
	 * @param idImpostoTipo
	 *            Id do ImpostoTipo
	 * @param anoMesReferencia
	 *            Ano Mês de Referência
	 * @return aliquotas do imposto
	 */
	public ImpostoTipoAliquota pesquisarAliquotaImposto(Integer idImpostoTipo,
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * [UC0150] - Retificar Conta Author: Fernanda Paiva Data: 25/09/2006
	 * 
	 * @param idConta
	 * @throws ErroRepositorioException
	 */
	public void removerClientesConta(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC0150] - Retificar Conta Author: Fernanda Paiva Data: 25/09/2006
	 * 
	 * @param idConta
	 * @throws ErroRepositorioException
	 */
	public void removerImpostosDeduzidosConta(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC0113] Faturar Grupo de Faturamento
	 * 
	 * Atualiza a data e hora da realização da atividade.
	 * 
	 * @author Pedro Alexandre
	 * @date 27/09/2006
	 * 
	 * @param idAtividade
	 * @param anoMesReferencia
	 * @param idFaturamentoGrupo
	 * @throws ErroRepositorioException
	 */
	public void atualizarDataHoraRealizacaoAtividade(Integer idAtividade,
			Integer anoMesReferencia, Integer idFaturamentoGrupo)
			throws ErroRepositorioException;

	/**
	 * [UC0113] Faturar Grupo de Faturamento
	 * 
	 * Atualiza o ano/mês de referência do faturamento para o mês seguinte.
	 * 
	 * @author Pedro Alexandre
	 * @date 27/09/2006
	 * 
	 * @param idFaturamentoGrupo
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 */
	public void atualizarAnoMesReferenciaFaturamentoGrupo(
			Integer idFaturamentoGrupo, Integer anoMesReferencia)
			throws ErroRepositorioException;

	/**
	 * Permite inserir DebitoACobrarGeral contidos numa coleção [UC0394] Gerar
	 * Débitos a Cobrar de Doações
	 * 
	 * @author César Araújo
	 * @date 05/08/2006
	 * @param Collection
	 *            <DebitoACobrarGeral> colecaoDebitosACobrarGeral - Coleção de
	 *            DebitoACobrarGeral
	 * @throws ErroRepositorioException
	 **/
	public Integer inserirDebitoACobrarGeral(
			DebitoACobrarGeral debitoACobrarGeral)
			throws ErroRepositorioException;

	/**
	 * Permite inserir DebitoACobrar contidos numa coleção [UC0394] Gerar
	 * Débitos a Cobrar de Doações
	 * 
	 * @author César Araújo
	 * @date 05/08/2006
	 * @param Collection
	 *            <DebitoACobrarGeral> colecaoDebitosACobrarGeral - Coleção de
	 *            DebitoACobrarGeral
	 * @throws ErroRepositorioException
	 **/
	public void inserirDebitoACobrar(DebitoACobrar debitoACobrar)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês - Item 05
	 * 
	 * Atualiza a situação de imóvel com cobrança finalizada
	 * 
	 * @author Pedro Alexandre
	 * @date 07/10/2006
	 * 
	 * @param anoMesFaturamento
	 * @throws ErroRepositorioException
	 */
	public void atualizarImoveisSituacaoEspecialCobrancaFinalizada(
			int anoMesFaturamento, Integer idSetorComercial)
			throws ErroRepositorioException;

	/**
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * Pesquisa os débitos cobrados por categoria
	 * 
	 * @author Pedro Alexandre
	 * @date 09/10/2006
	 * 
	 * @param idDebitoCobrado
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitoCobradoCategoria(Integer idDebitoCobrado)
			throws ErroRepositorioException;

	/**
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * Pesquisa os credios realizados por categoria
	 * 
	 * @author Pedro Alexandre
	 * @date 09/10/2006
	 * 
	 * @param idCreditoRealizado
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCreditoRealizadoCategoria(
			Integer idCreditoRealizado) throws ErroRepositorioException;

	/**
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * Pesquisa os débitos a cobrar por categoria.
	 * 
	 * @author Pedro Alexandre
	 * @date 09/10/2006
	 * 
	 * @param debitoACobrar
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitosACobrarCategoria(
			DebitoACobrar debitoACobrar) throws ErroRepositorioException;

	/**
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * Pesquisa os créditos a realizar por categoria.
	 * 
	 * @author Pedro Alexandre
	 * @date 09/10/2006
	 * 
	 * @param creditoARealizar
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCreditoARealizarCategoria(
			CreditoARealizar creditoARealizar) throws ErroRepositorioException;

	/**
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * Pesquisa os impostos deduzidos da conta.
	 * 
	 * @author Pedro Alexandre
	 * @date 10/10/2006
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContaImpostosDeduzidos(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * Pesquisa as conta categoria consumo de faixa.
	 * 
	 * @author Pedro Alexandre
	 * @date 10/10/2005
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContaCategoriaConsumoFaixa(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * Para cada conta transferida para o histórico, atualiza o indicador de que
	 * a conta está no histórico na tabela ContaGeral.
	 * 
	 * @author Pedro Alexandre
	 * @date 11/10/2006
	 * 
	 * @param idsContas
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorContaNoHistorico(Collection idsContas)
			throws ErroRepositorioException;

	/**
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * Para cada débito a cobrar transferido para o histórico, atualiza o
	 * indicador de que o débito a cobrar está no histórico na tabela
	 * DebitoACobrarGeral.
	 * 
	 * @author Pedro Alexandre
	 * @date 11/10/2006
	 * 
	 * @param idsDebitosACobrar
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorDebitoACobrarNoHistorico(
			Collection idsDebitosACobrar) throws ErroRepositorioException;

	/**
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * Para cada crédito a realizar transferido para o histórico, atualiza o
	 * indicador de que o crédito a realizar está no histórico na tabela
	 * CreditoARealizarGeral.
	 * 
	 * @author Pedro Alexandre
	 * @date 11/10/2006
	 * 
	 * @param idsCreditoARealizar
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorCreditoARealizarNoHistorico(
			Collection idsCreditoARealizar) throws ErroRepositorioException;

	/**
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * Verifica se todos os grupos já foram faturados
	 * 
	 * @author Pedro Alexandre
	 * @date 07/10/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarFaturamentoGrupoNaoFaturados(
			Integer anoMesReferenciaFaturamento)
			throws ErroRepositorioException;

	/**
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * Pesquisa os relacionamentos entre cliente e conta.
	 * 
	 * @author Pedro Alexandre
	 * @date 13/10/2005
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteConta(Integer idConta)
			throws ErroRepositorioException;

	public void inserirDebitoAutomaticoMovimento(
			DebitoAutomaticoMovimento debitoAutomaticoMovimento)
			throws ErroRepositorioException;

	/**
	 * 
	 * @author Raphael Rossiter
	 * @date 30/10/2006
	 * 
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarContaRetificacao(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Seleciona as contaas agrupando por imóvel
	 * 
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 * 
	 * @author Rafael Pinto
	 * @date 22/11/2006
	 * 
	 * @param anoMesReferenciaContabil
	 * @throws ErroRepositorioException
	 */
	public Collection<Conta> obterContaAgrupadasPorImovel(
			int anoMesReferenciaContabil, int idLocalidade, int idQuadra)
			throws ErroRepositorioException;

	/**
	 * 
	 * 
	 * Utilizado pelo [UC0] Manter Conta
	 * 
	 * @author Rafael Santos
	 * @date 23/11/2006
	 * 
	 * @param idConta
	 * @param dataUltimaAlteracao
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Object pesquisarDataUltimaAlteracaoConta(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * [SB0004] - Calcular Valor de Água e/ou Esgoto
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 04/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Object[] pesquisarParmsFaturamentoGrupo(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * [SB0004] - Calcular Valor de Água e/ou Esgoto
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 04/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */

	public Date pesquisarDataRealizacaoFaturamentoAtividadeCronograma(
			Integer idFaturamentoGrupo, Integer idFaturamentoAtividade,
			Integer amReferencia) throws ErroRepositorioException;

	/**
	 * Recupera o id do cliente responsável pela conta [UC0348] - Emitir Contas
	 * 
	 * @author Sávio Luiz
	 * @date 05/12/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarParmsClienteResponsavelConta(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Inserir Pagamentos
	 * 
	 * Pesquisa a conta digitada
	 * 
	 * @author Rafael Corrêa
	 * @date 07/12/2006
	 * 
	 * @param idImovel
	 * @param referenciaConta
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */

	public Object[] pesquisarContaDigitada(String idImovel,
			String referenciaConta) throws ErroRepositorioException;

	/**
	 * UC0113 - Faturar Grupo Faturamento Author: Rafael Santos Data: 12/12/2006
	 * 
	 * @param idImovel
	 *            Id do Imóvel
	 * @return cliente responsavel
	 */
	public Object pesquisarClienteResponsavel(Integer idImovel)
			throws ErroRepositorioException;

	public Collection obterContasImovelIntervalo(Integer imovel,
			Integer situacaoNormal, Integer situacaoIncluida,
			Integer situacaoRetificada, Integer anoMesInicio, Integer anoMesFim)
			throws ErroRepositorioException;

	public Collection obterContasImovelIntervalo(Integer imovel,
			Integer situacaoNormal, Integer situacaoIncluida,
			Integer situacaoRetificada, Integer anoMesInicio,
			Integer anoMesFim, Integer idContaMotivoRevisao)
			throws ErroRepositorioException;

	/**
	 * UC0113 - Faturar Grupo Faturamento Author: Rafael Santos Data: 27/12/2006
	 * 
	 * Pesquisar o Resumo Faturamento Simulação
	 * 
	 */
	public ResumoFaturamentoSimulacao pesquisarResumoFaturamentoSimulacao(
			ResumoFaturamentoSimulacao resumoFaturamentoSimulacao)
			throws ErroRepositorioException;

	/**
	 * UC0113 - Faturar Grupo Faturamento Author: Rafael Santos, Raphael
	 * Rossiter Data: 02/01/2007, 24/03/2008
	 * 
	 * Deleta CONTA_CATEGORIA_CONSUMO_FAIXA
	 */
	public void apagarContaCategoriaConsumoFaixa(
			ApagarDadosFaturamentoHelper helper)
			throws ErroRepositorioException;

	/**
	 * UC0113 - Faturar Grupo Faturamento Author: Rafael Santos, Raphael
	 * Rossiter Data: 02/01/2007, 24/03/2008
	 * 
	 * Deleta CONTA_CATEGORIA
	 * 
	 */
	public void apagarContaCategoria(ApagarDadosFaturamentoHelper helper)
			throws ErroRepositorioException;

	/**
	 * UC0113 - Faturar Grupo Faturamento Author: Rafael Santos, Raphael
	 * Rossiter Data: 02/01/2007, 24/03/2008
	 * 
	 * Deleta CONTA_IMPRESSAO
	 */
	public void apagarContaImpressao(ApagarDadosFaturamentoHelper helper)
			throws ErroRepositorioException;

	/**
	 * UC0113 - Faturar Grupo Faturamento Author: Rafael Santos, Raphael
	 * Rossiter Data: 02/01/2007, 19/02/2008
	 * 
	 * Deleta CLIENTE_CONTA
	 */
	public void apagarClienteConta(ApagarDadosFaturamentoHelper helper)
			throws ErroRepositorioException;

	/**
	 * UC0113 - Faturar Grupo Faturamento Author: Rafael Santos, Raphael
	 * Rossiter Data: 02/01/2007, 25/03/2008
	 * 
	 * Deleta CONTA_IMPOSTOS_DEDUZIDOS
	 */
	public void apagarContaImpostosDeduzidos(ApagarDadosFaturamentoHelper helper)
			throws ErroRepositorioException;

	/**
	 * UC0113 - Faturar Grupo Faturamento Author: Rafael Santos, Raphael
	 * Rossiter Data: 02/01/2007, 25/03/2008
	 * 
	 * Deleta DEBITO_AUTOMATICO_MOVIMENTO
	 */
	public void apagarDebitoAutomaticoMovimento(
			ApagarDadosFaturamentoHelper helper)
			throws ErroRepositorioException;

	/**
	 * UC0113 - Faturar Grupo Faturamento Author: Rafael Santos, Raphael
	 * Rossiter Data: 02/01/2007, 25/03/2008
	 * 
	 * Deleta DEBITO_COBRADO_CATEGORIA
	 */
	public void apagarDebitoCobradoCategoria(ApagarDadosFaturamentoHelper helper)
			throws ErroRepositorioException;

	/**
	 * UC0113 - Faturar Grupo Faturamento Author: Rafael Santos, Raphael
	 * Rossiter Data: 02/01/2007, 25/03/2008
	 * 
	 * Deleta DEBITO_COBRADO
	 * 
	 */
	public void apagarDebitoCobrado(ApagarDadosFaturamentoHelper helper)
			throws ErroRepositorioException;

	/**
	 * UC0113 - Faturar Grupo Faturamento Author: Rafael Santos, Raphael
	 * Rossiter Data: 02/01/2007, 25/03/2008
	 * 
	 * Deleta CREDITO_REALIZADO_CATEGORIA
	 */
	public void apagarCreditoRealizadoCategoria(
			ApagarDadosFaturamentoHelper helper)
			throws ErroRepositorioException;

	/**
	 * UC0113 - Faturar Grupo Faturamento Author: Rafael Santos, Raphael
	 * Rossiter Data: 02/01/2007, 25/03/2008
	 * 
	 * Deleta CREDITO_REALIZADO
	 */
	public void apagarCreditoRealizado(ApagarDadosFaturamentoHelper helper)
			throws ErroRepositorioException;

	/**
	 * UC0113 - Faturar Grupo Faturamento Author: Rafael Santos, Raphael
	 * Rossiter Data: 02/01/2007, 25/03/2008
	 * 
	 * Update DEBITO_A_COBRAR
	 */
	public void atualizarDebitoACobrar(ApagarDadosFaturamentoHelper helper)
			throws ErroRepositorioException;

	/**
	 * UC0113 - Faturar Grupo Faturamento Author: Rafael Santos, Raphael
	 * Rossiter Data: 02/01/2007, 25/03/2008
	 * 
	 * Update CREDITO_A_REALIZAR
	 */
	public void atualizarCreditoARealizar(ApagarDadosFaturamentoHelper helper)
			throws ErroRepositorioException;

	/**
	 * UC0113 - Faturar Grupo Faturamento Author: Rafael Santos, Raphael
	 * Rossiter Data: 02/01/2007, 25/03/2008
	 * 
	 * Update CONTA_GERAL
	 */
	public void atualizarContaGeral(ApagarDadosFaturamentoHelper helper)
			throws ErroRepositorioException;

	/**
	 * UC0113 - Faturar Grupo Faturamento Author: Rafael Santos, Raphael
	 * Rossiter Data: 02/01/2007, 25/03/2008
	 * 
	 * Deleta CONTA
	 * 
	 */
	public void apagarConta(ApagarDadosFaturamentoHelper helper)
			throws ErroRepositorioException;

	/**
	 * UC0113 - Faturar Grupo Faturamento Author: Rafael Santos, Raphael
	 * Rossiter Data: 02/01/2007, 25/03/2008
	 * 
	 * Delete CONTA_GERAL
	 */
	public void apagarContaGeral(ApagarDadosFaturamentoHelper helper)
			throws ErroRepositorioException;

	/**
	 * [UC113] Faturar Grupo Faturamento
	 * 
	 * Retorna a quantidade de contas existentes para uma rota em um determinado
	 * anoMês de referência e de acordo com a situação atual recebida.
	 * 
	 * @author Rafael Santos, Raphael Rossiter
	 * @date 02/01/2007, 24/03/2008
	 * 
	 * @param Integer
	 *            anoMesFaturamento
	 * @param Integer
	 *            idRota
	 * @param Integer
	 *            debitoCreditoSituacaoAtual
	 */
	public Integer quantidadeContasRota(Integer anoMesFaturamento, Rota rota,
			Integer debitoCreditoSituacaoAtual, Integer idImovel)
			throws ErroRepositorioException;

	public Collection pesquisarConsumoTarifaCategoriaPorSubCategoria(
			ConsumoTarifaVigencia consumoTarifaVigencia, Categoria categoria,
			Subcategoria subCategoria) throws ErroRepositorioException;

	/**
	 * [UC0320] Gerar Fatura de Cliente Responsável
	 * 
	 * Deleta as faturas e os items da fatura por cliente responsável e ano/mês
	 * de referência.
	 * 
	 * @author Pedro Alexandre
	 * @date 04/01/2007
	 * 
	 * @param idCliente
	 * @param anoMesReferenciaFatura
	 * @throws ErroRepositorioException
	 */
	public void deletarFaturaClienteResponsavel(Integer idCliente,
			Integer anoMesReferenciaFatura,
			Integer anoMesReferenciaFaturaAntecipada)
			throws ErroRepositorioException;

	public void removerFaturamentoGrupoAtividades(
			Integer idFaturamentoGrupoMensal) throws ErroRepositorioException;

	/**
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * Pesquisar os ids das localidades para encerrar o faturamento do ano/mês
	 * de referência corrente.
	 * 
	 * @author Pedro Alexandre
	 * @date 05/01/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsLocalidadeParaEncerrarFaturamento()
			throws ErroRepositorioException;

	/**
	 * Pesquisar os ids das localidades para gerar o resumo das
	 * ligações/economias.
	 * 
	 * @author Rodrigo Silveira
	 * @date 17/01/2007
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarIdsLocalidadeParaGerarResumoLigacoesEconomias()
			throws ErroRepositorioException;

	/**
	 * Recupera os dados da conta p emitir a 2ª via [UC0482]Emitir 2ª Via de
	 * Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 08/01/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContaERota(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC0532] Gerar Relatório de Faturamento das Ligações com Medição
	 * Individualizada
	 * 
	 * @author Vivianne Sousa
	 * @date 09/01/2007
	 * 
	 * @param filtroMedicaoHistoricoSql
	 * @param anoMesfaturamentoGrupo
	 * @throws ControladorException
	 */

	public Collection pesquisarIdImovelCondominioLigacoesMedicaoIndividualizada(
			FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql,
			String anoMesfaturamentoGrupo) throws ErroRepositorioException;

	/**
	 * [UC0532] Gerar Relatório de Faturamento das Ligações com Medição
	 * Individualizada
	 * 
	 * @author Vivianne Sousa
	 * @date 09/01/2007
	 * 
	 * @param idImovel
	 * @param anoMesfaturamentoGrupo
	 * @throws ControladorException
	 */
	public Collection pesquisarLigacoesMedicaoIndividualizadaRelatorio(
			Integer idImovel, String anoMesfaturamentoGrupo)
			throws ErroRepositorioException;

	/**
	 * [UC0493] Emitir de Extrato de Consumo de Imóvel Condomínio
	 * 
	 * Flávio Cordeiro 08/01/2007
	 * 
	 * @throws ErroRepositorioException
	 *             idsRotas string formatada com valores separados por virgula.
	 *             Ex: 1,2,5,6 anoMesFaturamento
	 */

	public Collection pesquisarEmitirExtratoConsumoImovelCondominio(
			Collection idsRotas, String anoMesFaturamento)
			throws ErroRepositorioException;

	/**
	 * soma dos consumos dos imoveis associados [UC0493] Emitir de Extrato de
	 * Consumo de Imóvel Condomínio
	 * 
	 * Flávio Cordeiro 12/01/2007
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer somaConsumosImoveisAssociados(Integer idImovel, String anoMes)
			throws ErroRepositorioException;

	/**
	 * quantidade de imoveis associados [UC0493] Emitir de Extrato de Consumo de
	 * Imóvel Condomínio
	 * 
	 * Flávio Cordeiro 12/01/2007
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer quantidadeImoveisAssociados(Integer idImovel, String anoMes)
			throws ErroRepositorioException;

	/**
	 * [UC0173] Gerar Relatório de Resumo do Faturamento
	 * 
	 * @author Vivianne Sousa, Diogo Peixoto
	 * @created 24/01/2007, 25/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer consultarQtdeRegistrosResumoFaturamentoRelatorio(
			int anoMesReferencia, Integer localidade, Integer municipio,
			Integer gerenciaRegional, String opcaoTotalizacao)
			throws ErroRepositorioException;

	/**
	 * @author Ana Maria
	 * @date 26/01/2007
	 * 
	 * @param idConta
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection obterConta(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC0335] Gerar Resumo de Pendência
	 * 
	 * Pesquisar os ids das localidade
	 * 
	 * @author Ana Maria
	 * @date 29/01/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsLocalidade()
			throws ErroRepositorioException;

	/**
	 * 
	 * atualiza o sequencial de conta impressão
	 * 
	 * @author Sávio Luiz
	 * @date 29/01/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarSequencialContaImpressao(
			Map<Integer, Integer> mapAtualizaSequencial)
			throws ErroRepositorioException;

	/**
	 * [UC] Gerar Relatório de Contas Emitidas
	 * 
	 * @author Vivianne Sousa
	 * @created 30/01/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarContasEmitidasRelatorio(int anoMesReferencia,
			Integer grupoFaturamento, Collection esferaPoder)
			throws ErroRepositorioException;

	/**
	 * [UC] Gerar Relatório de Contas Emitidas
	 * 
	 * @author Vivianne Sousa
	 * @created 02/02/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer consultarQtdeContasEmitidasRelatorio(int anoMesReferencia,
			Integer grupoFaturamento, Collection esferaPoder)
			throws ErroRepositorioException;

	// retorna o anoMes do faturamento grupo do imóvel passado
	public Integer retornaAnoMesFaturamentoGrupo(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Monta a colecao de resultdos apartir da tbela conta impressao para
	 * geracao do relatorio de MAPA DE CONTROLE DAS CONTAS EMITIDAS
	 * 
	 * @author Flávio Cordeiro
	 * @date 13/02/2007
	 * 
	 * @param idGrupoFaturamento
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Collection filtrarMapaControleContaRelatorio(
			Integer idGrupoFaturamento, String anoMes,
			String indicadorFichaCompensacao) throws ErroRepositorioException;

	/**
	 * Monta a colecao de resultdos apartir da tabela conta impressao para
	 * geracao do relatorio de RESUMO CONTAS EMITIDAS POR LOCALIDADE NO GRUPO
	 * 
	 * @author Flávio Cordeiro
	 * @date 13/02/2007
	 * 
	 * @param idGrupoFaturamento
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Collection filtrarResumoContasLocalidade(Integer idGrupoFaturamento,
			String anoMes, Integer idFirma) throws ErroRepositorioException;

	/**
	 * Author: Vivianne Sousa Data: 06/03/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterGuiasPagamentoParcelamentoItem(
			Integer idGuiaPagamento) throws ErroRepositorioException;

	/**
	 * Author: Vivianne Sousa Data: 06/03/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterGuiasPagamentoCobrancaDocumentoItem(
			Integer idGuiaPagamento) throws ErroRepositorioException;

	/**
	 * Author: Vivianne Sousa Data: 06/03/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterGuiasPagamentoPagamento(Integer idGuiaPagamento)
			throws ErroRepositorioException;

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * @author Pedro Alexandre
	 * @date 17/03/2007
	 * 
	 * @param conta
	 * @param idImovel
	 * @param anoMesReferenciaConta
	 * @param anoMesReferenciaAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean obterIndicadorPagamentosClassificadosContaReferenciaMenorIgualAtual(
			Integer conta, Integer idImovel, Integer anoMesReferenciaConta,
			Integer anoMesReferenciaAtual) throws ErroRepositorioException;

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * @author Pedro Alexandre
	 * @date 19/03/2007
	 * 
	 * @param idGuiaPagamento
	 * @param idImovel
	 * @param idDebitoTipo
	 * @param anoMesReferenciaAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean obterIndicadorPagamentosClassificadosGuiaPagamentoReferenciaMenorIgualAtual(
			Integer idGuiaPagamento, Integer idImovel, Integer idDebitoTipo,
			Integer anoMesReferenciaAtual) throws ErroRepositorioException;

	/**
	 * Obtém as contas de um imóvel com ano/mes da data de vencimento menor ou
	 * igual ao ano/mes de referencia da arrecadacao corrente e com situacao
	 * atual correspondente a normal, retificada ou incluida.
	 * 
	 * [UC0302] - Gerar Debitos a Cobrar de Acréscimos por Impontualidade
	 * 
	 * @author Fernanda Paiva, Pedro Alexandre
	 * @date 24/04/2006,15/03/2007
	 * 
	 * @param imovel
	 * @param situacaoNormal
	 * @param situacaoIncluida
	 * @param situacaoRetificada
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterContasImovel(Integer imovel, Integer situacaoNormal,
			Integer situacaoIncluida, Integer situacaoRetificada,
			Date dataAnoMesReferenciaUltimoDia) throws ErroRepositorioException;

	/**
	 * 
	 * [UC0544] - Gerar Arquivo Texto do Faturamento
	 * 
	 * @author Flávio Cordeiro
	 * @date 23/03/2007
	 * 
	 * @param anoMes
	 * @param idCliente
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection gerarArquivoTextoFaturamento(int anoMes, Integer idCliente)
			throws ErroRepositorioException;

	public Collection gerarArquivoTextoFaturamentoCreditos(int anoMes,
			Integer idCliente, Integer imovelId)
			throws ErroRepositorioException;

	public Collection gerarArquivoTextoFaturamentoServicos(int anoMes,
			Integer idCliente, Integer imovelId)
			throws ErroRepositorioException;

	public Collection gerarArquivoTextoFaturamentoImpostos(int anoMes,
			Integer idCliente, Integer imovelId)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 63 Retorna o valor de
	 * categoria de débito cobrado acumulado, de acordo com o ano/mês de
	 * referência, a situação igual a normal e o tipo de financiamento igual a
	 * doações.
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoDoacoesSituacaoNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * Recupera as contas do conjunto de imovéis
	 * 
	 * @author Ana Maria
	 * @date 19/03/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeContasImoveis(Integer anoMes,
			Collection idsImovel, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim,
			String indicadorContaPaga) throws ErroRepositorioException;

	/**
	 * Recupera as contas em revisão do Conjunto de Imóveis
	 * 
	 * @author Ana Maria
	 * @date 19/03/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeContasRevisaoImoveis(Integer anoMes,
			Collection idsImovel, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim,
			String indicadorContaPaga) throws ErroRepositorioException;

	/**
	 * Recupera as contas do Conjunto de Imóveis
	 * 
	 * @author Ana Maria
	 * @date 19/03/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasImoveis(Integer anoMes,
			Collection idsImovel, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim,
			String indicadorContaPaga) throws ErroRepositorioException;

	/**
	 * Recupera as contas do Conjunto de Imóveis
	 * 
	 * @author Ana Maria
	 * @date 19/03/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection obterContasImoveis(Integer anoMes, Collection idsImovel,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim,
			Integer anoMesFim, String indicadorContaPaga)
			throws ErroRepositorioException;

	/**
	 * Recupera o maior valor do sequêncial de impressão e soma 10 ao valor
	 * maximo retornado
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Pedro
	 * @date 27/03/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Short recuperarValorMaximoSequencialImpressaoMais10()
			throws ErroRepositorioException;

	/**
	 * Remove o id da conta dos pagamentos referentes a conta para poder mandar
	 * a conta para o histórico.
	 * 
	 * [UC0000] Gerar Histórco para encerrar Faturamento
	 * 
	 * @author Pedro Alexandre
	 * @date 01/04/2007
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void apagarIdContaPagamentos(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Remove o id do débito a cobrar dos pagamentos referentes a conta para
	 * poder mandar o débito a cobrar para o histórico.
	 * 
	 * [UC0000] Gerar Histórco para encerrar Faturamento
	 * 
	 * @author Pedro Alexandre
	 * @date 01/04/2007
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void apagarIdDebitoACobrarPagamentos(Integer idDebitoACobrar)
			throws ErroRepositorioException;

	/**
	 * Pesquisa as contas canceladas por localidade com paginação
	 * 
	 * [UC0000] Gerar Historico para Encerrar Faturamento
	 * 
	 * @author Pedro Alexandre
	 * @date 03/04/2007
	 * 
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param numeroIndice
	 * @param quantidadeRegistros
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Conta> pesquisarContasCanceladasPorMesAnoReferenciaContabil(
			int anoMesReferenciaContabil, Integer idSetorComercial,
			Integer numeroIndice, Integer quantidadeRegistros)
			throws ErroRepositorioException;

	/**
	 * 
	 * retorna o nome do cliente usuario da conta
	 * 
	 * @author Flávio Cordeiro
	 * @date 09/04/2007
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String pesquisarClienteUsuarioConta(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC0XXX] Emitir Aviso de Cobrança
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 09/04/2007
	 * 
	 */
	public Object[] pesquisarAnoMesEDiaVencimentoFaturamentoGrupo(
			Integer idImovel) throws ErroRepositorioException;

	/**
	 * Pesquisa a soma dos valores das multas cobradas para a conta.
	 * 
	 * @author Sávio Luiz
	 * @date 13/04/2007
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorMultasCobradasPorFinanciamnetoTipo(
			int idConta) throws ErroRepositorioException;

	/**
	 * Este caso de uso calcula a tarifa miníma de água para um imóvel
	 * (SUBCATEGORIA)
	 * 
	 * [UC0451] Obter Tarifa Miníma de Água para um Imóvel
	 * 
	 * @author Raphael Rossiter
	 * @date 14/04/2007
	 * 
	 * @param consumoTarifaVigencia
	 * @param subcategoria
	 * @return Object
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarTarifaMinimaCategoriaVigenciaPorSubcategoria(
			ConsumoTarifaVigencia consumoTarifaVigencia,
			Subcategoria subcategoria) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês pesquisar debitos cobrados de
	 * contas
	 * 
	 * @param idConta
	 *            Código da conta
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<DebitoCobrado> pesquisarDebitosCobrados(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Pesquisar créditos realizados de
	 * contas canceladas
	 * 
	 * @param idConta
	 *            Código da conta
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<CreditoRealizado> pesquisarCreditosRealizados(
			Integer idConta) throws ErroRepositorioException;

	/**
	 * [UC0251] Gerar Atividade de Ação de Cobrança [SB0004] Verificar Critério
	 * de Cobrança para Imóvel Pesquisa a soma dos imoveis com parcelamento.
	 * 
	 * @author Sávio Luiz
	 * @date 13/04/2007
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public int pesquisarQuantidadeDebitosCobradosComParcelamento(
			Collection<ContaValoresHelper> colecaoContasValores)
			throws ErroRepositorioException;

	/**
	 * Recupera os ids das contas do Conjunto de Imóveis
	 * 
	 * @author Ana Maria
	 * @date 19/04/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIdContasImoveis(Integer anoMes,
			Collection idsImovel, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim,
			String indicadorContaPaga) throws ErroRepositorioException;

	/**
	 * Recupera o debitoCreditoSituacaoAtual da Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 25/04/2007
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarDebitoCreditoSituacaoAtualConta(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Recupera o debitoCreditoSituacaoAtual da Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 10/08/2007
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarDebitoCreditoSituacaoAtualConta(Integer idImovel,
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * Método que retorna uma colecao de conta categoria
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0011] Obter Quantidade de Economias da Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 28/04/2007
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContaCategoriaFaixas(Integer idConta,
			Integer idCategoria, Integer idSubCategoria)
			throws ErroRepositorioException;

	/**
	 * Recupera o id da Conta Retificada
	 * 
	 * @author Vivianne Sousa
	 * @date 27/04/2007
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarContaRetificada(Integer idImovel,
			int anoMesReferenciaConta) throws ErroRepositorioException;

	/**
	 * Gera credito a realizar para os imóveis de determinados grupos
	 * 
	 * BATCH PARA CORREÇÃO DA BASE
	 * 
	 * @author Sávio Luiz
	 * @date 02/05/2007
	 * 
	 */
	public Collection pesquisarDadosImoveisParaGerarCreditoARealizar(
			Collection idsGrupos, Integer anoMesReferenciaConta,
			Integer anoMesReferenciaDebito) throws ErroRepositorioException;

	/**
	 * Método que retorna uma colecao de conta categoria
	 * 
	 * [UC0482]Emitir 2ª Via de Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 02/05/2007
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContaCategoriaSubCategoria(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Método que retorna as contas para impressao
	 * 
	 * Pesquisar Contas Emitir Caern
	 * 
	 * @author Tiago Moreno
	 * @date 05/05/2007
	 * 
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasEmitirCAERN(Integer idTipoConta,
			Integer idEmpresa, Integer numeroPaginas, Integer anoMesReferencia,
			Integer idFaturamentoGrupo, BigDecimal valorContaFichaComp)
			throws ErroRepositorioException;

	public Collection pesquisarContasEmitirOrgaoPublicoCAERN(
			Integer idTipoConta, Integer idEmpresa, Integer numeroPaginas,
			Integer anoMesReferencia, Integer idFaturamentoGrupo,
			BigDecimal valorContaFichaComp) throws ErroRepositorioException;

	/**
	 * Recupera o id da Conta Retificada
	 * 
	 * @author Vivianne Sousa
	 * @date 08/05/2007
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarAnoMesReferenciaFaturamentoGrupo(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0394] - Gerar Débitos a Cobrar de Doações
	 * 
	 * Pesquisas os debitos para serem removidos
	 * 
	 * @author Sávio Luiz
	 * @date 09/05/2007
	 * 
	 * @param colecaoRotas
	 *            , Integer anoMesReferenciaDebito
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitoACobrarParaRemocao(Rota rota,
			Integer anoMesReferenciaContabil) throws ErroRepositorioException;

	/**
	 * [UC0394] - Gerar Débitos a Cobrar de Doações
	 * 
	 * Deleta as categorias do débito a cobrar
	 * 
	 * @author Sávio Luiz
	 * @date 09/05/2007
	 * 
	 * @param colecaoRotas
	 *            , Integer anoMesReferenciaDebito
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarDebitosACobrarCategoria(Collection idsDebitoACobrar)
			throws ErroRepositorioException;

	/**
	 * [UC0394] - Gerar Débitos a Cobrar de Doações
	 * 
	 * Deleta os debitos a cobrar e os debitos a cobrar geral para o ano e mes
	 * de faturamento. Esse caso é quando um faturamento é rodado mais de 1 vez.
	 * 
	 * @author Sávio Luiz
	 * @date 09/05/2007
	 * 
	 * @param colecaoRotas
	 *            , Integer anoMesReferenciaDebito
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarDebitosACobrar(Collection idsDebitoACobrar)
			throws ErroRepositorioException;

	/**
	 * [UC0XXX] - Gerar Relatório Tarifa de Consumo
	 * 
	 * Pesquisas as tarifas de consumo para o relatório
	 * 
	 * @author Rafael Corrêa
	 * @date 11/05/2007
	 * 
	 * @param descricao
	 *            , dataVigenciaInicial, dataVigenciaFinal
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarConsumoTarifaRelatorio(String descricao,
			Date dataVigenciaInicial, Date dataVigenciaFinal)
			throws ErroRepositorioException;

	/**
	 * [UC0XXX] - Gerar Relatório de Tarifa de Consumo
	 * 
	 * Pesquisas a data final de validade de uma tarifa de consumo
	 * 
	 * @author Rafael Corrêa
	 * @date 11/05/2007
	 * 
	 * @param Integer
	 *            idConsumoTarifa
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarDataFinalValidadeConsumoTarifa(
			Integer idConsumoTarifa, Date dataInicioVigencia)
			throws ErroRepositorioException;

	/**
	 * Recupera id de contas que estão em revisão por acão do usuario
	 * 
	 * @author Vivianne Sousa
	 * @date 19/03/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection obterContasEmRevisaoPorAcaoUsuario(Collection idsConta)
			throws ErroRepositorioException;

	/**
	 * Recupera as contas do Cliente
	 * 
	 * @author Ana Maria
	 * @date 19/03/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeContasCliente(Integer codigoCliente,
			Short relacaoTipo, Integer anoMes, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim)
			throws ErroRepositorioException;

	/**
	 * Recupera as contas do Cliente
	 * 
	 * @author Ana Maria
	 * @date 19/03/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasCliente(Integer codigoCliente,
			Short relacaoTipo, Integer anoMes, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim,
			Integer codigoClienteSuperior) throws ErroRepositorioException;

	/**
	 * Recupera as contas do Cliente
	 * 
	 * @author Ana Maria
	 * @date 19/03/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection obterContasCliente(Integer codigoCliente,
			Short relacaoTipo, Integer anoMes, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim)
			throws ErroRepositorioException;

	/**
	 * Recupera as contas do Cliente
	 * 
	 * @author Ana Maria
	 * @date 14/05/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIdContasCliente(Integer codigoCliente,
			Short relacaoTipo, Integer anoMes, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim)
			throws ErroRepositorioException;

	/**
	 * Recupera id de conta(s) sem revisão ou em revisão por ação do usuário
	 * 
	 * @author Vivianne Sousa
	 * @date 14/05/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection obterContasNaoEmRevisaoOuEmRevisaoPorAcaoUsuario(
			Collection idsConta) throws ErroRepositorioException;

	/**
	 * Recupera os dados da conta historico p emitir a 2ª via [UC0482]Emitir 2ª
	 * Via de Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 16/05/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContaHistorico(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Recupera o id do cliente responsável pela conta historico [UC0482]Emitir
	 * 2ª Via de Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 16/05/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdClienteResponsavelContaHistorico(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Método que retorna a soma de quantidade economia
	 * 
	 * [UC0482]Emitir 2ª Via de Conta
	 * 
	 * [SB0007] Obter Quantidade de Economias da Conta Historico
	 * 
	 * @author Vivianne Sousa
	 * @date 16/05/2007
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Short obterQuantidadeEconomiasContaHistorico(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Pesquisa as contas do cliente responsável para todos os grupos de
	 * faturamento.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * @author Pedro Alexandre
	 * @date 17/05/2007
	 * 
	 * @param idTipoConta
	 * @param numeroPaginas
	 * @param anoMesReferencia
	 * @param indicadorEmissaoExtratoFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasClienteResponsavel(
			Collection<Integer> idTipoConta, Integer numeroPaginas,
			Integer anoMesReferencia, Short indicadorEmissaoExtratoFaturamento,
			Integer anoMesReferenciaFaturamentoAntecipado,
			Integer imovelContaEnvio) throws ErroRepositorioException;

	/**
	 * Método que retorna uma colecao de conta categoria
	 * 
	 * [UC0482]Emitir 2ª Via de Conta
	 * 
	 * [SB0011] Obter Quantidade de Economias da Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 16/05/2007
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContaCategoriaHistorico(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Método que retorna uma colecao de conta categoria
	 * 
	 * [UC0482]Emitir 2ª Via de Conta
	 * 
	 * [SB0011] Obter Quantidade de Economias da Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 16/05/2007
	 * 
	 * @param idConta
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContaCategoriaHistoricoFaixas(Integer idConta,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * Método que retorna uma array de object do conta impostos deduzidos
	 * 
	 * [UC0482]Emitir 2ª Via de Conta
	 * 
	 * [SB0015] Gerar Linhas dos Impostos Deduzidos
	 * 
	 * @author Vivianne Sousa
	 * @date 16/05/2007
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarParmsContaImpostosDeduzidosHistorico(
			Integer idConta) throws ErroRepositorioException;

	/**
	 * Método que retorna uma array de object com a soma do valor dos debitos
	 * cobrados de parcelamento,o numero da prestacao e o numero total de
	 * prestações
	 * 
	 * [UC0482]Emitir 2ª Via de Conta
	 * 
	 * [SB0013] Gerar Linhas dos Débitos Cobrados
	 * 
	 * @author Vivianne Sousa
	 * @date 16/05/2007
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarParmsDebitoAutomaticoHistorico(
			Integer idConta) throws ErroRepositorioException;

	/**
	 * Método que retorna uma array de object do debito cobrado ordenado pelo
	 * tipo de debito
	 * 
	 * 
	 * [UC0482]Emitir 2ª Via de Conta
	 * 
	 * [SB0013] Gerar Linhas dos Débitos Cobrados
	 * 
	 * @author Vivianne Sousa
	 * @date 16/05/2007
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List pesquisarParmsDebitoCobradoHistoricoPorTipo(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Método que retorna uma array de object do crédito realizado ordenado pelo
	 * tipo de crédito
	 * 
	 * 
	 * [UC0482]Emitir 2ª Via de Conta
	 * 
	 * [SB0014] Gerar Linhas dos Creditos Realizados
	 * 
	 * @author Vivianne Sousa
	 * @date 16/05/2007
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List pesquisarParmsCreditoRealizadoHistoricoPorTipo(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * 
	 * Este caso de uso permite gerar um ralatorio analitico do faturamento
	 * 
	 * [UC0xxx]Gerar Relatório Analítico do Faturamento
	 * 
	 * @author Flávio Cordeiro
	 * @date 18/05/2007
	 * 
	 * @param anoMesFaturamento
	 * @param idFaturamentoGrupo
	 * @param indicadorLocalidadeInformatizada
	 * @param idLocalidades
	 * @param idSetores
	 * @param idQuadras
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosRelatorioAnaliticoFaturamento(
			int anoMesFaturamento, Integer idFaturamentoGrupo,
			int indicadorLocalidadeInformatizada, Collection idLocalidades,
			Collection idSetores, Collection idQuadras)
			throws ErroRepositorioException;

	/**
	 * retorno o id do imvel com FNTP_ID da tabela DEBITO_A_COBRAR com o valor
	 * correspondente a parcelamento de água (2), parcelamento de esgoto (3), ou
	 * parcelamento de serviço(4)
	 * 
	 * [UC0259] - Processar Pagamento com código de Barras
	 * 
	 * [SB0012] - Verifica Pagamento de Debito a Cobrar de Parcelamento
	 * 
	 * @author Vivianne Sousa
	 * @date 30/05/2007
	 * 
	 * @param idDebitoACobrar
	 * @return retorna o id do imovel do debito
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Integer pesquisarImovelDebitoACobrar(Integer idDebitoACobrar)
			throws ErroRepositorioException;

	/**
	 * atualiza DSCT_IDATUAL com o valor correspondente a cancelado (3), na
	 * tabela DEBITO_A_COBRAR com IMOV_ID do debito a cobrar que foi pago,
	 * DCST_IDATUAL com o valor correspondente a normal (0) e FNTP_ID com o
	 * valor correspondente a juros de parcelamento (8)
	 * 
	 * [UC0259] - Processar Pagamento com código de Barras
	 * 
	 * [SB0012] - Verifica Pagamento de Debito a Cobrar de Parcelamento
	 * 
	 * @author Vivianne Sousa
	 * @date 30/05/2007
	 * 
	 * @param idimovel
	 * @return
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public void atualizarDebitoCreditoSituacaoAtualDoDebitoACobrar(
			Integer idImovel) throws ErroRepositorioException;

	/**
	 * [UC0302] - Gerar Debitos a Cobrar de Acréscimos por Impontualidade
	 * Author: Raphael Rossiter Data: 31/05/2007
	 * 
	 * Obtém os pagamentos da conta que contem a menor data de pagamento
	 * 
	 * @param Integer
	 *            conta, Integer idImovel, Integer anoMesReferenciaConta
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] obterArrecadacaoFormaPagamentoContasMenorData(
			Integer conta, Integer idImovel, Integer anoMesReferenciaConta)
			throws ErroRepositorioException;

	/**
	 * Consulta ResumoFaturamento para a geração do relatório '[UC0173] Gerar
	 * Relatório de Resumo Faturamento' de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa
	 * @created 31/05/2007
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoFaturamentoRelatorioEstadoPorUnidadeNegocio(
			int anoMesReferencia, String opcaoRelatorio)
			throws ErroRepositorioException;

	/**
	 * Consulta ResumoFaturamento para a geração do relatório '[UC0173] Gerar
	 * Relatório de Resumo Faturamento' de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa
	 * @created 31/05/2007
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoFaturamentoRelatorioPorUnidadeNegocio(
			int anoMesReferencia, Integer unidadeNegocio, String opcaoRelatorio)
			throws ErroRepositorioException;

	/**
	 * [UC0600] Emitir Histograma de Água
	 * 
	 * [SB0014] Selecionar por Indicador de Consumo [SB0015] Selecionar por
	 * Indicador de Medido [SB0016] Selecionar por Indicador de Poço [SB0017]
	 * Selecionar por Indicador de Volume Fixo de Água
	 * 
	 * @author Rafael Pinto
	 * @date 01/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaHelper
	 * 
	 * @return Collection<OrdemServico>
	 * @throws ControladorException
	 */
	public Collection<Object[]> pesquisarEmitirHistogramaAgua(
			FiltrarEmitirHistogramaAguaHelper filtro)
			throws ErroRepositorioException;

	/**
	 * [UC0600] Emitir Histograma de Água - Volume Faturado Ligacao Estimado
	 * 
	 * 
	 * @author Rafael Pinto
	 * @date 01/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaHelper
	 * 
	 * @return Collection<OrdemServico>
	 * @throws ControladorException
	 */
	public Integer pesquisarEmitirHistogramaAguaVolumeConsumo(
			FiltrarEmitirHistogramaAguaHelper filtro, Short consumo,
			Categoria categoria, Short medicao) throws ErroRepositorioException;

	/**
	 * [UC0600] Emitir Histograma de Água - Total Geral
	 * 
	 * [SB0014] Selecionar por Indicador de Consumo [SB0015] Selecionar por
	 * Indicador de Medido [SB0016] Selecionar por Indicador de Poço [SB0017]
	 * Selecionar por Indicador de Volume Fixo de Água
	 * 
	 * @author Rafael Pinto
	 * @date 01/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaHelper
	 * 
	 * @return Collection<OrdemServico>
	 * @throws ControladorException
	 */
	public Object[] pesquisarEmitirHistogramaAguaTotalGeral(
			FiltrarEmitirHistogramaAguaHelper filtro, Categoria categoria)
			throws ErroRepositorioException;

	/**
	 * [UC0605] Emitir Histograma de Água por Economia
	 * 
	 * @author Rafael Pinto
	 * @date 14/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaEconomiaHelper
	 * 
	 * @return Object[]
	 * @throws ControladorException
	 */
	public Object[] pesquisarEmitirHistogramaAguaEconomia(
			FiltrarEmitirHistogramaAguaEconomiaHelper filtro)
			throws ErroRepositorioException;

	/**
	 * [UC0605] Emitir Histograma de Água por Economia
	 * 
	 * Monta as quebras que serão necessarias para o relatorio
	 * 
	 * @author Rafael Pinto
	 * @date 18/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaEconomiaHelper
	 * 
	 * @return Collection<Object[]>
	 * @throws ControladorException
	 */
	public Collection<Object[]> pesquisarEmitirHistogramaAguaEconomiaChavesAgrupadas(
			FiltrarEmitirHistogramaAguaEconomiaHelper filtro)
			throws ErroRepositorioException;

	/**
	 * [UC0120] - Calcular Valores de Água e/ou Esgoto
	 * 
	 * @author Raphael Rossiter
	 * @date 29/06/2007
	 * 
	 * @param consumoTarifa
	 * @param dataFaturamento
	 * @throws ErroRepositorioException
	 */
	public ConsumoTarifaVigencia pesquisarConsumoTarifaVigenciaMenorOUIgualDataFaturamento(
			Integer idConsumoTarifa, Date dataFaturamento)
			throws ErroRepositorioException;

	/**
	 * Recupera o debitoCreditoSituacaoAtual da Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 21/06/2007
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarDebitoCreditoSituacaoAtualContaHistorico(
			Integer idConta) throws ErroRepositorioException;

	/**
	 * Recupera os dados da conta p emitir a 2ª via [UC0482]Emitir 2ª Via de
	 * Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 21/06/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContaHistoricoERota(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Método que retorna uma colecao de conta categoria
	 * 
	 * [UC0482]Emitir 2ª Via de Conta
	 * 
	 * [SB0011] Obter Quantidade de Economias da Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 21/06/2007
	 * 
	 * @param idConta
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContaCategoriaHistoricoFaixas(Integer idConta,
			Integer idCategoria, Integer idSubCategoria)
			throws ErroRepositorioException;

	/**
	 * Método que retorna uma colecao de conta categoria
	 * 
	 * [UC0482]Emitir 2ª Via de Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 21/06/2007
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContaHistoricoCategoriaSubCategoria(
			Integer idConta) throws ErroRepositorioException;

	/**
	 * [UC0150] - Retificar Conta
	 * 
	 * Author: Raphael Rossiter Data: 03/07/2007
	 * 
	 * @param idContaRetificada
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitoAutomaticoMovimentoContaRetificada(
			Integer idContaRetificada) throws ErroRepositorioException;

	/**
	 * [UC0150] - Retificar Conta
	 * 
	 * Author: Raphael Rossiter Data: 03/07/2007
	 * 
	 * @param colecaoDebitoAutomaticoMovimento
	 * @param idConta
	 * @throws ErroRepositorioException
	 */
	public void atualizarDebitoAutomaticoMovimentoContaRetificada(
			Collection colecaoDebitoAutomaticoMovimento, Integer idConta)
			throws ErroRepositorioException;

	/**
	 * 
	 * Metodo que retorna a data de revisão da conta
	 * 
	 * @author Vivianne Sousa
	 * @date 06/07/2007
	 * 
	 * @param idsConta
	 * @return
	 */
	public Collection pesquisarDataRevisaoConta(Collection idsConta)
			throws ErroRepositorioException;

	/**
	 * atualiza DSCT_IDATUAL com o valor correspondente a cancelado (3), na
	 * tabela CREDITO_A_REALIZAR com IMOV_ID do debito a cobrar que foi pago,
	 * DCST_IDATUAL com o valor correspondente a normal (0) e CROG_ID com o
	 * valor correspondente a descontos concedidos no parcelamento (6)
	 * 
	 * [UC0259] - Processar Pagamento com código de Barras
	 * 
	 * [SB0012] - Verifica Pagamento de Debito a Cobrar de Parcelamento
	 * 
	 * @author Vivianne Sousa
	 * @date 18/07/2007
	 * 
	 * @param idimovel
	 * @return
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public void atualizarDebitoCreditoSituacaoAtualDoCreditoARealizar(
			Integer idImovel) throws ErroRepositorioException;

	/**
	 * [UC0146] - Manter Conta Author: Raphael Rossiter Data: 21/01/2006
	 * 
	 * Obtém as contas de um imóvel que poderão ser mantidas
	 * 
	 * @param imovel
	 * @param situacaoNormal
	 * @param situacaoIncluida
	 * @param situacaoRetificada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterIdsContasImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0623] - Gerar Resumo de Metas CAERN Author: Sávio Luiz Data:
	 * 20/07/2007
	 * 
	 * Obtém as contas de um imóvel que poderão ser mantidas
	 * 
	 * @param imovel
	 * @param situacaoNormal
	 * @param situacaoIncluida
	 * @param situacaoRetificada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public int obterPagamentosContas(Collection idsContas)
			throws ErroRepositorioException;

	/**
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 * 
	 * verifica se a conta informada possui cliente responsável com esfera de
	 * poder de tipo de cliente igual a municipal, estadual ou federal.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/07/2007
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaClienteResponsavelConta(int idConta)
			throws ErroRepositorioException;

	/**
	 * [UC0623] - Gerar Resumo de Metas CAERN Author: Sávio Luiz Data:
	 * 20/07/2007
	 * 
	 * Obtém as contas de um imóvel que poderão ser mantidas
	 * 
	 * @param imovel
	 * @param situacaoNormal
	 * @param situacaoIncluida
	 * @param situacaoRetificada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarResumoMetas(Integer anoMesReferencia)
			throws ErroRepositorioException;

	/**
	 * [UC0623] - Gerar Resumo de Metas CAERN Author: Sávio Luiz Data:
	 * 20/07/2007
	 * 
	 * Obtém as contas de um imóvel que poderão ser mantidas
	 * 
	 * @param imovel
	 * @param situacaoNormal
	 * @param situacaoIncluida
	 * @param situacaoRetificada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarResumoMetasAcumulado(Integer anoMesReferencia)
			throws ErroRepositorioException;

	/**
	 * Recupera as contas de um grupo de faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 20/08/2007
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeContasGrupoFaturamento(Integer anoMes,
			Integer idGrupoFaturamento, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim)
			throws ErroRepositorioException;

	/**
	 * Recupera as contas em revisão de um grupo de faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 20/08/2007
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeContasRevisaoGrupoFaturamento(
			Integer anoMes, Integer idGrupoFaturamento,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim,
			Integer anoMesFim) throws ErroRepositorioException;

	/**
	 * Recupera as contas de um grupo de faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 20/08/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasGrupoFaturamento(Integer anoMes,
			Integer idGrupoFaturamento, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim)
			throws ErroRepositorioException;

	/**
	 * Recupera as contas de um grupo de faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection obterContasGrupoFaturamento(Integer anoMes,
			Integer idGrupoFaturamento, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim)
			throws ErroRepositorioException;

	/**
	 * Recupera os ids das contas de um grupo de faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIdContasGrupoFaturamento(Integer anoMes,
			Integer idGrupoFaturamento, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim)
			throws ErroRepositorioException;

	/**
	 * [UC0151] Alterar Vencimento Conta Author: Raphael Rossiter Data:
	 * 
	 * @autor Raphael Rossiter
	 * @data 22/08/2007
	 * 
	 * @throws ErroRepositorioException
	 */
	public void alterarVencimentoContaGrupoFaturamento(Date dataVencimento,
			Date dataValidade, Short indicadorAlteracaoVencimento,
			Integer idGrupoFaturamento, Integer anoMes, Integer anoMesFim,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim)
			throws ErroRepositorioException;

	/**
	 * [UC0482]Emitir 2ª Via de Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 20/08/2007
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTipoConta(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Metodo temporario para correção da base de dados
	 * 
	 * Gerar Crédito a Realizar para os imóveis com contas com vencimento em
	 * 14/08/2007 com multa da conta 06/2007 cobrada na conta 07/2007 e que
	 * pagaram em 17/07/2007
	 * 
	 * @author Pedro Alexandre
	 * @date 20/08/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosImoveisParaGerarCreditoARealizarPorImoveisComContasComVencimento14_08_2007()
			throws ErroRepositorioException;

	/**
	 * Busca os Debitos Cobrados Agrupados pelo Tipo de Debito Para a CAERN
	 * 
	 * @author Tiago Moreno
	 * @date 29/08/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection buscarDebitosCobradosEmitirContaCaern(Conta conta)
			throws ErroRepositorioException;

	public Date pesquisarFaturamentoAtividadeCronogramaDataPrevista(
			Integer faturamentoGrupoId, Integer faturamentoAtividadeId,
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * [UC0216] Calcular Acrescimo por Impontualidade
	 * 
	 * @author Raphael Rossiter
	 * @date 28/08/2007
	 * 
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarContaAtualizacaoTarifaria(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * @author Raphael Rossiter
	 * @date 30/09/2007
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeFaturaPorQualificador(
			Short codigoQualificador) throws ErroRepositorioException;

	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * @author Raphael Rossiter
	 * @date 30/09/2007
	 * 
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarFaturaPorQualificador(Short codigoQualificador,
			Integer anoMesReferencia, BigDecimal valorDebito)
			throws ErroRepositorioException;

	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * [SF0003] - Processar Pagamento de Documento de Cobrança
	 * 
	 * @author Sávio Luiz
	 * @created 16/02/2006
	 * 
	 * @param matriculaImovel
	 *            Matrícula do Imovel
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection pesquisarFaturaItem(Integer idFatura)
			throws ErroRepositorioException;

	/**
	 * Pesquisa os dados necessário para a geração do relatório
	 * 
	 * [UC0637] - Gerar Relatórios Volumes Faturados
	 * 
	 * @author Rafael Corrêa
	 * @created 11/09/2007
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection pesquisarDadosRelatorioVolumesFaturados(
			Integer idLocalidade, Integer anoMes, Integer anoMes1,
			Integer anoMes2, Integer anoMes3, Integer anoMes4, Integer anoMes5,
			Integer anoMes6) throws ErroRepositorioException;

	/**
	 * Pesquisa os dados necessário para a geração do relatório resumido
	 * 
	 * [UC0637] - Gerar Relatórios Volumes Faturados
	 * 
	 * @author Rafael Corrêa
	 * @created 13/09/2007
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection pesquisarDadosRelatorioVolumesFaturadosResumido(
			Integer idLocalidade, Integer anoMes, Integer anoMes1,
			Integer anoMes2, Integer anoMes3, Integer anoMes4, Integer anoMes5,
			Integer anoMes6) throws ErroRepositorioException;

	/**
	 * Pesquisa os dados necessário para a geração do relatório
	 * 
	 * [UC0635] - Gerar Relatórios de Contas em Revisão
	 * 
	 * @author Rafael Corrêa
	 * @created 20/09/2007
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection pesquisarDadosRelatorioContasRevisao(
			Integer idGerenciaRegional, Integer idUnidadeNegocio,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer codigoSetorComercialInicial,
			Integer codigoSetorComercialFinal,
			Collection colecaoIdsMotivoRevisao, Integer idImovelPerfil,
			Integer referenciaInicial, Integer referenciaFinal,
			Integer idCategoria, Integer idEsferaPoder)
			throws ErroRepositorioException;

	/**
	 * Pesquisa os dados necessário para a geração do relatório resumido
	 * 
	 * [UC0635] - Gerar Relatórios de Contas em Revisão
	 * 
	 * @author Rafael Corrêa
	 * @created 20/09/2007
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection pesquisarDadosRelatorioContasRevisaoResumido(
			Integer idGerenciaRegional, Integer idUnidadeNegocio,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer codigoSetorComercialInicial,
			Integer codigoSetorComercialFinal,
			Collection colecaoIdsMotivoRevisao, Integer idImovelPerfil,
			Integer referenciaInicial, Integer referenciaFinal,
			Integer idCategoria, Integer idEsferaPoder)
			throws ErroRepositorioException;

	/**
	 * Atualiza os Clientes Responsáveis para de Conta Impressao Alteracao feita
	 * para a ordenacao das contas de clientes orgaos publicos Por Tiago Moreno
	 * - 25/08/2007
	 * 
	 */

	public void atualizaClienteResponsavelOrgaoPublicoCAERN(
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * @author Vivianne Sousa
	 * @date 18/09/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasAtualizacaoTarifaria(Integer idImovel,
			Integer inicialReferencia, Integer finalReferencia,
			Date inicialVencimento, Date finalVencimento,
			Integer indicadorContasRevisao) throws ErroRepositorioException;

	/**
	 * Pesquisa os Valores das Faixas de Débitos
	 * 
	 * @author Ivan Sérgio
	 * @created 14/09/2007
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection pesquisarDebitoFaixaValores(Integer idFaixaValor,
			Double valorFaixa) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de crédito a
	 * realizar acumulado, de acordo com o ano/mês de referência contábil, a
	 * situação atual e a origem de crédito informados.
	 * 
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idOrigemCredito
	 * @param idSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection acumularValorCreditoARealizarPorOrigemCredito(
			int anoMesReferenciaContabil, Integer idLocalidade,
			Integer idOrigemCredito, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de categoria de
	 * credito realizado acumulado, de acordo com o ano/mês de referência, a
	 * situação atual, situação anterior e a origem do crédito informados.
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idCreditoOrigem
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ResumoFaturamento acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCredito(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idCreditoOrigem, Integer idSituacaoAtual,
			Integer idSituacaoAnterior) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de categoria de
	 * débito cobrado acumulado, de acordo com o ano/mês de referência, a
	 * situação atual, tipo de financiamento e pelo lançamento item contábil com
	 * o ano/mês de referência da baixa contábil da conta preenchido
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idFinanciamentoTipo
	 * @param idSituacaoAtual
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<ResumoFaturamento> acumularValorCategoriaDebitoCobradoCategoriaPorTipoFinanciamentoComBaixaContabilPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idFinanciamentoTipo, Integer idSituacaoAtual,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de categoria de
	 * débito cobrado acumulado, de acordo com o ano/mês de referência, a
	 * situação atual, tipo de financiamento e pelo lançamento item contábil com
	 * o ano/mês de referência da baixa contábil da conta não preenchido
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idFinanciamentoTipo
	 * @param idSituacaoAtual
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ResumoFaturamento acumularValorCategoriaDebitoCobradoCategoriaPorTipoFinanciamentoComBaixaContabilNaoPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idFinanciamentoTipo, Integer idSituacaoAtual,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor do imposto
	 * acumulado de acordo com o ano/mês de referência cntábil da conta, as
	 * situações atuais da conta e o tipo de imposto.
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idImpostoTipo
	 * @param idsSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 */
	public ResumoFaturamento acumularValorImpostoPorTipoImpostoESituacaoConta(
			int anoMesReferencia, Integer idLocalidade, Integer idCategoria,
			Integer idImpostoTipo, Integer[] idsSituacaoAtual)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor do imposto
	 * acumulado de acordo com o ano/mês de referência contábil da conta, a
	 * situação atual da conta e o tipo de imposto.
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idImpostoTipo
	 * @param idSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 */
	public BigDecimal acumularValorImpostoPorTipoImpostoESituacaoAtualConta(
			int anoMesReferencia, Integer idLocalidade, Integer idCategoria,
			Integer idImpostoTipo, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor do imposto
	 * acumulado de acordo com o ano/mês de referência contábil da conta, a
	 * situação atual e anterior da conta e o tipo de imposto.
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idImpostoTipo
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 */
	public BigDecimal acumularValorImpostoPorTipoImpostoESituacaoAtualConta(
			int anoMesReferencia, Integer idLocalidade, Integer idCategoria,
			Integer idImpostoTipo, Integer idSituacaoAtual,
			Integer idSituacaoAnterior) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de crédito a
	 * realizar acumulado, de acordo com o ano/mês de referência contábil, a
	 * situação atual ou anterior e a origem de crédito informados.
	 * 
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idsOrigemCredito
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection acumularValorCreditoARealizarPorOrigemCredito(
			int anoMesReferenciaContabil, Integer idLocalidade,
			Integer[] idsOrigemCredito, Integer idSituacaoAtual,
			Integer idSituacaoAnterior) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de crédito a
	 * realizar acumulado, de acordo com o ano/mês de referência contábil, a
	 * situação atual ou anterior e a origem de crédito informados.
	 * 
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idsOrigemCredito
	 * @param idSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection acumularValorCreditoARealizarPorOrigemCredito(
			int anoMesReferenciaContabil, Integer idLocalidade,
			Integer[] idsOrigemCredito, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna uma coleção de crédito a
	 * realizar, de acordo com o ano/mês de referência, a situação atual, a
	 * situação anterior e origem de crédito informados.
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsOrigemCredito
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCreditoARealizarPorOrigemCredito(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsOrigemCredito, Integer idSituacaoAtual,
			Integer idSituacaoAnterior) throws ErroRepositorioException;

	/**
	 * Pesquisa os dados necessário para a geração do relatório
	 * 
	 * [UC0638] - Gerar Relatórios Anormalidade Consumo
	 * 
	 * @author Rafael Corrêa
	 * @created 15/10/2007
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection pesquisarDadosRelatorioAnormalidadeConsumo(
			Integer idGrupoFaturamento, Short codigoRota,
			Integer idGerenciaRegional, Integer idUnidadeNegocio,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idSetorComercialInicial, Integer idSetorComercialFinal,
			Integer referencia, Integer idImovelPerfil,
			Integer numOcorConsecutivas, String indicadorOcorrenciasIguais,
			Integer mediaConsumoInicial, Integer mediaConsumoFinal,
			Collection<Integer> colecaoIdsAnormalidadeConsumo,
			Collection<Integer> colecaoIdsAnormalidadeLeitura,
			Collection<Integer> colecaoIdsAnormalidadeLeituraInformada,
			Integer tipoMedicao, Collection<Integer> colecaoIdsEmpresa,
			Integer numeroQuadraInicial, Integer numeroQuadraFinal,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * [UC0600] Emitir Histograma de Esgoto
	 * 
	 * [SB0014] Selecionar por Indicador de Consumo [SB0015] Selecionar por
	 * Indicador de Medido [SB0016] Selecionar por Indicador de Poço [SB0017]
	 * Selecionar por Indicador de Volume Fixo de Água
	 * 
	 * @author Rafael Pinto
	 * @date 05/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoHelper
	 * 
	 * @return Collection<Object[]>
	 * @throws ControladorException
	 */
	public Collection<Object[]> pesquisarEmitirHistogramaEsgoto(
			FiltrarEmitirHistogramaEsgotoHelper filtro)
			throws ErroRepositorioException;

	/**
	 * [UC0600] Emitir Histograma de Esgoto - Total Geral
	 * 
	 * [SB0014] Selecionar por Indicador de Consumo [SB0015] Selecionar por
	 * Indicador de Medido [SB0016] Selecionar por Indicador de Poço [SB0017]
	 * Selecionar por Indicador de Volume Fixo de Água
	 * 
	 * @author Rafael Pinto
	 * @date 05/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoHelper
	 * 
	 * @return Object[]
	 * @throws ControladorException
	 */
	public Object[] pesquisarEmitirHistogramaEsgotoTotalGeral(
			FiltrarEmitirHistogramaEsgotoHelper filtro, Categoria categoria)
			throws ErroRepositorioException;

	/**
	 * [UC0600] Emitir Histograma de Esgoto - Volume Faturado Ligacao Estimado
	 * ou Real
	 * 
	 * 
	 * @author Rafael Pinto
	 * @date 05/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoHelper
	 * 
	 * @return Collection<OrdemServico>
	 * @throws ControladorException
	 */
	public Integer pesquisarEmitirHistogramaEsgotoVolumeConsumo(
			FiltrarEmitirHistogramaEsgotoHelper filtro, Short consumo,
			Categoria categoria, Short medicao) throws ErroRepositorioException;

	/**
	 * [UC0606] Emitir Histograma de Esgoto por Economia
	 * 
	 * @author Rafael Pinto
	 * @date 07/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoEconomiaHelper
	 * 
	 * @return Object[]
	 * @throws ControladorException
	 */
	public Object[] pesquisarEmitirHistogramaEsgotoEconomia(
			FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro)
			throws ErroRepositorioException;

	/**
	 * [UC0606] Emitir Histograma de Esgoto por Economia
	 * 
	 * Monta as quebras que serão necessarias para o relatorio
	 * 
	 * @author Rafael Pinto
	 * @date 07/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoEconomiaHelper
	 * 
	 * @return Collection<Object[]>
	 * @throws ControladorException
	 */
	public Collection<Object[]> pesquisarEmitirHistogramaEsgotoEconomiaChavesAgrupadas(
			FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro)
			throws ErroRepositorioException;

	/**
	 * Recupera as contas com estouro de consumo ou com baixo consumo [UC0348] -
	 * Emitir Contas
	 * 
	 * @author Sávio Luiz, Vivianne Sousa
	 * @date 15/05/2006, 20/11/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasNaoFichaCompensacao(Integer idTipoConta,
			Integer idEmpresa, Integer numeroPaginas, Integer anoMesReferencia,
			Integer idFaturamentoGrupo,
			Integer anoMesReferenciaFaturamentoAntecipado,
			Integer imovelContaEnvio) throws ErroRepositorioException;

	/**
	 * Recupera as contas com estouro de consumo ou com baixo consumo [UC0348] -
	 * Emitir Contas
	 * 
	 * @author Sávio Luiz, Vivianne Sousa
	 * @date 15/05/2006, 20/11/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasFichaCompensacao(Integer idTipoConta,
			Integer idEmpresa, Integer numeroPaginas, Integer anoMesReferencia,
			Integer idFaturamentoGrupo,
			Integer anoMesReferenciaFaturamentoAntecipado,
			Integer imovelContaEnvio) throws ErroRepositorioException;

	/**
	 * Recupera as contas com entrega para o cliente responsável [UC0348] -
	 * Emitir Contas
	 * 
	 * @author Sávio Luiz, Vivianne Sousa
	 * @date 15/05/2006, 20/11/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasClienteResponsavelNaoFichaCompensacao(
			Integer idTipoConta, Integer numeroPaginas,
			Integer anoMesReferencia, Integer idFaturamentoGrupo,
			Short indicadorEmissaoExtratoFaturamento,
			Integer anoMesReferenciaFaturamentoAntecipado,
			Integer imovelContaEnvio) throws ErroRepositorioException;

	/**
	 * Recupera as contas com entrega para o cliente responsável [UC0348] -
	 * Emitir Contas
	 * 
	 * @author Sávio Luiz, Vivianne Sousa
	 * @date 15/05/2006, 20/11/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasClienteResponsavelFichaCompensacao(
			Integer idTipoConta, Integer numeroPaginas,
			Integer anoMesReferencia, Integer idFaturamentoGrupo,
			Short indicadorEmissaoExtratoFaturamento,
			Integer anoMesReferenciaFaturamentoAntecipado,
			Integer imovelContaEnvio) throws ErroRepositorioException;

	/**
	 * Pesquisa as contas do cliente responsável para todos os grupos de
	 * faturamento.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * @author Pedro Alexandre,Vivianne Sousa
	 * @date 17/05/2007, 20/11/2007
	 * 
	 * @param idTipoConta
	 * @param numeroPaginas
	 * @param anoMesReferencia
	 * @param indicadorEmissaoExtratoFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasClienteResponsavelNaoFichaCompensacao(
			Integer idTipoConta, Integer numeroPaginas,
			Integer anoMesReferencia, Short indicadorEmissaoExtratoFaturamento,
			Integer anoMesReferenciaFaturamentoAntecipado,
			Integer imovelContaEnvio) throws ErroRepositorioException;

	/**
	 * Pesquisa as contas do cliente responsável para todos os grupos de
	 * faturamento.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * @author Pedro Alexandre,Vivianne Sousa
	 * @date 17/05/2007, 20/11/2007
	 * 
	 * @param idTipoConta
	 * @param numeroPaginas
	 * @param anoMesReferencia
	 * @param indicadorEmissaoExtratoFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasClienteResponsavelFichaCompensacao(
			Integer idTipoConta, Integer numeroPaginas,
			Integer anoMesReferencia, Short indicadorEmissaoExtratoFaturamento,
			Integer anoMesReferenciaFaturamentoAntecipado,
			Integer imovelContaEnvio) throws ErroRepositorioException;

	/**
	 * [UC0626] Gerar Resumo de Metas Acumulado no Mês (CAERN)
	 * 
	 * @author Sávio Luiz
	 * @data 28/11/2007
	 * 
	 * @param idConta
	 * @return idParcelamento
	 */
	public Collection pesquisarIdsContasDoImovelPorMesAnoReferencia(
			int anoMesReferencia, Integer idImovel)
			throws ErroRepositorioException;

	public Boolean pesquisarExisteciaParcelamentoConta(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC0724] - Processar Pagamento com Ficha de Compensação Author: Vivianne
	 * Sousa Data: 26/11/2007
	 * 
	 * @param idConta
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Conta pesquisarExistenciaContaComSituacaoAtual(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * 
	 * atualiza o sequencial de conta impressão e o indicador de
	 * fichaCompensação
	 * 
	 * @author Vivianne Sousa
	 * @date 02/12/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarSequencialContaImpressaoFichaCompensacao(
			Map<Integer, Integer> mapAtualizaSequencial)
			throws ErroRepositorioException;

	/**
	 * [UC0120] - Calcular Valores de Água e/ou Esgoto CAER
	 * 
	 * @author Raphael Rossiter
	 * 
	 * @param idConsumoTarifa
	 * @return ConsumoTarifaVigencia
	 * @throws ErroRepositorioException
	 */
	public ConsumoTarifaVigencia pesquisarConsumoTarifaVigenciaEmVigor(
			Integer idConsumoTarifa) throws ErroRepositorioException;

	/**
	 * [UC00730] Gerar Relatório de Imóveis com Faturas Recentes em Dia e
	 * Faturas Antigas em Atraso
	 * 
	 * @author Rafael Pinto
	 * @date 10/01/2008
	 * 
	 * @param idImovel
	 * 
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarQuantidadeFaturasValorFaturas(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC00730] Gerar Relatório de Imóveis com Faturas Recentes em Dia e
	 * Faturas Antigas em Atraso
	 * 
	 * @author Rafael Pinto
	 * @date 10/01/2008
	 * 
	 * @param idImovel
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarReferenciaAntigaContaSemPagamento(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC00730] Gerar Relatório de Imóveis com Faturas Recentes em Dia e
	 * Faturas Antigas em Atraso
	 * 
	 * @author Rafael Pinto
	 * @date 10/01/2008
	 * 
	 * @param idImovel
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarReferenciaAtualContaSemPagamento(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0737] Atualiza Quantidade de Parcela Paga Consecutiva e Parcela Bônus
	 * 
	 * Retorna id e data vencimento da conta que tem serviço de parcelamento
	 * cobrado DEBITO_COBRADO com CNTA_ID da tabela CONTA e FNTP_ID com valor
	 * correspodente a parcelamento de agua, esgoto ou serviços da tabela
	 * FINANCIAMENTO_TIPO
	 * 
	 * @author Vivianne Sousa
	 * @date 28/12/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarContaImovel(Integer idImovel, int anoMesReferencia)
			throws ErroRepositorioException;

	/**
	 * [UC00113] - Faturar Grupo de Faturamento
	 * 
	 * Recupera o percentual alternativo e o numero de consumo do percentual
	 * alternativo para o imóvel informado.
	 * 
	 * @author Vivianne Sousa
	 * @date 24/01/2008
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterPercentualAlternativoLigacaoEsgotoImovel(
			Integer idImovel) throws ErroRepositorioException;

	/**
	 * [UC0724] - Processar Pagamento com Ficha de Compensação Author: Vivianne
	 * Sousa Data: 28/01/2008
	 * 
	 * @param idConta
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public ContaHistorico pesquisarExistenciaContaHistorico(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC0254] - Efetuar Análise do Movimento dos Arrecadadores
	 * 
	 * obtem imovel, localidade e conta atraves do id da conta
	 * 
	 * @author Vivianne Sousa
	 * @date 29/01/2008
	 * 
	 * @param idConta
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Conta obterImovelLocalidadeConta(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC0254] - Efetuar Análise do Movimento dos Arrecadadores
	 * 
	 * obtem imovel, localidade e contaHistorico atraves do id da conta
	 * historico
	 * 
	 * @author Vivianne Sousa
	 * @date 29/01/2008
	 * 
	 * @param idConta
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public ContaHistorico obterImovelLocalidadeContaHistorico(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC0737] - Atualiza Quantidade de Parcela Paga Consecutiva e Parcela
	 * Bônus
	 * 
	 * @author Vivianne Sousa
	 * @date 07/02/2008
	 * 
	 * @param idDebitoAcobrar
	 * @param prestacaoCobrada
	 * @throws ErroRepositorioException
	 */
	public void adicionaUmNNParcelaBonusDebitoAcobrar(Integer idParcelamento)
			throws ErroRepositorioException;

	/**
	 * [UC0737] - Atualiza Quantidade de Parcela Paga Consecutiva e Parcela
	 * Bônus
	 * 
	 * @author Vivianne Sousa
	 * @date 07/02/2008
	 * 
	 * @param idDebitoAcobrar
	 * @param prestacaoCobrada
	 * @throws ErroRepositorioException
	 */
	public void adicionaUmNNParcelaBonusCreditoARealizar(Integer idParcelamento)
			throws ErroRepositorioException;

	/**
	 * [UC0216] - Calcular Acréscimos por Impontualidade
	 * 
	 * @author Raphael Rossiter
	 * @date 12/02/2008
	 * 
	 * @param idConta
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarContaParaAcrescimoPorImpontualidade(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC0000] - Pré-Faturar Grupo de Faturamento
	 * 
	 * Obter o registro da tabela FaturamentoAtivCronRota referente a rota que
	 * está sendo pré-faturada
	 * 
	 * @author Raphael Rossiter
	 * @date 09/04/2008
	 * 
	 * @param idRota
	 * @param idFaturamentoAtividade
	 * @param idFaturamentoGrupo
	 * @param anoMesReferencia
	 * @return FaturamentoAtivCronRota
	 * @throws ErroRepositorioException
	 */
	public FaturamentoAtivCronRota pesquisarFaturamentoAtivCronRota(
			Integer idRota, Integer idFaturamentoAtividade,
			Integer idFaturamentoGrupo, Integer anoMesReferencia)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de guia de
	 * devolução acumulado, de acordo com o ano/mês de referência contábil, a
	 * situação atual e lançamento item contábil.
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idSituacaoAtual
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection acumularValorGuiaDevolucaoPorLancamentoItemContabil(
			int anoMesReferencia, Integer idLocalidade,
			Integer idSituacaoAtual, Integer idLancamentoItemContabil)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de guia de
	 * devolução acumulado, de acordo com o ano/mês de referência contábil, a
	 * situação atual, a situação anterior e lançamento item contábil.
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection acumularValorGuiaDevolucaoPorLancamentoItemContabil(
			int anoMesReferencia, Integer idLocalidade,
			Integer idSituacaoAtual, Integer idSituacaoAnterior,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 01 Retorna o valor de água
	 * acumulado, de acordo com o ano/mês de referência, a localiade, a
	 * categoria e a situação da conta igual a normal
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no Hibernate
	 */
	public Object[] acumularValorAguaEsgotoPorSituacaoConta(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idSituacaoAtual, int idSituacaoAnterior)
			throws ErroRepositorioException;

	/**
	 * Pesquisa os valores de curto e longo prazo dos débitos a cobrar da
	 * localidade informada por tipo de financiamento.
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 06/03/2008
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idTipoFinanciamento
	 * @param idSituacaoAnterior
	 * @param idSituacaoAtual
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarValorLongoECurtoPrazoDebitoACobrarPorTipoFinanciamento(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idTipoFinanciamento, int idLancamentoItemContabil,
			int idSituacaoAtual, int idSituacaoAnterior)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 10 Retorna o valor de débito
	 * acumulado, de acordo com o ano/mês de referência, a situação da conta
	 * igual a incluída e o tipo de financiamento igual a serviço
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public BigDecimal acumularValorDebitoTipoFinanciamentoServicoSituacaoIncluida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoLancamentoItemContabilRetificada(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, Integer idLancamentoItemContabil)
			throws ErroRepositorioException;

	/**
	 * Acumula o valor de débito cobrado por tipo de financiamento.
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 07/03/2008
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idSituacaoAtual
	 * @param idFinanciamentoTipo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorDebitoCobradoPorTipoFinanciamento(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idSituacaoAtual, int idFinanciamentoTipo)
			throws ErroRepositorioException;

	/**
	 * Retorna o valor de categoria de credito realizado acumulado, de acordo
	 * com o ano/mês de referência, a situação atual, o item de lançamento
	 * contábil e a origem do crédito informados.
	 * 
	 * [UC0155] - Encerrar Faturamento do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 06/03/2008
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsCreditoOrigem
	 * @param idSituacaoAtual
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoLancamentoItemContabil(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, Integer idSituacaoAtual,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	/**
	 * Retorna o valor de categoria de credito realizado acumulado, de acordo
	 * com o ano/mês de referência, a situação atual, o item de lançamento
	 * contábil e a origem do crédito informados.
	 * 
	 * [UC0155] - Encerrar Faturamento do Mês
	 * 
	 * @author Rafael Pinto
	 * @date 21/07/2011
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsCreditoOrigem
	 * @param idSituacaoAtual
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoLancamentoItemContabilCanceladoPorRetificacao(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, Integer idLancamentoItemContabil)
			throws ErroRepositorioException;

	/**
	 * Pesquisa os valores de curto e longo prazo dos débitos a cobrar da
	 * localidade informada por tipo de financiamento.
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 06/03/2008
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idTipoFinanciamento
	 * @param idSituacaoAtual
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarValorLongoECurtoPrazoDebitoACobrarPorTipoFinanciamento(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idTipoFinanciamento, int idLancamentoItemContabil,
			int idSituacaoAtual) throws ErroRepositorioException;

	/**
	 * Acumula o valor das guias de pagamento por tipo de financiamento.
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 07/03/2008
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idTipoFinanciamento
	 * @param idItemContabil
	 * @param idSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorGuiaPagamentoPorTipoFinanciamento(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int[] idTipoFinanciamento, int idItemContabil, int idSituacaoAtual)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês [SB0001] - acumula o valor de
	 * débito cobrado para situação de conta igual a cancelada por retificação
	 * de acordo com o ano/mês de referência
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public BigDecimal acumularValorDebitoCobradoPorTipoFinanciamentoSituacaoContaCanceladaPorRetificacao(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Collection<Integer> tipoFinanciamento, Integer itemContabil)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês [SB0001] - acumula o valor de
	 * débito cobrado para situação de conta igual a retificada de acordo com o
	 * ano/mês de referência
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public BigDecimal acumularValorDebitoCobradoPorTipoFinanciamentoSituacaoContaRetificada(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Collection<Integer> tipoFinaciamento, Integer itemContabil)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de categoria de
	 * débito cobrado acumulado, de acordo com o ano/mês de referência, a
	 * situação atual, tipo de financiamento e pelo lançamento item contábil com
	 * o ano/mês de referência da baixa contábil da conta preenchido
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsFinanciamentoTipo
	 * @param idSituacaoAtual
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> acumularValorCategoriaDebitoCobradoCategoriaPorTipoFinanciamentoComBaixaContabilPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsFinanciamentoTipo, Integer idSituacaoAtual,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de categoria de
	 * credito realizado acumulado, de acordo com o ano/mês de referência, a
	 * situação atual, o item de financiamento contábil e a origem do crédito
	 * informados e com o ano/mês da baixa contábil preenchida.
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsCreditoOrigem
	 * @param idSituacaoAtual
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ResumoFaturamento acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoComBaixaContabilPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, Integer idSituacaoAtual,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de categoria de
	 * débito cobrado acumulado, de acordo com o ano/mês de referência, a
	 * situação atual, tipo de financiamento e pelo lançamento item contábil com
	 * o ano/mês de referência da baixa contábil da conta não preenchido
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsFinanciamentoTipo
	 * @param idSituacaoAtual
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> acumularValorCategoriaDebitoCobradoCategoriaPorTipoFinanciamentoComBaixaContabilNaoPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsFinanciamentoTipo, Integer idSituacaoAtual,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de categoria de
	 * credito realizado acumulado, de acordo com o ano/mês de referência, a
	 * situação atual, o item de financiamento contábil e a origem do crédito
	 * informados e com o ano/mês da baixa contábil não preenchida.
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsCreditoOrigem
	 * @param idSituacaoAtual
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ResumoFaturamento acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoComBaixaContabilNaoPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, Integer idSituacaoAtual,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	/**
	 * Acumula o valor das guias de pagamentos por tipo de financiamento
	 * agrupando por lançamento item contábil.
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 11/03/2008
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @param idFinanciamentoTipo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection acumularValorGuiaPagamentoPorTipoFinanciamentoAgrupandoPorLancamentoItemContabil(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idSituacaoAtual, int idSituacaoAnterior, int idFinanciamentoTipo)
			throws ErroRepositorioException;

	/**
	 * Acumula o valor de água por situação da conta
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 11/03/2008
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorAguaPorSituacaoConta(int anoMesReferencia,
			int idLocalidade, int idCategoria, int idSituacaoAtual)
			throws ErroRepositorioException;

	/**
	 * Acumula o valor de água por situação da conta
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 11/03/2008
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorAguaPorSituacaoConta(int anoMesReferencia,
			int idLocalidade, int idCategoria, int idSituacaoAtual,
			int idSituacaoAnterior) throws ErroRepositorioException;

	/**
	 * Acumula o valor de esgoto por situação da conta
	 * 
	 * [UC0155 Encerrar Faturamentodo Mês]
	 * 
	 * @author Pedro Alexandre
	 * @date 11/03/2008
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorEsgotoPorSituacaoConta(int anoMesReferencia,
			int idLocalidade, int idCategoria, int idSituacaoAtual)
			throws ErroRepositorioException;

	/**
	 * Acumula o valor de esgoto por situação da conta
	 * 
	 * [UC0155 Encerrar Faturamentodo Mês]
	 * 
	 * @author Pedro Alexandre
	 * @date 11/03/2008
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorEsgotoPorSituacaoConta(int anoMesReferencia,
			int idLocalidade, int idCategoria, int idSituacaoAtual,
			int idSituacaoAnterior) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de categoria de
	 * credito realizado acumulado, de acordo com o ano/mês de referência, a
	 * situação atual, e a origem do crédito informados.
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsCreditoOrigem
	 * @param idSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoRetificada(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de categoria de
	 * credito realizado acumulado, de acordo com o ano/mês de referência, a
	 * situação atual, a situação anterior e a origem do crédito informados.
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsCreditoOrigem
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoCanceladoPorRetificacao(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de categoria de
	 * credito realizado acumulado, de acordo com o ano/mês de referência, a
	 * situação atual, e a origem do crédito informados.
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsCreditoOrigem
	 * @param idSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCredito(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	/**
	 * Pesquisa os valores de curto e longo prazo dos débitos a cobrar da
	 * localidade informada por tipo de financiamento.
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 06/03/2008
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idTipoFinanciamento
	 * @param idSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarValorLongoECurtoPrazoDebitoACobrarPorTipoFinanciamento(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idTipoFinanciamento, int idSituacaoAtual)
			throws ErroRepositorioException;

	/**
	 * Acumula o valor de débito cobrado por tipo de financiamento.
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 07/03/2008
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idSituacaoAtual
	 * @param idFinanciamentoTipo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorDebitoCobradoPorTipoFinanciamentoCanceladaPorRetificacao(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idFinanciamentoTipo) throws ErroRepositorioException;

	/**
	 * Acumula o valor de débito cobrado por tipo de financiamento.
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 07/03/2008
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idLancamentoItemContabil
	 * @param idSituacaoAtual
	 * @param idFinanciamentoTipo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorDebitoCobradoPorTipoFinanciamento(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idLancamentoItemContabil, int idSituacaoAtual,
			Collection<Integer> idFinanciamentoTipo)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 41 Retorna o valor de
	 * categoria de débito acumulado, de acordo com o ano/mês de referência, a
	 * situação da conta igual a normal e o tipo de financiamento igual a
	 * serviço, quando o número de prestações cobradas for maior que 11(onze)
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public BigDecimal acumularValorCategoriaDebitoTipoFinanciamentoServicoSituacaoNormalNumeroPrestacoesCobradasMaiorQue11(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idSituacaoAtual, int idSituacaoAnterior, int idFinanciamentoTipo)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Linha 42 Retorna o valor de
	 * categoria de débito cobrado acumulado, de acordo com o ano/mês de
	 * referência, a situação igual a normal e o tipo de financiamento igual a
	 * juros de parcelamento e a diferença de prestações maior que 11(onze)
	 * 
	 * @param anoMesReferencia
	 *            Ano e mês de referência do faturamento
	 * @param idLocalidade
	 *            Código da localidade
	 * @param idCategoria
	 *            Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public ResumoFaturamento acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoJurosParcelamentoSituacaoNormalDiferencaPrestacoesMaiorQue11(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idFinanciamentoTipo, int idSituacaoAtual, int idSituacaoAnterior)
			throws ErroRepositorioException;

	/**
	 * Pesquisa os valores de curto e longo prazo dos débitos a cobrar da
	 * localidade informada por grupo de parcelamento.
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 06/03/2008
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idGrupoParcelamento
	 * @param idSituacaoAnterior
	 * @param idSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarValorLongoECurtoPrazoDebitoACobrarPorGrupoParcelamento(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idGrupoParcelamento, int idSituacaoAtual,
			int idSituacaoAnterior, boolean flagNPrestacaoCobradaIgualAZero)
			throws ErroRepositorioException;

	/**
	 * Acumula o valor de curto e longo prazo para creditos a realizar por
	 * origem de crédito.
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 13/03/2008
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsCreditosOrigem
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarValorLongoECurtoPrazoCreditoARealizarPorOrigemCredito(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditosOrigem, int idSituacaoAtual,
			int idSituacaoAnterior) throws ErroRepositorioException;

	/**
	 * Acumula o valor do débito cobrado por tipo de financiamento agrupando por
	 * lançamento item contábil.
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 13/03/2008
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @param idTipoFinanciamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection acumularValorDebitoCobradoPorTipoFinanciamentoAgrupandoPorLancamentoItemContabil(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idSituacaoAtual, int idSituacaoAnterior, int idTipoFinanciamento)
			throws ErroRepositorioException;

	/**
	 * Acumula o valor de débito cobrado por tipo de financiamento.
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 07/03/2008
	 * 
	 * @param anoMesReferencia
	 * @param localidade
	 * @param idCategoria
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @param idFinanciamentoTipo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorDebitoCobradoPorTipoFinanciamentoPorReferenciaConta(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idSituacaoAtual, int idSituacaoAnterior, int idFinanciamentoTipo)
			throws ErroRepositorioException;

	/**
	 * Acumula valor do imposto por tipo de imposto.
	 * 
	 * [UC155] Encerrar Faturamento do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 14/03/2008
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idTipoImposto
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorContaCategoriaPorTipoImposto(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idTipoImposto, int idSituacaoAtual, int idSituacaoAnterior)
			throws ErroRepositorioException;

	/**
	 * Acumula o valor de crédito realizado por origem de crédito e pela
	 * referência da conta.
	 * 
	 * [UC0155] - Encerrar Faturamento do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 14/03/2008
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsCreditoOrigem
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoPorReferenciaConta(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, int idSituacaoAtual,
			int idSituacaoAnterior) throws ErroRepositorioException;

	/**
	 * Acumula o valor de crédito realizado po origem de crédito agrupando por
	 * lançamento item contábil.
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 17/03/2008
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsCreditoOrigem
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection acumularValorCreditoRealizadoCategoriaPorOrigemCreditoAgrupandoPorLancamentoItemContabil(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, int idSituacaoAtual,
			int idSituacaoAnterior) throws ErroRepositorioException;

	/**
	 * Acumula o valor de imposto por tipo de imposto.
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 17/03/2008
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idTipoImposto
	 * @param idSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorContaCategoriaPorTipoImposto(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idTipoImposto, int idSituacaoAtual)
			throws ErroRepositorioException;

	/**
	 * Acumula o valor do imposto por tipo de imposto por referência contábil.
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 17/03/2008
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idTipoImposto
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorContaCategoriaPorTipoImpostoReferenciaContabil(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idTipoImposto, int idSituacaoAtual, int idSituacaoAnterior)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de águae o de
	 * esgoto acumulado, de acordo com o ano/mês de referência, a localiade, a
	 * categoria e a situação da conta igual aos ids informados com ano/mês da
	 * baixa contábil preenchida
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] acumularValorAguaEsgotoPorSituacaoContaComBaixaContabilPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsSituacaoAtual) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de categoria de
	 * débito cobrado acumulado, de acordo com o ano/mês de referência, a(s)
	 * situação(ões) atual(ais) com o ano/mês de referência da baixa contábil da
	 * conta preenchido.
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaDebitoCobradoCategoriaComBaixaContabilPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsSituacaoAtual) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de águae o de
	 * esgoto acumulado, de acordo com o ano/mês de referência, a localiade, a
	 * categoria e a situação da conta igual aos ids informados com ano/mês da
	 * baixa contábil não preenchida
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] acumularValorAguaEsgotoPorSituacaoContaComBaixaContabilNaoPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsSituacaoAtual) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de categoria de
	 * débito cobrado acumulado, de acordo com o ano/mês de referência, a
	 * situação atual e o tipo de financiamento com o ano/mês de referência da
	 * baixa contábil da conta preenchido.
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idFinanciamentoTipo
	 * @param idSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaDebitoCobradoCategoriaPorTipoFinanciamentoComBaixaContabilPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idFinanciamentoTipo, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de categoria de
	 * credito realizado acumulado, de acordo com o ano/mês de referência, a
	 * situação atual e a origem do crédito informados e com o ano/mês da baixa
	 * contábil preenchida.
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idCreditoOrigem
	 * @param idSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoComBaixaContabilPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idCreditoOrigem, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor do imposto
	 * acumulado de acordo com o ano/mês de referência da conta, a situação
	 * atual da conta e o tipo de imposto e com o ano/mês da baixa contábil da
	 * conta preenchido.
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idImpostoTipo
	 * @param idSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 */
	public BigDecimal acumularValorImpostoPorTipoImpostoESituacaoContaComBaixaContabilPreenchida(
			int anoMesReferencia, Integer idLocalidade, Integer idCategoria,
			Integer idImpostoTipo, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de categoria de
	 * débito cobrado acumulado, de acordo com o ano/mês de referência, a
	 * situação atual e o tipo de financiamento com o ano/mês de referência da
	 * baixa contábil da conta não preenchido.
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idFinanciamentoTipo
	 * @param idSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaDebitoCobradoCategoriaPorTipoFinanciamentoComBaixaContabilNaoPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idFinanciamentoTipo, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de categoria de
	 * credito realizado acumulado, de acordo com o ano/mês de referência, a
	 * situação atual e a origem do crédito informados e com o ano/mês da baixa
	 * contábil não preenchida.
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idCreditoOrigem
	 * @param idSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ResumoFaturamento acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoComBaixaContabilNaoPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idCreditoOrigem, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor do imposto
	 * acumulado de acordo com o ano/mês de referência da conta, a situação
	 * atual da conta e o tipo de imposto e com o ano/mês da baixa contábil da
	 * conta não preenchido.
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idImpostoTipo
	 * @param idSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 */
	public BigDecimal acumularValorImpostoPorTipoImpostoESituacaoContaComBaixaContabilNaoPreenchida(
			int anoMesReferencia, Integer idLocalidade, Integer idCategoria,
			Integer idImpostoTipo, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês
	 * 
	 * Retorna o valor de crédito a realizar acumulado, de acordo com o ano/mês
	 * de referência contábil, a situação atual ou anterior e a origem de
	 * crédito informados.
	 * 
	 * @author Pedro Alexandre, Raphael Rossiter
	 * @date 00/00/0000, 24/03/2009
	 * 
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idsOrigemCredito
	 * @param idSituacaoAtual
	 * @param categoria
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection acumularValorCreditoARealizarPorOrigemCredito(
			int anoMesReferenciaContabil, Integer idLocalidade,
			Integer[] idsOrigemCredito, Integer idSituacaoAtual,
			Categoria categoria) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês
	 * 
	 * Retorna o valor de crédito a realizar acumulado, de acordo com o ano/mês
	 * de referência contábil, a situação atual e a origem de crédito
	 * informados.
	 * 
	 * @author Pedro Alexandre, Raphael Rossiter
	 * @date 00/00/0000, 24/03/2009
	 * 
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idOrigemCredito
	 * @param idSituacaoAtual
	 * @param categoria
	 * @return BigDecimal
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCreditoARealizarPorOrigemCredito(
			int anoMesReferenciaContabil, Integer idLocalidade,
			Integer idOrigemCredito, Integer idSituacaoAtual,
			Categoria categoria) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de crédito a
	 * realizar acumulado, de acordo com o ano/mês de referência contábil, a
	 * situação atual ou anterior e a origem de crédito informados.
	 * 
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idsOrigemCredito
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @param categoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection acumularValorCreditoARealizarPorOrigemCredito(
			int anoMesReferenciaContabil, Integer idLocalidade,
			Integer[] idsOrigemCredito, Integer idSituacaoAtual,
			Integer idSituacaoAnterior, Categoria categoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de crédito a
	 * realizar acumulado, de acordo com o ano/mês de referência contábil, a
	 * situação atual ou anterior e a origem de crédito informados.
	 * 
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idOrigemCredito
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @param categoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCreditoARealizarPorOrigemCredito(
			int anoMesReferenciaContabil, Integer idLocalidade,
			Integer idOrigemCredito, Integer idSituacaoAtual,
			Integer idSituacaoAnterior, Categoria categoria)
			throws ErroRepositorioException;

	/**
	 * Acumula o valor do débito a cobrar por tipo de financiamento
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 24/03/2008
	 * 
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsTipoFinanciamento
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorDebitoACobrarCategoriaPorTipoFinanciamento(
			int anoMesReferenciaContabil, int idLocalidade, int idCategoria,
			Integer[] idsTipoFinanciamento, int idSituacaoAtual,
			int idSituacaoAnterior) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de crédito a
	 * realizar acumulado, de acordo com o ano/mês de referência contábil, a
	 * situação atual ou anterior e a origem de crédito informados.
	 * 
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idOrigemCredito
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCreditoARealizarPorOrigemCredito(
			int anoMesReferenciaContabil, Integer idLocalidade,
			Integer idCategoria, Integer idOrigemCredito,
			Integer idSituacaoAtual, Integer idSituacaoAnterior)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês
	 * 
	 * Retorna o valor de crédito a realizar acumulado, de acordo com o ano/mês
	 * de referência contábil, a situação atual e a origem de crédito
	 * informados.
	 * 
	 * @author Pedro Alexandre, Raphael Rossiter
	 * @date 00/00/0000, 24/03/2009
	 * 
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idOrigemCredito
	 * @param idSituacaoAtual
	 * @return BigDecimal
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCreditoARealizarPorOrigemCredito(
			int anoMesReferenciaContabil, Integer idLocalidade,
			Integer idCategoria, Integer idOrigemCredito,
			Integer idSituacaoAtual) throws ErroRepositorioException;

	/**
	 * Acumula o valor de débito cobrado por tipo de financiamento.
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 07/03/2008
	 * 
	 * @param anoMesReferencia
	 * @param localidade
	 * @param idCategoria
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @param idFinanciamentoTipo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorDebitoCobradoPorTipoFinanciamentoRetificada(
			int anoMesReferencia, Localidade localidade, int idCategoria,
			int idFinanciamentoTipo) throws ErroRepositorioException;

	/**
	 * Pesquisa os valores de curto e longo prazo dos débitos a cobrar da
	 * localidade informada por tipo de financiamento agrupando por lançamento
	 * item contábil.
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 16/04/2008
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idTipoFinanciamento
	 * @param idSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorLongoECurtoPrazoDebitoACobrarPorTipoFinanciamentoAgrupandoPorLancamentoItemContabil(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idTipoFinanciamento, int idSituacaoAtual)
			throws ErroRepositorioException;

	/**
	 * Pesquisa os valores de curto e longo prazo dos débitos a cobrar da
	 * localidade informada por tipo de financiamento e agrupando por lançamento
	 * item contábil.
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 16/04/2008
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idTipoFinanciamento
	 * @param idSituacaoAnterior
	 * @param idSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorLongoECurtoPrazoDebitoACobrarPorTipoFinanciamentoAgrupandoPorLancamentoItemContabil(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idTipoFinanciamento, int idSituacaoAtual, int idSituacaoAnterior)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de categoria de
	 * débito cobrado acumulado, de acordo com o ano/mês de referência, a
	 * situação atual, tipo de financiamento e pelo lançamento item contábil com
	 * o ano/mês de referência da baixa contábil da conta não preenchido
	 * agrupando por lançamento item contábil.
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsFinanciamentoTipo
	 * @param idSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> acumularValorCategoriaDebitoCobradoCategoriaPorTipoFinanciamentoComBaixaContabilNaoPreenchidaAgrupandoPorLancamentoItemContabil(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsFinanciamentoTipo, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de categoria de
	 * débito cobrado acumulado, de acordo com o ano/mês de referência, a
	 * situação atual, tipo de financiamento e pelo lançamento item contábil com
	 * o ano/mês de referência da baixa contábil da conta preenchido agrupando
	 * por lançamento item contábil.
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsFinanciamentoTipo
	 * @param idSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> acumularValorCategoriaDebitoCobradoCategoriaPorTipoFinanciamentoComBaixaContabilPreenchidaAgrupandoPorLancamentoItemContabil(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsFinanciamentoTipo, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de categoria de
	 * credito realizado acumulado, de acordo com o ano/mês de referência, a
	 * situação atual, o item de financiamento contábil e a origem do crédito
	 * informados e com o ano/mês da baixa contábil não preenchida.
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsCreditoOrigem
	 * @param idSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoComBaixaContabilNaoPreenchidaAgrupandoPorLancamentoItemContabil(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de categoria de
	 * credito realizado acumulado, de acordo com o ano/mês de referência, a
	 * situação atual, o item de financiamento contábil e a origem do crédito
	 * informados e com o ano/mês da baixa contábil preenchida.
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsCreditoOrigem
	 * @param idSituacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoComBaixaContabilPreenchidaAgrupandoPorLancamentoItemContabil(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	/**
	 * Pesquisa para cada imóvel da rota informada a principal categoria.
	 * 
	 * [UC0302] - Gerar Débitos a Cobrar de Acréscimos por Impontualidade
	 * 
	 * @author Pedro Alexandre
	 * @date 05/05/2008
	 * 
	 * @param codigoEmpresaFebraban
	 * @param idRota
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarPrincipalCategoriaImovelPorRota(
			Short codigoEmpresaFebraban, Integer idRota)
			throws ErroRepositorioException;

	/**
	 * Pesquisa as contas parao imóvel com situação igual a norma, retificada ou
	 * incluida que tenha pagamento.
	 * 
	 * [UC0302] - Gerar Débitos a Cobrar de Acréscimos por Impontualidade
	 * 
	 * @author Pedro Alexandre
	 * @date 08/05/2008
	 * 
	 * @param imovel
	 * @param situacaoNormal
	 * @param situacaoIncluida
	 * @param situacaoRetificada
	 * @param dataAnoMesReferenciaUltimoDia
	 * @param anoMesArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterContasImovelComPagamento(Integer imovel,
			Integer situacaoNormal, Integer situacaoIncluida,
			Integer situacaoRetificada, Date dataAnoMesReferenciaUltimoDia,
			Integer anoMesArrecadacao) throws ErroRepositorioException;

	/**
	 * Retorna as contas com o indicador de pagamento para a conta.
	 * 
	 * [UC0302] - Gerar Débitos a Cobrar de Acréscimos por Impontualidade
	 * 
	 * @author Pedro Alexandre
	 * @date 08/05/2008
	 * 
	 * @param idsConta
	 * @param anoMesReferenciaConta
	 * @param anoMesReferenciaAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> obterIndicadorPagamentosClassificadosContaReferenciaMenorIgualAtual(
			Collection<Integer> idsConta, Integer anoMesReferenciaAtual)
			throws ErroRepositorioException;

	/**
	 * pesquisa o indicado de acréscimo para o cliente do imóvel.
	 * 
	 * [UC0302]-Gerar Débito a cobrar de Acréscimos por Impontualidade
	 * 
	 * @author Pedro Alexandre
	 * @date 09/05/2008
	 * 
	 * @param idRota
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> obterIndicadorGeracaoAcrescimosClienteImovel(
			Integer idRota) throws ErroRepositorioException;

	// ******************************************************************************************
	// [UC0764] Gerar Relatorio Contas Canceladas ou Retificadas
	// Flávio Leonardo
	// 09/05/2008
	// contas canceladas com faturamento fechado(pesquisa em conta historico)
	public Collection pesquisarContasCanceladasFaturamentoFechado(
			RelatorioContasCanceladasRetificadasHelper helper);

	// contas canceladas com faturamento Aberto(pesquisa em conta)
	public Collection pesquisarContasCanceladasFaturamentoAberto(
			RelatorioContasCanceladasRetificadasHelper helper);

	// contas retificadas com faturamento fechado(pesquisa em conta historico)
	public Collection pesquisarContasRetificadasFaturamentoFechado(
			RelatorioContasCanceladasRetificadasHelper helper);

	// contas retificadas com faturamento Aberto(pesquisa em conta)
	public Collection pesquisarContasRetificadasFaturamentoAberto(
			RelatorioContasCanceladasRetificadasHelper helper);

	// contas Retificadas(pesquisa em conta)
	public Collection pesquisarContasRetificadasValorNovoConta(
			RelatorioContasCanceladasRetificadasHelper helper, String idImovel,
			String anoMesReferenciaContaOriginal);

	// contas Retificadas(pesquisa em conta)
	public Collection pesquisarContasRetificadasValorNovoContaAberta(
			RelatorioContasCanceladasRetificadasHelper helper, String idImovel,
			String anoMesReferenciaContaOriginal);

	// contas Retificadas(pesquisa em contaHistorico)
	public Collection pesquisarContasRetificadasValorNovoContaHistorico(
			RelatorioContasCanceladasRetificadasHelper helper, String idImovel,
			String anoMesReferenciaContaOriginal);

	// *******************************************************************************************

	/**
	 * @author Vivianne Sousa
	 * @date 15/05/2008
	 */
	public Integer pesquisarMaxIdConta() throws ErroRepositorioException;

	/**
	 * @author Vivianne Sousa
	 * @date 15/05/2008
	 */
	public Integer pesquisarMaxIdContaHistorico()
			throws ErroRepositorioException;

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [FS0002] - Verificar Situação Especial de Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 17/04/2008
	 * 
	 * @param rota
	 * @param numeroPaginas
	 * @param quantidadeRegistros
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImovelGerarArquivoTextoFaturamento(Rota rota,
			int numeroPaginas, int quantidadeRegistros,
			SistemaParametro sistemaParametro, Integer idImovelCondominio)
			throws ErroRepositorioException;

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 17/04/2008
	 * 
	 * @param idRota
	 * @param anoMesReferencia
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarArquivoTextoRoteiroEmpresa(Integer idRota,
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 23/04/2008
	 * 
	 * @param idImovel
	 * @param anoMesReferencia
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarContaGerarArquivoTextoFaturamento(
			Integer idImovel, Integer anoMesReferencia,
			Integer idFaturamentoGrupo) throws ErroRepositorioException;

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0002] - Obter dados dos serviços de parcelamento
	 * 
	 * @author Raphael Rossiter
	 * @date 25/04/2008
	 * 
	 * @param conta
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDebitoCobradoDeParcelamento(Conta conta)
			throws ErroRepositorioException;

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0002] - Obter dados dos serviços de parcelamento
	 * 
	 * @author Raphael Rossiter
	 * @date 25/04/2008
	 * 
	 * @param conta
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitoCobradoNaoParcelamento(Conta conta)
			throws ErroRepositorioException;

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0003] - Obter dados dos créditos realizados
	 * 
	 * @author Raphael Rossiter
	 * @date 29/04/2008
	 * 
	 * @param conta
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCreditoRealizado(Conta conta)
			throws ErroRepositorioException;

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 02/05/2008
	 * 
	 * @param idRota
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarIntervaloNumeroQuadraPorRota(Integer idRota)
			throws ErroRepositorioException;

	/**
	 * [UC0259] - Processar Pagamento com Código de Barras - LEGADO
	 * 
	 * [SB0014] - Processar Pagamento Legado CAEMA
	 * 
	 * @author Raphael Rossiter
	 * @date 27/05/2008
	 * 
	 * @param codigoQualifica
	 * @param dataVencimento
	 * @param valorDebito
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarFaturaItem(Integer idCliente,
			Integer anoMesReferencia, BigDecimal valorDebito)
			throws ErroRepositorioException;

	/**
	 * [UC0147] - Cancelar Conta
	 * 
	 * Atualizar a conta que está sendo cancelada com o ra que está autorizando
	 * o cancelamento da mesma.
	 * 
	 * @author Raphael Rossiter
	 * @date 17/06/2008
	 * 
	 * @param conta
	 * @param registroAtendimento
	 * @throws ErroRepositorioException
	 */
	public void atualizarContaCanceladaOuRetificada(Conta conta,
			RegistroAtendimento registroAtendimento)
			throws ErroRepositorioException;

	/**
	 * [UC0352] - Emitir Contas
	 * 
	 * [SB0036] - Obter Valor dos Debitos Cobrados Servicos - CAEMA
	 * 
	 * @author Tiago Moreno
	 * @date 03/07/2008
	 * 
	 * @param id
	 *            da conta
	 * @throws ErroRepositorioException
	 */

	public BigDecimal obterValorDebitoServico(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC0352] - Emitir Contas
	 * 
	 * [SB0037] - Obter Valor dos Debitos Cobrados Multas - CAEMA
	 * 
	 * @author Tiago Moreno
	 * @date 03/07/2008
	 * 
	 * @param id
	 *            da conta
	 * @throws ErroRepositorioException
	 */

	public BigDecimal obterValorDebitoMultas(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC0641] - Emitir TXT de Fatura de Cliente Responsável
	 * 
	 * @author Rafael Corrêa
	 * @date 10/07/2008
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosRelatorioFaturasAgrupadas(
			Integer anoMesReferencia, Cliente cliente,
			Collection<Integer> idsClientes) throws ErroRepositorioException;

	/**
	 * [UC0641] - Emitir TXT de Fatura de Cliente Responsável
	 * 
	 * @author Rafael Corrêa
	 * @date 10/07/2008
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarDadosRelatorioFaturasAgrupadasCount(
			Integer anoMesReferencia, Cliente cliente,
			Collection<Integer> idsClientes) throws ErroRepositorioException;

	/**
	 * [UC0352] - Emitir TXT de Fatura de Cliente Responsável
	 * 
	 * Pesquisa a quantidade de itens de faturas para o cliente responsável
	 * 
	 * @author Rafael Corrêa
	 * @date 12/07/2008
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeFaturasReponsavel(
			Integer anoMesReferencia, Integer idCliente)
			throws ErroRepositorioException;

	/**
	 * [UC0641] - Emitir TXT de Fatura de Cliente Responsável
	 * 
	 * Pesquisa os clientes associados as faturas de uma determinada esfera de
	 * porder
	 * 
	 * @author Rafael Corrêa
	 * @date 21/01/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClientesFaturas(Integer idEsferaPoder)
			throws ErroRepositorioException;

	/**
	 * [UC0352] - Emitir TXT de Fatura de Cliente Responsável
	 * 
	 * Pesquisa o percentual de retenção
	 * 
	 * @author Rafael Corrêa
	 * @date 12/07/2008
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarPercentualAliquota()
			throws ErroRepositorioException;

	/**
	 * [UCXXXX] - Relatório de Protocolo de Entrega de Faturas
	 * 
	 * @author Rafael Corrêa
	 * @date 12/11/2008
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosRelatorioProtocoloEntregaFatura(
			Integer anoMesReferencia, Cliente cliente,
			Collection<Integer> idsClientes) throws ErroRepositorioException;

	/**
	 * [UC0153] - Apresentar Dados Para Análise da Medição e Consumo
	 * 
	 * Pesquisa a situação especial de faturamento vigente do mês/ano informada
	 * 
	 * @author Rafael Corrêa
	 * @date 11/08/2008
	 * 
	 * @param idImovel
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarSituacaoEspecialFaturamentoVigente(
			Integer idImovel, Integer anoMesReferencia)
			throws ErroRepositorioException;

	/**
	 * [UC0352] - Emitir Contas
	 * 
	 * [SB0041] - Gerar Linhas das Faixas de Consumo da Conta - CAEMA
	 * 
	 * Pesquisa as faixas da subcategoria
	 * 
	 * @author Rafael Corrêa
	 * @date 03/07/2008
	 * 
	 * @param idSubcategoria
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarConsumoTarifaFaixaPelaSubcategoria(
			Integer idSubcategoria) throws ErroRepositorioException;

	/**
	 * [UC0194] Inserir Credito a Realizar
	 * 
	 * Pesquisa a quantidade de contas e contas histórico para um imóvel em uma
	 * referência
	 * 
	 * @author Rafael Corrêa
	 * @date 14/08/2008
	 * 
	 * @param idImovel
	 * @param referenciaConta
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeContasEContasHistorico(Integer idImovel,
			Integer referenciaConta) throws ErroRepositorioException;

	/**
	 * [UC0857] - Gerar Relatório de Arrecadação das Multas de Autos de Infração
	 * 
	 * Pesquisa os dados necessários para geração do relatório
	 * 
	 * @author Rafael Corrêa
	 * @date 10/09/2008
	 * 
	 * @param idUnidadeNegocio
	 *            , idFuncionario, dataPagamentoInicial, dataPagamentoFinal
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosRelatorioAutoInfracao(
			Integer idUnidadeNegocio, Integer idFuncionario,
			Integer dataPagamentoInicial, Integer dataPagamentoFinal)
			throws ErroRepositorioException;

	/**
	 * Exclui resumo faturamento do ano/mês de faturamento corrente por
	 * localidade
	 * 
	 * [UC0155] - Encerrar Faturamento do Mês
	 * 
	 * @author Vivianne Sousa
	 * @date 11/08/2008
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */
	public void excluirResumoFaturamentoPorAnoMesArrecadacaoPorLocalidade(
			int anoMesReferenciaFaturamento, Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * [UC0866] Gerar Comando Contas em Cobrança por Empresa
	 * 
	 * Pesquisa a quantidade de contas
	 * 
	 * @author: Rafael Corrêa
	 * @date: 27/10/2008
	 */
	public Collection pesquisarQuantidadeContas(
			ComandoEmpresaCobrancaContaHelper comandoEmpresaCobrancaContaHelper)
			throws ErroRepositorioException;

	/**
	 * [UC0870] Gerar Movimento de Contas em Cobrança por Empresa
	 * 
	 * Pesquisa os imóveis das contas
	 * 
	 * @author: Rafael Corrêa
	 * @date: 28/10/2008
	 */
	public Collection<Integer> pesquisarImoveisInformarContasEmCobranca(
			ComandoEmpresaCobrancaContaHelper comandoEmpresaCobrancaContaHelper,
			Integer numeroPagina, boolean percentualInformado)
			throws ErroRepositorioException;

	/**
	 * [????] Informar Subdivisões de Rota
	 * 
	 * Verifica se esse grupo de Faturamento já está comandado para a atividade
	 * Gerar Arquivo de Leitura
	 * 
	 * @author: Victor Cisneiros
	 * @date: 30/09/2008
	 */
	public Boolean verificarGrupoFaturamentoComandado(
			int anoMesReferenciaFaturamento, int idGrupoFaturamento)
			throws ErroRepositorioException;

	/**
	 * 
	 * @author Sávio Luiz
	 * @date 24/10/2008
	 * 
	 * @param conta
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisaridDebitoTipoDoDebitoCobradoDeParcelamento(
			Integer idConta, Collection idsFinanciamentoTipo)
			throws ErroRepositorioException;

	/**
	 * [UC0150] Retificar Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 17/11/2008
	 * 
	 * @param idConta
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarFaturaItemDeConta(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC0150] Retificar Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 17/11/2008
	 * 
	 * @param idFaturaItem
	 * @param conta
	 * @throws ErroRepositorioException
	 */
	public void atualizarContaEmFaturaItem(Integer idFaturaItem, Conta conta,
			Integer consumoFaturaItem) throws ErroRepositorioException;

	/**
	 * [UC0150] Retificar Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 17/11/2008
	 * 
	 * @param idFatura
	 * @param valorDebitoFatura
	 * @throws ErroRepositorioException
	 */
	public void atualizarValorDebitoFatura(Integer idFatura,
			BigDecimal valorDebitoFatura) throws ErroRepositorioException;

	/**
	 * [UC0193] - Consultar Histórico de Faturamento
	 * 
	 * @author Vivianne Sousa
	 * @date 11/11/2008
	 * 
	 * @param imovelID
	 */
	public Collection obterDebitoACobrarImovel(Integer imovelID)
			throws ErroRepositorioException;

	/**
	 * [UC0193] - Consultar Histórico de Faturamento
	 * 
	 * @author Vivianne Sousa
	 * @date 11/11/2008
	 * 
	 * @param imovelID
	 */
	public Collection obterDebitoACobrarHistoricoImovel(Integer imovelID)
			throws ErroRepositorioException;

	/**
	 * [UC0193] - Consultar Histórico de Faturamento
	 * 
	 * @author Vivianne Sousa
	 * @date 11/11/2008
	 * 
	 * @param imovelID
	 */
	public Collection obterCreditoARealizarImovel(Integer imovelID)
			throws ErroRepositorioException;

	/**
	 * [UC0193] - Consultar Histórico de Faturamento
	 * 
	 * @author Vivianne Sousa
	 * @date 11/11/2008
	 * 
	 * @param imovelID
	 */
	public Collection obterCreditoARealizarHistoricoImovel(Integer imovelID)
			throws ErroRepositorioException;

	/**
	 * [UC0193] - Consultar Histórico de Faturamento
	 * 
	 * @author Vivianne Sousa
	 * @date 12/11/2008
	 * 
	 * @param imovelID
	 */
	public Collection obterGuiaPagamentoImovel(Integer imovelID)
			throws ErroRepositorioException;

	/**
	 * [UC0193] - Consultar Histórico de Faturamento
	 * 
	 * @author Vivianne Sousa
	 * @date 12/11/2008
	 * 
	 * @param imovelID
	 */
	public Collection obterGuiaPagamentoHistoricoImovel(Integer imovelID)
			throws ErroRepositorioException;

	/**
	 * Alterar Vencimento do Conjunto de Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 01/12/2008
	 * 
	 * @param codigoCliente
	 * @param anoMes
	 * @param dataVencimentoFaturaInicio
	 * @param dataVencimentoFaturaFim
	 * @param anoMesFim
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarFaturasCliente(Integer codigoCliente,
			Integer anoMes, Date dataVencimentoFaturaInicio,
			Date dataVencimentoFaturaFim, Integer anoMesFim,
			Integer codigoClienteSuperior) throws ErroRepositorioException;

	/**
	 * Alterar Vencimento do Conjunto de Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 01/12/2008
	 * 
	 * @param fatura
	 * @throws ErroRepositorioException
	 */
	public void alterarVencimentoFatura(Fatura fatura)
			throws ErroRepositorioException;

	/**
	 * [UC0113] Faturar Grupo de Faturamento
	 * 
	 * É necessário colocar a query abaixo no processo de faturar grupo como uma
	 * funcionalidade (Antes de rodar o faturar), para atender uma necessidade
	 * de uma localidade (Petrolina), onde existe uma cobrança diferenciada de
	 * esgoto. CRC771 - Socorro Oliveira
	 * 
	 * @author Raphael Rossiter
	 * @date 22/12/2008
	 * 
	 * @param percentualAlternativo
	 * @param consumoPercentualAlternativo
	 * @param rota
	 * @throws ErroRepositorioException
	 */
	public void atualizarLigacaoEsgotoPorRota(BigDecimal percentualAlternativo,
			Integer consumoPercentualAlternativo, Rota rota)
			throws ErroRepositorioException;

	/**
	 * [UC0150] Retificar Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 06/01/2009
	 * 
	 * @param idConta
	 * @return ContaMotivoRevisao
	 * @throws ErroRepositorioException
	 */
	public ContaMotivoRevisao pesquisarContaMotivoRevisao(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Pesquisar categoria por tarifa consumo
	 * 
	 * @author Rômulo Aurélio
	 * @date 19/12/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarCategoriaPorTarifaConsumo(Integer idConsumoTarifa)
			throws ErroRepositorioException;

	/**
	 * [UC0877] EmitirGuiaPagamentoEmAtraso
	 * 
	 * @author Flávio Leonardo
	 * @date 27/01/2009
	 */
	public Collection pesquisarDadosRelatorioGuiaPagamentoEmAtraso(
			FiltroGuiaPagamento filtro) throws ErroRepositorioException;

	/**
	 * Emitir Contas CAERN
	 * 
	 * Obter dados dos créditos realizados Referente a Ação Judicial da CAERN
	 * (50% de Agua)
	 * 
	 * @author Tiago Moreno
	 * @date 23/01/2009
	 * 
	 * @param conta
	 * @return CreditoRealizado
	 * @throws ErroRepositorioException
	 */
	public CreditoRealizado pesquisarCreditoRealizadoNitrato(Conta conta)
			throws ErroRepositorioException;

	/**
	 * [UC0871] Manter Fatura de Cliente Responsável
	 */
	public BigDecimal somarValorFaturasItemFatura(Fatura fatura)
			throws ErroRepositorioException;

	public Integer maximoNumeroSequencia(Fatura fatura)
			throws ErroRepositorioException;

	public Integer maximoNumeroSequenciaFaturaItem(Fatura fatura)
			throws ErroRepositorioException;

	public Date vencimentoFaturasItemFatura(Fatura fatura)
			throws ErroRepositorioException;

	/**
	 * [UC0871] Manter Fatura de Cliente Responsável
	 * 
	 * @param fatura
	 * @throws ErroRepositorioException
	 */
	public void alterarVencimentoFaturaFaturaItem(Fatura fatura)
			throws ErroRepositorioException;

	/**
	 * [UC0876] - Gerar Crédito Situação Especial Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 27/01/2009
	 * 
	 * @param anoMesFaturamento
	 * @param idRota
	 * @param idCreditoTipo
	 * @return qtdCreditosARealizar por Rota
	 * @throws ErroRepositorioException
	 */
	public Integer quantidadeCreditosARealizarRota(Integer anoMesFaturamento,
			Rota rota, Integer idCreditoTipo) throws ErroRepositorioException;

	/**
	 * [UC0876] - Gerar Crédito Situação Especial Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 27/01/2009
	 * 
	 * @param helper
	 * @throws ErroRepositorioException
	 */
	public void apagarCreditoARealizarCategoria(
			ApagarDadosFaturamentoHelper helper)
			throws ErroRepositorioException;

	/**
	 * [UC0876] - Gerar Crédito Situação Especial Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 27/01/2009
	 * 
	 * @param helper
	 * @throws ErroRepositorioException
	 */
	public void atualizarCreditoARealizarGeral(
			ApagarDadosFaturamentoHelper helper)
			throws ErroRepositorioException;

	/**
	 * [UC0876] - Gerar Crédito Situação Especial Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 27/01/2009
	 * 
	 * @param helper
	 * @throws ErroRepositorioException
	 */
	public void apagarCreditoARealizar(ApagarDadosFaturamentoHelper helper)
			throws ErroRepositorioException;

	/**
	 * [UC0876] - Gerar Crédito Situação Especial Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 27/01/2009
	 * 
	 * @param helper
	 * @throws ErroRepositorioException
	 */
	public void apagarCreditoARealizarGeral(ApagarDadosFaturamentoHelper helper)
			throws ErroRepositorioException;

	/**
	 * [UC0819] Gerar Historico do Encerramento do Faturamento
	 * 
	 * Verifica se existe ocorrência na tabela RESUMO_FATURAMENTO
	 * 
	 * @author Raphael Rossiter
	 * @date 10/02/2009
	 * 
	 * @param anoMesReferencia
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarResumoFaturamento(Integer anoMesReferencia)
			throws ErroRepositorioException;

	/**
	 * [UC0215] Consultar Posicao de Faturamento
	 * 
	 * @author Vinicius Medeiros
	 * @date 11/03/2009
	 * 
	 * @param faturamentoGrupo
	 * @throws ErroRepositorioException
	 * 
	 */
	public Collection retornaLeiturasNaoRegistradas(
			FaturamentoGrupo faturamentoGrupo) throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês
	 * 
	 * [SB0005] - Obter Valor do Parcelamento Concedido como Bonus
	 * 
	 * Acumula ((DBCG_VLCATEGORIA / DBAC_NNPRESTACAODEBITO) *
	 * DBAC_NNPARCELABONUS) a partir das tabelas DEBITO_A_COBRAR_CATEGORIA e
	 * DEBITO_A_COBRAR com mesmo DBAC_ID e com DBAC_NNPARCELABONUS diferente de
	 * nulo e maior que zero e DBAC_NNPRESTACAOCOBRADAS = DBAC_NNPRESTACAODEBITO
	 * - DBAC_NNPARCELABONUS
	 * 
	 * @author Raphael Rossiter
	 * @date 24/03/2009
	 * 
	 * @param idLocalidade
	 * @param idCategoria
	 * @return BigDecimal
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorDebitoACobrarParcelamentoConcedidoBonus(
			Integer idLocalidade, Integer idCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0155] - Encerrar Faturamento do Mês
	 * 
	 * [SB0005] - Obter Valor do Parcelamento Concedido como Bonus
	 * 
	 * Acumula negativamente ((CACG_VLCATEGORIA / CRAR_NNPRESTACAOCREDITO) *
	 * CRAR_NNPARCELABONUS) a partir das tabelas CREDITO_A_REALIZAR_CATEGORIA e
	 * CREDITO_A_REALIZAR com mesmo CRAR_ID e com CRAR_NNPARCELABONUS diferente
	 * de nulo e maior que zero e CRAR_NNPRESTACAOREALIZADAS =
	 * CRAR_NNPRESTACAOCREDITO - CRAR_NNPARCELABONUS
	 * 
	 * @author Raphael Rossiter
	 * @date 24/03/2009
	 * 
	 * @param idLocalidade
	 * @param idCategoria
	 * @return BigDecimal
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCreditoARealizarParcelamentoConcedidoBonus(
			Integer idLocalidade, Integer idCategoria)
			throws ErroRepositorioException;

	/**
	 * Pesquisa a soma dos valores das multas cobradas para a conta.
	 * 
	 * @author Sávio Luiz
	 * @date 31/03/2009
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorCreditoPorOrigem(int idConta)
			throws ErroRepositorioException;

	public Collection pesquisarContasImoveisDebitoAutomatico(Integer anoMes,
			Collection idsImovel, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim,
			String indicadorContaPaga, String[] bancos)
			throws ErroRepositorioException;

	/**
	 * Pesquisa o valor da água da conta.
	 * 
	 * @author Sávio Luiz
	 * @date 11/05/2009
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorAguaConta(Integer idImovel,
			Integer referencia) throws ErroRepositorioException;

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * Calcula os dados do consumo tarifa de vigência
	 * 
	 * @author Sávio Luiz
	 * @date 02/07/2009
	 * 
	 * @param consumoTarifa
	 * @param dataFaturamento
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosConsumoTarifaVigenciaProporcional(
			Date dataLeituraAnterior, Integer idConsumoTarifa,
			Integer idCategoria, Integer idSubcategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * Calcula os dados do consumo tarifa de vigência
	 * 
	 * @author Sávio Luiz
	 * @date 02/07/2009
	 * 
	 * @param consumoTarifa
	 * @param dataFaturamento
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosConsumoTarifaVigencia(Date dataFaturamento,
			Integer idConsumoTarifa, Integer idCategoria, Integer idSubcategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * Calcula os dados do consumo tarifa de vigência
	 * 
	 * @author Sávio Luiz
	 * @date 02/07/2009
	 * 
	 * @param consumoTarifa
	 * @param dataFaturamento
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosConsumoMaiorTarifaVigenciaPorTarifa(
			Date dataFaturamento, Integer idTarifaVigencia)
			throws ErroRepositorioException;

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * Calcula os dados do consumo tarifa de vigência
	 * 
	 * @author Sávio Luiz
	 * @date 02/07/2009
	 * 
	 * @param consumoTarifa
	 * @param dataFaturamento
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosConsumoTarifaFaixa(
			Collection idsConsumoTarifaCategoria)
			throws ErroRepositorioException;

	/**
	 * [UC0857] - Conta quantidade de registros do relatorio
	 * 
	 * @author Hugo Amorim
	 * @date 10/07/2009
	 * 
	 * @param idUnidadeNegocio
	 *            , idFuncionario, dataPagamentoInicial, dataPagamentoFinal
	 * @throws ErroRepositorioException
	 */
	public int countRelatorioAutoInfracao(Integer idUnidadeNegocio,
			Integer idFuncionario, Integer dataPagamentoInicial,
			Integer dataPagamentoFinal) throws ErroRepositorioException;

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * @author Sávio Luiz
	 * @date 15/07/2009
	 * 
	 * @param imovel
	 * @param anoMesReferencia
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarConsumoTarifaImoveis(
			Collection idsImoveis) throws ErroRepositorioException;

	/**
	 * [UC0184] - Manter débito a Cobrar
	 * 
	 * @author Hugo Amorim
	 * @date 17/07/2009
	 * 
	 */
	public boolean verificarAutosAssociadosAoDebito(String[] idsDebitosACobrar)
			throws ErroRepositorioException;

	/**
	 * [UC0184] - Manter débito a Cobrar
	 * 
	 * @author Hugo Amorim
	 * @date 17/07/2009
	 * 
	 */
	public void cancelarAutosInfracao(String[] idsDebitosACobrar)
			throws ErroRepositorioException;

	/**
	 * [UC0896] - Manter Autos de Infração
	 * 
	 * @author Hugo Amorim
	 * @date 17/07/2009
	 * 
	 */
	public boolean validarExistenciaDebitoAtivosAutoInfracao(
			Integer idAutoInfracao) throws ErroRepositorioException;

	/**
	 * [UC0896] - Manter Autos de Infração
	 * 
	 * @author Hugo Amorim
	 * @date 20/07/2009
	 * 
	 */
	public boolean validarExistenciaDeDebitosAutoInfracao(Integer idAutoInfracao)
			throws ErroRepositorioException;

	/**
	 * [UC0927] – Confirmar Cartão de Crédito/Débito
	 * 
	 * @author Hugo Amorim, Raphael Rossiter
	 * @date 30/07/2009, 14/01/2010
	 * 
	 * @param idArrecadacaoForma
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCartoes(Integer idArrecadacaoForma)
			throws ErroRepositorioException;

	/**
	 * [UCXXXX] - Gerar Conta
	 * 
	 * @author Rafael Corrêa
	 * @date 22/07/2009
	 * 
	 * @param anoMes
	 * @param idFaturamentoGrupo
	 * @param idLocalidadeInicial
	 * @param idLocalidadeFinal
	 * @param codigoSetorComercialInicial
	 * @param codigoSetorComercialFinal
	 * @param codigoRotaInicial
	 * @param codigoRotaFinal
	 * @return Collection<RelatorioContaBean>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosContaRelatorio(Integer anoMes,
			Integer idFaturamentoGrupo, Integer idLocalidadeInicial,
			Integer idLocalidadeFinal, Integer codigoSetorComercialInicial,
			Integer codigoSetorComercialFinal, Short codigoRotaInicial,
			Short codigoRotaFinal, Short sequencialRotaInicial,
			Short sequencialRotaFinal, String indicadorEmissao,
			String indicadorOrdenacao) throws ErroRepositorioException;

	/**
	 * UC0113 - Faturar Grupo Faturamento
	 * 
	 * Author: Raphael Rossiter Data: 04/08/2009
	 * 
	 * UPDATE COBRANCA_PARCELAMENTO_ITEM DELETE COBRANCA_DOCUMENTO_ITEM
	 */
	public void apagarDadosCobranca(ApagarDadosFaturamentoHelper helper)
			throws ErroRepositorioException;

	/**
	 * [UC0819] Gerar Historico do Encerramento do Faturamento
	 * 
	 * @author Vivianne Sousa
	 * @date 04/08/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsSetorParaGerarHistoricoParaEncerrarFaturamento()
			throws ErroRepositorioException;

	/**
	 * Pesquisa os dados do imóvel que será faturado
	 * 
	 * [UC0113] - Faturar Grupo Faturamento
	 * 
	 * @author Raphael Rossiter, Raphael Rossiter
	 * @date 26/08/2009, 16/08/2011
	 * 
	 * @param idRota
	 * @param numeroPaginas
	 * @param quantidadeRegistros
	 * @param preFaturar
	 * @param resumo
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImovelFaturarGrupoPorRotaAlternativa(
			Integer idRota, int numeroPaginas, int quantidadeRegistros,
			boolean preFaturar, boolean resumo) throws ErroRepositorioException;

	/**
	 * 
	 * Método que retorna todos os imóveis que tenham cliente responsável e
	 * indicacao de conta a ser entregue em outro endereço e que estejam nas
	 * quadras pertencentes às rotas passadas
	 * 
	 * UC0209 Gerar Taxa de Entrega de Conta em Outro Endereço.
	 * 
	 * @author Raphael Rossiter
	 * @date 26/08/2009
	 * 
	 * @param rotas
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection obterImoveisPorRotasComContaEntregaEmOutroEnderecoPorRotaAlternativa(
			Integer idRota) throws ErroRepositorioException;

	/**
	 * Metodo que retorna os imóveis das quadras pertencentes às rotas
	 * 
	 * Utilizado pelo [UC0302] Gerar Débitos a Cobrar de Acréscimos por
	 * Impontualidade
	 * 
	 * @author Raphael Rossiter
	 * @date 26/08/2009
	 * 
	 * @param idRota
	 * @return Collection
	 */
	public Collection pesquisarImoveisDasQuadrasPorRotaAlternativa(
			Integer idRota) throws ErroRepositorioException;

	/**
	 * Permite pesquisar imóvel doação baseando-se em rotas [UC0394] Gerar
	 * Débitos a Cobrar de Doações
	 * 
	 * @author Raphael Rossiter
	 * @date 26/08/2008
	 * 
	 * @param idRota
	 * @return Collection<ImovelCobrarDoacaoHelper> - Coleção de
	 *         ImovelCobrarDoacaoHelper já com as informações necessárias para
	 *         registro da cobrança
	 * @throws ErroRepositorioException
	 */
	public Collection<ImovelCobrarDoacaoHelper> pesquisarImovelDoacaoPorRota(
			Integer idRota) throws ErroRepositorioException;

	/**
	 * Permite pesquisar imóvel doação baseando-se em rotas [UC0394] Gerar
	 * Débitos a Cobrar de Doações
	 * 
	 * @author Raphael Rossiter
	 * @date 26/08/2008
	 * 
	 * @param idRota
	 * @return Collection<ImovelCobrarDoacaoHelper> - Coleção de
	 *         ImovelCobrarDoacaoHelper já com as informações necessárias para
	 *         registro da cobrança
	 * @throws ErroRepositorioException
	 */
	public Collection<ImovelCobrarDoacaoHelper> pesquisarImovelDoacaoPorRotaAlternativa(
			Integer idRota) throws ErroRepositorioException;

	/**
	 * [UC0928 ] - Manter Situação Especial de Faturamento [SB ] - [FS ] -
	 * 
	 * comment
	 * 
	 * @since 19/08/2009
	 * @author Marlon Patrick
	 */
	public Integer pesquisarSituacaoEspecialFaturamentoCount(
			FaturamentoSituacaoComando comando) throws ErroRepositorioException;

	/**
	 * [UC0928 ] - Manter Situação Especial de Faturamento [SB ] - [FS ] -
	 * 
	 * comment
	 * 
	 * @since 19/08/2009
	 * @author Marlon Patrick
	 */
	public Collection<FaturamentoSituacaoComando> pesquisarSituacaoEspecialFaturamento(
			FaturamentoSituacaoComando comando, Integer numeroPaginasPesquisa)
			throws ErroRepositorioException;

	/**
	 * [UC0320] Gerar Fatura de Cliente Responsável
	 * 
	 * Verifica se todos os grupos já foram faturados
	 * 
	 * @author Vivianne Sousa
	 * @date 17/08/2009
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGrupoFaturamentoNaoFaturados(
			Integer anoMesReferenciaFaturamento)
			throws ErroRepositorioException;

	/**
	 * 
	 * @author Hugo Amorim
	 * @date 26/08/2009
	 * @param idImovel
	 * @param anoMesReferencia
	 * @return Collection
	 */
	public Collection pesquisarVencimentoConta(Integer idImovel,
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * Método que retorna uma array de object do debito cobrado ordenado pelo
	 * tipo de debito
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * @author Rafael Corrêa
	 * @date 06/09/2009
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List pesquisarDebitoCobradoConta(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * 
	 * @author Hugo Amorim
	 * @date 26/08/2009
	 * @param idConta
	 * @return dataPagamento
	 */
	public Collection pesquisarDataPagamento(Integer idContal)
			throws ErroRepositorioException;

	/**
	 * Pesquisa para cada imóvel da rota informada a principal categoria.
	 * 
	 * [UC0302] - Gerar Débitos a Cobrar de Acréscimos por Impontualidade
	 * 
	 * @author Raphael Rossiter
	 * @date 01/09/2009
	 * 
	 * @param codigoEmpresaFebraban
	 * @param idRota
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarPrincipalCategoriaImovelPorRotaAlternativa(
			Short codigoEmpresaFebraban, Integer idRota)
			throws ErroRepositorioException;

	/**
	 * pesquisa o indicado de acréscimo para o cliente do imóvel.
	 * 
	 * [UC0302]-Gerar Débito a cobrar de Acréscimos por Impontualidade
	 * 
	 * @author Raphael Rossiter
	 * @date 01/09/2009
	 * 
	 * @param idRota
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> obterIndicadorGeracaoAcrescimosClienteImovelPorRotaAlternativa(
			Integer idRota) throws ErroRepositorioException;

	/**
	 * pesquisarDataPrevistaFaturamentoAtividadeCronograma
	 * 
	 * @author Hugo Amorim
	 * @date 11/09/2009
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Date pesquisarDataPrevistaFaturamentoAtividadeCronograma(
			Integer idFaturamentoGrupo, Integer idFaturamentoAtividade,
			Integer amReferencia) throws ErroRepositorioException;

	/**
	 * [UC0764] Gerar Relatorio Contas Canceladas ou Retificadas
	 * 
	 * @author Rafael Pinto
	 * @date 11/09/2009
	 * @param RelatorioContasCanceladasRetificadasHelper
	 * @return quantidade de registros
	 */
	public Integer pesquisarQuantidadeContasCanceladasFaturamentoFechado(
			RelatorioContasCanceladasRetificadasHelper helper);

	/**
	 * [UC0764] Gerar Relatorio Contas Canceladas ou Retificadas
	 * 
	 * @author Rafael Pinto
	 * @date 11/09/2009
	 * @param RelatorioContasCanceladasRetificadasHelper
	 * @return quantidade de registros
	 */
	public Integer pesquisarQuantidadeContasCanceladasFaturamentoAberto(
			RelatorioContasCanceladasRetificadasHelper helper);

	/**
	 * [UC0764] Gerar Relatorio Contas Canceladas ou Retificadas
	 * 
	 * @author Rafael Pinto
	 * @date 11/09/2009
	 * @param RelatorioContasCanceladasRetificadasHelper
	 * @return quantidade de registros
	 */

	public Integer pesquisarQuantidadeContasRetificadasFaturamentoFechado(
			RelatorioContasCanceladasRetificadasHelper helper);

	/**
	 * [UC0764] Gerar Relatorio Contas Canceladas ou Retificadas
	 * 
	 * @author Rafael Pinto
	 * @date 11/09/2009
	 * @param RelatorioContasCanceladasRetificadasHelper
	 * @return quantidade de registros
	 */
	public Integer pesquisarQuantidadeContasRetificadasFaturamentoAberto(
			RelatorioContasCanceladasRetificadasHelper helper);

	/**
	 * [UC0147] - Cancelar Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 21/09/2009
	 */
	public void atualizarIndicadorHistoricoContaGeral(Integer idContaGeral,
			Short indicadorHistorico) throws ErroRepositorioException;

	/**
	 * Método que retorna o tipo de cálculo da conta
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * @author Rafael Corrêa
	 * @date 21/09/2009
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTipoCalculoConta(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Método que retorna o tipo de cálculo da conta no histórico
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * @author Rafael Corrêa
	 * @date 21/09/2009
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTipoCalculoContaHistorico(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Pesquisa a quantidade de dados do relatório [UC0635] - Gerar Relatórios
	 * de Contas em Revisão
	 * 
	 * @author Arthur Carvalho
	 * @created 14/09/2009
	 * @exception ErroRepositorioException
	 */
	public Integer pesquisarDadosRelatorioContasRevisaoCount(
			Integer idGerenciaRegional, Integer idUnidadeNegocio,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer codigoSetorComercialInicial,
			Integer codigoSetorComercialFinal,
			Collection colecaoIdsMotivoRevisao, Integer idImovelPerfil,
			Integer referenciaInicial, Integer referenciaFinal,
			Integer idCategoria, Integer idEsferaPoder)
			throws ErroRepositorioException;

	/**
	 * Pesquisa a quantidade de dados do relatório [UC0635] - Gerar Relatório
	 * Acompanhamento Faturamento
	 * 
	 * @author Arthur Carvalho
	 * @created 23/09/2009
	 * @exception ErroRepositorioException
	 */
	public Integer gerarRelacaoAcompanhamentoFaturamentoCount(
			String idImovelCondominio, String idImovelPrincipal,
			String idNomeConta, String idSituacaoLigacaoAgua,
			String consumoMinimoInicialAgua, String consumoMinimoFinalAgua,
			String idSituacaoLigacaoEsgoto, String consumoMinimoInicialEsgoto,
			String consumoMinimoFinalEsgoto,
			String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,
			String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil,
			String idPocoTipo, String idFaturamentoSituacaoTipo,
			String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,
			String idEloAnormalidade, String areaConstruidaInicial,
			String areaConstruidaFinal, String idCadastroOcorrencia,
			String idConsumoTarifa, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String setorComercialInicial, String setorComercialFinal,
			String quadraInicial, String quadraFinal, String loteOrigem,
			String loteDestno, String cep, String logradouro, String bairro,
			String municipio, String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo,
			String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial,
			String numeroMoradoresFinal, String idAreaConstruidaFaixa)
			throws ErroRepositorioException;

	/**
	 * Método que retorna as contas para impressao
	 * 
	 * Pesquisar Contas Emitir COSANPA
	 * 
	 * @author Tiago Moreno
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasEmitirCOSANPA(Integer idTipoConta,
			Integer idEmpresa, Integer numeroPaginas, Integer anoMesReferencia,
			Integer idFaturamentoGrupo) throws ErroRepositorioException;

	/**
	 * [UC0958] - Gerar Relatório de juros, Multas e Débitos Cancelados
	 * 
	 * @since 13/10/2009
	 * @author Marlon Patrick
	 */
	public Collection<Object[]> pesquisarRelatorioJurosMultasDebitosCancelados(
			FiltrarRelatorioJurosMultasDebitosCanceladosHelper filtro)
			throws ErroRepositorioException;

	/**
	 * [UC0113] - Faturar Grupo Faturamento Author:Sávio Luiz Data:27/10/2009
	 * Consultar os Cre´ditos a Realizar do Imovel
	 * 
	 * @param imovelId
	 *            Id do Imovel
	 * @param debitoCreditoSituacaoAtualId
	 *            Id do Debito Credito Situação
	 * @return Coleção de Creditos a Realizar
	 * @exception ErroRepositorioException
	 */
	public Collection pesquisarCreditoARealizarPeloCreditoRealizado(
			Integer IdCreditoARealizar, Integer anoMesFaturamento)
			throws ErroRepositorioException;

	/**
	 * Metodo que deleta os creditos realizados categoria de um respectivo
	 * débito a cobrar
	 * 
	 * Utilizado pelo [UC0745] .
	 * 
	 * @author Sávio Luiz
	 * @date 24/08/2006
	 * 
	 * @param idDebitoACobrar
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarCreditoRealizadoCategoria(Integer idCreditoRealizado)
			throws ErroRepositorioException;

	/**
	 * Método que retorna as contas para impressao
	 * 
	 * Pesquisar Contas Emitir CAEMA
	 * 
	 * @author Rafael Pinto
	 * @date 03/11/2009
	 * 
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasEmitirCAEMA(Integer numeroPaginas,
			Integer anoMesReferencia, Integer idFaturamentoGrupo)
			throws ErroRepositorioException;

	/**
	 * Recupera as contas com estouro de consumo ou com baixo consumo [UC0348] -
	 * Emitir Contas
	 * 
	 * @author Sávio Luiz
	 * @date 15/05/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasEmitirCAER(
			Collection<Integer> idTipoConta, Integer idEmpresa,
			Integer numeroPaginas, Integer anoMesReferencia,
			Integer idFaturamentoGrupo) throws ErroRepositorioException;

	/**
	 * Recupera as contas com entrega para o cliente responsável [UC0348] -
	 * Emitir Contas
	 * 
	 * @author Sávio Luiz
	 * @date 15/05/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasClienteResponsavelCAER(
			Collection<Integer> idTipoConta, Integer numeroPaginas,
			Integer anoMesReferencia, Integer idFaturamentoGrupo,
			Short indicadorEmissaoExtratoFaturamento)
			throws ErroRepositorioException;

	/**
	 * Pesquisa as contas do cliente responsável para todos os grupos de
	 * faturamento.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * @author Pedro Alexandre
	 * @date 17/05/2007
	 * 
	 * @param idTipoConta
	 * @param numeroPaginas
	 * @param anoMesReferencia
	 * @param indicadorEmissaoExtratoFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasClienteResponsavelCAER(
			Collection<Integer> idTipoConta, Integer numeroPaginas,
			Integer anoMesReferencia, Short indicadorEmissaoExtratoFaturamento)
			throws ErroRepositorioException;

	/**
	 * 
	 * [UC0972] Gerar TXT das Contas dos Projetos Especiais
	 * 
	 * @author Hugo Amorim, Anderson Italo
	 * @since 14/12/2009, 29/01/2010
	 * 
	 */
	public Collection pesquisarDadosTxtContasProjetosEspeciais(String anoMes,
			Integer idCliente, Integer quantidadeRegistros, Integer numeroIndice)
			throws ErroRepositorioException;

	/**
	 * 
	 * [UC0972] count Gerar TXT das Contas dos Projetos Especiais
	 * 
	 * @author Hugo Amorim, Anderson Italo
	 * @since 15/12/2009, 29/01/2010
	 * 
	 */
	public Integer countTxtContasProjetosEspeciais(String anoMes,
			Integer idCliente) throws ErroRepositorioException;

	/**
	 * [UC0971] Inserir Pagamentos para Faturas Especiais [SB0002] Processar
	 * fatura do cliente responsavel
	 * 
	 * @author Vivianne Sousa
	 * @created 22/12/2009
	 */
	public Collection pesquisarFatura(Integer idCliente,
			Integer anoMesReferencia, Integer numeroSequencial,
			BigDecimal valordebito) throws ErroRepositorioException;

	/**
	 * [UC0971] Inserir Pagamentos para Faturas Especiais
	 * 
	 * @author Rafael Pinto
	 * @created 08/01/2010
	 * 
	 * @param idCliente
	 *            Id do Cliente
	 * @param anoMesReferencia
	 *            Ano Mes de Referencia
	 * @param numeroSequencial
	 *            Numero Sequencial
	 * @param valordebito
	 *            Valor do Debito
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection pesquisarFaturaItem(Integer idCliente,
			Integer anoMesReferencia, Integer numeroSequencial,
			BigDecimal valordebito, int numeroPaginas, int quantidadeRegistros)
			throws ErroRepositorioException;

	/**
	 * Recupera as contas dos imóveis selecionados que tenham o mês ano de
	 * referência e que estejam com a situação atual igual a normal ou situação
	 * anterior igual a normal
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 20/01/2010
	 * 
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarContasResumoSimulacaoFaturamento(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException;

	/**
	 * Recupera as contas que foram impressas do grupo de faturamento e que
	 * sejam para envio de email. [UC0394] - Gerar Débitos a Cobrar de Doações
	 * 
	 * @author Raphael Rossiter
	 * @date 23/02/2010
	 * 
	 * @param idImovel
	 * @param anoMesReferenciaContabil
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitoACobrarDeDoacao(Integer idImovel,
			Integer anoMesReferenciaContabil, Integer idDebitoTipo)
			throws ErroRepositorioException;

	/**
	 * [UC0840] - Atualizar Faturamento do Movimento Celular
	 * 
	 * @author Rafael Pinto
	 * @date 26/02/2010
	 * 
	 * @param idImovel
	 * @param anoMesReferencia
	 * @return Conta
	 * @throws ErroRepositorioException
	 */
	public Conta pesquisarContaPreFaturada(Integer idImovel,
			Integer anoMesReferencia, Integer idDebitoCreditoSituacaoAtual)
			throws ErroRepositorioException;

	/**
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 02/03/2010
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasImpressasParaEnvioEmail(
			Integer idLocalidade, SistemaParametro sistemaParametro)
			throws ErroRepositorioException;

	/**
	 * 
	 * [UC0993] Consultar Faturamento Imediato Ajuste
	 * 
	 * @author Hugo Leonardo
	 * @param form
	 * @throws ControladorException
	 * @data 26/02/2010
	 * 
	 */
	public Collection pesquisarFaturamentoImediatoAjuste(
			String anoMesReferencia, String faturamentoGrupo, String imovelId,
			String rotaId, int qtd) throws ErroRepositorioException;

	/**
	 * [UC0993] Consultar Faturamento Imediato Ajuste
	 * 
	 * @author Hugo Leonardo
	 * @date 01/03/2010
	 * 
	 * 
	 * @return Quantidade
	 * @throws FachadaException
	 */
	public Integer contarFaturamentoImediatoAjuste(String anoMesReferencia,
			String faturamentoGrupo, String imovelId, String rotaId)
			throws ErroRepositorioException;

	/**
	 * [UC1001] Emitir declaração de quitação anual de débitos
	 * 
	 * Pesquisa das contas.
	 * 
	 * @author Hugo Amorim
	 * @param quantidadeMaxima
	 * @param quantidadeInicio
	 * @param quantidadeMaxima
	 * @param quantidadeMaxima2
	 * @date 17/03/2010
	 */
	public Collection<Integer> pesquisarImoveisParaGeracaoDaDeclaracaodeQuitacaoDebitos(
			Integer idRota, int quantidadeInicio, int quantidadeMaxima)
			throws ErroRepositorioException;

	/**
	 * [UC1001] Emitir declaração de quitação anual de débitos
	 * 
	 * Pesquisa das contas.
	 * 
	 * @author Hugo Amorim
	 * @date 17/03/2010
	 */
	public Collection pesquisarContasPagasGeracaoDeclaracaoQuitacao(Integer id,
			String ano, Date dataVerificacaoPagamentos)
			throws ErroRepositorioException;

	/**
	 * [UC1001] Emitir declaração de quitação anual de débitos
	 * 
	 * Pesquisa das contas.
	 * 
	 * @author Hugo Amorim
	 * @date 17/03/2010
	 */
	public Collection pesquisarContasParceladasGeracaoDeclaracaoQuitacao(
			Integer id, String ano, Date dataVerificacaoPagamentos)
			throws ErroRepositorioException;

	/**
	 * [UC1001] Emitir declaração de quitação anual de débitos
	 * 
	 * Pesquisa das contas.
	 * 
	 * @author Hugo Amorim
	 * @date 17/03/2010
	 */
	public Collection pesquisarContasCanceladasGeracaoDeclaracaoQuitacao(
			Integer id, String ano, Date dataVerificacaoPagamentos)
			throws ErroRepositorioException;

	/**
	 * [UC1001] Emitir declaração de quitação anual de débitos
	 * 
	 * Pesquisa das contas.
	 * 
	 * @author Hugo Amorim
	 * @date 17/03/2010
	 */
	public Collection pesquisarContasEmCobrancaJudicialGeracaoDeclaracaoQuitacao(
			Integer id, String ano, Date dataVerificacaoPagamentos)
			throws ErroRepositorioException;

	/**
	 * [UC1008] Gerar TXT declaração de quitação anual de débitos
	 * 
	 * Este caso de uso permite a geração do TXT da declaração de quitação de
	 * débitos.
	 * 
	 * @author Hugo Amorim
	 * @date 23/03/2010
	 */
	public Collection<Integer> pesquisarAnosParaGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos()
			throws ErroRepositorioException;

	/**
	 * [UC1008] Gerar TXT declaração de quitação anual de débitos
	 * 
	 * Este caso de uso permite a geração do TXT da declaração de quitação de
	 * débitos.
	 * 
	 * @author Hugo Amorim
	 * @date 23/03/2010
	 */
	public Collection pesquisarExtratoQuitacaoParaGeracaoArquivoTexto(
			Integer ano, int empresaId, int quantidadeMaxima,
			Integer idGrupoFaturamento) throws ErroRepositorioException;

	/**
	 * [UC1008] Gerar TXT declaração de quitação anual de débitos
	 * 
	 * Este caso de uso permite a geração do TXT da declaração de quitação de
	 * débitos.
	 * 
	 * @author Hugo Amorim
	 * @date 23/03/2010
	 */
	public Collection<ExtratoQuitacaoItem> pesquisarExtratoQuitacaoItensParaGeracaoArquivoTexto(
			Integer id) throws ErroRepositorioException;

	/**
	 * [UC0394] - Gerar Débitos a Cobrar de Doações
	 * 
	 * @author Hugo Amorim
	 * @date 07/04/2010
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitoACobrarDeDoacaoAtivos(Integer idImovel,
			Integer anoMesReferenciaContabil, Integer idDebitoTipo)
			throws ErroRepositorioException;

	/**
	 * [SB0002] – Replicar os débitos existentes para uma nova vigência e valor.
	 * Pesquisa a última vigência de cada débito tipo, e retorna uma coleção com
	 * limite de 10 registros.
	 * 
	 * @author Josenildo Neves
	 * @date 22/02/2010
	 */
	public Collection<DebitoTipoVigencia> pesquisarDebitoTipoVigenciaUltimaVigencia(
			Integer numeroPagina) throws ErroRepositorioException;

	/**
	 * [SB0002] – Replicar os débitos existentes para uma nova vigência e valor.
	 * Pesquisa a última vigência de cada tipo débito, e retorna o total.
	 * 
	 * @author Josenildo Neves
	 * @date 22/02/2010
	 */
	public Integer pesquisarDebitoTipoVigenciaUltimaVigenciaTotal()
			throws ErroRepositorioException;

	/**
	 * [SB0002] – Replicar os débitos existentes para uma nova vigência e valor.
	 * Pesquisa a última vigência de cada tipo débito, e retorna uma coleção.
	 * 
	 * @author Josenildo Neves
	 * @date 22/02/2010
	 */
	public Collection<DebitoTipoVigencia> pesquisarDebitoTipoVigenciaUltimaVigenciaSelecionados(
			String[] selecionados) throws ErroRepositorioException;

	/**
	 * [UC1008] Gerar TXT declaração de quitação anual de débitos
	 * 
	 * Este caso de uso permite a geração do TXT da declaração de quitação de
	 * débitos.
	 * 
	 * @author Hugo Amorim
	 * @date 23/03/2010
	 */
	public Collection<Empresa> pesquisarEmpresasParaGeraracaoExtrato(
			Integer idGrupoFaturamento) throws ErroRepositorioException;

	/**
	 * [UC0820] Atualizar Faturamento do Movimento Celular
	 * 
	 * Método criado para atualizar apenas os campos necessários para Conta.
	 * 
	 * @author Bruno Barros
	 * @date 31/03/2010
	 * @param medicaoHistorico
	 * @throws ErroRepositorioException
	 */
	public void atualizarContaProcessoMOBILE(Conta conta)
			throws ErroRepositorioException;

	/**
	 * [UC0820] Atualizar Faturamento do Movimento Celular
	 * 
	 * Método criado para atualizar apenas os campos necessários para Conta
	 * Categoria.
	 * 
	 * @author Bruno Barros
	 * @date 31/03/2010
	 * @param medicaoHistorico
	 * @throws ErroRepositorioException
	 */
	public void atualizarContaCategoriaProcessoMOBILE(
			ContaCategoria contaCategoria) throws ErroRepositorioException;

	/**
	 * [UC0820] Atualizar Faturamento do Movimento Celular
	 * 
	 * Método criado para atualizar apenas os campos necessários para Conta
	 * Impostos Deduzidos.
	 * 
	 * @author Bruno Barros
	 * @date 31/03/2010
	 * @param medicaoHistorico
	 * @throws ErroRepositorioException
	 */
	public void atualizarContaImpostosDeduzidosProcessoMOBILE(
			ContaImpostosDeduzidos contaImpostosDeduzidos)
			throws ErroRepositorioException;

	/**
	 * [UC0820] Atualizar Faturamento do Movimento Celular
	 * 
	 * Método criado para atualizar apenas os campos necessários para Movimento
	 * Conta Prefaturada.
	 * 
	 * @author Bruno Barros
	 * @date 31/03/2010
	 * @param medicaoHistorico
	 * @throws ErroRepositorioException
	 */
	public void atualizarMovimentoContaPrefaturadaProcessoMOBILE(
			MovimentoContaPrefaturada movimentoContaPrefaturada)
			throws ErroRepositorioException;

	/**
	 * [UC0820] Atualizar Faturamento do Movimento Celular
	 * 
	 * Método criado para atualizar apenas os campos necessários para Conta.
	 * 
	 * @author Bruno Barros
	 * @date 22/04/2010
	 * @param Conta
	 * @throws ErroRepositorioException
	 */
	public void zerarValoresContaPassarDebitoCreditoSituacaoAtualPreFaturadaMOBILE(
			Conta conta) throws ErroRepositorioException;

	/**
	 * [UC0982] Inserir tipo de Débito com Vigência.
	 * 
	 * Verificar se existe vigência já cadastrada para o tipo de débito.
	 * 
	 * @author Hugo Leonardo
	 * @param dataVigenciaInicial
	 * @param dataVigenciaFinal
	 * @throws ErroRepositorioException
	 * @data 30/04/2010
	 * 
	 */
	public String verificarExistenciaVigenciaDebito(String dataVigenciaInicial,
			String dataVigenciaFinal, Integer idDebitoTipo)
			throws ErroRepositorioException;

	/**
	 * 4.1.1. Através do DBTP_ID relacionar com o DBTP_ID da tabela
	 * DEBITO_TIPO_VIGENCIA. Selecionar a última vigência (maior data
	 * DBTV_DTVIGENCIAFINAL) para o tipo de débito o valor DBTV_VLDEBITO
	 * correspondente. 4.1.2. Caso não seja encontrado para uma vigência e tipo
	 * de débito na tabela DEBITO_TIPO_VIGENCIA, deverá selecionar o valor
	 * sugerido (DBTP_VLSUGERIDO<>0) correspondente a constante 22 na tabela
	 * DEBITO_TIPO e utilizar este valor.
	 * 
	 * @author: Hugo Amorim
	 * @Analista: Nelson Carvalho
	 * @data: 17/05/210
	 * 
	 * @throws ErroRepositorioException
	 */
	public DebitoTipoVigencia pesquisarDebitoTipoVigenciaPorDebitoTipo(
			Integer idDebitoTipo) throws ErroRepositorioException;

	// /**
	// * [UC1008] Gerar TXT declaração de quitação anual de débitos
	// *
	// * Este caso de uso permite a geração do TXT da declaração de quitação de
	// débitos.
	// *
	// * @author Hugo Amorim
	// * @date 23/03/2010
	// */
	// public Collection pesquisarExtratoQuitacaoParaGeracaoArquivoTextoCAERN(
	// Integer ano, int empresaId, int quantidadeMaxima, Integer idGrupo)
	// throws ErroRepositorioException;
	/**
	 * 
	 * Metodo para calculo da receita liquida agua,esgoto e total.
	 * 
	 * @author Hugo Amorim
	 * @analista Ana Cristina
	 * @since 04/06/2010
	 * @param colecaoCategorias
	 * @param colecaoIdsLocalidades
	 */
	public Object[] pesquisarDadosReceitaLiquidaAguaEsgoto(
			Integer anoMesFaturamentoSistemaParametro, Integer idLocalidade,
			Integer id) throws ErroRepositorioException;

	/**
	 * 
	 * Metodo para calculo da receita liquida indireta.
	 * 
	 * @author Hugo Amorim
	 * @analista Ana Cristina
	 * @since 04/06/2010
	 * @param colecaoCategorias
	 * @param colecaoIdsLocalidades
	 */
	public BigDecimal pesquisarDadosReceitaLiquidaIndireta(
			Integer anoMesFaturamentoSistemaParametro, Integer idLocalidade,
			Integer id) throws ErroRepositorioException;

	/**
	 * [UC0876] - Gerar Crédito Situação Especial Faturamento
	 * 
	 * @author Sávio Luiz
	 * @date 10/06/2010
	 * 
	 * @param colecaoFaturamentoAtividadeCronogramaRota
	 * @param faturamentoGrupo
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public Object[] pesquisarCreditoARealizar(Integer imovelId,
			Integer idCreditoTipo, Integer debitoCreditoSituacaoAtualId,
			Integer anoMesFaturamento) throws ErroRepositorioException;

	/**
	 * Atualizar Credito a Realizar Campo numero de prestações realizadas e
	 * valor do crédito
	 * 
	 * [UC0840] - Atualizar Faturamento do Movimento Celular
	 * 
	 * @author Sávio Luiz
	 * @date 11/06/2010
	 * 
	 * @param idDebitoAcobrar
	 * @param prestacaoCobrada
	 * @throws ErroRepositorioException
	 */
	public void atualizarValorCreditoARealizar(Integer idCreditoARealizar,
			BigDecimal valorCredito, Integer idDebitoCreditoSituacaoAtual)
			throws ErroRepositorioException;

	/**
	 * Atualizar Credito a Realizar Categoria Campo valor do crédito por
	 * categoria
	 * 
	 * [UC0840] - Atualizar Faturamento do Movimento Celular
	 * 
	 * @author Sávio Luiz
	 * @date 11/06/2010
	 * 
	 * @param idDebitoAcobrar
	 * @param prestacaoCobrada
	 * @throws ErroRepositorioException
	 */
	public void atualizarValorCreditoARealizarCategoria(
			Integer idCreditoARealizar, Collection colecaoCategoria,
			Collection colecaoValorPorCategoria)
			throws ErroRepositorioException;

	/**
	 * Atualizar Credito a Realizar Campo numero de prestações realizadas e
	 * valor do crédito
	 * 
	 * [UC0840] - Atualizar Faturamento do Movimento Celular
	 * 
	 * @author Sávio Luiz
	 * @date 11/06/2010
	 * 
	 * @param idDebitoAcobrar
	 * @param prestacaoCobrada
	 * @throws ErroRepositorioException
	 */
	public void atualizarValorCreditoRealizado(Integer idCreditoRealizado,
			BigDecimal valorCredito) throws ErroRepositorioException;

	/**
	 * Atualizar Credito a Realizar Categoria Campo valor do crédito por
	 * categoria
	 * 
	 * [UC0840] - Atualizar Faturamento do Movimento Celular
	 * 
	 * @author Sávio Luiz
	 * @date 11/06/2010
	 * 
	 * @param idDebitoAcobrar
	 * @param prestacaoCobrada
	 * @throws ErroRepositorioException
	 */
	public void atualizarValorCreditoRealizadoCategoria(
			Integer idCreditoRealizado, Collection colecaoCategoriasObterValor,
			Collection colecaoCategoriasCalculadasValor)
			throws ErroRepositorioException;

	/**
	 * [UC0391] Inserir valor de cobrança de serviço.
	 * 
	 * Verificar se existe valor de cobrança de serviço já cadastrada.
	 * 
	 * @author Hugo Amorim
	 * @throws ControladorException
	 * @data 07/06/2010
	 * 
	 */
	public Boolean validarVigenciaValorCobrancaServico(
			ServicoCobrancaValor servicoCobrancaValor)
			throws ErroRepositorioException;

	/**
	 * Pesquisa Credito Realizado de nitrato
	 * 
	 * [UC0840] - Atualizar Faturamento do Movimento Celular
	 * 
	 * @author Sávio Luiz
	 * @date 11/06/2010
	 * 
	 * @param idDebitoAcobrar
	 * @param prestacaoCobrada
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdCreditoRealizadoNitrato(Conta conta)
			throws ErroRepositorioException;

	/**
	 * Método que retorna uma array de object com a soma do valor dos debitos
	 * cobrados de parcelamento(parcelamento de água,parcelamento de esgoto,
	 * parcelamento de serviços e juros de parcelamento),o numero da prestacao e
	 * o numero total de prestações
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0013] Gerar Linhas dos Débitos Cobrados
	 * 
	 * @author Vivianne Sousa
	 * @date 11/06/2010
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarParmsDebitoAutomaticoParcelasMaisJurosParcelamento(
			Integer idConta) throws ErroRepositorioException;

	/**
	 * Método que retorna uma array de object com a soma do valor dos debitos
	 * cobrados de parcelamento(parcelamento de água,parcelamento de esgoto,
	 * parcelamento de serviços e juros de parcelamento),o numero da prestacao e
	 * o numero total de prestações
	 * 
	 * [UC0482]Emitir 2ª Via de Conta
	 * 
	 * [SB0013] Gerar Linhas dos Débitos Cobrados
	 * 
	 * @author Vivianne Sousa
	 * @date 11/06/2010
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarParmsDebitoAutomaticoHistoricoParcelasMaisJurosParcelamento(
			Integer idConta) throws ErroRepositorioException;

	/**
	 * Método que retorna uma array de object do debito cobrado ordenado pelo
	 * tipo de debito
	 * 
	 * [UC0348] Emitir Contas [SB0013] Gerar Linhas dos Débitos Cobrados
	 * 
	 * @author Vivianne Sousa
	 * @date 16/01/2007
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List pesquisarParmsDebitoCobradoPorTipoSemParcelasEJurosParcelamento(
			Integer idConta) throws ErroRepositorioException;

	/**
	 * Método que retorna uma array de object do debito cobrado ordenado pelo
	 * tipo de debito
	 * 
	 * [UC0482]Emitir 2ª Via de Conta [SB0013] Gerar Linhas dos Débitos Cobrados
	 * 
	 * @author Vivianne Sousa
	 * @date 11/06/2010
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List pesquisarParmsDebitoCobradoHistoricoPorTipoSemParcelasEJurosParcelamento(
			Integer idConta) throws ErroRepositorioException;

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0002] - Obter dados dos serviços de parcelamento
	 * 
	 * @author Raphael Rossiter,Vivianne Sousa
	 * @date 25/04/2008,14/06/2010
	 * 
	 * @param conta
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDebitoCobradoDeParcelamentoMaisJurosParcelamento(
			Conta conta) throws ErroRepositorioException;

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0002] - Obter dados dos serviços de parcelamento
	 * 
	 * @author Raphael Rossiter,Vivianne Sousa
	 * @date 25/04/2008,14/06/2010
	 * 
	 * @param conta
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitoCobradoNaoParcelamentoEJurosParcelamento(
			Conta conta) throws ErroRepositorioException;

	/**
	 * [UC0745] - Deleta Arquivo Texto Dividido para Faturamento
	 * 
	 * @author Hugo Leonardo
	 * @date 16/06/2010
	 * 
	 * @param idArquivoTextoRoteiroEmpresa
	 * 
	 * @throws ControladorException
	 */
	public void deletaArquivoTextoRoteiroEmpresaDivisao(
			Integer idArquivoTextoRoteiroEmpresa)
			throws ErroRepositorioException;

	/**
	 * [UC] Gerar Relatório de Contas Emitidas
	 * 
	 * @author Hugo Amorim
	 * @created 16/06/2010
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarContasEmitidasImpressaoSimultaneaRelatorio(
			int anoMesReferencia, Integer grupoFaturamento,
			Collection esferaPoder) throws ErroRepositorioException;

	/**
	 * Monta a colecao de resultdos apartir da tabela
	 * mov_conta_prefaturada para geracao do relatorio de RESUMO CONTAS
	 * EMITIDAS POR LOCALIDADE NO GRUPO
	 * 
	 * @author Hugo Amorim
	 * @date 18/06/2010
	 * 
	 * @param idGrupoFaturamento
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Collection filtrarResumoContasLocalidadeImpressaoSimultanea(
			Integer idGrupoFaturamento, String anoMes, Integer idFirma)
			throws ErroRepositorioException;

	/**
	 * [UC1001]
	 * 
	 * [SB0047] Exibir mensagem quitação de débitos
	 * 
	 * @author Hugo Amorim
	 * @created 21/06/2010
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMesagemExtrato(Integer anoMesReferencia,
			Integer idImovel) throws ErroRepositorioException;

	/**
	 * @author Rômulo Aurélio
	 * @throws ControladorException
	 * @data 22/06/2010
	 */

	public Integer retornaAnoMesFaturamentoGrupoDaRota(Integer idRota)
			throws ErroRepositorioException;

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 17/04/2008
	 * 
	 * @param idRota
	 * @param anoMesReferencia
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public void deletarArquivoTextoRoteiroEmpresa(
			Integer idArquivoTextoRoteiroEmpresa)
			throws ErroRepositorioException;

	/**
	 * [UC0014] Manter Imóvel
	 * 
	 * [FS0037] Verificar Imóvel em Processo de Faturamento
	 * 
	 * @author Hugo Amorim
	 * @created 02/07/2010
	 * 
	 * @param idImovel
	 * @param anoMesFaturamento
	 * @throws ErroRepositorioException
	 */
	public boolean verificarImovelEmProcessoDeFaturamento(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC1041] Gerar Taxa Percentual da Tarifa Mínima para Cortado
	 * 
	 * @author Raphael Rossiter
	 * @date 09/07/2010
	 * 
	 * @param idRota
	 * @param numeroPaginas
	 * @param quantidadeRegistros
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImovelCortadoSemTarifaSocialPorRota(
			Integer idRota, int numeroPaginas, int quantidadeRegistros)
			throws ErroRepositorioException;

	/**
	 * [UC1041] Gerar Taxa Percentual da Tarifa Mínima para Cortado
	 * 
	 * @author Raphael Rossiter
	 * @date 09/07/2010
	 * 
	 * @param idRota
	 * @param numeroPaginas
	 * @param quantidadeRegistros
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImovelCortadoSemTarifaSocialPorRotaAlternativa(
			Integer idRota, int numeroPaginas, int quantidadeRegistros)
			throws ErroRepositorioException;

	/**
	 * [UC0014] Manter Imóvel
	 * 
	 * [FS0002] - Verificar existência de débito a cobrar de Tarifa de Cortado
	 * ativo para o imóvel
	 * 
	 * @author Raphael Rossiter
	 * @date 12/07/2010
	 * 
	 * @param idImovel
	 * @param anoMesFaturamento
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitoACobrarTarifaCortado(Integer idImovel,
			Integer anoMesFaturamento) throws ErroRepositorioException;

	/**
	 * [UC1042] Verificar Farturamento dos Imóveis Cortados
	 * 
	 * Apaga todos os debitos a cobrar, debitos a cobrar categoria e debito a
	 * cobrar geral, para o imovel/anoMes informado, com debito tipo =
	 * DebitoTipo.TARIFA_CORTADO e debito tipo situacao atual =
	 * DebitoCreditoSituacao.NORMAL
	 * 
	 * @autor Bruno Barros
	 * @date 13/07/2010
	 * 
	 * @param idImovel
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 */
	public Collection atualizarValorDebitoDaConta(int idImovel,
			int anoMesReferencia) throws ErroRepositorioException;

	/**
	 * [UC1035] Efetivar Alterar Inscrição de Imóvel
	 * 
	 * @author Hugo Amorim
	 * @throws ControladorException
	 * @data 08/07/201
	 */
	public Collection pesquisarImoveisComInscricaoPedenteParaAtualizacao(
			Integer idLocalidade, int numeroIndice, int quantidadeRegistros)
			throws ErroRepositorioException;

	/**
	 * 
	 * [UC1042] Verificar Farturamento dos Imóveis Cortados
	 * 
	 * Atualizar o indicador de faturamento do consumo historo
	 * 
	 * @param idConsumoHistorico
	 * @param indicadorFaturamento
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorFaturamentoConsumoHistorico(
			Integer idConsumoHistorico, short indicadorFaturamento)
			throws ErroRepositorioException;

	/**
	 * [UC1042] Verificar Farturamento dos Imóveis Cortados
	 * 
	 * @author Raphael Rossiter
	 * @date 13/07/2010
	 * 
	 * @param idRota
	 * @param anoMesFaturamento
	 * @param numeroPaginas
	 * @param quantidadeRegistros
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImovelComDebitoTarifaCortadoPorRota(
			Integer idRota, Integer anoMesFaturamento, int numeroPaginas,
			int quantidadeRegistros) throws ErroRepositorioException;

	/**
	 * [UC1042] Verificar Farturamento dos Imóveis Cortados
	 * 
	 * @author Raphael Rossiter
	 * @date 13/07/2010
	 * 
	 * @param idRota
	 * @param anoMesFaturamento
	 * @param numeroPaginas
	 * @param quantidadeRegistros
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImovelComDebitoTarifaCortadoPorRotaAlternativa(
			Integer idRota, Integer anoMesFaturamento, int numeroPaginas,
			int quantidadeRegistros) throws ErroRepositorioException;

	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 * 
	 * @author Vivianne Sousa
	 * @date 21/07/2010
	 */
	public Conta pesquisarUltimaContaDoImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC1042] Verificar Farturamento dos Imóveis Cortados
	 * 
	 * Apaga todos os debitos a cobrar, debitos a cobrar categoria e debito a
	 * cobrar geral, para o imovel/anoMes informado, com debito tipo =
	 * DebitoTipo.TARIFA_CORTADO e debito tipo situacao atual =
	 * DebitoCreditoSituacao.NORMAL
	 * 
	 * @autor Sávio Luiz
	 * @date 13/07/2010
	 * 
	 * @param idImovel
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 */
	public void deletarDebitosCobradosCategoriaImoveisCortados(int idImovel,
			int anoMesReferencia) throws ErroRepositorioException;

	/**
	 * [UC1042] Verificar Farturamento dos Imóveis Cortados
	 * 
	 * Apaga todos os debitos a cobrar, debitos a cobrar categoria e debito a
	 * cobrar geral, para o imovel/anoMes informado, com debito tipo =
	 * DebitoTipo.TARIFA_CORTADO e debito tipo situacao atual =
	 * DebitoCreditoSituacao.NORMAL
	 * 
	 * @autor Sávio Luiz
	 * @date 13/07/2010
	 * 
	 * @param idImovel
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 */
	public void deletarDebitosCobradosImoveisCortados(int idImovel,
			int anoMesReferencia) throws ErroRepositorioException;

	/**
	 * [UC0113] Faturar Grupo de Faturamento
	 * 
	 * @Author Hugo Amorim
	 * @Date 29/07/2010
	 * 
	 * @param idFaturamentoGrupo
	 *            Id do Faturamento Grupo
	 * @param anoMesReferencia
	 *            Ano Mes Referencia
	 * @exception ErroRepositorioException
	 *                Erro de Hibernate
	 */
	public void deletarResumoFaturamentoSimulacaoDetalheDebito(
			Integer idFaturamentoGrupo, Integer anoMesReferencia, Integer idRota)
			throws ErroRepositorioException;

	/**
	 * [UC0113] Faturar Grupo de Faturamento
	 * 
	 * @Author Hugo Amorim
	 * @Date 29/07/2010
	 * 
	 * @param idFaturamentoGrupo
	 *            Id do Faturamento Grupo
	 * @param anoMesReferencia
	 *            Ano Mes Referencia
	 * @exception ErroRepositorioException
	 *                Erro de Hibernate
	 */
	public void deletarResumoFaturamentoSimulacaoDetalheCredito(
			Integer idFaturamentoGrupo, Integer anoMesReferencia, Integer idRota)
			throws ErroRepositorioException;

	/**
	 * [UC0113] Faturar Grupo de Faturamento
	 * 
	 * @Author Hugo Amorim
	 * @Date 02/08/2010
	 * 
	 */
	public Collection<ResumoFaturamentoSimulacaoDebito> pesquisarResumoFaturamentoDebitoSimulacao(
			ResumoFaturamentoSimulacao resumoFaturamentoSimulacao)
			throws ErroRepositorioException;

	/**
	 * [UC0113] Faturar Grupo de Faturamento
	 * 
	 * @Author Hugo Amorim
	 * @Date 02/08/2010
	 * 
	 */
	public Collection<ResumoFaturamentoSimulacaoCredito> pesquisarResumoFaturamentoCreditoSimulacao(
			ResumoFaturamentoSimulacao resumoFaturamentoSimulacao)
			throws ErroRepositorioException;

	/**
	 * [UC1051] Gerar Relatório de Amostragem das Anormalidades Informadas
	 * 
	 * @author Hugo Leonardo
	 * @date 09/08/2010
	 * 
	 * @exception ErroRepositorioException
	 */
	public Collection pesquisarDadosRelatorioAnormalidadeConsumoPorAmostragem(
			Integer idGrupoFaturamento, Short codigoRota,
			Integer idGerenciaRegional, Integer idUnidadeNegocio,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idSetorComercialInicial, Integer idSetorComercialFinal,
			Integer referencia, Integer idImovelPerfil,
			Integer numOcorConsecutivas, String indicadorOcorrenciasIguais,
			Integer mediaConsumoInicial, Integer mediaConsumoFinal,
			Collection<Integer> colecaoIdsAnormalidadeConsumo,
			Collection<Integer> colecaoIdsAnormalidadeLeitura,
			Collection<Integer> colecaoIdsAnormalidadeLeituraInformada,
			Integer tipoMedicao, Collection<Integer> colecaoIdsEmpresa,
			Integer numeroQuadraInicial, Integer numeroQuadraFinal,
			Integer idCategoria, Integer limite)
			throws ErroRepositorioException;

	/**
	 * [UC1051] Gerar Relatório de Amostragem das Anormalidades Informadas
	 * 
	 * @author Hugo Leonardo
	 * @date 09/08/2010
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalDadosRelatorioAnormalidadeConsumoPorAmostragem(
			Integer idGrupoFaturamento, Short codigoRota,
			Integer idGerenciaRegional, Integer idUnidadeNegocio,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idSetorComercialInicial, Integer idSetorComercialFinal,
			Integer referencia, Integer idImovelPerfil,
			Integer numOcorConsecutivas, String indicadorOcorrenciasIguais,
			Integer mediaConsumoInicial, Integer mediaConsumoFinal,
			Collection<Integer> colecaoIdsAnormalidadeConsumo,
			Collection<Integer> colecaoIdsAnormalidadeLeitura,
			Collection<Integer> colecaoIdsAnormalidadeLeituraInformada,
			Integer tipoMedicao, Collection<Integer> colecaoIdsEmpresa,
			Integer numeroQuadraInicial, Integer numeroQuadraFinal,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * [UC0820] - Atualizar Faturamento do Movimento Celular
	 * 
	 * Verifica se a quantidade de imóveis que chegaram é a esperada.
	 * 
	 * @author bruno
	 * @date 16/08/2010
	 * 
	 * @param idRota
	 *            - Id da rota ser verificada
	 * @param anoMesFaturamento
	 *            - Ano mes de faturamento a ser pesquisado
	 * 
	 * @return Integer
	 * 
	 */
	public Integer pesquisarDiferencaQuantidadeMovimentoContaPrefaturadaArquivoTextoRoteiroEmpresa(
			Integer idRota, Integer anoMesFaturamento)
			throws ErroRepositorioException;

	/**
	 * 
	 * Pesquisa a conta historico digitada
	 * 
	 * @author Fernando Fontelles
	 * @date 06/08/2010
	 * 
	 * @param idImovel
	 * @param referenciaConta
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */

	public Object[] pesquisarContaHistoricoDigitada(String idImovel,
			String referenciaConta) throws ErroRepositorioException;

	/**
	 * 
	 * Pesquisa os imóveis que ja foram enviados para uma determinada rota em
	 * impressão simultanea
	 * 
	 * @autor Bruno Barros.
	 * @date 24/08/2010
	 * 
	 * @param idRota
	 *            - Id da rota a ser pesquisada
	 * 
	 * @return Collection<Integer> - Matriculas dos imóveis que ja foram
	 *         processados
	 */
	public Collection<Integer> pesquisarImoveisJaProcessadosBufferImpressaoSimultanea(
			Integer idRota) throws ErroRepositorioException;

	/**
	 * Metodo que retornar o grupo de faturamento a partir do id do Imovel
	 * 
	 * @author Rômulo Aurélio
	 * @date 24/08/2010
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public FaturamentoGrupo recuperaGrupoFaturamentoDoImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 18/08/2010
	 * 
	 * @param idOrdemServico
	 * @throws ErroRepositorioException
	 */
	public Collection verificarExistenciaAutosInfracaoPorOS(
			Integer idOrdemServico) throws ErroRepositorioException;

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 18/08/2010
	 * 
	 * @param idOrdemServico
	 * @throws ErroRepositorioException
	 */
	public AutosInfracao pesquisarAutosInfracaoPorOS(Integer idOrdemServico)
			throws ErroRepositorioException;

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 24/08/2010
	 * 
	 * @param idAutoInfracao
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisaAutosInfracaoDebitoACobrar(Integer idAutoInfracao)
			throws ErroRepositorioException;

	/**
	 * Obter o número de retificações da Conta
	 * 
	 * @author Hugo Leonardo
	 * @date 10/08/2010
	 * 
	 * @return void
	 * @throws ControladorException
	 */
	public Integer obterQuantidadeAlteracoesVencimentoConta(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC1073] – Religar Imóveis Cortados com Consumo Real
	 * 
	 * Data: 13/09/2010
	 * 
	 * @author Vivianne Sousa
	 */
	public Collection pesquisarImoveisCortados(Integer situacaoAgua,
			Date dataCorte, Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * [UC0014] Manter Imóvel
	 * 
	 * Verifica se o mesmo cliente está associado ao Imóvel em ClienteImovel.
	 * 
	 * Autor: Hugo Leonardo Data: 03/09/2010
	 */
	public int pesquisarExisteClienteAssociadoAoImovelEmClienteImovel(
			Integer idImovel, Integer idCliente)
			throws ErroRepositorioException;

	/**
	 * 
	 * @author Fernando Fontelles
	 * @date 27/09/2010
	 * 
	 *       Retorna as contas prefaturadas para enviar email com a conta.
	 * 
	 */

	public Collection pesquisarContasPrefaturadasParaEnvioEmail(
			SistemaParametro sistemaParametro, Integer idRota)
			throws ErroRepositorioException;

	/**
	 * [UC1010] Emitir 2ª via de declaração anual de quitação de débitos
	 * 
	 * @Author Daniel Alves
	 * @Date 14/09/2010
	 * 
	 */
	public Collection pesquisarAnoImovelEmissao2ViaDeclaracaoAnualQuitacaoDebitos(
			String idImovel) throws ErroRepositorioException;

	/**
	 * [UC1073] – Religar Imóveis Cortados com Consumo Real
	 * 
	 * Data: 13/09/2010
	 * 
	 * @author Vivianne Sousa
	 */
	public String pesquisarImoveisConsumoFaturadoReal(Integer idImovel,
			Integer anoMesReferencia, Integer consumoTipo, Integer ligacaoTipo)
			throws ErroRepositorioException;

	/**
	 * [UC1073] – Religar Imóveis Cortados com Consumo Real
	 * 
	 * Data: 15/09/2010
	 * 
	 * @author Vivianne Sousa
	 */
	public void religarImovelCortado(Integer idImovel,
			Integer idLigacaoAguaSituacao, Date dataReligacaoAgua)
			throws ErroRepositorioException;

	/**
	 * [UC0352] Emitir Contas e Cartas [SB0005] - Obter Dados da Medição da
	 * Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 20/09/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public Object[] obterLeituraAnteriorEAtualConta(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC0352] Emitir Contas e Cartas [SB0005] - Obter Dados da Medição da
	 * Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 20/09/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public Object[] obterLeituraAnteriorEAtualContaHistorico(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Método que retorna as contas para impressao
	 * 
	 * Pesquisar Contas Emitir(Ficha de Compensação)Caern
	 * 
	 * @author Vivianne Sousa
	 * @date 13/10/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasFichaCompensacaoEmitirCAERN(
			Integer idTipoConta, Integer idEmpresa, Integer anoMesReferencia,
			Integer idFaturamentoGrupo, BigDecimal valorContaFichaComp)
			throws ErroRepositorioException;

	/**
	 * Método que retorna as contas para impressao
	 * 
	 * Pesquisar Contas Emitir(Ficha de Compensação)Caern
	 * 
	 * @author Vivianne Sousa
	 * @date 13/10/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasFichaCompensacaoEmitirOrgaoPublicoCAERN(
			Integer idTipoConta, Integer idEmpresa, Integer anoMesReferencia,
			Integer idFaturamentoGrupo, BigDecimal valorContaFichaComp)
			throws ErroRepositorioException;

	/**
	 * 
	 * [UC1083] Prescrever Débitos de Imóveis Públicos Manual
	 * 
	 * @author Hugo Leonardo
	 * @date 18/10/2010
	 * 
	 * @param prescreverDebitosImovelHelper
	 * @throws ErroRepositorioException
	 */
	public void prescreverDebitosImoveisPublicosManual(
			PrescreverDebitosImovelHelper helper)
			throws ErroRepositorioException;

	/**
	 * 
	 * [UC1083] Prescrever Débitos de Imóveis Públicos Automático
	 * 
	 * @author Hugo Leonardo
	 * @date 19/10/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection obterDadosPrescricaoDebitosAutomaticos()
			throws ErroRepositorioException;

	/**
	 * 
	 * [UC1083] Prescrever Débitos de Imóveis Públicos Automático
	 * 
	 * @author Hugo Leonardo
	 * @date 19/10/2010
	 * 
	 * @param idFuncionalidadeIniciada
	 * @param anoMesFaturamento
	 * @throws ErroRepositorioException
	 */
	public void prescreverDebitosImoveisPublicosAutomatico(
			Integer anoMesReferencia, Integer anoMesPrescricao,
			Integer usuario, String idsEsferaPoder)
			throws ErroRepositorioException;

	public Integer pesquisarEmitirHistogramaAguaVolumeConsumo(
			FiltrarEmitirHistogramaAguaHelper filtro, Short consumo,
			Subcategoria subcategoria, Short medicao)
			throws ErroRepositorioException;

	/**
	 * 
	 * [UC0113] Faturar Grupo de Faturamento
	 * 
	 * @author Sávio Luiz
	 * @date 03/12/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarDataLeituraAtualMovimentoContaPreFaturada(
			Integer amMovimento, Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * 
	 * Verifica a quantidade de imóveis que estão em movimento conta pre
	 * faturada para um ano mes e rota
	 * 
	 * @param anoMesReferencia
	 * @param idRota
	 * @return
	 * @throws ErroRepositorioException
	 */
	public int verificarQuantidadeImoveisMovimentoContaPreFaturada(
			int anoMesReferencia, int idRota) throws ErroRepositorioException;

	/**
	 * [RM-4643 (COMPESA)] Verificamos se o imóvel sofreu alterações depois de
	 * ter sido mandado para o GSAN a primeira vez
	 * 
	 * @author Bruno Barros
	 * @date 14/12/2010
	 * 
	 * @param anoMes
	 * @param idImovel
	 * @param tipoMedicao
	 * @param leitura
	 * @param idAnormalidade
	 * @return
	 * @throws ControladorException
	 */
	public boolean reprocessarImovelImpressaoSimultanea(Integer anoMes,
			Integer idImovel, Short tipoMedicao, Integer leitura,
			Integer idAnormalidade, Short icImpresso)
			throws ErroRepositorioException;

	/**
	 * Recupera o debitoCreditoSituacaoAtual da Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 22/11/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarDebitoCreditoSituacaoAtualDaConta(Integer idImovel,
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * [UC0651] Inserir Comando de Negativação [FS0031] – Verificar existência
	 * de conta em nome do cliente
	 * 
	 * Pesquisa os relacionamentos entre cliente e conta.
	 * 
	 * @author Vivianne Sousa
	 * @date 29/12/2010
	 * 
	 * @param idCliente
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection verificarSeExisteClienteConta(Integer idCliente,
			Collection colecaoContasIds) throws ErroRepositorioException;

	/**
	 * 
	 * Retificação de um conjunto de contas que foram pagas e que o pagamento
	 * não estava o débito e/ou crédito (Conta paga via Impressão Simultânea)
	 * 
	 * @author Sávio Luiz
	 * @date 27/12/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasPagasSemDebitoCreditoPago(
			Integer amreferencia, Integer idGrupo)
			throws ErroRepositorioException;

	/**
	 * 
	 * Retificação de um conjunto de contas que foram pagas e que o pagamento
	 * não estava o débito e/ou crédito (Conta paga via Impressão Simultânea)
	 * 
	 * @author Sávio Luiz
	 * @date 28/12/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public List pesquisarValorPrestacaoDebitoCobradoSemreferencia(
			Integer idConta) throws ErroRepositorioException;

	/**
	 * Inserir Débitos para as contas impressas via Impressão Simultânea de
	 * Contas que sairam com o valor da conta errada (Alguns grupos com tarifa
	 * proporcional que não estava levando em consideração a quantidade de
	 * economias)
	 * 
	 * @author Sávio Luiz
	 * @date 12/01/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasComValorFaixasErradas(Integer amreferencia)
			throws ErroRepositorioException;

	/**
	 * [UC0150] Retificar Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 19/01/2011
	 * 
	 * @param idConta
	 * @return Rota
	 * @throws ErroRepositorioException
	 */
	public Rota pesquisarRotaParaRetificacao(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC0150] Retificar Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 20/01/2011
	 * 
	 * @param conta
	 * @param rota
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarArquivoTextoRoteiroEmpresaNaoFinalizado(
			Conta conta, Rota rota) throws ErroRepositorioException;

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [FS0002] - Verificar Situação Especial de Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 17/04/2008
	 * 
	 * @param rota
	 * @param numeroPaginas
	 * @param quantidadeRegistros
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImovelGerarArquivoTextoFaturamentoPorRotaAlternativa(
			Rota rota, int numeroPaginas, int quantidadeRegistros,
			SistemaParametro sistemaParametro, Integer idImovelCondominio)
			throws ErroRepositorioException;

	/**
	 * [UC0204] Consultar Conta
	 * 
	 * Pesquisa o consumo faturado do imóvel
	 * 
	 * @author Mariana Victor
	 * @date 06/01/2011
	 * 
	 * @param [UC0204] Consultar Conta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] consultarConsumoCadastrado(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0473] Consultar Dados Complementares do Imóvel
	 * 
	 * Pesquisa as matrículas associadas à mesma tarifa de consumo do imóvel.
	 * 
	 * @author Mariana Victor
	 * @date 06/01/2011
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> consultarMatriculasAssociadas(
			Integer idConsumoTarifa, Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0600] Emitir Histograma de Esgoto - Volume Faturado Ligacao Estimado
	 * ou Real
	 * 
	 * 
	 * @author Rafael Pinto
	 * @date 05/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoHelper
	 * 
	 * @return Collection<OrdemServico>
	 * @throws ControladorException
	 */
	public Integer pesquisarEmitirHistogramaEsgotoVolumeConsumo(
			FiltrarEmitirHistogramaEsgotoHelper filtro, Short consumo,
			Subcategoria subcategoria, Short medicao)
			throws ErroRepositorioException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [FS0048] – Verificar existência da conta.
	 * 
	 * @author Mariana Victor
	 * @date 27/01/2011
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Conta pesquisarContaAnoMesImovel(Integer idImovel,
			int anoMesReferencia) throws ErroRepositorioException;

	/**
	 * [UC0146] Manter Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 11/02/2011
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisaQtdeContaRetificadaMotivo(Integer idMotivo,
			Integer idImovel, Date dataLimite) throws ErroRepositorioException;

	/**
	 * [UC0146] Manter Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 11/02/2011
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisaQtdeContaHistoricoRetificadaMotivo(Integer idMotivo,
			Integer idImovel, Date dataLimite) throws ErroRepositorioException;

	/**
	 * [UC0146] Manter Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 11/02/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisaTabelaColunaContaMotivoRetificacaoColuna(
			Integer idMotivo) throws ErroRepositorioException;

	/**
	 * [UC1122] Automatizar Perfis de Grandes Consumidores
	 * 
	 * Pesquisa o consumo faturado do imóvel
	 * 
	 * @author Mariana Victor
	 * @date 06/01/2011
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer consultarImovelConsumoHistorico(Integer idImovel)
			throws ErroRepositorioException;

	public BigDecimal obterValorConta(Conta conta)
			throws ErroRepositorioException;

	/**
	 * 
	 * Consulta conta Tipo de conta impressao no emitir contas
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer consultarContaTipodeContaImpressao(Integer idConta)
			throws ErroRepositorioException;

	public Integer retornaAnoMesGrupoFaturamento(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0352] Emitir Contas e Cartas
	 * 
	 * Retorna o cliente usuário
	 * 
	 * @author Mariana Victor
	 * @date 11/03/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Cliente obterClienteConta(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC1129] Gerar Relatório Devolução dos Pagamentos em Duplicidade
	 * 
	 * @author Hugo Leonardo
	 * @date 10/03/2011
	 * 
	 * @param FiltrarRelatorioDevolucaoPagamentosDuplicidadeHelper
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioDevolucaoPagamentosDuplicidade(
			FiltrarRelatorioDevolucaoPagamentosDuplicidadeHelper helper)
			throws ErroRepositorioException;

	/**
	 * [UC1129] - Relatorio Devolucao dos Pagamentos em Duplicidade
	 * 
	 * @param idImovel
	 *            Id do Imovel
	 * @param anoMesCobrancaCredito
	 *            Ano/Mes da cobranca do credito
	 * 
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<CreditoRealizadoHistorico> pesquisarCreditosRealizadoHistorico(
			Integer idImovel, Integer anoMesCobrancaCredito)
			throws ErroRepositorioException;

	/**
	 * [UC1129] - Relatorio Devolucao dos Pagamentos em Duplicidade
	 * 
	 * @param idImovel
	 *            Id do Imovel
	 * @param anoMesCobrancaCredito
	 *            Ano/Mes da cobranca do credito
	 * 
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<CreditoRealizado> pesquisarCreditosRealizado(
			Integer idImovel, Integer anoMesCobrancaCredito)
			throws ErroRepositorioException;

	/**
	 * [UC1157] Seleciona Comando para Retirar Imóvel da Tarifa Social [SB0003]
	 * Excluir Comando Selecionado
	 * 
	 * @author Vivianne Sousa
	 * @date 01/04/2011
	 * 
	 * @exception ErroRepositorioException
	 */
	public Integer pesquisarQtdeContaNaoPaga(Collection idContas)
			throws ErroRepositorioException;

	/**
	 * [UC0113] Faturar Grupo de Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 10/03/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer gerarSequencialContaBoleto() throws ErroRepositorioException;

	/**
	 * [UC0352] Emitir Contas e Cartas
	 * 
	 * [SB0031] Obter Representação numérica do Nosso Número da Ficha de
	 * Compensação
	 * 
	 * @author Raphael Rossiter
	 * @date 10/03/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarSequencialContaBoleto(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC0724] - Processar Pagamento com Ficha de Compensação
	 * 
	 * @author Raphael Rossiter
	 * @date 15/03/2011
	 * 
	 * @param nnBoleto
	 * @return Conta
	 * @throws ErroRepositorioException
	 */
	public Conta pesquisarExistenciaContaPorNumeroBoleto(Integer nnBoleto)
			throws ErroRepositorioException;

	/**
	 * [UC0724] - Processar Pagamento com Ficha de Compensação
	 * 
	 * @author Raphael Rossiter
	 * @date 15/03/2011
	 * 
	 * @param nnBoleto
	 * @return Conta
	 * @throws ErroRepositorioException
	 */
	public Conta pesquisarExistenciaContaPorIdentificadorEValor(
			Integer idConta, BigDecimal valorPagamento)
			throws ErroRepositorioException;

	/**
	 * [UC0724] - Processar Pagamento com Ficha de Compensação
	 * 
	 * @author Raphael Rossiter
	 * @date 15/03/2011
	 * 
	 * @param nnBoleto
	 * @return Conta
	 * @throws ErroRepositorioException
	 */
	public Conta pesquisarExistenciaContaPorIdentificadorTruncadoEValor(
			Integer idConta, BigDecimal valorPagamento)
			throws ErroRepositorioException;

	/**
	 * [UC0724] - Processar Pagamento com Ficha de Compensação
	 * 
	 * @author Raphael Rossiter
	 * @date 15/03/2011
	 * 
	 * @param nnBoleto
	 * @return Conta
	 * @throws ErroRepositorioException
	 */
	public ContaHistorico pesquisarExistenciaContaHistoricoPorNumeroBoleto(
			Integer nnBoleto) throws ErroRepositorioException;

	/**
	 * [UC0724] - Processar Pagamento com Ficha de Compensação
	 * 
	 * @author Raphael Rossiter
	 * @date 15/03/2011
	 * 
	 * @param nnBoleto
	 * @return Conta
	 * @throws ErroRepositorioException
	 */
	public ContaHistorico pesquisarExistenciaContaHistoricoPorIdentificadorEValor(
			Integer idConta, BigDecimal valorPagamento)
			throws ErroRepositorioException;

	/**
	 * [UC0724] - Processar Pagamento com Ficha de Compensação
	 * 
	 * @author Raphael Rossiter
	 * @date 15/03/2011
	 * 
	 * @param nnBoleto
	 * @return Conta
	 * @throws ErroRepositorioException
	 */
	public ContaHistorico pesquisarExistenciaContaHistoricoPorIdentificadorTruncadoEValor(
			Integer idConta, BigDecimal valorPagamento)
			throws ErroRepositorioException;

	public Object[] pesquisarCreditoARealizar(Integer IdCreditoARealizar,
			Integer anoMesFaturamento) throws ErroRepositorioException;

	/**
	 * [UC1129] - Relatorio Devolucao dos Pagamentos em Duplicidade
	 * 
	 * @param idImovel
	 *            Id do Imovel
	 * @param anoMesCobrancaCredito
	 *            Ano/Mes da cobranca do credito
	 * 
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public BigDecimal pesquisarValorCreditosARealizarHistorico(
			Integer idImovel, Integer anoMesCredito)
			throws ErroRepositorioException;

	/**
	 * [UC1129] - Relatorio Devolucao dos Pagamentos em Duplicidade
	 * 
	 * @param idImovel
	 *            Id do Imovel
	 * @param anoMesCobrancaCredito
	 *            Ano/Mes da cobranca do credito
	 * 
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public BigDecimal pesquisarValorCreditosARealizar(Integer idImovel,
			Integer anoMesCredito) throws ErroRepositorioException;

	/**
	 * [UC0866] Gerar Comando Contas em Cobrança por Empresa
	 * 
	 * Pesquisa a quantidade de contas, agrupando por imóvel
	 * 
	 * @author: Mariana Victor
	 * @date: 07/04/2011
	 */
	public Collection<Object[]> pesquisarQuantidadeContasAgrupandoPorImovel(
			ComandoEmpresaCobrancaContaHelper comandoEmpresaCobrancaContaHelper)
			throws ErroRepositorioException;

	/**
	 * [UC0933] Alterar Leiturista do Arquivo Texto para Leitura
	 * 
	 * Alterar o leiturista da tabela de movimento conta prefaturada
	 * 
	 * @author Bruno Barros
	 * @Data 12/04/2011
	 * 
	 */
	public void alterarLeituristaMovimentoRoteiroEmpresa(Integer idRota,
			Integer anoMes, Integer idLeituristaNovo)
			throws ErroRepositorioException;

	/**
	 * [UC0933] Alterar Leiturista do Arquivo Texto para Leitura
	 * 
	 * Alterar o leiturista da tabela de movimento conta prefaturada
	 * 
	 * @author Bruno Barros
	 * @Data 12/04/2011
	 * 
	 */
	public void alterarLeituristaMovimentoRoteiroEmpresa(
			Collection<Integer> idsImovel, Integer anoMes,
			Integer idLeituristaNovo) throws ErroRepositorioException;

	/**
	 * [UC1166] Gerar txt para impressão de contas no formato braille
	 * 
	 * @author Vivianne Sousa
	 * @date 20/04/2011
	 */
	public Collection pesquisarContaBraille(Integer anoMesFaturamento)
			throws ErroRepositorioException;

	/**
	 * [UC1166] Gerar txt para impressão de contas no formato braille
	 * 
	 * @author Vivianne Sousa
	 * @date 20/04/2011
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterQuantidadeEconomiasContaCategoria(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * [UC1166] Gerar txt para impressão de contas no formato braille
	 * 
	 * @author Vivianne Sousa
	 * @date 20/04/2011
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGrupoFaturamentoGrupoNaoFaturados(
			Integer anoMesReferenciaFaturamento)
			throws ErroRepositorioException;

	/**
	 * [UC1166] Gerar txt para impressão de contas no formato braille
	 * 
	 * @author Vivianne Sousa
	 * @date 25/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasBrailleEmitir(
			Collection<Integer> idTipoConta, Integer anoMesReferencia,
			Integer anoMesReferenciaFaturamentoAntecipado)
			throws ErroRepositorioException;

	/**
	 * [UC1166] Gerar txt para impressão de contas no formato braille
	 * 
	 * @author Vivianne Sousa
	 * @date 25/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasBrailleClienteResponsavelFichaCompensacao(
			Integer idTipoConta, Integer anoMesReferencia,
			Short indicadorEmissaoExtratoFaturamento,
			Integer anoMesReferenciaFaturamentoAntecipado)
			throws ErroRepositorioException;

	/**
	 * [UC1166] Gerar txt para impressão de contas no formato braille
	 * 
	 * @author Vivianne Sousa
	 * @date 25/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasBrailleClienteResponsavelNaoFichaCompensacao(
			Integer idTipoConta, Integer anoMesReferencia,
			Short indicadorEmissaoExtratoFaturamento,
			Integer anoMesReferenciaFaturamentoAntecipado)
			throws ErroRepositorioException;

	/**
	 * [UC1166] Gerar txt para impressão de contas no formato braille
	 * 
	 * @author Vivianne Sousa
	 * @date 25/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasBrailleClienteResponsavel(
			Collection<Integer> idTipoConta, Integer anoMesReferencia,
			Short indicadorEmissaoExtratoFaturamento,
			Integer anoMesReferenciaFaturamentoAntecipado)
			throws ErroRepositorioException;

	/**
	 * [UC1166] Gerar txt para impressão de contas no formato braille
	 * 
	 * @author Vivianne Sousa
	 * @date 25/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasBrailleFichaCompensacao(
			Integer idTipoConta, Integer anoMesReferencia,
			Integer anoMesReferenciaFaturamentoAntecipado)
			throws ErroRepositorioException;

	/**
	 * [UC1166] Gerar txt para impressão de contas no formato braille
	 * 
	 * @author Vivianne Sousa
	 * @date 25/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasBrailleNaoFichaCompensacao(
			Integer idTipoConta, Integer anoMesReferencia,
			Integer anoMesReferenciaFaturamentoAntecipado)
			throws ErroRepositorioException;

	/**
	 * [UC1166] Gerar txt para impressão de contas no formato braille
	 * 
	 * @author Vivianne Sousa
	 * @date 20/04/2011
	 */
	public Conta obterObjetoConta(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 01/06/2011
	 * @param idImovel
	 * @param anoMesReferencia
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean pesquisarContaDoImovelDiferentePreFaturada(Integer idImovel,
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * [UC0242] - Registrar Movimento de Arrecadadores Author:
	 * 
	 * @author Raphael Rossiter
	 * @date 03/06/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorContaComSituacaoAtual(Integer idImovel,
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * [UC1169] Movimentar Ordens de Serviço de Cobrança por Resultado
	 * 
	 * Pesquisa a quantidade de contas, agrupando por imóvel
	 * 
	 * @author: Mariana Victor
	 * @date: 12/05/2011
	 */
	public Collection<Object[]> pesquisarQuantidadeContasComandoAgrupandoPorImovel(
			MovimentarOrdemServicoEmitirOSHelper movimentarOrdemServicoEmitirOSHelper)
			throws ErroRepositorioException;

	/**
	 * [UC1169] Movimentar Ordens de Serviço de Cobrança por Resultado
	 * 
	 * Pesquisa a quantidade de contas
	 * 
	 * @author: Mariana Victor
	 * @date: 12/05/2011
	 */
	public Object[] pesquisarQuantidadeContasComando(
			MovimentarOrdemServicoEmitirOSHelper movimentarOrdemServicoEmitirOSHelper)
			throws ErroRepositorioException;

	/**
	 * [UC1169] Movimentar Ordens de Serviço de Cobrança por Resultado
	 * 
	 * Pesquisa as ordens de serviço selecionadas
	 * 
	 * @author: Mariana Victor
	 * @date: 19/05/2011
	 */
	public Collection<Integer[]> pesquisarOSComandoSelecionado(
			MovimentarOrdemServicoEmitirOSHelper movimentarOrdemServicoEmitirOSHelper)
			throws ErroRepositorioException;

	/**
	 * [UC1173] Informar Consumo por Parâmetros
	 * 
	 * [FS0005] – Validar mês ano maior ou igual
	 * 
	 * @author Mariana Victor
	 * @date 20/05/2011
	 * 
	 * @param anoMesReferenciaInformado
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarAnoMesReferenciaMenorAnoMesReferenciaFaturamentoGrupo(
			int anoMesReferenciaInformado) throws ErroRepositorioException;

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * @author Rafael Pinto
	 * @date 20/05/2011
	 * 
	 * @param idImovel
	 *            Integer
	 * @param dataFaturamento
	 *            Date
	 * 
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarConsumoTarifaVigenciaImovel(Integer idImovel,
			Date dataFaturamento) throws ErroRepositorioException;

	/**
	 * UC 8xx - Relatório das Multas de Autos de Infração Pendentes
	 * 
	 * @author Hugo Azevedo
	 * @date 11/06/2011
	 * 
	 * @throws ErroRepositorioException
	 */

	public Collection obterColecaoGrupoFaturamento()
			throws ErroRepositorioException;

	/**
	 * [UC0713] Emitir Ordem de Serviço Seletiva [SB0002] Gerar TXT
	 * 
	 * @author Vivianne Sousa
	 * @date 29/06/2011
	 */
	public Integer pesquisarFaturamentoGrupoImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Acumula o valor de agua cancelado por retificação
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2011
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal diferencaValorAguaCanceladaRetificacao(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	// Tiago Moreno - 21/07/11
	public Collection pesquisarContaCategoriaResumo(Integer idConta)
			throws ErroRepositorioException;

	// Tiago Moreno - 21/07/11
	public Collection pesquisarDebitoCobradoCategoriaResumo(Integer idConta)
			throws ErroRepositorioException;

	// Tiago Moreno - 21/07/11
	public Collection pesquisarCreditoRealizadoCategoriaResumo(Integer idConta)
			throws ErroRepositorioException;

	public Integer obterQuantidadeCreditosRealizados(Integer idConta)
			throws ErroRepositorioException;

	public Integer obterQuantidadeDebitoCobrados(Integer idConta)
			throws ErroRepositorioException;

	public Collection pesquisarGuiaPagamentoCategoriaResumo(
			Integer idGuiaPagamento) throws ErroRepositorioException;

	public Collection pesquisarDebitoACobrarCategoriaResumo(
			Integer idDebitoACobrar) throws ErroRepositorioException;

	public Collection pesquisarCreditoARealizarCategoriaResumo(
			Integer idCreditoARealizar) throws ErroRepositorioException;

	public Integer pesquisarQuantidadeEconomiasCreditoARealizar(
			Integer idCreditoARealizar) throws ErroRepositorioException;

	/**
	 * Acumula o valor de esgoto cancelado por retificação
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2011
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal diferencaValorEsgotoCanceladaRetificacao(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	/**
	 * Acumula o valor de esgoto incluido por retificacao
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2011
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal diferencaValorEsgotoRetificada(int anoMesReferencia,
			int idLocalidade, int idCategoria) throws ErroRepositorioException;

	/**
	 * [UC0840] - Atualizar Faturamento do Movimento Celular
	 * 
	 * @author Raphael Rossiter
	 * @date 20/07/2011
	 * 
	 * @param idConta
	 * @param idMedicaoTipo
	 * @return MovimentoContaPrefaturada
	 * @throws ErroRepositorioException
	 */
	public MovimentoContaPrefaturada pesquisarMovimentoContaPrefaturada(
			Integer idConta, Integer idMedicaoTipo)
			throws ErroRepositorioException;

	/**
	 * [UC0840] - Atualizar Faturamento do Movimento Celular
	 * 
	 * @author Raphael Rossiter
	 * @date 20/07/2011
	 * 
	 * @param movimentoContaPrefaturada
	 * @throws ErroRepositorioException
	 */
	public void atualizarMedicaoHistoricoMovimentoCelular(
			MovimentoContaPrefaturada movimentoContaPrefaturada)
			throws ErroRepositorioException;

	/**
	 * [UC0840] - Atualizar Faturamento do Movimento Celular
	 * 
	 * @author Raphael Rossiter
	 * @date 20/07/2011
	 * 
	 * @param movimentoContaPrefaturada
	 * @param consumo
	 * @param idConsumoHistoricoImovelCondominio
	 * @param consumoImovelVinculadosCondominio
	 * @throws ErroRepositorioException
	 */
	public void atualizarConsumoHistoricoMovimentoCelular(
			MovimentoContaPrefaturada movimentoContaPrefaturada,
			Integer consumo, Integer idConsumoHistoricoImovelCondominio,
			Integer consumoImovelVinculadosCondominio)
			throws ErroRepositorioException;

	/**
	 * [UC1194] Consultar Estrutura Tarifária Loja Virtual [SB0001] Pesquisar
	 * Tarifa Social ou Tarifa Mínima
	 * 
	 * Método que vai retornar um Helper que possui o consumo da tarifa mínima e
	 * da tarifa social e seus respectivos valores.
	 * 
	 * @author Diogo Peixoto
	 * @since 14/07/2011
	 * 
	 * @param idTarifa
	 * @param idCategoria
	 * 
	 * @throws ErroRepositorioException
	 * 
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarTarifaSocialOuTarifaMinima(
			Integer idTarifa, Integer idCategoria)
			throws ErroRepositorioException;

	/**
	 * Acumula o valor de agua incluido por retificacao
	 * 
	 * [UC0155] Encerrar Faturamento do Mês
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2011
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal diferencaValorAguaRetificada(int anoMesReferencia,
			int idLocalidade, int idCategoria) throws ErroRepositorioException;

	/**
	 * [UC1194] Consultar Estrutura Tarifária Loja Virtual [SB0001] Pesquisar
	 * Tarifa Normal
	 * 
	 * Método que vai retornar um Helper que possui o consumo da tarifa normal e
	 * seus respectivos valores.
	 * 
	 * @author Diogo Peixoto
	 * @since 14/07/2011
	 * 
	 * @param idTarifa
	 * @param idCategoria
	 * 
	 * @throws ErroRepositorioException
	 * 
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarTarifaNormal(Integer idTarifa,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * [UC1194] Consultar Estrutura Tarifária Loja Virtual
	 * 
	 * Método que vai retornar um Helper que possui o consumo não medido de
	 * chafariz público.
	 * 
	 * @author Diogo Peixoto
	 * @since 06/09/2011
	 * 
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarEstruturaTarifariaChafarizPublico()
			throws ErroRepositorioException;

	/**
	 * [UC1187] Colocar Débito a Cobrar em Revisão
	 * 
	 * @author Mariana Victor
	 * @date 21/07/2011
	 * 
	 * @param debitoACobrar
	 * @throws ErroRepositorioException
	 */
	public void colocarRevisaoDebitoACobrar(DebitoACobrar debitoACobrar)
			throws ErroRepositorioException;

	/**
	 * [UC1188] Retirar Débito A Cobrar de Revisão
	 * 
	 * @author Mariana Victor
	 * @date 21/07/2011
	 * 
	 * @param debitoACobrar
	 * @throws ErroRepositorioException
	 */
	public void retirarDebitoACobrarRevisao(DebitoACobrar debitoACobrar)
			throws ErroRepositorioException;

	/**
	 * [UC1136] Inserir Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @date 21/07/2011
	 * 
	 * @param idDebitoACobrar
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public DebitoACobrar obterDebitoACobrar(Integer idDebitoACobrar)
			throws ErroRepositorioException;

	/**
	 * 
	 * @author Gustavo Amaral
	 * @date 20/07/2011
	 */
	public void atualizarIndicadorContaHistorico(Integer idFaturaItem)
			throws ErroRepositorioException;

	/**
	 * [UC0113 - Faturar Grupo Faturamento] Author: Leonardo Vieira, Rafael
	 * Santos DAta: 17/02/2006 Remove o Resumo Faturamento Simulçao
	 * 
	 * @param idFaturamentoGrupo
	 *            Id do Faturamento Grupo
	 * @param anoMesReferencia
	 *            Ano Mes Referencia
	 * @exception ErroRepositorioException
	 *                Erro de Hibernate
	 */
	public void deletarResumoFaturamento(Integer anoMesReferencia)
			throws ErroRepositorioException;

	/**
	 * [UC1136] Inserir Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @date 17/08/2011
	 * 
	 * @param idDebitoACobrar
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public DebitoTipo obterDebitoTipoCodigoConstante(Integer codigoConstante)
			throws ErroRepositorioException;

	/**
	 * [UC0113] - Faturar Grupo de Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 29/08/2011
	 * 
	 * @param idImovel
	 * 
	 * @return BigDecimal
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterPercentualColetaEsgotoImovel(Integer idImovel)
			throws ErroRepositorioException;

	public Collection pesquisarDadosRelatorioAutoInfracaoPendentes(
			Integer grupo, Integer funcionario) throws ErroRepositorioException;

	public Collection pesquisarItensParcelamentosNivel1(Integer idDebACobrar)
			throws ErroRepositorioException;

	public Collection pesquisarItensParcelamentosNivel2(Integer idDebACobrar)
			throws ErroRepositorioException;

	/**
	 * [UC1214] Informar Acerto Documentos Não Aceitos
	 * 
	 * [SB0002] ? Selecionar Débitos Pagos
	 * 
	 * @author Mariana Victor
	 * @date 23/08/2011
	 * 
	 * @param idImovel
	 * @param referenciaConta
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarContaOuContaHistoricoDigitada(String idImovel,
			String referenciaConta) throws ErroRepositorioException;

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 30/08/2011
	 * 
	 * @param idImovelMacro
	 * @param anoMesReferencia
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMovimentoContaPrefaturadaArquivoTextoFaturamento(
			Integer idImovelMacro, Integer anoMesReferencia)
			throws ErroRepositorioException;

	/**
	 * [UC1216] Suspender Leitura para Imóvel com Hidrômetro Retirado
	 * 
	 * [SB0001] – Selecionar Imóveis com Hidrômetro Retirado
	 * 
	 * @author Vivianne Sousa
	 * @date 23/08/2011
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarImovelNumeroDeOcorrenciasConsecultivasAnormalidades(
			Integer idAnormalidade, Integer qtdAnormalidades,
			Integer referenciaFaturamento, Integer grupofaturamento,
			Integer idRota) throws ErroRepositorioException;

	/**
	 * [UC1216] Suspender Leitura para Imóvel com Hidrômetro Retirado [SB0003] –
	 * Incluir Imóvel na Situação Especial de Faturamento
	 * 
	 * @author Vivianne Sousa
	 * @date 23/08/2011
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public LeituraAnormalidade obterNumeroMesesLeituraSuspensaLeituraAnormalidade(
			Integer idLeituraAnormalidade) throws ErroRepositorioException;

	/**
	 * [UC1218] Suspender Leitura para Imóvel com Consumo Real não Superior a
	 * 10m3
	 * 
	 * @author Vivianne Sousa
	 * @date 26/08/2011
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarImovelComConsumoRealNaoSuperiorA10(
			Integer qtdConsumoRealNaoSuperiorA10,
			Integer referenciaFaturamento, Integer grupofaturamento,
			Integer idRota, Integer numeroMesesReinicioSitEspFaturamento)
			throws ErroRepositorioException;

	/**
	 * [UC0457] - Encerrar Ordem de Serviço [SB0009 - Verificar Situação
	 * Especial de Faturamento]
	 * 
	 * @param idImovel
	 * @return FaturamentoSituacaoHistorico
	 * @throws ErroRepositorioException
	 */
	public FaturamentoSituacaoHistorico pesquisarFaturamentoSituacaoHistorico(
			Integer idImovel) throws ErroRepositorioException;

	/**
	 * [UC0457] - Encerrar Ordem de Serviço [SB0009 - Verificar Situação
	 * Especial de Faturamento]
	 * 
	 * @param faturamentoSituacaoHistorico
	 * @throws ErroRepositorioException
	 */
	public void atualizarFaturamentoSituacaoHistorico(
			FaturamentoSituacaoHistorico faturamentoSituacaoHistorico)
			throws ErroRepositorioException;

	/**
	 * Por Tiago Moreno Ajuste de mensagem da CAEMA Autorizado por Eduardo.
	 * 28/09/2011
	 */
	public String[] obterContaMensagemImovel(Integer imovelId, Integer amRef)
			throws ErroRepositorioException;

	/*
	 * TODO : COSANPA Método criado para não salvar na tabela de geração da rota
	 * todos os imóveis da rota, e sim, somente os que possuem conta
	 * pre-faturada
	 * 
	 * Obter quantidade de imóveis com conta Pré-faturada
	 */

	/**
	 * @author Adriana Muniz e Pamela Gatinho
	 * @date 13/12/2010
	 * 
	 * @return Collection Dados dos imóveis com conta pre-faturada
	 * @param anoMesReferencia
	 * @param rota
	 * @throws ErroRepositorioException
	 */
	public Collection obterImoveisComContaPF(Integer anoMesReferencia, Rota rota)
			throws ErroRepositorioException;

	/*
	 * TODO : COSANPA Método criado para não salvar na tabela de geração da rota
	 * todos os imóveis da rota, e sim, somente os que possuem conta
	 * pre-faturada
	 * 
	 * Obter quantidade de imóveis com conta Pré-faturada
	 */

	/**
	 * @author Adriana Muniz e Pamela Gatinho
	 * @date 13/12/2010
	 * 
	 * @return Integer quantidade de imóveis com conta pre-faturada
	 * @param anoMesReferencia
	 * @param rota
	 * @throws ErroRepositorioException
	 */
	public Integer obterQtdImoveisRotaDivididaComContaPF(
			Integer anoMesReferencia, List<Integer> imoveisIds)
			throws ErroRepositorioException;

	/*
	 * TODO : COSANPA
	 * 
	 * Dois métodos acescentados:
	 * 
	 * Método usado na geração do bonus social, que retorna o consumo de agua
	 * para determinado imóvel Que será utilizado para verificar uma das
	 * condições para o crédito ser concedido que é o consumo menor ou igual a
	 * 10
	 * 
	 * Método para verificar a subcategoria do imóvel(bônus social)
	 */
	/**
	 * @author Adriana Muniz e Welligton Rocha
	 * @date: 01/03/2011
	 * 
	 *        Pesquisa o consumo de agua faturado para um determinado imóvel
	 * 
	 * @return Integer consumo de agua do imóvel
	 * @param anoMesReferencia
	 * @param idImovel
	 * @throws ErroRepositorioException
	 * */
	public Integer pesquisarConsumoAguaImovel(Integer idImovel,
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * @author Adriana Muniz e Welligton Rocha
	 * @date: 01/03/2011
	 * 
	 *        Pesquisa a subcategoria do imóvel(Bonus social)
	 * 
	 * @return Integer com o id da subcategoria
	 * @param idImovel
	 * @throws ErroRepositorioException
	 * */
	public Integer pesquisarSubcategoriaImovel(Integer idImovel)
			throws ErroRepositorioException;

	/*
	 * TODO:COSANPA Métodos adicionados para pesquisar e excluir os creditos do
	 * tipo bonus social, para imoveis do IS Ultizado no retorno das informações
	 * enviadas pelo IS, para deletar os creditos realizados e a realizar, caso
	 * não tenha sido concedido, por consumo acima de 10 metros cúbicos
	 */
	/**
	 * @autorAdriana Muniz
	 * @date: 31/03/2011 Verifica se tem credito realizado para determinada
	 *        conta
	 * 
	 * @param idConta
	 *            Código da conta
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public CreditoRealizado pesquisarCreditoRealizadoBonusSocial(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * @author Adriana Muniz
	 * @date: 01/04/2011 Verifica se tem credito a realizar do tipo bonus social
	 *        para determinado imóvel
	 * 
	 * @param idImovel
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public CreditoARealizar pesquisarCreditoARealizarBonusSocial(
			Integer idImovel, Integer anoMesReferencia)
			throws ErroRepositorioException;

	/**
	 * @author Adriana Muniz
	 * @date: 01/04/2011 Exclui credito realizado do tipo bonus social para
	 *        determinado imóvel
	 * 
	 * @param idImovel
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public void excluirCreditoRealizadoBonusSocial(Integer idCreditoRealizado)
			throws ErroRepositorioException;

	/**
	 * @author Adriana Muniz
	 * @date: 01/04/2011 Exclui credito a realizar do tipo bonus social para
	 *        determinado imóvel
	 * 
	 * @param idImovel
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public void excluirCreditoARealizarBonusSocial(Integer idCreditoARealizar)
			throws ErroRepositorioException;

	/*
	 * TODO: COSANPA autor: Adriana Muniz Data: 12/05/2011 Utilizado na geração
	 * do arquivo da declaração de quitação anual débitos. verifica se o imovel
	 * possui endereço alternativo
	 */
	/**
	 * 
	 * Método que verifica se o imovel possui cliente responsável e indicacao de
	 * conta a ser entregue em outro endereço e que estejam nas quadras
	 * pertencentes às rota passada
	 * 
	 * Gerar arquivo da declaração de quitação anual de débitos
	 * 
	 * @author Adriana Muniz
	 * @date 12/05/2011
	 * 
	 * @param rota
	 *            , idImovel
	 * @return boolean
	 * @throws ErroRepositorioException
	 */
	public boolean verificaImovelPorRotasComContaEntregaEmOutroEndereco(
			Rota rota, Integer idImovel) throws ErroRepositorioException;

	/*
	 * TODO : COSANPA Método criado para obter os imóveis de uma rota que
	 * possuem conta
	 */

	/**
	 * @author Pamela Gatinho
	 * @date 21/06/2011
	 * 
	 * @return Collection Dados dos imóveis com conta
	 * @param anoMesReferencia
	 * @param rota
	 * @throws ErroRepositorioException
	 */
	public Collection obterImoveisComConta(Integer anoMesReferencia, Rota rota)
			throws ErroRepositorioException;

	/**
	 * TODO : COSANPA
	 * 
	 * @author Pamela Gatinho
	 * @date 04/08/2011
	 * 
	 *       Metodo que obtem a conta do imóvel, so não retorna a conta que
	 *       estiver com a situacao CANCELADA POR RETIFICACAO
	 * 
	 * @return Conta
	 * @param anoMesReferencia
	 * @param idImovel
	 * @throws ErroRepositorioException
	 */
	public Conta obterContaImovel(Integer idImovel, Integer anoMesReferencia)
			throws ErroRepositorioException;

	/*
	 * TODO:COSANPA Método chamado para buscar creditos do imovel que tenha
	 * valor residual diferente de zero, caso esse valor seja menor que o valor
	 * da conta no processamento de retorno do IS
	 */
	/**
	 * Autor: Adriana Muniz Data: 09/08/2011 Método retono todos os créditos a
	 * realizar do imovel que tenha valor residual diferente de zero
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection buscarCreditoARelizarPorImovel(Integer idImovel)
			throws ErroRepositorioException;

	/*
	 * TODO: COSANPA Alteração para calcular corretamente o valor do imposto no
	 * resumo do faturamento
	 */
	/**
	 * Autor: Wellington Rocha Data: 02/08/2011
	 * 
	 * Método para obter o valor dos imposto por categoria
	 * 
	 * Encerrar faturamento do Mês
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idTipoImposto
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorContaCategoriaPorTipoImpostoResumoFaturamento(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idTipoImposto, int idSituacaoAtual, int idSituacaoAnterior)
			throws ErroRepositorioException;

	/**
	 * TODO - COSANPA
	 * 
	 * Método para obter todos os imóveis que já foram processados na
	 * transmissão do arquivo de retorno do IS
	 * 
	 * @author Felipe Santos
	 * @date 22/08/2011
	 * 
	 * @param idRota
	 * @return List<Integer> retorno
	 * @throws ErroRepositorioException
	 */
	public List<Integer> obterImoveisMovimentoContaPF(Integer idRota,
			Integer anoMesFaturamento) throws ErroRepositorioException;

	/**
	 * TODO - COSANPA
	 * 
	 * Método para obter todos os imóveis que faltam ser transmitidos na
	 * transmissão do arquivo de retorno do IS
	 * 
	 * @author Felipe Santos
	 * @date 24/08/2011
	 * 
	 * @param idRota
	 * @return List<Integer> retorno
	 * @throws ErroRepositorioException
	 */
	public List<Integer> obterImoveisFaltandoTransmitir(Integer idRota,
			Integer anoMesFaturamento) throws ErroRepositorioException;

	/*
	 * TODO: COSANPA Utilizado na geração do bônus social
	 */
	/**
	 * Método verifica se a conta está cancelada
	 * 
	 * Gerar Bônus Social
	 * 
	 * @author Adriana Muniz
	 * @date: 17/06/2011
	 * 
	 * @param idConta
	 * @return boolean
	 * @throws ErroRepositorioException
	 * */
	public boolean verificaContaCancelada(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * TODO : COSANPA Pamela Gatinho - 15/09/2011
	 * 
	 * Gerar dados para o relatorio de contas retidas
	 * 
	 * @param anoMesFaturamento
	 * @param idFaturamentoGrupo
	 * @param tipoRelatorio
	 * @param usuarioLogado
	 * 
	 * @return
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarDadosRelatorioContasRetidas(
			int anoMesReferencia, Integer idFaturamentoGrupo)
			throws ErroRepositorioException;

	/**
	 * TODO : COSANPA Pamela Gatinho - 16/09/2011
	 * 
	 * Gerar dados para o relatorio de medicao do faturamento
	 * 
	 * @param anoMesFaturamento
	 * @param idFaturamentoGrupo
	 * @param idEmpresa
	 * @param tipoRelatorio
	 * @param usuarioLogado
	 * 
	 * @return
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarDadosRelatorioMedicaoFaturamento(
			int anoMesReferencia, Integer idFaturamentoGrupo, Integer idEmpresa)
			throws ErroRepositorioException;

	/**
	 * TODO:COSANPA Data: 11/10/2011 Autor: Adriana Muniz
	 * 
	 * Pesquisa se já registrou na tabela mov_conta_prefaturada por imóvel
	 * e referência
	 * 
	 * Utilizado no processamento das informações enviadas pelo IS.
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 * */
	public Collection pesquisaMovimentoContaPF(Integer idImovel, Integer anoMes)
			throws ErroRepositorioException;

	/**
	 * TODO:COSANPA Data: 11/10/2011 Autor: Adriana Muniz
	 * 
	 * Pesquisa o indicador de retransmissão pelo imóvel e referência
	 * 
	 * Utilizado no processamento das informações enviadas pelo IS.
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisaIndicadorRetransmissaoMovimentoContaPF(
			Integer idImovel, Integer anoMes) throws ErroRepositorioException;

	/**
	 * TODO : COSANPA Data: 25/10/2011 Autor: Pamela Gatinho
	 * 
	 * Metodo para pesquisar todas as contas que foram emitidas mas que serao
	 * impressas somente no fechamento do faturamento do grupo
	 * 
	 * @param grupo
	 * @param anoMesReferencia
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterContasNaoImpressas(FaturamentoGrupo grupo,
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * TODO - COSANPA
	 * 
	 * Método para pesquisar imóveis macro de condominios por rota
	 * 
	 * @author Felipe Santos
	 * @date 26/01/2012
	 * 
	 * @param rota
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImoveisCondominioMacro(Rota rota)
			throws ErroRepositorioException;

	/**
	 * TODO - COSANPA
	 * 
	 * Método para pesquisar os imóveis micro pelo imóvel condominio
	 * 
	 * @author Felipe Santos
	 * @date 26/01/2012
	 * 
	 * @param idImovelCondominio
	 * @return List<Integer> retorno
	 * @throws ErroRepositorioException
	 */
	public List<Integer> pesquisarImoveisCondominioMicro(
			Integer idImovelCondominio) throws ErroRepositorioException;

	/**
	 * TODO : COSANPA
	 * 
	 * @author Pamela Gatinho
	 * @date 24/02/2012
	 * 
	 *       Metodo que obtem o movimento do imóvel lido pelo IS
	 * 
	 * @return MovimentoContaPreFaturada
	 * @param anoMesReferencia
	 * @param idImovel
	 * @throws ErroRepositorioException
	 */
	public MovimentoContaPrefaturada obterMovimentoImovel(Integer idImovel,
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * TODO: COSANPA Mantis 583 - Confirmar Prescrição de Conta
	 * 
	 * @author Wellington Rocha
	 * @date 01/06/2012
	 */
	public Conta pesquisarContaParaPrescricao(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * TODO : COSANPA
	 * 
	 * @author Pamela Gatinho
	 * @date 06/03/2013
	 * 
	 *       Metodo que obtem o extrato de quitação de débitos de um imóvel para
	 *       um determinado ano.
	 * 
	 * @return ExtratoQuitacao
	 * @param anoReferencia
	 * @param idImovel
	 * @throws ErroRepositorioException
	 */
	public ExtratoQuitacao obterExtratoQuitacaoImovel(Integer idImovel,
			Integer anoReferencia) throws ErroRepositorioException;

	/**
	 * TODO:COSANPA autor: Adriana Muniz Data: 16/05/2011 Método para retornar
	 * uma coleção de debitos cobrados do parcelamento
	 * */
	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0002] - Obter dados dos serviços de parcelamento
	 * 
	 * @author Adriana Muniz
	 * @date 13/05/2011
	 * 
	 * @param conta
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitoCobradoDeParcelamentoIS(Conta conta)
			throws ErroRepositorioException;
	
	
	/**TODO: Cosanpa
	 * 
	 * [UC0155] - 
	 * 
	 * Alterar Contabilização de valores arrecadados antes de 31/12/2012
	 * 
	 * @author Wellington Rocha
	 * 
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idOrigemCredito
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCreditoARealizarPorOrigemCreditoDuplicidadeAte122012(
			int anoMesReferenciaContabil, Integer idLocalidade,
			Integer idCategoria, Integer idOrigemCredito,
			Integer idSituacaoAtual, Integer idSituacaoAnterior)
			throws ErroRepositorioException;
	
	/**TODO: COSANPA
	 * 
	 * 
	 * [UC0155] - Encerrar Faturamento do Mês Retorna o valor de crédito a
	 * realizar acumulado, de acordo com o ano/mês de referência contábil, a
	 * situação atual ou anterior e a origem de crédito informados.
	 * 
	 * Alterações para contabilizar em contas diferentes valores arrecadados até 31/12/2012
	 * 
	 * @author Wellington Rocha
	 * 
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idOrigemCredito
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCreditoARealizarPorOrigemCreditoDuplicidade(
			int anoMesReferenciaContabil, Integer idLocalidade,
			Integer idCategoria, Integer idOrigemCredito,
			Integer idSituacaoAtual, Integer idSituacaoAnterior)
			throws ErroRepositorioException;
	
	/**TODO: COSANPA
	 * 
	 * Alteração para contabilizar em contas diferentes valores arrecadados até 31/12/2012
	 * 
	 * [UC0155] - Encerrar Faturamento do Mês
	 * 
	 * @author Wellington Rocha
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsCreditoOrigem
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoPorReferenciaContaDuplicidadeAte122012(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, int idSituacaoAtual,
			int idSituacaoAnterior) throws ErroRepositorioException;
	
	
	/**TODO: COSANPA
	 * 
	 * Alteração para contabilizar em contas diferentes valores arrecadados até 31/12/2012
	 * 
	 * [UC0155] - Encerrar Faturamento do Mês
	 * 
	 * @author Wellington Rocha
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsCreditoOrigem
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoPorReferenciaContaDuplicidade(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, int idSituacaoAtual,
			int idSituacaoAnterior) throws ErroRepositorioException;
	
	
	/**TODO: COSANPA
	 * 
	 * Alteração para contabilizar em contas diferentes valores arrecadados até 31/12/2012
	 * 
	 * [UC0155] - Encerrar Faturamento do Mês
	 * 
	 * @author Wellington Rocha
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsCreditoOrigem
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoDuplicidadeAte201212(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, Integer idSituacaoAtual)
			throws ErroRepositorioException;
	
	
	/**TODO: COSANPA
	 * 
	 * Alteração para contabilizar em contas diferentes valores arrecadados até 31/12/2012
	 * 
	 * [UC0155] - Encerrar Faturamento do Mês
	 * 
	 * @author Wellington Rocha
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsCreditoOrigem
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoDuplicidade(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, Integer idSituacaoAtual)
			throws ErroRepositorioException;
	
	/**TODO: COSANPA
	 * 
	 * Alteração para contabilizar em contas diferentes valores arrecadados até 31/12/2012
	 * 
	 * [UC0155] - Encerrar Faturamento do Mês
	 * 
	 * @author Wellington Rocha
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsCreditoOrigem
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoCanceladoPorRetificacaoDuplicidadeAte201212(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem) throws ErroRepositorioException;
	
	
	/**TODO: COSANPA
	 * 
	 * Alteração para contabilizar em contas diferentes valores arrecadados até 31/12/2012
	 * 
	 * [UC0155] - Encerrar Faturamento do Mês
	 * 
	 * @author Wellington Rocha
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsCreditoOrigem
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoCanceladoPorRetificacaoDuplicidade(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem) throws ErroRepositorioException;
	
	
	/**TODO: COSANPA
	 * 
	 * Alteração para contabilizar em contas diferentes valores arrecadados até 31/12/2012
	 * 
	 * [UC0155] - Encerrar Faturamento do Mês
	 * 
	 * @author Wellington Rocha
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsCreditoOrigem
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoRetificadaDuplicidadeAte201212(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem)
			throws ErroRepositorioException;
	
	
	/**TODO: COSANPA
	 * 
	 * Alteração para contabilizar em contas diferentes valores arrecadados até 31/12/2012
	 * 
	 * [UC0155] - Encerrar Faturamento do Mês
	 * 
	 * @author Wellington Rocha
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idCategoria
	 * @param idsCreditoOrigem
	 * @param idSituacaoAtual
	 * @param idSituacaoAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoRetificadaDuplicidade(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem)
			throws ErroRepositorioException;

	public Object[] pesquisarContasRelatorioBIG(Integer anoMesReferencia,
			Integer idLocalidade) throws ErroRepositorioException;

	public Object[] pesquisarErrosContasRelatorioBIG(
			Integer anoMesReferencia, Date dataInicial, Date dataFinal,
			Integer idLocalidade) throws ErroRepositorioException;

	public Object[] pesquisarInadimplenciaVencidasRelatorioBIG(
			Date dataReferencia, Date dataInicial, Date dataFinal,
			Integer idLocalidade) throws ErroRepositorioException;

	public Object[] pesquisarInadimplenciaEmitidasRelatorioBIG(Date dataInicial,
			Date dataFinal, Integer idLocalidade)
			throws ErroRepositorioException;

	public Object[] pesquisarInadimplenciaVencidasMaior90RelatorioBIG(
			Date dataReferencia, Date dataFinal, Integer idLocalidade)
			throws ErroRepositorioException;

	public Object[] pesquisarInadimplenciaEmitidasMaior90RelatorioBIG(Date dataFinal,
			Integer idLocalidade) throws ErroRepositorioException;
	
}