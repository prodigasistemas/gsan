package gcom.gui.portal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;

/**
 * [UC1194] Consultar Estrutura Tarifária Loja Virtual
 *
 * Classe responsável por gerar o relatório relatorioEstruturaTarifariaLojaVirtual.jasper
 * 
 * @author Diogo Peixoto
 * @since 18/07/2011
 *
 */
public class RelatorioEstruturaTarifaria extends TarefaRelatorio {

	public RelatorioEstruturaTarifaria(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ESTRUTURA_TARIFARIA_LOJA_VIRTUAL);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	@Override
	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		SistemaParametro sistemaParametro = (SistemaParametro) getParametro("sistemaParametro");
		List<ConsultarEstruturaTarifariaPortalHelper> estruturaTarifariaHelper =  (List<ConsultarEstruturaTarifariaPortalHelper>) getParametro("estruturaTarifariaBeans");
		List<RelatorioEstruturaTarifariaBean> beans = new ArrayList<RelatorioEstruturaTarifariaBean>();
		RelatorioEstruturaTarifariaBean bean = null;
		
		for(ConsultarEstruturaTarifariaPortalHelper helper : estruturaTarifariaHelper){
			//Se for um consumo medido
			if(helper.getIndiceConsumo() == 1){
				bean = new RelatorioEstruturaTarifariaBean(helper.getCategoria(), helper.getQuantidade(), helper.getValor(), 1);
			//Se for um consumo não medido.	
			}else if(helper.getIndiceConsumo() == 2){
				bean = new RelatorioEstruturaTarifariaBean(helper.getCategoria(), "", helper.getValor() + " " + helper.getQuantidade(), 2);
			}else if(helper.getIndiceConsumo() == 3){
				bean = new RelatorioEstruturaTarifariaBean(helper.getCategoria(), helper.getQuantidade(), helper.getValor() + " por 1.000L", 3);
			}
			beans.add(bean);
		}
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("imagemConta", sistemaParametro.getImagemRelatorio());
		
		byte[] retorno = null;
		
		RelatorioDataSource ds = new RelatorioDataSource(beans);
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ESTRUTURA_TARIFARIA_LOJA_VIRTUAL, parametros, ds, tipoFormatoRelatorio);

		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_ESTRUTURA_TARIFARIA_LOJA_VIRTUAL, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {

	}
}
