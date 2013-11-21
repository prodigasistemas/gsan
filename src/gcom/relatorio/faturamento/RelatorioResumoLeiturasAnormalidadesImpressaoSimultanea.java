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
package gcom.relatorio.faturamento;

import gcom.relatorio.ConstantesRelatorios;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;


/**
 * 
 * Este caso de uso permite a inserção de dados na tabela movimento 
 * conta pré-faturada.
 *
 * [UC0923] Incluir Movimento Conta Pré-Faturada
 *
 *
 *  Caso seja chamado por uma tela, o sistema gera uma tela de acordo com o 
 *  movimento, Caso contrário, o sistema gera um relatório e envia, por 
 *  e-mail para o operador, registrado com os seguintes campos:
 *  
 *  No cabeçalho imprimir o grupo de faturamento informado (FTGR_ID), o 
 *  código e descrição da empresa (EMPR_ID e EMPR_NMEMPRESA da tabela 
 *  EMPRESA com EMPR_ID da tabela ROTA com ROTA_ID da tabela QUADRA com 
 *  QDRA_ID da tabela IMOVEL com IMOV_ID=matrícula do imóvel do primeiro 
 *  registro do arquivo que exista na tabela IMOVEL), o código da localidade 
 *  e o título fixo “MOVIMENTO CELULAR - IMPRESSÃO SIMULTÂNEA” quando 
 *  processado o arquivo de movimento;
 *  
 *  Imprimir o erro correspondente encontrado no processamento do imóvel;
 *  
 *  Caso seja chamado por uma tela, imprimir um texto “Arquivo processado 
 *  com problema e enviado para operação para processar o movimento. 
 *  Localidade <<Código da Localidade>>”;    
 *
 * [SB0001] - Gera Tela Resumo das leituras e anormalidades da impressão 
 * simultânea com Problemas
 *
 * @author bruno
 * @date 30/06/2009
 *
 * @param colErrors 
 */ 
public class RelatorioResumoLeiturasAnormalidadesImpressaoSimultanea extends TarefaRelatorio {
    
	private static final long serialVersionUID = 1L;
	
	private byte[] relatorio = null; 
    
	public RelatorioResumoLeiturasAnormalidadesImpressaoSimultanea(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RESUMO_LEITURAS_ANORMALIDADE_IMPRESSAO_SIMULTANEA );
	}
	
	@Deprecated
	public RelatorioResumoLeiturasAnormalidadesImpressaoSimultanea() {
		super(null, "");
	}
	public Object executar() throws TarefaException {
        return relatorio;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

        return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("relatorioResumoLeiturasAnormalidadesRegistradas", this);
	}

	public byte[] getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(byte[] relatorio) {
		this.relatorio = relatorio;
	}

}