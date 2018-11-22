package com.standard.service.venda;

import com.standard.BaseTest;
import com.standard.repository.*;
import com.standard.service.caixa.CaixaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;



@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VendaServiceImplTestIT extends BaseTest {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private FormaDePagamentoRepository formaDePagamentoRepository;

    @Autowired
    private CaixaRepository caixaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoHasItensTipoMedidaRepository produtoHasItensTipoMedidaRepository;

    private VendaService vendaService;
    private CaixaService caixaService;

    @BeforeEach
    public void setUp() {
        vendaService = new VendaServiceImpl(vendaRepository, formaDePagamentoRepository, caixaRepository,
                clienteRepository, produtoHasItensTipoMedidaRepository, caixaService);


    }

    @Test
    public void incluir() {

    }

    @Test
    public void alterar() {
    }

    @Test
    public void cancelar() {
    }

    @Test
    public void consultarByCodigo() {
    }

    @Test
    public void consultar() {
        vendaService.consultar();
    }

    @Test
    public void filtrarVenda() {
    }
}