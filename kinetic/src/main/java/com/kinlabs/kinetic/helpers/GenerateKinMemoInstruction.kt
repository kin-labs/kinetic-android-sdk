package com.kinlabs.kinetic.helpers

import android.util.Base64
import com.kinlabs.kinetic.KinBinaryMemo
import com.kinlabs.kinetic.KineticSdkInternal
import com.solana.core.TransactionInstruction

internal fun generateKinMemoInstruction(appIndex: Int, type: KinBinaryMemo.TransactionType): TransactionInstruction {
    val kinMemo = KinBinaryMemo.Builder(appIndex)
        .setTransferType(type)
        .build()
    val encodedMemo = Base64.encodeToString(kinMemo.encode(), 0).toByteArray()
    return TransactionInstruction(
        KineticSdkInternal.MEMO_V1_PROGRAM_ID.solanaPublicKey,
        emptyList(),
        encodedMemo
    )
}