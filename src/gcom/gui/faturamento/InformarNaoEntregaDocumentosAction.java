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
package gcom.gui.faturamento;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroCobrancaDocumento;
import gcom.cobranca.FiltroDocumentoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.DocumentoNaoEntregue;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.conta.Fatura;
import gcom.faturamento.conta.FiltroFatura;
import gcom.faturamento.conta.MotivoNaoEntregaDocumento;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action utilizado para Informar a Não Entrega de Documentos no banco
 * 
 * [UC0559] Informar Não Entrega de Documentos Permite informar uma Não Entrega
 * de Documentos
 * 
 * @author Thiago Tenório
 * @since 03/04/2007
 */
public class InformarNaoEntregaDocumentosAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {


		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute(Usuario.USUARIO_LOGADO);

		InformarNaoEntregaDocumentosActionForm informarNaoEntregaDocumentosActionForm = (InformarNaoEntregaDocumentosActionForm) actionForm;

		String idResponsavelEntrega = informarNaoEntregaDocumentosActionForm.getIdResponsavelEntrega();

		String tipoDocumento = informarNaoEntregaDocumentosActionForm
				.getTipoDocumento();
		String mesAnoConta = informarNaoEntregaDocumentosActionForm
				.getMesAnoConta();
		String idCodigo1 = informarNaoEntregaDocumentosActionForm
				.getIdCodigo1();
		String idCodigo2 = informarNaoEntregaDocumentosActionForm
				.getIdCodigo2();
		String idCodigo3 = informarNaoEntregaDocumentosActionForm
				.getIdCodigo3();
		String idCodigo4 = informarNaoEntregaDocumentosActionForm
				.getIdCodigo4();
		String idCodigo5 = informarNaoEntregaDocumentosActionForm
				.getIdCodigo5();
		String idCodigo6 = informarNaoEntregaDocumentosActionForm
				.getIdCodigo6();
		String idCodigo7 = informarNaoEntregaDocumentosActionForm
				.getIdCodigo7();
		String idCodigo8 = informarNaoEntregaDocumentosActionForm
				.getIdCodigo8();
		String idCodigo9 = informarNaoEntregaDocumentosActionForm
				.getIdCodigo9();
		String idCodigo10 = informarNaoEntregaDocumentosActionForm
				.getIdCodigo10();
		String idCodigo11 = informarNaoEntregaDocumentosActionForm
				.getIdCodigo11();
		String idCodigo12 = informarNaoEntregaDocumentosActionForm
				.getIdCodigo12();
		String idCodigo13 = informarNaoEntregaDocumentosActionForm
				.getIdCodigo13();
		String idCodigo14 = informarNaoEntregaDocumentosActionForm
				.getIdCodigo14();
		String idCodigo15 = informarNaoEntregaDocumentosActionForm
				.getIdCodigo15();
		String idCodigo16 = informarNaoEntregaDocumentosActionForm
				.getIdCodigo16();
		String idCodigo17 = informarNaoEntregaDocumentosActionForm
				.getIdCodigo17();
		String idCodigo18 = informarNaoEntregaDocumentosActionForm
				.getIdCodigo18();
		String idCodigo19 = informarNaoEntregaDocumentosActionForm
				.getIdCodigo19();
		String idCodigo20 = informarNaoEntregaDocumentosActionForm
				.getIdCodigo20();

		DocumentoTipo documentoTipo = new DocumentoTipo();
		
		if (Util.isCampoComboboxInformado(tipoDocumento)) {
			
			documentoTipo = consultarTipoDocumento(informarNaoEntregaDocumentosActionForm);			
		}

		Date dataDevolucaoFormatada = Util.converteStringParaDate(
				informarNaoEntregaDocumentosActionForm.getDataDevolucao());

		// Cria um Cliente que será setado no Responsável pela Entrega os
		// valores informados pelo
		// usuário na pagina para ser inserido no banco

		Cliente cliente = null;
		
