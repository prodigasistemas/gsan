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

package gcom.arrecadacao;

/**TODO:COSANPA
 * @author Adriana Muniz
 * @created 10 de dezembro de 2012
 * 
 * Classe Helper para guardar os parametros de consulta dos Dados Diarios
 * de Arrecadacao e os tipos de agrupamentos para cada tela
 * 
**/
public class FiltroConsultarDadosDiariosArrecadacaoAuxiliar {

	public static enum GROUP_BY_AUX {
		GERENCIA_REGIONAL, UNIDADE_NEGOCIO, ELO, LOCALIDADE, 
		ARRECADADOR, ANO_MES, FORMA_ARRECADACAO, CATEGORIA, 
		PERFIL, TIPO_DOCUMENTO_AGREGADOR, TIPO_DOCUMENTO, DATA;
	} 
	 
	private GROUP_BY_AUX agrupamento = GROUP_BY_AUX.DATA;
	
	private String anoMesArrecadacao;
	
	private String idGerenciaRegional;
	
	private String idUnidadeNegocio;
	
	private String idElo;
	
	private String idLocalidade;
	
	private String idArrecadador;
	
	private String idFormaArrecadacao;
	
	private String idsDocumentoTipoAgregador[];
	
	private String idDocumentoTipo;
	
	private boolean isRelatorioValoresDiariosAnalitico;
	
	public GROUP_BY_AUX getAgrupamento() {
		return agrupamento;
	}

	public void setAgrupamento(GROUP_BY_AUX agrupamento) {
		this.agrupamento = agrupamento;
	}

	public String getAnoMesArrecadacao() {
		return anoMesArrecadacao;
	}

	public void setAnoMesArrecadacao(String anoMesArrecadacao) {
		this.anoMesArrecadacao = anoMesArrecadacao;
	}

	public String getIdArrecadador() {
		return idArrecadador;
	}

	public void setIdArrecadador(String idArrecadador) {
		this.idArrecadador = idArrecadador;
	}

	public String getIdElo() {
		return idElo;
	}

	public void setIdElo(String idElo) {
		this.idElo = idElo;
	}

	public String getIdFormaArrecadacao() {
		return idFormaArrecadacao;
	}

	public void setIdFormaArrecadacao(String idFormaArrecadacao) {
		this.idFormaArrecadacao = idFormaArrecadacao;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdDocumentoTipo() {
		return idDocumentoTipo;
	}

	public void setIdDocumentoTipo(String idDocumentoTipo) {
		this.idDocumentoTipo = idDocumentoTipo;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo idsDocumentoTipoAgregador.
	 */
	public String[] getIdsDocumentoTipoAgregador() {
		return idsDocumentoTipoAgregador;
	}

	/**
	 * @param idsDocumentoTipoAgregador O idsDocumentoTipoAgregador a ser setado.
	 */
	public void setIdsDocumentoTipoAgregador(String[] idsDocumentoTipoAgregador) {
		this.idsDocumentoTipoAgregador = idsDocumentoTipoAgregador;
	}

	public boolean isRelatorioValoresDiariosAnalitico() {
		return isRelatorioValoresDiariosAnalitico;
	}

	public void setRelatorioValoresDiariosAnalitico(
			boolean isRelatorioValoresDiariosAnalitico) {
		this.isRelatorioValoresDiariosAnalitico = isRelatorioValoresDiariosAnalitico;
	}

	@Override
	public FiltroConsultarDadosDiariosArrecadacaoAuxiliar clone() {
		FiltroConsultarDadosDiariosArrecadacaoAuxiliar filtro = new FiltroConsultarDadosDiariosArrecadacaoAuxiliar();
		filtro.setAgrupamento(getAgrupamento());
		filtro.setAnoMesArrecadacao(getAnoMesArrecadacao());
		filtro.setIdArrecadador(getIdArrecadador());
		filtro.setIdDocumentoTipo(getIdDocumentoTipo());
		filtro.setIdElo(getIdElo());
		filtro.setIdFormaArrecadacao(getIdFormaArrecadacao());
		filtro.setIdGerenciaRegional(getIdGerenciaRegional());
		filtro.setIdLocalidade(getIdLocalidade());
		filtro.setIdsDocumentoTipoAgregador(getIdsDocumentoTipoAgregador());
		filtro.setIdUnidadeNegocio(getIdUnidadeNegocio());
		return filtro;
	}
	
}