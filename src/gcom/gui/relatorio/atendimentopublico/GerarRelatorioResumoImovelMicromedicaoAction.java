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
package gcom.gui.relatorio.atendimentopublico;

import gcom.cadastro.cliente.Cliente;
import gcom.fachada.Fachada;
import gcom.gui.cadastro.imovel.ConsultarImovelActionForm;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioResumoImovelMicromedicao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Ana Maria
 * @date 13/02/2007
 */
public class GerarRelatorioResumoImovelMicromedicaoAction extends
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
		
		Fachada fachada = Fachada.getInstancia();

		// cria uma instância da classe do relatório
		RelatorioResumoImovelMicromedicao relatorioResumoImovelMicromedicao = new RelatorioResumoImovelMicromedicao((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		ConsultarImovelActionForm consultarImovelActionForm = (ConsultarImovelActionForm) actionForm;
		
//		Collection<Integer> anosMeses = new ArrayList<Integer>(); 
//		
//		Collection colecaoMedicaoHistorico = fachada.carregarDadosMedicaoResumo(new Integer(
//				consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo()), true);
//
//		Collection imoveisMicromedicaoCarregamento = null;
//		Collection colecaoImovelMicromedicao = new ArrayList();
//		
//		if (colecaoMedicaoHistorico != null && !colecaoMedicaoHistorico.isEmpty()) {
//			Iterator iteratorMedicaoHistorico = colecaoMedicaoHistorico
//					.iterator();
//
//			while (iteratorMedicaoHistorico.hasNext()) {
//				MedicaoHistorico medicaoHistoricoConsumo = (MedicaoHistorico) iteratorMedicaoHistorico
//						.next();
//				if (medicaoHistoricoConsumo.getAnoMesReferencia() != 0) {
//					
//					
//					imoveisMicromedicaoCarregamento = fachada
//							.carregarDadosConsumo(
//									new Integer(consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo()),
//									medicaoHistoricoConsumo
//											.getAnoMesReferencia(), true);
//
//					if (imoveisMicromedicaoCarregamento != null) {
//						ImovelMicromedicao imovelMicromedicao = (ImovelMicromedicao)imoveisMicromedicaoCarregamento.iterator().next();
//						if(imovelMicromedicao.getMedicaoHistorico() != null 
//								&& imovelMicromedicao.getMedicaoHistorico().getNumeroConsumoMes() != null){
//							medicaoHistoricoConsumo.setNumeroConsumoMes(imovelMicromedicao.getMedicaoHistorico().getNumeroConsumoMes());
//							
//						}
//						
//						imovelMicromedicao.setMedicaoHistorico(medicaoHistoricoConsumo);
//						
//						colecaoImovelMicromedicao.add(imovelMicromedicao);
//					}
//				}
//			}
//			
//			// Organizar a coleção de Conta
//			if (colecaoImovelMicromedicao != null
//					&& !colecaoImovelMicromedicao.isEmpty()) {
//
//				Collections.sort((List) colecaoImovelMicromedicao,
//						new Comparator() {
//							public int compare(Object a, Object b) {
//
//								int retorno = 0;
//								Integer anoMesReferencia1 = ((ImovelMicromedicao) a)
//										.getMedicaoHistorico()
//										.getAnoMesReferencia();
//								Integer anoMesReferencia2 = ((ImovelMicromedicao) b)
//										.getMedicaoHistorico()
//										.getAnoMesReferencia();
//
//								if (anoMesReferencia1
//										.compareTo(anoMesReferencia2) == 1) {
//									retorno = -1;
//								} else if (anoMesReferencia1
//										.compareTo(anoMesReferencia2) == -1) {
//									retorno = 1;
//								}
//
//								return retorno;
//
//							}
//						});
//			}
//			
//		} 
		 
		 String endereco = fachada.pesquisarEndereco(new Integer(consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo()));
			
		 Cliente cliente = fachada.pesquisarClienteUsuarioImovelExcluidoOuNao(new Integer(consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo()));
		 
//		 relatorioResumoImovelMicromedicao.addParametro("colecaoImovelMicromedicao", colecaoImovelMicromedicao);
		 relatorioResumoImovelMicromedicao.addParametro("matricula", consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo());
		 relatorioResumoImovelMicromedicao.addParametro("inscricao", consultarImovelActionForm.getMatriculaImovelAnaliseMedicaoConsumo());
		 relatorioResumoImovelMicromedicao.addParametro("sitLigacaoAgua", consultarImovelActionForm.getSituacaoAguaAnaliseMedicaoConsumo());
		 relatorioResumoImovelMicromedicao.addParametro("sitLigacaoEsgoto", consultarImovelActionForm.getSituacaoEsgotoAnaliseMedicaoConsumo());
		 relatorioResumoImovelMicromedicao.addParametro("numeroHidrometro",consultarImovelActionForm.getNumeroHidrometro());
		 relatorioResumoImovelMicromedicao.addParametro("endereco", endereco);
		 

		 /** [MA2011061013] Incluir no cabeçalho o Número do Hidrômetro Retirado e a Data da Retirada
		  *  Autor: Paulo Diniz
		  *  Data: 13/07/2011
		  * */
		 List<HidrometroInstalacaoHistorico> hidrometrosHistorico = fachada.pesquisarHidrometroPeloIdImovel(new Integer(consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo()));
		 
		 if(hidrometrosHistorico != null && !hidrometrosHistorico.isEmpty()){
			 
			 Date dataInstalacao = hidrometrosHistorico.get(0).getDataInstalacao();
			 String numeroRetirado = "";
			 Date dataRetirada = hidrometrosHistorico.get(0).getDataRetirada();
			 if(hidrometrosHistorico.get(0).getNumeroLeituraRetirada() != null){
				 numeroRetirado = hidrometrosHistorico.get(0).getNumeroLeituraRetirada().intValue()+"";
			 }
			 
			 for (HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico : hidrometrosHistorico) {
				 
				 if(dataInstalacao == null || (hidrometroInstalacaoHistorico.getDataInstalacao() != null && dataInstalacao.before(hidrometroInstalacaoHistorico.getDataInstalacao()))){
					 dataInstalacao = hidrometroInstalacaoHistorico.getDataInstalacao();
				 }

				 if(dataRetirada == null || (hidrometroInstalacaoHistorico.getDataRetirada() != null && dataRetirada.before(hidrometroInstalacaoHistorico.getDataRetirada()))){
					 dataRetirada = hidrometroInstalacaoHistorico.getDataRetirada();
					 if(hidrometroInstalacaoHistorico.getNumeroLeituraRetirada() != null){
						 numeroRetirado = hidrometroInstalacaoHistorico.getNumeroLeituraRetirada().intValue()+"";
					 }else{
						 numeroRetirado = "";
					 }
				 }
			 }
			 
			 relatorioResumoImovelMicromedicao.addParametro("dataInstalacao",Util.formatarData(dataInstalacao));
			 relatorioResumoImovelMicromedicao.addParametro("numeroRetirado",numeroRetirado);
			 relatorioResumoImovelMicromedicao.addParametro("dataRetirada",Util.formatarData(dataRetirada));
		 } else{
			 relatorioResumoImovelMicromedicao.addParametro("dataInstalacao","");
			 relatorioResumoImovelMicromedicao.addParametro("numeroRetirado","");
			 relatorioResumoImovelMicromedicao.addParametro("dataRetirada","");
		 }
		 
		 if (cliente != null) {
			 relatorioResumoImovelMicromedicao.addParametro("clienteUsuario", cliente.getNome());
		 } else {
			 relatorioResumoImovelMicromedicao.addParametro("clienteUsuario", "");
		 }

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioResumoImovelMicromedicao.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));

		retorno = processarExibicaoRelatorio(relatorioResumoImovelMicromedicao,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);
		
		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
