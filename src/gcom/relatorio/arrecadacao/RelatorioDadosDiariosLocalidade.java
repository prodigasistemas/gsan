package gcom.relatorio.arrecadacao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * [UC0339] Consultar Dados Diários da Arrecadação
 * 
 * Gerar Relatório Dados Diários da Arrecadação - Elo
 * 
 * @author Mariana Victor
 * @date 03/02/2011
 */
public class RelatorioDadosDiariosLocalidade extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	
	public RelatorioDadosDiariosLocalidade(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RELATORIO_DADOS_DIARIOS_LOCALIDADE);
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {
		
		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String nomeArrecadador = (String) getParametro("arrecadador");
		
		BigDecimal valorTotalUnidadeNegocio = null;
		BigDecimal valorTotalElo = null;
		String referencia = null;
		String nomeGerencia = null;
		String nomeElo = null;
		BigDecimal valorTotalGerencia = null;
		String idUnidadeNegocio = null;
		String nomeUnidadeNegocio = null;
		String dadosMesInformado = null;
		String dadosAtual = null;
		String faturamentoCobradoEmConta = null;
		Collection<FiltrarDadosDiariosArrecadacaoHelper> colecaoDadosDiarios = null;

		if (getParametro("valorTotalUnidadeNegocio") != null) {
			valorTotalUnidadeNegocio = (BigDecimal) getParametro("valorTotalUnidadeNegocio");
		}
		if (getParametro("valorTotalElo") != null) {
			valorTotalElo = (BigDecimal) getParametro("valorTotalElo");
		}
		if (getParametro("referencia") != null) {
			referencia = (String) getParametro("referencia");
		}
		if (getParametro("nomeGerencia") != null) {
			nomeGerencia = (String) getParametro("nomeGerencia");
		}
		if (getParametro("nomeElo") != null) {
			nomeElo = (String) getParametro("nomeElo");
		}
		if (getParametro("valorTotalGerencia") != null) {
			valorTotalGerencia = (BigDecimal) getParametro("valorTotalGerencia");
		}
		if (getParametro("idUnidadeNegocio") != null) {
			idUnidadeNegocio = (String) getParametro("idUnidadeNegocio");
		}
		if (getParametro("nomeUnidadeNegocio") != null) {
			nomeUnidadeNegocio = (String) getParametro("nomeUnidadeNegocio");
		}
		if (getParametro("dadosMesInformado") != null) {
			dadosMesInformado = (String) getParametro("dadosMesInformado");
		}
		if (getParametro("dadosAtual") != null) {
			dadosAtual = (String) getParametro("dadosAtual");
		}
		if (getParametro("faturamentoCobradoEmConta") != null) {
			faturamentoCobradoEmConta = (String) getParametro("faturamentoCobradoEmConta");
		}
		if (getParametro("colecaoDadosDiarios") != null) {
			colecaoDadosDiarios = (Collection<FiltrarDadosDiariosArrecadacaoHelper>) getParametro("colecaoDadosDiarios");
		}
		
		// Parâmetros do relatório
		Map parametros = new HashMap();

		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("bairro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("cep");
		
		Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro,SistemaParametro.class.getName());
		
		SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();
		
		String cnpjEmpresa = "";
		if (sistemaParametro.getCnpjEmpresa() != null) {
			cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		}
		
		String arrecadador = "";
		if (nomeArrecadador != null) {
			if(nomeArrecadador.equals("")){
				arrecadador = "TODOS";
			}
			else{
				arrecadador = nomeArrecadador;
			}
		}
		
		// Usuario que emite o relatorio
		Usuario usuario = this.getUsuario();

		String nomeUsuario = usuario.getNomeUsuario();
		
		parametros.put("nomeUsuario", nomeUsuario);
		parametros.put("nomeEmpresa",sistemaParametro.getNomeAbreviadoEmpresa());
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("enderecoEmpresa", sistemaParametro.getEnderecoFormatado());
		parametros.put("cepEmpresa", sistemaParametro.getCep().getCepFormatado());
		parametros.put("telefoneGeral", sistemaParametro.getNumeroTelefone());
		parametros.put("arrecadador", arrecadador);
		
		Collection<RelatorioDadosDiariosLocalidadeBean> colecaoBean = this
			.inicializarBeanRelatorio(valorTotalUnidadeNegocio, valorTotalElo,
					referencia, nomeGerencia, nomeElo, valorTotalGerencia,
					idUnidadeNegocio, nomeUnidadeNegocio, dadosMesInformado,
					dadosAtual, faturamentoCobradoEmConta, colecaoDadosDiarios);

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this
				.gerarRelatorio(
						ConstantesRelatorios.RELATORIO_RELATORIO_DADOS_DIARIOS_LOCALIDADE,
						parametros, ds, tipoFormatoRelatorio);

		// retorna o relatório gerado
		return retorno;

	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioDadosDiariosLocalidade", this);
	}
	
	private Collection<RelatorioDadosDiariosLocalidadeBean> inicializarBeanRelatorio(
			BigDecimal valorTotalUnidadeNegocio, BigDecimal valorTotalElo, String referencia,
			String nomeGerencia, String nomeElo,  BigDecimal valorTotalGerencia, String idUnidadeNegocio,
			String nomeUnidadeNegocio, String dadosMesInformado, String dadosAtual, String faturamentoCobradoEmConta,
			Collection<FiltrarDadosDiariosArrecadacaoHelper> colecaoDadosDiarios) {
		
		Collection<RelatorioDadosDiariosLocalidadeBean> colecaoBean = new ArrayList<RelatorioDadosDiariosLocalidadeBean>();

		if (colecaoDadosDiarios != null && !colecaoDadosDiarios.isEmpty()){
			Iterator iterator = colecaoDadosDiarios.iterator();
			
			while (iterator.hasNext()) {
				FiltrarDadosDiariosArrecadacaoHelper itemHelper = (FiltrarDadosDiariosArrecadacaoHelper) iterator.next();
				RelatorioDadosDiariosLocalidadeBean bean = new RelatorioDadosDiariosLocalidadeBean();

				Localidade localidade = (Localidade) itemHelper.getItemAgrupado();
				
				bean.setProcessamentoDefinitivo(dadosMesInformado);
				bean.setMesAno(Util.formatarAnoMesParaMesAno(referencia));
				bean.setUltimoProcessamentoAtual(dadosAtual);
				bean.setFaturamentoCobradoEmConta(faturamentoCobradoEmConta);
				bean.setNomeGerencia(nomeGerencia);
				bean.setValorGerencia(Util.formatarMoedaReal(valorTotalGerencia));
				bean.setNomeUnidadeNegocio(nomeUnidadeNegocio);
				bean.setValorUnidadeNegocio(Util.formatarMoedaReal(valorTotalUnidadeNegocio));
				bean.setNomeLocalidadePolo(nomeElo);
				bean.setValorLocalidadePolo(Util.formatarMoedaReal(valorTotalElo));
				
				bean.setLocalidade(localidade.getDescricao());
				bean.setDebitos(Util.formatarMoedaReal(itemHelper.getValorDebitos()));
				bean.setDescontos(Util.formatarMoedaReal(itemHelper.getValorDescontos()));
				bean.setValorArrecadado(Util.formatarMoedaReal(itemHelper.getValorArrecadacao()));
				bean.setDevolucao(Util.formatarMoedaReal(itemHelper.getValorDevolucoes()));
				bean.setArrecadacaoLiquida(Util.formatarMoedaReal(itemHelper.getValorArrecadacaoLiquida()));
				bean.setPercentualMes(Util.formatarMoedaReal(itemHelper.getPercentual()));
				
				colecaoBean.add(bean);
			}
		}
		
		return colecaoBean;
	}
}
