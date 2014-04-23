package gcom.relatorio.faturamento.conta;

import gcom.batch.Relatorio;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ComparatorChain;

/**
 * [UC] 
 * @author Flavio Cordeiro
 * @date 14/02/2007
 */

public class RelatorioContasRetificadas extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	private Map mapTotalAnos = null;

	public RelatorioContasRetificadas(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CONTAS_RETIFICADAS);
	}

	@Deprecated
	public RelatorioContasRetificadas() {
		super(null, "");
	}

	//classe temporária criada para o totalizador geral dos anos. 
	private class TotaisAno {
		private String ano;

		private int contadorContas = 0;

		private BigDecimal sumValorOriginal = new BigDecimal("0");

		private BigDecimal sumValorNovo = new BigDecimal("0");

		public TotaisAno() {
		}

		public String getAno() {
			return ano;
		}

		public void setAno(String ano) {
			this.ano = ano;
		}

		public int getContadorContas() {
			return contadorContas;
		}

		public void setContadorContas(int contadorContas) {
			this.contadorContas = contadorContas;
		}

		public BigDecimal getSumValorOriginal() {
			return sumValorOriginal;
		}

		public void setSumValorOriginal(BigDecimal sumValorOriginal) {
			this.sumValorOriginal = sumValorOriginal;
		}

		public BigDecimal getSumValorNovo() {
			return sumValorNovo;
		}

		public void setSumValorNovo(BigDecimal sumValorNovo) {
			this.sumValorNovo = sumValorNovo;
		}
	}

	private Collection inicializarBeanRelatorio(Collection colecaoDados) {

		Fachada fachada = Fachada.getInstancia();

		Collection retorno = new ArrayList();

		mapTotalAnos = new HashMap();

		Iterator iter = colecaoDados.iterator();

		RelatorioContasRetificadasBean relatorioContasRetificadasBean = null;

		while (iter.hasNext()) {

			RelatorioContasCanceladasRetificadasHelper rel = (RelatorioContasCanceladasRetificadasHelper) iter
					.next();

			Localidade localidade = null;

			if (rel.getIdLocalidade() != null) {
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, rel.getIdLocalidade()));
				Collection colecaoLocalidade = fachada.pesquisar(
						filtroLocalidade, Localidade.class.getName());
				localidade = (Localidade) colecaoLocalidade.iterator().next();
			}

			relatorioContasRetificadasBean = new RelatorioContasRetificadasBean(
					rel.getCancelamento(), rel.getIdResponsavel(), rel
							.getEndereco(), rel.getReferencia(), rel
							.getIdMotivo(), rel.getValorOriginal(), rel
							.getValorNovo(), rel.getIdRA(), rel
							.getInscricaoImovel(), rel.getIdLocalidade(),
					localidade != null ? localidade.getDescricao() : null, rel
							.getMatricula());
			relatorioContasRetificadasBean.setUnidadeNegocio(rel
					.getUnidadeNegocio());
			relatorioContasRetificadasBean.setIdUnidadeNegocio(rel
					.getIdUnidadeNegocio());
			relatorioContasRetificadasBean.setGerenciaRegional(rel
					.getGerenciaRegional());
			relatorioContasRetificadasBean.setIdGerenciaRegional(rel
					.getIdGerenciaRegional());

			retorno.add(relatorioContasRetificadasBean);

			//Preenche os valores dos totais do anos
			String ano = rel.getReferencia().substring(3);

			if (mapTotalAnos.containsKey(ano)) {
				TotaisAno totaisAno = (TotaisAno) mapTotalAnos.get(ano);
				totaisAno.setContadorContas(totaisAno.getContadorContas() + 1);
				totaisAno.setSumValorNovo(totaisAno.getSumValorNovo().add(
						new BigDecimal(rel.getValorNovo())));
				totaisAno.setSumValorOriginal(totaisAno.getSumValorOriginal()
						.add(new BigDecimal(rel.getValorOriginal())));

				mapTotalAnos.put(ano, totaisAno);
			} else {
				TotaisAno totaisAno = new TotaisAno();

				totaisAno.setAno(ano);
				totaisAno.setContadorContas(1);
				totaisAno.setSumValorNovo(new BigDecimal(rel.getValorNovo()));
				totaisAno.setSumValorOriginal(new BigDecimal(rel
						.getValorOriginal()));

				mapTotalAnos.put(ano, totaisAno);
			}
		}

		return retorno;
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 * 
	 */
	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Fachada fachada = Fachada.getInstancia();

		String mesAno = (String) getParametro("mesAno");
		String relatorioTipo = (String) getParametro("relatorioTipo");
		String ordenacaoTipo = (String) getParametro("ordenacaoTipo");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map parametros = new HashMap();

		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
		parametros.put("mesAno", mesAno);

		RelatorioContasCanceladasRetificadasHelper helper = 
			(RelatorioContasCanceladasRetificadasHelper) getParametro("relatorioContasCanceladasRetificadasHelper");

		parametros.put("grupo", helper.getGrupo());

		Collection colecaoContasRetificadas = fachada.gerarRelatorioContasRetificadas(helper);

		Collection colecaoBean = new ArrayList();
		
		//************************************************
		// CRC5059
		// Por: Ivan Sergio
		// 08/09/2010
		// Verifica se a consulta retornou algum dado.
		//************************************************
		if (colecaoContasRetificadas != null && !colecaoContasRetificadas.isEmpty()) {
			colecaoBean = this.inicializarBeanRelatorio(colecaoContasRetificadas);

			ArrayList sortFields = new ArrayList();

			BeanComparator ordenaGerenciaRegional = new BeanComparator(
					"idGerenciaRegional", new Comparator() {
						public int compare(Object o1, Object o2) {
							Integer i = new Integer((String) o1);
							Integer i2 = new Integer((String) o2);
							return i.compareTo(i2);
						}
					});

			sortFields.add(ordenaGerenciaRegional);

			BeanComparator ordenaUnidadeNegocio = new BeanComparator(
					"unidadeNegocio", new Comparator() {
						public int compare(Object o1, Object o2) {
							String i = (String) o1;
							String i2 = (String) o2;
							return i.compareTo(i2);
						}
					});

			sortFields.add(ordenaUnidadeNegocio);

			BeanComparator ordenaLocalidade = new BeanComparator(
					"idLocalidade", new Comparator() {
						public int compare(Object o1, Object o2) {
							Integer i = Integer.parseInt((String) o1);
							Integer i2 = Integer.parseInt((String) o2);
							return i.compareTo(i2);
						}
					});

			sortFields.add(ordenaLocalidade);

			//	trecho que implementa os Totalizadores dos anos
			List colecaoTotais = null;

			if (mapTotalAnos != null && mapTotalAnos.size() != 0) {

				RelatorioContasRetificadasBean relatorioContasRetificadasBean = null;
				colecaoTotais = new ArrayList();

				Iterator iterator = mapTotalAnos.keySet().iterator();

				while (iterator.hasNext()) {
					relatorioContasRetificadasBean = new RelatorioContasRetificadasBean();
					TotaisAno totaisAno = (TotaisAno) mapTotalAnos.get((String) iterator.next());

					relatorioContasRetificadasBean.setAno(totaisAno.getAno());
					relatorioContasRetificadasBean.setQuantidadeContasAno(totaisAno.getContadorContas());
					relatorioContasRetificadasBean.setValorOriginalAno(totaisAno.getSumValorOriginal());
					relatorioContasRetificadasBean.setValorNovoAno(totaisAno.getSumValorNovo());

					colecaoTotais.add(relatorioContasRetificadasBean);
				}
			}

			// ORDENA COLEÇÃO POR ANO MES DE REFERENCIA DA CONTA
			Collections.sort(colecaoTotais, new Comparator() {
				public int compare(Object left, Object right) {
					RelatorioContasRetificadasBean leftKey = (RelatorioContasRetificadasBean) left;
					RelatorioContasRetificadasBean rightKey = (RelatorioContasRetificadasBean) right;
					return leftKey.getAno().compareTo(rightKey.getAno());
				}
			});

			RelatorioDataSource dataSourceAno = new RelatorioDataSource(colecaoTotais);
			parametros.put("DataSourceAno", dataSourceAno);

			if (relatorioTipo.equalsIgnoreCase("sintetico")) {
				BeanComparator ordenaReferencia = new BeanComparator(
						"referencia", new Comparator() {
							public int compare(Object o1, Object o2) {
								Integer i = Integer
										.parseInt(Util
												.formatarMesAnoParaAnoMesSemBarra((String) o1));
								Integer i2 = Integer
										.parseInt(Util
												.formatarMesAnoParaAnoMesSemBarra((String) o2));
								return i.compareTo(i2);
							}
						});

				sortFields.add(ordenaReferencia);

				ComparatorChain multiSort = new ComparatorChain(sortFields);
				Collections.sort((ArrayList) colecaoBean, multiSort);

				RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);
				retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_CONTAS_RETIFICADAS_SINTETICO,
								parametros, ds, tipoFormatoRelatorio);
			} else if (relatorioTipo.equalsIgnoreCase("analitico")) {
				if (ordenacaoTipo != null) {

					if (ordenacaoTipo.equals("2")) {
						BeanComparator ordenaData = new BeanComparator(
								"dataRetificacao", new Comparator() {
									public int compare(Object o1, Object o2) {
										String i = (String) o1;
										Date d = Util.converteStringParaDate(i);
										i = Util.formatarDataSemBarra(d);
										String i2 = (String) o2;
										Date j = Util
												.converteStringParaDate(i2);
										i2 = Util.formatarDataSemBarra(j);
										return i.compareTo(i2);
									}
								});

						sortFields.add(ordenaData);
					} else if (ordenacaoTipo.equals("1")) {
						BeanComparator ordenaInscricao = new BeanComparator(
								"inscricao", new Comparator() {
									public int compare(Object o1, Object o2) {
										String i = (String) o1;
										String i2 = (String) o2;
										if ( i == null || i2 == null ) {
											return 0;
										} else {
										
										return i.compareTo(i2);
										}
									}
								});
						sortFields.add(ordenaInscricao);
					}
				}

				BeanComparator ordenaReferencia = new BeanComparator(
						"referencia", new Comparator() {
							public int compare(Object o1, Object o2) {
								Integer i = Integer
										.parseInt(Util
												.formatarMesAnoParaAnoMesSemBarra((String) o1));
								Integer i2 = Integer
										.parseInt(Util
												.formatarMesAnoParaAnoMesSemBarra((String) o2));
								return i.compareTo(i2);
							}
						});
				sortFields.add(ordenaReferencia);

				ComparatorChain multiSort = new ComparatorChain(sortFields);
				Collections.sort((ArrayList) colecaoBean, multiSort);
				RelatorioDataSource ds = new RelatorioDataSource(
						(java.util.List) colecaoBean);
				retorno = this.gerarRelatorio(
						ConstantesRelatorios.RELATORIO_CONTAS_RETIFICADAS,
						parametros, ds, tipoFormatoRelatorio);
			}
		} else {
			//************************************************
			// Verifica se o relatorio está sendo executado
			// como batch ou online;
			//************************************************
			if (idFuncionalidadeIniciada.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new RelatorioVazioException("atencao.relatorio.vazio");
			}else {
				//************************************************
				// Gera o Relatório em Branco
				//************************************************
				RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);
				retorno = this.gerarRelatorio(
						ConstantesRelatorios.RELATORIO_CONTAS_RETIFICADAS,
						parametros, ds, tipoFormatoRelatorio);
			}
		}
		// ------------------------------------
		// Grava o relatório no sistema

		try {

			if (relatorioTipo.equalsIgnoreCase("sintetico")) {
				persistirRelatorioConcluido(retorno,
						Relatorio.RELATORIO_CONTAS_RETIFICADAS_SINTETICO,
						idFuncionalidadeIniciada);

			} else if (relatorioTipo.equalsIgnoreCase("analitico")) {
				persistirRelatorioConcluido(retorno,
						Relatorio.RELATORIO_CONTAS_RETIFICADAS,
						idFuncionalidadeIniciada);
			}
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

		RelatorioContasCanceladasRetificadasHelper relatorioContasCanceladasRetificadasHelper = (RelatorioContasCanceladasRetificadasHelper) getParametro("relatorioContasCanceladasRetificadasHelper");

		Fachada fachada = Fachada.getInstancia();

		Integer retorno = fachada
				.pesquisarQuantidadeContasCanceladasOuRetificadas(
						relatorioContasCanceladasRetificadasHelper, 2);

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioContasRetificadas", this);
	}

}
