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
package gcom.gui.cadastro.imovel;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 2 de Junho de 2004
 */
public class ImovelLocalizacaoFiltrarActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idNomeConta;
	
	private String idImovelPrincipal;
	
	private String idImovelCondominio;
	
	private String tipoRelacao;
	
	private String idCliente;
	
	private String loteDestino;
	
	private String quadraDestinoNM;
	
	private String idBairro;
	
	private String loteOrigem;
	
	private String idLocalidade;

	private String nomeLocalidadeOrigem;

	private String nomeSetorComercialOrigem;

	private String quadraOrigemNM;

	private String quadraMensagemOrigem;

	private String nomeLocalidadeDestino;

	private String setorComercialDestinoCD;

	private String setorComercialOrigemCD;

	private String setorComercialOrigemID;

	private String quadraOrigemID;

	private String localidadeDestinoID;

	private String localidadeOrigemID;	

	private String nomeSetorComercialDestino;

	private String setorComercialDestinoID;

	private String quadraMensagemDestino;

	private String idGerenciaRegional;
	
	private String quadraDestinoID;
	
	private String idMunicipio;

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getLocalidadeDestinoID() {
		return localidadeDestinoID;
	}

	public void setLocalidadeDestinoID(String localidadeDestinoID) {
		this.localidadeDestinoID = localidadeDestinoID;
	}

	public String getNomeLocalidadeDestino() {
		return nomeLocalidadeDestino;
	}

	public void setNomeLocalidadeDestino(String nomeLocalidadeDestino) {
		this.nomeLocalidadeDestino = nomeLocalidadeDestino;
	}

	public String getNomeLocalidadeOrigem() {
		return nomeLocalidadeOrigem;
	}

	public void setNomeLocalidadeOrigem(String nomeLocalidadeOrigem) {
		this.nomeLocalidadeOrigem = nomeLocalidadeOrigem;
	}

	public String getNomeSetorComercialDestino() {
		return nomeSetorComercialDestino;
	}

	public void setNomeSetorComercialDestino(String nomeSetorComercialDestino) {
		this.nomeSetorComercialDestino = nomeSetorComercialDestino;
	}

	public String getNomeSetorComercialOrigem() {
		return nomeSetorComercialOrigem;
	}

	public void setNomeSetorComercialOrigem(String nomeSetorComercialOrigem) {
		this.nomeSetorComercialOrigem = nomeSetorComercialOrigem;
	}

	public String getQuadraMensagemDestino() {
		return quadraMensagemDestino;
	}

	public void setQuadraMensagemDestino(String quadraMensagemDestino) {
		this.quadraMensagemDestino = quadraMensagemDestino;
	}

	public String getQuadraMensagemOrigem() {
		return quadraMensagemOrigem;
	}

	public void setQuadraMensagemOrigem(String quadraMensagemOrigem) {
		this.quadraMensagemOrigem = quadraMensagemOrigem;
	}

	public String getQuadraOrigemID() {
		return quadraOrigemID;
	}

	public void setQuadraOrigemID(String quadraOrigemID) {
		this.quadraOrigemID = quadraOrigemID;
	}

	public String getQuadraOrigemNM() {
		return quadraOrigemNM;
	}

	public void setQuadraOrigemNM(String quadraOrigemNM) {
		this.quadraOrigemNM = quadraOrigemNM;
	}

	public String getSetorComercialDestinoCD() {
		return setorComercialDestinoCD;
	}

	public void setSetorComercialDestinoCD(String setorComercialDestinoCD) {
		this.setorComercialDestinoCD = setorComercialDestinoCD;
	}

	public String getSetorComercialDestinoID() {
		return setorComercialDestinoID;
	}

	public void setSetorComercialDestinoID(String setorComercialDestinoID) {
		this.setorComercialDestinoID = setorComercialDestinoID;
	}

	public String getSetorComercialOrigemCD() {
		return setorComercialOrigemCD;
	}

	public void setSetorComercialOrigemCD(String setorComercialOrigemCD) {
		this.setorComercialOrigemCD = setorComercialOrigemCD;
	}

	public String getSetorComercialOrigemID() {
		return setorComercialOrigemID;
	}

	public void setSetorComercialOrigemID(String setorComercialOrigemID) {
		this.setorComercialOrigemID = setorComercialOrigemID;
	}

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(String idBairro) {
		this.idBairro = idBairro;
	}

	public String getLocalidadeOrigemID() {
		return localidadeOrigemID;
	}

	public void setLocalidadeOrigemID(String localidadeOrigemID) {
		this.localidadeOrigemID = localidadeOrigemID;
	}

	public String getLoteOrigem() {
		return loteOrigem;
	}

	public void setLoteOrigem(String loteOrigem) {
		this.loteOrigem = loteOrigem;
	}

	public String getQuadraDestinoNM() {
		return quadraDestinoNM;
	}

	public void setQuadraDestinoNM(String quadraDestinoNM) {
		this.quadraDestinoNM = quadraDestinoNM;
	}

	public String getQuadraDestinoID() {
		return quadraDestinoID;
	}

	public void setQuadraDestinoID(String quadraDestinoID) {
		this.quadraDestinoID = quadraDestinoID;
	}

	public String getLoteDestino() {
		return loteDestino;
	}

	public void setLoteDestino(String loteDestino) {
		this.loteDestino = loteDestino;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getTipoRelacao() {
		return tipoRelacao;
	}

	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}

	public String getIdImovelCondominio() {
		return idImovelCondominio;
	}

	public void setIdImovelCondominio(String idImovelCondominio) {
		this.idImovelCondominio = idImovelCondominio;
	}

	public String getIdImovelPrincipal() {
		return idImovelPrincipal;
	}

	public void setIdImovelPrincipal(String idImovelPrincipal) {
		this.idImovelPrincipal = idImovelPrincipal;
	}

	public String getIdNomeConta() {
		return idNomeConta;
	}

	public void setIdNomeConta(String idNomeConta) {
		this.idNomeConta = idNomeConta;
	}

}
