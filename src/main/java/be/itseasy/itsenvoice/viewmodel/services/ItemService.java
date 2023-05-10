package be.itseasy.itsenvoice.viewmodel.services;

import be.itseasy.itsenvoice.model.entities.Item;
import be.itseasy.itsenvoice.model.repositories.ItemRepository;
import be.itseasy.itsenvoice.model.utils.ItemSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    public List<Item> listAllItem() {
        return itemRepository.findAll();
    }

    public void saveItem(Item itemInfo) {
        itemRepository.save(itemInfo);
    }

    public Item getItem(Integer id) {
        Optional<Item> value=itemRepository.findById(id);
        return value.isPresent()?value.get():new Item();
    }

    public void deleteItem(Integer id) {
        itemRepository.deleteById(id);
    }
    public List<Item> findItems(String searchText){
        return itemRepository.findAll(ItemSpecification.textInAllColumns(searchText));
    }
}