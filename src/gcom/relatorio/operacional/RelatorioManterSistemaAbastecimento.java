package gcom.relatorio.operacional;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.SistemaAbastecimento;
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

public class RelatorioManterSistemaAbastecimento extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterSistemaAbastecimento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_SISTEMA_ABASTECIMENTO_MANTER);
	}
	
	@Deprecated
	public RelatorioManterSistemaAbastecimento() {
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

		FiltroSistemaAbastecimento filtroSistemaAbastecimento = (FiltroSistemaAbastecimento) 
							getParametro("filtroSistemaAbastecimento");
		SistemaAbastecimento sistemaAbastecimentoParametros = (SistemaAbastecimento) getParametro("sistemaAbastecimentoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterSistemaAbastecimentoBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoSistemaAbastecimento = fachada.pesquisar(filtroSistemaAbastecimento,
				SistemaAbastecimento.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoSistemaAbastecimento != null && !colecaoSistemaAbastecimento.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator sistemaAbastecimentoIterator = colecaoSistemaAbastecimento.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (sistemaAbastecimentoIterator.hasNext()) {

				SistemaAbastecimento sistemaAbastecimento = (SistemaAbastecimento) sistemaAbastecimentoIterator.next();

				String fonteCaptacao = "";
				
				if(sistemaAbastecimento.getFonteCaptacao() != null &&
						!sistemaAbastecimento.getFonteCaptacao().equals("")){
					
					fonteCaptacao = sistemaAbastecimento.getFonteCaptacao().getDescricao();
					
				}
				
				relatorioBean = new RelatorioManterSistemaAbastecimentoBean(
						// ID
						sistemaAbastecimento.getId().toString(), 
						
						// Descrição
						sistemaAbastecimento.getDescricao(), 
						
						// Fonte Captacao
						fonteCaptacao,
						
						//Indicador de Uso						
						sistemaAbastecimento.getIndicadorUso().toString());
						
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
				sistemaAbastecimentoParametros.getId() == null ? "" : ""
						+ sistemaAbastecimentoParametros.getId());
		
		if(sistemaAbastecimentoParametros.getDescricao() != null &&
				!sistemaAbastecimentoParametros.getDescricao().equals("")){
			
			parametros.put("descricao", sistemaAbastecimentoParametros.getDescricao());
			
		}
		
		
		String fonteCaptacao= "";
		
		if(sistemaAbastecimentoParametros.getFonteCaptacao() != null && 
				!sistemaAbastecimentoParametros.getFonteCaptacao().equals("")){
			
			fonteCaptacao = sistemaAbastecimentoParametros.getFonteCaptacao().getDescricao();
			
		}

		parametros.put("fonteCaptacao", fonteCaptacao);

		String indicadorUso = "";

		if (sistemaAbastecimentoParametros.getIndicadorUso() != null
				&& !sistemaAbastecimentoParametros.getIndicadorUso().equals("")) {
			if (sistemaAbastecimentoParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (sistemaAbastecimentoParametros.getIndicadorUso().equals(
					new Short("2"))) {
				indicadorUso = "Inativo";
			}
			
		}
		
		parametros.put("indicadorUso", indicadorUso);
		
		if(sistemaAbastecimentoParametros.getDescricaoAbreviada() != null &&
				!sistemaAbastecimentoParametros.getDescricaoAbreviada().equals("")){
			parametros.put("descricaoAbreviada",sistemaAbastecimentoParametros.getDescricaoAbreviada());
		}
		
		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_SISTEMA_ABASTECIMENTO_MANTER, parametros,
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
		AgendadorTarefas.agendarTarefa("RelatorioManterSistemaAbastecimento", this);
	}

}
