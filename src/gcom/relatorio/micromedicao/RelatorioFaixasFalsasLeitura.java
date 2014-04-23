package gcom.relatorio.micromedicao;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.micromedicao.bean.FaixasFalsasLeituraRelatorioHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * classe responsável por criar o relatório do comparativo de leituras e anormalidades
 * 
 * @author Rafael Corrêa
 * @created 12/04/2007
 */
public class RelatorioFaixasFalsasLeitura extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioFaixasFalsasLeitura(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_FAIXAS_FALSAS_LEITURA);
	}

	@Deprecated
	public RelatorioFaixasFalsasLeitura() {
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

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Integer anoMesReferencia = (Integer) getParametro("anoMesReferencia");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioFaixasFalsasLeituraBean relatorioBean = null;
		
		Collection colecaoDadosRelatorioFaixasFalsasLeitura = fachada
				.pesquisarImovelFaixaFalsa(anoMesReferencia);

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoDadosRelatorioFaixasFalsasLeitura != null && !colecaoDadosRelatorioFaixasFalsasLeitura.isEmpty()) {
						
			Integer idSetorAnterior = null;
			Integer totalFaixasFalsas = new Integer("0");
			NumberFormat formato = NumberFormat.getInstance(new Locale("pt", "BR"));

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoDadosRelatorioFaixasFalsasLeituraIterator = colecaoDadosRelatorioFaixasFalsasLeitura.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoDadosRelatorioFaixasFalsasLeituraIterator.hasNext()) {

				FaixasFalsasLeituraRelatorioHelper faixasFalsasLeituraRelatorioHelper = (FaixasFalsasLeituraRelatorioHelper) colecaoDadosRelatorioFaixasFalsasLeituraIterator
						.next();
				
				if (idSetorAnterior != null && !idSetorAnterior.equals(faixasFalsasLeituraRelatorioHelper.getIdSetorComercial())) {
					totalFaixasFalsas = new Integer("0");
				}
				
				totalFaixasFalsas = totalFaixasFalsas + 1; 
				
				// Grupo de Faturamento
				String grupoFaturamento = "";
				if (faixasFalsasLeituraRelatorioHelper.getIdGrupoFaturamento() != null) {
					grupoFaturamento = faixasFalsasLeituraRelatorioHelper
							.getIdGrupoFaturamento().toString() + " - "
							+ faixasFalsasLeituraRelatorioHelper
									.getDescricaoGrupoFaturamento();
				}
				
				// Empresa
				String empresa = "";
				if (faixasFalsasLeituraRelatorioHelper.getIdEmpresa() != null) {
					empresa = faixasFalsasLeituraRelatorioHelper.getIdEmpresa().toString()
							+ " - "
							+ faixasFalsasLeituraRelatorioHelper
									.getNomeEmpresa();
				}
				
				// Localidade
				String localidade = "";
				if (faixasFalsasLeituraRelatorioHelper.getIdLocalidade() != null) {
					localidade = faixasFalsasLeituraRelatorioHelper.getIdLocalidade().toString()
							+ " - "
							+ faixasFalsasLeituraRelatorioHelper
									.getNomeLocalidade();
				}
				
				// Setor Comercial
				String setorComercial = "";
				String idSetorComercial = "";
				if (faixasFalsasLeituraRelatorioHelper.getIdSetorComercial() != null) {
					setorComercial = faixasFalsasLeituraRelatorioHelper.getCodigoSetorComercial().toString()
							+ " - "
							+ faixasFalsasLeituraRelatorioHelper
									.getNomeSetorComercial();
					idSetorComercial = faixasFalsasLeituraRelatorioHelper.getIdSetorComercial().toString();
					idSetorAnterior = faixasFalsasLeituraRelatorioHelper.getIdSetorComercial();
				}
				
				// Indicador
				String indicador = "";
				if (faixasFalsasLeituraRelatorioHelper.getFaixaFalsaInferior() != null
						&& faixasFalsasLeituraRelatorioHelper.getLeituraAtual() != null
						&& faixasFalsasLeituraRelatorioHelper
								.getFaixaFalsaSuperior() != null
						&& (faixasFalsasLeituraRelatorioHelper
								.getFaixaFalsaInferior().intValue() <= faixasFalsasLeituraRelatorioHelper
								.getLeituraAtual().intValue() && faixasFalsasLeituraRelatorioHelper
								.getLeituraAtual().intValue() <= faixasFalsasLeituraRelatorioHelper
								.getFaixaFalsaSuperior())) {
					
					indicador = "***";
					
				}
				
				// Inscrição
				String inscricao = "";
				if (faixasFalsasLeituraRelatorioHelper.getNumeroQuadra() != null) {
					
					String quadraFormatada = Util.adicionarZerosEsquedaNumero(
							3, faixasFalsasLeituraRelatorioHelper
									.getNumeroQuadra().toString());
					String loteFormatado = Util.adicionarZerosEsquedaNumero(
							4, faixasFalsasLeituraRelatorioHelper
									.getLote().toString());
					String subloteFormatado = Util.adicionarZerosEsquedaNumero(
							3, faixasFalsasLeituraRelatorioHelper
									.getSublote().toString());
					
					inscricao = quadraFormatada + "." + loteFormatado + "." + subloteFormatado;
				}
				
				// Matrícula
				String matricula = "";
				if (faixasFalsasLeituraRelatorioHelper.getIdImovel() != null) {
					matricula = Util.retornaMatriculaImovelFormatada(faixasFalsasLeituraRelatorioHelper.getIdImovel());
				}
				
				// Leiturista
				String leiturista = "";
				if (faixasFalsasLeituraRelatorioHelper.getIdLeiturista() != null) {
					leiturista = Util.retornaMatriculaImovelFormatada(faixasFalsasLeituraRelatorioHelper.getIdLeiturista());
				}
				
				// Faixa Correta
				String faixaCorreta = "";
				if (faixasFalsasLeituraRelatorioHelper.getFaixaCorretaInferior() != null) {
					faixaCorreta = formato.format(faixasFalsasLeituraRelatorioHelper
							.getFaixaCorretaInferior())
							+ " A "
							+ formato.format(faixasFalsasLeituraRelatorioHelper
									.getFaixaCorretaSuperior());  
				}
				
				// Faixa Falsa
				String faixaFalsa = "";
				if (faixasFalsasLeituraRelatorioHelper.getFaixaFalsaInferior() != null) {
					faixaFalsa = formato.format(faixasFalsasLeituraRelatorioHelper
							.getFaixaFalsaInferior())
							+ " A "
							+ formato.format(faixasFalsasLeituraRelatorioHelper
									.getFaixaFalsaSuperior());  
				}
				
				// Leitura Informada
				String leituraInformada = "";
				if (faixasFalsasLeituraRelatorioHelper.getLeituraAtual() != null) {
					leituraInformada = formato.format(faixasFalsasLeituraRelatorioHelper.getLeituraAtual());
				}
				
				// Data de Leitura
				String dataLeitura = "";
				if (faixasFalsasLeituraRelatorioHelper.getDataLeitura() != null) {
					dataLeitura = Util.formatarData(faixasFalsasLeituraRelatorioHelper.getDataLeitura());
				}
				
				relatorioBean = new RelatorioFaixasFalsasLeituraBean(
								// Grupo de Faturamento
								grupoFaturamento,
								
								// Empresa
								empresa, 
								
								// Localidade
								localidade,
								
								// Id do Setor Comercial
								idSetorComercial,
								
								// Setor Comercial
								setorComercial,
								
								// Indicador
								indicador,
								
								// Inscrição
								inscricao,
								
								// Matrícula
								matricula,
								
								// Leiturista
								leiturista,
								
								// Faixa Correta
								faixaCorreta,
								
								// Faixa Falsa
								faixaFalsa,
								
								// Leitura Informada
								leituraInformada,
								
								// Data de Leitura
								dataLeitura,
								
								// Anormalidade de Leitura
								faixasFalsasLeituraRelatorioHelper.getDescricaoLeituraAnormalidade(),
								
								// Situacao da Leitura
								faixasFalsasLeituraRelatorioHelper.getDescricaoSituacaoLeitura(),
								
								// Total de Faixas Falsas 
								totalFaixasFalsas.toString());

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
			
		}

		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("mesAnoReferencia", Util.formatarAnoMesParaMesAno(anoMesReferencia));

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_FAIXAS_FALSAS_LEITURA,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.COMPARATIVO_LEITURAS_E_ANORMALIDADES,
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
		Integer retorno = (Integer) getParametro("qtdeRegistros");
		
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioFaixasFalsasLeitura", this);

	}

}
