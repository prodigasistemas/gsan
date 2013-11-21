package gcom.relatorio.arrecadacao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper;
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
 * Gerar Relatório Dados Diários da Arrecadação - Forma de Arrecadação
 * 
 * @author Mariana Victor
 * @date 04/02/2011
 */
public class RelatorioDadosDiariosArrecadacaoForma extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	
	public RelatorioDadosDiariosArrecadacaoForma(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RELATORIO_DADOS_DIARIOS_ARRECADACAO_FORMA);
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
		
		BigDecimal valorTotalArrecadador = null;
		String referencia = null;
		String dadosMesInformado = null;
		String dadosAtual = null;
		String nomeAgente = null;
		String faturamentoCobradoEmConta = null;
		Collection<FiltrarDadosDiariosArrecadacaoHelper> colecaoDadosDiarios = null;

		if (getParametro("valorTotalArrecadador") != null) {
			valorTotalArrecadador = (BigDecimal) getParametro("valorTotalArrecadador");
		}
		if (getParametro("referencia") != null) {
			referencia = (String) getParametro("referencia");
		}
		if (getParametro("dadosMesInformado") != null) {
			dadosMesInformado = (String) getParametro("dadosMesInformado");
		}
		if (getParametro("dadosAtual") != null) {
			dadosAtual = (String) getParametro("dadosAtual");
		}
		if (getParametro("nomeAgente") != null) {
			nomeAgente = (String) getParametro("nomeAgente");
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
		
		Collection<RelatorioDadosDiariosArrecadacaoFormaBean> colecaoBean = this
			.inicializarBeanRelatorio(valorTotalArrecadador, referencia, dadosMesInformado,
					dadosAtual, nomeAgente, faturamentoCobradoEmConta, colecaoDadosDiarios);

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this
				.gerarRelatorio(
						ConstantesRelatorios.RELATORIO_RELATORIO_DADOS_DIARIOS_ARRECADACAO_FORMA,
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
		AgendadorTarefas.agendarTarefa("RelatorioDadosDiariosArrecadacaoForma", this);
	}
	
	private Collection<RelatorioDadosDiariosArrecadacaoFormaBean> inicializarBeanRelatorio(
			BigDecimal valorTotalArrecadador, String referencia, String dadosMesInformado,
			String dadosAtual, String nomeAgente, String faturamentoCobradoEmConta,
			Collection<FiltrarDadosDiariosArrecadacaoHelper> colecaoDadosDiarios){
		
		Collection<RelatorioDadosDiariosArrecadacaoFormaBean> colecaoBean = new ArrayList<RelatorioDadosDiariosArrecadacaoFormaBean>();

		
		if (colecaoDadosDiarios != null && !colecaoDadosDiarios.isEmpty()){
			Iterator iterator = colecaoDadosDiarios.iterator();
			
			while (iterator.hasNext()) {
				FiltrarDadosDiariosArrecadacaoHelper itemHelper = (FiltrarDadosDiariosArrecadacaoHelper) iterator.next();
				RelatorioDadosDiariosArrecadacaoFormaBean bean = new RelatorioDadosDiariosArrecadacaoFormaBean();
				
				ArrecadacaoForma arrecadacaoForma = (ArrecadacaoForma) itemHelper.getItemAgrupado();
				
				bean.setProcessamentoDefinitivo(dadosMesInformado);
				bean.setMesAno(Util.formatarAnoMesParaMesAno(referencia));
				bean.setUltimoProcessamentoAtual(dadosAtual);
				bean.setFaturamentoCobradoEmConta(faturamentoCobradoEmConta);
				bean.setAgente(nomeAgente);
				bean.setValor(Util.formatarMoedaReal(valorTotalArrecadador));
				
				bean.setFormaArrecadacao(arrecadacaoForma.getDescricao());
				bean.setQuantidadeDocumentos(Util.agruparNumeroEmMilhares(itemHelper.getQuantidadeDocumentos()));
				bean.setQuantidadePagamentos(Util.agruparNumeroEmMilhares(itemHelper.getQuantidadePagamentos()));
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
