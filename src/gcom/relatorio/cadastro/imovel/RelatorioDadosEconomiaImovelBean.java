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
package gcom.relatorio.cadastro.imovel;

import gcom.relatorio.RelatorioBean;

import java.util.Date;

public class RelatorioDadosEconomiaImovelBean implements RelatorioBean {
	
	private String idGerenciaRegional;
	private String nomeGerenciaRegional;
	private String idLocalidade;
	private String nomeLocalidade;
	private String idSetorComercial;
	private String nomeSetorComercial;
	private String matricula;
	private String endereco;
	private String subcategoria;
	private String categoria;
	private String qtdeEconomias;
	private String nomeClienteUsuario;
	private String complementoEndereco;
	private String numeroPontos;
	private String numeroMoradores;
	private String numeroIptu;
	private String numeroCelpe;
	private String areaConstruida;
	private String nomeCliente;
	private String tipoRelacao;
	private String cpf;
	private Date dataInicioRelacao;
	private Date dataFimRelacao;
	private String motivoFimRelacao;
	private String inscricao;
	private String nomeUsuario;
	
	public RelatorioDadosEconomiaImovelBean (String idGerenciaRegional, String nomeGerenciaRegional, String idLocalidade,
			String nomeLocalidade, String idSetorComercial, String nomeSetorComercial, String matricula, String inscricao, String endereco,
			String subcategoria, String categoria, String qtdeEconomias, String nomeClienteUsuario, String complementoEndereco,
			String numeroPontos, String numeroMoradores, String numeroIptu, String numeroCelpe, String areaConstruida,
			String nomeCliente, String tipoRelacao, String cpf, Date dataInicioRelacao, Date dataFimRelacao, String motivoFimRelacao) {
		
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idLocalidade = idLocalidade;
		this.nomeLocalidade = nomeLocalidade;
		this.idSetorComercial = idSetorComercial;
		this.nomeSetorComercial = nomeSetorComercial;
		this.matricula = matricula;
		this.inscricao = inscricao;
		this.endereco = endereco;
		this.subcategoria = subcategoria;
		this.categoria = categoria;
		this.qtdeEconomias = qtdeEconomias;
		this.nomeClienteUsuario = nomeClienteUsuario;
		this.complementoEndereco = complementoEndereco;
		this.numeroPontos = numeroPontos;
		this.numeroMoradores = numeroMoradores;
		this.numeroIptu = numeroIptu;
		this.numeroCelpe = numeroCelpe;
		this.areaConstruida = areaConstruida;
		this.nomeCliente = nomeCliente;
		this.tipoRelacao = tipoRelacao;
		this.cpf = cpf;
		this.dataInicioRelacao = dataInicioRelacao;
		this.dataFimRelacao = dataFimRelacao;
		this.motivoFimRelacao = motivoFimRelacao;
		
	}
	
	
	public String getAreaConstruida() {
		return areaConstruida;
	}
	public void setAreaConstruida(String areaConstruida) {
		this.areaConstruida = areaConstruida;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getComplementoEndereco() {
		return complementoEndereco;
	}
	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDataFimRelacao() {
		return dataFimRelacao;
	}
	public void setDataFimRelacao(Date dataFimRelacao) {
		this.dataFimRelacao = dataFimRelacao;
	}
	public Date getDataInicioRelacao() {
		return dataInicioRelacao;
	}
	public void setDataInicioRelacao(Date dataInicioRelacao) {
		this.dataInicioRelacao = dataInicioRelacao;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getMotivoFimRelacao() {
		return motivoFimRelacao;
	}
	public void setMotivoFimRelacao(String motivoFimRelacao) {
		this.motivoFimRelacao = motivoFimRelacao;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}
	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}
	public String getNumeroCelpe() {
		return numeroCelpe;
	}
	public void setNumeroCelpe(String numeroCelpe) {
		this.numeroCelpe = numeroCelpe;
	}
	public String getNumeroIptu() {
		return numeroIptu;
	}
	public void setNumeroIptu(String numeroIptu) {
		this.numeroIptu = numeroIptu;
	}
	public String getNumeroMoradores() {
		return numeroMoradores;
	}
	public void setNumeroMoradores(String numeroMoradores) {
		this.numeroMoradores = numeroMoradores;
	}
	public String getNumeroPontos() {
		return numeroPontos;
	}
	public void setNumeroPontos(String numeroPontos) {
		this.numeroPontos = numeroPontos;
	}
	public String getQtdeEconomias() {
		return qtdeEconomias;
	}
	public void setQtdeEconomias(String qtdeEconomias) {
		this.qtdeEconomias = qtdeEconomias;
	}
	public String getSubcategoria() {
		return subcategoria;
	}
	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}
	public String getTipoRelacao() {
		return tipoRelacao;
	}
	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
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
	public String getIdSetorComercial() {
		return idSetorComercial;
	}
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	public String getNomeSetorComercial() {
		return nomeSetorComercial;
	}
	public void setNomeSetorComercial(String nomeSetorComercial) {
		this.nomeSetorComercial = nomeSetorComercial;
	}


	public String getInscricao() {
		return inscricao;
	}


	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}


	public String getNomeUsuario() {
		return nomeUsuario;
	}


	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	

}
