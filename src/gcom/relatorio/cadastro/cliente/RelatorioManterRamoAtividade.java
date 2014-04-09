package gcom.relatorio.cadastro.cliente;

import gcom.cadastro.cliente.FiltroRamoAtividade;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
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
 * @author Fernando Fontelles
 * @version 1.0
 */

public class RelatorioManterRamoAtividade extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterRamoAtividade(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RAMO_ATIVIDADE_MANTER);
	}
	
	@Deprecated
	public RelatorioManterRamoAtividade() {
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

		FiltroRamoAtividade filtroRamoAtividade = (FiltroRamoAtividade) 
							getParametro("filtroRamoAtividade");
		RamoAtividade ramoAtividadeParametros = (RamoAtividade) getParametro("ramoAtividadeParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterRamoAtividadeBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoRamoAtividade = fachada.pesquisar(filtroRamoAtividade,
				RamoAtividade.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoRamoAtividade != null && !colecaoRamoAtividade.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator ramoAtividadeIterator = colecaoRamoAtividade.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (ramoAtividadeIterator.hasNext()) {

				RamoAtividade ramoAtividade = (RamoAtividade) ramoAtividadeIterator.next();
				
				String codigo = "" + ramoAtividade.getCodigo();
				
				relatorioBean = new RelatorioManterRamoAtividadeBean(
						// CODIGO
						codigo, 
						
						// Descrição
						ramoAtividade.getDescricao(),
						
						//Indicador de Uso						
						ramoAtividade.getIndicadorUso().toString());
						
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
		
		if(""+ramoAtividadeParametros.getCodigo() != null 
				&& ramoAtividadeParametros.getCodigo() != 0){
			
			parametros.put("codigo",
					""+ ramoAtividadeParametros.getCodigo());
			
		}else{
			parametros.put("codigo","");
		}
		
		if(ramoAtividadeParametros.getDescricao() != null &&
				!ramoAtividadeParametros.getDescricao().equals("")){
			
			parametros.put("descricao", ramoAtividadeParametros.getDescricao());
			
		}
		
		String indicadorUso = "";

		if (ramoAtividadeParametros.getIndicadorUso() != null
				&& !ramoAtividadeParametros.getIndicadorUso().equals("")) {
			if (ramoAtividadeParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (ramoAtividadeParametros.getIndicadorUso().equals(
					new Short("2"))) {
				indicadorUso = "Inativo";
			}
			
		}
		
		parametros.put("indicadorUso", indicadorUso);
		
		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_RAMO_ATIVIDADE_MANTER, parametros,
				ds, tipoFormatoRelatorio);
		
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
		AgendadorTarefas.agendarTarefa("RelatorioManterRamoAtividade", this);
	}

}
