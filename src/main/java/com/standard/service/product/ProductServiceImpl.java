package com.standard.service.product;

import com.standard.domain.Product;
import com.standard.entity.DomainEntity;
import com.standard.entity.ProductEntity;
import com.standard.entity.ProductHasItemsTypeMeasureEntity;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final BrandRepository brandRepository;
	private final DomainRepository domainRepository;
	private final ProductRepository productRepository;
	private final MeasureRepository measureRepository;
	private final ProviderRepository providerRepository;
	private final CategoryRepository categoryRepository;
	private final SubcategoryRepository subcategoryRepository;
	private final ItemsTypeMeasureRepository itemsTypeMeasureRepository;

    @Override
	@Transactional
	public Product create(Product product) {
		ProductEntity productDB = new ProductEntity();
		productDB.setId(product.getId());
		productDB.setBarCode(product.getBarCode());
		productDB.setName(product.getName());
		productDB.setStatus(StatusEnum.ATIVO);
		productDB.setDescription(product.getDescription());
		productDB.setPrice(product.getPrice());
		productDB.setSalePrice(product.getSalePrice());
		productDB.setCostPrice(product.getCostPrice());
		productDB.setDiscountPrice(product.getDiscountPrice());
		productDB.setDiscount(product.getDiscount());
		productDB.setWeight(product.getWeight());
		productDB.setPercent(product.getPercent());
		productDB.setDiscountPercent(product.getDiscountPercent());

		if (product.getMeasure() != null && product.getMeasure().getId() != null) {
			productDB.setMeasure(measureRepository.getById(product.getMeasure().getId()));
		}

		if (product.getProvider() != null && product.getProvider().getId() != null) {
			productDB.setProvider(providerRepository.getById(product.getProvider().getId()));
		}

		if (product.getCategory() != null && product.getCategory().getId() != null) {
			productDB.setCategory(categoryRepository.getById(product.getCategory().getId()));
		}

		if (product.getSubcategory() != null && product.getSubcategory().getId() != null) {
			productDB.setSubcategory(subcategoryRepository.getById(product.getSubcategory().getId()));
		}

		if (product.getBrand() != null && product.getBrand().getId() != null) {
			productDB.setBrand(brandRepository.getById(product.getBrand().getId()));
		}
        getProductHasItemsTypeMeasure(product, productDB);
        return JpaFunctions.productToProductEntity.apply(productRepository.saveAndFlush(productDB));
	}

    private void getProductHasItemsTypeMeasure(Product product, ProductEntity productDB) {
        if (product.getProductHasItemsTypeMeasure() != null) {
            Set<ProductHasItemsTypeMeasureEntity> set = new HashSet<>();
            product.getProductHasItemsTypeMeasure().forEach(productHasItemsTypeMeasure -> {
                ProductHasItemsTypeMeasureEntity productHasItemsTypeMeasureEntity = new ProductHasItemsTypeMeasureEntity();

                productHasItemsTypeMeasureEntity.setQuantity(productHasItemsTypeMeasure.getQuantity());
                Set<DomainEntity> domainsDB = new HashSet<>();
                if(productHasItemsTypeMeasure.getDomains() != null) {
                    productHasItemsTypeMeasure.getDomains().forEach(domain -> {
                        if(domain.getId() != null) {
                            domainsDB.add(domainRepository.getById(domain.getId()));
                        }
                    });
                }
                productHasItemsTypeMeasureEntity.setDomains(domainsDB);
                productHasItemsTypeMeasureEntity.setItemsTypeMeasure(itemsTypeMeasureRepository.getById(productHasItemsTypeMeasure.getItemsTypeMeasure().getId()));
                set.add(productHasItemsTypeMeasureEntity);
            });
            productDB.setProductHasItemsTypeMeasure(new HashSet<>());
            productDB.getProductHasItemsTypeMeasure().addAll(set);
        }
    }

    @Override
	@Transactional
	public Product update(Long id, Product product) {
		ProductEntity productDB = productRepository.getById(id);
		productDB.setId(product.getId());
		productDB.setBarCode(product.getBarCode());
		productDB.setName(product.getName());
		productDB.setStatus(product.getStatus());
		productDB.setDescription(product.getDescription());
		productDB.setPrice(product.getPrice());
		productDB.setSalePrice(product.getSalePrice());
		productDB.setCostPrice(product.getCostPrice());
		productDB.setDiscountPrice(product.getDiscountPrice());
		productDB.setDiscount(product.getDiscount());
		productDB.setWeight(product.getWeight());
		productDB.setPercent(product.getPercent());
		productDB.setDiscountPercent(product.getDiscountPercent());


		if (product.getMeasure() != null && product.getMeasure().getId() != null) {
			productDB.setMeasure(measureRepository.getById(product.getMeasure().getId()));
		}

		if (product.getProvider() != null && product.getProvider().getId() != null) {
			productDB.setProvider(providerRepository.getById(product.getProvider().getId()));
		}

		if (product.getCategory() != null && product.getCategory().getId() != null) {
			productDB.setCategory(categoryRepository.getById(product.getCategory().getId()));
		}

		if (product.getSubcategory() != null && product.getSubcategory().getId() != null) {
			productDB.setSubcategory(subcategoryRepository.getById(product.getSubcategory().getId()));
		}

		if (product.getBrand() != null && product.getBrand().getId() != null) {
			productDB.setBrand(brandRepository.getById(product.getBrand().getId()));
		}

		productDB.getProductHasItemsTypeMeasure().forEach(d -> d.getDomains().clear() );
		productDB.getProductHasItemsTypeMeasure().clear();
        getProductHasItemsTypeMeasure(product, productDB);
		return JpaFunctions.productToProductEntity.apply(productRepository.saveAndFlush(productDB));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		ProductEntity productDB = productRepository.getById(id);
		productDB.setStatus(StatusEnum.INATIVO);
		productRepository.saveAndFlush(productDB);
	}

	@Override
	@Transactional(readOnly = true)
	public Product getById(Long id) {
		ProductEntity productDB = productRepository.getById(id);
		return JpaFunctions.productToProductEntity.apply(productDB);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Product getByBarCode(String barcode) {
		Optional<ProductEntity> p = productRepository.findByBarCode(barcode.trim());
        return p.map(JpaFunctions.productToProductEntity).orElse(null); // FIXME:
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return productRepository.findAll().stream().map(JpaFunctions.productToProductEntity).collect(Collectors.toList());
	}

}
