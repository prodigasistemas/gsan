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
package gcom.faturamento.bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

/**
 * [UC1225] Incluir Dados Acompanhamento de Serviço
 * 
 * Este caso de uso permite a inserção de dados na tabela de ordem de serviço para acompanhamento do serviço.
 * 
 * @author Thúlio Araújo
 * @date 22/09/2011
 */
public class IncluirDadosAcompanhamentoServicoHelper {
    
    public static final String REGISTRO_TIPO_1 = "01";
    public static final String REGISTRO_TIPO_2 = "02";
    public static final String REGISTRO_TIPO_3 = "03";
    public static final String REGISTRO_TIPO_4 = "04";
    
    // Campos comuns a todos
    private String tipoRegistro;
    private String idOrdemServico;
    
    // Tipo de registro 1
    private String rg1IdEquipamentoEspecialFaltante;
    private String rg1IdOsProgramacaoNaoEncerrarMotivo;
    private String rg1IdOsSituacao;
    private String rg1DsPontoReferencia;
    private String rg1DtProgramacamo;
    private String rg1NumeroImovel;
    
    // Tipo de registro 2
    private String rg2IdOsAtividade;
    private String rg2IdAtividade;
    private String rg2IdOsSituacao;
    private String rg2IdEquipamentoEspecialFaltante;
    private String rg2IdPrestadorServico;
    private String rg2QtdMaterialExcedente;
    
    // Tipo de registro 3
    private String rg3IdOsAtividadePrgAcompServico;
    private String rg3IdMaterial;
    private String rg3QtdMaterial;
    
    // Tipo de registro 4
    private String rg4DataExecucaoInicio;
    private String rg4DataExecucaoFim;
    private String rg4IdOsAtividadePrgAcompServico;
    
    /**
     * Método usado para extrair uma linha do buffer e enviar para o parse
     * 
     * @author Thúlio Aráujo
     * @param buffer
     * @return Collection<IncluirDadosAcompanhamentoServicoHelper>
     * @throws IOException
     */
    public Collection<IncluirDadosAcompanhamentoServicoHelper> parseHelperArquivo(BufferedReader buffer) throws IOException {
		String linha = "";
		
		Collection<IncluirDadosAcompanhamentoServicoHelper> retorno = new ArrayList<IncluirDadosAcompanhamentoServicoHelper>();
		IncluirDadosAcompanhamentoServicoHelper helper = null;
		
		while ( ( linha = buffer.readLine() ) != null ){
			helper = parseHelper( linha );
			if (helper != null && !helper.equals(null)){
				retorno.add(helper);
			}
		}
		
		return retorno;
    }
    
    /**
     * Retorna o helper com cada registro ara inserir na coleção
     * 
     * @author Thúlio Araújo
     * @date 23/09/2011
     * 
     * @param linha
     * @return IncluirDadosAcompanhamentoServicoHelper
     * @throws IOException
     */
    public IncluirDadosAcompanhamentoServicoHelper 
        parseHelper( String linha ) throws IOException{
        
    	// Vetor que contem todos os elementos que presentes
		// linha
		Vector<String> linhaElementos = new Vector<String>();
		IncluirDadosAcompanhamentoServicoHelper helper = null;
		
		// Quebramos a linha em um vetor de Strings
		String linhaFormatada[] = linha.split("\\|");

		for (int j = 0; j < linhaFormatada.length; j++) { 
			linhaElementos.addElement(linhaFormatada[j]);
		}
    	
		// Registro Tipo 1
		if (linha.substring(0, 2).equals(REGISTRO_TIPO_1)) {
			helper = parserRegistroTipo1(linhaElementos);
		}
		
		// Registro Tipo 2
		if (linha.substring(0, 2).equals(REGISTRO_TIPO_2)) {
			helper = parserRegistroTipo2(linhaElementos);
		}
		
		// Registro Tipo 3
		if (linha.substring(0, 2).equals(REGISTRO_TIPO_3)) {
			helper = parserRegistroTipo3(linhaElementos);
		}
		
		// Registro Tipo 4
		if (linha.substring(0, 2).equals(REGISTRO_TIPO_4)) {
			helper = parserRegistroTipo4(linhaElementos);
		}
		
        return helper;           
    }
   
