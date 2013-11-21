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

import org.apache.struts.action.ActionForm;

/**
 * Classe responsável por ajudar o caso de uso [UC0269] Resumo de Ligacoes Economias 
 *
 * @author Thiago Toscano
 * @date 20/04/2006 
 */
public class GerarRelatorioAnormalidadeLeituraPeriodoActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	
	private String idUnidadeNegocio;

	private String idLocalidadeInicial;
	private String nomeLocalidadeInicial;
	
	private String idLocalidadeFinal;
	private String nomeLocalidadeFinal;

//	private String idSetorComercialInicial; 
//	private String idSetorComercialFinal;

	private String codigoSetorComercialInicial;
	private String codigoSetorComercialFinal;

	private String nomeSetorComercialInicial;
	private String nomeSetorComercialFinal;

	private String codigoRotaInicial;
	private String codigoRotaFinal;
	
	private String sequencialRotaInicial;
	private String sequencialRotaFinal;
	
	private String mesAnoReferenciaInicial;
	private String mesAnoReferenciaFinal;
	
	private String idAnormalidadeLeitura;
	
	private String idGrupoFaturamento;

	
	public void limparForm(){
		 idUnidadeNegocio= "";

		 idLocalidadeInicial= "";
		 nomeLocalidadeInicial= "";
		
		 idLocalidadeFinal= "";
		 nomeLocalidadeFinal= "";

//		 idSetorComercialInicial= ""; 
//		 idSetorComercialFinal= "";

		 codigoSetorComercialInicial= "";
		 codigoSetorComercialFinal= "";

		 nomeSetorComercialInicial= "";
		 nomeSetorComercialFinal= "";

		 codigoRotaInicial= "";
		 codigoRotaFinal= "";
		
		 sequencialRotaInicial= "";
		 sequencialRotaFinal= "";
		
		 mesAnoReferenciaInicial= "";
		 mesAnoReferenciaFinal= "";
		
		 idAnormalidadeLeitura= "";
		
		 idGrupoFaturamento= "";
	}
	
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}

	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}

	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}

	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}

	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}

	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}

	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}

//	public String getIdSetorComercialInicial() {
//		return idSetorComercialInicial;
//	}
//
//	public void setIdSetorComercialInicial(String idSetorComercialInicial) {
//		this.idSetorComercialInicial = idSetorComercialInicial;
//	}
//
//	public String getIdSetorComercialFinal() {
//		return idSetorComercialFinal;
//	}
//
//	public void setIdSetorComercialFinal(String idSetorComercialFinal) {
//		this.idSetorComercialFinal = idSetorComercialFinal;
//	}

	public String getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	public void setCodigoSetorComercialInicial(String codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}

	public String getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	public void setCodigoSetorComercialFinal(String codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	public String getNomeSetorComercialInicial() {
		return nomeSetorComercialInicial;
	}

	public void setNomeSetorComercialInicial(String nomeSetorComercialInicial) {
		this.nomeSetorComercialInicial = nomeSetorComercialInicial;
	}

	public String getNomeSetorComercialFinal() {
		return nomeSetorComercialFinal;
	}

	public void setNomeSetorComercialFinal(String nomeSetorComercialFinal) {
		this.nomeSetorComercialFinal = nomeSetorComercialFinal;
	}

	public String getCodigoRotaInicial() {
		return codigoRotaInicial;
	}

	public void setCodigoRotaInicial(String codigoRotaInicial) {
		this.codigoRotaInicial = codigoRotaInicial;
	}

	public String getCodigoRotaFinal() {
		return codigoRotaFinal;
	}

	public void setCodigoRotaFinal(String codigoRotaFinal) {
		this.codigoRotaFinal = codigoRotaFinal;
	}

	public String getMesAnoReferenciaInicial() {
		return mesAnoReferenciaInicial;
	}

	public void setMesAnoReferenciaInicial(String referenciaInicial) {
		this.mesAnoReferenciaInicial = referenciaInicial;
	}

	public String getMesAnoReferenciaFinal() {
		return mesAnoReferenciaFinal;
	}

	public void setMesAnoReferenciaFinal(String referenciaFinal) {
		this.mesAnoReferenciaFinal = referenciaFinal;
	}

	public String getIdAnormalidadeLeitura() {
		return idAnormalidadeLeitura;
	}

	public void setIdAnormalidadeLeitura(String idAnormalidadeLeitura) {
		this.idAnormalidadeLeitura = idAnormalidadeLeitura;
	}

	public String getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	public void setIdGrupoFaturamento(String idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	public String getSequencialRotaInicial() {
		return sequencialRotaInicial;
	}

	public void setSequencialRotaInicial(String sequencialRotaInicial) {
		this.sequencialRotaInicial = sequencialRotaInicial;
	}

	public String getSequencialRotaFinal() {
		return sequencialRotaFinal;
	}

	public void setSequencialRotaFinal(String sequencialRotaFinal) {
		this.sequencialRotaFinal = sequencialRotaFinal;
	}
}