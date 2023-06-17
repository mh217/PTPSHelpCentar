package com.example.ptpshelpcentar

object TipsScorer {

    fun distribution(cluster1: Double,cluster2: Double,cluster3: Double,cluster4: Double) : ArrayList<String> {
        var clusters : ArrayList<String> = arrayListOf()
        var diagnostics : ArrayList<String> = arrayListOf()

        val bclustermean : Double = (cluster1) / 5
        val cclustermean : Double = (cluster2) / 2
        val dclustermean : Double = (cluster3) / 7
        val eclustermean : Double = (cluster4) / 6
        val bdiagnosis : String = Scorer.clustering(bclustermean)
        val cdiagnosis : String = Scorer.clustering(cclustermean)
        val ddiagnosis : String = Scorer.clustering(dclustermean)
        val ediagnosis : String = Scorer.clustering(eclustermean)
        diagnostics.addAll(listOf(bdiagnosis, cdiagnosis, ddiagnosis, ediagnosis))

        if (diagnostics[0] == "HIGH" || diagnostics[0] == "MEDIUM") {
            clusters.add("Alterations")
        }
        if(diagnostics[1] == "HIGH" || diagnostics[1] == "MEDIUM") {
            clusters.add("Avoidance")
        }
        if(diagnostics[2] == "HIGH" || diagnostics[2] == "MEDIUM") {
            clusters.add("Hyperarousal")
        }
        if(diagnostics[3] == "HIGH" || diagnostics[3] == "MEDIUM") {
            clusters.add("Reexperiencing")
        }

        return clusters

    }
}