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

import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorMovimento;
import gcom.cobranca.NegativadorMovimentoReg;
import gcom.cobranca.NegativadorMovimentoRegRetMot;
import gcom.cobranca.NegativadorRegistroTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativadorMovimentoRegRetMot;
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

/**
 * Permite a consulta dos dados de um registro no formato padrão SERASA.
 * [UC0684] - Consultar Dados do Registro SERASA
 * 
 * @author Yara Taciane de Souza
 * @date 29/01/2008
 */
public class ExibirConsultarDadosRegistroSERASAAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {		
		
		ActionForward retorno = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		ConsultarDadosRegistroActionForm form = (ConsultarDadosRegistroActionForm) actionForm;

		NegativadorMovimentoReg negativadorMovimentoReg = (NegativadorMovimentoReg) sessao.getAttribute("negativadorMovimentoReg");
		
		String indicCorrecao = "-1";
		
		if(negativadorMovimentoReg.getIndicadorCorrecao()!= null){
			if(negativadorMovimentoReg.getIndicadorCorrecao().equals(ConstantesSistema.CORRIGIDO)||negativadorMovimentoReg.getIndicadorCorrecao().equals(ConstantesSistema.NAO_CORRIGIDO) ){
				indicCorrecao = negativadorMovimentoReg.getIndicadorCorrecao().toString();
			}
		}
		
		form.setIndicadorCorrecao(indicCorrecao);		
				
		NegativadorRegistroTipo negativadorRegistroTipo= negativadorMovimentoReg.getNegativadorRegistroTipo();
		
		if(negativadorRegistroTipo != null){
			
			if(negativadorRegistroTipo.getId().equals(NegativadorRegistroTipo.ID_SERASA_HEADER)){
				retorno = exibirDadosRegistroTipoHeader(negativadorMovimentoReg,form,fachada,sessao,httpServletRequest,actionMapping);
			}else if(negativadorRegistroTipo.getId().equals(NegativadorRegistroTipo.ID_SERASA_DETALHE)){
				retorno = exibirDadosRegistroTipoDetalhe(negativadorMovimentoReg,form,fachada,sessao,httpServletRequest,actionMapping);			
			}else if(negativadorRegistroTipo.getId().equals(NegativadorRegistroTipo.ID_SERASA_TRAILLER)){
				retorno = exibirDadosRegistroTipoTrailler(negativadorMovimentoReg,form,fachada,sessao,httpServletRequest,actionMapping);
			}else{
			
				throw new ActionServletException("atencao.codigo_tipo_registro_inexistente");
			}
		}
		
		   FiltroNegativadorMovimentoRegRetMot filtroNegativadorMovimentoRegRetMot = new FiltroNegativadorMovimentoRegRetMot();
		   filtroNegativadorMovimentoRegRetMot
					.adicionarParametro(new ParametroSimples(
							FiltroNegativadorMovimentoRegRetMot.NEGATIVADOR_MOVIMENTO_REG_ID,
							negativadorMovimentoReg.getId()));
		   filtroNegativadorMovimentoRegRetMot.adicionarCaminhoParaCarregamentoEntidade("negativadorRetornoMotivo");
		   
