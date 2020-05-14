package br.com.codenation.service;

import java.util.*;
import java.util.stream.Collectors;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

public class OrderServiceImpl implements OrderService {

	private final ProductRepository productRepository = new ProductRepositoryImpl();

	/**
	 * Calculate the sum of all OrderItems
	 */
	@Override
	public Double calculateOrderValue(List<OrderItem> items) {

		double value = 0.0;

		List<Product> produtosCadastrados = productRepository.findAll();

		for(OrderItem ordem : items){

			Optional<Product> produto = produtosCadastrados.stream().filter(e -> ordem.getProductId().equals(e.getId())).findAny();
			double valorProduto;

			if(produto.isPresent()){

				valorProduto = produto.get().getValue();

				if(produto.get().getIsSale()){
					value += (ordem.getQuantity()*valorProduto*0.8);
				} else {
					value += (ordem.getQuantity()*valorProduto);
				}
			}
		}

		return value;
	}

	/**
	 * Map from idProduct List to Product Set
	 */
	@Override
	public Set<Product> findProductsById(List<Long> ids) {

		return productRepository.findAll().stream()
				.filter(e -> ids.stream().anyMatch(id -> id.equals(e.getId())))
				.collect(Collectors.toSet());

	}

	/**
	 * Calculate the sum of all Orders(List<OrderIten>)
	 */
	@Override
	public Double calculateMultipleOrders(List<List<OrderItem>> orders) {

		double value = 0.0;

		for(List<OrderItem> listaordem : orders){
			value += calculateOrderValue(listaordem);
		}

		return value;
	}

	/**
	 * Group products using isSale attribute as the map key
	 */
	@Override
	public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {

		Map<Boolean, List<Product>> mapa = new TreeMap<>();

		List<Product> produtosEmOferta = productRepository.findAll().stream()
				.filter(e -> productIds.contains(e.getId()) && e.getIsSale().equals(true))
				.collect(Collectors.toList());

		mapa.put(true,produtosEmOferta);

		List<Product> produtosSemOferta = productRepository.findAll().stream()
				.filter(e -> productIds.contains(e.getId()) && e.getIsSale().equals(false))
				.collect(Collectors.toList());

		mapa.put(false,produtosSemOferta);

		return mapa;
	}

}