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

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de Pagamento para Entidades
 * Beneficentes Analítico
 * 
 * @author Daniel Alves
 * @created 25 de Janeiro de 2010.
 */
public class RelatorioPagamentoEntidadesBeneficentesAnalitico extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RelatorioPagamentoEntidadesBeneficentesAnalitico(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_PAGAMENTO_ENTIDADES_BENEFICENTES_ANALITICO);
	}

	@Deprecated
	public RelatorioPagamentoEntidadesBeneficentesAnalitico() {
		super(null, "");
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		Fachada fachada = Fachada.getInstancia();
				
		String mesAnoInicial = (String) getParametro("mesAnoInicial");
		String mesAnoFinal = (String) getParametro("mesAnoFinal");		
		String idEntidadeBeneficente = (String) getParametro("idEntidadeBeneficente");
		String idGerenciaRegional = (String) getParametro("idGerenciaRegional");
		String idUnidadeNegocio = (String) getParametro("idUnidadeNegocio");
		String idLocalidade = (String) getParametro("idLocalidade");		
		int opcaoTotalizacao = (Integer) getParametro("opcaoTotalizacao");
		
		int retorno = 0;		
		
		retorno = (Integer) fachada
  	         .pesquisarPagamentoEntidadesBeneficentesCount(mesAnoInicial, mesAnoFinal, idEntidadeBeneficente, 
  	        		                                 idGerenciaRegional, idUnidadeNegocio, idLocalidade, 
  	        		                                 opcaoTotalizacao, "analitico");
		 if(retorno == 0){
			 throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
		 }
					 
		 return retorno; 
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioPagamentoEntidadesBeneficentesAnalitico", this);
	}

	@Override
	public Object executar() throws TarefaException {
						
		Fachada fachada = Fachada.getInstancia();
		

		byte[] retorno = null;
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		
		String mesAnoInicial = (String) getParametro("mesAnoInicial");
		String mesAnoFinal = (String) getParametro("mesAnoFinal");		
		String idEntidadeBeneficente = (String) getParametro("idEntidadeBeneficente");
		String idGerenciaRegional = (String) getParametro("idGerenciaRegional");
		String idUnidadeNegocio = (String) getParametro("idUnidadeNegocio");
		String idLocalidade = (String) getParametro("idLocalidade");		
		int opcaoTotalizacao = (Integer) getParametro("opcaoTotalizacao");
				
		Collection<RelatorioPagamentoEntidadesBeneficentesAnaliticoBean> beansRelatorio = 
			(Collection<RelatorioPagamentoEntidadesBeneficentesAnaliticoBean>) fachada
  	         .pesquisarPagamentoEntidadesBeneficentesAnalitico(mesAnoInicial, mesAnoFinal, idEntidadeBeneficente, 
  	        		                                 idGerenciaRegional, idUnidadeNegocio, idLocalidade, 
  	        		                                 opcaoTotalizacao);				
		
		if(beansRelatorio != null){			
			
			int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
			
			//Parâmetros do relatório
			Map parametros = new HashMap();
			
			
			//Recupera o AnoMesFaturamento de Sistema Parametro
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			
			parametros.put("imagem", sistemaParametro.getImagemRelatorio());
			parametros.put("periodoInicial", mesAnoInicial);
			parametros.put("periodoFinal", mesAnoFinal);
			parametros.put("opcaoTotalizacao", opcaoTotalizacao);
			parametros.put("useCase", "R0978");
	
			RelatorioDataSource ds = new RelatorioDataSource((List) beansRelatorio);
	
			// exporta o relatório em pdf e retorna o array de bytes
			retorno = gerarRelatorio(
					ConstantesRelatorios.RELATORIO_PAGAMENTO_ENTIDADES_BENEFICENTES_ANALITICO,
					parametros, ds, tipoFormatoRelatorio);
		}else {
			throw new ActionServletException(
            "atencao.pesquisa.nenhumresultado");
		}
		// ------------------------------------
		//  Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_PAGAMENTO_ENTIDADES_BENEFICENTES_ANALITICO, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------
		// retorna o relatório gerado
		
		return retorno;
		
	}

}
