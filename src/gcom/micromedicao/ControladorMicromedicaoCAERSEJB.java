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
package gcom.micromedicao;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.LogradouroTitulo;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.leitura.LeituraTipo;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.IoUtil;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;

/**
 * Controlador Faturamento CAER
 * 
 * @author Sávio Luiz
 * @date 25/07/2006
 */
public class ControladorMicromedicaoCAERSEJB extends ControladorMicromedicao
		implements SessionBean {

	private static final long serialVersionUID = 1L;

	// ==============================================================================================================
	// MÉTODOS EXCLUSIVOS DA CAER
	// ==============================================================================================================

	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0005] Gerar Relação(ROL) em TXT - CAER
	 * 
	 * @author Sávio Luiz
	 * @date 13/08/2007
	 * 
	 * @param idRota
	 * @param idLeituraTipo
	 * 
	 * @throws ErroRepositorioException
	 */

	protected void gerarArquivoConvencional(Integer anoMesCorrente,
			Integer idGrupoFaturamentoRota, StringBuilder arquivoTxtLinha,
			Integer idRota, boolean ehSistemaLegado)
			throws ControladorException {
		
		FiltroRota filtroRota = new FiltroRota();
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		
		filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, idRota));
		
		Collection colecaoRota = this.getControladorUtil().pesquisar(filtroRota, Rota.class.getName());
		
		Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRota);
		
		String codigoSetorComercial = Util.adicionarZerosEsquedaNumero(2, 
		String.valueOf(rota.getSetorComercial().getCodigo()));
		
		String codigoRota = Util.adicionarZerosEsquedaNumero(2, 
		String.valueOf(rota.getCodigo()));

		try {

			String nomeZipRol = null;

			if (ehSistemaLegado) {
				nomeZipRol = "Rt";
			}

			
			String idGrupoFaturamentoRotaFormatado;
			
			if(idGrupoFaturamentoRota.intValue() < 10){
				
				idGrupoFaturamentoRotaFormatado = "0"+idGrupoFaturamentoRota;
			}else{
				idGrupoFaturamentoRotaFormatado = idGrupoFaturamentoRota.toString();
			}
			
			
			/*
			 * Alterado por Raphael Rossiter em 30/03/2007 
			 * OBJ: Gerar apenas um arquivo para todas as rotas
			 */
			if (idRota != null) {
				nomeZipRol = nomeZipRol+"_"+"G"+idGrupoFaturamentoRotaFormatado+"_"+codigoSetorComercial+"_"+codigoRota;
			} else {
				nomeZipRol = nomeZipRol+"_"+"G"+idGrupoFaturamentoRotaFormatado+ "_"+anoMesCorrente+"-";
			}

			/*
			 * NÃO SERÁ GERADO ARQUIVO COM EXTENSÃO .ZIP
			 */
			//File compactadoTipoRol = new File(nomeZipRol + ".zip");
			//File leituraTipoRol = File.createTempFile(nomeZipRol, ".txt");
			
			File leituraTipoRol = new File(nomeZipRol);
			
			/*ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
			compactadoTipoRol));*/

			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
			new FileOutputStream(leituraTipoRol.getAbsolutePath())));

			out.write(arquivoTxtLinha.toString());
			out.flush();
			
			/*ZipUtil.adicionarArquivo(zos, leituraTipoRol);
			zos.close();*/

			//leituraTipoRol.delete();
			
			out.close();
		
		} catch (IOException e) {
			String mensagem = e.getMessage();
			String[] inicioMensagem = mensagem.split("\\.");

			if (inicioMensagem != null
					&& (inicioMensagem[0].equals("erro") || inicioMensagem[0]
							.equals("atencao"))) {
				throw new ControladorException(mensagem);
			} else {
				throw new ControladorException("erro.sistema", e);
			}
		} catch (Exception e) {
			String mensagem = e.getMessage();
			String[] inicioMensagem = mensagem.split("\\.");
			if (inicioMensagem != null
					&& (inicioMensagem[0].equals("erro") || inicioMensagem[0]
							.equals("atencao"))) {

				throw new ControladorException(mensagem);
			} else {
				throw new ControladorException("erro.sistema", e);
			}
		}
	}

	
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0002] Gerar Relação(ROL) em TXT
	 * 
	 * Cria o objeto Imovel apartir da pesquisa
	 * 
	 * @param Object[] arrayImoveis
	 * 
	 * @return Imovel
	 * 
	 * @author Rafael Francisco Pinto, Raphael Rossiter
	 * @date 19/01/2007, 27/09/2007
	 * 
	 * @throws ControladorException
	 */
	protected Imovel criarImovelApartirDadosPorLeituraConvencional(
			Object[] arrayImoveis) {

		// instancia um imóvel
		Imovel imovel = new Imovel();

		if (arrayImoveis[0] != null) {
			// seta o id no imovel
			imovel.setId((Integer) arrayImoveis[0]);
		}

		if (arrayImoveis[1] != null) {
			// instancia uma localidade para ser setado no imóvel
			Localidade localidade = new Localidade();
			localidade.setId((Integer) arrayImoveis[1]);
			localidade.setDescricao((String) arrayImoveis[30]);
			imovel.setLocalidade(localidade);
		}

		if (arrayImoveis[2] != null) {
			// instancia um setor comercial para ser setado no
			// imóvel
			SetorComercial setorComercial = new SetorComercial();
			setorComercial.setCodigo(Integer.parseInt(arrayImoveis[2]
					.toString()));
			imovel.setSetorComercial(setorComercial);
		}
		Quadra quadra = new Quadra();
		if (arrayImoveis[3] != null) {
			// instancia uma quadra para ser setado no imóvel

			Integer numeroQuadra = (Integer) arrayImoveis[3];
			quadra.setNumeroQuadra(numeroQuadra);
			imovel.setQuadra(quadra);
		}

		if (arrayImoveis[4] != null) {
			// seta o lote no imóvel
			imovel.setLote(Short.parseShort(arrayImoveis[4].toString()));
		}

		if (arrayImoveis[5] != null) {
			// seta o lote no imóvel
			imovel.setSubLote(Short.parseShort(arrayImoveis[5].toString()));
		}

		if (arrayImoveis[34] != null) {
			// seta numero sequencial rota
			imovel.setNumeroSequencialRota((Integer) arrayImoveis[34]);
		}

		if (arrayImoveis[6] != null) {
			// instancia uma imovel perfil para ser setado no
			// imóvel
			ImovelPerfil imovelPerfil = new ImovelPerfil();
			imovelPerfil.setId((Integer) arrayImoveis[6]);
			imovel.setImovelPerfil(imovelPerfil);
		}

		LigacaoAgua ligacaoAgua = new LigacaoAgua();
		if (arrayImoveis[7] != null) {
			// instancia uma ligação agua para ser setado no
			// imóvel

			ligacaoAgua.setId((Integer) arrayImoveis[7]);
			ligacaoAgua.setNumeroLacre((String) arrayImoveis[32]);
		}
		// instancia um hidrometro instalação historico para ser
		// colocado na ligacao agua
		HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoLigacaoAgua = new HidrometroInstalacaoHistorico();
		if (arrayImoveis[8] != null) {

			hidrometroInstalacaoHistoricoLigacaoAgua
					.setId((Integer) arrayImoveis[8]);
			MedicaoTipo medicaoTipo = new MedicaoTipo();
			medicaoTipo.setId((Integer) arrayImoveis[26]);
			hidrometroInstalacaoHistoricoLigacaoAgua
					.setMedicaoTipo(medicaoTipo);

			if (arrayImoveis[33] != null) {
				Hidrometro hidrometro = new Hidrometro();
				hidrometro.setNumero((String) arrayImoveis[33]);
				
				if (arrayImoveis[42] != null){
					hidrometro.setNumeroDigitosLeitura((Short) arrayImoveis[42]);
				}
				
				hidrometroInstalacaoHistoricoLigacaoAgua
						.setHidrometro(hidrometro);
			}

			ligacaoAgua
					.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistoricoLigacaoAgua);

		}
		imovel.setLigacaoAgua(ligacaoAgua);

		// //instancia um hidrometro instalação historico para
		// ser colocado no imovel
		HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoImovel = new HidrometroInstalacaoHistorico();
		if (arrayImoveis[9] != null) {

			hidrometroInstalacaoHistoricoImovel
					.setId((Integer) arrayImoveis[9]);

			MedicaoTipo medicaoTipo = new MedicaoTipo();
			medicaoTipo.setId((Integer) arrayImoveis[27]);

			hidrometroInstalacaoHistoricoImovel.setMedicaoTipo(medicaoTipo);

			if (arrayImoveis[36] != null) {
				Hidrometro hidrometro = new Hidrometro();
				hidrometro.setNumero((String) arrayImoveis[36]);
				
				if (arrayImoveis[43] != null){
					hidrometro.setNumeroDigitosLeitura((Short) arrayImoveis[43]);
				}
				
				hidrometroInstalacaoHistoricoImovel.setHidrometro(hidrometro);
			}

			imovel
					.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistoricoImovel);

		}
		// instancia a rota
		Rota rotaImovel = new Rota();

		if (arrayImoveis[10] != null) {
			// seta o id da rota
			rotaImovel.setId((Integer) arrayImoveis[10]);
		}

		if (arrayImoveis[11] != null) {
			// seta o indicador fiscalizador suprimido na rota
			rotaImovel.setIndicadorFiscalizarSuprimido(Short
					.parseShort(arrayImoveis[11].toString()));
		}

		if (arrayImoveis[12] != null) {
			// seta o indicador fiscalizador cortado na rota
			rotaImovel.setIndicadorFiscalizarCortado(Short
					.parseShort(arrayImoveis[12].toString()));
		}

		if (arrayImoveis[13] != null) {
			// seta o indicador gerar fiscalizacao na rota
			rotaImovel.setIndicadorGerarFiscalizacao(Short
					.parseShort(arrayImoveis[13].toString()));
		}

		if (arrayImoveis[14] != null) {
			// seta o indicador fgerar falsa faixa na rota
			rotaImovel.setIndicadorGerarFalsaFaixa(Short
					.parseShort(arrayImoveis[14].toString()));
		}

		if (arrayImoveis[15] != null) {
			// seta o percentual geracao fiscalizacao na rota
			rotaImovel
					.setPercentualGeracaoFiscalizacao((BigDecimal) (arrayImoveis[15]));
		}

		if (arrayImoveis[16] != null) {
			// seta o percentual geracao faixa falsa na rota
			rotaImovel
					.setPercentualGeracaoFaixaFalsa((BigDecimal) (arrayImoveis[16]));
		}

		if (arrayImoveis[35] != null) {
			// seta o codigo da rota
			rotaImovel.setCodigo((Short) (arrayImoveis[35]));
		}

		// instancia a empresa
		Empresa empresa = new Empresa();
		if (arrayImoveis[17] != null) {
			// seta o id na empresa
			empresa.setId((Integer) arrayImoveis[17]);
		}

		if (arrayImoveis[18] != null) {
			// seta a descrição abreviada na empresa
			empresa.setDescricaoAbreviada(arrayImoveis[18].toString());
		}

		if (arrayImoveis[19] != null) {
			// seta email da empresa
			empresa.setEmail(arrayImoveis[19].toString());
		}

		// seta a empresa na rota
		rotaImovel.setEmpresa(empresa);
		// instancia o faturamento
		FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();
		if (arrayImoveis[20] != null) {
			// seta o id no faturamentGrupo
			faturamentoGrupo.setId((Integer) arrayImoveis[20]);

		}
		if (arrayImoveis[21] != null) {
			// seta o descrição no faturamentGrupo
			faturamentoGrupo.setDescricao((String) arrayImoveis[21]);
		}
		// seta o faturamento na rota
		rotaImovel.setFaturamentoGrupo(faturamentoGrupo);
		if (arrayImoveis[22] != null) {
			// instancia a ligação esgoto situação
			LeituraTipo leituraTipo = new LeituraTipo();
			// seta o id na ligação esgoto situação
			leituraTipo.setId((Integer) arrayImoveis[22]);
			// seta a ligação esgoto situação no imovel
			rotaImovel.setLeituraTipo(leituraTipo);
		}

		// seta a rota na quadra
		quadra.setRota(rotaImovel);
		// seta a quadra no imovel
		imovel.setQuadra(quadra);
		if (arrayImoveis[23] != null) {
			// instancia a ligação agua situação
			LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
			// seta o id na ligação agua situação
			ligacaoAguaSituacao.setId((Integer) arrayImoveis[23]);
			ligacaoAguaSituacao.setDescricao((String) arrayImoveis[31]);
			ligacaoAguaSituacao.setDescricaoAbreviado((String) arrayImoveis[37]);
			ligacaoAguaSituacao.setIndicadorFaturamentoSituacao((Short) arrayImoveis[46]);

			// seta a ligação agua situação no imovel
			imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
		}
		if (arrayImoveis[24] != null) {
			// instancia a ligação esgoto situação
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
			// seta o id na ligação esgoto situação
			ligacaoEsgotoSituacao.setId((Integer) arrayImoveis[24]);
			ligacaoEsgotoSituacao.setIndicadorFaturamentoSituacao((Short) arrayImoveis[47]);
			// seta a ligação esgoto situação no imovel
			imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
		}

		if (arrayImoveis[25] != null) {
			// instancia o faturamento situacao tipo
			FaturamentoSituacaoTipo faturamentoSituacaoTipo = new FaturamentoSituacaoTipo();
			// seta o id no faturamento situacao tipo
			faturamentoSituacaoTipo
					.setIndicadorParalisacaoLeitura((Short) arrayImoveis[25]);
			// seta a ligação esgoto situação no imovel
			imovel.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);
		}

		if (arrayImoveis[28] != null) {
			// instancia o gerencia regional
			GerenciaRegional gerenciaRegional = new GerenciaRegional();

			// seta o id no faturamento situacao tipo
			gerenciaRegional.setId((Integer) arrayImoveis[28]);
			gerenciaRegional.setNome((String) arrayImoveis[29]);

			// seta a ligação esgoto situação no imovel
			imovel.getLocalidade().setGerenciaRegional(gerenciaRegional);
		}
		
		if (arrayImoveis[38] != null){
			//Logradouro Bairro
			LogradouroBairro logradouroBairro = new LogradouroBairro();
			
			//Logradouro
			Logradouro logradouro = new Logradouro();
			logradouro.setId((Integer) arrayImoveis[38]);
			logradouro.setNome((String) arrayImoveis[39]);
			
			
			if (arrayImoveis[45] != null){
				LogradouroTipo logradouroTipo = new LogradouroTipo();
				logradouroTipo.setDescricaoAbreviada((String) arrayImoveis[45]);
				logradouro.setLogradouroTipo(logradouroTipo);
			}
			
			
			if (arrayImoveis[44] != null){
				LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
				logradouroTitulo.setDescricaoAbreviada((String) arrayImoveis[44]);
				logradouro.setLogradouroTitulo(logradouroTitulo);
				
			}
			
			logradouroBairro.setLogradouro(logradouro);
			
			imovel.setLogradouroBairro(logradouroBairro);
		}
		
		if (arrayImoveis[40] != null){
			//Numero Imovel
			imovel.setNumeroImovel((String) arrayImoveis[40]);
		}
		
		if (arrayImoveis[41] != null){
			//Complemento Imovel
			imovel.setComplementoEndereco((String) arrayImoveis[41]);
		}

		if (arrayImoveis[48] != null){
			//Bairro
			Bairro bairro = new Bairro();
			bairro.setId((Integer)arrayImoveis[48]);
			imovel.getLogradouroBairro().setBairro(bairro);
		}
		
		if (arrayImoveis[49] != null){
			//Nome Bairro
			imovel.getLogradouroBairro().getBairro().setNome((String)arrayImoveis[49]);
		}
		
		return imovel;
	}
	
	
	/**
	 * [UC0083]- Gerar Dados para Leitura
	 * 
	 * 
	 * @date 10/07/2008
	 * @author Rômulo Aurélio
	 */

	public void gerarDadosPorLeituraConvencional(Collection<Rota> colecaoRota,
			Integer anoMesCorrente, Integer idGrupoFaturamentoRota,
			SistemaParametro sistemaParametro, int idFuncionalidadeIniciada)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		Integer qtdMovimentoRoteiroEmpresa = 0;

		try {

			Integer idLeituraTipo = LeituraTipo.CONVENCIONAL;

			
			// inicializa uma coleção de movimentoRoteiroEmpresa
			Collection<Object[]> objetosMovimentoRoteiroEmpresa = new ArrayList<Object[]>();

			Iterator colecaoRotaIterator = colecaoRota.iterator();

			while (colecaoRotaIterator.hasNext()) {

				StringBuilder arquivoTxtLinha = new StringBuilder();

				Rota rota = (Rota) colecaoRotaIterator.next();
				
				// Registrar o início do processamento da Unidade de Processamento
				// do Batch
				idUnidadeIniciada = getControladorBatch()
						.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
								UnidadeProcessamento.ROTA, rota.getId());


				// cria o HEADER
				// Ano mês referencia informado
				arquivoTxtLinha.append("" + anoMesCorrente);
				// Leiturista
				arquivoTxtLinha.append("0000");
				// Data
				arquivoTxtLinha.append(Util
						.formatarDataSemBarraDDMMAAAA(new Date()));
				// Brancos
				arquivoTxtLinha.append(Util.completaString("", 94));
				// fim da criação do HEADER

				// recupera todos os imóveis da coleção de rotas do tipo ROL
				objetosMovimentoRoteiroEmpresa = repositorioMicromedicao
						.pesquisarMovimentoRoteiroEmpresaParaLeituraPorColecaoRotaCAER(
								rota, idLeituraTipo, anoMesCorrente);

				if (objetosMovimentoRoteiroEmpresa != null
						&& !objetosMovimentoRoteiroEmpresa.isEmpty()) {

					qtdMovimentoRoteiroEmpresa = objetosMovimentoRoteiroEmpresa
							.size();

					boolean hidrometroSelecionado = false;

					MovimentoRoteiroEmpresa movimentoRoteiroEmpresa = null;

					Iterator objetosMovimentoRoteiroEmpresaIterator = objetosMovimentoRoteiroEmpresa
							.iterator();

					while (objetosMovimentoRoteiroEmpresaIterator.hasNext()) {

						Object[] dadosMovimentoRoteiroEmpresa = (Object[]) objetosMovimentoRoteiroEmpresaIterator
								.next();

						movimentoRoteiroEmpresa = (MovimentoRoteiroEmpresa) dadosMovimentoRoteiroEmpresa[0];

						Integer idQuadra = (Integer) dadosMovimentoRoteiroEmpresa[1];
						String descricaoFaturamentoGrupo = (String) dadosMovimentoRoteiroEmpresa[2];
						String descricaoAbreviadaEmpresa = (String) dadosMovimentoRoteiroEmpresa[3];
						String emailEmpresa = (String) dadosMovimentoRoteiroEmpresa[4];
						String descricaoLocalidade = (String) dadosMovimentoRoteiroEmpresa[5];
						String descricaoAbreviadoLigacaoAguaSitucao = (String) dadosMovimentoRoteiroEmpresa[6];
						String descricaoAbreviadoLigacaoEsgotoSitucao = (String) dadosMovimentoRoteiroEmpresa[7];

						LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
						ligacaoAguaSituacao.setId(movimentoRoteiroEmpresa
								.getLigacaoAguaSituacao().getId());
						ligacaoAguaSituacao
								.setDescricaoAbreviado(descricaoAbreviadoLigacaoAguaSitucao);

						LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
						ligacaoEsgotoSituacao.setId(movimentoRoteiroEmpresa
								.getLigacaoEsgotoSituacao().getId());
						ligacaoEsgotoSituacao
								.setDescricaoAbreviado(descricaoAbreviadoLigacaoEsgotoSitucao);

						movimentoRoteiroEmpresa
								.setLigacaoAguaSituacao(ligacaoAguaSituacao);

						movimentoRoteiroEmpresa
								.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);

						FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();
						faturamentoGrupo.setId(movimentoRoteiroEmpresa.getId());
						faturamentoGrupo
								.setDescricao(descricaoFaturamentoGrupo);

						Quadra quadra = new Quadra();
						quadra.setId(idQuadra);

						Imovel imovel = new Imovel();
						imovel.setId(movimentoRoteiroEmpresa.getImovel()
								.getId());

						imovel.setQuadra(quadra);
						movimentoRoteiroEmpresa.setImovel(imovel);

						Empresa empresa = new Empresa();
						empresa.setId(movimentoRoteiroEmpresa.getEmpresa()
								.getId());
						empresa.setEmail(emailEmpresa);
						empresa
								.setDescricaoAbreviada(descricaoAbreviadaEmpresa);

						movimentoRoteiroEmpresa.setEmpresa(empresa);

						Localidade localidade = new Localidade();
						localidade.setId(movimentoRoteiroEmpresa
								.getLocalidade().getId());
						localidade.setDescricao(descricaoLocalidade);

						movimentoRoteiroEmpresa.setLocalidade(localidade);

						gerarRelacaoTxt(arquivoTxtLinha,
								movimentoRoteiroEmpresa, anoMesCorrente,
								hidrometroSelecionado, sistemaParametro);

					}
				

				/*
				 * 
				 * Gerar apenas um arquivo para todas as rotas
				 */

				this.inserirArquivoTextoRoteiroEmpresa(anoMesCorrente, rota
						.getId(), qtdMovimentoRoteiroEmpresa,
						idGrupoFaturamentoRota, arquivoTxtLinha, true);

				}
				
				// Gera o arquivo para o sistema legado
				// this.gerarArquivoConvencional(anoMesCorrente,
				// idGrupoFaturamentoRota, arquivoTxtLinhaSistemaLegado,
				// null, true);

				// atualiza a data e a hora da realização da atividade com a
				// data e
				// a hora correntes
				repositorioMicromedicao
						.atualizarFaturamentoAtividadeCronograma(
								idGrupoFaturamentoRota, anoMesCorrente);
				//				 Encerra a unidade de Faturamento
				getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
						idUnidadeIniciada, false);


			}

			// Encerra a unidade de Faturamento
			/*getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);*/


		} catch (Exception e) { // Este catch serve para interceptar
			e.printStackTrace();
			// qualquer exceção que o processo batch
			// venha a lançar e garantir que a unidade
			// de processamento do batch será atualizada
			// com o erro ocorrido

			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);

			throw new EJBException(e);
		}

	}
	
	
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0005] Gerar Relação(ROL) em TXT - CAER
	 * 
	 * @author Sávio Luiz
	 * @date 13/08/2007
	 * 
	 * @param idRota
	 * @param idLeituraTipo
	 * 
	 * @throws ErroRepositorioException
	 */
	protected boolean gerarRelacaoTxt(StringBuilder arquivoTxtLinha,
			MovimentoRoteiroEmpresa movimentoRoteiroEmpresa, Integer anoMesCorrente,
			boolean hidrometroSelecionado, SistemaParametro sistemaParametro) {

		/*boolean ligacaoAgua = false;
		boolean ligacaoPoco = false;
		Imovel imovelParaSerGerado = new Imovel();
		Short numeroDigitosHidrometro = null;*/
		StringBuilder dadosHidrometro = new StringBuilder();
		StringBuilder arquivoTxtLinhaAux = new StringBuilder();
		// parte que coloca alguma dado para não entrar eu uma situação não
		// desejada do metodo pesquisarFaixaEsperadaOuFalsa
		dadosHidrometro.append("Qualquer dado");

		Object[] dadosLinhaTxtHidSelecionaldo = new Object[2];

		try {

			if (movimentoRoteiroEmpresa != null) {
				arquivoTxtLinhaAux.append(System.getProperty("line.separator"));
				// id do imovel
				if (movimentoRoteiroEmpresa.getImovel().getId() != null) {
					arquivoTxtLinhaAux.append(Util.adicionarZerosEsquedaNumeroTruncando(
							7, "" + movimentoRoteiroEmpresa.getImovel().getId()));
					//imovelParaSerGerado.setId((Integer) arrayImoveisPorRota[0]);

				} else {
					arquivoTxtLinhaAux.append(Util.adicionarZerosEsquedaNumero(
							7, ""));
				}

				// codigo da rota
				if (movimentoRoteiroEmpresa.getCodigoRota() != null) {
					arquivoTxtLinhaAux.append(Util.adicionarZerosEsquedaNumeroTruncando(
							4, "" + movimentoRoteiroEmpresa.getCodigoRota()));
					
				} else {
					arquivoTxtLinhaAux.append(Util.adicionarZerosEsquedaNumeroTruncando(
							4, ""));
				}

				// numero quadra
				if (movimentoRoteiroEmpresa.getNumeroQuadra() != null) {
					arquivoTxtLinhaAux.append(Util.adicionarZerosEsquedaNumeroTruncando(
							4, "" + movimentoRoteiroEmpresa.getNumeroQuadra()));
				} else {
					arquivoTxtLinhaAux.append(Util.adicionarZerosEsquedaNumeroTruncando(
							4, ""));
				}

				// lote do imovel
				if (movimentoRoteiroEmpresa.getNumeroLoteImovel() != null) {
					arquivoTxtLinhaAux.append(Util.adicionarZerosEsquedaNumeroTruncando(
							3, "" + movimentoRoteiroEmpresa.getNumeroLoteImovel()));
				} else {
					arquivoTxtLinhaAux.append(Util.adicionarZerosEsquedaNumeroTruncando(
							3, ""));
				}

				// situação de ligação de agua
				if (movimentoRoteiroEmpresa.getLigacaoAguaSituacao() != null) {
					arquivoTxtLinhaAux.append(Util.adicionarZerosEsquedaNumeroTruncando(
							3, "" + movimentoRoteiroEmpresa.getLigacaoAguaSituacao().getDescricaoAbreviado()));
				} else {
					arquivoTxtLinhaAux.append(Util.adicionarZerosEsquedaNumeroTruncando(
							3, ""));
				}

				// id logradouro
				if (movimentoRoteiroEmpresa.getLogradouro() != null) {
					arquivoTxtLinhaAux.append(Util.adicionarZerosEsquedaNumeroTruncando(
							4, "" + movimentoRoteiroEmpresa.getLogradouro().getId()));
				} else {
					arquivoTxtLinhaAux.append(Util.adicionarZerosEsquedaNumero(
							4, ""));
				}

				
//				Nome do Logradouro, com a descrição abreviada do titulo e tipo 
//				(MREM_DSABREVLOGRADOUROTITULO  + MREM_DSABREVLOGRADOUROTIPO  + 
//						MREM_NMLOGRADOURO da tabela MOVIMENTO_ROTEIRO_EMPRESA) Tam 25
				
				// nome logradouro
				if (movimentoRoteiroEmpresa.getNomeLogradouro() != null) {

					String titulo = "";
					if (movimentoRoteiroEmpresa
							.getDescricaoAbreviadaLogradouroTitulo() != null) {
						titulo =  Util.completaString(movimentoRoteiroEmpresa
								.getDescricaoAbreviadaLogradouroTitulo(),5);
					}
					
					String nomeLogradouroCompleto = titulo
							+ Util.completaString(movimentoRoteiroEmpresa
									.getDescricaoAbreviadaLogradouroTipo(),3)
							+ movimentoRoteiroEmpresa.getNomeLogradouro();

					arquivoTxtLinhaAux.append(Util.completaString(
							nomeLogradouroCompleto, 25));

				} else {
					arquivoTxtLinhaAux.append(Util.completaString("", 25));
				}

				// numero do imovel
				if (movimentoRoteiroEmpresa.getNumeroImovel() != null) {
					arquivoTxtLinhaAux.append(Util.completaString(
							 "" + movimentoRoteiroEmpresa.getNumeroImovel(),5));
				} else {
					arquivoTxtLinhaAux.append(Util.completaString(""
							, 5));
				}

				// complemento do imovel
				if (movimentoRoteiroEmpresa.getComplementoEndereco() != null) {
					arquivoTxtLinhaAux.append(Util.completaString(""
							+ movimentoRoteiroEmpresa.getComplementoEndereco(), 3));
				} else {
					arquivoTxtLinhaAux.append(Util.completaString("", 3));
				}
				
				
//				 numeroHidrometro
				if (movimentoRoteiroEmpresa.getNumeroHidrometro() != null) {
					arquivoTxtLinhaAux.append(Util.completaString(""
							+ movimentoRoteiroEmpresa.getNumeroHidrometro(), 10));
				} else {
					arquivoTxtLinhaAux.append(Util.completaString("", 10));
				}
				
				// Leitura Minima
				String faixaLeituraEsperadaInicial;
				
				String faixaLeituraEsperadaFinal;
				
				if (movimentoRoteiroEmpresa.getNumeroFaixaLeituraEsperadaInicial() != null) {
					/*arquivoTxtLinhaAux.append(Util.adicionarZerosEsquedaNumeroTruncando(7, ""
							+ movimentoRoteiroEmpresa.getNumeroFaixaLeituraEsperadaInicial()));*/
					
					 faixaLeituraEsperadaInicial = Util.adicionarZerosEsquedaNumeroTruncando(6, ""
							+ movimentoRoteiroEmpresa.getNumeroFaixaLeituraEsperadaInicial());
					
				} else {
					faixaLeituraEsperadaInicial = Util.adicionarZerosEsquedaNumeroTruncando(6, "");
					
					/*arquivoTxtLinhaAux.append(Util.adicionarZerosEsquedaNumeroTruncando(7, ""));*/
				}
				// Leitura Maxima
				
				if (movimentoRoteiroEmpresa.getNumeroFaixaLeituraEsperadaFinal() != null) {
					faixaLeituraEsperadaFinal = Util.adicionarZerosEsquedaNumeroTruncando(6, ""
							+ movimentoRoteiroEmpresa.getNumeroFaixaLeituraEsperadaFinal());
					
					/*arquivoTxtLinhaAux.append(Util.adicionarZerosEsquedaNumeroTruncando(7, ""
							+ movimentoRoteiroEmpresa.getNumeroFaixaLeituraEsperadaFinal()));*/
				} else {
					faixaLeituraEsperadaFinal = Util.adicionarZerosEsquedaNumeroTruncando(6, "");
					/*arquivoTxtLinhaAux.append(Util.adicionarZerosEsquedaNumeroTruncando(7, ""));*/
				}
				
				String faixaLeituraEsperada = Util.adicionarZerosEsquedaNumeroTruncando(14, ""
						+ faixaLeituraEsperadaInicial + faixaLeituraEsperadaFinal) ;
				
				arquivoTxtLinhaAux.append(faixaLeituraEsperada);
				
				arquivoTxtLinhaAux.append(Util.adicionarZerosEsquedaNumeroTruncando(30, ""));
				
				arquivoTxtLinha.append(arquivoTxtLinhaAux);
			}

		} catch (Exception e) { // Este catch serve para interceptar
			e.printStackTrace();

			throw new EJBException(e);
		}
		dadosLinhaTxtHidSelecionaldo[0] = arquivoTxtLinha;
		dadosLinhaTxtHidSelecionaldo[1] = hidrometroSelecionado;

		return hidrometroSelecionado;
	}
	

	
	
	
	/**
	 * @author Rômulo Aurélio
	 * @date 15/07/2008
	 * 
	 * @param imovelParaSerGerado
	 * @param anoMesCorrente
	 * @param sistemaParametro
	 * @throws ControladorException
	 */
	
	public void gerarDadosMovimentoRoteiroEmpresa(Imovel imovelParaSerGerado, 
			int anoMesCorrente, SistemaParametro sistemaParametro, Integer idLeituraTipo) throws ControladorException{
		
		boolean ligacaoAgua = false;
		boolean ligacaoPoco = false;
		
		boolean hidrometroSelecionado = false;
		
		if (imovelParaSerGerado.getLigacaoAgua() != null
				&& imovelParaSerGerado.getLigacaoAgua().getId() != null
				&& imovelParaSerGerado.getLigacaoAgua()
						.getHidrometroInstalacaoHistorico() != null
				&& imovelParaSerGerado.getLigacaoAgua()
						.getHidrometroInstalacaoHistorico()
						.getId() != null) {
			ligacaoAgua = true;
		}
		if (imovelParaSerGerado
				.getHidrometroInstalacaoHistorico() != null
				&& imovelParaSerGerado
						.getHidrometroInstalacaoHistorico()
						.getId() != null) {
			ligacaoPoco = true;
		}
		
		
		MovimentoRoteiroEmpresa movimentoRoteiroEmpresa = new MovimentoRoteiroEmpresa();

		movimentoRoteiroEmpresa
				.setAnoMesMovimento(anoMesCorrente);

		if (imovelParaSerGerado.getQuadra() != null) {

			// if (sistemaParametro.getIndicadorRoteiroEmpresa() == 1) {
			if (imovelParaSerGerado.getQuadra().getRoteiroEmpresa() == null) {

				// continue;

				// }

				if (imovelParaSerGerado.getQuadra() != null) {

					if (imovelParaSerGerado.getQuadra().getRoteiroEmpresa() == null)

						movimentoRoteiroEmpresa
								.setRoteiroEmpresa(imovelParaSerGerado
										.getQuadra().getRoteiroEmpresa());
					movimentoRoteiroEmpresa.setNumeroQuadra(imovelParaSerGerado
							.getQuadra().getNumeroQuadra());

					if (imovelParaSerGerado.getQuadra().getRota() != null) {
						// id do grupo de faturamento
						movimentoRoteiroEmpresa
								.setFaturamentoGrupo(imovelParaSerGerado
										.getQuadra().getRota()
										.getFaturamentoGrupo());
					}

					if (imovelParaSerGerado.getQuadra().getRoteiroEmpresa() != null) {
						movimentoRoteiroEmpresa.setEmpresa(imovelParaSerGerado
								.getQuadra().getRoteiroEmpresa().getEmpresa());
					} else {
						movimentoRoteiroEmpresa.setEmpresa(imovelParaSerGerado
								.getQuadra().getRota().getEmpresa());
					}
				}
			}
		}
		movimentoRoteiroEmpresa
				.setLocalidade(imovelParaSerGerado
						.getLocalidade());
		movimentoRoteiroEmpresa
				.setCodigoSetorComercial(imovelParaSerGerado
						.getSetorComercial().getCodigo());

		movimentoRoteiroEmpresa.setNumeroLoteImovel(""+imovelParaSerGerado.getLote());
		movimentoRoteiroEmpresa.setNumeroSubloteImovel(""	+ imovelParaSerGerado.getSubLote());

		movimentoRoteiroEmpresa
				.setImovelPerfil(imovelParaSerGerado
						.getImovelPerfil());

		// caso seja tipo ligação agua e poço cria a string
		// primeiro
		// com
		// tipo
		// ligação agua
		if (ligacaoAgua && ligacaoPoco) {

			if (imovelParaSerGerado.getLigacaoAgua() != null
					&& imovelParaSerGerado.getLigacaoAgua()
							.getHidrometroInstalacaoHistorico() != null
					&& imovelParaSerGerado.getLigacaoAgua()
							.getHidrometroInstalacaoHistorico()
							.getId() != null
					&& !imovelParaSerGerado.getLigacaoAgua()
							.getHidrometroInstalacaoHistorico()
							.getId().equals("")) {

				movimentoRoteiroEmpresa
						.setMedicaoTipo(imovelParaSerGerado
								.getLigacaoAgua()
								.getHidrometroInstalacaoHistorico()
								.getMedicaoTipo());
			}
			// caso não seja
		} else {
			// caso seja tipo ligação agua cria a string com
			// tipo
			// ligação agua
			if (ligacaoAgua) {
				if (imovelParaSerGerado.getLigacaoAgua() != null
						&& imovelParaSerGerado
								.getLigacaoAgua()
								.getHidrometroInstalacaoHistorico() != null
						&& imovelParaSerGerado
								.getLigacaoAgua()
								.getHidrometroInstalacaoHistorico()
								.getId() != null
						&& !imovelParaSerGerado
								.getLigacaoAgua()
								.getHidrometroInstalacaoHistorico()
								.getId().equals("")) {
					movimentoRoteiroEmpresa
							.setMedicaoTipo(imovelParaSerGerado
									.getLigacaoAgua()
									.getHidrometroInstalacaoHistorico()
									.getMedicaoTipo());
				}
			} else {
				// caso seja tipo ligação poço cria a string
				// com
				// tipo
				// ligação poço
				if (ligacaoPoco) {
					if (imovelParaSerGerado
							.getHidrometroInstalacaoHistorico() != null
							&& imovelParaSerGerado
									.getHidrometroInstalacaoHistorico()
									.getId() != null
							&& !imovelParaSerGerado
									.getHidrometroInstalacaoHistorico()
									.getId().equals("")) {
						movimentoRoteiroEmpresa
								.setMedicaoTipo(imovelParaSerGerado
										.getHidrometroInstalacaoHistorico()
										.getMedicaoTipo());
					}
				}
			}
		}

		// Matricula do imóvel
		movimentoRoteiroEmpresa.setImovel(imovelParaSerGerado);

		// Perfil do imovel
		movimentoRoteiroEmpresa
				.setImovelPerfil(imovelParaSerGerado
						.getImovelPerfil());

		String nomeClienteUsuario = null;
		try {
			// Pesquisa o nome do cliente que tem o tipo de
			// relação
			// usuário.
			nomeClienteUsuario = repositorioClienteImovel
					.pesquisarNomeClientePorImovel(imovelParaSerGerado
							.getId());
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		// nome do cliente usuário
		movimentoRoteiroEmpresa.setNomeCliente(completaString(
				nomeClienteUsuario, 50));

		// Pesquisa o endereço do imovel passando o id
		String enderecoImovel = getControladorEndereco()
				.pesquisarEnderecoFormatado(
						imovelParaSerGerado.getId());
		if (enderecoImovel != null
				&& !enderecoImovel.equals("")) {
			// endereço do imóvel
			movimentoRoteiroEmpresa
					.setEnderecoImovel(completaString(
							enderecoImovel, 100));
		} else {
			movimentoRoteiroEmpresa
					.setEnderecoImovel(completaString("", 100));
		}

		// Dados do Hidrometro

		// caso seja tipo ligação agua e poço cria a string
		// primeiro
		// com
		// tipo
		// ligação agua
		Short numeroDigitosHidrometro = null;
		StringBuilder dadosHidrometro = null;
		Integer capacidadeHidrometro = null;
		Integer marcaHidrometro = null;

		if (ligacaoAgua && ligacaoPoco) {

			Object[] dadosHidrometroNumeroLeitura = pesquisarDadosHidrometroTipoLigacaoAgua(imovelParaSerGerado);

			dadosHidrometro = (StringBuilder) dadosHidrometroNumeroLeitura[0];
			numeroDigitosHidrometro = (Short) dadosHidrometroNumeroLeitura[1];
			capacidadeHidrometro = (Integer) dadosHidrometroNumeroLeitura[2];
			marcaHidrometro = (Integer) dadosHidrometroNumeroLeitura[3];

			HidrometroCapacidade capacidade = new HidrometroCapacidade();
			capacidade.setId(capacidadeHidrometro);
			movimentoRoteiroEmpresa
					.setHidrometroCapacidade(capacidade);

			HidrometroMarca hidrometroMarca = new HidrometroMarca();
			hidrometroMarca.setId(marcaHidrometro);
			movimentoRoteiroEmpresa
					.setHidrometroMarca(hidrometroMarca);

			movimentoRoteiroEmpresa
					.setNumeroHidrometro(completaString(
							(String) dadosHidrometroNumeroLeitura[4],
							10));
			// caso não seja
		} else {
			// caso seja tipo ligação agua cria a string com
			// tipo
			// ligação agua
			if (ligacaoAgua) {

				Object[] dadosHidrometroNumeroLeitura = pesquisarDadosHidrometroTipoLigacaoAgua(imovelParaSerGerado);

				dadosHidrometro = (StringBuilder) dadosHidrometroNumeroLeitura[0];
				numeroDigitosHidrometro = (Short) dadosHidrometroNumeroLeitura[1];
				capacidadeHidrometro = (Integer) dadosHidrometroNumeroLeitura[2];
				marcaHidrometro = (Integer) dadosHidrometroNumeroLeitura[3];

				HidrometroCapacidade capacidade = new HidrometroCapacidade();
				capacidade.setId(capacidadeHidrometro);
				movimentoRoteiroEmpresa
						.setHidrometroCapacidade(capacidade);

				HidrometroMarca hidrometroMarca = new HidrometroMarca();
				hidrometroMarca.setId(marcaHidrometro);
				movimentoRoteiroEmpresa
						.setHidrometroMarca(hidrometroMarca);

				movimentoRoteiroEmpresa
						.setNumeroHidrometro(completaString(
								(String) dadosHidrometroNumeroLeitura[4],
								10));

				// caso não seja
			} else {
				// caso seja tipo ligação poço cria a string
				// com
				// tipo
				// ligação poço
				if (ligacaoPoco) {

					Object[] dadosHidrometroNumeroLeitura = pesquisarDadosHidrometroTipoPoco(imovelParaSerGerado);

					dadosHidrometro = (StringBuilder) dadosHidrometroNumeroLeitura[0];
					numeroDigitosHidrometro = (Short) dadosHidrometroNumeroLeitura[1];
					capacidadeHidrometro = (Integer) dadosHidrometroNumeroLeitura[2];
					marcaHidrometro = (Integer) dadosHidrometroNumeroLeitura[3];

					HidrometroCapacidade capacidade = new HidrometroCapacidade();
					capacidade.setId(capacidadeHidrometro);
					movimentoRoteiroEmpresa
							.setHidrometroCapacidade(capacidade);

					HidrometroMarca hidrometroMarca = new HidrometroMarca();
					hidrometroMarca.setId(marcaHidrometro);
					movimentoRoteiroEmpresa
							.setHidrometroMarca(hidrometroMarca);

					movimentoRoteiroEmpresa
							.setNumeroHidrometro(completaString(
									(String) dadosHidrometroNumeroLeitura[4],
									10));

					// caso não seja nem um nem outro então
					// pode
					// chamar
					// qualquer um dos métodos
					// pois os dois fazem a verificação e
					// retorna
					// strings
					// vazia e
					// a data cpm zeros
				} else {
					Object[] dadosHidrometroNumeroLeitura = pesquisarDadosHidrometroTipoPoco(imovelParaSerGerado);

					dadosHidrometro = (StringBuilder) dadosHidrometroNumeroLeitura[0];
					numeroDigitosHidrometro = (Short) dadosHidrometroNumeroLeitura[1];
					capacidadeHidrometro = (Integer) dadosHidrometroNumeroLeitura[2];
					marcaHidrometro = (Integer) dadosHidrometroNumeroLeitura[3];

					HidrometroCapacidade capacidade = new HidrometroCapacidade();
					capacidade.setId(capacidadeHidrometro);
					movimentoRoteiroEmpresa
							.setHidrometroCapacidade(capacidade);

					HidrometroMarca hidrometroMarca = new HidrometroMarca();
					hidrometroMarca.setId(marcaHidrometro);
					movimentoRoteiroEmpresa
							.setHidrometroMarca(hidrometroMarca);

					movimentoRoteiroEmpresa
							.setNumeroHidrometro(completaString(
									(String) dadosHidrometroNumeroLeitura[4],
									10));
				}
			}
		}

		if (imovelParaSerGerado
				.getHidrometroInstalacaoHistorico() != null) {

			movimentoRoteiroEmpresa
					.setHidrometroLocalInstalacao(imovelParaSerGerado
							.getHidrometroInstalacaoHistorico()
							.getHidrometroLocalInstalacao());

			movimentoRoteiroEmpresa
					.setDataInstalacaoHidrometro(imovelParaSerGerado
							.getHidrometroInstalacaoHistorico()
							.getDataInstalacao());

			movimentoRoteiroEmpresa
					.setHidrometroProtecao(imovelParaSerGerado
							.getHidrometroInstalacaoHistorico()
							.getHidrometroProtecao());

		}

		if (imovelParaSerGerado.getLigacaoAgua()
				.getHidrometroInstalacaoHistorico() != null) {

			movimentoRoteiroEmpresa
					.setHidrometroLocalInstalacao(imovelParaSerGerado
							.getLigacaoAgua()
							.getHidrometroInstalacaoHistorico()
							.getHidrometroLocalInstalacao());

			movimentoRoteiroEmpresa
					.setDataInstalacaoHidrometro(imovelParaSerGerado
							.getLigacaoAgua()
							.getHidrometroInstalacaoHistorico()
							.getDataInstalacao());

			movimentoRoteiroEmpresa
					.setHidrometroProtecao(imovelParaSerGerado
							.getLigacaoAgua()
							.getHidrometroInstalacaoHistorico()
							.getHidrometroProtecao());

		}

		// id da ligacao agua situação
		if (imovelParaSerGerado.getLigacaoAguaSituacao() != null
				&& imovelParaSerGerado.getLigacaoAguaSituacao()
						.getId() != null) {
			// Situação da ligação de agua
			movimentoRoteiroEmpresa
					.setLigacaoAguaSituacao(imovelParaSerGerado
							.getLigacaoAguaSituacao());
		}

		// id da ligacao esgoto situação
		if (imovelParaSerGerado.getLigacaoEsgotoSituacao() != null
				&& imovelParaSerGerado
						.getLigacaoEsgotoSituacao().getId() != null) {
			// Situação de ligação esgoto
			movimentoRoteiroEmpresa
					.setLigacaoEsgotoSituacao(imovelParaSerGerado
							.getLigacaoEsgotoSituacao());
		}

		// pega as descrições das categorias do imovel

		Categoria categoria = getControladorImovel()
				.obterDescricoesCategoriaImovel(
						imovelParaSerGerado);

		// quantidade de economias
		movimentoRoteiroEmpresa
				.setDescricaoAbreviadaCategoriaImovel(categoria
						.getDescricaoAbreviada());

		// [UC0086 - Obter quantidade de economias]
		int quantidadeEconomias = getControladorImovel()
				.obterQuantidadeEconomias(imovelParaSerGerado);
		// quantidade de economias
		movimentoRoteiroEmpresa
				.setQuantidadeEconomias(new Integer(
						quantidadeEconomias).shortValue());

		// Leitura anterior
		Integer anoMesAnterior = Util
				.subtrairData(anoMesCorrente);
		String leituraAnterior = null;
		Integer idMedicaoTipo = null;
		MedicaoHistorico medicaoHistorico = null;
		Object[] retorno = pesquisaLeituraAnterior(ligacaoAgua,
				ligacaoPoco, anoMesAnterior,
				imovelParaSerGerado);
		// verifica se a leitura anterior é diferente de
		// nula
		if (retorno[0] != null) {
			leituraAnterior = retorno[0].toString();
		}
		// verifica se a leitura situação atual é diferente
		// de
		// nula
		if (retorno[1] != null) {
			medicaoHistorico = (MedicaoHistorico) retorno[1];
		}
		// verifica se o id da medição tipo é diferente de
		// nula
		if (retorno[2] != null) {
			idMedicaoTipo = (Integer) retorno[2];
		}

		// verifica se a leitura anterior é diferente de
		// nula
		// para
		// ser
		// jogado no arquivo
		// txt
		if (leituraAnterior != null) {
			movimentoRoteiroEmpresa
					.setNumeroLeituraAnterior(new Integer(
							leituraAnterior));
			// caso contrario coloca a string com zeros
		}

		// Faixa de leitura esperada

		Object[] faixaInicialFinal = pesquisarFaixaEsperadaOuFalsaCelular(
				imovelParaSerGerado, dadosHidrometro,
				leituraAnterior, medicaoHistorico,
				idMedicaoTipo, sistemaParametro,
				hidrometroSelecionado, numeroDigitosHidrometro);

		hidrometroSelecionado = Boolean
				.parseBoolean(faixaInicialFinal[1].toString());

		boolean faixaFalsaLeitura = Boolean
				.parseBoolean(faixaInicialFinal[2].toString());

		int faixaInicialEsperada = 0;
		int faixaFinalEsperada = 0;

		if (!faixaFalsaLeitura) {
			faixaInicialEsperada = Integer
					.parseInt(faixaInicialFinal[3].toString());

			faixaFinalEsperada = Integer
					.parseInt(faixaInicialFinal[4].toString());
		}

		movimentoRoteiroEmpresa
				.setNumeroFaixaLeituraEsperadaInicial(faixaInicialEsperada);
		movimentoRoteiroEmpresa
				.setNumeroFaixaLeituraEsperadaFinal(faixaFinalEsperada);

		movimentoRoteiroEmpresa.setUltimaAlteracao(new Date());

		movimentoRoteiroEmpresa.setRota(imovelParaSerGerado
				.getQuadra().getRota());

		/**
		 * Obtem a colecao economias por categoria
		 * 
		 */

		Collection colecaoSubCategoria = this
				.getControladorImovel()
				.obterQuantidadeEconomiasSubCategoria(
						imovelParaSerGerado.getId());

		// 1.10.1 - SubCategoria 01
		// 1.10.2 - Quantidade 01
		// 1.10.3 - SubCategoria 02
		// 1.10.4 - Quantidade 02
		if (colecaoSubCategoria != null
				&& !colecaoSubCategoria.isEmpty()) {

			Iterator itera = colecaoSubCategoria.iterator();

			for (int i = 0; i < 2; i++) {

				while (itera.hasNext()) {

					Subcategoria subcategoria = (Subcategoria) itera
							.next();

					// tipoEconomia = categoria_id(1 posição) +
					// subcategoria_codigo(3 posições)

					if (i == 0) {
						// Código da subcategoria do imovel
						movimentoRoteiroEmpresa
								.setCodigoSubcategoria1(subcategoria
										.getCategoria().getId());
						// qtdeEconomia
						movimentoRoteiroEmpresa
								.setQuantidadeEconomias(subcategoria
										.getQuantidadeEconomias()
										.shortValue());
					} else {

						// Código da 2 subcategoria do imovel
						movimentoRoteiroEmpresa
								.setCodigoSubcategoria2(subcategoria
										.getCodigo());
						// qtdeEconomia
						movimentoRoteiroEmpresa
								.setQuantidadeEconomias2(subcategoria
										.getQuantidadeEconomias()
										.shortValue());
					}

				}
			}
		}
		// 1.11 - Consumo
		Integer numeroConsumoFaturadoMes = null;

		Integer anoMesFaturamento = sistemaParametro
				.getAnoMesFaturamento();

		try {

			// 1.11.1 - Consumo 01
			int anoMesSubtraido = Util.subtrairMesDoAnoMes(
					anoMesFaturamento, 6);

			numeroConsumoFaturadoMes = repositorioMicromedicao
					.obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
							imovelParaSerGerado.getId(),
							anoMesSubtraido,
							LigacaoTipo.LIGACAO_AGUA);

			// caso o numero consumo faturado do mes for
			// diferente de nulo
			if (numeroConsumoFaturadoMes != null) {

				movimentoRoteiroEmpresa
						.setNumeroConsumoFaturadoMenos6Meses(numeroConsumoFaturadoMes);

			}

			// 1.11.2 - Consumo 02
			anoMesSubtraido = Util.subtrairMesDoAnoMes(
					anoMesFaturamento, 5);

			numeroConsumoFaturadoMes = repositorioMicromedicao
					.obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
							imovelParaSerGerado.getId(),
							anoMesSubtraido,
							LigacaoTipo.LIGACAO_AGUA);

			// caso o numero consumo faturado do mes for
			// diferente de nulo
			if (numeroConsumoFaturadoMes != null) {

				movimentoRoteiroEmpresa
						.setNumeroConsumoFaturadoMenos5Meses(numeroConsumoFaturadoMes);

			}

			// 1.11.3 - Consumo 03
			anoMesSubtraido = Util.subtrairMesDoAnoMes(
					anoMesFaturamento, 4);

			numeroConsumoFaturadoMes = repositorioMicromedicao
					.obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
							imovelParaSerGerado.getId(),
							anoMesSubtraido,
							LigacaoTipo.LIGACAO_AGUA);

			// caso o numero consumo faturado do mes for
			// diferente de nulo
			if (numeroConsumoFaturadoMes != null) {

				movimentoRoteiroEmpresa
						.setNumeroConsumoFaturadoMenos4Meses(numeroConsumoFaturadoMes);

			}

		} catch (ErroRepositorioException e) {

			throw new ControladorException("erro.sistema", e);

		}

		// 1.12 - Condição
		String condicao = null;

		try {
			condicao = this.repositorioMicromedicao
					.obterDescricaoConsumoTipo(
							imovelParaSerGerado.getId(),
							LigacaoTipo.LIGACAO_AGUA);

		} catch (ErroRepositorioException e) {

			throw new ControladorException("erro.sistema", e);

		}

		if (condicao != null) {
			movimentoRoteiroEmpresa
					.setDescricaoConsumoTipo("" + condicao);
		}

		// 1.13 - Número do Lacre
		if (imovelParaSerGerado.getLigacaoAgua()
				.getNumeroLacre() != null) {
			movimentoRoteiroEmpresa
					.setNumeroLacreLigacaoAgua(
									""
											+ imovelParaSerGerado
													.getImovelPrincipal()
													.getLigacaoAgua()
													.getNumeroLacre());
		}

		// 1.14 - Sequencial da Rota

		movimentoRoteiroEmpresa
				.setNumeroSequencialRota(imovelParaSerGerado
						.getNumeroSequencialRota());

		// 1.15 - Consumo 04

		int anoMesSubtraido = Util.subtrairMesDoAnoMes(
				anoMesFaturamento, 3);

		try {
			numeroConsumoFaturadoMes = repositorioMicromedicao
					.obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
							imovelParaSerGerado.getId(),
							anoMesSubtraido,
							LigacaoTipo.LIGACAO_AGUA);
		} catch (ErroRepositorioException e1) {
			e1.printStackTrace();
		}

		// caso o numero consumo faturado do mes for diferente
		// de nulo
		if (numeroConsumoFaturadoMes != null) {

			movimentoRoteiroEmpresa
					.setNumeroConsumoFaturadoMenos3Meses(numeroConsumoFaturadoMes);

		}

		// 1.16 - Consumo 05
		anoMesSubtraido = Util.subtrairMesDoAnoMes(
				anoMesFaturamento, 2);

		try {
			numeroConsumoFaturadoMes = repositorioMicromedicao
					.obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
							imovelParaSerGerado.getId(),
							anoMesSubtraido,
							LigacaoTipo.LIGACAO_AGUA);
		} catch (ErroRepositorioException e1) {
			e1.printStackTrace();
		}

		// caso o numero consumo faturado do mes for diferente
		// de nulo
		if (numeroConsumoFaturadoMes != null) {

			movimentoRoteiroEmpresa
					.setNumeroConsumoFaturadoMenos2Meses(numeroConsumoFaturadoMes);

		}

		// 1.17 - Consumo 06
		anoMesSubtraido = Util.subtrairMesDoAnoMes(
				anoMesFaturamento, 1);

		try {
			numeroConsumoFaturadoMes = repositorioMicromedicao
					.obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
							imovelParaSerGerado.getId(),
							anoMesSubtraido,
							LigacaoTipo.LIGACAO_AGUA);
		} catch (ErroRepositorioException e1) {
			e1.printStackTrace();
		}

		// caso o numero consumo faturado do mes for diferente
		// de nulo
		if (numeroConsumoFaturadoMes != null) {

			movimentoRoteiroEmpresa
					.setNumeroConsumoFaturadoMenos1Mes(numeroConsumoFaturadoMes);

		}

		// 1.18 - Consumo Medio

		Integer numeroConsumoMedio = null;
		try {
			numeroConsumoMedio = this.repositorioMicromedicao
					.pesquisarNumeroConsumoMedioImovel(
							imovelParaSerGerado.getId(),
							anoMesCorrente,
							LigacaoTipo.LIGACAO_AGUA);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (numeroConsumoMedio != null) {
			movimentoRoteiroEmpresa
					.setNumeroConsumoMedio(numeroConsumoMedio);
		}

		// 1.19 -Rota

		movimentoRoteiroEmpresa
				.setCodigoRota(imovelParaSerGerado.getQuadra()
						.getRota().getCodigo());

		// 1.20 - Codigo Logradouro

		movimentoRoteiroEmpresa
				.setLogradouro(imovelParaSerGerado
						.getLogradouroBairro().getLogradouro());

		// 1.21 - Nome do Logradouro

		movimentoRoteiroEmpresa
				.setNomeLogradouro(imovelParaSerGerado
						.getLogradouroBairro().getLogradouro()
						.getNome());
		
		//?? Logradouro Titulo
		
		
		
		if(imovelParaSerGerado
						.getLogradouroBairro().getLogradouro().getLogradouroTitulo()!=null){
			
			movimentoRoteiroEmpresa.setDescricaoAbreviadaLogradouroTitulo(imovelParaSerGerado
							.getLogradouroBairro().getLogradouro().getLogradouroTitulo().getDescricaoAbreviada());
		}
		
		if(imovelParaSerGerado
		.getLogradouroBairro().getLogradouro().getLogradouroTipo() !=null){
			
		//?? Logradouro Tipo 
		movimentoRoteiroEmpresa.setDescricaoAbreviadaLogradouroTipo(Util.completaString(imovelParaSerGerado
				.getLogradouroBairro().getLogradouro().getLogradouroTipo().getDescricaoAbreviada(),5));
		}
		// 1.22 - Numero do Imovel

		movimentoRoteiroEmpresa
				.setNumeroImovel(imovelParaSerGerado
						.getNumeroImovel());

		// 1.23 - Complemento

		movimentoRoteiroEmpresa
				.setComplementoEndereco(imovelParaSerGerado
						.getComplementoEndereco());

		// 1.24 - Nome do Bairro

		movimentoRoteiroEmpresa
				.setNomeBairro(imovelParaSerGerado
						.getLogradouroBairro().getBairro()
						.getNome());

		// 1.25 - Id da Categoria 

		Categoria categoria1 = (Categoria) getControladorImovel()
				.obterPrincipalCategoriaImovel(
						imovelParaSerGerado.getId());

		movimentoRoteiroEmpresa.setCategoriaPrincipal(categoria1);
		// 1.1 Incricao do Imovel
		movimentoRoteiroEmpresa
				.setInscricaoImovel(imovelParaSerGerado
						.getInscricaoFormatada());

		// Id gerencia Regional
		movimentoRoteiroEmpresa
				.setGerenciaRegional(imovelParaSerGerado
						.getLocalidade().getGerenciaRegional());
		
		
		LeituraTipo leituraTipo = new LeituraTipo();
		
		leituraTipo.setId(idLeituraTipo);
	
		movimentoRoteiroEmpresa.setLeituraTipo(leituraTipo);
		
		getControladorUtil().inserir(movimentoRoteiroEmpresa);

		imovelParaSerGerado = null;

	}
	
	
	
	/**
	 * [UC0083] - Gerar Dados para Leitura Empresa
	 * 
	 * @author Rômulo Aurélio
	 * @date 09/07/2008
	 * 
	 * @param anoMesFaturamento
	 * @param movimentoRoteiroEmpresa
	 * @param qtdImoveis
	 * @param arquivoTexto
	 *            param nomeArquivo
	 * 
	 */
	public void inserirArquivoTextoRoteiroEmpresa(Integer anoMesFaturamento,
			Integer idRota, Integer qtdImoveis, Integer idGrupoFaturamentoRota,
			StringBuilder arquivoTexto, boolean ehSistemaLegado)
			throws ControladorException {

		// Remove todos os dados para o anoMesFaturamento,
		// idGrupoFaturamentoRota e idRota informado
		
			boolean gerarArquivoTexto = true;
			
			ArquivoTextoRoteiroEmpresa arquivo;
			
			try {
				
				arquivo = this.repositorioMicromedicao
					.pesquisaArquivoTextoParaLeituristaPorRota(
							anoMesFaturamento, idRota,
							idGrupoFaturamentoRota);
				
			} catch (ErroRepositorioException e1) {
				throw new ControladorException("erro.sistema", e1);
			}
			
			if(arquivo!=null){
				if(arquivo.getSituacaoTransmissaoLeitura().getId()
						.compareTo(SituacaoTransmissaoLeitura.LIBERADO)==0){
					
					this.getControladorUtil().remover(arquivo);
					
				}else{
					
					gerarArquivoTexto = false;
					
				}
			}
			
			if(gerarArquivoTexto){
	
			FiltroRota filtroRota = new FiltroRota();
			filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroRota
					.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.LOCALIDADE);
			filtroRota
					.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.SETOR_COMERCIAL);
			filtroRota
					.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.LEITURISTA);
	
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA,
					idRota));
	
			Collection colecaoRota = this.getControladorUtil().pesquisar(
					filtroRota, Rota.class.getName());
	
			Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRota);
	
			String codigoSetorComercial = Util.adicionarZerosEsquedaNumero(2,
					String.valueOf(rota.getSetorComercial().getCodigo()));
	
			String codigoRota = Util.adicionarZerosEsquedaNumero(2, String
					.valueOf(rota.getCodigo()));
	
			String nomeArquivoRol;
	
			String nomeZipRol = null;
	
			if (ehSistemaLegado) {
				nomeZipRol = "Rt";
			}
	
			String idGrupoFaturamentoRotaFormatado;
	
			if (idGrupoFaturamentoRota.intValue() < 10) {
				idGrupoFaturamentoRotaFormatado = "0"
						+ idGrupoFaturamentoRota.toString();
			} else {
				idGrupoFaturamentoRotaFormatado = idGrupoFaturamentoRota.toString();
			}
	
			if (idRota != null) {
				nomeArquivoRol = nomeZipRol + "_" + "G"
						+ idGrupoFaturamentoRotaFormatado + "_"
						+ codigoSetorComercial + "_" + codigoRota;
			} else {
				nomeArquivoRol = nomeZipRol + "_" + "G"
						+ idGrupoFaturamentoRotaFormatado + "_" + anoMesFaturamento
						+ "-";
			}
	
			ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa = new ArquivoTextoRoteiroEmpresa();
	
			// ANO_MES_REFERENCIA
			arquivoTextoRoteiroEmpresa.setAnoMesReferencia(anoMesFaturamento);
	
			// FATURAMENTO_GRUPO
			FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();
			faturamentoGrupo.setId(rota.getFaturamentoGrupo().getId());
	
			arquivoTextoRoteiroEmpresa.setFaturamentoGrupo(faturamentoGrupo);
	
			// EMPRESA
			arquivoTextoRoteiroEmpresa.setEmpresa(rota.getEmpresa());
	
			// LOCALIDADE
			arquivoTextoRoteiroEmpresa.setLocalidade(rota.getSetorComercial()
					.getLocalidade());
	
			// CÓDIGO DO SETOR COMERCIAL
			arquivoTextoRoteiroEmpresa.setCodigoSetorComercial1(rota
					.getSetorComercial().getCodigo());
	
			// QUANTIDADE DE IMÓVEIS
			arquivoTextoRoteiroEmpresa.setQuantidadeImovel(qtdImoveis);
	
			// NOME DO ARQUIVO
			arquivoTextoRoteiroEmpresa.setNomeArquivo(nomeArquivoRol);
	
			// INFORMAÇÕES LEITURISTA
			if (rota.getLeiturista() != null) {
				arquivoTextoRoteiroEmpresa.setLeiturista(rota.getLeiturista());
	
				arquivoTextoRoteiroEmpresa.setCodigoLeiturista(rota.getLeiturista()
						.getCodigoDDD());
	
				arquivoTextoRoteiroEmpresa.setNumeroFoneLeiturista(rota
						.getLeiturista().getNumeroFone());
	
				arquivoTextoRoteiroEmpresa.setNumeroImei(rota.getLeiturista()
						.getNumeroImei());
			}
	
			// MENOR E MAIOR NÚMERO DA QUADRA PARA ROTA
			Object[] intervalorNumeroQuadra = null;
			try {
	
				intervalorNumeroQuadra = this.repositorioFaturamento
						.pesquisarIntervaloNumeroQuadraPorRota(rota.getId());
	
			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}
	
			if (intervalorNumeroQuadra != null && intervalorNumeroQuadra[0] != null
					&& intervalorNumeroQuadra[1] != null) {
	
				arquivoTextoRoteiroEmpresa
						.setNumeroQuadraInicial1((Integer) intervalorNumeroQuadra[0]);
	
				arquivoTextoRoteiroEmpresa
						.setNumeroQuadraFinal1((Integer) intervalorNumeroQuadra[1]);
			} else {
	
				// PARA OS CASOS DE ROTA ALTERNATIVA
				arquivoTextoRoteiroEmpresa.setNumeroQuadraInicial1(0);
	
				arquivoTextoRoteiroEmpresa.setNumeroQuadraFinal1(0);
			}
			// ARQUIVO TEMPORÁRIO GERADO PARA ROTA
			byte[] arquivoTextoByte = null;
	
			try {
	
				arquivoTextoByte = IoUtil.transformarObjetoParaBytes(arquivoTexto);
	
			} catch (IOException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}
	
			arquivoTextoRoteiroEmpresa.setArquivoTexto(arquivoTextoByte);
	
			// SITUACAO_TRANSMISSAO_LEITURA
			SituacaoTransmissaoLeitura situacaoTransmissaoLeitura = new SituacaoTransmissaoLeitura();
			situacaoTransmissaoLeitura.setId(SituacaoTransmissaoLeitura.LIBERADO);
	
			arquivoTextoRoteiroEmpresa
					.setSituacaoTransmissaoLeitura(situacaoTransmissaoLeitura);
	
			ServicoTipoCelular servicoTipoCelular = new ServicoTipoCelular();
			servicoTipoCelular.setId(ServicoTipoCelular.LEITURA);
			arquivoTextoRoteiroEmpresa.setServicoTipoCelular(servicoTipoCelular);
	
			// ROTA
			arquivoTextoRoteiroEmpresa.setRota(rota);
	
			// ULTIMA ALTERACAO
			arquivoTextoRoteiroEmpresa.setUltimaAlteracao(new Date());
	
			// INSERINDO NA BASE
			this.getControladorUtil().inserir(arquivoTextoRoteiroEmpresa);
	      }
		
	}
	
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * @author Rômulo Aurélio
	 * @throws ControladorException
	 * @date 07/07/2008
	 */
	
	public Collection retornaImoveisPorRota(Rota rota, int numeroIndice,
			SistemaParametro sistemaParametro) throws ControladorException {
		
		Collection imoveisPorRota = new ArrayList();

		try {

			imoveisPorRota = repositorioMicromedicao
					.pesquisarImoveisParaLeituraPorColecaoRotaCAER(rota, rota.getLeituraTipo().getId());

		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		return imoveisPorRota;
	}
	
	
	
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * @author Rômulo Aurélio
	 * @date 07/07/2008
	 */
	public Collection retornaImoveisPorRota(Rota rota,
			SistemaParametro sistemaParametro) throws ControladorException {

		/**
		 * [SB0004]-Recuperar Dados para inclusao na Tabela
		 */

		// inicializa uma coleção de imoveis
		Collection objetosImoveis = new ArrayList();
		// cria uma coleção de imóvel por rota
		Collection imoveisPorRota = null;

		imoveisPorRota = this.pesquisarImoveisPorRota(rota, sistemaParametro);

		if (imoveisPorRota != null && !imoveisPorRota.isEmpty()) {

			Iterator imovelporRotaIterator = imoveisPorRota.iterator();

			while (imovelporRotaIterator.hasNext()) {

				// cria um array de objetos para pegar os parametros de
				// retorno da pesquisa
				Object[] arrayImoveis = (Object[]) imovelporRotaIterator.next();

				/*
				 * Alterado por Raphael Rossiter em 27/09/2007
				 */
				// Imovel imovel = this
				// .criarImovelApartirDadosPorLeituraConvencional(arrayImoveisPorRota);
				// instancia um imóvel
				Imovel imovel = new Imovel();

				if (arrayImoveis[0] != null) {
					// seta o id no imovel
					imovel.setId((Integer) arrayImoveis[0]);
				}

				if (arrayImoveis[1] != null) {
					// instancia uma localidade para ser setado no imóvel
					Localidade localidade = new Localidade();
					localidade.setId((Integer) arrayImoveis[1]);
					localidade.setDescricao((String) arrayImoveis[30]);
					imovel.setLocalidade(localidade);
				}

				if (arrayImoveis[2] != null) {
					// instancia um setor comercial para ser setado no
					// imóvel
					SetorComercial setorComercial = new SetorComercial();
					setorComercial.setCodigo(Integer.parseInt(arrayImoveis[2]
							.toString()));
					imovel.setSetorComercial(setorComercial);
				}
				Quadra quadra = new Quadra();
				if (arrayImoveis[3] != null) {
					// instancia uma quadra para ser setado no imóvel

					Integer numeroQuadra = (Integer) arrayImoveis[3];
					quadra.setNumeroQuadra(numeroQuadra);
					imovel.setQuadra(quadra);
				}

				if (arrayImoveis[4] != null) {
					// seta o lote no imóvel
					imovel
							.setLote(Short.parseShort(arrayImoveis[4]
									.toString()));
				}

				if (arrayImoveis[5] != null) {
					// seta o lote no imóvel
					imovel.setSubLote(Short.parseShort(arrayImoveis[5]
							.toString()));
				}

				if (arrayImoveis[34] != null) {
					// seta numero sequencial rota
					imovel.setNumeroSequencialRota((Integer) arrayImoveis[34]);
				}

				if (arrayImoveis[6] != null) {
					// instancia uma imovel perfil para ser setado no
					// imóvel
					ImovelPerfil imovelPerfil = new ImovelPerfil();
					imovelPerfil.setId((Integer) arrayImoveis[6]);
					imovel.setImovelPerfil(imovelPerfil);
				}

				LigacaoAgua ligacaoAgua = new LigacaoAgua();
				if (arrayImoveis[7] != null) {
					// instancia uma ligação agua para ser setado no
					// imóvel

					ligacaoAgua.setId((Integer) arrayImoveis[7]);
					ligacaoAgua.setNumeroLacre((String) arrayImoveis[32]);
				}
				// instancia um hidrometro instalação historico para ser
				// colocado na ligacao agua
				HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoLigacaoAgua = new HidrometroInstalacaoHistorico();
				if (arrayImoveis[8] != null) {

					hidrometroInstalacaoHistoricoLigacaoAgua
							.setId((Integer) arrayImoveis[8]);
					MedicaoTipo medicaoTipo = new MedicaoTipo();
					medicaoTipo.setId((Integer) arrayImoveis[26]);
					hidrometroInstalacaoHistoricoLigacaoAgua
							.setMedicaoTipo(medicaoTipo);

					if (arrayImoveis[33] != null) {
						Hidrometro hidrometro = new Hidrometro();
						hidrometro.setNumero((String) arrayImoveis[33]);

						if (arrayImoveis[42] != null) {
							hidrometro
									.setNumeroDigitosLeitura((Short) arrayImoveis[42]);
						}

						hidrometroInstalacaoHistoricoLigacaoAgua
								.setHidrometro(hidrometro);
					}

					ligacaoAgua
							.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistoricoLigacaoAgua);

				}
				imovel.setLigacaoAgua(ligacaoAgua);

				// //instancia um hidrometro instalação historico para
				// ser colocado no imovel
				HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoImovel = new HidrometroInstalacaoHistorico();
				if (arrayImoveis[9] != null) {

					hidrometroInstalacaoHistoricoImovel
							.setId((Integer) arrayImoveis[9]);

					MedicaoTipo medicaoTipo = new MedicaoTipo();
					medicaoTipo.setId((Integer) arrayImoveis[27]);

					hidrometroInstalacaoHistoricoImovel
							.setMedicaoTipo(medicaoTipo);

					if (arrayImoveis[36] != null) {
						Hidrometro hidrometro = new Hidrometro();
						hidrometro.setNumero((String) arrayImoveis[36]);

						if (arrayImoveis[43] != null) {
							hidrometro
									.setNumeroDigitosLeitura((Short) arrayImoveis[43]);
						}

						hidrometroInstalacaoHistoricoImovel
								.setHidrometro(hidrometro);
					}

					imovel
							.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistoricoImovel);

				}
				// instancia a rota
				Rota rotaImovel = new Rota();

				if (arrayImoveis[10] != null) {
					// seta o id da rota
					rotaImovel.setId((Integer) arrayImoveis[10]);
				}

				if (arrayImoveis[11] != null) {
					// seta o indicador fiscalizador suprimido na rota
					rotaImovel.setIndicadorFiscalizarSuprimido(Short
							.parseShort(arrayImoveis[11].toString()));
				}

				if (arrayImoveis[12] != null) {
					// seta o indicador fiscalizador cortado na rota
					rotaImovel.setIndicadorFiscalizarCortado(Short
							.parseShort(arrayImoveis[12].toString()));
				}

				if (arrayImoveis[13] != null) {
					// seta o indicador gerar fiscalizacao na rota
					rotaImovel.setIndicadorGerarFiscalizacao(Short
							.parseShort(arrayImoveis[13].toString()));
				}

				if (arrayImoveis[14] != null) {
					// seta o indicador fgerar falsa faixa na rota
					rotaImovel.setIndicadorGerarFalsaFaixa(Short
							.parseShort(arrayImoveis[14].toString()));
				}

				if (arrayImoveis[15] != null) {
					// seta o percentual geracao fiscalizacao na rota
					rotaImovel
							.setPercentualGeracaoFiscalizacao((BigDecimal) (arrayImoveis[15]));
				}

				if (arrayImoveis[16] != null) {
					// seta o percentual geracao faixa falsa na rota
					rotaImovel
							.setPercentualGeracaoFaixaFalsa((BigDecimal) (arrayImoveis[16]));
				}

				if (arrayImoveis[35] != null) {
					// seta o codigo da rota
					rotaImovel.setCodigo((Short) (arrayImoveis[35]));
				}

				if(rota.getLeiturista() != null ){
					rotaImovel.setLeiturista(rota.getLeiturista());
				}
				
				// instancia a empresa
				Empresa empresa = new Empresa();
				if (arrayImoveis[17] != null) {
					// seta o id na empresa
					empresa.setId((Integer) arrayImoveis[17]);
				}

				if (arrayImoveis[18] != null) {
					// seta a descrição abreviada na empresa
					empresa.setDescricaoAbreviada(arrayImoveis[18].toString());
				}

				if (arrayImoveis[19] != null) {
					// seta email da empresa
					empresa.setEmail(arrayImoveis[19].toString());
				}

				// seta a empresa na rota
				rotaImovel.setEmpresa(empresa);
				// instancia o faturamento
				FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();
				if (arrayImoveis[20] != null) {
					// seta o id no faturamentGrupo
					faturamentoGrupo.setId((Integer) arrayImoveis[20]);

				}
				if (arrayImoveis[21] != null) {
					// seta o descrição no faturamentGrupo
					faturamentoGrupo.setDescricao((String) arrayImoveis[21]);
				}
				// seta o faturamento na rota
				rotaImovel.setFaturamentoGrupo(faturamentoGrupo);
				if (arrayImoveis[22] != null) {
					// instancia a ligação esgoto situação
					LeituraTipo leituraTipo = new LeituraTipo();
					// seta o id na ligação esgoto situação
					leituraTipo.setId((Integer) arrayImoveis[22]);
					// seta a ligação esgoto situação no imovel
					rotaImovel.setLeituraTipo(leituraTipo);
				}

				// seta a rota na quadra
				quadra.setRota(rotaImovel);
				// seta a quadra no imovel
				imovel.setQuadra(quadra);
				if (arrayImoveis[23] != null) {
					// instancia a ligação agua situação
					LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
					// seta o id na ligação agua situação
					ligacaoAguaSituacao.setId((Integer) arrayImoveis[23]);
					ligacaoAguaSituacao.setDescricao((String) arrayImoveis[31]);
					ligacaoAguaSituacao
							.setDescricaoAbreviado((String) arrayImoveis[37]);
					ligacaoAguaSituacao
							.setIndicadorFaturamentoSituacao((Short) arrayImoveis[46]);

					// seta a ligação agua situação no imovel
					imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
				}
				if (arrayImoveis[24] != null) {
					// instancia a ligação esgoto situação
					LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
					// seta o id na ligação esgoto situação
					ligacaoEsgotoSituacao.setId((Integer) arrayImoveis[24]);
					ligacaoEsgotoSituacao
							.setIndicadorFaturamentoSituacao((Short) arrayImoveis[47]);
					// seta a ligação esgoto situação no imovel
					imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
				}

				if (arrayImoveis[25] != null) {
					// instancia o faturamento situacao tipo
					FaturamentoSituacaoTipo faturamentoSituacaoTipo = new FaturamentoSituacaoTipo();
					// seta o id no faturamento situacao tipo
					faturamentoSituacaoTipo
							.setIndicadorParalisacaoLeitura((Short) arrayImoveis[25]);
					// seta a ligação esgoto situação no imovel
					imovel.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);
				}

				if (arrayImoveis[28] != null) {
					// instancia o gerencia regional
					GerenciaRegional gerenciaRegional = new GerenciaRegional();

					// seta o id no faturamento situacao tipo
					gerenciaRegional.setId((Integer) arrayImoveis[28]);
					gerenciaRegional.setNome((String) arrayImoveis[29]);

					// seta a ligação esgoto situação no imovel
					imovel.getLocalidade()
							.setGerenciaRegional(gerenciaRegional);
				}

				if (arrayImoveis[38] != null) {
					// Logradouro Bairro
					LogradouroBairro logradouroBairro = new LogradouroBairro();

					// Logradouro
					Logradouro logradouro = new Logradouro();
					logradouro.setId((Integer) arrayImoveis[38]);
					logradouro.setNome((String) arrayImoveis[39]);

					if (arrayImoveis[45] != null) {
						LogradouroTipo logradouroTipo = new LogradouroTipo();
						logradouroTipo
								.setDescricaoAbreviada((String) arrayImoveis[45]);
						logradouro.setLogradouroTipo(logradouroTipo);
					}

					if (arrayImoveis[44] != null) {
						LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo
								.setDescricaoAbreviada((String) arrayImoveis[44]);
						logradouro.setLogradouroTitulo(logradouroTitulo);

					}

					logradouroBairro.setLogradouro(logradouro);

					imovel.setLogradouroBairro(logradouroBairro);
				}

				if (arrayImoveis[40] != null) {
					// Numero Imovel
					imovel.setNumeroImovel((String) arrayImoveis[40]);
				}

				if (arrayImoveis[41] != null) {
					// Complemento Imovel
					imovel.setComplementoEndereco((String) arrayImoveis[41]);
				}

				if (arrayImoveis[48] != null) {
					// Bairro
					Bairro bairro = new Bairro();
					bairro.setId((Integer) arrayImoveis[48]);
					imovel.getLogradouroBairro().setBairro(bairro);
				}

				if (arrayImoveis[49] != null) {
					// Nome Bairro
					imovel.getLogradouroBairro().getBairro().setNome(
							(String) arrayImoveis[49]);
				}

				// adiciona na coleção de imoveis
				objetosImoveis.add(imovel);
				arrayImoveis = null;

			}
		}

		imoveisPorRota.clear();
		imoveisPorRota = null;
		return objetosImoveis;

	}


	
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * @author Rômulo Aurélio
	 * @throws ControladorException 
	 * @date 07/07/2008
	 */
	public Collection pesquisarImoveisPorRota(Rota rota, 
			SistemaParametro sistemaParametro) throws ControladorException {
		
		Collection imoveisPorRota = new ArrayList();
		
		/*
		 * Caso a rota não esteja com o indicador de rota alternativa ativo; a pesquisa
		 * dos imóveis será feita a partir de sua quadra.
		 */
		if (!rota.getIndicadorRotaAlternativa().equals(ConstantesSistema.SIM)){
		
			try {

				// recupera todos os imóveis da coleção de rotas do tipo ROL
				imoveisPorRota = repositorioMicromedicao
		    		.pesquisarImoveisParaLeituraPorColecaoRotaCAER(rota,
		                    rota.getLeituraTipo().getId());

			} catch (ErroRepositorioException e) {
				e.printStackTrace();
			}
		
		}/*
		 * Caso contrário; a pesquisa dos imóveis será feita a partir da rota alternativa 
		 * que estará associada ao mesmo.
		 */
		else{
			
			try {
				
				imoveisPorRota = repositorioMicromedicao
					.pesquisarImoveisPorRotaAlternativaCAER(rota, sistemaParametro.getNomeAbreviadoEmpresa());

			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
		}
		
		
		return imoveisPorRota;
	}
	
	
	/**
	 * Author: Rômulo Aurélio [UC0083] - Gerar Dados para Leitura
	 * 
	 * @param objetosImoveis
	 * @return
	 */

	public Collection verificarImoveisParaSerGerados(Collection objetosImoveis) {

		// Instancia uma coleção que será usada para gerar o arquivo
		// txt.
		Collection<Imovel> imoveisParaSerGerados = new ArrayList();

		Iterator imovelIterator = objetosImoveis.iterator();
		while (imovelIterator.hasNext()) {
			// Recupera o imovel da coleção
			Imovel imovel = (Imovel) imovelIterator.next();
			// variavel responsável para entrar em uma das 4 condicões
			// abaixo

			boolean achouImovel = false;

			// [SF0002] - Verificar situação especial de faturamento

			// caso no imovel o faturamento situação grupo seja diferente de
			// nulo e
			// igual a leitura
			// não realizada então não seleciona o imovel caso contrario
			// seleciona.
			if (imovel.getFaturamentoSituacaoTipo() == null
					|| !imovel
							.getFaturamentoSituacaoTipo()
							.equals(
									FaturamentoSituacaoTipo.INDICADOR_PARALIZACAO_LEITURA_NAO_REALIZADA)) {

				if (!achouImovel) {
					// modificado por Flávio Leonardo a pedido de Rosana,Aryed e
					// Eduardo
					// é pra sair no rol todos os imoveis que tiverem hidrometro
					// independente do indicador de situacao do faturamento

					// Verifica se a situação da ligação agua é diferente de
					// nulo
					// Se for verifica se está ligado ou cortado
					// if (imovel.getLigacaoAguaSituacao() != null
					// &&
					// imovel.getLigacaoAguaSituacao().getIndicadorFaturamentoSituacao()
					// != null
					// &&
					// (imovel.getLigacaoAguaSituacao().getIndicadorFaturamentoSituacao().equals(
					// LigacaoAguaSituacao.FATURAMENTO_ATIVO))) {

					// Se for ligado ou cortado então
					// Verifica se a ligação agua é diferente de nulo
					// se for verifica se o id da ligação agua é igual
					// ao id do imovel e se o id do histórico da instalação do
					// hidrometro
					// é diferente de null
					if (imovel.getLigacaoAgua() != null
							&& imovel.getLigacaoAgua().getId() != null
							&& (imovel.getLigacaoAgua().getId().equals(
									imovel.getId())
									&& imovel.getLigacaoAgua()
											.getHidrometroInstalacaoHistorico() != null && imovel
									.getLigacaoAgua()
									.getHidrometroInstalacaoHistorico().getId() != null)) {
						imoveisParaSerGerados.add(imovel);
						achouImovel = true;

					}
					// }
				}

				if (!achouImovel) {

					// Verifica se a situação da ligação esgoto é diferente de
					// nulo
					// Se for verifica se está ligado
					if (imovel.getLigacaoEsgotoSituacao() != null
							&& imovel.getLigacaoEsgotoSituacao()
									.getIndicadorFaturamentoSituacao() != null
							&& (imovel.getLigacaoEsgotoSituacao()
									.getIndicadorFaturamentoSituacao()
									.equals(LigacaoEsgotoSituacao.FATURAMENTO_ATIVO))) {

						// Verifica se o id do hidrometro historico é diferente
						// de
						// nulo na tabela imovel
						if (imovel.getHidrometroInstalacaoHistorico() != null
								&& imovel.getHidrometroInstalacaoHistorico()
										.getId() != null) {
							imoveisParaSerGerados.add(imovel);
							achouImovel = true;
						}
					}
				}
				if (!achouImovel) {
					// Verifica se a situação da ligação agua é diferente de
					// nulo
					// Se for verifica se está suprimido
					if (imovel.getLigacaoAguaSituacao() != null
							&& imovel.getLigacaoAguaSituacao().getId() != null
							&& imovel.getLigacaoAguaSituacao().getId().equals(
									LigacaoAguaSituacao.SUPRIMIDO)) {

						// verifica se o indicador de fiscalização suprimido é
						// diferente de nulo se for verifica se está ativo
						if (imovel.getQuadra().getRota()
								.getIndicadorFiscalizarSuprimido() != null
								&& imovel.getQuadra().getRota()
										.getIndicadorFiscalizarSuprimido()
										.equals(Rota.INDICADOR_SUPRIMIDO_ATIVO)) {
							imoveisParaSerGerados.add(imovel);
							achouImovel = true;
						}

					}
				}
				if (!achouImovel) {
					// Verifica se a situação da ligação agua é diferente de
					// nulo Se for verifica se está cortado
					if ((imovel.getLigacaoAguaSituacao() != null && imovel
							.getLigacaoAguaSituacao().getId() != null)
							&& (imovel.getLigacaoAguaSituacao().getId()
									.equals(LigacaoAguaSituacao.CORTADO))) {

						// Se for cortado então verifica se a ligação agua é
						// diferente de nulo
						// se for verifica se o id da ligação agua é igual ao id
						// do
						// imovel e se o id do histórico da instalação do
						// hidrometro
						// é null
						if (imovel.getLigacaoAgua() != null
								&& imovel.getLigacaoAgua().getId() != null
								&& (imovel.getLigacaoAgua().getId().equals(
										imovel.getId()) && (imovel
										.getLigacaoAgua()
										.getHidrometroInstalacaoHistorico() == null || imovel
										.getLigacaoAgua()
										.getHidrometroInstalacaoHistorico()
										.getId() == null))) {

							// verifica se o indicador de fiscalização cortado é
							// diferente de nulo se for verifica se está ativo
							if (imovel.getQuadra().getRota()
									.getIndicadorFiscalizarCortado() != null
									&& imovel
											.getQuadra()
											.getRota()
											.getIndicadorFiscalizarCortado()
											.equals(
													Rota.INDICADOR_CORTADO_ATIVO)) {
								imoveisParaSerGerados.add(imovel);
								achouImovel = true;
							}
						}

					}
				}

			}
		}

		return imoveisParaSerGerados;
	}		

}


