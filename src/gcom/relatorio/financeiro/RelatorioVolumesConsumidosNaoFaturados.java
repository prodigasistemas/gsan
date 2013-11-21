package gcom.relatorio.financeiro;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RelatorioVolumesConsumidosNaoFaturados extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	private Integer tipoRelatorio;
	private Integer mesAno;
	private String opcaoTotalizacao;
	private Integer idEntidade;

	public RelatorioVolumesConsumidosNaoFaturados(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_VOLUMES_CONSUMIDOS_NAO_FATURADOS);
	}
	
	@Deprecated
	public RelatorioVolumesConsumidosNaoFaturados() {
		super(null, "");
	}

	@Override
	public Object executar() throws TarefaException {
		
		tipoRelatorio = (Integer) getParametro("tipoRelatorio");
		mesAno = (Integer) getParametro("mesAno");
		opcaoTotalizacao = (String) getParametro("opcaoTotalizacao");
		idEntidade = (Integer) getParametro("idEntidade");
		
		Fachada fachada = Fachada.getInstancia();
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<RelatorioVolumesConsumidosNaoFaturadosBean> beans = new ArrayList<RelatorioVolumesConsumidosNaoFaturadosBean>();
		
		popularParametros(parametros);
		
		Collection<RelatorioVolumesConsumidosNaoFaturadosBean> pesquisa = 
			fachada.pesquisarVolumesConsumidosNaoFaturados(mesAno, opcaoTotalizacao, idEntidade);
		
		if (pesquisa == null || pesquisa.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Volumes Consumidos Nao Faturados");
		}
		
		// ------------------------------
		// -- Estrutura de Dados para Agrupamento
		// ------------------------------
		
		Map<Integer, RelatorioVolumesConsumidosNaoFaturadosBean> estadoBeans = new HashMap<Integer, RelatorioVolumesConsumidosNaoFaturadosBean>();
		Map<Integer, RelatorioVolumesConsumidosNaoFaturadosBean> unidadeBeans = new HashMap<Integer, RelatorioVolumesConsumidosNaoFaturadosBean>();
		Map<Integer, RelatorioVolumesConsumidosNaoFaturadosBean> gerenciaBeans = new HashMap<Integer, RelatorioVolumesConsumidosNaoFaturadosBean>();
		
		RelatorioVolumesConsumidosNaoFaturadosBean totalEstadoBean = new RelatorioVolumesConsumidosNaoFaturadosBean("TOT");
		RelatorioVolumesConsumidosNaoFaturadosBean totalGerenciaBean = new RelatorioVolumesConsumidosNaoFaturadosBean("TOT");
		RelatorioVolumesConsumidosNaoFaturadosBean totalUnidadeBean =  new RelatorioVolumesConsumidosNaoFaturadosBean("TOT");
		RelatorioVolumesConsumidosNaoFaturadosBean totalLocalidadeBean = new RelatorioVolumesConsumidosNaoFaturadosBean("TOT");
		RelatorioVolumesConsumidosNaoFaturadosBean totalMunicipioBean = new RelatorioVolumesConsumidosNaoFaturadosBean("TOT");
		
		// ------------------------------
		// -- Processamento
		// ------------------------------
		
		int idAntigoLocalidade = -1;
		int idAntigoUnidade = -1;
		int idAntigoGerencia = -1;
		int idAntigoMunicipio = -1;
		
		for (RelatorioVolumesConsumidosNaoFaturadosBean bean : pesquisa) {
			
			int idCategoria = bean.getCategoria().getId();
			int idLocalidade = bean.getLocalidade().getId();
			int idUnidade = bean.getUnidade().getId();
			int idGerencia = bean.getGerencia().getId();
			int idMunicipio = 0;
			if(bean.getMunicipio()!= null){
				idMunicipio = bean.getMunicipio().getId();
			}
			
			if (opcaoEstado()) {
				parametros.put("titulo", (String) this.getParametro("estado"));
			}
			else if (opcaoGerenciaRegional()) {
				parametros.put("titulo", bean.getDescricaoGerencia());
			}
			else if (opcaoUnidadeNegocio()) {
				parametros.put("titulo", bean.getDescricaoUnidade());
			}
			else if (opcaoLocalidade()) {
				parametros.put("titulo", bean.getDescricaoLocalidade());
			} 
			else if(opcaoMunicipio()){
				parametros.put("titulo", bean.getDescricaoMunicipio());
			} 
			else{
				parametros.put("titulo", "");
			}
			
			// ------------------------------
			// -- Localidade
			// ------------------------------
			if (totalizacaoPorLocalidade()) {
				if (idLocalidade != idAntigoLocalidade) {
					if (idAntigoLocalidade != -1) {
						beans.add(totalLocalidadeBean.copy());
					}
					idAntigoLocalidade = idLocalidade;
					totalLocalidadeBean.resetValues();
				} else {
					bean.setLocalidade(null);
				}
				totalLocalidadeBean.sum(bean);
				totalLocalidadeBean.copyEntidades(bean);
			}
			
			// ------------------------------
			// -- Unidade
			// ------------------------------
			if (opcaoUnidadeNegocio() || 
				totalizacaoPorUnidade() ||
				opcaoTotalizacao.equals("estadoPorLocalidade") ||
				opcaoTotalizacao.equals("gerenciaRegionalPorLocalidade") || 
				opcaoTotalizacao.equals("estadoPorMunicipio")) {
				
				if (idUnidade != idAntigoUnidade) {
					if (idAntigoUnidade != -1) {
						adicionarLinhasTotalUnidade(beans, unidadeBeans, totalUnidadeBean.copy());
					}
					idAntigoUnidade = idUnidade;
					unidadeBeans.clear();
					totalUnidadeBean.resetValues();
				}
				RelatorioVolumesConsumidosNaoFaturadosBean categoriaUnidadeBean = getBean(bean, unidadeBeans, idCategoria);
				categoriaUnidadeBean.sum(bean);
				totalUnidadeBean.sum(bean);
				totalUnidadeBean.copyEntidades(bean);
			}
			
			// ------------------------------
			// -- Gerencia
			// ------------------------------
			if (opcaoGerenciaRegional() || 
				totalizacaoPorGerencia() ||
				opcaoTotalizacao.equals("estadoPorUnidadeNegocio") || 
				opcaoTotalizacao.equals("estadoPorLocalidade") ||
				opcaoTotalizacao.equals("estadoPorMunicipio")){
				
				if (idGerencia != idAntigoGerencia) {
					if (idAntigoGerencia != -1) {
						adicionarLinhasTotalGerencia(beans, gerenciaBeans, totalGerenciaBean.copy());
					}
					idAntigoGerencia = idGerencia;
					gerenciaBeans.clear();
					totalGerenciaBean.resetValues();
				}
				RelatorioVolumesConsumidosNaoFaturadosBean categoriaGerenciaBean = getBean(bean, gerenciaBeans, idCategoria);
				categoriaGerenciaBean.sum(bean);
				totalGerenciaBean.sum(bean);
				totalGerenciaBean.copyEntidades(bean);
			}
			
			// ------------------------------
			// -- Município
			// ------------------------------
			if (totalizacaoPorMunicipio()) {
				if (idMunicipio != idAntigoMunicipio) {
					if (idAntigoMunicipio != -1) {
						beans.add(totalMunicipioBean.copy());
					}
					idAntigoMunicipio = idMunicipio;
					totalMunicipioBean.resetValues();
				} else {
					bean.setMunicipio(null);
				}
				totalMunicipioBean.sum(bean);
				totalMunicipioBean.copyEntidades(bean);
			}
			
			// ------------------------------
			// -- Estado
			// ------------------------------
			if (opcaoEstado()) {
				RelatorioVolumesConsumidosNaoFaturadosBean categoriaEstadoBean = getBean(bean, estadoBeans, idCategoria);
				categoriaEstadoBean.sum(bean);
				totalEstadoBean.sum(bean);
			}
			
			if (totalizacaoPorLocalidade()) {
				beans.add(bean);
			}
			
			if (totalizacaoPorMunicipio()) {
				beans.add(bean);
			}
		}
		if (opcaoLocalidade() || totalizacaoPorLocalidade()) {
			beans.add(totalLocalidadeBean);
		}
		if (opcaoUnidadeNegocio() || 
			totalizacaoPorUnidade() || 
			opcaoTotalizacao.equals("estadoPorLocalidade") ||
			opcaoTotalizacao.equals("gerenciaRegionalPorLocalidade")) {
			
			adicionarLinhasTotalUnidade(beans, unidadeBeans, totalUnidadeBean);
		}
		if (opcaoGerenciaRegional() || 
			totalizacaoPorGerencia() || 
			opcaoTotalizacao.equals("estadoPorUnidadeNegocio") || 
			opcaoTotalizacao.equals("estadoPorLocalidade") ||
			opcaoTotalizacao.equals("gerenciaRegionalPorLocalidade")) {
			
			adicionarLinhasTotalGerencia(beans, gerenciaBeans, totalGerenciaBean);
		}
		if (opcaoEstado()) {
			adicionarLinhasTotalEstado(beans, estadoBeans, totalEstadoBean);
		}
		if (opcaoMunicipio() || totalizacaoPorMunicipio()) {
			beans.add(totalMunicipioBean);
		}
		
		byte[] retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_VOLUMES_CONSUMIDOS_NAO_FATURADOS,
				parametros, new RelatorioDataSource(beans), tipoRelatorio);
		
		return retorno;
	}
	
	private boolean opcaoEstado() {
		if (opcaoTotalizacao.equals("estado") ||
			opcaoTotalizacao.equals("estadoPorGerenciaRegional") ||
			opcaoTotalizacao.equals("estadoPorUnidadeNegocio") ||
			opcaoTotalizacao.equals("estadoPorLocalidade") ||
			opcaoTotalizacao.equals("estadoPorMunicipio")){
			return true;
		} else {
			return false;
		}
	}
	
	private boolean opcaoGerenciaRegional() {
		if (opcaoTotalizacao.equals("gerenciaRegional") ||
			opcaoTotalizacao.equals("gerenciaRegionalPorUnidadeNegocio") ||
			opcaoTotalizacao.equals("gerenciaRegionalPorLocalidade")) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean opcaoUnidadeNegocio() {
		if (opcaoTotalizacao.equals("unidadeNegocio") ||
			opcaoTotalizacao.equals("unidadeNegocioPorLocalidade")) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean opcaoLocalidade() {
		if (opcaoTotalizacao.equals("localidade")) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean opcaoMunicipio() {
		if (opcaoTotalizacao.equals("municipio")) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean totalizacaoPorGerencia() {
		if (opcaoTotalizacao.equals("estadoPorGerenciaRegional") || 
			opcaoTotalizacao.equals("gerenciaRegional")) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean totalizacaoPorUnidade() {
		if (opcaoTotalizacao.equals("estadoPorUnidadeNegocio") ||
			opcaoTotalizacao.equals("gerenciaRegionalPorUnidadeNegocio") ||
			opcaoTotalizacao.equals("unidadeNegocio")) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean totalizacaoPorLocalidade() {
		if (opcaoTotalizacao.equals("estadoPorLocalidade") ||
			opcaoTotalizacao.equals("gerenciaRegionalPorLocalidade") ||
			opcaoTotalizacao.equals("unidadeNegocioPorLocalidade") || 
			opcaoTotalizacao.equals("localidade")) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean totalizacaoPorMunicipio() {
		if (this.opcaoTotalizacao.equals("municipio")
				|| this.opcaoTotalizacao.equals("estadoPorMunicipio")) {
			return true;
		} else {
			return false;
		}
	}
	
	private void adicionarLinhasTotalGerencia(List<RelatorioVolumesConsumidosNaoFaturadosBean> beans,
			Map<Integer, RelatorioVolumesConsumidosNaoFaturadosBean> gerenciaBeans, RelatorioVolumesConsumidosNaoFaturadosBean totalGerenciaBean) {
		
		boolean primeiroUnidade = true;
		List<RelatorioVolumesConsumidosNaoFaturadosBean> listUnidadeBeans = new ArrayList<RelatorioVolumesConsumidosNaoFaturadosBean>(gerenciaBeans.values());
		listUnidadeBeans.add(totalGerenciaBean.copy());
		
		for (RelatorioVolumesConsumidosNaoFaturadosBean bean : listUnidadeBeans) {
			if (primeiroUnidade) {
				primeiroUnidade = false;
				if (totalizacaoPorGerencia()) {
					bean.setDescricaoLocalidade(bean.getDescricaoGerencia());
				} else {
					bean.setDescricaoLocalidade("TOTAL DA REGIONAL " + bean.getDescricaoGerencia());
				}
			} else {
				bean.setDescricaoLocalidade("");
			}
			beans.add(bean);
		}
	}
	
	private void adicionarLinhasTotalUnidade(List<RelatorioVolumesConsumidosNaoFaturadosBean> beans, 
			Map<Integer, RelatorioVolumesConsumidosNaoFaturadosBean> unidadeBeans, RelatorioVolumesConsumidosNaoFaturadosBean totalUnidadeBean) {
		
		boolean primeiroUnidade = true;
		List<RelatorioVolumesConsumidosNaoFaturadosBean> listUnidadeBeans = new ArrayList<RelatorioVolumesConsumidosNaoFaturadosBean>(unidadeBeans.values());
		listUnidadeBeans.add(totalUnidadeBean.copy());
		
		for (RelatorioVolumesConsumidosNaoFaturadosBean bean : listUnidadeBeans) {
			if (primeiroUnidade) {
				primeiroUnidade = false;
				if (totalizacaoPorUnidade()) {
					bean.setDescricaoLocalidade(bean.getDescricaoUnidade());
				} else {
					bean.setDescricaoLocalidade("TOTAL DA UNIDADE " + bean.getDescricaoUnidade());
				}
			} else {
				bean.setDescricaoLocalidade("");
			}
			beans.add(bean);
		}
	}
	
	private void adicionarLinhasTotalEstado(List<RelatorioVolumesConsumidosNaoFaturadosBean> beans, 
			Map<Integer, RelatorioVolumesConsumidosNaoFaturadosBean> estadoBeans, RelatorioVolumesConsumidosNaoFaturadosBean totalEstadoBean) {
		
		boolean primeiroEstado = true;
		List<RelatorioVolumesConsumidosNaoFaturadosBean> listEstadoBeans = new ArrayList<RelatorioVolumesConsumidosNaoFaturadosBean>(estadoBeans.values());
		listEstadoBeans.add(totalEstadoBean);
		
		for (RelatorioVolumesConsumidosNaoFaturadosBean bean : listEstadoBeans) {
			if (primeiroEstado) {
				primeiroEstado = false;
				totalEstadoBean.copyEntidades(bean);
				bean.setDescricaoLocalidade("TOTAL DO ESTADO");
			} else {
				bean.setDescricaoLocalidade("");
			}
			bean.setTotalEstado(true);
			beans.add(bean);
		}
	}
	
	public RelatorioVolumesConsumidosNaoFaturadosBean getBean(RelatorioVolumesConsumidosNaoFaturadosBean bean,
			Map<Integer, RelatorioVolumesConsumidosNaoFaturadosBean> hash, Integer id) {
		
		RelatorioVolumesConsumidosNaoFaturadosBean result = hash.get(id);
		if (result == null) {
			result = bean.copy();
			result.resetValues();
			hash.put(id, result);
		}
		return result;
	}
	
	public void popularParametros(Map<String, Object> parametros) {
		Fachada fachada = Fachada.getInstancia();
		
		String ano = mesAno.toString().substring(0, 4);
		String mes = mesAno.toString().substring(4);
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(new Integer(ano), new Integer(mes) -1, 1);
		String dia = "" + calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		parametros.put("dataFinal", dia + "/" + mes + "/" + ano);
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoRelatorio", "RF0822");
		
		this.addParametro("estado", "ESTADO DE " + sistemaParametro.getNomeEstado());
		
		if (opcaoTotalizacao.equals("estado")) {
			parametros.put("opcaoTotalizacao", "ESTADO");
		}
		else if (totalizacaoPorGerencia()) {
			parametros.put("opcaoTotalizacao", "GERÊNCIA REGIONAL");
		}
		else if (totalizacaoPorUnidade()) {
			parametros.put("opcaoTotalizacao", "UNIDADE DE NEGÓCIO");
		}
		else if (totalizacaoPorLocalidade()) {
			parametros.put("opcaoTotalizacao", "LOCALIDADE");
		}else if(totalizacaoPorMunicipio()){
			parametros.put("opcaoTotalizacao", "MUNICÍPIO");
		}
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioVolumesConsumidosNaoFaturados", this);
	}

}
