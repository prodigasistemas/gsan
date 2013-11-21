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
package gcom.relatorio.cobranca.parcelamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [UC0878] Gerar Relação de Parcelamento - Visão Analitica
 * 
 * Classe que cria o pdf do relatorio
 * 
 * @author Bruno Barros
 * @date 04/02/2009
 *
 */
public class RelatorioRelacaoParcelamentoAnalitico extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RelatorioRelacaoParcelamentoAnalitico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RELACAO_PARCELAMENTO);
	}
	
	@Deprecated
	public RelatorioRelacaoParcelamentoAnalitico() {
		super(null, "");
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {
		
		Collection dadosRelatorio = (Collection)getParametro("colecaoRelacaoParcelamentoAnalitico");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String cabecalho = (String)getParametro("cabecalho");
		//String parametrosFiltro = (String)getParametro("parametros");
		
		String gerencia = (String)getParametro("parametrosGerencia");
		String unidadeOrganizacional = (String)getParametro("parametroUnidadeOrganizacional");
		String unidadeNegocio = (String)getParametro("parametroUnidadeNegocio");
		String elo = (String)getParametro("parametroElo");
		String periodoParcelamento = (String)getParametro("parametroPeriodo");
		String usuarioParcelamento = (String)getParametro("parametroUsuario");
		String perfilImovel = (String)getParametro("parametroPerfilImovel");
		String valorParcelamento = (String)getParametro("parametroValor");
		String municipio = (String)getParametro("municipio");
		
		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();
		
		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());		
		parametros.put("cabecalho", cabecalho);
		//parametros.put("parametros", parametrosFiltro );
		parametros.put("numeroRelatorio", "R0878");
		
		parametros.put("parametrosGerencia", gerencia );
		parametros.put("parametroUnidadeOrganizacional", unidadeOrganizacional );
		parametros.put("parametroUnidadeNegocio", unidadeNegocio );
		parametros.put("parametroElo", elo );
		parametros.put("parametroPeriodo", periodoParcelamento );
		parametros.put("parametroUsuario", usuarioParcelamento );
		parametros.put("parametroPerfilImovel", perfilImovel );
		parametros.put("parametroValor", valorParcelamento );
		parametros.put("parametroMunicipio", municipio);
		
		if (dadosRelatorio == null || dadosRelatorio.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((List) dadosRelatorio);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_RELACAO_PARCELAMENTO_ANALITICO,
				parametros, ds, tipoFormatoRelatorio);

		// retorna o relatório gerado
		return retorno;

	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioRelacaoParcelamento", this);
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return -1;
	}
}