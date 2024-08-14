package com.backend.tokokantjil.services;

import com.backend.tokokantjil.dtos.inputs.CateringInputDto;
import com.backend.tokokantjil.dtos.mappers.CateringMapper;
import com.backend.tokokantjil.dtos.outputs.CateringOutputDto;
import com.backend.tokokantjil.exceptions.RecordNotFoundException;
import com.backend.tokokantjil.models.Address;
import com.backend.tokokantjil.models.Catering;
import com.backend.tokokantjil.models.Dish;
import com.backend.tokokantjil.models.Product;
import com.backend.tokokantjil.repositories.AddressRepository;
import com.backend.tokokantjil.repositories.CateringRepository;
import com.backend.tokokantjil.repositories.DishRepository;
import com.backend.tokokantjil.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CateringService {
    private final CateringRepository cateringRepository;
    private final AddressRepository addressRepository;
    private final DishRepository dishRepository;
    private final ProductRepository productRepository;

    public CateringService(CateringRepository cateringRepository, AddressRepository addressRepository, DishRepository dishRepository, ProductRepository productRepository) {
        this.cateringRepository = cateringRepository;
        this.addressRepository = addressRepository;
        this.dishRepository = dishRepository;
        this.productRepository = productRepository;
    }

    public List<CateringOutputDto> getEveryCatering() {
        List<CateringOutputDto> list = new ArrayList<>();
        for (Catering i: this.cateringRepository.findAll()) {
            list.add(CateringMapper.fromCateringToCateringOutputDto(i));
        }
        return list;
    }

    public CateringOutputDto getCateringById(Long id) {
        Catering catering = this.cateringRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No catering with id " + id + " found."));
        return CateringMapper.fromCateringToCateringOutputDto(catering);
    }

    public CateringOutputDto createNewCatering(CateringInputDto cateringInputDto) {
        Catering catering = this.cateringRepository.save(CateringMapper.fromCateringInputDtoToCatering(cateringInputDto));
        return CateringMapper.fromCateringToCateringOutputDto(catering);
    }

    public void deleteCateringById(Long id) {
        if(this.cateringRepository.findById(id).isPresent()) {
            this.cateringRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No catering with id " + id + " found.");
        }
    }

    public CateringOutputDto updateCateringWithNewCateringInputDto(Long id, CateringInputDto cateringInputDto) {
        Catering oldCatering = this.cateringRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No catering with id " + id + " found."));
        Catering cateringUpdate = CateringMapper.fromCateringInputDtoToCatering(cateringInputDto);
        Catering newCatering = this.cateringRepository.save(CateringMapper.fromCateringToUpdatedCatering(oldCatering, cateringUpdate));

        return CateringMapper.fromCateringToCateringOutputDto(newCatering);
    }

    public CateringOutputDto setCateringAddress(Long id, Long addressId) {
        Catering catering = this.cateringRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No catering with id " + id + " found."));
        Address address = this.addressRepository.findById(addressId).orElseThrow(() -> new RecordNotFoundException("No address with id " + id + " found."));

        catering.setAddress(address);
        this.cateringRepository.save(catering);

        return CateringMapper.fromCateringToCateringOutputDto(catering);
    }

    public String addProductToListOfCatering(Long id, Long productId) {
        Catering catering = this.cateringRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No catering with id " + id + " found."));
        Product product = this.productRepository.findById(productId).orElseThrow(() -> new RecordNotFoundException("No product with id " + productId + " found."));
        String response = "";

        if (product.isForRetail()){
            catering.setAppraised(false);
            catering.getProducts().add(product);
            this.cateringRepository.save(catering);

            response = "Added product " + productId + " to catering.";
        } else {
            response = "Unable to add product " + productId + ". Product is not for retail.";
        }
        return response;
    }

    public String removeProductFromListOfCatering(Long id, Long productId) {
        Catering catering = this.cateringRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No catering with id " + id + " found."));
        String response = "No product with id " + productId + " found. Catering is unchanged.";
        List<Product> productList = catering.getProducts();

        for (Product product : catering.getProducts()) {
            if (product.getId().equals(productId)) {
                productList.remove(product);
                catering.setAppraised(false);
                response = "Product with id " + productId + " removed from catering.";
                break;
            }
        }
        catering.setProducts(productList);
        this.cateringRepository.save(catering);

        return response;
    }

    public String addDishToListOfCatering(Long id, Long dishId) {
        Catering catering = this.cateringRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No catering with id " + id + " found."));
        Dish dish = this.dishRepository.findById(dishId).orElseThrow(() -> new RecordNotFoundException("No dish with id " + dishId + " found."));
        String response = "";

        if (dish.isAppraised()) {
            catering.setAppraised(false);
            catering.getDishes().add(dish);
            this.cateringRepository.save(catering);
            response = "Added dish " + dish.getId() + " to catering.";
        } else {
            response = "Unable to add dish to catering. Set prices dish " + dish.getId() + " first.";
        }
        return response;
    }

    public String removeDishFromListOfCatering(Long id, Long dishId) {
        Catering catering = this.cateringRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No catering with id " + id + " found."));
        String response = "No dish with id " + dishId + " found. Catering is unchanged.";
        List<Dish> dishList = catering.getDishes();

        for (Dish dish : catering.getDishes()) {
            if (dish.getId().equals(dishId)) {
                dishList.remove(dish);
                catering.setAppraised(false);
                response = "Dish with id " + dishId + " removed from catering.";
                break;
            }
        }
        catering.setDishes(dishList);
        this.cateringRepository.save(catering);

        return response;
    }

    public String setAgreedPriceOfCatering(Long id, double agreedPrice) {
        Catering catering = this.cateringRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No catering with id " + id + " found."));

        catering.setAgreedPrice(agreedPrice);
        this.cateringRepository.save(catering);

        String response = "Price of catering " + catering.getId() + " set to " + catering.getAgreedPrice() + ".";
        if (catering.getAgreedPrice() < catering.getTotalSellPrice()) {
            response = "Agreed price is lower than total sell price!" + response;
        }

        return response;
    }

    public String calculateCateringPrices(Long id, double laborAndMaterialCost) {
        Catering catering = this.cateringRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No catering with id " + id + " found."));
        double laborAndMaterialPriceMultiplier = 1.5;
        String response = "";

        if (!catering.isAppraised()) {
            double totalCostPrice = 0;
            double totalSellPrice = 0;

            for (Product product : catering.getProducts()) {
                totalCostPrice += product.getBuyPrice();
                totalSellPrice += product.getSellPrice();
            }
            for (Dish dish : catering.getDishes()) {
                totalCostPrice += dish.getProductionPrice();
                totalSellPrice += dish.getSellPrice();
            }

            catering.setTotalCostPrice(laborAndMaterialCost + totalCostPrice);
            catering.setTotalSellPrice(laborAndMaterialCost * laborAndMaterialPriceMultiplier + totalSellPrice);
            catering.setAppraised(true);
            this.cateringRepository.save(catering);

            response = "Catering prices calculated. Cost price: " + catering.getTotalCostPrice() + " and sell price: " + catering.getTotalSellPrice();
        } else {
            response = "Catering prices are already calculated. Reset prices first if you want to recalculate them.";
        }
        if (catering.getTotalSellPrice() > catering.getAgreedPrice()){
            response = "Agreed price is lower than total sell price! " + response;
        }
        return response;
    }

    public CateringOutputDto setCateringPricesToZero(Long id) {
        Catering catering = this.cateringRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No catering with id " + id + " found."));

        catering.setAppraised(false);
        catering.setTotalCostPrice(0);
        catering.setTotalSellPrice(0);
        this.cateringRepository.save(catering);

        return CateringMapper.fromCateringToCateringOutputDto(catering);
    }

}
