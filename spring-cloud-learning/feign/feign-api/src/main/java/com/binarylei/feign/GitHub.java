package com.binarylei.feign;

import feign.Param;
import feign.RequestLine;

import java.util.List;

/**
 * @author binarylei
 * @version 2019-09-19
 * @since 2.0.0
 */
interface GitHub {
    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);

    @RequestLine("POST /repos/{owner}/{repo}/issues")
    void createIssue(Issue issue, @Param("owner") String owner, @Param("repo") String repo);

}

