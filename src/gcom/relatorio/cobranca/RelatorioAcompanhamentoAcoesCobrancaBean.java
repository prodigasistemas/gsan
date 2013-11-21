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
* Genival Soares Barbosa Filho
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
package gcom.relatorio.cobranca;


import gcom.relatorio.RelatorioBean;

/**
 * [UCXXXX] - Gerar Relatório Analisar Metas Ciclo
 * 
 * @author Genival Barbosa
 * @date 21/07/2009
 */
public class RelatorioAcompanhamentoAcoesCobrancaBean implements RelatorioBean {	

	private String idCobrancaAcao;
	
	private String nomeCobrancaAcao;
	
	private String dataInicial;
	
	private String dataFinal;
	
	private String chkEstado;
	
	private String chkGerencia;
	
	private String chkUnidade;
	
	private String chkLocalidade;
	
	private String idDocumento;
	
	private String idGerenciaRegional;
	
	private String nomeGerencia;
	
	private String idUnidadeNegocio;
	
	private String nomeUnidade;
	
	private String idLocalidade;
	
	private String nomeLocalidade;
	
	private String idCobrancaAcaoSituacao;
	
	private String nomeCobrancaAcaoSituacao;
	
	private String quantidadeSituacao;
	
	private String valorSituacao;
		
	private String idEmpresa;
	
	private String nomeEmpresa;
	
	
//  ------------- Situação 1
	private String idCobrancaAcaoSituacao1;
	
	private String nomeCobrancaAcaoSituacao1;
	
	private String quantidadeSituacao1;
	
	private String valorSituacao1;
	
//	 ------------- Situação 2
	private String idCobrancaAcaoSituacao2;
	
	private String nomeCobrancaAcaoSituacao2;
	
	private String quantidadeSituacao2;
	
	private String valorSituacao2;
	
//	 ------------- Situação 3
	private String idCobrancaAcaoSituacao3;
	
	private String nomeCobrancaAcaoSituacao3;
	
	private String quantidadeSituacao3;
	
	private String valorSituacao3;
	
//	 ------------- Situação 4
	private String idCobrancaAcaoSituacao4;
	
	private String nomeCobrancaAcaoSituacao4;
	
	private String quantidadeSituacao4;
	
	private String valorSituacao4;
	
//	 ------------- Situação 5
	private String idCobrancaAcaoSituacao5;
	
	private String nomeCobrancaAcaoSituacao5;
	
	private String quantidadeSituacao5;
	
	private String valorSituacao5;
	
//	 ------------- Situação 6
	private String idCobrancaAcaoSituacao6;
	
	private String nomeCobrancaAcaoSituacao6;
	
	private String quantidadeSituacao6;
	
	private String valorSituacao6;
	
	
	
	//---------------------
	private Integer tipoRelatorio;
		

	public RelatorioAcompanhamentoAcoesCobrancaBean(){}
	
	public RelatorioAcompanhamentoAcoesCobrancaBean(String idCobrancaAcao,String nomeCobrancaAcao, 
			String dataInicial, String dataFinal, String chkEstado, String chkGerencia, 
			String chkUnidade, String chkLocalidade, String idDocumento, 
			String idGerenciaRegional, String nomeGerencia, 
			String idUnidadeNegocio, String nomeUnidade, 
			String idLocalidade, String nomeLocalidade, 
			String idCobrancaAcaoSituacao, String nomeCobrancaAcaoSituacao, 
			String quantidadeSituacao, String valorSituacao, String idEmpresa, 
			String nomeEmpresa, Integer tipoRelatorio) {
		super();

		this.idCobrancaAcao = idCobrancaAcao;
		this.nomeCobrancaAcao = nomeCobrancaAcao;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.chkEstado = chkEstado;
		this.chkGerencia = chkGerencia;
		this.chkUnidade = chkUnidade;
		this.chkLocalidade = chkLocalidade;
		this.idDocumento = idDocumento;
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerencia = nomeGerencia;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.nomeUnidade = nomeUnidade;
		this.idLocalidade = idLocalidade;
		this.nomeLocalidade = nomeLocalidade;
		this.idCobrancaAcaoSituacao = idCobrancaAcaoSituacao;
		this.nomeCobrancaAcaoSituacao = nomeCobrancaAcaoSituacao;
		this.quantidadeSituacao = quantidadeSituacao;
		this.valorSituacao = valorSituacao;
		this.idEmpresa = idEmpresa;
		this.nomeEmpresa = nomeEmpresa;
		this.tipoRelatorio = tipoRelatorio; 
		this.quantidadeSituacao1 = "0";
		this.quantidadeSituacao2 = "0";
		this.quantidadeSituacao3 = "0";
		this.quantidadeSituacao4 = "0";
		this.quantidadeSituacao5 = "0";
		this.quantidadeSituacao6 = "0";
		this.valorSituacao1 = "0";
		this.valorSituacao2 = "0";
		this.valorSituacao3 = "0";
		this.valorSituacao4 = "0";
		this.valorSituacao5 = "0";
		this.valorSituacao6 = "0";
		
	}

	

