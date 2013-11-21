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
package gcom.cadastro.imovel.bean;

import gcom.util.Util;

import java.util.Date;


public class ImovelCobrancaSituacaoHelper {
	
	private static final long serialVersionUID = 1L;
	
	private String descricaoSituacaoCobranca;
    private Integer anoMesReferenciaInicio = 0;
    private Integer anoMesReferenciaFinal = 0;
	private Date dataImplantacaoCobranca;
	private Date dataRetiradaCobranca;
	private Integer idClienteAlvo;
	private String escritorioCobranca; 
	private String advogadoResponsavelCobranca;
	
	//Relatorio de Dados Complementares do Imóvel
	
	public String getAdvogadoResponsavelCobranca() {
		return advogadoResponsavelCobranca;
	}
	public void setAdvogadoResponsavelCobranca(String advogadoResponsavelCobranca) {
		this.advogadoResponsavelCobranca = advogadoResponsavelCobranca;
	}
	public Integer getAnoMesReferenciaFinal() {
		return anoMesReferenciaFinal;
	}
	public void setAnoMesReferenciaFinal(Integer anoMesReferenciaFinal) {
		this.anoMesReferenciaFinal = anoMesReferenciaFinal;
	}
	public Integer getAnoMesReferenciaInicio() {
		return anoMesReferenciaInicio;
	}
	public void setAnoMesReferenciaInicio(Integer anoMesReferenciaInicio) {
		this.anoMesReferenciaInicio = anoMesReferenciaInicio;
	}
	public Date getDataImplantacaoCobranca() {
		return dataImplantacaoCobranca;
	}
	public void setDataImplantacaoCobranca(Date dataImplantacaoCobranca) {
		this.dataImplantacaoCobranca = dataImplantacaoCobranca;
	}
	public Date getDataRetiradaCobranca() {
		return dataRetiradaCobranca;
	}
	public void setDataRetiradaCobranca(Date dataRetiradaCobranca) {
		this.dataRetiradaCobranca = dataRetiradaCobranca;
	}
	public String getDescricaoSituacaoCobranca() {
		return descricaoSituacaoCobranca;
	}
	public void setDescricaoSituacaoCobranca(String descricaoSituacaoCobranca) {
		this.descricaoSituacaoCobranca = descricaoSituacaoCobranca;
	}
	public String getEscritorioCobranca() {
		return escritorioCobranca;
	}
	public void setEscritorioCobranca(String escritorioCobranca) {
		this.escritorioCobranca = escritorioCobranca;
	}
	public Integer getIdClienteAlvo() {
		return idClienteAlvo;
	}
	public void setIdClienteAlvo(Integer idClienteAlvo) {
		this.idClienteAlvo = idClienteAlvo;
	}
	public String getAnoMesReferencia() {
		String retorno = "";
		
		if(this.anoMesReferenciaInicio != null && !this.anoMesReferenciaInicio.equals(new Integer(0))
				&& this.anoMesReferenciaFinal != null && !this.anoMesReferenciaFinal.equals(new Integer(0))){
			
			retorno = this.anoMesReferenciaInicio + " a " + this.anoMesReferenciaFinal;
		}
		
		return retorno;
	}
	public String getDataImplantacaoCobrancaString() {
		String retorno = "";
		if(this.dataImplantacaoCobranca != null){
			retorno = Util.formatarData(this.dataImplantacaoCobranca);
		}
		return retorno;
	}
	public String getDataRetiradaCobrancaString() {
		String retorno = "";
		if(this.dataRetiradaCobranca != null){
			retorno = Util.formatarData(this.dataRetiradaCobranca);
		}
		return retorno;
	}
	public String getIdClienteAlvoString() {
		String retorno = "";
		if(this.idClienteAlvo != null){
			retorno = this.idClienteAlvo.toString();
		}
		return retorno;
	}
	

}
