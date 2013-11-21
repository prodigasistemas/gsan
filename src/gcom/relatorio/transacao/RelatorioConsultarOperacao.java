/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.relatorio.transacao;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.FiltroUsuarioAlteracao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAlteracao;
import gcom.seguranca.transacao.FiltroTabelaLinhaColunaAlteracao;
import gcom.seguranca.transacao.TabelaLinhaColunaAlteracao;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * classe responsável por criar o relatório de operação consultar
 * 
 * @author Rafael Corrêa
 * @created 06/04/2006
 */
public class RelatorioConsultarOperacao extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAgua
	 */
	public RelatorioConsultarOperacao(Usuario usuario) {
		super(usuario,ConstantesRelatorios.RELATORIO_OPERACAO_CONSULTAR);
	}
	
	@Deprecated
	public RelatorioConsultarOperacao() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */
	
	@SuppressWarnings("unchecked")
	public Object executar() throws TarefaException {
	
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		String[]    idOperacoes              = (String[])    getParametro("idOperacoes");
		Usuario     usuario                  = (Usuario)     getParametro("usuario");
		UsuarioAcao usuarioAcao              = (UsuarioAcao) getParametro("usuarioAcao");
		Date        periodoRealizacaoInicial = (Date)        getParametro("periodoRealizacaoInicial");
		Date        periodoRealizacaoFinal   = (Date)        getParametro("periodoRealizacaoFinal");
		Date        horarioRealizacaoInicial = (Date)        getParametro("horarioRealizacaoInicial");
		Date        horarioRealizacaoFinal   = (Date)        getParametro("horarioRealizacaoFinal");
		Integer     id1                      = (Integer)     getParametro("id1");
		int         tipoFormatoRelatorio     = (Integer)     getParametro("tipoFormatoRelatorio");
		String      tipoRelatorio            = (String)      getParametro("tipoRelatorio");
		String      unidadeNegocio           = (String)      getParametro("unidadeNegocio");
		Integer     idFuncionalidade         = (Integer)     getParametro("idFuncionalidade");
		Hashtable<String, String> argumentos = (Hashtable<String, String>) getParametro("argumentos");

		
		// valor de retorno
		byte[] retorno = null;

		// Instacia a fachada
		Fachada fachada = Fachada.getInstancia();
		
		Collection colecaoOperacoesEfetuadas =   null;

		colecaoOperacoesEfetuadas = fachada.pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlRelatorio(
		usuarioAcao.getId() , idOperacoes , usuario.getLogin(), periodoRealizacaoInicial , 
		periodoRealizacaoFinal , horarioRealizacaoInicial , horarioRealizacaoFinal , 
		argumentos , id1 , unidadeNegocio );
		
		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioConsultarOperacaoBean relatorioBean = null;
		// se a coleção de parâmetros da analise não for vazia
		if (colecaoOperacoesEfetuadas != null
				&& !colecaoOperacoesEfetuadas.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoOperacoesEfetuadasIterator = colecaoOperacoesEfetuadas
					.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoOperacoesEfetuadasIterator.hasNext()) {

	OperacaoEfetuada operacaoEfetuada = (OperacaoEfetuada) colecaoOperacoesEfetuadasIterator.next();

	FiltroUsuarioAlteracao filtroUsuarioAlteracao = new FiltroUsuarioAlteracao();
	filtroUsuarioAlteracao.adicionarParametro(new ParametroSimples( FiltroUsuarioAlteracao.OPERACAO_EFETUADA_ID ,
						operacaoEfetuada.getId()));
	
	filtroUsuarioAlteracao.adicionarCaminhoParaCarregamentoEntidade("usuario");

	Collection colecaoUsuarioAlteracao = fachada.pesquisar( filtroUsuarioAlteracao ,
			UsuarioAlteracao.class.getName());
	
				String nomeUsuario = "";
				String ipUsuario   = "";

				if ( colecaoUsuarioAlteracao != null
						&& !colecaoUsuarioAlteracao.isEmpty()) {
		UsuarioAlteracao usuarioAlteracao = (UsuarioAlteracao) colecaoUsuarioAlteracao.iterator().next();
	
		nomeUsuario = usuarioAlteracao.getUsuario().getNomeUsuario();

//		ipUsuario = usuarioAlteracao.getUsuario().getIpLogado();
		ipUsuario = usuarioAlteracao.getIpAlteracao();
				}
				
			FiltroTabelaLinhaColunaAlteracao filtroTabelaLinhaColunaAlteracao = new FiltroTabelaLinhaColunaAlteracao();
			filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.TABELA_COLUNA);
			filtroTabelaLinhaColunaAlteracao.adicionarParametro(new ParametroSimples(FiltroTabelaLinhaColunaAlteracao.OPERACAO_EFETUADA_ID, operacaoEfetuada.getId()));
				
Collection<TabelaLinhaColunaAlteracao> colecaoTabelaLinhaColunaAlteracao = (Collection<TabelaLinhaColunaAlteracao>) fachada.pesquisar(filtroTabelaLinhaColunaAlteracao, TabelaLinhaColunaAlteracao.class.getName());
					
	for ( TabelaLinhaColunaAlteracao tabelaLinhaColunaAlteracao : colecaoTabelaLinhaColunaAlteracao ) {

					relatorioBean = new RelatorioConsultarOperacaoBean(
								
							// Data Realização
							operacaoEfetuada.getUltimaAlteracao(),
						
							// Nome da operacao
							operacaoEfetuada.getOperacao().getDescricao(),
						
							// dados
							operacaoEfetuada.getArgumentoValor() + "",
					
							// outros dados
							consultarInformacoesExtrasOperacaoEfetuada(operacaoEfetuada),
						
							// Usuário
							nomeUsuario,
								
							// Id Operação Efetuada
							operacaoEfetuada.getId(),
								
							// Campo
							tabelaLinhaColunaAlteracao.getTabelaColuna().getDescricaoColuna(),
								
							// Conteúdo Anterior
							tabelaLinhaColunaAlteracao.getConteudoColunaAnterior(),
								
							// Conteúdo Atual
							tabelaLinhaColunaAlteracao.getConteudoColunaAtual(),
								
							// Data da Atualização
							tabelaLinhaColunaAlteracao.getUltimaAlteracao(),
							
							ipUsuario
						);

					// adiciona o bean a coleção
					relatorioBeans.add(relatorioBean);
				}
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
	SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		if (tipoRelatorio != null) {
			parametros.put("tipoRelatorio", tipoRelatorio);
		} else {
			parametros.put("tipoRelatorio", "");
		}

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		Operacao operacao = null;
		if (idOperacoes != null && idOperacoes.length == 1){
			FiltroOperacao filtro = new FiltroOperacao();
			filtro.adicionarParametro(new ParametroSimples(FiltroOperacao.ID, idOperacoes[0]));
			Collection operacoes = Fachada.getInstancia().pesquisar(filtro, Operacao.class.getSimpleName());			
			operacao = (Operacao) Util.retonarObjetoDeColecao(operacoes);
			
		}
		if (operacao != null){
			parametros.put("idOperacao", operacao.getId() == null? "": "" + operacao.getId());
			parametros.put("nomeOperacao", operacao.getDescricao());				
		} else {
			parametros.put("idOperacao", "");
			parametros.put("nomeOperacao", "");				
		}
		

		parametros.put("idUsuario", usuario.getId() == null ? "": "" + usuario.getId());

		parametros.put("nomeUsuario", usuario.getNomeUsuario());

		parametros.put("acaoUsuario", usuarioAcao.getDescricao());

		if (periodoRealizacaoInicial != null && periodoRealizacaoFinal != null) {
			parametros.put("periodoRealizacao", "" + Util.formatarData(periodoRealizacaoInicial) + " a " + Util.formatarData(periodoRealizacaoFinal));
		} else {
			parametros.put("periodoRealizacao", "");
		}

		if (horarioRealizacaoInicial != null && horarioRealizacaoFinal != null) {
			parametros.put("horarioRealizacao", "" +Util.formatarHoraSemData(horarioRealizacaoInicial) + " a " + Util.formatarHoraSemData(horarioRealizacaoFinal));
		} else {
			parametros.put("horarioRealizacao", "");
		}
		
		if( idFuncionalidade != null ){
			parametros.put( "idFuncionalidade" , "" +idFuncionalidade );
		}
		else{
			parametros.put( "idFuncionalidade" , "" );
		}
		if( unidadeNegocio != null ){
			parametros.put( "unidadeNegocio" , unidadeNegocio );
		}
		else{
			parametros.put( "unidadeNegocio" , "" );
		}
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

retorno = gerarRelatorio( ConstantesRelatorios.RELATORIO_OPERACAO_EFETUADA , parametros, ds, tipoFormatoRelatorio );
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
persistirRelatorioConcluido( retorno , Relatorio.CONSULTAR_OPERACAO , idFuncionalidadeIniciada );
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
		return 0;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioConsultarOperacao", this);
	}
	
	private String consultarInformacoesExtrasOperacaoEfetuada(OperacaoEfetuada operacaoEfetuada){
		
		String dadosFormatados = "";
		    
		String dadosAdicionais = operacaoEfetuada.getDadosAdicionais();		
		if (dadosAdicionais != null){
			
			StringTokenizer stk = new StringTokenizer(dadosAdicionais,"$");
			
			while (stk.hasMoreElements()) {
				String element = (String) stk.nextElement();
				int ind = element.indexOf(":");
				if (ind != -1){
					String label = element.substring(0,ind);
					String valor = element.substring(ind+1, element.length());
					dadosFormatados += label + "="+valor + ";";
				}
				
			}
			
		}
		return dadosFormatados;
	}	
}
