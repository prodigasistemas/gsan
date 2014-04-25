package gcom.relatorio.micromedicao;

import gcom.batch.Relatorio;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.FiltroCep;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.micromedicao.medicao.FiltroMedicaoHistoricoSql;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de bairro manter de água
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class RelatorioAvisoAnormalidade extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioAvisoAnormalidade(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_AVISO_ANORMALIDADE);
	}

	@Deprecated
	public RelatorioAvisoAnormalidade() {
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

		Integer anoMesFaturamentoGrupo = (Integer) getParametro("anoMesFaturamentoGrupo");
		Integer anoMesPesquisa = (Integer) getParametro("anoMesPesquisa");
		Collection colecaoImoveisGerarAviso = (Collection<Imovel>) getParametro("colecaoImoveisGerarAviso");
		FiltrarAnaliseExcecoesLeiturasHelper filtrarAnaliseExcecoesLeiturasHelper = (FiltrarAnaliseExcecoesLeiturasHelper) getParametro("filtrarAnaliseExcecoesLeiturasHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioAvisoAnormalidadeBean relatorioBean = null;

		Collection<AvisoAnormalidadeRelatorioHelper> colecaoAvisosAnormalidadeHelper = null;
		
		if (colecaoImoveisGerarAviso != null && !colecaoImoveisGerarAviso.isEmpty()) {
			colecaoAvisosAnormalidadeHelper = fachada.pesquisarAvisoAnormalidadeRelatorio(colecaoImoveisGerarAviso, anoMesPesquisa);
		} else {
			colecaoAvisosAnormalidadeHelper = fachada.pesquisarAvisoAnormalidadeRelatorio(filtrarAnaliseExcecoesLeiturasHelper, anoMesPesquisa);
		}

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoAvisosAnormalidadeHelper != null && !colecaoAvisosAnormalidadeHelper.isEmpty()) {
			
			for (AvisoAnormalidadeRelatorioHelper avisoAnormalidadeRelatorioHelper : colecaoAvisosAnormalidadeHelper) {
				
				relatorioBean = new RelatorioAvisoAnormalidadeBean(
						// Nome do Cliente
						avisoAnormalidadeRelatorioHelper.getNomeCliente(),
						
						// Endereço
						avisoAnormalidadeRelatorioHelper.getEndereco(),
						
						// Matrícula do Imóvel
						Util.retornaMatriculaImovelFormatada(avisoAnormalidadeRelatorioHelper.getIdImovel()),
						
						// Inscrição
						avisoAnormalidadeRelatorioHelper.getInscricao(),
						
						// Código da Rota
						Util.adicionarZerosEsquedaNumero(3, avisoAnormalidadeRelatorioHelper.getCodigoRota().toString()),
						
						// Sequencial da Rota
						avisoAnormalidadeRelatorioHelper.getSequencialRota() != null?"." + Util.adicionarZerosEsquedaNumero(4, avisoAnormalidadeRelatorioHelper.getSequencialRota().toString()): "",
						
						
//						Util.adicionarZerosEsquedaNumero(4, avisoAnormalidadeRelatorioHelper.getSequencialRota().toString()),
						
						// Mês/Ano 
						Util.formatarAnoMesParaMesAno(anoMesFaturamentoGrupo),
						
						// Anormalidade
						avisoAnormalidadeRelatorioHelper.getDescricaoAnormalidadeConsumo(),
						
						// Munícipio
						avisoAnormalidadeRelatorioHelper.getNomeMunicipio(),
						
						// Consumo Faturado
						avisoAnormalidadeRelatorioHelper.getConsumoFaturado(),
						
						// Consumo Médio
						avisoAnormalidadeRelatorioHelper.getConsumoMedio(),
						
						// Variação de Consumo 
						avisoAnormalidadeRelatorioHelper.getVariacaoConsumo(),
						
						// Consumo Medido
						avisoAnormalidadeRelatorioHelper.getConsumoMedido()
				);

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
		
		FiltroBairro filtroBairro = new FiltroBairro();
		filtroBairro.adicionarCaminhoParaCarregamentoEntidade("municipio");
		filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.ID, sistemaParametro.getBairro().getId()));
		
		Collection colecaoBairros = fachada.pesquisar(filtroBairro, Bairro.class.getName());
		
		Bairro bairro = (Bairro) Util.retonarObjetoDeColecao(colecaoBairros);
		
		String endereco = formatarEndereco(sistemaParametro, bairro);
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("nomeEmpresa", sistemaParametro.getNomeEmpresa());
		
		parametros.put("cnpjEmpresa", Util.formatarCnpj(sistemaParametro.getCnpjEmpresa()));
		
		parametros.put("enderecoEmpresa", endereco);
		
		parametros.put("telefoneEmpresa", sistemaParametro.getNumero0800Empresa());

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_AVISO_ANORMALIDADE,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_AVISO_ANORMALIDADE,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}
	
	private String formatarEndereco(SistemaParametro sistemaParametro, Bairro bairro) {
		String retorno = "";
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade("logradouroTipo");
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade("logradouroTitulo");
		
		filtroLogradouro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID, sistemaParametro.getLogradouro().getId()));
		
		Collection colecaoLogradouros = fachada.pesquisar(filtroLogradouro, Logradouro.class.getName());
		
		Logradouro logradouro = (Logradouro) Util.retonarObjetoDeColecao(colecaoLogradouros);
		
		retorno = logradouro.getDescricaoFormatada();
		
		if (sistemaParametro.getNumeroImovel() != null) {
			retorno = retorno + ", " + sistemaParametro.getNumeroImovel(); 
		}
		
		retorno = retorno + " - " + bairro.getNome();
		
		FiltroCep filtroCep = new FiltroCep();
		filtroCep.adicionarParametro(new ParametroSimples(FiltroCep.CEPID, sistemaParametro.getCep().getCepId()));
		
		Collection colecaoCeps = fachada.pesquisar(filtroCep, Cep.class.getName());
		
		Cep cep = (Cep) Util.retonarObjetoDeColecao(colecaoCeps);
		
		retorno = retorno + " - CEP: " + cep.getCepFormatado();
		
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;
		
		Fachada fachada = Fachada.getInstancia();
		
		Collection<Imovel> colecaoImoveisGerarAviso = (Collection<Imovel>) getParametro("colecaoImoveisGerarAviso");
		
		FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql = (FiltroMedicaoHistoricoSql) getParametro("filtroMedicaoHistoricoSql");
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) getParametro("faturamentoGrupo");
		Integer mesAnoPesquisa = (Integer) getParametro("anoMesPesquisa");
		String valorAguaEsgotoInicial = (String) getParametro("valorAguaEsgotoInicial");
		String valorAguaEsgotoFinal = (String) getParametro("valorAguaEsgotoFinal");
		
		if (colecaoImoveisGerarAviso != null
				&& !colecaoImoveisGerarAviso.isEmpty()) {
			retorno = colecaoImoveisGerarAviso.size();
		} else {
			retorno = fachada.filtrarExcecoesLeiturasConsumosCount(
					faturamentoGrupo, filtroMedicaoHistoricoSql,
					mesAnoPesquisa.toString(), valorAguaEsgotoInicial, valorAguaEsgotoFinal);
		}

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAvisoAnormalidade", this);

	}

}
