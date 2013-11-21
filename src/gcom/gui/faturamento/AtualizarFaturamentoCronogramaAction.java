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
package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FaturamentoGrupoCronogramaMensal;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class AtualizarFaturamentoCronogramaAction extends GcomAction {
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

        ActionForward retorno = actionMapping.findForward("telaSucesso");

        FaturamentoActionForm faturamentoActionForm = (FaturamentoActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();

        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);
        //Variavel para testar se o campo naum obrigatorio esta vazio
       // String testeFaturamentoNaoObrigatorio = "0";

     //   String idFaturamentoGrupoCronograma = (String) sessao
    //            .getAttribute("idGrupoFaturamento");
        
        //Estava sendo usado pelo trecho comentado por Anderson Italo
        //data:20/04/2010
        //SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
        
        String mesAno = faturamentoActionForm.getMesAno();
        if(mesAno == null){
        	mesAno = (String)sessao.getAttribute("mesAno");
        }
//        Collection faturamentoAtividades = (Collection) sessao
//                .getAttribute("faturamentoAtividades");
//        Collection faturamentoAtividadeCronogramasVelhas = (Collection) sessao
//                .getAttribute("faturamentoAtividadeCronogramas");
        
        Collection colecaoFaturamentoAtividadeCronograma = (Collection) sessao
        	.getAttribute("colecaoFaturamentoAtividadeCronograma");
        
        Collection faturamentoAtividadeCronogramas = new ArrayList();
        
        //Colecao para guadar todas as atividades inclusive as que não vão ser inseridas ou alteradas
        Collection colecaoTodasAtividades = new ArrayList();
        
        FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = null;

        FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal = (FaturamentoGrupoCronogramaMensal)sessao.getAttribute("faturamentoGrupoCronogramaMensalSessao");
        
        //Instancia objeto FaturamenatoGrupo
        FiltroFaturamentoGrupo filtroFaturamentoGrupoObjeto = new FiltroFaturamentoGrupo();
        filtroFaturamentoGrupoObjeto.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID, faturamentoGrupoCronogramaMensal
                .getFaturamentoGrupo().getId()));
        
        Collection colecaoFaturamentoGrupoObjeto = fachada.pesquisar(filtroFaturamentoGrupoObjeto, FaturamentoGrupo.class.getName());
        
        FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) Util.retonarObjetoDeColecao(colecaoFaturamentoGrupoObjeto);

