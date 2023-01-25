package org.kin.kinetic.helpers

import org.kin.kinetic.KinBinaryMemo
import com.solana.core.*
import com.solana.programs.TokenProgram

internal fun generateMakeTransferTransaction(
    addMemo: Boolean,
    amount: String,
    blockhash: String,
    destination: String,
    destinationTokenAccount: String,
    index: Int,
    mintDecimals: Int,
    mintFeePayer: String,
    mintPublicKey: String,
    owner: Account,
    ownerTokenAccount: String,
    senderCreate: Boolean = false,
    type: KinBinaryMemo.TransactionType
): Transaction {
    // Create objects from Response
    val destinationPublicKey = PublicKey(destination)
    val destinationTokenAccountPublicKey = PublicKey(destinationTokenAccount)
    val feePayerKey = PublicKey(mintFeePayer)
    val mintKey = PublicKey(mintPublicKey)
    val ownerPublicKey = owner.publicKey
    val ownerTokenAccountPublicKey = PublicKey(ownerTokenAccount)

    var instructions = emptyArray<TransactionInstruction>()

    if (addMemo) {
        instructions += generateKinMemoInstruction(index, type)
    }

    if (senderCreate) {
        instructions += createAssociatedTokenAccountInstruction(
            feePayerKey,
            destinationTokenAccountPublicKey,
            destinationPublicKey,
            mintKey
        )
    }

    instructions += TokenProgram.transferChecked(
            ownerTokenAccountPublicKey,
            destinationTokenAccountPublicKey,
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