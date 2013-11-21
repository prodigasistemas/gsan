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
package gcom.gui.relatorio.faturamento;

import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.faturamento.autoinfracao.GerarRelatorioAutoInfracaoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.autoinfracao.RelatorioAutoInfracao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição do relatório de bairro manter
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class GerarRelatorioAutoInfracaoAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
		ActionForward retorno = null;

		GerarRelatorioAutoInfracaoActionForm gerarRelatorioAutoInfracaoActionForm = (GerarRelatorioAutoInfracaoActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametro = this.getSistemaParametro();
		
		boolean peloMenosUmParametroInformado = false;

		// Inicio da parte que vai mandar os parametros para o relatório

		UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
		Funcionario funcionario = new Funcionario();
		
		String idUnidadeNegocio = gerarRelatorioAutoInfracaoActionForm.getIdUnidadeNegocio();
		
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		
		if (idUnidadeNegocio != null && !idUnidadeNegocio.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, idUnidadeNegocio));
			
			Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			
			if (colecaoUnidadeNegocio != null && !colecaoUnidadeNegocio.isEmpty()) {
				unidadeNegocio = (UnidadeNegocio) Util.retonarObjetoDeColecao(colecaoUnidadeNegocio);
			}
			
		}
		
		String idFuncionario = gerarRelatorioAutoInfracaoActionForm.getIdFuncionario();
		
		if (idFuncionario != null && !idFuncionario.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			filtroFuncionario.adicionarParametro(new ParametroSimples(FiltroFuncionario.ID, idFuncionario));
			
			Collection colecaoFuncionario = fachada.pesquisar(filtroFuncionario, Funcionario.class.getName());
			
			if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {
				funcionario = (Funcionario) Util.retonarObjetoDeColecao(colecaoFuncionario);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Funcionário");
			}
			
		}

		String dataPagamentoInicialForm = gerarRelatorioAutoInfracaoActionForm.getDataPagamentoInicio();
		String dataPagamentoFinalForm = gerarRelatorioAutoInfracaoActionForm.getDataPagamentoFim();
		
		String dataPagamentoInicial = null;
		String dataPagamentoFinal = null;

		if (dataPagamentoInicialForm != null && !dataPagamentoInicialForm.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			
			dataPagamentoInicial = Util.formatarMesAnoParaAnoMesSemBarra(dataPagamentoInicialForm);
			dataPagamentoFinal = Util.formatarMesAnoParaAnoMesSemBarra(dataPagamentoFinalForm);
			
			if (dataPagamentoInicial
					.compareTo(dataPagamentoFinal) > 0) {
				throw new ActionServletException(
						"atencao.data.intervalo.invalido");
			}
			
			//Consulta a data referente ano mes arrecadação direto da tabela e não quando sistema parametro é carregado.
			FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
			filtroSistemaParametro.adicionarParametro( new ParametroSimples( 
					FiltroSistemaParametro.Parm_Id, sistemaParametro.getParmId() ) );
			Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());
			SistemaParametro sistParam = (SistemaParametro) Util.retonarObjetoDeColecao(colecaoSistemaParametro);
			
			if(dataPagamentoFinal.compareTo(sistParam.getAnoMesArrecadacao().toString())>=0){
				throw new ActionServletException(
				"atencao.data.menor.sistema.parametro",null,Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesArrecadacao().toString()));
			}
		}
		
		
		
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
			"atencao.filtro.nenhum_parametro_informado");
		}

		// Fim da parte que vai mandar os parametros para o relatório
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioAutoInfracao relatorioAutoInfracao = new RelatorioAutoInfracao((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioAutoInfracao.addParametro("unidadeNegocio",
				unidadeNegocio);
		relatorioAutoInfracao.addParametro("funcionario",
				funcionario);
		relatorioAutoInfracao.addParametro("dataPagamentoInicial",
				new Integer(dataPagamentoInicial));
		relatorioAutoInfracao.addParametro("dataPagamentoFinal",
				new  Integer(dataPagamentoFinal));
		
		int count = fachada.countRelatorioAutoInfracao(
				unidadeNegocio.getId(),
				funcionario.getId(),
				new Integer(dataPagamentoInicial),
				new Integer(dataPagamentoFinal));
		if(count==0){
			throw new ActionServletException(
			"atencao.relatorio.vazio");
		}
		
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioAutoInfracao.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorioAutoInfracao, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
