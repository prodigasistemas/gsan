package gcom.cadastro.imovel;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.cadastro.endereco.FiltroLogradouroTipo;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.fachada.Fachada;
import gcom.util.filtro.ParametroSimples;

public class ImovelAtualizacaoCadastralBuilder {

	private int matricula;
	private ImovelAtualizacaoCadastral imovelAtualizacaoCadastral;

	private AtualizacaoCadastralImovel atualizacaoCadastralImovel;
	
	private AtualizacaoCadastral atualizacaoCadastral;
	
	private int tipoOperacao;
	
	public ImovelAtualizacaoCadastralBuilder(int matricula,AtualizacaoCadastral atualizacaoCadastral,  AtualizacaoCadastralImovel atualizacaoCadastralImovel, int tipoOperacao){
		this.matricula = matricula;
		this.imovelAtualizacaoCadastral = new ImovelAtualizacaoCadastral();
		this.atualizacaoCadastralImovel = atualizacaoCadastralImovel;
		this.atualizacaoCadastral = atualizacaoCadastral;
		this.tipoOperacao = tipoOperacao;
		
		buildImovel();
	}

	public ImovelAtualizacaoCadastral getImovelAtualizacaoCadastral() {
		return imovelAtualizacaoCadastral;
	}
	
	public void buildImovel(){
		// Linha 2
		imovelAtualizacaoCadastral.setIdImovel(matricula);
		imovelAtualizacaoCadastral.setTipoOperacao(tipoOperacao);
		
		String inscricao = atualizacaoCadastralImovel.getLinhaImovel("inscricao");
		imovelAtualizacaoCadastral.setIdLocalidade(Integer.parseInt(inscricao.substring(0, 3)));
		imovelAtualizacaoCadastral.setCodigoSetorComercial(Integer.parseInt(inscricao.substring(3, 6)));
		imovelAtualizacaoCadastral.setNumeroQuadra(Integer.parseInt(inscricao.substring(6, 10)));
		imovelAtualizacaoCadastral.setLote(Short.parseShort(inscricao.substring(10, 14)));
		imovelAtualizacaoCadastral.setSubLote(Short.parseShort(inscricao.substring(14, 17)));
		imovelAtualizacaoCadastral.setIdRota(atualizacaoCadastral.getIdRota());
		
		imovelAtualizacaoCadastral.setCodigoMunicipio(Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("codigoMunicipio")));

		imovelAtualizacaoCadastral.setNumeroIptu(atualizacaoCadastralImovel.getLinhaImovel("numeroIPTU"));
		String contratoEnergia = atualizacaoCadastralImovel.getLinhaImovel("numeroCelpa");
		
		if (contratoEnergia.equals("")) {
			imovelAtualizacaoCadastral.setNumeroContratoEnergia(null);
		} else {
			try {
				imovelAtualizacaoCadastral.setNumeroContratoEnergia(Long.parseLong(contratoEnergia));
			} catch (NumberFormatException e) {
				imovelAtualizacaoCadastral.setNumeroContratoEnergia(null);
			}
		}
		
		imovelAtualizacaoCadastral.setNumeroPontosUtilizacao(Short.parseShort(atualizacaoCadastralImovel.getLinhaImovel("numeroPontosUteis")) == 0 ? null : Short.parseShort(atualizacaoCadastralImovel.getLinhaImovel("numeroPontosUteis")));
		imovelAtualizacaoCadastral.setNumeroMorador(Short.parseShort(atualizacaoCadastralImovel.getLinhaImovel("numeroOcupantes")) == 0 ? null : Short.parseShort(atualizacaoCadastralImovel.getLinhaImovel("numeroOcupantes")));
		
