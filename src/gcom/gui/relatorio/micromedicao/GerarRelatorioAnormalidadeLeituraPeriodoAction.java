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

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.gui.ActionServletException;
import gcom.gui.micromedicao.GerarRelatorioAnormalidadeLeituraPeriodoActionForm;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.micromedicao.FiltrarRelatorioAnormalidadeLeituraPeriodoHelper;
import gcom.relatorio.micromedicao.RelatorioAnormalidadeLeituraPeriodo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesInterfaceGSAN;
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
public class GerarRelatorioAnormalidadeLeituraPeriodoAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		GerarRelatorioAnormalidadeLeituraPeriodoActionForm relatorioForm = (GerarRelatorioAnormalidadeLeituraPeriodoActionForm) actionForm;

		validarForm(relatorioForm);

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		RelatorioAnormalidadeLeituraPeriodo relatorioAnormalidadeLeituraPeriodo = criarRelatorioBeanParametros(
				relatorioForm, httpServletRequest,tipoRelatorio);
		
		return processarExibicaoRelatorio(relatorioAnormalidadeLeituraPeriodo,
				tipoRelatorio, httpServletRequest, httpServletResponse,actionMapping);

	}

	/**
	 * Esse método faz todas as validações necessárias antes de gerar o relatório.
	 *
	 *@since 30/10/2009
	 *@author Marlon Patrick
	 */
	private void validarForm(GerarRelatorioAnormalidadeLeituraPeriodoActionForm relatorioForm) {


		if ( Util.verificarNaoVazio(relatorioForm.getIdLocalidadeInicial())) {

			validarLocalidade(relatorioForm);
		}

		if ( Util.verificarNaoVazio(relatorioForm.getCodigoSetorComercialInicial())) {

			validarSetorComercial(relatorioForm);
		}

		if ( Util.verificarNaoVazio(relatorioForm.getCodigoRotaInicial())) {

			validarRota(relatorioForm);
		}		

	}

	/**
	 * Esse método cria o Bean do relatorio já com os seus parametros.
	 *
	 *@since 30/10/2009
	 *@author Marlon Patrick
	 */
	private RelatorioAnormalidadeLeituraPeriodo criarRelatorioBeanParametros(
			GerarRelatorioAnormalidadeLeituraPeriodoActionForm relatorioForm,
			HttpServletRequest httpServletRequest,String tipoRelatorio) {
		
		RelatorioAnormalidadeLeituraPeriodo relatorio = new RelatorioAnormalidadeLeituraPeriodo(
				(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		relatorio.addParametro("relatorioForm",relatorioForm);
		relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		relatorio.addParametro("filtroRelatorio", criarFiltro(relatorioForm));

		return relatorio;
	}
	
	/**
	 * Esse método faz validações em cima dos campos
	 * de localidade inicial e final.
	 *
	 *@since 30/10/2009
	 *@author Marlon Patrick
	 */
	private void validarLocalidade(GerarRelatorioAnormalidadeLeituraPeriodoActionForm relatorioForm) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,relatorioForm.getIdLocalidadeInicial()));
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO
				,ConstantesSistema.INDICADOR_USO_ATIVO));

		boolean isUnidadeNegocioInformado = false;
		
		if(Util.isCampoComboboxInformado(relatorioForm.getIdUnidadeNegocio())){
			isUnidadeNegocioInformado = true;
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.UNIDADE_NEGOCIO_ID,relatorioForm.getIdUnidadeNegocio()));
		}

		Collection<Localidade> colecaoLocalidade = 
			this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

		if ( Util.isVazioOrNulo(colecaoLocalidade)) {

			if(isUnidadeNegocioInformado){
				throw new ActionServletException(
						ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO1_INEXISTENTE_NA_CAMPO2_INFORMADA,
						ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE_INICIAL,ConstantesInterfaceGSAN.LABEL_GSAN_UNIDADE_NEGOCIO);

			}
			
			throw new ActionServletException(
					ConstantesInterfaceGSAN.ATENCAO_PESQUISA_INEXISTENTE,ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE_INICIAL);
		}

		if( !relatorioForm.getIdLocalidadeInicial().equals(relatorioForm.getIdLocalidadeFinal())){
			
			filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,relatorioForm.getIdLocalidadeFinal()));
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO
					,ConstantesSistema.INDICADOR_USO_ATIVO));

			isUnidadeNegocioInformado = false;
			
			if(Util.isCampoComboboxInformado(relatorioForm.getIdUnidadeNegocio())){
				isUnidadeNegocioInformado = true;
				
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.UNIDADE_NEGOCIO_ID,relatorioForm.getIdUnidadeNegocio()));
			}

			colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

			if ( Util.isVazioOrNulo(colecaoLocalidade)) {

				if(isUnidadeNegocioInformado){
					throw new ActionServletException(
							ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO1_INEXISTENTE_NA_CAMPO2_INFORMADA,
							ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE_FINAL,ConstantesInterfaceGSAN.LABEL_GSAN_UNIDADE_NEGOCIO);

				}
				
				throw new ActionServletException(
						ConstantesInterfaceGSAN.ATENCAO_PESQUISA_INEXISTENTE,ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE_FINAL);
			}
		}
	}

	/**
	 * Esse método faz validações em cima dos campos
	 * de setor comercial inicial e final.
	 *
	 *@since 06/10/2009
	 *@author Marlon Patrick
	 */
	private void validarSetorComercial(GerarRelatorioAnormalidadeLeituraPeriodoActionForm relatorioForm) {
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,relatorioForm.getCodigoSetorComercialInicial()));
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID, relatorioForm.getIdLocalidadeInicial()));
		filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<SetorComercial> colecaoSetorComercial = 
			this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoSetorComercial)) {
			throw new ActionServletException(
					ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO1_INEXISTENTE_NA_CAMPO2_INFORMADA,
					ConstantesInterfaceGSAN.LABEL_GSAN_SETOR_COMERCIAL_INICIAL,ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE_INICIAL);
		}
		
		if( !relatorioForm.getCodigoSetorComercialInicial().equals(relatorioForm.getCodigoSetorComercialFinal())){
			
			filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,relatorioForm.getCodigoSetorComercialFinal()));
			filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID, relatorioForm.getIdLocalidadeFinal()));
			
			colecaoSetorComercial = 
				this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		
			if ( Util.isVazioOrNulo(colecaoSetorComercial)) {
				throw new ActionServletException(
						ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO1_INEXISTENTE_NA_CAMPO2_INFORMADA,
						ConstantesInterfaceGSAN.LABEL_GSAN_SETOR_COMERCIAL_FINAL,ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE_FINAL);
			}			
		}
	}

	/**
	 * Esse método faz validações em cima dos campos
	 * de rota inicial e final.
	 *
	 *@since 06/10/2009
	 *@author Marlon Patrick
	 */
	private void validarRota(GerarRelatorioAnormalidadeLeituraPeriodoActionForm form) {

		Integer codRotaInicial = new Integer(form.getCodigoRotaInicial());
		Integer codRotaFinal = new Integer(form.getCodigoRotaFinal());

		if(codRotaFinal < codRotaInicial){
			throw new ActionServletException(
				"atencao.rota.final.maior.rota.inicial");

		}
		
		FiltroRota filtroRota = new FiltroRota();
        filtroRota.adicionarParametro(new ParametroSimples(
                FiltroRota.LOCALIDADE_ID, new Integer(form.getIdLocalidadeInicial())));
           
        filtroRota.adicionarParametro(new ParametroSimples(
        		FiltroRota.SETOR_COMERCIAL_CODIGO, new Integer(form.getCodigoSetorComercialInicial())));
                
        filtroRota.adicionarParametro(new ParametroSimples(
                FiltroRota.CODIGO_ROTA, codRotaInicial));

    	filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.INDICADOR_USO,
    			ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<Rota> colecaoRota = 
			this.getFachada().pesquisar(filtroRota, Rota.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoRota)) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Rota Inicial");
		}
		
		if( !codRotaInicial.equals(codRotaFinal)){
			filtroRota = new FiltroRota();
	        filtroRota.adicionarParametro(new ParametroSimples(
	                FiltroRota.LOCALIDADE_ID, new Integer(form.getIdLocalidadeInicial())));
	           
	        filtroRota.adicionarParametro(new ParametroSimples(
	        		FiltroRota.SETOR_COMERCIAL_CODIGO, new Integer(form.getCodigoSetorComercialInicial())));
	                
	        filtroRota.adicionarParametro(new ParametroSimples(
	                FiltroRota.CODIGO_ROTA, codRotaInicial));

	    	filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.INDICADOR_USO,
	    			ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoRota = 
				this.getFachada().pesquisar(filtroRota, Rota.class.getName());
		
			if ( Util.isVazioOrNulo(colecaoRota)) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,"Rota Inicial");
			}
			
		}
	}
	
	/**
	 * Este método cria e configura o filtro necessário a geração do relatório.
	 * 
	 *
	 *@since 25/08/2009
	 *@author Marlon Patrick
	 */
	private FiltrarRelatorioAnormalidadeLeituraPeriodoHelper criarFiltro(
			GerarRelatorioAnormalidadeLeituraPeriodoActionForm relatorioForm) {
		
		FiltrarRelatorioAnormalidadeLeituraPeriodoHelper filtro = 
			new FiltrarRelatorioAnormalidadeLeituraPeriodoHelper();

		
		filtro.setAnoMesReferenciaInicial(new Integer(
				Util.formatarMesAnoParaAnoMesSemBarra(relatorioForm.getMesAnoReferenciaInicial())));

		filtro.setAnoMesReferenciaFinal(new Integer(
				Util.formatarMesAnoParaAnoMesSemBarra(relatorioForm.getMesAnoReferenciaFinal())));

		Integer diferencaMeses = Util.getDiferencaMeses(relatorioForm.getMesAnoReferenciaInicial(), relatorioForm.getMesAnoReferenciaFinal());
		diferencaMeses++;

		filtro.setQuantidadeMeses(diferencaMeses);
			
		filtro.setAnormalidadeLeitura(new Integer(relatorioForm.getIdAnormalidadeLeitura()));

		if (Util.isCampoComboboxInformado(relatorioForm.getIdGrupoFaturamento())) {
			
			filtro.setGrupoFaturamento(new Integer(relatorioForm.getIdGrupoFaturamento()));
		}

		if (Util.isCampoComboboxInformado(relatorioForm.getIdUnidadeNegocio())) {
			
			filtro.setUnidadeNegocio(new Integer(relatorioForm.getIdUnidadeNegocio()));
		}
			
		if (Util.verificarNaoVazio(relatorioForm.getIdLocalidadeInicial())) {
			filtro.setLocalidadeInicial(new Integer(relatorioForm.getIdLocalidadeInicial()));
			filtro.setLocalidadeFinal(new Integer(relatorioForm.getIdLocalidadeFinal()));
		}

		if (Util.verificarNaoVazio(relatorioForm.getCodigoSetorComercialInicial())) {
			filtro.setSetorComercialInicial(new Integer(relatorioForm.getCodigoSetorComercialInicial()));
			filtro.setSetorComercialFinal(new Integer(relatorioForm.getCodigoSetorComercialFinal()));
		}

		if (Util.verificarNaoVazio(relatorioForm.getCodigoRotaInicial())) {
			filtro.setRotaInicial(new Integer(relatorioForm.getCodigoRotaInicial()));
			filtro.setRotaFinal(new Integer(relatorioForm.getCodigoRotaFinal()));
		}

		if (Util.verificarNaoVazio(relatorioForm.getSequencialRotaInicial())) {
			filtro.setSequencialRotaInicial(new Integer(relatorioForm.getSequencialRotaInicial()));
			filtro.setSequencialRotaFinal(new Integer(relatorioForm.getSequencialRotaFinal()));
		}

		return filtro;
	}
}
