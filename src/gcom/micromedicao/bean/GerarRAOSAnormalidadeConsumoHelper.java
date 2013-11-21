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

package gcom.micromedicao.bean;

import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.seguranca.acesso.usuario.Usuario;

import java.math.BigDecimal;

/**
 * 
 * Classe de apoio ao 
 * [UC-1127] - Gerar RA e OS para Anormalidade Consumo
 * 
 * @author Bruno Barros
 * @date 16/02/2011
 *
 */
public class GerarRAOSAnormalidadeConsumoHelper {
	
	private Integer idImovel;	
	private Integer idImovelPerfil;
	private Integer idPrincipalCategoria;
	private Integer idConsumoAnormalidade;
	private Integer idSolicitacaoTipoEspecificacaoMes1;
	private Integer idSolicitacaoTipoEspecificacaoMes2;
	private Integer idSolicitacaoTipoEspecificacaoMes3;
	private String  descricaoAnormalidade;
	private UnidadeOrganizacional unidadeOrganizacional;
	private Integer idLocalidadeImovel;
	private Integer idSolicitacaoTipoGrupo;
	private String dataPrevistaRA;
	private Integer numeroDiasPrazoAtendimentoRA;
	private Integer idSolicitacaoTipoEspecificacao;
	private String observacaoRA;
	private Integer idSolicitacaoTipo;
	private Integer idSetorComercial;
	private Integer idQuadra;
	private Usuario admin;	
	private Integer idServicoTipo;
	private BigDecimal coordenadaX;
	private BigDecimal coordenadaY;
	
	public Integer getIdServicoTipo() {
		return idServicoTipo;
	}


	public void setIdServicoTipo(Integer idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}


	public String getObservacaoRA() {
		return observacaoRA;
	}


	public void setObservacaoRA(String observacaoRA) {
		this.observacaoRA = observacaoRA;
	}


	public Integer getIdSolicitacaoTipoEspecificacao() {
		return idSolicitacaoTipoEspecificacao;
	}


	public void setIdSolicitacaoTipoEspecificacao(
			Integer idSolicitacaoTipoEspecificacao) {
		this.idSolicitacaoTipoEspecificacao = idSolicitacaoTipoEspecificacao;
	}


	public Integer getNumeroDiasPrazoAtendimentoRA() {
		return numeroDiasPrazoAtendimentoRA;
	}


	public void setNumeroDiasPrazoAtendimentoRA(Integer numeroDiasPrazoAtendimentoRA) {
		this.numeroDiasPrazoAtendimentoRA = numeroDiasPrazoAtendimentoRA;
	}


	public String getDataPrevistaRA() {
		return dataPrevistaRA;
	}


	public void setDataPrevistaRA(String dataPrevistaRA) {
		this.dataPrevistaRA = dataPrevistaRA;
	}


	public Integer getIdSolicitacaoTipoGrupo() {
		return idSolicitacaoTipoGrupo;
	}


	public void setIdSolicitacaoTipo(Integer idSolicitacaoTipo) {
		this.idSolicitacaoTipo = idSolicitacaoTipo;
	}


	public Integer getIdLocalidadeImovel() {
		return idLocalidadeImovel;
	}


	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}


	public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}


	public String getDescricaoAnormalidade() {
		return descricaoAnormalidade;
	}


	public void setDescricaoAnormalidade(String descricaoAnormalidade) {
		this.descricaoAnormalidade = descricaoAnormalidade;
	}


	public Integer getIdConsumoAnormalidade() {
		return idConsumoAnormalidade;
	}


	public Integer getIdPrincipalCategoria() {
		return idPrincipalCategoria;
	}


	public void setIdPrincipalCategoria(Integer idPrincipalCategoria) {
		this.idPrincipalCategoria = idPrincipalCategoria;
	}


	/**
	 * 
	 * Construtor que ira monta o helper para facilitar
	 * o desenvolvimento
	 * 
	 * @param dados - Sequencia para criação:
	 * 
	 * 	[0] - imov_id
	 *  [1] - iper_id
	 *  [2] - csaa_id
	 *  [3] - loca_id
	 *  [4] - stcm_id
	 *  [5] - qdra_id
	 *  [6] - IMOV_NNCOORDENADAY
	 *  [7] - IMOV_NNCOORDENADAX
	 * 
	 */
	public GerarRAOSAnormalidadeConsumoHelper( Object[] dados ){
		this.idImovel = (Integer) dados[0];
		this.idImovelPerfil = (Integer) dados[1];
		this.idConsumoAnormalidade = ( Integer ) dados[2];
		this.idLocalidadeImovel = ( Integer ) dados[3];
		this.idSetorComercial = ( Integer ) dados[4];
		this.idQuadra = ( Integer ) dados[5];
		this.coordenadaY = ( BigDecimal ) dados[6];
		this.coordenadaX = ( BigDecimal ) dados[7];
	}


	public Integer getIdImovel() {
		return idImovel;
	}


	public Integer getIdImovelPerfil() {
		return idImovelPerfil;
	}


	public Integer getIdSolicitacaoTipoEspecificacaoMes1() {
		return idSolicitacaoTipoEspecificacaoMes1;
	}


	public void setIdSolicitacaoTipoEspecificacaoMes1(
			Integer idSolicitacaoTipoEspecificacaoMes1) {
		this.idSolicitacaoTipoEspecificacaoMes1 = idSolicitacaoTipoEspecificacaoMes1;
	}


	public Integer getIdSolicitacaoTipoEspecificacaoMes2() {
		return idSolicitacaoTipoEspecificacaoMes2;
	}


	public void setIdSolicitacaoTipoEspecificacaoMes2(
			Integer idSolicitacaoTipoEspecificacaoMes2) {
		this.idSolicitacaoTipoEspecificacaoMes2 = idSolicitacaoTipoEspecificacaoMes2;
	}


	public Integer getIdSolicitacaoTipoEspecificacaoMes3() {
		return idSolicitacaoTipoEspecificacaoMes3;
	}


	public void setIdSolicitacaoTipoEspecificacaoMes3(
			Integer idSolicitacaoTipoEspecificacaoMes3) {
		this.idSolicitacaoTipoEspecificacaoMes3 = idSolicitacaoTipoEspecificacaoMes3;
	}


	public Integer getIdQuadra() {
		return idQuadra;
	}


	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}


	public Integer getIdSolicitacaoTipo() {
		return idSolicitacaoTipo;
	}


	public Usuario getAdmin() {
		return admin;
	}


	public void setAdmin(Usuario admin) {
		this.admin = admin;
	}


	public BigDecimal getCoordenadaX() {
		return coordenadaX;
	}

	public BigDecimal getCoordenadaY() {
		return coordenadaY;
	}


	public void setIdSolicitacaoTipoGrupo(Integer idSolicitacaoTipoGrupo) {
		this.idSolicitacaoTipoGrupo = idSolicitacaoTipoGrupo;
	}	
}
