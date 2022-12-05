package org.kin.kinetic.helpers

import org.kin.kinetic.KinBinaryMemo
import com.solana.core.*
import com.solana.programs.TokenProgram

internal fun generateMakeTransferTransaction(
    addMemo: Boolean,
    amount: String,
    blockhash: String,
    destination: String,
    index: Int,
    mintDecimals: Int,
    mintFeePayer: String,
    mintPublicKey: String,
    owner: Account,
    senderCreate: Boolean = false,
    type: KinBinaryMemo.TransactionType
): Transaction {
    // Create objects from Response
    val mintKey = PublicKey(mintPublicKey)
    val feePayerKey = PublicKey(mintFeePayer)
    val ownerPublicKey = owner.publicKey

    val ownerTokenAccount = getAssociatedTokenAddress(ownerPublicKey, mintKey)
    val destinationTokenAccount = getAssociatedTokenAddress(PublicKey(destination), mintKey)

    var instructions = emptyArray<TransactionInstruction>()

    if (addMemo) {
        instructions += generateKinMemoInstruction(index, type)
    }

    if (senderCreate) {
        instructions += createAssociatedTokenAccountInstruction(
            feePayerKey,
            destinationTokenAccount,
            ownerPublicKey,
            mintKey
        )
    }

    instructions += TokenProgram.transferChecked(
            ownerTokenAccount,
            destinationTokenAccount,
            amount.toLong(),
            mintDecimals.toByte(),
            ownerPublicKey,
            mintKey
        )

    var transaction = Transaction()
    transaction.signatures = mutableListOf<SignaturePubkeyPair>(SignaturePubkeyPair(null, ownerPublicKey))
    transaction.feePayer = feePayerKey
    transaction.add(*instructions)
    transaction.setRecentBlockHash(blockhash)
    transaction.partialSign(owner)

    return transaction
}