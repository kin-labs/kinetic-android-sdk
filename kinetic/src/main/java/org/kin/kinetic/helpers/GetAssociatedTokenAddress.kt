package org.kin.kinetic.helpers

import com.solana.core.PublicKey
import com.solana.programs.AssociatedTokenProgram
import com.solana.programs.TokenProgram

internal fun getAssociatedTokenAddress(
    ownerPublicKey: PublicKey,
    mintKey: PublicKey
): PublicKey {
    return com.solana.core.PublicKey.findProgramAddress(
        listOf(
            ownerPublicKey.pubkey,
            TokenProgram.PROGRAM_ID.pubkey,
            mintKey.pubkey
        ), AssociatedTokenProgram.SPL_ASSOCIATED_TOKEN_ACCOUNT_PROGRAM_ID
    ).address
}