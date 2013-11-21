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

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadador;
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

public class RelatorioManterArrecadador extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioManterArrecadador(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_ARRECADADOR);
	}
	
	@Deprecated
	public RelatorioManterArrecadador() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param situacao arrecadador
	 *            Description of the Parameter
	 * @param arrecadadorParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		FiltroArrecadador filtroArrecadador = (FiltroArrecadador) getParametro("filtroArrecadador");
		
		Arrecadador arrecadadorParametros = (Arrecadador) getParametro("arrecadadorParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterArrecadadorBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoArrecadador = fachada.pesquisar(filtroArrecadador,
				Arrecadador.class.getName());

		
		
		// se a coleção de parâmetros da analise não for vazia
		if (colecaoArrecadador != null && !colecaoArrecadador.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator arrecadadorIterator = colecaoArrecadador.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (arrecadadorIterator.hasNext()) {

				Arrecadador arrecadador = (Arrecadador) arrecadadorIterator.next();
				
				String codigoAgente = "";
				if(arrecadador.getCodigoAgente()!= null 
						&& !arrecadador.getCodigoAgente().equals("")){
					
					codigoAgente = arrecadador.getCodigoAgente() + "";
					
				}
					
				String idCliente = "";
				if(arrecadador.getCliente().getId() != null
						&& !arrecadador.getCliente().getId().equals("")){
					
					idCliente = arrecadador.getCliente().getId() + "";
					
				}
				
				String nomeCliente = "";
				if(arrecadador.getCliente().getNome() != null
						&& !arrecadador.getCliente().getNome().equals("")){
					
					nomeCliente = arrecadador.getCliente().getNome();
					
				}
				
				String idImovel = "";
				String inscricaoEstadual = "";
				if(arrecadador.getImovel() != null
						&& !arrecadador.getImovel().equals("")){
					
					idImovel = arrecadador.getImovel().getId()+"";
					inscricaoEstadual = arrecadador.getImovel().getInscricaoFormatada() + "";
					
				}
				
				
//				String inscricaoEstadual = "";
//				if(arrecadador.getImovel().getInscricaoFormatada() != null
//						&& !arrecadador.getImovel().getInscricaoFormatada().equals("")){
//					
//					inscricaoEstadual = arrecadador.getImovel().getInscricaoFormatada() + "";
//					
//				}
				
				relatorioBean = new RelatorioManterArrecadadorBean(
						
						//Agente
						
						codigoAgente,
						
						//ID Cliente
						
						idCliente,
						
						//Nome cliente
						
						nomeCliente,
						
						//Imóvel
						
						idImovel,
						
						//Inscrição Estadual
						
						inscricaoEstadual );
						
						
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

		//Código do Agente
		if ( arrecadadorParametros.getCodigoAgente() != null
				&& arrecadadorParametros.getCodigoAgente().equals("")){
			parametros.put("codigoAgente", arrecadadorParametros.getCodigoAgente());
		}
		
		//Cliente
		if( arrecadadorParametros.getCliente() != null
				&& !arrecadadorParametros.getCliente().equals("")){
			
			parametros.put("cliente",
				arrecadadorParametros.getCliente().getId());
		
		}
		
		//Imóvel Id e Inscrição Estadual
		if( arrecadadorParametros.getImovel() != null
				&& !arrecadadorParametros.getImovel().equals("")){
			if ( arrecadadorParametros.getImovel().getId() != null
					&& !arrecadadorParametros.getImovel().getId().equals("")){
				
				parametros.put("imovel", arrecadadorParametros.getImovel().getId());
			
			}
			
			if ( arrecadadorParametros.getImovel().getInscricaoFormatada() != null
					&& !arrecadadorParametros.getImovel().getInscricaoFormatada().equals("")){
				
				parametros.put("inscricaoEstadual", new String( arrecadadorParametros.getImovel().getInscricaoFormatada()));
				
			}
		}
		
		//Indicador de Uso
		
		parametros.put("indicadorUso", new String(arrecadadorParametros.getIndicadorUso().toString()));
		
		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_ARRECADADOR, parametros,
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
		
		AgendadorTarefas.agendarTarefa("RelatorioManterArrecadador", this);
	
	}

}