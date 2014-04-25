package gcom.relatorio.cobranca;

import gcom.batch.Relatorio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório
 * 
 * @author Rômulo Aurélio
 * @created 23/03/2009
 */
public class RelatorioImoveisComAcordo extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioImoveisComAcordo(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_COM_ACORDO);
	}

	@Deprecated
	public RelatorioImoveisComAcordo() {
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
	@SuppressWarnings("static-access")
	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		UnidadeNegocio unidadeNegocio = (UnidadeNegocio) getParametro("unidadeNegocio");
		GerenciaRegional gerenciaRegional = (GerenciaRegional) getParametro("gerenciaRegional");
		Localidade localidadeInicial = (Localidade) getParametro("localidadeInicial");
		Localidade localidadeFinal = (Localidade) getParametro("localidadeFinal");
		SetorComercial setorComercialInicial = (SetorComercial) getParametro("setorComercialInicial");
		SetorComercial setorComercialFinal = (SetorComercial) getParametro("setorComercialFinal");
		String rotaInicial = (String) getParametro("rotaInicial");
		String rotaFinal = (String) getParametro("rotaFinal");
		String sequencialRotaInicial = (String) getParametro("sequencialRotaInicial");
		String sequencialRotaFinal = (String) getParametro("sequencialRotaFinal");
		Date dataInicial = (Date) getParametro("dataInicial");
		Date dataFinal = (Date) getParametro("dataFinal");

		@SuppressWarnings("unused")
		String opcaoEmissao = (String) getParametro("opcaoRelatorio");

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Collection colecaoRelatorioImoveisComAcordoBean = Fachada
				.getInstancia().pesquisarDadosGerarRelatorioImoveisComAcordo(
						unidadeNegocio == null ? null : unidadeNegocio.getId(),
						localidadeInicial == null ? null : localidadeInicial
								.getId(),
						localidadeFinal == null ? null : localidadeFinal
								.getId(),
						gerenciaRegional == null ? null : gerenciaRegional
								.getId(),
						dataInicial,
						dataFinal,
						rotaInicial == null || rotaInicial.equals("") ? null
								: Integer.parseInt(rotaInicial),
						rotaFinal == null || rotaFinal.equals("") ? null
								: Integer.parseInt(rotaFinal),
						sequencialRotaInicial == null
								|| sequencialRotaInicial.equals("") ? null
								: Integer.parseInt(sequencialRotaInicial),
						sequencialRotaFinal == null
								|| sequencialRotaFinal.equals("") ? null
								: Integer.parseInt(sequencialRotaFinal),
						setorComercialInicial == null ? null
								: setorComercialInicial.getCodigo(),
						setorComercialFinal == null ? null
								: setorComercialFinal.getCodigo());

		if (colecaoRelatorioImoveisComAcordoBean != null
				&& !colecaoRelatorioImoveisComAcordoBean.isEmpty()) {

			Iterator it = colecaoRelatorioImoveisComAcordoBean.iterator();

			while (it.hasNext()) {
				RelatorioImoveisComAcordoBean relatorioImoveisComAcordoBean = (RelatorioImoveisComAcordoBean) it
						.next();

				try {
					relatorioImoveisComAcordoBean.setEndereco(Fachada
							.getInstancia().pesquisarEnderecoAbreviadoCAER(
									Integer.parseInt(relatorioImoveisComAcordoBean
											.getIdImovel())));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}

				relatorioImoveisComAcordoBean.setNomeCliente(Fachada
						.getInstancia().pesquisarNomeClientePorImovel(
								Integer.parseInt(relatorioImoveisComAcordoBean
										.getIdImovel())));

				relatorioImoveisComAcordoBean.setInscricao(Fachada
						.getInstancia().pesquisarInscricaoImovel(
								Integer.parseInt(relatorioImoveisComAcordoBean
										.getIdImovel())));

			}

			relatorioBeans.addAll(colecaoRelatorioImoveisComAcordoBean);
		} else {
			throw new ActionServletException("");
		}

		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = Fachada.getInstancia()
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		if (unidadeNegocio != null) {

			parametros.put("idUnidadeNegocio", unidadeNegocio.getId());
			parametros.put("nomeUnidadeNegocio", unidadeNegocio.getNome());

		} else {

			parametros.put("nomeUnidadeNegocio", "");
		}

		if (gerenciaRegional != null) {

			parametros.put("idGerenciaRegional", gerenciaRegional.getId());
			parametros.put("nomeGerenciaRegional", gerenciaRegional.getNome());

		} else {

			parametros.put("nomeGerenciaRegional", "");
		}

		if (sequencialRotaInicial != null && !sequencialRotaInicial.equals("")) {

			parametros.put("sequencialRotaInicial", sequencialRotaInicial);

		} else {
			parametros.put("sequencialRotaInicial", "");
		}

		if (sequencialRotaFinal != null && !sequencialRotaFinal.equals("")) {

			parametros.put("sequencialRotaFinal", sequencialRotaFinal);

		} else {
			parametros.put("sequencialRotaFinal", "");
		}

		if (rotaInicial != null && !rotaInicial.equals("")) {

			parametros.put("rotaInicial", rotaInicial);

		} else {
			parametros.put("rotaInicial", "");
		}

		if (rotaInicial != null && !rotaInicial.equals("")) {

			parametros.put("rotaFinal", rotaFinal);
		} else {
			parametros.put("rotaFinal", "");
		}

		if (localidadeInicial != null) {

			parametros.put("idLocalidadeInicial", localidadeInicial.getId());
			parametros.put("nomeLocalidadeInicial", localidadeInicial
					.getDescricao());
		} else {

			parametros.put("nomeLocalidadeInicial", "");
		}

		if (localidadeFinal != null) {

			parametros.put("idLocalidadeFinal", localidadeFinal.getId());
			parametros.put("nomeLocalidadeFinal", localidadeFinal
					.getDescricao());
		} else {

			parametros.put("nomeLocalidadeFinal", "");
		}

		parametros.put("dataInicial", Util.formatarData(dataInicial));

		parametros.put("dataFinal", Util.formatarData(dataFinal));
		parametros.put("tipoRelatorio", "R0891");

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_IMOVEIS_COM_ACORDO, parametros,
				ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_IMOVEIS_COM_ACORDO,
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

		UnidadeNegocio unidadeNegocio = (UnidadeNegocio) getParametro("unidadeNegocio");
		GerenciaRegional gerenciaRegional = (GerenciaRegional) getParametro("gerenciaRegional");
		Localidade localidadeInicial = (Localidade) getParametro("localidadeInicial");
		Localidade localidadeFinal = (Localidade) getParametro("localidadeFinal");
		SetorComercial setorComercialInicial = (SetorComercial) getParametro("setorComercialInicial");
		SetorComercial setorComercialFinal = (SetorComercial) getParametro("setorComercialFinal");
		String rotaInicial = (String) getParametro("rotaInicial");
		String rotaFinal = (String) getParametro("rotaFinal");
		String sequencialRotaInicial = (String) getParametro("sequencialRotaInicial");
		String sequencialRotaFinal = (String) getParametro("sequencialRotaFinal");
		Date dataInicial = (Date) getParametro("dataInicial");
		Date dataFinal = (Date) getParametro("dataFinal");

		retorno = Fachada.getInstancia()
				.pesquisarDadosGerarRelatorioImoveisComAcordoCount(
						unidadeNegocio == null ? null : unidadeNegocio.getId(),
						localidadeInicial == null ? null
								: localidadeInicial.getId(),
						localidadeFinal == null ? null : localidadeFinal.getId(),
						gerenciaRegional == null ? null : gerenciaRegional.getId(),
						dataInicial,
						dataFinal,
						rotaInicial == null || rotaInicial.equals("") ? null
								: Integer.parseInt(rotaInicial),
						rotaFinal == null || rotaFinal.equals("") ? null
								: Integer.parseInt(rotaFinal),
						sequencialRotaInicial == null
								|| sequencialRotaInicial.equals("") ? null
								: Integer.parseInt(sequencialRotaInicial),
						sequencialRotaFinal == null
								|| sequencialRotaFinal.equals("") ? null
								: Integer.parseInt(sequencialRotaFinal),
						setorComercialInicial == null ? null
								: setorComercialInicial.getCodigo(),
						setorComercialFinal == null ? null
								: setorComercialFinal.getCodigo());

		if (retorno == 0) {
			// Caso a pesquisa não retorne nenhum resultado comunica ao
			// usuário;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");

		}

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioImovelComAcordo", this);
	}
}
