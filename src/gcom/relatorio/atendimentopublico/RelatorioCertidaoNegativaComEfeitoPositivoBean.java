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
package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

/**
 * classe responsável por criar a certidao negativa com efeito positivo
 * 
 * @author Bruno Barros
 * @created 29/01/2008
 */
public class RelatorioCertidaoNegativaComEfeitoPositivoBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String gerenciaRegional;
	private String localidade;
	private String unidadeNegocio;
	private String setorComercial;
	private String nomeClienteUsuario;
	private String matriculaImovel;
	private String endereco;
	private String bairro;
	private String CEP;
	private String municipio;
	private String inscricaoImovel;
	private String categoria;
	private String subcategoria;
	private String economias;
	private String ligacaoAguaSituacao;
	private String ligacaoEsgotoSituacao;
	private String situacaoPoco;
	private String descricaoEmpresa;
	private String enderecoEmpresa;
	private String cepEmpresa;
	private String CNPJEmpresa;
	private String inscricaoEstadualEmpresa;
	private String siteEmpresa;
	private String zeroOitossentosEmpresa;
	private String perfilImovel;
	
	public static long getSerialVersionUID() {
	
		return serialVersionUID;
	}
	
	public String getBairro() {
	
		return bairro;
	}
	
	public String getCategoria() {
	
		return categoria;
	}
	
	public String getCEP() {
	
		return CEP;
	}
	
	public String getCepEmpresa() {
	
		return cepEmpresa;
	}
	
	public String getCNPJEmpresa() {
	
		return CNPJEmpresa;
	}
	
	public String getDescricaoEmpresa() {
	
		return descricaoEmpresa;
	}
	
	public String getEconomias() {
	
		return economias;
	}
	
	public String getEndereco() {
	
		return endereco;
	}
	
	public String getEnderecoEmpresa() {
	
		return enderecoEmpresa;
	}
	
	public String getGerenciaRegional() {
	
		return gerenciaRegional;
	}
	
	public String getInscricaoEstadualEmpresa() {
	
		return inscricaoEstadualEmpresa;
	}
	
	public String getInscricaoImovel() {
	
		return inscricaoImovel;
	}
	
	public String getLigacaoAguaSituacao() {
	
		return ligacaoAguaSituacao;
	}
	
	public String getLigacaoEsgotoSituacao() {
	
		return ligacaoEsgotoSituacao;
	}
	
	public String getLocalidade() {
	
		return localidade;
	}
	
	public String getMatriculaImovel() {
	
		return matriculaImovel;
	}
	
	public String getMunicipio() {
	
		return municipio;
	}
	
	public String getNomeClienteUsuario() {
	
		return nomeClienteUsuario;
	}
	
	public String getPerfilImovel() {
	
		return perfilImovel;
	}
	
	public String getSetorComercial() {
	
		return setorComercial;
	}
	
	public String getSiteEmpresa() {
	
		return siteEmpresa;
	}
	
	public String getSituacaoPoco() {
	
		return situacaoPoco;
	}
	
	public String getSubcategoria() {
	
		return subcategoria;
	}
	
	public String getUnidadeNegocio() {
	
		return unidadeNegocio;
	}
	
	public String getZeroOitossentosEmpresa() {
	
		return zeroOitossentosEmpresa;
	}
}
