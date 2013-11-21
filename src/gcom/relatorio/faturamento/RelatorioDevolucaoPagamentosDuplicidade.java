/*
* Copyright (C) 2007-2007 the GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place – Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o Relatório Devolução dos Pagamentos em Duplicidade.
 * 
 * [UC1129] Gerar Relatório Devolução dos Pagamentos em Duplicidade
 * 
 * @author Hugo Leonardo
 *
 * @date 10/03/2011
 */
public class RelatorioDevolucaoPagamentosDuplicidade extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioDevolucaoPagamentosDuplicidade(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_DEVOLUCAO_PAGAMENTO_DUPLICIDADE);
	}

	@Deprecated
	public RelatorioDevolucaoPagamentosDuplicidade() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 */
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltrarRelatorioDevolucaoPagamentosDuplicidadeHelper relatorioHelper = 
			(FiltrarRelatorioDevolucaoPagamentosDuplicidadeHelper) getParametro("filtrarRelatorioDevolucaoPagamentosDuplicidadeHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String gerenciaDescricao = (String) getParametro("gerenciaDescricao");
		String unidadeDescricao = (String) getParametro("unidadeDescricao");
		String perfilDescricao = (String) getParametro("perfilDescricao");
		String categoriaDescricao = (String) getParametro("categoriaDescricao");
		String periodoReferencia = (String) getParametro("periodoReferencia");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioDevolucaoPagamentosDuplicidadeBean relatorioDevolucaoPagamentosDuplicidadeBean = null;

		Collection<RelatorioDevolucaoPagamentosDuplicidadeHelper> colecao =  
			fachada.pesquisarRelatorioDevolucaoPagamentosDuplicidade(relatorioHelper);

		// se a coleção de parâmetros da analise não for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {

				RelatorioDevolucaoPagamentosDuplicidadeHelper helper = 
					(RelatorioDevolucaoPagamentosDuplicidadeHelper) colecaoIterator.next();
				
				relatorioDevolucaoPagamentosDuplicidadeBean = 
					new RelatorioDevolucaoPagamentosDuplicidadeBean(
							
							helper.getIdGerencia(),
							helper.getNomeGerencia(),
							helper.getIdUnidade(),
							helper.getNomeUnidade(),
							helper.getIdLocalidade(),
							helper.getNomeLocalidade(),
							helper.getNumeroRA(),
							helper.getMatricula(),
							helper.getMesAnoPagamentoDuplicidade(),
							helper.getValorPagamentoDuplicidade(),
							helper.getMesAnoConta(),
							helper.getValorConta(),
							helper.getCreditoRealizado(),
							helper.getCreditoARealizar(),
							helper.getDataAtualizacao());

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioDevolucaoPagamentosDuplicidadeBean);				
			}
		}else{
			
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());	
		
		parametros.put("gerenciaDescricao", gerenciaDescricao); 
		parametros.put("unidadeDescricao", unidadeDescricao);
		parametros.put("perfilDescricao", perfilDescricao);
		parametros.put("categoriaDescricao", categoriaDescricao);
		parametros.put("periodoReferencia", periodoReferencia);
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_DEVOLUCAO_PAGAMENTO_DUPLICIDADE,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_DEVOLUCAO_PAGAMENTO_DUPLICIDADE,
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
		
		int retorno = 10;

		/*
		retorno = Fachada.getInstancia().pesquisarTotalRelatorioAcessosPorUsuario(
				(FiltrarRelatorioDevolucaoPagamentosDuplicidadeHelper) 
					getParametro("filtrarRelatorioDevolucaoPagamentosDuplicidadeHelper"));
        */
        
		if (retorno == 0) {
			// Caso a pesquisa não retorne nenhum resultado comunica ao
			// usuário;
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		return retorno;		
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioDevolucaoPagamentosDuplicidade", this);
	}

}