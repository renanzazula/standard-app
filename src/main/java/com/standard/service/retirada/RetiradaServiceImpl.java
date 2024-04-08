package com.standard.service.retirada;

import com.standard.domain.Retirada;
import com.standard.entity.RetiradaEntity;
import com.standard.function.JpaFunctions;
import com.standard.repository.PosRepository;
import com.standard.repository.RetiradaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RetiradaServiceImpl implements RetiradaService {

    private RetiradaRepository repository;
    private PosRepository posRepository;

    public RetiradaServiceImpl(RetiradaRepository retiradaRepository, PosRepository posRepository) {
        this.repository = retiradaRepository;
        this.posRepository = posRepository;
    }

    @Override
    public Retirada incluir(Retirada obj) {
        RetiradaEntity retiradaDB = new RetiradaEntity();
        retiradaDB.setDescription(obj.getDescricao());
        retiradaDB.setAmount(obj.getValor());
        retiradaDB.setCaixa(posRepository.getOne(obj.getPos().getCodigo()));
        return JpaFunctions.retiradaEntityToRetirada.apply(repository.saveAndFlush(retiradaDB));
    }

    @Override
    public Retirada alterar(Long codigo, Retirada obj) {
        RetiradaEntity retiradaDB = repository.getOne(codigo);
        retiradaDB.setDescription(obj.getDescricao());
        retiradaDB.setAmount(obj.getValor());
        retiradaDB.setCaixa(posRepository.getOne(obj.getPos().getCodigo()));
        return JpaFunctions.retiradaEntityToRetirada.apply(repository.saveAndFlush(retiradaDB));
    }

    @Override
    public Retirada consultarByCodigo(Long codigo) {
        return JpaFunctions.retiradaEntityToRetirada.apply(repository.findById(codigo).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!")));
    }

    @Override
    public List<Retirada> consultar() {
        return repository.findAll().stream().map(JpaFunctions.retiradaEntityToRetirada).collect(Collectors.toList());
    }

    @Override
    public void excluir(Long codigo) {
        repository.deleteById(codigo);
    }
}
