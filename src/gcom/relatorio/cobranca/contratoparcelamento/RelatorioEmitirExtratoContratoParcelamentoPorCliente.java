package gcom.relatorio.cobranca.contratoparcelamento;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.contratoparcelamento.PrestacaoContratoParcelamentoHelper;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

public class RelatorioEmitirExtratoContratoParcelamentoPorCliente extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	public RelatorioEmitirExtratoContratoParcelamentoPorCliente(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_EMITIR_EXTRATO_CONTRATO_PARCELAMENTO_CLIENTE);
	}
	public Object executar() throws TarefaException {

		EmitirExtratoContratoParcelamentoPorClienteHelper helper = 
			(EmitirExtratoContratoParcelamentoPorClienteHelper) 
				getParametro("relatorioEmitirExtratoContratoParcelamentoPorClienteHelper");
		Collection<PrestacaoContratoParcelamentoHelper> colecaoHelper = 
			(Collection<PrestacaoContratoParcelamentoHelper>) 
				getParametro("colecaoPrestacaoContratoParcelamentoHelper");
		Usuario usuarioLogado = (Usuario) getParametro("usuarioLogado");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		List<RelatorioEmitirExtratoContratoParcelamentoPorClienteBean> relatorioBeans = 
			fachada.pesquisarDadosRelatorioEmitirExtratoContratoParcelamentoPorCliente(
				helper, colecaoHelper);
		
		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		String nomeEmpresa = sistemaParametro.getNomeEmpresa();
		String cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("nomeEmpresa", nomeEmpresa);
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		parametros.put("nomeUsuario", usuarioLogado.getNomeUsuario());

		if (sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(
				SistemaParametro.EMPRESA_COMPESA)){
			parametros.put("agenciaCodigoCedente", "3234-4/2868-1");
		} else if (sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(
				SistemaParametro.EMPRESA_CAERN)){
			parametros.put("agenciaCodigoCedente", "3795-8/9121-9");
		}
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_EMITIR_EXTRATO_CONTRATO_PARCELAMENTO_CLIENTE,
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
		AgendadorTarefas.agendarTarefa("RelatorioEmitirExtratoContratoParcelamentoPorCliente",
				this);
	}
	

}
