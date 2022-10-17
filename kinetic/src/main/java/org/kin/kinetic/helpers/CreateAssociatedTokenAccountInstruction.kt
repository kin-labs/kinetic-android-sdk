package org.kin.kinetic.helpers

import com.solana.core.AccountMeta
import com.solana.core.PublicKey
import com.solana.core.TransactionInstruction
import com.solana.programs.AssociatedTokenProgram
import com.solana.programs.SystemProgram
import com.solana.programs.TokenProgram

internal fun createAssociatedTokenAccountInstruction(
    feePayer: PublicKey,
    ownerTokenAccount: PublicKey,
    ownerPublicKey: PublicKey,
    mintKey: PublicKey
): TransactionInstruction {
    return TransactionInstruction(
        AssociatedTokenProgram.SPL_ASSOCIATED_TOKEN_ACCOUNT_PROGRAM_ID,
        listOf(
            AccountMeta(feePayer, true, true),
            AccountMeta(ownerTokenAccount, false, true),
            AccountMeta(ownerPublicKey, true, false),
            AccountMeta(mintKey, false, false),
            AccountMeta(SystemProgram.PROGRAM_ID, false, false),
            AccountMeta(TokenProgram.PROGRAM_ID, false, false),
            AccountMeta(TokenProgram.SYSVAR_RENT_PUBKEY, false, false)
        ),
        ByteArray(0)
    )
}