		if ( Util.verificarNaoVazio(idResponsavelEntrega)) {		

			cliente = new Cliente();
			cliente.setId(new Integer(
					informarNaoEntregaDocumentosActionForm.getIdResponsavelEntrega()));
		}
		
		Collection<DocumentoNaoEntregue> colecaoDocumentosNaoEntregues = new ArrayList<DocumentoNaoEntregue>();
		Integer contador = 0;
		
		if (idCodigo1 != null && !idCodigo1.equals("-1")) {

			montarDocumentoNaoEntregue(idCodigo1,informarNaoEntregaDocumentosActionForm.getQtTentativas1(),
					documentoTipo,informarNaoEntregaDocumentosActionForm.getMatriculaOuNumeroDocumento1(), 
					mesAnoConta,dataDevolucaoFormatada, cliente,colecaoDocumentosNaoEntregues);
			
			contador = contador +1;
		}

		if (idCodigo2 != null && !idCodigo2.equals("-1")) {

			montarDocumentoNaoEntregue(idCodigo2,
					informarNaoEntregaDocumentosActionForm.getQtTentativas2(),
					documentoTipo,
					informarNaoEntregaDocumentosActionForm
							.getMatriculaOuNumeroDocumento2(), mesAnoConta,
					dataDevolucaoFormatada, cliente,
					colecaoDocumentosNaoEntregues);
			contador = contador +1;
		}
		
		// Tabela 3
		// Cria todos os campos a serem setados no banco e que serão setados na classe Não entrega de Documentos
		// os
		// valores informados pelo
		// usuário na pagina para ser inserido no banco

		if (idCodigo3 != null && !idCodigo3.equals("-1")) {

			montarDocumentoNaoEntregue(idCodigo3,
					informarNaoEntregaDocumentosActionForm.getQtTentativas3(),
					documentoTipo, 
					informarNaoEntregaDocumentosActionForm
							.getMatriculaOuNumeroDocumento3(), mesAnoConta,
					dataDevolucaoFormatada, cliente,
					colecaoDocumentosNaoEntregues);
			contador = contador +1;
		}

		// Tabela 4
		// Cria todos os campos a serem setados no banco e que serão setados na classe Não entrega de Documentos
		// os
		// valores informados pelo
		// usuário na pagina para ser inserido no banco

		if (idCodigo4 != null && !idCodigo4.equals("-1")) {

			montarDocumentoNaoEntregue(idCodigo4,
					informarNaoEntregaDocumentosActionForm.getQtTentativas4(),
					documentoTipo, 
					informarNaoEntregaDocumentosActionForm
							.getMatriculaOuNumeroDocumento4(), mesAnoConta,
					dataDevolucaoFormatada, cliente,
					colecaoDocumentosNaoEntregues);
			contador = contador +1;
		}

		// Tabela 5
		// Cria todos os campos a serem setados no banco e que serão setados na classe Não entrega de Documentos
		// os
		// valores informados pelo
		// usuário na pagina para ser inserido no banco

		if (idCodigo5 != null && !idCodigo5.equals("-1")) {

			montarDocumentoNaoEntregue(idCodigo5,
					informarNaoEntregaDocumentosActionForm.getQtTentativas5(),
					documentoTipo, 
					informarNaoEntregaDocumentosActionForm
							.getMatriculaOuNumeroDocumento5(), mesAnoConta,
					dataDevolucaoFormatada, cliente,
					colecaoDocumentosNaoEntregues);
			contador = contador +1;
			
		}
		// Tabela 6
		// Cria todos os campos a serem setados no banco e que serão setados na classe Não entrega de Documentos
		// os
		// valores informados pelo
		// usuário na pagina para ser inserido no banco

