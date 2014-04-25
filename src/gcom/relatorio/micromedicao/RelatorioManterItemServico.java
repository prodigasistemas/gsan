package gcom.relatorio.micromedicao;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.micromedicao.FiltroItemServico;
import gcom.micromedicao.ItemServico;
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
 * @author Rodrigo Cabral
 * @version 1.0
 */

public class RelatorioManterItemServico extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterItemServico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_ITEM_SERVICO);
	}
	
	@Deprecated
	public RelatorioManterItemServico() {
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

		FiltroItemServico filtroItemServico = (FiltroItemServico) 
							getParametro("filtroItemServico");
		ItemServico itemServicoParametros = (ItemServico) getParametro("itemServicoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterItemServicoBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoItemServico = fachada.pesquisar(filtroItemServico,
				ItemServico.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoItemServico != null && !colecaoItemServico.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator itemServicoIterator = colecaoItemServico.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (itemServicoIterator.hasNext()) {

				ItemServico itemServico = (ItemServico) itemServicoIterator.next();
				
				String codigo = "" + itemServico.getCodigoItem();
				
				String codigoConstanteCalculo = "" ;
				if ( itemServico.getCodigoConstanteCalculo() != null ) {
					codigoConstanteCalculo = itemServico.getCodigoConstanteCalculo().toString();
				}
				
				relatorioBean = new RelatorioManterItemServicoBean(
						// Descrição
						itemServico.getDescricao(),
						
						//Descrição Abreviada
						itemServico.getDescricaoAbreviada(),
						
						//Indicador de Uso						
						itemServico.getIndicadorUso().toString(),
				
						//Código Constante de Cálculo
						codigoConstanteCalculo,

						// CODIGO
						codigo);
				
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
		
		if(itemServicoParametros.getCodigoItem() != null 
				&& itemServicoParametros.getCodigoItem() != 0){
			
			parametros.put("codigoItem",
					""+ itemServicoParametros.getCodigoItem());
			
		}else{
			parametros.put("codigoItem","");
		}
		
		if(itemServicoParametros.getDescricao() != null &&
				!itemServicoParametros.getDescricao().equals("")){
			
			parametros.put("descricao", itemServicoParametros.getDescricao());
			
		}
		
		if(itemServicoParametros.getDescricaoAbreviada() != null &&
				!itemServicoParametros.getDescricaoAbreviada().equals("")){
			
			parametros.put("descricaoAbreviada", itemServicoParametros.getDescricaoAbreviada());
			
		}
		
		if(itemServicoParametros.getCodigoConstanteCalculo() != null &&
				!itemServicoParametros.getCodigoConstanteCalculo().equals("")){
			
			parametros.put("codigoConstanteCalculo", "" +itemServicoParametros.getCodigoConstanteCalculo());
			
		}
		
		parametros.put("tipo", "R1065" );
		
		String indicadorUso = "";

		if (itemServicoParametros.getIndicadorUso() != null
				&& !itemServicoParametros.getIndicadorUso().equals("")) {
			if (itemServicoParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (itemServicoParametros.getIndicadorUso().equals(
					new Short("2"))) {
				indicadorUso = "Inativo";
			}
			
		}
		
		parametros.put("indicadorUso", indicadorUso);
		
		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_ITEM_SERVICO, parametros,
				ds, tipoFormatoRelatorio);
		
		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 1;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterItemServico", this);
	}

}