	public String getChkEstado() {
		return chkEstado;
	}


	public void setChkEstado(String chkEstado) {
		this.chkEstado = chkEstado;
	}


	public String getChkGerencia() {
		return chkGerencia;
	}


	public void setChkGerencia(String chkGerencia) {
		this.chkGerencia = chkGerencia;
	}


	public String getChkLocalidade() {
		return chkLocalidade;
	}


	public void setChkLocalidade(String chkLocalidade) {
		this.chkLocalidade = chkLocalidade;
	}


	public String getChkUnidade() {
		return chkUnidade;
	}


	public void setChkUnidade(String chkUnidade) {
		this.chkUnidade = chkUnidade;
	}


	public String getDataFinal() {
		return dataFinal;
	}


	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}


	public String getDataInicial() {
		return dataInicial;
	}


	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}


	public String getIdCobrancaAcao() {
		return idCobrancaAcao;
	}


	public void setIdCobrancaAcao(String idCobrancaAcao) {
		this.idCobrancaAcao = idCobrancaAcao;
	}


	public String getIdEmpresa() {
		return idEmpresa;
	}


	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}


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


	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}


	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}


	public String getIdCobrancaAcaoSituacao() {
		return idCobrancaAcaoSituacao;
	}


	public void setIdCobrancaAcaoSituacao(String idCobrancaAcaoSituacao) {
		this.idCobrancaAcaoSituacao = idCobrancaAcaoSituacao;
	}


	public String getIdDocumento() {
		return idDocumento;
	}


	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}


	public String getNomeCobrancaAcaoSituacao() {
		return nomeCobrancaAcaoSituacao;
	}


	public void setNomeCobrancaAcaoSituacao(String nomeCobrancaAcaoSituacao) {
		this.nomeCobrancaAcaoSituacao = nomeCobrancaAcaoSituacao;
	}


	public String getNomeEmpresa() {
		return nomeEmpresa;
	}


	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}


	public String getNomeGerencia() {
		return nomeGerencia;
	}


	public void setNomeGerencia(String nomeGerencia) {
		this.nomeGerencia = nomeGerencia;
	}


	public String getNomeLocalidade() {
		return nomeLocalidade;
	}


	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}


	public String getNomeUnidade() {
		return nomeUnidade;
	}


	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}


	public String getQuantidadeSituacao() {
		return quantidadeSituacao;
	}


	public void setQuantidadeSituacao(String quantidadeSituacao) {
		this.quantidadeSituacao = quantidadeSituacao;
	}


	public String getValorSituacao() {
		return valorSituacao;
	}


	public void setValorSituacao(String valorSituacao) {
		this.valorSituacao = valorSituacao;
	}


	public String getNomeCobrancaAcao() {
		return nomeCobrancaAcao;
	}


	public void setNomeCobrancaAcao(String nomeCobrancaAcao) {
		this.nomeCobrancaAcao = nomeCobrancaAcao;
	}



	public String getIdCobrancaAcaoSituacao1() {
		return idCobrancaAcaoSituacao1;
	}



	public void setIdCobrancaAcaoSituacao1(String idCobrancaAcaoSituacao1) {
		this.idCobrancaAcaoSituacao1 = idCobrancaAcaoSituacao1;
	}



	public String getIdCobrancaAcaoSituacao2() {
		return idCobrancaAcaoSituacao2;
	}



	public void setIdCobrancaAcaoSituacao2(String idCobrancaAcaoSituacao2) {
		this.idCobrancaAcaoSituacao2 = idCobrancaAcaoSituacao2;
	}



	public String getIdCobrancaAcaoSituacao3() {
		return idCobrancaAcaoSituacao3;
	}



	public void setIdCobrancaAcaoSituacao3(String idCobrancaAcaoSituacao3) {
		this.idCobrancaAcaoSituacao3 = idCobrancaAcaoSituacao3;
	}



	public String getIdCobrancaAcaoSituacao4() {
		return idCobrancaAcaoSituacao4;
	}



	public void setIdCobrancaAcaoSituacao4(String idCobrancaAcaoSituacao4) {
		this.idCobrancaAcaoSituacao4 = idCobrancaAcaoSituacao4;
	}



	public String getIdCobrancaAcaoSituacao5() {
		return idCobrancaAcaoSituacao5;
	}



	public void setIdCobrancaAcaoSituacao5(String idCobrancaAcaoSituacao5) {
		this.idCobrancaAcaoSituacao5 = idCobrancaAcaoSituacao5;
	}



	public String getIdCobrancaAcaoSituacao6() {
		return idCobrancaAcaoSituacao6;
	}



	public void setIdCobrancaAcaoSituacao6(String idCobrancaAcaoSituacao6) {
		this.idCobrancaAcaoSituacao6 = idCobrancaAcaoSituacao6;
	}



	public String getNomeCobrancaAcaoSituacao1() {
		return nomeCobrancaAcaoSituacao1;
	}



	public void setNomeCobrancaAcaoSituacao1(String nomeCobrancaAcaoSituacao1) {
		this.nomeCobrancaAcaoSituacao1 = nomeCobrancaAcaoSituacao1;
	}



	public String getNomeCobrancaAcaoSituacao2() {
		return nomeCobrancaAcaoSituacao2;
	}



	public void setNomeCobrancaAcaoSituacao2(String nomeCobrancaAcaoSituacao2) {
		this.nomeCobrancaAcaoSituacao2 = nomeCobrancaAcaoSituacao2;
	}



	public String getNomeCobrancaAcaoSituacao3() {
		return nomeCobrancaAcaoSituacao3;
	}



	public void setNomeCobrancaAcaoSituacao3(String nomeCobrancaAcaoSituacao3) {
		this.nomeCobrancaAcaoSituacao3 = nomeCobrancaAcaoSituacao3;
	}



	public String getNomeCobrancaAcaoSituacao4() {
		return nomeCobrancaAcaoSituacao4;
	}



	public void setNomeCobrancaAcaoSituacao4(String nomeCobrancaAcaoSituacao4) {
		this.nomeCobrancaAcaoSituacao4 = nomeCobrancaAcaoSituacao4;
	}



	public String getNomeCobrancaAcaoSituacao5() {
		return nomeCobrancaAcaoSituacao5;
	}



	public void setNomeCobrancaAcaoSituacao5(String nomeCobrancaAcaoSituacao5) {
		this.nomeCobrancaAcaoSituacao5 = nomeCobrancaAcaoSituacao5;
	}



	public String getNomeCobrancaAcaoSituacao6() {
		return nomeCobrancaAcaoSituacao6;
	}



	public void setNomeCobrancaAcaoSituacao6(String nomeCobrancaAcaoSituacao6) {
		this.nomeCobrancaAcaoSituacao6 = nomeCobrancaAcaoSituacao6;
	}



	public String getQuantidadeSituacao1() {
		return quantidadeSituacao1;
	}



	public void setQuantidadeSituacao1(String quantidadeSituacao1) {
		this.quantidadeSituacao1 = quantidadeSituacao1;
	}



	public String getQuantidadeSituacao2() {
		return quantidadeSituacao2;
	}



	public void setQuantidadeSituacao2(String quantidadeSituacao2) {
		this.quantidadeSituacao2 = quantidadeSituacao2;
	}



	public String getQuantidadeSituacao3() {
		return quantidadeSituacao3;
	}



	public void setQuantidadeSituacao3(String quantidadeSituacao3) {
		this.quantidadeSituacao3 = quantidadeSituacao3;
	}



	public String getQuantidadeSituacao4() {
		return quantidadeSituacao4;
	}



	public void setQuantidadeSituacao4(String quantidadeSituacao4) {
		this.quantidadeSituacao4 = quantidadeSituacao4;
	}



	public String getQuantidadeSituacao5() {
		return quantidadeSituacao5;
	}



	public void setQuantidadeSituacao5(String quantidadeSituacao5) {
		this.quantidadeSituacao5 = quantidadeSituacao5;
	}



	public String getQuantidadeSituacao6() {
		return quantidadeSituacao6;
	}



	public void setQuantidadeSituacao6(String quantidadeSituacao6) {
		this.quantidadeSituacao6 = quantidadeSituacao6;
	}



	public String getValorSituacao1() {
		return valorSituacao1;
	}



	public void setValorSituacao1(String valorSituacao1) {
		this.valorSituacao1 = valorSituacao1;
	}



	public String getValorSituacao2() {
		return valorSituacao2;
	}



	public void setValorSituacao2(String valorSituacao2) {
		this.valorSituacao2 = valorSituacao2;
	}



	public String getValorSituacao3() {
		return valorSituacao3;
	}



	public void setValorSituacao3(String valorSituacao3) {
		this.valorSituacao3 = valorSituacao3;
	}



	public String getValorSituacao4() {
		return valorSituacao4;
	}



	public void setValorSituacao4(String valorSituacao4) {
		this.valorSituacao4 = valorSituacao4;
	}



	public String getValorSituacao5() {
		return valorSituacao5;
	}



	public void setValorSituacao5(String valorSituacao5) {
		this.valorSituacao5 = valorSituacao5;
	}



	public String getValorSituacao6() {
		return valorSituacao6;
	}



	public void setValorSituacao6(String valorSituacao6) {
		this.valorSituacao6 = valorSituacao6;
	}

	public Integer getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(Integer tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}
	
}
