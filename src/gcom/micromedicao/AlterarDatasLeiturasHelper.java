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

package gcom.micromedicao;

import gcom.util.Util;
import java.util.Date;

/**
 * 
 * Helper contendo as informações necessárias para a exibição do
 * grupo de contas a ser alterado 
 *
 * @author bruno
 * @date 26/02/2009
 */

public class AlterarDatasLeiturasHelper {
    
    private String dtLeituraAnterior;
    private String dtLeituraAtual;
    private Integer qtdImoveis;
    private Integer qtdDias;
    private String dtLeituraAnteriorNova;
    private String dtLeituraAtualNova;

    public AlterarDatasLeiturasHelper(
            Date dtLeituraAnterior, 
            Date dtLeituraAtual, 
            Integer qtdImoveis ) {
        super();

        this.dtLeituraAnterior = Util.formatarData( dtLeituraAnterior );
        this.dtLeituraAtual = Util.formatarData( dtLeituraAtual );
        this.qtdImoveis = qtdImoveis;
        this.qtdDias = 
            Util.obterQuantidadeDiasEntreDuasDatas( 
                    dtLeituraAnterior, dtLeituraAtual );
    }

    public String getDtLeituraAnterior() {
        return dtLeituraAnterior;
    }
    
    public String getDtLeituraAtual() {
        return dtLeituraAtual;
    }
    
    public Integer getQtdDias() {
        return qtdDias;
    }
    
    public void setQtdDias(Integer qtdDias) {
        this.qtdDias = qtdDias;
    }
    
    public Integer getQtdImoveis() {
        return qtdImoveis;
    }
    
    public void setQtdImoveis(Integer qtdImoveis) {
        this.qtdImoveis = qtdImoveis;
    }

    
    public String getDtLeituraAnteriorNova() {
        return dtLeituraAnteriorNova;
    }

    
    public void setDtLeituraAnteriorNova(String dtLeituraAnteriorNova) {
        this.dtLeituraAnteriorNova = dtLeituraAnteriorNova;
    }

    
    public String getDtLeituraAtualNova() {
        return dtLeituraAtualNova;
    }

    
    public void setDtLeituraAtualNova(String dtLeituraAtualNova) {
        this.dtLeituraAtualNova = dtLeituraAtualNova;
    }
}
