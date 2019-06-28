package gcom.cadastro.imovel;

import java.math.BigDecimal;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.cadastro.endereco.FiltroLogradouroTipo;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.fachada.Fachada;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

public class ImovelAtualizacaoCadastralBuilder {

	private ImovelAtualizacaoCadastral imovelAtualizacaoCadastral;
	
	private AtualizacaoCadastral atualizacaoCadastral;
	private AtualizacaoCadastralImovel imovel;

	public ImovelAtualizacaoCadastralBuilder(AtualizacaoCadastral atualizacaoCadastral, AtualizacaoCadastralImovel imovel) {
		this.imovelAtualizacaoCadastral = new ImovelAtualizacaoCadastral();
		this.atualizacaoCadastral = atualizacaoCadastral;
		this.imovel = imovel;

		buildImovel();
	}

	public ImovelAtualizacaoCadastral getImovelAtualizacaoCadastral() {
		return imovelAtualizacaoCadastral;
	}

	public void buildImovel() {
		// Linha 2
		imovelAtualizacaoCadastral.setIdImovel(imovel.getMatricula());
		imovelAtualizacaoCadastral.setTipoOperacao(Integer.parseInt(imovel.getLinhaImovel("tipoOperacao")));

		String inscricao = imovel.getLinhaImovel("inscricao");
		imovelAtualizacaoCadastral.setIdLocalidade(Integer.parseInt(inscricao.substring(0, 3)));
		imovelAtualizacaoCadastral.setCodigoSetorComercial(Integer.parseInt(inscricao.substring(3, 6)));
		imovelAtualizacaoCadastral.setNumeroQuadra(Integer.parseInt(inscricao.substring(6, 10)));
		imovelAtualizacaoCadastral.setLote(Short.parseShort(inscricao.substring(10, 14)));
		imovelAtualizacaoCadastral.setSubLote(Short.parseShort(inscricao.substring(14, 17)));
		imovelAtualizacaoCadastral.setIdRota(atualizacaoCadastral.getIdRota());

		imovelAtualizacaoCadastral.setCodigoMunicipio(Integer.parseInt(imovel.getLinhaImovel("codigoMunicipio")));

		imovelAtualizacaoCadastral.setNumeroIptu(imovel.getLinhaImovel("numeroIPTU"));
		String contratoEnergia = imovel.getLinhaImovel("numeroCelpa");

		if (contratoEnergia.equals("")) {
			imovelAtualizacaoCadastral.setNumeroContratoEnergia(null);
		} else {
			try {
				imovelAtualizacaoCadastral.setNumeroContratoEnergia(Long.parseLong(contratoEnergia));
			} catch (NumberFormatException e) {
				imovelAtualizacaoCadastral.setNumeroContratoEnergia(null);
			}
		}

		imovelAtualizacaoCadastral.setNumeroPontosUtilizacao(Util.setValorInteiro(imovel.getLinhaImovel("numeroPontosUteis")).shortValue());
		imovelAtualizacaoCadastral.setNumeroMorador(Util.setValorInteiro(imovel.getLinhaImovel("numeroOcupantes")).shortValue());

		imovelAtualizacaoCadastral.setIdLogradouroTipo(Integer.parseInt(imovel.getLinhaImovel("idTipoLogradouroImovel")));
		imovelAtualizacaoCadastral.setDsLogradouroTipo(getDescricaoLogradouro(Integer.parseInt(imovel.getLinhaImovel("idTipoLogradouroImovel"))));
		imovelAtualizacaoCadastral.setDescricaoLogradouro(imovel.getLinhaImovel("logradouroImovel"));
		imovelAtualizacaoCadastral.setNumeroImovel(imovel.getLinhaImovel("numeroImovel"));
		imovelAtualizacaoCadastral.setComplementoEndereco(imovel.getLinhaImovel("complementoImovel"));
		imovelAtualizacaoCadastral.setNomeBairro(imovel.getLinhaImovel("bairro"));
		imovelAtualizacaoCadastral.setCodigoCep(Integer.parseInt(imovel.getLinhaImovel("cep")));
		imovelAtualizacaoCadastral.setNomeMunicipio(imovel.getLinhaImovel("municipio"));
		imovelAtualizacaoCadastral.setIdLogradouro(Integer.parseInt(imovel.getLinhaImovel("codigoLogradouro")));
		imovelAtualizacaoCadastral.setIdFonteAbastecimento(Util.setValorInteiro(imovel.getLinhaImovel("fonteAbastecimento")));

		if (Util.isPositivo(imovel.getLinhaImovel("classeSocial"))) {
			imovelAtualizacaoCadastral.setClasseSocial(Short.parseShort(imovel.getLinhaImovel("classeSocial")));
		}

		if (Util.isPositivo(imovel.getLinhaImovel("quantidadeAnimaisDomesticos"))) {
			imovelAtualizacaoCadastral.setQuantidadeAnimaisDomesticos(Short.parseShort(imovel.getLinhaImovel("quantidadeAnimaisDomesticos")));
		}

		if (Util.isBigDecimal(imovel.getLinhaImovel("areaConstruida"))) {
			imovelAtualizacaoCadastral.setAreaConstruida(new BigDecimal(imovel.getLinhaImovel("areaConstruida")));
		}
		if (Util.isBigDecimal(imovel.getLinhaImovel("volPiscina"))) {
			imovelAtualizacaoCadastral.setVolumePiscina(new BigDecimal(imovel.getLinhaImovel("volPiscina")));
		}

		if (Util.isBigDecimal(imovel.getLinhaImovel("volCisterna"))) {
			imovelAtualizacaoCadastral.setVolumeCisterna(new BigDecimal(imovel.getLinhaImovel("volCisterna")));
		}

		if (Util.isBigDecimal(imovel.getLinhaImovel("volCxDagua"))) {
			imovelAtualizacaoCadastral.setVolumeCaixaDagua(new BigDecimal(imovel.getLinhaImovel("volCxDagua")));
		}

		if (Util.isPositivo(imovel.getLinhaImovel("tipoUso"))) {
			imovelAtualizacaoCadastral.setTipoUso(Short.parseShort(imovel.getLinhaImovel("tipoUso")));
		}

		if (Util.isPositivo(imovel.getLinhaImovel("acessoHidrometro"))) {
			imovelAtualizacaoCadastral.setAcessoHidrometro(Short.parseShort(imovel.getLinhaImovel("acessoHidrometro")));
		}

		if (Util.isPositivo(imovel.getLinhaImovel("quantidadeEconomiasSocial"))) {
			imovelAtualizacaoCadastral.setQuantidadeEconomiasSocial(Integer.parseInt(imovel.getLinhaImovel("quantidadeEconomiasSocial")));
		}

		if (Util.isPositivo(imovel.getLinhaImovel("quantidadeEconomiasOutra"))) {
			imovelAtualizacaoCadastral.setQuantidadeEconomiasOutra(Integer.parseInt(imovel.getLinhaImovel("quantidadeEconomiasOutra")));
		}

		if (Util.isPositivo(imovel.getLinhaImovel("percentualAbastecimento"))) {
			imovelAtualizacaoCadastral.setPercentualAbastecimento(Short.parseShort(imovel.getLinhaImovel("percentualAbastecimento")));
		}
		
			imovelAtualizacaoCadastral.setObservacaoCategoria(imovel.getLinhaImovel("observacaoCategoria"));

		// Linha 4
		imovelAtualizacaoCadastral.setIdLigacaoAguaSituacao(Util.setValorInteiro(imovel.getLinhaServicos("ligacaoAguaSituacao")));
		imovelAtualizacaoCadastral.setIdLigacaoEsgotoSituacao(Util.setValorInteiro(imovel.getLinhaServicos("ligacaoEsgotoSituacao")));
		imovelAtualizacaoCadastral.setIdLocalInstalacaoRamal(Util.setValorInteiro(imovel.getLinhaServicos("localInstalacaoRamal")));

		// Linha 5
		if (imovel.isExisteMedidor()) {
			imovelAtualizacaoCadastral.setNumeroHidrometro(imovel.getLinhaMedidor("numeroHidrometro"));
			imovelAtualizacaoCadastral.setIdMarcaHidrometro(Util.setValorInteiro(imovel.getLinhaMedidor("marcaHidrometro")));
			imovelAtualizacaoCadastral.setIdCapacidadeHidrometro(Util.setValorInteiro(imovel.getLinhaMedidor("capacidadeHidrometro")));
			imovelAtualizacaoCadastral.setIdProtecaoHidrometro(Util.setValorInteiro(imovel.getLinhaMedidor("tipoCaixaProtecaoHidrometro")));
		}

		// Linha 6
		imovelAtualizacaoCadastral.setIdCadastroOcorrencia(Util.setValorInteiro(imovel.getLinhaAnormalidade("codigoAnormalidade")));
		imovelAtualizacaoCadastral.setDescricaoOutrasInformacoes(imovel.getLinhaAnormalidade("comentario").trim());
		imovelAtualizacaoCadastral.setCoordenadaX(imovel.getLinhaAnormalidade("latitude"));
		imovelAtualizacaoCadastral.setCoordenadaY(imovel.getLinhaAnormalidade("longitude"));
		imovelAtualizacaoCadastral.setTipoEntrevistado(imovel.getLinhaAnormalidade("tipoEntrevistado"));
	}

	public String getDescricaoLogradouro(int idTipoLogradouro) {
		FiltroLogradouroTipo filtro = new FiltroLogradouroTipo();
		filtro.adicionarParametro(new ParametroSimples(FiltroLogradouroTipo.ID, idTipoLogradouro));
		LogradouroTipo logradouroTipo = (LogradouroTipo) (Fachada.getInstancia().pesquisar(filtro, LogradouroTipo.class.getName()).iterator().next());

		return logradouroTipo.getDescricao();
	}
}
