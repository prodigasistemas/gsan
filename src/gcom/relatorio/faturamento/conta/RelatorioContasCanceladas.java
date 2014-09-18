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

public class RelatorioContasCanceladas extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	
	private Map mapTotalAnos = null;
	
	public RelatorioContasCanceladas(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CONTAS_CANCELADAS);
	}
	
	@Deprecated
	public RelatorioContasCanceladas() {
		super(null, "");
	}
	
	//classe temporária criada para o totalizador geral dos anos. 
	private class TotaisAno{
		private String ano; 
		private int contadorContas = 0;		
		private BigDecimal sumValorCanceladas = new BigDecimal("0");		
		
		public TotaisAno(){}
		
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
		public BigDecimal getSumValorCanceladas() {
			return sumValorCanceladas;
		}
		public void setSumValorCanceladas(BigDecimal sumValorCanceladas) {
			this.sumValorCanceladas = sumValorCanceladas;
		}						
	}
	
	
	private Collection inicializarBeanRelatorio(
			Collection colecaoDados) {
		
		Fachada fachada = Fachada.getInstancia();
		
		mapTotalAnos = new HashMap();
		
		Collection retorno = new ArrayList();

		Iterator iter = colecaoDados.iterator();
		
		RelatorioContasCanceladasBean relatorioContasCanceladasBean = null;
		
		while (iter.hasNext()) {
			
			RelatorioContasCanceladasRetificadasHelper rel = (RelatorioContasCanceladasRetificadasHelper) iter.next();

			Localidade localidade = null;
			
			if(rel.getIdLocalidade() != null){
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, rel.getIdLocalidade()));
				Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
				localidade = (Localidade)colecaoLocalidade.iterator().next();
			}
			
			relatorioContasCanceladasBean = 
					new RelatorioContasCanceladasBean(
							rel.getCancelamento(),
							rel.getIdResponsavel(),
							rel.getEndereco(),
							rel.getReferencia(),
							rel.getIdMotivo(),
							rel.getValorCancelado(),
							rel.getIdRA(),
							rel.getInscricaoImovel(),
							rel.getIdLocalidade(),
							localidade != null ? localidade.getDescricao() : null,
						    rel.getMatricula() );
			
			/**
			 * Incluir opção de contas prescritas no relatório de contas
			 * canceladas/retificadas.
			 * @author Wellington Rocha
			 * @date 18/07/2012*/
			if(rel.getTipoConta().equals("3")){
				relatorioContasCanceladasBean.setIcRelatorioDeContasPrescritas("1");
			}else{
				relatorioContasCanceladasBean.setIcRelatorioDeContasPrescritas("2");
			}
			relatorioContasCanceladasBean.setCodigoSetorComercial(rel.getCodigoSetorComercial());
			relatorioContasCanceladasBean.setUnidadeNegocio(rel.getUnidadeNegocio());
			relatorioContasCanceladasBean.setIdUnidadeNegocio(rel.getIdUnidadeNegocio());
			relatorioContasCanceladasBean.setGerenciaRegional(rel.getGerenciaRegional());
			relatorioContasCanceladasBean.setIdGerenciaRegional(rel.getIdGerenciaRegional());
			relatorioContasCanceladasBean.setIdGrupo(rel.getGrupo());			
			
			retorno.add(relatorioContasCanceladasBean);
			
			//Preenche os valores dos totais do anos
			String ano = rel.getReferencia().substring(3);
					
			if(mapTotalAnos.containsKey(ano)){
				TotaisAno totaisAno = (TotaisAno) mapTotalAnos.get(ano);
				totaisAno.setContadorContas(totaisAno.getContadorContas() + 1);
				totaisAno.setSumValorCanceladas(totaisAno.getSumValorCanceladas().add(new BigDecimal(rel.getValorCancelado())));				
				
				mapTotalAnos.put(ano, totaisAno);				
			}else{
				TotaisAno totaisAno = new TotaisAno();
				
				totaisAno.setAno(ano);
				totaisAno.setContadorContas(1);
				totaisAno.setSumValorCanceladas( new BigDecimal(rel.getValorCancelado()) );				
								
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

		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
		parametros.put("mesAno", mesAno);

		RelatorioContasCanceladasRetificadasHelper helper = (RelatorioContasCanceladasRetificadasHelper) getParametro("relatorioContasCanceladasRetificadasHelper");

		parametros.put("grupo", helper.getGrupo());

		Collection colecaoContasCanceladas = fachada
				.gerarRelatorioContasCanceladas(helper);
		
		Collection colecaoBean = new ArrayList();
		//************************************************
		// CRC5059
		// Por: Ivan Sergio
		// 08/09/2010
		// Verifica se a consulta retornou algum dado.
		//************************************************
		if (colecaoContasCanceladas != null && !colecaoContasCanceladas.isEmpty()) {

			colecaoBean = this
					.inicializarBeanRelatorio(colecaoContasCanceladas);
	
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
					"idUnidadeNegocio", new Comparator() {
						public int compare(Object o1, Object o2) {
							Integer i = new Integer((String) o1);
							Integer i2 = new Integer((String) o2);
							return i.compareTo(i2);
						}
					});
	
			sortFields.add(ordenaUnidadeNegocio);
	
			BeanComparator ordenaLocalidade = new BeanComparator("idLocalidade",
					new Comparator() {
						public int compare(Object o1, Object o2) {
							Integer i = Integer.parseInt((String) o1);
							Integer i2 = Integer.parseInt((String) o2);
							return i.compareTo(i2);
						}
					});
	
			sortFields.add(ordenaLocalidade);
	
			//trecho que implementa os Totalizadores dos anos
			List colecaoTotais = null;
	
			if (mapTotalAnos != null && mapTotalAnos.size() != 0) {
	
				RelatorioContasCanceladasBean relatorioContasCanceladasBean = null;
				colecaoTotais = new ArrayList();
	
				Iterator iterator = mapTotalAnos.keySet().iterator();
	
				while (iterator.hasNext()) {
					relatorioContasCanceladasBean = new RelatorioContasCanceladasBean();
					TotaisAno totaisAno = (TotaisAno) mapTotalAnos
							.get((String) iterator.next());
	
					relatorioContasCanceladasBean.setAno(totaisAno.getAno());
					relatorioContasCanceladasBean.setQuantidadeContasAno(totaisAno
							.getContadorContas());
					relatorioContasCanceladasBean.setValorCanceladasAno(totaisAno
							.getSumValorCanceladas());
	
					colecaoTotais.add(relatorioContasCanceladasBean);
				}
			}
	
			// ORDENA COLEÇÃO POR ANO MES DE REFERENCIA DA CONTA
			Collections.sort(colecaoTotais, new Comparator() {
				public int compare(Object left, Object right) {
					RelatorioContasCanceladasBean leftKey = (RelatorioContasCanceladasBean) left;
					RelatorioContasCanceladasBean rightKey = (RelatorioContasCanceladasBean) right;
					return leftKey.getAno().compareTo(rightKey.getAno());
				}
			});
	
			RelatorioDataSource dataSourceAno = new RelatorioDataSource(
					colecaoTotais);
	
			parametros.put("DataSourceAno", dataSourceAno);
	
			if (relatorioTipo.equalsIgnoreCase("sintetico")) {
	
				BeanComparator ordenaReferencia = new BeanComparator("referencia",
						new Comparator() {
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
						ConstantesRelatorios.RELATORIO_CONTAS_CANCELADAS_SINTETICO,
						parametros, ds, tipoFormatoRelatorio);
			} else if (relatorioTipo.equalsIgnoreCase("analitico")) {
	
				if (ordenacaoTipo != null) {
	
					if (ordenacaoTipo.equals("2")) {
	
						BeanComparator ordenaData = new BeanComparator(
								"cancelamento", new Comparator() {
									public int compare(Object o1, Object o2) {
										String i = (String) o1;
										String i2 = (String) o2;
										Date i3 = Util.converteStringParaDate(i);
										Date i4 = Util.converteStringParaDate(i2);
										i = Util.formatarDataSemBarra(i3);
										i2 = Util.formatarDataSemBarra(i4);
	
										return i.compareTo(i2);
									}
								});
	
						sortFields.add(ordenaData);
					} else if (ordenacaoTipo.equals("1")) {
						BeanComparator ordenaInscricao = new BeanComparator(
								"inscricao", new Comparator() {
									public int compare(Object o1, Object o2) {
										String i = (String) o1;
	
										if (i == null) {
											i = "";
										}
										String i2 = (String) o2;
										if (i2 == null) {
											i2 = "";
										}
										return i.compareTo(i2);
									}
								});
						sortFields.add(ordenaInscricao);
					}
	
				}
	
				BeanComparator ordenaReferencia = new BeanComparator("referencia",
						new Comparator() {
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
						ConstantesRelatorios.RELATORIO_CONTAS_CANCELADAS,
						parametros, ds, tipoFormatoRelatorio);
			}
		}else {
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
						Relatorio.RELATORIO_CONTAS_CANCELADAS_SINTETICO,
						idFuncionalidadeIniciada);
			} else if (relatorioTipo.equalsIgnoreCase("analitico")) {
				persistirRelatorioConcluido(retorno,
						Relatorio.RELATORIO_CONTAS_CANCELADAS,
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
		
		RelatorioContasCanceladasRetificadasHelper helper = 
			(RelatorioContasCanceladasRetificadasHelper) 
				getParametro("relatorioContasCanceladasRetificadasHelper");
		
		Fachada fachada = Fachada.getInstancia();
		
		int retorno = 
			fachada.pesquisarQuantidadeContasCanceladasOuRetificadas(helper,1);
		
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioContasCanceladas", this);
	}
	
}
