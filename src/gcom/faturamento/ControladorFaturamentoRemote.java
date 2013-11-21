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

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.bean.CalcularValoresAguaEsgotoHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.ConsumoTarifaCategoria;
import gcom.faturamento.consumotarifa.ConsumoTarifaFaixa;
import gcom.faturamento.consumotarifa.ConsumoTarifaVigencia;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaMotivoCancelamento;
import gcom.faturamento.conta.ContaMotivoInclusao;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.micromedicao.Rota;
import gcom.relatorio.faturamento.FiltrarRelatorioJurosMultasDebitosCanceladosHelper;
import gcom.relatorio.faturamento.RelatorioJurosMultasDebitosCanceladosHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * < <Descrição da Interface>>
 * 
 * @author Administrador
 */
public interface ControladorFaturamentoRemote extends javax.ejb.EJBObject {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param faturamentoAtividadeCronogramas
	 *            Descrição do parâmetro
	 * @param faturamentoGrupoCronogramaMensal
	 *            Descrição do parâmetro
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public void inserirFaturamentoCronograma(
			Collection faturamentoAtividadeCronogramas,
			FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal)
			throws RemoteException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param faturamentoGrupoCronogramaMensal
	 *            Descrição do parâmetro
	 * @param faturamentoAtividadeCronogramas
	 *            Descrição do parâmetro
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public void inserirFaturamentoGrupoCronogramaMensal(
			FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal,
			Collection faturamentoAtividadeCronogramas) throws RemoteException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param faturamentoGrupoCronogramaMensal
	 *            Descrição do parâmetro
	 * @param faturamentoAtividadeCronogramas
	 *            Descrição do parâmetro
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public void atualizarFaturamentoGrupoCronogramaMensal(
			FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal,
			Collection faturamentoAtividadeCronogramas) throws RemoteException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param faturamentoGrupo
	 * @return um boleano que identifica a existência do cronograma para o grupo
	 */
	public void verificarExistenciaCronogramaGrupo(
			FaturamentoGrupo faturamentoGrupo) throws RemoteException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param faturamentoGrupo
	 * @return um boleano que identifica a existência do cronograma para o grupo
	 */
	public boolean verificarExistenciaCronogramaAtividadeGrupo(
			FaturamentoAtividade faturamentoAtividade,
			FaturamentoGrupo faturamentoGrupo) throws RemoteException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param faturamentoGrupo
	 * @return um boleano que identifica a existência do cronograma para o grupo
	 */
	public Collection selecionarAtividadeFaturamentoQuePodeSerComandada(
			FaturamentoGrupo faturamentoGrupo) throws RemoteException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param faturamentoGrupo
	 * @return uma coleção de rotas pertencentes ao grupo selecionado
	 */
	public Collection verificarExistenciaRotaGrupo(
			FaturamentoGrupo faturamentoGrupo) throws RemoteException;

	/**
	 * 
	 * @param colecaoRotasGrupo
	 * @param faturamentoAtividade
	 * @param faturamentoGrupo
	 * @return
	 * @throws RemoteException
	 */
	public Collection verificarSituacaoAtividadeRota(
			Collection colecaoRotasGrupo,
			FaturamentoAtividade faturamentoAtividade,
			FaturamentoGrupo faturamentoGrupo, boolean habilitada)
			throws RemoteException;

	public void inserirComandoAtividadeFaturamento(
			FaturamentoGrupo faturamentoGrupo,
			FaturamentoAtividade faturamentoAtividade, Collection colecaoRotas,
			Date dataVencimentoGrupo) throws RemoteException;

	/**
	 * [UC0104] Manter Comando Atividade de Faturamento
	 * 
	 * Retorna uma lista de atividades de faturamento comandadas e ainda não
	 * realizadas
	 * 
	 */
	public Collection buscarAtividadeComandadaNaoRealizada(
			FiltroFaturamentoAtividadeCronograma filtroFaturamentoAtividadeCronograma)
			throws RemoteException;

	/**
	 * [UC0104] Manter Comando Atividade de Faturamento
	 * 
	 * [SB0002] - Excluir Comando de Atividade de Faturamento
	 * 
	 */
	public void removerComandoAtividadeFaturamento(String[] ids)
			throws RemoteException;

	/**
	 * [UC0104] Manter Comando Atividade de Faturamento
	 * 
	 * Atualizar Comando de Atividade de Faturamento
	 * 
	 */
	public void atualizarComandoAtividadeFaturamento(
			FaturamentoAtividadeCronograma faturamentoAtividadeCronograma,
			Collection colecaoFaturamentoAtividadeCronogramaRota)
			throws RemoteException;
	
