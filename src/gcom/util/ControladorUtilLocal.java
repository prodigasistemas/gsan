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
package gcom.util;

import gcom.cadastro.DbVersaoBase;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.filtro.Filtro;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * < <Descrição da Interface>>
 * 
 * @author rodrigo
 */
public interface ControladorUtilLocal extends javax.ejb.EJBLocalObject {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param classe
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public int registroMaximo(Class classe) throws ControladorException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param classe
	 *            Descrição do parâmetro
	 * @param atributo
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public int valorMaximo(Class classe, String atributo)
			throws ControladorException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param classe
	 *            Descrição do parâmetro
	 * @param atributo
	 *            Descrição do parâmetro
	 * @param parametro1
	 *            Descrição do parâmetro
	 * @param parametro2
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public int valorMaximo(Class classe, String atributo, String parametro1,
			String parametro2) throws ControladorException;

	/**
	 * Retorna o único registro do SistemaParametros.
	 * 
	 * @return Descrição do retorno
	 */
	public SistemaParametro pesquisarParametrosDoSistema()
			throws ControladorException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param filtro
	 *            Descrição do parâmetro
	 * @param pacoteNomeObjeto
	 *            Descrição do parâmetro
	 * @param limite
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public Collection limiteMaximoFiltroPesquisa(Filtro filtro,
			String pacoteNomeObjeto, int limite) throws ControladorException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param objeto
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public Object inserir(Object objeto) throws ControladorException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param filtro
	 *            Descrição do parâmetro
	 * @param pacoteNomeObjeto
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public Collection pesquisar(Filtro filtro, String pacoteNomeObjeto)
			throws ControladorException;

	public Collection pesquisar(Collection ids, Filtro filtro,
			String pacoteNomeObjeto) throws ControladorException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param ids
	 *            Descrição do parâmetro
	 * @param pacoteNomeObjeto
	 *            Descrição do parâmetro
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public void remover(String[] ids, String pacoteNomeObjeto,
			OperacaoEfetuada operacaoEfetuada, Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper)
			throws ControladorException;

	public void remover(Object object) throws ControladorException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param id
	 *            Description of the Parameter
	 * @param pacoteNomeObjeto
	 *            Descrição do parâmetro
	 * @exception RemoteException
	 *                Description of the Exception
	 */
	public void removerUm(int id, String pacoteNomeObjeto,
			OperacaoEfetuada operacaoEfetuada, Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper)
			throws ControladorException;

	/**
	 * Description of the Method
	 * 
	 * @param objeto
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 * @exception RemoteException
	 *                Description of the Exception
	 */
	public Object inserirOuAtualizar(Object objeto) throws ControladorException;

	/**
	 * Atualiza Banco de Dados - Roberta Costa
	 * 
	 * @param objeto
	 *            Descrição do parâmetro
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public void atualizar(Object objeto) throws ControladorException;

	/**
	 * Este método recebe 2 datas e as compara gerando uma exceção se a data fim
	 * for menor que a data inicio
	 * 
	 * @author Vivianne Sousa
	 * @created 18/03/2006
	 * 
	 * @param inicio
	 *            data inicio
	 * @param fim
	 *            data fim
	 * @param msgErro
	 *            constante do application.properties definida no caso de uso
	 * @throws ControladorException
	 */
	public void validarCampoFinalMaiorIgualCampoInicial(Date inicio, Date fim,
			String msgErro) throws ControladorException;

	/**
	 * Este método recebe 2 inteiros e os compara gerando uma exceção se o campo
	 * fim for menor que a campo inicio
	 * 
	 * @author Vivianne Sousa
	 * @created 18/03/2006
	 * 
	 * @param inicio
	 *            campo inicio
	 * @param fim
	 *            campo fim
	 * @param msgErro
	 *            constante do application.properties definida no caso de uso
	 * @throws ControladorException
	 */
	public void validarCampoFinalMaiorIgualCampoInicial(Integer inicio,
			Integer fim, String msgErro) throws ControladorException;

