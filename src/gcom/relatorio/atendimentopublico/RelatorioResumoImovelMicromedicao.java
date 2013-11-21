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
package gcom.relatorio.atendimentopublico;

import gcom.batch.Relatorio;
import gcom.cadastro.imovel.bean.ImovelMicromedicao;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
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
import java.util.List;
import java.util.Map;

/**
 * @author Ana Maria
 * @date 14/02/2007
 * 
 */
public class RelatorioResumoImovelMicromedicao extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioResumoImovelMicromedicao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RESUMO_IMOVEL_MICROMEDICAO);
	}
	
	@Deprecated
	public RelatorioResumoImovelMicromedicao() {
		super(null, "");
	}

	private Collection<RelatorioResumoImovelMicromedicaoBean> inicializarBeanRelatorio(
			Collection dadosRelatorio) {

		Collection<RelatorioResumoImovelMicromedicaoBean> retorno = new ArrayList();
		
		//Histórico Medição e Consumo
		Iterator iterator = dadosRelatorio.iterator();
		while (iterator.hasNext()) {
			
			ImovelMicromedicao imovelMicromedicao = (ImovelMicromedicao)iterator.next();
			
			String mesAnoMedicao = "";
			
			if (imovelMicromedicao.getMedicaoHistorico().getMesAno() != null) {
				mesAnoMedicao = ""+imovelMicromedicao.getMedicaoHistorico().getMesAno();
			} else {
				mesAnoMedicao = ""+imovelMicromedicao.getConsumoHistorico().getMesAno();
			}
			
			String dataLeituraInformada =  "";
			if(imovelMicromedicao.getMedicaoHistorico().getDataLeituraAtualInformada() != null){
				dataLeituraInformada = Util.formatarData(imovelMicromedicao.getMedicaoHistorico().getDataLeituraAtualInformada());
			}
			
			String leituraInformada = "";
			if (imovelMicromedicao.getMedicaoHistorico().getLeituraAtualInformada() != null){
				leituraInformada = ""+ imovelMicromedicao.getMedicaoHistorico().getLeituraAtualInformada();
			}
			
			String dataLeituraFaturada = "" ;
			if (imovelMicromedicao.getMedicaoHistorico().getDataLeituraAtualFaturamento() != null){
				dataLeituraFaturada = Util.formatarData(imovelMicromedicao.getMedicaoHistorico().getDataLeituraAtualFaturamento());
			}
			
			String leituraFaturada = "";
			if (imovelMicromedicao.getMedicaoHistorico().getLeituraAtualFaturamento() != 0){
				leituraFaturada = "" + imovelMicromedicao.getMedicaoHistorico().getLeituraAtualFaturamento();
			}
			
			String consumo = "";
			if(imovelMicromedicao.getConsumoHistorico().getNumeroConsumoFaturadoMes() != null){
				consumo = "" + imovelMicromedicao.getConsumoHistorico().getNumeroConsumoFaturadoMes();
			}
			
			String media = "";
			if(imovelMicromedicao.getConsumoHistorico().getConsumoMedio() != null){
				media= "" + imovelMicromedicao.getConsumoHistorico().getConsumoMedio();
			}
			
			String anormalidadeConsumo = "";
			if(imovelMicromedicao.getConsumoHistorico().getConsumoAnormalidade() != null && imovelMicromedicao.getConsumoHistorico().getConsumoAnormalidade().getDescricaoAbreviada() != null){
				anormalidadeConsumo = "" + imovelMicromedicao.getConsumoHistorico().getConsumoAnormalidade().getDescricaoAbreviada();
			}
			
			String anormalidadeLeitura = "";
			if(imovelMicromedicao.getMedicaoHistorico().getLeituraAnormalidadeFaturamento() != null && imovelMicromedicao.getMedicaoHistorico().getLeituraAnormalidadeFaturamento().getId() != null){
				anormalidadeLeitura = "" + imovelMicromedicao.getMedicaoHistorico().getLeituraAnormalidadeFaturamento().getId();
			}
			
			String sitLeituraAtual = "";
			if(imovelMicromedicao.getMedicaoHistorico().getLeituraSituacaoAtual() != null && 
					imovelMicromedicao.getMedicaoHistorico().getLeituraSituacaoAtual().getDescricao() != null){
				sitLeituraAtual = "" + imovelMicromedicao.getMedicaoHistorico().getLeituraSituacaoAtual().getDescricao();
			}
			
			RelatorioResumoImovelMicromedicaoBean bean = new RelatorioResumoImovelMicromedicaoBean(mesAnoMedicao, dataLeituraInformada,
					leituraInformada, dataLeituraFaturada, leituraFaturada, consumo, media, anormalidadeConsumo, anormalidadeLeitura, sitLeituraAtual);
			 
			retorno.add(bean);
						
		}
		
		return retorno;
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String matricula = (String) getParametro("matricula");

		Collection colecaoImovelMicromedicao = 
			Fachada.getInstancia().carregarDadosConsumo(new Integer(matricula), true); 
//			(Collection)getParametro("colecaoImovelMicromedicao");
		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();
		
		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		String matriculaFormatada = (matricula).substring(0, (matricula).length() - 1) + "." + (matricula).substring((matricula).length() - 1);
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("matricula", matriculaFormatada);
		parametros.put("inscricao", (String) getParametro("inscricao"));
		parametros.put("sitLigacaoAgua", (String) getParametro("sitLigacaoAgua"));
		parametros.put("sitLigacaoEsgoto", (String) getParametro("sitLigacaoEsgoto"));
		parametros.put("endereco", (String) getParametro("endereco"));
		parametros.put("clienteUsuario", (String) getParametro("clienteUsuario"));
		parametros.put("numeroHidrometro",(String) getParametro("numeroHidrometro"));
		parametros.put("dataInstalacao",(String) getParametro("dataInstalacao"));
		parametros.put("numeroRetirado",(String) getParametro("numeroRetirado"));
		parametros.put("dataRetirada",(String) getParametro("dataRetirada"));

		Collection<RelatorioResumoImovelMicromedicaoBean> colecaoBean = null;
		
		if (colecaoImovelMicromedicao != null && !colecaoImovelMicromedicao.isEmpty()) {
			colecaoBean = this
				.inicializarBeanRelatorio(colecaoImovelMicromedicao);
		} else {
			colecaoBean = new ArrayList<RelatorioResumoImovelMicromedicaoBean>();
		}

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_RESUMO_IMOVEL_MICROMEDICAO, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RESUMO_IMOVEL_MEDICAO,
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
		int retorno = 0;

		//retorno = ((Collection) getParametro("idsGuiaDevolucao")).size();

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioResumoImovelMicromedicao", this);
	}
}