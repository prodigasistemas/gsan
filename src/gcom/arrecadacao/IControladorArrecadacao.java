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
package gcom.arrecadacao;

import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.aviso.AvisoDeducoes;
import gcom.arrecadacao.aviso.bean.AcertosAvisoBancarioHelper;
import gcom.arrecadacao.aviso.bean.AvisoBancarioHelper;
import gcom.arrecadacao.aviso.bean.DeducoesHelper;
import gcom.arrecadacao.aviso.bean.PagamentosDevolucoesHelper;
import gcom.arrecadacao.aviso.bean.ValoresArrecadacaoDevolucaoAvisoBancarioHelper;
import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.arrecadacao.bean.ArrecadadorMovimentoItemHelper;
import gcom.arrecadacao.bean.ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper;
import gcom.arrecadacao.bean.DadosConteudoRegistroMovimentoArrecadadorHelper;
import gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper;
import gcom.arrecadacao.bean.InformarAcertoDocumentosNaoAceitosHelper;
import gcom.arrecadacao.bean.MovimentoArrecadadoresPorNSAHelper;
import gcom.arrecadacao.bean.PagamentoHelperCodigoBarras;
import gcom.arrecadacao.bean.PesquisarAnaliseArrecadacaoHelper;
import gcom.arrecadacao.bean.PesquisarAnaliseAvisosBancariosHelper;
import gcom.arrecadacao.bean.PesquisarAvisoBancarioPorContaCorrenteHelper;
import gcom.arrecadacao.bean.RegistroHelperCodigo0;
import gcom.arrecadacao.bean.RegistroHelperCodigoA;
import gcom.arrecadacao.bean.RegistroHelperCodigoBarras;
import gcom.arrecadacao.bean.RegistroHelperFichaCompensacao;
import gcom.arrecadacao.debitoautomatico.DebitoAutomaticoMovimento;
import gcom.arrecadacao.pagamento.FiltroPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaDocumentoItem;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.bean.DebitoACobrarValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoTipo;
import gcom.micromedicao.bean.ConsultarArquivoTextoRoteiroEmpresaHelper;
import gcom.relatorio.arrecadacao.GuiaDevolucaoRelatorioHelper;
import gcom.relatorio.arrecadacao.RelatorioAnaliseArrecadacaoBean;
import gcom.relatorio.arrecadacao.RelatorioAnaliseAvisosBancariosBean;
import gcom.relatorio.arrecadacao.RelatorioAnalisePagamentoCartaoDebitoBean;
import gcom.relatorio.arrecadacao.RelatorioAvisoBancarioPorContaCorrenteBean;
import gcom.relatorio.arrecadacao.RelatorioComparativoFatArrecExpurgoBean;
import gcom.relatorio.arrecadacao.RelatorioDocumentoNaoAceitosBean;
import gcom.relatorio.arrecadacao.RelatorioTranferenciaPagamentoBean;
import gcom.relatorio.arrecadacao.pagamento.GuiaPagamentoRelatorioHelper;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.ParametroNaoInformadoException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Interface Controlador Arrecadacao PADRÃO
 * 
 * @author Raphael Rossiter
 * @date 30/04/2007
 */
public interface IControladorArrecadacao {

	/**
	 * registra movimento dos arrecadadores no sistema
	 * 
	 */
	public Collection registrarMovimentoArrecadadores(
			StringBuilder stringBuilderTxt, Short codigoArrecadador,
			String nomeArrecadador, String idTipoMovimento,
			int quantidadeRegistros, Usuario usuario, Integer idArrecadador,
			ArrecadadorContrato arrecadadorContrato)
			throws ControladorException;

	/**
	 * Coisa de Rafael Corrêa tem que comentar
	 * 
	 * @author Administrador
	 * @date 16/03/2006
	 * 
	 * @param codigoAgente
	 * @param dataLancamento
	 * @return
	 * @throws ControladorException
	 */
	public Double pesquisarDeducoesAvisoBancario(String codigoAgente,
			Date dataLancamento, String numeroSequencial)
			throws ControladorException;

	/**
	 * 
	 * [UC0235] Inserir Aviso Bancário [FS0003] Verificar existência de avisos
	 * bancários não realizados [FS0004] Verificar seleção de aviso Retorna o
	 * valor do maior número sequencial do arrecadador selecionado
	 * 
	 * @author Rafael Corrêa
	 * @date 24/03/2006
	 * 
	 * @param dataLancamento
	 * @return
	 * @throws ControladorException
	 */
	public Short pesquisarValorMaximoNumeroSequencial(Date dataLancamento,
			String idArrecadador) throws ControladorException;

	/**
	 * Este caso de uso cria um filtro do movimento dos arrecadadores
	 * 
	 * [UC0263] - Filtrar Movimento dos Arrecadadores
	 * 
	 * @author Raphael Rossiter
	 * @date 23/02/2006
	 * 
	 * @param filtroArrecadadorMovimento
	 * @param movimentoOcorrencia
	 * @param movimentoAceito
	 * @param movimentoAbertoFechado
	 * @return Uma coleçao com os movimentos selecionados
	 * @throws ControladorException
	 */
	public FiltroArrecadadorMovimento filtrarMovimentoArrecadadores(
			FiltroArrecadadorMovimento filtroArrecadadorMovimento,
			String movimentoOcorrencia, String movimentoAceito,
			String movimentoAbertoFechado) throws ControladorException;

	/**
	 * Obtém o número de registros em ocorrência de um determinado movimento
	 * (número de linhas da tabela ARRECADADOR_MOVIMENTO_ITEM com ARMV_ID =
	 * ARMV_ID da tabela ARRECADADOR_MOVIMENTO e AMIT_DSOCORRENCIA diferente de
	 * "OK")
	 * 
	 * @author Raphael Rossiter
	 * @date 08/03/2006
	 * 
	 * @param arrecadadorMovimento
	 * @return Um integer que representa a quantidade de registros selecionados
	 * @throws ControladorException
	 */
	public Integer obterNumeroRegistrosEmOcorrenciaPorMovimentoArrecadadores(
			ArrecadadorMovimento arrecadadorMovimento,
			String descricaoOcorrencia) throws ControladorException;

	/**
	 * Obtém o número de registros que não foram aceitos de um determinado
	 * movimento (número de linhas da tabela ARRECADADOR_MOVIMENTO_ITEM com
	 * ARMV_ID = ARMV_ID da tabela ARRECADADOR_MOVIMENTO e AMIT_ICACEITACAO
	 * igual a 2 (NÃO))
	 * 
	 * @author Raphael Rossiter
	 * @date 08/03/2006
	 * 
	 * @param arrecadadorMovimento
	 * @return Um integer que representa a quantidade de registros selecionados
	 * @throws ControladorException
	 */
	public Integer obterNumeroRegistrosNaoAceitosPorMovimentoArrecadadores(
			ArrecadadorMovimento arrecadadorMovimento, Short indicadorAceitacao)
			throws ControladorException;

	/**
	 * Obtém o valor total dos avisos bancários de um determinado movimento
	 * (Total da soma do campo AVBC_VALORARRECADACAO da tabela AVISO_BANCARIO
	 * com ARMV_ID = ARMV_ID da tabela ARRECADADOR_MOVIMENTO
	 * 
	 * @author Raphael Rossiter
	 * @date 08/03/2006
	 * 
	 * @param arrecadadorMovimento
	 * @return Um BigDecimal que representa o total da soma dos avisos bancários
	 * @throws ControladorException
	 */
	public BigDecimal obterTotalArrecadacaoAvisoBancarioPorMovimentoArrecadadores(
			ArrecadadorMovimento arrecadadorMovimento)
			throws ControladorException;

	/**
	 * Caso o valor total do movimento (ARMV_VALORTOTALMOVIMENTO) seja diferente
	 * do valor da soma das arrecadações dos avisos bancários relacionados
	 * (ARMV_ID = ARMV_ID da tabela AVISO_BANCARIO e o campo para totalização
	 * será AVBC_VLARRECADACAO), a situação do movimento será "ABERTO". Caso
	 * contrário a situação do movimento será "FECHADO"
	 * 
	 * @author Raphael Rossiter
	 * @date 08/03/2006
	 * 
	 * @param arrecadadorMovimento
	 * @return Uma String que representa a situação do movimento
	 * @throws ControladorException
	 */
	public String obterSituacaoArrecadadorMovimento(
			ArrecadadorMovimento arrecadadorMovimento)
			throws ControladorException;

	/**
	 * Lista os avisos bancários associados ao movimento com os seguintes dados:
	 * Data do Lançamento Sequencial do Aviso Tipo do Aviso Data do Crédito
	 * Valor do Crédito Valor da Arrecadação Valor Total dos pagamentos
	 * associados ao aviso Situação do Aviso
	 * 
	 * @author Raphael Rossiter
	 * @date 08/03/2006
	 * 
	 * @param arrecadadorMovimento
	 * @return Uma Collection<AvisoBancarioHelper> que representa a os avisos
	 *         bancários selecionados
	 * @throws ControladorException
	 */
	public Collection<AvisoBancarioHelper> obterColecaoAvisosBancariosPorArrecadadorMovimento(
			ArrecadadorMovimento arrecadadorMovimento)
			throws ControladorException;

	/**
	 * Caso o valor total dos pagamentos seja igual ao valor da arrecadação
	 * (AVBC_VLARRECADACAO) e o valor total das devoluções seja igual ao valor
	 * da devolução (AVBC_VLDEVOLUCAO) a situação será "FECHADO"; Caso contrário
	 * será "ABERTO"
	 * 
	 * @author Raphael Rossiter
	 * @date 09/03/2006
	 * 
	 * @param avisoBancario
	 * @return Uma String que representa a situação do aviso
	 * @throws ControladorException
	 */
	public String obterSituacaoAvisoBancarioParaArrecadadorMovimento(
			AvisoBancario avisoBancario) throws ControladorException;

	public void verificarExistenciaContaParaAvisoBancario(String idArrecadador,
			String idConta) throws ControladorException;

