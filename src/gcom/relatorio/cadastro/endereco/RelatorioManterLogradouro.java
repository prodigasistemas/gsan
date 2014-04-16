package gcom.relatorio.cadastro.endereco;

import gcom.batch.Relatorio;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class RelatorioManterLogradouro extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterLogradouro(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_LOGRADOURO_MANTER);
	}

	@Deprecated
	public RelatorioManterLogradouro() {
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

		Collection logradouros = (Collection) getParametro("logradouros");
		Logradouro logradouroParametros = (Logradouro) getParametro("logradouroParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterLogradouroBean relatorioBean = null;

		// se a coleção de parâmetros da analise não for vazia
		if (logradouros != null && !logradouros.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			Iterator logradouroIterator = logradouros.iterator();

//			FiltroLogradouroBairro filtroLogradouroBairroCarregar = new FiltroLogradouroBairro();
//			filtroLogradouroBairroCarregar.adicionarCaminhoParaCarregamentoEntidade("logradouro.logradouroTitulo");
//			filtroLogradouroBairroCarregar.adicionarCaminhoParaCarregamentoEntidade("logradouro.logradouroTipo");
//			filtroLogradouroBairroCarregar.adicionarCaminhoParaCarregamentoEntidade("logradouro.municipio");
//			filtroLogradouroBairroCarregar.adicionarCaminhoParaCarregamentoEntidade("bairro");

			// laço para criar a coleção de parâmetros da analise
			while (logradouroIterator.hasNext()) {

				LogradouroBairro logradouroBairro = (LogradouroBairro) logradouroIterator.next();
				
				Logradouro logradouro = logradouroBairro.getLogradouro();

//				LogradouroBairro logradouroBairro = null;
//				FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();
//				filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade("bairro");
//
//				filtroLogradouroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.ID_LOGRADOURO, logradouro.getId()));
//
//				Collection logradourosBairros = fachada.pesquisar(filtroLogradouroBairro, LogradouroBairro.class.getName());
//
//				if (logradourosBairros != null && !logradourosBairros.isEmpty()) {
//					// O Bairro Foi Encontrado
//
//					Iterator logradouroBairroIterator = logradourosBairros.iterator();
//					logradouroBairro = (LogradouroBairro) logradouroBairroIterator.next();
//
//				}

				String bairro = "";

				if (logradouroBairro != null && logradouroBairro.getBairro() != null) {
					bairro = logradouroBairro.getBairro().getNome();
				}

				String titulo = "";

				if (logradouro.getLogradouroTitulo() != null) {
					titulo = logradouro.getLogradouroTitulo().getDescricao();
				}

				relatorioBean = new RelatorioManterLogradouroBean(
						// Código
						logradouro.getId().toString(),

						// Nome
						logradouro.getNome(),

						// Título do Logradouro
						titulo,

						// Tipo do Logradouro
						logradouro.getLogradouroTipo().getDescricao(),

						// Município
						logradouro.getMunicipio().getNome(),

						// Bairro
						bairro,

						// Indicador Uso
						logradouro.getIndicadorUso().toString());

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("codigo", logradouroParametros.getId() == null ? "" : ""	+ logradouroParametros.getId());

		parametros.put("nome", logradouroParametros.getNome());

		parametros.put("titulo", logradouroParametros.getLogradouroTitulo().getDescricao());

		parametros.put("tipo", logradouroParametros.getLogradouroTipo().getDescricao());

		parametros.put("idMunicipio", logradouroParametros.getMunicipio().getId() == null ? "" : ""
				+ logradouroParametros.getMunicipio().getId());

		parametros.put("nomeMunicipio", logradouroParametros.getMunicipio().getNome());

		String indicadorUso = "";

		if (logradouroParametros.getIndicadorUso() != null
				&& !logradouroParametros.getIndicadorUso().equals("")) {
			if (logradouroParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else {
				indicadorUso = "Inativo";
			}
		}

		parametros.put("indicadorUso", indicadorUso);

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_LOGRADOURO_MANTER, parametros,
				ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_LOGRADOURO,
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

		if (getParametro("logradouros") != null
				&& getParametro("logradouros") instanceof Collection) {
			retorno = ((Collection) getParametro("logradouros")).size();
		}

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterLogradouro", this);
	}

}
