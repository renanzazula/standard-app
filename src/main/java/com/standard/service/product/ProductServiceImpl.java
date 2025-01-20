package com.standard.service.product;

import com.standard.domain.Product;
import com.standard.entity.DomainEntity;
import com.standard.entity.ProductEntity;
import com.standard.entity.ProductHasItemsTypeMeasureEntity;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.BrandRepository;
import com.standard.repository.CategoryRepository;
import com.standard.repository.DomainRepository;
import com.standard.repository.ItemsTypeMeasureRepository;
import com.standard.repository.MeasureRepository;
import com.standard.repository.ProductRepository;
import com.standard.repository.ProviderRepository;
import com.standard.repository.SubcategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
		productDB.setStatus(StatusEnum.ENABLE);
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
			productDB.setMeasure(measureRepository.findById(product.getMeasure().getId()).orElseThrow(() -> new EntityNotFoundException("Measure não encontrado!")));
		}

		if (product.getProvider() != null && product.getProvider().getId() != null) {
			productDB.setProvider(providerRepository.findById(product.getProvider().getId()).orElseThrow(() -> new EntityNotFoundException("Provider não encontrado!")));
		}

		if (product.getCategory() != null && product.getCategory().getId() != null) {
			productDB.setCategory(categoryRepository.findById(product.getCategory().getId()).orElseThrow(() -> new EntityNotFoundException("Category não encontrado!")));
		}

		if (product.getSubcategory() != null && product.getSubcategory().getId() != null) {
			productDB.setSubcategory(subcategoryRepository.findById(product.getSubcategory().getId()).orElseThrow(() -> new EntityNotFoundException("Subcategory não encontrado!")));
		}

		if (product.getBrand() != null && product.getBrand().getId() != null) {
			productDB.setBrand(brandRepository.findById(product.getBrand().getId()).orElseThrow(() -> new EntityNotFoundException("Brand não encontrado!")));
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
                            domainsDB.add(domainRepository.findById(domain.getId()).orElseThrow(() -> new EntityNotFoundException("Domain não encontrado!")));
                        }
                    });
                }
                productHasItemsTypeMeasureEntity.setDomains(domainsDB);
                productHasItemsTypeMeasureEntity.setItemsTypeMeasure(itemsTypeMeasureRepository.findById(productHasItemsTypeMeasure.getItemsTypeMeasure().getId()).orElseThrow(() -> new EntityNotFoundException("Item Type Measure não encontrado!")));
                set.add(productHasItemsTypeMeasureEntity);
            });
            productDB.setProductHasItemsTypeMeasure(new HashSet<>());
            productDB.getProductHasItemsTypeMeasure().addAll(set);
        }
    }

    @Override
	@Transactional
	public Product update(Long id, Product product) {
		ProductEntity productDB = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product não encontrado!"));
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
			productDB.setMeasure(measureRepository.findById(product.getMeasure().getId()).orElseThrow(() -> new EntityNotFoundException("Product não encontrado!")));
		}

		if (product.getProvider() != null && product.getProvider().getId() != null) {
			productDB.setProvider(providerRepository.findById(product.getProvider().getId()).orElseThrow(() -> new EntityNotFoundException("Provider não encontrado!")));
		}

		if (product.getCategory() != null && product.getCategory().getId() != null) {
			productDB.setCategory(categoryRepository.findById(product.getCategory().getId()).orElseThrow(() -> new EntityNotFoundException("Category não encontrado!")));
		}

		if (product.getSubcategory() != null && product.getSubcategory().getId() != null) {
			productDB.setSubcategory(subcategoryRepository.findById(product.getSubcategory().getId()).orElseThrow(() -> new EntityNotFoundException("SubCategory não encontrado!")));
		}

		if (product.getBrand() != null && product.getBrand().getId() != null) {
			productDB.setBrand(brandRepository.findById(product.getBrand().getId()).orElseThrow(() -> new EntityNotFoundException("Brand não encontrado!")));
		}

		productDB.getProductHasItemsTypeMeasure().forEach(d -> d.getDomains().clear() );
		productDB.getProductHasItemsTypeMeasure().clear();
        getProductHasItemsTypeMeasure(product, productDB);
		return JpaFunctions.productToProductEntity.apply(productRepository.saveAndFlush(productDB));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		ProductEntity productDB = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product não encontrado!"));
		productDB.setStatus(StatusEnum.DISABLE);
		productRepository.saveAndFlush(productDB);
	}

	@Override
	@Transactional(readOnly = true)
	public Product getById(Long id) {
		ProductEntity productDB = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product não encontrado!"));
		return JpaFunctions.productToProductEntity.apply(productDB);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Product getByBarCode(String barcode) {
		ProductEntity product = productRepository.findByBarCode(barcode.trim()).orElseThrow(() -> new EntityNotFoundException("Brand não encontrado!"));
        return JpaFunctions.productToProductEntity.apply(product);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return productRepository.findAll().stream().map(JpaFunctions.productToProductEntity).toList();
	}

}
