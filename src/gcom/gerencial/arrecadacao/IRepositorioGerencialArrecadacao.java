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
package gcom.gerencial.arrecadacao;

import java.util.List;

import gcom.util.ErroRepositorioException;

/**
 * 
 * 
 * @author Ivan Sérgio
 * @created 10/05/2007
 */
public interface IRepositorioGerencialArrecadacao {
	
	/**
	 * [UC0553 - Gerar Resumo da Arrecadacao - Aguá/Esgoto]
	 * 
	 * @author Ivan Sérgio
	 * @date 19/05/2008
	 * 
	 * @param 	idLocalidade
	 *		  	anoMesReferenciaPagamento
	 * @return 	list
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoAguaEsgoto(int idSetorComercial, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * [UC0553 - Gerar Resumo da Arrecadacao - Outros - CONTA]
	 * 
	 * @author Ivan Sérgio
	 * @date 20/05/2008
	 * 
	 * @param idSetorComercial
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoOutrosConta(int idSetorComercial, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * [UC0553 - Gerar Resumo da Arrecadacao - Outros - GUIA DE PAGAMENTO]
	 * 
	 * @author Ivan Sérgio
	 * @date 20/05/2008
	 * 
	 * @param idSetorComercial
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoOutrosGuiaPagamento(int idSetorComercial, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * [UC0553 - Gerar Resumo da Arrecadacao - Outros - DEBITO A COBRAR]
	 * 
	 * @author Ivan Sérgio
	 * @date 20/05/2008
	 * 
	 * @param idSetorComercial
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoOutrosDebitoACobrar(int idSetorComercial, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * @author Ivan Sérgio
	 * @date 22/05/2008
	 *
	 * @param idSetorComercial
	 * @param anoMesReferenciaPagamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoCreditos(int idSetorComercial, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * Seleciona o maior mês/ano de referência da tabela un_resumo_arrecadacao
	 * 
 	 * [UC????] - Gerar Resumo Indicadores da Cobrança
	 * 
	 * @author Rafael Corrêa
	 * @date 25/03/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoArrecadacao()
			throws ErroRepositorioException;
	
	/**
	 * [UC0553 - Gerar Resumo da Arrecadacao - Aguá/Esgoto - Valor Nao Identificado]
	 * 
	 * @author Ivan Sérgio
	 * @date 02/06/2008
	 * 
	 * @param 	idLocalidade
	 *		  	anoMesReferenciaPagamento
	 * @return 	list
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoAguaEsgotoValorNaoIdentificado(
			int idSetorComercial, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * Caso em que o Pagamento nao possui Conta, Guia de Pagamento e Debio a Cobrar
	 * 
 	 * [UC0533] - Gerar Resumo da Arrecadacao
	 * 
	 * @author Ivan Sérgio
	 * @date 12/06/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarDadosPagamentoSemContaGuiaDebito(Integer idLocalidade)
			throws ErroRepositorioException;
	
	/***
	 * [UC0533] - Gerar Resumo da Arrecadacao - Devolucao
	 * 
	 * @author Ivan Sérgio
	 * @date 09/10/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoDevolucao(int idLocalidade, int anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;
	
	/**
	 * Gerar Resumo da Arrecadacao Por Ano
	 * 
	 * @author Fernando Fontelles
	 * @date 02/06/2010
	 * 		 
	 * @param 	idLocalidade
	 *		  	anoMesReferenciaPagamento
	 * @return 	list
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoAguaEsgotoPorAno(int idLocalidade, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * Caso em que o Pagamento nao possui Conta, Guia de Pagamento e Debio a Cobrar
	 * 
 	 * Gerar Resumo da Arrecadacao Por Ano
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 03/06/2010
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarDadosPagamentoSemContaGuiaDebitoPorAno(Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * Gerar Resumo da Arrecadacao Por Ano - Aguá/Esgoto - Valor Nao Identificado
	 * 
	 * @author Fernando Fontelles
	 * @date 05/06/2010
	 * 
	 * @param 	idLocalidade
	 *		  	anoMesReferenciaPagamento
	 * @return 	list
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoAguaEsgotoValorNaoIdentificadoPorAno(
			int idLocalidade, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * Gerar Resumo da Arrecadacao Por Ano - Outros - CONTA
	 * 
	 * @author Fernando Fontelles
	 * @date 09/06/2010
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoOutrosContaPorAno(int idLocalidade, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * Gerar Resumo da Arrecadacao Por Ano - Outros - GUIA DE PAGAMENTO
	 * 
	 * @author Fernando Fontelles
	 * @date 09/06/2010
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoOutrosGuiaPagamentoPorAno(int idLocalidade, 
			int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * Gerar Resumo da Arrecadacao Por Ano - Outros - DEBITO A COBRAR
	 * 
	 * @author Fernando Fontelles
	 * @date 09/06/2010
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoOutrosDebitoACobrarPorAno(int idLocalidade, 
			int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * @author Fernando Fontelles
	 * @date 09/06/2010
	 *
	 * @param idLocalidade
	 * @param anoMesReferenciaPagamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoCreditosPorAno(int idLocalidade, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/***
	 * Gerar Resumo da Arrecadacao Por Ano - Devolucao
	 * 
	 * @author Fernando Fontelles
	 * @date 10/06/2010
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoDevolucaoPorAno(int idLocalidade, int anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;
	
}