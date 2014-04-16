package gcom.gui.micromedicao;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelSubCategoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.bean.ImovelMicromedicao;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.gui.GcomAction;
import gcom.micromedicao.medicao.FiltroMedicaoHistoricoSql;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirDadosAnaliseMedicaoConsumoAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("dadosAnaliseMedicaoConsumo");

		LeituraConsumoActionForm leituraConsumoActionForm = new LeituraConsumoActionForm();

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		sessao.removeAttribute("leituraConsumoActionForm");
		
		String codigoImovel = httpServletRequest
				.getParameter("idRegistroAtualizacao");
		
		String idMedicaoTipo = httpServletRequest
				.getParameter("medicaoTipo");
		
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo)sessao.getAttribute("faturamentoGrupo");
		
		int quantidadeTotalPaginas = 0;
		if (sessao.getAttribute("totalRegistros") != null
				&& !sessao.getAttribute("totalRegistros").equals("")) {
			int totalRegistros = (Integer) sessao
					.getAttribute("totalRegistros");
			quantidadeTotalPaginas = ((Double) Math.ceil(Double.parseDouble(""
					+ totalRegistros) / 10)).intValue();
		}

		FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql = new FiltroMedicaoHistoricoSql();
		int numeroPaginasPesquisa = 0;
		if (sessao.getAttribute("numeroPaginasPesquisa") != null
				&& !sessao.getAttribute("numeroPaginasPesquisa").equals("")) {
			numeroPaginasPesquisa = (Integer) sessao
					.getAttribute("numeroPaginasPesquisa");
		}

		if (sessao.getAttribute("filtroMedicaoHistoricoSql") != null) {
			filtroMedicaoHistoricoSql = (FiltroMedicaoHistoricoSql) sessao
					.getAttribute("filtroMedicaoHistoricoSql");
		}
		int index = 0;
		if (sessao.getAttribute("index") != null) {
			index = (Integer) sessao.getAttribute("index");
		}
		Collection colecaoIdsImovel = (Collection) sessao
				.getAttribute("colecaoImovelMedicao");

		// verifica se é a primeira vez.Se for então pesquisa o index do id do
		// imovel na coleção para não precisar ficar rodando a coleção toda vez
		// que o usuário quiser o imovel anterior ou o proximo imovel
		if (codigoImovel != null && !codigoImovel.equals("")) {
			int i = 0;
			Iterator iterator = colecaoIdsImovel.iterator();
			while (iterator.hasNext()) {
				ImovelMicromedicao imovelMicromedicao = (ImovelMicromedicao) iterator
						.next();
				if (!imovelMicromedicao.getImovel().getId().equals(
						new Integer(codigoImovel))) {
					i = i + 1;
				} else {
					break;
				}
			}
			index = i;
			sessao.setAttribute("index", index);
			// caso não seja a primeira vez então, dependendo do parametro que
			// foi passado, recupera o id do imóvel para ser pesquisado
		} else {
			if (httpServletRequest.getParameter("imovelAnterior") != null) {

				index = index - 1;

			}
			if (httpServletRequest.getParameter("proximoImovel") != null) {
				index = index + 1;

			}

			// caso
			if (index == colecaoIdsImovel.size() || index == -1) {
				if (index == colecaoIdsImovel.size()) {
					numeroPaginasPesquisa = numeroPaginasPesquisa + 1;
					index = 0;
					colecaoIdsImovel = fachada.filtrarExcecoesLeiturasConsumos(faturamentoGrupo,
							filtroMedicaoHistoricoSql, numeroPaginasPesquisa,false, faturamentoGrupo.getAnoMesReferencia()+"",
							(String)sessao.getAttribute("valorAguaEsgotoInicial"), (String)sessao.getAttribute("valorAguaEsgotoFinal"));

				} else {
					if (index == -1 && numeroPaginasPesquisa != 0) {
						numeroPaginasPesquisa = numeroPaginasPesquisa - 1;
						colecaoIdsImovel = fachada
								.filtrarExcecoesLeiturasConsumos(faturamentoGrupo,
										filtroMedicaoHistoricoSql,
										numeroPaginasPesquisa, false, faturamentoGrupo.getAnoMesReferencia()+"",
										(String)sessao.getAttribute("valorAguaEsgotoInicial"), (String)sessao.getAttribute("valorAguaEsgotoFinal"));
						index = colecaoIdsImovel.size() - 1;
					}
				}

				sessao.setAttribute("numeroPaginasPesquisa",
						numeroPaginasPesquisa);
				sessao.setAttribute("colecaoImovelMedicao", colecaoIdsImovel);
				if (colecaoIdsImovel != null && !colecaoIdsImovel.isEmpty()) {

					// recupera o id do imovel
					codigoImovel = ((ImovelMicromedicao) ((List) colecaoIdsImovel)
							.get(index)).getImovel().getId().toString();
					if(((ImovelMicromedicao) ((List) colecaoIdsImovel)
							.get(index)).getMedicaoHistorico().getMedicaoTipo() != null){
						idMedicaoTipo = ((ImovelMicromedicao) ((List) colecaoIdsImovel)
								.get(index)).getMedicaoHistorico().getMedicaoTipo().getId().toString();
					}
					sessao.setAttribute("index", index);
				}
			} else {

				// recupera o id do imovel
				codigoImovel = ((ImovelMicromedicao) ((List) colecaoIdsImovel)
						.get(index)).getImovel().getId().toString();
				
				if(((ImovelMicromedicao) ((List) colecaoIdsImovel)
						.get(index)).getMedicaoHistorico().getMedicaoTipo() != null
                        && ((ImovelMicromedicao) ((List) colecaoIdsImovel)
                                .get(index)).getMedicaoHistorico().getMedicaoTipo().getId() != null){
					idMedicaoTipo = ((ImovelMicromedicao) ((List) colecaoIdsImovel)
							.get(index)).getMedicaoHistorico().getMedicaoTipo().getId().toString();
				}
				sessao.setAttribute("index", index);
			}
		}

		// -------Parte que trata do código quando o usuário tecla enter
		// caso seja o id do municipio
		int consumoMedioHidrometro = 0;

		if (codigoImovel != null && !codigoImovel.equals("")) {

			Object[] parmClienteImovel = null;
			// ---- Carrega objetos ligados a cliente
			//Integer anoMesfaturamentoGrupo = null;
			
			boolean ligacaoAgua = false;
			if(idMedicaoTipo != null && idMedicaoTipo.trim().equals("" + MedicaoTipo.LIGACAO_AGUA)){
				ligacaoAgua = true;
			}
			


			Collection parmsclienteImovel = fachada
					.pesquiarImovelExcecoesApresentaDados(new Integer(
							codigoImovel), ligacaoAgua);
			if (!parmsclienteImovel.isEmpty()) {
				parmClienteImovel = (Object[]) parmsclienteImovel.iterator()
						.next();
				// ---- Seta os atributos
				leituraConsumoActionForm.setImovel(codigoImovel);
				// id faturamento grupo
				if (parmClienteImovel[0] != null) {
					leituraConsumoActionForm
							.setGrupoFaturamento(((Integer) parmClienteImovel[0])
									.toString());
				}
				// ano mes faturamento grupo
				if (parmClienteImovel[1] != null) {
					String anoMes = ((Integer) parmClienteImovel[1]).toString();
					//anoMesfaturamentoGrupo = (Integer) parmClienteImovel[1];

					String ano = anoMes.substring(0, 4);
					String mes = anoMes.substring(4, 6);
					leituraConsumoActionForm.setMesAnoFaturamentoCorrente(mes
							+ "/" + ano);
				}

				leituraConsumoActionForm
						.setIdImovelSubstituirConsumo(codigoImovel);

				// nome empresa
				if (parmClienteImovel[2] != null) {
					leituraConsumoActionForm
							.setEmpresaLeitura((String) parmClienteImovel[2]);
				}
				//Rota e Seq de Rota
				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.rota");
				filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, new Integer(codigoImovel)));
				Collection imoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
				if(!imoveis.isEmpty()){
					Imovel imovel = (Imovel) imoveis.iterator().next();
					leituraConsumoActionForm.setRota(imovel.getQuadra().getRota().getCodigo() + "");
					leituraConsumoActionForm.setSeqRota(imovel.getNumeroSequencialRota().toString());
				}
				

				String inscricaoFormatada = fachada
						.pesquisarInscricaoImovel(new Integer(codigoImovel));

				leituraConsumoActionForm
						.setInscricaoImovel(inscricaoFormatada != null ? inscricaoFormatada
								: "");

				String enderecoFormatado = null;
				try {
					enderecoFormatado = fachada
							.pesquisarEnderecoFormatado(new Integer(
									codigoImovel));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (ControladorException e) {
					e.printStackTrace();
				}

				leituraConsumoActionForm
						.setEnderecoFormatado(enderecoFormatado != null ? enderecoFormatado
								: "");

				// imovel condomínio
				if (parmClienteImovel[3] != null) {
					leituraConsumoActionForm
							.setIndicadorImovelCondominio(((Short) parmClienteImovel[3])
									.toString().trim().equals("1") ? "Sim" : "Não");
				}

				// descrição ligação agua situação
				if (parmClienteImovel[4] != null) {
					leituraConsumoActionForm
							.setLigacaoEsgotoSituacao(((String) parmClienteImovel[4])
									.toString());
				}

				// descrição ligação esgoto situação
				if (parmClienteImovel[5] != null) {
					leituraConsumoActionForm
							.setLigacaoAguaSituacao(((String) parmClienteImovel[5])
									.toString());
				}

				// nome cliente
				if (parmClienteImovel[6] != null) {
					leituraConsumoActionForm
							.setClienteNome(((String) parmClienteImovel[6])
									.toString());
				}

				// cpf do cliente for diferente de null
				if (parmClienteImovel[7] != null) {
					leituraConsumoActionForm
							.setClienteCpfCnpj(((String) parmClienteImovel[7])
									.toString());
				} else {
					// senão cnpj do cliente for null
					if (parmClienteImovel[8] != null) {
						leituraConsumoActionForm
								.setClienteCpfCnpj(((String) parmClienteImovel[8])
										.toString());
					}
				}
				// numero do hidrometro
				if (parmClienteImovel[9] != null) {
					leituraConsumoActionForm
							.setNumeroHidrometro(((String) parmClienteImovel[9])
									.toString());
				}

				// data de instalação de hidrometro
				if (parmClienteImovel[10] != null) {
					leituraConsumoActionForm.setInstalacaoHidrometro(Util
							.formatarData((Date) parmClienteImovel[10]));
				}
				// descrição hidrometro capacidade
				if (parmClienteImovel[11] != null) {
					leituraConsumoActionForm
							.setCapacidadeHidrometro((String) parmClienteImovel[11]);
				}
				// descrição hidrometro tipo
				if (parmClienteImovel[12] != null) {
					leituraConsumoActionForm
							.setTipoHidrometro((String) parmClienteImovel[12]);
				}
				// descrição hidrometro marca
				if (parmClienteImovel[13] != null) {
					leituraConsumoActionForm
							.setMarcaHidrometro((String) parmClienteImovel[13]);
				}
				// descrição hidrometro local instalação
				if (parmClienteImovel[14] != null) {
					leituraConsumoActionForm
							.setLocalInstalacaoHidrometro((String) parmClienteImovel[14]);
				}
				// descrição hidrometro diametro
				if (parmClienteImovel[15] != null) {
					leituraConsumoActionForm
							.setDiametroHidrometro((String) parmClienteImovel[15]);
				}
				// descrição hidrometro proteção
				if (parmClienteImovel[16] != null) {
					leituraConsumoActionForm
							.setProtecaoHidrometro((String) parmClienteImovel[16]);
				}
				// indicador cavalete do hidrometro instalação histórico
				if (parmClienteImovel[17] != null) {
					Short icCavalete = (Short) parmClienteImovel[17];
					if (icCavalete != null && icCavalete == 1) {
						leituraConsumoActionForm.setIndicadorCavalete("Sim");
					} else {
						leituraConsumoActionForm.setIndicadorCavalete("Não");
					}
				}
				// ano fabricação do hidrometro
				if (parmClienteImovel[18] != null) {
					leituraConsumoActionForm
							.setAnoFabricacao(((Short) parmClienteImovel[18])
									.toString());
				}

				// descrição imovel perfil
				if (parmClienteImovel[19] != null) {
					leituraConsumoActionForm
							.setPerfilImovel((String) parmClienteImovel[19]);
				}
				// data ligação agua
				if (parmClienteImovel[20] != null) {
					leituraConsumoActionForm.setDataLigacaoAgua(Util
							.formatarData((Date) parmClienteImovel[20]));
				}
				// data corte agua
				if (parmClienteImovel[21] != null) {
					leituraConsumoActionForm.setDataCorteAgua(Util
							.formatarData((Date) parmClienteImovel[21]));
				}

				// data religação agua
				if (parmClienteImovel[22] != null) {
					leituraConsumoActionForm.setDataReligacaoAgua(Util
							.formatarData((Date) parmClienteImovel[22]));
				}
				// data supressão agua
				if (parmClienteImovel[23] != null) {
					leituraConsumoActionForm.setDataSupressaoAgua(Util
							.formatarData((Date) parmClienteImovel[23]));
				}
				// data restabelecimento agua
				if (parmClienteImovel[35] != null) {
					leituraConsumoActionForm.setDataRestabelecimentoAgua(Util
							.formatarData((Date) parmClienteImovel[35]));
				}
				// descrição ligação agua diametro
				if (parmClienteImovel[24] != null) {
					leituraConsumoActionForm
							.setDescricaoLigacaoAguaDiametro((String) parmClienteImovel[24]);
				}
				// descrição ligação agua material
				if (parmClienteImovel[25] != null) {
					leituraConsumoActionForm
							.setDescricaoLigacaoAguaMaterial((String) parmClienteImovel[25]);
				}

				// numero consumo mínimo agua
				if (parmClienteImovel[26] != null) {
					leituraConsumoActionForm
							.setNumeroConsumominimoAgua(((Integer) parmClienteImovel[26])
									.toString());
				}

				// Perfil de ligação
				if (parmClienteImovel[36] != null) {
					leituraConsumoActionForm
							.setDescricaoligacaoAguaPerfil((String) parmClienteImovel[36]);
				}

				// data ligação esgoto
				if (parmClienteImovel[27] != null) {
					leituraConsumoActionForm.setDataLigacaoEsgoto(Util
							.formatarData((Date) parmClienteImovel[27]));
				}
				// descrição ligação esgoto diametro
				if (parmClienteImovel[28] != null) {
					leituraConsumoActionForm
							.setDescricaoLigacaoEsgotoDiametro((String) parmClienteImovel[28]);
				}
				// descrição ligação esgoto material
				if (parmClienteImovel[29] != null) {
					leituraConsumoActionForm
							.setDescricaoLigacaoEsgotoMaterial((String) parmClienteImovel[29]);
				}
				// descrição ligação esgoto perfil
				if (parmClienteImovel[30] != null) {
					leituraConsumoActionForm
							.setDescricaoligacaoEsgotoPerfil((String) parmClienteImovel[30]);
				}
				// numero consumo mínimo esgoto
				if (parmClienteImovel[31] != null) {
					leituraConsumoActionForm
							.setNumeroConsumominimoEsgoto(((Integer) parmClienteImovel[31])
									.toString());
				}
				// percentual ligação esgoto
				if (parmClienteImovel[32] != null) {
					leituraConsumoActionForm
							.setPercentualEsgoto((Util
									.formatarMoedaReal((BigDecimal) parmClienteImovel[32])));
				}
				// percentual coleta ligação esgoto
				if (parmClienteImovel[33] != null) {
					leituraConsumoActionForm
							.setPercentualAguaConsumidaColetada((Util
									.formatarMoedaReal((BigDecimal) parmClienteImovel[33])));
				}
				// descrição tipo poço
				if (parmClienteImovel[34] != null) {
					leituraConsumoActionForm
							.setDescricaoPocoTipo((String) parmClienteImovel[34]);
				}
				// descrição tipo poço
				if (parmClienteImovel[37] != null) {
					leituraConsumoActionForm
							.setIdLigacaoEsgoto(((Integer) parmClienteImovel[37])
									.toString());
				}
				
				//id Ligação Agua
				if (parmClienteImovel[38] != null) {
					leituraConsumoActionForm
							.setIdLigacaoAgua(((Integer) parmClienteImovel[38])
									.toString());
				}
				
				//id Ligação Agua Situação
				if (parmClienteImovel[39] != null) {
					leituraConsumoActionForm
							.setIdLigacaoAguaSituacao(((Integer) parmClienteImovel[39])
									.toString());
				}
				
				//id imovel condominio
				if (parmClienteImovel[40] != null) {
					leituraConsumoActionForm
							.setImovelCondominio(((Integer) parmClienteImovel[40])
									.toString());
				}

			}
			

			// --- OBTER DESCRICAO DO IMOVEL
			Imovel imovelDescricaoCategoria = new Imovel();
			imovelDescricaoCategoria.setId(new Integer(codigoImovel));
			Categoria categoria = (Categoria) fachada
					.obterDescricoesCategoriaImovel(imovelDescricaoCategoria);

			leituraConsumoActionForm.setCategoriaImovel(categoria
					.getDescricaoAbreviada());

			// --- OBTER QUANTIDADE DE ECONOMIAS
			int qtdEconomias = fachada
					.obterQuantidadeEconomias(imovelDescricaoCategoria);

			leituraConsumoActionForm.setQuantidadeEconomia("" + qtdEconomias);

			Object[] parmMedicaoHistorico = null;
			// ---- Carrega objetos ligados a cliente

			Collection parmsMedicaoHistorico = fachada
					.pesquiarMedicaoConsumoHistoricoExcecoesApresentaDados(faturamentoGrupo,new Integer(
							codigoImovel), ligacaoAgua);
			if (!parmsclienteImovel.isEmpty()) {
				parmMedicaoHistorico = (Object[]) parmsMedicaoHistorico
						.iterator().next();
				// ---- Seta os atributos

				// descrição tipo medicao
				if (parmMedicaoHistorico[0] != null) {
					leituraConsumoActionForm
							.setTipoMedicao((String) parmMedicaoHistorico[0]);
				}
				MedicaoTipo medicaoTipo = new MedicaoTipo();
				// id tipo medicao
				if (parmMedicaoHistorico[1] != null) {
					leituraConsumoActionForm
							.setIdTipoMedicao(((Integer) parmMedicaoHistorico[1])
									.toString());
					medicaoTipo.setId((Integer) parmMedicaoHistorico[1]);
				}
				// data leitura anterior
				Date dataLeituraAnterior = null;
				if (parmMedicaoHistorico[2] != null) {
					dataLeituraAnterior = (Date) parmMedicaoHistorico[2];
				}
				// data leitura atual faturada
				Date dtLeituraAtualFaturada = null;
				if (parmMedicaoHistorico[3] != null) {
					dtLeituraAtualFaturada = (Date) parmMedicaoHistorico[3];
				}

				// --- fim variavel
				int diasConsumo = 0;
				if (dataLeituraAnterior != null
						&& dtLeituraAtualFaturada != null) {
					diasConsumo = gcom.util.Util
							.obterQuantidadeDiasEntreDuasDatas(
									dataLeituraAnterior, dtLeituraAtualFaturada);
				}

				leituraConsumoActionForm.setDiasConsumo("" + diasConsumo);

				if (dataLeituraAnterior != null) {
					leituraConsumoActionForm.setDtLeituraAnterior(Util
							.formatarData(dataLeituraAnterior));
				}
				// leitura anterior faturamento
				if (parmMedicaoHistorico[5] != null) {
					leituraConsumoActionForm
							.setLeituraAnterior(((Integer) parmMedicaoHistorico[5])
									.toString());
				}
				// data leitura atual informada
				if (parmMedicaoHistorico[6] != null) {
					leituraConsumoActionForm.setDtLeituraInformada((Util
							.formatarData((Date) parmMedicaoHistorico[6])));
				}
				// leitura atual informada
				if (parmMedicaoHistorico[7] != null) {
					leituraConsumoActionForm
							.setLeituraAtualInformada(((Integer) parmMedicaoHistorico[7])
									.toString());
				}
				// data leitura faturada
				if (dtLeituraAtualFaturada != null) {
					leituraConsumoActionForm.setDtLeituraFaturada(Util
							.formatarData(dtLeituraAtualFaturada));
				}
				// leitura atual faturamento
				if (parmMedicaoHistorico[4] != null) {
					leituraConsumoActionForm
							.setLeituraAnterior(((Integer) parmMedicaoHistorico[4])
									.toString());
				}
				// descrição leitura situação atual
				if (parmMedicaoHistorico[8] != null) {
					leituraConsumoActionForm
							.setSituacaoLeituraAtual(((String) parmMedicaoHistorico[8])
									.toString());
				}

				// data leitura atual faturamento
				if (parmMedicaoHistorico[9] != null) {
					leituraConsumoActionForm.setDtLeituraFaturada((Util
							.formatarData((Date) parmMedicaoHistorico[9])));
				}

				// leitura atual faturamento
				if (parmMedicaoHistorico[10] != null) {
					leituraConsumoActionForm
							.setLeituraAtualFaturada(((Integer) parmMedicaoHistorico[10])
									.toString());
				}
				// id funcionário
				if (parmMedicaoHistorico[11] != null) {
					leituraConsumoActionForm
							.setCodigoFuncionario(((Integer) parmMedicaoHistorico[11])
									.toString());
				}
				// descrição leitura anormalidade informada
				if (parmMedicaoHistorico[12] != null) {
					leituraConsumoActionForm
							.setAnormalidadeLeituraInformada((String) parmMedicaoHistorico[12]);
				}
				// descrição leitura anormalidade faturamento
				if (parmMedicaoHistorico[13] != null) {
					leituraConsumoActionForm
							.setAnormalidadeLeituraFaturada((String) parmMedicaoHistorico[13]);
				}
				// numero consumo mes
				if (parmMedicaoHistorico[14] != null) {
					leituraConsumoActionForm
							.setConsumoMedido(((Integer) parmMedicaoHistorico[14])
									.toString());
				}

				int consumoFaturado = 0;
				// numero consumo fatura mes
				if (parmMedicaoHistorico[15] != null) {
					consumoFaturado = (Integer) parmMedicaoHistorico[15];
					leituraConsumoActionForm
							.setConsumoFaturado(((Integer) parmMedicaoHistorico[15])
									.toString());
				}

				// numero consumo rateio
				if (parmMedicaoHistorico[16] != null) {
					leituraConsumoActionForm
							.setConsumoRateio(((Integer) parmMedicaoHistorico[16])
									.toString());
				}
				// descrição abreviada consumo anormalidade
				if (parmMedicaoHistorico[17] != null) {
					leituraConsumoActionForm
							.setAnormalidadeConsumo((String) parmMedicaoHistorico[17]);
				}
				// descrição abreviada consumo tipo
				if (parmMedicaoHistorico[18] != null) {
					leituraConsumoActionForm
							.setConsumoTipo((String) parmMedicaoHistorico[18]);
				}

				// consumo médio do hidrometro
				if (parmMedicaoHistorico[19] != null) {
					leituraConsumoActionForm
							.setConsumoMedioHidrometro(((Integer) parmMedicaoHistorico[19])
									.toString());
					consumoMedioHidrometro = (Integer) parmMedicaoHistorico[19];
				}
				// consumo medio do imovel
				if (parmMedicaoHistorico[20] != null) {
					leituraConsumoActionForm
							.setConsumoMedioImovel(((Integer) parmMedicaoHistorico[20])
									.toString());
				}
				// consumo mes esgoto
				if (parmMedicaoHistorico[21] != null) {
					leituraConsumoActionForm
							.setConsumoMesEsgoto(((Integer) parmMedicaoHistorico[21])
									.toString());
				}

				if (consumoFaturado != 0 && consumoMedioHidrometro != 0) {
					int operacaoSubMult = (consumoFaturado - consumoMedioHidrometro) * 100;
					BigDecimal percentual = new BigDecimal(operacaoSubMult)
							.divide(new BigDecimal(consumoMedioHidrometro), 2,
									BigDecimal.ROUND_HALF_UP);
					String valorPercentual = "" + percentual;
					leituraConsumoActionForm.setPercentualVariacao(""
							+ valorPercentual.replace(".", ",") + "%");

				}
			}

			Collection<MedicaoHistorico> medicoesHistorico = new ArrayList();
			Collection<ImovelMicromedicao> imoveisMicromedicao = new ArrayList();

			if(leituraConsumoActionForm
					.getIdTipoMedicao() != null && leituraConsumoActionForm
					.getIdTipoMedicao().trim().equals(MedicaoTipo.LIGACAO_AGUA.toString())){
				httpServletRequest.setAttribute("habilitaConsumoEsgoto", true);
			}
			
