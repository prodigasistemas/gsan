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
package gcom.relatorio.cobranca;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.FiltroResolucaoDiretoria;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de bairro manter de água
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class RelatorioManterResolucaoDiretoria extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterResolucaoDiretoria(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RESOLUCAO_DIRETORIA_MANTER);
	}
	
	@Deprecated
	public RelatorioManterResolucaoDiretoria() {
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

		FiltroResolucaoDiretoria filtroResolucaoDiretoria = (FiltroResolucaoDiretoria) getParametro("filtroResolucaoDiretoria");
		ResolucaoDiretoria resolucaoDiretoriaParametros = (ResolucaoDiretoria) getParametro("resolucaoDiretoriaParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterResolucaoDiretoriaBean relatorioBean = null;

		filtroResolucaoDiretoria.setConsultaSemLimites(true);

		Collection colecaoResolucoesDiretoria = fachada.pesquisar(
				filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoResolucoesDiretoria != null
				&& !colecaoResolucoesDiretoria.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoResolucoesDiretoriaIterator = colecaoResolucoesDiretoria
					.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoResolucoesDiretoriaIterator.hasNext()) {

				ResolucaoDiretoria resolucaoDiretoria = (ResolucaoDiretoria) colecaoResolucoesDiretoriaIterator
						.next();
				
				// Faz as validações dos campos necessáriose e formata a String
				// para a forma como irá aparecer no relatório
				
				// Data Vigência Início
				String dataVigenciaInicio = "";
				
				if (resolucaoDiretoria.getDataVigenciaInicio() != null) {
					dataVigenciaInicio = Util.formatarData(resolucaoDiretoria
							.getDataVigenciaInicio());
				}
				
				// Data Vigência Fim
				String dataVigenciaFim = "";
				
				if (resolucaoDiretoria.getDataVigenciaFim() != null) {
					dataVigenciaFim = Util.formatarData(resolucaoDiretoria
							.getDataVigenciaFim());
				}

				relatorioBean = new RelatorioManterResolucaoDiretoriaBean(
						
						// Número
						resolucaoDiretoria.getNumeroResolucaoDiretoria(),
						
						// Assunto
						resolucaoDiretoria.getDescricaoAssunto(),
						
						// Data da Vigência Início
						dataVigenciaInicio,
										
						// Data da Vigência Fim
						dataVigenciaFim);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("numero", resolucaoDiretoriaParametros
				.getNumeroResolucaoDiretoria());

		parametros.put("assunto", resolucaoDiretoriaParametros
				.getDescricaoAssunto());

		if (resolucaoDiretoriaParametros.getDataVigenciaInicio() != null) {
			parametros.put("dataInicio", Util
					.formatarData(resolucaoDiretoriaParametros
							.getDataVigenciaInicio()));
		} else {
			parametros.put("dataInicio", "");
		}

		if (resolucaoDiretoriaParametros.getDataVigenciaFim() != null) {
			parametros.put("dataTermino", Util
					.formatarData(resolucaoDiretoriaParametros
							.getDataVigenciaFim()));
		} else {
			parametros.put("dataTermino", "");
		}

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_RESOLUCAO_DIRETORIA_MANTER,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_RESOLUCAO_DIRETORIA,
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

		retorno = Fachada
				.getInstancia()
				.totalRegistrosPesquisa(
						(FiltroResolucaoDiretoria) getParametro("filtroResolucaoDiretoria"),
						ResolucaoDiretoria.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterResolucaoDiretoria", this);
	}
}