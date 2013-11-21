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
package gcom.relatorio.cadastro.localidade;

import gcom.batch.Relatorio;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.Quadra;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corrêa
 * @created 9 de Setembro de 2005
 * @version 1.0
 */

public class RelatorioManterQuadra extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the RelatorioManterQuadra object
	 */
	public RelatorioManterQuadra(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_QUADRA_MANTER);
	}

	@Deprecated
	public RelatorioManterQuadra() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param quadraParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		// Recebe os parâmetros que serão utilizados no relatório
		FiltroQuadra filtroQuadra = (FiltroQuadra) getParametro("filtroQuadra");
		Quadra quadraParametros = (Quadra) getParametro("quadraParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório

		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterQuadraBean relatorioBean = null;

		// Cria adiciona os carregamentos dos objetos necessários para
		// a impressão do relatório
		filtroQuadra
				.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("quadraPerfil");
		filtroQuadra
				.adicionarCaminhoParaCarregamentoEntidade("bacia.sistemaEsgoto");
		filtroQuadra
				.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional");
		filtroQuadra
				.adicionarCaminhoParaCarregamentoEntidade("ibgeSetorCensitario");
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("zeis");
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("areaTipo");
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota");

		filtroQuadra.setConsultaSemLimites(true);

		// Nova consulta para trazer objeto completo
		Collection quadrasNovas = fachada.pesquisar(filtroQuadra, Quadra.class
				.getName());

		Collections.sort((List) quadrasNovas, new Comparator() {
			public int compare(Object a, Object b) {
				String idLocal1 = ""
						+ ((Quadra) a).getSetorComercial().getLocalidade()
								.getId();
				String idLocal2 = ""
						+ ((Quadra) b).getSetorComercial().getLocalidade()
								.getId();
				if (idLocal1.compareTo(idLocal2) == 0) {

					String idSetor1 = ""
							+ ((Quadra) a).getSetorComercial().getId();
					String idSetor2 = ""
							+ ((Quadra) b).getSetorComercial().getId();

					if (idSetor1.compareTo(idSetor2) == 0) {
						Integer numQuadra1 = ((Quadra) a).getNumeroQuadra();
						Integer numQuadra2 = ((Quadra) b).getNumeroQuadra();

						return numQuadra1.compareTo(numQuadra2);

					} else {

						return idSetor1.compareTo(idSetor2);
					}

				} else {

					return idLocal1.compareTo(idLocal2);
				}
			}
		});
		
			
			
			if (quadrasNovas != null && !quadrasNovas.isEmpty()) {
				// coloca a coleção de parâmetros da analise no iterator
				Iterator quadraNovaIterator = quadrasNovas.iterator();

				while (quadraNovaIterator.hasNext()) {

					Quadra quadraNova = (Quadra) quadraNovaIterator.next();
					
					//Consulta do indicador da quadra face do Sistema Parametro e verifica se contem Quadra Face
					SistemaParametro sistParametro = fachada.pesquisarParametrosDoSistema();
					String indicadorQuadraFace = "" + sistParametro.getIndicadorQuadraFace();
					
					Integer idQuadra = quadraNova.getId();
					
					// Faz as validações dos campos necessáriose e formata a String
					// para a forma como irá aparecer no relatório
					String tipoArea = "";

					if (quadraNova.getAreaTipo() != null) {
						tipoArea = quadraNova.getAreaTipo().getDescricaoAbreviada();
					}

					String indicadorRedeAgua = "";

					if (quadraNova.getIndicadorRedeAgua() != null
							&& quadraNova.getIndicadorRedeAgua().equals(
									new Short("2"))) {
						
						indicadorRedeAgua = "SIM";
						
					} else if (quadraNova.getIndicadorRedeAgua() != null
							&& quadraNova.getIndicadorRedeAgua().equals(
									new Short("1"))) {
						
						indicadorRedeAgua = "NÃO";
						
					} else {
						indicadorRedeAgua = "PARCIAL";
					}
					
					String indicadorRedeEsgoto = "";

					if (quadraNova.getIndicadorRedeEsgoto() != null
							&& quadraNova.getIndicadorRedeEsgoto().equals(
									new Short("2"))) {
						
						indicadorRedeEsgoto = "SIM";
						
					} else if (quadraNova.getIndicadorRedeEsgoto() != null
							&& quadraNova.getIndicadorRedeEsgoto().equals(
									new Short("1"))) {
						
						indicadorRedeEsgoto = "NÃO";
						
					} else {
						indicadorRedeEsgoto = "PARCIAL";
					}

					// Inicializa o construtor constituído dos campos
					// necessários para a impressão do relatorio
					relatorioBean = new RelatorioManterQuadraBean(
					// Número da Quadra
							"" + quadraNova.getNumeroQuadra(),

							// Descrição Localidade
							quadraNova.getSetorComercial().getLocalidade()
									.getDescricao(),

							// Código Setor Comercial
							"" + quadraNova.getSetorComercial().getCodigo(),

							// Descrição Setor Comercial
							quadraNova.getSetorComercial().getDescricao(),

							// Quadra Perfil
							quadraNova.getQuadraPerfil().getDescricao(),

							// Tipo de Área
							tipoArea,

							// Indicador Rede Água
							indicadorRedeAgua,

							// Indicador Rede Esgoto
							indicadorRedeEsgoto,

							// Campos que ficam em branco para serem setados
							// após o construtor, evitando, assim, que ocorra
							// erros
							// devido ao fato de alguns objetos estarem nulos.
							// Esses campos são: bacia, distrito operacional,
							// setor censitário e zeis
							"", "", "", "", "",

							// Rota
							quadraNova.getRota().getCodigo().toString(),

							// Indicador Uso
							quadraNova.getIndicadorUso().toString(),
							
							//Indicador de Face da Quadra
							indicadorQuadraFace,
							
							//Id da Face da Quadra
							null,
							
							//Bacia da Quadra Face
							null,
							
							//Sistems Esgoto da Quadra Face
							null,
							
							//Id Quadra
							""+ idQuadra,
							
							//Rede de Agua da Quadra Face
							null,
							
							//Distrito Operacional da Quadra Face
							null,
							
							//Descricao Sistema Esgoto Quadra Face
							null
							);

					// Bacia
					if (quadraNova.getBacia() != null) {
						relatorioBean.setSistemaEsgoto(quadraNova.getBacia()
								.getSistemaEsgoto().getDescricao());
						relatorioBean
								.setBacia(quadraNova.getBacia().getDescricao());
					}

					// Distrito Operacional
					if (quadraNova.getDistritoOperacional() != null) {
						relatorioBean.setDistritoOperacional(quadraNova
								.getDistritoOperacional().getDescricaoAbreviada());
					}

					// Setor Censitário
					if (quadraNova.getIbgeSetorCensitario() != null) {
						relatorioBean.setSetorCensitario(quadraNova
								.getIbgeSetorCensitario().getDescricao());
					}

					// Zeis
					if (quadraNova.getZeis() != null) {
						relatorioBean.setZeis(quadraNova.getZeis()
								.getDescricaoAbreviada());
					}
						
					//Verifica o parametro da empresa se trabalha com Quadra Face.
					if ( indicadorQuadraFace.equals("1") ) {
						
						//Pesquisa que retorna a Quadra face associada a Quadra
						Collection<Object[]> quadraFace = null;
						quadraFace = fachada.pesquisarQuadraFaceAssociadaQuadra(idQuadra);
						
						if ( quadraFace != null && !quadraFace.isEmpty() ) {
							
							
							Iterator quadraFaceNovaIterator = quadraFace.iterator();
							
							while ( quadraFaceNovaIterator.hasNext() ) { 
								
								Object[] objeto = (Object[]) quadraFaceNovaIterator.next();
								
								Integer numeroQuadraFace = null;
								String baciaQuadraFace = "";
								String distritoOperacionalQuadraFace = "";
								Short indicadorAgua = null;
								Short indicadorEsgoto = null;
								String descricaoSistemaEsgotoQuadraFace = "";
								String redeEsgoto = "";
								String redeAgua = "";
								
								//ID da Quadra Face
								if ( objeto[0] != null ) {
									numeroQuadraFace = (Integer) objeto[0];
								}
								
								//Bacia da Quadra Face
								if ( objeto[1] != null ) {
									baciaQuadraFace = (String) objeto[1];
								}

								//Indicador Rede De Esgoto
								if ( objeto[2] != null ) {
									indicadorEsgoto = (Short) objeto[2];
								}
								
								if (indicadorEsgoto.equals(new Short("2")) ){
									redeEsgoto = "SIM";
								} else if (indicadorEsgoto.equals(new Short("1")) ){
									redeEsgoto = "NÃO";
								} else {
									redeEsgoto = "PARCIAL";
								}
								
								//Indicador de Agua
								if ( objeto[3] != null ) {
									indicadorAgua = (Short) objeto[3];
								}
								
								if (indicadorAgua.equals(new Short("2")) ){
									redeAgua = "SIM";
								} else if (indicadorAgua.equals(new Short("1")) ){
									redeAgua = "NÃO";
								} else {
									redeAgua = "PARCIAL";
								}
								
								//Distrito Operacional da Quadra Face
								if ( objeto[4] != null ) {
									distritoOperacionalQuadraFace = ( String ) objeto[4];
								}
								
								//Sistema Esgoto Quadra Face
								if ( objeto[5] != null ) {
									descricaoSistemaEsgotoQuadraFace = (String) objeto[5];
								}
								//Inicializa o construtor constituído dos campos
								// necessários para a impressão do relatorio
								relatorioBean = new RelatorioManterQuadraBean(
								// Número da Quadra
										"" + quadraNova.getNumeroQuadra(),

										// Descrição Localidade
										quadraNova.getSetorComercial().getLocalidade()
												.getDescricao(),

										// Código Setor Comercial
										"" + quadraNova.getSetorComercial().getCodigo(),

										// Descrição Setor Comercial
										quadraNova.getSetorComercial().getDescricao(),

										// Quadra Perfil
										quadraNova.getQuadraPerfil().getDescricao(),

										// Tipo de Área
										tipoArea,

										// Indicador Rede Água
										indicadorRedeAgua,

										// Indicador Rede Esgoto
										indicadorRedeEsgoto,

										// Campos que ficam em branco para serem setados
										// após o construtor, evitando, assim, que ocorra
										// erros
										// devido ao fato de alguns objetos estarem nulos.
										// Esses campos são: bacia, distrito operacional,
										// setor censitário e zeis
										"", "", "", "", "",

										// Rota
										quadraNova.getRota().getCodigo().toString(),

										// Indicador Uso
										quadraNova.getIndicadorUso().toString(),
										
										//Indicador de Face da Quadra
										indicadorQuadraFace,
										
										//Id da Face da Quadra
										"" + numeroQuadraFace,
										
										//Bacia da Quadra Face
										baciaQuadraFace,
										
										//Sistems Esgoto da Quadra Face
										redeEsgoto,
										
										//Id Quadra
										""+ idQuadra,
										
										//Rede de Agua da Quadra Face
										redeAgua,
										
										//Distrito Operacional da Quadra Face
										distritoOperacionalQuadraFace,
										
										//Descricao Sistema Esgoto Quadra Face
										descricaoSistemaEsgotoQuadraFace
										);

								// Bacia
								if (quadraNova.getBacia() != null) {
									relatorioBean.setSistemaEsgoto(quadraNova.getBacia()
											.getSistemaEsgoto().getDescricao());
									relatorioBean
											.setBacia(quadraNova.getBacia().getDescricao());
								}

								// Distrito Operacional
								if (quadraNova.getDistritoOperacional() != null) {
									relatorioBean.setDistritoOperacional(quadraNova
											.getDistritoOperacional().getDescricaoAbreviada());
								}

								// Setor Censitário
								if (quadraNova.getIbgeSetorCensitario() != null) {
									relatorioBean.setSetorCensitario(quadraNova
											.getIbgeSetorCensitario().getDescricao());
								}

								// Zeis
								if (quadraNova.getZeis() != null) {
									relatorioBean.setZeis(quadraNova.getZeis()
											.getDescricaoAbreviada());
								}
								// adiciona o bean a coleção
								relatorioBeans.add(relatorioBean);
							}
						} else {
							relatorioBeans.add(relatorioBean);
						}
					} else {
						relatorioBeans.add(relatorioBean);
					}
			}
		}
		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("quadra", quadraParametros.getNumeroQuadra() == -1 ? ""
				: "" + quadraParametros.getNumeroQuadra());

		parametros.put("idLocalidade", quadraParametros.getSetorComercial()
				.getLocalidade().getId() == null ? "" : ""
				+ quadraParametros.getSetorComercial().getLocalidade().getId());

		parametros.put("nomeLocalidade", quadraParametros.getSetorComercial()
				.getLocalidade().getDescricao());

		parametros.put("idSetorComercial", quadraParametros.getSetorComercial()
				.getCodigo() == 0 ? "" : ""
				+ quadraParametros.getSetorComercial().getCodigo());

		parametros.put("nomeSetorComercial", quadraParametros
				.getSetorComercial().getDescricao());
		
		if (quadraParametros.getRota() != null) {
			parametros.put("codigoRota", quadraParametros.getRota().getCodigo().toString());
		} else {
			parametros.put("codigoRota", "");
		}

		String indicadorUso = "";

		if (quadraParametros.getIndicadorUso() != null
				&& !quadraParametros.getIndicadorUso().equals("")) {
			if (quadraParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (quadraParametros.getIndicadorUso()
					.equals(new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}

		parametros.put("indicadorUso", indicadorUso);

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_QUADRA_MANTER, parametros, ds,
				tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_QUADRA,
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

		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
				(FiltroQuadra) getParametro("filtroQuadra"),
				Quadra.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterQuadra", this);
	}

}