	/**
	 * 
	 * [UC0120 - Calcular Valores de Água e/ou Esgoto]
	 * 
	 * @param anoMesReferencia
	 * @param ligacaoSituacaoAguaId
	 * @param ligacaoSituacaoEsgotoId
	 * @param indicadorFaturamentoAgua
	 * @param indicadorFaturamentoEsgoto
	 * @param categoriasImovel
	 * @param consumoFaturadoAguaMes
	 * @param consumoFaturadoEsgotoMes
	 * @param consumoMinimoLigacao
	 * @param dataLeituraAnterior
	 * @param dataLeituraAtual
	 * @param percentualEsgoto
	 * @param tarifaImovel
	 * @return
	 * @throws RemoteException
	 */
	public Collection<CalcularValoresAguaEsgotoHelper> calcularValoresAguaEsgoto(Integer anoMesReferencia,
			Integer ligacaoSituacaoAguaId, Integer ligacaoSituacaoEsgotoId,
			Short indicadorFaturamentoAgua, Short indicadorFaturamentoEsgoto,
			Collection categoriasImovel, Integer consumoFaturadoAguaMes,
			Integer consumoFaturadoEsgotoMes, int consumoMinimoLigacao,
			Date dataLeituraAnterior, Date dataLeituraAtual,
			BigDecimal percentualEsgoto, Integer tarifaImovel) throws RemoteException;
	
	/**
	 * 
	 * @param imovel
	 * @param situacao
	 * @return
	 * @throws RemoteException
	 */
	public Date buscarDataLeituraCronograma(Imovel imovel, boolean situacao, Integer anoMesReferencia) throws RemoteException;
	
	
	/**
	 * Calcula os valores da conta de acordo com os parâmetros passados
	 * 
	 * [UC0145] - Inserir Conta
	 * [SF0001] - Determinar Valores para Faturamento de Água e/ou Esgoto
	 * Author: Raphael Rossiter
	 * 05/12/2005
	 * @param mesAnoConta
	 * @param imovelID
	 * @param situacaoAguaConta
	 * @param situacaoEsgotoConta
	 * @param colecaoCategoria
	 * @param consumoAgua
	 * @param consumoEsgoto
	 * @param percentualEsgoto
	 * @return Collection<CalcularValoresAguaEsgotoHelper>
	 * @throws ControladorException
	 */
	public Collection<CalcularValoresAguaEsgotoHelper> calcularValoresConta(String mesAnoConta, String imovelID,
			Integer situacaoAguaConta, Integer situacaoEsgotoConta, Collection colecaoCategoria, 
			String consumoAgua, String consumoEsgoto, String percentualEsgoto) throws RemoteException;
	
	
	/**
	 * Cálcula o valor total dos débitos de uma conta de acordo com o informado pelo usuário
	 * 
	 * [UC0145] - Inserir Conta
	 * Author: Raphael Rossiter
	 * Data: 10/01/2006
	 * @param colecaoDebitoCobrado
	 * @param requestMap
	 * @return BigDecimal valorTotalDebitoConta
	 * @throws ControladorException
	 */
	public BigDecimal calcularValorTotalDebitoConta(Collection<DebitoCobrado> colecaoDebitoCobrado, 
			Map<String, String[]> requestMap) throws RemoteException ;
	
