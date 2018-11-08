package com.standard.service.medida;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.standard.entity.CategoriaEntity;
import com.standard.entity.ItensTipoMedidaEntity;
import com.standard.entity.MarcaEntity;
import com.standard.entity.MedidaEntity;
import com.standard.entity.SubCategoriaEntity;
import com.standard.function.JpaFunctions;
import com.standard.domain.Medida;
import com.standard.domain.Produto;
import com.standard.repository.CategoriaRepository;
import com.standard.repository.MarcaRepository;
import com.standard.repository.MedidaRepository;
import com.standard.repository.SubCategoriaRepository;

@Service
public class MedidaServiceImpl implements MedidaService {

	@Autowired
	private MedidaRepository medidaRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private SubCategoriaRepository subCategoriaRepository;

	@Autowired
	private MarcaRepository marcaRepository;

	@Override
	public Medida incluir(Medida medida) {
		MedidaEntity medidaDB = new MedidaEntity();
		medidaDB.setDescricao(medida.getDescricao());
		medidaDB.setNome(medida.getNome());
		if (medida.getItensTipoMedida() != null) {
			Set<ItensTipoMedidaEntity> itensSet = new HashSet<>();
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
			medidaDB.setItensTipoMedida(itensSet);
		}
		return JpaFunctions.medidaToMedidaEntity.apply(medidaRepository.saveAndFlush(medidaDB));
	}

	@Override
	public Medida alterar(Integer codigo, Medida medida) {
		MedidaEntity medidaDB = medidaRepository.getOne(codigo);
		medidaDB.setDescricao(medida.getDescricao());
		medidaDB.setNome(medida.getNome());
		medidaDB.getItensTipoMedida().clear();
		if (medida.getItensTipoMedida() != null) {
			Set<ItensTipoMedidaEntity> itensSet = new HashSet<>();
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
			medidaDB.getItensTipoMedida().addAll(itensSet);
		}
		return JpaFunctions.medidaToMedidaEntity.apply(medidaRepository.saveAndFlush(medidaDB));
	}

	@Override
	public void excluir(Integer codigo) {
		MedidaEntity medidaDB = medidaRepository.getOne(codigo);
		medidaRepository.delete(medidaDB);
	}

	@Override
	public List<Medida> consultar() {
		return medidaRepository.findAll().stream().map(JpaFunctions.medidaToMedidaEntity).collect(Collectors.toList());
	}

	@Override
	public Medida consultarByCodigo(Integer codigo) {
		return JpaFunctions.medidaToMedidaEntity.apply(medidaRepository.getOne(codigo));
	}

	@Override
	public List<Medida> consultarByProdutoAndValor(Produto produto) {

		return null;
	}

	@Override
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
