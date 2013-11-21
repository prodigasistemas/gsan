/* Feito por Felipe e Rafael na data 16/12/2005 */

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
package gcom.gui.faturamento;

import org.apache.struts.action.ActionForm;


public class AlterarFaturamentoDadosActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String idLeiturista; 

	private String idSetorComercial; 

	private String idSituacaoLeituraAtual; 

	private String consumoInformado; 

	private String consumoMedido; 

	private String dataLeituraAnterior; 

	private String dataLeituraAtual; 
	
	private String enderecoFormatado;
	
	private String idImovel; 

	private String idLocalidade; 

	private String idImovelSelecionado; 

	private String indicadorConfirmacao; 

	private String leituraAnterior; 

	private String leituraAtual; 

	private String nomeAnormalidade; 

	private String nomeLeiturista; 

	private String nomeLocalidade; 

	private String nomeSetorComercial; 

	private String nomeSituacaoLeituraAtual; 

	private String tipoMedicao; 

	private String tipoMedicaoSelecionada; 
	
	private String idAnormalidade;
	
	private String inscricaoImovel;
	
	private String nomeUsuario;
	
	private String ligacaoAguaSituacao;
	
	private String ligacaoEsgotoSituacao;

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getConsumoInformado() {
		return consumoInformado;
	}

	public void setConsumoInformado(String consumoInformado) {
		this.consumoInformado = consumoInformado;
	}

	public String getConsumoMedido() {
		return consumoMedido;
	}

	public void setConsumoMedido(String consumoMedido) {
		this.consumoMedido = consumoMedido;
	}

	public String getDataLeituraAnterior() {
		return dataLeituraAnterior;
	}

	public void setDataLeituraAnterior(String dataLeituraAnterior) {
		this.dataLeituraAnterior = dataLeituraAnterior;
	}

	public String getDataLeituraAtual() {
		return dataLeituraAtual;
	}

	public void setDataLeituraAtual(String dataLeituraAtual) {
		this.dataLeituraAtual = dataLeituraAtual;
	}

	public String getEnderecoFormatado() {
		return enderecoFormatado;
	}

	public void setEnderecoFormatado(String enderecoFormatado) {
		this.enderecoFormatado = enderecoFormatado;
	}

	public String getIdAnormalidade() {
		return idAnormalidade;
	}

	public void setIdAnormalidade(String idAnormalidade) {
		this.idAnormalidade = idAnormalidade;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getIdImovelSelecionado() {
		return idImovelSelecionado;
	}

	public void setIdImovelSelecionado(String idImovelSelecionado) {
		this.idImovelSelecionado = idImovelSelecionado;
	}

	public String getIdLeiturista() {
		return idLeiturista;
	}

	public void setIdLeiturista(String idLeiturista) {
		this.idLeiturista = idLeiturista;
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

	public String getIdSituacaoLeituraAtual() {
		return idSituacaoLeituraAtual;
	}

	public void setIdSituacaoLeituraAtual(String idSituacaoLeituraAtual) {
		this.idSituacaoLeituraAtual = idSituacaoLeituraAtual;
	}

	public String getIndicadorConfirmacao() {
		return indicadorConfirmacao;
	}

	public void setIndicadorConfirmacao(String indicadorConfirmacao) {
		this.indicadorConfirmacao = indicadorConfirmacao;
	}

	public String getLeituraAnterior() {
		return leituraAnterior;
	}

	public void setLeituraAnterior(String leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}

	public String getLeituraAtual() {
		return leituraAtual;
	}

	public void setLeituraAtual(String leituraAtual) {
		this.leituraAtual = leituraAtual;
	}

	public String getNomeAnormalidade() {
		return nomeAnormalidade;
	}

	public void setNomeAnormalidade(String nomeAnormalidade) {
		this.nomeAnormalidade = nomeAnormalidade;
	}

	public String getNomeLeiturista() {
		return nomeLeiturista;
	}

	public void setNomeLeiturista(String nomeLeiturista) {
		this.nomeLeiturista = nomeLeiturista;
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

	public String getNomeSituacaoLeituraAtual() {
		return nomeSituacaoLeituraAtual;
	}

	public void setNomeSituacaoLeituraAtual(String nomeSituacaoLeituraAtual) {
		this.nomeSituacaoLeituraAtual = nomeSituacaoLeituraAtual;
	}

	public String getTipoMedicao() {
		return tipoMedicao;
	}

	public void setTipoMedicao(String tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}

	public String getTipoMedicaoSelecionada() {
		return tipoMedicaoSelecionada;
	}

	public void setTipoMedicaoSelecionada(String tipoMedicaoSelecionada) {
		this.tipoMedicaoSelecionada = tipoMedicaoSelecionada;
	}

	/**
	 * @return Retorna o campo ligacaoAguaSituacao.
	 */
	public String getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	/**
	 * @param ligacaoAguaSituacao O ligacaoAguaSituacao a ser setado.
	 */
	public void setLigacaoAguaSituacao(String ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	/**
	 * @return Retorna o campo ligacaoEsgotoSituacao.
	 */
	public String getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	/**
	 * @param ligacaoEsgotoSituacao O ligacaoEsgotoSituacao a ser setado.
	 */
	public void setLigacaoEsgotoSituacao(String ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	/**
	 * @return Retorna o campo nomeUsuario.
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	/**
	 * @param nomeUsuario O nomeUsuario a ser setado.
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	} 
	

}

	