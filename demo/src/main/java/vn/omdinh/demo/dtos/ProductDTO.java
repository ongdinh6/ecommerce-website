package vn.omdinh.demo.dtos;


import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class ProductDTO {
    private int id;
    private String name;
    private BigInteger price;
    private String img;
    private String img01;
    private String img02;
    private String img03;
    private String img04;
    private int brandID;
    private int ramID;
    private int romID;
    private int pinID;
    private String size;
    private String selfieCamera;
    private String mainCamera;
    private String longDescription;
    private int active;
}