//        faturamentoGrupo.setId(faturamentoGrupoCronogramaMensal
//                .getFaturamentoGrupo().getId());
        
        //Concatena ano mes para insercao
        String mes = mesAno.substring(0, 2);
        String ano = mesAno.substring(3, 7);

        mesAno = ano + "" + mes;
       	Iterator iteratorFaturamentoAtividadeCronogramas = colecaoFaturamentoAtividadeCronograma.iterator();
    		while (iteratorFaturamentoAtividadeCronogramas.hasNext()) {
    			
    			FaturamentoAtividadeAtualizarHelp fatAtividadeAtualizarHelp = (FaturamentoAtividadeAtualizarHelp)
    			iteratorFaturamentoAtividadeCronogramas.next();
    			
    			faturamentoAtividadeCronograma = fatAtividadeAtualizarHelp.
    			getFaturamentoAtividadeCronograma();
    			
	            
	            String dataPrevista = (String) httpServletRequest.getParameter("n"
	                    + fatAtividadeAtualizarHelp.getIdFaturamentoAtividade().toString());
	                      
	           if ( dataPrevista != null && !dataPrevista.equals("") && Util.validarDiaMesAno(dataPrevista) ) {
	        	   throw new ActionServletException("atencao.anomesreferencia.invalida", null , ""+dataPrevista);
	           }
    			
    			//String dataPrevista = fatAtividadeAtualizarHelp.getDataPrevista();
	            SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
	
	            try {
	                if (dataPrevista != null && !dataPrevista.trim().equalsIgnoreCase("")) {
	                    faturamentoAtividadeCronograma.setDataPrevista(data
	                            .parse(dataPrevista));
	                    faturamentoAtividadeCronogramas
	                            .add(faturamentoAtividadeCronograma);
	                    colecaoTodasAtividades.add(faturamentoAtividadeCronograma);
	                } else if (faturamentoAtividadeCronograma
	                        .getFaturamentoAtividade()
	                        .getIndicadorObrigatoriedadeAtividade().intValue() == 1 &&
	                        (dataPrevista == null || dataPrevista.trim().equalsIgnoreCase(""))) {
	                    throw new ActionServletException(
	                    "atencao.campos_obrigatorios_cronograma_nao_preenchidos");
	                }else if (faturamentoAtividadeCronograma
	                        .getFaturamentoAtividade()
	                        .getIndicadorObrigatoriedadeAtividade().intValue() == 1) {
	                //	testeFaturamentoNaoObrigatorio = "1";
	                	colecaoTodasAtividades.add(faturamentoAtividadeCronograma);
	                }else{
	                	faturamentoAtividadeCronograma.setDataPrevista(null);
	                	colecaoTodasAtividades.add(faturamentoAtividadeCronograma);
	                	faturamentoAtividadeCronogramas.add(faturamentoAtividadeCronograma);
	                }
	                
	                //se foi marcado o check de comandar a atividade
	                //sera atribuido o valor de comando
	                //este valor será igual ao da data prevista            
	                String comandar = (String) httpServletRequest.getParameter("c"
	                        + faturamentoAtividadeCronograma.getId().toString());
	                        
	               if(comandar != null && !comandar.trim().equals("")
	            		   && dataPrevista != null && !dataPrevista.trim().equals("")){
		            	   
	            	   /* comentado por: Anderson Italo
		                * analista que solicitou: Aryede Lins
		                * motivo: Adequar-se ao que estava descrito no UC
		                * 4.2.1.	Atualiza a data e hora do comando (FTAC_TMCOMANDO=CURRENT TIMESTAMP)
		                * data: 20/04/2010*/
	            	   
	            	   	/*int diaVencimento = faturamentoGrupo.getDiaVencimento()
	                       .intValue();
			               String mesVencimento = String.valueOf(
			                       faturamentoGrupo.getAnoMesReferencia()
			                               .intValue()).substring(4, 6);
			               String anoVencimento = String.valueOf(
			                       faturamentoGrupo.getAnoMesReferencia()
			                               .intValue()).substring(0, 4);*/
			
			               /*
			                * Colocado por Raphael Rossiter em 05/05/2007
			                * Obtendo a data de vencimento do grupo
			                */ 
			               /*Date dataVencimento = fachada.obterDataVencimentoFaturamentoGrupo(diaVencimento, 
			               new Integer(mesVencimento), new Integer(anoVencimento));
			               faturamentoAtividadeCronograma.setComando(Util.converteStringParaDate(formatoData.format(dataVencimento)));
			               */
			               
			               faturamentoAtividadeCronograma.setComando(new Date());
	               }else{
	            	   faturamentoAtividadeCronograma.setComando(null);
	               }
	               
	            } catch (ParseException ex) {
	                throw new ActionServletException("errors.date", null,
	                        "data prevista");
	            }
	            
	        }

    		//Validação das seguencia das datas no cronograma e predecessora
    		fachada.validarFaturamentoCronogramaAtividadeMaiorQueMesAnoCronograma((new Integer(mesAno)).intValue(), colecaoTodasAtividades);
    		fachada.validarFaturamentoCronograma(colecaoTodasAtividades);
    		
    		

        /*if (faturamentoAtividadeCronogramas.size() < 5
                || (faturamentoAtividadeCronogramas.size() == 5 && testeFaturamentoNaoObrigatorio
                        .trim().equalsIgnoreCase("0"))) {
            throw new ActionServletException(
                    "atencao.campos_obrigatorios_cronograma_nao_preenchidos");
        }*/

        fachada.atualizarFaturamentoGrupoCronogramaMensal(
                faturamentoGrupoCronogramaMensal,
                faturamentoAtividadeCronogramas, colecaoTodasAtividades,usuarioLogado);
        
        
        FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
        filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID, faturamentoGrupo.getId()));
        Collection colecaoFaturamentoGrupo = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
        FaturamentoGrupo faturamentoGrupoDescricao = new FaturamentoGrupo();
        if(!colecaoFaturamentoGrupo.isEmpty()){
        	faturamentoGrupoDescricao = (FaturamentoGrupo)colecaoFaturamentoGrupo.iterator().next();
        }

        sessao.removeAttribute("faturamentoAtividades");
        sessao.removeAttribute("idGrupoFaturamento");
        montarPaginaSucesso(httpServletRequest,
        		"Cronograma de Faturamento do grupo "+ faturamentoGrupoDescricao.getId() 
                +" referente ao mês/ano: " + faturamentoGrupoCronogramaMensal.getMesAno() +" atualizado com sucesso.",
                "Realizar outra Manutenção de Cronograma de Faturamento",
                "exibirFiltrarFaturamentoCronogramaAction.do");

        return retorno;
    }

}
