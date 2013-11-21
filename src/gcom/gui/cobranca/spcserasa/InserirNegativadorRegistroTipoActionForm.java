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
 * Yara Taciane de Souza
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

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Action form responsável pelas propiedades do caso de uso de inserir o
 * tipo do registro do negativador.
 * 
 * @author Yara Taciane de Souza
 * @date 07/01/2008
 */
public class InserirNegativadorRegistroTipoActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private String idNegativadorRegistroTipo;

	private String descricaoRegistroTipo;

	private String codigoRegistro;

	private String ultimaAlteracao;

	private String idNegativador;

	private Collection collNegativador;

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * <Breve descrição sobre o subfluxo>
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * <Breve descrição sobre o fluxo secundário>
	 * 
	 * <Identificador e nome do fluxo secundário>
	 * 
	 * @author Yara Taciane
	 * @date 02/01/2008
	 * 
	 * @param arg0
	 * @param arg1
	 */
	@Override
	public void reset(ActionMapping arg0, ServletRequest arg1) {
		// TODO Auto-generated method stub
		super.reset(arg0, arg1);

		this.idNegativadorRegistroTipo = "";
		this.codigoRegistro = "";
		this.descricaoRegistroTipo = "";
		this.ultimaAlteracao = "";
		this.idNegativador = "";
		this.collNegativador = new ArrayList();
		this.ultimaAlteracao = "";

	}

	/**
	 * @return Retorna o campo codigoRegistro.
	 */
	public String getCodigoRegistro() {
		return codigoRegistro;
	}

	/**
	 * @param codigoRegistro
	 *            O codigoRegistro a ser setado.
	 */
	public void setCodigoRegistro(String codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}

	/**
	 * @return Retorna o campo descricaoRegistroTipo.
	 */
	public String getDescricaoRegistroTipo() {
		return descricaoRegistroTipo;
	}

	/**
	 * @param descricaoRegistroTipo
	 *            O descricaoRegistroTipo a ser setado.
	 */
	public void setDescricaoRegistroTipo(String descricaoRegistroTipo) {
		this.descricaoRegistroTipo = descricaoRegistroTipo;
	}

	/**
	 * @return Retorna o campo idNegativadorRegistroTipo.
	 */
	public String getIdNegativadorRegistroTipo() {
		return idNegativadorRegistroTipo;
	}

	/**
	 * @param idNegativadorRegistroTipo
	 *            O idNegativadorRegistroTipo a ser setado.
	 */
	public void setIdNegativadorRegistroTipo(String idNegativadorRegistroTipo) {
		this.idNegativadorRegistroTipo = idNegativadorRegistroTipo;
	}

	/**
	 * @return Retorna o campo collNegativador.
	 */
	public Collection getCollNegativador() {
		return collNegativador;
	}

	/**
	 * @param collNegativador
	 *            O collNegativador a ser setado.
	 */
	public void setCollNegativador(Collection collNegativador) {
		this.collNegativador = collNegativador;
	}

	/**
	 * @return Retorna o campo idNegativador.
	 */
	public String getIdNegativador() {
		return idNegativador;
	}

	/**
	 * @param idNegativador
	 *            O idNegativador a ser setado.
	 */
	public void setIdNegativador(String idNegativador) {
		this.idNegativador = idNegativador;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao
	 *            O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

}