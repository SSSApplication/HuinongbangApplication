package com.hnb.huinongbang.logic.model

data class PolicyClassifyResponse(val code: Int, val data: List<Classify>, val message: String)

data class Classify(val names: String, val policy: List<Policy>)