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

import gcom.arrecadacao.debitoautomatico.DebitoAutomatico;
import gcom.arrecadacao.debitoautomatico.FiltroDebitoAutomatico;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
 * @author Rafael Corrêa
 * @version 1.0
 */

public class RelatorioManterDebitoAutomatico extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioManterDebitoAutomatico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_DEBITO_AUTOMATICO);
	}
	
	public Object executar() throws TarefaException {

		FiltroDebitoAutomatico filtroDebitoAutomatico = (FiltroDebitoAutomatico) getParametro("filtroDebitoAutomatico");
		DebitoAutomatico debitoAutomaticoParametros = (DebitoAutomatico) getParametro("debitoAutomaticoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		byte[] retorno = null;

		List relatorioBeans = new ArrayList();

		RelatorioManterDebitoAutomaticoBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		filtroDebitoAutomatico.setConsultaSemLimites(true);

		Collection<DebitoAutomatico> colecaoDebitosAutomaticos = fachada.pesquisar(filtroDebitoAutomatico,DebitoAutomatico.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoDebitosAutomaticos != null && !colecaoDebitosAutomaticos.isEmpty()) {

			// laço para criar a coleção de parâmetros da analise
			for (DebitoAutomatico debitoAutomatico : colecaoDebitosAutomaticos) {

				String matricula = "";
				String banco= "";
				String codigoAgencia = "";
				String nomeCliente = "";
				
				if(debitoAutomatico.getImovel()!=null){
					matricula = Util.retornaMatriculaImovelFormatada( debitoAutomatico.getImovel().getId());
					Cliente cliente = Fachada.getInstancia().pesquisarClienteUsuarioImovelExcluidoOuNao( debitoAutomatico.getImovel().getId() );
					nomeCliente = cliente.getNome();
				}
				if(debitoAutomatico.getAgencia()!=null){
					codigoAgencia = debitoAutomatico.getAgencia().getCodigoAgencia();
					
					if(debitoAutomatico.getAgencia().getBanco()!=null){
						banco = debitoAutomatico.getAgencia().getBanco().getDescricaoAbreviada();
					}
				}
				relatorioBean = new RelatorioManterDebitoAutomaticoBean(matricula,banco,codigoAgencia,nomeCliente); 
						
				relatorioBeans.add(relatorioBean);				
			}			
		}

		Map parametros = new HashMap();

		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		if(debitoAutomaticoParametros.getImovel()!=null){
			parametros.put("matricula",Util.retornaMatriculaImovelFormatada(debitoAutomaticoParametros.getImovel().getId()));
		}
		if(debitoAutomaticoParametros.getAgencia()!=null){
			if(debitoAutomaticoParametros.getAgencia().getId()!=null){
				parametros.put("agencia",debitoAutomaticoParametros.getAgencia().getCodigoAgencia() + " - "+ debitoAutomaticoParametros.getAgencia().getNomeAgencia());				
			}
			
			if(debitoAutomaticoParametros.getAgencia().getBanco()!=null){
				parametros.put("banco",debitoAutomaticoParametros.getAgencia().getBanco().getId() + " - " + debitoAutomaticoParametros.getAgencia().getBanco().getDescricaoAbreviada());
			}
		}

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_MANTER_DEBITO_AUTOMATICO, parametros,ds, tipoFormatoRelatorio);

		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterDebitoAutomatico", this);
	}

}