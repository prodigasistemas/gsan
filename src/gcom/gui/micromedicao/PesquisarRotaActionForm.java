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

import org.apache.struts.action.ActionForm;


/**
 * 
 * Form da Pesquisa por Rota da página Infomar Leitura por Rota
 * 
 * @author Administrador
 *
 */
public class PesquisarRotaActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String idGrupoFaturamento;
	
	private String idLocalidade;
	
	private String localidadeDescricao;
	
	private String codigoSetorComercial;
	
	private String setorComercialDescricao;
	
	private String empresaLeituristica;
	
	private String codigoRota;
	
	private String indicadorUso;
	
	private String indicadorRotaAlternativa;
	
	private String bloquearCampos;

	/**
	 * @return String O campo de indicador rota alternativa
	 */
	public String getIndicadorRotaAlternativa() {
		return indicadorRotaAlternativa;
	}
	
	
	/**
	 * @param indicadorRotaAlternativa - O campo indicador de rota alternativa
	 */
	public void setIndicadorRotaAlternativa(String indicadorRotaAlternativa) {
		this.indicadorRotaAlternativa = indicadorRotaAlternativa;
	}

	/**
	 * @return Retorna o campo bloquearCampos.
	 */
	public String getBloquearCampos() {
		return bloquearCampos;
	}

	/**
	 * @param bloquearCampos O bloquearCampos a ser setado.
	 */
	public void setBloquearCampos(String bloquearCampos) {
		this.bloquearCampos = bloquearCampos;
	}

	/**
	 * @return Retorna o campo codigoRota.
	 */
	public String getCodigoRota() {
		return codigoRota;
	}

	/**
	 * @param codigoRota O codigoRota a ser setado.
	 */
	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}

	/**
	 * @return Retorna o campo indicadorUso.
	 */
	public String getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @param indicadorUso O indicadorUso a ser setado.
	 */
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	/**
	 * @return Returns the codigoSetorComercial.
	 */
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	/**
	 * @param codigoSetorComercial The codigoSetorComercial to set.
	 */
	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	/**
	 * @return Returns the empresaLeituristica.
	 */
	public String getEmpresaLeituristica() {
		return empresaLeituristica;
	}

	/**
	 * @param empresaLeituristica The empresaLeituristica to set.
	 */
	public void setEmpresaLeituristica(String empresaLeituristica) {
		this.empresaLeituristica = empresaLeituristica;
	}

	/**
	 * @return Returns the idGrupoFaturamento.
	 */
	public String getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	/**
	 * @param idGrupoFaturamento The idGrupoFaturamento to set.
	 */
	public void setIdGrupoFaturamento(String idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
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
	 * @return Returns the localidadeDescricao.
	 */
	public String getLocalidadeDescricao() {
		return localidadeDescricao;
	}

	/**
	 * @param localidadeDescricao The localidadeDescricao to set.
	 */
	public void setLocalidadeDescricao(String localidadeDescricao) {
		this.localidadeDescricao = localidadeDescricao;
	}

	/**
	 * @return Returns the setorComercialDescricao.
	 */
	public String getSetorComercialDescricao() {
		return setorComercialDescricao;
	}

	/**
	 * @param setorComercialDescricao The setorComercialDescricao to set.
	 */
	public void setSetorComercialDescricao(String setorComercialDescricao) {
		this.setorComercialDescricao = setorComercialDescricao;
	}
	
	

}
