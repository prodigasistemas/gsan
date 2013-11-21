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
package gcom.gui.gerencial.cobranca;

import gcom.fachada.Fachada;
import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.gerencial.cobranca.bean.ResumoPendenciaAcumuladoHelper;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe 
 *
 * @author Roberta Costa
 * @date 29/05/2006
 */
public class ConsultarResumoPendenciaAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping
                .findForward("consultarResumoPendencia");

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();
        
        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);
						
        InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper = 
        	(InformarDadosGeracaoRelatorioConsultaHelper)sessao.getAttribute("informarDadosGeracaoRelatorioConsultaHelper");
        sessao.setAttribute("informarDadosGeracaoRelatorioConsultaHelper", informarDadosGeracaoRelatorioConsultaHelper);

        Collection<ResumoPendenciaAcumuladoHelper> resumoPendenciaAcumuladoHelper = 
        	fachada.retornaConsultaResumoPendencia(informarDadosGeracaoRelatorioConsultaHelper);
        sessao.setAttribute("resumoPendenciaAcumuladoHelper", resumoPendenciaAcumuladoHelper);

        
        // Variáveis do tipo Particular
        ResumoPendenciaAcumuladoHelper resumoPendenciaParticularPotencial = new ResumoPendenciaAcumuladoHelper();
        ResumoPendenciaAcumuladoHelper resumoPendenciaParticularFactivel = new ResumoPendenciaAcumuladoHelper();
        ResumoPendenciaAcumuladoHelper resumoPendenciaParticularLigadoAgua = new ResumoPendenciaAcumuladoHelper();
        ResumoPendenciaAcumuladoHelper resumoPendenciaParticularCortado = new ResumoPendenciaAcumuladoHelper();
        ResumoPendenciaAcumuladoHelper resumoPendenciaParticularLigadoSoEsgoto = new ResumoPendenciaAcumuladoHelper();
        ResumoPendenciaAcumuladoHelper resumoPendenciaParticularEsgotoForaUso = new ResumoPendenciaAcumuladoHelper();
        ResumoPendenciaAcumuladoHelper resumoPendenciaParticularEsgotoTamponado = new ResumoPendenciaAcumuladoHelper();
        ResumoPendenciaAcumuladoHelper resumoPendenciaParticularSuprimidoTotal = new ResumoPendenciaAcumuladoHelper();
        ResumoPendenciaAcumuladoHelper resumoPendenciaParticularSuprimidoParcial = new ResumoPendenciaAcumuladoHelper();
        ResumoPendenciaAcumuladoHelper resumoPendenciaParticularSuprimidoAPedido  = new ResumoPendenciaAcumuladoHelper();
        Integer resumoPendenciaParticularQuantidadeLigacoes = 0;
        Integer resumoPendenciaParticularQuantidadeDocumento = 0;
        BigDecimal resumoPendenciaParticularValorPendente  = new BigDecimal("0.00");
        
        // Variáveis do tipo Publico
        ResumoPendenciaAcumuladoHelper resumoPendenciaPublicoPotencial = new ResumoPendenciaAcumuladoHelper();
        ResumoPendenciaAcumuladoHelper resumoPendenciaPublicoFactivel = new ResumoPendenciaAcumuladoHelper();
        ResumoPendenciaAcumuladoHelper resumoPendenciaPublicoLigadoAgua = new ResumoPendenciaAcumuladoHelper();
        ResumoPendenciaAcumuladoHelper resumoPendenciaPublicoCortado = new ResumoPendenciaAcumuladoHelper();
        ResumoPendenciaAcumuladoHelper resumoPendenciaPublicoLigadoSoEsgoto = new ResumoPendenciaAcumuladoHelper();
        ResumoPendenciaAcumuladoHelper resumoPendenciaPublicoEsgotoForaUso = new ResumoPendenciaAcumuladoHelper();
        ResumoPendenciaAcumuladoHelper resumoPendenciaPublicoEsgotoTamponado = new ResumoPendenciaAcumuladoHelper();
        ResumoPendenciaAcumuladoHelper resumoPendenciaPublicoSuprimidoTotal = new ResumoPendenciaAcumuladoHelper();
        ResumoPendenciaAcumuladoHelper resumoPendenciaPublicoSuprimidoParcial = new ResumoPendenciaAcumuladoHelper();
        ResumoPendenciaAcumuladoHelper resumoPendenciaPublicoSuprimidoAPedido  = new ResumoPendenciaAcumuladoHelper();
        Integer resumoPendenciaPublicoQuantidadeLigacoes  = 0;
        Integer resumoPendenciaPublicoQuantidadeDocumento  = 0;
        BigDecimal resumoPendenciaPublicoValorPendente  = new BigDecimal("0.00");

        if (resumoPendenciaAcumuladoHelper != null
				&& !resumoPendenciaAcumuladoHelper.isEmpty()) {
			Iterator resumoPendenciaAcumulado= resumoPendenciaAcumuladoHelper.iterator();

			// Verifica e Acumula o tipo Particular e Publico
			int existeParticular = 0;
			int existePublico = 0;
			String categoriaAnteriorParticular = "";
			String categoriaAnteriorPublico = "";
			
			while(resumoPendenciaAcumulado.hasNext()) {
				
				ResumoPendenciaAcumuladoHelper resumoPendencia = 
					(ResumoPendenciaAcumuladoHelper) resumoPendenciaAcumulado.next();

				if(resumoPendencia.getTipoCategoria().equals("PARTICULAR")){
					// Verifica se existe mais de uma categoria do tipo particular
					if( !resumoPendencia.getCategoria().equals(categoriaAnteriorParticular)){
						existeParticular++;	
					}
					categoriaAnteriorParticular = resumoPendencia.getCategoria();

					// Totalizadores do tipo Particular
					resumoPendenciaParticularQuantidadeLigacoes =
						resumoPendenciaParticularQuantidadeLigacoes + resumoPendencia.getQuantidadeLigacoes();	
					resumoPendenciaParticularQuantidadeDocumento =
						resumoPendenciaParticularQuantidadeDocumento + resumoPendencia.getQuantidadeDocumento();	
					resumoPendenciaParticularValorPendente = 
							resumoPendenciaParticularValorPendente
									.add(resumoPendencia.getValorPendente());	
					
					// Acumulando para cada tipo de situação de Água e Esgoto
					if(resumoPendencia.getTipoSituacaoAguaEsgoto().equals("POTENCIAL")){
						if(resumoPendenciaParticularPotencial.getQuantidadeLigacoes() == null ){
							resumoPendenciaParticularPotencial
								.setQuantidadeLigacoes(resumoPendencia.getQuantidadeLigacoes());	
						}else{
							resumoPendenciaParticularPotencial
								.setQuantidadeLigacoes(resumoPendenciaParticularPotencial
										.getQuantidadeLigacoes()+resumoPendencia.getQuantidadeLigacoes());	
						}
						if(resumoPendenciaParticularPotencial.getQuantidadeDocumento() == null ){
							resumoPendenciaParticularPotencial
								.setQuantidadeDocumento(resumoPendencia.getQuantidadeDocumento());	
						}else{
							resumoPendenciaParticularPotencial
								.setQuantidadeDocumento(resumoPendenciaParticularPotencial
										.getQuantidadeDocumento()+resumoPendencia.getQuantidadeDocumento());	
						}
						if(resumoPendenciaParticularPotencial.getValorPendente() == null ){
							resumoPendenciaParticularPotencial
								.setValorPendente(resumoPendencia.getValorPendente());	
						}else{
							resumoPendenciaParticularPotencial
								.setValorPendente(resumoPendenciaParticularPotencial
										.getValorPendente().add(resumoPendencia.getValorPendente()));	
						}
					}else if(resumoPendencia.getTipoSituacaoAguaEsgoto().equals("FACTÍVEL")){
						if(resumoPendenciaParticularFactivel.getQuantidadeLigacoes() == null ){
							resumoPendenciaParticularFactivel
								.setQuantidadeLigacoes(resumoPendencia.getQuantidadeLigacoes());	
						}else{
							resumoPendenciaParticularFactivel
								.setQuantidadeLigacoes(resumoPendenciaParticularFactivel
										.getQuantidadeLigacoes()+resumoPendencia.getQuantidadeLigacoes());	
						}
						if(resumoPendenciaParticularFactivel.getQuantidadeDocumento() == null ){
							resumoPendenciaParticularFactivel
								.setQuantidadeDocumento(resumoPendencia.getQuantidadeDocumento());	
						}else{
							resumoPendenciaParticularFactivel
								.setQuantidadeDocumento(resumoPendenciaParticularFactivel
										.getQuantidadeDocumento()+resumoPendencia.getQuantidadeDocumento());	
						}
						if(resumoPendenciaParticularFactivel.getValorPendente() == null ){
							resumoPendenciaParticularFactivel
								.setValorPendente(resumoPendencia.getValorPendente());	
						}else{
							resumoPendenciaParticularFactivel
								.setValorPendente(resumoPendenciaParticularFactivel
										.getValorPendente().add(resumoPendencia.getValorPendente()));	
						}
					}else if(resumoPendencia.getTipoSituacaoAguaEsgoto().equals("LIGADO DE ÁGUA")){
						if(resumoPendenciaParticularLigadoAgua.getQuantidadeLigacoes() == null ){
							resumoPendenciaParticularLigadoAgua
								.setQuantidadeLigacoes(resumoPendencia.getQuantidadeLigacoes());	
						}else{
							resumoPendenciaParticularLigadoAgua
								.setQuantidadeLigacoes(resumoPendenciaParticularLigadoAgua
										.getQuantidadeLigacoes()+resumoPendencia.getQuantidadeLigacoes());	
						}
						if(resumoPendenciaParticularLigadoAgua.getQuantidadeDocumento() == null ){
							resumoPendenciaParticularLigadoAgua
								.setQuantidadeDocumento(resumoPendencia.getQuantidadeDocumento());	
						}else{
							resumoPendenciaParticularLigadoAgua
								.setQuantidadeDocumento(resumoPendenciaParticularLigadoAgua
										.getQuantidadeDocumento()+resumoPendencia.getQuantidadeDocumento());	
						}
						if(resumoPendenciaParticularLigadoAgua.getValorPendente() == null ){
							resumoPendenciaParticularLigadoAgua
								.setValorPendente(resumoPendencia.getValorPendente());	
						}else{
							resumoPendenciaParticularLigadoAgua
								.setValorPendente(resumoPendenciaParticularLigadoAgua
										.getValorPendente().add(resumoPendencia.getValorPendente()));	
						}
					}else if(resumoPendencia.getTipoSituacaoAguaEsgoto().equals("CORTADO")){
						if(resumoPendenciaParticularCortado.getQuantidadeLigacoes() == null ){
							resumoPendenciaParticularCortado
								.setQuantidadeLigacoes(resumoPendencia.getQuantidadeLigacoes());	
						}else{
							resumoPendenciaParticularCortado
								.setQuantidadeLigacoes(resumoPendenciaParticularCortado
										.getQuantidadeLigacoes()+resumoPendencia.getQuantidadeLigacoes());	
						}
						if(resumoPendenciaParticularCortado.getQuantidadeDocumento() == null ){
							resumoPendenciaParticularCortado
								.setQuantidadeDocumento(resumoPendencia.getQuantidadeDocumento());	
						}else{
							resumoPendenciaParticularCortado
								.setQuantidadeDocumento(resumoPendenciaParticularCortado
										.getQuantidadeDocumento()+resumoPendencia.getQuantidadeDocumento());	
						}
						if(resumoPendenciaParticularCortado.getValorPendente() == null ){
							resumoPendenciaParticularCortado
								.setValorPendente(resumoPendencia.getValorPendente());	
						}else{
							resumoPendenciaParticularCortado
								.setValorPendente(resumoPendenciaParticularCortado
										.getValorPendente().add(resumoPendencia.getValorPendente()));	
						}
					}else if(resumoPendencia.getTipoSituacaoAguaEsgoto().equals("LIGADO SÓ DE ESGOTO")){
						if(resumoPendenciaParticularLigadoSoEsgoto.getQuantidadeLigacoes() == null ){
							resumoPendenciaParticularLigadoSoEsgoto
								.setQuantidadeLigacoes(resumoPendencia.getQuantidadeLigacoes());	
						}else{
							resumoPendenciaParticularLigadoSoEsgoto
								.setQuantidadeLigacoes(resumoPendenciaParticularLigadoSoEsgoto
										.getQuantidadeLigacoes()+resumoPendencia.getQuantidadeLigacoes());	
						}
						if(resumoPendenciaParticularLigadoSoEsgoto.getQuantidadeDocumento() == null ){
							resumoPendenciaParticularLigadoSoEsgoto
								.setQuantidadeDocumento(resumoPendencia.getQuantidadeDocumento());	
						}else{
							resumoPendenciaParticularLigadoSoEsgoto
								.setQuantidadeDocumento(resumoPendenciaParticularLigadoSoEsgoto
										.getQuantidadeDocumento()+resumoPendencia.getQuantidadeDocumento());	
						}
						if(resumoPendenciaParticularLigadoSoEsgoto.getValorPendente() == null ){
							resumoPendenciaParticularLigadoSoEsgoto
								.setValorPendente(resumoPendencia.getValorPendente());	
						}else{
							resumoPendenciaParticularLigadoSoEsgoto
								.setValorPendente(resumoPendenciaParticularLigadoSoEsgoto
										.getValorPendente().add(resumoPendencia.getValorPendente()));	
						}
					}else if(resumoPendencia.getTipoSituacaoAguaEsgoto().equals("ESGOTO FORA DE USO")){
						if(resumoPendenciaParticularEsgotoForaUso.getQuantidadeLigacoes() == null ){
							resumoPendenciaParticularEsgotoForaUso
								.setQuantidadeLigacoes(resumoPendencia.getQuantidadeLigacoes());	
						}else{
							resumoPendenciaParticularEsgotoForaUso
								.setQuantidadeLigacoes(resumoPendenciaParticularEsgotoForaUso
										.getQuantidadeLigacoes()+resumoPendencia.getQuantidadeLigacoes());	
						}
						if(resumoPendenciaParticularEsgotoForaUso.getQuantidadeDocumento() == null ){
							resumoPendenciaParticularEsgotoForaUso
								.setQuantidadeDocumento(resumoPendencia.getQuantidadeDocumento());	
						}else{
							resumoPendenciaParticularEsgotoForaUso
								.setQuantidadeDocumento(resumoPendenciaParticularEsgotoForaUso
										.getQuantidadeDocumento()+resumoPendencia.getQuantidadeDocumento());	
						}
						if(resumoPendenciaParticularEsgotoForaUso.getValorPendente() == null ){
							resumoPendenciaParticularEsgotoForaUso
								.setValorPendente(resumoPendencia.getValorPendente());	
						}else{
							resumoPendenciaParticularEsgotoForaUso
								.setValorPendente(resumoPendenciaParticularEsgotoForaUso
										.getValorPendente().add(resumoPendencia.getValorPendente()));	
						}
					}else if(resumoPendencia.getTipoSituacaoAguaEsgoto().equals("ESGOTO TAMPONADO")){
						if(resumoPendenciaParticularEsgotoTamponado.getQuantidadeLigacoes() == null ){
							resumoPendenciaParticularEsgotoTamponado
								.setQuantidadeLigacoes(resumoPendencia.getQuantidadeLigacoes());	
						}else{
							resumoPendenciaParticularEsgotoTamponado
								.setQuantidadeLigacoes(resumoPendenciaParticularEsgotoTamponado
										.getQuantidadeLigacoes()+resumoPendencia.getQuantidadeLigacoes());	
						}
						if(resumoPendenciaParticularEsgotoTamponado.getQuantidadeDocumento() == null ){
							resumoPendenciaParticularEsgotoTamponado
								.setQuantidadeDocumento(resumoPendencia.getQuantidadeDocumento());	
						}else{
							resumoPendenciaParticularEsgotoTamponado
								.setQuantidadeDocumento(resumoPendenciaParticularEsgotoTamponado
										.getQuantidadeDocumento()+resumoPendencia.getQuantidadeDocumento());	
						}
						if(resumoPendenciaParticularEsgotoTamponado.getValorPendente() == null ){
							resumoPendenciaParticularEsgotoTamponado
								.setValorPendente(resumoPendencia.getValorPendente());	
						}else{
							resumoPendenciaParticularEsgotoTamponado
								.setValorPendente(resumoPendenciaParticularEsgotoTamponado
										.getValorPendente().add(resumoPendencia.getValorPendente()));	
						}
					}else if(resumoPendencia.getTipoSituacaoAguaEsgoto().equals("SUPRIMIDO TOTAL")){
						if(resumoPendenciaParticularSuprimidoTotal.getQuantidadeLigacoes() == null ){
							resumoPendenciaParticularSuprimidoTotal
								.setQuantidadeLigacoes(resumoPendencia.getQuantidadeLigacoes());	
						}else{
							resumoPendenciaParticularSuprimidoTotal
								.setQuantidadeLigacoes(resumoPendenciaParticularSuprimidoTotal
										.getQuantidadeLigacoes()+resumoPendencia.getQuantidadeLigacoes());	
						}
						if(resumoPendenciaParticularSuprimidoTotal.getQuantidadeDocumento() == null ){
							resumoPendenciaParticularSuprimidoTotal
								.setQuantidadeDocumento(resumoPendencia.getQuantidadeDocumento());	
						}else{
							resumoPendenciaParticularSuprimidoTotal
								.setQuantidadeDocumento(resumoPendenciaParticularSuprimidoTotal
										.getQuantidadeDocumento()+resumoPendencia.getQuantidadeDocumento());	
						}
						if(resumoPendenciaParticularSuprimidoTotal.getValorPendente() == null ){
							resumoPendenciaParticularSuprimidoTotal
								.setValorPendente(resumoPendencia.getValorPendente());	
						}else{
							resumoPendenciaParticularSuprimidoTotal
								.setValorPendente(resumoPendenciaParticularSuprimidoTotal
										.getValorPendente().add(resumoPendencia.getValorPendente()));	
						}
					}else if(resumoPendencia.getTipoSituacaoAguaEsgoto().equals("SUPRIMIDO PARCIAL")){
						if(resumoPendenciaParticularSuprimidoParcial.getQuantidadeLigacoes() == null ){
							resumoPendenciaParticularSuprimidoParcial
								.setQuantidadeLigacoes(resumoPendencia.getQuantidadeLigacoes());	
						}else{
							resumoPendenciaParticularSuprimidoParcial
								.setQuantidadeLigacoes(resumoPendenciaParticularSuprimidoParcial
										.getQuantidadeLigacoes()+resumoPendencia.getQuantidadeLigacoes());	
						}
						if(resumoPendenciaParticularSuprimidoParcial.getQuantidadeDocumento() == null ){
							resumoPendenciaParticularSuprimidoParcial
								.setQuantidadeDocumento(resumoPendencia.getQuantidadeDocumento());	
						}else{
							resumoPendenciaParticularSuprimidoParcial
								.setQuantidadeDocumento(resumoPendenciaParticularSuprimidoParcial
										.getQuantidadeDocumento()+resumoPendencia.getQuantidadeDocumento());	
						}
						if(resumoPendenciaParticularSuprimidoParcial.getValorPendente() == null ){
							resumoPendenciaParticularSuprimidoParcial
								.setValorPendente(resumoPendencia.getValorPendente());	
						}else{
							resumoPendenciaParticularSuprimidoParcial
								.setValorPendente(resumoPendenciaParticularSuprimidoParcial
										.getValorPendente().add(resumoPendencia.getValorPendente()));	
						}
					}else if(resumoPendencia.getTipoSituacaoAguaEsgoto().equals("SUPRIMIDO A PEDIDO")){
						if(resumoPendenciaParticularSuprimidoAPedido.getQuantidadeLigacoes() == null ){
							resumoPendenciaParticularSuprimidoAPedido
								.setQuantidadeLigacoes(resumoPendencia.getQuantidadeLigacoes());	
						}else{
							resumoPendenciaParticularSuprimidoAPedido
								.setQuantidadeLigacoes(resumoPendenciaParticularSuprimidoAPedido
										.getQuantidadeLigacoes()+resumoPendencia.getQuantidadeLigacoes());	
						}
						if(resumoPendenciaParticularSuprimidoAPedido.getQuantidadeDocumento() == null ){
							resumoPendenciaParticularSuprimidoAPedido
								.setQuantidadeDocumento(resumoPendencia.getQuantidadeDocumento());	
						}else{
							resumoPendenciaParticularSuprimidoAPedido
								.setQuantidadeDocumento(resumoPendenciaParticularSuprimidoAPedido
										.getQuantidadeDocumento()+resumoPendencia.getQuantidadeDocumento());	
						}
						if(resumoPendenciaParticularSuprimidoAPedido.getValorPendente() == null ){
							resumoPendenciaParticularSuprimidoAPedido
								.setValorPendente(resumoPendencia.getValorPendente());	
						}else{
							resumoPendenciaParticularSuprimidoAPedido
								.setValorPendente(resumoPendenciaParticularSuprimidoAPedido
										.getValorPendente().add(resumoPendencia.getValorPendente()));	
						}
					}
				// TIPO CATEGORIA PÚBLICO
				}else if(resumoPendencia.getTipoCategoria().equals("PUBLICO")){
					// Verifica se existe mais de uma categoria do tipo publico
					if( !resumoPendencia.getCategoria().equals(categoriaAnteriorPublico)){
						existePublico++;	
					}
					categoriaAnteriorPublico = resumoPendencia.getCategoria(); 

					// Totalizadores do tipo Publico
					resumoPendenciaPublicoQuantidadeLigacoes =
						resumoPendenciaPublicoQuantidadeLigacoes + resumoPendencia.getQuantidadeLigacoes();	
					resumoPendenciaPublicoQuantidadeDocumento =
						resumoPendenciaPublicoQuantidadeDocumento + resumoPendencia.getQuantidadeDocumento();	
					resumoPendenciaPublicoValorPendente = 
							resumoPendenciaPublicoValorPendente
									.add(resumoPendencia.getValorPendente());	

					// Acumulando para cada tipo de situação de Água e Esgoto
					if(resumoPendencia.getTipoSituacaoAguaEsgoto().equals("POTENCIAL")){
						if(resumoPendenciaPublicoPotencial.getQuantidadeLigacoes() == null ){
							resumoPendenciaPublicoPotencial
								.setQuantidadeLigacoes(resumoPendencia.getQuantidadeLigacoes());	
						}else{
							resumoPendenciaPublicoPotencial
								.setQuantidadeLigacoes(resumoPendenciaPublicoPotencial
										.getQuantidadeLigacoes()+resumoPendencia.getQuantidadeLigacoes());	
						}
						if(resumoPendenciaPublicoPotencial.getQuantidadeDocumento() == null ){
							resumoPendenciaPublicoPotencial
								.setQuantidadeDocumento(resumoPendencia.getQuantidadeDocumento());	
						}else{
							resumoPendenciaPublicoPotencial
								.setQuantidadeDocumento(resumoPendenciaPublicoPotencial
										.getQuantidadeDocumento()+resumoPendencia.getQuantidadeDocumento());	
						}
						if(resumoPendenciaPublicoPotencial.getValorPendente() == null ){
							resumoPendenciaPublicoPotencial
								.setValorPendente(resumoPendencia.getValorPendente());	
						}else{
							resumoPendenciaPublicoPotencial
								.setValorPendente(resumoPendenciaPublicoPotencial
										.getValorPendente().add(resumoPendencia.getValorPendente()));	
						}
					}else if(resumoPendencia.getTipoSituacaoAguaEsgoto().equals("FACTÍVEL")){
						if(resumoPendenciaPublicoFactivel.getQuantidadeLigacoes() == null ){
							resumoPendenciaPublicoFactivel
								.setQuantidadeLigacoes(resumoPendencia.getQuantidadeLigacoes());	
						}else{
							resumoPendenciaPublicoFactivel
								.setQuantidadeLigacoes(resumoPendenciaPublicoFactivel
										.getQuantidadeLigacoes()+resumoPendencia.getQuantidadeLigacoes());	
						}
						if(resumoPendenciaPublicoFactivel.getQuantidadeDocumento() == null ){
							resumoPendenciaPublicoFactivel
								.setQuantidadeDocumento(resumoPendencia.getQuantidadeDocumento());	
						}else{
							resumoPendenciaPublicoFactivel
								.setQuantidadeDocumento(resumoPendenciaPublicoFactivel
										.getQuantidadeDocumento()+resumoPendencia.getQuantidadeDocumento());	
						}
						if(resumoPendenciaPublicoFactivel.getValorPendente() == null ){
							resumoPendenciaPublicoFactivel
								.setValorPendente(resumoPendencia.getValorPendente());	
						}else{
							resumoPendenciaPublicoFactivel
								.setValorPendente(resumoPendenciaPublicoFactivel
										.getValorPendente().add(resumoPendencia.getValorPendente()));	
						}
					}else if(resumoPendencia.getTipoSituacaoAguaEsgoto().equals("LIGADO DE ÁGUA")){
						if(resumoPendenciaPublicoLigadoAgua.getQuantidadeLigacoes() == null ){
							resumoPendenciaPublicoLigadoAgua
								.setQuantidadeLigacoes(resumoPendencia.getQuantidadeLigacoes());	
						}else{
							resumoPendenciaPublicoLigadoAgua
								.setQuantidadeLigacoes(resumoPendenciaPublicoLigadoAgua
										.getQuantidadeLigacoes()+resumoPendencia.getQuantidadeLigacoes());	
						}
						if(resumoPendenciaPublicoLigadoAgua.getQuantidadeDocumento() == null ){
							resumoPendenciaPublicoLigadoAgua
								.setQuantidadeDocumento(resumoPendencia.getQuantidadeDocumento());	
						}else{
							resumoPendenciaPublicoLigadoAgua
								.setQuantidadeDocumento(resumoPendenciaPublicoLigadoAgua
										.getQuantidadeDocumento()+resumoPendencia.getQuantidadeDocumento());	
						}
						if(resumoPendenciaPublicoLigadoAgua.getValorPendente() == null ){
							resumoPendenciaPublicoLigadoAgua
								.setValorPendente(resumoPendencia.getValorPendente());	
						}else{
							resumoPendenciaPublicoLigadoAgua
								.setValorPendente(resumoPendenciaPublicoLigadoAgua
										.getValorPendente().add(resumoPendencia.getValorPendente()));	
						}
					}else if(resumoPendencia.getTipoSituacaoAguaEsgoto().equals("CORTADO")){
						if(resumoPendenciaPublicoCortado.getQuantidadeLigacoes() == null ){
							resumoPendenciaPublicoCortado
								.setQuantidadeLigacoes(resumoPendencia.getQuantidadeLigacoes());	
						}else{
							resumoPendenciaPublicoCortado
								.setQuantidadeLigacoes(resumoPendenciaPublicoCortado
										.getQuantidadeLigacoes()+resumoPendencia.getQuantidadeLigacoes());	
						}
						if(resumoPendenciaPublicoCortado.getQuantidadeDocumento() == null ){
							resumoPendenciaPublicoCortado
								.setQuantidadeDocumento(resumoPendencia.getQuantidadeDocumento());	
						}else{
							resumoPendenciaPublicoCortado
								.setQuantidadeDocumento(resumoPendenciaPublicoCortado
										.getQuantidadeDocumento()+resumoPendencia.getQuantidadeDocumento());	
						}
						if(resumoPendenciaPublicoCortado.getValorPendente() == null ){
							resumoPendenciaPublicoCortado
								.setValorPendente(resumoPendencia.getValorPendente());	
						}else{
							resumoPendenciaPublicoCortado
								.setValorPendente(resumoPendenciaPublicoCortado
										.getValorPendente().add(resumoPendencia.getValorPendente()));	
						}
					}else if(resumoPendencia.getTipoSituacaoAguaEsgoto().equals("LIGADO SÓ DE ESGOTO")){
						if(resumoPendenciaPublicoLigadoSoEsgoto.getQuantidadeLigacoes() == null ){
							resumoPendenciaPublicoLigadoSoEsgoto
								.setQuantidadeLigacoes(resumoPendencia.getQuantidadeLigacoes());	
						}else{
							resumoPendenciaPublicoLigadoSoEsgoto
								.setQuantidadeLigacoes(resumoPendenciaPublicoLigadoSoEsgoto
										.getQuantidadeLigacoes()+resumoPendencia.getQuantidadeLigacoes());	
						}
						if(resumoPendenciaPublicoLigadoSoEsgoto.getQuantidadeDocumento() == null ){
							resumoPendenciaPublicoLigadoSoEsgoto
								.setQuantidadeDocumento(resumoPendencia.getQuantidadeDocumento());	
						}else{
							resumoPendenciaPublicoLigadoSoEsgoto
								.setQuantidadeDocumento(resumoPendenciaPublicoLigadoSoEsgoto
										.getQuantidadeDocumento()+resumoPendencia.getQuantidadeDocumento());	
						}
						if(resumoPendenciaPublicoLigadoSoEsgoto.getValorPendente() == null ){
							resumoPendenciaPublicoLigadoSoEsgoto
								.setValorPendente(resumoPendencia.getValorPendente());	
						}else{
							resumoPendenciaPublicoLigadoSoEsgoto
								.setValorPendente(resumoPendenciaPublicoLigadoSoEsgoto
										.getValorPendente().add(resumoPendencia.getValorPendente()));	
						}
					}else if(resumoPendencia.getTipoSituacaoAguaEsgoto().equals("ESGOTO FORA DE USO")){
						if(resumoPendenciaPublicoEsgotoForaUso.getQuantidadeLigacoes() == null ){
							resumoPendenciaPublicoEsgotoForaUso
								.setQuantidadeLigacoes(resumoPendencia.getQuantidadeLigacoes());	
						}else{
							resumoPendenciaPublicoEsgotoForaUso
								.setQuantidadeLigacoes(resumoPendenciaPublicoEsgotoForaUso
										.getQuantidadeLigacoes()+resumoPendencia.getQuantidadeLigacoes());	
						}
						if(resumoPendenciaPublicoEsgotoForaUso.getQuantidadeDocumento() == null ){
							resumoPendenciaPublicoEsgotoForaUso
								.setQuantidadeDocumento(resumoPendencia.getQuantidadeDocumento());	
						}else{
							resumoPendenciaPublicoEsgotoForaUso
								.setQuantidadeDocumento(resumoPendenciaPublicoEsgotoForaUso
										.getQuantidadeDocumento()+resumoPendencia.getQuantidadeDocumento());	
						}
						if(resumoPendenciaPublicoEsgotoForaUso.getValorPendente() == null ){
							resumoPendenciaPublicoEsgotoForaUso
								.setValorPendente(resumoPendencia.getValorPendente());	
						}else{
							resumoPendenciaPublicoEsgotoForaUso
								.setValorPendente(resumoPendenciaPublicoEsgotoForaUso
										.getValorPendente().add(resumoPendencia.getValorPendente()));	
						}
					}else if(resumoPendencia.getTipoSituacaoAguaEsgoto().equals("ESGOTO TAMPONADO")){
						if(resumoPendenciaPublicoEsgotoTamponado.getQuantidadeLigacoes() == null ){
							resumoPendenciaPublicoEsgotoTamponado
								.setQuantidadeLigacoes(resumoPendencia.getQuantidadeLigacoes());	
						}else{
							resumoPendenciaPublicoEsgotoTamponado
								.setQuantidadeLigacoes(resumoPendenciaPublicoEsgotoTamponado
										.getQuantidadeLigacoes()+resumoPendencia.getQuantidadeLigacoes());	
						}
						if(resumoPendenciaPublicoEsgotoTamponado.getQuantidadeDocumento() == null ){
							resumoPendenciaPublicoEsgotoTamponado
								.setQuantidadeDocumento(resumoPendencia.getQuantidadeDocumento());	
						}else{
							resumoPendenciaPublicoEsgotoTamponado
								.setQuantidadeDocumento(resumoPendenciaPublicoEsgotoTamponado
										.getQuantidadeDocumento()+resumoPendencia.getQuantidadeDocumento());	
						}
						if(resumoPendenciaPublicoEsgotoTamponado.getValorPendente() == null ){
							resumoPendenciaPublicoEsgotoTamponado
								.setValorPendente(resumoPendencia.getValorPendente());	
						}else{
							resumoPendenciaPublicoEsgotoTamponado
								.setValorPendente(resumoPendenciaPublicoEsgotoTamponado
										.getValorPendente().add(resumoPendencia.getValorPendente()));	
						}
					}else if(resumoPendencia.getTipoSituacaoAguaEsgoto().equals("SUPRIMIDO TOTAL")){
						if(resumoPendenciaPublicoSuprimidoTotal.getQuantidadeLigacoes() == null ){
							resumoPendenciaPublicoSuprimidoTotal
								.setQuantidadeLigacoes(resumoPendencia.getQuantidadeLigacoes());	
						}else{
							resumoPendenciaPublicoSuprimidoTotal
								.setQuantidadeLigacoes(resumoPendenciaPublicoSuprimidoTotal
										.getQuantidadeLigacoes()+resumoPendencia.getQuantidadeLigacoes());	
						}
						if(resumoPendenciaPublicoSuprimidoTotal.getQuantidadeDocumento() == null ){
							resumoPendenciaPublicoSuprimidoTotal
								.setQuantidadeDocumento(resumoPendencia.getQuantidadeDocumento());	
						}else{
							resumoPendenciaPublicoSuprimidoTotal
								.setQuantidadeDocumento(resumoPendenciaPublicoSuprimidoTotal
										.getQuantidadeDocumento()+resumoPendencia.getQuantidadeDocumento());	
						}
						if(resumoPendenciaPublicoSuprimidoTotal.getValorPendente() == null ){
							resumoPendenciaPublicoSuprimidoTotal
								.setValorPendente(resumoPendencia.getValorPendente());	
						}else{
							resumoPendenciaPublicoSuprimidoTotal
								.setValorPendente(resumoPendenciaPublicoSuprimidoTotal
										.getValorPendente().add(resumoPendencia.getValorPendente()));	
						}
					}else if(resumoPendencia.getTipoSituacaoAguaEsgoto().equals("SUPRIMIDO PARCIAL")){
						if(resumoPendenciaPublicoSuprimidoParcial.getQuantidadeLigacoes() == null ){
							resumoPendenciaPublicoSuprimidoParcial
								.setQuantidadeLigacoes(resumoPendencia.getQuantidadeLigacoes());	
						}else{
							resumoPendenciaPublicoSuprimidoParcial
								.setQuantidadeLigacoes(resumoPendenciaPublicoSuprimidoParcial
										.getQuantidadeLigacoes()+resumoPendencia.getQuantidadeLigacoes());	
						}
						if(resumoPendenciaPublicoSuprimidoParcial.getQuantidadeDocumento() == null ){
							resumoPendenciaPublicoSuprimidoParcial
								.setQuantidadeDocumento(resumoPendencia.getQuantidadeDocumento());	
						}else{
							resumoPendenciaPublicoSuprimidoParcial
								.setQuantidadeDocumento(resumoPendenciaPublicoSuprimidoParcial
										.getQuantidadeDocumento()+resumoPendencia.getQuantidadeDocumento());	
						}
						if(resumoPendenciaPublicoSuprimidoParcial.getValorPendente() == null ){
							resumoPendenciaPublicoSuprimidoParcial
								.setValorPendente(resumoPendencia.getValorPendente());	
						}else{
							resumoPendenciaPublicoSuprimidoParcial
								.setValorPendente(resumoPendenciaPublicoSuprimidoParcial
										.getValorPendente().add(resumoPendencia.getValorPendente()));	
						}
					}else if(resumoPendencia.getTipoSituacaoAguaEsgoto().equals("SUPRIMIDO A PEDIDO")){
						if(resumoPendenciaPublicoSuprimidoAPedido.getQuantidadeLigacoes() == null ){
							resumoPendenciaPublicoSuprimidoAPedido
								.setQuantidadeLigacoes(resumoPendencia.getQuantidadeLigacoes());	
						}else{
							resumoPendenciaPublicoSuprimidoAPedido
								.setQuantidadeLigacoes(resumoPendenciaPublicoSuprimidoAPedido
										.getQuantidadeLigacoes()+resumoPendencia.getQuantidadeLigacoes());	
						}
						if(resumoPendenciaPublicoSuprimidoAPedido.getQuantidadeDocumento() == null ){
							resumoPendenciaPublicoSuprimidoAPedido
								.setQuantidadeDocumento(resumoPendencia.getQuantidadeDocumento());	
						}else{
							resumoPendenciaPublicoSuprimidoAPedido
								.setQuantidadeDocumento(resumoPendenciaPublicoSuprimidoAPedido
										.getQuantidadeDocumento()+resumoPendencia.getQuantidadeDocumento());	
						}
						if(resumoPendenciaPublicoSuprimidoAPedido.getValorPendente() == null ){
							resumoPendenciaPublicoSuprimidoAPedido
								.setValorPendente(resumoPendencia.getValorPendente());	
						}else{
							resumoPendenciaPublicoSuprimidoAPedido
								.setValorPendente(resumoPendenciaPublicoSuprimidoAPedido
										.getValorPendente().add(resumoPendencia.getValorPendente()));	
						}
					}
				}
			}

			// Variáveis Particular
	        if( existeParticular >= 2 ){
		        sessao.setAttribute("existeParticular", existeParticular);
	        }
	        if( resumoPendenciaParticularPotencial.getValorPendente() != null ){
	        	sessao.setAttribute("resumoPendenciaParticularPotencial", resumoPendenciaParticularPotencial);
	        }
	        if( resumoPendenciaParticularFactivel.getValorPendente() != null ){
	        	sessao.setAttribute("resumoPendenciaParticularFactivel", resumoPendenciaParticularFactivel);
	        }
	        if( resumoPendenciaParticularLigadoAgua.getValorPendente() != null ){
	        	sessao.setAttribute("resumoPendenciaParticularLigadoAgua", resumoPendenciaParticularLigadoAgua);
	        }
	        if( resumoPendenciaParticularCortado.getValorPendente() != null ){
	        	sessao.setAttribute("resumoPendenciaParticularCortado", resumoPendenciaParticularCortado);
	        }
	        if( resumoPendenciaParticularLigadoSoEsgoto.getValorPendente() != null ){
	        	sessao.setAttribute("resumoPendenciaParticularLigadoSoEsgoto", resumoPendenciaParticularLigadoSoEsgoto);
	        }
	        if( resumoPendenciaParticularEsgotoForaUso.getValorPendente() != null ){
	        	sessao.setAttribute("resumoPendenciaParticularEsgotoForaUso", resumoPendenciaParticularEsgotoForaUso);
	        }
	        if( resumoPendenciaParticularEsgotoTamponado.getValorPendente() != null ){
	        	sessao.setAttribute("resumoPendenciaParticularEsgotoTamponado", resumoPendenciaParticularEsgotoTamponado);
	        }
	        if( resumoPendenciaParticularSuprimidoTotal.getValorPendente() != null ){
	        	sessao.setAttribute("resumoPendenciaParticularSuprimidoTotal", resumoPendenciaParticularSuprimidoTotal);
	        }
	        if( resumoPendenciaParticularSuprimidoParcial.getValorPendente() != null ){
	        	sessao.setAttribute("resumoPendenciaParticularSuprimidoParcial", resumoPendenciaParticularSuprimidoParcial);
	        }
	        if( resumoPendenciaParticularSuprimidoAPedido.getValorPendente() != null ){
	        	sessao.setAttribute("resumoPendenciaParticularSuprimidoAPedido", resumoPendenciaParticularSuprimidoAPedido);
	        }
	        sessao.setAttribute("resumoPendenciaParticularQuantidadeLigacoes", resumoPendenciaParticularQuantidadeLigacoes);
	        sessao.setAttribute("resumoPendenciaParticularQuantidadeDocumento", resumoPendenciaParticularQuantidadeDocumento);
	        sessao.setAttribute("resumoPendenciaParticularValorPendente", resumoPendenciaParticularValorPendente);

	        // Variáveis Público
	        if( existePublico >= 2 ){
		        sessao.setAttribute("existePublico", existePublico);
	        }
	        if( resumoPendenciaPublicoPotencial.getValorPendente() != null ){
	        	sessao.setAttribute("resumoPendenciaPublicoPotencial", resumoPendenciaPublicoPotencial);
	        }
	        if( resumoPendenciaPublicoFactivel.getValorPendente() != null ){
	        	sessao.setAttribute("resumoPendenciaPublicoFactivel", resumoPendenciaPublicoFactivel);
	        }
	        if( resumoPendenciaPublicoLigadoAgua.getValorPendente() != null ){
	        	sessao.setAttribute("resumoPendenciaPublicoLigadoAgua", resumoPendenciaPublicoLigadoAgua);
	        }
	        if( resumoPendenciaPublicoCortado.getValorPendente() != null ){
	        	sessao.setAttribute("resumoPendenciaPublicoCortado", resumoPendenciaPublicoCortado);
	        }
	        if( resumoPendenciaPublicoLigadoSoEsgoto.getValorPendente() != null ){
	        	sessao.setAttribute("resumoPendenciaPublicoLigadoSoEsgoto", resumoPendenciaPublicoLigadoSoEsgoto);
	        }
	        if( resumoPendenciaPublicoEsgotoForaUso.getValorPendente() != null ){
	        	sessao.setAttribute("resumoPendenciaPublicoEsgotoForaUso", resumoPendenciaPublicoEsgotoForaUso);
	        }
	        if( resumoPendenciaPublicoEsgotoTamponado.getValorPendente() != null ){
	        	sessao.setAttribute("resumoPendenciaPublicoEsgotoTamponado", resumoPendenciaPublicoEsgotoTamponado);
	        }
	        if( resumoPendenciaPublicoSuprimidoTotal.getValorPendente() != null ){
	        	sessao.setAttribute("resumoPendenciaPublicoSuprimidoTotal", resumoPendenciaPublicoSuprimidoTotal);
	        }
	        if( resumoPendenciaPublicoSuprimidoParcial.getValorPendente() != null ){
	        	sessao.setAttribute("resumoPendenciaPublicoSuprimidoParcial", resumoPendenciaPublicoSuprimidoParcial);
	        }
	        if( resumoPendenciaPublicoSuprimidoAPedido.getValorPendente() != null ){
	        	sessao.setAttribute("resumoPendenciaPublicoSuprimidoAPedido", resumoPendenciaPublicoSuprimidoAPedido);
	        }
	        sessao.setAttribute("resumoPendenciaPublicoQuantidadeLigacoes", resumoPendenciaPublicoQuantidadeLigacoes);
	        sessao.setAttribute("resumoPendenciaPublicoQuantidadeDocumento", resumoPendenciaPublicoQuantidadeDocumento);
	        sessao.setAttribute("resumoPendenciaPublicoValorPendente", resumoPendenciaPublicoValorPendente);
        }	
        
		/**
		 * Cria coleção de agrupamntos(uma coleção de object[3], agrupamento, id, descricao)
		 */
		Collection colecaoAgrupamento = fachada
				.criarColecaoAgrupamentoResumos(informarDadosGeracaoRelatorioConsultaHelper);
		
		sessao.setAttribute("colecaoAgrupamento", colecaoAgrupamento);
		sessao.setAttribute("mesAnoReferencia", Util.formatarAnoMesParaMesAno(informarDadosGeracaoRelatorioConsultaHelper.getAnoMesReferencia()));
		
        //devolve o mapeamento de retorno
        return retorno;
    }
}