//========= Obter Consumo médio do Imóvel ===============================
			
			Imovel imovel = new Imovel();
			imovel.setId(new Integer(codigoImovel));

			if(leituraConsumoActionForm.getIdLigacaoAgua() != null){
				ligacaoAgua = true;
			}
			
			if(idMedicaoTipo != null && idMedicaoTipo.equals(MedicaoTipo.POCO.toString())){
				ligacaoAgua = false; 
			}
			
			medicoesHistorico = fachada.carregarDadosMedicao(new Integer(
					codigoImovel), ligacaoAgua);

			Collection imoveisMicromedicaoCarregamento = null;

			if (medicoesHistorico != null && !medicoesHistorico.isEmpty()) {
				Iterator iteratorMedicaoHistorico = medicoesHistorico
						.iterator();

				while (iteratorMedicaoHistorico.hasNext()) {
					MedicaoHistorico medicaoHistoricoConsumo = (MedicaoHistorico) iteratorMedicaoHistorico
							.next();
					if (medicaoHistoricoConsumo.getAnoMesReferencia() != 0) {
						
						
						imoveisMicromedicaoCarregamento = fachada
								.carregarDadosConsumo(
										new Integer(codigoImovel),
										medicaoHistoricoConsumo
												.getAnoMesReferencia(), ligacaoAgua);


						if (imoveisMicromedicaoCarregamento != null) {
							imoveisMicromedicao
									.addAll(imoveisMicromedicaoCarregamento);
						}
					}
				}

//				 Organizar a coleção de Conta
				if (medicoesHistorico != null
						&& !medicoesHistorico.isEmpty()) {
					
					Collections.sort((List) medicoesHistorico,
							new Comparator() {
								public int compare(Object a, Object b) {
									
									int retorno = 0;
									Integer anoMesReferencia1 = ((MedicaoHistorico) a).getAnoMesReferencia();
									Integer anoMesReferencia2 = ((MedicaoHistorico) b).getAnoMesReferencia();

									if(anoMesReferencia1.compareTo(anoMesReferencia2) == 1){
										retorno = -1;
									}else if(anoMesReferencia1.compareTo(anoMesReferencia2) == -1){
										retorno = 1;
									}
									
									return retorno;

								}
						});
				}
				
				// coloca a colecao de medicao historico no request
				sessao.setAttribute("medicoesHistoricos", medicoesHistorico);
				// coloca colecao de consumo historico no request
				
//				 Organizar a coleção de Conta
				if (imoveisMicromedicao != null
						&& !imoveisMicromedicao.isEmpty()) {
					
					Collections.sort((List) imoveisMicromedicao,
							new Comparator() {
								public int compare(Object a, Object b) {
									
									int retorno = 0;
									Integer anoMesReferencia1 = ((ImovelMicromedicao) a).getConsumoHistorico()
										.getReferenciaFaturamento();
									Integer anoMesReferencia2 = ((ImovelMicromedicao) b).getConsumoHistorico()
										.getReferenciaFaturamento();

									if(anoMesReferencia1.compareTo(anoMesReferencia2) == 1){
										retorno = -1;
									}else if(anoMesReferencia1.compareTo(anoMesReferencia2) == -1){
										retorno = 1;
									}
									
									return retorno;

								}
						});
				}
				
				sessao.setAttribute("imoveisMicromedicao", imoveisMicromedicao);
			}

			Collection colecaoImovelSubcategoria = null;
			FiltroImovelSubCategoria filtroImovelSubCategoria = new FiltroImovelSubCategoria();
			filtroImovelSubCategoria
					.adicionarCaminhoParaCarregamentoEntidade("comp_id.subcategoria.categoria");
			filtroImovelSubCategoria
					.adicionarCaminhoParaCarregamentoEntidade("comp_id.subcategoria");
			filtroImovelSubCategoria.adicionarParametro(new ParametroSimples(
					FiltroImovelSubCategoria.IMOVEL_ID, codigoImovel));
			colecaoImovelSubcategoria = fachada.pesquisar(
					filtroImovelSubCategoria, ImovelSubcategoria.class
							.getName());
			sessao.setAttribute("colecaoImovelSubcategoria",
					colecaoImovelSubcategoria);
		}

		// caso venha do substituir ou do alterar
		httpServletRequest.setAttribute("sucesso", httpServletRequest
				.getAttribute("sucesso"));

		sessao.setAttribute("leituraConsumoActionForm",
				leituraConsumoActionForm);
		if (index == 0 && numeroPaginasPesquisa == 0) {
			httpServletRequest.setAttribute("desabilitaBotaoAnterior", 1);
		}
		if (index >= (colecaoIdsImovel.size() - 1)
				&& numeroPaginasPesquisa == (quantidadeTotalPaginas - 1)) {
			httpServletRequest.setAttribute("desabilitaBotaoProximo", 1);
		}

		return retorno;

	}
}
