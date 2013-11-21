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
package gcom.gui.arrecadacao.pagamento;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ConsultarPagamentoRetornoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String inscricao;
	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	private String nomeCliente;
	private String cpfCnpj;
	private String profissao;
	private String ramoAtividade;
	private String telefone;
	private String idArrecadador;
	private String descricaoArrecadador;
	private String dataLancamento;
	private String sequencialAviso;
	private String tipoAviso;
	private String tipoRemessa;
	private String identificacaoServico;
	private String numeroSequencial;
	private String dataGeracao;
	private String totalRegistros;
	private String valorTotalMovimento;
	private String dataProcessamento;
	private String horaProcessamento;
	
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public String getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public String getDescricaoArrecadador() {
		return descricaoArrecadador;
	}
	public void setDescricaoArrecadador(String descricaoArrecadador) {
		this.descricaoArrecadador = descricaoArrecadador;
	}
	public String getIdArrecadador() {
		return idArrecadador;
	}
	public void setIdArrecadador(String idArrecadador) {
		this.idArrecadador = idArrecadador;
	}
	public String getInscricao() {
		return inscricao;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public String getRamoAtividade() {
		return ramoAtividade;
	}
	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}
	public String getSequencialAviso() {
		return sequencialAviso;
	}
	public void setSequencialAviso(String sequencialAviso) {
		this.sequencialAviso = sequencialAviso;
	}
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}
	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}
	public String getTipoAviso() {
		return tipoAviso;
	}
	public void setTipoAviso(String tipoAviso) {
		this.tipoAviso = tipoAviso;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	/**
	 * @return Retorna o campo dataGeracao.
	 */
	public String getDataGeracao() {
		return dataGeracao;
	}
	/**
	 * @param dataGeracao O dataGeracao a ser setado.
	 */
	public void setDataGeracao(String dataGeracao) {
		this.dataGeracao = dataGeracao;
	}
	/**
	 * @return Retorna o campo dataProcessamento.
	 */
	public String getDataProcessamento() {
		return dataProcessamento;
	}
	/**
	 * @param dataProcessamento O dataProcessamento a ser setado.
	 */
	public void setDataProcessamento(String dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}
	/**
	 * @return Retorna o campo horaProcessamento.
	 */
	public String getHoraProcessamento() {
		return horaProcessamento;
	}
	/**
	 * @param horaProcessamento O horaProcessamento a ser setado.
	 */
	public void setHoraProcessamento(String horaProcessamento) {
		this.horaProcessamento = horaProcessamento;
	}
	/**
	 * @return Retorna o campo identificacaoServico.
	 */
	public String getIdentificacaoServico() {
		return identificacaoServico;
	}
	/**
	 * @param identificacaoServico O identificacaoServico a ser setado.
	 */
	public void setIdentificacaoServico(String identificacaoServico) {
		this.identificacaoServico = identificacaoServico;
	}
	/**
	 * @return Retorna o campo numeroSequencial.
	 */
	public String getNumeroSequencial() {
		return numeroSequencial;
	}
	/**
	 * @param numeroSequencial O numeroSequencial a ser setado.
	 */
	public void setNumeroSequencial(String numeroSequencial) {
		this.numeroSequencial = numeroSequencial;
	}
	/**
	 * @return Retorna o campo tipoRemessa.
	 */
	public String getTipoRemessa() {
		return tipoRemessa;
	}
	/**
	 * @param tipoRemessa O tipoRemessa a ser setado.
	 */
	public void setTipoRemessa(String tipoRemessa) {
		this.tipoRemessa = tipoRemessa;
	}
	/**
	 * @return Retorna o campo totalRegistros.
	 */
	public String getTotalRegistros() {
		return totalRegistros;
	}
	/**
	 * @param totalRegistros O totalRegistros a ser setado.
	 */
	public void setTotalRegistros(String totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	/**
	 * @return Retorna o campo valorTotalMovimento.
	 */
	public String getValorTotalMovimento() {
		return valorTotalMovimento;
	}
	/**
	 * @param valorTotalMovimento O valorTotalMovimento a ser setado.
	 */
	public void setValorTotalMovimento(String valorTotalMovimento) {
		this.valorTotalMovimento = valorTotalMovimento;
	}
	
	
}
