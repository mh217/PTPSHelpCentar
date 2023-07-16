package com.example.ptpshelpcentar.data

data class Treatment(
    var Id: String ="",
    var Name: String ="",
    var Description : String ="",
    var Image: String ="",
    var Clusters : ArrayList<String> = arrayListOf(),
)
