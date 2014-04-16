package gcom.relatorio.seguranca;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
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

public class RelatorioOperacaoEfetuada extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	
	
	public RelatorioOperacaoEfetuada(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_EMPRESA_MANTER);
	}
	
	@Deprecated
	public RelatorioOperacaoEfetuada() {
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

		int       tipoFormatoRelatorio     = (Integer)   getParametro("tipoFormatoRelatorio");
		String [] idOperacoes              = (String[])  getParametro("idOperacoes");
		String    usuario                  = (String)    getParametro("usuario");
		Integer   usuarioAcao              = (Integer)   getParametro("usuarioAcao");
		Date      periodoRealizacaoInicial = (Date)      getParametro("periodoRealizacaoInicial");
		Date      periodoRealizacaoFinal   = (Date)      getParametro("periodoRealizacaoFinal");
		Date      horarioRealizacaoInicial = (Date)      getParametro("horarioRealizacaoInicial");
		Date      horarioRealizacaoFinal   = (Date)      getParametro("horarioRealizacaoFinal");
		Integer   id1                      = (Integer)   getParametro("id1");
		Integer   numeroPaginasPesquisa    = (Integer)   getParametro("numeroPaginasPesquisa");
		String    unidadeNegocio           = (String)    getParametro("unidadeNegocio");
		Integer   idFuncionalidade         = (Integer)   getParametro("idFuncionalidade");
		Hashtable < String,String > argumentos = ( Hashtable<String,String> ) getParametro("argumentos");
		

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioOperacaoEfetuadaBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		
 Collection colecaoOperacaoEfetuada = fachada.pesquisarUsuarioAlteracaoDasOperacoesEfetuadas(
		usuarioAcao , idOperacoes , usuario , periodoRealizacaoInicial ,
		periodoRealizacaoFinal , horarioRealizacaoInicial , horarioRealizacaoFinal , argumentos, id1 , 
		numeroPaginasPesquisa , unidadeNegocio );
		
		
		// se a coleção de parâmetros da analise não for vazia
		if ( colecaoOperacaoEfetuada != null && !colecaoOperacaoEfetuada.isEmpty() ) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator operacaoIterator = colecaoOperacaoEfetuada.iterator();

			// laço para criar a coleção de parâmetros da analise
			while ( operacaoIterator.hasNext() ) {
				//OperacaoEfetuada operacao = (OperacaoEfetuada) operacaoIterator.next();
				relatorioBean = new RelatorioOperacaoEfetuadaBean(		
			);	
			// adiciona o bean a coleção
			relatorioBeans.add( relatorioBean );
			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		parametros.put( "imagem", sistemaParametro.getImagemRelatorio() );

		if ( periodoRealizacaoInicial != null )
			parametros.put( "dataInicial" , periodoRealizacaoInicial );
		
		if( periodoRealizacaoFinal != null )
			parametros.put( "dataFinal" , periodoRealizacaoInicial );
		
		if (  horarioRealizacaoInicial != null )
			parametros.put( "horaInicial" , horarioRealizacaoInicial );
		
		if( horarioRealizacaoFinal != null )
			parametros.put( "horaFinal" , horarioRealizacaoFinal );
		
		if(  usuario!= null && !usuario.equals("") )
			parametros.put( "usuario" , usuario );
		
		if(  idFuncionalidade!= null )
			parametros.put( "idFuncionalidade" , idFuncionalidade );
		
		if( unidadeNegocio != null && !unidadeNegocio.equals("") )
			parametros.put( "unidadeNegocio" , unidadeNegocio );
	
		
		
	// cria uma instância do dataSource do relatório
	RelatorioDataSource ds = new RelatorioDataSource( relatorioBeans );

	retorno = this.gerarRelatorio( ConstantesRelatorios.RELATORIO_EMPRESA_MANTER , parametros ,
			ds , tipoFormatoRelatorio );
		
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
		AgendadorTarefas.agendarTarefa("RelatorioManterEmpresa", this);
	}

}
