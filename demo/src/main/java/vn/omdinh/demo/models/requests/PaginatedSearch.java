package vn.omdinh.demo.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class PaginatedSearch {
    @JsonProperty("DEFAULT_LIMIT")
    private final int DEFAULT_PAGE_SIZE = 10;

    @Getter
    @Setter
    String lastItemId;

    @Getter
    @Setter
    int limit;

    public PaginatedSearch(String lastItemId, int limit) {
        this.lastItemId = lastItemId == null ? "" : lastItemId;
        this.limit = limit <= 0 ? DEFAULT_PAGE_SIZE : limit;
    }
}
