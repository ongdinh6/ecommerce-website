package vn.omdinh.demo.models.responses;

import vn.omdinh.demo.models.requests.PaginatedSearch;

public record PaginatedResultResponse<T>(
        int totalItems,
        int currentPage,
        int totalPage,
        int itemsPerPage,
        PaginatedSearch paginatedSearch,
        T data
) {
}
