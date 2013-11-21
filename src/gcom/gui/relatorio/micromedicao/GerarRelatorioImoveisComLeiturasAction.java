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
package gcom.gui.relatorio.micromedicao;

import java.util.ArrayList;
import java.util.Iterator;

import gcom.batch.Relatorio;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.micromedicao.FiltrarRelatorioImoveisComLeiturasHelper;
import gcom.relatorio.cadastro.micromedicao.RelatorioImoveisComLeituras;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1180] Gerar Relatório de Imóveis Com Leituras
 * 
 * @author Magno Gouveia
 * @date 03/06/2011
 */
public class GerarRelatorioImoveisComLeiturasAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioImoveisComLeiturasAction");
		
		GerarRelatorioImoveisComLeiturasActionForm form = (GerarRelatorioImoveisComLeiturasActionForm) actionForm;
		
		FiltrarRelatorioImoveisComLeiturasHelper filtro =  new FiltrarRelatorioImoveisComLeiturasHelper();
		
		ArrayList<String> filtrosUtilizados = new ArrayList<String>();
		
		// Mês/Ano de Referência
		if(Util.verificarNaoVazio(form.getMesAnoReferencia())){
			filtro.setMesAnoReferencia(Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoReferencia()).toString());
			filtrosUtilizados.add("Mês/Ano de Referência");
		} else {
			throw new ActionServletException("atencao.anomesreferencia.obrigatorio", 
											  null, 
											  Util.formatarAnoMesParaMesAno(form.getMesAnoReferencia()));
		}
		
		// Grupo Faturamento
		if(Util.verificarIdNaoVazio(form.getGrupoFaturamento())){
			filtro.setGrupoFaturamento(Integer.parseInt(form.getGrupoFaturamento()));
			filtrosUtilizados.add("Grupo de Faturamento");
		} else {
			throw new ActionServletException("atencao.grupofaturamento.obrigatorio");
		}
		
		// Empresa
		if(Util.verificarIdNaoVazio(form.getEmpresa())){
			filtro.setEmpresa(Integer.parseInt(form.getEmpresa()));
			filtrosUtilizados.add("Empresa");
		} 
		
		// Leiturista
		if(Util.verificarIdNaoVazio(form.getLeiturista())){
			filtro.setLeiturista(Integer.parseInt(form.getLeiturista()));
			filtrosUtilizados.add("Leiturista");
		}
		
		// Localidade Inicial
		if(Util.verificarIdNaoVazio(form.getLocalidadeInicial())){
			filtro.setLocalidadeInicial(Integer.parseInt(form.getLocalidadeInicial()));
			filtrosUtilizados.add("Localidade");
		}
		
		// Setor Comercial Inicial
		if(Util.verificarIdNaoVazio(form.getSetorComercialInicial())){
			filtro.setSetorComercialInicial(Integer.parseInt(form.getSetorComercialInicial()));
			filtrosUtilizados.add("Setor Comercial");
		}
		
		// Rota Inicial
		if(Util.verificarIdNaoVazio(form.getRotaInicial())){
			filtro.setRotaInicial(Integer.parseInt(form.getRotaInicial()));
			filtrosUtilizados.add("Rota");
		}
		
		// Localidade Final
		if(Util.verificarIdNaoVazio(form.getLocalidadeFinal())){
			filtro.setLocalidadeFinal(Integer.parseInt(form.getLocalidadeFinal()));
			if(!filtrosUtilizados.contains("Localidade")){
				filtrosUtilizados.add("Localidade");
			}
		}
		
		// Setor Comercial Final
		if(Util.verificarIdNaoVazio(form.getSetorComercialFinal())){
			filtro.setSetorComercialFinal(Integer.parseInt(form.getSetorComercialFinal()));
			if(!filtrosUtilizados.contains("Setor Comercial")){
				filtrosUtilizados.add("Setor Comercial");
			}
		}
		
		// Rota Final
		if(Util.verificarIdNaoVazio(form.getRotaFinal())){
			filtro.setRotaFinal(Integer.parseInt(form.getRotaFinal()));
			if(!filtrosUtilizados.contains("Rota")){
				filtrosUtilizados.add("Rota");
			}
		}
		
		int opcaoTipoRelatorio = Integer.parseInt(httpServletRequest.getParameter("opcaoTipoRelatorio"));
		
		if (Util.verificarIdNaoVazio(String.valueOf(opcaoTipoRelatorio))) {
			
			String constanteRelatorio = "";
			String opcaoTipoRelatorioNome = "";
			int parametroPersistirRelatorio = 0;
			
			switch (opcaoTipoRelatorio) {
				case 1:
					parametroPersistirRelatorio = Relatorio.GERAR_RELATORIO_IMOVEIS_COM_LEITURAS_TIPO_1;
					constanteRelatorio = ConstantesRelatorios.RELATORIO_IMOVEIS_COM_LEITURAS_QUANTITATIVOS;
					opcaoTipoRelatorioNome = "Quantitativo de Imóveis com Leituras Através da WEB";
					break;
				case 2:
					parametroPersistirRelatorio = Relatorio.GERAR_RELATORIO_IMOVEIS_COM_LEITURAS_TIPO_2;
					constanteRelatorio = ConstantesRelatorios.RELATORIO_IMOVEIS_COM_LEITURAS_QUANTITATIVOS;
					opcaoTipoRelatorioNome = "Quantitativo de Imóveis sem Leituras Através da ISC e WEB";
					break;
				case 3:
					parametroPersistirRelatorio = Relatorio.GERAR_RELATORIO_IMOVEIS_COM_LEITURAS_TIPO_3;
					constanteRelatorio = ConstantesRelatorios.RELATORIO_IMOVEIS_COM_LEITURAS_QUANTITATIVOS;
					opcaoTipoRelatorioNome = "Quantitativo de imóveis que estão na rota mas não foram recebidos através da ISC";
					break;
				case 4:
					parametroPersistirRelatorio = Relatorio.GERAR_RELATORIO_IMOVEIS_COM_LEITURAS_TIPO_4;
					constanteRelatorio = ConstantesRelatorios.RELATORIO_IMOVEIS_COM_LEITURAS_RELACAO;
					opcaoTipoRelatorioNome = "Relação de imóveis com leituras não recebidas através da ISC";
					break;
				case 5:
					parametroPersistirRelatorio = Relatorio.GERAR_RELATORIO_IMOVEIS_COM_LEITURAS_TIPO_5;
					constanteRelatorio = ConstantesRelatorios.RELATORIO_IMOVEIS_COM_LEITURAS_RELACAO;
					opcaoTipoRelatorioNome = "Relação de imóveis não medidos que não estão na rota de ISC";
					break;
				case 6:
					parametroPersistirRelatorio = Relatorio.GERAR_RELATORIO_IMOVEIS_COM_LEITURAS_TIPO_6;
					constanteRelatorio = ConstantesRelatorios.RELATORIO_IMOVEIS_COM_LEITURAS_RELACAO;
					opcaoTipoRelatorioNome = "Relação de imóveis medidos que não estão na rota de ISC";
					break;
				case 7:
					parametroPersistirRelatorio = Relatorio.GERAR_RELATORIO_IMOVEIS_COM_LEITURAS_TIPO_7;
					constanteRelatorio = ConstantesRelatorios.RELATORIO_IMOVEIS_COM_LEITURAS_TIPO_7;
					opcaoTipoRelatorioNome = "Quantitativo de imóveis com leituras enviado e recebidos";
					break;
				default:
					throw new ActionServletException("CONSTANTE DE RELATORIO INVALIDA [UC1180]");
			}
			
			RelatorioImoveisComLeituras relatorio = new RelatorioImoveisComLeituras(this.getUsuarioLogado(httpServletRequest), constanteRelatorio);
			relatorio.addParametro("opcaoTipoRelatorio", opcaoTipoRelatorio);
			relatorio.addParametro("parametroPersistirRelatorio", parametroPersistirRelatorio);
			relatorio.addParametro("filtro", filtro);
			relatorio.addParametro("filtrosUtilizados", this.parseFiltrosUtilizados(filtrosUtilizados));
			relatorio.addParametro("opcaoTipoRelatorioNome", opcaoTipoRelatorioNome);
			
			try {
				retorno = processarExibicaoRelatorio(relatorio, TarefaRelatorio.TIPO_PDF,
								httpServletRequest, httpServletResponse, actionMapping);
			} catch (RelatorioVazioException e) {
				// manda o erro para a página no request atual
				reportarErros(httpServletRequest, "atencao.relatorio.vazio");
				// seta o mapeamento de retorno para a tela de atenção de popup
				retorno = actionMapping.findForward("telaAtencaoPopup");
			}
		} else {
			throw new ActionServletException("atencao.tipo_relatorio_nao_informado");
		}

		return retorno;
	}

	private String parseFiltrosUtilizados(ArrayList<String> filtrosUtilizados) {
		String filtros = "";
		Iterator it = filtrosUtilizados.iterator();
		
		while (it.hasNext()) {
			filtros += (String) it.next();
			if(it.hasNext()){
				filtros += ", ";
			}
		}
		
		return filtros;
	}
	
}