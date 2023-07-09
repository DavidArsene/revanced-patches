package app.revanced.patches.services

import app.revanced.extensions.returnFalse
import app.revanced.patcher.annotation.*
import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.fingerprint.method.impl.MethodFingerprint
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.PatchResultSuccess
import app.revanced.patcher.patch.annotations.Patch

object KGSupportedFP : MethodFingerprint(customFingerprint = { methodDef, _ ->
    methodDef.name == "isSupportKGOnSEC"
})

@Patch
@Name("knoxguard")
@Description("Disable KnoxGuard")
@Compatibility([Package("services.jar")])
@Version("1.0.0")
class KnoxGuardPatch : BytecodePatch(listOf(KGSupportedFP)) {
    override fun execute(context: BytecodeContext) =

        KGSupportedFP.result!!.mutableMethod.returnFalse()
            .let { PatchResultSuccess() }
}
