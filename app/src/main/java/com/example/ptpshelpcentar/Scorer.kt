package com.example.ptpshelpcentar

object Scorer {



    var finaldiagnosis : String = ""


    fun scoring(overall : Double, cluster1: Double,cluster2: Double,cluster3: Double,cluster4: Double, function: Int) : String {
        var diagnosis : ArrayList<String> = arrayListOf()
        var brLOW : Int = 0
        var brMEDIUM : Int = 0
        var brHIGH : Int = 0
        val bclustermean : Double = (cluster1) / 5
        val cclustermean : Double = (cluster2) / 2
        val dclustermean : Double = (cluster3) / 7
        val eclustermean : Double = (cluster4) / 6
        if (overall > 38) {
            val bdiagnosis : String = clustering(bclustermean)
            val cdiagnosis : String = clustering(cclustermean)
            val ddiagnosis : String = clustering(dclustermean)
            val ediagnosis : String = clustering(eclustermean)
            diagnosis.addAll(listOf(bdiagnosis, cdiagnosis, ddiagnosis, ediagnosis))
            for (diagnos in diagnosis) {
                when (diagnos) {
                    "LOW" -> {
                        brLOW +=1
                    }
                    "MEDIUM" -> {
                        brMEDIUM +=1
                    }
                    "HIGH" -> {
                        brHIGH +=1
                    }
                }
            }

            diagnosis(brLOW, brMEDIUM, brHIGH)
            if (function == 1) {
                return finaldiagnosis
            }
            else {
                return writendiagnosis(brLOW, brMEDIUM, brHIGH)
            }

        }
        return if (function == 1) {
            "LOW"
        } else {
            "Osoba ima nisku mogućnost dobivanja PTSP-a"
        }
    }


    fun  clustering (number: Double) : String {
        if (number in 0.0..1.5) {
            return "LOW"
        }
        if (number in 1.5..2.5) {
            return "MEDIUM"
        }
        return "HIGH"
    }

    fun diagnosis (numLOW: Int,numMEDIUM: Int, numHIGH: Int) {
        if (numLOW == 4 || numLOW == 3 || (numLOW==2 && numMEDIUM==2) || (numLOW ==2 && numHIGH ==2) || (numLOW==2 && numHIGH==1 && numMEDIUM == 1)) {
            finaldiagnosis = "LOW"
        }
        else if (numMEDIUM == 4 || numMEDIUM ==3 || (numMEDIUM == 2 && numHIGH ==1 && numLOW ==1) || (numHIGH ==3 && numLOW ==1) || (numHIGH ==2 && numMEDIUM == 2)) {
            finaldiagnosis = "MEDIUM"
        }
        else {
            finaldiagnosis = "HIGH"
        }
    }

    fun writendiagnosis(numLOW: Int,numMEDIUM: Int, numHIGH: Int) :String {
        if (numLOW == 4) {
            return "Vrlo je niska mogućnost da osoba ima PTSP ili da ga razvije "
        }
        else if (numLOW == 3) {
            return "Niska mogućnost da osoba ima PTSP, ali bi trebala pripaziti na simptome koji su povišeni"
        }
        else if ((numLOW==2 && numMEDIUM==2) || (numLOW ==2 && numHIGH ==2)) {
            return "Postoji niska mogućnost da osoba ima PTSP, ali postoji mogućnost da osoba dalje razvije simptome"
        }
        else if ((numLOW==2 && numHIGH==1 && numMEDIUM == 1)) {
            return "Niska mogućnost da osoba ima PTSP, ali se treba pratiti u slučaju razvitka ostalih simptoma"
        }
        else if (numHIGH ==3 && numLOW ==1) {
            return "Postoji mogućnost da osoba ima PTSP ili da je on u fazi razvijanja PTSP simptoma, time pažljivo treba motriti pojavu simptoma i u slučaju da se stanje pogorša javiti se liječniku da bi se napravio službeni test"
        }
        else if (numMEDIUM == 2 && numHIGH ==1 && numLOW ==1) {
            return "Postoji mogućnost da je osoba razvila PTSP-a ili da je on u fazi razvitka u slučaju da osoba kroz period od nekoliko mjeseci ne osjeća poboljšanje trebala bi se javiti liječniku kako bi se napravio službeni test "
        }
        else if (numMEDIUM == 3 && numHIGH ==1) {
            return "Postoji mogućnost razvitka visokih simptoma PTSP koje treba nastaviti promatrati u slučaju danjeg razvijanja simptoma javiti se liječniku kako bi se napravio službeni test"
        }
        else if (numMEDIUM == 3 && numLOW ==1) {
            return " Postoji mogućnost razvitka blagih simptoma PTSP koje treba nastaviti promatrati u slučaju razvitka jačih oblika javiti se liječniku kako bi se napravio službeni test"
        }
        else if (numMEDIUM == 4) {
            return "Postoji mogućnost razvitka nekih ozbiljnih simptoma PTSP-a, osoba treba promatrati ako bi joj se simptomi razvili u neke jače simptome treba se javiti liječniku kako bi se napravio službeni test"
        }
        else if (numHIGH == 2 && numLOW ==1 && numMEDIUM == 1) {
            return "Mogućnost da je osoba razvila PTSP, te bi se trebala javiti svome liječniku kako bi se napravio službeni test i ovisno o tim rezultatima prilagoditi liječenje"
        }
        else if (numHIGH == 3 && numMEDIUM ==1) {
            return "Mogućnost da je osoba razvila PTSP, potrebno se javiti liječniku kako bi se napravio službeni test i na temelju otkriti uznapredovalost bolesti i prilagoditi liječenje"
        }
        else if (numHIGH == 2 || numMEDIUM ==2) {
            return "Mogućnost da je osoba razvila PTSP, te bi se trebala javiti svome liječniku kako bi se napravio službeni test i ovisno o tim rezultatima prilagoditi liječenje"
        }
        return "Osoba ima visoku mogućnost PTSP-a, te bi se trebala javiti svome liječniku kako bi se napravio službeni test i početi s prigodnim liječenjem ovisno o uznapredovalosti bolesti"
    }

    fun validation(overall : Boolean, cluster1: Boolean,cluster2: Boolean,cluster3: Boolean,cluster4: Boolean) : String {
        var validate : ArrayList<Boolean> = arrayListOf()
        var truebr : Int = 0
        validate.addAll(listOf(overall, cluster1, cluster2, cluster3, cluster4))
        for (valid in validate) {
            if (valid) {
                truebr +=1
            }
        }
        val result = truebr *100 /validate.size
        val final = result.toString()

        return "$final %"
    }


}