	/**
	 * Este método recebe 2 bigDecimal e os compara gerando uma exceção se o
	 * campo fim for menor que a campo inicio
	 * 
	 * @author Vivianne Sousa
	 * @created 18/03/2006
	 * 
	 * @param inicio
	 *            campo inicio
	 * @param fim
	 *            campo fim
	 * @param msgErro
	 *            constante do application.properties definida no caso de uso
	 * @throws ControladorException
	 */
	public void validarCampoFinalMaiorIgualCampoInicial(BigDecimal inicio,
			BigDecimal fim, String msgErro) throws ControladorException;

	/**
	 * Este método recebe 1 data e compara com a data atual gerando uma exceção
	 * se a data atual for menor que a data passada por paramentro
	 * 
	 * @author Vivianne Sousa
	 * @created 22/03/2006
	 * 
	 * @param data
	 * @param msgErro
	 *            constante do application.properties definida no caso de uso
	 * @throws ControladorException
	 */
	public void validarDataMenorDataAtual(Date data, String msgErro)
			throws ControladorException;

	/**
	 * Este método recebe 1 anoMes e compara com o anoMesAtual gerando uma
	 * exceção se o anoMesAtual for menor que o anoMes passado por paramentro
	 * 
	 * @author Vivianne Sousa
	 * @created 22/03/2006
	 * 
	 * @param anoMes
	 * @param msgErro
	 *            constante do application.properties definida no caso de uso
	 * @throws ControladorException
	 */
	public void validarAnoMesMenorAnoMesAtual(Integer anoMes, String msgErro)
			throws ControladorException;

	/**
	 * Este método de pesquisa serve para localizar qualquer objeto no sistema.
	 * Ele aceita como parâmetro um offset que indica a página desejada no
	 * esquema de paginação. A paginação procura 10 registros de casa vez.
	 * 
	 * @author Rodrigo Silveira
	 * @date 30/03/2006
	 * 
	 * @param filtro
	 *            Filtro da pesquisa
	 * @param pageOffset
	 *            Indicador da página desejada do esquema de paginação
	 * @param pacoteNomeObjeto
	 *            Pacote do objeto
	 * @return Coleção dos resultados da pesquisa
	 * @throws ControladorException
	 *             Exceção do controlador
	 */
	public Collection pesquisar(Filtro filtro, int pageOffset,
			String pacoteNomeObjeto) throws ControladorException;

	/**
	 * Informa o número total de registros de uma pesquisa, auxiliando o
	 * esquema de paginação
	 * 
	 * @author Rodrigo Silveira
	 * @date 30/03/2006
	 * 
	 * @param Filtro
	 *            da Pesquisa
	 * @param Pacote
	 *            do objeto pesquisado
	 * @return Número de registros da pesquisa
	 * @throws ControladorException
	 *             Exceção do controlador
	 */
	public int totalRegistrosPesquisa(Filtro filtro, String pacoteNomeObjeto)
			throws ControladorException;
	
	/**
	 * Verificar referência final menor que referência inicial
	 * 
	 * @author Rafael Santos
	 * @throws ControladorException
	 * @date 10/05/2006
	 */
	public void validarAnoMesInicialFinalPeriodo(
			String anoMesReferenciaInicial, String anoMesReferenciaFinal,
			String descricaoCampoAnoMesReferenciaInicial,
			String descricaoAnoMesReferenciaFinal,
			String mensagemErroDoApplicationProperties)
			throws ControladorException; 

	/**
	 * Verificar data final menos que data inicial
	 * @author Rafael Santos
	 * @throws ControladorException
	 * @date 10/05/2006
	 */
	public void verificarDataInicialFinalPeriodo(
			String dataPeriodoInicial, String dataPeriodoFinal,
			String descricaoCampoDataReferenciaInicial,
			String descricaoDataReferenciaFinal,
			String mensagemErroDoApplicationProperties)
			throws ControladorException; 	
	
	/**
	 * Método que insere uma Lista em Batch
	 *
	 * inserirBatch
	 *
	 * @author Roberta Costa
	 * @date 17/05/2006
	 *
	 * @param list
	 * @throws ControladorException
	 */
	public void inserirBatch(List list) throws ControladorException;
	
