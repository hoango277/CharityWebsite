package com.javaweb.utils;

public class MessageUtils {
    public static final String PAGE_NUMBER = "0";
    public static final String PAGE_SIZE = "2";
    public static final String SORT_CATEGORIES_BY = "categoryId";
    public static final String SORT_PRODUCTS_BY = "productId";
    public static final String SORT_USERS_BY = "userId";
    public static final String SORT_ORDERS_BY = "totalAmount";
    public static final String SORT_DIR = "asc";
    public static final Long ADMIN_ID = 2L;
    public static final Long USER_ID = 4L;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    public static final String[] PUBLIC_URLS = {"/auth/**","/send-email","/api/v1/confirm/**"};
    public static final String[] USER_URLS = { "/api/user/**" };
    public static final String[] ADMIN_URLS = { "/api/admin/**" };
    public static final String DEFAULT_PASSWORD = "123456";
}
