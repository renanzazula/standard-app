package com.standard.service.produto;

import com.standard.domain.Produto;
import com.standard.entity.DominioEntity;
import com.standard.entity.ProdutoEntity;
import com.standard.entity.ProdutoHasItensTipoMedidaEntity;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private MedidaRepository medidaRepository;

	@Autowired
	private DominioRepository dominioRepository;

	@Autowired
	private FornecedorRepository fornecedorRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private SubCategoriaRepository subCategoriaRepository;

	@Autowired
	private MarcaRepository marcaRepository;

	@Autowired
	private ItensTipoMedidaRepository itensTipoMedidaRepository;

	@Override
	@Transactional
	public Produto incluir(Produto produto) {
		ProdutoEntity produtoDB = new ProdutoEntity();
		produtoDB.setCodigo(produto.getCodigo());
		produtoDB.setBarCode(produto.getBarCode());
		produtoDB.setNome(produto.getNome());
		produtoDB.setStatus(StatusEnum.Ativo);
		produtoDB.setDescricao(produto.getDescricao());
		produtoDB.setPreco(produto.getPreco());
		produtoDB.setPrecoVenda(produto.getPrecoVenda());
		produtoDB.setPrecoCusto(produto.getPrecoCusto());
		produtoDB.setPrecoOferta(produto.getPrecoOferta());
		produtoDB.setDesconto(produto.getDesconto());
		produtoDB.setPeso(produto.getPeso());
		produtoDB.setPorcentagem(produto.getPorcentagem());
		produtoDB.setPorcentagemDesconto(produto.getPorcentagemDesconto());
		produtoDB.setDataHoraCadastro(produto.getDataHoraCadastro());

		if (produto.getMedida() != null && produto.getMedida().getCodigo() != null) {
			produtoDB.setMedida(medidaRepository.getOne(produto.getMedida().getCodigo()));
		}

		if (produto.getFornecedor() != null && produto.getFornecedor().getCodigo() != null) {
			produtoDB.setFornecedor(fornecedorRepository.getOne(produto.getFornecedor().getCodigo()));
		}

		if (produto.getCategoria() != null && produto.getCategoria().getCodigo() != null) {
			produtoDB.setCategoria(categoriaRepository.getOne(produto.getCategoria().getCodigo()));
		}

		if (produto.getSubCategoria() != null && produto.getSubCategoria().getCodigo() != null) {
			produtoDB.setSubCategoria(subCategoriaRepository.getOne(produto.getSubCategoria().getCodigo()));
		}

		if (produto.getMarca() != null && produto.getMarca().getCodigo() != null) {
			produtoDB.setMarca(marcaRepository.getOne(produto.getMarca().getCodigo()));
		}

		if (produto.getProdutoHasItensTipoMedida() != null) {
			Set<ProdutoHasItensTipoMedidaEntity> set = new HashSet<>();
			produto.getProdutoHasItensTipoMedida().forEach(phitm -> {
				ProdutoHasItensTipoMedidaEntity produtoHasItensTipoMedida = new ProdutoHasItensTipoMedidaEntity();
				produtoHasItensTipoMedida.setQuantidade(phitm.getQuantidade());

				Set<DominioEntity> dominiosDB = new HashSet<>();
				if(phitm.getDominios() != null) {
					phitm.getDominios().forEach(dominio -> {
						if(dominio.getCodigo() != null) {
							dominiosDB.add(dominioRepository.getOne(dominio.getCodigo()));
						}
					});
				}
				produtoHasItensTipoMedida.setDominios(dominiosDB);
				produtoHasItensTipoMedida.setItensTipoMedida(itensTipoMedidaRepository.getOne(phitm.getItensTipoMedida().getCodigo()));
				set.add(produtoHasItensTipoMedida);
			});
			produtoDB.setProdutoHasItensTipoMedida(set);
		}
		return JpaFunctions.produtoToProdutoEntity.apply(produtoRepository.saveAndFlush(produtoDB));
	}

	@Override
	@Transactional
	public Produto alterar(Long codigo, Produto produto) {
		ProdutoEntity produtoDB = produtoRepository.getOne(codigo);
		produtoDB.setCodigo(produto.getCodigo());
		produtoDB.setBarCode(produto.getBarCode());
		produtoDB.setNome(produto.getNome());
		produtoDB.setStatus(StatusEnum.Ativo);
		produtoDB.setDescricao(produto.getDescricao());
		produtoDB.setPreco(produto.getPreco());
		produtoDB.setPrecoVenda(produto.getPrecoVenda());
		produtoDB.setPrecoCusto(produto.getPrecoCusto());
		produtoDB.setPrecoOferta(produto.getPrecoOferta());
		produtoDB.setDesconto(produto.getDesconto());
		produtoDB.setPeso(produto.getPeso());
		produtoDB.setPorcentagem(produto.getPorcentagem());
		produtoDB.setPorcentagemDesconto(produto.getPorcentagemDesconto());
		produtoDB.setDataHoraCadastro(produto.getDataHoraCadastro());

		if (produto.getMedida() != null && produto.getMedida().getCodigo() != null) {
			produtoDB.setMedida(medidaRepository.getOne(produto.getMedida().getCodigo()));
		}

		if (produto.getFornecedor() != null && produto.getFornecedor().getCodigo() != null) {
			produtoDB.setFornecedor(fornecedorRepository.getOne(produto.getFornecedor().getCodigo()));
		}

		if (produto.getCategoria() != null && produto.getCategoria().getCodigo() != null) {
			produtoDB.setCategoria(categoriaRepository.getOne(produto.getCategoria().getCodigo()));
		}

		if (produto.getSubCategoria() != null && produto.getSubCategoria().getCodigo() != null) {
			produtoDB.setSubCategoria(subCategoriaRepository.getOne(produto.getSubCategoria().getCodigo()));
		}

		if (produto.getMarca() != null && produto.getMarca().getCodigo() != null) {
			produtoDB.setMarca(marcaRepository.getOne(produto.getMarca().getCodigo()));
		}

		produtoDB.getProdutoHasItensTipoMedida().forEach(d -> d.getDominios().clear() );
		produtoDB.getProdutoHasItensTipoMedida().clear();
		
		if (produto.getProdutoHasItensTipoMedida() != null) {
			Set<ProdutoHasItensTipoMedidaEntity> produtoHasItensTipoMedidaUpdate = new HashSet<>();
			produto.getProdutoHasItensTipoMedida().forEach(phitm -> {
				
				ProdutoHasItensTipoMedidaEntity produtoHasItensTipoMedida = new ProdutoHasItensTipoMedidaEntity();
				produtoHasItensTipoMedida.setQuantidade(phitm.getQuantidade());
				
				if(phitm.getDominios() != null) {
					Set<DominioEntity> dominiosDB = new HashSet<DominioEntity>();
					phitm.getDominios().forEach(dominio -> {
						if(dominio.getCodigo() != null) {
							dominiosDB.add(dominioRepository.getOne(dominio.getCodigo()));
						}
					});
					produtoHasItensTipoMedida.setDominios(dominiosDB);
				}
				produtoHasItensTipoMedida.setItensTipoMedida(itensTipoMedidaRepository.getOne(phitm.getItensTipoMedida().getCodigo()));
				produtoHasItensTipoMedidaUpdate.add(produtoHasItensTipoMedida);
				
			});
			
			produtoDB.getProdutoHasItensTipoMedida().addAll(produtoHasItensTipoMedidaUpdate);
		}

		return JpaFunctions.produtoToProdutoEntity.apply(produtoRepository.saveAndFlush(produtoDB));
	}

	@Override
	@Transactional
	public void excluir(Long codigo) {
		ProdutoEntity produtoDB = produtoRepository.getOne(codigo);
		produtoDB.setStatus(StatusEnum.Inativo);
		produtoRepository.saveAndFlush(produtoDB);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean validarCodigoProduto(Produto produto) {

		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public Produto consultarByCodigo(Long codigo) {
		ProdutoEntity p = produtoRepository.getOne(codigo);
		return JpaFunctions.produtoToProdutoEntity.apply(p);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Produto consultarByBarCode(Produto produto) {
		Optional<ProdutoEntity> p = produtoRepository.findByBarCode(produto.getBarCode().trim());
		return JpaFunctions.produtoToProdutoEntity.apply(p.get());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Produto> consultar() {
		return produtoRepository.findAll().stream().map(JpaFunctions.produtoToProdutoEntity).collect(Collectors.toList());
	}

}
