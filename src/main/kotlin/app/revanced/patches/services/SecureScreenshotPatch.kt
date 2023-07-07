package app.revanced.patches.services

import app.revanced.extensions.returnFalse
import app.revanced.patcher.annotation.*
import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.fingerprint.method.impl.MethodFingerprint
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.PatchResultSuccess
import app.revanced.patcher.patch.annotations.Patch

object SecureScreenshotFP : MethodFingerprint(customFingerprint = { methodDef, _ ->
    methodDef.name == "isSecureLocked"
})

@Patch
@Name("secure-screenshot")
@Description("Allow screenshots in any app")
@Compatibility([Package("services.jar")])
@Version("1.0.0")
class SecureScreenshotPatch : BytecodePatch(listOf(SecureScreenshotFP)) {
    override fun execute(context: BytecodeContext) =

        SecureScreenshotFP.result!!.mutableMethod.returnFalse()
            .let { PatchResultSuccess() }
}
