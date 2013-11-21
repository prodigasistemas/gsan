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
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.geografico.FiltroMunicipioFeriado;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.MunicipioFeriado;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.email.ServicosEmail;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ControladorUtilSEJB implements SessionBean {
	private static final long serialVersionUID = 1L;
	private IRepositorioUtil repositorioUtil;

	SessionContext sessionContext;

	/**
	 * < <Descrição do método>>
	 * 
	 * @exception CreateException
	 *                Descrição da exceção
	 */
	public void ejbCreate() throws CreateException {
		repositorioUtil = RepositorioUtilHBM.getInstancia();
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbRemove() {
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbActivate() {
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbPassivate() {
	}

	/**
	 * Seta o valor de sessionContext
	 * 
	 * @param sessionContext
	 *            O novo valor de sessionContext
	 */
	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param classe
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @throws ControladorException
	 */
	public int registroMaximo(Class classe) throws ControladorException {
		try {
			return repositorioUtil.registroMaximo(classe);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param classe
	 *            Descrição do parâmetro
	 * @param atributo
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @throws ControladorException
	 */
	public int valorMaximo(Class classe, String atributo)
			throws ControladorException {
		try {
			return repositorioUtil.valorMaximo(classe, atributo);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

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
	 * @throws ControladorException
	 */
	public int valorMaximo(Class classe, String atributo, String parametro1,
			String parametro2) throws ControladorException {
		try {
			return repositorioUtil.valorMaximo(classe, atributo, parametro1,
					parametro2);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Retorna o único registro do SistemaParametros.
	 * 
	 * @return Descrição do retorno
	 * @throws ControladorException
	 */
	public SistemaParametro pesquisarParametrosDoSistema()
			throws ControladorException {
		try {
						
			return repositorioUtil.pesquisarParametrosDoSistema();
			
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

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
	 * @throws ControladorException
	 */
	public Collection limiteMaximoFiltroPesquisa(Filtro filtro,
			String pacoteNomeObjeto, int limite) throws ControladorException {
		try {
			return repositorioUtil.limiteMaximoFiltroPesquisa(filtro,
					pacoteNomeObjeto, limite);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param objeto
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @throws ControladorException
	 */
	public Object inserir(Object objeto) throws ControladorException {
		try {

			Object sequence = null;
			Integer id = null;

			// verifica se o objeto é do tipo Imovel
			if (objeto instanceof Imovel) {
				// type new_name = (type) name;
				sequence = repositorioUtil.obterNextVal(objeto);

				id = Util.obterDigitoVerificadorModulo11(((Integer) sequence)
						.toString());

				((Imovel) objeto).setId(new Integer((((Integer) sequence)
						.toString() + id.toString())));
				
				((Imovel) objeto).setCodigoDebitoAutomatico(new Integer((((Integer) sequence)
						.toString() + id.toString())));

				// verifica se o objeto é do tipo Cliente
			} else if (objeto instanceof Cliente) {
				sequence = repositorioUtil.obterNextVal(objeto);

				id = Util.obterDigitoVerificadorModulo11(((Integer) sequence)
						.toString());

				((Cliente) objeto).setId(new Integer((((Integer) sequence)
						.toString() + id.toString())));

			}

			return repositorioUtil.inserir(objeto);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

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
	public Collection pesquisar(Filtro filtro, String pacoteNomeObjeto)
			throws ControladorException {
		try {
			return repositorioUtil.pesquisar(filtro, pacoteNomeObjeto);
		} catch (ErroRepositorioException ex) {
		//	sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	public Collection pesquisar(Collection ids, Filtro filtro,
			String pacoteNomeObjeto) throws ControladorException {
		try {
			return repositorioUtil.pesquisar(ids, filtro, pacoteNomeObjeto);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param ids
	 *            Descrição do parâmetro
	 * @param pacoteNomeObjeto
	 *            Descrição do parâmetro
	 * @throws ControladorException
	 */
	public void remover(String[] ids, String pacoteNomeObjeto,
			OperacaoEfetuada operacaoEfetuada,
			Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper)
			throws ControladorException {

		// se for selecionado alguma unidade executora pelo usuário
		if (ids != null && ids.length != 0) {
			// remove todas as unidade executoras informadas
			for (int i = 0; i < ids.length; i++) {
				// atribui a variável "id" o código da unidade executora para
				// remoção
				int id = Integer.parseInt(ids[i]);

				try {
					// chama o metódo de remover unidade executora do
					// repositório
					repositorioUtil.remover(id, pacoteNomeObjeto,
							operacaoEfetuada, acaoUsuarioHelper);

					// objeto a ser removido não existe na base
				} catch (RemocaoRegistroNaoExistenteException ex) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.registro_remocao_nao_existente", ex);
					// erro de restrição na base
				} catch (RemocaoInvalidaException ex) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.dependencias.existentes", ex);
				} catch (ErroRepositorioException ex) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", ex);
				}
			}
		}
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param id
	 *            Description of the Parameter
	 * @param pacoteNomeObjeto
	 *            Descrição do parâmetro
	 * @throws ControladorException
	 */
	public void removerUm(int id, String pacoteNomeObjeto,
			OperacaoEfetuada operacaoEfetuada,
			Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper)
			throws ControladorException {
		try {
			// chama o metódo de remover unidade executora do repositório
			repositorioUtil.remover(id, pacoteNomeObjeto, operacaoEfetuada,
					acaoUsuarioHelper);

			// objeto a ser removido não existe na base
		} catch (RemocaoRegistroNaoExistenteException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.registro_remocao_nao_existente", ex);
			// erro de restrição na base
		} catch (RemocaoInvalidaException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.dependencias.existentes",
					ex);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * FAVOR NÃO USAR!!! Método para ser utilizada apenas em logradouro
	 * (atualizar)
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
			OperacaoEfetuada operacaoEfetuada,
			Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper)
			throws ControladorException {
		try {
			// chama o metódo de remover unidade executora do repositório
			repositorioUtil.remover(id, pacoteNomeObjeto, operacaoEfetuada,
					acaoUsuarioHelper);

			// objeto a ser removido não existe na base
		} catch (RemocaoRegistroNaoExistenteException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.registro_remocao_nao_existente", ex);
			// erro de restrição na base
		} catch (RemocaoInvalidaException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.dependencias.existentes",
					ex);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		// Usa o rollback para não remover nada, somente obter a crítica
		sessionContext.setRollbackOnly();
	}

	public void remover(Object object) throws ControladorException {
		try {
			repositorioUtil.remover(object);
			// objeto a ser removido não existe na base
		} catch (RemocaoRegistroNaoExistenteException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.registro_remocao_nao_existente", ex);
		} catch (RemocaoInvalidaException ex1) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.dependencias.existentes",
					ex1);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * Description of the Method
	 * 
	 * @param objeto
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 * @throws ControladorException
	 */
	public Object inserirOuAtualizar(Object objeto) throws ControladorException {
		try {
			return repositorioUtil.inserirOuAtualizar(objeto);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param objeto
	 *            Descrição do parâmetro
	 * @throws ControladorException
	 */
	public void atualizar(Object objeto) throws ControladorException {
		try {
			repositorioUtil.atualizar(objeto);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

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
			String msgErro) throws ControladorException {

		if (inicio != null && fim != null) {
			if (!inicio.equals("") && !fim.equals("")) {

				if (inicio.after(fim)) {
					throw new ControladorException(msgErro);
				}

			}
		}

	}

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
			Integer fim, String msgErro) throws ControladorException {

		if (inicio != null && fim != null) {
			if (!inicio.equals("") && !fim.equals("")) {

				if (inicio.compareTo(fim) == 1) {
					throw new ControladorException(msgErro);
				}

			}
		}
	}

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
			BigDecimal fim, String msgErro) throws ControladorException {

		if (inicio != null && fim != null) {
			if (!inicio.equals("") && !fim.equals("")) {

				if (inicio.compareTo(fim) == 1) {
					throw new ControladorException(msgErro);
				}
			}
		}
	}

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
			throws ControladorException {

		Date dataAtual = new Date();

		if (data != null) {
			if (!data.equals("")) {

				if (data.compareTo(dataAtual) == 1) {
					throw new ControladorException(msgErro, null, Util
							.formatarData(dataAtual));
				}
			}
		}
	}

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
			throws ControladorException {

		Date dataAtual = new Date();
		Integer anoMesAtual = Util.recuperaAnoMesDaData(dataAtual);

		if (anoMes != null) {
			if (!anoMes.equals("")) {

				if (anoMes.compareTo(anoMesAtual) == 1) {
					throw new ControladorException(msgErro);
				}

			}
		}
	}

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
			String pacoteNomeObjeto) throws ControladorException {
		try {
			return repositorioUtil.pesquisar(filtro, pageOffset,
					pacoteNomeObjeto);
		} catch (ErroRepositorioException ex) {
			//sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Informa o número total de registros de uma pesquisa, auxiliando o esquema
	 * de paginação
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
			throws ControladorException {
		try {
			return repositorioUtil.totalRegistrosPesquisa(filtro,
					pacoteNomeObjeto);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

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
			throws ControladorException {

		if ((anoMesReferenciaInicial != null && !anoMesReferenciaInicial
				.equals(""))
				&& (anoMesReferenciaFinal != null && !anoMesReferenciaFinal
						.equals(""))) {
			if (anoMesReferenciaInicial.length() == 7
					& anoMesReferenciaFinal.length() == 7) {

				String anoInicial = anoMesReferenciaInicial.substring(3, 7);
				String mesInicial = anoMesReferenciaInicial.substring(0, 2);

				String anoFinal = anoMesReferenciaFinal.substring(3, 7);
				String mesFinal = anoMesReferenciaFinal.substring(0, 2);

				boolean valida = Util.validarAnoMes(anoMesReferenciaInicial);
				if (valida) {
					throw new ControladorException("errors.invalid", null,
							descricaoCampoAnoMesReferenciaInicial);
				}

				valida = Util.validarAnoMes(anoMesReferenciaFinal);
				if (valida) {
					throw new ControladorException("errors.invalid", null,
							descricaoAnoMesReferenciaFinal);
				}

				Calendar periodoInicial = new GregorianCalendar();
				periodoInicial.set(Calendar.DATE, 1);
				periodoInicial.set(Calendar.MONTH, (new Integer(mesInicial)
						.intValue() - 1));
				periodoInicial.set(Calendar.YEAR, new Integer(anoInicial)
						.intValue());

				Calendar periodoFinal = new GregorianCalendar();
				periodoFinal.set(Calendar.DATE, 1);
				periodoFinal.set(Calendar.MONTH, (new Integer(mesFinal)
						.intValue() - 1));
				periodoFinal.set(Calendar.YEAR, new Integer(anoFinal)
						.intValue());

				if (periodoInicial.compareTo(periodoFinal) > 0) {
					throw new ControladorException(
							mensagemErroDoApplicationProperties);
				}
			} else {
				if (anoMesReferenciaInicial.length() < 7) {
					throw new ControladorException("errors.invalid", null,
							descricaoCampoAnoMesReferenciaInicial);
				}

				if (anoMesReferenciaFinal.length() < 7) {
					throw new ControladorException("errors.invalid", null,
							descricaoAnoMesReferenciaFinal);
				}

			}
		}

	}

	/**
	 * Verificar data final menos que data inicial
	 * 
	 * @author Rafael Santos
	 * @throws ControladorException
	 * @date 10/05/2006
	 */
	public void verificarDataInicialFinalPeriodo(String dataPeriodoInicial,
			String dataPeriodoFinal,
			String descricaoCampoDataReferenciaInicial,
			String descricaoDataReferenciaFinal,
			String mensagemErroDoApplicationProperties)
			throws ControladorException {

		if ((dataPeriodoInicial != null && !dataPeriodoInicial.equals(""))
				&& (dataPeriodoFinal != null && !dataPeriodoFinal.equals(""))) {

			if (dataPeriodoInicial.length() == 10
					& dataPeriodoFinal.length() == 10) {
				String anoInicial = dataPeriodoInicial.substring(6, 10);
				String mesInicial = dataPeriodoInicial.substring(3, 5);
				String diaInicial = dataPeriodoInicial.substring(0, 2);

				String anoFinal = dataPeriodoFinal.substring(6, 10);
				String mesFinal = dataPeriodoFinal.substring(3, 5);
				String diaFinal = dataPeriodoFinal.substring(0, 2);

				boolean valida = Util.validarDiaMesAno(dataPeriodoInicial);
				if (valida) {
					throw new ControladorException("errors.invalid", null,
							descricaoCampoDataReferenciaInicial);
				}
				valida = Util.validarDiaMesAno(dataPeriodoFinal);
				if (valida) {
					throw new ControladorException("errors.invalid", null,
							descricaoDataReferenciaFinal);
				}

				Calendar periodoInicial = new GregorianCalendar();
				periodoInicial.set(Calendar.DATE, new Integer(diaInicial)
						.intValue());
				periodoInicial.set(Calendar.MONTH, (new Integer(mesInicial)
						.intValue() - 1));
				periodoInicial.set(Calendar.YEAR, new Integer(anoInicial)
						.intValue());

				Calendar periodoFinal = new GregorianCalendar();
				periodoFinal.set(Calendar.DATE, new Integer(diaFinal)
						.intValue());
				periodoFinal.set(Calendar.MONTH, (new Integer(mesFinal)
						.intValue() - 1));
				periodoFinal.set(Calendar.YEAR, new Integer(anoFinal)
						.intValue());

				if (periodoInicial.compareTo(periodoFinal) > 0) {
					throw new ControladorException(
							mensagemErroDoApplicationProperties);
				}
			} else {
				if (dataPeriodoInicial.length() < 10) {
					throw new ControladorException("errors.invalid", null,
							descricaoCampoDataReferenciaInicial);
				}

				if (dataPeriodoFinal.length() < 10) {
					throw new ControladorException("errors.invalid", null,
							descricaoDataReferenciaFinal);
				}

			}
		}

	}

	/**
	 * Método que insere uma Lista em Batch
	 * 
	 * inserirBatch
	 * 
	 * @author Roberta Costa
	 * @date 17/05/2006
	 * 
	 * @param list
	 * @throws ErroRepositorioException
	 */
	public void inserirBatch(List list) throws ControladorException {
		try {
			repositorioUtil.inserirBatch(list);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Método que consulta uma coleção por filtro e valida se encontrou
	 * registros.
	 * 
	 * [FS0001] - Verificar existência de dados
	 * 
	 * @param filtro
	 *            Filtro
	 * @param pacoteNomeObjeto
	 *            pacoteNomeObjeto
	 * @param nomeTabela
	 *            nomeTabela
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisar(Filtro filtro, String pacoteNomeObjeto,
			String nomeTabela) throws ControladorException {
		Collection colecao = null;
		try {
			colecao = repositorioUtil.pesquisar(filtro, pacoteNomeObjeto);
			/*
			 * Caso a tabela esteja sem dados, exibir a mensagem: "Tabela <<nome_tabela>>
			 * sem dados para seleção."
			 */
			if (colecao == null || colecao.isEmpty()) {
				throw new ControladorException(
						"atencao.entidade_sem_dados_para_selecao", null,
						nomeTabela);
			}
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		return colecao;
	}

	/**
	 * Retorna todos os feriados nacionais do sistema
	 * 
	 * @author Pedro Alexandre
	 * @date 13/09/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection<NacionalFeriado> pesquisarFeriadosNacionais()
			throws ControladorException {
		try {
			return repositorioUtil.pesquisarFeriadosNacionais();
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Atualiza a numeração do último RA cadastrado manualmente
	 * 
	 * [UC0494] Gerar Numeração de RA Manual
	 * 
	 * @author Raphael Rossiter
	 * @date 06/11/2006
	 * 
	 * @param idSistemaParametro,
	 *            ultimoRAManual
	 * @return void
	 */
	public void atualizarSistemaParametro(SistemaParametro sistemaParametro)
			throws ControladorException {

		// -----VALIDAÇÃO DOS TIMESTAMP PARA ATUALIZAÇÃO DE CADASTRO

		// Validação para Setor Comercial
		if (sistemaParametro != null) {

			SistemaParametro sistemaParametroNaBase = this
					.pesquisarParametrosDoSistema();

			// Verifica se a data de alteração do objeto gravado na base é
			// maior que a na instancia
			if ((sistemaParametroNaBase.getUltimaAlteracao()
					.after(sistemaParametro.getUltimaAlteracao()))) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}

			// Seta a data/hora
			sistemaParametro.setUltimaAlteracao(new Date());

		}

		// Atualiza objeto
		this.atualizar(sistemaParametro);

	}
	
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
			throws ControladorException {
		try {
			return repositorioUtil.pesquisarDbVersaoBase();
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}
	
	
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
	public Map<Integer, Map<Object, Object>> dividirColecao(Collection colecao) {

		Map<Integer, Map<Object, Object>> mapOrdenada = new HashMap();
		List listColecao = new ArrayList();
		listColecao.addAll(colecao);
		int quantidadeContas = 0;
		int quantidadeContasColecao = listColecao.size();
		int metadeColecao = 0;
		// caso a coleção tenha um numero impar a metade será ela + 1
		// Ex.:coleção tamanho = 21; metade = 10+1 = 11.
		if (quantidadeContasColecao % 2 == 0) {
			metadeColecao = quantidadeContasColecao / 2;
		} else {
			metadeColecao = (quantidadeContasColecao / 2) + 1;
		}
		while (quantidadeContas < metadeColecao) {
			Map<Object, Object> map = new HashMap();
			Object object1 = (Object) listColecao.get(quantidadeContas);
			Object object2 = null;
			if (metadeColecao + quantidadeContas < quantidadeContasColecao) {
				object2 = (Object) listColecao.get(metadeColecao
						+ quantidadeContas);
			}
			map.put(object1, object2);
			mapOrdenada.put(quantidadeContas, map);
			map = null;

			quantidadeContas++;
		}
		quantidadeContasColecao = 0;

		return mapOrdenada;
	}
	
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
			throws ControladorException {
		try {
			File leitura = File.createTempFile(nomeArquivo, ".txt");
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(leitura.getAbsolutePath())));
			out.write(arquivo.toString());
			out.close();

			// criar o arquivo zip
			File compactado = new File(nomeArquivo + ".zip"); // nomeZip
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
					compactado));
			ZipUtil.adicionarArquivo(zos, leitura);
			// close the stream
			zos.close();
			

			ServicosEmail.enviarMensagemArquivoAnexado(emailReceptor,
					emailRemetente, tituloMensagem,
					corpoMensagem, compactado);
			
			leitura.delete();
			
		} catch (IOException e) {
			throw new ControladorException("erro.sistema", e);
		} catch (Exception e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	
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
			throws ControladorException {
		
		Integer qtdDiasUteis = 0;
		
		//FERIADO NACIONAL
		Collection<NacionalFeriado> colecaoFeriadosNacionais = null;
		
		try {
			
			colecaoFeriadosNacionais = repositorioUtil.pesquisarFeriadosNacionais();
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		//FERIADO MUNICIPAL
		FiltroMunicipioFeriado filtroMunicipioFeriado = new FiltroMunicipioFeriado();

		filtroMunicipioFeriado.adicionarParametro(new ParametroSimples(
		FiltroMunicipioFeriado.ID_MUNICIPIO, municipio.getId()));

		Collection<MunicipioFeriado> colecaoFeriadosMunicipais = this.pesquisar(filtroMunicipioFeriado,
		MunicipioFeriado.class.getName());
		
		
		Calendar dataInicioCalendar = new GregorianCalendar();
		dataInicioCalendar.setTime(dataInicio);
		
		while (dataInicioCalendar.getTime().compareTo(Util.formatarDataSemHora(dataFim)) < 0){
			
			if (Util.ehDiaUtil(Util.formatarDataSemHora(dataInicioCalendar.getTime()), 
				colecaoFeriadosNacionais, colecaoFeriadosMunicipais)){
				
				qtdDiasUteis++;
			}
			
			dataInicioCalendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		return qtdDiasUteis;
	}
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
			throws ControladorException {
		try {
			return repositorioUtil.pesquisarGerencial(filtro, pacoteNomeObjeto);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
}