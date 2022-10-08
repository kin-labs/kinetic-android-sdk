package com.kinlabs.kinetic.helpers

import com.solana.core.AccountMeta
import com.solana.core.PublicKey
import com.solana.core.TransactionInstruction
import com.solana.programs.TokenProgram

fun createSetCloseAuthorityInstruction(
    account: PublicKey,
    currentAuthority: PublicKey,
    newAuthority: PublicKey
): TransactionInstruction {
    var keys = emptyList<AccountMeta>()
    keys += AccountMeta(account, false, true)
    keys += AccountMeta(currentAuthority, true, false)

    val SET_AUTHORITY_INSTRUCTION: Byte = 6
    val AUTHORITY_TYPE: Byte = 3
    val NEW_AUTHORITY_OPTION: Byte = 1

    var data = byteArrayOf(
        SET_AUTHORITY_INSTRUCTION,
        AUTHORITY_TYPE,
        NEW_AUTHORITY_OPTION
    )
    data += newAuthority.toByteArray()

    return TransactionInstruction(TokenProgram.PROGRAM_ID, keys, data)
}