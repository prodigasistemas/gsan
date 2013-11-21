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
package gcom.relatorio.micromedicao.hidrometro;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.micromedicao.hidrometro.FiltroRetornoControleHidrometro;
import gcom.micromedicao.hidrometro.RetornoControleHidrometro;
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
import java.util.List;
import java.util.Map;


public class RelatorioManterRetornoControleHidrometro extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterRetornoControleHidrometro(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_RETORNO_CONTROLE_HIDROMETRO);
	}
	
	@Deprecated
	public RelatorioManterRetornoControleHidrometro() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param localidades
	 *            Description of the Parameter
	 * @param localidadeParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroRetornoControleHidrometro filtroRetornoControleHidrometro = (FiltroRetornoControleHidrometro) getParametro("filtroRetornoControleHidrometro");
		RetornoControleHidrometro retornoControleHidrometroParametros = (RetornoControleHidrometro) getParametro("retornoControleHidrometroParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterRetornoControleHidrometroBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		filtroRetornoControleHidrometro.setConsultaSemLimites(true);

		Collection<RetornoControleHidrometro> colecaoRetornoControleHidrometros = fachada.pesquisar(filtroRetornoControleHidrometro,
				RetornoControleHidrometro.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoRetornoControleHidrometros != null && !colecaoRetornoControleHidrometros.isEmpty()) {

			// laço para criar a coleção de parâmetros da analise
			for (RetornoControleHidrometro retornoControleHidrometro : colecaoRetornoControleHidrometros) {

				String ativoInativo = "";

				if ( retornoControleHidrometro.getIndicadorUso().equals( ConstantesSistema.INDICADOR_USO_ATIVO ) ){
					ativoInativo = "Ativo";
				} else if ( retornoControleHidrometro.getIndicadorUso().equals( ConstantesSistema.INDICADOR_USO_DESATIVO ) ){
					ativoInativo = "Inativo";
				} else {
					ativoInativo = "Todos";
				}
				
				String indicadorGeracao = "";
				if ( retornoControleHidrometro.getIndicadorGeracao().equals( ConstantesSistema.INDICADOR_USO_ATIVO ) ){
					indicadorGeracao = "Sim";
				} else if ( retornoControleHidrometro.getIndicadorGeracao().equals( ConstantesSistema.INDICADOR_USO_DESATIVO ) ){
					indicadorGeracao = "Não";
				}
				
				relatorioBean = new RelatorioManterRetornoControleHidrometroBean(
						// Código
						retornoControleHidrometro.getId().toString(),					 
						
						// Descrição
						retornoControleHidrometro.getDescricao(),					
						
						
						// Indicador de Geração
						indicadorGeracao,
						
						// Indicador de Uso
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

		if (retornoControleHidrometroParametros.getId() != null) {
			parametros.put("id",
					retornoControleHidrometroParametros.getId().toString());
		} else {
			parametros.put("id", "");
		}

		if ( retornoControleHidrometroParametros.getDescricao() != null &&
				!retornoControleHidrometroParametros.getDescricao().equals("") ) {
			
			parametros.put("descricao", retornoControleHidrometroParametros.getDescricao());
		} else {
			parametros.put("descricao", "" );
		}
		
		if ( retornoControleHidrometroParametros.getIndicadorGeracao() != null &&
				!retornoControleHidrometroParametros.getIndicadorGeracao().equals("") ) {
			parametros.put("indicadorDeGeracao", retornoControleHidrometroParametros.getIndicadorGeracao().toString());
		} else {
			parametros.put("indicadorDeGeracao", "");
		}
		
		String indicadorUso = "";

		if (retornoControleHidrometroParametros.getIndicadorUso() != null
				&& !retornoControleHidrometroParametros.getIndicadorUso().equals("")) {
			if (retornoControleHidrometroParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (retornoControleHidrometroParametros.getIndicadorUso().equals(
					new Short("2"))) {
				indicadorUso = "Inativo";
			} else if (retornoControleHidrometroParametros.getIndicadorUso().equals("3")) {
				indicadorUso = "Todos";
			}


		}
		parametros.put("indicadorUso", indicadorUso);
		
		String indicadorGeracao = "";

		if (retornoControleHidrometroParametros.getIndicadorGeracao() != null
				&& !retornoControleHidrometroParametros.getIndicadorGeracao().equals("")) {
			if (retornoControleHidrometroParametros.getIndicadorGeracao().equals(new Short("1"))) {
				indicadorGeracao = "Sim";
			} else if (retornoControleHidrometroParametros.getIndicadorGeracao().equals(
					new Short("2"))) {
				indicadorUso = "Não";
			} 
		}


		parametros.put("indicadorGeracao", indicadorGeracao);

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_RETORNO_CONTROLE_HIDROMETRO, parametros,
				ds, tipoFormatoRelatorio);
		
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 1;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterRetornoControleHidrometro", this);
	}

}