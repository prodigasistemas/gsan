package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
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
 * @author Arthur Carvalho
 * @version 1.0
 */

public class RelatorioManterPerfilLigacaoEsgoto extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterPerfilLigacaoEsgoto(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_PERFIL_LIGACAO_ESGOTO_MANTER);
	}
	
	@Deprecated
	public RelatorioManterPerfilLigacaoEsgoto() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param situacao pagamento
	 *            Description of the Parameter
	 * @param SituacaoPagamentoParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroLigacaoEsgotoPerfil filtroLigacaoEsgotoPerfil = (FiltroLigacaoEsgotoPerfil) getParametro("filtroLigacaoEsgotoPerfil");
		LigacaoEsgotoPerfil ligacaoEsgotoPerfilParametros = (LigacaoEsgotoPerfil) getParametro("ligacaoEsgotoPerfilParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterPerfilLigacaoEsgotoBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoLigacaoEsgotoPerfil = fachada.pesquisar(filtroLigacaoEsgotoPerfil,
				LigacaoEsgotoPerfil.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoLigacaoEsgotoPerfil != null && !colecaoLigacaoEsgotoPerfil.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator ligacaoEsgotoPerfilIterator = colecaoLigacaoEsgotoPerfil.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (ligacaoEsgotoPerfilIterator.hasNext()) {

				LigacaoEsgotoPerfil ligacaoEsgotoPerfil = (LigacaoEsgotoPerfil) ligacaoEsgotoPerfilIterator.next();

				String indicadorUso = "";
				
				if(ligacaoEsgotoPerfil.getIndicadorUso().equals(ConstantesSistema.INDICADOR_USO_ATIVO)){
					indicadorUso = "ATIVO";
				} else {
					indicadorUso= "INATIVO";
				}
				
				
				relatorioBean = new RelatorioManterPerfilLigacaoEsgotoBean(
						// ID
						ligacaoEsgotoPerfil.getId().toString(), 
						
						// Descrição
						ligacaoEsgotoPerfil.getDescricao(), 
						
						//Percentual de Esgoto
						ligacaoEsgotoPerfil.getPercentualEsgotoConsumidaColetada().toString(),
						
						//Indicador de Uso
						indicadorUso);
						
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

		parametros.put("id",
				ligacaoEsgotoPerfilParametros.getId() == null ? "" : ""
						+ ligacaoEsgotoPerfilParametros.getId());

		parametros.put("descricao", ligacaoEsgotoPerfilParametros.getDescricao());
		
		if (ligacaoEsgotoPerfilParametros.getPercentualEsgotoConsumidaColetada() != null) {
			parametros.put("percentualEsgotoConsumidaColetada", ( ligacaoEsgotoPerfilParametros.getPercentualEsgotoConsumidaColetada().toString()));
		} else {
			parametros.put("percentualEsgotoConsumidaColetada", "");
		}

		String indicadorUso = "";

		if (ligacaoEsgotoPerfilParametros.getIndicadorUso() != null
				&& !ligacaoEsgotoPerfilParametros.getIndicadorUso().equals("")) {
			if (ligacaoEsgotoPerfilParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (ligacaoEsgotoPerfilParametros.getIndicadorUso().equals(new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}

		parametros.put("indicadorUso", indicadorUso);
		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_PERFIL_LIGACAO_ESGOTO_MANTER, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
//		try {
//			persistirRelatorioConcluido(retorno, Relatorio.MANTER_LOCALIDADE,
//					idFuncionalidadeIniciada);
//		} catch (ControladorException e) {
//			e.printStackTrace();
//			throw new TarefaException("Erro ao gravar relatório no sistema", e);
//		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

//		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
//				(FiltroLocalidade) getParametro("filtroLocalidade"),
//				Localidade.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterPerfilLigacaoEsgoto", this);
	}

}