			Collection<NegativadorMovimentoRegRetMot> collNegativadorMovimentoRegRetMot = fachada
			.pesquisar(filtroNegativadorMovimentoRegRetMot,
					NegativadorMovimentoRegRetMot.class.getName());
		   

//			Map resultado = controlarPaginacao(httpServletRequest, retorno,
//					filtroNegativadorMovimentoRegRetMot, NegativadorMovimentoRegRetMot.class.getName());			
//			
//			Collection<NegativadorMovimentoRegRetMot> collNegativadorMovimentoRegRetMot = (Collection) resultado.get("colecaoRetorno");

			
			form.setCollNegativadorMovimentoRegRetMot(collNegativadorMovimentoRegRetMot);
			sessao.setAttribute("collNegativadorMovimentoRegRetMot", collNegativadorMovimentoRegRetMot);
			
			
		return retorno;
	}
	
	
	
	
	private ActionForward exibirDadosRegistroTipoHeader(
			NegativadorMovimentoReg negativadorMovimentoReg,
			ConsultarDadosRegistroActionForm form,
			Fachada fachada, HttpSession sessao,
			HttpServletRequest httpServletRequest,
			ActionMapping actionMapping ) {

		   ActionForward  retorno = actionMapping.findForward("consultarDadosRegistroSERASAHeader");


		   Negativador negativador = negativadorMovimentoReg.getNegativadorMovimento().getNegativador();
		   NegativadorMovimento negativadorMovimento = negativadorMovimentoReg.getNegativadorMovimento();
		   
		   if(negativador != null && !negativador.equals("")){
			   form.setNegativador(negativador.getCliente().getNome());
		   }else{
			   form.setNegativador(""); 
		   }
		   
		   Short codigoMovimento = negativadorMovimento.getCodigoMovimento();
		   if(codigoMovimento != null && !codigoMovimento.equals("")){
			   form.setTipoMovimento(codigoMovimento.toString());
		   }else{
			   form.setTipoMovimento(""); 
		   }
		  
		   String tipoRegistroCodigo=negativadorMovimentoReg.getNegativadorRegistroTipo().getCodigoRegistro();
		     if(tipoRegistroCodigo != null && !tipoRegistroCodigo.equals("")){
			   form.setTipoRegistroCodigo(tipoRegistroCodigo);
		   }else{
			   form.setTipoRegistroCodigo(""); 
		   }
		     
		   String tipoRegistroDescricao=negativadorMovimentoReg.getNegativadorRegistroTipo().getDescricaoRegistroTipo() ;
		   if(tipoRegistroDescricao != null && !tipoRegistroDescricao.equals("")){
			   form.setTipoRegistroDescricao(tipoRegistroDescricao);
		   }else{
			   form.setTipoRegistroDescricao(""); 
		   }
		       
			//Conteúdo Registro
		   String conteudoRegistro = negativadorMovimentoReg.getConteudoRegistro();	
		   conteudoRegistro =Util.completaString(conteudoRegistro, 600);
		   
		   if(conteudoRegistro!= null && !conteudoRegistro.equals("")){
			 
			   //H.02
			   String cnpj=conteudoRegistro.substring(1, 10);
			   if(cnpj != null && !cnpj.equals("")){
				   form.setCnpj(cnpj);
			   }else{
				   form.setCnpj(""); 
			   }
			   
			   //H.03
			   Date dataMovimento = Util.converteStringParaDate(conteudoRegistro.substring(10,18));
			   if(dataMovimento != null && !dataMovimento.equals("")){
				   form.setDataMovimento(Util.formatarDataSemBarraDDMMAAAA(dataMovimento));
			   }else{
				   form.setDataMovimento(""); 
			   } 
			   
			   //H.04
			   String dddTelefoneContato=conteudoRegistro.substring(19, 22);
			   if(dddTelefoneContato != null && !dddTelefoneContato.equals("")){
				   form.setDddTelefoneContato(dddTelefoneContato);
			   }else{
				   form.setDddTelefoneContato(""); 
			   }
			   
			   //H.05
			   String telefoneContato=conteudoRegistro.substring(22, 30);
			   if(telefoneContato != null && !telefoneContato.equals("")){
				   form.setTelefoneContato(telefoneContato);
			   }else{
				   form.setTelefoneContato(""); 
			   }
			   
			   //H.06
			   String ramalTelefoneContato=conteudoRegistro.substring(30, 34);
			   if(ramalTelefoneContato != null && !ramalTelefoneContato.equals("")){
				   form.setRamalTelefoneContato(ramalTelefoneContato);
			   }else{
				   form.setRamalTelefoneContato(""); 
			   }
			   
			   //H.07
			   String nomeContato=conteudoRegistro.substring(34, 104);
			   if(nomeContato != null && !nomeContato.equals("")){
				   form.setNomeContato(nomeContato);
			   }else{
				   form.setNomeContato(""); 
			   }
			   
			   //H.08
			   String identificacaoArquivo=conteudoRegistro.substring(104, 119);
			   if(identificacaoArquivo != null && !identificacaoArquivo.equals("")){
				   form.setIdentificacaoArquivo(identificacaoArquivo);
			   }else{
				   form.setIdentificacaoArquivo(""); 
			   }
			   
			   //H.09
			   String sequencialRemessa=conteudoRegistro.substring(119, 125);
			   if(sequencialRemessa != null && !sequencialRemessa.equals("")){
				   form.setSequencialRemessa(sequencialRemessa);
			   }else{
				   form.setSequencialRemessa(""); 
			   }
			   
			   //H.10
			   String codigoEnvio=conteudoRegistro.substring(125, 126);
			   String descricaoCodigoEnvio = "";
			   if(codigoEnvio != null && !codigoEnvio.equals("")){
				   if(codigoEnvio.equalsIgnoreCase("E")){
					   descricaoCodigoEnvio = "ENTRADA";
				   }else if(codigoEnvio.equalsIgnoreCase("R")){
					   descricaoCodigoEnvio = "RETORNO";
				   }
				   form.setCodigoEnvio(codigoEnvio);
				   form.setDescricaoCodigoEnvio(descricaoCodigoEnvio);
				   
			   }else{
				   form.setCodigoEnvio(""); 
				   form.setDescricaoCodigoEnvio("");
			   }
			   
			   //H.11
			   Date diferencialRemessa = Util.converteStringParaDate(conteudoRegistro.substring(126,130));
			   if(diferencialRemessa != null && !diferencialRemessa.equals("")){
				   form.setDiferencialRemessa(Util.formatarDataComTracoAAAAMMDD(diferencialRemessa));
			   }else{
				   form.setDiferencialRemessa(""); 
			   } 
			   
			   //H.13
			   String codigoRetorno=conteudoRegistro.substring(533, 593);
			   if(codigoRetorno != null && !codigoRetorno.equals("")){
				   form.setCodigoRetorno(codigoRetorno);
			   }else{
				   form.setCodigoRetorno(""); 
			   }
			   
			   //H.14
			   String sequencialRegistro=conteudoRegistro.substring(593, 600);
			   if(sequencialRegistro != null && !sequencialRegistro.equals("")){
				   form.setSequencialRegistro(sequencialRegistro);
			   }else{
				   form.setSequencialRegistro(""); 
			   }
				 	 
			   Short indicadorAceito=negativadorMovimentoReg.getIndicadorAceito();
			   if(indicadorAceito != null && !indicadorAceito.equals("")){
				   
				   if(indicadorAceito.equals(ConstantesSistema.ACEITO)){
					   form.setIndicadorAceitacao("SIM"); 
				   }else{
					   form.setIndicadorAceitacao("NÃO");   
				   }
				   
			   }else{
				   form.setIndicadorAceitacao(""); 
			   }
			   
		   }
			
			return retorno;
	}
	
	/**
	 * --------------------------------------------------------------------------------------------------------
	 */
	
	private ActionForward exibirDadosRegistroTipoDetalhe(
			NegativadorMovimentoReg negativadorMovimentoReg,
			ConsultarDadosRegistroActionForm form,
			Fachada fachada, HttpSession sessao,
			HttpServletRequest httpServletRequest,
			ActionMapping actionMapping ) {

		   ActionForward  retorno = actionMapping.findForward("consultarDadosRegistroSERASADetalhe");
		
		   Negativador negativador = negativadorMovimentoReg.getNegativadorMovimento().getNegativador();
		   NegativadorMovimento negativadorMovimento = negativadorMovimentoReg.getNegativadorMovimento();
		   
		   if(negativador != null && !negativador.equals("")){
			   form.setNegativador(negativador.getCliente().getNome());
		   }else{
			   form.setNegativador(""); 
		   }
		   
		   Short codigoMovimento = negativadorMovimento.getCodigoMovimento();
		   if(codigoMovimento != null && !codigoMovimento.equals("")){
			   form.setTipoMovimento(codigoMovimento.toString());
		   }else{
			   form.setTipoMovimento(""); 
		   }
		  
   
		   String tipoRegistroCodigo=negativadorMovimentoReg.getNegativadorRegistroTipo().getCodigoRegistro();
		     if(tipoRegistroCodigo != null && !tipoRegistroCodigo.equals("")){
			   form.setTipoRegistroCodigo(tipoRegistroCodigo);
		   }else{
			   form.setTipoRegistroCodigo(""); 
		   }
		     
		   String tipoRegistroDescricao=negativadorMovimentoReg.getNegativadorRegistroTipo().getDescricaoRegistroTipo() ;
		   if(tipoRegistroDescricao != null && !tipoRegistroDescricao.equals("")){
			   form.setTipoRegistroDescricao(tipoRegistroDescricao);
		   }else{
			   form.setTipoRegistroDescricao(""); 
		   }
		   
		   //Conteúdo Registro
		   String conteudoRegistro = negativadorMovimentoReg.getConteudoRegistro();		  
		   conteudoRegistro =Util.completaString(conteudoRegistro, 600);
		   
		   if(conteudoRegistro!= null && !conteudoRegistro.equals("")){			   
			   //D1.02
			   String codigoOperacao=conteudoRegistro.substring(1, 2);
			   if(codigoOperacao != null && !codigoOperacao.equals("")){
				   form.setCodigoOperacao(codigoOperacao);
				   if(codigoOperacao.equals("1")){
					   form.setOperacao("INCLUSÃO");
				   }else if(codigoOperacao.equals("2")){
					   form.setOperacao("EXCLUSÃO");
				   }
			   }else{
				   form.setCodigoOperacao(""); 
				   form.setOperacao(""); 
			   }
			   
			  
			   //D.03
			   String cnpj=conteudoRegistro.substring(2, 8);
			   if(cnpj != null && !cnpj.equals("")){
				   form.setCnpj(cnpj);
			   }else{
				   form.setCnpj(""); 
			   }
			   
			   
			   //D.04
			   Date dataOcorrencia = Util.converteStringParaDate(conteudoRegistro.substring(8,16));
			   if(dataOcorrencia != null && !dataOcorrencia.equals("")){
				   form.setDataOcorrencia(Util.formatarDataComTracoAAAAMMDD(dataOcorrencia));
			   }else{
				   form.setDataOcorrencia(""); 
			   } 
			   
			   //D.05
			   Date dataTerminoContrato = Util.converteStringParaDate(conteudoRegistro.substring(16,24));
			   if(dataTerminoContrato != null && !dataTerminoContrato.equals("")){
				   form.setDataTerminoContrato(Util.formatarDataComTracoAAAAMMDD(dataTerminoContrato));
			   }else{
				   form.setDataTerminoContrato(""); 
			   } 
			   
			   
			   //D.06
			   String codigoNaturezaOperacao=conteudoRegistro.substring(24, 27);
			   if(codigoNaturezaOperacao != null && !codigoNaturezaOperacao.equals("")){
				   form.setCodigoNaturezaOperacao(codigoNaturezaOperacao);
			   }else{
				   form.setCodigoNaturezaOperacao(""); 
			   }
			   
			   //D.07
			   String codigoPracaEmbratel=conteudoRegistro.substring(27, 31);
			   if(codigoPracaEmbratel != null && !codigoPracaEmbratel.equals("")){
				   form.setCodigoPracaEmbratel(codigoPracaEmbratel);
			   }else{
				   form.setCodigoPracaEmbratel(""); 
			   }
			   
			   
			   //D.08
			   String tipoPessoaPrincipal=conteudoRegistro.substring(31, 32);
			   if(tipoPessoaPrincipal != null && !tipoPessoaPrincipal.equals("")){
				   if(tipoPessoaPrincipal.equalsIgnoreCase("F")){
					   form.setTipoPessoaPrincipal("FÍSICA");
				   }else if(tipoPessoaPrincipal.equalsIgnoreCase("J")){
					   form.setTipoPessoaPrincipal("JURÍDICA");
				   }
			   }else{
				   form.setTipoPessoaPrincipal(""); 
			   }
			   
			   //D.09
			   String tipoPrimeiroDocumentoPrincipal=conteudoRegistro.substring(32, 33);
			   if(tipoPrimeiroDocumentoPrincipal != null && !tipoPrimeiroDocumentoPrincipal.equals("")){
				   if(tipoPrimeiroDocumentoPrincipal.equalsIgnoreCase("1")){
					   form.setTipoPrimeiroDocumentoPrincipal("CPF");
				   }else if(tipoPrimeiroDocumentoPrincipal.equalsIgnoreCase("2")){
					   form.setTipoPrimeiroDocumentoPrincipal("CNPJ");
				   }
			   }else{
				   form.setTipoPessoaPrincipal(""); 
			   }			   
			   
			   //D.10
			   String cpfCnpjPrincipal=conteudoRegistro.substring(33, 48);
			   if(cpfCnpjPrincipal != null && !cpfCnpjPrincipal.equals("")){
				   form.setCpfCnpjPrincipal(cpfCnpjPrincipal);
			   }else{
				   form.setCpfCnpjPrincipal(""); 
			   }
			   
			   //D.11
			   String motivoBaixa=conteudoRegistro.substring(48, 50);
			   if(motivoBaixa != null && !motivoBaixa.equals("")){
				   form.setMotivoBaixa(motivoBaixa);
			   }else{
				   form.setMotivoBaixa(""); 
			   }
			   
			   //D.12
			   String tipoSegundoDocumentoPrincipal=conteudoRegistro.substring(50, 51);
			   if(tipoSegundoDocumentoPrincipal != null && !tipoSegundoDocumentoPrincipal.equals("")){
				   if(tipoSegundoDocumentoPrincipal.equals("3")){
					   form.setTipoSegundoDocumentoPrincipal("RG");
				   }
			   }else{
				   form.setTipoSegundoDocumentoPrincipal(""); 
			   }
			   
			   
			   //D.13
			   String rgPrincipal=conteudoRegistro.substring(51, 66);
			   if(rgPrincipal != null && !rgPrincipal.equals("")){
				   form.setRgPrincipal(rgPrincipal);
			   }else{
				   form.setRgPrincipal(""); 
			   }
			   
			   
			   //D.14
			   String ufDoRgPrincipal=conteudoRegistro.substring(66, 68);
			   if(ufDoRgPrincipal != null && !ufDoRgPrincipal.equals("")){
				   form.setUfDoRgPrincipal(ufDoRgPrincipal);
			   }else{
				   form.setUfDoRgPrincipal(""); 
			   }
			   
			   
			   //D.15
			   String tipoPessoaCoobrigado=conteudoRegistro.substring(68, 69);
			   if(tipoPessoaCoobrigado != null && !tipoPessoaCoobrigado.equals("")){
				   
				   if(tipoPessoaCoobrigado.equals("F")){
					   form.setTipoPessoaCoobrigado("FÍSICA");
				   }else if(tipoPessoaCoobrigado.equals("J")){
					   form.setTipoPessoaCoobrigado("JURÍDICA");
				   }
				   
			   }else{
				   form.setTipoPessoaCoobrigado(""); 
			   }
			   
			   
			   //D.16
			   String tipoPrimeiroDocumentoCoobrigado=conteudoRegistro.substring(69, 70);
			   if(tipoPrimeiroDocumentoCoobrigado != null && !tipoPrimeiroDocumentoCoobrigado.equals("")){
				   
				   if(tipoPrimeiroDocumentoCoobrigado.equals("1")){
					   form.setTipoPrimeiroDocumentoCoobrigado("CPF");
				   }else if(tipoPrimeiroDocumentoCoobrigado.equals("2")){
					   form.setTipoPrimeiroDocumentoCoobrigado("CNPJ");
				   }
				   
			   }else{
				   form.setTipoPessoaCoobrigado(""); 
			   }
			   
			   
			   
			   //D.17
			   String cpfCnpjCoobrigado=conteudoRegistro.substring(70, 85);
			   if(cpfCnpjCoobrigado != null && !cpfCnpjCoobrigado.equals("")){
				   form.setCpfCnpjCoobrigado(cpfCnpjCoobrigado);
			   }else{
				   form.setCpfCnpjCoobrigado(""); 
			   }
			   
			   
			   //D.19
			   String tipoSegundoDocumentoCoobrigado=conteudoRegistro.substring(87, 88);
			   if(tipoSegundoDocumentoCoobrigado != null && !tipoSegundoDocumentoCoobrigado.equals("")){				   
				   if(tipoSegundoDocumentoCoobrigado.equals("3")){
					   form.setTipoSegundoDocumentoCoobrigado("RG");
				   }				   
			   }else{
				   form.setTipoSegundoDocumentoCoobrigado(""); 
			   }
			   
			   //D.20
			   String rgCoobrigado=conteudoRegistro.substring(88, 103);
			   if(rgCoobrigado != null && !rgCoobrigado.equals("")){		   
					   form.setRgCoobrigado(rgCoobrigado);
			   }else{
				   form.setRgCoobrigado(""); 
			   }

			   //D.21
			   String ufRgCoobrigado=conteudoRegistro.substring(103, 105);
			   if(ufRgCoobrigado != null && !ufRgCoobrigado.equals("")){		   
					   form.setUfDoRgCoobrigado(ufRgCoobrigado);
			   }else{
				   form.setUfDoRgCoobrigado(""); 
			   }
			   
			   //D.22
			   String nomeDevedor=conteudoRegistro.substring(105, 175);
			   if(nomeDevedor != null && !nomeDevedor.equals("")){		   
					   form.setNomeDevedor(nomeDevedor);
			   }else{
				   form.setNomeDevedor(""); 
			   }

			   //D.23
			   Date dataNascimento = Util.converteStringParaDate(conteudoRegistro.substring(175,183));
			   if(dataNascimento != null && !dataNascimento.equals("")){
				   form.setDataNascimento(Util.formatarDataComTracoAAAAMMDD(dataNascimento));
			   }else{
				   form.setDataNascimento(""); 
			   } 
			   
			   //D.24
			   String nomePai=conteudoRegistro.substring(183, 253);
			   if(nomePai != null && !nomePai.equals("")){		   
					   form.setNomePai(nomePai);
			   }else{
				   form.setNomePai(""); 
			   }
			   
			   //D.25
			   String nomeMae=conteudoRegistro.substring(253, 323);
			   if(nomeMae != null && !nomeMae.equals("")){		   
					   form.setNomeMae(nomeMae);
			   }else{
				   form.setNomeMae(""); 
			   }
			   
			   //D.26
			   String endereco=conteudoRegistro.substring(323, 368);
			   if(endereco != null && !endereco.equals("")){		   
					   form.setEndereco(endereco);
			   }else{
				   form.setEndereco(""); 
			   }
			   
			   //D.27
			   String bairro=conteudoRegistro.substring(368, 388);
			   if(bairro != null && !bairro.equals("")){		   
					   form.setBairro(bairro);
			   }else{
				   form.setBairro(""); 
			   }
			   
			   //D.28
			   String municipio=conteudoRegistro.substring(388, 413);
			   if(municipio != null && !municipio.equals("")){		   
					   form.setMunicipio(municipio);
			   }else{
				   form.setMunicipio(""); 
			   }
			   
			   //D.29
			   String uf=conteudoRegistro.substring(413, 415);
			   if(uf != null && !uf.equals("")){		   
					   form.setUf(uf);
			   }else{
				   form.setUf(""); 
			   }
			   
			   //D.30
			   String cep=conteudoRegistro.substring(415, 423);
			   if(cep != null && !cep.equals("")){		   
					   form.setCep(cep);
			   }else{
				   form.setCep(""); 
			   }
			   
			   //D.31
			   String valorDebito=conteudoRegistro.substring(423, 438);
			   if(valorDebito != null && !valorDebito.equals("")){		   
					   form.setValorDebito(valorDebito);
			   }else{
				   form.setValorDebito(""); 
			   }
			   
			   //D.32
			   String contrato=conteudoRegistro.substring(438, 454);
			   if(contrato != null && !contrato.equals("")){		   
					   form.setContrato(contrato);
			   }else{
				   form.setContrato(""); 
			   }
			   
			   //D.33
			   String nossoNumero=conteudoRegistro.substring(454, 463);
			   if(nossoNumero != null && !nossoNumero.equals("")){		   
					   form.setNossoNumero(nossoNumero);
			   }else{
				   form.setNossoNumero(""); 
			   }
			   
			   //D.34
			   String complementoEndereco=conteudoRegistro.substring(463, 488);
			   if(complementoEndereco != null && !complementoEndereco.equals("")){		   
					   form.setComplemento(complementoEndereco);
			   }else{
				   form.setComplemento(""); 
			   }
			   
			   //D.35
			   String dddTelefoneDevedor=conteudoRegistro.substring(488, 492);
			   if(dddTelefoneDevedor != null && !dddTelefoneDevedor.equals("")){		   
					   form.setDddTelefoneDevedor(dddTelefoneDevedor);
			   }else{
				   form.setDddTelefoneDevedor(""); 
			   }
			   
			   //D.36
			   String telefoneDevedor=conteudoRegistro.substring(492, 501);
			   if(telefoneDevedor != null && !telefoneDevedor.equals("")){		   
					   form.setTelefoneDevedor(telefoneDevedor);
			   }else{
				   form.setTelefoneDevedor(""); 
			   }

			   //D.37
			   Date dataVencimentoDebitoMaisAntigo=Util.converteStringParaDate(conteudoRegistro.substring(501,509));
			   if(dataVencimentoDebitoMaisAntigo != null && !dataVencimentoDebitoMaisAntigo.equals("")){
				   form.setDataVencimentoDebitoMaisAntigo(Util.formatarDataComTracoAAAAMMDD(dataVencimentoDebitoMaisAntigo));
			   }else{
				   form.setDataVencimentoDebitoMaisAntigo(""); 
			   } 
			   
			   //D.38
			   String valorTotalCompromisso=conteudoRegistro.substring(509, 524);
			   if(valorTotalCompromisso != null && !valorTotalCompromisso.equals("")){		   
					   form.setValorToralCompromisso(valorTotalCompromisso);
			   }else{
				   form.setValorToralCompromisso(""); 
			   }
			   
			   //D.40
			   String codigoRetorno=conteudoRegistro.substring(533, 593);
			   if(codigoRetorno != null && !codigoRetorno.equals("")){		   
					   form.setCodigoRetorno(codigoRetorno);
			   }else{
				   form.setCodigoRetorno(""); 
			   }
			   
			   //D.41
			   String sequencialRegistro=conteudoRegistro.substring(593, 600);
			   if(sequencialRegistro != null && !sequencialRegistro.equals("")){		   
					   form.setSequencialRegistro(sequencialRegistro);
			   }else{
				   form.setSequencialRegistro(""); 
			   }
			  
			   Short indicadorAceito=negativadorMovimentoReg.getIndicadorAceito();
			   if(indicadorAceito != null && !indicadorAceito.equals("")){
				   
				   if(indicadorAceito.equals(ConstantesSistema.ACEITO)){
					   form.setIndicadorAceitacao("SIM"); 
				   }else{
					   form.setIndicadorAceitacao("NÃO");   
				   }
				   
			   }else{
				   form.setIndicadorAceitacao(""); 
			   }
		   }
			

		
			return retorno;
	}
	
	
	private ActionForward exibirDadosRegistroTipoTrailler(
			NegativadorMovimentoReg negativadorMovimentoReg,
			ConsultarDadosRegistroActionForm form,
			Fachada fachada, HttpSession sessao,
			HttpServletRequest httpServletRequest,
			ActionMapping actionMapping ) {

		   ActionForward  retorno = actionMapping.findForward("consultarDadosRegistroSERASATrailler");

			
		   Negativador negativador = negativadorMovimentoReg.getNegativadorMovimento().getNegativador();
		   NegativadorMovimento negativadorMovimento = negativadorMovimentoReg.getNegativadorMovimento();
		   
		   if(negativador != null && !negativador.equals("")){
			   form.setNegativador(negativador.getCliente().getNome());
		   }else{
			   form.setNegativador(""); 
		   }
		   
		   Short codigoMovimento = negativadorMovimento.getCodigoMovimento();
		   if(codigoMovimento != null && !codigoMovimento.equals("")){
			   form.setTipoMovimento(codigoMovimento.toString());
		   }else{
			   form.setTipoMovimento(""); 
		   }
		  
   
		   String tipoRegistroCodigo=negativadorMovimentoReg.getNegativadorRegistroTipo().getCodigoRegistro();
		     if(tipoRegistroCodigo != null && !tipoRegistroCodigo.equals("")){
			   form.setTipoRegistroCodigo(tipoRegistroCodigo);
		   }else{
			   form.setTipoRegistroCodigo(""); 
		   }
		     
		   String tipoRegistroDescricao=negativadorMovimentoReg.getNegativadorRegistroTipo().getDescricaoRegistroTipo() ;
		   if(tipoRegistroDescricao != null && !tipoRegistroDescricao.equals("")){
			   form.setTipoRegistroDescricao(tipoRegistroDescricao);
		   }else{
			   form.setTipoRegistroDescricao(""); 
		   }
		 	     
		     
			//Conteúdo Registro
		   String conteudoRegistro = negativadorMovimentoReg.getConteudoRegistro();	
		   conteudoRegistro =Util.completaString(conteudoRegistro, 600);
	   
		   if(conteudoRegistro!= null && !conteudoRegistro.equals("")){   
			   
					   
			   //T.03
			   String codigoRetorno=conteudoRegistro.substring(533,593);
			   if(codigoRetorno != null && !codigoRetorno.equals("")){
				   form.setCodigoRetorno(codigoRetorno);
			   }else{
				   form.setCodigoRetorno(""); 
			   }
			   
    		   //T.04
			   String sequencialRegistro=conteudoRegistro.substring(593,600);
			   if(sequencialRegistro != null && !sequencialRegistro.equals("")){
				   form.setSequencialRegistro(sequencialRegistro);
			   }else{
				   form.setSequencialRegistro(""); 
			   }
			   
			   
			   Short indicadorAceito=negativadorMovimentoReg.getIndicadorAceito();
			   if(indicadorAceito != null && !indicadorAceito.equals("")){
				   
				   if(indicadorAceito.equals(ConstantesSistema.ACEITO)){
					   form.setIndicadorAceitacao("SIM"); 
				   }else{
					   form.setIndicadorAceitacao("NÃO");   
				   }
				   
			   }else{
				   form.setIndicadorAceitacao(""); 
			   }
			   
			   
		   }   
		
			return retorno;
	}
	
}
