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
package gcom.relatorio.arrecadacao;

import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.FiltroAgencia;
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

public class RelatorioManterAgenciaBancaria extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioManterAgenciaBancaria(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_AGENCIA_BANCARIA);
	}
	
	@Deprecated
	public RelatorioManterAgenciaBancaria() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param situacao agenciaBancaria
	 *            Description of the Parameter
	 * @param agenciaBancariaParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		FiltroAgencia filtroAgenciaBancaria = (FiltroAgencia) getParametro("filtroAgenciaBancaria");
		
		Agencia agenciaBancariaParametros = (Agencia) getParametro("agenciaBancariaParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterAgenciaBancariaBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoAgenciaBancaria = fachada.pesquisar(filtroAgenciaBancaria,
				Agencia.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoAgenciaBancaria != null && !colecaoAgenciaBancaria.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator agenciaBancariaIterator = colecaoAgenciaBancaria.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (agenciaBancariaIterator.hasNext()) {

				Agencia agenciaBancaria = (Agencia) agenciaBancariaIterator.next();
				
				relatorioBean = new RelatorioManterAgenciaBancariaBean(
						
						//Cod. Banco
						
						agenciaBancaria.getBanco().getId(),
						
						//Cod. Agencia
						
						agenciaBancaria.getCodigoAgencia(),
						
						//Nome Agencia
						
						agenciaBancaria.getNomeAgencia() );
						
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

		//Banco
		if(agenciaBancariaParametros.getBanco() != null
				&& !agenciaBancariaParametros.getBanco().equals("")){
			parametros.put("banco", agenciaBancariaParametros.getBanco().getDescricao());
		}
		//Codigo Agencia
		if(agenciaBancariaParametros.getCodigoAgencia() != null 
				&& !agenciaBancariaParametros.getCodigoAgencia().equals("")){
			
			parametros.put("codigoAgencia",
				agenciaBancariaParametros.getCodigoAgencia() == null ? "" : ""
						+ agenciaBancariaParametros.getCodigoAgencia());
		}
		//Nome Agencia
		if( agenciaBancariaParametros.getNomeAgencia() != null
				&& !agenciaBancariaParametros.getNomeAgencia().equals("")){
			parametros.put("nomeAgencia", agenciaBancariaParametros.getNomeAgencia());
		}
		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_AGENCIA_BANCARIA, parametros,
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
		
		AgendadorTarefas.agendarTarefa("RelatorioManterAgenciaBancaria", this);
	
	}

}