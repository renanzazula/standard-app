package com.standard.service.recebimento;

import com.standard.domain.Recebimento;
import com.standard.entity.RecebimentoEntity;
import com.standard.function.JpaFunctions;
import com.standard.repository.CaixaRepository;
import com.standard.repository.ClienteRepository;
import com.standard.repository.RecebimentoRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecebimentoServiceImpl implements RecebimentoService {

    private RecebimentoRepository repository;
    private CaixaRepository caixaRepository;
    private ClienteRepository clienteRepository;

    public RecebimentoServiceImpl(RecebimentoRepository repository, CaixaRepository caixaRepository, ClienteRepository clienteRepository) {
        this.repository = repository;
        this.caixaRepository = caixaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Recebimento incluir(Recebimento recebimento) {
        RecebimentoEntity entity =  new RecebimentoEntity();
        entity.setCodigo(recebimento.getCodigo());
        entity.setNome(recebimento.getNome());
        entity.setDescricao(recebimento.getDescricao());
        entity.setValor(recebimento.getValor());
        if(recebimento.getCaixa() != null){
            entity.setCaixa(caixaRepository.getOne(recebimento.getCaixa().getCodigo()));
        }
        if(recebimento.getCliente() != null){
            entity.setCliente(clienteRepository.getOne(recebimento.getCliente().getCodigo()));
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
        if(recebimento.getCaixa() != null){
            entity.setCaixa(caixaRepository.getOne(recebimento.getCaixa().getCodigo()));
        }
        if(recebimento.getCliente() != null){
            entity.setCliente(clienteRepository.getOne(recebimento.getCliente().getCodigo()));
        }
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
