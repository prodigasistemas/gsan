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
package gcom.gui.cobranca.spcserasa;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InformarDadosConsultaNegativacaoActionForm extends ActionForm {
	
	private String idNegativador;
	private String[] arrayNegativador;
	
	private String nomeNegativador;
	
	private String periodoEnvioNegativacaoInicio;
	
	private String periodoEnvioNegativacaoFim;
	
	private String ultimaAtualizacao;
	
	private String indicadorRelExclusao;
	
	private String periodoExclusaoNegativacaoInicio;
	
	private String periodoExclusaoNegativacaoFim;
	
	private String idNegativadorExclusaoMotivo;
	
	
	private String tituloComando;
	
	private String idNegativacaoComando;
	
	private String  cobrancaGrupo;
	private Collection collCobrancaGrupo;
	private String[] arrayCobrancaGrupo;
	

	private String  gerenciaRegional;
	private Collection collGerenciaRegional;
	private String[] arrayGerenciaRegional;	
	
	private String  unidadeNegocio;
	private Collection collUnidadeNegocio;
	private String[] arrayUnidadeNegocio;
	
	private String  imovelPerfil;
	private Collection collImovelPerfil;
	private String[] arrayImovelPerfil;
	
	private String  categoria;
	private Collection collCategoria;
	private String[] arrayCategoria;	

	
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
	
	private String idEsferaPoder;
	
	
	private String  tipoCliente;
	private Collection collTipoCliente;
	private String[] arrayTipoCliente;
	
	
	private String  esferaPoder;
	private Collection collEsferaPoder;
	private String[] arrayEsferaPoder;
	
	private String okEloPolo;
	
	private String okLocalidade;
	
	private String okSetorComercial;
	
	private String okQuadra;
	
	private String indicadorApenasNegativacoesRejeitadas;
	private String  motivoRejeicao;
	private Collection collMotivoRejeicao;
	private String[] arrayMotivoRejeicao;
	private String indicadorRelAcompanhamentoClientesNegativados;
	
	private String ligacaoAguaSituacao;
	private Collection collLigacaoAguaSituacao;
	private String[] arrayLigacaoAguaSituacao;
	private String ligacaoEsgotoSituacao;
	private Collection collLigacaoEsgotoSituacao;
	private String[] arrayLigacaoEsgotoSituacao;
	
	private String tipoRelatorioNegativacao;
	
	private static final long serialVersionUID = 1L;

    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    	
    	this.idNegativador = "";
    	this.periodoEnvioNegativacaoInicio = "";
    	this.periodoEnvioNegativacaoFim = "";
    	this.ultimaAtualizacao= "";
    	this.tituloComando = "";
    	this.cobrancaGrupo = "";
    	this.collCobrancaGrupo = new ArrayList();
    	this.arrayCobrancaGrupo = new String[0];
    	
    	this.gerenciaRegional = "";
    	this.collGerenciaRegional = new ArrayList();
    	this.arrayGerenciaRegional = new String[0];
    	
    	this.unidadeNegocio = "";
    	this.collUnidadeNegocio = new ArrayList();
    	this.arrayUnidadeNegocio = new String[0];
    	
    	this.imovelPerfil="";
    	this.collImovelPerfil = new ArrayList();
    	this.arrayImovelPerfil = new String[0];
        	
       	this.categoria="";
    	this.collCategoria = new ArrayList();
    	this.arrayCategoria = new String[0];
    	
    	this.tipoCliente="";
    	this.collTipoCliente = new ArrayList();
    	this.arrayTipoCliente= new String[0];
    	
    	this.esferaPoder="";
    	this.collEsferaPoder= new ArrayList();
    	this.arrayEsferaPoder= new String[0];
    	
    	this.motivoRejeicao="";
    	this.collMotivoRejeicao= new ArrayList();
    	this.arrayMotivoRejeicao= new String[0];
    
    	  	
    	this.okEloPolo = "";
    	this.okLocalidade = "";
    	this.okSetorComercial = "";
    	this.okQuadra = "";  
    	
    	
    	this.indicadorRelExclusao="";    	
    	this.periodoExclusaoNegativacaoInicio="";    	
    	this.periodoExclusaoNegativacaoFim="";
    	this.idNegativadorExclusaoMotivo = "";
    	
    	
    	this.idEloPolo = "";    	
    	this.descricaoEloPolo= "";
    	
    	this.idLocalidade= "";    	
    	this.descricaoLocalidade= "";

    	this.idSetorComercial= "";    	
    	this.descricaoSetorComercial= "";
    	
    	this.idQuadra= "";    	
    	this.descricaoQuadra= "";
    	
    	
    	
    	
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
	 * @return Retorna o campo arrayGerenciaRegional.
	 */
	public String[] getArrayGerenciaRegional() {
		return arrayGerenciaRegional;
	}

	/**
	 * @param arrayGerenciaRegional O arrayGerenciaRegional a ser setado.
	 */
	public void setArrayGerenciaRegional(String[] arrayGerenciaRegional) {
		this.arrayGerenciaRegional = arrayGerenciaRegional;
	}

	/**
	 * @return Retorna o campo collGerenciaRegional.
	 */
	public Collection getCollGerenciaRegional() {
		return collGerenciaRegional;
	}

	/**
	 * @param collGerenciaRegional O collGerenciaRegional a ser setado.
	 */
	public void setCollGerenciaRegional(Collection collGerenciaRegional) {
		this.collGerenciaRegional = collGerenciaRegional;
	}

	/**
	 * @return Retorna o campo gerenciaRegional.
	 */
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	/**
	 * @param gerenciaRegional O gerenciaRegional a ser setado.
	 */
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
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

	/**
	 * @return Retorna o campo arrayCobrancaGrupo.
	 */
	public String[] getArrayCobrancaGrupo() {
		return arrayCobrancaGrupo;
	}

	/**
	 * @param arrayCobrancaGrupo O arrayCobrancaGrupo a ser setado.
	 */
	public void setArrayCobrancaGrupo(String[] arrayCobrancaGrupo) {
		this.arrayCobrancaGrupo = arrayCobrancaGrupo;
	}

	/**
	 * @return Retorna o campo cobrancaGrupo.
	 */
	public String getCobrancaGrupo() {
		return cobrancaGrupo;
	}

	/**
	 * @param cobrancaGrupo O cobrancaGrupo a ser setado.
	 */
	public void setCobrancaGrupo(String cobrancaGrupo) {
		this.cobrancaGrupo = cobrancaGrupo;
	}

	/**
	 * @return Retorna o campo collCobrancaGrupo.
	 */
	public Collection getCollCobrancaGrupo() {
		return collCobrancaGrupo;
	}

	/**
	 * @param collCobrancaGrupo O collCobrancaGrupo a ser setado.
	 */
	public void setCollCobrancaGrupo(Collection collCobrancaGrupo) {
		this.collCobrancaGrupo = collCobrancaGrupo;
	}

	/**
	 * @return Retorna o campo arrayUnidadeNegocio.
	 */
	public String[] getArrayUnidadeNegocio() {
		return arrayUnidadeNegocio;
	}

	/**
	 * @param arrayUnidadeNegocio O arrayUnidadeNegocio a ser setado.
	 */
	public void setArrayUnidadeNegocio(String[] arrayUnidadeNegocio) {
		this.arrayUnidadeNegocio = arrayUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo collUnidadeNegocio.
	 */
	public Collection getCollUnidadeNegocio() {
		return collUnidadeNegocio;
	}

	/**
	 * @param collUnidadeNegocio O collUnidadeNegocio a ser setado.
	 */
	public void setCollUnidadeNegocio(Collection collUnidadeNegocio) {
		this.collUnidadeNegocio = collUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo unidadeNegocio.
	 */
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	/**
	 * @param unidadeNegocio O unidadeNegocio a ser setado.
	 */
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	/**
	 * @return Retorna o campo arrayImovelPerfil.
	 */
	public String[] getArrayImovelPerfil() {
		return arrayImovelPerfil;
	}

	/**
	 * @param arrayImovelPerfil O arrayImovelPerfil a ser setado.
	 */
	public void setArrayImovelPerfil(String[] arrayImovelPerfil) {
		this.arrayImovelPerfil = arrayImovelPerfil;
	}

	/**
	 * @return Retorna o campo collImovelPerfil.
	 */
	public Collection getCollImovelPerfil() {
		return collImovelPerfil;
	}

	/**
	 * @param collImovelPerfil O collImovelPerfil a ser setado.
	 */
	public void setCollImovelPerfil(Collection collImovelPerfil) {
		this.collImovelPerfil = collImovelPerfil;
	}

	/**
	 * @return Retorna o campo imovelPerfil.
	 */
	public String getImovelPerfil() {
		return imovelPerfil;
	}

	/**
	 * @param imovelPerfil O imovelPerfil a ser setado.
	 */
	public void setImovelPerfil(String imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	/**
	 * @return Retorna o campo arrayCategoria.
	 */
	public String[] getArrayCategoria() {
		return arrayCategoria;
	}

	/**
	 * @param arrayCategoria O arrayCategoria a ser setado.
	 */
	public void setArrayCategoria(String[] arrayCategoria) {
		this.arrayCategoria = arrayCategoria;
	}

	/**
	 * @return Retorna o campo categoria.
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria O categoria a ser setado.
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return Retorna o campo collCategoria.
	 */
	public Collection getCollCategoria() {
		return collCategoria;
	}

	/**
	 * @param collCategoria O collCategoria a ser setado.
	 */
	public void setCollCategoria(Collection collCategoria) {
		this.collCategoria = collCategoria;
	}

	/**
	 * @return Retorna o campo arrayEsferaPoder.
	 */
	public String[] getArrayEsferaPoder() {
		return arrayEsferaPoder;
	}

	/**
	 * @param arrayEsferaPoder O arrayEsferaPoder a ser setado.
	 */
	public void setArrayEsferaPoder(String[] arrayEsferaPoder) {
		this.arrayEsferaPoder = arrayEsferaPoder;
	}

	/**
	 * @return Retorna o campo arrayTipoCliente.
	 */
	public String[] getArrayTipoCliente() {
		return arrayTipoCliente;
	}

	/**
	 * @param arrayTipoCliente O arrayTipoCliente a ser setado.
	 */
	public void setArrayTipoCliente(String[] arrayTipoCliente) {
		this.arrayTipoCliente = arrayTipoCliente;
	}

	/**
	 * @return Retorna o campo collEsferaPoder.
	 */
	public Collection getCollEsferaPoder() {
		return collEsferaPoder;
	}

	/**
	 * @param collEsferaPoder O collEsferaPoder a ser setado.
	 */
	public void setCollEsferaPoder(Collection collEsferaPoder) {
		this.collEsferaPoder = collEsferaPoder;
	}

	/**
	 * @return Retorna o campo collTipoCliente.
	 */
	public Collection getCollTipoCliente() {
		return collTipoCliente;
	}

	/**
	 * @param collTipoCliente O collTipoCliente a ser setado.
	 */
	public void setCollTipoCliente(Collection collTipoCliente) {
		this.collTipoCliente = collTipoCliente;
	}

	/**
	 * @return Retorna o campo esferaPoder.
	 */
	public String getEsferaPoder() {
		return esferaPoder;
	}

	/**
	 * @param esferaPoder O esferaPoder a ser setado.
	 */
	public void setEsferaPoder(String esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	/**
	 * @return Retorna o campo tipoCliente.
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * @param tipoCliente O tipoCliente a ser setado.
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
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
	 * @return Retorna o campo indicadorRelExclusao.
	 */
	public String getIndicadorRelExclusao() {
		return indicadorRelExclusao;
	}

	/**
	 * @param indicadorRelExclusao O indicadorRelExclusao a ser setado.
	 */
	public void setIndicadorRelExclusao(String indicadorRelExclusao) {
		this.indicadorRelExclusao = indicadorRelExclusao;
	}

	/**
	 * @return Retorna o campo periodoExclusaoNegativacaoFim.
	 */
	public String getPeriodoExclusaoNegativacaoFim() {
		return periodoExclusaoNegativacaoFim;
	}

	/**
	 * @param periodoExclusaoNegativacaoFim O periodoExclusaoNegativacaoFim a ser setado.
	 */
	public void setPeriodoExclusaoNegativacaoFim(
			String periodoExclusaoNegativacaoFim) {
		this.periodoExclusaoNegativacaoFim = periodoExclusaoNegativacaoFim;
	}

	/**
	 * @return Retorna o campo periodoExclusaoNegativacaoInicio.
	 */
	public String getPeriodoExclusaoNegativacaoInicio() {
		return periodoExclusaoNegativacaoInicio;
	}

	/**
	 * @param periodoExclusaoNegativacaoInicio O periodoExclusaoNegativacaoInicio a ser setado.
	 */
	public void setPeriodoExclusaoNegativacaoInicio(
			String periodoExclusaoNegativacaoInicio) {
		this.periodoExclusaoNegativacaoInicio = periodoExclusaoNegativacaoInicio;
	}

	/**
	 * @return Retorna o campo ultimaAtualizacao.
	 */
	public String getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	/**
	 * @param ultimaAtualizacao O ultimaAtualizacao a ser setado.
	 */
	public void setUltimaAtualizacao(String ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

	/**
	 * @return Retorna o campo idNegativadorExclusaoMotivo.
	 */
	public String getIdNegativadorExclusaoMotivo() {
		return idNegativadorExclusaoMotivo;
	}

	/**
	 * @param idNegativadorExclusaoMotivo O idNegativadorExclusaoMotivo a ser setado.
	 */
	public void setIdNegativadorExclusaoMotivo(String idNegativadorExclusaoMotivo) {
		this.idNegativadorExclusaoMotivo = idNegativadorExclusaoMotivo;
	}

	public String[] getArrayMotivoRejeicao() {
		return arrayMotivoRejeicao;
	}

	public void setArrayMotivoRejeicao(String[] arrayMotivoRejeicao) {
		this.arrayMotivoRejeicao = arrayMotivoRejeicao;
	}

	public Collection getCollMotivoRejeicao() {
		return collMotivoRejeicao;
	}

	public void setCollMotivoRejeicao(Collection collMotivoRejeicao) {
		this.collMotivoRejeicao = collMotivoRejeicao;
	}

	public String getIndicadorApenasNegativacoesRejeitadas() {
		return indicadorApenasNegativacoesRejeitadas;
	}

	public void setIndicadorApenasNegativacoesRejeitadas(
			String indicadorApenasNegativacoesRejeitadas) {
		this.indicadorApenasNegativacoesRejeitadas = indicadorApenasNegativacoesRejeitadas;
	}

	public String getMotivoRejeicao() {
		return motivoRejeicao;
	}

	public void setMotivoRejeicao(String motivoRejeicao) {
		this.motivoRejeicao = motivoRejeicao;
	}

	public String getIndicadorRelAcompanhamentoClientesNegativados() {
		return indicadorRelAcompanhamentoClientesNegativados;
	}

	public void setIndicadorRelAcompanhamentoClientesNegativados(
			String indicadorRelAcompanhamentoClientesNegativados) {
		this.indicadorRelAcompanhamentoClientesNegativados = indicadorRelAcompanhamentoClientesNegativados;
	}

	public String[] getArrayNegativador() {
		return arrayNegativador;
	}

	public void setArrayNegativador(String[] arrayNegativador) {
		this.arrayNegativador = arrayNegativador;
	}

	public String[] getArrayLigacaoAguaSituacao() {
		return arrayLigacaoAguaSituacao;
	}

	public void setArrayLigacaoAguaSituacao(String[] arrayLigacaoAguaSituacao) {
		this.arrayLigacaoAguaSituacao = arrayLigacaoAguaSituacao;
	}

	public String[] getArrayLigacaoEsgotoSituacao() {
		return arrayLigacaoEsgotoSituacao;
	}

	public void setArrayLigacaoEsgotoSituacao(String[] arrayLigacaoEsgotoSituacao) {
		this.arrayLigacaoEsgotoSituacao = arrayLigacaoEsgotoSituacao;
	}

	public Collection getCollLigacaoAguaSituacao() {
		return collLigacaoAguaSituacao;
	}

	public void setCollLigacaoAguaSituacao(Collection collLigacaoAguaSituacao) {
		this.collLigacaoAguaSituacao = collLigacaoAguaSituacao;
	}

	public Collection getCollLigacaoEsgotoSituacao() {
		return collLigacaoEsgotoSituacao;
	}

	public void setCollLigacaoEsgotoSituacao(Collection collLigacaoEsgotoSituacao) {
		this.collLigacaoEsgotoSituacao = collLigacaoEsgotoSituacao;
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

	public String getTipoRelatorioNegativacao() {
		return tipoRelatorioNegativacao;
	}

	public void setTipoRelatorioNegativacao(String tipoRelatorioNegativacao) {
		this.tipoRelatorioNegativacao = tipoRelatorioNegativacao;
	}

	

}