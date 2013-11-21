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




/**
 * [UC0991] Filtrar Faturamento Imediato Ajuste
 * 
 * @author Hugo Leonardo
 *
 * @date 26/02/2010
 */

public class FaturamentoImediatoAjusteHelper {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private String mesAnoReferencia;

    /** nullable persistent field */
    private String faturamentoGrupo;

    /** nullable persistent field */
    private String imovelId;
    
    /** nullable persistent field */
    private String rotaId;
    
    private String inscricao;
    
    private String rota;
    
    private String difValorAgua;
    
    private String difConsumoAgua;
    
    private String difValorEsgoto;
    
    private String difConsumoEsgoto;

    /** default constructor */
    public FaturamentoImediatoAjusteHelper() {
    }

	/**
	 * @return Returns the faturamentoGrupo.
	 */
	public String getFaturamentoGrupo() {
		return faturamentoGrupo;
	}

	/**
	 * @param faturamentoGrupo The faturamentoGrupo to set.
	 */
	public void setFaturamentoGrupo(String faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}

	/**
	 * @return Returns the mesAnoReferencia.
	 */
	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	/**
	 * @param mesAnoReferencia The mesAnoReferencia to set.
	 */
	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	/**
	 * @return Returns the imovelId.
	 */
	public String getImovelId() {
		return imovelId;
	}

	/**
	 * @param imovelId The imovelId to set.
	 */
	public void setImovelId(String imovelId) {
		this.imovelId = imovelId;
	}

	/**
	 * @return Returns the rotaId.
	 */
	public String getRotaId() {
		return rotaId;
	}

	/**
	 * @param rotaId The rotaId to set.
	 */
	public void setRotaId(String rotaId) {
		this.rotaId = rotaId;
	}

	/**
	 * @return Returns the difConsumoAgua.
	 */
	public String getDifConsumoAgua() {
		return difConsumoAgua;
	}

	/**
	 * @param difConsumoAgua The difConsumoAgua to set.
	 */
	public void setDifConsumoAgua(String difConsumoAgua) {
		this.difConsumoAgua = difConsumoAgua;
	}

	/**
	 * @return Returns the difConsumoEsgoto.
	 */
	public String getDifConsumoEsgoto() {
		return difConsumoEsgoto;
	}

	/**
	 * @param difConsumoEsgoto The difConsumoEsgoto to set.
	 */
	public void setDifConsumoEsgoto(String difConsumoEsgoto) {
		this.difConsumoEsgoto = difConsumoEsgoto;
	}

	/**
	 * @return Returns the difValorAgua.
	 */
	public String getDifValorAgua() {
		return difValorAgua;
	}

	/**
	 * @param difValorAgua The difValorAgua to set.
	 */
	public void setDifValorAgua(String difValorAgua) {
		this.difValorAgua = difValorAgua;
	}

	/**
	 * @return Returns the difValorEsgoto.
	 */
	public String getDifValorEsgoto() {
		return difValorEsgoto;
	}

	/**
	 * @param difValorEsgoto The difValorEsgoto to set.
	 */
	public void setDifValorEsgoto(String difValorEsgoto) {
		this.difValorEsgoto = difValorEsgoto;
	}

	/**
	 * @return Returns the inscricao.
	 */
	public String getInscricao() {
		return inscricao;
	}

	/**
	 * @param inscricao The inscricao to set.
	 */
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	/**
	 * @return Returns the rota.
	 */
	public String getRota() {
		return rota;
	}

	/**
	 * @param rota The rota to set.
	 */
	public void setRota(String rota) {
		this.rota = rota;
	}
}