	/**
	 * FAVOR NÃO USAR!!!
	 * Método para ser utilizada apenas em logradouro (atualizarr
	 * 
	 * @author Tiago Moreno  
	 * @date 08/08/2006
	 * @param id
	 *            Description of the Parameter
	 * @param pacoteNomeObjeto
	 *            Descrição do parâmetro
	 * @throws ControladorException
	 */

	
	public void verificaObjetoRemocao(int id, String pacoteNomeObjeto,
			OperacaoEfetuada operacaoEfetuada, Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper)
			throws ControladorException;
	
	
	/**
	 * Método que consulta uma coleção por filtro e valida 
	 * se encontrou registros.
	 * 
	 * [FS0001] - Verificar existência de dados
	 * 
	 * @param filtro 			Filtro
	 * @param pacoteNomeObjeto 	pacoteNomeObjeto
	 * @param nomeTabela 		nomeTabela
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisar(Filtro filtro, String pacoteNomeObjeto, String nomeTabela) throws ControladorException;

	/**
	 * Retorna todos os feriados nacionais do sistema
	 *
	 * @author Pedro Alexandre
	 * @date 13/09/2006
	 *
	 * @return
	 * @throws ControladorException
	 */
	public Collection<NacionalFeriado> pesquisarFeriadosNacionais() throws ControladorException ;
	
	
	/**
	 * Atualiza a numeração do último RA cadastrado manualmente 
	 * 
	 * [UC0494] Gerar Numeração de RA Manual
	 * 
	 * @author Raphael Rossiter
	 * @date 06/11/2006
	 * 
	 * @param idSistemaParametro, ultimoRAManual
	 * @return void
	 */
	public void atualizarSistemaParametro(SistemaParametro sistemaParametro) throws ControladorException ;
	
	/**
	 * [UC???] - ????????
	 * 
	 * @author Rômulo Aurélio Filho
	 * @date 25/01/2007
	 * @descricao O método retorna um objeto com a maior data de Implementacao
	 *            do Banco e sua ultima alteracao
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	
	public DbVersaoBase pesquisarDbVersaoBase()
			throws ControladorException;
	
	
	/**
	 * 
	 * Divide a coleção em duas partes e cria um map onde vai ter as 2 partes.É
	 * criado outro map que guarda a ordem de como será chamada a o map das 2
	 * partes. Ex.:Map<1,Map<objeto1,objeto2>>, onde 1 é a ordem que será
	 * chamado o segundo map<objeto1,objeto2> e o objeto1 é primeiro objeto da
	 * coleção da primeira parte e o objeto2 é o primeiro objeto da segunda
	 * parte da coleção
	 * 
	 * @author Sávio Luiz
	 * @date 22/01/2007
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Map<Integer, Map<Object, Object>> dividirColecao(Collection colecao);
	
	/**
	 * 
	 *Metodo de criação, e envio de email
	 *
	 * @author Savio passado pra o Util por Flávio
	 * @date 12/03/2007
	 *
	 * @param arquivo
	 * @param emailReceptor
	 * @param emailRemetente
	 * @param nomeRemetente
	 * @param tituloMensagem
	 * @param corpoMensagem
	 * @throws ControladorException
	 */
	public void mandaArquivoLeituraEmail(String nomeArquivo,StringBuilder arquivo,
			String emailReceptor, String emailRemetente, 
			String tituloMensagem, String corpoMensagem)
			throws ControladorException ;
	
	
	/**
	 * [UC0747] - Calcular Diferença de dias úteis entre duas datas
	 *
	 * @author Raphael Rossiter
	 * @date 12/02/2008
	 *
	 * @param dataInicio
	 * @param dataFim
	 * @param municipio
	 * @throws ControladorException
	 */
	public Integer calcularDiferencaDiasUteisEntreDuasDatas(Date dataInicio, Date dataFim, Municipio municipio)
			throws ControladorException ;
	/**
	 * < <Descrição do método>>
	 * 
	 * @param filtro
	 *            Descrição do parâmetro
	 * @param pacoteNomeObjeto
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @throws ControladorException
	 */
	public Collection pesquisarGerencial(Filtro filtro, String pacoteNomeObjeto)
			throws ControladorException;
}
