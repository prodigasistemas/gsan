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
package gcom.gui.relatorio.financeiro;

import gcom.batch.Processo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.financeiro.RelatorioContasBaixadasContabilmente;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Geração do relatório [UC0726] Gerar Relatório das Contas Baixadas Contabilmente
 * 
 * @author Vivianne Sousa
 * @data 08/04/2008
 */

public class GerarRelatorioContasBaixadasContabilmenteAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

//		ActionForward retorno = null;
        ActionForward retorno = actionMapping.findForward("telaSucesso");
        GerarRelatorioContasBaixadasContabilmenteActionForm form = (GerarRelatorioContasBaixadasContabilmenteActionForm) actionForm;

        //Este map levará todos os parâmetros para a inicialização do relatório
        Map parametros = new HashMap();
        
               
        String referenciaInicial = form.getReferenciaInicial();
        String referenciaFinal = form.getReferenciaFinal();
        Integer referenciaInicialInt = 0;
        Integer referenciaFinalInt = 0;
        String tipoFormato = form.getTipoFormato();
        
        if (referenciaInicial != null && !referenciaInicial.equals("") &&
                !Util.validarMesAno(referenciaInicial)) {
            throw new ActionServletException("atencao.adicionar_debito_ano_mes_referencia_invalido",null,"Inicial");
        }
        if (referenciaFinal != null && !referenciaFinal.equals("") &&
                !Util.validarMesAno(referenciaFinal)) {
            throw new ActionServletException("atencao.adicionar_debito_ano_mes_referencia_invalido",null,"Final");
        }
        
        //[FS0001] - Verificar tipo de relatório
        if (form.getTipo() == null || form.getTipo().equalsIgnoreCase("")) {
          throw new ActionServletException("atencao.required", null, "Tipo de Relatório");
        }
        
        //[FS0002] - Verificar a periodicidade
        if (form.getPeriodicidade() == null || form.getPeriodicidade().equalsIgnoreCase("")) {
          throw new ActionServletException("atencao.required", null, "Periodicidade");
        }
        
        Short tipo = new Short (form.getTipo());
        Short periodicidade = new Short (form.getPeriodicidade());
        //[FS0003] - Verificar atributos inicial e final
        if ((periodicidade.equals(ConstantesSistema.ACUMULADO) || (periodicidade.equals(ConstantesSistema.MENSAL))) &&
            (referenciaFinal == null || referenciaFinal.equalsIgnoreCase(""))) {
          throw new ActionServletException("atencao.required", null, "Referência das Faturas Final");
        }else{
            referenciaFinalInt = new Integer(Util
                    .formatarMesAnoParaAnoMesSemBarra(referenciaFinal));
        }
        
        if (periodicidade.equals(ConstantesSistema.MENSAL)) {
            
            if((referenciaInicial != null && !referenciaInicial.equalsIgnoreCase("")) &&
               (referenciaFinal == null || referenciaFinal.equalsIgnoreCase(""))){
                
                throw new ActionServletException("atencao.required", null, "Referência das Faturas Final");
            }
            
            if((referenciaFinal != null && !referenciaFinal.equalsIgnoreCase("")) &&
                (referenciaInicial == null || referenciaInicial.equalsIgnoreCase(""))){
                 
                 throw new ActionServletException("atencao.required", null, "Referência das Faturas Inicial");
            }
            
            if (referenciaInicial != null && !referenciaInicial.equalsIgnoreCase("")&&
                referenciaFinal != null && !referenciaFinal.equalsIgnoreCase("")) {

                referenciaInicialInt = new Integer(Util
                        .formatarMesAnoParaAnoMesSemBarra(referenciaInicial));

                referenciaFinalInt = new Integer(Util
                        .formatarMesAnoParaAnoMesSemBarra(referenciaFinal));
                
                if (Util.compararAnoMesReferencia(referenciaInicialInt, referenciaFinalInt, ">")) {
                    throw new ActionServletException(
                    "atencao.referencia_fatura_final_menor_referencia_fatura_inicial");
                }

            }
           
        }
        
        if (tipoFormato == null || tipoFormato.equals("")){
        	throw new ActionServletException(
            "atencao.relatorio_tipo_nao_informado");
        }
        
        parametros.put("tipo",tipo);
        parametros.put("periodicidade",periodicidade);
        parametros.put("referenciaInicial",referenciaInicialInt);
        parametros.put("referenciaFinal",referenciaFinalInt);
        parametros.put("tipoFormatoRelatorio", tipoFormato);
        

        if (tipoFormato.equals("TXT")){
        
        Fachada.getInstancia().inserirProcessoIniciadoParametrosLivres(parametros, 
        		Processo.GERAR_TXT_CONTAS_BAIXADAS_CONTABILMENTE ,
        		this.getUsuarioLogado(httpServletRequest));
        
        
        } else 
        	if (tipoFormato.equals("PDF")){
        	
        		RelatorioContasBaixadasContabilmente relatorioContasBaixadasContabilmente = new RelatorioContasBaixadasContabilmente((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
    			
        		relatorioContasBaixadasContabilmente.addParametro("tipo", tipo);
        		relatorioContasBaixadasContabilmente.addParametro("periodicidade", periodicidade);
        		relatorioContasBaixadasContabilmente.addParametro("referenciaInicial", referenciaInicialInt);
        		relatorioContasBaixadasContabilmente.addParametro("referenciaFinal", referenciaFinalInt);
        		relatorioContasBaixadasContabilmente.addParametro("tipoFormatoRelatorio", TarefaRelatorio.TIPO_PDF);
    			String tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
    			
    			try {
    				retorno = processarExibicaoRelatorio(relatorioContasBaixadasContabilmente,
    						tipoRelatorio, httpServletRequest, httpServletResponse,
    						actionMapping);

    			} catch (RelatorioVazioException ex) {
    				// manda o erro para a página no request atual
    				reportarErros(httpServletRequest, "atencao.relatorio.vazio");

    				// seta o mapeamento de retorno para a tela de atenção de popup
    				retorno = actionMapping.findForward("telaAtencaoPopup");
    			}
        	
        }
        
        montarPaginaSucesso(httpServletRequest, "Relatório foi para batch.",
                "",
                "");
        
		return retorno;
	}
	
}
