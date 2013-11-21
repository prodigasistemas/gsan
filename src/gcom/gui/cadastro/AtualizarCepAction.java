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
package gcom.gui.cadastro;

import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.CepTipo;
import gcom.cadastro.endereco.FiltroCepTipo;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarCepAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando for implementado o esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarCepActionForm atualizarCepActionForm = (AtualizarCepActionForm) actionForm;

		Cep cep = (Cep) sessao.getAttribute("atualizarCep");
		
		Collection colecaoPesquisa = null;
		
        String codigo = Util.retirarFormatacaoCEP(atualizarCepActionForm.getCodigo());
        String cepTipo = atualizarCepActionForm.getCepTipo();    
        String municipio = atualizarCepActionForm.getMunicipio();
        String bairro = atualizarCepActionForm.getBairro();
        String LogradouroTipo = atualizarCepActionForm.getLogradouroTipo();
        String logradouro = atualizarCepActionForm.getLogradouro();
        String indicadordeUso = atualizarCepActionForm.getIndicadorUso();
        
        //Municipio é obrigatório.
        
        if (municipio == null || municipio.equalsIgnoreCase("")) {
            throw new ActionServletException("atencao.required", null, "Município");
        }
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

		filtroMunicipio.adicionarParametro(new ParametroSimples(
				FiltroMunicipio.NOME, municipio));

		filtroMunicipio.adicionarParametro(new ParametroSimples(
				FiltroMunicipio.INDICADOR_USO,
		        ConstantesSistema.INDICADOR_USO_ATIVO));

		//Retorna Municipio
		colecaoPesquisa = fachada.pesquisar(filtroMunicipio,
				Municipio.class.getName());

		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
		    throw new ActionServletException(
		            "atencao.pesquisa.municipio_inexistente");
		}
        
        //Bairro é obrigatório.
        if (bairro == null || bairro.equalsIgnoreCase("")) {
            throw new ActionServletException("atencao.required", null, "Bairro");
        }
		FiltroBairro filtroBairro = new FiltroBairro();
		
		String bairroId = atualizarCepActionForm.getBairroId();
		String municipioId = atualizarCepActionForm.getMunicipioId();
		
		filtroBairro.adicionarParametro(new ParametroSimples(
				FiltroBairro.CODIGO, bairroId));
		filtroBairro.adicionarParametro(new ParametroSimples(
				FiltroBairro.NOME,bairro));
		filtroBairro.adicionarParametro(new ParametroSimples(
				FiltroBairro.MUNICIPIO_ID, municipioId));
		filtroBairro.adicionarParametro(new ParametroSimples(
				FiltroBairro.INDICADOR_USO,
		        ConstantesSistema.INDICADOR_USO_ATIVO));

		//Retorna Bairro
		colecaoPesquisa = fachada.pesquisar(filtroBairro,
				Bairro.class.getName());

		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
		    throw new ActionServletException(
		            "atencao.pesquisa.bairro_inexistente");
		}
        
       	// Atualiza o Codigo
        cep.setCodigo(new Integer(codigo));
        
        // Atualiza o Bairro
        cep.setBairro(bairro);	

		// Verifica se o Tipo de CEP foi passado
		if (cepTipo != null
				&& !cepTipo.equalsIgnoreCase("")) {

			FiltroCepTipo filtroCepTipo = new FiltroCepTipo();

			filtroCepTipo.adicionarParametro(new ParametroSimples(
					FiltroCepTipo.ID, cepTipo));
			filtroCepTipo.adicionarParametro(new ParametroSimples(
					FiltroCepTipo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			// Retorna Tipo de CEP
			colecaoPesquisa = fachada.pesquisar(filtroCepTipo,
					CepTipo.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ActionServletException(
						"atencao.cep_tipo_inexistente");
			}
			CepTipo objetoCepTipo = (CepTipo) Util
					.retonarObjetoDeColecao(colecaoPesquisa);
			cep.setCepTipo(objetoCepTipo);
		}
        
        
        
        // Atualiza o Municipio
        cep.setMunicipio(municipio);
        
        //Atualiza o Logradouro
        cep.setLogradouro(logradouro);
        
        //Atualiza o Logradouro Tipo
        cep.setDescricaoTipoLogradouro(LogradouroTipo);
        
        // Atualiza o indicador de uso
        cep.setIndicadorUso(new Short(indicadordeUso));
		
	   	// Seta a data da alteração
	    cep.setUltimaAlteracao( new Date() );	
	    
		fachada.atualizar(cep);
	
		montarPaginaSucesso(httpServletRequest, "CEP "
				+ Util.formatarCEP(codigo) + " atualizado com sucesso.",
				"Realizar outra Manutenção de CEP ",
				"exibirFiltrarCepAction.do?menu=sim");        
	    
		return retorno;
	}
}
