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
package gcom.gui.cadastro.atualizacaocadastralsimplificado;

import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificado;
import gcom.cadastro.atualizacaocadastralsimplificado.FiltroAtualizacaoCadastralSimplificado;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Carrega os dados necessários e redireciona para a página que
 * exibe os detalhes da importação.
 * 
 * [UC0969] Importar arquivo de atualização cadastral simplificado
 * 
 * @author Samuel Valerio
 * 
 * @date 27/10/2009
 * 
 * 
 */
public class ExibirDetalhesAtualizacaoCadastralSimplificadoAction extends GcomAction {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward retorno = mapping.findForward("exibirDetalhesAtualizacaoCadastralSimplificado");
		
		String idArquivoTxt = request.getParameter("id");

		validarSeIdNaoEhNulo(idArquivoTxt);

		int idArquivoTxtInt = validarSeIdEhNumerico(idArquivoTxt);

		Fachada fachada = Fachada.getInstancia();

		AtualizacaoCadastralSimplificado arquivo = buscarArquivoTxt(idArquivoTxt, fachada);
		
		if (arquivo == null)
			throw new ActionServletException(
					"atencao.arquivo_texto_nao_encontrado_para_o_id_informado", null, idArquivoTxt);
		
		request.setAttribute("arquivo", arquivo);
		request.setAttribute("criticas", fachada.pesquisarAtualizacaoCadastralSimplificadoCritica(idArquivoTxtInt));
		
		return retorno;
	}

	/**
	 * Busca o arquivo txt no banco de dados.
	 *  
	 * @param idArquivoTxt Id do arquivo a ser procurado.
	 * @param fachada Fachada para invocar o método de busca.
	 * @return O arquivo texto correspondente ao id procurado.
	 */
	@SuppressWarnings("unchecked")
	private AtualizacaoCadastralSimplificado buscarArquivoTxt(String idArquivoTxt,
			Fachada fachada) {
		FiltroAtualizacaoCadastralSimplificado filtro = new FiltroAtualizacaoCadastralSimplificado();
		filtro.adicionarCaminhoParaCarregamentoEntidade("usuario");
		filtro.adicionarParametro(new ParametroSimples(
				FiltroAtualizacaoCadastralSimplificado.ID, idArquivoTxt));

		AtualizacaoCadastralSimplificado arquivo = (AtualizacaoCadastralSimplificado) Util
				.retonarObjetoDeColecao(fachada.pesquisar(filtro,
						AtualizacaoCadastralSimplificado.class.getName()));
		return arquivo;
	}

	/**
	 * Valida se o id do arquivo txt passado como parâmetro é numérico.
	 * Se for, retornar o valor convertido para inteiro. Caso contrário,
	 * lança uma exceção.
	 * 
	 * @param idArquivoTxt Id do arquivo txt.
	 * @return O valor convertido para inteiro.
	 */
	private int validarSeIdEhNumerico(String idArquivoTxt) {
		int idArquivoTxtInt = 0;
		try {
			idArquivoTxtInt = Integer.parseInt(idArquivoTxt);
		} catch (NumberFormatException nfe) {
			throw new ActionServletException(
					"atencao.arquivo_texto_id_deve_ser_numerico", null, idArquivoTxt);
		}
		return idArquivoTxtInt;
	}

	/**
	 * Valida se o id do arquivo txt informado como parâmetro não é nulo.
	 * Se for, lança uma exceção.
	 * 
	 * @param idArquivoTxt Id do arquivo txt a ser validado.
	 */
	private void validarSeIdNaoEhNulo(String idArquivoTxt) {
		if (idArquivoTxt == null)
			throw new ActionServletException(
					"atencao.arquivo_texto_id_deve_ser_informado");
	}
}
