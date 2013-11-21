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

import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.micromedicao.RegistrarLeiturasAnormalidadesRelatorioActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.micromedicao.RelatorioComparativoLeiturasEAnormalidades;
import gcom.seguranca.acesso.usuario.Usuario;
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
 * action responsável pela exibição do relatório de bairro manter
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class GerarRelatorioRegistrarLeiturasAnormalidadesAction extends
		ExibidorProcessamentoTarefaRelatorio {
	
	private Collection colecaoPesquisa = null;
	private String setorComercialCD = null;

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
		
		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		RegistrarLeiturasAnormalidadesRelatorioActionForm registrarLeiturasAnormalidadesRelatorioActionForm = (RegistrarLeiturasAnormalidadesRelatorioActionForm) actionForm;

		Integer idFaturamentoGrupo = null;
		if(registrarLeiturasAnormalidadesRelatorioActionForm.getIdFaturamentoGrupo() != null &&
				!registrarLeiturasAnormalidadesRelatorioActionForm.getIdFaturamentoGrupo().equals("-1")){
			idFaturamentoGrupo = new Integer(registrarLeiturasAnormalidadesRelatorioActionForm.getIdFaturamentoGrupo());
		}
		
		String anoMesReferenciaString = registrarLeiturasAnormalidadesRelatorioActionForm.getMesAno();
		
		Integer idEmpresa = null;
		if(registrarLeiturasAnormalidadesRelatorioActionForm.getIdFirma()!= null &&
				!registrarLeiturasAnormalidadesRelatorioActionForm.getIdFirma().equals("-1")){
			idEmpresa = new Integer(registrarLeiturasAnormalidadesRelatorioActionForm.getIdFirma());		
		}
		
		Integer idLocalidadeInicial = null;
		if(registrarLeiturasAnormalidadesRelatorioActionForm.getLocalidadeOrigemID() != null &&
				!registrarLeiturasAnormalidadesRelatorioActionForm.getLocalidadeOrigemID().equals("")){
			idLocalidadeInicial = new Integer(registrarLeiturasAnormalidadesRelatorioActionForm.getLocalidadeOrigemID());
		}
		Integer idLocalidadeFinal = null;
		if(registrarLeiturasAnormalidadesRelatorioActionForm.getLocalidadeDestinoID() != null &&
				!registrarLeiturasAnormalidadesRelatorioActionForm.getLocalidadeDestinoID().equals("")){
			idLocalidadeFinal = new Integer(registrarLeiturasAnormalidadesRelatorioActionForm.getLocalidadeDestinoID());
		}
		
		// Rota Inicial
		Integer idRotaInicial = null;
		if (registrarLeiturasAnormalidadesRelatorioActionForm.getRotaInicial() != null && 
			!registrarLeiturasAnormalidadesRelatorioActionForm.getRotaInicial().equals("") ) {
				
			idRotaInicial = new Integer( registrarLeiturasAnormalidadesRelatorioActionForm.getRotaInicial());
		}

		
		// Rota Final
		Integer idRotaFinal = null;
		if (registrarLeiturasAnormalidadesRelatorioActionForm.getRotaFinal() != null && 
			!registrarLeiturasAnormalidadesRelatorioActionForm.getRotaFinal().equals("") ) {
				
			idRotaFinal = new Integer (registrarLeiturasAnormalidadesRelatorioActionForm.getRotaFinal());
		}
		
		
		Integer idSetorInicial = null;
		if(registrarLeiturasAnormalidadesRelatorioActionForm.getSetorComercialOrigemCD() != null &&
			!registrarLeiturasAnormalidadesRelatorioActionForm.getSetorComercialOrigemCD().equals("")){
			if(registrarLeiturasAnormalidadesRelatorioActionForm.getSetorComercialOrigemID() != null &&
					!registrarLeiturasAnormalidadesRelatorioActionForm.getSetorComercialOrigemID().equals("")){
				idSetorInicial = new Integer(registrarLeiturasAnormalidadesRelatorioActionForm.getSetorComercialOrigemID());
			}else{
				pesquisarSetorComercial("origem", registrarLeiturasAnormalidadesRelatorioActionForm, fachada, httpServletRequest);
				idSetorInicial = new Integer(registrarLeiturasAnormalidadesRelatorioActionForm.getSetorComercialOrigemID());
			}
		}
		
		Integer idSetorFinal = null;
		if(registrarLeiturasAnormalidadesRelatorioActionForm.getSetorComercialDestinoCD() != null &&
				!registrarLeiturasAnormalidadesRelatorioActionForm.getSetorComercialDestinoCD().equals("")){
			if(registrarLeiturasAnormalidadesRelatorioActionForm.getSetorComercialDestinoID() != null &&
					!registrarLeiturasAnormalidadesRelatorioActionForm.getSetorComercialDestinoID().equals("")){
				idSetorFinal = new Integer(registrarLeiturasAnormalidadesRelatorioActionForm.getSetorComercialDestinoID());
			}else{
				pesquisarSetorComercial("destino", registrarLeiturasAnormalidadesRelatorioActionForm, fachada, httpServletRequest);
				idSetorFinal = new Integer(registrarLeiturasAnormalidadesRelatorioActionForm.getSetorComercialDestinoID());
			}
		}
		
		Integer idGerencia = null;
		if(registrarLeiturasAnormalidadesRelatorioActionForm.getGerenciaRegional() != null &&
				!registrarLeiturasAnormalidadesRelatorioActionForm.getGerenciaRegional().equals("-1")){
			idGerencia = new Integer(registrarLeiturasAnormalidadesRelatorioActionForm.getGerenciaRegional());
		}
		
		Integer idUnidadeNegocio = null;
		if(registrarLeiturasAnormalidadesRelatorioActionForm.getUnidadeNegocio() != null &&
				!registrarLeiturasAnormalidadesRelatorioActionForm.getUnidadeNegocio().equals("-1")){
			idUnidadeNegocio = new Integer(registrarLeiturasAnormalidadesRelatorioActionForm.getUnidadeNegocio());
		}
		
		Integer idLeiturista = null;
		if(registrarLeiturasAnormalidadesRelatorioActionForm.getIdLeiturista() != null && 
				!registrarLeiturasAnormalidadesRelatorioActionForm.getIdLeiturista().equals("")
				){
			
			idLeiturista = new Integer(registrarLeiturasAnormalidadesRelatorioActionForm.getIdLeiturista());
		}
		
		Integer anoMes = Util.formatarMesAnoComBarraParaAnoMes(anoMesReferenciaString);
		
		Integer idPerfilImovel = null;
		if(Util.verificarIdNaoVazio(registrarLeiturasAnormalidadesRelatorioActionForm.getPerfilImovel())){
			idPerfilImovel = Integer.parseInt(registrarLeiturasAnormalidadesRelatorioActionForm.getPerfilImovel());
		}
		
		Collection<String> colecaoAnormalidadesLeituras = new ArrayList<String>();
		if(registrarLeiturasAnormalidadesRelatorioActionForm.getAnormalidadesLeituras() != null && 
			registrarLeiturasAnormalidadesRelatorioActionForm.getAnormalidadesLeituras().length > 0){
			for (String anormalidade : registrarLeiturasAnormalidadesRelatorioActionForm.getAnormalidadesLeituras()) {
				
				if(anormalidade != null && !anormalidade.equals("-1")){
					colecaoAnormalidadesLeituras.add(anormalidade);	
				}
			}
		}
		
		Integer numOcorrenciasConsecutivas = null;
		if(Util.verificarNaoVazio(registrarLeiturasAnormalidadesRelatorioActionForm.getNumOcorrenciasConsecutivas())){
			numOcorrenciasConsecutivas = Integer.parseInt(registrarLeiturasAnormalidadesRelatorioActionForm.getNumOcorrenciasConsecutivas());
		}
		
		// Fim da parte que vai mandar os parametros para o relatório
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioComparativoLeiturasEAnormalidades relatorioComparativoLeiturasEAnormalidades = new RelatorioComparativoLeiturasEAnormalidades((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioComparativoLeiturasEAnormalidades.addParametro(
				"idGrupoFaturamento", idFaturamentoGrupo);

		relatorioComparativoLeiturasEAnormalidades.addParametro(
				"anoMes", anoMes);
		
		relatorioComparativoLeiturasEAnormalidades.addParametro(
				"idEmpresa", idEmpresa);
		
		relatorioComparativoLeiturasEAnormalidades.addParametro(
				"idLocalidadeInicial", idLocalidadeInicial);
				
        relatorioComparativoLeiturasEAnormalidades.addParametro(
				"idLocalidadeFinal", idLocalidadeFinal);	
        
        relatorioComparativoLeiturasEAnormalidades.addParametro(
        		"idRotaInicial", idRotaInicial);
        
        relatorioComparativoLeiturasEAnormalidades.addParametro(
				"idRotaFinal", idRotaFinal);		
        
        relatorioComparativoLeiturasEAnormalidades.addParametro(
        		"idSetorInicial", idSetorInicial);
        
        relatorioComparativoLeiturasEAnormalidades.addParametro(
				"idSetorFinal", idSetorFinal);		        
		
        relatorioComparativoLeiturasEAnormalidades.addParametro(
				"idGerencia", idGerencia);	
        
        relatorioComparativoLeiturasEAnormalidades.addParametro(
				"idUnidadeNegocio", idUnidadeNegocio);	
        
    	relatorioComparativoLeiturasEAnormalidades.addParametro(
    			"idPerfilImovel", idPerfilImovel);
    	
    	relatorioComparativoLeiturasEAnormalidades.addParametro(
    			"numOcorrenciasConsecutivas", numOcorrenciasConsecutivas);
        
    	relatorioComparativoLeiturasEAnormalidades.addParametro(
    			"colecaoAnormalidadesLeituras", colecaoAnormalidadesLeituras);
        
        if(idLeiturista != null){
        	relatorioComparativoLeiturasEAnormalidades.addParametro("idLeiturista", idLeiturista);
        }
        
		// relatorioComparativoLeiturasEAnormalidades.addParametro(
		//				"dataUltimaAlteracao", new Date());
		
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioComparativoLeiturasEAnormalidades.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorioComparativoLeiturasEAnormalidades, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}
	
	private void pesquisarSetorComercial(String inscricaoTipo,
			RegistrarLeiturasAnormalidadesRelatorioActionForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			form.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			String localidadeID = (String) form
					.getLocalidadeOrigemID();

			// O campo localidadeOrigemID será obrigatório
			if (localidadeID != null
					&& !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) form
						.getSetorComercialOrigemCD();

				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
						SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialOrigemCD,
					// nomeSetorComercialOrigem e setorComercialOrigemID do
					// formulário
					form
							.setSetorComercialOrigemCD("");
					form
							.setSetorComercialOrigemID("");
					form
							.setNomeSetorComercialOrigem("Setor comercial inexistente.");
					
					throw new ActionServletException("atencao.setor_comercial.inexistente");
					
				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					//setorComercialOrigem
					form
							.setSetorComercialOrigemCD(String
									.valueOf(objetoSetorComercial.getCodigo()));
					form
							.setSetorComercialOrigemID(String
									.valueOf(objetoSetorComercial.getId()));
					form
							.setNomeSetorComercialOrigem(objetoSetorComercial
									.getDescricao());
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formulário
				form.setSetorComercialOrigemCD("");
				form
						.setNomeSetorComercialOrigem("Informe a localidade da inscrição de origem.");
			}
		} else {
			
			form.setInscricaoTipo("destino");
			
			// Recebe o valor do campo localidadeDestinoID do formulário.
			String localidadeID = (String) form
					.getLocalidadeDestinoID();

			// O campo localidadeOrigem será obrigatório
			if (localidadeID != null
					&& !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) form
						.getSetorComercialDestinoCD();

				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
						SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialDestinoCD,
					// nomeSetorComercialDestino e setorComercialDestinoID do
					// formulário
					form
							.setSetorComercialDestinoCD("");
					form
							.setSetorComercialDestinoID("");
					form
							.setNomeSetorComercialDestino("Setor comercial inexistente.");
				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					form
							.setSetorComercialDestinoCD(String
									.valueOf(objetoSetorComercial.getCodigo()));
					form
							.setSetorComercialDestinoID(String
									.valueOf(objetoSetorComercial.getId()));
					form
							.setNomeSetorComercialDestino(objetoSetorComercial
									.getDescricao());
				}
			} else {
				// Limpa o campo setorComercialDestinoCD do formulário
				form.setSetorComercialDestinoCD("");
				form.setNomeSetorComercialDestino("Informe a localidade da inscrição de destino.");
			}
		}

	}

}
