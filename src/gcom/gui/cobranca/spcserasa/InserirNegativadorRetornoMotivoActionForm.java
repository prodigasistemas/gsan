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
 * Action form responsável pelas propiedades do caso de uso 
 * [UC0662] Inserir Motivo de Retorno do Registro do Negativador
 * 
 * @author Thiago Vieira
 * @date 03/01/2008
 */
public class InserirNegativadorRetornoMotivoActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private String idNegativador;

	private Collection colecaoNegativador;
	
	private String codigoMotivo;

	private String descricaoRetornoMotivo;

	private String indicadorUso;

	private String indicadorRegistroAceito;

	private String ultimaAlteracao;
	
	private String corCodigoMotivo;
	
	private String mensagemCodigoMotivo;
	
	private String okCodigoMotivo;

	//private String negativadorMovimentoRegRetMot;

	public void reset(ActionMapping arg0, ServletRequest arg1) {
		super.reset(arg0, arg1);

		this.idNegativador = "";
		this.colecaoNegativador = new ArrayList();
		this.codigoMotivo ="";
		this.descricaoRetornoMotivo = "";
		this.indicadorUso = "";
		this.indicadorRegistroAceito = "2";
		this.ultimaAlteracao = "";
		this.corCodigoMotivo = "";
		this.mensagemCodigoMotivo = "";
		this.okCodigoMotivo = "";
	}

	/**
	 * @return Retorna o campo colecaoNegativador.
	 */
	public Collection getColecaoNegativador() {
		return colecaoNegativador;
	}

	/**
	 * @return Retorna o campo corCodigoMotivo.
	 */
	public String getCorCodigoMotivo() {
		return corCodigoMotivo;
	}

	/**
	 * @param corCodigoMotivo O corCodigoMotivo a ser setado.
	 */
	public void setCorCodigoMotivo(String corCodigoMotivo) {
		this.corCodigoMotivo = corCodigoMotivo;
	}

	/**
	 * @return Retorna o campo mensagemCodigoMotivo.
	 */
	public String getMensagemCodigoMotivo() {
		return mensagemCodigoMotivo;
	}

	/**
	 * @param mensagemCodigoMotivo O mensagemCodigoMotivo a ser setado.
	 */
	public void setMensagemCodigoMotivo(String mensagemCodigoMotivo) {
		this.mensagemCodigoMotivo = mensagemCodigoMotivo;
	}

	/**
	 * @param colecaoNegativador O colecaoNegativador a ser setado.
	 */
	public void setColecaoNegativador(Collection colecaoNegativador) {
		this.colecaoNegativador = colecaoNegativador;
	}

	/**
	 * @return Retorna o campo descricaoRetornoCodigo.
	 */
	public String getDescricaoRetornoMotivo() {
		return descricaoRetornoMotivo;
	}

	/**
	 * @param descricaoRetornoCodigo O descricaoRetornoCodigo a ser setado.
	 */
	public void setDescricaoRetornoMotivo(String descricaoRetornoMotivo) {
		this.descricaoRetornoMotivo = descricaoRetornoMotivo;
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
	 * @return Retorna o campo codigoMotivo.
	 */
	public String getCodigoMotivo() {
		return codigoMotivo;
	}

	/**
	 * @param codigoMotivo O codigoMotivo a ser setado.
	 */
	public void setCodigoMotivo(String codigoMotivo) {
		this.codigoMotivo = codigoMotivo;
	}

	/**
	 * @return Retorna o campo indicadorRegistroAceito.
	 */
	public String getIndicadorRegistroAceito() {
		return indicadorRegistroAceito;
	}

	/**
	 * @param indicadorRegistroAceito O indicadorRegistroAceito a ser setado.
	 */
	public void setIndicadorRegistroAceito(String indicadorRegistroAceito) {
		this.indicadorRegistroAceito = indicadorRegistroAceito;
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
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * @return Retorna o campo okCodigoMotivo.
	 */
	public String getOkCodigoMotivo() {
		return okCodigoMotivo;
	}

	/**
	 * @param okCodigoMotivo O okCodigoMotivo a ser setado.
	 */
	public void setOkCodigoMotivo(String okCodigoMotivo) {
		this.okCodigoMotivo = okCodigoMotivo;
	}

	

}