		if (idCodigo6 != null && !idCodigo6.equals("-1")) {

			montarDocumentoNaoEntregue(idCodigo6,
					informarNaoEntregaDocumentosActionForm.getQtTentativas6(),
					documentoTipo, 
					informarNaoEntregaDocumentosActionForm
							.getMatriculaOuNumeroDocumento6(), mesAnoConta,
					dataDevolucaoFormatada, cliente,
					colecaoDocumentosNaoEntregues);
			contador = contador +1;
		}
		// Tabela 7
		// Cria todos os campos a serem setados no banco e que serão setados na classe Não entrega de Documentos
		// os
		// valores informados pelo
		// usuário na pagina para ser inserido no banco
		
		if (idCodigo7 != null && !idCodigo7.equals("-1")) {

			montarDocumentoNaoEntregue(idCodigo7,
					informarNaoEntregaDocumentosActionForm.getQtTentativas7(),
					documentoTipo, 
					informarNaoEntregaDocumentosActionForm
							.getMatriculaOuNumeroDocumento7(), mesAnoConta,
					dataDevolucaoFormatada, cliente,
					colecaoDocumentosNaoEntregues);
			contador = contador +1;
		}
		// Tabela 8
		// Cria todos os campos a serem setados no banco e que serão setados na classe Não entrega de Documentos
		// os
		// valores informados pelo
		// usuário na pagina para ser inserido no banco

		if (idCodigo8 != null && !idCodigo8.equals("-1")) {

			montarDocumentoNaoEntregue(idCodigo8,
					informarNaoEntregaDocumentosActionForm.getQtTentativas8(),
					documentoTipo, 
					informarNaoEntregaDocumentosActionForm
							.getMatriculaOuNumeroDocumento8(), mesAnoConta,
					dataDevolucaoFormatada, cliente,
					colecaoDocumentosNaoEntregues);
			contador = contador +1;
		}
		// Tabela 9
		// Cria todos os campos a serem setados no banco e que serão setados na classe Não entrega de Documentos
		// os
		// valores informados pelo
		// usuário na pagina para ser inserido no banco

		if (idCodigo9 != null && !idCodigo9.equals("-1")) {

			montarDocumentoNaoEntregue(idCodigo9,
					informarNaoEntregaDocumentosActionForm.getQtTentativas9(),
					documentoTipo, 
					informarNaoEntregaDocumentosActionForm
							.getMatriculaOuNumeroDocumento9(), mesAnoConta,
					dataDevolucaoFormatada, cliente,
					colecaoDocumentosNaoEntregues);
			contador = contador +1;
		}
		// Tabela 10
		// Cria todos os campos a serem setados no banco e que serão setados na classe Não entrega de Documentos
		// os
		// valores informados pelo
		// usuário na pagina para ser inserido no banco

		if (idCodigo10 != null && !idCodigo10.equals("-1")) {

			montarDocumentoNaoEntregue(idCodigo10,
					informarNaoEntregaDocumentosActionForm.getQtTentativas10(),
					documentoTipo, 
					informarNaoEntregaDocumentosActionForm
							.getMatriculaOuNumeroDocumento10(), mesAnoConta,
					dataDevolucaoFormatada, cliente,
					colecaoDocumentosNaoEntregues);
			contador = contador +1;
		}

		// Tabela 11
		// Cria todos os campos a serem setados no banco e que serão setados na classe Não entrega de Documentos
		// os
		// valores informados pelo
		// usuário na pagina para ser inserido no banco

		if (idCodigo11 != null && !idCodigo11.equals("-1")) {

			montarDocumentoNaoEntregue(idCodigo11,
					informarNaoEntregaDocumentosActionForm.getQtTentativas11(),
					documentoTipo, 
					informarNaoEntregaDocumentosActionForm
							.getMatriculaOuNumeroDocumento11(), mesAnoConta,
					dataDevolucaoFormatada, cliente,
					colecaoDocumentosNaoEntregues);
			contador = contador +1;
		}
		// Tabela 12
		// Cria todos os campos a serem setados no banco e que serão setados na classe Não entrega de Documentos
		// os
		// valores informados pelo
		// usuário na pagina para ser inserido no banco

