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
package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Ana Maria
 * @date 06/10/2006
 */
public class RelatorioGuiaDevolucaoBean implements RelatorioBean {

	private String sequencialAno;

	private String valor;

	private String registroServicoAtendimento;

	private String matriculaImovel;

	private String cliente;
	
	private String nomeCliente;
	
	private String cpfCnpj;

	private String endereco;

	private String identidade;

	private String valorExtenso;
	
	private String observacao;

	private String dataDigitacao;
	
	private String dataAnalise;
	
	private String dataAutorizacao;
	
	private String nomeUsuario;
	
	private String nomeAnalista;
	
	private String nomeAutorizador;
	
	private String via;
	
	private String contaDebito;

	/**
	 * @return Retorna o campo contaDebito.
	 */
	public String getContaDebito() {
		return contaDebito;
	}

	/**
	 * @param contaDebito O contaDebito a ser setado.
	 */
	public void setContaDebito(String contaDebito) {
		this.contaDebito = contaDebito;
	}

	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAguaBean
	 * 
	 * @param codigo
	 *            Description of the Parameter
	 * @param nome
	 *            Description of the Parameter
	 * @param municipio
	 *            Description of the Parameter
	 * @param codPref
	 *            Description of the Parameter
	 * @param indicadorUso
	 *            Description of the Parameter
	 */
	public RelatorioGuiaDevolucaoBean(String sequencialAno, String valor,
			String registroServicoAtendimento, String matriculaImovel, String cliente,
			String nomeCliente, String cpfCnpj, 
			String endereco, String identidade, String valorExtenso,
			String observacao, String dataDigitacao, String dataAnalise,
			String dataAutorizacao, String nomeUsuario, String nomeAnalista,
			String nomeAutorizador, String via, String contaDebito) {
		this.sequencialAno = sequencialAno;
		this.valor = valor;
		this.registroServicoAtendimento = registroServicoAtendimento;
		this.matriculaImovel = matriculaImovel;
		this.cliente = cliente;
		this.nomeCliente = nomeCliente;
		this.cpfCnpj = cpfCnpj;
		this.endereco = endereco;
		this.identidade = identidade;
		this.valorExtenso = valorExtenso;
		this.observacao = observacao;
		this.dataDigitacao = dataDigitacao;
		this.dataAnalise = dataAnalise;
		this.dataAutorizacao = dataAutorizacao;
		this.nomeUsuario = nomeUsuario;
		this.nomeAnalista = nomeAnalista;
		this.nomeAutorizador = nomeAutorizador;
		this.via = via;
		this.contaDebito = contaDebito;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDataAnalise() {
		return dataAnalise;
	}

	public void setDataAnalise(String dataAnalise) {
		this.dataAnalise = dataAnalise;
	}

	public String getDataAutorizacao() {
		return dataAutorizacao;
	}

	public void setDataAutorizacao(String dataAutorizacao) {
		this.dataAutorizacao = dataAutorizacao;
	}

	public String getDataDigitacao() {
		return dataDigitacao;
	}

	public void setDataDigitacao(String dataDigitacao) {
		this.dataDigitacao = dataDigitacao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getNomeAnalista() {
		return nomeAnalista;
	}

	public void setNomeAnalista(String nomeAnalista) {
		this.nomeAnalista = nomeAnalista;
	}

	public String getNomeAutorizador() {
		return nomeAutorizador;
	}

	public void setNomeAutorizador(String nomeAutorizador) {
		this.nomeAutorizador = nomeAutorizador;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}


	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getRegistroServicoAtendimento() {
		return registroServicoAtendimento;
	}

	public void setRegistroServicoAtendimento(String registroServicoAtendimento) {
		this.registroServicoAtendimento = registroServicoAtendimento;
	}

	public String getSequencialAno() {
		return sequencialAno;
	}

	public void setSequencialAno(String sequencialAno) {
		this.sequencialAno = sequencialAno;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getValorExtenso() {
		return valorExtenso;
	}

	public void setValorExtenso(String valorExtenso) {
		this.valorExtenso = valorExtenso;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}


}
