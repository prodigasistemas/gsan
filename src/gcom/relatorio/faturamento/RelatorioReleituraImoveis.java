package gcom.relatorio.faturamento;

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.faturamento.FiltrarAnalisarReleituraImoveisActionForm;
import gcom.gui.faturamento.bean.AnalisarImoveisReleituraHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioReleituraImoveis extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioReleituraImoveis(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RELEITURA_IMOVEIS);
	}

	@Override
	public Object executar() throws TarefaException {

		int tipoFormatoRelatorio = (Integer) getParametro("tipoRelatorio");
		Collection<AnalisarImoveisReleituraHelper> colAnalisarImoveisReleituraHelper = (Collection<AnalisarImoveisReleituraHelper>) getParametro("colAnalisarImoveisReleituraHelper");
		FiltrarAnalisarReleituraImoveisActionForm form = (FiltrarAnalisarReleituraImoveisActionForm) getParametro("formularioFiltrarReleituraImoveis");

		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();

		Collection colecaoSistemaParametro = fachada.pesquisar(
				filtroSistemaParametro, SistemaParametro.class.getName());

		SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro
				.iterator().next();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		if (form.getIdGrupo() == null || form.getIdGrupo().equals("")
				|| form.getIdGrupo().equals("-1")) {
			parametros.put("grupo", "");
		} else {
			parametros.put("grupo", form.getIdGrupo());
		}

		if (form.getIdRota() == null || form.getIdRota().equals("")
				|| form.getIdRota().equals("-1")) {
			parametros.put("rota", "");
		} else {
			parametros.put("rota", form.getIdRota());
		}

		if (form.getIdQuadra() == null || form.getIdQuadra().equals("")
				|| form.getIdQuadra().equals("-1")) {
			parametros.put("quadra", "");
		} else {
			parametros.put("quadra", form.getIdQuadra());
		}

		if (form.getIdSituacaoReleitura() == null
				|| form.getIdSituacaoReleitura().equals("")
				|| form.getIdSituacaoReleitura().equals("-1")) {
			parametros.put("situacaoReleitura", "");
		} else {
			parametros.put("situacaoReleitura", form.getIdSituacaoReleitura());
		}

		if (form.getEmpresa() == null || form.getEmpresa().equals("")
				|| form.getEmpresa().equals("-1")) {
			parametros.put("empresa", "");
		} else {
			parametros.put("empresa", form.getEmpresa());
		}

		if (form.getMesAno() == null || form.getMesAno().equals("")
				|| form.getMesAno().equals("-1")) {
			parametros.put("referencia", "");
		} else {
			parametros.put("referencia", form.getMesAno());
		}

		List<RelatorioReleituraImoveisBean> beans = inicializaBeansRelatorio(colAnalisarImoveisReleituraHelper);

		RelatorioDataSource ds = new RelatorioDataSource(beans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_RELEITURA_IMOVEIS, parametros,
				ds, tipoFormatoRelatorio);

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 1;
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioReleituraImoveis", this);
	}

	private List<RelatorioReleituraImoveisBean> inicializaBeansRelatorio(
			Collection<AnalisarImoveisReleituraHelper> colAnalisarImoveisReleituraHelper) {
		List<RelatorioReleituraImoveisBean> beans = new ArrayList<RelatorioReleituraImoveisBean>();
		if (colAnalisarImoveisReleituraHelper != null
				&& !colAnalisarImoveisReleituraHelper.isEmpty()) {

			Iterator iterator = colAnalisarImoveisReleituraHelper.iterator();

			while (iterator.hasNext()) {
				AnalisarImoveisReleituraHelper helper = (AnalisarImoveisReleituraHelper) iterator
						.next();

				RelatorioReleituraImoveisBean bean = new RelatorioReleituraImoveisBean();

				// setar todos os campos do relatório

				if (helper.getLeituraAnteriorAgua() != null) {
					bean.setLeituraAnteriorAgua(helper.getLeituraAnteriorAgua()
							.toString());
				} else {
					bean.setLeituraAnteriorAgua("-");
				}
				if (helper.getLeituraAtualAgua() != null) {
					bean.setLeituraAtualAgua(helper.getLeituraAtualAgua()
							.toString());
				} else {
					bean.setLeituraAtualAgua("-");
				}
				if (helper.getLeituraAnormalidadeAnteriorAgua() != null) {
					bean.setLeituraAnormalidadeAnteriorAgua(helper
							.getLeituraAnormalidadeAnteriorAgua()
							.getDescricao());
				} else {
					bean.setLeituraAnormalidadeAnteriorAgua("-");
				}
				if (helper.getLeituraAnormalidadeAtualAgua() != null) {
					bean.setLeituraAnormalidadeAtualAgua(helper
							.getLeituraAnormalidadeAtualAgua().getDescricao());
				} else {
					bean.setLeituraAnormalidadeAtualAgua("-");
				}
				if (helper.getLeituraAnteriorPoco() != null) {
					bean.setLeituraAnteriorPoco(helper.getLeituraAnteriorPoco()
							.toString());
				} else {
					bean.setLeituraAnteriorPoco("-");
				}
				if (helper.getLeituraAtualPoco() != null) {
					bean.setLeituraAtualPoco(helper.getLeituraAtualPoco()
							.toString());
				} else {
					bean.setLeituraAtualPoco("-");
				}
				if (helper.getLeituraAnormalidadeAnteriorPoco() != null) {
					bean.setLeituraAnormalidadeAnteriorPoco(helper
							.getLeituraAnormalidadeAnteriorPoco()
							.getDescricao());
				} else {
					bean.setLeituraAnormalidadeAnteriorPoco("-");
				}
				if (helper.getLeituraAnormalidadeAtualPoco() != null) {
					bean.setLeituraAnormalidadeAtualPoco(helper
							.getLeituraAnormalidadeAtualPoco().getDescricao());
				} else {
					bean.setLeituraAnormalidadeAtualPoco("-");
				}

				if (helper.getIdSituacaoReleitura() == AnalisarImoveisReleituraHelper.RELEITURA_PENDENTE) {
					bean.setStatus("PENDENTE");
				} else if (helper.getIdSituacaoReleitura() == AnalisarImoveisReleituraHelper.RELEITURA_NAO_REALIZADA) {
					bean.setStatus("NAO REALIZADA");
				} else if (helper.getIdSituacaoReleitura() == AnalisarImoveisReleituraHelper.RELEITURA_REALIZADA) {
					bean.setStatus("REALIZADA");
				}
				if (helper.getMatriculaImovel() != null) {
					bean.setImovel(helper.getMatriculaImovel());
				} else {
					bean.setImovel("-");
				}
				if (helper.getCodigoRota() != 0) {
					bean.setRota((new Integer(helper.getCodigoRota())
							.toString()));
				} else {
					bean.setRota("-");
				}
				if (helper.getIdLocalidade() != 0) {
					bean.setLocalidade((new Integer(helper.getIdLocalidade())
							.toString()));
				} else {
					bean.setLocalidade("-");
				}
				if (helper.getIdQuadra() != 0) {
					bean.setQuadra((new Integer(helper.getIdQuadra())
							.toString()));
				} else {
					bean.setQuadra("-");
				}
				if (helper.getCodigoSetorComercial() != 0) {
					bean
							.setSetor((new Integer(helper
									.getCodigoSetorComercial()).toString()));
				} else {
					bean.setSetor("-");
				}
				if (helper.getRecebeuMensagem() != null) {
					bean.setRecebeuMensagem(helper.getRecebeuMensagem());
				} else {
					bean.setRecebeuMensagem("-");
				}
				beans.add(bean);

			}

		}
		return beans;
	}

}
