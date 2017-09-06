package gcom.relatorio.cobranca;

import gcom.batch.Relatorio;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.RelatorioPagamentosContasCobrancaEmpresaHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
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
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório
 * 
 * @author Rômulo Aurélio
 * @created 08/01/2009
 */
public class RelatorioPagamentosContasCobrancaEmpresa extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioPagamentosContasCobrancaEmpresa(Usuario usuario) {
		super(usuario,ConstantesRelatorios.RELATORIO_PAGAMENTOS_CONTAS_COBRANCA_EMPRESA);
	}

	@Deprecated
	public RelatorioPagamentosContasCobrancaEmpresa() {
		super(null, "");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Empresa empresa = (Empresa) getParametro("empresa");
		String dataPagamentoInicial = (String) getParametro("dataPagamentoInicial");
		String dataPagamentoFinal = (String) getParametro("dataPagamentoFinal");
		@SuppressWarnings("unused")
		String opcaoEmissao = (String) getParametro("opcaoRelatorio");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String opcaoTotalizacao = (String) getParametro("opcaoTotalizacao");
		Integer codigoLocalidade = (Integer) getParametro("localidade");
		Integer codigoGerencia = (Integer) getParametro("gerenciaRegional");
		Integer unidadeNegocio = (Integer) getParametro("unidadeNegocio");
		
		
		RelatorioPagamentosContasCobrancaEmpresaHelper helper = 
			new RelatorioPagamentosContasCobrancaEmpresaHelper(empresa,
					dataPagamentoInicial,  dataPagamentoFinal, 
					opcaoTotalizacao, codigoLocalidade, codigoGerencia,unidadeNegocio);
		
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();

		List relatorioBeans = new ArrayList();

		Collection colecaoRelatorioBean = fachada.pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresa(helper);

		if (colecaoRelatorioBean != null && !colecaoRelatorioBean.isEmpty()) {
			relatorioBeans.addAll(colecaoRelatorioBean);
		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		// __________________________________________________________________

		Map parametros = new HashMap();

		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		if (empresa.getId() != null) {
			parametros.put("idEmpresa", empresa.getDescricao());
			parametros.put("nomeEmpresa", empresa.getDescricao());
		} else {
			parametros.put("empresa", "");
		}
		
		String opcaoQuebra = "";
		String opcaoQuebra2 = "";
		
		
		if (helper.getOpcaoTotalizacao().equalsIgnoreCase("estadoGerencia")) {
			opcaoQuebra = "Gerência Regional";
		}else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("estadoLocalidade")) {
			opcaoQuebra = "Localidade";
		}else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("gerenciaRegional")) {
			opcaoQuebra = "Gerência Regional";
		}else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("gerenciaRegionalLocalidade")) {
			opcaoQuebra = "Gerência Regional";
			opcaoQuebra2 = "Localidade";
		}else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("localidade")) {
			opcaoQuebra = "Localidade";
		}else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("estadoUnidadeNegocio")) {
			opcaoQuebra = "Unidade Negócio";
		}else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("unidadeNegocio")) {
			opcaoQuebra = "Unidade Negócio";
		}else {
			opcaoQuebra = "Estado";
		}

		parametros.put("opcaoQuebra", opcaoQuebra);
		parametros.put("opcaoQuebra2",opcaoQuebra2);
		parametros.put("periodoPagamentoInicial", Util.formatarYYYYMMDDTracoParaDDMMAAAABarra(dataPagamentoInicial));
		parametros.put("periodoPagamentoFinal", Util.formatarYYYYMMDDTracoParaDDMMAAAABarra(dataPagamentoFinal));

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		if (opcaoEmissao.equals("1")) {
			parametros.put("visaoRelatorio", "SINTETICO");
		} else {
			parametros.put("visaoRelatorio", "ANALITICO");
		}
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_PAGAMENTOS_CONTAS_COBRANCA_EMPRESA, parametros, ds, tipoFormatoRelatorio);

		try {
			persistirRelatorioConcluido(retorno,Relatorio.RELATORIO_PAGAMENTOS_CONTAS_COBRANCA_EMPRESA, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		Empresa empresa = (Empresa) getParametro("empresa");
		String dataPagamentoInicial = (String) getParametro("dataPagamentoInicial");
		String dataPagamentoFinal = (String) getParametro("dataPagamentoFinal");

		retorno = Fachada.getInstancia().pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresaCount(
						empresa.getId(), dataPagamentoInicial, dataPagamentoFinal);

		if (retorno == 0) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioPagamentosContasCobranca", this);
	}
}
