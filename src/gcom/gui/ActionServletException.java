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
package gcom.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Representa um problema no nível do Action
 * 
 * @author rodrigo
 */
public class ActionServletException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> parametrosMensagem = new ArrayList<String>();
	
	private String parametroMensagem;
	
	private String urlBotaoVoltar;

	/**
	 * Construtor da classe ActionServletException
	 * 
	 * @param mensagem
	 *            Chave do erro que aconteceu no controlador(mensagem obtida num
	 *            arquivo de properties)
	 * @param excecaoCausa
	 *            Exceção que originou o problema
	 */
	public ActionServletException(String mensagem, Exception excecaoCausa) {
		super(mensagem, excecaoCausa);

	}

	/**
	 * Construtor da classe ActionServletException
	 * 
	 * @param mensagem
	 *            Descrição do parâmetro
	 */
	public ActionServletException(String mensagem) {
		super(mensagem, null);
	}

	/**
	 * Construtor da classe ActionServletException
	 * 
	 * @param mensagem
	 *            Descrição do parâmetro
	 * @param excecaoCausa
	 *            Descrição do parâmetro
	 * @param parametroMensagem
	 *            Descrição do parâmetro
	 */
	public ActionServletException(String mensagem, Exception excecaoCausa,
			String... parametroMensagem) {
		super(mensagem, excecaoCausa);
		parametrosMensagem.addAll(Arrays.asList(parametroMensagem));

	}

	public ActionServletException(String mensagem, Exception excecaoCausa,
			String parametroMensagem) {
		super(mensagem, excecaoCausa);
		this.parametroMensagem = parametroMensagem;

	}

	public ActionServletException(String mensagem, String parametroMensagem) {
		super(mensagem);
		this.parametroMensagem = parametroMensagem;

	}

	public ActionServletException(String mensagem, String... parametroMensagem) {
		super(mensagem);
		parametrosMensagem.addAll(Arrays.asList(parametroMensagem));
	}

	public ActionServletException(String mensagem, List<String> parametroMensagem) {
		super(mensagem);
		parametrosMensagem.addAll(parametroMensagem);
	}

	public List<String> getParametroMensagem() {
		ArrayList<String> list = new ArrayList<String>();
		list.addAll(parametrosMensagem);
		if (parametroMensagem != null && !parametroMensagem.trim().equals("")) {
			list.add(parametroMensagem);
		}
		return list;
	}

	public String getParametroMensagem(int numeroMensagem) {
		return getParametroMensagem().get(numeroMensagem);

	}

	public void setParametroMensagem(String... parametroMensagem) {
		parametrosMensagem.addAll(Arrays.asList(parametroMensagem));
	}

	/**
	 * Construtor da classe ActionServletException
	 * 
	 * @param mensagem
	 *            Descrição do parâmetro
	 * @param excecaoCausa
	 *            Descrição do parâmetro
	 * @param url botão voltar
	 *            Descrição do parâmetro
	 * @param parametroMensagem
	 *            Descrição do parâmetro
	 */
	public ActionServletException(String mensagem, String urlBotaoVoltar, 
			Exception excecaoCausa, String... parametroMensagem) {
		super(mensagem, excecaoCausa);
		this.urlBotaoVoltar = urlBotaoVoltar;
		parametrosMensagem.addAll(Arrays.asList(parametroMensagem));

	}

	public String getUrlBotaoVoltar() {
		return urlBotaoVoltar;
	}

	public void setUrlBotaoVoltar(String urlBotaoVoltar) {
		this.urlBotaoVoltar = urlBotaoVoltar;
	}
	
	
}
