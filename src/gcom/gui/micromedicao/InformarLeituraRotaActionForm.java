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

import java.util.Vector;

import org.apache.struts.action.ActionForm;


/**
 * 
 * Form com as leituras e anormalidades dos imoveis que foram informadas
 * 
 * @author Thiago Nascimento
 *
 */
public class InformarLeituraRotaActionForm extends ActionForm {
	
	
	private static final long serialVersionUID = 1L;
	
	private String rota;
	
	private String descricaoRota;
	
	private Vector<DadosMovimentacao> dados;
	
	private Integer indice;
	
	private Integer total;
	
	private String[] leituras;
	
	private String[] anormalidades;
	
	private String[] datas;
	
	private String idLocalidade;
	
	private String codigoSetorComercial;
	
	private String localidadeDescricao;
	
	private String setorComercialDescricao;
	
	private String bloquearCampos;
	
	private String tipo;
	
	
	
	
	/**
	 * @return Returns the rota.
	 */
	public String getRota() {
		return rota;
	}

	/**
	 * @param rota The rota to set.
	 */
	public void setRota(String rota) {
		this.rota = rota;
	}

	/**
	 * @return Returns the anormalidades.
	 */
	public String[] getAnormalidades() {
		return anormalidades;
	}

	/**
	 * @param anormalidades The anormalidades to set.
	 */
	public void setAnormalidades(String[] anormalidades) {
		this.anormalidades = anormalidades;
	}

	/**
	 * @return Returns the datas.
	 */
	public String[] getDatas() {
		return datas;
	}

	/**
	 * @param datas The datas to set.
	 */
	public void setDatas(String[] datas) {
		this.datas = datas;
	}

	/**
	 * @return Returns the leituras.
	 */
	public String[] getLeituras() {
		return leituras;
	}

	/**
	 * @param leituras The leituras to set.
	 */
	public void setLeituras(String[] leituras) {
		this.leituras = leituras;
	}

	/**
	 * @return Returns the dados.
	 */
	public Vector<DadosMovimentacao> getDados() {
		return dados;
	}

	/**
	 * @param dados The dados to set.
	 */
	public void setDados(Vector<DadosMovimentacao> dados) {
		this.dados = dados;
	}

	/**
	 * @return Returns the indice.
	 */
	public Integer getIndice() {
		return indice;
	}

	/**
	 * @param indice The indice to set.
	 */
	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	/**
	 * @return Returns the total.
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * @param total The total to set.
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * @return Returns the descricaoRota.
	 */
	public String getDescricaoRota() {
		return descricaoRota;
	}

	/**
	 * @param descricaoRota The descricaoRota to set.
	 */
	public void setDescricaoRota(String descricaoRota) {
		this.descricaoRota = descricaoRota;
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

	/**
	 * @return Returns the bloquearCampos.
	 */
	public String getBloquearCampos() {
		return bloquearCampos;
	}

	/**
	 * @param bloquearCampos The bloquearCampos to set.
	 */
	public void setBloquearCampos(String bloquearCampos) {
		this.bloquearCampos = bloquearCampos;
	}

	/**
	 * @return Returns the tipo.
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo The tipo to set.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
		
	
	
	
	
	
}
