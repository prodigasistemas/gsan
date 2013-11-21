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
package gcom.relatorio.arrecadacao.pagamento;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.financeiro.FiltroFinanciamentoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de certidao negativa
 * 
 * @author Bruno Barros
 * @created 29/01/2008
 */
public class RelatorioGuiaPagamentoEmAtraso extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioGuiaPagamentoEmAtraso(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CERTIDAO_NEGATIVA);
	}

	public RelatorioGuiaPagamentoEmAtraso() {
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

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		FiltroGuiaPagamento filtroGuiaPagamento = (FiltroGuiaPagamento) getParametro("filtroGuiaPagamento");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioGuiaPagamentoEmAtrasoBean relatorioGuiaPagamentoEmAtrasoBean = null;

		Collection colecao =  
			fachada.pesquisarDadosRelatorioGuiaPagamentoEmAtraso(filtroGuiaPagamento);
		
		Collection<RelatorioGuiaPagamentoEmAtrasoHelper> colecaoGuiaImovel = null;
		Collection<RelatorioGuiaPagamentoEmAtrasoHelper> colecaoGuiaCliente = null;
		Iterator iteratorColecao = colecao.iterator();
		while(iteratorColecao.hasNext()){
			colecaoGuiaImovel = (Collection<RelatorioGuiaPagamentoEmAtrasoHelper>)iteratorColecao.next();
			colecaoGuiaCliente = (Collection<RelatorioGuiaPagamentoEmAtrasoHelper>)iteratorColecao.next();
		}

		
		BigDecimal valorTotal = BigDecimal.ZERO;
		BigDecimal valorTotalImovel = BigDecimal.ZERO;
		BigDecimal valorTotalCliente = BigDecimal.ZERO;
		
		// se a coleção de parâmetros da analise não for vazia
		if (colecaoGuiaImovel != null && !colecaoGuiaImovel.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoIterator = colecaoGuiaImovel.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {

				RelatorioGuiaPagamentoEmAtrasoHelper helper = 
					(RelatorioGuiaPagamentoEmAtrasoHelper) colecaoIterator.next();
				
				relatorioGuiaPagamentoEmAtrasoBean = 
					new RelatorioGuiaPagamentoEmAtrasoBean(helper);
				
				valorTotalImovel = valorTotalImovel.add(Util.formatarMoedaRealparaBigDecimal(helper.getValor()));
				
				valorTotal = valorTotal.add(Util.formatarMoedaRealparaBigDecimal(helper.getValor()));

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioGuiaPagamentoEmAtrasoBean);
			}
			
		}
		
		//se a coleção de parâmetros da analise não for vazia
		if (colecaoGuiaCliente != null && !colecaoGuiaCliente.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoIterator = colecaoGuiaCliente.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {

				RelatorioGuiaPagamentoEmAtrasoHelper helper = 
					(RelatorioGuiaPagamentoEmAtrasoHelper) colecaoIterator.next();
				
				relatorioGuiaPagamentoEmAtrasoBean = 
					new RelatorioGuiaPagamentoEmAtrasoBean(helper);
				
				valorTotalCliente = valorTotalCliente.add(Util.formatarMoedaRealparaBigDecimal(helper.getValor()));
				
				valorTotal = valorTotal.add(Util.formatarMoedaRealparaBigDecimal(helper.getValor()));

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioGuiaPagamentoEmAtrasoBean);
			}
			
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("valorTotal", Util.formatarMoedaReal(valorTotal));
		parametros.put("valorTotalImovel", Util.formatarMoedaReal(valorTotalImovel));
		parametros.put("valorTotalCliente", Util.formatarMoedaReal(valorTotalCliente));
		
		parametros.put("qtdImoveis", colecaoGuiaImovel.size() + "");
		parametros.put("qtdClientes", colecaoGuiaCliente.size() + "");
		
		String financiamentoTipo = (String) getParametro("financiamentoTipo");
		String vencimentoInicial = (String) getParametro("vencimentoInicial");
		String vencimentoFinal = (String) getParametro("vencimentoFinal");
		String referenciaInicial = (String) getParametro("referenciaInicial");
		String referenciaFinal = (String) getParametro("referenciaFinal");
		
		FinanciamentoTipo financiamento = null;
		
		if(financiamentoTipo != null && !financiamentoTipo.trim().equals("")){
			FiltroFinanciamentoTipo filtroFinanciamentoTipo = new FiltroFinanciamentoTipo();
			filtroFinanciamentoTipo.adicionarParametro(new ParametroSimples(FiltroFinanciamentoTipo.ID, financiamentoTipo));
			Collection colecaoFinanciamento = fachada.pesquisar(filtroFinanciamentoTipo, FinanciamentoTipo.class.getName());
			
			if(!colecaoFinanciamento.isEmpty()){
				financiamento = (FinanciamentoTipo)Util.retonarObjetoDeColecao(colecaoFinanciamento);
			}
		}
		if(financiamento != null){
			parametros.put("financiamentoTipo", financiamento.getDescricaoAbreviada());
		}else{
			parametros.put("financiamentoTipo", "");
		}
		parametros.put("vencimentoInicial", vencimentoInicial);
		parametros.put("vencimentoFinal", vencimentoFinal);
		parametros.put("referenciaInicial", referenciaInicial);
		parametros.put("referenciaFinal", referenciaFinal);
		parametros.put("tipoFormatoRelatorio", "R0877");
		
		String nomeRelatorio = ConstantesRelatorios.RELATORIO_GUIA_PAGAMENTO_EM_ATRASO;

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		retorno = gerarRelatorio(nomeRelatorio,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_GUIA_PAGAMENTO_EM_ATRASO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioGuiaPagamentoEmAtraso", this);
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}
}