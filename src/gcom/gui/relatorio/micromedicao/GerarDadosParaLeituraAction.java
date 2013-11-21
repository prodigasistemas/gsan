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
package gcom.gui.relatorio.micromedicao;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.micromedicao.bean.GerarDadosParaLeituraHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.micromedicao.RelatorioGerarDadosParaleitura;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarDadosParaLeituraAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirGerarDadosParaLeitura");

		DiskFileUpload upload = new DiskFileUpload();
		List items = null;
		try {
			items = upload.parseRequest(httpServletRequest);
			this.getSessao(httpServletRequest).setAttribute("arquivo", items);
		} catch (FileUploadException e) {
			items = (List) this.getSessao(httpServletRequest).getAttribute(
					"arquivo");
			if (items == null) {
				throw new ActionServletException("erro.sistema", e);
			}

		}

		Collection colecaoGerarDadosParaLeituraHelper = this
				.gerarColecaoDadosParaLeituraHelper(items);

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioGerarDadosParaleitura relatorio = new RelatorioGerarDadosParaleitura(
				this.getUsuarioLogado(httpServletRequest));

		relatorio.addParametro("colecaoGerarDadosParaLeituraHelper",
				colecaoGerarDadosParaLeituraHelper);

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		return retorno;
	}

	private Collection gerarColecaoDadosParaLeituraHelper(List items) {

		Collection retorno = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		FileItem item = null;
		String nomeItem = null;
		String identificacaoArquivo = null;
		String grupo = "1";

		// Parse the request
		try {

			Iterator iter = items.iterator();
			while (iter.hasNext()) {

				item = (FileItem) iter.next();

				// verifica se não é diretorio
				if (!item.isFormField()) {

					// coloca o nome do item para maiusculo
					nomeItem = item.getName().toUpperCase();
					
					/*
					 * Colocado por Raphael Rossiter em 26/09/2007
					 * OBJ: Processar os arquivos que serão gerados na empresa CAER. Os arquivos 
					 * de leitura nessa empresa são gerados sem extensão .TXT
					 * 
					 * Nomenclatura do arquivo: Rt0000
					 */
					identificacaoArquivo =  nomeItem.substring( 0, 2 ); //nomeItem.substring(nomeItem.length() - 6, nomeItem.length() - 4);
					
					// compara o final do nome do arquivo é .txt
					if (nomeItem.endsWith(".TXT") || identificacaoArquivo.equalsIgnoreCase("RT")) {

						// abre o arquivo
						InputStreamReader reader = new InputStreamReader(item
								.getInputStream());

						BufferedReader buffer = new BufferedReader(reader);

						// cria uma variavel do tipo boolean
						boolean eof = false;

						GerarDadosParaLeituraHelper gerarDadosParaLeituraHelper = null;

						boolean primeiraLinha = true;
						Integer matriculaImovel = null;

						// enquanto a variavel for false
						while (!eof) {
							// pega a linha do arquivo
							String linhaLida = buffer.readLine();

							// se for a ultima linha do arquivo
							if (linhaLida == null
									|| linhaLida.trim().length() == 0) {
								// seta a variavel boolean para true
								eof = true;
							} else {
								
								gerarDadosParaLeituraHelper = new GerarDadosParaLeituraHelper();

								if (primeiraLinha) {
									
									String linhaLida2 = buffer.readLine();
									
									//MATRICULA IMOVEL
									matriculaImovel = Util
									.converterStringParaInteger(linhaLida2.substring(0, 7));
									
									//OBTÉM O GRUPO A PARTIR DA MATRICULA DO IMOVEL
									FaturamentoGrupo faturamentoGrupo = fachada.pesquisarGrupoImovel(matriculaImovel);
									grupo = faturamentoGrupo.getId().toString();
									
									gerarDadosParaLeituraHelper
											.setAnoMesReferncia(Util
													.formatarAnoMesParaMesAno(linhaLida
															.substring(0, 6)));
									if (grupo != null) {
										gerarDadosParaLeituraHelper
												.setGrupo(grupo);
									}

									try {
										Date dataPrevista = fachada
												.pesquisarFaturamentoAtividadeCronogramaDataPrevista(
														Integer.parseInt(grupo),
														FaturamentoAtividade.EFETUAR_LEITURA,
														Integer
																.parseInt(linhaLida
																		.substring(
																				0,
																				6)));

										gerarDadosParaLeituraHelper
												.setDataPrevistaFaturamento(Util
														.formatarData(dataPrevista));

									} catch (NumberFormatException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									retorno.add(gerarDadosParaLeituraHelper);
									primeiraLinha = false;
									linhaLida = linhaLida2;
								}
								
								//MATRICULA IMOVEL
								matriculaImovel = Util
								.converterStringParaInteger(linhaLida.substring(0, 7));
								
								if (retorno.size() == 1) {

									GerarDadosParaLeituraHelper gerarDadosParaLeituraHelperCadastrado = (GerarDadosParaLeituraHelper) Util
											.retonarObjetoDeColecao(retorno);

									if (matriculaImovel != null) {
										Object[] dadosLocalidadeSetor = fachada
												.pesquisarLocalidadeSetorImovel(matriculaImovel);
										if (dadosLocalidadeSetor != null) {
											// descrição da localidade
											if (dadosLocalidadeSetor[0] != null) {
												gerarDadosParaLeituraHelperCadastrado
														.setDescricaoLocalidade(""
																+ dadosLocalidadeSetor[0]);
											}
											// codigo do setor
											if (dadosLocalidadeSetor[1] != null) {
												gerarDadosParaLeituraHelperCadastrado
														.setCodigoSetor(""
																+ dadosLocalidadeSetor[1]);
											}
											Integer codigoRota = Util
													.converterStringParaInteger(linhaLida
															.substring(7, 11));
											gerarDadosParaLeituraHelperCadastrado
													.setCodigoRota(""
															+ codigoRota);
										}
									}

								}

								// matricula do imovel
								if (matriculaImovel != null) {
									gerarDadosParaLeituraHelper
											.setMatriculaImovel(Util
													.retornaMatriculaImovelFormatada(matriculaImovel));
								}

								Integer sequencialRota = fachada
										.pesquisarSequencialRota(matriculaImovel);

								// sequencial da rota
								if (sequencialRota != null) {
									gerarDadosParaLeituraHelper
											.setSequencialRota(""
													+ sequencialRota);
								}

								// nome do usuário
								String nomeClienteUsuario = fachada
										.pesquisarNomeClientePorImovel(matriculaImovel);
								if (nomeClienteUsuario != null) {
									gerarDadosParaLeituraHelper
											.setNomeClienteUsuario(nomeClienteUsuario);
								}

								// endereço do imovel
								String enderecoImovel = fachada.pesquisarEnderecoAbreviadoCAER(matriculaImovel);
								
								if (enderecoImovel != null) {
									gerarDadosParaLeituraHelper
											.setEnderecoImovel(enderecoImovel);
								}

								// numero do hidrometro
								gerarDadosParaLeituraHelper
										.setNumeroHidrometro(linhaLida
												.substring(58, 68));

								// descrição do local de instalação e proteção
								Object[] dadosLocalProtecaoAgua = fachada
										.pesquisarDadosLocalProtecaoTipoLigacaoAgua(matriculaImovel);
								if (dadosLocalProtecaoAgua != null) {
									// descrição local de armazenagem
									if (dadosLocalProtecaoAgua[0] != null) {
										gerarDadosParaLeituraHelper
												.setLocalInstalacao(""
														+ dadosLocalProtecaoAgua[0]);
									}
									// descrição proteção
									if (dadosLocalProtecaoAgua[1] != null) {
										gerarDadosParaLeituraHelper
												.setProtecao(""
														+ dadosLocalProtecaoAgua[1]);
									}

								} else {
									Object[] dadosLocalProtecaPoco = fachada
											.pesquisarDadosLocalProtecaoTipoTipoPoco(matriculaImovel);
									if (dadosLocalProtecaPoco != null) {
										// descrição local de armazenagem
										if (dadosLocalProtecaPoco[0] != null) {
											gerarDadosParaLeituraHelper
													.setLocalInstalacao(""
															+ dadosLocalProtecaPoco[0]);
										}
										// descrição proteção
										if (dadosLocalProtecaPoco[1] != null) {
											gerarDadosParaLeituraHelper
													.setProtecao(""
															+ dadosLocalProtecaPoco[1]);
										}
									}
								}

								// inscrição do imovel
								String inscricaoImovel = fachada
										.pesquisarInscricaoImovel(matriculaImovel);
								if (inscricaoImovel != null) {
									gerarDadosParaLeituraHelper
											.setInscricao(inscricaoImovel);
								}

								retorno.add(gerarDadosParaLeituraHelper);

							}
						}// fim do while eof
					}
				}
			}

		} catch (IOException ex) {
			throw new ActionServletException("erro.importacao.nao_concluida");
		}

		return retorno;
	}

}