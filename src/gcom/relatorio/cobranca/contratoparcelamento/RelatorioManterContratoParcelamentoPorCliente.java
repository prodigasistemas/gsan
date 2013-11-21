package gcom.relatorio.cobranca.contratoparcelamento;

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamentoCliente;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Paulo Diniz
 * @param <dataImplantacaoInicial>
 * @date 25/03/2011
 * 
 */
public class RelatorioManterContratoParcelamentoPorCliente<dataImplantacaoInicial> extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	public RelatorioManterContratoParcelamentoPorCliente(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_CONTRATO_PARCELAMENTO_POR_CLIENTE);
	}
	public Object executar() throws TarefaException {

		FiltroContratoParcelamentoCliente filtroContratoParcelamentoCliente = (FiltroContratoParcelamentoCliente) getParametro("filtroContratoParcelamentoCliente"); 
		
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
		
		List<RelatorioManterContratoParcelamentoPorClienteBean> beans = fachada.gerarRelatorioManterContratoParcelamentoPorCliente(filtroContratoParcelamentoCliente);
		
		RelatorioDataSource ds = new RelatorioDataSource(beans);
		
		String numeroContrato = "";
		String numeroContratoAnt = "";
		String clienteContrato = "";
		String usuarioResp = "";
		String dataNegociacaoInicial = "";
		String dataNegociacaoFinal = "";
		String dataImplantacaoInicial = "";
		String dataImplantacaoFinal = "";
		String situacaoPgto = "Todos";
		String situacaoCancel = "Todos";
		String dataCancelamentoInicial = "";
		String dataCancelamentoFinal = "";
		String motivoCancelamento = "";
		
		List parametrosFiltro = new ArrayList(filtroContratoParcelamentoCliente.getParametros());
		for (Object object : parametrosFiltro) {
			if(object instanceof ParametroSimples){
				ParametroSimples parametro = (ParametroSimples) object;
				if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.NUMERO_CONTRATO)){
					numeroContrato = parametro.getValor() + "";
				}else if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.NUMERO_ANTERIOR)){
					numeroContratoAnt = parametro.getValor() + "";
				}else if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.ID_CLIENTE)){
					clienteContrato = parametro.getValor() + "";
				}else if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.USUARIO_RESPONSAVEL_ID)){
					usuarioResp = parametro.getValor() + "";
				}else if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.VALOR_A_COBRAR)){
					situacaoPgto = "Pagos";
				}else if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.MOTIVO_DESFAZER)){
					motivoCancelamento += parametro.getValor() + ", ";
				}
			}else if(object instanceof MaiorQue){
				MaiorQue parametro = (MaiorQue) object;
				if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.DATA_CONTRATO)){
					Date data = (Date)parametro.getNumero();
					dataNegociacaoInicial = (Util.formatarData(data));
				}else if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.DATA_IMPLANTACAO)){
					Date data = (Date)parametro.getNumero();
					dataImplantacaoInicial = (Util.formatarData(data));
				}else if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.VALOR_A_COBRAR)){
					situacaoPgto = "Pendentes";
				}else if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.DATA_CANCELAMENTO)){
					Date data = (Date)parametro.getNumero();
					dataCancelamentoInicial = (Util.formatarData(data));
				}
			}else if(object instanceof MenorQue){
				MenorQue parametro = (MenorQue) object;
				if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.DATA_CONTRATO)){
					Date data = (Date)parametro.getNumero();
					dataNegociacaoInicial = (Util.formatarData(data));
				}else if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.DATA_IMPLANTACAO)){
					Date data = (Date)parametro.getNumero();
					dataImplantacaoFinal = (Util.formatarData(data));
				}else if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.DATA_CANCELAMENTO)){
					Date data = (Date)parametro.getNumero();
					dataCancelamentoFinal = (Util.formatarData(data));
				}
			}else if(object instanceof ParametroNulo){
				ParametroNulo parametro = (ParametroNulo) object;
				if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.MOTIVO_DESFAZER)){
					situacaoCancel = "Não Cancelados";
				}
			}else if(object instanceof ParametroNaoNulo){
				ParametroNaoNulo parametro = (ParametroNaoNulo) object;
				if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.MOTIVO_DESFAZER)){
					situacaoCancel = "Cancelados";
				}
			}
		}
		
		if(!motivoCancelamento.equals("")){
			motivoCancelamento = motivoCancelamento.substring(0, motivoCancelamento.length() - 2);
		}
		
		parametros.put("numeroContrato", numeroContrato);
		parametros.put("numeroContratoAnt", numeroContratoAnt);
		parametros.put("clienteContrato", clienteContrato);
		parametros.put("usuarioResp", usuarioResp);
		parametros.put("dataNegociacaoInicial", dataNegociacaoInicial);
		parametros.put("dataNegociacaoFinal", dataNegociacaoFinal);
		parametros.put("dataImplantacaoInicial", dataImplantacaoInicial);
		parametros.put("dataImplantacaoFinal", dataImplantacaoFinal);
		parametros.put("situacaoPgto", situacaoPgto);
		parametros.put("situacaoCancel", situacaoCancel);
		parametros.put("dataCancelamentoInicial", dataCancelamentoInicial);
		parametros.put("dataCancelamentoFinal", dataCancelamentoFinal);
		parametros.put("motivoCancelamento", motivoCancelamento);
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_MANTER_CONTRATO_PARCELAMENTO_POR_CLIENTE,
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
		AgendadorTarefas.agendarTarefa("RelatorioManterContratoParcelamentoPorCliente",
				this);
	}
}
