package gcom.relatorio.transacao;
 
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.transacao.FiltroTabelaLinhaColunaAlteracao;
import gcom.seguranca.transacao.TabelaLinhaColunaAlteracao;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RelatorioOperacao extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioOperacao (Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_OPERACAO);
	}

	private Collection<RelatorioOperacaoBean> inicializarBeanRelatorio( Collection dadosRelatorio) {

        Collection<RelatorioOperacaoBean> colecaoBean = new ArrayList();

        Iterator iterator = dadosRelatorio.iterator();
        while (iterator.hasNext()) {
        	OperacaoEfetuada operacaoEfetuada = (OperacaoEfetuada) iterator.next();

        	Set usuarioAlteracoes = operacaoEfetuada.getUsuarioAlteracoes();
        	/*Iterator itUsuarioAlteracoes =*/ usuarioAlteracoes.iterator();

       FiltroTabelaLinhaColunaAlteracao filtroTabelaLinhaColunaAlteracao = new FiltroTabelaLinhaColunaAlteracao();            
       filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.TABELA_COLUNA);
       filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.TABELA_LINHA_ALTERACAO);
       filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.TABELA);
       filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.OPERACAO_EFETUADA);
       filtroTabelaLinhaColunaAlteracao.adicionarParametro(new ParametroSimples(FiltroTabelaLinhaColunaAlteracao.OPERACAO_EFETUADA_ID, operacaoEfetuada.getId()));
       
   Collection tabelaLinhaColunaAlteracoes = Fachada.getInstancia()
   .pesquisar( filtroTabelaLinhaColunaAlteracao , TabelaLinhaColunaAlteracao.class.getName() );
            /*Iterator itTabelaLinhaColunaAlteracoes =*/ tabelaLinhaColunaAlteracoes.iterator();

        }
        
        return colecaoBean;

	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {
        
        int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map parametros = new HashMap();

		//Fachada fachada = Fachada.getInstancia();
		Collection dadosRelatorio = new ArrayList();
//        Collection dadosRelatorio = Fachada.getInstancia().pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlRelatorio(idUsuarioAcao, 
//        		idOperacao, idUsuario,dataInicial, dataFinal, horaInicial, horaFinal, idTabela, idTabelaColuna, id1);

    	Collection<RelatorioOperacaoBean> colecaoBean = inicializarBeanRelatorio( dadosRelatorio );

		Date data = new Date(System.currentTimeMillis());
		String nomeOperacao = "";  
		if (colecaoBean == null || colecaoBean.isEmpty()) {
			throw new RelatorioVazioException("Relatório sem dados");
		}
		
		OperacaoEfetuada operacaoEfetuada = (OperacaoEfetuada) dadosRelatorio.iterator().next();
		nomeOperacao = operacaoEfetuada.getOperacao().getDescricao(); 
		data = operacaoEfetuada.getUltimaAlteracao();

		parametros.put("dataRealizacao", Util.formatarDataComHora(data));
		parametros.put("nomeOperacao", nomeOperacao);

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_OPERACAO, parametros,ds, tipoFormatoRelatorio);

		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		return 0;
	}

	public void agendarTarefaBatch() {
	}
}
