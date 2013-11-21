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
package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

public class ConsultarDebitoImovelActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String codigoImovel;
	
	private String tipoRelacao;

	private String referenciaInicial;

	private String referenciaFinal;

	private String dataVencimentoInicial;

	private String dataVencimentoFinal;
	
	private String ligacaoAgua;
	
	private String ligacaoEsgoto;
	
	private String maticula;
	
	private String inscricao;
	
	private String nomeCliente;
	
	private String tipoRelacaoCliente;
	
	private String cpf;
	
	private String cnpj;
	
	private String refInicial;
	
	private String refFinal;
	
	private String dtInicial;
	
	private String dtFinal;
	
	private String ligacaoAguaId;
	private String ligacaoEsgotoId;
	private String indicadorEmissaoExtratoNaConsulta;
	
	private String[] contasSelecionadas = {};
	
	private String[] debitosSelecionados = {};
	
	private String[] creditosSelecionados = {};
	
	private String[] guiasSelecionadas = {};
	
	public String[] getContasSelecionadas() {
		return contasSelecionadas;
	}

	public void setContasSelecionadas(String[] contasSelecionadas) {
		this.contasSelecionadas = contasSelecionadas;
	}

	public String[] getDebitosSelecionados() {
		return debitosSelecionados;
	}

	public void setDebitosSelecionados(String[] debitosSelecionados) {
		this.debitosSelecionados = debitosSelecionados;
	}

	public String[] getCreditosSelecionados() {
		return creditosSelecionados;
	}

	public void setCreditosSelecionados(String[] creditosSelecionados) {
		this.creditosSelecionados = creditosSelecionados;
	}

	public String[] getGuiasSelecionadas() {
		return guiasSelecionadas;
	}

	public void setGuiasSelecionadas(String[] guiasSelecionadas) {
		this.guiasSelecionadas = guiasSelecionadas;
	}

	public String getIndicadorEmissaoExtratoNaConsulta() {
		return indicadorEmissaoExtratoNaConsulta;
	}

	public void setIndicadorEmissaoExtratoNaConsulta(
			String indicadorEmissaoExtratoNaConsulta) {
		this.indicadorEmissaoExtratoNaConsulta = indicadorEmissaoExtratoNaConsulta;
	}

	public String getCodigoImovel() {
		return codigoImovel;
	}

	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	public String getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}

	public void setDataVencimentoFinal(String dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}

	public String getDataVencimentoInicial() {
		return dataVencimentoInicial;
	}

	public void setDataVencimentoInicial(String dataVencimentoInicial) {
		this.dataVencimentoInicial = dataVencimentoInicial;
	}

	public String getReferenciaFinal() {
		return referenciaFinal;
	}

	public void setReferenciaFinal(String referenciaFinal) {
		this.referenciaFinal = referenciaFinal;
	}

	public String getReferenciaInicial() {
		return referenciaInicial;
	}

	public void setReferenciaInicial(String referenciaInicial) {
		this.referenciaInicial = referenciaInicial;
	}

	public String getTipoRelacao() {
		return tipoRelacao;
	}

	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getLigacaoAgua() {
		return ligacaoAgua;
	}

	public void setLigacaoAgua(String ligacaoAgua) {
		this.ligacaoAgua = ligacaoAgua;
	}

	public String getLigacaoEsgoto() {
		return ligacaoEsgoto;
	}

	public void setLigacaoEsgoto(String ligacaoEsgoto) {
		this.ligacaoEsgoto = ligacaoEsgoto;
	}

	public String getMaticula() {
		return maticula;
	}

	public void setMaticula(String maticula) {
		this.maticula = maticula;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getTipoRelacaoCliente() {
		return tipoRelacaoCliente;
	}

	public void setTipoRelacaoCliente(String tipoRelacaoCliente) {
		this.tipoRelacaoCliente = tipoRelacaoCliente;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(String dtFinal) {
		this.dtFinal = dtFinal;
	}

	public String getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(String dtInicial) {
		this.dtInicial = dtInicial;
	}

	public String getRefFinal() {
		return refFinal;
	}

	public void setRefFinal(String refFinal) {
		this.refFinal = refFinal;
	}

	public String getRefInicial() {
		return refInicial;
	}

	public void setRefInicial(String refInicial) {
		this.refInicial = refInicial;
	}

	public String getLigacaoAguaId() {
		return ligacaoAguaId;
	}

	public void setLigacaoAguaId(String ligacaoAguaId) {
		this.ligacaoAguaId = ligacaoAguaId;
	}

	public String getLigacaoEsgotoId() {
		return ligacaoEsgotoId;
	}

	public void setLigacaoEsgotoId(String ligacaoEsgotoId) {
		this.ligacaoEsgotoId = ligacaoEsgotoId;
	}

}
