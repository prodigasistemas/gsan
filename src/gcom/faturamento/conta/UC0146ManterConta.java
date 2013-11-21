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
package gcom.faturamento.conta;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.bean.CalcularValoresAguaEsgotoHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.SessionContext;

/**
 * Esta classe tem como finalidade encapsular o caso de uso [UC0146] - Manter Conta, gerando
 * maior facilidade na manutenção do mesmo.  
 *
 * @author Raphael Rossiter
 * @date 04/11/2008
 */
public class UC0146ManterConta {

private static UC0146ManterConta instancia;
	
	@SuppressWarnings("unused")
	private IRepositorioFaturamento repositorioFaturamento;
	
	@SuppressWarnings("unused")
	private SessionContext sessionContext;

	
	private UC0146ManterConta(IRepositorioFaturamento repositorioFaturamento, 
			SessionContext sessionContext) {

		this.repositorioFaturamento = repositorioFaturamento;
		this.sessionContext = sessionContext;
	}

	public static UC0146ManterConta getInstancia(IRepositorioFaturamento repositorioFaturamento,
			SessionContext sessionContext) {
		
		if (instancia == null) {
			instancia = new UC0146ManterConta(repositorioFaturamento, sessionContext);
		}
		return instancia;
	}
	

	/**
	 * Controlador Imovel 
	 *
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 *
	 * @return ControladorImovelLocal
	 */
	protected ControladorImovelLocal getControladorImovel() {
		ControladorImovelLocalHome localHome = null;
		ControladorImovelLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorImovelLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Controlador Faturamento 
	 *
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 *
	 * @return ControladorFaturamentoLocal
	 */
	private ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * [UC0146] - Manter Conta
	 * 
	 * Retificar Conjunto de Conta
	 *
	 * @author Raphael Rossiter
	 * @date 04/11/2008
	 *
	 * @param colecaoContas
	 * @param identificadores
	 * @param sistemaParametro
	 * @param usuarioLogado
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection gerarColecaoContaSelecaoParaRetificacao(Collection<Conta> colecaoContas, String identificadores, 
			SistemaParametro sistemaParametro, Usuario usuarioLogado) throws ControladorException {
		
		Collection<Conta> colecaoContasSelecionadas = new ArrayList();
		
		Conta contaColecao;
		
		//Verifica se houve seleção de contas
		String[] arrayIdentificadores = null;
		
		if (identificadores != null) {
			arrayIdentificadores = identificadores.split(",");
		}
		
		Iterator colecaoContasIt = colecaoContas.iterator();
		
		while (colecaoContasIt.hasNext()) {
			
			contaColecao = (Conta) colecaoContasIt.next();

			if (identificadores != null) {
				
				for (int index = 0; index < arrayIdentificadores.length; index++) {
					
					String dadosConta = arrayIdentificadores[index];
					String[] idUltimaAlteracao = dadosConta.split("-");

					// Contas que sofrerão alteração na sua data de vencimento
					if (contaColecao.getId().equals(new Integer(idUltimaAlteracao[0]))) {
						
						if (sistemaParametro.getIndicadorLimiteAlteracaoVencimento().equals(ConstantesSistema.SIM)) {
							//[FS0023] - Verificar Limite de Alteração de vencimento.	
							this.getControladorFaturamento().verificarQuantidadeAlteracoesVencimentoConta(contaColecao.getId());
						}
						
						colecaoContasSelecionadas.add(contaColecao);
					}
				}
				
			} 
			else {
				
				colecaoContasSelecionadas.add(contaColecao);
			}
		}
		
		return colecaoContasSelecionadas;
	}
	
	/*
	 * Comentado por: Hugo Leonardo
	 * Data: 11/08/2010
	 * Analista: Aryed Lins
	 * 
	 * 
	 * [UC0146] - Manter Conta
	 *
	 * [FS0023] - Verificar Limite de Alteração de vencimento.
	 *
	 * @author Raphael Rossiter
	 * @date 04/11/2008
	 *
	 * @param conta
	 * @param sistemaParametro
	 * @param usuarioLogado
	 * @throws ControladorException
	 
	public void verificarLimiteAlteracaoVencimento(Conta conta, SistemaParametro sistemaParametro, Usuario usuarioLogado) 
		throws ControladorException {
		
		//-------------------------------------------------------------------------------------------
		// Alterado por :  Yara Taciane  - data : 17/07/2008 
		// Analista :  Denys Guimarães
		//-------------------------------------------------------------------------------------------	  
		
		// [FS0023] - Verificar Limite de Alteração de vencimento.
		
		if (sistemaParametro.getIndicadorLimiteAlteracaoVencimento().equals(ConstantesSistema.SIM)) {
			
			boolean temPermissaoParaAlterarVencimentoJaAlterado = 
			getControladorPermissaoEspecial().verificarPermissaoEspecial(
			PermissaoEspecial.ALTERAR_VENCIMENTO_JA_ALTERADO,usuarioLogado);		 			
			
			Integer idConta = conta.getId();
			Integer idImovel = conta.getImovel().getId();
				
			// Verifica se existe algum registro de alteração de vencimento para esse imovel e essa conta.
			Integer qtdOperacaoEfetuadaAltVencConta =  getControladorTransacao()
			.pesquisarOperacaoEfetuada(Operacao.OPERACAO_ALTERAR_VENCIMENTO_CONTA,idImovel,idConta);
								
			if(qtdOperacaoEfetuadaAltVencConta > 0) {					
				// Alteração de Vencimento.				
				if (temPermissaoParaAlterarVencimentoJaAlterado == false) {
					throw new ControladorException("atencao.necessario_permissao_especial_para_novo_vencimento",null);
				}
			}
				
			// Verifica se existe algum registro de retificação de conta para esse imovel e essa conta.
			Integer qtdOperacaoEfetuadaRetifConta =  getControladorTransacao()
			.pesquisarOperacaoEfetuada(Operacao.OPERACAO_CONTA_RETIFICAR,idImovel,idConta);
				
			if(qtdOperacaoEfetuadaRetifConta > 0 ){					
				
				Integer qtdTabelaLinhaColunaAlteracao =  getControladorTransacao()
				.pesquisarTabelaLinhaColunaAlteracao(idConta, TabelaColuna.DATA_VENCIMENTO_CONTA);					
				
				if(qtdTabelaLinhaColunaAlteracao > 0 && temPermissaoParaAlterarVencimentoJaAlterado == false){
					throw new ControladorException("atencao.necessario_permissao_especial_para_novo_vencimento",null);
				}
			}		
		}
	
	}
	*/
	
	/**
	 * Retificar Conjunto de Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2007
	 * 
	 * @throws ControladorException
	 */
	public void retificarConjuntoConta(Collection<Conta> colecaoContas, 
		LigacaoAguaSituacao ligacaoAguaSituacao, Integer consumoAgua,
		LigacaoEsgotoSituacao ligacaoEsgotoSituacao, Integer consumoEsgoto, Date dataVencimento,
		ContaMotivoRetificacao contaMotivoRetificacao, Short indicadorCategoriaEconomiaConta, SistemaParametro sistemaParametro, Usuario usuarioLogado) throws ControladorException {

		if (colecaoContas != null && !colecaoContas.isEmpty()) {

			Iterator colecaoContasIterator = colecaoContas.iterator();

			while (colecaoContasIterator.hasNext()) {

				Conta conta = (Conta) colecaoContasIterator.next();
				
				Collection colecaoCategoriaOUSubcategoria = null;
				
				if (sistemaParametro.getIndicadorTarifaCategoria().equals(SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA)){
	        		
					if (indicadorCategoriaEconomiaConta.equals(ConstantesSistema.SIM)) {
						// [UC0108] - Quantidade de economias por categoria
						colecaoCategoriaOUSubcategoria = this.getControladorImovel()
							.obterQuantidadeEconomiasContaCategoriaPorSubcategoria(conta);
					} else {
						colecaoCategoriaOUSubcategoria = this.getControladorImovel().obterQuantidadeEconomiasSubCategoria(conta.getImovel().getId());
					}
	        	}
	        	else{
	        		
	        		if (indicadorCategoriaEconomiaConta.equals(ConstantesSistema.SIM)) {
	        			// [UC0108] - Quantidade de economias por categoria
	        			colecaoCategoriaOUSubcategoria = this.getControladorImovel().obterQuantidadeEconomiasContaCategoria(conta);
	        		} else {
	        			colecaoCategoriaOUSubcategoria = this.getControladorImovel().obterQuantidadeEconomiasCategoria(conta.getImovel());
	        		}
	        	}
				

				Collection colecaoCreditoRealizado = this.getControladorFaturamento().obterCreditosRealizadosConta(conta);

				Collection colecaoDebitoCobrado = this.getControladorFaturamento().obterDebitosCobradosConta(conta);

				//[UC0120] - Calcular Valores de Água e/ou Esgoto
				Collection<CalcularValoresAguaEsgotoHelper> valoresConta = 
				this.getControladorFaturamento().calcularValoresConta(
						
				Util.formatarAnoMesParaMesAno(conta.getReferencia()), 
						conta.getImovel().getId().toString(), ligacaoAguaSituacao.getId(), 
						ligacaoEsgotoSituacao.getId(), colecaoCategoriaOUSubcategoria, consumoAgua.toString(), 
						consumoEsgoto.toString(), conta.getPercentualEsgoto().toString(), conta
						.getConsumoTarifa().getId(), usuarioLogado);
				
				//[UC0150] - Retificar Conta
				Conta contaParaRetificacao =  this.getControladorFaturamento().pesquisarContaRetificacao(conta.getId());

				this.getControladorFaturamento().retificarConta(
				contaParaRetificacao.getReferencia(), contaParaRetificacao, contaParaRetificacao.getImovel(), colecaoDebitoCobrado,
				colecaoCreditoRealizado, ligacaoAguaSituacao, ligacaoEsgotoSituacao,
				colecaoCategoriaOUSubcategoria, consumoAgua.toString(), consumoEsgoto.toString(),
				contaParaRetificacao.getPercentualEsgoto().toString(), dataVencimento, valoresConta, 
				contaMotivoRetificacao, null, usuarioLogado, contaParaRetificacao.getConsumoTarifa().getId().toString(),
				false,null,null,false, null,null,null,null,null);
			}
		}
	}
}
