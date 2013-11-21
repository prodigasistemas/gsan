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
package gcom.relatorio.cobranca.spcserasa;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Vivianne Sousa
 * @created 11/05/2009
 * @version 1.0
 */

public class RelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean implements RelatorioBean {
	  
	private String negativador;
	private String periodoEnvioNegativacao;
	private String tituloComando;
	private String eloPolo;
	private String localidade;
	private String codigoSetorComercial;
	private String numeroQuadra;
	private String grupoCobranca;
	private String gerenciaRegional;
	private String unidadeNegocio;
	private String imovelPerfil;
	private String categoria;
	private String tipoCliente;
	private String esferaPoder;
	
	//*************************************************************
	// RM3755
	// Autor: Ivan Sergio
	// Data: 10/01/2011
	//*************************************************************
	private String ligacaoAguaSituacao;
	private String ligacaoEsgotoSituacao;
	//*************************************************************

	public RelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean(
			String negativador,
			String periodoEnvioNegativacao,
			String tituloComando,
			String eloPolo,
			String localidade,
			String codigoSetorComercial,
			String numeroQuadra,
			String grupoCobranca,
			String gerenciaRegional,
			String unidadeNegocio,
			String imovelPerfil,
			String categoria,
			String tipoCliente,
			String esferaPoder

			) {
		super();
		// TODO Auto-generated constructor stub

		this.negativador = negativador;
		this.periodoEnvioNegativacao = periodoEnvioNegativacao;
		this.tituloComando = tituloComando;
		this.eloPolo = eloPolo;
		this.localidade = localidade;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.grupoCobranca = grupoCobranca;
		this.gerenciaRegional = gerenciaRegional;
		this.unidadeNegocio = unidadeNegocio;
		this.imovelPerfil = imovelPerfil;
		this.categoria = categoria;
		this.tipoCliente = tipoCliente;
		this.esferaPoder = esferaPoder; 
	}

	public RelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean(){
		super();
	}
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getEloPolo() {
		return eloPolo;
	}

	public void setEloPolo(String eloPolo) {
		this.eloPolo = eloPolo;
	}

	public String getEsferaPoder() {
		return esferaPoder;
	}

	public void setEsferaPoder(String esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getGrupoCobranca() {
		return grupoCobranca;
	}

	public void setGrupoCobranca(String grupoCobranca) {
		this.grupoCobranca = grupoCobranca;
	}

	public String getImovelPerfil() {
		return imovelPerfil;
	}

	public void setImovelPerfil(String imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getNegativador() {
		return negativador;
	}

	public void setNegativador(String negativador) {
		this.negativador = negativador;
	}

	public String getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public String getPeriodoEnvioNegativacao() {
		return periodoEnvioNegativacao;
	}

	public void setPeriodoEnvioNegativacao(String periodoEnvioNegativacao) {
		this.periodoEnvioNegativacao = periodoEnvioNegativacao;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getTituloComando() {
		return tituloComando;
	}

	public void setTituloComando(String tituloComando) {
		this.tituloComando = tituloComando;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(String ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public String getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(String ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	
	
}
