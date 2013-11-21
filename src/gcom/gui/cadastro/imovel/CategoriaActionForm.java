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
package gcom.gui.cadastro.imovel;

import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Roberta Costa
 * @created 22 de Dezembro de 2005
 */
public class CategoriaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idCategoria;

    private String descricao;

    private String descricaoAbreviada;

    private String tipoCategoria;

    private String consumoMinimo;

    private String consumoEstouro;

    private String vezesMediaEstouro;
    
    private String mediaBaixoConsumo;
    
    private String porcentagemMediaBaixoConsumo;
    
    private String consumoAlto;

    private String vezesMediaAltoConsumo;

    private String indicadorUso;

	/**
	 * @return Returns the consumoAlto.
	 */
	public String getConsumoAlto() {
		return consumoAlto;
	}



	/**
	 * @param consumoAlto The consumoAlto to set.
	 */
	public void setConsumoAlto(String consumoAlto) {
		this.consumoAlto = consumoAlto;
	}



	/**
	 * @return Returns the consumoEstouro.
	 */
	public String getConsumoEstouro() {
		return consumoEstouro;
	}



	/**
	 * @param consumoEstouro The consumoEstouro to set.
	 */
	public void setConsumoEstouro(String consumoEstouro) {
		this.consumoEstouro = consumoEstouro;
	}



	/**
	 * @return Returns the consumoMinimo.
	 */
	public String getConsumoMinimo() {
		return consumoMinimo;
	}



	/**
	 * @param consumoMinimo The consumoMinimo to set.
	 */
	public void setConsumoMinimo(String consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}



	/**
	 * @return Returns the descricao.
	 */
	public String getDescricao() {
		return descricao;
	}



	/**
	 * @param descricao The descricao to set.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	/**
	 * @return Returns the descricaoAbreviada.
	 */
	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}



	/**
	 * @param descricaoAbreviada The descricaoAbreviada to set.
	 */
	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}



	/**
	 * @return Returns the idCategoria.
	 */
	public String getIdCategoria() {
		return idCategoria;
	}



	/**
	 * @param idCategoria The idCategoria to set.
	 */
	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
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
	 * @return Returns the mediaBaixoConsumo.
	 */
	public String getMediaBaixoConsumo() {
		return mediaBaixoConsumo;
	}



	/**
	 * @param mediaBaixoConsumo The mediaBaixoConsumo to set.
	 */
	public void setMediaBaixoConsumo(String mediaBaixoConsumo) {
		this.mediaBaixoConsumo = mediaBaixoConsumo;
	}



	/**
	 * @return Returns the porcentagemMediaBaixoConsumo.
	 */
	public String getPorcentagemMediaBaixoConsumo() {
		return porcentagemMediaBaixoConsumo;
	}



	/**
	 * @param porcentagemMediaBaixoConsumo The porcentagemMediaBaixoConsumo to set.
	 */
	public void setPorcentagemMediaBaixoConsumo(String porcentagemMediaBaixoConsumo) {
		this.porcentagemMediaBaixoConsumo = porcentagemMediaBaixoConsumo;
	}



	/**
	 * @return Returns the vezesMediaAltoConsumo.
	 */
	public String getVezesMediaAltoConsumo() {
		return vezesMediaAltoConsumo;
	}



	/**
	 * @param vezesMediaAltoConsumo The vezesMediaAltoConsumo to set.
	 */
	public void setVezesMediaAltoConsumo(String vezesMediaAltoConsumo) {
		this.vezesMediaAltoConsumo = vezesMediaAltoConsumo;
	}



	/**
	 * @return Returns the vezesMediaEstouro.
	 */
	public String getVezesMediaEstouro() {
		return vezesMediaEstouro;
	}

	/**
	 * @param vezesMediaEstouro The vezesMediaEstouro to set.
	 */
	public void setVezesMediaEstouro(String vezesMediaEstouro) {
		this.vezesMediaEstouro = vezesMediaEstouro;
	}
	
	/**
	 * @return Retorna o campo tipoCategoria.
	 */
	public String getTipoCategoria() {
		return tipoCategoria;
	}

	/**
	 * @param tipoCategoria O tipoCategoria a ser setado.
	 */
	public void setTipoCategoria(String tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
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
    	idCategoria = null;
        descricao = null;
        descricaoAbreviada = null;
        consumoMinimo = null;
        consumoEstouro = null;
        vezesMediaEstouro = null;
        mediaBaixoConsumo = null;
        porcentagemMediaBaixoConsumo = null;
        consumoAlto = null;
        vezesMediaAltoConsumo = null;
		indicadorUso = ConstantesSistema.INDICADOR_USO_ATIVO.toString();
    }
}