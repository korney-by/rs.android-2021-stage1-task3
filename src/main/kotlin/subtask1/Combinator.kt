package subtask1

import java.math.BigInteger

fun main() {
    val combinator = Combinator()

    println(combinator.getChoose(3, 3))
    println(combinator.getChoose(3, 2))
    println(combinator.getChoose(3, 1))
}

class Combinator {


    fun checkChooseFromArray(array: Array<Int>): Int? {
        val postersCount=array[0]
        val colorsCount=array[1]

        if ((postersCount==0) || (colorsCount==0)){
            return null
        }

        if (postersCount==1){
            return colorsCount
        }

        if (postersCount==colorsCount){
            return 1
        }

        for (i in 2 until colorsCount) {
            if (getChoose(colorsCount,i)==postersCount){
                return i
            }
        }
        return null
    }

     fun getChoose(fromN: Int, chooseM: Int): Int {
         return if (fromN < arrFactorials.size) {
             (factorial(fromN).toLong() / (factorial(fromN - chooseM).toLong() * factorial(chooseM).toLong())).toInt()
         } else {
             partFactorial(fromN - chooseM + 1, fromN).divide(factorial(chooseM)).toInt()
         }
    }

    private fun partFactorial(nFrom: Int, nTo: Int): BigInteger {
        var result = nFrom.toBigInteger()
        for (i in nFrom + 1..nTo) {
            result = result.multiply(i.toBigInteger())
        }
        return result
    }

    private fun factorial(n: Int): BigInteger {
        return if (n < arrFactorials.size) {
            arrFactorials[n].toBigInteger()
        } else {
            partFactorial(arrFactorials.size,n).multiply(arrFactorials[arrFactorials.size - 1].toBigInteger())
        }
    }

    companion object {
        val arrFactorials = arrayOf(
            1L,
            1,
            2,
            6,
            24,
            120,
            720,
            5_040,
            40_320,
            362_880,
            3_628_800,
            399_16_800,
            479_001_600,
            6_227_020_800,
            87_178_291_200,
            1_307_674_368_000,
            20_922_789_888_000,
            355_687_428_096_000,
            6_402_373_705_728_000,
            121_645_100_408_832_000,
            2_432_902_008_176_640_000
        )
    }
}
