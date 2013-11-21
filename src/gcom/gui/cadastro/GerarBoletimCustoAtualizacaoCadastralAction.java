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
package gcom.gui.cadastro;

import gcom.cadastro.AtributosBoletimChaveHelper;
import gcom.cadastro.AtributosBoletimHelper;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.RelatorioBoletimCustoAtualizacaoCadastral;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pre- processamento para geração do boletim de 
 * custo atualizacao cadastral
 * 
 * @author Anderson Italo
 * @date 25/06/2009
 */

public class GerarBoletimCustoAtualizacaoCadastralAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

//		 Seta o caminho de retorno
		ActionForward retorno = null;
		Fachada fachada = Fachada.getInstancia();
		InformarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm informarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm = (InformarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm) actionForm;
		
		//pesquisa a Empresa
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, 
				new Integer(informarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm.getEmpresa())));
		Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
		
		Empresa empresa = (Empresa)Util.retonarObjetoDeColecao(colecaoEmpresa);
		Date dataInicial = Util.converteStringParaDate(informarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm.getDataInicial());
		Date dataFinal = Util.converteStringParaDate(informarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm.getDataFinal());

		Object[] dadosBoletim = fachada.
		gerarBoletimCustoAtualizacaoCadastral(empresa, dataInicial, dataFinal);
		
		TreeMap<AtributosBoletimChaveHelper, AtributosBoletimHelper> mapAtributosBoletim = 
			(TreeMap<AtributosBoletimChaveHelper, AtributosBoletimHelper>)dadosBoletim[0];
		
		Integer numImoveisAtualizados = (Integer)dadosBoletim[1];
		
		//Parte que vai mandar o relatório para a tela
		// cria uma instância da classe do relatório
		RelatorioBoletimCustoAtualizacaoCadastral relatorioBoletimCustoAtualizacaoCadastral = new RelatorioBoletimCustoAtualizacaoCadastral((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		relatorioBoletimCustoAtualizacaoCadastral.addParametro("mapAtributosBoletim", mapAtributosBoletim);
		relatorioBoletimCustoAtualizacaoCadastral.addParametro("empresa", empresa);
		relatorioBoletimCustoAtualizacaoCadastral.addParametro("dataInicial", informarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm.getDataInicial());
		relatorioBoletimCustoAtualizacaoCadastral.addParametro("dataFinal", informarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm.getDataFinal());
		relatorioBoletimCustoAtualizacaoCadastral.addParametro("numImoveisAtualizados", numImoveisAtualizados);
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		relatorioBoletimCustoAtualizacaoCadastral.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));

		try {
			retorno = processarExibicaoRelatorio(relatorioBoletimCustoAtualizacaoCadastral,
				tipoRelatorio, httpServletRequest, httpServletResponse,	actionMapping);

		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}
		
		return retorno;
	}

}
