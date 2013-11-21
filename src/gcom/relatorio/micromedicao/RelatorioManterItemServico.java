/*
* Copyright (C) 2007-2007 the GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place – Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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