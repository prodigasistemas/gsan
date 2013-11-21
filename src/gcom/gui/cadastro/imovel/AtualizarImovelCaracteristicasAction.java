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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ImovelAbaCaracteristicasHelper;
import gcom.cadastro.imovel.bean.ImovelAbaCaracteristicasRetornoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class AtualizarImovelCaracteristicasAction extends GcomAction {
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

        ActionForward retorno = actionMapping
                .findForward("gerenciadorProcesso");

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        //instanciando o ActionForm de InserirImovelLocalidadeActionForm
        DynaValidatorForm inserirImovelCaracteristicasActionForm = (DynaValidatorForm) actionForm;
        Imovel imovelAtualizar = (Imovel) sessao.getAttribute("imovelAtualizacao");
        
        String areaConstruida = (String) inserirImovelCaracteristicasActionForm.get("areaConstruida");
		String faixaAreaConstruida = (String) inserirImovelCaracteristicasActionForm.get("faixaAreaConstruida");
        String reservatorioInferior = (String) inserirImovelCaracteristicasActionForm.get("reservatorioInferior");
        String reservatorioSuperior = (String) inserirImovelCaracteristicasActionForm.get("reservatorioSuperior");
        String piscina = (String) inserirImovelCaracteristicasActionForm.get("piscina");
		String pavimentoCalcada = (String) inserirImovelCaracteristicasActionForm.get("pavimentoCalcada");
		String pavimentoRua = (String) inserirImovelCaracteristicasActionForm.get("pavimentoRua");
		String fonteAbastecimento = (String) inserirImovelCaracteristicasActionForm.get("fonteAbastecimento");
		String situacaoLigacaoAgua = (String) inserirImovelCaracteristicasActionForm.get("situacaoLigacaoAgua");
		String situacaoLigacaoEsgoto = (String) inserirImovelCaracteristicasActionForm.get("situacaoLigacaoEsgoto");
		String idLigacaoEsgotoEsgotamento = (String) inserirImovelCaracteristicasActionForm.get("idLigacaoEsgotoEsgotamento");
		String perfilImovel = (String) inserirImovelCaracteristicasActionForm.get("perfilImovel");
		
		String idSetorComercial = (String) inserirImovelCaracteristicasActionForm.get("idSetorComercial");
		String idQuadra = (String) inserirImovelCaracteristicasActionForm.get("idQuadra");
		
		String indicadorNivelInstalacaoEsgoto = (String) inserirImovelCaracteristicasActionForm.get("indicadorNivelInstalacaoEsgoto");

        Fachada fachada = Fachada.getInstancia();
        
		ImovelAbaCaracteristicasHelper helperCaracteristica = new ImovelAbaCaracteristicasHelper();
		helperCaracteristica.setImovelAtualizar(imovelAtualizar);
		helperCaracteristica.setAreaConstruida(areaConstruida);
		helperCaracteristica.setIdAreaConstruidaFaixa(faixaAreaConstruida);
		helperCaracteristica.setVolumeReservatorioInferior(reservatorioInferior);
		helperCaracteristica.setVolumeReservatorioSuperior(reservatorioSuperior);
		helperCaracteristica.setVolumePiscinaMovel(piscina);
		helperCaracteristica.setIdPavimentoCalcada(pavimentoCalcada);
		helperCaracteristica.setIdPavimentoRua(pavimentoRua);
		helperCaracteristica.setIdFonteAbastecimento(fonteAbastecimento);
		helperCaracteristica.setIdLigacaoAguaSituacao(situacaoLigacaoAgua);
		helperCaracteristica.setIdLigacaoEsgotoSituacao(situacaoLigacaoEsgoto);
		helperCaracteristica.setIdLigacaoEsgotoEsgotamento(idLigacaoEsgotoEsgotamento);
		helperCaracteristica.setIdImovelPerfil(perfilImovel);
		
		

		//*************************************************
		// Autor: Ivan Sergio
		// Data: 23/04/2009
		// CRC1657
		//*************************************************
		// [FS0023] - Verificar Setor e Quadra
		//*************************************************
		helperCaracteristica.setIdSetorComercial(idSetorComercial);
		helperCaracteristica.setIdQuadra(idQuadra);
		//*************************************************
	
		// Autor: Nathalia Santos 
		// Data: 12/07/2011
		// RR201106690 - Verifica se Existe nível para instalação de esgoto caso a empresa seja CAER.
		helperCaracteristica.setIdNivelInstalacaoEsgoto(indicadorNivelInstalacaoEsgoto);
		//*****************************************************
		
		ImovelAbaCaracteristicasRetornoHelper resultado = fachada.validarImovelAbaCaracteristicas(helperCaracteristica);

		if (resultado.getAreaConstruidaFaixa() != null) {
			inserirImovelCaracteristicasActionForm.set(
					"faixaAreaConstruida", resultado.getAreaConstruidaFaixa().getId() + "");
		}
		if (resultado.getReservatorioVolumeFaixaInferior() != null) {
			inserirImovelCaracteristicasActionForm.set(
					"faixaReservatorioInferior", resultado.getReservatorioVolumeFaixaInferior().getId() + "");
		}
		if (resultado.getReservatorioVolumeFaixaSuperior() != null) {
			inserirImovelCaracteristicasActionForm.set(
					"faixaResevatorioSuperior", resultado.getReservatorioVolumeFaixaSuperior().getId() + "");
		}
		if (resultado.getPiscinaVolumeFaixa() != null) {
			inserirImovelCaracteristicasActionForm.set(
					"faixaPiscina", resultado.getPiscinaVolumeFaixa().getId() + "");
		} 


        return retorno;
    }

}
