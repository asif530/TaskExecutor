package com.poc2.TaskExecutor.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc2.TaskExecutor.model.ProductModel;

import java.util.Arrays;

public class DeepCopy {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        ProductModel productModelOg = new ProductModel(1, Arrays.asList("a", "b") ,"N-12");

        JsonNode jsonNode = objectMapper.valueToTree(productModelOg);

        String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);

        ProductModel productModelNew =   objectMapper.readValue(jsonString, ProductModel.class);

        productModelNew.setProductNumber("N-13");
        productModelNew.setName(Arrays.asList("c", "d"));

        System.out.println(productModelOg.getName());
        System.out.println(productModelNew.getName());
    }
}
