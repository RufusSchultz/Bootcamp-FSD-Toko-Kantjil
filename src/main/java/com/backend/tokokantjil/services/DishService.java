package com.backend.tokokantjil.services;

import com.backend.tokokantjil.dtos.inputs.DishInputDto;
import com.backend.tokokantjil.dtos.outputs.DishOutputDto;
import com.backend.tokokantjil.exceptions.RecordNotFoundException;
import com.backend.tokokantjil.dtos.mappers.DishMapper;
import com.backend.tokokantjil.models.Dish;
import com.backend.tokokantjil.repositories.DishRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishService {
    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<DishOutputDto> getAllDishes() {
        List<DishOutputDto> list = new ArrayList<>();
        for (Dish i: this.dishRepository.findAll()) {
            list.add(DishMapper.fromDishToDishOutputDto(i));
        }
        return list;
    }

    public DishOutputDto getDishById(Long id) {
        Dish p = this.dishRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No dish with id " + id + " found."));
        return DishMapper.fromDishToDishOutputDto(p);
    }

    public DishOutputDto createDish(DishInputDto dishInputDto) {
        Dish p = this.dishRepository.save(DishMapper.fromDishInputDtoToDish(dishInputDto));
        return DishMapper.fromDishToDishOutputDto(p);
    }

    public void deleteDish(Long id) {
        if(this.dishRepository.findById(id).isPresent()) {
            this.dishRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No dish with id " + id + " found.");
        }
    }

    public DishOutputDto updateDish(Long id, DishInputDto dishInputDto) {
        Dish d1 = this.dishRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No dish with id " + id + " found."));
        Dish d2 = DishMapper.fromDishInputDtoToDish(dishInputDto);
        Dish d3 = this.dishRepository.save(DishMapper.fromDishToUpdatedDish(d1, d2));

        return DishMapper.fromDishToDishOutputDto(d3);
    }
}
