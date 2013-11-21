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
* Ivan Sérgio Virginio da Silva Júnior
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

/**
 * 
 * Bean do [UC0713] Emitir Ordem de Servico Seletiva 
 *
 * @author Ivan Sérgio
 * @date 10/11/2007
 */
public class RelatorioEmitirOrdemServicoSeletivaSugestaoBean implements RelatorioBean {
	
	private String descricaoTipoServico;
	private String totalSelecionados;
	private String sugestao;
	private String firma;
	
	// Parametros
	private String nomeFirma;
	private String nomeElo;
	private String tipoOrdem;
	private String quantidadeMaxima;
	private String nomeLocalidadeInicial;
	private String nomeLocalidadeFinal;
	private String nomeSetorComercialInicial;
	private String nomeSetorComercialFinal;
	private String quadraInicial;
	private String quadraFinal;
	
	// Caracteristicas
	private String perfilImovelDescricao;
	private String categoriaDescricao;
	private String subCategoriaDescricao;
	private String quantidadeEconomia;
	private String quantidadeDocumentos;
	private String numeroMoradores;
	private String areaConstruida;
	private String imovelCondominio;
	private String mediaImovel;
	private String consumoEconomia;
	private String tipoMedicaoDescricao;
	private String situacaoLigacaoAgua;
	private String situacaoLigacaoAguaDescricao;
	
	// Hidrometro
	private String capacidadeDescricao;
	private String marcaDescricao;
	//private String mesAnoInstalacao;
	private String mesAnoInstalacaoInicial;
	private String mesAnoInstalacaoFinal;
	private String anormalidadeHidrometro;
	private String localInstalacaoDescricao;
	
