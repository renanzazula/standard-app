package com.standard.service.marca;

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
        brand = service.save(brand);
    }

    @Test
    public void incluir() {
        Brand saved = service.save(brand);
        assertNotNull(saved);

        Brand found = service.findById(saved.getCodigo());
        assertEquals(found.getCodigo(), saved.getCodigo());
        assertEquals(found.getNome(), saved.getNome());
        assertEquals(found.getDescricao(), saved.getDescricao());
    }

    @Test
    public void alterar() {
        Brand update = service.findById(brand.getCodigo());
        assertNotNull(update);
        update.setNome(NOME_UPDATE);
        update.setDescricao(DESCRICAO_UPDATE);

        Brand updated = service.update(update.getCodigo(), update);
        assertEquals(update.getCodigo(), updated.getCodigo());
        assertEquals(update.getNome(), updated.getNome());
        assertEquals(update.getDescricao(), updated.getDescricao());
    }

    @Test
    public void consultar() {
        List<Brand> found = service.findAll();
        assertNotNull(found);
    }

    @Test
    public void consultarByCodigo() {
        Brand found = service.findById(brand.getCodigo());
        assertNotNull(found);
        assertEquals(found.getCodigo(), brand.getCodigo());
    }

    @Test
    public void excluir() {

        Brand delete = service.findById(brand.getCodigo());
        assertNotNull(delete);

        service.delete(delete.getCodigo());

        Brand found = service.findById(brand.getCodigo());
        assertNull(found.getCodigo());
        assertNull(found.getNome());
        assertNull(found.getDescricao());
    }
}
