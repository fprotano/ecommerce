package it.exolab.ecommerce.mapper;

import it.exolab.ecommerce.model.Item;

public interface ItemMapper   extends GenericMapper<Item> {

	public void updateQuantity(Item item);
}
