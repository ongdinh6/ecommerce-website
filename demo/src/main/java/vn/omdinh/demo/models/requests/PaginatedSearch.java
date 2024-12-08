package vn.omdinh.demo.models.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.omdinh.demo.utils.StringUtils;

@NoArgsConstructor
public class PaginatedSearch {
 
    @JsonIgnore
    private final String EMPTY_STRING = "";

    @JsonProperty("DEFAULT_PAGE_SIZE")
    private final int DEFAULT_PAGE_SIZE = 10;

    /** Set default values used when creating no args constructor instances **/
    @Getter
    String query = EMPTY_STRING;
    @Getter
    String category = EMPTY_STRING;
    @Getter
    @Setter
    String lastItemId = EMPTY_STRING;
    @Getter
    int limit = DEFAULT_PAGE_SIZE;

    public PaginatedSearch(String query, String category, String lastItemId, int limit) {
        this.query = StringUtils.valueAsDefault(query, EMPTY_STRING);
        this.category = StringUtils.valueAsDefault(category, EMPTY_STRING);
        this.lastItemId = lastItemId;
        this.limit = limit <= 0 ? DEFAULT_PAGE_SIZE : limit;
    }
}
