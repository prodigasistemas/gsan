package gcom.relatorio.micromedicao.rota;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
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
import java.util.Collections;
import java.util.Comparator;
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
 * @author Rafael Corrêa
 * @version 1.0
 */

public class RelatorioManterRota extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterRota(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ROTA_MANTER);
	}

	@Deprecated
	public RelatorioManterRota() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param localidades
	 *            Description of the Parameter
	 * @param localidadeParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroRota filtroRota = (FiltroRota) getParametro("filtroRota");
		Rota rotaParametros = (Rota) getParametro("rotaParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterRotaBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		rotaParametros.getFaturamentoGrupo();

		filtroRota.adicionarCaminhoParaCarregamentoEntidade("empresa");
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("leituraTipo");
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("cobrancaGrupo");
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
		filtroRota
				.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("empresaEntregaContas");
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("empresaCobranca");
		filtroRota.setConsultaSemLimites(true);

		Collection colecaoRotas = fachada.pesquisar(filtroRota, Rota.class
				.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoRotas != null && !colecaoRotas.isEmpty()) {

			// Organizar a coleção

			Collections.sort((List) colecaoRotas, new Comparator() {
				public int compare(Object a, Object b) {
					String setor1 = ""
							+ ((Rota) a).getSetorComercial().getLocalidade()
									.getId()
							+ ((Rota) a).getSetorComercial().getCodigo();
					String setor2 = ""
							+ ((Rota) b).getSetorComercial().getLocalidade()
									.getId()
							+ ((Rota) b).getSetorComercial().getCodigo();

					return setor1.compareTo(setor2);
				}
			});

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoRotasIterator = colecaoRotas.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoRotasIterator.hasNext()) {

				Rota rota = (Rota) colecaoRotasIterator.next();

				// Faz as validações dos campos necessáriose e formata a String
				// para a forma como irá aparecer no relatório
				String setorComercial = "";
				String localidade = "";

				if (rota.getSetorComercial() != null) {

					setorComercial = rota.getSetorComercial().getCodigo()
							+ " - " + rota.getSetorComercial().getDescricao();

					if (rota.getSetorComercial().getLocalidade() != null) {
						localidade = rota.getSetorComercial().getLocalidade()
								.getId().toString()
								+ " - "
								+ rota.getSetorComercial().getLocalidade()
										.getDescricao();
					}
				}

				String tipoLeitura = "";

				if (rota.getLeituraTipo() != null) {
					tipoLeitura = rota.getLeituraTipo().getDescricao();
				}

				String empresa = "";

				if (rota.getEmpresa() != null) {
					empresa = rota.getEmpresa().getDescricao();
				}
				
				String empresaCobranca = "";

				if (rota.getEmpresaCobranca() != null) {
					empresaCobranca = rota.getEmpresaCobranca().getDescricao();
				}

				String empresaEntregaContas = "";

				if (rota.getEmpresaEntregaContas() != null) {
					empresaEntregaContas = rota.getEmpresaEntregaContas().getDescricao();
				}
				relatorioBean = new RelatorioManterRotaBean(
						// Código
						"" + rota.getCodigo(),

						// Localidade
						localidade,

						// Setor Comercial
						setorComercial,

						// Grupo de Faturamento
						rota.getFaturamentoGrupo().getDescricao(),

						// Grupo de Cobrança
						rota.getCobrancaGrupo().getDescricao(),

						// Tipo de Leitura
						tipoLeitura,

						// Empresa
						empresa,
						
						//Empresa Cobranca
						empresaCobranca,
						
						//Empresa Entrega Contas
						empresaEntregaContas,

						// Indicador Uso
						rota.getIndicadorUso().toString());

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		if (rotaParametros.getCodigo() != null) {

			parametros.put("codigo", rotaParametros.getCodigo().toString());

		} else {
			parametros.put("codigo", "");
		}

		if (rotaParametros.getSetorComercial().getLocalidade().getId() != null) {

			parametros.put("idLocalidade", rotaParametros.getSetorComercial()
					.getLocalidade().getId().toString());

		} else {
			parametros.put("idLocalidade", "");
		}

		parametros.put("nomeLocalidade", rotaParametros.getSetorComercial()
				.getLocalidade().getDescricao());

		if (rotaParametros.getSetorComercial().getId() != null) {

			parametros.put("idSetorComercial", ""
					+ rotaParametros.getSetorComercial().getCodigo());

		} else {
			parametros.put("idSetorComercial", "");
		}

		parametros.put("nomeSetorComercial", rotaParametros.getSetorComercial()
				.getDescricao());

		parametros.put("grupoFaturamento", rotaParametros.getFaturamentoGrupo()
				.getDescricao());

		String indicadorUso = "";

		if (rotaParametros.getIndicadorUso() != null
				&& !rotaParametros.getIndicadorUso().equals("")) {
			if (rotaParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (rotaParametros.getIndicadorUso().equals(new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}

		parametros.put("indicadorUso", indicadorUso);

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_ROTA_MANTER, parametros, ds,
				tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_ROTA,
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

		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
				(FiltroRota) getParametro("filtroRota"), Rota.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterRota", this);
	}

}
