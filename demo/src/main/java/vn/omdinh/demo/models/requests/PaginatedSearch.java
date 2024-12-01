package vn.omdinh.demo.models.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedSearch {
    String query = "";
    String category = "";
    int offset = 0;
    int limit = 2;
}
