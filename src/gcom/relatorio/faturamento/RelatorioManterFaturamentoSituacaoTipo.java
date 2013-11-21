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

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FiltroFaturamentoSituacaoTipo;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Arthur Carvalho
 * @version 1.0
 */

public class RelatorioManterFaturamentoSituacaoTipo extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterFaturamentoSituacaoTipo(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_FATURAMENTO_SITUACAO_TIPO_MANTER);
	}
	
	@Deprecated
	public RelatorioManterFaturamentoSituacaoTipo() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param situacao pagamento
	 *            Description of the Parameter
	 * @param SituacaoPagamentoParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroFaturamentoSituacaoTipo filtroFaturamentoSituacaoTipo= (FiltroFaturamentoSituacaoTipo) getParametro("filtroFaturamentoSituacaoTipo");
		FaturamentoSituacaoTipo faturamentoSituacaoTipoParametros = (FaturamentoSituacaoTipo) getParametro("faturamentoSituacaoTipoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		//filtroFaturamentoSituacaoTipo.adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoTipo.ID);
		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterFaturamentoSituacaoTipoBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoFaturamentoSituacaoTipo = fachada.pesquisar(filtroFaturamentoSituacaoTipo,
				FaturamentoSituacaoTipo.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoFaturamentoSituacaoTipo != null && !colecaoFaturamentoSituacaoTipo.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator faturamentoSituacaoTipoIterator = colecaoFaturamentoSituacaoTipo.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (faturamentoSituacaoTipoIterator.hasNext()) {

				FaturamentoSituacaoTipo faturamentoSituacaoTipo = (FaturamentoSituacaoTipo) faturamentoSituacaoTipoIterator.next();

				String indicadorParalisacaoFaturamento = "";
				
				if(faturamentoSituacaoTipo.getIndicadorParalisacaoFaturamento().equals(ConstantesSistema.SIM)){
					indicadorParalisacaoFaturamento = "SIM";
				} else {
					indicadorParalisacaoFaturamento = "NÃO";
				}
				
				String indicadorParalisacaoLeitura = "";
				
				if(faturamentoSituacaoTipo.getIndicadorParalisacaoLeitura().equals(ConstantesSistema.SIM)){
					indicadorParalisacaoLeitura = "SIM";
				} else {
					indicadorParalisacaoLeitura = "NÃO";
				}
				
				String indicadorValidoAgua = "";
				
				if(faturamentoSituacaoTipo.getIndicadorValidoAgua().equals(ConstantesSistema.SIM)){
					indicadorValidoAgua = "SIM";
				} else {
					indicadorValidoAgua = "NÃO";
				}
				
				String indicadorValidoEsgoto = "";
				
				if(faturamentoSituacaoTipo.getIndicadorValidoEsgoto().equals(ConstantesSistema.SIM)){
					indicadorValidoEsgoto = "SIM";
				} else {
					indicadorValidoEsgoto = "NÃO";
				}
				
				
				relatorioBean = new RelatorioManterFaturamentoSituacaoTipoBean(
						// ID
						faturamentoSituacaoTipo.getId().toString(), 
						
						// Descrição
						faturamentoSituacaoTipo.getDescricao(), 
						
						// paralisacao faturamento
						indicadorParalisacaoFaturamento,
						
						//paralisacao leitura
						indicadorParalisacaoLeitura,
						
						//indicador valido agua
						indicadorValidoAgua,
						
						//indicador valido esgoto
						indicadorValidoEsgoto);
						
				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("id",
				faturamentoSituacaoTipoParametros.getId() == null ? "" : ""
						+ faturamentoSituacaoTipoParametros.getId());

		parametros.put("descricao", faturamentoSituacaoTipoParametros.getDescricao());
		
		
		String indicadorParalisacaoFaturamento = "";

		if (faturamentoSituacaoTipoParametros.getIndicadorParalisacaoFaturamento() != null
				&& !faturamentoSituacaoTipoParametros.getIndicadorParalisacaoFaturamento().equals("")) {
			if (faturamentoSituacaoTipoParametros.getIndicadorParalisacaoFaturamento().equals(new Short("1"))) {
				indicadorParalisacaoFaturamento = "Sim";
			} else if (faturamentoSituacaoTipoParametros.getIndicadorParalisacaoFaturamento().equals(
					new Short("2"))) {
				indicadorParalisacaoFaturamento = "Não";
			}
		}

		parametros.put("indicadorParalisacaoFaturamento", indicadorParalisacaoFaturamento);
		
		
		String indicadorParalisacaoLeitura = "";

		if (faturamentoSituacaoTipoParametros.getIndicadorParalisacaoLeitura() != null
				&& !faturamentoSituacaoTipoParametros.getIndicadorParalisacaoLeitura().equals("")) {
			if (faturamentoSituacaoTipoParametros.getIndicadorParalisacaoLeitura().equals(new Short("1"))) {
				indicadorParalisacaoLeitura = "Sim";
			} else if (faturamentoSituacaoTipoParametros.getIndicadorParalisacaoLeitura().equals(
					new Short("2"))) {
				indicadorParalisacaoLeitura = "Não";
			}
		}

		parametros.put("indicadorParalisacaoLeitura", indicadorParalisacaoLeitura);
		
		String indicadorValidoAgua = "";

		if (faturamentoSituacaoTipoParametros.getIndicadorValidoAgua() != null
				&& !faturamentoSituacaoTipoParametros.getIndicadorValidoAgua().equals("")) {
			if (faturamentoSituacaoTipoParametros.getIndicadorValidoAgua().equals(new Short("1"))) {
				indicadorValidoAgua = "Sim";
			} else if (faturamentoSituacaoTipoParametros.getIndicadorValidoAgua().equals(
					new Short("2"))) {
				indicadorValidoAgua = "Não";
			}
		}

		parametros.put("indicadorValidoAgua", indicadorValidoAgua);
		
		String indicadorValidoEsgoto = "";

		if (faturamentoSituacaoTipoParametros.getIndicadorValidoEsgoto() != null
				&& !faturamentoSituacaoTipoParametros.getIndicadorValidoEsgoto().equals("")) {
			if (faturamentoSituacaoTipoParametros.getIndicadorValidoEsgoto().equals(new Short("1"))) {
				indicadorValidoEsgoto = "Sim";
			} else if (faturamentoSituacaoTipoParametros.getIndicadorValidoEsgoto().equals(
					new Short("2"))) {
				indicadorValidoEsgoto = "Não";
			}
		}

		parametros.put("indicadorValidoEsgoto", indicadorValidoEsgoto);

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_FATURAMENTO_SITUACAO_TIPO_MANTER, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
//		try {
//			persistirRelatorioConcluido(retorno, Relatorio.MANTER_LOCALIDADE,
//					idFuncionalidadeIniciada);
//		} catch (ControladorException e) {
//			e.printStackTrace();
//			throw new TarefaException("Erro ao gravar relatório no sistema", e);
//		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

//		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
//				(FiltroLocalidade) getParametro("filtroLocalidade"),
//				Localidade.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterFaturamentoSituacaoTipo", this);
	}

}