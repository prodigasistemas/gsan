package gcom.relatorio.micromedicao;

import gcom.batch.Relatorio;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.bean.AnormalidadeLeituraHelper;
import gcom.micromedicao.bean.ComparativoLeiturasEAnormalidadesRelatorioHelper;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
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
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório do comparativo de leituras e anormalidades
 * 
 * @author Rafael Corrêa     - Hugo Leonardo
 * @created 12/04/2007		 - 18/03/2010
 */
public class RelatorioComparativoLeiturasEAnormalidades extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioComparativoLeiturasEAnormalidades(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_COMPARATIVOS_LEITURAS_E_ANORMALIDADES);
	}

	@Deprecated
	public RelatorioComparativoLeiturasEAnormalidades() {
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

		Integer idGrupoFaturamento = (Integer) getParametro("idGrupoFaturamento");
		Integer idEmpresa = (Integer) getParametro("idEmpresa");
		Integer anoMes = (Integer) getParametro("anoMes");
		
		Integer idLocalidadeInicial = (Integer) getParametro("idLocalidadeInicial");
		Integer idLocalidadeFinal = (Integer) getParametro("idLocalidadeFinal");
		Integer idSetorInicial = (Integer) getParametro("idSetorInicial");
		Integer idSetorFinal = (Integer) getParametro("idSetorFinal");
		
		Integer idGerencia = (Integer) getParametro("idGerencia");
		Integer idUnidadeNegocio = (Integer) getParametro("idUnidadeNegocio");
		
		Integer idLeiturista = (Integer) getParametro("idLeiturista");
		
		Integer idRotaInicial = (Integer) getParametro("idRotaInicial");
		Integer idRotaFinal = (Integer) getParametro("idRotaFinal");
		
		Integer idPerfilImovel = (Integer) getParametro("idPerfilImovel");
		Integer numOcorrenciasConsecutivas = (Integer) getParametro("numOcorrenciasConsecutivas");
		Collection<Integer> colecaoAnormalidadesLeituras = (Collection) getParametro("colecaoAnormalidadesLeituras");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioComparativoLeiturasEAnormalidadesBean relatorioBean = null;
		
		Collection colecaoDadosRelatorioComparativoLeiturasEAnormalidades = fachada
				.pesquisarDadosRelatorioComparativoLeiturasEAnormalidades(
						idGrupoFaturamento, idEmpresa, anoMes, 						
						idLocalidadeInicial, idLocalidadeFinal,
						idSetorInicial, idSetorFinal,
						idGerencia, idUnidadeNegocio, idLeiturista, idRotaInicial, idRotaFinal, 
						idPerfilImovel, numOcorrenciasConsecutivas, colecaoAnormalidadesLeituras);

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoDadosRelatorioComparativoLeiturasEAnormalidades != null && !colecaoDadosRelatorioComparativoLeiturasEAnormalidades.isEmpty()) {
			
			String empresa = "";
			String grupoFaturamento = "";
			
			if (idEmpresa != null) {
				FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
				filtroEmpresa.adicionarParametro(new ParametroSimples(
								FiltroEmpresa.ID, idEmpresa));

				Collection colecaoPesquisa = fachada.pesquisar(
								filtroEmpresa, Empresa.class.getName());

				Empresa emp = (Empresa) Util.retonarObjetoDeColecao(colecaoPesquisa);
				
				empresa = emp.getId()+ "-" + emp.getDescricao();
			}
			
			if(idGrupoFaturamento != null){
				FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
				filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
						FiltroFaturamentoGrupo.ID, idGrupoFaturamento));

				Collection colecaoPesquisa = fachada.pesquisar(
						filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

				FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) Util.retonarObjetoDeColecao(colecaoPesquisa);
				
			    grupoFaturamento = faturamentoGrupo.getDescricao();
			}

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoDadosRelatorioComparativoLeiturasEAnormalidadesIterator = colecaoDadosRelatorioComparativoLeiturasEAnormalidades.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoDadosRelatorioComparativoLeiturasEAnormalidadesIterator.hasNext()) {

				ComparativoLeiturasEAnormalidadesRelatorioHelper comparativoLeiturasEAnormalidadesRelatorioHelper = (ComparativoLeiturasEAnormalidadesRelatorioHelper) colecaoDadosRelatorioComparativoLeiturasEAnormalidadesIterator
						.next();
				
				relatorioBean = new RelatorioComparativoLeiturasEAnormalidadesBean(
								// Grupo de Faturamento
								grupoFaturamento,
								
								// Empresa
								empresa, 
								
								// Id do Setor Comercial
								comparativoLeiturasEAnormalidadesRelatorioHelper.getCodigoSetorComercial().toString(),
								
								// Id da Localidade
								comparativoLeiturasEAnormalidadesRelatorioHelper.getIdLocalidade().toString(),
								
								// Código do Setor Comercial
								comparativoLeiturasEAnormalidadesRelatorioHelper.getCodigoSetorComercial().toString(),
								
								// Registros Recebidos
								comparativoLeiturasEAnormalidadesRelatorioHelper.getRegistrosRecebidos().toString(),
								
								// Registros c/ Leitura
								comparativoLeiturasEAnormalidadesRelatorioHelper.getRegistrosComLeitura().toString(),
								
								// Registros c/ Anormalidade
								comparativoLeiturasEAnormalidadesRelatorioHelper.getRegistrosComAnormalidade().toString(),
								
								// Registros c/ Leitura e Anormalidade
								comparativoLeiturasEAnormalidadesRelatorioHelper.getRegistrosComLeituraEAnormalidade().toString(),
								
								// Anormalidade de Leitura
								null,
								
								// Quantidade de Anormalidades de Leitura
								null,
								
								//Quantidade de Leituras Enviadas
								comparativoLeiturasEAnormalidadesRelatorioHelper.getRegistrosEnviados().toString(),
								
								//rota
								comparativoLeiturasEAnormalidadesRelatorioHelper.getCodigoRota().toString(),
								
								// Registros Recebidos Convencional
								comparativoLeiturasEAnormalidadesRelatorioHelper.getRegistrosRecebidosConvencional().toString(),
								
								// Registros Recebidos Simultaneo
								comparativoLeiturasEAnormalidadesRelatorioHelper.getRegistrosRecebidosSimultaneo().toString()
				);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}
		
		Collection colecaoAnormalidadesLeiturasRelatorio = fachada
				.pesquisarAnormalidadesRelatorioComparativoLeiturasEAnormalidades(
						idGrupoFaturamento, idEmpresa, anoMes,
						idLocalidadeInicial, idLocalidadeFinal,
						idSetorInicial, idSetorFinal,
						idGerencia, idUnidadeNegocio, idLeiturista, idRotaInicial, idRotaFinal,
						idPerfilImovel, numOcorrenciasConsecutivas, colecaoAnormalidadesLeituras);

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoAnormalidadesLeiturasRelatorio != null
				&& !colecaoAnormalidadesLeiturasRelatorio
						.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoAnormalidadesLeiturasRelatorioIterator = colecaoAnormalidadesLeiturasRelatorio
					.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoAnormalidadesLeiturasRelatorioIterator
					.hasNext()) {

				AnormalidadeLeituraHelper anormalidadeLeituraHelper = (AnormalidadeLeituraHelper) colecaoAnormalidadesLeiturasRelatorioIterator
						.next();

				String anormalidadeLeitura = "";
				if (anormalidadeLeituraHelper
						.getIdAnormalidadeLeitura() != null) {
					anormalidadeLeitura = anormalidadeLeituraHelper
							.getIdAnormalidadeLeitura()
							+ " - "
							+ anormalidadeLeituraHelper
									.getDescricaoAnormalidadeLeitura();
				}
				
				String empresa = "";
				
				if (idEmpresa != null) {
					empresa = anormalidadeLeituraHelper.getIdEmpresa() + " - " + anormalidadeLeituraHelper.getNomeEmpresa();
				}
				
				String grupoFaturamento = null;
				
				if(idGrupoFaturamento != null){
					 grupoFaturamento = anormalidadeLeituraHelper
							.getIdGrupoFaturamento().toString();
				}
				

				relatorioBean = new RelatorioComparativoLeiturasEAnormalidadesBean(

						// Grupo de Faturamento
						grupoFaturamento,

						// Empresa
						empresa,

						// Id do Setor Comercial
						null,

						// Id da Localidade
						null,

						// Código do Setor Comercial
						null,

						// Registros Recebidos
						null,

						// Registros c/ Leitura
						null,

						// Registros c/ Anormalidade
						null,

						// Registros c/ Leitura e Anormalidade
						null,

						// Anormalidade de Leitura
						anormalidadeLeitura,

						// Quantidade de Anormalidades de Leitura
						anormalidadeLeituraHelper.getQuantidade().toString(),
						
						// Quantidade de Leituras Enviadas
						null,
						
						//Rota
						null,
						
						// Registros Recebidos Convencional
						null,
						
						// Registros Recebidos Simultaneo
						null
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
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("mesAno", Util.formatarAnoMesParaMesAno(anoMes));
		
		parametros.put("tipoFormatoRelatorio", "R0939");
		
		//Localidade Inicial
		if ( idLocalidadeInicial != null && !idLocalidadeInicial.equals("") ) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro( new ParametroSimples( FiltroLocalidade.ID, idLocalidadeInicial));
			
			Collection colecaoLocalidadeInicial = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			Localidade localidadeInicial = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidadeInicial);
			
			parametros.put("localidadeInicialDescricao", localidadeInicial.getDescricao() );
		}
		
		//Localidade Final
		if ( idLocalidadeFinal != null && !idLocalidadeFinal.equals("") ) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro( new ParametroSimples( FiltroLocalidade.ID, idLocalidadeFinal));
			
			Collection colecaoLocalidadeFinal = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			Localidade localidadeFinal = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidadeFinal);
			
			parametros.put("localidadeFinalDescricao", localidadeFinal.getDescricao() );
		}
		
		//Setor Comercial Inicial
		if ( idSetorInicial != null && !idSetorInicial.equals("") ) {
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro( new ParametroSimples( FiltroSetorComercial.ID, idSetorInicial));
			
			Collection colecaoSetor = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetor);
			
			parametros.put("setorComercialInicialDescricao", setorComercial.getDescricao());
		}
		
		//Setor Comercial Final
		if ( idSetorFinal != null && !idSetorFinal.equals("") ) {
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro( new ParametroSimples( FiltroSetorComercial.ID, idSetorFinal));
			
			Collection colecaoSetor = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetor);
			
			parametros.put("setorComercialFinalDescricao", setorComercial.getDescricao());
		}
		
		//Unidade Negocio
		if ( idUnidadeNegocio != null && !idUnidadeNegocio.equals("") ) {
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro( new ParametroSimples( FiltroUnidadeNegocio.ID, idUnidadeNegocio));
			
			Collection colecaoUnidade = fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			UnidadeNegocio unidadeNegocio = (UnidadeNegocio) Util.retonarObjetoDeColecao(colecaoUnidade);
			
			parametros.put("unidadeNegocioDescricao", unidadeNegocio.getNome());
		}
		
		//Gerencia Regional
		if ( idGerencia != null && !idGerencia.equals("") ) {
			FiltroGerenciaRegional filtroGerencia = new FiltroGerenciaRegional();
			filtroGerencia.adicionarParametro( new ParametroSimples( FiltroGerenciaRegional.ID, idGerencia));
			
			Collection colecaoGerencia = fachada.pesquisar(filtroGerencia, GerenciaRegional.class.getName());
			GerenciaRegional gerenciaRegional = (GerenciaRegional) Util.retonarObjetoDeColecao(colecaoGerencia);
			
			parametros.put("gerenciaRegionalDescricao", gerenciaRegional.getNome());
		}
		
		// Leiturista
		if(idLeiturista != null && !idLeiturista.equals("") && !idLeiturista.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
			filtroLeiturista.adicionarParametro( new ParametroSimples( FiltroLeiturista.ID, idLeiturista));
			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.INDICADOR_USO, ConstantesSistema.SIM));
			filtroLeiturista
					.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			filtroLeiturista
					.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);		
			
			Collection colecaoLeiturista = fachada.pesquisar(filtroLeiturista, Leiturista.class.getName());
			Leiturista leiturista = (Leiturista) Util.retonarObjetoDeColecao(colecaoLeiturista);
			
			if ( leiturista.getFuncionario() != null ){
				parametros.put("leituristaDescicao", leiturista.getFuncionario().getNome() );
			} else {			
				parametros.put("leituristaDescicao", leiturista.getCliente().getNome() );
			}
			
		}
		
		// Rota Inicial
		if(idRotaInicial != null && !idRotaInicial.equals("")){
			parametros.put("rotaInicialDescricao", idRotaInicial.toString());
		}
		
		// Rota Final
		if(idRotaFinal != null && !idRotaFinal.equals("")){
			parametros.put("rotaFinalDescricao", idRotaFinal.toString());
		}
		
		// Perfil do Imóvel
		if(Util.verificarIdNaoVazio(String.valueOf(idPerfilImovel))){
			parametros.put("perfilImovel", fachada.pesquisarImovelPerfil(idPerfilImovel).getDescricao());
		}
		
		// Anormalidade de Leitura
		if(colecaoAnormalidadesLeituras != null && !colecaoAnormalidadesLeituras.isEmpty()){
			
			if(colecaoAnormalidadesLeituras.size() >= 1){
				
				String anormalidades = "";
				Iterator iter = colecaoAnormalidadesLeituras.iterator();
				int contador = 0;
				while (iter.hasNext()) {
					String idAnormladidade = (String) iter.next();
					
					if(!idAnormladidade.equals("-1")){
						
						FiltroLeituraAnormalidade filtro = new FiltroLeituraAnormalidade();
						filtro.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.ID, new Integer(idAnormladidade)));
						
						anormalidades += ((LeituraAnormalidade) Util.retonarObjetoDeColecao(fachada.pesquisar(filtro, LeituraAnormalidade.class.getName()))).getDescricao();
						
						if (iter.hasNext()) {
							anormalidades += ", ";
						}
						
						contador++;
						
						if (contador == 3) { 
							anormalidades += "etc";
							break;
						}
						
					}
				}
				if(anormalidades != null && !anormalidades.equals("")){
					parametros.put("anormalidadesLeituras", anormalidades);	
				}
			}
		}
		
		if(Util.verificarIdNaoVazio(String.valueOf(numOcorrenciasConsecutivas))){
			parametros.put("numOcorrenciasConsecutivas", numOcorrenciasConsecutivas);
		}

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_COMPARATIVOS_LEITURAS_E_ANORMALIDADES,
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
		
		/*
		Fachada fachada = Fachada.getInstancia();
		
		Integer idGrupoFaturamento = (Integer) getParametro("idGrupoFaturamento");
		Integer idEmpresa = (Integer) getParametro("idEmpresa");
		Integer anoMes = (Integer) getParametro("anoMes");
		
		Integer idLocalidadeInicial = (Integer) getParametro("idLocalidadeInicial");
		Integer idLocalidadeFinal = (Integer) getParametro("idLocalidadeFinal");
		Integer idSetorInicial = (Integer) getParametro("idSetorInicial");
		Integer idSetorFinal = (Integer) getParametro("idSetorFinal");
		
		Integer idGerencia = (Integer) getParametro("idGerencia");
		Integer idUnidadeNegocio = (Integer) getParametro("idUnidadeNegocio");
		
		Integer idLeiturista = (Integer) getParametro("idLeiturista");
		
		Integer idRotaInicial = (Integer) getParametro("idRotaInicial");
		Integer idRotaFinal = (Integer) getParametro("idRotaFinal");
		
		Integer idPerfilImovel = (Integer) getParametro("idPerfilImovel");
		Integer numOcorrenciasConsecutivas = (Integer) getParametro("numOcorrenciasConsecutivas");
		Collection<Integer> colecaoAnormalidadesLeituras = (Collection) getParametro("colecaoAnormalidadesLeituras"); 
		
		Integer colecaoDadosRelatorioComparativoLeiturasEAnormalidades = fachada
				.pesquisarDadosRelatorioComparativoLeiturasEAnormalidadesCount(
						idGrupoFaturamento, idEmpresa, anoMes, 						
						idLocalidadeInicial, idLocalidadeFinal,
						idSetorInicial, idSetorFinal,
						idGerencia, idUnidadeNegocio, idLeiturista, idRotaInicial, idRotaFinal,
						idPerfilImovel, numOcorrenciasConsecutivas, colecaoAnormalidadesLeituras);
		if (colecaoDadosRelatorioComparativoLeiturasEAnormalidades > 0) {
		
			int retorno = 10;
			
			return retorno;
		
		} else {
			
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		*/
		return 10;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioComparativoLeiturasEAnormalidades", this);

	}

}
