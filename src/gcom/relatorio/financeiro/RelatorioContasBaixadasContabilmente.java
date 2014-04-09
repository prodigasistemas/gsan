package gcom.relatorio.financeiro;

import gcom.batch.Relatorio;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [UC0726] Gerar Relatório das Contas Baixadas Contabilmente
 * 
 * @author Vivianne Sousa
 * @data 08/04/2008
 */

public class RelatorioContasBaixadasContabilmente extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioContasBaixadasContabilmente(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CONTAS_BAIXADAS_CONTABILMENTE);
	}
	
	@Deprecated
	public RelatorioContasBaixadasContabilmente() {
		super(null, "");
	}
	
	 public Collection<RelatorioContasBaixadasContabilmenteBean> inicializarBeanRelatorio(
         Integer referenciaInicial, Integer referenciaFinal,Short periodicidade)throws ControladorException{
		
		 Collection<RelatorioContasBaixadasContabilmenteBean> colecaoBean = new ArrayList();
		  
		 BigDecimal valorTotalFaixaUm = BigDecimal.ZERO;
		 
		 BigDecimal valorTotalFaixaDois = BigDecimal.ZERO;
		 
		 BigDecimal valorTotalFaixaTres = BigDecimal.ZERO;
		 
		 BigDecimal valorTotal = BigDecimal.ZERO;

		 
			//Preencher valor Setor Um
			Map faixaUmSetor = Fachada.getInstancia().consultarSomatorioValorContasBaixadasContabilmenteFaixa(
					referenciaInicial , referenciaFinal, ConstantesSistema.FAIXA_1 , periodicidade);
			
			
			valorTotalFaixaUm = (BigDecimal)faixaUmSetor.get("somatorio");
		
			//Preencher valor Setor Dois
			
			Map faixaDoisSetor = Fachada.getInstancia().consultarSomatorioValorContasBaixadasContabilmenteFaixa(
					referenciaInicial , referenciaFinal, ConstantesSistema.FAIXA_2 , periodicidade);
			
			
			valorTotalFaixaDois = (BigDecimal)faixaDoisSetor.get("somatorio");
			
		
			//Preencher valor Setor Tres
			
			Map faixaTresSetor = Fachada.getInstancia().consultarSomatorioValorContasBaixadasContabilmenteFaixa(
					referenciaInicial , referenciaFinal, ConstantesSistema.FAIXA_3 , periodicidade);
			
			valorTotalFaixaTres = (BigDecimal)faixaTresSetor.get("somatorio");
		
			valorTotal = valorTotal.add(valorTotalFaixaUm).add(valorTotalFaixaDois).add(valorTotalFaixaTres);
	
		
		 RelatorioContasBaixadasContabilmenteBean bean = new RelatorioContasBaixadasContabilmenteBean(
					valorTotalFaixaUm, 
					valorTotalFaixaDois, 
					valorTotalFaixaTres,
					valorTotal);
			
			colecaoBean.add(bean);
			
		return colecaoBean;
		}

	
	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
        Short tipo = (Short)getParametro("tipo");
        Short periodicidade = (Short)getParametro("periodicidade");
        Integer referenciaInicial = (Integer) getParametro("referenciaInicial");
        Integer referenciaFinal = (Integer) getParametro("referenciaFinal");
        Integer tipoRelatorio = TarefaRelatorio.TIPO_PDF;
        	
        String referenciaIni = Util.formatarAnoMesParaMesAno(referenciaInicial);
        String referenciaFin = Util.formatarAnoMesParaMesAno(referenciaFinal);
        
        String tipoDeRelatorio = "";
        if (tipo == 1){
        	tipoDeRelatorio = "Analítico";
        } else 
        	if (tipo == 2){
        		tipoDeRelatorio = "Sintético";
        	}
        
        String periodicidadeParametros = "";
        if (periodicidade == 1){
        	periodicidadeParametros = "Mensal";
        } else
        	if (periodicidade == 2){
        		periodicidadeParametros = "Acumulado";
        	}
        
		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map parametros = new HashMap();

		Fachada fachada = Fachada.getInstancia();

		Collection<RelatorioContasBaixadasContabilmenteBean> colecaoBean = null;
		try {
			colecaoBean = this.inicializarBeanRelatorio(
                        referenciaInicial,  
                        referenciaFinal,
                        periodicidade);
			
		} catch (ControladorException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("periodicidade", periodicidadeParametros);
		
		if (periodicidade != 2){
		parametros.put("referenciaInicial", referenciaIni);
		}
		
		parametros.put("referencialFinal", referenciaFin);
		
		parametros.put("tipo", tipoDeRelatorio);
		
		parametros.put("relatorio", "R0726");

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CONTAS_BAIXADAS_CONTABILMENTE, parametros,
				ds, tipoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.CONTAS_BAIXADAS_CONTABILMENTE, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
		//return null;
	}


	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioContasBaixadasContabilmente", this);
	}
}
