package com.standard.service.brand;

import com.standard.BaseTest;
import com.standard.domain.Brand;
import com.standard.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BrandServiceImplTestIT extends BaseTest {

    @Autowired
    private BrandRepository repository;

    private BrandService service;

    @BeforeEach
    public void setUp() {
        service = new BrandServiceImpl(repository);
        setUpMarca();
        brand = service.create(brand);
    }

    @Test
    public void incluir() {
        Brand saved = service.create(brand);
        assertNotNull(saved);

        Brand found = service.findById(saved.getId());
        assertEquals(found.getId(), saved.getId());
        assertEquals(found.getName(), saved.getName());
        assertEquals(found.getDescription(), saved.getDescription());
    }

    @Test
    public void alterar() {
        Brand update = service.findById(brand.getId());
        assertNotNull(update);
        update.setName(NOME_UPDATE);
        update.setDescription(DESCRICAO_UPDATE);

        Brand updated = service.update(update.getId(), update);
        assertEquals(update.getId(), updated.getId());
        assertEquals(update.getName(), updated.getName());
        assertEquals(update.getDescription(), updated.getDescription());
    }

    @Test
    public void consultar() {
        List<Brand> found = service.findAll();
        assertNotNull(found);
    }

    @Test
    public void consultarByCodigo() {
        Brand found = service.findById(brand.getId());
        assertNotNull(found);
        assertEquals(found.getId(), brand.getId());
    }

    @Test
    public void excluir() {

        Brand delete = service.findById(brand.getId());
        assertNotNull(delete);

        service.delete(delete.getId());

        Brand found = service.findById(brand.getId());
        assertNull(found.getId());
        assertNull(found.getName());
        assertNull(found.getDescription());
    }
}
