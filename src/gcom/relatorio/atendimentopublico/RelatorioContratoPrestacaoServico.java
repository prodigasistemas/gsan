package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.bean.ContratoPrestacaoServicoHelper;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * classe responsável por criar o relatório de bairro manter de água
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class RelatorioContratoPrestacaoServico extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioContratoPrestacaoServico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CONTRATO_PRESTACAO_SERVICO);
	}
	
	@Deprecated
	public RelatorioContratoPrestacaoServico() {
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

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		Integer idImovel = (Integer) getParametro("idImovel");

		Integer idCliente = (Integer) getParametro("idCliente");

		// valor de retorno
		byte[] retorno = null;
		
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioContratoPrestacaoServicoBean relatorioBean = null;
		
		Collection colecaoContratoPrestacaoServicoHelper = fachada.obterDadosContratoPrestacaoServico(idImovel, idCliente);


		// se a coleção de parâmetros da analise não for vazia
		if (colecaoContratoPrestacaoServicoHelper != null
				&& !colecaoContratoPrestacaoServicoHelper.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoContratoPrestacaoServicoHelperIterator = colecaoContratoPrestacaoServicoHelper
					.iterator();
			
			Date dataCorrente = new Date();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoContratoPrestacaoServicoHelperIterator.hasNext()) {

				ContratoPrestacaoServicoHelper contratoPrestacaoServicoHelper = (ContratoPrestacaoServicoHelper) colecaoContratoPrestacaoServicoHelperIterator
						.next();
				
				// Faz as validações dos campos necessários e formata a String
				// para a forma como irá aparecer no relatório
				
				// Nome Cliente
				String nomeCliente = "";
				if (contratoPrestacaoServicoHelper.getCliente().getNome() != null) {
					nomeCliente = contratoPrestacaoServicoHelper.getCliente().getNome(); 
				}
				
				// Nome Localidade
				String nomeLocalidade = "";
				if (contratoPrestacaoServicoHelper.getNomeLocalidade() != null) {
					nomeLocalidade = contratoPrestacaoServicoHelper.getNomeLocalidade(); 
				}
				
				// Nome Responsável
				String nomeResponsavel = "";
				if (contratoPrestacaoServicoHelper.getClienteResponsavel().getNome() != null) {
					nomeResponsavel = contratoPrestacaoServicoHelper.getClienteResponsavel().getNome(); 
				}
				
				// CPF Responsável
				String cpfResponsavel = "";
				if (contratoPrestacaoServicoHelper.getClienteResponsavel().getCpf() != null) {
					cpfResponsavel = contratoPrestacaoServicoHelper.getClienteResponsavel().getCpfFormatado(); 
				}
				
				// RG Responsável
				String rgResponsavel = "";
				if (contratoPrestacaoServicoHelper.getClienteResponsavel().getRg() != null) {
					rgResponsavel = contratoPrestacaoServicoHelper.getClienteResponsavel().getRg(); 
				}
				
				// CPF Cliente
				String cpfCliente = "";
				if (contratoPrestacaoServicoHelper.getCliente().getCpf() != null) {
					cpfCliente = contratoPrestacaoServicoHelper.getCliente().getCpfFormatado(); 
				}
				
				// RG Cliente
				String rgCliente = "";
				if (contratoPrestacaoServicoHelper.getCliente().getRg() != null) {
					rgCliente = contratoPrestacaoServicoHelper.getCliente().getRg(); 
				}
				
				// Consumo Mínimo
				String consumoMinimo = "";
				if (contratoPrestacaoServicoHelper.getConsumoMinimo() != null) {
					consumoMinimo = contratoPrestacaoServicoHelper.getConsumoMinimo().toString(); 
				}
				
				String anoCorrente = "" + Util.getAno(dataCorrente);
				
//				 Pega a Data Atual formatada da seguinte forma: dd de mês(por
				// extenso) de aaaa
				// Ex: 23 de maio de 1985
				DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale
						.getDefault());
				String dataCorrenteFormatada = df.format(new Date());
				
				
				// Dados da 1ª página
				relatorioBean = new RelatorioContratoPrestacaoServicoBean(
						
						// Indicador Pessoa Física
						"1",
						
						// Número Página
						"1",
						
						// Número Contrato
						idImovel.toString() + anoCorrente,
						
						// Nome Cliente
						nomeCliente,
						
						// Nome Localidade
						nomeLocalidade,
						
						// Nome Responsável
						nomeResponsavel,
						
						// CPF Responsável
						cpfResponsavel,
						
						// RG Responsável
						rgResponsavel,
						
						// CPF Cliente
						cpfCliente,
						
						// RG Cliente
						rgCliente,
						
						// Endereço Cliente
						contratoPrestacaoServicoHelper.getEnderecoCliente(),
						
						// Id Imóvel
						idImovel.toString(),
						
						// Endereço Imóvel
						contratoPrestacaoServicoHelper.getEnderecoImovel(),
						
						// Categoria
						contratoPrestacaoServicoHelper.getCategoria(),
						
						// Consumo Mínimo
						consumoMinimo,
						
						// Data Corrente
						"",
				
						// Município
						"");
				
				relatorioBeans.add(relatorioBean);
				
				// Dados da 2ª página
				relatorioBean = new RelatorioContratoPrestacaoServicoBean(
						
						// Indicador Pessoa Física
						"1",
						
						// Número Página
						"2",
						
						// Número Contrato
						"",
						
						// Nome Cliente
						"",
						
						// Nome Unidade Negócio
						nomeLocalidade,
						
						// Nome Responsável
						"",
						
						// CPF Responsável
						"",
						
						// RG Responsável
						"",
						
						// CPF Cliente
						"",
						
						// RG Cliente
						"",
						
						// Endereço Cliente
						"",
						
						// Id Imóvel
						"",
						
						// Endereço Imóvel
						"",
						
						// Categoria
						"",
						
						// Consumo Mínimo
						"",
						
						// Data Corrente
						dataCorrenteFormatada,
						
						// Município
						contratoPrestacaoServicoHelper.getNomeMunicipio());

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
				
//				relatorioBean = new RelatorioContratoPrestacaoServicoBean(
//						
//						// Indicador Pessoa Física
//						"1",
//						
//						// Número Página
//						"2");

				// adiciona o bean a coleção
//				relatorioBeans.add(relatorioBean);
				
//				relatorioBean = new RelatorioContratoPrestacaoServicoBean(
//						
//						// Indicador Pessoa Física
//						"2",
//						
//						// Número Página
//						"1");
//
//				// adiciona o bean a coleção
//				relatorioBeans.add(relatorioBean);
//				
//				relatorioBean = new RelatorioContratoPrestacaoServicoBean(
//						
//						// Indicador Pessoa Física
//						"2",
//						
//						// Número Página
//						"2");
//
//				// adiciona o bean a coleção
//				relatorioBeans.add(relatorioBean);
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CONTRATO_PRESTACAO_SERVICO,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_RESOLUCAO_DIRETORIA,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
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
		AgendadorTarefas.agendarTarefa("RelatorioManterResolucaoDiretoria", this);
	}
}
