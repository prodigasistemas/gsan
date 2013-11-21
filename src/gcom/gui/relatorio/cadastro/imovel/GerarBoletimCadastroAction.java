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
package gcom.gui.relatorio.cadastro.imovel;

import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cadastro.imovel.RelatorioBoletimCadastro;
import gcom.relatorio.cadastro.imovel.RelatorioBoletimCadastroBean;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarBoletimCadastroAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirGerarBoletimCadastro");
		
		DiskFileUpload upload = new DiskFileUpload();
		List items = null;
		try {
			items = upload.parseRequest(httpServletRequest);
			this.getSessao(httpServletRequest).setAttribute("arquivo",items);
		} catch (FileUploadException e) {
			items = (List) this.getSessao(httpServletRequest).getAttribute("arquivo");
			if(items == null){
				throw new ActionServletException("erro.sistema", e);
			}
			
		}
		
		Collection colecaoBoletimCadastro = 
			this.gerarColecaoBoletimCadastro(items);
		
		FileItem item = (FileItem) Util.retonarObjetoDeColecao(items);
		
		String nomeArquivo = this.tratarNomeArquivo(item.getName());
		nomeArquivo = nomeArquivo.replace(".txt","");
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioBoletimCadastro relatorio = 
			new RelatorioBoletimCadastro(this.getUsuarioLogado(httpServletRequest));
		
		relatorio.addParametro("colecaoBoletimCadastro", colecaoBoletimCadastro);
		
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		relatorio.addParametro("nomeArquivo",nomeArquivo);
		retorno = 
			processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
				httpServletResponse, actionMapping);

		return retorno;
	}
	
	private Collection gerarColecaoBoletimCadastro(List items){
		
		Collection retorno = new ArrayList();
		
		FileItem item = null;
		String nomeItem = null;
		
		// Parse the request
		try {
			
			Iterator iter = items.iterator();
			while (iter.hasNext()) {

				item = (FileItem) iter.next();

				// verifica se não é diretorio
				if (!item.isFormField()) {
					// coloca o nome do item para maiusculo
					nomeItem = item.getName().toUpperCase();
					// compara o final do nome do arquivo é .txt
					if (nomeItem.endsWith(".TXT")) {

						// abre o arquivo
						InputStreamReader reader = new InputStreamReader(item.getInputStream());

						BufferedReader buffer = new BufferedReader(reader);
						
						// cria uma variavel do tipo boolean
						boolean eof = false;
						
						RelatorioBoletimCadastroBean relatorioBean = null;
						
						// enquanto a variavel for false
						while (!eof) {
							// pega a linha do arquivo
							String linhaLida = buffer.readLine();

							// se for a ultima linha do arquivo
							if (linhaLida == null || linhaLida.trim().length() == 0) {
								// seta a variavel boolean para true
								eof = true;
							} else {
								
								relatorioBean = new RelatorioBoletimCadastroBean();

								//--Dados do Cliente Proprietario--

								//1-Numero Documento Referencia - Tam 8
								relatorioBean.setNumeroDocumentoReferencia(linhaLida.substring(0, 8).trim());
								
								//2-Codigo do Cliente Proprietario - Tam 9
								relatorioBean.setCodigoClienteProprietario(linhaLida.substring(8, 17).trim());
								
								//3-Nome do Cliente Proprietario - Tam 23
								relatorioBean.setNomeClienteProprietario(linhaLida.substring(17, 40).trim());

								//4-Tipo do Cliente Proprietario - Tam 2
								relatorioBean.setTipoClienteProprietario(linhaLida.substring(40, 42).trim());
								
								//5-Cpf do Cliente Proprietario - Tam 14
								relatorioBean.setCpfCnpjProprietario(linhaLida.substring(42, 56).trim());
								
								//6-Rg do Cliente Proprietario - Tam 13
								relatorioBean.setRgProprietario(linhaLida.substring(56, 69).trim());
								
								//7-Data de Emissao do Cliente Proprietario - Tam 8
								relatorioBean.setDataEmissaoProprietario(
									Util.converterDataSemBarraParaDataComBarra(linhaLida.substring(69, 77).trim()));
								
								//8-Orgao Expedidor do Cliente Proprietario - Tam 4
								relatorioBean.setOrgaoExpedidorProprietario(linhaLida.substring(77, 81).trim());
								
								//9-Unidade Federacao do Cliente Proprietario - Tam 2
								relatorioBean.setUnidadeFederacaoProprietario(linhaLida.substring(81, 83).trim());
								
								//10-Data do Nascimento do Cliente Proprietario - Tam 8
								relatorioBean.setDataNascimentoProprietario(
									Util.converterDataSemBarraParaDataComBarra(linhaLida.substring(83, 91).trim()));
								
								//11-Profissao do Cliente Proprietario - Tam 18
								relatorioBean.setProfissaoProprietario(linhaLida.substring(91, 109).trim());

								//12-Sexo do Cliente Proprietario - Tam 1
								relatorioBean.setSexoProprietario(linhaLida.substring(109, 110).trim());
								
								//13-Nome da Mae do Cliente Proprietario - Tam 32
								relatorioBean.setNomeMaeProprietario(linhaLida.substring(110, 142).trim());
								
								//14-Indicador de Uso do Cliente Proprietario - Tam 1
								relatorioBean.setIndicadorUsoProprietario(linhaLida.substring(142, 143).trim());
								
								//15-Tipo de endereco - Tam 1
								relatorioBean.setTipoEnderecoProprietario(linhaLida.substring(143, 144).trim());
								
								//16-Codigo do logradouro do Cliente Proprietario - Tam 9
								relatorioBean.setCodigoLogradouroProprietario(linhaLida.substring(144, 153).trim());
								
								//17-Endereço do Cliente Proprietario - Tam 60
								relatorioBean.setEnderecoProprietario(linhaLida.substring(153, 213).trim());

								//18-Cep do Cliente Proprietario - Tam 8
								relatorioBean.setCepProprietario(linhaLida.substring(213, 221).trim());
								
								//19-Bairro do Cliente Proprietario - Tam 3
								relatorioBean.setBairroProprietario(linhaLida.substring(221, 224).trim());

								//20-Referencia do Cliente Proprietario - Tam 1
								relatorioBean.setReferenciaProprietario(linhaLida.substring(224, 225).trim());

								//21-Numero do Imovel do Cliente Proprietario - Tam 5
								relatorioBean.setNumeroImovelProprietario(linhaLida.substring(225, 230).trim());

								//22-Complemento do Cliente Proprietario - Tam 19
								relatorioBean.setComplementoProprietario(linhaLida.substring(230, 249).trim());
								
								
								//23-Tipo Telefone do Cliente Proprietario 1 - Tam 1
								relatorioBean.setTipoTelefone1Proprietario(linhaLida.substring(249, 250).trim());

								//24-DDD do Cliente Proprietario 1 - Tam 2
								relatorioBean.setDdd1Proprietario(linhaLida.substring(250, 252).trim());
								
								//25-Numero do Telefone do Cliente Proprietario 1 - Tam 8
								relatorioBean.setNumero1TelefoneProprietario(linhaLida.substring(252, 260).trim());
								
								//26-Ramal do Cliente Proprietario 2 - Tam 4
								relatorioBean.setRamal1Proprietario(linhaLida.substring(260, 264).trim());


								//27-Tipo Telefone do Cliente Proprietario 2 - Tam 1
								relatorioBean.setTipoTelefone1Proprietario(linhaLida.substring(264, 265).trim());

								//28-DDD do Cliente Proprietario 2 - Tam 2
								relatorioBean.setDdd2Proprietario(linhaLida.substring(265, 267).trim());
								
								//29-Numero do Telefone do Cliente Proprietario 2 - Tam 8
								relatorioBean.setNumero2TelefoneProprietario(linhaLida.substring(267, 275).trim());
								
								//30-Ramal do Cliente Proprietario 2 - Tam 4
								relatorioBean.setRamal2Proprietario(linhaLida.substring(275, 279).trim());
								
								//Aumenta em 8 no espaço seguindo o caso de uso
								
								//--Dados do Cliente Usuario--
								
								//31-Codigo do Cliente Usuario - Tam 9
								relatorioBean.setCodigoClienteUsuario(linhaLida.substring(287, 296).trim());
								
								//32-Nome do Cliente Usuario - Tam 23
								relatorioBean.setNomeClienteUsuario(linhaLida.substring(296, 319).trim());

								//33-Tipo do Cliente Usuario - Tam 2
								relatorioBean.setTipoClienteUsuario(linhaLida.substring(319, 321).trim());
								
								//34-Cpf do Cliente Usuario - Tam 14
								relatorioBean.setCpfCnpjUsuario(linhaLida.substring(321, 335).trim());
								
								//35-Rg do Cliente Usuario - Tam 13
								relatorioBean.setRgUsuario(linhaLida.substring(335, 348).trim());
								
								//36-Data de Emissao do Cliente Usuario - Tam 8
								relatorioBean.setDataEmissaoUsuario(
									Util.converterDataSemBarraParaDataComBarra(linhaLida.substring(348, 356).trim()));
								
								//37-Orgao Expedidor do Cliente Usuario - Tam 4
								relatorioBean.setOrgaoExpedidorUsuario(linhaLida.substring(356, 360).trim());
								
								//38-Unidade Federacao do Cliente Usuario - Tam 2
								relatorioBean.setUnidadeFederacaoUsuario(linhaLida.substring(360, 362).trim());
								
								//39-Data do Nascimento do Cliente Usuario - Tam 8
								relatorioBean.setDataNascimentoUsuario(
									Util.converterDataSemBarraParaDataComBarra(linhaLida.substring(362, 370).trim()));
								
								//40-Profissao do Cliente Usuario - Tam 18
								relatorioBean.setProfissaoUsuario(linhaLida.substring(370, 388).trim());

								//41-Sexo do Cliente Usuario - Tam 1
								relatorioBean.setSexoUsuario(linhaLida.substring(388, 389).trim());
								
								//42-Nome da Mae do Cliente Usuario - Tam 32
								relatorioBean.setNomeMaeUsuario(linhaLida.substring(389, 421).trim());
								
								//43-Indicador de Uso do Cliente Usuario - Tam 1
								relatorioBean.setIndicadorUsoUsuario(linhaLida.substring(421, 422).trim());
								
								//44-Tipo de endereco - Tam 1
								relatorioBean.setTipoEnderecoUsuario(linhaLida.substring(422, 423).trim());
								
								//45-Codigo do logradouro do Cliente Usuario - Tam 9
								relatorioBean.setCodigoLogradouroUsuario(linhaLida.substring(423, 432).trim());
								
								//46-Endereço do Cliente Usuario - Tam 60
								relatorioBean.setEnderecoUsuario(linhaLida.substring(432, 492).trim());

								//47-Cep do Cliente Usuario - Tam 8
								relatorioBean.setCepUsuario(linhaLida.substring(492, 500).trim());
								
								//48-Bairro do Cliente Usuario - Tam 3
								relatorioBean.setBairroUsuario(linhaLida.substring(500, 503).trim());

								//49-Referencia do Cliente Usuario - Tam 1
								relatorioBean.setReferenciaUsuario(linhaLida.substring(503, 504).trim());

								//50-Numero do Imovel do Cliente Usuario - Tam 5
								relatorioBean.setNumeroImovelUsuario(linhaLida.substring(504, 509).trim());

								//51-Complemento do Cliente Usuario - Tam 19
								relatorioBean.setComplementoUsuario(linhaLida.substring(509, 528).trim());
								
								
								//52-Tipo Telefone do Cliente Usuario 1 - Tam 1
								relatorioBean.setTipoTelefone1Usuario(linhaLida.substring(528, 529).trim());

								//53-DDD do Cliente Usuario 1 - Tam 2
								relatorioBean.setDdd1Usuario(linhaLida.substring(529, 531).trim());
								
								//54-Numero do Telefone do Cliente Usuario 1 - Tam 8
								relatorioBean.setNumero1TelefoneUsuario(linhaLida.substring(531, 539).trim());
								
								//55-Ramal do Cliente Usuario 2 - Tam 4
								relatorioBean.setRamal1Usuario(linhaLida.substring(539, 543).trim());


								//56-Tipo Telefone do Cliente Usuario 2 - Tam 1
								relatorioBean.setTipoTelefone1Usuario(linhaLida.substring(543, 544).trim());

								//57-DDD do Cliente Usuario 2 - Tam 2
								relatorioBean.setDdd2Usuario(linhaLida.substring(544, 546).trim());
								
								//58-Numero do Telefone do Cliente Usuario 2 - Tam 8
								relatorioBean.setNumero2TelefoneUsuario(linhaLida.substring(546, 554).trim());
								
								//59-Ramal do Cliente Usuario 2 - Tam 4
								relatorioBean.setRamal2Usuario(linhaLida.substring(554, 558).trim());
								
								//--adciona mais 8 espaços
								
								//----Cadastro Do Imovel------

								//60-Movimento - Tam 1
								relatorioBean.setMovimentoImovel(linhaLida.substring(566, 567).trim());
								
								//61-Inscricao Anterior - Tam 16
								relatorioBean.setInscricaoAnteriorImovel(linhaLida.substring(567, 583).trim());

								//62-Matricula do Imovel - Tam 9
								relatorioBean.setMatriculaImovel(linhaLida.substring(583, 592).trim());
								
								//63-Codigo do Cliente Proprietario - Tam 12

								//64-Inscricao Atual - Tam 16
								relatorioBean.setRamal2Usuario(linhaLida.substring(604, 620).trim());

								//65-Numero Moradores - Tam 4
								relatorioBean.setNumeroMoradores(linhaLida.substring(620, 624).trim());

								//66-Nome da Conta - Tam 1
								relatorioBean.setNomeConta(linhaLida.substring(624, 625).trim());

								//67-Codigo do Cliente Usuario - Tam 12

								//68-Codigo do Logradouro - Tam 9
								relatorioBean.setCodigoLogradouroImovel(linhaLida.substring(637, 646).trim());
								
								//69-Endereco do Imovel - Tam 60
								relatorioBean.setEnderecoImovel(linhaLida.substring(646, 706).trim());
								
								//70-CEP - Tam 8
								relatorioBean.setCepImovel(linhaLida.substring(706, 714).trim());

								//71-Bairro - Tam 3
								relatorioBean.setBairroImovel(linhaLida.substring(714, 717).trim());
								
								//72-Referencia - Tam 1
								relatorioBean.setReferenciaImovel(linhaLida.substring(717, 718).trim());
								
								//73-Numero do Imovel - Tam 5
								relatorioBean.setNumeroImovel(linhaLida.substring(718, 723).trim());

								//74-Complemento - Tam 19
								relatorioBean.setComplementoImovel(linhaLida.substring(723, 742).trim());
								
								if (linhaLida.length() > 1021) {
									//75-SubCategoria 1 - Tam 3
									relatorioBean.setSubCategoria1(linhaLida.substring(1021, 1024).trim());
									//77-SubCategoria 2 - Tam 3
									relatorioBean.setSubCategoria2(linhaLida.substring(1024, 1027).trim());
									//79-SubCategoria 3 - Tam 3
									relatorioBean.setSubCategoria3(linhaLida.substring(1027, 1030).trim());
									//81-SubCategoria 4 - Tam 3
									relatorioBean.setSubCategoria4(linhaLida.substring(1030, 1033).trim());
									//83-SubCategoria 5 - Tam 3
									relatorioBean.setSubCategoria5(linhaLida.substring(1033, 1036).trim());
									//85-SubCategoria 6 - Tam 3
									relatorioBean.setSubCategoria6(linhaLida.substring(1036, 1039).trim());
								} else {
									//75-SubCategoria 1 - Tam 2
									relatorioBean.setSubCategoria1(linhaLida.substring(742, 744).trim());
									//77-SubCategoria 2 - Tam 2
									relatorioBean.setSubCategoria2(linhaLida.substring(748, 750).trim());
									//79-SubCategoria 3 - Tam 2
									relatorioBean.setSubCategoria3(linhaLida.substring(754, 756).trim());
									//81-SubCategoria 4 - Tam 2
									relatorioBean.setSubCategoria4(linhaLida.substring(760, 762).trim());
									//83-SubCategoria 5 - Tam 2
									relatorioBean.setSubCategoria5(linhaLida.substring(766, 768).trim());
									//85-SubCategoria 6 - Tam 2
									relatorioBean.setSubCategoria6(linhaLida.substring(772, 774).trim());
								}
							
								//76-Quantidade de Economia 1 - Tam 4
								relatorioBean.setQuantidadeEconomias1(linhaLida.substring(744, 748).trim());

								//78-Quantidade de Economia 2 - Tam 4
								relatorioBean.setQuantidadeEconomias2(linhaLida.substring(750, 754).trim());
								
								//80-Quantidade de Economia 3 - Tam 4
								relatorioBean.setQuantidadeEconomias3(linhaLida.substring(756, 760).trim());

								//82-Quantidade de Economia 4 - Tam 4
								relatorioBean.setQuantidadeEconomias4(linhaLida.substring(762, 766).trim());
								
								//84-Quantidade de Economia 5 - Tam 4
								relatorioBean.setQuantidadeEconomias5(linhaLida.substring(768, 772).trim());
								
								//86-Quantidade de Economia 6 - Tam 4
								relatorioBean.setQuantidadeEconomias6(linhaLida.substring(774, 778).trim());
								
								//87-Hotel Qtd. Ap. - Tam 6
								relatorioBean.setQuantidadeApHotel(linhaLida.substring(778, 784).trim());
								
								//88-Area Construida - Tam 6
								relatorioBean.setAreaConstruida(linhaLida.substring(784, 790).trim());
								
								//89-Situacao Ligacao Agua - Tam 1
								relatorioBean.setSituacaoAgua(linhaLida.substring(790, 791).trim());

								//90-Diametro - Tam 1
								relatorioBean.setDiamentroAgua(linhaLida.substring(791, 792).trim());
								
								//91-Material - Tam 1
								relatorioBean.setMateriaAgua(linhaLida.substring(792, 793).trim());
								
								//92-Volume Inferior - Tam 1
								relatorioBean.setVolumeInferior(linhaLida.substring(793, 794).trim());
								
								//93-Volume Superior - Tam 1
								relatorioBean.setVolumeSuperior(linhaLida.substring(794, 795).trim());
								
								//94-Volume Piscina - Tam 1
								relatorioBean.setVolumePiscina(linhaLida.substring(795, 796).trim());

								//95-Jardim - Tam 1
								relatorioBean.setJardim(linhaLida.substring(796, 797).trim());

								//96-Pavimentação Calçada - Tam 2
								relatorioBean.setPavimentacaoCalcada(linhaLida.substring(797, 799).trim());

								//97-Pavimentação Rua - Tam 2
								relatorioBean.setPavimentacaoRua(linhaLida.substring(799, 801).trim());

								//98-Fonte Abastecimento - Tam 1
								relatorioBean.setFonteAbastecimento(linhaLida.substring(801, 802).trim());

								//99-Poço - Tam 1
								relatorioBean.setPoco(linhaLida.substring(802, 803).trim());
								
								//100-Numero de Pontos - Tam 4
								relatorioBean.setNumeroPontos(linhaLida.substring(803, 807).trim());
								
								//101-Situacao Esgoto - Tam 1
								relatorioBean.setSituacaoEsgoto(linhaLida.substring(807, 808).trim());
								
								//102-Diametro - Tam 1
								relatorioBean.setDiamentroEsgoto(linhaLida.substring(808, 809).trim());
								
								//103-Material - Tam 1
								relatorioBean.setMateriaAgua(linhaLida.substring(809, 810).trim());
								
								//104-Perfil do Imovel - Tam 1
								relatorioBean.setPerfilImovel(linhaLida.substring(810, 811).trim());

								//105-Despejo - Tam 1
								relatorioBean.setPerfilImovel(linhaLida.substring(811, 812).trim());
								
								//106-Leitura Inicial - Tam 6
								relatorioBean.setLeituraInicial(linhaLida.substring(812, 818).trim());
								
								//107-Capacidade Hidrometro - Tam 2
								relatorioBean.setCapacidade(linhaLida.substring(818, 820).trim());
								
								//108-Marca Hidrometro - Tam 2
								relatorioBean.setMarca(linhaLida.substring(820, 822).trim());
								
								//109-Local Hidrometro - Tam 2
								relatorioBean.setLocal(linhaLida.substring(822, 824).trim());
								
								//110-Proteção Hidrometro - Tam 1
								relatorioBean.setProtecao(linhaLida.substring(824, 825).trim());
								
								//111-Cavalete Hidrometro - Tam 1
								relatorioBean.setCavalete(linhaLida.substring(825, 826).trim());
								
								//Numero do Iptu - Tam 26
								//Contrato Cia Eletricidade - Tam 10

								//112-Codigo Rota - Tam 7
								relatorioBean.setCodigoRota(linhaLida.substring(862, 869).trim());
								
								//113-Sequencial Rota - Tam 8
								relatorioBean.setSequencialRota(linhaLida.substring(869, 877).trim());

								//114-Valor Debito - Tam 11
								relatorioBean.setValorDebitos(linhaLida.substring(877, 888).trim());
								
								//115-Descrição Abreviada - Tam 3
								relatorioBean.setDescricaoAbreviadaPrincipalCategoria(linhaLida.substring(888, 891).trim());
								
								retorno.add(relatorioBean);
							}
						}//fim do while eof
					} else {
						throw new ActionServletException("atencao.tipo_importacao.nao_txt");
					}
				}	
			}

		} catch (IOException ex) {
			throw new ActionServletException("erro.importacao.nao_concluida");
		}
	
		return retorno;
	}
	
	
	public String tratarNomeArquivo(String caminho) {
		
		if (caminho.indexOf("\\") != -1){
			caminho = caminho.substring(caminho.lastIndexOf("\\") + 1);
		}
	
		return caminho;
	}


}