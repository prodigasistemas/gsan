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
package gcom.gui.cobranca.spcserasa;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade gerar o formulário que receberá os parâmetros para informar
 * os dados para geração de relatório/consulta
 *
 * @author Marcio Roberto
 * @date 26/02/2008
 */ 
public class InformarDadosParaConsultaNegativacaoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idNegativador;
	
	private String nomeNegativador;
	
	private String periodoEnvioNegativacaoInicio;
	
	private String periodoEnvioNegativacaoFim;
	
	private String idNegativacaoComando;
	
	private String tituloComando;
	
	private String idEloPolo;

	private String descricaoEloPolo;
	
	private String idLocalidade;
	
	private String descricaoLocalidade;

	private String idSetorComercial;
	
	private String descricaoSetorComercial;
	
	private String idQuadra;
	
	private String descricaoQuadra;
	
	private String idGrupoCobranca;
	
	private String idGerenciaRegional;
	
	private String idUnidadeNegocio;
	
	private String idImovelPerfil;
	
	private String idCategoria;
	
	private String idTipoCliente;
	
	private String idEsferaPoder;
	
	private String okEloPolo;
	
	private String okLocalidade;
	
	private String okSetorComercial;
	
	private String okQuadra;
	
    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    	
    	this.idNegativador = "";
    	this.periodoEnvioNegativacaoInicio = "";
    	this.periodoEnvioNegativacaoFim = "";
    	this.tituloComando = "";
    	this.idGrupoCobranca = "";
    	this.idGerenciaRegional = "";
    	this.idUnidadeNegocio = "";
    	this.idEloPolo = "";
    	this.descricaoEloPolo = "";
    	this.idLocalidade = "";
    	this.descricaoLocalidade = "";
    	this.idSetorComercial = "";
    	this.descricaoSetorComercial = "";
    	this.idQuadra = "";
    	this.descricaoQuadra = "";
    	this.idTipoCliente = "";
    	this.idImovelPerfil = "";
    	this.idCategoria = "";
    	this.idNegativacaoComando = "";
    	this.idEsferaPoder = "";
    	this.okEloPolo = "";
    	this.okLocalidade = "";
    	this.okSetorComercial = "";
    	this.okQuadra = "";
    }

	/**
	 * @return Retorna o campo descricaoEloPolo.
	 */
	public String getDescricaoEloPolo() {
		return descricaoEloPolo;
	}

	/**
	 * @param descricaoEloPolo O descricaoEloPolo a ser setado.
	 */
	public void setDescricaoEloPolo(String descricaoEloPolo) {
		this.descricaoEloPolo = descricaoEloPolo;
	}

	/**
	 * @return Retorna o campo descricaoLocalidade.
	 */
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	/**
	 * @param descricaoLocalidade O descricaoLocalidade a ser setado.
	 */
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	/**
	 * @return Retorna o campo descricaoQuadra.
	 */
	public String getDescricaoQuadra() {
		return descricaoQuadra;
	}

	/**
	 * @param descricaoQuadra O descricaoQuadra a ser setado.
	 */
	public void setDescricaoQuadra(String descricaoQuadra) {
		this.descricaoQuadra = descricaoQuadra;
	}

	/**
	 * @return Retorna o campo descricaoSetorComercial.
	 */
	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}

	/**
	 * @param descricaoSetorComercial O descricaoSetorComercial a ser setado.
	 */
	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}

	/**
	 * @return Retorna o campo idEloPolo.
	 */
	public String getIdEloPolo() {
		return idEloPolo;
	}

	/**
	 * @param idEloPolo O idEloPolo a ser setado.
	 */
	public void setIdEloPolo(String idEloPolo) {
		this.idEloPolo = idEloPolo;
	}

	/**
	 * @return Retorna o campo idGerenciaRegional.
	 */
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	/**
	 * @param idGerenciaRegional O idGerenciaRegional a ser setado.
	 */
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	/**
	 * @return Retorna o campo idGrupoCobranca.
	 */
	public String getIdGrupoCobranca() {
		return idGrupoCobranca;
	}

	/**
	 * @param idGrupoCobranca O idGrupoCobranca a ser setado.
	 */
	public void setIdGrupoCobranca(String idGrupoCobranca) {
		this.idGrupoCobranca = idGrupoCobranca;
	}

	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Retorna o campo idNegativador.
	 */
	public String getIdNegativador() {
		return idNegativador;
	}

	/**
	 * @param idNegativador O idNegativador a ser setado.
	 */
	public void setIdNegativador(String idNegativador) {
		this.idNegativador = idNegativador;
	}

	/**
	 * @return Retorna o campo idQuadra.
	 */
	public String getIdQuadra() {
		return idQuadra;
	}

	/**
	 * @param idQuadra O idQuadra a ser setado.
	 */
	public void setIdQuadra(String idQuadra) {
		this.idQuadra = idQuadra;
	}

	/**
	 * @return Retorna o campo idSetorComercial.
	 */
	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	/**
	 * @param idSetorComercial O idSetorComercial a ser setado.
	 */
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	/**
	 * @return Retorna o campo idTipoCliente.
	 */
	public String getIdTipoCliente() {
		return idTipoCliente;
	}

	/**
	 * @param idTipoCliente O idTipoCliente a ser setado.
	 */
	public void setIdTipoCliente(String idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
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
	 * @return Retorna o campo periodoEnvioNegativacaoFim.
	 */
	public String getPeriodoEnvioNegativacaoFim() {
		return periodoEnvioNegativacaoFim;
	}

	/**
	 * @param periodoEnvioNegativacaoFim O periodoEnvioNegativacaoFim a ser setado.
	 */
	public void setPeriodoEnvioNegativacaoFim(String periodoEnvioNegativacaoFim) {
		this.periodoEnvioNegativacaoFim = periodoEnvioNegativacaoFim;
	}

	/**
	 * @return Retorna o campo periodoEnvioNegativacaoInicio.
	 */
	public String getPeriodoEnvioNegativacaoInicio() {
		return periodoEnvioNegativacaoInicio;
	}

	/**
	 * @param periodoEnvioNegativacaoInicio O periodoEnvioNegativacaoInicio a ser setado.
	 */
	public void setPeriodoEnvioNegativacaoInicio(
			String periodoEnvioNegativacaoInicio) {
		this.periodoEnvioNegativacaoInicio = periodoEnvioNegativacaoInicio;
	}

	/**
	 * @return Retorna o campo tituloComando.
	 */
	public String getTituloComando() {
		return tituloComando;
	}

	/**
	 * @param tituloComando O tituloComando a ser setado.
	 */
	public void setTituloComando(String tituloComando) {
		this.tituloComando = tituloComando;
	}

	/**
	 * @return Retorna o campo idCategoria.
	 */
	public String getIdCategoria() {
		return idCategoria;
	}

	/**
	 * @param idCategoria O idCategoria a ser setado.
	 */
	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * @return Retorna o campo idEsferaPoder.
	 */
	public String getIdEsferaPoder() {
		return idEsferaPoder;
	}

	/**
	 * @param idEsferaPoder O idEsferaPoder a ser setado.
	 */
	public void setIdEsferaPoder(String idEsferaPoder) {
		this.idEsferaPoder = idEsferaPoder;
	}

	/**
	 * @return Retorna o campo idImovelPerfil.
	 */
	public String getIdImovelPerfil() {
		return idImovelPerfil;
	}

	/**
	 * @param idImovelPerfil O idImovelPerfil a ser setado.
	 */
	public void setIdImovelPerfil(String idImovelPerfil) {
		this.idImovelPerfil = idImovelPerfil;
	}

	/**
	 * @return Retorna o campo idNegativacaoComando.
	 */
	public String getIdNegativacaoComando() {
		return idNegativacaoComando;
	}

	/**
	 * @param idNegativacaoComando O idNegativacaoComando a ser setado.
	 */
	public void setIdNegativacaoComando(String idNegativacaoComando) {
		this.idNegativacaoComando = idNegativacaoComando;
	}

	/**
	 * @return Retorna o campo okEloPolo.
	 */
	public String getOkEloPolo() {
		return okEloPolo;
	}

	/**
	 * @param okEloPolo O okEloPolo a ser setado.
	 */
	public void setOkEloPolo(String okEloPolo) {
		this.okEloPolo = okEloPolo;
	}

	/**
	 * @return Retorna o campo okLocalidade.
	 */
	public String getOkLocalidade() {
		return okLocalidade;
	}

	/**
	 * @param okLocalidade O okLocalidade a ser setado.
	 */
	public void setOkLocalidade(String okLocalidade) {
		this.okLocalidade = okLocalidade;
	}

	/**
	 * @return Retorna o campo okQuadra.
	 */
	public String getOkQuadra() {
		return okQuadra;
	}

	/**
	 * @param okQuadra O okQuadra a ser setado.
	 */
	public void setOkQuadra(String okQuadra) {
		this.okQuadra = okQuadra;
	}

	/**
	 * @return Retorna o campo okSetorComercial.
	 */
	public String getOkSetorComercial() {
		return okSetorComercial;
	}

	/**
	 * @param okSetorComercial O okSetorComercial a ser setado.
	 */
	public void setOkSetorComercial(String okSetorComercial) {
		this.okSetorComercial = okSetorComercial;
	}

	public String getNomeNegativador() {
		return nomeNegativador;
	}

	public void setNomeNegativador(String nomeNegativador) {
		this.nomeNegativador = nomeNegativador;
	}
}
