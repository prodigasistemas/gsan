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
		// TODO Auto-generated method stub
		return 0;
	}

	public void agendarTarefaBatch() {
	}
}