package com.backend.tokokantjil.dtos.outputs;


import java.util.List;

public class AddressOutputDto {
    private Long id;
    private String name;
    private String street;
    private int houseNumber;
    private String houseNumberSuffix;
    private String postalCode;
    private String city;
    private String notes;
    private List<CateringOutputDto> cateringOutputDtoList;
    private List<CustomerOutputDto> customerOutputDtoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getHouseNumberSuffix() {
        return houseNumberSuffix;
    }

    public void setHouseNumberSuffix(String houseNumberSuffix) {
        this.houseNumberSuffix = houseNumberSuffix;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<CateringOutputDto> getCateringOutputDtoList() {
        return cateringOutputDtoList;
    }

    public void setCateringOutputDtoList(List<CateringOutputDto> cateringOutputDtoList) {
        this.cateringOutputDtoList = cateringOutputDtoList;
    }

    public List<CustomerOutputDto> getCustomerOutputDtoList() {
        return customerOutputDtoList;
    }

    public void setCustomerOutputDtoList(List<CustomerOutputDto> customerOutputDtoList) {
        this.customerOutputDtoList = customerOutputDtoList;
    }
}