		if (idCodigo12 != null && !idCodigo12.equals("-1")) {

			montarDocumentoNaoEntregue(idCodigo12,
					informarNaoEntregaDocumentosActionForm.getQtTentativas12(),
					documentoTipo, 
					informarNaoEntregaDocumentosActionForm
							.getMatriculaOuNumeroDocumento12(), mesAnoConta,
					dataDevolucaoFormatada, cliente,
					colecaoDocumentosNaoEntregues);
			contador = contador +1;
		}
		// Tabela 13
		// Cria todos os campos a serem setados no banco e que serão setados na classe Não entrega de Documentos
		// os
		// valores informados pelo
		// usuário na pagina para ser inserido no banco
		
		if (idCodigo13 != null && !idCodigo13.equals("-1")) {

			montarDocumentoNaoEntregue(idCodigo13,
					informarNaoEntregaDocumentosActionForm.getQtTentativas13(),
					documentoTipo, 
					informarNaoEntregaDocumentosActionForm
							.getMatriculaOuNumeroDocumento13(), mesAnoConta,
					dataDevolucaoFormatada, cliente,
					colecaoDocumentosNaoEntregues);
			contador = contador +1;
		}
		// Tabela 14
		// Cria todos os campos a serem setados no banco e que serão setados na classe Não entrega de Documentos
		// os
		// valores informados pelo
		// usuário na pagina para ser inserido no banco

		if (idCodigo14 != null && !idCodigo14.equals("-1")) {

			montarDocumentoNaoEntregue(idCodigo14,
					informarNaoEntregaDocumentosActionForm.getQtTentativas14(),
					documentoTipo, 
					informarNaoEntregaDocumentosActionForm
							.getMatriculaOuNumeroDocumento14(), mesAnoConta,
					dataDevolucaoFormatada, cliente,
					colecaoDocumentosNaoEntregues);
			contador = contador +1;
		}
		// Tabela 15
		// Cria todos os campos a serem setados no banco e que serão setados na classe Não entrega de Documentos
		// os
		// valores informados pelo
		// usuário na pagina para ser inserido no banco

		if (idCodigo15 != null && !idCodigo15.equals("-1")) {

			montarDocumentoNaoEntregue(idCodigo15,
					informarNaoEntregaDocumentosActionForm.getQtTentativas15(),
					documentoTipo, 
					informarNaoEntregaDocumentosActionForm
							.getMatriculaOuNumeroDocumento15(), mesAnoConta,
					dataDevolucaoFormatada, cliente,
					colecaoDocumentosNaoEntregues);
			contador = contador +1;
		}
		// Tabela 16
		// Cria todos os campos a serem setados no banco e que serão setados na classe Não entrega de Documentos
		// os
		// valores informados pelo
		// usuário na pagina para ser inserido no banco
		
		if (idCodigo16 != null && !idCodigo16.equals("-1")) {

			montarDocumentoNaoEntregue(idCodigo16,
					informarNaoEntregaDocumentosActionForm.getQtTentativas16(),
					documentoTipo, 
					informarNaoEntregaDocumentosActionForm
							.getMatriculaOuNumeroDocumento16(), mesAnoConta,
					dataDevolucaoFormatada, cliente,
					colecaoDocumentosNaoEntregues);
			contador = contador +1;
		}
		// Tabela 17
		// Cria todos os campos a serem setados no banco e que serão setados na classe Não entrega de Documentos
		// os
		// valores informados pelo
		// usuário na pagina para ser inserido no banco
		
		if (idCodigo17 != null && !idCodigo17.equals("-1")) {

			montarDocumentoNaoEntregue(idCodigo17,
					informarNaoEntregaDocumentosActionForm.getQtTentativas17(),
					documentoTipo, 
					informarNaoEntregaDocumentosActionForm
							.getMatriculaOuNumeroDocumento17(), mesAnoConta,
					dataDevolucaoFormatada, cliente,
					colecaoDocumentosNaoEntregues);
			contador = contador +1;
		}
		// Tabela 18
		// Cria todos os campos a serem setados no banco e que serão setados na classe Não entrega de Documentos
		// os
		// valores informados pelo
		// usuário na pagina para ser inserido no banco

