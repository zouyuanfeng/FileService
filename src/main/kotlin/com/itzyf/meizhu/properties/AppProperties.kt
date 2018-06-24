package com.itzyf.meizhu.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * @author 依风听雨
 * @version 创建时间：2017/10/31 10:49
 */
@Component
@ConfigurationProperties(prefix = "app")
class AppProperties {
    var imageUrl: String? = null
    var imageDir: String? = null
}