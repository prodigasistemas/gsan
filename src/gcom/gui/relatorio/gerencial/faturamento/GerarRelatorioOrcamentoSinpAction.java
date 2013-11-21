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
package gcom.gui.relatorio.gerencial.faturamento;

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.gerencial.bean.FiltrarRelatorioOrcamentoSINPHelper;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.gerencial.faturamento.RelatorioOrcamentoSINP;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe montar o filtro para pesquisa do relatorio do orcamento e SINP 
 *
 * @author Rafael Pinto
 * 
 * @date 21/11/2007
 */

public class GerarRelatorioOrcamentoSinpAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("emitirOrcamentoSinp");
		
		// Form
		EmissaoRelatorioOrcamentoSinpActionForm form = 
			(EmissaoRelatorioOrcamentoSinpActionForm) actionForm;
		
		FiltrarRelatorioOrcamentoSINPHelper filtro = new FiltrarRelatorioOrcamentoSINPHelper();
		
		// Mês/Ano do Faturamento
		Integer anoMes = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoFaturamento());
		
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
		
		if(sistemaParametro.getAnoMesFaturamento().intValue() < anoMes.intValue()){
			throw new ActionServletException("atencao.mes_ano.faturamento.inferior", 
				null,""+sistemaParametro.getAnoMesFaturamento());
		}
		
		this.getFachada().validarDadosOrcamentoSINP(anoMes);
		
		filtro.setAnoMesReferencia(anoMes);

		// Opção de Totalização
		int opcaoTotalizacao = Integer.parseInt(form.getOpcaoTotalizacao());
		filtro.setOpcaoTotalizacao(opcaoTotalizacao);
		
		// Gerência Regional
		if ((opcaoTotalizacao == 6 || opcaoTotalizacao == 10) &&
			form.getGerenciaRegional() != null && 
			!form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			Collection<Integer> colecao = new ArrayList<Integer>();
			colecao.add(new Integer(form.getGerenciaRegional()));

			filtro.setChavesGerencia(colecao);
		}
		
		// Unidade de Negocio
		if (opcaoTotalizacao == 10 && 
			form.getUnidadeNegocio() != null && 
			!form.getUnidadeNegocio().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			Collection<Integer> colecao = new ArrayList<Integer>();
			colecao.add(new Integer(form.getUnidadeNegocio()));
			
			filtro.setChavesUnidade(colecao);
		}
			
		// Localidade		
		if (opcaoTotalizacao == 16 && 
			form.getLocalidade() != null && 
			!form.getLocalidade().equals("") ) {
				
			Collection<Integer> colecao = new ArrayList<Integer>();
			
			//verifica se existe a localidade escolhida
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(
					new ParametroSimples(FiltroLocalidade.ID, 
					form.getLocalidade()));
			
			// Recupera Localidade
			Collection colecaoLocalidade = 
				this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
		
			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				colecao.add(new Integer(form.getLocalidade()));
				
				filtro.setChavesLocalidade(colecao);
			}else{
				throw new ActionServletException(
    					"atencao.pesquisa_inexistente", null,"Localidade");
			}
			
		}
		
		// Município		
		if (opcaoTotalizacao == 19 && form.getMunicipio() != null && !form.getMunicipio().equals("") ) {
			
			Collection<Integer> colecao = new ArrayList<Integer>();
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, form.getMunicipio()));
			Collection colecaoMunicipio = this.getFachada().pesquisar(filtroMunicipio, Municipio.class.getName());
		
			if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {
				colecao.add(new Integer(form.getMunicipio()));
				filtro.setChavesLocalidadesMunicipio(colecao);
			}else{
				throw new ActionServletException("atencao.pesquisa_inexistente", null,"Município");
			}
		}
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioOrcamentoSINP relatorio = 
			new RelatorioOrcamentoSINP(this.getUsuarioLogado(httpServletRequest));
		
		relatorio.addParametro("filtrarRelatorioOrcamentoSINPHelper", filtro);
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		retorno = 
			processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
				httpServletResponse, actionMapping);

		return retorno;
	}
}