package gcom.relatorio.arrecadacao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
 * Gerar Relatório Dados Diários da Arrecadação por Arrecadador
 * 
 * @author Mariana Victor
 * @date 02/02/2011
 */
public class RelatorioDadosDiariosArrecadacaoArrecadador extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	
	public RelatorioDadosDiariosArrecadacaoArrecadador(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RELATORIO_DADOS_DIARIOS_ARRECADACAO_ARRECADADOR);
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
		
		Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> mapDadosDiariosAnoMes =
			(Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>>) getParametro("mapDadosDiariosAnoMes");
		
		Map<Integer, FiltrarDadosDiariosArrecadacaoHelper> mapDadosProcessamento =
			(Map<Integer, FiltrarDadosDiariosArrecadacaoHelper>) getParametro("mapDadosProcessamento");
		Collection<BigDecimal> colecaoValorTotal = (Collection<BigDecimal>) getParametro("colecaoValorTotal");
		
		BigDecimal valorTotalPeriodo = (BigDecimal) getParametro("valorTotalPeriodo");
		
		String dataAtual = (String) getParametro("dataAtual");
		
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
		if (nomeArrecadador != null){
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
		
		if (sistemaParametro.getCdDadosDiarios() != null){
			parametros.put("exibirFaturamentoCobradoConta", sistemaParametro.getCdDadosDiarios().toString());
		}
		
		Collection<RelatorioDadosDiariosArrecadacaoArrecadadorBean> colecaoBean = this
			.inicializarBeanRelatorio(mapDadosDiariosAnoMes, colecaoValorTotal,
					valorTotalPeriodo, mapDadosProcessamento, dataAtual);

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this
				.gerarRelatorio(
						ConstantesRelatorios.RELATORIO_RELATORIO_DADOS_DIARIOS_ARRECADACAO_ARRECADADOR,
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
		AgendadorTarefas.agendarTarefa("RelatorioDadosDiariosArrecadacaoArrecadador", this);
	}
	
	private Collection<RelatorioDadosDiariosArrecadacaoArrecadadorBean> inicializarBeanRelatorio(
			Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> mapDadosDiariosAnoMes,
			Collection<BigDecimal> colecaoValorTotal, BigDecimal valorTotalPeriodo,
			Map<Integer, FiltrarDadosDiariosArrecadacaoHelper> mapDadosProcessamento,
			String dataAtual){
		
		Collection<RelatorioDadosDiariosArrecadacaoArrecadadorBean> colecaoBean = new ArrayList<RelatorioDadosDiariosArrecadacaoArrecadadorBean>();
		
		BigDecimal valorPercentualMesAnteriorParaCalculo = new BigDecimal(0.0);
        
        BigDecimal percentualEvolucao = new BigDecimal(0.0);	                

        boolean primeiraVez = true;
        
		for (Integer itemAnoMes : mapDadosDiariosAnoMes.keySet()) {
			
			FiltrarDadosDiariosArrecadacaoHelper helperProcessamento = mapDadosProcessamento.get(itemAnoMes);
			
			for (FiltrarDadosDiariosArrecadacaoHelper helper : mapDadosDiariosAnoMes.get(itemAnoMes)){
				RelatorioDadosDiariosArrecadacaoArrecadadorBean bean = new RelatorioDadosDiariosArrecadacaoArrecadadorBean();
				
				Integer anoMes = (Integer) helper.getItemAgrupado();
				
		    	BigDecimal valorItemAnoMes = helper.getValorArrecadacaoLiquida(); 
		    	
				if(!primeiraVez) {
					BigDecimal valorSubtracao = valorItemAnoMes.subtract(valorPercentualMesAnteriorParaCalculo);
					
					BigDecimal valorMultiplicacaoParaCalculoEvolucao = valorSubtracao.multiply(new BigDecimal("100.00"));
					
					percentualEvolucao = valorMultiplicacaoParaCalculoEvolucao.divide(
						valorPercentualMesAnteriorParaCalculo,2,BigDecimal.ROUND_HALF_UP);
                }	

		  		valorPercentualMesAnteriorParaCalculo = valorItemAnoMes;
		    
    		    BigDecimal valorArrecadado = helper.getValorArrecadacao();
				
				bean.setUltimoProcessamentoAtual(dataAtual);
				bean.setFaturamentoCobradoEmConta(helperProcessamento.getFaturamentoCobradoEmConta());
				bean.setMesAno(Util.formatarAnoMesParaMesAno(anoMes));
				bean.setDebitos(Util.formatarMoedaReal(helper.getValorDebitos()));
				bean.setDescontos(Util.formatarMoedaReal(helper.getValorDescontos()));
				bean.setValorArrecadado(Util.formatarMoedaReal(valorArrecadado));
				bean.setDevolucao(Util.formatarMoedaReal(helper.getValorDevolucoes()));
				bean.setArrecadacaoLiquida(Util.formatarMoedaReal(helper.getValorArrecadacaoLiquida()));

                if(primeiraVez){
           	 		primeiraVez = false;
           	 		bean.setEvolucaoMesAnterior("");
                } else {
                	bean.setEvolucaoMesAnterior(Util.formatarMoedaReal(percentualEvolucao) + " %");
                }
                
				bean.setValorTotalPeriodo(Util.formatarMoedaReal(valorTotalPeriodo));
				
				colecaoBean.add(bean);
			}
			
		}
		
		return colecaoBean;
	}
}
