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
import com.standard.security.exceptions.BrandNotFoundException;
import com.standard.security.exceptions.CategoryNotFoundException;
import com.standard.security.exceptions.DomainNotFoundException;
import com.standard.security.exceptions.ItemsTypeMeasureException;
import com.standard.security.exceptions.MeasureNotFoundException;
import com.standard.security.exceptions.ProductNotFoundException;
import com.standard.security.exceptions.SubcategoryNotFoundException;
import com.standard.util.ConstantMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
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
			productDB.setMeasure(measureRepository.findById(product.getMeasure().getId()).orElseThrow(() -> new MeasureNotFoundException(ConstantMessage.MEASURE_NOT_FOUND)));
		}

		if (product.getProvider() != null && product.getProvider().getId() != null) {
			productDB.setProvider(providerRepository.findById(product.getProvider().getId()).orElseThrow(() -> new ProviderNotFoundException(ConstantMessage.PROVIDER_NOT_FOUND)));
		}

		if (product.getCategory() != null && product.getCategory().getId() != null) {
			productDB.setCategory(categoryRepository.findById(product.getCategory().getId()).orElseThrow(() -> new CategoryNotFoundException(ConstantMessage.CATEGORY_NOT_FOUND)));
		}

		if (product.getSubcategory() != null && product.getSubcategory().getId() != null) {
			productDB.setSubcategory(subcategoryRepository.findById(product.getSubcategory().getId()).orElseThrow(() -> new SubcategoryNotFoundException(ConstantMessage.SUBCATEGORY_NOT_FOUND)));
		}

		if (product.getBrand() != null && product.getBrand().getId() != null) {
			productDB.setBrand(brandRepository.findById(product.getBrand().getId()).orElseThrow(() -> new BrandNotFoundException(ConstantMessage.BRAND_NOT_FOUND)));
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
                            domainsDB.add(domainRepository.findById(domain.getId()).orElseThrow(() -> new DomainNotFoundException(ConstantMessage.DOMAIN_NOT_FOUND)));
                        }
                    });
                }
                productHasItemsTypeMeasureEntity.setDomains(domainsDB);
                productHasItemsTypeMeasureEntity.setItemsTypeMeasure(
						itemsTypeMeasureRepository.findById(productHasItemsTypeMeasure.getItemsTypeMeasure().getId()).orElseThrow(() -> new ItemsTypeMeasureException(
						ConstantMessage.ITEM_TYPE_MEASURE_NOT_FOUND)));
                set.add(productHasItemsTypeMeasureEntity);
            });
            productDB.setProductHasItemsTypeMeasure(new HashSet<>());
            productDB.getProductHasItemsTypeMeasure().addAll(set);
        }
    }

    @Override
	@Transactional
	public Product update(Long id, Product product) {
		ProductEntity productDB = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(ConstantMessage.PRODUCT_NOT_FOUND));
		productDB.setId(product.getId());
		productDB.setBarCode(product.getBarCode());
		productDB.setName(product.getName());
		productDB.setStatus(StatusEnum.valueOf(product.getStatus()));
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
			productDB.setMeasure(measureRepository.findById(product.getMeasure().getId()).orElseThrow(() -> new EntityNotFoundException(ConstantMessage.MEASURE_NOT_FOUND)));
		}

		if (product.getProvider() != null && product.getProvider().getId() != null) {
			productDB.setProvider(providerRepository.findById(product.getProvider().getId()).orElseThrow(() -> new EntityNotFoundException(ConstantMessage.PROVIDER_NOT_FOUND)));
		}

		if (product.getCategory() != null && product.getCategory().getId() != null) {
			productDB.setCategory(categoryRepository.findById(product.getCategory().getId()).orElseThrow(() -> new EntityNotFoundException(ConstantMessage.CATEGORY_NOT_FOUND)));
		}

		if (product.getSubcategory() != null && product.getSubcategory().getId() != null) {
			productDB.setSubcategory(subcategoryRepository.findById(product.getSubcategory().getId()).orElseThrow(() -> new EntityNotFoundException(ConstantMessage.SUBCATEGORY_NOT_FOUND)));
		}

		if (product.getBrand() != null && product.getBrand().getId() != null) {
			productDB.setBrand(brandRepository.findById(product.getBrand().getId()).orElseThrow(() -> new EntityNotFoundException(ConstantMessage.BRAND_NOT_FOUND)));
		}

		productDB.getProductHasItemsTypeMeasure().forEach(d -> d.getDomains().clear() );
		productDB.getProductHasItemsTypeMeasure().clear();
        getProductHasItemsTypeMeasure(product, productDB);
		return JpaFunctions.productToProductEntity.apply(productRepository.saveAndFlush(productDB));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		ProductEntity productDB = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(ConstantMessage.PRODUCT_NOT_FOUND));
		productDB.setStatus(StatusEnum.DISABLE);
		productRepository.saveAndFlush(productDB);
	}

	@Override
	@Transactional(readOnly = true)
	public Product getById(Long id) {
		ProductEntity productDB = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(ConstantMessage.PRODUCT_NOT_FOUND));
		return JpaFunctions.productToProductEntity.apply(productDB);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Product getByBarCode(String barcode) {
		ProductEntity product = productRepository.findByBarCode(barcode.trim()).orElseThrow(() -> new BrandNotFoundException(ConstantMessage.BRAND_NOT_FOUND));
        return JpaFunctions.productToProductEntity.apply(product);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return productRepository.findAll().stream().map(JpaFunctions.productToProductEntity).toList();
	}

}
