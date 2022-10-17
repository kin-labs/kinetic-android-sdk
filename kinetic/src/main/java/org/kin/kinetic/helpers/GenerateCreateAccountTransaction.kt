package org.kin.kinetic.helpers

import org.kin.kinetic.KinBinaryMemo
import com.solana.core.*

internal fun generateCreateAccountTransaction(
    addMemo: Boolean,
    blockhash: String,
    index: Int,
    mintFeePayer: String,
    mintPublicKey: String,
    owner: Account
): Transaction {
    // Create objects from Response
    val mintKey = PublicKey(mintPublicKey)
    val feePayerKey = PublicKey(mintFeePayer)
    val ownerPublicKey = owner.publicKey

    // Get AssociatedTokenAccount
    val ownerTokenAccount = getAssociatedTokenAddress(ownerPublicKey, mintKey)

    var instructions = emptyArray<TransactionInstruction>()

    if (addMemo) {
        instructions += generateKinMemoInstruction(index, KinBinaryMemo.TransactionType.None)
    }

    instructions += createAssociatedTokenAccountInstruction(
        feePayerKey,
        ownerTokenAccount,
        ownerPublicKey,
        mintKey
    )

    instructions += createSetCloseAuthorityInstruction(
        ownerTokenAccount,
        ownerPublicKey,
        feePayerKey
    )

    var transaction = Transaction()
    transaction.signatures = mutableListOf<SignaturePubkeyPair>(SignaturePubkeyPair(null, ownerPublicKey))
    transaction.feePayer = feePayerKey
    transaction.add(*instructions)
    transaction.setRecentBlockHash(blockhash)
    transaction.partialSign(owner)

    return transaction
}