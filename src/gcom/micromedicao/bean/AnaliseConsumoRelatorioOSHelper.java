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
package gcom.micromedicao.bean;

import java.io.Serializable;


/**
 * Relatorio Ordem de Serviço
 * 
 * @author Vivianne Sousa
 * @date 07/06/2007
 */
public class AnaliseConsumoRelatorioOSHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String anoMesHistoricoConsumo1;
	private String dtLeituraAtualInformada1;
	private String leituraAtualInformada1;
	private String consumoFaturado1;
	private String descAbrevAnormalidadeConsumo1;
	private String descAbrevAnormalidadeLeitura1;
	
	private String anoMesHistoricoConsumo2;
	private String dtLeituraAtualInformada2;
	private String leituraAtualInformada2;
	private String consumoFaturado2;
	private String descAbrevAnormalidadeConsumo2;
	private String descAbrevAnormalidadeLeitura2;
	
	private String anoMesHistoricoConsumo3;
	private String dtLeituraAtualInformada3;
	private String leituraAtualInformada3;
	private String consumoFaturado3;
	private String descAbrevAnormalidadeConsumo3;
	private String descAbrevAnormalidadeLeitura3;
	
	private String anoMesHistoricoConsumo4;
	private String dtLeituraAtualInformada4;
	private String leituraAtualInformada4;
	private String consumoFaturado4;
	private String descAbrevAnormalidadeConsumo4;
	private String descAbrevAnormalidadeLeitura4;
	
	private String anoMesHistoricoConsumo5;
	private String dtLeituraAtualInformada5;
	private String leituraAtualInformada5;
	private String consumoFaturado5;
	private String descAbrevAnormalidadeConsumo5;
	private String descAbrevAnormalidadeLeitura5;
	
	private String anoMesHistoricoConsumo6;
	private String dtLeituraAtualInformada6;
	private String leituraAtualInformada6;
	private String consumoFaturado6;
	private String descAbrevAnormalidadeConsumo6;
	private String descAbrevAnormalidadeLeitura6;
	
	public String getAnoMesHistoricoConsumo1() {
		return anoMesHistoricoConsumo1;
	}
	public void setAnoMesHistoricoConsumo1(String anoMesHistoricoConsumo1) {
		this.anoMesHistoricoConsumo1 = anoMesHistoricoConsumo1;
	}
	public String getAnoMesHistoricoConsumo2() {
		return anoMesHistoricoConsumo2;
	}
	public void setAnoMesHistoricoConsumo2(String anoMesHistoricoConsumo2) {
		this.anoMesHistoricoConsumo2 = anoMesHistoricoConsumo2;
	}
	public String getAnoMesHistoricoConsumo3() {
		return anoMesHistoricoConsumo3;
	}
	public void setAnoMesHistoricoConsumo3(String anoMesHistoricoConsumo3) {
		this.anoMesHistoricoConsumo3 = anoMesHistoricoConsumo3;
	}
	public String getAnoMesHistoricoConsumo4() {
		return anoMesHistoricoConsumo4;
	}
	public void setAnoMesHistoricoConsumo4(String anoMesHistoricoConsumo4) {
		this.anoMesHistoricoConsumo4 = anoMesHistoricoConsumo4;
	}
	public String getAnoMesHistoricoConsumo5() {
		return anoMesHistoricoConsumo5;
	}
	public void setAnoMesHistoricoConsumo5(String anoMesHistoricoConsumo5) {
		this.anoMesHistoricoConsumo5 = anoMesHistoricoConsumo5;
	}
	public String getAnoMesHistoricoConsumo6() {
		return anoMesHistoricoConsumo6;
	}
	public void setAnoMesHistoricoConsumo6(String anoMesHistoricoConsumo6) {
		this.anoMesHistoricoConsumo6 = anoMesHistoricoConsumo6;
	}
	public String getConsumoFaturado1() {
		return consumoFaturado1;
	}
	public void setConsumoFaturado1(String consumoFaturado1) {
		this.consumoFaturado1 = consumoFaturado1;
	}
	public String getConsumoFaturado2() {
		return consumoFaturado2;
	}
	public void setConsumoFaturado2(String consumoFaturado2) {
		this.consumoFaturado2 = consumoFaturado2;
	}
	public String getConsumoFaturado3() {
		return consumoFaturado3;
	}
	public void setConsumoFaturado3(String consumoFaturado3) {
		this.consumoFaturado3 = consumoFaturado3;
	}
	public String getConsumoFaturado4() {
		return consumoFaturado4;
	}
	public void setConsumoFaturado4(String consumoFaturado4) {
		this.consumoFaturado4 = consumoFaturado4;
	}
	public String getConsumoFaturado5() {
		return consumoFaturado5;
	}
	public void setConsumoFaturado5(String consumoFaturado5) {
		this.consumoFaturado5 = consumoFaturado5;
	}
	public String getConsumoFaturado6() {
		return consumoFaturado6;
	}
	public void setConsumoFaturado6(String consumoFaturado6) {
		this.consumoFaturado6 = consumoFaturado6;
	}
	public String getDescAbrevAnormalidadeConsumo1() {
		return descAbrevAnormalidadeConsumo1;
	}
	public void setDescAbrevAnormalidadeConsumo1(
			String descAbrevAnormalidadeConsumo1) {
		this.descAbrevAnormalidadeConsumo1 = descAbrevAnormalidadeConsumo1;
	}
	public String getDescAbrevAnormalidadeConsumo2() {
		return descAbrevAnormalidadeConsumo2;
	}
	public void setDescAbrevAnormalidadeConsumo2(
			String descAbrevAnormalidadeConsumo2) {
		this.descAbrevAnormalidadeConsumo2 = descAbrevAnormalidadeConsumo2;
	}
	public String getDescAbrevAnormalidadeConsumo3() {
		return descAbrevAnormalidadeConsumo3;
	}
	public void setDescAbrevAnormalidadeConsumo3(
			String descAbrevAnormalidadeConsumo3) {
		this.descAbrevAnormalidadeConsumo3 = descAbrevAnormalidadeConsumo3;
	}
	public String getDescAbrevAnormalidadeConsumo4() {
		return descAbrevAnormalidadeConsumo4;
	}
	public void setDescAbrevAnormalidadeConsumo4(
			String descAbrevAnormalidadeConsumo4) {
		this.descAbrevAnormalidadeConsumo4 = descAbrevAnormalidadeConsumo4;
	}
	public String getDescAbrevAnormalidadeConsumo5() {
		return descAbrevAnormalidadeConsumo5;
	}
	public void setDescAbrevAnormalidadeConsumo5(
			String descAbrevAnormalidadeConsumo5) {
		this.descAbrevAnormalidadeConsumo5 = descAbrevAnormalidadeConsumo5;
	}
	public String getDescAbrevAnormalidadeConsumo6() {
		return descAbrevAnormalidadeConsumo6;
	}
	public void setDescAbrevAnormalidadeConsumo6(
			String descAbrevAnormalidadeConsumo6) {
		this.descAbrevAnormalidadeConsumo6 = descAbrevAnormalidadeConsumo6;
	}
	public String getDtLeituraAtualInformada1() {
		return dtLeituraAtualInformada1;
	}
	public void setDtLeituraAtualInformada1(String dtLeituraAtualInformada1) {
		this.dtLeituraAtualInformada1 = dtLeituraAtualInformada1;
	}
	public String getDtLeituraAtualInformada2() {
		return dtLeituraAtualInformada2;
	}
	public void setDtLeituraAtualInformada2(String dtLeituraAtualInformada2) {
		this.dtLeituraAtualInformada2 = dtLeituraAtualInformada2;
	}
	public String getDtLeituraAtualInformada3() {
		return dtLeituraAtualInformada3;
	}
	public void setDtLeituraAtualInformada3(String dtLeituraAtualInformada3) {
		this.dtLeituraAtualInformada3 = dtLeituraAtualInformada3;
	}
	public String getDtLeituraAtualInformada4() {
		return dtLeituraAtualInformada4;
	}
	public void setDtLeituraAtualInformada4(String dtLeituraAtualInformada4) {
		this.dtLeituraAtualInformada4 = dtLeituraAtualInformada4;
	}
	public String getDtLeituraAtualInformada5() {
		return dtLeituraAtualInformada5;
	}
	public void setDtLeituraAtualInformada5(String dtLeituraAtualInformada5) {
		this.dtLeituraAtualInformada5 = dtLeituraAtualInformada5;
	}
	public String getDtLeituraAtualInformada6() {
		return dtLeituraAtualInformada6;
	}
	public void setDtLeituraAtualInformada6(String dtLeituraAtualInformada6) {
		this.dtLeituraAtualInformada6 = dtLeituraAtualInformada6;
	}
	public String getLeituraAtualInformada1() {
		return leituraAtualInformada1;
	}
	public void setLeituraAtualInformada1(String leituraAtualInformada1) {
		this.leituraAtualInformada1 = leituraAtualInformada1;
	}
	public String getLeituraAtualInformada2() {
		return leituraAtualInformada2;
	}
	public void setLeituraAtualInformada2(String leituraAtualInformada2) {
		this.leituraAtualInformada2 = leituraAtualInformada2;
	}
	public String getLeituraAtualInformada3() {
		return leituraAtualInformada3;
	}
	public void setLeituraAtualInformada3(String leituraAtualInformada3) {
		this.leituraAtualInformada3 = leituraAtualInformada3;
	}
	public String getLeituraAtualInformada4() {
		return leituraAtualInformada4;
	}
	public void setLeituraAtualInformada4(String leituraAtualInformada4) {
		this.leituraAtualInformada4 = leituraAtualInformada4;
	}
	public String getLeituraAtualInformada5() {
		return leituraAtualInformada5;
	}
	public void setLeituraAtualInformada5(String leituraAtualInformada5) {
		this.leituraAtualInformada5 = leituraAtualInformada5;
	}
	public String getLeituraAtualInformada6() {
		return leituraAtualInformada6;
	}
	public void setLeituraAtualInformada6(String leituraAtualInformada6) {
		this.leituraAtualInformada6 = leituraAtualInformada6;
	}
	public String getDescAbrevAnormalidadeLeitura1() {
		return descAbrevAnormalidadeLeitura1;
	}
	public void setDescAbrevAnormalidadeLeitura1(
			String descAbrevAnormalidadeLeitura1) {
		this.descAbrevAnormalidadeLeitura1 = descAbrevAnormalidadeLeitura1;
	}
	public String getDescAbrevAnormalidadeLeitura2() {
		return descAbrevAnormalidadeLeitura2;
	}
	public void setDescAbrevAnormalidadeLeitura2(
			String descAbrevAnormalidadeLeitura2) {
		this.descAbrevAnormalidadeLeitura2 = descAbrevAnormalidadeLeitura2;
	}
	public String getDescAbrevAnormalidadeLeitura3() {
		return descAbrevAnormalidadeLeitura3;
	}
	public void setDescAbrevAnormalidadeLeitura3(
			String descAbrevAnormalidadeLeitura3) {
		this.descAbrevAnormalidadeLeitura3 = descAbrevAnormalidadeLeitura3;
	}
	public String getDescAbrevAnormalidadeLeitura4() {
		return descAbrevAnormalidadeLeitura4;
	}
	public void setDescAbrevAnormalidadeLeitura4(
			String descAbrevAnormalidadeLeitura4) {
		this.descAbrevAnormalidadeLeitura4 = descAbrevAnormalidadeLeitura4;
	}
	public String getDescAbrevAnormalidadeLeitura5() {
		return descAbrevAnormalidadeLeitura5;
	}
	public void setDescAbrevAnormalidadeLeitura5(
			String descAbrevAnormalidadeLeitura5) {
		this.descAbrevAnormalidadeLeitura5 = descAbrevAnormalidadeLeitura5;
	}
	public String getDescAbrevAnormalidadeLeitura6() {
		return descAbrevAnormalidadeLeitura6;
	}
	public void setDescAbrevAnormalidadeLeitura6(
			String descAbrevAnormalidadeLeitura6) {
		this.descAbrevAnormalidadeLeitura6 = descAbrevAnormalidadeLeitura6;
	}
	
	
	
	
}
