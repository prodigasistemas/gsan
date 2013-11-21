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
package gcom.cadastro;

import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.debito.DebitoTipo;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.ZipUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.zip.ZipOutputStream;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;

import br.com.danhil.BarCode.Interleaved2of5;

/**
 * Controlador Cadastro CAERN
 *
 * @author Rômulo Aurélio
 * @date 24/11/2009
 */
public class ControladorCadastroCAERNSEJB extends ControladorCadastro implements SessionBean{
	
	private static final long serialVersionUID = 1L;
	
	//==============================================================================================================
	// MÉTODOS EXCLUSIVOS DA CAERN
	//==============================================================================================================

	
	
	/**
	 * [UC0925] Emitir Boletos
	 *
	 * Alteracao para o caso da processamento da CAERN, 
	 * considerar todos os municipios e o tipo de debito 202
	 * 
	 * @author Rômulo Aurélio / Analista: Rafael Pinto
	 * @date 25/11/2009
	 */
	public void emitirBoletos(Integer idFuncionalidadeIniciada,Integer grupo)throws ControladorException{
        
        int idUnidadeIniciada = 0;
       
        try {
	        /*
	         * Registrar o início do processamento da Unidade de
	         * Processamento do Batch
	        */
        	idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.FUNCIONALIDADE,0);
            
    		boolean flagFimPesquisa = false;
    		final int quantidadeCobrancaDocumento = 1000;
    		int quantidadeInicio = 0;
    		StringBuilder boletoTxt = new StringBuilder();

    		System.out.println("***************************************");
    		System.out.println("EMITIR BOLETOS");
    		System.out.println("***************************************");
    		
    		SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
    		
    		while (!flagFimPesquisa) {
    			Collection dadosBoleto = repositorioCadastro.pesquisarDadosBoleto(
    					quantidadeInicio,grupo,sistemaParametro.getNomeAbreviadoEmpresa());
    			
    			if(dadosBoleto != null && !dadosBoleto.isEmpty()){
    				
    				Iterator iterDadosBoleto = dadosBoleto.iterator();
            		

					if (dadosBoleto.size() < quantidadeCobrancaDocumento) {
						flagFimPesquisa = true;
					} else {
						quantidadeInicio = quantidadeInicio + 1000;
					}
    				
					System.out.println("***************************************");
					System.out.println("QUANTIDADE :" + dadosBoleto.size());
					System.out.println("***************************************");
					
            		while (iterDadosBoleto.hasNext()) {
            			DadosBoletoHelper helper = (DadosBoletoHelper) iterDadosBoleto.next();
            			
        				//1.1 Inscrição
        				boletoTxt.append(Util.completaString(helper.getImovel().getInscricaoFormatada(), 20));
        				
        				//1.2 Matrícula Imóvel
        				String matriculaStr = Util.adicionarZerosEsquedaNumero(9, "" + helper.getImovel().getId()); 
        				boletoTxt.append(matriculaStr.substring(0,8) + "." + matriculaStr.substring(8,9));
        				
        				//1.3 Nome Cliente Usuário
        				boletoTxt.append(Util.completaString(helper.getNomeCliente(), 40));
            							
        				//1.4 Endereço do Imóvel
        				String endereco = getControladorEndereco().pesquisarEnderecoFormatado(helper.getImovel().getId());
        				boletoTxt.append(Util.completaString(endereco, 60));				
            							
            			//1.5 Grupo de Faturamento
        				boletoTxt.append(Util.adicionarZerosEsquedaNumero(2,helper.getIdGrupoFaturamento().toString()));	
        				
        				//1.6 Empresa
        				boletoTxt.append(helper.getIdEmpresa().toString());	
            							
            							
            			//1.7 Representação Numérica do Código de Barras
        				//1.8 Código de Barras
        				
        				BigDecimal valorCodigoBarras = pesquisarValorSugeridoDebitoTipo(DebitoTipo.DOACAO_AO_HOSPITAL_INFANTIL_VARELA_SANTIAGO);
            										
        				String representacaoNumericaCodBarra = "";

        				// Obtém a representação numérica do código de barra
        				representacaoNumericaCodBarra = this.getControladorArrecadacao().obterRepresentacaoNumericaCodigoBarra(
        								4,
        								valorCodigoBarras,
        								helper.getImovel().getLocalidade().getId(),
        								helper.getImovel().getId(),
        								null,
        								null,
        								DebitoTipo.DOACAO_AO_HOSPITAL_INFANTIL_VARELA_SANTIAGO,
        								"" + Util.getAno(new Date()),
        								null,
        								null,
        								null,
        								null,
        								null);

        				// Formata a representação númerica do código de barras
        				String representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarra.substring(0, 11)
        						+ " " + representacaoNumericaCodBarra.substring(11, 12)
        						+ " " + representacaoNumericaCodBarra.substring(12, 23)
        						+ " " + representacaoNumericaCodBarra.substring(23, 24)
        						+ " " + representacaoNumericaCodBarra.substring(24, 35)
        						+ " " + representacaoNumericaCodBarra.substring(35, 36)
        						+ " " + representacaoNumericaCodBarra.substring(36, 47)
        						+ " " + representacaoNumericaCodBarra.substring(47, 48);

        				boletoTxt.append(representacaoNumericaCodBarraFormatada);

        				// Cria o objeto para gerar o código de barras no padrão intercalado 2 de 5
        							Interleaved2of5 codigoBarraIntercalado2de5 = new Interleaved2of5();
         
        							// Recupera a representação númerica do código de barras sem os dígitos verificadores 
        				String representacaoCodigoBarrasSemDigitoVerificador = 
        					    representacaoNumericaCodBarra.substring(0, 11)
        						+ representacaoNumericaCodBarra.substring(12, 23)
        						+ representacaoNumericaCodBarra.substring(24, 35)
        						+ representacaoNumericaCodBarra.substring(36, 47);

        				boletoTxt.append(codigoBarraIntercalado2de5.encodeValue(representacaoCodigoBarrasSemDigitoVerificador));
        				// Codigo da Rota tam (02)
        				boletoTxt.append(Util.adicionarZerosEsquedaNumeroTruncando(2,helper.getCodigoRota().toString()));
        				//Sequencial da Rota Tam (04)
        				boletoTxt.append("." + Util.adicionarZerosEsquedaNumeroTruncando(4,helper.getSequencialRota().toString()));
        				
        				boletoTxt.append(System.getProperty("line.separator"));
            			
            		}	
    				
    			}else{
    				flagFimPesquisa = true;
    			}
    			
    		}
    		
    		    		
    		Date dataAtual = new Date();

    		String nomeZip = null;

    		nomeZip = "BOLETO_HOSPITAL_INFANTIL_VARELA_SANTIAGO_GRUPO_" + grupo + "_" + Util.formatarData(dataAtual) + Util.formatarHoraSemDataSemDoisPontos(dataAtual);
    		nomeZip = nomeZip.replace("/", "_");

    		// pegar o arquivo, zipar pasta e arquivo e escrever no stream
    		try {

    			System.out.println("***************************************");
    			System.out.println("INICO DA CRIACAO DO ARQUIVO");
    			System.out.println("***************************************");

    			if (boletoTxt != null && boletoTxt.length() != 0) {

    				// criar o arquivo zip
    				File compactado = new File(nomeZip + ".zip"); // nomeZip
    				ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(compactado));

    				File leitura = new File(nomeZip + ".txt");
    				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leitura.getAbsolutePath())));
    				out.write(boletoTxt.toString());
    				out.close();
    				ZipUtil.adicionarArquivo(zos, leitura);

    				// close the stream
    				zos.close();
    				leitura.delete();
    			}
    			System.out.println("***************************************");
    			System.out.println("FIM DA CRIACAO DO ARQUIVO");
    			System.out.println("***************************************");

    		} catch (IOException e) {
    			e.printStackTrace();
    			throw new ControladorException("erro.sistema", e);
    		} catch (Exception e) {
    			e.printStackTrace();
    			throw new ControladorException("erro.sistema", e);
    		}
            
            getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
        System.out.println("******* FIM **********");
        } catch (Exception ex) {
            ex.printStackTrace();
            getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,idUnidadeIniciada, true);
            throw new EJBException(ex);
        }
        
    }
	

}
