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
package gcom.gui.micromedicao;

import gcom.gui.GcomAction;
import gcom.micromedicao.Rota;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class GerarDadosParaLeituraAction extends GcomAction {
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

    	
//   	Seta o retorno
        ActionForward retorno = actionMapping
                .findForward("telaPrincipal");
//        
//        String txtAntigo = "11111111111111111111";
//        String txtNovo = "22222";
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(txtAntigo);
//        
//        stringBuilder.replace(10,15,txtNovo);
//        
//        StringBuilder stringBuilder1 = new StringBuilder();
//        stringBuilder1 = stringBuilder;
//        
//        //Obtém a instância da fachada
       // Fachada fachada = Fachada.getInstancia();
        
        Collection rotas = new ArrayList();
        
        Rota rota = new Rota();
        rota.setId(572);
        
        rotas.add(rota);
        
        /*Rota rota1 = new Rota();
        
        rota1.setId(3);
        
        rotas.add(rota1); */
        
       // Integer anoMesCorrente = 200601;
        
      //  Integer idLeituraTipo = 2;
        
//        try {
//			Collection gerarDados = fachada.gerarDadosPorLeituraConvencional(rotas,
//					anoMesCorrente, idLeituraTipo);
//		} catch (ControladorException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
//        Imovel imovel = new Imovel();
//        Quadra quadra = new Quadra(); 
//        Rota rota = new Rota();
//        rota.setPercentualGeracaoFaixaFalsa(new BigDecimal(1.5));
//        quadra.setRota(rota);
//        imovel.setQuadra(quadra);
//        imovel.setId(54653212);
//        
////      Faixa de leitura esperada
//
//		SistemaParametro sistemaParametro = null;
//		sistemaParametro = fachada
//					.pesquisarParametrosDoSistema();
//		
//		Integer leituraAnterior = null;
//
//			MedicaoTipo medicaoTipo = new MedicaoTipo();
//
//			medicaoTipo.setId(MedicaoTipo.LIGACAO_AGUA);
//			
//			
//			try {
//				leituraAnterior = fachada.pesquisarLeituraAnteriorTipoLigacaoAgua(imovel.getId());
//			} catch (ControladorException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//			
//			
//			
//        int[] mediaPeriodo = fachada.obterConsumoMedioHidrometro(imovel,sistemaParametro,medicaoTipo);
//        
//        int mediaConsumoHidrometro = mediaPeriodo[0];
//        
//        boolean hidrometroSelecionado = true;
//        MedicaoHistorico medicaoHistorico = new MedicaoHistorico();
//        
//        LeituraSituacao leituraSituacaoAtual = new LeituraSituacao(); 
//        leituraSituacaoAtual.setId(2);
//        medicaoHistorico.setLeituraSituacaoAtual(leituraSituacaoAtual);
//        
//        Hidrometro hidrometro = new Hidrometro();
//        hidrometro.setNumeroDigitosLeitura(new Short("6"));
//        Object[] consumoMinino = null;
//        try {
//			consumoMinino = fachada.calcularFaixaLeituraFalsa(imovel,mediaConsumoHidrometro,leituraAnterior,medicaoHistorico,hidrometroSelecionado,hidrometro);
//		} catch (ControladorException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Object[] consumoMinino1 = consumoMinino;
//       
        return retorno;
    }
}
