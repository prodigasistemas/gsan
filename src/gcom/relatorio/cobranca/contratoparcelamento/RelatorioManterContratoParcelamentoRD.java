package gcom.relatorio.cobranca.contratoparcelamento;

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamentoRD;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Paulo Diniz
 * @date 25/03/2011
 * 
 */
public class RelatorioManterContratoParcelamentoRD extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	public RelatorioManterContratoParcelamentoRD(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_CONTRATO_PARCELAMENTO_RD);
	}
	public Object executar() throws TarefaException {

		FiltroContratoParcelamentoRD filtroContratoParcelamento = (FiltroContratoParcelamentoRD) getParametro("filtroContratoParcelamento"); 
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;


		Fachada fachada = Fachada.getInstancia();

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		
		Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro,SistemaParametro.class.getName());
		
		SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		List<RelatorioManterContratoParcelamentoRDBean> beans = fachada.gerarRelatorioManterContratoParcelamentoRD(filtroContratoParcelamento);
		
		RelatorioDataSource ds = new RelatorioDataSource(beans);
		

		String numero = "";
		String assunto = "";
		String vigenciaInicial = "";
		String vigenciaFinal = "";

		List parametrosFiltro = new ArrayList(filtroContratoParcelamento.getParametros());
		for (Object object : parametrosFiltro) {
			if(object instanceof ComparacaoTexto){
				ComparacaoTexto campo = (ComparacaoTexto) object;
				if(campo.getNomeAtributo().equals("numero")){
					numero = campo.getValor();
					numero = numero.replace("%","");
				}else{
					assunto = campo.getValor();
					assunto = assunto.replace("%","");
				}
			}else if(object instanceof MaiorQue){
				MaiorQue campo = (MaiorQue) object;
				Date data = (Date)campo.getNumero();
				vigenciaInicial = (Util.formatarData(data));
			}else if(object instanceof MenorQue){
				MenorQue campo = (MenorQue) object;
				Date data = (Date)campo.getNumero();
				vigenciaFinal = (Util.formatarData(data));
			}
		
		}
		parametros.put("numero", numero);
		parametros.put("assunto", assunto);
		parametros.put("dataVigenciaInicial", vigenciaInicial);
		parametros.put("dataVigenciaFinal", vigenciaFinal);
		
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_MANTER_CONTRATO_PARCELAMENTO_RD,
		parametros, ds, tipoFormatoRelatorio);
		
		
		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 1;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterContratoParcelamentoRD",
				this);
	}
}