		imovelAtualizacaoCadastral.setIdLogradouroTipo(Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("idTipoLogradouroImovel")));
		imovelAtualizacaoCadastral.setDsLogradouroTipo(getDescricaoLogradouro(Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("idTipoLogradouroImovel"))));
		imovelAtualizacaoCadastral.setDescricaoLogradouro(atualizacaoCadastralImovel.getLinhaImovel("logradouroImovel"));
		imovelAtualizacaoCadastral.setNumeroImovel(atualizacaoCadastralImovel.getLinhaImovel("numeroImovel"));
		imovelAtualizacaoCadastral.setComplementoEndereco(atualizacaoCadastralImovel.getLinhaImovel("complementoImovel"));
		imovelAtualizacaoCadastral.setNomeBairro(atualizacaoCadastralImovel.getLinhaImovel("bairro"));
		imovelAtualizacaoCadastral.setCodigoCep(Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("cep")));
		imovelAtualizacaoCadastral.setNomeMunicipio(atualizacaoCadastralImovel.getLinhaImovel("municipio"));
		imovelAtualizacaoCadastral.setIdLogradouro(Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("codigoLogradouro")));
		imovelAtualizacaoCadastral.setIdFonteAbastecimento(Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("fonteAbastecimento")) == 0 ? null : Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("fonteAbastecimento")));

		// Linha 4
		imovelAtualizacaoCadastral.setIdLigacaoAguaSituacao(Integer.parseInt(atualizacaoCadastralImovel.getLinhaServicos("ligacaoAguaSituacao")) == 0 ? null : Integer.parseInt(atualizacaoCadastralImovel.getLinhaServicos("ligacaoAguaSituacao")));
		imovelAtualizacaoCadastral.setIdLigacaoEsgotoSituacao(Integer.parseInt(atualizacaoCadastralImovel.getLinhaServicos("ligacaoEsgotoSituacao")) == 0 ? null : Integer.parseInt(atualizacaoCadastralImovel.getLinhaServicos("ligacaoEsgotoSituacao")));
		imovelAtualizacaoCadastral.setIdLocalInstalacaoRamal(Integer.parseInt(atualizacaoCadastralImovel.getLinhaServicos("localInstalacaoRamal")) == 0 ? null : Integer.parseInt(atualizacaoCadastralImovel.getLinhaServicos("localInstalacaoRamal")));

		// Linha 5
		if (atualizacaoCadastralImovel.isExisteMedidor()) {
			imovelAtualizacaoCadastral.setNumeroHidrometro(atualizacaoCadastralImovel.getLinhaMedidor("numeroHidrometro"));
			imovelAtualizacaoCadastral.setIdMarcaHidrometro(Integer.parseInt(atualizacaoCadastralImovel.getLinhaMedidor("marcaHidrometro")) == 0 ? null : Integer.parseInt(atualizacaoCadastralImovel.getLinhaMedidor("marcaHidrometro")));
			imovelAtualizacaoCadastral.setIdCapacidadeHidrometro(Integer.parseInt(atualizacaoCadastralImovel.getLinhaMedidor("capacidadeHidrometro")) == 0 ? null : Integer.parseInt(atualizacaoCadastralImovel.getLinhaMedidor("capacidadeHidrometro")));
			imovelAtualizacaoCadastral.setIdProtecaoHidrometro(Integer.parseInt(atualizacaoCadastralImovel.getLinhaMedidor("tipoCaixaProtecaoHidrometro")) == 0 ? null : Integer.parseInt(atualizacaoCadastralImovel.getLinhaMedidor("tipoCaixaProtecaoHidrometro")));
		}

		// Linha 6
		imovelAtualizacaoCadastral.setIdCadastroOcorrencia(Integer.parseInt(atualizacaoCadastralImovel.getLinhaAnormalidade("codigoAnormalidade")) == 0 ? null : Integer.parseInt(atualizacaoCadastralImovel.getLinhaAnormalidade("codigoAnormalidade")));
		imovelAtualizacaoCadastral.setDescricaoOutrasInformacoes(atualizacaoCadastralImovel.getLinhaAnormalidade("comentario").trim());
		imovelAtualizacaoCadastral.setCoordenadaX(atualizacaoCadastralImovel.getLinhaAnormalidade("latitude"));
		imovelAtualizacaoCadastral.setCoordenadaY(atualizacaoCadastralImovel.getLinhaAnormalidade("longitude"));
		imovelAtualizacaoCadastral.setTipoEntrevistado(atualizacaoCadastralImovel.getLinhaAnormalidade("tipoEntrevistado"));
	}
	
	public String getDescricaoLogradouro(int idTipoLogradouro) {
		FiltroLogradouroTipo filtro = new FiltroLogradouroTipo();
		filtro.adicionarParametro(new ParametroSimples(FiltroLogradouroTipo.ID, idTipoLogradouro));
		LogradouroTipo logradouroTipo = (LogradouroTipo) (Fachada.getInstancia().pesquisar(filtro, LogradouroTipo.class.getName()).iterator().next());

		return logradouroTipo.getDescricao();
	}
}