		if (idCodigo18 != null && !idCodigo18.equals("-1")) {

			montarDocumentoNaoEntregue(idCodigo18,
					informarNaoEntregaDocumentosActionForm.getQtTentativas18(),
					documentoTipo, 
					informarNaoEntregaDocumentosActionForm
							.getMatriculaOuNumeroDocumento18(), mesAnoConta,
					dataDevolucaoFormatada, cliente,
					colecaoDocumentosNaoEntregues);
			contador = contador +1;
		}
		// Tabela 19
		// Cria todos os campos a serem setados no banco e que serão setados na classe Não entrega de Documentos
		// os
		// valores informados pelo
		// usuário na pagina para ser inserido no banco

		if (idCodigo19 != null && !idCodigo19.equals("-1")) {

			montarDocumentoNaoEntregue(idCodigo19,
					informarNaoEntregaDocumentosActionForm.getQtTentativas19(),
					documentoTipo, 
					informarNaoEntregaDocumentosActionForm
							.getMatriculaOuNumeroDocumento19(), mesAnoConta,
					dataDevolucaoFormatada, cliente,
					colecaoDocumentosNaoEntregues);
			contador = contador +1;
		}
		// Tabela 20
		// Cria todos os campos a serem setados no banco e que serão setados na classe Não entrega de Documentos
		// os
		// valores informados pelo
		// usuário na pagina para ser inserido no banco

		if (idCodigo20 != null && !idCodigo20.equals("-1")) {

			montarDocumentoNaoEntregue(idCodigo20,
					informarNaoEntregaDocumentosActionForm.getQtTentativas20(),
					documentoTipo, 
					informarNaoEntregaDocumentosActionForm
							.getMatriculaOuNumeroDocumento20(), mesAnoConta,
					dataDevolucaoFormatada, cliente,
					colecaoDocumentosNaoEntregues);
			
			contador = contador +1;
		}
		
		if( !Util.isVazioOrNulo(colecaoDocumentosNaoEntregues)){
			
			verificarDocumentoRepetido(documentoTipo,colecaoDocumentosNaoEntregues);
		}

		// Informar um Documento Não Entregue na base, mas fazendo, antes,
		// algumas verificações no ControladorFaturamento.
		
		fachada.informarNaoEntregaDocumentos(colecaoDocumentosNaoEntregues,usuarioLogado);

		// Exibe a página de sucesso
		montarPaginaSucesso(httpServletRequest, 
				+ contador
				+ " Documento(s) Não Entregue(s) Informado(s) com Sucesso.",
				"Informar outro Documentos Não Entregues",
				"exibirInformarNaoEntregaDocumentosAction.do?menu=sim");