	/**
	 * [UC0150] - Retificar Conta Author: Raphael Rossiter Data: 10/01/2006
	 * Cálcula o valor total dos créditos de uma conta de acordo com o informado
	 * pelo usuário
	 * 
	 * @param colecaoCreditoRealizado
	 * @param requestMap
	 * @return BigDecimal valorTotalCreditoConta
	 * @throws ControladorException
	 */
	public BigDecimal calcularValorTotalCreditoConta(
			Collection<CreditoRealizado> colecaoCreditoRealizado,
			Map<String, String[]> requestMap) throws RemoteException;
	
	
	/**
	 * [UC0145] - Inserir Conta
	 * Author: Raphael Rossiter
	 * 05/12/2005 
	 * @param mesAnoConta
	 * @param imovel
	 * @param colecaoDebitoCobrado
	 * @param ligacaoAguaSituacao
	 * @param ligacaoEsgotoSituacao
	 * @param colecaoCategoria
	 * @param consumoAgua
	 * @param consumoEsgoto
	 * @param percentualEsgoto
	 * @param dataVencimentoConta
	 * @param calcularValoresConta
	 * @param motivoInclusaoConta
	 * @throws ControladorException
	 */
	public void inserirConta(Integer mesAnoConta, Imovel imovel, Collection colecaoDebitoCobrado, 
			LigacaoAguaSituacao ligacaoAguaSituacao, LigacaoEsgotoSituacao ligacaoEsgotoSituacao,
			Collection colecaoCategoria, String consumoAgua, String consumoEsgoto, String percentualEsgoto,
			Date dataVencimentoConta, Collection<CalcularValoresAguaEsgotoHelper> calcularValoresConta, 
			ContaMotivoInclusao contaMotivoInclusao, Map<String, String[]> requestMap) throws RemoteException;

	
	/**
	 * [UC0147] - Cancelar Conta
	 * Author: Raphael Rossiter
	 * Data: 10/12/2005
	 * @param colecaoContas - coleção com todas as contas do imóvel
	 * @param identificadores - identifica através do ID, quais as contas que serão canceladas
	 * @param motivoCancelamentoConta - motivo do cancelamento escolhido pelo usuário
	 */
	public void cancelarConta (Collection<Conta> colecaoContas, String identificadores,
			ContaMotivoCancelamento contaMotivoCancelamento) throws RemoteException;
	
	
	/**
	 * [UC0148] - Colocar Conta em Revisão
	 * Author: Raphael Rossiter
	 * Data: 21/12/2005
	 * @param colecaoContas - coleção com todas as contas do imóvel
	 * @param identificadores - identifica através do ID, quais as contas que serão colocadas em revisão
	 * @param motivoRevisãoConta - motivo da revisão escolhida pelo usuário
	 */
	public void colocarRevisaoConta (Collection<Conta> colecaoContas, String identificadores,
			ContaMotivoRevisao contaMotivoRevisao) throws RemoteException;
	
	
	/**
	 * [UC0149] - Retirar Conta em Revisão
	 * Author: Raphael Rossiter
	 * Data: 22/12/2005
	 * @param colecaoContas - coleção com todas as contas do imóvel
	 * @param identificadores - identifica através do ID, quais as contas que serão retiradas de revisão
	 */
	public void retirarRevisaoConta (Collection<Conta> colecaoContas, String identificadores) 
		throws RemoteException;
	
	
	/**
	 * [UC0151] - Alterar Vencimento de Conta
	 * Author: Raphael Rossiter
	 * Data: 22/12/2005
	 * @param colecaoContas - coleção com todas as contas do imóvel
	 * @param identificadores - identifica através do ID, quais as contas que sofrerão alteração na sua data de vencimento
	 * @param dataVencimento - a nova data de vencimento da conta
	 */
	public void alterarVencimentoConta (Collection<Conta> colecaoContas, String identificadores, Date dataVencimento) 
		throws RemoteException;
	
	
	/**
	 * 
	 * @param mesAnoConta
	 * @param contaAtual
	 * @param imovel
	 * @param colecaoDebitoCobrado
	 * @param colecaoCreditoRealizado
	 * @param ligacaoAguaSituacao
	 * @param ligacaoEsgotoSituacao
	 * @param colecaoCategoria
	 * @param consumoAgua
	 * @param consumoEsgoto
	 * @param percentualEsgoto
	 * @param dataVencimentoConta
	 * @param calcularValoresConta
	 * @param contaMotivoRetificacao
	 * @param requestMap
	 * @throws ControladorException
	 */
	public void retificarConta(Integer mesAnoConta, Conta contaAtual,
			Imovel imovel,
			Collection colecaoDebitoCobrado,
			Collection colecaoCreditoRealizado,
			LigacaoAguaSituacao ligacaoAguaSituacao,
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao,
			Collection colecaoCategoria, String consumoAgua,
			String consumoEsgoto, String percentualEsgoto,
			Date dataVencimentoConta,
			Collection<CalcularValoresAguaEsgotoHelper> calcularValoresConta,
			ContaMotivoRetificacao contaMotivoRetificacao, Map<String, String[]> requestMap)
			throws RemoteException;
	
	
	/**
	 * [UC0150] - Retificar Conta
	 * Author: Raphael Rossiter
	 * Data: 26/12/2005
	 * @param conta
	 * @return uma coleção com os débitos cobrados de uma conta
	 * @throws ControladorException
	 */
	public Collection<DebitoCobrado> obterDebitosCobradosConta(Conta conta) throws RemoteException;
	
	
	/**
	 * [UC0150] - Retificar Conta
	 * Author: Raphael Rossiter
	 * Data: 28/12/2005
	 * @param conta
	 * @return uma coleção com os créditos realizados de uma conta
	 * @throws ControladorException
	 */
	public Collection<CreditoRealizado> obterCreditosRealizadosConta(Conta conta) throws RemoteException;
	
