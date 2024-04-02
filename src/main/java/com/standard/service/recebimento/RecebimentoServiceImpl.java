package com.standard.service.recebimento;

import com.standard.domain.Recebimento;
import com.standard.entity.RecebimentoEntity;
import com.standard.function.JpaFunctions;
import com.standard.repository.PosRepository;
import com.standard.repository.CustomerRepository;
import com.standard.repository.RecebimentoRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecebimentoServiceImpl implements RecebimentoService {

    private RecebimentoRepository repository;
    private PosRepository posRepository;
    private CustomerRepository customerRepository;

    public RecebimentoServiceImpl(RecebimentoRepository repository, PosRepository posRepository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.posRepository = posRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Recebimento incluir(Recebimento recebimento) {
        RecebimentoEntity entity =  new RecebimentoEntity();
        entity.setCodigo(recebimento.getCodigo());
        entity.setNome(recebimento.getNome());
        entity.setDescricao(recebimento.getDescricao());
        entity.setValor(recebimento.getValor());
        if(recebimento.getPos() != null){
            entity.setPos(posRepository.getOne(recebimento.getPos().getCodigo()));
        }
        if(recebimento.getCustomer() != null){
            entity.setCustomer(customerRepository.getOne(recebimento.getCustomer().getId()));
        }
        return JpaFunctions.recebimentoEntityToRecebimento.apply(repository.saveAndFlush(entity));
    }

    @Override
    public Recebimento alterar(Long codigo, Recebimento recebimento) {
        RecebimentoEntity entity = repository.getOne(codigo);
        entity.setCodigo(recebimento.getCodigo());
        entity.setNome(recebimento.getNome());
        entity.setDescricao(recebimento.getDescricao());
        entity.setValor(recebimento.getValor());
        if(recebimento.getPos() != null){
            entity.setPos(posRepository.getOne(recebimento.getPos().getCodigo()));
        }
//        if(recebimento.getCliente() != null){
//            entity.setCliente(clienteRepository.getOne(recebimento.getCliente().getCodigo()));
//        }
        return JpaFunctions.recebimentoEntityToRecebimento.apply(repository.saveAndFlush(entity));
    }

    @Override
    public void excluir(Long codigo) {
        repository.deleteById(codigo);
    }

    @Override
    public List<Recebimento> consultar() {
        return repository.findAll().stream().map(JpaFunctions.recebimentoEntityToRecebimento).collect(Collectors.toList());
    }

    @Override
    public Recebimento consultarByCodigo(Long codigo) {
        return JpaFunctions.recebimentoEntityToRecebimento.apply(repository.findById(codigo).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!")));
    }
}
