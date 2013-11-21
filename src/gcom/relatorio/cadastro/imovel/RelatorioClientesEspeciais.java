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
package gcom.relatorio.cadastro.imovel;

import gcom.batch.Relatorio;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ImovelClientesEspeciaisRelatorioHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de bairro manter de água
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class RelatorioClientesEspeciais extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioClientesEspeciais(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CLIENTES_ESPECIAIS);
	}

	@Deprecated
	public RelatorioClientesEspeciais() {
		super(null, "");
	}

	public Object executar() throws TarefaException {
		
		List<RelatorioClientesEspeciaisBean> relatorioBeans = executarConsultaECriarRelatoriosBean();
		
		if(Util.isVazioOrNulo(relatorioBeans)){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		byte[] retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_CLIENTES_ESPECIAIS,
				criarParametros() , ds, tipoFormatoRelatorio);

		try {
			persistirRelatorioConcluido(retorno, Relatorio.CLIENTES_ESPECIAIS,this.getIdFuncionalidadeIniciada());
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}

		
		return retorno;
	}

	/**
	 * O método cria os parametros necessários a geração do relatorio.
	 *
	 *@since 06/10/2009
	 *@author Marlon Patrick
	 */
	private Map<String, Object> criarParametros() {
		Map<String,Object> parametros = new HashMap<String,Object>();

		parametros.put("imagem", Fachada.getInstancia().pesquisarParametrosDoSistema().getImagemRelatorio());
		parametros.put("tipoFormatoRelatorio", "R0591");
		
		return parametros;
	}

	/**
	 * Esse método tem a lógica para realizar a consulta referente ao relatorio
	 * e a partir do resultado obtido criar os beans.
	 *
	 *@since 06/10/2009
	 *@author Marlon Patrick
	 */
	private List<RelatorioClientesEspeciaisBean> executarConsultaECriarRelatoriosBean() {

		Collection<ImovelClientesEspeciaisRelatorioHelper> 
				colecaoImovelClientesEspeciaisRelatorioHelper = executarConsulta();

		List<RelatorioClientesEspeciaisBean> relatorioBeans = new ArrayList<RelatorioClientesEspeciaisBean>();

		if ( !Util.isVazioOrNulo(colecaoImovelClientesEspeciaisRelatorioHelper)) {

			 Collections.sort(
					(List<ImovelClientesEspeciaisRelatorioHelper>) colecaoImovelClientesEspeciaisRelatorioHelper,
						new ComparatorImovelClientesEspeciaisRelatorioHelper());

			Iterator<ImovelClientesEspeciaisRelatorioHelper> colecaoImovelClientesEspeciaisRelatorioHelperIterator = 
				colecaoImovelClientesEspeciaisRelatorioHelper.iterator();

			while (colecaoImovelClientesEspeciaisRelatorioHelperIterator.hasNext()) {

				ImovelClientesEspeciaisRelatorioHelper imovelClientesEspeciaisRelatorioHelper = colecaoImovelClientesEspeciaisRelatorioHelperIterator.next();
								
				RelatorioClientesEspeciaisBean relatorioBean = new RelatorioClientesEspeciaisBean();

				if (imovelClientesEspeciaisRelatorioHelper.getIdGerenciaRegional() != null) {

					relatorioBean.setGerenciaRegional(
							imovelClientesEspeciaisRelatorioHelper.getIdGerenciaRegional()
							+ " - "
							+ imovelClientesEspeciaisRelatorioHelper.getNomeGerenciaRegional());
				}

				if (imovelClientesEspeciaisRelatorioHelper.getIdCategoria() != null) {
					relatorioBean.setCategoria(
							imovelClientesEspeciaisRelatorioHelper.getIdCategoria()
							+ " - "
							+ imovelClientesEspeciaisRelatorioHelper.getDescricaoCategoria());
				}

				if (imovelClientesEspeciaisRelatorioHelper.getIdLocalidade() != null) {
					relatorioBean.setLocalidade(
							imovelClientesEspeciaisRelatorioHelper.getIdLocalidade()
							+ " - "
							+ imovelClientesEspeciaisRelatorioHelper.getNomeLocalidade());
				}

				if (imovelClientesEspeciaisRelatorioHelper.getIdSubcategoria() != null) {
					relatorioBean.setSubcategoria(
							imovelClientesEspeciaisRelatorioHelper.getIdSubcategoria().toString());
				}

				if (imovelClientesEspeciaisRelatorioHelper.getDataInstalacaoHidrometro() != null) {
					relatorioBean.setDataInstalacao(
							Util.formatarData(imovelClientesEspeciaisRelatorioHelper
									.getDataInstalacaoHidrometro()));
				}
				
				if (imovelClientesEspeciaisRelatorioHelper.getIdClienteUsuario() != null) {
					relatorioBean.setClienteUsuario(
							imovelClientesEspeciaisRelatorioHelper.getIdClienteUsuario()
							+ " - "
							+ imovelClientesEspeciaisRelatorioHelper.getNomeClienteUsuario());
				}
				
				if (imovelClientesEspeciaisRelatorioHelper.getIdClienteResponsavel() != null) {
					relatorioBean.setClienteResponsavel(
							imovelClientesEspeciaisRelatorioHelper.getIdClienteResponsavel()
							+ " - "
							+ imovelClientesEspeciaisRelatorioHelper.getNomeClienteResponsavel());
				}
				
				if (imovelClientesEspeciaisRelatorioHelper.getQtdeEconomias() != null) {
					relatorioBean.setQtdeEconomias(
							imovelClientesEspeciaisRelatorioHelper.getQtdeEconomias().toString());
				}
				
				if (imovelClientesEspeciaisRelatorioHelper.getConsumoAgua() != null) {
					relatorioBean.setConsumoAgua(
							imovelClientesEspeciaisRelatorioHelper.getConsumoAgua().toString());
				} 
				
				if (imovelClientesEspeciaisRelatorioHelper.getConsumoEsgoto() != null) {
					relatorioBean.setConsumoEsgoto(
							imovelClientesEspeciaisRelatorioHelper.getConsumoEsgoto().toString());
				}
				
				/**
				 * TODO : COSANPA Alterando o cálculo da média
				 */
				Fachada fachada = Fachada.getInstancia();
				Imovel imovel = new Imovel(imovelClientesEspeciaisRelatorioHelper.getIdImovel());
				MedicaoTipo medicao = new MedicaoTipo();
				medicao.setId(new Integer(MedicaoTipo.LIGACAO_AGUA));
					
				boolean houveIntslacaoHidrometro = false;
				try {
					houveIntslacaoHidrometro = fachada.verificarInstalacaoSubstituicaoHidrometro(imovel.getId(), medicao);
				} catch (ControladorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
				SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
				int[] consumoMedioImovel = Fachada.getInstancia().obterVolumeMedioAguaEsgoto(
							imovel.getId(),sistemaParametro.getAnoMesFaturamento(),1, houveIntslacaoHidrometro );

					relatorioBean.setMedia(String.valueOf(consumoMedioImovel[0]));

				
				if (imovelClientesEspeciaisRelatorioHelper.getConsumoMinimoEsgoto() != null) {
					relatorioBean.setEsgotoFixo(imovelClientesEspeciaisRelatorioHelper.getConsumoMinimoEsgoto().toString());
				}
				
				if (imovelClientesEspeciaisRelatorioHelper.getValorDebitosVencidos() != null) {
					relatorioBean.setDebitosVencidos(imovelClientesEspeciaisRelatorioHelper.getValorDebitosVencidos());
				}
				
				if (imovelClientesEspeciaisRelatorioHelper.getQtdeDebitosVencidos() != null) {
					relatorioBean.setFaturasEmAtraso(
							imovelClientesEspeciaisRelatorioHelper.getQtdeDebitosVencidos().toString());
				}
				
				if (imovelClientesEspeciaisRelatorioHelper.getValorAgua() != null) {
					relatorioBean.setValorAgua(imovelClientesEspeciaisRelatorioHelper.getValorAgua());
				}

				if (imovelClientesEspeciaisRelatorioHelper.getValorEsgoto() != null) {
					relatorioBean.setValorEsgoto(imovelClientesEspeciaisRelatorioHelper.getValorEsgoto());
				}

				if (imovelClientesEspeciaisRelatorioHelper.getValorConta() != null) {
					relatorioBean.setValorFatura(
							Util.formatarMoedaReal(imovelClientesEspeciaisRelatorioHelper.getValorConta()));
				}

				if(imovelClientesEspeciaisRelatorioHelper.getIdImovel()!=null){
					relatorioBean.setMatricula(
							imovelClientesEspeciaisRelatorioHelper.getIdImovel().toString());
				}
				
				if(imovelClientesEspeciaisRelatorioHelper.getCodigoSetor()!=null){
					relatorioBean.setSetor(
							imovelClientesEspeciaisRelatorioHelper.getCodigoSetor()
							+ " - " +
							imovelClientesEspeciaisRelatorioHelper.getDescricaoSetor());					
				}

				if(imovelClientesEspeciaisRelatorioHelper.getCodigoRota()!=null){
					relatorioBean.setCodigoRota(
							imovelClientesEspeciaisRelatorioHelper.getCodigoRota().toString());					
				}
				if(imovelClientesEspeciaisRelatorioHelper.getIndicadorCobrarMulta()!=null){
					relatorioBean.setIndicadorCobraMulta(
							imovelClientesEspeciaisRelatorioHelper.getIndicadorCobrarMulta());
				}

				relatorioBean.setInscricao(imovelClientesEspeciaisRelatorioHelper.getInscricaoImovel());
				
				relatorioBean.setCapacidadeHidrometro(
						imovelClientesEspeciaisRelatorioHelper.getDescricaoCapacidadeHidrometro());
				
				relatorioBean.setLigacaoAgua(
						imovelClientesEspeciaisRelatorioHelper.getDescricaoSituacaoLigacaoAgua());

				relatorioBean.setLigacaoEsgoto(
						imovelClientesEspeciaisRelatorioHelper.getDescricaoSituacaoLigacaoEsgoto());
				
				relatorioBean.setTarifaConsumo(
						imovelClientesEspeciaisRelatorioHelper.getDescricaoTarifaConsumo());

				relatorioBeans.add(relatorioBean);
			}
		}
		
		return relatorioBeans;
	}

	/**
	 * Esse método obtem os parametros e realiza a consulta referente ao relatorio.
	 *
	 *@since 06/10/2009
	 *@author Marlon Patrick
	 */
	private Collection<ImovelClientesEspeciaisRelatorioHelper> executarConsulta() {
		
		String idUnidadeNegocio = (String) getParametro("idUnidadeNegocio");
		String idGerenciaRegional = (String) getParametro("idGerenciaRegional");
		
		String idLocalidadeInicial = (String) getParametro("idLocalidadeInicial");
		String idLocalidadeFinal = (String) getParametro("idLocalidadeFinal");
		
		String codigoSetorInicial = (String) getParametro("codigoSetorInicial");
		String codigoSetorFinal = (String) getParametro("codigoSetorFinal");
		String codigoRotaInicial = (String) getParametro("codigoRotaInicial");
		String codigoRotaFinal = (String) getParametro("codigoRotaFinal");
		
		String[] idsPerfilImovel = (String[]) getParametro("idsPerfilImovel");
		String[] idsCategoria = (String[]) getParametro("idsCategoria");
		String[] idsSubcategoria = (String[]) getParametro("idsSubcategoria");
		String idSituacaoAgua = (String) getParametro("idSituacaoAgua");
		String idSituacaoEsgoto = (String) getParametro("idSituacaoEsgoto");
		String qtdeEconomiasInicial = (String) getParametro("qtdeEconomiasInicial");
		String qtdeEconomiasFinal = (String) getParametro("qtdeEconomiasFinal");
		String intervaloConsumoAguaInicial = (String) getParametro("intervaloConsumoAguaInicial");
		String intervaloConsumoAguaFinal = (String) getParametro("intervaloConsumoAguaFinal");
		String intervaloConsumoEsgotoInicial = (String) getParametro("intervaloConsumoEsgotoInicial");
		String intervaloConsumoEsgotoFinal = (String) getParametro("intervaloConsumoEsgotoFinal");
		String idClienteResponsavel = (String) getParametro("idClienteResponsavel");
		String intervaloConsumoResponsavelInicial = (String) getParametro("intervaloConsumoResponsavelInicial");
		String intervaloConsumoResponsavelFinal = (String) getParametro("intervaloConsumoResponsavelFinal");
		Date dataInstalacaoHidrometroInicial = (Date) getParametro("dataInstalacaoHidrometroInicial");
		Date dataInstalacaoHidrometroFinal = (Date) getParametro("dataInstalacaoHidrometroFinal");
		String[] idsCapacidadesHidrometro = (String[]) getParametro("idsCapacidadesHidrometro");
		String[] idsTarifasConsumo = (String[]) getParametro("idsTarifasConsumo");
		String idLeituraAnormalidade = (String) getParametro("idLeituraAnormalidade");
		String idConsumoAnormalidade = (String) getParametro("idConsumoAnormalidade");
		String leituraAnormalidade = (String) getParametro("leituraAnormalidade");
		String consumoAnormalidade = (String) getParametro("consumoAnormalidade");

		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		Collection<ImovelClientesEspeciaisRelatorioHelper> colecaoImovelClientesEspeciaisRelatorioHelper = 
			fachada.pesquisarImovelClientesEspeciaisRelatorio(idUnidadeNegocio,idGerenciaRegional, 
						idLocalidadeInicial,idLocalidadeFinal, 
						codigoSetorInicial, codigoSetorFinal,
						codigoRotaInicial, codigoRotaFinal,
						idsPerfilImovel, idsCategoria,
						idsSubcategoria, idSituacaoAgua, idSituacaoEsgoto,
						qtdeEconomiasInicial, qtdeEconomiasFinal,
						intervaloConsumoAguaInicial, intervaloConsumoAguaFinal,
						intervaloConsumoEsgotoInicial,
						intervaloConsumoEsgotoFinal, idClienteResponsavel,
						intervaloConsumoResponsavelInicial,
						intervaloConsumoResponsavelFinal,
						dataInstalacaoHidrometroInicial,
						dataInstalacaoHidrometroFinal,
						idsCapacidadesHidrometro, idsTarifasConsumo, 
						Util.subtrairMesDoAnoMes(sistemaParametro.getAnoMesFaturamento(), 1),
						idLeituraAnormalidade, leituraAnormalidade,
						idConsumoAnormalidade, consumoAnormalidade);
		
		return colecaoImovelClientesEspeciaisRelatorioHelper;
	}

	class ComparatorImovelClientesEspeciaisRelatorioHelper implements Comparator<ImovelClientesEspeciaisRelatorioHelper>{
		public int compare(ImovelClientesEspeciaisRelatorioHelper a, ImovelClientesEspeciaisRelatorioHelper b) {
			String chave1 = a.getIdGerenciaRegional().toString()
			+ a.getIdLocalidade().toString()
			+ a.getCodigoSetor().toString()
			+ a.getCodigoRota().toString()
			+ a.getIdCategoria().toString()
			+ a.getIdSubcategoria().toString();

			String chave2 = b.getIdGerenciaRegional().toString()
			+ b.getIdLocalidade().toString()
			+ b.getCodigoSetor().toString()
			+ b.getCodigoRota().toString()
			+ b.getIdCategoria().toString()
			+ b.getIdSubcategoria().toString();

			return chave1.compareTo(chave2);
		}
	}
	
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		int retorno = 0;
		
		String idUnidadeNegocio = (String) getParametro("idUnidadeNegocio");
		String idGerenciaRegional = (String) getParametro("idGerenciaRegional");
		
		String idLocalidadeInicial = (String) getParametro("idLocalidadeInicial");
		String idLocalidadeFinal = (String) getParametro("idLocalidadeFinal");
		
		String codigoSetorInicial = (String) getParametro("codigoSetorInicial");
		String codigoSetorFinal = (String) getParametro("codigoSetorFinal");
		String codigoRotaInicial = (String) getParametro("codigoRotaInicial");
		String codigoRotaFinal = (String) getParametro("codigoRotaFinal");
		
		String[] idsPerfilImovel = (String[]) getParametro("idsPerfilImovel");
		String[] idsCategoria = (String[]) getParametro("idsCategoria");
		String[] idsSubcategoria = (String[]) getParametro("idsSubcategoria");
		String idSituacaoAgua = (String) getParametro("idSituacaoAgua");
		String idSituacaoEsgoto = (String) getParametro("idSituacaoEsgoto");
		String qtdeEconomiasInicial = (String) getParametro("qtdeEconomiasInicial");
		String qtdeEconomiasFinal = (String) getParametro("qtdeEconomiasFinal");
		String intervaloConsumoAguaInicial = (String) getParametro("intervaloConsumoAguaInicial");
		String intervaloConsumoAguaFinal = (String) getParametro("intervaloConsumoAguaFinal");
		String intervaloConsumoEsgotoInicial = (String) getParametro("intervaloConsumoEsgotoInicial");
		String intervaloConsumoEsgotoFinal = (String) getParametro("intervaloConsumoEsgotoFinal");
		String idClienteResponsavel = (String) getParametro("idClienteResponsavel");
		String intervaloConsumoResponsavelInicial = (String) getParametro("intervaloConsumoResponsavelInicial");
		String intervaloConsumoResponsavelFinal = (String) getParametro("intervaloConsumoResponsavelFinal");
		Date dataInstalacaoHidrometroInicial = (Date) getParametro("dataInstalacaoHidrometroInicial");
		Date dataInstalacaoHidrometroFinal = (Date) getParametro("dataInstalacaoHidrometroFinal");
		String[] idsCapacidadesHidrometro = (String[]) getParametro("idsCapacidadesHidrometro");
		String[] idsTarifasConsumo = (String[]) getParametro("idsTarifasConsumo");
		String idLeituraAnormalidade = (String) getParametro("idLeituraAnormalidade");
		String idConsumoAnormalidade = (String) getParametro("idConsumoAnormalidade");
		String leituraAnormalidade = (String) getParametro("leituraAnormalidade");
		String consumoAnormalidade = (String) getParametro("consumoAnormalidade");

		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		Integer colecaoCount = 
			fachada.pesquisarImovelClientesEspeciaisRelatorioCount(idUnidadeNegocio,idGerenciaRegional, 
						idLocalidadeInicial,idLocalidadeFinal, 
						codigoSetorInicial, codigoSetorFinal,
						codigoRotaInicial, codigoRotaFinal,
						idsPerfilImovel, idsCategoria,
						idsSubcategoria, idSituacaoAgua, idSituacaoEsgoto,
						qtdeEconomiasInicial, qtdeEconomiasFinal,
						intervaloConsumoAguaInicial, intervaloConsumoAguaFinal,
						intervaloConsumoEsgotoInicial,
						intervaloConsumoEsgotoFinal, idClienteResponsavel,
						intervaloConsumoResponsavelInicial,
						intervaloConsumoResponsavelFinal,
						dataInstalacaoHidrometroInicial,
						dataInstalacaoHidrometroFinal,
						idsCapacidadesHidrometro, idsTarifasConsumo, 
						Util.subtrairMesDoAnoMes(sistemaParametro.getAnoMesFaturamento(), 1),
						idLeituraAnormalidade, leituraAnormalidade,
						idConsumoAnormalidade, consumoAnormalidade);
		
		if(colecaoCount!=null){
			retorno = colecaoCount;
		}
		
		
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioClientesEspeciais", this);

	}

}