    /**
     * Método usado para preencher os campos do Registro Tipo 1
     * 
     * @author Thúlio Araújo
     * @since 23/09/2011
     * @param obj
     * @return IncluirDadosAcompanhamentoServicoHelper
     */
    private IncluirDadosAcompanhamentoServicoHelper parserRegistroTipo1(Vector<String> obj){
    	IncluirDadosAcompanhamentoServicoHelper incluirDadosAcompanhamentoServicoHelper = 
    		new IncluirDadosAcompanhamentoServicoHelper();
    	
    	incluirDadosAcompanhamentoServicoHelper.setTipoRegistro(ignoreNull( obj.get(0)));
    	incluirDadosAcompanhamentoServicoHelper.setIdOrdemServico(ignoreNull( obj.get(1)));
    	incluirDadosAcompanhamentoServicoHelper.setRg1IdEquipamentoEspecialFaltante(ignoreNull( obj.get(2)));
    	incluirDadosAcompanhamentoServicoHelper.setRg1IdOsProgramacaoNaoEncerrarMotivo(ignoreNull( obj.get(3)));
    	incluirDadosAcompanhamentoServicoHelper.setRg1IdOsSituacao(ignoreNull( obj.get(4)));
    	incluirDadosAcompanhamentoServicoHelper.setRg1DtProgramacamo(ignoreNull( obj.get(5)));
    	incluirDadosAcompanhamentoServicoHelper.setRg1DsPontoReferencia(ignoreNull( obj.get(6)));
    	incluirDadosAcompanhamentoServicoHelper.setRg1NumeroImovel(ignoreNull( obj.get(7)));
		
		return incluirDadosAcompanhamentoServicoHelper;
	}
    
    /**
     * Método usado para preencher os campos do Registro Tipo 2
     * 
     * @author Thúlio Araújo
     * @since 23/09/2011
     * @param obj
     * @return IncluirDadosAcompanhamentoServicoHelper
     */
    public static IncluirDadosAcompanhamentoServicoHelper parserRegistroTipo2(Vector<String> obj){
    	IncluirDadosAcompanhamentoServicoHelper incluirDadosAcompanhamentoServicoHelper = 
    		new IncluirDadosAcompanhamentoServicoHelper();
    	
    	incluirDadosAcompanhamentoServicoHelper.setTipoRegistro(ignoreNull( obj.get(0)));
    	incluirDadosAcompanhamentoServicoHelper.setRg2IdOsAtividade(ignoreNull( obj.get(1)));
    	incluirDadosAcompanhamentoServicoHelper.setIdOrdemServico(ignoreNull( obj.get(2)));
    	incluirDadosAcompanhamentoServicoHelper.setRg2IdAtividade(ignoreNull( obj.get(3)));
    	incluirDadosAcompanhamentoServicoHelper.setRg2IdOsSituacao(ignoreNull( obj.get(4)));
    	incluirDadosAcompanhamentoServicoHelper.setRg2IdEquipamentoEspecialFaltante(ignoreNull( obj.get(5)));
    	incluirDadosAcompanhamentoServicoHelper.setRg2IdPrestadorServico(ignoreNull( obj.get(6)));
    	incluirDadosAcompanhamentoServicoHelper.setRg2QtdMaterialExcedente(ignoreNull( obj.get(7)));
    	
		return incluirDadosAcompanhamentoServicoHelper;
	}
    
