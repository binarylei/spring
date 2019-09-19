package com.binarylei.feign.annotation;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * @author binarylei
 * @version 2019-09-19
 * @since 2.0.0
 */
public interface HeadersService {

    @RequestLine("GET /api/documents/{contentType}")
    @Headers("Accept: {contentType}")
    String getDocumentByType(@Param("contentType") String type);
}
