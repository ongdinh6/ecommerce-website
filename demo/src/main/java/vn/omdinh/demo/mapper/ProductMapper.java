package vn.omdinh.demo.mapper;

import nu.studer.sample.tables.records.ProductRecord;
import vn.omdinh.demo.dtos.ProductDTO;

public class ProductMapper {

    public static ProductDTO toDTO(ProductRecord productRecord) {
        ProductDTO dto = new ProductDTO();

        dto.setId(productRecord.getId());
        dto.setName(productRecord.getName());
        dto.setImg(productRecord.getImg());
        dto.setImg01(productRecord.getImg01());
        dto.setImg02(productRecord.getImg02());
        dto.setImg03(productRecord.getImg03());
        dto.setImg04(productRecord.getImg04());
        dto.setPrice(productRecord.getPrice());
        dto.setSize(productRecord.getSize());
        dto.setLongDescription(productRecord.getLongDescription());
        dto.setActive(productRecord.getActive());
        dto.setBrandID(productRecord.getBrandId());
        dto.setMainCamera(productRecord.getMainCamera());

        return dto;
    }

}
