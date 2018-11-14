package com.standard.service.medida;

import com.standard.domain.Medida;
import com.standard.domain.Produto;
import com.standard.entity.*;
import com.standard.function.JpaFunctions;
import com.standard.repository.CategoriaRepository;
import com.standard.repository.MarcaRepository;
import com.standard.repository.MedidaRepository;
import com.standard.repository.SubCategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MedidaServiceImpl implements MedidaService {

	private MedidaRepository medidaRepository;
	private CategoriaRepository categoriaRepository;
	private SubCategoriaRepository subCategoriaRepository;
	private MarcaRepository marcaRepository;

    public MedidaServiceImpl(MedidaRepository medidaRepository, CategoriaRepository categoriaRepository,
                             SubCategoriaRepository subCategoriaRepository, MarcaRepository marcaRepository) {
        this.medidaRepository = medidaRepository;
        this.categoriaRepository = categoriaRepository;
        this.subCategoriaRepository = subCategoriaRepository;
        this.marcaRepository = marcaRepository;
    }

    @Override
	@Transactional
	public Medida incluir(Medida medida) {
		MedidaEntity medidaDB = new MedidaEntity();
		medidaDB.setDescricao(medida.getDescricao());
		medidaDB.setNome(medida.getNome());
		if (medida.getItensTipoMedida() != null) {
			Set<ItensTipoMedidaEntity> itensSet = new HashSet<>();
			itensMedidaBuild(medida, itensSet);
			medidaDB.setItensTipoMedida(itensSet);
		}
		return JpaFunctions.medidaToMedidaEntity.apply(medidaRepository.saveAndFlush(medidaDB));
	}

	@Override
	@Transactional
	public Medida alterar(Long codigo, Medida medida) {
		MedidaEntity medidaDB = medidaRepository.getOne(codigo);
		medidaDB.setDescricao(medida.getDescricao());
		medidaDB.setNome(medida.getNome());
		medidaDB.getItensTipoMedida().clear();
		if (medida.getItensTipoMedida() != null) {
			Set<ItensTipoMedidaEntity> itensSet = new HashSet<>();
			itensMedidaBuild(medida, itensSet);
			medidaDB.getItensTipoMedida().addAll(itensSet);
		}
		return JpaFunctions.medidaToMedidaEntity.apply(medidaRepository.saveAndFlush(medidaDB));
	}

	private void itensMedidaBuild(Medida medida, Set<ItensTipoMedidaEntity> itensSet) {
		medida.getItensTipoMedida().forEach(itensMedida -> {
			ItensTipoMedidaEntity itens = new ItensTipoMedidaEntity();
			itens.setCategoria(categoriaRepository.getOne(medida.getCategoria().getCodigo()));
			itens.setSubCategoria(subCategoriaRepository.getOne(medida.getSubCategoria().getCodigo()));
			if (medida.getMarca() != null) {
				itens.setMarca(marcaRepository.getOne(medida.getMarca().getCodigo()));
			}
			itens.setValor(itensMedida.getValor());
			itensSet.add(itens);
		});
	}

	@Override
	@Transactional
	public void excluir(Long codigo) {
		MedidaEntity medidaDB = medidaRepository.findById(codigo).orElse(null);
		medidaRepository.delete(medidaDB);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Medida> consultar() {
		return medidaRepository.findAll().stream().map(JpaFunctions.medidaToMedidaEntity).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Medida consultarByCodigo(Long codigo) {
		return JpaFunctions.medidaToMedidaEntity.apply(medidaRepository.findById(codigo).orElse(null));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Medida> consultarByCategoriaSubCategoriaMarca(Produto produto) {
		CategoriaEntity categoria = null;
		SubCategoriaEntity subCategoria = null;
		MarcaEntity marca = null;
		if (produto.getMarca() != null && produto.getMarca().getCodigo() != null) {
			marca = marcaRepository.getOne(produto.getMarca().getCodigo());
		}
		if (produto.getCategoria().getSubCategorias() != null && produto.getSubCategoria().getCodigo() != null) {
			subCategoria = subCategoriaRepository.getOne(produto.getSubCategoria().getCodigo());
		}
		if (produto.getCategoria() != null && produto.getCategoria().getCodigo() != null) {
			categoria = categoriaRepository.getOne(produto.getCategoria().getCodigo());
		}
		return medidaRepository
				.findByItensTipoMedidaCategoriaAndItensTipoMedidaSubCategoriaAndAndItensTipoMedidaMarca(categoria,
						subCategoria, marca)
				.stream().map(JpaFunctions.medidaToMedidaEntity).collect(Collectors.toList());

	}
	
	 
}
