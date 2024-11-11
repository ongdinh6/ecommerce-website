import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { UserLogin } from "api/types.ts";
import { PaginatedSearch } from "pages/UserDataPage/type.ts";

export const userApi = createApi({
  reducerPath: "userApi",
  baseQuery: fetchBaseQuery({ baseUrl: "/api/v1/products" }),
  endpoints: (builder) => ({
    login: builder.mutation<string, UserLogin>({
      query: (user) => ({
        url: "/login",
        method: "POST",
        body: user,
      }),
    }),

    getUsers: builder.query<any[], PaginatedSearch>({
      query: (params) => ({
        url: `?_start=${params.start}&_end=${params.end}`,
        params,
      }),
    }),
  }),
});

export const { useLoginMutation, useGetUsersQuery, useLazyGetUsersQuery } = userApi;