	private String idLocalidade;
	private String desLocalidade;
	private String idSetorComercial;
	private String desSetorComercial;

	
	public String getAreaConstruida() {
		return areaConstruida;
	}
	public void setAreaConstruida(String areaConstruida) {
		this.areaConstruida = areaConstruida;
	}
	public String getCapacidadeDescricao() {
		return capacidadeDescricao;
	}
	public void setCapacidadeDescricao(String capacidadeDescricao) {
		this.capacidadeDescricao = capacidadeDescricao;
	}
	public String getCategoriaDescricao() {
		return categoriaDescricao;
	}
	public void setCategoriaDescricao(String categoriaDescricao) {
		this.categoriaDescricao = categoriaDescricao;
	}
	public String getConsumoEconomia() {
		return consumoEconomia;
	}
	public void setConsumoEconomia(String consumoEconomia) {
		this.consumoEconomia = consumoEconomia;
	}
	public String getDescricaoTipoServico() {
		return descricaoTipoServico;
	}
	public void setDescricaoTipoServico(String descricaoTipoServico) {
		this.descricaoTipoServico = descricaoTipoServico;
	}
	public String getFirma() {
		return firma;
	}
	public void setFirma(String firma) {
		this.firma = firma;
	}
	public String getImovelCondominio() {
		return imovelCondominio;
	}
	public void setImovelCondominio(String imovelCondominio) {
		this.imovelCondominio = imovelCondominio;
	}
	public String getMarcaDescricao() {
		return marcaDescricao;
	}
	public void setMarcaDescricao(String marcaDescricao) {
		this.marcaDescricao = marcaDescricao;
	}
	public String getMediaImovel() {
		return mediaImovel;
	}
	public void setMediaImovel(String mediaImovel) {
		this.mediaImovel = mediaImovel;
	}
//	public String getMesAnoInstalacao() {
//		return mesAnoInstalacao;
//	}
//	public void setMesAnoInstalacao(String mesAnoInstalacao) {
//		this.mesAnoInstalacao = mesAnoInstalacao;
//	}
	public String getNomeFirma() {
		return nomeFirma;
	}
	public void setNomeFirma(String nomeFirma) {
		this.nomeFirma = nomeFirma;
	}
	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}
	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}
	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}
	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}
	public String getNomeSetorComercialFinal() {
		return nomeSetorComercialFinal;
	}
	public void setNomeSetorComercialFinal(String nomeSetorComercialFinal) {
		this.nomeSetorComercialFinal = nomeSetorComercialFinal;
	}
	public String getNomeSetorComercialInicial() {
		return nomeSetorComercialInicial;
	}
	public void setNomeSetorComercialInicial(String nomeSetorComercialInicial) {
		this.nomeSetorComercialInicial = nomeSetorComercialInicial;
	}
	public String getNumeroMoradores() {
		return numeroMoradores;
	}
	public void setNumeroMoradores(String numeroMoradores) {
		this.numeroMoradores = numeroMoradores;
	}
	public String getPerfilImovelDescricao() {
		return perfilImovelDescricao;
	}
	public void setPerfilImovelDescricao(String perfilImovelDescricao) {
		this.perfilImovelDescricao = perfilImovelDescricao;
	}
	public String getQuadraFinal() {
		return quadraFinal;
	}
	public void setQuadraFinal(String quadraFinal) {
		this.quadraFinal = quadraFinal;
	}
	public String getQuadraInicial() {
		return quadraInicial;
	}
	public void setQuadraInicial(String quadraInicial) {
		this.quadraInicial = quadraInicial;
	}
	public String getQuantidadeDocumentos() {
		return quantidadeDocumentos;
	}
	public void setQuantidadeDocumentos(String quantidadeDocumentos) {
		this.quantidadeDocumentos = quantidadeDocumentos;
	}
	public String getQuantidadeEconomia() {
		return quantidadeEconomia;
	}
	public void setQuantidadeEconomia(String quantidadeEconomia) {
		this.quantidadeEconomia = quantidadeEconomia;
	}
	public String getQuantidadeMaxima() {
		return quantidadeMaxima;
	}
	public void setQuantidadeMaxima(String quantidadeMaxima) {
		this.quantidadeMaxima = quantidadeMaxima;
	}
	public String getSubCategoriaDescricao() {
		return subCategoriaDescricao;
	}
	public void setSubCategoriaDescricao(String subCategoriaDescricao) {
		this.subCategoriaDescricao = subCategoriaDescricao;
	}
	public String getSugestao() {
		return sugestao;
	}
	public void setSugestao(String sugestao) {
		this.sugestao = sugestao;
	}
	public String getTipoMedicaoDescricao() {
		return tipoMedicaoDescricao;
	}
	public void setTipoMedicaoDescricao(String tipoMedicaoDescricao) {
		this.tipoMedicaoDescricao = tipoMedicaoDescricao;
	}
	public String getTipoOrdem() {
		return tipoOrdem;
	}
	public void setTipoOrdem(String tipoOrdem) {
		this.tipoOrdem = tipoOrdem;
	}
	public String getTotalSelecionados() {
		return totalSelecionados;
	}
	public void setTotalSelecionados(String totalSelecionados) {
		this.totalSelecionados = totalSelecionados;
	}
	public String getNomeElo() {
		return nomeElo;
	}
	public void setNomeElo(String nomeElo) {
		this.nomeElo = nomeElo;
	}
	public String getMesAnoInstalacaoFinal() {
		return mesAnoInstalacaoFinal;
	}
	public void setMesAnoInstalacaoFinal(String mesAnoInstalacaoFinal) {
		this.mesAnoInstalacaoFinal = mesAnoInstalacaoFinal;
	}
	public String getMesAnoInstalacaoInicial() {
		return mesAnoInstalacaoInicial;
	}
	public void setMesAnoInstalacaoInicial(String mesAnoInstalacaoInicial) {
		this.mesAnoInstalacaoInicial = mesAnoInstalacaoInicial;
	}
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}
	public String getSituacaoLigacaoAguaDescricao() {
		return situacaoLigacaoAguaDescricao;
	}
	public void setSituacaoLigacaoAguaDescricao(String situacaoLigacaoAguaDescricao) {
		this.situacaoLigacaoAguaDescricao = situacaoLigacaoAguaDescricao;
	}
	public String getAnormalidadeHidrometro() {
		return anormalidadeHidrometro;
	}
	public void setAnormalidadeHidrometro(String anormalidadeHidrometro) {
		this.anormalidadeHidrometro = anormalidadeHidrometro;
	}
	public String getLocalInstalacaoDescricao() {
		return localInstalacaoDescricao;
	}
	public void setLocalInstalacaoDescricao(String localInstalacaoDescricao) {
		this.localInstalacaoDescricao = localInstalacaoDescricao;
	}
	public String getDesLocalidade() {
		return desLocalidade;
	}
	public void setDesLocalidade(String desLocalidade) {
		this.desLocalidade = desLocalidade;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getDesSetorComercial() {
		return desSetorComercial;
	}
	public void setDesSetorComercial(String desSetorComercial) {
		this.desSetorComercial = desSetorComercial;
	}
	public String getIdSetorComercial() {
		return idSetorComercial;
	}
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	
	
	
}