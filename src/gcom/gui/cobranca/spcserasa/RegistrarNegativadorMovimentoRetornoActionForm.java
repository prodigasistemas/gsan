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
public class RegistrarNegativadorMovimentoRetornoActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private String negativador;

	private String dataProcessamento;

	private String horaProcessamento;

	private String numeroSequencialArquivo;

	private String totalRegistrosArquivo;


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

		this.negativador="";

		this.dataProcessamento="";

		this.horaProcessamento="";

		this.numeroSequencialArquivo="";

		this.totalRegistrosArquivo="";

	}


	/**
	 * @return Retorna o campo dataProcessamento.
	 */
	public String getDataProcessamento() {
		return dataProcessamento;
	}


	/**
	 * @param dataProcessamento O dataProcessamento a ser setado.
	 */
	public void setDataProcessamento(String dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}


	/**
	 * @return Retorna o campo horaProcessamento.
	 */
	public String getHoraProcessamento() {
		return horaProcessamento;
	}


	/**
	 * @param horaProcessamento O horaProcessamento a ser setado.
	 */
	public void setHoraProcessamento(String horaProcessamento) {
		this.horaProcessamento = horaProcessamento;
	}


	/**
	 * @return Retorna o campo negativador.
	 */
	public String getNegativador() {
		return negativador;
	}


	/**
	 * @param negativador O negativador a ser setado.
	 */
	public void setNegativador(String negativador) {
		this.negativador = negativador;
	}


	/**
	 * @return Retorna o campo numeroSequencialArquivo.
	 */
	public String getNumeroSequencialArquivo() {
		return numeroSequencialArquivo;
	}


	/**
	 * @param numeroSequencialArquivo O numeroSequencialArquivo a ser setado.
	 */
	public void setNumeroSequencialArquivo(String numeroSequencialArquivo) {
		this.numeroSequencialArquivo = numeroSequencialArquivo;
	}


	/**
	 * @return Retorna o campo totalRegistrosArquivo.
	 */
	public String getTotalRegistrosArquivo() {
		return totalRegistrosArquivo;
	}


	/**
	 * @param totalRegistrosArquivo O totalRegistrosArquivo a ser setado.
	 */
	public void setTotalRegistrosArquivo(String totalRegistrosArquivo) {
		this.totalRegistrosArquivo = totalRegistrosArquivo;
	}

	
}