	/**
	 * Método que atualiza o aviso bancario, adiciona as deducoes e acertos
	 * novas e remove as deducoes e os acertos que forma para remover
	 * 
	 * @author thiago
	 * @date 14/03/2006
	 * 
	 * @param avisoBancario
	 * @param duducoes
	 * @param deducoesParaRemover
	 * @param acertos
	 * @param acertosParaRemover
	 */
	public void atualizarAvisoBancario(AvisoBancario avisoBancario,
			Collection duducoes, Collection deducoesParaRemover,
			Collection acertos, Collection acertosParaRemover,
			Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * Faz a pesquisa de devolução fazendo os carregamentos de clienteContas,
	 * clienteImoveis, clientesGuiaPagamento
	 * 
	 * @author Rafael Corrêa
	 * @date
	 * 
	 * @param FiltroDevolucao
	 * @return Collection<Devolucao>
	 * @throws ControladorException
	 */
	public Collection<Devolucao> pesquisarDevolucao(
			FiltroDevolucao filtroDevolucao) throws ControladorException;

	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Imóvel pesquisarPagamentoImovel
	 * 
	 * @author Tiago Moreno, Roberta Costa
	 * @date 12/06/06
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection<Pagamento> pesquisarPagamentoImovel(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos, String valorPagamentoInicial, 
            String valorPagamentoFinal ) throws ControladorException;

	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * para pesquisar os pagamento historicos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos historicos do Imóvel
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoImovel(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ControladorException;

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * <Breve descrição sobre o subfluxo>
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * <Breve descrição sobre o fluxo secundário>
	 * 
	 * <Identificador e nome do fluxo secundário>
	 * 
	 * @author Administrador
	 * @date 16/03/2006
	 * 
	 * @param codigoBarras
	 * @param dataPagamento
	 * @param idFormaPagamento
	 * @param sistemaParametro
	 * @param idArrecadadorMovimentoItem
	 * @return
	 * @throws ControladorException
	 */
	public PagamentoHelperCodigoBarras processarPagamentosCodigoBarras(
			String codigoBarras, Date dataPagamento, Integer idFormaPagamento,
			SistemaParametro sistemaParametro, Usuario usuarioLogado) throws ControladorException;

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * <Breve descrição sobre o subfluxo>
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * <Breve descrição sobre o fluxo secundário>
	 * 
	 * <Identificador e nome do fluxo secundário>
	 * 
	 * @author Administrador
	 * @date 17/03/2006
	 * 
	 * @param codigoBarras
	 * @return
	 */
	public RegistroHelperCodigoBarras distribuirDadosCodigoBarras(
			String codigoBarras) throws ControladorException;

	/**
	 * [UC0239] Filtrar Aviso Bancário
	 * 
	 * Validar Filtrar Aviso Bancário
	 * 
	 * @author Vivianne Sousa
	 * @date 18/03/2006
	 * 
	 * @param dataLancamentoInicio
	 * @param dataLancamentoFim
	 * @param periodoArrecadacaoInicio
	 * @param periodoArrecadacaoFim
	 * @param dataPrevisaoCreditoDebitoInicio
	 * @param dataPrevisaoCreditoDebitoFim
	 * @param intervaloValorPrevistoInicio
	 * @param intervaloValorPrevistoFim
	 * @param dataRealizacaoCreditoDebitoInicio
	 * @param dataRealizacaoCreditoDebitoFim
	 * @param intervaloValorRealizadoInicio
	 * @param intervaloValorRealizadoFim
	 * 
	 * return void
	 * @throws ControladorException
	 */
	public void validacaoFinal(Date dataLancamentoInicio,
			Date dataLancamentoFim, Integer periodoArrecadacaoInicio,
			Integer periodoArrecadacaoFim,
			Date dataPrevisaoCreditoDebitoInicio,
			Date dataPrevisaoCreditoDebitoFim,
			BigDecimal intervaloValorPrevistoInicio,
			BigDecimal intervaloValorPrevistoFim,
			Date dataRealizacaoCreditoDebitoInicio,
			Date dataRealizacaoCreditoDebitoFim,
			BigDecimal intervaloValorRealizadoInicio,
			BigDecimal intervaloValorRealizadoFim) throws ControladorException;

	/**
	 * [UC0239] Filtrar Aviso Bancário
	 * 
	 * Filtrar Aviso Bancário de acordo com a opção Aberto / Fechado
	 * 
	 * @author Vivianne Sousa
	 * @date 18/03/2006
	 * 
	 * @param collectionAvisoBancario
	 * @param indicadorAbertoFechado
	 * @param tipoAviso
	 * 
	 * return collection<AvisoBancario>
	 * @throws ControladorException
	 */
	public Collection<AvisoBancarioHelper> filtrarAvisoBancarioAbertoFechado(
			Collection collectionAvisoBancario, String indicadorAbertoFechado,
			String tipoAviso) throws ControladorException;

	/**
	 * Método que recebe um array de Integer e remove os Avisos Bancarios dos
	 * ids passado, caso exista um Aviso Bancario que tenha um relacionamento
	 * com outra tabela entao nao remove nenhum. Outra tabela fora aviso_deducao
	 * e aviso_acerto
	 * 
	 * 
	 * @author Thiago Toscano
	 * @date 20/03/2006
	 * 
	 * @param ids
	 * @throws ControladorException
	 */
	public void removerAvisosBancarios(Integer[] ids,
			OperacaoEfetuada operacaoEfetuada,
			Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper)
			throws ControladorException;

	/**
	 * [UC0270] Apresentar Análise do Movimento dos Arrecadadores
	 * 
	 * O sistema seleciona os itens do movimento do arrecadador com os seguintes
	 * dados: 1 - Código do Registro 2 - Identificação do Imóvel/Cliente 3 -
	 * Ocorrência 4 - Indicador de Aceitação 5 - Descrição do Indicador de
	 * Aceitação
	 * 
	 * [SF0001] Consultar os Itens do Movimento do Arrecadador
	 * 
	 * @author Raphael Rossiter
	 * @data 20/03/2006
	 * 
	 * @param arrecadadorMovimento
	 * @return Collection<ArrecadadorMovimentoItemHelper>
	 */
	public Collection<ArrecadadorMovimentoItemHelper> consultarItensMovimentoArrecadador(
			ArrecadadorMovimento arrecadadorMovimento, Integer idImovel,
			Short indicadorAceitacao, String descricaoOcorrencia)
			throws ControladorException;

	/**
	 * [UC0270] Apresentar Análise do Movimento dos Arrecadadores
	 * 
	 * O sistema seleciona os itens do movimento do arrecadador com os seguintes
	 * dados: 1 - Código do Registro 2 - Identificação do Imóvel/Cliente 3 -
	 * Ocorrência 4 - Indicador de Aceitação 5 - Descrição do Indicador de
	 * Aceitação
	 * 
	 * [SF0001] Consultar os Itens do Movimento do Arrecadador
	 * 
	 * @author Raphael Rossiter, Kassia Albuquerque
	 * @data 20/03/2006, 24/08/2007
	 * 
	 * @param arrecadadorMovimento
	 * @return Collection<ArrecadadorMovimentoItemHelper>
	 */
	public Collection<ArrecadadorMovimentoItemHelper> consultarItensMovimentoArrecadador(
			ArrecadadorMovimento arrecadadorMovimento, Integer idImovel,
			Short indicadorAceitacao, String descricaoOcorrencia,String codigoArrecadacaoForma,
			Short indicadorDiferencaValorMovimentoValorPagamento)
			throws ControladorException;
	/**
	 * [UC0270] Apresentar Análise do Movimento dos Arrecadadores
	 * 
	 * O sistema captura os dados referentes ao conteúdo do registro de
	 * Movimento do arrecadador
	 * 
	 * [SF0002] Apresentar Dados do Conteúdo do Registro de Movimento do
	 * Arrecadador
	 * 
	 * @author Raphael Rossiter
	 * @data 21/03/2006
	 * 
	 * @param arrecadadorMovimentoItem
	 * @return DadosConteudoRegistroMovimentoArrecadador
	 */
	public DadosConteudoRegistroMovimentoArrecadadorHelper apresentarDadosConteudoRegistroMovimentoArrecadador(
			ArrecadadorMovimentoItem arrecadadorMovimentoItem)
			throws ControladorException;

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * <Breve descrição sobre o subfluxo>
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * <Breve descrição sobre o fluxo secundário>
	 * 
	 * <Identificador e nome do fluxo secundário>
	 * 
	 * @author Pedro Alexandre
	 * @date 22/03/2006
	 * 
	 * @param idsPagamentos
	 * @throws ControladorException
	 */
	public void removerPagamentos(String[] idsPagamentos, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * Este caso de uso apresenta a análise do aviso bancário e os
	 * pagamentos/devoluções associados.
	 * 
	 * [UC0267] - Apresentar Análise do Aviso Bancário
	 * 
	 * @author Raphael Rossiter
	 * @date 23/03/2006
	 * 
	 * @param avisoBancario
	 * @return AvisoBancarioHelper
	 */
	public AvisoBancarioHelper apresentarAnaliseAvisoBancario(
			AvisoBancario avisoBancario) throws ControladorException;

	/**
	 * Inseri uma coleção de pagamentos no sistema
	 * 
	 * [UC0265] Inserir Pagamentos
	 * 
	 * Pesquisa o débito a cobrar do imóvel informado pelo usuário
	 * 
	 * [FS0024] - Verificar existência do débito a cobrar
	 * 
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 * 
	 * @param idImovel
	 * @param idDebitoACobrar
	 * @return
	 * @throws ControladorException
	 */
	public DebitoACobrar pesquisarDebitoACobrarDigitado(String idImovel,
			String idDebitoACobrar) throws ControladorException;

	/**
	 * Inseri uma coleção de pagamentos no sistema
	 * 
	 * [UC0265] Inserir Pagamentos
	 * 
	 * Pesquisa a guia de pagamento do imóvel informado pelo usuário
	 * 
	 * [FS0022] - Verificar existência da guia de pagamento
	 * 
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 * 
	 * @param idImovel
	 * @param idCliente
	 * @param idGuiaPagamento
	 * @return
	 * @throws ControladorException
	 */
	public GuiaPagamento pesquisarGuiaPagamentoDigitada(String idImovel,
			String idCliente, String idGuiaPagamento)
			throws ControladorException;

	/**
	 * Inseri uma coleção de pagamentos no sistema
	 * 
	 * [UC0265] Inserir Pagamentos
	 * 
	 * Verifica se o usuário informou o código da guia de pagamento e o tipo de
	 * débito, só pode ser informado um dos dois
	 * 
	 * [FS0021] Verificar preenchimento da guia de pagamento e do tipo de débito
	 * 
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 * 
	 * @param idGuiaPagamento
	 * @param idTipoDebito
	 * @throws ControladorException
	 */
	public void verificarPreeenchimentoGuiaPagamentoETipoDebito(
			String idGuiaPagamento, String idTipoDebito)
			throws ControladorException;

	/**
	 * Inseri uma coleção de pagamentos no sistema
	 * 
	 * [UC0265] Inserir Pagamentos
	 * 
	 * Verifica se o usuário informou o código do débito a cobrar e o tipo de
	 * débito, só pode ser informado um dos dois
	 * 
	 * [FS0023] Verificar preenchimento do débito a cobrar e do tipo de débito
	 * 
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 * 
	 * @param idDebitoACobrar
	 * @param idTipoDebito
	 * @throws ControladorException
	 */
	public void verificarPreeenchimentoDebitoACobrarETipoDebito(
			String idDebitoACobrar, String idTipoDebito)
			throws ControladorException;

	/**
	 * Inseri uma coleção de pagamentos no sistema
	 * 
	 * [UC0265] Inserir Pagamentos
	 * 
	 * Verifica se a localidade informada é a mesma da guia de pagamento
	 * 
	 * [FS0014] Verificar localidade da guia de pagamento
	 * 
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 * 
	 * @param guiaPagamento
	 * @param idLocalidade
	 * @throws ControladorException
	 */
	public void verificarLocalidadeGuiaPagamento(GuiaPagamento guiaPagamento,
			String idLocalidade) throws ControladorException;

	/**
	 * Inseri uma coleção de pagamentos no sistema
	 * 
	 * [UC0265] Inserir Pagamentos
	 * 
	 * Verifica se a localidade informada é a mesma do débito a cobrar
	 * 
	 * [FS0017] Verificar localidade do débito a cobrar
	 * 
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 * 
	 * @param debitoACobrar
	 * @param idLocalidade
	 * @throws ControladorException
	 */
	public void verificarLocalidadeDebitoACobrar(DebitoACobrar debitoACobrar,
			String idLocalidade) throws ControladorException;

	/**
	 * Inseri uma coleção de pagamentos no sistema
	 * 
	 * [UC0265] Inserir Pagamentos
	 * 
	 * Verifica a existência de débito a cobrar com o tipo de débito e o imóvel
	 * informados
	 * 
	 * [FS0016] Verificar existência de débito a cobrar com tipo de débito
	 * informado
	 * 
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 * 
	 * @param tipoDebito
	 * @param idImovel
	 * @return
	 * @throws ControladorException
	 */
	public DebitoACobrar verificarExistenciaDebitoACobrarComTipoDebito(
			DebitoTipo tipoDebito, String idImovel, BigDecimal valorInformado) throws ControladorException;

	/**
	 * Inseri uma coleção de pagamentos no sistema
	 * 
	 * [UC0265] Inserir Pagamentos
	 * 
	 * Verifica a existência de guia de pagamento com o tipo de débito e o
	 * imóvel informados
	 * 
	 * [FS0013] Verificar existência de guia de pagamento com tipo de débito
	 * informado
	 * 
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 * 
	 * @param tipoDebito
	 * @param idImovel
	 * @param idCliente
	 * @return
	 * @throws ControladorException
	 */
	public GuiaPagamento verificarExistenciaGuiaPagamentoComTipoDebito(
			DebitoTipo tipoDebito, String idImovel, String idCliente)
			throws ControladorException;

	/**
	 * Responsável pela manutenção das informações de pagamento
	 * 
	 * [UC0266] Manter Pagamentos
	 * 
	 * Atualiza um pagamento no sistema, verificando se a atualização já foi
	 * executada por outro usuário
	 * 
	 * [SB0001] Atualizar Pagamento
	 * 
	 * @author Pedro Alexandre
	 * @date 25/03/2006
	 * 
	 * @param pagamento
	 * @throws ControladorException
	 */
	public void atualizarPagamento(Pagamento pagamento)
			throws ControladorException;

	/**
	 * Insere os aviso deduções no aviso bancário
	 * 
	 * [UC0000] Inserir Aviso Bancário
	 * 
	 * @author Rafael Corrêa
	 * @date 18/04/2006
	 * 
	 * @throws ControladorException
	 */
	public void inserirAvisosDeducoes(AvisoDeducoes avisoDeducoes,
			AvisoBancario avisoBancario) throws ControladorException;

	/**
	 * Gera os dados diários da arrecadação acumulando a quantidade e o valor
	 * dos pagamentos
	 * 
	 * [UC0301] Gerar Dados Diários da Arrecadação
	 * 
	 * @author Pedro Alexandre
	 * @date 11/04/2006
	 * 
	 * @throws ControladorException
	 */
	public void gerarDadosDiariosArrecadacao(int idFuncionalidadeIniciada,
			Collection<Integer> colecaoLocalidades) throws ControladorException;

	/**
	 * [UC0319] Gerar Movimento de Débito Automático para o banco
	 * 
	 * pesquisa todos os bancos que tenham contrato vigente para arrecadador
	 * contas com forma de arrecadação correspondente a debito automático
	 * 
	 * [SB0002] - Carregar Lista de Bancos
	 * 
	 * @author Sávio Luiz
	 * @date 18/04/2006
	 * 
	 * @return Coleção de Bancos
	 * @throws ControladorException
	 */

	/*
	 * public Collection<Banco> pesquisaBancosDebitoAutomatico() throws
	 * ControladorException;
	 */

	/**
	 * [UC0319] Gerar Movimento de Débito Automático para o banco
	 * 
	 * pesquisa os movimentos de débito automático para o banco,referentes ao
	 * grupo e ano/mês de faturamento informados
	 * 
	 * [SB0002] - Carregar Lista de Bancos
	 * 
	 * @author Sávio Luiz
	 * @date 18/04/2006
	 * 
	 * @param idFaturamentoGrupo,anoMesReferenciaFaturamento,idBanco
	 * @return Coleção de DebitoAutomaticoMovimento
	 * @throws ControladorException
	 */

	public Map<Banco, Collection<DebitoAutomaticoMovimento>> pesquisaDebitoAutomaticoMovimento(
			Collection colecaoIdsFaturamentoGrupo, Integer anoMesReferenciaFaturamento)
			throws ControladorException;

	/**
	 * [UC0319] Filtrar Aviso Bancario
	 * 
	 * 
	 * @author Vivianne Sousa
	 * @date 20/04/2006
	 * 
	 * @param avisoBancarioHelper
	 * @return Coleção de DebitoAutomaticoMovimento
	 * @throws ErroRepositorioException
	 */

	public Collection filtrarAvisoBancarioAbertoFechado(
			AvisoBancarioHelper avisoBancarioHelper)
			throws ControladorException;

	/**
	 * Este caso de uso permite classificar os pagamentos e as devoluções no mês
	 * de arrecadação corrente
	 * 
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @date 18/04/2006, 06/12/2006
	 * 
	 * @param
	 * @return void
	 */
	public void classificarPagamentosDevolucoes(
			Collection<Integer> colecaoLocalidades, int idFuncionalidadeIniciada)
			throws ControladorException;

	/**
	 * [UC0319] Gerar Movimento de Débito Automático para o banco
	 * 
	 * Movimento de débito automático em arquivo TXT gerado e enviado ao banco.
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 20/04/2006
	 * 
	 * @param idFaturamentoGrupo,anoMesReferenciaFaturamento,idBanco
	 * @return Collection<GerarMovimentoDebitoAutomaticoBancoHelper>
	 * @throws ControladorException
	 */

	public void gerarMovimentoDebitoAutomaticoBanco(
			Map<Banco, Collection<DebitoAutomaticoMovimento>> debitosAutomaticoBancosMap,
			Usuario usuario) throws ControladorException;

	/**
	 * [UC0319] Gerar Movimento de Débito Automático para o banco
	 * 
	 * Cria uma linha de 150 posições com o registro tipo E.
	 * 
	 * 
	 * [SB0003] - Regerar arquivo TXT para um movimento de débito automático
	 * gerado anteriormente
	 * 
	 * @author Sávio Luiz
	 * @date 25/04/2006
	 * 
	 * @param arrecadadorMovimento
	 * @return Object[]
	 * @throws ControladorException
	 */
	public void regerarArquivoTxtMovimentoDebitoAutomatico(
			ArrecadadorMovimento arrecadadorMovimento, String envioBanco,
			Usuario usuario) throws ControladorException;

	/**
	 * [UC0322] - Inserir Guia de Devolução
	 * 
	 * Insere uma Guia de Devolução
	 * 
	 * @author Rafael Corrêa, Pedro Alexandre
	 * @date 02/05/2006, 21/11/2006
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer inserirGuiaDevolucao(GuiaDevolucao guiaDevolucao,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * Atualizar Devolucao
	 * 
	 * @author Fernanda Paiva
	 * @created 03/05/2006
	 * 
	 * @param valor
	 *            arrecadacao
	 * 
	 * @exception controladorException
	 *                controlador Exception
	 */
	public void atualizaValorArrecadacaoAvisoBancaraio(BigDecimal valor,
			Integer codigoAvisoBancario) throws ControladorException;

	/**
	 * 
	 * Faz a pesquisa de guia de devolução para o relatório fazendo os
	 * carregamentos de clienteContas, clienteImoveis, clientesGuiaPagamento
	 * 
	 * @author Rafael Corrêa
	 * @date 11/09/2006
	 * 
	 * @param FiltroGuiaDevolucao
	 * @return Collection<GuiaDevolucao>
	 * @throws ControladorException
	 */
	public Collection<GuiaDevolucao> pesquisarGuiaDevolucaoRelatorio(
			FiltroGuiaDevolucao filtroGuiaDevolucao)
			throws ControladorException;

	/**
	 * [UC0324] - Filtrar Guia de Devolucao
	 * 
	 * [SF0001] - Seleciona Guias de Devolução do Cliente
	 * 
	 * Faz a pesquisa de guia de devolução fazendo os carregamentos de
	 * clienteContas, clienteImoveis, clientesGuiaPagamento
	 * 
	 * @author Rafael Corrêa
	 * @date
	 * 
	 * @param FiltroGuiaDevolucao
	 * @return Collection<GuiaDevolucao>
	 * @throws ControladorException
	 */
	public Collection<GuiaDevolucao> pesquisarGuiaDevolucao(
			FiltroGuiaDevolucao filtroGuiaDevolucao, Integer numeroPagina)
			throws ControladorException;

	/**
	 * [UC0324] - Filtrar Guia de Devolucao
	 * 
	 * [SF0001] - Seleciona Guias de Devolução do Cliente
	 * 
	 * Faz a pesquisa de guia de devolução fazendo os carregamentos de
	 * clienteContas, clienteImoveis, clientesGuiaPagamento
	 * 
	 * @author Rafael Corrêa
	 * @date
	 * 
	 * @param FiltroGuiaDevolucao
	 * @return Collection<GuiaDevolucao>
	 * @throws ControladorException
	 */
	public Integer pesquisarGuiaDevolucaoCount(
			FiltroGuiaDevolucao filtroGuiaDevolucao)
			throws ControladorException;

	/**
	 * [UC0266] Manter Guia de Devolução
	 * 
	 * [SB0001] - Atualizar Guia de Devolução
	 * 
	 * Atualiza uma Guia de Devolução e as Devoluções associadas a ela
	 * 
	 * @author Rafael Corrêa
	 * @date 10/05/2006
	 * 
	 * @throws ControladorException
	 */
	public void atualizarGuiaDevolucao(GuiaDevolucao guiaDevolucao ,Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * [UC0339] - Consultar Dados Diarios da Arrecadacao
	 * 
	 * [SB0007] - Apresentar Dados Diarios da Arrecadacao por Categoria
	 * 
	 * Acumula os dados de uma Colecao de Dados Diarios da Arrecadação
	 * 
	 * @author Fernanda Paiva
	 * @date 24/05/2006
	 * 
	 * @throws ControladorException
	 */
	public void acumularDadosArrecadacao(
			Collection colecaoCategoriaPorArrecadacaoDadosDiarios,
			ArrecadacaoDadosDiarios arrecadacaoDadosDiarios, int indicador,
			String idElo, String idGerencia, String idLocalidade)
			throws ControladorException;

	/**
	 * Consulta ResumoArrecadacao para a geração do relatório '[UC0345] Gerar
	 * Relatório de Resumo do Arrecadacao' de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa, Diogo Peixoto
	 * @created 24/05/2006, 20/04/2011
	 * 
	 * @param opcaoTotalizacao
	 * @param mesAnoReferencia
	 * @param gerenciaRegional
	 * @param localidade
	 * @param unidadeNegocio
	 * @param municipio
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection consultarResumoArrecadacaoRelatorio(String opcaoTotalizacao, int mesAnoReferencia,
			Integer gerenciaRegional, Integer localidade, Integer unidadeNegocio, Integer municipio)
			throws ControladorException;
	
	/**
	 * Consulta a qtde de registros ResumoArrecadacao para a geração do
	 * relatório '[UC0345] Gerar Relatório de Resumo do Arrecadacao' de acordo
	 * com a opção de totalização.
	 * 
	 * @author Vivianne Sousa, Diogo Peixoto
	 * @created 02/06/2006, 20/04/2011
	 * 
	 * @param opcaoTotalizacao
	 * @param mesAnoReferencia
	 * @param gerenciaRegional
	 * @param localidade
	 * @param municipio
	 * 
	 * @return Quantidade de registros do relatório
	 * @throws ControladorException
	 */
	public Integer consultarQtdeRegistrosResumoArrecadacaoRelatorio(
			String opcaoTotalizacao, int mesAnoReferencia,
			Integer gerenciaRegional, Integer localidade, Integer municipio)
			throws ControladorException;

	/**
	 * Encerra a arrecadação do ano/mês atual
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @throws ControladorException
	 */
	public void encerrarArrecadacaoMes(
			Collection<Integer> colecaoIdsLocalidades,
			int idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Imóvel pesquisarPagamentoImovel
	 * 
	 * @author Roberta Costa
	 * @date 12/06/06
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection<Pagamento> pesquisarPagamentoClienteConta(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ControladorException;

	/**
	 * Filtra os Pagamento Historicos do Cliente Conta
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoClienteConta(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ControladorException;

	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Imóvel pesquisarPagamentoImovel
	 * 
	 * @author Roberta Costa
	 * @date 12/06/06
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection<Pagamento> pesquisarPagamentoClienteDebitoACobrar(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ControladorException;

	/**
	 * Filtra os pagamentos historicos do debito a cobrar
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 12/06/06,06/10/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoClienteDebitoACobrar(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ControladorException;

	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Imóvel pesquisarPagamentoImovel
	 * 
	 * @author Roberta Costa
	 * @date 12/06/06
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection<Pagamento> pesquisarPagamentoClienteGuiaPagamento(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ControladorException;

	/**
	 * Filtrar os pagamentos historicos do Cliente Guia Pagamento
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/06
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoClienteGuiaPagamento(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ControladorException;

	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Imóvel pesquisarPagamentoImovel
	 * 
	 * @author Roberta Costa
	 * @date 12/06/06
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection<Pagamento> pesquisarPagamentoLocalidade(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos, Integer numeroPagina,
            String valorPagamentoInicial, 
            String valorPagamentoFinal)
			throws ControladorException;

	/**
	 * Filtra os Pagamento Historicos da Localidade
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoLocalidade(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			Integer numeroPagina) throws ControladorException;

	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Imóvel pesquisarPagamentoImovel
	 * 
	 * @author Roberta Costa
	 * @date 12/06/06
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection<Pagamento> pesquisarPagamentoAvisoBancario(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial, 
            String valorPagamentoFinal)
			throws ControladorException;

	/**
	 * Filtra oas pagamento historicos do Aviso Bancario
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoAvisoBancario(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ControladorException;

	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Imóvel pesquisarPagamentoImovel
	 * 
	 * @author Roberta Costa
	 * @date 12/06/06
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection<Pagamento> pesquisarPagamentoMovimentoArrecadador(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial, 
            String valorPagamentoFinal)
			throws ControladorException;

	/**
	 * Filtrar pagamentos historicos do movimento arrecador
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoMovimentoArrecadador(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ControladorException;

	/**
	 * obtem colecao com dados diarios da arrecadacao
	 * 
	 * @author Fernanda Paiva
	 * @date 09/06/2006
	 * 
	 * @param anoMesReferencia
	 *            id descricao - para informar por onde fazer a pesquisa
	 * @return Uma Colecao
	 * @throws ControladorException
	 */
	public Collection consultarDadosDiarios(int anoMesReferencia, int id,
			String descricao, int idElo) throws ControladorException;

	/**
	 * [UC0319] Filtrar Aviso Bancario
	 * 
	 * 
	 * @author Fernanda Paiva
	 * @date 16/08/2006
	 * 
	 * @param avisoBancarioHelper
	 * @return Coleção de DebitoAutomaticoMovimento
	 * @throws ErroRepositorioException
	 */

	public Integer filtrarAvisoBancarioAbertoFechadoCount(
			AvisoBancarioHelper avisoBancarioHelper,
			AvisoBancarioHelper avisoBancarioHelperNovo)
			throws ControladorException;

	/**
	 * [UC0319] Filtrar Aviso Bancario
	 * 
	 * 
	 * @author Fernanda Paiva
	 * @date 16/08/2006
	 * 
	 * @param avisoBancarioHelper
	 * @return Coleção de DebitoAutomaticoMovimento
	 * @throws ErroRepositorioException
	 */
	public Collection filtrarAvisoBancarioAbertoFechadoParaPaginacao(
			AvisoBancarioHelper avisoBancarioHelper, Integer numeroPagina)
			throws ControladorException;

	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Cliente
	 * 
	 * @author Vivianne Sousa
	 * @date 21/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentoCliente(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos, Integer numeroPagina,
            String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ControladorException;

	/**
	 * Filtra os pagamento historicos do cliente
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 21/08/06,06/10/2006
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoCliente(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			Integer numeroPagina) throws ControladorException;

	/**
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Cliente
	 * 
	 * @author Vivianne Sousa
	 * @date 21/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarPagamentoClienteCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal) throws ControladorException;

	/**
	 * Filtrar a quantidade de pagamento historicos do cliente
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Cliente
	 * 
	 * @author Rafael Santos
	 * @date 06/10/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarPagamentoHistoricoClienteCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos) throws ControladorException;

	/**
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Aviso Bancario
	 * pesquisarPagamentoAvisoBancario
	 * 
	 * @author Vivianne Sousa
	 * @date 21/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentoAvisoBancarioParaPaginacao(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			Integer numeroPagina,
            String valorPagamentoInicial, 
            String valorPagamentoFinal ) throws ControladorException;

	/**
	 * Filtra os pagamento historicos do aviso bancario para paginação
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoAvisoBancarioParaPaginacao(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			Integer numeroPagina) throws ControladorException;

	/**
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Aviso Bancario
	 * pesquisarPagamentoAvisoBancario
	 * 
	 * @author Vivianne Sousa
	 * @date 21/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarPagamentoAvisoBancarioCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos,
            String valorPagamentoInicial, 
            String valorPagamentoFinal ) throws ControladorException;

	/**
	 * Filtra a quantidade de pagamento historicos do avio bancario
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarPagamentoHistoricoAvisoBancarioCount(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ControladorException;

	/**
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Imovel pesquisarPagamentoImovelParaPaginacao
	 * 
	 * @author Vivianne Sousa
	 * @date 22/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarPagamentoImovelCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos,
            String valorPagamentoInicial, 
            String valorPagamentoFinal) throws ControladorException;

	/**
	 * 
	 * Filtar a quantiade de pagamento historicos do imovel [UC0255] Filtrar
	 * Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarPagamentoHistoricoImovelCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos) throws ControladorException;

	/**
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Imovel pesquisarPagamentoImovelParaPaginacao
	 * 
	 * @author Vivianne Sousa
	 * @date 22/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */

	public Collection<Pagamento> pesquisarPagamentoImovelParaPaginacao(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			Integer numeroPagina, String valorPagamentoInicial, 
            String valorPagamentoFinal) throws ControladorException;

	/**
	 * Filtra os pagamento historicos do Imovel para paginação
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoImovelParaPaginacao(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			Integer numeroPagina) throws ControladorException;

	/**
	 * 
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * para o Relatório
	 * 
	 * @author Rafael Corrêa
	 * @date 29/08/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */

	public Collection<Pagamento> pesquisarPagamentoImovelRelatorio(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ControladorException;

	/**
	 * 
	 * Este caso de uso cria um sql que será usado na pesquisa de pagamentos
	 * para o Relatório
	 * 
	 * @author Rafael Corrêa
	 * @date 12/12/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection
	 * @throws ControladorException
	 */

	public Collection pesquisarPagamentoClienteRelatorio(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal) throws ControladorException;

	/**
	 * 
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * para o Relatório
	 * 
	 * @author Rafael Corrêa
	 * @date 29/08/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */

	public Collection<Pagamento> pesquisarPagamentoAvisoBancarioRelatorio(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ControladorException;

	/**
	 * 
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * para o Relatório
	 * 
	 * @author Rafael Corrêa
	 * @date 29/08/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */

	public Collection<Pagamento> pesquisarPagamentoMovimentoArrecadadorRelatorio(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ControladorException;

	/**
	 * 
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * para o Relatório
	 * 
	 * @author Rafael Corrêa
	 * @date 29/08/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */

	public Collection<Pagamento> pesquisarPagamentoLocalidadeRelatorio(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ControladorException;

	/**
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Imovel pesquisarPagamentoImovelParaPaginacao
	 * 
	 * @author Rafael Corrêa
	 * @date 29/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarPagamentoMovimentoArrecadadorCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos,
            String valorPagamentoInicial, 
            String valorPagamentoFinal) throws ControladorException;

	/**
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Imovel pesquisarPagamentoImovelParaPaginacao
	 * 
	 * @author Rafael Corrêa
	 * @date 29/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarPagamentoLocalidadeCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos,
            String valorPagamentoInicial, 
            String valorPagamentoFinal) throws ControladorException;

	/**
	 * Filtra a quantiadade dos Pagamento Historicos da Localidade
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Imovel pesquisarPagamentoImovelParaPaginacao
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarPagamentoHistoricoLocalidadeCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos) throws ControladorException;

	public Integer verificarExistenciaGuiaDevolucao(Integer idGuiaDevolucao)
			throws ControladorException;

	/**
	 * Pesquisa os avisos bancários para o relatório através das opções
	 * selecionadas no Filtrar Aviso Bancário
	 * 
	 * @author Rafael Corrêa
	 * @date 04/09/06
	 * 
	 * @return Collection<AvisoBancarioRelatorioHelper>
	 * @throws ControladorException
	 */

	public Collection pesquisarAvisoBancarioRelatorio(
			AvisoBancarioHelper avisoBancarioHelper)
			throws ControladorException;

	/**
	 * Pesquisa os avisos deduções de um aviso bancário para o relatório através
	 * do id do aviso bancário
	 * 
	 * @author Rafael Corrêa
	 * @date 05/09/06
	 * 
	 * @return Collection<DeducoesRelatorioHelper>
	 * @throws ControladorException
	 */

	public Collection pesquisarAvisoDeducoesAvisoBancarioRelatorio(
			Integer idAvisoBancario) throws ControladorException;

	/**
	 * Pesquisa os avisos acertos de um aviso bancário para o relatório através
	 * do id do aviso bancário
	 * 
	 * @author Rafael Corrêa
	 * @date 05/09/06
	 * 
	 * @return Collection<AcertosRelatorioHelper>
	 * @throws ControladorException
	 */

	public Collection pesquisarAvisoAcertosAvisoBancarioRelatorio(
			Integer idAvisoBancario) throws ControladorException;

	/**
	 * 
	 * Pesquisa dos dados diários da arrecadação
	 * 
	 * [UC0333] Filtrar Dados Diários da Arrecadação
	 * 
	 * @author Rafael Santos
	 * @date 05/09/2006
	 * 
	 * @return
	 */
	public Collection filtrarDadosDiariosArrecadacao(
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String idLocalidade, String idGerenciaRegional,
			String idArrecadador, String idElo, String[] idsImovelPerfil,
			String[] idsLigacaoAgua, String[] idsLigacaoEsgoto,
			String[] idsDocumentosTipos, String[] idsCategoria,
			String[] idsEsferaPoder) throws ControladorException;

	/**
	 * Pesquisa dos dados diários da arrecadação pela Gerencia
	 * 
	 * [UC0333] Filtrar Dados Diários da Arrecadação
	 * 
	 * @author Rafael Santos
	 * @date 05/09/2006
	 * 
	 * @return
	 */
	public Collection filtrarDadosDiariosArrecadacaoValoresDiarios(
			String idGerenciaRegional) throws ControladorException;

	/**
	 * Este caso de uso retorna uma colecao do movimento dos arrecadadores
	 * 
	 * [UC0263] - Filtrar Movimento dos Arrecadadores
	 * 
	 * @author Fernanda Paiva
	 * @date 12/09/2006
	 * 
	 * @param filtroArrecadadorMovimento
	 * @param numero
	 *            de páginas para paginação
	 * @return Uma coleçao com os movimentos selecionados
	 * @throws ControladorException
	 */
	public Collection<ArrecadadorMovimento> retornarColecaoMovimentoArrecadadores(
			FiltroArrecadadorMovimento filtro, Integer numeroPagina,
			String movimentoOcorrencia, String movimentoAceito,
			String movimentoAbertoFechado) throws ControladorException;

	/**
	 * Pesquisa os dados da Guia de Pagamento necessários para o relatório
	 * através do id da Guia de Pagamento
	 * 
	 * @author Vivianne Sousa
	 * @date 03/10/06
	 * 
	 * @return Collection<GuiaPagamentoRelatorioHelper>
	 * @throws ControladorException
	 */

	public Collection<GuiaPagamentoRelatorioHelper> pesquisarGuiaPagamentoRelatorio(
			String[] ids) throws ControladorException;

	/**
	 * Pesquisa os dados da Guia de Devolução necessários para o relatório
	 * através do id da Guia de Devolução
	 * 
	 * @author Ana Maria
	 * @date 05/10/06
	 * 
	 * @return Collection<GuiaDevolucaoRelatorioHelper>
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */

	public Collection<GuiaDevolucaoRelatorioHelper> pesquisarGuiaDevolucaoRelatorio(
			String[] ids) throws ControladorException;

	/**
	 * Faz a pesquisa de devoluçãoHistorico fazendo os carregamentos de
	 * clienteContas, clienteImoveis, clientesGuiaPagamento
	 * 
	 * @author Vivianne Sousa
	 * @date 09/10/2006
	 * 
	 * @param FiltroDevolucaoHistorico
	 * @return Collection<DevolucaoHistorico>
	 * @throws ErroRepositorioException
	 */
	public Collection<DevolucaoHistorico> pesquisarDevolucaoHistorico(
			FiltroDevolucaoHistorico filtroDevolucaoHistorico)
			throws ControladorException;

	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * histórico para o Relatório
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos histórico do tipo Debito a Cobrar do Cliente
	 * 
	 * @author Vivianne Sousa
	 * @date 17/10/06
	 * 
	 * @return Collection<PagamentoHistorico>
	 * @throws ErroRepositorioException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoLocalidadeRelatorio(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ControladorException;

	/**
	 * Consulta dados da tabela dados diarios arrecadacao
	 * 
	 * @author Rafael Santos
	 * @created 21/10/2006
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ArrecadacaoDadosDiarios consultarDadosDiarios(
			int idGerenciaRegional, int idLocalidade, int idElo)
			throws ControladorException;

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades da
	 * inserir guia de devolução no momento da exibição.
	 * 
	 * [FS0002] Validar registro de atendimento [FS0004] Validar ordem de
	 * servico.
	 * 
	 * @author Rafael Pinto
	 * @date 06/11/2006
	 * 
	 * @param RegistroAtendimento,OrdemServico
	 */
	public void validarExibirInserirGuiaDevolucao(RegistroAtendimento ra,
			OrdemServico ordemServico) throws ControladorException;

	/**
	 * Metodo responsável pela remoção das guias de devolução
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * @author Pedro Alexandre
	 * @date 24/11/2006
	 * 
	 * @param idImovel
	 * @param usuarioLogado
	 * @param ids
	 * @param pacoteNomeObjeto
	 * @param operacaoEfetuada
	 * @param acaoUsuarioHelper
	 * @throws ControladorException
	 */
	public void removerGuiaDevolucao(String idImovel, Usuario usuarioLogado,
			String[] ids, String pacoteNomeObjeto,
			OperacaoEfetuada operacaoEfetuada,
			Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper)
			throws ControladorException;

	/**
	 * [UC0263] Filtrar Movimento dos Arrecadadores
	 * 
	 * @author Vivianne Sousa
	 * @date 30/11/06
	 * 
	 * @param codigoBanco
	 * @param codigoRemessa
	 * @param descricaoIdentificacaoServico
	 * @param numeroSequencialArquivo
	 * @param dataGeracaoInicio
	 * @param dataGeracaoFim
	 * @param ultimaAlteracaoInicio
	 * @param ultimaAlteracaoFim
	 * @param descricaoOcorrencia
	 * @param indicadorAceitacao
	 * @param numeroPagina
	 * @param indicadorAbertoFechado
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<ArrecadadorMovimento> filtrarMovimentoArrecadadorParaPaginacao(
			String codigoBanco, String codigoRemessa,
			String descricaoIdentificacaoServico,
			String numeroSequencialArquivo, Date dataGeracaoInicio,
			Date dataGeracaoFim, Date ultimaAlteracaoInicio,
			Date ultimaAlteracaoFim, String descricaoOcorrencia,
			String indicadorAceitacao, Integer numeroPagina,
			String indicadorAbertoFechado) throws ControladorException;

	/**
	 * [UC0263] Filtrar Movimento dos Arrecadadores
	 * 
	 * @author Vivianne Sousa
	 * @date 30/11/06
	 * 
	 * @param codigoBanco
	 * @param codigoRemessa
	 * @param descricaoIdentificacaoServico
	 * @param numeroSequencialArquivo
	 * @param dataGeracaoInicio
	 * @param dataGeracaoFim
	 * @param ultimaAlteracaoInicio
	 * @param ultimaAlteracaoFim
	 * @param descricaoOcorrencia
	 * @param indicadorAceitacao
	 * @param indicadorAbertoFechado
	 * 
	 * @throws ControladorException
	 */
	public Integer filtrarMovimentoArrecadadoresCount(String codigoBanco,
			String codigoRemessa, String descricaoIdentificacaoServico,
			String numeroSequencialArquivo, Date dataGeracaoInicio,
			Date dataGeracaoFim, Date ultimaAlteracaoInicio,
			Date ultimaAlteracaoFim, String descricaoOcorrencia,
			String indicadorAceitacao, String indicadorAbertoFechado)
			throws ControladorException;

	/**
	 * Pesquisar os ids das localidades que possuem pagamentos
	 * 
	 * [UC0301] Gerar Dados Diários da Arrecadação
	 * 
	 * @author Pedro Alexandre
	 * @date 04/12/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */
	// public Collection<Integer> pesquisarIdsLocalidadeComPagamentos() throws
	// ControladorException;
	/**
	 * 
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * para o Relatório
	 * 
	 * @author Rafael Corrêa
	 * @date 12/12/2006
	 * 
	 * @return Collection
	 * @throws ControladorException
	 */

	public Collection pesquisarPagamentoLocalidadeAmbosRelatorio(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ControladorException;

	/**
	 * Pesquisa o imóvel pelo id fazendo os carregamentos necessários
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Corrêa
	 * @date 16/12/06
	 * 
	 * @return Imovel
	 * @throws ControladorException
	 */
	public Imovel pesquisarImovelPagamento(Integer idImovel)
			throws ControladorException;

	/**
	 * Pesquisa o cliente pelo id fazendo os carregamentos necessários
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Corrêa
	 * @date 16/12/06
	 * 
	 * @return Cliente
	 * @throws ErroRepositorioException
	 */
	public Cliente pesquisarClientePagamento(Integer idCliente)
			throws ControladorException;

	/**
	 * Pesquisa o endereço de correspondência do cliente pelo seu id fazendo os
	 * carregamentos necessários
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Corrêa
	 * @date 16/12/06
	 * 
	 * @return ClienteEndereco
	 * @throws ControladorException
	 */
	public ClienteEndereco pesquisarClienteEnderecoPagamento(Integer idCliente)
			throws ControladorException;

	/**
	 * Pesquisa o telefone padrão do cliente pelo seu id fazendo os
	 * carregamentos necessários
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Corrêa
	 * @date 16/12/06
	 * 
	 * @return ClienteFone
	 * @throws ControladorException
	 */
	public ClienteFone pesquisarClienteFonePagamento(Integer idCliente)
			throws ControladorException;

	/**
	 * Pesquisa os clientes do imóvel pelo seu id do imóvel fazendo os
	 * carregamentos necessários
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Corrêa
	 * @date 16/12/06
	 * 
	 * @return Collection<ClienteImovel>
	 * @throws ControladorException
	 */
	public Collection<ClienteImovel> pesquisarClientesImoveisPagamento(
			Integer idImovel) throws ControladorException;

	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Debito a Cobrar do Cliente
	 * pesquisarPagamentoLocalidade
	 * 
	 * @author Rafael Corrêa
	 * @date 21/12/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentoMovimentoArrecadadorParaPaginacao(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			Integer numeroPagina,
            String valorPagamentoInicial, 
            String valorPagamentoFinal) throws ControladorException;

	public Collection<Integer> pesquisarIdsLocalidadeComPagamentosOuDevolucoes()
			throws ControladorException;

	/**
	 * [UC0268] - Apresentar Análise do Aviso Bancário
	 * 
	 * @author Vivianne Sousa
	 * @date 13/12/2006
	 * 
	 * @return Collection<AcertosAvisoBancarioHelper>
	 * @throws ControladorException
	 */

	public Collection<AcertosAvisoBancarioHelper> pesquisarAcertosAvisoBancario(
			Integer idAvisoBancario, Integer indicadorArrecadacaoDevolucao)
			throws ControladorException;

	/**
	 * [UC0268] - Apresentar Análise do Aviso Bancário
	 * 
	 * @author Vivianne Sousa
	 * @date 13/12/2006
	 * 
	 * @return Collection<DeducoesHelper>
	 * @throws ControladorException
	 */

	public Collection<DeducoesHelper> pesquisarDeducoesAvisoBancario(
			Integer idAvisoBancario) throws ControladorException;

	/**
	 * [UC0263] Filtrar Movimento dos Arrecadadores
	 * 
	 * @author Vivianne Sousa
	 * @date 30/11/06
	 * 
	 * @param codigoBanco
	 * @param codigoRemessa
	 * @param descricaoIdentificacaoServico
	 * @param numeroSequencialArquivo
	 * @param dataGeracaoInicio
	 * @param dataGeracaoFim
	 * @param ultimaAlteracaoInicio
	 * @param ultimaAlteracaoFim
	 * @param descricaoOcorrencia
	 * @param indicadorAceitacao
	 * @param indicadorAbertoFechado
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection filtrarMovimentoArrecadadorParaRelatorio(
			String codigoBanco, String codigoRemessa,
			String descricaoIdentificacaoServico,
			String numeroSequencialArquivo, Date dataGeracaoInicio,
			Date dataGeracaoFim, Date ultimaAlteracaoInicio,
			Date ultimaAlteracaoFim, String descricaoOcorrencia,
			String indicadorAceitacao, String indicadorAbertoFechado)
			throws ControladorException;

	/**
	 * [UC0263] Filtrar Movimento dos Arrecadadores
	 * 
	 * @author Vivianne Sousa
	 * @date 04/01/07
	 * 
	 * @param codigoBanco
	 * @param codigoRemessa
	 * @param descricaoIdentificacaoServico
	 * @param numeroSequencialArquivo
	 * @param dataGeracaoInicio
	 * @param dataGeracaoFim
	 * @param ultimaAlteracaoInicio
	 * @param ultimaAlteracaoFim
	 * @param descricaoOcorrencia
	 * @param indicadorAceitacao
	 * @param indicadorAbertoFechado
	 * 
	 * @throws ControladorException
	 */
	public Integer filtrarMovimentoArrecadadoresRelatorioCount(
			String codigoBanco, String codigoRemessa,
			String descricaoIdentificacaoServico,
			String numeroSequencialArquivo, Date dataGeracaoInicio,
			Date dataGeracaoFim, Date ultimaAlteracaoInicio,
			Date ultimaAlteracaoFim, String descricaoOcorrencia,
			String indicadorAceitacao, String indicadorAbertoFechado)
			throws ControladorException;

	/**
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * Para cada guia de pagamento transferida para o histórico atualiza o
	 * indicador de que a guia de pagamento está no histórico.
	 * 
	 * @author Pedro Alexandre
	 * @date 09/01/2007
	 * 
	 * @param colecaoGuiasPagamento
	 * @throws ControladorException
	 */
	public void atualizarIndicadorGuiaPagamentoNoHistorico(
			Collection colecaoGuiasPagamento) throws ControladorException;

	/**
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * Transfere para o histórico os pagamentos informados.
	 * 
	 * @author Pedro Alexandre
	 * @date 09/01/2007
	 * 
	 * @param colecaoGuiasPagamento
	 * @throws ControladorException
	 */
	public void transferirGuiaPagamentoParaHistorico(
			Collection<GuiaPagamento> colecaoGuiasPagamento)
			throws ControladorException;

	/**
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * Transfere para o histórico as guias de pagamentos e os relacionamentos
	 * ligados a ela.
	 * 
	 * @author Pedro Alexandre
	 * @date 09/01/2007
	 * 
	 * @param colecaoGuiasPagamento
	 * @param anoMesFaturamentoSistemaParametro
	 * @throws ControladorException
	 */
	public void transferirPagamentoParaHistorico(
			Collection<Pagamento> colecaoPagamentos)
			throws ControladorException;

	/**
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * Transfere para o histórico as devoluções informadas.
	 * 
	 * @author Administrador
	 * @date 09/01/2007
	 * 
	 * @param colecaoDevolucoes
	 * @throws ControladorException
	 */
	public void transferirDevolucaoParaHistorico(
			Collection<Devolucao> colecaoDevolucoes)
			throws ControladorException;

	/**
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * Atualiza o ano/mês de referência da arrecadação.
	 * 
	 * @author Pedro Alexandre
	 * @date 09/01/2007
	 * 
	 * @param anoMesArrecadacaoSistemaParametro
	 * @throws ControladorException
	 */
	public void atualizarAnoMesArrecadacao(
			Integer anoMesArrecadacaoSistemaParametro)
			throws ControladorException;

	/**
	 * Metódo responsável por inserir uma Agência Bancaria
	 * 
	 * [UC0000 - Inserir Agência Bancaria
	 * 
	 * @author Thiago Tenório
	 * @date 27/06/2006, 16/11/2006
	 * 
	 * @param agencia
	 *            bancaria
	 * @param usuarioLogado
	 * @return
	 * @throws ControladorException
	 */
	public Integer inserirAgenciaBancaria(Agencia agencia)
			throws ControladorException;

	/**
	 * [UC0391] Atualizar Agência Bancária.
	 * 
	 * 
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 01/11/2006
	 * 
	 * @param
	 * @throws ControladorException
	 */
	public void atualizarAgenciaBancaria(Agencia agencia)
			throws ControladorException;

	/**
	 * [UC0506] Inserir Arrecadador
	 * 
	 * Inclusão de um novo arrecadador.
	 * 
	 * @author Marcio Roberto
	 * @date 29/01/2007
	 * 
	 * @param String
	 *            idAgente, String idCliente, String inscricaoEstadual, String
	 *            idImovel, Usuario usuarioLogado
	 * @throws ControladorException
	 */
	public Integer inserirArrecadador(String idAgente, String idCliente,
			String inscricaoEstadual, String idImovel, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * @author Ana Maria
	 * @date 29/01/2007
	 * 
	 * @param idGuiaPagamento
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGuiaPagemento(Integer idGuiaPagamento)
			throws ControladorException;

	/**
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * Metodo responsável pela transferência das contas, guias de pagamento,
	 * pagamentos e devoluções para o histórico.
	 * 
	 * @author Pedro Alexandre
	 * @date 06/02/2007
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @throws ControladorException
	 */
	public void gerarHistoricoParaEncerrarArrecadacaoMes(
			Integer anoMesReferenciaArrecadacao, Integer idLocalidade,
			int idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * @author Marcio Roberto
	 * @date 07/02/2007
	 * 
	 * @param codigoAgente
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaAgente(Integer codigoAgente)
			throws ControladorException;

	/**
	 * [UC0507] Manter Arrecadador
	 * 
	 * Remover Arrecadador
	 * 
	 * @author Marcio Roberto
	 * @date 08/02/2007
	 * 
	 * @pparam ids, usuarioLogado
	 * @throws ControladorException
	 */
	public void removerArrecadador(String[] ids, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * [UC507] Manter Arrecadador [SB0001]Atualizar Arrecadador
	 * 
	 * @author Marcio Roberto
	 * @date 8/02/2007
	 * 
	 * @pparam
	 * @throws ControladorException
	 */
	public void atualizarArrecadador(Arrecadador arrecadador,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * Atualiza logradouroCep de um ou mais imóveis
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarLogradouroCep(LogradouroCep logradouroCepAntigo,
			LogradouroCep logradouroCepNovo) throws ControladorException;

	/**
	 * Atualiza logradouroBairro de um ou mais imóveis
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarLogradouroBairro(
			LogradouroBairro logradouroBairroAntigo,
			LogradouroBairro logradouroBairroNovo) throws ControladorException;

	/**
	 * 
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * para o Relatório
	 * 
	 * @author Rafael Corrêa
	 * @date 29/08/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarPagamentoImovelAmbosRelatorio(String idImovel)
			throws ControladorException;

	/**
	 * Metódo responsável por inserir uma Conta Bancaria
	 * 
	 * [UC0000 - Inserir Conta Bancaria
	 * 
	 * @author Thiago Tenório
	 * @date 27/06/2006, 16/11/2006
	 * 
	 * @param agencia
	 *            bancaria
	 * @param usuarioLogado
	 * @return
	 * @throws ControladorException
	 */
	public Integer inserirContaBancaria(ContaBancaria contaBancaria)
			throws ControladorException;

	/**
	 * [UC0391] Atualizar Conta Bancária.
	 * 
	 * 
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 01/11/2006
	 * 
	 * @param
	 * @throws ControladorException
	 */
	public void atualizarContaBancaria(ContaBancaria contaBancaria)
			throws ControladorException;

	/**
	 * [UC0213] Desfazer Parcelamento Debito - remover guia pagamento referente
	 * ao parcelamento
	 * 
	 * remove a guia de pagamento do Pagamento
	 * 
	 * @author Vivianne Sousa
	 * @date 06/03/2007
	 * 
	 * @param
	 * @return void
	 */
	public void removerGuiaPagamentoPagamento(Integer idPagamento)
			throws ControladorException;

	/**
	 * [UC0113] - Faturar Grupo de Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 20/03/2007
	 * 
	 * @param idDebitoACobrar
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadePagamentosPorDebitoACobrar(
			Integer idDebitoACobrar) throws ControladorException;

	/**
	 * Pesquisa os movimentos dos arrecadores para a geração do relatório
	 * 
	 * [UCXXXX] Acompanhar Movimento dos Arrecadadores
	 * 
	 * @author Rafael Corrêa
	 * @date 02/04/2007
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarMovimentoArrecadadoresRelatorio(
			Integer mesAnoReferencia, Integer idArrecadador,
			Integer idFormaArrecadacao, Date dataPagamentoInicial,
			Date dataPagamentoFinal) throws ControladorException;

	/**
	 * Relatório para acompanhar o movimento dos arrecadadores
	 * 
	 * @author Sávio Luiz
	 * @date 02/04/2007
	 * 
	 * @param idDebitoACobrar
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public void gerarResumoAcompanhamentoMovimentoArrecadadores(
			Usuario usuario, String mesAnoReferencia, Arrecadador arrecadador,
			ArrecadacaoForma arrecadacaoForma) throws ControladorException;

	public Collection<Integer> pesquisarIdsSetoresComPagamentosOuDevolucoes()
			throws ControladorException;

	public void gerarHistoricoConta(Integer anoMesReferenciaArrecadacao,
			Integer idSetorComercial, int idFuncionalidadeIniciada)
			throws ControladorException;

	/**
	 * Seleciona os pagamentos histórios de um aviso
	 * 
	 * @author Rafael Corrêa
	 * @date 23/04/2007
	 * 
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarPagamentoHistoricoAvisoBancario(
			Integer idAvisoBancario) throws ControladorException;

	/**
	 * [UC0150] Retificar Conta
	 * 
	 * @author Vivianne Sousa
	 * @data 23/04/2006
	 * 
	 * @param idConta
	 * @return idParcelamento
	 */
	public Pagamento pesquisarPagamentoDeConta(Integer idConta)
		throws ControladorException;

	/**
	 * 
	 * @author Sávio Luiz
	 * @data 28/04/2007
	 * 
	 * @param idConta
	 * @return idParcelamento
	 */
	public Integer pesquisarIdPagamentoDoDebitoACobrar(Integer idDebitoACobrar)
			throws ControladorException;

	/**
	 * 
	 * @author Sávio Luiz
	 * @data 28/04/2007
	 * 
	 * @param idConta
	 * @return idParcelamento
	 */
	public Integer pesquisarIdPagamentoDaGuia(Integer idGuiaPagamento)
			throws ControladorException;

	/**
	 * [UC0259] - Processar Pagamento com código de Barras [SB0008] - Alterar
	 * Vencimento dos Itens do documento de cobrança Autor: Sávio Luiz
	 * Data:15/02/2006
	 */

	public void alterarVencimentoItensDocumentoCobranca(
			Integer idCobrancaDocumento, Date dataEmissao)
			throws ControladorException;
	
	
    /**
     * [UC0509] Inserir Contrato Arrecadador -
     * 
     * @author Marcio Roberto
     * @date 22/03/2007
     * 
     * @param 
     * @return void
     */            
    public Integer inserirContratoArrecadador(ArrecadadorContrato contrato,
    		Collection<ArrecadadorContratoTarifa> colecaoArrecadadorContratoTarifa,
			Usuario usuarioLogado) throws ControladorException;
 
	/**
	 * @author Marcio Roberto
	 * @date 09/04/2007
	 * 
	 * @param codigoArrecadador
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaArrecadador(Integer codigoArrecadador)
			throws ControladorException;
	
	/**
	 * @author Marcio Roberto
	 * @date 09/04/2007
	 * 
	 * @param numeroContrato
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaContrato(String numeroContrato)
			throws ControladorException;

	/**
	 * [UC507] Manter Contrato de Arrecadador [SB0001]Atualizar Contrato Arrecadador 
	 * 
	 * @author Marcio Roberto
	 * @date 12/04/2007
	 * 
	 * @pparam 
	 * @throws ControladorException
	 */
	public void atualizarContratoArrecadador(
			ArrecadadorContrato arrecadadorContrato, Collection<ArrecadadorContratoTarifa> colecaoArrecadadorContratoTarifa,
			Usuario usuarioLogado)
			throws ControladorException;
	

	/**
	 * [UC0510] Manter Arrecadador 
	 * 			
	 * 			Remover Contrato de Arrecadador
	 * 
	 * @author Marcio Roberto
	 * @date 12/04/2007
	 * 
	 * @pparam ids, usuarioLogado
	 * @throws ControladorException
	 */
	public void removerContratoArrecadador(String[] ids, Usuario usuarioLogado)
			throws ControladorException;

	
	/**
	 * Retorna uma coleção de ids de categoria
	 *
	 * @author Pedro Alexandre
	 * @date 13/06/2007
	 *
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarIdsCategoria()	throws ControladorException ;

	/**
	 * Pesquisar pagamentos pelo aviso bancário
	 * 
	 * @author Ana Maria
	 * @date 11/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public PagamentosDevolucoesHelper filtrarPagamentos(
			FiltroPagamento filtroPagamento) throws ControladorException;
	
	/**
	 * Pesquisar devoluções pelo aviso bancário
	 * 
	 * @author Ana Maria
	 * @date 11/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public PagamentosDevolucoesHelper filtrarDevolucoes(
			FiltroDevolucao filtroDevolucao) throws ControladorException;	
	
	/**
	 * Pesquisar valores de arrecadação e devolução do aviso bancário
	 * 
	 * @author Ana Maria
	 * @date 14/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public ValoresArrecadacaoDevolucaoAvisoBancarioHelper pesquisarValoresAvisoBancario(
			Integer idAvisoBancario) throws ControladorException;
	
	/**
	 * Atualizar Pagamentos e Aviso Bancário
	 * 
	 * @author Ana Maria
	 * @date 15/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public void atualizarAvisoBancarioPagamentos(Collection<Integer> idsPagamentos, String arrecadacaoInformadoDepoisOrigem, String arrecadacaoCalculadoDepoisOrigem, 
			String arrecadacaoInformadoDepoisDestino, String arrecadacaoCalculadoDepoisDestino, Integer idAvisoBancarioO, Integer idAvisoBancarioD)
			throws ControladorException;
	
	/**
	 * Atualizar Devoluções e Aviso Bancário
	 * 
	 * @author Ana Maria
	 * @date 15/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public void atualizarAvisoBancarioDevolucoes(Collection<Integer> idsDevolucoes, String devolucaoInformadoDepoisOrigem, String devolucaoCalculadoDepoisOrigem, 
			String devolucaoInformadoDepoisDestino, String devolucaoCalculadoDepoisDestino, Integer idAvisoBancarioO, Integer idAvisoBancarioD)
			throws ControladorException;
	
	/**
	 * [UC0263] Filtrar Movimento dos Arrecadadores - Relatório
	 * 
	 * @author Ana Maria
	 * @date 13/07/07
	 * 
	 * @param codigoBanco
	 * @param codigoRemessa
	 * @param descricaoIdentificacaoServico
	 * @param numeroSequencialArquivo
	 * @param dataGeracaoInicio
	 * @param dataGeracaoFim
	 * @param ultimaAlteracaoInicio
	 * @param ultimaAlteracaoFim
	 * @param descricaoOcorrencia
	 * @param indicadorAceitacao
	 * @param indicadorAbertoFechado
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> filtrarIdsMovimentoArrecadador(
			String codigoBanco, String codigoRemessa,
			String descricaoIdentificacaoServico,
			String numeroSequencialArquivo, Date dataGeracaoInicio,
			Date dataGeracaoFim, Date ultimaAlteracaoInicio,
			Date ultimaAlteracaoFim, String descricaoOcorrencia,
			String indicadorAceitacao,String indicadorAbertoFechado) throws ControladorException;
	
	/**
	 * 
	 * [UC0619] Gerar Relação de Acompanhamento dos Movimentos Arrecadadores por NSA
	 * 
	 * @author Ana Maria
	 * @date 12/07/2007
	 * 
	 * @param idMovimentoArrecadador
	 * @return
	 */
	public Collection<MovimentoArrecadadoresPorNSAHelper> gerarMovimentoArrecadadoresNSA
			(Collection<Integer> idsArrecadadorMovimento, Integer codigoFormaArrecadacao)throws ControladorException;	

	
	/**
	 * Obtém a representação númerica do código de barras de um pagamento de
	 * acordo com os parâmetros informados
	 * 
	 * [UC0229] Obter Representação Numérica do Código de Barras
	 * 
	 * Formata a identificação do pagamento de acordo com o tipo de pagamento
	 * informado
	 * 
	 * [SB0001] Obter Identificação do Pagamento
	 * 
	 * @author Pedro Alexandre
	 * @date 20/04/2006
	 * 
	 * @param tipoPagamento
	 * @param idLocalidade
	 * @param matriculaImovel
	 * @param anoMesReferenciaConta
	 * @param digitoVerificadorRefContaModulo10
	 * @param idTipoDebito
	 * @param anoEmissaoGuiaPagamento
	 * @param sequencialDocumentoCobranca
	 * @param idTipoDocumento
	 * @param idCliente
	 * @param seqFaturaClienteResponsavel
	 * @return
	 */
	public String obterIdentificacaoPagamento(Integer tipoPagamento,
			Integer idLocalidade, Integer matriculaImovel,
			String mesAnoReferenciaConta,
			Integer digitoVerificadorRefContaModulo10, Integer idTipoDebito,
			String anoEmissaoGuiaPagamento, String sequencialDocumentoCobranca,
			Integer idTipoDocumento, Integer idCliente,
			Integer seqFaturaClienteResponsavel,String idGuiaPagamento) 
			throws ControladorException;
	
	/**
	 * Obtém a representação númerica do código de barras de um pagamento de
	 * acordo com os parâmetros informados
	 * 
	 * [UC0229] Obter Representação Numérica do Código de Barras
	 * 
	 * @author Pedro Alexandre,Hugo Amorim,Hugo Amorim
	 * @date 20/04/2006,12/01/2010,10/03/2010
	 * 
	 * @param tipoPagamento
	 * @param valorCodigoBarra
	 * @param idLocalidade
	 * @param matriculaImovel
	 * @param anoMesReferenciaConta
	 * @param digitoVerificadorRefContaModulo10
	 * @param idTipoDebito
	 * @param anoEmissaoGuiaPagamento
	 * @param sequencialDocumentoCobranca
	 * @param idTipoDocumento
	 * @param idCliente
	 * @param seqFaturaClienteResponsavel
	 * @param idGuiaPagamento
	 * @return
	 * @throws ControladorException
	 */
	public String obterRepresentacaoNumericaCodigoBarra(
			Integer tipoPagamento, BigDecimal valorCodigoBarra,
			Integer idLocalidade, Integer matriculaImovel,
			String mesAnoReferenciaConta,
			Integer digitoVerificadorRefContaModulo10, Integer idTipoDebito,
			String anoEmissaoGuiaPagamento, String sequencialDocumentoCobranca,
			Integer idTipoDocumento, Integer idCliente,
			Integer seqFaturaClienteResponsavel,
			String idGuiaPagamento)
			throws ControladorException;
	
	
	/**
	 * Processamento Rápido
	 * 
	 * @author Raphael Rossiter
	 * @date 17/08/2007
	 * 
	 * @return Collection<Conta>
	 * @throws ErroRepositorioException
	 */
	public Collection<Conta> pesquisarContaComPagamentoHistorico()
			throws ControladorException ;
	
	
	/**
	 * Inserir uma coleção de pagamentos informados manualmente
	 * 
	 * @author Raphael Rossiter
	 * @date 26/09/2007
	 * 
	 * @return Collection, Usuario, AvisoBancario
	 * @throws ControladorException
	 */
	public Integer inserirPagamentos(Collection<Pagamento> colecaoPagamento, Usuario usuarioLogado,
			AvisoBancario avisoBancario) throws ControladorException ;
	
	
	/**
	 * Inserir Pagamentos por código de barras
	 * 
	 * @author Raphael Rossiter
	 * @date 30/10/2007
	 * 
	 * @return 
	 * @throws ControladorException
	 */
	public Integer inserirPagamentosCodigoBarras(Collection<Pagamento> colecaoPagamentos, 
			Collection<Devolucao> colecaoDevolucoes, Usuario usuarioLogado, 
			AvisoBancario avisoBancario) throws ControladorException ;
    

    
    /**
     * Obtém a representação númerica do código de barras da Ficha de Compensação
     * 
     * [UC0716] Obter Representação Numérica do Código de Barras da Ficha de Compensação
     * 
     * @author Vivianne Sousa
     * @date 12/11/2007
     * 
     * @param codigoBanco
     * @param codigoMoeda
     * @param valorCodigoBarra
     * @param nossoNumero
     * @param carteira
     * @param fatorVencimento
     * @return
     * @throws ParametroNaoInformadoException
     */
    public String obterRepresentacaoNumericaCodigoBarraFichaCompensacao(String especificacaoCodigoBarra)
                 throws ControladorException;
    
    /**
     * Obtém a representação númerica do código de barras da Ficha de Compensação
     * 
     * [UC0716] Obter Representação Numérica do Código de Barras da Ficha de Compensação
     * 
     * @author Vivianne Sousa
     * @date 12/11/2007
     * 
     * @param codigoBanco
     * @param codigoMoeda
     * @param valorCodigoBarra
     * @param nossoNumero
     * @param carteira
     * @param fatorVencimento
     * @return
     * @throws ParametroNaoInformadoException
     */
    public String obterEspecificacaoCodigoBarraFichaCompensacao( String codigoBanco,
                 String codigoMoeda, BigDecimal valorCodigoBarra, String nossoNumeroSemDV,
                 String carteira, String fatorVencimento)
                 throws ControladorException; 
                 
    
    
    /**
	 * [UC0626] Gerar Resumo de Metas Acumulado no Mês (CAERN)
	 * 
	 * @author Sávio Luiz
	 * @data 28/11/2007
	 * 
	 * @param idConta
	 * @return idParcelamento
	 */
	public Collection pesquisarPagamentoDeContas(Collection colecaoConta)
			throws ControladorException;
	
	 /**
     * [UC0739] - Informar Situação de Expurgo do Pagamento
     * Autor: Sávio Luiz
     * Data: 02/01/2008
     */
    public Object[] gerarColecaoDadosPagamentoPelaData(String dataPagamento,Integer idCliente,Integer anoMesArrecadacao) throws ControladorException;
    
    /**
	 * [UC0739] - Informar Situação de Expurgo do Pagamento
	 * Autor: Sávio Luiz
	 * Data: 02/01/2008
	 */
	public void atualizarSituacaoExpurgoPagamento(Collection colecaoPagamento)
			throws ControladorException;
	
	/**
	 * [UC0744] Gerar Comparativo do Faturamento, Arrecadação e Expurgo
	 * 
	 * @author Sávio Luiz
	 * @data 10/01/2008
	 * 
	 * @param idConta
	 * @return idParcelamento
	 */
	public Collection<RelatorioComparativoFatArrecExpurgoBean> pesquisarDadosComparativosFaturamentoArrecadacaoExpurgo(Integer anoMesReferencia,
			String idGerenciaRegional,String idUnidadeNegocio)
			throws ControladorException;
	
	/**
	 * [UC0744] Gerar Comparativo do Faturamento, Arrecadação e Expurgo
	 * 
	 * @author Sávio Luiz
	 * @data 14/01/2008
	 * 
	 * @param idConta
	 * @return idParcelamento
	 */
	public int pesquisarQuantidadeDadosComparativosFaturamentoArrecadacaoExpurgo(Integer anoMesReferencia,
			String idGerenciaRegional,String idUnidadeNegocio)
			throws ControladorException;
	
	/**
	 * [UCXXX] - Pesquisar Guia de Devolucao
	 * 
	 * Faz a pesquisa de guia de devolução pelo id
	 * 
	 * @author Roberto Barbalho
	 * @date 25/01/2008
	 * 
	 * @param guiaDevolucaoId
	 * @return GuiaDevolucao
	 * @throws ControladorException
	 */
	public GuiaDevolucao pesquisarGuiaDevolucao(Integer guiaDevolucaoId)
			throws ControladorException;
    
    /**
     * [UC0737] Atualiza Quantidade de Parcela Paga Consecutiva e Parcela Bônus
     * 
     * @author Vivianne Sousa
     * @data 01/02/2008
     * 
     * @param idConta
     * @return dataPagamento
     */
    public Date pesquisarDataPagamentoDeConta(Integer idConta)
            throws ControladorException;
    
    /**
	 * [UC0322] Inserir Guia de Devolução
	 *
	 * Quando for verificar a existência da conta, pesquisar também  no respectivo histórico no fluxo secundário
	 *  [FS0008 - Verificar existência da conta]
	 *
	 * @author Raphael Rossiter
	 * @date 14/04/2008
	 *
	 * @param colecaoConta
	 * @param idLocalidade
	 */
	public ContaGeral verificarLocalidadeContaGuiaDevolucao(Collection colecaoConta, String idLocalidade)
		throws ControladorException ;
	
	/**
	 * [UC0322] Inserir Guia de Devolução
	 *
	 * @author Raphael Rossiter
	 * @date 14/04/2008
	 *
	 * @param imovel
	 * @param anoMesReferencia
	 * @return Collection
	 */
	public Collection pesquisarContaParaGuiaDevolucao(Imovel imovel, Integer anoMesReferencia) 
		throws ControladorException;
	
	/**
	 * [UC0322] Inserir Guia de Devolução
	 * 
	 * [FS0014] - Verificar imóvel do débito a cobrar
	 * 
	 * [FS0015] - Verificar localidade do débito a cobrar
	 *
	 * @author Raphael Rossiter
	 * @date 14/04/2008
	 *
	 * @param idImovel
	 * @param idLocalidade
	 * @param ordemServico
	 * @param debitoACobrar
	 */
	public DebitoACobrarGeral verificarDebitoACobrarParaGuiaDevolucao(Collection colecaoDebitoACobrar, Integer idImovel, 
		Integer idLocalidade, OrdemServico ordemServico) throws ControladorException ;
	
	/**
	 * [UC0322] Inserir Guia de Devolução
	 *
	 * @author Raphael Rossiter
	 * @date 14/04/2008
	 *
	 * @param imovel
	 * @param idDebitoACobrar
	 * @return Collection
	 */
	public Collection pesquisarDebitoACobrarParaGuiaDevolucao(Imovel imovel, Integer idDebitoACobrar) 
		throws ControladorException;
	
	/**
	 * [UC0322] Inserir Guia de Devolução
	 *
	 * @author Raphael Rossiter
	 * @date 15/04/2008
	 *
	 * @param idImovel
	 * @param idCliente
	 * @param idGuiaPagamento
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarGuiaPagamentoParaGuiaDevolucao(Integer idImovel, Integer idCliente, 
			Integer idGuiaPagamento) throws ControladorException ;
	
	/**
	 * [UC0322] Inserir Guia de Devolução
	 *
	 * @author Raphael Rossiter
	 * @date 15/04/2008
	 *
	 * @param colecaoGuiaPagamento
	 * @param registroAtendimento
	 * @param idClienteRegistroAtendimento
	 * @param localidadeImovel
	 * @param ordemServico
	 * @return GuiaPagamentoGeral
	 * @throws ControladorException
	 */
	public GuiaPagamentoGeral verificarGuiaPagamentoParaGuiaDevolucao(Collection colecaoGuiaPagamento,
			RegistroAtendimento registroAtendimento, Integer idClienteRegistroAtendimento,
			Integer idLocalidadeImovel, OrdemServico ordemServico) throws ControladorException;
	
	
	/**
	 * [UC0352] Emitir Conta
	 * 
	 * pesquisa o nome do banco e código da agencia passando o id do imóvel
	 * 
	 * [SB0017] - Gerar Linhas das contas com Débito Automático
	 * 
	 * @author Sávio Luiz
	 * @date 26/05/2006
	 * 
	 * @return String
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarParmsDebitoAutomatico(Integer idImovel) throws ControladorException;

	
	 /**
     * [UC0823] Atualiza Ligação de Água de Ligado em Análise para Ligado
     * 
     * Seleciona a lista de imóveis que esteja com a situação de água ligado em análise.
     * @author Yara Taciane
     * @date 23/05/2008
     */
	public List pesquisarLocalidadeComImoveisComSituacaoLigadoEmAnalise()throws ControladorException;
	
	
	/**
	 * [UC0823] Atualiza Ligação de Água de Ligado em Análise para Ligado.
	 * 
	 *  Este caso de uso permite atualizar a situação de água de ligado em análise para ligado.
	 * 	Este caso de uso deve ser processado após o encerramento da arrecadação.
	 * 
	 * @author Yara Taciane
	 * @date 04/06/2008
	 * 
	 * @return String
	 * @throws ErroRepositorioException
	 */
	public void atualizarLigacaoAguaLigadoAnaliseParaLigado(Integer idFuncionalidadeIniciada, Integer idLocalidade) throws ControladorException;
	

	/**
	 * Verificar se a entrada de um parcelamento foi paga
	 * @param idImovel
	 * @param dataParcelamento
	 * @return
	 * @throws ControladorException
	 * 
	 * @author Francisco do Nascimento
	 * @date 02/07/2008
	 */
	public boolean verificarExistenciaPagamentoEntradaParcelamento(Integer idImovel,
			Date dataParcelamento)
			throws ControladorException;
	
	/**
	 * [UC0826] Gerar Relatório Análise da Arrecação
	 * 
	 * @author Victor Cisneiros
	 * @date 24/07/2008
	 */
    public List<RelatorioAnaliseArrecadacaoBean> pesquisarAnaliseArrecadacao(
    		PesquisarAnaliseArrecadacaoHelper helper) throws ControladorException;
    
	/**
	 * [UC0827] Gerar Relatório Análise dos Avisos Bancarios
	 * 
	 * @see ControladorArrecadacao#pesquisarAnaliseAvisosBancarios(PesquisarAnaliseAvisosBancariosHelper)
	 * 
	 * @author Victor Cisneiros
	 * @date 30/07/2008
	 */
    public List<RelatorioAnaliseAvisosBancariosBean> pesquisarAnaliseAvisosBancarios(
    		PesquisarAnaliseAvisosBancariosHelper helper) throws ControladorException;
    
    /**
     * [UC0829] Gerar Relatório Avisos Bancarios Por Conta Corrente
     * 
     * @author Victor Cisneiros
     * @date 21/08/2008
     */
    public List<RelatorioAvisoBancarioPorContaCorrenteBean> pesquisarAvisoBancarioPorContaCorrente(
    		PesquisarAvisoBancarioPorContaCorrenteHelper helper) throws ControladorException;
    
    /**
     * [UC0828] Atualizar Diferenca Acumulada no Mês
     * 
     * @see ControladorArrecadacao#atualizarDiferencaAcumuladaNoMes(int, int)
     * 
     * @author Victor Cisneiros
     * @date 01/09/2008
     */
    public void atualizarDiferencaAcumuladaNoMes(int idFuncionalidadeIniciada, int anoMesArrecadacao) throws ControladorException;
    
    /**
     * 
     * [UC0818] - Gerar Histórico do Encerramento da Arrecadação
     *
     * Fluxo Principal
     *
     * [FS0001] - Verifica existencia resumo arrecadação   
     *
     * @author bruno
     * @date 24/10/2008
     *
     * @param anoMesReferencia
     * @return
     */
    public boolean verificarExistenciaResumoArrecadacaoParaAnoMes( Integer anoMesReferencia ) throws ControladorException;    
    
    /**
     * [UC0242] - Registrar Movimento dos Arrecadadores
     *
     * [SF0001] - Validar Arquivo de Movimento de Arrecadador
     *
     * @author Raphael Rossiter
     * @date 10/11/2008
     *
     * @param codigoRegistro
     * @param linha
     * @param codigoArrecadador
     * @param nomeArrecadador
     * @param idTipoMovimento
     * @param arrecadadorContrato
     * @param numeroSequecialArquivoRetornoCodigoBarras
     * @param numeroSequencialArquivoRetornoDebitoAutomatico
     * @param numeroSequencialArquivoEnvioDebitoAutomatico
     * @return RegistroHelperCodigoA
     * @throws ControladorException
     */
    public RegistroHelperCodigoA validarArquivoMovimentoArrecadador(String codigoRegistro, String linha, Short codigoArrecadador,
            String nomeArrecadador, String idTipoMovimento, ArrecadadorContrato arrecadadorContrato,
            Integer numeroSequecialArquivoRetornoCodigoBarras, Integer numeroSequencialArquivoRetornoDebitoAutomatico,
            Integer numeroSequencialArquivoEnvioDebitoAutomatico, Integer idArrecadador) throws ControladorException ;
    
    
    /**
     * [UC0242] - Registrar Movimento dos Arrecadadores
     *
     * [SF0011] - Validar Arquivo de Movimento de Arrecadador da Ficha de Compensação
     *
     * @author Raphael Rossiter
     * @date 10/11/2008
     *
     * @param codigoRegistro
     * @param linha
     * @param codigoArrecadador
     * @param nomeArrecadador
     * @param idTipoMovimento
     * @param arrecadadorContrato
     * @param numeroSequecialArquivoRetornoFichaComp
     * @return RegistroHelperCodigo0
     * @throws ControladorException
     */
    public RegistroHelperCodigo0 validarArquivoMovimentoArrecadadorFichaCompensacao(String codigoRegistro, String linha, 
    		Short codigoArrecadador, String nomeArrecadador, String idTipoMovimento, ArrecadadorContrato arrecadadorContrato,
    		Integer numeroSequecialArquivoRetornoFichaComp, Integer idArrecadador) throws ControladorException ;
    
	/**
	 * [UC0333] Consultar Dados Diários da Arrecadação
	 * 
	 * Método para filtrar os dados diários para qualquer aba da funcionalidade
	 * 
	 * @author Francisco do Nascimento
	 * @date 12/11/2008
	 *
	 * @param filtro
	 * @return Um TreeMap contendo colecoes de dados diarios por ano mes 
	 * @throws ControladorException 
	 */
	public Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> 
		filtrarDadosDiariosArrecadacao(int anoMesInicial, int anoMesFinal, 
		FiltroConsultarDadosDiariosArrecadacao filtro) throws ControladorException;
	
	/**
	 * 
	 * [UC0333] Consultar Dados Diários da Arrecadação
	 *
	 * Verificar se existe dados diarios da arrecadacao de acordo com o filtro 
	 * passado
	 *
	 * @author Francisco do Nascimento
	 * @date 18/11/2008
	 *
	 * @param anoMesInicial Inicio do periodo da consulta
	 * @param anoMesFinal   Final do periodo da consulta
	 * @param filtro   Filtro com os parametros setados para a consulta
	 * @return boolean de existencia dos dados
	 * @throws ControladorException
	 */
	public boolean verificarExistenciaDadosDiariosArrecadacao(int anoMesInicial, int anoMesFinal,
		FiltroConsultarDadosDiariosArrecadacao filtro)
		throws ControladorException;	
	
	/**
	 * Inseri uma coleção de pagamentos no sistema
	 * 
	 * [UC0265] Inserir Pagamentos
	 * 
	 * Pesquisa o débito a cobrar do imóvel informado pelo usuário
	 * 
	 * [FS0024] - Verificar existência do débito a cobrar
	 * 
	 * @author Rômulo Aurélio
	 * @date 30/01/2009
	 * 
	 * @param idImovel
	 * @param idDebitoACobrar
	 * @return
	 * @throws ControladorException
	 */
	 public DebitoACobrarGeral pesquisarDebitoACobrarGeralDigitado(String idImovel,
				String idDebitoACobrar) throws ControladorException;
	 /**
		 * Inseri uma coleção de pagamentos no sistema
		 * 
		 * [UC0265] Inserir Pagamentos
		 * 
		 * Pesquisa o débito a cobrar do imóvel informado pelo usuário
		 * 
		 * [FS0024] - Verificar existência do débito a cobrar
		 * 
		 * @author Rômulo Aurélio
		 * @date 30/01/2009
		 * 
		 * @param idImovel
		 * @param idDebitoACobrar
		 * @return
		 * @throws ControladorException
		 */
	 public void verificarLocalidadeDebitoACobrarGeral(
				DebitoACobrarGeral debitoACobrarGeral, String idLocalidade)
				throws ControladorException;
	 
	 /**
		 * Inseri uma coleção de pagamentos no sistema
		 * 
		 * [UC0265] Inserir Pagamentos
		 * 
		 * Verifica a existência de débito a cobrar com o tipo de débito e o imóvel
		 * informados
		 * 
		 * [FS0016] Verificar existência de débito a cobrar com tipo de débito
		 * informado
		 * 
		 * @author Rômulo Aurélio
		 * @date 30/01/2009
		 * 
		 * @param tipoDebito
		 * @param idImovel
		 * @return
		 * @throws ControladorException
		 */
		public DebitoACobrarGeral verificarExistenciaDebitoACobrarGeralComTipoDebito(
				DebitoTipo tipoDebito, String idImovel, BigDecimal valorInformado) throws ControladorException;
		
		
		/**
		 * Pesquisa os bancos q tem imoveis cadastrados em debito automatico
		 * @return
		 * @throws ErroRepositorioException
		 */
		public Collection<Banco> pesquisarBancoDebitoAutomatico()
			throws ControladorException;
		
		/**
		  * [UC0146] Manter Conta
		  * 
		  * FS0028 - Verificar parâmetro consulta e débito automático
		  * @return
		  * @throws ErroRepositorioException
		  */
			public Collection pesquisarImoveisBancoDebitoAutomatico(String[] bancos)
				throws ControladorException;
			
			public Integer countImoveisBancoDebitoAutomatico(String[] bancos, 
					Integer anoMesInicial,Integer anoMesFinal, Date dataVencimentoInicial,
					Date dataVencimentoFinal, String indicadorContaPaga)
				throws ControladorException;
			
			public Collection selecionarImoveisBancoDebitoAutomatico(String[] bancos, 
					Integer anoMesInicial,Integer anoMesFinal, Date dataVencimentoInicial,
					Date dataVencimentoFinal, String indicadorContaPaga)
				throws ControladorException;
		
	    /**
		 * Consultar os dados do movimento arrecadador 
		 * 
		 * dados[0] = NomeBanco
		 * dados[1] = NomeAgencia
		 * 
		 * @author Arthur Carvalho
		 * @date 03/04/2009
		 * 
		 * @param 
		 * @return Collection
		 * @throws ControladorException
		 */
	    public Collection<Object[]> consultarNomeArrecadadorNomeAgencia(
				String idArrecadadorMovimento ) throws ControladorException;
	    
	    /**
		 * [UC0255] Filtrar Pagamentos
		 * 
		 * Pesquisa os pagamentos do Imovel pesquisarPagamentoImovelParaPaginacao
		 * 
		 * @author Arthur Carvalho
		 * @date 16/12/09
		 * 
		 * @return Collection<Pagamento>
		 * @throws ErroRepositorioException
		 */

		public Integer pesquisarPagamentoCount(String idImovel,
				String idCliente, String idTipoRelacao, String localidadeInicial,
				String localidadeFinal, String idAvisoBancario,
				String idArrecadador, String periodoArrecadacaoInicial,
				String periodoArrecadacaoFinal, String periodoPagamentoInicio,
				String periodoPagamentoFim, Date dataPagamentoInicial,
				Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
				String[] idsDebitosTipos, String[] idsArrecadacaoForma,
				String[] idsDocumentosTipos,
	            String valorPagamentoInicial, 
	            String valorPagamentoFinal) throws ControladorException;
		
		/**
		 * [UC0971] Inserir Pagamentos para Faturas Especiais
		 * [SB0001] Processar fatura com codigo de barras
		 * 
		 * @author 	Vivianne Sousa
		 * @created	22/12/2009
		 *
		 * @param codigoBarras
		 * @param sistemaParametro
		 * @return PagamentoHelperCodigoBarras
		 * @throws ControladorException
		 */
		public PagamentoHelperCodigoBarras processarFaturaComCodigoBarras(
				String codigoBarras, SistemaParametro sistemaParametro) throws ControladorException ;
		
		/**
		 * [UC0971] Inserir Pagamentos para Faturas Especiais
		 * 
		 * @author 	Vivianne Sousa
		 * @created	22/12/2009
		 */
		public Integer inserirPagamentosFaturasEspeciais(Integer idFuncionalidadeIniciada,Map parametros)
				throws ControladorException;
		
		/**
		 * [UC0978] Pesquisa Relatório de Pagamento para Entidades Beneficentes Analítico
		 * 
		 * @author Daniel Alves
		 * @data   26/01/2010
		 * @param  anoMesInicial = periodo inicial do relatorio
		 *         anoMesFinal   = periodo final do relatorio
		 *         idUnidadeBeneficente
		 *         idGerenciaRegional
		 *         idUnidadeNegocio
		 *         idLocalidade
		 */
		public Collection pesquisarPagamentoEntidadesBeneficentesAnalitico(
				String mesAnoInicial, String mesAnoFinal,
				String idEntidadeBeneficente, String idGerenciaRegional,
				String idUnidadeNegocio, String idLocalidade, int opcaoTotalizacao)
				throws ControladorException;
		
		/**
		 * [UC0978] Pesquisa Relatório de Pagamento para Entidades Beneficentes Sintético
		 * 
		 * @author Daniel Alves
		 * @data   26/01/2010
		 * @param  anoMesInicial = periodo inicial do relatorio
		 *         anoMesFinal   = periodo final do relatorio
		 *         idUnidadeBeneficente
		 *         idGerenciaRegional
		 *         idUnidadeNegocio
		 *         idLocalidade
		 */
		public Collection pesquisarPagamentoEntidadesBeneficentesSintetico(
				String mesAnoInicial, String mesAnoFinal,
				String idEntidadeBeneficente, String idGerenciaRegional,
				String idUnidadeNegocio, String idLocalidade, int opcaoTotalizacao)
				throws ControladorException;
		
		/**
		 * [UC0978] Count para Relatório de Pagamento para Entidades Beneficentes
		 * 
		 * @author Daniel Alves
		 * @data   26/01/2010
		 * @param  mesAnoInicial = periodo inicial do relatorio
		 *         mesAnoFinal   = periodo final do relatorio
		 *         idUnidadeBeneficente
		 *         idGerenciaRegional
		 *         idUnidadeNegocio
		 *         idLocalidade
		 *         opcaoTotalizacao
		 *         relatorioTipo = Analitico/Sintetico	 
		 * @throws ControladorException 
		 */ 
		public int pesquisarPagamentoEntidadesBeneficentesCount(String mesAnoInicial, String mesAnoFinal,
				String idEntidadeBeneficente, String idGerenciaRegional, String idUnidadeNegocio, String idLocalidade,
				int opcaoTotalizacao, String relatorioTipo
				) throws ControladorException;
		
		
		/**
		 * [UC0927] – Confirmar Cartão de Crédito/Débito
		 *
		 * @author Raphael Rossiter
		 * @date 18/01/2010
		 *
		 * @param codigoAgente
		 * @return Integer
		 * @throws ControladorException
		 */
		public Integer pesquisarContaBancaria(Short codigoAgente) throws ControladorException ;
		
		/**
		 * [UC0977] - Registrar Movimento Cartão de Crédito
		 * 
		 * [FS0001 – Verificar existência do arrecadador]
		 * [FS0002 – Verificar arrecadação forma cartão crédito]
		 *
		 * @author Raphael Rossiter
		 * @date 26/01/2010
		 *
		 * @param idArrecadador
		 * @return Arrecadador
		 * @throws ControladorException
		 */
		public Arrecadador verificarArrecadacaoFormaCartaoCredito(Integer idArrecadador) 
			throws ControladorException ;
		
		/**
		 * [UC0977] - Registrar Movimento Cartão de Crédito
		 *
		 * @author Raphael Rossiter
		 * @date 29/01/2010
		 *
		 * @param arrecadador
		 * @param stringBuilderTxt
		 * @param quantidadeRegistros
		 * @param usuarioLogado
		 * @throws ControladorException
		 */
		public void registrarMovimentoCartaoCredito(Arrecadador arrecadador, StringBuilder stringBuilderTxt,
				int quantidadeRegistros, Usuario usuarioLogado) throws ControladorException ;
				
		/**
		 * [UC0259] - Processar Pagamento com código de Barras
		 * 
		 * [SB0012] - Verifica Pagamento de Debito a Cobrar de Parcelamento 
		 *
		 * @author Vivianne Sousa, Raphael Rossiter
		 * @date 19/07/2007, 12/04/2010
		 *
		 * @param idDebitoACobrar
		 * @param numeroParcelasAntecipadas
		 * @throws ControladorException
		 */
		public void verificaPagamentoDebitoACobrarParcelamento(Integer idDebitoACobrar, Integer numeroParcelasAntecipadas) 
			throws ControladorException ;
		
		/**
		 * [UC0262] - Distribuir dados do Registro de Movimento do Arrecadador
		 * Autor: Sávio Luiz Data: 30/01/2006
		 * 
		 * Caso a descrição de Ocorrencia venha nula então recupera o código
		 * registro da linha senão então seta o valor de código registro para 'C'
		 */
		public Object distribuirdadosRegistroMovimentoArrecadador(String linha,
				String descricaoOcorrencia) throws ControladorException;
				
		/**
		 * [UC0259] - Processar Pagamento com código de Barras
		 * 
		 * [SB0019] – Gerar Débitos/Créditos Parcelas Antecipadas 
		 *
		 * @author Raphael Rossiter
		 * @date 12/04/2010
		 *
		 * @param idDebitoACobrar
		 * @param numeroParcelasAntecipadas
		 * @throws ControladorException
		 */
		public Object gerarDebitoCreditoParcelasAntecipadas(Integer idImovel, CobrancaDocumentoItem cobrancaDocumentoItem, 
				Usuario usuarioLogado) throws ControladorException ;
		
		/**
		 * [UC0259] – Processar Pagamento com Código de Barras
		 * 
		 * [SB0019] – Gerar Débitos/Créditos Parcelas Antecipadas.
		 *
		 * @author Raphael Rossiter
		 * @date 19/04/2010
		 *
		 * @param idParcelamento
		 * @return
		 * @throws ControladorException
		 */
		public DebitoACobrar pesquisarDebitoACobrarJurosParcelamento(Integer idParcelamento) 
		throws ControladorException ;
		
		/**
		 * [UC0259] – Processar Pagamento com Código de Barras
		 * 
		 * [SB0019] – Gerar Débitos/Créditos Parcelas Antecipadas. 
		 *
		 * @author Raphael Rossiter
		 * @date 19/04/2010
		 *
		 * @param debitoACobrar
		 * @throws ControladorException
		 */
		public void atualizarNumeroParcelasBonus(DebitoACobrar debitoACobrar) 
		throws ControladorException ;
		
		/**
		 * [UC0255] Filtrar Pagamentos
		 * 
		 * Pesquisa os pagamentos do tipo Aviso Bancario
		 * pesquisarPagamentoAvisoBancario historico
		 * 
		 * @author Arthur Carvalho
		 * @date 12/05/10
		 * 
		 * @return Collection<Pagamento>
		 * @throws ErroRepositorioException
		 */
		public Integer pesquisarPagamentoHistoricoAvisoBancarioCount(String idImovel,
				String idCliente, String idTipoRelacao, String localidadeInicial,
				String localidadeFinal, String idAvisoBancario,
				String idArrecadador, String periodoArrecadacaoInicial,
				String periodoArrecadacaoFinal, String periodoPagamentoInicio,
				String periodoPagamentoFim, Date dataPagamentoInicial,
				Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
				String[] idsDebitosTipos, String[] idsArrecadacaoForma,
				String[] idsDocumentosTipos,
	            String valorPagamentoInicial, 
	            String valorPagamentoFinal ) throws ControladorException;
		
		/**
		 * [UC0322] Inserir Guia Devolucao.
		 * 		[FS0023] Verificar crédito a realizar. –Verificarasds 
		 *
		 * [UC0194] Inserir Crédito a realizar.
		 * 		[FS0013] Verificar crédito a realizar. 
		 *
		 * @author Hugo Leonardo
		 * @date 26/05/2010
		 *
		 * @param idImovel, anoMesReferenciaConta
		 * @throws ControladorException
		 */
		public Integer verificarExistenciaCreditoARealizar(Integer idImovel, Integer anoMesReferenciaConta)
			throws ControladorException;
		
		/**
		 * [UC0322] Inserir Guia Devolucao.
		 * 		[FS0023] Verificar crédito a realizar histórico.–Verificara
		 *
		 *	[UC0194] Inserir Crédito a realizar.
		 * 		[FS0013] Verificar crédito a realizar histórico.
		 *
		 * @author Hugo Leonardo
		 * @date 26/05/2010
		 *
		 * @param idImovel, anoMesReferenciaConta
		 * @throws ControladorException
		 */
		public Integer verificarExistenciaCreditoARealizarHistorico(Integer idImovel, Integer anoMesReferenciaConta)
			throws ControladorException;
		
		/**
		 * [UC0194] Inserir Crédito a realizar.
		 * 		[FS0013] Verificar Guia devolução. –Verificarasds 
		 *
		 * @author Hugo Leonardo
		 * @date 27/05/2010
		 *
		 * @param idImovel, anoMesReferenciaGuiaDevolucao
		 * @throws ControladorException
		 */
		public Integer verificarExistenciaGuiaDevolucao(Integer idImovel, Integer anoMesReferenciaGuiaDevolucao)
			throws ControladorException;
		
		/**
		 * [UC0629] Consultar Arquivo Texto para Leitura
		 *
		 * @author Raphael Rossiter
		 * @date 29/06/2010
		 *
		 * @param helper
		 * @return Integer
		 * @throws ErroRepositorioException
		 */
		public Integer filtrarArquivoTextoRoteiroEmpresaCount(ConsultarArquivoTextoRoteiroEmpresaHelper helper)
			throws ControladorException ;
		
		/**
		 * [UC0629] Consultar Arquivo Texto para Leitura 
		 *
		 * @author Raphael Rossiter
		 * @date 29/06/2010
		 *
		 * @param helper
		 * @param numeroPagina
		 * @return Collection
		 * @throws ControladorException
		 */
		public Collection filtrarArquivoTextoRoteiroEmpresaParaPaginacao(ConsultarArquivoTextoRoteiroEmpresaHelper helper, 
				Integer numeroPagina) throws ControladorException ;
		
		/**
		 * [UC0339] Consultar Dados Diários da Arrecadação
		 *
		 * @author Hugo Amorim
		 * @date 29/06/2010
		 *
		 * @throws ControladorException
		 */
		public Date pesquisarDataProcessamentoMes(Integer anoMes) throws ControladorException;
		
		/**
		 * [UC0322] Inserir Guia de Devolução
		 *
		 *
		 * @author Fernando Fontelles
		 * @date 21/07/2010
		 *
		 * @param colecaoConta
		 */
		public ContaGeral retornaContaGeral(Collection colecaoConta)
			throws ControladorException ;
		
		/**
		 * 
		 * [UC1043] Gerar Relatório Análise Pagamento Cartão Débito
		 * 
		 * 			-Validações
		 * 
		 * @author Hugo Amorim
		 * @since 21/07/2010
		 *
		 */
		public void validarGerarRelatorioAnalisePagamentoCartaoDebito(
				ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper helper) throws ControladorException;
		
		/**
		 * [UC1043] Gerar Relatório Análise Pagamento Cartão Débito
		 *
		 * @author Hugo Amorim
		 * @date 21/06/2010
		 *
		 * @throws ErroRepositorioException
		 */
		public Integer relatorioAnalisePagamentoCartaoDebitoCount(
				ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper helper) 
				throws ControladorException;
		
		/**
		 * 
		 * [UC1043] Gerar Relatório Análise Pagamento Cartão Débito
		 * 
		 * 			-Pesquisa
		 * 
		 * @author Hugo Amorim
		 * @since 21/07/2010
		 *
		 */
		public List<RelatorioAnalisePagamentoCartaoDebitoBean> 
			pesquisarBeansRelatorioAnalisePagamentoCartaoDebito(
				ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper helper)
					throws ControladorException;
		
		
		/**
	     * [UC0724] - Processar Pagamento com Ficha de Compensação
	     * 
	     * Autor: Vivianne Sousa
	     * Data: 26/11/2007
	     */
	    public PagamentoHelperCodigoBarras processarPagamentosFichaCompensacao(
	            SistemaParametro sistemaParametro, Date dataPagamento, 
	            BigDecimal valorPagamento, String nossoNumero, Integer idFormaArrecadacao, 
	            Usuario usuarioLogado) throws ControladorException ;
	    
	    /**
		 * [UC0265] Inserir Pagamentos 
		 *
		 * @author Raphael Rossiter
		 * @date 30/11/2010
		 *
		 * @param codigoBarras
		 * @return RegistroHelperFichaCompensacao
		 * @throws ControladorException
		 */
		public RegistroHelperFichaCompensacao distribuirDadosFichaCompensacao(String codigoBarras) throws ControladorException;

		/**
		 * [UC0339] Consultar Dados Diários da Arrecadação
		 *
		 * @author Mariana Victor
		 * @date 01/02/2011
		 *
		 * @throws ErroRepositorioException
		 */
		public BigDecimal pesquisarFaturamentoCobradoEmConta(Integer anoMes) throws ControladorException;
		
		/**
		 * [UC0339] Consultar Dados Diários da Arrecadação
		 *
		 * @author Arthur Carvalho
		 * @date 22/03/2011
		 *
		 * @throws ErroRepositorioException
		 */
		public BigDecimal pesquisarFaturamentoCobradoEmContaComQuebra(Integer anoMes, Integer idGerenciaRegional, 
				Integer idCategoria) throws ControladorException;

		/**
		 * [UC0188] Manter Guia de Pagamento
		 *
		 * [FS0019] – Verificar bloqueio de guia de pagamento
		 * 
		 * @author Mariana Victor
		 * @date 27/04/2011
		 *
		 * @throws ErroRepositorioException
		 */
		public Collection<Integer> verificarBloqueioGuiaPagamento(Collection<GuiaPagamento> guiasPagamentos) 
			throws ControladorException;
		
		/**
		 * [UC 1215] – Gerar Relatório de Documentos não Aceitos
		 * 
		 * @author Raimundo Martins
		 *
		 * @date 19/08/2011
		 */
		public List<RelatorioDocumentoNaoAceitosBean> pesquisarDocumentosNaoAceitos(Arrecadador arrecadador, 
				String periodoInicial, String periodoFinal, Integer movimentoArrecadadorCodigo, 
				AvisoBancario avisoBancario, ArrecadacaoForma arrecadacaoForma) throws ControladorException;
		
		/**
		 * [UC 1217] – Gerar Relatório de Transferencia de Pagamento
		 * 
		 * @author Raimundo Martins
		 *
		 * @date 19/08/2011
		 */
		public List<RelatorioTranferenciaPagamentoBean> pesquisarTransfereciasPagamento(Arrecadador arrecadador, 
				String periodoInicial, String periodoFinal, AvisoBancario avisoBancario, ArrecadacaoForma arrecadacaoForma, 
				DebitoTipo debitoTipo, DocumentoTipo documentoTipo) throws ControladorException;


		/**
	     * [UC1214] Informar Acerto Documentos Não Aceitos
		 * 
		 * 3. O sistema identifica os pagamentos com documentos não aceitos 
		 * 	 que foram gerados para um cliente fictício e
		 *   junto com o filtro selecionado pelo usuário.
		 * 
		 * @author Mariana Victor
		 * @date 19/08/2011
		 * 
		 * @param codigoConstante
		 * 
		 * @return Collection
		 * @throws ErroRepositorioException
		 */
		public Collection<PagamentoDocumentosNaoAceitosHelper> pesquisarPagamentosDocumentosNaoAceitos(
				InformarAcertoDocumentosNaoAceitosPagamentoHelper helper) throws ControladorException;
		
		/**
	     * [UC1214] Informar Acerto Documentos Não Aceitos
		 * 
		 *  7.2.1. Total do Pagamento (PGMT _VLPAGAMENTO do pagamento doc. não aceito).
		 * 
		 * @author Mariana Victor
		 * @date 22/08/2011
		 * 
		 * @param idPagamento
		 * 
		 * @return BigDecimal
		 * @throws ErroRepositorioException
		 */
		public BigDecimal pesquisarValorPagamento(
				Integer idPagamento) throws ControladorException;

		/**
		 * [UC1214] Informar Acerto Documentos Não Aceitos
		 * 
		 * Pesquisa a guia de pagamento do imóvel informado pelo usuário
		 * 
		 * [FS0009] ? Verificar existência da guia de pagamento
		 * 
		 * @author Mariana Victor
		 * @date 23/08/2011
		 * 
		 * @param idImovel
		 * @param idGuiaPagamento
		 * @return
		 * @throws ControladorException 
		 */
		public GuiaPagamentoValoresHelper pesquisarGuiaPagamentoDigitada(String idImovel, 
				String idGuiaPagamento) throws ControladorException;
		
		
		/**
		 * [UC1214] Informar Acerto Documentos Não Aceitos
		 * 
		 * Pesquisa o débito a cobrar do imóvel informado pelo usuário
		 * 
		 * [FS0012] - Verificar existência do débito.
		 * 
		 * @author Mariana Victor
		 * @date 23/08/2011
		 * 
		 * @param idImovel
		 * @param idDebitoACobrar
		 * @return
		 * @throws ControladorException
		 */
		public DebitoACobrarValoresHelper pesquisarDebitoACobrarImovelDigitado(String idImovel,
				String idDebitoACobrar) throws ControladorException;
		
		/**
		 * [UC1214] Informar Acerto Documentos Não Aceitos
		 * 
		 * 10.	O usuário conclui o acerto dos pagamentos 
		 * 
		 * @author Mariana Victor
		 * @date 24/08/2011
		 * 
		 * @param helper
		 * @return
		 * @throws ControladorException
		 */
		public void efetuarAcertosPagamentos(InformarAcertoDocumentosNaoAceitosHelper helper)
			throws ControladorException;
		
		/**
	     * [UC1214] Informar Acerto Documentos Não Aceitos
		 * 
		 * @author Mariana Victor
		 * @date 24/08/2011
		 * 
		 * @param idGuia
		 * 
		 * @return Integer
		 * @throws ControladorException 
		 */
		public Integer pesquisarLocalidadeGuiaPagamento(
				Integer idGuia) throws ControladorException;
		
		/**
	     * [UC1214] Informar Acerto Documentos Não Aceitos
		 * 
		 * @author Mariana Victor
		 * @date 24/08/2011
		 * 
		 * @param idConta
		 * 
		 * @return Integer
		 * @throws ControladorException
		 */
		public Integer pesquisarLocalidadeConta(
				Integer idConta) throws ControladorException;
		
		/**
	     * [UC1214] Informar Acerto Documentos Não Aceitos
		 * 
		 * @author Mariana Victor
		 * @date 24/08/2011
		 * 
		 * @param idDebitoACobrar
		 * 
		 * @return Integer
		 * @throws ControladorException
		 */
		public Integer pesquisarLocalidadeDebitoACobrar(
				Integer idDebitoACobrar) throws ControladorException;
		
		/**
		 * Filtrar a quantidade de pagamento historicos do cliente
		 * 
		 * [UC0255] Filtrar Pagamentos
		 * 
		 * Pesquisa os pagamentos do Cliente
		 * 
		 * @author Rodrigo Cabral
		 * @date 15/09/11
		 * 
		 * @return Collection<Pagamento>
		 * @throws ErroRepositorioException
		 */
		public Integer pesquisarPagamentoHistoricoClienteCount(String idImovel,
				String idCliente, String idTipoRelacao, String localidadeInicial,
				String localidadeFinal, String idAvisoBancario,
				String idArrecadador, String periodoArrecadacaoInicial,
				String periodoArrecadacaoFinal, String periodoPagamentoInicio,
				String periodoPagamentoFim, Date dataPagamentoInicial,
				Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
				String[] idsDebitosTipos, String[] idsArrecadacaoForma,
				String[] idsDocumentosTipos, String valorPagamentoInicial, String valorPagamentoFinal) throws ControladorException;

		
		/**
		 * 
		 * Este caso de uso cria um sql que será usado na pesquisa de pagamentos
		 * para o Relatório
		 * 
		 * @author Rodrigo Cabral
		 * @date 16/09/2011
		 * 
		 * @param FiltroPagamento
		 * @return Collection
		 * @throws ControladorException
		 */

		public Collection pesquisarPagamentoHistoricoClienteRelatorio(String idImovel,
				String idCliente, String idTipoRelacao, String localidadeInicial,
				String localidadeFinal, String idAvisoBancario,
				String idArrecadador, String periodoArrecadacaoInicial,
				String periodoArrecadacaoFinal, String periodoPagamentoInicio,
				String periodoPagamentoFim, Date dataPagamentoInicial,
				Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
				String[] idsDebitosTipos, String[] idsArrecadacaoForma,
				String[] idsDocumentosTipos,
	            String valorPagamentoInicial,
	            String valorPagamentoFinal) throws ControladorException;
		
		/*TODO: COSANPA
		 * Criado para a consulta, de manter conta por conjunto de imóveis, pelo id do banco
		 * e pelo grupo de faturamento
		 * 
		 * */
		
		/** @author Adriana Muniz
		  * @date: 08/04/2011
		  * [UC0146] Manter Conta(por conjunto de imóveis)
		  * 
		  * FS0028 - Verificar parâmetro consulta e débito automático
		  * @return
		  * @throws ErroRepositorioException
		  */
		public Collection pesquisarImoveisBancoDebitoAutomaticoEPorGrupoFaturamento(String[] bancos, Integer idGrupoFaturamento)
			throws ControladorException;
		
		/* TODO: COSANPA
		 * autor: Adriana Muniz
		 * 
		 * Alteração para a conta considerar como filtro grupo de faturamento, quando o mesmo estiver preenchido
		 */
		/**
		 * @autor: Adriana Muniz
		 * @date: 27/04/2011
		 * 
		 * [UC0146] Manter Conta
		 * 
		 * FS0028 - Verificar parâmetro consulta e débito automático
		 * 
		 * Método para retornar a quantidade de contas a partir dos imóveis com débito automático - 
		 * Manter Contas de um Conjunto de imóveis. 
		 * 
		 * */
		public Integer countImoveisBancoDebitoAutomaticoPorGrupoFaturamento(String[] bancos, 
				Integer anoMesInicial,Integer anoMesFinal, Date dataVencimentoInicial,
				Date dataVencimentoFinal, String indicadorContaPaga, Integer idGrupoFaturamento)
			throws ControladorException;
		

		/**
		 * TODO: COSANPA
		 * 
		 * Mantis 537
		 * 
		 * @author Wellington Rocha
		 * @data 15/03/2012
		 * 
		 * @param idConta
		 * @return pagamento
		 */
		public PagamentoHistorico pesquisarPagamentoDeContaEmHistorico(Integer idConta)
				throws ControladorException;
		

		/**TODO:COSANPA
		 * @date:03/10/2012
		 * @autor Adriana Muniz
		 * 
		 * Consultar Dados Diários da Arrecadação por formas de arrecadação com tarifa 
		 * 
		 * @param filtro
		 * @return Um TreeMap contendo colecoes de dados diarios por ano mes
		 * @throws ControladorException
		 */
		public Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> 
		filtrarDadosDiariosFormasArrecadacaoComTarifa(int anoMesInicial, int anoMesFinal, 
			FiltroConsultarDadosDiariosArrecadacao filtro) throws ControladorException;
		

		/**TODO:COSANPA
		 * @author Adriana Muniz
		 * @date 10/12/2012
		 * 
		 * Consultar Dados Diários da Arrecadação da tabela arrecadacao_dados_diarios_auxiliar
		 *
		 * Verificar se existe dados diarios da arrecadacao de acordo com o filtro 
		 * passado
		 *
		 *
		 * @param filtro
		 * @return boolean de existencia dos dados
		 * @throws ControladorException
		 */
		public boolean verificarExistenciaDadosDiariosArrecadacaoAuxiliar(int anoMesInicial, int anoMesFinal,
			FiltroConsultarDadosDiariosArrecadacaoAuxiliar filtro) throws ControladorException;
		
		/**TODO:COSANPA
		 * data: 11/12/2012
		 * 
		 * Recupera os dados diarios da arrecadação auxiliar
		 * 
		 * @param anoMesInicial
		 * @param anoMesFinal
		 * @param filtro
		 * @return
		 * @throws ControladorException
		 */
		public Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> 
			filtrarDadosDiariosArrecadacaoAuxiliar(int anoMesInicial, int anoMesFinal, 
					FiltroConsultarDadosDiariosArrecadacaoAuxiliar filtro) throws ControladorException;
		
		/**
		 * TODO:COSANPA
		 * @author Adriana Muniz 
		 * @data: 19/12/2012
		 * 
		 * Consulta para retornar os dados diários arrecadação auxiliar
		 * agrupados por formas de arrecadação com tarifa
		 * 
		 * @param anoMesInicial
		 * @param anoMesFinal
		 * @param filtro
		 * @return
		 * @throws ControladorException
		 */
		public Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> filtrarDadosDiariosArrecadacaoAuxiliarFormasArrecadacaoComTarifa(
				int anoMesInicial, int anoMesFinal,
				FiltroConsultarDadosDiariosArrecadacaoAuxiliar filtro)
				throws ControladorException;
		
		/**
		 * COSANPA - Mantis 414 - Felipe Santos - 25/10/2013
		 * 
		 * Gera dados para o Relatorio BIG
		 * 
		 * @param localidades
		 * @param idFuncionalidadeIniciada
		 */
		public void gerarDadosRelatorioBIG(Integer anoMesReferencia, Localidade localidade,
				Integer idFuncionalidadeIniciada) throws ControladorException;
		
		/**
		 * COSANPA - Mantis 414 - Felipe Santos - 08/11/2013
		 * 
		 * Returna dados do Relatorio BIG
		 * 
		 * @param anoMesReferencia
		 */
		public Collection pesquisarDadosRelatorioBIG(
				Integer anoMesReferencia) throws ControladorException;
		
		public Collection<GuiaPagamento> obterGuiasPagamentoNaoPagasAtePeriodo(Integer idFuncionalidadeIniciada, Date dataVencimentoLimite, 
				Integer financiamentoTipoServico, Integer idLocalidade) throws ControladorException;
				
		public Collection<Integer> pesquisarIdsLocalidadeComGuiasPagamentoNaoPagas(Date dataVencimentoLimite, 
				Integer financiamentoTipoServico) throws ControladorException;
		
		public void processarPagamentosDiferencaDoisReais(Integer anoMesReferenciaArrecadacao, Localidade localidade, Integer idFuncionalidadeIniciada) throws Exception;

}	

