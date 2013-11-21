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
package gcom.gui.relatorio.cadastro.imovel;


import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaSituacao;
import gcom.util.ConstantesSistema;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC00725] Gerar Relatório de Imóveis por Situação da Ligação de Água
 * 
 * @author Rafael Pinto
 *
 * @date 28/11/2007
 */

public class GerarRelatorioImoveisFaturasAtrasoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String unidadeNegocio;
	private String gerenciaRegional;
	
	private String localidadeInicial;
	private String nomeLocalidadeInicial;
	
	private String setorComercialInicial;
	private String nomeSetorComercialInicial;
	
	private String rotaInicial;
	private String sequencialRotaInicial;

	private String localidadeFinal;
	private String nomeLocalidadeFinal;
	
	private String setorComercialFinal;
	private String nomeSetorComercialFinal;
	
	private String rotaFinal;
	private String sequencialRotaFinal;
	
	private String[] situacaoLigacaoAgua;	
	private String[] categorias;
	private String[] perfilImovel;
	
	private String quantidadeFaturasAtrasoInicial;
	private String quantidadeFaturasAtrasoFinal;	
	
	private String referenciaFaturasAtrasoInicial;
	private String referenciaFaturasAtrasoFinal;
	
	private String valorFaturasAtrasoInicial;
	private String valorFaturasAtrasoFinal;
	
	private String[] esferaPoder;
	private String tipo;
	private String hidrometro;
	
	private String criterioFiltro;
	
	private String codigoClienteSuperior;	
	private String nomeClienteSuperior;
	private String codigoCliente;
	private String nomeCliente;
	private String tipoRelacao;
	private String responsavel;
	private String situacaoCobranca;

	
	private Collection<ClienteRelacaoTipo> colecaoTiposRelacoes;
	private Collection<CobrancaSituacao> colecaoSituacoesCobranca;
	private Collection<GerenciaRegional> colecaoGerenciasRegionais;
	private Collection<UnidadeNegocio> colecaoUnidadesNegocios;
	private Collection<LigacaoAguaSituacao> colecaoSituacoesLigacaoAgua;
	private Collection<Categoria> colecaoCategorias;
	private Collection<ImovelPerfil> colecaoPerfisImovel;
	private Collection<EsferaPoder> colecaoEsferasPoder;

	
	public void reset(){
		
		this.localidadeInicial = null;
		this.nomeLocalidadeInicial = null;

		this.setorComercialInicial = null;
		this.nomeSetorComercialInicial = null;
		
		this.rotaInicial = null;
		this.sequencialRotaInicial = null;

		this.localidadeFinal = null;
		this.nomeLocalidadeFinal = null;

		this.setorComercialFinal = null;
		this.nomeSetorComercialFinal = null;

		this.rotaFinal = null;
		this.sequencialRotaFinal = null;
		
		this.gerenciaRegional = null;
		this.unidadeNegocio = null;
		
		this.esferaPoder = null;
		this.tipo = "A";
		this.hidrometro = "0";
		
		this.criterioFiltro = "1";		
		this.codigoClienteSuperior = "";
		this.codigoCliente = "";
		this.nomeCliente = "";
		this.nomeClienteSuperior = "";
		this.tipoRelacao = String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO);
		this.responsavel = "1";
		this.situacaoCobranca = "";
		
		this.colecaoSituacoesCobranca = new ArrayList<CobrancaSituacao>();
		this.colecaoCategorias = new ArrayList<Categoria>();
		this.colecaoPerfisImovel = new ArrayList<ImovelPerfil>();
		this.colecaoEsferasPoder = new ArrayList<EsferaPoder>();
		this.colecaoGerenciasRegionais = new ArrayList<GerenciaRegional>();
		this.colecaoSituacoesLigacaoAgua = new ArrayList<LigacaoAguaSituacao>();
		this.colecaoUnidadesNegocios = new ArrayList<UnidadeNegocio>();

	}
	
	public String getHidrometro() {
		return hidrometro;
	}

	public void setHidrometro(String hidrometro) {
		this.hidrometro = hidrometro;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String[] getEsferaPoder() {
		return esferaPoder;
	}

	public void setEsferaPoder(String[] esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}


	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(String localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}

	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}

	public String getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(String localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}

	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
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

	public String getSetorComercialFinal() {
		return setorComercialFinal;
	}

	public void setSetorComercialFinal(String setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}

	public String getSetorComercialInicial() {
		return setorComercialInicial;
	}

	public void setSetorComercialInicial(String setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}

	public String getRotaFinal() {
		return rotaFinal;
	}

	public void setRotaFinal(String rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	public String getRotaInicial() {
		return rotaInicial;
	}

	public void setRotaInicial(String rotaInicial) {
		this.rotaInicial = rotaInicial;
	}

	public String getSequencialRotaFinal() {
		return sequencialRotaFinal;
	}

	public void setSequencialRotaFinal(String sequencialRotaFinal) {
		this.sequencialRotaFinal = sequencialRotaFinal;
	}

	public String getSequencialRotaInicial() {
		return sequencialRotaInicial;
	}

	public void setSequencialRotaInicial(String sequencialRotaInicial) {
		this.sequencialRotaInicial = sequencialRotaInicial;
	}

	public String[] getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	public void setSituacaoLigacaoAgua(String[] situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}
	
	public String[] getCategorias() {
	
		return categorias;
	}

	
	public void setCategorias(String[] categorias) {
	
		this.categorias = categorias;
	}

	
	public String[] getPerfilImovel() {
		return perfilImovel;
	}


	public void setPerfilImovel(String[] perfilImovel) {
		this.perfilImovel = perfilImovel;
	}


	public String getQuantidadeFaturasAtrasoInicial() {
	
		return quantidadeFaturasAtrasoInicial;
	}

	
	public void setQuantidadeFaturasAtrasoInicial(
			String quantidadeFaturasAtrasoInicial) {
	
		this.quantidadeFaturasAtrasoInicial = quantidadeFaturasAtrasoInicial;
	}

	
	public String getQuantidadeFaturasAtrasoFinal() {
	
		return quantidadeFaturasAtrasoFinal;
	}

	
	public void setQuantidadeFaturasAtrasoFinal(String quantidadeFaturasAtrasoFinal) {
	
		this.quantidadeFaturasAtrasoFinal = quantidadeFaturasAtrasoFinal;
	}

	
	public String getReferenciaFaturasAtrasoFinal() {
	
		return referenciaFaturasAtrasoFinal;
	}

	
	public void setReferenciaFaturasAtrasoFinal(String referenciaFaturasAtrasoFinal) {
	
		this.referenciaFaturasAtrasoFinal = referenciaFaturasAtrasoFinal;
	}

	
	public String getReferenciaFaturasAtrasoInicial() {
	
		return referenciaFaturasAtrasoInicial;
	}

	
	public void setReferenciaFaturasAtrasoInicial(
			String referenciaFaturasAtrasoInicial) {
	
		this.referenciaFaturasAtrasoInicial = referenciaFaturasAtrasoInicial;
	}

	
	public String getValorFaturasAtrasoFinal() {
	
		return valorFaturasAtrasoFinal;
	}

	
	public void setValorFaturasAtrasoFinal(String valorFaturasAtrasoFinal) {
	
		this.valorFaturasAtrasoFinal = valorFaturasAtrasoFinal;
	}

	
	public String getValorFaturasAtrasoInicial() {
	
		return valorFaturasAtrasoInicial;
	}
	
	public void setValorFaturasAtrasoInicial(String valorFaturasAtrasoInicial) {
	
		this.valorFaturasAtrasoInicial = valorFaturasAtrasoInicial;
	}

	public String getCriterioFiltro() {
		return criterioFiltro;
	}

	public void setCriterioFiltro(String criterioFiltro) {
		this.criterioFiltro = criterioFiltro;
	}

	public String getCodigoClienteSuperior() {
		return codigoClienteSuperior;
	}

	public void setCodigoClienteSuperior(String codigoClienteSuperior) {
		this.codigoClienteSuperior = codigoClienteSuperior;
	}

	
	public String getNomeClienteSuperior() {
		return nomeClienteSuperior;
	}

	public void setNomeClienteSuperior(String nomeClienteSuperior) {
		this.nomeClienteSuperior = nomeClienteSuperior;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getTipoRelacao() {
		return tipoRelacao;
	}

	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public Collection<CobrancaSituacao> getColecaoSituacoesCobranca() {
		return colecaoSituacoesCobranca;
	}

	public void setColecaoSituacoesCobranca(Collection<CobrancaSituacao> situacoesCobranca) {
		this.colecaoSituacoesCobranca = situacoesCobranca;
	}

	public String getSituacaoCobranca() {
		return situacaoCobranca;
	}

	public void setSituacaoCobranca(String situacaoCobranca) {
		this.situacaoCobranca = situacaoCobranca;
	}

	public Collection<GerenciaRegional> getColecaoGerenciasRegionais() {
		return colecaoGerenciasRegionais;
	}

	public void setColecaoGerenciasRegionais(
			Collection<GerenciaRegional> colecaoGerenciasRegionais) {
		this.colecaoGerenciasRegionais = colecaoGerenciasRegionais;
	}

	public Collection<UnidadeNegocio> getColecaoUnidadesNegocios() {
		return colecaoUnidadesNegocios;
	}

	public void setColecaoUnidadesNegocios(
			Collection<UnidadeNegocio> colecaoUnidadesNegocios) {
		this.colecaoUnidadesNegocios = colecaoUnidadesNegocios;
	}

	public Collection<LigacaoAguaSituacao> getColecaoSituacoesLigacaoAgua() {
		return colecaoSituacoesLigacaoAgua;
	}

	public void setColecaoSituacoesLigacaoAgua(
			Collection<LigacaoAguaSituacao> colecaoLigacoesAguaSituacao) {
		this.colecaoSituacoesLigacaoAgua = colecaoLigacoesAguaSituacao;
	}

	public Collection<Categoria> getColecaoCategorias() {
		return colecaoCategorias;
	}

	public void setColecaoCategorias(Collection<Categoria> colecaoCategorias) {
		this.colecaoCategorias = colecaoCategorias;
	}

	public Collection<ImovelPerfil> getColecaoPerfisImovel() {
		return colecaoPerfisImovel;
	}

	public void setColecaoPerfisImovel(Collection<ImovelPerfil> colecaoPerfisImovel) {
		this.colecaoPerfisImovel = colecaoPerfisImovel;
	}



	public Collection<EsferaPoder> getColecaoEsferasPoder() {
		return colecaoEsferasPoder;
	}

	public void setColecaoEsferasPoder(Collection<EsferaPoder> colecaoEsferaPoder) {
		this.colecaoEsferasPoder = colecaoEsferaPoder;
	}

	public Collection<ClienteRelacaoTipo> getColecaoTiposRelacoes() {
		return colecaoTiposRelacoes;
	}

	public void setColecaoTiposRelacoes(Collection<ClienteRelacaoTipo> colecaoTiposRelacoes) {
		this.colecaoTiposRelacoes = colecaoTiposRelacoes;
	}
}