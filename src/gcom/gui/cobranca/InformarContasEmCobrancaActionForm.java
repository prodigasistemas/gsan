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

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0796] Informar Contas em Cobrança por Empresa
 * @author Rafael Corrêa
 * @since 21/10/2008
 */
public class InformarContasEmCobrancaActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String idEmpresa;
	
	private String nomeEmpresa;
	
	private String[] idsCategoria;
	
	private String idUnidadeNegocio;
	
	private String idImovel;
	
	private String inscricaoImovel;
	
	private String idCliente;
	
	private String nomeCliente;
	
	private String idLocalidadeOrigem;
	
	private String nomeLocalidadeOrigem;
	
	private String idLocalidadeDestino;
	
	private String nomeLocalidadeDestino;
	
	private String idSetorComercialOrigem;
	
	private String codigoSetorComercialOrigem;
	
	private String descricaoSetorComercialOrigem;
	
	private String idSetorComercialDestino;
	
	private String codigoSetorComercialDestino;
	
	private String descricaoSetorComercialDestino;
	
	private String referenciaInicial;
	
	private String referenciaFinal;
	
	private String dataVencimentoInicial;
	
	private String dataVencimentoFinal;
	
	private String valorMinimo;
	
	private String valorMaximo;
	
	private String qtdContas;
	
	private String qtdClientes;
	
	private String valorTotalDivida;
	
	private String[] idsImovelPerfil;
	
	private String[] idsUnidadeNegocio;
	
	private String[] idsGerenciaRegional;
	
	private String codigoQuadraInicial;
	
	private String descricaoQuadraInicial;
	
	private String codigoQuadraFinal;
	
	private String descricaoQuadraFinal;
	
	private String dataInicioCiclo;
	
	private String quantidadeDiasCiclo;
	
	private String idServicoTipo;
	
	private String descricaoServicoTipo;
	
	private String colecaoInformada;
	
	private String totalSelecionado;
	
	private String[] idsLigacaoAguaSituacao;
	
	private String quantidadeContasInicial;
	
	private String quantidadeContasFinal;
	
	private String quantidadeDiasVencimento;
	
	
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	/**
	 * @return Retorna o campo idEmpresa.
	 */
	public String getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * @param idEmpresa O idEmpresa a ser setado.
	 */
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	/**
	 * @return Retorna o campo nomeEmpresa.
	 */
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	/**
	 * @param nomeEmpresa O nomeEmpresa a ser setado.
	 */
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	/**

	/**
	 * @return Retorna o campo dataVenciamentoFinal.
	 */
	public String getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}

	/**
	 * @param dataVenciamentoFinal O dataVenciamentoFinal a ser setado.
	 */
	public void setDataVencimentoFinal(String dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}

	/**
	 * @return Retorna o campo dataVencimentoInicial.
	 */
	public String getDataVencimentoInicial() {
		return dataVencimentoInicial;
	}

	/**
	 * @param dataVencimentoInicial O dataVencimentoInicial a ser setado.
	 */
	public void setDataVencimentoInicial(String dataVencimentoInicial) {
		this.dataVencimentoInicial = dataVencimentoInicial;
	}

	/**
	 * @return Retorna o campo idCliente.
	 */
	public String getIdCliente() {
		return idCliente;
	}

	/**
	 * @param idCliente O idCliente a ser setado.
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * @return Retorna o campo idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}

	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	/**
	 * @return Retorna o campo inscricaoImovel.
	 */
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	/**
	 * @param inscricaoImovel O inscricaoImovel a ser setado.
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	/**
	 * @return Retorna o campo nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente O nomeCliente a ser setado.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * @return Retorna o campo referenciaFinal.
	 */
	public String getReferenciaFinal() {
		return referenciaFinal;
	}

	/**
	 * @param referenciaFinal O referenciaFinal a ser setado.
	 */
	public void setReferenciaFinal(String referenciaFinal) {
		this.referenciaFinal = referenciaFinal;
	}

	/**
	 * @return Retorna o campo referenciaInicial.
	 */
	public String getReferenciaInicial() {
		return referenciaInicial;
	}

	/**
	 * @param referenciaInicial O referenciaInicial a ser setado.
	 */
	public void setReferenciaInicial(String referenciaInicial) {
		this.referenciaInicial = referenciaInicial;
	}

	/**
	 * @return Retorna o campo codigoSetorComercialDestino.
	 */
	public String getCodigoSetorComercialDestino() {
		return codigoSetorComercialDestino;
	}

	/**
	 * @param codigoSetorComercialDestino O codigoSetorComercialDestino a ser setado.
	 */
	public void setCodigoSetorComercialDestino(String codigoSetorComercialDestino) {
		this.codigoSetorComercialDestino = codigoSetorComercialDestino;
	}

	/**
	 * @return Retorna o campo codigoSetorComercialOrigem.
	 */
	public String getCodigoSetorComercialOrigem() {
		return codigoSetorComercialOrigem;
	}

	/**
	 * @param codigoSetorComercialOrigem O codigoSetorComercialOrigem a ser setado.
	 */
	public void setCodigoSetorComercialOrigem(String codigoSetorComercialOrigem) {
		this.codigoSetorComercialOrigem = codigoSetorComercialOrigem;
	}

	/**
	 * @return Retorna o campo descricaoSetorComercialDestino.
	 */
	public String getDescricaoSetorComercialDestino() {
		return descricaoSetorComercialDestino;
	}

	/**
	 * @param descricaoSetorComercialDestino O descricaoSetorComercialDestino a ser setado.
	 */
	public void setDescricaoSetorComercialDestino(
			String descricaoSetorComercialDestino) {
		this.descricaoSetorComercialDestino = descricaoSetorComercialDestino;
	}

	/**
	 * @return Retorna o campo descricaoSetorComercialOrigem.
	 */
	public String getDescricaoSetorComercialOrigem() {
		return descricaoSetorComercialOrigem;
	}

	/**
	 * @param descricaoSetorComercialOrigem O descricaoSetorComercialOrigem a ser setado.
	 */
	public void setDescricaoSetorComercialOrigem(
			String descricaoSetorComercialOrigem) {
		this.descricaoSetorComercialOrigem = descricaoSetorComercialOrigem;
	}

	/**
	 * @return Retorna o campo idLocalidadeDestino.
	 */
	public String getIdLocalidadeDestino() {
		return idLocalidadeDestino;
	}

	/**
	 * @param idLocalidadeDestino O idLocalidadeDestino a ser setado.
	 */
	public void setIdLocalidadeDestino(String idLocalidadeDestino) {
		this.idLocalidadeDestino = idLocalidadeDestino;
	}

	/**
	 * @return Retorna o campo idLocalidadeOrigem.
	 */
	public String getIdLocalidadeOrigem() {
		return idLocalidadeOrigem;
	}

	/**
	 * @param idLocalidadeOrigem O idLocalidadeOrigem a ser setado.
	 */
	public void setIdLocalidadeOrigem(String idLocalidadeOrigem) {
		this.idLocalidadeOrigem = idLocalidadeOrigem;
	}

	/**
	 * @return Retorna o campo nomeLocalidadeDestino.
	 */
	public String getNomeLocalidadeDestino() {
		return nomeLocalidadeDestino;
	}

	/**
	 * @param nomeLocalidadeDestino O nomeLocalidadeDestino a ser setado.
	 */
	public void setNomeLocalidadeDestino(String nomeLocalidadeDestino) {
		this.nomeLocalidadeDestino = nomeLocalidadeDestino;
	}

	/**
	 * @return Retorna o campo nomeLocalidadeOrigem.
	 */
	public String getNomeLocalidadeOrigem() {
		return nomeLocalidadeOrigem;
	}

	/**
	 * @param nomeLocalidadeOrigem O nomeLocalidadeOrigem a ser setado.
	 */
	public void setNomeLocalidadeOrigem(String nomeLocalidadeOrigem) {
		this.nomeLocalidadeOrigem = nomeLocalidadeOrigem;
	}

	/**
	 * @return Retorna o campo qtdContas.
	 */
	public String getQtdContas() {
		return qtdContas;
	}

	/**
	 * @param qtdContas O qtdContas a ser setado.
	 */
	public void setQtdContas(String qtdContas) {
		this.qtdContas = qtdContas;
	}

	/**
	 * @return Retorna o campo valorMaximo.
	 */
	public String getValorMaximo() {
		return valorMaximo;
	}

	/**
	 * @param valorMaximo O valorMaximo a ser setado.
	 */
	public void setValorMaximo(String valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	/**
	 * @return Retorna o campo valorMinimo.
	 */
	public String getValorMinimo() {
		return valorMinimo;
	}

	/**
	 * @param valorMinimo O valorMinimo a ser setado.
	 */
	public void setValorMinimo(String valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	/**
	 * @return Retorna o campo idUnidadeNegocio.
	 */
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	/**
	 * @param idUnidadeNegocio O idUnidadeNegocio a ser setado.
	 */
	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo idsCategoria.
	 */
	public String[] getIdsCategoria() {
		return idsCategoria;
	}

	/**
	 * @param idsCategoria O idsCategoria a ser setado.
	 */
	public void setIdsCategoria(String[] idsCategoria) {
		this.idsCategoria = idsCategoria;
	}

	public String getQtdClientes() {
		return qtdClientes;
	}

	public void setQtdClientes(String qtdClientes) {
		this.qtdClientes = qtdClientes;
	}

	public String getValorTotalDivida() {
		return valorTotalDivida;
	}

	public void setValorTotalDivida(String valorTotalDivida) {
		this.valorTotalDivida = valorTotalDivida;
	}

	public String[] getIdsImovelPerfil() {
		return idsImovelPerfil;
	}

	public void setIdsImovelPerfil(String[] idsImovelPerfil) {
		this.idsImovelPerfil = idsImovelPerfil;
	}

	public String[] getIdsUnidadeNegocio() {
		return idsUnidadeNegocio;
	}

	public void setIdsUnidadeNegocio(String[] idsUnidadeNegocio) {
		this.idsUnidadeNegocio = idsUnidadeNegocio;
	}

	public String[] getIdsGerenciaRegional() {
		return idsGerenciaRegional;
	}

	public void setIdsGerenciaRegional(String[] idsGerenciaRegional) {
		this.idsGerenciaRegional = idsGerenciaRegional;
	}

	public String getCodigoQuadraFinal() {
		return codigoQuadraFinal;
	}

	public void setCodigoQuadraFinal(String codigoQuadraFinal) {
		this.codigoQuadraFinal = codigoQuadraFinal;
	}

	public String getCodigoQuadraInicial() {
		return codigoQuadraInicial;
	}

	public void setCodigoQuadraInicial(String codigoQuadraInicial) {
		this.codigoQuadraInicial = codigoQuadraInicial;
	}

	public String getDescricaoQuadraFinal() {
		return descricaoQuadraFinal;
	}

	public void setDescricaoQuadraFinal(String descricaoQuadraFinal) {
		this.descricaoQuadraFinal = descricaoQuadraFinal;
	}

	public String getDescricaoQuadraInicial() {
		return descricaoQuadraInicial;
	}

	public void setDescricaoQuadraInicial(String descricaoQuadraInicial) {
		this.descricaoQuadraInicial = descricaoQuadraInicial;
	}

	public String getDataInicioCiclo() {
		return dataInicioCiclo;
	}

	public void setDataInicioCiclo(String dataInicioCiclo) {
		this.dataInicioCiclo = dataInicioCiclo;
	}

	public String getQuantidadeDiasCiclo() {
		return quantidadeDiasCiclo;
	}

	public void setQuantidadeDiasCiclo(String quantidadeDiasCiclo) {
		this.quantidadeDiasCiclo = quantidadeDiasCiclo;
	}

	public String getIdSetorComercialDestino() {
		return idSetorComercialDestino;
	}

	public void setIdSetorComercialDestino(String idSetorComercialDestino) {
		this.idSetorComercialDestino = idSetorComercialDestino;
	}

	public String getIdSetorComercialOrigem() {
		return idSetorComercialOrigem;
	}

	public void setIdSetorComercialOrigem(String idSetorComercialOrigem) {
		this.idSetorComercialOrigem = idSetorComercialOrigem;
	}

	public String getIdServicoTipo() {
		return idServicoTipo;
	}

	public void setIdServicoTipo(String idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}

	public String getDescricaoServicoTipo() {
		return descricaoServicoTipo;
	}

	public void setDescricaoServicoTipo(String descricaoServicoTipo) {
		this.descricaoServicoTipo = descricaoServicoTipo;
	}

	public String getColecaoInformada() {
		return colecaoInformada;
	}

	public void setColecaoInformada(String colecaoInformada) {
		this.colecaoInformada = colecaoInformada;
	}

	public String getTotalSelecionado() {
		return totalSelecionado;
	}

	public void setTotalSelecionado(String totalSelecionado) {
		this.totalSelecionado = totalSelecionado;
	}

	public String[] getIdsLigacaoAguaSituacao() {
		return idsLigacaoAguaSituacao;
	}

	public void setIdsLigacaoAguaSituacao(String[] idsLigacaoAguaSituacao) {
		this.idsLigacaoAguaSituacao = idsLigacaoAguaSituacao;
	}

	public String getQuantidadeContasFinal() {
		return quantidadeContasFinal;
	}

	public void setQuantidadeContasFinal(String quantidadeContasFinal) {
		this.quantidadeContasFinal = quantidadeContasFinal;
	}

	public String getQuantidadeContasInicial() {
		return quantidadeContasInicial;
	}

	public void setQuantidadeContasInicial(String quantidadeContasInicial) {
		this.quantidadeContasInicial = quantidadeContasInicial;
	}

	public String getQuantidadeDiasVencimento() {
		return quantidadeDiasVencimento;
	}

	public void setQuantidadeDiasVencimento(String quantidadeDiasVencimento) {
		this.quantidadeDiasVencimento = quantidadeDiasVencimento;
	}

}
