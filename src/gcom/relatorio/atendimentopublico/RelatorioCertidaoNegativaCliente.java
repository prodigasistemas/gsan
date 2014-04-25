package gcom.relatorio.atendimentopublico;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de certidao negativa
 * 
 * @author Bruno Barros
 * @created 29/01/2008
 */
public class RelatorioCertidaoNegativaCliente extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioCertidaoNegativaCliente(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CERTIDAO_NEGATIVA);
	}

	@Deprecated
	public RelatorioCertidaoNegativaCliente() {
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
	@SuppressWarnings("unchecked")
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Collection<Integer> idsClientes = (Collection<Integer>) getParametro("idsClientes");
		Cliente clienteInformado = (Cliente) getParametro("clienteInformado");
		Usuario usuarioLogado = (Usuario) getParametro("usuarioLogado");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		Collection<RelatorioCertidaoNegativaClienteBean> colecao = fachada
				.pesquisarRelatorioCertidaoNegativaCliente(idsClientes,
						clienteInformado);

		// se a coleção de parâmetros da analise não for vazia
		if (colecao != null && !colecao.isEmpty()) {
			// adiciona o bean a coleção
			relatorioBeans.addAll(colecao);				
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		Date dataAtual = new Date();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		String nomeRelatorio = ConstantesRelatorios.RELATORIO_CERTIDAO_NEGATIVA_CLIENTE;
		
		
		if (sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAEMA)) {
			
			parametros.put("textoCertidaoNegativa",
					"Pelo presente instrumento certificamos, para fins de direito, que revendo os nossos controles, não encontramos débitos referente(s) ao(s) imóvel(eis) acima especificado(s) até a presente data: "
							+ Util.formatarData(dataAtual) + ".");
	
			parametros.put("validade", "IMPORTANTE: Qualquer rasura tornará nulo o efeito desta certidão, que tem validade de 60 dias.");
			parametros.put("atendente", usuarioLogado.getNomeUsuario());
			parametros.put("nomeEmpresa", "COMPANHIA DE SANEAMENTO AMBIENTAL DO MARANHÃO");
			parametros.put("cnpjEmpresa", Util.formatarCnpj( sistemaParametro.getCnpjEmpresa()) );
			parametros.put("inscricaoEstadual", Util.formatarInscricaoEstadualCaema( sistemaParametro.getInscricaoEstadual()) );
			parametros.put("nomeRelatorio", "CERTIDÃO NEGATIVA DE DÉBITOS POR CLIENTE");
			nomeRelatorio = ConstantesRelatorios.RELATORIO_CERTIDAO_NEGATIVA_CLIENTE_CAEMA;
			
		} else {
			
			parametros.put("textoCertidaoNegativa",
					"Pelo presente instrumento certificamos, para fins de direito, que revendo os nossos controles, não encontramos débitos referente(s) ao(s) imóvel(eis) acima especificado(s) até a presente data: "
							+ Util.formatarData(dataAtual) + ".");
	
			parametros.put("validade", "IMPORTANTE: Qualquer rasura tornará nulo o efeito desta certidão, que tem validade de 60 dias.");
			parametros.put("atendente", usuarioLogado.getNomeUsuario());
			parametros.put("nomeEmpresa", sistemaParametro.getNomeEmpresa());
			parametros.put("cnpjEmpresa", Util.formatarCnpj( sistemaParametro.getCnpjEmpresa()) );
			parametros.put("inscricaoEstadual", sistemaParametro.getInscricaoEstadual());
			parametros.put("nomeRelatorio", "CERTIDÃO NEGATIVA DE DÉBITOS POR CLIENTE");
		}

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(nomeRelatorio,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
//		try {
//			persistirRelatorioConcluido(retorno, Relatorio.CERTIDAO_NEGATIVA,
//					idFuncionalidadeIniciada);
//		} catch (ControladorException e) {
//			e.printStackTrace();
//			throw new TarefaException("Erro ao gravar relatório no sistema", e);
//		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioCertidaoNegativaCliente", this);
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}
}
