package gcom.relatorio.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.GerarRelatorioAnormalidadeConsumoHelper;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de volumes faturados
 * 
 * @author Rafael Corrêa
 * @created 12/09/2007
 */
public class RelatorioAnormalidadeConsumo extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioAnormalidadeConsumo(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_ANORMALIDADE_CONSUMO);
	}

	@Deprecated
	public RelatorioAnormalidadeConsumo() {
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

		Integer idGrupo = (Integer) getParametro("idGrupo");
		Short cdRota = (Short) getParametro("cdRota");
		Integer idGerenciaRegional = (Integer) getParametro("idGerenciaRegional");
		Integer idUnidadeNegocio = (Integer) getParametro("idUnidadeNegocio");
		Integer idLocalidadeInicial = (Integer) getParametro("idLocalidadeInicial");
		Integer idLocalidadeFinal = (Integer) getParametro("idLocalidadeFinal");
		Integer idSetorComercialInicial = (Integer) getParametro("idSetorComercialInicial");
		Integer idSetorComercialFinal = (Integer) getParametro("idSetorComercialFinal");
		Collection<Integer> colecaoIdsEmpresa = (Collection<Integer>) getParametro("colecaoIdsEmpresa");
//		Collection<Integer> colecaoIdsAnormalidadeLeitura = (Collection<Integer>) getParametro("colecaoIdsAnormalidadeLeitura");
		Collection<Integer> colecaoIdsAnormalidadeLeituraInformada = (Collection<Integer>) getParametro("colecaoIdsAnormalidadeLeituraInformada");
//		Collection<Integer> colecaoIdsAnormalidadeConsumo = (Collection<Integer>) getParametro("colecaoIdsAnormalidadeConsumo");
		Integer numeroOcorrencias = (Integer) getParametro("numeroOcorrencias");
		String indicadorOcorrenciasIguais = (String) getParametro("ocorrenciasIguais");
		Integer idImovelPerfil = (Integer) getParametro("idImovelPerfil");
		Integer referencia = (Integer) getParametro("referencia");
		Integer mediaConsumoInicial = (Integer) getParametro("mediaConsumoInicial");
		Integer mediaConsumoFinal = (Integer) getParametro("mediaConsumoFinal");
		Integer tipoMedicao = (Integer) getParametro("tipoMedicao");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		Integer idCategoria = (Integer)getParametro("idCategoria");
		Integer numeroQuadraInicial = (Integer)getParametro("numeroQuadraInicial");
		Integer numeroQuadraFinal = (Integer)getParametro("numeroQuadraFinal");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioAnormalidadeConsumoBean relatorioBean = null;

		//CRC4561 - comentado por Vivianne Sousa - 07/06/2010 - analista:Adriana Ribeiro
//		Collection colecaoGerarRelatorioAnormalidadeConsumoHelper = fachada.pesquisarDadosRelatorioAnormalidadeConsumo(idGrupo, cdRota,
//				idGerenciaRegional, idUnidadeNegocio, idLocalidadeInicial, idLocalidadeFinal, 
//				idSetorComercialInicial, idSetorComercialFinal ,referencia,
//				idImovelPerfil, numeroOcorrencias, indicadorOcorrenciasIguais,
//				mediaConsumoInicial, mediaConsumoFinal, colecaoIdsAnormalidadeConsumo, 
//				colecaoIdsAnormalidadeLeitura, colecaoIdsAnormalidadeLeituraInformada, tipoMedicao, colecaoIdsEmpresa);
		
		Collection colecaoGerarRelatorioAnormalidadeConsumoHelper = fachada.pesquisarDadosRelatorioAnormalidadeConsumo(idGrupo, cdRota,
				idGerenciaRegional, idUnidadeNegocio, idLocalidadeInicial, idLocalidadeFinal, 
				idSetorComercialInicial, idSetorComercialFinal ,referencia,
				idImovelPerfil, numeroOcorrencias, indicadorOcorrenciasIguais,
				mediaConsumoInicial, mediaConsumoFinal, null, 
				null, colecaoIdsAnormalidadeLeituraInformada, tipoMedicao, colecaoIdsEmpresa,
				numeroQuadraInicial, numeroQuadraFinal, idCategoria);
		
/*		if (colecaoGerarRelatorioAnormalidadeConsumoHelper == null ||
			colecaoGerarRelatorioAnormalidadeConsumoHelper.isEmpty()) {
			throw new ActionServletException("atencao.relatorio.vazio");
		}*/

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoGerarRelatorioAnormalidadeConsumoHelper != null
				&& !colecaoGerarRelatorioAnormalidadeConsumoHelper.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoGerarRelatorioAnormalidadeConsumoHelperIterator = colecaoGerarRelatorioAnormalidadeConsumoHelper
					.iterator();
			
			Integer totalGrupo = new Integer("0");
			Integer totalGerenciaRegional = new Integer("0");
			Integer totalUnidadeNegocio = new Integer("0");
			Integer totalElo = new Integer("0");
			Integer totalLocalidade = new Integer("0");
			Integer totalSetorComercial = new Integer("0");
			
			Integer idGrupoAnterior = null;
			Integer idGerenciaRegionalAnterior = null;
			Integer idUnidadeNegocioAnterior = null;
			Integer idEloAnterior = null;
			Integer idLocalidadeAnterior = null;
			Integer idSetorComercialAnterior = null;
			
			boolean zerarGrupo = false;
			boolean zerarGerenciaRegional = false;
			boolean zerarUnidadeNegocio = false;
			boolean zerarElo = false;
			boolean zerarLocalidade = false;
			boolean zerarSetorComercial = false;

			// laço para criar a coleção de parâmetros da analise
			while (colecaoGerarRelatorioAnormalidadeConsumoHelperIterator.hasNext()) {

				GerarRelatorioAnormalidadeConsumoHelper gerarRelatorioAnormalidadeConsumoHelper = (GerarRelatorioAnormalidadeConsumoHelper) colecaoGerarRelatorioAnormalidadeConsumoHelperIterator
						.next();

				// Faz as validações dos campos necessáriose e formata a String
				// para a forma como irá aparecer no relatório

				// Grupo
				String grupo = "";

				if (gerarRelatorioAnormalidadeConsumoHelper.getIdGrupo() != null) {
					grupo = gerarRelatorioAnormalidadeConsumoHelper
							.getIdGrupo()
							+ " - "
							+ gerarRelatorioAnormalidadeConsumoHelper
									.getNomeGrupo();
					
					if (idGrupoAnterior == null) {
						idGrupoAnterior = gerarRelatorioAnormalidadeConsumoHelper.getIdGrupo();
					} else {
						if (!idGrupoAnterior.equals(gerarRelatorioAnormalidadeConsumoHelper.getIdGrupo())) {
							zerarGrupo = true;
						}
					}
				}
				
				// Gerência Regional
				String gerenciaRegional = "";

				if (gerarRelatorioAnormalidadeConsumoHelper.getIdGerenciaRegional() != null) {
					gerenciaRegional = gerarRelatorioAnormalidadeConsumoHelper
							.getIdGerenciaRegional()
							+ " - "
							+ gerarRelatorioAnormalidadeConsumoHelper
									.getNomeGerenciaRegional();
					
					if (idGerenciaRegionalAnterior == null) {
						idGerenciaRegionalAnterior = gerarRelatorioAnormalidadeConsumoHelper.getIdGerenciaRegional();
					} else {
						if (!idGerenciaRegionalAnterior.equals(gerarRelatorioAnormalidadeConsumoHelper.getIdGerenciaRegional())) {
							zerarGerenciaRegional = true;
						}
					}
				}
				
				// Unidade de Negócio
				String unidadeNegocio = "";

				if (gerarRelatorioAnormalidadeConsumoHelper.getIdUnidadeNegocio() != null) {
					unidadeNegocio = gerarRelatorioAnormalidadeConsumoHelper
							.getIdUnidadeNegocio()
							+ " - "
							+ gerarRelatorioAnormalidadeConsumoHelper
									.getNomeUnidadeNegocio();
					
					if (idUnidadeNegocioAnterior == null) {
						idUnidadeNegocioAnterior = gerarRelatorioAnormalidadeConsumoHelper.getIdUnidadeNegocio();
					} else {
						if (!idUnidadeNegocioAnterior.equals(gerarRelatorioAnormalidadeConsumoHelper.getIdUnidadeNegocio())) {
							zerarUnidadeNegocio = true;
						}
					}
				}
				
				// Elo
				String elo = "";

				if (gerarRelatorioAnormalidadeConsumoHelper.getIdElo() != null) {
					elo = gerarRelatorioAnormalidadeConsumoHelper
							.getIdElo()
							+ " - "
							+ gerarRelatorioAnormalidadeConsumoHelper
									.getNomeElo();
					
					if (idEloAnterior == null) {
						idEloAnterior = gerarRelatorioAnormalidadeConsumoHelper.getIdElo();
					} else {
						if (!idEloAnterior.equals(gerarRelatorioAnormalidadeConsumoHelper.getIdElo())) {
							zerarElo = true;
						}
					}
				}
				
				// Localidade
				String localidade = "";

				if (gerarRelatorioAnormalidadeConsumoHelper.getIdLocalidade() != null) {
					localidade = gerarRelatorioAnormalidadeConsumoHelper
							.getIdLocalidade()
							+ " - "
							+ gerarRelatorioAnormalidadeConsumoHelper
									.getNomeLocalidade();
					
					if (idLocalidadeAnterior == null) {
						idLocalidadeAnterior = gerarRelatorioAnormalidadeConsumoHelper.getIdLocalidade();
					} else {
						if (!idLocalidadeAnterior.equals(gerarRelatorioAnormalidadeConsumoHelper.getIdLocalidade())) {
							zerarLocalidade = true;
						}
					}
				}
				
				// Setor Comercial
				String setorComercial = "";
				String idSetorComercial = "";

				if (gerarRelatorioAnormalidadeConsumoHelper.getIdSetorComercial() != null) {
					setorComercial = gerarRelatorioAnormalidadeConsumoHelper
							.getCodigoSetorComercial().toString();
					idSetorComercial = gerarRelatorioAnormalidadeConsumoHelper
					.getIdSetorComercial().toString();
					
					if (idSetorComercialAnterior == null) {
						idSetorComercialAnterior = gerarRelatorioAnormalidadeConsumoHelper.getIdSetorComercial();
					} else {
						if (!idSetorComercialAnterior.equals(gerarRelatorioAnormalidadeConsumoHelper.getIdSetorComercial())) {
							zerarSetorComercial = true;
						}
					}
				}
				
				// Caso tenha mudado de Grupo zera seus totalizadores
				if (zerarGrupo) {
					totalGrupo = new Integer("0");
					idGrupoAnterior = gerarRelatorioAnormalidadeConsumoHelper.getIdGrupo();
					zerarGrupo = false;
				}

				// Caso tenha mudado de Gerência Regional zera seus totalizadores
				if (zerarGerenciaRegional) {
					totalGerenciaRegional = new Integer("0");
					idGerenciaRegionalAnterior = gerarRelatorioAnormalidadeConsumoHelper.getIdGerenciaRegional();
					zerarGerenciaRegional = false;
				}

				// Caso tenha mudado de Unidade de Negócio zera seus totalizadores
				if (zerarUnidadeNegocio) {
					totalUnidadeNegocio = new Integer("0");
					idUnidadeNegocioAnterior = gerarRelatorioAnormalidadeConsumoHelper.getIdUnidadeNegocio();
					zerarUnidadeNegocio = false;
				}
				
				// Caso tenha mudado de Elo zera seus totalizadores
				if (zerarElo) {
					totalElo = new Integer("0");
					idEloAnterior = gerarRelatorioAnormalidadeConsumoHelper.getIdElo();
					zerarElo = false;
				}
				
				// Caso tenha mudado de Localidade zera seus totalizadores
				if (zerarLocalidade) {
					totalLocalidade = new Integer("0");
					idLocalidadeAnterior = gerarRelatorioAnormalidadeConsumoHelper.getIdLocalidade();
					zerarLocalidade = false;
				}
				
				// Caso tenha mudado de setor comercial zera seus totalizadores
				if (zerarSetorComercial) {
					totalSetorComercial = new Integer("0");
					idSetorComercialAnterior = gerarRelatorioAnormalidadeConsumoHelper.getIdSetorComercial();
					zerarSetorComercial = false;
				}
				
				totalGrupo = totalGrupo + 1;
				totalGerenciaRegional = totalGerenciaRegional + 1;
				totalUnidadeNegocio = totalUnidadeNegocio + 1;
				totalElo = totalElo + 1;
				totalLocalidade = totalLocalidade + 1;
				totalSetorComercial = totalSetorComercial + 1;

				// Imóvel, Endereço e Categoria
				String idImovel = "";
				String endereco = "";
				String descricaoCategoria = "";

				if (gerarRelatorioAnormalidadeConsumoHelper.getIdImovel() != null) {
					idImovel = gerarRelatorioAnormalidadeConsumoHelper.getIdImovel()
							.toString();
					Imovel imovel = new Imovel();
					imovel.setId(gerarRelatorioAnormalidadeConsumoHelper.getIdImovel());
//					endereco = fachada.pesquisarEndereco(gerarRelatorioAnormalidadeConsumoHelper.getIdImovel());
					Categoria categoria = fachada.obterDescricoesCategoriaImovel(imovel);
					
					if (categoria.getDescricaoAbreviada() != null) {
						descricaoCategoria = categoria.getDescricaoAbreviada();
					}
				
				}
				
				if(gerarRelatorioAnormalidadeConsumoHelper.getEnderecoImovel() != null){
					endereco = gerarRelatorioAnormalidadeConsumoHelper.getEnderecoImovel() ;
				}

				// Nome do Usuário
				String nomeUsuario = "";

				if (gerarRelatorioAnormalidadeConsumoHelper.getNomeUsuario() != null) {
					nomeUsuario = gerarRelatorioAnormalidadeConsumoHelper
							.getNomeUsuario();
				}
				
				// Situação da Ligação de Água
				String situacaoAgua = "";

				if (gerarRelatorioAnormalidadeConsumoHelper.getSituacaoLigacaoAgua() != null) {
					situacaoAgua = gerarRelatorioAnormalidadeConsumoHelper
							.getSituacaoLigacaoAgua().toString();
				}

				// Situação de Esgoto
				String situacaoEsgoto = "";

				if (gerarRelatorioAnormalidadeConsumoHelper.getSituacaoLigacaoEsgoto() != null) {
					situacaoEsgoto = gerarRelatorioAnormalidadeConsumoHelper
							.getSituacaoLigacaoEsgoto().toString();
				}
				
				// Débito Automático
				String debitoAutomatico = "";
				
				if (gerarRelatorioAnormalidadeConsumoHelper.getIndicadorDebito() != null) {
					if (gerarRelatorioAnormalidadeConsumoHelper.getIndicadorDebito().equals(ConstantesSistema.SIM)) {
						debitoAutomatico = "SIM";
					} else {
						debitoAutomatico = "NÃO";
					}
				}
				
				// Média de Consumo
				String media = "";

				if (gerarRelatorioAnormalidadeConsumoHelper.getConsumoMedio() != null) {
					media = gerarRelatorioAnormalidadeConsumoHelper.getConsumoMedio()
							.toString();
				}

				// Consumo
				String consumo = "";

				if (gerarRelatorioAnormalidadeConsumoHelper.getConsumoMes() != null) {
					consumo = gerarRelatorioAnormalidadeConsumoHelper
							.getConsumoMes().toString();
				}

				// Anormalidade de Consumo
				String anormalidadeConsumo = "";
				
				if (gerarRelatorioAnormalidadeConsumoHelper.getDescricaoAbrevConsumoAnormalidade() != null) {
					anormalidadeConsumo = gerarRelatorioAnormalidadeConsumoHelper.getDescricaoAbrevConsumoAnormalidade();
				}
				
				// Anormalidade de Leitura
				String anormalidadeLeitura = "";
				
				if (gerarRelatorioAnormalidadeConsumoHelper.getIdLeituraAnormalidade() != null) {
					anormalidadeLeitura = gerarRelatorioAnormalidadeConsumoHelper.getIdLeituraAnormalidade().toString();
				}
				
				String nnLeituraAtualInformada = "";
				if (gerarRelatorioAnormalidadeConsumoHelper.getNnLeituraAtualInformada() != null) {
					nnLeituraAtualInformada = gerarRelatorioAnormalidadeConsumoHelper.getNnLeituraAtualInformada().toString();
				}
				
				// Quantidade de Economias
				String qtdeEconomias = "";
				
				if (gerarRelatorioAnormalidadeConsumoHelper.getQuantidadeEconomias() != null) {
					qtdeEconomias = gerarRelatorioAnormalidadeConsumoHelper.getQuantidadeEconomias().toString();
				}
				
				// Capacidade do Hidrômetro
				String capacidadeHidrometro = "";
				
				if (gerarRelatorioAnormalidadeConsumoHelper.getCapacidadeHidrometro() != null) {
					capacidadeHidrometro = gerarRelatorioAnormalidadeConsumoHelper.getCapacidadeHidrometro();
				}
				
				// Local de Instalação do Hidrômetro
				String localInstalacaoHidrometro = "";
				
				if (gerarRelatorioAnormalidadeConsumoHelper.getLocalInstalacaoHidrometro() != null) {
					localInstalacaoHidrometro = gerarRelatorioAnormalidadeConsumoHelper.getLocalInstalacaoHidrometro();
				}
				
				// Setor Comercial
				String idEmpresa = "";
				String nomeEmpresa = "";

				if (gerarRelatorioAnormalidadeConsumoHelper.getIdEmpresa() != null) {
				    idEmpresa = gerarRelatorioAnormalidadeConsumoHelper.getIdEmpresa().toString();
					nomeEmpresa = idEmpresa + " - " +gerarRelatorioAnormalidadeConsumoHelper.getNomeEmpresa();
				}

				String inscricaoImovel = gerarRelatorioAnormalidadeConsumoHelper.getInscricaoImovel();
				if(gerarRelatorioAnormalidadeConsumoHelper.getInscricaoImovel() != null){
					inscricaoImovel = gerarRelatorioAnormalidadeConsumoHelper.getInscricaoImovel();
				}
				
				relatorioBean = new RelatorioAnormalidadeConsumoBean(
						
						// Grupo
						grupo,
						
						// Gerência Regional
						gerenciaRegional,
						
						// Unidade de Negócio
						unidadeNegocio,
						
						// Elo
						elo,

						// Localidade
						localidade,
						
						// Id do Setor Comercial
						idSetorComercial,
						
						// Setor Comercial
						setorComercial,

						// Imóvel
						idImovel,
						
						// Usuário
						nomeUsuario,
						
						// Endereço
						endereco,
						
						// Situação de Água
						situacaoAgua,
						
						// Situação de Esgoto
						situacaoEsgoto,
						
						// Débito Automático
						debitoAutomatico,
						
						// Média de Consumo
						media,
						
						// Consumo
						consumo,
						
						// Anormalidade de Consumo
						anormalidadeConsumo,
						
						// Anormalidade de Leitura
						anormalidadeLeitura,
						
						// Número leitura atual informada 
						nnLeituraAtualInformada,
						
						// Categoria
						descricaoCategoria,
						
						// Quantidade de Economias
						qtdeEconomias,
						
						// Tipo de Medição
						gerarRelatorioAnormalidadeConsumoHelper.getTipoMedicao(),
						
						// Capacidade do Hidrômetro
						capacidadeHidrometro,
						
						// Local de Instalação do Hidrômetro
						localInstalacaoHidrometro,
				
						// Total do Grupo
						totalGrupo.toString(),
						
						// Total da Gerência Regional
						totalGerenciaRegional.toString(),
						
						// Total da Unidade de Negócio
						totalUnidadeNegocio.toString(),
						
						// Total do Elo
						totalElo.toString(),
						
						// Total da Localidade
						totalLocalidade.toString(),
						
						// Total do Setor Comercial
						totalSetorComercial.toString(),
						
						//Id Empresa
						idEmpresa,
						
						//Nome Empresa
						nomeEmpresa,
						
						inscricaoImovel
						
				);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);

			}
		} else {
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("mesAno", Util.formatarAnoMesParaMesAno(referencia));
		
		parametros.put("tipoFormatoRelatorio", "R0638");

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_ANORMALIDADE_CONSUMO,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.ANORMALIDADE_CONSUMO,
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
		int retorno = 1;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAnormalidadeConsumo",
				this);
	}
}
