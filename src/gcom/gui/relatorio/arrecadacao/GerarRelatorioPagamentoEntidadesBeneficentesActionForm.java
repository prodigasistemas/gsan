/*
* Copyright (C) 2007-2007 the GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place – Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
package gcom.gui.relatorio.arrecadacao;

import org.apache.struts.action.ActionForm;
/**
 * action responsável pela exibição do Relatório de Pagamento para Entidades Beneficentes
 * [UC0959]
 * @author Daniel Alves
 * @created 13/01/2010
 */
public class GerarRelatorioPagamentoEntidadesBeneficentesActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String tipo;
	
	private String tipoRelatorio;
	
	private String idEntidadeBeneficente;
	
	private String mesAnoInicial;
	
	private String mesAnoFinal;

	private String indicadorEstado;

	private String indicadorGerenciaRegional;	               
		
	private String indicadorUnidadeNegocio;
	
	private String indicadorLocalidade;
	
	private String idGerenciaRegional;
	
	private String idUnidadeNegocio;
	
	private String idLocalidade;
	
	private int opcaoTotalizacao;
	
	
	/*public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
		mesAnoInicial = null;
		mesAnoFinal = null;		
		tipo = null;
	}*/

	
	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getIdEntidadeBeneficente() {
		return idEntidadeBeneficente;
	}


	public void setIdEntidadeBeneficente(String idEntidadeBeneficente) {
		this.idEntidadeBeneficente = idEntidadeBeneficente;
	}


	public String getMesAnoInicial() {
		return mesAnoInicial;
	}


	public void setMesAnoInicial(String mesAnoInicial) {
		this.mesAnoInicial = mesAnoInicial;
	}


	public String getMesAnoFinal() {
		return mesAnoFinal;
	}


	public void setMesAnoFinal(String mesAnoFinal) {
		this.mesAnoFinal = mesAnoFinal;
	}


	public String getIndicadorEstado() {
		return indicadorEstado;
	}


	public void setIndicadorEstado(String indicadorEstado) {
		this.indicadorEstado = indicadorEstado;
	}

	
	public String getIndicadorUnidadeNegocio() {
		return indicadorUnidadeNegocio;
	}


	public void setIndicadorUnidadeNegocio(String indicadorUnidadeNegocio) {
		this.indicadorUnidadeNegocio = indicadorUnidadeNegocio;
	}


	public String getIndicadorGerenciaRegional() {
		return indicadorGerenciaRegional;
	}


	public void setIndicadorGerenciaRegional(String indicadorGerenciaRegional) {
		this.indicadorGerenciaRegional = indicadorGerenciaRegional;
	}
	
	
	public String getIndicadorLocalidade() {
		return indicadorLocalidade;
	}


	public void setIndicadorLocalidade(String indicadorLocalidade) {
		this.indicadorLocalidade = indicadorLocalidade;
	}


	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}


	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}


	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}


	public String getIdLocalidade() {
		return idLocalidade;
	}


	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}


	public int getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}


	public void setOpcaoTotalizacao(int opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}


	public String getTipoRelatorio() {
		return tipoRelatorio;
	}


	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}
	
	
}