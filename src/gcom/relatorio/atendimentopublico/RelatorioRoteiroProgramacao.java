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
package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.EquipeComponentes;
import gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao;
import gcom.atendimentopublico.ordemservico.bean.ObterDadosEquipe;
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
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

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
 * @author not attributable
 * @version 1.0
 */

public class RelatorioRoteiroProgramacao extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioRoteiroProgramacao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ROTEIRO_PROGRAMACAO);
	}
	
	@Deprecated
	public RelatorioRoteiroProgramacao() {
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

		HashMap mapEquipe = (HashMap) getParametro("mapEquipe");
		StringTokenizer nomesEquipes = (StringTokenizer) getParametro("nomesEquipes");
		Date dataRoteiro = (Date) getParametro("dataRoteiro");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioRoteiroProgramacaoBean relatorioBean = null;

		// se a coleção de parâmetros da analise não for vazia
		if (nomesEquipes != null) {

			// laço para criar a coleção de equipes
			while (nomesEquipes.hasMoreTokens()) {

				String chave = nomesEquipes.nextToken();

				Equipe equipe = (Equipe) mapEquipe.get(chave);

				ObterDadosEquipe obterDadosEquipe = fachada
						.obterDadosEquipe(equipe.getId());

				Collection colecaoComponentesEquipe = obterDadosEquipe
						.getColecaoEquipeComponentes();

				String placa = "";

				if (equipe.getPlacaVeiculo() != null
						&& !equipe.getPlacaVeiculo().trim().equals("")) {

					String letras = equipe.getPlacaVeiculo().substring(0, 3);
					String digitos = equipe.getPlacaVeiculo().substring(3, 7);

					placa = letras + "-" + digitos;

				}

				EquipeComponentes encarregado = null;

				if (colecaoComponentesEquipe != null
						&& !colecaoComponentesEquipe.isEmpty()) {

					Iterator colecaoComponenteEncarregadoIterator = colecaoComponentesEquipe
							.iterator();

					while (colecaoComponenteEncarregadoIterator.hasNext()) {
						encarregado = (EquipeComponentes) colecaoComponenteEncarregadoIterator
								.next();
						if (("" + encarregado.getIndicadorResponsavel())
								.equals(""
										+ EquipeComponentes.INDICADOR_RESPONSAVEL_SIM)) {
							break;
						}
					}

					Iterator colecaoComponentesEquipeIterator = colecaoComponentesEquipe
							.iterator();

					int posicaoComponente = 0;

					while (colecaoComponentesEquipeIterator.hasNext()) {

						EquipeComponentes equipeComponentes = (EquipeComponentes) colecaoComponentesEquipeIterator
								.next();

						posicaoComponente = posicaoComponente + 1;

						if (colecaoComponentesEquipeIterator.hasNext()) {

							EquipeComponentes equipeComponentes2 = (EquipeComponentes) colecaoComponentesEquipeIterator
									.next();

							relatorioBean = new RelatorioRoteiroProgramacaoBean(

							// Unidade Organizacional
									equipe.getUnidadeOrganizacional()
											.getDescricao() == null ? "" : equipe
											.getUnidadeOrganizacional()
											.getDescricao(),

									// Data do Roteiro
									Util.formatarData(dataRoteiro),

									// Código da Equipe
									equipe.getId().toString(),

									// Nome da Equipe
									equipe.getNome(),

									// Placa
									placa,

									// Encarregado
									encarregado.getComponentes(),

									// Posição do Componente
									"" + posicaoComponente,

									// Nome do Componente
									equipeComponentes.getComponentes(),

									// Posição do Componente 2
									"" + (posicaoComponente + 1),

									// Nome do Componente 2
									equipeComponentes2.getComponentes(),

									// Sequencial
									"",

									// Número do RA
									"",

									// Número da OS
									"",

									// Tipo de Serviço
									"",

									// Endereço da Ocorrência
									"",

									// Observação
									"");

							// adiciona o bean a coleção
							relatorioBeans.add(relatorioBean);

							posicaoComponente = posicaoComponente + 1;

						} else {
							relatorioBean = new RelatorioRoteiroProgramacaoBean(

							// Unidade Organizacional
									equipe.getUnidadeOrganizacional()
											.getDescricao() == null ? "" : equipe
											.getUnidadeOrganizacional()
											.getDescricao(),

									// Data do Roteiro
									Util.formatarData(dataRoteiro),

									// Código da Equipe
									equipe.getId().toString(),

									// Nome da Equipe
									equipe.getNome(),

									// Placa
									placa,

									// Encarregado
									encarregado.getComponentes(),

									// Posição do Componente
									"" + posicaoComponente,

									// Nome do Componente
									equipeComponentes.getComponentes(),

									// Posição do Componente 2
									"",

									// Nome do Componente 2
									"",

									// Sequencial
									"",

									// Número do RA
									"",

									// Número da OS
									"",

									// Tipo de Serviço
									"",

									// Endereço da Ocorrência
									"",

									// Observação
									"");

							// adiciona o bean a coleção
							relatorioBeans.add(relatorioBean);
						}

					}

				}

				Collection colecaoOrdensServicoProgramacao = fachada
						.pesquisarOrdemServicoProgramacaoRelatorio(equipe
								.getId(), dataRoteiro);

				if (colecaoOrdensServicoProgramacao != null
						&& !colecaoOrdensServicoProgramacao.isEmpty()) {

					Iterator colecaoOrdensServicoProgramacaoIterator = colecaoOrdensServicoProgramacao
							.iterator();

					while (colecaoOrdensServicoProgramacaoIterator.hasNext()) {
						OrdemServicoProgramacao ordemServicoProgramacao = (OrdemServicoProgramacao) colecaoOrdensServicoProgramacaoIterator
								.next();

						String endereco = "";
						String idRegistroAtendimento = "";

						if (ordemServicoProgramacao.getOrdemServico()
								.getRegistroAtendimento().getId() != null) {
							idRegistroAtendimento = ordemServicoProgramacao
									.getOrdemServico().getRegistroAtendimento()
									.getId().toString();
							endereco = fachada
									.obterEnderecoOcorrenciaRA(ordemServicoProgramacao
											.getOrdemServico()
											.getRegistroAtendimento().getId());
						}

						relatorioBean = new RelatorioRoteiroProgramacaoBean(

								// Unidade Organizacional
								equipe.getUnidadeOrganizacional().getSigla() == null ? ""
										: equipe.getUnidadeOrganizacional()
												.getSigla(),

								// Data do Roteiro
								Util.formatarData(dataRoteiro),

								// Código da Equipe
								equipe.getId().toString(),

								// Nome da Equipe
								equipe.getNome(),

								// Placa
								placa,

								// Encarregado
								encarregado == null ? "" : encarregado
										.getComponentes(),

								// Posição do Componente
								"",

								// Nome do Componente
								"",

								// Posição do Componente 2
								"",

								// Nome do Componente 2
								"",

								// Sequencial
								""
										+ ordemServicoProgramacao
												.getNnSequencialProgramacao(),

								// Número do RA
								idRegistroAtendimento,

								// Número da OS
								ordemServicoProgramacao.getOrdemServico()
										.getId().toString(),

								// Tipo de Serviço
								ordemServicoProgramacao.getOrdemServico()
										.getServicoTipo().getId().toString(),

								// Endereço da Ocorrência
								endereco,

								// Observação
								ordemServicoProgramacao.getOrdemServico()
										.getObservacao());

						// adiciona o bean a coleção
						relatorioBeans.add(relatorioBean);

					}

				}

			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_ROTEIRO_PROGRAMACAO, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.ROTEIRO_PROGRAMACAO,
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

		retorno = ((StringTokenizer) getParametro("nomesEquipes"))
				.countTokens();

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioRoteiroProgramacao", this);
	}

}