package com.example.yatterdroid.api

data class Status(var id: Int, var content: String, var account: Account)
data class Account(
    var username: String,
    var display_name: String,
    var note: String,
    var avatar: String,
    var header: String
)