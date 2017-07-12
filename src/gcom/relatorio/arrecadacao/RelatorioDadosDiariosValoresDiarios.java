package gcom.relatorio.arrecadacao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
 * Gerar Relatório Dados Diários da Arrecadação - Valores Diários
 * 
 * @author Mariana Victor
 * @date 03/02/2011
 */
public class RelatorioDadosDiariosValoresDiarios extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	
	public RelatorioDadosDiariosValoresDiarios(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RELATORIO_DADOS_DIARIOS_VALORES_DIARIOS);
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

		BigDecimal valorTotal = null;
		String referencia = null;
		String dadosMesInformado = null;
		String dadosAtual = null;
		String nomeGerencia = null;
		String descricaoLocalidade = null;
		String descricaoElo = null;
		String nomeAgente = null;
		String nomeCategoria = null;
		String nomePerfil = null;
		String nomeDocumento = null;
		String nomeArrecadacaoForma = null;
		String nomeUnidadeNegocio = null;
		String mostraUnidadeGerencia = null;
		String faturamentoCobradoEmConta = null;
		Collection<FiltrarDadosDiariosArrecadacaoHelper> colecaoDadosDiarios = null;

		if (getParametro("valorTotal") != null) {
			valorTotal = (BigDecimal) getParametro("valorTotal");
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
		if (getParametro("nomeGerencia") != null) {
			nomeGerencia = (String) getParametro("nomeGerencia");
		}
		if (getParametro("descricaoLocalidade") != null) {
			descricaoLocalidade = (String) getParametro("descricaoLocalidade");
		}
		if (getParametro("descricaoElo") != null) {
			descricaoElo = (String) getParametro("descricaoElo");
		}
		if (getParametro("nomeAgente") != null) {
			nomeAgente = (String) getParametro("nomeAgente");
		}
		if (getParametro("nomeCategoria") != null) {
			nomeCategoria = (String) getParametro("nomeCategoria");
		}
		if (getParametro("nomePerfil") != null) {
			nomePerfil = (String) getParametro("nomePerfil");
		}
		if (getParametro("nomeDocumento") != null) {
			nomeDocumento = (String) getParametro("nomeDocumento");
		}
		if (getParametro("nomeArrecadacaoForma") != null) {
			nomeArrecadacaoForma = (String) getParametro("nomeArrecadacaoForma");
		}
		if (getParametro("mostraUnidadeGerencia") != null) {
			mostraUnidadeGerencia = (String) getParametro("mostraUnidadeGerencia");
		}
		if (getParametro("faturamentoCobradoEmConta") != null) {
			faturamentoCobradoEmConta = (String) getParametro("faturamentoCobradoEmConta");
		}
		if (getParametro("nomeUnidadeNegocio") != null) {
			nomeUnidadeNegocio = (String) getParametro("nomeUnidadeNegocio");
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
		
		Collection<RelatorioDadosDiariosValoresDiariosBean> colecaoBean = this
			.inicializarBeanRelatorio(valorTotal, referencia, dadosMesInformado,
					dadosAtual, nomeGerencia, descricaoLocalidade, descricaoElo,
					nomeAgente, nomeCategoria, nomePerfil, nomeDocumento, nomeArrecadacaoForma,
					nomeUnidadeNegocio, mostraUnidadeGerencia, faturamentoCobradoEmConta,
					colecaoDadosDiarios);

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this
				.gerarRelatorio(
						ConstantesRelatorios.RELATORIO_RELATORIO_DADOS_DIARIOS_VALORES_DIARIOS,
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
		AgendadorTarefas.agendarTarefa("RelatorioDadosDiariosValoresDiarios", this);
	}
	
	private Collection<RelatorioDadosDiariosValoresDiariosBean> inicializarBeanRelatorio(
			BigDecimal valorTotal, String referencia, String dadosMesInformado, String dadosAtual,
			String nomeGerencia, String descricaoLocalidade, String descricaoElo,
			String nomeAgente, String nomeCategoria, String nomePerfil, String nomeDocumento,
			String nomeArrecadacaoForma, String nomeUnidadeNegocio, String mostraUnidadeGerencia,
			String faturamentoCobradoEmConta,
			Collection<FiltrarDadosDiariosArrecadacaoHelper> colecaoDadosDiarios) {
		
		Collection<RelatorioDadosDiariosValoresDiariosBean> colecaoBean = new ArrayList<RelatorioDadosDiariosValoresDiariosBean>();

		if (colecaoDadosDiarios != null && !colecaoDadosDiarios.isEmpty()){
			Iterator iterator = colecaoDadosDiarios.iterator();

			BigDecimal valorDia = new BigDecimal("0.00");
			
			while (iterator.hasNext()) {
				FiltrarDadosDiariosArrecadacaoHelper itemHelper = (FiltrarDadosDiariosArrecadacaoHelper) iterator.next();
				RelatorioDadosDiariosValoresDiariosBean bean = new RelatorioDadosDiariosValoresDiariosBean();

				bean.setProcessamentoDefinitivo(dadosMesInformado);
				bean.setMesAno(Util.formatarAnoMesParaMesAno(referencia));
				bean.setUltimoProcessamentoAtual(dadosAtual);
				bean.setFaturamentoCobradoEmConta(faturamentoCobradoEmConta);
				
				if (nomeGerencia != null) {
					bean.setNomeGerencia(nomeGerencia);
				}
				if (nomeUnidadeNegocio != null) {
					bean.setNomeUnidadeNegocio(nomeUnidadeNegocio);
				}
				if (descricaoElo != null) {
					bean.setNomeLocalidadePolo(descricaoElo);
				}
				if (descricaoLocalidade != null) {
					bean.setNomeLocalidade(descricaoLocalidade);
				}
				if (nomeAgente != null) {
					bean.setNomeAgente(nomeAgente);
				}
				if (nomeCategoria != null) {
					bean.setNomeCategoria(nomeCategoria);
				}
				if (nomePerfil != null) {
					bean.setNomePerfil(nomePerfil);
				}
				if (nomeDocumento != null) {
					bean.setNomeDocumento(nomeDocumento);
				}
				if (nomeArrecadacaoForma != null) {
					bean.setNomeArrecadacaoForma(nomeArrecadacaoForma);
				}
				bean.setValor(Util.formatarMoedaReal(valorTotal));

				Date data = (Date) itemHelper.getItemAgrupado();
				String quantidadeDocumentos = Util.agruparNumeroEmMilhares(itemHelper.getQuantidadeDocumentos());
				String quantidadePagamentos = Util.agruparNumeroEmMilhares(itemHelper.getQuantidadePagamentos());
				BigDecimal valorArrecadadoBruto = itemHelper.getValorDebitos();
				BigDecimal valorDescontos = itemHelper.getValorDescontos();
				BigDecimal valorDevolucoes = itemHelper.getValorDevolucoes();
				BigDecimal valorArrecadado = itemHelper.getValorArrecadacao();
				BigDecimal valorArrecadadoLiquido = itemHelper.getValorArrecadacaoLiquida();
				
				bean.setData(Util.formatarData(data));
				
				if (quantidadeDocumentos != null) {
					bean.setQuantDoc(quantidadeDocumentos);
				} else {
					bean.setQuantDoc("");
				}
				
				if (quantidadePagamentos != null) {
					bean.setQuantPag(quantidadePagamentos);
				} else {
					bean.setQuantPag("");
				}
				
				bean.setDebitos(Util.formatarMoedaReal(valorArrecadadoBruto));
				bean.setDescontos(Util.formatarMoedaReal(valorDescontos));
				bean.setValorArrecadado(Util.formatarMoedaReal(valorArrecadado));
				bean.setDevolucao(Util.formatarMoedaReal(valorDevolucoes));
				bean.setArrecadacaoLiquida(Util.formatarMoedaReal(valorArrecadadoLiquido));
				
				BigDecimal percentualMultiplicacao = itemHelper.getValorDebitos().multiply(new BigDecimal("100.00"));
				BigDecimal percentual = percentualMultiplicacao.divide(
						valorTotal,2,BigDecimal.ROUND_HALF_UP);
				valorDia = itemHelper.getValorArrecadacaoLiquida().add(valorDia);
				BigDecimal percentualMultiplicacaoDoDia = valorDia.multiply(new BigDecimal("100.00"));
				BigDecimal percentualDoDia = percentualMultiplicacaoDoDia.divide(
						valorTotal,2,BigDecimal.ROUND_HALF_UP);
				
				bean.setPercentual(Util.formatarMoedaReal(percentual));
				bean.setValorAteDia(Util.formatarMoedaReal(valorDia));
				bean.setPercentualDoDia(Util.formatarMoedaReal(percentualDoDia));
				
				if (itemHelper.getArrecadador() != null && !itemHelper.getArrecadador().isEmpty()) {
					bean.setArrecadador(itemHelper.getArrecadador());
				}
				
				colecaoBean.add(bean);
			}
		}
		
		return colecaoBean;
	}
}
