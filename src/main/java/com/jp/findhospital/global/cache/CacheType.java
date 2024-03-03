package com.jp.findhospital.global.cache;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CacheType {

    HOSPITAL_PAGE("hospitalPageCache",180,50000),
    HOSPITAL_ID("hospitalIdCache",180,100);

    private final String cacheName;
    private final int expiredAfterWhite;
    private final int maximumSize;


}