	/**
	 * [UC0168] - Inserir Tarifa de Consumo
	 * 
	 * @param consumoTarifa
	 * @param consumoTarifaVigencia
	 * @param colecaoConsumoTarifaCategoria
	 * @param colecaoConsumoTarifaFaixa
	 * @throws ControladorException
	 */
	public void inserirConsumoTarifa(ConsumoTarifa consumoTarifa,
			ConsumoTarifaVigencia consumoTarifaVigencia,
			Collection<ConsumoTarifaCategoria> colecaoConsumoTarifaCategoria,
			Collection<ConsumoTarifaFaixa> colecaoConsumoTarifaFaixa)
			throws RemoteException;
	
	
	/**
	 * [UC0145] - Inserir Conta
	 * Author: Raphael Rossiter
	 * Data: 12/01/2006
	 * Cálcula o valor total de água ou esgoto
	 * @param calcularValoresAguaEsgotoHelper
	 * @param tipoRetorno
	 * @return valorTotalAguaOuEsgoto
	 */
	public BigDecimal calcularValorTotalAguaOuEsgotoPorCategoria(Collection<CalcularValoresAguaEsgotoHelper>
	calcularValoresAguaEsgotoHelper, String tipoRetorno) throws RemoteException;
	
	
	/**
	 * [UC0146] - Manter Conta
	 * Author: Raphael Rossiter
	 * Data: 21/01/2006
	 * 
	 * Obtém as contas de um imóvel que poderão ser mantidas
	 * 
	 * @param imovel
	 * @param situacaoNormal
	 * @param situacaoIncluida
	 * @param situacaoRetificada
	 * @return
	 * @throws ControladorException
	 */
	public Collection obterContasImovelManter(Imovel imovel, Integer situacaoNormal, Integer situacaoIncluida,
			Integer situacaoRetificada) throws RemoteException;
	
	
	
	/**
	 * 
	 * @param colecaoCalcularValoresAguaEsgotoHelper
	 * @return
	 * @throws ControladorException
	 */
	public Collection<CalcularValoresAguaEsgotoHelper> calcularValoresAguaEsgotoTotalizandoPorCategoria(
			Collection colecaoCalcularValoresAguaEsgotoHelper) throws RemoteException;
	
	
	public void removerFaturamentoCronograma(String[] ids, String pacoteNomeObjeto) throws RemoteException;
	
	/**
	 * Permite gerar os débitos de doações para os imóveis contidos na coleção
	 * [UC0394] Gerar Débitos a Cobrar de Doações
	 * @author  César Araújo
	 * @date    05/08/2006
	 * @param   Collection<ImovelCobrarDoacaoHelper> imovelCobrarDoacaoHelpers - Coleção de imovelCobrarDoacaoHelpers 
	 * @return  Collection<ImovelCobrarDoacaoHelper> - Coleção de ImovelCobrarDoacaoHelper 
	 *          já com as informações necessárias para registro da cobrança
	 * @throws  ErroRepositorioException
	**/ 
	public void gerarDebitoACobrarDoacao(Collection<Rota> rotas) throws ControladorException;

	
	
	public void atualizarDataHoraRealizacaoAtividade(Integer idAtividade,
			Integer anoMesReferencia, Integer idFaturamentoGrupo)
			throws ControladorException;
	
	/**
	 *[UC0928] - Manter Situação Especial de Faturamento
	 *[SB] -
	 *[FS ] -
	 *
	 * comment
	 *
	 *@since 17/08/2009
	 *@author Marlon Patrick
	 */
	public void atualizarSituacaoEspecialFaturamento(FaturamentoSituacaoComando comandoOriginal,FaturamentoSituacaoComando comandoInserir, Collection<FaturamentoSituacaoHistorico> colecaoHistoricoInserir,FaturamentoSituacaoComando comandoAtualizar, Collection<FaturamentoSituacaoHistorico> colecaoHistoricoAtualizar) throws ControladorException;

	/**
	 *[UC0928] - Manter Situação Especial de Faturamento
	 *[SB] -
	 *[FS ] -
	 *
	 * comment
	 *
	 *@since 19/08/2009
	 *@author Marlon Patrick
	 */
	public Integer pesquisarSituacaoEspecialFaturamentoCount(FaturamentoSituacaoComando comando) throws ControladorException;

	/**
	 *[UC0928] - Manter Situação Especial de Faturamento
	 *
	 *@since 19/08/2009
	 *@author Marlon Patrick
	 */
	public Collection<FaturamentoSituacaoComando> pesquisarSituacaoEspecialFaturamento(FaturamentoSituacaoComando comando,Integer numeroPaginasPesquisa)throws ControladorException;

	/**
	 *[UC0958] - Gerar Relatório de juros, Multas e Débitos Cancelados
	 *
	 *@since 13/10/2009
	 *@author Marlon Patrick
	 */
	public Collection<RelatorioJurosMultasDebitosCanceladosHelper> pesquisarRelatorioJurosMultasDebitosCancelados(FiltrarRelatorioJurosMultasDebitosCanceladosHelper filtro) throws ControladorException;

}
