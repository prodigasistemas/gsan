package gcom.relatorio.cobranca;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.cobranca.contratoparcelamento.FiltrarRelatorioEmitirContratoParcelamentoPorClienteHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [UC1141] Emitir Contrato de Parcelamento por Cliente
 * 
 * Classe responsável por emitir o relatório com os dados de um Contrato de Parcelamento por Cliente
 * 
 * @author Mariana Victor
 *
 * @date 28/04/2011
 */
public class RelatorioEmitirContratoParcelamentoPorCliente extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioEmitirContratoParcelamentoPorCliente(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_EMITIR_CONTRATO_PARCELAMENTO_POR_CLIENTE);
	}

	@Deprecated
	public RelatorioEmitirContratoParcelamentoPorCliente() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 */
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		FiltrarRelatorioEmitirContratoParcelamentoPorClienteHelper relatorioHelper = 
			(FiltrarRelatorioEmitirContratoParcelamentoPorClienteHelper) getParametro("filtrarRelatorioEmitirContratoParcelamentoPorClienteHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		Fachada fachada = Fachada.getInstancia();
		
		RelatorioEmitirContratoParcelamentoPorClienteBean relatorioEmitirContratoParcelamentoPorClienteBean = null;
		
		List<RelatorioEmitirContratoParcelamentoPorClienteBean> colecaoBean = fachada
			.pesquisarDadosRelatorioContratoParcelamentoPorCliente(relatorioHelper);
		
		
		if (colecaoBean == null || colecaoBean.isEmpty()) {
			
			relatorioEmitirContratoParcelamentoPorClienteBean = 
				new RelatorioEmitirContratoParcelamentoPorClienteBean();
			
			colecaoBean.add(relatorioEmitirContratoParcelamentoPorClienteBean);
			
		}
		
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("nomeEmpresa", sistemaParametro.getNomeEmpresa());
		parametros.put("tipoFormatoRelatorio", "R1141");
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(colecaoBean);
		
		if(colecaoBean != null && colecaoBean.size() > 0){
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_EMITIR_CONTRATO_PARCELAMENTO_POR_CLIENTE,
					parametros, ds, tipoFormatoRelatorio);
		} else {
			
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_VAZIO,
					parametros, ds, tipoFormatoRelatorio);
		}
			
		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		int retorno = 1;
   
		return retorno;		
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioEmitirContratoParcelamentoPorCliente", this);
	}

}
