package com.standard.service.produto;

import com.standard.domain.Produto;
import com.standard.entity.DomainEntity;
import com.standard.entity.ProductEntity;
import com.standard.entity.ProductHasItemsTypeMeasureEntity;
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
		ProductEntity produtoDB = new ProductEntity();
		produtoDB.setId(produto.getCodigo());
		produtoDB.setBarCode(produto.getBarCode());
		produtoDB.setName(produto.getNome());
		produtoDB.setStatus(StatusEnum.ATIVO);
		produtoDB.setDescription(produto.getDescricao());
		produtoDB.setPrice(produto.getPreco());
		produtoDB.setSalePrice(produto.getPrecoVenda());
		produtoDB.setCostPrice(produto.getPrecoCusto());
		produtoDB.setDiscountPrice(produto.getPrecoOferta());
		produtoDB.setDiscount(produto.getDesconto());
		produtoDB.setWeight(produto.getPeso());
		produtoDB.setPercent(produto.getPorcentagem());
		produtoDB.setDiscountPercent(produto.getPorcentagemDesconto());

		if (produto.getMeasure() != null && produto.getMeasure().getId() != null) {
			produtoDB.setMeasure(measureRepository.getOne(produto.getMeasure().getId()));
		}

		if (produto.getProvider() != null && produto.getProvider().getCodigo() != null) {
			produtoDB.setProvider(providerRepository.getOne(produto.getProvider().getCodigo()));
		}

		if (produto.getCategory() != null && produto.getCategory().getId() != null) {
			produtoDB.setCategory(categoryRepository.getOne(produto.getCategory().getId()));
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

    private void getProdutoHasItensTipoMedida(Produto produto, ProductEntity produtoDB) {
        if (produto.getProdutoHasItensTipoMedida() != null) {
            Set<ProductHasItemsTypeMeasureEntity> set = new HashSet<>();
            produto.getProdutoHasItensTipoMedida().forEach(phitm -> {
                ProductHasItemsTypeMeasureEntity produtoHasItensTipoMedida = new ProductHasItemsTypeMeasureEntity();

                produtoHasItensTipoMedida.setQuantity(phitm.getQuantidade());
                Set<DomainEntity> dominiosDB = new HashSet<>();
                if(phitm.getDomains() != null) {
                    phitm.getDomains().forEach(dominio -> {
                        if(dominio.getCodigo() != null) {
                            dominiosDB.add(domainRepository.getOne(dominio.getCodigo()));
                        }
                    });
                }
                produtoHasItensTipoMedida.setDomains(dominiosDB);
                produtoHasItensTipoMedida.setItemsTypeMeasure(itensTipoMedidaRepository.getOne(phitm.getItemsTypeMeasure().getId()));
                set.add(produtoHasItensTipoMedida);
            });
            produtoDB.setProductHasItemsTypeMeasure(new HashSet<>());
            produtoDB.getProductHasItemsTypeMeasure().addAll(set);
        }
    }

    @Override
	@Transactional
	public Produto alterar(Long codigo, Produto produto) {
		ProductEntity produtoDB = produtoRepository.getOne(codigo);
		produtoDB.setId(produto.getCodigo());
		produtoDB.setBarCode(produto.getBarCode());
		produtoDB.setName(produto.getNome());
		produtoDB.setStatus(produto.getStatus());
		produtoDB.setDescription(produto.getDescricao());
		produtoDB.setPrice(produto.getPreco());
		produtoDB.setSalePrice(produto.getPrecoVenda());
		produtoDB.setCostPrice(produto.getPrecoCusto());
		produtoDB.setDiscountPrice(produto.getPrecoOferta());
		produtoDB.setDiscount(produto.getDesconto());
		produtoDB.setWeight(produto.getPeso());
		produtoDB.setPercent(produto.getPorcentagem());
		produtoDB.setDiscountPercent(produto.getPorcentagemDesconto());


		if (produto.getMeasure() != null && produto.getMeasure().getId() != null) {
			produtoDB.setMeasure(measureRepository.getOne(produto.getMeasure().getId()));
		}

		if (produto.getProvider() != null && produto.getProvider().getCodigo() != null) {
			produtoDB.setProvider(providerRepository.getOne(produto.getProvider().getCodigo()));
		}

		if (produto.getCategory() != null && produto.getCategory().getId() != null) {
			produtoDB.setCategory(categoryRepository.getOne(produto.getCategory().getId()));
		}

		if (produto.getSubcategory() != null && produto.getSubcategory().getCodigo() != null) {
			produtoDB.setSubcategory(subcategoryRepository.getOne(produto.getSubcategory().getCodigo()));
		}

		if (produto.getBrand() != null && produto.getBrand().getCodigo() != null) {
			produtoDB.setBrand(brandRepository.getOne(produto.getBrand().getCodigo()));
		}

		produtoDB.getProductHasItemsTypeMeasure().forEach(d -> d.getDomains().clear() );
		produtoDB.getProductHasItemsTypeMeasure().clear();
        getProdutoHasItensTipoMedida(produto, produtoDB);
		return JpaFunctions.produtoToProdutoEntity.apply(produtoRepository.saveAndFlush(produtoDB));
	}

	@Override
	@Transactional
	public void excluir(Long codigo) {
		ProductEntity produtoDB = produtoRepository.getOne(codigo);
		produtoDB.setStatus(StatusEnum.INATIVO);
		produtoRepository.saveAndFlush(produtoDB);
	}

	@Override
	@Transactional(readOnly = true)
	public Produto consultarByCodigo(Long codigo) {
		ProductEntity p = produtoRepository.getOne(codigo);
		return JpaFunctions.produtoToProdutoEntity.apply(p);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Produto consultarByBarCode(String barcode) {
		Optional<ProductEntity> p = produtoRepository.findByBarCode(barcode.trim());
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
