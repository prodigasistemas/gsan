package gcom.relatorio.cadastro.imovel;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de imoveis por Programas Especiais
 * 
 * @author Hugo Leonrado
 * @created 18/01/2010
 */
public class RelatorioImoveisProgramasEspeciaisAnalitico extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioImoveisProgramasEspeciaisAnalitico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_PROGRAMAS_ESPECIAIS_ANALITICO);
	}

	@Deprecated
	public RelatorioImoveisProgramasEspeciaisAnalitico() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 */
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltrarRelatorioImoveisProgramasEspeciaisHelper relatorioHelper = 
			(FiltrarRelatorioImoveisProgramasEspeciaisHelper) getParametro("filtrarRelatorioImoveisProgramasEspeciaisHelper");
		
		String anoMesReferencia = relatorioHelper.getMesAnoReferencia();
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String perfilImovel = relatorioHelper.getNomePerfilImovel();
		String opcaoTotalizacao = relatorioHelper.getOpcaoTotalizacao();
		String opcaoRelatorio = relatorioHelper.getTipo();

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioImoveisProgramasEspeciaisBean relatorioImoveisProgramasEspeciaisBean = null;

		
		Collection<RelatorioImoveisProgramasEspeciaisHelper> colecao =  
			fachada.pesquisarRelatorioImoveisProgramasEspeciaisAnalitico(relatorioHelper);

		// se a coleção de parâmetros da analise não for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {

				RelatorioImoveisProgramasEspeciaisHelper helper = 
					(RelatorioImoveisProgramasEspeciaisHelper) colecaoIterator.next();
				
				relatorioImoveisProgramasEspeciaisBean = 
					new RelatorioImoveisProgramasEspeciaisBean(
							helper.getIdRegiaoDesenvolvimento(),
							helper.getNomeRegiaoDesenvolvimento(),
							helper.getIdLocalidade(),
							helper.getNomeLocalidade(),
							helper.getIdImovel(),
							helper.getNomeUsuario(),
							helper.getSituacaoMedicao(),
							helper.getConsumoAgua(),
							helper.getValorConta() ,
							helper.getEndereco()
							);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioImoveisProgramasEspeciaisBean);				
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());		
	
		parametros.put("anoMesReferencia" , Util.formatarAnoMesParaMesAno(anoMesReferencia));
		
		// selecionar o tipo de relatório
		if(opcaoRelatorio.equals("0")) {
			opcaoRelatorio = "ANALÍTICO";
		} else {
			opcaoRelatorio = "SINTÉTICO";
		}
		
		parametros.put("tipo", opcaoRelatorio);
		
		parametros.put("perfilImovel", perfilImovel);
		
		// selecionar a opção de Totalização
		if ( opcaoTotalizacao != null){
			if (opcaoTotalizacao.equals("0")) {
				opcaoTotalizacao = "ESTADO";
			} else if ( opcaoTotalizacao.equals("1")) {
				opcaoTotalizacao = "REGIÃO DE DESENVOLVIMENTO";
			} else if (opcaoTotalizacao.equals("2")) {
				opcaoTotalizacao = "LOCALIDADE";
			}
		}
		parametros.put("opcaoTotalizacao", opcaoTotalizacao);
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_IMOVEIS_PROGRAMAS_ESPECIAIS_ANALITICO,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_IMOVEIS_PROGRAMAS_ESPECIAIS_ANALITICO,
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

		
		retorno = 
			Fachada.getInstancia().pesquisarTotalRegistroRelatorioImoveisProgramaEspecial(
				(FiltrarRelatorioImoveisProgramasEspeciaisHelper) 
					getParametro("filtrarRelatorioImoveisProgramasEspeciaisHelper"));
        
		if (retorno == 0) {
			// Caso a pesquisa não retorne nenhum resultado comunica ao
			// usuário;
			throw new RelatorioVazioException("atencao.relatorio.vazio");

		}
		
		return retorno;		
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioImoveisProgramasEspeciaisAnalitico", this);

	}

}
