package app.revanced.patches

import app.revanced.extensions.returnConst
import app.revanced.patcher.annotation.*
import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.fingerprint.method.impl.MethodFingerprint
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.PatchResultSuccess
import app.revanced.patcher.patch.annotations.Patch

object ApiLevelFP : MethodFingerprint(strings = listOf("Failed parsing API level"))

@Patch
@Name("samsung-health")
@Description("Fix Samsung Health")
@Compatibility([Package("knoxsdk.jar")])
@Version("1.0.0")
class SamsungHealthPatch : BytecodePatch(listOf(ApiLevelFP)) {
    override fun execute(context: BytecodeContext) =

        ApiLevelFP.result!!.mutableMethod.returnConst(19)
            .let { PatchResultSuccess() }
}
