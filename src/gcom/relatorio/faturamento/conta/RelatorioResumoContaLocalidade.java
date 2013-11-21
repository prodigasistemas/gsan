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
package gcom.relatorio.faturamento.conta;

import gcom.batch.Relatorio;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.MapaControleContaRelatorioHelper;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * [UC] 
 * @author Flavio Cordeiro
 * @date 14/02/2007
 */

public class RelatorioResumoContaLocalidade extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioResumoContaLocalidade(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RESUMO_CONTA_LOCALIDADE);
	}
	
	private Collection inicializarBeanRelatorio(
			Collection colecaoResumoContaLocalidadeConta) {
		
		Collection retorno = new ArrayList();

		Integer qtdTotalPublico =0;
		Integer idLocalidadeAnterior = 0;
		Integer qtdTotalFirma = 0;
		Integer qtdTotalCorreios = 0;
		Integer qtdTotalContas = 0;
		Integer qtdTotalMacro = 0;
		Integer qtdTotalGeralFirma = 0;
		Integer qtdTotalGeralCorreio = 0;
		Integer qtdTotalGeralPublico = 0;
		Integer qtdTotalGeral = 0;
		
		boolean primeirVez = true;
		
		if(colecaoResumoContaLocalidadeConta != null && !colecaoResumoContaLocalidadeConta.isEmpty()){
			
			Iterator iter = colecaoResumoContaLocalidadeConta.iterator();
			
			while (iter.hasNext()) {
			
				MapaControleContaRelatorioHelper mapaControleContaRelatorioHelper = (MapaControleContaRelatorioHelper) iter.next();
				
				qtdTotalContas = 0;
				qtdTotalMacro = 0;
				
				if(mapaControleContaRelatorioHelper.getIdLocalidade() != null){
					Integer idLocalidadeAtual = new Integer(mapaControleContaRelatorioHelper.getIdLocalidade());
					
					if(primeirVez){
						primeirVez = false;
						idLocalidadeAnterior = idLocalidadeAtual;
					}
					//laço para somar as contas 
					while(idLocalidadeAtual.equals(idLocalidadeAnterior) && iter.hasNext()){
						if (idLocalidadeAtual.equals(idLocalidadeAnterior) 
								&& mapaControleContaRelatorioHelper.getIdEsferaPoder() != null
								&& !mapaControleContaRelatorioHelper.getIdEsferaPoder().equals(new Integer(EsferaPoder.PARTICULAR+""))){
							idLocalidadeAnterior = idLocalidadeAtual;

							qtdTotalPublico = qtdTotalPublico + mapaControleContaRelatorioHelper.getQtdeContas();
							qtdTotalGeralPublico = qtdTotalGeralPublico + mapaControleContaRelatorioHelper.getQtdeContas();							
						}
						
						if(mapaControleContaRelatorioHelper.getIdEsferaPoder() == null){
							
							qtdTotalFirma = mapaControleContaRelatorioHelper.getQtdeContas();
							qtdTotalGeralFirma = qtdTotalGeralFirma + mapaControleContaRelatorioHelper.getQtdeContas();
							
						}else if(mapaControleContaRelatorioHelper.getIdEsferaPoder().equals(new Integer(EsferaPoder.PARTICULAR+""))){
							
							qtdTotalCorreios = mapaControleContaRelatorioHelper.getQtdeContas();
							qtdTotalGeralCorreio = qtdTotalGeralCorreio + mapaControleContaRelatorioHelper.getQtdeContas();
						}
						
						qtdTotalMacro = qtdTotalMacro + mapaControleContaRelatorioHelper.getQtdTotalMacro();
						qtdTotalContas = qtdTotalContas + mapaControleContaRelatorioHelper.getQtdeContas();
						
						mapaControleContaRelatorioHelper = (MapaControleContaRelatorioHelper) iter.next();
						idLocalidadeAtual = new Integer(mapaControleContaRelatorioHelper.getIdLocalidade());
					}			
					
					if (!iter.hasNext()) {
						
						if (!idLocalidadeAtual.equals(idLocalidadeAnterior)){
							qtdTotalContas = 0;
							qtdTotalMacro = 0;
						}
						
						if (mapaControleContaRelatorioHelper.getIdEsferaPoder() != null
								&& !mapaControleContaRelatorioHelper.getIdEsferaPoder().equals(new Integer(EsferaPoder.PARTICULAR+""))){
							qtdTotalPublico = qtdTotalPublico + mapaControleContaRelatorioHelper.getQtdeContas();
							qtdTotalGeralPublico = qtdTotalGeralPublico + mapaControleContaRelatorioHelper.getQtdeContas();
							
						}
						
						if(mapaControleContaRelatorioHelper.getIdEsferaPoder() == null){
							
							qtdTotalFirma = mapaControleContaRelatorioHelper.getQtdeContas();
							qtdTotalGeralFirma = qtdTotalGeralFirma + mapaControleContaRelatorioHelper.getQtdeContas();
							
						}else if(mapaControleContaRelatorioHelper.getIdEsferaPoder().equals(new Integer(EsferaPoder.PARTICULAR+""))){
							
							qtdTotalCorreios = mapaControleContaRelatorioHelper.getQtdeContas();
							qtdTotalGeralCorreio = qtdTotalGeralCorreio + mapaControleContaRelatorioHelper.getQtdeContas();
							
						}						
					}
						qtdTotalContas = qtdTotalContas + mapaControleContaRelatorioHelper.getQtdeContas();
						
						qtdTotalGeral = qtdTotalGeral + qtdTotalContas;
						
						RelatorioMapaControleContaBean relatorioResumoContaLocalidadeBean = 
							new RelatorioMapaControleContaBean(
									"" + mapaControleContaRelatorioHelper.getIdEmpresa(),
									mapaControleContaRelatorioHelper.getNomeEmpresa(),
									"" + Util.adicionarZerosEsquedaNumero(3,idLocalidadeAtual.toString()),
									"" + mapaControleContaRelatorioHelper.getSequencialInicial(),
									"" + mapaControleContaRelatorioHelper.getSequencialFinal(),
									"" + mapaControleContaRelatorioHelper.getIdEsferaPoder(),
									"" + qtdTotalFirma,
									"" + qtdTotalCorreios,
									"" + qtdTotalPublico,
									"" + qtdTotalMacro,
									"" + qtdTotalContas,
									"" + qtdTotalGeralFirma,
									"" + qtdTotalGeralCorreio,
									"" + qtdTotalGeralPublico,									
									"" + qtdTotalGeral);
						
						retorno.add(relatorioResumoContaLocalidadeBean);
					
					
					primeirVez = true;
				}
			}
		}

		return retorno;
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 * 
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		Fachada fachada = Fachada.getInstancia();

		String mesAno = (String) getParametro("mesAno");
		String idGrupoFaturamento = (String) getParametro("idGrupoFaturamento");
		Empresa firma = (Empresa) getParametro("firma");
		String vencimento = (String) getParametro("vencimento");
		Collection colecaoResumoContaLocalidadeConta = (Collection) getParametro("colecaoResumoContaLocalidadeConta");
				
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map<String, String> parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
		parametros.put("mesAno",mesAno);
		parametros.put("vencimento", vencimento);
		parametros.put("idGrupoFaturamento",idGrupoFaturamento);
		if (firma != null) {
			parametros.put("firma", firma.getDescricao());
		} else {
			parametros.put("firma", null);
		}
		
		Collection dadosRelatorio = colecaoResumoContaLocalidadeConta;

		Collection<RelatorioMapaControleContaBean> colecaoBean = this
						.inicializarBeanRelatorio(dadosRelatorio);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);

		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_RESUMO_CONTA_LOCALIDADE, 
				parametros, ds,	tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RESUMO_CONTA_LOCALIDADE,
					idFuncionalidadeIniciada);
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
//		Integer mesAnoInteger = (Integer) getParametro("mesAnoInteger");
//		Integer idGrupoFaturamento = (Integer) getParametro("idGrupoFaturamento");
//		Collection idEsferaPoder = (Collection) getParametro("colecaoIdEsferaPoder");
		
		
		return 0;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioMapaControleConta", this);
	}
	
}
