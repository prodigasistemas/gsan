package gcom.relatorio.cobranca;

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.FiltroDocumentosReceberFaixaDiasVencidos;
import gcom.fachada.Fachada;
import gcom.financeiro.FaixaDocumentosAReceber;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioAnalisePerdasCreditos extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioAnalisePerdasCreditos(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ANALISE_PERDAS_CREDITOS);
	}

	@Override
	public Object executar() throws TarefaException {
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String mesAno = (String) getParametro("mesAno");
		
		//valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();
		
		int maiorAnoMesExistente = fachada.maiorAnoMesReferenciaDocumentosAReceberResumo();
 
		
		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório 
		// adiciona o laudo da análise
		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro,SistemaParametro.class.getName());
		
		SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("mesAnoReferencia",mesAno.substring(4,6)+"/"+mesAno.substring(0,4));
		
		List<FaixaDocumentosAReceber> faixas = new ArrayList<FaixaDocumentosAReceber>();
		Collection colecao = fachada.pesquisar(new FiltroDocumentosReceberFaixaDiasVencidos(FiltroDocumentosReceberFaixaDiasVencidos.ID), FaixaDocumentosAReceber.class.getName());
		faixas.addAll(colecao);
		
		if(Integer.parseInt(mesAno) > maiorAnoMesExistente){
			throw new ActionServletException("atencao.mes.ano.superior");
		}
		
		if(faixas.size() != 3){
			throw new ActionServletException("atencao.conteudo.tabela.faixa.invalido");
		}
		
		RelatorioAnalisePerdasCreditosBean bean = fachada.gerarRelatorioAnalisePerdasCreditos(mesAno);
		
		if(bean == null){
			throw new ActionServletException("atencao.pesquisa.sem.resultado");
		}
		
		List<RelatorioAnalisePerdasCreditosBean> listaParaDS = new ArrayList<RelatorioAnalisePerdasCreditosBean>();
		listaParaDS.add(bean);
		RelatorioDataSource ds = new RelatorioDataSource(listaParaDS);
		
		
		String primeiraFaixaTexto = "";
		if(faixas.get(0).getValorFaixaInicial().intValue() != 0){
			primeiraFaixaTexto = "Entre R$ " + Util.formatarMoedaReal(faixas.get(0).getValorFaixaInicial()) + " e R$ "+Util.formatarMoedaReal(faixas.get(0).getValorFaixaFinal()) ;
		}else{
			primeiraFaixaTexto = "Até R$ " + Util.formatarMoedaReal(faixas.get(0).getValorFaixaFinal());
		}
		
		parametros.put("primeiraFaixaVencidosSuperiorSeisMeses", bean.getPrimeiraFaixaVencidosSuperiorSeisMeses());
		parametros.put("primeiraFaixaVencidosAteSeisMeses",bean.getPrimeiraFaixaVencidosAteSeisMeses());
		parametros.put("primeiraFaixaTotal",bean.getPrimeiraFaixaTotal());
		parametros.put("primeiraFaixaVencer",bean.getPrimeiraFaixaVencer());
		parametros.put("primeiraFaixaTexto",primeiraFaixaTexto);
		
		String segundaFaixaTexto = "Entre R$ " + Util.formatarMoedaReal(faixas.get(1).getValorFaixaInicial()) + " e R$ "+Util.formatarMoedaReal(faixas.get(1).getValorFaixaFinal()) ;
		parametros.put("segundaFaixaVencidosAteDoseMeses",bean.getSegundaFaixaVencidosAteDoseMeses());
		parametros.put("segundaFaixaVencidosSuperiorDoseMeses",bean.getSegundaFaixaVencidosSuperiorDoseMeses());
		parametros.put("segundaFaixaVencer",bean.getSegundaFaixaVencer());
		parametros.put("segundaFaixaTotal",bean.getSegundaFaixaTotal());
		parametros.put("segundaFaixaTexto",segundaFaixaTexto);

		String terceiraFaixaTexto = "";
		if(faixas.get(2).getValorFaixaFinal().precision() <= 25){
			terceiraFaixaTexto = "Entre R$ " + Util.formatarMoedaReal(faixas.get(2).getValorFaixaInicial()) + " e R$ "+Util.formatarMoedaReal(faixas.get(2).getValorFaixaFinal());
		}else{
			terceiraFaixaTexto = "Superior a R$ " + Util.formatarMoedaReal(faixas.get(2).getValorFaixaInicial());
		}
		parametros.put("terceiraFaixaVencidosAteDoseMeses",bean.getTerceiraFaixaVencidosAteDoseMeses());
		parametros.put("terceiraFaixaVencidosSuperiorDoseMeses",bean.getTerceiraFaixaVencidosSuperiorDoseMeses());
		parametros.put("terceiraFaixaVencer",bean.getTerceiraFaixaVencer());	
		parametros.put("terceiraFaixaTotal",bean.getTerceiraFaixaTotal());
		parametros.put("terceiraFaixaTexto",terceiraFaixaTexto);
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ANALISE_PERDAS_CREDITOS,
		parametros, ds, tipoFormatoRelatorio);
		
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
		AgendadorTarefas.agendarTarefa("RelatorioAnalisePerdasCreditos",
				this);
	}
	
}
