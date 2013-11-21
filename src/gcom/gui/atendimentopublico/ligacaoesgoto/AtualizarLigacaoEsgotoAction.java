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
 * Rômulo Aurélio de Melo Souza Filho
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
package gcom.gui.atendimentopublico.ligacaoesgoto;

import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Leonardo Regis
 * @created 18 de Julho de 2006
 */
public class AtualizarLigacaoEsgotoAction extends GcomAction {

	/**
	 * Atualiza ligação de esgoto
	 * 
	 * [FS0005] Registrar Transação
	 * 
	 * @author Leonardo Regis
	 * @date 21/07/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta Retorno (Forward = Sucesso)
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Form
		AtualizarLigacaoEsgotoActionForm ligacaoEsgotoActionForm = (AtualizarLigacaoEsgotoActionForm) actionForm;

		// Fachada
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// [UC0107] Registrar Transação
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_LIGACAO_ESGOTO_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_LIGACAO_ESGOTO_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// [UC0107] -Fim- Registrar Transação

		// Cria objeto LigacaoEsgoto
		LigacaoEsgoto ligacaoEsgoto = null;

		// Filtra Imóvel
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
				ligacaoEsgotoActionForm.getMatriculaImovel()));
		Collection<Imovel> colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {
			Imovel imovel = (Imovel) colecaoImovel.iterator().next();

			// Seta no objeto informações do form
			ligacaoEsgoto = setLigacaoEsgoto(ligacaoEsgotoActionForm, imovel);

			// Seta novos valores da ligação de esgoto para validação
			imovel.setLigacaoEsgoto(ligacaoEsgoto);

			// Faz as validações de atualização de ligação de esgoto
			fachada.validarLigacaoEsgotoImovelAtualizar(imovel);
		} else {
			ligacaoEsgotoActionForm.setInscricaoImovel("Matrícula inexistente");
		}



		// Atualiza Ligação de Esgoto
		fachada.atualizarLigacaoEsgoto(ligacaoEsgoto,usuario);

		// Setando Operação
		ligacaoEsgoto.setOperacaoEfetuada(operacaoEfetuada);
		ligacaoEsgoto.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(ligacaoEsgoto);

		// [FS005] Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest,
				"Atualização da Ligação de Esgoto do imóvel "
						+ ligacaoEsgoto.getId() + " efetuada com sucesso!",
				"Atualizar Ligação de Esgoto efetuada",
				"exibirAtualizarLigacaoEsgotoAction.do?matriculaImovel="
						+ ligacaoEsgoto.getId());
		return retorno;
	}

	/**
	 * Seta no objeto LigacaoEsgoto as atualizações.
	 * 
	 * @author Leonardo Regis, Diogo Peixoto 
	 * @date 21/07/2006, 10/08/2011
	 * 
	 * @param ligacaoEsgotoActionForm
	 * @param imovel
	 * @return LigacaoEsgoto
	 */
	private LigacaoEsgoto setLigacaoEsgoto(AtualizarLigacaoEsgotoActionForm ligacaoEsgotoActionForm, Imovel imovel) {
		LigacaoEsgoto ligacaoEsgoto;
		
		// Seta objeto LigacaoEsgoto
		ligacaoEsgoto = imovel.getLigacaoEsgoto();
		ligacaoEsgoto.setImovel(imovel);
		ligacaoEsgoto.setUltimaAlteracao(ligacaoEsgotoActionForm.getDataConcorrencia());

		// Data de Ligacao
		String dataLigacao = ligacaoEsgotoActionForm.getDataLigacao();
		if (Util.verificarNaoVazio(dataLigacao)){
			ligacaoEsgoto.setDataLigacao(Util.converteStringParaDate(dataLigacao));
		}

		// Diâmetro
		LigacaoEsgotoDiametro ligacaoEsgotoDiametro = new LigacaoEsgotoDiametro();
		ligacaoEsgotoDiametro.setId(new Integer(ligacaoEsgotoActionForm.getDiametroLigacao()));
		ligacaoEsgoto.setLigacaoEsgotoDiametro(ligacaoEsgotoDiametro);
		
		// Material
		LigacaoEsgotoMaterial ligacaoEsgotoMaterialMaterial = new LigacaoEsgotoMaterial();
		ligacaoEsgotoMaterialMaterial.setId(new Integer(ligacaoEsgotoActionForm.getMaterialLigacao()));
		ligacaoEsgoto.setLigacaoEsgotoMaterial(ligacaoEsgotoMaterialMaterial);
		
		// Perfil
		LigacaoEsgotoPerfil ligacaoEsgotoPerfil = new LigacaoEsgotoPerfil();
		ligacaoEsgotoPerfil.setId(new Integer(ligacaoEsgotoActionForm.getPerfilLigacao()));
		ligacaoEsgoto.setLigacaoEsgotoPerfil(ligacaoEsgotoPerfil);

		// Percentual de Coleta
		String percentualColeta = ligacaoEsgotoActionForm.getPercentualColeta().replace(",", ".");
		ligacaoEsgoto.setPercentualAguaConsumidaColetada(new BigDecimal(percentualColeta));
		
		// Percentual de Esgoto
		if (Util.verificarNaoVazio(ligacaoEsgotoActionForm.getPercentualEsgoto())){
			String percentualEsgoto = ligacaoEsgotoActionForm.getPercentualEsgoto().replace(",", ".");
			ligacaoEsgoto.setPercentual(new BigDecimal(percentualEsgoto));
		} else {
			ligacaoEsgoto.setPercentual(null);
		}

		if (ligacaoEsgotoActionForm.getIdLigacaoOrigem() != null && 
				!ligacaoEsgotoActionForm.getIdLigacaoOrigem().equals(ConstantesSistema.NUMERO_NAO_INFORMADO) 
				&& !ligacaoEsgotoActionForm.getIdLigacaoOrigem().equals("")) {

			LigacaoOrigem ligacaoOrigem = new LigacaoOrigem();
			ligacaoOrigem.setId(new Integer(ligacaoEsgotoActionForm.getIdLigacaoOrigem()));
			ligacaoEsgoto.setLigacaoOrigem(ligacaoOrigem);

		}

		ligacaoEsgoto.setIndicadorCaixaGordura(new Short(ligacaoEsgotoActionForm.getIndicadorCaixaGordura()));
		ligacaoEsgoto.setIndicadorLigacaoEsgoto(new Short(ligacaoEsgotoActionForm.getIndicadorLigacao()));
		ligacaoEsgoto.setUltimaAlteracao(new Date());
		
		return ligacaoEsgoto;
	}
}
