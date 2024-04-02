package com.standard.service.produto;

import com.standard.domain.Produto;
import com.standard.entity.DomainEntity;
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
	private final MeasureRepository measureRepository;
	private final DomainRepository domainRepository;
	private final ProviderRepository providerRepository;
	private final CategoryRepository categoryRepository;
	private final SubcategoryRepository subcategoryRepository;
	private final BrandRepository brandRepository;
	private final ItensTipoMedidaRepository itensTipoMedidaRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, MeasureRepository measureRepository,
							  DomainRepository domainRepository, ProviderRepository providerRepository,
							  CategoryRepository categoryRepository, SubcategoryRepository subcategoryRepository,
							  BrandRepository brandRepository, ItensTipoMedidaRepository itensTipoMedidaRepository) {
        this.produtoRepository = produtoRepository;
        this.measureRepository = measureRepository;
        this.domainRepository = domainRepository;
        this.providerRepository = providerRepository;
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.brandRepository = brandRepository;
        this.itensTipoMedidaRepository = itensTipoMedidaRepository;
    }

    @Override
	@Transactional
	public Produto incluir(Produto produto) {
		ProdutoEntity produtoDB = new ProdutoEntity();
		produtoDB.setId(produto.getCodigo());
		produtoDB.setBarCode(produto.getBarCode());
		produtoDB.setNome(produto.getNome());
		produtoDB.setStatus(StatusEnum.ATIVO);
		produtoDB.setDescricao(produto.getDescricao());
		produtoDB.setPreco(produto.getPreco());
		produtoDB.setPrecoVenda(produto.getPrecoVenda());
		produtoDB.setPrecoCusto(produto.getPrecoCusto());
		produtoDB.setPrecoOferta(produto.getPrecoOferta());
		produtoDB.setDesconto(produto.getDesconto());
		produtoDB.setPeso(produto.getPeso());
		produtoDB.setPorcentagem(produto.getPorcentagem());
		produtoDB.setPorcentagemDesconto(produto.getPorcentagemDesconto());

		if (produto.getMeasure() != null && produto.getMeasure().getId() != null) {
			produtoDB.setMeasure(measureRepository.getOne(produto.getMeasure().getId()));
		}

		if (produto.getProvider() != null && produto.getProvider().getCodigo() != null) {
			produtoDB.setProvider(providerRepository.getOne(produto.getProvider().getCodigo()));
		}

		if (produto.getCategory() != null && produto.getCategory().getCodigo() != null) {
			produtoDB.setCategory(categoryRepository.getOne(produto.getCategory().getCodigo()));
		}

		if (produto.getSubcategory() != null && produto.getSubcategory().getCodigo() != null) {
			produtoDB.setSubcategory(subcategoryRepository.getOne(produto.getSubcategory().getCodigo()));
		}

		if (produto.getBrand() != null && produto.getBrand().getCodigo() != null) {
			produtoDB.setBrand(brandRepository.getOne(produto.getBrand().getCodigo()));
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
                Set<DomainEntity> dominiosDB = new HashSet<>();
                if(phitm.getDomains() != null) {
                    phitm.getDomains().forEach(dominio -> {
                        if(dominio.getCodigo() != null) {
                            dominiosDB.add(domainRepository.getOne(dominio.getCodigo()));
                        }
                    });
                }
                produtoHasItensTipoMedida.setDomains(dominiosDB);
                produtoHasItensTipoMedida.setItensTipoMedida(itensTipoMedidaRepository.getOne(phitm.getItemsTypeMeasure().getId()));
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
		produtoDB.setId(produto.getCodigo());
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


		if (produto.getMeasure() != null && produto.getMeasure().getId() != null) {
			produtoDB.setMeasure(measureRepository.getOne(produto.getMeasure().getId()));
		}

		if (produto.getProvider() != null && produto.getProvider().getCodigo() != null) {
			produtoDB.setProvider(providerRepository.getOne(produto.getProvider().getCodigo()));
		}

		if (produto.getCategory() != null && produto.getCategory().getCodigo() != null) {
			produtoDB.setCategory(categoryRepository.getOne(produto.getCategory().getCodigo()));
		}

		if (produto.getSubcategory() != null && produto.getSubcategory().getCodigo() != null) {
			produtoDB.setSubcategory(subcategoryRepository.getOne(produto.getSubcategory().getCodigo()));
		}

		if (produto.getBrand() != null && produto.getBrand().getCodigo() != null) {
			produtoDB.setBrand(brandRepository.getOne(produto.getBrand().getCodigo()));
		}

		produtoDB.getProdutoHasItensTipoMedida().forEach(d -> d.getDomains().clear() );
		produtoDB.getProdutoHasItensTipoMedida().clear();
        getProdutoHasItensTipoMedida(produto, produtoDB);
		return JpaFunctions.produtoToProdutoEntity.apply(produtoRepository.saveAndFlush(produtoDB));
	}

	@Override
	@Transactional
	public void excluir(Long codigo) {
		ProdutoEntity produtoDB = produtoRepository.getOne(codigo);
		produtoDB.setStatus(StatusEnum.INATIVO);
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
		if(p.isPresent()){
			return JpaFunctions.produtoToProdutoEntity.apply(p.get());
		}else{
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Produto> consultar() {
		return produtoRepository.findAll().stream().map(JpaFunctions.produtoToProdutoEntity).collect(Collectors.toList());
	}

}