    /**
     * 
     * Método usado para preencher os campos do Registro Tipo 3
     * @author Thúlio Araújo
     * @since 23/09/2011
     * @param obj
     * @return IncluirDadosAcompanhamentoServicoHelper
     */
    public static IncluirDadosAcompanhamentoServicoHelper parserRegistroTipo3(Vector<String> obj){
    	IncluirDadosAcompanhamentoServicoHelper incluirDadosAcompanhamentoServicoHelper = 
    		new IncluirDadosAcompanhamentoServicoHelper();
    	
    	incluirDadosAcompanhamentoServicoHelper.setTipoRegistro(ignoreNull( obj.get(0)));
    	//incluirDadosAcompanhamentoServicoHelper.setIdOrdemServico(ignoreNull( obj.get(1)));
    	incluirDadosAcompanhamentoServicoHelper.setRg3IdMaterial(ignoreNull( obj.get(1)));
    	incluirDadosAcompanhamentoServicoHelper.setRg3IdOsAtividadePrgAcompServico(ignoreNull( obj.get(2)));
    	incluirDadosAcompanhamentoServicoHelper.setRg3QtdMaterial(ignoreNull( obj.get(3)));
    	
		return incluirDadosAcompanhamentoServicoHelper;
	}
    
    /**
     * Método usado para preencher os campos do Registro Tipo4
     * 
     * @author Thúlio Araújo
     * @since 23/09/2011
     * @param obj
     * @return IncluirDadosAcompanhamentoServicoHelper
     */
    public static IncluirDadosAcompanhamentoServicoHelper parserRegistroTipo4(Vector<String> obj){
    	IncluirDadosAcompanhamentoServicoHelper incluirDadosAcompanhamentoServicoHelper = 
    		new IncluirDadosAcompanhamentoServicoHelper();
    	
    	incluirDadosAcompanhamentoServicoHelper.setTipoRegistro(ignoreNull( obj.get(0)));
    	incluirDadosAcompanhamentoServicoHelper.setRg4IdOsAtividadePrgAcompServico(ignoreNull( obj.get(1)));
    	incluirDadosAcompanhamentoServicoHelper.setRg4DataExecucaoInicio(ignoreNull( obj.get(2)));
    	incluirDadosAcompanhamentoServicoHelper.setRg4DataExecucaoFim(ignoreNull( obj.get(3)));
    	//incluirDadosAcompanhamentoServicoHelper.setRg4IdOsProgramacaoAcompanhamentoServico(ignoreNull( obj.get(4)));

    	return incluirDadosAcompanhamentoServicoHelper;
	}

	public String getRg1DsPontoReferencia() {
		return rg1DsPontoReferencia;
	}

	public void setRg1DsPontoReferencia(String rg1DsPontoReferencia) {
		this.rg1DsPontoReferencia = rg1DsPontoReferencia;
	}

	public String getRg1IdEquipamentoEspecialFaltante() {
		return rg1IdEquipamentoEspecialFaltante;
	}

	public void setRg1IdEquipamentoEspecialFaltante(
			String rg1IdEquipamentoEspecialFaltante) {
		this.rg1IdEquipamentoEspecialFaltante = rg1IdEquipamentoEspecialFaltante;
	}

	public String getRg1IdOsProgramacaoNaoEncerrarMotivo() {
		return rg1IdOsProgramacaoNaoEncerrarMotivo;
	}

	public void setRg1IdOsProgramacaoNaoEncerrarMotivo(
			String rg1IdOsProgramacaoNaoEncerrarMotivo) {
		this.rg1IdOsProgramacaoNaoEncerrarMotivo = rg1IdOsProgramacaoNaoEncerrarMotivo;
	}

	public String getRg1IdOsSituacao() {
		return rg1IdOsSituacao;
	}

	public void setRg1IdOsSituacao(String rg1IdOsSituacao) {
		this.rg1IdOsSituacao = rg1IdOsSituacao;
	}

	public String getRg1NumeroImovel() {
		return rg1NumeroImovel;
	}

