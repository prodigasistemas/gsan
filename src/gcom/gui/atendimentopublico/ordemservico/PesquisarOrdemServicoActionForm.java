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
package gcom.gui.atendimentopublico.ordemservico;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0450] Pesquisar Ordem Serviço
 * 
 * @author Rafael Pinto
 *
 * @date 15/06/2006
 */
public class PesquisarOrdemServicoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String numeroRA;
	private String descricaoRA;
	private String documentoCobranca;
	private String descricaoDocumentoCobranca;

	private String situacaoOrdemServico;
	private String situacaoProgramacao;
	
	private Integer[] tipoServico;
	private Integer[] tipoServicoSelecionados;
	
	private String matriculaImovel;
	private String inscricaoImovel;
	private String codigoCliente;
	private String nomeCliente;
	
	private String unidadeGeracao;
	private String descricaoUnidadeGeracao;
	
	private String unidadeAtual;
	private String descricaoUnidadeAtual;
	
	private String unidadeSuperior;
	private String descricaoUnidadeSuperior;
	
	private String periodoAtendimentoInicial;
	private String periodoAtendimentoFinal;
	private String periodoGeracaoInicial;
	private String periodoGeracaoFinal;
	private String periodoProgramacaoInicial;
	private String periodoProgramacaoFinal;
	private String periodoEncerramentoInicial;
	private String periodoEncerramentoFinal;
	
	private String municipio;
	private String descricaoMunicipio;
	private String bairro;
	private String idBairro;
	private String descricaoBairro;
	private String areaBairro;
	private String logradouro;
	private String descricaoLogradouro;
	
	private String origemOrdemServico;
	
	private String projeto;

	/*public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
		
		numeroRA = null;
		descricaoRA = null;
		documentoCobranca = null;
		descricaoDocumentoCobranca = null;

		situacaoOrdemServico= null;
		situacaoProgramacao= null;
		
		matriculaImovel= null;
		inscricaoImovel= null;
		codigoCliente= null;
		nomeCliente= null;
		
		unidadeGeracao= null;
		descricaoUnidadeGeracao= null;
		
		unidadeAtual= null;
		descricaoUnidadeAtual= null;
		
		unidadeSuperior= null;
		descricaoUnidadeSuperior= null;
		
		periodoAtendimentoInicial = null;
		periodoAtendimentoFinal = null;
		periodoEncerramentoInicial = null;
		periodoEncerramentoFinal = null;
		periodoGeracaoInicial = null;
		periodoGeracaoFinal = null;
		periodoProgramacaoInicial = null;
		periodoProgramacaoFinal = null;
		
		municipio = null;
		descricaoMunicipio = null;
		idBairro = null;
		bairro = null;
		descricaoBairro = null;

		logradouro = null;
		descricaoLogradouro = null;

	}*/

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}


	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}


	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getPeriodoAtendimentoFinal() {
		return periodoAtendimentoFinal;
	}


	public void setPeriodoAtendimentoFinal(String periodoAtendimentoFinal) {
		this.periodoAtendimentoFinal = periodoAtendimentoFinal;
	}


	public String getPeriodoAtendimentoInicial() {
		return periodoAtendimentoInicial;
	}


	public void setPeriodoAtendimentoInicial(String periodoAtendimentoInicial) {
		this.periodoAtendimentoInicial = periodoAtendimentoInicial;
	}


	public String getPeriodoEncerramentoFinal() {
		return periodoEncerramentoFinal;
	}


	public void setPeriodoEncerramentoFinal(String periodoEncerramentoFinal) {
		this.periodoEncerramentoFinal = periodoEncerramentoFinal;
	}


	public String getPeriodoEncerramentoInicial() {
		return periodoEncerramentoInicial;
	}


	public void setPeriodoEncerramentoInicial(String periodoEncerramentoInicial) {
		this.periodoEncerramentoInicial = periodoEncerramentoInicial;
	}

	public String getNumeroRA() {
		return numeroRA;
	}


	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getDescricaoBairro() {
		return descricaoBairro;
	}

	public void setDescricaoBairro(String descricaoBairro) {
		this.descricaoBairro = descricaoBairro;
	}

	public String getDescricaoDocumentoCobranca() {
		return descricaoDocumentoCobranca;
	}

	public void setDescricaoDocumentoCobranca(String descricaoDocumentoCobranca) {
		this.descricaoDocumentoCobranca = descricaoDocumentoCobranca;
	}

	public String getDescricaoLogradouro() {
		return descricaoLogradouro;
	}

	public void setDescricaoLogradouro(String descricaoLogradouro) {
		this.descricaoLogradouro = descricaoLogradouro;
	}

	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}

	public void setDescricaoMunicipio(String descricaoMunicipio) {
		this.descricaoMunicipio = descricaoMunicipio;
	}

	public String getDescricaoRA() {
		return descricaoRA;
	}

	public void setDescricaoRA(String descricaoRA) {
		this.descricaoRA = descricaoRA;
	}

	public String getDescricaoUnidadeAtual() {
		return descricaoUnidadeAtual;
	}

	public void setDescricaoUnidadeAtual(String descricaoUnidadeAtual) {
		this.descricaoUnidadeAtual = descricaoUnidadeAtual;
	}

	public String getDescricaoUnidadeGeracao() {
		return descricaoUnidadeGeracao;
	}

	public void setDescricaoUnidadeGeracao(String descricaoUnidadeGeracao) {
		this.descricaoUnidadeGeracao = descricaoUnidadeGeracao;
	}

	public String getDescricaoUnidadeSuperior() {
		return descricaoUnidadeSuperior;
	}

	public void setDescricaoUnidadeSuperior(String descricaoUnidadeSuperior) {
		this.descricaoUnidadeSuperior = descricaoUnidadeSuperior;
	}

	public String getDocumentoCobranca() {
		return documentoCobranca;
	}

	public void setDocumentoCobranca(String documentoCobranca) {
		this.documentoCobranca = documentoCobranca;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getPeriodoGeracaoFinal() {
		return periodoGeracaoFinal;
	}

	public void setPeriodoGeracaoFinal(String periodoGeracaoFinal) {
		this.periodoGeracaoFinal = periodoGeracaoFinal;
	}

	public String getPeriodoGeracaoInicial() {
		return periodoGeracaoInicial;
	}

	public void setPeriodoGeracaoInicial(String periodoGeracaoInicial) {
		this.periodoGeracaoInicial = periodoGeracaoInicial;
	}

	public String getPeriodoProgramacaoFinal() {
		return periodoProgramacaoFinal;
	}

	public void setPeriodoProgramacaoFinal(String periodoProgramacaoFinal) {
		this.periodoProgramacaoFinal = periodoProgramacaoFinal;
	}

	public String getPeriodoProgramacaoInicial() {
		return periodoProgramacaoInicial;
	}

	public void setPeriodoProgramacaoInicial(String periodoProgramacaoInicial) {
		this.periodoProgramacaoInicial = periodoProgramacaoInicial;
	}

	public String getSituacaoOrdemServico() {
		return situacaoOrdemServico;
	}

	public void setSituacaoOrdemServico(String situacaoOrdemServico) {
		this.situacaoOrdemServico = situacaoOrdemServico;
	}

	public String getSituacaoProgramacao() {
		return situacaoProgramacao;
	}

	public void setSituacaoProgramacao(String situacaoProgramacao) {
		this.situacaoProgramacao = situacaoProgramacao;
	}

	public String getUnidadeAtual() {
		return unidadeAtual;
	}

	public void setUnidadeAtual(String unidadeAtual) {
		this.unidadeAtual = unidadeAtual;
	}

	public String getUnidadeGeracao() {
		return unidadeGeracao;
	}

	public void setUnidadeGeracao(String unidadeGeracao) {
		this.unidadeGeracao = unidadeGeracao;
	}

	public String getUnidadeSuperior() {
		return unidadeSuperior;
	}

	public void setUnidadeSuperior(String unidadeSuperior) {
		this.unidadeSuperior = unidadeSuperior;
	}

	public Integer[] getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(Integer[] tipoServico) {
		this.tipoServico = tipoServico;
	}

	public Integer[] getTipoServicoSelecionados() {
		return tipoServicoSelecionados;
	}

	public void setTipoServicoSelecionados(Integer[] tipoServicoSelecionados) {
		this.tipoServicoSelecionados = tipoServicoSelecionados;
	}

	public String getAreaBairro() {
		return areaBairro;
	}

	public void setAreaBairro(String areaBairro) {
		this.areaBairro = areaBairro;
	}

	public String getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(String idBairro) {
		this.idBairro = idBairro;
	}

	/**
	 * @return Retorna o campo origemOrdemServico.
	 */
	public String getOrigemOrdemServico() {
		return origemOrdemServico;
	}

	/**
	 * @param origemOrdemServico O origemOrdemServico a ser setado.
	 */
	public void setOrigemOrdemServico(String origemOrdemServico) {
		this.origemOrdemServico = origemOrdemServico;
	}


	public String getProjeto() {
		return projeto;
	}


	public void setProjeto(String projeto) {
		this.projeto = projeto;
	}

}