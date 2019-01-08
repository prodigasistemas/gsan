package gcom.relatorio.cobranca;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.Filtro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioNotificacaoDebito extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioNotificacaoDebito(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_NOTIFICACAO_DEBITO);
	}

	@Deprecated
	public RelatorioNotificacaoDebito() {
		super(null, "");
	}

	public Object executar() throws TarefaException {
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();

		Integer idCobrancaAcaoCronograma = (Integer) getParametro("idCobrancaAcaoCronograma");
		Integer idCobrancaAcaoComando = (Integer) getParametro("idCobrancaAcaoComando");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String tipoEndRelatorio = (String) getParametro("tipoEndRelatorio");
		String quantidadeRelatorios = (String) getParametro("quantidadeRelatorios");

		int tamanhoMaximoDebito = 15;

		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();

		Map<String, Object> parametros = new HashMap<String, Object>();

		SistemaParametro sistemaParametro = getSistemaParametro(fachada);
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		if (sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_COSANPA)) {
			tamanhoMaximoDebito = 35;
		}

		List<RelatorioNotificacaoDebitoBean> relatorioBeans = (List<RelatorioNotificacaoDebitoBean>) fachada.gerarRelatorioNotificacaoDebito(
				idCobrancaAcaoCronograma, idCobrancaAcaoComando, tamanhoMaximoDebito, quantidadeRelatorios, tipoEndRelatorio);

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		String nomeEmpresa = sistemaParametro.getNomeEmpresa();
		String enderecoEmpresa = sistemaParametro.getEnderecoFormatado();
		String cepEmpresa = "CEP: " + sistemaParametro.getCep().getCepFormatado();
		String cnpjEmpresa = "CNPJ: " + Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		String foneFaxEmpresa = "FONE: " + Util.formatarTelefone(sistemaParametro.getNumeroTelefone()) + "  " + "FAX: " + Util.formatarTelefone(sistemaParametro.getNumeroFax());

		parametros.put("nomeEmpresa", nomeEmpresa);
		parametros.put("enderecoEmpresa", enderecoEmpresa);
		parametros.put("cepEmpresa", cepEmpresa);
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		parametros.put("foneFaxEmpresa", foneFaxEmpresa);

		if (sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(SistemaParametro.EMPRESA_CAERN)) {
			parametros.put("quantidadeDias", "30º (trigésimo)");
		} else {
			parametros.put("quantidadeDias", "10º (décimo)");
		}

		if (sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_COSANPA)) {
			if (tipoEndRelatorio.equals("1") || tipoEndRelatorio.equals("2")) {
				ArrayList<String> array = new ArrayList<String>();
				array.add("relatorio");
				RelatorioDataSource dsRelatorio = new RelatorioDataSource(array);
				parametros.put("dataSource", ds);

				retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_NOTIFICACAO_DEBITO_COSANPA_2_PAGINAS, parametros, dsRelatorio, tipoFormatoRelatorio);
			} else {
				retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_NOTIFICACAO_DEBITO_COSANPA, parametros, ds, tipoFormatoRelatorio);
			}

		} else {
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_NOTIFICACAO_DEBITO, parametros, ds, tipoFormatoRelatorio);
		}

		try {
			persistirRelatorioConcluido(retorno, Relatorio.ANORMALIDADE_CONSUMO, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		
		return retorno;
	}

	@SuppressWarnings("rawtypes")
	private SistemaParametro getSistemaParametro(Fachada fachada) {
		Filtro filtro = new FiltroSistemaParametro();
		filtro.adicionarCaminhoParaCarregamentoEntidade("bairro");
		filtro.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtro.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
		filtro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
		filtro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
		filtro.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
		filtro.adicionarCaminhoParaCarregamentoEntidade("cep");
		
		
		Collection colecaoSistemaParametro = fachada.pesquisar(filtro,SistemaParametro.class.getName());
		
		return (SistemaParametro) colecaoSistemaParametro.iterator().next();
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 1;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioNotificacaoDebito", this);
	}
}
