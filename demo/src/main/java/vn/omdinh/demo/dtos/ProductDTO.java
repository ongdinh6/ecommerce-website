package vn.omdinh.demo.dtos;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import vn.omdinh.demo.models.requests.ProductRequest;
import vn.omdinh.demo.utils.ImageUtils;
import vn.omdinh.demo.utils.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    String id;
    String name;
    String thumbnail;
    String description;
    String category;
    Double price;
    String type;
    Double discount;
    Boolean published;
    Date createdAt;
    Date updatedAt;
    String createdBy;
    String updatedBy;

    public static ProductDTO from(ProductRequest request, MultipartFile thumbnail) throws IOException {
        ProductDTO dto = new ProductDTO();

        dto.setName(request.name());
        dto.setPrice(request.price());
        dto.setDiscount(request.discount());
        dto.setCategory(request.category());
        dto.setDescription(request.description());
        dto.setType(request.type());
        dto.setThumbnail(ImageUtils.encodeMultipartToBase64(thumbnail));
        dto.setPublished(request.published());

        return dto;
    }

    public ProductDTO update(ProductDTO dto) throws IllegalAccessException, NoSuchFieldException {
        var updatedDto = new ProductDTO();

        var fields = dto.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            var value = field.get(dto);

            if (value != null) {
                field.set(updatedDto, value);
            } else {
                var f = this.getClass().getDeclaredField(field.getName());
                f.setAccessible(true);
                field.set(updatedDto, f.get(this));
            }
        }
        return updatedDto;
    }
}
