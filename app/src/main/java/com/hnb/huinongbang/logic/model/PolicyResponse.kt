package com.hnb.huinongbang.logic.model

import java.util.*

data class PolicyResponse(val code: Int, val data: List<Policy>, val message: String, val classify: List<Classify>)

data class Policy(val policy_ID: Int,
                  val policy_Topic: String,
                  val policy_Values: String,
                  val policy_Area: String,
                  val policy_Class: String,
                  val policy_View: Int,
                  val policy_CreateTime: Date
)
