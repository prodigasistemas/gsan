package gcom.relatorio.operacional;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.operacional.DivisaoEsgoto;
import gcom.operacional.FiltroDivisaoEsgoto;
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

public class RelatorioManterDivisaoEsgoto extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterDivisaoEsgoto(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_DIVISAO_ESGOTO_MANTER);
	}
	
	@Deprecated
	public RelatorioManterDivisaoEsgoto() {
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

		FiltroDivisaoEsgoto filtroDivisaoEsgoto = (FiltroDivisaoEsgoto) getParametro("filtroDivisaoEsgoto");
		DivisaoEsgoto divisaoEsgotoParametros = (DivisaoEsgoto) getParametro("divisaoEsgotoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterDivisaoEsgotoBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		filtroDivisaoEsgoto.adicionarCaminhoParaCarregamentoEntidade(FiltroDivisaoEsgoto.UNIDADE_ORGANIZACIONAL);
		
		Collection colecaoDivisaoEsgoto = fachada.pesquisar(filtroDivisaoEsgoto,
				DivisaoEsgoto.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoDivisaoEsgoto != null && !colecaoDivisaoEsgoto.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator divisaoEsgotoIterator = colecaoDivisaoEsgoto.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (divisaoEsgotoIterator.hasNext()) {

				DivisaoEsgoto divisaoEsgoto = (DivisaoEsgoto) divisaoEsgotoIterator.next();
				
				String ativoInativo = "";
				
				if ( divisaoEsgoto.getIndicadorUso().equals( ConstantesSistema.INDICADOR_USO_ATIVO ) ){
					ativoInativo = "Ativo";
				} else {
					ativoInativo = "Inativo";
				}				
				
				relatorioBean = new RelatorioManterDivisaoEsgotoBean(
						// ID
						divisaoEsgoto.getId().toString(), 
						
						// Descrição
						divisaoEsgoto.getDescricao(), 
						
						// unidade Organizacional
						divisaoEsgoto.getUnidadeOrganizacional() == null ? "" : divisaoEsgoto.getUnidadeOrganizacional().getDescricao(),
						
						//indicador de uso
						ativoInativo);
						
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
				divisaoEsgotoParametros.getId()== null ? "" : ""
					+ divisaoEsgotoParametros.getId());
						
		parametros.put("descricao", divisaoEsgotoParametros.getDescricao());
		
		
		if (divisaoEsgotoParametros.getUnidadeOrganizacional() != null) {
			parametros.put("divisaoEsgoto", divisaoEsgotoParametros.getUnidadeOrganizacional().getDescricao());
		} else {
			parametros.put("divisaoEsgoto", "");	
		}

		String indicadorUso = "";

		if (divisaoEsgotoParametros.getIndicadorUso() != null
				&& !divisaoEsgotoParametros.getIndicadorUso().equals("")) {
			if (divisaoEsgotoParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (divisaoEsgotoParametros.getIndicadorUso().equals(
					new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}

		parametros.put("indicadorUso", indicadorUso);
		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_DIVISAO_ESGOTO_MANTER, parametros,
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

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterDivisaoEsgoto", this);
	}

}
