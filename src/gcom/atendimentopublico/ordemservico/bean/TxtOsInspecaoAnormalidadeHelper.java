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
package gcom.atendimentopublico.ordemservico.bean;

import java.util.Collection;

/**
 * [UC0713] Emitir Ordem de Serviço Seletiva
 * [SB0002] Gerar TXT 
 * 
 * @author Vivianne Sousa
 * @date 29/06/2011
 */
public class TxtOsInspecaoAnormalidadeHelper {
	
	
	private Integer codigoOrdemServico;
	private Integer matriculaImovel;
	private String ultimaAlteracao;
	private String descPerfilImovel;
	private String descSituacaoLigAgua;
	private String descSituacaoLigEsgoto;
	private String inscricaoImovel;
	private String enderecoImovel;
	private String categoriaRES;
	private String categoriaPUB;
	private String categoriaIND;
	private String categoriaCOM;
	private String grupoFaturamento;
	private String consumoImovel;
	private String coletaEsgoto;
	
	private String nomeCliente;
	private String cpfCnpjCliente;
	private String identidadeCliente;
	private String foneCliente;
	
	private String marcaHidrometro;
	private String numeroHidrometro;
	private String localInstalacao;
	private String dataInstalacao;
	private String protecaoHidrometro;
	private String indicadorCavalete;
	private String capacidadeHidrometro;
	private String diamentroHidrometro;
	private String anormalidade;
	private String leituraAtualFaturamento;
	private Collection colecaoAtendimentoMotivoEncerramento;
	
	public Collection getColecaoAtendimentoMotivoEncerramento() {
		return colecaoAtendimentoMotivoEncerramento;
	}
	public void setColecaoAtendimentoMotivoEncerramento(
			Collection colecaoAtendimentoMotivoEncerramento) {
		this.colecaoAtendimentoMotivoEncerramento = colecaoAtendimentoMotivoEncerramento;
	}
	public String getAnormalidade() {
		return anormalidade;
	}
	public void setAnormalidade(String anormalidade) {
		this.anormalidade = anormalidade;
	}
	public String getCapacidadeHidrometro() {
		return capacidadeHidrometro;
	}
	public void setCapacidadeHidrometro(String capacidadeHidrometro) {
		this.capacidadeHidrometro = capacidadeHidrometro;
	}
	public String getCategoriaCOM() {
		return categoriaCOM;
	}
	public void setCategoriaCOM(String categoriaCOM) {
		this.categoriaCOM = categoriaCOM;
	}
	public String getCategoriaIND() {
		return categoriaIND;
	}
	public void setCategoriaIND(String categoriaIND) {
		this.categoriaIND = categoriaIND;
	}
	public String getCategoriaPUB() {
		return categoriaPUB;
	}
	public void setCategoriaPUB(String categoriaPUB) {
		this.categoriaPUB = categoriaPUB;
	}
	public String getCategoriaRES() {
		return categoriaRES;
	}
	public void setCategoriaRES(String categoriaRES) {
		this.categoriaRES = categoriaRES;
	}
	public Integer getCodigoOrdemServico() {
		return codigoOrdemServico;
	}
	public void setCodigoOrdemServico(Integer codigoOrdemServico) {
		this.codigoOrdemServico = codigoOrdemServico;
	}
	public String getColetaEsgoto() {
		return coletaEsgoto;
	}
	public void setColetaEsgoto(String coletaEsgoto) {
		this.coletaEsgoto = coletaEsgoto;
	}
	public String getConsumoImovel() {
		return consumoImovel;
	}
	public void setConsumoImovel(String consumoImovel) {
		this.consumoImovel = consumoImovel;
	}
	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}
	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}
	public String getDataInstalacao() {
		return dataInstalacao;
	}
	public void setDataInstalacao(String dataInstalacao) {
		this.dataInstalacao = dataInstalacao;
	}
	public String getDescPerfilImovel() {
		return descPerfilImovel;
	}
	public void setDescPerfilImovel(String descPerfilImovel) {
		this.descPerfilImovel = descPerfilImovel;
	}
	public String getDescSituacaoLigAgua() {
		return descSituacaoLigAgua;
	}
	public void setDescSituacaoLigAgua(String descSituacaoLigAgua) {
		this.descSituacaoLigAgua = descSituacaoLigAgua;
	}
	public String getDescSituacaoLigEsgoto() {
		return descSituacaoLigEsgoto;
	}
	public void setDescSituacaoLigEsgoto(String descSituacaoLigEsgoto) {
		this.descSituacaoLigEsgoto = descSituacaoLigEsgoto;
	}
	public String getDiamentroHidrometro() {
		return diamentroHidrometro;
	}
	public void setDiamentroHidrometro(String diamentroHidrometro) {
		this.diamentroHidrometro = diamentroHidrometro;
	}
	public String getEnderecoImovel() {
		return enderecoImovel;
	}
	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}
	public String getFoneCliente() {
		return foneCliente;
	}
	public void setFoneCliente(String foneCliente) {
		this.foneCliente = foneCliente;
	}
	public String getGrupoFaturamento() {
		return grupoFaturamento;
	}
	public void setGrupoFaturamento(String grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}
	public String getIdentidadeCliente() {
		return identidadeCliente;
	}
	public void setIdentidadeCliente(String identidadeCliente) {
		this.identidadeCliente = identidadeCliente;
	}
	public String getIndicadorCavalete() {
		return indicadorCavalete;
	}
	public void setIndicadorCavalete(String indicadorCavalete) {
		this.indicadorCavalete = indicadorCavalete;
	}
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public String getLeituraAtualFaturamento() {
		return leituraAtualFaturamento;
	}
	public void setLeituraAtualFaturamento(String leituraAtualFaturamento) {
		this.leituraAtualFaturamento = leituraAtualFaturamento;
	}
	public String getLocalInstalacao() {
		return localInstalacao;
	}
	public void setLocalInstalacao(String localInstalacao) {
		this.localInstalacao = localInstalacao;
	}
	public String getMarcaHidrometro() {
		return marcaHidrometro;
	}
	public void setMarcaHidrometro(String marcaHidrometro) {
		this.marcaHidrometro = marcaHidrometro;
	}
	public Integer getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(Integer matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}
	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}
	public String getProtecaoHidrometro() {
		return protecaoHidrometro;
	}
	public void setProtecaoHidrometro(String protecaoHidrometro) {
		this.protecaoHidrometro = protecaoHidrometro;
	}
	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
}