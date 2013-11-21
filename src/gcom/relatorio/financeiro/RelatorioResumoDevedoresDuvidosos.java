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
package gcom.relatorio.financeiro;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.financeiro.bean.ResumoDevedoresDuvidososRelatorioHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * [UC0487] Gerar Relatório de Resumo de Devedores Duvidosos
 * 
 * @author Vivianne Sousa
 * @created 20/07/2007
 */
public class RelatorioResumoDevedoresDuvidosos extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioResumoDevedoresDuvidosos (Usuario usuario) {
		super(usuario,ConstantesRelatorios.RELATORIO_RESUMO_DEVEDORES_DUVIDOSOS);
	}


	private Collection<RelatorioResumoDevedoresDuvidososBean> inicializarBeanRelatorio(
			Collection listaCamposConsultaRelatorio) {

		Iterator iterator = listaCamposConsultaRelatorio.iterator();

		Collection<RelatorioResumoDevedoresDuvidososBean> retorno = new ArrayList();

		while (iterator.hasNext()) {
			ResumoDevedoresDuvidososRelatorioHelper resumoDevedoresDuvidososRelatorioHelper = 
				(ResumoDevedoresDuvidososRelatorioHelper) iterator.next();
			
			RelatorioResumoDevedoresDuvidososBean relatorioResumoDevedoresDuvidososBean = 
				new RelatorioResumoDevedoresDuvidososBean(
						resumoDevedoresDuvidososRelatorioHelper.getValorItemDevedoresDuvidosos(),
						resumoDevedoresDuvidososRelatorioHelper.getDescricaoTipoLancamento(),
						resumoDevedoresDuvidososRelatorioHelper.getDescricaoItemLancamento(),
						resumoDevedoresDuvidososRelatorioHelper.getDescricaoItemContabil(),
						resumoDevedoresDuvidososRelatorioHelper.getIndicadorImpressao(),
						resumoDevedoresDuvidososRelatorioHelper.getIndicadorTotal(),
						resumoDevedoresDuvidososRelatorioHelper.getLancamentoTipo(),
						resumoDevedoresDuvidososRelatorioHelper.getLancamentoTipoSuperior(),
						false,
						resumoDevedoresDuvidososRelatorioHelper.getDescricaoGerencia(),
						resumoDevedoresDuvidososRelatorioHelper.getGerencia(),
						resumoDevedoresDuvidososRelatorioHelper.getDescricaoLocalidade(),
						resumoDevedoresDuvidososRelatorioHelper.getLocalidade(),
						resumoDevedoresDuvidososRelatorioHelper.getDescLancamentoTipoSuperior(),
						resumoDevedoresDuvidososRelatorioHelper.getDescricaoUnidadeNegocio(),
						resumoDevedoresDuvidososRelatorioHelper.getUnidadeNegocio());

			retorno.add(relatorioResumoDevedoresDuvidososBean);
			
			
		}		


		return retorno;
	}

	
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		String opcaoTotalizacao = (String) getParametro("opcaoTotalizacao");
		int mesAno = (Integer) getParametro("mesAnoInteger");
		Integer codigoLocalidade = (Integer) getParametro("localidade");
		Integer codigoGerencia = (Integer) getParametro("gerenciaRegional");
		Integer unidadeNegocio = (Integer) getParametro("unidadeNegocio");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map parametros = new HashMap();

		Fachada fachada = Fachada.getInstancia();

		Collection dadosRelatorio = fachada
				.consultarResumoDevedoresDuvidososRelatorio(opcaoTotalizacao, mesAno,
						codigoGerencia, codigoLocalidade, unidadeNegocio);

		Collection<RelatorioResumoDevedoresDuvidososBean> colecaoBean = this
				.inicializarBeanRelatorio(dadosRelatorio);

		Integer lancamentoTipoAnterior = null;
		
		for (Iterator<RelatorioResumoDevedoresDuvidososBean> iter = colecaoBean
				.iterator(); iter.hasNext();) {
			RelatorioResumoDevedoresDuvidososBean bean = iter.next();

			// Se o tipo de lançamento for subordinado a outro tipo de
			// lançamento
			if (!bean.getLancamentoTipo().equals(bean.getLancamentoTipoSuperior())) {

				// Recua a descrição neste caso
				bean.setDescricaoTipoLancamento("    "	+ bean.getDescricaoTipoLancamento());

				bean.setDescricaoItemLancamento("    " 	+ bean.getDescricaoItemLancamento());

				if (bean.getDescricaoItemContabil() != null){
					bean.setDescricaoItemContabil("    "  + bean.getDescricaoItemContabil());	
				}
 
				if (lancamentoTipoAnterior != null && !lancamentoTipoAnterior.equals(bean.getLancamentoTipoSuperior())){
					String descricaoLancamentoTipoSuperior = fachada.obterDescricaoLancamentoTipo(bean.getLancamentoTipoSuperior());
					bean.setDescLancamentoTipoSuperior(descricaoLancamentoTipoSuperior);
				}
				
			}
			
			lancamentoTipoAnterior = bean.getLancamentoTipo();
			
			// Se o indicador impressão não estiver setado então a linha de
			// detalhe não aparecerá no relatório
			if (bean.getIndicadorImpressao() == null || !bean.getIndicadorImpressao().toString().equals("1")) {
				bean.setDescricaoTipoLancamento("");
				//iter.remove();
				continue;
			}
			
		}

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		String opcaoTotalizacaoDesc = "";
		if (opcaoTotalizacao.equalsIgnoreCase("estado")) {
			opcaoTotalizacaoDesc = "Estado";
		} else if (opcaoTotalizacao.equalsIgnoreCase("estadoGerencia")) {
			opcaoTotalizacaoDesc = "Estado por Gerência Regional";
		} else if (opcaoTotalizacao.equalsIgnoreCase("estadoLocalidade")) {
			opcaoTotalizacaoDesc = "Estado por Localidade";
		} else if (opcaoTotalizacao.equalsIgnoreCase("gerenciaRegional")) {
			opcaoTotalizacaoDesc = "Gerência Regional";
		} else if (opcaoTotalizacao.equalsIgnoreCase("gerenciaRegionalLocalidade")) {
			opcaoTotalizacaoDesc = "Gerência Regional por Localidade";
		} else if (opcaoTotalizacao.equalsIgnoreCase("localidade")) {
			opcaoTotalizacaoDesc = "Localidade";
		} else if (opcaoTotalizacao.equals("estadoUnidadeNegocio")) {
			opcaoTotalizacaoDesc = "Estado por Unidade de Negócio";
		} else if (opcaoTotalizacao.equals("unidadeNegocio")) {
			opcaoTotalizacaoDesc = "Unidade de Negócio";
		}

		parametros.put("opcaoTotalizacaoDesc", opcaoTotalizacaoDesc);

		String mesAnoString = "" + mesAno;
		if (mesAnoString.length() == 5) {
			mesAnoString = "0" + mesAnoString;
		}
		mesAnoString = mesAnoString.substring(0, 2) + "/"
				+ mesAnoString.substring(2, 6);

		parametros.put("mesAnoArrecadacao", mesAnoString);

		parametros.put("tipoFormatoRelatorio", "");

		if (opcaoTotalizacao.equalsIgnoreCase("unidadeNegocio") || opcaoTotalizacao.equalsIgnoreCase("estadoUnidadeNegocio")) {
			parametros.put("agrupaPorUnidadeNegocio", "1");
		}else if (opcaoTotalizacao.equalsIgnoreCase("estadoGerencia")) {
			parametros.put("agrupaPorGerencia", "1");
		} else {
			parametros.put("agrupaPorGerencia", "0");
		}
		
			
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_RESUMO_DEVEDORES_DUVIDOSOS, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RESUMO_DEVEDORES_DUVIDOSOS,idFuncionalidadeIniciada);
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

//		totalRegistrosRel = fachada
//				.consultarQtdeRegistrosResumoDevedoresDuvidososRelatorio(mesAno, idLocalidade, 
//			    		idGerencia, opcaoTotalizacao);
//		return totalRegistrosRel.intValue();
		return 1;
	}


	public void agendarTarefaBatch() {
	}
}
