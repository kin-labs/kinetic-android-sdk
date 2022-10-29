package org.kin.kinetic.helpers

import java.math.BigDecimal

public fun addDecimals(amount: String, decimals: Int): BigDecimal {
    return BigDecimal(amount).movePointRight(decimals)
}

public fun removeDecimals(amount: String, decimals: Int): String {
    return BigDecimal(amount).movePointLeft(decimals).toString()
}