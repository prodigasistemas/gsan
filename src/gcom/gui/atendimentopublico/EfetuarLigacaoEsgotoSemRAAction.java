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
package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
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

public class EfetuarLigacaoEsgotoSemRAAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = httpServletRequest.getSession(false);

		EfetuarLigacaoEsgotoSemRAActionForm efetuarLigacaoEsgotoSemRAActionForm = (EfetuarLigacaoEsgotoSemRAActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		LigacaoEsgoto ligacaoEsgoto = new LigacaoEsgoto();

		String matriculaImovel = efetuarLigacaoEsgotoSemRAActionForm.getMatriculaImovel();

		String materialLigacao = efetuarLigacaoEsgotoSemRAActionForm.getMaterialLigacao();
		String perfilLigacao = efetuarLigacaoEsgotoSemRAActionForm.getPerfilLigacao();
		String percentual = efetuarLigacaoEsgotoSemRAActionForm.getPercentualColeta().toString().replace(",", ".");
		String percentualEsgoto = efetuarLigacaoEsgotoSemRAActionForm.getPercentualEsgoto().toString().replace(",", ".");
		String dataLigacao = efetuarLigacaoEsgotoSemRAActionForm.getDataLigacao();
		String indicadorCaixaGordura = efetuarLigacaoEsgotoSemRAActionForm.getIndicadorCaixaGordura();
		String indicadorLigacaoEsgoto = efetuarLigacaoEsgotoSemRAActionForm.getIndicadorLigacao();
		String diametroLigacao = efetuarLigacaoEsgotoSemRAActionForm.getDiametroLigacao();

		if (matriculaImovel != null && !matriculaImovel.equals("")) {

			Imovel imovel = new Imovel();
			imovel.setId(new Integer(matriculaImovel));

			ligacaoEsgoto.setImovel(imovel);
			ligacaoEsgoto.setUltimaAlteracao(new Date());
			ligacaoEsgoto.setId(imovel.getId());

			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
			ligacaoEsgotoSituacao.setId(LigacaoEsgotoSituacao.LIGADO);
			
			if(indicadorCaixaGordura != null && !indicadorCaixaGordura.equals("")) {
				ligacaoEsgoto.setIndicadorCaixaGordura(new Short(indicadorCaixaGordura));
			} else {
				throw new ActionServletException("atencao.informe_campo_obrigatorio", null,
						"Caixa de Gordura");
			}
			
			if(indicadorLigacaoEsgoto != null && !indicadorLigacaoEsgoto.equals("")) {
				ligacaoEsgoto.setIndicadorLigacaoEsgoto(new Short(indicadorLigacaoEsgoto));
			} else {
				throw new ActionServletException("atencao.informe_campo_obrigatorio", null,
						"Ligação");
			}

			if (diametroLigacao != null
					&& !diametroLigacao.equals("")
					&& !diametroLigacao.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				LigacaoEsgotoDiametro ligacaoEsgotoDiametro = new LigacaoEsgotoDiametro();
				ligacaoEsgotoDiametro.setId(new Integer(diametroLigacao));
				ligacaoEsgoto.setLigacaoEsgotoDiametro(ligacaoEsgotoDiametro);
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Diametro da Ligação");
			}

			if (materialLigacao != null
					&& !materialLigacao.equals("")
					&& !materialLigacao.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				LigacaoEsgotoMaterial ligacaoEsgotoMaterialMaterial = new LigacaoEsgotoMaterial();
				ligacaoEsgotoMaterialMaterial
						.setId(new Integer(materialLigacao));
				ligacaoEsgoto
						.setLigacaoEsgotoMaterial(ligacaoEsgotoMaterialMaterial);
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Material da Ligação");
			}

			if (perfilLigacao != null
					&& !perfilLigacao.equals("")
					&& !perfilLigacao.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				LigacaoEsgotoPerfil ligacaoEsgotoPerfil = new LigacaoEsgotoPerfil();
				ligacaoEsgotoPerfil.setId(new Integer(perfilLigacao));
				ligacaoEsgoto.setLigacaoEsgotoPerfil(ligacaoEsgotoPerfil);
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Perfil da Ligação");
			}

			if (percentual != null && !percentual.equals("")) {

				BigDecimal percentualInformadoColeta = new BigDecimal(
						percentual);
				if (percentualInformadoColeta != null
						&& !percentualInformadoColeta.equals("")
						&& (percentualInformadoColeta.intValue() <= ConstantesSistema.NUMERO_MAXIMO_CONSUMO_MINIMO_FIXADO)) {
					ligacaoEsgoto
							.setPercentualAguaConsumidaColetada(percentualInformadoColeta);
				}
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Percentual de Coleta");
			}

			if (percentualEsgoto != null && !percentualEsgoto.equals("")) {

				BigDecimal percentualEsgotoColeta = new BigDecimal(percentualEsgoto);
				ligacaoEsgoto.setPercentual(percentualEsgotoColeta);
			}

			if (dataLigacao != null && !dataLigacao.equals("")) {
				ligacaoEsgoto.setDataLigacao(Util.converteStringParaDate(dataLigacao));
				if (ligacaoEsgoto.getDataLigacao().after(new Date())) {
					throw new ActionServletException(
							"atencao.data_menor_que_atual", null, "Ligação");
				}
			}

			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_LIGACAO_ESGOTO__SEM_RA_EFETUAR,
					imovel.getId(), imovel.getId(),
					new UsuarioAcaoUsuarioHelper(usuario,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			registradorOperacao.registrarOperacao(ligacaoEsgoto);
			
			fachada.atualizarImovelExecucaoOrdemServicoLigacaoEsgoto(imovel,
					ligacaoEsgotoSituacao);

			FiltroLigacaoEsgoto filtroLigacaoEsgoto = new FiltroLigacaoEsgoto();
			filtroLigacaoEsgoto.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgoto.ID, imovel.getId()));
			Collection colecaoLigacaoEsgotoBase = fachada.pesquisar(
					filtroLigacaoEsgoto, LigacaoEsgoto.class.getName());

			if (colecaoLigacaoEsgotoBase != null
					&& !colecaoLigacaoEsgotoBase.isEmpty()) {
				fachada.atualizar(ligacaoEsgoto);
			} else {
				fachada.inserir(ligacaoEsgoto);
			}

			montarPaginaSucesso(httpServletRequest,
					"Ligação de Esgoto sem RA efetuada com Sucesso",
					"Efetuar outra Ligação de Esgoto sem RA",
					"exibirEfetuarLigacaoEsgotoSemRAAction.do?menu=sim");

			return retorno;
		} else {
			throw new ActionServletException(
					"atencao.informe_campo_obrigatorio", null,
					"Matrícula Imóvel");
		}
	}

}
