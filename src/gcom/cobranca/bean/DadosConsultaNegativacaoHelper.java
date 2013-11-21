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
* Thiago Vieira de Melo
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
package gcom.cobranca.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * [UC 0653] Pesquisar Comando Negativação
 * 
 * @author Thiago Vieira
 * @date 29/01/2008
 */

public class DadosConsultaNegativacaoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idNegativador;
	
	private String nomeNegativador;
	
	private Date periodoEnvioNegativacaoInicio;
	
	private Date periodoEnvioNegativacaoFim;
	
	private Date periodoExclusaoNegativacaoInicio;
	
	private Date periodoExclusaoNegativacaoFim;
	
	private Integer idNegativadorExclusaoMotivo;
	
	private String tituloComando;
	
	private Integer idNegativacaoComando;
	
	private Integer idGrupoCobranca;
	
	private Integer idGerenciaRegional;
	
	private Integer idUnidadeNegocio;
	
	private Integer idEloPolo;
	
	private String descricaoEloPolo;
	
	private Integer idLocalidade;
	
	private String descricaoLocalidade;

	private Integer idSetorComercial;
	
	private String descricaoSetorComercial;
	
	private Integer idQuadra;
	
	private String descricaoQuadra;
	
	private Integer idTipoCliente;
	
	private Integer idImovelPerfil;
	
	private Integer idCategoria;
	
	private Integer idEsferaPoder;
	
	private Collection colecaoImovelPerfil;
	private Collection colecaoCategoria;
	private Collection colecaoCobrancaGrupo;
	private Collection colecaoGerenciaRegional;
	private Collection colecaoUnidadeNegocio;
	private Collection colecaoClienteTipo;
	private Collection colecaoEsferaPoder;
	
	private Integer numeroExecucaoResumoNegativacao;
	
	private Collection colecaoMotivoRejeicao;
	private Short indicadorApenasNegativacoesRejeitadas;
	
	//********************************************************
	// RM3755
	// Autor: Ivan Sergio
	// Data: 12/01/2011
	//********************************************************
	private Collection colecaoNegativador;
	private Collection colecaoLigacaoAguaSituacao;
	private Collection colecaoLigacaoEsgotoSituacao;
	//********************************************************
	
	//********************************************************
	// RM4036
	// Autor: Ivan Sergio
	// Data: 04/02/2011
	//********************************************************
	private String tipoRelatorio = "";
	//********************************************************
	
	public Short getIndicadorApenasNegativacoesRejeitadas() {
		return indicadorApenasNegativacoesRejeitadas;
	}

	public void setIndicadorApenasNegativacoesRejeitadas(
			Short indicadorApenasNegativacoesRejeitadas) {
		this.indicadorApenasNegativacoesRejeitadas = indicadorApenasNegativacoesRejeitadas;
	}

	public DadosConsultaNegativacaoHelper() {
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
	 * @return Retorna o campo idCategoria.
	 */
	public Integer getIdCategoria() {
		return idCategoria;
	}

	/**
	 * @param idCategoria O idCategoria a ser setado.
	 */
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}



	/**
	 * @return Retorna o campo idNegativacaoComando.
	 */
	public Integer getIdNegativacaoComando() {
		return idNegativacaoComando;
	}

	/**
	 * @param idNegativacaoComando O idNegativacaoComando a ser setado.
	 */
	public void setIdNegativacaoComando(Integer idNegativacaoComando) {
		this.idNegativacaoComando = idNegativacaoComando;
	}

	/**
	 * @return Retorna o campo idEloPolo.
	 */
	public Integer getIdEloPolo() {
		return idEloPolo;
	}

	/**
	 * @param idEloPolo O idEloPolo a ser setado.
	 */
	public void setIdEloPolo(Integer idEloPolo) {
		this.idEloPolo = idEloPolo;
	}

	/**
	 * @return Retorna o campo idEsferaPoder.
	 */
	public Integer getIdEsferaPoder() {
		return idEsferaPoder;
	}

	/**
	 * @param idEsferaPoder O idEsferaPoder a ser setado.
	 */
	public void setIdEsferaPoder(Integer idEsferaPoder) {
		this.idEsferaPoder = idEsferaPoder;
	}

	/**
	 * @return Retorna o campo idGerenciaRegional.
	 */
	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	/**
	 * @param idGerenciaRegional O idGerenciaRegional a ser setado.
	 */
	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	/**
	 * @return Retorna o campo idGrupoCobranca.
	 */
	public Integer getIdGrupoCobranca() {
		return idGrupoCobranca;
	}

	/**
	 * @param idGrupoCobranca O idGrupoCobranca a ser setado.
	 */
	public void setIdGrupoCobranca(Integer idGrupoCobranca) {
		this.idGrupoCobranca = idGrupoCobranca;
	}

	/**
	 * @return Retorna o campo idImovelPerfil.
	 */
	public Integer getIdImovelPerfil() {
		return idImovelPerfil;
	}

	/**
	 * @param idImovelPerfil O idImovelPerfil a ser setado.
	 */
	public void setIdImovelPerfil(Integer idImovelPerfil) {
		this.idImovelPerfil = idImovelPerfil;
	}

	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Retorna o campo idNegativador.
	 */
	public Integer getIdNegativador() {
		return idNegativador;
	}

	/**
	 * @param idNegativador O idNegativador a ser setado.
	 */
	public void setIdNegativador(Integer idNegativador) {
		this.idNegativador = idNegativador;
	}

	/**
	 * @return Retorna o campo idQuadra.
	 */
	public Integer getIdQuadra() {
		return idQuadra;
	}

	/**
	 * @param idQuadra O idQuadra a ser setado.
	 */
	public void setIdQuadra(Integer idQuadra) {
		this.idQuadra = idQuadra;
	}

	/**
	 * @return Retorna o campo idSetorComercial.
	 */
	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}

	/**
	 * @param idSetorComercial O idSetorComercial a ser setado.
	 */
	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	/**
	 * @return Retorna o campo idTipoCliente.
	 */
	public Integer getIdTipoCliente() {
		return idTipoCliente;
	}

	/**
	 * @param idTipoCliente O idTipoCliente a ser setado.
	 */
	public void setIdTipoCliente(Integer idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}

	/**
	 * @return Retorna o campo idUnidadeNegocio.
	 */
	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	/**
	 * @param idUnidadeNegocio O idUnidadeNegocio a ser setado.
	 */
	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo periodoEnvioNegativacaoFim.
	 */
	public Date getPeriodoEnvioNegativacaoFim() {
		return periodoEnvioNegativacaoFim;
	}

	/**
	 * @param periodoEnvioNegativacaoFim O periodoEnvioNegativacaoFim a ser setado.
	 */
	public void setPeriodoEnvioNegativacaoFim(Date periodoEnvioNegativacaoFim) {
		this.periodoEnvioNegativacaoFim = periodoEnvioNegativacaoFim;
	}

	/**
	 * @return Retorna o campo periodoEnvioNegativacaoInicio.
	 */
	public Date getPeriodoEnvioNegativacaoInicio() {
		return periodoEnvioNegativacaoInicio;
	}

	/**
	 * @param periodoEnvioNegativacaoInicio O periodoEnvioNegativacaoInicio a ser setado.
	 */
	public void setPeriodoEnvioNegativacaoInicio(Date periodoEnvioNegativacaoInicio) {
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

	public String getNomeNegativador() {
		return nomeNegativador;
	}

	public void setNomeNegativador(String nomeNegativador) {
		this.nomeNegativador = nomeNegativador;
	}

	/**
	 * @return Retorna o campo colecaoCategoria.
	 */
	public Collection getColecaoCategoria() {
		return colecaoCategoria;
	}

	/**
	 * @param colecaoCategoria O colecaoCategoria a ser setado.
	 */
	public void setColecaoCategoria(Collection colecaoCategoria) {
		this.colecaoCategoria = colecaoCategoria;
	}

	/**
	 * @return Retorna o campo colecaoCobrancaGrupo.
	 */
	public Collection getColecaoCobrancaGrupo() {
		return colecaoCobrancaGrupo;
	}

	/**
	 * @param colecaoCobrancaGrupo O colecaoCobrancaGrupo a ser setado.
	 */
	public void setColecaoCobrancaGrupo(Collection colecaoCobrancaGrupo) {
		this.colecaoCobrancaGrupo = colecaoCobrancaGrupo;
	}


	/**
	 * @return Retorna o campo colecaoEsferaPoder.
	 */
	public Collection getColecaoEsferaPoder() {
		return colecaoEsferaPoder;
	}

	/**
	 * @param colecaoEsferaPoder O colecaoEsferaPoder a ser setado.
	 */
	public void setColecaoEsferaPoder(Collection colecaoEsferaPoder) {
		this.colecaoEsferaPoder = colecaoEsferaPoder;
	}

	/**
	 * @return Retorna o campo colecaoImovelPerfil.
	 */
	public Collection getColecaoImovelPerfil() {
		return colecaoImovelPerfil;
	}

	/**
	 * @param colecaoImovelPerfil O colecaoImovelPerfil a ser setado.
	 */
	public void setColecaoImovelPerfil(Collection colecaoImovelPerfil) {
		this.colecaoImovelPerfil = colecaoImovelPerfil;
	}

	/**
	 * @return Retorna o campo colecaoGerenciaRegional.
	 */
	public Collection getColecaoGerenciaRegional() {
		return colecaoGerenciaRegional;
	}

	/**
	 * @param colecaoGerenciaRegional O colecaoGerenciaRegional a ser setado.
	 */
	public void setColecaoGerenciaRegional(Collection colecaoGerenciaRegional) {
		this.colecaoGerenciaRegional = colecaoGerenciaRegional;
	}

	/**
	 * @return Retorna o campo colecaoUnidadeNegocio.
	 */
	public Collection getColecaoUnidadeNegocio() {
		return colecaoUnidadeNegocio;
	}

	/**
	 * @param colecaoUnidadeNegocio O colecaoUnidadeNegocio a ser setado.
	 */
	public void setColecaoUnidadeNegocio(Collection colecaoUnidadeNegocio) {
		this.colecaoUnidadeNegocio = colecaoUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo colecaoClienteTipo.
	 */
	public Collection getColecaoClienteTipo() {
		return colecaoClienteTipo;
	}

	/**
	 * @param colecaoClienteTipo O colecaoClienteTipo a ser setado.
	 */
	public void setColecaoClienteTipo(Collection colecaoClienteTipo) {
		this.colecaoClienteTipo = colecaoClienteTipo;
	}

	/**
	 * @return Retorna o campo periodoExclusaoNegativacaoFim.
	 */
	public Date getPeriodoExclusaoNegativacaoFim() {
		return periodoExclusaoNegativacaoFim;
	}

	/**
	 * @param periodoExclusaoNegativacaoFim O periodoExclusaoNegativacaoFim a ser setado.
	 */
	public void setPeriodoExclusaoNegativacaoFim(Date periodoExclusaoNegativacaoFim) {
		this.periodoExclusaoNegativacaoFim = periodoExclusaoNegativacaoFim;
	}

	/**
	 * @return Retorna o campo periodoExclusaoNegativacaoInicio.
	 */
	public Date getPeriodoExclusaoNegativacaoInicio() {
		return periodoExclusaoNegativacaoInicio;
	}

	/**
	 * @param periodoExclusaoNegativacaoInicio O periodoExclusaoNegativacaoInicio a ser setado.
	 */
	public void setPeriodoExclusaoNegativacaoInicio(
			Date periodoExclusaoNegativacaoInicio) {
		this.periodoExclusaoNegativacaoInicio = periodoExclusaoNegativacaoInicio;
	}

	/**
	 * @return Retorna o campo numeroExecucaoResumoNegativacao.
	 */
	public Integer getNumeroExecucaoResumoNegativacao() {
		return numeroExecucaoResumoNegativacao;
	}

	/**
	 * @param numeroExecucaoResumoNegativacao O numeroExecucaoResumoNegativacao a ser setado.
	 */
	public void setNumeroExecucaoResumoNegativacao(
			Integer numeroExecucaoResumoNegativacao) {
		this.numeroExecucaoResumoNegativacao = numeroExecucaoResumoNegativacao;
	}

	/**
	 * @return Retorna o campo idNegativadorExclusaoMotivo.
	 */
	public Integer getIdNegativadorExclusaoMotivo() {
		return idNegativadorExclusaoMotivo;
	}

	/**
	 * @param idNegativadorExclusaoMotivo O idNegativadorExclusaoMotivo a ser setado.
	 */
	public void setIdNegativadorExclusaoMotivo(Integer idNegativadorExclusaoMotivo) {
		this.idNegativadorExclusaoMotivo = idNegativadorExclusaoMotivo;
	}

	public Collection getColecaoMotivoRejeicao() {
		return colecaoMotivoRejeicao;
	}

	public void setColecaoMotivoRejeicao(Collection colecaoMotivoRejeicao) {
		this.colecaoMotivoRejeicao = colecaoMotivoRejeicao;
	}

	public Collection getColecaoLigacaoAguaSituacao() {
		return colecaoLigacaoAguaSituacao;
	}

	public void setColecaoLigacaoAguaSituacao(Collection colecaoLigacaoAguaSituacao) {
		this.colecaoLigacaoAguaSituacao = colecaoLigacaoAguaSituacao;
	}

	public Collection getColecaoLigacaoEsgotoSituacao() {
		return colecaoLigacaoEsgotoSituacao;
	}

	public void setColecaoLigacaoEsgotoSituacao(
			Collection colecaoLigacaoEsgotoSituacao) {
		this.colecaoLigacaoEsgotoSituacao = colecaoLigacaoEsgotoSituacao;
	}

	public Collection getColecaoNegativador() {
		return colecaoNegativador;
	}

	public void setColecaoNegativador(Collection colecaoNegativador) {
		this.colecaoNegativador = colecaoNegativador;
	}

	public String getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

	
	
	
}