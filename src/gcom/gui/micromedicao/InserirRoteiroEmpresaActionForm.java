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
package gcom.gui.micromedicao;

import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.*;

/**
 * Form utilizado no Inserir Roteiro Empresa e no Atualizar Roteiro Empresa
 * 
 * @author Francisco Nascimento
 * @created 24/07/2007
 */
public class InserirRoteiroEmpresaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String empresa;
	private String idLocalidade;
	private String descricaoLocalidade;
	private String faturamentoGrupo;
	private String idLeiturista;
	private String nomeLeiturista;
	private String []idSetorComercial;
	private String []idSetorComercialSelecionados;
	private String[] idQuadraAdicionar;
	private String indicadorUso;
	private String ultimaAlteracao;
	private String quadra;
	
	/**
	 * @param quadra The quadra to set.
	 */
	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}
	/**
	 * @return Returns the idLeiturista.
	 */
	public String getIdLeiturista() {
		return idLeiturista;
	}
	/**
	 * @param idLeiturista The idLeiturista to set.
	 */
	public void setIdLeiturista(String idLeiturista) {
		this.idLeiturista = idLeiturista;
	}
	/**
	 * @return Returns the nomeLeiturista.
	 */
	public String getNomeLeiturista() {
		return nomeLeiturista;
	}
	/**
	 * @param nomeLeiturista The nomeLeiturista to set.
	 */
	public void setNomeLeiturista(String nomeLeiturista) {
		this.nomeLeiturista = nomeLeiturista;
	}
	/**
	 * @return Returns the descricaoLocalidade.
	 */
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	/**
	 * @param descricaoLocalidade The descricaoLocalidade to set.
	 */
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	/**
	 * @return Returns the empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}
	/**
	 * @param empresa The empresa to set.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	/**
	 * @return Returns the idLocalidade.
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}
	/**
	 * @param idLocalidade The idLocalidade to set.
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	/**
	 * @return Returns the indicadorUso.
	 */
	public String getIndicadorUso() {
		return indicadorUso;
	}
	/**
	 * @param indicadorUso The indicadorUso to set.
	 */
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	/**
	 * @return Returns the quadras.
	 */
	public String getQuadra() {
		return quadra;
	}
	/**
	 * @param quadras The quadras to set.
	 */
	public void setQuadras(String quadras) {
		this.quadra = quadras;
	}
	/**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     */
    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    	indicadorUso = ConstantesSistema.INDICADOR_USO_ATIVO.toString();
    	ultimaAlteracao = null;
    	setIdQuadraAdicionar(new String[0]);
    }
	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	/**
	 * @return Returns the faturamentoGrupo.
	 */
	public String getFaturamentoGrupo() {
		return faturamentoGrupo;
	}
	/**
	 * @param faturamentoGrupo The faturamentoGrupo to set.
	 */
	public void setFaturamentoGrupo(String faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}
	/**
	 * @return Returns the idSetorComercial.
	 */
	public String[] getIdSetorComercial() {
		return idSetorComercial;
	}
	/**
	 * @param idSetorComercial The idSetorComercial to set.
	 */
	public void setIdSetorComercial(String idSetorComercial[]) {
		this.idSetorComercial = idSetorComercial;
	}
	/**
	 * @return Returns the idSetorComercialSelecionados.
	 */
	public String[] getIdSetorComercialSelecionados() {
		return idSetorComercialSelecionados;
	}
	/**
	 * @param idSetorComercialSelecionados The idSetorComercialSelecionados to set.
	 */
	public void setIdSetorComercialSelecionados(
			String[] idSetorComercialSelecionados) {
		this.idSetorComercialSelecionados = idSetorComercialSelecionados;
	}
	/**
	 * @return Returns the idQuadraAdicionar.
	 */
	public String[] getIdQuadraAdicionar() {
		return idQuadraAdicionar;
	}
	/**
	 * @param idQuadraAdicionar The idQuadraAdicionar to set.
	 */
	public void setIdQuadraAdicionar(String[] idQuadraAdicionar) {
		this.idQuadraAdicionar = idQuadraAdicionar;
	}
	
	}