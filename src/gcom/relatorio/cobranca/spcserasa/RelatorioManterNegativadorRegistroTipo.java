package gcom.relatorio.cobranca.spcserasa;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.FiltroNegativadorRegistroTipo;
import gcom.cobranca.NegativadorRegistroTipo;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesExecucaoRelatorios;
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
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corrêa
 * @created 9 de Setembro de 2005
 * @version 1.0
 */

public class RelatorioManterNegativadorRegistroTipo extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the RelatorioManterNegativadorRegistroTipo object
	 */
	public RelatorioManterNegativadorRegistroTipo(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_NEGATIVADOR_REGISTRO_TIPO);
	}

	@Deprecated
	public RelatorioManterNegativadorRegistroTipo() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param NegativadorRegistroTipoParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		// Recebe os parâmetros que serão utilizados no relatório
		FiltroNegativadorRegistroTipo filtroNegativadorRegistroTipo = (FiltroNegativadorRegistroTipo) getParametro("filtroNegativadorRegistroTipo");
		NegativadorRegistroTipo negativadorRegistroTipoParametros = (NegativadorRegistroTipo) getParametro("negativadorRegistroTipoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório

		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterNegativadorRegistroTipoBean relatorioBean = null;

		// Cria adiciona os carregamentos dos objetos necessários para
		// a impressão do relatório
		filtroNegativadorRegistroTipo.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");
		filtroNegativadorRegistroTipo.setConsultaSemLimites(true);

		// Nova consulta para trazer objeto completo
		Collection<NegativadorRegistroTipo> colecaoNegativadorRegistroTiposNovas = fachada.pesquisar(filtroNegativadorRegistroTipo, NegativadorRegistroTipo.class
				.getName());

		if (colecaoNegativadorRegistroTiposNovas != null && !colecaoNegativadorRegistroTiposNovas.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			for (NegativadorRegistroTipo negativadorRegistroTipo : colecaoNegativadorRegistroTiposNovas) {
				
				// Faz as validações dos campos necessáriose e formata a String
				// para a forma como irá aparecer no relatório
				
				// ID
				String id = "";
				
				if (negativadorRegistroTipo.getId() != null) {
					id = negativadorRegistroTipo.getId().toString();
				}
				
				// Descrição
				String descricao = "";
				
				if (negativadorRegistroTipo.getDescricaoRegistroTipo() != null
						&& !negativadorRegistroTipo.getDescricaoRegistroTipo()
								.trim().equals("")) {
					descricao = negativadorRegistroTipo.getDescricaoRegistroTipo();
				}
				
				// codigoRegistro
				String codigoRegistro = "";
				
				if (negativadorRegistroTipo.getCodigoRegistro() != null
						&& !negativadorRegistroTipo.getCodigoRegistro()
								.trim().equals("")) {
					codigoRegistro = negativadorRegistroTipo.getCodigoRegistro();
				}

				
				// negativador
				String negativador = "";				
				if (negativadorRegistroTipo.getNegativador().getId() != null) {
					negativador = negativadorRegistroTipo.getNegativador().getCliente().getNome();
				}
				
				

				// Inicializa o construtor constituído dos campos
				// necessários para a impressão do relatorio
				relatorioBean = new RelatorioManterNegativadorRegistroTipoBean(
						
						// ID
						id,

						// Descrição
						descricao,

						// Código Registro
						codigoRegistro,

						// Descrição do Negativador
						negativador);

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

		parametros.put("negativador", negativadorRegistroTipoParametros.getNegativador().getId() == -1 ? ""
				: "" + negativadorRegistroTipoParametros.getNegativador().getCliente().getNome());	

		parametros.put("descricao", negativadorRegistroTipoParametros.getDescricaoRegistroTipo());

		parametros.put("codigoTipoRegistro", negativadorRegistroTipoParametros.getCodigoRegistro());

	

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_NEGATIVADOR_REGISTRO_TIPO, parametros, ds,
				tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_NEGATIVADOR_REGISTRO_TIPO,
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
		int retorno = ConstantesExecucaoRelatorios.QUANTIDADE_NAO_INFORMADA;

//		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
//				(FiltroNegativadorRegistroTipo) getParametro("filtroNegativadorRegistroTipo"),
//				NegativadorRegistroTipo.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterNegativadorRegistroTipo", this);
	}

}
