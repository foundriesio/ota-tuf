package com.advancedtelematic.ota_tuf.crypt

import com.advancedtelematic.ota_tuf.data.RepositoryDataType.{Checksum, HashMethod, ValidChecksum}
import org.bouncycastle.crypto.digests.SHA256Digest
import org.bouncycastle.util.encoders.Hex
import com.advancedtelematic.ota_tuf.data.RefinedUtils.refineTry

object Sha256Digest {
  def digest(data: Array[Byte]): Checksum = {
    val digest = new SHA256Digest()
    val buf = Array.fill[Byte](digest.getDigestSize)(0)
    digest.update(data, 0, data.length)
    digest.doFinal(buf, 0)
    val checksum = refineTry[String, ValidChecksum](Hex.toHexString(buf)).get

    Checksum(HashMethod.SHA256, checksum)
  }
}