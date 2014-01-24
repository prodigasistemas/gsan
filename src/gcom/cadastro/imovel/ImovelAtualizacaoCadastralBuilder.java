package gcom.cadastro.imovel;

import java.math.BigDecimal;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.cadastro.endereco.FiltroLogradouroTipo;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.fachada.Fachada;
import gcom.util.filtro.ParametroSimples;

public class ImovelAtualizacaoCadastralBuilder {

	private int matricula;
	private ImovelAtualizacaoCadastral imovelAtualizacaoCadastral;

	private AtualizacaoCadastralImovel atualizacaoCadastralImovel;
	
	public ImovelAtualizacaoCadastralBuilder(int matricula, AtualizacaoCadastralImovel atualizacaoCadastralImovel){
		this.matricula = matricula;
		this.imovelAtualizacaoCadastral = new ImovelAtualizacaoCadastral();
		this.atualizacaoCadastralImovel = atualizacaoCadastralImovel;
		
		buildImovel();
	}

	public ImovelAtualizacaoCadastral getImovelAtualizacaoCadastral() {
		return imovelAtualizacaoCadastral;
	}
	
	public void buildImovel(){
		// Linha 2
		imovelAtualizacaoCadastral.setIdImovel(matricula);
		imovelAtualizacaoCadastral.setNumeroImovel(atualizacaoCadastralImovel.getLinhaImovel("numeroImovel"));
		imovelAtualizacaoCadastral.setComplementoEndereco(atualizacaoCadastralImovel.getLinhaImovel("complementoImovel"));
		imovelAtualizacaoCadastral.setIdFonteAbastecimento(Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("fonteAbastecimento")));
		imovelAtualizacaoCadastral.setNumeroIptu(atualizacaoCadastralImovel.getLinhaImovel("numeroIPTU") == null ? null : new BigDecimal(atualizacaoCadastralImovel.getLinhaImovel("numeroIPTU")));
		imovelAtualizacaoCadastral.setNumeroContratoEnergia(atualizacaoCadastralImovel.getLinhaImovel("numeroCelpa").equals("") ? null : Long.parseLong(atualizacaoCadastralImovel.getLinhaImovel("numeroCelpa")));
		imovelAtualizacaoCadastral.setIdLogradouroTipo(Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("idTipoLogradouroImovel")));
		imovelAtualizacaoCadastral.setDsLogradouroTipo(getDescricaoLogradouro(Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("idTipoLogradouroImovel"))));
		imovelAtualizacaoCadastral.setDescricaoLogradouro(atualizacaoCadastralImovel.getLinhaImovel("logradouroImovel"));
		imovelAtualizacaoCadastral.setCodigoCep(Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("cep")));
		imovelAtualizacaoCadastral.setNomeBairro(atualizacaoCadastralImovel.getLinhaImovel("bairro"));
		imovelAtualizacaoCadastral.setNomeMunicipio(atualizacaoCadastralImovel.getLinhaImovel("municipio"));
		imovelAtualizacaoCadastral.setNumeroPontosUtilizacao(Short.parseShort(atualizacaoCadastralImovel.getLinhaImovel("numeroPontosUteis")));
		imovelAtualizacaoCadastral.setNumeroMorador(Short.parseShort(atualizacaoCadastralImovel.getLinhaImovel("numeroOcupantes")));
		imovelAtualizacaoCadastral.setTipoOperacao(Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("tipoOperacao")));

		// Linha 4
		imovelAtualizacaoCadastral.setIdLigacaoAguaSituacao(Integer.parseInt(atualizacaoCadastralImovel.getLinhaServicos("ligacaoAguaSituacao")));
		imovelAtualizacaoCadastral.setIdLigacaoEsgotoSituacao(Integer.parseInt(atualizacaoCadastralImovel.getLinhaServicos("ligacaoEsgotoSituacao")));
		imovelAtualizacaoCadastral.setIdLocalInstalacaoRamal(atualizacaoCadastralImovel.getLinhaServicos("localInstalacaoRamal").equals("") ? null : Integer.parseInt(atualizacaoCadastralImovel.getLinhaServicos("localInstalacaoRamal")));

		// Linha 5
		if (atualizacaoCadastralImovel.getLinhaMedidor().size() > 0) {
			imovelAtualizacaoCadastral.setNumeroHidrometro(atualizacaoCadastralImovel.getLinhaMedidor("numeroHidrometro"));
			imovelAtualizacaoCadastral.setIdMarcaHidrometro(atualizacaoCadastralImovel.getLinhaMedidor("marcaHidrometro").equals("") ? 0 : Integer.parseInt(atualizacaoCadastralImovel.getLinhaMedidor("marcaHidrometro")));
			imovelAtualizacaoCadastral.setIdProtecaoHidrometro(atualizacaoCadastralImovel.getLinhaMedidor("tipoCaixaProtecaoHidrometro").equals("") ? 0 : Integer.parseInt(atualizacaoCadastralImovel.getLinhaMedidor("tipoCaixaProtecaoHidrometro")));
			imovelAtualizacaoCadastral.setIdCapacidadeHidrometro(atualizacaoCadastralImovel.getLinhaMedidor("capacidadeHidrometro").equals("") ? 0 : Integer.parseInt(atualizacaoCadastralImovel.getLinhaMedidor("capacidadeHidrometro")));
		}

		// Linha 6
		imovelAtualizacaoCadastral.setIdCadastroOcorrencia(Integer.parseInt(atualizacaoCadastralImovel.getLinhaAnormalidade("codigoAnormalidade")));
		imovelAtualizacaoCadastral.setDescricaoOutrasInformacoes(atualizacaoCadastralImovel.getLinhaAnormalidade("comentario").trim());
		imovelAtualizacaoCadastral.setCoordenadaY(atualizacaoCadastralImovel.getLinhaAnormalidade("latitude"));
		imovelAtualizacaoCadastral.setCoordenadaX(atualizacaoCadastralImovel.getLinhaAnormalidade("longitude"));
	}
	
	public String getDescricaoLogradouro(int idTipoLogradouro) {
		FiltroLogradouroTipo filtro = new FiltroLogradouroTipo();
		filtro.adicionarParametro(new ParametroSimples(FiltroLogradouroTipo.ID, idTipoLogradouro));
		LogradouroTipo logradouroTipo = (LogradouroTipo) (Fachada.getInstancia().pesquisar(filtro, LogradouroTipo.class.getName()).iterator().next());

		return logradouroTipo.getDescricao();
	}
}