		return actionMapping.findForward("telaSucesso");

	}

	/**
	 * O método itera sob a coleção de documentos não entegues
	 * verificando se o usuário informou o mesmo documento mais de uma vez,
	 * seja ele com o mesmo motivo ou não, e caso seja verdade lançará uma exceção.
	 * 
	 * OBS: Para identificar que não se está comprando o mesmo objeto
	 * compara-se pela data de ultima alteração, pois ela é obtida com um (new Date()),
	 * sendo assim, o único campo que possibilita essa distinção via código.
	 *
	 *@since 13/10/2009
	 *@author Marlon Patrick
	 */
	private void verificarDocumentoRepetido(DocumentoTipo documentoTipo,
			Collection<DocumentoNaoEntregue> colecaoDocumentosNaoEntregues) {
		
		boolean isDocumentoRepetido = false;
		
		for(DocumentoNaoEntregue documento : colecaoDocumentosNaoEntregues){
			for(DocumentoNaoEntregue documentoTemp : colecaoDocumentosNaoEntregues){
				
					if (documentoTipo.getId().equals(DocumentoTipo.CONTA)) {
						
						if(!documento.getUltimaAlteracao().equals(documentoTemp.getUltimaAlteracao())){
							if(documento.getContaGeral().getConta().getId().equals(
									documentoTemp.getContaGeral().getConta().getId())){

								isDocumentoRepetido = true;
							}							
						}
												
					} else if (documentoTipo.getId().equals(DocumentoTipo.GUIA_PAGAMENTO)) {
				
						if(!documento.getUltimaAlteracao().equals(documentoTemp.getUltimaAlteracao())){
							if(documento.getGuiaPagamentoGeral().getGuiaPagamento().getId().equals(
									documentoTemp.getGuiaPagamentoGeral().getGuiaPagamento().getId())){

								isDocumentoRepetido = true;
							}							
						}
					
					} else if (documentoTipo.getId().equals(DocumentoTipo.FATURA_CLIENTE)) {
					
						if(!documento.getUltimaAlteracao().equals(documentoTemp.getUltimaAlteracao())){
							if(documento.getFatura().getId().equals(
									documentoTemp.getFatura().getId())){

								isDocumentoRepetido = true;
							}							
						}
					
					} else {
					
						if(!documento.getUltimaAlteracao().equals(documentoTemp.getUltimaAlteracao())){
							if(documento.getCobrancaDocumento().getId().equals(
									documentoTemp.getCobrancaDocumento().getId())){

								isDocumentoRepetido = true;
							}													
						}
					} 
				}
			
			if(isDocumentoRepetido){
				
				throw new ActionServletException("atencao.informar_documentos_nao_entregues.documento_repetido");
			}
		}
	}

	/**
	 * Método responsável por consultar o Tipo de Documento info0rmado pelo usuário.
	 *
	 *@since 13/10/2009
	 *@author Marlon Patrick
	 */
	private DocumentoTipo consultarTipoDocumento(InformarNaoEntregaDocumentosActionForm form) {
		
		String tipoDocumento = form.getTipoDocumento();

		FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();

		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.ID, tipoDocumento));

		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<DocumentoTipo> colecaoDocumentoTipo = Fachada.getInstancia().pesquisar(
				filtroDocumentoTipo, DocumentoTipo.class.getName());
		
		if ( Util.isVazioOrNulo(colecaoDocumentoTipo)) {

			throw new ActionServletException("atencao.pesquisa_tipo_documento_nao_inexistente");
		}
		
		return colecaoDocumentoTipo.iterator().next();
	}

	private void montarDocumentoNaoEntregue(String idCodigo,
			String qtTentativas, DocumentoTipo documentoTipo,
			String matriculaOuNumeroDocumento, String mesAno,
			Date dataDevolucaoFormatada, Cliente cliente,
			Collection<DocumentoNaoEntregue> colecaoDocumentosNaoEntregues) {
		
		if (matriculaOuNumeroDocumento != null && !matriculaOuNumeroDocumento.trim().equals("")) {
		
			Fachada fachada = Fachada.getInstancia();
		
			DocumentoNaoEntregue documentoNaoEntregue = new DocumentoNaoEntregue();
		
			documentoNaoEntregue.setCliente(cliente);
			documentoNaoEntregue
					.setDataTentativaEntrega(dataDevolucaoFormatada);
			documentoNaoEntregue.setDocumentoTipo(documentoTipo);
			documentoNaoEntregue.setNumeroTentativa(new Short(qtTentativas));
			documentoNaoEntregue.setUltimaAlteracao(new Date());
		
			MotivoNaoEntregaDocumento motivoNaoEntregaDocumento = new MotivoNaoEntregaDocumento();
			
			motivoNaoEntregaDocumento.setId(new Integer(idCodigo.trim()));

			documentoNaoEntregue
				.setMotivoNaoEntregaDocumento(motivoNaoEntregaDocumento);

			
			if (documentoTipo.getId().equals(DocumentoTipo.CONTA)) {
			
				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, Integer.parseInt(matriculaOuNumeroDocumento)));
				filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO, 2));
			
				Collection<Imovel> colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
			
				if (colecaoImovel == null || colecaoImovel.isEmpty()) {
					throw new ActionServletException("atencao.pesquisa.imovel.inexistente", null, matriculaOuNumeroDocumento);
				}
			
				Conta conta = fachada.pesquisarContaDigitada(matriculaOuNumeroDocumento, mesAno);
			
				if (conta != null) {
					ContaGeral contaGeral = new ContaGeral();
					contaGeral.setId(conta.getId());
					contaGeral.setConta(conta);
					documentoNaoEntregue.setContaGeral(contaGeral);
				} else {
					throw new ActionServletException("atencao.referencia.naocadastrada", new Exception(), mesAno, matriculaOuNumeroDocumento);
				}
			
			} else if (documentoTipo.getId().equals(DocumentoTipo.GUIA_PAGAMENTO)) {
			
				FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
				filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.ID, Integer.parseInt(matriculaOuNumeroDocumento)));
			
				Collection<GuiaPagamento> colecaoGuiaPagamento = fachada.pesquisar(filtroGuiaPagamento, GuiaPagamento.class.getName());
			
				if (colecaoGuiaPagamento != null && !colecaoGuiaPagamento.isEmpty()) {
					GuiaPagamento guiaPagamento = (GuiaPagamento) Util.retonarObjetoDeColecao(colecaoGuiaPagamento);
				
					GuiaPagamentoGeral guiaPagamentoGeral = new GuiaPagamentoGeral();
					guiaPagamentoGeral.setId(guiaPagamento.getId());
					guiaPagamentoGeral.setGuiaPagamento(guiaPagamento);
				
					documentoNaoEntregue.setGuiaPagamentoGeral(guiaPagamentoGeral);
				} else {
					String mensagem = "Guia de Pagamento " + matriculaOuNumeroDocumento;
					throw new ActionServletException("atencao.pesquisa_inexistente", null, mensagem);
				}
			
			} else if (documentoTipo.getId().equals(DocumentoTipo.FATURA_CLIENTE)) {
			
				FiltroFatura filtroFatura = new FiltroFatura();
				filtroFatura.adicionarParametro(new ParametroSimples(FiltroFatura.ID, Integer.parseInt(matriculaOuNumeroDocumento)));
			
				Collection<Fatura> colecaoFatura = fachada.pesquisar(filtroFatura, Fatura.class.getName());
			
				if (colecaoFatura != null && !colecaoFatura.isEmpty()) {
					Fatura fatura = (Fatura) Util.retonarObjetoDeColecao(colecaoFatura);
					documentoNaoEntregue.setFatura(fatura);
				} else {
					String mensagem = "Fatura " + matriculaOuNumeroDocumento;
					throw new ActionServletException("atencao.pesquisa_inexistente", null, mensagem);
				}
			
			} else {
			
				FiltroCobrancaDocumento filtroCobrancaDocumento = new FiltroCobrancaDocumento();
				filtroCobrancaDocumento.adicionarParametro(new ParametroSimples(FiltroCobrancaDocumento.ID, Integer.parseInt(matriculaOuNumeroDocumento)));
			
				Collection<CobrancaDocumento> colecaoCobrancaDocumento = fachada.pesquisar(filtroCobrancaDocumento, CobrancaDocumento.class.getName());
			
				if (colecaoCobrancaDocumento != null && !colecaoCobrancaDocumento.isEmpty()) {
					CobrancaDocumento cobrancaDocumento = (CobrancaDocumento) Util.retonarObjetoDeColecao(colecaoCobrancaDocumento);
					documentoNaoEntregue.setCobrancaDocumento(cobrancaDocumento);
				} else {
					String mensagem = "Documento de Cobrança " + matriculaOuNumeroDocumento;
					throw new ActionServletException("atencao.pesquisa_inexistente", null, mensagem);
				}
			
			} 

			colecaoDocumentosNaoEntregues.add(documentoNaoEntregue);
			
		}
	}

}