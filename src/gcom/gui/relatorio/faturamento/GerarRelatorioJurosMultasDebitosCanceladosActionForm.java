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
package gcom.gui.relatorio.faturamento;


import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.faturamento.debito.DebitoTipo;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.validator.ValidatorActionForm;

/**
 *[UC0958] - Gerar Relatorio de Juros, Multas e Debitos Cancelados.
 *
 * @author Marlon Patrick
 * @since 07/10/2009
 */
public class GerarRelatorioJurosMultasDebitosCanceladosActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String mesAnoFaturamento;

	private String dataCancelamentoInicial;	
	private String dataCancelamentoFinal;

	private String idUnidadeNegocio;	
	
	private String idLocalidade;
	private String nomeLocalidade;
	private boolean isLocalidadeExistente;
	
	private String[] idsTiposDebito;

	private String[] idsCategoria;
		
	private String[] idsPerfilImovel;	

	private String[] idsEsferaPoder;
	
	private String idUsuarioCancelamento;
	private String nomeUsuarioCancelamento;	
	private boolean isUsuarioExistente;
	
	private Collection<UnidadeNegocio> colecaoUnidadesNegocios;
	private Collection<DebitoTipo> colecaoTiposDebito;
	private Collection<Categoria> colecaoCategorias;
	private Collection<ImovelPerfil> colecaoPerfisImovel;
	private Collection<EsferaPoder> colecaoEsferasPoder;
	
	public void reset(){
		
		this.mesAnoFaturamento = "";

		this.dataCancelamentoInicial = "";		
		this.dataCancelamentoFinal = "";

		this.idUnidadeNegocio = null;

		this.idLocalidade = "";
		this.nomeLocalidade = "";
		this.isLocalidadeExistente = false;
		
		this.idsTiposDebito = null;
		this.idsCategoria = null;
		this.idsPerfilImovel = null;
		this.idsEsferaPoder = null;
		
		this.idUsuarioCancelamento = "";
		this.nomeUsuarioCancelamento = "";
		this.isUsuarioExistente = false;
		
		this.colecaoUnidadesNegocios = new ArrayList<UnidadeNegocio>();
		this.colecaoTiposDebito = new ArrayList<DebitoTipo>();
		this.colecaoCategorias = new ArrayList<Categoria>();
		this.colecaoPerfisImovel = new ArrayList<ImovelPerfil>();
		this.colecaoEsferasPoder = new ArrayList<EsferaPoder>();

	}

	public String getMesAnoFaturamento() {
		return mesAnoFaturamento;
	}

	public void setMesAnoFaturamento(String mesAnoFaturamento) {
		this.mesAnoFaturamento = mesAnoFaturamento;
	}

	public String getDataCancelamentoInicial() {
		return dataCancelamentoInicial;
	}

	public void setDataCancelamentoInicial(String dataCancelamentoInicial) {
		this.dataCancelamentoInicial = dataCancelamentoInicial;
	}

	public String getDataCancelamentoFinal() {
		return dataCancelamentoFinal;
	}

	public void setDataCancelamentoFinal(String dataCancelamentoFinal) {
		this.dataCancelamentoFinal = dataCancelamentoFinal;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String unidadeNegocio) {
		this.idUnidadeNegocio = unidadeNegocio;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String[] getIdsTiposDebito() {
		return idsTiposDebito;
	}

	public void setIdsTiposDebito(String[] tipoDebito) {
		this.idsTiposDebito = tipoDebito;
	}

	public String[] getIdsCategoria() {
		return idsCategoria;
	}

	public void setIdsCategoria(String[] categorias) {
		this.idsCategoria = categorias;
	}

	public String[] getIdsPerfilImovel() {
		return idsPerfilImovel;
	}

	public void setIdsPerfilImovel(String[] perfisImovel) {
		this.idsPerfilImovel = perfisImovel;
	}

	public String[] getIdsEsferaPoder() {
		return idsEsferaPoder;
	}

	public void setIdsEsferaPoder(String[] esferasPoder) {
		this.idsEsferaPoder = esferasPoder;
	}

	public String getIdUsuarioCancelamento() {
		return idUsuarioCancelamento;
	}

	public void setIdUsuarioCancelamento(
			String idUsuarioResponsavelCancelamento) {
		this.idUsuarioCancelamento = idUsuarioResponsavelCancelamento;
	}

	public String getNomeUsuarioCancelamento() {
		return nomeUsuarioCancelamento;
	}

	public void setNomeUsuarioCancelamento(
			String nomeUsuarioResponsavelCancelamento) {
		this.nomeUsuarioCancelamento = nomeUsuarioResponsavelCancelamento;
	}

	public Collection<UnidadeNegocio> getColecaoUnidadesNegocios() {
		return colecaoUnidadesNegocios;
	}

	public void setColecaoUnidadesNegocios(
			Collection<UnidadeNegocio> colecaoUnidadesNegocios) {
		this.colecaoUnidadesNegocios = colecaoUnidadesNegocios;
	}

	public Collection<Categoria> getColecaoCategorias() {
		return colecaoCategorias;
	}

	public void setColecaoCategorias(Collection<Categoria> colecaoCategorias) {
		this.colecaoCategorias = colecaoCategorias;
	}

	public Collection<EsferaPoder> getColecaoEsferasPoder() {
		return colecaoEsferasPoder;
	}

	public void setColecaoEsferasPoder(Collection<EsferaPoder> colecaoEsferasPoder) {
		this.colecaoEsferasPoder = colecaoEsferasPoder;
	}

	public Collection<DebitoTipo> getColecaoTiposDebito() {
		return colecaoTiposDebito;
	}

	public void setColecaoTiposDebito(Collection<DebitoTipo> colecaoTiposDebito) {
		this.colecaoTiposDebito = colecaoTiposDebito;
	}

	public Collection<ImovelPerfil> getColecaoPerfisImovel() {
		return colecaoPerfisImovel;
	}

	public void setColecaoPerfisImovel(Collection<ImovelPerfil> colecaoPerfisImovel) {
		this.colecaoPerfisImovel = colecaoPerfisImovel;
	}

	public boolean isLocalidadeExistente() {
		return isLocalidadeExistente;
	}

	public void setLocalidadeExistente(boolean isLocalidadeExistente) {
		this.isLocalidadeExistente = isLocalidadeExistente;
	}

	public boolean isUsuarioExistente() {
		return isUsuarioExistente;
	}

	public void setUsuarioExistente(boolean isUsuarioExistente) {
		this.isUsuarioExistente = isUsuarioExistente;
	}	
}