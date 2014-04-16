package gcom.relatorio.atendimentopublico;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de bairro manter de água
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class RelatorioContratoPrestacaoServicoJuridico extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioContratoPrestacaoServicoJuridico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CONTRATO_PRESTACAO_SERVICO_JURIDICO);
	}
	
	@Deprecated
	public RelatorioContratoPrestacaoServicoJuridico() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */
	public Object executar() throws TarefaException {


		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		Integer idImovel = (Integer) getParametro("idImovel");
		Integer idClienteEmpresa = (Integer) getParametro("idCliente"); 

		// valor de retorno
		byte[] retorno = null;
		
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioContratoPrestacaoServicoJuridicoBean relatorioBean = null;
		
		// Dados da 1ª página
		relatorioBean = fachada.gerarContratoJuridica(idImovel, idClienteEmpresa);
		relatorioBean.setNumeroPagina("1");
		relatorioBeans.add(relatorioBean);

		RelatorioContratoPrestacaoServicoJuridicoBean relatorioBean2 = new RelatorioContratoPrestacaoServicoJuridicoBean("2");
		relatorioBean2.setData(relatorioBean.getData());
		relatorioBeans.add(relatorioBean2);		
		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CONTRATO_PRESTACAO_SERVICO_JURIDICO,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioContratoPrestacaoServicoJuridico", this);
	}
}
