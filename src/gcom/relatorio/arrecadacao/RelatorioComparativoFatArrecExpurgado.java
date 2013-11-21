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
package gcom.relatorio.arrecadacao;

import gcom.batch.Relatorio;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
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
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [UC00744] Gerar Comparativo do Faturamento, Arrecadação e Expurgo
 * 
 * @author Sávio Luiz
 * 
 * @date 09/12/2008
 */

public class RelatorioComparativoFatArrecExpurgado extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioComparativoFatArrecExpurgado(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_COMPARATIVO_FATURAMENTO_ARRECADACAO_EXPURGO);
	}
	
	int quantidadeRegistros;

	@Deprecated
	public RelatorioComparativoFatArrecExpurgado() {
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

		String gerenciaRegional = (String) getParametro("gerenciaRegional");
		String unidadeNegocio = (String) getParametro("unidadeNegocio");
		String mesAnoReferencia = (String) getParametro("mesAnoreferencia");

		Integer anoMesreferencia = Util
				.formatarMesAnoComBarraParaAnoMes(mesAnoReferencia);

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		Collection<RelatorioComparativoFatArrecExpurgoBean> colecaoComparativoExpurgoBean = fachada
				.pesquisarDadosComparativosFaturamentoArrecadacaoExpurgo(
						anoMesreferencia, gerenciaRegional, unidadeNegocio);

	

				// adiciona o bean a coleção
				relatorioBeans.addAll(colecaoComparativoExpurgoBean);

		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise

		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("mesAnoReferencia", mesAnoReferencia);
		
		if ((gerenciaRegional == null || gerenciaRegional.equals(""))
				&& (unidadeNegocio == null || unidadeNegocio.equals(""))) {
			parametros.put("opcaoTotalizacao", "ESTADO");
		}else{
			if(gerenciaRegional != null && !gerenciaRegional.equals("")){
				FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
				filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID,gerenciaRegional));
				Collection colecaoGR = fachada.pesquisar(filtroGerenciaRegional,GerenciaRegional.class.getName());
				GerenciaRegional grBase = (GerenciaRegional)Util.retonarObjetoDeColecao(colecaoGR);
				parametros.put("opcaoTotalizacao", "GERÊNCIA REGIONAL - "+grBase.getNome().toUpperCase());
			}
			
			if(unidadeNegocio != null && !unidadeNegocio.equals("")){
				FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
				filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID,unidadeNegocio));
				Collection colecaoUN = fachada.pesquisar(filtroUnidadeNegocio,UnidadeNegocio.class.getName());
				UnidadeNegocio unBase = (UnidadeNegocio)Util.retonarObjetoDeColecao(colecaoUN);
				parametros.put("opcaoTotalizacao", "UNIDADE NEGÓCIO - "+unBase.getNome().toUpperCase());
			}
		}
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoRelatorio", "R0744");

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_COMPARATIVO_FATURAMENTO_ARRECADACAO_EXPURGO,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.COMPARATIVO_FATURAMENTO_ARRECADACAO_EXPURGO, idFuncionalidadeIniciada);
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


		Integer anoMesreferencia = Util
		.formatarMesAnoComBarraParaAnoMes((String)getParametro("mesAnoreferencia"));
		
		int retorno = Fachada
				.getInstancia()
				.pesquisarQuantidadeDadosComparativosFaturamentoArrecadacaoExpurgo(anoMesreferencia,(String) getParametro("gerenciaRegional"),
						(String) getParametro("unidadeNegocio"));

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioComparativoFatArrecExpurgado", this);

	}

}