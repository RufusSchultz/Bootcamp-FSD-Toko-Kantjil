package com.backend.tokokantjil.dtos.outputs;

import com.backend.tokokantjil.enumerations.Status;
import com.backend.tokokantjil.models.Catering;

import java.time.LocalDateTime;
import java.util.List;

public class OrderOutputDto {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private Status status;
    private boolean isCatering;

    private List<ProductOutputDto> productOutputDtoList;
    private List<DishOutputDto> dishOutputDtoList;
    private CateringOutputDto cateringOutputDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isCatering() {
        return isCatering;
    }

    public void setCatering(boolean catering) {
        isCatering = catering;
    }

    public List<ProductOutputDto> getProductOutputDtoList() {
        return productOutputDtoList;
    }

    public void setProductOutputDtoList(List<ProductOutputDto> productOutputDtoList) {
        this.productOutputDtoList = productOutputDtoList;
    }

    public List<DishOutputDto> getDishOutputDtoList() {
        return dishOutputDtoList;
    }

    public void setDishOutputDtoList(List<DishOutputDto> dishOutputDtoList) {
        this.dishOutputDtoList = dishOutputDtoList;
    }

    public CateringOutputDto getCateringOutputDto() {
        return cateringOutputDto;
    }

    public void setCateringOutputDto(CateringOutputDto cateringOutputDto) {
        this.cateringOutputDto = cateringOutputDto;
    }
}
