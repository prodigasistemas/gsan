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
package gcom.gui.gerencial;

import org.apache.struts.action.*;

import javax.servlet.http.*;

/**
 * Esta classe tem por finalidade gerar o formulário que receberá os parâmetros para informar
 * os dados para geração de relatório/consulta
 *
 * @author Raphael Rossiter
 * @date 17/05/2006
 */
public class InformarDadosGeracaoRelatorioConsultaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String mesAnoFaturamento;
	private String opcaoTotalizacao;
	private String grupoFaturamento;
	private String grupoCobranca;
	private String gerencialRegional;
	private String eloPolo;
	private String descricaoEloPolo;
	private String localidade;
	private String descricaoLocalidade;
	private String idSetorComercial;
	private String setorComercial;
	private String descricaoSetorComercial;
	private String quadra;
	private String descricaoQuadra;
	private String[] perfilImovel;
	private String[] situacaoLigacaoAgua;
	private String[] situacaoLigacaoEsgoto;
	private String[] categoria;
	private String[] esferaPoder;
	private String tipoAnaliseFaturamento;
	private String perfilImovel2;
	private String situacaoLigacaoAgua2;
	private String situacaoLigacaoEsgoto2;
	private String categoria2;
	private String esferaPoder2;	
	private String unidadeNegocio;
	private String municipio;
	private String descricaoMunicipio;
	private String idRota;
	private String codigoRota;
	
	/**
	 * @return Retorna o campo categoria2.
	 */
	public String getCategoria2() {
		return categoria2;
	}
	/**
	 * @param categoria2 O categoria2 a ser setado.
	 */
	public void setCategoria2(String categoria2) {
		this.categoria2 = categoria2;
	}
	/**
	 * @return Retorna o campo esferaPoder2.
	 */
	public String getEsferaPoder2() {
		return esferaPoder2;
	}
	/**
	 * @param esferaPoder2 O esferaPoder2 a ser setado.
	 */
	public void setEsferaPoder2(String esferaPoder2) {
		this.esferaPoder2 = esferaPoder2;
	}
	/**
	 * @return Retorna o campo perfilImovel2.
	 */
	public String getPerfilImovel2() {
		return perfilImovel2;
	}
	/**
	 * @param perfilImovel2 O perfilImovel2 a ser setado.
	 */
	public void setPerfilImovel2(String perfilImovel2) {
		this.perfilImovel2 = perfilImovel2;
	}
	/**
	 * @return Retorna o campo situacaoLigacaoAgua2.
	 */
	public String getSituacaoLigacaoAgua2() {
		return situacaoLigacaoAgua2;
	}
	/**
	 * @param situacaoLigacaoAgua2 O situacaoLigacaoAgua2 a ser setado.
	 */
	public void setSituacaoLigacaoAgua2(String situacaoLigacaoAgua2) {
		this.situacaoLigacaoAgua2 = situacaoLigacaoAgua2;
	}
	/**
	 * @return Retorna o campo situacaoLigacaoEsgoto2.
	 */
	public String getSituacaoLigacaoEsgoto2() {
		return situacaoLigacaoEsgoto2;
	}
	/**
	 * @param situacaoLigacaoEsgoto2 O situacaoLigacaoEsgoto2 a ser setado.
	 */
	public void setSituacaoLigacaoEsgoto2(String situacaoLigacaoEsgoto2) {
		this.situacaoLigacaoEsgoto2 = situacaoLigacaoEsgoto2;
	}
	public String getTipoAnaliseFaturamento() {
		return tipoAnaliseFaturamento;
	}
	public void setTipoAnaliseFaturamento(String tipoAnaliseFaturamento) {
		this.tipoAnaliseFaturamento = tipoAnaliseFaturamento;
	}
	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	    /**@todo: finish this method, this is just the skeleton.*/
	    return null;
	}
	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	}
	
	public String[] getCategoria() {
		return categoria;
	}
	public void setCategoria(String[] categoria) {
		this.categoria = categoria;
	}
	public String getDescricaoEloPolo() {
		return descricaoEloPolo;
	}
	public void setDescricaoEloPolo(String descricaoEloPolo) {
		this.descricaoEloPolo = descricaoEloPolo;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public String getDescricaoQuadra() {
		return descricaoQuadra;
	}
	public void setDescricaoQuadra(String descricaoQuadra) {
		this.descricaoQuadra = descricaoQuadra;
	}
	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}
	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}
	public String getEloPolo() {
		return eloPolo;
	}
	public void setEloPolo(String eloPolo) {
		this.eloPolo = eloPolo;
	}
	public String[] getEsferaPoder() {
		return esferaPoder;
	}
	public void setEsferaPoder(String[] esferaPoder) {
		this.esferaPoder = esferaPoder;
	}
	public String getGerencialRegional() {
		return gerencialRegional;
	}
	public void setGerencialRegional(String gerencialRegional) {
		this.gerencialRegional = gerencialRegional;
	}
	public String getGrupoFaturamento() {
		return grupoFaturamento;
	}
	public void setGrupoFaturamento(String grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getMesAnoFaturamento() {
		return mesAnoFaturamento;
	}
	public void setMesAnoFaturamento(String mesAnoFaturamento) {
		this.mesAnoFaturamento = mesAnoFaturamento;
	}
	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}
	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}
	public String[] getPerfilImovel() {
		return perfilImovel;
	}
	public void setPerfilImovel(String[] perfilImovel) {
		this.perfilImovel = perfilImovel;
	}
	public String getQuadra() {
		return quadra;
	}
	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}
	public String getSetorComercial() {
		return setorComercial;
	}
	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}
	public String[] getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	public void setSituacaoLigacaoAgua(String[] situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}
	public String[] getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}
	public void setSituacaoLigacaoEsgoto(String[] situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}
	public String getIdSetorComercial() {
		return idSetorComercial;
	}
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	/**
	 * @return Retorna o campo grupoCobranaca.
	 */
	public String getGrupoCobranca() {
		return grupoCobranca;
	}
	/**
	 * @param grupoCobranaca O grupoCobranaca a ser setado.
	 */
	public void setGrupoCobranca(String grupoCobranca) {
		this.grupoCobranca = grupoCobranca;
	}
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}
	public void setDescricaoMunicipio(String descricaoMunicipio) {
		this.descricaoMunicipio = descricaoMunicipio;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getIdRota() {
		return idRota;
	}
	public void setIdRota(String idRota) {
		this.idRota = idRota;
	}
	public String getCodigoRota() {
		return codigoRota;
	}
	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}
}