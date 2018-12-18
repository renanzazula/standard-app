package com.standard.service.produto;

import com.standard.domain.Produto;
import com.standard.entity.DominioEntity;
import com.standard.entity.ProdutoEntity;
import com.standard.entity.ProdutoHasItensTipoMedidaEntity;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	private final ProdutoRepository produtoRepository;
	private final MedidaRepository medidaRepository;
	private final DominioRepository dominioRepository;
	private final FornecedorRepository fornecedorRepository;
	private final CategoriaRepository categoriaRepository;
	private final SubCategoriaRepository subCategoriaRepository;
	private final MarcaRepository marcaRepository;
	private final ItensTipoMedidaRepository itensTipoMedidaRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, MedidaRepository medidaRepository,
                              DominioRepository dominioRepository, FornecedorRepository fornecedorRepository,
                              CategoriaRepository categoriaRepository, SubCategoriaRepository subCategoriaRepository,
                              MarcaRepository marcaRepository, ItensTipoMedidaRepository itensTipoMedidaRepository) {
        this.produtoRepository = produtoRepository;
        this.medidaRepository = medidaRepository;
        this.dominioRepository = dominioRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.categoriaRepository = categoriaRepository;
        this.subCategoriaRepository = subCategoriaRepository;
        this.marcaRepository = marcaRepository;
        this.itensTipoMedidaRepository = itensTipoMedidaRepository;
    }

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
        getProdutoHasItensTipoMedida(produto, produtoDB);
        return JpaFunctions.produtoToProdutoEntity.apply(produtoRepository.saveAndFlush(produtoDB));
	}

    private void getProdutoHasItensTipoMedida(Produto produto, ProdutoEntity produtoDB) {
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
            produtoDB.setProdutoHasItensTipoMedida(new HashSet<>());
            produtoDB.getProdutoHasItensTipoMedida().addAll(set);
        }
    }

    @Override
	@Transactional
	public Produto alterar(Long codigo, Produto produto) {
		ProdutoEntity produtoDB = produtoRepository.getOne(codigo);
		produtoDB.setCodigo(produto.getCodigo());
		produtoDB.setBarCode(produto.getBarCode());
		produtoDB.setNome(produto.getNome());
		produtoDB.setStatus(produto.getStatus());
		produtoDB.setDescricao(produto.getDescricao());
		produtoDB.setPreco(produto.getPreco());
		produtoDB.setPrecoVenda(produto.getPrecoVenda());
		produtoDB.setPrecoCusto(produto.getPrecoCusto());
		produtoDB.setPrecoOferta(produto.getPrecoOferta());
		produtoDB.setDesconto(produto.getDesconto());
		produtoDB.setPeso(produto.getPeso());
		produtoDB.setPorcentagem(produto.getPorcentagem());
		produtoDB.setPorcentagemDesconto(produto.getPorcentagemDesconto());


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
        getProdutoHasItensTipoMedida(produto, produtoDB);
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
	public Produto consultarByCodigo(Long codigo) {
		ProdutoEntity p = produtoRepository.getOne(codigo);
		return JpaFunctions.produtoToProdutoEntity.apply(p);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Produto consultarByBarCode(String barcode) {
		Optional<ProdutoEntity> p = produtoRepository.findByBarCode(barcode.trim());
		return JpaFunctions.produtoToProdutoEntity.apply(p.get());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Produto> consultar() {
		return produtoRepository.findAll().stream().map(JpaFunctions.produtoToProdutoEntity).collect(Collectors.toList());
	}

}
