package gcom.relatorio.cadastro.imovel;

import gcom.batch.Relatorio;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
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
 * Classe responsável por criar o relatório de imoveis com consumo medido
 * 
 * @author Bruno Barros
 * @created 17/12/2007
 */
public class RelatorioImoveisConsumoMedio extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioImoveisConsumoMedio(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_CONSUMO_MEDIO);
	}

	@Deprecated
	public RelatorioImoveisConsumoMedio() {
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

		FiltrarRelatorioImoveisConsumoMedioHelper filtro = (FiltrarRelatorioImoveisConsumoMedioHelper) getParametro("filtrarRelatorioImoveisConsumoMedioHelper");

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioImoveisConsumoMedioBean relatorioImoveisConsumoMedioBean = null;

		Collection<RelatorioImoveisConsumoMedioHelper> colecao = fachada
				.pesquisarRelatorioImoveisConsumoMedio(filtro);

		// se a coleção de parâmetros da analise não for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {

				RelatorioImoveisConsumoMedioHelper helper = (RelatorioImoveisConsumoMedioHelper) colecaoIterator
						.next();

				relatorioImoveisConsumoMedioBean = new RelatorioImoveisConsumoMedioBean(
						helper);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioImoveisConsumoMedioBean);
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise

		// verifica os parêmtros informados
		String gerenciaRegional = "";
		if (filtro.getGerenciaRegional() != null
				&& !filtro.getGerenciaRegional().toString().equals("")
				&& !filtro.getGerenciaRegional().toString().equals(
						ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.ID, filtro.getGerenciaRegional()));

			Collection colecaoGerencia = fachada.pesquisar(
					filtroGerenciaRegional, GerenciaRegional.class.getName());

			GerenciaRegional gerencia = (GerenciaRegional) Util
					.retonarObjetoDeColecao(colecaoGerencia);
			gerenciaRegional = gerencia.getNomeAbreviado() + " - "
					+ gerencia.getNome();
		}

		String unidadeNegocio = "";
		if (filtro.getUnidadeNegocio() != null
				&& !filtro.getUnidadeNegocio().toString().equals("")
				&& !filtro.getUnidadeNegocio().toString().equals(
						ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.ID, filtro.getUnidadeNegocio()));

			Collection colecaoUnidade = fachada.pesquisar(filtroUnidadeNegocio,
					UnidadeNegocio.class.getName());

			UnidadeNegocio unidade = (UnidadeNegocio) Util
					.retonarObjetoDeColecao(colecaoUnidade);
			unidadeNegocio = unidade.getNome();
		}

		String localidadeInicial = "";
		String localidadeFinal = "";
		if (filtro.getLocalidadeInicial() != null
				&& !filtro.getLocalidadeInicial().toString().equals("")) {
			FiltroLocalidade filtroLocalidadeInicial = new FiltroLocalidade();
			filtroLocalidadeInicial.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, filtro.getLocalidadeInicial()));

			Collection colecaoLocalidadeInicial = fachada.pesquisar(
					filtroLocalidadeInicial, Localidade.class.getName());

			Localidade localidadeInicialEncontrada = (Localidade) Util
					.retonarObjetoDeColecao(colecaoLocalidadeInicial);
			localidadeInicial = localidadeInicialEncontrada.getId() + " - "
					+ localidadeInicialEncontrada.getDescricao();

			if (filtro.getLocalidadeFinal() != null
					&& !filtro.getLocalidadeFinal().toString().equals("")) {
				FiltroLocalidade filtroLocalidadeFinal = new FiltroLocalidade();
				filtroLocalidadeFinal.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, filtro.getLocalidadeFinal()));

				Collection colecaoLocalidadeFinal = fachada.pesquisar(
						filtroLocalidadeFinal, Localidade.class.getName());

				Localidade localidadeFinalEncontrada = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidadeFinal);
				localidadeFinal = localidadeFinalEncontrada.getId() + " - "
						+ localidadeFinalEncontrada.getDescricao();
			} else {
				localidadeFinal = localidadeInicial;
			}
		}

		String setorComercialInicial = "";
		String setorComercialFinal = "";
		if (filtro.getSetorComercialInicial() != null
				&& !filtro.getSetorComercialInicial().toString().equals("")) {

			FiltroSetorComercial filtroSetorInicial = new FiltroSetorComercial();
			filtroSetorInicial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.LOCALIDADE_ID, filtro
							.getLocalidadeInicial()));
			filtroSetorInicial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, filtro
							.getSetorComercialInicial()));

			Collection colecaoSetorInicial = fachada.pesquisar(
					filtroSetorInicial, SetorComercial.class.getName());

			SetorComercial setorInicialEncontrado = (SetorComercial) Util
					.retonarObjetoDeColecao(colecaoSetorInicial);
			setorComercialInicial = setorInicialEncontrado.getCodigo() + " - "
					+ setorInicialEncontrado.getDescricao();

			if (filtro.getSetorComercialFinal() != null
					&& !filtro.getSetorComercialFinal().toString().equals("")) {
				FiltroSetorComercial filtroSetorFinal = new FiltroSetorComercial();
				filtroSetorFinal.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.LOCALIDADE_ID, filtro
								.getLocalidadeFinal()));
				filtroSetorFinal.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, filtro
								.getSetorComercialFinal()));

				Collection colecaoSetorFinal = fachada.pesquisar(
						filtroSetorFinal, SetorComercial.class.getName());

				SetorComercial setorFinalEncontrado = (SetorComercial) Util
						.retonarObjetoDeColecao(colecaoSetorFinal);
				setorComercialFinal = setorFinalEncontrado.getCodigo() + " - "
						+ setorFinalEncontrado.getDescricao();
			} else {
				setorComercialFinal = setorComercialInicial;
			}
		}

		String consumoMedioInicial = "";
		String consumoMedioFinal = "";
		if (filtro.getConsumoMedioAguaInicial() != null
				&& !filtro.getConsumoMedioAguaInicial().toString().equals("")) {
			consumoMedioInicial = filtro.getConsumoMedioAguaInicial()
					.toString();

			if (filtro.getConsumoMedioAguaFinal() != null
					&& !filtro.getConsumoMedioAguaFinal().toString().equals("")) {
				consumoMedioFinal = filtro.getConsumoMedioAguaFinal()
						.toString();
			} else {
				consumoMedioFinal = consumoMedioInicial;
			}
		}

		String perfisImovel = "";
		if (filtro.getColecaoPerfisImovel() != null
				&& filtro.getColecaoPerfisImovel().size() > 0) {

			Iterator perfis = filtro.getColecaoPerfisImovel().iterator();
			Collection collectionImovelPerfil = null;
			
			while (perfis.hasNext()) {
				FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
				filtroImovelPerfil.adicionarParametro(new ParametroSimples(
						FiltroImovelPerfil.ID, perfis.next()));
				collectionImovelPerfil = fachada.pesquisar(filtroImovelPerfil,
						ImovelPerfil.class.getName());

				perfisImovel += ((ImovelPerfil) collectionImovelPerfil
						.iterator().next()).getDescricao();

				if (perfis.hasNext()) {
					perfisImovel += ", ";
				}
			}
		}
		
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("gerenciaRegional", gerenciaRegional);
		parametros.put("unidadeNegocio", unidadeNegocio);
		parametros.put("localidadeInicial", localidadeInicial);
		parametros.put("localidadeFinal", localidadeFinal);
		parametros.put("setorComercialInicial", setorComercialInicial);
		parametros.put("setorComercialFinal", setorComercialFinal);
		parametros.put("consumoMedioInicial", consumoMedioInicial);
		parametros.put("consumoMedioFinal", consumoMedioFinal);
		parametros.put("perfisImovel", perfisImovel);
		parametros.put("mesAnoReferencia", Util.formatarAnoMesParaMesAno(filtro.getAnoMesReferencia()));
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_IMOVEIS_CONSUMO_MEDIO,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.IMOVEIS_CONSUMO_MEDIO, idFuncionalidadeIniciada);
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

		FiltrarRelatorioImoveisConsumoMedioHelper filtro = (FiltrarRelatorioImoveisConsumoMedioHelper) getParametro("filtrarRelatorioImoveisConsumoMedioHelper");

		// verifica se contem algum imovel para gerar o relatorio.
		retorno = Fachada.getInstancia()
				.pesquisarTotalRegistroRelatorioImoveisConsumoMedio(filtro);

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioImoveisConsumoMedio", this);

	}

}
