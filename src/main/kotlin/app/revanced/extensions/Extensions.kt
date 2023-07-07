package app.revanced.extensions

import app.revanced.patcher.extensions.InstructionExtensions.addInstruction
import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.util.proxy.mutableTypes.MutableMethod

/**
 * Override method to return a constant.
 *
 * @param value The value to return.
 */
internal fun MutableMethod.returnConst(value: Int) {
    val insn = when (value) {
        in -8..7 -> "const/4"
        in -32768..32767 -> "const/16"
        else -> "const"
    }

    addInstructions(0, """
        $insn v0, $value
        return v0
    """)
}

/**
 * Override method to return `true`.
 */
internal fun MutableMethod.returnTrue() = returnConst(1)

/**
 * Override method to return `false`.
 */
internal fun MutableMethod.returnFalse() = returnConst(0)

/**
 * Override method to return `(Object) null`.
 */
internal fun MutableMethod.returnNull() = addInstructions(0, """
    const/4 v0, 0
    return-object v0
""")

/**
 * Override method to return `void`.
 */
internal fun MutableMethod.returnVoid() = addInstruction(0, "return-void")