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
import gcom.util.Util;

import java.util.ArrayList;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * classe responsável por criar a certidao negativa
 * 
 * @author Bruno Barros
 * @created 29/01/2008
 */
public class RelatorioCertidaoNegativaBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String localidade;
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
	private String descricaoAbriviadaEmpresa;
	private String descricaoEmpresa;
	private String enderecoEmpresa;
	private String cepEmpresa;
	private String CNPJEmpresa;
	private String inscricaoEstadualEmpresa;
	private String siteEmpresa;
	private String zeroOitossentosEmpresa;
	private String perfilImovel;
	private String area;
	private String numeroHidrometro;
	private String nomeRelatorio;
	private String nomeUsuario;
	private Boolean imovelComParcelamento;
	
	private String cpfCnpj;
	private String unidadeNegocio;
	private String enderecoCompleto;
	
	private JRBeanCollectionDataSource arrayJrItens;
	private ArrayList arrayRelatorioCertidaoNegativaBean;	
	
	public RelatorioCertidaoNegativaBean( RelatorioCertidaoNegativaHelper helper ){
		this.localidade = ( helper.getLocalidade() == null ? "-" : helper.getLocalidade() );
		
		// Verificamos se devemos informar se o usuário está em dia ou não
		if ( helper.getItens() == null || helper.getItens().size() == 0 ){		
			this.nomeClienteUsuario = 
	            "			Certifico, a vista de documentos comprobatórios e pesquisa de registros " +
	            "na nossa base de dados, que o imóvel abaixo descrito,  atualmente sob " +
	            "responsabilidade do Sr. " +
	            helper.getNomeClienteUsuario() +" , se encontra em situação regular, sem " +
	            "débitos, acordos ou serviços a faturar, nesta data.";
			this.nomeRelatorio = "Certidão Negativa de Débito";
		} else {
			this.nomeClienteUsuario = 
	            "			Certifico, a vista de documentos comprobatórios e pesquisa de registros " +
	            "na nossa base de dados, que o imóvel abaixo descrito,  atualmente sob " +
	            "responsabilidade do Sr. " +
	            helper.getNomeClienteUsuario() +" , se encontra na seguinte situação, nesta data.";
			this.nomeRelatorio = "Certidão Negativa de Débito - Com Efeito Positivo";			
		}		
		
		this.matriculaImovel = Util.retornaMatriculaImovelFormatada( helper.getMatriculaImovel() );
		this.endereco = ( helper.getEndereco() == null ? "-" : helper.getEndereco() );
		this.bairro = ( helper.getBairro() == null ? "-" : helper.getBairro() );
		this.CEP = ( helper.getCEP() == null ? "-" : helper.getCEP() );
		this.municipio = ( helper.getMunicipio() == null ? "-" : helper.getMunicipio() );
		this.inscricaoImovel = ( helper.getInscricaoImovel() == null ? "-" : helper.getInscricaoImovel() );
		this.categoria = ( helper.getCategoria() == null ? "-" : helper.getCategoria() );
		this.subcategoria = ( helper.getSubcategoria() == null ? "-" : helper.getSubcategoria() );
		this.economias = ( helper.getEconomias() == null ? "0" : Util.agruparNumeroEmMilhares( helper.getEconomias().intValue() ) );
		this.ligacaoAguaSituacao = ( helper.getLigacaoAguaSituacao() == null ? "-" : helper.getLigacaoAguaSituacao() );
		this.ligacaoEsgotoSituacao  = ( helper.getLigacaoEsgotoSituacao() == null ? "-" : helper.getLigacaoEsgotoSituacao() );
		this.situacaoPoco = ( helper.getSituacaoPoco() == null ? "-" : helper.getSituacaoPoco() );
		this.descricaoAbriviadaEmpresa = ( helper.getDescricaoAbreviadaEmpresa() == null ? "-" : helper.getDescricaoAbreviadaEmpresa() );
		this.descricaoEmpresa = ( helper.getDescricaoEmpresa() == null ? "-" : helper.getDescricaoEmpresa() );
		this.enderecoEmpresa = ( helper.getEnderecoEmpresa() == null ? "-" : helper.getEnderecoEmpresa() );
		this.cepEmpresa = ( helper.getCepEmpresa() == null ? "-" : helper.getCepEmpresa() );
		this.CNPJEmpresa = ( helper.getCNPJEmpresa() == null ? "-" : Util.formatarCnpj( helper.getCNPJEmpresa() ) );
		this.inscricaoEstadualEmpresa = ( helper.getInscricaoEstadualEmpresa() == null ? "-" : helper.getInscricaoEstadualEmpresa() );
		this.siteEmpresa = ( helper.getSiteEmpresa() == null ? "-" : helper.getSiteEmpresa() );
		this.zeroOitossentosEmpresa = ( helper.getZeroOitossentosEmpresa() == null ? "-" : helper.getZeroOitossentosEmpresa() );
		this.perfilImovel = ( helper.getPerfilImovel() == null ? "-" : helper.getPerfilImovel() );		
		this.area = helper.getArea();
		this.numeroHidrometro = helper.getNumeroHidrometro();
		this.nomeUsuario = helper.getNomeUsuario();
		this.imovelComParcelamento = helper.getImovelComParcelamento();
		 
		if (helper.getItens() != null && !helper.getItens().isEmpty()) {
			this.arrayRelatorioCertidaoNegativaBean = new ArrayList();
			this.arrayRelatorioCertidaoNegativaBean
					.addAll(helper.getItens());
			this.arrayJrItens = new JRBeanCollectionDataSource(
					this.arrayRelatorioCertidaoNegativaBean);

		} else {
			this.arrayJrItens = null;
		}
		
		this.unidadeNegocio = ( helper.getUnidadeNegocio() == null ? "-" : helper.getUnidadeNegocio() );
		this.cpfCnpj = ( helper.getCpfCnpj() == null ? "-" : helper.getCpfCnpj() );
		this.enderecoCompleto = ( helper.getEnderecoCompleto() == null ? "-" : helper.getEnderecoCompleto() );
	}
	
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
	
	public String getSiteEmpresa() {
	
		return siteEmpresa;
	}
	
	public String getSituacaoPoco() {
	
		return situacaoPoco;
	}
	
	public String getSubcategoria() {
	
		return subcategoria;
	}
	
	public String getZeroOitossentosEmpresa() {
	
		return zeroOitossentosEmpresa;
	}

	
	public String getDescricaoAbriviadaEmpresa() {
	
		return descricaoAbriviadaEmpresa;
	}

	
	public JRBeanCollectionDataSource getArrayJrItens() {
	
		return arrayJrItens;
	}

	
	public ArrayList getArrayRelatorioCertidaoNegativaBean() {
	
		return arrayRelatorioCertidaoNegativaBean;
	}

	
	public String getNomeRelatorio() {
	
		return nomeRelatorio;
	}

	/**
	 * @return Retorna o campo area.
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @return Retorna o campo numeroHidrometro.
	 */
	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	/**
	 * @return Retorna o campo nomeUsuario.
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public Boolean getImovelComParcelamento() {
		return imovelComParcelamento;
	}

	public void setImovelComParcelamento(Boolean imovelComParcelamento) {
		this.imovelComParcelamento = imovelComParcelamento;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public String getEnderecoCompleto() {
		return enderecoCompleto;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

}
