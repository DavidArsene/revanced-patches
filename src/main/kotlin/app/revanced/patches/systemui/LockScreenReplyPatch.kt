package app.revanced.patches.systemui

import app.revanced.extensions.returnTrue
import app.revanced.patcher.annotation.*
import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.fingerprint.method.impl.MethodFingerprint
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.PatchResultSuccess
import app.revanced.patcher.patch.annotations.Patch

object LockScreenReplyFP : MethodFingerprint(customFingerprint = { methodDef, _ ->
    methodDef.name == "shouldAllowLockscreenRemoteInput" && methodDef.definingClass.contains("Impl")
})

@Patch
@Name("lockscreen-reply")
@Description("Allow replying from the lockscreen")
@Compatibility([Package("com.android.systemui")])
@Version("1.0.0")
class LockScreenReplyPatch : BytecodePatch(listOf(LockScreenReplyFP)) {
    override fun execute(context: BytecodeContext) =

        LockScreenReplyFP.result!!.mutableMethod.returnTrue()
            .let { PatchResultSuccess() }
}
