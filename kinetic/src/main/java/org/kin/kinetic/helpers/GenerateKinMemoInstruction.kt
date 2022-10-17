package org.kin.kinetic.helpers

import android.util.Base64
import org.kin.kinetic.KinBinaryMemo
import org.kin.kinetic.KineticSdkInternal
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