	public void setRg1NumeroImovel(String rg1NumeroImovel) {
		this.rg1NumeroImovel = rg1NumeroImovel;
	}

	public String getRg2IdOsAtividade() {
		return rg2IdOsAtividade;
	}

	public void setRg2IdOsAtividade(String rg2IdOsAtividade) {
		this.rg2IdOsAtividade = rg2IdOsAtividade;
	}

	public String getRg2IdAtividade() {
		return rg2IdAtividade;
	}

	public void setRg2IdAtividade(String rg2IdAtividade) {
		this.rg2IdAtividade = rg2IdAtividade;
	}

	public String getRg2IdEquipamentoEspecialFaltante() {
		return rg2IdEquipamentoEspecialFaltante;
	}

	public void setRg2IdEquipamentoEspecialFaltante(
			String rg2IdEquipamentoEspecialFaltante) {
		this.rg2IdEquipamentoEspecialFaltante = rg2IdEquipamentoEspecialFaltante;
	}

	public String getRg2IdOsSituacao() {
		return rg2IdOsSituacao;
	}

	public void setRg2IdOsSituacao(String rg2IdOsSituacao) {
		this.rg2IdOsSituacao = rg2IdOsSituacao;
	}

	public String getRg2IdPrestadorServico() {
		return rg2IdPrestadorServico;
	}

	public void setRg2IdPrestadorServico(String rg2IdPrestadorServico) {
		this.rg2IdPrestadorServico = rg2IdPrestadorServico;
	}

	public String getRg2QtdMaterialExcedente() {
		return rg2QtdMaterialExcedente;
	}

	public void setRg2QtdMaterialExcedente(String rg2QtdMaterialExcedente) {
		this.rg2QtdMaterialExcedente = rg2QtdMaterialExcedente;
	}

	public String getRg3IdMaterial() {
		return rg3IdMaterial;
	}

	public void setRg3IdMaterial(String rg3IdMaterial) {
		this.rg3IdMaterial = rg3IdMaterial;
	}

	public String getRg3IdOsAtividadePrgAcompServico() {
		return rg3IdOsAtividadePrgAcompServico;
	}

	public void setRg3IdOsAtividadePrgAcompServico(
			String rg3IdOsAtividadePrgAcompServico) {
		this.rg3IdOsAtividadePrgAcompServico = rg3IdOsAtividadePrgAcompServico;
	}

	public String getRg3QtdMaterial() {
		return rg3QtdMaterial;
	}

	public void setRg3QtdMaterial(String rg3QtdMaterial) {
		this.rg3QtdMaterial = rg3QtdMaterial;
	}

	public String getRg4DataExecucaoFim() {
		return rg4DataExecucaoFim;
	}

	public void setRg4DataExecucaoFim(String rg4DataExecucaoFim) {
		this.rg4DataExecucaoFim = rg4DataExecucaoFim;
	}

	public String getRg4DataExecucaoInicio() {
		return rg4DataExecucaoInicio;
	}

	public void setRg4DataExecucaoInicio(String rg4DataExecucaoInicio) {
		this.rg4DataExecucaoInicio = rg4DataExecucaoInicio;
	}

	

	public String getRg4IdOsAtividadePrgAcompServico() {
		return rg4IdOsAtividadePrgAcompServico;
	}

	public void setRg4IdOsAtividadePrgAcompServico(String rg4IdOsAtividadePrgAcompServico) {
		this.rg4IdOsAtividadePrgAcompServico = rg4IdOsAtividadePrgAcompServico;
	}

	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public String getRg1DtProgramacamo() {
		return rg1DtProgramacamo;
	}

	public void setRg1DtProgramacamo(String rg1DtProgramacamo) {
		this.rg1DtProgramacamo = rg1DtProgramacamo;
	}
	
	public static String ignoreNull( String string ){
		if (string != null && (string.trim().equals( "" ) || string.trim().equals( "null" )) ){
			return null;
		} else {
			return string;
		}
	}
}
