package org.kin.kinetic.helpers

import com.solana.core.PublicKey
import com.solana.programs.AssociatedTokenProgram
import com.solana.programs.TokenProgram

internal fun getTokenAddress(
    ownerPublicKey: String,
    mintKey: String
): String? {
    try {
        return com.solana.core.PublicKey.findProgramAddress(
            listOf(
                PublicKey(ownerPublicKey).pubkey,
                TokenProgram.PROGRAM_ID.pubkey,
                PublicKey(mintKey).pubkey
            ), AssociatedTokenProgram.SPL_ASSOCIATED_TOKEN_ACCOUNT_PROGRAM_ID
        ).address.toBase58()
    } catch (_: Exception) {
        return null
    }
}