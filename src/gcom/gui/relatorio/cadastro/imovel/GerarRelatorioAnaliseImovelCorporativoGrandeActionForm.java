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

package gcom.gui.relatorio.cadastro.imovel;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * 
 * Este caso de uso gera relatório de análise do imóvel corporativo ou grande
 * 
 * @author Ana Maria
 * @date 06/01/09
 * 
 */


public class GerarRelatorioAnaliseImovelCorporativoGrandeActionForm extends ValidatorActionForm {
	
	
	
	private static final long serialVersionUID = 1L;
	
	private String referencia;
	private String unidadeNegocio;
	private String regional;
	private String idLocalidadeInicial;
	private String nomeLocalidadeInicial;
	private String idLocalidadeFinal;
	private String nomeLocalidadeFinal;
	private String codigoSetorComercialInicial;
	private String nomeSetorComercialInicial;
	private String codigoSetorComercialFinal;
	private String nomeSetorComercialFinal;
	private String idImovelPerfil;
	private String selecionar;
	

	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}
	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}
	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}
	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}
	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}
	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}
	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}
	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}
	public String getRegional() {
		return regional;
	}
	public void setRegional(String regional) {
		this.regional = regional;
	}
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}
	public void setCodigoSetorComercialFinal(String codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}
	public String getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}
	public void setCodigoSetorComercialInicial(String codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}
	public String getNomeSetorComercialFinal() {
		return nomeSetorComercialFinal;
	}
	public void setNomeSetorComercialFinal(String nomeSetorComercialFinal) {
		this.nomeSetorComercialFinal = nomeSetorComercialFinal;
	}
	public String getNomeSetorComercialInicial() {
		return nomeSetorComercialInicial;
	}
	public void setNomeSetorComercialInicial(String nomeSetorComercialInicial) {
		this.nomeSetorComercialInicial = nomeSetorComercialInicial;
	}
	/**
	 * @return Retorna o campo referencia.
	 */
	public String getReferencia() {
		return referencia;
	}
	/**
	 * @param referencia O referencia a ser setado.
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	/**
	 * @return Retorna o campo idImovelPerfil.
	 */
	public String getIdImovelPerfil() {
		return idImovelPerfil;
	}
	/**
	 * @param idImovelPerfil O idImovelPerfil a ser setado.
	 */
	public void setIdImovelPerfil(String idImovelPerfil) {
		this.idImovelPerfil = idImovelPerfil;
	}
	/**
	 * @return Retorna o campo selecionar.
	 */
	public String getSelecionar() {
		return selecionar;
	}
	/**
	 * @param selecionar O selecionar a ser setado.
	 */
	public void setSelecionar(String selecionar) {
		this.selecionar = selecionar;
	}
	

}
