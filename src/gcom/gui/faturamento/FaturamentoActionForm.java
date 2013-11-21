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

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FaturamentoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
    private String idFaturamentoGrupoCronogramaMensal;

    private String idFaturamentoAtividadeCronograma;

    private String idGrupoFaturamento;

    private String mesAno;
    
    private String comandar;
    
    private String auxIDHidden;

    public String getComandar() {
		return comandar;
	}

	public void setComandar(String comandar) {
		this.comandar = comandar;
	}

	/**
     * Construtor da classe FaturamentoActionForm
     */
    public FaturamentoActionForm() {
    }

    /**
     * Retorna o valor de idFaturamentoAtividadeCronograma
     * 
     * @return O valor de idFaturamentoAtividadeCronograma
     */
    public String getIdFaturamentoAtividadeCronograma() {
        return idFaturamentoAtividadeCronograma;
    }

    /**
     * Seta o valor de idFaturamentoAtividadeCronograma
     * 
     * @param idFaturamentoAtividadeCronograma
     *            O novo valor de idFaturamentoAtividadeCronograma
     */
    public void setIdFaturamentoAtividadeCronograma(
            String idFaturamentoAtividadeCronograma) {
        this.idFaturamentoAtividadeCronograma = idFaturamentoAtividadeCronograma;
    }

    /**
     * Retorna o valor de idFaturamentoGrupoCronogramaMensal
     * 
     * @return O valor de idFaturamentoGrupoCronogramaMensal
     */
    public String getIdFaturamentoGrupoCronogramaMensal() {
        return idFaturamentoGrupoCronogramaMensal;
    }

    /**
     * Seta o valor de idFaturamentoGrupoCronogramaMensal
     * 
     * @param idFaturamentoGrupoCronogramaMensal
     *            O novo valor de idFaturamentoGrupoCronogramaMensal
     */
    public void setIdFaturamentoGrupoCronogramaMensal(
            String idFaturamentoGrupoCronogramaMensal) {
        this.idFaturamentoGrupoCronogramaMensal = idFaturamentoGrupoCronogramaMensal;
    }

    /**
     * Retorna o valor de idGrupoFaturamento
     * 
     * @return O valor de idGrupoFaturamento
     */
    public String getIdGrupoFaturamento() {
        return idGrupoFaturamento;
    }

    /**
     * Seta o valor de idGrupoFaturamento
     * 
     * @param idGrupoFaturamento
     *            O novo valor de idGrupoFaturamento
     */
    public void setIdGrupoFaturamento(String idGrupoFaturamento) {
        this.idGrupoFaturamento = idGrupoFaturamento;
    }

    /**
     * Retorna o valor de mesAno
     * 
     * @return O valor de mesAno
     */
    public String getMesAno() {
        return mesAno;
    }

    /**
     * Seta o valor de mesAno
     * 
     * @param mesAno
     *            O novo valor de mesAno
     */
    public void setMesAno(String mesAno) {
        this.mesAno = mesAno;
    }

	/**
	 * @return Returns the auxIDHidden.
	 */
	public String getAuxIDHidden() {
		return auxIDHidden;
	}

	/**
	 * @param auxIDHidden The auxIDHidden to set.
	 */
	public void setAuxIDHidden(String auxIDHidden) {
		this.auxIDHidden = auxIDHidden;
	}
	
}
